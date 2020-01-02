 #if($dis=='0')
 #set ($classDis = "class='disabled' disabled='disabled'" )
 #set ($classDis1 = "class='disabled' disabled='disabled'" )
 #else
 #set ($classDis1 = "class='disabled' disabled='disabled'" )
 #set ($classDis = "class='disabled' disabled='disabled'" )
 #end
 <table width="98%" border="0" cellspacing="2" cellpadding="2">
	<tr>		
    	<td>  
    		&nbsp;
	    </td>  	
	</tr>
 	<tr>
    	<td>    	
 
			<fieldset><legend>Kemaskini Tajuk Fail</legend>

				<table width="100%" border="0">
				  <tr>
				    <td width="3%">&nbsp;</td>
				    <td width="35%"><div align="left">No. Fail Seksyen</div></td>
				    <td width="1%"><div align="center">:</div></td>
				    <td width="61%"><input type="text" name="txtNoFailSek" size="30" value="$!permohonan.fail"  $classDis1 onkeyup="this.value=this.value.toUpperCase();" ></td>
				    </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td valign="top"><div align="left">Tajuk</div></td>
				    <td valign="top"><div align="center">:</div></td>
				    <td><textarea name="txttajuk" cols="41" rows="5" onblur="this.value=this.value.toUpperCase();"  >$!permohonan.tajukfail</textarea></td>
				    </tr>
				  <tr>
				    <td>&nbsp;</td>
				    <td colspan="3"> 
				    </tr>
				</table>
				</fieldset>
  		</td>
 	</tr>	
 	<tr>
    	<td align="center">   
    		#if($dis=='0')
				    <input class="stylobutton100" type="button" value="Kemaskini" onClick="kemaskiniTajukPermohonan()"/>
				    #else
				    <input class="stylobutton100" type="button" value="Simpan" onClick="simpanTajukPermohonan($!permohonan.idfail)"/>
				    #end
					<input class="stylobutton100" type="button" onclick="javascript:cetakKulitFail('$!permohonan.idpermohonan');" value="Cetak Kulit Fail" />
					<input class="stylobutton100" type="button" onclick="javascript:cetakDoket('$!permohonan.idpermohonan');" value="Cetak Doket" />
				    <input class="stylobutton100" type="button" value="Kembali" onClick="backMain()"/></td>
				    	
 		</td>
 	</tr>			 	
 </table>