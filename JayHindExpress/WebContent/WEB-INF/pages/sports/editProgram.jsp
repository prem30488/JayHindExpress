<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<center>
	<h1>Edit Sports News Details</h1>
</center>
<br/><br/>
<script src="../js/ckeditor4.6.2/ckeditor.js"></script>
<a href="viewSports" style="color:black">View Sports News</a> -> Edit Sports News Details
<br/><br/>
<center>
	<form:form id="programForm" name="programForm" modelAttribute="program" method='POST' action="updateSport?${_csrf.parameterName}=${_csrf.token}"  enctype="multipart/form-data">
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
			<spring:bind path="createdDate">
			<tr class="trcenter" >
				
				<td colspan="3" align="center"><form:label	path="createdDate">	Created Date : </form:label>
					<fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy" />
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="programName">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="programName">
						Program Name
					</form:label>
				</th>
				<td style="width:40%;">
					<form:hidden path="id" id="id"/>
					<form:input path="programName" id="programName" maxlength="200" size="25"/>
				</td>
				<td style="width:30%;">
					<form:errors path="programName"></form:errors>
					<label for="programName" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="description">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="description">
						Description
					</form:label>
				</th>
				<td style="width:40%;">
					<form:textarea path="description" id="description" rows="10" cols="200"/>
				</td>
				<td style="width:30%;">
					<form:errors path="description"></form:errors>
					<label for="description" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="location">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="location">
						Location
					</form:label>
				</th>
				<td style="width:40%;">
					<form:textarea path="location" id="location"/>
				</td>
				<td style="width:30%;">
					<form:errors path="location"></form:errors>
					<label for="location" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="posted_by">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="posted_by">
						Posted By
					</form:label>
				</th>
				<td style="width:40%;">
					<form:textarea path="posted_by" id="posted_by"/>
				</td>
				<td style="width:30%;">
					<form:errors path="posted_by"></form:errors>
					<label for="posted_by" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<tr>
				<td colspan="3" align="center">
					<input name="submit" type="submit" value="<spring:message code="btn.program.edit" />" onclick="return validate();" class="button"/>
					<input name="reset" type="reset" value="<spring:message	code="btn.reset" />" class="button" />				
				</td>
			</tr>
		</table>
	</form:form>
</center>

<script>
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    CKEDITOR.replace( 'description' );
</script>