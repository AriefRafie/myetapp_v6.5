<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {
	font-size: x-large;
	font-weight: bold;
}
-->
</style>
<body  onload="submitForm();" >
#if($!skrin_deraf == "yes")
	<div align="center" ><font  class="style2">DRAF PERMOHONAN PEMBAHAGIAN PUSAKA KECIL</font></div>
#else
	<div align="center" ><font  class="style2">STATUS PERMOHONAN PEMBAHAGIAN PUSAKA KECIL</font></div>
#end
<input type="hidden" name="v_tab" id="v_tab" value="" />
<input type="hidden" name="id_pemohon" id="id_pemohon" value="$!id_pemohon" />
<table width="100%" cellspacing="2" cellpadding="1" border="0">
	<tr>
		<td colspan="6">
			<!--<input type="button" value="Semakkan Status Berdasarkan MyID/No. KP Pemohon" onclick="searchbyIc()">
			<input type="button" value="Semakkan Status Berdasarkan MyID/No. KP Si Mati" onclick="searchBySiMati()">-->
            
            <fieldset>
<legend><strong>Carian</strong></legend>
<table width="100%" border="0">
  <tr>
    <td width="29%"><div align="right">MyID Pemohon</div></td>
    <td width="1%">:</td>
    <td width="70%"><input type='text' name="kppemohon" value="$!kppemohon"></td>
  </tr>
  <tr>
    <td><div align="right">MyID Simati</div></td>
    <td>:</td>
    <td><input type='text' name="kpsimati"  value="$!kpsimati"></td>
  </tr>
  <tr>
    <td><div align="right">No Fail</div></td>
    <td>:</td>
    <td><input type='text' name="nofail"  value="$!nofail"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="submit" name="cmdCari" id="cmdCari" value="Semak"  >
   </td>
  </tr>
</table>
</fieldset>
            
            
		</td>
	</tr>
</table>
<fieldset>
  <legend>Senarai Permohonan Online</legend>
  #parse("app/utils/record_paging.jsp")
<table width="100%" cellspacing="2" cellpadding="1" border="0" id="header_carian">
	
  <tr class="table_header">
  	<td width="2%" align="center">No</td>
  	<td width="9%"align="center">
    #if($!skrin_deraf == "yes")
    Tarikh Kemasukkan Maklumat Permohonan
    #else
    Tarikh Hantar Permohonan ke Unit Pusaka
    #end
    </td>	
	<td width="14%" align="center">
     #if($!skrin_deraf == "yes")
    Nama Simati
    #else
    No Rujukan Online
    #end
    
    </td>
	<td width="17%" align="center">
    #if($!skrin_deraf == "yes")
    Nama Pemohon
    #else
    No Fail
    #end
    
    </td>
	<td width="4%" align="center">Seksyen</td>
  	<td width="15%">Status Pergerakan Fail</td>
  	#if($!skrin_deraf == "yes")
  	<td width="5%" align="center">Tindakan</td>
  	#end
  </tr>	
    
	#foreach ($senarai in $senaraitugasan )
	#set( $counter = $velocityCount )
	#if ( ($counter % 2) == 0 )
		#set( $row = "row2" )
	#else
		#set( $row = "row1" )
	#end
  <tr>
  <td class="$row" align="center">
  #set ($cnt = ($page - 1) * $itemsPerPage + $counter )
  $!cnt</td>
  <td class="$row" align="center">
  
  <!-- #if( $!senarai.id_pemohon != "" )
  <a href="javascript:papar('$!senarai.id_Permohonan','$!senarai.id_simati','$!senarai.seksyen','$!senarai.id_pemohon','$!senarai.no_subjaket')" class="style1">
	  #if($!skrin_deraf == "yes")
	  $!senarai.tarikhmasuk 
	  #else
	  $!senarai.tarikh_mohon_online
	  #end</a>
  #else
      #if($!skrin_deraf == "yes")
	  $!senarai.tarikhmasuk 
	  #else
	  $!senarai.tarikh_mohon_online
	  #end
  #end -->
  #if($!skrin_deraf == "yes")
  	<a href="javascript:papar('$!senarai.id_Permohonan','$!senarai.id_simati','$!senarai.seksyen','$!senarai.id_pemohon','$!senarai.no_subjaket')" class="style1"><b>$!senarai.tarikhmasuk</b></a>
  #else
  	#if($!senarai.id_pemohon != "")
  	<a href="javascript:papar('$!senarai.id_Permohonan','$!senarai.id_simati','$!senarai.seksyen','$!senarai.id_pemohon','$!senarai.no_subjaket')" class="style1"><b>$!senarai.tarikh_mohon_online</b></a>
  	#else
  	$!senarai.tarikh_mohon_online
  	#end
  #end
  
  </td>
  <!--<td class="$row" align="center"><a href="javascript:edit_item('$!senarai.no')" class="style1">$!senarai.no.toUpperCase()</a></td>-->
  <td class="$row" align="center">
   #if($!skrin_deraf == "yes")
   $!senarai.nama_simati.toUpperCase()
    #else
    $!senarai.no.toUpperCase()
    #end
 </td>
  
  <td class="$row" align="center">
 
  <!-- #if($!skrin_deraf == "yes")
   $!senarai.nama_pemohon.toUpperCase()
    #else
    #if ($senarai.nofail!="")
    #if( $!id_pemohon != "" )
		    <a href="javascript:papar('$!senarai.id_Permohonan','$!senarai.id_simati','$!senarai.seksyen','$!senarai.id_pemohon','$!senarai.no_subjaket')" class="style1">
		  <font color="blue"><b>$!senarai.nofail.toUpperCase()</b> </font>
		    </a>
    #else
    	<font color="blue"><b>$!senarai.nofail.toUpperCase()</b> </font>
    #end
  #else
  	-
  #end  
    #end -->
  
  #if($!skrin_deraf == "yes")
  	$!senarai.nama_pemohon.toUpperCase()
  #else
  	#if ($senarai.nofail!="")
	  	#if($!senarai.id_pemohon != "")
	  		<a href="javascript:papar('$!senarai.id_Permohonan','$!senarai.id_simati','$!senarai.seksyen','$!senarai.id_pemohon','$!senarai.no_subjaket')" class="style1"><font color="blue"><b>$!senarai.nofail.toUpperCase()</b></font>
	  	#else
	  		$!senarai.nofail.toUpperCase()
	  	#end
	#else
		-
	#end
  #end
  
  </td>
  <!--<td class="$row">$!senarai.nokpbarupemohon.toUpperCase()</td>
  <td class="$row">
  #if ($senarai.nokpbarusimati != "")
  	$!senarai.nokpbarusimati.toUpperCase()
  #elseif ($senarai.nokpbarusimati == "" && $senarai.nokplamasimati != "")
  	$!senarai.nokplamasimati.toUpperCase()
  #elseif ($senarai.nokpbarusimati == "" && $senarai.nokplamasimati == "" && $senarai.nokplainsimati != "")
   $!senarai.nokplainsimati.toUpperCase()
  #end</td>-->
  <td class="$row" align="center">$senarai.seksyen</td>
  <td class="$row">$senarai.status</td>
  #if($!skrin_deraf == "yes")
  <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:doHapus('$!senarai.idFail','$!senarai.id_Permohonan')"><img border="0" src="../img/hapus.gif"/></a></td>
 #end
  </tr>
  #end
		#if ($cnt == 0)
		<tr> 
			<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod</font></td>
		</tr>
		#end
  </table>
    <input type="hidden" name="hitButt" >
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
	<input type="hidden" name="nopermohonan" >
	<input type="hidden" name="typez" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >
	<input type="hidden" name="page" value="$page">
    
    <input type="hidden" name="idpermohonan" id="idpermohonan">    
    <input type="hidden" name="idPermohonan" id="idPermohonan">  
    <input type="hidden" name="idSimati" id="idSimati">
    <input type="hidden" name="idPemohon" id="idPemohon">
    
    <input type="hidden" name="v_tab" id="v_tab"  />
    <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$!flagFromSenaraiFailSek8"/>
    <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$!flagFromSenaraiPermohonanSek8"/>
    <input name="flagForView" type="hidden" id="flagForView" value="$!flagForView"/>
    <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
    <input type="hidden" name="id_Permohonansimati" id="id_Permohonansimati" value="$!id_Permohonansimati" >
    
     <input name="tabIdatas" type="hidden" id="tabIdatas" value="$!selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$!selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$!selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$!selectedTabtepi"/>
 
  <input name="no_subjaket" type="hidden" id="no_subjaket" value="$!no_subjaket"/>
    
  <input type="hidden" name="idFail">  
    
    
</fieldset>

</body>

<script>

function submitForm() {    
//alert('$val_tab')
if('$!val_tab' != "" && '$!val_tab' != null)
{
   window.location.hash='$!val_tab';
   goTo('$!val_tab');
   }
   else
{
window.location.hash='header_carian';
goTo('header_carian');
}
	
} 

function papar(idPermohonan,idSimati,seksyen,idpemohon,no_subjaket) {

			
if (seksyen == '8'){
//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
document.${formName}.action = "$EkptgUtil.getTabID('Pusaka Kecil',$myrole)?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon&command=Simati&mode=Simatiview";
}else
{
//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
//document.${formName}.action = "?_portal_module=FrmPrmhnnSek17Senarai&command=tab";
document.${formName}.action = "$EkptgUtil.getTabID('Pusaka Kecil',$myrole)?_portal_module=ekptg.view.ppk.FrmBorangPSek17Online&command=Simati&mode=Simatiview";

}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.idPermohonan.value = idPermohonan;
				document.${formName}.idPemohon.value = idpemohon;
				document.${formName}.idSimati.value = idSimati;
				document.${formName}.no_subjaket.value = parseInt(no_subjaket) - 1;
				
				/*
				
				document.f1.command.value = "papar";
	document.f1.action = "";
	document.f1.idpermohonan.value = id;
	document.f1.idSimati.value = id2;
	document.f1.no_subjaket.value = ns;
	document.f1.submit();
				*/
				
				
			   /* document.${formName}.tabIdatas.value = 0;
				document.${formName}.tabIdtengah.value = 0;
				document.${formName}.tabIdbawah.value = 0;	
				document.${formName}.tabIdtepi.value = 0;*/
				
				document.${formName}.method="POST";
		        document.${formName}.submit();
}

function edit_item(id) {
		var typeid = id.substring(9,11);
		if (typeid == "02"){
		document.${formname}.method="post";
		document.${formname}.nopermohonan.value=id;
		document.${formname}.typez.value="online";		
		//doAjaxCall${formName}("check_kp17","typez=online&nopermohonan="+id);
		doAjaxCall${formName}("check_kp17");
		document.${formname}.submit();
		}else if (typeid == "01"){
			document.${formname}.method="post";
			document.${formname}.nopermohonan.value=id;
			document.${formname}.typez.value="online";
			document.${formname}.action="$EkptgUtil.getTabID('Panduan','online')?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAKemaskini";
			//document.${formname}.action="$EkptgUtil.getTabID('Panduan','online')?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
			//doAjaxCall${formName}("check_kp","typez=online&nopermohonan="+id);
			doAjaxCall${formName}("check_kp");
			document.${formname}.submit();
		}
}
function searchbyIc(){
	document.${formname}.command.value="searchByIc";
	
	doAjaxCall${formName}("searchByIc");
}
function searchBySiMati(){
	doAjaxCall${formName}("searchByIcSiMati");
}
function cariICSiMati(){
	doAjaxCall${formName}("cariSiMati");
}
function cariIC(){
	doAjaxCall${formName}("cariIc");
}
function menuUtama(){
	doAjaxCall${formName}("menuUtama");
}
function doChanges() {
	
	doAjaxCall${formName}("doChanges");
}
function doHapus(idFail,idPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formname}.idPermohonan.value=idPermohonan;
	document.${formname}.idFail.value=idFail;
	doAjaxCall${formName}("doHapus");
	document.${formname}.submit();
}
</script>
