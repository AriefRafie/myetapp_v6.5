<style>



@media all {
	.page-break	{ display: none; }
}

@media print {
	
	.page-break	{ display: block; page-break-before: always;}

}

.classFade{
    
    animation: fadein 2s;
    -moz-animation: fadein 2s; /* Firefox */
    -webkit-animation: fadein 2s; /* Safari and Chrome */
    -o-animation: fadein 2s; /* Opera */
}

.classFade1{
    
    animation: fadein 2s;
    -moz-animation: fadein 2s; /* Firefox */
    -webkit-animation: fadein 2s; /* Safari and Chrome */
    -o-animation: fadein 2s; /* Opera */
}

.classFade2{
    
    animation: fadein 0s;
    -moz-animation: fadein 0s; /* Firefox */
    -webkit-animation: fadein 0s; /* Safari and Chrome */
    -o-animation: fadein 0s; /* Opera */
}



@keyframes fadein {
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-moz-keyframes fadein { /* Firefox */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-webkit-keyframes fadein { /* Safari and Chrome */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-o-keyframes fadein { /* Opera */
    from {
        opacity:0;
    }
    to {
        opacity: 1;
    }
}



.underline_td_tajuk {
	border-bottom: 1px solid #000;
    padding: 3px 1px;    
}


.underline_td_main {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 120%;
    color : white;
    text-shadow: 2px 2px 5px black;
}
.underline_td_sub {
	border-bottom: 1px solid #000;
    padding: 3px 1px;
    font-size: 100%;
    color : white;
    text-shadow: 2px 2px 5px black;
}

  
.blink {
  animation: blink-animation 1s steps(5, start) infinite;
  -webkit-animation: blink-animation 1s steps(5, start) infinite;
}
@keyframes blink-animation {
  to {
    visibility: hidden;
  }
}
@-webkit-keyframes blink-animation {
  to {
    visibility: hidden;
  }
}
</style>


<div id="div_carian" >
#parse("app/admin/Dashboard/dashboard.jsp")
</div>


<script>

function gotoFLMSstat() {
		document.${formName}.action = "$EkptgUtil.getTabID("PLA",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=paparLaporan";
		document.${formName}.method="POST";
		document.${formName}.submit();
	}
	
function gotoProfile() {
		document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.admin.UserProfileInternal";
		document.${formName}.method="POST";
		document.${formName}.submit();
	}


function paparAduan(){
        document.${formName}.action = "$EkptgUtil.getTabID("PLA",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
		document.${formName}.method="POST";
		document.${formName}.submit();
}


function userPermohonan() {
	document.${formName}.action = "$EkptgUtil.getTabID("User Management",$portal_role)?_portal_module=ekptg.view.admin.UserListModuleV3";
   // document.${formName}.action = "$EkptgUtil.getTabID("'User Management'",$portal_role)?_portal_module=ekptg.view.admin.UserListModuleV3";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function getPageLocation(val)
{
	var tempScrollTop = $jquery(window).scrollTop();
	//alert("tempScrollTop :"+tempScrollTop);
	$jquery('#scrolPosition'+val).val(tempScrollTop);
	//document.getElementById("scrolPosition").value = tempScrollTop;
	return tempScrollTop;
}

function gotoPanduan() {
		document.${formName}.action = "$EkptgUtil.getTabID("Panduan",$portal_role)?_portal_module=ekptg.view.FrmManualPenggunaAll";
		document.${formName}.method="POST";
		document.${formName}.submit();
	}
</script>
