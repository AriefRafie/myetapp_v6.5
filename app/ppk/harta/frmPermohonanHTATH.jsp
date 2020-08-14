
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<!--
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
-->
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}

.style42 {color: #0000FF}
.style43 {font-size: 10px; }
.style45 {color: #FF0000}
.style50 {font-size: 16px; color: #0000FF;}
.style53 {font-size: 9px}
.style54 {color: #000000}
.style55 {font-size: 10px; color: #FF0000; }
.style56 {font-size: 10}
.style57 {font-size: 10px; color: #000000; }
-->
</style>
</head>

<body onload="submitForm();daerah_harta();check_harta()" >
<form id="form1" name="f1" method="post" action="">
	<input type="hidden" name="v_tab" id="v_tab" value="" />
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'	/>
 	<input type="hidden" name=upload id="upload" value="$upload" /> 
	<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 	<input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
	<input name="load" id="load" type="hidden"/>
	<input type="hidden" name="paramOnline" id="paramOnline" value="$!paramOnline" />
#if($add_new=="yes")
        #set($radio2="yes")		
		#set($checked1="checked")        
        #set($radio3="")
		#set($checked2="")
		#set($checked3="")
		#set($idhta="")	
	    #set($noHakmilik="")		    
	    #set($idSimati="")
	    #set($nopt="")
	    #set($nilai_Hta_memohon="")
	    #set($nilai_Hta_mati="")
	    #set($kategori="")
	    #set($jenishakmilik="")
	    #set($pemilikan="1")	    
	    #set($negeri="")
	    #set($daerah="")	
	 	#set($mukim="")
	 	#set($luashmp="")
	    #set($luasasal="") 
		#set($nocagaran="")			    
	    #set($nopajakan="")	   	
	    #set($jenistanah="")	    
	    #set($basimati="")
	    #set($bbsimati="")
	    #set($jenishta="")
	    #set($tanggungan="")
	    #set($jenisluas="")	    
	    #set($catatan="")	
        #set($FLAG_DAFTAR="")
	    #set($noperserahan="")	    
	    #set($namapemaju="")
	    #set($alamatpemaju1="")
	    #set($alamatpemaju2="")
	    #set($alamatpemaju3="")	    
	    #set($poskodpemaju="")
	    #set($bandarpemaju="")	    
	    #set($negeripemaju="")
	    #set($alamathta1="")
	    #set($alamathta2="")
	    #set($alamathta3="")	    
	    #set($poskodhta="")
	    #set($bandarhta="")
        #set($negerihta="")
        #set($namaPelan="")
               
	    #set($noperjanjian="")
	    #set($tarikhperjanjian="")
	    #set($namarancangan="")
	    #set($noroh="ROH")
	    #set($nolot="")	   
	    #set($nocagaran="")
               
        #set($listBandarTetapbyNegeri="")
        #set($listBandarSuratbyNegeri="")
        #set($listMukimbyDaerah="")
        #set($listDaerahbyNegeri="")
         
#end

#if($HtaamviewX1=="yes")
    			#set($idhta="")
    		    #set($noHakmilik="")	    
    		    #set($idSimati="")
    		    #set($nopt="")
    		    #set($nilai_Hta_memohon="")
    		    #set($nilai_Hta_mati="")
    		    #set($kategori="")
    		    #set($jenishakmilik="")
    		    #set($pemilikan="1")    		    
    		    #set($negeri="")
    		    #set($daerah="")
    	     	#set($mukim="")
    	     	#set($luashmp="")
    		    #set($luasasal="") 
    			#set($nocagaran="")		    
    		    #set($nopajakan="")   		   	
    		    #set($jenistanah="")    
    		    #set($basimati="")
    		    #set($bbsimati="")
    		    #set($jenishta="")
    		    #set($tanggungan="")
    		    #set($jenisluas="")   		    
    		    #set($catatan="") 
                #set($FLAG_DAFTAR="")    		
    	        #set($noperserahan="")    	        
    	        #set($namapemaju="")
    	        #set($alamatpemaju1="")
    	        #set($alamatpemaju2="")
    	        #set($alamatpemaju3="")    	        
    	        #set($poskodpemaju="")
    	        #set($bandarpemaju="")    	        
    	        #set($negeripemaju="")
    	        #set($alamathta1="")
    	        #set($alamathta2="")
    	        #set($alamathta3="")    	        
    	        #set($poskodhta="")
    	        #set($bandarhta="")
                #set($negerihta="")
    	        #set($noperjanjian="")
    	        #set($tarikhperjanjian="")
    	        #set($namarancangan="")
    	        #set($noroh="ROH")
    	        #set($nolot="")    	        
    	        #set($nolot="")    	        
    	        #set($nocagaran="")
                
	#set($listBandarTetapbyNegeri="")
  	#set($listBandarSuratbyNegeri="")
	#set($listMukimbyDaerah="")
	#set($listDaerahbyNegeri="")
	
#end

#if($HtaamviewX2 == "yes")
                #set($idhta="")	
    		    #set($noHakmilik="")		    
    		    #set($idSimati="")
    		    #set($nopt="")
    		    #set($nilai_Hta_memohon="")
    		    #set($nilai_Hta_mati="")
    		    #set($kategori="")
    		    #set($jenishakmilik="")
    		    #set($pemilikan="1")    		    
    		    #set($negeri="")
    		    #set($daerah="")	
    	     	#set($mukim="")	   		
    	        #set($luashmp="")
    		    #set($luasasal="") 
    			#set($nocagaran="")			    
    		    #set($nopajakan="")
    		    #set($jenistanah="3")	    
    		    #set($basimati="")
    		    #set($bbsimati="")
    		    #set($jenishta="")
    		    #set($tanggungan="")
    		    #set($jenisluas="")
    		    #set($catatan="")
                #set($FLAG_DAFTAR="")	
    	        #set($noperserahan="")
    	        #set($namapemaju="")
    	        #set($alamatpemaju1="")
    	        #set($alamatpemaju2="")
    	        #set($alamatpemaju3="")
    	        #set($poskodpemaju="")
    	        #set($bandarpemaju="")
    	        #set($negeripemaju="")
    	        #set($alamathta1="")
    	        #set($alamathta2="")
    	        #set($alamathta3="")
    	        #set($poskodhta="")
                #set($negerihta="")
    	        #set($bandarhta="")
    	        #set($noperjanjian="")
    	        #set($tarikhperjanjian="")
    	        #set($namarancangan="")
    	        #set($noroh="ROH")
    	        #set($nolot="")
    	        #set($nocagaran="")
                
	#set($listBandarTetapbyNegeri="")
	#set($listBandarSuratbyNegeri="")
	#set($listMukimbyDaerah="")
	#set($listDaerahbyNegeri="")
	
#end

#if($HtaamviewX3 == "yes")
	#set($idhta="")	
    	#set($noHakmilik="")		    
    	#set($idSimati="")
    	#set($nopt="")
    	#set($nilai_Hta_memohon="")
    	#set($nilai_Hta_mati="")
    	#set($kategori="")
    	#set($jenishakmilik="")
    	#set($pemilikan="1")
    	#set($negeri="")
    	#set($daerah="")	
    	#set($mukim="")
    	#set($luashmp="")
    	#set($luasasal="") 
    	#set($nocagaran="")			    
    	#set($nopajakan="")	
    	#set($jenistanah="")	    
    	#set($basimati="")
    	#set($bbsimati="")
    	#set($jenishta="")
    	#set($tanggungan="")
    	#set($jenisluas="")
    	#set($catatan="")	
        #set($FLAG_DAFTAR="")
    	#set($noperserahan="")
    	#set($namapemaju="")
    	#set($alamatpemaju1="")
    	#set($alamatpemaju2="")
    	#set($alamatpemaju3="")
    	#set($poskodpemaju="")
    	#set($bandarpemaju="")
    	#set($negeripemaju="")
    	#set($alamathta1="")
    	#set($alamathta2="")
    	#set($alamathta3="")
    	#set($poskodhta="")
    	#set($bandarhta="")
        #set($negerihta="")
    	#set($noperjanjian="")
    	#set($tarikhperjanjian="")
    	#set($namarancangan="")
    	#set($noroh="ROH")
    	#set($nolot="")
    	#set($nolot="")
    	#set($nocagaran="")
    	#set($jeniskepentingan="")
	#set($listBandarTetapbyNegeri="")
	#set($listBandarSuratbyNegeri="")
   	#set($listMukimbyDaerah="")
    #set($listDaerahbyNegeri="")

#end
	#parse("app/ppk/paging_sek8.jsp") 
	<input name="eventStatus" id="eventStatus" type="hidden" />
	#parse("app/ppk/bil_fail.jsp") 
	
	<table width="100%" border="0">
		<input type="hidden" name='command'>
 		<input type="hidden" name="mode" value="">
 		<input type="hidden" name="tabIdatas" id="tabIdatas" value="$selectedTabatas"/>
 		<input type="hidden" name="tabIdtengah" id="tabIdtengah" value="$selectedTabtengah"/>
 		<input type="hidden" name="tabIdbawah" id="tabIdbawah" value="$selectedTabbawah"/>
 		<input type="hidden" name="tabIdtepi" id="tabIdtepi" value="$selectedTabtepi"/>
 		<input type="hidden" name="flagFromSenaraiFailSek8" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>

	#foreach($list in $View)
	    #set ($id = $list.idPermohonan)
	    #set ($idPemohon = $list.idPemohon)
	    #set ($idSimati = $list.idSimati)
	    #set($id_Status = $list.id_Status)
	    
	    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
	    <input name="idPermohonan" type="hidden"  value="$id" id="idPermohonan"/>
	    <input name="idpermohonan" type="hidden"  value="$id"/>
	  	<input name="idPemohon" type="hidden"  value="$idPemohon"/>
	  	<input name="idSimati" type="hidden"  value="$idSimati" id="idSimati"/>
	  	<input name="idtemp" type="hidden"  value="$id" />
		<input name="jpphlepas" type="hidden"  value="$list.jpphlepas"/>
		<input name="id_Permohonansimati" type="hidden"  value="$list.id_Permohonansimati" id="id_Permohonansimati"/>
		<input name="id_Fail" type="hidden" value="$list.idFail" />
	 
		#set($listnoFail = $list.noFail)
		#set($listidnegeri = $list.idnegeri)
		#set($listnamadaerah = $list.namadaerah)
		#set($listnamaPejabat = $list.namaPejabat)
		#set($listketerangan = $list.keterangan)
		#set($listseksyen = $list.seksyen)
		#set($listtarikhMohon = $list.tarikhMohon)
		#set($listnamaSimati = $list.namaSimati )
		#set($listnamaPemohon = $list.namaPemohon)
		#set($listtarikhMohon = $list.tarikhMohon)
		#set($listidSimati = $list.idSimati)
		#set($id_PermohonanSimati = $list.id_Permohonansimati)
	 	#set($idPermohonanSimati = $list.id_Permohonansimati)
	 
	#end
		<tr>
			<td>
		#parse("app/ppk/maklumat_sek8.jsp")	
		
		#set($md=$listtarikhMohon)
         		<input type="hidden" name="txtSeksyen" value="$listseksyen" readonly="true"/>
     			<input type="hidden" name="txdTarikhMohon" id="txdTarikhMohon" value="$listtarikhMohon" />
       			<input type="hidden" name="txtNamaPemohon" value="$listnamaPemohon" readonly="true"/>
				<!-- <input type="hidden" name="idSimati" value="$listidSimati" readonly="true"/> -->
			</td>
		</tr>
		
		<tr>
    		<td>
    			<div id="TabbedPanels1" class="TabbedPanels">
    				<ul class="TabbedPanelsTabGroup">
			      		<li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()" >PERMOHONAN</li>
			      		<li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(1,0,0,0);HtaamView('$!paramOnline')">HARTA TAK ALIH</li>
			      		<li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(2,0,0,0);HAview()" >HARTA ALIH</li>
      
       	#if($!skrin_online != "yes")
      					<li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" >NILAIAN HARTA</li>
     	#else
      		#if($!hideTabPengesahan == "show")
      					<li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(3,0,0,0);NAview()" >PENGESAHAN PERMOHONAN</li>
      		#end
      	#end
     				</ul>
    				<div class="TabbedPanelsContent">
    					<div class="TabbedPanelsContentGroup">
							<div id="TabbedPanels2" class="TabbedPanelsContentVisible">        
	          					<div class="TabbedPanelsContentGroup">
	            					<div ></div>            
		            				<div>
			          					<div id="TabbedPanels3" >
			            					<div class="TabbedPanelsContentGroup">
			                					<div></div>
			                   					<div></div>
			                				</div>
			          					</div>
		            				</div>	            
				            		<div ></div>
					       			<div ></div>
					       			<div ></div>
					      			<div ></div>
					      			<div ></div>
				     			</div>
				  			</div>		
      					</div>	
     				
		     			<div class="TabbedPanelsContentVisible">
		        			<div id="TabbedPanels4" class="TabbedPanels">
		          					<ul class="TabbedPanelsTabGroup">
						            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(1,0,0,0);HtaamView('$!paramOnline')">HARTA TAK ALIH (ADA HAKMILIK)</li>
						            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(1,0,0,1);HtaamViewX('$!paramOnline')" id="maklumat_pemohon">HARTA TAK ALIH (TIADA HAKMILIK)</li>
						          	</ul>
		          				<div class="TabbedPanelsContentGroup">
		            				<div ></div>
		            				<div class="TabbedPanelsContent"> 
				#parse("app/ppk/info_berjaya_disimpan.jsp")
		      				       		<table width="100%"  border="0">
           		<!-- Mula Simpan-->
           		#if($show_htaa_add_table=="yes")
          								<tr>
						                	<td>
						                		<table width="100%" border="0">
						                       		<tr>
						                            	<td>
						                              	<fieldset><legend>HARTA TAK ALIH(TIADA HAKMILIK)</legend>
						                                	<input name="noradio" type="hidden" />
						                                   	<table width="70%" align="center">
										                        <tr>
				                                        			<td><span class="style36">
					                                              		<input name="radioHtaamViewX" id="radioHtaamViewX1" type="radio" onclick="setSelected(1,0,0,1);HtaamViewX1()"   $checked1 value="1" />
					                                                   
					                                                  Perjanjian Jual Beli</span></td>
					                                          	</tr>
					                                   			<tr>
					                                                <td><span class="style36">
					                                        			<input type="radio" name="radioHtaamViewX"  $checked2 id="radioHtaamViewX2" onclick="setSelected(1,0,0,1);HtaamViewX2()" value="2" />
					                                   
					                                                  Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</span></td>
					                                         	</tr>
					                                       		<tr>
					                                           		<td><span class="style36">
					                                              	<input type="radio" name="radioHtaamViewX" id="radioHtaamViewX3" onclick= "setSelected(1,0,0,1);HtaamViewX3()" $checked3 value="3" />
					                                              
					                                                  Kepentingan Lain- lain</span></td>
					                                          	</tr>
						                 					</table>
						                 				</fieldset> 
						                 				</td>
						               				</tr>                     							                 				
						                 		</table>
						                	</td>
						               </tr>     
                                  
         		<tr>
               		<td>
                   		<fieldset>
                      	<table width="100%" border="0">
                       		<tr>
                         		<td width="50%" valign="top"><table width="100%" border="0">
                               		<tr>
                                  		<td valign="top"><span class="style55">*</span></td>
                                    	<td width="29%"><div align="right" class="style43 style54">
                                      		<div align="left">Negeri</div>
                                      	</div></td>
                                    	<td width="1%"><div align="right">:</div></td>
                                   		<td width="70%">$!socNegeri</td>
                               		</tr>
                                   	
                                   	<tr>
                                 		<td valign="top"><span class="style55">*</span></td>
                                     	<td><div align="right" class="style43" >
                                         	<div align="left">Daerah</div>
                                      	</div></td>
                                      	<td><div align="right">:</div></td>
                                    	<td>$!socDaerah<span id="check_daerah_harta" style="color:red" ></span></td>
                                	</tr>
                                            
                                 	<tr>
                             			<td valign="top"><span class="style55">*</span></td>
                                      	<td><div align="right" class="style43" >
                                                <div align="left">Mukim</div>
                                   		</div></td>
                                   		<td><div align="right">:</div></td>                                           
                                      	<td>$!socMukim</td>
                                	</tr>                                            
                      		#if($radio2=="yes")                                   	
                                   	<tr>
                           				<td>&nbsp;</td>
                                       	<td><div align="right" class="style43">
                                              <div align="left">Alamat Harta</div>
                                     	</div></td>
                                    	<td><div align="right">:</div></td>
                                   		<td><input name="txtAlamatHarta1HtaamX" type="text" id="textfield57" value="$alamathta1" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                	</tr>
                                          
                                  	<tr>
                                  		<td>&nbsp;</td>
                                    	<td><div align="left"><span class="style43"></span></div></td>
                                       	<td>&nbsp;</td>
                                      	<td><input name="txtAlamatHarta2HtaamX" type="text" id="textfield58" size="34" $readmode value="$alamathta2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" /></td>
                                	</tr>
                                 	
                                 	<tr>
                                   		<td>&nbsp;</td>
                                        <td><div align="left"><span class="style43"></span></div></td>
                                   		<td>&nbsp;</td>
                                      	<td><input name="txtAlamatHarta3HtaamX" type="text" id="textfield59" value="$alamathta3" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                                 	</tr>
                                 	
                                 	<tr>
                               			<td>&nbsp;</td>
                                   	    <td><div align="right" class="style43">
                                              <div align="left">Poskod</div>
                                      	</div></td>
                                      	<td><div align="right">:</div></td>
                                   		<td><input name="txtPoskodHartaHtaamX" type="text" id="textfield60" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodHartaHtaamX')" value="$poskodhta" size="5" maxlength="5" $readmode/></td>
                             		</tr>
                             		
                           			<tr>
		                            	<td class="style38 style43 style45">&nbsp;</td>
		                            	<td class="style38"><div align="right" class="style38 style43">
		                                      <div align="left"><span class="style40"> Bandar</span></div>
		                            	</div></td>
		                            	<td><div align="right"><span class="style38">:</span></div></td>
		                            	<td>$!socBandar</td>
		                      		</tr>
                      		#end
                          			<tr>
                                              <td>&nbsp;</td>
                                              <td><div align="right" class="style43">
                                                <div align="left">
                                    	#if($radio3 == "yes")
                                        	Agensi
                                       	#else
                                        	Nama Pemaju
                                      	#end
                                                </div>
                                              </div></td>
                                             <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNamaPemajuHtaamX" type="text" id="txtNamaPemajuHtaamX" value="$namapemaju" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                              </label></td>
                               		</tr>
                              		
                              		<tr>
                                            	<td>&nbsp;</td>
                                       			<td><div align="right" class="style43">
                                        			<div align="left">
                                     	#if($radio3 == "yes")
                                           Alamat Agensi
                                    	#else
                                      		Alamat Pemaju
                                     	#end
                                        			</div>
                                       			</div></td>
                                              	<td><div align="right">:</div></td>
                                              	<td><label>
                                                	<input name="txtAlamatPemaju1HtaamX" type="text" id="textfield20" value="$alamatpemaju1" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
                                 	</tr>
                             		
                             		<tr>
                                              <td>&nbsp;</td>
                                              <td><div align="left"><span class="style43"></span></div></td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju2HtaamX" type="text" id="textfield53" value="$alamatpemaju2" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                              </label></td>
                                	</tr>
                                 	
                                 	<tr>
                                              <td>&nbsp;</td>
                                              <td><div align="left"><span class="style43"></span></div></td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju3HtaamX" type="text" id="textfield54" value="$alamatpemaju3" size="34" $readmode style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                              </label></td>
                                   	</tr>
                                  	
                                  	<tr>
                                              <td>&nbsp;</td>
                                              <td><div align="right" class="style43">
                                                <div align="left">Poskod</div>
                                              </div></td>
                                             <td><div align="right">:</div></td>
                                            
                                              <td><input name="txtPoskodPemaju1HtaamX" type="text" id="textfield55" value="$poskodpemaju" size="5" maxlength="5" $readmode style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPemaju1HtaamX')"/></td>
                                 	</tr>
                                	
                                	<tr>
                                             	<td>&nbsp;</td>
                                              	<td><div align="right" class="style43">
                                                	<div align="left">Negeri</div>
                                              	</div></td>
                                              	<td><div align="right">:</div></td>
                                              	<td>$!socNegeriAdd</td>
                                	</tr>
                               		
                               		<tr>
	                                       		<td class="style38 style43 style45">&nbsp;</td>
			                                    <td class="style38"><div align="right" class="style43">
			                                 		<div align="left">Bandar</div>
			                                    </div></td>
			                                    <td><div align="right"><span class="style38">:</span></div></td>
			                                    <td>$!socBandarAdd</td>
		                     		</tr>
                                #if($radio2=="yes")
                       				<tr>
	                                            <td>&nbsp;</td>
	                                            <td><div align="right" class="style43">
	                                              <div align="left">No Perjanjian</div>
	                                            </div></td>
	                                           <td><div align="right">:</div></td>
	                                            <td><input name="txtNoPerjanjianHtaamX" type="text" id="textfield62" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$noperjanjian" size="34" maxlength="40" $readmode/></td>
	                          		</tr>
                                          	
              						<tr>
                                            <td>&nbsp;</td>
                                            <td><div align="right" class="style43">
                                              <div align="left">Tarikh Perjanjian</div>
                                            </div></td>
                                           <td><div align="right">:</div></td>
                                            <td><span class="style36">
                                              <input name="txtTarikhPerjanjianHtaamX" id="txtTarikhPerjanjianHtaamX" type="text" $readmode value="$tarikhperjanjian" size="10"  onblur="trans_date(this.value)" />
                                              <a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>                                            </td>
                              		</tr>
                        	#end
                          	#if($radio3=="yes")
                            		<tr>
                                            <td>&nbsp;</td>
                                                        <td><div align="right" class="style43">
                                                          <div align="left">Nama Rancangan</div>
                                                        </div></td>
                                                        <td><div align="right">:</div></td>
                                            <td><label>
                                                          <input name="txtNamaRancanganHtaamX" type="text" id="textfield63" value="$namarancangan" size="34" $readmodevalue="$namarancangan" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
                                                        </label></td>
                                	</tr>
                                 	
                                 	<tr>
                                                        <td>&nbsp;</td>
                                                        <td><div align="right" class="style43">
                                                          <div align="left">No ROH</div>
                                                        </div></td>
                                                        <td><div align="right">:</div></td>
                                                        <td><input name="txtNoRohHtaamX" type="text" id="txtNoRohHtaamX" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$noroh" size="34" maxlength="50" $readmode/></td>
                                	</tr>
                                 	
                                 	<tr>
                                                        <td>&nbsp;</td>
                                                        <td><div align="right" class="style43">
                                                          <div align="left">Lot ID</div>
                                                        </div></td>
                                                        <td><div align="right">:</div></td>
                                                        <td><input name="txtLotIdHtaamX" type="text" id="txtLotIdHtaamX" $readmode value="$nolot" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
                             		</tr>
                      		#end
                       		#if($radio3 != "yes" && $radio2 != "yes")  
                         			<tr>
                                                        <td valign="top">&nbsp;</td>
                                                      <td valign="top"><div align="right" class="style43">
                                                        <div align="left">Jenis Kepentingan</div>
                                                      </div></td>
                                                      <td valign="top"><div align="right">:</div></td>
                                                      <td>
                                                      <textarea name="txtJenisKepentinganX" id="txtJenisKepentinganX" cols="31" rows="5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()">$jeniskepentingan</textarea>                                                      </td>                                                      
                          			</tr>
                          	#end
                                          
                               	</table>
                        	</td>
                   			                   			
                   			<td width="50%" valign="top"><table width="100%" border="0">
                          		<tr>
                                              <td valign="top"><span class="style55">*</span></td>
                                              <td width="29%"><div align="right" class="style57">
                                                <div align="left">Bahagian Simati</div>
                                              </div></td>
                                              
                                             <td width="1%"><div align="right">:</div></td>
                                              <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati7" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati1X')" style="text-align:right;text-transform:uppercase;" value="$basimati" size="14" maxlength="14" $readmode  onblur="bahagiansimati()" />
                                                /
                                                <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati8" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati2X')" style="text-align:left;text-transform:uppercase;" value="$bbsimati" size="14" maxlength="14" $readmodevalue="$bbsimati" onblur="bahagiansimati()" /></td>
                     			</tr>
                                            
                      			<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Kategori Tanah</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>
                                         	#if($kategori == "2")
                                    			#set($meterhektar = "Hektar")
                                  			#elseif($kategori == "1" || $kategori == "3" || $kategori == "4" || $kategori == "5" || $kategori == "6")
                                    			#set($meterhektar = "Meter Persegi")
                                    		#else
                                      			#set($meterhektar = "")
                                    		#end
                                    		$!socKaTanah                                             
                                              </td>
                             	</tr>
                                    	
                     			<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57"> 
                                                <div align="left">Luas Asal</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>
                              	#foreach($listluashta in $listluas)            
                                	#if($jenisluas==$listluashta.id)
                                    	#set($listluasK=$listluashta.kod)
                                    	#set($listluasN=$listluashta.nama)
                                  	#end 
                                 #end
                                                #if($jenisluas!="")
                                                	<input name="txtLuasAsalHtaamX" type="text"  class="disabled" id="txtLuasAsalHtaamX" style="text-transform:uppercase;"  onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamX')" value="$luasasal" size="15" maxlength="15" readonly="readonly"  />
                                                
                                                #else
                                                	<input name="txtLuasAsalHtaamX" type="text"  class="disabled" id="txtLuasAsalHtaamX" style="text-transform:uppercase;"  onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamX')"  value="$luasasal" size="15" maxlength="15" readonly="readonly" />
                                                #end 
                                	</td>
                            	</tr>
                                   		
                        		<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57"> 
                                                <div align="left">Jenis Luas</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>$!socLuas</td>
                             	</tr>
                                            
                             	<tr id="tr_luasharta" style="display:none;">
                                             <td>&nbsp;</td>
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td>
                                              <span id="luas1" style="display:none;">
                                              <input name="txtLuasAsalHtaam1" type="text" id="txtLuasAsalHtaam1" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam1')"   />
                                              </span>
                                              <span id="luas2" style="display:none;">
                                               <input name="txtLuasAsalHtaam2" type="text" id="txtLuasAsalHtaam2" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam2')"   />
                                               </span>
                                               <span id="luas3" style="display:none;">
                                                <input name="txtLuasAsalHtaam3" type="text" id="txtLuasAsalHtaam3" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam3')"  />
                                                </span>                                              </td>
                       			</tr>
                            	
                            	<tr id="tr_luasharta_b" style="display:none;">
                                              <td>&nbsp;</td>
                                              <td class="style38">&nbsp;</td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input type="button" name="button2" id="button2" value="Convert" onclick="ConvertLuasHarta('socJenisLuasHtaamX','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','socKategoriTanahHtaamX')" />
                                              </label></td>
                         		</tr>
                                            
                           		<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Luas (Hektar/MP)</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtLuasHMpHtaamX" type="text" id="txtLuasHMpHtaamX" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtLuasHMpHtaamX')" value="$luashmp" size="15" maxlength="15" $readmode  />
                                              <input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" /></td>
                           		</tr>
                                            
                         		<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Nilai Tarikh Mohon (RM)</div>
                                              </div></td>
                                            <td><div align="right">:</div></td>
                                              <td>
                                              
                                        	#if($nilai_Hta_memohon=="")                    
                                              	#set($n2="")
                                         	#elseif(($nilai_Hta_memohon==0) || (""+$nilai_Hta_memohon.equals("0.0")))
                                              	#set($n2="0.00")                                                                                   
                                         	#else
                                          		#set($n2=$nilai_Hta_memohon)                                           
                                         	#end
                                               
                                                 <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaaX" $readmode value="$n2" onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaaX')" onblur="validateModal(this,this.value,'$n2')" />                                              </td>
                          		</tr>
                                            
                         		<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Nilai Tarikh Mati (RM)</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>
                                        	#if($nilai_Hta_mati=="")
                                              	#set($n1="")
                                         	#elseif(($nilai_Hta_mati==0) || (""+$nilai_Hta_mati.equals("0.0")))
                                              	#set($n1="0.00")
                                           	#else
                                              	#set($n1=$nilai_Hta_mati)                                             
                                          	#end
                                           
                                               <input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamX" $readmode value="$n1"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamX')"   onblur="validateModal(this,this.value,'$n1');" />                                            </td>
                       			</tr>
                                            
                       			<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">No Cagaran</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtNoCagaranX" type="text" id="txtNoPajakan5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$nocagaran" size="15" maxlength="12" $readmode/></td>
                        		</tr>
                                  		
                              	<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">No Pajakan</div>
                                              </div></td>
                                             <td><div align="right">:</div></td>
                                              <td><input name="txtNoPajakanX" type="text" id="txtNoPajakan6" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$nopajakan" size="15" maxlength="12" $readmode/></td>
                             	</tr>
                                      	
                        		<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Status Pemilikan</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>$!socPB</td>
                   				</tr>
                                            
                          		<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Tanggungan</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtTanggunganHtaamX" type="text" id="txtTanggunganHtaam4" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$tanggungan" size="15" maxlength="15"/></td>
                             	</tr>
                                            
                           		<tr>
                                              <td><span class="style56"></span></td>
                                              <td><div align="right" class="style57">
                                                <div align="left">Jenis Tanah</div>
                                              </div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listtan in $listtanah)
                                                
                                                #if($jenistanah==$listtan.id)
                                                
                                                #set($listtanK=$listtan.kod)
                                                #set($listtanN=$listtan.keterangan)
                                                 
                                                #end 
                                                #end
                                                #if($jenistanah!="" && $jenistanah!="0")
                                                  <select name="socJenisTanahHtaamX" class="autoselect" $readmode id="socJenisTanahHtaam" style="text-transform:uppercase;" onblur="uppercase()">
                                              
                                               
                                               #if($jenistanah=="3")
                                                  
                                        
                                        <!--<option value="1">TANAH RIZAB</option>  -->
                                        <!-- <option value="2">TANAH ADAT</option>  -->
                                        <option value="3">TANAH GSA</option>
                                        <option value="4">BUKAN TANAH GSA</option>
                                        
                                        
                                                  
                                              #end
                                               #if($jenistanah=="4")
                                                
                                        <!-- 
                                        <option value="2">TANAH ADAT</option>
                                        <option value="1">TANAH RIZAB</option>
                                         -->
                                        <option value="4">BUKAN TANAH GSA</option>
                                        <option value="3">TANAH GSA</option>
                                        
                                                  
                                              #end
                                  </select>
                                                #else
                                                <select name="socJenisTanahHtaamX" class="autoselect" $readmode id="socJenisTanahHtaamX" style="text-transform:uppercase;" onblur="uppercase()">
                                                  <option value="0">Sila Pilih Jenis Tanah</option>
                                                  <!-- 
			                                        <option value="1">TANAH RIZAB</option>
			                                        <option value="2">TANAH ADAT</option>
			                                         -->
			                                        <option value="3">TANAH GSA</option>
			                                        <option value="4">BUKAN TANAH GSA</option>
                                                </select>
                                                #end </td>
                        		</tr>
                                		
                            	<tr>
                                              <td valign="top"><span class="style56"></span></td>
                                              <td valign="top"><div align="right" class="style57">
                                                <div align="left">Catatan</div>
                                              </div></td>
                                              <td valign="top"><div align="right">:</div></td>
                                              <td><textarea name="txtCatatanHtaamX" id="txtCatatanHtaamX" cols="31" rows="5"  >$catatan</textarea></td>
                            	</tr>
                                            
                        		<tr id="tr_flag_daftar"  style="display:none">
                                        	<td></td>
                                          	<td  valign="top">Urusan Kemasukkan Maklumat Harta</td>
                                          	<td valign="top">:</td>
                                          	<td valign="top">
                                          
                                          
                                          #if($readmode != "disabled" )
                                          
                                          #if($FLAG_DAFTAR == '1')
                                          #set($checked_flag_daftar1 = "checked")
                                          #set($checked_flag_daftar2 = "")   
                                          #elseif($FLAG_DAFTAR == '2') 
                                          #set($checked_flag_daftar2 = "checked")    
                                          #set($checked_flag_daftar1 = "") 
                                          #else
                                          #set($checked_flag_daftar1 = "checked")
                                          #set($checked_flag_daftar2 = "")                                   
                                          #end
                                          
                                          
                                          		<input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar1 id="FLAG_DAFTAR" 
                                          value="1" /> Pendaftaran
                                          <br />                                          
                                          <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar2 id="FLAG_DAFTAR" 
                                          value="2" /> Perbicaraan
                                          
                                          
                                          #else
                                          
                                          #set($text_daftar = "")
                                          #if($FLAG_DAFTAR == '1')
                                          #set($text_daftar = "PENDAFTARAN")
                                          #elseif($FLAG_DAFTAR == '2') 
                                          #set($text_daftar = "PERBICARAAN")                                         
                                          #end
                                          
                                          <input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$readmode" />                                          
                                          <input type="hidden" name="FLAG_DAFTAR" id="FLAG_DAFTAR" value="$FLAG_DAFTAR"  />
                                           
                                          #end
                                          
                                          
                                          	</td>
                      			</tr>                                         
                               		#if($!skrin_online != "yes")                         
                                   		<script>
                                      		document.getElementById('tr_flag_daftar').style.display = "";
                                      	</script>
                                 	#end 
                        	</table>
                      	</td>
                	</tr>
            	</table>
                     
          		<table width="100%">
                     	<tr>
                        	<td><span class="style69 style47 style5 style53"><em><span class="style45">Perhatian</span> : Sila  pastikan label bertanda <span class="style45">*</span> diisi.</em></span></td>
                   		</tr>
				</table>                                   
         	</td>
		</tr>						                               	
				#end
				<!-- End Simpan-->	
					
				<!-- Mula Kemaskini-->	
				#if($show_htaa_update_table=="yes")
				#foreach($listam in $listHTAXid)
							 			<tr>
							      			<td>
							            	<fieldset>
							#if($!skrin_online != "yes") 
								#if($readmode == "disabled")
									#set($readmodeR = "readonly")
								#else
									#set($readmodeR = "")
								#end
							#else
								#if($open_button_online == "no")
									#set($readmodeR = "readonly")
									#set($readmode = "disabled")
									#set($readmodenegeri = "disabled")
									#set($readmodedaerah = "disabled")
									#set($readmodemukim = "disabled")
								#else
									#set($readmodeR = "")
									#set($readmode = "")
									#set($readmodenegeri = "")
									#set($readmodedaerah = "")
									#set($readmodemukim = "")
								#end
							#end
												<input type="hidden" name="flag" value="$!listam.flag">
							#if($readmode == "disabled")
							     				<table width="90%" align="center">
								#set($idhta = $listam.idhta)
							  	#if($listam.flag=="1")
									           		<tr>                                       
									           			<td><div align="center" class="style50"><span  style="text-transform:uppercase;"  >
									               		PERJANJIAN JUAL BELI                                     
									              		</span></div></td>
									        		</tr>	
							  	#end
						                                      
						       	#if($listam.flag=="2")
										      		<tr>
										            	<td><div align="center" class="style42"  style="text-transform:uppercase;; font-size: 16px;">Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</div></td>
										        	</tr>
						      	#end
						   		#if($listam.flag=="3")
										          	<tr>
										           		<td><div align="center" class="style42"  style="text-transform:uppercase;; font-size: 16px;">
										             	Kepentingan Lain- lain                                           
										          		</div></td>
										       		</tr>
						     	#end
						  						</table>
						   	
						   	#else						                                   
									<!-- penambahbaikkan skrin harta tak alih -->
								#if($listam.flag=="1")
							   		#set($checked1="checked")
							     	#set($checked2="")
							      	#set($checked3="")
								#end
							                                   
								#if($listam.flag=="2")
							   		#set($checked2="checked")
							   		#set($checked1="")
							       	#set($checked3="")
								#end
					                                   
						       	#if($listam.flag=="3")
						        	#set($checked3="checked")
						         	#set($checked2="")
						        	#set($checked1="")
						      	#end
						                                   
						     	#if($listam.jenishta=="Y")
						        	#set($radioJenisHTA_update_checked1="checked")
						          	#set($radioJenisHTA_update_checked2="")
						      	#end
						                                    
						      	#if($listam.jenishta=="T")
						        	#set($radioJenisHTA_update_checked2="checked")
						        	#set($radioJenisHTA_update_checked1="")
						      	#end
                                   
      									<input type="hidden" name="nama_skrin" id="nama_skrin" value="tiadahakmilik"  />
						       				<table width="85%" align="center">
							                	<tr>
							                    	<td width="40%">
							                        	<span class="style36">
							 								<input name="radioJenisHTA_update" id="radioJenisHTA_update" type="radio" onclick="setSelected(1,0,0,1);check_jenis_hta1();tukarjenis_Htaam();" $radioJenisHTA_update_checked1 value="1" />
															<font color="BLUE" ><b>HARTA TAK ALIH (ADA HAKMILIK)</b></font>
							 							 </span>
							                     	</td>
								                   	<td  width="60%">
								                    	<span class="style36">
								 							<input name="radioJenisHTA_update" id="radioJenisHTA_update" type="radio" onclick="setSelected(1,0,0,1);check_jenis_hta2();tukarjenis_HtaamX();" $radioJenisHTA_update_checked2 value="2" />
								 							<font color="BLUE" ><b>HARTA TAK ALIH (TIADA HAKMILIK)</b></font>
								 						</span>
								                   </td>
								         		</tr>
							                  	<tr>
							                       	<td></td>
							                  		<td>
							                       		<input type="hidden" name="flag_tukar_jenis_hta" id="flag_tukar_jenis_hta" value="">
							                     	<fieldset> 
										            	<table width="96%"  align="right">
							                          		<tr>
							                                	<td><span class="style36">
							                                    	<input name="radioHtaamViewX_update" id="radioHtaamViewX_update" type="radio" onclick="setSelected(1,0,0,1);tukarjenis_HtaamX()"   $checked1 value="1" />
							                                        	Perjanjian Jual Beli</span>
							                                	</td>
							                        		</tr>
                                              
							                              	<tr>
							                                                <td><span class="style36">
							                                        <input type="radio" name="radioHtaamViewX_update"  $checked2 id="radioHtaamViewX_update" onclick="setSelected(1,0,0,1);tukarjenis_HtaamX()" value="2" />
							                                   
							                                                  Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</span></td>
							                             	</tr>
							                          		
							                          		<tr>
							                                                <td><span class="style36">
							                                              <input type="radio" name="radioHtaamViewX_update" id="radioHtaamViewX_update" onclick= "setSelected(1,0,0,1);tukarjenis_HtaamX()" $checked3 value="3" />
							                                              
							                                                  Kepentingan Lain- lain</span></td>
							                             	</tr>
                          								</table>
								         		  	</fieldset>
            										</td>
           										</tr>
           									</table>
							#end 		
										</fieldset>
										</td>
									</tr>
					
								   	<tr>                                  
								       	<td>
								     	<script>
							 function check_jenis_hta1(){
								 if(document.f1.radioJenisHTA_update[0].checked == true){
									 document.f1.radioHtaamViewX_update[0].checked = false;
									 document.f1.radioHtaamViewX_update[1].checked = false;
									 document.f1.radioHtaamViewX_update[2].checked = false;
									 document.f1.radioJenisHTA_update[1].checked = false;
								 }
							 }
							 function check_jenis_hta2(){
								 if(document.f1.radioJenisHTA_update[1].checked == true) {
									 document.f1.radioHtaamViewX_update[0].checked = true;
									 document.f1.radioHtaamViewX_update[1].checked = false;
									 document.f1.radioHtaamViewX_update[2].checked = false;
									 document.f1.radioJenisHTA_update[0].checked = false;
								 }
							 }
										</script>	
										<fieldset><legend>HARTA TAK ALIH (TIADA HAKMILIK) </legend>
           									<table width="100%">                                       		
							              		<tr>
							                   		<td width="50%" valign="top"> 
							                   			<input type="hidden" name="idhtaamid" value="$listam.idhta" />
							                   			<table width="100%">
							                        		<tr>
							                                    	<td valign="top"><span class="style55">#if($readmode != "disabled")*#end</span></td>
							                                     	<td width="29%"><div align="right" class="style57">
							                                      		<div align="left">#if($readmodenegeri != "disabled")Negeri#else Negeri#end</div>
							                                      	</div></td>
							                                      	<td width="1%"><div align="right" class="style43">:</div></td>
							                                      	<td width="70%">$!socNegeri</td>
							                               	</tr>
                            	
							                            	<tr>
							                                  		<td valign="top"><span class="style55">#if($readmode != "disabled")*#end</span></td>
							                                     	<td><div align="right" class="style57">
							                                       		<div align="left">#if($readmodedaerah != "disabled")Daerah#else Daerah#end</div>
							                                       	</div></td>
							                                    	<td width="1%"><div align="right" class="style43">:</div></td>
							                                       	<td>$!socDaerah</td>
							                               	</tr>
                                
							                              	<tr>
							                              			<td valign="top"><span class="style55">#if($readmode != "disabled")*#end</span></td>
							                                       	<td><div align="right" class="style57">
							                                       		<div align="left">#if($readmodemukim != "disabled")Mukim#else Mukim#end</div>
							                                      	</div></td>
							                                       	<td width="1%"><div align="right" class="style43">:</div></td>
							                                       	<td>$!socMukim</td>
							                       			</tr>										
							                 #if($listam.flag=="1")
							                             	<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Alamat Harta</div>
							                                                </div></td>
							                                               <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><input name="txtAlamatHarta1HtaamX" type="text" id="textfield44" value="$listam.alamathta1" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
							                              	</tr>
							                                              
							                       			<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="left"></div></td>
							                                                <td>&nbsp;</td>
							                                                <td><input name="txtAlamatHarta2HtaamX" type="text" id="textfield45" value="$listam.alamathta2" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
							                        		</tr>
							                      			
							                      			<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="left"></div></td>
							                                                <td>&nbsp;</td>
							
							                                                <td><input name="txtAlamatHarta3HtaamX" type="text" id="textfield46" value="$listam.alamathta3" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/></td>
							                 				</tr>
							                              	
							                              	<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Poskod</div>
							                                                </div></td>
							                                                 <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><input name="txtPoskodHartaHtaamX" type="text" id="textfield47" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodHartaHtaamX')" value="$listam.poskodhta" size="5" maxlength="5" $readmodeR class="$readmode" /></td>
							                         		</tr>
                           		
							                           		<tr>
							                                   		<td class="style38 style43 style45">&nbsp;</td>
							                                    	<td class="style38"><div align="right" class="style57">
							                                      		<div align="left">Bandar</div>
							                                    	</div></td>
							                                  		<td><div align="right"><span class="style38">:</span></div></td>
							                                    	<td>$!socBandar</td>
							                            	</tr>
                                         	#end                                  	
							                             	<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">
							                                                  
							                                                   #if($listam.flag=="2")
							                                                    Nama Agensi
							                                                   #else
							                                                    Nama Pemaju
							                                                   #end
							                                                 </div>
							                                                </div></td>
							                                                 <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><label>
							                                                  <input name="txtNamaPemajuHtaamX" type="text" id="txtNamaPemajuHtaamX" style="text-transform:uppercase;" onblur="uppercase()" value="$listam.namapemaju" size="34" $readmodeR class="$readmode" />
							                                                </label></td>
							                     			</tr>
                                              
							                              	<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">
							                                                 
							                                                   #if($listam.flag=="2")
							                                                    Alamat Agensi
							                                                   #else
							                                                    Alamat Pemaju
							                                                   #end
							                                                 
							                                                  </div>
							                                                </div></td>
							                                                 <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><label>
							                                                  <input name="txtAlamatPemaju1HtaamX" type="text" id="textfield21" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.alamatpemaju1" size="34" $readmodeR class="$readmode" />
							                                                </label></td>
							                           		</tr>
                            		
							                            	<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="left"></div></td>
							                                                <td>&nbsp;</td>
							                                                <td><label>
							                                                  <input name="txtAlamatPemaju2HtaamX" type="text" id="textfield22" value="$listam.alamatpemaju2" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
							                                                </label></td>
							                         		</tr>
							                                  	
							                              	<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="left"></div></td>
							
							                                                <td>&nbsp;</td>
							                                                <td><label>
							                                                  <input name="txtAlamatPemaju3HtaamX" type="text" id="textfield23" value="$listam.alamatpemaju3" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
							                                                </label></td>
							                            	</tr>
							                                  	
							                 				<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Poskod</div>
							                                                </div></td>
							                                                <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><input name="txtPoskodPemaju1HtaamX" type="text" id="textfield24" value="$listam.poskodpemaju" size="5" maxlength="5"  $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="uppercase()" onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPemaju1HtaamX')"/></td>
							                             	</tr>
							                                	
							                             	<tr>
							                                   		<td>&nbsp;</td>
							                                     	<td><div align="right" class="style57">
							                                       		<div align="left">Negeri</div>
							                                        </div></td>
							                                       	<td width="1%"><div align="right" class="style43">:</div></td>
							                                 		<td>$!socNegeriAdd</td>
							                          		</tr>
                             		
							                            	<tr>
							                                    	<td class="style38 style43 style45">&nbsp;</td>
							                                    	<td class="style38"><div align="right" class="style57">
							                                      		<div align="left">Bandar</div>
							                                    	</div></td>
							                                   		<td><div align="right"><span class="style38">:</span></div></td>
							                                    	<td>$!socBandarAdd</td>
							                             	</tr>
										#if($listam.flag=="1")
							                       			<tr>
							                                    	<td>&nbsp;</td>
							                                   		<td><div align="right" class="style57">
							                                      		<div align="left">No Perjanjian</div>
							                                       	</div></td>
							                                     	<td width="1%"><div align="right" class="style43">:</div></td>
							                                     	<td><input name="txtNoPerjanjianHtaamX" type="text" id="textfield49" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.noperjanjian" size="34" maxlength="40" $readmodeR class="$readmode" /></td>
							                              	</tr>
							                              		
							                         		<tr>
							                                    	<td>&nbsp;</td>
							                                    	<td><div align="right" class="style57">
							                                        	<div align="left">Tarikh Perjanjian</div>
							                                       	</div></td>
							                                       	<td width="1%"><div align="right" class="style43">:</div></td>
							                                    	<td><span class="style36">
							               								<input name="txtTarikhPerjanjianHtaamX" id="txtTarikhPerjanjianHtaamX2" type="text" $readmodeR class="$readmode" value="$listam.tarikhperjanjian" size="15"  onblur="trans_date(this.value)" />
							               							#if($readmode != "disabled" )
							               									<a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
							                                       	#end                                                
							                                       	</td>
							                       			</tr>
                     					#end     							                         		
										#if( $listam.flag=="2")
							                              	<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Nama Rancangan</div>
							                                                </div></td>
							                                                 <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><label>
							                                                  <input name="txtNamaRancanganHtaamX" type="text" id="textfield50" value="$listam.namarancangan" size="34" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"/>
							                        		</tr>
							                                
							                              	<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">No ROH</div>
							                                                </div></td>
							                                                <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><input name="txtNoRohHtaamX" type="text" id="textfield51" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.noroh" size="34" maxlength="50" $readmodeR class="$readmode" /></td>
							                              	</tr>
							                                
							                           		<tr>
							                                                <td>&nbsp;</td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Lot ID</div>
							                                                </div></td>
							                                                 <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><input name="txtLotIdHtaamX" type="text" id="textfield52" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.nolot" size="15" maxlength="15" $readmodeR class="$readmode" /></td>
							                        		</tr>
                                              
                						#end	
				                		#if($listam.flag=="3") 
							                       			<tr>
							                                  		<td valign="top">&nbsp;</td>
							                                      	<td valign="top"><div align="right" class="style57">
							                                        	<div align="left">Jenis Kepentingan</div>
							                                      	</div></td>
							                                      	<td valign="top"><div align="right">:</div></td>
							                             	</tr>
				                  		#end
                         								</table>
													</td>
 
									               	<td width="50%" valign="top">
									                	<table width="100%">
									                   		<tr>
									                               		<td valign="top"><div align="left"><span class="style43">#if($readmode != "disabled")<span class="style45">*</span>#end</span></div></td>
									                                 	<td width="29%"><div align="right" class="style43 style54">
									                                   		<div align="left">#if($readmodedaerah != "disabled") Bahagian Simati #else Bahagian Simati #end</div>
									                                   	</div></td>
									                                	<td width="1%"><div align="right" class="style43">:</div></td>
									                                    <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati5" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati1X')" value="$listam.basimati" style="text-align:right;text-transform:uppercase;" size="14" maxlength="14" $readmodeR class="$readmode"  onblur="bahagiansimati()"/>
																		/
																		  <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati6" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati2X')" value="$listam.bbsimati" size="14" maxlength="14" $readmodeR class="$readmode" style="text-align:left;text-transform:uppercase;" onblur="bahagiansimati()" /></td>
									                     	</tr>  
									                     	                     			
							                      			<tr>
							                               		<td><div align="left"><span class="style43"></span></div></td>
							                                    <td><div align="right" class="style57">
							                                    	<div align="left">Kategori Tanah </div>
							                                 	</div></td>
							                                 	<td width="1%"><div align="right" class="style43">:</div></td>
							                                  	<td>
							                        	#if($listam.kategori == "2")
							                            	#set($meterhektar = "Hektar")
							                            #elseif($listam.kategori == "1" 
							                            	|| $listam.kategori == "3" 
							                            	|| $listam.kategori == "4" 
							                            	|| $listam.kategori == "5" 
							                            	|| $listam.kategori == "6"
							                            	|| $listam.kategori == "7" 
							                            	|| $listam.kategori == "8")
							                             	#set($meterhektar = "Meter Persegi")
							                             #else
							                         		#set($meterhektar = "")
							                            #end
							                                    $socKaTanah           
							                         			</td>
							                           		</tr>
							                               	
							                               	<tr>
							                             		<td><div align="left"><span class="style43"></span></div></td>
							                                  	<td><div align="right" class="style57"> 
							                                   	<div align="left">Luas Asal</div>
							                                 	</div></td>
							                                	<td width="1%"><div align="right" class="style43">:</div></td>
							                                   	<td>
							                                	##foreach($listluashta in $listluas)                                          
							                                    	##if($listam.jenisluas==$listluashta.id)                     
							                                          	##set($listluasK=$listluashta.kod)
							                                          	##set($listluasN=$listluashta.nama)
							                             			##end 
							                            		##end
							
							                                  	#if($readmode == "disabled")
							                                  		<input name="txtLuasAsalHtaamX" type="text" id="txtLuasAsalHtaamX" value="$listam.luasasal" size="15" maxlength="15" $readmodeR class="$readmode"  />
							                                 
							                                   			##if($listam.jenisluas!="" && $listam.jenisluas!="0")
							                    						##else
							                     						##end 
							                                  	#else
							                                         #if($listam.jenisluas!="" && $listam.jenisluas!="0")
							                                       	<input name="txtLuasAsalHtaamX" type="text"  class = "disabled" id="txtLuasAsalHtaamX"  style="text-transform:uppercase;"   onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamX')" value="$listam.luasasal" size="15" maxlength="15" readonly />
							                                          
							 										#else
																	<input name="txtLuasAsalHtaamX" type="text"  class = "disabled" id="txtLuasAsalHtaamX"  onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamX')" value="$listam.luasasal" size="15" maxlength="15" readonly />
							 										#end 
							 
							 									#end
							 									</td>
							                            	</tr>
							                            	<tr>
							                               		<td><div align="left"><span class="style43"></span></div></td>
							                               		<td><div align="right" class="style57"> 
							                                   		<div align="left">Jenis Luas</div>
							                                   	</div></td>
							                               		<td width="1%"><div align="right" class="style43">:</div></td>
							                            		<td>$!socLuas</td>
							                               	</tr>
                                          
					                              			<tr id="tr_luasharta" style="display:none;">
					                                             <td>&nbsp;</td>
					                                              <td class="style38">&nbsp;</td>
					                                              <td>&nbsp;</td>
					                                              <td>
					                                              <span id="luas1" style="display:none;">
					                                              <input name="txtLuasAsalHtaam1" type="text" id="txtLuasAsalHtaam1" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam1')"   />
					                                              </span>
					                                              <span id="luas2" style="display:none;">
					                                               <input name="txtLuasAsalHtaam2" type="text" id="txtLuasAsalHtaam2" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam2')"   />
					                                               </span>
					                                               <span id="luas3" style="display:none;">
					                                                <input name="txtLuasAsalHtaam3" type="text" id="txtLuasAsalHtaam3" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam3')"  />
					                                                </span>                                              
							                                	</td>
							                           		</tr>
                           		
							                           		<tr id="tr_luasharta_b" style="display:none;">
							                                	<td>&nbsp;</td>
							                                              <td class="style38">&nbsp;</td>
							                                              <td>&nbsp;</td>
							                                              <td><label>
							                                                <input type="button" name="button2" id="button2" value="Convert" onclick="ConvertLuasHarta('socJenisLuasHtaamX','txtLuasAsalHtaamX','txtLuasHMpHtaamX','meterhektar','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','socKategoriTanahHtaamX')" />
							                                              </label>
							                               		</td>
							                            	</tr>
                          		
							                          		<tr>
							                               		<td><div align="left"><span class="style43"></span></div></td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Luas (Hektar/MP)</div>
							                                                </div></td>
							                                                 <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><input name="txtLuasHMpHtaamX" type="text" class="$readmode" id="txtLuasHMpHtaamX" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasHMpHtaamX')" value="$listam.luashmp" size="15" maxlength="15" $readmodeR/>
							                                                <input name="meterhektar" type="text" id="meterhektar" value="$meterhektar" size="15" readonly class="disabled" />
							                              		</td>
							                              	</tr>
                                             
							                             	<tr>
							                               		<td><div align="left"><span class="style43"></span></div></td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Nilai Tarikh Mohon (RM)</div>
							                                                </div></td>
							                                               
							                             	#if($listam.nilai_Hta_memohon=="")
							                                	#set($ntp="")                                                
							                            	#elseif($listam.nilai_Hta_memohon == 0 )
							                               		#set($ntp="0.00")                                                
							                           		#else                                                
							                      	            #set($ntp="$listam.nilai_Hta_memohon")                                                
							                           		#end
							                                                                                               
							                               		<td width="1%"><div align="right" class="style43">:</div></td>
							                                  	<td> 
							                            	#if($readmode=="disabled")
							                              		#if($listam.nilai_Hta_memohon!="")
							                                                 <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaaX" $readmodeR class="$readmode" value="$listam.nilai_Hta_memohon"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaaX')" />
							                                   	#else
							                                                  <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaaX" $readmodeR class="$readmode" value=""  onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaaX')" />
							                                	#end
							                               	#else
							                                	<input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaaX" $readmodeR class="$readmode" value="$ntp"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHtaaX')"  onblur="validateModal(this,this.value,'$ntp')" />
							                               	#end
							                               		</td>
							                           		</tr>
                                
							                          		<tr>
							                              		<td><div align="left"><span class="style43"></span></div></td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Nilai Tarikh Mati (RM)</div>
							                                                </div></td>
							                                                <td width="1%"><div align="right" class="style43">:</div></td>
							                                                
							                             	#if($listam.nilai_Hta_mati=="")
							                                	#set($ntm="")                                                
							                               	#elseif($listam.nilai_Hta_mati == 0)
							                                   	#set($ntm="0.00")                                                
							                               	#else                                                
							                                   	#set($ntm="$listam.nilai_Hta_mati")
							                           		#end
							                                                
							                                                <td>
							                                                
							                                                #if($readmode=="disabled")
							                                                #if($listam.nilai_Hta_mati!="")
							                                                <input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamX" $readmodeR class="$readmode" value="$listam.nilai_Hta_mati" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamX')"/>
							                                                #else
							                                                <input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamX" $readmodeR class="$readmode" value="" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamX')"/>
							                                                #end
							                                                #else
							                                                
							                                                <input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamX" $readmodeR class="$readmode" value="$ntm" onKeyUp="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamX')" onblur="validateModal(this,this.value,'$ntm');" />
							                                                #end</td>
							                     			</tr>
                                              
							                          		<tr>
							                                                <td><div align="left"><span class="style43"></span></div></td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">No Cagaran</div>
							                                                </div></td>
							                                                 <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><input name="txtNoCagaranX" type="text" id="txtNoPajakan4" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.nocagaran" size="15" maxlength="12" $readmodeR class="$readmode" /></td>
							                              	</tr>
							                            	
							                            	<tr>
							                               		<td><div align="left"><span class="style43"></span></div></td>
							                                 	<td><div align="right" class="style57">
							                                    	<div align="left">No Pajakan</div>
							                                  	</div></td>
							                              		<td width="1%"><div align="right" class="style43">:</div></td>
							                                   	<td>
							                                   	<input name="txtNoPajakanX" type="text" id="txtNoPajakan3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listam.nopajakan" size="15" maxlength="12" $readmodeR class="$readmode" />
							                                   	</td>
							                                </tr>
                         		
							                         		<tr>
							                                                <td><div align="left"><span class="style43"></span></div></td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Status Pemilikan</div>
							                                                </div></td>
							                                                <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td>$!socPB</td>
							                            	</tr>
							                                              
							                              	<tr>
							                                                <td><div align="left"><span class="style43"></span></div></td>
							                                                <td><div align="right" class="style57">
							                                                  <div align="left">Tanggungan</div>
							                                                </div></td>
							                                                 <td width="1%"><div align="right" class="style43">:</div></td>
							                                                <td><input name="txtTanggunganHtaamX" type="text" id="txtTanggunganHtaam3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$!listam.tanggungan" size="15" maxlength="15" $readmodeR class="$readmode" /></td>
							                      			</tr>
				     										
				     										<tr>
				                                            	<td><div align="left"><span class="style43"></span></div></td>
				                                                <td><div align="right" class="style57">
				                                                  <div align="left">Jenis Tanah</div>
				                                                </div></td>
				                                               	<td width="1%"><div align="right" class="style43">:</div></td>
				                                                <td>
					                            	#foreach($listtan in $listtanah)
					                                          
					                             		#if($listam.jenistanah==$listtan.id)
					                                    	#set($listtanK=$listtan.kod)
					                                  		#set($listtanN=$listtan.keterangan)
					                                  	#end 
					                                  	
					                       			#end
                                          
					                      			#if($readmode == "disabled")                              
					                               		#if($listam.jenistanah!="" && $listam.jenistanah!="0" )
					                                   		<input name="jt" value="$listtanK - $listtanN" size="25" style="text-transform:uppercase;" $readmodeR class="$readmode" />
					                       				#else
					                                   		<input name="jt" value="" size="15" style="text-transform:uppercase;" $readmodeR class="$readmode" />
					                             		#end
                              
                        							#else
						                           		#if($listam.jenistanah!="" && $listam.jenistanah!="0")
						                              			<select name="socJenisTanahHtaamX" class="autoselect" $readmode id="socJenisTanahHtaam3" style="text-transform:uppercase;" onblur="uppercase()">
						                           			#if($listam.jenistanah=="3")
							                                  		<option value="3">TANAH GSA</option>
							                                  	 	<option value="4">BUKAN TANAH GSA</option>
						                                    #end
						                                   	#if($listam.jenistanah=="4")
							                                    	<option value="4">BUKAN TANAH GSA</option>
							                                    	<option value="3">TANAH GSA</option>         
						                                   	#end
						                             			</select>
														#else
																<select name="socJenisTanahHtaamX" class="autoselect" $readmode id="socJenisTanahHtaam3" style="text-transform:uppercase;" onblur="uppercase()">
							   											<option value="0">TIDAK DINYATAKAN</option>
							                                         	<option value="3">TANAH GSA</option>
							                                        	<option value="4">BUKAN TANAH GSA</option>
																</select>
														#end 
							
													#end
																</td>
                         									</tr>     
                         									
															<tr>
				                                                <td valign="top"><div align="left"><span class="style43"></span></div></td>
				                                                <td valign="top"><div align="right" class="style57">
				                                                  <div align="left">Catatan</div>
				                                                </div></td>
				                                             	<td width="1%" valign="top"><div align="right" class="style43">:</div></td>
				                            					<td>
				                            						<textarea name="txtCatatanHtaamX" id="txtCatatanHtaam2" cols="31" rows="5" $readmodeR class="$readmode" >$listam.catatan</textarea>
				                            					</td>
				                            				</tr>
                                              
							                           		<tr id="tr_flag_daftar"  style="display:none">
							                                	<td></td>
							                                  	<td  valign="top">Urusan Kemasukkan Maklumat Harta</td>
							                                 	<td valign="top">:</td>
							                               		<td valign="top">
                          							#set($FLAG_DAFTAR = $listam.FLAG_DAFTAR)                                          
                           							#if($readmode != "disabled" )
                                          
					                                	#if($FLAG_DAFTAR == '1')
					                                    	#set($checked_flag_daftar1 = "checked")
					                                     	#set($checked_flag_daftar2 = "")   
					                                 	#elseif($FLAG_DAFTAR == '2') 
					                                       	#set($checked_flag_daftar2 = "checked")    
					                                     	#set($checked_flag_daftar1 = "") 
					                                	#else
					                                       	#set($checked_flag_daftar1 = "checked")
					                                      	#set($checked_flag_daftar2 = "")                                   
					                       				#end
                                                                                    
						                                          <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar1 id="FLAG_DAFTAR" 
						                                          value="1" /> Pendaftaran
						                                          <br />                                          
						                                          <input type="radio" name="FLAG_DAFTAR"  $checked_flag_daftar2 id="FLAG_DAFTAR" 
						                                          value="2" /> Perbicaraan
                              						#else
                                  						#set($text_daftar = "")
				                                  		#if($FLAG_DAFTAR == '1')
				                                        	#set($text_daftar = "PENDAFTARAN")
				                                       	#elseif($FLAG_DAFTAR == '2') 
				                                          	#set($text_daftar = "PERBICARAAN")                                         
				                                      	#end
                                 								<input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$readmode" />                                          
                                       							<input type="hidden" name="FLAG_DAFTAR" id="FLAG_DAFTAR" value="$FLAG_DAFTAR"  />
                                           
                              						#end		                                        		                                          
		                                          			</td>
                      									</tr>
						                       		#if($!skrin_online != "yes")                         
						                             	<script>
						                               		document.getElementById('tr_flag_daftar').style.display = "";
						                            	</script>
						                       		#end                          									  
                           								<input type="hidden" name="idhtaamX" value="$listam.idhta" />
  														</table>
													</td>     
                  								</tr>
            							</table>
          							</fieldset>
           
				           		#if($readmode != "disabled")
						  				<table width="100%">
						               		<tr>
						                   		<td><span class="style69 style5 style43 style54"><em><span class="style45">Perhatian</span> : Sila pastikan label bertanda <span class="style45">*</span> diisi.</em></span></td>
						                 	</tr>
										</table>				 
								#end                          
       								</td>
       						</tr>													
													                 			             																							
					#end <!-- end for -->	
				#end
				<!-- Tamat Kemaskini-->	
						<input type="hidden" name="idhtaamXid" id="idhtaamXid" value="$idhtaam" />
					
				#if($show_button=="yes")
							       	<tr>
							       		<td align="center">
							      	#if($!skrin_online != "yes")
							        	#if($show_kemaskini_htaam == "yes")
							          		<!-- button integrasi -->                                        
							              	<input type="button" name="cmdJPPH" value ="Nilaian JPPH" onClick="javascript:intNilaianJPPH('$id', '$idhta')" disabled>&nbsp;                                          <input type="button" name="cmdETanahHM" value="Semakan Status Hakmilik" onclick="checkWSHM('$idhta');" />&nbsp;
							        		<input type="button" name="cmdSPTB" value ="Semakan Maklumat Geran" onClick="javascript:viewSPTB('$!id_fail','$!idhta',2)">
											<!-- end button integrasi -->                                        
										#end
									#end
							         	</td>
							     	</tr>
									
									<tr>
							                                  
							                                    <td align="center"> 
							                                    
							                                #if($open_button_online == "yes")
							                                    #if($show_simpan_add_htaam=="yes")
							                                    	<!--<input type="button" name="tambahpenghutang3" id="tambahpenghutang3" $readmode value="Simpan"  onclick="setSelected(1,0,0,1);add_HtaamX()"/>-->
							   										<input type="button" name="tambahta" id="tambahta" $readmode value="Simpan"  onclick="setSelected(1,0,0,1);add_HtaamX('$id','$!paramOnline')"/>
							                                  	#end
							                                   	#if($show_batal_add_htaam=="yes")
							                                   		#if($!skrin_online != "yes") 
							                                      		<input type="button" name="cmdSimpan9" id="cmdSimpan8" value="Batal" onclick="cancelwaris()"/>
							                                      	#end
							                                  	#end
							                                	#if($show_kemaskini_htaam=="yes")
							                        				##if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
							                     						<!--   <input type="button" name="button7" id="button11" value="Kemaskini"  onclick="setSelected(1,0,0,1);edit_HtaamX()" />  -->
							                         				##end
							                      					##if($boleh_kemaskini == "yes")
							                      					##end
							                      					#if($!skrin_online != "yes")  
							                      						<!--<input type="button" name="cmdKemaskini1" id="cmdKemaskini1" value="Kemaskini"  onclick="setSelected(1,0,0,1);edit_HtaamX()" /> -->
										  								<input type="button" name="cmdKemaskini1" id="cmdKemaskini1" value="Kemaskini"  onclick="setSelected(1,0,0,1);edit_HtaamX('$idhta')" />
							                       						#if($flag_kemaskini_selesai != "yes")
							                                				<script>
							                                				document.getElementById('cmdKemaskini1').style.display = "none";
							                                				</script>
							                                			#end   
							                      					
							                      					#else
							                     						<!-- <input type="button" name="button13" id="button12" value="Simpan"  onclick="setSelected(1,0,0,1);save_HtaamX()"/> -->
																		<input type="button" name="button13" id="button12" value="Simpan"  onclick="setSelected(1,0,0,1);save_HtaamX('$!id_PermohonanSimati','$!idhta','$!idSimati','$!idDokumen','$!paramOnline')"/>                     					
							                      					#end
							                       				
							                       				#end
							                               		#if($show_save_update_htaam=="yes")
							                                   		<!-- <input type="button" name="button13" id="button12" value="Simpan"  onclick="setSelected(1,0,0,1);save_HtaamX()"/> -->
																	<input type="button" name="button13" id="button12" value="Simpan"  onclick="setSelected(1,0,0,1);save_HtaamX('$!id_PermohonanSimati','$!idhta','$!idSimati','$!idDokumen','$!paramOnline')"/>
							                                  	#end
							                                   	#if($show_batal_update_htaam=="yes")
							                                      	#if($!skrin_online != "yes") 
							                                      		<input type="button" name="button7" id="button13" value="Batal"  onclick="setSelected(1,0,0,1);batal_HtaamX()" />
							                                  		#end
							                                  	#end
							                                	#if($show_hapus_htaam=="yes" && $show_kemaskini_htaam=="yes") 
							                           				##if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
																		<!--
							                                      		<input type="button" name="cmdCetak10" id="cmdCetak9" value="Hapus"  onclick="setSelected(1,0,0,1);hapus_HtaamX()"/>  -->
							                                      		<!--
							                                      		<input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onclick="javascript:cetak('$NO_FAIL')"/> -->               
							                                      	##end 
											                       ##if($boleh_kemaskini == "yes")
											                       ##end  
							                        				<input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus"  onclick="setSelected(1,0,0,1);hapus_HtaamX()"/>
							                        				#if($flag_kemaskini_selesai != "yes")
							                                			<script>
							                                			document.getElementById('cmdHapus1').style.display = "none";
							                                			</script>
							                                		#end   
							                                     <!--
							                                      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onclick="javascript:setTable('tableReport')"/>
							                                      -->
							                             		#end
							                              		##if($show_kembali_htaam=="yes")
							                                    ##end 
							                        			<!-- <input type="button" name="cmdKembali10" id="cmdKembali9" value="Kembali" onclick="setSelected(1,0,0,1);HtaamViewX()"/> -->
																<input type="button" name="cmdKembali10" id="cmdKembali9" value="Kembali" onclick="setSelected(1,0,0,1);HtaamViewX('$!paramOnline')"/>
							                        
							                        	#end                     
							        	</td>
									</tr>
       			#end	
   				   						<tr>
       										<td>
           										<fieldset><legend>SENARAI HARTA TAK ALIH (TIADA HAKMILK)</legend>				
												<table width="100%">
                    								<tr>
                        								<td align="left">
                            								<div align="left">
							             			#if($tambahbutton == "yes")
							                    			#if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")
															<!--     <input type="submit" name="button" id="button" value="Tambah" onclick="nktambah()"/>  -->
							                          		#end
							                              	##if($boleh_kemaskini == "yes")
							                              	##end
							                              	#if($open_button_online == "yes")
							                              		<input type="submit" name="buttonTambah1" id="buttonTambah1" value="Tambah" onclick="nktambah()"/>
							                               		#if($flag_kemaskini_selesai != "yes")
							                                	<script>
							                                	document.getElementById('buttonTambah1').style.display = "none";
							                                	</script>
							                                	#end 
							                              	#end
							              			#end
							              			##if($kembalibutton == "yes")
                          							<!-- <input type="submit" name="cmdKembali2" id="cmdKembali2" value="Kembali"  onclick="kembali_simati()" /> -->
              										##end                                     
									             	#if($!skrin_online != "yes")
									                	#if(($listHTAX.size()>0) && $show_htaa_add_table=="" && $show_htaa_update_table=="" ) 
									                   		#if($open_button_online == "yes")   
									                        	<input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onclick="javascript:setTable('tableReport')"/>
									                       	#end
									              		#end      
									               	#end
                             									</div>
                       										</td>
                    									</tr>
        											</table>
        										
                                     				<fieldset  id="tableReport" style="display:none;" ><legend><strong>Senarai Laporan</strong></legend>
														<table width="100%" border="0" cellspacing="2" cellpadding="2">
	      													<tr> 
									        					<td>        
									         					#if($flagFromSenaraiFailSek8 != "yes" && $flagForView != "yes")
									        						<a href="#" class="style2 style42" onClick="javascript:tukarstatus();cetak('$NO_FAIL','$idhta')">Surat ke JPPH</a>
														        #else
														         <a href="#" class="style2 style42" onClick="javascript:cetak('$NO_FAIL','$idhta')">Surat ke JPPH</a>
														        #end
									        					</td>
									      					</tr>
									        				<tr>
													        	<td ><a href="#" class="style2 style42" onClick="javascript:cetakSuratPeringatan('$NO_FAIL','$idhta')">Surat Peringatan ke JPPH</a></td>
													      	</tr>
													       	<!--<tr>
													        	<td ><a href="#" class="style2 style42" onClick="javascript:cetakNilaiHarta('$NO_FAIL')">Nilaian Harta oleh PPSPP</a></td>
													      	</tr>  -->
									    				</table>								    				
								    				</fieldset>				    
						               	#set($xjumpa_lain = "")
						             	#set($xjumpa_kelompok = "")	
						          		#set($xjumpa_beli = "")
                    
        								#if($listHTAX.size()!=0 )    
         											<fieldset><legend>PERJANJIAN JUAL BELI</legend>
                   									<table width="100%">
						           			#foreach($listam in $listHTAX)                                     
						                    	#if($listam.kategori_hta == 1)
						                        	#set($xjumpa_beli = "no")
						                     	#end                                     
						                	#end
              								#if($xjumpa_beli =="no")
								           				<tr class="table_header">
								                       		<td width="2%">
										                 		<input type="checkbox" name="selectallHTATH" id="selectallHTATH" onClick="doCheckAll1_HTATH()" />
															</td>
								                    		<td width="3%"><div align="center">NO</div></td>
								                           	<td width="15%"><div align="left">NEGERI</div></td>
								                          	<td width="15%"><div align="left">DAERAH</div></td>
								                        	<td width="15%"><div align="left">MUKIM</div></td>
								                        	<td width="15%"><div align="left">ALAMAT HARTA</div></td>
															<td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
								                   			<td width="32%"><div align="left">DOKUMEN</div></td>
								                   		</tr>
               								#end
                                      
            								#set($plko=0)
	              							#foreach($listam in $listHTAX)
							         		
							         			#if($listam.kategori_hta == 1)
													#set($plko=$plko+1)
													
													#if ( ($plko % 2) != 1 )
												   		#set( $row = "row2" )
												   	#else
														#set( $row = "row1" )
													#end                       		
									                  	<tr bgcolor="white" class="$row">
													 		<td><input type="checkbox" name="selectHTATH" id="selectHTATH" value="$listam.idhta" /></td>
									                   		<td><div align="center" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()">$plko</div></td>
									                      	<td><div align="left" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"><a href="javascript:get_htaam('$!idPermohonanSimati','$!listam.idhta','$!listam.idDokumen')" class="style42">$!listam.namaNegeri</a></div></td>
									                      	<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaDaerah</div></td>
									                     	<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaMukim</div></td>
									                      	<td>
								 							#set($al_harta = "") 
								    						#set($al_harta = "$listam.alamat1  $listam.alamat2  $listam.alamat3 $listam.poskod")
								   	                          	<div align="left" style="text-transform:uppercase;" onblur="uppercase()">$al_harta</div>
								   	                  		</td>
								                     	#if($listam.basimati!="" && $listam.bbsimati!="")
									               			<td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>
									                 	#else
									                  		<td></td>
									              		#end 
									                 		<td>  
														#if($id_Status != "169" 
									                    	&& $id_Status != "21" 
									       					&& $id_Status != "64" 
									                        && $id_Status != "163" 
									                      	&& $id_Status != "164" 
									                      	&& $id_Status != "165")	 									                 		
									                 	##if($open_button_online == "yes")
										                   		<a href = "javascript:lampiranHarta('$listam.idhta','$!paramOnline');">
																	<img border="0" src="../img/plus.gif" width="20" height="15"/>
																</a><br>
														##end
														#end
															 	$listam.lampirans
									                  		</td>
									              		</tr>   

													#set($xjumpa_beli = "no")
	                    
	           									#end
              		
              								#end
                    
             								#if($xjumpa_beli == "" )
								                   		<tr bgcolor="white">  
								                       		<td colspan="6" ><div align="left"  onblur="uppercase()">Tiada Rekod</div></td>	  
								   						</tr>                                     
               								#end 
                 									</table>
            									</fieldset>	           		
									       	
									       		<fieldset><legend> PEGANGAN DIBAWAH AKTA TANAH</legend>
									                <table width="100%">
								            #foreach($listam in $listHTAX)                                     
								                	#if($listam.kategori_hta == 2 && $listam.kategori_hta != 1 && $listam.kategori_hta != 3)
								                   		#set($xjumpa_kelompok = "no")
								                 	 #end                                     
								         	#end
							           		
							          		#if($xjumpa_kelompok == "no")
								            			<tr class="table_header">
								                   			<td width="3%"><div align="center">NO</div></td>
								                       		<td width="15%"><div align="left">NEGERI</div></td>
								                       		<td width="15%"><div align="left">DAERAH</div></td>
								                         	<td width="15%"><div align="left">MUKIM</div></td>
								                          	<td width="15%"><div align="left">NO ROH</div></td>
								                           	<td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
								                    		<td width="32%"><div align="left">DOKUMEN</div></td>
								                  		</tr>
   											#end
                 
						           			#set($plko=0)
							    			#foreach($listam in $listHTAX)
							                  	
							                  	#if($listam.kategori_hta == 2 && $listam.kategori_hta != 1 && $listam.kategori_hta != 3)
							 						#set($plko=$plko+1)
							                  		#if ( ($plko % 2) != 1 )
												   		#set( $row = "row2" )
												   	#else
														#set( $row = "row1" )
													#end 
									               		<tr bgcolor="white" class="$row">
													    	<!-- <td><input type="checkbox" name="selectHTATH" id="selectHTATH" value="$listam.idhta" /></td> -->
									                    	<td><div align="center" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()">$plko</div></td>
									                       	<td><div align="left" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"><a href="javascript:get_htaam('$!idPermohonanSimati','$!listam.idhta','$!listam.idDokumen')" class="style42">$!listam.namaNegeri</a></div></td>
									                       	<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaDaerah</div></td>
									                   		<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaMukim</div></td>
									                      	<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.noroh</div></td>
								                     	#if($listam.basimati!="" && $listam.bbsimati!="")
									                    	<td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>
									                	#else
									                  		<td></td>
									                  	#end 
									              			<td>
														#if($id_Status != "169" 
									                    	&& $id_Status != "21" 
									       					&& $id_Status != "64" 
									                        && $id_Status != "163" 
									                      	&& $id_Status != "164" 
									                      	&& $id_Status != "165")	 									              			  
									              		##if($open_button_online == "yes")
										                      	<a href = "javascript:lampiranHarta('$listam.idhta','$!paramOnline');">
																	<img border="0" src="../img/plus.gif" width="20" height="15"/>
																</a><br>
														##end
														#end
															 	$listam.lampirans
									                  		</td>									                 	
									                  	</tr>                        	

                									#set($xjumpa_kelompok = "no")                                   
												#end
            	
            								#end
                                      
              								#if($xjumpa_kelompok == "")
								               			<tr bgcolor="white">  
								                 			<td colspan="6" ><div align="left"  onblur="uppercase()">Tiada Rekod</div></td>	  
								   						</tr>
           									#end 
            										</table>
            									</fieldset>
                                  
             									<fieldset><legend>KEPENTINGAN LAIN-LAIN</legend>
             										<table>
							          		#foreach($listam in $listHTAX)                                     
									             	#if($listam.kategori_hta == 3 && $listam.kategori_hta != 2 && $listam.kategori_hta != 1  && $listam.negeri != "" )
									                	#set($xjumpa_lain = "no")
									               	#end                                     
								      		#end
	                  						#if($xjumpa_lain == "no")
								                 		<tr class="table_header">
								                      		<td width="3%"><div align="center">NO</div></td>
								                            <td width="15%"><div align="left">NEGERI</div></td>
								                           	<td width="15%"><div align="left">DAERAH</div></td>
								                          	<td width="15%"><div align="left">MUKIM</div></td>
								                        	<td width="15%"><div align="left">JENIS KEPENTINGAN</div></td>
								                            <td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
								                    		<td width="32%"><div align="left">DOKUMEN</div></td>
								                   		</tr>
	                   						#end 
                                    
						      				#set($plko=0)
						       				#foreach($listam in $listHTAX)
						         				#if($listam.kategori_hta == 3 && $listam.kategori_hta != 2 && $listam.kategori_hta != 1  && $listam.negeri != "" )
						             			
							             			#set($plko=$plko+1)						
													#if ( ($plko % 2) != 1 )
												   		#set( $row = "row2" )
												   	#else
														#set( $row = "row1" )
													#end                                        
								               			<tr bgcolor="white" class="$row">
												    	<!-- <td><input type="checkbox" name="selectHTATH" id="selectHTATH" value="$listam.idhta" /></td> -->
									                    	<td><div align="center" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()">$plko</div></td>
									                       	<td><div align="left" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"><a href="javascript:get_htaam('$!idPermohonanSimati','$!listam.idhta','$!listam.idDokumen')" class="style42">$!listam.namaNegeri</a></div></td>
									                       	<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaDaerah</div></td>
									                   		<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaMukim</div></td>
									                      	<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.jenis_penting</div></td>
								                      	#if($listam.basimati!="" && $listam.bbsimati!="")
									                    	<td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>
									                	#else
									                  		<td></td>
									                  	#end 
									              			<td> 
														#if($id_Status != "169" 
									                    	&& $id_Status != "21" 
									       					&& $id_Status != "64" 
									                        && $id_Status != "163" 
									                      	&& $id_Status != "164" 
									                      	&& $id_Status != "165")	 									              			 
									              			##if($open_button_online == "yes")
									                      	<a href = "javascript:lampiranHarta('$listam.idhta','$!paramOnline');">
																<img border="0" src="../img/plus.gif" width="20" height="15"/>
															</a><br>
															##end
															#end
														 	$listam.lampirans
								                  			</td>
								                 		</tr>                        	
					
													#set($xjumpa_lain = "no")
              
              									#end
              									
	                						#end
               	
               								#if($xjumpa_lain == "")
									                	<tr bgcolor="white">  
									                   		<td colspan="7"><div align="left" onBlur="uppercase()">Tiada Rekod</div></td>	  
									   					</tr>
            								#end             	
         											</table>
            									</fieldset>           		
										#else							
											<table width="100%">
								            	<tr  class="table_header">
								             	<td width="5%"><div align="center">NO</div></td>
								             	<td width="20%"><div align="center">NEGERI</div></td>
								              	<td width="20%"><div align="center">DAERAH</div></td>
								         		<td width="20%"><div align="center">MUKIM</div></td>
								              	<td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
								            	</tr>
								            	<tr bgcolor="white">
								              	<td align="left" colspan="5">Tiada Rekod</td>
								              	</tr>
								          	</table>                                                                       
   										#end          		    										
          									<input type="hidden" name="idhtaam" value="$listam.idhta" />
    										</fieldset> 
						                		</td>
						                	</tr>  
						                
							                <tr>
												<td>
										                #if($!skrin_online != "yes")
										      				<p align="right">CL - 01 - 78</p>     
										      			#end	
											 	</td>
											</tr>   															    				    										    										                                    	
  										</table> 
		      				       	</div>
		          				</div>
		        			</div>
		      			</div>      			
       					<div class="TabbedPanelsContent"></div>
      					<div class="TabbedPanelsContent"></div>
      		   		</div>
  				</div>  			
  			</td>
  		</tr>
		<!-- <input type="hidden" name="idhtaam" value="$listamid.idhta" /> -->
	  	<input name="no_lot_hta" id="no_lot_hta" type="hidden"  />
		<input name="id_harta" id="id_harta" type="hidden" value="$!idhta"  />
		<input type="hidden" name="idDokumen" value="$idDokumen" />
		<input name="id_daerah_harta" id="id_daerah_harta" type="hidden" />
		<input name="save_harta" id="save_harta" type="hidden" />
		<input name="bolehsimpan" id="bolehsimpan" type="hidden" />	
		<!-- <input type="hidden" name="selectHTATH" id="selectHTATH" /> -->
	   	<input type="hidden" name="selectedHartaTakAlih" id="selectedHartaTakAlih" value="$!selectedHartaTakAlih"/>
	    <input type="hidden" name="selectedHartaAlih" id="selectedHartaAlih" />
    
	</table>
	#parse("app/ppk/paging_sek8.jsp") 
	#parse("app/ppk/headerppk_script.jsp")
</form>

<script>
<!-- TAB -->
if(document.f1.selectedHartaTakAlih.value.length>0){
	var selectHTAs = document.getElementsByName('selectHTATH');
	var arrayCarryHartaTakAlih = document.f1.selectedHartaTakAlih.value;
	var selectedHartaTakAlihAdaHakmilik = []; 
	
	for (var i=0; i<arrayCarryHartaTakAlih.length; i++) {
		var carryHartaTakAlih = '';
		if(arrayCarryHartaTakAlih.indexOf(",")>0){ 
			carryHartaTakAlih = arrayCarryHartaTakAlih.substring(0, arrayCarryHartaTakAlih.indexOf(",")); 		
// 			selectedHartaTakAlihAdaHakmilik.push(carryHartaTakAlih);
			arrayCarryHartaTakAlih = arrayCarryHartaTakAlih.substring(arrayCarryHartaTakAlih.indexOf(",")+1, arrayCarryHartaTakAlih.length);
		} else {
			carryHartaTakAlih = arrayCarryHartaTakAlih;
// 			selectedHartaTakAlihAdaHakmilik.push(carryHartaTakAlih);
			arrayCarryHartaTakAlih = "";
		}
		
		for(var j=0; j<selectHTAs.length; j++){
			if(selectHTAs[j].value == carryHartaTakAlih){
				selectHTAs[j].checked = true;
			}
		}
	}
}

function setHartaTakAlih(){
	var arrayCarryHartaTakAlih = document.f1.selectedHartaTakAlih.value;
	
	var selectHTATHs = document.getElementsByName('selectHTATH');
	var selectedHartaTakAlihAdaHakmilik = []; 

	for(var i=0; i<arrayCarryHartaTakAlih.length; i++){
		var carryHartaTakAlih = '';
		//untuk pisahkan data jika lebih dari 1
		if(arrayCarryHartaTakAlih.indexOf(",")>0){ 
			carryHartaTakAlih = arrayCarryHartaTakAlih.substring(0, arrayCarryHartaTakAlih.indexOf(",")); 		
			arrayCarryHartaTakAlih = arrayCarryHartaTakAlih.substring(arrayCarryHartaTakAlih.indexOf(",")+1, arrayCarryHartaTakAlih.length);
			selectedHartaTakAlihAdaHakmilik.push(carryHartaTakAlih);	 			
		} else {
			carryHartaTakAlih = arrayCarryHartaTakAlih;
			arrayCarryHartaTakAlih = "";
			selectedHartaTakAlihAdaHakmilik.push(carryHartaTakAlih);
		}
	}
	
	if(selectedHartaTakAlihAdaHakmilik.length>0){
		for(var j=0; j<selectedHartaTakAlihAdaHakmilik.length; j++){
			var val = selectedHartaTakAlihAdaHakmilik[j];
			
			for (var i=0; i<selectHTATHs.length; i++) {
				var cbx = '';
		    	cbx = selectHTATHs[i].value;
		    	
				if(cbx == val){
				    if (selectHTATHs[i].checked) {
				    	if(selectedHartaTakAlihAdaHakmilik.indexOf(cbx)<0){
				    		selectedHartaTakAlihAdaHakmilik.push(cbx);	
				    	}
				    } else {
				    	if(selectedHartaTakAlihAdaHakmilik.indexOf(val)>0){
				    		selectedHartaTakAlihAdaHakmilik.splice(selectedHartaTakAlihAdaHakmilik.indexOf(val),1);
				    	}
				    }
				} else {
				    if (selectHTATHs[i].checked) {
				    	if(selectedHartaTakAlihAdaHakmilik.indexOf(cbx)<0){
				    		selectedHartaTakAlihAdaHakmilik.push(cbx);	
				    	}
				    }
				}
			}
		}
	} else {
		for (var i=0; i<selectHTATHs.length; i++) {
			var cbx = '';
	    	cbx = selectHTATHs[i].value;
	    	if (selectHTATHs[i].checked) {
	    		selectedHartaTakAlihAdaHakmilik.push(cbx);
	    	}
		}
	}
	return selectedHartaTakAlihAdaHakmilik;
}

function sendNilaianHTA2(NO_FAIL, ID_HTAAH) {
	// kmie, 20100721
	// to cater for integration
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&selectedTab=0&NO_FAIL=" + NO_FAIL + "&ID_HTAAH=" + ID_HTAAH;
	document.f1.method = "POST";
	document.f1.submit();
}

function sendNilaianHTA(idPermohonan,noFail) {
	var selectedHartaTakAlihAdaHakmilik = setHartaTakAlih();
	var idPermohonan = document.f1.idPermohonan.value;
	var idSimati = document.f1.idSimati.value;     

	if (!window.confirm("Adakah anda pasti?")) return;

	 document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&selectedHartaTakAlih="+selectedHartaTakAlihAdaHakmilik+"&idSimati="+idSimati+"&idPermohonan="+idPermohonan+"&action2=viewNilaianHTA&method=post&command=nilai_harta&mode=simpan_HTAHA";
	 document.f1.submit();
  }

/* START HARTA TAK ALIH (TIADA HAKMILIK)   */

function cancel() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
document.f1.reset();
document.f1.selectHTATH.focus();
}
}

function doCheckAll1_HTATH(){    
if (document.f1.selectallHTATH.checked == true){
if (document.f1.selectHTATH.length == null){
    document.f1.selectHTATH.checked = true;
} else {
    for (i = 0; i < document.f1.selectHTATH.length; i++){
        document.f1.selectHTATH[i].checked = true;
    }


}
} else {
if (document.f1.selectHTATH.length == null){
    document.f1.selectHTATH.checked = false;
} else {
    for (i = 0; i < document.f1.selectHTATH.length; i++){
        document.f1.selectHTATH[i].checked = false;
    }
}
}
}

function doUpdateCheckAll1_HTATH(){  
var c = 0;
if(document.f1.selectHTATH.length > 1)
{     
  for (i = 0; i < document.f1.selectHTATH.length; i++)
  {
  if (document.f1.selectHTATH[i].checked == false)
  {	 
  c++
  }
  }  
}
else
{
if (document.f1.selectHTATH.checked == false)
{	 
c++;
}	 	
}	 
if(c>0)
  {	  
  document.f1.selectallHTATH.checked = false;
  }
  else
  {
  document.f1.selectallHTATH.checked = true;
  }
  
}

function cetakImej(id){
	var url = "../servlet/ekptg.view.ppk.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
	
	function HtaamViewX(paramOnline) {
	// 	if(paramOnline == 'seksyen8online')
	// 		document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
	// 	else
	// 		document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8Internal";
		document.f1.mode.value = "HtaamviewX";
		document.f1.command.value = "HtaamX";
		document.f1.action="";
		document.f1.submit();
		
	}

function HtaamView(paramOnline) {
	var 	selectedHartaTakAlihAdaHakmilik = setHartaTakAlih();
    document.f1.selectedHartaTakAlih.value = selectedHartaTakAlihAdaHakmilik;

// 	if(paramOnline == 'seksyen8online')
// 		document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
// 	else
// 		document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8Internal";
	
	document.f1.mode.value = "Htaamview";
	document.f1.command.value = "Htaam";
	document.f1.load.value = "N";
	document.f1.action="";
	document.f1.submit();
}
function HAview() {
	document.f1.action = "";
	document.f1.mode.value = "view_harta_alih";
	document.f1.command.value = "harta_alih";
	document.f1.submit();
}

function NAview() {
	document.f1.action = "";
	document.f1.mode.value = "view_nilai_harta";
	document.f1.command.value = "nilai_harta";
	document.f1.submit();
}

function PenghutangView() {
	document.f1.action = "";
	document.f1.mode.value = "Penghutangview";
	document.f1.command.value = "Penghutang";
	document.f1.submit();
}
function PemiutangView() {
	document.f1.action = "";
	document.f1.mode.value = "Pemiutangview";
	document.f1.command.value = "Pemiutang";
	document.f1.submit();
}
function SaksiView() {
	document.f1.action = "";
	document.f1.mode.value = "Saksiview";
	document.f1.command.value = "Saksi";
	document.f1.submit();
}

function PentingView() {
	document.f1.action = "";
	document.f1.mode.value = "Pentingview";
	document.f1.command.value = "Penting";
	document.f1.submit();
}

function WarisView() {
	document.f1.action = "";
	document.f1.mode.value = "Warisview";
	document.f1.command.value = "Waris";
	document.f1.submit();
}
function SimatiView() {
	document.f1.action = "";
	document.f1.mode.value = "Simatiview";
	document.f1.command.value = "Simati";
	document.f1.submit();
	
}

function PemohonView() {
	document.f1.action = "";
	document.f1.mode.value = "Pemohonview";
	document.f1.command.value = "Pemohon";
	document.f1.submit();
}
function kembali_simati(){
	
	document.f1.command.value = "kembali_simati";
	document.f1.action = "";
	document.f1.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)

 {
    document.f1.tabIdatas.value = tabIdatas;
    document.f1.tabIdtengah.value = tabIdtengah;
    document.f1.tabIdbawah.value = tabIdbawah;	
	document.f1.tabIdtepi.value = tabIdtepi;	
}
function cancelwaris() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
document.f1.reset();
document.f1.txtNoKPBaru1Waris.focus();
}
}

<!-- HTATH -->
function nktambah(paramOnline) {
// document.f1.reset();
// document.f1.command.value = "HtaamX";
// document.f1.mode.value = "add_new";
// document.f1.submit();

// alert(4);

document.f1.reset();
document.f1.command.value = "HtaamX";
document.f1.mode.value = "add_new";
// if(paramOnline == 'seksyen8online')
// 	document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
// else
// 	document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8Internal";
document.f1.action = "";
document.f1.submit();
}

function CheckMukimU()
{
if(document.f1.socDaerahHtaamX.value == "" || document.f1.socDaerahHtaamX.value == "0")
{
  alert("Sila pilih daerah terlebih dahulu.");
  document.f1.socDaerahHtaamX.focus();
  return;
	  		
}


}

function CheckMukim()
{
if(document.f1.socDaerahHtaamX.value == "" || document.f1.socDaerahHtaamX.value == "0")
{
  alert("Sila pilih daerah terlebih dahulu.");
  document.f1.socDaerahHtaamX.focus();
  return;
	  		
}


}

function CheckDaerah()
{
if(document.f1.socNegeriHtaamX.value == "" || document.f1.socNegeriHtaamX.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriHtaamX.focus();
  return;
	  	
}

}

	function get_htaam(id_Permohonansimati,idhtaam,idDokumen){		
	    document.f1.command.value = "HtaamX";
	    document.f1.idhtaamXid.value = idhtaam;
		document.f1.idDokumen.value = idDokumen;
		document.f1.id_Permohonansimati.value= id_Permohonansimati;
		document.f1.mode.value = "getHtaamX";
		document.f1.action = "";
		document.f1.submit();
		
	}

function get_X(idhtaam)
{
  
	document.f1.command.value = "HtaamX";
	document.f1.idhtaamXid.value = idhtaam;
	document.f1.mode.value = "getHtaamX";
	document.f1.action = "";
	document.f1.submit();
	
}

function HtaamViewX1() {
	document.f1.action = "";
	document.f1.mode.value = "HtaamviewX1";
	document.f1.command.value = "HtaamX";
	document.f1.submit();
}
function HtaamViewX2() {
	document.f1.action = "";
	document.f1.mode.value = "HtaamviewX2";
	document.f1.command.value = "HtaamX";
	document.f1.submit();
}
function HtaamViewX3() {
	document.f1.action = "";
	document.f1.mode.value = "HtaamviewX3";
	document.f1.command.value = "HtaamX";
	document.f1.submit();
}

	function negerichangeX(v_t){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangeX";
		document.f1.action="";
		
		if(document.f1.radioHtaamViewX[0].checked == true){
			document.f1.noradio.value="1";
		}else if(document.f1.radioHtaamViewX[1].checked == true){
			document.f1.noradio.value="2";
		}else if(document.f1.radioHtaamViewX[2].checked == true){
			document.f1.noradio.value="3";
		}
		document.f1.v_tab.value = v_t;
		document.f1.submit();
		
	}

	function negerichangepemajuX(v_t){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangepemajuX";
		document.f1.action="";
			
		if(document.f1.radioHtaamViewX[0].checked == true){
			document.f1.noradio.value="1";
		}else if(document.f1.radioHtaamViewX[1].checked == true){
			document.f1.noradio.value="2";
		}else if(document.f1.radioHtaamViewX[2].checked == true){
			document.f1.noradio.value="3";
		}
		document.f1.v_tab.value = v_t;
		document.f1.submit();
		
	}

	function daerahchangeX(v_t){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="daerahchangeX";
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
		
	}

	function negerichangeupX(v_t){		
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangeupX";
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
	
	}
	function negerichangepemajuupX(v_t){		
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangepemajuupX";
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
	
	}

	function daerahchangeupX(v_t){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="daerahchangeupX";		
		document.f1.action="";
		document.f1.v_tab.value = v_t;
		document.f1.submit();
		
	}

	function add_HtaamX(id,paramOnline){
		if (document.f1.radioHtaamViewX1.checked == true ){
		    var dt=document.f1.txtTarikhPerjanjianHtaamX	
	    	var currentTime = new Date();
	    	var month = currentTime.getMonth() + 1;
			var day = currentTime.getDate();
			var year = currentTime.getFullYear();
			var currentDate = day + "/" + month + "/" + year;
		
		   var str1  = document.getElementById("txtTarikhPerjanjianHtaamX").value;
		    
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		  
		   var date1 = new Date(yr1, mon1, dt1); 
   		}
   
		var b1=parseInt(document.f1.txtBahagianSimati1X.value);
	   	var b2=parseInt(document.f1.txtBahagianSimati2X.value);

	    if (document.f1.socNegeriHtaamX.value=="") {
			alert("Sila pilih negeri");
			document.f1.socNegeriHtaamX.focus();
			return; 
		} else if (document.f1.socDaerahHtaamX.value=="") {
			alert("Sila pilih daerah");
			document.f1.socDaerahHtaamX.focus();
			return; 
		}  else if (document.f1.socMukimHtaamX.value=="") {
			alert("Sila pilih mukim");
			document.f1.socMukimHtaamX.focus();
			return; 
		} else if ( document.f1.txtPoskodPemaju1HtaamX.value != "" && document.f1.txtPoskodPemaju1HtaamX.value.length < 5 ) {
			alert("Sila masukkan nombor poskod alamat pemaju dengan lengkapnya");
			document.f1.txtPoskodPemaju1HtaamX.focus();
			return; 
		} else if(document.f1.txtBahagianSimati1X.value == "" && document.f1.txtBahagianSimati2X.value == "" ){
		     alert('Sila masuk bahagian simati');
		 	document.f1.txtBahagianSimati1X.focus(); 
			return; 
		} else if(document.f1.txtBahagianSimati1X.value != "" && document.f1.txtBahagianSimati2X.value == "" ) {
	            alert('Sila masuk bahagian simati selengkapnya');
		  		document.f1.txtBahagianSimati2X.focus(); 
				return; 
	    } else if(document.f1.txtBahagianSimati2X.value != "" && document.f1.txtBahagianSimati1X.value == "" ) {
	            alert('Sila masuk bahagian simati selengkapnya');
		  		document.f1.txtBahagianSimati1X.focus(); 
				return; 
	    } else if(b1>b2) {
	            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
		  		document.f1.txtBahagianSimati1X.focus(); 
				return; 
		} else if(document.f1.radioHtaamViewX1.checked == true && document.f1.txtPoskodHartaHtaamX.value != "" && document.f1.txtPoskodHartaHtaamX.value.length < 5 ){
	  		alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
			document.f1.txtPoskodHartaHtaamX.focus();
			return; 
		} else if (document.f1.radioHtaamViewX1.checked == true && dt.value!="" && isDate(dt.value)==false){
			dt.focus()
			return false
		} else if (document.f1.radioHtaamViewX1.checked == true &&  date1 > currentTime){
			alert("Sila pastikan tarikh perjanjian tidak melebihi dari tarikh hari ini.");
			//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
			document.f1.txtTarikhPerjanjianHtaamX.focus();
		/*	}
		
		else if(document.f1.txtNilaiTarikhMatiHtaamX.value == 0)
		{
		
		alert("Sila pastikan nilai harta pada tarikh mati melebihi RM 0");
			//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
			document.f1.txtNilaiTarikhMatiHtaamX.focus();
		}
		else if(document.f1.txtNilaiTarikhMohonHtaaX.value == 0)
		{
		
		alert("Sila pastikan nilai harta pada tarikh mohon melebihi RM 0");
			//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
			document.f1.txtNilaiTarikhMohonHtaaX.focus(); */
	
		}else if(document.f1.save_harta.value == "yes" ) {
            alert('Sila masukkan maklumat hta dibawah jagaan unit terlebih dahulu!');
	  	//	document.f1.txtBahagianSimati1.focus(); 
			return; 
		
		} else{
			input_box = confirm("Adakah anda pasti?");
			if (input_box == true) {
			/*	
			   document.f1.command.value="HtaamX";
			document.f1.mode.value="masukkanX";		
			document.f1.action="";
			document.f1.submit();   
			 */
		  
			    document.f1.method="POST";
			    document.f1.command.value="HtaamX";
				document.f1.mode.value="masukkanX";	
				document.f1.upload.value="simpanUpload";	
	// 		    var x = create_request_string(document.f1);		    
	// 	   		document.f1.enctype="multipart/form-data";
	// 	  		document.f1.encoding="multipart/form-data"; 
	  		
	// 			if(paramOnline == 'seksyen8online')
	// 				document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon&"+x;
	// 			else
	// 			    document.f1.action = "?_portal_module=FrmPrmhnnSek8Internal&"+x;
				document.f1.action="";
		    	document.f1.submit();  
		    
			}else{
				return;
			}
     
		}
	
	}

if(document.f1.upload.value=='simpanUpload' && document.f1.paramOnline.value != ''){
	document.f1.mode.value = "HtaamviewX";
	document.f1.command.value = "HtaamX";
	document.f1.upload.value = "";
// 	document.f1.action = "?_portal_module=FrmPrmhnnBorangAMaklumatPemohon";
	
// 	if(document.f1.paramOnline.value == 'seksyen8online')
// 		document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon";
// 	else
// 	    document.f1.action = "?_portal_module=FrmPrmhnnSek8Internal";
	document.f1.action="";
	document.f1.submit();
}

	// Diguna oleh pengguna dalaman
	function edit_HtaamX(idhtaam){
	    document.f1.idhtaamXid.value = idhtaam;
		document.f1.command.value="HtaamX";
		document.f1.mode.value="kemaskiniHtaamX";
		document.f1.action="";
		document.f1.submit();
	
	}

	function save_HtaamX(id_PermohonanSimati,idhta,idSimati,idDokumen,paramOnline){
		if(document.f1.flag.value == "1") {
			var dt=document.f1.txtTarikhPerjanjianHtaamX;
   			var currentTime = new Date();
    		var month = currentTime.getMonth() + 1;
			var day = currentTime.getDate();
			var year = currentTime.getFullYear();
			var currentDate = day + "/" + month + "/" + year;
	
   			var str1  = document.getElementById("txtTarikhPerjanjianHtaamX2").value;    
   			var dt1   = parseInt(str1.substring(0,2),10);
			var mon1  = parseInt(str1.substring(3,5),10)-1;
			var yr1   = parseInt(str1.substring(6,10),10); 
			var date1 = new Date(yr1, mon1, dt1);
   
   		}
 		
		var b1=parseInt(document.f1.txtBahagianSimati1X.value);
 		var b2=parseInt(document.f1.txtBahagianSimati2X.value);
		//alert(":::::2");		 
 		if (document.f1.socNegeriHtaamX.value=="") {
			alert("Sila pilih negeri");
			document.f1.socNegeriHtaamX.focus();
			return; 
		
 		} else if (document.f1.socDaerahHtaamX.value=="") {
			alert("Sila pilih daerah");
			document.f1.socDaerahHtaamX.focus();
			return; 
		
 		}else if (document.f1.socMukimHtaamX.value=="") {
			alert("Sila pilih mukim");
			document.f1.socMukimHtaamX.focus();
			return; 
		
 		}else if ((document.f1.flag.value == "1") 
 			&& (document.f1.txtPoskodHartaHtaamX.value != "" 
 			&& document.f1.txtPoskodHartaHtaamX.value.length < 5)) {
   
			alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
			document.f1.txtPoskodHartaHtaamX.focus();
			return; 
		
 		}else if (document.f1.txtPoskodPemaju1HtaamX.value != "" 
 			&& document.f1.txtPoskodPemaju1HtaamX.value.length < 5) {
			alert("Sila masukkan nombor poskod alamat pemaju dengan lengkapnya");
			document.f1.txtPoskodPemaju1HtaamX.focus();
			return; 
	
 		}else if(document.f1.txtBahagianSimati1X.value == "" 
 			&& document.f1.txtBahagianSimati2X.value == "" ){
       		alert('Sila masuk bahagian simati');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            
 		}else if(document.f1.txtBahagianSimati1X.value != "" 
 			&& document.f1.txtBahagianSimati2X.value == "" ){
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati2X.focus(); 
			return; 
            
 		}else if(document.f1.txtBahagianSimati2X.value != "" 
 			&& document.f1.txtBahagianSimati1X.value == "" ){
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            
 		}else if(b1>b2){
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            
 		}else if (document.f1.flag.value == "1"  
 			&& dt.value!="" && isDate(dt.value)==false){
			dt.focus()
			return false;
			
		}else if (document.f1.flag.value == "1" &&  date1 > currentTime){
			alert("Sila pastikan tarikh perjanjian tidak melebihi dari tarikh hari ini.");
			//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
			document.f1.txtTarikhPerjanjianHtaamX.focus();
	
		/*}
		
		else if(document.f1.txtNilaiTarikhMatiHtaamX.value == 0)
		{
		
		alert("Sila pastikan nilai harta pada tarikh mati melebihi RM 0");
			//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
			document.f1.txtNilaiTarikhMatiHtaamX.focus();
		}
		
		else if(document.f1.txtNilaiTarikhMohonHtaaX.value == 0)
		{
		
		alert("Sila pastikan nilai harta pada tarikh mohon melebihi RM 0");
			//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
			document.f1.txtNilaiTarikhMohonHtaaX.focus();
		*/
		
		}else if(document.f1.save_harta.value == "yes" ){
            alert('Sila masukkan maklumat hta dibawah jagaan unit terlebih dahulu!');
	  	//	document.f1.txtBahagianSimati1.focus(); 
			return; 
            
		}else{
		
			input_box = confirm("Adakah anda pasti?");
			if (input_box == true) {		
			/***/
				document.f1.command.value="HtaamX";
				document.f1.mode.value="simpanHtaamX";		
				document.f1.action="";	
				//document.f1.submit(); 		

/* 				document.f1.id_Permohonansimati.value= id_PermohonanSimati;
		    	document.f1.idDokumen.value=idDokumen;
		    	document.f1.idSimati.value=idSimati;
		    	document.f1.idhtaamid.value=idhta;
		    	document.f1.command.value="HtaamX";
				document.f1.mode.value="simpanHtaamX";	
				document.f1.upload.value="simpanUpload";	
		    	var x = create_request_string(document.f1); */		    

		    	/*  	   			
		    	document.f1.enctype="multipart/form-data";
 	  			document.f1.encoding="multipart/form-data"; 
 				if(paramOnline == 'seksyen8online')
 					document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon&"+x;
 				else
 			    	document.f1.action = "?_portal_module=ekptg.view.ppk.FrmPrmhnnSek8Internal&"+x;
 				*/
 				document.f1.submit();  
		
			}else{
				return;
			}

		}

	}

function hapus_HtaamX(){
input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.f1.command.value="HtaamX";
		document.f1.mode.value="hapusHtaamX";		
		document.f1.action="";
		document.f1.submit();
		}else
		{
		return;
		}
}

	// Diguna oleh pengguna dalaman
	function batal_HtaamX(){
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.f1.command.value="HtaamX";
			document.f1.mode.value="kemaskiniHtaamX";
			document.f1.action="";
			document.f1.submit();
			
		}
		
	}

function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
		if(content != "")
	{
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	}
	else
	{
	elmnt.value ="";
	return;
	}
}

</script>

<script language = "Javascript">
/**
 * DHTML date validation script for dd/mm/yyyy. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yg sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yg sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yg sah")
		return false
	}
return true
}

function ValidateForm(){
	var dt=document.f1.txtTarikhPerjanjianHtaamX
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
 }

function ValidateForm1(){
	var dt=document.f1.txtTarikhPerjanjianHtaamX
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
 }
 
//socJenisLuasHtaam
//txtLuasAsalHtaam
//socKategoriTanahHtaam
//txtLuasHMpHtaam

//socKategoriTanahHtaamX

//txtLuasAsalHtaamX

//socJenisLuasHtaamX

//txtLuasHMpHtaamX

function convertTo(){
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
	
    
}

function getConversion(){
if(document.f1.txtLuasAsalHtaamX.value != "" )
{
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 

}



function getConvert(){
if(document.f1.socKategoriTanahHtaamX.value == 1 ||  document.f1.socKategoriTanahHtaamX.value == 2 || document.f1.socKategoriTanahHtaamX.value == 3 || document.f1.socKategoriTanahHtaamX.value == 4 || document.f1.socKategoriTanahHtaamX.value == 5  || document.f1.socKategoriTanahHtaamX.value == 6  )
{
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 
 else
 {
  document.f1.socKategoriTanahHtaamX.value = "";
  document.f1.txtLuasHMpHtaamX.value = "";
  document.f1.meterhektar.value= "";
  document.f1.txtLuasAsalHtaamX.value = "";
 
 }

}


 //socKategoriTanahHtaamUp
 //txtLuasAsalHtaamUpd
 //socJenisLuasHtaamUpd
 //txtLuasHMpHtaamUpd
 

//socKategoriTanahHtaamX
//txtLuasAsalHtaamX
//socJenisLuasHtaamX
//txtLuasHMpHtaamX
  
 
 function convertToU(){
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
	
    
}

function getConversionU(){
if(document.f1.txtLuasAsalHtaamX.value != "" )
{
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 

}



function getConvertU(){
if(document.f1.socKategoriTanahHtaamX.value == 1 ||  document.f1.socKategoriTanahHtaamX.value == 2 || document.f1.socKategoriTanahHtaamX.value == 3 || document.f1.socKategoriTanahHtaamX.value == 4 || document.f1.socKategoriTanahHtaamX.value == 5  || document.f1.socKategoriTanahHtaamX.value == 6  )
{
	if (document.f1.socJenisLuasHtaamX.value=="4" || document.f1.socJenisLuasHtaamX.value=="7"){
        var a = document.f1.txtLuasAsalHtaamX.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamX.value=="2"){
        var a = document.f1.txtLuasAsalHtaamX.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamX.value=="3"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamX.value=="5"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamX.value=="1"){
        var a = document.f1.txtLuasAsalHtaamX.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamX.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamX.value=num1.toFixed(2);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamX.value=="1" || document.f1.socKategoriTanahHtaamX.value=="3" || document.f1.socKategoriTanahHtaamX.value=="4" || document.f1.socKategoriTanahHtaamX.value=="5" || document.f1.socKategoriTanahHtaamX.value=="6")        
        {
        document.f1.txtLuasHMpHtaamX.value=num.toFixed(2);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 
 else
 {
  document.f1.socKategoriTanahHtaamX.value = "";
  document.f1.txtLuasHMpHtaamX.value = "";
  document.f1.meterhektar.value= "";
  document.f1.txtLuasAsalHtaamX.value = "";
 
 }

}

function cetakNilaiHarta(noFail) 
{
  //  var url = "../servlet/ekptg.report.ppk.NilaianHartaPPSPP?nofail="+noFail;
  var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=NilaianHartaPPSPP&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function tukarstatus()
{
 document.f1.command.value="HtaamX";
		document.f1.mode.value="getHtaamStatus";		
		document.f1.action="";
		document.f1.submit();
    
}

function cetak(noFail,idhta) 
{

//alert(document.f1.jpphlepas.value)
if(document.f1.jpphlepas.value == "no")
{

 
  var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratIringanJPPH_TM&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
 
 
}

if(document.f1.jpphlepas.value == "yes")
{	
 
    var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratIringanJPPH&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
     
 }   
}

function cetakSuratPeringatan(noFail,idhta) 
{
    //var url = "../servlet/ekptg.report.ppk.SuratPeringatanJPPH?NO_FAIL="+noFail;
	
	/* var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratPeringatan&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/
	
	 if(document.f1.jpphlepas.value == "no")
{
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratPeringatan_TM&flagReport=S";    
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}	


if(document.f1.jpphlepas.value == "yes")
{
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idhta="+idhta+"&report=SuratPeringatan&flagReport=S";    
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}	
    
      
    
}

function cetakNilaiHarta(noFail) 
{
  //  var url = "../servlet/ekptg.report.ppk.NilaianHartaPPSPP?nofail="+noFail;
  var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=NilaianHartaPPSPP&flagReport=B";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
 
</script>

<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

	function submitForm() {    
		if('$!val_tab' != "" && '$!val_tab' != null){
			window.location.hash='$!val_tab';
	  		// goTo('$!val_tab');
	  		var nextFieldID = '$!val_tab';
	   		document.getElementById(nextFieldID).focus();
	   }else{
			window.location.hash='maklumat_pemohon';
			//goTo('maklumat_pemohon');
			var nextFieldID = 'maklumat_pemohon';
	   		document.getElementById(nextFieldID).focus();
		}
		
	} 

 function trans_date(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txtTarikhPerjanjianHtaamX.value = trans;

}
else
{
return;
}

}

function bahagiansimati() { 
if(document.f1.txtBahagianSimati1X.value != "" && document.f1.txtBahagianSimati2X.value != "")
{
if(document.f1.txtBahagianSimati1X.value == document.f1.txtBahagianSimati2X.value )
{
document.f1.txtBahagianSimati1X.value = "1";
document.f1.txtBahagianSimati2X.value = "1";
}
}     
  
} 

function kembalix() {
	document.f1.method = "POST";
	document.f1.command.value="papar";
	document.f1.action = "";
	document.f1.submit();
}
function kembalidaftar()
{
        document.f1.command.value="kembali_daftar_pemohon";
		document.f1.eventStatus.value="1";
		document.f1.action = "";
		document.f1.submit();
}

function kembaliSenaraiFail(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.f1.submit();
}
function kembaliSenaraiPermohonan(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.f1.method="POST";
	document.f1.submit();
}
function Kembali(){
	document.f1.method="POST";
	document.f1.command.value="xxx";
	document.f1.action = "";
	document.f1.submit();
}

	function CheckBandarTetap(){
		if(document.f1.socNegeriPemajuHtaamX.value == "" || document.f1.socNegeriPemajuHtaamX.value == "0"){
		  alert("Sila pilih negeri terlebih dahulu.");
		  document.f1.socNegeriPemajuHtaamX.focus();
		  return;
		}
	}
	    
	function CheckBandarSurat(){
		if(document.f1.socNegeriHtaamX.value == "" || document.f1.socNegeriHtaamX.value == "0"){
		  alert("Sila pilih negeri terlebih dahulu.");
		  document.f1.socNegeriHtaamX.focus();
		  return;
		}
	}

function samakan1(){
	document.f1.txtNilaiTarikhMatiHtaamX.value=document.f1.txtNilaiTarikhMohonHtaaX.value
}
 
function samakan2(){
	document.f1.txtNilaiTarikhMatiHtaamX.value=document.f1.txtNilaiTarikhMohonHtaaX.value
}

function no_lot1()
{
//alert("ss")
if(document.f1.id_harta.value != "" && document.f1.id_harta.value != null)
{
document.f1.no_lot_hta.value = document.f1.txtNoPTHtaamUp.value
}else
{
document.f1.no_lot_hta.value = document.f1.txtNoPTHtaam.value
}
}

function no_lot2(){
	document.f1.no_lot_hta.value = document.f1.txtNoPTHtaamUp.value
}

function checklot()
{
	if('$!skrin_online_popup' == "yes")
{
url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
}
else
{
url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
}
	actionName = "check_no_lot";
	//actionName = "check_harta";
	
	target = "checklot";
	doAjaxUpdater(document.f1, url, target, actionName);
}

	function check_harta(){
		if('$!skrin_online' != 'yes'){
			if('$!skrin_online_popup' == "yes"){
				url = "../../servlet/ekptg.view.ppk.PendaftaranCheck";
			}else{
				url = "../servlet/ekptg.view.ppk.PendaftaranCheck";
			}
			
			actionName = "check_harta";	
			target = "check_daerah_harta";
			doAjaxUpdater(document.f1, url, target, actionName);
		
		}
		
	}

	function daerah_harta(){
		if(document.f1.id_harta.value != "" && document.f1.id_harta.value != null){
			document.f1.id_daerah_harta.value = document.f1.socDaerahHtaamX.value
		}else{
			document.f1.id_daerah_harta.value = document.f1.socDaerahHtaamX.value
		}
		
	}

function intNilaianJPPH_lama(NO_FAIL, ID_HTAAH) {
	// kmie, 20100721
	// to cater for integration
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&selectedTab=1&NO_FAIL=" + NO_FAIL + "&ID_HTAAH=" + ID_HTAAH;
	document.f1.method = "POST";
	document.f1.submit();
}

	function viewSPTB_lama(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN) {


	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewSPTB&action2=viewSPTB&ID_FAIL=" + ID_FAIL+"&ID_HAKMILIK=" + ID_HAKMILIK+"&ID_SEKSYEN=" + ID_SEKSYEN;
	document.f1.submit();
}                        
function checkWSHM_lama(ID_HAKMILIK) {
	// kmie, 20100906
	// check hakmilik status with eTanah
    var url = "../x/${securityToken}/ekptg.view.integrasi.FrmViewETanah?action2=checkHM&ID_HAKMILIK=" + ID_HAKMILIK;
    var hWnd = window.open(url, 'Semakan Status Hakmilik di Sistem eTanah', 'width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

<!-- script integrasi -->
function intNilaianJPPH(ID_PERMOHONAN, ID_HTA) {
	// kmie, 20100721
	// to cater for integration
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewNilaianHartaTakAlih&action2=viewNilaianHTA&JENIS_HTA=1&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_HTA=" + ID_HTA;
	document.f1.method = "POST";
	document.f1.submit();
}

function viewSPTB(ID_FAIL,ID_HAKMILIK,ID_SEKSYEN) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewSPTB&action2=viewSPTB&ID_FAIL=" + ID_FAIL+"&ID_HAKMILIK=" + ID_HAKMILIK+"&ID_SEKSYEN=" + ID_SEKSYEN;
	document.f1.submit();
}                        
function checkWSHM(ID_HAKMILIK) {
	// kmie, 20100906
	// check hakmilik status with eTanah
    var url = "../x/${securityToken}/ekptg.view.integrasi.FrmViewETanah?action2=checkHM&ID_HAKMILIK=" + ID_HAKMILIK;
    var hWnd = window.open(url, 'Semakan Status Hakmilik di Sistem eTanah', 'width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
<!-- end script integrasi -->

function tukarjenis_HtaamX(){
	if(document.f1.flag.value == "1"){
		var dt=document.f1.txtTarikhPerjanjianHtaamX

   		var currentTime = new Date();
	    var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var currentDate = day + "/" + month + "/" + year;
	
   		var str1  = document.getElementById("txtTarikhPerjanjianHtaamX2").value;
    
   		var dt1   = parseInt(str1.substring(0,2),10);
	   	var mon1  = parseInt(str1.substring(3,5),10)-1;
	   	var yr1   = parseInt(str1.substring(6,10),10);
	  
	   	var date1 = new Date(yr1, mon1, dt1);
   
   	}
 	
	var b1=parseInt(document.f1.txtBahagianSimati1X.value);
 	var b2=parseInt(document.f1.txtBahagianSimati2X.value);
	if (document.f1.socNegeriHtaamX.value=="") {
		alert("Sila pilih negeri");
		document.f1.socNegeriHtaamX.focus();
		return; 
	}else if (document.f1.socDaerahHtaamX.value=="") {
		alert("Sila pilih daerah");
		document.f1.socDaerahHtaamX.focus();
		return; 
	}else if (document.f1.socMukimHtaamX.value=="") {
		alert("Sila pilih mukim");
		document.f1.socMukimHtaamX.focus();
		return; 
	/*} 
	
	else if ((document.f1.flag.value == "1") && (document.f1.txtPoskodHartaHtaamX.value != "" && document.f1.txtPoskodHartaHtaamX.value.length < 5)) 
	{
   
		alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
		document.f1.txtPoskodHartaHtaamX.focus();
		return; 
	
	
	}
	
	else if (document.f1.txtPoskodPemaju1HtaamX.value != "" && document.f1.txtPoskodPemaju1HtaamX.value.length < 5) {
		alert("Sila masukkan nombor poskod alamat pemaju dengan lengkapnya");
		document.f1.txtPoskodPemaju1HtaamX.focus();
		return; 
	}
	
	else if(document.f1.txtBahagianSimati1X.value == "" && document.f1.txtBahagianSimati2X.value == "" )
            {
            alert('Sila masuk bahagian simati');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
	
	
	else if(document.f1.txtBahagianSimati1X.value != "" && document.f1.txtBahagianSimati2X.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati2X.focus(); 
			return; 
            }
            
            else if(document.f1.txtBahagianSimati2X.value != "" && document.f1.txtBahagianSimati1X.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
                        
            else if(b1>b2)
            {
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
			
			
    else if (document.f1.flag.value == "1"  && dt.value!="" && isDate(dt.value)==false){
		dt.focus()
		return false
	}
	else if (document.f1.flag.value == "1" &&  date1 > currentTime){
		alert("Sila pastikan tarikh perjanjian tidak melebihi dari tarikh hari ini.");
		//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
		document.f1.txtTarikhPerjanjianHtaamX.focus();
	}
	
	
	  else if(document.f1.save_harta.value == "yes" )
            {
            alert('Sila masukkan maklumat hta dibawah jagaan unit terlebih dahulu!');
	  	//	document.f1.txtBahagianSimati1.focus(); 
			return; 
    */
    	
     	}else{	
			document.f1.flag_tukar_jenis_hta.value="TIADA";
			document.f1.command.value="HtaamX";
			document.f1.mode.value="kemaskiniHtaamX";		
			document.f1.action="";	
			document.f1.submit();
		
		}

	}

function tukarjenis_Htaam(){

//alert(":::::1");

if(document.f1.flag.value == "1")
{
var dt=document.f1.txtTarikhPerjanjianHtaamX

   var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
   var str1  = document.getElementById("txtTarikhPerjanjianHtaamX2").value;
    
   var dt1   = parseInt(str1.substring(0,2),10);
   var mon1  = parseInt(str1.substring(3,5),10)-1;
   var yr1   = parseInt(str1.substring(6,10),10);
  
   var date1 = new Date(yr1, mon1, dt1);
   
   }
 var b1=parseInt(document.f1.txtBahagianSimati1X.value);
 var b2=parseInt(document.f1.txtBahagianSimati2X.value);
			 
//alert(":::::2");		 
 if (document.f1.socNegeriHtaamX.value=="") {
		alert("Sila pilih negeri");
		document.f1.socNegeriHtaamX.focus();
		return; 
	} 
	//alert(":::::3");
	else if (document.f1.socDaerahHtaamX.value=="") {
		alert("Sila pilih daerah");
		document.f1.socDaerahHtaamX.focus();
		return; 
	} 
	//alert(":::::4");
	else if (document.f1.socMukimHtaamX.value=="") {
		alert("Sila pilih mukim");
		document.f1.socMukimHtaamX.focus();
		return; 
	} 
	/*
	else if ((document.f1.flag.value == "1") && (document.f1.txtPoskodHartaHtaamX.value != "" && document.f1.txtPoskodHartaHtaamX.value.length < 5)) 
	{
   
		alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
		document.f1.txtPoskodHartaHtaamX.focus();
		return; 
	
	
	}
	
	else if (document.f1.txtPoskodPemaju1HtaamX.value != "" && document.f1.txtPoskodPemaju1HtaamX.value.length < 5) {
		alert("Sila masukkan nombor poskod alamat pemaju dengan lengkapnya");
		document.f1.txtPoskodPemaju1HtaamX.focus();
		return; 
	}
	
	else if(document.f1.txtBahagianSimati1X.value == "" && document.f1.txtBahagianSimati2X.value == "" )
            {
            alert('Sila masuk bahagian simati');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
	
	
	else if(document.f1.txtBahagianSimati1X.value != "" && document.f1.txtBahagianSimati2X.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati2X.focus(); 
			return; 
            }
            
            else if(document.f1.txtBahagianSimati2X.value != "" && document.f1.txtBahagianSimati1X.value == "" )
            {
            alert('Sila masuk bahagian simati selengkapnya');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
                        
            else if(b1>b2)
            {
            alert('Sila pastikan bahagian simati pada kotak yang pertama lebih besar atau sama dengan kotak yang kedua');
	  		document.f1.txtBahagianSimati1X.focus(); 
			return; 
            }
			
			
    else if (document.f1.flag.value == "1"  && dt.value!="" && isDate(dt.value)==false){
		dt.focus()
		return false
	}
	else if (document.f1.flag.value == "1" &&  date1 > currentTime){
		alert("Sila pastikan tarikh perjanjian tidak melebihi dari tarikh hari ini.");
		//document.f1.txtTarikhPerjanjianHtaamX.value=currentDate;		
		document.f1.txtTarikhPerjanjianHtaamX.focus();
	}
	
	  else if(document.f1.save_harta.value == "yes" )
            {
            alert('Sila masukkan maklumat hta dibawah jagaan unit terlebih dahulu!');
	  	//	document.f1.txtBahagianSimati1.focus(); 
			return; 
            }
    */
	else{
	
	    document.f1.flag_tukar_jenis_hta.value="ADA";
		document.f1.command.value="Htaam";
		document.f1.mode.value="kemaskiniHtaam";	
		document.f1.action="";	
		document.f1.submit();
		

	}
	

}
	//Mula Lampiran
	function lampiranHarta(idHarta) {
		var url = "../x/${securityToken}/ekptg.view.ppk.util.FrmUploadDokumenHarta?actionrefresh=paparHTATH&actionPopup=papar&idHarta="+idHarta+"&flagOnline="+flagOnline;
	    //
	    var hWnd = window.open(url,'printuser','width=400,height=200, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus(); /**/
	    //
	    var title = 'Cetakan';
		var w =1024;
		var h = 800;
	    var left = (screen.width/2)-(w/2);
	    //var top = (screen.height/2)-(h/2);
	    //return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);

	}
	function lampiranHartaPapar(id_){
		var url = "../servlet/ekptg.view.ppk.util.DisplayBlobHarta?iDokumen="+id_+"&tablename=hta";
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	//Tamat Lampiran

</script>

</body>
</html>
