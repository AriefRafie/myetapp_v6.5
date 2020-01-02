<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>

#set($saizTxtPerkara="1000")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionTukarguna" type="hidden" id="actionTukarguna" value="$actionTukarguna"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  
  <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" />
  <input type="hidden" name="idPHPBorangK" id="idPHPBorangK" value="$idPHPBorangK" />
  <input type="hidden" name="idPPTBorangK" id="idPPTBorangK" value="$idPPTBorangK" />
  <input type="hidden" name="idHakmilikUrusan" id="idHakmilikUrusan" value="$idHakmilikUrusan" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
<tr>
  <td colspan="2"><fieldset>
    <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
      #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
      <tr>
        <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td width="28%" valign="top">No. Fail</td>
        <td width="1%" >:</td>
        <td width="70%"><strong>
          <input name="noFail" type="text" id="noFail" size="49" maxlength="100" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.noFail" onblur="this.value=this.value.toUpperCase();checkingExistNoFail();" />
          </strong>
          <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
          <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" /></td>
      </tr>
      <tr>
        <td width="1%">&nbsp;</td>
        <td valign="top">Urusan</td>
        <td>:</td>
        <td>PELEPASAN / PENYERAHANBALIK</td>
      </tr>
      <tr>
        <td width="1%">&nbsp;</td>
        <td valign="top">Suburusan</td>
        <td>:</td>
        <td>TUKARGUNA</td>
      </tr>
      <tr>
        <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td valign="top">Tarikh Terima</td>
        <td>:</td>
        <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" size="9" $readonly class="$inputTextClass" onblur="check_date(this);cekTarikhTerima(this)"/>
          #if ($mode != 'view') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
      </tr>
      <tr>
        <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td valign="top">Tarikh Surat</td>
        <td>:</td>
        <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
          #if ($mode != 'view')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
      </tr>
      <tr>
        <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td valign="top">No. Rujukan Surat</td>
        <td>:</td>
        <td><input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat" value="$beanMaklumatPermohonan.noRujukanSurat" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
      </tr>
      <tr>
        <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td valign="top">Perkara</td>
        <td valign="top">:</td>
        <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizTxtPerkara);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizTxtPerkara);" >$beanMaklumatPermohonan.perkara</textarea>
          #if ($mode == 'new')
            #if ($idJenisTanah == '1' || $idJenisTanah == '2')
            <input type="button" name="cmdJanaTajuk" id="cmdJanaTajuk" value="Jana Tajuk" onclick="janaTajuk('$idKategoriPemohon')"/>
            #end
            #end </td>
      </tr>
      #if ($mode != 'view')
      <tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
        <td valign="top">&nbsp;</td>
        <td>Baki Aksara :&nbsp;
          <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtPerkara" /></td>
      </tr>
      #end  
      #end
    </table>
    </fieldset></td>
</tr>
<tr>
  <td colspan="2"><fieldset>
    <legend><strong>MAKLUMAT PEMOHON</strong></legend>
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td width="28%">Kategori Pemohon</td>
        <td>:</td>
        <td width="70%">$selectKategoriPemohon</td>
      </tr>
      #if($idKategoriPemohon == '3')
      <tr>
        <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td>Kementerian</td>
        <td>:</td>
        <td>$selectKementerian</td>
      </tr>
      <tr>
        <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td>Agensi</td>
        <td>:</td>
        <td>$selectAgensi</td>
      </tr>
      #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
      <tr>
        <td>&nbsp;</td>
        <td>Nama</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.namaAgensi
          <input type="hidden" name="namaAgensiKem" id="namaAgensiKem" value="$beanMaklumatAgensi.namaAgensi"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Alamat</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.alamat1</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.alamat2</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.alamat3</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Poskod</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.poskod</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Negeri</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.negeri</td>
      </tr>
      #end        
      #end
      #if($idKategoriPemohon == '8')
      <tr>
        <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td>Negeri</td>
        <td>:</td>
        <td>$selectNegeri</td>
      </tr>
      <tr>
        <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td>Pejabat</td>
        <td>:</td>
        <td>$selectPejabat</td>
      </tr>
      #foreach ($beanMaklumatPejabat in $BeanMaklumatPejabat)
      <tr>
        <td>&nbsp;</td>
        <td>Nama Pejabat</td>
        <td>:</td>
        <td>$beanMaklumatPejabat.namaPejabat</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Alamat</td>
        <td>:</td>
        <td>$beanMaklumatPejabat.alamat1</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$beanMaklumatPejabat.alamat2</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$beanMaklumatPejabat.alamat3</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Poskod</td>
        <td>:</td>
        <td>$beanMaklumatPejabat.poskod</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Bandar</td>
        <td>:</td>
        <td>$beanMaklumatPejabat.bandar</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Negeri</td>
        <td>:</td>
        <td>$beanMaklumatPejabat.negeri
          #set($idNegeriJKPTG = $beanMaklumatPejabat.idNegeri) </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>No. Telefon</td>
        <td>:</td>
        <td>$beanMaklumatPejabat.noTel</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>No. Fax</td>
        <td>:</td>
        <td>$beanMaklumatPejabat.noFax</td>
      </tr>
      #end
      #end
    </table>
    </fieldset></td>
</tr>
#if($idKategoriPemohon == '8')
<tr>
  <td colspan="2"><fieldset>
    <legend><strong>MAKLUMAT AGENSI</strong></legend>
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td width="28%">Kementerian</td>
        <td width="1%">:</td>
        <td width="70%">$selectKementerian</td>
      </tr>
      <tr>
        <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
        <td>Agensi</td>
        <td>:</td>
        <td>$selectAgensi</td>
      </tr>
      #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
      <tr>
        <td>&nbsp;</td>
        <td>Nama</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.namaAgensi
          <input type="hidden" name="namaAgensiJKPTG" id="namaAgensiJKPTG" value="$beanMaklumatAgensi.namaAgensi"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Alamat</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.alamat1</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.alamat2</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.alamat3</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Poskod</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.poskod</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Negeri</td>
        <td>:</td>
        <td>$beanMaklumatAgensi.negeri</td>
      </tr>
      #end
    </table>
    </fieldset></td>
</tr>
#end
<tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Jenis Tanah</td>
          <td width="1%">:</td>
          <td width="70%"><select name="socJenisTanah" id="socJenisTanah" onchange="doChangeJenisTanah()" $inputTextClass class="$inputTextClass">
              <option $selected value="0">SILA PILIH</option>
              <option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option>
              <option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option>
              <option $selected3 value="3">BORANG K</option>
            </select></td>
        </tr>
        #if ($idJenisTanah == '1' || $idJenisTanah == '2')
        #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"> #if ($mode == 'new')
            #if ($idKategoriPemohon == '3')
            #if ($idAgensi != '' && $idAgensi != '99999')
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();"/>
            <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onclick="pilihTanah('$idKategoriPemohon','$idAgensi' , '99999')"/>
            #else
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled" />
            #end
            #elseif ($idKategoriPemohon == '8')
            #if ($idAgensi != '' && $idAgensi != '99999' && $idPejabat !='' && $idPejabat !='99999')
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();"/>
            <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onclick="pilihTanah('$idKategoriPemohon','$idAgensi','$idNegeriJKPTG')"/>
            #else
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled" />
            #end
            #else
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled" />
            #end
            #else
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
            #end <span class="style1">$errorPeganganHakmilik</span> </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.lot
            <input type="hidden" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.noLot" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.luas
            <input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatTanah.idLuas" />
              <input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatTanah.luasBersamaan" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Hakmilik</td>
          <td>:</td>
          <td>$beanMaklumatTanah.hakmilik
            <input type="hidden" name="noMilikTanah" id="noMilikTanah" value="$beanMaklumatTanah.noHakmilik" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Warta</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noWarta
            <input type="hidden" name="noWartaTanah" id="noWartaTanah" value="$beanMaklumatTanah.noWarta" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Warta</td>
          <td>:</td>
          <td>$beanMaklumatTanah.tarikhWarta</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Mukim</td>
          <td>:</td>
          <td>$beanMaklumatTanah.mukim
            <input type="hidden" name="namaMukimTanah" id="namaMukimTanah" value="$beanMaklumatTanah.mukim" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Daerah</td>
          <td>:</td>
          <td>$beanMaklumatTanah.daerah
            <input type="hidden" name="namaDerahTanah" id="namaDerahTanah" value="$beanMaklumatTanah.daerah" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatTanah.negeri
            <input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatTanah.idNegeri" />
              <input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$beanMaklumatTanah.negeri" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kementerian
            <input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatTanah.idKementerian" />
              <input type="hidden" name="kementerian" id="kementerian" value="$beanMaklumatTanah.kementerian" /></td>
        </tr>
        <tr>
          <td height="31">&nbsp;</td>
          <td>Agensi</td>
          <td>:</td>
          <td>$beanMaklumatTanah.agensi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kegunaan Tanah</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kegunaanTanah
            <input type="hidden" name="kegunaanTanah" id="kegunaanTanah" value="$beanMaklumatTanah.kegunaanTanah" />
              <input type="hidden" name="statusRizab" id="statusRizab" value="$beanMaklumatTanah.statusRizab" /></td>
        </tr>
        
        #end
        #end
        
        #if ($idJenisTanah == '3')
        <tr>
          <td colspan="4"><fieldset>
            <legend><strong>Maklumat Lot</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
              <tr>
                <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Pegangan Hakmilik</td>
                <td width="1%">:</td>
                <td width="70%"> #if ($mode == 'new')
                  #if ($idKategoriPemohon == '3')
                  #if ($idAgensi != '' && $idAgensi != '99999')
                  <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik" onblur="doChangePeganganHakmilikBorangK();"/>
                  <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Borang K" onclick="pilihBorangK('$idKategoriPemohon','$idAgensi' , '99999')"/>
                  #else
                  <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik" readonly="readonly" class="disabled" />
                  #end
                  #elseif ($idKategoriPemohon == '8')
                  #if ($idAgensi != '' && $idAgensi != '99999' && $idPejabat !='' && $idPejabat !='99999')
                  <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik" onblur="doChangePeganganHakmilikBorangK();"/>
                  <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Borang K" onclick="pilihBorangK('$idKategoriPemohon','$idAgensi','$idNegeriJKPTG')"/>
                  #else
                  <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik" readonly="readonly" class="disabled" />
                  #end
                  #else
                  <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik" readonly="readonly" class="disabled" />
                  #end
                  #else
                  <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik" readonly="readonly" class="disabled">
                  #end <span class="style1">$errorPeganganHakmilik</span> </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Lot</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.lot </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Luas Lot</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.luas
                  <input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatBorangK.idLuas" />
                    <input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatBorangK.luasBersamaan" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Hakmilik</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.hakmilik </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Mukim</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.mukim </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Daerah</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.daerah </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Negeri</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.negeri
                  <input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatBorangK.idNegeri" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Kementerian</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.kementerian
                  <input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatBorangK.idKementerian" /></td>
              </tr>
              <tr>
                <td height="31">&nbsp;</td>
                <td>Agensi</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.agensi</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Kegunaan Tanah</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.kegunaanTanah</td>
              </tr>
              #end
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="4"><fieldset>
            <legend><strong>Maklumat Borang K</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Borang K</td>
                <td width="1%">:</td>
                <td width="70%">$beanMaklumatBorangK.tarikhBorangK</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Catatan</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.catatan </td>
              </tr>
              #end
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="4"><fieldset>
            <legend><strong>Maklumat Rekod Endosan Borang K</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">No. Perserahan</td>
                <td width="1%">:</td>
                <td width="70%">$beanMaklumatBorangK.noPerserahan </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Tarikh Catatan Dibuat</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.tarikhCatatan </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Tarikh Terima</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.tarikhTerima </td>
              </tr>
              #end
            </table>
            </fieldset></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT KEGUNAAN TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Luas Kegunaan</td>
          <td width="1%">:</td>
          <td width="70%">$selectLuasKegunaan </td>
        </tr>
        <tr>
          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tujuan Kegunaan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtTujuanKegunaan" id="txtTujuanKegunaan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tujuanKegunaan</textarea>
          	#if ($mode == 'new')
            #if ($idJenisTanah == '1' || $idJenisTanah == '2')
            <input type="button" name="cmdDaftarBaru2" id="cmdDaftarBaru2" value="Jana Tajuk" onclick="janaTajuk('$idKategoriPemohon')"/>
            #end
            #end
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'new')
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onclick="daftarBaru()"/>
      #end
      #if ($mode == 'view')
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
      #end </td>
  </tr>
</table>
<div id="checkingExistNoFail_result"></div>
<script>
function doChangeJenisTanah() {
	doAjaxCall${formName}("doChangeJenisTanah");
}
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeAgensi() {
	doAjaxCall${formName}("doChangeAgensi");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}
function pilihTanah(idKategoriPemohon,idAgensiPemohon,idNegeriJKPTG) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupSenaraiTanahView?idKategoriPemohon="+idKategoriPemohon+"&idAgensiPemohon="+idAgensiPemohon+"&idNegeriJKPTG="+idNegeriJKPTG;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
function daftarBaru() {
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if(document.${formName}.noFail.value == "JKPTG/SPHP/879/" || document.${formName}.noFail.value == ''){
		alert('Sila masukkan No Fail.');
  		document.${formName}.noFail.focus(); 
		return; 
	}
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhTerima.focus(); 
		return;
	}
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtNoRujukanSurat.value == ""){
		alert('Sila masukkan No. Rujukan Surat.');
  		document.${formName}.txtNoRujukanSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	if(document.${formName}.socKategoriPemohon.value == ""){
		alert('Sila pilih Jenis Kategori Pemohon.');
  		document.${formName}.socKategoriPemohon.focus(); 
		return; 
	}
	if(document.${formName}.socKategoriPemohon.value == "3"){
		if(document.${formName}.socKementerian.value == ""){
			alert('Sila pilih Kementerian.');
			document.${formName}.socKementerian.focus(); 
			return; 
		}	
		if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih Agensi.');
			document.${formName}.socAgensi.focus(); 
			return; 
		}		
	} else if(document.${formName}.socKategoriPemohon.value == "8") {
		if(document.${formName}.socNegeriJKPTG.value == ""){
			alert('Sila pilih Negeri.');
			document.${formName}.socNegeriJKPTG.focus(); 
			return; 
		}
		if(document.${formName}.socPejabat.value == ""){
			alert('Sila pilih Pejabat.');
			document.${formName}.socPejabat.focus(); 
			return; 
		}
		if(document.${formName}.socKementerian.value == ""){
			alert('Sila pilih Kementerian.');
			document.${formName}.socKementerian.focus(); 
			return; 
		}	
		if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih Agensi.');
			document.${formName}.socAgensi.focus(); 
			return; 
		}
	}
	if(document.${formName}.socJenisTanah.value == "3"){
		if(document.${formName}.idPPTBorangK.value == "" && document.${formName}.idHakmilikUrusan.value == "" && document.${formName}.idPHPBorangK.value == ""){
			alert('Sila pilih Pegangan Hakmilik.');
			return; 
		}
	} else {		
		if(document.${formName}.idHakmilikAgensi.value == ""){
			alert('Sila pilih Pegangan Hakmilik.');
			return; 
		}
	}
	
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila masukkan Luas Kegunaan.');
  		document.${formName}.socLuasKegunaan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanKegunaan.value == ""){
		alert('Sila masukkan Tujuan Kegunaan.');
  		document.${formName}.txtTujuanKegunaan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionTukarguna.value = "daftarBaru";
		return;
	}
	
	document.${formName}.actionTukarguna.value = "papar";
	document.${formName}.hitButton.value = "daftarBaru";
	doAjaxCall${formName}("");
}
function kembali() {	
	document.${formName}.actionTukarguna.value = "";
	document.${formName}.submit();
}

function cekTarikhTerima(elmnt) {
//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		elmnt.value ="";
		document.${formName}.tarikhTerima.focus(); 
		return;
	}
}
function cekTarikhSurat(elmnt) {  
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
}
function validateLength(str) {	
	if (str.length < 5) {
 		alert("Sila Masukan Poskod Dengan Betul.")
		document.${formName}.txtPoskod.value = "";
		return false
	}
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function checkingExistNoFail() {
	url = "../servlet/ekptg.view.php2.FrmPLPServlet?command=checkingExistNoFail&idSuburusan=33";
	actionName = "checkingExistNoFail";
	target = "checkingExistNoFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function popupMsg(){
	alert('No. Fail telah wujud.');
	document.${formName}.noFail.value = "JKPTG/SPHP/879/";
}
function seterusnya(){
	document.${formName}.action = "$EkptgUtil.getTabID("Tukarguna",$portal_role)?_portal_module=ekptg.view.php2.FrmTKRMaklumatPermohonanView";
	document.${formName}.submit();
}
function janaTajuk(idKategoriPemohon) {
	
	if(document.${formName}.idHakmilikAgensi.value == ""){
		alert('Sila pilih Pegangan Hakmilik Sebelum Menjana Tajuk.');
		document.${formName}.idHakmilikAgensi.focus();
		return;
	}
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila masukkan Luas Kegunaan.');
		document.${formName}.socLuasKegunaan.focus();
		return;
	}
		if(document.${formName}.txtTujuanKegunaan.value == ""){
		alert('Sila masukkan Tujuan Kegunaan.');
  		document.${formName}.txtTujuanKegunaan.focus(); 
		return; 
	}
	
	var strTajuk = " ";
	var luasKegunaan = " ";
	var milikOrRizab = " ";
	var pemohon = " ";
	
	if(idKategoriPemohon == '3') {
	    pemohon = document.${formName}.namaAgensiKem.value;
	} else if(idKategoriPemohon == '8') {
		pemohon = document.${formName}.namaAgensiJKPTG.value;
	}
	
	if(document.${formName}.socLuasKegunaan.value == "1") {
		luasKegunaan = "KESELURUHAN";
	}
	else if(document.${formName}.socLuasKegunaan.value == "2"){
		luasKegunaan = "SEBAHAGIAN";
	}
	
	var str1 = document.${formName}.noLotTanah.value;
	var str2 = document.${formName}.noMilikTanah.value;
	var str3 = document.${formName}.noWartaTanah.value;
	var str4 = document.${formName}.namaMukimTanah.value;
	var str5 = document.${formName}.namaDerahTanah.value;
	var str6 = document.${formName}.namaNegeriTanah.value;
	var kegunaanTanah = document.${formName}.kegunaanTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;
	var kjpTnh = document.${formName}.kementerian.value;
	var tujuanKegunaan = document.${formName}.txtTujuanKegunaan.value;
		
	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str3;
	}
	strTajuk = "PERMOHONAN TUKARGUNA " + luasKegunaan +" TANAH " + statusRizabTnh + " PERSEKUTUAN " + str1 +", " + milikOrRizab + ", "+ str4 + ", " + str5+ ", " + str6 +" ( " + kegunaanTanah + " )" + " DARIPADA " + kjpTnh + " KEPADA " + pemohon +" BAGI TUJUAN " + tujuanKegunaan;
	
	document.${formName}.txtPerkara.value = strTajuk;
}

function pilihBorangK(idKategoriPemohon,idAgensiPemohon,idNegeriJKPTG) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmTKRPopupSenaraiBorangKView?idKategoriPemohon="+idKategoriPemohon+"&idAgensiPemohon="+idAgensiPemohon+"&idNegeriJKPTG="+idNegeriJKPTG;
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihBorangK(idPPTBorangK,idHakmilikUrusan,idPHPBorangK) {
	
	document.${formName}.idPPTBorangK.value = idPPTBorangK;
	document.${formName}.idHakmilikUrusan.value = idHakmilikUrusan;
	document.${formName}.idPHPBorangK.value = idPHPBorangK;
	doAjaxCall${formName}("doChangeMaklumatBorangK");
}
function doChangePeganganHakmilikBorangK() {
	doAjaxCall${formName}("doChangePeganganHakmilikBorangK");
}
</script>
