<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
<input type="hidden" id="idNegeri" name="idNegeri" value="$idNegeri" />
<input type="hidden" id="SOC_UNIT" name="SOC_UNIT" value="$SOC_UNIT" />
<input type="hidden" id="Mesyuarat_Unit" name="Mesyuarat_Unit" value="$Mesyuarat_Unit" />
<fieldset>
  <legend><strong>MAKLUMAT MESYUARAT</strong></legend>
  <br />
  <table width="100%" align="center">
    <tr>
      <td align="right" width="29%" valign="top" nowrap="nowrap"><div align="left"><strong>No Fail / No Rujukan Mesyuarat</strong></div></td>
      <td align="center" width="1%" valign="top" nowrap="nowrap">:</td>
      <td align="left"  width="70%"valign="top" nowrap="nowrap" class="link">$Mesyuarat_NoFail </td>
    </tr>
    <tr>
      <td  align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Bil Mesyuarat</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Bil      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Tajuk Mesyuarat</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Tajuk      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Kategori Mesyuarat</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
      #if($Mesyuarat_Kategori == '1')
      AD-HOC
      #else
      RUTIN
      #end      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Tarikh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
      $Mesyuarat_Tarikh</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Dari</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
      $Mesyuarat_Dari</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Hingga</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
      $Mesyuarat_Hingga</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Lokasi</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Lokasi      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Status</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Status      </td>
    </tr>
    #if($idNegeri == '16')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Urusetia / Seksyen</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Seksyen      </td>
    </tr>
    #else
        <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Urusetia / Unit</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Unit     </td>
    </tr>
    #end
        <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>No Fail yang Diperlukan</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_NoFailDiperlukan</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Disediakan Oleh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_DisediakanOleh      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Disemak Oleh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_DisemakOleh      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Disahkan Oleh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_DisahkanOleh</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Catatan</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Catatan</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">&nbsp;</td>
    </tr>
    <tr class="table_header">
      <td colspan="3" align="center" valign="top" nowrap="nowrap"><div align="left">Tempahan Makanan &amp; Minuman</div></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Nama Pemohon</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><input name="Mesyuarat_TempahMakananPemohon" type="text" id="Mesyuarat_TempahMakananPemohon" value="$!Mesyuarat_TempahMakananPemohon" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Bil Ahli Mesyuarat</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><input name="Mesyuarat_TempahMakananBilAhli" type="text" id="Mesyuarat_TempahMakananBilAhli" value="$!Mesyuarat_TempahMakananBilAhli" size="5" maxlength="3" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Jenis Makanan</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><input name="Mesyuarat_TempahMakananMakanan" type="text" id="Mesyuarat_TempahMakananMakanan" value="$!Mesyuarat_TempahMakananMakanan" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Jenis Minuman</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><input name="Mesyuarat_TempahMakananMinuman" type="text" id="Mesyuarat_TempahMakananMinuman" value="$!Mesyuarat_TempahMakananMinuman" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap" class="link"><input type="button" id="cmdSimpanTempahanMakanan" name="cmdSimpanTempahanMakanan" value="Simpan" onclick="tempahMakanan();" /></td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>ANDA KINI BOLEH..</strong></legend>
  <br />
  <table width="100%" align="center">
#if ($hideSaveButton != 'true')  
    <tr>
      <td style="width:30%; text-align:right">&nbsp;</td>
      <td style="width:70%">
        <input type="radio" id="optJenisReport" name="optJenisReport" value="6" checked="checked" />Cetak Surat Panggilan Mesyuarat<br />
      </td>
    </tr>

    <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="4" />Cetak Minit Mesyuarat<br />
      </td>
    </tr>
    
    <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="15" />Cetak Minit Mesyuarat (Maklumbalas)<br />
      </td>
    </tr>
    
    <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="5" />Cetak Surat Tempahan Makanan<br />
      </td>
    </tr>
    <!--tr>
      <td style="text-align:right">5.</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="8" />Cetak Maklumbalas<br />
      </td>
    </tr-->
        <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="9" />Cetak Lampiran Senarai Edaran <br />
      </td>
    </tr>
        <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="10" />Cetak Borang Kehadiran<br />
      </td>
    </tr>
        <!--tr>
      <td style="text-align:right">8.</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="11" />Cetak Senarai Kehadiran<br />
      </td>
    </tr-->
#else  
    <tr>
      <td style="width:30%; text-align:right">&nbsp;</td>
      <td style="width:70%">
        <input type="radio" id="optJenisReport" name="optJenisReport" value="4" checked="checked" />Cetak Minit Mesyuarat<br />
      </td>
    </tr>
#end    
    <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
      #if($idNegeri == '16')
      <input type="button" id="cmdCetakMesyuarat" name="cmdCetakMesyuarat" value="Cetak" onclick="cetakMesyuarat('$ID_MESYUARAT');" />
      #else
            <input type="button" id="cmdCetakMesyuarat" name="cmdCetakMesyuarat" value="Cetak" onclick="cetakMesyuaratNegeri('$ID_MESYUARAT');" />
      #end
      </td>
      </tr>
      
      <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
      <img src="../img/emel.gif"  />
        <input type="button" id="cmdSearch" name="cmdSearch" value="Emel Kepada Ahli Mesyuarat" onclick="emailMesyuarat();" />
       
      </td>
      </tr>
      
      <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <img src="../img/emel.gif"  />
        <input type="button" id="cmdSearch" name="cmdSearch" value="Emel Pemberitahuan Minit Selesai" onclick="emailMesyuaratMinit();" />
      </td>
      </tr>
      
       <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
  
        <br /><br /><input type="button" id="cmdPrev" name="cmdPrev" value="Kembali" onclick="daftarMinit();" />
        <input type="button" id="cmdSearch" name="cmdSearch" value="Kembali ke Senarai Mesyuarat" onclick="senaraiMesyuarat();" />
      </td>
      </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI AHLI</strong></legend>
  <br />
  <table width="100%"  align="center">
    <tr class="table_header">
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="40%" align="left" valign="top" nowrap="nowrap">Nama Ahli</td>
      <td width="15%" align="center" valign="top" nowrap="nowrap">Hadir</td>
      <td width="40%" align="left" valign="top" nowrap="nowrap">Wakil</td>
    </tr>
#set ($fail = '')
#foreach ($fail in $List_AhliMesyuarat)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
    #if ($fail.No != '')
    <tr>
      <td class="$row" width="5%" align="center" valign="top" nowrap="nowrap">$fail.No</td>
      <td class="$row" width="40%" align="left" valign="top" nowrap="nowrap">$fail.NamaAhli</td>
      <td class="$row" width="15%" align="center" valign="top" nowrap="nowrap">$fail.Hadir</td>
      <td class="$row" width="40%" align="left" valign="top" nowrap="nowrap">$fail.Wakil</td>
    </tr>
	#end
#end
  </table>
</fieldset>
<!--<br />
<fieldset>
  <legend><strong>SENARAI MINIT</strong></legend>
  <br />
  <br />
  <div style="height:100%; width:1000; overflow-y:auto">
  <table align="center" width="100%">
#set ($fail = '')
#set ($PrevAgenda = '')
#set ($PrevMinit = '')
#set ($PrevSubMinit = '')
#foreach ($fail in $List_Minit)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
    #if ($fail.NoAgenda != '')
    	#if ($PrevAgenda != $fail.NoAgenda)
	        #set ($PrevAgenda = $fail.NoAgenda)
            #set ($PrevMinit = '')
    <tr>
      <td width="10%" class="$row" valign="top" nowrap="nowrap">
        <strong>$fail.NoAgenda.</strong>
      </td>
      <td colspan="4" class="$row" valign="top" nowrap="nowrap">
        <strong>$fail.Agenda</strong>
      </td>
    </tr>
    	#end
        #if ($PrevMinit != $fail.NoMinit)
        	#set ($PrevMinit = $fail.NoMinit)
        	#set ($PrevSubMinit = '')
    <tr>
      <td width="10%" class="$row" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="10%" class="$row" valign="top" nowrap="nowrap">
        $fail.NoAgenda.$fail.NoMinit
      </td>
      <td colspan="3" class="$row" valign="top" nowrap="nowrap">
        $fail.Minit
      </td>
    </tr>
    <tr>
      <td colspan="5" class="$row" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    	#end
    	#if ($fail.NoSubMinit != '')
    <tr>
      <td width="10%" class="$row" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="10%" class="$row" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="10%" class="$row" valign="top" nowrap="nowrap">&nbsp;</td>
      <td width="10%" class="$row" valign="top" nowrap="nowrap">
        $fail.NoAgenda.$fail.NoMinit.$fail.NoSubMinit
      </td>
      <td class="$row" valign="top" nowrap="nowrap">
        $fail.SubMinit<br />
      </td>
    </tr>
    <tr>
      <td colspan="5" class="$row" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
        #end
	#end
#end
  </table>-->
  </div>
</fieldset>
<br />
<br />
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="actionx" name="actionx" value="$actionx" />
<script type="text/javascript">
  function tempahMakanan() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=selesaiMesyuarat";
	  doAjaxCall${formName}("tempahMakanan");
  }
  function daftarMinit() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=daftarMinit";
	  document.${formName}.submit();
  }
  function senaraiMesyuarat() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=";
	  document.${formName}.submit();
  }
    function emailMesyuarat() {
		input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=email";
	  document.${formName}.submit();
	}
  }
   function emailMesyuaratMinit() {
	   input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=emailminit";
	  document.${formName}.submit();
	}
  }
  function cetakMesyuarat(id_mesyuarat) {
	  var cetakmaklumbalas = "no";
	  for (var i=0; i < document.${formName}.optJenisReport.length; i++) {
		  if (document.${formName}.optJenisReport[i].checked) {
			  var TYPE = document.${formName}.optJenisReport[i].value;
			  
			  if(TYPE == "15")
			  {
				cetakmaklumbalas = "yes";  
			  }
		  }
	  }
	  
	  if(cetakmaklumbalas == "no")
	  {
	  var url = "../servlet/ekptg.report.pfd.PFDReport?reportType=PDF&TYPE=" + TYPE + "&ID_MESYUARAT="+id_mesyuarat+"&MINITMESYUARAT_ID=$ID_MESYUARAT";
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
	  }
	  
	  if(cetakmaklumbalas == "yes")
	  {
	   var url = "../servlet/ekptg.report.pfd.CetakMaklumBalas?ID_MESYUARAT="+id_mesyuarat;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	  }
	  
  }
  
   function cetakMesyuaratNegeri(id_mesyuarat) {
	  var cetakmaklumbalas = "no";
	  for (var i=0; i < document.${formName}.optJenisReport.length; i++) {
		  if (document.${formName}.optJenisReport[i].checked) {
			  var TYPE = document.${formName}.optJenisReport[i].value;
			  
			  if(TYPE == "15")
			  {
				cetakmaklumbalas = "yes";  
			  }
		  }
	  }
	  
	  if(cetakmaklumbalas == "no")
	  {
	  var url = "../servlet/ekptg.report.pfd.PFDReportNegeri?reportType=PDF&TYPE=" + TYPE + "&ID_MESYUARAT="+id_mesyuarat+"&MINITMESYUARAT_ID=$ID_MESYUARAT";
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
	  }
	  
	  if(cetakmaklumbalas == "yes")
	  {
	   var url = "../servlet/ekptg.report.pfd.CetakMaklumBalas?ID_MESYUARAT="+id_mesyuarat;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	  }
	  
  }
  
  
function cetakMaklumBalas(id_mesyuarat)
{
    var url = "../servlet/ekptg.report.pfd.CetakMaklumBalas?ID_MESYUARAT="+id_mesyuarat;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

  
</script>