<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
#set($hide='style="display:none"')
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	
	<input type="hidden" name="actionPajakan" id="actionPajakan" value="$actionPajakan"/>
  	<input type="hidden" name="mode" id="mode" value="$mode"/>
  	<input type="hidden" name="hitButton" id="hitButton" value="$hitButton"/>
	<input type="hidden" name="selectedTab" id="selectedTab" value="$selectedTab"/>
	
	<input type="hidden" name="flagPopup" id="flagPopup" value="$!flagPopup"/>
	<input type="hidden" name="modePopup" id="modePopup" value="$!modePopup"/>
  	<input type="hidden" name="idFail" id="idFail" value="$idFail"/>
  	<input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>  	
  	<input type="hidden" name="idPemohon"  id="idPemohon" value="$idPemohon"/>
  	<input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
  	<input type="hidden" name="subUrusan" id="subUrusan" value="$subUrusan"/>
  	<input type="hidden" name="idHakmilikUrusan" id="idHakmilikUrusan" />
  	<input type="hidden" name="idHakmilik" id="idHakmilik" value="$idHakmilik" />
  	
  	<input name="idHakmilikAgensiPopup" type="hidden" id="idHakmilikAgensiPopup"/>
  	<input type="hidden" name="kategori" id="kategori" value="$!pemohon.get("kategoriPemohon")"/>  	
</p>

<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
#if ($idFail != '')
	<tr>
    	<td>#parse("app/htp/frmPajakanHeaderOnline.jsp")</td>
  	</tr>
#else
  	<tr>
    	<td>&nbsp;<div class="warning">SILA PILIH FAIL ONLINE DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  	</tr>
#end

#if ($idFail != '')
	<tr>
    	<td>
        	<div id="TabbedPanels1" class="TabbedPanels">
            	<ul class="TabbedPanelsTabGroup"> 
                	<li onclick="doChangeTab(0);" class="TabbedPanelsTab"  tabindex="0">SENARAI SEMAK</li>
                	<li onclick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">PENGESAHAN PERMOHONAN</li>
				</ul>
				
				<div class="TabbedPanelsContentGroup">   
				    <!--<div class="TabbedPanelsContent">-->
				    
                <!------- CONTENT MAKLUMAT PERMOHONAN ------->
                	<!--<fieldset>
                		<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>                		
                    	<table width="100%" border="0" cellspacing="2" cellpadding="2">
                    	#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
                    	
                        	<tr>
                            	<td width="1%"></td>
                            	<td width="28%">Negeri</td>
                            	<td width="1%">:</td>
                            	<td width="70%">$beanMaklumatPermohonan.negeri</td>
                         	</tr>
                        	<tr>
                            	<td></td>
                            	<td>Kementerian</td>
                            	<td>:</td>
                            	<td>$beanMaklumatPermohonan.kementerian</td>
                         	</tr>
                         	<tr>
                            	<td></td>
                            	<td>Agensi</td>
                            	<td>:</td>
                            	<td>$beanMaklumatPermohonan.agensi</td>
                         	</tr>
                         	<tr>
                            	<td>&nbsp;</td>
                            	<td>Urusan</td>
                            	<td>:</td>
                            	<td>882 - PAJAKAN TANAH</td>
                         	</tr>
                      	  <tr>
                            	<td></td>
                            	<td>Sub Urusan</td>
                            	<td>:</td>
                            	<td>$beanMaklumatPermohonan.namaSubUrusan</td>
                         	</tr>
                         	<tr>
                            	<td></td>
                            	<td>Status Tanah</td>
                            	<td>:</td>
                            	<td>$beanMaklumatPermohonan.statusTanah</td>
                         	</tr>
                         	<tr>
                            	<td></td>
                            	<td>Jenis Fail</td>
                            	<td>:</td>
                            	<td>$beanMaklumatPermohonan.jenisFail</td>
                         	</tr>
                         	<tr>
                            	<td width="1%">&nbsp;</td>
                            	<td width="28%">No. Fail Seksyen</td>
                            	<td width="1%">:</td>
                            	<td width="70%">
                            	#if ($mode == 'update')
                            	<input name="txtNoFail" type="text" id="txtNoFail" value="$beanMaklumatPermohonan.noFail" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
                            	#else
                            	<input name="txtNoFail" type="text" disabled="disabled" class="disabled" id="txtNoFail" value="$beanMaklumatPermohonan.noFail" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
                            </td> #end
                         	</tr>
                         	<tr>
                            	<td></td>
                            	<td>No. Fail KJP</td>
                            	<td>:</td>
                            	<td>
                            	#if ($mode == 'update')
                            	<input type="text" name="txtNoFailKJP" id="txtNoFailKJP" value="$beanMaklumatPermohonan.noFailKJP" onkeyup="this.value=this.value.toUpperCase();" />
															#else
															<input type="text" name="txtNoFailKJP" id="txtNoFailKJP" $readonly class="$classDis" value="$beanMaklumatPermohonan.noFailKJP" onkeyup="this.value=this.value.toUpperCase();" />                         	
                         		</td>#end
                         	</tr>
                         	<tr>
                            	<td></td>
                            	<td valign="top">Tarikh Surat KJP</td>
                            	<td>:</td>
                            	<td>
                            	#if ($mode == 'update')
                            	<input type="text" size="11" maxlength="10" name="tarikhSuratKJP" id="tarikhSuratKJP" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratKJP"/>
                            	<a href="javascript:displayDatePicker('tarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                            	#else
                            	<input type="text" size="11" maxlength="10" name="tarikhSuratKJP" id="tarikhSuratKJP" onblur="check_date(this)" $readOnly class="$classDis" value="$beanMaklumatPermohonan.tarikhSuratKJP"/>
                            	</td>
                            	#end
                         	</tr>
                         	<tr>
                         		<td></td>
                         		<td>No. Fail Lain / Pemohon</td>
                         		<td>:</td>
                         		<td>
                         		#if ($mode == 'update')
                         		<input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$beanMaklumatPermohonan.noFailLain" onkeyup="this.value=this.value.toUpperCase();"/>
                         		#else
                         		<input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$beanMaklumatPermohonan.noFailLain" onkeyup="this.value=this.value.toUpperCase();" $readOnly class="$classDis"/></td>
                         		</td>
                         		#end
                         	</tr>
                         	<tr>
                           		<td></td>
                           		<td>Tarikh Surat Pemohon</td>
                           		<td>:</td>
                           		<td>
                           		#if ($mode == 'update')
                           		<input type="text" size="11" maxlength="10" name="tarikhSuratPemohon" id="tarikhSuratPemohon" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratPemohon"/>
															<a href="javascript:displayDatePicker('tarikhSuratPemohon',false,'dmy');"><img src="../img/calendar.gif" alt="Calendar" border="0"/>
															#else
															<input type="text" size="11" maxlength="10" name="tarikhSuratPemohon" class="$classDis" id="tarikhSuratPemohon" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratPemohon" readonly="readonly" $readOnly/>
															</td>
															#end
                         	</tr>
                         	<tr>
                            	<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
                           		<td valign="top">Tujuan</td>
                            	<td valign="top">:</td>
                            	
                            	<td valign="top">
                            	#if ($mode == 'update')
                            	<textarea name="txtTajuk" id="txtTajuk" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea>
                            	#else
                            	<textarea name="txtTajuk" id="txtTajuk" rows="5" cols="50" $readOnly class="$classDis" onkeyup="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea>
                            	</td>
                            	#end
                         	</tr>
                         	#end
                         	<tr>
                            	<td></td>
                           		<td valign="top">&nbsp;</td>
                           		<td valign="top">&nbsp;</td>
                            	<td colspan="2" valign="top">
                       			#if ($mode == 'view')
                					<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniFail()"/>
                					<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:goBack()"/>                					
               						#elseif ($mode == 'update')
                					<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanKemaskiniFail()"/>
                					<input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" $hide/>
                					<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalKemaskiniFail()"/>	           					 	
				                  #end </td>
                          	</tr>
            			</table>
                    </fieldset>                    
                	</div>--->
                	<!------- CLOSED CONTENT MAKLUMAT PERMOHONAN ------->
                    
                    
                    
                <!------- CONTENT SENARAI HAKMILIK ------->    
             <!-------<div class="TabbedPanelsContent">
                #if ($selectedTab == '1')
                   	<fieldset>
                    <legend><strong>SENARAI HAKMILIK</strong></legend>
                     <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    	<tr>
        				<td width="1%"></td>
          				<td width="28%">Pengangan Hakmilik</td>
          				<td width="1%">:</td>
          				<td width="70%"> $senaraiHakmilik.peganganHakmilik
          						<!-- #if ($mode == 'new')
            					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$senaraiHakmilik.peganganHakmilik" onblur="doChangePeganganHakmilik();">
            					<input class="stylobutton100" name="cmdDaftar" type="button" value="Carian Tanah" onClick="pilihTanah('$idPermohonan')">
          						#else
            					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$senaraiHakmilik.peganganHakmilik" readonly="readonly" class="disabled">
          						#end
            					<input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi">
            					<span class="style1">$errorPeganganHakmilik</span> 
            					<span class="style4"><i><font color="#ff0000">Contoh</font> : </i><span class="style5">160140GRN00000576</span></span>-->
            				<!--</td>
        			</tr>
        				<tr>
          					<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          					<td>No. Lot</td>
          					<td>:</td>
          					<td>
          					#if ($mode == 'update')
          						<input type="text" name="txtnoLot" id="txtnoLot" value="$senaraiHakmilik.noLot">
          					#else
          						<input type="text" name="txtnoLot" id="txtnoLot" value="$senaraiHakmilik.noLot" readonly="readonly" class="disabled">
          					#end
          						<input type="hidden" name="noLotTanah" id="noLotTanah" value="$senaraiHakmilik.lot" /></td>
        				</tr>
        				
        				<tr>
          					<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          					<td>No. Hakmilik</td>
          					<td>:</td>
          					<td>
          					#if ($mode == 'update')
          					<input type="text" name="txtnoHakmilik" id="txtnoHakmilik" value="$senaraiHakmilik.noHakmilik" onblur="doChangePeganganHakmilik();">
          					#else
          					<input type="text" name="txtnoHakmilik" id="txtnoHakmilik" value="$senaraiHakmilik.noHakmilik" onblur="doChangePeganganHakmilik1();" readonly="readonly" class="disabled">
          					#end	
          					<input type="hidden" name="noMilikTanah" id="noMilikTanah" value="$senaraiHakmilik.hakmilik" /><span class="style1">$errorPeganganHakmilik</span> </td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Luas Lot</td>
          					<td>:</td>
          					<td>$senaraiHakmilik.luasLot
          						<input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$senaraiHakmilik.idLuas" /> 
          						<input type="hidden" name="luasTanah" id="luasTanah" value="$senaraiHakmilik.luasBersamaan" /></td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
			          		<td>Mukim</td>
          					<td>:</td>
          					<td>$senaraiHakmilik.mukim
          						<input type="hidden" name="namaMukimTanah" id="namaMukimTanah" value="$senaraiHakmilik.mukim" /></td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Daerah</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.daerah
          						<input type="hidden" name="namaDerahTanah" id="namaDerahTanah" value="$senaraiHakmilik.daerah" /></td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Negeri</td>
          					<td>:</td>			
          					<td>$senaraiHakmilik.negeri
            					<input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$senaraiHakmilik.idNegeriTanah">
            					<input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$senaraiHakmilik.negeri">
          					</td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Kementerian</td>
          					<td>:</td>
          					<td>$senaraiHakmilik.kementerian
            					<input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$senaraiHakmilik.idKementerian">
            					<input type="hidden" name="kodKementerian" id="kodKementerian" value="$senaraiHakmilik.kodKementerian">
          					</td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Agensi</td>
          					<td>:</td>
          					<td>$senaraiHakmilik.agensi
          						<input type="hidden" name="idAgensiTanah" id="idAgensiTanah" value="$senaraiHakmilik.idAgensi">
        				</tr>
              			<tr>
                			<td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                			<td>Unit Luas</td>
                			<td>:</td>
                			<td>#parse("app/htp/unit_luas.jsp") </td>
              			</tr>
              			#if ($idLuas != '99999' && $idLuas != '')
              			<tr>
                			<td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
                			<td>Luas Mohon</td>
                			<td>:</td>
                			<td width="57%">
								#if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6' || $socLuas == '9')
								<input value="$!txtLuasLama" name="txtLuas1" type="text" class="$disabled" id="txtLuas1" size="20" maxlength="8" onkeyup="validateNumber(this,this.value);" $disability onblur="format5Decimal(this,this.value,'');kiraLuas('$socLuas');"/>
								#elseif($socLuas == '8' || $socLuas == '4')
								<input name="txtLuas2" type="text" class="$disabled" id="txtLuas2" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" />
								<input name="txtLuas3" type="text" class="$disabled" id="txtLuas3" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" />
								<input name="txtLuas4" type="text" class="$disabled" id="txtLuas4" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" onblur="kiraLuas('$socLuas');"/>
								#elseif($socLuas == '7')
								<input name="txtLuas5" type="text" class="$disabled" id="txtLuas5" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" />
								<input name="txtLuas6" type="text" class="$disabled" id="txtLuas6" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" onblur="kiraLuas('$socLuas');"/>
								#else
								<input value="$!txtLuasLama" name="txtLuas1" type="text" id="txtLuas1" size="20" maxlength="8" $disability $readability onkeyup="validateNumber(this,this.value);" onblur="kiraLuas('$socLuas');"/>
								#end 
								<input type=hidden name=XtxtLuas value=$!Luas>
								<input name="txtLuasLama" type="hidden" id="txtLuasLama" value="$!Luas" />
								<input name="txtLuasGabung" type="hidden" id="txtLuasGabung" value="$!txtLuasLama" />                                        	
							</td>
						</tr>
              
              			<tr>
                          	<td></td>
                        	<td valign="top">&nbsp;</td>
                          	<td colspan="2" valign="top">
                       		#if ($mode == 'view')
                			<input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniFail()"/>
                			<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:doBackList()"/>
               				#elseif ($mode == 'update')
                			<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanKemaskiniFail()"/>
                			<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" $hide/>
           					<input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalKemaskiniFail()"/>
       					 	#end</td>
                       	</tr>
					</table>
					#end
				</fieldset>
                #end
			</div>------->
              <!------- CLOSED SENARAI HAKMILIK------->
              	
              		
              	<!------- SENARAI SEMAK ------->  
			<div class="TabbedPanelsContent">
				<fieldset>
				<legend><strong>SENARAI SEMAK</strong></legend>
                	<table width="100%" border="0" cellspacing="2" cellpadding="2">
                	    <tr class="row2">
						<td width="3%"><b>Bil</b></td>
						<td width="82%"><b>Keterangan</b></td>
						<td width="15%"><b>Dokumen</b></td>
					</tr> 
  		#if ($SenaraiSemak.size() > 0)
      		#set ($list = "")
			#foreach ($list in $SenaraiSemak)
	        	#set( $i = $velocityCount )
	       		#if ( ($i % 2) == 0 )
	   	        	#set( $row = "row2" )
	            #else
	               	#set( $row = "row1" )
	          	#end
        
        		#if($list.flag == 'Y')
        			#set($checked = 'checked')
        		#else
        			#set($checked = '')
        		#end
	        
   				    	<tr class="$row">
     						<td class="$row" width="95%"><input type="checkbox" value="$list.id" name="idsSenaraiSemak" $checked $disabled />
     						$list.keterangan
     						</td>
          					<td class="$row" width="5%">
          					#if($list.jenisDokumen != 0)
          					<a href = "javascript:onlineAttach('$list.id','$list.jenisDokumen');">
          						<!-- <a href = "javascript:onlineAttach('$list.id','$list.jenisDokumen');">
								<img border="0" src="../img/plus.gif" width="20" height="15"/>
								</a><br> -->
							 	$!listam.lampirans
						##end 			
        				</tr>
        				#end
        	
        #else
        				<tr>
	          				<td class="$row" width="3%">&nbsp;</td>
    	      				<td class="$row" colspan="2" width="95%">Tiada Rekod</td>
        				</tr>
        #end
						<tr>
    						<td colspan="2">&nbsp;</td>
  						</tr>
  						<tr>
    						<td width="30%">&nbsp;</td>
    						<td width="70%">#if ($mode == 'update')
      							<input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniSenaraiSemak()"/>
      							<input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      							#end
      						
      						#if ($mode == 'view')
      							#if ($idStatus == '')
      							<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniFail()"/>
                				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:goBack()"/>
      							#end
      							<input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      						#end
      							
      						#if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
								<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
      						#end
     						</td>
      					</tr>
                    </table>
				</fieldset>
			</div>
            <!------- CLOSED SENARAI SEMAK ------->
            
            
            <!------- PENGESAHAN ------->  
		<div class="TabbedPanelsContent">
				<fieldset>
				<legend><strong>PENGESAHAN PERMOHONAN</strong></legend>
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
           	<td valign="top">
           	#if ($mode == 'view')<input type="checkbox" name="pengesahan" id="pengesahan">#end
      			#if ($idStatus == '')<input type="checkbox" name="pengesahan" id="pengesahan" $disabled checked>#end</td>
           	<td>Saya,$!pemohon.get("namaPemohon"), dengan ini mengaku bahawa segala maklumat yang diberikan adalah benar belaka 
           	<br/>tanpa sebarang keraguan dan paksaan dari mana-mana pihak.</td> 
           	<tr>
           	<td colspan=2 align="center">
           	#if ($mode == 'view')
            	<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onClick="doHantar()"/>
            	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
            #end</td>
           	</tr>            
       </table>
				</fieldset>
			</div>
            <!------- CLOSED PENGESAHAN ------->
		</div>
	<!-- CLOSED TabbedPanelsContentGroup -->    
</div>
      		<!-- CLOSED TabbedPanels1 -->
		</td>
	</tr>
	<tr>
    	<td>&nbsp;</td>
  	</tr>
  	<tr>
	    <td align="center"></td>
  	</tr>
#end
</table>


<script language="javascript" type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
#end
</script>

<script>
function seterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	
	document.${formName}.hitButton.value = "seterusnya";
	doAjaxCall${formName}("");
}

/*function pilihTanah(idPermohonan) {
	var url = "../x/${securityToken}/ekptg.view.htp.online.FrmPajakanPopupCarianSenaraiTanahView?idPermohonan="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
}*/

/*function refreshFromPilihTanah() {
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.submit();
	refreshAgain()
}*/

/*function refreshAgain() {
	document.${formName}.submit();
}*/

/*function doDeleteHakmilik(idHakmilikUrusan){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.idHakmilikUrusan.value = idHakmilikUrusan;
	document.${formName}.hitButton.value = "deleteHakmilik";
	document.${formName}.submit();
}*/

function doHantar(){
	if(pengesahan.checked != true){
		alert('Sila tanda pada checkbox untuk teruskan permohonan. ');
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "doHantar";
	doAjaxCall${formName}("");
}

function doHapus(){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	document.${formName}.hitButton.value = "doHapus";
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	doAjaxCall${formName}("");
}

function doChangeTab(tabId) {
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.selectedTab.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	doAjaxCall${formName}("");
}

function KemaskiniFail(){
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.selectedTab.value = 0;
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}


/*function KemaskiniFail2(){
	
	document.${formName}.actionPajakan.value = "";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}*/


function batalKemaskiniFail(){
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function SimpanKemaskiniFail(){
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan Tujuan.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	if (!window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTab.value = 0;
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.hitButton.value = "saveUpdateFail";
	doAjaxCall${formName}("");
}

function doBacklist() {
	alert('doBacklist');
	document.${formName}.actionPajakan.value = "";
	//document.${formName}.submit();
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

/*function goBack() {
  window.history.back();
}*/


<!-- MAKLUMAT LAMPIRAN -->
/*function daftarLampiran() {
	document.${formName}.action = "?_portal_module=ekptg.view.htp.online.FrmPajakanOnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.actionPajakan = "paparMaklumatPajakan";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}*/

function kemaskiniTanah() {
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("kemaskiniTanah");
}

/*function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}*/

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function doSimpanKemaskiniSenaraiSemak(){
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.selectedTab.value = 0;
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}

function doBatalKemaskini(){
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

</script>
