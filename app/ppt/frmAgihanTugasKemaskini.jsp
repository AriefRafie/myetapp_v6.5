<form name="f1" method="POST">
<input type="hidden" name="id_tugas" value="$id_tugas">
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="command">
<fieldset>
#foreach ( $senarai in $PermohonanList )
<!---------------------------------------------------- default mode -------------------------------------------------->
        <fieldset>
          <legend>Maklumat Fail</legend>
        <table width="100%" border="0" cellspacing="4">
          <tr>
            <td width="20%">No. Fail</td>
            <td width="80%">:&nbsp;<input type="text" size="40" onkeyup="validatetxtNoHM(this,this.value);" name="txtNoFail" id="txtNoFail" style="text-transform:uppercase;" value="$senarai.no_fail" readonly="true" /></td>
          </tr>
          <tr>
            <td>No. Permohonan</td>
            <td>:&nbsp;<input type="text" size="40" name="txtNoPermohonan" id="txtNoPermohonan" style="text-transform:uppercase;" value="$senarai.no_permohonan" readonly="true" /></td>
          </tr>
          <tr>
            <td>No. Rujukan Seksyen</td>
            <td>:&nbsp;<input type="text" size="40" name="txtRujSek" id="txtRujSek" style="text-transform:uppercase;" value="NULL" readonly="true" /></td>
          </tr>
        </table>
         
  </fieldset> 
		<p>
  <fieldset>
  <legend>Maklumat Pengagihan / Penyerahan Tugas</legend>
          <table width="100%" border="0">
          <tr>
            <td width="20%">Pegawai Pengagih</td>
            <td width="1%">:</td>
            <td width="79%"><input type="text" size="40" name="txtNamaPegawai" id="txtNamaPegawai" value="$senarai.nama_pegawaipengagih" readonly="true"  style="text-transform:uppercase"/></td>
          </tr>
          <tr>
            <td>Tarikh Serah Tugas</td>
            <td>:</td>
            <td><input name="txdTarikhSerahTugas" type="text" onblur="check_date(this)" value="$senarai.tarikh_agih" size="10" />
              <a href="javascript:displayDatePicker('txdTarikhSerahTugas',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
          </tr>
          <tr>
            <td>Pegawai Penerima</td>
            <td>:</td>
            <td>$selectEditPegawai
            <input type="hidden" name="id_existPegawai" value="$senarai.id_pegawaipenerima" /></td>
          </tr>
          <tr>
            <td valign="top">Catatan</td>
            <td valign="top">:</td>
            <td><textarea name="txtCatatan" cols="37%" rows="3" onBlur="this.value=this.value.toUpperCase();" id="txtCatatan">$senarai.perihal_agih</textarea></td>
          </tr>
  </table>
#end
  </fieldset>    
  <p>
  <div align="center">
    <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_EditAgihan('$id_permohonan','$id_fail','$id_tugas');" />
    <input name="cmdBatal" type="button" value="Kembali" onclick="javascript: Kembali_skrin2('$id_permohonan','$id_fail','$id_tugas');" />
  </div>
</fieldset>
</form>

<script>
function Simpan_Agihan(id_fail,id_permohonan){	
	if(document.f1.txdTarikhSerahTugas.value == ""){
		alert("Sila masukkan \"Tarikh Serah Tugas\" terlebih dahulu.");
  		document.f1.txdTarikhSerahTugas.focus(); 
		return;
	}
		if(document.f1.socPegawai.value == ""){
		alert("Sila pilih \"Pegawai Penerima\" terlebih dahulu.");
  		document.f1.socPegawai.focus(); 
		return;
	}	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.f1.method = "POST";
	document.f1.command.value = "Simpan_Agihan";
	document.f1.action = "";
	document.f1.submit();
}
function Simpan_EditAgihan(id_permohonan,id_fail,id_tugas){
	if(document.f1.txdTarikhSerahTugas.value == ""){
		alert("Sila masukkan \"Tarikh Serah Tugas\" terlebih dahulu.");
  		document.f1.txdTarikhSerahTugas.focus(); 
		return;
	}
		if(document.f1.socEditPegawai.value == ""){
		alert("Sila pilih \"Pegawai Penerima\" terlebih dahulu.");
  		document.f1.socEditPegawai.focus(); 
		return;
	}						
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.id_fail.value = id_fail;
	document.f1.id_tugas.value = id_tugas;
	document.f1.command.value = "Simpan_EditAgihan";
	document.f1.action = "";
	document.f1.submit();
}

function Kembali_skrin2(id_permohonan,id_fail,id_tugas){
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.id_fail.value = id_fail;
	document.f1.id_tugas.value = id_tugas;
	document.f1.command.value = "Kembali_skrin2";
	document.f1.action = "";
	document.f1.submit();
}
</script> 