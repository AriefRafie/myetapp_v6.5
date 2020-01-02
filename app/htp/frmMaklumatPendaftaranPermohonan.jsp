

<fieldset><legend><strong>Pendaftaran Permohonan </strong></legend>
<table width="100%" border="0">
  <tr>
    <td width="22%"><div align="right">Kementerian</div></td>
    <td width="31%"><label>$labelKementerian</label></td>
    <td width="16%"><div align="right">No. Fail Seksyen</div></td>
    <td width="31%"><label>
      <input name="cmdfailseksyen" type="text" id="cmdfailseksyen" value="$!noFaillSeksyen">
    </label></td>
  </tr>
  
  <tr>
    <td><div align="right">Agensi</div></td>
    <td><label>$labelAgensi</label></td>
    <!-- <td><div align="right">No. Fail UPE</div></td>
    <td><label>
      <input name="cmdfailUPE" type="text" id="cmdfailUPE" value="$!noFailUPE">
    </label></td> -->
  </tr>
  
  <tr>
   <!-- <td><div align="right">Urusan</div></td>
    <td><label>
      <input name="txturusan" type="text" id="txturusan" value="$!selectUrusan">
    </label></td>-->
    <td><div align="right">No. Fail PTG</div></td>
    <td><label>
      <input name="cmdfailPTG" type="text" id="cmdfailPTG" value="$!noFailPTG">
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Jenis Tanah</div></td>
    <td><label>
      <input name="txtjenistanah" type="text" id="txtjenistanah" value="$!selectJenisTanah">
    </label></td>
    <td><div align="right">No. Fail PTD</div></td>
    <td><label>
      <input name="cmdfailPTD" type="text" id="cmdfailPTD" value="$!noFailPTD">
    </label></td>
  </tr>
  
 <!-- <tr>
    <td><div align="right">Tarikh Surat UPE</div></td>
    <td><label>
      <input name="txtsuratUPE" type="text" id="txtsuratUPE" value="$!tarikhUPE" />
    </label><a href="javascript:displayDatePicker('txtsuratUPE',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td> -->
    
    <td><div align="right">No. Fail UPT</div></td>
    <td><label>
      <input name="cmdfailUPT" type="text" id="cmdfailUPT" value="$!noFailUPT">
    </label></td>
  </tr>
  
  <tr>
    <td><div align="right">Tarikh Buka Fail</div></td>
    <td><label>
      <input name="txttarikhbukafail" type="text" id="txttarikhbukafail" value="$!tarikhBukaFail" />
    </label><a href="javascript:displayDatePicker('txttarikhbukafail',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
    <td><div align="right">No. Fail Lain</div></td>
    <td><label>
      <input name="cmdfailLain" type="text" id="cmdfailLain" value="$!noFailLain">
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Tajuk</div></td>
    <td><label>
      <textarea name="txttajuk" id="txttajuk" cols="30" rows="2">$!tajuk</textarea>
    </label></td>
    <td></td>
    <td></td>
  </tr>
</table>
</fieldset>


</fieldset>

<script>


function tambahPengarah() {
	doAjaxCall${formName}("tambahPengarah");
	
}

function next() {
	doAjaxCall${formName}("next");
	
}


</script>