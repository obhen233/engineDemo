<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>engine Demo</title>
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
<div><button class="btn btn-primary" onclick="toAdd()" >添加表达式</button></div>
<div style="margin-left:20px">
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
   <script type="text/javascript"> 
        $(document).ready(function () {
			
            $("#jqGrid").jqGrid({
                url: '${pageContext.request.contextPath}/getRoots',
                mtype: "GET",
				styleUI : 'Bootstrap',
                datatype: "json",
                colModel: [
                    { label: 'unid', name: 'unid', key: true, width: 75 },
                    { label: 'expression', name: 'expression', width: 150 },
                    { label:'operation', name: 'operation', width: 150,formatter:formatterOp }
                ],
				viewrecords: true,
                height: 250,
                rowNum: 20,
                Multiselect:true
               // pager: "#jqGridPager"
            });
        });
        
        function formatterOp(cellvalue, options, rowObj){
        	var container = $("<div></div>");
        	var express = $("<a href='javaScript:void(0);' onclick='toexp(\""+rowObj.unid+"\")'>验证</a>");
        	var deleteRoot = $("<a href='javaScript:void(0);' onclick='deleteRoot(\""+rowObj.unid+"\",\""+rowObj.expression+"\")'>删除</a>");
        	container.append(express);
        	container.append("|");
        	container.append(deleteRoot);
        	return container[0].outerHTML;
        }
        function deleteRoot(unid,expression){
       		$.ajax({
       			type:"post",
       			url:"${pageContext.request.contextPath}/deleteRoot",
       			data:{unid:unid,expression:expression},
       			dataType:"json",
       			success:function(res){
       				console.log(res);
       				if(res.flag)
       					alert("success!");
       				else
       					alert("fail");
       				$("#jqGrid").trigger("reloadGrid",[]);
       			}
       			
       		});
        }
        function toAdd(){
        	window.location.href="${pageContext.request.contextPath}/toAddRoot";
        }
        function toexp(unid){
        	window.location.href="${pageContext.request.contextPath}/toexp?unid="+unid;
        }
 
   </script>
</body>
<%-- 
<script type="text/javascript">
	$(function(){
		$.ajax({
			type:"get",
			"url":"${pageContext.request.contextPath}/getRuleInfo",
			dataType:"json",
			success:function(res){
				console.log(res);
			}
		});
	});
</script>
--%>
</html>