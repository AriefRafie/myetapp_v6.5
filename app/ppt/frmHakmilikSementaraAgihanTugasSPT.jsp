<input type="hidden" name="id_tugas" value="$id_tugas">
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_status" value="$id_status">
<fieldset>
<legend><strong>Maklumat Fail</strong></legend>
<table width="100%">
  <tr>
    <td width="29%" align="left">No. Fail</td>
    <td width="1%">:</td>
    <td width="70%">$noFail</td>
  </tr>
  <tr>
    <td width="29%" align="left">No. Permohonan</td>
    <td width="1%">:</td>
    <td width="70%">$noPermohonan</td>
  </tr>
  <tr>
    <td width="29%" align="left">Status</td>
    <td width="1%">:</td>
    <td width="70%">$status</td>
  </tr>
</table>
</fieldset>
<p>
<fieldset>
          <legend><strong>Maklumat Pengagihan / Penyerahan Tugas</strong></legend>
          <table width="100%" border="0" cellspacing="4">
                      
          <tr>
            <td width="29%">Pegawai Pengagih SPT</td>
            <td width="1%">:</td>
            <td width="70%"><input type="text" size="40" name="txtNamaPegawai" id="txtNamaPegawai" value="$pegawaiPengagih" readonly="true" style="text-transform:uppercase" class="disabled"  />
            <input type="hidden" readonly="true" name="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" /></td>
          </tr>
          <tr>
            <td><font color="red">*</font>Tarikh Serah Tugas</td>
            <td>:</td>
            <td><input name="txdTarikhSerahTugas" id="txdTarikhSerahTugas" type="text" value="$txdTarikhSerahTugas" size="11" $readonly class="$disabled"/>
              <a href="javascript:displayDatePicker('txdTarikhSerahTugas',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
          </tr>
          #if($id_tugas == '')
          <tr>
            <td><font color="red">*</font>Negeri</td>
            <td>:</td>
            <td>$SelectNegeri</td>
          </tr>
          #end
          <tr>
            <td><font color="red">*</font>Pegawai Penerima</td>
            <td>:</td>
            <td><input type="text" name="PegawaiPenerima" value="$addPegawaiPenerima" id="PegawaiPenerima" maxlength="80" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" size="40px" readonly="true" class="disabled" />
            <input type="hidden" name="id_pegawai" value="$id_pegawai" /></td>
          </tr>
          <tr>
            <td valign="top"><font color="red">*</font>Jawatan Pegawai Penerima</td>
            <td valign="top">:</td>
            <td><input name="txtjawatan" id="txtjawatan" value="$addtxtjawatan" type="text" readonly="true" onblur="this.value=this.value.toUpperCase();" size="40" class="disabled" /></td>
          </tr>
          <tr>
            <td valign="top">Catatan</td>
            <td valign="top">:</td>
            <td><textarea name="txtCatatan" cols="37%" value="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" rows="3" id="txtCatatan"$readonly class="$disabled">$txtCatatan</textarea></td>
          </tr>
          <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>#if($id_tugas == "")
              <input type="button" name="Simpan2" id="Simpan2" value="Simpan" onclick="javascript:Simpan_Agihan('$id_fail','$id_permohonan');" />
              <input name="cmdBatal3" type="button" value="Kembali" onclick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" />
#end
            
            #if($id_tugas != "")
            <!--<input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:KemaskiniSkrin('$id_permohonan','$id_fail','$id_tugas');" />-->
            <input name="cmdBatal3" type="button" value="Kembali" onclick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" />
#end </td>
          </tr>
  		</table>
  
</fieldset> 
<script>
function doChangeidPegawai() {
    doAjaxCall${formName}("doChangeidPegawai");
	
	
}
function doChangeidNegeri() {

    doAjaxCall${formName}("doChangeidNegeri");
}
function Simpan_Agihan(id_fail,id_permohonan){	

	if(document.${formName}.txdTarikhSerahTugas.value == ""){
		alert("Sila masukkan \"Tarikh Serah Tugas\" terlebih dahulu.");
  		document.${formName}.txdTarikhSerahTugas.focus(); 
		return;
	}
		if(document.${formName}.negeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.negeri.focus(); 
		return;
	}	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "Simpan_Agihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraAgihanTugasSPT&action=Simpan_Agihan";
	document.${formName}.submit();
	
}
function Kembali_skrin1(id_fail,id_permohonan){
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraAgihanTugasSPT&action=Kembali_skrin1";
	document.${formName}.submit();
}
</script>