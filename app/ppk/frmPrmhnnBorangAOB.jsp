<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}
body {
	background-color: #FFFFFF;
}
.style36 {font-size: 12}
.style38 {font-size: 10px}
.style40 {color: #FF0000}
.style41 {color: #000000}
.style42 {color: #0000FF}
-->
</style>
<!--<body onLoad="submitForm()">-->
<fieldset>
<legend>Pembahagian Pusaka Kecil</legend>
<fieldset>
<legend>Maklumat untuk Borang A</legend>
<table width="100%" border="0">

<tr>
<td>

<input type="hidden" name="v_tab" id="v_tab" value="" />
<input type="hidden" name="action">
<input type="hidden" name="hitButt">
 <input type="hidden" name="mode">
 <input type="hidden" name="idPermohonan" value="$IdPermohonan">
 <input type="hidden" name="simpanStatus" value="$SimpanStatus">
  <input type="hidden" name="idSimati" value="$IdSimati">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
<input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/> 
 #foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    
<input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
     <input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idtemp" type="hidden"  value="$id"/>
       <input name="idpermohonansimati" type="hidden"  value="$idpermohonansimati"/>
#end
</td>
</tr>
  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')">PERMOHONAN</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HAview('2','0','0','0')" >HARTA ALIH</li>
      #set ($hidden = "")
      #if ($hidden == "0")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="NAview('3','0','0','0')" >NILAIAN HARTA</li>
      #end
      #if($hideTabPengesahan != "hide")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PengesahanView('4','0','0','0')">PENGESAHAN PERMOHONAN</li>
      #end
    </ul>
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
     
        <div id="TabbedPanels2" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')" >SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemohonView('0','1','0','0')">PEMOHON</li>
            
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="WarisView('0','2','0','0')">WARIS</li>
         
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PentingView('0','3','0','0')">ORANG KEPENTINGAN</li>
           
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemiutangView('0','4','0','0')">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PenghutangView('0','5','0','0')">PENGHUTANG</li>
          #set ($hidden = "")
      		#if ($hidden == "0")
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SaksiView('0','4','0','0')">SAKSI</li>
            #end
          </ul>
          <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent">
            <table width="100%" border="0">
      <tr>
      <td>
                   <tr><td width="100%">
                   
#if($onchange=="no")                   
	#set ($nokplama = "")
	#set ($jenisKp = "")
	#set ($nokplain = "")
	#set ($namaob = "")
	#set ($taraf = "")
	#set ($alamat1 = "")
	#set ($alamat2 = "")
	#set ($alamat3 = "")
	#set ($poskod = "")
	#set ($bandar = "")
	#set ($negeri = "")
	#set ($notel = "")
	#set ($nofaks = "")
	#set ($idOb = "") 
#end

	#if ($nk_update_penting=="yes")
		#foreach ($listdata in $listPentingbyIDOB)
		#set ($idOb = $listdata.idOb)
		#set ($nokp1 = $listdata.nokpbaru1)
		#set ($nokp2 = $listdata.nokpbaru2)
		#set ($nokp3 = $listdata.nokpbaru3)
		#set ($nokplama = $listdata.nokplama)
		#set ($jenisKp = $listdata.jeniskp)
		#set ($nokplain = $listdata.nokplain)
		#set ($namaob = $listdata.nama_Ob)
		#set ($taraf = $listdata.taraf)
		#set ($alamat1 = $listdata.alamat1)
		#set ($alamat2 = $listdata.alamat2)
		#set ($alamat3 = $listdata.alamat3)
		#set ($poskod = $listdata.poskod)
		#set ($bandar = $listdata.bandar)
		#set ($negeri = $listdata.idnegeri)
		#end
	#end
	
#if($onchange=="no")  	
#if ($nk_tambah_penting=="yes")
	
	#set ($idOb = "")
	#set ($nokp1 = "")
	#set ($nokp2 = "")
	#set ($nokp3 ="")
	#set ($nokplama = "")
	#set ($jenisKp = "")
	#set ($nokplain = "")
	#set ($namaob = "")
	#set ($taraf ="")
	#set ($alamat1 ="")
	#set ($alamat2 = "")
	#set ($alamat3 = "")
	#set ($poskod = "")
	#set ($bandar = "")
	#set ($negeri ="")
#end	
#end

                            <fieldset><legend>MAKLUMAT ORANG KEPENTINGAN</legend>
                            <table width="100%" border="0">
                              <tr>
                                <td width="80%"><table width="100%">
                                   <input type="hidden" name="txtIdSimatiPenting" value="$id_Simati" > 
                                  
                                   <input type="hidden" name="txtIdOb" value="$idOb" >        
                                  <tr>
                                  <td width="29%" ><div align="right"><span class="style38">MyID Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36">
                                     <input name="txtNoKPBaru1Penting" id="txtNoKPBaru1Penting" style="width: 50px;" type="text" value="$!nokp1" $readmode size="6" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Penting')"/>-<input name="txtNoKPBaru2Penting" id="txtNoKPBaru2Penting" value="$!nokp2" style="width: 20px;" type="text" $readmode size="2" maxlength="2"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')"/>-<input name="txtNoKPBaru3Penting" id="txtNoKPBaru3Penting" value="$!nokp3" style="width: 40px;" type="text" $readmode size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')"/>                                  </td>
                                </tr>
                                <tr>
                                  <td ><div align="right"><span class="style38">MyID Lama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPenting" type="text" id="txtNoKPLamaPenting" value="$!nokplama" size="8" maxlength="8" $readmode style="text-transform:uppercase;"  />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Lain-lain MyID</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
									 <select name="socJenisKPLainPenting" $readmode style="text-transform:uppercase; width: 146px;">
								   #if($jenisKp=="1")
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      <option value="3">Pasport</option>

	                               #elseif($jenisKp=="2")
	                                
                                      <option value="2">Polis</option>
                                      <option value="1">Tentera</option>
                                      <option value="3">Pasport</option>
                                      
	                              
								   #elseif($jenisKp=="3")
	                               
                                      <option value="3">Pasport</option>
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      
	                               #else
	                                 
                                      <option value="">SILA PILIH JENIS KP</option>
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      <option value="3">Pasport</option>
	                               #end
                                    </select>&nbsp;<input name="txtNoKPLainPenting" type="text" id="txtNoKPLainPenting" value="$!nokplain" size="9" maxlength="9" $readmode style="text-transform:uppercase;" /></td>
                                </tr>
                                  <tr>
                                    <td><div align="right"><span class="style40">*</span><span class="style38">&nbsp;Nama Orang Berkepentingan</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting" type="text" id="txtNamaOBPenting" value="$!namaob" size="34" $readmode style="text-transform:uppercase;" />
                                    </label></td>
                                  </tr>
                                  <tr>
                                          <td><div align="right"><span class="style40">*</span><span class="style38">&nbsp;Taraf Kepentingan</span></div></td>
                                         
                                          <td><div align="right"><span class="style38">:</span></div></td>
                                  #foreach($listtar in $listtaraf)
                                 #if($taraf==$listtar.id_Tarafkptg)  
	                              #set($tarafkepentinganP=$listtar.kod)
	                              #set($tarafkepentinganketeranganP=$listtar.keterangan)
                                 #end    
	                               #end
	                                 <td>
	                              #if($taraf=="" || $taraf=="0")
                                         <select name="socTarafKepentinganPenting" style="width: 225px;" $readmode>
                                   <option value="0">SILA PILIH TARAF KEPENTINGAN</option>
                                      #foreach($listtar in $listtaraf)
                                            #if($listtar.id_Tarafkptg!=1 && $listtar.id_Tarafkptg!=2 && $listtar.id_Tarafkptg!=14)
                                           <option value="$listtar.id_Tarafkptg">$!listtar.keterangan</option>
                                           #end
                                      #end
                                      </select> 
                                  #else
                                     <select name="socTarafKepentinganPenting" style="width: 225px;"  $readmode>
                                           <option value="$taraf">$!tarafkepentinganketeranganP</option>
                                          <option value="0">SILA PILIH TARAF KEPENTINGAN</option>
                                          #foreach($listtar in $listtaraf)
                                              #if($listtar.id_Tarafkptg!=$taraf)
                                                  #if($listtar.id_Tarafkptg!=1 && $listtar.id_Tarafkptg!=2 && $listtar.id_Tarafkptg!=14)
                                                   <option value="$listtar.id_Tarafkptg">$!listtar.keterangan</option>
                                                   
                                                   #end
                                               
                                              #end    
                                           #end
                                        </select>
                                 #end                                      </td>                                
                                     </tr>
                                  <tr>
                                  <td class="style38" width="29%"><div align="right" class="style38">Alamat</div></td>
                                  <td width="1%"><div align="right" class="style38">:</div></td>
                                  <td width="70%"><label>
 									
                                    <input name="txtAlamatTerakhir1Penting" type="text" id="txtAlamatTerakhir1Penting" value="$!alamat1" size="34"  $readmode style="text-transform:uppercase;" />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2Penting" type="text" id="txtAlamatTerakhir2Penting" value="$!alamat2" size="34" $readmode style="text-transform:uppercase;" />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><input name="txtAlamatTerakhir3Penting" type="text" id="txtAlamatTerakhir3Penting" value="$!alamat3" size="34" $readmode style="text-transform:uppercase;" /></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38">Poskod</div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtPoskodPenting" type="text" id="txtPoskodPenting" value="$!poskod" style="text-transform:uppercase;" size="5" maxlength="5" $readmode onKeyUp="javascript:validatePoskod(this,this.value);" onBlur="validLength()"/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right" class="style38">Negeri</div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                    <td>$selectNegeriTetap</td>
                                </tr>
                                  <tr>
                                  <td class="style38"><div align="right" class="style38">Bandar</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td>$selectBandarTetap</td>
                                </tr>
                                  <tr>
                                    <td class="style38">&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                  </tr>
                                  <tr>
                                    <td class="style38" colspan="3"><i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>            
                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila pastikan salah satu MyID diisi.</font>&nbsp;
                         </i></td>
                                    
                                  </tr>
                                  
                                </table>
                                </td>
                               <!-- <td width="10%" valign="top"></td>-->
                              </tr>
                            </table>
                            </fieldset>
                                                      </td>
                          </tr>
            		#if($nk_button_penting=="yes") 
                          <tr>
                            <td><table width="100%" border="0" align="center">
                                  <tr>
                                  
                            <td align="center"> 
					#if ($idstatus=="150") 
							#if($buttonpenting=="tambah") 
                              <input type="button" name="tambahpenting" id="tambahpenting2" value="Simpan" onKeyPress="setSelected(0,3,0,0);tambah_simpan_penting()" onClick="setSelected(0,3,0,0);tambah_simpan_penting()"/>								
                              	<input type="submit" name="batalpenting" id="cmdSimpan3" value="Batal" onKeyPress="setSelected(0,3,0,0);cancelwaris()" onClick="setSelected(0,3,0,0);cancelwaris()"/>                                 
                                  #else
          					  		<input type="button" name="tambahpenting" id="tambahpenting3" value="$buttonpenting" onKeyPress="setSelected(0,3,0,0);tambah_penting()" onClick="setSelected(0,3,0,0);tambah_penting()"/>
                                    #if($buttonpenting=="Simpan")
                                      <input type="reset" name="cmdBatal" id="cmdBatal" value="Batal"/>
                                       #end
                                     #if($buttonpenting=="Kemaskini")
                                     <input type="submit" name="cmdHapus" id=""cmdHapus""  value="Hapus" onKeyPress="setSelected(0,3,0,0);hapus_penting()" onClick="setSelected(0,3,0,0);hapus_penting()" />
                                     <input type="submit" name="cmdKembali3" id="cmdKembali3" value="Batal"  onclick="PentingView('0','3','0','0')" />
                                     #end
                                  #end
						#end
                                    </td>
                                  </tr>
                                  
                                </table></td>
                          </tr>
                        #end 
                         <tr>
                            <td>
                            <input type="hidden" name="idOb" value="" />
				#if ($idhub != "31")
				<fieldset><legend>SENARAI ORANG KEPENTINGAN</legend>
                              <table width="100%" >
                                <tr>
                                  <td>
                                  #if($listPenting.size()!=0)
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="15%">NAMA ORANG BERKEPENTINGAN</td>
                                      <td width="10%"><div align="center">NO KP BARU</div></td>
                                      <td width="25%">TARAF KEPENTINGAN</td>
                                    </tr>
                                    #set($peno=0)
                                      #foreach($listpenting in $listPenting)
                                      
                                      #if($listpenting.taraf!=1 && $listpenting.taraf!=2 && $listpenting.taraf!=14)
                                      #set($peno=$peno+1)
          
         							 #if($peno%2!=0)
                                    <tr bgcolor="white">
                                    <td class="row1"><div align="center" class="style41">$!peno</div></td>
                                      <td class="row1" style="text-transform:uppercase;">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb')">
                                       <div class="style42" >$!listpenting.nama_Ob</div>   
                                        <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row1" style="text-transform:uppercase;"><div align="center" >$!listpenting.nokpbaru</div></td>
                                      <td class="row1" style="text-transform:uppercase;"><div>$!listpenting.keterangan</div></td>
                                    </tr>
                                    #else
                                        <tr >
                                  
                                   <td class="row2"><div align="center" class="style41">$!peno</div></td>
                                      <td class="row2" style="text-transform:uppercase;">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb')">
                                       <div class="style42"> $!listpenting.nama_Ob</div>   
                                        <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row2" style="text-transform:uppercase;"><div align="center">$!listpenting.nokpbaru</div></td>
                                      <td class="row2" style="text-transform:uppercase;"><div>$!listpenting.keterangan</div></td>
                                    </tr>
                                    #end
                                    #end
                                    #end
                                  </table>
                                  #else
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="40%"><div align="center">NAMA OB</div></td>
                                      <td width="25%"><div align="center">NO KP BARU</div></td>
                                      <td width="30%"><div align="center">STATUS OB</div></td>
                                    </tr>
                                   </table> 
                                    <table width="100%" bgcolor="#FFFFFF">
                                    <tr>
                                      <td width="100%"><div align="left">Tiada Rekod</div>
                                      </td>
                                      </tr>
                                   </table> 
                                  #end   
                                 </td>
                                </tr>
                              </table>  
                              </fieldset> 
                              
                              #elseif ($idhub == "31") 
                              <fieldset><legend>SENARAI ORANG KEPENTINGAN </legend>
                              <table width="100%" >
                                <tr>
                                  <td>
                                  #if($listPenting.size()!=0)
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="25%">PEMOHON</td>
                                      <td width="10%"><div align="center">NO SYARIKAT</div></td>
                                   
                                    </tr>
                                    #set($peno=0)
                                      #foreach($listpenting in $listPenting)
                                      
                                      #if($listpenting.taraf!=1 && $listpenting.taraf!=2 && $listpenting.taraf!=14)
                                      
                                        #set($peno=$peno+1)
          
         							 #if($peno%2!=0)
                                    <tr bgcolor="white">
                                    <td class="row1"><div align="center" class="style41">$!peno</div></td>
                                      <td class="row1" style="text-transform:uppercase;">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb')">
                                       <div class="style42" >$!listpenting.nama_Ob</div>   
                                        <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row1" style="text-transform:uppercase;"><div align="center" >$!listpenting.nokplama</div></td>
                                      
                                    </tr>
                                    #else
                                        <tr >
                                  
                                   <td class="row2"><div align="center" class="style41">$!peno</div></td>
                                      <td class="row2" style="text-transform:uppercase;">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb')">
                                       <div class="style42"> $!listpenting.nama_Ob</div>   
                                        <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row2" style="text-transform:uppercase;"><div align="center">$!listpenting.nokplama</div></td>
                                      
                                    </tr>
                                    #end
                                    #end
                                    #end
                                  </table>
                                  #else
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="40%"><div align="center">NAMA OB</div></td>
                                      <td width="25%"><div align="center">NO KP BARU</div></td>
                                      <td width="30%"><div align="center">STATUS OB</div></td>
                                    </tr>
                                   </table> 
                                    <table width="100%" bgcolor="#FFFFFF">
                                    <tr>
                                      <td width="100%"><div align="left">Tiada Rekod</div>
                                      </td>
                                      </tr>
                                   </table> 
                                  #end   
                                 </td>
                                </tr>
                              </table>  
                              </fieldset>
							#end
                          </td>
                          </tr>
                    </table>
             </div>
            
           
            </div>
         
          </div>
        </div>
      </div>
  </div>    </td>
  </tr>
</table>
</fieldset>
</fieldset>
<script>
function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();
}
function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Warisview";
	doAjaxCall${formName}("Waris");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemohonview";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}("Htaam");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}
function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="view_nilai_harta";
	doAjaxCall${formName}("nilai_harta");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Penghutangview";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}
function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Pemiutangview";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}
function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.action="";
	document.${formName}.mode.value="Saksiview";
	document.${formName}.hitButt.value="Saksi";	
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Pentingview";
	doAjaxCall${formName}("Penting");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}



function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	doAjaxCall${formName}("kembali_simati");
	document.${formName}.action.value="";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
    document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;	
}
function tambah_penting_baru(){
	document.${formName}.method="post";
    document.${formName}.mode.value="tambah_penting_baru";
	doAjaxCall${formName}("Penting");
	document.${formName}.action.value="";
	document.${formName}.submit();

}
function tambah_penting(){

	if( document.${formName}.tambahpenting.value == "Simpan" ) {

	var negeri_code = document.${formName}.txtNoKPBaru2Penting.value;
	var dob_code = document.${formName}.txtNoKPBaru1Penting.value;
	
		if(dob_code.charAt(0)<3) {
		 var dum = "20";
		}
		else {
		var dum = "19";
		}
		
	 var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);
	 var date_dob = new Date(yr_dob, mon_dob, dt_dob);

		if (document.${formName}.socTarafKepentinganPenting.value != "8" && document.${formName}.socTarafKepentinganPenting.value != "6") {
		
			if (document.${formName}.txtNoKPBaru1Penting.value == "" && document.${formName}.txtNoKPBaru2Penting.value == "" && document.${formName}.txtNoKPBaru3Penting.value == "" && document.${formName}.txtNoKPLamaPenting.value == "" && document.${formName}.txtNoKPLainPenting.value == "") {
				alert("Sila masukkan salah satu No KP");
			}
			else if (document.${formName}.txtNoKPBaru1Penting.value != "" && document.${formName}.txtNoKPBaru1Penting.value.length < 6){
				alert("Sila masukkan No KP Baru");
				txtNoKPBaru1Penting.focus();
			}
			else if (document.${formName}.txtNoKPBaru2Penting.value != "" && document.${formName}.txtNoKPBaru2Penting.value.length < 2){
				alert("Sila masukkan No KP Baru");
				txtNoKPBaru2Penting.focus();
			}
			else if (document.${formName}.txtNoKPBaru3Penting.value != "" && document.${formName}.txtNoKPBaru3Penting.value.length < 4){
				alert("Sila masukkan No KP Baru");
				txtNoKPBaru3Penting.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Penting.value != "" && document.${formName}.txtNoKPBaru2Penting.value == "" && document.${formName}.txtNoKPBaru3Penting.value == ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2Penting.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Penting.value != "" && document.${formName}.txtNoKPBaru2Penting.value != "" && document.${formName}.txtNoKPBaru3Penting.value == ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru3Penting.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Penting.value != "" && document.${formName}.txtNoKPBaru2Penting.value == "" && document.${formName}.txtNoKPBaru3Penting.value != ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2Penting.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Penting.value == "" && document.${formName}.txtNoKPBaru2Penting.value != "" && document.${formName}.txtNoKPBaru3Penting.value != ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2Penting.focus();
			}
			else if (document.${formName}.txtNoKPBaru1Penting.value != "" && isIc(tt)==false){
	  			txtNoKPBaru1Penting.focus();
			}
			else if (document.${formName}.txtNoKPBaru2Penting.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
				negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" && negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" && negeri_code!="15" && negeri_code!="58" && negeri_code!="16" && negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99")) {
				alert("Sila masukkan kod negeri yang sah");
				txtNoKPBaru2Penting.focus()
			}
			else if (document.${formName}.socJenisKPLainPenting.value != "" && document.${formName}.txtNoKPLainPenting.value == ""){
				alert("Sila masukkan No KP Lain");
				txtNoKPLainPenting.focus();
			}
			else if (document.${formName}.txtNoKPLainPenting.value != "" && document.${formName}.socJenisKPLainPenting.value == ""){
				alert("Sila pilih jenis No KP Lain");
				socJenisKPLainPenting.focus();
			}
			else if (document.${formName}.txtNamaOBPenting.value =="") {
				alert("Sila masukkan Nama Orang Berkepentingan");
				txtNamaOBPenting.focus();
			}
			else if (document.${formName}.socTarafKepentinganPenting.value=="0") {
				alert("Sila pilih Taraf Berkepentingan.");
				socTarafKepentinganPenting.focus();
			} 
			else if (document.${formName}.txtPoskodPenting.value!="" && document.${formName}.txtPoskodPenting.value.length < 5){
				alert("Sila masukkan no poskod dengan lengkap.");
				txtPoskodPenting.focus();
			}
			else{
			    input_box=confirm("Adakah anda pasti?");
				if (input_box == true) {
				document.${formName}.method="post";
				doAjaxCall${formName}("Penting");
				document.${formName}.mode.value="simpan_penting";
				document.${formName}.action.value="";
				document.${formName}.submit();
				}
			}
		}

		else if (document.${formName}.socTarafKepentinganPenting.value == "8" || document.${formName}.socTarafKepentinganPenting.value == "6") {
			if (document.${formName}.txtNamaOBPenting.value == "") {
				alert("Sila masukkan Pemohon");
				txtNamaOBPenting.focus();
			}
			else if (document.${formName}.txtPoskodPenting.value!="" && document.${formName}.txtPoskodPenting.value.length < 5){
				alert("Sila masukkan no poskod dengan lengkap.");
				txtPoskodPenting.focus();
			}
			else{
			    input_box=confirm("Adakah anda pasti?");
				if (input_box == true) {
				document.${formName}.method="post";
				doAjaxCall${formName}("Penting");
				document.${formName}.mode.value="simpan_penting";
				document.${formName}.action.value="";
				document.${formName}.submit();
				}
			}
		}
		
	}


	if( document.${formName}.tambahpenting.value == "Kemaskini" ) {
		document.${formName}.method="post";
		doAjaxCall${formName}("Penting");
		document.${formName}.mode.value="KemaskiniPenting";
		document.${formName}.action.value="";
		document.${formName}.submit();
	}

}
function edit_item(idOb) 
{
	document.${formName}.method="post";
	document.${formName}.txtIdOb.value=idOb;
	doAjaxCall${formName}("Penting");
	document.${formName}.mode.value="GetPenting";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function validLength(){
	if (document.${formName}.txtPoskodPenting.value!="" && document.${formName}.txtPoskodPenting.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodPenting.focus();
	}
}

function hapus_penting(){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.method="post";
	document.${formName}.mode.value="hapus_penting";
	doAjaxCall${formName}("Penting");
	document.${formName}.action.value="";
	document.${formName}.submit();
	}
}

function onChangeBandarTetap(){
	document.${formName}.mode.value="onChangeBandarTetap";
	document.${formName}.tabIdatas.value="0";
    document.${formName}.tabIdtengah.value="5";
    document.${formName}.tabIdbawah.value="0";	
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
	doAjaxCall${formName}("Penting");
}
function submitForm() {    
   // document.val.focus();
	goTo('$val_tab');
	Effect.ScrollTo('$val_tab').focus(); return false;
	
	//doucument.f1.'$val_tab'.focus();
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


function isIc(dtStr){
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
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}
</script>
<script type="text/javascript">

var TabbedPanels1=new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2=new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:3});
var TabbedPanels3=new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4=new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>



<!--</body>-->
