
<div id="dummyJanaCatatan"  style="display:none;" >$htmlSkrinMaklumat</div>


<!--

$ID_SIMATI<br />
$ID_PERINTAH<br />
$ID_PERMOHONAN<br />
$ID_PERMOHONANSIMATI<br />
$ID_PERBICARAAN<br />
$ID_PEMOHON<br />
$skrinName<br />

-->

<script>
//alert('x');

//alert("yyyyyyyyyyyyy : "+document.getElementById("dummyJanaCatatan").innerHTML);

 //alert('1');

//$jquery(document).ready(function () {
	//alert('$htmlSkrinMaklumat'.replace(/\>\s+\</g,''))
	/*
	var firstname = document.getElementsByTagName('input')[0],parentHTML = firstname.parentNode.innerHTML,
 newHTML = parentHTML.replace(/\>\s+\</g,'');
	firstname.parentNode.innerHTML = newHTML;
*/
	
	
	//alert("--");
	
	 //alert("1"+document.getElementById("dummyJanaCatatan").innerHTML);
	// document.getElementById("keputusanCATATAN").value = '$htmlSkrinMaklumat';
	  
	 //$jquery("#keputusanCATATAN_PERINTAH_BI").data("wysihtml5").editor.setValue(document.getElementById("dummyJanaCatatan").innerHTML);
	 //$jquery("#keputusanCATATAN_PERINTAH_BI").data("wysihtml5").editor.focus();
	 //alert('1');
	 //$jquery("#keputusanCATATAN_PERINTAH_BI").val(document.getElementById("dummyJanaCatatan").innerHTML);
	 //alert('2');
	 //$jquery("#divViewEditorkeputusanCATATAN_PERINTAH_BI").html(document.getElementById("dummyJanaCatatan").innerHTML);
	 document.getElementById("keputusanCATATAN_PERINTAH_BI").value = document.getElementById("dummyJanaCatatan").innerHTML;
	 document.getElementById("divViewEditorkeputusanCATATAN_PERINTAH_BI").innerHTML = document.getElementById("dummyJanaCatatan").innerHTML;
	 //alert("masuk 1");
	 //document.getElementById("tdDisplayLinkJana").innerHTML = "* <a href=\"javascript:janaCatatanPerintahT('$ID_SIMATI','$ID_PERINTAH','$ID_PERMOHONAN','$ID_PERMOHONANSIMATI','$ID_PERBICARAAN','$ID_PEMOHON','$skrinName');\" ><font color=\"blue\"><u><b>>> Simpan & Jana Catatan Perintah</b></u></font></a> ";
	 //alert("masuk 2");
	 
	 //alert('3');
	 
	 
	// alert('2');
//});
/*
function janaCatatanPerintahT(ID_SIMATI,ID_PERINTAH,ID_PERMOHONAN,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_PEMOHON,skrinName)
{
	doDivAjaxCall$formname('locationJanaCatatan','janaCatatanPerintah','ID_SIMATI='+ID_SIMATI+'&ID_PERINTAH='+ID_PERINTAH+"&ID_PERMOHONAN="+ID_PERMOHONAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PEMOHON="+ID_PEMOHON+"&skrinName="+skrinName);	
}
*/
</script>