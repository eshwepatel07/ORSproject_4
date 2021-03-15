<%@page import="in.co.rays.ORSProj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.ORSProj4.util.HTMLUtility"%>
<%@page import="in.co.rays.ORSProj4.util.DataUtility"%>
<%@page import="in.co.rays.ORSProj4.util.ServletUtility"%>
<%@page import="in.co.rays.ORSProj4.controller.MyProfileCtl"%>
<%@page import="in.co.rays.ORSProj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> My Profile</title>


</head>



<body>
        <jsp:useBean id="bean" class="in.co.rays.ORSProj4.bean.UserBean" scope="request"></jsp:useBean>
<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

	 <%@ include file="Header.jsp"%>
<center>
         
            
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            
     <div align="center">
                <h1>My Profile</h1>
            <H2><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></H2>
            <H2><font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></H2>
     </div>
          <!--   <table style="font-size: 20px"> -->
            <table>
                <tr>
                    <th align="left">LoginId <span style="color: red">*</span> :</th>
                    <td><input type="text" name="login" readonly="readonly" size="21" value="<%=DataUtility.getStringData(bean.getLogin())%>"readonly="readonly"></td>          
                   <td style="position: fixed"> <font  color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
	
	<tr><th style="padding: 3px"></th></tr>

                <tr>
                    <th align="left">First Name <span style="color: red">*</span> :</th>
                    <td><input type="text" name="firstName" size="21"  value="<%=DataUtility.getStringData(bean.getFirstName())%>">
                   </td><td style="position: fixed"> <font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
    
    <tr><th style="padding: 3px"></th></tr>
                <tr>
                    <th align="left">Last Name <span style="color: red">*</span> :</th>
                    <td><input type="text" name="lastName" size="21"  value="<%=DataUtility.getStringData(bean.getLastName())%>">
                   </td><td style="position: fixed"> <font  color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
   <tr><th style="padding: 3px"></th></tr>             
                <tr>
                    <th align="left">Gender <span style="color: red">*</span> :</th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                      
                            map.put("M", "Male");
                            map.put("F", "Female");
                            String htmllist=HTMLUtility.getList("gender", bean.getGender(),map);
                        %> <%=htmllist%>
                    </td>
                    <td style="position: fixed"> <font  color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font></td>
                </tr>
    <tr><th style="padding: 3px"></th></tr>
                <tr>
                    <th align="left">Mobile No <span style="color: red">*</span> :</th>
                    <td><input type="text" name="mobileNo" size="21" maxlength="10" value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
                    
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
                </tr>
<tr><th style="padding: 3px"></th></tr>
                <tr>
                    <th align="left">Date Of Birth <span style="color: red">*</span> :</th>
                    <td><input type="text" name="dob" id="datepicker" size="21" value="<%=DataUtility.getDateString(bean.getDob())%>">
                    
                    </td><td><font style="position: fixed" ; color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
   <tr><th style="padding: 3px"></th></tr>	
                <tr> <th></th><td colspan="2">
                    &nbsp;
                    <input type="submit" name="operation" value="<%=MyProfileCtl.OP_SAVE %>"> 
                   &nbsp;
                    <input type="submit" name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD %>"> 
                    
                    </td>
                </tr>
            </table>
     
    </form>
    
    </center>
 
    <%@ include file="Footer.jsp"%>
</body>


</html>