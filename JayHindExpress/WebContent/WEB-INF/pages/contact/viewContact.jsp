<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script src="../js/jquery.dataTables.js"></script> 
<script type="text/javascript" src="../js/contacts/viewContact.js"></script>
<link rel="stylesheet" href="../themes/Default/datatable.css" media="all">

	<div class="art-postcontent art-postcontent-0 clearfix">
						<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 100%">
									<center><h3>View Contacts</h3></center>
								</div>
							</div>
						</div>
	
	<datatables:table id="contactstbl" url="getContactList" serverSide="true" pipelining="true" pipeSize="3" 
	row="contact" rowIdBase="id" rowIdPrefix="contact_"
	displayLength="5" lengthMenu="2,5,10,15,25,50,100"
	jqueryUI="true"
	filterable="true" sortable="true"
	processing="true" autoWidth="true" 
	pageable="true" paginationType="full_numbers" 
	stateSave="true" deferRender="true" >
	
	<datatables:column title="Id" property="id" filterable="false" sortable="false" searchable="false" visible="false" />
	<%-- <datatables:column title="Actions" renderFunction="actions" filterable="false" sortable="false" searchable="false" display="HTML" /> --%>
	<datatables:column title="Id" property="id" />
    <datatables:column title="Contact Name" property="name" />
    <datatables:column title="Email" property="email" />
    <datatables:column title="Number" property="number" />
    <datatables:column title="Message" property="message" />
    
</datatables:table>
	<br/>
	<c:url value="editContactForm" var="editUrl" />
	<form:form action="${editUrl}" method="post" id="editForm" name="editForm" modelAttribute="contactId">
		<input type="hidden" name="contactId" id="contactId" value="0"/>
	</form:form>	
	
	<c:url value="selectContactForm" var="selectUrl" />
	<form:form action="${selectUrl}" method="post" id="selectForm" name="selectForm" modelAttribute="contactIdsel">
		<input type="hidden" name="contactIdsel" id="contactIdsel" value="0"/>
	</form:form>
</div>

