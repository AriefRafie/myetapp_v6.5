
<style type="text/css">
<!--
.style1 {color: #0000FF}
.style2 {
	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
-->

<!--
.pautanms {color: #0000FF}
-->

</style>
<div id='div_isexist'>
	
<input name="semak" type="hidden" value="$semakTanah1" />
<input name="semak" type="hidden" value='$!{session.getAttribute("semakTanah")}' />

</div>

<input name="action" type="hidden" value="$action" />
<input name="idHakmilik" type="hidden" value="$!idHakmilik" />
<input name="idPermohonan" type="hidden" value="$idPermohonan" />
<input name="mode" type="hidden" value="$mode" />
<input name="INS_UPD" type="hidden" value="$INS_UPD" />
<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">
<input type="hidden" name="socJenisHakmilikLama" value="$!flagAdvSearch">
<input type="hidden" name="txtNoHakmilikLama" value="$!flagAdvSearch">
<input type="hidden" name="isexistxx">
#if($semakSubUrusan == false)
<table width="100%" border="0" cellspacing="2" cellpadding="2">
<tr>
<td>
<div class=warning>[MODUL HTP REKOD] SILA KEMASKINI MAKLUMAT SUB URUSAN BAGI FAIL ($!txtNoFailSeksyen) TERLEBIH DAHULU</div>
</td>
</tr>
</table>
#else

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
   		<td>&nbsp;</td>
	</tr>

	<tr>
    	<td>
    	
#set ($label = 'MAKLUMAT')
#if ($mode == 'update' || $mode == 'kemaskini' )
	#set ($label = "KEMASKINI")
 #elseif ($mode == 'new' || $mode == 'addHakmilik')  
	#set ($label = "PENDAFTARAN")
#end 
##parse("app/htp/paging_penerimaanhakmilikrizab.jsp")
<fieldset><legend>$!label HAKMILIK</legend>
#parse("app/htp/rekod/frmPendaftaranHakmilikMaklumatFail.jsp")
<fieldset>
<!--<legend>LAYER 11</legend>-->
<table width="100%" border="0">
      		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Negeri</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
 								$txtNamaNegeri
  							</td>
                		</tr>
                     	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Daerah</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
 								$txtNamaDaerah
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Bandar/Pekan/Mukim</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
 								$txtNamaMukim
  							</td>
                		</tr>

                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                   	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Jenis Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
 								$!txtJenisHakmilik
  							</td>
                		</tr>
              			
            			<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<input name="txtNoHakmilik" type="text" class="$disabled" id="txtNoHakmilik" maxlength="50" value="$!txtNoHakmilik" size="30" $readonly />
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Strata</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
                   				<span class="labelinput">No.Bang</span>&nbsp;<input name="txtNoBangunan" type="text" class="$disabled" id="txtNoBangunan"  value="$!txtNoBangunan" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
								<span class="labelinput">No.Ting</span>&nbsp;<input name="txtNoTingkat" type="text" class="$disabled" id="txtNoTingkat"  value="$!txtNoTingkat" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
								<span class="labelinput">No.Petak</span>&nbsp;<input name="txtNoPetak" type="text" class="$disabled" id="txtNoPetak"  value="$!txtNoPetak" size="3" maxlength="3" $readonly onkeyup="this.value=this.value.toUpperCase();"/>
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kod</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
 								$selectLot
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Lot/PT</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<input name="txtNoLot" type="text" class="$disabled" id="txtNoLot" size="30" maxlength="25" value="$txtNoLot" $readonly onblur = "semakHakmilik();"/>
  							</td>
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
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%" valign="top">
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Daftar Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this); validateTarikhHakmilik();" />
      							#if ($mode == 'update' || $mode == 'addHakmilik' || $mode == 'new') 
      								<a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');" > <img border="0" src="../img/calendar.gif"/>
      								 <span class="pautanms">dd/mm/yyyy</span> </a>
    							#end 			
        					</td>
                		</tr>
                     	<tr>
  							<td width="1%" valign="top">
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Terima Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
 								<input name="txdTarikhTerima" type="text" id="txdTarikhTerima" value="$txdTarikhTerima" size="11" maxlength="10" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="check_date(this); validateTarikhHakmilik();"/>
    							#if ($mode == 'update' || $mode == 'addHakmilik' || $mode == 'new') 
    								<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"> <img border="0" src="../img/calendar.gif"/>
    								<span class="pautanms">dd/mm/yyyy</span></a> 
    							#end
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Taraf Hakmilik</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
						   	#if ($socTaraf == "P")
	                            #set ($selectedDefault = "")
	                            #set ($selectedP = "selected")
	                            #set ($selectedF = "")
	                        #elseif ($socTaraf == "S")
	                            #set ($selectedDefault = "")
	                            #set ($selectedP = "")
	                            #set ($selectedF = "selected")
	                         #else
	                            #set ($selectedDefault = "selected")
	                            #set ($selectedP = "")
	                            #set ($selectedF = "")
	                         #end   
					          <select $readonly class="$disabled" $disabled name="socTaraf" id="socTaraf" style="width:200px;" >
					            <option $selectedDefault value="0">SILA PILIH</option>
					            <option $selectedP value="P">P - PAJAKAN</option>
					            <option $selectedF value="S">S - PEGANGAN BEBAS/FREE HOLD</option>
					          </select>
      						</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory"></span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tempoh</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txtTempoh" type="text" id="txtTempoh" value="$!txtTempoh" size="4" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="cal_tarikh_luput()"/> Tahun  			
      						</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory"></span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Luput</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
						 		<input name="txdTarikhLuput" type="text" class="disabled" id="txdTarikhLuput" value="$!txdTarikhLuput" size="11" maxlength="10" $readonly class="$disabled" />
						 	</td>
                		</tr>
                   		<tr>
  							<td width="1%" valign="top">
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%" >
                            	<div align="left">
                            		<span class="labelinput">Cukai Tahun Pertama (RM)</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
                            #if ($mode == "update")
                            #set ($readOnlyCukai = "readonly class='disabled'")
                            #else
                            #set ($readOnlyCukai = "")
                            #end
    							<input $readOnlyCukai name="txtCukaiTahun" type="text" id="txtCukaiTahun" value="$!txtCukaiTahun" size="20" $readonly class="$disabled" onkeyup="validateNumber(this,this.value);" onblur="formatCurrencyPertama(this.value)"/> 						
      						</td>
                		</tr>
                   		<tr>
  						<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Lokasi</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txtLokasi" type="text" id="txtLokasi" value="$!txtLokasi" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>		
				   			</td>
                		</tr>
                   		<tr>
  							<td width="1%" valign="top">
  								<span class="labelmandatory" >#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%" valign="top" >
                            	<div align="left">
                            		<span class="labelinput" >Kegunaan Tanah</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<textarea name="txtKegunaanTanah" cols="61" rows="3" id="txtKegunaanTanah" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$!txtKegunaanTanah</textarea>
						   	</td>
                		</tr>                		
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Unit Luas</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
                   			#if ($mode == 'update' || $mode == 'updateHakmilik')  
								#parse("app/htp/unit_luasV1.jsp")                    			
                   			#else
								#parse("app/htp/unit_luas.jsp") 
						   	#end
						   	</td>
                		</tr> 
                   		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Luas</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								#if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6' || $socLuas == '9')
									<input name="txtLuas1" type="text" class="$disabled" id="txtLuas1" value="$!txtLuasLama" size="30" maxlength="15" onkeyup="validateNumber(this,this.value);" $readonly onBlur="kiraLuas('$socLuas')" />
								#elseif($socLuas == '8' || $socLuas == '4')
									<input name="txtLuas2" type="text" class="$disabled" id="txtLuas2" value="$!txtLuasLama" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
									<input name="txtLuas3" type="text" class="$disabled" id="txtLuas3" value="$!txtLuasLama1" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
									<input name="txtLuas4" type="text" class="$disabled" id="txtLuas4" value="$!txtLuasLama2" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')" />
								#elseif($socLuas == '7')
									<input name="txtLuas5" type="text" class="$disabled" id="txtLuas5" value="$!txtLuasLama" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
									<input name="txtLuas6" type="text" class="$disabled" id="txtLuas6" value="$!txtLuasLama1" size="13" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onBlur="kiraLuas('$socLuas')" />
								#else
									<input name="txtLuas1" type="text" class="$disabled" id="txtLuas1" value="$!txtLuasLama" size="30" maxlength="15" $readonly onkeyup="validateNumber(this,this.value);"  onBlur="kiraLuas('$socLuas')" />
								#end 
									<input name=XtxtLuas type=hidden value=$!Luas>
									<input name="txtLuasLama" type="hidden" id="txtLuasLama" value="$!Luas" />
									<input name="txtLuasGabung" type="hidden" id="txtLuasGabung" value="$!txtLuasLama" />
									
									
						   	</td>
                		</tr> 
                   		<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Luas Bersamaan</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txtLuas" type="text" class="disabled" id="txtLuas" onkeyup="this.value=this.value.toUpperCase();" value="$!txtLuas" size="30" $readonly />   				
          				        (Hektar)  							
							</td>
                		</tr>

                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">

                   		<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Warta</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
      							<input name="txtNoWarta" type="text" id="txtNoWarta" value="$!txtNoWarta" $readonly class="$disabled"/>
        					</td>
                		</tr>
                   		<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Warta</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txdTarikhWarta" type="text" class="$disabled" id="txdTarikhWarta" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);validateTarikhSemasa(document.${formName}.txdTarikhWarta);"
			                	 value="$!txdTarikhWarta" size="11" maxlength="10" $readonly class="$disabled"/>
			        			#if ($mode == 'update' || $mode == 'addHakmilik' || $mode == 'new') 
			            			<a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"> <img border="0" src="../img/calendar.gif"/> <span class="pautanms">dd/mm/yyyy </span>
			        			#end
      						</td>
                		</tr>                		
                    		<tr>
  							<td width="1%">
  								<!-- <span class="labelmandatory">*</span> -->
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Jenis Rizab</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								$selectRizab
        					</td>
                		</tr>
                		<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kawasan Rizab</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<input name="txtKawasanRizab" type="text" id="txtKawasanRizab" onkeyup="this.value=this.value.toUpperCase();" value="$!txtKawasanRizab" size="27" $readonly class="$disabled""/>
        					</td>
                		</tr>
                		<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kategori</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="labeldisplay" >
								$selectKategori
        					</td>
                		</tr>              			
            			<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%" valign="top" >
                            	<div align="left">
                            		<span class="labelinput">Syarat Nyata</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top" >:</td>
                   			<td width="68%" class="labeldisplay" >
        						<textarea name="txtSyarat" cols="61" rows="3" id="txtSyarat" style="text-transform:uppercase;"  
                    				$readonly class="$disabled">$!txtSyarat</textarea>
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%" valign="top" >
                            	<div align="left">
                            		<span class="labelinput">Sekatan Kepentingan</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top" >:</td>
                   			<td width="68%" class="labeldisplay" >
								<textarea name="txtSekatan" cols="61" rows="3" id="txtSekatan" style="text-transform:uppercase;" $readonly="$readonly" class="$disabled">$!txtSekatan</textarea>  							
							</td>
                		</tr>
               			<tr>
	  						<td width="1%" >
					       	</td>				        
	                        <td width="30%" valign="top" >
	                           	<div align="left">
	                           		<span class="labelinput">Catatan/Urusan</span>
	                           	</div>
	                        </td>
	                  		<td width="1%" class="labelinput" valign="top" >:</td>
	                   		<td width="68%" class="labeldisplay" >
								<textarea name="txtKemAgenTerkini" cols="61" rows="3" style="text-transform:uppercase;" class="$disabled" id="txtKemAgenTerkini" $readonly>$!txtKemAgenTerkini</textarea>  							
							</td>
                		</tr>                     		
	                   		<tr>
	  							<td width="1%"  >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Pelan Diperakui</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<input name="txtNoPelan" type="text" id="txtNoPelan" value="$!txtNoPelan" size="30" $readonly class="$disabled" onkeyup="this.value=this.value.toUpperCase();"/>
	        					</td>
	                		</tr>
	                   		<tr>
	  							<td width="1%"  >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Syit Piawai</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
	      							<input name="txtNoSyit" type="text" id="txtNoSyit" value="$!txtNoSyit" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>
	        					</td>
	                		</tr>
	                   		<tr>
	  							<td width="1%"  >
					        	</td>				        
	                        	<td width="30%">
	                            	<div align="left">
	                            		<span class="labelinput">No. Permohonan Ukur</span>
	                            	</div>
	                        	</td>
	                  			<td width="1%" class="labelinput" >:</td>
	                   			<td width="68%" class="labeldisplay" >
									<input name="txtNoPu" type="text" id="txtNoPu" value="$!txtNoPu" size="30" $readonly class="$disabled" style="text-transform:uppercase;"/>
	      						</td>
	                		</tr>

                	</table>
                </td>
       		</tr>   
       		                 
		</table>
	</fieldset>		
<!--<tr>
        <td colspan="2"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
	-->
	#if ($mode != 'doView' && $mode != 'view')  
	  <table>
	  <tr>
	    <td colspan="2">
        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	    	</td>
	  </tr>
	  </table>
	#end

<table width="100%" border="0" align="left">
	<tr><td colspan=2>
		<div align="center">
        ##if ($INS_UPD == 'update')  
        #if ($mode == 'new')  
 			<input type="button" class="stylobutton100" name="btnSimpan"  value="Simpan" onclick="tambah_detailHakmilik($idPermohonan)"/>
			<input type="reset"  class="stylobutton100" name="btnReset"   value="Kosongkan"/>
        	<input type="button" class="stylobutton100" name="btnkembali" value="Batal" onclick="doAjaxCall${formName}('')"/> 
         
        #elseif ($mode == 'update' || $mode == 'updateHakmilik')  
            <input type="button" class="stylobutton100" value="Simpan" onclick="kemaskini_detailHakmilik($idHakmilik)"/>           
       <!-- <input type="button" class="stylobutton100" value="Hapus" name="hapus" onclick="kemaskini_detailHakmilik($idHakmilik)"/>           
          	<input type="button" class="stylobutton100" value="Cetak" name="Cetak" id="Cetak" onclick="javascript:cetakMaklumatHakmilik($idHakmilik);" /> 
       --> 
    		<input type="button" class="stylobutton100" value="Batal" name="btnkembali" onclick="kemaskiniHakmilik('$idPermohonan','$idHakmilik')"/> 
        ##elseif ($INS_UPD == 'doView')  
        #elseif ($mode == 'doView')  
            <input type="button" class="stylobutton100" value="Hapus" name="hapus" onclick="hapusTanah($idHakmilik)"/>           
            <input type="button" class="stylobutton100" value="Kemaskini" onclick="doKemaskiniView($idHakmilik)"/>
           	<input type="button" class="stylobutton100" value="Cetak" name="Cetak" id="Cetak"  onclick="javascript:cetakMaklumatHakmilik($idHakmilik);" />
            <input type="button" class="stylobutton100" value="Tambah" onclick="TambahHakmilikByIdPermohonan($idPermohonan)"/>
    		<input type="button" class="stylobutton100" value="Kembali" name="btnkembali" onclick="hakmilik_detail2P($idPermohonan)"/> 

        #elseif ($mode == 'view')  
        	<input type="button" class="stylobutton100" value="Hapus" name="hapus" onclick="hapusTanah($idHakmilik)"/>           
            <input type="button" class="stylobutton100" value="Kemaskini" onclick="doKemaskiniView($idHakmilik)"/>
           	<input type="button" class="stylobutton100" value="Cetak" name="Cetak" id="Cetak" onclick="javascript:cetakMaklumatHakmilik($idHakmilik);" />
      <!--  <input type="button" class="stylobutton100" value="Kembali" name="btnkembali" onclick="doHakmilikPage2()"/> -->
    		<input type="button" class="stylobutton100" value="Kembali" name="btnkembali" onclick="hakmilik_detail2P($idPermohonan)"/> 
    		
         
       	#else
			<input type="button" class="stylobutton100" name="btnSimpan"  value="Simpan" onclick="tambah_detailHakmilik($idPermohonan)"/>
			<input type="reset"  class="stylobutton100" name="btnReset"   value="Kosongkan"/>
			##if ($INS_UPD == 'add')  
    		##else
   	  <!-- <input type="button" class="stylobutton100" name="btnkembali" value="Batal" onclick="doHakmilikPage2()"/> -->
     		<input type="button" class="stylobutton100" value="Batal" name="btnkembali" onclick="hakmilik_detail2P($idPermohonan)"/> 
    		##end
		#end
		<!-- <input type="button" class="stylobutton" value="Kembali" onclick="kembaliHakmilik()"/> -->

        </div>
  <p> 
  </p> </td>
</tr>
</table>

</fieldset>
##parse("app/htp/paging_penerimaanhakmilikrizab.jsp") 


		</td>
	<tr/>
</table>

#end