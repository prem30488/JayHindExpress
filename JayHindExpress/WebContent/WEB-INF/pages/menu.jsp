<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${sessionScope.user.userName.equalsIgnoreCase('admin')}">
		<ul class="art-hmenu">
		<li><a id="home" href="adminHome" style="font-size: 11px;padding: 0px 25px;">Home</a></li>
		<li><a id="international" href="viewPrograms" style="font-size: 11px;padding: 0px 25px;">International</a></li>
		<li><a id="national" href="viewNationalPrograms" style="font-size: 11px;padding: 0px 25px;">National</a></li>
		<li><a id="state" href="viewStatePrograms" style="font-size: 11px;padding: 0px 25px;">State</a></li>
		<li><a id="business" href="viewBusiness" style="font-size: 11px;padding: 0px 25px;">Business</a></li>
		<li><a id="sports" href="viewSports" style="font-size: 11px;padding: 0px 25px;">Sports</a></li>
		<li><a id="technologies" href="viewTechnologies" style="font-size: 11px;padding: 0px 25px;">Technology</a></li>
		<li><a id="astro" href="viewAstro" style="font-size: 11px;padding: 0px 25px;">Astro & Dharma</a></li>
		<li><a id="other" href="viewOtherPrograms" style="font-size: 11px;padding: 0px 25px;">Others</a></li>
		<li><a id="video" href="viewVideos" style="font-size: 11px;padding: 0px 25px;">Videos</a></li>
		<li><a id="poll" href="viewPolls" style="font-size: 11px;padding: 0px 25px;">Poll</a></li>
		<li><a id="entertainment" href="viewEntertainments" style="font-size: 11px;padding: 0px 25px;">Entertainment</a></li>
		<li><a id="fashion" href="viewFashions" style="font-size: 11px;padding: 0px 25px;">Fashion</a></li>
		<li><a id="game" href="viewGames" style="font-size: 11px;padding: 0px 25px;">Game</a></li>
		<li><a id="link" href="viewLinks" style="font-size: 11px;padding: 0px 25px;">Link</a></li>
		<li><a id="sponsor" href="viewSponsors" style="font-size: 11px;padding: 0px 25px;">Sponsor</a></li>
		<li><a id="advertisement" href="viewAdvertisements" style="font-size: 11px;padding: 0px 25px;">Advertisement</a></li>
		<li><a id="greeting" href="viewGreetings" style="font-size: 11px;padding: 0px 25px;">Greeting</a></li>
		<li><a id="photography" href="viewPhotographys" style="font-size: 11px;padding: 0px 25px;">Photography</a></li>
		<li><a id="contact" href="viewContacts" style="font-size: 11px;padding: 0px 25px;">Contacts</a></li>
		<li><a id="user" href="viewUsers" style="font-size: 11px;padding: 0px 25px;">User Management</a></li>
		<li><a id="homepage" href="viewHomePageSettings" style="font-size: 11px;padding: 0px 25px;">Home page Settings</a></li>
		<li><a id="password" href="change-password" style="font-size: 11px;padding: 0px 25px;">Change Password</a></li>
		<li><a id="logout" href="javascript:formSubmit()" style="font-size: 11px;padding: 0px 25px;">Logout</a></li>		
	</ul>
	</c:when>
	<c:otherwise>
		<ul class="art-hmenu">
			<li><a id="home" href="userHome" style="font-size: 11px;padding: 0px 25px;">Home</a></li>
			<c:if test="${sessionScope.user.international==true}">
				<li><a id="international" href="viewPrograms" style="font-size: 11px;padding: 0px 25px;">International</a></li>
			</c:if>
			<c:if test="${sessionScope.user.national==true}">
				<li><a id="national" href="viewNationalPrograms" style="font-size: 11px;padding: 0px 25px;">National</a></li>
			</c:if>
			<c:if test="${sessionScope.user.state==true}">
				<li><a id="state" href="viewStatePrograms" style="font-size: 11px;padding: 0px 25px;">State</a></li>
			</c:if>
			<c:if test="${sessionScope.user.business==true}">
				<li><a id="business" href="viewBusiness" style="font-size: 11px;padding: 0px 25px;">Business</a></li>
			</c:if>
			<c:if test="${sessionScope.user.sport==true}">
				<li><a id="sports" href="viewSports" style="font-size: 11px;padding: 0px 25px;">Sports</a></li>
			</c:if>
			<c:if test="${sessionScope.user.technology==true}">
				<li><a id="technologies" href="viewTechnologies" style="font-size: 11px;padding: 0px 25px;">Technology</a></li>
			</c:if>
			<c:if test="${sessionScope.user.astro==true}">
				<li><a id="astro" href="viewAstro" style="font-size: 11px;padding: 0px 25px;">Astro & Dharma</a></li>
			</c:if>
			<c:if test="${sessionScope.user.other==true}">
				<li><a id="other" href="viewOtherPrograms" style="font-size: 11px;padding: 0px 25px;">Others</a></li>
			</c:if>
			<c:if test="${sessionScope.user.video==true}">
				<li><a id="video" href="viewVideos" style="font-size: 11px;padding: 0px 25px;">Videos</a></li>
			</c:if>
			<c:if test="${sessionScope.user.poll==true}">
				<li><a id="poll" href="viewPolls" style="font-size: 11px;padding: 0px 25px;">Poll</a></li>
			</c:if>
			<c:if test="${sessionScope.user.entertainment==true}">
				<li><a id="entertainment" href="viewEntertainments" style="font-size: 11px;padding: 0px 25px;">Entertainment</a></li>
			</c:if>
			<c:if test="${sessionScope.user.fashion==true}">
				<li><a id="fashion" href="viewFashions" style="font-size: 11px;padding: 0px 25px;">Fashion</a></li>
			</c:if>
			<c:if test="${sessionScope.user.game==true}">
				<li><a id="game" href="viewGames" style="font-size: 11px;padding: 0px 25px;">Game</a></li>
			</c:if>
			<c:if test="${sessionScope.user.link==true}">
				<li><a id="link" href="viewLinks" style="font-size: 11px;padding: 0px 25px;">Link</a></li>
			</c:if>
			<c:if test="${sessionScope.user.sponser==true}">
				<li><a id="sponsor" href="viewSponsors" style="font-size: 11px;padding: 0px 25px;">Sponsor</a></li>
			</c:if>
			<c:if test="${sessionScope.user.advertisement==true}">
				<li><a id="advertisement" href="viewAdvertisements" style="font-size: 11px;padding: 0px 25px;">Advertisement</a></li>
			</c:if>
			<c:if test="${sessionScope.user.greeting==true}">
				<li><a id="greeting" href="viewGreetings" style="font-size: 11px;padding: 0px 25px;">Greeting</a></li>
			</c:if>
			<c:if test="${sessionScope.user.photography==true}">
				<li><a id="photography" href="viewPhotographys" style="font-size: 11px;padding: 0px 25px;">Photography</a></li>
			</c:if>
			<c:if test="${sessionScope.user.contact==true}">
				<li><a id="contact" href="viewContacts" style="font-size: 11px;padding: 0px 25px;">Contacts</a></li>
			</c:if>
			<li><a id="password" href="change-password" style="font-size: 11px;padding: 0px 25px;">Change Password</a></li>
			<li><a id="logout" href="javascript:formSubmit()" style="font-size: 11px;padding: 0px 25px;">Logout</a></li>		
		</ul>
	</c:otherwise>
</c:choose>