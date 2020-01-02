<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>
<fieldset>
  <legend>Senarai Tugasan Yang telah Selesai</legend>
<table>
  	<tr>
    	<td><a href ="javascript:kembali()" class="style2"><u>Kembali</u></a></td>
   	</tr>
  <table>
<table width="100%" >
  <tr class="table_header">
      <td>NO</td>
      <td>NO FAIL</td>
      <td>TAJUK FAIL</td>
      <td>DIPOHON OLEH</td>
      <td>TARIKH PINJAMAN DILULUSKAN</td>
      <td>TARIKH PEMULANGAN DILULUSKAN</td>
      <td>DILULUSKAN OLEH</td>
      <td>STATUS PERGERAKAN FAIL</td>
  </tr>
  #foreach ($listTugasanSelesai in $senaraiPergerakanFailSelesai)
  
  #if ($listTugasanSelesai.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listTugasanSelesai.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listTugasanSelesai.bil</td>
    <td class="$row">
     #if($listTugasanSelesai.status_pergerakan_fail == "1" )
    <a href="javascript:edit_item1('$listTugasanSelesai.idPergerakanfail','$listTugasanSelesai.idFail','$listTugasanSelesai.status_pergerakan_fail')" class="style1">$!listTugasanSelesai.no_Fail</a>
     #elseif($listTugasanSelesai.status_pergerakan_fail == "2" )
    <a href="javascript:edit_item2('$listTugasanSelesai.idPergerakanfail','$listTugasanSelesai.idFail','$listTugasanSelesai.status_pergerakan_fail')" class="style1">$!listTugasanSelesai.no_Fail</a>
      #elseif($listTugasanSelesai.status_pergerakan_fail == "3" )
    <a href="javascript:edit_item3('$listTugasanSelesai.idPergerakanfail','$listTugasanSelesai.idFail','$listTugasanSelesai.status_pergerakan_fail')" class="style1">$!listTugasanSelesai.no_Fail</a>
    #elseif($listTugasanSelesai.status_pergerakan_fail == "4" )
    <a href="javascript:edit_item4('$listTugasanSelesai.idPergerakanfail','$listTugasanSelesai.idFail','$listTugasanSelesai.status_pergerakan_fail')" class="style1">$!listTugasanSelesai.no_Fail</a>
    #elseif($listTugasanSelesai.status_pergerakan_fail == "0" )
    <a href="javascript:edit_item('$listTugasanSelesai.idPergerakanfail','$listTugasanSelesai.idFail','$listTugasanSelesai.status_pergerakan_fail')" class="style1">$!listTugasanSelesai.no_Fail</a>  
    </td>
    #else
    <td class="$row">$!listTugasanSelesai.no_Fail</td>
    #end
    
	<td class="$row">$!listTugasanSelesai.nama_fail</td>
    <td class="$row">$!listTugasanSelesai.peminjam_fail</td>
    <td class="$row">$!listTugasanSelesai.tarikh_sah_pinjamfail</td>
    <td class="$row">$!listTugasanSelesai.tarikh_sah_pulangfail</td>
    <td class="$row">$!listTugasanSelesai.nama_pt_fail</td>
    <td class="$row">
    #if($listTugasanSelesai.status_pergerakan_fail == "1" )
   	<blink>DALAM TINDAKAN</blink>
    #elseif($listTugasanSelesai.status_pergerakan_fail == "2" )
	TELAH DILULUSKAN
    #elseif($listTugasanSelesai.status_pergerakan_fail == "3" )
   	TELAH DIPULANGKAN
    #elseif($listTugasanSelesai.status_pergerakan_fail == "4" )
    SEDANG DIPINJAM
    #elseif($listTugasanSelesai.status_pergerakan_fail == "0" )
    TIADA MAKLUMBALAS</td>
    #else
    <td class="$row">$!listTugasanSelesai.status_pergerakan_fail</td>
    #end
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
