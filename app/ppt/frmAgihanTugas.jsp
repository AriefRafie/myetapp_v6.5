
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>(dd/mm/yyyy)</font></i>")

#foreach ( $senarai in $PermohonanListView )
	#set($id_tugas=$senarai.id_tugas)
    #set($no_fail=$senarai.no_fail)
    #set($no_permohonan=$senarai.no_permohonan)
    #set($keterangan=$senarai.keterangan) 
#end
<input type="hidden" name="id_tugas" value="$id_tugas"> 
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_status" value="$id_status">

<!---------------------------------------------------- default mode -------------------------------------------------->
#if($default=="yes")

<fieldset>
   <legend>Maklumat Fail</legend>          
        <table width="100%" border="0" cellspacing="4">
          <tr>
            <td width="26%">Bil.Permohonan</td>
            <td width="74%">:&nbsp;$no_permohonan</td>
          </tr>
          <tr>
            <td>No.Fail Permohonan</td>
            <td>:&nbsp;$no_fail</td>
          </tr>
          
          <!-- <tr>
            <td>Status</td>
            <td>:&nbsp;$keterangan</td>
          </tr> -->
          
        </table>
  </fieldset> 
  
#end

<!---------------------------------------------------- add mode -------------------------------------------------->
#if( $id_status == 127 )
	
		<p>
		<fieldset>
          <legend>Maklumat Pengagihan / Penyerahan Tugas</legend>
          <table width="100%" border="0" cellspacing="4">
                      
          <tr>
            <td width="26%">Pegawai Pengagih</td>
            <td width="1%">:</td>
            <td width="73%"><input type="text" size="50" name="txtNamaPegawai" id="txtNamaPegawai" value="${session.getAttribute("_portal_username")}" readonly="true" style="text-transform:uppercase" class="disabled"  />
            <input type="hidden" readonly="true" name="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" /></td>
          </tr>
          <tr>
            <td>Tarikh Serah Tugas</td>
            <td>:</td>
            <td>$!currentDATE&nbsp;$!frmtdate</td>
            <input type="hidden" name="txdTarikhSerahTugas" id="txdTarikhSerahTugas" value="$!currentDATE" />
            <!-- <td><input name="txdTarikhSerahTugas" id="txdTarikhSerahTugas" type="text" onblur="check_date(this)" value="$txdTarikhSerahTugas" size="10" />
              <a href="javascript:displayDatePicker('txdTarikhSerahTugas',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td> -->
          </tr>
          <tr>
            <td><font color="red">*</font>Pegawai Penerima</td>
            <td>:</td>
            <td>$selectPegawai</td>
          </tr>
          <tr>
            <td valign="top">Jawatan Pegawai Penerima</td>
            <td valign="top">:&nbsp;</td>
            <td><input name="txtjawatan" id="txtjawatan" value="$txtjawatan" type="text" readonly="true" size="40" class="disabled" /></td>
          </tr>
          <tr>
            <td valign="top">Catatan</td>
            <td valign="top">:</td>
            <td><textarea name="txtCatatan" cols="47%" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" rows="3" id="txtCatatan">$txtCatatan</textarea></td>
          </tr>
  		</table>
  
  </fieldset> 

#else 
<!---------------------------------------------------- view mode -------------------------------------------------->

		<p>
		<fieldset>
          <legend><strong>Maklumat Pengagihan / Penyerahan Tugas</strong></legend>
          #foreach($senarai in $PermohonanListView) 
          <table width="100%" border="0" cellspacing="4">
          <tr>
            <td width="26%">Pegawai Pengagih</td>
            <td width="1%">:</td>
            <td width="73%"><input type="text" size="50" name="txtNamaPegawai" id="txtNamaPegawai" value="$senarai.user_name" readonly class="disabled" style="text-transform:uppercase"/>
            <input type="hidden" name="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" /></td>
          </tr>
          <tr>
            <td>Tarikh Serah Tugas</td>
            <td>:</td>
            <!-- <td><input name="txdTarikhSerahTugas" type="text" value="$senarai.tarikh_agih" readonly class="disabled" size="10" /></td> -->
            <td>$!senarai.tarikh_agih&nbsp;$!frmtdate</td>
          </tr>
          <tr>
            <td>Pegawai Penerima</td>
            <td>:</td>
            <td><input name="txtPegawaiPenerima" type="txtPegawaiPenerima" value="$senarai.nama_pegawai" readonly class="disabled" size="50" /></td>
          </tr>
          <tr>
            <td valign="top">Jawatan Penerima</td>
            <td valign="top">:&nbsp;</td>
            <td><input name="txtJwtPegawaiPenerima" type="txtJwtPegawaiPenerima" value="$senarai.jawatan" readonly class="disabled" size="40" /></td>
          </tr>
          <tr>
            <td valign="top">Catatan</td>
            <td valign="top">:</td>
            <td><textarea name="txtCatatan" cols="47%" rows="3" id="txtCatatan" style="text-transform:uppercase;" readonly class="disabled" style="text-transform:uppercase">$senarai.perihal_agih</textarea></td>
          </tr>
  		</table>
        #end
  		</fieldset> 
#end     
  
  <p>
  <div align="center">
    #if( $id_status == 127 )
    <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_Agihan('$id_fail','$id_permohonan');" />          
    <input name="cmdBatal" type="button" value="Kembali" onclick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" />
    #else
  	<!--<input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:KemaskiniSkrin('$id_permohonan','$id_fail','$id_tugas');" />-->          
    <input name="cmdBatal2" type="button" value="Kembali" onclick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" />
    #end  </div>

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function Simpan_Agihan(id_fail,id_permohonan){	
/*	if(document.${formName}.txdTarikhSerahTugas.value == ""){
		alert("Sila masukkan \"Tarikh Serah Tugas\" terlebih dahulu.");
  		document.${formName}.txdTarikhSerahTugas.focus(); 
		return;
	}
*/	if(document.${formName}.socPegawai.value == ""){
		alert("Sila pilih \"Pegawai Penerima\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.method = "POST";
	document.${formName}.command.value = "Simpan_Agihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiAgihanTugas";
	document.${formName}.submit();
}
function Kembali_skrin1(id_fail,id_permohonan){
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Kembali_skrin1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiAgihanTugas";
	document.${formName}.submit();
}
function KemaskiniSkrin(id_permohonan,id_fail,id_tugas){
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_tugas.value = id_tugas;
	document.${formName}.command.value = "KemaskiniSkrin";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiAgihanTugas";
	document.${formName}.submit();
}
function doChangeidPegawai() {
	document.${formName}.command.value = "doChangeidPegawai";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiAgihanTugas"; 
	document.${formName}.submit();
	
}
</script> 