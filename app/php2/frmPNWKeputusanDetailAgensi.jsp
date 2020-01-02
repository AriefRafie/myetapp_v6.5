<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT TANAH</legend>
      #parse("app/php2/frmPNWMaklumatTanahKeputusan.jsp")
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI KEMENTERIAN / AGENSI BERMINAT</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="5" align="right"><div id="calculateTotal_result" style="color:#FF0000;font-weight:bold"></div></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="35%"><strong>Kementerian</strong></td>
          <td width="35%"><strong>Agensi</strong></td>
          <td width="25%" align="center"><strong>Luas (Hektar)</strong></td>
        </tr>
        #set ($senaraiAgensi = "")
        #if ($SenaraiAgensi.size() > 0)
        #foreach ($senaraiAgensi in $SenaraiAgensi)
        #if ($senaraiAgensi.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiAgensi.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiAgensi.bil</td>
          <td class="$row">$senaraiAgensi.kementerian</td>
          <td class="$row">$senaraiAgensi.agensi</td>
          <td class="$row" align="center"><input name="txtLuas" type="text" size="14" maxlength="14" style="text-align:center" value="$senaraiAgensi.luas" onBlur="validateLuas(this,this.value,'$senaraiAgensi.luas');calculateTotal();" $readonly class="$inputTextClass">
            <input name="ids" type="hidden" value="$senaraiAgensi.idPenawaranKJP">
          </td>
        </tr>
        #end
        <tr class="table_header">
          <td width="5%">&nbsp;</td>
          <td colspan="2" align="left"><strong>JUMLAH</strong></td>
          <td align="center" width="35%"><input name="txtJumlahLuas" id="txtJumlahLuas" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahLuas" readonly class="disabled">
          </td>
        </tr>
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
  	<td align="center">
    #if ($mode == 'view')
    	<input name="cmdKemaskini" type="button" value="Kemaskini" onclick="doKemaskini()"/>
        <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
         #if ($idStatus == '1610206')
      	<input type="button" name="cmdHantar" id="cmdHantar" value="Selesai" onClick="doSeterusnya()"/>
      	<input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
   	  #end
  #end
  #if ($mode == 'update')
    <input name="cmdSimpan" type="button" value="Simpan" onclick="simpanKemaskiniAgensi()"/>
        <input name="cmdBatal" type="button" value="Batal" onclick="doBatalKemaskini()"/>
    #end
    </td>
  </tr>
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onclick="javascript:cetakSuratTolak('$idFail')"> Surat Tolak </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onclick="javascript:cetakSuratLulus('$idFail')"> Surat Lulus </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onclick="javascript:cetakSuratLulusPajakan('$idFail')"> Surat Lulus (Pajakan) </a></td>
  </tr>
</table>
</fieldset>
<script>
function cetakSuratTolak(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=PNWSuratTolak";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratLulus(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=PNWSuratLulus";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratLulusPajakan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=PNWSuratLulusPajakan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
