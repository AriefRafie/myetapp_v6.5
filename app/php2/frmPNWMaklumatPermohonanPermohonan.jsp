<input name="flagHakmilik" type="hidden" id="flagHakmilik" value="$!flagHakmilik" /> 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatPenawaran in $BeanMaklumatPenawaran)
  <tr>
    <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">Tarikh Terima</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPenawaran.tarikhTerima" onBlur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
      #if ($mode == 'update') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
  </tr>
  <tr>
    <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
    <td valign="top">Tarikh Surat</td>
    <td>:</td>
    <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPenawaran.tarikhSurat" onBlur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
      #if ($mode == 'update')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
  </tr>
  <tr>
    <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
    <td valign="top">Perkara</td>
    <td valign="top">:</td>
    <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$beanMaklumatPenawaran.perkara</textarea></td>
  </tr>
  <tr>
    <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
    <td>Luas Kegunaan</td>
    <td>:</td>
    <td >$selectLuasKegunaan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Keluasan Asal</td>
    <td>:</td>
    <td>$beanMaklumatPenawaran.luasAsal $beanMaklumatPenawaran.keteranganLuasAsal
      <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$!beanMaklumatPenawaran.luasAsal"/></td>
  </tr>
  #if ($idLuasKegunaan == '2')
  <tr>
    <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
    <td>Unit Luas</td>
    <td>:</td>
    <td>#parse("app/php2/unit_luas.jsp") </td>
  </tr>
  #if ($idLuas != '99999' && $idLuas != '')
  <tr>
    <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
    <td>Luas Ditawarkan</td>
    <td>:</td>
    <td> #if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
      <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPenawaran.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/ >
      #elseif ($idLuas == '7')
      <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPenawaran.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
      <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatPenawaran.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" / $readonly class="$inputTextClass">
      #elseif ($idLuas == '8' || $idLuas == '4')
      <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$beanMaklumatPenawaran.luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
      <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$beanMaklumatPenawaran.luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
      <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$beanMaklumatPenawaran.luas3" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/>
      #end </td>
  </tr>
  #end
  #end
  <tr>
    <td>&nbsp;</td>
    <td>Luas Bersamaan</td>
    <td>:</td>
    <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$beanMaklumatPenawaran.luasBersamaan"  style="text-align:right" readonly="readonly" class="disabled"/>
      HEKTAR</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Baki Luas</td>
    <td>:</td>
    <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$beanMaklumatPenawaran.luasBaki" readonly="readonly" class="disabled" style="text-align:right"/>
      HEKTAR</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if ($mode == 'update')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniMaklumatPenawaran('$idLuas')"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      #end
      #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniMaklumatPenawaran()"/>
      #if($idStatus == '1610198')
      <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end </td>
  </tr>
  #end
  <tr>
   	<td colspan="4">
   		<table width="100%" border="0" cellspacing="2" cellpadding="2">
   			<tr>
                <td>
                	<fieldset>
                		<legend><b>SENARAI TANAH</b></legend>
                		<table align="center" width="100%">
	                		<tr class="table_header">
		                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
		                      <td width="15%"><strong>Pegangan Hakmilik</strong></td>
		                      <td width="10%"><strong>Lot</strong></td>
		                      <td width="10%"><strong>No. Hakmilik</strong></td>
		                      <td width="10%"><strong>No. Warta</strong></td>
		                      <td width="15%"><strong>Mukim</strong></td>
		                      <td width="15%"><strong>Daerah</strong></td>
		                      <td width="15%"><strong>Negeri</strong></td>
		                    </tr>
				            #set ($senaraiTanahSemua = "")
		                    #if ($SenaraiTanahSemua.size() > 0)
		                    	#foreach ($senaraiTanahSemua in $SenaraiTanahSemua)
		                    		#if ($senaraiTanahSemua.bil == '')
		                    			#set( $row = "row1" )
		                    		#elseif (($senaraiTanahSemua.bil % 2) != 0)
		                    			#set( $row = "row1" )
		                    		#else 
		                    			#set( $row = "row2" )
		                    		#end
				                    <tr>
				                      <td class="$row" align="center">$senaraiTanahSemua.bil</td>
				                      <td class="$row"><a href="javascript:papar('$senaraiTanahSemua.idHakmilikPermohonan','$senaraiTanahSemua.flagHakmilik')" class="style2">$senaraiTanahSemua.peganganHakmilik</a></td>
				                      <td class="$row">$senaraiTanahSemua.noLot</td>
				                      <td class="$row">$senaraiTanahSemua.noHakmilik</td>
				                      <td class="$row">$senaraiTanahSemua.noWarta</td>
				                      <td class="$row">$senaraiTanahSemua.namaMukim</td>
				                      <td class="$row">$senaraiTanahSemua.namaDaerah</td>
				                      <td class="$row">$senaraiTanahSemua.namaNegeri</td>
				                    </tr>		
		                    	#end
		                    #else                    
			                    <tr>
			                      <td class="row1" align="center">&nbsp;</td>
			                      <td class="row1">Tiada Rekod</td>
			                      <td class="row1">&nbsp;</td>
			                      <td class="row1">&nbsp;</td>
			                      <td class="row1">&nbsp;</td>
			                      <td class="row1">&nbsp;</td>
			                      <td class="row1">&nbsp;</td>
			                      <td class="row1">&nbsp;</td>
			                    </tr>	
                    		#end	                    
                		</table>
                	</fieldset>
                </td>
            </tr>
   		</table>
    </td>
  </tr>
</table>
<script>
function papar(idHakmilikPermohonan, flagHakmilik){
	document.${formName}.selectedTabUpper.value = '2';
	document.${formName}.idHakmilikPermohonan.value = idHakmilikPermohonan;
	document.${formName}.flagHakmilik.value = flagHakmilik;
	document.${formName}.flagPopup.value = "openPopupMaklumatPermohonan";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatPenawaran() {
	document.${formName}.mode.value = "update";
	document.${formName}.selectedTabUpper.value = '2';
	document.${formName}.flagPopup.value = "openPopupMaklumatPermohonan";
	doAjaxCall${formName}("");
}
</script>