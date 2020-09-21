<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
#set($saizPerkara="1000")

<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idUrusan" type="hidden" id="idUrusan" value="$idUrusan"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
  <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" />
  <input name="noPermohonanLama" type="hidden" id="noPermohonanLama" value="$noPermohonanLama"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
          <input name="noFailLama" type="hidden" id="noFailLama" value="$beanMaklumatPermohonan.noFailLama"/>
          <input name="idPermohonanLama" type="hidden" id="idPermohonanLama" value="$beanMaklumatPermohonan.idPermohonanLama"/>
          <input name="noPermohonanLama" type="hidden" id="noPermohonanLama" value="$beanMaklumatPermohonan.noPermohonanLama"/>
        
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Fail</td>
          <td width="1%" >:</td>
          <td width="70%"><strong>$beanMaklumatPermohonan.noFail</strong></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">Jenis Permohonan</td>
          <td width="1%" >:</td>
          #if ($beanMaklumatPermohonan.jenisPermohonan == '1')
          <td width="70%"><strong>PERMOHONAN LESEN</strong></td>
          #elseif ($beanMaklumatPermohonan.jenisPermohonan == '2')
          <td width="70%"><strong>PEMBAHARUAN LESEN</strong></td>
          #end

        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Rujukan <i>Online</i></td>
          <td width="1%" >:</td>
          <td width="70%">$beanMaklumatPermohonan.noPermohonan</td>
        </tr>
        #if ($beanMaklumatPermohonan.jenisPermohonan == '2')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">No. Permohonan Lama</td>
          <td width="1%" >:</td>
          <td width="70%"><strong>$beanMaklumatPermohonan.noPermohonanLama</strong></td>    
        </tr>
        #end
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Urusan</td>
          <td>:</td>
          <td>AKTA PELANTAR BENUA
            </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Tarikh Terima</td>
          <td>:</td>
          <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" readonly="readonly" class="disabled" size="11"> </td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizPerkara);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizPerkara);" >$beanMaklumatPermohonan.perkara</textarea>
          #if ($mode != 'view')<input type="button" name="cmdJanaTajuk" id="cmdJanaTajuk" value="Jana Tajuk" onclick="janaTajuk()"/>#end</td>
        </tr>
         #if ($mode != 'view')
          <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>Baki Aksara :&nbsp;
              <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizPerkara" /></td>
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
        #foreach ($beanMaklumatPemohon in $BeanMaklumatPemohon)
        <tr>
          <td width="1%"></td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">$selectKategoriPemohon</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Nama</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.nama
            <input type="hidden" name="namaPemohon" id="namaPemohon" value="$beanMaklumatPemohon.nama " /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Pengenalan / Pendaftaran</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noPengenalan</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.alamat1 </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>$beanMaklumatPemohon.alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>$beanMaklumatPemohon.alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.poskod </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td></td>
          <td>Bandar</td>
          <td>:</td>
          <td>$selectBandar</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>E-mel</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.emel </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Tel</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noTel</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Faks</td>
          <td>:</td>
          <td>$beanMaklumatPemohon.noFax </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"></td>
  </tr>
   #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> 
      #if ($mode != 'view')
	      #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
	      	#if ($beanMaklumatPermohonan.jenisPermohonan == 1)
	      		<input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onClick="daftarBaru()"/>
	      	#elseif ($beanMaklumatPermohonan.jenisPermohonan == 2)
	      		<input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar Sambung" onClick="daftarSambung()"/>
	      	#end
	      #end
       <input type="button" name="cmdKembali" id="cmdKembali" value="Batal" onClick="kembali()"/>
      #else
       <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>
      #end
       </td>
  </tr>
</table>
<script>
function daftarBaru() {

	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionOnline.value = "daftar";
		return;
	}
	
	document.${formName}.actionOnline.value = "papar";
	document.${formName}.hitButton.value = "daftarBaru";
	document.${formName}.submit();
}

function daftarSambung() {

	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionOnline.value = "daftar";
		return;
	}
	
	document.${formName}.actionOnline.value = "papar";
	document.${formName}.hitButton.value = "daftarSambung";
	document.${formName}.submit();
}

function kembali() {	
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}
function janaTajuk(){	
	var strTajuk = "Permohonan Untuk Mendapatkan Lesen Bagi Mengeluarkan Pasir Dasar Laut Di Bawah Seksyen 4, Akta Pelantar Benua 1966 P.U 2009 di Kawasan Luar Perairan ";
	document.${formName}.txtPerkara.value = strTajuk;
}

function generateNoFailAPB(){	
	document.${formName}.hitButton.value = "generateNoFailAPBOnline";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
</script>
