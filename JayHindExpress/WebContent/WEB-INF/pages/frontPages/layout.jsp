<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="hi" http-equiv="Content-Language">
<meta name="keywords" content="${program.programName}"/>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<title><tiles:insertAttribute name="title" ignore="true" />-${program.programName}</title>
<!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<link rel="stylesheet" href="themes/Default/style.css" media="screen">
<!--[if lte IE 7]><link rel="stylesheet" href="themes/Default/style.ie7.css" media="screen" /><![endif]-->
<link rel="stylesheet" href="themes/Default/style.responsive.css" media="all">


<script src="js/jquery.js"></script>
<script src="js/script.js"></script>
<script src="js/script.responsive.js"></script>


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