<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>表达式校验</title>
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
	
<form class="form-horizontal" id="parameter" role="form">
	
 
  
  
</form>
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" onclick="exp()" class="btn btn-default">验证</button>
       <button type="button" onclick="back()" class="btn btn-default">返回</button>
    </div>
  </div>
	
<script type="text/javascript"> 
var unid ="${unid}";
$(document).ready(function () {
	$("#parameter").empty();
	$.ajax({
		url:"${pageContext.request.contextPath}/getRootParam",
		type:"post",
		data:{unid:unid},
		dataType:"json",
		success:function(res){
			var str ='<div class="form-group" style="padding-left: 150px;">'
		    +'<label for="unid">校验参数添加</label>'
		    +'<input type="hidden" class="form-control" id="unid" name="unid" value="'+res.unid+'" >'
	  		+'</div>';
	  		var array = new Array();
	  		array.push(str);
	  		var feilds = res.feilds;
	  		for(var i = 0;i<feilds.length;i++){
	  			var feild = feilds[i];
	  			var tmp = '<div class="form-group">'
	  		   + '<label for="'+feild.simpleName+'" class="col-sm-2 control-label">'+(feild.fieldType=='base'?"基准值":(feild.fieldType=='param'?"参数":""))+':'+feild.name+'</label>'
	  		    +'<div class="col-sm-10">'
	  		     + '<input type="text" id="'+feild.simpleName+'" name="'+feild.name+'" class="form-control" style="width:10%;" placeholder="'+feild.desc+'">'
	  		    +'</div>'
	  		  +'</div>';
	  			console.log(tmp);
	  			array.push(tmp);
	  		}
	  		$("#parameter").html(array.join());
		}
	});
});
function exp(){
	var inputs = $("#parameter input");
	if(!inputs || inputs.length <= 0){
		alert("不是合法的标准规则表达式！");
		return false;
	}
	var flag = true;
	var data = new Object();
	inputs.each(function(){
		var key = $(this).attr("name");
		var value = $(this).val();
		if(!value || '' == value){
			alert(key+"值未设置,请设置！");
			flag = false;
			return false;
		}
		data[key] = value;
	});
	console.log(data);
	$.ajax({
		url:"${pageContext.request.contextPath}/doExcute",
		type:"post",
		data:data,
		dataType:"json",
		success:function(res){
			alert(res.result);
		}
	});
}
function back(){
	window.location.href="${pageContext.request.contextPath}/index";
}
</script>
</body>
</html>