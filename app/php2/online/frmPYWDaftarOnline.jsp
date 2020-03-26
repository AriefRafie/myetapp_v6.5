<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
#set($saizTxtPerkara="1000")
<input name="actionPenyewaan" type="hidden" id="actionPenyewaan" value="$actionPenyewaan"/>
<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
<input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
<input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
<input name="hitButton" type="hidden" id="hitButton"/>
<input name="mode" type="hidden" id="mode" value="$mode"/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="idJenisTanah" type="hidden" id="idJenisTanah" value="$idJenisTanah"/> 
<input type="hidden" name="idPHPBorangK" id="idPHPBorangK" value="$idPHPBorangK" /> 
<input type="hidden" name="idPPTBorangK" id="idPPTBorangK" value="$idPPTBorangK" /> 
<input type="hidden" name="idHakmilikUrusan" id="idHakmilikUrusan" value="$idHakmilikUrusan" />
<table width="100%" border="0">
  <tr>
  
<!-- TAMBAH JENIS PERMOHONAN -->
  	<td><fieldset><legend><strong>JENIS PERMOHONAN</strong></legend>
  	<table width="100%" border="0">
  	<tr>
  		<td width="1%"><span class="style1">*</span></td>
    	<td width="28%">Jenis Permohonan</td>
        <td width="1%">:</td>
        <td width="70%"></td>
  	</tr>
  	</table>
  	</td>
  </tr>

<!-- TAMBAH MAKLUMAT PEMOHON -->
  <tr>
  	<td><fieldset><legend><strong>MAKLUMAT PEMOHON</strong></legend>
  	<table width="100%" border="0" cellspacing="2" cellpadding="2">
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">Kategori Pemohon</td>
  		<td width="1%">:</td>
  		<td width="70%"> $!pemohon.get("kategoriPemohon")</td>
  	</tr>
  	
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">
  		#if($!pemohon.get("kategoriPemohon") == "INDIVIDU") 
  			Nama Pemohon 
  		#end
  		#if($!pemohon.get("kategoriPemohon") != "INDIVIDU")
  			Nama Syarikat
  		#end
  		</td>
  		<td width="1%">:</td>
  		<td width="70%"> $!pemohon.get("namaPemohon")
  		<input type="hidden" name="txtNama" id="txtNama" value="$!pemohon.get("namaPemohon")"></td>
  	</tr>
 	<tr>
  		<td></td>
  		<td>
  		#if($!pemohon.get("kategoriPemohon") == "INDIVIDU") 
  			No. Kad Pengenalan/MyID 
  		#end
  		#if($!pemohon.get("kategoriPemohon") != "INDIVIDU")
  			No. Pendaftaran Syarikat/MyCoid
  		#end
  		</td>
  		<td>:</td>
  		<td>$!pemohon.get("noPengenalan")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td valign="top">Alamat</td>
  		<td valign="top">:</td>
  		<td>$!pemohon.get("alamat1")<br>$!pemohon.get("alamat2")<br>$!pemohon.get("alamat3")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>Poskod</td>
  		<td>:</td>
  		<td>$!pemohon.get("poskod")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>Negeri</td>
  		<td>:</td>
  		<td>$!pemohon.get("negeri")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>Bandar</td>
  		<td>:</td>
  		<td>$!pemohon.get("bandar")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>No. Tel</td>
  		<td>:</td>
  		<td>$!pemohon.get("noTel")</td>
  	</tr>
  	<tr>
  		<td></td>
  		<td>No. Faks</td>
  		<td>:</td>
  		<td>$!pemohon.get("noFax")</td>
  	</tr>
  	  	<tr>
  		<td></td>
  		<td>Emel</td>
  		<td>:</td>
  		<td>$!pemohon.get("emel")</td>
  	</tr>
  	</fieldset></table>
  	</td>
  </tr>
  
<!-- MAKLUMAT TANAH -->
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT TANAH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        
        <tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Jenis Tanah</td>
			<td width="1%">:</td>
			<td width="70%">
				<select name="socJenisTanah" id="socJenisTanah" onchange="doChangeJenisTanah()"
					$inputTextClass class="$inputTextClass">
				<option $selected value="0">SILA PILIH</option>
				<option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option>
				<option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option>
				<option $selected3 value="3">BORANG K</option>
				</select>
			</td>
		</tr>
		<!-- MAKLUMAT HAKMILIK -->
		#if($idJenisTanah == '1' || $idJenisTanah == '2')
		#foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
        <tr>
          <td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td width="28%">Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"> 
          #if ($mode == 'new')
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();">
          #else
            <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
          #end
            <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi">
            <span class="style1">$errorPeganganHakmilik</span> 
            <span class="style4"><i><font color="#ff0000">Contoh</font> : </i><span class="style5">160140GRN00000576</span></span>
            </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noLot
          <input type="hidden" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.lot" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.luasLot
          <input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatTanah.idLuas" /> 
          <input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatTanah.luasBersamaan" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Hakmilik</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noHakmilik
          <input type="hidden" name="noMilikTanah" id="noMilikTanah" value="$beanMaklumatTanah.hakmilik" /></td>
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
            <input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatTanah.idNegeriTanah">
            <input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$$beanMaklumatTanah.negeri">
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$beanMaklumatTanah.kementerian
            <input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatTanah.idKementerian">
            <input type="hidden" name="kodKementerian" id="kodKementerian" value="$beanMaklumatTanah.kodKementerian">
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
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
  		</tr>
  		<!-- MAKLUMAT BORANG K -->
  		#if ($idJenisTanah == '3')
  		#foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
		<tr>
			<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			<td width="28%">Pegangan Hakmilik</td>
			<td width="1%">:</td>
			<td width="70%">
				#if ($mode == 'new') 
					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" 
					value="$beanMaklumatBorangK.peganganHakmilik" onblur="doChangePeganganHakmilikBorangK();" />
					<!--<input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Borang K" onclick="pilihBorangK()" />--> 
				#else 
					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik"
					readonly="readonly" class="disabled" /> 
				#end 
					<span class="style1">$errorPeganganHakmilik</span>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Lot</td>
			<td>:</td>
			<td>$beanMaklumatBorangK.lot</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Luas Lot</td>
			<td>:</td>
			<td>$beanMaklumatBorangK.luas 
				<input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatBorangK.idLuas"> 
				<input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatBorangK.luasBersamaan">
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>No. Hakmilik</td>
			<td>:</td>
			<td>$beanMaklumatBorangK.hakmilik</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Mukim</td>
			<td>:</td>
			<td>$beanMaklumatBorangK.mukim</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Daerah</td>
			<td>:</td>
			<td>$beanMaklumatBorangK.daerah</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Negeri</td>
			<td>:</td>
			<td>$beanMaklumatBorangK.negeri <input type="hidden" name="idNegeriTanah" id="idNegeriTanah"
				value="$beanMaklumatBorangK.idNegeri">
			</td>
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
		#end
		</table>
	</fieldset></td>
  </tr>
  
<!-- MAKLUMAT PERMOHONAN -->
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)        
        <input type="hidden" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" 
        onblur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass" />
        <tr>
        	<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
        	<td width="28%">Urusan</td>
          	<td width="1%">:</td>
          	<td width="70%">$selectUrusan</td>
        </tr>
        <tr>
          	<td>#if ($mode == 'new')<span class="style1">*</span>#end</td>
          	<td>Suburusan</td>
          	<td>:</td>
          	<td>$selectSuburusan</td>
        </tr>
        <tr>
        	<td></td>
        	<td>Tarikh Surat/Borang</td>
        	<td>:</td>
        	<td><input type="text" name="txttarikhSurat" id="txttarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat"
        		onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass" /> 
        		#if ($mode != 'view')
        			<a href="javascript:displayDatePicker('txttarikhSurat',false,'dmy');">
        			<img border="0" src="../img/calendar.gif" /></a>
        		#end
        	</td>
        </tr>
        <tr>
        	<td></td>
        	<td>No. Rujukan Surat</td>
        	<td>:</td>
        	<td>
        	<input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat"
        	value="$beanMaklumatPermohonan.noRujukanSurat" $readonly onblur="this.value=this.value.toUpperCase();" 
        	size="38" maxlength="50" />
			</td>
        </tr>
        <tr>
        <td></td>
        <td valign="top">Perkara</td>
        <td valign="top">:</td>
        <td><textarea name="txtperkara" id="txtperkara" rows="5"
			cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"
			onKeyUp="textCounter(this.form.txtperkara,this.form.remLen1,$!saizTxtPerkara);"
			onKeyDown="textCounter(this.form.txtperkara,this.form.remLen1,$!saizTxtPerkara);">$beanMaklumatPermohonan.perkara</textarea>
		</td>
		</tr>
		#if ($mode != 'view')
		<tr>
			<td valign="top">&nbsp;</td>
			<td valign="top">&nbsp;</td>
			<td valign="top">&nbsp;</td>
			<td>Baki Aksara :&nbsp; 
				<input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtPerkara" />
			</td>
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
    <td width="100%" align="center"> 
    #if ($mode == 'new')
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Seterusnya" onclick="daftar()"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
    #end 
    </td>
  </tr>
</table>
<script>
function pilihTanah() {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmPYWOnlinePopupSenaraiTanahView";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	doAjaxCall${formName}("");
}
function doChangeUrusan() {
	doAjaxCall${formName}("doChangeUrusan");
}
function doChangeSuburusan() {
	doAjaxCall${formName}("doChangeSuburusan");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeJenisTanah() {
	doAjaxCall${formName}("doChangeJenisTanah");
}
function doChangePeganganHakmilikBorangK() {
	doAjaxCall${formName}("doChangePeganganHakmilikBorangK");
}
function doChangeTujuan() {
	doAjaxCall${formName}("doChangeTujuan");
}
function daftar() {
	if(document.${formName}.socUrusan.value == ""){
		alert('Sila pilih Urusan.');
  		document.${formName}.socUrusan.focus(); 
		return; 
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih Suburusan.');
  		document.${formName}.socSuburusan.focus(); 
		return; 
	}
	if(document.${formName}.idHakmilikAgensi.value == ""){
		alert('Sila pilih Pegangan Hakmilik.');
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPenyewaan.value = "daftarBaru";
		return;
	}
	document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doDaftarBaru";
	document.${formName}.submit();
}
function kembali() {	
	document.${formName}.actionPenyewaan.value = "";
	document.${formName}.submit();
}
function seterusnya() {	
	document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
	// otherwise, update 'Baki Aksara' counter
	else
		countfield.value = maxlimit - field.value.length;
}
function pilihBorangK() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupSenaraiBorangKView";
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

</script>