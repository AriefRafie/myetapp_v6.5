
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
<input name="idMinitArahanSeksyenLain" type="hidden" value="$idMinitArahanSeksyenLain" />
<input name="idLampiran" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
<input name="idDokumen" type="hidden" value="$idDokumen"/>
<input name="idSeksyen" type="hidden" value="$idSeksyen"/>
<input name="idNegeri" type="hidden" value="$idNegeri"/>
<input name="pengirim_Dokumen" type="hidden" value="$pengirim_Dokumen"/>
<input name="paparLampiranDokumenMasuk_size" type="hidden" value="$!paparLampiranDokumenMasuk_size"/>
&nbsp;


<table width="100%">
  <tr>
    <td>
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
</td>
  </tr>
  <tr>
    <td>
    
    <fieldset>
<legend>MAKLUMAT HANTAR KE SEKSYEN LAIN</legend>
<table width="100%">
   <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td scope="row" valign="top">&nbsp;</td>
    <td><input type="hidden" name="levelArahan" id="levelArahan" value="$!levelArahan"/></td>
  </tr>
   <tr>
     <td width="2%" valign="top" scope="row">&nbsp;</td>
     <td width="27%" align="left" valign="top" scope="row">No Rujukan Lain</td>
     <td width="1%" scope="row" valign="top">:</td>
     <td width="70%"><span class="style41">$!no_Rujukan_Lain
         <input name="no_Rujukan_Lain" type="hidden" value="$no_Rujukan_Lain" />
     </span></td>
     
   </tr>
   <tr>
     <td scope="row" valign="top">&nbsp;</td>
     <td align="left" valign="top" scope="row">Tajuk Dokumen</td>
     <td scope="row" valign="top">:</td>
     <td><span class="style41">$!tajuk_Dokumen
         <input name="tajuk_Dokumen" type="hidden" value="$tajuk_Dokumen" /></span></td>
     
  </tr>
   <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td align="left" valign="top" scope="row">Daripada</td>
    <td scope="row" valign="top">:</td>
    <td><span class="style41">$!user_Name</span>
      <input name="user_Id" type="hidden" value="$user_Id" /></td>   
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
    #if($modeMinit == 'paparMinit')
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai Yang Memberi Arahan</td>
    <td valign="top" scope="row">:</td>
    <td><input name="pengarah" type="text" value="$!pengarah" size="30" $readOnlyMinit $disabledMinit/></td>
    
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Seksyen</td>
    <td valign="top" scope="row">:</td>
    <td>$selectSeksyen</td>
   </tr>
  <tr>
     <td scope="row"><div align="center"></div></td>
     <td scope="row"><div align="left">Nama PAR</div></td>
     <td width="1%" valign="top" scope="row"><div align="right">:</div></td>
     <td><input name="penerima" type="text" value="$!penerima" size="30" $readOnlyMinit $disabledMinit/></td>
  </tr>
  #elseif($modeMinit == 'newMinit' || $modeMinit == 'kemaskiniMinit' )
   <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai Yang Memberi Arahan</td>
    <td valign="top" scope="row">:</td>
    <td>$user_Name<input name="user_Id" type="hidden" value="$user_Id" /></td>
    
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Seksyen</td>
    <td valign="top" scope="row">:</td>
    <td>$selectSeksyenPAR</td>
   </tr>
                #if($getPARbyIdSeksyen_size > 0)
            <tr>
              <td scope="row"><div align="center"></div></td>
              <td scope="row"><div align="left">Nama PAR</div></td>
              <td width="1%" valign="top" scope="row"><div align="right">:</div></td>
              <td>$selectPAR</td>
            </tr>
            #else
            <tr>
              <td scope="row"><div align="center"></div></td>
              <td scope="row"><div align="left">Nama PAR</div></td>
              <td width="1%" valign="top" scope="row"><div align="right">:</div></td>
              <td>
                <select name="socPAR" disabled="disabled">
                  <option value="">SILA PILIH</option>
                </select>              </td>
            </tr>
            #end
  #end
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Catatan</td>
    <td valign="top" scope="row">:</td>
    <td><textarea name="txtCatatan" cols="70" rows="6" id="txtCatatan" onkeyup="this.value=this.value.toUpperCase()" $readOnlyMinit   $disabledMinit>$catatan</textarea></td>
    
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Makluman</td>
    <td valign="top" scope="row">:</td>
    <td><label>
      <input name="txdTarikhMinitArahan" type="text" id="txdTarikhMinitArahan" value="$tarikh_Minit_Arahan" size="10" $readOnlyMinit $disabledMinit/>
    </label>
      <a href="javascript:displayDatePicker('txdTarikhMinitArahan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
    
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
    
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
   <td valign="top" scope="row">&nbsp;</td>
    <td><label></label></td>
    
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
      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanSeksyenLain()" />
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
</fieldset>
</td>
  </tr>
</table>


<script>
function kembaliMinit(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar&paparArahan=true";
	document.${formName}.submit();
}
function doChangeSeksyenPAR() {
	//alert(document.${formName}.socSeksyenPAR.value);
	document.${formName}.action.value = "tambahMinitSeksyenLain";
 	doAjaxCall${formName}("doChangeSeksyenPAR","action1=tambahMinitSeksyenLain");
}
function simpanSeksyenLain(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanSeksyenLain";
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
