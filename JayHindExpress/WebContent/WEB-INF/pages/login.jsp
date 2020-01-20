<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<%@page session="true"%>
<html>
<head>
<title><spring:message code="myapp.name" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link rel="stylesheet" href="<spring:theme code="css"/>" type="text/css" /> --%>
<link rel="stylesheet" href="themes/Default/style.css" type="text/css" />
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
<!-- <script src='https://www.google.com/recaptcha/api.js'></script> -->

</head>
<body onload='document.loginForm.username.focus();'>
	<CENTER>
	<h1><spring:message code="myapp.name" /></h1>
	<a href="Home" style="color:black;">Back To Home Page </a>
	<div id="login-box">

		<h3><spring:message	code="login.logindetails" /></h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
			action="<c:url value='/auth/login_check?targetUrl=${targetUrl}' />"
			method='POST'>
			<table>
				<tr>
					<td><label>
							<strong>
								<spring:message	code="login.username" />:
							</strong>
						</label>
					</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>
						<label>
							<strong>
								<spring:message	code="login.password" />:
							</strong>
						</label>
					</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan="2">
				<!-- 	<div class="g-recaptcha" data-sitekey="6Lf4eBgUAAAAAJNrfcjKukRn9wbdPw7dkY7tmg7u"></div> -->
					<%
			         ReCaptcha c = ReCaptchaFactory.newReCaptcha("6Ld0HBoUAAAAAE-jbNV4kQwcdMYmYVzx4izR6Y1B", "6Ld0HBoUAAAAAArZ0ydekDD7n6WBs7IFPtXKgCvJ", false);
			         out.print(c.createRecaptchaHtml(null, null));
        			%>
					</td>
				</tr>
				<!-- if this is login for update, ignore remember me check -->
				<%-- <c:if test="${empty loginUpdate}">
					<tr>
						<td colspan="2"><spring:message	code="login.rememberme" />: <input type="checkbox" name="remember-me" /></td>
					</tr>
				</c:if> --%>

				<tr>
					<td colspan='2'>
						<input name="submit" type="submit" value="<spring:message	code="btn.login" />" />
						<input name="reset" type="reset" value="<spring:message	code="btn.reset" />" />
					</td>
				</tr>

			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>

	</div>
	</CENTER>
</body>
</html>