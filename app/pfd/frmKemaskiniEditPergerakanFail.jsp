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
<input name="idFail" type="hidden" value="$idFail" />
<input name="idMasuk" type="hidden" value="$idMasuk" />
<input name="idPergerakanfail" type="hidden" value="$idPergerakanfail" />
<table width="100%">
 
   <input name="idFail" type="hidden" value="$fail.idFail" />
  <tr>
    <td width="29%" scope="row">NO FAIL</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <span class="style3">
      $noFail.toUpperCase()</span></td>
  </tr>
  <tr>
    <td scope="row">TAJUK FAIL</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td class="style3">$tajukFail.toUpperCase()</td>
  </tr>
  <tr>
    <td scope="row">STATUS FAIL</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td class="style3">$status.toUpperCase()</td>
  </tr>
</table>
</fieldset>
&nbsp;
<fieldset>
<legend>MAKLUMAT PERGERAKAN FAIL</legend>
<table width="100%">
  <tr>
    <td width="2%" scope="row"><span class="style2">*</span> </td>
    <td width="27%" scope="row">Didaftar Oleh</td>
    <td width="1%" scope="row">:</td>
    <td width="70%" >$user_Name</td>
  </tr>
  <tr>
    <td  scope="row"><span class="style2">*</span> </td>
    <td  scope="row"><span class="style2 style6">Nama Pegawai Penghantar</span></td>
    <td  scope="row">:</td>
    <td >$selectPegawaiA </td>
  </tr>
  <tr>
    <td scope="row"><span class="style2">*</span> </td>
    <td scope="row">Nama Pegawai Penerima</td>
    <td width="1%" scope="row">:</td>
    <td> $selectPegawaiB </td>
  </tr>
#if($mode == 'Update')
<tr>
  <td scope="row"><span class="style2">*</span> </td>
  <td scope="row">Fail Telah Dikembalikan</td>
  <td width="1%" scope="row">:</td>
  <td><label>
    <input type="radio" name="radio" id="sorFailTlhDikembalikan" value="sorFailTlhDikembalikan" $readonly/>
    </label>  </td>
</tr>
#end
<tr>
  <td scope="row"><span class="style2">*</span> </td>
  <td scope="row">Tarikh Fail Masuk</td>
  <td width="1%" scope="row">:</td>
  <td><label>
    <input name="txtTarikhFailMsk" type="text" id="txtTarikhFailMsk" value="$!tarikhFailMsk" size="10" $readOnly $disabled/>
    </label>
    <a href="javascript:displayDatePicker('txtTarikhFailMsk',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
</tr>
<tr>
  <td scope="row"><span class="style2">*</span> </td>
  <td scope="row">Tarikh Fail Diedarkan</td>
  <td width="1%" scope="row">:</td>
  <td><label>
    <input name="txtTarikhFailKeluar" type="text" id="txtTarikhFailKeluar" value="$!tarikhFailKeluar" size="10" $readOnly  $disabled/>
    </label>
    <a href="javascript:displayDatePicker('txtTarikhFailKeluar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
</tr>
<tr>
  <td scope="row"><span class="style2">*</span> </td>
  <td scope="row">Tempoh SDP </td>
  <td width="1%" scope="row">:</td>
  <td><label>
     Dari
     <input name="txtTempohSDPDari" type="text" id="txtTempohSDPDari" value="$!tempohSdpDari" size="10" $readOnly  $disabled/>
    </label>
      <a href="javascript:displayDatePicker('txtTempohSDPDari',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> Hingga
    <input name="txtTempohSDPHingga" type="text" id="txtTempohSDPHingga" value="$!tempohSdpHingga" size="10" $readOnly  $disabled/>
    <a href="javascript:displayDatePicker('txtTempohSDPHingga',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </td>
</tr>
<tr>
  <td scope="row" valign="top">&nbsp;</td>
  <td scope="row" valign="top">  Catatan</td>
  <td width="1%" scope="row" valign="top">:</td>
  <td><label>
    <textarea name="txtCatatan" cols="41" id="txtCatatan"  $readonly  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" onchange="javascript:this.value=ucwords(this.value)" $disabled >$!catatan</textarea>
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
<tr></tr>
<tr align="center"></tr>
<tr align="center"></tr>
</table>
</fieldset>
&nbsp;
<fieldset>
  <legend>SENARAI PERGERAKAN FAIL</legend>

<table width="100%">
  <tr class="table_header">
    <td width="1%" scope="row">No</td>
    <td width="15%">No Fail</td>
    <td width="15%">Nama Pegawai Penghantar</td>
    <td width="15%">Nama Pegawai Penerima</td>
    <td width="10%">Tarikh Fail Masuk</td>
    <td width="10%">Tarikh Fail Keluar</td>
    <td width="15%">Di Daftar Oleh</td>
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
    <td class="$row"><a href="javascript:papar_pergerakan('$prgrkn.idPergerakanfail')" class="style1 style1">$prgrkn.no_Fail</a></td>
    #else
    <td class="$row">$prgrkn.no_Fail</td>
    #end
    <td class="$row">$prgrkn.nama_Pegawai1</td>
    <td class="$row">$prgrkn.nama_Pegawai2</td>
    <td class="$row">$prgrkn.tarikh_Fail_Masuk</td>
    <td class="$row">$prgrkn.tarikh_Fail_Keluar</td>
    <td class="$row">$prgrkn.didaftar_Oleh</td>

  </tr>
  #end
</table>
</fieldset>
<table width="100%" border="0" >
  <tr>
    <td align="right"><strong>CL-05-10</strong></td>
  </tr>
</table>



<script>
function batal(){

	document.${formName}.action1.value = "SenaraiFail";
	document.${formName}.submit();
}
function kembali(id){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=paparPergerakan";
	document.${formName}.idFail.value = id;
	document.${formName}.submit();

}
function simpan1() {

	var DateMasuk = document.${formName}.txtTarikhFailMsk.value;
	var DateKeluar = document.${formName}.txtTarikhFailKeluar.value;
	var DateSDPdari = document.${formName}.txtTempohSDPDari.value;
	var DateSDPhingga = document.${formName}.txtTempohSDPHingga.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		
	if( DateMasuk > today)
	{
		alert('Sila pastikan " Tarikh Fail Masuk " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTarikhFailMsk.focus();
		return false;
	}
	if( DateKeluar > today)
	{
		alert('Sila pastikan " Tarikh Fail Diedarkan " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTarikhFailKeluar.focus();
		return false;
	}
	
	if( DateSDPdari > today)
	{
		alert('Sila pastikan " Tarikh SDP Dari " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTempohSDPDari.focus();
		return false;
	}
	if( DateSDPhingga > today)
	{
		alert('Sila pastikan " Tarikh SDP Hingga " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTempohSDPHingga.focus();
		return false;
	}

//	if (document.${formName}.socPegawaiA.value == ""){
//		alert('Sila masukkan " Nama Pegawai Penghantar " terlebih dahulu.');
//		document.${formName}.socPegawaiA.focus();
//		return;
//	} 
	if (document.${formName}.socPegawaiB.value == ""){
		alert('Sila masukkan " Nama Pegawai Penerima " terlebih dahulu.');
		document.${formName}.socPegawaiB.focus();
		return;
	} 
	
	if (document.${formName}.txtTarikhFailMsk.value == ""){
		alert('Sila masukkan " Tarikh Fail Masuk " terlebih dahulu.');
		document.${formName}.txtTarikhFailMsk.focus();
		return;
	} 
	if (document.${formName}.txtTarikhFailKeluar.value == ""){
		alert('Sila masukkan " Tarikh Fail Diedarkan " terlebih dahulu.');
		document.${formName}.txtTarikhFailKeluar.focus();
		return;
	} 
		if (document.${formName}.txtTempohSDPDari.value == ""){
		alert('Sila masukkan " Tarikh SDP Dari " terlebih dahulu.');
		document.${formName}.txtTempohSDPDari.focus();
		return;
	} 
	if (document.${formName}.txtTempohSDPHingga.value == ""){
		alert('Sila masukkan " Tarikh SDP Hingga " terlebih dahulu.');
		document.${formName}.txtTempohSDPHingga.focus();
		return;
	} 
	
	if (!window.confirm("Anda Pasti?")) {
		return;
	}
	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=simpanPF";
	document.${formName}.submit();
		

	
}
	//if (document.${formName}.idPergerakanfail.value == ""){
	//	document.${formName}.mode.value = "tambahBaru";
	//}
	//else{
	//document.${formName}.mode.value = "kemaskini";
	//}
	
function kemaskini(){
	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=kemaskiniPergerakanfail";
	document.${formName}.submit();

}
function update1(){
	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=updatePergerakanfail";
	//document.${formName}.idPergerakanfail.value = idPergerakanfail;
	document.${formName}.submit();

}
function papar_pergerakan(idPergerakanfail){

	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PergerakanFail&action1=paparPergerakanfail";
	//document.${formName}.mode.value = "";
	document.${formName}.idPergerakanfail.value = idPergerakanfail;
	document.${formName}.submit();

}

//function papar_pergerakan(id){
//
//	document.${formName}.action1 = "papar";
//	document.${formName}.mode.value = "";
//	document.${formName}.idPergerakanfail.value = id;
//	document.${formName}.submit();
//
//}
</script>