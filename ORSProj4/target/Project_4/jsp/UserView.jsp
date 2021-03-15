
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ORSProj4.util.HTMLUtility"%>
<%@page import="in.co.rays.ORSProj4.util.DataUtility"%>
<%@page import="in.co.rays.ORSProj4.util.ServletUtility"%>
<%@page import="in.co.rays.ORSProj4.controller.UserCtl"%>
<%@page import="in.co.rays.ORSProj4.bean.UserBean" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>User Page</title>

</head>


<body>
    <form action="<%=ORSView.USER_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
        <script type="text/javascript" src="../js/calendar.js"></script>
        <jsp:useBean id="bean" class="in.co.rays.ORSProj4.bean.UserBean"
            scope="request"></jsp:useBean>
		
        <%
            List l = (List) request.getAttribute("roleList");
        %>

        <center>
        <%
        
        	if(bean.getId()>0)
        	{
        		
        	
        
        %>
            <h1>Update User</h1>
            <% } else {%>
            
            <h1>Add User</h1>
<%} %>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>



            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


            <table>
                <tr>
                    <th>First Name <font color="red">*</font>:</th>
                    <td><input type="text" name="firstName" size="21" placeholder="Enter First Name "
                         value="<%=DataUtility.getStringData(bean.getFirstName())%>"> 
                       <%-- value="<%=ServletUtility.getParameter("firstName", request) %>">--%><font
                        color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr>
                    <th>Last Name<font color="red">*</font>:</th>
                    <td><input type="text" name="lastName" size="21" placeholder="Enter Last Name "
                        value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <th>LoginId<font color="red">*</font>:</th>
                    <td><input type="text" name="login" size="21" placeholder="Enter EmailId "
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"
                        <%=(bean.getId() > 0) ? "readonly" : ""%>>
                        <font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                 <tr>
                    <th>MobileNo<font color="red">*</font>:</th>
                    <td><input type="text" name="mno" size="21" placeholder=" Enter MobileNo "
                        value="<%=DataUtility.getStringData(bean.getMobileNo())%>" maxlength="10">
                        <font
                        color="red"> <%=ServletUtility.getErrorMessage("mno", request)%></font></td>
                </tr>
                <tr>
                    <th>Password<font color="red">*</font>:</th>
                    <td><input type="password" name="password" size="21" placeholder="Enter Password "
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th>Confirm Password<font color="red">*</font>:</th>
                    <td><input type="password" name="confirmPassword" size="21" placeholder="Enter Confirm Password "
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("confirmPassword",
                    request)%></font></td>
                </tr>
                <tr>
                    <th>Gender<font color="red">*</font>:</th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("M", "Male");
                            map.put("F", "Female");

                            String htmlList = HTMLUtility.getList("gender",  bean.getGender(),
                                    map);
                        %> <%=htmlList%>
                </tr>
                <tr>
                    <th><b>Role<font color="red">*</font>:</b> </th>
                    <td>
                    <%=HTMLUtility.getList("roleId",
                    String.valueOf(bean.getRoleId()), l)%></td>
                </tr>
                
                				<tr>
					<th align="left">Date Of Birth<font color="red">*</font>:</th>
					<td><input type="text" name="dob" id="datepicker"
						placeholder="Enter Date Of Birth" readonly="readonly" size="21"
						value="<%=DataUtility.getDateString(bean.getDob())%>">
						&nbsp;<font style="position: fixed;" color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=UserCtl.OP_SAVE%>">&emsp; <input type="submit"
                        name="operation" value="<%=UserCtl.OP_RESET%>"></td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>


</html>