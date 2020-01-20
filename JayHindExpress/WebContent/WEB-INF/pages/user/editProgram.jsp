<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<center>
	<h1>Edit User Details</h1>
</center>
<br/><br/>
<script src="../js/ckeditor4.6.2/ckeditor.js"></script>
<a href="viewUsers" style="color:black">View User</a> -> Edit User Details
<br/><br/>
<center>
	<form:form id="programForm" name="programForm" modelAttribute="program" method='POST' action="updateUser?${_csrf.parameterName}=${_csrf.token}"  enctype="multipart/form-data">
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
			<spring:bind path="userName">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="userName">
						User Name
					</form:label>
				</th>
				<td style="width:40%;">
					<form:input path="userName" id="userName" maxlength="200" size="25"/>
					<form:hidden path="userId"/>
				</td>
				<td style="width:30%;">
					<form:errors path="userName"></form:errors>
					<label for="userName" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="password">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="password">
						password
					</form:label>
				</th>
				<td style="width:40%;">
					<form:input path="password" id="password" maxlength="200" size="25"/>
				</td>
				<td style="width:30%;">
					<form:errors path="password"></form:errors>
					<label for="password" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="enabled">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="enabled">
						enabled
					</form:label>
				</th>
				<td style="width:40%;">
					<form:input path="enabled" id="enabled" maxlength="200" size="25"/>
				</td>
				<td style="width:30%;">
					<form:errors path="enabled"></form:errors>
					<label for="enabled" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			
			<spring:bind path="accountNonExpired">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="accountNonExpired">
						accountNonExpired
					</form:label>
				</th>
				<td style="width:40%;">
					<form:input path="accountNonExpired" id="accountNonExpired" maxlength="200" size="25"/>
				</td>
				<td style="width:30%;">
					<form:errors path="accountNonExpired"></form:errors>
					<label for="accountNonExpired" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			
			<spring:bind path="accountNonLocked">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="accountNonLocked">
						accountNonLocked
					</form:label>
				</th>
				<td style="width:40%;">
					<form:input path="accountNonLocked" id="accountNonLocked" maxlength="200" size="25"/>
				</td>
				<td style="width:30%;">
					<form:errors path="accountNonLocked"></form:errors>
					<label for="accountNonLocked" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			
			
			<spring:bind path="credentialsNonExpired">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="credentialsNonExpired">
						credentialsNonExpired
					</form:label>
				</th>
				<td style="width:40%;">
					<form:input path="credentialsNonExpired" id="credentialsNonExpired" maxlength="200" size="25"/>
				</td>
				<td style="width:30%;">
					<form:errors path="credentialsNonExpired"></form:errors>
					<label for="credentialsNonExpired" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			
			<spring:bind path="international">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="international">
						International
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="international" id="international"/>
				</td>
				<td style="width:30%;">
					<form:errors path="international"></form:errors>
					<label for="international" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="national">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="national">
						National
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="national" id="national"/>
				</td>
				<td style="width:30%;">
					<form:errors path="national"></form:errors>
					<label for="national" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="state">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="state">
						State
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="state" id="state"/>
				</td>
				<td style="width:30%;">
					<form:errors path="state"></form:errors>
					<label for="state" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="business">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="business">
						Business
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="business" id="business"/>
				</td>
				<td style="width:30%;">
					<form:errors path="business"></form:errors>
					<label for="business" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="sport">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="sport">
						Sport
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="sport" id="sport"/>
				</td>
				<td style="width:30%;">
					<form:errors path="sport"></form:errors>
					<label for="sport" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="technology">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="technology">
						Technology
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="technology" id="technology"/>
				</td>
				<td style="width:30%;">
					<form:errors path="technology"></form:errors>
					<label for="technology" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="astro">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="astro">
						Astro & Dharma
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="astro" id="astro"/>
				</td>
				<td style="width:30%;">
					<form:errors path="astro"></form:errors>
					<label for="astro" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="other">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="other">
						Other
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="other" id="other"/>
				</td>
				<td style="width:30%;">
					<form:errors path="other"></form:errors>
					<label for="other" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="video">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="video">
						Video
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="video" id="video"/>
				</td>
				<td style="width:30%;">
					<form:errors path="video"></form:errors>
					<label for="video" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="poll">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="poll">
						Poll
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="poll" id="poll"/>
				</td>
				<td style="width:30%;">
					<form:errors path="poll"></form:errors>
					<label for="poll" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="entertainment">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="entertainment">
						Entertainment
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="entertainment" id="entertainment"/>
				</td>
				<td style="width:30%;">
					<form:errors path="entertainment"></form:errors>
					<label for="entertainment" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="fashion">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="fashion">
						Fashion
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="fashion" id="fashion"/>
				</td>
				<td style="width:30%;">
					<form:errors path="fashion"></form:errors>
					<label for="fashion" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="game">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="game">
						Game
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="game" id="game"/>
				</td>
				<td style="width:30%;">
					<form:errors path="game"></form:errors>
					<label for="game" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="link">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="link">
						Link
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="link" id="link"/>
				</td>
				<td style="width:30%;">
					<form:errors path="link"></form:errors>
					<label for="link" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="sponser">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="sponser">
						Sponser
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="sponser" id="sponser"/>
				</td>
				<td style="width:30%;">
					<form:errors path="sponser"></form:errors>
					<label for="sponser" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="advertisement">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="advertisement">
						Advertisement
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="advertisement" id="advertisement"/>
				</td>
				<td style="width:30%;">
					<form:errors path="advertisement"></form:errors>
					<label for="advertisement" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="greeting">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="greeting">
						Greeting
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="greeting" id="greeting"/>
				</td>
				<td style="width:30%;">
					<form:errors path="greeting"></form:errors>
					<label for="greeting" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="photography">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="photography">
						Photography
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="photography" id="photography"/>
				</td>
				<td style="width:30%;">
					<form:errors path="photography"></form:errors>
					<label for="photography" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="contact">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="contact">
						Contact
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="contact" id="contact"/>
				</td>
				<td style="width:30%;">
					<form:errors path="contact"></form:errors>
					<label for="contact" class="error"></label>
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
	
	
<script>
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    CKEDITOR.replace( 'description' );
</script>
</center>