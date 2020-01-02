
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
    <td width="1%" scope="row"><div align="right" class="style43">:</div></td>
    <td width="70%">
      <span class="style3 style42">
      $noFail.toUpperCase()</span></td>
  </tr>
  <tr>
   
    <td scope="row"><div align="left" class="style43">TAJUK FAIL</div></td>
    <td  scope="row"><div align="right" class="style43">:</div></td>
    <td class="style3 style42">$tajukFail.toUpperCase()</td>
  </tr>
  <tr>
   
    <td scope="row"><div align="left" class="style43">STATUS FAIL</div></td>
    <td scope="row"><div align="right" class="style43">:</div></td>
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
    <li class="TabbedPanelsTab" tabindex="0" >PENGESAHAN PINJAMAN FAIL</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
     <div class="TabbedPanelsContent"></div>
     <div class="TabbedPanelsContent">
 <fieldset>
<legend>MAKLUMAT PERGERAKAN FAIL</legend>
<table width="100%">
  <tr>
    <td width="2%" scope="row"><span class="style2 style40">*</span> </td>
    <td width="27%" scope="row">Diluluskan Oleh</td>
    <td width="1%" scope="row">:</td>
    <td width="70%" >$user_Name</td>
  </tr>
  <tr>
    <td  scope="row"><span class="style2 style40">*</span> </td>
    <td  scope="row">Nama Pegawai Yg Meminjam Fail</td>
    <td  scope="row">:</td>
    <td >$selectPegawaiA </td>
  </tr>
  <tr>
    <td scope="row"><span class="style2 style40">*</span> </td>
    <td scope="row">Tempoh Pinjam Fail</td>
    <td  scope="row">:</td>
    <td>Dari
      <input name="txtTempohPinjamDari" type="text" id="txtTempohPinjamDari" value="$!tempohPinjamDari" size="10" $readOnly  $disabled/>
      </label>
      <a href="javascript:displayDatePicker('txtTempohPinjamDari',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> Hingga
      <input name="txtTempohPinjamHingga" type="text" id="txtTempohPinjamHingga" value="$!tempohPinjamHingga" size="10" $readOnly  $disabled/>
      <a href="javascript:displayDatePicker('txtTempohPinjamHingga',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td scope="row"><span class="style40">*</span></td>
    <td scope="row">Status Pinjaman Fail</td>
    <td scope="row">:</td>
    <td>
      <label></label>    <select name="socStatusPergerakan" id="socStatusPergerakan" $readOnly $disabled>
        
		#if ($status_Pergerakan == '1')
        
        <option value="0">SILA PILIH</option>
        <option value="1" selected="selected">BELUM DILULUSKAN</option>
        <option value="2">SEDANG DIPINJAM</option>
        <option value="3">TELAH DIPULANGKAN</option>

        #elseif ($status_Pergerakan == '2')
        
        <option value="0">SILA PILIH</option>
        <option value="1">BELUM DILULUSKAN</option>
        <option value="2" selected="selected">SEDANG DIPINJAM</option>
        <option value="3">TELAH DIPULANGKAN</option>
        
        #else
        
        <option value="0">SILA PILIH</option>
        <option value="1">BELUM DILULUSKAN</option>
        <option value="2">SEDANG DIPINJAM</option>
        <option value="3" selected="selected">TELAH DIPULANGKAN</option>
        
        #end
      
      
      </select>
      <label></label></td>
  </tr>
#if($mode == 'Update')
 #end
<tr>
  <td scope="row"><span class="style2 style40">*</span> </td>
  <td scope="row">Tarikh Pinjaman Diluluskan</td>
  <td width="1%" scope="row">:</td>
  <td><label>
    <input name="txtTarikhPinjamanDisahkan" type="text" id="txtTarikhPinjamanDisahkan" value="$!tarikhPinjamanDisahkan" size="10" $readOnly $disabled/>
    </label>
    <a href="javascript:displayDatePicker('txtTarikhPinjamanDisahkan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
</tr>
<tr>
  <td scope="row"><span class="style40">*</span></td>
  <td scope="row">Tarikh Pemulangan Diluluskan</td>
  <td scope="row">:</td>
  <td><label>
    <input name="txtTarikhPulangDisahkan" type="text" id="txtTarikhPulangDisahkan" value="$!tarikhPulangDisahkan" size="10" $readOnly $disabled/>
  </label>
    <a href="javascript:displayDatePicker('txtTarikhPulangDisahkan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
</tr>
<tr>
  <td scope="row" valign="top">&nbsp;</td>
  <td scope="row" valign="top">  Catatan</td>
  <td width="1%" scope="row" valign="top">:</td>
  <td><label>
    <textarea name="txtCatatan" cols="41" id="txtCatatan" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" onchange="javascript:this.value=ucwords(this.value)" $readOnly $disabled>$!catatan</textarea>
    </label>  </td>
</tr>
<tr>
  <td colspan="4" align="center" scope="row">&nbsp;</td>
</tr>
<tr>
  <td colspan="4" align="center" scope="row"><div align="left"><span class="style4 style45 style69 style47 style5"><em>Perhatian : <span class="style6">Pastikan label berwarna</span> merah <span class="style6">wajib diisi.</span></em></span></div></td>
</tr>
<tr>
  <td colspan="4" align="center" scope="row"><p>#if($mode == 'baru')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan1()"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
#elseif($mode == 'papar')
<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali('$idFail')"/>
#elseif($mode == 'kemaskini')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="update1('$idPergerakanfail')"/>
<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
#else
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
#end </p></td>
</tr>
</table>
</fieldset>
    </div>  	
    
    
  </div>
</div>
</fieldset>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:3});
//-->
function tabPinjamFail(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action=tabPinjamFail";
	document.${formName}.submit();
}
function simpan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action=simpanPinjamFail";
	document.${formName}.submit();

}


</script>


