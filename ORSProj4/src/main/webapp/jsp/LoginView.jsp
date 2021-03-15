<%@page import="in.co.rays.ORSProj4.controller.LoginCtl"%>
<%@page import="in.co.rays.ORSProj4.util.DataUtility"%>
<%@page import="in.co.rays.ORSProj4.util.ServletUtility"%>
<html>
<body>
    <form action="<%=ORSView.LOGIN_CTL%>" method="post">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.rays.ORSProj4.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>Login</h1>

            <H2>
                <font color="red" > <%=ServletUtility.getErrorMessage(request)%> <!-- for invalid id password  -->
                </font> </H2>
 <H2><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%></font></H2> <!--  for logout-->
            
			<%
			String msg =(String) request.getAttribute("message"); //front controller msg
            if(msg!= null){ 
            %>
            <h2 align="center"><font style="color: red"><%=msg %></font>
            <%} %></h2>
              
<%--               <input type="hidden" name="id" value="<%=bean.getId()%>"> --%>
<%--               <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>"> --%>
<%--               <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> --%>
<%--               <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>"> --%>
<%--               <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>"> --%>

            <table>
                <tr>
                    <th>LoginId <font color="red">* </font></th>
                    <td><input type="text" name="login" size=30 placeholder="Enter Login Id"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"> </td>
                        					<td style="position: fixed"><font
                        color="red" > <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                <tr>
                    <th>Password<font color="red">* </font></th>
                    <td><input type="password" name="password" size=30 placeholder="Enter Password"  
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"> </td>
                        					<td style="position: fixed"><font
                        color="red" > <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=LoginCtl.OP_SIGN_IN %>"> &nbsp; <input type="submit"
                        name="operation" value="<%=LoginCtl.OP_SIGN_UP %>" > &nbsp;</td>
                </tr>
                <tr><th></th>
                <td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget my password?</b></a>&nbsp;</td>
            </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>