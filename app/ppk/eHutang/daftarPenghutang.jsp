<table width="100%" border="0" cellspacing="2" cellpadding="2">
#foreach ($list in $idPejabat)
#set ($idPejabat_user = $list.idPejabat)

#end

  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PENGHUTANG</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">              
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">MyID Baru <input type="hidden" value="$idPejabat_user" name="idPejabat_user"></td>
                <td width="1%">:</td>
                <td width="70%"><input name="noPengenalanBaru" type="text" id="noPengenalanBaru" onBlur="this.value=this.value.toUpperCase();doDivAjaxCall$formname('err_noPengenalanBaru','doSemakNoPengenalanBaru','');" value="$!penghutang.noPengenalanBaru" maxlength="12" onKeyUp="javascript:validateIC(event,this,this.value,'noPengenalanBaru');$('err_noPengenalanBaru').innerHTML=''; at(this, event);" />
                 <!-- <td width="70%"><input name="noPengenalanBaru" type="text" id="noPengenalanBaru"  value="$!penghutang.noPengenalanBaru" maxlength="12" onKeyUp="javascript:validateIC(event,this,this.value,'noPengenalanBaru');$('err_noPengenalanBaru').innerHTML=''; at(this, event);" /> -->
                <div id="err_noPengenalanBaru" style="color:#CC0000;font-weight:bold;border:2px #000">#parse("$templateDir/semakNoPengenalanbaru.jsp")</div></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Nama</td>
                <td width="1%">:</td>
                <td width="70%"><input name="nama" type="text" id="nama" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.nama" size="35" maxlength="200"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">No. Telefon</td>
                <td width="1%">:</td>
                <td width="70%"><input name="noTelefon" type="text" id="noTelefon" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.noTelefon" size="12" maxlength="12" onKeyUp="javascript:validateIC(event,this,this.value,'noTelefon')"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Emel</td>
                <td width="1%">:</td>
                <td width="70%"><input name="emel" type="text" id="emel" value="$!penghutang.emel" size="35"/></td>
              </tr>
              <tr>
                <td width="1%" valign="top">&nbsp;</td>
                <td width="28%" valign="top">Catatan</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top"><textarea name="catatan"  cols="35" rows="2" id="catatan" onBlur="this.value=this.value.toUpperCase()" >$!penghutang.catatan</textarea></td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Alamat</td>
                <td width="1%">:</td>
                <td width="70%"><input name="alamat1" type="text" id="alamat1" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.alamat1" size="35"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                <td width="70%"><input name="alamat2" type="text" id="alamat2" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.alamat2" size="35"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                <td width="70%"><input name="alamat3" type="text" id="alamat3" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.alamat3" size="35"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Poskod</td>
                <td width="1%">:</td>
                <td width="70%"><input name="poskod" type="text" id="poskod" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.poskod" size="5" maxlength="5" onKeyUp="javascript:validateIC(event,this,this.value,'poskod')"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Jabatan</td>
                <td width="1%">:</td>
                <td width="70%"><input name="jabatan" type="text" id="jabatan" onBlur="this.value=this.value.toUpperCase()" value="$!penghutang.jabatan" size="35"/></td>
              </tr>
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
            </table></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanPenghutang()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','','');"/></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
