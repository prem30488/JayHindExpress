<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<br />
<div align="center" style="align: center;">
	<h1 align="center">Home Page Settings</h1>
		<div id="settings-box" align="center">
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<form:form name="configurationForm" id="configurationForm"
				action="changeHomePageSettings" method='POST' commandName="configuration">
				<table style="width:100%;height:100%">
					
						
					<spring:bind path="createdDate">
					<tr class="trcenter" >
						
						<td colspan="3" align="center"><form:label	path="createdDate">	Created Date : </form:label>
							<fmt:formatDate value="${configuration.createdDate}" pattern="dd-MMM-yyyy" />
						</td>
					</tr>
					</spring:bind>
				<spring:bind path="international">
				<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="international">
						International
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="international" id="international"/>
				</td>
				<td style="width:30%;">
					<form:errors path="international"></form:errors>
					<label for="international" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="national">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="national">
						National
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="national" id="national"/>
				</td>
				<td style="width:30%;">
					<form:errors path="national"></form:errors>
					<label for="national" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="state">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="state">
						State
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="state" id="state"/>
				</td>
				<td style="width:30%;">
					<form:errors path="state"></form:errors>
					<label for="state" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="business">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="business">
						Business
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="business" id="business"/>
				</td>
				<td style="width:30%;">
					<form:errors path="business"></form:errors>
					<label for="business" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="sport">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="sport">
						Sport
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="sport" id="sport"/>
				</td>
				<td style="width:30%;">
					<form:errors path="sport"></form:errors>
					<label for="sport" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="technology">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="technology">
						Technology
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="technology" id="technology"/>
				</td>
				<td style="width:30%;">
					<form:errors path="technology"></form:errors>
					<label for="technology" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="astro">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="astro">
						Astro & Dharma
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="astro" id="astro"/>
				</td>
				<td style="width:30%;">
					<form:errors path="astro"></form:errors>
					<label for="astro" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="other">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="other">
						Other
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="other" id="other"/>
				</td>
				<td style="width:30%;">
					<form:errors path="other"></form:errors>
					<label for="other" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="video">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="video">
						Video
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="video" id="video"/>
				</td>
				<td style="width:30%;">
					<form:errors path="video"></form:errors>
					<label for="video" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="poll">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="poll">
						Poll
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="poll" id="poll"/>
				</td>
				<td style="width:30%;">
					<form:errors path="poll"></form:errors>
					<label for="poll" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="entertainment">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="entertainment">
						Entertainment
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="entertainment" id="entertainment"/>
				</td>
				<td style="width:30%;">
					<form:errors path="entertainment"></form:errors>
					<label for="entertainment" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="fashion">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="fashion">
						Fashion
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="fashion" id="fashion"/>
				</td>
				<td style="width:30%;">
					<form:errors path="fashion"></form:errors>
					<label for="fashion" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="game">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="game">
						Game
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="game" id="game"/>
				</td>
				<td style="width:30%;">
					<form:errors path="game"></form:errors>
					<label for="game" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="link">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="link">
						Link
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="link" id="link"/>
				</td>
				<td style="width:30%;">
					<form:errors path="link"></form:errors>
					<label for="link" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="sponser">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="sponser">
						Sponser
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="sponser" id="sponser"/>
				</td>
				<td style="width:30%;">
					<form:errors path="sponser"></form:errors>
					<label for="sponser" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="advertisement">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="advertisement">
						Advertisement
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="advertisement" id="advertisement"/>
				</td>
				<td style="width:30%;">
					<form:errors path="advertisement"></form:errors>
					<label for="advertisement" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="greeting">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="greeting">
						Greeting
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="greeting" id="greeting"/>
				</td>
				<td style="width:30%;">
					<form:errors path="greeting"></form:errors>
					<label for="greeting" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="photography">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="photography">
						Photography
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="photography" id="photography"/>
				</td>
				<td style="width:30%;">
					<form:errors path="photography"></form:errors>
					<label for="photography" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="contact">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="contact">
						Contact
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="contact" id="contact"/>
				</td>
				<td style="width:30%;">
					<form:errors path="contact"></form:errors>
					<label for="contact" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			
			<spring:bind path="city">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="city">
						city Search
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="city" id="city"/>
				</td>
				<td style="width:30%;">
					<form:errors path="city"></form:errors>
					<label for="city" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			
			
			<spring:bind path="topmenu">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="topmenu">
						Top Menu
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="topmenu" id="topmenu"/>
				</td>
				<td style="width:30%;">
					<form:errors path="topmenu"></form:errors>
					<label for="topmenu" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="logo">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="logo">
						Logo
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="logo" id="logo"/>
				</td>
				<td style="width:30%;">
					<form:errors path="logo"></form:errors>
					<label for="logo" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="banner">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="banner">
						Banner
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="banner" id="banner"/>
				</td>
				<td style="width:30%;">
					<form:errors path="banner"></form:errors>
					<label for="banner" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="advertisement_dropdown">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="advertisement_dropdown">
						Advertisement Dropdown
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="advertisement_dropdown" id="advertisement_dropdown"/>
				</td>
				<td style="width:30%;">
					<form:errors path="advertisement_dropdown"></form:errors>
					<label for="advertisement_dropdown" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="menu">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="menu">
						Menu
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="menu" id="menu"/>
				</td>
				<td style="width:30%;">
					<form:errors path="menu"></form:errors>
					<label for="menu" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="breaking_news">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="breaking_news">
						Breaking news
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="breaking_news" id="breaking_news"/>
				</td>
				<td style="width:30%;">
					<form:errors path="breaking_news"></form:errors>
					<label for="breaking_news" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="social_links">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="social_links">
						Social Links
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="social_links" id="social_links"/>
				</td>
				<td style="width:30%;">
					<form:errors path="social_links"></form:errors>
					<label for="social_links" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="slider">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="slider">
						Slider
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="slider" id="slider"/>
				</td>
				<td style="width:30%;">
					<form:errors path="slider"></form:errors>
					<label for="slider" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="latest_post">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="latest_post">
						Latest Post
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="latest_post" id="latest_post"/>
				</td>
				<td style="width:30%;">
					<form:errors path="latest_post"></form:errors>
					<label for="latest_post" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="advertisement_sidebar">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="advertisement_sidebar">
						Advertisement sidebar
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="advertisement_sidebar" id="advertisement_sidebar"/>
				</td>
				<td style="width:30%;">
					<form:errors path="advertisement_sidebar"></form:errors>
					<label for="advertisement_sidebar" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="popular_post">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="popular_post">
						Popular Post
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="popular_post" id="popular_post"/>
				</td>
				<td style="width:30%;">
					<form:errors path="popular_post"></form:errors>
					<label for="popular_post" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="category_tags_tab">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="category_tags_tab">
						Category tags tab
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="category_tags_tab" id="category_tags_tab"/>
				</td>
				<td style="width:30%;">
					<form:errors path="category_tags_tab"></form:errors>
					<label for="category_tags_tab" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="video_tab">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="video_tab">
						Video tab
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="video_tab" id="video_tab"/>
				</td>
				<td style="width:30%;">
					<form:errors path="video_tab"></form:errors>
					<label for="video_tab" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="poll_tab">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="poll_tab">
						Poll tab
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="poll_tab" id="poll_tab"/>
				</td>
				<td style="width:30%;">
					<form:errors path="poll_tab"></form:errors>
					<label for="poll_tab" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="twitter">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="twitter">
						twitter
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="twitter" id="twitter"/>
				</td>
				<td style="width:30%;">
					<form:errors path="twitter"></form:errors>
					<label for="twitter" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="tag_footer">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="tag_footer">
						tag footer
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="tag_footer" id="tag_footer"/>
				</td>
				<td style="width:30%;">
					<form:errors path="tag_footer"></form:errors>
					<label for="tag_footer" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="contact_footer">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="contact_footer">
						contact footer
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="contact_footer" id="contact_footer"/>
				</td>
				<td style="width:30%;">
					<form:errors path="contact_footer"></form:errors>
					<label for="contact_footer" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			<spring:bind path="copyright_footer">
			<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
				<th class="headerRow thWidth">
					<form:label	path="copyright_footer">
						copyright footer
					</form:label>
				</th>
				<td style="width:40%;">
					<form:checkbox path="copyright_footer" id="copyright_footer"/>
				</td>
				<td style="width:30%;">
					<form:errors path="copyright_footer"></form:errors>
					<label for="copyright_footer" class="error"></label>
				</td>
			</tr>
			</spring:bind>
			
					<tr>
						<td colspan='2' align="center"><input name="submit"
							type="submit" value=" Update Settings "
							onclick="return confirm('Are you sure, you want to change settings?');" class="button" /> 
							<input	name="reset" type="reset" value="<spring:message	code="btn.reset" />" class="button" /></td>
					</tr>
				</table>
			</form:form>
		</div>
</div>