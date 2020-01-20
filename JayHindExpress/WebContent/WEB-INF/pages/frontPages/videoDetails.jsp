<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="${program.videoName}"/>
    <%-- <meta name="description" content="${program.description}"/> --%>
    <title>Jay Hind Express - ${program.videoName}</title>

<div class="single_page">
            <ol class="breadcrumb">
              <li><a href="Home">Home</a></li>
              <li><a href="viewVideoGallary">Video Gallery</a></li>
              <li class="active">${program.videoName}</li>
            </ol>
            <h1>Video Gallery</h1>
            
            <div class="single_page_content"> 
            <h4><a href="selectVideo?id=${program.id}&albumName=${program.videoName}">${program.videoName }</a></h4>
            <div class="post_commentbox"> <a href="#"><i class="fa fa-user"></i>${program.posted_by}</a> <span><i class="fa fa-calendar"></i><fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy" /></span> <a href="#"><i class="fa fa-clock-o"></i>${program.niceCreatedDate}</a> <a href="#"><i class="fa fa-map-marker"></i>${program.location}</a> <a href="#"><i class="fa fa-eye"></i>Total View : ${program.frequency}</a></div>
            <br/><br/><br/><br/>
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
				      function onYouTubeIframeAPIReady() {
				        player = new YT.Player('player', {
				          height: '300',
				          width: '600',
				          videoId: '${program.video_id}',
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
	    <script type="text/javascript" src="//platform-api.sharethis.com/js/sharethis.js#property=58d40dfc2b02af00112c757a&product=inline-share-buttons"></script>
		    <span class="sharethis-inline-share-buttons" style="max-width:600px;posotion:relative;"></span>
		    </div>
		    <br/>
			<span>
              ${program.description}
            </span>
            <div class="related_post">
              <h2>Related Post <i class="fa fa-thumbs-o-up"></i></h2>
              <ul class="spost_nav wow fadeInDown animated">
                <c:forEach items="${items}" var="sample" varStatus="s">
                <c:if test="${sample.category eq 'video' }">
                <li>
                  <div class="media"> 
                  	<a class="media-left" href="${sample.url}"> 
                  		<iframe width="100%" height="100%" src="http://www.youtube.com/embed/${sample.video_id}?feature=player_detailpage" frameborder="0" allowfullscreen></iframe> 
                  	</a>
                    <div class="media-body">
                    	<a class="catg_title" href="${sample.url}"> 
                    	${sample.title}</a> 
                    </div>
                  </div>
                </li>
                </c:if>
                </c:forEach>
              </ul>
            </div>
            <c:set var="nav" value="1"/>
            <nav class="nav-slit"> 
	        <c:forEach items="${items}" var="sample" varStatus="s">
                <c:if test="${sample.category eq 'video' && sample.title ne program.videoName}">
                
                <c:if test="${nav==1}">
	        		<a class="prev" href="${sample.url}"> <span class="icon-wrap"><i class="fa fa-angle-left"></i></span>
	        		<div>
	          		<h3>${sample.title}</h3>
	        		<iframe width="100%" height="100%" src="http://www.youtube.com/embed/${sample.video_id}?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
				</div>
	        		</a>
	        		<c:set var="nav" value="2"/>
	        	</c:if>
	        	
	        	<c:if test="${nav==2}">
	        	<a class="next" href="${sample.url}"> <span class="icon-wrap"><i class="fa fa-angle-right"></i></span>
	        	<div>
	          	<h3>${sample.title}</h3>
	          	<iframe width="100%" height="100%" src="http://www.youtube.com/embed/${sample.video_id}?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>
				</div>
	        	</a> 
	        	</c:if>
	        	</c:if>
	        </c:forEach>
	        </nav>
  </div>