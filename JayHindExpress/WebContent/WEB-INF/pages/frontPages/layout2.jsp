<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content="hi" http-equiv="Content-Language">
<c:catch var ="catchException">
<c:choose>
	<c:when test="${not empty e && not empty program}">
		<meta name="keywords" content="${program.programName}" />
		<title><tiles:insertAttribute name="title" ignore="true" />-${program.programName}</title>
	</c:when>
	<c:when test="${not empty v && not empty program && not empty program.videoName}">
		<meta name="keywords" content="${program.videoName}" />
		<title><tiles:insertAttribute name="title" ignore="true" />-${program.videoName}</title>
	</c:when>
</c:choose>
</c:catch>
<c:if test = "${catchException != null}">
	<meta name="keywords" content="jay,hind,express,homepage,jayhindexpress.com" />
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
</c:if>
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/animate.css">
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<link rel="stylesheet" type="text/css" href="assets/css/li-scroller.css">
<link rel="stylesheet" type="text/css" href="assets/css/slick.css">
<link rel="stylesheet" type="text/css" href="assets/css/jquery.fancybox.css">
<link rel="stylesheet" type="text/css" href="assets/css/theme.css">
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<!--[if lt IE 9]>
<script src="assets/js/html5shiv.min.js"></script>
<script src="assets/js/respond.min.js"></script>
<![endif]-->

<link rel="stylesheet" type="text/css" href="js/jquery.bxslider/jquery.bxslider.css">
<script src="js/jquery.bxslider/jquery.min.js"></script>
<script src="js/jquery.bxslider/vendor/jquery.fitvids.js"></script>
<script src="js/jquery.bxslider/jquery.bxslider.js"></script>
       
</head>
<body id="<tiles:insertAttribute name="menuselection" ignore="true" />">
<div id="preloader">
  <div id="status">&nbsp;</div>
</div>
<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
<div class="container">
<tiles:insertAttribute name="header" />
  <section id="navArea">
   <tiles:insertAttribute name="menu" /> 
  </section>
  <section id="newsSection">
   <tiles:insertAttribute name="breaking" />  
  </section>
  <section id="sliderSection">
   <tiles:insertAttribute name="slider" /> 
  </section>
  <section id="contentSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="left_content">
          <tiles:insertAttribute name="body" />
        </div>
      </div>
      <div class="col-lg-4 col-md-4 col-sm-4">
        <aside class="right_content">
          <tiles:insertAttribute name="sidebar" />
        </aside>
      </div>
    </div>
  </section>
  <footer id="footer">
    <tiles:insertAttribute name="footer" />
  </footer>
</div>
<script src="assets/js/jquery.min.js"></script> 
<script src="assets/js/wow.min.js"></script> 
<script src="assets/js/bootstrap.min.js"></script> 
<script src="assets/js/slick.min.js"></script> 
<script src="assets/js/jquery.li-scroller.1.0.js"></script> 
<script src="assets/js/jquery.newsTicker.min.js"></script> 
<script src="assets/js/jquery.fancybox.pack.js"></script> 
<script src="assets/js/custom.js"></script>
</body>

</html>