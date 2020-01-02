<fieldset>
	<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
	<table width="100%" border="0" >
          #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
  <input name="idPemohon" type="hidden" id="idPemohon" value="$beanMaklumatPemohon.idPemohon"/>
          <tr>
            <td width="1%">#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
            <td width="28%">Nama</td>
            <td width="1%">:</td>
            <td width="70%"><input type="text" name="txtNama" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" value="$beanMaklumatPemohon.nama" $readOnly class="$classDis" /></td>
            </tr>
          <tr>
            <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
            <td>Alamat </div></td>
            <td>:</td>
            <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="60" value="$beanMaklumatPemohon.alamat1" $readOnly class="$classDis" /></td>
            </tr>
          <tr>
            <td></td>
            <td></td>
            <td>&nbsp;</td>
            <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="60" value="$beanMaklumatPemohon.alamat2" $readOnly class="$classDis" /></td>
            </tr>
          <tr>
            <td></td>
            <td></td>
            <td>&nbsp;</td>
            <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="60" value="$beanMaklumatPemohon.alamat3" $readOnly class="$classDis" /></td>
            </tr>
          <tr>
            <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
            <td>Poskod </td>
            <td>:</td>
            <td><input type="text" name="txtPoskod" size="5" maxlength="5" onBlur="validatePoskod(this,this.value);" value="$beanMaklumatPemohon.poskod" $readOnly class="$classDis" /></td>
            </tr>
          <tr>
            <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
            <td>Negeri</td>
            <td>:</td>
            <td>$selectNegeri</td>
            </tr>
          <tr>
            <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
            <td>Daerah</td>
            <td>:</td>
            <td>$selectDaerah</td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td>No. Telefon</td>
            <td>:</td>
            <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$beanMaklumatPemohon.tel" $readOnly class="$classDis" /></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td>No. Fax</td>
            <td>:</td>
            <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$beanMaklumatPemohon.fax" $readOnly class="$classDis" /></td>
            </tr>
            #end
            <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
            <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">
            #if($mode == 'view')
        <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniPemohon()" />
        
	#elseif ($mode == 'update')
        <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanPemohon()" />
        <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
        <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPemohon()" />
	#end   
            </td>
            </tr>
        </table>
</fieldset>