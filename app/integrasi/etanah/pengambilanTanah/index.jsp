<style type="text/css">
<!--
#parse("css/eTapp_PPT.css")
-->
</style>


<div style="display:none;" > </div> 

<input type="text" name="form_token" value='$!{session.getAttribute("form_token")}'>
id_fail :
<input type="text" name="id_fail" value="$!id_fail">
<br>
id_permohonan :
<input type="text" name="id_permohonan" value="$!id_permohonan">
<br>
id_hakmilik :
<input type="text" name="id_hakmilik" value="$!id_hakmilik">
<br>
jenis_skrin :
<input type="text" name="jenis_skrin" value="$!jenis_skrin">

<br>
id_penarikan :
<input type="text" name="id_penarikan" value="$!id_penarikan">



 
 
 
  #set($tajuk_popup = "")
  #if($!jenis_skrin == "BorangD")         
  	#set($tajuk_popup = "SKRIN INTEGRASI BORANG D")
  #elseif($!jenis_skrin == "BorangB")         
  	#set($tajuk_popup = "SKRIN INTEGRASI BORANG B")
  #elseif($!jenis_skrin == "BorangK")         
  	#set($tajuk_popup = "SKRIN INTEGRASI BORANG K")
  #elseif($!jenis_skrin == "hantarPelanChartingS8")         
  	#set($tajuk_popup = "SKRIN MUAT NAIK PELAN")
  #elseif($!jenis_skrin == "hantarPelanChartingS4")         
  	#set($tajuk_popup = "SKRIN MUAT NAIK PELAN")
  #elseif($!jenis_skrin == "PU")         
  	#set($tajuk_popup = "SKRIN INTEGRASI PELAN PA & B1")  
  #elseif($!jenis_skrin == "BorangI")         
  	#set($tajuk_popup = "SKRIN INTEGRASI BORANG I")  
  #elseif($!jenis_skrin == "TarikBalik")         
  	#set($tajuk_popup = "SKRIN INTEGRASI PENARIKAN BALIK")  
  #end
 
 
<fieldset>
<legend><strong>$tajuk_popup</strong></legend>
<table border="0" width="100%"  > 
<tr  >
<td valign="top" >
#parse("app/integrasi/etanah/pengambilanTanah/MaklumatIntegrasi.jsp")

</td>
</tr>
</table>
</fieldset>


 <div id="divPaparProgress">:::::
    
    $!displayStat
    
    </div>
	
     <input type="button" name="cmdHantarRekod" id="cmdHantarRekod" value="Hantar Rekod" onClick="hantar('$id_permohonan','$jenis_skrin')">
     
     <input type="button" name="butPaparStat" id="butPaparStat" value="TEST" onClick="paparStat('$id_permohonan','$jenis_skrin')">





<script>


paparStat('$id_permohonan','$jenis_skrin');


function displayListDokumen(id_permohonan,jenis_skrin) {
	alert("sss");
	//alert("divListDokumen");
	//doDivAjaxCall$formname('divListDokumen','paparStat','hitButton=displayListDokumen');
	//doDivAjaxCall$formname('divPaparProgress','paparStat','hitButton=paparStat');
	//doAjaxCall${formname}('paparStat','');
	
}


function paparStat(id_permohonan,jenis_skrin) {

	
	alert('6');
	
	doDivAjaxCall$formname('divPaparProgress','paparStat','hitButton=paparStat');
	//doAjaxCall${formname}('paparStat','');
	
	
		alert('q');
	
	
}

function hantar(id_permohonan,jenis_skrin) {
	
	

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	var id_permohonan_set =  document.${formName}.id_permohonan.value;
	var id_fail_set =  document.${formName}.id_fail.value;
	var jenis_skrin_set =  document.${formName}.jenis_skrin.value;
	var id_hakmilik_set =  document.${formName}.id_hakmilik.value;
	
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah&hitButton=hantarData"+dopost+"&id_permohonan="+id_permohonan_set+"&jenis_skrin="+jenis_skrin_set+"&id_fail="+id_fail_set+"&id_hakmilik="+id_hakmilik_set;
	
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}

function paparLampiran(id_dokumen) {
    var url = "../../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function kembali() {	
	window.close();
}
function simpan(id_permohonan,jenis_skrin) {
	
	if(document.${formName}.tajuk.value == ""){
		alert('Sila pastikan tajuk dokumen diisi.');
  		document.${formName}.tajuk.focus(); 
		return; 
	}
	
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Lampiran yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}
	
	

	
	
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	var id_permohonan_set =  document.${formName}.id_permohonan.value;
	var id_fail_set =  document.${formName}.id_fail.value;
	var jenis_skrin_set =  document.${formName}.jenis_skrin.value;
	var tajuk_set =  document.${formName}.tajuk.value;
	var id_hakmilik_set =  document.${formName}.id_hakmilik.value;
	
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah&hitButton=simpanDokumen&id_permohonan="+id_permohonan_set+"&jenis_skrin="+jenis_skrin_set+""+dopost+"&id_fail="+id_fail_set+"&tajuk="+tajuk_set+"&id_hakmilik="+id_hakmilik_set;
	
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}

function hapusDokumen(id_dokumen) {
	
	

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	alert("1");
	var dp = document.${formName}.form_token.value ;
	alert("token:"+dp);
	var dopost = "&form_token="+dp;
	var id_permohonan_set =  document.${formName}.id_permohonan.value;
	var id_fail_set =  document.${formName}.id_fail.value;
	var jenis_skrin_set =  document.${formName}.jenis_skrin.value;
	var id_hakmilik_set =  document.${formName}.id_hakmilik.value;
	alert("2");
	document.${formName}.action = "?_portal_module=ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah&hitButton=hapusDokumen&id_dokumen="+id_dokumen+""+dopost+"&id_permohonan="+id_permohonan_set+"&jenis_skrin="+jenis_skrin_set+"&id_fail="+id_fail_set+"&id_hakmilik="+id_hakmilik_set;
	
	document.${formName}.method="post";
	//document.${formName}.enctype="multipart/form-data";
    //document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}

function closeWin(){
	window.opener.refreshFromMuatNaikLampiran();
	window.close();
}


</script>
