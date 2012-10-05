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

.outerborder {
	padding: 10px;
	border-radius: 10px;
	background: #FFFFFF;
	width: 40%;
	margin-right: auto;
	margin-left: auto;
	border: 1px solid #000;
}

.contentarea {
	padding: 10px;
	border-radius: 10px;
	background: #FFFFFF;
	width: 20%;
	margin-right: auto;
	margin-top:60px;
	margin-left: auto;
	border: 1px solid #000;
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
	<div class="outerborder">
		<h2><FONT COLOR=#4297D7>Welcome to Coupon Gateway Merchant Service</FONT></h2>
	</div>
<div class="contentarea">
		<form method="post">
				<span id="errorMessage"></span>
				<span><FONT COLOR=#4297D7>Merchant ID</FONT></span>
				<input id="merchant.merchantID" name="merchant.merchantID" value="4266961000520001" /> <br/>
				<input id="formParam" type="button" value="Login"/>
				
				<input type="hidden" id="merchant.merchantName" name="merchant.merchantName"  />
		</form>
	</div>
</body>
</html>
