<html>
<head>
<!-- 
# Auther : zulfazdliabuas@gmail.com
# Penerangan : Untuk mobile bila taip pada browser myetapp.gov.my akan default ke page ini dahulu. 
-->

<style>

body{
	 /* background: url(./img/bexy.jpg);
	 background-size: cover; */
	 
background: #070000;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #96738b, #ce8f7d, #927385);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #96738b, #ce8f7d, #927385); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	 
	 
}

.text-shadow{
	text-shadow:2px 2px #b5b5b5;
}

.big-btn{
	padding: 10px 0px 11px 0px;
	width: 250px;
	margin-top:5px;
}

.big-btn i{
	font-size: 65px;
	margin-right: 0px !important;
}
.big-btn strong{
	/* font-size: 25px;
	text-shadow: 1px 2px 0px #34659B; */
	font-size: 80;
	text-shadow: 6px 9px 2px #34659B;
	color: #fff;
	line-height: 70px;
}
.big-btn .btn-text{
	/* margin-top:6px; */
}

.big-btn small{
	font-size: 60px;
}

/* MYETAPP */
.myetapp-btn{
	background-color:#008A00;
	width: 250px;
	padding: 13px 5px 13px 10px;
	border-color: #055805;
}
.myetapp-btn:hover{
	background-color: #008A00;
}
.myetapp-btn .btn-text{
	text-shadow: none;
	font-size: 26px;
	/* line-height: 25px; 
	line-height: 120px;*/
}

/* ONLINE */
.online-btn{
	width: 250px;
	padding: 13px 17px 13px 0px;
	background-color: #139675 !important;
	border-color: #239276 !important;
}
.online-btn:hover{
	background-color: #246f5c !important;
}


/* ANDROID */
.android-btn{
	width: 250px;
	padding: 13px 17px 13px 0px;
	background-color: #000 !important;
	border-color: #000 !important;
}
.android-btn:hover{
	background-color: #171616 !important;
}
/* .android-btn i{
	font-size: 55px;
}
.android-btn .btn-text{
	margin-top: 0px;
}
.android-btn strong{
	font-size: 25px;
	text-shadow: none;
	color: #fff;
	line-height: 24px;
} */


/* APPLE BUTTON */
.apple-btn{
	width: 250px;
	padding: 13px 17px 13px 0px;
	background-color: #000 !important;
	border-color: #000 !important;
}
.apple-btn:hover{
	background-color: #171616 !important;
}


/* MOBILE VIEW */
.mobile-view-btn{
	background-color:#008A00;
	width: 250px;
	padding: 13px 5px 13px 10px;
	border-color: #055805;
}

.btn-lg{
	width:100%
}


</style>

<link type="text/css" rel="stylesheet" href="./css/bootstrap.css" />
<script type="text/javascript">

	function linkToSistem() {
	    //location.replace("http://poc.myetapp.gov.my/myetapp_HQ/login.jsp")
	    location.replace("login.jsp")
	}

	function linkToSistemOnline(){
		//location.replace("http://poc.myetapp.gov.my/myetapp_HQ/online/")
		location.replace("online/")
	}

	function linkToDownloadApp() {
	    
	    var iOS = !!navigator.platform && /iPad|iPhone|iPod/.test(navigator.platform);
	    //alert("iOS :: " + iOS);
	    if (iOS == false){
	    	//disable sekejap sebelum Link sebenar dapat dari mampu
			location.replace("https://play.google.com/store/apps/details?id=com.google.android.googlequicksearchbox") //pastikan replace link kepada link Application dalam google play
		}else if(iOS == true){
			//disable sekejap sebelum Link sebenar dapat dari mampu
			location.replace("https://itunes.apple.com/us/app/gmail-email-by-google-secure-fast-organized/id422689480?mt=8") // pastikan replace link kepada apple store
		}
	    
	}

	function chackPlatform(){
		
		var apple_link = document.getElementById('apple_link');
		var android_link = document.getElementById('android_link');
	   
	    var iOS = !!navigator.platform && /iPad|iPhone|iPod/.test(navigator.platform);
	    //var iOS = true;
	    if(iOS == true){
	    	android_link.style.display = 'none';
	    	apple_link.style.display = '';
	    }else if( iOS == false){
	    	android_link.style.display = '';
	    	apple_link.style.display = 'none';
	    }
	   
	}

	function linkToPaparanMobile(){
		//location.replace("http://myetapp.gov.my/myetapp_app") //link live
		//location.replace("http://poc.myetapp.gov.my/myetapp_app") //link poc
		location.replace("myetapp_app")
	}

</script>

</head>

<body onload="chackPlatform()">

	<div class="container"> 
	
		<div class="row">
			<div class="col-md-12"><br/><br/><br/></div>
		</div>
		
		<div class="row">
		   <!-- <div class="col-md-3"></div>
		    <div class="col-md-6"> -->
		    <div class="col-md-12">
		    	<div class="text-center"><logo><img src="./img/logo_myetapp.png" width="100%"></logo></div>
		    	<h1 class="text-center">
		    		<p class="text-shadow">JABATAN KETUA PENGARAH <br /> TANAH DAN GALIAN PERSEKUTUAN</p>
		    	</h1>
		    </div>
		    <!-- <div class="col-md-3"></div> -->
		</div>
		
		<div class="row">
			<div class="col-md-12"><br/><br/><br/></div>
		</div>
		
	    <div class="row">
	        <div class="col-md-12">
				<div class="text-center">
				
					<!-- Star MYETAPP -->
					<div id="myetapp">
						<a onclick="linkToSistem()" class="btn btn-lg btn-success big-btn myetapp-btn col-md-12">
							<div class="col-md-12">
							<img width="30%" class="pull-left" src="./img/computer_screen.png">
							<div class="btn-text">
								<small>Sistem</small><br>
								<strong>MyeTaPP</strong>
								<h1>Internal</h1>
							</div>
							</div>
						</a>
					</div>
					<!-- End MYETAPP -->
					
					<!-- Start SISTEM ONLINE -->
				  	<div id="myetapp_online">
						<a onclick="linkToSistemOnline()" class="btn btn-lg btn-success big-btn online-btn">
							<img width="30%" class="pull-left" src="./img/computer_screen.png">
							<div class="btn-text">
								<small>MyeTaPP</small><br>
								<strong>Online</strong>
								<h1>Orang Awam</h1>
							</div>
						</a>
        			</div>
	        		<!-- End SISTEM ONLINE -->
					
					<!-- Start DOWNLOAD -->
					<div id="apple_link"><!-- Apple store button -->
						<a onclick="linkToDownloadApp()" class="btn btn-lg btn-primary big-btn apple-btn">
							<img width="30%" class="pull-left" src="./img/apple_logo.png">
							<div class="btn-text">
								<!-- <small>Available on the</small><br> -->
								<small>Aplikasi MyeTaPP</small><br>
								<strong>App Store</strong>
								<h1>Muat Turun Aplikasi</h1>
							</div>
						</a>
					</div>
					
				  	<div id="android_link"><!-- Android button -->
						<a onclick="linkToDownloadApp()" class="btn btn-lg btn-success big-btn android-btn">
							<img width="30%" class="pull-left" src="./img/google_play_logo.png">
							<div class="btn-text">
								<!-- <small>Available on</small><br> -->
								<small>Aplikasi MyeTaPP</small><br>
								<strong>Google Play</strong>
								<h1>Muat Turun Aplikasi</h1>
							</div>
						</a>
        			</div>
	        		<!-- End DOWNLOAD -->
	        
	        		<!-- Start MOBILE VIEW -->
	        		<div id="mobile_view">
						<a onclick="linkToPaparanMobile()" class="btn btn-lg btn-primary big-btn mobile-view-btn">
							<img width="30%" class="pull-left" src="./img/iphone_logo.png">
							<div class="btn-text">
								<small>Paparan</small><br>
								<strong>Mobile</strong>
								<!-- <h1>Paparan Mobile</h1> -->
							</div>
						</a>
					</div>
					<!-- End MOBILE VIEW -->
					    
				</div>
	        </div>
	    </div>
		
	</div>
</body>
</html>