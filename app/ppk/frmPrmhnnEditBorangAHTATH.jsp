<html>
<head>
<title>Untitled Document</title>
<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
}
.style42 {color: #0000FF}
.style43 {font-size: 10px; }
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }

-->
</style>
</head>

<body>
<table width="100%" border="0">
<tr>
<td>
 <input type="hidden" name="hitButt">
 <input type="hidden" name="mode">
 <input type="hidden" name="action">
 <input type="hidden" name="idpermohonansimati" value="$idpermohonansimati">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimatix = $list.idSimati)
<input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
<input name="idPermohonan" type="hidden"  value="$id"/>
<input name="idPemohon" type="hidden"  value="$idPemohon"/>
 
<input name="idtemp" type="hidden"  value="$id"/>
#end
 <input type="hidden" name="idSimati" value="$idSimati">
</td>
</tr>
  <tr>
    <td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  <li class="TabbedPanelsTab" tabindex="0" onClick="SimatiView()">PERMOHONAN</li>
   <li class="TabbedPanelsTab" tabindex="0" onClick="HAview()">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HAview()">HARTA ALIH</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
	
	<div id="TabbedPanels2" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView()">HARTA TAK ALIH (ADA HAKMILIK)</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamViewX()">HARTA TAK ALIH (TIADA HAKMILIK)</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
<table width="100%"  border="0">
                                  #if($show_htaa_add_table=="yes")
          <tr>
                                    <td><table width="100%" border="0">
                                        <tr>
                                                                                   
                                            <td>
                                            <fieldset><legend>MAKLUMAT HARTA TAK ALIH SIMATI (TIADA HAKMILIK)</legend>
                                            
                                            <table width="100%" align="left">
                                              <tr>
                                                <td><span class="style36"><font class="mandatory" color="#FF0000">*</font>&nbsp;Sila pilih salah satu Jenis Perjanjian</span></td>
                                              </tr>
											  <tr>
                                                <td><span class="style36">
                                                  <input type="radio" name="radioHtaamViewX1" value="1"  $checked1 onClick="HtaamViewX1('1','0','0','1')"/>
                                                  Perjanjian Jual Beli</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                        		<input type="radio" name="radioHtaamViewX1" value="2"   $checked2 onClick="HtaamViewX2('1','0','0','1')"/>
                                                  Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                              <input type="radio" name="radioHtaamViewX1" value="3"  $checked3 onClick="HtaamViewX3('1','0','0','1')"/>
                                                  Kepentingan Lain- lain</span></td>
                                              </tr>
                                            </table>
                                            
                                            </fieldset>
                                            
                                          </td>
                                        </tr>
                                    </table></td>
                                  </tr>
                                  <tr>
                                    <td>
                                   <fieldset>
                                   <table width="100%" border="0">
                                      <tr>
                                        <td width="80%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td width="30%"><div align="right" class="style43"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td width="70%">#foreach($listnegpomo in $listnegeri)
                                                #if($negeri==$listnegpomo.id_Negeri)
                                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                                #end 
                                                #end
                                                #if($negeri!="")
                                                <select name="socNegeriHtaamX" $readmodenegeri onChange="negerichangeX('1','0','0','1')" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                        <option value="$negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
				                                 #foreach($listnegpomo in $listnegeri)
						                                  #if($negeri!=$listnegpomo.id_Negeri)
						                                         <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
						                                  #end    
					                               #end
                                                </select>
                                                #else
                                                <select name="socNegeriHtaamX" $readmodenegeri onChange="negerichangeX('1','0','0','1')" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                  <option value="">Sila Pilih Negeri</option>
                                   #foreach($listnegpomo in $listnegeri)
                                                   <option value="$listnegpomo.id_Negeri">$!listnegpomo.kod_Negeri - $!listnegpomo.nama_Negeri</option>
 	                               #end
                                                 </select>
                                                #end </td>
                                            </tr>
                                            <tr>

                                              <td><div align="right" class="style43"><font class="mandatory" color="#FF0000">*</font>&nbsp;Daerah</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listdaerah in $listDaerahbyNegeri)
                                                #if($daerah==$listdaerah.id)
                                                
                                                #set($listDaerahbyNegeriK=$listdaerah.kod)
                                                #set($listDaerahbyNegeriN=$listdaerah.nama)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($daerah!="")
                                               
                                                <select name="socDaerahHtaamX" $readmodedaerah onChange="daerahchangeX('1','0','0','1')" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                        <option value="$daerah">$!listDaerahbyNegeriK - $!listDaerahbyNegeriN</option>

                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                                  <option value="$listdaerah.id">$!listdaerah.kod - $!listdaerah.nama</option>
                                  #end    
	                               #end
                                                </select>
                                                #else
                                                <select name="socDaerahHtaamX" $readmodedaerah onChange="daerahchangeX('1','0','0','1')" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                  <option value="">Sila Pilih Daerah</option>
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                                  <option value="$listDaerah.id">$!listDaerah.kod - $!listDaerah.nama</option>
	                               #end
                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><div align="right" class="style43"><font class="mandatory" color="#FF0000">*</font>&nbsp;Mukim</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listmukim in $listMukimbyDaerah)
                                                #if($mukim==$listmukim.id)
                                                
                                                #set($listMukimbyDaerahK=$listmukim.kod)
                                                #set($listMukimbyDaerahN=$listmukim.nama)
                                                
                                                
                                                
                                                #end 
                                                #end
                                                #if($mukim!="")
                                               
                                                <select name="socMukimHtaamX" $readmodemukim id="socMukimHtaamX" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                  <option value="$mukim">$!listMukimbyDaerahK - $!listMukimbyDaerahN</option>

                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($mukim!=$listmukim.id)
                                                  <option value="$listmukim.id">$!listmukim.kod - $!listdaerah.nama</option>
                                  #end    
	                               #end

                                                </select>
                                                #else
                                                <select name="socMukimHtaamX" $readmodemukim id="socMukimHtaamX" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                  <option value="">Sila Pilih Mukim</option>
                                  #foreach($listmukim in $listMukimbyDaerah)
                                                  <option value="$listmukim.id">$!listmukim.kod - $!listmukim.nama</option>
	                               #end
                                               </select>
                                                #end </td>
                                            </tr>
											 #if($radioHtaamViewX1=="1")
                                          <tr>
                                            <td><div align="right" class="style43">Alamat Harta</div></td>
                                            <td><div align="right">:</div></td>
                                            <td><input name="txtAlamatHarta1HtaamX" type="text" id="textfield57" value="$!alamathta1" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                          </tr>
                                          <tr>
                                            <td><span class="style43"></span></td>
                                            <td>&nbsp;</td>
                                            <td><input name="txtAlamatHarta2HtaamX" type="text" id="textfield58" size="34" $readmode value="$!alamathta2" style="text-transform:uppercase;" onBlur="uppercase()" /></td>
                                          </tr>
                                          <tr>
                                            <td><span class="style43"></span></td>
                                            <td>&nbsp;</td>
                                            <td><input name="txtAlamatHarta3HtaamX" type="text" id="textfield59" value="$!alamathta3" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right" class="style43">Poskod</div></td>
                                            <td><div align="right">:</div></td>
                                            
                                            <td><input name="txtPoskodHartaHtaamX" type="text" id="textfield60" $readmode value="$!poskodhta" size="5" maxlength="5" style="text-transform:uppercase;" onBlur="validLength1()" onKeyUp="javascript:validatePoskod(this,this.value)"/></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right" class="style43">Bandar</div></td>
                                           <td><div align="right">:</div></td>
                                            <td><!--<input name="txtBandarHartaHtaamX" type="text" id="textfield61" value="$bandarhta" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>-->$selectBandarxTetap</td>
                                          </tr>
                                          <tr>
                                            <td><div align="right" class="style43">No Perjanjian</div></td>
                                           <td><div align="right">:</div></td>
                                            <td><input name="txtNoPerjanjianHtaamX" type="text" id="textfield62" value="$!noperjanjian" size="34" maxlength="40" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right" class="style43">Tarikh Perjanjian</div></td>
                                           <td><div align="right">:</div></td>
                                            <td><span class="style36">
                                              <input name="txtTarikhPerjanjianHtaamX" id="txdTarikhMatiWaris3" type="text" $readmode value="$!tarikhperjanjian" size="10" maxlength="10"  onblur="trans_date(this.value)"  />
                                              <a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');"><img border="0" src="../img/calendar.gif"/> &nbsp;<span class="style52">format : dd/mm/yyyy</span>                                           </td>
                                          </tr>
                                          #end
                                            <tr>
                                              <td><div align="right" class="style43">Nama Pemaju</div></td>
                                             <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNamaPemajuHtaamX" type="text" id="txtNamaPemajuHtaamX" value="$!namapemaju" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right" class="style43">Alamat Pemaju</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtAlamatPemaju1HtaamX" type="text" id="textfield20" value="$!alamatpemaju1" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()" />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><span class="style43"></span></td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju2HtaamX" type="text" id="textfield53" value="$!alamatpemaju2" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><span class="style43"></span></td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju3HtaamX" type="text" id="textfield54" value="$!alamatpemaju3" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right" class="style43">Poskod</div></td>
                                             <td><div align="right">:</div></td>
                                            
                                              <td><input name="txtPoskodPemaju1HtaamX" type="text" id="textfield55" value="$!poskodpemaju" size="5" maxlength="5" $readmode style="text-transform:uppercase;" onBlur="validLength()" onKeyUp="javascript:validatePoskod(this,this.value)"/></td>
                                            </tr>
                                            
                                            <tr>
                                              <td><div align="right" class="style43">Negeri</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>$selectNegeriTetap</td>
                                            </tr>
                                            <tr>
                                              <td><div align="right" class="style43">Bandar</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>$selectBandarTetap</td>
                                            </tr>
                                            <tr>
                                              <td ><div align="right" class="style43">Luas</div></td>
                                              <td valign="top"><div align="right">:</div></td>
                                              <td>                                              
                                              #foreach($listluashta in $listluas)
	                                                #if($jenisluas==$listluashta.id)
		                                                #set($listluasK=$listluashta.kod)
		                                                #set($listluasN=$listluashta.nama)
	                                                #end 
                                                #end
                                                #if($jenisluas!="")
                                                </select><input type="text" name="txtLuasAsalHtaamX" id="txtLuasAsalHtaamUp5" value="$!luasasal" onKeyUp="javascript:validateIC(this,this.value,'txtLuasAsalHtaamX')" style="text-transform:uppercase;" onBlur="uppercase()" maxlength="10"/>&nbsp;<select name="socJenisLuasHtaamX" $readmode id="socJenisLuasHtaamX" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                  <option value="0" >SILA PILIH</option>
												  <option value="$jenisluas">$!listluasK - $!listluasN</option>
                                   #foreach($listluashta in $listluas)
                                 
                                  #if($jenisluas!=$listluashta.id)
                                                   <option value="$listluashta.id">$!listluashta.kod - $!listluashta.nama</option>
                                  #end    
	                               #end
                                                
                                                #else
                                                <input type="text" name="txtLuasAsalHtaamX" id="txtLuasAsalHtaamUp6" $readmode value="$luasasal" style="text-transform:uppercase;" onBlur="validateModal(this,this.value,'txtLuasAsalHtaamX');" onKeyUp="javascript:validatePoskod(this,this.value)" maxlength="10"/>&nbsp;<select name="socJenisLuasHtaamX" $readmode id="socJenisLuasHtaamX" style="width: 225px;text-transform:uppercase;" onBlur="uppercase()">
                                                  <option value="0" >SILA PILIH </option>
                                    #foreach($listluashta in $listluas)
                                                  <option value="$listluashta.id">$!listluashta.kod - $!listluashta.nama</option>
	                               #end
                                               </select>&nbsp;
                                                #end </td>
                                            </tr>
                                           <tr>
                                              <td width="29%"><div align="right" class="style43">Bahagian Simati</div></td>
                                              
                                             <td width="1%"><div align="right">:</div></td>
                                              <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati7" $readmode value="$basimati" size="5" maxlength="5" onKeyUp="javascript:validateIC(this,this.value,'txtBahagianSimati1X')" />
                                                /
                                                <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati8" value="$bbsimati" size="5" maxlength="5" $readmode onKeyUp="javascript:validateIC(this,this.value,'txtBahagianSimati2X')" /></td>
</tr>
<tr>
                                              <td><div align="right" class="style43">Nilai Harta (RM)</div></td>
                                            <td><div align="right">:</div></td>
                                              <td><input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaa3" maxlength="7" size="10" style="text-align: right;" $readmode value="$nilai_Hta_memohon" onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" /></td>
</tr>                                                                              
                                          #if($radioHtaamViewX1=="2")
<tr>
                                                        <td><div align="right" class="style43">Nama Rancangan</div></td>
                                  <td><div align="right">:</div></td>
                                            <td><label>
                                                          <input name="txtNamaRancanganHtaamX" type="text" id="textfield63" value="$!namarancangan" size="34" $readmodevalue="$namarancangan" style="text-transform:uppercase;" onBlur="uppercase()"/>
                                                        </label></td>
                                          </tr>
                                                      <tr>
                                                        <td><div align="right" class="style43">No ROH</div></td>
                                                        <td><div align="right">:</div></td>
                                                        <td><input name="txtNoRohHtaamX" type="text" id="txtNoRohHtaamX" value="$!noroh" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                                      </tr>
                                                      <tr>
                                                        <td><div align="right" class="style43">Lot ID</div></td>
                                                        <td><div align="right">:</div></td>
                                                        <td><input name="txtLotIdHtaamX" type="text" id="txtLotIdHtaamX" $readmode value="$!nolot" style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                                      </tr>
                                          #end
										#if( $radioHtaamViewX1=="3")
										#if ($listam.jeniskepentingan!= "")
											#set ($jeniskepentingan = $listam.jeniskepentingan)
										#else
											#set ($jeniskepentingan = "")
										#end
                                          		<tr>
                                                   <td valign="top"><div align="right" class="style43">Jenis Kepentingan</div></td>
                                                  <td valign="top"><div align="right">:</div></td>
                                       			   <td><textarea name="txtKepentinganHtaamX" cols="31" rows="4" $readmode style="text-transform:uppercase;">$!jeniskepentingan</textarea></td>
                                          		</tr>
                                            #end                                          
                                        </table></td>
                                      </tr>
									  <tr><td colspan="2"><table>
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan label bertanda <font color="#ff0000">*</font> 
                      diisi.</i></td>
                    </tr>
                    </table></td></tr>
                                    </table>
                                   
                                   
                                   </fieldset>
                                    
                                    
                                    </td>
                                  </tr>
                                  #end
                                    #if($show_htaa_update_table=="yes")
                                      #foreach($listam in $listHTAXid)
                                   <tr>
                                   <td>
                                   <fieldset><legend>MAKLUMAT HARTA TAK ALIH (TIADA HAKMILIK)</legend>
                                   #if($listam.flag=="1")
                                   		#set ($checked1="checked")
                                   		#set ($checked2="")
                                   		#set ($checked3="")
                                   #elseif ($listam.flag=="2")
                                   		#set ($checked1="")
                                   		#set ($checked2="checked")
                                   		#set ($checked3="")
                                   #elseif ($listam.flag=="3")
                                  		#set ($checked1="")
                                   		#set ($checked2="")
                                   		#set ($checked3="checked")
                                   #end
                                   <table width="100%" align="left">
                                              <tr>
                                                <td><span class="style36">
                                                  <input type="radio" name="radioHtaamViewX1" value="1"  $checked1 $readmodecheckbox onClick="HtaamViewX1('1','0','0','1')"/>
                                                  Perjanjian Jual Beli</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                        		<input type="radio" name="radioHtaamViewX1" value="2"  $checked2 $readmodecheckbox onClick="HtaamViewX2('1','0','0','1')"/>
                                                  Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                              <input type="radio" name="radioHtaamViewX1" value="3" $checked3 $readmodecheckbox onClick="HtaamViewX3('1','0','0','1')"/>
                                                  Kepentingan Lain- lain</span></td>
                                              </tr>
                                            </table>
                                   </fieldset>
                               
                                     </td>
                                  </tr>  
                                    
                                  <tr>
                                  
                                    <td>
                                  <fieldset>
                                  <table width="100%">
                                        <tr>
                                          <td width="80%" valign="top"><table width="100%">
                                              <tr>
                                                <td width="29%"><div align="right" class="style43"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td width="70%">#foreach($listnegpomo in $listnegeri)
                                          
                                          #if($listam.negeri==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          #end 
                                          #end
                                          #if($listam.negeri!="")
                                          <select name="socNegeriHtaamX" $readmodenegeri onChange="negerichangeupX('1','0','0','1')" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                            <option value="$listam.negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                            
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listam.negeri!=$listnegpomo.id_Negeri)
                                    
	                               
                                              
                                            <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          </select>
#else
<select name="socNegeriHtaamX" $readmodenegeri onChange="negerichangeupX('1','0','0','1')" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
  <option value="">Sila Pilih Negeri</option>
  
                                              
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               
                                              
  <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            
</select>
#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43"><font class="mandatory" color="#FF0000">*</font>&nbsp;Daerah</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>#foreach($listdaerah in $listdaerah)
                                          
                                          #if($listam.daerah==$listdaerah.id)
                                          
                                          #set($listDaerahbyNegeriK=$listdaerah.kod)
                                          #set($listDaerahbyNegeriN=$listdaerah.nama)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.daerah!="")
                                          <select name="socDaerahHtaamX" $readmodedaerah onChange="daerahchangeupX('1','0','0','1')" style="text-transform:uppercase;  width: 225px;" onBlur="uppercase()">
                                            <option value="$listam.daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                            
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($listam.daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                            <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          </select>
#else
<select name="socDaerahHtaamX" $readmodedaerah onChange="daerahchangeupX('1','0','0','1')" style="text-transform:uppercase;  width: 225px;" onBlur="uppercase()">
  <option value="">Sila Pilih Daerah</option>
  
  
                                              
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                 
                                
	                               
                                              
  
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

</select>
#end </td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43"><font class="mandatory" color="#FF0000">*</font>&nbsp;Mukim</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>#foreach($listmukim in $listmukim)
                                          
                                          #if($listam.mukim==$listmukim.id)
                                          
                                          #set($listMukimbyDaerahK=$listmukim.kod)
                                          #set($listMukimbyDaerahN=$listmukim.nama)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.mukim!="")
                                          <select name="socMukimHtaamX" $readmodemukim  style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                            <option value="$listam.mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                   #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($listam.mukim!=$listmukim.id)
                                    
	                               
                                              
                                            
                                            
                                            
                                            <option value="$listmukim.id">$listmukim.kod - $listdaerah.nama</option>
                                            
                                            
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          
                                          
                                          </select>
#else
<select name="socMukimHtaamX" $readmodemukim style="text-transform:uppercase;  width: 225px;" onBlur="uppercase()">
  <option value="">Sila Pilih Mukim</option>
  
  
  
                                              
                                  #foreach($listmukim in $listMukimbyDaerah)
                                 
                                
	                               
                                              
  
  
  <option value="$listmukim.id">$listmukim.kod - $listmukim.nama</option>
  
  
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            


</select>
#end </td>
                                              </tr>
											  #if($listam.flag=="1")
                                              <tr>
                                                <td><div align="right" class="style43">Alamat Harta</div></td>
                                               <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtAlamatHarta1HtaamX" type="text" id="textfield44" value="$listam.alamathta1" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style43"></span></td>
                                                <td>&nbsp;</td>
                                                <td><input name="txtAlamatHarta2HtaamX" type="text" id="textfield45" value="$listam.alamathta2" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style43"></span></td>
                                                <td>&nbsp;</td>
                                                <td><input name="txtAlamatHarta3HtaamX" type="text" id="textfield46" value="$listam.alamathta3" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43">Poskod</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtPoskodHartaHtaamX" type="text" id="textfield47" $readmode value="$listam.poskodhta" maxlength="5" size="5" style="text-transform:uppercase;" onBlur="validLength1()" onKeyUp="javascript:validatePoskod(this,this.value)"/></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43">Bandar</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><!--<input name="txtBandarHartaHtaamX" type="text" id="textfield48" value="$listam.bandarhta" size="34"  maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>-->$selectBandarxTetap</td>
                                              </tr>
                                              
                                              <tr>
                                                <td><div align="right" class="style43">No Perjanjian</div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtNoPerjanjianHtaamX" type="text" id="textfield49" value="$listam.noperjanjian" size="34" maxlength="40" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43">Tarikh Perjanjian</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><span class="style36">
               <input name="txtTarikhPerjanjianHtaamX" id="txtTarikhPerjanjianHtaamX" type="text" $readmode value="$listam.tarikhperjanjian" size="10" maxlength="10" onBlur="return ValidateForm1()"  />
                                             #if ($readmode=="")
                                                <a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>&nbsp;<span class="style52">format : dd/mm/yyyy</span>
                                                #end
                                                </td>
                                              </tr>
                                              #end
                                              <tr>
                                                <td><div align="right" class="style43">Nama Pemaju</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><label>
                                                  <input name="txtNamaPemajuHtaamX" type="text" id="txtNamaPemajuHtaamX" style="text-transform:uppercase;" onBlur="uppercase()" value="$listam.namapemaju" size="34" $readmode />
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43">Alamat Pemaju</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju1HtaamX" type="text" id="textfield21" style="text-transform:uppercase;" onBlur="uppercase()" value="$listam.alamatpemaju1" size="34" $readmode />
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style43"></span></td>
                                                <td>&nbsp;</td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju2HtaamX" type="text" id="textfield22" value="$listam.alamatpemaju2" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style43"></span></td>
                                                <td>&nbsp;</td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju3HtaamX" type="text" id="textfield23" value="$listam.alamatpemaju3" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43">Poskod</div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtPoskodPemaju1HtaamX" type="text" id="textfield24" value="$listam.poskodpemaju" size="5" maxlength="5"  $readmode style="text-transform:uppercase;" onBlur="validLength()" onKeyUp="javascript:validatePoskod(this,this.value)"/></td>
                                              </tr>
                                             
                                              <tr>
                                                <td><div align="right" class="style43">Negeri</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><!--#foreach($listnegpomo in $listnegeri)
                                          
                                          #if($listam.negeripemaju==$listnegpomo.id_Negeri)
                                          
                                          #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                          #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                          
                                          
                                          
                                          #end 
                                          #end
                                          #if($listam.negeripemaju!="")
                                          <select name="socNegeriPemajuHtaamX" $readmode id="socNegeriPemajuHtaamX" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                            <option value="0">Sila Pilih Negeri</option>
											<option value="$listam.negeripemaju">$!negerikodpemoP - $!negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                  #if($listam.negeripemaju!=$listnegpomo.id_Negeri)
                                            <option value="$listnegpomo.id_Negeri">$!listnegpomo.kod_Negeri - $!listnegpomo.nama_Negeri</option>
                                  #end    
	                               #end
                                          </select>
#else
<select name="socNegeriPemajuHtaamX" $readmode id="socNegeriPemajuHtaamX" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()" >
  <option value="0">Sila Pilih Negeri</option>
                                  #foreach($listnegpomo in $listnegeri)
  <option value="$listnegpomo.id_Negeri">$!listnegpomo.kod_Negeri - $!listnegpomo.nama_Negeri</option>
	                               #end
</select>
#end-->$selectNegeriTetap</td>
                                              </tr>
                                               <tr>
                                                <td><div align="right" class="style43">Bandar</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><!--<input name="txtBandarPemaju1HtaamX" type="text" id="textfield43" value="$listam.bandarpemaju" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()" />-->$selectBandarTetap</td>
                                              </tr>
											  <!--
                                              <tr>
                                                <td width="29%"><div align="right" class="style43">Bahagian Simati</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati5" $readmode value="$listam.basimati" size="5" maxlength="5" onkeyup="javascript:validateIC(this,this.value,'txtBahagianSimati1X')" /> / <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati6" value="$listam.bbsimati" size="5" maxlength="5" $readmode onkeyup="javascript:validateIC(this,this.value,'txtBahagianSimati2X')" /></td>
                                              </tr>
											  -->
                                              <tr>
                                                <td><div align="right" class="style43">Luas </div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>#foreach($listluashta in $listluas)
                                          
                                          #if($listam.jenisluas==$listluashta.id)
                                          
                                          #set($listluasK=$listluashta.kod)
                                          #set($listluasN=$listluashta.nama)
                                          #end 
                                          #end
                                          #if($listam.jenisluas!="")
                                          <input name="txtLuasAsalHtaamX" type="text" id="txtLuasAsalHtaamUp3" value="$listam.luasasal" size="15" maxlength="15" $readmode style="text-transform:uppercase;" onBlur="uppercase()" onKeyUp="javascript:validateIC(this,this.value,'txtLuasAsalHtaamX')" />&nbsp;<select name="socJenisLuasHtaamX" class="autoselect" $readmodedaerah id="socJenisLuasHtaamUp2" style="text-transform:uppercase;" onBlur="uppercase()">
                                            <option value="0">Sila Pilih</option>
											<option value="$listam.jenisluas" selected>$!listluasK - $!listluasN</option>
                                  #foreach($listluashta in $listluas)
                                  #if($listam.jenisluas!=$listluashta.id)
                                            <option value="$listluashta.id" style="text-transform:uppercase;" onBlur="uppercase()">$!listluashta.kod - $!listluashta.nama</option>

                                  #end    
	                               #end

                                          </select>
                                          
#else
<input name="txtLuasAsalHtaamX" type="text" id="txtLuasAsalHtaamUp4" value="$luas" size="15" $readmode onKeyUp="javascript:validateIC(this,this.value,'txtLuasAsalHtaamX')" />&nbsp;
<select name="socJenisLuasHtaamX" class="autoselect" $readmodedaerah id="socJenisLuasHtaamUp2" style="text-transform:uppercase;" onBlur="uppercase()">
  <option value="0">Sila Pilih</option>

                                    #foreach($listluashta in $listluas)

  <option value="$listluashta.id">$!listluashta.kod - $!listluashta.nama</option>

	                               #end

</select>
							#if ($listam.luasasal!="")
                                		#set ($value1 = $Util.formatDecimal($listam.luasasal))
                                		#set ($luas = $EkptgUtil.RemoveSymbol($value1))
                            #else
                            			#set ($luas = "")
                            #end

#end </td>
                                              </tr>
                                              
                              
                                              <tr>
                                              <td width="29%"><div align="right" class="style43">Bahagian Simati</div></td>
                                              
                                             <td width="1%"><div align="right">:</div></td>
                                              <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati7" $readmode value="$basimati" size="5" maxlength="5" onKeyUp="javascript:validateIC(this,this.value,'txtBahagianSimati1X')" />
                                                /
                                                <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati8" value="$bbsimati" size="5" maxlength="5" $readmode onKeyUp="javascript:validateIC(this,this.value,'txtBahagianSimati2X')" /></td>
</tr> 
											   <tr>
                                              <td><div align="right" class="style43">Nilai Harta (RM)</div></td>
                                            <td><div align="right">:</div></td>
                                              <td>
                                               #if ($show_htaa_update_table=="yes" && $readmode != "")
                             
                             						#if($listam.nilai_Hta_memohon !="")
                                              
                                              <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaa3" maxlength="7" size="10" style="text-align: right;" $readmode value="$Util.formatDecimal($listam.nilai_Hta_memohon)" onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" />
                                              #else
                                               <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaa3" maxlength="7" size="10" style="text-align: right;" $readmode value="" onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" />
                                               #end
                                               #else
                                               #set($nilai_Hta_memohon = $nilai_Hta_memohon)
                                               <input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaa3" maxlength="7" size="10" style="text-align: right;" $readmode value="$listam.nilai_Hta_memohon" onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" />
                                               #end
                                              
                                              </td>
                                            </tr>
											
                                              
                                              
                                              #if( $listam.flag=="2")
                                              <tr>
                                                <td><div align="right" class="style43">Nama Rancangan</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><label>
                                                  <input name="txtNamaRancanganHtaamX" type="text" id="textfield50" value="$!listam.namarancangan" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43">No ROH</div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtNoRohHtaamX" type="text" id="textfield51" value="$!listam.noroh" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43">Lot ID</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtLotIdHtaamX" type="text" id="textfield52" value="$!listam.nolot" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                              </tr>
                                              #end
                                              #if( $listam.flag=="3")
                                          		<tr>
                                                   <td valign="top"><div align="right" class="style43">Jenis Kepentingan</div></td>
                                                   <td valign="top"><div align="right">:</div></td>
                                       			   <td><textarea name="txtKepentinganHtaamX" cols="31" rows="4" $readmode style="text-transform:uppercase;">$!listam.jeniskepentingan</textarea></td>
                                          		</tr>
                                            #end  
                                          </table></td>
                                        </tr>
                                        <tr><td colspan="2"><table>
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan label bertanda <font color="#ff0000">*</font> 
                      diisi.</i></td>
                    </tr>
                    </table></td></tr>
                                    </table>
                                  
                                  
                                  </fieldset>
                                    
                                    </td>
                                  </tr>
                                  #end
                                  
                                 
                                  #end
                                    <input type="hidden" name="idhtaamXid" value="$idhtaam" />
                                    #if($show_button=="yes")
          <tr>
                                    <td align="center"> #if($show_simpan_add_htaam=="yes")
                                      <input type="button" name="cmdSimpan" id="cmdSimpan" $readmode value="Simpan" onClick="add_HtaamX('1','0','0','1')"/>#end 
                                      #if($show_kemaskini_htaam=="yes")
                                      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="edit_HtaamX('1','0','0','1')" />
                                      #end
                                      #if($show_save_update_htaam=="yes")
                                      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="save_HtaamX('1','0','0','1')"/>
                                      
                                      #end
                                      #if($show_batal_add_htaam=="yes")
                                      <input type="reset" name="cmdBatal" id="cmdBatal" value="Batal" onClick="HtaamViewX('1','0','0','1')"/>
                                      #end
                                      
                                      #if($show_hapus_htaam=="yes")
                                      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="hapus_HtaamX('1','0','0','1')"/>
                                      #end
                                       #if($show_batal_update_htaam=="yes")
                                      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onClick="HtaamViewX('1','0','0','1')" />
                                      #end
                                      </td>
                                  </tr>
                                   #end
                                  <tr>
                                  <td>
                                  <fieldset><legend>SENARAI HARTA TAK ALIH (TIADA HAKMILK)</legend>
                                  #if($listHTAX.size()!=0 )    
                                  <table width="100%">
                             
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <td width="20%"><div align="center">NEGERI</div></td>
                                        <td width="20%"><div align="center">DAERAH</div></td>
                                        <td width="20%"><div align="center">MUKIM</div></td>
                                        <!--<td width="10%"><div align="center">NO HAK MILIK</div></td>
                                        <td width="10%"><div align="center">NO PT/NO LOT</div></td>
                                        <td width="5%"><div align="center">BHGN SIMATI</div></td>-->
                                      </tr>
                                      #set($plko=0)
                                      #foreach($listam in $listHTAX)
                                      #set($plko=$plko+1)
                                      #if($plko%2!=0)
  <tr bgcolor="white">
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
    <td class="row1"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$listam.namanegeri</a></div></td>
    <td class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$listam.namadaerah</div></td>
    <td class="row1"><div style="text-transform:uppercase;" onblur="uppercase()">$listam.namamukim</div></td>
    <!--<td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td class="row1"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>-->
    <!-- #else
   <td class="row1"> </td>
    #end--> </tr>
                                      #else
  <tr class="table_header">
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
    <td class="row2"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$listam.namanegeri</a></div></td>
    <td class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$listam.namadaerah</div></td>
    <td class="row2"><div style="text-transform:uppercase;" onblur="uppercase()">$listam.namamukim</div></td>
    <!--<td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.noHakmilik</div></td>
    <td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.nopt</div></td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <<td class="row2"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>--.
    #else
    <!--<<td class="row2"></td>
    #end--> </tr>
                                      #end
                                      #end
                                    </table>
                                    #else
                                      <table width="100%">
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <td width="20%"><div align="center">NEGERI</div></td>
                                        <td width="20%"><div align="center">DAERAH</div></td>
                                        <td width="20%"><div align="center">MUKIM</div></td>
                                        <td width="10%"><div align="center">NO HAK MILIK</div></td>
                                        <td width="10%"><div align="center">NO PT/NO LOT</div></td>
                                        <!--<td width="5%"><div align="center">BHGN SIMATI</div></td>-->
                                      </tr>
                                      </table>
                                      <table width="100%">
                                      <tr bgcolor="white">
                                        <td align="left">Tiada Rekod
                                        </td>
                                      </tr>
                                      </table>
                                    #end
                                  <input type="hidden" name="idhtaam" value="$listamid.idhta" />
                                    <input type="hidden" name="idhtaamid" value="$idhtaam" />
                                  
                                  
                                
                    </fieldset></table>	
					</div>
  </div>
</div>
<p>&nbsp;</p>
	</div>
	<div class="TabbedPanelsContent"></div>
  </div>
</div>
</td>
</tr>
</table>
<script>
<!-- TAB -->
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function HtaamViewX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="HtaamviewX";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.submit();
}
function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}

function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_nilai_harta";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("nilai_harta");
	document.${formName}.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Penghutangview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
}
function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemiutangview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.submit();
}

function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Warisview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}
function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemohonview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.submit();
}
function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Simatiview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Simati");
	document.${formName}.submit();
}


function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Saksiview";

	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	doAjaxCall${formName}("Saksi");
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Pentingview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	doAjaxCall${formName}("Penting");
	document.${formName}.submit();
}

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

function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	document.${formName}.hitButt.value="kembali_simati";
	document.${formName}.action.value="";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;	
}

function cancelwaris() {
input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.reset();
	document.${formName}.txtNoKPBaru1Waris.focus();
	}
}

<!-- HTATH -->
function nktambah() {
document.${formName}.reset();
document.${formName}.mode.value="add_new";
doAjaxCall${formName}("HtaamX");
document.${formName}.submit();
}

function get_htaam(idhtaam)
{
	document.${formName}.method="post";
	document.${formName}.idhtaamXid.value=idhtaam;
	document.${formName}.mode.value="getHtaamX";
	document.${formName}.tabIdatas.value="1";
	document.${formName}.tabIdtengah.value="0";
	document.${formName}.tabIdbawah.value="0";
	document.${formName}.tabIdtepi.value="1";
	document.${formName}.action.value="";
doAjaxCall${formName}("HtaamX");
	document.${formName}.submit();
}

function get_X(idhtaam)
{
	document.${formName}.method="post";
	document.${formName}.idhtaamXid.value=idhtaam;
	document.${formName}.mode.value="getHtaamX";
	document.${formName}.tabIdatas.value="1";
	document.${formName}.tabIdtengah.value="0";
	document.${formName}.tabIdbawah.value="0";
	document.${formName}.tabIdtepi.value="1";
	document.${formName}.action.value="";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.submit();
	
}

function HtaamViewX1(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="HtaamviewX1";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function HtaamViewX2(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="HtaamviewX2";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function HtaamViewX3(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="HtaamviewX3";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function negerichangeX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="negerichangeX";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;
		document.${formName}.action.value="";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
}

function daerahchangeX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="daerahchangeX";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;
		document.${formName}.action.value="";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
}

function negerichangeupX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){		
		document.${formName}.method="post";
		document.${formName}.mode.value="negerichangeupX";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;
		document.${formName}.action.value="";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
}

function daerahchangeupX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="daerahchangeupX";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;		
		document.${formName}.action.value="";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
}

function add_HtaamX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
var currentTime = new Date();
	
	/*var str1  = document.getElementById("txtTarikhPerjanjianHtaamX").value;
	var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);*/

	if (document.${formName}.radioHtaamViewX1.value == "") {
		alert("Sila pilih jenis perjanjian");
	}
	else if (document.${formName}.socNegeriHtaamX.value=="") {
		alert("Sila pilih negeri");
		socNegeriHtaamX.focus();
	}
	else if (document.${formName}.socDaerahHtaamX.value=="") {
		alert("Sila pilih daerah");
		socDaerahHtaamX.focus();
	} 
	else if (document.${formName}.socMukimHtaamX.value=="") {
		alert("Sila pilih mukim");
		socMukimHtaamX.focus();
	}
	else if (document.${formName}.radioHtaamViewX1[0].checked == "checked" && document.${formName}.txtPoskodHartaHtaamX.value != "" && document.${formName}.txtPoskodHartaHtaamX.value.length < 5) 
	{
		alert("Sila masukkan nombor poskod alamat harta dengan lengkapnya");
		txtPoskodHartaHtaamX.focus();
		
	} 
	else if (document.${formName}.txtPoskodPemaju1HtaamX.value != "" && document.${formName}.txtPoskodPemaju1HtaamX.value.length < 5) {
		alert("Sila masukkan nombor poskod alamat pemaju dengan lengkapnya");
		txtPoskodPemaju1HtaamX.focus();
		
	}
	else if (document.${formName}.txtLuasAsalHtaamX.value!="" && document.${formName}.socJenisLuasHtaamX.value=="0"){
		alert("Sila pilih jenis luas.");
		socJenisLuasHtaamX.focus();
	}
	/*else if (document.${formName}.txtTarikhPerjanjianHtaamX.value != "" && isDate(str1)==false){
		txtTarikhPerjanjianHtaamX.focus();
	}
	else if (document.${formName}.txtTarikhPerjanjianHtaamX.value != "" && date1 > currentTime){
		alert("Sila pastikan tarikh perjanjian tidak melebihi dari tarikh hari ini.");
		txtTarikhPerjanjianHtaamX.focus();
	}*/
	else{
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.mode.value="masukkanX";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;		
		document.${formName}.action.value="";
		document.${formName}.submit(); 
		}
	}
}
		
function edit_HtaamX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.mode.value="kemaskiniHtaamX";		
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;		
		document.${formName}.action.value="";
		document.${formName}.submit();
}

function save_HtaamX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	var currentTime = new Date();
	
	/*var str1  = document.getElementById("txtTarikhPerjanjianHtaamX").value;
	 var dt1   = parseInt(str1.substring(0,2),10);
     var mon1  = parseInt(str1.substring(3,5),10)-1;
     var yr1   = parseInt(str1.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);*/
	
	if (document.${formName}.socNegeriHtaamX.value=="") {
		alert("Sila pilih negeri");
		socNegeriHtaamX.focus();
	} 
	else if (document.${formName}.socDaerahHtaamX.value=="") {
		alert("Sila pilih daerah");
		socDaerahHtaamX.focus(); 
	} 
	else if (document.${formName}.socMukimHtaamX.value=="") {
		alert("Sila pilih mukim");
		socMukimHtaamX.focus();
	}
	else if (document.${formName}.txtLuasAsalHtaamX.value!="" && document.${formName}.socJenisLuasHtaamX.value=="0"){
		alert("Sila pilih jenis luas.");
		socJenisLuasHtaamX.focus();
	}
	/*else if (document.${formName}.txtTarikhPerjanjianHtaamX.value != "" && isDate(str1)==false){
		txtTarikhPerjanjianHtaamX.focus();
	}
	else if (document.${formName}.txtTarikhPerjanjianHtaamX.value != "" && date1 > currentTime){
		alert("Sila pastikan tarikh perjanjian tidak melebihi dari tarikh hari ini.");
		txtTarikhPerjanjianHtaamX.focus();
	}*/
	else{
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="post";
			doAjaxCall${formName}("HtaamX");	
			document.${formName}.mode.value="simpanHtaamX";		
			document.${formName}.tabIdatas.value=tabIdatas;
			document.${formName}.tabIdtengah.value=tabIdtengah;
			document.${formName}.tabIdbawah.value=tabIdbawah;
			document.${formName}.tabIdtepi.value=tabIdtepi;		
			document.${formName}.action.value="";
			document.${formName}.submit();
		}

	}

}

function hapus_HtaamX(){
input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.mode.value="hapusHtaamX";		
		document.${formName}.action.value="";
		document.${formName}.submit();
		}else
		{
		return;
		}
}
function kembali_HtaamX(){
		document.${formName}.method="post";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.mode.value="kembaliHtaamX";
		document.${formName}.action.value="";	
		document.${formName}.submit();
}
function batal_HtaamX(){
		document.${formName}.method="post";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.mode.value="batalHtaamX";
		document.${formName}.action.value="";	
		document.${formName}.submit();
}

function validLength(){
	if (document.${formName}.txtPoskodPemaju1HtaamX.value!="" && document.${formName}.txtPoskodPemaju1HtaamX.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodPemaju1HtaamX.focus();
	}
}

function validLength1(){
	if (document.${formName}.txtPoskodHartaHtaamX.value!="" && document.${formName}.txtPoskodHartaHtaamX.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodHartaHtaamX.focus();
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


document.${formName}.txtTarikhPerjanjianHtaamX.value = trans;

}
else
{
return;
}

}


function onChangeBandarTetap(){
	if (document.${formName}.radioHtaamViewX1[0].checked){
		document.${formName}.method="post";
		document.${formName}.radioHtaamViewX1[0].value = 1;
		document.${formName}.mode.value="onChangeBandarTetaphtath";
		document.${formName}.tabIdatas.value="0";
		document.${formName}.tabIdtengah.value="1";
		document.${formName}.tabIdbawah.value="0";	
		document.${formName}.tabIdtepi.value="0";
		document.${formName}.action.value="";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
	} else if (document.${formName}.radioHtaamViewX1[1].checked){
		document.${formName}.method="post";
		document.${formName}.radioHtaamViewX1[1].value = 2;
		document.${formName}.mode.value="onChangeBandarTetaphtath";
		document.${formName}.tabIdatas.value="0";
		document.${formName}.tabIdtengah.value="1";
		document.${formName}.tabIdbawah.value="0";	
		document.${formName}.tabIdtepi.value="0";
		document.${formName}.action.value="";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
	} else if (document.${formName}.radioHtaamViewX1[2].checked){
		document.${formName}.method="post";
		document.${formName}.radioHtaamViewX1[2].value = 3;
		document.${formName}.mode.value="onChangeBandarTetaphtath";
		document.${formName}.tabIdatas.value="0";
		document.${formName}.tabIdtengah.value="1";
		document.${formName}.tabIdbawah.value="0";	
		document.${formName}.tabIdtepi.value="0";
		document.${formName}.action.value="";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
	}
	
}

function onChangeBandarTetaphtaam(){
	document.${formName}.mode.value="onChangeBandarTetaphtath";
	document.${formName}.tabIdatas.value="0";
    document.${formName}.tabIdtengah.value="1";
    document.${formName}.tabIdbawah.value="0";	
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
	doAjaxCall${formName}("HtaamX");
}
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:1});
//-->
</script>
</body>
</html>
