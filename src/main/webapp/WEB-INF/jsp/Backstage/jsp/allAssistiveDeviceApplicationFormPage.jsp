<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="default/myNavbar.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>iMedical長照醫療網管理表單頁面</title>
<link href="${contextRoot}/css/backStage.css" rel="stylesheet"
	type="text/css" />

</head>
<body>


	<div
		style="width: 100%; text-align: center; padding-top: 0px; background-color: rgba(255, 255, 255, 0.69);">
		<img alt="圖片" src="${contextRoot}/img/header4.png" style="width: 40%;">
	</div>



	<div
		style="width: 100%; text-align: center; margin-top: 20px; margin-bottom: 30px;">
		<form action="../Backstage/searchEmployee">
			<input name="id" type="search" required="required"
				style="border-radius: 10px; width: 50%;" placeholder=""> <input
				type="submit" value="查詢"
				style="border-style: groove; border-radius: 5px;">

		</form>
	</div>





	<div
		style="border: 2px groove; width:100%; background-color:highlighttext; border-radius: 2em; padding: 30px 30px 30px 30px; text-align: center;">
		<h1>管理全部表單</h1>


		<table class="table table-hover table-bordered" style="background-color: white;">
			<tr
				style="margin: 30px 30px 30px 30px; background-color: silver;">
				<th>申請表單編號</th>
				<th>聯絡人姓名</th>
				<th>聯絡人性別</th>
				<th>聯絡人電話</th>
				<th>連絡人地址</th>
				<th>期望申請輔具</th>
			</tr>



			<c:forEach var="list" items="${page.content}">
				<tr>
					<td>${list.id}</td>
					<td>${list.name}</td>
					<td>${list.gender}</td>
					<td>${list.phone}</td>
					<td>${list.address}</td>
					<td>${list.device}</td>
					<td><a
						href="${contextRoot}/Backstage/goEditForm?id=${list.id}">編輯</a>
					</td>
					<td><a
						href="${contextRoot}/Backstage/deleteAssistiveDeviceApplicationForm?id=${list.id}"
						onclick="return confirm('確認刪除嗎?')">刪除</a></td>
				</tr>

			</c:forEach>






		</table>

		<div class="row justify-content-center" style="text-align: center;">
			<div class="col-9">
				<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
					<c:choose>
						<c:when test="${page.number!=pageNumber-1 }">
							<a
								href="${contextRoot}/Backstage/getAllAssistiveDeviceApplicationForm?p=${pageNumber}">
								<c:out value="${pageNumber}"></c:out>
							</a>
						</c:when>
						<c:otherwise>
							<c:out value="${pageNumber}"></c:out>
						</c:otherwise>


					</c:choose>
					<c:if test="${pageNumber != page.totalPages}"></c:if>

				</c:forEach>
			</div>
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






</body>
</html>