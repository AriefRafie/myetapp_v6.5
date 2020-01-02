<style type="text/css">
<!--
  .link {color: #FF0000}
  .mandatori {color:#FF0000}
-->
</style>
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="ID_TEMPLATE" name="ID_TEMPLATE" value="$ID_TEMPLATE" />
<input type="hidden" id="idNegeri" name="idNegeri" value="$idNegeri" />
<input type="hidden" id="Mesyuarat_Unit" name="Mesyuarat_Unit" value="$Mesyuarat_Unit" />
<input type="hidden" id="PrevPage" name="PrevPage" value="1" />
<input type="hidden" id="action" name="action" value="$action" />
<fieldset>
  <legend><strong>PENDAFTARAN MESYUARAT</strong></legend>
  <br />
  <table width="100%" border="0" align="center">
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> No Fail / No Rujukan Mesyuarat</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="Mesyuarat_NoRujukanMesyuarat" type="text" id="Mesyuarat_NoRujukanMesyuarat" onkeyup="this.value=this.value.toUpperCase();" value="$!Mesyuarat_NoRujukanMesyuarat" size="50" maxlength="255" $RO_NoFail />
      #if ($Flag_failWujud == 'true')
      <span class="mandatori">*Fail tidak wujud</span>
      #end</td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">* </span>Tajuk Mesyuarat</div></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="70%" align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_Tajuk" type="text" id="Mesyuarat_Tajuk" value="$!Mesyuarat_Tajuk" size="100" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" />      </td>
    </tr>
    <!--tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"> Dari</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_Dari" type="text" id="Mesyuarat_Dari" value="$!Mesyuarat_Dari" size="10" maxlength="4" /> 
        (<em>format: hhmm, contoh 1500</em>)</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"> Hingga</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_Hingga" type="text" id="Mesyuarat_Hingga" value="$!Mesyuarat_Hingga" size="10" maxlength="4" /> 
        (<em>format: hhmm, contoh 1500</em>)</td>
    </tr-->
    #if($idNegeri == '16')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">* </span>Urusetia / Seksyen</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"> $Mesyuarat_Seksyen</td>
    </tr>
    #else
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">* </span>Negeri</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Mesyuarat_Negeri</td>
    </tr>
      	#if($getUnitByNegeri_size > 0)
       <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">* </span>Urusetia / Unit</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
           <td align="left" valign="top" nowrap="nowrap">$Mesyuarat_Unit</td>
    </tr>
   	 	#else
           <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">* </span>Urusetia / Unit</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
           <td align="left" valign="top" nowrap="nowrap">
                <select name="socSubUrusanB" disabled="disabled">
                  <option value="">SILA PILIH</option>
                </select>
              </td>
    </tr>
    	#end
    #end
    <!--tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Lokasi</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        $Mesyuarat_Lokasi      </td>
    </tr-->
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">No Fail yang Perlu Dibawa</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <textarea name="Mesyuarat_NoFail" cols="50" rows="2" id="Mesyuarat_NoFail" onkeyup="this.value=this.value.toUpperCase();">$!Mesyuarat_NoFail</textarea>      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Disediakan Oleh</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_DisediakanOleh" type="text" id="Mesyuarat_DisediakanOleh" value="$!Mesyuarat_DisediakanOleh" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Disemak Oleh</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_DisemakOleh" type="text" id="Mesyuarat_DisemakOleh" value="$!Mesyuarat_DisemakOleh" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Disahkan Oleh</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_DisahkanOleh" type="text" id="Mesyuarat_DisahkanOleh" value="$!Mesyuarat_DisahkanOleh" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Catatan</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <textarea name="Mesyuarat_Catatan" cols="50" rows="5" id="Mesyuarat_Catatan" onkeyup="this.value=this.value.toUpperCase();">$!Mesyuarat_Catatan</textarea>      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="button" id="cmdNext" name="cmdNext" value="Seterusnya" onclick="daftarAhli('$idNegeri');" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><span style="font-style:italic">Sila pastikan medan bertanda <span class="mandatori">*</span> diisi</span></td>
    </tr>
  </table>
</fieldset>
<br />

<script type="text/javascript">
  function daftarAhli(idNegeri) {

  	  if (document.${formName}.Mesyuarat_NoRujukanMesyuarat.value == '') {
		  alert('Sila masukkan No Rujukan Fail Mesyuarat.');
		  document.${formName}.Mesyuarat_NoRujukanMesyuarat.focus();
		  return;
	  }
	  else if (document.${formName}.Mesyuarat_Tajuk.value == '') {
		  alert('Sila masukkan Tajuk Mesyuarat.');
		  document.${formName}.Mesyuarat_Tajuk.focus();
		  return;
	  }
	  
	  else if(idNegeri == '16' && document.${formName}.SOC_SEKSYEN.value == '')
	  {

			alert('Sila masukkan Urusetia / Seksyen.');
			document.${formName}.SOC_SEKSYEN.focus();
			return;
	  }
	  else if(idNegeri != '16' && document.${formName}.SOC_UNIT.value == '')
	  {
		  	alert('Sila masukkan Urusetia / Unit.');
		  	document.${formName}.SOC_UNIT.focus();
			return;
	  }
	  else 
	  {
	  	
	  	document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTemplate&action=daftarAhli";
	  	document.${formName}.submit();
	  }
  }
  function cetakMesyuarat() {
		var url = "../servlet/ekptg.report.pfd.PFDReport?reportType=PDF&TYPE=4&MINITMESYUARAT_ID=$ID_MESYUARAT";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
  }
    function doChangeSOC() {
	 // alert("submitType :"+submitType);
      document.${formName}.action.value = "daftarMesyuarat";
      doAjaxCall${formName}("doChangeSOC");
  }
  function doChangeUrusanB() {
	document.${formName}.action.value = "tabFailSeksyenBaru";
 	doAjaxCall${formName}("doChangeUrusanB","modeAktiviti=ada");
}
  
</script>