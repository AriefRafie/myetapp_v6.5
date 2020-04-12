<style type="text/css">
<!--
.style1 {color: #0000FF}
.style4 {color: #FF0000; font-style: italic;}
.style5 {color: #000000}
.style3 {color: #FF0000}
.pautanms {color: #0000FF}
-->
</style>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'/>
<input type="hidden" name="idHakmilik" value="$idHakmilik" />
<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="negeri" value="$txtNamaNegeri" />

#parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")
<table width="99%" border="0">	
	<tr>
    	<td>
			<fieldset><legend>MAKLUMAT $!rizab_hakmilik_label</legend>
			    
				<table width="100%" border="0">
				       		<tr>
				            	<td width="50%" valign="top">
				                	<table width="100%" border="0">
				                    	<tr>
				  							<td width="1%" valign="top" >
								        	</td>				        
				                        	<td width="30%">
				                            	<div align="left">
				                            		<span class="labelinput">Kementerian</span>
				                            	</div>
				                        	</td>
				                  			<td width="1%" class="labelinput" >:</td>
				                   			<td width="68%" class="labelinput" >
				 								$txtNamaKementerian
				  							</td>
				                		</tr>
				                     	<tr>
				  							<td width="1%" valign="top" >
								        	</td>				        
				                        	<td width="30%">
				                            	<div align="left">
				                            		<span class="labelinput">No. Fail Seksyen</span>
				                            	</div>
				                        	</td>
				                  			<td width="1%" class="labelinput" >:</td>
				                   			<td width="68%" class="labelinput" >
				 								$txtNoFailSeksyen 
				  							</td>
				                		</tr>
				                    	<tr>
				  							<td width="1%" valign="top" >
								        	</td>				        
				                        	<td width="30%">
				                            	<div align="left">
				                            		<span class="labelinput">No. Fail PTG</span>
				                            	</div>
				                        	</td>
				                  			<td width="1%" class="labelinput" >:</td>
				                   			<td width="68%" class="labelinput" >
				 								$txtFailPTG 
				  							</td>
				                		</tr>
				                    	<tr>
				  							<td width="1%" valign="top" >
								        	</td>				        
				                        	<td width="30%">
				                            	<div align="left">
				                            		<span class="labelinput">Cara Perolehan</span>
				                            	</div>
				                        	</td>
				                  			<td width="1%" class="labelinput" >:</td>
				                   			<td width="68%" class="labelinput" >
				 								$txtTajuk
				  							</td>
				                		</tr>
				                 	</table>
				           		</td>
				                        	
				                <td valign="top">
				               		<table width="100%">
				                   	<tr>
				  							<td width="1%" valign="top" >
								        	</td>				        
				                        	<td width="30%">
				                            	<div align="left">
				                            		<span class="labelinput">Agensi</span>
				                            	</div>
				                        	</td>
				                  			<td width="1%" class="labelinput" >:</td>
				                   			<td width="68%" class="labelinput" >
				 								$txtNamaAgensi
				  							</td>
				                		</tr>
				              			
				            			<tr>
				  							<td width="1%" valign="top" >
								        	</td>				        
				                        	<td width="30%">
				                            	<div align="left">
				                            		<span class="labelinput">No. Fail KJP</span>
				                            	</div>
				                        	</td>
				                  			<td width="1%" class="labelinput" >:</td>
				                   			<td width="68%" class="labelinput" >
				 								$txtFailKJP
				  							</td>
				                		</tr>
				
				                    	<tr>
				  							<td width="1%" valign="top" >
								        	</td>				        
				                        	<td width="30%">
				                            	<div align="left">
				                            		<span class="labelinput">No. Fail PTD</span>
				                            	</div>
				                        	</td>
				                  			<td width="1%" class="labelinput" >:</td>
				                   			<td width="68%" class="labelinput" >
				 								$!txtFailPTD
				  							</td>
				                		</tr>
				
				                	</table>
				                </td>
				       		</tr>                    		
			
				</table>
			</fieldset>
    	</td>
   	</tr>	
   	
   	#if ($mode == "new" && $!SenaraiImej.size()>0 )
	<tr>
    	<td>	  
	       <fieldset>
	        <legend>SENARAI LAMPIRAN</legend>
			#if($!SenaraiImej.size()>10)		    
		     	<div style="width:100%;height:200;overflow:auto"> 
			#end	        
	     	<table>
	        #set ($xx = 0)
	        #foreach ($listlampiran in $SenaraiImej)
	        #set ($xx = $xx + 1)	       	
	        <tr>  
	        <!-- <td width="3%">$!xx</td> -->
	        <td  width="97%" colspan="2"> $!xx.
	 	        <a onClick="javascript:cetakImej('$listlampiran.idGambar')" 
	        		href="#" style="color:#0000FF">$listlampiran.namaFail</a>
	        		&nbsp;&nbsp;
	        	#if (!$!jenisAkses.equals('Readonly'))        		
	        	<a href="#" onClick="javascript:deleteDetailImej('$listlampiran.idGambar')">
	  	       	<img border="0" src="../img/delete.gif" /></a>
	  	       	#end
	        </td>
	       </tr>
	       #end
	       #if ($xx == 0)
	       <tr><td></td><td colspan="2">Tiada Lampiran</td></tr>
	       #end
	       </table>
			#if($!SenaraiImej.size()>10)		    
				</div>
			#end	        
			</fieldset>
    	</td>
   	</tr>	  
	#end

	<tr>
    	<td>
			<fieldset><legend>SENARAI IMEJ</legend>		
			#if($!SenaraiImejDist.size()>10)		    
		     	<div style="width:100%;height:200;overflow:auto"> 
			#end	
				<table width="100%" border="0">
	            	<tr class="table_header">
						<td width="3%">Bil.</td>
				   	  	<td width="87%">Ringkasan</td>
				      	<td width="10%">Tindakan</td>
		      		</tr>	
		      		#if($SenaraiImejDist.isEmpty())
		  	        <tr >
						<td width="3%"></td>
				   	  	<td width="97%" colspan=2 align="left">Tiada Rekod</td>
		      		</tr>	    			
		      		#end
		      	#foreach ($senarai in $SenaraiImej)
		  	  		#set( $i = $velocityCount )
		      		#if ( ($i % 2) != 1 )
		      			#set( $row = "row2" )
		      		#else
		      			#set( $row = "row1" )
		 			#end  
		  		
		  			<tr class="$row">
		    			<td>$senarai.bil</td>
		      		#if ($senarai.bil !="")
		      			<td>
		      				<a href="javascript:viewDetailImej('$senarai.idGambar')" class="style1">$senarai.ringkasan ($!senarai.namaFail)</a> 
		      				<!-- <a href="javascript:viewDetailInfo('$senarai.idHakmilik')" class="style1">$senarai.ringkasan</a> -->
		      				<!--<td><a href="javascript:deleteDetailImej('$senarai.idGambar')" class="style1"><div align="left"><img border="0" title ="Hapus Imej" src="../img/hapus.gif"/></div></a>-->
		      			</td>
		      			<td>
		      				#if (!$!jenisAkses.equals('Readonly'))
		      				<div align="center">
		      					<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick= "deleteDetailImej('$senarai.idGambar')">
		      				</div>
		      				#end
		      			</td>
		      
			  		#else
		      			<td colspan="3">$senarai.ringkasan</td>
		      		#end
		    		
		    		</tr>
				#end
			
				</table>
			#if($!SenaraiImejDist.size()>10)		    
				</div>
			#end					
			</fieldset>
    	</td>
   	</tr>  
   	
#if($mode=="view" || $mode=="kemaskini")
   	<tr>	
    	<td>
			<fieldset><legend>IMEJ</legend>
			<table width="100%" border="0">
			  	<tr>
			    	<td height>
			    		<div align="center">
			      		<p><img src="../servlet/ekptg.view.htp.FrmRekodDisplayImej?id=$idGambar" alt="Imej Pelan" border="1" width="250" height="250"/></p>
			      		</div>
			      	</td>
			  	</tr>
			  	<tr>
			    	<td>
			    		<div align="center">
			      			<input type="button" name="btnImejPenuh" id="btnImejPenuh" value="Imej Penuh" onclick="cetakImej($idGambar)" />
			     		</div>
			     	</td>
			  	</tr>			
			</table>
    		</fieldset>
	    </td>
	</tr>
		
#end 	
   	
   	<tr>
    	<td>
			<fieldset><legend>PERINCIAN IMEJ</legend>			    
				<table width="100%" border="0">
					<tr>
						<td>
							#parse ("app/htp/LinkMacGDI.jsp")						
						</td>
					</tr>
			  	#if($mode=="new")
					#if (!$!jenisAkses.equals('Readonly'))
			  		<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">##if ($mode != 'readonly') * #end </span></td>				        
						<td width="30%">
							<div align="right" class="labelinput">
							<div align="left">Bil. Lampiran</div>
							</div>
						</td>				      	
						<td width="1%">:</td>				        
						<td width="68%">
							<input type=text size=2 name=jumlahlampiran $!RO_General onBlur="doChangeJumlahLampiran('$!IDJadualLampiran',this,'$!action');" 
	              			value=$!num_files> (<font size=1 color=red>Sila masukkan jumlah lampiran</font>)
						</td>
					</tr>
					<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
						<td valign="top" width="30%">
							<div align="right" class="labelinput">
							<div align="left"> Direktori  </div>
							</div>
						</td>				      	
						<td valign="top" width="1%">:</td>				        
						<td width="68%">
							#foreach( $i in [1..$num_files] )							
							<input id="fileupload" name="fileupload" type="file" size="40" $readOnly  class="$disabled" /></br>
							#end
						</td>
					</tr>
							<tr>
						<td valign="top" width="1%">
							<span class="labelmandatory"></span></td>				        
						<td width="30%">
							<div align="right" class="labelinput">
							<div align="left"></div>
							</div>
						</td>				      	
						<td width="1%"></td>				        
						<td width="68%">
							<i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik imej adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz imej yang lebih kecil.</span></span>
						</td>
					</tr>
					#end
				#end
				
				#if (!$!jenisAkses.equals('Readonly'))
					<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%" valign="top">
							<div align="right" class="labelinput">
							<div align="left"> Perihal Imej</div>
							</div>
						</td>				      	
						<td width="1%" valign="top">:</td>				        
						<td width="68%">
							<textarea name="txtPerihalImej" cols="43" rows="5" class="$disabled" id="txtPerihalImej" $readOnly style="text-transform:uppercase;">$txtPerihalImej</textarea>
						</td>
					</tr>	
					<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%" valign="top">
							<div align="right" class="labelinput">
							<div align="left"> Keterangan Ringkas Imej  </div>
							</div>
						</td>				      	
						<td width="1%" valign="top">:</td>				        
						<td width="68%">
							<textarea name="txtRingkas" cols="43" rows="5" class="$disabled" id="txtRingkas" $readOnly  style="text-transform:uppercase;">$txtRingkas</textarea>
						</td>
					</tr>	
				#end
				#if($mode=="view") 
			  		<tr>
						<td valign="top" width="1%">
						</td>				        
						<td width="30%" valign="top">
							<div align="right" class="labelinput">
							<div align="left">Tarikh Kemaskini</div>
							</div>
						</td>				      	
						<td width="1%" valign="top">:</td>				        
						<td width="68%">
							<input name="txdTarikhKemaskiniImej" type="text" id="txdTarikhKemaskiniImej" value="$txdTarikhKemaskiniImej" size="11" maxlength="10" onkeyup="validateNumber(this,this.value);" $readonly class="disabled"/>
						</td>
					</tr>
			  	#end
				</table>
			</fieldset>
    	</td>
   	</tr>
   	
	<tr>
  		<td>
  					
		 <table align="center">
		  <tr>
		    <td colspan="4"  ><div >
			#if($mode =="new")
				#if (!$!jenisAkses.equals('Readonly'))
		    	<!-- <input class="stylobutton100" type="button" name="btnSave" id="btnSave" value="Simpan"  onclick="simpanDetailImej($idHakmilik)" />  -->
		    	<input class="stylobutton100" type="button" name="btnSave" id="btnSave" value="Simpan"  onclick="simpanDetailImej($idHakmilik)" />
				#end
		      	<input class="stylobutton100" type="button" name="btnBack" id="btnBack" value="Kembali" onclick="screen3($idHakmilik)" />
		  	#end
		    #if($mode=="kemaskini")
		      <input class="stylobutton100" type="button" name="btnUpdate" id="btnUpdate" value="Simpan" onclick="updateDetailImej($idGambar)" />
		      <input class="stylobutton100" type="button" name="btnReset" value="Batal" onclick="viewDetailImej($idGambar)"/>
		  	#end 
		   	#if($mode=="view") 
		      <input class="stylobutton100" type="button" name="btnKemaskini" id="btnKemaskini" value="Kemaskini" onclick="kemaskininDetailImej($idGambar)"/>
		      <input class="stylobutton100" type="button" name="btnReset" id="btnReset" value="Batal" onclick="baru($idHakmilik)"/>
		 	#end
		    </div></td>
		  </tr>
		 </table>
		
    	</td>
	</tr>   	
   	   	
   	<tr>
    	<td>

    	</td>
   	</tr>	
   	
</table>
#parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")


<script>
	var urljava = "ekptg.view.htp.rekod.FrmRekodTanah";
	/**
	*Diguna selepas Upload
	**/
	function viewDetailImej(id){		
		
		//document.${formName}.action = "?_portal_module="+urljava+"&firstAction=PendaftaranImej&nextAction=viewDetailImej&idGambar="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=viewDetailImej&idGambar="+id);

	}
	
	
	
	/**
	*Diguna selepas Upload
	**/
	function cetakImej(id){
		
		var url = "../servlet/ekptg.view.htp.FrmRekodDisplayImej?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function deleteDetailImej(id){
	
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		//document.${formName}.command.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id);
	}
</script>