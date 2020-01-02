<style type="text/css">
<!--
.style1 {color: #0000FF}
.style2 {color: #FF0000}
-->
</style>

        <input type="hidden" name="hitButt">
        <input type="hidden" name="action">
        <input type="hidden" name="mode">
        <input name="tabIdatas" type="hidden" id="tabIdatas" />
        <input name="tabIdtengah" type="hidden" id="tabIdtengah" />
        <input name="idpermohonansimati" type="hidden"  value="$idpermohonansimati"/>
        <input name="idPermohonan" type="hidden"  value="$idPermohonan"/>
        
        #foreach($list in $View)
            #set ($id = $list.idPermohonan)
            #set ($idPemohon = $list.idPemohon)
            #set ($idSimati = $list.idSimati)
            #set ($nofail = $list.noFail)
            #set ($namadaerah = $list.namadaerah)
            #set ($namanegeri = $list.namanegeri)
            #set ($namaPejabat = $list.nama_pejabat)
            #set ($alamat1 = $list.alamat_1)
            #set ($keterangan = $list.keterangan)
            #set ($seksyen = $list.seksyen)
            #set ($tarikhMohon = $list.tarikhMohon)
            #set ($namaPemohon = $list.namaPemohon)
            #set ($namasimati = $list.namaSimati)
            
          
            <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
            <!--
            <input name="idPermohonan" type="hidden"  value="$id"/>
            -->
            <input name="idPemohon" type="hidden"  value="$idPemohon"/>
           
            <input name="idtemp" type="hidden"  value="$id"/>
        #end
 
#foreach ($fail in $DetailPemohon)
	#set ($nofail1 = $fail.nofaillama)
    #set ($idfail2 = $fail.idfail)
    #set ($nofail2 = $fail.nofail)
	#set ($keterangan = $fail.keterangan)
    #set ($seksyen = $fail.seksyen)
	#set ($tarikhMohon = $fail.tarikhmohon)
	#set ($pemohon = $fail.namaPemohon)
    #set ($namasimati = $fail.namasimati)
    #set ($idStatus = $fail.idstatus)
    #set ($bkp = $fail.batalpentadbir)
    #set ($lp = $fail.lantikpentadbir)
    #set ($bpa = $fail.batalamanah)
    #set ($lpa = $fail.lantikamanah)
    #set ($ht = $fail.hartatinggal)
    #set ($idpermohonansimatilama = $fail.idpermohonansimatilama)
     <input name="idSimati" type="hidden"  value="$fail.idSimati"/>
     <input name="idpermohonansimatilama" type="hidden" value="$fail.idpermohonansimatilama" />
     
#end
	<input name="bkp" type="hidden" value="$bkp" />
     <input name="lp" type="hidden" value="$lp" />
     <input name="bpa" type="hidden" value="$bpa" />
     <input name="lpa" type="hidden" value="$lpa" />
     <input name="ht" type="hidden" value="$ht" />
<fieldset>
<legend>PEMBAHAGIAN PUSAKA KECIL</legend>
<fieldset>
<legend>MAKLUMAT UNTUK BORANG P</legend>
<input name="idStatus" type="hidden" value="$idStatus" />
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
      #if ($idStatus == "0" || $idStatus == "160" || $idStatus == "171")  
       <tr>
          <td width="35%" style="text-transform:uppercase; "><div align="right">No Rujukan Lama :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style42 style1">$!nofail1
            
          </div></td>
        </tr>
      #else
        <tr>
          <td width="35%" style="text-transform:uppercase; "><div align="right">No Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style42 style1">$!nofail2
              
              <input type="hidden" name="idfail2" value="$idfail2" />
          </div></td>
        </tr>
      #end   
      <tr>
          <td style="text-transform:uppercase;" valign="top"><div align="right">Status Fail :</div></td>
          <td style="text-transform:uppercase;"><div align="left" class="style1"><span class="style42">$!keterangan</span></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left" class="style1"><span class="style42">17</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
      </table>
    </td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style1"><span class="style42">$!tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style42 style1">$!namasimati</span>
            <span class="style1">
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/>
            </span></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left">
            <span class="style42 style1">$!pemohon</span>
                    <span class="style1">
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
                    </span></td>
        </tr>
      </table>
    </div></td>
  </tr>
</table>
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
          <div class="TabbedPanelsContent">
          <br>
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

<table width="98%" border="0" cellpadding="0" cellspacing="0">
<!-- HTA UNTUK INSERT BARU-->
  <tr>
    <td>
#if($show_htaa_add_table=="yes")
<fieldset>
<legend>MAKLUMAT HARTA TAK ALIH SIMATI (ADA HAKMILIK)</legend>
<table width="97%" border="0">
                                        <tr>
                                          <td width="80%" valign="top"><table width="100%" border="0">
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
                                                <select name="socNegeriHtaam" $readmodenegeri style="width: 225px;" 
                                                onChange="negerichange(this.value)">
                                              <option value="$negeri">$!negeriketeranganpemoP</option>        
                                  #foreach($listnegpomo in $listnegeri)
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                                  <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
                                  #end    
	                               #end
                                                </select>
                                                #else
  <select name="socNegeriHtaam" $readmodenegeri onChange="negerichange(this.value)" style="width: 225px;">
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
                                                <select name="socDaerahHtaam" $readmodedaerah onChange="daerahchange()" style="width: 225px;">
                                              <option value="$daerah">$listDaerahbyNegeriN</option>
				                                  #foreach($listdaerah in $listDaerahbyNegeri)
					                                  #if($daerah!=$listdaerah.id)
					                                        <option value="$listdaerah.id">$!listdaerah.nama</option>
					                                  #end    
					                              #end
											 </select>
                                                #else
  												<select name="socDaerahHtaam" style="width: 225px;" $readmodedaerah onChange="daerahchange()">
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
                                              <option value="$jenishakmilik">$listjenishakmilikK - $!listjenishakmiliN</option>

                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                  
                                  #if($jenishakmilik!=$listjenishakmilik.id)

                                                  <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $!listjenishakmilik.keterangan</option>

                                  #end    
	                               #end

                                                </select>
                                                #else
  <select name="socJenisHakmilikHtaam" style="width: 225px;" $readmode id="socJenisHakmilikHtaam">
    <option value="">Sila Pilih Jenis Hak Milik</option>

                                  #foreach($listjenishakmilik in $listJenisHakMilik)

    <option value="$listjenishakmilik.id">$listjenishakmilik.kod - $!listjenishakmilik.keterangan</option>
	                               #end
  </select>#end </td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;No Hakmilik</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtNoHakmilikHtaam" type="text" id="txtNoHakmilikHtaam" value="$!noHakmilik" style="text-transform:uppercase;" size="21" maxlength="20"/></td>
                                            </tr>
                                            <tr>
                                              <td class="style38"><div align="right"><font class="mandatory" color="#FF0000">*</font>&nbsp;No PT / No Lot</div></td>
                                              <td><div align="right">:</div></td>
                                              <td><input name="txtNoPTHtaam" type="text" id="txtNoPTHtaam" value="$!nopt" size="10" style="text-transform:uppercase;" maxlength="10" /></td>
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
                            <td class="style38"><div align="right">Nilai Harta (RM)</div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNilaiTarikhMohonHtaa" type="text" value="$nilai_Hta_memohon" style="text-align: right;" size="15" maxlength="8" onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" $readmode />
                            </label></td>
                          </tr>
                                           <tr>
                                              <td class="style38" valign="top"><div align="right">Catatan</div></td>
                                              <td width="1%" valign="top"><div align="right">:</div></td>
                                              <td><textarea name="txtCatatanHtaam" id="txtCatatanHtaam" cols="31" rows="5" style="text-transform:uppercase;">$!catatan</textarea></td>
                                            </tr> 
                                          </table>
                                          </tr>
                                    </table>
                   <table>
                    <tr>
                    <td><i><font color="red" style="font-size:11px">Perhatian</font> <font style="font-size:11px">: Sila pastikan label bertanda * </font> <font style="font-size:11px">diisi.</font></i></td>
                    </tr>
                    <tr>
                    <td><i><font color="red" style="font-size:11px">Perhatian</font> <font style="font-size:11px">: Sila pastikan membuat pengesahan penghantaran di bahagian PENGESAHAN PERMOHONAN</font>.</i></td>
                    </tr>
                    </table>
                    <br>
</fieldset>
#end
</td>
</tr>
<tr>
<td>
<!-- HTA UNTUK UPDATE-->
#foreach($listamid in $listHTAid)
#set ($id_permohonan_simati = $listamid.idpermohonansimati)
#if($show_htaa_update_table=="yes") 
                  
                    <fieldset><legend>HARTA TAK ALIH (ADA HAKMILIK)</legend>
                    <table width="97%" border="0">
                      <tr>
                      <input type="hidden" name="id_htaam" value="$listamid.idhta" />
                        <td valign="top" width="80%"><table width="100%" border="0">
                          <tr>
                            <td width="35%" class="style38"><div align="right"><span class="style41 style2">*</span>Negeri</div></td>
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
                            <td class="style38" ><div align="right"><span class="style41 style2">*</span>Daerah</div></td>
                            <td>:</td>
                            <td>#foreach($listdaerah in $listdaerah)
                              
                              #if($listamid.daerah==$listdaerah.id)
                              
                              #set($listDaerahbyNegeriK=$listdaerah.kod)
                              #set($listDaerahbyNegeriN=$listdaerah.nama)

                              #end 
                              #end
                              #if($listamid.daerah!="")
                              <select name="socDaerahHtaamUp" style="width: 225px;" $readmodedaerah onChange="daerahchangeup('1','0','0','0')" id="socDaerahHtaam">
                                              <option value="$listamid.daerah">$!listDaerahbyNegeriN</option>

                                  #foreach($listdaerah in $listDaerahbyNegeri)
                                 
                                  #if($listamid.daerah!=$listdaerah.id)
                                <option value="$listdaerah.id">$!listdaerah.nama </option>
                                  #end    
	                               #end
                              </select>
                              #else
  <select name="socDaerahHtaamUp" style="width: 225px;" $readmodedaerah onChange="daerahchangeup('1','0','0','0')">
    <option value="">Sila Pilih Daerah</option>
                                  #foreach($listDaerah in $listDaerahbyNegeri)
    <option value="$listDaerah.id">$!listDaerah.nama</option>
	                               #end
  </select>
                              #end </td>
                          </tr>
                          <tr>
                            <td class="style38" ><div align="right"><span class="style41 style2">*</span>Mukim</div></td>
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
                            <td class="style38" ><div align="right"><span class="style41 style2">*</span>Jenis Hakmilik </div></td>
                            <td>:</td>
                            <td>#foreach($listjenishakmilik in $listJenisHakMilik)
                              
                              #if($listamid.jenishakmilik==$listjenishakmilik.id)
                              
                              #set($listjenishakmilikK=$listjenishakmilik.kod)
                              #set($listjenishakmilikN=$listjenishakmilik.keterangan)

                              #end 
                              #end
                              #if($listamid.jenishakmilik!="")
                              <select name="socJenisHakmilikHtaamUp" style="width: 225px;" $readmode id="socJenisHakmilikHtaam2">
                                              <option value="$listamid.jenishakmilik">$!listjenishakmilikN</option>

                                  #foreach($listjenishakmilik in $listJenisHakMilik)
                                  
                                  #if($listamid.jenishakmilik!=$listjenishakmilik.id)

                                <option value="$listjenishakmilik.id">$!listjenishakmilik.keterangan</option>

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
                            <td class="style38"><div align="right">No Hakmilik </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoHakmilikHtaamUp" type="text" id="txtNoHakmilikHtaam2" value="$listamid.noHakmilik" size="21" $readmode style="text-transform:uppercase;"  maxlength="20" />
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">No PT / No Lot </div></td>
                            <td>:</td>
                            <td><label>
                              <input name="txtNoPTHtaamUp" type="text" id="txtNoPTHtaamUp" value="$listamid.nopt" size="34" $readmode style="text-transform:uppercase;"  maxlength="10"/>
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38"><div align="right">Bahagian Simati</div></td>
                            <td>:</td>
                            <td><input name="txtBahagianSimati1Up" type="text" id="txtBahagianSimati1Up" onKeyUp="javascript:validatePoskod(this,this.value)" $readmode value="$!listamid.basimati" size="14" maxlength="14" style="text-align: right;" />
                              /
                              <input name="txtBahagianSimati2Up" type="text" id="txtBahagianSimati2Up" $readmode onKeyUp="javascript:validatePoskod(this,this.value)" value="$!listamid.bbsimati" size="14" maxlength="14" onBlur="javascript:validateBahagian('txtBahagianSimati1Up','txtBahagianSimati2Up')" />                            </td></tr>
                        
                          <tr>
                            <td class="style38"><div align="right">Nilai Harta (RM)</div></td>
                            <td>:</td>
                            <td><label>
                             #if ($show_htaa_update_table=="yes" && $readmode != "")
                             	#if($nilai_Hta_memohon !="")
                              <input name="txtNilaiTarikhMohonHt" type="text" value="$Util.formatDecimal($nilai_Hta_memohon)" style="text-align: right;" size="15" maxlength="8" onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" $readmode />
                               #else
                                <input name="txtNilaiTarikhMohonHt" type="text" value="" style="text-align: right;" size="15" maxlength="8" onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" $readmode />
                                #end
                                  #else
                                  #set($nilai_Hta_memohon = $nilai_Hta_memohon)
                                  <input name="txtNilaiTarikhMohonHt" type="text" value="$nilai_Hta_memohon" style="text-align: right;" size="15" maxlength="8" onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" $readmode />
                                  #end
                            </label></td>
                          </tr>
                          <tr>
                            <td class="style38" valign="top"><div align="right">Catatan</div></td>
                            <td valign="top">:</td>
                            <td><textarea name="txtCatatanHt" id="txtCatatanHt" cols="31" rows="5" style="text-transform:uppercase;" $readmode>$!listamid.catatan</textarea>
                            </td>
                          </tr>
                        </table>
                        <table>
                    <tr>
                    <td><i><font color="red" style="font-size:11px">Perhatian</font> <font style="font-size:11px">: Sila pastikan label bertanda <span class="style44">*</span></font> <font style="font-size:11px">diisi.</font></i></td>
                    </tr>
                    <tr>
                    <td><i><font color="red" style="font-size:11px">Perhatian</font> <font style="font-size:11px">: Sila pastikan membuat pengesahan penghantaran di bahagian PENGESAHAN PERMOHONAN</font>.</i></td>
                    </tr>
                    </table>
                    </td>
                      </tr>
                    </table>
                    </fieldset>
#end
#end
</td>
</tr>
<tr>
<td>
#if($show_button=="yes")
                          				<table width="100%" border="0" align="center">
                                      	<tr>
										<td align="center">
								#if ($idStatus == "0" || $idStatus == "160" || $idStatus == "171" || $idStatus == "21")
                                        #if($show_simpan_add_htaam=="yes")
                                          <input type="button" name="tambahpenghutang2" id="tambahpenghutang2" value="Simpan" onClick="add_Htaam('0','0','0','0')"/>
                                        #end
                                         #if($show_save_update_htaam=="yes")
          <input type="button" name="cmdKemaskiniSimpan" id="cmdKemaskiniSimpan" value="Simpan" onClick="save_Htaam('0','0','0','0')"/>
                                       #end
                                        #if($show_batal_add_htaam=="yes")
                                          <input type="reset" name="cmdSimpan6" id="cmdSimpan6" value="Batal"/>
                                        #end
                                       
                                        #if ($idpermohonansimati == $id_permohonan_simati)
                                         #if($show_kemaskini_htaam=="yes")
          <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="edit_Htaam();" />
                                            #end
                                         #end
                                         
                                       
                                       #if ($idpermohonansimati == $id_permohonan_simati)
                                       #if($show_hapus_htaam=="yes")
                                          <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="hapus_Htaam('0','0','0','0')"/>
                                     #end
                                   	#end
                                    #if($show_batal_update_htaam=="yes")
                                          <input type="button" name="cmdBatal" id="cmdBatal" value="Tambah" onClick="HtaamView('0','0','0','0')" />
                                       #end
                                #end
                                    </td>
                                    </tr>
                                    </table>
                               
                    #end
</td>
</tr>
<tr>
<td>
<br>          
 <fieldset><legend>SENARAI HARTA TAK ALIH (ADA HAKMILIK)</legend>
 					<table>
                    <tr>
                    <td valign="top"><i>*</i></td>
                    <td><i>Untuk tujuan pembatalan atau perlantikan baru bagi Pemegang Amanah atau Pemegang Surat Kuasa Tadbir, sila pilih hakmilik harta tak alih yang berkenaan.</i></td>
                    </tr>
                    </table>
                                    <table width="98%">
                                      <tr  class="table_header">
                                        <td width="4%"><div align="center">NO</div></td>
                                        <td width="20%"><div align="center">NEGERI</div></td>
                                        <td width="25%"><div align="center">DAERAH</div></td>
                                        <td width="20%"><div align="center">MUKIM</div></td>
                                        <td width="15%"><div align="center">NO HAKMILIK</div></td>
                                        <td width="15%"><div align="center">NO PT/NO LOT</div></td>
                                        <td width="10%"><div align="center">BHGN SIMATI</div></td>
                                        <td width="15%"><div align="center"><input type="checkbox" name="all" onclick="javascript:doCheckAll();"></div></td>
                                        </tr>
                    #set($plko=0)
                #if ($listHTAbaru.size()>0)
                	#foreach ($data in $listHTAbaru)
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
                <td class="$row"><div align="center"><span class="style41">$!plko</span></div></td>
                <td class="$row"><a href="javascript:get_htaam($data.idhta);"><span class="style1">$!data.namanegeri</span></a></td>
                <td class="$row"><span class="style41">$!data.namadaerah </span></td>
                <td class="$row"><span class="style41">$!data.namamukim</span></td>
                <td class="$row"><span class="style41">$!data.noHakmilik</span></td>
                <td class="$row"><span class="style41">$!data.nopt</span></td>  
                #if($data.basimati!="" && $data.bbsimati!="")
                <td class="$row"><div align="center">$!data.basimati / $!data.bbsimati</div></td>
                #else
                <td class="$row"><div align="center">0 / 0</div></td>
                #end                                         
                <td class="$row"><span class="style41"><input type="checkbox" name="chkbox1" disabled="disabled"></span></td>
                </tr>
                	#end
                #end
                #if($listHTA.size()!=0 ) 
                    ##set($plko=0)
                                 #foreach($listam in $listHTA)
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
    <td class="$row"><div align="center"><span class="style41">$!plko</span></div></td>
    <td class="$row"><a href="javascript:get_htaam($listam.idhta);"><span class="style1">$!listam.namanegeri</span></a></td>
	<td class="$row"><span class="style41">$!listam.namadaerah</span></td>
    <td class="$row"><span class="style41">$!listam.namamukim</span></td>
    <td class="$row"><span class="style41">$!listam.noHakmilik</span></td>
    <td class="$row"><span class="style41">$!listam.nopt</span></td>    
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td class="$row"><div align="center"><span class="style41">$!listam.basimati / $!listam.bbsimati</span></div></td>
    #else
    <td class="$row"><div align="center">0 / 0</div></td>
    #end                                      
    <td class="$row"><span class="style41"><input type="checkbox" name="chkbox" value="$listam.idhta" $y ></span></td>
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
    <td class="$row"><div align="center"><span class="style41">$!plko</span></div></td>
    <td class="$row"><a href="javascript:get_htaam($listam.idhta)"><span class="style1">$!listam.namanegeri</span></a></td>
    <td class="$row"><span class="style41">$!listam.namadaerah</span></td>
    <td class="$row"><span class="style41">$!listam.namamukim</span></td>
    <td class="$row"><span class="style41">$!listam.noHakmilik</span></td>
    <td class="$row"><span class="style41">$!listam.nopt</span></td>
    #if($listam.basimati!="" && $listam.bbsimati!="")
    <td class="$row"><div align="center"><span class="style41">$!listam.basimati / $!listam.bbsimati</span></div></td>
    #else
    <td class="$row"><div align="center">0 / 0</div></td>
    #end 
    <td class="$row"><span class="style41"><input type="checkbox" name="chkbox" value="$listam.idhta" $y ></span></td>
  </tr>
                                      #end
                               #end
 <tr>
    <td height="40px" align="right" colspan="8"><input type="button" name="flag_btn" value="Simpan" onclick="javascript:simpanhakmilik()"></td>
  </tr>
  </table>
                                    #end
                              <br>      
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
                              <input type="hidden" name="idhtaam">
                              <input type="hidden" name="idhtaamid" value="$idhtaam" />
                              </fieldset>
</td>
</tr>
</table>
</fieldset>
</fieldset>
<br>
          </div>
          <div class="TabbedPanelsContent"></div>
        </div>
      </div>
       <p>&nbsp;</p>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<script>
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

function doUpdateCheckAll(){  
	var c = 0;
	if (document.${formName}.ids.length > 1){
		for (i = 0; i < document.${formName}.ids.length; i++){
			if (document.${formName}.ids[i].checked == false){
				c++
			}
		}
	} else {
		if (document.${formName}.ids.checked == false){
			c++
		}
	}
	  
	if(c > 0){
		document.${formName}.all.checked = false;
	} else {
		document.${formName}.all.checked = true;
	}				         
}

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

alert(AB+"  --  "+BB);

	if ( AB > BB ) {
		alert("Pembahagian bahagian pembawah si mati hendaklah lebih besar. Contoh: 2/4");
	}
}

function CheckAll(chk)
{
for (i = 0; i < chk.length; i++)
chk[i].checked = true ;
}

function UnCheckAll(chk)
{
for (i = 0; i < chk.length; i++)
chk[i].checked = false ;
}

function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}('Htaam');
	document.${formName}.action.value="";
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

function get_htaam(idhtaam)
{
	document.${formName}.method="post";
	document.${formName}.idhtaam.value = idhtaam;
	doAjaxCall${formName}("Htaam");
	document.${formName}.mode.value="getHtaam";
	document.${formName}.submit();
}

function edit_Htaam()
{
		document.${formName}.method="post";
		document.${formName}.mode.value="kemaskiniHtaam";
		doAjaxCall${formName}("Htaam");
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
		alert('Sila pilih " Jenis Hak Milik " .');
  		document.${formName}.socJenisHakmilikHtaam.focus(); 
		return; 
    }
	else if(AB!="" && BB==""){
		alert('Sila pembahagian Simati dengan betul.');
  		document.${formName}.txtBahagianSimati2.focus(); 
		return; 
    }
	else if(AB!="" && BB!="" && AB > BB){
		alert('Pembahagian bahagian pembawah si mati hendaklah lebih besar. Contoh: 2/4');
  		document.${formName}.txtBahagianSimati2.focus(); 
		return; 
    }
	else {
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) 
	    {
			document.${formName}.method="post";
			doAjaxCall${formName}('Htaam');
			document.${formName}.mode.value="masukkan";
			document.${formName}.submit();
		}
    }
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
		alert('Sila pilih " Jenis hak milik ".');
  		socJenisHakmilikHtaamUp.focus(); 
		return; 
    }
	else if(AB!="" && BB==""){
		alert('Sila pembahagian Simati dengan betul.');
  		document.${formName}.txtBahagianSimati2Up.focus(); 
		return; 
    }
	else if(AB!="" && BB!="" && AB > BB){
		alert('Pembahagian bahagian pembawah si mati hendaklah lebih besar. Contoh: 2/4');
  		document.${formName}.txtBahagianSimati2Up.focus(); 
		return; 
    }    
    else {
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="post";
			doAjaxCall${formName}('Htaam');
			document.${formName}.mode.value="simpanHtaam";
			document.${formName}.submit();
		}
    }
}

function hapus_Htaam(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	doAjaxCall${formName}('Htaam');
	document.${formName}.mode.value="hapusHtaam";
	document.${formName}.submit();
}

function negerichange(val){
	document.${formName}.method="post";
	document.${formName}.mode.value="negerichange";
	doAjaxCall${formName}('Htaam');
	document.${formName}.submit();	
}

function negerichangeup(){
	document.${formName}.method="post";
	document.${formName}.mode.value="negerichangeup";
	doAjaxCall${formName}('Htaam');	
	document.${formName}.submit();	
}

function daerahchange(){
	document.${formName}.method="post";
	document.${formName}.mode.value="daerahchange";
	doAjaxCall${formName}('Htaam');
	document.${formName}.submit();
}

function daerahchangeup(){
	document.${formName}.method="post";
	document.${formName}.mode.value="daerahchangeup";
	doAjaxCall${formName}('Htaam');	
	document.${formName}.submit();
}

function simpanhakmilik(){ 
	/*var total = 0;
	var max = ${formName}.chkbox.length;
	for (var idx = 0; idx < max; idx++) {
	if (eval("document.${formName}.chkbox[" + idx + "].checked") == true) {
		total += 1;
	   }		
	}
	if (total == 0){
			alert("Sila pilih 'Harta Tak Alih' terlebih dahulu");
		}else{*/
			document.${formName}.method="post";
			document.${formName}.mode.value="simpanflag";
			doAjaxCall${formName}('Htaam');	
			document.${formName}.submit();
		//}
	
}
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:0});
//-->
</script>
</body>
</html>
