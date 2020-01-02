
#foreach ( $senarai in $PermohonanListView )
	#set($id_tugas=$senarai.id_tugas)
    #set($no_fail=$senarai.no_fail)
    #set($no_permohonan=$senarai.no_permohonan)
    #set($keterangan=$senarai.keterangan)  
#end
<input type="hidden" name="id_tugas" value="$id_tugas"> 
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">

<br/>

<!---------------------------------------------------- default mode -------------------------------------------------->
#if($default=="yes")

 <fieldset>
    <legend>Maklumat Fail</legend>         
        <table width="100%" border="0" cellspacing="4">
          <tr>
            <td width="26%">No.Fail</td>
          	<td>:&nbsp;$no_fail</td>
          </tr>
          <tr>
            <td>No.Permohonan</td>
           	<td>:&nbsp;$no_permohonan</td>
          </tr>
          <tr>
            <td>Status</td>
            <td>:&nbsp;$keterangan</td>
          </tr>
        </table>
</fieldset>

#end

<!---------------------------------------------------- add mode -------------------------------------------------->
#if($id_tugas == "")
	
<br/>
		<fieldset>
          <legend>Maklumat Pengagihan / Penyerahan Tugas</legend>
          <table width="100%" border="0" cellspacing="4">                     
          <tr>
            <td width="26%">&nbsp;&nbsp;Pegawai Pengagih SPT</td>
            <td width="1%">:</td>
            <td width="73%"><input type="text" size="40" name="txtNamaPegawai" id="txtNamaPegawai" value="${session.getAttribute("_portal_username")}" readonly style="text-transform:uppercase" class="disabled"  />
          </tr>
          <tr>
            <td><font color="red">*</font>Tarikh Serah Tugas</td>
            <td>:</td>
            <td><input name="txdTarikhSerahTugas" id="txdTarikhSerahTugas" onblur="check_date(this)" type="text" value="$txdTarikhSerahTugas" size="11" />
              <a href="javascript:displayDatePicker('txdTarikhSerahTugas',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
          </tr>
          <tr>
            <td><font color="red">*</font>Negeri</td>
            <td>:</td>
            <td>$SelectNegeri</td>
          </tr>
<!--          <tr>
            <td><font color="red">*</font>Pegawai Penerima</td>
            <td>:</td>
            <td>$SelectPegawai
              <input type="text" name="PegawaiPenerima" value="$addPegawaiPenerima" id="PegawaiPenerima" maxlength="80" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" size="40px" readonly class="disabled" />
            <input type="hidden" name="id_pegawai" value="$id_pegawai" /></td>
          </tr>-->
          <tr>
            <td><font color="red">*</font>Pegawai Penerima</td>
            <td>:</td>
            <td><input type="text" name="PegawaiPenerima" value="$addPegawaiPenerima" id="PegawaiPenerima" maxlength="80" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" size="40px" readonly class="disabled" />
            <input type="hidden" name="id_pegawai" value="$id_pegawai" /></td>
          </tr>          
          <tr>
            <td valign="top"><font color="red">*</font>Jawatan Pegawai Penerima</td>
            <td valign="top">:</td>
            <td><input name="txtjawatan" id="txtjawatan" value="$addtxtjawatan" type="text" readonly onblur="this.value=this.value.toUpperCase();" size="40" class="disabled" /></td>
          </tr>
          <tr>
            <td valign="top">&nbsp;&nbsp;Catatan</td>
            <td valign="top">:</td>
            <td><textarea name="txtCatatan" cols="37%" value="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" rows="3" id="txtCatatan">$txtCatatan</textarea></td>
          </tr>
  		</table>
  
  </fieldset> 

#end  
<!---------------------------------------------------- view mode -------------------------------------------------->
#if($id_tugas != "")

#foreach($senarai in $PermohonanListTugas) 
	#set($user_name = $senarai.user_name)
    #set($tarikh_agih = $senarai.tarikh_agih)
    #set($nama_pegawai = $senarai.nama_pegawai)
    #set($jawatan = $senarai.jawatan)
    #set($perihal_agih = $senarai.perihal_agih)
#end

<br/>
		<fieldset>
          <legend>Maklumat Pengagihan / Penyerahan Tugas</legend>
          
          <table width="100%" border="0" cellspacing="4">
          <tr>
            <td width="26%">Pegawai Pengagih</td>
            <td width="1%">:</td>
            <td width="73%"><input type="text" size="40" name="txtNamaPegawai" id="txtNamaPegawai" value="$user_name" readonly  style="text-transform:uppercase" class="disabled"/>
            <!--<input type="hidden" readonly="true" name="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" />--></td>
          </tr>
          <tr>
            <td>Tarikh Serah Tugas</td>
            <td>:</td>
            <td><input name="txdTarikhSerahTugas" type="text" value="$tarikh_agih" readonly size="11" class="disabled" /></td>
          </tr>
          <tr>
            <td>Pegawai Penerima</td>
            <td>:</td>
            <td><input name="txtPegawaiPenerima" type="txtPegawaiPenerima" value="$nama_pegawai" readonly size="40" class="disabled" /></td>
          </tr>
          <tr>
            <td valign="top">Jawatan Penerima</td>
            <td valign="top">:</td>
            <td><input name="txtJwtPegawaiPenerima" type="txtJwtPegawaiPenerima" value="$jawatan" readonly size="40" class="disabled" /></td>
          </tr>
          <tr>
            <td valign="top">Catatan</td>
            <td valign="top">:</td>
            <td><textarea name="txtCatatan" cols="37%" value="" onblur="this.value=this.value.toUpperCase();" rows="3" id="txtCatatan" readonly class="disabled" style="text-transform:uppercase">$perihal_agih</textarea></td>
          </tr>
  		</table>       
  		</fieldset> 
#end     
  
  <p>
  <div align="center">
    #if($id_tugas == "")
    <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_Agihan('$id_fail','$id_permohonan');" />          
    <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" />
    #end
    
    #if($id_tugas != "")
  	<!--<input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:KemaskiniSkrin('$id_permohonan','$id_fail','$id_tugas');" />-->          
    <input name="cmdBatal" type="button" value="Kembali" onclick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" />
    #end  </div>

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
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
	document.${formName}.method = "POST";
	document.${formName}.command.value = "Simpan_Agihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiAgihanTugasSPTSek8";
	document.${formName}.submit();
}
function Kembali_skrin1(id_fail,id_permohonan){
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Kembali_skrin1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiAgihanTugasSPTSek8";
	document.${formName}.submit();
}
function KemaskiniSkrin(id_permohonan,id_fail,id_tugas){
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_tugas.value = id_tugas;
	document.${formName}.command.value = "KemaskiniSkrin";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiAgihanTugasSPTSek8";
	document.${formName}.submit();
}
function doChangeidPegawai() {
	document.${formName}.command.value = "doChangeidPegawai";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiAgihanTugasSPTSek8"; 
	document.${formName}.submit();
	
}
function doChangeidNegeri2() {
	document.${formName}.command.value = "doChangeidNegeri2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSenaraiAgihanTugasSPTSek8"; 
	document.${formName}.submit();		
}
</script> 