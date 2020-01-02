<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
-->
</style>
<fieldset>
<legend>Senarai Log Dokumen</legend>
     <table> 
  <tr><td><a href ="javascript:kembali()" class="style2"><u>Kembali</u></a>
  </td>
</tr>
    </table>
<table width="100%" >
  <tr class="table_header">
    <td>NO</td>
    <td>TAJUK DOKUMEN</td>
    <td>NO RUJUKAN DOKUMEN</td>
    <td>TARIKH DOKUMEN</td>
    <td>TARIKH TERIMA DOKUMEN</td>
     <td>STATUS DOKUMEN</td>
    <td>JENIS DOKUMEN</td>
  </tr>
  #foreach ($listLogDokumenByUserIdSelesai in $SenaraiDokumenByUserIdSelesai)
  #if ($listLogDokumenByUserIdSelesai.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listLogDokumenByUserIdSelesai.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listLogDokumenByUserIdSelesai.bil</td>
    #if($listLogDokumenByUserIdSelesai.flag_Logdokumen == '1')
    	#if ($listLogDokumenByUserIdSelesai.bil != '') 
    <td class="$row"><a href="javascript:edit_logDokumen('$listLogDokumenByUserIdSelesai.idLogDokumen','$listLogDokumenByUserIdSelesai.flag_Logdokumen','$listLogDokumenByUserIdSelesai.idFail')" class="style1">$listLogDokumenByUserIdSelesai.tajuk_Dokumen</a></td>
    	#else
        <td class="$row">$listLogDokumenByUserIdSelesai.tajuk_Dokumen</td>   
    	#end
    #elseif($listLogDokumenByUserIdSelesai.flag_Logdokumen == '2')
     	#if ($listLogDokumenByUserIdSelesai.bil != '') 
    <td class="$row"><a href="javascript:edit_logDokumenKeluar('$listLogDokumenByUserIdSelesai.idLogDokumen','$listLogDokumenByUserIdSelesai.flag_Logdokumen','$listLogDokumenByUserIdSelesai.idDokumen')" class="style1">$listLogDokumenByUserIdSelesai.tajuk_Dokumen</a></td>
    	#else
        <td class="$row">$listLogDokumenByUserIdSelesai.tajuk_Dokumen</td>   
    	#end
     #elseif($listLogDokumenByUserIdSelesai.flag_Logdokumen == '3')
        #if ($listLogDokumenByUserIdSelesai.bil != '') 
    <td class="$row"><a href="javascript:edit_logDokumenSeksyenLain('$listLogDokumenByUserIdSelesai.idLogDokumen','$listLogDokumenByUserIdSelesai.flag_Logdokumen','$listLogDokumenByUserIdSelesai.idDokumen')" class="style1">$listLogDokumenByUserIdSelesai.tajuk_Dokumen</a></td>
    	#else
        <td class="$row">$listLogDokumenByUserIdSelesai.tajuk_Dokumen</td>   
    	#end
    #else
        #if ($listLogDokumenByUserIdSelesai.bil != '') 
    <td class="$row"><a href="javascript:edit_logDokumenSeksyenKPTG('$listLogDokumenByUserIdSelesai.idLogDokumen','$listLogDokumenByUserIdSelesai.flag_Logdokumen','$listLogDokumenByUserIdSelesai.idLogDokumenKPTG')" class="style1">$listLogDokumenByUserIdSelesai.tajuk_Dokumen</a></td>
    	#else
         <td class="$row">$listLogDokumenByUserIdSelesai.tajuk_Dokumen</td> 
    	#end
    #end
   	<td class="$row">$listLogDokumenByUserIdSelesai.no_Rujukan_Lain</td>   
	<td class="$row">$listLogDokumenByUserIdSelesai.tarikh_Dokumen</td>
    <td class="$row">$listLogDokumenByUserIdSelesai.tarikh_DokumenDiterima</td>
     #if ($listLogDokumenByUserIdSelesai.status_Logdokumen == '1') 
    <td class="$row"><blink>DALAM TINDAKAN<blink></td>
    #elseif($listLogDokumenByUserIdSelesai.status_Logdokumen == '2') 
    <td class="$row">TELAH DILULUSKAN</td>
    #elseif($listLogDokumenByUserIdSelesai.status_Logdokumen == '3') 
    <td class="$row">TELAH DIPULANGKAN</td>
    #else 
     <td class="$row">TELAH DIAMBIL TINDAKAN</td>
  	#end
     #if($listLogDokumenByUserIdSelesai.flag_Logdokumen == '1')
    <td class="$row">DOKUMEN MASUK</td>
     #elseif($listLogDokumenByUserIdSelesai.flag_Logdokumen == '2')
      <td class="$row">DOKUMEN KELUAR</td>
      #elseif($listLogDokumenByUserIdSelesai.flag_Logdokumen == '3')
       <td class="$row">DOKUMEN DARI SEKSYEN LAIN</td>
     #else
      <td class="$row">DOKUMEN DARI SEKSYEN KPTG</td>
     #end
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
function edit_logDokumen(idLogDokumen,idFail){
	
	document.${formName}.action = "$EkptgUtil.getTabID("Pengurusan Fail Dokumen",$portal_role)?_portal_module=ekptg.view.pfd.PendaftaranDokumen&pagemode=1&idLogDokumen="+idLogDokumen+"&idFail="+idFail;
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
