<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Read Employee Page</title>
</head>
<body>
	<center>
		<h1 style="color: #0066CC">Read Operation</h1>
		<br />
		<br />

		<!--
			<table bgcolor="#DCDCDC">

				<tr>
					<td>Please Enter the Employee Id:<span style="color: red;">*</span></td>
					<td><form:input type="text" value="empId" /></td>
					</td>
					
					
					<td colspan="2"><input type="submit" name="submit"
						value="Delete"></input></td>
				
				</tr>
			</table> -->
<table bgcolor="#DCDCDC" border=1>
<tr><th>Employee ID</th>
<th>Employee Name</th>
<th>Employee Pan</th>
<th>Employee Designation</th>
<th>Employee Domian</th>
<th>Employee Doj</th>
<th>Employee Dob</th>
<th>Employee salary</th>
<th>Employee Email</th>
<th>Employee Password</th>
</tr>
<c:forEach var="list" items="empList">

<tr>
<td>${empList.empId}</td>
<td>${empList.empName}</td>
<td>${empList.empPan}</td>
<td>${empList.empDesignation}</td>
<td>${empList.empDomain}</td>
<td>$empList.empDoj}</td>
<td>${empList.empDob}</td>
<td>${empList.empSalary}</td>
<td>${empList.empEmail}</td>
<td>${empList.empPassword}</td>
</tr>
</c:forEach>
</table>
		
		<br> <a href="EmployeeForms.html">Back To Employee Forms</a>
	</center>
</body>
</html>