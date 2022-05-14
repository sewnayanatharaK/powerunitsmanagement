<%@ page import="com.Unit"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Units Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Unit.js"></script>
</head>
<body>
	<div class = "container">
		<div class = "row">
				<div class = "col-6">
					<h1>Power Units Management</h1>
	
	<form id="formItem" name="formItem" method="post" action="unit.jsp">
		 Unit code:
		 <input id="unitcode" name="unitcode" type="text"
 						class="form-control form-control-sm">
 						
		<br> Name:
		<input id="name" name="name" type="text"
 						class="form-control form-control-sm">
 						
		<br> Address:
		<input id="address" name="address" type="text"
 						class="form-control form-control-sm">
 						
 						
		<br> Date:
		<input id="date" name="date" type="date"
						 class="form-control form-control-sm">
						 
		<br> No .of Units:
		<input id="nunits" name="nunits" type="text"
						 class="form-control form-control-sm">
						 
		<br> Period:
		<input id="period" name="period" type="text"
						 class="form-control form-control-sm">
						 
		<br> Price per Unit:
		<input id="pricep" name="pricep" type="text"
						 class="form-control form-control-sm">
						 
		<br> Total Price:
		<input id="tprice" name="tprice" type="text"
						 class="form-control form-control-sm">
						 
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
						 class="btn btn-primary">
						 
		<input type="hidden" id="hidUnitIDSave" name="hidUnitIDSave" value="">
	</form>
	
	<br/>
	<!-- Show output -->

	<div id= "alertSuccess" class="alert alert-success"></div>
 	<div id = "alertError" class="alert alert-danger"></div>
	
	<br>
	<div id ="divItemsGrid">
		<%
			 Unit unitObj = new Unit(); 
	 		 out.print(unitObj.readUnits()); 
		%>
    </div>

   </div> 
  </div>
  </div>
  


</body>
</html> 