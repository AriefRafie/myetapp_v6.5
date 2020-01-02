<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>
<fieldset>
  <legend>Senarai Agihan Tugasan Pengarah/KPP/Pegawai Berkaitan yang telah diambil tindakan</legend>
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
  #foreach ($listAgihanTugasanByUserIdTindakan in $SenaraiAgihanTugasanByUserIdTindakan)
  
  #if ($listAgihanTugasanByUserIdTindakan.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listAgihanTugasanByUserIdTindakan.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listAgihanTugasanByUserIdTindakan.bil</td>
    #if ($listAgihanTugasanByUserIdTindakan.bil != '') 
    <td class="$row">
    <a href="javascript:edit_agihanTugasan('$listAgihanTugasanByUserIdTindakan.idDokumen','$listAgihanTugasanByUserIdTindakan.idFail')" class="style1">$listAgihanTugasanByUserIdTindakan.no_Rujukan_Dokumen</a>
    </td>
    #else
        <td class="$row">$listAgihanTugasanByUserIdTindakan.no_Rujukan_Dokumen</td>   
    #end
	<td class="$row">$listAgihanTugasanByUserIdTindakan.tajuk_Dokumen</td>
    <td class="$row">$listAgihanTugasanByUserIdTindakan.nama_Pengirim</td>
    <td class="$row">$listAgihanTugasanByUserIdTindakan.tarikh_Dokumen</td>
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
