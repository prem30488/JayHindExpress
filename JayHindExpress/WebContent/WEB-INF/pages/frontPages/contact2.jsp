<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.errorMSG {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

</style>

<div class="left_content">
          <div class="contact_area">
            <h2>Contact Us</h2>
            <p>Provide your address, phone numbers and e-mail information allowing visitors to reach you faster. Maps and directions might be also useful.</p>
            <%-- <form action="#" class="contact_form">
              <input class="form-control" type="text" placeholder="Name*">
              <input class="form-control" type="email" placeholder="Email*">
              <textarea class="form-control" cols="30" rows="10" placeholder="Message*"></textarea>
              <input type="submit" value="Send Message">
            </form> --%>
          
            <form:form class="contact_form" name="form" modelAttribute="contact" method="post" action="addContactForm" >
              <fieldset>
              <table style="width: 100%">
							<c:if test="${not empty error}">
								<div class="errorMSG">${error}</div>
							</c:if>
							<c:if test="${not empty msg}">
								<div class="msg">${msg}</div>
							</c:if>
					<spring:bind path="name">
							<form:input path="name" placeholder="Name*" class="form-control" type="text"  />
							<form:errors path="name"></form:errors>
					</spring:bind>
					<spring:bind path="email">
							<form:input path="email" placeholder="Email*" class="form-control" type="text"  />
							<form:errors path="email"></form:errors>
					</spring:bind>
                	<spring:bind path="number">
							<form:input path="number" placeholder="Phone*" class="form-control" type="text"  />
							<form:errors path="number"></form:errors>
					</spring:bind>
					<spring:bind path="message">
							<form:textarea path="message" placeholder="Message*" class="form-control" onBlur="if(this.value==''){this.value='MESSAGE'}" onFocus="if(this.value=='MESSAGE'){this.value=''}"></form:textarea>
							<form:errors path="message"></form:errors>
					</spring:bind>
                			<input type="submit" value="Send Message" />

            	</table>
              </fieldset>  
            </form:form> 
        </div>
      </div>  