
<style type="text/css">
<!--
.style5 {font-size: 10px; color: #FF0000; }
.style40 {color: #FF0000}
.style6 {
	color: #000000;
	font-style: italic;
}
.style42 {color: #0000FF}
.style43 {color: #000000}
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
   
    <td width="29%" scope="row"><div align="left" class="style43">NO FAIL</div></td>
    <td width="1%" scope="row"><div align="right" class="style42">:</div></td>
    <td width="70%">
      <span class="style3 style42">
      $noFail.toUpperCase()</span></td>
  </tr>
  <tr>
   
    <td scope="row"><div align="left" class="style43">TAJUK FAIL</div></td>
    <td  scope="row"><div align="right" class="style42">:</div></td>
    <td class="style3 style42">$tajukFail.toUpperCase()</td>
  </tr>
  <tr>
   
    <td scope="row"><div align="left" class="style43">STATUS FAIL</div></td>
    <td  scope="row"><div align="right" class="style42">:</div></td>
    <td class="style3 style42">$status.toUpperCase()</td>
  </tr>
</table>
</fieldset>
&nbsp;
<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabPinjamFail()">PINJAM FAIL</li>
    <li class="TabbedPanelsTab" tabindex="0" >PULANG FAIL</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabSahFail()">PENGESAHAN PINJAMAN FAIL</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
 <fieldset>
       <legend>MAKLUMAT PEMINJAM FAIL</legend>
    <table width="100%">
  <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Nama Pegawai Yg Meminjam Fail</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="70%">
      <label></label>
      <label>$user_Name</label></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Nama PT Fail</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPTFail" id="socPTFail">
                  #foreach($list in $SenaraiPTFail)
                  
                    <option value="$list.user_id" selected>$list.user_id - $list.user_name </option>
                  #end
                  </select></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Pulang Fail</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
    <input name="txtTarikhPulang" type="text" id="txtTarikhPulang" value="$!tarikhPulang" size="10" $readOnly  $disabled/>
    </label>
    <a href="javascript:displayDatePicker('txtTarikhPulang',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if($mode == 'New')
  #end
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label berwarna <span class="style40">*</span> wajib diisi.</span></td>
  </tr>
  <tr>
    <td colspan="4" align="center" scope="row">#if ($mode == 'View')
      <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini()"/>
      <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembali()"/>
#end
    	#if($mode == 'New')
<input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpan()"/>
<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
<input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali()"/>
#end#if($mode == 'Update')
        <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onclick = "update()"/>
<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
<input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembaliTabMasuk()"/>
#end
        #if($mode == 'PaparUpdate')
<input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembali()"/>
#end</td>
  </tr>
</table>
</fieldset>
    </div>
    <div class="TabbedPanelsContent"></div>
 
  </div>
</div>
</fieldset>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
//-->
function tabPinjamFail(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action=tabPinjamFail";
	document.${formName}.submit();
}
function tabSahFail(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action=tabSahFail";
	document.${formName}.submit();
}
</script>
