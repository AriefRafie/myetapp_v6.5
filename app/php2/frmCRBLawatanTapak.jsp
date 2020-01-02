<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><div id="TabbedPanels2" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabLower(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT LAWATAN TAPAK</li>
          <li onClick="doChangeTabLower(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT LAIN</li>
          <li onClick="doChangeTabLower(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PENCEROBOHAN</li>
          <li onClick="doChangeTabLower(3);" class="TabbedPanelsTab" tabindex="0">KEHADIRAN</li>
          <li onClick="doChangeTabLower(4);" class="TabbedPanelsTab" tabindex="0">PEGAWAI PELAPOR</li>
          <li onClick="doChangeTabLower(5);" class="TabbedPanelsTab" tabindex="0">IMEJAN</li>
          <li onClick="doChangeTabLower(6);" class="TabbedPanelsTab" tabindex="0">LAMPIRAN LAPORAN TANAH</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
              <div class="TabbedPanelsContent"> <br>
                #parse("app/php2/frmCRBLawatanTapakDetail.jsp") </div>
              <div class="TabbedPanelsContent"> <br>
                #parse("app/php2/frmCRBMaklumatLain.jsp") </div>
              <div class="TabbedPanelsContent"> <br>
                #parse("app/php2/frmCRBMaklumatPencerobohan.jsp") </div>
              <div class="TabbedPanelsContent"> <br>
                #parse("app/php2/frmCRBMaklumatKehadiran.jsp") </div>
              <div class="TabbedPanelsContent"> <br>
                #parse("app/php2/frmCRBPegawaiPelapor.jsp") </div>
              <div class="TabbedPanelsContent"> <br>
                #parse("app/php2/frmCRBImejan.jsp") </div>
              <div class="TabbedPanelsContent"> <br>
                #parse("app/php2/frmCRBLampiran.jsp") </div>
        </div>
      </div></td>
  </tr>
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
   <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakLaporanTanah('$idFail')"> Laporan Tanah </a></td>
  </tr>
</table>
</fieldset>
<script type="text/javascript">
	var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabLower});
</script>


