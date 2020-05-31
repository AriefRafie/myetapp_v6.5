<style type="text/css">

</style>

#set($saizTxtPerkara="1000")

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"></td>
          <td width="28%">Jenis Tanah</td>
          <td width="1%">:</td>
          <td width="70%"><select name="socJenisTanah" id="socJenisTanah" onchange="doChangeJenisTanah()">
              <option $selected value="0">SILA PILIH</option>
              <option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option>
              <option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option>
              <option $selected3 value="3">BORANG K</option>
            </select></td>
        </tr>
        #if ($idJenisTanah == '1' || $idJenisTanah == '2')
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Pegangan Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"> 
            		<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();"/>
            		<input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onclick="pilihTanah('$idKategoriPemohon','$idKementerian' , '99999')"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Lot</td>
          <td>:</td>
          <td>$beanMaklumatTanah.lot 
            <input type="hidden" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.lot" /></td>
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
        
        #if ($idJenisTanah == '3')
        <tr>
          <td colspan="4"><fieldset>
            <legend><strong>Maklumat Lot</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Pegangan Hakmilik</td>
                <td width="1%">:</td>
                <td width="70%"> 

                <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik" onblur="doChangePeganganHakmilikBorangK();"/>
                <input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Borang K" onclick="pilihBorangK('$idKategoriPemohon','$idKementerian' , '99999')"/>

               </td>
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
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> 
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onclick="daftarBaru()"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
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
function pilihTanah(idKategoriPemohon,idKementerianPemohon,idNegeri) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupSenaraiTanahView?idKategoriPemohon="+idKategoriPemohon+"&idKementerianPemohon="+idKementerianPemohon+"&idNegeriPemohon="+idNegeri;
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
function pilihBorangK(idKategoriPemohon,idKementerianPemohon,idNegeri) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupSenaraiBorangKView?idKategoriPemohon="+idKategoriPemohon+"&idKementerianPemohon="+idKementerianPemohon+"&idNegeriPemohon="+idNegeri;
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
	if(document.${formName}.socKategoriPemohon.value == "1" || document.${formName}.socKategoriPemohon.value == "2" || document.${formName}.socKategoriPemohon.value == "6" || document.${formName}.socKategoriPemohon.value == "7"){
		if(document.${formName}.txtNama.value == ""){
			alert('Sila masukan Nama.');
  			document.${formName}.txtNama.focus(); 
			return; 
		}		
		if(document.${formName}.txtAlamat1.value == ""){
			alert('Sila masukan Alamat.');
  			document.${formName}.txtAlamat1.focus(); 
			return; 
		}
		if(document.${formName}.txtPoskod.value == ""){
			alert('Sila masukan Poskod.');
  			document.${formName}.txtPoskod.focus(); 
			return; 
		}
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila masukan Negeri.');
  			document.${formName}.socNegeri.focus(); 
			return; 
		}		
		if(document.${formName}.socBandar.value == ""){
			alert('Sila masukan Bandar.');
  			document.${formName}.socBandar.focus(); 
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
		if(document.${formName}.idPPTBorangK.value == "" && document.${formName}.idHakmilikUrusan.value == "" && document.${formName}.idPHPBorangK.value == ""){
			alert('Sila pilih Pegangan Hakmilik.');
			return; 
		}
	} else {		
		if(document.${formName}.idHakmilikAgensi.value == "" && document.${formName}.idHakmilikSementara.value == ""){
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
		document.${formName}.actionPelepasan.value = "daftarBaru";
		return;
	}
	
	document.${formName}.actionPelepasan.value = "papar";
	document.${formName}.hitButton.value = "daftarBaru";
	doAjaxCall${formName}("");
}
function kembali() {	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPLPSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.actionPelepasan.value = "";
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
function seterusnya(){
	document.${formName}.action = "$EkptgUtil.getTabID("Pelepasan",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPMaklumatPermohonanView";
	document.${formName}.submit();
}
function janaTajuk() {
	
	if(document.${formName}.idHakmilikAgensi.value == "" && document.${formName}.idHakmilikSementara.value == ""){
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
	var luasKegunaan  = "";
	
	if(document.${formName}.socLuasKegunaan.value == "1") {
		luasKegunaan = "KESELURUHAN";
	}
	else if(document.${formName}.socLuasKegunaan.value == "2"){
		luasKegunaan = "SEBAHAGIAN";
	}

	var str2  = document.${formName}.noLotTanah.value;
	var str3  = document.${formName}.noMilikTanah.value;
	var noWarta  = document.${formName}.noWartaTanah.value;
	var str4  = document.${formName}.namaMukimTanah.value;
	var str5  = document.${formName}.namaDerahTanah.value;	
	var str6  = document.${formName}.namaNegeriTanah.value;
	var kegunaanTanah = document.${formName}.kegunaanTanah.value;
	var tujuanKegunaan = document.${formName}.txtTujuanKegunaan.value;
	
	
	if(document.${formName}.statusRizab.value == "MILIK"){
		strTajuk = "PERMOHONAN PENYERAHAN BALIK " + luasKegunaan +" TANAH MILIK PERSEKUTUAN " + str2 +", " + str3 + ", "+ str4 + ", " + str5 + " " + str6 +" (" + kegunaanTanah + ")" +" BAGI TUJUAN " + tujuanKegunaan ;
	} else if(document.${formName}.statusRizab.value == "RIZAB"){
		if(document.${formName}.socLuasKegunaan.value == "1"){
			strTajuk = "PERMOHONAN PELEPASAN "+ luasKegunaan +" TANAH RIZAB PERSEKUTUAN " +  str2 +" , " + noWarta +", " + str4 + ", "+ str5 + ", " + str6  +" (" + kegunaanTanah + ")" +" BAGI TUJUAN " + tujuanKegunaan ;
		} else if(document.${formName}.socLuasKegunaan.value == "2"){
			strTajuk = "PERMOHONAN PELEPASAN "+ luasKegunaan +" TANAH RIZAB PERSEKUTUAN " +  str2 +" , " + noWarta +", " + str4 + ", "+ str5 + ", " + str6  +" (" + kegunaanTanah + ")" +" BAGI TUJUAN " + tujuanKegunaan;		
		}
	} 
	document.${formName}.txtPerkara.value = strTajuk;
}
</script>
