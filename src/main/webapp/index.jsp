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
	margin: 20px 0px;
}

a{
margin: 10px 0px;
text-align: left;
}

#errorMessage{
color:red;
margin: 20px 0px;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery.json-2.3.js"></script>
<script type="text/javascript">
	$(document).ready(function(){		
		$('#formParam').click(function(){			
			var merchantID=$('#merchant\\.merchantID').val();	
			$.post("services/merchantService/authenticateMerchant",{"merchantID":merchantID},function(data){				
				displayResponse('formParam',data);
			});
		});
	});
	
	function displayResponse(idName,data){
		//var jsonData=$.toJSON( data );		
		if(data){
			if(data.status.statusID=='A'){				
				$('#merchant\\.merchantName').val(data.merchantName);				
				$('form').attr('action','merchantview.jsp');
				$('form').submit();
			}else{
				$('#errorMessage').text('Merchant Id is inactive');
			}
		}else{
			$('#errorMessage').text('Merchant Id is invalid');
		}
	}
</script>
</head>
<body>
	<div id="content">
		<h2>Welcome to Coupon Gateway Merchant Service</h2>
		<form method="post">
				<span id="errorMessage"></span>
				<span>Merchant ID</span>
				<input id="merchant.merchantID" name="merchant.merchantID" value="4266961000520001" /> <br/>
				<input id="formParam" type="button" value="Login"/>
				
				<input type="hidden" id="merchant.merchantName" name="merchant.merchantName"  />
		</form>
	</div>
</body>
</html>
