<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>

<fieldset>

  <legend>Senarai Log Dokumen yg Telah Selesai</legend>
    <table>
  	<tr>
    	<td><a href ="javascript:kembali()" class="style2"><u>Kembali</u></a></td>
   	</tr>
  <table>
  <table width="100%" >
  <tr class="table_header">
    <td>NO</td>
    <td>STATUS DOKUMEN</td>
    <td>NO RUJUKAN DOKUMEN</td>
    <td>TAJUK DOKUMEN</td>
    <td>TARIKH DOKUMEN</td>
    <td>TARIKH TERIMA DOKUMEN</td>
  </tr>
  #foreach ($listLogDokumenByUserId in $SenaraiDokumenByUserId)
  
  #if ($listLogDokumenByUserId.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listLogDokumenByUserId.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listLogDokumenByUserId.bil</td>
    #if ($listLogDokumenByUserId.status_Logdokumen == '1') 
     <td class="$row">DALAM TINDAKAN</td>
    #elseif($listLogDokumenByUserId.status_Logdokumen == '2') 
     <td class="$row">TELAH DILULUSKAN</td>
    #elseif($listLogDokumenByUserId.status_Logdokumen == '3') 
     <td class="$row">TELAH DIPULANGKAN</td>
    #else 
     <td class="$row"></td>
  	#end
    #if($listLogDokumenByUserId.flag_Logdokumen == '1')
    	#if ($listLogDokumenByUserId.bil != '') 
    <td class="$row"><a href="javascript:edit_logDokumen('$listLogDokumenByUserId.idLogDokumenKPTG','$listLogDokumenByUserId.flag_Logdokumen')" class="style1">$listLogDokumenByUserId.no_Rujukan_Lain</a></td>
    	#else
        <td class="$row">$listLogDokumenByUserId.no_Rujukan_Lain</td>   
    	#end
    #elseif($listLogDokumenByUserId.flag_Logdokumen == '2')
     	#if ($listLogDokumenByUserId.bil != '') 
    <td class="$row"><a href="javascript:edit_logDokumenKeluar('$listLogDokumenByUserId.idLogDokumenKPTG','$listLogDokumenByUserId.flag_Logdokumen','$listLogDokumenByUserId.idDokumen')" class="style1">$listLogDokumenByUserId.no_Rujukan_Lain</a></td>
    	#else
        <td class="$row">$listLogDokumenByUserId.no_Rujukan_Lain</td>   
    	#end
    #else
        #if ($listLogDokumenByUserId.bil != '') 
    <td class="$row"><a href="javascript:edit_logDokumenSeksyenKPTG('$listLogDokumenByUserId.idLogDokumenKPTG','$listLogDokumenByUserId.flag_Logdokumen','$listLogDokumenByUserId.idLogDokumenKPTGSekLain')" class="style1">$listLogDokumenByUserId.no_Rujukan_Lain</a></td>
    	#else
        <td class="$row">$listLogDokumenByUserId.no_Rujukan_Lain</td>   
    	#end
    #end
    <td class="$row">$listLogDokumenByUserId.tajuk_Dokumen</td>
	<td class="$row">$listLogDokumenByUserId.tarikh_Dokumen</td>
    <td class="$row">$listLogDokumenByUserId.tarikh_Diterima_Dihantar</td>
  </tr>
  #end
</table>

</fieldset>

<script>
function edit_item(idPergerakan,idFail){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=pengesahanFail";
	document.${formName}.idPergerakanfail.value = idPergerakan;
	document.${formName}.idFail.value = idFail;
	document.${formName}.submit();
}
function edit_logDokumen(idLogDokumen){
	
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.PendaftaranDokumen&pagemode=1&idLogDokumen="+idLogDokumen;
	document.${formName}.submit();
}

function edit_logDokumenSeksyenKPTG(idLogDokumenKPTG,flag_Logdokumen,idLogDokumenKPTGSekLain){

	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenDariSeksyenLainKPTG&idLogDokumenKPTG="+idLogDokumenKPTG+"&idLogDokumenKPTGSekLain="+idLogDokumenKPTGSekLain+"";
	document.${formName}.submit();

}

function edit_agihanTugasan(idLogDokumenKPTG){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=papar&pagemode=1&idLogDokumenKPTG="+idLogDokumenKPTG+"&paparArahan=true";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasanTKPK&action1=";
	document.${formName}.submit();
}
  </script>
