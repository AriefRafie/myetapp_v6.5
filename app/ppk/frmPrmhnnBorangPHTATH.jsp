<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}
body {
	background-color: #FFFFFF;
}
.style38 {font-size: 10px}
.style41 {color: #000000}
.style43 {color: #000000}
.style42 {color: #0000FF}
-->
</style>
<body>
<table width="100%" border="0">
    <tr>
        <td>
        <input type="hidden" name="hitButt">
        <input type="hidden" name="mode">
        <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
        <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
        <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
        <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
        <input type="hidden" name="idpermohonansimati" value="$idpermohonansimati">
          <input name="idPermohonan" type="hidden"  value="$idPermohonan"/>
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
#foreach ($fail in $DetailPemohon)
	#set ($nofail1 = $fail.nofaillama)
    #set ($idfail2 = $fail.idfail)
    #set ($nofail2 = $fail.nofail)
	#set ($keterangan = $fail.keterangan)
    #set ($seksyen = $fail.seksyen)
	#set ($tarikhMohon = $fail.tarikhmohon)
	#set ($pemohon = $fail.namaPemohon)
    #set ($namasimati = $fail.namasimati)
    #set ($idpermohonansimatilama = $fail.idpermohonansimatilama)
     <input name="idSimati" type="hidden"  value="$fail.idSimati"/>
     <input name="idpermohonansimatilama" type="hidden" value="$fail.idpermohonansimatilama" />
#end
<fieldset>
<legend>PEMBAHAGIAN PUSAKA KECIL</legend>
    <fieldset>
<legend>MAKLUMAT UNTUK BORANG P</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
      #if ($idStatus == "0" || $idStatus == "160" || $idStatus == "171")  
       <tr>
          <td width="35%" style="text-transform:uppercase; "><div align="right">No Rujukan Lama :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style42">$!nofail1</div></td>
        </tr>
      #else
        <tr>
          <td width="35%" style="text-transform:uppercase; "><div align="right">No Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style42">$!nofail2
            <input type="hidden" name="idfail2" value="$idfail2" />
          </div></td>
        </tr>
      #end   
      <tr>
          <td style="text-transform:uppercase;" valign="top"><div align="right">Status Fail :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$!keterangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">17</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left"><span class="style42">$!tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style42">$!namasimati</span>
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$!pemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
          </div></td>
        </tr>
		
      </table>
    </div></td>
  </tr>
</table>
</fieldset>  
</fieldset> 
</td>
</tr>
<tr>
<td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
   <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView()">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HAview()">HARTA ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="PengesahanView()">PENGESAHAN PERMOHONAN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
      <div id="TabbedPanels2" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView()">HARTA TAK ALIH( ADA HAKMILIK )</li>
          <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamViewX()">HARTA TAK ALIH( TAK ADA HAKMILIK )</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent">
<!-- HTA UNTUK INSERT BARU-->
#if($show_htaa_add_table=="yes")
<fieldset><legend>MAKLUMAT HARTA TAK ALIH SIMATI (TIADA HAKMILIK)</legend>

                                            <table width="97%" align="left">
                                              <tr>
                                                <td><span class="style36"><font class="mandatory" color="#FF0000">*</font>&nbsp;Sila pilih salah satu Jenis Perjanjian</span></td>
                                              </tr>
											  <tr>
                                                <td><span class="style36">
<input type="radio" name="radioHtaamViewX1" value="1" id="radioHtaamViewX1" $checked1 onClick="javascript:HtaamViewX1();"/>
                                                  Perjanjian Jual Beli</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
<input type="radio" name="radioHtaamViewX1" value="2" id="radioHtaamViewX1" $checked2 onClick="javascript:HtaamViewX2();"/>
                                                  Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
<input type="radio" name="radioHtaamViewX1" value="3" id="radioHtaamViewX1" $checked3 onClick="javascript:HtaamViewX3();"/>
                                                  Kepentingan Lain- lain</span></td>
                                              </tr>
                                            </table>
                                            </fieldset>
                                        
                                          
                                    <fieldset>
                                   <table width="97%" border="0">
                                      <tr>
                                        <td width="80%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td width="30%"><div align="right" class="style44"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td width="70%">#foreach($listnegpomo in $listnegeri)
                                                #if($negeri==$listnegpomo.id_Negeri)
                                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                                #end 
                                                #end
                                                #if($negeri!="")
                                                <select name="socNegeriHtaamX" $readmodenegeri onChange="javascript:negerichangeX();" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                        <option value="$negeri">$negeriketeranganpemoP</option>
				                                 #foreach($listnegpomo in $listnegeri)
						                                  #if($negeri!=$listnegpomo.id_Negeri)
						                                         <option value="$listnegpomo.id_Negeri">$listnegpomo.nama_Negeri</option>
						                                  #end    
					                               #end
                                                </select>
                                                #else
                                                <select name="socNegeriHtaamX" $readmodenegeri onChange="javascript:negerichangeX();" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                  <option value="">Sila Pilih Negeri</option>
                                   #foreach($listnegpomo in $listnegeri)
                                                   <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
 	                               #end
                                                 </select>
                                                #end </td>
                                            </tr>
                                            <tr>

                                              <td><div align="right" class="style44"><font class="mandatory" color="#FF0000">*</font>&nbsp;Daerah</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listdaerah in $listDaerahbyNegeri)
                                                    #if($daerah==$listdaerah.id)
                                                        #set($listDaerahbyNegeriK=$listdaerah.kod)
                                                        #set($listDaerahbyNegeriN=$listdaerah.nama)
                                                    #end 
                                                  #end
                                                #if($daerah!="")
                                               
                                                <select name="socDaerahHtaamX" $readmodedaerah onChange="javascript:daerahchangeX();" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                        <option value="$daerah">$!listDaerahbyNegeriK - $!listDaerahbyNegeriN</option>

                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                                  <option value="$listdaerah.id">$!listdaerah.kod - $!listdaerah.nama</option>
                                  #end    
	                               #end
                                                </select>
                                                #else
                                                <select name="socDaerahHtaamX" $readmodedaerah onChange="javascript:daerahchangeX();" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                  <option value="">Sila Pilih Daerah</option>
                                  #foreach($listDaerah in $listDaerahbyNegeri)
                                                  <option value="$listDaerah.id">$!listDaerah.kod - $!listDaerah.nama</option>
	                               #end
                                                </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td><div align="right" class="style44"><font class="mandatory" color="#FF0000">*</font>&nbsp;Mukim</div></td>
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
                                             <!--IF PERJANJIAN NO 1-->
                                            #if($radioHtaamViewX1=="1")
                                          <tr>
                                            <td><div align="right" class="style44">Alamat Harta</div></td>
                                            <td><div align="right">:</div></td>
                                            <td><input name="txtAlamatHarta1HtaamX" type="text" id="textfield57" value="$!alamathta1" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                          </tr>
                                          <tr>
                                            <td><span class="style44"></span></td>
                                            <td>&nbsp;</td>
                                            <td><input name="txtAlamatHarta2HtaamX" type="text" id="textfield58" size="34" $readmode value="$!alamathta2" style="text-transform:uppercase;" onBlur="uppercase()" /></td>
                                          </tr>
                                          <tr>
                                            <td><span class="style44"></span></td>
                                            <td>&nbsp;</td>
                                            <td><input name="txtAlamatHarta3HtaamX" type="text" id="textfield59" value="$!alamathta3" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right" class="style44">Poskod</div></td>
                                            <td><div align="right">:</div></td>
                                            
                                            <td><input name="txtPoskodHartaHtaamX" type="text" id="textfield60" $readmode value="$!poskodhta" size="5" maxlength="5" style="text-transform:uppercase;" onBlur="validLength1()" onKeyUp="javascript:validatePoskod(this,this.value)"/></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right" class="style44">Bandar</div></td>
                                           <td><div align="right">:</div></td>
                                            <td>$selectBandarxTetap</td>
                                          </tr>
                                          <tr>
                                            <td><div align="right" class="style44">No Perjanjian</div></td>
                                           <td><div align="right">:</div></td>
                                            <td><input name="txtNoPerjanjianHtaamX" type="text" id="textfield62" value="$!noperjanjian" size="34" maxlength="40" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                          </tr>
                                          <tr>
                                            <td><div align="right" class="style44">Tarikh Perjanjian</div></td>
                                           <td><div align="right">:</div></td>
                                            <td><span class="style36">
                                              <input name="txtTarikhPerjanjianHtaamX" id="txtTarikhPerjanjianHtaamX" type="text" $readmode value="$!tarikhperjanjian" size="10" maxlength="10"  onblur="specialcharecter(this.value)"  />
<a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');"><img border="0" src="../img/calendar.gif"/> &nbsp;<span class="style52">format : dd/mm/yyyy</span>                                           </td>
                                          </tr>
                                          #end
                                          <!--TAMAT PERJANJIAN NO 1-->
                                            
                                             <tr>
                                              <td><div align="right" class="style44">Nama Pemaju</div></td>
                                             <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNamaPemajuHtaamX" type="text" id="txtNamaPemajuHtaamX" value="$!namapemaju" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right" class="style44">Alamat Pemaju</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtAlamatPemaju1HtaamX" type="text" id="txtAlamatPemaju1HtaamX" value="$!alamatpemaju1" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()" />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><span class="style44"></span></td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju2HtaamX" type="text" id="txtAlamatPemaju2HtaamX" value="$!alamatpemaju2" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><span class="style44"></span></td>
                                              <td>&nbsp;</td>
                                              <td><label>
                                                <input name="txtAlamatPemaju3HtaamX" type="text" id="txtAlamatPemaju3HtaamX" value="$!alamatpemaju3" size="34" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right" class="style44">Poskod</div></td>
                                             <td><div align="right">:</div></td>
                                              <td><input name="txtPoskodPemaju1HtaamX" type="text" id="txtPoskodPemaju1HtaamX" value="$!poskodpemaju" size="5" maxlength="5" $readmode style="text-transform:uppercase;" onBlur="validLength()" onKeyUp="javascript:validatePoskod(this,this.value)"/></td>
                                            </tr>
                                            <tr>
                                              <td><div align="right" class="style44">Negeri</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>$selectNegeriTetap</td>
                                            </tr>
                                            <tr>
                                              <td><div align="right" class="style44">Bandar</div></td>
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
                                                <input type="text" name="txtLuasAsalHtaamX" id="txtLuasAsalHtaamUp5" value="$!luasasal" onKeyUp="javascript:validateIC(this,this.value,'txtLuasAsalHtaamX')" style="text-transform:uppercase;" onBlur="uppercase()" maxlength="10"/>&nbsp;
    <select name="socJenisLuasHtaamX" $readmode id="socJenisLuasHtaamX" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()">
                                                  <option value="0" >SILA PILIH</option>
												  <option value="$jenisluas">$!listluasN</option>
                                   #foreach($listluashta in $listluas)
                                 
                                  #if($jenisluas!=$listluashta.id)
                                                   <option value="$listluashta.id">$!listluashta.nama</option>
                                  #end    
	                               #end
                                                #else
                                                <input type="text" name="txtLuasAsalHtaamX" id="txtLuasAsalHtaamUp6" $readmode value="$!luasasal" style="text-transform:uppercase;" onBlur="validateModal(this,this.value,'txtLuasAsalHtaamX');" onKeyUp="javascript:validatePoskod(this,this.value)" maxlength="10"/>&nbsp;<select name="socJenisLuasHtaamX" $readmode id="socJenisLuasHtaamX" style="width: 225px;text-transform:uppercase;" onBlur="uppercase()">
                                                  <option value="0" >SILA PILIH </option>
                                    #foreach($listluashta in $listluas)
                                                  <option value="$listluashta.id">$!listluashta.nama</option>
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
                                              <td><div align="right" class="style43">Anggaran Nilai Harta (RM)</div></td>
                                            <td><div align="right">:</div></td>
                                              <td><input name="txtNilaiTarikhMohonHtaaX" type="text" id="txtNilaiTarikhMohonHtaa3" maxlength="7" size="10" style="text-align: right;" $readmode value="$nilai_Hta_memohon" onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" /></td>
</tr>
                                          #if($radioHtaamViewX1=="2")
                                          <tr>
                                                        <td><div align="right" class="style44">Nama Rancangan</div></td>
                                                        <td><div align="right">:</div></td>
                                            <td><label>
                                                          <input name="txtNamaRancanganHtaamX" type="text" id="textfield63" value="$!namarancangan" size="34" $readmodevalue="$namarancangan" style="text-transform:uppercase;" onBlur="uppercase()"/>
                                                        </label></td>
                                          </tr>
                                                      <tr>
                                                        <td><div align="right" class="style44">No ROH</div></td>
                                                        <td><div align="right">:</div></td>
                                                        <td><input name="txtNoRohHtaamX" type="text" id="txtNoRohHtaamX" value="$!noroh" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/></td>
                                                      </tr>
                                                      <tr>
                                                        <td><div align="right" class="style44">Lot ID</div></td>
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
                                                   <td valign="top"><div align="right" class="style44">Jenis Kepentingan</div></td>
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
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan membuat pengesahan penghantaran pada tab PENGESAHAN PERMOHONAN.</i></td>
                    </tr>
                    </table></td></tr></table>
                  
               </fieldset>
#end
<!--END FORM INSERT HTATH BARU-->
<!--FORM UPDATE HTATH-->
#if($show_htaa_update_table=="yes")
    #foreach($listam in $listHTAXid)
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
                                   <table width="97%" align="left">
                                              <tr>
                                                <td><span class="style36">
                                                  <input type="radio" name="radioHtaamViewX1" value="1"  $checked1 $readmodecheckbox $readmodenegeri onClick="HtaamViewX1('1','0','0','1')"/>
                                                  Perjanjian Jual Beli</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                        		<input type="radio" name="radioHtaamViewX1" value="2"  $checked2 $readmodecheckbox $readmodenegeri onClick="HtaamViewX2('1','0','0','1')"/>
                                                  Pegangan Di Bawah Akta Tanah (Kawasan Penempatan Berkelompok 1960)</span></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style36">
                                              <input type="radio" name="radioHtaamViewX1" value="3" $checked3 $readmodecheckbox $readmodenegeri onClick="HtaamViewX3('1','0','0','1')"/>
                                                  Kepentingan Lain- lain</span></td>
                                              </tr>
                                            </table>
    </fieldset>
    <br>
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
                                          <select name="socNegeriHtaamX" $readmodenegeri onChange="negerichangeupX('1','0','0','1')" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()" >
                                            <option value="$listam.negeri">$negerikodpemoP - $negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($listam.negeri!=$listnegpomo.id_Negeri)
                                            <option value="$listnegpomo.id_Negeri">$listnegpomo.kod_Negeri - $listnegpomo.nama_Negeri</option>
                                  #end    
	                               #end
                                          </select>
#else
<select name="socNegeriHtaamX" $readmodenegeri onChange="negerichangeupX('1','0','0','1')" style="text-transform:uppercase; width: 225px;" onBlur="uppercase()" >
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
                                          <select name="socDaerahHtaamX" $readmodedaerah onChange="daerahchangeupX('1','0','0','1')" style="text-transform:uppercase;  width: 225px;" onBlur="uppercase()" >
                                            <option value="$listam.daerah">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                            
                                            
                                              
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($listam.daerah!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                            <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                            
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                          </select>
#else
<select name="socDaerahHtaamX" $readmodedaerah onChange="daerahchangeupX('1','0','0','1')" style="text-transform:uppercase;  width: 225px;" onBlur="uppercase()" >
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
                                          <select name="socMukimHtaamX" $readmodemukim  style="text-transform:uppercase; width: 225px;" onBlur="uppercase()" >
                                            <option value="$listam.mukim">$listMukimbyDaerahK - $listMukimbyDaerahN</option>
                                   #foreach($listmukim in $listMukimbyDaerah)
                                 
                                  #if($listam.mukim!=$listmukim.id)

                                            <option value="$listmukim.id">$listmukim.kod - $listdaerah.nama</option>

                                  #end    
	                               #end

                                          </select>
#else
<select name="socMukimHtaamX" $readmodemukim style="text-transform:uppercase;  width: 225px;" onBlur="uppercase()" >
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
                                                <td>$selectBandarxTetap</td>
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
               <input name="txtTarikhPerjanjianHtaamX" id="txtTarikhPerjanjianHtaamX" type="text" $readmode value="$listam.tarikhperjanjian" size="10" maxlength="10" onBlur="javascript:specialcharecter(this.value)"  />
                                              #if ($readmode=="")  <a href="javascript:displayDatePicker('txtTarikhPerjanjianHtaamX',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>&nbsp;<span class="style52">format : dd/mm/yyyy</span>
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
                                                  <input name="txtAlamatPemaju2HtaamX" type="text" id="textfield22" value="$!listam.alamatpemaju2" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><span class="style43"></span></td>
                                                <td>&nbsp;</td>
                                                <td><label>
                                                  <input name="txtAlamatPemaju3HtaamX" type="text" id="textfield23" value="$!listam.alamatpemaju3" size="34" maxlength="25" $readmode style="text-transform:uppercase;" onBlur="uppercase()"/>
                                                </label></td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43">Poskod</div></td>
                                                <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td><input name="txtPoskodPemaju1HtaamX" type="text" id="textfield24" value="$!listam.poskodpemaju" size="5" maxlength="5"  $readmode style="text-transform:uppercase;" onBlur="validLength()" onKeyUp="javascript:validatePoskod(this,this.value)"/></td>
                                              </tr>
                                             
                                              <tr>
                                                <td><div align="right" class="style43">Negeri</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>$selectNegeriTetap</td>
                                              </tr>
                                               <tr>
                                                <td><div align="right" class="style43">Bandar</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>$selectBandarTetap</td>
                                              </tr>
                                              <tr>
                                                <td><div align="right" class="style43">Luas</div></td>
                                                 <td width="1%"><div align="right" class="style43">:</div></td>
                                                <td>#foreach($listluashta in $listluas)
                                          
                                          #if($listam.jenisluas==$listluashta.id)
                                          
                                          #set($listluasK=$listluashta.kod)
                                          #set($listluasN=$listluashta.nama)
                                          #end 
                                          #end
                                          #if($listam.jenisluas!="")
                                          <input name="txtLuasAsalHtaamX" type="text" id="txtLuasAsalHtaamUp3" value="$!listam.luasasal" size="15" maxlength="15" $readmode style="text-transform:uppercase;" onBlur="uppercase()" onKeyUp="javascript:validateIC(this,this.value,'txtLuasAsalHtaamX')" />&nbsp; <select name="socJenisLuasHtaamX" $readmode id="socJenisLuasHtaamUp2" style="text-transform:uppercase;" onBlur="uppercase()" >
                                            <option value="0">Sila Pilih</option>
											<option value="$listam.jenisluas" selected>$!listluasN</option>
                                  #foreach($listluashta in $listluas)
                                  #if($listam.jenisluas!=$listluashta.id)
                                            <option value="$listluashta.id" style="text-transform:uppercase;" onBlur="uppercase()">$!listluashta.nama</option>

                                  #end    
	                               #end

                                          </select>
                                          
#else
<input name="txtLuasAsalHtaamX" type="text" id="txtLuasAsalHtaamUp4" value="$!luas" size="15" $readmode onKeyUp="javascript:validateIC(this,this.value,'txtLuasAsalHtaamX')" />&nbsp;
<select name="socJenisLuasHtaamX" $readmodenegeri id="socJenisLuasHtaamUp2" style="text-transform:uppercase;" onBlur="uppercase()" >
  <option value="0">Sila Pilih</option>

                                    #foreach($listluashta in $listluas)

  <option value="$listluashta.id">$!listluashta.nama</option>

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
                                              <td width="70%"><input name="txtBahagianSimati1X" type="text" id="txtBahagianSimati7" $readmode value="$!listam.basimati" size="14" maxlength="14" onKeyUp="javascript:validateIC(this,this.value,'txtBahagianSimati1X')"  style="text-align: right;" />
                                                /
                                                <input name="txtBahagianSimati2X" type="text" id="txtBahagianSimati8" value="$!listam.bbsimati" size="14" maxlength="14" $readmode onKeyUp="javascript:validateIC(this,this.value,'txtBahagianSimati2X')" />
                                                <input name="basimati" type="hidden" value="$basimati" />
                                                
                                               </td>
</tr>
<tr>
                                              <td><div align="right" class="style43">Anggaran Nilai Harta (RM)</div></td>
                                            <td><div align="right">:</div></td>
                                              <td>
                                               #if ($show_htaa_update_table=="yes" && $readmode != "")
                             
                             	#if($listam.nilai_Hta_memohon !="")
                                  	 <input name="txtNilaiTarikhMohonHtaaX" id="txtNilaiTarikhMohonHtaaX" type="text" style="width: 225px; "  $readmode value="$Util.formatDecimal($listam.nilai_Hta_memohon)" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)" />
       
                                  #else
                                  	<input name="txtNilaiTarikhMohonHtaaX" id="txtNilaiTarikhMohonHtaaX" type="text" style="width: 225px; "  $readmode value="" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)" />
                                  #end
                                  #else
                                  #set($nilai_Hta_memohon = $nilai_Hta_memohon)
                        <input name="txtNilaiTarikhMohonHtaaX" id="txtNilaiTarikhMohonHtaaX" type="text" style="width: 225px; "  $readmode value="$listam.nilai_Hta_memohon" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)" />
                        <input name="readmode" type="hidden" value="$readmode">
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
                                        <tr><td colspan="2">
		<table>
                    <tr>
                    <td><i><font color=red style=font-size:11px>Perhatian</font> <font style=font-size:11px>: Sila pastikan label bertanda *</font> <font style=font-size:11px>diisi.</font></i></td>
                    </tr>
                    <tr>
                      <td><i><font color=red style=font-size:11px>Perhatian</font> <font style=font-size:11px>: Sila pastikan membuat pengesahan penghantaran di bahagian PENGESAHAN PERMOHONAN</font><font style=font-size:11px></font></i></td>
                    </tr>
                    </table></td></tr>
                                    </table>
                                  </fieldset>
    #end
#end
<!--END FORM UPDATE HTATH-->
						<table width="97%">
                        <tr><td align="center">
						  <input type="hidden" name="idhtaamXid" value="$idhtaam" />
                          #if($show_button=="yes")
									##if ($idStatus == "160")
                                    #if($show_simpan_add_htaam=="yes")
                                      <input type="button" name="cmdSimpan" id="cmdSimpan" $readmode value="Simpan" onClick="add_HtaamX('1','0','0','1')"/>
                                      <input type="reset" name="cmdBatal" id="cmdBatal" value="Batal"/>
                                      #end
                                      #if($show_kemaskini_htaam=="yes")
                                      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="edit_HtaamX('1','0','0','1')" />
                                      #end
                                      #if($show_save_update_htaam=="yes")
                                      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="save_HtaamX('1','0','0','1')"/>
                                      #end
                                      #if($show_hapus_htaam=="yes")
                                      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="hapus_HtaamX('1','0','0','1')"/>
                                      #end
                                       #if($show_batal_update_htaam=="yes")
                                      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onClick="HtaamViewX('1','0','0','1')" />
                                      #end
                                      ##end
                           #end
                           </td>
                           </tr>
						</table>
<br>
<fieldset><legend>SENARAI HARTA TAK ALIH (TIADA HAKMILK)</legend>
                                    <table>
                    <tr>
                    <td valign="top"><i>*</i></td>
                    <td><i>Untuk tujuan pembatalan atau perlantikan baru bagi  Pemegang Surat Kuasa Tadbir, sila pilih hakmilik harta tak alih yang berkenaan.</i></td>
                    </tr>
                    </table>
                                  <table width="97%">
                             
                                      <tr  class="table_header">
                                        <td width="5%"><div align="center">NO</div></td>
                                        <td width="20%"><div align="center">NEGERI</div></td>
                                        <td width="20%"><div align="center">DAERAH</div></td>
                                        <td width="20%"><div align="center">MUKIM</div></td>
                                        <td width="30%"><div align="center">ALAMAT / NO.PERJANJIAN / NO. ROH</div></td>
                                        <td width="5%"><div align="center"><input type="checkbox" name="all" onClick="javascript:doCheckAll()"></div></td>
                                      </tr>
                                      #set($plko=0)
     #if($listHTAXbaru.size()!=0)
     		#foreach($data in $listHTAXbaru)
            							#set($plko=$plko+1)
            							#if ($idpermohonansimati == $data.idpermohonansimati)
								       		#set ($i = $velocityCount)
										        #if (($i % 2) == 0)
										       		#set ($row = "row4")
										        #else
										       		#set ($row = "row4")
										        #end
         							 	#else
         							 		#set ($row = "row4")
         							 	#end
            
    <tr bgcolor="white">
    <td class="$row" valign="top"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
    <td class="$row" valign="top"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($data.idhta)" class="style42">$data.namanegeri</a></div></td>
    <td class="$row" valign="top"><div style="text-transform:uppercase;" onblur="uppercase()">$data.namadaerah</div></td>
    <td class="$row" valign="top"><div style="text-transform:uppercase;" onblur="uppercase()">$data.namamukim</div></td>
    <td class="$row" valign="top"><div style="text-transform:uppercase;" onblur="uppercase()">
    #if ($data.flaghta == "1")
    	$data.alamathta1,<br> $data.alamathta2,<br>
        $data.alamathta3,<br> 
        $data.poskodhta  $data.keterangan
    #elseif ($data.flaghta == "2")
    	No. Roh : $data.noroh <br> 
        No. Lot : $data.nolotid
    #end
    </div></td>
    <td class="$row" align="center" valign="top"><input type="checkbox" name="chkbox" value="$listam.idhta" disabled="disabled" ></td>
    </tr>
     		#end
     #end      
              #if($listHTAX.size()!=0 ) 
                                      #foreach($listam in $listHTAX)
                                      #if ($listam.pilih != 0)
                                     	#set($y="checked")
                                     #else
                                     	#set($y="")
                                     #end
                                      #set($plko=$plko+1)
                                      #if($plko%2!=0)
                                      #if ($idpermohonansimati == $listam.idpermohonansimati)
								       		#set ($i = $velocityCount)
										        #if (($i % 2) == 0)
										       		#set ($row = "row3")
										        #else
										       		#set ($row = "row3")
										        #end
         							 	#else
         							 		#set ($row = "row3")
         							 	#end
  <tr bgcolor="white">
    <td class="$row"><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
    <td class="$row"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$listam.namanegeri</a></div></td>
    <td class="$row"><div style="text-transform:uppercase;" onblur="uppercase()">$listam.namadaerah</div></td>
    <td class="$row"><div style="text-transform:uppercase;" onblur="uppercase()">$listam.namamukim</div></td>
    <td class="$row"><div style="text-transform:uppercase;" onblur="uppercase()"></div></td>
    <td class="$row" align="center"><input type="checkbox" name="chkbox" value="$listam.idhta" $y ></td>
    </tr>
                                      #else
                                      #if ($idpermohonansimati == $listam.idpermohonansimati)
								       		#set ($i = $velocityCount)
										        #if (($i % 2) == 0)
										       		#set ($row = "row4")
										        #else
										       		#set ($row = "row4")
										        #end
         							 	#else
         							 		#set ($row = "row3")
         							 	#end
  <tr class="table_header">
    <td class="$row"><div align="center" style="text-transform:uppercase;" onblur="uppercase()"><span class="style41">$plko</span></div></td>
    <td class="$row"><div style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_htaam($listam.idhta)" class="style42">$listam.namanegeri</a></div></td>
    <td class="$row"><div style="text-transform:uppercase;" onblur="uppercase()"><span class="style41">$listam.namadaerah</span></div></td>
    <td class="$row"><div style="text-transform:uppercase;" onblur="uppercase()"><span class="style41">$listam.namamukim</span></div></td>
    <td class="$row"><div style="text-transform:uppercase;" onblur="uppercase()"></div></td>
    <td class="$row" align="center"><input type="checkbox" name="chkbox" value="$listam.idhta" $y ></td>
 </tr>
                                      #end
                                      #end
 <tr>
    <td height="40px" align="right" colspan="7"><input type="button" name="flag_btn" value="Simpan" onClick="javascript:simpanhakmilik()"></td>
  </tr>
                                    </table>
                                    #end
                                 <input type="hidden" name="idhtaamid" value="$idhtaam" />
                                   <table width="50%">
                              <tr>
                              <td colspan="2">NOTA :</td>
                              </tr>
                              <tr>
                              <td width="3%" bgcolor="#FAFAFA">&nbsp;</td>
                              <td width="47%">&nbsp;Rekod Terkini</td>
                              </tr>
                             <tr>
                              <td width="3%" bgcolor="#E2E2E2">&nbsp;</td>
                              <td width="47%">&nbsp;Rekod Terdahulu</td>
                              </tr>
                              </table> 
                    </fieldset> 


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
</table>
<script type="text/javascript">
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
function doCheckAll(){    
    if (document.${formName}.all.checked == true){
        if (document.${formName}.chkbox.length == null){
            document.${formName}.chkbox.checked = true;
        } else {
            for (i = 0; i < document.${formName}.chkbox.length; i++){
                document.${formName}.chkbox[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.chkbox.length == null){
            document.${formName}.chkbox.checked = false;
        } else {
            for (i = 0; i < document.${formName}.chkbox.length; i++){
                document.${formName}.chkbox[i].checked = false;
            }
        }
    }
}


function HtaamView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}('Htaam');
	document.${formName}.submit();
}

function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}('harta_alih');
	document.${formName}.submit();
}

function HtaamViewX() {
	document.${formName}.method="post";
	document.${formName}.mode.value="HtaamviewX";
	doAjaxCall${formName}('HtaamX');
	document.${formName}.submit();
}

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();
}


function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.${formName}.tabIdatas.value = tabIdatas;
    document.${formName}.tabIdtengah.value = tabIdtengah;
    document.${formName}.tabIdbawah.value = tabIdbawah;	
	document.${formName}.tabIdtepi.value = tabIdtepi;	
}

function HtaamViewX1() {
	document.${formName}.method="post";
	document.${formName}.radioHtaamViewX1.value=1;
	document.${formName}.mode.value="HtaamviewX1";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.submit();
}

function HtaamViewX2() {
	document.${formName}.method="post";
	document.${formName}.radioHtaamViewX1.value=2;
	document.${formName}.mode.value="HtaamviewX2";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.submit();
}

function HtaamViewX3() {
	document.${formName}.method="post";
	document.${formName}.radioHtaamViewX1.value=3;
	document.${formName}.mode.value="HtaamviewX3";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.submit();
}

function negerichangeX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="negerichangeX";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
}

function daerahchangeX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="daerahchangeX";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
}

function negerichangeupX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){		
		document.${formName}.method="post";
		document.${formName}.mode.value="negerichangeupX";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
}

function daerahchangeupX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="daerahchangeupX";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
}

function onChangeBandarTetap(){
	if (document.${formName}.radioHtaamViewX1[0].checked){
		document.${formName}.method="post";
		document.${formName}.radioHtaamViewX1[0].value = 1;
		document.${formName}.mode.value="onChangeBandarTetap";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
	} else if (document.${formName}.radioHtaamViewX1[1].checked){
		document.${formName}.method="post";
		document.${formName}.radioHtaamViewX1[1].value = 2;
		document.${formName}.mode.value="onChangeBandarTetap";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
	} else if (document.${formName}.radioHtaamViewX1[2].checked){
		document.${formName}.method="post";
		document.${formName}.radioHtaamViewX1[2].value = 3;
		document.${formName}.mode.value="onChangeBandarTetap";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.submit();
	}
	
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
		document.${formName}.submit(); 
		}
	}
}
		
function edit_HtaamX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		doAjaxCall${formName}("HtaamX");
		document.${formName}.mode.value="kemaskiniHtaamX";		
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
		document.${formName}.submit();
		}else
		{
		return;
		}
}

function get_htaam(idhtaam)
{
	document.${formName}.method="post";
	document.${formName}.idhtaamXid.value=idhtaam;
	document.${formName}.mode.value="getHtaamX";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.submit();
}

function get_X(idhtaam)
{
	document.${formName}.method="post";
	document.${formName}.idhtaamXid.value=idhtaam;
	document.${formName}.mode.value="getHtaamX";
	doAjaxCall${formName}("HtaamX");
	document.${formName}.submit();
	
}


function specialcharecter(t_d){
	var iChars = "!`@#$%^&*()+=-[]\\\';,.{}|\":<>?~_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";   
	var data = document.getElementById("txtTarikhPerjanjianHtaamX").value;
	for (var i = 0; i < data.length; i++)
	{      
		if (iChars.indexOf(data.charAt(i)) != -1)
		{  
			document.getElementById("txtTarikhPerjanjianHtaamX").value = "";
			return;  
		}
		else{
			trans_date(t_d)
		} 
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
	document.${formName}.txtTarikhPerjanjianHtaamX.value = trans;
}
else
{
return;
}

}

function simpanhakmilik(){ 
	var total = 0;
	var max = ${formName}.chkbox.length;
	for (var idx = 0; idx < max; idx++) {
	if (eval("document.${formName}.chkbox[" + idx + "].checked") == true) {
		total += 1;
	   }		
	}
	if (total == 0){
			alert("Sila pilih 'Harta Tak Alih' terlebih dahulu");
		}else{
			document.${formName}.method="post";
			document.${formName}.mode.value="simpanflagX";
			doAjaxCall${formName}("HtaamX");
			document.${formName}.submit();
		}
	
}
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:1});
//-->
</script>
</body>

