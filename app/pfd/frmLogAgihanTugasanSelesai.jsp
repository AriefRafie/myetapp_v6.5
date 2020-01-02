<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>
<fieldset>
  <legend>Senarai Agihan Tugasan Pengarah</legend>
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
    <a href="javascript:edit_agihanTugasan('$listAgihanTugasanByUserId.idMinitArahan','$listAgihanTugasanByUserIdSelesai.idDokumen','$listAgihanTugasanByUserIdSelesai.idFail')" class="style1">$listAgihanTugasanByUserIdSelesai.no_Rujukan_Lain</a>
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
<script>

function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=";
	document.${formName}.submit();
}
function edit_agihanTugasan(idMinitArahan,idDokumen,idFail){
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar&idMinitArahan="+idMinitArahan+"&idDokumen="+idDokumen+"&idFail="+idFail+"&paparArahan=true";
	document.${formName}.submit();
}
  </script>
