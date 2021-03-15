<%@page import="in.co.rays.ORSProj4.util.HTMLUtility"%>
<%@page import="in.co.rays.ORSProj4.controller.CourseListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.ORSProj4.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ORSProj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>

<jsp:useBean id="bean" class="in.co.rays.ORSProj4.bean.CourseBean" scope="request">
</jsp:useBean>

<%@ include file="Header.jsp" %>
<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">
<div align="center">
<h3>
<font color="red"> <%=ServletUtility.getErrorMessage(request) %> </font>
<font color="green"> <%=ServletUtility.getSuccessMessage(request) %> </font>
</h3>
</div>
<%
List clist= (List)request.getAttribute("cList");

int next=DataUtility.getInt(request.getAttribute("nextlist").toString());

%>

<%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);                
                    int index = (pageNo - 1)*pageSize + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<CourseBean> it = list.iterator();

	       			if(list.size()!=0){
                    %>
       

 <table width="100%" align="center">
                <tr>
                 <td align="center">
                 <label> Course Name :</label> 
                 	<%=HTMLUtility.getList("cname", String.valueOf(bean.getId()), clist) %>
                 	<%-- <input type="text" name="cname" placeholder="Enter Course Name" Size= "25" value="<%=DataUtility.getStringData(bean.getName()) %>">
					 --%>&nbsp;
                     <input type="submit" name="operation" value="<%=CourseListCtl.OP_SEARCH%>">
        	         &nbsp;
        	         <input type="submit" name="operation" value="<%=CourseListCtl.OP_RESET%>">
        	         
                 </td>
                </tr>
            </table>
	
     <br>
            
            <table border="1" width="100%" align="center" cellpadding=6px cellspacing=".2">
               <tr style="background: skyblue">
                <th><input type="checkbox" id="select_all" name="select">Select All.</th>
                
                <th>S.NO.</th>
                <th>Course Name.</th>
                <th>Duration.</th>
                <th>Description.</th>
                <th>Edit</th>
                </tr>
                
                <%
                	while(it.hasNext())
                	{
						bean = it.next();
                %>
                
                
                
       <tr align="center">
           	<td><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId() %>">
                    <td><%=index++%></td>
                    <td><%=bean.getName()%></td>
                    <td><%=bean.getDuration()%></td>
                    <td><%=bean.getDescription()%></td>
                    <td><a href="CourseCtl?id=<%=bean.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                <%if(pageNo == 1){ %>
                    <td><input type="submit" name="operation" disabled="disabled" value="<%=CourseListCtl.OP_PREVIOUS%>">
       				<%}else{ %>
       				<td><input type="submit" name="operation"  value="<%=CourseListCtl.OP_PREVIOUS%>"></td>
       				<%} %>		
                     
                     <td><input type="submit" name="operation" value="<%=CourseListCtl.OP_DELETE%>"> </td>
                    <td> <input type="submit" name="operation" value="<%=CourseListCtl.OP_NEW%>"></td>
                    
                 <%--  <% CourseModel model = new CourseModel();                  
                  %>  
                  <% if(list.size() < pageSize || model.nextPk()-1 == bean.getId()){ %>
                  <td align="right"> <input type="submit" name="operation" disabled="disabled" value="<%=CourseListCtl.OP_NEXT%>"></td>
  					<%}else{ %>                   
  				  <td align="right"> <input type="submit" name="operation"  value="<%=CourseListCtl.OP_NEXT%>"></td>
   					<%} %>   --%>   
   					
   					<td align="right"><input type="submit"  name="operation" value="<%=CourseListCtl.OP_NEXT%>" <%=(list.size()<pageSize||next==0)?"disabled":"" %>> </td>
			            
                    
                </tr>
            </table>
            		<%}
	       			System.out.println("----------------00000------"+list.size());
	       			if(list.size() == 0 ){ %>
            		<td align="center"><input type="submit" name="operation" value="<%=CourseListCtl.OP_BACK%>"></td>	
            		<% } %>
            	
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
                   </br>
                   </br>
    </center>
</div>	
 <%@include file="Footer.jsp"%>
</body>
</html>