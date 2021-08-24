<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<script type="text/javascript">
	function ShowHideDivSector() {
		var chktype = document.getElementById("type");
		var dvSector = document.getElementById("dvSector");
		dvSector.style.display = chktype.value == "Non-Residential" ? "block"
				: "none";
	}
	
	function ShowHideDivTariff() {
		var chktype = document.getElementById("sector");
		var dvTariff = document.getElementById("dvTariff");
		dvTariff.style.display = chktype.value == "Commercial" ? "block"
				: "none";
	}
</script>

</head>
<body>
	<div class="container">

		<h2>Electricity Bill Calculator</h2>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="form1" action="EBController" method="post">

					<div class="form-group">
						<label for="type">Type of Residential:</label> <select required id="type"
							class="form-control" name="type" onchange="ShowHideDivSector()">				
							<option hidden="" disabled="disabled" selected="selected" value="">Choose type of customer</option>
							<option value="Residential">Residential</option>
							<option value="Non-Residential">Non-Residential</option>
						</select>
					</div>

					<div class="form-group" id="dvSector" style="display: none">
						<label for="sector">Type of Sector:</label> <select  id="sector"
							class="form-control" name="sector" onchange="ShowHideDivTariff()">
							<option hidden="" disabled="disabled" selected="selected" value="">Choose type of sector</option>		
							<option value="Commercial">Commercial</option>
							<option value="Industry" disabled>Industry</option>
						</select>
					</div>
					
					<div class="form-group" id="dvTariff" style="display: none">
						<label for="tariff">Tariff Code:</label> <select  id="tariff"
							class="form-control" name="tariff">
							<option hidden="" disabled="disabled" selected="selected" value="">Choose tariff code</option>		
							<option value="Tariff B">Tariff B: Low Voltage Commercial</option>
							<option value="Tariff C1" disabled>Tariff C1: Medium Voltage General Commercial</option>
							<option value="Tariff C2" disabled>Tariff C2: Medium Voltage Peak/Off Peak Commercial</option>
						</select>
					</div>
					

					<div class="form-group">
						Electricity Consumption<input type="number" class="form-control"
							name="econsumption"
							placeholder="Enter the electricity consumption" required/>
					</div>

					<div>
						<input type="submit" class="btn btn-success" name="Calculate"
							value="Calculate" /> <input type="reset" class="btn btn-success"
							name="reset" value="Reset" /> <input type="button"
							class="btn btn-success"
							onclick="window.location.href = '/ElectricityBill/index.jsp'"
							value="Back" /> <br>
						<script type="text/javascript">
							document.write("<p>" + Date() + "</p>");
						</script>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>