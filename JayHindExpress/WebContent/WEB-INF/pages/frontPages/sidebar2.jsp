<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<link rel="stylesheet" type="text/css" href="js/jquery.bxslider/jquery.bxslider.css">
<script src="js/jquery.bxslider/jquery.min.js"></script>
<script src="js/jquery.bxslider/vendor/jquery.fitvids.js"></script>
<script src="js/jquery.bxslider/jquery.bxslider.js"></script>
  		    <c:if test="${configuration.advertisement_sidebar==true}">
            <div class="single_sidebar wow fadeInDown">
            
            <ul class="bxslideradv2">
			  <c:forEach items="${advertisementlist}" var="program" varStatus="status">
			  <li>
			    <a class="sideAdd" href="${program.link}" target="${program.link_target}"><img src="${program.photourl}" alt=""></a>
			  </li>
			  </c:forEach>
			</ul>
            
            
              	
              	<%-- <a href="selectSponsorProgram?id=${program.id}&albumName=${program.programName}" target="${program.link_target}">${program.programName} --%>
            
            <br/>
            <!-- <a href="viewSponsorPrograms" target="_self">More >></a> -->
            </div>
            <script type="text/javascript">
            $('.bxslideradv2').bxSlider({
            	  video: true,
            	  useCSS: false
            	});
            </script>
            </c:if>
            <c:if test="${configuration.popular_post==true}">
			<div class="single_sidebar">
            <h2><span>Popular Post</span></h2>
            <ul class="spost_nav">
              <c:forEach items="${flist}" var="program" varStatus="status">
              <c:if test="${status.count<10}" >
              <li style="border-bottom: 1px solid #ddd;">
                <div class="media wow fadeInDown"  style="border-bottom: 0px solid #ddd;">
                <a href="${program.url}" class="media-left">
                
                <c:choose>
                	<c:when test="${program.category eq 'video' && configuration.video==true}">
                		<iframe width="100%" height="100%" src="http://www.youtube.com/embed/${program.video_id}?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
                	</c:when>
                	<c:otherwise>
                	<img alt="" src="${fn:replace(fn:replace(program.photoURL, '0.jpeg', '0_icon.jpeg'),'0.jpg','0_icon.jpg')}">
                	</c:otherwise>
                </c:choose>
                 </a>
                  <div class="media-body">
                  <a href="${program.url}" class="catg_title">
                  ${program.title}(${program.frequency})</a> </div>
                </div>
              </li>
              </c:if>
              </c:forEach>
            </ul>
          </div>
          </c:if>
          <div class="single_sidebar">
            <ul class="nav nav-tabs" role="tablist">
              <c:if test="${configuration.category_tags_tab==true}">
              <li role="presentation" class="active"><a href="#category" aria-controls="home" role="tab" data-toggle="tab">Category</a></li>
              </c:if>
              <c:if test="${configuration.video_tab==true}">
              <li role="presentation" <c:if test="${configuration.category_tags_tab==false}">class="active"</c:if>><a href="#video" aria-controls="profile" role="tab" data-toggle="tab">Video</a></li>
              </c:if>
              <c:if test="${configuration.poll_tab==true}">
              <li role="presentation" <c:if test="${configuration.category_tags_tab==false && configuration.video_tab==false}">class="active"</c:if>><a href="#comments" aria-controls="messages" role="tab" data-toggle="tab">Poll</a></li>
              </c:if>
            </ul>
            <div class="tab-content">
              <c:if test="${configuration.category_tags_tab==true}">
              <div role="tabpanel" class="tab-pane active" id="category">
                <ul>
                  <c:if test="${configuration.international==true}">
                  <li class="cat-item"><a href="viewPrograms">International</a></li>
                  </c:if>
                  <c:if test="${configuration.national==true}">
                  <li class="cat-item"><a href="viewNationalPrograms">National</a></li>
                  </c:if>
                  <c:if test="${configuration.state==true}">
                  <li class="cat-item"><a href="viewStatePrograms">State</a></li>
                  </c:if>
                  <c:if test="${configuration.business==true}">
                  <li class="cat-item"><a href="viewBusinessPrograms">Business</a></li>
                  </c:if>
                  <c:if test="${configuration.sport==true}">
                  <li class="cat-item"><a href="viewSportPrograms">Sports</a></li>
                  </c:if>
                  <c:if test="${configuration.technology==true}">
                  <li class="cat-item"><a href="viewTechnologyPrograms">Technology</a></li>
                  </c:if>
                  <c:if test="${configuration.entertainment==true}">
                  <li class="cat-item"><a href="viewEntertainmentPrograms">Entertainment</a></li>
                  </c:if>
                  <c:if test="${configuration.fashion==true}">
                  <li class="cat-item"><a href="viewFashionPrograms">Fashion</a></li>
                  </c:if>
                  <c:if test="${configuration.game==true}">
                  <li class="cat-item"><a href="viewGamePrograms">Game</a></li>
                  </c:if>
                  <c:if test="${configuration.video==true}">
                  <li class="cat-item"><a href="viewVideoGallary">Video</a></li>
                  </c:if>
                  <c:if test="${configuration.astro==true}">
                  <li class="cat-item"><a href="viewAstroPrograms">Astro &amp; Dharma</a></li>
                  </c:if>
                  <c:if test="${configuration.photography==true}">
                  <li class="cat-item"><a href="viewPhorographyPrograms">Photography</a></li>
                  </c:if>
                  <c:if test="${configuration.other==true}">
                  <li class="cat-item"><a href="viewOtherPrograms">Other</a></li>
                  </c:if>
                </ul>
              </div>
              </c:if>
              <c:if test="${configuration.video_tab==true}">
              <div role="tabpanel" class="tab-pane <c:if test="${configuration.category_tags_tab==false}">active</c:if>" id="video">
                <div class="vide_area">
                <c:forEach items="${vlist}" var="video" varStatus="s">
                  <iframe width="100%" height="250" src="http://www.youtube.com/embed/${video.video_id}?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
                  </c:forEach>
                </div>
              </div>
              </c:if>
              <c:if test="${configuration.poll_tab==true}">
              <div role="tabpanel" class="tab-pane <c:if test="${configuration.category_tags_tab==false && configuration.video_tab==false}">active</c:if>" id="comments">
                <ul class="spost_nav">
                <li>
                    <div class="media wow fadeInDown"> 
                    
                	<form action="pollsubmit" >
						<b>${poll.programName}</b>
						<input type="hidden" name="id" value="${poll.id}"/>
						<p><input type="radio" name="vote" value="yes">Yes</p>
							<p><input type="radio" name="vote" value="no">No</p>
							<p><input type="radio" name="vote" value="none">Don't know</p>	
							<input type="submit" value="Submit" onclick="return validate();">
					</form>
										<script type="text/javascript">
										function validate(){
											if($("input[type=radio]:checked").length > 0) {
											    // Do your stuff here
											}else
											{
												alert("Please Select Vote");
												return false;
											}
											return true;
										}
										</script>
										
								</div>
								</li>
                </ul>
              </div>
              </c:if>
            </div>
          </div>
          <c:if test="${configuration.sponser==true}">
          <div class="single_sidebar wow fadeInDown">
            <h2><span>Sponsor</span></h2>
            <!-- <a class="sideAdd" href="#"><img src="images/add_img.jpg" alt=""></a> </div> -->
            <%-- <c:forEach items="${sponsorlist}" var="program" varStatus="status">
              	<a class="sideAdd" href="${program.link}" target="${program.link_target}"><img src="${program.photourl}" alt=""></a>
              	<center><a style="color:blue" href="selectSponsorProgram?id=${program.id}&albumName=${program.programName}" target="${program.link_target}">${program.programName}</a></span>
              	</center>
              	<br/><br/>
            </c:forEach>
             --%>
            <ul class="bxslider2">
			  <c:forEach items="${sponsorlist}" var="program" varStatus="status">
			  <li>
			    <a class="sideAdd" href="${program.link}" target="${program.link_target}"><img src="${program.photourl}" alt=""></a>
			  </li>
			  </c:forEach>
			</ul>
            <script type="text/javascript">
            $('.bxslider2').bxSlider({
            	  video: true,
            	  useCSS: false
            	});
            </script>
            
            <a style="color:blue" href="viewSponsorPrograms" target="_self">view More >></a>
            </div>
           </c:if>
           <c:if test="${configuration.link==true}">
          <div class="single_sidebar wow fadeInDown">
            <h2><span>Links</span></h2>
            <ul>
              <c:forEach items="${linklist}" var="program" varStatus="status">
              		<li><a href="${program.link}" target="${program.link_target}">${program.programName}</a></li>
              </c:forEach>
              <li><a href="viewLinkPrograms" target="_self">More >></a></li>
            </ul>
          </div>
          </c:if>
          <c:if test="${configuration.greeting==true}">
          <div class="single_sidebar wow fadeInDown">
            <h2><span>Greetings</span></h2>
            <!-- <a class="sideAdd" href="#"><img src="images/add_img.jpg" alt=""></a> </div> -->
            <%-- <c:forEach items="${greetinglist}" var="program" varStatus="status">
              	<a class="sideAdd" href="${program.link}" target="${program.link_target}"><img src="${program.photourl}" alt=""></a>
              	<center><a style="color:blue" href="selectGreetingProgram?id=${program.id}&albumName=${program.programName}" target="${program.link_target}">${program.programName}</a>
              	</center>
              	<br/><br/>
            </c:forEach> --%>
            
            <ul class="bxslider3">
			  <c:forEach items="${greetinglist}" var="program" varStatus="status">
			  <li>
			    <a class="sideAdd" href="${program.link}" target="${program.link_target}"><img src="${program.photourl}" alt=""></a>
			  </li>
			  </c:forEach>
			</ul>
            <script type="text/javascript">
            $('.bxslider3').bxSlider({
            	  video: true,
            	  useCSS: false
            	});
            </script>
            
            <a style="color:blue" href="viewGreetingPrograms" target="_self">view More >></a>
            </div>
        	</c:if>
          <!-- <div class="single_sidebar wow fadeInDown">
            <h2><span>Category Archive</span></h2>
            <select class="catgArchive">
              <option>Select Category</option>
              <option>Life styles</option>
              <option>Sports</option>
              <option>Technology</option>
              <option>Treads</option>
            </select>
          </div> -->
          