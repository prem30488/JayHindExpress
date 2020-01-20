<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="../js/password/changePassword.js"></script>
<br />

<div align="center" style="align: center;width: 100%">
	<h1 align="center"><spring:message	code="btn.change.password" /></h1>
	<br/><br/>
	 <form:form name="changePasswordForm" id="changePasswordForm"
		action="changePassword" method="POST" commandName="changePassword"> 
		<table style="width: 100%">
			<tr>
				<td colspan="3" align="center">
					<c:if test="${not empty error}">
						<div class="errorMSG">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>
				</td>
			</tr>
			<spring:bind path="currentPassword">
				<tr class="<c:if test="${status.error}"> trError </c:if>">
					<td style="width: 45%" class="tdright">
						<label for="currentPassword">Current Password :</label> 
					</td>
					<td style="width: 20%">
						<form:password path="currentPassword" name="currentPassword" id="currentPassword"  maxlength="25" size="15"/>
					</td>
					<td><form:errors path="currentPassword"></form:errors> <label for="currentPassword"
							class="error"></label></td>
				</tr>
			</spring:bind>
			<spring:bind path="newPassword">
				<tr class="<c:if test="${status.error}"> trError </c:if>">
					<td style="width: 45%" class="tdright">
						<label for="newPassword">New Password : </label>
					</td>
					<td style="width: 20%">
						<form:password path="newPassword" name="newPassword" id="newPassword"  maxlength="25" size="15"/>
					</td>
					<td><form:errors path="newPassword"></form:errors> <label for="newPassword"
							class="error"></label></td>
				</tr>
			</spring:bind>
			<spring:bind path="confirmPassword">
				<tr class="<c:if test="${status.error}"> trError </c:if>">
					<td style="width: 45%" class="tdright">
						 <label for="confirmPassword">Confirm Password :</label> 
					</td>
					<td style="width: 20%">
						<form:password path="confirmPassword" name="confirmPassword" id="confirmPassword"  maxlength="25" size="15"/>
					</td>
					<td><form:errors path="confirmPassword"></form:errors> <label for="confirmPassword"
							class="error"></label></td>
				</tr>
			</spring:bind>
			<tr>
				<td colspan='3' align="center">
					<input name="submit" type="submit" value="<spring:message	code="btn.change.password" />" onclick="return validate();" class="button" />
					<input name="reset"	type="reset" value="<spring:message	code="btn.reset" />" class="button" />
				</td>
			</tr>
		</table>
	</form:form>  
</div>