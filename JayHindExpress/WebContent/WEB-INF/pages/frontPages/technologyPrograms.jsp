<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="js/technology/programs.js"></script>
   <div class="single_page">
            <ol class="breadcrumb">
              <li><a href="Home">Home</a></li>
              <li class="active"><a href="viewTechnologyPrograms">Technology News</a></li>
              <!-- <li class="active">Mobile</li> -->
            </ol>
            <h1>Technology News</h1>
            
            <div class="single_page_content"> 
            <c:forEach items="${list}" var="program" varStatus="status">
            <h4><a href="selectTechnologyProgram?id=${program.id}&albumName=${program.programName}">${program.programName }</a></h4>
            <div class="post_commentbox"> <a href="#"><i class="fa fa-user"></i>${program.posted_by}</a> <span><i class="fa fa-calendar"></i><fmt:formatDate value="${program.createdDate}" pattern="dd-MMM-yyyy" /></span> <a href="#"><i class="fa fa-clock-o"></i>${program.niceCreatedDate}</a> <a href="#"><i class="fa fa-map-marker"></i>${program.location}</a> <a href="#"><i class="fa fa-eye"></i>Total View : ${program.frequency}</a></div>
            <br/><br/><br/><br/>
            <a href="selectTechnologyProgram?id=${program.id}&albumName=${program.programName}">
            <img class="img-center" style="width:400px;height:300px;" src="${program.photourl}" alt="">
            </a>  <span>
              <c:set var="string1" value="${program.description}"/>
			<c:set var="string2" value="${fn:substring(string1, 0, 200)}<b>...</b>" />
			${string2}
              </span>
            </c:forEach>
            <table border="1" cellpadding="3" cellspacing="3">
				<tr>
					<c:forEach begin="1" end="${noOfPages}" var="i">
						<ul>
							<c:choose>
								<c:when test="${currentPage eq i}">
									<td id="next" style="width: 20px;">${i}</td>
								</c:when>
								<c:otherwise>
									<td id="next" style="width: 20px;"><a href="#"
										onclick="getPage(${i});">${i}</a></td>

								</c:otherwise>
							</c:choose>
						</ul>
						<c:url value="getItemByModel" var="getModel" />
						<form:form action="${getModel}" method="post"
							id="getItemByModelForm" name="getItemByModelForm">
							<input type="hidden" name="from_model" id="from_model"
								value="${param.from_model}" />
							<input type="hidden" name="to_model" id="to_model"
								value="${param.to_model}" />
							<input type="hidden" name="page" id="paged" value="0" />
						</form:form>
					</c:forEach>
					<c:if test="${currentPage lt noOfPages}">
						<td id="next"><a href="#"
							onclick="getNextPage(${currentPage + 1});">Next</a></td>
					</c:if>
				</tr>

			</table>
            
            </div>
         
  <form:form  action="viewTechnologyPrograms" method='get' id="form">
					    <input type="hidden" id="page" name="page" value = "1" /> 
  </form:form>
  <div class="related_post">
              <h2>Related Post <i class="fa fa-thumbs-o-up"></i></h2>
              <ul class="spost_nav wow fadeInDown animated">
                <c:forEach items="${items}" var="sample" varStatus="s">
                <c:if test="${sample.category eq 'technology' }">
                <li>
                  <div class="media"> 
                  	<a class="media-left" href="${sample.url}"> 
                  		<img src="${sample.photoURL}" alt=""> 
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
  </div>