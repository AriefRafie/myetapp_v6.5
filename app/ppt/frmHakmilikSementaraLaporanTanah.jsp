<style type="text/css">
<!--
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>

#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<table width="100%"> 
	<tr>
    	<td>
        <fieldset><legend><strong>Maklumat Hakmilik</strong></legend>
        <table width="100%">
          <tr>
            <td width="18%" align="left">No. Hakmilik</td>
            <td width="1%">:</td>
            <td width="29%"><label>$SelectNoHakmilik</label></td>
            <td width="19%" align="left">Luas Diguna/Disewa</td>
            <td width="1%">:</td>
            <td width="32%"><label>$LUAS_SEWA $UNIT_LUAS_SEWA</label></td>
          </tr>
          <tr>
            <td align="left">No. Lot</td>
            <td>:</td>
            <td><label>$NO_LOT</label></td>
            <td align="left">Kategori Penggunaan Tanah</td>
            <td>:</td>
            <td>$KATEGORI_TANAH</td>
          </tr>
          <tr>
            <td align="left">Luas Lot (Keluasan)</td>
            <td>:</td>
            <td><label>$LUAS_LOT $UNIT_LUAS_LOT</label></td>
            <td align="left">No. Pelan Diperakui</td>
            <td>:</td>
            <td><label>$NO_PELAN</label></td>
          </tr>
          <tr>
            <td align="left">Bandar/Pekan/Mukim</td>
            <td>:</td>
            <td>$NAMA_MUKIM</td>
            <td align="left">No. Lembaran Piawai</td>
            <td>:</td>
            <td><label>$NO_SYIT</label></td>
          </tr>
        </table>
        </fieldset>
        </td>
    </tr>
	<tr>
    	<td>
       	  <fieldset>
          <legend><strong>Maklumat Lawatan Tapak</strong></legend>
            
            <table width="100%">
              <tr>
                <td width="1%" align="left">&nbsp;</td>
                <td width="18%" align="left">Tarikh Mula <em>Charting</em></td>
                <td width="1%">:</td>
                <td width="29%"><label>
                  <input name="txdTkhMulaChart" type="text" id="txdTkhMulaChart" value="$TARIKH_MULA_CHART" size="10" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_mula_chart_check','yes','mula charting','tarikh');"/>
                 #if($readonly != 'readonly') <a href="javascript:displayDatePicker('txdTkhMulaChart',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
                <span class="style52">dd/mm/yyyy</span></label></td>
                <td width="1%" align="left">&nbsp;</td>
                <td width="19%" align="left">Tarikh Lawatan Tapak</td>
                <td width="1%">:</td>
                <td width="32%"><label>
                  <input name="txdTkhLawatTapak" type="text" id="txdTkhLawatTapak" value="$TARIKH_LAWATAN" size="10" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_lawat_tapak_check','yes','lawatan tapak','tarikh');"/>
                #if($readonly != 'readonly')  <a href="javascript:displayDatePicker('txdTkhLawatTapak',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end <span class="style52">dd/mm/yyyy</span>                </label></td>
              </tr>
              <tr>
                <td width="1%" align="left">&nbsp;</td>
                <td align="left">Tarikh Akhir <em>Charting</em></td>
                <td>:</td>
                <td><label>
<input name="txdTkhAkhirChart" type="text" id="txdTkhAkhirChart" value="$TARIKH_AKHIR_CHART" size="10" $readonly class="$disabled"/ onblur="checking_validation(this,'tarikh_akhir_chart_check','yes','akhir charting','tarikh');">                 
#if($readonly != 'readonly')   <a href="javascript:displayDatePicker('txdTkhAkhirChart',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
                <span class="style52">dd/mm/yyyy</span></label></td>
                <td width="1%" align="left">&nbsp;</td>
                <td align="left">Nama Pegawai</td>
                <td>:</td>
                <td><label>
                  <input name="txtNamaPegawai" type="text" id="txtNamaPegawai" value="$NAMA_PEGAWAI" readonly="readonly" class="$disabled"/>
                </label></td>
              </tr>
              <tr>
                <td align="left">&nbsp;</td>
                <td align="left">&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td align="left">&nbsp;</td>
                <td align="left">Tarikh Laporan</td>
                <td>:</td>
                <td><input name="txdTarikhLaporan" type="text" id="txdTarikhLaporan" value="$TARIKH_LAPOR" size="10" $readonly class="$disabled" onblur="checking_validation(this,'tarikh_laporan_check','yes','laporan','tarikh');"/>
                #if($readonly != 'readonly') <a href="javascript:displayDatePicker('txdTarikhLaporan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end <span class="style52">dd/mm/yyyy</span> </td>
              </tr>
          </table>
          </fieldset>
        </td>
    </tr>
    <tr>
   	  <td width="100%">
   	    <fieldset><legend><strong>Butiran-Butiran Tanah</strong></legend>
            <table width="100%">
              <tr>
                <td width="1%" align="left">&nbsp;</td>
                <td width="28%" align="left">Bil. Syit Piawaian</td>
                <td width="1%">:</td>
                <td width="70%">
                  <input name="txtBilSyitPiawai" type="text" id="txtBilSyitPiawai" value="$PERIHAL_SYIT" $readonly class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();"/>               </td>
              </tr>
              <tr>
                <td width="1%" align="left">&nbsp;</td>
                <td width="28%" align="left">Jenis Tanah</td>
                <td>:</td>
                <td width="70%">
                  #if($FLAG_JENIS_TANAH == '1')
                    <input type="radio" name="sorJenisTanah" id="sorTanahDesa" value="1" checked="checked"  $readonly class="$disabled" $disabled>  Tanah Desa
                  #else
                    <input type="radio" name="sorJenisTanah" id="sorTanahDesa" value="1" $readonly class="$disabled" $disabled>Tanah Desa
                  #end    </td>
              </tr>
              <tr>
                <td width="1%" align="left">&nbsp;</td>
                <td width="28%" align="left">&nbsp;</td>
                <td>&nbsp;</td>
                <td width="70%">
                 #if($FLAG_JENIS_TANAH == '2')
                  <input name="sorJenisTanah" type="radio" id="sorTanahPekan" value="2" checked="checked" $readonly class="$disabled" $disabled> Tanah Pekan
                 #else
                  <input name="sorJenisTanah" type="radio" id="sorTanahPekan" value="2" $readonly class="$disabled" $disabled>Tanah Pekan
                 #end    </td>
              </tr>
              <tr>
                <td width="1%" align="left">&nbsp;</td>
                <td width="28%" align="left">&nbsp;</td>
                <td>&nbsp;</td>
                <td width="70%">
                #if($FLAG_JENIS_TANAH == '3')
                  <input name="sorJenisTanah" type="radio" id="sorTanahBandar" value="3" checked="checked" $readonly class="$disabled" $disabled>Tanah Bandar
                #else
                  <input name="sorJenisTanah" type="radio" id="sorTanahBandar" value="3" $readonly class="$disabled" $disabled>Tanah Bandar
                #end    </td>
              </tr>
              <tr>
                <td width="1%" align="left" valign="top">&nbsp;</td>
                <td width="28%" align="left" valign="top">Lokasi Tanah</td>
                <td valign="top">:</td>
                <td width="70%">
                  <textarea name="txtLokasiTnh" id="txtLokasiTnh" cols="35" rows="5" $readonly class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$LOKASI_TANAH</textarea>               </td>
              </tr>
              <tr>
                <td align="left" valign="top">&nbsp;</td>
                <td width="28%" align="left" valign="top">Kedudukan Tanah</td>
                <td valign="top">:</td>
                <td width="70%">#if($FLAG_LUAR_SIMPANAN == '1')
                  <input name="sbcLuarKwsnSmpnMelayu" type="checkbox" id="sbcLuarKwsnSmpnMelayu" value="1" checked="checked" $readonly class="$disabled" $disabled />
Di Luar Kawasan Simpanan Melayu
                #else
<input name="sbcLuarKwsnSmpnMelayu" type="checkbox" id="sbcLuarKwsnSmpnMelayu" value="1" $readonly class="$disabled" $disabled />
Di Luar Kawasan Simpanan Melayu
                #end </td>
              </tr>
              <tr>
                <td align="left" valign="top">&nbsp;</td>
                <td width="28%" align="left" valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td width="70%">#if($FLAG_DLM_SIMPANAN == '1')
                  <input name="sbcDlmKwsnSmpnMelayu" type="checkbox" id="sbcDlmKwsnSmpnMelayu" value="1" checked="checked" $readonly class="$disabled" $disabled />
Di Dalam Kawasan Simpanan Melayu
                #else
<input type="checkbox" name="sbcDlmKwsnSmpnMelayu" id="sbcDlmKwsnSmpnMelayu" value="1" $readonly class="$disabled" $disabled />
Di Dalam Kawasan Simpanan Melayu
                #end </td>
              </tr>
              <tr>
                <td align="left" valign="top">&nbsp;</td>
                <td width="28%" align="left" valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td width="70%">#if($FLAG_LUAR_MAJLIS == '1')
                  <input name="sbcLuarKwsnMajlisDaerah" type="checkbox" id="sbcLuarKwsnMajlisDaerah" value="1" checked="checked" $readonly class="$disabled" $disabled />
Di Luar Kawasan Majlis Daerah
                #else
<input name="sbcLuarKwsnMajlisDaerah" type="checkbox" id="sbcLuarKwsnMajlisDaerah" value="1" $readonly class="$disabled" $disabled />
Di Luar Kawasan Majlis Daerah
                #end </td>
              </tr>
              <tr>
                <td align="left" valign="top">&nbsp;</td>
                <td width="28%" align="left" valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td width="70%">#if($FLAG_DLM_MAJLIS == '1')
                  <input name="sbcDlmKwsnMajlisDaerah" type="checkbox" id="sbcDlmKwsnMajlisDaerah" value="1" checked="checked" $readonly class="$disabled" $disabled />
Di Dalam Kawasan Majlis Daerah
                #else
<input name="sbcDlmKwsnMajlisDaerah" type="checkbox" id="sbcDlmKwsnMajlisDaerah" value="1" $readonly class="$disabled" $disabled />
Di Dalam Kawasan Majlis Daerah
                #end </td>
              </tr>
            </table>
        </fieldset>
        </td>
    </tr>
    <tr>
    	<td>
       	  <fieldset><legend><strong>Keadaan Rupabumi</strong></legend>
            <table width="100%">
              <tr>
                <td width="1%" align="left" valign="top">&nbsp;</td>
                <td width="18%" align="left" valign="top">Keseluruhan Lot</td>
                <td width="1%" valign="top">:</td>
                <td width="29%">
                  <textarea name="txtRupabumiSeluruhLot" id="txtRupabumiSeluruhLot" cols="35" rows="5" $readonly class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$RUPABUMI_SELURUH_LOT</textarea>                </td>
                <td width="1%" align="left" valign="top">&nbsp;</td>
                <td width="19%" align="left" valign="top">Kawasan Yang Terlibat</td>
                <td width="1%" valign="top">:</td>
                <td width="32%">
                  <textarea name="txtRupabumiKwsTerlibat" id="txtRupabumiKwsTerlibat" cols="35" rows="5" $readonly class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$RUPABUMI_KWSN_TERLIBAT</textarea>                </td>
              </tr>
          </table>
          </fieldset>
        </td>
    </tr>
    <tr>
    	<td>
       	  <fieldset><legend><strong>Keadaan Lot</strong></legend>
            <table width="100%">
              <tr>
                <td width="1%" align="left" valign="top">&nbsp;</td>
                <td width="18%" align="left" valign="top">Keseluruhan Lot</td>
                <td width="1%" valign="top">:</td>
                <td width="29%">
                  <textarea name="txtKeadaanLotSeluruh" id="txtKeadaanLotSeluruh" cols="35" rows="5" $readonly class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$LOT_SELURUH_LOT</textarea>               </td>
                <td width="1%" align="left" valign="top">&nbsp;</td>
                <td width="19%" align="left" valign="top">Keadaan Tanaman/Umur</td>
                <td width="1%" valign="top">:</td>
                <td width="32%">
                  <textarea name="txtKeadaanLotTanaman" id="txtKeadaanLotTanaman" cols="35" rows="5" $readonly class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$LOT_KEADAAN_TANAMAN</textarea>                </td>
              </tr>
              <tr>
                <td width="1%" align="left" valign="top">&nbsp;</td>
                <td align="left" valign="top">Jenis Tanaman</td>
                <td valign="top">:</td>
                <td>
                  <textarea name="txtKeadaanLotJnsTanaman" id="txtKeadaanLotJnsTanaman" cols="35" rows="5" $readonly class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$LOT_JENIS_TANAMAN</textarea>               </td>
                <td width="1%" align="left" valign="top">&nbsp;</td>
                <td align="left" valign="top">Kawasan Terlibat</td>
                <td valign="top">:</td>
                <td>
                  <textarea name="txtKeadaanLotKwsTerlibat" id="txtKeadaanLotKwsTerlibat" cols="35" rows="5" $readonly class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$LOT_KWSN_TERLIBAT</textarea>                </td>
              </tr>
          </table>
          </fieldset>
        </td>
    </tr>
</table>

<table width="100%">
  <tr align="center">
    <td>  
    #if($mode == 'newLaporan')
    	#if($existLaporan != '0')
  	  	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()"/>
        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')"/>
   	  	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus()" />
    	#else
    	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick = "simpan('$idHakmilik')" />
        <input type="submit" name="cmdBatal" id="cmdBatal" value="Batal" onClick = "batal()" />
        #end
    #end
    #if($mode == 'paparLaporan')
    	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini()"/>
        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
        <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus()"/>
    #end
    #if($mode == 'kemaskiniLaporan')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick = "updateLaporan()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick = "batalTanahUmum()" />
    #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick = "kembali()" /></td>
  </tr>
</table>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" onClick="javascript:cetakLaporanTanah('$idFail','$idHakmilik')"><font color="blue"> Laporan Tanah </font></a></td>
      </tr>           
    </table>
</fieldset>
<input type="hidden" name="id_permohonan" value="$idPermohonan" />
<input type="hidden" name="id_fail" value="$idFail" />
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<input name="idTanahUmum" type="hidden" value="$idTanahUmum" />
<input name="idStatus" type="hidden" value="$idStatus" />
<input name="mode" type="hidden" value="$mode" />
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakLaporanTanah(id_fail,id_hakmilik) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.LaporanTanahSementara?idfail="+id_fail+"&idhakmilik="+id_hakmilik;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function doChangeHakmilik() {
    doAjaxCall${formName}("doChangeHakmilik");
}
function simpan(id_hakmilik){
	/*
	if(document.${formName}.txdTkhMulaChart.value == ""){
		alert("Sila masukkan \"Tarikh Mula Charting\" terlebih dahulu.");
  		document.${formName}.txdTkhMulaChart.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhAkhirChart.value == ""){
		alert("Sila masukkan \"Tarikh Akhir Charting\" terlebih dahulu.");
  		document.${formName}.txdTkhAkhirChart.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhLawatTapak.value == ""){
		alert("Sila masukkan \"Tarikh Lawatan Tapak\" terlebih dahulu.");
  		document.${formName}.txdTkhLawatTapak.focus(); 
		return;
	}
	else if(document.${formName}.txtBilSyitPiawai.value == ""){
		alert("Sila masukkan \"Bil Syit Piawaian\" terlebih dahulu.");
  		document.${formName}.txtBilSyitPiawai.focus(); 
		return;
	}
	else if(document.${formName}.sorJenisTanah.value == ""){
		alert("Sila pilih \"Jenis Tanah\" terlebih dahulu.");
  		document.${formName}.sorJenisTanah.focus(); 
		return;
	}
	else if(document.${formName}.sbcLuarKwsnSmpnMelayu.value == "" || document.${formName}.sbcDlmKwsnSmpnMelayu.value == "" || document.${formName}.sbcLuarKwsnMajlisDaerah.value == "" || document.${formName}.sbcDlmKwsnMajlisDaerah.value == ""){
		alert("Sila pilih \"Kedudukan Tanah\" terlebih dahulu.");
  		document.${formName}.sbcLuarKwsnSmpnMelayu.focus(); 
		return;
	}
	else if(document.${formName}.txtRupabumiSeluruhLot.value == ""){
		alert("Sila masukkan \" Keadaan Rupabumi bagi Keseluruhan Lot\" terlebih dahulu.");
  		document.${formName}.txtRupabumiSeluruhLot.focus(); 
		return;
	}
	else if(document.${formName}.txtRupabumiKwsTerlibat.value == ""){
		alert("Sila masukkan \" Keadaan Rupabumi bagi Kawasan Yang Terlibat \" terlebih dahulu.");
  		document.${formName}.txtRupabumiKwsTerlibat.focus(); 
		return;
	}
	else if(document.${formName}.txtKeadaanLotSeluruh.value == ""){
		alert("Sila masukkan \"Keadaan Lot bagi Keseluruhan Lot\" terlebih dahulu.");
  		document.${formName}.txtKeadaanLotSeluruh.focus(); 
		return;
	}
	else if(document.${formName}.txtKeadaanLotTanaman.value == ""){
		alert("Sila masukkan \"Keadaan Lot bagi Keadaan Tanaman\" terlebih dahulu.");
  		document.${formName}.txtKeadaanLotTanaman.focus(); 
		return;
	}
	else if(document.${formName}.txtKeadaanLotJnsTanaman.value == ""){
		alert("Sila masukkan \"Keadaan Lot bagi Jenis Tanaman\" terlebih dahulu.");
  		document.${formName}.txtKeadaanLotJnsTanaman.focus(); 
		return;
	}
	else if(document.${formName}.txtKeadaanLotKwsTerlibat.value == ""){
		alert("Sila masukkan \"Keadaan Lot bagi Kawasan Terlibat\" terlebih dahulu.");
  		document.${formName}.txtKeadaanLotKwsTerlibat.focus(); 
		return;
	}
	*/
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idHakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraLaporanTanah&action=addLaporanTanah";
	document.${formName}.submit();
}
function kemaskini(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraLaporanTanah&action=kemaskiniLaporanTanah";
	document.${formName}.submit();
}
function updateLaporan(){
/*
	if(document.${formName}.txdTkhMulaChart.value == ""){
		alert("Sila masukkan \"Tarikh Mula Charting\" terlebih dahulu.");
  		document.${formName}.txdTkhMulaChart.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhAkhirChart.value == ""){
		alert("Sila masukkan \"Tarikh Akhir Charting\" terlebih dahulu.");
  		document.${formName}.txdTkhAkhirChart.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhLawatTapak.value == ""){
		alert("Sila masukkan \"Tarikh Lawatan Tapak\" terlebih dahulu.");
  		document.${formName}.txdTkhLawatTapak.focus(); 
		return;
	}
	else if(document.${formName}.txtBilSyitPiawai.value == ""){
		alert("Sila masukkan \"Bil Syit Piawaian\" terlebih dahulu.");
  		document.${formName}.txtBilSyitPiawai.focus(); 
		return;
	}
	else if(document.${formName}.sorJenisTanah.value == ""){
		alert("Sila pilih \"Jenis Tanah\" terlebih dahulu.");
  		document.${formName}.sorJenisTanah.focus(); 
		return;
	}
	else if(document.${formName}.sbcLuarKwsnSmpnMelayu.value == "" || document.${formName}.sbcDlmKwsnSmpnMelayu.value == "" || document.${formName}.sbcLuarKwsnMajlisDaerah.value == "" || document.${formName}.sbcDlmKwsnMajlisDaerah.value == ""){
		alert("Sila pilih \"Kedudukan Tanah\" terlebih dahulu.");
  		document.${formName}.sbcLuarKwsnSmpnMelayu.focus(); 
		return;
	}
	else if(document.${formName}.txtRupabumiSeluruhLot.value == ""){
		alert("Sila masukkan \" Keadaan Rupabumi bagi Keseluruhan Lot\" terlebih dahulu.");
  		document.${formName}.txtRupabumiSeluruhLot.focus(); 
		return;
	}
	else if(document.${formName}.txtRupabumiKwsTerlibat.value == ""){
		alert("Sila masukkan \" Keadaan Rupabumi bagi Kawasan Yang Terlibat \" terlebih dahulu.");
  		document.${formName}.txtRupabumiKwsTerlibat.focus(); 
		return;
	}
	else if(document.${formName}.txtKeadaanLotSeluruh.value == ""){
		alert("Sila masukkan \"Keadaan Lot bagi Keseluruhan Lot\" terlebih dahulu.");
  		document.${formName}.txtKeadaanLotSeluruh.focus(); 
		return;
	}
	else if(document.${formName}.txtKeadaanLotTanaman.value == ""){
		alert("Sila masukkan \"Keadaan Lot bagi Keadaan Tanaman\" terlebih dahulu.");
  		document.${formName}.txtKeadaanLotTanaman.focus(); 
		return;
	}
	else if(document.${formName}.txtKeadaanLotJnsTanaman.value == ""){
		alert("Sila masukkan \"Keadaan Lot bagi Jenis Tanaman\" terlebih dahulu.");
  		document.${formName}.txtKeadaanLotJnsTanaman.focus(); 
		return;
	}
	else if(document.${formName}.txtKeadaanLotKwsTerlibat.value == ""){
		alert("Sila masukkan \"Keadaan Lot bagi Kawasan Terlibat\" terlebih dahulu.");
  		document.${formName}.txtKeadaanLotKwsTerlibat.focus(); 
		return;
	}*/

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraLaporanTanah&action=updateLaporanTanah";
	document.${formName}.submit();
}
function batalTanahUmum(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraLaporanTanah&action=newLaporan";
	document.${formName}.submit();
}
function batal(){
	
	document.${formName}.txdTkhMulaChart.value == "";
	document.${formName}.txdTkhAkhirChart.value == "";
	document.${formName}.txdTkhLawatTapak.value == "";
	document.${formName}.txtBilSyitPiawai.value == "";
	document.${formName}.sorJenisTanah.value == "";
	document.${formName}.sbcLuarKwsnSmpnMelayu.value == "";
	document.${formName}.sbcDlmKwsnSmpnMelayu.value == "";
	document.${formName}.sbcLuarKwsnMajlisDaerah.value == "";
	document.${formName}.sbcDlmKwsnMajlisDaerah.value == "";
	document.${formName}.txtRupabumiSeluruhLot.value == "";
	document.${formName}.txtRupabumiKwsTerlibat.value == "";
	document.${formName}.txtKeadaanLotSeluruh.value == "";
	document.${formName}.txtKeadaanLotTanaman.value == "";
	document.${formName}.txtKeadaanLotJnsTanaman.value == "";
	document.${formName}.txtKeadaanLotKwsTerlibat.value == "";
	return;
}
function kembali(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraLaporanTanah&action=";
	document.${formName}.submit();
}
function hapus(){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraLaporanTanah&action=hapusLaporanTanah";
	document.${formName}.submit();
}
function checking_validation(field,point,mandatory,value_field,jenis_field){	
    	
	var lepas_or_xlepas = 2;	
	
	
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();
		 // DateField.focus();
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	   //alert(lepas_or_xlepas);
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   }
	   else
	   {
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.select();
	//   DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	//   DateField.select();
	//   DateField.focus();
	   
	   }
	   
	   }
	   
	   
	   if(jenis_field == "normal")
	   {
	   
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	
	   }
	   else
	   {
	    document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   	   
	   }
	   
	   
	   
	
	}
</script>