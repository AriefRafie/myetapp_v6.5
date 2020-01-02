<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>
<fieldset>
  <legend>Senarai Log Penerima Tugasan Yg Telah Selesai</legend>
      <table>
  	<tr>
    	<td><a href ="javascript:kembali()" class="style2"><u>Kembali</u></a></td>
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
  #foreach ($listPenerimaTugasanByUserIdSelesai in $SenaraiPenerimaTugasanByUserIdSelesai)
  
  #if ($listPenerimaTugasanByUserIdSelesai.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listPenerimaTugasanByUserIdSelesai.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listPenerimaTugasanByUserIdSelesai.bil</td>
    #if ($listPenerimaTugasanByUserIdSelesai.bil != '') 
    <td class="$row">
    <a href="javascript:edit_agihanTugasan('$listPenerimaTugasanByUserIdSelesai.idMinitArahan','$listPenerimaTugasanByUserIdSelesai.idDokumen','$listPenerimaTugasanByUserIdSelesai.idFail')" class="style1">$listPenerimaTugasanByUserIdSelesai.no_Rujukan_Lain</a>
    </td>
    #else
        <td class="$row">$listPenerimaTugasanByUserIdSelesai.no_Rujukan_Lain</td>   
    #end
	<td class="$row">$listPenerimaTugasanByUserIdSelesai.tajuk_Dokumen</td>
    <td class="$row">$listPenerimaTugasanByUserIdSelesai.nama_Pengirim</td>
    <td class="$row">$listPenerimaTugasanByUserIdSelesai.tarikh_Dokumen</td>
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

function edit_agihanTugasan(idMinitArahan,idDokumen,idFail){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar&idMinitArahan="+idMinitArahan+"&idDokumen="+idDokumen+"&idFail="+idFail+"&paparArahan=true";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasanTKPK&action1=";
	document.${formName}.submit();
}
  </script>
