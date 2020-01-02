<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><div id="TabbedPanels2" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabLower(0);" class="TabbedPanelsTab" tabindex="0">LAPORAN OPERASI</li>
          <li onClick="doChangeTabLower(1);" class="TabbedPanelsTab" tabindex="0">KEHADIRAN</li>
          <li onClick="doChangeTabLower(2);" class="TabbedPanelsTab" tabindex="0">IMEJAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmCRBLaporanOperasi.jsp") </div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmCRBLaporanOperasiKehadiran.jsp")</div>
          <div class="TabbedPanelsContent"> <br>
            #parse("app/php2/frmCRBLaporanOperasiImejan.jsp") </div>
        </div>
      </div></td>
  </tr>
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakLaporanOperasi('$idFail','$idLaporanTanah','$idPermohonan')"> Laporan Operasi </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakCRBLampiranA('$idLaporanTanah')"> Lampiran A </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratPagarTanah('$idFail')"> Surat Pagar Tanah </a></td>
  </tr>
</table>
</fieldset>