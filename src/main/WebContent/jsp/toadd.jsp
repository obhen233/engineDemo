<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加表达式</title>
  <script type="text/ecmascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script> 
    <script type="text/ecmascript" src="${pageContext.request.contextPath}/static/js/trirand/i18n/grid.locale-en.js"></script>
    <script type="text/ecmascript" src="${pageContext.request.contextPath}/static/js/trirand/jquery.jqGrid.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/static/css/trirand/ui.jqgrid-bootstrap.css" />
	<script>
		$.jgrid.defaults.width = 780;
	</script>
	<script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <meta charset="utf-8" />
</head>
<body>
	<button class="btn btn-primary" data-toggle="modal" data-target="#rules">添加规则</button>
	<button class="btn btn-primary" type="button" onclick="saveExpression()" >保存表达式</button>
	<div style="margin-left:20px">
    	<table id="jqGrid"></table>
    </div>
   
	
	<div class="modal fade" id="rules" tabindex="-1" role="dialog" aria-labelledby="rulesLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">规则列表</h4>
	            </div>
	            <div class="modal-body">
	            	<table id="ruleGrid"></table>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" id="ruleSave" class="btn btn-primary">保存</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<script type="text/javascript"> 
	$(document).ready(function () {
		 $("#jqGrid").jqGrid({
             mtype: "GET",
			styleUI : 'Bootstrap',
             datatype: "json",
             colModel: [
                 { label: 'operator', name: 'operator',  width: 50 ,formatter:'select', formatter:formatterSelect },
                 { label: 'expression', name: 'expression', width: 150 },
                 { label:'operation', name: 'operation', width: 150,formatter:formatterOp }
             ],
				viewrecords: true,
             height: 250,
             rowNum: 20,
             Multiselect:true
            // pager: "#jqGridPager"
         });
		 $('#rules .modal-dialog').width("800px");
		 $("#ruleGrid").jqGrid({
			 url: '${pageContext.request.contextPath}/getRuleInfo',
             mtype: "GET",
			styleUI : 'Bootstrap',
             datatype: "json",
             colNames: ['','取反','函数','',''],
             colModel: [
				 { label: 'choose', name: 'choose', width: 50, formatter:formatterChoose},
				 { label: 'not', name: 'not', width: 50, formatter:formatterNot },
                 { label: 'desc', name: 'desc', width: 150 },
                 { label: 'function', name: 'function', hidden:true },
                 { label: 'expression', name: 'expression', hidden:true }
             ],
				viewrecords: true,
             height: 250,
             rowNum: 20,
             Multiselect:true
            // pager: "#jqGridPager"
         });
	});
		$("#ruleSave").click(function(){
			var checkeds = $("input[name='choose']:checked");
			if(!!checkeds && checkeds.length > 0){
				var arr = new Array();
				checkeds.each(function(){
					var rowId = $(this).attr("rowId");
					var func = $("#ruleGrid").jqGrid("getRowData",rowId);
					var not = $("#not_"+rowId).val();
					arr.push(not+func.expression);
				});
				var expression = "["+arr.join(",")+"]";
				var data = new Object();
				console.log(expression)
				data.expression = expression;
				var rowIds = $("#jqGrid").jqGrid('getDataIDs');
		        var rowId = rowIds.length + 1;
		        $("#jqGrid").jqGrid("addRowData", rowId, data);
			}
			$("#rules").modal('hide');
		});
		function formatterOp (cellvalue, options, rowObj){
			var container = $("<div></div>");
			var deleteLeaf = $("<a href='javaScript:void(0);' onclick='deleteLeaf(\""+options.rowId+"\")'>删除</a>");
			container.append(deleteLeaf);
			return container[0].outerHTML;
		}
		function formatterChoose(cellvalue, options, rowObj){
			var container = $("<div></div>");
			var choose = $("<input rowId='"+options.rowId+"' type='checkbox' name='choose' ></input>");
			container.append(choose);
			return container[0].outerHTML;
		}
		function formatterNot(cellvalue, options, rowObj){
			var container = $("<div></div>");
			var not = $("<select id='not_"+options.rowId+"' name='not'></select>");
			not.append("<option value=''></option>");
			not.append("<option value='!'>!</option>")
			container.append(not);
			return container[0].outerHTML;
		}
		function deleteLeaf(rowId){
			 $("#jqGrid").jqGrid("delRowData",rowId);
		}
		function saveExpression(){
			var opr = null;
			var flag = true;
			$("select[name='opr']").each(function(){
				tmpOpr = $(this).val();
				if(opr == null)
					opr = tmpOpr;
				else{
					if(opr != tmpOpr){
						alert("标识符请保持一致");
						flag = false;
						return flag;
					}
				}
					
			});
			if(!flag)
				return false;
			var ids = $("#jqGrid").jqGrid('getDataIDs');
			if(!ids || ids.length <= 0){
				alert("请先选择一行");
				return false;
			}
			var array = new Array();
			for(var i = 0;i<ids.length;i++){
				var data = $("#jqGrid").jqGrid("getRowData",ids[i]);
				if(opr == "&&")
					array.push(data.expression);
				else
					array.push("["+data.expression+"]");
			}
			var expression ="[" + array.join("") + "]";
			$.ajax({
				url:"${pageContext.request.contextPath}/saveRoot",
				type:"post",
				data:{expression:expression},
				datatype:"json",
				success:function(res){
					if(res.flag){
						alert("添加成功");
						window.location.href="${pageContext.request.contextPath}/index";
					}else{
						alert("添加失败");
					}
				}
			});
		}
		
		function formatterSelect(cellvalue, options, rowObj){
			var container = $("<div></div>");
			var opr = $("<select id='opr"+options.rowId+"' name='opr'></select>");
			opr.append("<option value='||'>||</option>");
			opr.append("<option value='&&'>&&</option>")
			container.append(opr);
			return container[0].outerHTML;
		}
	</script>
</body>
</html>