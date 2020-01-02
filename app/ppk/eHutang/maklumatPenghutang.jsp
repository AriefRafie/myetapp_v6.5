<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PENGHUTANG</strong></legend>
      #if($!role == "adminppk")
      	#set ($disable = "disabled")
      #else
      	#set ($disable = $disabled)
      #end
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">MyID Baru</td>
                <td width="1%">:</td>
                <td width="70%"><b>$!penghutang.noPengenalanBaru</b></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Nama</td>
                <td width="1%">:</td>
                <td width="70%"><input name="nama" type="text" id="nama" $disable class="$disable" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.nama" size="35" maxlength="200"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">No. Telefon</td>
                <td width="1%">:</td>
                <td width="70%"><input name="noTelefon" type="text" id="noTelefon" $disable class="$disable" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.noTelefon" size="12" maxlength="12" onKeyUp="javascript:validateIC(event,this,this.value,'noTelefon')"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Emel</td>
                <td width="1%">:</td>
                <td width="70%"><input name="emel" type="text" id="emel" $disable class="$disable" value="$!penghutang.emel" size="35"/></td>
              </tr>
              <tr>
                <td width="1%" valign="top">&nbsp;</td>
                <td width="28%" valign="top">Catatan</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top"><textarea name="catatan"  $disable class="$disable" cols="35" rows="2" id="catatan" onBlur="this.value=this.value.toUpperCase()" >$!penghutang.catatan</textarea></td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Alamat</td>
                <td width="1%">:</td>
                <td width="70%"><input name="alamat1" type="text" id="alamat1" $disable class="$disable" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.alamat1" size="35"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                <td width="70%"><input name="alamat2" type="text" id="alamat2" $disable class="$disable" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.alamat2" size="35"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                <td width="70%"><input name="alamat3" type="text" id="alamat3" $disable class="$disable" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.alamat3" size="35"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Poskod</td>
                <td width="1%">:</td>
                <td width="70%"><input name="poskod" type="text" id="poskod" $disable class="$disable"onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.poskod" size="5" maxlength="5" onKeyUp="javascript:validateIC(event,this,this.value,'poskod')"/></td>
              </tr> 
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Jabatan</td>
                <td width="1%">:</td>
                <td width="70%"><input name="jabatan" type="text" id="jabatan" $disable class="$disable" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.jabatan" size="35"/></td>
              </tr>
              #if($!role == "adminppk")
             <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Negeri</td>
                <td width="1%">:</td>
                <td width="70%">$!selectNegeri</td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Bandar</td>
                <td width="1%">:</td>
                <td width="70%"><div id="divBandar">#parse("$templateDir/selectBandar.jsp")</div></td>
              </tr>
              
              #else
               <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Negeri</td>
                <td width="1%">:</td>
                <td width="70%">$!selectNegeri</td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Bandar</td>
                <td width="1%">:</td>
                <td width="70%"><div id="divBandar">#parse("$templateDir/selectBandar.jsp")</div></td>
              </tr>
              #end
              
            </table></td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          #if(($!role != "adminppk") && ($disable == "disabled"))
                <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniPenghutang()"/>
                <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusPenghutang('$!penghutang.idHutang')"/>
                <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','','');"/></td>
          #end
          #if(($!role != "adminppk") && ($disable != "disabled"))
           		<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanKemaskiniPenghutang()"/>
           		<input type="button" name="cmdKembali" id="cmdKembali" value="Batal" onclick="doDivAjaxCall$formname('divMainForm','paparPenghutang','id='+$!id2);"/></td>
          #end
          #if($!role == "adminppk")
          		<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','','');"/></td>
          #end  
            
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
