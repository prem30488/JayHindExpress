<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                <h2 class="art-postheader">Contact Us</h2>
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%" >
        <p>Provide your address, phone numbers and e-mail information allowing visitors to reach you faster. Maps and directions might be also useful.</p>
        
        
        
        
    </div>
    </div>
</div>
</div>


</article></div>
                    </div>
                </div>
            </div>
   
   <section id="content"><div class="ic"></div>
      <div class="sub-page">
      	<div class="sub-page-left box-9">
        	<h2>Contact Form</h2>
            <form:form id="form" name="form" modelAttribute="contact" method="post" action="addContactForm" >
              <fieldset>
              <table style="width: 60%">
					<tr>
						<td colspan="2" align="center">
							<c:if test="${not empty error}">
								<div class="errorMSG">${error}</div>
							</c:if>
							<c:if test="${not empty msg}">
								<div class="msg">${msg}</div>
							</c:if>
						</td>
					</tr>
					<spring:bind path="name">
					<tr class="<c:if test="${status.error}"> trError </c:if>">
						<td style="padding:0px;width:70%;">
							<label><form:input path="name" type="text" value="Name" onBlur="if(this.value=='') this.value='Name'" onFocus="if(this.value =='Name' ) this.value=''" /></label>
							<br/>
							<form:errors path="name"></form:errors>
						</td>
					</tr>
					</spring:bind>
					<spring:bind path="email">
					<tr class="<c:if test="${status.error}"> trError </c:if>">
						<td style="padding:0px">
							<label><form:input path="email" type="text" value="Email" onBlur="if(this.value=='') this.value='Email'" onFocus="if(this.value =='Email' ) this.value=''" /></label>
							<br/>
							<form:errors path="email"></form:errors>
						</td>
					</tr>
					</spring:bind>
                	<spring:bind path="number">
					<tr class="<c:if test="${status.error}"> trError </c:if>">
						<td style="padding:0px">
							<label><form:input path="number" type="text" value="Phone" onBlur="if(this.value=='') this.value='Phone'" onFocus="if(this.value =='Phone' ) this.value=''" /></label>
							<br/>
							<form:errors path="number"></form:errors>
						</td>
					</tr>
					</spring:bind>
					<spring:bind path="message">
					<tr class="<c:if test="${status.error}"> trError </c:if>">
						<td style="padding:0px">
							<label><form:textarea path="message" onBlur="if(this.value==''){this.value='MESSAGE'}" onFocus="if(this.value=='MESSAGE'){this.value=''}"></form:textarea></label>
							<br/>
							<form:errors path="message"></form:errors>
						</td>
					</tr>
					</spring:bind>
                	<tr>
						<td style="padding:0px">
                			<div class="btns"><a href="#" class="button" onClick="document.getElementById('form').submit()">Send</a><br/></div>
                		</td>
                	</tr>
            	</table>
              </fieldset>  
            </form:form> 
        </div>
      </div>  
   </section> 