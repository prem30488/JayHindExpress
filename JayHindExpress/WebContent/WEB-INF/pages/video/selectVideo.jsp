<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <center>
	<h1>Video Details</h1>
	</center>
<br/><br/>
<a href="viewVideos" style="color:black">View Videos</a> -> Video Details
<br/><br/>
<center>
		<table style="width: 100%;font-size: 14px;" align="center" class="table">
		
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Video Name
			</th>
			 <td>
				${video.videoName}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Description
			</th>
			 <td>
				${video.description}
			</td>
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Created Date
			</th>
			 <td>
				<fmt:formatDate value="${video.createdDate}" pattern="dd-MMM-yyyy hh:mm:ss" />
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Video URL
			</th>
			 <td>
				${video.photourl}
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Video ID
			</th>
			 <td>
				${video.video_id}
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Posted  By
			</th>
			 <td>
				${video.posted_by}
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Preview
			</th>
			 <td>
				<div id="player"></div>
				<script>
										// 2. This code loads the IFrame Player API code asynchronously.
									      var tag = document.createElement('script');
									
									      tag.src = "https://www.youtube.com/iframe_api";
									      var firstScriptTag = document.getElementsByTagName('script')[0];
									      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
									
									      // 3. This function creates an <iframe> (and YouTube player)
									      //    after the API code downloads.
									      var player;
									      function onYouTubeIframeAPIReady${s.count}() {
									        player = new YT.Player('player', {
									          height: '300',
									          width: '600',
									          videoId: '${video.video_id}',
									          events: {
									            'onReady': onPlayerReady,
									            'onStateChange': onPlayerStateChange
									          }
									        });
									      }
									    
											
								            // 4. The API will call this function when the video player is ready.
								      function onPlayerReady(event) {
								        //event.target.playVideo();
								      }
								
								      // 5. The API calls this function when the player's state changes.
								      //    The function indicates that when playing a video (state=1),
								      //    the player should play for six seconds and then stop.
								      var done = false;
								      function onPlayerStateChange(event) {
								        if (event.data == YT.PlayerState.PLAYING && !done) {
								          setTimeout(stopVideo, 6000);
								          done = true;
								        }
								      }
								      function stopVideo() {
								        player.stopVideo();
								      }									    	
									
										</script>
								
			</td>  
		</tr>
		
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				<!-- Program Slideshow Photos -->
			</th>
			 <td>
			 <%-- <div style="width:100%;height:100%;">
				 <c:forEach items="${fileList}" var="fileName" varStatus="status">
            	 	<img src="../${fileDir}/${fileName}" width="100" height="100">
           		</c:forEach>
			</div> --%>
			</td>  
		</tr>
		</table>
</center>
		