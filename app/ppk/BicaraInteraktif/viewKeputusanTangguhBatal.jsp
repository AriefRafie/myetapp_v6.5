#if($scrolPosition!="")
     <script>
     setPageLocation('$scrolPosition');
     </script>
#end



$htmlSkrinMaklumat
<!--
::::::::: $mode
-->
<!--
::: $div
::: $command
::: $skrinName
-->
<script>

var skrinName = "$!skrinName";
var flagDisable = document.getElementById("flagDisable").value;
var FLAG_FAIL_SELESAI = document.getElementById("FLAG_FAIL_SELESAI").value;
var PEGAWAIBICARAASLOGIN = document.getElementById("PEGAWAIBICARAASLOGIN").value;	
var JAGAAN_MATCH = document.getElementById("JAGAAN_MATCH").value;
//alert("flagDisable : "+flagDisable+" skrinName : "+skrinName+" mode : "+ '$mode');
//alert('ID_STATUSPERMOHONAN : '+ID_STATUSPERMOHONAN);
if(
(
//flagDisable == "Y" && 
'$mode' == "view")
)
{	
	//alert('1');
	hideByClass("wysihtml5-toolbar");
	disableByClass("wysihtml5-sandbox");
	disableInput("divSkrinSuplimentTangguhBatal");	
	//special condition untuk bukak button
	//alert('1');
	/*
	if(FLAG_FAIL_SELESAI=="Y" && PEGAWAIBICARAASLOGIN != "Y")
	{
		$jquery("#view_keputusan input[id=cmdSimpankeputusan]").hide();
	}
	else
	{
		$jquery("#view_keputusan input[id=cmdSimpankeputusan]").show();
	}
	*/
	//alert('2');
	//$jquery("#view_keputusan input[id=cmdSimpankeputusan]").show();
	//alert('3');
}


if(FLAG_FAIL_SELESAI=="Y" || PEGAWAIBICARAASLOGIN != "Y" || parseInt(JAGAAN_MATCH)==0)
{
	$jquery("#view_keputusan input[id=cmdSimpankeputusan]").hide();
}
else
{
	$jquery("#view_keputusan input[id=cmdSimpankeputusan]").show();
}


$jquery(document).ready(function () {
 //alert('x');
 //divToTop("view_keputusan");
 removeClassFromDiv('loadWholePage','loading');
 $jquery('#divSkrinSuplimentTangguhBatal').scrollView();
 //alert('x2');
});

</script>
