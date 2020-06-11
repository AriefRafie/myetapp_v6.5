<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionLesen" type="hidden" id="actionLesen" value="$actionLesen"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>  
  	<td colspan="2"><fieldset>
  	  <legend><strong>JENIS PERMOHONAN</strong></legend>
  	  <table width="100%" border="0" cellspacing="2" cellpadding="2">
  		<tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Jenis Permohonan</td>
          <td>:</td>
          <td width="70%">$selectJenisPermohonan</td>
        </tr>
        #if ($idJenisPermohonan == '1')
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No. Fail</td>
          <td>:</td>
          <td><strong>$beanMaklumatPermohonan.noFail</strong>
            <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
            <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" />
          </td>
        </tr>
        #end
        #elseif ($idJenisPermohonan == '2')
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No. Fail Lama</td>
          <td>:</td>
          <td>
          	<input type="text" name="txtNoFailLama" id="txtNoFailLama" size="43" value="$beanMaklumatPermohonan.noFail" 
          		onblur="this.value=this.value.toUpperCase();doChangeNoFailAPB();" $readonly class="$inputTextClass"/>
          </td>
          </td>
        </tr>
        #end
        #end
  	  </table>
  	</td>
  </tr>
  #if ($idJenisPermohonan == '1')
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Kategori Pemohon</td>
          <td>:</td>
          <td>$selectIndividuBukanIndividu</td>
        </tr>
        #if ($idKategoriIndividu == '1')
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top"></td>
          <td>:</td>
          <td>$selectKategoriPemohon</td>
        </tr>
        #elseif ($idKategoriIndividu == '11')
        <tr>
          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%"></td>
          <td>:</td>
          <td>$selectKategoriPemohon2</td>
        </tr>
        #end
        #if ($idKategoriPemohon == '1')
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Nama</td>
          <td>:</td>
          <td><input type="text" name="txtNama" id="txtNama" value="$beanMaklumatPemohon.nama" $readonly class="$inputTextClass" size="43" /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Jenis Pengenalan</td>
          <td>:</td>
          <td>$selectJenisPengenalanIndividu</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>No. Pengenalan/<em>MyID</em></td>
          <td>:</td>
          <td><input type="text" name="txtNoPengenalan" id="txtNoPengenalan" maxlength="12" value="$beanMaklumatPemohon.noPengenalan"  $readonly class="$inputTextClass" /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Pekerjaan</td>
          <td>:</td>
          <td><input type="text" name="txtPekerjaan" id="txtPekerjaan" value="$beanMaklumatPemohon.pekerjaan"  $readonly class="$inputTextClass" size="43" /></td>
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
          <td><input type="text" name="txtAlamat1" id="txtAlamat1" value="$beanMaklumatPemohon.alamat1"  $readonly class="$inputTextClass" size="43" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input type="text" name="txtAlamat2" id="txtAlamat2" value="$beanMaklumatPemohon.alamat2"  $readonly class="$inputTextClass" size="43" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input type="text" name="txtAlamat3" id="txtAlamat3" value="$beanMaklumatPemohon.alamat3"  $readonly class="$inputTextClass" size="43" /></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Poskod</td>
          <td>:</td>
          <td><input type="text" name="txtPoskod" id="txtPoskod" size="4" onkeyup="validatePoskod(this,this.value);" maxlength="5" value="$beanMaklumatPemohon.poskod" $readonly class="$inputTextClass"/></td>
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
          <td><input type="text" name="txtEmel" id="txtEmel" value="$beanMaklumatPemohon.emel" $readonly class="$inputTextClass" size="43"/></td>
        </tr>
        #elseif ($idKategoriPemohon == '2' || $idKategoriPemohon == '10' || $idKategoriPemohon == '9')
        #if($idKategoriPemohon != '9')
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>
          	#if($idKategoriPemohon == '2')
          	No. Pendaftaran Syarikat/<em>MyCoID</em>
          	#elseif($idKategoriPemohon == '10')
          	No. Pendaftaran/<em>MyCoID</em>
          	#end
          </td>
          <td>:</td>
          <td><input type="text" name="txtNoPengenalanSykt" id="txtNoPengenalanSykt" maxlength="12" value="$beanMaklumatPemohon.noPengenalanSykt" onblur="this.value=this.value.toUpperCase();doChangeNoPendaftaran();" $readonly class="$inputTextClass"/>
          </td>
        </tr>
        #end
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>
          	#if($idKategoriPemohon == '2')
          	Nama Syarikat
          	#elseif($idKategoriPemohon == '10')
          	Nama Organisasi
          	#else
            Nama
          	#end
          </td>
          <td>:</td>
          <td><input type="text" name="txtNamaSykt" id="txtNamaSykt" value="$beanMaklumatPemohon.namaSykt" $readonly class="$inputTextClass"  size="43" /></td>
        </tr>        
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Jenis Perniagaan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtPekerjaanSykt" class="$inputTextClass" id="txtPekerjaanSykt" cols="43" rows="5"   $readonly="$readonly">$beanMaklumatPemohon.pekerjaanSykt</textarea></td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Alamat</td>
          <td>:</td>
          <td><input type="text" name="txtAlamat1Sykt" id="txtAlamat1Sykt" value="$beanMaklumatPemohon.alamat1Sykt"  $readonly class="$inputTextClass" size="43" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input type="text" name="txtAlamat2Sykt" id="txtAlamat2Sykt" value="$beanMaklumatPemohon.alamat2Sykt"  $readonly class="$inputTextClass" size="43" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td><input type="text" name="txtAlamat3Sykt" id="txtAlamat3Sykt" value="$beanMaklumatPemohon.alamat3Sykt"  $readonly class="$inputTextClass" size="43" /></td>
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
          <td><input type="text" name="txtEmelSykt" id="txtEmelSykt" value="$beanMaklumatPemohon.emelSykt" $readonly class="$inputTextClass" size="43"/></td>
        </tr>
        #end
      </table>
      #end
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>KAWASAN PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Luar Perairan Negeri</td>
          <td>:</td>
          <td>$selectFlagLuar</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Negeri</td>
          <td>:</td>
          <td>$selectNegeriPerairan
            <input type="hidden" name="namaNegeriPerairan" id="namaNegeriPerairan" value="$namaNegeriPerairan" />
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Urusan</td>
          <td>:</td>
          <td>AKTA PELANTAR BENUA
            <input type="hidden" name="idUrusan" id="idUrusan" value="9"/>
            <input type="hidden" name="idSubUrusan" id="idSubUrusan" value="57"/></td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Jenis Lesen</td>
          <td>:</td>
          <td>$selectJenisLesen</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Terima</td>
          <td>:</td>
          <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" onblur="check_date(this);cekTarikhTerima(this)" size="11" $readonly class="$inputTextClass"/>
            #if ($mode != 'view') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="11" $readonly class="$inputTextClass"/>
            <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>No. Rujukan Surat</td>
          <td>:</td>
          <td><input type="text" name="txtNoRujukanSurat" id="txtNoRujukanSurat" value="$beanMaklumatPermohonan.noRujSurat" $readonly class="$inputTextClass" size="43" /></td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="txtPerkara" id="txtPerkara" rows="6" cols="43" $readonly class="$inputTextClass" >$beanMaklumatPermohonan.perkara</textarea>
            #if ($mode != 'view')
            <input type="button" name="cmdJanaTajuk" id="cmdJanaTajuk" value="Jana Tajuk" onclick="janaTajuk()"/>
            #end</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  
  #elseif($idJenisPermohonan == '2')
  #if($idFail != '99999' && $idFail != 'null')
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Kategori Pemohon</td>
          <td>:</td>
          <td>$selectIndividuBukanIndividu</td>
        </tr>
        #if ($idKategoriIndividu == '1')
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top"></td>
          <td>:</td>
          <td>$selectKategoriPemohon</td>
        </tr>
        #elseif ($idKategoriIndividu == '11')
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top"></td>
          <td>:</td>
          <td>$selectKategoriPemohon2</td>
        </tr>
        #end
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">
          	#if($idKategoriPemohon == '2') Nama Syarikat
          	#elseif($idKategoriPemohon == '10') Nama Organisasi
          	#else Nama #end
          <td>:</td>
          <td>$beanMaklumatPemohon.nama</td>
        </tr>
        #if ($idKategoriPemohon == '1')
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Jenis Pengenalan</td>
          <td>:</td>
          <td>$selectJenisPengenalanIndividu</td>
        </tr>
        #end
        #if($idKategoriPemohon != '9')
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">
          	#if($idKategoriPemohon == '2')
          	No. Pendaftaran Syarikat/<em>MyCoID</em>
          	#elseif($idKategoriPemohon == '10')
          	No. Pendaftaran/<em>MyCoID</em>
          	#else
          	No. Pengenalan/<em>MyID
          	#end
          </td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noPengenalan</td>
        </tr>
        #end
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Pekerjaan</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.pekerjaan</td>
        </tr>
        #if ($idKategoriPemohon == '1')
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Jantina</td>
          <td>:</td>
          <td>$selectJantina</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Bangsa</td>
          <td>:</td>
          <td>$selectBangsa</td>
        </tr>
        #end
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Alamat</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.alamat1</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.alamat2</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.alamat3</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Poskod</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.poskod</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Negeri</td>
          <td>:</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Bandar</td>
          <td>:</td>
          <td>$selectBandar</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No. Telefon</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noTel</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No. Telefon Bimbit</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noTelBim</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No. Faks</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noFax</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">E-mel</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.emel</td>
        </tr>
  	  </table>
  	</fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>KAWASAN PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatKawasanMohon in $BeanMaklumatKawasanMohon)
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Luar Perairan Negeri</td>
          <td>:</td>
          <td>$selectFlagLuar</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Negeri</td>
          <td>:</td>
          <td>$selectNegeriPerairan
            <input type="hidden" name="namaNegeriPerairan" id="namaNegeriPerairan" value="$namaNegeriPerairan" />
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Urusan</td>
          <td>:</td>
          <td>AKTA PELANTAR BENUA
            <input type="hidden" name="idUrusan" id="idUrusan" value="9"/>
            <input type="hidden" name="idSubUrusan" id="idSubUrusan" value="57"/></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Jenis Lesen</td>
          <td>:</td>
          <td>$selectJenisLesen</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Tarikh Terima</td>
          <td>:</td>
          <td>$beanMaklumatPermohonan.tarikhTerima</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td>$beanMaklumatPermohonan.tarikhSurat</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td>No. Rujukan Surat</td>
          <td>:</td>
          <td>$beanMaklumatPermohonan.noRujSurat</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td>
          	<textarea name="txtPerkara2" id="txtPerkara2" rows="6" cols="43" $readonly class="$inputTextClass" >$!beanMaklumatPermohonan.perkara2</textarea>
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
  #end
  
  
  
  #end
  #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'new')
	  <!--       <input type="button" name="cmdJanaTajuk" id="cmdJanaTajuk" value="Jana Tajuk" onclick="janaTajuk()"/> -->
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onclick="daftarBaru()"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
      #end
      #if ($mode == 'view')
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
      #end</td>
  </tr>
</table>
<script>
function doChangeJenisPermohonan() {
	doAjaxCall${formName}("doChangeJenisPermohonan");
}
function doChangeNoFailAPB() {
	doAjaxCall${formName}("doChangeNoFailAPB");
}
function doChangeIndividuBknIndividu() {
	doAjaxCall${formName}("doChangeIndividuBknIndividu");
}
function doChangeKategoriPemohon() {
	doAjaxCall${formName}("doChangeKategoriPemohon");
}
function doChangeKategoriPemohon2() {
	doAjaxCall${formName}("doChangeKategoriPemohon2");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeNoPendaftaran() {
	doAjaxCall${formName}("doChangeNoPendaftaran");
}
function doChangeNegeriPerairan() {
	doAjaxCall${formName}("doChangeNegeriPerairan");
}
function doChangeJenisLesen() {
	doAjaxCall${formName}("doChangeJenisLesen");
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
	if (document.${formName}.socKategoriPemohon.value == '1'){
		//INDIVIDU
		if(document.${formName}.txtNama.value == ""){
			alert('Sila masukkan Nama Pemohon.');
			document.${formName}.txtNama.focus(); 
			return; 
		}
		if(document.${formName}.socJenisPengenalanIndividu.value == ""){
			alert('Sila pilih Jenis Pengenalan.');
			document.${formName}.socJenisPengenalanIndividu.focus(); 
			return; 
		}
		if(document.${formName}.txtNoPengenalan.value == ""){
			alert('Sila masukkan No. Pengenalan.');
			document.${formName}.txtNoPengenalan.focus(); 
			return; 
		}
		if(document.${formName}.txtPekerjaan.value == ""){
			alert('Sila masukkan Pekerjaan.');
			document.${formName}.txtPekerjaan.focus(); 
			return; 
		}
		if(document.${formName}.socJantina.value == ""){
			alert('Sila pilih Jantina.');
			document.${formName}.socJantina.focus(); 
			return; 
		}
		if(document.${formName}.socBangsa.value == ""){
			alert('Sila pilih Bangsa.');
			document.${formName}.socBangsa.focus(); 
			return; 
		}
		if(document.${formName}.txtAlamat1.value == ""){
			alert('Sila masukkan Alamat.');
			document.${formName}.txtAlamat1.focus(); 
			return; 
		}
		if(document.${formName}.txtPoskod.value == ""){
			alert('Sila masukkan Poskod.');
			document.${formName}.txtPoskod.focus(); 
			return; 
		}
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
			document.${formName}.socNegeri.focus(); 
			return; 
		}
		if(document.${formName}.socBandar.value == ""){
			alert('Sila pilih Bandar.');
			document.${formName}.socBandar.focus(); 
			return; 
		}
	
	} else if (document.${formName}.socKategoriPemohon.value == '2'){
		//SYARIKAT
		if(document.${formName}.txtNamaSykt.value == ""){
			alert('Sila masukkan Nama Syarikat.');
			document.${formName}.txtNamaSykt.focus(); 
			return; 
		}
		if(document.${formName}.txtNoPengenalanSykt.value == ""){
			alert('Sila masukkan No. Pendaftaran Syarikat.');
			document.${formName}.txtNoPengenalanSykt.focus(); 
			return; 
		}
		if(document.${formName}.txtAlamat1Sykt.value == ""){
			alert('Sila masukkan Alamat.');
			document.${formName}.txtAlamat1Sykt.focus(); 
			return; 
		}
		if(document.${formName}.txtPoskodSykt.value == ""){
			alert('Sila masukkan Poskod.');
			document.${formName}.txtPoskodSykt.focus(); 
			return; 
		}
		if(document.${formName}.socNegeriSykt.value == ""){
			alert('Sila pilih Negeri.');
			document.${formName}.socNegeriSykt.focus(); 
			return; 
		}
		if(document.${formName}.socBandarSykt.value == ""){
			alert('Sila pilih Bandar.');
			document.${formName}.socBandarSykt.focus(); 
			return; 
		}
	}
	if(document.${formName}.socFlagLuar.value == ""){
		alert('Sila pilih Luar Perairan Negeri.');
  		document.${formName}.socFlagLuar.focus(); 
		return; 
	}
	if(document.${formName}.socNegeriPerairan.value == ""){
		alert('Sila pilih Negeri Perairan.');
  		document.${formName}.socNegeriPerairan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionLesen.value = "daftarBaru";
		return;
	}
	
	document.${formName}.actionLesen.value = "papar";
	doAjaxCall${formName}("doDaftarBaru");
}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBSenaraiFailView";
	document.${formName}.method="POST";	
	document.${formName}.actionLesen.value = "";
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
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function janaTajuk(){	
	if(document.${formName}.socKategoriPemohon.value == ""){
		alert('Sila lengkapkan maklumat pemohon terlebih dahulu');
		return; 
	} else if(document.${formName}.socKategoriPemohon.value == "1"){
		if(document.${formName}.txtNama.value == ""){
			alert('Sila lengkapkan maklumat pemohon terlebih dahulu');
			return; 
		}
	} else {
		if(document.${formName}.txtNamaSykt.value == ""){
			alert('Sila lengkapkan maklumat pemohon terlebih dahulu');
			return; 
		}
	}		
	if(document.${formName}.socFlagLuar.value == ""){
		alert('Sila lengkapkan maklumat kawasan permohonan terlebih dahulu');
		return; 
	}	
	if(document.${formName}.socNegeriPerairan.value == ""){
		alert('Sila lengkapkan maklumat kawasan permohonan terlebih dahulu');
		return; 
	}
	
	var strTajuk = "Permohonan Lesen Untuk Mendapatkan Pasir Dasar Laut Di Bawah Seksyen 4, Akta Pelantar Benua 1966 di Kawasan";
	if (document.${formName}.socFlagLuar.value == '1') {
		strTajuk = strTajuk + " Luar"
	}
	strTajuk = strTajuk + " Perairan Negeri " + document.${formName}.namaNegeriPerairan.value + " Oleh ";	
	
	if(document.${formName}.socKategoriPemohon.value == "1"){
		strTajuk = strTajuk + document.${formName}.txtNama.value;
	} else {
		strTajuk = strTajuk + document.${formName}.txtNamaSykt.value;
	}
	
	document.${formName}.txtPerkara.value = strTajuk.toUpperCase();
}

function seterusnya(){
	document.${formName}.action = "$EkptgUtil.getTabID("Akta Pelantar Benua",$portal_role)?_portal_module=ekptg.view.php2.FrmAPBMaklumatPermohonanView";
	document.${formName}.submit();
}
</script>
