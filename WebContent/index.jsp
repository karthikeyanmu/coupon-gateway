<html>
<head>
<style type="text/css">
body {
	text-align: center;
	margin: 50px 0px;
	padding: 0px;
}

#content {
	
}

input{
	margin: 10px 0px;
}

a{
margin: 10px 0px;
text-align: left;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var merchantID=$('#merchant.merchantID').val();
		var couponID=$('#coupon.couponID').val();
		$('#formParam').click(function(){			
			$.post("services/couponPay/validateFormParamAndCharge",{"merchant.merchantID":merchantID,"coupon.couponID":couponID},function(data){
				alert(data);
			});
		});
	});
</script>
</head>
<body>
	<div id="content">
		<h2>Coupon Gateway WebService Test</h2>
		<form action="services/couponPay/validateFormParamAndCharge"
			method="post">
			
				Merchant ID <input id="merchant.merchantID" name="merchant.merchantID" value="4266961000520001" /> <br/>
				Coupon ID <input id="coupon.couponID" name="coupon.couponID" value="4990040730108120004" /> <br/>
				<a id="formParam" href="#">Charge Coupon (Form Param)</a> <br/>
				<div id="formParamRequestReponse"></div>
				<a id="queryParam" href="#">Charge Coupon (Query Param)</a> <br/>
				<div id="queryParamRequestReponse"></div>
				<a id="matrixParam" href="#">Charge Coupon (Matrix Param)</a> <br/>
				<div id="matrixParamRequestReponse"></div>
		</form>
	</div>
</body>
</html>
