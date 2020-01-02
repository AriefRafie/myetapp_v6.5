<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
</head>

<body>
<fieldset>
<legend>PEMBATALAN HAKMILIK</legend>
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
    <td width="34%"><span class="style1">$txtFailPTG</span></td>
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
    <td width="21%"><div align="right">Negeri</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%">$selectNegeriHR</td>
    <td width="15%"><div align="right">Luas</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><label>
      <input name="txtLuas" type="text" class="disabled" id="txtLuas" onKeyUp="validateNumber(this,this.value);" value="$txtLuas" size="20" readonly onChange="cal_luas('$socLuas')" onBlur="cal_luas('$socLuas')"/>
    </label></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Daerah</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%">$selectDaerahHR</td>
    <td width="15%"><div align="right">Luas Bersamaan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><input name="txtLuasBersamaanH" type="text" id="txtLuasBersamaanH" size="20" readonly="readonly" class="disabled" onKeyUp="this.value=this.value.toUpperCase();"/>
      (hektar) </td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Bandar/Pekan/Mukim</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%">$selectMukimHR</td>
    <td width="15%"><div align="right">No Pelan Akui</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><input name="txtNoPelan" type="text" id="txtNoPelan" value="$txtNoPelan" size="30" readonly class="disabled" onKeyUp="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Jenis Hakmilik</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%">$selectJenisHakmilikHR</td>
    <td width="15%"><div align="right">No. Syit Piawai</div></td>
    <td><div align="center">:</div></td>
    <td width="34%"><input name="txtNoSyit" type="text" id="txtNoSyit" value="$txtNoSyit" size="30" readonly class="disabled" style="text-transform:uppercase;"/></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">No. Hakmilik</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><span class="style1">
      <input name="txtNoHakmilik" type="text" class="disabled" id="txtNoHakmilik" onKeyUp="validateNumber(this,this.value);" value="$txtNoHakmilik" size="20" readonly/>
    </span></td>
    <td width="15%"><div align="right">No. PU</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><input name="txtNoPu" type="text" id="txtNoPu" value="$txtNoPu" size="30" readonly class="disabled" style="text-transform:uppercase;"/></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">No. Strata</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><span class="style1">
      <input name="txtNoStrata" type="text" class="disabled" id="txtNoStrata" onKeyUp="validateNumber(this,this.value);" size="20" readonly />
    </span></td>
    <td width="15%"><div align="right">Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><select name="socRizab" id="socRizab" style="width:200px;" readonly class="disabled" disabled onChange="doChangeTaraf()">
      
          
            
            
            
            
            
            
            
          
          
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
    <td><div align="right">Jenis Lot</div></td>
    <td><div align="center">:</div></td>
    <td>$selectJenisLotHR</td>
    <td width="15%"><div align="right">No. Warta</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><label>
      <input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" readonly class="disabled"/>
    </label></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">No Lot</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><span class="style1">
      <input name="txtNoLot" type="text" class="disabled" readonly id="txtNoLot" onKeyUp="validateNumber(this,this.value);" value="$txtNoLot" size="20"/>
    </span></td>
    <td width="15%"><div align="right">Tarikh Warta</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><input name="txdTarikhWarta" type="text" id="txdTarikhWarta" value="$txdTarikhWarta" size="9" readonly class="disabled" onKeyUp="validateNumber(this,this.value);"/></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Tarikh Terima Hakmilik</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><span class="style2">
      <input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="9" readonly class="disabled"
        onkeyup="validateNumber(this,this.value);"/>
    </span></td>
    <td width="15%"><div align="right">Jenis Rezab</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><label> $selectRizab</label></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Tarikh Daftar Hakmilik </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="9" readonly class="disabled" 
        onkeyup="validateNumber(this,this.value);" /></td>
    <td><div align="right">Kawasan Rezab</div></td>
    <td><div align="center">:</div></td>
    <td><input name="txtKawasanRizab" type="text" id="txtKawasanRizab" style="text-transform:uppercase;" 
        value="$txtKawasanRizab" size="27" readonly class="disabled"/>    </td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Taraf Hakmilik </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><select name="socTaraf" id="socTaraf" style="width:200px;" readonly class="disabled" $disabled onChange="doChangeTaraf()">
      
          
            
            
            
            
            
            
            
          
          
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
        
        
        
          
          
          
          
          
          
          
        
    </select>    </td>
    <td width="15%"><div align="right">Syarat Nyata</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%" rowspan="4"><label></label>
        <label>
        <textarea name="txtSyarat" cols="27" rows="5" id="txtSyarat" style="text-transform:uppercase;"  readonly class="disabled">$txtSyarat </textarea>
      </label></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Tempoh </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><input name="txtTempoh" type="text" id="txtTempoh" value="$txtTempoh" size="4" readonly class="disabled" onChange="cal_tarikh_luput()"
        onkeyup="validateNumber(this,this.value);"
        onblur="cal_tarikh_luput()"/>
      Tahun</td>
    <td width="15%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Tarikh Luput </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td><input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="$txdTarikhLuput" size="9" readonly class="disabled" 
        	 onkeyup="validateNumber(this,this.value);" /></td>
    <td width="15%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Kod Bayaran Cukai</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td><a href="javascript:displayDatePicker('txdTarikhLuput',false,'dmy');">
      <select name="socStatus2" id="socStatus2" style="width:200px;" $readonly class="disabled" disabled>
        
          
          
          
          
          
            
            
            
            
            
            
            
          
          
          
          
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
        
        
        
        
        
          
          
          
          
          
          
          
        
        
        
        
        
      </select>
    </a></td>
    <td width="15%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Cukai Tahun Pertama </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="28%"><a href="javascript:displayDatePicker('txdTarikhLuput',false,'dmy');">
      <input name="txtCukaiTahun" type="text" id="txtCukaiTahun" value="$txtCukaiTahun" size="20" readonly class="disabled" onKeyUp="validateNumber(this,this.value);"/>
    </a></td>
    <td width="15%"><div align="right">Sekatan Kepentingan</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%" rowspan="3"><textarea name="txtSekatan" cols="27" rows="5" id="txtSekatan" style="text-transform:uppercase;" readonly class="disabled">$txtSekatan</textarea></td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Cukai Semasa </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td><input name="txtCukaiTerkini2" type="text" id="txtCukaiTerkini2" value="$txtCukaiTerkini" size="20" readonly class="disabled" onKeyUp="validateNumber(this,this.value);"/></td>
    <td width="15%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Lokasi </div></td>
    <td width="1%"><div align="center">:</div></td>
    <td><input name="txtLokasi" type="text" id="txtLokasi" value="$txtLokasi" size="30" $readonly class="disabled" style="text-transform:uppercase;"/></td>
    <td width="15%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Kegunaan Tanah</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td rowspan="3"><textarea name="txtKegunaanTanah" cols="27" rows="5" id="txtKegunaanTanah" style="text-transform:uppercase;" readonly="readonly" class="disabled">$txtKegunaanTanah</textarea></td>
    <td width="15%"><div align="right">Kementerian/Agensi Terkini</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td width="34%"><input name="txtKemAgenTerkini" type="text" id="txtKemAgenTerkini" size="30" readonly class="disabled"/></td>
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
                <option value="P">P - BATAL PELEPASAN</option>
                <option value="T">T - BATAL TUKARGUNA</option>
            	<option value="S">B - BATAL SAMBUNGAN</option>    
            #elseif($socStatus == 'P')
                <option value="">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="P" selected="selected">P- BATAL PELEPASAN</option>
                <option value="T">T - BATAL TUKARGUNA</option>
				<option value="S">B - BATAL SAMBUNGAN</option>
            #elseif($socStatus == 'S')
                <option value="">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="P">BP - BATAL PELEPASAN</option>
                <option value="T">T - BATAL TUKARGUNA</option>
				<option value="S" selected="selected">B - BATAL SAMBUNGAN</option>    
            #elseif($socStatus == 'T')
                <option value="">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="P">BP - BATAL PELEPASAN</option>
                <option value="T" selected="selected">T - BATAL TUKARGUNA</option>
				<option value="S">B - BATAL SAMBUNGAN</option>                      
            #else
                <option value="" selected="selected">SILA PILIH</option>
                <option value="D">D - DAFTAR</option>
                <option value="P">BP - BATAL PELEPASAN</option>
                <option value="T">T - BATAL TUKARGUNA</option>
				<option value="S" selected="selected">B - BATAL SAMBUNGAN</option>   
        	#end
        </select>    </td>
  </tr>
  <tr>
    <td width="21%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td rowspan="2"><div align="right">Pegawai Akhir Kemaskini</div></td>
    <td width="1%" rowspan="2"><div align="center">:</div></td>
    <td width="34%" rowspan="2"><input name="txtPegawaiAkhir" type="text" id="txtPegawaiAkhir" size="30" readonly class="disabled"/></td>
  </tr>
  <tr>
    <td><div align="right">Kategori</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td>$selectKategori</td>
  </tr>
  <tr>
    <td width="21%"><div align="right">Unit Luas</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td>$selectLuas</td>
    <td><div align="right">Tarikh Akhir Kemaskini</div></td>
    <td width="1%"><div align="center">:</div></td>
    <td><input name="txdTarikhKemaskini" type="text" id="txdTarikhKemaskini" value="$txdTarikhKemaskini" size="9" onKeyUp="validateNumber(this,this.value);" readonly class="disabled"/></td>
  </tr>
    <tr>
      <td colspan="6">&nbsp;</td>
    </tr>
    #if($socStatus=="S")<tr>
      <td colspan="6"><fieldset>
      <legend>SENARAI HAKMILIK SAMBUNGAN</legend>
      <table border="0" width="100%">
        <tr>
          <td colspan="7">#parse("app/utils/record_paging.jsp") <br/></td>
        </tr>
        <tr class="table_header">
          <td width="8%">Bil.</td>
          <td width="55%">No Hakmilik Berikut</td>
          <td width="37%">Tindakan</td>
        </tr>
        #foreach ($senarai in $listSambungan)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2")
        #else
        #set( $row = "row1")
        #end
        <tr class="$row">
          <td>$senarai.bil</td>
          <td>$senarai.kodJenisBaru $senarai.noHakmilikBaru</td>
          <td><a href="javascript:deleteHakmilikBaru('$senarai.idHakmilikAsal','$senarai.idHakmilikBaru')" class="style1"><div align="left"><img border="0" title ="Hapus Imej" src="../img/hapus.gif"/></div>
          </a></td>
          </tr>
        #end
      </table>
      </fieldset>      </td>
    </tr>
    #end
    <tr>
      <td colspan="6">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="6"><div align="center">
 	<input type="button" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
    <input type="button" name="btnBackHakmilik" id="btnBackHakmilik" value="Kembali" onclick="kembaliHakmilik()"/>
      </div></td>
  </tr>
</table>
<p>&nbsp;</p>
</fieldset>
<script>
function kemaskini_detailHakmilik(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=kemaskiniDetailHakmilik&idHakmilik="+id;
	document.${formName}.submit();
	//doAjaxCall${formName}("kemaskiniDetailHakmilik","idHakmilik="+id);
}
function update_detailHakmilik(id){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
	   return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=updateDetailHakmilik&idHakmilik="+id;
	document.${formName}.submit();
	//doAjaxCall${formName}("updateDetailHakmilik","idHakmilik="+id);
}
function tambahHakmilikSambungan(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=addHakmilikBaru&idHakmilik="+id;
	document.${formName}.submit();
	//doAjaxCall${formName}("kemaskiniDetailHakmilik","idHakmilik="+id);
}
function hakmilikBaru(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=daftarHakmilik&idHakmilik="+id;
	document.${formName}.submit();
	//doAjaxCall${formName}("kemaskiniDetailHakmilik","idHakmilik="+id);
}
function deleteHakmilikBaru(id,id2){
if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.command.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=deleteHakmilikBaru&idHakmilik="+id+"&idHakmilikBaru="+id2;
	document.${formName}.submit();
}
</script>
