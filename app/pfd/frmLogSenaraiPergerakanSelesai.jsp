<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>

<fieldset>
  <legend>Senarai Log Pergerakan Fail Yang telah Selesai</legend>
    <table>
  	<tr>
    	<td><a href ="javascript:kembali()" class="style2"><u>Kembali</u></a></td>
   	</tr>
  <table>
    <table width="100%">
    <tr class="table_header">
      <td>No</td>
      <td>Status Pergerakan Fail</td>
      <td>No Fail</td>
      <td>Nama Fail</td>
      <td>Di Pohon Oleh</td>
      <td>Tarikh Pinjaman Diluluskan</td>
      <td>Tarikh Pemulangan Diluluskan</td>
      <td>Di Luluskan Oleh</td>
    </tr>
#foreach ($senaraiSelesai in $senaraiPergerakanFailSelesai)
  
  	#if ($senaraiSelesai.bil == '') 
  	#set ($row = 'row1')
  	#elseif ($senaraiSelesai.bil % 2 != 0)
  	#set ($row = 'row1')
  	#else 
  	#set ($row = 'row2')
  	#end  
  <tr>
    <td class="$row">$senaraiSelesai.bil</td>
    <td class="$row">
    #if($senaraiSelesai.status_pergerakan_fail == "1" )
    <a href="javascript:edit_item('$senaraiSelesai.idPergerakanfail','$senaraiSelesai.idFail')" class="style1">BELUM DILULUSKAN</a>
     #elseif($senaraiSelesai.status_pergerakan_fail == "2" )
    <a href="javascript:edit_item('$senaraiSelesai.idPergerakanfail','$senaraiSelesai.idFail')" class="style1">TELAH DILULUSKAN</a>
      #elseif($senaraiSelesai.status_pergerakan_fail == "3" )
    <a href="javascript:edit_item('$senaraiSelesai.idPergerakanfail','$senaraiSelesai.idFail')" class="style1">TELAH DIPULANGKAN</a>
    #elseif($senaraiSelesai.status_pergerakan_fail == "4" )
    <a href="javascript:edit_item('$senaraiSelesai.idPergerakanfail','$senaraiSelesai.idFail')" class="style1">SEDANG DIPINJAM</a>
    #elseif($senaraiSelesai.status_pergerakan_fail == "0" )
    <a href="javascript:edit_item('$senaraiSelesai.idPergerakanfail','$senaraiSelesai.idFail')" class="style1">TIADA MAKLUMBALAS</a>    </td>
    #end
    <td class="$row">$!senaraiSelesai.no_Fail</td>
    <td class="$row">$!senaraiSelesai.nama_fail</td>
    <td class="$row">$!senaraiSelesai.peminjam_fail</td>
    <td class="$row"> $!senaraiSelesai.tarikh_sah_pinjamfail    </td>
    <td class="$row">$!senaraiSelesai.tarikh_sah_pulangfail    </td>
    <td class="$row">$!senaraiSelesai.nama_pt_fail</td>
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.SenaraiTugasan&action1=";
	document.${formName}.submit();
}
  </script>
