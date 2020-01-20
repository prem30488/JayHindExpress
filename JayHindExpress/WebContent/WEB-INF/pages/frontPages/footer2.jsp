<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="footer_top">
      <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-4">
          <c:if test="${configuration.twitter==true}">
          <div class="footer_widget wow fadeInLeftBig">
            <h2>Twitter</h2>
            <!-- <a class="sideAdd" href="#"><img src="images/add_img.jpg" alt=""></a> -->
            <div style="overflow: hidden">
            	<a style="color:blue;" class="twitter-timeline" href="https://twitter.com/jayhindexpress"  data-theme="dark" data-link-color="blue" data-chrome="noheader nofooter noborders transparent"  data-related="twitterapi,twitter" data-aria-polite="assertive" width="100%" height="500" lang="EN">
            	
            	Tweets by Jay Hind Express</a>
            	<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>     	 
          	</div>
          </div>
          </c:if>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4">
          <c:if test="${configuration.tag_footer==true}">
          <div class="footer_widget wow fadeInDown">
            <h2>Tag</h2>
            <ul class="tag_nav">
              <c:if test="${configuration.international==true}">
              <li><a href="viewPrograms">International News</a></li>
              </c:if>
              <c:if test="${configuration.national==true}">
              <li><a href="viewNationalPrograms">National News</a></li>
              </c:if>
              <c:if test="${configuration.state==true}">
              <li><a href="viewStatePrograms">State News</a></li>
              </c:if>
              <c:if test="${configuration.business==true}">
              <li><a href="viewBusinessPrograms">Business News</a></li>
              </c:if>
              <c:if test="${configuration.sport==true}">
              <li><a href="viewSportPrograms">Sports News</a></li>
              </c:if>
              <c:if test="${configuration.technology==true}">
              <li><a href="viewTechnologyPrograms">Technology News</a></li>
              </c:if>
              <c:if test="${configuration.entertainment==true}">
              <li><a href="viewEntertainmentPrograms">Entertainment News</a></li>
              </c:if>
              <c:if test="${configuration.fashion==true}">
              <li><a href="viewFashionPrograms">Fashion News</a></li>
              </c:if>
              <c:if test="${configuration.game==true}">
              <li><a href="viewGamePrograms">Games News</a></li>
              </c:if>
              <c:if test="${configuration.other==true}">
              <li><a href="viewOtherPrograms">Other News</a></li>
              </c:if>
              <c:if test="${configuration.video==true}">
              <li><a href="viewVideoGallary">Video Gallery</a></li>
              </c:if>
              <c:if test="${configuration.astro==true}">
              <li><a href="viewAstroPrograms">Astro & Dharma News</a></li>
              </c:if>
              <c:if test="${configuration.photography==true}">
              <li><a href="viewPhotographyPrograms">Photography</a></li>
              </c:if>
            </ul>
          </div>
          </c:if>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4">
          <c:if test="${configuration.contact_footer==true}">
          <div class="footer_widget wow fadeInRightBig">
            <h2>Contact</h2>
            
            <address>
            340,Indira Nagar<br />
        	Sector-24,Gandhinagar-382024
        	</address>
        	
        	<p>T: + 91 98250 97353<br />
        	</p>
        	<p>W:&nbsp;<a href="http://www.jayhindexpress.com" style="color:white;">http://www.jayhindexpress.com</a><br />
        	E: &nbsp;<a href="mailto%3jayhindexpress%40%20gmail.com" style="color:white;">jayhindexpress@&nbsp;gmail.com</a></p>
            
          </div>
          </c:if>
        </div>
      </div>
    </div>
    <c:if test="${configuration.copyright_footer==true}">
    <div class="footer_bottom">
      <p class="copyright">All Rights Reserved Copyright &copy; 2017 <a href="/Home">Jay Hind Express</a>
      <br/>This site is best viewed with Internet Explorer 6.0 or higher Firefox 2.0 or higher at a minimum screen resolution of 1024x768
      </p>
      <p class="developer">Developed by Jay Hind Express</p>
    </div>
    </c:if>