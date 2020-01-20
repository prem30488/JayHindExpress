<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${configuration.advertisement_dropdown==true}">
<section>
	<div id="add">
	<a style="" id="advadd"><i class="fa fa-close"></i>close</a>
		<ul class="bxslideradd">
			  <c:forEach items="${advertisementlist}" var="program" varStatus="status">
			  <li>
			    <a class="sideAdd" href="${program.link}" target="${program.link_target}"><img src="${program.photourl}" alt=""></a>
			  </li>
			  </c:forEach>
			</ul>
            
	</div>
</section>

<script type="text/javascript">
 $('.bxslideradd').bxSlider({
 	  video: true,
 	  useCSS: false
 	});
 $('a#advadd').click(function() {
	$('div#add').toggle('slow');
 });
 </script>
</c:if>
<style type="text/css">
body#home .top_nav>li#home,
body#about .top_nav>li#about,
body#contact .top_nav>li#contact,
body#city .navbar-nav>li#city,
body#Ahmedabad .navbar-nav>li#Ahmedabad,
body#Surat .navbar-nav>li#Surat,
body#Vadodara .navbar-nav>li#Vadodara,
body#Rajkot .navbar-nav>li#Rajkot,
body#Kachcha .navbar-nav>li#Kachcha,
body#Jamanagar .navbar-nav>li#Jamanagar,
body#Valsad .navbar-nav>li#Valsad,
body#Mumbai .navbar-nav>li#Mumbai,
body#Delhi .navbar-nav>li#Delhi,
body#Chennai .navbar-nav>li#Chennai,
body#USA .navbar-nav>li#USA,
body#home .navbar-nav>li#home,
body#international .navbar-nav>li#international,
body#national .navbar-nav>li#national,
body#state .navbar-nav>li#state,
body#sports .navbar-nav>li#sports,
body#business .navbar-nav>li#business,
body#technology .navbar-nav>li#technology,
body#entertainment .navbar-nav>li#entertainment,
body#fashion .navbar-nav>li#fashion,
body#game .navbar-nav>li#game,
body#astro .navbar-nav>li#astro,
body#link .navbar-nav>li#link,
body#sponsor .navbar-nav>li#other,
body#advertisement .navbar-nav>li#other,
body#greeting .navbar-nav>li#other,
body#photography .navbar-nav>li#other,
body#other .navbar-nav>li#other,
body#videos .navbar-nav>li#videos,
body#poll .navbar-nav>li#poll,
body#contact .navbar-nav>li#contact,
body#about .navbar-nav>li#about{
    background-color: #7c8def;
}
</style>
<c:if test="${configuration.menu==true}">
<nav class="navbar navbar-inverse" role="navigation">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav main_nav">
          <li id="home"><a  href="Home"><span class="fa fa-home desktop-home"></span><span class="mobile-show">Home</span></a></li>
          <c:if test="${configuration.international==true}">
          <li id="international"><a  href="viewPrograms">International</a></li>
          </c:if>
          <c:if test="${configuration.national==true}">
          <li id="national"><a  href="viewNationalPrograms">National</a></li>
          </c:if>
          <c:if test="${configuration.state==true}">
          <li id="state"><a  href="viewStatePrograms">State</a></li>
          </c:if>
          <c:if test="${configuration.city==true}">
          <li class="dropdown" id="city"> 
          <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
          City
          </a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="viewProgramsByCity?city=Ahmedabad">Ahmedabad</a></li>
              <li><a href="viewProgramsByCity?city=Surat">Surat</a></li>
              <li><a href="viewProgramsByCity?city=Vadodara">Vadodara</a></li>
              <li><a href="viewProgramsByCity?city=Rajkot">Rajkot</a></li>
              <li><a href="viewProgramsByCity?city=Kachcha">Kachcha</a></li>
              <li><a href="viewProgramsByCity?city=Jamanagar">Jamanagar</a></li>
              <li><a href="viewProgramsByCity?city=Valsad">Valsad</a></li>
              <li><a href="viewProgramsByCity?city=Mumbai">Mumbai</a></li>
              <li><a href="viewProgramsByCity?city=Delhi">Delhi</a></li>
              <li><a href="viewProgramsByCity?city=Chennai">Chennai</a></li>
              <li><a href="viewProgramsByCity?city=USA">USA</a></li>
            </ul>
          </li>
          </c:if>
          <c:if test="${configuration.business==true}">
          <li id="business"><a  href="viewBusinessPrograms">Business</a></li>
          </c:if>
          <c:if test="${configuration.sport==true}">
          <li id="sports"><a  href="viewSportPrograms">Sports</a></li>
          </c:if>
          <c:if test="${configuration.technology==true}">
          <li id="technology"><a  href="viewTechnologyPrograms">Technology</a></li>
          </c:if>
          <c:if test="${configuration.entertainment==true}">
          <li id="entertainment"><a  href="viewEntertainmentPrograms">Entertainment</a></li>
          </c:if>
          <c:if test="${configuration.video==true}">
          <li id="videos" ><a href="viewVideoGallary">Video Gallery</a></li>
          </c:if>
          <c:if test="${configuration.astro==true}">
          <li id="astro"><a  href="viewAstroPrograms">Astro &amp; Dharma</a></li>
          </c:if>
          <c:if test="${configuration.fashion==true}">
          <li id="fashion"><a href="viewFashionPrograms">Fashion</a></li>
          </c:if>
          <c:if test="${configuration.game==true}">
          <li id="game"><a href="viewGamePrograms">Games</a></li>
          </c:if>
          <c:if test="${configuration.other==true}">
          <li class="dropdown" id="other"> 
          <a href="viewOtherPrograms" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
          Other
          </a>
            <ul class="dropdown-menu" role="menu">
              <c:if test="${configuration.photography==true}">
              <li><a href="viewPhotographyPrograms">Photography</a></li>
              </c:if>
              <c:if test="${configuration.sponser==true}">
              <li><a href="viewSponsorPrograms">Sponsor</a></li>
              </c:if>
              <c:if test="${configuration.advertisement==true}">
              <li><a href="viewAdvertisementPrograms">Advertisement</a></li>
              </c:if>
              <c:if test="${configuration.greeting==true}">
              <li><a href="viewGreetingPrograms">Greetings</a></li>
              </c:if>
              <c:if test="${configuration.other==true}">
              <li><a href="viewOtherPrograms">Other</a></li>
              </c:if>
            </ul>
          </li>
          </c:if>
        </ul>
      </div>
    </nav>
    </c:if>