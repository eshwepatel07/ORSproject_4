<%@page import="in.co.rays.ORSProj4.controller.ForgetPasswordCtl"%>
<%@page import="in.co.rays.ORSProj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Forget Password</title>

<style type="text/css">
.ss{
height: 580px;
}
</style> 
</head>
<body>

<jsp:useBean id="bean" class="in.co.rays.ORSProj4.bean.UserBean" scope="request"></jsp:useBean>

<%@include file="Header.jsp" %>
<div class="ss">
	<form action="<%= ORSView.FORGET_PASSWORD_CTL %>" method="post">

		<div align="center">
		<h1> Forget Your Password ?....</h1>
		<lable>Submit your Email address and we'll send your password.</lable><br><br>
		
		<h2> <font color="green"> <%=ServletUtility.getSuccessMessage(request) %>
		</font></h2>
		<h2> <font color="red"> <%=ServletUtility.getErrorMessage(request) %>
		 </font></h2>
		
		</div>

	<div align="center">
<%-- 			<input type="hidden" name="id"  value="<%=bean.getId() %>"> --%>
			
			<table align="center">
			
			<tr><th>Email Id <span style="color:red ">*</span></th>
			<td> <input type="text" name="login" size="25" placeholder="Enter the Valid-Email Id" value="<%=ServletUtility.getParameter("login", request) %>">
			</td>
			<td style="position: fixed"> <font color="red">
	
				<%=ServletUtility.getErrorMessage("login", request) %>
				
			</font>
			</tr>
			
				<tr><th style="padding: 3px"></th></tr>
				
		            <tr><th></th>
		            <td>
		            &emsp;&nbsp;
	            	<input type="submit" name="operation" value="<%=ForgetPasswordCtl.OP_GO%>">
	            	&nbsp;&nbsp;
	            	 <input type="submit" name="operation" value="<%=ForgetPasswordCtl.OP_RESET%>">
	            	 </td>
	            	 </tr>
			</table>
		</div>
	</form>

</div>

 <%@ include file="Footer.jsp"%>
</body>
</html>