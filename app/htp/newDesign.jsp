<style type="text/css">
<!--
.style1 {color: #0000FF}
.style3 {
	font-size: x-small;
	font-style: italic;
}
.style5 {
	color: #0000FF;
	font-size: x-small;
	font-style: italic;
}
.style6 {font-size: x-small}
-->
</style>
#parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")<br />
<fieldset>
<legend>MAKLUMAT HAKMILIK</legend>
<table width="100%" border="0" align="left">
  <tr>
    <td width="21%"><div align="right">Kementerian</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><span class="style1">$txtNamaKementerian</span></td>
    <td width="15%" ><div align="right">No. Fail KJP</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><span class="style1">$txtFailKJP</span></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Agensi</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><span class="style1">$txtNamaAgensi</span></td>
    <td width="15%"><div align="right">No. Fail PTG</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"> <span class="style1">$txtFailPTG</span></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">No. Fail Seksyen</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><span class="style1">$txtNoFailSeksyen</span></td>
    <td width="15%"><div align="right">No. Fail PTD</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td  width="34%">&nbsp;</td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Tajuk</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><span class="style1">$txtTajuk</span></td>
    <td width="15%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="34%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6"><hr /></td>
  </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font></i> Negeri</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%">$selectNegeriHR</td>
      <td width="15%"><div align="right"><i><font color="#ff0000">*</font> </i>Luas</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><label>
        <input name="txtLuas" type="text" class="$disabled" id="txtLuas" onkeyup="validateNumber(this,this.value);" value="$txtLuas" size="20" $readonly onchange="cal_luas('$socLuas')" onblur="cal_luas('$socLuas')"/>
      </label></td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font></i> Daerah</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%">$selectDaerahHR</td>
      <td width="15%"><div align="right">Luas Bersamaan</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><input name="txtLuasBersamaanH" type="text" id="txtLuasBersamaanH" size="20" readonly="readonly" class="disabled" onkeyup="this.value=this.value.toUpperCase();"/>
      (hektar) </td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font></i> Bandar/Pekan/Mukim</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%">$selectMukimHR</td>
      <td width="15%"><div align="right">No Pelan Akui</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><input name="txtNoPelan" type="text" id="txtNoPelan" value="$txtNoPelan" size="30" $readonly class="$disabled" onkeyup="this.value=this.value.toUpperCase();"/></td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font></i> Jenis Hakmilik</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%">$selectJenisHakmilikHR</td>
      <td width="15%"><div align="right">No. Syit Piawai</div></td>
      <td><div align="center">:</div></td>
      <td width="34%"><input name="txtNoSyit" type="text" id="txtNoSyit" value="$txtNoSyit" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/></td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font></i> No. Hakmilik</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%"><span class="style1">
        <input name="txtNoHakmilik" type="text" class="$disabled" id="txtNoHakmilik" onkeyup="validateNumber(this,this.value);" value="$txtNoHakmilik" size="20" $readonly />
      </span></td>
      <td width="15%"><div align="right">No. PU</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><input name="txtNoPu" type="text" id="txtNoPu" value="$txtNoPu" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/></td>
    </tr>
    <tr>
      <td width="21%"><div align="right">No. Strata</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%"><span class="style1">
        <input name="txtNoStrata" type="text" class="$disabled" id="txtNoStrata" onkeyup="validateNumber(this,this.value);" size="20" $readonly />
      </span></td>
      <td width="15%"><div align="right">Rezab</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><select name="socRizab" id="socRizab" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf()">
          
            
            
            
            
            
            
            
          
          
         #if($socRizab == 'Y')
          
          
          
            
            
            
            
            
            
            
          <option value="">SILA PILIH</option>
            <option value="Y" selected="selected"> Y - YA</option>
            <option value="T"> T - TIDAK</option>
          
            
            
            
            
            
            
            
          
          
         #elseif ($socRizab == 'T') 
            
          
          
            
            
            
            
            
            
            
          <option value="">SILA PILIH</option>
            <option value="Y"> Y - YA</option>
            <option value="T"selected="selected"> T - TIDAK</option>
          
            
            
            
            
            
            
            
          
          
         #else
             
          
          
            
            
            
            
            
            
            
          <option value="" selected="selected">SILA PILIH</option>
            <option value="Y"> Y - YA</option>
            <option value="T"> T - TIDAK</option>
          
            
            
            
            
            
            
            
          
          
         #end
       
        
        
          
          
          
          
          
          
          
        </select></td>
    </tr>
    <tr>
      <td><div align="right"><i><font color="#ff0000">*</font></i> Jenis Lot</div></td>
      <td><div align="center">:</div></td>
      <td>$selectJenisLotHR</td>
      <td width="15%"><div align="right">No. Warta</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><label>
        <input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" $readonly class="$disabled"/>
      </label></td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font></i> No Lot</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%"><span class="style1">
        <input name="txtNoLot" type="text" class="$disabled" id="txtNoLot" onKeyUp="validateNumber(this,this.value);" value="$txtNoLot" size="20" $readonly />
      </span></td>
      <td width="15%"><div align="right">Tarikh Warta</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><input name="txdTarikhWarta" type="text" id="txdTarikhWarta" value="$txdTarikhWarta" size="9" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);"/>
        #if ($mode == 'update') <a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="style5">dd/mm/yyyy </span> #end</a></td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font> </i>Tarikh Terima Hakmilik</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%"><span class="style2">
        <input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="9" $readonly class="$disabled"
        onkeyup="validateNumber(this,this.value);"/>
      </span>#if ($mode == 'update') <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="style1"><span class="style3">dd/mm/yyyy</span>  #end</span></a></td>
      <td width="15%"><div align="right">Jenis Rezab</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><label> $selectRizab</label></td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font> </i>Tarikh Daftar Hakmilik </div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%"><input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="9" $readonly class="$disabled" 
        onkeyup="validateNumber(this,this.value);" />
        #if ($mode == 'update') <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="style5">dd/mm/yyyy </span> #end </a></td>
      <td><div align="right">Kawasan Rezab</div></td>
      <td><div align="center">:</div></td>
      <td><input name="txtKawasanRizab" type="text" id="txtKawasanRizab" style="text-transform:uppercase;" 
        value="$txtKawasanRizab" size="27" $readonly class="$disabled"/>      </td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font> </i>Taraf Hakmilik </div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%"><select name="socTaraf" id="socTaraf" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf()">
          
            
            
            
            
            
            
            
          
          
        #if($socTaraf == 'P')
          
          
          
            
            
            
            
            
            
            
          <option value="">SILA PILIH</option>
            <option value="P" selected="selected">P - PAJAKAN</option>
            <option value="F">F - PEGANGAN BEBAS</option>
          
            
            
            
            
            
            
            
          
          
           #elseif($socTaraf == 'F')
             
          
          
            
            
            
            
            
            
            
          <option value="">SILA PILIH</option>
            <option value="P">P - PAJAKAN</option>
            <option value="F" selected="selected">F - PEGANGAN BEBAS/FREE HOLD</option>
          
            
            
            
            
            
            
            
          
          
           #else
             
          
          
            
            
            
            
            
            
            
          <option value="" selected="selected">SILA PILIH</option>
            <option value="P">P - PAJAKAN</option>
            <option value="F">F - PEGANGAN BEBAS/FREE HOLD</option>
          
            
            
            
            
            
            
            
          
          
        #end
        
        
        
          
          
          
          
          
          
          
        </select>      </td>
      <td width="15%"><div align="right"><i><font color="#ff0000">*</font></i> Syarat Nyata</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%" rowspan="4"><label></label>
          <label>
          <textarea name="txtSyarat" cols="27" rows="5" id="txtSyarat" style="text-transform:uppercase;"  $readonly class="$disabled">$txtSyarat </textarea>
      </label></td>
    </tr>
    <tr>
      <td width="21%"><div align="right">Tempoh </div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%"><input name="txtTempoh" type="text" id="txtTempoh" value="$txtTempoh" size="4" $readonly class="$disabled" onchange="cal_tarikh_luput()"
        onkeyup="validateNumber(this,this.value);"
        onblur="cal_tarikh_luput()"/>
        Tahun</td>
      <td width="15%">&nbsp;</td>
      <td width="1%">&nbsp;</td>
    </tr>
    <tr>
      <td width="21%"><div align="right">Tarikh Luput </div></td>
      <td width="1%"><div align="center">:</div></td>
      <td><input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="$txdTarikhLuput" size="9" $readonly class="disabled" 
        	 onKeyUp="validateNumber(this,this.value);" /></td>
      <td width="15%">&nbsp;</td>
      <td width="1%">&nbsp;</td>
    </tr>
    <tr>
      <td width="21%"><div align="right">Kod Bayaran Cukai</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td>&nbsp;</td>
      <td width="15%">&nbsp;</td>
      <td width="1%">&nbsp;</td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font> </i>Cukai Tahun Pertama </div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="28%">
        <input name="txtCukaiTahun" type="text" id="txtCukaiTahun" value="$txtCukaiTahun" size="20" $readonly class="$disabled" onKeyUp="validateNumber(this,this.value);"/>
      </a></td>
      <td width="15%"><div align="right"><i><font color="#ff0000">*</font></i> Sekatan Kepentingan</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%" rowspan="3"><textarea name="txtSekatan" cols="27" rows="5" id="txtSekatan" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$txtSekatan</textarea></td>
    </tr>
    <tr>
      <td width="21%"><div align="right">Cukai Semasa </div></td>
      <td width="1%"><div align="center">:</div></td>
      <td><input name="txtCukaiTerkini" type="text" id="txtCukaiTerkini" value="$txtCukaiTerkini" size="20" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);"/></td>
      <td width="15%">&nbsp;</td>
      <td width="1%">&nbsp;</td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font> </i>Lokasi </div></td>
      <td width="1%"><div align="center">:</div></td>
      <td><input name="txtLokasi" type="text" id="txtLokasi" value="$txtLokasi" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/></td>
      <td width="15%">&nbsp;</td>
      <td width="1%">&nbsp;</td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font> </i>Kegunaan Tanah</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td rowspan="3"><textarea name="txtKegunaanTanah" cols="27" rows="5" id="txtKegunaanTanah" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$txtKegunaanTanah</textarea></td>
      <td width="15%"><div align="right">Kementerian/Agensi Terkini</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><input name="txtKemAgenTerkini" type="text" id="txtKemAgenTerkini" size="30" $readonly class="$disabled"/></td>
    </tr>
    <tr>
      <td width="21%">&nbsp;</td>
      <td width="1%">&nbsp;</td>
      <td width="15%"><div align="right"><i><font color="#ff0000">*</font></i> Status Sah</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="34%"><select name="socStatus" id="socStatus" style="width:200px;" $readonly class="$disabled" $disabled>
        
        
        
        
          
          
          
            
            
            
            
            
            
            
          
          
          
          
            #if($socStatus == 'D')
          		
          
          
          
          
            
            
            
            
            
            
            
          
          
          
        
        
        
        <option value="">SILA PILIH</option>
        <option value="D" selected="selected">D - DAFTAR</option>
        <option value="H">H - HAKMILIK ASAL TIADA</option>
        
        
        
        
          
          
          
            
            
            
            
            
            
            
          
          
          
          
            #elseif($socStatus == 'H')
                
          
          
          
          
            
            
            
            
            
            
            
          
          
          
        
        
        
        <option value="">SILA PILIH</option>
        <option value="D">D - DAFTAR</option>
        <option value="H" selected="selected">H - HAKMILIK ASAL TIADA</option>
        
        
        
        
          
          
          
            
            
            
            
            
            
            
          
          
          
          
            #else
                
          
          
          
          
            
            
            
            
            
            
            
          
          
          
        
        
        
        <option value="" selected="selected">SILA PILIH</option>
        <option value="D">D - DAFTAR</option>
        <option value="H">H - HAKMILIK ASAL TIADA</option>
        
        
        
        
          
          
          
            
            
            
            
            
            
            
          
          
          
           
        	#end
        
        
        
        
        
          
          
          
          
          
          
          
        
        
        
      
      
      
      </select>      </td>
    </tr>
    <tr>
      <td width="21%">&nbsp;</td>
      <td width="1%">&nbsp;</td>
      <td rowspan="2"><div align="right">Pegawai Akhir Kemaskini</div></td>
      <td width="1%" rowspan="2"><div align="center">:</div></td>
      <td width="34%" rowspan="2"><input name="txtPegawaiAkhir" type="text" id="txtPegawaiAkhir" size="30" $readonly class="$disabled"/></td>
    </tr>
    <tr>
      <td><div align="right"><i><font color="#ff0000">*</font></i> Kategori</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td>$selectKategori</td>
    </tr>
    <tr>
      <td width="21%"><div align="right"><i><font color="#ff0000">*</font> </i>Unit Luas</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td>$selectLuas</td>
      <td><div align="right">Tarikh Akhir Kemaskini</div></td>
      <td width="1%"><div align="center">:</div></td>
      <td><input name="txdTarikhKemaskini" type="text" id="txdTarikhKemaskini" value="$txdTarikhKemaskini" size="9" onKeyUp="validateNumber(this,this.value);" $readonly class="disabled"/></td>
    </tr>
    
    
    <tr>
      <td height="24" colspan="6">
      <div align="center">
  <p>
    #if ($mode == 'view')
    <input type="button" name="btnUpdateHakmilik" id="btnUpdateHakmilik" value="Kemaskini" onclick="kemaskini_detailHakmilik($idHakmilik)"/>
    <input type="button" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
    #end
    
    #if ($mode == 'update')  
<input type="button" name="btnSaveHakmilik" id="btnSaveHakmilik" value="Simpan" onclick="update_detailHakmilik($idHakmilik)"/>
   		<input type="button" name="btnResetHakmilik" id="btnResetHakmilik" value="Batal" onclick="hakmilik_detailHakmilik($idHakmilik)"/>
    #end
    <input type="button" name="btnBackHakmilik" id="btnBackHakmilik" value="Kembali" onclick="kembaliHakmilik()"/>
  </p>
</div>      </td>
    </tr>
</table>
</fieldset>
#parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")<br />
<fieldset id="tableReport1" style="display:none;">
<legend>SENARAI LAPORAN</legend>
<table width="100%" border="0">
  <tr>
    <td><a href="javascript:cetakHakmilik($idHakmilik);" class="style1">Maklumat Fail</a></td>
  </tr>
</table>
</fieldset>
<script>
function kemaskini_detailHakmilik(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&idHakmilik="+id;
	document.${formName}.submit();
}
function doChangeStateHR() {
  doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeStateHR");
}
function doChangeDaerahHR() {
	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeDaerahHR");
}
function doChangeMukimHR() {
	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeMukimHR");
}
function update_detailHakmilik(id){
////VALIDATAION
 if(document.${formName}.socNegeriHR.value == 99999){ 
	alert('Sila masukkan " Negeri " terlebih dahulu.'); 
	document.${formName}.socNegeriHR.focus();
	return; 
 }
 if(document.${formName}.txdTarikhTerima.value == ""){ 
	alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
	document.${formName}.txdTarikhTerima.focus();
	return; 
 }
 if(document.${formName}.txdTarikhDaftar.value == ""){ 
	alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
	document.${formName}.txdTarikhDaftar.focus();
	return; 
 }
 if(document.${formName}.socTaraf.value == ""){ 
	alert('Sila masukkan " Taraf Hakmilik " terlebih dahulu.'); 
	document.${formName}.socTaraf.focus(); 
	return; 
 }	
// if(document.${formName}.socTaraf.value == "P"){
//	alert('Sila masukkan " Tempoh " terlebih dahulu.'); 
//	document.${formName}.txtTempoh.focus(); 
//	return; 
// }
 if(document.${formName}.txtCukaiTahun.value == ""){ 
	alert('Sila masukkan " Cukai Tahun Pertama " terlebih dahulu.'); 
	document.${formName}.txtCukaiTahun.focus();
	return; 
 }
 if(document.${formName}.txtLokasi.value == ""){ 
	alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
	document.${formName}.txtLokasi.focus();
	return; 
 }
 if(document.${formName}.txtKegunaanTanah.value == ""){ 
	alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
	document.${formName}.txtKegunaanTanah.focus();
	return; 
 }			
 if(document.${formName}.socLuas.value == ""){ 
	alert('Sila masukkan " Jenis Luas " terlebih dahulu.'); 
	document.${formName}.socLuas.focus();
	return; 
 }   
 if(document.${formName}.txtKegunaanTanah.value == ""){ 
	alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
	document.${formName}.txtKegunaanTanah.focus();
	return; 
 }
 if(document.${formName}.txtLuas.value == ""){ 
	alert('Sila masukkan " Luas " terlebih dahulu.'); 
	document.${formName}.txtLuas.focus();
	return; 
 }
 if(document.${formName}.socKategori.value == ""){ 
	alert('Sila masukkan " Kategori " terlebih dahulu.'); 
	document.${formName}.socKategori.focus();
	return; 
 }
 if(document.${formName}.txtSyarat.value == ""){ 
	alert('Sila masukkan " Syarat Nyata " terlebih dahulu.'); 
	document.${formName}.txtSyarat.focus();
	return; 
 }
 if(document.${formName}.txtSekatan.value == ""){ 
	alert('Sila masukkan " Sekatan Kepentingan " terlebih dahulu.'); 
	document.${formName}.txtSekatan.focus();
	return; 
 }
 if(document.${formName}.socStatus.value == ""){ 
	alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
	document.${formName}.socStatus.focus();
	return; 
 }        
 if ( !window.confirm("Adakah Anda Pasti ?") ){
	   return;
 }
//END OF VALIDATION
document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&idHakmilik="+id;	
document.${formName}.submit();
}
function doChangeTaraf() {
	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik");
}
function kembaliHakmilik(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";	
	document.${formName}.submit();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakHakmilik(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?idHakmilik="+idhakmilik;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cal_tarikh_luput(){
  var tr = document.${formName}.txdTarikhDaftar.value;
  var temp_tr = tr.substring(0,6)
  var year_cur = tr.substring(6,10)
  var tempoh = document.${formName}.txtTempoh.value; 
  var luput = temp_tr+(parseInt(year_cur)+parseInt(tempoh));
  document.${formName}.txdTarikhLuput.value = luput;
}
function cal_luas(idLuas){
  var jenisLuas = idLuas;
  if(jenisLuas == "2"){
  	var luasAsal = document.${formName}.txtLuas.value;
  	var luasM = luasH*10000;
	document.${formName}.txtLuasBersamaanH.value = luasAsal;
  	document.${formName}.txtLuasBersamaanM.value = luasM;
   }
   else
   if(jenisLuas == "5"){
  	  var luasAsal = document.${formName}.txtLuas.value;
  	  var luasH = luasAsal*0.09290304;
	  var luasM = luasAsal*929.0304;
	  document.${formName}.txtLuasBersamaanH.value = luasH;
  	  document.${formName}.txtLuasBersamaanM.value = luasM;
   }
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
</script>