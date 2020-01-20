<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="../js/jquery.dataTables.js"></script> 
<script type="text/javascript" src="../js/user/viewProgram.js"></script>
<link rel="stylesheet" href="../themes/Default/datatable.css" media="all" />
<div class="art-postcontent art-postcontent-0 clearfix">
						<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 100%">

<center>
	<h1>View Users</h1>
	
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
	</table>
	
	
	<datatables:table id="programstbl" url="getUserList" serverSide="true" pipelining="true" pipeSize="3" 
	row="program" rowIdBase="id" rowIdPrefix="program_"
	displayLength="5" lengthMenu="2,5,10,15,25,50,100"
	jqueryUI="true"
	filterable="true" sortable="true"
	processing="true" autoWidth="true" 
	pageable="true" paginationType="full_numbers" 
	stateSave="true" deferRender="true" >
	
	<datatables:column title="Id" property="userId" filterable="false" sortable="false" searchable="false" visible="false" />
	<datatables:column title="Actions" renderFunction="actions" filterable="false" sortable="false" searchable="false" display="HTML" />
	<datatables:column title="Id" property="userId" />
    <datatables:column title="User Name" property="userName" />
    <datatables:column title="Location" property="location" />
    <datatables:column title="Posted by" property="posted_by" />
    <datatables:column title="Account Expired" property="accountNonExpired" />
</datatables:table>
	<br/>
	<div style="float: right;">
		<form name="addDepartment" id="addDepartment" action="addUserForm" method='get'>
			<table>
				<tr>
					<td colspan='3' align="right">
						<input name="submit" type="submit" value="<spring:message code="btn.add" />"  class="button"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<c:url value="editUserForm" var="editUrl" />
	<form:form action="${editUrl}" method="post" id="editForm" name="editForm" modelAttribute="programId">
		<input type="hidden" name="programId" id="programId" value="0"/>
	</form:form>	
	
	<c:url value="selectUserForm" var="selectUrl" />
	<form:form action="${selectUrl}" method="post" id="selectForm" name="selectForm" modelAttribute="programIdsel">
		<input type="hidden" name="programIdsel" id="programIdsel" value="0"/>
	</form:form>
	
	<c:url value="moreUserPhotos" var="addPhotoUrl" />
	<form:form action="${addPhotoUrl}" method="post" id="addPhotoForm" name="addPhotoForm" modelAttribute="photoId">
		<input type="hidden" name="photoId" id="photoId" value="0"/>
	</form:form>
</center>
</div>
</div>
</div>
</div>