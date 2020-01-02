<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #FF0000}
.style3 {color: #0000FF}
.style4 {font-size: 10px}
.style5 {font-size: 10px; color: #FF0000; }
.style6 {color: #000000}
-->
</style>
<fieldset>
<legend>MAKLUMAT FAIL</legend>


<input name="mode" type="hidden" value="$mode" />
<input name="hiddenButton1" type="hidden" value="$hiddenButton1"/>
<input name="hiddenButton2" type="hidden" value="$hiddenButton2"/>
<input name="idFail" type="text" value="$idFail" />
<input name="idPergerakanfail" type="text" value="$idPergerakanfail" />
<table width="100%">
 
   <input name="idFail" type="hidden" value="$fail.idFail" />
  <tr>
    <td width="29%" scope="row"><div align="left">NO FAIL</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <span class="style3">
      $noFail.toUpperCase()</span></td>
  </tr>
  <tr>
    <td scope="row"><div align="left">TAJUK FAIL</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td class="style3">$tajukFail.toUpperCase()</td>
  </tr>
  <tr>
    <td scope="row"><div align="left">STATUS FAIL</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td class="style3">$keterangan1.toUpperCase()</td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend>MAKLUMAT PERGERAKAN FAIL</legend>
<table width="100%">
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Didaftar Oleh</td>
    <td scope="row">:</td>
    <td>$user_Name</td>
  </tr>
   <tr>
     <td width="2%" scope="row">&nbsp;</td>
    <td width="27%" scope="row"><span class="style2 style6">Nama Pegawai Penghantar</span></td>
    <td width="1%" scope="row">:</td>
    <td width="70%">
     
       $selectPegawaiA</td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Nama Pegawai Penerima</td>
    <td width="1%" scope="row">:</td>
    <td>
  
         $selectPegawaiB    </td>
  </tr>
  #if($mode == 'Update')
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Fail Telah Dikembalikan</td>
    <td width="1%" scope="row">:</td>
    <td>
      <label>
        <input type="radio" name="radio" id="sorFailTlhDikembalikan" value="sorFailTlhDikembalikan" $readOnly/>
        </label>       </td>
  </tr>
  #end
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Tarikh Fail Masuk</td>
    <td width="1%" scope="row">:</td>
    <td>
      <label>
        <input name="txtTarikhFailMsk" type="text" id="txtTarikhFailMsk" value="$tarikhFailMsk" size="10"/>
        </label>
        <a href="javascript:displayDatePicker('txtTarikhFailMsk',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Tarikh Fail Diedarkan</td>
    <td width="1%" scope="row">:</td>
    <td>
      <label>
        <input name="txtTarikhFailKeluar" type="text" id="txtTarikhFailKeluar" value="$tarikhFailKeluar" size="10"/>
        </label>
        <a href="javascript:displayDatePicker('txtTarikhFailKeluar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Tempoh SDP - Dari</td>
    <td width="1%" scope="row">:</td>
    <td>
      <label>
        <input name="txtTempohSDPDari" type="text" id="txtTempohSDPDari" value="$tempohSdpDari" size="10"/>
        </label>
        <a href="javascript:displayDatePicker('txtTempohSDPDari',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
        Hingga
        <input name="txtTempohSDPHingga" type="text" id="txtTempohSDPHingga" value="$tempohSdpHingga" size="10"/>
         <a href="javascript:displayDatePicker('txtTempohSDPHingga',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>        </td>
  </tr>
  <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td scope="row" valign="top">Catatan</td>
    <td width="1%" scope="row" valign="top">:</td>
    <td>
      <label>
        <textarea name="txtCatatan" cols="41" id="txtCatatan"  $readOnly  onChange="javascript:this.value=ucwords(this.value)">$catatan</textarea>
        </label>      </td>
  </tr>
  <tr>
    <td colspan="4" align="center" scope="row">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center" scope="row"><div align="left"><span class="style4 style45 style69 style47 style5"><em>Perhatian : <span class="style6">Pastikan label berwarna</span> merah <span class="style6">wajib diisi.</span></em></span></div></td>
  </tr>
  <tr>
    <td colspan="4" align="center" scope="row">
      <p>#if ($hiddenButton1 != 'TIDAK AKTIF - TUKAR JILID' && $hiddenButton1 != 'TIDAK AKTIF - TUKAR TARAF KESELAMATAN' && $hiddenButton2 != 'SIMPAN DALAM PERHATIAN' && $hiddenButton2 != 'SEDANG DIPINJAM' )#end      </p>    </td>
  </tr>
  <tr align="center">
    
    <td colspan="4">  
 	#if($mode == 'baru')
     <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
     <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
     <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>

    #elseif($mode == 'papar')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="cetak('$!idFail')" />
    

    #elseif($mode == 'kemaskini')
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="update()"/>
     <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
   	#else
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
    #end</td>
  </tr>
</table>
</fieldset>
<fieldset>
  <legend>SENARAI PERGERAKAN FAIL</legend>

  <label>
  <input type="submit" name="cmdTambah" id="cmdTambah" value="Tambah" />
  </label>

<table width="100%" >
  <tr class="table_header">
    <td width="1%" scope="row">No</td>
    <td width="20%">No Fail</td>
    <td width="20%">Nama Pegawai Penghantar</td>
    <td width="20%">Nama Pegawai Penerima</td>
    <td width="10%">Tarikh Fail Masuk</td>
    <td width="10%">Tarikh Fail Keluar</td>
  </tr>
  #foreach ($prgrkn in $Senaraipergerakan)
  
   #if ($prgrkn.bil == '') 
  	#set ($row = 'row1')
  #elseif ($prgrkn.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$prgrkn.bil</td>
    #if ($prgrkn.bil != '')
    <td class="$row"><a href="javascript:papar_pergerakan('$prgrkn.id_Pergerakanfail')" class="style1 style1">$prgrkn.no_Fail</a></td>
    #else
    <td class="$row">$prgrkn.no_Fail</td>
    #end
    <td class="$row">$prgrkn.nama_Pegawai1</td>
    <td class="$row">$prgrkn.nama_Pegawai2</td>
    <td class="$row">$prgrkn.tarikh_Fail_Masuk</td>
    <td class="$row">$prgrkn.tarikh_Fail_Keluar</td>
  </tr>
  #end
</table>
</fieldset>
<table width="100%">
  <tr>
    <td align="right"><strong>CL-05-10</strong></td>
  </tr>
</table>


<script>
function batal(){

	document.${formName}.action1.value = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=SenaraiFail";
	document.${formName}.submit();
}
function kembali(){

	document.${formName}.action1.value = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=SenaraiFail";
	document.${formName}.submit();
}
function simpan(){
	if ( !window.confirm("Anda Pasti?") ) return;
	if (document.${formName}.socPegawaiA.value == ""){
		alert('Sila masukkan " Nama Pegawai Penghantar " terlebih dahulu.');
		document.${formName}.socPegawaiA.focus();
		return;
	} 
	if (document.${formName}.txtTarikhFailMsk.value == ""){
		alert('Sila masukkan " Tarikh Fail Masuk " terlebih dahulu.');
		document.${formName}.txtTarikhFailMsk.focus();
		return;
	}
	document.${formName}.action1.value = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=simpan";
	if (document.${formName}.idPergerakanfail.value == ""){
		document.${formName}.mode.value = "tambahBaru";
	}
	else{
	document.${formName}.mode.value = "kemaskini";
	}
	document.${formName}.submit();
}
function kemaskini(){
	
	document.${formName}.action1.value = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=editData";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function papar_pergerakan(id){

	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=papar";
	document.${formName}.mode.value = "";
	document.${formName}.idPergerakanfail.value = id;
	document.${formName}.submit();

}
</script>