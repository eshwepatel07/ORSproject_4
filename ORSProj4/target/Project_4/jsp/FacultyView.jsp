<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.ORSProj4.util.HTMLUtility"%>
<%@page import="in.co.rays.ORSProj4.util.DataUtility"%>
<%@page import="in.co.rays.ORSProj4.util.ServletUtility"%>
<%@page import="in.co.rays.ORSProj4.controller.FacultyCtl"%>
<%@page import="in.co.rays.ORSProj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Faculty Registration Page</title>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
</head>



<body>
<jsp:useBean id="bean" class="in.co.rays.ORSProj4.bean.FacultyBean" scope="request"></jsp:useBean>


<%@include file="Header.jsp"%>


	<form action="<%=ORSView.FACULTY_CTL%>" method="post">

		<%
			List colist = (List) request.getAttribute("CollegeList");
			List clist = (List) request.getAttribute("CourseList");
			List slist = (List) request.getAttribute("SubjectList");
		%>

		<center>
			<h1>
				<%
					if (bean != null && bean.getId() > 0) {
				%>

				<tr>
					<th>Update Faculty</th>
				</tr>
				<%
					} else {
				%>

				<tr>
					<th>Add Faculty</th>
				</tr>
				<%
					}
				%>
			</h1>

			<div>
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
			</div>

			<input type="hidden" name="id" value=<%=bean.getId()%>> 
			<input type="hidden" name="createdby" value=<%=bean.getCreatedBy()%>>
			<input type="hidden" name="modifiedby" value=<%=bean.getModifiedBy()%>>
			 <input type="hidden" name="createdDatetime" value=<%=DataUtility.getStringData(bean.getCreatedDatetime())%>>
			<input type="hidden" name="modifiedDatetime" value=<%=DataUtility.getStringData(bean.getModifiedDatetime())%>>

			<table>

				<tr>
					<th align="left">First Name <span style="color: red">*</span>
						:
					</th>
					</th>
					<td><input type="text" name="firstname"
						placeholder=" Enter First Name" size="21"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("firstname", request)%></font>
					</td>
				</tr>
				<tr>
					<th style="padding: 2px"></th>
				</tr>

				<tr>
					<th align="left">Last Name <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="lastname"
						placeholder=" Enter last Name" size="21"
						value="<%=DataUtility.getStringData(bean.getLastName())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("lastname", request)%></font>
					</td>
				</tr>
				<tr>
					<th style="padding: 2px"></th>
				</tr>

				<tr>
					<th align="left">Gender <span style="color: red">*</span> :
					</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");

							String hlist = HTMLUtility.getList("gender", String.valueOf(bean.getGender()), map);
						%> <%=hlist%>
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td>
				</tr>
				<tr>
					<th style="padding: 2px"></th>
				</tr>

				<tr>
					<th align="left">CollegeName <span style="color: red">*</span>
						:
					</th>
					<td><%=HTMLUtility.getList("collegeid", String.valueOf(bean.getCollegeId()), colist)%>
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("collegeid", request)%></font>
					</td>
				</tr>
				<tr>
					<th style="padding: 2px"></th>
				</tr>

				<tr>
					<th align="left">CourseName <span style="color: red">*</span>
						:
					</th>
					<td><%=HTMLUtility.getList("courseid", String.valueOf(bean.getCourseId()), clist)%>
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("courseid", request)%></font>
					</td>
				</tr>
				<tr>
					<th style="padding: 2px"></th>
				</tr>

				<tr>
					<th align="left">SubjectName <span style="color: red">*</span>
						:
					</th>
					<td><%=HTMLUtility.getList("subjectid", String.valueOf(bean.getSubjectId()), slist)%>
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("subjectid", request)%></font>
					</td>
				</tr>
				<tr>
					<th style="padding: 2px"></th>
				</tr>
                       
                        <tr>
                    <th align="left">Date Of Birth <span style="color: red">*</span> :</th>
                    <td><input type="text" name="dob" placeholder="Enter Date Of Birth" size= "21" readonly="readonly" id="datepicker" value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
                      <td style="position: fixed;">	<font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                <tr>
    <tr><th style="padding: 2px"></th></tr>          
                
                       
				<tr>
					<th align="left">LoginId <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="loginid"
						placeholder=" Enter Login Id" size="21"
						value="<%=DataUtility.getStringData(bean.getEmailId())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("loginid", request)%></font>
					</td>
				</tr>
				<tr>
					<th style="padding: 2px"></th>
				</tr>

				<tr>
					<th align="left">MobileNo <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="mobileno" size="21"
						maxlength="10" placeholder=" Enter Mobile No"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
					</td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("mobileno", request)%></font>
					</td>
				</tr>
				<tr>
					<th style="padding: 2px"></th>
				</tr>

				<tr>
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>

					<td>
					 &nbsp;  &emsp;
					<input type="submit" name="operation" value="<%=FacultyCtl.OP_UPDATE%>">
					 &nbsp;  &nbsp;
					 <input type="submit" name="operation" value="<%=FacultyCtl.OP_CANCEL%>"></td>
					<%
						} else {
					%>
					<td>
					 &nbsp;  &emsp;
					<input type="submit" name="operation" value="<%=FacultyCtl.OP_SAVE%>"> 
						 &nbsp;  &nbsp;
						<input type="submit" name="operation" value="<%=FacultyCtl.OP_RESET%>"></td>
					<%
						}
					%>
				</tr>
			</table>
		</center>

	</form>
	<%@include file="Footer.jsp"%>
</body>



</html>