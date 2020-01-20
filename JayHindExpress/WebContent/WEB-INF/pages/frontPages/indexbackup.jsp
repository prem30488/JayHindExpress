<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="art-layout-wrapper">
	<div class="art-content-layout">
		<div class="art-content-layout-row">
			<div class="art-layout-cell art-content">
				<article class="art-post art-article">
					<div class="art-postcontent art-postcontent-0 clearfix">
						<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 100%">
									<p style="border-radius: 4px 4px 0 0; border: 1px solid #ECB14B;padding: 8px">
									Breaking News :
										<marquee><span style="font-weight: bold;font-color:red;">${heading.message}</span></marquee>
									</p>

									<h3>International</h3>
								</div>
							</div>
						</div>
						<c:forEach items="${list}" var="program" varStatus="status">
						<c:if test="${status.count==1}">
						<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 50%;word-wrap:break-word;">
									<br /> <a href="#" onclick="openchild('slideshow?albumName=${program.programName}','300px','600px;','300','200')"><img src="${program.photourl}" alt="" class="img-indent-2" width="300" height="200"></a>
									
								</div>
								<div class="art-layout-cell layout-item-0" style="width: 50%;word-wrap:break-word;">
									<c:forEach items="${list}" var="p" varStatus="status">
									<p>
									
										<span style="font-weight: bold;"><a href="selectProgram?id=${p.id}&albumName=${p.programName}">${p.programName}(${p.frequency})</a></span>&nbsp;<br/>
										
										<br/><b>${p.location} :</b><c:set var="string1" value="${p.description}"/>
											<c:set var="string2" value="${fn:substring(string1, 0, 200)}" />
											<fmt:formatDate value="${p.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/>
											${string2}
									</p>
								</c:forEach>
							
								</div>
								
								<div class="art-layout-cell layout-item-1" style="width: 50%;word-wrap:break-word;">
								<h4>Most Viewed Posts</h4>
									<c:forEach items="${flist}" var="po" varStatus="status2">
									<p>
									
										<span style="font-weight: bold;"><a href="selectProgram?id=${po.id}&albumName=${po.programName}">${po.programName}(${po.frequency})</a></span>&nbsp;<br/>
										
										<br/><b>${po.location} :</b><c:set var="string11" value="${po.description}"/>
											<c:set var="string22" value="${fn:substring(string11, 0, 200)}" />
											<fmt:formatDate value="${po.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/>
											${string22}
									</p>
								</c:forEach>
							
								</div>
								
								
							</div>
						</div>
						</c:if>
						
						</c:forEach>
						<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 50%;word-wrap:break-word;">
									<h3>National</h3>
									<c:forEach items="${nationallist}" var="program" varStatus="status">
									<ul>
										<li><span style="color: rgb(53, 52, 19);"><span
												style="font-weight: bold;"><a href="selectNationalProgram?id=${program.id}&albumName=${program.programName}">${program.programName}</a></span>&nbsp;<br/></span><br /> 
												<b>${program.location} :</b>
												
												<fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/>
												
												<c:set var="string1" value="${program.description}"/>
											<c:set var="string2" value="${fn:substring(string1, 0, 200)}" />
											
											${string2}
												<br /> <br /></span></li>
									</ul>
									</c:forEach>
								</div>
								<div class="art-layout-cell layout-item-0" style="width: 50%;word-wrap:break-word;">
									<h3>State</h3>
									<c:forEach items="${statelist}" var="program" varStatus="status">
									<ul>
										<li><span style="color: rgb(53, 52, 19);"><span
												style="font-weight: bold;"><a href="selectStateProgram?id=${program.id}&albumName=${program.programName}">${program.programName}</a></span>&nbsp;<br/></span><br /> 
												<b>${program.location} :</b><fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/><c:set var="string1" value="${program.description}"/>
											<c:set var="string2" value="${fn:substring(string1, 0, 200)}" />
											${string2}
												<br /> <br /></span></li>

										
									</ul>
									</c:forEach>
								</div>
								<div class="art-layout-cell layout-item-1" style="width: 50%;word-wrap:break-word;">
								<h3>Videos</h3>
								<c:forEach items="${vlist}" var="video" varStatus="s">
																<!-- 1. The <iframe> (and video player) will replace this <div> tag. -->
								    <div id="player${s.count}"></div>
								    <center><b><span id="playertitle${s.count}"><a href="selectVideo?id=${video.id}">${video.videoName}</a></span></b></center>
										<script>
										// 2. This code loads the IFrame Player API code asynchronously.
									      var tag${s.count} = document.createElement('script');
									
									      tag${s.count}.src = "https://www.youtube.com/iframe_api";
									      var firstScriptTag${s.count} = document.getElementsByTagName('script')[${s.count-1}];
									      firstScriptTag${s.count}.parentNode.insertBefore(tag${s.count}, firstScriptTag${s.count});
									
									      // 3. This function creates an <iframe> (and YouTube player)
									      //    after the API code downloads.
									      var player${s.count};
									      function onYouTubeIframeAPIReady${s.count}() {
									        player${s.count} = new YT.Player('player${s.count}', {
									          height: '390',
									          width: '400',
									          videoId: '${video.video_id}',
									          events: {
									            'onReady': onPlayerReady${s.count},
									            'onStateChange': onPlayerStateChange${s.count}
									          }
									        });
									      }
									    
											
								            // 4. The API will call this function when the video player is ready.
								      function onPlayerReady${s.count}(event) {
								        //event.target.playVideo();
								      }
								
								      // 5. The API calls this function when the player's state changes.
								      //    The function indicates that when playing a video (state=1),
								      //    the player should play for six seconds and then stop.
								      var done${s.count} = false;
								      function onPlayerStateChange${s.count}(event) {
								        if (event.data == YT.PlayerState.PLAYING && !done${s.count}) {
								          setTimeout(stopVideo, 6000);
								          done${s.count} = true;
								        }
								      }
								      function stopVideo() {
								        player${s.count}.stopVideo();
								      }									    	
									
										</script>		
									</c:forEach>
								<script>
								function onYouTubeIframeAPIReady(){
									onYouTubeIframeAPIReady1();
									//onYouTubeIframeAPIReady2();
									//onYouTubeIframeAPIReady3();
									//onYouTubeIframeAPIReady4();
									//onYouTubeIframeAPIReady5();
								}
								</script>
		
								</div>
							</div>
						</div>
					</div>



				<div class="art-postcontent art-postcontent-0 clearfix">
					<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 40%;word-wrap:break-word;">
									<h3>Business</h3>
									<c:forEach items="${businesslist}" var="program" varStatus="status">
									<ul>
										<li><span style="color: rgb(53, 52, 19);"><span
												style="font-weight: bold;"><a href="selectBusinessProgram?id=${program.id}&albumName=${program.programName}">${program.programName}</a></span>&nbsp;<br/></span><br /> 
												<b>${program.location} :</b>
												
												<fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/>
												
												<c:set var="string1" value="${program.description}"/>
											<c:set var="string2" value="${fn:substring(string1, 0, 200)}" />
											
											${string2}
												<br /> <br /></span></li>
									</ul>
									</c:forEach>
								</div>
								<div class="art-layout-cell layout-item-0" style="width: 30%;word-wrap:break-word;">
									<h3>Sports</h3>
									<c:forEach items="${sportslist}" var="program" varStatus="status">
									<ul>
										<li><span style="color: rgb(53, 52, 19);"><span
												style="font-weight: bold;"><a href="selectSportProgram?id=${program.id}&albumName=${program.programName}">${program.programName}</a></span>&nbsp;<br/></span><br /> 
												<b>${program.location} :</b><fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/><c:set var="string1" value="${program.description}"/>
											<c:set var="string2" value="${fn:substring(string1, 0, 200)}" />
											${string2}
												<br /> <br /></span></li>

										
									</ul>
									</c:forEach>
								</div>
								<div class="art-layout-cell layout-item-0" style="width: 30%;word-wrap:break-word;">
									<h3>Technology</h3>
									<c:forEach items="${technologylist}" var="program" varStatus="status">
									<ul>
										<li><span style="color: rgb(53, 52, 19);"><span
												style="font-weight: bold;"><a href="selectTechnologyProgram?id=${program.id}&albumName=${program.programName}">${program.programName}</a></span>&nbsp;<br/></span><br /> 
												<b>${program.location} :</b><fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/><c:set var="string1" value="${program.description}"/>
											<c:set var="string2" value="${fn:substring(string1, 0, 200)}" />
											${string2}
												<br /> <br /></span></li>

										
									</ul>
									</c:forEach>
								</div>
								
								</div>
							</div>
						</div>
					</div>
 	 	


					<div class="art-postcontent art-postcontent-0 clearfix">					
						<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 70%;word-wrap:break-word;">
									<h3>Astro & Dharma</h3>
									<c:forEach items="${astrolist}" var="program" varStatus="status">
									<ul>
										<li><span style="color: rgb(53, 52, 19);"><span
												style="font-weight: bold;"><a href="selectAstroProgram?id=${program.id}&albumName=${program.programName}">${program.programName}</a></span>&nbsp;<br/></span><br /> 
												<b>${program.location} :</b>
												
												<fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/>
												
												<c:set var="string1" value="${program.description}"/>
											<c:set var="string2" value="${fn:substring(string1, 1, 100)}" />
											
											${string2}
												<br /> <br /></span></li>
									</ul>
									</c:forEach>
								</div>
	 							<div class="art-layout-cell layout-item-0" style="width: 30%;word-wrap:break-word;">
									<h3>Poll</h3>
									<form action="pollsubmit" >
									<b>${poll.programName}</b>
									<input type="hidden" name="id" value="${poll.id}"/>
									<p><input type="radio" name="vote" value="yes">Yes</input></p>
										<p><input type="radio" name="vote" value="no">No</input></p>
										<p><input type="radio" name="vote" value="none">Don't know</input></p>	
										<input type="submit" value="Submit" onclick="return validate();">
										</form>
										<script>
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
 								</div>
							</div>
						</div>
					</div>

			<div class="art-postcontent art-postcontent-0 clearfix">
						<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 100%;word-wrap:break-word;">
					<h3>Entertainment</h3>
					</div></div></div>
					<c:forEach items="${entertainmentlist}" var="program" varStatus="status">
					<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 50%;word-wrap:break-word;">
									<br /> <a href="#" onclick="openchild('slideshow?albumName=${program.programName}','300px','600px;','300','200')"><img src="${program.photourl}" alt="" class="img-indent-2" width="300" height="200"></a>
								</div>
								<div class="art-layout-cell layout-item-0" style="width: 50%;word-wrap:break-word;">
									<p>
										<span style="font-weight: bold;"><a href="selectOtherProgram?id=${program.id}&albumName=${program.programName}">${program.programName}</a></span>&nbsp;
										<br/><b>${program.location} :</b><fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/><c:set var="string1" value="${program.description}"/>
											<c:set var="string2" value="${fn:substring(string1, 0, 200)}" />
											${string2}
										</p>
								</div>
							</div>
						</div>
					</c:forEach>
				
			</div>
		
		

					<div class="art-postcontent art-postcontent-0 clearfix">
						<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 100%;word-wrap:break-word;">
					<h3>Others</h3>
					</div></div></div>
					<c:forEach items="${otherlist}" var="program" varStatus="status">
					<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 50%;word-wrap:break-word;">
									<br /> <a href="#" onclick="openchild('slideshow?albumName=${program.programName}','300px','600px;','300','200')"><img src="${program.photourl}" alt="" class="img-indent-2" width="300" height="200"></a>
								</div>
								<div class="art-layout-cell layout-item-0" style="width: 50%;word-wrap:break-word;">
									<p>
										<span style="font-weight: bold;"><a href="selectOtherProgram?id=${program.id}&albumName=${program.programName}">${program.programName}</a></span>&nbsp;
										<br/><b>${program.location} :</b><fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy hh:mm" />
											<br/><c:set var="string1" value="${program.description}"/>
											<c:set var="string2" value="${fn:substring(string1, 0, 200)}" />
											${string2}
										</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</article>
			</div>
		
		
		</div>
	</div>
</div>