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

		<h2>Electricity Bill Report</h2>
		<hr>
		<div class="row">
			<div class="col-md-4">
				<form name="form1" action="EBController" method="post">

					<div class="form-group">
						<label for="type">State of Residence:</label> <select id="type"
							class="form-control" name="type" disabled>
							<option value="Residential" selected>Residential</option>
							<option value="Non-Residential">Non-Residential</option>
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
						<h6>Your Tax Bill: ${result[1]}</h6>
						<h6>Your Total Bill: ${result[2]}</h6>
						<p></p>
						<h5>Detail Calculation of Your Electricity Bill</h5>
						<h5>****************************************</h5>
						1.First 200 kWh(1-200 kWh): ${result[3]} <br>2.Next 100
						kWh(201-300 kWh): ${result[4]} <br>3.Next 300 kWh(301-600
						kWh): ${result[5]} <br>4.Next 300 kWh(601-900 kWh):
						${result[6]} <br>5.Next 901 kWh onwards: ${result[7]}
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