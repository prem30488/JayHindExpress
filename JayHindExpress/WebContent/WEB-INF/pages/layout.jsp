<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) {
		response.sendRedirect("../login");
		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html dir="ltr" lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta content="gu" http-equiv="Content-Language">
<meta http-equiv="Last-Modified"
	content="Friday, November 06, 2015  11:27:08 AM">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="" name="viewport">

<meta charset="utf-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<meta name="viewport"
	content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

<!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<link rel="stylesheet" href="../themes/Default/style.css" media="screen">
<!--[if lte IE 7]><link rel="stylesheet" href="themes/Default/style.ie7.css" media="screen" /><![endif]-->
<link rel="stylesheet" href="../themes/Default/style.responsive.css" media="all">


 <script src="../js/jquery/jquery.js"></script>
<script src="../js/script.js"></script>
<script src="../js/script.responsive.js"></script>


<c:url value="../logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<script>
				function formSubmit() {
					document.getElementById("logoutForm").submit();
				}
			</script>
<dandelion:asset cssExcludes="datatables"/>
	<dandelion:asset jsExcludes="datatables"/>
	<dandelion:asset jsExcludes="jquery"/>
	<script src="../js/jquery/jquery.js"></script> 
	<script src="../js/jquery.dataTables.js"></script>
<style>
.art-content .art-postcontent-0 .layout-item-0 {
	padding-right: 10px;
	padding-left: 10px;
}

.ie7 .art-post .art-layout-cell {
	border: none !important;
	padding: 0 !important;
}

.ie6 .art-post .art-layout-cell {
	border: none !important;
	padding: 0 !important;
}
</style>

</head>
<body id="<tiles:insertAttribute name="menuselection" ignore="true" />">
	<div id="art-main">
		<div id="art-hmenu-bg" class="art-bar art-nav"></div>
		<div class="art-sheet clearfix">
			<nav class="art-nav">
			<div class="art-nav-inner">
			<tiles:insertAttribute name="menu" />
				
			</div>
			</nav>
		
			<tiles:insertAttribute name="header" />
		
		<tiles:insertAttribute name="body" />
		
			
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>