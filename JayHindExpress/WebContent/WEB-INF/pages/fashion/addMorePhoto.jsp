<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<script type="text/javascript" src="../js/fashion/dropzone.js"></script>
<link rel="stylesheet" type="text/css" href="../themes/Default/dropzone.css" />
</head>
<body onload="alert(document.getElementById(my-awesome-dropzone).value());">
<br/><br/>
<center>
	<h1>Add More Fashion Photos</h1>
</center>
<br/><br/>
<a href="viewFashions" style="color:black">View Fashion News</a> -> Add More Fashion Photos
<br/><br/>
<center>
<table style="width: 100%;font-size: 14px;" align="center" class="table">
		<tr id="name" class="odd" style="width:400px;height: 35px;">
			 <th class="headerRow thWidth thcenter" style="width:400px;" align="center">
				programName :
			</th>
			<td align="left">
				${photo.programName}
			</td> 
</tr>
</table>
<form:form name="douploadPhoto"  method="post" action="douploadFashionPhoto?${_csrf.parameterName}=${_csrf.token}" commandName="uploadPhoto"  enctype="multipart/form-data" class="dropzone"
      id="my-awesome-dropzone">
      <input type="hidden"  name="addPhoto"  id="addPhoto" value="${photo.folder_url}">

    </form:form>  
    <br/><br/>
</body>
