<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta charset="utf-8">
<div class="art-layout-wrapper">
	<div class="art-content-layout">
		<div class="art-content-layout-row">
			<div class="art-layout-cell art-content">
				<article class="art-post art-article">
					<div class="art-postcontent art-postcontent-0 clearfix">
						<div class="art-content-layout">
							<div class="art-content-layout-row">
								<div class="art-layout-cell layout-item-0" style="width: 100%">
									<p>
										<span style="font-weight: bold;font-color:red;">Breaking News :
										<form:form id="programForm" name="programForm" modelAttribute="heading" method='POST' action="updateHeading?${_csrf.parameterName}=${_csrf.token}"  enctype="multipart/form-data"> 
										<table style="width: 100%">
											<tr>
												<td colspan="3" align="center">
													<c:if test="${not empty error}">
														<div class="errorMSG">${error}</div>
													</c:if>
													<c:if test="${not empty msg}">
														<div class="msg">${msg}</div>
													</c:if>
												</td>
											</tr>
											<spring:bind path="message">
											<tr class="<c:if test="${status.error}"> trError </c:if>" style="width:300px;">
												<th class="headerRow thWidth">
													<form:label	path="message">
														Breaking News :
													</form:label>
												</th>
												<td style="width:40%;">
													<form:textarea path="message" id="message" maxlength="200" size="25"  />
													
													<form:hidden path="id"/>
												</td>
												<td style="width:30%;">
													<form:errors path="message"></form:errors>
													<label for="message" class="error"></label>
												</td>
											</tr>
											<tr>
												<td colspan="3" align="center">
													<input name="submit" type="submit" value="Update Breaking News" class="button"/>
												</td>
											</tr>
											</spring:bind>
										</table>
										</form:form>
										</span>
									</p>

								
								</div>
							</div>
						</div>
						
				</article>
			</div>
		</div>
	</div>
</div>