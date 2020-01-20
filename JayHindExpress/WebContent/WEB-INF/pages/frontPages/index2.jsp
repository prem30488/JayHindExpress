<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${configuration.international==true}">
<div class="single_post_content">
            <h2><span>International</span></h2>
            <c:forEach items="${list}" var="program" varStatus="status">
            <c:if test="${status.count==1}">
            <div class="single_post_content_left">
              <ul class="business_catgnav  wow fadeInDown">
                <li style="word-wrap:break-word;">
                  <figure class="bsbig_fig"> 
                  <a href="selectProgram?id=${program.id}&albumName=${program.programName}" class="featured_img"> 
                  <img alt="" src="${program.photourl}"> <span class="overlay"></span> </a>
                    <figcaption> <a href="selectProgram?id=${program.id}&albumName=${program.programName}">
                    ${program.programName }</a> </figcaption>
                    <p>
                   <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 2000)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}</p>
                  </figure>
                </li>
              </ul>
            </div>
            </c:if>
            </c:forEach>
            <div class="single_post_content_right">
              <ul class="spost_nav">
                <c:forEach items="${list}" var="program" varStatus="status">
                <c:choose >
                	<c:when test="${status.count==1}" >
                	</c:when>
                	<c:otherwise>
                	<li>
                  <div class="media wow fadeInDown" style=""> <a href="selectProgram?id=${program.id}&albumName=${program.programName}" class="media-left"> <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
                    <div class="media-body" style="word-wrap:break-word;"> <a href="selectProgram?id=${program.id}&albumName=${program.programName}" class="catg_title"> 
                    ${program.programName}
                    <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 100)}<b>...</b>" />
					</a><br/>
					${program.niceCreatedDate}
					<br/><div style="word-wrap:break-word;width:200px">
					${program.location}
					</div>
                     </div>
                  </div>
                </li>
                
                	</c:otherwise>
                </c:choose>
                
                </c:forEach>
              </ul>
            </div>
          </div>
          </c:if>
          
          <div class="fashion_technology_area">
            <c:if test="${configuration.national==true}">
            <div class="fashion">
              <div class="single_post_content">
                <h2><span>National</span></h2>
                <c:forEach items="${nationallist}" var="program" varStatus="status">
                <ul class="business_catgnav wow fadeInDown">
                  <c:if test="${status.count==1}">
                  <li>
                    <figure class="bsbig_fig">
                    <a href="selectNationalProgram?id=${program.id}&albumName=${program.programName}" class="featured_img"> 
                    <img alt="" src="${program.photourl}"> <span class="overlay"></span> </a>
                      <figcaption> <a href="selectNationalProgram?id=${program.id}&albumName=${program.programName}">
                      ${program.programName}</a> </figcaption>
                      <p><c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 200)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}</p>
                    </figure>
                  </li>
                  </c:if>
                </ul>
                </c:forEach>
                <ul class="spost_nav">
                <c:forEach items="${nationallist}" var="program" varStatus="status">
                <c:choose >
                	<c:when test="${status.count==1}" >
                	</c:when>
                	<c:otherwise>
                	<li>
                  <div class="media wow fadeInDown" style=""> <a href="selectProgram?id=${program.id}&albumName=${program.programName}" class="media-left"> <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
                    <div class="media-body" style="word-wrap:break-word;"> <a href="selectProgram?id=${program.id}&albumName=${program.programName}" class="catg_title"> 
                    ${program.programName}
                    <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 100)}<b>...</b>" />
					</a><br/>
					${program.niceCreatedDate}
					<br/><div style="word-wrap:break-word;width:200px">
					${program.location}
					</div>
                     </div>
                  </div>
                </li>
                	</c:otherwise>
                </c:choose>
                  </c:forEach>
                </ul>
              </div>
            </div>
            </c:if>
            <c:if test="${configuration.state==true}">
            <div class="technology">
              <div class="single_post_content">
                <h2><span>State</span></h2>
                <ul class="business_catgnav">
                <c:forEach items="${statelist}" var="program" varStatus="status">
                  <c:if test="${status.count==1}">
                  <li>
                    <figure class="bsbig_fig wow fadeInDown"> <a href="selectStateProgram?id=${program.id}&albumName=${program.programName}" class="featured_img"> <img alt="" src="${program.photourl}"> <span class="overlay"></span> </a>
                      <figcaption> <a href="selectStateProgram?id=${program.id}&albumName=${program.programName}">
						${program.programName }</a> </figcaption>
                      <p>
                      <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 200)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}
                      </p>
                    </figure>
                  </li>
                  </c:if>
                  </c:forEach>
                </ul>
                <ul class="spost_nav">
                <c:forEach items="${statelist}" var="program" varStatus="status">
                <c:choose>
                	<c:when test="${status.count==1}" >
                	</c:when>
                	<c:otherwise>
                    <li>
	                    <div class="media wow fadeInDown">
	                    <a href="selectStateProgram?id=${program.id}&albumName=${program.programName}" class="media-left">
	                     <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
	                      <div class="media-body"> <a href="selectStateProgram?id=${program.id}&albumName=${program.programName}" class="catg_title"> 
	                      ${program.programName}</a> </div>
	                    </div>
	                  </li>
	                </c:otherwise>
	               </c:choose>
                </c:forEach>
                   </ul>
              </div>
            </div>
            </c:if>
          </div>

			<c:if test="${configuration.video==true}">
			<div class="single_post_content">
            <h2><span>Video Gallery</span></h2>
            
              <ul class="bxslider1">
				  <c:forEach items="${items}" var="sample" varStatus="s">
				  <c:if test="${sample.category eq 'video' }">
				  <li>
				    <iframe width="100%" height="50%" src="http://www.youtube.com/embed/${sample.video_id}?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
				  </li>
				  </c:if>
				  </c:forEach>
			  </ul>
			  <script type="text/javascript">
            	$('.bxslider1').bxSlider({
            	  video: true,
            	  useCSS: false
            	});
            </script>
			
            
          </div>
        </c:if>
		<c:if test="${configuration.business==true}">
		<div class="single_post_content">
            <h2><span>Business</span></h2>
            <div class="single_post_content_left">
              <ul class="business_catgnav  wow fadeInDown">
              <c:forEach items="${businesslist}" var="program" varStatus="status">
              <c:if test="${status.count==1}">
                <li>
                  <figure class="bsbig_fig"> <a href="selectBusinessProgram?id=${program.id}&albumName=${program.programName}" class="featured_img"> 
                  <img alt="" src="${program.photourl}"> <span class="overlay"></span> </a>
                    <figcaption> <a href="selectBusinessProgram?id=${program.id}&albumName=${program.programName}">
                    ${program.programName }</a> </figcaption>
                    <p><c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 2000)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}</p>
                  </figure>
                </li>
                </c:if>
              </c:forEach>
              </ul>
            </div>
            <div class="single_post_content_right">
              <ul class="spost_nav">
                <c:forEach items="${businesslist}" var="program" varStatus="status">
               	<c:choose>
               	<c:when test="${status.count==1}" >
                </c:when>
                <c:otherwise>
                <li>
                  <div class="media wow fadeInDown"> <a href="selectBusinessProgram?id=${program.id}&albumName=${program.programName}" class="media-left"> 
                  <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
                    <div class="media-body"> <a href="selectBusinessProgram?id=${program.id}&albumName=${program.programName}" class="catg_title">
                    ${program.programName }</a> </div>
                  </div>
                </li>
                </c:otherwise>
                </c:choose>
                </c:forEach>
              </ul>
            </div>
          </div>
          </c:if>
          
          <div class="fashion_technology_area">
            <c:if test="${configuration.sport==true}">
            <div class="fashion">
              <div class="single_post_content">
                <h2><span>Sports</span></h2>
                <ul class="business_catgnav wow fadeInDown">
                <c:forEach items="${sportslist}" var="program" varStatus="status">
                 <c:if test="${status.count==1}">
                  <li>
                    <figure class="bsbig_fig"> <a href="selectSportProgram?id=${program.id}&albumName=${program.programName}" class="featured_img"> 
                    <img alt="" src="${program.photourl}"> <span class="overlay"></span> </a>
                      <figcaption> <a href="selectSportProgram?id=${program.id}&albumName=${program.programName}">
                      ${program.programName}</a> </figcaption>
                      <p>
                      <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 200)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}
                      </p>
                    </figure>
                  </li>
                  </c:if>
                  </c:forEach>
                </ul>
                <ul class="spost_nav">
                 <c:forEach items="${sportslist}" var="program" varStatus="status">
                <c:choose>
               	<c:when test="${status.count==1}" >
                </c:when>
                <c:otherwise>
                  <li>
                    <div class="media wow fadeInDown"> <a href="selectSportProgram?id=${program.id}&albumName=${program.programName}" class="media-left"> 
                    <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
                      <div class="media-body"> <a href="selectSportProgram?id=${program.id}&albumName=${program.programName}" class="catg_title">
                      ${program.programName}</a> </div>
                    </div>
                  </li>
                </c:otherwise>
                </c:choose>
                  </c:forEach>
                </ul>
              </div>
            </div>
            </c:if>
            <c:if test="${configuration.technology==true}">
            <div class="technology">
              <div class="single_post_content">
                <h2><span>Technology</span></h2>
                <ul class="business_catgnav">
                  <c:forEach items="${technologylist}" var="program" varStatus="status">
                 <c:if test="${status.count==1}">
                  <li>
                    <figure class="bsbig_fig wow fadeInDown"> 
                    <a href="selectTechnologyProgram?id=${program.id}&albumName=${program.programName}" class="featured_img"> 
                    <img alt="" src="${program.photourl}"> <span class="overlay"></span> </a>
                      <figcaption> <a href="selectTechnologyProgram?id=${program.id}&albumName=${program.programName}">
                      ${program.programName}</a> </figcaption>
                      <p>
                      <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 200)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}
                      </p>
                    </figure>
                  </li>
                  </c:if>
                  </c:forEach>
                </ul>
                <ul class="spost_nav">
                  <c:forEach items="${technologylist}" var="program" varStatus="status">
                <c:choose>
               	<c:when test="${status.count==1}" >
                </c:when>
                <c:otherwise>
                  <li>
                    <div class="media wow fadeInDown"> <a href="selectTechnologyProgram?id=${program.id}&albumName=${program.programName}" class="media-left"> 
                    <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
                      <div class="media-body"> <a href="selectTechnologyProgram?id=${program.id}&albumName=${program.programName}" class="catg_title">
                      ${program.programName }</a> </div>
                    </div>
                  </li>
                  </c:otherwise>
                  </c:choose>
                  </c:forEach>
                </ul>
              </div>
            </div>
            </c:if>
          </div>
          <c:if test="${configuration.photography==true}">
          <div class="single_post_content">
            <h2><span>Photography</span></h2>
            <ul class="photograph_nav  wow fadeInDown">
              <c:forEach items="${photographylist}" var="program" varStatus="status">
              <li>
                <div class="photo_grid">
                  <figure class="effect-layla"> 
                  	<a class="fancybox-buttons" data-fancybox-group="button" href="${program.photourl}" title="${program.programName} <a href='selectPhotographyProgram?id=${program.id}'><font color=blue>view Details</font></a>"> 
                  	<img src="${program.photourl}" alt=""/>
                  	</a> 
                  </figure>
                </div>
              </li>
              </c:forEach>
            </ul>
          </div>
          </c:if>
          <c:if test="${configuration.entertainment==true}">
          <div class="single_post_content">
            <h2><span>Entertainment</span></h2>
            <div class="single_post_content_left">
              <ul class="business_catgnav">
              <c:forEach items="${entertainmentlist}" var="program" varStatus="status">
               <c:if test="${status.count==1}"> 
                <li>
                  <figure class="bsbig_fig  wow fadeInDown"> <a class="featured_img" href="selectEntertainmentProgram?id=${program.id}&albumName=${program.programName}"> 
                  <img src="${program.photourl}" alt=""> <span class="overlay"></span> </a>
                    <figcaption> <a href="selectEntertainmentProgram?id=${program.id}&albumName=${program.programName}">
                    ${program.programName}</a> </figcaption>
                    <p>
                      <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 200)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}
                    </p>
                  </figure>
                </li>
                </c:if>
                </c:forEach>
              </ul>
            </div>
            <div class="single_post_content_right">
              <ul class="spost_nav">
                <c:forEach items="${entertainmentlist}" var="program" varStatus="status">
                <c:choose>
               	<c:when test="${status.count==1}" >
                </c:when>
                <c:otherwise>
                <li>
                  <div class="media wow fadeInDown"> <a href="selectEntertainmentProgram?id=${program.id}&albumName=${program.programName}" class="media-left"> 
                  <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
                    <div class="media-body"> <a href="selectEntertainmentProgram?id=${program.id}&albumName=${program.programName}" class="catg_title"> 
                    ${program.programName}</a> </div>
                  </div>
                </li>
                </c:otherwise>
                </c:choose>
                </c:forEach>
              </ul>
            </div>
          </div>
          </c:if>
          
          <div class="fashion_technology_area">
            <c:if test="${configuration.fashion==true}">
            <div class="fashion">
              <div class="single_post_content">
                <h2><span>Fashion</span></h2>
                <ul class="business_catgnav wow fadeInDown">
                <c:forEach items="${fashionlist}" var="program" varStatus="status">
                 <c:if test="${status.count==1}">
                  <li>
                    <figure class="bsbig_fig"> <a href="selectFashionProgram?id=${program.id}&albumName=${program.programName}" class="featured_img"> 
                    <img alt="" src="${program.photourl}"> <span class="overlay"></span> </a>
                      <figcaption> <a href="selectFashionProgram?id=${program.id}&albumName=${program.programName}">
                      ${program.programName}</a> </figcaption>
                      <p>
                      <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 200)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}
                      </p>
                    </figure>
                  </li>
                  </c:if>
                  </c:forEach>
                </ul>
                <ul class="spost_nav">
                 <c:forEach items="${fashionlist}" var="program" varStatus="status">
                <c:choose>
               	<c:when test="${status.count==1}" >
                </c:when>
                <c:otherwise>
                  <li>
                    <div class="media wow fadeInDown"> <a href="selectFashionProgram?id=${program.id}&albumName=${program.programName}" class="media-left"> 
                    <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
                      <div class="media-body"> <a href="selectFashionProgram?id=${program.id}&albumName=${program.programName}" class="catg_title">
                      ${program.programName}</a> </div>
                    </div>
                  </li>
                </c:otherwise>
                </c:choose>
                  </c:forEach>
                </ul>
              </div>
            </div>
            </c:if>
            <c:if test="${configuration.game==true}">
            <div class="technology">
              <div class="single_post_content">
                <h2><span>Game</span></h2>
                <ul class="business_catgnav">
                  <c:forEach items="${gamelist}" var="program" varStatus="status">
                 <c:if test="${status.count==1}">
                  <li>
                    <figure class="bsbig_fig wow fadeInDown"> 
                    <a href="selectGameProgram?id=${program.id}&albumName=${program.programName}" class="featured_img"> 
                    <img alt="" src="${program.photourl}"> <span class="overlay"></span> </a>
                      <figcaption> <a href="selectGameProgram?id=${program.id}&albumName=${program.programName}">
                      ${program.programName}</a> </figcaption>
                      <p>
                      <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 200)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}
                      </p>
                    </figure>
                  </li>
                  </c:if>
                  </c:forEach>
                </ul>
                <ul class="spost_nav">
                  <c:forEach items="${gamelist}" var="program" varStatus="status">
                <c:choose>
               	<c:when test="${status.count==1}" >
                </c:when>
                <c:otherwise>
                  <li>
                    <div class="media wow fadeInDown"> <a href="selectGameProgram?id=${program.id}&albumName=${program.programName}" class="media-left"> 
                    <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
                      <div class="media-body"> <a href="selectGameProgram?id=${program.id}&albumName=${program.programName}" class="catg_title">
                      ${program.programName }</a> </div>
                    </div>
                  </li>
                  </c:otherwise>
                  </c:choose>
                  </c:forEach>
                </ul>
              </div>
            </div>
            </c:if>
          </div>
          <c:if test="${configuration.other==true}">
          <div class="single_post_content">
            <h2><span>Other</span></h2>
            <div class="single_post_content_left">
              <ul class="business_catgnav">
              <c:forEach items="${otherlist}" var="program" varStatus="status">
               <c:if test="${status.count==1}"> 
                <li>
                  <figure class="bsbig_fig  wow fadeInDown"> <a class="featured_img" href="selectOtherProgram?id=${program.id}&albumName=${program.programName}"> 
                  <img src="${program.photourl}" alt=""> <span class="overlay"></span> </a>
                    <figcaption> <a href="selectOtherProgram?id=${program.id}&albumName=${program.programName}">
                    ${program.programName}</a> </figcaption>
                    <p>
                      <c:set var="string1" value="${program.description}"/>
					<c:set var="string2" value="${fn:substring(string1, 0, 200)}<b>...</b>" />
					<b>${program.location}</b> : <fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
					<br/>
					${program.niceCreatedDate}
					<br/>
					${string2}
                    </p>
                  </figure>
                </li>
                </c:if>
                </c:forEach>
              </ul>
            </div>
            <div class="single_post_content_right">
              <ul class="spost_nav">
                <c:forEach items="${otherlist}" var="program" varStatus="status">
                <c:choose>
               	<c:when test="${status.count==1}" >
                </c:when>
                <c:otherwise>
                <li>
                  <div class="media wow fadeInDown"> <a href="selectOtherProgram?id=${program.id}&albumName=${program.programName}" class="media-left"> 
                  <img alt="" src="${fn:replace(fn:replace(program.photourl, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}"> </a>
                    <div class="media-body"> <a href="selectOtherProgram?id=${program.id}&albumName=${program.programName}" class="catg_title"> 
                    ${program.programName}</a> </div>
                  </div>
                </li>
                </c:otherwise>
                </c:choose>
                </c:forEach>
              </ul>
            </div>
          </div>
          </c:if>