<!-- saiz text -->
#set($saizTxtPekerjaanSykt="1000")

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'view' || $mode == 'update')
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">$selectKategoriPemohon</td>
        </tr>
        #if ($idKategoriPemohon == '1')
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Nama</td>
          <td>:</td>
          <td><input type="text" name="txtNama" id="txtNama" value="$beanMaklumatPemohon.nama" $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Jenis Pengenalan</td>
          <td>:</td>
          <td>$selectJenisPengenalanIndividu</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>No. Pengenalan</td>
          <td>:</td>
          <td><input type="text" name="txtNoPengenalan" id="txtNoPengenalan" maxlength="12" value="$beanMaklumatPemohon.noPengenalan"  $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Pekerjaan</td>
          <td>:</td>
          <td><input type="text" name="txtPekerjaan" id="txtPekerjaan" value="$beanMaklumatPemohon.pekerjaan"  $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Jantina</td>
          <td>:</td>
          <td>$selectJantina</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Bangsa</td>
          <td>:</td>
          <td>$selectBangsa</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Alamat</td>
          <td>:</td>
          <td><input type="text" name="txtAlamat1" id="txtAlamat1" value="$beanMaklumatPemohon.alamat1"  $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input type="text" name="txtAlamat2" id="txtAlamat2" value="$beanMaklumatPemohon.alamat2"  $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input type="text" name="txtAlamat3" id="txtAlamat3" value="$beanMaklumatPemohon.alamat3"  $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Poskod</td>
          <td>:</td>
          <td><input type="text" name="txtPoskod" id="txtPoskod" size="4" onkeyup="validatePoskod(this,this.value);" maxlength="5" value="$beanMaklumatPemohon.poskod" $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Bandar</td>
          <td>:</td>
          <td>$selectBandar</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Telefon</td>
          <td>:</td>
          <td><input type="text" name="txtNoTel" id="txtNoTel" size="10" maxlength="10" value="$beanMaklumatPemohon.noTel" $readonly class="$inputTextClass" onblur="validatePoskod(this,this.value);"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Telefon Bimbit</td>
          <td>:</td>
          <td><input type="text" name="txtNoTelBimbit" id="txtNoTelBimbit" size="10" maxlength="10" value="$beanMaklumatPemohon.noTelBim" $readonly class="$inputTextClass" onblur="validatePoskod(this,this.value);"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Faks</td>
          <td>:</td>
          <td><input type="text" name="txtNoFax" id="txtNoFax" size="10" maxlength="10" value="$beanMaklumatPemohon.noFax" $readonly class="$inputTextClass" onblur="validatePoskod(this,this.value);"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>E-mel</td>
          <td>:</td>
          <td><input type="text" name="txtEmel" id="txtEmel" value="$beanMaklumatPemohon.emel" $readonly class="$inputTextClass"/></td>
        </tr>
        #elseif ($idKategoriPemohon == '2')
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Nama Syarikat</td>
          <td>:</td>
          <td><input type="text" name="txtNamaSykt" id="txtNamaSykt" value="$beanMaklumatPemohon.namaSykt" size="40" maxlength="80" $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>No. Pendaftaran</td>
          <td>:</td>
          <td><input type="text" name="txtNoPengenalanSykt" id="txtNoPengenalanSykt" maxlength="12" value="$beanMaklumatPemohon.noPengenalanSykt"  $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Jenis Perniagaan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtPekerjaanSykt" class="$inputTextClass" id="txtPekerjaanSykt" cols="50" rows="5" oonKeyUp="textCounter(this.form.txtPekerjaanSykt,this.form.remLen3,$!saizTxtPekerjaanSykt);" onKeyDown="textCounter(this.form.txtPekerjaanSykt,this.form.remLen3,$!saizTxtPekerjaanSykt);"  $readonly="$readonly" >$beanMaklumatPemohon.pekerjaanSykt</textarea></td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
              <input type="text" readonly="readonly" class="disabled" name="remLen3" size="3" maxlength="3" value="$!saizTxtPekerjaanSykt" /></td>
        </tr>
        #end
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Alamat</td>
          <td>:</td>
          <td><input name="txtAlamat1Sykt" type="text" class="$inputTextClass" id="txtAlamat1Sykt"  value="$beanMaklumatPemohon.alamat1Sykt" size="40" maxlength="80" $readonly /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input name="txtAlamat2Sykt" type="text" class="$inputTextClass" id="txtAlamat2Sykt"  value="$beanMaklumatPemohon.alamat2Sykt" size="40" maxlength="80" $readonly /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input name="txtAlamat3Sykt" type="text" class="$inputTextClass" id="txtAlamat3Sykt"  value="$beanMaklumatPemohon.alamat3Sykt" size="40" maxlength="80" $readonly /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Poskod</td>
          <td>:</td>
          <td><input type="text" name="txtPoskodSykt" id="txtPoskodSykt" size="4" onkeyup="validatePoskod(this,this.value);" maxlength="5" value="$beanMaklumatPemohon.poskodSykt" $readonly class="$inputTextClass"/></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeriSykt</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Bandar</td>
          <td>:</td>
          <td>$selectBandarSykt</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Telefon</td>
          <td>:</td>
          <td><input type="text" name="txtNoTelSykt" id="txtNoTelSykt" size="10" maxlength="10" value="$beanMaklumatPemohon.noTelSykt" $readonly class="$inputTextClass" onblur="validatePoskod(this,this.value);"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Faks</td>
          <td>:</td>
          <td><input type="text" name="txtNoFaxSykt" id="txtNoFaxSykt" size="10" maxlength="10" value="$beanMaklumatPemohon.noFaxSykt" $readonly class="$inputTextClass" onblur="validatePoskod(this,this.value);"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>E-mel</td>
          <td>:</td>
          <td><input type="text" name="txtEmelSykt" id="txtEmelSykt" value="$beanMaklumatPemohon.emelSykt" size="40" maxlength="80" $readonly class="$inputTextClass"/></td>
        </tr>
        #end
      </table>
      #end
      </fieldset></td>
  </tr>
  #if ($idKategoriPemohon == '2')
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>KEUPAYAAN KEWANGAN</strong></legend>
      #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="29%">Modal Dibenarkan</td>
                <td width="70%">: RM
                  <input type="text" name="txtModalBenar" id="txtModalBenar" onblur="validateCurrency(this,this.value,'$beanMaklumatPemohon.modalBenar');" value="$beanMaklumatPemohon.modalBenar" $readonly class="$inputTextClass"/></td>
              </tr>
            </table></td>
          <td width="50%"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="29%">Modal Jelas</td>
                <td width="70%">: RM
                  <input type="text" name="txtModalJelas" id="txtModalJelas" onblur="validateCurrency(this,this.value,'$beanMaklumatPemohon.modalJelas');" value="$beanMaklumatPemohon.modalJelas" $readonly class="$inputTextClass"/></td>
              </tr>
            </table></td>
        </tr>
      </table>
      #end
      </fieldset></td>
  </tr>
  #end
  #end
  <td colspan="2"><table width="100%" border="0" cellspacing="2" cellpadding="2">
        #if ($mode == 'newProjek' || $mode == 'viewProjek' || $mode == 'updateProjek')
        <tr>
          <td>#parse("app/php2/frmAPBMaklumatProjek.jsp")</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        #end
        #if ($mode == 'view' || $mode == 'newProjek' || $mode == 'viewProjek' || $mode == 'updateProjek')
        <tr>
          <td><fieldset>
            <legend><strong>SENARAI PROJEK</strong></legend>
            <table align="center" width="100%">
              #if ($mode == 'view')
              <tr>
                <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahProjek()"/></td>
              </tr>
              #end
              <tr class="table_header">
                <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                <td width="95%"><strong>Nama Projek</strong></td>
              </tr>
              #set ($listProjek = "")
              #if ($SenaraiProjek.size() > 0)
              #foreach ($listProjek in $SenaraiProjek)
              #if ($listProjek.bil == '')
              #set( $row = "row1" )
              #elseif (($listProjek.bil % 2) != 0)
              #set( $row = "row1" )
              #else 
              #set( $row = "row2" )
              #end
              <tr>
                <td class="$row" align="center">$listProjek.bil</td>
                <td class="$row"><a href="javascript:paparProjek('$listProjek.idProjek')" class="style2">$listProjek.namaProjek</a></td>
              </tr>
              #end
              #else
              <tr>
                <td class="row1" align="center">&nbsp;</td>
                <td class="row1">Tiada Rekod</td>
              </tr>
              #end
            </table>
            </fieldset></td>
        </tr>
        #end
      </table></td>
  #if ($mode != 'update' && $idKategoriPemohon == '2')
  <tr>
    <td colspan="2"><table width="100%" border="0" cellspacing="2" cellpadding="2">
        #if ($mode == 'newPengarah' || $mode == 'updatePengarah' || $mode == 'viewPengarah')
        <tr>
          <td> #parse("app/php2/frmAPBMaklumatPengarah.jsp") </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        #end
        <tr>
          <td><fieldset>
            <legend><strong>SENARAI PENGARAH</strong></legend>
            <table align="center" width="100%">
              #if ($mode == 'view')
              <tr>
                <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahPengarah()"/></td>
              </tr>
              #end
              <tr class="table_header">
                <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                <td width="40%"><strong>Nama Pengarah</strong></td>
                <td width="20%"><strong>No Pengenalan</strong></td>
                <td width="25%"><strong>Warganegara</strong></td>
                <td width="10%" align="center"><strong>Pegangan Saham</strong></td>
              </tr>
              #set ($listPengarah = "")
              #if ($SenaraiPengarah.size() > 0)
              #foreach ($listPengarah in $SenaraiPengarah)
              #if ($listPengarah.bil == '')
              #set( $row = "row1" )
              #elseif (($listPengarah.bil % 2) != 0)
              #set( $row = "row1" )
              #else 
              #set( $row = "row2" )
              #end
              <tr>
                <td class="$row" align="center">$listPengarah.bil</td>
                <td class="$row"><a href="javascript:paparPengarah('$listPengarah.idPengarah')" class="style2">$listPengarah.nama</a></td>
                <td class="$row">$listPengarah.noPengenalan</td>
                <td class="$row">$listPengarah.warganegara</td>
                <td class="$row" align="center">RM$listPengarah.peganganSaham</td>
              </tr>
              #end
              #else
              <tr>
                <td class="row1" align="center">&nbsp;</td>
                <td class="row1">Tiada Rekod</td>
                <td class="row1">&nbsp;</td>
                <td class="row1">&nbsp;</td>
                <td class="row1" align="center">&nbsp;</td>
              </tr>
              #end
            </table>
            </fieldset></td>
        </tr>
      </table></td>
  </tr>
  #end
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  #if ($mode == 'updatePemohon')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'view')
      <input type="button" name="cmdKemaskiniPemohon" id="cmdKemaskiniPemohon" value="Kemaskini" onclick="kemaskiniPemohon()"/>
      #if ($idStatus == '1610198')
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
      #end
      <input type="button" name="cdmCetak2" id="cdmCetak2" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskiniPemohon" id="cmdSimpanKemaskiniPemohon" value="Simpan" onclick="simpanKemaskiniPemohon()"/>
      <input type="button" name="cmdBatalKemaskiniPemohon" id="cmdSimpanKemaskiniPemohon" value="Batal" onclick="batalKemaskiniPemohon()"/>
      #end </td>
  </tr>
</table>
