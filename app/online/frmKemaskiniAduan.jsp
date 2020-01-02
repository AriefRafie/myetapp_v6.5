<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<form action="" method="post" enctype="multipart/form-data" name="f1" id="f1">
<input name="command" type="hidden" value="$command">
<input name="mode" type="hidden" value="$mode">
<input name="role" type="hidden" value="$role">
&nbsp;
  <table width="100%" border="1" cellpadding="2">
  #if($command == 'tambahAduan' && $role == 'online')
    <tr >
      <td>
      <fieldset>
      <legend><strong>Untuk Kegunaan Pengadu</strong></legend>
      <table width="100%" border="0" cellpadding="2">
      #if($command != 'tambahAduan')
          <tr>
            <td width="29%">No Aduan</td>
            <td width="1%">:</td>
            <td width="70%">&nbsp;</td>
          </tr>
 	  #end
          <tr>
            <td width="29%"><span class="style1">*</span>Jenis Aduan</td>
            <td width="1%">:</td>
            <td width="70%">$selectJenisAduan</td>
          </tr>
          <tr>
            <td valign="top"><span class="style1">*</span>Aduan</td>
            <td valign="top">:</td>
            <td><label>
              <textarea name="txtAduan" cols="53" id="txtAduan"></textarea>
            </label></td>
          </tr>
          <tr>
            <td><span class="style1">*</span>Tarikh Aduan</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txdTarikhAduan" id="txdTarikhAduan" />
            </label>
             <a href="javascript:displayDatePicker('txdTarikhAduan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>            </td>
          </tr>
          <tr>
            <td><span class="style1">*</span>Nama Pengadu</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txtNamaPengadu" id="txtNamaPengadu" />
            </label></td>
          </tr>
          <tr>
            <td><span class="style1">*</span>No Tel (Rumah)</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txtNoTelRumah" id="txtNoTelRumah" />
            </label></td>
          </tr>
          <tr>
            <td><span class="style1">*</span>No Tel (Bimbit)</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txtNoTelBimbit" id="txtNoTelBimbit" />
            </label></td>
          </tr>
          <tr>
            <td><span class="style1">*</span>Emel</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txtEmel" id="txtEmel" />
            </label></td>
          </tr>
          <tr>
            <td>Bukti Aduan</td>
            <td>:</td>
            <td><label>
              <input type="file" name="txfBuktiAduan" id="txfBuktiAduan" />
            </label></td>
          </tr>
        </table>

      </fieldset>
      
      
      </td>
    </tr>
  #else
  <tr>
      <td>
      <fieldset>
      <legend><strong>Untuk Kegunaan Pengadu</strong></legend>
      <table width="100%" border="0" cellpadding="2">
      #if($command != 'tambahAduan')
          <tr>
            <td width="29%">No Aduan</td>
            <td width="1%">:</td>
            <td width="70%">&nbsp;</td>
          </tr>
 	  #end
          <tr>
            <td width="29%"><span class="style1">*</span>Jenis Aduan</td>
            <td width="1%">:</td>
            <td width="70%">$selectJenisAduan</td>
          </tr>
          <tr>
            <td valign="top"><span class="style1">*</span>Aduan</td>
            <td valign="top">:</td>
            <td><label>
              <textarea name="txtAduan" cols="53" id="txtAduan"></textarea>
            </label></td>
          </tr>
          <tr>
            <td><span class="style1">*</span>Tarikh Aduan</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txdTarikhAduan" id="txdTarikhAduan" />
            </label>
             <a href="javascript:displayDatePicker('txdTarikhAduan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>            </td>
          </tr>
          <tr>
            <td><span class="style1">*</span>Nama Pengadu</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txtNamaPengadu" id="txtNamaPengadu" />
            </label></td>
          </tr>
          <tr>
            <td><span class="style1">*</span>No Tel (Rumah)</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txtNoTelRumah" id="txtNoTelRumah" />
            </label></td>
          </tr>
          <tr>
            <td><span class="style1">*</span>No Tel (Bimbit)</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txtNoTelBimbit" id="txtNoTelBimbit" />
            </label></td>
          </tr>
          <tr>
            <td><span class="style1">*</span>Emel</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txtEmel" id="txtEmel" />
            </label></td>
          </tr>
          <tr>
            <td>Bukti Aduan</td>
            <td>:</td>
            <td><label>
              <input type="file" name="txfBuktiAduan" id="txfBuktiAduan" />
            </label></td>
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
            <td width="29%"><span class="style1">*</span>Seksyen Yang Bertanggungjawab</td>
            <td width="1%">:</td>
            <td width="70%">&nbsp;</td>
          </tr>
          <tr>
            <td><span class="style1">*</span>Tarikh Pengagihan Aduan</td>
            <td>:</td>
            <td><label>
              <input type="text" name="txdTarikhPengagihanAduan" id="txdTarikhPengagihanAduan" />
            </label>
            <a href="javascript:displayDatePicker('txdTarikhPengagihanAduan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>            </td>
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
                    <td width="29%"><span class="style1">*</span>Pegawai Yang Bertindak</td>
                    <td width="1%">:</td>
                    <td width="70%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td><span class="style1">*</span>Tarikh Pengagihan Aduan Kepada Pegawai</td>
                    <td>:</td>
                    <td><label>
                      <input type="text" name="txdTarikhPengagihanAduanKpdPegawai" id="txdTarikhPengagihanAduanKpdPegawai" />
                    </label>
                     <a href="javascript:displayDatePicker('txdTarikhPengagihanAduan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>                    </td>
                  </tr>
                </table>
            </fieldset>
      </td>
    </tr>
    <tr>
    	<td>
        <fieldset><legend><strong>Untuk Kegunaan Pegawai</strong></legend>
              <table width="100%" border="0" cellpadding="2">
              <tr>
                <td width="29%">Pegawai Yang Bertindak</td>
                <td width="1%">:</td>
                <td width="70%">&nbsp;</td>
              </tr>
              <tr>
                <td>Status Aduan</td>
                <td>:</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td valign="top">Tindakan Susulan</td>
                <td valign="top">:</td>
                <td><label>
                  <textarea name="txtTindakanSusulan" cols="53" id="txtTindakanSusulan"></textarea>
                </label></td>
              </tr>
            </table>
        </fieldset>
        
        </td>
    </tr>
  #end
    <tr>
    	<td align="center">
        #if($mode == 'View')
        <label><input name="cmdKemaskini" type="button" value="Kemaskini" /></label>
		<label><input name="cmdTambah" type="button" value="Tambah" /> </label>
        <label><input name="cmdCetak" type="button" value="Cetak" /> </label>
        <label><input name="cmdBatal" type="button" value="Batal" />  </label>
        <label><input name="cmdKembali" type="button" value="Kembali" /></label>
        #else
        <label><input name="cmdSimpan" type="button" value="Simpan" /></label>
        <label><input name="cmdBatal" type="button" value="Batal" />  </label>
       #end
      </td>
    </tr>
  </table>

 <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-08-04</strong></td>
  	</tr>
</table>

</form>
