<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<div class="row">
      <div class="col-lg-12 col-md-12">
        <div class="latest_newsarea"> 
        <c:if test="${configuration.breaking_news==true}">
        <span>Breaking News</span>
          <ul id="ticker01" class="news_sticker">
          	<li><a>${heading.message}</a></li>
	 		<c:forEach items="${items}" var="sample" varStatus="s">
	 		<li><a href="${sample.url}"><img src="${fn:replace(fn:replace(sample.photoURL, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}" alt="">${sample.title}</a></li>
	 		</c:forEach>
          </ul>
          </c:if>
          <c:if test="${configuration.social_links==true}">
          <div class="social_area">
            <ul class="social_nav">
              <li class="facebook"><a href="https://www.facebook.com/JAYHINDEXPRESS/"></a></li>
              <li class="twitter"><a href="https://twitter.com/jayhindexpress"></a></li>
              <li class="flickr"><a href="#"></a></li>
              <li class="pinterest"><a href="#"></a></li>
              <li class="googleplus"><a href="#"></a></li>
              <li class="vimeo"><a href="#"></a></li>
              <li class="youtube"><a href="https://www.youtube.com/channel/UC0JpoUuv8kzo3R7p5XWZb0w"></a></li>
              <li class="mail"><a href="mailto:jayhindexpress@gmail.com"></a></li>
            </ul>
          </div>
          </c:if>
        </div>
      </div>
    </div>