<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Merchant View</title>
<style type="text/css">



.outerborder {
	padding: 10px;
	border-radius: 10px;
	background: #FFFFFF;
	width: 60%;
	margin-right: auto;
	margin-left: auto;
	border: 1px solid #000;
}

.contentarea {
	padding: 10px;
	border-radius: 10px;
	background: #FFFFFF;
	width: 25%;
	margin-right: auto;
	margin-top: 60px;
	margin-left: auto;
	border: 1px solid #000;
}

body {	
	margin: 50px 0px;	
	text-align: center;
}

#content {
	
}
</style>
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery.json-2.3.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/jquery-ui-1.8.24.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
<script type="text/javascript" src="js/grid.locale-en.js"></script>
<script type="text/javascript" src="js/jquery.jqGrid.min.js"></script>
<script type="text/javascript">
var merchantID=null;
var orderNo=null;	
	$(document).ready(function(){
		merchantID=$('#merchant\\.merchantID').val();
		orderNo=$('#orderNo').val();	
		
		$("#couponList").jqGrid({
			autowidth : false,
			loadonce:true,
		   	url:'services/merchantService/getCouponList',
		   	serializeGridData: function (postData){			
		   		//alert(merchantID+" JNSON"+ $.toJSON( postData ));
				//alert('merchant\\.merchantID'+merchantID);
				return postData;	
				},
			datatype: "json",
			postData:{"merchant.merchantID":merchantID,"orderNo":0},
			mtype: 'POST',
		   	colNames:['Order Number','Coupon Number','Coupon Value','Transaction Date','Status'],
		   	colModel:[
				{name:'orderNo',index:'orderNo',key:true},      
		   		{name:'coupon.couponID',index:'coupon.couponID',key:true},
		   		{name:'coupon.couponValue',index:'coupon.couponValue'},
		   		{name:'transDate',index:'transDate'},
		   		{name:'status.statusName',index:'status.statusName'}		
		   	],
		   	rowNum:10,
		   	rowList:[5,10,20,50,100],
		   	emptyrecords:"No records To View",
		   	pager: '#pager',
		   	sortname: 'id',
		    viewrecords: true,
		    sortorder: "desc",
		    caption:"Coupon",		    
			jsonReader : {
		        repeatitems : false
		    }
		});
		$("#couponList").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});
		
		$('#chargeCoupon').click(function(){
			var newCoupon=$('#newCoupon').val();
			var merchantID=$('#merchant\\.merchantID').val();
			$.post("services/couponPay/validateFormParamAndCharge",{"merchant.merchantID":merchantID,"coupon.couponID":newCoupon},function(data){				
				refreshGrid('formParam',data);				
			});			
		});
		
		$('#search').click(function(){
			merchantID=$('#merchant\\.merchantID').val();
			orderNo=$('#orderNo').val();
			$("#couponList").jqGrid('setGridParam',{url:"services/merchantService/getCouponList", datatype:"json",postData:{"merchant.merchantID":merchantID,"orderNo":orderNo}, page:1}).trigger("reloadGrid");
		});
	});
	
	window.setTimeout( autoRefreshGrid, 5000);
	
	function autoRefreshGrid(){		
		merchantID=$('#merchant\\.merchantID').val();
		orderNo=$('#orderNo').val();
		$("#couponList").jqGrid('setGridParam',{url:"services/merchantService/getCouponList", datatype:"json",postData:{"merchant.merchantID":merchantID,"orderNo":0}, page:1}).trigger("reloadGrid");
		window.setTimeout( autoRefreshGrid, 5000);
	}
	
	function refreshGrid(idName,data){
		var jsonData=$.toJSON( data );
		//alert(jsonData);
		if(data){
			merchantID=$('#merchant\\.merchantID').val();
			orderNo=$('#orderNo').val();
			$("#couponList").jqGrid('setGridParam',{url:"services/merchantService/getCouponList", datatype:"json",postData:{"merchant.merchantID":merchantID,"orderNo":0}, page:1}).trigger("reloadGrid");
		}else{
			alert();
		}
	}
</script>
</head>
<body>
	<div id="content">
		<div class="outerborder">
			<h2>
				<FONT COLOR=#4297D7>Welcome to Coupon Gateway Merchant
					Service</FONT>
			</h2>
		</div>
		<br/>		
		<div id="" style="margin-left: 28%;">
			<h5><FONT COLOR=#4297D7>Welcome <%=request.getParameter("merchant.merchantID")%>
					- <%=request.getParameter("merchant.merchantName")%></FONT></h5>
		</div>
		<form method="post">
				<input type="hidden" id="merchant.merchantID" name="merchant.merchantID" value="<%= request.getParameter("merchant.merchantID")%>"/>
				<div id="" style="margin-top: 30px;">
				<h4>
					<FONT COLOR=#4297D7>Charge Coupon</FONT>
				</h4>
				<span><FONT COLOR=#4297D7>Coupon Number</FONT></span> <input
					id="newCoupon" name="newCoupon" /> <input id="chargeCoupon"
					type="button" value="Charge" />
			</div>

			<div id="" style="margin-top: 30px;">
				<h4>
					<FONT COLOR=#4297D7>Search</FONT>
				</h4>
				<span><FONT COLOR=#4297D7>Order Number</FONT></span> <input
					type="text" id="orderNo" name="orderNo" /> <input id="search"
					type="button" value="Search" />
			</div>


			<div id="" style="margin-top: 30px;">
				<h4>
					<FONT COLOR=#4297D7>Search Result</FONT>
				</h4>
			</div>
			<div id="" style="margin-left: 25%;">
				<table id="couponList"></table>
				<div id="pager"></div>
			</div>
		</form>
	</div>
</body>
</html>
