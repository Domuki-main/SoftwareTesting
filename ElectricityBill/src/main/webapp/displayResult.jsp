<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">

		<h2>Electricity Bill Calculator</h2>
		<p style="color: #b50e0e">${NOTIFICATION}</p>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="form1" action="EBController"
					onSubmit="return validateForm()" method="post">


					<c:set var="type" value="${vtype}" />
					<div class="form-group">
						<label for="type">State of Residence:</label> <select id="type"
							class="form-control" name="type" onchange="ShowHideDivSector()"
							disabled>
							<option value="None" disabled>Choose type of customer</option>
							<option value="Residential">Residential</option>
							<option value="Non-Residential" selected>Non-Residential</option>
						</select>
					</div>

					<c:set var="sector" value="${vsector}" />
					<div class="form-group" id="dvSector" style="display: block">
						<label for="sector">Type of Sector:</label> <select id="sector"
							class="form-control" name="sector" disabled>
							<option value="None" disabled>Choose type of sector</option>
							<option value="Commercial"
								<c:if test="${sector=='Commercial'}">selected</c:if>>Commercial</option>
							<option value="Industry"
								<c:if test="${sector=='Industry'}">selected</c:if>>Industry</option>
						</select>
					</div>

					<c:set var="tariff" value="${vtariff}" />
					<div class="form-group" id="dvTariff" style="display: block">
						<label for="tariff">Tariff Code:</label> <select id="tariff"
							class="form-control" name="tariff" disabled>
							<option value="None" disabled>Choose tariff code</option>
							<option value="Tariff B"
								<c:if test="${tariff=='Tariff B'}">selected</c:if>>Tariff
								B: Low Voltage Commercial</option>
							<option value="Tariff C1" disabled>Tariff C1: Medium
								Voltage General Commercial</option>
							<option value="Tariff C2" disabled>Tariff C2: Medium
								Voltage Peak/Off Peak Commercial</option>
						</select>
					</div>


					<div class="form-group">
						Electricity Consumption<input type="text" class="form-control"
							name="econsumption"
							placeholder="Enter the electricity consumption"
							value="${econsumption}" disabled />
					</div>

					<div class="form-group">
						<h5>Estimated Electricity Bill for this Month</h5>
						<h5>****************************************</h5>
						<h6>Your Electricity Bill: ${result[0]}</h6>
						<h6>Your ICPT (RM0.0135 per kWh): ${result[1]}</h6>
						<h6>Your Total Bill: ${result[2]}</h6>
						<p></p>
						<h5>Detail Calculation of Your Electricity Bill</h5>
						<h5>****************************************</h5>
						1.First 200 kWh(1-200 kWh): ${result[3]} <br>2.Next 901 kWh
						onwards: ${result[4]}
					</div>

					<div>

						<input type="button" class="btn btn-danger"
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