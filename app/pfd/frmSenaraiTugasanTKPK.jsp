<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>
#if ($pagemode == "1")
#parse("app/pfd/frmLogDokumenKPTGSelesai.jsp")
#elseif($pagemode == "2")
#parse("app/pfd/frmLogAgihanTugasanKPTGSelesai.jsp")
#else
<fieldset>
  <legend>Senarai Log Dokumen</legend>
    <table>
  	<tr>
    	<td><a href ="javascript:selesai_logDokumen()" class="style2"><u>Log Dokumen Yg Telah Selesai</u></a></td>
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
    #if ($listLogDokumenByUserId.status_Logdokumen == '0') 
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
    <td class="$row"><a href="javascript:edit_logDokumenKeluar('$listLogDokumenByUserId.idLogDokumenKPTG','$listLogDokumenByUserId.flag_Logdokumen','$listLogDokumenByUserId.idLogDokumenKPTGSekLain')" class="style1">$listLogDokumenByUserId.no_Rujukan_Lain</a></td>
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
<br>
<fieldset>
  <legend>Senarai Agihan Tugasan Ketua Pengarah / Timb. Ketua Pengarah</legend>
<table>
  	<tr>
    	<td><a href ="javascript:selesai_AgihanTugasan()" class="style2"><u>Log Agihan Tugasan Yg Telah Selesai</u></a></td>
   	</tr>
<table>
<table width="100%" >
  <tr class="table_header">
    <td>NO</td>
    <td>NO RUJUKAN DOKUMEN</td>
    <td>TAJUK DOKUMEN</td>
    <td>PENGIRIM DOKUMEN</td>
    <td>TARIKH DOKUMEN</td>
  </tr>
  #foreach ($listAgihanTugasanByUserId in $SenaraiAgihanTugasanByUserId)
  
  #if ($listAgihanTugasanByUserId.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listAgihanTugasanByUserId.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listAgihanTugasanByUserId.bil</td>
    #if ($listAgihanTugasanByUserId.bil != '') 
    <td class="$row">
    <a href="javascript:edit_agihanTugasan('$listAgihanTugasanByUserId.idLogDokumenKPTG','$listAgihanTugasanByUserId.idLogDokumenKPTGSekLain')" class="style1">$listAgihanTugasanByUserId.no_Rujukan_Lain</a>
    </td>
    #else
        <td class="$row">$listAgihanTugasanByUserId.no_Rujukan_Lain</td>   
    #end
	<td class="$row">$listAgihanTugasanByUserId.tajuk_Dokumen</td>
    <td class="$row">$listAgihanTugasanByUserId.nama_Pengirim</td>
    <td class="$row">$listAgihanTugasanByUserId.tarikh_Dokumen</td>
  </tr>
  #end
  </table>
</fieldset>
#end

<input name="idLogDokumenKPTG" type="hidden" value="$!idLogDokumenKPTG"/>
<input name="idLogDokumenKPTGSekLain" type="hidden" value="$!idLogDokumenKPTGSekLain"/>
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

	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenDariSeksyenLainKPTG&idLogDokumenKPTG="+idLogDokumenKPTG+"&idLogDokumenKPTGSekLain="+idLogDokumenKPTGSekLain+"&pagemode=1";
	document.${formName}.submit();

}

function edit_agihanTugasan(idLogDokumenKPTG, idLogDokumenKPTGSekLain){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=papar&pagemode=1&idLogDokumenKPTG="+idLogDokumenKPTG+"&idLogDokumenKPTGSekLain="+idLogDokumenKPTGSekLain+"&paparArahan=true";
	document.${formName}.submit();
}

function selesai_logDokumen(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasanTKPK&action1=logDokumenSelesai";
	document.${formName}.submit();
}

function selesai_AgihanTugasan(idLogDokumenKPTG, idLogDokumenKPTGSekLain){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasanTKPK&action1=agihanTugasanSelesai&idLogDokumenKPTG="+idLogDokumenKPTG+"&idLogDokumenKPTGSekLain="+idLogDokumenKPTGSekLain+"";
	document.${formName}.submit();
}
  </script>
