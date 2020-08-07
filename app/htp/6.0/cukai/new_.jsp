
   <style>
    #boxListCukai {        
        position: absolute;       
        background: #eeeeee;       
        border: 1px solid #1a1a1a;       
		display: block;		
		z-index:2;
		
		/* CSS Box Shadow */
		-moz-box-shadow: 0 0 8px #000000;
		-webkit-box-shadow: 0 0 8px #000000;
		box-shadow: 0 0 8px #000000;		
		
		/* CSS Box Radius */
		-moz-border-radius: 5px;
		-webkit-border-shadow: 5px;
		border-radius: 5px;
      }	 
    </style>
    
    <table width="98%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
		#set ($inputstyle = "class=disabled" )
			<fieldset><legend>KEMASUKAN MAKLUMAT MANUAL</legend>
			
			<table width="100%" border="0">
			
  				<tr>
    				<td width="50%" valign="top">
    					<fieldset><legend>MAKLUMAT TANAH</legend>
    					
    					        <table width="100%" border="0">
							          <tr>
							            <td width="1%"><font color="red">*</font></td>
							            <td width="28%">Negeri</td>
							            <td width="1%">:</td>
							            <td width="70%">$manualNegeri</td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>Daerah</td>
							            <td>:</td>
							            <td>$manualDaerah</td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>Mukim/Pekan/Bandar</td>
							            <td>:</td>
							            <td>$manualMukim</td>
							          </tr>
							          <tr>
							            <td>&nbsp;</td>
							            <td>Jenis Hakmilik</td>
							            <td>:</td>
							            <td>$JenisHakmilik</td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>No. Hakmilik</td>
							            <td>:</td>
							            <td><input name="txtNoHakmilik" type="text" id="txtNoHakmilik" size="44" onkeyup="this.value=this.value.toUpperCase();">
							              </td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>Jenis Lot/PT</td>
							            <td>:</td>
							            <td>$jenisLot
							              </td>
							          </tr>
							          <tr>
							            <td >&nbsp;</td>
							            <td>No. Lot/PT</td>
							            <td>:</td>
							            <td><input name="txtNoLot" type="text" id="txtNoLot" size="44" onkeyup="this.value=this.value.toUpperCase();" /></td>
							          </tr>
							          <tr>
							            <td valign="top"><font color="red">*</font></td>
							            <td valign="top" >Kegunaan Tanah</td>
							            <td valign="top" >:</td>
							            <td><textarea name="txtKegunaanTanah" id="txtKegunaanTanah" cols="41" rows="5" onkeyup="this.value=this.value.toUpperCase();"></textarea></td>
							          </tr>
							        </table>
        
    					</fieldset>
    				</td>
    				<td width="50%" valign="top">
 						<fieldset><legend>BAYARAN</legend>
 						
 						        <table width="100%" >
									<tr>
							            <td width="1%"><font color="red">*</font></td>
							            <td width="28%">Cukai Semasa (RM)</td>
							            <td width="1%">:</td>
							            <td width="63%">
							              <input name="txtTahunan" type="text" id="txtTahunan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.cukaiKenaBayar)">
							              </td>
							          </tr>
							       
							          <tr>
							            <td>&nbsp;</td>
							            <td>Tunggakan (RM)</td>
							            <td>:</td>
							            <td>
							              <input name="txtTungakan" type="text" id="txtTungakan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.tunggakkan)">
							              </td>
							          </tr>
							          <tr>
							            <td>&nbsp;</td>
							            <td>Denda Lewat (RM)</td>
							            <td>:</td>
							            <td>
							                <input name="txtDenda" type="text" id="txtDenda" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.denda)">
							              </td>
							          </tr>
							          <tr>
							            <td>&nbsp;</td>
							            <td>Cukai Lain (RM)</td>
							            <td>:</td>
							            <td><input name="txtCukaiLain" type="text" id="txtCukaiLain" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.cukailain)"/></td>
							            </tr>
							          <tr>
							            <td>&nbsp;</td>
							            <td>Lebihan [ - ] (RM)</td>
							            <td>:</td>
							            <td><input name="txtLebihan" type="text" id="txtLebihan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.lebihan)"></td>
							          </tr>
							          <tr>
							            <td><font color="red">*</font></td>
							            <td>Cukai Kena Bayar (RM)</td>
							            <td>:</td>
							            <td><input name="txtJumBayaran" type="text" id="txtJumBayaran" size="11"  onblur="validateCurrency(this,this.value,'');calculate()" value="$!UTIL.format2Decimal($!cukai.cukaiPerluBayar)" $inputstyle></td>
							          </tr>
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
							          <tr>
							            <td >&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							          </tr> 
					
							      	</table>
        
    					</fieldset>
    				</td>   				
    					
  				</tr>
			</table>

			</fieldset>
  	    </td> 	
  	</tr>
  	<tr>
		<td align="center">
			<input class="stylobutton100" name="Simpan" type="button" value="Simpan" onclick="simpanManual()"/>
 			<input class="stylobutton100" name="Batal" type="button" value="Batal" onclick="Kembali()"/>
		</td>
	</tr>
</table>
	<input type="hidden" name="idcukai" value="$cukai.idCukaiTemp" />
	<input type="hidden" value="$Tahun" name="tahun"/>

<script>

</script>