<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr bgcolor="#DCDCDC">
<td><form:input path="employeeId"/></td>
<td style="color: red;"><form:errors path="employeeId"></form:errors></td>
</tr>
<tr bgcolor="#DCDCDC">
<td>Employee Name:<span style="color: red;">*</span></td>
<td><form:input path="employeeName"/></td>
<td style="color: red;"><form:errors path="employeeName"></form:errors></td>
</tr>
<tr bgcolor="#DCDCDC">
<td>Employee Salary:<span style="color: red;">*</span></td>
<td><form:input path="salary"/></td>
<td style="color: red;"><form:errors path="salary"></form:errors></td>
</tr>
<tr bgcolor="#DCDCDC">
<td>Department:<span style="color: red;">*</span></td>
<td><form:input path="employeeDepartment"/></td>
<td style="color: red;"><form:errors path="employeeDepartment"></form:errors></td>
</tr>
<tr>
					<td>Employee Domain:<span style="color: red;"></span>
					<td><input list="domains"/>
					<datalist id="domains"> 
					<option value="Domain">
					<option value="Jee">
					<option value="Cloud">
					<option value="Java">
					<option value="SAP">
					</datalist>
					</td> 
					 

				</tr>
<tr align="center">
<td colspan="2"><input type="submit" name="submit"
value="Claim"></input></td>
</table>
</body>
</html>