#set ($idFail = "")
#set ($tajuk = "")
#set ($noFail = "")
##set ($tSurat = "")
#set ($noRujukan = "")
##set ($tAgihan = "")
#set ($tarikhDaftarFail = "")
#foreach ( $semak in $Semak )
    #set ($idFail = $semak.idFail)
	#set ($tajuk = $semak.tajuk)
    #set ($noFail = $semak.noFail)
    ##set ($tSurat = $semak.tSurat)
    #set ($noRujukan = $semak.noRujukan)
    ##set ($tAgihan = $semak.tAgihan)
    #set ($tarikhDaftarFail = $semak.tarikhDaftarFail)
#end

#set ($btnName = "value='Kosongkan'")
#if ($idFail != "")
	#set ($btnName = "value='Batal'")
#end
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
    	
			<fieldset><legend><strong>MAKLUMAT FAIL</strong></legend>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
		      		<tr>
						<td align="center" valign="top" width="50%">
							<table width="100%" border="0">
				  				<tr>
								    <td><div align="right"><font color="#FF0000">*</font></div></td>
								    <td><div align="left">Urusan</div></td>
								    <td>:</td>
								    <td>$selectSuburusan</td>
								</tr>
				  	<tr>
				    <td><div align="right"><font color="#FF0000">*</font></div></td>
				    <td>Agensi</td>
				    <td valign="top">:</td>
				    <td>$selectAgensi</td>
				    </tr>
		              <tr>
		                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
		                <td width="20%"><div align="left">Negeri</div></td>
		                <td width="2%" valign="top">:</td>
		                <td width="75%">$selectNegeri</td>
		              </tr>
		              <tr>
		                <td valign="top"><div align="right"><font color="#FF0000">*</font></div></td>
		                <td valign="top"><div align="left">Tajuk </div></td>
		                <td valign="top">:</td>
		                <td>$selectTajuk</td>
		              </tr>
		              <tr>
		                <td valign="top">&nbsp;</td>
		                <td valign="top">&nbsp;</td>
		                <td valign="top">&nbsp;</td>
		                <td>&nbsp;</td>
		              </tr>
		        </table></td>
		        <td align="center" valign="top" width="50%"><table width="100%" border="0">
		              <tr>
		                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
		                <td width="31%"><div align="left">No. Fail Seksyen</div></td>
		                <td width="2%">:</td>
		                <td width="64%">
		                 #if ($idFail == "")
		                <input type="text" name="txtNoFailSek" size="40" maxlength="400" class="" value="" onkeyup="this.value=this.value.toUpperCase();">
		                #else
		                
		                $noFail
		 
		                </td>
		                #end              
		                
		                
		                
		              </tr>
		              
		             #if ($idFail == "")
		             <tr>
		               <td>&nbsp;</td>
		               <td><div align="left">Tarikh Daftar Fail</div></td>
		                <td>:</td>
		                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$datenow" class="disabled" onblur="" />
		               <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/>                </td>
		              </tr>
		             #else
		             <tr>
		               <td>&nbsp;</td>
		               <td><div align="left">Tarikh Daftar Fail</div></td>
		                <td>:</td>
		                <td>
		                $tarikhDaftarFail
		                </td>
		              </tr>
		             #end
		        </table>
		        </td>
		      </tr>
		      <tr>
		        <td colspan="2">&nbsp;</td>
		      </tr>
		
				</table>
  
  			  	<table>
				  	<tr>
				    	<td colspan="2">
			        		<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
				    	</td>
				  	</tr>
			  	</table>	
 
			  	<input type="hidden" name="idFail" value="$idFail">
			  	<input type="hidden" name="noFail" value="$noFail">
			  	<input type="hidden" name="style1" value="$Style1">
				<input type="hidden" name="style2" value="$Style2">

				<input type="hidden" name="negeri" value="$!idNegeri">
			</fieldset>

    	</td>
	</tr>
	
    <tr>
		<td align="center">        
        #if ($pagemode == "new" || $pagemode == "kemaskini" )		
        	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" class="stylobutton" onClick="javascript:fGS2A_Simpan()" />
        	<input type="reset" name="cmdBatal" id="cmdBatal" $btnName class="stylobutton" onClick="javascript:fGS2A_Batal()" />
        	<input type="button" style="display:none" name="cmdKembali" id="cmdKembali" value="Kembali" class="stylobutton" onclick="javascript:fGS2A_Kembali()" />       
        #elseif ($pagemode == "view")
        	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" class="stylobutton" onClick="jaavascript:fGS2A_Kemaskini()" />
        	<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" class="stylobutton" onClick="javascript:fGS2A_Seterusnya()" />
        #end             
        </td>
	</tr>	
</table>





