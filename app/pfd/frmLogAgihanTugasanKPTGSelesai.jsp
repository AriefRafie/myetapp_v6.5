<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>
<fieldset>

  <legend>Senarai Log Agihan Tugasan Ketua Pengarah / Timb. Ketua Pengarah Yg Telah Selesai</legend>
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
  #foreach ($listAgihanTugasanByUserIdSelesai in $SenaraiAgihanTugasanByUserIdSelesai)
  
  #if ($listAgihanTugasanByUserIdSelesai.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listAgihanTugasanByUserIdSelesai.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listAgihanTugasanByUserIdSelesai.bil</td>
    #if ($listAgihanTugasanByUserIdSelesai.bil != '') 
    <td class="$row">
    <a href="javascript:edit_agihanTugasan('$listAgihanTugasanByUserIdSelesai.idLogDokumenKPTG','$listAgihanTugasanByUserIdSelesai.idLogDokumenKPTGSekLain')" class="style1">$listAgihanTugasanByUserIdSelesai.no_Rujukan_Lain</a>
    </td>
    #else
        <td class="$row">$listAgihanTugasanByUserIdSelesai.no_Rujukan_Lain</td>   
    #end
	<td class="$row">$listAgihanTugasanByUserIdSelesai.tajuk_Dokumen</td>
    <td class="$row">$listAgihanTugasanByUserIdSelesai.nama_Pengirim</td>
    <td class="$row">$listAgihanTugasanByUserIdSelesai.tarikh_Dokumen</td>
  </tr>
  #end
  </table>
  
</fieldset>

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

	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenDariSeksyenLainKPTG&idLogDokumenKPTG="+idLogDokumenKPTG+"&idLogDokumenKPTGSekLain="+idLogDokumenKPTGSekLain+"";
	document.${formName}.submit();

}

function edit_agihanTugasan(idLogDokumenKPTG, idLogDokumenKPTGSekLain){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=papar&pagemode=1&idLogDokumenKPTG="+idLogDokumenKPTG+"&idLogDokumenKPTGSekLain="+idLogDokumenKPTGSekLain+"&paparArahan=true";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasanTKPK&action1=";
	document.${formName}.submit();
}
  </script>
