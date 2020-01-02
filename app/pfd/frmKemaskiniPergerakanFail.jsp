<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
.style3 {color: #0000FF}
.style4 {font-size: 10px}
.style5 {font-size: 10px; color: #FF0000; }
.style6 {color: #000000}
-->
</style>
<fieldset>
<legend>MAKLUMAT FAIL</legend>

<input name="mode" type="hidden" value="$mode" />
<input name="hiddenButton1" type="hidden" value="$hiddenButton1"/>
<input name="hiddenButton2" type="hidden" value="$hiddenButton2"/>
<input name="idFail" type="hidden" value="$idFail" />
<input name="idMasuk" type="hidden" value="$idMasuk" />
<input name="idPergerakanfail" type="hidden" value="$idPergerakanfail" />
<table width="100%">
 
   <input name="idFail" type="hidden" value="$fail.idFail" />
  <tr>
    <td width="29%" scope="row"><div align="left">NO FAIL</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <span class="style3">
      $noFail.toUpperCase()</span></td>
  </tr>
  <tr>
    <td scope="row"><div align="left">TAJUK FAIL</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td class="style3">$tajukFail.toUpperCase()</td>
  </tr>
  <tr>
    <td scope="row"><div align="left">STATUS FAIL</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td class="style3">$status.toUpperCase()</td>
  </tr>
</table>
</fieldset>
&nbsp;
<fieldset>
  <legend>Senarai Pergerakan Fail</legend>
 <input type="submit" name="cmdTambahPergerakan" id="cmdTambahPergerakan" value="Pinjam Fail" onclick="tambahSenaraiPF()" />
 <input type="submit" name="cetak" id="cetak" value="Cetak Pergerakan Fail" onclick="cetakfail('$!idFail')" />
<table width="100%"  border="0"><tr><td colspan="3" scope="row"><table width="100%"  border="0">
    <tr class="table_header">
      <td>No</td>
      <td>No Fail</td>
      <td>Nama Fail</td>
      <td>Di Pohon Oleh</td>
      <td>Tempoh Pinjaman Dari</td>
      <td>Tempoh Pinjaman Hingga</td>
      <td>Tarikh Pulang Fail</td>
      <td>Di Luluskan Oleh</td>
      <td>Status Pergerakan Fail</td>
    </tr>
    #set ( $cnt = 0 )
    #foreach ($senarai in $senaraiPergerakanFailUser)
    #set ( $cnt = $cnt + 1 )
    #if ($senarai.bil == '') 
    #set ($row = 'row1')
    #elseif ($senarai.bil % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
  <tr>
    <td class="$row">$senarai.bil</td>
    #if ($senarai.bil != '')
    #if($nama_pemohon == $senarai.ID_PEGAWAI_PINJAMFAIL )
    <td class="$row"><a href="javascript:edit_item('$senarai.idPergerakanfail','$senarai.idFail')" class="style1">$!senarai.no_Fail</a></td>
    #elseif($nama_pemohon != $senarai.ID_PEGAWAI_PINJAMFAIL )
    <td class="$row">$!senarai.no_Fail</td>
    #end
    #else
     <td class="$row">$!senarai.no_Fail</td>
    #end
    <td class="$row">$!senarai.nama_fail</td>
    <td class="$row">$!senarai.peminjam_fail</td>
    <td class="$row"> $!senarai.tempoh_pinjam_dari    </td>
    <td class="$row">$!senarai.tempoh_pinjam_hingga</td>
    <td class="$row">$!senarai.tarikh_pulang_fail</td>
    <td class="$row">$!senarai.nama_pt_fail</td>
    #if ($senarai.bil != '')
    <td class="$row">
    #if($senarai.status_pergerakan_fail == "1" )  
    BELUM DILULUSKAN
    #elseif($senarai.status_pergerakan_fail == "2" )
   	SEDANG DIPINJAM
    #elseif($senarai.status_pergerakan_fail == "3" )
	TELAH DIPULANGKAN 
    #elseif($senarai.status_pergerakan_fail == "0" )
    TIADA MAKLUMBALAS
    #end  
    </td>
    #else
    <td class="$row">$!senarai.status_pergerakan_fail</td>
    #end
  </tr>
  #end
  </table>    
  <font color="#FF0000">&nbsp;</font></td>
	</tr>
  </table>
<input name="idFail" type="hidden" value="$idFail" />
<input name="nama_pemohon" type="hidden" value="$nama_pemohon" />
<input name="idPergerakanfail" type="hidden" value="$idPergerakanfail" />
</fieldset>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-10</strong></td>
  </tr>
</table>


<script>
function edit_item(idPergerakanfail, idFail){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=paparPinjamFail&idPergerakanfail="+idPergerakanfail+"&idFail="+idFail;
	document.${formName}.submit();
}
function batal(){

	document.${formName}.action.value = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=SenaraiFail";
	document.${formName}.submit();
}
function kembali(){

	document.${formName}.action.value = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=";
	document.${formName}.submit();
}

function papar_pergerakan(idPergerakanfail){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=paparPergerakanfail";
	//document.${formName}.mode.value = "";
	document.${formName}.idPergerakanfail.value = idPergerakanfail;
	document.${formName}.submit();

}
function tambahSenaraiPF(idFail){
	
	//document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=checkPF";
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=tabPinjamFail";
	document.${formName}.mode.value = "";
	document.${formName}.idFail.value = idFail;
	document.${formName}.submit();

}
function cetak(idFail) {
	alert("asdasdasdasd");
        var url = "../servlet/ekptg.report.pfd.PergerakanFail?id_Fail="+idFail;
        var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();

}

function cetakfail(idFail) {

        var url = "../servlet/ekptg.report.pfd.PergerakanFail?id_Fail="+idFail;
        var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();

}

</script>