#if($scrolPosition!="")
	<script>
		setPageLocation('$scrolPosition');
	</script>
#end

$htmlSkrinMaklumat

xxxxxxxxxxxxxxxxxxxx

#parse("app/ppk/BicaraInteraktif/perintah_perbicaraan.jsp")

yyyyyyyyyyyyyyyyyyyy

<script type="text/javascript">
function paparHTA(idHTA) {
	document.Fekptg_view_ppk_BicaraInteraktif.actionPerintah.value = "papar";
	document.Fekptg_view_ppk_BicaraInteraktif.flagPopup.value = "openHTA";
	document.Fekptg_view_ppk_BicaraInteraktif.modePopup.value = "update";
	document.Fekptg_view_ppk_BicaraInteraktif.idHTA.value = idHTA;
	document.Fekptg_view_ppk_BicaraInteraktif.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.Fekptg_view_ppk_BicaraInteraktif.anchor.value = "tabUpper";
	document.Fekptg_view_ppk_BicaraInteraktif.method="POST";
	document.Fekptg_view_ppk_BicaraInteraktif.submit();
}
</script>
<!--
::::::::: $mode
-->

<script>
var skrinName = "$!skrinName";
var flagDisable = document.getElementById("flagDisable").value;
var FLAG_FAIL_SELESAI = document.getElementById("FLAG_FAIL_SELESAI").value;
var PEGAWAIBICARAASLOGIN = document.getElementById("PEGAWAIBICARAASLOGIN").value;
var JAGAAN_MATCH = document.getElementById("JAGAAN_MATCH").value;
//alert('ID_STATUSPERMOHONAN : '+ID_STATUSPERMOHONAN);
//alert("flagDisable : "+flagDisable+" skrinName : "+skrinName+" mode : "+ '$mode');


if(
(
//flagDisable == "Y" &&
'$mode' == "view")
)
{
	hideByClass("wysihtml5-toolbar");
	disableByClass("wysihtml5-sandbox");
	disableInput("view_keputusan");
	//special condition untuk bukak button
	//alert('1');
	if(FLAG_FAIL_SELESAI=="Y"  || PEGAWAIBICARAASLOGIN != "Y")
	{
		$jquery("#view_keputusan input[id=cmdSimpankeputusan]").hide();
	}
	else
	{
		$jquery("#view_keputusan input[id=cmdSimpankeputusan]").show();
	}
	$jquery("#view_keputusan input[id=cmdKePerintahkeputusan]").show();
	//alert('2');
	//$jquery("#view_keputusan input[id=cmdSimpankeputusan]").show();
	//alert('3');
}
else
{

	if(FLAG_FAIL_SELESAI=="Y"  || PEGAWAIBICARAASLOGIN != "Y" || parseInt(JAGAAN_MATCH)==0)
	{
		$jquery("#view_keputusan input[id=cmdSimpankeputusan]").hide();
		$jquery("#view_keputusan input[id=cmdKemaskinikeputusan]").hide();
	}
	else
	{
		$jquery("#view_keputusan input[id=cmdSimpankeputusan]").show();
		$jquery("#view_keputusan input[id=cmdKemaskinikeputusan]").show();
	}
}
//sementara
//$jquery("#view_keputusan input[id=cmdSimpankeputusan]").show();

$jquery(document).ready(function () {
 //alert('x');
 //divToTop("view_keputusan");
	removeClassFromDiv('loadWholePage','loading');
	$jquery('#view_keputusan').scrollView();
 //alert('x2');
});
</script>
