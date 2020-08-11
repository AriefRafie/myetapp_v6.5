<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>

#set($saizTxtPerkara="1000")
#set($saizTxtJenisPerniagaan="500")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionPengkuatkuasaan" type="hidden" id="actionPengkuatkuasaan" value="$actionPengkuatkuasaan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" />
  <input type="hidden" name="idHakmilikSementara" id="idHakmilikSementara" value="$idHakmilikSementara" />
  <input type="hidden" name="idPHPBorangK" id="idPHPBorangK" value="$idPHPBorangK" />
  <input type="hidden" name="idPPTBorangK" id="idPPTBorangK" value="$idPPTBorangK" />
  <input type="hidden" name="idHakmilikUrusan" id="idHakmilikUrusan" value="$idHakmilikUrusan" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
  	<td colspan="2"><fieldset>
  		<legend><strong>MAKLUMAT FAIL</strong></legend>
  		<table width="100%" border="0" cellspacing="2" cellpadding="2">
  			#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
	  		<tr>
	          <td width="1%">&nbsp;</td>
	          <td width="28%" valign="top">No. Fail</td>
	          <td width="1%" >:</td>
	          <td width="70%"><strong>$beanMaklumatPermohonan.noFail</strong>
	            <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
	            <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" /></td>
	        </tr>
	        <!--tr>
	          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
	          <td>Jenis Fail</td>
	          <td>:</td>
	          <td><select name="jenisFail" id="jenisFail" style="width:170px;" $readonly class="$inputTextClass" $inputTextClass>
	              #if($beanMaklumatPermohonan.jenisFail == "1")
	               <option value="">SILA PILIH</option>
	               <option value="1" selected="selected">1 - PENGUATKUASAAN</option>
	               <option value="2">2 - PELBAGAI</option>
				  #elseif($beanMaklumatPermohonan.jenisFail == "2")
					<option value="">SILA PILIH</option>
	               <option value="1">1 - PENGUATKUASAAN</option>
	               <option value="2" selected="selected">2 - PELBAGAI</option>
	              #elseif($beanMaklumatPermohonan.jenisFail == "")
	               <option value="" selected="selected">SILA PILIH</option>
	               <option value="1">1 - PENGUATKUASAAN</option>
	               <option value="2">2 - PELBAGAI</option>
	              #end
	             </select>
	          </td>
	        </tr-->
	        <tr>
	          <td width="1%">&nbsp;</td>
	          <td width="28%" valign="top">Urusan</td>
	          <td width="1%">:</td>
	          <td width="70%">PENGUATKUASAAN</td>
	        </tr>
<!-- 	        <tr> -->
<!-- 	          <td width="1%">&nbsp;</td> -->
<!-- 	          <td width="28%" valign="top">Suburusan</td> -->
<!-- 	          <td width="1%">:</td> -->
<!-- 	          <td width="70%">PENGUATKUASAAN</td> -->
<!-- 	        </tr> -->
	        #end
  		</table>
  	</fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PENGADU</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Kategori Pengadu</td>
          <td>:</td>
          <td width="70%">$selectKategoriPemohon</td>
        </tr>
        #if($idKategoriPemohon == '3' || $idKategoriPemohon == '4' || $idKategoriPemohon == '5' || $idKategoriPemohon == '8')
        #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
        <tr>
          <td></td>
          <td>Nama Pegawai</td>
          <td>:</td>
          <td><input name="txtNamaPengadu" type="text" id="txtNamaPengadu" value="$beanMaklumatPemohon.namaPengadu" size="43" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"></td>
        </tr>
        #end
        #end
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
          <td>$beanMaklumatAgensi.namaAgensi</td>
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
        #if($idKategoriPemohon == '1' || $idKategoriPemohon == '2' || $idKategoriPemohon == '6' || $idKategoriPemohon == '7' || $idKategoriPemohon == '9')
        #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td> #if($idKategoriPemohon == '1')
            Nama
            #elseif($idKategoriPemohon == '2')
            Nama Syarikat
            #elseif($idKategoriPemohon == '6')
            Nama Persatuan
            #elseif($idKategoriPemohon == '7')
            Nama Agensi
            #elseif($idKategoriPemohon == '9')
            Nama
            #end </td>
          <td>:</td>
          <td><input name="txtNama" type="text" class="$inputTextClass" id="txtNama" value="$beanMaklumatPemohon.nama" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        #if($idKategoriPemohon != '1')
        <tr>
          <td></td>
          <td>Nama Pegawai</td>
          <td>:</td>
          <td><input name="txtNamaPengadu" type="text" id="txtNamaPengadu" value="$beanMaklumatPemohon.namaPengadu" size="43" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          #if($idKategoriPemohon == '1')
          <td>No. Kad Pengenalan/<em>MyID</em></td>
          #else
          <td>No. Pendaftaran Syarikat/<em>MyCoid</em></td>
          #end
          <td>:</td>
          <td><input name="txtNoPendaftaran" type="text" class="$inputTextClass" id="txtNoPendaftaran" value="$beanMaklumatPemohon.noPendaftaran" maxlength="12" $readonly onblur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td><input name="txtAlamat1" type="text" class="$inputTextClass" id="txtAlamat1" value="$beanMaklumatPemohon.alamat1" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input name="txtAlamat2" type="text" class="$inputTextClass" id="txtAlamat2" value="$beanMaklumatPemohon.alamat2" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input name="txtAlamat3" type="text" class="$inputTextClass" id="txtAlamat3" value="$beanMaklumatPemohon.alamat3" size="43" maxlength="80" $readonly onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td><input name="txtPoskod" type="text" class="$inputTextClass" id="txtPoskod" value="$beanMaklumatPemohon.poskod" size="5" maxlength="5" $readonly onblur="validateLength(this.value);"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Bandar</td>
          <td>:</td>
          <td>$selectBandar</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>E-mel</td>
          <td>:</td>
          <td><input name="txtEmel" type="text" class="$inputTextClass" id="txtEmel" value="$beanMaklumatPemohon.emel" maxlength="50" $readonly/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Tel</td>
          <td>:</td>
          <td><input name="txtNoTel" type="text" class="$inputTextClass" id="txtNoTel" onkeyup="validateNumber(this,this.value);" value="$beanMaklumatPemohon.noTel" maxlength="12" $readonly/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Faks</td>
          <td>:</td>
          <td><input name="txtNoFaks" type="text" class="$inputTextClass" id="txtNoFaks" onkeyup="validateNumber(this,this.value);" value="$beanMaklumatPemohon.noFaks" maxlength="12" $readonly/>
          </td>
        </tr>
        #end
        #end
        #if($idKategoriPemohon == '4' || $idKategoriPemohon == '5' || $idKategoriPemohon == '8')
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeri </td>
        </tr>
        #if($idKategoriPemohon == '8')
        <tr>
          <td></td>
          <td>Bahagian</td>
          <td>:</td>
          <td>$selectSeksyen</td>
        </tr>
        #end
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
          <td>$beanMaklumatPejabat.negeri</td>
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
              <!-- <option $selected4 value="4">LAIN-LAIN TANAH</option> -->
            </select></td>
        </tr>
        #if ($idJenisTanah == '1' || $idJenisTanah == '2')
        #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%">
          #if ($mode == 'new')
           #if ($idKategoriPemohon == '3')
            #if ($idKementerian != '' && $idKementerian != '99999')
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();"/>
            <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onclick="pilihTanah('$idKategoriPemohon','$idKementerian' , '99999')"/>
            #else
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled" />
            #end
           #elseif ($idKategoriPemohon != '3')
            #if ($idNegeri != '' && $idNegeri != '99999')
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();" />
            <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onclick="pilihTanah('$idKategoriPemohon','99999' , '$idNegeri')"/>
            #else
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled" />
            #end
           #end
          #else
           <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
          #end
           <span class="style1">$errorPeganganHakmilik</span>
          </td>
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
            <input type="hidden" name="noWartaTanah" id="noWartaTanah" value="$beanMaklumatTanah.noWarta"></td>
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
            <input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatTanah.idKementerian" /></td>
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
        #if ($idJenisTanah == '4')
        #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
        <tr>
          <td>&nbsp;</td>
          <td>Pegangan Hakmilik</td>
          <td>:</td>
          <td><input type="text" class="$inputTextClass" name="txtPeganganHakmilik1" id="txtPeganganHakmilik1" value="$beanMaklumatTanah.peganganHakmilik1" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Hakmilik</td>
          <td>:</td>
          <td>$selectJenisHakmilik
          	<input type="hidden" name="idJenisHakmilik" id="idJenisHakmilik" value="$idJenisHakmilik"/>
          	<input type="text" class="$inputTextClass" name="txtNoHakmilikTanah" id="txtNoHakmilikTanah" value="$beanMaklumatTanah.noHakmilik" size="43" maxlength="80" $readonly/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Warta</td>
          <td>:</td>
          <td>
            <input type="text" class="$inputTextClass" name="noWarta" id="noWarta" value="$beanMaklumatTanah.noWarta" size="43" maxlength="80" $readonly onblur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Warta</td>
          <td>:</td>
          <td>
          	<input type="text" name="tarikhWarta" id="tarikhWarta" value="$beanMaklumatTanah.tarikhWarta" onBlur="check_date(this);cekTarikhWarta(this)" size="9" $readonly class="$inputTextClass"/>
          		<a href="javascript:displayDatePicker('tarikhWarta',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</a>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Lot</td>
          <td>:</td>
          <td>$selectJenisLot
          	<input type="hidden" name="idJenisLot" id="idJenisLot" value="$idJenisLot" />
          	<input type="text" class="$inputTextClass" name="txtNoLot" id="txtNoLot" value="$beanMaklumatTanah.noLot" size="43" maxlength="80" $readonly/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Lot</td>
          <td>:</td>
          <td>
            <input type="text" class="$inputTextClass" name="txtLuas" id="txtLuas" value="$beanMaklumatTanah.luas" size="43" maxlength="80" $readonly/>
            #parse("app/php2/unit_luas.jsp")
           </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>
          	$selectNegeriTanah
            <input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$beanMaklumatTanah.negeri" /></td>
            <input type="hidden" name="idNegeri" id="idNegeri" value="$beanMaklumatTanah.idNegeri" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Daerah</td>
          <td>:</td>
          <td>$selectDaerahTanah
            <input type="hidden" name="namaDerahTanah" id="namaDerahTanah" value="$beanMaklumatTanah.daerah" />
            <input type="hidden" name="idDaerahTanah" id="idDaerahTanah" value="$beanMaklumatTanah.idDaerah" /></td>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Mukim</td>
          <td>:</td>
          <td>$selectMukimTanah
            <input type="hidden" name="namaMukimTanah" id="namaMukimTanah" value="$beanMaklumatTanah.mukim" /></td>
            <input type="hidden" name="idMukimTanah" id="idMukimTanah" value="$beanMaklumatTanah.idMukim" /></td>
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
                  <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik" onblur="doChangePeganganHakmilikBorangK();"/>
                  <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Borang K" onclick="pilihBorangK()"/>
                  #else
                  <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik" readonly="readonly" class="disabled" />
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
                  <input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatBorangK.idLuas">
                  <input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatBorangK.luasBersamaan"></td>
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
                  <input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatBorangK.idNegeri"></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Kementerian</td>
                <td>:</td>
                <td>$beanMaklumatBorangK.kementerian
                  <input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatBorangK.idKementerian"></td>
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
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%" valign="top">Tarikh Terima Aduan</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" onblur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
            #if ($mode != 'view') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat Aduan</td>
          <td>:</td>
          <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
            <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizTxtPerkara);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizTxtPerkara);" >$beanMaklumatPermohonan.perkara</textarea>
            #if ($mode == 'new')
            <input type="button" name="cmdDaftarBaru2" id="cmdDaftarBaru2" value="Jana Tajuk" onclick="janaTajuk()"/>
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
  #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'new')
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onclick="daftarBaru()"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
      #else
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
      #end </td>
  </tr>
</table>
<script>
function doChangeJenisTanah() {
	doAjaxCall${formName}("doChangeJenisTanah");
}
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeAgensi() {
	doAjaxCall${formName}("doChangeAgensi");
}
function doChangeJenisHakmilik(){
	doAjaxCall${formName}("doChangeJenisHakmilik");
}
function doChangeJenisLot(){
	doAjaxCall${formName}("doChangeJenisLot");
}
function doChangeNegeriTanah(){
	doAjaxCall${formName}("doChangeNegeriTanah");
}
function doChangeDaerahTanah(){
	doAjaxCall${formName}("doChangeDaerahTanah");
}
function doChangeMukimTanah(){
	doAjaxCall${formName}("doChangeMukimTanah");
}
/* function pilihTanah() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupSenaraiTanahView";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
} */
function pilihTanah(idKategoriPemohon,idKementerianPemohon,idNegeri) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupSenaraiTanahView?idKategoriPemohon="+idKategoriPemohon+"&idKementerianPemohon="+idKementerianPemohon+"&idNegeriPemohon="+idNegeri;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi,idHakmilikSementara) {
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	document.${formName}.idHakmilikSementara.value = idHakmilikSementara;
	doAjaxCall${formName}("doChangeMaklumatTanah");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
function kembali() {
	document.${formName}.actionPengkuatkuasaan.value = "";
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
		alert('Sila masukkan Tarikh Terima Aduan.');
  		document.${formName}.tarikhTerima.focus();
		return;
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat Aduan.');
  		document.${formName}.tarikhSurat.focus();
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
	}
	if(document.${formName}.socKategoriPemohon.value == "1" || document.${formName}.socKategoriPemohon.value == "2" || document.${formName}.socKategoriPemohon.value == "6" || document.${formName}.socKategoriPemohon.value == "7" || document.${formName}.socKategoriPemohon.value == "9"){
		if(document.${formName}.txtNama.value == ""){
			alert('Sila masukkan Nama.');
  			document.${formName}.txtNama.focus();
			return;
		}
	}
	if((document.${formName}.socKategoriPemohon.value == "4") || (document.${formName}.socKategoriPemohon.value == "5") || (document.${formName}.socKategoriPemohon.value == "8")){
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
			document.${formName}.socNegeri.focus();
			return;
		}
		if(document.${formName}.socPejabat.value == ""){
			alert('Sila pilih Pejabat.');
			document.${formName}.socPejabat.focus();
			return;
		}
	}
	if(document.${formName}.socJenisTanah.value == "3"){
		if(document.${formName}.idPHPBorangK.value == ""){
			alert('Sila pilih Pegangan Hakmilik.');
			return;
		}
	} else {
		if(document.${formName}.idHakmilikAgensi.value == "" && document.${formName}.idHakmilikSementara.value == ""){
			alert('Sila pilih Pegangan Hakmilik.');
			return;
		}
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPengkuatkuasaan.value = "daftarBaru";
		return;
	}

	document.${formName}.actionPengkuatkuasaan.value = "papar";
	document.${formName}.hitButton.value = "daftarBaru";
	doAjaxCall${formName}("");
}
function checkEmail(str) {

		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   alert("Sila Masukan Email Dengan Betul.")
		   document.${formName}.txtEmelI.focus();
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		   alert("Sila Masukan Email Dengan Betul.")
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		    alert("Sila Masukan Email Dengan Betul.")
		    return false
		 }

 		 return true
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function validateLength(str) {

	if (str.length < 5) {
 		alert("Sila Masukan Poskod Dengan Betul.")
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
function janaTajuk() {
	if(document.${formName}.idHakmilikAgensi.value != "4"){
		if(document.${formName}.idHakmilikAgensi.value == "" && document.${formName}.idHakmilikSementara.value == ""){
			alert('Sila pilih Pegangan Hakmilik Sebelum Menjana Tajuk.');
			return;
		}
	}

	//var str1  = document.${formName}.keteranganLotTanah.value;
	var str2  = document.${formName}.noLotTanah.value;
	var str3  = document.${formName}.noWartaTanah.value;
	var str4  = document.${formName}.noMilikTanah.value;
	var str5  = document.${formName}.namaMukimTanah.value;
	var str6  = document.${formName}.namaDerahTanah.value;
	var str7  = document.${formName}.namaNegeriTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;

	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str3;
	}

	var strTajuk = "PENCEROBOHAN TANAH " + statusRizabTnh + " PERSEKUTUAN DI ATAS TANAH  " + str2 +", " + str3 + str4 + ", " + str5 + ", " + str6 + ", " + str7;
	var strTajuk = "PENCEROBOHAN TANAH MILIK/RIZAB KJP DI ATAS TANAH  " + str2 +", " + str3 + str4 + ", " + str5 + ", " + str6 + ", " + str7;


	document.${formName}.txtPerkara.value = strTajuk;
}
function seterusnya(){
	document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBMaklumatPermohonanView";
	document.${formName}.submit();
}

function pilihBorangK(idKategoriPemohon,idKementerianPemohon,idNegeri) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupSenaraiBorangKView";
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihBorangK(idPHPBorangK) {
	document.${formName}.idPHPBorangK.value = idPHPBorangK;
	doAjaxCall${formName}("doChangeMaklumatBorangK");
}
function doChangePeganganHakmilikBorangK() {
	doAjaxCall${formName}("doChangePeganganHakmilikBorangK");
}
</script>