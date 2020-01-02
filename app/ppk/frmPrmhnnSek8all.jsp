<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />

<style type="text/css">
<!--
.style5 {font-size: 14px}
.style6 {font-family: Arial, Helvetica, sans-serif}
.style7 {font-size: 12px}
.style36 {font-size: 12}
.style38 {font-size: 10px}
.style39 {
	color: #FF0000;
	font-weight: bold;
}
.style41 {
	font-size: 10px;
	color: #FF0000;
}
.style43 {color: #FF0000}
.style46 {font-size: 16px; font-weight: bold; }
-->
</style><form  method="post"  name="f1" >

 <table border="2" bgcolor="#FFFFFF" width="100%">
 <tr>
 <td>
 
  <table width="100%" border="0">
    <tr>
      <td width="100%"><table width="100%" border="0">
          <tr>
            <td width="700">
            <table width="708" border="0">
	#set ($namaUnit = "")
             #foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($namaUnit = $list.unitnamapejabat)
                <tr>
                  <td width="18%"><div align="right">No Fail:</div></td>
                  
                  <td width="32%" class="style7">$list.noFail</td>
                  
                  <td width="18%"><div align="right">Status Fail:</div></td>
                  
                  <td width="32%"><span class="style7">$list.keterangan</span></td>
                  
                  <input type="hidden" name="txtNoFail" value="$list.noFail" readonly="true"/>
                  <input type="hidden" name="txtStatusFail" value="$list.id_fail" readonly="true"/>
                </tr>
                <tr>
                  <td><div align="right">Negeri :</div></td>
                  
               <td width="32%"><span class="style7">$list.namanegeri</span></td>
                 <input type="hidden" name="txtPilih Negeri" value="$list.namanegeri" readonly="true"/>
                  <td><div align="right">Seksyen :</div></td>
                  <td>
                  <span class="style7">$list.seksyen</span>
                  <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/></td>
                </tr>
                <tr>
                  <td ><div align="right">Daerah/Jajahan :</div></td>
                 
               <td width="32%"><span class="style7">$list.namadaerah</span></td>
                 <input type="hidden" name="txtDaerah Jajahan" value="$list.namadaerah" readonly="true"/>
                  <td ><div align="right">Tarikh Mohon :</div></td>
                  <td>
                  <span class="style7">$list.tarikhMohon</span>
                 
                  <input type="hidden" name="txdTarikhMohon" value="$list.tarikhMohon" readonly="true"/>
                  </td>
                
                
                
                </tr>
                <tr>
                  <td ><div align="right">Unit :</div></td>
                  <td>
                  <span class="style7">$namaUnit</span>
                  <input type="hidden" name="txtUnit" value="$namaUnit" readonly="true"/></td>
                  <td><div align="right">Nama Pemohon :</div></td>
                  <td>
                  <span class="style7">$list.namaPemohon.toUpperCase()</span>
                  <input type="hidden" name="txtNamaPemohon" value="$list.namaPemohon" readonly="true"/>
                  </td>
                </tr>
                  <tr>
                  <td >
                  <td>
                  
                  <td><div align="right">Nama Simati :</div></td>
                  <td>
                  <span class="style7">$list.namaSimati.toUpperCase()</span>
                
                  <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/>
                  </td>
                   
                </tr>
             
                #end
            </table></td>
          </tr>
        </table><table width="100%" border="1">
      <tr>
              <td width="100%">
              <div id="TabbedPanels1" class="TabbedPanels">
                <ul class="TabbedPanelsTabGroup">
                 
             
                  <li class="TabbedPanelsTab style5 style6 style7" tabindex=""  onclick="setSelected(0,0,0,0);SimatiView()">PERMOHONAN</li>
                 	<li class="TabbedPanelsTab style5 style6 style7" tabindex="" onclick="setSelected(1,0,0,0);HtaamView()">HARTA TAK ALIH</li>
                    <li class="TabbedPanelsTab style5 style6 style7" tabindex="">HARTA ALIH</li>
                    <li class="TabbedPanelsTab style5 style6 style7" tabindex="">NILAIAN HARTA</li>
                </ul>                
                <div class="TabbedPanels">
                  <div class="TabbedPanelsContent">
                    <div id="TabbedPanels2" class="TabbedPanels">
                      <ul class="TabbedPanelsTabGroup">
                        <li class="TabbedPanelsTab style7" tabindex="0" title="SIMATI" onclick="setSelected(0,0,0,0);SimatiView()">SIMATI</li>
                        <li class="TabbedPanelsTab style5 style7" tabindex="" title="PEMOHON" onclick="setSelected(0,1,0,0);PemohonView()">PEMOHON</li>
                        <li class="TabbedPanelsTab style5 style7" tabindex="" onclick="setSelected(0,2,0,0);WarisView()">WARIS</li>
                        <li class="TabbedPanelsTab style5 style7" tabindex="" onclick="setSelected(0,3,0,0);PentingView()">ORANG KEPENTINGAN</li>
                        <li class="TabbedPanelsTab style5 style7" tabindex="" onclick="setSelected(0,4,0,0);SaksiView()">SAKSI</li>
                        <li class="TabbedPanelsTab style5 style7" tabindex="" onclick="setSelected(0,5,0,0);PemiutangView()">PEMIUTANG</li>
                        <li class="TabbedPanelsTab style5 style7" tabindex="" onclick="setSelected(0,6,0,0);PenghutangView()">PENGHUTANG</li>
                      </ul>
                      <div class="TabbedPanelsTab">
                        <div class="TabbedPanelsContent">
                        <table width="100%" border="0">
                          <tr>
		<td>
			<div align="center"></div></td>
</tr>
</table>
                          #foreach($listmati in $listSimati)
                          <table width="100%" border="0">
                         
                        

                                                  
                      <tr>    
                      <input type="hidden" name="idPermohonan" value="$listmati.idPermohonan">
                      <input type="hidden" name="idPemohon" value="$listmati.idPemohon">
                       <input type="hidden" name="idSimati" value="$listmati.idSimati">
                           
                              <td width="337"><table width="337" border="0">
                                <tr>
                                  <td width="117"><div align="right"><span class="style38">No KP Baru :</span></div></td>
                                  <td width="210" class="style36"><label>
                                    <input name="txtNoKPBaru1Simati" type="text" value="$listmati.noKpBaru1" id="textfield" size="6" maxlength="6"  $readmode"/>
                                    -
                                    <input name="txtNoKPBaru2Simati" type="text" value="$listmati.noKpBaru2" id="textfield2" size="2" maxlength="2"  $readmode/>
                                    -
                                    <input name="txtNoKPBaru3Simati" type="text" value="$listmati.noKpBaru3" id="textfield3" size="4" maxlength="4"  $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td ><div align="right"><span class="style38">No KP Lama :</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaSimati" type="text" value="$listmati.noKpLama" id="textfield4" size="34" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
                                  <td class="style36"> #if($readmode=="disabled")
                                    
                                    #if($listmati.jenisKp=="1")
                                    #set($jkp="Tentera")
                                    
                                    #elseif($listmati.jenisKp=="2")
                                    #set($jkp="Polis")
                                    
                                    #elseif($listmati.jenisKp=="3")
                                    #set($jkp="Pasport")
                                    
                                    #else
                                    #set($jkp="")
                                    #end
                                    
                                    #if($jkp=="")
                                    <input name="textfield4" type="text" id="textfield" value="tidak dinyatakan"  size="13" $readmode />
                                    #else
                                    <input name="textfield4" type="text" id="textfield" value="$jkp"  size="13" $readmode />
                                    #end
                                    <input name="socJenisKPLainSimati" type="hidden" id="textfield" value="$jkp"  size="15" $readmode />
                                    #else
                                    <select name="socJenisKPLainSimati" id="select" class="mediumselect">
                                      
									
								   #if($listmati.jenisKp=="1")
	                                 
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      <option value="3">Pasport</option>
                                      
	                              
	                               #elseif($listmati.jenisKp=="2")
	                                
                                      <option value="2">Polis</option>
                                      <option value="1">Tentera</option>
                                      <option value="3">Pasport</option>
                                      
	                              
								   #elseif($listmati.jenisKp=="3")
	                               
                                      <option value="3">Pasport</option>
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      
	                               #else
	                                 
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      <option value="3">Pasport</option>
                                      
	                               #end
                                    
                                  
                                    </select>
                                    #end
                                    <input name="txtNoKPLainSimati" type="text" id="textfield5"  value="$listmati.noKpLain" size="10" $readmode />
                                    <label></label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38"><span class="style39">*</span>Nama Simati :</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNamaSimati" type="text" id="textfield6" value="$listmati.namaSimati" size="34" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Nama Lain :</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNamaLainSimati" type="text" id="textfield" value="$listmati.namaLain"  size="34" $readmode />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Jantina :</span></div></td>
                                  <td class="style36"><label> #if($readmode=="disabled")
                                    
                                    #if($listmati.jantina=="1")
                                    #set($sex="Lelaki")
                                    
                                    
                                    #elseif($listmati.jantina=="2")
                                    #set($sex="Perempuan")
                                    
                                    #else
                                    #set($sex="")
                                    #end
                                    
                                    #if($sex=="")
                                    <input name="socJantinaSi" type="text" id="textfield" value="tidak dinyatakan"  size="34" $readmode />
                                    #else
                                    <input name="socJantinaSi" type="text" id="textfield" value="$sex"  size="34" $readmode />
                                    #end
                                    <input name="socJantinaSimati" type="hidden" id="textfield" value="$listmati.jantina"  size="30" $readmode />
                                    #else
                                    <select name="socJantinaSimati" id="select2" class="mediumselect">
                                      
                                   #if($listmati.jantina=="1")
	                               
                                      <option value="1">Lelaki</option>
                                      <option value="2">Perempuan</option>
                                      
	                               #elseif($listmati.jantina=="1")
	                               
                                      <option value="2">Perempuan</option>
                                      <option value="1">Lelaki</option>
                                      
	                               #else
	                               
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Lelaki</option>
                                      <option value="2">Perempuan</option>
                                      
	                               #end
                                     
                                    
                                    </select>
                                    #end </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Agama :</span></div></td>
                                  <td class="style36"><label> #if($readmode=="disabled")
                                    
                                    #if($listmati.jenisAgama=="1")
                                    
                                    #set($ag="Islam")
                                    
                                    #elseif($listmati.jenisAgama=="2")
                                    
                                    #set($ag="Bukan Islam")
                                    
                                    #else
                                    
                                    #set($ag="")
                                    
                                    #end
                                    
                                    #if($ag=="")
                                    <input name="socAgamaSi" type="text" id="textfield" value="tidak dinyatakan"  size="34" $readmode />
                                    #else
                                    <input name="socAgamaSi" type="text" id="textfield" value="$ag"  size="34" $readmode />
                                    #end
                                    <input name="socAgamaSimati" type="hidden" id="textfield" value="$listmati.jenisAgama"  size="30" $readmode />
                                    #else
                                    <select name="socAgamaSimati" id="select3" class="mediumselect">
                                      
                                   #if($listmati.jenisAgama=="1")
	                               
	                               
                                      <option value="1">Islam</option>
                                      <option value="2">Bukan Islam</option>
                                      
	                               #elseif($listmati.jenisAgama=="2")
	                               
                                      <option value="2">Bukan Islam</option>
                                      <option value="1">Islam</option>
                                      
	                               #else
	                               
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Islam</option>
                                      <option value="2">Bukan Islam</option>
                                      
	                               #end
                                      
                                    
                                    </select>
                                    #end </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Warganegara :</span></div></td>
                                  <td class="style36"><label> #if($readmode=="disabled")
                                    
                                    #if($listmati.jenisWarga=="1")
                                    
                                    #set($wr="Warganegara")
                                    
                                    #elseif($listmati.jenisWarga=="2")
                                    
                                    #set($wr="Bukan Warganegara")
                                    
                                    #elseif($listmati.jenisWarga=="3")
                                    
                                    #set($wr="Pemastautin Tetap")
                                    
                                    #else
                                    #set($wr="")
                                    
                                    #end
                                    
                                    #if($wr=="")
                                    <input name="socWarganegaraSi" type="text" id="textfield" value="tidak dinyatakan"  size="34" $readmode />
                                    #else
                                    <input name="socWarganegaraSi" type="text" id="textfield" value="$wr"  size="34" $readmode />
                                    #end
                                    <input name="socWarganegaraSimati" type="hidden" id="textfield" value="$wr"  size="30" $readmode />
                                    #else
                                    <select name="socWarganegaraSimati" id="select4" class="mediumselect">
                                      
                                   #if($listmati.jenisWarga=="1")
	                               
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #elseif($listmati.jenisWarga=="2")
	                               
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="1">Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #elseif($listmati.jenisWarga=="3")
	                               
                                      <option value="3">Pemastautin Tetap</option>
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      
                                   #else
                                   
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #end
                                  
                                    
                                    </select>
                                    #end </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Umur :</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtUmurSimati" type="text" id="textfield" value="$listmati.umur"  size="5" $readmode />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Bukti Kematian:</span></div></td>
                                 <td>
                                  #if($readmode=="disabled")
                                
                                    <input name="txtBuktiKematian" type="text" id="textfield" value="$listmati.buktikematian"  size="34" $readmode />
                                    <input name="socBuktiKematianSimati" type="hidden" id="textfield" value="$listmati.idbuktikematian"  size="30" $readmode />
                                
                                  #else
                            
                                  
                                  #if($listmati.idbuktikematian!="")
                                    <select name="socBuktiKematianSimati" class="mediumselect">
                                        <option value="$listmati.idbuktikematian">$listmati.kodbuktikematian - $listmati.buktikematian</option>
                                        
                                  #foreach($listbuk in $listbuktimati)
                                 
                                  #if($listmati.idbuktikematian!=$listbuk.id_Buktimati)
                                    
	                               
                                      <option value="$listbuk.id_Buktimati">$listbuk.kod -  $listbuk.keterangan</option>
                                      
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                    </select>
                                     
                                    #else
                                    <select name="socBuktiKematianSimati" class="mediumselect">
                                      <option value="">< Sila Pilih ></option>
                                    
                                  #foreach($listbuk in $listbuktimati)
                                 
                                 
	                               
                                      <option value="$listbuk.id_Buktimati">$listbuk.keterangan</option>
                                      
                                   
                                    
	                               #end
                                  
                                  
                                  
                                  
                                    </select>                                
                                  #end
                                  
                                  #end
                                    </td>
                                   </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">No Sijil Mati :</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoSijilMatiSimati" type="text" id="txtNoSijilMati" value="$listmati.noSijilMati" size="34" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td valign="top"><div align="right"><span class="style38"><span class="style39">*</span>Tarikh Mati :</span></div></td>
                                  <td> #if($readmode=="disabled")
                                    <input name="txdTarikhMati" type="text" id="textfield" value="$listmati.tarikhMati"  size="15" $readmode />
                                      <input name="txdTarikhMati" type="hidden" id="textfield" value="$listmati.tarikhMatidb"  size="9" $readmode />
                                    #else
                                    <input name="txdTarikhMatiSimati" id="txdTarikhMatiSimati" type="text" value="$listmati.tarikhMati" size="15" $readmode />
                                       <a href="javascript:displayDatePicker('txdTarikhMatiSimati',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                                    #end </td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Waktu Kematian :</span></div></td>
                                  <td class="style36">
                                
                                
                                    <input name="socWaktuKematianSimati" type="text" id="textfield" value="$listmati.masamati"  size="5" maxlength="4" $readmode />
                                  </td>
                                </tr>
                                
                              </table></td>
                              <td width="428" valign="top"><table width="100%">
                                <tr valign="top">
                                  <td width="167" class="style38"><div align="right" class="style38">Tempat Mati :</div></td>
                                  <td width="323"><label>
                                    <input name="txtTempatMatiSimati" type="text" id="txtTempatMati" value="$listmati.tempatMati" "size="34" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38" valign="top"><div align="right" class="style38"><span class="style39">*</span>Sebab Kematian :</div></td>
                                 
                                  <td><textarea name="txtSebabKematianSimati" cols="30" rows="3" id="txtSebabKematian" $readmode>$listmati.sebabMati</textarea></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right" class="style38">Alamat Kematian :</div></td>
                                  <td><label>
 									
                                    <input name="txtAlamatTerakhir1Simati" type="text" id="txtAlamatTerakhir" value="$listmati.alamat1" size="34"  $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2Simati" type="text" id="txtAlamatTerakhir2"  value="$listmati.alamat2" size="34" $readmode />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><input name="txtAlamatTerakhir3Simati" type="text" id="txtAlamatTerakhir3" value="$listmati.alamat3" size="34" $readmode/></td>
                                </tr>

                                <tr>
                                  <td class="style38"><div align="right" class="style38">Poskod :</div></td>
                                  <td><label>
                                    <input name="txtPoskodSimati" type="text" id="txtPoskod " value="$listmati.poskod" size="15" maxlength="5" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                                  <td><label>
                                    <input name="txtBandarSimati" type="text" id="txtBandar " value="$listmati.bandar" size="34" $readmode/>
                                  </label></td>
                                </tr>
                                
                                
                                
                               
                                
                                
                                
                                
                                <tr>
                                  <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                                   <td>
                                    #if($readmode=="disabled")
                                
	                                <input name="socNegeri" type="text" id="textfield7" value="$listmati.kod_Negeri - $listmati.namanegeri"  size="34" $readmode />                           
	                                <input name="socNegeriSimati" type="hidden" id="textfield7" value="$listmati.idnegeri"  size="30" $readmode /> 
                                
	                               #else
	                              
	                               
	                              #if($listmati.idnegeri!="")
                                  <select name="socNegeriSimati" class="mediumselect">
                                   <option value="$listmati.idnegeri">$listmati.kod_Negeri - $listmati.namanegeri</option>
                                  #foreach($listneg in $listnegeri)
                                 
                                  #if($listmati.idnegeri!=$listneg.id_Negeri)
                                    
	                               <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socNegeriSimati" class="mediumselect">
                                   <option value="$listmati.idnegeri">$listmati.kod_Negeri - $listmati.namanegeri</option>
                                  #foreach($listneg in $listnegeri)
                                 
                                
	                               <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                   
                                  #end
                                
                                  #end 
                                   </td>                             
                                  </tr>
                                
                                
                                <tr>
                                  <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
                                  <td><textarea name="txtCatatanSimati" cols="30" rows="3" id="Catatan" $readmode>$listmati.catatan</textarea></td>
                                </tr>
                              </table>                                
                                <label></label>
                              <span class="style36"></span>                            </td>
                            </tr>
                          </table>
                          <table width="200" border="0" align="center">
                            <tr>
                            <td align="center">
                               #if($show_kemaskini_button=="yes")
                            
                                <input type="submit" name="cmdKemaskinisimati" id="cmdKemaskinisimati" value="Kemaskini" onKeyPress="setSelected(0,0,0,0);kemaskini_simati()" onclick="setSelected(0,0,0,0);kemaskini_simati()" />
                              
 								#end
								 #if($show_simpan_button=="yes")
                             
                                <input type="button" name="cmdSimpansimati" id="cmdSimpansimati" value="Simpan"  onKeyPress="setSelected(0,0,0,0);SimpanSimati()" onclick="setSelected(0,0,0,0);SimpanSimati()" />
                             
								#end
                              
                              
                             
                               #if($show_kemaskini_button!="yes")
                             
                                <input type="submit" name="cmdBatal" id="cmdBatal" value="Batal" onKeyPress="setSelected(0,0,0,0);BatalSimati()" onclick="setSelected(0,0,0,0);BatalSimati()" />
                             
                              #end
                             
                                <input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" onKeyPress="kembali_simati()" onclick="kembali_simati()"/>
                            </td>
                            </tr>
                          
                          </table>
                          #end
                        </div>
                        <div class="TabbedPanelsContent">
                          <div id="TabbedPanels3" class="TabbedPanels">
                            <ul class="TabbedPanelsTabGroup">
                              <li class="TabbedPanelsTab style7" tabindex="1" onclick="setSelected(0,1,0,0);PemohonView()">PEMOHON</li>
                              <li class="TabbedPanelsTab style7" tabindex="" onclick="setSelected(0,1,1,0);PeguamView()">PEGUAM</li>
                            </ul>
                            <div class="TabbedPanelsContentGroup">
                              <div class="TabbedPanelsContent">
                                <table width="750" border="0" align="center">
                                  #foreach($listpemohon in $listPemohon)
                                  <input type="hidden" name="idPemohon" value="$listpemohon.idPemohon">
                                  <tr>
                                    <td width="354" valign="top"><table width="337" border="0">
                                        <tr>
                                          <td width="123"><div align="right"><span class="style38">No KP Baru :</span></div></td>
                                          <td width="204" class="style36"><label>
                                            <input name="txtnoKpBaru1Pemohon" type="text" id="textfield8" value="$listpemohon.noKpBaru1" size="6" maxlength="6" $readmode />
                                            -
                                            <input name="txtnoKpBaru2Pemohon" type="text" id="textfield9" value="$listpemohon.noKpBaru2" size="2" maxlength="2" $readmode/>
                                            -
                                            <input name="txtnoKpBaru3Pemohon" type="text" id="textfield10" value="$listpemohon.noKpBaru3" size="4" maxlength="4" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
                                          <td class="style36">
                                          
                                          #if($readmode=="disabled")
                                    
                                    #if($listpemohon.jenisKp=="1")
                                    #set($pkp="Tentera")
                                    
                                    #elseif($listpemohon.jenisKp=="2")
                                    #set($pkp="Polis")
                                    
                                    #elseif($listpemohon.jenisKp=="3")
                                    #set($pkp="Pasport")
                                    
                                    #else
                                    #set($pkp="")
                                    #end
                                    
                                    #if($pkp=="")
                                    <input name="textfield4" type="text" id="textfield" value="tidak dinyatakan"  size="10" $readmode />
                                    #else
                                    <input name="textfield4" type="text" id="textfield" value="$pkp"  size="15" $readmode />
                                    #end
                                    <input name="socJenisKPLainPemohon" type="hidden" id="textfield" value="$pkp"  size="15" $readmode />
                                    #else
                                    <select name="socJenisKPLainPemohon" id="select" class="mediumselect">
                                      
									
								   #if($listpemohon.jenisKp=="1")
	                                 
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      <option value="3">Pasport</option>
                                      
	                              
	                               #elseif($listpemohon.jenisKp=="2")
	                                
                                      <option value="2">Polis</option>
                                      <option value="1">Tentera</option>
                                      <option value="3">Pasport</option>
                                      
	                              
								   #elseif($listpemohon.jenisKp=="3")
	                               
                                      <option value="3">Pasport</option>
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      
	                               #else
	                                 
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      <option value="3">Pasport</option>
                                      
	                               #end
                                    
                                  
                                    </select>
                                    #end
                                    <input name="txtNoKPLainPemohon" type="text" id="textfield5"  value="$listpemohon.noKpLain" size="14" $readmode />
                                    <label></label>
                                          
                                          
                                          </td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Nama Pemohon :</span></div></td>
                                          <td class="style36"><label>
                                            <input name="txtNamaPemohonPemohon" type="text" id="textfield13" value="$listpemohon.namaPemohon" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Taraf Kepentingan:</span></div></td>
                                         <td>
                                         #foreach($listtar in $listtaraf)
                                 
                                  #if($listpemohon.idTarafkptg==$listtar.id_Tarafkptg)
                                    
	                              #set($tarafkepentingan=$listtar.kod)
	                              #set($tarafkepentinganketerangan=$listtar.keterangan)
	                              
	                         
                                   
                                  #end    
	                               #end
                                         
                                            #if($readmode=="disabled")
                                
	                                <input name="socTarafKepentingan" type="text" id="textfield7" value="$tarafkepentingan - $tarafkepentinganketerangan"  size="34" $readmode /> 
                                <input name="socTarafKepentinganPemohon" type="hidden" id="textfield7" value="$listpemohon.idTarafkptg"  size="34" $readmode /> 
                                
	                               #else
	                                 
	                               
	                              #if($listmati.idTarafkptg!="")
                                  <select name="socTarafKepentinganPemohon" class="mediumselect">
                                   <option value="$listpemohon.idTarafkptg">$tarafkepentingan - $tarafkepentinganketerangan</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                  #if($listpemohon.idTarafkptg!=$listtar.id_Tarafkptg)
                                    
	                               <option value="$listtar.id_Tarafkptg">$listtar.kod - $listtar.keterangan</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socTarafKepentinganPemohon" class="mediumselect">
                                   <option value="$listpemohon.idTarafkptg">$tarafkepentingan - $tarafkepentinganketerangan</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                
	                               <option value="$listtar.id_Tarafkptg">$listtar.kod - $listtar.keterangan</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                    
                                  #end
                                
                                  #end 
                                  </td>          
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Talian Persaudaraan:</span></div></td>
                                        <td>
                                          #foreach($listsau in $listsaudara)
                                 
                                  #if($listpemohon.idsaudara==$listsau.id_Saudara)
                                    
	                               #set($kodsaudara=$listsau.kod)
	                               #set($kodsaudaraketerangan=$listsau.keterangan)
	                               
	                               
                                   
                                  #end    
	                               #end
                                        
                                        
                                         #if($readmode=="disabled")
                                
	                                <input name="socTalianPersaudaraan" type="text" id="textfield7" value="$kodsaudara - $kodsaudaraketerangan"  size="34" $readmode /> 
                                <input name="socTalianPersaudaraanPemohon" type="hidden" id="textfield7" value="$listpemohon.idsaudara"  size="34" $readmode /> 
                                
	                               #else
	                                 
	                               
	                              #if($listpemohon.idsaudara!="")
                                  <select name="socTalianPersaudaraanPemohon" class="mediumselect">
                                   <option value="$listpemohon.idsaudara">$kodsaudara - $kodsaudaraketerangan</option>
                                  #foreach($listsau in $listsaudara)
                                 
                                  #if($listpemohon.idsaudara!=$listsau.id_Saudara)
                                    
	                               <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socTalianPersaudaraanPemohon" class="mediumselect">
                                   <option value="">< Sila Pilih ></option>
                                  #foreach($listsau in $listsaudara)
                                 
                                
	                               <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                   
                                  #end
                                          #end
                                           </td>
                                        <tr>
                                  <td><div align="right"><span class="style38">Jantina :</span></div></td>
                                  <td class="style36"><label> #if($readmode=="disabled")
                                    
                                    #if($listpemohon.jantina=="1")
                                    #set($sexpemohon="Lelaki")
                                    
                                    
                                    #elseif($listpemohon.jantina=="2")
                                    #set($sexpemohon="Perempuan")
                                    
                                    #else
                                    #set($sexpemohon="")
                                    #end
                                    
                                    #if($sexpemohon=="")
                                    <input name="socJantinaPe" type="text" id="textfield" value="tidak dinyatakan"  size="34" $readmode />
                                    #else
                                    <input name="socJantinaPe" type="text" id="textfield" value="$sexpemohon"  size="34" $readmode />
                                    #end
                                    <input name="socJantinaPemohon" type="hidden" id="textfield" value="$listpemohon.jantina"  size="30" $readmode />
                                    #else
                                    <select name="socJantinaPemohon" id="select2" class="mediumselect">
                                      
                                   #if($listpemohon.jantina=="1")
	                               
                                      <option value="1">Lelaki</option>
                                      <option value="2">Perempuan</option>
                                      
	                               #elseif($listpemohon.jantina=="1")
	                               
                                      <option value="2">Perempuan</option>
                                      <option value="1">Lelaki</option>
                                      
	                               #else
	                               
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Lelaki</option>
                                      <option value="2">Perempuan</option>
                                      
	                               #end
                                     
                                    
                                    </select>
                                    #end </label></td>
                                </tr> 
                                
                                 <tr>
                                  <td><div align="right"><span class="style38">Agama :</span></div></td>
                                  <td class="style36"><label> #if($readmode=="disabled")
                                    
                                    #if($listpemohon.jenisAgama=="1")
                                    
                                    #set($agp="Islam")
                                    
                                    #elseif($listpemohon.jenisAgama=="2")
                                    
                                    #set($agp="Bukan Islam")
                                    
                                    #else
                                    
                                    #set($agp="")
                                    
                                    #end
                                    
                                    #if($agp=="")
                                    <input name="socAgamaPe" type="text" id="textfield" value="tidak dinyatakan"  size="34" $readmode />
                                    #else
                                    <input name="socAgamaPe" type="text" id="textfield" value="$agp"  size="34" $readmode />
                                    #end
                                    <input name="socAgamaPemohon" type="hidden" id="textfield" value="$listpemohon.jenisAgama"  size="30" $readmode />
                                    #else
                                    <select name="socAgamaPemohon" id="select3" class="mediumselect">
                                      
                                   #if($listpemohon.jenisAgama=="1")
	                               
	                               
                                      <option value="1">Islam</option>
                                      <option value="2">Bukan Islam</option>
                                      
	                               #elseif($listpemohon.jenisAgama=="2")
	                               
                                      <option value="2">Bukan Islam</option>
                                      <option value="1">Islam</option>
                                      
	                               #else
	                               
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Islam</option>
                                      <option value="2">Bukan Islam</option>
                                      
	                               #end
                                      
                                    
                                    </select>
                                    #end </label></td>
                                </tr>
                                      
                                         <tr>
                                  <td><div align="right"><span class="style38">Warganegara :</span></div></td>
                                  <td class="style36"><label> #if($readmode=="disabled")
                                    
                                    #if($listpemohon.jenisWarga=="1")
                                    
                                    #set($wrp="Warganegara")
                                    
                                    #elseif($listpemohon.jenisWarga=="2")
                                    
                                    #set($wrp="Bukan Warganegara")
                                    
                                    #elseif($listpemohon.jenisWarga=="3")
                                    
                                    #set($wrp="Pemastautin Tetap")
                                    
                                    #else
                                    #set($wrp="")
                                    
                                    #end
                                    
                                    #if($wrp=="")
                                    <input name="socWarganegaraPe" type="text" id="textfield" value="tidak dinyatakan"  size="34" $readmode />
                                    #else
                                    <input name="socWarganegaraPe" type="text" id="textfield" value="$wrp"  size="34" $readmode />
                                    #end
                                    <input name="socWarganegaraPemohon" type="hidden" id="textfield" value="$listpemohon.jenisWarga"  size="30" $readmode />
                                    #else
                                    <select name="socWarganegaraPemohon" id="select4" class="mediumselect">
                                      
                                   #if($listpemohon.jenisWarga=="1")
	                               
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #elseif($listpemohon.jenisWarga=="2")
	                               
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="1">Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #elseif($listpemohon.jenisWarga=="3")
	                               
                                      <option value="3">Pemastautin Tetap</option>
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      
                                   #else
                                   
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #end
                                  
                                    
                                    </select>
                                    #end </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Umur :</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtUmurPemohon" type="text" id="textfield" value="$listpemohon.umur"  size="5" $readmode />
                                  </label></td>
                                </tr> 
                                        
                                    </table></td>
                                    <td width="436"><table width="430">
                                    
                                    
                                                   <tr>
                                  <td class="style38"><div align="right" class="style38">Alamat Terakhir :</div></td>
                                  <td><label>
 									
                                    <input name="txtAlamatTerakhir1Pemohon" type="text" id="txtAlamatTerakhir1Pemohon" value="$listpemohon.alamat1" size="34"  $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2Pemohon" type="text" id="txtAlamatTerakhir2Pemohon"  value="$listpemohon.alamat2" size="34" $readmode />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><input name="txtAlamatTerakhir3Pemohon" type="text" id="txtAlamatTerakhir3Pemohon" value="$listpemohon.alamat3" size="34" $readmode/></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38">Poskod :</div></td>
                                  <td><label>
                                    <input name="txtPoskodPemohon" type="text" id="txtPoskodPemohon " value="$listpemohon.poskod" size="15" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                                  <td><label>
                                    <input name="txtBandarPemohon" type="text" id="txtBandarPemohon " value="$listpemohon.bandar" size="34" $readmode/>
                                  </label></td>
                                </tr>
                                        <tr>
                                  <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                                   
                                    #foreach($listnegpomo in $listnegeri)
                                 
                                    #if($listpemohon.idnegeri==$listnegpomo.id_Negeri)
                                    
	                                #set($negerikodpemo=$listnegpomo.kod_Negeri)
	                                 #set($negeriketeranganpemo=$listnegpomo.nama_Negeri)
                                   
                                   
                                   
                                    #end 
                                    #end  
                                    #if($readmode=="disabled")
                                
	                                <td><input name="socNegeriPe" type="text" id="textfield7" value="$negerikodpemo - $negeriketeranganpemo"  size="34" $readmode /> </td>
                                <input name="socNegeriPemohon" type="hidden" id="textfield7" value="$listpemohon.idnegeri"  size="30" $readmode /> 
                                
	                               #else
	                               <td>
	                               
	                              #if($listpemohon.idnegeri!="")
                                  <select name="socNegeriPemohon" class="mediumselect">
                                   <option value="$listpemohon.idnegeri">$negerikodpemo - $negeriketeranganpemo</option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listpemohon.idnegeri!=$listnegpomo.id_Negeri)
                                    
	                               <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socNegeriPemohon" class="mediumselect">
                                   <option value=""> < Sila Pilih > </option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end
                                
                                  #end  
                                  
                                  
                                  
                                  
                                                              
                                    </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right">No Telepon:</div></td>
                                          <td><input name="txtNoTelefonPemohon" type="text" id="txtNoTelefonPemohon" value="$listpemohon.noTel" size="34" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right">No Telepon Bimbit :</div></td>
                                          <td><input name="txtNoTelefonBimbitPemohon" type="text" id="txtNoTelefonBimbitPemohon" value="$listpemohon.noHp" size="34" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right">No Faks :</div></td>
                                          <td><input name="txtNoFaksPemohon" type="text" id="txtNoFaksPemohon" value="$listpemohon.noFax" size="34" $readmode /></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right">Email :</div></td>
                                          <td><input name="txtEmelPemohon" type="text" id="txtEmelPemohon" value="$listpemohon.emel" size="34" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
                                          <td><textarea name="txtCatatanPemohon" cols="30" rows="3" id="txtCatatanPemohon" $readmode>$listpemohon.catatan</textarea></td>
                                      </tr>
                                      </table>
                                        <label></label>
                                        <span class="style36"></span></td>
                                  </tr>
                                </table>
                                <table width="200" border="0" align="center">
                                

                                  <tr>
                                  <td>
                                    #if($show_kemaskini_button=="yes")
                                  
                                      <input type="submit" name="cmdKemaskin2" id="cmdKemaskin2" value="Kemaskini" onKeyPress="setSelected(0,1,0,0);kemaskini_pemohon()" onclick="setSelected(0,1,0,0);kemaskini_pemohon()" />
                               
                                   #end
						           #if($show_simpan_button=="yes")
                                 
                                      <input type="submit" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onKeyPress="SimpanPemohon()" onclick="SimpanPemohon()"/>
                                   
                                    #end
                                   
                                     
                                     #if($show_kemaskini_button!="yes")
                                   
                                      <input type="submit" name="cmdBatal2" id="cmdBatal2" value="Batal" onKeyPress="setSelected(0,1,0,0);BatalPemohon()" onclick="setSelected(0,1,0,0);BatalPemohon()"/>
                                  
                                    #end
                                  
                                      <input type="submit" name="cmdKembali2" id="cmdKembali2" value="Kembali" onKeyPress="kembali_simati()" onclick="kembali_simati()" />
                                    </td>
                                  </tr>
                                  
                                </table>
                               #end
                              </div>
                              <div class="TabbedPanelsContent">
                                <table width="700">
                                   #foreach($listpeguam in $listPeguam)
                                   
                                   <input type="hidden" name="idPemohon" value="$listpeguam.idPemohon">
                                   <input type="hidden" name="idPeguam" value="$listpeguam.idPeguam">
                                  <tr>
                                    <td width="333" valign="top"><table width="100%">
                                        <tr>
                                          <td width="117"><div align="right" class="style38">Nama Firma:</div></td>
                                          <td width="159"><label>
                                            <input name="txtNamaFirmaPeguam" type="text" id="txtNamaFirmaPeguam" value="$listpeguam.namaFirma" size="34" $readmode />
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right" class="style38">No Rujukan:</div></td>
                                          <td><label>
                                            <input name="txtNoRujukanFirma" type="text" id="txtNoRujukanFirma" value="$listpeguam.noRujukanFirma" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                  <td class="style38"><div align="right" class="style38">Alamat :</div></td>
                                  <td><label>
 									
                                    <input name="txtAlamat1Peguam" type="text" id="txtAlamat1Peguam" value="$listpeguam.alamat1" size="34"  $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><label>
                                    <input name="txtAlamat2Peguam" type="text" id="txtAlamat2Peguam"  value="$listpeguam.alamat2" size="34" $readmode />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><input name="txtAlamat3Peguam" type="text" id="txtAlamat3Peguam" value="$listpeguam.alamat3" size="34" $readmode/></td>
                                </tr>
                                    </table></td>
                                    <td width="313"><table width="100%" border="0">
                                        <tr>
                                          <td width="130"><div align="right" class="style38">Poskod :</div></td>
                                          <td width="210"><label>
                                            <input name="txtPoskodPeguam" type="text" id="txtPoskodPeguam" value="$listpeguam.poskod" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right" class="style38">Bandar :</div></td>
                                          <td><label>
                                            <input name="txtBandarPeguam" type="text" id="txtBandarPeguam" value="$listpeguam.bandar" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        
                                          <tr>
                                  <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                                   
                                    #foreach($listnegpeg in $listnegeri)
                                 
                                    #if($listpeguam.idnegeri==$listnegpeg.id_Negeri)
                                    
	                                #set($negerikodpeguam=$listnegpeg.kod_Negeri)
	                                 #set($negeriketeranganpeguam=$listnegpeg.nama_Negeri)
                                   
                                   
                                   
                                    #end 
                                    #end  
                                    #if($readmode=="disabled")
                                
	                                <td><input name="socNegeriPeg" type="text" id="textfield7" value="$negerikodpeguam - $negeriketeranganpeguam"  size="34" $readmode /> </td>
                                <input name="socNegeriPeguam" type="hidden" id="textfield7" value="$listpeguam.idnegeri"  size="30" $readmode /> 
                                
	                               #else
	                               <td>
	                               
	                              #if($listpeguam.idnegeri!="")
                                  <select name="socNegeriPeguam" class="mediumselect">
                                   <option value="$listpeguam.idnegeri">$negerikodpeguam - $negeriketeranganpeguam </option>
                                  #foreach($listnegpeg in $listnegeri)
                                 
                                  #if($listpeguam.idnegeri!=$listnegpeg.id_Negeri)
                                    
	                               <option value="$listnegpeg.id_Negeri">$listnegpeg.kod_Negeri - $listnegpeg.nama_Negeri</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socNegeriPeguam" class="mediumselect">
                                   <option value=""> < Sila Pilih > </option>
                                  #foreach($listnegpeg in $listnegeri)
                                 
                               
	                               <option value="$listneg.id_Negeri">$listnegpeg.kod_Negeri - $listnegpeg.nama_Negeri</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end
                                
                                  #end  
                                  
                                  
                                  
                                  
                                                              
                                    </tr>
                                        <tr>
                                          <td><div align="right" class="style38">No Telepon:</div></td>
                                          <td><label>
                                            <input name="txtNomborTelefonPeguam" type="text" id="txtNomborTelefonPeguam" value="$listpeguam.noTel" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right" class="style38">No Faks :</div></td>
                                          <td><label>
                                            <input name="txtNomborFaksPeguam" type="text" id="txtNomborFaksPeguam" value="$listpeguam.noFax" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right" class="style38">Emel :</div></td>
                                          <td><label>
                                            <input name="txtEmelPeguam" type="text" id="txtEmelPeguam" value="$listpeguam.emel" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                    </table></td>
                                  </tr>
                                </table>
                               
                                
                                <table width="200" border="0" align="center">
                                  <tr>
                                  <td>
                                  #if($show_kemaskini_button=="yes")
                                   
                                      <input type="submit" name="cmdKemaskin3" id="cmdKemaskin3" value="Kemaskini" onKeyPress="setSelected(0,1,1,0);kemaskini_peguam()" onclick="setSelected(0,1,1,0);kemaskini_peguam()"/>
                                 
                                    #end
                                      #if($show_simpan_button=="yes")
                                   
                                      <input type="submit" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onKeyPress="setSelected(0,1,1,0);simpan_peguam()" onclick="setSelected(0,1,1,0);simpan_peguam()"/>
                                  
                                    #end
                                      
                                     #if($show_kemaskini_button!="yes")
                                   
                                      <input type="submit" name="cmdBatal2" id="cmdBatal2" value="Batal" onKeyPress="setSelected(0,1,0,0);BatalPemohon()" onclick="setSelected(0,1,0,0);BatalPemohon()"/>
                                  
                                    #end                            
                                   
                                
                                      <input type="submit" name="cmdKembali3" id="cmdKembali3" value="Kembali" onKeyPress="kembali_simati()" onclick="kembali_simati()" />
                                   
                                    </td>
                                  </tr>
                                 
                                 
                                </table>
                               #end
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="TabbedPanelsContent">
                         
                         <!--
                          <table
 
 border="0">
                            <tbody>
                              <tr>
                                <td style="width: 150px;" scope="row"><strong>No KP Baru</strong></td>
                                <td style="width: 330px;"><input name="txtNoFail2" id="txtNoFail" style="width: 80px;" type="text" />
                                  -
                                  <input name="txtNoFail2" id="txtNoFail" style="width: 30px;" type="text" />
                                  -
                                  <input name="txtNoFail2" id="txtNoFail10"  style="width: 60px;" type="text" /></td>
                                <td style="width: 150px;"><strong>Tarikh Mati</strong></td>
                                <td style="width: 338px;"><input name="txtTarikhDaftar" id="txtTarikhDaftar" type="text" />
                                  &nbsp;
                                  <input name="button6" id="button6" value="Kalendar" onclick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" type="submit" /></td>
                              </tr>
                              <tr>
                                <td style="" scope="row"><strong> No. KP Lama</strong></td>
                                <td><input
 name="txtNegeri" id="txtNegeri" type="text" />
                                </td>
                                <td style="width: 150px;"><strong>Waktu Kematian</strong></td>
                                <td style="width: 338px;"><input
 name="txtNamaPemohon3" id="txtNamaPemohon3" type="text" width="60%" /></td>
                              </tr>
                              <tr>
                                <td><strong>Lain- lain KP</strong></td>
                                <td><select name="socKpLain" id="socKpLain">
                                    <option> &lt;--Pilih--&gt;</option>
                                  </select>
                                    <input name="txtNoFail2" id="txtNoFail2" style="width: 120px;" type="text" /></td>
                                <td><strong>Alamat</strong></td>
                                <td><span style="width: 330px;">
                                  <input
 name="txtNoFail9" id="txtNoFail9" type="text" width="300px" />
                                </span></td>
                              </tr>
                              <tr>
                                <td><strong>Nama Waris </strong></td>
                                <td><span style="width: 330px;">
                                  <input
 name="txtNoFail5" id="txtNoFail5" type="text" width="300px" />
                                </span></td>
                                <td>&nbsp;</td>

                                <td><span style="width: 330px;">
                                  <input
 name="txtNoFail8" id="txtNoFail8" type="text" width="300px" />
                                </span></td>
                              </tr>
                              <tr>
                                <td><strong>Jantina</strong></td>
                                <td><select name="socSeksyen3" id="socSeksyen3"
 style="width: 100px;">
                                    <option> &lt;-- Pilih --&gt; </option>
                                </select></td>
                                <td>&nbsp;</td>
                                <td><span style="width: 330px;">
                                  <input
 name="txtNoFail7" id="txtNoFail7" type="text" width="300px" />
                                </span></td>
                              </tr>
                              <tr>
                                <td><strong>Agama </strong></td>
                                <td><select name="socSeksyen" id="socSeksyen"
 style="width: 100px;">
                                    <option> &lt;-- Pilih --&gt; </option>
                                </select></td>
                                <td><strong>Poskod </strong></td>
                                <td><input
 name="txtNamaPemohon2" id="txtNamaPemohon2"
 type="text" /></td>
                              </tr>
                              <tr>
                                <td><strong>Warganegara</strong></td>
                                <td><select name="socSeksyen2" id="socSeksyen2"
 style="width: 100px;">
                                    <option> &lt;-- Pilih --&gt; </option>
                                </select></td>
                                <td><strong>Bandar</strong></td>
                                <td><span style="width: 330px;">
                                  <input
 name="txtNoFail3" id="txtNoFail3" type="text" width="300px" />
                                </span></td>
                              </tr>
                              <tr>
                                <td><strong>Tarikh Lahir</strong></td>
                                <td><input name="txtTarikhDaftar2" id="txtTarikhDaftar2" type="text" />
                                  &nbsp;
                                  <input name="button6" id="button7" value="Kalendar" onclick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" type="submit" /></td>
                                <td><strong>Negeri</strong></td>
                                <td><select name="socSeksyen5" id="socSeksyen5"
 style="width: 100px;">
                                    <option> &lt;-- Pilih --&gt; </option>
                                </select></td>
                              </tr>
                              <tr>
                                <td><strong>No.Surat Beranak</strong></td>
                                <td><input
 name="txtNamaPemohon4" id="txtNamaPemohon4"
 type="text" /></td>
                                <td><strong>No. Telefon</strong></td>
                                <td><input
 name="txtNamaPemohon2" id="txtNamaPemohon"  type="text" /></td>
                              </tr>
                              <tr>
                                <td><strong>Talian Persaudaraan</strong></td>
                                <td><select name="socSeksyen4" id="socSeksyen4"
 style="width: 100px;">
                                    <option> &lt;-- Pilih --&gt; </option>
                                </select></td>
                                <td><strong>No. Telefon Bimbit</strong></td>
                                <td><input
 name="txtNamaSimati2" id="txtNamaSimati" type="text" /></td>
                              </tr>
                              <tr>
                                <td><strong>Sudah Meninggal Dunia</strong></td>
                                <td><label>
                                  <input type="checkbox" name="checkbox" id="checkbox" />
                                </label></td>
                                <td><strong>Catatan</strong></td>
                                <td><label>
                                  <textarea name="textarea" id="textarea" cols="45" rows="5"></textarea>
                                </label></td>
                              </tr>
                            </tbody>
                          </table>
                          
                          -->
                         
                             <table width="100%" border="1">
                              #if($show_lapisan_berikut=="")
                             
                               #if($show_waris_update=="yes")
                               #foreach($lwu in $listWarisUpdate)
  <tr>
    <td width="683"><table width="100%" border="0">
      <tr>
        <td width="60%" valign="top">
        
        
        
        <table width="100%">
          <input type="hidden" name="idwarisup" value="$lwu.idwaris" />
          <input type="hidden" name="txtIdSimatiWaris" value="$lwu.idSimati" />
          <tr>
            <td width="30%"><div align="right"><span class="style38">No KP Baru :</span></div></td>
            <td width="70%" class="style36"><label>
              <input name="txtNoKPBaru1Waris" type="text"  id="textfield26" value="$lwu.nokpbaru1" size="6" maxlength="6"  $readmode"/>
              -
              <input name="txtNoKPBaru2Waris" type="text"  id="textfield27" size="2" value="$lwu.nokpbaru2" maxlength="2"  $readmode/>
              -
              <input name="txtNoKPBaru3Waris" type="text" value="$lwu.nokpbaru3" id="textfield28" size="4" maxlength="4"  $readmode/>
            </label></td>
          </tr>
          <tr>
            <td ><div align="right"><span class="style38">No KP Lama :</span></div></td>
            <td class="style36"><label>
              <input name="txtNoKPLamaWaris" type="text" value="$lwu.nokplama" id="textfield29" size="34" $readmode/>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
            <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode>
              
                                           
                                            
                                      
									
								   #if($lwu.jeniskp=="1")
	                                 
                                      
                                            
                                           
              <option value="1">Tentera</option>
              <option value="2">Polis</option>
              <option value="3">Pasport</option>
              
                                           
                                            
                                      
	                              
	                               #elseif($lwu.jeniskp=="2")
	                                
                                      
                                            
                                           
              <option value="2">Polis</option>
              <option value="1">Tentera</option>
              <option value="3">Pasport</option>
              
                                           
                                            
                                      
	                              
								   #elseif($lwu.jeniskp=="3")
	                               
                                      
                                            
                                           
              <option value="3">Pasport</option>
              <option value="1">Tentera</option>
              <option value="2">Polis</option>
              
                                           
                                            
                                      
	                               #else
	                                 
                                      
                                            
                                           
              <option value="">&lt; Sila Pilih &gt;</option>
              <option value="1">Tentera</option>
              <option value="2">Polis</option>
              <option value="3">Pasport</option>
              
                                           
                                            
                                      
	                               #end
                                    
                                  
                                           
                                  
                                          
                                         
            </select>
                    <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris"  value="$lwu.nokplain" size="15" $readmode />
                    <label></label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style41">*</span><span class="style38">Nama Waris:</span></div></td>
            <td><label>
              <input name="txtNamaOBWaris" type="text" id="txtNamaOBWaris" value="$lwu.nama_Ob" size="34" $readmode/>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Jantina :</span></div></td>
            <td class="style36"><label>
              <select name="socJantinaWaris" id="socJantinaWaris" class="mediumselect" $readmode>
                
                                             
                                              
                                      
                                   #if($lwu.jantina=="1")
	                               
                                      
                                              
                                             
                <option value="1">Lelaki</option>
                <option value="2">Perempuan</option>
                
                                             
                                              
                                      
	                               #elseif($lwu.jantina=="2")
	                               
                                      
                                              
                                             
                <option value="2">Perempuan</option>
                <option value="1">Lelaki</option>
                
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                <option value="">&lt; Sila Pilih &gt;</option>
                <option value="1">Lelaki</option>
                <option value="2">Perempuan</option>
                
                                             
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            
                                           
              </select>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Agama :</span></div></td>
            <td class="style36"><label>
              <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode>
                
                            
                                      
                                   #if($lwu.agama=="1")
	                               
	                               
                                      
                                              
                                             
                <option value="1">Islam</option>
                <option value="2">Bukan Islam</option>
                
                                             
                                              
                                      
	                               #elseif($lwu.agama=="2")
	                               
                                      
                                              
                                             
                <option value="2">Bukan Islam</option>
                <option value="1">Islam</option>
                
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                <option value="">&lt; Sila Pilih &gt;</option>
                <option value="1">Islam</option>
                <option value="2">Bukan Islam</option>
                
                                             
                                              
                                      
	                               #end
                                      
                                    
                                                    
                                           
              </select>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Warganegara :</span></div></td>
            <td class="style36"><label>
              <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="mediumselect" $readmode>
                
                                             
                                              
                                      
                                   #if($lwu.warga=="1")
	                               
                                      
                                              
                                             
                <option value="1">Warganegara</option>
                <option value="2">Bukan Warganegara</option>
                <option value="3">Pemastautin Tetap</option>
                
                                             
                                              
                                      
	                               #elseif($lwu.warga=="2")
	                               
                                      
                                              
                                             
                <option value="2">Bukan Warganegara</option>
                <option value="1">Warganegara</option>
                <option value="3">Pemastautin Tetap</option>
                
                                             
                                              
                                      
	                               #elseif($lwu.warga=="3")
	                               
                                      
                                              
                                             
                <option value="3">Pemastautin Tetap</option>
                <option value="1">Warganegara</option>
                <option value="2">Bukan Warganegara</option>
                
                                             
                                              
                                      
                                   #else
                                   
                                      
                                              
                                             
                <option value="">&lt; Sila Pilih &gt;</option>
                <option value="1">Warganegara</option>
                <option value="2">Bukan Warganegara</option>
                <option value="3">Pemastautin Tetap</option>
                
                                             
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            
                                           
              </select>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Tarikh Lahir :</span></div></td>
            <td class="style36"><input name="txdTarikhLahirWaris" id="txdTarikhLahirWaris" type="text" value="$lwu.dob" size="15" $readmode />
                     <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">No Surat Beranak:</span></div></td>
            <td class="style36"><input type="text" name="txtNoSuratBeranakWaris" id="txtNoSuratBeranakWaris" value="$lwu.noberanak" $readmode/></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Umur :</span></div></td>
            <td class="style36"><input name="txtUmurWaris" type="text" id="txtUmurWaris" value="$lwu.umur" size="15" $readmode/></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Status Waris :</span></div></td>
            <td class="style36"><select name="socStatusOBWaris"  class="mediumselect" $readmode id="socStatusOBWaris">
              
                                         
                                         
                                           
                                           
									
								   #if($lwu.status_Ob=="1")
	                                 
                                      
                                           
                                           
              <option value="1">01 - Dewasa/Waras</option>
              <option value="2">02 - Belum Dewasa</option>
              <option value="3">03 - Hilang</option>
              <option value="4">04 - Tidak Sempurna Akal</option>
              
                                           
                                      
	                              
	                               #elseif($lwu.status_Ob=="2")
	                                
	                                  
                                           
              <option value="2">02 - Belum Dewasa</option>
              <option value="1">01 - Dewasa/Waras</option>
              <option value="3">03 - Hilang</option>
              <option value="4">04 - Tidak Sempurna Akal</option>
              
                                           
                                      
	                              
								   #elseif($lwu.status_Ob=="3")
	                               
	                                 
                                           
              <option value="3">03 - Hilang</option>
              <option value="1">01 - Dewasa/Waras</option>
              <option value="2">02 - Belum Dewasa</option>
              <option value="4">04 - Tidak Sempurna Akal</option>
              
                                           
                                     
                                   #elseif($lwu.status_Ob=="4")
	                                    
                                           
              <option value="4">04 - Tidak Sempurna Akal</option>
              <option value="1">01 - Dewasa/Waras</option>
              <option value="2">02 - Belum Dewasa</option>
              <option value="3">03 - Hilang</option>
              
                                           
                                    
                                        
	                               #else
	                                 
                                      
                                           
              <option value="">&lt; Sila Pilih &gt;</option>
              <option value="1">01 - Dewasa/Waras</option>
              <option value="2">02 - Belum Dewasa</option>
              <option value="3">03 - Hilang</option>
              <option value="4">04 - Tidak Sempurna Akal</option>
              
                                           
                                      
	                               #end
                                   
                                         
            </select>            </td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38"><span class="style43">*</span>Tali Persaudaraan :</span></div></td>
            #foreach($listsau in $listsaudara)
            
            #if($lwu.saudara==$listsau.id_Saudara)
            
            #set($kodsaudara=$listsau.kod)
            #set($kodsaudaraketerangan=$listsau.keterangan)
            
            
            
            #end    
            #end
            <td> #if($lwu.saudara!="")
              <select name="socSaudaraWaris" class="mediumselect" $readmode>
                      <option value="$lwu.saudara">$kodsaudara - $kodsaudaraketerangan</option>
                
                                             
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                  #if($lwu.saudara!=$listsau.id_Saudara)
                                    
	                               
                <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
                
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            
                                           
              </select>
              #else
              <select name="socSaudaraWaris" class="mediumselect" $readmode>
                <option value="">&lt; Sila Pilih &gt;</option>
                
                                  #foreach($listsau in $listsaudara)
                                 
                                
	                               
                <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
                
                                   
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
              </select>            </td>
            #end </tr>
          <tr>
            <td><div align="right"><span class="style38">Sudah Meninggal Dunia:</span></div></td>
            <td class="style36"><label> #if($lwu.statushidup=="1")
              #set($ch="checked")
              #else
              #set($ch="")
              #end
              <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $ch $readmode value="$checkmati" onkeypress="setSelected(0,2,0,0);tarikh_waris_update()" onclick="setSelected(0,2,0,0);tarikh_waris_update()" />
            </label></td>
          </tr>
          #if($lwu.statushidup=="1")
          <tr>
            <td><div align="right"><span class="style38">Tarikh Mati :</span></div></td>
            <td class="style36"><input name="txdTarikhMatiWaris" id="txdTarikhMatiWaris" type="text" value="$lwu.tarikhmati" size="15" $readmode />
                     <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                     </td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Waktu Kematian :</span></div></td>
            <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" value="$lwu.waktumati" size="15" $readmode/></td>
          </tr>
          #end
        </table></td>
        <td width="40%" valign="top"><table width="100%" border="0">
          <tr>
            <td class="style38" width="35%" ><div align="right" class="style38">Alamat  :</div></td>
            <td width="65%"><label>
              <input name="txtAlamatTerakhir1Waris" type="text" id="txtAlamatTerakhir1Waris" value="$lwu.alamat1" size="30"  $readmode/>
            </label></td>
          </tr>
          <tr>
            <td class="style38"><div align="right"><span class="style7"></span></div></td>
            <td><label>
              <input name="txtAlamatTerakhir2Waris" type="text" id="txtAlamatTerakhir2Waris"  value="$lwu.alamat2" size="30" $readmode />
            </label></td>
          </tr>
          <tr>
            <td class="style38"><div align="right"><span class="style7"></span></div></td>
            <td><input name="txtAlamatTerakhir3Waris" type="text" id="txtAlamatTerakhir3Waris" value="$lwu.alamat3" size="30" $readmode/></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style38">Poskod :</div></td>
            <td><label>
              <input name="txtPoskodWaris" type="text" id="txtPoskodWaris" value="$lwu.poskod" size="15" $readmode/>
            </label></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style38">Bandar :</div></td>
            <td><label>
              <input name="txtBandarWaris" type="text" id="txtBandarWaris" value="$lwu.bandar" size="30" $readmode/>
            </label></td>
          </tr>
          <tr>
            <td class="style38"><div align="right" class="style38">Negeri :</div></td>
            #foreach($listnegpomo in $listnegeri)
            
            #if($lwu.idnegeri==$listnegpomo.id_Negeri)
            
            #set($negerikodpemoP=$listnegpomo.kod_Negeri)
            #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
            
            
            
            #end 
            #end
            <td> #if($lwu.idnegeri!="")
              <select name="socNegeriWaris" class="mediumselect" $readmode>
                      <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               

                                              
                                             
                <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                
                                             
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
              </select>
              #else
              <select name="socNegeriWaris" class="mediumselect" $readmode>
                <option value=""> &lt; Sila Pilih &gt; </option>
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                     
                                  
                                            
                                           
              </select>            </td>
            #end </tr>
          <tr>
            <td class="style38" valign="top"><div align="right" class="style38">No Telepon :</div></td>
            <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" value="$lwu.noTel" size="30" $readmode/></td>
          </tr>
          <tr>
            <td class="style38" valign="top"><div align="right">No HP:</div></td>
            <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" value="$lwu.nohp" size="30" $readmode/></td>
          </tr>
          <tr>
            <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
            <td><textarea name="txtCatatanWaris" cols="27"  rows="3"  $readmode>$lwu.catatan</textarea></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="200" border="0" align="center">
      <tr>
        <td><label>
          <input type="button" name="tambahwaris" id="tambahwaris" value="$buttonwaris" onkeypress="setSelected(0,2,0,0);tambah_waris()" onclick="setSelected(0,2,0,0);tambah_waris()"/>
        </label></td>
        #if($show_lapisan_waris=="yes" || $ch=="checked" )
        <td>
        
        
        <input type="button" name="lapisanberikut" id="lapisanberikut" value="Lapisan Berikut" onkeypress="setSelected(0,2,0,0);lapisan_waris($lwu.idwaris)" onclick="setSelected(0,2,0,0);lapisan_waris($lwu.idwaris)"/></td>
        #end
        
        
        #if($show_batal_waris=="yes")
        <td><label>
                                               <input type="button" name="cmdSimpan7" id="cmdSimpan7" value="Batal" onkeypress="warisbatalupdate()" onclick="warisbatalupdate()"/>
          #end </label></td>
        <td><label>
                                               <input type="submit" name="cmdCetak8" id="cmdCetak8" value="Cetak" />
                                             </label></td>
        <td><label>
                                               <input type="submit" name="cmdKembali8" id="cmdKembali8" value="Kembali" onkeypress="setSelected(0,2,0,0);WarisView()" onclick="setSelected(0,2,0,0);WarisView()"/>
                                             </label></td>
      </tr>
    </table></td>
  </tr>
                               #end
                               
                               #end
                               #if($show_table_waris=="yes")
                               <tr>
                              <td width="683"><table width="100%" border="0">
                                  <tr>
                                    <td width="60%" valign="top"><table width="100%">
                                        <input type="hidden" name="txtIdSimatiWaris" value="$id_Simati" />
                                        <tr>
                                          <td width="30%"><div align="right"><span class="style38">No KP Baru :</span></div></td>
                                          <td width="70%" class="style36"><label>
                                            <input name="txtNoKPBaru1Waris" type="text"  id="textfield26" value="$nokpbaru1" size="6" maxlength="6"  $readmode"/>
                                            -
                                            <input name="txtNoKPBaru2Waris" type="text"  id="textfield27" size="2" value="$nokpbaru2" maxlength="2"  $readmode/>
                                            -
                                            <input name="txtNoKPBaru3Waris" type="text" value="$nokpbaru3" id="textfield28" size="4" maxlength="4"  $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td ><div align="right"><span class="style38">No KP Lama :</span></div></td>
                                          <td class="style36"><label>
                                            <input name="txtNoKPLamaWaris" type="text" value="$nokpwaris" id="textfield29" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
                                          <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode>
                                            
                                           
                                            
                                      
									
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
	                                 
                                      
                                            
                                           
                                            <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Tentera</option>
                                              <option value="2">Polis</option>
                                              <option value="3">Pasport</option>
                                            
                                           
                                            
                                      
	                               #end
                                    
                                  
                                    
                                          
                                         
                                          </select>
                                              <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris"  value="$nokplain" size="15" $readmode />
                                              <label></label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style41">*</span><span class="style38">Nama Waris:</span></div></td>
                                          <td><label>
                                            <input name="txtNamaOBWaris" type="text" id="txtNamaOBWaris" value="$namaOB" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Jantina :</span></div></td>
                                          <td class="style36"><label>
                                            <select name="socJantinaWaris" id="socJantinaWaris" class="mediumselect" $readmode>
                                              
                                             
                                              
                                      
                                   #if($jantina=="1")
	                               
                                      
                                              
                                             
                                              <option value="1">Lelaki</option>
                                              <option value="2">Perempuan</option>
                                              
                                             
                                              
                                      
	                               #elseif($jantina=="1")
	                               
                                      
                                              
                                             
                                              <option value="2">Perempuan</option>
                                              <option value="1">Lelaki</option>
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Lelaki</option>
                                              <option value="2">Perempuan</option>
                                              
                                             
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            
                                           
                                            </select>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Agama :</span></div></td>
                                          <td class="style36"><label>
                                            <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode>
                                              
                                             
                                              
                                      
                                   #if($agama=="1")
	                               
	                               
                                      
                                              
                                             
                                              <option value="1">Islam</option>
                                              <option value="2">Bukan Islam</option>
                                              
                                             
                                              
                                      
	                               #elseif($agama=="2")
	                               
                                      
                                              
                                             
                                              <option value="2">Bukan Islam</option>
                                              <option value="1">Islam</option>
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Islam</option>
                                              <option value="2">Bukan Islam</option>
                                              
                                             
                                              
                                      
	                               #end
                                      
                                    
                                    
                                            
                                           
                                            </select>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Warganegara :</span></div></td>
                                          <td class="style36"><label>
                                            <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="mediumselect" $readmode>
                                              
                                             
                                              
                                      
                                   #if($warga=="1")
	                               
                                      
                                              
                                             
                                              <option value="1">Warganegara</option>
                                              <option value="2">Bukan Warganegara</option>
                                              <option value="3">Pemastautin Tetap</option>
                                              
                                             
                                              
                                      
	                               #elseif($warga=="2")
	                               
                                      
                                              
                                             
                                              <option value="2">Bukan Warganegara</option>
                                              <option value="1">Warganegara</option>
                                              <option value="3">Pemastautin Tetap</option>
                                              
                                             
                                              
                                      
	                               #elseif($warga=="3")
	                               
                                      
                                              
                                             
                                              <option value="3">Pemastautin Tetap</option>
                                              <option value="1">Warganegara</option>
                                              <option value="2">Bukan Warganegara</option>
                                              
                                             
                                              
                                      
                                   #else
                                   
                                      
                                              
                                             
                                              <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Warganegara</option>
                                              <option value="2">Bukan Warganegara</option>
                                              <option value="3">Pemastautin Tetap</option>
                                              
                                             
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            
                                           
                                            </select>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Tarikh Lahir :</span></div></td>
                                          <td class="style36"><input name="txdTarikhLahirWaris" id="txdTarikhLahirWaris" type="text" value="$dob" size="15" $readmode />
                                               <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">No Surat Beranak :</span></div></td>
                                          <td class="style36"><input name="txtNoSuratBeranakWaris" type="text" id="txtNoSuratBeranakWaris" value="$noberanak" size="34" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Umur :</span></div></td>
                                          <td class="style36"><input name="txtUmurWaris" type="text" id="txtUmurWaris" value="$umur" size="15" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Status Waris :</span></div></td>
                                          <td class="style36"><select name="socStatusOBWaris"  class="mediumselect" $readmode id="socStatusOBWaris">
                                            
                                           
									
								   #if($statusWaris=="1")
	                                 
                                      
                                           
                                           
                                            <option value="1">01 - Dewasa/Waras</option>
                                              <option value="2">02 - Belum Dewasa</option>
                                              <option value="3">03 - Hilang</option>
                                              <option value="4">04 - Tidak Sempurna Akal</option>
                                            
                                           
                                      
	                              
	                               #elseif($statusWaris=="2")
	                                
	                                  
                                           
                                            <option value="2">02 - Belum Dewasa</option>
                                              <option value="1">01 - Dewasa/Waras</option>
                                              <option value="3">03 - Hilang</option>
                                              <option value="4">04 - Tidak Sempurna Akal</option>
                                            
                                           
                                      
	                              
								   #elseif($statusWaris=="3")
	                               
	                                 
                                           
                                            <option value="3">03 - Hilang</option>
                                              <option value="1">01 - Dewasa/Waras</option>
                                              <option value="2">02 - Belum Dewasa</option>
                                              <option value="4">04 - Tidak Sempurna Akal</option>
                                            
                                           
                                     
                                   #elseif($statusWaris=="4")
	                                    
                                           
                                            <option value="4">04 - Tidak Sempurna Akal</option>
                                              <option value="1">01 - Dewasa/Waras</option>
                                              <option value="2">02 - Belum Dewasa</option>
                                              <option value="3">03 - Hilang</option>
                                            
                                           
                                    
                                        
	                               #else
	                                 
                                      
                                           
                                            <option value=""> &lt; Sila Pilih &gt; </option>
                                              <option value="1">01 - Dewasa/Waras</option>
                                              <option value="2">02 - Belum Dewasa</option>
                                              <option value="3">03 - Hilang</option>
                                              <option value="4">04 - Tidak Sempurna Akal</option>
                                            
                                           
                                      
	                               #end
                                   
                                         
                                          </select>                                          </td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38"><span class="style43">*</span>Tali Persaudaraan :</span></div></td>
                                          #foreach($listsau in $listsaudara)
                                          
                                          #if($saudara==$listsau.id_Saudara)
                                          
                                          #set($kodsaudara=$listsau.kod)
                                          #set($kodsaudaraketerangan=$listsau.keterangan)
                                          
                                          
                                          
                                          #end    
                                          #end
                                          <td> #if($saudara!="")
                                            <select name="socSaudaraWaris" class="mediumselect" $readmode>
                                                <option value="$saudara">$kodsaudara - $kodsaudaraketerangan</option>
                                              
                                             
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                  #if($saudara!=$listsau.id_Saudara)
                                    
	                               
                                              <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            
                                           
                                            </select>
                                            #else
                                            <select name="socSaudaraWaris" class="mediumselect" $readmode>
                                              <option value=""> &lt; Sila Pilih &gt; </option>
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                
	                               
                                              <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
                                              
                                   
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
                                            </select>                                          </td>
                                          #end </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Sudah Meninggal Dunia:</span></div></td>
                                          <td class="style36"><label> #if($checkmati=="1")
                                            #set($chq="checked")
                                            #else
                                            #set($chq="")
                                            #end
                                            <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $chq value="$checkmati" onkeypress="setSelected(0,2,0,0);tarikh_waris()" onclick="setSelected(0,2,0,0);tarikh_waris()" />
                                          </label></td>
                                        </tr>
                                      #if($show_tarikh=="yes")
                                      <tr>
                                        <td><div align="right"><span class="style38">Tarikh Mati :</span></div></td>
                                        <td class="style36"><input name="txdTarikhMatiWaris" id="txdTarikhMatiWaris" type="text" value="$tarikhmati" size="15" $readmode />
                     <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Waktu Kematian :</span></div></td>
                                        <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" value="$waktumatiwaris" size="15" $readmode/></td>
                                      </tr>
                                      #end
                                    </table></td>
                                    <td width="40%" valign="top"><table width="100%" border="0">
                                        <tr>
                                          <td class="style38" width="35%" ><div align="right" class="style38">Alamat  :</div></td>
                                          <td width="65%"><label>
                                            <input name="txtAlamatTerakhir1Waris" type="text" id="txtAlamatTerakhir1Waris" value="$alamat1" size="30"  $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><label>
                                            <input name="txtAlamatTerakhir2Waris" type="text" id="txtAlamatTerakhir2Waris"  value="$alamat2" size="30" $readmode />
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><input name="txtAlamatTerakhir3Waris" type="text" id="txtAlamatTerakhir3Waris" value="$alamat3" size="30" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Poskod :</div></td>
                                          <td><label>
                                            <input name="txtPoskodWaris" type="text" id="txtPoskodWaris" value="$poskod" size="15" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                                          <td><label>
                                            <input name="txtBandarWaris" type="text" id="txtBandarWaris" value="$bandar" size="30" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                                          #foreach($listnegpomo in $listnegeri)
                                          
                                          #if($negeri==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          <td> #if($negeri!="")
                                            <select name="socNegeriWaris" class="mediumselect" $readmode>
                                                <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                             
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            </select>
                                            #else
                                            <select name="socNegeriWaris" class="mediumselect" $readmode>
                                              <option value=""> &lt; Sila Pilih &gt; </option>
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            </select>                                          </td>
                                          #end </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">No Telepon :</div></td>
                                          <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" value="$notel" size="30" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right">No HP:</div></td>
                                          <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" value="$hp" size="30" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
                                          <td><textarea name="txtCatatanWaris" cols="27"  rows="3"  $readmode>$catatan</textarea></td>
                                        </tr>
                                    </table></td>
                                  </tr>
                              </table></td>
                               </tr>
                            <tr>
                              <td><table width="200" border="0" align="center">
                                  <tr>
                                  
                                  #if($buttonwarisSimpan!="")
                                    <td><input type="button" name="tambahwarisSimpan" id="tambahwarisSimpan" value="$buttonwarisSimpan" onkeypress="setSelected(0,2,0,0);tambah_waris()" onclick="setSelected(0,2,0,0);tambah_waris_Simpan()"/></td>
                                   #end 
                                     #if($buttonwarisSimpan=="")
                                    <td><label>
                                      <input type="button" name="tambahwaris" id="tambahwaris" value="$buttonwaris" onkeypress="setSelected(0,2,0,0);tambah_waris()" onclick="setSelected(0,2,0,0);tambah_waris()"/>
                                    </label></td>
                                    #end
                                    
                                    
                                    
                                    #if($show_batal_waris=="yes")
                                    <td><label>
                                      <input type="button" name="cmdSimpan7" id="cmdSimpan7" value="Batal" onkeypress="saksitambahbatal()" onclick="pentingtambahbatal()"/>
                                    </label></td>
                                    #end
                                    <td>
                                      <input type="submit" name="cmdKembali8" id="cmdKembali8" value="Kembali" onkeypress="setSelected(0,2,0,0);WarisView()" onclick="setSelected(0,2,0,0);WarisView()"/>
                                    </td>
                                  </tr>
                              </table></td>
                            </tr>
                               #end
  <tr>
    <td><table width="281">
      #if($show_tambah_waris=="yes")
      <tr>
        <td><input type="button" name="naktambah" id="naktambah" value="Tambah" onkeypress="setSelected(0,2,0,0);new_waris()" onclick="setSelected(0,2,0,0);new_waris()"/></td>
      </tr>
      #end
      <tr>
        <td>SENARAI WARIS LAPISAN PERTAMA</td>
      </tr>
    </table>
        <table width="100%" >
          <tr >
            <td width="700"><div align="center">
                <table width="100%">
                  <tr class="table_header">
                  <td width="5%"><div align="center" >NO</div></td>
                    <td width="20%"><div align="center" >NAMA WARIS</div></td>
                    <td width="15%"><div align="center">NO KP BARU</div></td>
                    <td width="5%"><div align="center">UMUR</div></td>
                    <td width="20%"><div align="center">TALI PERSAUDARAAN</div></td>
                    <td width="20%"><div align="center">STATUS</div></td>
                     <td width="15%"><div align="center">LAPISAN</div></td>
                  </tr>
            </table>      
                  <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" />
                  
                  #if($listWaris.size()==0)
                  <table width="100%">
                   <tr bgcolor="white">
                   <td align="left">
                   Tiada Rekod                   </td>
                  </tr>
                  </table>
                  #else
                   <table width="100%">
                   
                   #set($nowa=0)
                  #foreach($listwaris in $listWaris)
                 
                 #set($nowa=$nowa+1)
                 #if($nowa%2!=0)
                  <tr bgcolor="white">
                    <!-- 
                                             <td><div align="center"><a href="javascript:edit_item_waris('$listwaris.idwaris', '$listwaris.nama_Ob', '$listwaris.nokpbaru1','$listwaris.nokpbaru2','$listwaris.nokpbaru3','$listwaris.idSimati','$listwaris.nokplama','$listwaris.jeniskp','$listwaris.nokplain','$listwaris.idnegeri','$listwaris.noTel','$listwaris.jantina','$listwaris.alamat1','$listwaris.alamat2','$listwaris.alamat3','$listwaris.bandar','$listwaris.agama','$listwaris.catatan','$listwaris.warga','$listwaris.poskod','$listwaris.statushidup','$listwaris.tarikhmati','$listwaris.waktumati','$listwaris.nohp','$listwaris.status_Ob','$listwaris.dob','$listwaris.saudara','$listwaris.umur','$show_table_waris')"> $listwaris.nama_Ob</a></div>
                                             -->
                    <td width="5%%"><div align="center">$nowa</div></td>
                    <td width="20%"><div align="center"><a href="javascript:get_waris('$listwaris.idwaris')"> $listwaris.nama_Ob</a></div></td>
                    <td width="15%"><div align="center">$listwaris.nokpbaru</div></td>
                    <td width="5%"><div align="center">$listwaris.umur</div></td>
                    #foreach($listsaudaralist in $listsaudara)
                    
                    #if($listwaris.saudara==$listsaudaralist.id_Saudara)
                    
                    #set($wariskodsaudara=$listsaudaralist.kod)
                    #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                    
                    
                    #end    
                    #end
                    <td width="20%"><div align="center">$warissaudaraketerangan</div></td>
                    #if($listwaris.statushidup=="0")
                    #set($hidup="Masih Hidup")
                    #end
                    #if($listwaris.statushidup=="1")
                    #set($hidup="Sudah Meningal")
                    #end
                    #if($listwaris.statushidup=="")
                    #set($hidup="")
                    #end
                    <td width="20%"><div align="center">$hidup</div></td>
                     <td width="15%"><div align="center">$listwaris.lapis</div></td>
                  </tr>
                  #else
                  
                   <tr class="table_header">
                    <!-- 
                                             <td><div align="center"><a href="javascript:edit_item_waris('$listwaris.idwaris', '$listwaris.nama_Ob', '$listwaris.nokpbaru1','$listwaris.nokpbaru2','$listwaris.nokpbaru3','$listwaris.idSimati','$listwaris.nokplama','$listwaris.jeniskp','$listwaris.nokplain','$listwaris.idnegeri','$listwaris.noTel','$listwaris.jantina','$listwaris.alamat1','$listwaris.alamat2','$listwaris.alamat3','$listwaris.bandar','$listwaris.agama','$listwaris.catatan','$listwaris.warga','$listwaris.poskod','$listwaris.statushidup','$listwaris.tarikhmati','$listwaris.waktumati','$listwaris.nohp','$listwaris.status_Ob','$listwaris.dob','$listwaris.saudara','$listwaris.umur','$show_table_waris')"> $listwaris.nama_Ob</a></div>
                                             -->
                    <td width="5%"><div align="center">$nowa</div></td>
                    <td width="20%"><div align="center"><a href="javascript:get_waris('$listwaris.idwaris')"> $listwaris.nama_Ob</a></div></td>
                    <td width="15%"><div align="center">$listwaris.nokpbaru</div></td>
                    <td width="5%"><div align="center">$listwaris.umur</div></td>
                    #foreach($listsaudaralist in $listsaudara)
                    
                    #if($listwaris.saudara==$listsaudaralist.id_Saudara)
                    
                    #set($wariskodsaudara=$listsaudaralist.kod)
                    #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                    
                    
                    #end    
                    #end
                    <td width="20%"><div align="center">$warissaudaraketerangan</div></td>
                    #if($listwaris.statushidup=="0")
                    #set($hidup="Masih Hidup")
                    #end
                    #if($listwaris.statushidup=="1")
                    #set($hidup="Sudah Meningal")
                    #end
                    #if($listwaris.statushidup=="")
                    #set($hidup="")
                    #end
                    <td width="20%"><div align="center">$hidup</div></td>
                     <td width="15%"><div align="center">$listwaris.lapis</div></td>
                  </tr>
                  
                  #end
                  
                  
                  
                  
                   #end
                    </table>
                 
                  #end
              
            </div></td>
          </tr>
      </table></td>
      
      #end      </tr>
  
  #if($show_lapisan_bawah=="yes")
    <tr>
      <td><table width="700">
        #set($idwww=$idparent)
        <input type="hidden" name="idwarislapisX" value="$idwww" />
          <input type="hidden" name="idparentlapisX" value="" />
          <tr>
            <td>SENARAI WARIS LAPISAN BERIKUT</td>
          </tr>
        </table>
          <div align="center">
            <table width="100%">
              <tr class="table_header">
               <td width="5%"><div align="center">NO</div></td>
                <td width="20%"><div align="center">NAMA WARIS</div></td>
                <td width="15%"><div align="center">NO KP BARU</div></td>
                <td width="5%"><div align="center">UMUR</div></td>
                <td width="15%"><div align="center">TALI PERSAUDARAAN</div></td>
                <td width="20%"><div align="center">NAMA WARIS YANG MENINGGAL</div></td>
                <td width="10%"><div align="center">STATUS</div></td>
                <td width="10%"><div align="center">LAPISAN</div></td>
              </tr>
              <!--   <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" /> -->
            </table>
            #if($listWarisLapisanIdMati.size()==0)
            <table width="100%">
              <tr bgcolor="white">
                <td align="left"> Tiada Rekod </td>
              </tr>
            </table>
            #else
            <table width="100%">
            #set($nowar=0)
              #foreach($listwarislapisan in $listWarisLapisanIdMati)
               
                 #set($nowar=$nowar+1)
                 #if($nowar%2!=0)
              <tr bgcolor="white">
              <td width="5%"><div align="center">$nowar</div></td>
            <td width="20%"><div align="center"><a href="javascript:get_waris_lapisan_X('$listwarislapisan.idwaris','$listwarislapisan.idparent')"> $listwarislapisan.nama_Ob</a></div></td>
                <td width="15%"><div align="center">$listwarislapisan.nokpbaru</div></td>
                <td width="5%"><div align="center">$listwarislapisan.umur</div></td>
                #foreach($listsaudaralist in $listsaudara)
                
                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                
                #set($wariskodsaudara=$listsaudaralist.kod)
                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                
                
                #end    
                #end
                <td width="15%"><div align="center">$warissaudaraketerangan</div></td>
                 #if($listwarislapisan.idparent=="")
                #set($idpar="")
                #else
                
                #foreach($lw in $listWarisOB)
                
                #if($lw.idwaris==$listwarislapisan.idparent)
                #set($idpar=$lw.nama_Ob)
                
               
                #end
                
                #end
                 #end
                <td width="20%"><div align="center">$idpar</div></td>
               
                #if($listwarislapisan.statushidup=="0")
                #set($hidup="Masih Hidup")
                #end
                #if($listwarislapisan.statushidup=="1")
                #set($hidup="Sudah Meningal")
                #end
                #if($listwarislapisan.statushidup=="")
                #set($hidup="")
                #end
                <td width="10%"><div align="center">$hidup</div></td>
                <td width="10%"><div align="center">$listwarislapisan.lapis</div></td>
              </tr>
              
              #else
               <tr class="table_header">
              <td width="5%"><div align="center">$nowar</div></td>
            <td width="20%"><div align="center"><a href="javascript:get_waris_lapisan_X('$listwarislapisan.idwaris','$listwarislapisan.idparent')"> $listwarislapisan.nama_Ob</a></div></td>
                <td width="15%"><div align="center">$listwarislapisan.nokpbaru</div></td>
                <td width="5%"><div align="center">$listwarislapisan.umur</div></td>
                #foreach($listsaudaralist in $listsaudara)
                
                #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
                
                #set($wariskodsaudara=$listsaudaralist.kod)
                #set($warissaudaraketerangan=$listsaudaralist.keterangan)
                
                
                #end    
                #end
                <td width="15%"><div align="center">$warissaudaraketerangan</div></td>
                 #if($listwarislapisan.idparent=="")
                #set($idpar="")
                #else
                
                #foreach($lw in $listWarisOB)
                
                #if($lw.idwaris==$listwarislapisan.idparent)
                #set($idpar=$lw.nama_Ob)
                
               
                #end
                
                #end
                 #end
                <td width="20%"><div align="center">$idpar</div></td>
               
                #if($listwarislapisan.statushidup=="0")
                #set($hidup="Masih Hidup")
                #end
                #if($listwarislapisan.statushidup=="1")
                #set($hidup="Sudah Meningal")
                #end
                #if($listwarislapisan.statushidup=="")
                #set($hidup="")
                #end
                <td width="10%"><div align="center">$hidup</div></td>
                <td width="10%"><div align="center">$listwarislapisan.lapis</div></td>
              </tr>
              #end
              #end
            </table>
            #end </div></td>
    </tr>
  
 #end 
  
  
  
  
  
  
  
   #if($show_lapisan_berikut=="yes")
   
    #if($show_lapisan_berikut_tambah=="yes")


   

    
    <tr>
    <td><table width="100%" border="0">
        <tr>
          <td width="60%" valign="top"><table width="100%">
              <input type="hidden" name="txtIdSimatiWaris" value="$id_Simati" />
              <tr>
                <td><div align="right"><span class="style38">Nama Waris Yang Meninggal :</span></div></td>
                
                
                
                  #foreach($listWarisParentlist in $listWarisParent)
                   <td class="style36">
                   $listWarisParentlist.nama_Ob
                  <input name="txtIdParent" type="hidden"  id="textfield31" size="15" value="$listWarisParentlist.idwaris" maxlength="2"  $readmode/>
                  <input name="txtLapisParent" type="hidden"  id="textfield31" size="15" value="$listWarisParentlist.lapis" maxlength="2"  $readmode/>                  </td>
            
                #end                </tr>
              <tr>
                <td width="30%"><div align="right"><span class="style38">No KP Baru :</span></div></td>
                <td width="70%" class="style36"><label>
                  <input name="txtNoKPBaru1Waris" type="text"  id="textfield30" value="$nokpbaru1" size="6" maxlength="6"  $readmode"/>
                  -
                  <input name="txtNoKPBaru2Waris" type="text"  id="textfield31" size="2" value="$nokpbaru2" maxlength="2"  $readmode/>
                  -
                  <input name="txtNoKPBaru3Waris" type="text" value="$nokpbaru3" id="textfield32" size="4" maxlength="4"  $readmode/>
                </label></td>
              </tr>
              <tr>
                <td ><div align="right"><span class="style38">No KP Lama :</span></div></td>
                <td class="style36"><label>
                  <input name="txtNoKPLamaWaris" type="text" value="$nokpwaris" id="textfield33" size="34" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
                <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode>
                  
                                            
                                           
                                            
                                      
									
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
	                                 
                                      
                                            
                                           
                                            
                  <option value="">&lt; Sila Pilih &gt;</option>
                    <option value="1">Tentera</option>
                    <option value="2">Polis</option>
                    <option value="3">Pasport</option>
                  
                                            
                                           
                                            
                                      
	                               #end
                                    
                                  
                                    
                                          
                                         
                                          
                </select>
                    <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris"  value="$nokplain" size="15" $readmode />
                    <label></label></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style41">*</span><span class="style38">Nama Waris:</span></div></td>
                <td><label>
                  <input name="txtNamaOBWaris" type="text" id="txtNamaOBWaris" value="$namaOB" size="34" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Jantina :</span></div></td>
                <td class="style36"><label>
                  <select name="socJantinaWaris" id="socJantinaWaris" class="mediumselect" $readmode>
                    
                                              
                                             
                                              
                                      
                                   #if($jantina=="1")
	                               
                                      
                                              
                                             
                                              
                    
                    <option value="1">Lelaki</option>
                    <option value="2">Perempuan</option>
                    
                                              
                                             
                                              
                                      
	                               #elseif($jantina=="1")
	                               
                                      
                                              
                                             
                                              
                    <option value="2">Perempuan</option>
                    <option value="1">Lelaki</option>
                    
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              
                    <option value="">&lt; Sila Pilih &gt;</option>
                    <option value="1">Lelaki</option>
                    <option value="2">Perempuan</option>
                    
                                              
                                             
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            
                                           
                                            
                  </select>
                </label></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Agama :</span></div></td>
                <td class="style36"><label>
                  <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode>
                    
                                              
                                             
                                              
                                      
                                   #if($agama=="1")
	                               
	                               
                                      
                                              
                                             
                                              
                    
                    <option value="1">Islam</option>
                    <option value="2">Bukan Islam</option>
                    
                                              
                                             
                                              
                                      
	                               #elseif($agama=="2")
	                               
                                      
                                              
                                             
                                              
                    <option value="2">Bukan Islam</option>
                    <option value="1">Islam</option>
                    
                                              
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                                              
                    <option value="">&lt; Sila Pilih &gt;</option>
                    <option value="1">Islam</option>
                    <option value="2">Bukan Islam</option>
                    
                                              
                                             
                                              
                                      
	                               #end
                                      
                                    
                                    
                                            
                                           
                                            
                  </select>
                </label></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Warganegara :</span></div></td>
                <td class="style36"><label>
                  <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="mediumselect" $readmode>
                    
                                              
                                             
                                              
                                      
                                   #if($warga=="1")
	                               
                                      
                                              
                                             
                                              
                    
                    <option value="1">Warganegara</option>
                    <option value="2">Bukan Warganegara</option>
                    <option value="3">Pemastautin Tetap</option>
                    
                                              
                                             
                                              
                                      
	                               #elseif($warga=="2")
	                               
                                      
                                              
                                             
                                              
                    <option value="2">Bukan Warganegara</option>
                    <option value="1">Warganegara</option>
                    <option value="3">Pemastautin Tetap</option>
                    
                                              
                                             
                                              
                                      
	                               #elseif($warga=="3")
	                               
                                      
                                              
                                             
                                              
                    <option value="3">Pemastautin Tetap</option>
                    <option value="1">Warganegara</option>
                    <option value="2">Bukan Warganegara</option>
                    
                                              
                                             
                                              
                                      
                                   #else
                                   
                                      
                                              
                                             
                                              
                    <option value="">&lt; Sila Pilih &gt;</option>
                    <option value="1">Warganegara</option>
                    <option value="2">Bukan Warganegara</option>
                    <option value="3">Pemastautin Tetap</option>
                    
                                              
                                             
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            
                                           
                                            
                  </select>
                </label></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Tarikh Lahir :</span></div></td>
                <td class="style36"><input name="txdTarikhLahirWaris" id="txdTarikhLahirWaris" type="text" value="$dob" size="15" $readmode />
                       <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">No Surat Beranak :</span></div></td>
                <td class="style36"><input name="txtNoSuratBeranakWaris" type="text" id="txtNoSuratBeranakWaris" value="$noberanak" size="34" $readmode/></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Umur :</span></div></td>
                <td class="style36"><input name="txtUmurWaris" type="text" id="txtUmurWaris" value="$umur" size="15" $readmode/></td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38">Status Waris :</span></div></td>
                <td class="style36"><select name="socStatusOBWaris"  class="mediumselect" $readmode id="socStatusOBWaris">
                  
                                            
                                           
									
								   #if($statusWaris=="1")
	                                 
                                      
                                           
                                           
                                            
                  
                  <option value="1">01 - Dewasa/Waras</option>
                    <option value="2">02 - Belum Dewasa</option>
                    <option value="3">03 - Hilang</option>
                    <option value="4">04 - Tidak Sempurna Akal</option>
                  
                                            
                                           
                                      
	                              
	                               #elseif($statusWaris=="2")
	                                
	                                  
                                           
                                            
                  <option value="2">02 - Belum Dewasa</option>
                    <option value="1">01 - Dewasa/Waras</option>
                    <option value="3">03 - Hilang</option>
                    <option value="4">04 - Tidak Sempurna Akal</option>
                  
                                            
                                           
                                      
	                              
								   #elseif($statusWaris=="3")
	                               
	                                 
                                           
                                            
                  <option value="3">03 - Hilang</option>
                    <option value="1">01 - Dewasa/Waras</option>
                    <option value="2">02 - Belum Dewasa</option>
                    <option value="4">04 - Tidak Sempurna Akal</option>
                  
                                            
                                           
                                     
                                   #elseif($statusWaris=="4")
	                                    
                                           
                                            
                  <option value="4">04 - Tidak Sempurna Akal</option>
                    <option value="1">01 - Dewasa/Waras</option>
                    <option value="2">02 - Belum Dewasa</option>
                    <option value="3">03 - Hilang</option>
                  
                                            
                                           
                                    
                                        
	                               #else
	                                 
                                      
                                           
                                            
                  <option value=""> &lt; Sila Pilih &gt; </option>
                    <option value="1">01 - Dewasa/Waras</option>
                    <option value="2">02 - Belum Dewasa</option>
                    <option value="3">03 - Hilang</option>
                    <option value="4">04 - Tidak Sempurna Akal</option>
                  
                                            
                                           
                                      
	                               #end
                                   
                                         
                                          
                </select>                </td>
              </tr>
              <tr>
                <td><div align="right"><span class="style38"><span class="style43">*</span>Tali Persaudaraan :</span></div></td>
                #foreach($listsau in $listsaudara)
                
                #if($saudara==$listsau.id_Saudara)
                
                #set($kodsaudara=$listsau.kod)
                #set($kodsaudaraketerangan=$listsau.keterangan)
                
                
                
                #end    
                #end
                <td> #if($saudara!="")
                  <select name="socSaudaraWaris" class="mediumselect" $readmode id="socSaudaraWaris">
                      <option value="$saudara">$kodsaudara - $kodsaudaraketerangan</option>
                    
                                              
                                             
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                  #if($saudara!=$listsau.id_Saudara)
                                    
	                               
                                              
                    <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
                    
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            
                                           
                                            
                  </select>
                  #else
                  <select name="socSaudaraWaris" class="mediumselect" $readmode id="socSaudaraWaris">
                    <option value=""> &lt; Sila Pilih &gt; </option>
                    
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                
	                               
                                              
                    <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
                    
                                              
                                   
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
                                            
                  </select>                </td>
                #end </tr>
              <tr>
                <td><div align="right"><span class="style38">Sudah Meninggal Dunia:</span></div></td>
                <td class="style36"><label> 
                #if($checkmati=="1")
                  #set($chq="checked")
                  #else
                  #set($chq="")
                  #end
                  <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $chq value="$checkmati" onkeypress="setSelected(0,2,0,0);tarikh_waris_lapisan()" onclick="setSelected(0,2,0,0);tarikh_waris_lapisan()" />
                </label></td>
              </tr>
            #if($show_tarikh=="yes")
            <tr>
              <td><div align="right"><span class="style38">Tarikh Mati :</span></div></td>
              <td class="style36"><input name="txdTarikhMatiWaris" id="txdTarikhMatiWaris" type="text" value="$tarikhmati" size="15" $readmode />
                    <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
            </tr>
            <tr>
              <td><div align="right"><span class="style38">Waktu Kematian :</span></div></td>
              <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" value="$waktumatiwaris" size="15" $readmode/></td>
            </tr>
            #end
          </table></td>
          <td width="40%" valign="top"><table width="100%" border="0">
              <tr>
                <td class="style38" width="35%" ><div align="right" class="style38">Alamat  :</div></td>
                <td width="65%"><label>
                  <input name="txtAlamatTerakhir1Waris" type="text" id="txtAlamatTerakhir1Waris" value="$alamat1" size="30"  $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td><label>
                  <input name="txtAlamatTerakhir2Waris" type="text" id="txtAlamatTerakhir2Waris"  value="$alamat2" size="30" $readmode />
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right"><span class="style7"></span></div></td>
                <td><input name="txtAlamatTerakhir3Waris" type="text" id="txtAlamatTerakhir3Waris" value="$alamat3" size="30" $readmode/></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style38">Poskod :</div></td>
                <td><label>
                  <input name="txtPoskodWaris" type="text" id="txtPoskodWaris" value="$poskod" size="15" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                <td><label>
                  <input name="txtBandarWaris" type="text" id="txtBandarWaris" value="$bandar" size="30" $readmode/>
                </label></td>
              </tr>
              <tr>
                <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                #foreach($listnegpomo in $listnegeri)
                
                #if($negeri==$listnegpomo.id_Negeri)
                
                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                
                
                
                #end 
                #end
                <td> #if($negeri!="")
                  <select name="socNegeriWaris" class="mediumselect" $readmode id="socNegeriWaris">
                      <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                                              
                    <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                    
                                              
                                             
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            
                  </select>
                  #else
                  <select name="socNegeriWaris" class="mediumselect" $readmode id="socNegeriWaris">
                    <option value=""> &lt; Sila Pilih &gt; </option>
                    
                                              
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                                              
                    <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                    
                                              
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
                                            
                  </select>                </td>
                #end </tr>
              <tr>
                <td class="style38" valign="top"><div align="right" class="style38">No Telepon :</div></td>
                <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" value="$notel" size="30" $readmode/></td>
              </tr>
              <tr>
                <td class="style38" valign="top"><div align="right">No HP:</div></td>
                <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" value="$hp" size="30" $readmode/></td>
              </tr>
              <tr>
                <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
                <td><textarea name="txtCatatanWaris2" cols="27"  rows="3"  $readmode>$catatan</textarea></td>
              </tr>
          </table></td>
        </tr>
    </table></td>
  </tr>
  
  #end
  
  
  #if($show_lapisan_berikut_update=="yes")
  #foreach($lwu in $listWarisLapisanUpdate)
  
  
<tr>
  <td><table width="100%" border="0">
    <tr>
      <td width="60%" valign="top">
      
      
      
      
      <table width="100%">
        <input type="hidden" name="idwarisup" value="$lwu.idwaris" />
        <input type="hidden" name="txtIdSimatiWaris" value="$lwu.idSimati" />
        <tr>
          <td><div align="right"><span class="style38">Nama Waris Yang Meninggal :</span></div></td>
          
          
                  #foreach($listWarisParentlist in $listWarisParent)
                   <td class="style36">
                   $listWarisParentlist.nama_Ob
                  <input name="txtIdParent" type="hidden"  id="txtIdParent" size="15" value="$listWarisParentlist.idwaris" maxlength="2"  $readmode/>
                  <input name="txtLapisParent" type="hidden"  id="txtLapisParent" size="15" value="$listWarisParentlist.lapis" maxlength="2"  $readmode/>                  </td>
                    #set($ip=$listWarisParentlist.idwaris)
                    #set($lp=$listWarisParentlist.lapis)
                #end        </tr>
        <tr>
          <td width="30%"><div align="right"><span class="style38">No KP Baru :</span></div></td>
          <td width="70%" class="style36"><label>
            <input name="txtNoKPBaru1Waris" type="text"  id="textfield34" value="$lwu.nokpbaru1" size="6" maxlength="6"  $readmode"/>
            -
            <input name="textfield3" type="text"  id="textfield35" size="2" value="$lwu.nokpbaru2" maxlength="2"  $readmode/>
            -
            <input name="txtNoKPBaru3Waris" type="text" value="$lwu.nokpbaru3" id="textfield36" size="4" maxlength="4"  $readmode/>
          </label></td>
        </tr>
        <tr>
          <td ><div align="right"><span class="style38">No KP Lama :</span></div></td>
          <td class="style36"><label>
            <input name="textfield3" type="text" value="$lwu.nokplama" id="textfield41" size="34" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
          <td class="style36"><select name="socJenisKPLainWaris" id="socJenisKPLainWaris" class="mediumselect" $readmode>
            
              
                                           
                                            
                                      
									
								   #if($lwu.jeniskp=="1")
	                                 
                                      
                                            
                                           
              
            
            <option value="1">Tentera</option>
            <option value="2">Polis</option>
            <option value="3">Pasport</option>
            
              
                                           
                                            
                                      
	                              
	                               #elseif($lwu.jeniskp=="2")
	                                
                                      
                                            
                                           
              
            <option value="2">Polis</option>
            <option value="1">Tentera</option>
            <option value="3">Pasport</option>
            
              
                                           
                                            
                                      
	                              
								   #elseif($lwu.jeniskp=="3")
	                               
                                      
                                            
                                           
              
            <option value="3">Pasport</option>
            <option value="1">Tentera</option>
            <option value="2">Polis</option>
            
              
                                           
                                            
                                      
	                               #else
	                                 
                                      
                                            
                                           
              
            <option value="">&lt; Sila Pilih &gt;</option>
            <option value="1">Tentera</option>
            <option value="2">Polis</option>
            <option value="3">Pasport</option>
            
              
                                           
                                            
                                      
	                               #end
                                    
                                  
                                           
                                  
                                          
                                         
            
          </select>
                  <input name="txtNoKPLainWaris" type="text" id="txtNoKPLainWaris"  value="$lwu.nokplain" size="15" $readmode />
                  <label></label></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style41">*</span><span class="style38">Nama Waris:</span></div></td>
          <td><label>
            <input name="txtNamaOBWaris" type="text" id="txtNamaOBWaris" value="$lwu.nama_Ob" size="34" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">Jantina :</span></div></td>
          <td class="style36"><label>
            <select name="socJantinaWaris" id="socJantinaWaris" class="mediumselect" $readmode>
              
                
                                             
                                              
                                      
                                   #if($lwu.jantina=="1")
	                               
                                      
                                              
                                             
                
              
              <option value="1">Lelaki</option>
              <option value="2">Perempuan</option>
              
                
                                             
                                              
                                      
	                               #elseif($lwu.jantina=="2")
	                               
                                      
                                              
                                             
                
              <option value="2">Perempuan</option>
              <option value="1">Lelaki</option>
              
                
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                
              <option value="">&lt; Sila Pilih &gt;</option>
              <option value="1">Lelaki</option>
              <option value="2">Perempuan</option>
              
                
                                             
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            
                                           
              
            </select>
          </label></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">Agama :</span></div></td>
          <td class="style36"><label>
            <select name="socAgamaWaris" id="socAgamaWaris" class="mediumselect" $readmode>
              
                
                            
                                      
                                   #if($lwu.agama=="1")
	                               
	                               
                                      
                                              
                                             
                
              
              <option value="1">Islam</option>
              <option value="2">Bukan Islam</option>
              
                
                                             
                                              
                                      
	                               #elseif($lwu.agama=="2")
	                               
                                      
                                              
                                             
                
              <option value="2">Bukan Islam</option>
              <option value="1">Islam</option>
              
                
                                             
                                              
                                      
	                               #else
	                               
                                      
                                              
                                             
                
              <option value="">&lt; Sila Pilih &gt;</option>
              <option value="1">Islam</option>
              <option value="2">Bukan Islam</option>
              
                
                                             
                                              
                                      
	                               #end
                                      
                                    
                                                    
                                           
              
            </select>
          </label></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">Warganegara :</span></div></td>
          <td class="style36"><label>
            <select name="socWarganegaraWaris" id="socWarganegaraWaris" class="mediumselect" $readmode>
              
                
                                             
                                              
                                      
                                   #if($lwu.warga=="1")
	                               
                                      
                                              
                                             
                
              
              <option value="1">Warganegara</option>
              <option value="2">Bukan Warganegara</option>
              <option value="3">Pemastautin Tetap</option>
              
                
                                             
                                              
                                      
	                               #elseif($lwu.warga=="2")
	                               
                                      
                                              
                                             
                
              <option value="2">Bukan Warganegara</option>
              <option value="1">Warganegara</option>
              <option value="3">Pemastautin Tetap</option>
              
                
                                             
                                              
                                      
	                               #elseif($lwu.warga=="3")
	                               
                                      
                                              
                                             
                
              <option value="3">Pemastautin Tetap</option>
              <option value="1">Warganegara</option>
              <option value="2">Bukan Warganegara</option>
              
                
                                             
                                              
                                      
                                   #else
                                   
                                      
                                              
                                             
                
              <option value="">&lt; Sila Pilih &gt;</option>
              <option value="1">Warganegara</option>
              <option value="2">Bukan Warganegara</option>
              <option value="3">Pemastautin Tetap</option>
              
                
                                             
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            
                                           
              
            </select>
          </label></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">Tarikh Lahir :</span></div></td>
          <td class="style36"><input name="txdTarikhLahirWaris" id="txdTarikhLahirWaris" type="text" value="$lwu.dob" size="15" $readmode />
                   <a href="javascript:displayDatePicker('txdTarikhLahirWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">No Surat Beranak:</span></div></td>
          <td class="style36"><input name="txtNoSuratBeranakWaris" type="text" id="txtNoSuratBeranakWaris" value="$lwu.noberanak" size="34" $readmode/></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">Umur :</span></div></td>
          <td class="style36"><input name="txtUmurWaris" type="text" id="txtUmurWaris" value="$lwu.umur" size="15" $readmode/></td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38">Status Waris :</span></div></td>
          <td class="style36"><select name="socStatusOBWaris"  class="mediumselect" $readmode id="socStatusOBWaris">
            
              
                                         
                                         
                                           
                                           
									
								   #if($lwu.status_Ob=="1")
	                                 
                                      
                                           
                                           
              
            
            <option value="1">01 - Dewasa/Waras</option>
            <option value="2">02 - Belum Dewasa</option>
            <option value="3">03 - Hilang</option>
            <option value="4">04 - Tidak Sempurna Akal</option>
            
              
                                           
                                      
	                              
	                               #elseif($lwu.status_Ob=="2")
	                                
	                                  
                                           
              
            <option value="2">02 - Belum Dewasa</option>
            <option value="1">01 - Dewasa/Waras</option>
            <option value="3">03 - Hilang</option>
            <option value="4">04 - Tidak Sempurna Akal</option>
            
              
                                           
                                      
	                              
								   #elseif($lwu.status_Ob=="3")
	                               
	                                 
                                           
              
            <option value="3">03 - Hilang</option>
            <option value="1">01 - Dewasa/Waras</option>
            <option value="2">02 - Belum Dewasa</option>
            <option value="4">04 - Tidak Sempurna Akal</option>
            
              
                                           
                                     
                                   #elseif($lwu.status_Ob=="4")
	                                    
                                           
              
            <option value="4">04 - Tidak Sempurna Akal</option>
            <option value="1">01 - Dewasa/Waras</option>
            <option value="2">02 - Belum Dewasa</option>
            <option value="3">03 - Hilang</option>
            
              
                                           
                                    
                                        
	                               #else
	                                 
                                      
                                           
              
            <option value="">&lt; Sila Pilih &gt;</option>
            <option value="1">01 - Dewasa/Waras</option>
            <option value="2">02 - Belum Dewasa</option>
            <option value="3">03 - Hilang</option>
            <option value="4">04 - Tidak Sempurna Akal</option>
            
              
                                           
                                      
	                               #end
                                   
                                         
            
          </select>          </td>
        </tr>
        <tr>
          <td><div align="right"><span class="style38"><span class="style43">*</span>Tali Persaudaraan :</span></div></td>
          #foreach($listsau in $listsaudara)
          
          #if($lwu.saudara==$listsau.id_Saudara)
          
          #set($kodsaudara=$listsau.kod)
          #set($kodsaudaraketerangan=$listsau.keterangan)
          
          
          
          #end    
          #end
          <td> #if($lwu.saudara!="")
            <select name="socSaudaraWaris" class="mediumselect" $readmode id="socSaudaraWaris">
                    <option value="$lwu.saudara">$kodsaudara - $kodsaudaraketerangan</option>
              
                
                                             
                                              
                                  #foreach($listsau in $listsaudara)
                                 
                                  #if($lwu.saudara!=$listsau.id_Saudara)
                                    
	                               
                
              <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
              
                
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                            
                                           
              
            </select>
            #else
            <select name="socSaudaraWaris" class="mediumselect" $readmode id="socSaudaraWaris">
              <option value="">&lt; Sila Pilih &gt;</option>
              
                
                                  #foreach($listsau in $listsaudara)
                                 
                                
	                               
                
              <option value="$listsau.id_Saudara">$listsau.kod - $listsau.keterangan</option>
              
                
                                   
                                 
	                               #end
	                               
                                         
                                  
                                  
                                  
                                            
                                           
              
            </select>          </td>
          #end </tr>
           <tr>
            <td><div align="right"><span class="style38">Sudah Meninggal Dunia:</span></div></td>
            <td class="style36"><label> #if($lwu.statushidup=="1")
              #set($ch="checked")
              #else
              #set($ch="")
              #end
              <input name="checkHidupWaris" type="checkbox" id="checkHidupWaris" $ch $readmode value="$checkmati" onkeypress="setSelected(0,2,0,0);tarikh_waris_lapisan_update()" onclick="setSelected(0,2,0,0);tarikh_waris_lapisan_update()" />
            </label></td>
          </tr>
          #if($lwu.statushidup=="1")
          <tr>
            <td><div align="right"><span class="style38">Tarikh Mati :</span></div></td>
            <td class="style36"><input name="txdTarikhMatiWaris" id="txdTarikhMatiWaris" type="text" value="$lwu.tarikhmati" size="15" $readmode />
                    <a href="javascript:displayDatePicker('txdTarikhMatiWaris',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
          </tr>
          <tr>
            <td><div align="right"><span class="style38">Waktu Kematian :</span></div></td>
            <td class="style36"><input name="txtWaktuKematianWaris" type="text" id="txtWaktuKematianWaris" value="$lwu.waktumati" size="15" $readmode/></td>
          </tr>
          #end
      </table></td>
      <td width="40%" valign="top"><table width="100%" border="0">
        <tr>
          <td class="style38" width="35%" ><div align="right" class="style38">Alamat :</div></td>
          <td width="65%"><label>
            <input name="txtAlamatTerakhir1Waris" type="text" id="txtAlamatTerakhir1Waris" value="$lwu.alamat1" size="30"  $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td><label>
            <input name="txtAlamatTerakhir2Waris" type="text" id="txtAlamatTerakhir2Waris"  value="$lwu.alamat2" size="30" $readmode />
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right"><span class="style7"></span></div></td>
          <td><input name="txtAlamatTerakhir3Waris" type="text" id="txtAlamatTerakhir3Waris" value="$lwu.alamat3" size="30" $readmode/></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style38">Poskod :</div></td>
          <td><label>
            <input name="txtPoskodWaris" type="text" id="txtPoskodWaris" value="$lwu.poskod" size="15" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style38">Bandar :</div></td>
          <td><label>
            <input name="txtBandarWaris" type="text" id="txtBandarWaris" value="$lwu.bandar" size="30" $readmode/>
          </label></td>
        </tr>
        <tr>
          <td class="style38"><div align="right" class="style38">Negeri :</div></td>
          #foreach($listnegpomo in $listnegeri)
          
          #if($lwu.idnegeri==$listnegpomo.id_Negeri)
          
          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
          
          
          
          #end 
          #end
          <td> #if($lwu.idnegeri!="")
            <select name="socNegeriWaris" class="mediumselect" $readmode id="socNegeriWaris">
                    <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
              
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                             
                
              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
              
                
                                             
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                           
              
            </select>
            #else
            <select name="socNegeriWaris" class="mediumselect" $readmode id="socNegeriWaris">
              <option value=""> &lt; Sila Pilih &gt; </option>
              
                
                                             
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                             
                
              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
              
                
                                             
                                              
                                   
                                 
	                               #end
                                  
                                  
                                     
                                  
                                            
                                           
              
            </select>          </td>
          #end </tr>
        <tr>
          <td class="style38" valign="top"><div align="right" class="style38">No Telepon :</div></td>
          <td><input name="txtNoTeleponWaris" type="text" id="txtNoTeleponWaris" value="$lwu.noTel" size="30" $readmode/></td>
        </tr>
        <tr>
          <td class="style38" valign="top"><div align="right">No HP:</div></td>
          <td><input name="txtNoTeleponBimbitWaris" type="text" id="txtNoTeleponBimbitWaris" value="$lwu.nohp" size="30" $readmode/></td>
        </tr>
        <tr>
          <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
          <td><textarea name="txtCatatanWaris3" cols="27"  rows="3"  $readmode>$lwu.catatan</textarea></td>
        </tr>
      </table></td>
    </tr>
  </table></td>
</tr>
 #end
 
 #end
 
 
 #if($show_button_lapisan=="yes")

   #foreach($lwu in $listWarisLapisanUpdate)
  <tr>
 
    <td><table width="200" border="0" align="center">
        <tr>
        #if($buttonwarislapisanSimpan!="")
          <td><input type="button" name="tambahwarislapisanSimpan" id="tambahwarisSimpan" value="$buttonwarislapisanSimpan" onkeypress="setSelected(0,2,0,0);tambah_waris_lapisan_Simpan()" onclick="setSelected(0,2,0,0);tambah_waris_lapisan_Simpan()"/></td>
        #end  
          #if($buttonwarislapisanSimpan=="") 
          <td><input type="button" name="tambahwarislapisan" id="tambahwaris4" value="$buttonwarislapisan" onkeypress="setSelected(0,2,0,0);tambah_waris_lapisan()" onclick="setSelected(0,2,0,0);tambah_waris_lapisan()"/></td>
          #end
          <td><input type="button" name="lapisanberikut3" id="lapisanberikut3" value="Lapisan Sebelum" onkeypress="setSelected(0,2,0,0);WarisView()" onclick="setSelected(0,2,0,0);WarisView()"/>          </td>
         
         
         #if($lwu.statushidup==1)
          <td>
          <label>
          <input type="button" name="lapisanberikut" id="lapisanberikut" value="Lapisan Berikut" onkeypress="setSelected(0,2,0,0);lapisan_waris_lapisan($lwu.idwaris)" onclick="setSelected(0,2,0,0);lapisan_waris_lapisan($lwu.idwaris)"/>
          </label></td>
          #end
          #if($show_batal_waris_lapisan=="yes")
          <td><label>
            <input type="button" name="cmdSimpan8" id="cmdSimpan10" value="Batal" onkeypress="saksitambahbatal()" onclick="pentingtambahbatal()"/>
          </label></td>
          #end
          <td><label>
            <input type="submit" name="cmdKembali9" id="cmdKembali11" value="Kembali" onkeypress="setSelected(0,2,0,0);WarisView()" onclick="setSelected(0,2,0,0);WarisView()"/>
          </label></td>
        </tr>
        #end
    </table></td>
  </tr>



































#end




  
  
  <tr>
    <td>
      <table width="281">
       
        
        #set($idwww=$idparent)
        <input type="hidden" name="idwarislapis" value="$idwww">
        
        <input type="hidden" name="idparentlapis" value="">
        
  <tr>
  
  
    <td><input type="button" name="naktambahlapisan" id="naktambahlapisan" value="Tambah" onkeypress="setSelected(0,2,0,0);tambah_lapisan_berikut()" onclick="setSelected(0,2,0,0);tambah_lapisan_berikut()"/></td>
  </tr>
        
  <tr>
    <td>SENARAI WARIS LAPISAN BERIKUT</td>
  </tr>
      </table>      
      <div align="center">
        <table width="100%">
          <tr class="table_header">
           <td width="5%"><div align="center">NO</div></td>
           <td width="20%"><div align="center">NAMA WARIS</div></td>
            <td width="15%"><div align="center">NO KP BARU</div></td>
            <td width="5%"><div align="center">UMUR</div></td>
            <td width="15%"><div align="center">TALI PERSAUDARAAN</div></td>
            <td width="20%"><div align="center">NAMA WARIS YANG MENINGGAL</div></td>
            <td width="10%"><div align="center">STATUS</div></td>
            <td width="10%"><div align="center">LAPISAN</div></td>
          </tr>
       <!--   <input name="idwaris" type="hidden" id="idwaris" value="$listwaris.idwaris" /> -->
       </table>
       
       
         #if($listWarisLapisan.size()==0)
                  <table width="100%">
                   <tr bgcolor="white">
                   <td align="left">
                   Tiada Rekod                   </td>
                  </tr>
                  </table>
                  #else
       
       <table width="100%">
       #set($bu=0)
       
       
          #foreach($listwarislapisan in $listWarisLapisan)
          
          #set($bu=$bu+1)
          
          #if($bu%2!=0)
          <tr bgcolor="white">
          
          <td width="5%"><div align="center">$bu</div></td>
            <td width="20%"><div align="center"><a href="javascript:get_waris_lapisan('$listwarislapisan.idwaris','$listwarislapisan.idparent')"> $listwarislapisan.nama_Ob</a></div></td>
            <td width="15%"><div align="center">$listwarislapisan.nokpbaru</div></td>
            <td width="5%"><div align="center">$listwarislapisan.umur</div></td>
            #foreach($listsaudaralist in $listsaudara)
            
            #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
            
            #set($wariskodsaudara=$listsaudaralist.kod)
            #set($warissaudaraketerangan=$listsaudaralist.keterangan)
            
            
            #end    
            #end
            
            
            
            <td width="15%"><div align="center">$warissaudaraketerangan</div></td>
             #foreach($lw in $listWa)
              
              #if($lw.idwaris==$listwarislapisan.idparent)
              #set($idpar=$lw.nama_Ob)
              
              #else
               #set($idpar="")
              #end
              
             #end
             
             
             
            <td width="20%"><div align="center">$idpar</div></td>
            
            
            #if($listwarislapisan.statushidup=="0")
            #set($hidup="Masih Hidup")
            #end
            #if($listwarislapisan.statushidup=="1")
            #set($hidup="Sudah Meningal")
            #end
            #if($listwarislapisan.statushidup=="")
            #set($hidup="")
            #end
            <td width="10%"><div align="center">$hidup</div></td>
             <td width="10%"><div align="center">$listwarislapisan.lapis</div></td>
          </tr>
          #else
            <tr class="table_header">
          
          <td width="5%"><div align="center">$bu</div></td>
            <td width="20%"><div align="center"><a href="javascript:get_waris_lapisan('$listwarislapisan.idwaris','$listwarislapisan.idparent')"> $listwarislapisan.nama_Ob</a></div></td>
            <td width="15%"><div align="center">$listwarislapisan.nokpbaru</div></td>
            <td width="5%"><div align="center">$listwarislapisan.umur</div></td>
            #foreach($listsaudaralist in $listsaudara)
            
            #if($listwarislapisan.saudara==$listsaudaralist.id_Saudara)
            
            #set($wariskodsaudara=$listsaudaralist.kod)
            #set($warissaudaraketerangan=$listsaudaralist.keterangan)
            
            
            #end    
            #end
            
            
            
            <td width="15%"><div align="center">$warissaudaraketerangan</div></td>
             #foreach($lw in $listWa)
              
              #if($lw.idwaris==$listwarislapisan.idparent)
              #set($idpar=$lw.nama_Ob)
              
              #else
               #set($idpar="")
              #end
              
             #end
             
             
             
            <td width="20%"><div align="center">$idpar</div></td>
            
            
            #if($listwarislapisan.statushidup=="0")
            #set($hidup="Masih Hidup")
            #end
            #if($listwarislapisan.statushidup=="1")
            #set($hidup="Sudah Meningal")
            #end
            #if($listwarislapisan.statushidup=="")
            #set($hidup="")
            #end
            <td width="10%"><div align="center">$hidup</div></td>
             <td width="10%"><div align="center">$listwarislapisan.lapis</div></td>
          </tr>
          #end
          #end
        </table>
        #end      </div>    </td>
  </tr>
  #end
                             </table>
                       
                     
                        </div>
                        #foreach($listmati in $listSimati)                        
                        #set($id_Simati=$listmati.idSimati)
                        
                        <table width="100%" border="1">
                        
                                       <tr>
                            <td width="683"><table width="100%" border="0">
                              <tr>
                                <td width="359"><table width="355">
                                   <input type="hidden" name="txtIdSimatiPenting" value="$id_Simati" >        
                                  <tr>
                                  <td width="117" ><div align="right"><span class="style38">No KP Baru :</span></div></td>
                                  <td width="210" class="style36"><label>
                                    <input name="txtNoKPBaru1Penting" type="text"  id="textfield" value="$nokpbaru1" size="6" maxlength="6"  $readmode"/>
                                    -
                                    <input name="txtNoKPBaru2Penting" type="text"  id="textfield2" size="2" value="$nokpbaru2" maxlength="2"  $readmode/>
                                    -
                                    <input name="txtNoKPBaru3Penting" type="text" value="$nokpbaru3" id="textfield3" size="4" maxlength="4"  $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td ><div align="right"><span class="style38">No KP Lama :</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPenting" type="text" value="$nokppenting" id="textfield4" size="34" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
                                  <td class="style36"> 
                                  
                                   
									
									 <select name="socJenisKPLainPenting"  class="smallselect" $readmode>
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
	                                 
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      <option value="3">Pasport</option>
                                      
	                               #end
                                    
                                  
                                    </select>
                                    
                                    <input name="txtNoKPLainPenting" type="text" id="textfield5"  value="$nokplain" size="15" $readmode />
                                    <label></label></td>
                                </tr>
                                  <tr>
                                    <td><div align="right"><span class="style41">*</span><span class="style38">Nama OB :</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting" type="text" id="txtNamaOBPenting" value="$namaOB" size="34" $readmode/>
                                    </label></td>
                                  </tr>
                                  <tr>
                                  <td valign="top"><div align="right"><span class="style38">Status OB :</span></div></td>
                                  <td class="style36"> 
                              
                                    
                                       <select name="socStatusOB"  class="mediumselect" $readmode>
									
								   #if($statusOB=="1")
	                                 
                                      <option value="1">01 - Dewasa/Waras</option>
                                      <option value="2">02 - Belum Dewasa</option>
                                      <option value="3">03 - Hilang</option>
                                      <option value="4">04 - Tidak Sempurna Akal</option>
                                      
	                              
	                               #elseif($statusOB=="2")
	                                
	                                  <option value="2">02 - Belum Dewasa</option>
                                     <option value="1">01 - Dewasa/Waras</option>
                                    
                                      <option value="3">03 - Hilang</option>
                                      <option value="4">04 - Tidak Sempurna Akal</option>
                                      
	                              
								   #elseif($statusOB=="3")
	                               
	                                 <option value="3">03 - Hilang</option>
                                      <option value="1">01 - Dewasa/Waras</option>
                                      <option value="2">02 - Belum Dewasa</option>
                                    
                                      <option value="4">04 - Tidak Sempurna Akal</option>
                                     
                                   #elseif($statusOB=="4")
	                                    <option value="4">04 - Tidak Sempurna Akal</option>
                                      <option value="1">01 - Dewasa/Waras</option>
                                      <option value="2">02 - Belum Dewasa</option>
                                      <option value="3">03 - Hilang</option>
                                    
                                        
	                               #else
	                                 
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">01 - Dewasa/Waras</option>
                                      <option value="2">02 - Belum Dewasa</option>
                                      <option value="3">03 - Hilang</option>
                                      <option value="4">04 - Tidak Sempurna Akal</option>
                                      
	                               #end
                                   </select>
                                   
                                    
                                                                      
                                  </td>
                                </tr>
                                   <tr>
                                          <td><div align="right"><span class="style38">Taraf Kepentingan:</span></div></td>
                                         
                                         #foreach($listtar in $listtaraf)
                                 
                                  #if($taraf==$listtar.id_Tarafkptg)
                                    
	                              #set($tarafkepentinganP=$listtar.kod)
	                              #set($tarafkepentinganketeranganP=$listtar.keterangan)
	                              
	                         
                                   
                                  #end    
	                               #end
                                         
                                       
	                                 <td>
	                               
	                              #if($taraf!="")
                                  <select name="socTarafKepentinganPenting" class="mediumselect" $readmode>
                                   <option value="$taraf">$tarafkepentinganP - $tarafkepentinganketeranganP</option>
                                  #foreach($listtar in $listtaraf)
                                 
                                  #if($taraf!=$listtar.id_Tarafkptg)
                                    
	                               <option value="$listtar.id_Tarafkptg">$listtar.kod - $listtar.keterangan</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socTarafKepentinganPenting" class="mediumselect" $readmode>
                                   <option value=""> < Sila Pilih > </option>
                                  #foreach($listtar in $listtaraf)
                                 
                                
	                               <option value="$listtar.id_Tarafkptg">$listtar.kod - $listtar.keterangan</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end
                                
                                         
                                      </tr>
                                  <tr>
                                  <td><div align="right"><span class="style38">Jantina :</span></div></td>
                                  <td class="style36"><label> 
                                    <select name="socJantinaPenting" id="select2" class="mediumselect" $readmode>
                                      
                                   #if($jantina=="1")
	                               
                                      <option value="1">Lelaki</option>
                                      <option value="2">Perempuan</option>
                                      
	                               #elseif($jantina=="1")
	                               
                                      <option value="2">Perempuan</option>
                                      <option value="1">Lelaki</option>
                                      
	                               #else
	                               
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Lelaki</option>
                                      <option value="2">Perempuan</option>
                                      
	                               #end
                                     
                                    
                                    </select>
                                    </label></td>
                                </tr> 
                                
                                 <tr>
                                  <td><div align="right"><span class="style38">Agama :</span></div></td>
                                  <td class="style36"><label> 
                                    
                                    
                                    <select name="socAgamaPenting" id="select3" class="mediumselect" $readmode>
                                      
                                   #if($agama=="1")
	                               
	                               
                                      <option value="1">Islam</option>
                                      <option value="2">Bukan Islam</option>
                                      
	                               #elseif($agama=="2")
	                               
                                      <option value="2">Bukan Islam</option>
                                      <option value="1">Islam</option>
                                      
	                               #else
	                               
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Islam</option>
                                      <option value="2">Bukan Islam</option>
                                      
	                               #end
                                      
                                    
                                    </select>
                                     </label></td>
                                </tr>
                                      
                                         <tr>
                                  <td><div align="right"><span class="style38">Warganegara :</span></div></td>
                                  <td class="style36"><label>
                                    
                                   
                                    <select name="socWarganegaraPenting" id="select4" class="mediumselect" $readmode>
                                      
                                   #if($warga=="1")
	                               
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #elseif($warga=="2")
	                               
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="1">Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #elseif($warga=="3")
	                               
                                      <option value="3">Pemastautin Tetap</option>
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      
                                   #else
                                   
                                      <option value="">< Sila Pilih ></option>
                                      <option value="1">Warganegara</option>
                                      <option value="2">Bukan Warganegara</option>
                                      <option value="3">Pemastautin Tetap</option>
                                      
	                               #end
                                  
                                    
                                    </select>
                                     </label></td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">Umur :</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtUmurPenting" type="text" id="textfield" value="$umur"  size="15" $readmode />
                                  </label></td>
                                </tr> 
                                <tr>
                                 <td ><div align="right">Tarikh Lahir  :</div></td>
                                
                                  <td>
                                     <input name="txdTarikhLahirPenting" id="txdTarikhMatiSimati" type="text" value="$dob" size="15" $readmode />
                                    <a href="javascript:displayDatePicker('txdTarikhLahirPenting',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                                   <!-- 
                                    <input name="txdTarikhLahirPenting" id="txdTarikhLahirPenting" type="text" value="" size="15" $readmode />
                                                    <a href="_javascript:displayDatePicker('txdTarikhLahirPenting',false,'dmy');"><img border="0"src="../img/calendar.gif"/></a> 
                                    -->
                                  </td>
                                 </tr>
                                <tr>
                                    <td><div align="right"><span class="style38">No Surat Beranak :</span></div></td>
                                    <td><label>
                                      <input name="txtNoSuratBeranakPenting" type="text" id="txtNoSuratBeranakPenting" value="$noberanak" size="15" $readmode/>
                                    </label></td>
                                  </tr>
                                  
                                </table></td>
                                <td width="367" valign="top"><table width="367" border="0">
                                  <tr>
                                  <td class="style38"><div align="right" class="style38">Alamat Terakhir :</div></td>
                                  <td><label>
 									
                                    <input name="txtAlamatTerakhir1Penting" type="text" id="txtAlamatTerakhir1Penting" value="$alamat1" size="30"  $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2Penting" type="text" id="txtAlamatTerakhir2Penting"  value="$alamat2" size="30" $readmode />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><input name="txtAlamatTerakhir3Penting" type="text" id="txtAlamatTerakhir3Penting" value="$alamat3" size="30" $readmode/></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38">Poskod :</div></td>
                                  <td><label>
                                    <input name="txtPoskodPenting" type="text" id="txtPoskodPenting" value="$poskod" size="15" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                                  <td><label>
                                    <input name="txtBandarPenting" type="text" id="txtBandarPenting" value="$bandar" size="30" $readmode/>
                                  </label></td>
                                </tr>
                                        <tr>
                                  <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                                   
                                    #foreach($listnegpomo in $listnegeri)
                                 
                                    #if($negeri==$listnegpomo.id_Negeri)
                                    
	                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
	                                 #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                   
                                   
                                   
                                    #end 
                                    #end  
                                  
	                               <td>
	                               
	                              #if($negeri!="")
                                  <select name="socNegeriPenting" class="mediumselect" $readmode>
                                   <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socNegeriPenting" class="mediumselect" $readmode>
                                   <option value=""> < Sila Pilih > </option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end
                                
                                   
                                  
                                  
                                  
                                  
                                                              
                                    </tr>
                                        
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
                                          <td><textarea name="txtCatatanPenting" cols="27"  rows="3"  $readmode>$catatan</textarea></td>
                                      </tr>
                                </table></td>
                              </tr>
                            </table></td>
                          </tr>
                          
                         
                          <tr>
                            <td>  <table width="200" border="0" align="center">
                                  <tr>
                                    <td><input type="button" name="tambahpenting" id="tambahpenting2" value="$buttonpenting" onkeypress="setSelected(0,3,0,0);tambah_penting()" onclick="setSelected(0,3,0,0);tambah_penting()"/></td>
                                
                                    
                                
                                    
                                    
                                    
                                     <td><label>
                                      <input type="button" name="batalpenting" id="cmdSimpan3" value="Batal" onKeyPress="setSelected(0,3,0,0);pentingbatal()" onclick="setSelected(0,3,0,0);pentingbatal()"/>
                                    </label></td>
                                     
                                    
                                   
                                     <td><label>
                                      <input type="submit" name="cmdKembali3" id="cmdKembali3" value="Kembali" onKeyPress="PentingView()" onclick="PentingView()" />
                                    </label></td>
                                  </tr>
                                 
                                </table></td>
                                
                          </tr>
                          
                           
                          <tr>
                            <td>
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                              <table width="281">
                                <tr>
                                  <td>SENARAI ORANG BERKEPENTINGAN</td>
                                </tr>
                              </table>
                             
                              <table width="100%" >
                                <tr>
                                  <td width="647"><label>
                                    <input type="submit" name="cmdPapar" id="cmdPapar" value="Tambah" onKeyPress="tambah_penting_baru()" onclick="tambah_penting_baru()"/>
                                  </label>
                                    <label></label></td>
                                </tr>
                                
                               
          
       
                                
                                
                                <tr>
                                  <td><table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                      <td width="40%"><div align="center">NAMA OB</div></td>
                                      <td width="25%"><div align="center">NO KP BARU</div></td>
                                      <td width="30%"><div align="center">STATUS OB</div></td>
                                    </tr>
                                    #set($peno=0)
                                      #foreach($listpenting in $listPenting)
                                        #set($peno=$peno+1)
          
         							 #if($peno%2!=0)
                                    <tr bgcolor="white">
                                    <td><div align="center">$peno</div></td>
                                      <td>
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb', '$listpenting.nama_Ob', '$listpenting.nokpbaru1','$listpenting.nokpbaru2','$listpenting.nokpbaru3','$listpenting.idSimati','$listpenting.nokplama','$listpenting.jeniskp','$listpenting.nokplain','$listpenting.noberanak','$listpenting.dob','$listpenting.idnegeri','$listpenting.noTel','$listpenting.jantina','$listpenting.umur','$listpenting.alamat1','$listpenting.alamat2','$listpenting.alamat3','$listpenting.bandar','$listpenting.status_Ob','$listpenting.taraf','$listpenting.saudara','$listpenting.agama','$listpenting.catatan','$listpenting.warga','$listpenting.poskod')">
                                       <div align="center"> $listpenting.nama_Ob</div>   
                                          <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>
         
          
          
                                          
                                   
                                      
                                      </td>
                                      <td><div align="center">$listpenting.nokpbaru</div></td>
                                      <td><div align="center">$listpenting.status_Ob</div></td>
                                    </tr>
                                    #else
                                        <tr class="table_header">
                                  
                                   <td><div align="center">$peno</div></td>
                                      <td>
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb', '$listpenting.nama_Ob', '$listpenting.nokpbaru1','$listpenting.nokpbaru2','$listpenting.nokpbaru3','$listpenting.idSimati','$listpenting.nokplama','$listpenting.jeniskp','$listpenting.nokplain','$listpenting.noberanak','$listpenting.dob','$listpenting.idnegeri','$listpenting.noTel','$listpenting.jantina','$listpenting.umur','$listpenting.alamat1','$listpenting.alamat2','$listpenting.alamat3','$listpenting.bandar','$listpenting.status_Ob','$listpenting.taraf','$listpenting.saudara','$listpenting.agama','$listpenting.catatan','$listpenting.warga','$listpenting.poskod')">
                                       <div align="center"> $listpenting.nama_Ob</div>   
                                          <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>
         
          
          
                                          
                                   
                                      
                                      </td>
                                      <td><div align="center">$listpenting.nokpbaru</div></td>
                                      <td><div align="center">$listpenting.status_Ob</div></td>
                                    </tr>
                                    
                                    #end
                                    #end
                                  </table></td>
                                </tr>
                              </table>                              </td>
                          </tr>
                        </table>
                       
                        #end
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        <div class="TabbedPanelsContent">
                          <table width="100%" border="1">
                            <tr>
                              <td width="683"><table width="760" border="0">
                                  <tr>
                                    <td width="359" valign="top"><table width="355">
                                        <input type="hidden" name="txtIdSimatiSaksi" value="$id_Simati" />
                                        <tr>
                                          <td width="117"><div align="right"><span class="style38">No KP Baru :</span></div></td>
                                          <td width="210" class="style36"><label>
                                            <input name="txtNoKPBaru1Saksi" type="text"  id="textfield11" value="$nokpbaru1" size="6" maxlength="6"  $readmode"/>
                                            -
                                            <input name="txtNoKPBaru2Saksi" type="text"  id="textfield14" size="2" value="$nokpbaru2" maxlength="2"  $readmode/>
                                            -
                                            <input name="txtNoKPBaru3Saksi" type="text" value="$nokpbaru3" id="textfield15" size="4" maxlength="4"  $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td ><div align="right"><span class="style38">No KP Lama :</span></div></td>
                                          <td class="style36"><label>
                                            <input name="txtNoKPLamaSaksi" type="text" value="$nokpsaksi" id="textfield16" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
                                          <td class="style36"><select name="socJenisKPLainSaksi" id="socJenisKPLainPenting" class="mediumselect" $readmode>
                                            
                                      
									
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
	                                 
                                      
                                            <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Tentera</option>
                                              <option value="2">Polis</option>
                                              <option value="3">Pasport</option>
                                            
                                      
	                               #end
                                    
                                  
                                    
                                          </select>
                                              <input name="txtNoKPLainSaksi" type="text" id="txtNoKPLainSaksi"  value="$nokplain" size="15" $readmode />
                                              <label></label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style41">*</span><span class="style38">Nama Saksi :</span></div></td>
                                          <td><label>
                                            <input name="txtNamaOBSaksi" type="text" id="txtNamaOBSaksi" value="$namaOB" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Jantina :</span></div></td>
                                          <td class="style36"><label>
                                            <select name="socJantinaSaksi" id="socJantinaSaksi" class="mediumselect" $readmode>
                                              
                                      
                                   #if($jantina=="1")
	                               
                                      
                                              <option value="1">Lelaki</option>
                                              <option value="2">Perempuan</option>
                                              
                                      
	                               #elseif($jantina=="1")
	                               
                                      
                                              <option value="2">Perempuan</option>
                                              <option value="1">Lelaki</option>
                                              
                                      
	                               #else
	                               
                                      
                                              <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Lelaki</option>
                                              <option value="2">Perempuan</option>
                                              
                                      
	                               #end
                                     
                                    
                                    
                                            </select>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Agama :</span></div></td>
                                          <td class="style36"><label>
                                            <select name="socAgamaSaksi" id="socAgamaSaksi" class="mediumselect" $readmode>
                                              
                                      
                                   #if($agama=="1")
	                               
	                               
                                      
                                              <option value="1">Islam</option>
                                              <option value="2">Bukan Islam</option>
                                              
                                      
	                               #elseif($agama=="2")
	                               
                                      
                                              <option value="2">Bukan Islam</option>
                                              <option value="1">Islam</option>
                                              
                                      
	                               #else
	                               
                                      
                                              <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Islam</option>
                                              <option value="2">Bukan Islam</option>
                                              
                                      
	                               #end
                                      
                                    
                                    
                                            </select>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">Warganegara :</span></div></td>
                                          <td class="style36"><label>
                                            <select name="socWarganegaraSaksi" id="socWarganegaraSaksi" class="mediumselect" $readmode>
                                              
                                      
                                   #if($warga=="1")
	                               
                                      
                                              <option value="1">Warganegara</option>
                                              <option value="2">Bukan Warganegara</option>
                                              <option value="3">Pemastautin Tetap</option>
                                              
                                      
	                               #elseif($warga=="2")
	                               
                                      
                                              <option value="2">Bukan Warganegara</option>
                                              <option value="1">Warganegara</option>
                                              <option value="3">Pemastautin Tetap</option>
                                              
                                      
	                               #elseif($warga=="3")
	                               
                                      
                                              <option value="3">Pemastautin Tetap</option>
                                              <option value="1">Warganegara</option>
                                              <option value="2">Bukan Warganegara</option>
                                              
                                      
                                   #else
                                   
                                      
                                              <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Warganegara</option>
                                              <option value="2">Bukan Warganegara</option>
                                              <option value="3">Pemastautin Tetap</option>
                                              
                                      
	                               #end
                                  
                                    
                                    
                                            </select>
                                          </label></td>
                                        </tr>
                                    </table></td>
                                    <td width="367" valign="top"><table width="367" border="0">
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Alamat Terakhir :</div></td>
                                          <td><label>
                                            <input name="txtAlamatTerakhir1Saksi" type="text" id="txtAlamatTerakhir1Saksi" value="$alamat1" size="34"  $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><label>
                                            <input name="txtAlamatTerakhir2Saksi" type="text" id="txtAlamatTerakhir2Saksi"  value="$alamat2" size="34" $readmode />
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><input name="txtAlamatTerakhir3Saksi" type="text" id="txtAlamatTerakhir3Saksi" value="$alamat3" size="34" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Poskod :</div></td>
                                          <td><label>
                                            <input name="txtPoskodSaksi" type="text" id="txtPoskodSaksi" value="$poskod" size="15" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                                          <td><label>
                                            <input name="txtBandarSaksi" type="text" id="txtBandarSaksi" value="$bandar" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                                          #foreach($listnegpomo in $listnegeri)
                                          
                                          #if($negeri==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          <td> #if($negeri!="")
                                            <select name="socNegeriSaksi" class="mediumselect" $readmode>
                                                <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            </select>
                                            #else
                                            <select name="socNegeriSaksi" class="mediumselect" $readmode>
                                              <option value=""> &lt; Sila Pilih &gt; </option>
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            </select>                                          </td>
                                          #end </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">No Telepon :</div></td>
                                          <td><input name="txtNoTeleponSaksi" type="text" id="txtNoTeleponSaksi" value="$notel" size="34" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
                                          <td><textarea name="txtCatatanSaksi" cols="27"  rows="3"  $readmode>$catatan</textarea></td>
                                        </tr>
                                    </table></td>
                                  </tr>
                              </table></td>
                            </tr>
                            <tr>
                              <td><table width="200" border="0" align="center">
                                  <tr>
                                    <td><label>
                                      <input type="button" name="tambahsaksi" id="tambahsaksi" value="$buttonsaksi" onkeypress="setSelected(0,4,0,0);tambah_saksi()" onclick="setSelected(0,4,0,0);tambah_saksi()"/>
                                    </label></td>
                                    <td><label>
                                      <input type="reset" name="cmdSimpan5" id="cmdSimpan5" value="Batal" onkeypress="saksitambahbatal()" onclick="pentingtambahbatal()"/>
                                    </label></td>
                                    <td><label>
                                      <input type="submit" name="cmdKembali5" id="cmdKembali5" value="Kembali" onkeypress="kembali_simati()" onclick="kembali_simati()" />
                                    </label></td>
                                  </tr>
                              </table></td>
                            </tr>
                            <tr>
                              <td><table width="281">
                                  <tr>
                                    <td>SENARAI SAKSI</td>
                                  </tr>
                                </table>
                                  <table width="100%" >
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  <tr>
                                    <td><input type="submit" name="cmdPapar3" id="cmdPapar3" value="Tambah" onkeypress="SaksiView()" onclick="SaksiView()"/></td>
                                  </tr>
                                  <tr>
                                  <td>
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                     <td width="50%"><div align="center">NAMA SAKSI</div></td>
                                     <td width="45%"><div align="center">NO KP BARU</div></td>
                                    </tr>
                                    #set($p=0)
                                      #foreach($listsaksi in $listSaksi)
                                        #set($p=$p+1)
          
         							 #if($p%2!=0)
                                    <tr bgcolor="white">
                                    <td><div align="center">$p</div></td>
                                      <td>
                                      
                                        <div align="center"><a href="javascript:edit_item_saksi('$listsaksi.idOb', '$listsaksi.nama_Ob', '$listsaksi.nokpbaru1','$listsaksi.nokpbaru2','$listsaksi.nokpbaru3','$listsaksi.idSimati','$listsaksi.nokplama','$listsaksi.jeniskp','$listsaksi.nokplain','$listsaksi.idnegeri','$listsaksi.noTel','$listsaksi.jantina','$listsaksi.alamat1','$listsaksi.alamat2','$listsaksi.alamat3','$listsaksi.bandar','$listsaksi.agama','$listsaksi.catatan','$listsaksi.warga','$listsaksi.poskod')"> 
                                          $listsaksi.nama_Ob</a>
                                          
                                          <input name="idsaksi" type="hidden" id="idsaksi" value="$listsaksi.idOb" />                                              
                                        </div></td>
                                              <td><div align="center">$listsaksi.nokpbaru</div></td>
                                            </tr>
                                    #else
                                        <tr class="table_header">
                                  
                                   <td><div align="center">$p</div></td>
                                      <td>
                                      
                                        <div align="center"><a href="javascript:edit_item_saksi('$listsaksi.idOb', '$listsaksi.nama_Ob', '$listsaksi.nokpbaru1','$listsaksi.nokpbaru2','$listsaksi.nokpbaru3','$listsaksi.idSimati','$listsaksi.nokplama','$listsaksi.jeniskp','$listsaksi.nokplain','$listsaksi.idnegeri','$listsaksi.noTel','$listsaksi.jantina','$listsaksi.alamat1','$listsaksi.alamat2','$listsaksi.alamat3','$listsaksi.bandar','$listsaksi.agama','$listsaksi.catatan','$listsaksi.warga','$listsaksi.poskod')"> 
                                          $listsaksi.nama_Ob</a>
                                          
                                          <input name="idsaksi" type="hidden" id="idsaksi" value="$listsaksi.idOb" />                                              
                                        </div></td>
                                              <td><div align="center">$listsaksi.nokpbaru</div></td>
                                            </tr>
                                    
                                    #end
                                    #end
                                  </table>                                  </td>
                                </tr>
                                </table></td>
                            </tr>
                          </table>
                        </div>
                        <div class="TabbedPanelsContent">
                          <table width="765" border="1">
                            <tr>
                              <td width="683"><table width="760" border="0">
                                  <tr>
                                    <td width="359" valign="top"><table width="355">
                                        <input type="hidden" name="txtIdSimatiPemiutang" value="$id_Simati" />
                                        <tr>
                                          <td width="117"><div align="right"><span class="style38">No KP Baru :</span></div></td>
                                          <td width="210" class="style36"><label>
                                            <input name="txtNoKPBaru1Pemiutang" type="text"  id="textfield12" value="$nokpbaru1" size="6" maxlength="6"  $readmode"/>
                                            -
                                            <input name="txtNoKPBaru2Pemiutang" type="text"  id="textfield17" size="2" value="$nokpbaru2" maxlength="2"  $readmode/>
                                            -
                                            <input name="txtNoKPBaru3Pemiutang" type="text" value="$nokpbaru3" id="textfield18" size="4" maxlength="4"  $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td ><div align="right"><span class="style38"> No Syarikat:</span></div></td>
                                          <td class="style36"><label>
                                            <input name="txtNoKPLamaPemiutang" type="text" value="$nokppenting" id="textfield19" size="30" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
                                          <td class="style36"><select name="socJenisKPLainPemiutang" id="socJenisKPLainPemiutang" class="mediumselect" $readmode>
                                            
                                            
                                      
									
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
	                                 
                                      
                                            
                                            <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Tentera</option>
                                              <option value="2">Polis</option>
                                              <option value="3">Pasport</option>
                                            
                                            
                                      
	                               #end
                                    
                                  
                                    
                                          
                                          </select>
                                              <input name="txtNoKPLainPemiutang" type="text" id="textfield25"  value="$nokplain" size="15" $readmode />
                                              <label></label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style41">*</span><span class="style38">Nama Pemiutang :</span></div></td>
                                          <td><label>
                                            <input type="text" name="txtNamaPemiutang" id="txtNamaPemiutang" value="$namaPG" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">No Akaun :</span></div></td>
                                          <td class="style36"><label>
                                            <input name="txtNoAkaunPemiutang" type="text" id="txtNoAkaunPemiutang" value="$noakaunhutang"  size="30" $readmode />
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td ><div align="right"><span class="style38">Jumlah Hutang (RM):</span></div></td>
                                          <td><input name="txtJumlahPemiutang" type="text" id="txtJumlahPemiutang" value="$jumlahhutang"  size="30" $readmode /></td>
                                        </tr>

                                    </table></td>
                                    <td width="367" valign="top"><table width="367" border="0">
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Alamat Terakhir :</div></td>
                                          <td><label>
                                            <input name="txtAlamatTerakhir1Pemiutang" type="text" id="txtAlamatTerakhir1Pemiutang" value="$alamat1" size="30"  $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><label>
                                            <input name="txtAlamatTerakhir2Pemiutang" type="text" id="txtAlamatTerakhir2Pemiutang"  value="$alamat2" size="30" $readmode />
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><input name="txtAlamatTerakhir3Pemiutang" type="text" id="txtAlamatTerakhir3Pemiutang" value="$alamat3" size="30" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Poskod :</div></td>
                                          <td><label>
                                            <input name="txtPoskodPemiutang" type="text" id="txtPoskodPemiutang" value="$poskod" size="15" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                                          <td><label>
                                            <input name="txtBandarPemiutang" type="text" id="txtBandarPemiutang" value="$bandar" size="30" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                                          #foreach($listnegpomo in $listnegeri)
                                          
                                          #if($negeri==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          <td> #if($negeri!="")
                                            <select name="socNegeriPemiutang" class="mediumselect" $readmode>
                                                <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                              
                                              
                                              
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                              
                                              
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                              
                                              
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                            
                                            
                                            </select>
                                            #else
                                            <select name="socNegeriPemiutang" class="mediumselect" $readmode>
                                              <option value=""> &lt; Sila Pilih &gt; </option>
                                              
                                              
                                              
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                              
                                              
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                              
                                              
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                            
                                            
                                            </select>                                          </td>
                                          #end </tr>
                                        <tr>
                                          <td valign="top"><div align="right"><span class="style38">Butiran Hutang :</span></div></td>
                                          <td><textarea name="txtCatatanPemiutang" cols="27"  rows="3" $readmode>$catatan</textarea></td>
                                        </tr>
                                    </table></td>
                                  </tr>
                              </table></td>
                            </tr>
                            <tr>
                              <td><table width="200" border="0" align="center">
                                  <tr>
                                  
                                    <td><label>
                                      <input type="button" name="tambahpemiutang" id="tambahpemiutang" value="$tambah_pemiutang" onkeypress="setSelected(0,5,0,0);tambah_pemiutang()" onclick="setSelected(0,5,0,0);tambah_pemiutang()"/>
                                    </label></td>
                                    
                                    <td><label>
                                      <input type="reset" name="cmdSimpan" id="cmdSimpan" value="Batal" onkeypress="pentingtambahbatal()" onclick="pentingtambahbatal()"/>
                                    </label></td>
                                    <td><label>
                                      <input type="submit" name="cmdKembali4" id="cmdKembali4" value="Kembali" onkeypress="kembali_simati()" onclick="kembali_simati()" />
                                    </label></td>
                                  </tr>
                              </table></td>
                            </tr>
                            <tr>
                              <td><table width="281">
                                  <tr>
                                    <td>SENARAI PEMIUTANG</td>
                                  </tr>
                                </table>
                                  <table width="100%" >
                                    <tr>
                                      <td width="647"><label>
                                        <input type="submit" name="cmdPapar4" id="cmdPapar4" value="Tambah" />
                                        </label>
                                          <label></label></td>
                                    </tr>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    <tr>
                                  <td>
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                       <td width="25%"><div align="center">NAMA PEMIUTANG</div></td>
                                             <td width="15%"><div align="center">NO KP BARU</div></td>
                                            <td width="10%">NO SYARIKAT</td>
                                            <td width="15%"><div align="center">NO AKAUN</div></td>
                                          
                                            <td width="15%"><div align="center">JUMLAH HUTANG (RM)</div></td>
                                            <td width="15%"><div align="center">BUTIRAN HUTANG</div></td>
                                    </tr>
                                    #set($px=0)
                                   #foreach($listpemiutang in $listPemiutang)
                                        #set($px=$px+1)
          
         							 #if($px%2!=0)
                                    <tr bgcolor="white">
                                    <td><div align="center">$px</div></td>
                                      <td><a href="javascript:edit_item_pemiutang('$listpemiutang.idpm','$listpemiutang.idSimati', '$listpemiutang.nama_Pg','$listpemiutang.jumlah','$listpemiutang.noakaun','$listpemiutang.nokpbaru1','$listpemiutang.nokpbaru2','$listpemiutang.nokpbaru3','$listpemiutang.nokplama','$listpemiutang.jeniskp','$listpemiutang.nokplain','$listpemiutang.idnegeri','$listpemiutang.alamat1','$listpemiutang.alamat2','$listpemiutang.alamat3','$listpemiutang.bandar','$listpemiutang.poskod','$listpemiutang.catatan')">
                    <div align="center"> $listpemiutang.nama_Pg</div>
                                            <input name="idpm" type="hidden" id="idpm" value="$listpemiutang.idpm" />    
                                          </td>
                                          <td><div align="center">$listpemiutang.nokpbaru</div></td>
                                          <td><div align="center">$listpemiutang.nokplama</div></td>
                                         
                                          <td><div align="center">$listpemiutang.noakaun</div></td>
                                          
                                          <td><div align="center">$listpemiutang.jumlah</div></td>
                                          <td><div align="center">$listpemiutang.catatan</div></td>
                                            </tr>
                                    #else
                                        <tr class="table_header">
                                  
                                   <td><div align="center">$px</div></td>
                                      <td><a href="javascript:edit_item_pemiutang('$listpemiutang.idpm','$listpemiutang.idSimati', '$listpemiutang.nama_Pg','$listpemiutang.jumlah','$listpemiutang.noakaun','$listpemiutang.nokpbaru1','$listpemiutang.nokpbaru2','$listpemiutang.nokpbaru3','$listpemiutang.nokplama','$listpemiutang.jeniskp','$listpemiutang.nokplain','$listpemiutang.idnegeri','$listpemiutang.alamat1','$listpemiutang.alamat2','$listpemiutang.alamat3','$listpemiutang.bandar','$listpemiutang.poskod','$listpemiutang.catatan')">
                    <div align="center"> $listpemiutang.nama_Pg</div>
                                            <input name="idpm" type="hidden" id="idpm" value="$listpemiutang.idpm" />    
                                          </td>
                                          <td><div align="center">$listpemiutang.nokpbaru</div></td>
                                          <td><div align="center">$listpemiutang.nokplama</div></td>
                                         
                                          <td><div align="center">$listpemiutang.noakaun</div></td>
                                          
                                          <td><div align="center">$listpemiutang.jumlah</div></td>
                                          <td><div align="center">$listpemiutang.catatan</div></td>
                                            </tr>
                                    
                                    #end
                                    #end
                                  </table>                                  </td>
                                </tr>
                                    
                                    
                                   
                                    
                                </table></td>
                            </tr>
                          </table>
                        </div>
                        
                        <div class="TabbedPanelsContent">
                          <table width="100%" border="1">
                            <tr>
                              <td width="683"><table width="100%" border="0">
                                  <tr>
                                    <td width="359" valign="top"><table width="355">
                                        <input type="hidden" name="txtIdSimatiPenghutang" value="$id_Simati" />
                                         <tr>
                                          <td valign="top"><div align="right"><span class="style38">Jenis Hutang :</span></div></td>
                                          <td class="style36"><select name="socJenisHutang" id="socJenisHutang" class="mediumselect" $readmode>
                                            
                                      
									
								   #if($jenisHutang=="1")
	                                 
                                       <option value="1">01 - Agensi</option>
                                              <option value="2">02 - Individu</option>
                                      
	                              
	                               #elseif($jenisHutang=="2")
	                                
                                      
                                            <option value="2">02 - Individu</option>
                                              <option value="1">01 - Agensi</option>
                                             
                                            
                                      
	                              
								
	                               #else
	                                 
                                      
                                            <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">01 - Agensi</option>
                                              <option value="2">02 - Individu</option>
                                             
                                      
	                               #end
                                    
                                  
                                    
                                          </select>
                                            
                                              <label></label></td>
                                        </tr>
                                        <tr>
                                          <td width="117"><div align="right"><span class="style38">No KP Baru :</span></div></td>
                                          <td width="210" class="style36"><label>
                                            <input name="txtNoKPBaru1Penghutang" type="text"  id="textfield37" value="$nokpbaru1" size="6" maxlength="6"  $readmode"/>
-
<input name="txtNoKPBaru2Penghutang" type="text"  id="textfield38" size="2" value="$nokpbaru2" maxlength="2"  $readmode/>
-
<input name="txtNoKPBaru3Penghutang" type="text" value="$nokpbaru3" id="textfield42" size="4" maxlength="4"  $readmode/>
</label></td>
                                        </tr>
                                        <tr>
                                          <td ><div align="right"><span class="style38">No KP Lama/ No Syarikat:</span></div></td>
                                          <td class="style36">
                                            <input name="txtNoKPLamaPenghutang" type="text" value="$nokppenting" id="textfield39" size="34" $readmode/>
                                          </td>
                                        </tr>
                                        <tr>
                                          <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
                                          <td class="style36"><select name="socJenisKPLainPenghutang" id="socJenisKPLainPenghutang" class="mediumselect" $readmode>
                                            
                                      
									
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
	                                 
                                      
                                            <option value="">&lt; Sila Pilih &gt;</option>
                                              <option value="1">Tentera</option>
                                              <option value="2">Polis</option>
                                              <option value="3">Pasport</option>
                                            
                                      
	                               #end
                                    
                                  
                                    
                                          </select>
                                              <input name="txtNoKPLainPenghutang" type="text" id="textfield40"  value="$nokplain" size="15" $readmode />
                                              <label></label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style41">*</span><span class="style38">Nama Penghutang :</span></div></td>
                                          <td><label>
                                            <input name="txtNamaPenghutang" type="text" id="txtNamaPenghutang" value="$namaPG" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td><div align="right"><span class="style38">No Akaun :</span></div></td>
                                          <td class="style36"><label>
                                            <input name="txtNoAkaunPenghutang" type="text" id="txtNoAkaunPenghutang" value="$noakaunhutang"  size="34" $readmode />
                                           
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td ><div align="right"><span class="style38">Jumlah Hutang :</span></div></td>
                                          <td><input name="txtJumlahPenghutang" type="text" id="txtJumlahPenghutang" value="$jumlahhutang"  size="34" $readmode /></td>
                                        </tr>
                                        <tr>
                                          <td valign="top"><div align="right"><span class="style38">Butiran Hutang :</span></div></td>
                                          <td><textarea name="txtCatatanPenghutang" cols="27"  rows="3" $readmode >$catatan</textarea></td>
                                        </tr>
                                    </table></td>
                                    <td width="367" valign="top"><table width="367" border="0">
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Alamat Terakhir :</div></td>
                                          <td><label>
                                            <input name="txtAlamatTerakhir1Penghutang" type="text" id="txtAlamatTerakhir1Penghutang" value="$alamat1" size="34"  $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><label>
                                            <input name="txtAlamatTerakhir2Penghutang" type="text" id="txtAlamatTerakhir2Penghutang"  value="$alamat2" size="34" $readmode />
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right"><span class="style7"></span></div></td>
                                          <td><input name="txtAlamatTerakhir3Penghutang" type="text" id="txtAlamatTerakhir3Penghutang" value="$alamat3" size="34" $readmode/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Poskod :</div></td>
                                          <td><label>
                                            <input name="txtPoskodPenghutang" type="text" id="txtPoskodPenghutang" value="$poskod" size="15" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                                          <td><label>
                                            <input name="txtBandarPenghutang" type="text" id="txtBandarPenghutang" value="$bandar" size="34" $readmode/>
                                          </label></td>
                                        </tr>
                                        <tr>
                                          <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                                          #foreach($listnegpomo in $listnegeri)
                                          
                                          #if($negeri==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          <td> #if($negeri!="")
                                            <select name="socNegeriPenghutang" class="mediumselect" $readmode>
                                                <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            </select>
                                            #else
                                            <select name="socNegeriPenghutang" class="mediumselect" $readmode>
                                              <option value=""> &lt; Sila Pilih &gt; </option>
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            </select>                                          </td>
                                          #end </tr>
                                    </table></td>
                                  </tr>
                              </table></td>
                            </tr>
                            <tr>
                              <td><table width="200" border="0" align="center">
                                  <tr>
                                    <td><label>
                                      <input type="button" name="tambahpenghutang" id="tambahpenghutang" value="$tambah_penghutang" onkeypress="setSelected(0,7,0,0);tambah_penghutang()" onclick="setSelected(0,7,0,0);tambah_penghutang()"/>
                                    </label></td>
                                    <td><label>
                                      <input type="reset" name="cmdSimpan4" id="cmdSimpan4" value="Batal" onkeypress="pentingtambahbatal()" onclick="pentingtambahbatal()"/>
                                    </label></td>
                                    <td><label>
                                      <input type="submit" name="cmdKembali6" id="cmdKembali6" value="Kembali" onkeypress="kembali_simati()" onclick="kembali_simati()" />
                                    </label></td>
                                  </tr>
                              </table></td>
                            </tr>
                            <tr>
                              <td><table width="281">
                                  <tr>
                                    <td>SENARAI PENGHUTANG</td>
                                  </tr>
                                </table>
                                  <table width="100%" >
                                    <tr>
                                      <td width="647"><label>
                                        <input type="submit" name="cmdPapar2" id="cmdPapar2" value="Tambah" onkeypress="PenghutangView()" onclick="PenghutangView()"/>
                                        </label>
                                          <label></label></td>
                                    </tr>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    <tr>
                                  <td>
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                       <td width="25%"><div align="center">NAMA PEMIUTANG</div></td>
                                             <td width="15%"><div align="center">JENIS PENGHUTANG</div></td>
                                            <td width="15%">NO KP BARU</td>
                                            
                                            <td width="20%"><div align="center">JUMLAH HUTANG (RM)</div></td>
                                            <td width="20%"><div align="center">BUTIRAN HUTANG</div></td>
                                            
                                         
                                            
                                    </tr>
                                    #set($pll=0)
                                    #foreach($listpenghutang in $listPenghutang)
                                        #set($pll=$pll+1)
          
         							 #if($pll%2!=0)
                                    <tr bgcolor="white">
                                    <td><div align="center">$pll</div></td>
                                      <td><a href="javascript:edit_item_penghutang('$listpenghutang.idpg','$listpenghutang.idSimati', '$listpenghutang.nama_Pg','$listpenghutang.jumlah','$listpenghutang.jenishutang','$listpenghutang.noakaun','$listpenghutang.nokpbaru1','$listpenghutang.nokpbaru2','$listpenghutang.nokpbaru3','$listpenghutang.nokplama','$listpenghutang.jeniskp','$listpenghutang.nokplain','$listpenghutang.idnegeri','$listpenghutang.alamat1','$listpenghutang.alamat2','$listpenghutang.alamat3','$listpenghutang.bandar','$listpenghutang.poskod','$listpenghutang.catatan')">
                                            
                                            <div align="center"> $listpenghutang.nama_Pg</div>
                                            <input name="idpg" type="hidden" id="idpg" value="$listpenghutang.idpg" />
                                          </td>
                                          
                                          #if($listpenghutang.jenishutang=="1")
                                          #set($jenis="Agensi")
                                          #end
                                          #if($listpenghutang.jenishutang=="2")
                                          #set($jenis="Individu")
                                          #end
                                          #if($listpenghutang.jenishutang=="")
                                          #set($jenis="")
                                          #end
                                              
                                          <td><div align="center">$jenis</div></td>
                                          <td><div align="center">$listpenghutang.nokpbaru</div></td>
                                          <td><div align="center">$listpenghutang.jumlah</div></td>
                                          <td><div align="center">$listpenghutang.catatan</div></td>
                                            </tr>
                                    #else
                                        <tr class="table_header">
                                  
                                   <td><div align="center">$pll</div></td>
                                      <td><a href="javascript:edit_item_penghutang('$listpenghutang.idpg','$listpenghutang.idSimati', '$listpenghutang.nama_Pg','$listpenghutang.jumlah','$listpenghutang.jenishutang','$listpenghutang.noakaun','$listpenghutang.nokpbaru1','$listpenghutang.nokpbaru2','$listpenghutang.nokpbaru3','$listpenghutang.nokplama','$listpenghutang.jeniskp','$listpenghutang.nokplain','$listpenghutang.idnegeri','$listpenghutang.alamat1','$listpenghutang.alamat2','$listpenghutang.alamat3','$listpenghutang.bandar','$listpenghutang.poskod','$listpenghutang.catatan')">
                                            
                                            <div align="center"> $listpenghutang.nama_Pg</div>
                                            <input name="idpg" type="hidden" id="idpg" value="$listpenghutang.idpg" />
                                          </td>
                                          #if($listpenghutang.jenishutang=="1")
                                          #set($jenis="Agensi")
                                          #end
                                          #if($listpenghutang.jenishutang=="2")
                                          #set($jenis="Individu")
                                          #end
                                          #if($listpenghutang.jenishutang=="")
                                          #set($jenis="")
                                          #end
                                              
                                          <td><div align="center">$jenis</div></td>
                                          <td><div align="center">$listpenghutang.nokpbaru</div></td>
                                          <td><div align="center">$listpenghutang.jumlah</div></td>
                                          <td><div align="center">$listpenghutang.catatan</div></td>
                                            </tr>
                                    
                                    #end
                                    #end
                                  </table>                                  </td>
                                </tr>
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                 
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                </table></td>
                            </tr>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>
                  


		<div class="TabbedPanelsContent">
                    <div id="TabbedPanels4" class="TabbedPanels">
                            <ul class="TabbedPanelsTabGroup">
                              <li class="TabbedPanelsTab style7" tabindex="1" onclick="setSelected(1,0,0,0);HtaamView()">HARTA TAK ALIH (ADA HAKMILIK)</li>
                              <li class="TabbedPanelsTab style7" tabindex="" onclick="setSelected(1,0,0,1);HtaamViewX()">HARTA TAK ALIH (TIADA HAKMILIK)</li>
                            </ul>
                            <div class="TabbedPanelsContentGroup">
                              <div class="TabbedPanelsContent">
                           
                                <table width="100%" border="1" align="center">
                                  <tr>
                                    <td>
                                   
                                    #if($show_htaa_add_table=="yes")
                                    
                                    <table width="100%" border="0">
                                      <tr>
                                        <td width="22%" class="style38"><div align="right">Negeri :</div></td>
                                        <td width="25%">
                                          #foreach($listnegpomo in $listnegeri)
                                          
                                          #if($negeri==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($negeri!="")
                                          <select name="socNegeriHtaam" class="mediumselect" $readmodenegeri onchange="setSelected(1,0,0,0);negerichange()">
                                          <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            </select>
                                            #else
                                          <select name="socNegeriHtaam" class="mediumselect" $readmodenegeri onchange="setSelected(1,0,0,0);negerichange()">
                                          <option value=""> &lt; Sila Pilih &gt; </option>
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            </select>                                          
                                          #end                                        </td>
                                        <td width="23%" class="style38"><div align="right">Kategory Tanah :</div></td>
                                        <td width="30%">#foreach($listkate in $listkategori)
                                          
                                          #if($kategori==$listkate.id)
                                          
                                          #set($listkategoriK=$listkate.kod)
                                          #set($listkategoriN=$listkate.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($kategori!="")
                                          <select name="socKategoriTanahHtaam" class="mediumselect" $readmode id="socKategoriTanahHtaam">
                                            <option value="$kategori">$listkategoriK - $listkategoriN</option>
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          </select>
#else
<select name="socKategoriTanahHtaam" class="mediumselect" $readmode id="socKategoriTanahHtaam">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




</select>
#end </td>
                                      </tr>
                                      <tr>
                                        <td class="style38" ><div align="right">Daerah :</div></td>
                                        <td>#foreach($listdaerah in $listDaerahbyNegeri)
                                          
                                          #if($daerah==$listdaerah.id)
                                          
                                          #set($listDaerahbyNegeriK=$listdaerah.kod)
                                          #set($listDaerahbyNegeriN=$listdaerah.nama)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($daerah!="")
                                          <select name="socDaerahHtaam" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchange()">
                                            <option value="$daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          </select>
#else
<select name="socDaerahHtaam" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchange()">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
</select>
#end </td>
                                        <td class="style38"><div align="right">Jenis Luas : </div></td>
                                        <td>
                                        #foreach($listluashta in $listluas)
                                          
                                          #if($jenisluas==$listluashta.id)
                                          
                                          #set($listluasK=$listluashta.kod)
                                          #set($listluasN=$listluashta.nama)
                                          #end 
                                          #end
                                          #if($jenisluas!="")
                                          <select name="socJenisLuasHtaam" class="mediumselect" $readmode id="socJenisLuasHtaam">
                                            <option value="$jenisluas">$listluasK - $listluasN</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          </select>
                                          
                                          
                                          <input name="txtLuasAsalHtaam" type="text" id="txtLuasAsalHtaam" value="$luasasal" size="34" />
                                          
                                          
#else
<select name="socJenisLuasHtaam" class="mediumselect" $readmode id="socJenisLuasHtaam">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


</select>

<input name="txtLuasAsalHtaam" type="text" id="txtLuasAsalHtaamUp" value="$luasasal" size="34" />
#end
 </td>
                                      </tr>
                                      <tr>
                                        <td class="style38" ><div align="right">Mukim :</div></td>
                                        <td>#foreach($listmukim in $listMukimbyDaerah)
                                          
                                          #if($mukim==$listmukim.id)
                                          
                                          #set($listMukimbyDaerahK=$listmukim.kod)
                                          #set($listMukimbyDaerahN=$listmukim.nama)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($mukim!="")
                                          <select name="socMukimHtaam" class="mediumselect" $readmodemukim id="socMukimHtaam">
                                            <option value="$mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                            
                                            
                                            
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($mukim!=$listmukim.id)
                                    
	                               
                                              
                                            
                                            
                                            <option value="$listmukim.id">$listmukim.kod - $listdaerah.nama</option>
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          </select>
#else
<select name="socMukimHtaam" class="mediumselect" $readmodemukim>
  <option value=""> &lt; Sila Pilih &gt; </option>
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

</select>
#end </td>
                                        <td class="style38"><div align="right">Luas (Hektar/MP) :</div></td>
                                        <td><label>
                                          <input name="txtLuasHMpHtaam" type="text" id="txtLuasHMpHtaamUp" value="$luashmp" size="34" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38" ><div align="right">Jenis Hakmilik :</div></td>
                                        <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                                          
                                          #if($jenishakmilik==$listjenishakmilik.id)
                                          
                                          #set($listjenishakmilikK=$listjenishakmilik.kod)
                                          #set($listjenishakmilikN=$listjenishakmilik.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($jenishakmilik!="")
                                          <select name="socJenisHakmilikHtaam" class="mediumselect" $readmode id="socJenisHakmilikHtaam">
                                          <option value="$jenishakmilik">$listjenishakmilikK - $listjenishakmiliN</option>
                                            
                                            
                                            
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                  
                                  #if($jenishakmilik!=$listjenishakmilik.id)
                                    
	                               
                                              
                                            
                                            
                                            <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          </select>
#else
<select name="socJenisHakmilikHtaam" class="mediumselect" $readmode id="socJenisHakmilikHtaam">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
  
  
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                 
                                
	                          <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
                                                 
                                              
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


</select>
#end </td>
                                        <td class="style38"><div align="right">Nilai Tarikh Mati :</div></td>
                                        <td><label>
                                        <input name="txtNilaiTarikhMatiHtaam" type="text" id="txtNilaiTarikhMatiHtaam" value="$nilai_Hta_mati" size="34" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">No Hak Milik :</div></td>
                                        <td><label>
                                          <input type="text" name="txtNoHakmilikHtaam" id="txtNoHakmilikHtaam" value="$noHakmilik"/>
                                        </label></td>
                                        <td class="style38"><div align="right">Nilai Tarikh Permohonan :</div></td>
                                        <td><label>
                                        <input name="txtNilaiTarikhMohonHtaa" type="text" id="txtNilaiTarikhMohonHtaa" value="$nilai_Hta_memohon" size="34" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">No PT / No Lot :</div></td>
                                        <td><label>
                                          <input name="txtNoPTHtaam" type="text" id="txtNoPTHtaam" value="$nopt" />
                                        </label></td>
                                        <td class="style38"><div align="right">Status Pemilikan :</div></td>
                                        <td>#foreach($listpemilik in $listpemilikan)
                                          
                                          #if($pemilikan==$listpemilik.id)
                                          
                                          #set($listpemilikK=$listpemilik.kod)
                                          #set($listpemilikN=$listpemilik.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($pemilikan!="")
                                          <select name="socStatusPemilikanHtaam" class="mediumselect" $readmode id="socStatusPemilikanHtaam">
                                          <option value="$pemilikan">$listpemilikK - $listpemilikN</option>
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                  #if($pemilikan!=$listpemilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          </select>
                                          #else
  <select name="socStatusPemilikanHtaam" class="mediumselect" $readmode id="socStatusPemilikanHtaamUp">
    <option value=""> &lt; Sila Pilih &gt; </option>
    
  
  
  
  
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                
	                               
                                              
  
  
  
  
    <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
    
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




  </select>
                                          #end </td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">Bahagian Simati :</div></td>
                                        <td>
                                          <input name="txtBahagianSimati1" type="text" id="txtBahagianSimati1" value="$basimati" size="5" />
                                        /
                                        <input name="txtBahagianSimati2" type="text" id="txtBahagianSimati2" value="$bbsimati" size="5" />                                        </td>
                                        <td class="style38"><div align="right">Tanggungan :</div></td>
                                        <td><input name="txtTanggunganHtaam" type="text" id="txtTanggunganHtaam" value="$tanggungan" size="15" /></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">No Pajakan :</div></td>
                                        <td><label>
                                          <input name="txtNoPajakan" type="text" id="txtNoPajakan" value="$nopajakan" />
                                        </label></td>
                                        <td class="style38"><div align="right">Jenis Tanah :</div></td>
                                        <td><label>#foreach($listtan in $listtanah)
                                          
                                          #if($jenistanah==$listtan.id)
                                          
                                          #set($listtanK=$listtan.kod)
                                          #set($listtanN=$listtan.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($jenistanah!="")
                                          <select name="socJenisTanahHtaam" class="mediumselect" $readmode id="socJenisTanahHtaam">
                                      <option value="$jenistanah">$listtanK - $listtanN</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                  #if($jenistanah!=$listtan.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          </select>
                                          #else
  <select name="socJenisTanahHtaam" class="mediumselect" $readmode id="socJenisTanahHtaam">
  <option value=""> &lt; Sila Pilih &gt; </option>
    
  
  
  
  
  
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                
	                               
                                              
  
  
  
  
  
    <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
    
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            





  </select>
                                        #end </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38" valign="top"><div align="right">No Pers :</div></td>
                                        <td valign="top"><label>
                                          <input name="txtNoPersHtaam" type="text" id="txtNoPersHtaam" value="$noperserahan" />
                                        </label></td>
                                        <td class="style38" valign="top"><div align="right">Catatan :</div></td>
                                        <td><textarea name="txtCatatanHtaam" id="txtCatatanHtaam" cols="25" rows="5">$catatan</textarea></td>
                                      </tr>
                                    </table>
                                    
                                    #end</td>
                                  </tr>
                                  
                                  
                                  #foreach($listamid in $listHTAid)

                                  <tr>
                                    <td>
                                    
                                  
                    

                    #if($show_htaa_update_table=="yes")
                                    <table width="100%" border="0">
                                    
                                      <tr>
                                      <input name="id_htaam" value="$listamid.idhta" type="hidden" />
                                        <td width="22%" class="style38"><div align="right">Negeri :</div></td>
                                        <td width="25%"> #foreach($listnegpomo in $listnegeri)
                                          
                                          #if($listamid.negeri==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listamid.negeri!="")
                                          <select name="socNegeriHtaamUp" class="mediumselect" $readmodenegeri  onchange="negerichangeup()"  >
                                                  <option value="$listamid.negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listamid.negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          </select>
                                          #else
                                          <select name="socNegeriHtaamUp" class="mediumselect" $readmodenegeri onchange="negerichangeup()" >
                                            <option value=""> &lt; Sila Pilih &gt; </option>
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
                                            <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                            
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          </select>
                                          #end </td>
                                        <td width="23%" class="style38"><div align="right">Kategory Tanah :</div></td>
                                        <td width="30%">#foreach($listkate in $listkategori)
                                          
                                          #if($listamid.kategori==$listkate.id)
                                          
                                          #set($listkategoriK=$listkate.kod)
                                          #set($listkategoriN=$listkate.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listamid.kategori!="")
                                          <select name="socKategoriTanahHtaamUp" class="mediumselect" $readmode id="socKategoriTanahHtaam2">
                                            <option value="$listamid.kategori">$listkategoriK - $listkategoriN</option>
                                            
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($listamid.kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            
                                            <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          
                                          </select>
#else
<select name="socKategoriTanahHtaam" class="mediumselect" $readmode id="socKategoriTanahHtaam2">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
                                            
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
                                            
  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
  
                                            
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




                                          
</select>
#end </td>
                                      </tr>
                                      <tr>
                                        <td class="style38" ><div align="right">Daerah :</div></td>
                                        <td>#foreach($listdaerah in $listdaerah)
                                          
                                          #if($listamid.daerah==$listdaerah.id)
                                          
                                          #set($listDaerahbyNegeriK=$listdaerah.kod)
                                          #set($listDaerahbyNegeriN=$listdaerah.nama)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listamid.daerah!="")
                                          <select name="socDaerahHtaamUp" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchange()" id="socDaerahHtaam">
                                            <option value="$listamid.daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                            
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($listamid.daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                            <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          </select>
                                          #else
                                          <select name="socDaerahHtaamUp" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchangeup()">
                                            <option value=""> &lt; Sila Pilih &gt; </option>
                                            
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
                                            <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                            
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

                                          </select>
                                          #end </td>
                                          
                                          
                                        <td class="style38"><div align="right">Jenis Luas :</div></td>
                                        <td>#foreach($listluashta in $listluas)
                                          
                                          #if($listamid.jenisluas==$listluashta.id)
                                          
                                          #set($listluasK=$listluashta.kod)
                                          #set($listluasN=$listluashta.nama)
                                          #end 
                                          #end
                                          #if($listamid.jenisluas!="")
                                          <select name="socJenisLuasHtaamUpd" class="mediumselect" $readmode >
                                            <option value="$listamid.jenisluas">$listluasK - $listluasN</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($listamid.jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                                          </select>
                                          <input name="txtLuasAsalHtaamUpd" type="text" id="txtLuasAsalHtaamUpd" value="$listamid.luasasal" size="34"  $readmode />
#else
<select name="socJenisLuasHtaamUpd" class="mediumselect" $readmode id="socJenisLuasHtaamUpd">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
                                            
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
                                            
  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
  
                                            
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                          
</select>
<input name="txtLuasAsalHtaamUpd" type="text" value="$listamid.luasasal" size="34"  $readmode />
#end </td>
                                      </tr>
                                      
                                      
                                      
                                      <tr>
                                        <td class="style38" ><div align="right">Mukim :</div></td>
                                        <td>#foreach($listmukim in $listmukim)

                                          
                                          #if($listamid.mukim==$listmukim.id)
                                          
                                          #set($listMukimbyDaerahK=$listmukim.kod)
                                          #set($listMukimbyDaerahN=$listmukim.nama)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listamid.mukim!="")
                                          <select name="socMukimHtaamUp" class="mediumselect" $readmodemukim id="socMukimHtaam2">
                                            <option value="$listamid.mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listmukim in $listmukim)
                                 
                                  #if($listamid.mukim!=$listmukim.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            <option value="$listmukim.id">$listmukim.kod - $listdaerah.nama</option>
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          </select>
                                          #else
                                          <select name="socMukimHtaamUp" class="mediumselect" $readmodemukim id="socMukimHtaamUp">
                                            <option value=""> &lt; Sila Pilih &gt; </option>
                                            
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  
                                            <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
                                            
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                          </select>
                                          #end </td>
                                        <td class="style38"><div align="right">Luas (Hektar/MP) :</div></td>
                                        <td><label>
                                          <input name="txtLuasHMpHtaamUpd" type="text" id="txtLuasHMpHtaam2" value="$listamid.luashmp" size="15" $readmode />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38" ><div align="right">Jenis Hakmilik :</div></td>
                                        <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                                          
                                          #if($listamid.jenishakmilik==$listjenishakmilik.id)
                                          
                                          #set($listjenishakmilikK=$listjenishakmilik.kod)
                                          #set($listjenishakmilikN=$listjenishakmilik.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listamid.jenishakmilik!="")
                                          <select name="socJenisHakmilikHtaamUp" class="mediumselect" $readmode id="socJenisHakmilikHtaam2">
                                            <option value="$listamid.jenishakmilik">$listjenishakmilikK - $listjenishakmilikN</option>
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                  
                                  #if($listamid.jenishakmilik!=$listjenishakmilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          </select>
                                          #else
                                          <select name="socJenisHakmilikHtaam" class="mediumselect" $readmode id="socJenisHakmilikHtaam2">
                                            <option value=""> &lt; Sila Pilih &gt; </option>
                                            
  
  
  
                                              
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                 
                                
	                          
                                            <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $listjenishakmilik.keterangan</option>
                                            
                                                 
                                              
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



                                          </select>
                                          #end </td>
                                        <td class="style38"><div align="right">Nilai Tarikh Mati :</div></td>
                                        <td><label>
                                          <input name="txtNilaiTarikhMatiHtaamUpd" type="text" id="txtNilaiTarikhMatiHtaam2" value="$listamid.nilai_Hta_mati" size="34" $readmode />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">No Hak Milik :</div></td>
                                        <td><label>
                                          <input name="txtNoHakmilikHtaamUp" type="text" id="txtNoHakmilikHtaam2" value="$listamid.noHakmilik" size="34" $readmode/>
                                        </label></td>
                                        <td class="style38"><div align="right">Nilai Tarikh Permohonan :</div></td>
                                        <td><label>
                                          <input name="txtNilaiTarikhMohonHt" type="text" value="$listamid.nilai_Hta_memohon" size="34"  $readmode />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">No PT / No Lot :</div></td>
                                        <td><label>
                                          <input name="txtNoPTHtaamUp" type="text" id="txtNoPTHtaamUp" value="$listamid.nopt" size="34" $readmode />
                                        </label></td>
                                        <td class="style38"><div align="right">Status Pemilikan :</div></td>
                                        <td>#foreach($listpemilik in $listpemilikan)
                                          
                                          #if($listamid.pemilikan==$listpemilik.id)
                                          
                                          #set($listpemilikK=$listpemilik.kod)
                                          #set($listpemilikN=$listpemilik.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listamid.pemilikan!="")
                                          <select name="socStatusPemilikanHtaamUpd" class="mediumselect" $readmode id="socStatusPemilikanHtaam2">
                                                  <option value="$listamid.pemilikan">$listpemilikK - $listpemilikN</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                  #if($listamid.pemilikan!=$listpemilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          </select>
                                          #else
                                          <select name="socStatusPemilikanHtaamUpd" class="mediumselect" $readmode id="socStatusPemilikanHtaamUpd">
                                            <option value=""> &lt; Sila Pilih &gt; </option>
                                            
    
  
  
  
  
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                
	                               
                                              
  
  
  
  
    
                                            <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                            
    
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




  
                                          </select>
                                          #end </td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">Bahagian Simati :</div></td>
                                        <td><input name="txtBahagianSimati1Up" type="text" id="txtBahagianSimati3" $readmode value="$listamid.basimati" size="5" />
                                          /
                                          <input name="txtBahagianSimati2Up" type="text" id="txtBahagianSimati4" $readmode value="$listamid.bbsimati" size="5" />                                        </td>
                                        <td class="style38"><div align="right">Tanggungan :</div></td>
                                        <td><input name="txtTanggunganHtaamUp" type="text" id="txtTanggunganHtaam2" value="$listamid.tanggungan" size="15" $readmode /></td>
                                      </tr>
                                      <tr>
                                        <td class="style38"><div align="right">No Pajakan :</div></td>
                                        <td><label>
                                          <input name="txtNoPajakanUp" type="text" id="txtNoPajakan2" value="$listamid.nopajakan" size="34" $readmode />
                                        </label></td>
                                        <td class="style38"><div align="right">Jenis Tanah :</div></td>
                                        <td><label>#foreach($listtan in $listtanah)
                                          
                                          #if($listamid.jenistanah==$listtan.id)
                                          
                                          #set($listtanK=$listtan.kod)
                                          #set($listtanN=$listtan.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listamid.jenistanah!="")
                                          <select name="socJenisTanahHtaamUpd" class="mediumselect" $readmode id="socJenisTanahHtaam2">
                                                  <option value="$listamid.jenistanah">$listtanK - $listtanN</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                  #if($listamid.jenistanah!=$listtan.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          </select>
                                          #else
                                          <select name="socJenisTanahHtaamUpd" class="mediumselect" $readmode id="socJenisTanahHtaam2">
                                            <option value=""> &lt; Sila Pilih &gt; </option>
                                            
    
  
  
  
  
  
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                
	                               
                                              
  
  
  
  
  
    
                                            <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
                                            
    
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            





  
                                          </select>
                                          #end </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38" valign="top"><div align="right">No Pers :</div></td>
                                        <td valign="top"><label>
                                          <input name="txtNoPersHtaamUp" type="text" id="txtNoPersHtaam2" value="$listamid.noperserahan" size="34" $readmode />
                                        </label></td>
                                        <td class="style38" valign="top"><div align="right">Catatan :</div></td>
                                        <td>
                                        <textarea name="txtCatatanHt" id="txtCatatanHt" value="$listamid.catatan" cols="25" rows="5">$listamid.catatan</textarea>                                        </td>
                                      </tr>
                                    </table>
                                    #end
                                    </td>
                                  </tr>
                                  
                                  #end
                                  <tr>
                                    <td><table width="100%" border="0" align="center">
                                      <tr>
                                      
                                      <!--
                                      
                                      this.context.put("show_simpan_add_htaam","yes");
				    this.context.put("show_batal_add_htaam","yes");
		            this.context.put("show_kemaskini_htaam","");
				    this.context.put("show_save_update_htaam","");
				    this.context.put("show_batal_update_htaam","");
				    this.context.put("show_hapus_htaam","yes");
				    
				    this.context.put("show_kembali_htaam","yes");
				    
				    this.context.put("show_htaa_update_table","");
				    this.context.put("show_htaa_add_table","yes");	  
                    -->
                    
                                        <td align="center">
                                        
                                        #if($show_simpan_add_htaam=="yes")
                                          <input type="button" name="tambahpenghutang2" id="tambahpenghutang2"$readmode value="Simpan" onkeypress="setSelected(1,0,0,0);add_Htaam()" onclick="setSelected(1,0,0,0);add_Htaam()"/>
                                        #end
                                        
                                        #if($show_batal_add_htaam=="yes")
                                          <input type="reset" name="cmdSimpan6" id="cmdSimpan6" value="Batal" onkeypress="addcancelhtaam()" onclick="addcancelhtaam()"/>
                                       
                                        #end
                                         #if($show_kemaskini_htaam=="yes")
                                            <input type="submit" name="button8" id="button8" value="Kemaskini" onkeypress="setSelected(1,0,0,0);edit_Htaam()" onclick="setSelected(1,0,0,0);edit_Htaam()" />
                                            #end
                                         #if($show_save_update_htaam=="yes")
                                            <input type="submit" name="button9" id="button9" value="Simpan" onkeypress="setSelected(1,0,0,0);save_Htaam()" onclick="setSelected(1,0,0,0);save_Htaam()"/>
                                       #end
                                        #if($show_batal_update_htaam=="yes")
                                          <input type="submit" name="button10" id="button10" value="Batal" onkeypress="setSelected(1,0,0,0);batal_Htaam()" onclick="setSelected(1,0,0,0);batal_Htaam()" />
                                       #end
                                       #if($show_hapus_htaam=="yes")
                                          <input type="submit" name="cmdCetak7" id="cmdCetak7" value="Hapus" setSelected(1,0,0,0);hapus_Htaam()" onclick="setSelected(1,0,0,0);hapus_Htaam()"/>
                                     #end
                                      #if($show_kembali_htaam=="yes")
                                     
                                          <input type="submit" name="cmdKembali7" id="cmdKembali7" value="Kembali" onkeypress="kembali_Htaam()" onclick="kembali_Htaam()" />
                                          #end
                                        </td>
                                      </tr>
                                    </table></td>
                                  </tr>
                                  
                                  
                                  
                                  
                                  
                                  
                                  <tr>
                                    <td><table width="100%">
                                    
                                    
                                    
                                    
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <td><div align="center">ID HTA</div></td>
                                        <td><div align="center">NEGERI</div></td>
                                        <td><div align="center">DAERAH</div></td>
                                        <td><div align="center">MUKIM</div></td>
                                        <td><div align="center">NO HAK MILIK</div></td>
                                        <td><div align="center">NO PT/NO LOT</div></td>
                                        <td><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                      
                                      
                                      
                                      #set($plko=0)
                                      
                                      #foreach($listam in $listHTA)
                                      #set($plko=$plko+1)
                                      #if($plko%2!=0)
                                      
  <tr bgcolor="white">
    <td><div align="center">$plko</div></td>
    <td><a href="javascript:get_htaam($listam.idhta)">$listam.idhta </a></td>
    #if($listam.negeri=="")
    
    	#set($nama_negeri="")
    #else
    	#foreach($ln in $listnegeri)                                                                      
    		#if($ln.id_Negeri==$listam.negeri)
    			#set($nama_negeri=$ln.nama_Negeri)
    
    		#end
    	#end
    
    #end
    <td>$nama_negeri</td>
    #if($listam.daerah=="")
    
    #set($nama_daerah="")
    #else
    #foreach($ld in $listdaerah)                                                                      
    #if($ld.id==$listam.daerah)
    #set($nama_daerah=$ld.nama)
    
    #end
    #end
    
    #end
    <td>$nama_daerah</td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td>$nama_mukim</td>
    <td>$listam.noHakmilik</td>
    <td>$listam.nopt</td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td>$listam.basimati / $listam.bbsimati</td>
    #else
    <td></td>
    #end </tr>
                                      #else
  <tr class="table_header">
    <td><div align="center">$plko</div></td>
    <td><a href="javascript:get_htaam($listam.idhta)">$listam.idhta</a></td>
    #if($listam.negeri=="")
    
    #set($nama_negeri="")
    #else
    #foreach($ln in $listnegeri)                                                                      
    #if($ln.id_Negeri==$listam.negeri)
    #set($nama_negeri=$ln.nama_Negeri)
    
    #end
    #end
    
    #end
    <td>$nama_negeri</td>
    #if($listam.daerah=="")
    
    #set($nama_daerah="")
    #else
    #foreach($ld in $listdaerah)                                                                      
    #if($ld.id==$listam.daerah)
    #set($nama_daerah=$ld.nama)
    
    #end
    #end
    
    #end
    <td>$nama_daerah</td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td>$nama_mukim</td>
    <td>$listam.noHakmilik</td>
    <td>$listam.nopt</td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td>$listam.basimati / $listam.bbsimati</td>
    #else
    <td></td>
    #end </tr>
                                      #end
                                      #end
                                    </table></td>
                                    <input type="hidden" name="idhtaam" value="$listamid.idhta" />
                                    <input type="hidden" name="idhtaamid" value="$idhtaam" />
                                  </tr>
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                               <!--   
                                  <tr>
                                    <td>
                                 
                                    <table width="100%" border="1">
                                      <tr>
                                        <td><div align="center">ID HTA</div></td>
                                        <td> <div align="center">NEGERI</div></td>
                                        <td><div align="center">DAERAH</div></td>
                                        <td><div align="center">MUKIM</div></td>
                                        <td><div align="center">NO HAK MILIK</div></td>
                                        <td><div align="center">NO PT/NO LOT</div></td>
                                        <td><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                           #foreach($listam in $listHTA)
                                      <tr>
                                      
                            
                                      
                                      
                                        <td><a href="javascript:get_htaam($listam.idhta)">$listam.idhta</td>
                                        
                                        
                                        
                                         #if($listam.negeri=="")
                                        
                                        #set($nama_negeri="")
                                        #else
                                        #foreach($ln in $listnegeri)                                                                      
                                        #if($ln.id_Negeri==$listam.negeri)
                                        #set($nama_negeri=$ln.nama_Negeri)
                                        
                                        #end
                                        #end
                                        
                                        #end
                                        
                                        
                                        <td>$nama_negeri</td>
                                        
                                        
                                        
                                         #if($listam.daerah=="")
                                        
                                        #set($nama_daerah="")
                                        #else
                                        #foreach($ld in $listdaerah)                                                                      
                                        #if($ld.id==$listam.daerah)
                                        #set($nama_daerah=$ld.nama)
                                        
                                        #end
                                        #end
                                        
                                        #end
                                   
                                        <td>$nama_daerah</td> 
                                        
                                        
                                        
                                        
                                        #if($listam.mukim=="")
                                        
                                        #set($nama_mukim="")
                                        #else
                                        #foreach($lm in $listmukim)                                                                      
                                        #if($lm.id==$listam.mukim)
                                        #set($nama_mukim=$lm.nama)
                                        
                                        #end
                                        #end
                                        
                                        #end
                                        <td>$nama_mukim</td>
                                        
                                        <td>$listam.noHakmilik</td>
                                        <td>$listam.nopt</td>
                                        
                                        
                                        
                                                                        #if($listam.basimati!="" && $listam.bbsimati!="")
                                        <td>$listam.basimati / $listam.bbsimati</td>
                                        #else
                                         <td></td>
                                        #end      </tr>
                                      #end
                                      
                                       
                                      
                                    </table>
                                    
                                    
                                    </td>
                                    <input type="hidden" name="idhtaam" value="$listamid.idhta" />
                                    <input type="hidden" name="idhtaamid" value="$idhtaam" />
                                  </tr>
                                  
                                  
                                  
                                  -->
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                  
                                </table>
                </div>
                              <div class="TabbedPanelsContent">
                                <CENTER>
                                </CENTER>
                               
                                
                             
                                <table width="100%" height="359" border="1">
                                  #if($show_htaa_add_table=="yes")
                                  <tr>
                                    <td><table width="100%" border="0">
                                        <tr>
                                                                                   
                                            <td>
                                            <table width="70%" align="center">
                                              <tr>
                                                <td><span class="style36">
                                                  <input type="radio" name="radioHtaamViewX1"  $checked1 onchange="setSelected(1,0,0,1);HtaamViewX1()"/>
                                                  Perjanjian Jual Beli</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                        <input type="radio" name="radioHtaamViewX2"  $checked2 onchange="setSelected(1,0,0,1);HtaamViewX2()"/>
                                                  Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                              <input type="radio" name="radioHtaamViewX3" $checked3 onchange="setSelected(1,0,0,1);HtaamViewX3()"/>
                                                  Kepentingan Lain- lain</span></td>
                                              </tr>
                                            </table>                                      </td>
                                        </tr>
                                    </table></td>
                                  </tr>
                                  
                                  
                                  
                                  <tr>
                                    <td>
                                    
                                    <table width="100%" border="0">
                                      <tr>
                                        <td width="50%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td width="30%"><div align="right"><span class="style43">*</span>Negeri :</div></td>
                                              <td width="70%">#foreach($listnegpomo in $listnegeri)
                                                
                                                #if($negeri==$listnegpomo.id_Negeri)
                                                
                                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($negeri!="")
                                                <select name="socNegeriHtaamX" class="mediumselect" $readmodenegeri onchange="setSelected(1,0,0,1);negerichangeX()">
                                                        <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                                  
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                </select>
                                                #else
                                                <select name="socNegeriHtaamX" class="mediumselect" $readmodenegeri onchange="setSelected(1,0,0,1);negerichangeX()">
                                                  <option value=""> &lt; Sila Pilih &gt; </option>
                                                  
  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
  
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><div align="right"><span class="style43">*</span>Daerah :</div></td>
                                              <td>#foreach($listdaerah in $listDaerahbyNegeri)
                                                
                                                #if($daerah==$listdaerah.id)
                                                
                                                #set($listDaerahbyNegeriK=$listdaerah.kod)
                                                #set($listDaerahbyNegeriN=$listdaerah.nama)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($daerah!="")
                                                <select name="socDaerahHtaamX" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchangeX()">
                                                        <option value="$daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                                  
                                            
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                            
                                                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                                  
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socDaerahHtaamX" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchangeX()">
                                                  <option value=""> &lt; Sila Pilih &gt; </option>
                                                  
  
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
  
                                                  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                                  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><div align="right"><span class="style43">*</span>Mukim :</div></td>
                                              <td>#foreach($listmukim in $listMukimbyDaerah)
                                                
                                                #if($mukim==$listmukim.id)
                                                
                                                #set($listMukimbyDaerahK=$listmukim.kod)
                                                #set($listMukimbyDaerahN=$listmukim.nama)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($mukim!="")
                                                <select name="socMukimHtaamX" class="mediumselect" $readmodemukim id="socMukimHtaamX">
                                                  <option value="$mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                                  
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($mukim!=$listmukim.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                                  <option value="$listmukim.id">$listmukim.kod - $listdaerah.nama</option>
                                                  
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socMukimHtaamX" class="mediumselect" $readmodemukim id="socMukimHtaamX">
                                                  <option value=""> &lt; Sila Pilih &gt; </option>
                                                  
  
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  
  
                                                  <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
                                                  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Nama Pemaju :</div></td>
                                              <td><label>
                                                <input name="txtNamaPemajuHtaamX" type="text" id="txtNamaPemajuHtaamX" value="$namapemaju" size="34" $readmode />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Alamat Pemaju :</div></td>
                                              <td><label>
                                                <input name="txtAlamatPemaju1HtaamX" type="text" id="textfield20" value="$alamatpemaju1" size="34" $readmode />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju2HtaamX" type="text" id="textfield53" value="$alamatpemaju2" size="34" $readmode />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju3HtaamX" type="text" id="textfield54" value="$alamatpemaju3" size="34" $readmode />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Poskod :</div></td>
                                              <td><input name="txtPoskodPemaju1HtaamX" type="text" id="textfield55" value="$poskodpemaju" maxlength="5" $readmode /></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Bandar :</div></td>
                                              <td><input name="txtBandarPemaju1HtaamX" type="text" id="textfield56" value="$bandarpemaju" size="34" $readmode /></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Negeri :</div></td>
                                              <td>#foreach($listnegpomo in $listnegeri)
                                                
                                                #if($negeripemaju==$listnegpomo.id_Negeri)
                                                
                                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($negeripemaju!="")
                                                <select name="socNegeriPemajuHtaamX" class="mediumselect" $readmode id="socNegeriPemajuHtaamX" >
                                                  <option value="$negeripemaju">$negerikodpemoP - $negeriketeranganpemoP</option>
                                                  
                                            
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeripemaju!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            
                                            
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socNegeriPemajuHtaamX" class="mediumselect" $readmode id="socNegeriPemajuHtaamX" >
                                                  <option value=""> &lt; Sila Pilih &gt; </option>
                                                  
  
  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
  
  
                                                  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                                  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


                                                </select>
                                                #end</td>
                                            </tr>
                                          #if($radio2=="yes")
                                          <tr>
                                            <td><div align="right">Alamat Harta :</div></td>
                                            <td><input name="txtAlamatHarta1HtaamX" type="text" id="textfield57" value="$alamathta1" size="34" $readmode /></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td><input name="txtAlamatHarta2HtaamX" type="text" id="textfield58" value="$alamathta2" size="34" $readmodevalue="$alamathta2" /></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td><input name="txtAlamatHarta3HtaamX" type="text" id="textfield59" value="$alamathta3" size="34" $readmode /></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right">Poskod :</div></td>
                                            <td><input name="txtPoskodHartaHtaamX" type="text" id="textfield60" $readmode value="$poskodhta" maxlength="5" /></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right">Bandar :</div></td>
                                            <td><input name="txtBandarHartaHtaamX" type="text" id="textfield61" value="$bandarhta" size="34" $readmode /></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right">No Perjanjian :</div></td>
                                            <td><input name="txtNoPerjanjianHtaamX" type="text" id="textfield62" value="$noperjanjian" size="34" $readmode /></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right">Tarikh Perjanjian :</div></td>
                                            <td><span class="style36">
                                              <input name="txtTarikhPerjanjianHtaamX" id="txdTarikhMatiWaris3" type="text" $readmode value="$tarikhperjanjian" size="15"  />
                                              <a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                                            </td>
                                          </tr>
                                          #end
                                          
                                          #if($radio3=="yes")
                                          <tr>
                                                        <td><div align="right">Nama Rancangan :</div></td>
                                            <td><label>
                                                          <input name="txtNamaRancanganHtaamX" type="text" id="textfield63" value="$namarancangan" size="34" $readmodevalue="$namarancangan" />
                                                        </label></td>
                                          </tr>
                                                      <tr>
                                                        <td><div align="right">No ROH :</div></td>
                                                        <td><input name="textfield" type="text" id="textfield64" value="noroh" $readmodevalue="$noroh" /></td>
                                                      </tr>
                                                      <tr>
                                                        <td><div align="right">Lot ID :</div></td>
                                                        <td><input name="textfield" type="text" id="textfield65" $readmode value="$nolot" /></td>
                                                      </tr>
                                          #end
                                        </table></td>
                                        <td width="50%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td width="30%"><div align="right">Bahagian Simati :</div></td>
                                              <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati7" $readmode value="$basimati" size="5" />
                                                /
                                                <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati8" value="$bb" size="5" $readmodevalue="$bbsimati" /></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Kategory Tanah :</div></td>
                                              <td>#foreach($listkate in $listkategori)
                                                
                                                #if($kategori==$listkate.id)
                                                
                                                #set($listkategoriK=$listkate.kod)
                                                #set($listkategoriN=$listkate.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($kategori!="")
                                                <select name="socKategoriTanahHtaamX" class="mediumselect" $readmode id="socKategoriTanahHtaamX">
                                                  <option value="$kategori">$listkategoriK - $listkategoriN</option>
                                                  
                                            
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socKategoriTanahHtaamX" class="mediumselect" $readmode id="socKategoriTanahHtaamX">
                                                  <option value=""> &lt; Sila Pilih &gt; </option>
                                                  
  
  
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
  
  
                                                  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                                  
  
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            






                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Jenis Luas : </div></td>
                                              <td>#foreach($listluashta in $listluas)
                                                
                                                #if($jenisluas==$listluashta.id)
                                                
                                                #set($listluasK=$listluashta.kod)
                                                #set($listluasN=$listluashta.nama)
                                                #end 
                                                #end
                                                #if($jenisluas!="")
                                                <select name="socJenisLuasHtaamX" class="mediumselect" $readmode id="socJenisLuasHtaamX">
                                                  <option value="$jenisluas">$listluasK - $listluasN</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                      <input type="text" name="txtLuasAsalHtaamX" id="txtLuasAsalHtaamUp5" value="$luasasal" />
                                                #else
                                                <select name="socJenisLuasHtaamX" class="mediumselect" $readmode id="socJenisLuasHtaamX">
                                                  <option value=""> &lt; Sila Pilih &gt; </option>
                                                  
  
  
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
  
  
                                                  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                                  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




                                                </select>
                                                <input type="text" name="txtLuasAsalHtaamX" id="txtLuasAsalHtaamUp6" $readmode value="$luasasal" />
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Luas (Hektar/MP) :</div></td>
                                              <td><input name="txtLuasHMpHtaamX" type="text" id="txtLuasHMpHtaamUp3" $readmode value="$luashmp" /></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Nilai Tarikh Mati :</div></td>
                                              <td><input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamUp3" $readmode value="$nilai_Hta_mati" /></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Nilai Tarikh Mohon :</div></td>
                                              <td><input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaa3" $readmode value="$nilai_Hta_memohon" /></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">No Cagaran :</div></td>
                                              <td><input name="txtNoCagaranX" type="text" id="txtNoPajakan5" value="$nocagaran" $readmode /></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">No Pajakan :</div></td>
                                              <td><input name="txtNoPajakanX" type="text" id="txtNoPajakan6" value="$nopajakan" $readmode /></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Status Pemilikan :</div></td>
                                              <td>#foreach($listpemilik in $listpemilikan)
                                                
                                                #if($pemilikan==$listpemilik.id)
                                                
                                                #set($listpemilikK=$listpemilik.kod)
                                                #set($listpemilikN=$listpemilik.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($pemilikan!="")
                                                <select name="socStatusPemilikanHtaamX" class="mediumselect" $readmode id="socStatusPemilikanHtaamX">
                                                  <option value="$pemilikan">$listpemilikK - $listpemilikN</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                  #if($pemilikan!=$listpemilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socStatusPemilikanHtaamX" class="mediumselect" $readmode id="socStatusPemilikanHtaamX">
                                                  <option value=""> &lt; Sila Pilih &gt; </option>
                                                  
  
    
  
  
  
  
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                
	                               
                                              
  
  
  
  
    
  
                                                  <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                                  
  
    
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




  

                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Tanggungan :</div></td>
                                              <td><input name="txtTanggunganHtaamX" type="text" id="txtTanggunganHtaam4" value="$tanggungan" /></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Jenis Tanah :</div></td>
                                              <td>#foreach($listtan in $listtanah)
                                                
                                                #if($jenistanah==$listtan.id)
                                                
                                                #set($listtanK=$listtan.kod)
                                                #set($listtanN=$listtan.keterangan)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($jenistanah!="")
                                                <select name="socJenisTanahHtaamX" class="mediumselect" $readmode id="socJenisTanahHtaamX">
                                                  <option value="$jenistanah">$listtanK - $listtanN</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                  #if($jenistanah!=$listtan.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                                  <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
                                                  
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          
                                                </select>
                                                #else
                                                <select name="socJenisTanahHtaamX" class="mediumselect" $readmode id="socJenisTanahHtaamX">
                                                  <option value=""> &lt; Sila Pilih &gt; </option>
                                                  
  
    
  
  
  
  
  
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                
	                               
                                              
  
  
  
  
  
    
  
                                                  <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
                                                  
  
    
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            





  

                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><div align="right">Catatan :</div></td>
                                              <td><textarea name="txtCatatanHtaamX" id="txtCatatanHtaamX" cols="25" rows="5">$catatan</textarea></td>
                                            </tr>
                                        </table></td>
                                      </tr>
                                    </table></td>
                                  </tr>
                                  #end
                                    #if($show_htaa_update_table=="yes")
                                      #foreach($listam in $listHTAXid)
                                   <tr>
                                   <td>
                                   
                               
                                     <table width="70%" align="center">
                                     <input type="hidden" name="flag" value="$listam.flag">
                                       #if($listam.flag=="1")
                                       <tr>
                                       
                                         <td><div align="center"><span class="style46">
                                           Perjanjian Jual Beli                                           </span></div></td>
                                       </tr>
                                       #end
                                       #if($listam.flag=="2")
                                       <tr>
                                         <td><div align="center"><span class="style46">Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</span></div></td>
                                       </tr>
                                       #end
                                       #if($listam.flag=="3")
                                       <tr>
                                         <td><div align="center"><span class="style46">
                                           Kepentingan Lain- lain                                           </span></div></td>
                                       </tr>
                                       #end
                                     </table></td>
                                  </tr>  
                                    
                                  <tr>
                                  
                                    <td><table width="100%">
                                        <tr>
                                          <td width="50%" valign="top"><table width="100%">
                                              <tr>
                                                <td width="30%"><div align="right"><span class="style43">*</span>Negeri :</div></td>
                                                <td width="70%">#foreach($listnegpomo in $listnegeri)
                                          
                                          #if($listam.negeri==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.negeri!="")
                                          <select name="socNegeriHtaamX" class="mediumselect" $readmodenegeri onchange="setSelected(1,0,0,1);negerichangeupX()">
                                            <option value="$listam.negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listam.negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          </select>
#else
<select name="socNegeriHtaamX" class="mediumselect" $readmodenegeri onchange="setSelected(1,0,0,1);negerichangeupX()">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
</select>
#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="right"><span class="style43">*</span>Daerah :</div></td>
                                                <td>#foreach($listdaerah in $listdaerah)
                                          
                                          #if($listam.daerah==$listdaerah.id)
                                          
                                          #set($listDaerahbyNegeriK=$listdaerah.kod)
                                          #set($listDaerahbyNegeriN=$listdaerah.nama)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.daerah!="")
                                          <select name="socDaerahHtaamX" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,1);daerahchangeupX()">
                                            <option value="$listam.daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                            
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($listam.daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                            <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          </select>
#else
<select name="socDaerahHtaamX" class="mediumselect" $readmodedaerah onchange="setSelected(1,0,0,0);daerahchangeupX()">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

</select>
#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="right"><span class="style43">*</span>Mukim :</div></td>
                                                <td>#foreach($listmukim in $listmukim)
                                          
                                          #if($listam.mukim==$listmukim.id)
                                          
                                          #set($listMukimbyDaerahK=$listmukim.kod)
                                          #set($listMukimbyDaerahN=$listmukim.nama)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.mukim!="")
                                          <select name="socMukimHtaamX" class="mediumselect" $readmodemukim id="socMukimHtaam3">
                                            <option value="$mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($listam.mukim!=$listmukim.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            <option value="$listmukim.id">$listmukim.kod - $listdaerah.nama</option>
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          </select>
#else
<select name="socMukimHtaamX" class="mediumselect" $readmodemukim>
  <option value=""> &lt; Sila Pilih &gt; </option>
  
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  
  <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


</select>
#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Nama Pemaju :</div></td>
                                                <td><label>
                                                  <input name="txtNamaPemajuHtaamX" type="text" id="txtNamaPemajuHtaamX" value="$listam.namapemaju" size="34" $readmode />
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Alamat Pemaju :</div></td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju1HtaamX" type="text" id="textfield21" value="$listam.alamatpemaju1" size="34" $readmode />
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju2HtaamX" type="text" id="textfield22" value="$listam.alamatpemaju2" size="34" $readmode />
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju3HtaamX" type="text" id="textfield23" value="$listam.alamatpemaju3" size="34" $readmode />
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Poskod :</div></td>
                                                <td><input name="txtPoskodPemaju1HtaamX" type="text" id="textfield24" value="$listam.poskodpemaju" maxlength="5"  $readmode /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Bandar :</div></td>
                                                <td><input name="txtBandarPemaju1HtaamX" type="text" id="textfield43" value="$listam.bandarpemaju" size="34" $readmode /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Negeri :</div></td>
                                                <td>#foreach($listnegpomo in $listnegeri)
                                          
                                          #if($listam.negeripemaju==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.negeripemaju!="")
                                          <select name="socNegeriPemajuHtaamX" class="mediumselect" $readmode id="socNegeriPemajuHtaamX" >
                                            <option value="$listam.negeripemaju">$negerikodpemoP - $negeriketeranganpemoP</option>
                                            
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listam.negeripemaju!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            
                                            <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          </select>
#else
<select name="socNegeriPemajuHtaamX" class="mediumselect" $readmode id="socNegeriPemajuHtaamX" >
  <option value=""> &lt; Sila Pilih &gt; </option>
  
  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
  
  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

</select>
#end</td>
                                              </tr>
                                              #if($listam.flag=="1")
                                              <tr>
                                                <td><div align="right">Alamat Harta :</div></td>
                                                <td><input name="txtAlamatHarta1HtaamX" type="text" id="textfield44" value="$listam.alamathta1" size="34" $readmode /></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><input name="txtAlamatHarta2HtaamX" type="text" id="textfield45" value="$listam.alamathta2" size="34" $readmode /></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><input name="txtAlamatHarta3HtaamX" type="text" id="textfield46" value="$listam.alamathta3" size="34" $readmode /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Poskod :</div></td>
                                                <td><input name="txtPoskodHartaHtaamX" type="text" id="textfield47" $readmode value="$listam.poskodhta" maxlength="5" /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Bandar :</div></td>
                                                <td><input name="txtBandarHartaHtaamX" type="text" id="textfield48" value="$listam.bandarhta" size="34" $readmode /></td>
                                              </tr>
                                              
                                              <tr>
                                                <td><div align="right">No Perjanjian :</div></td>
                                                <td><input name="txtNoPerjanjianHtaamX" type="text" id="textfield49" value="$listam.noperjanjian" size="34" $readmode /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Tarikh Perjanjian :</div></td>
                                                <td><span class="style36">
               <input name="txtTarikhPerjanjianHtaamX" id="txdTarikhMatiWaris2" type="text" $readmode value="$listam.tarikhperjanjian" size="15" />
                                                <a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
                                              </tr>
                                              #end
                                              
                                              #if( $listam.flag=="2")
                                              <tr>
                                                <td><div align="right">Nama Rancangan :</div></td>
                                                <td><label>
                                                  <input name="txtNamaRancanganHtaamX" type="text" id="textfield50" value="$listam.namarancangan" size="34" $readmode />
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">No ROH :</div></td>
                                                <td><input name="txtNoRohHtaamX" type="text" id="textfield51" value="$listam.noroh" size="34" $readmode /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Lot ID :</div></td>
                                                <td><input name="txtLotIdHtaamX" type="text" id="textfield52" value="$listam.nolot" size="34" $readmode /></td>
                                              </tr>
                                              #end
                                          </table></td>
                                          <td width="50%" valign="top"><table width="100%">
                                              <tr>
                                                <td width="30%"><div align="right">Bahagian Simati :</div></td>
                                                <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati5" $readmode value="$listam.basimati" size="5" />
/
  <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati6" value="$listam.bbsimati" size="5" $readmode/></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Kategory Tanah :</div></td>
                                                <td>#foreach($listkate in $listkategori)
                                          
                                          #if($listam.kategori==$listkate.id)
                                          
                                          #set($listkategoriK=$listkate.kod)
                                          #set($listkategoriN=$listkate.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.kategori!="")
                                          <select name="socKategoriTanahHtaamX" class="mediumselect" $readmode id="socKategoriTanahHtaam3">
                                            <option value="$listam.kategori">$listkategoriK - $listkategoriN</option>
                                            
                                            
                                        
                                            
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                  #if($listam.kategori!=$listkate.id)
                                    
	                               
                                                  
                                          
                                            
                                            
                                            
                                            
                                            <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                   
                                                   
                                          
                                          
                                          
                                          
                                          </select>
#else
<select name="socKategoriTanahHtaamX" class="mediumselect" $readmode id="socKategoriTanahHtaam3">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
  
  
  
  
  
                                              
                                  #foreach($listkate in $listkategori)
                                 
                                
	                               
                                              
  
  
  
  
  
  <option value="$listkate.id">$listkate.kod - $listkate.keterangan</option>
  
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            





</select>
#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Jenis Luas : </div></td>
                                                <td>#foreach($listluashta in $listluas)
                                          
                                          #if($listam.jenisluas==$listluashta.id)
                                          
                                          #set($listluasK=$listluashta.kod)
                                          #set($listluasN=$listluashta.nama)
                                          #end 
                                          #end
                                          #if($listam.jenisluas!="")
                                          <select name="socJenisLuasHtaamX" class="mediumselect" $readmode id="socJenisLuasHtaamUp2">
                                            <option value="$listam.jenisluas">$listluasK - $listluasN</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listluashta in $listluas)
                                 
                                  #if($listam.jenisluas!=$listluashta.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          </select>
                                          <input name="txtLuasAsalHtaamX" type="text" id="txtLuasAsalHtaamUp3" value="$listam.luasasal" size="15" $readmode />
#else
<select name="socJenisLuasHtaamX" class="mediumselect" $readmode id="socJenisLuasHtaamUp2">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
  
  
  
                                              
                                    #foreach($listluashta in $listluas)
                                 
                                
	                               
                                              
  
  
  
  <option value="$listluashta.id">$listluashta.kod - $listluashta.nama</option>
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            



</select>
<input name="txtLuasAsalHtaamX" type="text" id="txtLuasAsalHtaamUp4" value="$listam.luasasal" size="15" $readmode />
#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Luas (Hektar/MP) :</div></td>
                                                <td><input name="txtLuasHMpHtaamX" type="text" id="txtLuasHMpHtaamUp2" $readmode value="$listam.luashmp" /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Nilai Tarikh Mati :</div></td>
                                                <td><input name="txtNilaiTarikhMatiHtaamX" type="text" id="txtNilaiTarikhMatiHtaamUp2" $readmode value="$listam.nilai_Hta_mati" /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Nilai Tarikh Mohon :</div></td>
                                                <td><input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaa2" $readmode value="$listam.nilai_Hta_memohon" /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">No Cagaran :</div></td>
                                                <td><input name="txtNoCagaranX" type="text" id="txtNoPajakan4" value="$listam.nocagaran" $readmode/></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">No Pajakan :</div></td>
                                                <td><input name="txtNoPajakanX" type="text" id="txtNoPajakan3" value="$listam.nopajakan" $readmode/></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Status Pemilikan :</div></td>
                                                <td>#foreach($listpemilik in $listpemilikan)
                                          
                                          #if($listam.pemilikan==$listpemilik.id)
                                          
                                          #set($listpemilikK=$listpemilik.kod)
                                          #set($listpemilikN=$listpemilik.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.pemilikan!="")
                                          <select name="socStatusPemilikanHtaamX" class="mediumselect" $readmode id="socStatusPemilikanHtaamUp2">
                                            <option value="$listam.pemilikan">$listpemilikK - $listpemilikN</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                  #if($listam.pemilikan!=$listpemilik.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          </select>
#else
<select name="socStatusPemilikanHtaamX" class="mediumselect" $readmode id="socStatusPemilikanHtaamUp2">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
    
  
  
  
  
                                              
                                  #foreach($listpemilik in $listpemilikan)
                                 
                                
	                               
                                              
  
  
  
  
    
  <option value="$listpemilik.id">$listpemilik.kod - $listpemilik.keterangan</option>
  
    
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            




  
</select>
#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Tanggungan :</div></td>
                                                <td><input name="txtTanggunganHtaamX" type="text" id="txtTanggunganHtaam3" $readmode value="$listam.tanggungan" /></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Jenis Tanah :</div></td>
                                                <td>#foreach($listtan in $listtanah)
                                          
                                          #if($listam.jenistanah==$listtan.id)
                                          
                                          #set($listtanK=$listtan.kod)
                                          #set($listtanN=$listtan.keterangan)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.jenistanah!="")
                                          <select name="socJenisTanahHtaamX" class="mediumselect" $readmode id="socJenisTanahHtaam3">
                                            <option value="$listam.jenistanah">$listtanK - $listtanN</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                  #if($listam.jenistanah!=$listtan.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            
                                            
                                            
                                            <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          
                                          
                                          
                                          </select>
#else
<select name="socJenisTanahHtaamX" class="mediumselect" $readmode id="socJenisTanahHtaam3">
  <option value=""> &lt; Sila Pilih &gt; </option>
  
    
  
  
  
  
  
                                              
                                  #foreach($listtan in $listtanah)
                                 
                                
	                               
                                              
  
  
  
  
  
    
  <option value="$listtan.id">$listtan.kod - $listtan.keterangan</option>
  
    
  
  
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            





  
</select>
#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="right">Catatan :</div></td>
                                                <td><textarea name="txtCatatanHtaamX" id="txtCatatanHtaam2" cols="25" rows="5" $readmode>$listam.catatan</textarea></td>
                                              </tr>
                                                <input type="hidden" name="idhtaamX" value="$listam.idhta" />
                                   

                                          </table></td>
                                        </tr>
                                    </table></td>
                                  </tr>
                                  #end
                                  
                                 
                                  #end
                                    <input type="hidden" name="idhtaamXid" value="$idhtaam" />
                                  <tr>
                                    <!--
                                      
                                      this.context.put("show_simpan_add_htaam","yes");
				    this.context.put("show_batal_add_htaam","yes");
		            this.context.put("show_kemaskini_htaam","");
				    this.context.put("show_save_update_htaam","");
				    this.context.put("show_batal_update_htaam","");
				    this.context.put("show_hapus_htaam","yes");
				    
				    this.context.put("show_kembali_htaam","yes");
				    
				    this.context.put("show_htaa_update_table","");
				    this.context.put("show_htaa_add_table","yes");	  
                    -->
                                    <td align="center"> #if($show_simpan_add_htaam=="yes")
                                      <input type="button" name="tambahpenghutang3" id="tambahpenghutang3"$readmode value="Simpan add" onkeypress="setSelected(1,0,0,1);add_HtaamX()" onclick="setSelected(1,0,0,1);add_HtaamX()"/>
                                      #end
                                      
                                      #if($show_batal_add_htaam=="yes")
                                      <input type="button" name="cmdSimpan9" id="cmdSimpan8" value="Batal add" onkeypress="addcancelhtaam()" onclick="addcancelhtaam()"/>
                                      #end
                                      #if($show_kemaskini_htaam=="yes")
                                      <input type="submit" name="button7" id="button11" value="Kemaskini" onkeypress="setSelected(1,0,0,1);edit_HtaamX()" onclick="setSelected(1,0,0,1);edit_HtaamX()" />
                                      #end
                                      #if($show_save_update_htaam=="yes")
                                      <input type="submit" name="button13" id="button12" value="Simpan Update" onkeypress="setSelected(1,0,0,1);save_HtaamX()" onclick="setSelected(1,0,0,1);save_HtaamX()"/>
                                      #end
                                      #if($show_batal_update_htaam=="yes")
                                      <input type="submit" name="button7" id="button13" value="Batal Update" onkeypress="setSelected(1,0,0,1);batal_HtaamX()" onclick="setSelected(1,0,0,1);batal_HtaamX()" />
                                      #end
                                      #if($show_hapus_htaam=="yes")
                                      <input type="submit" name="cmdCetak10" id="cmdCetak9" value="Hapus" onkeypress="setselected(1,0,0,1);hapus_HtaamX()" onclick="setSelected(1,0,0,1);hapus_HtaamX()"/>
                                      #end
                                      #if($show_kembali_htaam=="yes")
                                      <input type="submit" name="cmdKembali10" id="cmdKembali9" value="Kembali" onkeypress="setselected(1,0,0,1);kembali_HtaamX()" onclick="setSelected(1,0,0,1);kembali_HtaamX()"/>
                                      #end </td>
                                  </tr>
                                  
                                  
                                  
                                  
                                  
                                  
                                  <tr>
                                  <td>
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="5%"><div align="center">NO</div></td>
                                         <td><div align="center">ID HTA</div></td>
                                        <td><div align="center">NEGERI</div></td>
                                        <td><div align="center">DAERAH</div></td>
                                        <td><div align="center">MUKIM</div></td>
                                        <td><div align="center">NO PERJANJIAN / ROH</div></td>
                                        <td><div align="center">NO PT/NO LOT</div></td>
                                        <td><div align="center">BAHAGIAN SIMATI</div></td>
                                            
                                         
                                            
                                    </tr>
                                    #set($plk=0)
                                    #foreach($listam in $listHTAX)
                                        #set($plk=$plk+1)
          
         							 #if($plk%2!=0)
                                    <tr bgcolor="white">
                                    <td><div align="center">$plk</div></td>
                                       <td><a href="javascript:setSelected(1,0,0,1);get_X($listam.idhta)">$listam.idhta</td>
    #if($listam.negeri=="")
    
    #set($nama_negeri="")
    #else
    #foreach($ln in $listnegeri)                                                                      
    #if($ln.id_Negeri==$listam.negeri)
    #set($nama_negeri=$ln.nama_Negeri)
    
    #end
    #end
    
    #end
    <td>$nama_negeri</td>
    #if($listam.daerah=="")
    
    #set($nama_daerah="")
    #else
    #foreach($ld in $listdaerah)                                                                      
    #if($ld.id==$listam.daerah)
    #set($nama_daerah=$ld.nama)
    
    #end
    #end
    
    #end
    <td>$nama_daerah</td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td>$nama_mukim</td>
    #if($listam.noperjanjian!="" && $listam.noroh!="")
    <td>$listam.noperjanjian / $listam.noroh</td>
    #else
    <td>
    
    </td>
    #end
    
    <td>$listam.nopt</td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td>$listam.basimati / $listam.bbsimati</td>
    #else
    <td></td>
    #end
    
                                            </tr>
                                    #else
                                        <tr class="table_header">
                                  
                                   <td><div align="center">$plk</div></td>
                                      <td><a href="javascript:setSelected(1,0,0,1);get_X($listam.idhta)">$listam.idhta</td>
    #if($listam.negeri=="")
    
    #set($nama_negeri="")
    #else
    #foreach($ln in $listnegeri)                                                                      
    #if($ln.id_Negeri==$listam.negeri)
    #set($nama_negeri=$ln.nama_Negeri)
    
    #end
    #end
    
    #end
    <td>$nama_negeri</td>
    #if($listam.daerah=="")
    
    #set($nama_daerah="")
    #else
    #foreach($ld in $listdaerah)                                                                      
    #if($ld.id==$listam.daerah)
    #set($nama_daerah=$ld.nama)
    
    #end
    #end
    
    #end
    <td>$nama_daerah</td>
    #if($listam.mukim=="")
    
    #set($nama_mukim="")
    #else
    #foreach($lm in $listmukim)                                                                      
    #if($lm.id==$listam.mukim)
    #set($nama_mukim=$lm.nama)
    
    #end
    #end
    
    #end
    <td>$nama_mukim</td>
    #if($listam.noperjanjian!="" && $listam.noroh!="")
    <td>$listam.noperjanjian / $listam.noroh</td>
    #else
    <td>
    
    </td>
    #end
    
    <td>$listam.nopt</td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td>$listam.basimati / $listam.bbsimati</td>
    #else
    <td></td>
                                           
                                           
    #end                                        </tr>
                                    
                                    #end
                                    #end
                                  </table>                               
                                     </td>
                                </tr>

                                </table>
                              </div>
                            </div>
                    </div>
                  </div>
                  <div class="TabbedPanelsContent">
                    <div class="TabbedPanelsContent">           
#set ($bhgnmati1 = "")
#set ($bhgnmati2 = "")
#set ($norujukan = "")
#set ($nilaitarikhmati = "0.00")
#set ($nosijil = "")
#set ($nilaitarikhmohon = "0.00")
#set ($bilunit = "")
#set ($nilaiseunit = "0.00")
#set ($agensi = "")
#set ($catatan = "")
#set ($disabled = "")

#if ($EventStatus == 2 || $EventStatus == 3)
 	#foreach($Data in $DataHa)
	#set ($idJenisHa = $Data.id_Jenisha)
	#set ($bhgnmati1 = $Data.basimati)
	#set ($bhgnmati2 = $Data.bbsimati)
	#set ($norujukan = $Data.noDaftar)
	#set ($nilaitarikhmati = $Data.nilaiha_tarikhmati)
	#set ($nosijil = $Data.nosijil)
	#set ($nilaitarikhmohon = $Data.nilaiha_tarikhmohon)
	#set ($bilunit = $Data.bilunit)
	#set ($nilaiseunit = $Data.nilaiseunit)
	#set ($agensi = $Data.jenama)
	#set ($catatan = $Data.catatan)
		
	#end
#end

#if ($EventStatus == 2)
	#set ($disabled = "disabled")
#end
#if ($EventStatus == 0 || $EventStatus == 2 || $EventStatus == 3)
<fieldset >
<table width="100%" border="0">
  <tbody>
    <tr>
      <td width="330px" scope="row"><strong>Jenis Harta Alih <font color="red">*</font></strong></td>
      <td width="200px">: <select name="socJenisHartaAlih" id="socJenisHartaAlih" class="mediumselect" onChange="getJenisHa()" $disabled>
        <option value="0"> &lt;--Pilih--&gt;</option>
        #set ($selected = "")
	        #foreach($listJenis in $ViewJenisHa)
	        	#if ($EventStatus == 0)
		        	#if ($listJenis.idjenisha == $socJenisHa)
		        		#set ($selected = "selected")
		        	<option value="$listJenis.idjenisha" $selected>$listJenis.kod - $listJenis.keterangan</option>
		        	#else
		        	<option value="$listJenis.idjenisha">$listJenis.kod - $listJenis.keterangan</option>
		        	#end
	        	#end
	        	#if ($EventStatus == 2 || $EventStatus == 3 )
		        	#if ($listJenis.idjenisha == $idJenisHa)
		        		#set ($selected = "selected")
		        	<option value="$listJenis.idjenisha" $selected>$listJenis.kod - $listJenis.keterangan</option>
		        	#else
		        	<option value="$listJenis.idjenisha">$listJenis.kod - $listJenis.keterangan</option>
		        	#end
	        	#end
	        #end
 
      </select></td>
      <td width="300px" ><strong>Bahagian Simati</strong></td>
      <td width="250px" >: <input name="txtBhgnSimati1" id="txtBhgnSimati1" type="text" style="width: 70px; text-transform:uppercase;" value="$bhgnmati1" $disabled/> / <input  name="txtBhgnSimati2" id="txtBhgnSimati2" type="text" style="width: 70px;" value="$bhgnmati2" $disabled/></td>
    </tr>
    <tr>
      	<td scope="row"><strong>
     #if ($socJenisHa == 1) 
      	No. Rujukan UPT
     #elseif ($socJenisHa == 2)
     	No. Akaun
     #elseif ($socJenisHa == 3)
     	No. Rujukan UPT
     #elseif ($socJenisHa == 4)
     	No. Ahli
     #elseif ($socJenisHa == 5)	
     	No. Daftar
     #else
     	No. Rujukan UPT
     #end
      	</strong></td>
      #if ($socJenisHa == 1) 
      	<td>: <input name="txtNoRujukan" id="txtNoRujukan" type="text" style="width: 150px; text-transform:uppercase;" value="$norujukan" $disabled/></td> 
      #else
      <td>: <input name="txtNoRujukan" id="txtNoRujukan" type="text" style="width: 150px; text-transform:uppercase;" value="$norujukan" $disabled readonly/></td>
      #end 
       <td><strong>Nilai Tarikh Mati (RM)</strong></td>
      <td>: <input name="txtNilaiTarikhMati" id="txtNilaiTarikhMati" type="text" style="width: 150px; text-align: right; text-transform:uppercase;" value="$nilaitarikhmati" $disabled /></td>
     </tr>
    <tr>
    #if ($socJenisHa == 1) 
      <td scope="row"><strong>No. Sijil</strong></td>
      <td>: <input name="txtNoSijil" id="txtNoSijil" type="text" style="width: 150px; text-transform:uppercase;" value="$nosijil" $disabled/></td>
    #else
      <td scope="row"><strong>No. Sijil</strong></td>
      <td>: <input name="txtNoSijil" id="txtNoSijil" type="text" style="width: 150px; text-transform:uppercase;" value="$nosijil" readonly/></td>
    #end  
      <td><strong>Nilai Tarikh Mohon (RM)</strong></td>
      <td>: <input  name="txtNilaiTarikhMohon" id="txtNilaiTarikhMohon" type="text" style="width: 150px; text-align: right; text-transform:uppercase;" value="$nilaitarikhmohon" $disabled /></td>
    </tr>
    <tr>
    #if ($socJenisHa == 1)
      <td scope="row"><strong>Bil. Unit</strong></td>
      <td>: <input name="txtBilUnit" id="txtBilUnit" type="text" style="width: 150px; text-transform:uppercase;" value="$bilunit" $disabled/></td>
    #else
      <td scope="row"><strong>Bil. Unit</strong></td>
      <td>: <input name="txtBilUnit" id="txtBilUnit" type="text" style="width: 150px; text-transform:uppercase;" value="$bilunit" readonly/></td>
    #end  
      <td></td>
      <td></td>
    </tr>
    <tr>
     #if ($socJenisHa == 1)
      <td><strong>Nilai Seunit</strong></td>
      <td>: <input name="txtNilaiSeunit" id="txtNilaiSeunit" align="right" type="text" style="width: 150px; text-align: right; text-transform:uppercase;" value="$nilaiseunit" $disabled/></td>
      #else
      <td><strong>Nilai Seunit</strong></td>
      <td>: <input name="txtNilaiSeunit" id="txtNilaiSeunit" align="right" type="text" style="width: 150px; text-align: right; text-transform:uppercase;" value="$nilaiseunit" readonly/></td>
    #end
      <td valign="top"></td>
      <td></td>
    </tr>
    <tr> 
      <td><strong>
      #if ($socJenisHa == 1)
      Lain
      #elseif ($socJenisHa == 2)
      Agensi
      #elseif ($socJenisHa == 3)
      Lain
      #elseif ($socJenisHa == 4)
      Agensi
      #elseif ($socJenisHa == 5)
      Jenama
      #else
      Lain
      #end
      </strong></td>
      <td>: <input  name="txtAgensi" id="txtAgensi" type="text" style="width: 150px; text-transform:uppercase;" value="$agensi" $disabled/></td>
      <td valign="top"><strong></strong></td>
      <td></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
	  <td valign="top"><strong>Catatan</strong></td>
      <td><textarea name="txtCatatan" id="txtCatatan" cols="25" rows="4" style="width: 150px; text-transform:uppercase;" $disabled>$catatan</textarea></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><strong>Rekod</strong></td>
      <td>$jumList</td>
	    </tr>
  </tbody>
</table>
</fieldset>
#end

#if ($EventStatus == 1)
<input type="submit" name="cmdTambah" value="Tambah" onClick="getFormHa()">
#elseif ($EventStatus == 2)
<p align="center">
<input type="submit" name="cmdKemaskini" value="Kemaskini" onClick="getKemaskini()">
<input type="submit" name="cmdHapus" value="Hapus" onClick="getHapus()">
<input type="submit" name="cmdBatal" value="Batal" onClick="getBatal()">
</p>
#elseif ($EventStatus == 3)
<p align="center">
<input type="submit" name="cmdSimpan" value="Simpan" onClick="getUpdate()">
<input type="submit" name="cmdBatal" value="Batal" onClick="getBatal()">
</p>
#else
<p align="center">
<input type="submit" name="cmdSimpan" value="Simpan" onClick="getSimpan()">
<input type="reset" name="cmdKosong" value="Kosongkan">
<input type="submit" name="cmdBatal" value="Batal" onClick="getBatal()">
</p>
#end
<fieldset><legend><b>Senarai Harta Alih</b></legend>
<table width="100%" bordercolor="#333333">
<tr class="table_header">
<td>Bil</td>
<td>Jenis Harta Alih</td>
<td>No Rujukan UPT / No Daftar / No Akaun / No Ahli</td>
</tr>
#set ($cnt = 0)
#foreach($list in $listHa)
#set ($cnt = $cnt + 1)
#set ($id3 = $list.id_Ha)
<tr bgcolor="white">
<td><a href="javascript:edit_hartaalih('$id3')">$cnt</a></td>
<td>$list.Keterangan</td>
<td>$list.noDaftar</td>
</tr>
#end
<tr>
<td colspan="3"></td>
</tr>
</table>
</fieldset>			
                    </div>
                  </div>
                  <div class="TabbedPanelsContent">
<fieldset>
<B>Nilai Harta Tak Alih</B>
<table width="100%">
<tr class="table_header">
<td width="40px">Bil</td>
<td width="80px">Negeri</td>
<td width="70px">Daerah</td>
<td width="50px">Mukim</td>
<td width="100px">No Hakmilik /<br> No Perjanjian</td>
<td width="60px">No PT /<br> No Lot</td>
<td width="115px">Nilai HTA Tarikh Mohon</td>
<td width="115px">Nilai HTA Tarikh Mati</td>
</tr>
#set ($sumhta = 0)
#set ($sumhtamati = 0)
#set ($cnt = 0)
#set ($i = 0)
#foreach($listhath in $listHtaht2)
#set ($cnt = $cnt + 1)
	#set ($sumhta = $sumhta + $listhath.nilai_tarikhmohon)
	#set ($sumhtamati = $sumhtamati + $listhath.nilai_tarikhmati)
<tr bgcolor="white">
<td>$cnt</td>
<td>$listhath.nama_Negeri</td>
<td>$listhath.nama_Daerah</td>
<td>$listhath.nama_Mukim</td>
<td>$listhath.no_Perjanjian</td>
<td>$listhath.no_Pt</td>
#if ($EventStatus == 4)
<input type="hidden" name="idHta" value="$listhath.id_Hta">
<input type="hidden" name="txtJumlahHtaTarikhMohon" value="$sumhta">
<input type="hidden" name="txtJumlahHtaTarikhMati" value="$sumhtamati">
<td align="right"><input type="text" style="width: 150px; text-align: right;" name="txtHtaNilaiTarikhMohon" value="$listhath.nilai_tarikhmohon" size="20"></td>
<td align="right"><input type="text" style="width: 150px; text-align: right;" name="txtHtaNilaiTarikhMati" value="$listhath.nilai_tarikhmati" size="20"></td>
#else
<td align="right">$Util.formatDecimal($listhath.nilai_tarikhmohon)</td>
<td align="right">$Util.formatDecimal($listhath.nilai_tarikhmati)</td>
#end
</tr>
#end
<tr>
<td colspan="8">&nbsp;</td>
</tr>

<tr class="table_header">
<td colspan="6"><b>Jumlah Nilai Harta Tak Alih (RM)</b></td>
<td colspan="1" align="right"><b>$Util.formatDecimal($sumhta)</b></td>
<td colspan="1">&nbsp;</td>
</tr>
</table>
</fieldset>
<br>
<fieldset>
<b>Nilai Harta Alih</b>
<table width="100%" bordercolor="#333333">
<tr class="table_header">
<td width="30px">Bil</td>
<td width="100px">Jenis Harta Alih</td>
<td width="140px">No Rujukan UPT / No Daftar / <br>No Akaun / No Ahli</td>
<td width="80px">No Sijil</td>
<td width="110px">Nilai HA Tarikh Mohon</td>
<td width="110px">Nilai HA Tarikh Mati</td>
</tr>
#set ($id_ha = "&nbsp;")
#set ($nama_Negeri = "&nbsp;")
#set ($nama_Daerah = "&nbsp;")
#set ($jenis = "&nbsp;")
#set ($no_Perjanjian = "&nbsp;")
#set ($sijil = "&nbsp;")
#set ($nilai_tarikhmohon = "&nbsp;")
#set ($nilai_tarikhmati = "&nbsp;")
#set ($sumppkha = "")

#set ($cnt = 0)
#foreach($listha2 in $listHa)
#set ($id_ha = $listha2.id_Ha)
#set ($jenis = $listha2.Keterangan)
#set ($no_Perjanjian = $listha2.noDaftar)
#set ($sijil = $listha2.nosijil)
#set ($nilai_tarikhmohon = $listha2.nilai_tarikhmohon)
#set ($nilai_tarikhmati = $listha2.nilai_tarikhmati)
#set ($cnt = $cnt + 1)
<tr bgcolor="white">
<td>$cnt</td>
<td>$jenis</td>
<td>$no_Perjanjian</td>
<td>$sijil</td>
#if ($EventStatus == 4)
<input type="hidden" name="idHa" value="$listha2.id_Ha">
<td align="right"><input type="text" style="width: 150px; text-align: right;" name="txtHaNilaiTarikhMohon" value="$nilai_tarikhmohon" size="20"></td>
<td align="right"><input type="text" style="width: 150px; text-align: right;" name="txtHaNilaiTarikhMati" value="$nilai_tarikhmati" size="20"></td>
#else
<td align="right">$Util.formatDecimal($nilai_tarikhmohon)</td>
<td align="right">$Util.formatDecimal($nilai_tarikhmati)</td>
#end
</tr>
#end
#set ($sumjumlah = 0)
#set ($sumjumlahmati = 0)
#foreach($listha2 in $listHa)
#set ($sumjumlah = $sumjumlah + $listha2.nilai_tarikhmohon)
#set ($sumjumlahmati = $sumjumlahmati + $listha2.nilai_tarikhmati)
#end
<input type="hidden" name="txtJumlahHaTarikhMohon" value="$sumhta">
<input type="hidden" name="txtJumlahHaTarikhMati" value="$sumjumlahmati">
#set ($overalljumlah = 0)
#foreach($listOverall in $overall)
#set ($overalljumlah = $overalljumlah + $listOverall.nilaibesar)
#end
#set ($overalljumlahmati = 0)
#foreach($listOverallmati in $overallmati)
#set ($overalljumlahmati = $overalljumlahmati + $listOverallmati.nilaibesarmati)
#end
<input type="hidden" name="txtJumlahBesarTarikhMohon" value="$overalljumlah">
<input type="hidden" name="txtJumlahBesarTarikhMati" value="$overalljumlahmati">
<tr>
<td colspan="6">&nbsp;</td>
</tr>
<tr class="table_header">
<td colspan="4"><b>Jumlah Nilai Harta Alih (RM) </b></td>

<td align="right"><b>$Util.formatDecimal($sumjumlah)</b></td>


<td>&nbsp;</td>
</tr>
</table>
</fieldset>
<br>
<fieldset>
<B>Nilai Harta Keseluruhan</B>
<table width="100%">
<tr class="table_header">
<td width="10px">Bil</td>
<td width="80px">Perkara</td>
<td width="70px">Jumlah Nilai Harta (RM)</td>
</tr>
<tr>
<td width="10px">1. </td>
<td width="80px">Jumlah Nilai Harta Tak Alih</td>
<td width="70px" align="right">$Util.formatDecimal($sumhta)</td>
</tr>
<tr>
<td width="10px">2. </td>
<td width="80px">Jumlah Nilai Harta Alih</td>
<td width="70px" align="right">$Util.formatDecimal($sumjumlah)</td>
</tr>
<tr>
<td colspan="3">&nbsp;</td>
</tr>
<tr class="table_header">
<td colspan="2"><b>Jumlah Nilai Harta Keseluruhan</b></td>

#foreach($listOverall in $overall)
#set ($overalljumlah = $overalljumlah + $listOverall.nilaibesar)
#end
<td colspan="1" align="right"><b>$Util.formatDecimal($overalljumlah)</b></td>
</tr>
<tr>
<tr>
<td colspan="8">&nbsp;</td>
</tr>
</table>
</fieldset>
#if ($EventStatus == 4)
<p align="center"><input type="button" name="cmdSimpan" value="Simpan" onClick="getNilaiHartaSimpan()"><input type="button" name="cmdBatal" value="Batal" onClick="getNilaiHartaBatal()"></p>
#else
<p align="center"><input type="button" name="cmdkemasini" value="Kemaskini" onClick="getNilaiHartaKemaskini()"></p>
#end

</div>
                </div>
            </div>
            </td>
          </tr>
            <tr>
              <td>&nbsp;</td>
          </tr>
          </table>
      <p>&nbsp;</p>
      </td>
    </tr>
      <input type="hidden" name="command" value="">
      <input type="hidden" name="mode" value="">
      <input type="hidden" name="eventStatus">
      <input type="hidden" name="idTemp" value="$id">
      <input type="hidden" name="id" value="$id">
      <input type="hidden" name="id1" value="$id1">
      <input type="hidden" name="id2" value="$id2">
      <input type="hidden" name="id3" value="$id3">
       <input type="hidden" name="idha" value="$idha">
       <input type="hidden" name="bil" value="$jumList">
  </table>
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
  <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
   <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
     <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
 <input name="tabLevel" type="hidden" id="tabLevel" value="$selectLevelTab"/>
  <p>&nbsp;</p>
  </td>
  </tr>
   </table>
 
 
</form>

<p>&nbsp;</p>
<script type="text/javascript">

	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});

   var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});

var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>
<script>
function cancel() {
document.f1.reset();
document.f1.txtNoFail.value = "";
document.f1.txtNoFail.focus();
}
 onchange="setSelected(1,0,0,0);Perjanjian_Jual_Beli()"
                                            onchange="setSelected(1,0,0,0);Pegangan_Di_Bawah_Akta_Tanah()"
                                            onchange="setSelected(1,0,0,0);Kepentingan_Lain_lain()"
											
											
function Perjanjian_Jual_Beli() {

document.f1.command.value = "HtaamX";
document.f1.mode.value = "HtaamviewX";
document.f1.submit();
document.f1.action = "";

}

function Pegangan_Di_Bawah_Akta_Tanah() {

document.f1.command.value = "HtaamX";
document.f1.mode.value = "HtaamviewXradio2";
document.f1.submit();
document.f1.action = "";

}

function Kepentingan_Lain_lain() {

document.f1.command.value = "HtaamX";
document.f1.mode.value = "HtaamviewXradio3";
document.f1.submit();
document.f1.action = "";

}												
											
function addcancelhtaam() {
document.f1.reset();
document.f1.command.value = "Htaam";
document.f1.mode.value = "Htaamview";
document.f1.submit();
document.f1.socNegeriHtaam.focus();
}



function get_htaam(idhtaam)
{
    document.f1.command.value = "Htaam";
	document.f1.idhtaam.value = idhtaam;
	document.f1.mode.value = "getHtaam";
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

function search_data(){
	document.f1.command.value = "";
	//document.f1.nama_fail.value = key;
	document.f1.action = "";
	document.f1.submit();
}
function Pemohon(){
	document.f1.command.value = "pemohon";
	document.f1.action = "";
	document.f1.submit();
}
function Peguam(){
	document.f1.command.value = "peguam";
	document.f1.action = "";
	document.f1.submit();
}

function kemaskini_peguam(){
	document.f1.mode.value = "kemaskini_peguam";
	document.f1.command.value = "Peguam";
	document.f1.action = "";
	document.f1.submit();
}
function simpan_peguam() {
	
	document.f1.mode.value = "simpan_peguam";
	document.f1.command.value = "Peguam";
	document.f1.action = "";
	document.f1.submit();
	
}
function batal_peguam() {
	document.f1.action = "";
	document.f1.mode.value = "batal_peguam";
	document.f1.command.value = "Peguam";
	document.f1.submit();
}
function TambahWaris() {
	document.f1.command.value = "tambahWaris";
	document.f1.action = "";
	document.f1.submit();
}
function cetak_simati(){	
	window.print();
}
function view_item(id){
	document.f1.command.value = "papar";
	document.f1.action = "";
	document.f1.idSimati.value = id;
	document.f1.submit();
}

function kemaskini_simati(){
	document.f1.mode.value = "kemaskini_simati";
	document.f1.command.value = "Simati";
	document.f1.action = "";
	document.f1.submit();
}
function simpan_simati(){
	document.f1.mode.value = "simpan_simati";
	document.f1.command.value = "Simati";
	document.f1.action = "";
	document.f1.submit();
}
function batal_simati(){
	document.f1.command.value = "Simati";
	document.f1.mode.value = "batal_simati";
	document.f1.action = "";
	document.f1.submit();
}

function kembali_simati(){
	
	document.f1.command.value = "kembali_simati";
	
	document.f1.action = "";
	document.f1.submit();
}

function batal_update_penting(){
	
	document.close()
}


function Seterusnya(){
	document.f1.command.value = "seterusnya";
	document.f1.action = "";
	document.f1.submit();
}


function kemaskini_pemohon(){
	document.f1.mode.value = "kemaskini_pemohon";
	document.f1.command.value = "Pemohon";
	document.f1.action = "";
	document.f1.submit();
}

function simpan_pemohon(){
	document.f1.mode.value = "simpan_pemohon";
	document.f1.command.value = "Pemohon";
	document.f1.action = "";
	document.f1.submit();
}


function kemaskini_penting(){
	document.f1.mode.value = "kemaskini_penting";
	document.f1.command.value = "Penting";
	document.f1.action = "";
	document.f1.submit();
}


function simpan_penting(){
	document.f1.mode.value = "simpan_penting";
	document.f1.command.value = "Penting";
	document.f1.action = "";
	
	document.f1.submit();

	
	
}
function PentingView() {
	document.f1.action = "";
	document.f1.mode.value = "Pentingview";
	document.f1.command.value = "Penting";
	document.f1.submit();
}

function SimatiView() {
	document.f1.action = "";
	document.f1.mode.value = "Simatiview";
	document.f1.command.value = "Simati";
	document.f1.submit();
}



function BatalSimati() {
	document.f1.action = "";
	document.f1.mode.value = "batal_simati";
	document.f1.command.value = "Simati";
	document.f1.submit();
}

function BatalPemohon() {
	document.f1.action = "";
	document.f1.mode.value = "batal_pemohon";
	document.f1.command.value = "Pemohon";
	document.f1.submit();
}



function PemohonView() {
	document.f1.action = "";
	document.f1.mode.value = "Pemohonview";
	document.f1.command.value = "Pemohon";
	document.f1.submit();
}

function PeguamView() {
	document.f1.action = "";
	document.f1.mode.value = "Peguamview";
	document.f1.command.value = "Peguam";
	document.f1.submit();
}

function HtaamView() {
	document.f1.action = "";
	document.f1.mode.value = "Htaamview";
	document.f1.command.value = "Htaam";
	document.f1.submit();
}

function HtaamViewX() {
	document.f1.action = "";
	document.f1.mode.value = "HtaamviewX";
	document.f1.command.value = "HtaamX";
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

function SimpanSimati() {

input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	if(document.f1.txtNamaSimati.value == ""){
		alert('Sila masukkan " Nama simati " terlebih dahulu.');
  		document.f1.txtNamaSimati.focus(); 
		return; 
	}
	if(document.f1.txdTarikhMatiSimati.value == ""){
		alert('Sila masukkan " Tarikh mati " terlebih dahulu.');
  		document.f1.txdTarikhMatiSimati.focus(); 
		return; 
	}
	if(document.f1.txtSebabKematianSimati.value == ""){
		alert('Sila masukkan " Sebab Kematian " terlebih dahulu.');
  		document.f1.txtSebabKematianSimati.focus(); 
		return; 
	}
	document.f1.mode.value = "simpan_simati";
	document.f1.command.value = "Simati";
	document.f1.action = "";
	document.f1.submit();
	}
}

function SimpanPemohon() {
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.mode.value = "simpan_pemohon_data";
	document.f1.command.value = "Pemohon";
	document.f1.action = "";
	document.f1.submit();
    }
	
}

function simpan_peguam() {
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.mode.value = "simpan_peguam";
	document.f1.command.value = "Peguam";
	document.f1.action = "";
	document.f1.submit();
    }
	
}


function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
    document.f1.tabIdatas.value = tabIdatas;
    document.f1.tabIdtengah.value = tabIdtengah;
    document.f1.tabIdbawah.value = tabIdbawah;
	
	document.f1.tabIdtepi.value = tabIdtepi;
   
	
}




 





function PenghutangView() {
	document.f1.action = "";
	document.f1.mode.value = "Penghutangview";
	document.f1.command.value = "Penghutang";
	document.f1.submit();
}

function penghutangtambahbatal() {
	document.f1.reset();
	
document.f1.txtNamaPenghutang.value="";


document.f1.socJenisHutang.value="";
document.f1.txtNoAkaunPenghutang.value="";
document.f1.txtJumlahPenghutang.value="";


document.f1.txtNoKPBaru1Penghutang.value="";
document.f1.txtNoKPBaru2Penghutang.value="";
document.f1.txtNoKPBaru3Penghutang.value="";
document.f1.txtNoKPLamaPenghutang.value="";
document.f1.socJenisKPLainPenghutang.value="";
document.f1.txtNoKPLainPenghutang.value="";


document.f1.txtAlamatTerakhir1Penghutang.value="";
document.f1.txtAlamatTerakhir2Penghutang.value="";
document.f1.txtAlamatTerakhir3Penghutang.value="";
document.f1.txtPoskodPenghutang.value="";
document.f1.txtBandarPenghutang.value="";
document.f1.socNegeriPenghutang.value="";
document.f1.txtCatatanPenghutang.value="";
	document.f1.txtNoKPBaru1Penting.focus();
	}


function tambah_penghutang(){
	
	if( document.f1.tambahpenghutang.value == "simpan" ) 
	{
		input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    
    
    if(document.f1.txtNamaPenghutang.value==""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaPenghutang.focus(); 
			return; 
		}
        
		else
		{
	document.f1.mode.value = "tambah_penghutang";
	document.f1.command.value = "Penghutang";
	document.f1.action = "";
	document.f1.submit();
		}
	}}

	if( document.f1.tambahpenghutang.value == "Simpan" ) 
	{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {document.f1.mode.value = "simpan_penghutang";
	document.f1.command.value = "Penghutang";
	
	document.f1.action = "";
	document.f1.submit();
    }
	
	}

	if( document.f1.tambahpenghutang.value == "Kemaskini" ) 
	{   

    document.f1.tambahpenghutang.value = "Simpan";	
    document.f1.idpg.disabled = false;
    document.f1.txtNamaPenghutang.disabled = false;


    document.f1.socJenisHutang.disabled = false;
    document.f1.txtNoAkaunPenghutang.disabled = false;
    document.f1.txtJumlahPenghutang.disabled = false;

    document.f1.txtIdSimatiPenghutang.disabled = false;
    document.f1.txtNoKPBaru1Penghutang.disabled = false;
    document.f1.txtNoKPBaru2Penghutang.disabled = false;
    document.f1.txtNoKPBaru3Penghutang.disabled = false;
    document.f1.txtNoKPLamaPenghutang.disabled = false;
    document.f1.socJenisKPLainPenghutang.disabled = false;
    document.f1.txtNoKPLainPenghutang.disabled = false;


    document.f1.txtAlamatTerakhir1Penghutang.disabled = false;
    document.f1.txtAlamatTerakhir2Penghutang.disabled = false;
    document.f1.txtAlamatTerakhir3Penghutang.disabled = false;
    document.f1.txtPoskodPenghutang.disabled = false;
    document.f1.txtBandarPenghutang.disabled = false;
    document.f1.socNegeriPenghutang.disabled = false;
    document.f1.txtCatatanPenghutang.disabled = false;
		
	document.f1.txtNoKPBaru1Penghutang.focus();
	
	
	}
	
	
}
       
function edit_item_penghutang(idpg,idSimati,nama_Pg,jumlah,jenishutang,noakaun,nokpbaru1,nokpbaru2,nokpbaru3,nokplama,jeniskp,nokplain,idnegeri,alamat1,alamat2,alamat3,bandar,poskod,catatan) 
{

	document.f1.txtNoKPBaru1Penghutang.focus();
	document.f1.tambahpenghutang.value = "Kemaskini";
	

document.f1.idpg.value=idpg;
document.f1.txtNamaPenghutang.value=nama_Pg;


document.f1.socJenisHutang.value=jenishutang;
document.f1.txtNoAkaunPenghutang.value=noakaun;
document.f1.txtJumlahPenghutang.value=jumlah;

document.f1.txtIdSimatiPenghutang.value=idSimati;
document.f1.txtNoKPBaru1Penghutang.value=nokpbaru1;
document.f1.txtNoKPBaru2Penghutang.value=nokpbaru2;
document.f1.txtNoKPBaru3Penghutang.value=nokpbaru3;
document.f1.txtNoKPLamaPenghutang.value=nokplama;
document.f1.socJenisKPLainPenghutang.value=jeniskp;
document.f1.txtNoKPLainPenghutang.value=nokplain;


document.f1.txtAlamatTerakhir1Penghutang.value=alamat1;
document.f1.txtAlamatTerakhir2Penghutang.value=alamat2;
document.f1.txtAlamatTerakhir3Penghutang.value=alamat3;
document.f1.txtPoskodPenghutang.value=poskod;
document.f1.txtBandarPenghutang.value=bandar;
document.f1.socNegeriPenghutang.value=idnegeri;
document.f1.txtCatatanPenghutang.value=catatan;






document.f1.idpg.disabled = true;
document.f1.txtNamaPenghutang.disabled = true;


document.f1.socJenisHutang.disabled = true;
document.f1.txtNoAkaunPenghutang.disabled = true;
document.f1.txtJumlahPenghutang.disabled = true;

document.f1.txtIdSimatiPenghutang.disabled = true;
document.f1.txtNoKPBaru1Penghutang.disabled = true;
document.f1.txtNoKPBaru2Penghutang.disabled = true;
document.f1.txtNoKPBaru3Penghutang.disabled = true;
document.f1.txtNoKPLamaPenghutang.disabled = true;
document.f1.socJenisKPLainPenghutang.disabled = true;
document.f1.txtNoKPLainPenghutang.disabled = true;


document.f1.txtAlamatTerakhir1Penghutang.disabled = true;
document.f1.txtAlamatTerakhir2Penghutang.disabled = true;
document.f1.txtAlamatTerakhir3Penghutang.disabled = true;
document.f1.txtPoskodPenghutang.disabled = true;
document.f1.txtBandarPenghutang.disabled = true;
document.f1.socNegeriPenghutang.disabled = true;
document.f1.txtCatatanPenghutang.disabled = true;







}










function pentingbatal() {

document.f1.txtNoKPBaru1Penting.focus();

	if(document.f1.batalpenting.value = "Batal")
    {
	document.f1.reset();
	document.f1.idob.value = "";
	document.f1.txtNamaOBPenting.value = "";
	document.f1.txtIdSimatiPenting.value = idSimati;
	document.f1.txtNoKPBaru1Penting.value = "";
	document.f1.txtNoKPBaru2Penting.value = "";
	document.f1.txtNoKPBaru3Penting.value = "";
	document.f1.txtNoKPLamaPenting.value = "";
	document.f1.socJenisKPLainPenting.value = "";
	document.f1.txtNoKPLainPenting.value = "";
	document.f1.txtNoSuratBeranakPenting.value = "";
	document.f1.socStatusOB.value = "";
	document.f1.socJantinaPenting.value = "";
	document.f1.socAgamaPenting.value = "";
	document.f1.socWarganegaraPenting.value = "";
	document.f1.txtUmurPenting.value = "";
	document.f1.txdTarikhLahirPenting.value = "";
	document.f1.txtAlamatTerakhir1Penting.value = "";
	document.f1.txtAlamatTerakhir2Penting.value = "";
	document.f1.txtAlamatTerakhir3Penting.value = "";
	document.f1.txtPoskodPenting.value = "";
	document.f1.txtBandarPenting.value = "";
	document.f1.socNegeriPenting.value = "";
	document.f1.txtCatatanPenting.value = "";
	document.f1.socTarafKepentinganPenting.value = "";
	document.f1.txdTarikhLahirPenting.value = "";
	document.f1.txtNoKPBaru1Penting.focus();
    
    
    }
    
    
	}
    
    
    
    
    
    
    
function tambah_penting_baru(){

document.f1.mode.value = "tambah_penting_baru";
	document.f1.command.value = "Penting";
	
	document.f1.action = "";
	document.f1.submit();

}

function tambah_penting(){
	
	if( document.f1.tambahpenting.value == "simpan" ) 
	{
		input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {if(document.f1.txtNamaOBPenting.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaSimati.focus(); 
			return; 
            }
		}
	document.f1.mode.value = "tambah_penting";
	document.f1.command.value = "Penting";
	document.f1.action = "";
	document.f1.submit();
	}

	if( document.f1.tambahpenting.value == "Simpan" ) 
	{
    input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.mode.value = "simpan_penting";
	document.f1.command.value = "Penting";
	
	document.f1.action = "";
	document.f1.submit();
}
	
	}

	if( document.f1.tambahpenting.value == "Kemaskini" ) 
	{

    

    document.f1.tambahpenting.value = "Simpan";	

    document.f1.idob.disabled = false;
	document.f1.txtNamaOBPenting.disabled = false;
	document.f1.txtIdSimatiPenting.disabled = false;
	document.f1.txtNoKPBaru1Penting.disabled = false;
	document.f1.txtNoKPBaru2Penting.disabled = false;
	document.f1.txtNoKPBaru3Penting.disabled = false;
	document.f1.txtNoKPLamaPenting.disabled = false;
	document.f1.socJenisKPLainPenting.disabled = false;
	document.f1.txtNoKPLainPenting.disabled = false;
	document.f1.txtNoSuratBeranakPenting.disabled = false;
	document.f1.socStatusOB.disabled = false;
	document.f1.socJantinaPenting.disabled = false;
	document.f1.socAgamaPenting.disabled = false;
	document.f1.socWarganegaraPenting.disabled = false;
	document.f1.txtUmurPenting.disabled = false;
	document.f1.txdTarikhLahirPenting.disabled = false;
	document.f1.txtAlamatTerakhir1Penting.disabled = false;
	document.f1.txtAlamatTerakhir2Penting.disabled = false;
	document.f1.txtAlamatTerakhir3Penting.disabled = false;
	document.f1.txtPoskodPenting.disabled = false;
	document.f1.txtBandarPenting.disabled = false;
	document.f1.socNegeriPenting.disabled = false;
	document.f1.txtCatatanPenting.disabled = false;
	document.f1.socTarafKepentinganPenting.disabled = false;
	document.f1.txdTarikhLahirPenting.disabled = false;
		
	document.f1.txtNoKPBaru1Penting.focus();
	
	
	}
	
	
}

function edit_item(idOb,nama_Ob,nokpbaru1,nokpbaru2,nokpbaru3,idSimati,nokplama,jeniskp,nokplain,noberanak,dob,idnegeri,noTel,jantina,umur,alamat1,alamat2,alamat3,bandar,status_Ob,taraf,saudara,agama,catatan,warga,poskod) 
{

	document.f1.txtNoKPBaru1Penting.focus();
	document.f1.tambahpenting.value = "Kemaskini";
    
	
document.f1.idob.value = idOb;
document.f1.txtNamaOBPenting.value = nama_Ob;
document.f1.txtIdSimatiPenting.value = idSimati;
document.f1.txtNoKPBaru1Penting.value = nokpbaru1;
document.f1.txtNoKPBaru2Penting.value = nokpbaru2;
document.f1.txtNoKPBaru3Penting.value = nokpbaru3;
document.f1.txtNoKPLamaPenting.value = nokplama;
document.f1.socJenisKPLainPenting.value = jeniskp;
document.f1.txtNoKPLainPenting.value = nokplain;
document.f1.txtNoSuratBeranakPenting.value = noberanak;
document.f1.socStatusOB.value = status_Ob;
document.f1.socJantinaPenting.value = jantina;
document.f1.socAgamaPenting.value = agama;
document.f1.socWarganegaraPenting.value = warga;
document.f1.txtUmurPenting.value = umur;
document.f1.txdTarikhLahirPenting.value = "";
document.f1.txtAlamatTerakhir1Penting.value = alamat1;
document.f1.txtAlamatTerakhir2Penting.value = alamat2;
document.f1.txtAlamatTerakhir3Penting.value = alamat3;
document.f1.txtPoskodPenting.value = poskod;
document.f1.txtBandarPenting.value = bandar;
document.f1.socNegeriPenting.value = idnegeri;
document.f1.txtCatatanPenting.value = catatan;
document.f1.socTarafKepentinganPenting.value = taraf;
document.f1.txdTarikhLahirPenting.value = dob;

document.f1.idob.disabled = true;
document.f1.txtNamaOBPenting.disabled = true;
document.f1.txtIdSimatiPenting.disabled = true;
document.f1.txtNoKPBaru1Penting.disabled = true;
document.f1.txtNoKPBaru2Penting.disabled = true;
document.f1.txtNoKPBaru3Penting.disabled = true;
document.f1.txtNoKPLamaPenting.disabled = true;
document.f1.socJenisKPLainPenting.disabled = true;
document.f1.txtNoKPLainPenting.disabled = true;
document.f1.txtNoSuratBeranakPenting.disabled = true;
document.f1.socStatusOB.disabled = true;
document.f1.socJantinaPenting.disabled = true;
document.f1.socAgamaPenting.disabled = true;
document.f1.socWarganegaraPenting.disabled = true;
document.f1.txtUmurPenting.disabled = true;
document.f1.txdTarikhLahirPenting.disabled = true;
document.f1.txtAlamatTerakhir1Penting.disabled = true;
document.f1.txtAlamatTerakhir2Penting.disabled = true;
document.f1.txtAlamatTerakhir3Penting.disabled = true;
document.f1.txtPoskodPenting.disabled = true;
document.f1.txtBandarPenting.disabled = true;
document.f1.socNegeriPenting.disabled = true;
document.f1.txtCatatanPenting.disabled = true;
document.f1.socTarafKepentinganPenting.disabled = true;
document.f1.txdTarikhLahirPenting.disabled = true;







}





function SaksiView() {
	document.f1.action = "";
	document.f1.mode.value = "Saksiview";
	document.f1.command.value = "Saksi";
	document.f1.submit();
}



function saksitambahbatal() {
	document.f1.reset();
	document.f1.idob.value = "";
	document.f1.txtNamaOBSaksi.value = "";
	document.f1.txtIdSimatiSaksi.value = idSimati;
	document.f1.txtNoKPBaru1Saksi.value = "";
	document.f1.txtNoKPBaru2Saksi.value = "";
	document.f1.txtNoKPBaru3Saksi.value = "";
	document.f1.txtNoKPLamaSaksi.value = "";
	document.f1.socJenisKPLainSaksi.value = "";
	document.f1.txtNoKPLainSaksi.value = "";
	document.f1.txtNoSuratBeranakSaksi.value = "";
	document.f1.socStatusOB.value = "";
	document.f1.socJantinaSaksi.value = "";
	document.f1.socAgamaSaksi.value = "";
	document.f1.socWarganegaraSaksi.value = "";
	document.f1.txtUmurSaksi.value = "";
	document.f1.txdTarikhLahirSaksi.value = "";
	document.f1.txtAlamatTerakhir1Saksi.value = "";
	document.f1.txtAlamatTerakhir2Saksi.value = "";
	document.f1.txtAlamatTerakhir3Saksi.value = "";
	document.f1.txtPoskodSaksi.value = "";
	document.f1.txtBandarSaksi.value = "";
	document.f1.socNegeriSaksi.value = "";
	document.f1.txtCatatanSaksi.value = "";
	document.f1.socTarafKepentinganSaksi.value = "";
	document.f1.txdTarikhLahirSaksi.value = "";
	document.f1.txtNoKPBaru1Saksi.focus();
	}

function tambah_saksi(){
	
	if( document.f1.tambahsaksi.value == "simpan" ) 
	{
		input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {if(document.f1.txtNamaOBSaksi.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaSimati.focus(); 
			return; 
		}}
	document.f1.mode.value = "tambah_saksi";
	document.f1.command.value = "Saksi";
	document.f1.action = "";
	document.f1.submit();
	}

	if( document.f1.tambahsaksi.value == "Simpan" ) 
	{
    input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.mode.value = "simpan_saksi";
	document.f1.command.value = "Saksi";
	
	document.f1.action = "";
	document.f1.submit();
}
	
	}

	if( document.f1.tambahsaksi.value == "Kemaskini" ) 
	{

    

    document.f1.tambahsaksi.value = "Simpan";	

   document.f1.idsaksi.disabled = false;
document.f1.txtNamaOBSaksi.disabled = false;
document.f1.txtNoTeleponSaksi.disabled = false;
document.f1.txtIdSimatiSaksi.disabled = false;
document.f1.txtNoKPBaru1Saksi.disabled = false;
document.f1.txtNoKPBaru2Saksi.disabled = false;
document.f1.txtNoKPBaru3Saksi.disabled = false;
document.f1.txtNoKPLamaSaksi.disabled = false;
document.f1.socJenisKPLainSaksi.disabled = false;
document.f1.txtNoKPLainSaksi.disabled = false;

document.f1.socJantinaSaksi.disabled = false;
document.f1.socAgamaSaksi.disabled = false;
document.f1.socWarganegaraSaksi.disabled = false;


document.f1.txtAlamatTerakhir1Saksi.disabled = false;
document.f1.txtAlamatTerakhir2Saksi.disabled = false;
document.f1.txtAlamatTerakhir3Saksi.disabled = false;
document.f1.txtPoskodSaksi.disabled = false;
document.f1.txtBandarSaksi.disabled = false;
document.f1.socNegeriSaksi.disabled = false;
document.f1.txtCatatanSaksi.disabled = false;

	document.f1.txtNoKPBaru1Saksi.focus();
	
	
	}
	
	
}

function edit_item_saksi(idOb,nama_Ob,nokpbaru1,nokpbaru2,nokpbaru3,idSimati,nokplama,jeniskp,nokplain,idnegeri,noTel,jantina,alamat1,alamat2,alamat3,bandar,agama,catatan,warga,poskod) 
{

	document.f1.txtNoKPBaru1Saksi.focus();
	document.f1.tambahsaksi.value = "Kemaskini";
	
document.f1.idsaksi.value = idOb;



document.f1.txtNamaOBSaksi.value = nama_Ob;
document.f1.txtNoTeleponSaksi.value = noTel;
document.f1.txtIdSimatiSaksi.value = idSimati;
document.f1.txtNoKPBaru1Saksi.value = nokpbaru1;
document.f1.txtNoKPBaru2Saksi.value = nokpbaru2;
document.f1.txtNoKPBaru3Saksi.value = nokpbaru3;
document.f1.txtNoKPLamaSaksi.value = nokplama;
document.f1.socJenisKPLainSaksi.value = jeniskp;
document.f1.txtNoKPLainSaksi.value = nokplain;

document.f1.socJantinaSaksi.value = jantina;
document.f1.socAgamaSaksi.value = agama;
document.f1.socWarganegaraSaksi.value = warga;

document.f1.txtAlamatTerakhir1Saksi.value = alamat1;
document.f1.txtAlamatTerakhir2Saksi.value = alamat2;
document.f1.txtAlamatTerakhir3Saksi.value = alamat3;
document.f1.txtPoskodSaksi.value = poskod;
document.f1.txtBandarSaksi.value = bandar;
document.f1.socNegeriSaksi.value = idnegeri;
document.f1.txtCatatanSaksi.value = catatan;

document.f1.idsaksi.disabled = true;
document.f1.txtNamaOBSaksi.disabled = true;
document.f1.txtNoTeleponSaksi.disabled = true;

document.f1.txtNoKPBaru1Saksi.disabled = true;
document.f1.txtNoKPBaru2Saksi.disabled = true;
document.f1.txtNoKPBaru3Saksi.disabled = true;
document.f1.txtNoKPLamaSaksi.disabled = true;
document.f1.socJenisKPLainSaksi.disabled = true;
document.f1.txtNoKPLainSaksi.disabled = true;

document.f1.socJantinaSaksi.disabled = true;
document.f1.socAgamaSaksi.disabled = true;
document.f1.socWarganegaraSaksi.disabled = true;


document.f1.txtAlamatTerakhir1Saksi.disabled = true;
document.f1.txtAlamatTerakhir2Saksi.disabled = true;
document.f1.txtAlamatTerakhir3Saksi.disabled = true;
document.f1.txtPoskodSaksi.disabled = true;
document.f1.txtBandarSaksi.disabled = true;
document.f1.socNegeriSaksi.disabled = true;
document.f1.txtCatatanSaksi.disabled = true;




}










function WarisView() {
	document.f1.action = "";
	document.f1.mode.value = "Warisview";
	document.f1.command.value = "Waris";
	document.f1.submit();
}


function tarikh_waris_lapisan()
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh_lapisan";
	document.f1.command.value = "Waris";
	document.f1.submit();
}



function tarikh_waris()
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh";
	document.f1.command.value = "Waris";
	document.f1.submit();
}

function tarikh_waris_update()
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh_update";
	document.f1.command.value = "Waris";
	document.f1.submit();
}
function tarikh_waris_lapisan_update()
 {
	document.f1.action = "";
	document.f1.mode.value = "Waristarikh_lapisan_update";
	document.f1.command.value = "Waris";
	document.f1.submit();
}
function new_waris() {
	document.f1.action = "";
	document.f1.mode.value = "Newwaris";
	document.f1.command.value = "Waris";
	document.f1.submit();
}
function get_waris(idw) {
	document.f1.action = "";
	document.f1.mode.value = "Getwaris";
	document.f1.command.value = "Waris";
	document.f1.idwaris.value = idw;
	document.f1.submit();
}



function lapisan_sebelum() {
	document.f1.action = "";
	document.f1.mode.value = "Lapisan_sebelum";
	document.f1.command.value = "Waris";
	
	document.f1.submit();
}
function get_waris_lapisan(idw,idp) {
	document.f1.action = "";
	document.f1.mode.value = "GetwarisLapisan";
	document.f1.command.value = "Waris";
	document.f1.idwarislapis.value = idw;
    document.f1.idparentlapis.value = idp;
	document.f1.submit();
}
function get_waris_lapisan_X(idw,idp) {
	document.f1.action = "";
	document.f1.mode.value = "GetwarisLapisanX";
	document.f1.command.value = "Waris";
	document.f1.idwarislapisX.value = idw;
    document.f1.idparentlapisX.value = idp;
	document.f1.submit();
}
function lapisan_waris(idw) {
	document.f1.action = "";
	document.f1.mode.value = "Lapisan_berikut";
	document.f1.command.value = "Waris";
	document.f1.idwaris.value = idw;
	document.f1.submit();
}

function lapisan_waris_lapisan(idw) {
	document.f1.action = "";
	document.f1.mode.value = "Lapisan_berikut_lapisan";
	document.f1.command.value = "Waris";
	document.f1.idwarislapis.value = idw;
	document.f1.submit();
}


function lapisan_waris_sebelum(idw) {
	document.f1.action = "";
	document.f1.mode.value = "Lapisan_sebelum_lapisan";
	document.f1.command.value = "Waris";
	document.f1.txtIdParent.value = idw;
	document.f1.submit();
}

function tambah_lapisan_berikut(idw) {
	document.f1.action = "";
	document.f1.mode.value = "Tambah_lapisan_berikut";
	document.f1.command.value = "Waris";
	//document.f1.idwarislapis.value = idw;
	document.f1.submit();
}


function tambah_lapisan(idw) {
	document.f1.action = "";
	document.f1.mode.value = "Tambah_lapisan";
	document.f1.command.value = "Waris";
	document.f1.idwaris.value = idw;
	document.f1.submit();
}







function tambah_waris_lapisan_Simpan() {
	
	
	
	
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	if( document.f1.tambahwarislapisanSimpan.value == "Simpan" ) 
	{
		if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		
		if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		
	document.f1.mode.value = "tambah_waris_lapisan";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	}
}
	
	
	
	
	
	
}



















function tambah_waris_lapisan() {
	
	
	
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	
	if( document.f1.tambahwarislapisan.value == "Tambah" ) 
	{
		if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		
		if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		
	document.f1.mode.value = "tambah_waris_lapisan";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	}

	if( document.f1.tambahwarislapisan.value == "Simpan" ) 
	{
	document.f1.mode.value = "simpan_waris_lapisan";
	document.f1.command.value = "Waris";
	
	document.f1.action = "";
	document.f1.submit();

	
	}
   
    if( document.f1.tambahwarislapisan.value == "Kemaskini" ) 
	{

    document.f1.mode.value = "kemaskini_waris_lapisan";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	
	}
	
	
	
	}
	
	
}


function saksitambahbatal() {
	document.f1.reset();
	document.f1.idob.value = "";
	document.f1.txtNamaOBSaksi.value = "";
	document.f1.txtIdSimatiSaksi.value = idSimati;
	document.f1.txtNoKPBaru1Saksi.value = "";
	document.f1.txtNoKPBaru2Saksi.value = "";
	document.f1.txtNoKPBaru3Saksi.value = "";
	document.f1.txtNoKPLamaSaksi.value = "";
	document.f1.socJenisKPLainSaksi.value = "";
	document.f1.txtNoKPLainSaksi.value = "";
	document.f1.txtNoSuratBeranakSaksi.value = "";
	document.f1.socStatusOB.value = "";
	document.f1.socJantinaSaksi.value = "";
	document.f1.socAgamaSaksi.value = "";
	document.f1.socWarganegaraSaksi.value = "";
	document.f1.txtUmurSaksi.value = "";
	document.f1.txdTarikhLahirSaksi.value = "";
	document.f1.txtAlamatTerakhir1Saksi.value = "";
	document.f1.txtAlamatTerakhir2Saksi.value = "";
	document.f1.txtAlamatTerakhir3Saksi.value = "";
	document.f1.txtPoskodSaksi.value = "";
	document.f1.txtBandarSaksi.value = "";
	document.f1.socNegeriSaksi.value = "";
	document.f1.txtCatatanSaksi.value = "";
	document.f1.socTarafKepentinganSaksi.value = "";
	document.f1.txdTarikhLahirSaksi.value = "";
	document.f1.txtNoKPBaru1Saksi.focus();
	}
    
    function warisbatalupdate()
    {
      document.f1.mode.value = "kemaskini_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
    }



function tambah_waris_Simpan(){
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	if( document.f1.tambahwarisSimpan.value == "Simpan" ) 
	{
		if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		
		if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');

	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		
	document.f1.mode.value = "tambah_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	}

	
	}
	
}




function tambah_waris(){

input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	
	if( document.f1.tambahwaris.value == "Tambah" ) 
	{
		if(document.f1.txtNamaOBWaris.value == ""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		
		if(document.f1.socSaudaraWaris.value == ""){
			alert('Sila plih " Tali Persaudaraan " terlebih dahulu.');
	  		document.f1.txtNamaOBWaris.focus(); 
			return; 
		}
		
	document.f1.mode.value = "tambah_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	}

	if( document.f1.tambahwaris.value == "Simpan" ) 
	{
	document.f1.mode.value = "simpan_waris";
	document.f1.command.value = "Waris";
	
	document.f1.action = "";
	document.f1.submit();

	
	}
   
    }
   

	if( document.f1.tambahwaris.value == "Kemaskini" ) 
	{

    document.f1.mode.value = "kemaskini_waris";
	document.f1.command.value = "Waris";
	document.f1.action = "";
	document.f1.submit();
	
	
	}
	
	
}



function PemiutangView() {
	document.f1.action = "";
	document.f1.mode.value = "Pemiutangview";
	document.f1.command.value = "Pemiutang";
	document.f1.submit();
}

function Pemiutangtambahbatal() {
	document.f1.reset();
	
document.f1.txtNamaPemiutang.value="";



document.f1.txtNoAkaunPemiutang.value="";
document.f1.txtJumlahPemiutang.value="";


document.f1.txtNoKPBaru1Pemiutang.value="";
document.f1.txtNoKPBaru2Pemiutang.value="";
document.f1.txtNoKPBaru3Pemiutang.value="";
document.f1.txtNoKPLamaPemiutang.value="";
document.f1.socJenisKPLainPemiutang.value="";
document.f1.txtNoKPLainPemiutang.value="";


document.f1.txtAlamatTerakhir1Pemiutang.value="";
document.f1.txtAlamatTerakhir2Pemiutang.value="";
document.f1.txtAlamatTerakhir3Pemiutang.value="";
document.f1.txtPoskodPemiutang.value="";
document.f1.txtBandarPemiutang.value="";
document.f1.socNegeriPemiutang.value="";
document.f1.txtCatatanPemiutang.value="";
	document.f1.txtNoKPBaru1Pemiutang.focus();
	}


function tambah_pemiutang(){
	
	if( document.f1.tambahpemiutang.value == "simpan" ) 
	{
		input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {if(document.f1.txtNamaPemiutang.value==""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaPemiutang.focus(); 
			return; 
		}
		else
		{
	document.f1.mode.value = "tambah_pemiutang";
	document.f1.command.value = "Pemiutang";
	document.f1.action = "";
	document.f1.submit();
		}
        }
	}

	if( document.f1.tambahpemiutang.value == "Simpan" ) 
	{
    input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
    if(document.f1.txtNamaPemiutang.value==""){
			alert('Sila masukkan " Nama " terlebih dahulu.');
	  		document.f1.txtNamaPemiutang.focus(); 
			return; 
            }else
            {
	document.f1.mode.value = "simpan_pemiutang";
	document.f1.command.value = "Pemiutang";
	
	document.f1.action = "";
	document.f1.submit();
    }
    }

	
	}

	if( document.f1.tambahpemiutang.value == "Kemaskini" ) 
	{   

    document.f1.tambahpemiutang.value = "Simpan";
    	

document.f1.idpm.disabled = false;
document.f1.txtNamaPemiutang.disabled = false;



document.f1.txtNoAkaunPemiutang.disabled = false;
document.f1.txtJumlahPemiutang.disabled = false;

document.f1.txtIdSimatiPemiutang.disabled = false;
document.f1.txtNoKPBaru1Pemiutang.disabled = false;
document.f1.txtNoKPBaru2Pemiutang.disabled = false;
document.f1.txtNoKPBaru3Pemiutang.disabled = false;
document.f1.txtNoKPLamaPemiutang.disabled = false;
document.f1.socJenisKPLainPemiutang.disabled = false;
document.f1.txtNoKPLainPemiutang.disabled = false;


document.f1.txtAlamatTerakhir1Pemiutang.disabled = false;
document.f1.txtAlamatTerakhir2Pemiutang.disabled = false;
document.f1.txtAlamatTerakhir3Pemiutang.disabled = false;
document.f1.txtPoskodPemiutang.disabled = false;
document.f1.txtBandarPemiutang.disabled = false;
document.f1.socNegeriPemiutang.disabled = false;
document.f1.txtCatatanPemiutang.disabled = false;
		
	document.f1.txtNoKPBaru1Pemiutang.focus();
	
	
	}
	
	
}
       
function edit_item_pemiutang(idpm,idSimati,nama_Pg,jumlah,noakaun,nokpbaru1,nokpbaru2,nokpbaru3,nokplama,jeniskp,nokplain,idnegeri,alamat1,alamat2,alamat3,bandar,poskod,catatan) 
{

	document.f1.txtNoKPBaru1Pemiutang.focus();
	document.f1.tambahpemiutang.value = "Kemaskini";
	

document.f1.idpm.value=idpm;
document.f1.txtNamaPemiutang.value=nama_Pg;



document.f1.txtNoAkaunPemiutang.value=noakaun;
document.f1.txtJumlahPemiutang.value=jumlah;

document.f1.txtIdSimatiPemiutang.value=idSimati;
document.f1.txtNoKPBaru1Pemiutang.value=nokpbaru1;
document.f1.txtNoKPBaru2Pemiutang.value=nokpbaru2;
document.f1.txtNoKPBaru3Pemiutang.value=nokpbaru3;
document.f1.txtNoKPLamaPemiutang.value=nokplama;
document.f1.socJenisKPLainPemiutang.value=jeniskp;
document.f1.txtNoKPLainPemiutang.value=nokplain;


document.f1.txtAlamatTerakhir1Pemiutang.value=alamat1;
document.f1.txtAlamatTerakhir2Pemiutang.value=alamat2;
document.f1.txtAlamatTerakhir3Pemiutang.value=alamat3;
document.f1.txtPoskodPemiutang.value=poskod;
document.f1.txtBandarPemiutang.value=bandar;
document.f1.socNegeriPemiutang.value=idnegeri;
document.f1.txtCatatanPemiutang.value=catatan;






document.f1.idpm.disabled = true;
document.f1.txtNamaPemiutang.disabled = true;



document.f1.txtNoAkaunPemiutang.disabled = true;
document.f1.txtJumlahPemiutang.disabled = true;

document.f1.txtIdSimatiPemiutang.disabled = true;
document.f1.txtNoKPBaru1Pemiutang.disabled = true;
document.f1.txtNoKPBaru2Pemiutang.disabled = true;
document.f1.txtNoKPBaru3Pemiutang.disabled = true;
document.f1.txtNoKPLamaPemiutang.disabled = true;
document.f1.socJenisKPLainPemiutang.disabled = true;
document.f1.txtNoKPLainPemiutang.disabled = true;


document.f1.txtAlamatTerakhir1Pemiutang.disabled = true;
document.f1.txtAlamatTerakhir2Pemiutang.disabled = true;
document.f1.txtAlamatTerakhir3Pemiutang.disabled = true;
document.f1.txtPoskodPemiutang.disabled = true;
document.f1.txtBandarPemiutang.disabled = true;
document.f1.socNegeriPemiutang.disabled = true;
document.f1.txtCatatanPemiutang.disabled = true;








}



function getFormHa(){
	document.f1.command.value="harta_alih";
	document.f1.mode.value="tambah_harta";
	document.f1.eventStatus.value="0";
	document.f1.action="";
	document.f1.submit();
}

function getJenisHa(){
	document.f1.command.value="harta_alih";
	document.f1.mode.value="tambah_harta";
	document.f1.eventStatus.value="0";
	document.f1.action="";
	document.f1.submit();
}

function getSimpan(){
	if (isNan(document.f1.txtBilUnit.value)){
		alert("Sila masukkan nombor sahaja");
	}else{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="harta_alih";
		document.f1.mode.value="simpan_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
	}
	}
}
function edit_hartaalih(id3){
	document.f1.method="post";
	document.f1.command.value="harta_alih";
	document.f1.mode.value="edit_harta";
	document.f1.eventStatus.value="2";
	document.f1.idha.value=id3;
	document.f1.action="";
	document.f1.submit();
}
function getKemaskini(){
	document.f1.command.value="harta_alih";
	document.f1.mode.value="kemaskini_harta";
	document.f1.eventStatus.value="3";
	document.f1.action="";
	document.f1.submit();
}
function getHapus(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="harta_alih";
		document.f1.mode.value="hapus_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
	}
}
function getUpdate(){
	if (isNan(document.f1.txtBilUnit.value)){
		alert("Sila masukkan nombor sahaja");
	}else{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.command.value="harta_alih";
		document.f1.mode.value="update_harta";
		document.f1.eventStatus.value="2";
		document.f1.action="";
		document.f1.submit();
	}
	}
}
function getBatal(){
		document.f1.command.value="harta_alih";
		document.f1.mode.value="batal_harta";
		document.f1.eventStatus.value="1";
		document.f1.action="";
		document.f1.submit();
}


function negerichange(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="negerichange";
		document.f1.action="";
		document.f1.submit();
}
function negerichangeX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangeX";
		document.f1.action="";
		document.f1.submit();
}
function daerahchangeX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="daerahchangeX";
		document.f1.action="";
		document.f1.submit();
}
function negerichangeup(){
		
		document.f1.command.value="Htaam";
		document.f1.mode.value="negerichangeup";
		document.f1.action="";
		document.f1.submit();
}

function negerichangeupX(){
		
		document.f1.command.value="HtaamX";
		document.f1.mode.value="negerichangeupX";
		document.f1.action="";
		document.f1.submit();
}

function daerahchange(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="daerahchange";
		
		document.f1.action="";
		document.f1.submit();
}
function daerahchangeup(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="daerahchangeup";
		
		document.f1.action="";
		document.f1.submit();
}

function daerahchangeupX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="daerahchangeupX";
		
		document.f1.action="";
		document.f1.submit();
}

function add_HtaamX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="masukkanX";
		
		document.f1.action="";
		document.f1.submit();
}
function add_Htaam(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="masukkan";
		
		document.f1.action="";
		document.f1.submit();
}
function edit_Htaam(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="kemaskiniHtaam";
		
		document.f1.action="";
		document.f1.submit();
}
function edit_HtaamX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="kemaskiniHtaamX";
		
		document.f1.action="";
		document.f1.submit();
}
function save_Htaam(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="simpanHtaam";
		
		document.f1.action="";
	
		document.f1.submit();
}
function save_HtaamX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="simpanHtaamX";
		
		document.f1.action="";
	
		document.f1.submit();
}

function hapus_Htaam(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="hapusHtaam";		
		document.f1.action="";
		document.f1.submit();
}

function hapus_HtaamX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="hapusHtaamX";		
		document.f1.action="";
		document.f1.submit();
}
function kembali_Htaam(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="kembaliHtaam";
		
		document.f1.action="";
		document.f1.submit();
}
function kembali_HtaamX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="kembaliHtaamX";
		
		document.f1.action="";
		document.f1.submit();
}

function batal_Htaam(){
		document.f1.command.value="Htaam";
		document.f1.mode.value="batalHtaam";
		
		document.f1.action="";
		document.f1.submit();
}

function batal_HtaamX(){
		document.f1.command.value="HtaamX";
		document.f1.mode.value="batalHtaamX";
		
		document.f1.action="";
		document.f1.submit();
}
function getNilaiHartaKemaskini(){
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="kemaskini_nilai_harta";
	document.f1.eventStatus.value="4";
	document.f1.action="";
	document.f1.submit();
}
function getNilaiHartaSimpan(){
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="simpan_nilai_harta";
	document.f1.eventStatus.value="1";
	document.f1.action="";
	document.f1.submit();
}
function getNilaiHartaBatal(){
	document.f1.command.value="nilai_harta";
	document.f1.mode.value="batal_nilai_harta";
	document.f1.eventStatus.value="1";
	document.f1.action="";
	document.f1.submit();
}

<!--Begin
function initArray() {
this.length = initArray.arguments.length;
for (var i = 0; i < this.length; i++)
{this[i] = initArray.arguments[i];
   }
}
var isn04 = new initArray("4b","5b","8b","8b");
isn04[0] = "~01~11550~210.76~31.196~40.0395~50.002471~60.0009884~70.0002471~80.0000003861~9";
isn04[1] = "~00.0006452~11~20.006944~30.0007716~40.00002551~50.000001594~60.0000006377~70.0000001594~82.291e-10~9";
isn04[2] = "~00.09290~1144~21~30.1111~40.003673~50.0002296~60.00009183~70.00002296~83.587e-8~9";
isn04[3] = "~00.8361~11296~29~31~40.03306~50.002066~60.0008264~70.0002066~83.228e-7~9";
isn04[4] = "~025.29~139204~2272.25~330.25~41~50.0625~60.025~70.00625~89.766e-6~9";
isn04[5] = "~0404.7~1627264~24356~3484~416~51~60.4~70.1~80.00015625~9";
isn04[6] = "~01012~11568160~210890~31210~440~52.5~61~70.25~80.000390625~9";
isn04[7] = "~04047~16272640~243560~34840~4160~510~64~71~80.0015625~9";
isn04[8] = "~02589988~14013355318~227878400~33097600~4102400~56400~62560~7640~81~9";
function areCon() {
for (var i = 0; i < 9; i++) {
if (document.f1.arei[i].checked) {
arei = i;
areinm = document.f1.arei[i].value;
   }
}
for (var i = 0; i < 9; i++) {
if (document.f1.areo[i].checked) {
areo = i;
areonm = document.f1.areo[i].value;
   }
}
useri = document.f1.areinp.value;
if (useri == 0) {
useri = 1;
document.f1.areinp.value = useri;
}
mulstr = isn04[arei];
picker = "~" + areo;
ps = mulstr.indexOf(picker);
areo++;
picker = "~" + areo;
ps1 = mulstr.indexOf(picker);
mulstr = mulstr.substring((ps + 2),ps1);
ps = (useri * mulstr);
picker = "";
picker += ps
ps1 = picker.indexOf(".");
if (ps1 > -1) {
ps = ps + .000001;
picker = "";
picker += ps;
ps2 = picker.indexOf("e");
if (ps2 < 0) {
picker = picker.substring(0,(ps1 + 6));
}
if (ps2 == 0 || ps2 > 0) {
ps3 = picker.indexOf("00000");
if (ps3 > 0) {
picker = picker.substring(0,ps3 + 1) + picker.substring(ps2,picker.length);
      }
   }
}
picker = useri + " " + areinm + " = " + picker + " " + areonm
document.f1.areout.value = picker;
}
// End -->

</script>
