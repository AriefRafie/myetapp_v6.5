<style type="text/css">
<!--
	.stylelabel{color: #0000FF}
-->
</style>
#set ($tajuk = "")
#set ($noFail = "")
#set ($tSurat = "")
#set ($tPermohonan = "")
#set ($noRujukan = "")
#set ($tAgihan = "")
#set ($idAgensi = "")
#set ($idSuburusan = "")
#foreach ( $semakbaru in $SemakBaru )
	#set ($tajuk = $semakbaru.tajuk)
    #set ($noFail = $semakbaru.noFail)
    #set ($idAgensi = $semakbaru.idAgensi)
    #set ($idSuburusan = $semakbaru.idSuburusan)
    #set ($tAgihan = $semakbaru.tAgihan)
#end
#foreach ( $semak in $Semak )
	#set ($tajuk = $semak.tajuk)
    #set ($noFail = $semak.noFail)
    #set ($tSurat = $semak.tSurat)
    #set ($noRujukan = $semak.noRujukan)
    #set ($tAgihan = $semak.tAgihan)
    #set ($tPermohonan = $semak.tPermohonan)
    #set ($idSuburusan = $semakbaru.idSuburusan)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdPermohonan != "")
	#set ($btnName = "value='Batal'")
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
    <tr>
		<td>

		<fieldset><legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
  			<table border="0" cellpadding="0" cellspacing="0" width="100%">
      			<tr>
					<td align="center" valign="top" width="50%">
						<table width="100%" border="0">
              				<tr>
				            	<td width="35%" style="text-transform:uppercase;">
				                	<div align="right">Negeri</div>
				               	</td>
				                <td width="1%">:</td>            
				                <td width="64%">
				                	<div align="left" class="stylelabel">$selectNegeri</div>               	
				                </td>			              	
			              	</tr>
              				<tr>
				            	<td width="35%" style="text-transform:uppercase;" valign="top">
				                	<div align="right" >Agensi</div>
				               	</td>
				                <td width="1%" valign="top">:</td>            
				                <td width="64%">
				                	<div align="left" class="stylelabel">$selectAgensi</div>               	
				                </td>			              	
			              	</tr>			              	
              				<tr>
				            	<td width="35%" style="text-transform:uppercase;">
				                	<div align="right">Urusan</div>
				               	</td>
				                <td width="1%">:</td>            
				                <td width="64%">
				                	<div align="left" class="stylelabel">$selectSuburusan</div>               	
				   					<input type="hidden" name="idSuburusan" value="$idSuburusan">             
				   				</td>			              	
			              	</tr>	
              				<tr>
				            	<td width="35%" style="text-transform:uppercase;" valign="top">
				                	<div align="right" valign="top">Tajuk</div>
				               	</td>
				                <td width="1%" valign="top">:</td>            
				                <td width="64%">
				                	<div align="left" class="stylelabel">$tajuk</div>             
				   				</td>			              	
			              	</tr>	
							<!--
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td><input type="hidden" name="txtTajuk2" value="$tajuk" /></td>
              </tr>	-->
        				</table>
        			</td>
        			
        			<td align="center" valign="top" width="50%">
        				<table width="100%" border="0">
        					<tr>
        						<td width="1%">
        						</td>
				            	<td width="34%" style="text-transform:uppercase;" >
				                	<div align="left">No. Fail Seksyen</div>
				               	</td>
				                <td width="1%" >:</td>            
				                <td width="64%">
				                	<div align="left" class="stylelabel">$noFail</div>             
				                	            
				   				</td>			              	
			              	</tr>	
        					<tr>
        						<td width="1%">
        					  		<span class="labelmandatory">#if ($pagemode == 'baru' || $pagemode == 'kemaskini') * #end </span>
        						</td>
				            	<td width="34%" style="text-transform:uppercase;" >
				                	<div align="left">No. Fail Lain</div>
				               	</td>
				                <td width="1%" >:</td>            
				                <td width="64%">
				                	<input type="text" name="txtNoFailLain" id="txtNoFailLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode $classDis />            
				   				</td>			              	
			              	</tr>
        					<tr>
        						<td width="1%">
        					  		<span class="labelmandatory">#if ($pagemode == 'baru' || $pagemode == 'kemaskini') * #end </span>
        						</td>
				            	<td width="34%" style="text-transform:uppercase;">
				                	<div align="left">Tarikh Surat KJP</div>
				               	</td>
				                <td width="1%" >:</td>            
				                <td width="64%">
				                	<input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" value="$tSurat" $mode $classDis >
                					<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2">          
				   				</td>			              	
			              	</tr>
        					<tr>
        						<td width="1%">
        					  		<span class="labelmandatory">#if ($pagemode == 'baru' || $pagemode == 'kemaskini') * #end </span>
        						</td>
				            	<td width="34%" style="text-transform:uppercase;" >
				                	<div align="left">Tarikh Permohonan</div>
				               	</td>
				                <td width="1%" >:</td>            
				                <td width="64%">
				                	<input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="15" value="$tPermohonan" $mode $classDis />
                					<img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2">          
				   				</td>			              	
			              	</tr>             

            			#if ($IdPermohonan == "")
        					<tr>
        						<td width="1%">
        						</td>
				            	<td width="34%" style="text-transform:uppercase;" >
				                	<div align="left">Tarikh Buka Fail</div>
				               	</td>
				                <td width="1%" >:</td>            
				                <td width="64%">
				                	<input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$datenow" readonly="readonly" $classDis >
                					<input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/>          
				   				</td>			              	
			              	</tr>               				

            			#else
        					<tr>
        						<td width="1%">
        						</td>
				            	<td width="34%" style="text-transform:uppercase;" >
				                	<div align="left">Tarikh Buka Fail</div>
				               	</td>
				                <td width="1%" >:</td>            
				                <td width="64%">
				                	<input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$tAgihan" readonly="readonly" $classDis >
                					<input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/>          
				   				</td>			              	
			              	</tr>              			

            			#end
            			
        				</table>
        			</td>
      			</tr>
           	</table>
		</fieldset> 

		</td>
      </tr>		     
      
	<tr>
        <td colspan="2">
        	
        	<fieldset><legend><strong>SENARAI SEMAKAN</strong></legend>       	
        		<table width="100%" border="0">

          		#set ( $checked = "" )
            	#foreach ( $semak in $senaraiSemakan )
                	#set( $i = $velocityCount )
                	#if ( ($i % 2) == 0 )
                    	#set( $row = "row2" )
                	#else
                    	#set( $row = "row1" )
                	#end
	                <tr>
	                    <td class="$row" width="10">
	                        #if ( $semakclass.isSemakan("$IdPermohonan", "$semak.id" ))
	                            #set ( $checked = "checked" )
	                        #else
	                           #set ( $checked = "" )
	                        #end
	                        <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $modeSoc>
	                    </td>
	                    <td class="$row">
	                        $semak.keterangan
	                    </td>
	                </tr>       
            	#end
        		</table>
			</fieldset> 
		</td>
	</tr>
  	#if ($pagemode == 'baru' || $pagemode == 'kemaskini')
    <tr>
        <td>
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        </td>
    </tr>
	#end    
	
	<tr>
		<td colspan="2" align="center">
        
		#if($pagemode == 'baru' || $pagemode == 'kemaskini')
        	<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGSA_Simpan()">
        	<input type="reset" class="stylobutton100" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fGSA_Batal()">
       		<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGSA_Kembali()">

        #elseif($pagemode == 'simpan')     
       		<input type="button" class="stylobutton100" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGSA_Kemaskini()">
       		<input type="button" class="stylobutton100" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGSA_Seterusnya()">
        
       	#else    
       	<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGSA_Kembali()">
       	<input type="button" class="stylobutton100" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGSA_Seterusnya()">
		
		#end     
        </td>     
	</tr>
    
  </table>

	<input type="hidden" name="idFail" value="$IdFail">
	<input type="hidden" name="noFail" value="$NoFail">
  	<input type="hidden" name="idPermohonan" value="$IdPermohonan">
  	<input type="hidden" name="style1" value="$Style1">
  	<input type="hidden" name="style2" value="$Style2">
  	<input type="hidden" name="socAgensi" value="$agensiVal">