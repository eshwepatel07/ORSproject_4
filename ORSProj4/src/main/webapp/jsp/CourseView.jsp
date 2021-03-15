<%@page import="in.co.rays.ORSProj4.controller.CourseCtl"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.ORSProj4.util.HTMLUtility"%>
<%@page import="in.co.rays.ORSProj4.util.ServletUtility"%>
<%@page import="in.co.rays.ORSProj4.util.DataUtility"%>
<%@page import="in.co.rays.ORSProj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="Header.jsp" %>
<jsp:useBean id="bean" class="in.co.rays.ORSProj4.bean.CourseBean" scope="request"></jsp:useBean>

<form action="<%=ORSView.COURSE_CTL %>" method="post">

	<div align="center">
	<h3>
	<font color="green"> <%=ServletUtility.getSuccessMessage(request) %></font>
	</h3>
<h2>
	<font color="red"> <%=ServletUtility.getErrorMessage(request) %></font>

</h2>
	</div>


	<input type="hidden" name="id" value="<%=bean.getId() %>">
	<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
	<input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy() %>">
	<input type ="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime()) %>">
	<input type ="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime()) %>">
	<%
	if(bean.getId()>0)
	{
	%>
	<h1 align="center"> Update Course</h1>
	<%
	}
	else
	{
	%>
	
	<h1 align="center"> Add Course</h1>
	<%
	}
	%>
	<table align="center">
		<tr> 
	<th align="left">Course Name <span style="color: red" >*</span> :</th></th> 
	<td> <input type="text" name="cname" placeholder="enter course name" size="21" value="<%=DataUtility.getStringData(bean.getName()) %>"> </td>
	<td style="position: fixed"> <font color="red"> <%=ServletUtility.getErrorMessage("cname",request) %> </font>   </td>
	
	</tr>
	
	
	</td><td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("duration", request) %></font>
	</td>
	</tr>
<tr><th style="padding: 3px"></th></tr>	
	<tr>
	<th align="left">Description <span style="color: red" >*</span> :</th>
	<td><input type="text" name ="description" placeholder="Enter Description" size="21" value="<%=DataUtility.getStringData(bean.getDescription())%>">
	</td>
	<td style="position: fixed"  ><font color="red"><%=ServletUtility.getErrorMessage("description", request) %></font>
	</td>
	</tr>

	
	
	<tr><th style="padding: 3px"></th></tr>	
	
	<tr>
	<th align="left">Duration <span style="color:red">*</span> :</th>
	<td>
	<%
	LinkedHashMap map = new LinkedHashMap();
//	HashMap map = new HashMap();
		map.put("1 Year", "1 Year");
    	map.put("2 Year", "2 Year");
    	map.put("3 Year", "3 Year");
    	map.put("4 Year", "4 Year");
    	map.put("5 Year", "5 Year");
    	map.put("6 Year", "6 Year");
    	
  	  String htmlList = HTMLUtility.getList("duration", bean.getDuration(), map);
	%> 
	<%=htmlList%>
	
	</td>
	<tr><th></th>
	<%
	if(bean.getId() > 0){
	%>
	<td>
	 &nbsp;  &emsp;
	<input type="submit" name ="operation" value="<%=CourseCtl.OP_UPDATE %>">
	 &nbsp;  &nbsp;
	<input type="submit" name ="operation" value="<%=CourseCtl.OP_CANCEL %>">
	</td>
	<%}else{ %>
	<td>
	 &nbsp;  &emsp;
	<input type="submit" name ="operation" value="<%=CourseCtl.OP_SAVE %>">
		 &nbsp;  &nbsp;
		<input type="submit" name ="operation" value="<%=CourseCtl.OP_RESET %>">
	</td>
	<%} %>
	</tr>
	
	
		</table>
	
	
		
</form>

	</center>
	

	<%@include file ="Footer.jsp"%>

</body>
</html>