<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header id="header">
    <div class="row">
      <div class="col-lg-12 col-md-12 col-sm-12">
        <c:if test="${configuration.topmenu==true}">
        <div class="header_top">
          <div class="header_top_left">
            <ul class="top_nav">
              <li id="home"><a href="Home">Home</a></li>
              <li id="about"><a href="about">About</a></li>
              <c:if test="${configuration.contact==true}">
              <li id="contact"><a href="contact">Contact</a></li>
              </c:if>
            </ul>
          </div>
          <div class="header_top_right">
            <!-- <p>Friday, December 05, 2045</p> -->
            <c:set var="now" value="<%=new java.util.Date()%>" />
            <p>
			<fmt:formatDate pattern="E" 
            value="${now}" />,
            <fmt:formatDate type="both" 
            dateStyle="long" timeStyle="long" 
            value="${now}" />
            </p>
          </div>
        </div>
        </c:if>
      </div>
      <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="header_bottom">
        <c:if test="${configuration.logo==true}">
          <div class="logo_area"><a href="Home" class="logo"><img style="width:260px;height:100px;" src="images/logo.png" alt="Jay Hind Express Logo"></a>
          </div>
        </c:if>
        <c:if test="${configuration.banner==true}">
          <div class="add_banner"><a href="Home"><img src="images/addbanner_728x90_V2.jpg" alt=""></a></div>
        </c:if>
        </div>
      </div>
    </div>
  </header>
  