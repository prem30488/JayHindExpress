<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <center>
	<h1>User Details</h1>
	</center>
<br/><br/>
<a href="viewUsers" style="color:black">View Users</a> -> User Details
<br/><br/>
<center>
		<table style="width: 100%;font-size: 14px;" align="center" class="table">
		
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				User Name
			</th>
			 <td>
				${selectProgram.userName}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Password
			</th>
			 <td>
				${selectProgram.password}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				enabled
			</th>
			 <td>
				${selectProgram.enabled}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				accountNonExpired
			</th>
			 <td>
				${selectProgram.accountNonExpired}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				accountNonLocked
			</th>
			 <td>
				${selectProgram.accountNonLocked}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				credentialsNonExpired
			</th>
			 <td>
				${selectProgram.credentialsNonExpired}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				International
			</th>
			 <td>
				${selectProgram.international}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				National
			</th>
			 <td>
				${selectProgram.national}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				State
			</th>
			 <td>
				${selectProgram.state}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Business
			</th>
			 <td>
				${selectProgram.business}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Sport
			</th>
			 <td>
				${selectProgram.sport}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Technology
			</th>
			 <td>
				${selectProgram.technology}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Astro & Dharma
			</th>
			 <td>
				${selectProgram.astro}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Other
			</th>
			 <td>
				${selectProgram.other}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Video
			</th>
			 <td>
				${selectProgram.video}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Poll
			</th>
			 <td>
				${selectProgram.poll}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Entertainment
			</th>
			 <td>
				${selectProgram.entertainment}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Fashion
			</th>
			 <td>
				${selectProgram.fashion}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Game
			</th>
			 <td>
				${selectProgram.game}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Link
			</th>
			 <td>
				${selectProgram.link}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Sponser
			</th>
			 <td>
				${selectProgram.sponser}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Advertisement
			</th>
			 <td>
				${selectProgram.advertisement}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Greeting
			</th>
			 <td>
				${selectProgram.greeting}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Photography
			</th>
			 <td>
				${selectProgram.photography}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Contact
			</th>
			 <td>
				${selectProgram.contact}
			</td> 
		</tr>
		
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Description
			</th>
			 <td>
				${selectProgram.description}
			</td>
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Created Date
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
            	 	<a href='deleteUserFile?fileDir=${fn:replace(fileDir, '\\', '/')}/${fileName}'>Delete</a>
            	 	<br/>
           		</c:forEach>
			</div>
			</td>  
		</tr>
		</table>
</center>
		