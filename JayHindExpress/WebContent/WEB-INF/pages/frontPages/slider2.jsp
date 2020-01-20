<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	
      <div class="col-lg-8 col-md-8 col-sm-8">
      <c:if test="${configuration.slider==true}">
        <div class="slick_slider">
        
      <c:forEach items="${list}" var="program" varStatus="status">
          <div class="single_iteam"> <a href="selectProgram?id=${program.id}&albumName=${program.programName}"> 
          <img src="${program.photourl}" alt=""></a>
            <div class="slider_article">
              <h2><a class="slider_tittle" href="selectProgram?id=${program.id}&albumName=${program.programName}">
              ${program.programName}(${program.frequency})</a></h2>
              <p><b>${program.location} :</b>
              <c:set var="string1" value="${program.description}"/>
			  <c:set var="string2" value="${fn:substring(string1, 0, 100)}" />
			  <fmt:formatDate value="${p.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
			  ${program.niceCreatedDate} :
     			<br/>
			  ${string2}...</p>
            </div>
          </div>
    </c:forEach>
        </div>
        </c:if>
      </div>
      
      
      <div class="col-lg-4 col-md-4 col-sm-4">
        <c:if test="${configuration.latest_post==true}">
        <div class="latest_post">
          <h2><span>Latest post</span></h2>
          <div class="latest_post_container">
            <div id="prev-button"><i class="fa fa-chevron-up"></i></div>
            <ul class="latest_postnav">
              
            <c:forEach items="${items}" var="program" varStatus="status">  
              <li>
                <div class="media"> <a href="${program.url}" class="media-left"> 
                	<c:choose>
                	<c:when test="${program.category eq 'video' }">
                		<iframe width="80px" height="56px" src="http://www.youtube.com/embed/${program.video_id}?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
                	</c:when>
                	<c:otherwise>
                		<img alt="" src="${fn:replace(fn:replace(program.photoURL, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> 
                	</c:otherwise>
                	</c:choose>	
                		</a>
                  <div class="media-body"> <a href="${program.url}" class="catg_title">
                   ${program.title }</a> </div>
                </div>
              </li>
            </c:forEach>
            </ul>
            <div id="next-button"><i class="fa  fa-chevron-down"></i></div>
          </div>
        </div>
        </c:if>
      </div>
    </div>