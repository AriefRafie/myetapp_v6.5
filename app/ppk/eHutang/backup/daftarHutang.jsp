<table width="100%" border="0" cellspacing="2" cellpadding="2">

  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT HUTANG</strong></legend>
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
                <td width="1%">&nbsp;</td>
                <td width="28%">Nama</td>
                <td width="1%">:</td>
                <td width="70%"><b>$!penghutang.nama</b></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Nama / Agensi Pemiutang</td>
                <td width="1%">:</td>
                <td width="70%"><input name="nama" type="text" id="nama" onblur="this.value=this.value.toUpperCase()" value="$!hutang.nama" size="35" maxlength="200"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">No. Pengenalan / Pendaftaran</td>
                <td width="1%">:</td>
                <td width="70%"><input name="noPengenalan" type="text" id="noPengenalan" value="$!hutang.noPengenalan" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Alamat</td>
                <td width="1%">:</td>
                <td width="70%"><input name="alamat1" type="text" id="alamat1" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.alamat1" size="35"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                <td width="70%"><input name="alamat2" type="text" id="alamat2" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.alamat2" size="35"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                <td width="70%"><input name="alamat3" type="text" id="alamat3" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.alamat3" size="35"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Poskod</td>
                <td width="1%">:</td>
                <td width="70%"><input name="poskod" type="text" id="poskod" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.poskod" size="5" maxlength="5" onKeyUp="javascript:validateIC(event,this,this.value,'poskod')"/></td>
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
          <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Jenis Hutang</td>
                <td width="1%">:</td>
                <td width="70%"><input name="jenisHutang" type="text" id="jenisHutang" value="$!hutang.jenisHutang" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">No. Akaun</td>
                <td width="1%">:</td>
                <td width="70%"><input name="noAkaun" type="text" id="noAkaun" value="$!hutang.noAkaun" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
              </tr>
              <tr>
                <td width="1%" valign="top">&nbsp;</td>
                <td width="28%" valign="top">Butiran Hutang</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top"><textarea name="butiranHutang"  cols="35" rows="2" id="butiranHutang" onBlur="this.value=this.value.toUpperCase()" >$!hutang.butiranHutang</textarea></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Nilai Hutang (RM)</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="nilaiHutang" id="nilaiHutang" value="$!hutang.nilaiHutang" onblur="validateCurrency(this,this.value,'$!hutang.nilaiHutang');" style="text-align:right"/></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Baki Hutang (RM)</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="bakiHutang" id="bakiHutang" value="$!hutang.bakiHutang" onblur="validateCurrency(this,this.value,'$!hutang.bakiHutang');" style="text-align:right"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Selesai Hutang</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="tarikhSelesaiHutang" id="tarikhSelesaiHutang" value="$!hutang.tarikhSelesaiHutang" onblur="check_date(this);" size="9"/>
                  <a href="javascript:displayDatePicker('tarikhSelesaiHutang',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
              </tr>
              <tr>
                <td width="1%" valign="top">&nbsp;</td>
                <td width="28%" valign="top">Catatan</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top"><textarea name="catatan"  cols="35" rows="2" id="catatan" onBlur="this.value=this.value.toUpperCase()" >$!hutang.catatan</textarea></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanHutang()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','paparPenghutang','id='+ $!penghutang.idHutang);"/></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
