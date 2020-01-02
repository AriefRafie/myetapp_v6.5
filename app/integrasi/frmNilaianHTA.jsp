<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
#if ($sendNilaianHTA == 'true')
<fieldset>
	#if ($isJPPHUser == 'true')
  <div class="success">Nilaian harta telah berjaya dihantar ke JKPTG.</div>
    #else
  <div class="success">Permohonan bagi mendapatkan nilaian telah berjaya dihantar ke JPPH.</div>
  	#end
</fieldset>
<br />
#elseif ($saveNilaianHTA == 'true')
<fieldset>
	#if ($isJPPHUser == 'true')
  <div class="success">Maklumat nilaian harta telah berjaya disimpan.</div>
	#end
</fieldset>
#end
<fieldset>
  <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
  <br />
  <table width="100%" border="0" align="center">
    <tr>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>NO FAIL</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap" class="link">$MP_NOFAIL</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH MOHON</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_TARIKHMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO PERMOHONAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOPERMOHONAN</td>
      <td width="15%" align="right" valign="top" nowrap="nowrap"><strong>NAMA PEMOHON</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="34%" align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMAPEMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NEGERI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NEGERI</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO KP PEMOHON</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOKPPEMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>DAERAH / JAJAHAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_DAERAH</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>ALAMAT PEMOHON</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_ALAMATPEMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>UNIT</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_UNIT</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO TEL PEMOHON</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOTELPEMOHON</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NAMA SIMATI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NAMASIMATI</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO KP SIMATI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOKPSIMATI</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">&nbsp;</td>
      <td align="right" valign="top" nowrap="nowrap"><strong>TARIKH MATI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_TARIKHMATI</td>
    </tr>
    <tr>
      <td colspan="6" align="right" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    #if ($selectedTab == '0')
    <li class="TabbedPanelsTab" tabindex="0"><strong>HARTA TAK ALIH (ADA HAKMILIK)</strong></li>
    #end
    #if ($selectedTab == '1')
    <li class="TabbedPanelsTab" tabindex="1"><strong>HARTA TAK ALIH (TIADA HAKMILIK)</strong></li>
    #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    #if ($selectedTab == '0')
    <div class="TabbedPanelsContent">
    <!-- content permohonan -->
      #parse ("app/integrasi/frmNilaianHTATabAH.jsp")
    <!-- end content permohonan -->
    </div>
    #end
    #if ($selectedTab == '1')
    <div class="TabbedPanelsContent">
    <!-- content keputusan -->
      #parse ("app/integrasi/frmNilaianHTATabTH.jsp")
    <!-- end content keputusan -->
    </div>
    #end
  </div>
  <br />
</div>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_HTA" name="ID_HTA" value="$ID_HTA" />
<input type="hidden" id="selectedTab" name="selectedTab" value="$selectedTab" />
<input type="hidden" id="action2" name="action2" value="$action2" />
<input type="hidden" id="command2" name="command2" />
<input type="hidden" id="idhtaam" name="idhtaam" />
<input type="hidden" id="idPermohonan" name="idPermohonan" />
<input type="hidden" id="id_Permohonansimati" name="id_Permohonansimati" />
<input type="hidden" id="mode" name="mode" />
<script type="text/javascript">
<!--
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", {defaultTab:$selectedTab});
//-->
</script>
<script type="text/javascript">
  function toggleJenisPegangan() {
      document.${formName}.action2.value = "viewNilaianHTA";
      document.${formName}.command2.value = "changeSOCPajakan";
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA";
      document.${formName}.method = "POST";
      doAjaxCall${formName}("changeSOCPajakan");
  }
  function doChangeTab(TabID) {
      document.${formName}.action2.value = "viewNilaianHTA";
      document.${formName}.selectedTab.value = TabID;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&selectedTab=" + TabID;
      doAjaxCall${formName}("");
  }
  function doChangeSOC(SOC_TYPE) {
      document.${formName}.action2.value = "viewNilaianHTA";
      document.${formName}.command2.value = SOC_TYPE;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA";
      doAjaxCall${formName}(SOC_TYPE);
  }
  function viewNilaianHTAAH(ID_HTAAH) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&selectedTab=0&ID_HTAAH=" + ID_HTAAH;
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function viewNilaianHTATH(ID_HTATH) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&selectedTab=1&ID_HTATH=" + ID_HTATH;
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function saveNilaianHTA() {
#if ($isJPPHUser == 'true')
    #if ($selectedTab == '0')
      if (document.getElementById('HTAAH_NILAIANPADATARIKHMATI').checked) {
          if (document.getElementById('HTAAH_NILAITARIKHMATI').value == '') {
              alert('Sila masukkan maklumat nilaian tarikh mati.');
              document.getElementById('HTAAH_NILAITARIKHMATI').focus();
              return;
          }
      }
    #elseif ($selectedTab == '1')
      if (document.getElementById('HTATH_NILAIANPADATARIKHMATI').checked) {
          if (document.getElementById('HTATH_NILAITARIKHMATI').value == '') {
              alert('Sila masukkan maklumat nilaian tarikh mati.');
              document.getElementById('HTATH_NILAITARIKHMATI').focus();
              return;
          }
      }
    #end
#end      
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=saveNilaianHTA";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function sendNilaianHTA() {
#if ($isJPPHUser == 'true')
    #if ($selectedTab == '0')
      if (document.getElementById('HTAAH_NILAITARIKHMOHON').value == '') {
          alert('Sila masukkan nilai harta pada tarikh mohon.');
          document.getElementById('HTAAH_NILAITARIKHMOHON').focus();
          return;
      }
      if (document.getElementById('HTAAH_NILAIANPADATARIKHMATI').checked) {
          if (document.getElementById('HTAAH_NILAITARIKHMATI').value == '') {
              alert('Sila masukkan maklumat nilaian tarikh mati.');
              document.getElementById('HTAAH_NILAITARIKHMATI').focus();
              return;
          }
      }
      if (document.getElementById('HTAAH_NAMAPEGAWAI').value == '') {
          alert('Sila masukkan nama pegawai penilai.');
          document.getElementById('HTAAH_NAMAPEGAWAI').focus();
          return;
      }
      if (document.getElementById('HTAAH_CAWANGANJPPH').value == '') {
          alert('Sila masukkan nama cawangan Pejabat JPPH.');
          document.getElementById('HTAAH_CAWANGANJPPH').focus();
          return;
      }
      if (document.getElementById('HTAAH_TARIKHNILAIAN').value == '') {
          alert('Sila masukkan tarikh nilaian dibuat.');
          document.getElementById('HTAAH_TARIKHNILAIAN').focus();
          return;
      }
    #elseif ($selectedTab == '1')
      if (document.getElementById('HTATH_NILAITARIKHMOHON').value == '') {
          alert('Sila masukkan nilai harta pada tarikh mohon.');
          document.getElementById('HTATH_NILAITARIKHMOHON').focus();
          return;
      }
      if (document.getElementById('HTATH_NILAIANPADATARIKHMATI').checked) {
          if (document.getElementById('HTATH_NILAITARIKHMATI').value == '') {
              alert('Sila masukkan maklumat nilaian tarikh mati.');
              document.getElementById('HTATH_NILAITARIKHMATI').focus();
              return;
          }
      }
      if (document.getElementById('HTATH_NAMAPEGAWAI').value == '') {
          alert('Sila masukkan nama pegawai penilai.');
          document.getElementById('HTATH_NAMAPEGAWAI').focus();
          return;
      }
      if (document.getElementById('HTATH_CAWANGANJPPH').value == '') {
          alert('Sila masukkan nama cawangan Pejabat JPPH.');
          document.getElementById('HTATH_CAWANGANJPPH').focus();
          return;
      }
      if (document.getElementById('HTATH_TARIKHNILAIAN').value == '') {
          alert('Sila masukkan tarikh nilaian dibuat.');
          document.getElementById('HTATH_TARIKHNILAIAN').focus();
          return;
      }
    #end
#end  
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=sendNilaianHTA";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function backNilaianHTA() {
#if ($isJPPHUser == 'true')
      document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewMyInfoNilaianHartaTakAlih&action2=";
#else
	#if ($mode == 'edit')
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA";
      document.${formName}.mode.value = "";
	#else
      document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8Internal";
      document.${formName}.idPermohonan.value = "$ID_PERMOHONAN";
      document.${formName}.id_Permohonansimati.value = "$ID_PMHNSM";
      document.${formName}.idhtaam.value = "$ID_HTAAH";
      document.${formName}.command.value = "Htaam";
      document.${formName}.mode.value = "getHtaam";
	#end
      //document.${formName}.method = "POST";
#end  
      document.${formName}.submit();
  }
  function deleteNilaianHTA() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=deleteNilaianHTA";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function returnNilaianHTA(ID_CATATAN) {
      if (document.getElementById(ID_CATATAN).value == '') {
          alert('Sila isikan sebab permohonan dikembalikan dalam ruangan Catatan.');
          document.getElementById(ID_CATATAN).focus();
          return;
      }
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=returnNilaianHTA";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function printNilaianHTA(RT, ID_HTA) {
      var url = "../servlet/ekptg.report.integrasi.ReportNilaianJPPH?reportType=PDF&rt=" + RT + "&ID_HTA=" + ID_HTA;
      var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
  }
  function editNilaianHTA() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&mode=edit";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>