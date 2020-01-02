
<style type="text/css">
<!--
.style1 {color: #0033FF}
.style40 {color: #FF0000}
.style41 {color: #000000}
.style42 {color: #0000FF}
-->
</style>

<input name="idFail" type="hidden" value="$idFail" />
<input name="emel" type="hidden" value="$emel" />
<input name="idMinitArahanSebelum" type="hidden" value="$idMinitArahanSebelum" />
<input name="idMinitarahan" type="hidden" value="$id_Minitarahan" />
<input name="idLampiran" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
<input name="idDokumen" type="hidden" value="$idDokumen"/>
<input name="idSeksyen" type="hidden" value="$idSeksyen"/>
<input name="idNegeri" type="hidden" value="$idNegeri"/>
<input name="pengirim_Dokumen" type="hidden" value="$pengirim_Dokumen"/>
<input name="paparLampiranDokumenMasuk_size" type="hidden" value="$!paparLampiranDokumenMasuk_size"/>
&nbsp;
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<table width="100%">

 
   
  <tr>
    <td width="29%" scope="row">NO FAIL</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <span class="style42">
      <label>$noFail.toUpperCase()</label>
      </span> </td>
  </tr>
  <tr>
    <td scope="row">TAJUK FAIL</td>
    <td scope="row"><div align="right">:</div></td>
    <td class="style42">$tajukFail.toUpperCase()</td>
  </tr>
  <tr>
    <td scope="row">STATUS FAIL</td>
    <td scope="row"><div align="right">:</div></td>
    <td class="style42">$keterangan.toUpperCase()</td>
  </tr>
  <tr>
    <td scope="row">TARIKH DAFTAR FAIL</td>
    <td scope="row"><div align="right">:</div></td>
    <td class="style42">$tarikh_Daftar_Fail.toUpperCase()</td>
  </tr>
</table>
</fieldset>


#if($action1 == 'tambahPeg3Masuk')
<fieldset>
<legend>MAKLUMAT AGIHAN TUGASAN</legend>
 #if($emel == 'success')
  	<div class="success">Telah di emel ke pegawai yang berkenaan.</div>
 #end
<table width="100%">
  <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td scope="row" valign="top">&nbsp;</td>
    <td><input type="hidden" name="levelArahan" id="levelArahan" value="$!levelArahan"/></td>
  </tr>
  <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td scope="row" valign="top">:</td>
    <td><span class="style41">$!no_Rujukan_Dokumen</span></td>
  </tr>
  <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td scope="row" valign="top">:</td>
    <td><span class="style41">$!tajuk_Dokumen</span></td>    
  </tr>
    <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td align="left" valign="top" scope="row">Daripada</td>
    <td scope="row" valign="top">:</td>
    <td><span class="style41">$!pengirim_Dokumen</span></td>   
  </tr>
  #if($paparLampiranDokumenMasuk_size == '0')
  	 <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><span class="style40"><em>Tiada lampiran di masukkan dalam sistem eTapp, dokumen fizikal akan dihantar ke pegawai yang berkenaan.</em></span></td>
  </tr>
  #else
  	 <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
    #foreach($paparLampiranDokumenMasuk in $SenaraiListLampiranDokumenMasuk)
      <table width="100%" border="0">
      <tr>
        <td>$paparLampiranDokumenMasuk.bil - <a href="javascript:papar_Lampiran('$paparLampiranDokumenMasuk.id_lampiran')" class="style1">$paparLampiranDokumenMasuk.nama_fail</a></td>
      </tr>
    </table>
      #end</td>
  </tr>
  #end
   #if($action1 == 'tambahPeg3Masuk')
  <tr>
    <td scope="row" valign="top"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Status Tindakan</td>
    <td scope="row" valign="top">:</td>
    <td> <select name="socStatusTindakan" id="socStatusTindakan" onchange="doChangeStatusTindakan()" $readOnlyMinit  $disabledMinit />
        
		#if ($status_Tindakan == '1')
        
        <option value="0">SILA PILIH</option>
        <option value="1" selected="selected">TUGASAN TELAH DIAGIHKAN</option>
        <option value="2">TELAH DIAMBIL TINDAKAN</option>
        <option value="3">SUDAH MAKLUM</option>
        <option value="99">TUGASAN SELESAI</option>

        #elseif ($status_Tindakan == '2')
        
        <option value="0">SILA PILIH</option>
        <option value="1">TUGASAN TELAH DIAGIHKAN</option>
        <option value="2" selected="selected">TELAH DIAMBIL TINDAKAN</option>
        <option value="3">SUDAH MAKLUM</option>
        <option value="99">TUGASAN SELESAI</option>
        
        #elseif ($status_Tindakan == '3')
        
        <option value="0">SILA PILIH</option>
        <option value="1">TUGASAN TELAH DIAGIHKAN</option>
        <option value="2">TELAH DIAMBIL TINDAKAN</option>
        <option value="3" selected="selected">SUDAH MAKLUM</option>
        <option value="99">TUGASAN SELESAI</option>
        
        #elseif ($status_Tindakan == '99')
        
        <option value="0">SILA PILIH</option>
        <option value="1">TUGASAN TELAH DIAGIHKAN</option>
        <option value="2">TELAH DIAMBIL TINDAKAN</option>
        <option value="3">SUDAH MAKLUM</option>
        <option value="99" selected="selected">TUGASAN SELESAI</option>
        
        #else
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">TUGASAN TELAH DIAGIHKAN</option>
        <option value="2">TELAH DIAMBIL TINDAKAN</option>
        <option value="3">SUDAH MAKLUM</option>
        <option value="99">TUGASAN SELESAI</option>
        
        #end   
      
      </select></td>
  </tr>
  #end
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai Yang Memberi Arahan</td>
    <td scope="row" valign="top">:</td>
    <td>$user_Name
    <input name="user_Id" type="hidden" value="$user_Id" /></td>
  </tr>
 #if($modeStatusTindakan != '1')
  <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <input name="idMinitArahan2" type="hidden" value="$minit.id_Minitarahan" />
    <td width="27%" align="left" valign="top" scope="row">Arahan</td>
    <td width="1%" scope="row" valign="top">:</td>
    <td width="70%"><textarea name="txtMinitArahan" cols="70" rows="6" id="txtMinitArahan" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readOnlyMinit  $disabledMinit>$!minit_Arahan</textarea></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai Pertama Yang Menerima Arahan</td>
    <td valign="top" scope="row">:</td>
    <td>
    <select name="socPegawai1" id="socPegawai1" readOnlyMinit $disabledMinit>
    			#if ($modeMinit == 'newMinit' || $modeMinit == 'kemaskiniMinit')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPegawai1 in $SenaraiPegawai1)
                  	#if($selectidorang1 == $listPegawai1.user_id)
                    <option value="$listPegawai1.user_id" selected="selected">$listPegawai1.user_name</option>
                    #else
                    <option value="$listPegawai1.user_id" >$listPegawai1.user_name</option>
                 	 #end
                  #end
        </select></td>
  </tr>
    <tr>
      <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Pegawai ke-2 Yang Menerima Arahan</td>
    <td valign="top" scope="row">:</td>
    <td>
    <select name="socPegawai2" id="socPegawai2" readOnlyMinit $disabledMinit>
    			#if ($modeMinit == 'newMinit' || $modeMinit == 'kemaskiniMinit')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPegawai2 in $SenaraiPegawai2)
                  	#if($selectidorang2 == $listPegawai2.user_id)
                    <option value="$listPegawai2.user_id" selected="selected">$listPegawai2.user_name</option>
                    #else
                    <option value="$listPegawai2.user_id" >$listPegawai2.user_name</option>
                 	 #end
                  #end
        </select></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Pegawai ke-3 Yang Menerima Arahan</td>
    <td valign="top" scope="row">:</td>
    <td>
    <select name="socPegawai3" id="socPegawai3" readOnlyMinit $disabledMinit>
    			#if ($modeMinit == 'newMinit' || $modeMinit == 'kemaskiniMinit')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPegawai3 in $SenaraiPegawai3)
                  	#if($selectidorang3 == $listPegawai3.user_id)
                    <option value="$listPegawai3.user_id" selected="selected">$listPegawai3.user_name</option>
                    #else
                    <option value="$listPegawai3.user_id" >$listPegawai3.user_name</option>
                 	 #end
                  #end
        </select>
    <span class="style40"><em>* Pegawai Kedua dan Ketiga tidak &quot;mandatory&quot;</em></span></td>
  </tr>
  #end
   <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh</td>
    <td valign="top" scope="row">:</td>
    <td><label>
      <input name="txdTarikhMinitArahan" type="text" id="txdTarikhMinitArahan" value="$!tarikh_Minit_Arahan" size="10" $readOnlyMinit $disabledMinit/>
    </label>
      <a href="javascript:displayDatePicker('txdTarikhMinitArahan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Catatan</td>
    <td valign="top" scope="row">:</td>
    <td><textarea name="txtCatatan" cols="70" rows="6" id="txtCatatan" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readOnlyMinit   $disabledMinit>$catatan</textarea></td>
  </tr>
  <tr>
    <td colspan="5" scope="row">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="5" align="center" scope="row"> 
    #if ($modeMinit == 'paparMinit') 
       #if($emel == 'notsuccess')
     <img src="../img/emel.gif" title="Pemberitahuan kepada pegawai melalui emel" >
      <input type="button" name="cmdEmel" id="cmdEmel" align="left" value="Emel Minit Arahan" onclick = "emelMinitArahan()" />
      #end
      <input type="button" name="cmdKembali11" id="cmdKembali11" value="Kemaskini" onclick="kemaskiniMinit('$id_Minitarahan','$idDokumen')" />
      <input type="button" name="cmdKembali8" id="cmdKembali8" value="Kembali" onclick="kembaliMinit()" />
      
      #elseif ($modeMinit == 'newMinit')
      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanMinitPeg3('$idMinitArahanSebelum')" />
      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onclick="batalNewMinit()"/>
      <input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembaliMinit()" />
      #elseif ($modeMinit == 'kemaskiniMinit')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick = "updateMinit('$id_Minitarahan','$idDokumen')" />
      <input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembaliMinit()" />
      #else
      #if($emel == 'notsuccess') 
      <img src="../img/emel.gif" title="Pemberitahuan kepada pegawai melalui emel" >
      <input type="button" name="cmdEmel" id="cmdEmel" align="left" value="Emel MinitArahan" onclick = "emelMinitArahan()" />
      #end
      <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembaliMinit()" />
    #end
    </td>
  </tr>
</table>
</br>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="center"><strong> <blink><font color="#FF0000"></strong>*Anda perlu menekan <em>button</em> Emel Minit Arahan untuk menghantar minit arahan ke pegawai berkenaan, selepas minit arahan disimpan.<blink>    
      <color></td>
  </tr>
</table>
</fieldset>
#end
<script type="text/javascript">
<!--
//-->
function tabDokumenKeluar(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenKeluar";
	document.${formName}.submit();
}
</script>
<script>
function batalView(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.mode.value = "paparDokumen";
	document.${formName}.submit();
}
function batalNewMinit(){

	document.${formName}.reset();
	document.${formName}.socStatusTindakan.value = "0";
	document.${formName}.txtMinitArahan.value = "";
	document.${formName}.socPegawai1.value = "";
	document.${formName}.socPegawai2.value = "";
	document.${formName}.socPegawai3.value = "";
	document.${formName}.txtCatatan.value = "";

	
	return;
	
}

function tambahPeg2(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg2Masuk&idDokumen="+document.${formName}.idDokumen.value+"&statusTindakan="+document.${formName}.socStatusTindakan.value+"&minitArahan="+document.${formName}.txtMinitArahan.value+"&tarikh="+document.${formName}.txdTarikhMinitArahan.value+"&catatan="+document.${formName}.txtCatatan.value+"&level="+document.${formName}.levelArahan.value+"&idSeksyen="+document.${formName}.idSeksyen.value+"&idNegeri="+document.${formName}.idNegeri.value;
	document.${formName}.submit();
}
function tambahPeg3(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg3Masuk&idDokumen="+document.${formName}.idDokumen.value+"&statusTindakan="+document.${formName}.socStatusTindakan.value+"&minitArahan="+document.${formName}.txtMinitArahan.value+"&tarikh="+document.${formName}.txdTarikhMinitArahan.value+"&catatan="+document.${formName}.txtCatatan.value+"&level="+document.${formName}.levelArahan.value+"&idSeksyen="+document.${formName}.idSeksyen.value+"&idNegeri="+document.${formName}.idNegeri.value;
	document.${formName}.submit();
}
//function simpanMinit(){
//	if ('$status_Tindakan' == 4){
//	
//	}
//	else{
//		if (document.${formName}.socStatusTindakan.value == ""){
//			alert('Sila masukkan " Status tindakan" terlebih dahulu.');
//			document.${formName}.socStatusTindakan.focus();
//			return;
//		}
//	
//	
//		if (document.${formName}.txtMinitArahan.value == ""){
//			alert('Sila masukkan " Tindakan " terlebih dahulu.');
//			document.${formName}.txtMinitArahan.focus();
//			return;
//		}
//		
//		if (document.${formName}.socPegawai1.value == ""){
//			alert('Sila masukkan " Nama Pegawai Pertama" terlebih dahulu.');
//			document.${formName}.socPegawai1.focus();
//			return;
//		}
//	}
//	if ( !window.confirm("Adakah Anda Pasti?")) return;
//	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanMinitMasuk";
//	document.${formName}.submit();
//	
//}
//function simpanMinitPeg2(){
//	if ('$status_Tindakan' == 4){
//	
//	}
//	else{
//		if (document.${formName}.socStatusTindakan.value == ""){
//			alert('Sila masukkan " Status tindakan" terlebih dahulu.');
//			document.${formName}.socStatusTindakan.focus();
//			return;
//		}
//	
//	
//		if (document.${formName}.txtMinitArahan.value == ""){
//			alert('Sila masukkan " Tindakan " terlebih dahulu.');
//			document.${formName}.txtMinitArahan.focus();
//			return;
//		}
//		
//		if (document.${formName}.socPegawai1.value == ""){
//			alert('Sila masukkan " Nama Pegawai Pertama" terlebih dahulu.');
//			document.${formName}.socPegawai1.focus();
//			return;
//		}
//	}
//	if ( !window.confirm("Adakah Anda Pasti?")) return;
//	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanMinitMasukPeg2";
//	document.${formName}.submit();
//	
//}
function simpanMinitPeg3(id){
	if ('$status_Tindakan' == 99){
	
	}
	else{	
	
		if (document.${formName}.socStatusTindakan.value == "0"){
			alert('Sila masukkan " Status Tindakan " terlebih dahulu.');
			document.${formName}.socStatusTindakan.focus();
			return;
		}
	
		if (document.${formName}.txtMinitArahan.value == ""){
			alert('Sila masukkan " Arahan " terlebih dahulu.');
			document.${formName}.txtMinitArahan.focus();
			return;
		}
	
		if (document.${formName}.socPegawai1.value == ""){
			alert('Sila masukkan " Nama Pegawai Pertama" terlebih dahulu.');
			document.${formName}.socPegawai1.focus();
			return;
		}
	
		if (document.${formName}.txdTarikhMinitArahan.value == ""){
			alert('Sila masukkan " Tarikh Minit Arahan " terlebih dahulu.');
			document.${formName}.txdTarikhMinitArahan.focus();
			return;
		}
		

	}
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanMinitMasukPeg3&idMinitArahanSebelum="+id;
	document.${formName}.submit();
	
}
function kemaskiniMinit(id_Minitarahan, idDokumen){
	document.${formName}.idMinitarahan.value = id_Minitarahan;
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.action ="?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=kemaskiniMinitMasuk";
	document.${formName}.submit();
}
function updateMinit(id_Minitarahan,idDokumen){
	document.${formName}.idMinitarahan.value = id_Minitarahan;
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=updateMinitMasuk";
	document.${formName}.submit();
}
function doChangeStatusTindakan() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahMinitPage&statusTindakan="+document.${formName}.socStatusTindakan.value;
	document.${formName}.submit();
}
function kembaliMinit(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar&paparArahan=true";
	document.${formName}.submit();
}
function emelMinitArahan(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=emelMinitArahan";
	document.${formName}.submit();
}
function papar_Lampiran(id_lampiran) {
    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id_lampiran;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>
