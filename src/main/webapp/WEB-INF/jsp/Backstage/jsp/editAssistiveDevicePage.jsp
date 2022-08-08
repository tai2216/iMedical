<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="default/myNavbar.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>iMedical長照醫療網編輯輔具資料頁面</title>

<link href="${contextRoot}/css/backStage.css" rel="stylesheet"
	type="text/css" />

</head>
<body>

	<div style="width: 100%; text-align: center; padding-top: 20px; padding-bottom: 30px;">
		<img alt="圖片" src="${contextRoot}/img/header3.png" style="width: 30%;">

	</div>




	<div style="border: 5px solid; margin:10px 200px 100px 200px;  border-radius: 2em; background-color: white; text-align: center">
	<h1 style=" margin-top: 20px; margin-bottom: 30px; font: 50px bold; text-align: center;">編輯輔具資料列表</h1>
	<div style="border: 4px solid; padding-bottom: 50px; margin: 10px 350px 100px 350px; border-style: groove; border-radius: 2em;  ">
	
	<div style="max-width: 100%;">
		<img style="width: 250px;" id="thumbnail" alt="LogoPreview" src="" />
	</div>
	
	<form:form action="editRealAssistiveDevice" modelAttribute="someDevice" enctype="multipart/form-data"
		method="post">

		<form:input type="hidden" path="id"
			value="<%=request.getParameter(\"id\")%>" />
		<br>


		<div class="form-group">
			<span>編輯輔具照片: </span> <input type="file" name="assistiveDevicePic"
				id="fileImage" />


		</div>

		<div class="form-group">
			<label for="assistiveDeviceName">編輯輔具名稱:</label>
			<form:input type="text" path="assistiveDeviceName" class="form-control-sm" id="assistiveDeviceName"
				aria-describedby="assistiveDeviceNameHelp" />
		</div>
		<%-- 		<form:errors path="name" cssClass="error" /> --%>
		<!-- 		<span id="name.errors" class="error">此帳號已有人使用</span> -->
		<div class="form-group">
			<label for="assistiveDeviceDescription">編輯描述:</label>
			<form:input type="text" path="assistiveDeviceDescription"
				class="form-control-sm" id="assistiveDeviceDescription"
				aria-describedby="assistiveDeviceDescriptionHelp" />
		</div>

		<form:button type="submit" class="btn btn-primary">送出</form:button>
	</form:form>
	
	</div>
	</div>


	<footer class="main-footer" style="margin-top: 100px;">
		<div class="container" style="width: 100%; text-align: center;">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.01
			</div>
			<strong>Copyright © 2022-2025 <a href="">iMedical</a>.
			</strong> All rights reserved.
		</div>

	</footer>

<script type="text/javascript">
	
	$(document).ready(function(){
		$('#fileImage').change(function(){
			showImageThumbnail(this);
		});
		
		
	});
	
	function showImageThumbnail(fileInput){
		file = fileInput.files[0];
		reader = new FileReader();
		
		reader.onload = function(e){
			$('#thumbnail').attr('src', e.target.result);
		};
		
		reader.readAsDataURL(file);
		
	}
	
	
	
	</script>





</body>
</html>