<script type="text/javascript" src="../../library/js/CalendarPopup.js" ></script>
<link rel="stylesheet" type="text/css" href="../../library/js/jquery.timepickr.css" />
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />

<style type="text/css">
<!-- 
input[readonly] {
	background-color:#AAD198;
	border:#546F0E 1px solid;
	color:#000;
}
-->
</style>
<body>
<fieldset>
<legend><font style="font-family:Verdana; font-size:8pt;font-weight:bold;">HARTA TAK ALIH (ADA HAKMILIK) </font></legend>
                          <table width="100%" border="0">
                         <!-- frmPrmhnnSek8HTAAH --> 
                            <tr>
                              <!-- <input type="hidden" name="idhta" id="idhta" value="idhta" />-->
                              <input type="hidden" name="idPermohonanSimati" id="idPermohonanSimati" value="$idPermohonanSimati" />
                              #set($idhta = $listamid.idhta)
                              <td valign="top" width="50%"><table width="100%" border="0">
                                  <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                                    <td width="29%" class="style38"><div align="right" class="style44">
                                        <div align="left">#if($readmodenegeri != "disabled")Negeri#else Negeri#end</div>
                                      </div></td>
                                    <td width="1%">:</td>
                                    <td width="70%"> 
                                    #foreach($listnegpomo in $listnegeri)
                                    	#if($listamid.negeri==$listnegpomo.id_Negeri)
 									   		#set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                     		#set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)   
                                      	#end 
                        			#end
                                      
                                      
                                      #if($readmode == "disabled")
                                      
                                      #if($listamid.negeri!="" && $listamid.negeri!="0" )
                                      <input name="n" value="$!kodNegeri - $!namaNegeri" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="n" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      #else
                                      #if($listamid.negeri!="")
                                      <input name="n" value="$!kodNegeri - $!namaNegeri" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="n" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      <!-- <input name="txtNoHakmilikHtaamUp" type="text" id="txtNoHakmilikHtaam2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri" size="50" maxlength="50" $readmodeR class="$readmode"  />-->
                                      #end          #end </td>
                                  </tr>
                                  <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                                   <!-- Kemaskini -->
                                    <td class="style38" ><div align="right" class="style44">
                                        <div align="left">#if($readmodedaerah != "disabled")Daerah#else Daerah#end</div>
                                      </div></td>
                                    <td>:</td>
                                    <td>#foreach($listdaerah in $listdaerah)
                                      
                                      #if($listamid.daerah==$listdaerah.id)
                                      
                                      #set($listDaerahbyNegeriK=$listdaerah.kod)
                                      #set($listDaerahbyNegeriN=$listdaerah.nama)
                                 
                                      #end 
                                      #end
                                      
                                      #if($readmode == "disabled")
                                      
                                      #if($listamid.daerah!="" && $listamid.daerah!="0" )
                                      <input name="d" value="$!kodDaerah - $!namaDaeraH" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="d" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      #else
                                      
                                      
                                      #if($listamid.daerah!="")
                                      <input name="d" value="$!kodDaerah - $!namaDaeraH" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="d" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end           #end 
                                      
                                      #if($readmode != "disabled") <span id="check_daerah_harta" style="color:red" ></span> #end </td>
                                  </tr>
                                  <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                                    <td class="style38" ><div align="right" class="style44">
                                        <div align="left">#if($readmodemukim != "disabled")Mukim#else Mukim#end</div>
                                      </div></td>
                                    <td>:</td>
                                    <td>#foreach($listmukim in $listmukim)
                        
                                      #if($listamid.mukim==$listmukim.id)
                                      
                                      #set($idMukim=$listmukim.kod)
                                      #set($listMukimbyDaerahN=$listmukim.nama)
                               
                                      #end 
                                      #end
                                      
                                      #if($readmode == "disabled")
                                      
                                      #if($listamid.mukim!="" && $listamid.mukim!="0" )
                                      <input name="m" value="$!kodMukim - $!namaMukim" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="m" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      #else
                             
                                      #if($listamid.mukim!="")
                                      <input name="m" value="$!kodMukim - $!namaMukim" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="m" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end                #end </td>
                                  </tr>
                                  <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                                    <td class="style38" ><div align="right" class="style44">
                                        <div align="left">#if($readmode != "disabled")Jenis Hakmilik#else Jenis Hakmilik#end </div>
                                      </div></td>
                                    <td>:</td>
                                    <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                                      
                                      #if($listamid.jenishakmilik==$listjenishakmilik.id)
                                      
                                      #set($jenisHM=$listjenishakmilik.kod)
                                      #set($listjenishakmilikN=$listjenishakmilik.keterangan)
                                      
                                      
                                      
                                      
                                      
                                      #end 
                                      #end
                                      
                                      #if($readmode == "disabled")
                                      
                                      #if($listamid.jenishakmilik!="" && $listamid.jenishakmilik!="0")
                                      <input name="jh" value="$!kodHM - $!keteranganHM" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="jh" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      #else
                                
                                      #if($listamid.jenishakmilik!="")
                                      <input name="jh" value="$!kodHM - $!keteranganHM" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="jh" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end                    #end </td>
                                  </tr>
                                  <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                                    <td class="style38 style44"><div align="right">
                                      <div align="left">#if($readmode != "disabled")No Hakmilik#else No Hakmilik#end </div></td>
                                    <td>:</td>
                                    <td><label>
                                      <input name="txtNoHakmilikHtaamUp" type="text" id="txtNoHakmilikHtaam2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$!noHakmilik" size="50" maxlength="50" $readmodeR class="$readmode"  />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                                    <td class="style38"><div align="right" class="style44">
                                        <div align="left">#if($readmode != "disabled")No PT / No Lot#else No PT / No Lot #end </div>
                                      </div></td>
                                    <td>:</td>
                                    <td><label>
                                      <input name="txtNoPTHtaamUp" type="text" id="txtNoPTHtaamUp" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$!noPt" size="15" maxlength="50" $readmodeR class="$readmode" onkeyup="no_lot1();checklot()"   />
                                      </label>
                                      #if($readmode != "disabled") <span id="checklot" style="color:red" ></span> #end </td>
                                  </tr>
                                  
                                   <!--    <!-- Salnizam edit starts --> 
                                                           
                                    <tr>
                                    <td class="style38" valign="top" ></td>
                                    <td class="style38"><div align="right" class="style44">
                                        <div align="left">#if($readmode != "disabled")Alamat Harta#else Alamat Harta #end </div>
                                      </div></td>
                                    <td>:</td>
                                    <td><label>
                                      <input name="txtAlamat1Htaam1" type="text" id="txtAlamat1Htaam1" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$!alamat1" size="50" maxlength="50" $readmodeR class="$readmode"    />
                                      </label>
                                      #if($readmode != "disabled") <span id="checklot" style="color:red" ></span> #end </td>
                                  </tr>
                                  
                                   <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")#end</td>
                                    <td class="style38"><div align="right" class="style44">
                                        <div align="left">#if($readmode != "disabled")#else   #end </div>
                                      </div></td>
                                    <td></td>
                                    <td><label>
                                    
                                      <input name="txtAlamat2Htaam" type="text" id="txtAlamat2Htaam" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$!alamat2" size="50" maxlength="50" $readmodeR class="$readmode"    />
                                      </label>
                                      #if($readmode != "disabled") <span id="checklot" style="color:red" ></span> #end </td>
                                  </tr>
                                  
                                   <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")#end</td>
                                    <td class="style38"><div align="right" class="style44">
                                        <div align="left">#if($readmode != "disabled")#else #end </div>
                                      </div></td>
                                    <td></td>
                                    <td><label>
                                      <input name="txtAlamat3Htaam" type="text" id="txtAlamat3Htaam" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$!listamid.alamat3" size="50" maxlength="50" $readmodeR class="$readmode" onkeyup="no_lot1();checklot()"   />
                                      </label>
                                      #if($readmode != "disabled") <span id="checklot" style="color:red" ></span> #end </td>
                                  </tr>
                                   <tr>
                                    <td class="style38" valign="top" ></td>
                                    <td class="style38"><div align="right" class="style44">
                                        <div align="left">#if($readmode != "disabled")Poskod#else Poskod #end </div>
                                      </div></td>
                                    <td>:</td>
                                    <td><label>
                                      <input name="txtAlamatPoskodHtaam" type="text" id="txtAlamatPoskodHtaam" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$!poskodhta" size="5" maxlength="5" $readmodeR class="$readmode" onkeyup="no_lot1();checklot()"   />
                                      </label>
                                      #if($readmode != "disabled") <span id="checklot" style="color:red" ></span> #end </td>
                                  </tr>
                                  <!-- 
                                  <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                                    <td class="style38"><div align="right" class="style44">
                                        <div align="left">#if($readmode != "disabled")Bandar3333#else Bandar3333 #end </div>
                                      </div></td>
                                    <td>:</td>
                                    <td><label>
                                      <input name="txtAlamatBandarHtaam" type="text" id="txtAlamatBandarHtaam" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listamid.bandar" size="30" maxlength="50" $readmodeR class="$readmode" onkeyup="no_lot1();checklot()"   />
                                      </label>
                                      #if($readmode != "disabled") <span id="checklot" style="color:red" ></span> #end </td>
                                  </tr>
                                  
                                   -->
               
                                  <tr>
                                                              
                                    <td class="style38 style43 style45"></td>
                                   <!-- Kemaskini -->
                                    <td class="style38"><div align="right" class="style57">
                                      <div align="left">Bandar</div>
                                    </div></td>
                                   
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                     <td><label>
                                        #set($listneg = "")
                                      
                                   		#foreach($listneg in $listBandarSuratbyNegeri)  
                                   			
                                   				#if($listamid.bandar==$listneg.id)                                      
              										#set($kodbx="$listneg.kod - $listneg.nama")
              									#end
              							#end
              							
                                       	#if($readmode == "disabled")
                                       		#foreach($listamid.listbandar in $listdaerah)
                                				#set($kodbandar="bandarhta")
											#end
                                    
                                     		#if($listamid.bandar!="" && $listamid.bandar!="0" )
                                     			<input name="ntbb2" value="$!kodBandar - $!ketBandar" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     		#else
                                     			<input name="ntbb3" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     		#end                     
                                   		#else                             
                                  			#if($listamid.bandar!="" && $listamid.bandar!="0" )
                                     			<input name="ntbb2" value="$!kodBandar - $!ketBandar" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     		#else
                                     			<input name="ntbb3" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                     		#end 
										#end
 </label></td>
                  
                                  </tr>
                        
                                  <tr>
                                    <td class="style38" valign="top" >#if($readmode != "disabled")<span class="style41">*</span>#end</td>
                                    <td class="style38"><div align="right" class="style44">
                                        <div align="left">#if($readmode != "disabled") Bahagian Simati #else Bahagian Simati #end</div>
                                      </div></td>
                                    <td>:</td>
                                    <td><input name="txtBahagianSimati1Up" type="text" id="txtBahagianSimati3" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati1Up')" style="text-align:right;text-transform:uppercase;" value="$!baSimati" size="14" maxlength="14" $readmodeR class="$readmode"   onblur="bahagiansamaUp()" />
                                      /
                                      <input name="txtBahagianSimati2Up" type="text" id="txtBahagianSimati4" onkeyup="javascript:validateIC(event,this,this.value,'txtBahagianSimati2Up')" style="text-align:left;text-transform:uppercase;" value="$!bbSimati" size="14" maxlength="14" $readmodeR class="$readmode"  onblur="bahagiansamaUp()" />
                                    </td>
                                  </tr>
                                </table></td>
                              <td valign="top" width="50%"><table width="100%" border="0">
                                  <tr>
                                    <td width="29%" class="style38"><div align="left">Kategori Tanah</div></td>
                                    <td width="1%">:</td>
                                    <td width="70%"> #if($listamid.kategori == "2")
                                      
                                      #set($meterhektar = "Hektar")
                                      #elseif($listamid.kategori == "1" || $listamid.kategori == "3" || $listamid.kategori == "4" || $listamid.kategori == "5" || $listamid.kategori == "6")
                                      #set($meterhektar = "Meter Persegi")
                                      #else
                                      #set($meterhektar = "")
                                      
                                      #end
                       
                                      #foreach($listkate in $listkategori)
                                      
                                      #if($listamid.kategori==$listkate.id)
                                      
                                      #set($listkategoriK=$listkate.kod)
                                      #set($listkategoriN=$listkate.keterangan)
                               
                                      #end 
                                      #end
                                  
                                      #if($readmode == "disabled")
                                      
                                      
                                      #if($listamid.kategori!="" && $listamid.kategori!="0" )
                                      <input name="ktt" value="$!kodKategori - $!keteranganKa" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="ktt" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      #else
                                      #if($listamid.kategori!="")
                                      <input name="ktt" value="$!kodKategori - $!keteranganKa" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="ktt" value="" size="34" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end        #end </td>
                                  </tr>
                                  <tr>
                                    <td class="style38"><div align="left">Luas Asal</div></td>
                                    <td>:</td>
                                    <td>#foreach($listluashta in $listluas)
                                      
                                      #if($listamid.jenisluas==$listluashta.id)
                                      
                                      #set($listluasK=$listluashta.kod)
                                      #set($listluasN=$listluashta.nama)
                                      #end 
                                      #end
                                      
                                      #if($readmode == "disabled")
                                      <input name="txtLuasAsalHtaamUpd" type="text" id="txtLuasAsalHtaamUpd" value="$!luas" size="15" maxlength="15" $readmodeR class="$readmode" onblur="getConversionU()" />
                                      #if($listamid.jenisluas!="" && $listamid.jenisluas!="0")
                                      
                                      #else
                                      
                                      #end 
                                      
                                      #else
                                      #if($listamid.jenisluas!="" && $listamid.jenisluas!="0")
                                      <input name="txtLuasAsalHtaamUpd" type="text" id="txtLuasAsalHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamUpd')" value="$!luas" size="15" maxlength="15" readonly  class = "disabled"  />
                                      #else
                                      <input name="txtLuasAsalHtaamUpd" id="txtLuasAsalHtaamUpd" type="text" onkeyup="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaamUpd')" value="$!luas" size="15" maxlength="15"  readonly  class = "disabled"  />
                                      #end             #end </td>
                                  </tr>
                                  <tr>
                                    <td class="style38"><div align="left">Jenis Luas </div></td>
                                    <td>:</td>
                                    <td>#foreach($listluashta in $listluas)
                                      
                                      #if($listamid.jenisluas==$listluashta.id)
                                      
                                      #set($listluasK=$listluashta.kod)
                                      #set($listluasN=$listluashta.nama)
                                      #end 
                                      #end
                                      
                                      #if($readmode == "disabled")
                                      
                                      
                                      #if($listamid.jenisluas!="" && $listamid.jenisluas!="0")
                                      <input name="txtLuas" type="text" id="txtLuas" value="$!kodLuas - $!ketLuas" size="25"  $readmodeR class="$readmode"  />
                                      #else
                                      <input name="txtLuas" type="text" id="txtLuas" value="" size="25"  $readmodeR class="$readmode"  />
                                      #end 
                                      
                                      #else
                                      #if($listamid.jenisluas!="")
                                      <input name="txtLuas" type="text" id="txtLuas" value="$!kodLuas - $!ketLuas" size="25"  $readmodeR class="$readmode"  />
                                      #else
                                      <input name="txtLuas" type="text" id="txtLuas" value="" size="25"  $readmodeR class="$readmode"  />
                                      #end             #end </td>
                                  </tr>
                                  <tr id="tr_luasharta" style="display:none;">
                                    <td class="style38">&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td><span id="luas1" style="display:none;">
                                      <input name="txtLuasAsalHtaam1" type="text" id="txtLuasAsalHtaam1" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam1')"   />
                                      </span> <span id="luas2" style="display:none;">
                                      <input name="txtLuasAsalHtaam2" type="text" id="txtLuasAsalHtaam2" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam2')"   />
                                      </span> <span id="luas3" style="display:none;">
                                      <input name="txtLuasAsalHtaam3" type="text" id="txtLuasAsalHtaam3" value="$luasasal" size="12" maxlength="15" onKeyUp="javascript:validateIC(event,this,this.value,'txtLuasAsalHtaam3')"  />
                                      </span> </td>
                                  </tr>
                                  <tr id="tr_luasharta_b" style="display:none;">
                                    <td class="style38">&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td><label>
                                      <input type="button" name="button2" id="button2" value="Convert" onclick="ConvertLuasHarta('socJenisLuasHtaamUpd','txtLuasAsalHtaamUpd','txtLuasHMpHtaamUpd','meterhektar','txtLuasAsalHtaam1','txtLuasAsalHtaam2','txtLuasAsalHtaam3','socKategoriTanahHtaamUp')" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38"><div align="left">Luas (Hektar/MP) </div></td>
                                    <td>:</td>
                                    <td><label>
                                      <input name="txtLuasHMpHtaamUpd" type="text" class="$readmode" id="txtLuasHMpHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtLuasHMpHtaamUpd')" value="$!luasHMP" size="15" maxlength="15" $readmodeR />
                                      <input name="meterhektar" type="text" id="meterhektar" value="$!ketLuas" size="15" readonly class="disabled" />
                                      </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38"><div align="left">Nilai Tarikh Mohon (RM)</div></td>
                                    <td>:</td>
                                    <td><label> #if($readmode == "disabled")
                                      #if($listamid.nilai_Hta_memohon!="")
                                      <input name="txtNilaiTarikhMohonHt" type="text" value="$Util.formatDecimal($listamid.nilai_Hta_memohon)" size="15"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHt')" $readmodeR class="$readmode" />
                                      #else
                                      <input name="txtNilaiTarikhMohonHt" type="text" value="" size="15"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHt')" $readmodeR class="$readmode" />
                                      #end
                                      #else
                                      #set($listamidnilai_Hta_memohon=$listamid.nilai_Hta_memohon)
                                      <input name="txtNilaiTarikhMohonHt" type="text" value="$!nilaihtaTM" size="15"  onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMohonHt')" $readmodeR class="$readmode" onblur="validateModal(this,this.value,'$listamidnilai_Hta_memohon')" />
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38"><div align="left">Nilai Tarikh Mati (RM)</div></td>
                                    <td>:</td>
                                    <td><label> #if($readmode == "disabled")
                                      #if($listamid.nilai_Hta_mati!="")
                                      <input name="txtNilaiTarikhMatiHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamUpd')" type="text" id="txtNilaiTarikhMatiHtaam2" value="$Util.formatDecimal($listamid.nilai_Hta_mati)" size="15" $readmodeR class="$readmode" />
                                      #else
                                      <input name="txtNilaiTarikhMatiHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamUpd')" type="text" id="txtNilaiTarikhMatiHtaam2" value="" size="15" $readmodeR class="$readmode" />
                                      #end
                                      
                                      #else
                                      #set($listamidnilai_Hta_mati=$listamid.nilai_Hta_mati)
                                      <input name="txtNilaiTarikhMatiHtaamUpd" onkeyup="javascript:validateIC(event,this,this.value,'txtNilaiTarikhMatiHtaamUpd')" type="text" id="txtNilaiTarikhMatiHtaam2" value="$!nilaihtaTMA" size="15" $readmodeR class="$readmode" onblur="validateModal(this,this.value,'$listamidnilai_Hta_mati');" />
                                      #end </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38"><div align="left"><span class="style41">*</span>&nbsp;Status Pemilikan</div></td>
                                    <td>:</td>
                                    <td>
                                      #set($text_daftar = "")
                                      #if($!status_pemilikan == '1')
                                      #set($text_daftar = "PM - PEMILIK")
                                      #elseif($!status_pemilikan == '2') 
                                      #set($text_daftar = "JA - PENJAGA AMANAH")
                                      #elseif($!status_pemilikan == '3') 
                                      #set($text_daftar = "KL - K.A (L.A)")
                                      #elseif($!status_pemilikan == '4') 
                                      #set($text_daftar = "PA - PEMEGANG AMANAH")
                                      #elseif($!status_pemilikan == '5') 
                                      #set($text_daftar = "PBD - PIHAK BERKEPENTINGAN BERDAFTAR")
                                      #elseif($!status_pemilikan == '6') 
                                      #set($text_daftar = "PG -- PEMEGANG GADAIAN")
                                      #elseif($!status_pemilikan == '7') 
                                      #set($text_daftar = "PJ - PEMEGANG PAJAKAN")
                                      #elseif($!status_pemilikan == '8') 
                                      #set($text_daftar = "PJK - PEMEGANG PAJAKAN KECIL")
                                      #elseif($!status_pemilikan == '9') 
                                      #set($text_daftar = "PJL - PENJUAL")
                                      #elseif($!status_pemilikan == '10') 
                                      #set($text_daftar = "PK - PEMEGANG KUASA (PROBATE)/EXECUTOR")
                                      #elseif($!status_pemilikan == '11') 
                                      #set($text_daftar = "PMB - PEMILIK BANGUNAN") 
                                      #elseif($!status_pemilikan == '12') 
                                      #set($text_daftar = "PMG - PEMEGANG MORTGAGE")
                                      #elseif($!status_pemilikan == '13') 
                                      #set($text_daftar = "PP - PENTADBIR")
                                      #elseif($!status_pemilikan == '14') 
                                      #set($text_daftar = "PY - PENYEWA")
                                      #elseif($!status_pemilikan == '15') 
                                      #set($text_daftar = "RP - REPRESENTATIVE")
                                      #elseif($!status_pemilikan == '16') 
                                      #set($text_daftar = "WAR - WARIS")
                                      #elseif($!status_pemilikan == '17') 
                                      #set($text_daftar = "WKL - WAKIL")
                                      #elseif($!status_pemilikan == '18') 
                                      #set($text_daftar = "WPA - WASI DAN PEMEGANG AMANAH")                                  
                                      #elseif($!status_pemilikan == '19') 
                                      #set($text_daftar = "WPS - WAKIL PENILAI SWASTA")
                                      #elseif($!status_pemilikan == '20') 
                                      #set($text_daftar = "WS - WASI")
                                      #end                                     
                                      
                                      #if($readmode == "disabled")
                                      
                                      #if($listamid.pemilikan!="" && $listamid.pemilikan!="0" )
                                      <input name="jstm" value="$text_daftar" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="jstm" value="" size="15" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      #else
                                    
                                      #if($listamid.pemilikan!="")
                                      <input name="jstm" value="$text_daftar" size="45" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="jstm" value="" size="15" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end                         #end </td>
                                  </tr>
                                  <!--<tr>
                                    <td class="style38"><div align="left">Tanggungan </div></td>
                                    <td>:</td>
                                    <td><input name="txtTanggunganHtaamUp" type="text" id="txtTanggunganHtaam2" value="$!tanggungan" size="15"$readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" /></td>
                                  </tr>-->
                                  <tr>
                                    <td class="style38"><div align="left"><span class="style41">*</span>&nbsp;Jenis Tanah</div></td>
                                    <td>:</td>
                                    
                                    <td><label>#foreach($listtan in $listtanah)
                                      <!-- listtanah = $listtanah listamid=$listamid -->
                                  
                                      #if($listamid.jenistanah==$listtan.id)
                                      
                                      #set($listtanK=$listtan.kod)
                                      #set($listtanN=$listtan.keterangan)
                                    
                                      #end 
                                      #end
                                      
                                      #if($readmode == "disabled")
                                      
                                      #if($listamid.jenistanah!="" && $listamid.jenistanah!="0" )
                                      <input name="jt" value="$!kodTanah - $!ketTanah" size="25" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="jt" value="" size="15" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end
                                      
                                      #else
                                      
                                      #if($listamid.jenistanah!="")
                                      <input name="jt" value="$!kodTanah - $!ketTanah" size="25" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #else
                                      <input name="jt" value="" size="15" style="text-transform:uppercase;" $readmodeR class="$readmode" />
                                      #end            #end </label></td>
                                  </tr>
                                  <tr>
                                    <td class="style38" valign="top"><div align="left">Catatan</div></td>
                                    <td valign="top">:</td>
                                    <td><textarea name="txtCatatanHt" id="txtCatatanHt" readonly="" class="disabled" value="$!catatan" cols="31" rows="5"></textarea>
                                    </td>
                                  </tr>
                                  <tr id="tr_flag_daftar"  style="display:none">
                                    <td  valign="top">Urusan Kemasukkan Maklumat Harta </td>
                                    <td valign="top">:</td>
                                    <td valign="top"> #set($FLAG_DAFTAR = $listamid.FLAG_DAFTAR)
                                      
                                      #if($readmode != "enabled" )
                                      
                                      #set($text_daftar = "")
                                      #if($!flag_daftar == '1')
                                      #set($text_daftar = "PENDAFTARAN")
                                      #elseif($!flag_daftar == '2') 
                                      #set($text_daftar = "PERBICARAAN")                                         
                                      #end
                                      <input name="FLAG_DAFTAR_TEXT" type="text" id="FLAG_DAFTAR_TEXT" style="text-transform:uppercase;"  value="$text_daftar" size="18" maxlength="40" $readmodeR class="$readmode" />
                                      <input type="hidden" name="FLAG_DAFTAR" id="FLAG_DAFTAR" value="$FLAG_DAFTAR"  />
  
                                      #end </td>
                                  </tr>
                                  #if($!skrin_online != "yes")
                                  <script>
                                                    document.getElementById('tr_flag_daftar').style.display = "";
                                                    </script>
                                  #end
                                </table></td>
                            </tr>
                          </table>
                          </fieldset>
                          
                    <input type="button" name="cetak" id="cetak" value="Cetak Maklumat Borang B" align="center" onClick="javascript:cetakPPKBorangB('$idFail','$kodPejabat')" />
					<input type="button" name="tutup" id="tutup" value="Tutup" align="center" onClick="tutupTetingkap()" />
					<input type="button" name="hantar2" id="hantar2" value="Hantar" align="left" onClick="javascript:hantarPermohonan()" disabled />
</body>		
<script>
function tutupTetingkap() {
	window.close();
}

function convertTo(){
	if (document.f1.socJenisLuasHtaam.value=="4" || document.f1.socJenisLuasHtaam.value=="7"){
        var a = document.f1.txtLuasAsalHtaam.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")
        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaam.value=="2"){
        var a = document.f1.txtLuasAsalHtaam.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaam.value=="3"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaam.value=="5"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaam.value=="1"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
  
}

function getConversion(){
if(document.f1.txtLuasAsalHtaam.value != "" )
{
	if (document.f1.socJenisLuasHtaam.value=="4" || document.f1.socJenisLuasHtaam.value=="7"){
        var a = document.f1.txtLuasAsalHtaam.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")
        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaam.value=="2"){
        var a = document.f1.txtLuasAsalHtaam.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaam.value=="3"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaam.value=="5"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaam.value=="1"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 

}

function getConvert(){
if(document.f1.socKategoriTanahHtaam.value == 1 ||  document.f1.socKategoriTanahHtaam.value == 2 || document.f1.socKategoriTanahHtaam.value == 3 || document.f1.socKategoriTanahHtaam.value == 4 || document.f1.socKategoriTanahHtaam.value == 5  || document.f1.socKategoriTanahHtaam.value == 6  )
{
	if (document.f1.socJenisLuasHtaam.value=="4" || document.f1.socJenisLuasHtaam.value=="7"){
        var a = document.f1.txtLuasAsalHtaam.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")
        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaam.value=="2"){
        var a = document.f1.txtLuasAsalHtaam.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaam.value=="3"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaam.value=="5"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaam.value=="1"){
        var a = document.f1.txtLuasAsalHtaam.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaam.value=="2") 
        {       
		document.f1.txtLuasHMpHtaam.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaam.value=="1" || document.f1.socKategoriTanahHtaam.value=="3" || document.f1.socKategoriTanahHtaam.value=="4" || document.f1.socKategoriTanahHtaam.value=="5" || document.f1.socKategoriTanahHtaam.value=="6")        
        {
        document.f1.txtLuasHMpHtaam.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 
 else
 {
  document.f1.socJenisLuasHtaam.value = "";
  document.f1.txtLuasHMpHtaam.value = "";
  document.f1.meterhektar.value= "";
  document.f1.txtLuasAsalHtaam.value = "";
 
 }

}

function convertToU(){
	if (document.f1.socJenisLuasHtaamUpd.value=="4" || document.f1.socJenisLuasHtaamUpd.value=="7"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamUpd.value=="2"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamUpd.value=="3"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamUpd.value=="5"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamUpd.value=="1"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
	
    
}




function getConversionU(){
if(document.f1.txtLuasAsalHtaamUpd.value != "" )
{
	if (document.f1.socJenisLuasHtaamUpd.value=="4" || document.f1.socJenisLuasHtaamUpd.value=="7"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamUpd.value=="2"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamUpd.value=="3"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamUpd.value=="5"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamUpd.value=="1"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 

}



function getConvertU(){
if(document.f1.socKategoriTanahHtaamUp.value == 1 ||  document.f1.socKategoriTanahHtaamUp.value == 2 || document.f1.socKategoriTanahHtaamUp.value == 3 || document.f1.socKategoriTanahHtaamUp.value == 4 || document.f1.socKategoriTanahHtaamUp.value == 5  || document.f1.socKategoriTanahHtaamUp.value == 6  )
{
	if (document.f1.socJenisLuasHtaamUpd.value=="4" || document.f1.socJenisLuasHtaamUpd.value=="7"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
		var num = a * 4046.8252519 ; 
		var num1 = a * 0.4046863;
                
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")
        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        } 
	}
    
    if (document.f1.socJenisLuasHtaamUpd.value=="2"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
       	var num = a * 10000; 
		var num1 = a * 1; 
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
		
	}
    
     if (document.f1.socJenisLuasHtaamUpd.value=="3"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num = a * 1; 
		var num1 = a * 0.0001;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
 if (document.f1.socJenisLuasHtaamUpd.value=="5"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 9.290304; 
		var num = a * 0.09290304;
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }       
	}	
 
    if (document.f1.socJenisLuasHtaamUpd.value=="1"){
        var a = document.f1.txtLuasAsalHtaamUpd.value;
        var num1 = a * 100; 
		var num = a * 1000000;       
               
        if(document.f1.socKategoriTanahHtaamUp.value=="2") 
        {       
		document.f1.txtLuasHMpHtaamUpd.value=num1.toFixed(4);
	    document.f1.meterhektar.value= "Hektar";
        }
        else if(document.f1.socKategoriTanahHtaamUp.value=="1" || document.f1.socKategoriTanahHtaamUp.value=="3" || document.f1.socKategoriTanahHtaamUp.value=="4" || document.f1.socKategoriTanahHtaamUp.value=="5" || document.f1.socKategoriTanahHtaamUp.value=="6")        
        {
        document.f1.txtLuasHMpHtaamUpd.value=num.toFixed(4);
	    document.f1.meterhektar.value= "Meter Persegi";
        }  
             
	}	
 } 
 else
 {
  document.f1.socJenisLuasHtaamUpd.value = "";
  document.f1.txtLuasHMpHtaamUpd.value = "";
  document.f1.meterhektar.value= "";
  document.f1.txtLuasAsalHtaamUpd.value = "";
 
 }

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
</script>		
					