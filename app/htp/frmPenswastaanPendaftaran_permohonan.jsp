

<legend><strong>PENDAFTARAN PERMOHONAN </strong></legend>
<table width="100%" border="0">
  <tr>
    <td width="20%"></td>
    <td width="29%"></td>
    <td width="20%"></td>
    <td width="29%"></td>
  </tr>
  <tr>
    <td><div align="right">Negeri</div></td>
    <td><label>$selectNegeri</label></td>
    <td><div align="right">No. Fail Seksyen</div></td>
    <td><label>
    <input type="text" name="txtnofailseky" id="txtnofailseky" colour = "red" value="$!nofailsek"  onblur="uppercase()" class="autoselect" disabled />
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Kementerian</div></td>
    <td>$selectKementerian</td>
    <td><div align="right">No. Fail UPE</div></td>
    <td><label>
      <input type="text" name="txtnofailkjp" id="txtnofailkjp" value="$!nofailKJP" />
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Agensi</div></td>
    <td>$selectAgensi</td>
    <td><div align="right">Tarikh Surat UPE</div></td>
    <td><label>
      <input type="text" name="txttarikhsuratKJP" id="txttarikhsuratKJP" />
      </label>
      <a href="javascript:displayDatePicker('txttarikhsuratKJP',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
  </tr>
  <tr>
    <td><div align="right">Urusan</div></td>
    <td><select name="flag_syarat" id="flag_syarat" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled >
      <option value="4">906-Penswastaan Tanah</option>
    </select></td>
    <td><div align="right">No. Fail Lain</div></td>
    <td><label>
      <input type="text" name="txtnofaillain" id="txtnofaillain" value="$!nofaillain" />
    </label></td>
  </tr>
  
 
  <tr>
    <td><div align="right">Jenis Fail</div></td>
    <td>$selectTarafKeselamatan</td>
   <td>
     
    <!--<td><div align="right">Status Tanah</div></td>
    <td>$selectStatusTanah</td>!-->
    <div align="right">Tarikh Buka Fail</div>
    <td><label>
      <input type="text" name="txttarikhbukafail" id="txttarikhbukafail" />
      </label>
      <a href="javascript:displayDatePicker('txttarikhbukafail',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
  </td>
  </tr>
  
  <tr>
    <td><div align="right">Tajuk</div></td>
    <td><label>
      <textarea name="txttajuk" id="txttajuk" cols="30" rows="3" value="$!tajuk" onkeyup="this.value=this.value.toUpperCase();"></textarea>
    </label></td>
    <td></td>
    <td></td>
  </tr>
</table>
<label> <br>
</label>
<label></label> 
<label></label>
<label>
<div align="center">

  
  <input type="button" name="cmdsimpan" id="cmdsimpan" value="Simpan"  onclick="simpanPendaftaranPermohonan()"/>
  <input type="reset" name="cmdkosongkan" id="cmdkosongkan" value="Kosongkan">
  <input type="button" name="cmdbatal" id="cmdbatal" value="Kembali" onclick="backListSenarai($id_permohonan)" />

 
</div>
</label>
<label>

<div align="center"></div>
</label>
<label></label>
</fieldset>

<script>

function simpanPendaftaranPermohonan() {

	doAjaxCall${formName}("simpanPendaftaranPermohonan");		
}

function doChangeKementerian() {
	doAjaxCall${formName}("simpanPendaftaranPermohonan");		
}

function next() {
	doAjaxCall${formName}("next");
	
}

function batalMaklumat() {
	document.${formName}.id_kemaskini.value = batalMaklumat;
	document.${formName}.command.value = "pksemakanseterus";
	document.${formName}.action = "";
	document.${formName}.submit();
}
	function backListSenarai() {
alert ('back');
	doAjaxCall${formName}("backListSenarai");	

}

</script>

