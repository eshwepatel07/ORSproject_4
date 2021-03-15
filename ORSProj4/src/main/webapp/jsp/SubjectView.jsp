<%@page import="in.co.rays.ORSProj4.controller.SubjectCtl"%>
<%@page import="in.co.rays.ORSProj4.util.DataUtility"%>
<%@page import="in.co.rays.ORSProj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ORSProj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Subject Registration Page</title>


</head>
<body>
<jsp:useBean id="bean" class="in.co.rays.ORSProj4.bean.SubjectBean" scope="request"></jsp:useBean>
<%@ include file="Header.jsp" %>
<form action="<%=ORSView.SUBJECT_CTL %>" method="post">
	
	<%
	List clist=(List) request.getAttribute("clist");
	%>
<center>
	
	<%
		if(bean!=null && bean.getId()>0)
		{
	
	%>
		<h1>Update Subject</h1>
		<%
		}
		else
		{
		 %>
		 <h1>Add Subject</h1>
		<% 	}	%>	
	<div align="center">
	<h3>
	<font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
		<font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
	</h3>
	
	
	
	</div>
	<input type="hidden" name="id" value="<%=bean.getId()%>">
	<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
	<input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
	<input type="hidden" name="createdDatetime" value="<%=bean.getCreatedDatetime()%>">
	<input type="hidden" name="modifiedDatetime" value="<%=bean.getModifiedDatetime()%>">
	
	
	<table>
	
		<tr>
			<tr>
	<th align="left">Course Name <span style="color: red">*</span> :</th>
	<td> <%=HTMLUtility.getList("cname", DataUtility.getStringData(bean.getCourseId()), clist) %> </td>
			<td> <font color="red"> <%=ServletUtility.getErrorMessage("cname",request) %></font> </td>
			
			</tr>

<tr> <th style="padding: 3px"></th> </tr>
		<tr>
		
	
		
		<th>Subject Name <span style="color: red">*</span>:</th>
		<td> 
			<input type="text" name="sname" placeholder="enter Subject Name" size="21" value="<%=DataUtility.getStringData(bean.getSubjectName()) %>"> 
		</td>
		<td> <font color="red"> <%=ServletUtility.getErrorMessage("sname",request) %></font> </td>
		</tr>
		
<tr> <th style="padding: 3px"></th> </tr>
		<tr>
		<th align="left"> Description <span style="color: red">*</span> :</th>
		<td><input type="text" name="description" placeholder="enter description" size="21" value="<%=DataUtility.getStringData(bean.getDescription()) %>"> </td>
		<td><font color="red"> <%=ServletUtility.getErrorMessage("description",request) %></font> </td>
		
		</tr>
		
	
<tr> <th style="padding: 3px"></th> </tr>

<tr> <th> </th>

<%
if(bean.getId()>0)
{

%>
<td>


	 &nbsp;  &emsp;
	<input type="submit" name="operation" value="<%=SubjectCtl.OP_UPDATE %>">
	 &nbsp;  &nbsp;
	<input type="submit" name="operation" value="<%=SubjectCtl.OP_CANCEL %>">	
		</td>
<%
}
else
{
%>

	
	<td>
	 &nbsp;  &emsp;
	<input type="submit" name="operation" value="<%=SubjectCtl.OP_SAVE %>">
	 &nbsp;  &nbsp;
	<input type="submit" name="operation" value="<%=SubjectCtl.OP_RESET %>">	
	</td>
	<%}
	%>
	
	</tr>
	
	
	</table>


</form>
</center>

<%@include file ="Footer.jsp"%>
</body>

</html>