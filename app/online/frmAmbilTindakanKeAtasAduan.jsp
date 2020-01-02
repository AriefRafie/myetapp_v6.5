
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td>
    <fieldset>
<legend><strong>::Aduan::</strong></legend>
<table width="100%" border="0" cellpadding="2">
 <input name="action" type="hidden" value="$action" />

 #foreach($view in $ViewAduan)
 <input name="idAduan" type="hidden" value="$view.id_Aduan" />
  <tr>
      <td width="29%">No Aduan</td>
    <td width="1%">:</td>
    <td width="70%">$view.no_Aduan_Online</td>
  </tr>
 
  <tr>
    <td width="29%">Jenis Aduan</td>
    <td width="1%">:</td>
    <td width="70%">$view.jenis_Aduan</td>
  </tr>
  <tr>
    <td valign="top">Aduan</td>
    <td valign="top">:</td>
    <td><label>$view.aduan</label></td>
  </tr>
  <tr>
    <td>Nama Pengadu</td>
    <td>:</td>
    <td><label>$view.nama_Pengadu</label></td>
  </tr>
  <tr>
    <td>No Tel (Rumah)</td>
    <td>:</td>
    <td><label>$view.no_Tel_Rumah</label></td>
  </tr>
  <tr>
    <td>No Tel (Bimbit)</td>
    <td>:</td>
    <td><label>$view.no_Tel_Bimbit</label></td>
  </tr>
  <tr>
    <td>Emel</td>
    <td>:</td>
    <td><label>$view.emel</label></td>
  </tr>
  <!--<tr>
    <td>Bukti Aduan</td>
    <td>:</td>
    <td><label>
      <input type="file" name="txfBuktiAduan" id="txfBuktiAduan" />
    </label></td>
  </tr>-->
  <tr>
    <td>Tarikh Aduan</td>
    <td>:</td>
    <td>$view.tarikh_Aduan</td>
  </tr>
 
</table>
</fieldset>
    
    
    
    </td>
  </tr>
  <tr>
    <td>
    <fieldset><legend><strong>Untuk Kegunaan Seksyen Pengurusan dan Perundangan Tanah</strong></legend>
        <table width="100%" border="0" cellpadding="2">
          <tr>
            <td width="29%">Seksyen Yang Bertanggungjawab</td>
            <td width="1%">:</td>
            <td width="70%">$view.nama_Seksyen</td>
          </tr>
          <tr>
            <td>Tarikh Pengagihan Aduan</td>
            <td>:</td>
            <td>$view.tarikh_Pengagihan</td>
          </tr>
          
        </table>
      </fieldset>
    
    
    
    </td>
  </tr>
  <tr>
    <td>
      <fieldset><legend><strong>Untuk Kegunaan Seksyen Yang Bertanggungjawab</strong></legend>
                    <table width="100%" border="0" cellpadding="2">
                  <tr>
                    <td width="29%">Pegawai Yang Bertindak</td>
                    <td width="1%">:</td>
                    <td width="70%">$view.nama_Pegawai</td>
                  </tr>
                  <tr>
                    <td>Tarikh Pengagihan Aduan Kepada Pegawai</td>
                    <td>:</td>
                    <td>$view.tarikh_Pengagihan_Kpdpegawai</td>
                  </tr>
                   #end
                </table>
      </fieldset>
    
    
    
    </td>
  </tr>
  <tr>
    <td>
     <fieldset><legend><strong>Untuk Kegunaan Pegawai</strong></legend>
              <table width="100%" border="0" cellpadding="2">
              <tr>
                <td width="29%" valign="top">Tindakan Susulan</td>
                <td width="1%" valign="top">:</td>
                <td width="70%"><label>
                  <textarea name="txtTindakanSusulan" cols="53" id="txtTindakanSusulan"></textarea>
                </label></td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td><label>
                      <input type="button" name="cmdSimpanTindakan" id="cmdSimpanTindakan" value="Simpan Tindakan" onClick="simpanTindakan()"/>
                      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()" />
                    </label></td>
          		 </tr>
            </table>
      </fieldset>
    
    
    
    </td>
  </tr>
</table>
<script>
function simpanTindakan(){

	document.${formName}.action.value = "simpanTindakan";
	document.${formName}.submit();
	
}
function kembali(){
	
	document.${formName}.action.value = "kembali";
	document.${formName}.submit();

}
</script>
