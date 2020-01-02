
<style type="text/css">
<!--
.style1 {color: #0000FF}
.style2 {
	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
-->
</style>
<input name="action" type="hidden" value="$action" />
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<input name="mode" type="hidden" value="$mode" />
<input name="cetak" type="hidden" value="$cetak" />
<fieldset><legend>PENDAFTARAN HAKMILIK</legend>
#parse("app/htp/rekod/frmPendaftaranHakmilikMaklumatFail.jsp")
<fieldset>
<!--<legend>LAYER 11</legend>-->
<table width="100%" border="0" align="left">
	<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="20%"><div align="left"><i><font color="#ff0000">*</font> </i>Negeri</div></td>
	                <td width="2%">:</td>
	                <td width="75%">$txtNamaNegeri</td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left"><i><font color="#ff0000">*</font> </i>Jenis Hakmilik</div></td>
                <td width="2%">:</td>
                <td width="75%">$txtJenisHakmilik</td>
              </tr>
			</table>
		</td>		
	</tr>
	<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="20%"><div align="left"><i><font color="#ff0000">*</font> </i>Daerah</div></td>
	                <td width="2%">:</td>
	                <td width="75%">$txtNamaDaerah</td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left"><i><font color="#ff0000">*</font> </i>No Hakmilik</div></td>
                <td width="2%">:</td>
                <td width="75%"><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" /></td>
              </tr>
			</table>
		</td>		
	</tr>
	<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="20%"><div align="left"><i><font color="#ff0000">*</font> </i>Bdr/Pkn/Mkm</div></td>
	                <td width="2%">:</td>
	                <td width="75%">$txtNamaMukim</td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">No Strata</div></td>
                <td width="2%">:</td>
                <td width="75%">
                	<input type="text" name="txtBangunan" id="txtBangunan" size="5"/>
                	<input type="text" name="txtTingkat" id="txtTingkat" size="5"/>
                	<input type="text" name="txtPetak" id="txtPetak" size="5"/>
                	</td>
              </tr>
			</table>
		</td>		
	</tr>	
		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="20%"><div align="left"></div></td>
	                <td width="2%"></td>
	                <td width="75%"></td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left"><i><font color="#ff0000">*</font> </i>Kod</div></td>
                <td width="2%">:</td>
                <td width="75%">$selectLot</td>
              </tr>
			</table>
		</td>		
	</tr>
			<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="20%"><div align="left"></div></td>
	                <td width="2%"></td>
	                <td width="75%"></td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left"><i><font color="#ff0000">*</font> </i>No Lot</div></td>
                <td width="2%">:</td>
                <td width="75%"><input type="text" name="txtNoLot" id="txtNoLot" /></td>
              </tr>
			</table>
		</td>		
	</tr>
</table>
</fieldset>

<fieldset>
<!--<legend>LAYER 111</legend>-->
<table width="100%" border="0" align="left">
	<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left"><i><font color="#ff0000">*</font> </i>Tarikh Terima Hakmilik</div></div></td>
	                <td width="2%">:</td>
	                <td width="70%">
	                	<input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="9" $readonly class="$disabled"
        					onkeyup="validateNumber(this,this.value);" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdTarikhTerima);" 
        					 /> 
        					##if ($mode == 'update')
            				<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"> <img border="0" src="../img/calendar.gif"/><span class="style2"> dd/mm/yyyy </span>
        					<span id="txdTarikhHantar_a" class="alert_msg" ></span> ##end  
        			</td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left"><i><font color="#ff0000">*</font> </i>Rezab</div></td>
                <td width="2%">:</td>
                <td width="75%">
                <!--<select name="statusRizab" id="statusRizab" style="width:200px;" $readonly class="$disabled" $disabled> -->
                	<select name="statusRizab" id="statusRizab" style="width:200px;" >
          				<option value="0" selected="selected">SILA PILIH</option>
          				<option value="Y"> Y - YA</option>
          				<option value="T"> T - TIDAK</option>
       				</select> 
                </td>
              </tr>
			</table>
		</td>		
	</tr>
	<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left"><i><font color="#ff0000">*</font> </i>Tarikh Daftar Hakmilik</div></td>
	                <td width="2%">:</td>
	                <td width="70%">
                		<input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="9" $readonly class="$disabled" 
			        	onkeyup="validateNumber(this,this.value);" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdTarikhDaftar);semakTarikhAkhirMula(document.${formName}.txdTarikhDaftar,document.${formName}.txdTarikhTerima,'Tarikh Daftar tidak melebihi dari Tarikh Terima.');">
			        ##if ($mode == 'update')
			            <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="style2">dd/mm/yyyy </span>
			        ##end 		
        			</td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">No. Warta</div></td>
                <td width="2%">:</td>
                <td width="75%">          
                	<input name="txtNoWarta" type="text" id="txtNoWarta" value="$txtNoWarta" $readonly class="$disabled"/>
					</td>
              </tr>
			</table>
		</td>		
	</tr>
	<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left"><i><font color="#ff0000">*</font> </i>Taraf Hakmilik</div></td>
	                <td width="2%">:</td>
	                <td width="65%">
	                	<!--<select name="socTaraf" id="socTaraf" style="width:200px;" $readonly class="$disabled" $disabled onchange="doChangeTaraf();">
				      #if($socTaraf == '') 
				          <option value="0" selected="selected">SILA PILIH</option>
				          <option value="P">P - PAJAKAN</option>
				          <option value="F">F - PEGANGAN BEBAS</option>
				        #end
				        #if($socTaraf == 'P')
				          <option value="0">SILA PILIH</option>
				          <option value="P" selected="selected">P - PAJAKAN</option>
				          <option value="F">F - PEGANGAN BEBAS</option>
				        #end	
				        #if($socTaraf == 'F')
				          <option value="0">SILA PILIH</option>
				          <option value="P">P - PAJAKAN</option>
				          <option value="F" selected="selected">F - PEGANGAN BEBAS/FREE HOLD</option>
				        #end
				        </select>-->
				        
				        <select name="socTaraf" id="socTaraf" style="width:200px;" >
				          <option value="0" selected="selected">SILA PILIH</option>
				          <option value="P">P - PAJAKAN</option>
				          <option value="F">F - PEGANGAN BEBAS/FREE HOLD</option>
				        </select>
        		</td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">Tarikh Warta</div></td>
                <td width="2%">:</td>
                <td width="75%">
                	<input name="txdTarikhWarta" type="text" id="txdTarikhWarta" value="$txdTarikhWarta" size="9" $readonly class="$disabled"
                	onkeyup="validateNumber(this,this.value);" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdTarikhWarta);"/>
        			##if ($mode == 'update')
            			<a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="style2">dd/mm/yyyy </span>
        			##end
                	</td>
              </tr>
			</table>
		</td>		
	</tr>	
		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left">Tempoh</div></td>
	                <td width="2%">:</td>
	                <td width="65%">	
 						<input name="txtTempoh" type="text" id="txtTempoh" value="$txtTempoh" size="4" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="cal_tarikh_luput()"/>Tahun
 						</td>
   	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">Jenis Rezab</div></td>
                <td width="2%">:</td>
                <td width="75%">$selectRizab</td>
              </tr>
			</table>
		</td>		
	</tr>

	</tr>	
		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left">Tarikh Luput</div></td>
	                <td width="2%">:</td>
	                <td width="65%">	
	      			#if($checkTaraf == 'P' || $socTaraf=='P')
              	<input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="$txdTarikhLuput" size="9" onchange="cal_tarikh_luput()" $readonly class="disabled" 
        	 onkeyup="validateNumber(this,this.value);" />
            <a href="javascript:displayDatePicker('txdTarikhLuput',false,'dmy');"></a>
            	#else
            	
	              <input name="txdTarikhLuput" type="text" id="txdTarikhLuput" value="" size="9" onchange="cal_tarikh_luput()" $readonly class="disabled" 
        	 onkeyup="validateNumber(this,this.value);" />
            <a href="javascript:displayDatePicker('txdTarikhLuput',false,'dmy');">
	             	#end
	              
	              </tr>
	              
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">Kawasan Rezab</div></td>
                <td width="2%">:</td>
                <td width="75%">        <input name="txtKawasanRizab" type="text" id="txtKawasanRizab" onkeyup="this.value=this.value.toUpperCase();" value="$txtKawasanRizab" size="27" $readonly class="$disabled""/>
</td>
              </tr>
			</table>
		</td>		
	</tr>

		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left"><i><font color="#ff0000">*</font> </i>Cukai Tahun Pertama</div></div></td>
	                <td width="2%">:</td>
	                <td width="65%"><input name="txtCukaiTahun" type="text" id="txtCukaiTahun" value="$txtCukaiTahun" size="20" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);"/>
          
	              </tr>
	              
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left"><i><font color="#ff0000">*</font> </i>Kategori</div></td>
                <td width="2%">:</td>
                <td width="75%">$selectKategori</td>
              </tr>
			</table>
		</td>		
	</tr>

		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left"><i><font color="#ff0000">*</font> </i>Lokasi</div></div></td>
	                <td width="2%"></td>
          <td width="65%"><input name="txtLokasi" type="text" id="txtLokasi" onkeyup="this.value=this.value.toUpperCase();" value="$txtLokasi" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>
          
	              </tr>
	              
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%" valign="top"><div align="left">Syarat Nyata</div></td>
                <td width="2%" valign="top">:</td>
                <td width="75%">
                	<textarea name="txtSyarat" cols="27" rows="5" id="txtSyarat" style="text-transform:uppercase;"  $readonly class="$disabled">$txtSyarat </textarea>
                </td>
              </tr>
			</table>
		</td>		
	</tr>

		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%" valign="top"><div align="left">
	                	<i><font color="#ff0000">*</font> </i>Kegunaan Tanah</div>
	                </td>
	                <td width="2%" valign="top">:</td>
					<td width="65%" valign="top">
						<textarea name="txtKegunaanTanah" cols="27" rows="5" id="txtKegunaanTanah" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$txtKegunaanTanah</textarea> 
					</td>	         
	              </tr>
	              
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%" valign="top"><div align="left">Sekatan Kepentingan</div></td>
                <td width="2%" valign="top">:</td>
                <td width="75%">
                	<textarea name="txtSekatan" cols="27" rows="5" id="txtSekatan" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$txtSekatan</textarea>
                </td>
              </tr>
			</table>
		</td>		
	</tr>

		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left"><i><font color="#ff0000">*</font> </i>Unit Luas</div></td>
	                <td width="2%">:</td>
					<td width="65%">$selectLuas	 </td>             
				</tr>	              
			</table>
		</td>
		
				<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">No Pelan Akui</div></td>
                <td width="2%">:</td>
                <td width="75%"><input name="txtNoPelan" type="text" id="txtNoPelan" value="$txtNoPelan" size="30" $readonly class="$disabled" onkeyup="this.value=this.value.toUpperCase();"/>
       	        </td>
              </tr>
			</table>
		</td>	
	</tr>
		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left"><i><font color="#ff0000">*</font> </i>Luas</div></td>
	                <td width="2%">:</td>
					<td width="65%">
						<input name="ktanah" type="hidden" value="2"/>
						<input name="field_luas2" type="hidden"/>
						<input name="field_luas3" type="hidden"/>
						<input name="txtLuas" type="text" class="$disabled" id="txtLuas" onkeyup="validateNumber(this,this.value);" 
						onblur=" kira_keluasan(document.${formName}.socLuas,document.${formName}.ktanah,document.${formName}.txtLuas,document.${formName}.field_luas2,document.${formName}.field_luas3,document.${formName}.txtLuasBersamaan);" 
						value="$txtLuas" size="20" $readonly/>
						
					</td>
					    <td id="ekarpole" style="display:none;">
							<input name="field_luas2" type="text"/>
							<input name="field_luas3" type="text"/>
					    </td>
				</tr>	              
			</table>
		</td>
				<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
				
				
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">No. Syit Piawai</div></td>
                <td width="2%">:</td>
                <td width="75%">          
                	<input name="txtNoSyit" type="text" id="txtNoSyit" value="$txtNoSyit" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>
				</td>
              </tr>
			</table>
		</td>		
		
	</tr>

		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="30%"><div align="left">Luas Bersamaan(H)</td> <input name="txtIdLuasBersamaan" type="hidden" value="2"/>
	                <td width="2%">:</td>
          			<td width="65%">
          				<input name="txtLuasBersamaan" type="text" id="txtLuasBersamaan" size="20" $readonly class="disabled" onkeyup="this.value=this.value.toUpperCase();"/>
         	 		</td>    
				</tr>             
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">No. PU</div></td>
                <td width="2%">:</td>
                <td width="75%">          
                	<input name="txtNoPu" type="text" id="txtNoPu" value="$txtNoPu" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>
				</td>
              </tr>
			</table>
		</td>		

	</tr>
	
	     <tr>
        <td colspan="2"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
	
</table>
</fieldset>

<!----><input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan" /> 
<table width="100%" border="0" align="left">
	<tr><td colspan=2>
		<div align="center">
  <p> <!--
    #if ($mode == 'view')
    <input type="button" name="btnUpdateHakmilik" id="btnUpdateHakmilik" value="Kemaskini" onclick="kemaskini_detailHakmilik($idHakmilik)"/>
    <input type="button" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
    #end
    <input type="submit" name="btnDelete" id="btnDelete" value="Hapus" />
    -->
    ##if ($mode == 'update')  
    	<input type="button" class="stylobutton" name="btnSaveHakmilik" id="btnSaveHakmilik" value="Simpan" onclick="update_detailHakmilik($idHakmilik)"/>
   		<input type="button" class="stylobutton" name="btnResetHakmilik" id="btnResetHakmilik" value="Batal" onclick="kembaliHakmilik()"/>
    ##end 
    	<!--<input type="button" class="stylobutton" name="btnBackHakmilik" id="btnBackHakmilik" value="Kembali" onclick="kembaliHakmilik()"/> -->
  </p> </td>
</div>
</tr>
</table>


