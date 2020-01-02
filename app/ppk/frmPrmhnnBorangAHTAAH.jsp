

<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<body>
<fieldset>
<legend>Pembahagian Pusaka Kecil</legend>
<fieldset>
<legend>Maklumat untuk Borang A</legend>
<table width="97%" border="0">
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
 <input name="hideTabPengesahan " type="hidden" value="$hideTabPengesahan ">
#foreach($list in $View)
   #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
    <input name="idPemohon" type="hidden"  value="$idPemohon"/>
    <input name="idSimati" type="hidden"  value="$idSimati"/>
    <input name="idtemp" type="hidden"  value="$id"/>
#end
</td>
</tr>
  <tr>
    <td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  <li class="TabbedPanelsTab" tabindex="0" onClick="SimatiView('0','0','0','0')">PERMOHONAN</li>
   <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HAview('2','0','0','0')">HARTA ALIH</li>
     #if($hideTabPengesahan != "hide")
    <li class="TabbedPanelsTab" tabindex="0" onClick="PengesahanView('3','0','0','0')">PENGESAHAN PERMOHONAN</li>
    #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
	
	<div id="TabbedPanels2" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH (ADA HAKMILIK)</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamViewX('1','0','0','1')">HARTA TAK ALIH (TIADA HAKMILIK)</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"><br>
	<table width="97%" border="0" align="center">
          <tr>
          <td width="80%">                                 
#set ($nilai_Hta_mati = "0.00")
#set ($nilai_Hta_memohon = "0.00")
#set ($luashmp = "0")
#set ($luasasal = "0")
#set ($tanggungan = "")
#set ($noHakmilik = "")
#set ($nopt = "")
#set ($basimati = "")
#set ($bbsimati = "")
#set ($nopajakan = "")
#set ($noperserahan = "")
#set ($catatan = "")
#set ($nama_daerah= "")
#set ($nama_mukim = "")
#set ($jenishakmilik = "")

#if($show_htaa_update_table=="yes") 
	#foreach($listamid in $listHTAid)
		#set ($nilai_Hta_mati = $listamid.nilai_Hta_mati)
		#set ($nilai_Hta_memohon = $listamid.nilai_Hta_memohon)
		#set ($luashmp = $listamid.luashmp)
		#set ($luasasal = $listamid.luasasal)
		#set ($tanggungan = $listamid.tanggungan)
		#set ($noHakmilik = $listamid.noHakmilik)
		#set ($nopt = $listamid.nopt)
		#set ($basimati = $listamid.basimati)
		#set ($bbsimati = $listamid.bbsimati)
		#set ($nopajakan = $listamid.nopajakan)
		#set ($noperserahan = $listamid.noperserahan)
		#set ($catatan = $listamid.catatan)
		#set ($nama_daerah= $listamid.daerah)
		#set ($nama_mukim = $listamid.mukim)
		#set ($nama_negeri= $listamid.negeri)
	#end
#end
                                      #if($show_htaa_add_table=="yes")
                                      <fieldset><legend>MAKLUMAT HARTA TAK ALIH SIMATI(ADA HAKMILIK)</legend>
                                      <table width="100%" border="0">
                                        <tr>
                                          <td width="100%" valign="top"><table width="100%" border="0">
                                            <tr>
                                              <td width="35%" class="style38"><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                                              <td width="1%"><div align="right">:</div></td>
                                              <td width="64%"> 
                                              #set($negerikodpemoP="")
                                              #set($negeriketeranganpemoP="")
                                              #foreach($listnegpomo in $listnegeri)
                                                #if($negeri==$listnegpomo.id_Negeri)
                                                #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                                 #end 
                                                #end
                                                #if($negeri!="")
                                                <select name="socNegeriHtaam" $readmodenegeri style="width: 225px;" onChange="setSelected(1,0,0,0);negerichange()">
                                              <option value="$negeri">$!negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                                  <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
                                  #end    
	                               #end
                                                </select>
                                                #else
  <select name="socNegeriHtaam" $readmodenegeri onChange="setSelected(1,0,0,0);negerichange()" style="width: 225px;">
    <option value="">Sila Pilih Negeri</option>
                                  #foreach($listnegpomo in $listnegeri)
    <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
	                               #end
  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38" ><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Daerah</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>
                                              #set($listDaerahbyNegeriK="")
                                                #set($listDaerahbyNegeriN="")
                                              #foreach($listdaerah in $listDaerahbyNegeri)
                                                    #if($daerah==$listdaerah.id)
                                                        #set($listDaerahbyNegeriK=$listdaerah.kod)
                                                        #set($listDaerahbyNegeriN=$listdaerah.nama)
                                                    #end 
                                              #end
                                                #if($daerah!="")
                                                <select name="socDaerahHtaam" $readmodedaerah onChange="setSelected(1,0,0,0);daerahchange()" style="width: 225px;">
                                              <option value="$daerah">$!listDaerahbyNegeriN</option>
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($daerah!=$listdaerah.id)
                                                  <option value="$listdaerah.id">$!listdaerah.nama</option>
                                  #end    
	                               #end
                                                </select>
                                                #else
  <select name="socDaerahHtaam" style="width: 225px;" $readmodedaerah onChange="setSelected(1,0,0,0);daerahchange()">
    <option value="">Sila Pilih Daerah</option>
                                  #foreach($listDaerah in $listDaerahbyNegeri)
    <option value="$listDaerah.id">$!listDaerah.nama</option>
	                               #end
  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38" ><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Mukim</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listmukim in $listMukimbyDaerah)
                                                        #if($mukim==$listmukim.id)
                                                            #set($listMukimbyDaerahK=$listmukim.kod)
                                                            #set($listMukimbyDaerahN=$listmukim.nama)
                                                        #end 
	                                                #end
                                                #if($mukim!="")
                                                <select name="socMukimHtaam" style="width: 225px;" $readmodemukim id="socMukimHtaam">
                                              <option value="$mukim">$!listMukimbyDaerahN</option>
                                  #foreach($listmukim in $listMukimbyDaerah)
                                  #if($mukim!=$listmukim.id)
                                                  <option value="$listmukim.id">$!listdaerah.nama</option>
                                  #end    
	                               #end
                                                </select>
                                                #else
  <select name="socMukimHtaam" style="width: 225px;" $readmodemukim>
    <option value="">Sila Pilih Mukim</option>
                                  #foreach($listmukim in $listMukimbyDaerah)
    <option value="$listmukim.id">$!listmukim.nama</option>
	                               #end
  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38" ><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Jenis Hakmilik</div></td>
                                              <td><div align="right">:</div></td>
                                              <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                                                    #if($jenishakmilik==$listjenishakmilik.id)
                                                        #set($listjenishakmilikK=$listjenishakmilik.kod)
                                                        #set($listjenishakmilikN=$listjenishakmilik.keterangan)
                                                    #end 
                                                  #end
                                                #if($jenishakmilik!="")
                                                <select name="socJenisHakmilikHtaam" style="width: 225px;" $readmode id="socJenisHakmilikHtaam">
                                              <option value="$jenishakmilik">$!listjenishakmilikK - $!listjenishakmiliN</option>
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                  #if($jenishakmilik!=$listjenishakmilik.id)
                                                  <option value="$listjenishakmilik.id">$!listjenishakmilik.kod - $!listjenishakmilik.keterangan</option>
                                  #end    
	                               #end
                                                </select>
                                                #else
  <select name="socJenisHakmilikHtaam" style="width: 225px;" $readmode id="socJenisHakmilikHtaam">
    <option value="">Sila Pilih Jenis Hak Milik</option>

                                  #foreach($listjenishakmilik in $listJenisHakMilik)

    <option value="$listjenishakmilik.id">$!listjenishakmilik.kod - $!listjenishakmilik.keterangan</option>
	                               #end
  </select>
                                                #end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;No Hakmilik</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNoHakmilikHtaam" type="text" id="txtNoHakmilikHtaam" value="$!noHakmilik" style="text-transform:uppercase;" size="34" maxlength="40" />
                                              </label></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;No PT / No Lot</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><label>
                                                <input name="txtNoPTHtaam" type="text" id="txtNoPTHtaam" value="$!nopt" size="12" style="text-transform:uppercase;" maxlength="12" />
                                              </label></td>
                                            </tr>
                                            <tr>
                            <td class="style38"><div align="right">Bahagian Simati</div></td>
                            <td>:</td>
                            <td><input name="txtBahagianSimati1" type="text" id="txtBahagianSimati1" onKeyUp="javascript:validatePoskod(this,this.value)" $readmode value="$!listamid.basimati" size="14" maxlength="14" style="text-align: right;" />
                              /
                              <input name="txtBahagianSimati2" type="text" id="txtBahagianSimati2" $readmode onKeyUp="javascript:validatePoskod(this,this.value)" value="$!listamid.bbsimati" size="14" maxlength="14" onBlur="javascript:validateBahagian('txtBahagianSimati1','txtBahagianSimati2')" />
                            </td>
                          </tr>
                         
                                 
		                 
                          <tr>
                            <td class="style38"><div align="right">Anggaran Nilai Harta (RM)</div></td>
                            <td>:</td>
                            <td><label>
                          
                         <input name="txtNilaiTarikhMohonHtaa" id="txtNilaiTarikhMohonHtaa" type="text" style="width: 225px; "  $disabled value="$nilai_Hta_memohon" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)" />
                           
                            </label></td>
                          </tr>
											<tr>
                                              <td class="style38" valign="top"><div align="right">Catatan </div></td>
                                              <td width="1%" valign="top"><div align="right">:</div></td>
                                              <td><textarea name="txtCatatanHtaam" id="txtCatatanHtaam" cols="31" rows="5" style="text-transform:uppercase;">$!catatan</textarea></td>
                                            </tr> 
                                          </table></td>
                                          
                                        </tr>
										<tr>
										<td valign="bottom">
                                       <i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>            
                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>                         </i></td>
										</tr>
                                      </table>
                                      </fieldset>
                                    #end</td>
                      </tr>
                                  #foreach($listamid in $listHTAid)
                                  <tr>
                                    <td>
                    #if($show_htaa_update_table=="yes") 
                  
                    <fieldset><legend>HARTA TAK ALIH (ADA HAKMILIK)</legend>
                    <table width="100%" border="0">
                      <tr>
                      <input type="hidden" name="id_htaam" value="$listamid.idhta" />
                        <td valign="top" width="80%"><table width="100%" border="0">
                          <tr>
                            <td width="35%" class="style38"><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                            <td width="1%">:</td>
                            <td width="64%"> #foreach($listnegpomo in $listnegeri)
                                  #if($listamid.negeri==$listnegpomo.id_Negeri)
                                      #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                      #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                  #end 
                              #end
                              #if($listamid.negeri!="")
                              <select name="socNegeriHtaamUp" style="width: 225px;" $readmodenegeri  onchange="negerichangeup()"  >
                                              <option value="$listamid.negeri">$!negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                      #if($listamid.negeri!=$listnegpomo.id_Negeri)
                                            <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
                                      #end    
	                              #end

                              </select>
                              #else
                              <select name="socNegeriHtaamUp" style="width: 225px;" $readmodenegeri onChange="negerichangeup()" >
                                <option value="">Sila Pilih Negeri</option>
                                          #foreach($listnegpomo in $listnegeri)
                                <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
                                          #end
                              </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38" ><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Daerah</div></td>
                            <td>:</td>
                            <td>#foreach($listdaerah in $listdaerah)
                                      #if($listamid.daerah==$listdaerah.id)
                                          #set($listDaerahbyNegeriK=$listdaerah.kod)
                                          #set($listDaerahbyNegeriN=$listdaerah.nama)
                                      #end 
                                #end
                              #if($listamid.daerah!="")
                              <select name="socDaerahHtaamUp" style="width: 225px;" $readmodedaerah onChange="setSelected(1,0,0,0);daerahchange()" id="socDaerahHtaam">
                                              <option value="$listamid.daerah">$!listDaerahbyNegeriN</option>
                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                          #if($listamid.daerah!=$listdaerah.id)
                                                <option value="$listdaerah.id">$!listdaerah.nama</option>
                                          #end    
	                               #end

                              </select>
                              #else
                              <select name="socDaerahHtaamUp" style="width: 225px;" $readmodedaerah onChange="setSelected(1,0,0,0);daerahchangeup()">
                                <option value="">Sila Pilih Daerah</option>
                                   #foreach($listDaerah in $listDaerahbyNegeri)
                                <option value="$listDaerah.id">$!listDaerah.nama</option>
                                   #end
                              </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38" ><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Mukim</div></td>
                            <td>:</td>
                            <td>#foreach($listmukim in $listmukim)
                                  #if($listamid.mukim==$listmukim.id)
                                      #set($listMukimbyDaerahK=$listmukim.kod)
                                      #set($listMukimbyDaerahN=$listmukim.nama)
                                  #end 
                                #end
                              #if($listamid.mukim!="")
                              <select name="socMukimHtaamUp" style="width: 225px;" $readmodemukim id="socMukimHtaam2">
                                              <option value="$listamid.mukim">$!listMukimbyDaerahN</option>
                                  #foreach($listmukim in $listmukim)
                                      #if($listamid.mukim!=$listmukim.id)
                                            <option value="$listmukim.id">$!listdaerah.nama</option>
                                      #end    
	                               #end

                              </select>
                              #else
                              <select name="socMukimHtaamUp" style="width: 225px;" $readmodemukim id="socMukimHtaamUp">
                                <option value="">Sila Pilih Mukim</option>
                                                              #foreach($listmukim in $listMukimbyDaerah)
                                <option value="$listmukim.id">$!listmukim.nama</option>
                                                               #end
                              </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38" ><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;Jenis Hakmilik</div></td>
                            <td>:</td>
                            <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                                  #if($listamid.jenishakmilik==$listjenishakmilik.id)
                                      #set($listjenishakmilikK=$listjenishakmilik.kod)
                                      #set($listjenishakmilikN=$listjenishakmilik.keterangan)
                                  #end 
                                #end
                              #if($listamid.jenishakmilik!="")
                              <select name="socJenisHakmilikHtaamUp" style="width: 225px;" $readmode id="socJenisHakmilikHtaam2">
                                              <option value="$listamid.jenishakmilik">$listjenishakmilikK - $!listjenishakmilikN</option>
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                      #if($listamid.jenishakmilik!=$listjenishakmilik.id)
                                        <option value="$listjenishakmilik.id">$!listjenishakmilik.kod - $!listjenishakmilik.keterangan</option>
                                      #end    
	                              #end
                              </select>
                              #else
                          <select name="socJenisHakmilikHtaamUp" style="width: 225px;" $readmode id="socJenisHakmilikHtaam2">
                            <option value="">Sila Pilih Jenis Hak Milik</option>
                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                            <option value="$listjenishakmilik.id">$!listjenishakmilik.keterangan</option>
                                  #end
                          </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;No Hakmilik</div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoHakmilikHtaamUp" type="text" id="txtNoHakmilikHtaam2" value="$!listamid.noHakmilik" size="34" maxlength="40" $readmode style="text-transform:uppercase;" />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;No PT / No Lot </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoPTHtaamUp" type="text" id="txtNoPTHtaamUp" value="$!listamid.nopt" size="12" maxlength="12" $readmode style="text-transform:uppercase;" />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Bahagian Simati</div></td>
                            <td>:</td>
                            <td><input name="txtBahagianSimati1Up" type="text" id="txtBahagianSimati1Up" onKeyUp="javascript:validatePoskod(this,this.value)" $readmode value="$!listamid.basimati" size="14" maxlength="14" style="text-align: right;" />
                              /
                              <input name="txtBahagianSimati2Up" type="text" id="txtBahagianSimati2Up" $readmode onKeyUp="javascript:validatePoskod(this,this.value)" value="$!listamid.bbsimati" size="14" maxlength="14" onBlur="javascript:validateBahagian('txtBahagianSimati1Up','txtBahagianSimati2Up')" />                            </td>
                          </tr>
                       
                          		
                        
                          <tr>
                            <td class="style38"><div align="right">Anggaran Nilai Harta (RM)</div></td>
                            <td>:</td>
                            <td><label>
                             #if ($show_htaa_update_table=="yes" && $readmode != "")
                             
                             	#if($nilai_Hta_memohon !="")
                                  	  <input name="txtNilaiTarikhMohonHt" id="txtNilaiTarikhMohonHt" type="text" style="width: 225px; "  $readmode value="$Util.formatDecimal($nilai_Hta_memohon)" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)" />
       
                                  #else
                                  	<input name="txtNilaiTarikhMohonHt" id="txtNilaiTarikhMohonHt" type="text" style="width: 225px; "  $readmode value="" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)" />
                                  #end
                                  #else
                                  #set($nilai_Hta_memohon = $nilai_Hta_memohon)
                        <input name="txtNilaiTarikhMohonHt" id="txtNilaiTarikhMohonHt" type="text" style="width: 225px; "  $readmode value="$nilai_Hta_memohon" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value)" />
                        <input name="readmode" type="hidden" value="$readmode">
                            #end
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38" valign="top"><div align="right">Catatan</div></td>
                            <td valign="top">:</td>
                            <td><textarea name="txtCatatanHt" id="txtCatatanHt" value="$!listamid.catatan" cols="31" rows="5" style="text-transform:uppercase;" $readmode>$!listamid.catatan</textarea>                            </td>
                          </tr>
                        </table></td>
                       
                      </tr>
					  <tr>
					<td valign="bottom"><i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>            
                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>                         </i></td>
										</tr>
                    </table>
                    </fieldset>
#end                                    </td>
                      </tr>
                                  
                                  #end
                         #if ($idstatus == "150" || $idstatus == "171") 
                              #if($show_button=="yes")
                              <tr>
                                <td><table width="100%" border="0" align="center">
                                  <tr>
                                    <td align="center">
                                    #if($show_simpan_add_htaam=="yes")
                                      <input type="button" name="tambahpenghutang2" id="tambahpenghutang2" value="Simpan" onClick="add_Htaam('1','0','0','0')"/>
                                    #end
                                    #if($show_kemaskini_htaam=="yes")
                                        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="edit_Htaam('1','0','0','0')" />
                                        #end
                                     #if($show_save_update_htaam=="yes")
                                        <input type="button" name="cmdKemaskiniSimpan" id="cmdKemaskiniSimpan" value="Simpan" onClick="save_Htaam('1','0','0','0')"/>
                                   #end
                                   #if($show_batal_add_htaam=="yes")
                                      <input type="reset" name="cmdSimpan6" id="cmdSimpan6" value="Batal" onClick="HtaamView('1','0','0','0')"/>
                                    #end
                                     
                                   #if($show_hapus_htaam=="yes")
                                      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="hapus_Htaam('1','0','0','0')"/>
                                 	#end
                                  #if($show_batal_update_htaam=="yes")
                                      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onClick="HtaamView('1','0','0','0')" />
                                   #end
                                 </tr>
                                </table>
                                </td>
                              </tr>
                              #end
                         #end
                                  <tr>
                                    <td>
                                    <fieldset><legend>SENARAI HARTA TAK ALIH (ADA HAKMILIK)</legend>
                                      <table width="100%">
                                    </table>
                                    #if($listHTA.size()!=0 )    
                                    <table width="100%">
                                      <tr  class="table_header">
                                        <td width="4%"><div align="center">NO</div></td>
                                        <td width="25%"><div align="center">NEGERI</div></td>
                                        <td width="25%"><div align="center">DAERAH</div></td>
                                        <td width="20%"><div align="center">MUKIM</div></td>
                                        <td width="10%"><div align="center">NO HAKMILIK</div></td>
                                        <td width="10%"><div align="center">NO PT/NO LOT</div></td>
										<td width="20%"><div align="center">BHGN SIMATI</div></td>
                                      </tr>
                                      #set($plko=0)
                                      #foreach($listam in $listHTA)
                                      #set($plko=$plko+1)
                                      #if($plko%2!=0)
  <tr bgcolor="white">
    <td class="row1"><div align="center">$plko</div></td>
    <td class="row1"><div class="style42"><a href="javascript:get_htaam($listam.idhta)"><span class="style1">$listam.namanegeri</span></a></div></td>
	<td class="row1">$listam.namadaerah</td>
    <td class="row1">$listam.namamukim</td>
    <td class="row1">$listam.kod_jenis_hakmilik$listam.noHakmilik</td>
    <td class="row1">$listam.nopt</td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td class="row1"><div align="center">$listam.basimati / $listam.bbsimati</div></td>
    #else
    <td class="row1"><div align="center">0 / 0</div></td>
    #end </tr>
                                      #else
  <tr class="table_header">
    <td><div align="center">$plko</div></td>

    <td class="row2"><a href="javascript:get_htaam($listam.idhta)"><div class="style42 style1">$listam.namanegeri</div>
    </a></td>
    <td class="row2">$listam.namadaerah</td>
    <td class="row2">$listam.namamukim</td>
    <td class="row2">$listam.kod_jenis_hakmilik$listam.noHakmilik</td>
    <td class="row2">$listam.nopt</td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td class="row2"><div align="center">$listam.basimati / $listam.bbsimati</div></td>
    #else
    <td class="row2"><div align="center">0 / 0</div></td>
    #end </tr>
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
                                        <td width="10%"><div align="center">NO HAKMILIK</div></td>
                                        <td width="10%"><div align="center">NO PT/NO LOT</div></td>
                                        <td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
                                      </tr>
                                      </table>
                                      <table width="100%">
                                      <tr bgcolor="white">
                                        <td align="left">Tiada Rekod
                                        </td>
                                      </tr>
                                      </table>
                                    #end
                                    </fieldset>
                                    </td>
                                    <input type="hidden" name="idhtaam" value="$listamid.idhta" />
                                    <input type="hidden" name="idhtaamid" value="$idhtaam" />
                                  </tr>
                                 </table>
								 </div>
    <div class="TabbedPanelsContent"></div>
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
</fieldset>
</fieldset>
<script>
<!-- TAB -->

function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content2);
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	if (elmnt.value == "0.00"){
		elmnt.value = "";
		return;
	}else{
		elmnt.value = num.toFixed(2);
		return;
	}
}

function validateBahagian(content1,content2) {
var AB = document.${formName}.content1.value;
var BB = document.${formName}.content2.value;

	if ( BB < AB ) {
		alert("Pembahagian bahagian pembawah si mati hendaklah lebih besar. Contoh: 2/4");
	}
}

function convertTo(elmnt,content){
	if (document.${formName}.socJenisLuasHtaam.value=="4" || document.${formName}.socJenisLuasHtaam.value=="7"){
		var num = content * 0.4046863; 
		var num1 = content * 4046.8252519;
		document.${formName}.txtLuasHMpHtaam.value=num.toFixed(2);
		document.${formName}.txtLuasHMpHtaamX.value=num1.toFixed(2);
	}
	if (document.${formName}.socJenisLuasHtaam.value=="2"){
		var num = content; 
		var num1 = content * 10000;
		document.${formName}.txtLuasHMpHtaam.value=num;
		document.${formName}.txtLuasHMpHtaamX.value=num1;
	}
	if (document.${formName}.socJenisLuasHtaam.value=="3"){
		var num = content * 0.0001; 
		var num1 = content;
		document.${formName}.txtLuasHMpHtaam.value=num.toFixed(2);
		document.${formName}.txtLuasHMpHtaamX.value=num1.toFixed(2);
	}
	if (document.${formName}.socJenisLuasHtaam.value=="5"){
		var num = content * 9.290304; 
		var num1 = content * 0.09290304;
		document.${formName}.txtLuasHMpHtaam.value=num.toFixed(2);
		document.${formName}.txtLuasHMpHtaamX.value=num1.toFixed(2);
	}
	if (document.${formName}.socJenisLuasHtaam.value=="1"){
		var num = content * 100; 
		var num1 = content * 1000000;
		document.${formName}.txtLuasHMpHtaam.value=num.toFixed(2);
		document.${formName}.txtLuasHMpHtaamX.value=num1.toFixed(2);
	}
}

function getConversion(){
	if (document.${formName}.txtLuasAsalHtaam.value != "0"||document.${formName}.txtLuasAsalHtaam.value != ""){
		if (document.${formName}.socJenisLuasHtaam.value=="4" || document.${formName}.socJenisLuasHtaam.value=="7") {
			var content = document.${formName}.txtLuasAsalHtaam.value;
			var num = content * 0.4046863; 
			var num1 = content * 4046.8252519;
			document.${formName}.txtLuasHMpHtaam.value=num.toFixed(2);
			document.${formName}.txtLuasHMpHtaamX.value=num1.toFixed(2);
		}
		if (document.${formName}.socJenisLuasHtaam.value=="2"){
			var content = document.${formName}.txtLuasAsalHtaam.value;
			var num = content; 
			var num1 = content * 10000;
			document.${formName}.txtLuasHMpHtaam.value=num;
			document.${formName}.txtLuasHMpHtaamX.value=num1;
		}
		if (document.${formName}.socJenisLuasHtaam.value=="3"){
			var content = document.${formName}.txtLuasAsalHtaam.value;
			var num = content * 0.0001; 
			var num1 = content;
			document.${formName}.txtLuasHMpHtaam.value=num.toFixed(2);
			document.${formName}.txtLuasHMpHtaamX.value=num1.toFixed(2);
		}
		if (document.${formName}.socJenisLuasHtaam.value=="5"){
			var content = document.${formName}.txtLuasAsalHtaam.value;
			var num = content * 9.290304; 
			var num1 = content * 0.09290304;
			document.${formName}.txtLuasHMpHtaam.value=num.toFixed(2);
			document.${formName}.txtLuasHMpHtaamX.value=num1.toFixed(2);
		}
		if (document.${formName}.socJenisLuasHtaam.value=="1"){
			var content = document.${formName}.txtLuasAsalHtaam.value;
			var num = content * 100; 
			var num1 = content * 1000000;
			document.${formName}.txtLuasHMpHtaam.value=num.toFixed(2);
			document.${formName}.txtLuasHMpHtaamX.value=num1.toFixed(2);
		}
	}
}

function HtaamViewX(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="HtaamviewX";
	doAjaxCall${formName}("HtaamX");
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
	/*document.${formName}.socJenisHakmilikHtaam.value="";
	document.${formName}.txtNoPTHtaam.value="";
	document.${formName}.txtBahagianSimati1.value="";
	document.${formName}.txtBahagianSimati2.value="";
	document.${formName}.txtNilaiTarikhMohonHtaa.value="0.00";
	document.${formName}.txtCatatanHtaam.value="";*/
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
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
	document.${formName}.hitButt.value="nilai_harta";
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
	document.${formName}.action.value="";
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

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.action.value="";
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

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;	
}

function cancelwaris(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.reset();
	document.${formName}.txtNoKPBaru1Waris.focus();
}

<!-- HTAAH -->
function addcancelhtaam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.reset();
	document.${formName}.action.value="";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}("Htaam");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.socNegeriHtaam.focus();
	document.${formName}.submit();
}
function nktambah(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.reset();
	document.${formName}.action.value="";
	doAjaxCall${formName}("Htaam");
	document.${formName}.mode.value="add_new";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}


function get_htaam(idhtaam)
{
	document.${formName}.method="post";
	doAjaxCall${formName}("Htaam");
	document.${formName}.idhtaam.value=idhtaam;
	document.${formName}.mode.value="getHtaam";
	document.${formName}.tabIdatas.value="1";
	document.${formName}.tabIdtengah.value="0";
	document.${formName}.tabIdbawah.value="0";
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function negerichange(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	//document.${formName}.method="post";

	document.${formName}.mode.value="negerichange";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
		doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}
function negerichangeup(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";

		document.${formName}.mode.value="negerichangeup";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;
		document.${formName}.action.value="";
			doAjaxCall${formName}("Htaam");
		document.${formName}.submit();
}
function daerahchange(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		
		document.${formName}.mode.value="daerahchange";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;		
		document.${formName}.action.value="";
	doAjaxCall${formName}("Htaam");
		document.${formName}.submit();
}
function daerahchangeup(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
	
		document.${formName}.mode.value="daerahchangeup";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;		
		document.${formName}.action.value="";
			doAjaxCall${formName}("Htaam");
		document.${formName}.submit();
}

function tambah_simpan_penting(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {if(document.${formName}.txtNamaOBPenting.value == ""){
			alert('Sila masukkan " Nama " .');
	  		document.${formName}.txtNamaOBPenting.focus(); 
			return; 
            }
		}
	document.${formName}.method="post";
	document.${formName}.mode.value="tambah_penghutang";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
		
	}


function add_Htaam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){

var AB = document.${formName}.txtBahagianSimati1.value;
var BB = document.${formName}.txtBahagianSimati2.value;

	if(document.${formName}.socNegeriHtaam.value == ""){
		alert('Sila pilih " Negeri " .');
  		document.${formName}.socNegeriHtaam.focus(); 
		return; 
        }
	else if(document.${formName}.socDaerahHtaam.value == ""){
		alert('Sila pilih " Daerah " .');
  		document.${formName}.socDaerahHtaam.focus(); 
		return; 
        }
	else if(document.${formName}.socMukimHtaam.value == ""){
		alert('Sila pilih " Mukim " .');
  		document.${formName}.socMukimHtaam.focus(); 
		return; 
        }
	else if(document.${formName}.socJenisHakmilikHtaam.value == ""){
		alert('Sila pilih " Jenis Hakmilik " .');
  		document.${formName}.socJenisHakmilikHtaam.focus(); 
		return; 
    }
	else if(document.${formName}.txtNoHakmilikHtaam.value == ""){
		alert('Sila nyatakan " No Hakmilik " .');
  		document.${formName}.txtNoHakmilikHtaam.focus(); 
		return; 
    }
	else if(document.${formName}.txtNoPTHtaam.value == ""){
		alert('Sila pilih " No PT / No Lot " .');
  		document.${formName}.txtNoPTHtaam.focus(); 
		return; 
    }
	else if(AB!="" && BB==""){
		alert('Sila pembahagian Simati dengan betul.');
  		document.${formName}.txtBahagianSimati2.focus(); 
		return; 
    }
	else if(AB!="" && BB!="" && BB < AB){
		alert('Pembahagian bahagian pembawah si mati hendaklah lebih besar. Contoh: 2/4');
  		document.${formName}.txtBahagianSimati2.focus(); 
		return; 
    }
	else {
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) 
	    {
	        document.${formName}.method="post";
			doAjaxCall${formName}("Htaam");
			document.${formName}.mode.value="masukkan";
			document.${formName}.tabIdatas.value=tabIdatas;
			document.${formName}.tabIdtengah.value=tabIdtengah;
			document.${formName}.tabIdbawah.value=tabIdbawah;
			document.${formName}.tabIdtepi.value=tabIdtepi;
			document.${formName}.action.value="";
			document.${formName}.submit();
		}
    }
}
function edit_Htaam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";

		document.${formName}.mode.value="kemaskiniHtaam";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;		
		document.${formName}.action.value="";
		doAjaxCall${formName}("Htaam");
		document.${formName}.submit();
}

function save_Htaam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
var AB = document.${formName}.txtBahagianSimati1Up.value;
var BB = document.${formName}.txtBahagianSimati2Up.value;
	
	if(document.${formName}.socNegeriHtaamUp.value == ""){
		alert('Sila pilih " Negeri " .');
		socNegeriHtaamUp.focus();
		return; 
     }    
    else if(document.${formName}.socDaerahHtaamUp.value == ""){
		alert('Sila pilih " Daerah " .');
  		socDaerahHtaamUp.focus(); 
		return; 
    }
    else if(document.${formName}.socMukimHtaamUp.value == ""){
		alert('Sila pilih " Mukim " .');
  		socMukimHtaamUp.focus(); 
		return; 
    }
    else if(document.${formName}.socJenisHakmilikHtaamUp.value == ""){
		alert('Sila pilih " Jenis Hakmilik ".');
  		socJenisHakmilikHtaamUp.focus(); 
		return; 
    }
	else if(document.${formName}.txtNoHakmilikHtaamUp.value == ""){
		alert('Sila nyatakan " No Hakmilik " .');
  		document.${formName}.txtNoHakmilikHtaamUp.focus(); 
		return; 
    }
	else if(document.${formName}.txtNoPTHtaamUp.value == ""){
		alert('Sila pilih " No PT / No Lot " .');
  		document.${formName}.txtNoPTHtaamUp.focus(); 
		return; 
    }    
    else if(AB!="" && BB==""){
		alert('Sila pembahagian Simati dengan betul.');
  		document.${formName}.txtBahagianSimati2Up.focus(); 
		return; 
    }
	else if(AB!="" && BB!="" && BB < AB){
		alert('Pembahagian bahagian pembawah si mati hendaklah lebih besar. Contoh: 2/4');
  		document.${formName}.txtBahagianSimati2Up.focus(); 
		return; 
    }
	else {
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.${formName}.method="post";
		document.${formName}.mode.value="simpanHtaam";	
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;	
		document.${formName}.action.value="";
        doAjaxCall${formName}("Htaam");
		document.${formName}.submit();
		}
    }
}


function hapus_Htaam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";

	document.${formName}.mode.value="hapusHtaam";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;		
	document.${formName}.action.value="";
	doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}
function kembali_Htaam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";

		document.${formName}.mode.value="kembaliHtaam";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;		
		document.${formName}.action.value="";
		doAjaxCall${formName}("Htaam");
		document.${formName}.submit();
}
function batal_Htaam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="batalHtaam";
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;		
		document.${formName}.action.value="";
		doAjaxCall${formName}("Htaam");
		document.${formName}.submit();
}
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:0});
//-->
</script>
</body>

