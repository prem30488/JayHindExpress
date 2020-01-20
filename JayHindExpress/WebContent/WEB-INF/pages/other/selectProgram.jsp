<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <center>
	<h1>Other News Details</h1>
	</center>
<br/><br/>
<a href="viewOtherPrograms" style="color:black">View Other News</a> -> Other News Details
<br/><br/>
<center>
		<table style="width: 100%;font-size: 14px;" align="center" class="table">
		
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Program Name
			</th>
			 <td>
				${selectProgram.programName}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Program Description
			</th>
			 <td>
				${selectProgram.description}
			</td>
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Program Created Date
			</th>
			 <td>
				<fmt:formatDate value="${selectProgram.createdDate}" pattern="dd-MMM-yyyy hh:mm:ss" />
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Program Location
			</th>
			 <td>
				${selectProgram.location}
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Posted  By
			</th>
			 <td>
				${selectProgram.posted_by}
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Program Cover Photo
			</th>
			 <td>
				<img src="../${selectProgram.photourl}" width="300" height="200"/>
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Program Slideshow Photos
			</th>
			 <td>
			 <div style="width:100%;height:100%;">
				 <c:forEach items="${fileList}" var="fileName" varStatus="status">
            	 	<img src="../${fileDir}/${fileName}" width="100" height="100">
            	 	&nbsp;
            	 	<a href='deleteInternationalFile?fileDir=${fn:replace(fileDir, '\\', '/')}/${fileName}'>Delete</a>
            	 	<br/>
           		</c:forEach>
			</div>
			</td>  
		</tr>
		</table>
</center>
		