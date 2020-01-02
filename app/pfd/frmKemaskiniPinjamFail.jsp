
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
    <td scope="row"><div align="right" class="style43">:</div></td>
    <td class="style3 style42">$tajukFail.toUpperCase()</td>
  </tr>
  <tr>
    
    <td scope="row"><div align="left" class="style43">STATUS FAIL</div></td>
    <td  scope="row"><div align="right" class="style43">:</div></td>
    <td class="style3 style42">$status.toUpperCase()</td>
  </tr>
</table>
</fieldset>
&nbsp;
<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" >PINJAM FAIL</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<div class="TabbedPanelsContent">
     <fieldset>
       <legend>MAKLUMAT PEMINJAM FAIL</legend>
     #if($emel == 'success')
  	<div class="success">Telah di emel ke pegawai yang berkenaan.</div>
 	#end
    <table width="100%">
  <tr>
    <td width="2%" align="left" valign="top" scope="row">&nbsp;</td>
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
    <td><select name="socPTFail" id="socPTFail" $readonly $disabled>
    			#if ($mode == 'View' || $mode == 'New' || $mode == 'update')
      				<option value="" >SILA PILIH</option>
                #end
                  #foreach($list in $SenaraiPTFail)
                  	#if($selectidorang == $list.user_id)
                    <option value="$list.user_id" selected>$list.user_name </option>
                    #else
                    <option value="$list.user_id" >$list.user_name </option>
                  #end
                  #end
                  </select></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tujuan Pinjaman</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
      <textarea name="txtTujuan" cols="41" id="txtTujuan" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonly $disabled>$!tujuan</textarea>
    </label></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tempoh Pinjam Fail</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>Dari
      <input name="txtTempohPinjamDari" type="text" id="txtTempohPinjamDari" value="$!tempohPinjamDari" size="10" onfocus="tarikhpinjam();" $readonly  $disabled/>
      <a href="javascript:displayDatePicker('txtTempohPinjamDari',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> Hingga
      <input name="txtTempohPinjamHingga" type="text" id="txtTempohPinjamHingga" value="$!tempohPinjamHingga" size="10" $readonly  $disabled/>
      <a href="javascript:displayDatePicker('txtTempohPinjamHingga',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
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
    <td colspan="4" align="center" scope="row">
        #if ($mode == 'View')
        <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini()"/>
        <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick = "hapus('$idPergerakanfail')"/>
        <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembali()"/>
        #elseif($mode == 'New')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpan()"/>
        <input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali()"/>
        #elseif($mode == 'update')
                <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onclick = "update1()"/>
        <input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembali()"/>
        #else
        <input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembali()"/>
        #end
    </td>
  </tr>
</table>
</fieldset>
    </div>    
    
  </div>
</div>
</fieldset>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
function emelPinjamFail(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=emelPinjamFail";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=paparPergerakan";
	document.${formName}.submit();
}
function tabSahFail(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=tabSahFail";
	document.${formName}.submit();
}

function tarikhpinjam(){
var BDate = document.${formName}.txtTempohPinjamDari.value;
		
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth();
		curr_month++
		var curr_year = d.getFullYear();
	
		var today = curr_date +'/' + curr_month +'/' + curr_year;
	
		if( BDate > today)
		{
			alert('Sila pastikan " Tarikh Pinjam " tidak melebihi tarikh fail dipulangkan.');
			document.${formName}.txtTempohPinjamDari.focus();
			return false;
		}


}
function simpan(){

		if (document.${formName}.socPTFail.value == ""){
				alert('Sila masukkan " Nama PT Fail " terlebih dahulu.');
				document.${formName}.socPTFail.focus();
				return;
		}  
		
				if (document.${formName}.txtTujuan.value == ""){
				alert('Sila masukkan " Tujuan Pinjaman " terlebih dahulu.');
				document.${formName}.txtTujuan.focus();
				return;
		}
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=simpanPinjamFail";
		document.${formName}.submit();

}
function kemaskini(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=kemaskiniPinjamFail";
	document.${formName}.submit();
}

function hapus(id){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=hapusPinjamFail&idPergerakanfail="+id;
	document.${formName}.submit();
}

function update1(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=updatePinjamFail";
	document.${formName}.submit();
}
</script>


