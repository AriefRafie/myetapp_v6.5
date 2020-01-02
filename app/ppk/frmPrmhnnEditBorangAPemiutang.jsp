<html>
<head>
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
}
.style36 {font-size: 12}
.style38 {font-size: 10px}
-->
</style>
<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
<!--
.style39 {color: #0000FF}
-->
</style>
</head>
<body>
<table width="97%" border="0">
<tr>
<td>
#foreach($list in $View)
   #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
<input type="hidden" name="hitButt">
<input type="hidden" name="mode">
<input type="hidden" name="action">
<input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
<input name="idPermohonan" type="hidden"  value="$id"/>
<input name="idPemohon" type="hidden"  value="$idPemohon"/>
<input name="idSimati" type="hidden"  value="$idSimati"/>
<input name="idtemp" type="hidden"  value="$id"/>
<input type="hidden" name="idPermohonan" value="$IdPermohonan">
<input type="hidden" name="idpermohonansimati" value="$idpermohonansimati">
 <input type="hidden" name="simpanStatus" value="$SimpanStatus">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/></td>
#end


</td>
</tr>
  <tr>
    <td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:SimatiView();">PERMOHONAN</li>
    <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:HtaamView();">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:HAview()">HARTA ALIH</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
	<div class="TabbedPanelsContent">
      <div id="TabbedPanels2" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:SimatiView()">SIMATI</li>
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:PemohonView()">PEMOHON</li>
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:WarisView()">WARIS</li>
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:PemiutangView()">PEMIUTANG</li>
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:PenghutangView()">PENGHUTANG</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent">
		  <table width="99%">
                                    #if($nk_update_penting=="yes")
	                                 #set ($checked2a="")
	                                 #set ($checked1a="")
	                                 #set ($checkedDropMenu="")
                                
                                		#set ($kplama="")
                                		#set ($kplama1="")
                                		#set ($nama="")
                                		#set ($nama1="")
										
                                       #foreach($listob in $listPentingbyIDOB)
                                       	#if ($listob.pemiutang == "2")
                                       		#set ($kplama=$listob.nokplama)
                                       		#set ($nama=$listob.nama_Ob)
                                       		#set ($checked2a="checked")
	                                 		#set ($checked1a="")
	                                 		#set ($checkedDropMenu="")
	                                 		#set ($checkedDropMenu="")
	                                 		#set ($jenishutang1="")
	                                 		#set ($jenishutang2="readonly class=disabled")
                                		#elseif ($listob.pemiutang == "1")
                                			#set ($kplama1=$listob.nokplama)
                                			#set ($nama1=$listob.nama_Ob)
                                			#set ($checked2a="")
			                                #set ($checked1a="checked")
			                                #set ($checkedDropMenu="readonly")
			                                #set ($jenishutang1="readonly class=disabled")
	                                 		#set ($jenishutang2="")
	                                 		#set ($checkedDropMenu="disabled")
                                		#end
     <tr>							 #set ($jenishutang = $listob.pemiutang)
                                         <td>
                                          <fieldset>
                                          <legend>MAKLUMAT PEMIUTANG</legend>
                                          
                                          <table width="100%">
                              <tr>
                               <td width="80%" valign="top"><table width="100%">
                                   <input type="hidden" name="txtIdSimatiPenting" value="$listob.idSimati" >   
                                    <input type="hidden" name="txtIdOb" value="$listob.idOb" >      
                                <tr>
                                  <td width="29%" ><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Kategori Pemiutang</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><input type="radio" name="socJenisHutangPentingU" id="socJenisHutangPentingU" value="2" $checked2a onClick="jenis_hutangU(this.value);" disabled/>&nbsp;<span class="style38">INDIVIDU</span>&nbsp;&nbsp;<input type="radio" name="socJenisHutangPentingU" id="socJenisHutangPentingU" value="1" $checked1a onClick="jenis_hutangU(this.value);" disabled/>&nbsp;<span class="style38">INSTITUSI</span></td>
                                </tr> 
                                 #if($jenishutang == "2")
                                  <tr id="kpb">
                                  <td width="29%" ><div align="right"><span class="style38">MyID Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36">
                                     <input name="txtNoKPBaru1Penting" type="text" id="txtNoKPBaru1Penting" style="width: 50px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Penting')" value="$listob.nokpbaru1" $jenishutang1 size="7" maxlength="6" $readmode/>-<input name="txtNoKPBaru2Penting" type="text" id="txtNoKPBaru2Penting" style="width: 20px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')" value="$listob.nokpbaru2" $jenishutang1 size="1" maxlength="2" $readmode/>-<input name="txtNoKPBaru3Penting" type="text" id="txtNoKPBaru3Penting"  style="width: 40px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')" value="$listob.nokpbaru3" $jenishutang1 size="5" maxlength="4" $readmode/>
                                  </td>
                                </tr>
                                <tr id="kpl">
                                  <td ><div align="right"><span class="style38">MyID Lama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPenting" type="text" id="txtNoKPLamaPenting" value="$kplama" $jenishutang1 size="8" maxlength="8" style="text-transform:uppercase;"  $readmode />
                                  </label></td>
                                </tr>
                                
                                <tr id="kps">
                                  <td><div align="right"><span class="style38">Lain-lain MyID</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
 								 <select name="socJenisKPLainPenting"  style="width: 146px; text-transform:uppercase;" $checkedDropMenu $readmode id="socJenisKPLainPenting" >
								   #if($listob.jeniskp=="1")
									   <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      <option value="3">Pasport</option>
	                               #elseif($listob.jeniskp=="2")
                                      <option value="2">Polis</option>
                                      <option value="1">Tentera</option>
                                      <option value="3">Pasport</option>
								   #elseif($listob.jeniskp=="3")
                                      <option value="3">Pasport</option>
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
	                               #else
                                      <option value="">Sila Pilih Jenis KP</option>
                                      <option value="1">Tentera</option>
                                      <option value="2">Polis</option>
                                      <option value="3">Pasport</option>
	                               #end
                                    </select>&nbsp;<input name="txtNoKPLainPenting" type="text" id="txtNoKPLainPenting" value="$listob.nokplain" $jenishutang1 size="9" maxlength="9" $readmode  style="text-transform:uppercase;"  /></td>
                                </tr>
                                <tr id="kpn">
                                    <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Pemiutang</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting" type="text" id="txtNamaOBPenting" value="$nama" size="34" $jenishutang1 style="text-transform:uppercase;"  $readmode/>
                                    </label></td>
                                  </tr>
                                 #end
                                 #if($jenishutang == "1")
                                <tr id="adaftar">
                                  <td ><div align="right"><span class="style38">No Pendaftaran</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPenting1" type="text" id="txtNoKPLamaPenting1" value="$kplama1" $jenishutang2 size="8" maxlength="8" style="text-transform:uppercase;"  $readmode />
                                  </label></td>
                                </tr>
                                
								
                                  
                                   <tr id="aname">
                                    <td><div align="right"><span class="style38">Nama Institusi</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting1" type="text" value="$nama1" id="txtNamaOBPenting1" size="34" $jenishutang2 $readmode style="text-transform:uppercase;" />
                                    </label></td>
                                  </tr>
                                  #end
                                   #if ($readmode == "disabled")
                                  	 #set ($value = $Util.formatDecimal($listob.nilaihutang))
                                  #else
                                  		#set ($value1 = $Util.formatDecimal($listob.nilaihutang))
                                  		#set ($value = $EkptgUtil.RemoveSymbol($value1))
                                  #end
                                  <tr>
                                  <td><div align="right"><span class="style38">Nilai Hutang (RM)</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36">
                                  <input name="txtNilaiHutangPenting" type="text" id="txtNilaiHutangPenting" style="text-transform:uppercase; text-align: right;" value="$!value" size="20" maxlength="10"  $readmode onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validateModal(this,this.value,this.value);"/>
                                  </td>
                                </tr>     
                                 <tr>
                                  <td><div align="right"><span class="style38">No Akaun</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                  <input name="txtNoAkaunPenting" type="text" id="txtNoAkaunPenting" style="text-transform:uppercase;" value="$listob.noakaun" size="20" maxlength="20"  $readmode />
                                  </label></td>
                                </tr>
                                 <tr>
                                  <td class="style38" width="29%"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat</div></td>
                                  <td width="1%"><div align="right" class="style38">:</div></td>
                                  <td width="70%"><label>
 									
                                    <input name="txtAlamatTerakhir1Penting" type="text" id="txtAlamatTerakhir1Penting" value="$listob.alamat1" size="34"  $readmode style="text-transform:uppercase;"  />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2Penting" type="text" id="txtAlamatTerakhir2Penting" value="$listob.alamat2" size="34" $readmode style="text-transform:uppercase;"  />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><input name="txtAlamatTerakhir3Penting" type="text" id="txtAlamatTerakhir3Penting" value="$listob.alamat3" size="34" $readmode style="text-transform:uppercase;"  /></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtPoskodPenting" type="text" id="txtPoskodPenting" value="$listob.poskod" size="5" maxlength="5" $readmode style="text-transform:uppercase;" onBlur="validLength()" />
                                  </label></td>
                                </tr>
                                
                                        <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri</div></td>
                                   
                                    <td><div align="right" class="style38">:</div></td>
                                    #foreach($listnegpomo in $listnegeri)
                                    	#if ($negeri == "" || $negeri == "0")
                                            #if($listob.idnegeri==$listnegpomo.id_Negeri )
                                                 #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                 #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                            #end 
                                         #else
                                          	#if($negeri==$listnegpomo.id_Negeri)
                                                 #set($negerikodpemoP=$listnegpomo.kod_Negeri)
                                                 #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                            #end
                                         #end
                                    #end  
	                               <td>
	                              #if($listob.idnegeri!="" && $negeri == "0")
                                  <select name="socNegeriPenting" style="width: 225px;" $readmode id="socNegeriPenting" onChange="getBandar()">
                                   <option value="$listob.idnegeri">$!negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                  #if($listob.idnegeri!=$listnegpomo.id_Negeri)
	                               <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
                                  #end    
	                               #end
                                  </select>
                                  #elseif($listob.idnegeri!="" && $negeri != "0")
                                  <select name="socNegeriPenting" style="width: 225px;" $readmode id="socNegeriPenting" onChange="getBandar()">
                                   <option value="$negeri">$!negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                  #if($negeri!=$listnegpomo.id_Negeri)
	                               <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
                                  #end    
	                               #end
                                  </select>
                                  
                                  #else
                                  <select name="socNegeriPenting" style="width: 225px;" $readmode id="socNegeriPenting" onChange="getBandar()">
                                   <option value="">Sila Pilih Negeri</option>
                                  #foreach($listnegpomo in $listnegeri)
	                               <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
	                               #end
                                  </select></td>
                                  #end</tr>
                                  <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><!--<label>
                                    <input name="txtBandarPenting" type="text" id="txtBandarPenting" value="$listob.bandar" size="34" $readmode style="text-transform:uppercase;"/>
                                  </label>--><select name="socDaerah" id="socDaerah" style="width: 225px; text-transform:uppercase;" $readmode>
										  #if ($listob.idbandar == "" || $listob.idbandar == "0")                                         
                                              <option value="0" >SILA PILIH</option>
                                              #foreach ($listBandarTetap in $listBandarbyNegeri)
                                              <option value="$listBandarTetap.idbandarnegeri">$!listBandarTetap.nama</option>
                                              #end
                                          #else
                                              #foreach ($listBandarTetap1 in $ListBandar)
                                              		#if ($listBandarTetap1.id == $idBandar || $listBandarTetap1.id == $listob.idbandar)
                                              <option value="$listBandarTetap1.id" selected>$!listBandarTetap1.nama</option>
                                              		#end
                                              #end
                                              <option value="0" >SILA PILIH</option>
                                              #foreach ($listBandarTetap in $listBandarbyNegeri)
                                              <option value="$listBandarTetap.idbandarnegeri">$!listBandarTetap.nama</option>
                                              #end
                                          #end</select></td>
                                </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Butiran Hutang</div></td>
                                          <td valign="top"><div align="right" class="style38">:</div></td>
                                          <td><textarea name="txtButiranHutangPenting" cols="31"  rows="3"  $readmode id="txtButiranHutangPenting" style="text-transform:uppercase;" >$listob.butiranhutang</textarea></td>
                                      </tr> 
                                </table>
                                <br>
					<br>
					<br><table>
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan label bertanda <font color="#ff0000">*</font> 
                      diisi.</i></td>
                    </tr>
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan maklumat pada tab SIMATI, PEMOHON 
                      dan HARTA TAK ALIH diisikan dan seterusnya membuat pengesahan 
                      penghantaran pada tab PENGESAHAN PERMOHONAN.</i></td>
                    </tr>
                    </table>
                      <br>
                      <br>
                                </td>
                              </tr>
                            </table>
                                         
                                          </fieldset>
                                         
                                         
                                         </td>
                                       </tr>
                                       #end
                                       
                                  #end     
                                       
                                       
                                #if($nk_tambah_penting=="yes")
	                                 #set ($jenishutang1="")
	                                 #set ($jenishutang2="")
	                                 #set ($checked2a="")
	                                 #set ($checked1a="")
	                                 #set ($checkedDropMenu="")
                                #if ($socJenisHutang=="1")
	                                 #set ($jenishutang1="readonly class=disabled")
	                                 #set ($jenishutang2="")
	                                 #set ($checked2a="")
	                                 #set ($checked1a="checked")
	                                 #set ($checkedDropMenu="disabled")                               	
                                #elseif ($socJenisHutang=="2")
                                	 #set ($jenishutang1="")
	                                 #set ($jenishutang2="readonly class=disabled")
	                                 #set ($checked2a="checked")
	                                 #set ($checked1a="")
	                                 #set ($checkedDropMenu="")
	                                 #set ($checkedDropMenu="") 
                                #else
                                	#set ($jenishutang1="")
	                                 #set ($jenishutang2="")
	                                 #set ($checked2a="")
	                                 #set ($checked1a="")
	                                 #set ($checkedDropMenu="")
                                #end
                                       <tr>
                            <td width="100%">
                            
                            <fieldset>
                            <legend>MAKLUMAT PEMIUTANG</legend>
                            
                            <table width="100%" border="0">
                              <tr>
                                <td width="80%" valign="top"><table width="100%">
                                   <input type="hidden" name="txtIdSimatiPenting" value="$id_Simati" >       
                                  <tr>
                                  <td width="29%" ><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Kategori Pemiutang</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><input type="radio" name="socJenisHutangPentingU" id="socJenisHutangPentingU" value="2" $checked2a onClick="hiddendisplay();"/>&nbsp;<span class="style38">INDIVIDU</span>&nbsp;&nbsp;<input type="radio" name="socJenisHutangPentingU" id="socJenisHutangPentingU" value="1" $checked1a onClick="hiddendisplay();"/>&nbsp;<span class="style38">INSTITUSI</span></td>
                                </tr> 
							                
                                  <tr id="kpb">
                                  <td width="29%" ><div align="right"><span class="style38">MyID Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><input name="txtNoKPBaru1Penting" id="txtNoKPBaru1Penting" style="width: 50px;" type="text" $jenishutang1 $readmode size="7" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Penting')" value="$!nokpbaru1"/>-<input name="txtNoKPBaru2Penting" id="txtNoKPBaru2Penting" style="width: 20px;" type="text" $jenishutang1 $readmode size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')" value="$!nokpbaru2"/>-<input name="txtNoKPBaru3Penting" id="txtNoKPBaru3Penting"  style="width: 40px;" type="text" $jenishutang1 $readmode size="5" maxlength="4" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Penting')" value="$!nokpbaru3"/>
                                  </td>
                                </tr>                                     
                                 <tr id="kpl">
                                  <td ><div align="right"><span class="style38">MyID Lama</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPenting" type="text" id="txtNoKPLamaPenting" value="$!nolama1" size="8" maxlength="8" style="text-transform:uppercase;"  $jenishutang1 $readmode/>
                                  </label></td>
                                </tr>                                
                                <tr id="kps">
                                  <td><div align="right"><span class="style38">Lain-lain MyID</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36" style="text-transform:uppercase;"> <select name="socJenisKPLainPenting"  style="width: 146px;" $checkedDropMenu $readmode>
								   #if($jenisKp=="1")
                                      <option value="1">TENTERA</option>
                                      <option value="2">POLIS</option>
                                      <option value="3">PASPORT</option>
	                               #elseif($jenisKp=="2")
                                      <option value="2">POLIS</option>
                                      <option value="1">TENTERA</option>
                                      <option value="3">PASPORT</option>
								   #elseif($jenisKp=="3")
                                      <option value="3">PASPORT</option>
                                      <option value="1">TENTERA</option>
                                      <option value="2">POLIS</option>
	                               #else
                                      <option value="">SILA PILIH</option>
                                      <option value="1">TENTERA</option>
                                      <option value="2">POLIS</option>
                                      <option value="3">PASPORT</option>
	                               #end
                                    </select>&nbsp;<input name="txtNoKPLainPenting" type="text" id="textfield5" value="$!nolain" size="9" maxlength="9" style="text-transform:uppercase;" $jenishutang1 $readmode/>
									 </td>
                                </tr>
                                 <tr id="adaftar">
                                  <td ><div align="right" class="style38">No Pendaftaran</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtNoKPLamaPenting1" type="text" id="txtNoKPLamaPenting1" value="$!nolama2" size="8" maxlength="8" $jenishutang2 $readmode style="text-transform:uppercase;"  />
                                  </label></td>
                                </tr>
                                
                                  <tr id="kpn">
                                    <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Pemiutang</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting" type="text" id="txtNamaOBPenting" value="$!ob1" size="34" $jenishutang1 $readmode style="text-transform:uppercase;" />
                                    </label></td>
                                  </tr>
                                  <tr id="aname">
                                    <td><div align="right"><span class="style38">Nama Institusi</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting1" type="text" id="txtNamaOBPenting1" value="$!ob2" size="34" $jenishutang2 $readmode style="text-transform:uppercase;" />
                                    </label></td>
                                  </tr>
                                  <tr>
                                  <td><div align="right"><span class="style38">Nilai Hutang (RM)</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36">
			
                                  <input name="txtNilaiHutangPenting" type="text" id="txtNilaiHutangPenting" value="$!hutangrm" style="text-transform:uppercase;text-align: right;" size="20" maxlength="10"  $readmode  onKeyUp="javascript:validateIC(this,this.value,'txtNilaiHutangPenting')" onBlur="validateModal(this,this.value,this.value);"/>
                                  </td>
                                </tr>
                                <tr>
                                  <td><div align="right"><span class="style38">No Akaun</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                  <input name="txtNoAkaunPenting" type="text" id="txtNoAkaunPentingU" value="$!akaun" style="text-transform:uppercase;" size="20" maxlength="20" $readmode/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38" width="29%"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Alamat</div></td>
                                  <td width="1%"><div align="right" class="style38">:</div></td>
                                  <td width="70%"><label>
                                    <input name="txtAlamatTerakhir1Penting" type="text" id="txtAlamatTerakhir1Penting" value="$!alamatakhir1" size="34"  $readmode style="text-transform:uppercase;" />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2Penting" type="text" id="txtAlamatTerakhir2Penting" value="$!alamatakhir2" size="34" $readmode style="text-transform:uppercase;" />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><input name="txtAlamatTerakhir3Penting" type="text" id="txtAlamatTerakhir3Penting" value="$!alamatakhir3" size="34" $readmode style="text-transform:uppercase;" /></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Poskod</div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtPoskodPenting" type="text" id="txtPoskodPenting" value="$!poskodakhir" style="text-transform:uppercase;" size="5" maxlength="5" $readmode onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()"/>
                                  </label></td>
                                </tr>
                                
                                <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Negeri </div></td>
                                   
                                    <td><div align="right" class="style38">:</div></td>
                                    #foreach($listnegpomo in $listnegeri)
                                    #if($negeri==$listnegpomo.id_Negeri)
		                                 #set($negerikodpemoP=$listnegpomo.kod_Negeri)
		                                 #set($negeriketeranganpemoP=$listnegpomo.nama_Negeri)
                                    #end 
                                    #end  
                                  
	                               <td>
	                               
	                              #if($negeri!="")
                                  <select name="socNegeriPenting" style="text-transform:uppercase; width: 225px;" $readmode onChange="getBandar()">
                                   <option value="$negeri">$!negeriketeranganpemoP</option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                  #if($negeri!=$listnegpomo.id_Negeri)
                                    
	                               <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  </select>
                                  #else
                                  <select name="socNegeriPenting" style="text-transform:uppercase; width: 225px;" $readmode onChange="getBandar()">
                                   <option value="">Sila Pilih Negeri</option>
                                  #foreach($listnegpomo in $listnegeri)
                                 
                                
	                               <option value="$listnegpomo.id_Negeri">$!listnegpomo.nama_Negeri</option>
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  </select>                                    </td>
                                  #end </tr>
                                  <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bandar</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td><label>
                                    <!--<input name="txtBandarPenting" type="text" id="txtBandarPenting" value="$!bandarakhir" size="34" $readmode style="text-transform:uppercase;" />-->
                                  </label>
                                  <select name="socDaerah" id="socDaerah" style="width: 225px;" $setmode2>
										  #if ($idBandar == "" || $idBandar == "0")                                         
                                              <option value="0" >SILA PILIH</option>
                                              #foreach ($listBandarTetap in $listBandarbyNegeri)
                                              <option value="$listBandarTetap.idbandarnegeri">$!listBandarTetap.nama</option>
                                              #end
                                          #else
                                              #foreach ($listBandarTetap in $listBandarbyNegeri)
                                              		#if ($listBandarTetap.idbandarnegeri == $idBandar)
                                              <option value="$listBandarTetap.idbandarnegeri" selected>$!listBandarTetap.nama</option>
                                              		#end
                                              #end
                                              <option value="0" >SILA PILIH</option>
                                              #foreach ($listBandarTetap in $listBandarbyNegeri)
                                              <option value="$listBandarTetap.idbandarnegeri">$!listBandarTetap.nama</option>
                                              #end
                                          #end</select>
                                  </td>
                                </tr>
                                <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Butiran Hutang</div></td>
                                          <td valign="top"><div align="right" class="style38">:</div></td>
                                          <td><textarea name="txtButiranHutangPenting" cols="34"  rows="3"  $readmode style="text-transform:uppercase;" id="txtButiranHutangPenting" >$!butiran</textarea></td>
                                      </tr>
                                </table></td>
                              </tr>
                            </table>
                            <br>
					<br>
					<br><table>
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan label bertanda <font color="#ff0000">*</font> 
                      diisi.</i></td>
                    </tr>
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan maklumat pada tab SIMATI, PEMOHON 
                      dan HARTA TAK ALIH diisikan dan seterusnya membuat pengesahan 
                      penghantaran pada tab PENGESAHAN PERMOHONAN.</i></td>
                    </tr>
                    </table>
                      <br>
                      <br>
                            </fieldset>                            </td>
                          </tr>
                                       
                               #end        
                                       
            #if($nk_button_penting=="yes") 
                         
                          <tr>
                            <td>  <table width="100%" border="0" align="center">
                                  <tr>
                                  <td align="center">
							#if ($idstatus=="150" || $idstatus=="171")
                                  #if($buttonpenting=="tambah")
                              <input type="button" name="tambahpenting" id="tambahpenting2" value="Simpan" onKeyPress="setSelected(0,4,0,0);tambah_simpan_penting()" onClick="setSelected(0,4,0,0);tambah_simpan_penting()"/>								
                              <input type="reset" name="cmdBatal" id="cmdBatal" value="Batal" onClick="setSelected(0,4,0,0);cancelwaris()"/>    
                                     #else
          					  <input type="button" name="tambahpenting" id="tambahpenting3" value="$buttonpenting" onKeyPress="setSelected(0,4,0,0);tambah_penting()" onClick="setSelected(0,4,0,0);tambah_penting()"/>
                                    #if($buttonpenting=="Simpan")
                                    #end
                                     <input type="button" name="hapuspenting" id="hapuspenting" value="Hapus"  onkeypress="setSelected(0,4,0,0);hapus_penting()" onClick="setSelected(0,4,0,0);hapus_penting()" />
                                     <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah"  onclick="PemiutangView('0','4','0','0')"/> 
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
                            
                            <fieldset>
                            <legend>SENARAI PEMIUTANG</legend>
                              <table width="100%" >
                                <tr>
                                  <td>
                                  #set($saksic=0)
                                  #foreach($listpenting in $listPenting)
                                  #if($listpenting.taraf==2)
                                  #set($saksic=$saksic+1)
                                  #end
                                  #end
                                  #if($listPenting.size()!=0 && $saksic!=0)
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="2%"><div align="center">NO</div></td>
                                      <td width="25%"><div align="center">NAMA PEMIUTANG</div></td>
                                      <td width="13%"><div align="center">MyID</div></td>                                      
                                      <td width="12%"><div align="center">NO PENDAFTARAN</div></td>
                                      <td width="12%"><div align="center">NO AKAUN</div></td>
                                      <td width="18%"><div align="right">NILAI HUTANG(RM)</div></td>
                                      <td width="24%"><div align="center">BUTIRAN HUTANG</div></td>
                                    </tr>
                                    #set($peno=0)
                                      #foreach($listpenting in $listPenting)
                                      #if($listpenting.taraf==2)
                                        #set($peno=$peno+1)
         							 #if($peno%2!=0)
                                    <tr bgcolor="white">
                                    <td class="row1"><div align="center" class="style41">$peno</div></td>
                                      <td class="row1" style="text-transform:uppercase;">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb');hiddendisplay1('$listpenting.pemiutang');">
                                       <div class="style42 style39" > $listpenting.nama_Ob</div>   
                                        <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row1" style="text-transform:uppercase;"><div align="center" >
                                      #if ($listpenting.nokpbaru!="")
                                      	$listpenting.nokpbaru
                                      #elseif ($listpenting.nokplama!="") 
                                      	$listpenting.nokplama
                                      #else
                                      	$listpenting.nokplain
                                      #end
                                        </div></td>
                                      <td class="row1" style="text-transform:uppercase;">
                                      #if ($listpenting.pemiutang=="1")
                                      	$listpenting.nokplama
                                      #end
                                      </td>
                                      <td class="row1" style="text-transform:uppercase;">$listpenting.noakaun</td>
                                      <td class="row1"><div align="right" >$listpenting.nilaihutang</div></td>
                                      <td class="row1" style="text-transform:uppercase;">$listpenting.butiranhutang</td>
                                      
                                         #if($listpenting.status_Ob=="1")
                                         #set($stat="Dewasa/Waras")
                                         #end
                                         #if($listpenting.status_Ob=="2")
                                         #set($stat="Belum Dewasa")
                                         #end
                                         #if($listpenting.status_Ob=="3")
                                         #set($stat="Hilang")
                                         #end
                                         #if($listpenting.status_Ob=="4")
                                         #set($stat="Tidak Sempurna Akal")

                                         #end
                                         #if($listpenting.status_Ob=="" || $listpenting.status_Ob=="0")
                                         #set($stat="")
                                         #end                                    </tr>
                                    #else
                                        <tr >
                                  
                                   <td class="row2"><div align="center" class="style41">$peno</div></td>
                                      <td class="row2" style="text-transform:uppercase;">
                                      
                                        <a href="javascript:edit_item('$listpenting.idOb')">
                                       <div class="style42 style39"> $listpenting.nama_Ob</div>   
                                        <input type="hidden" name="idob" value="$listpenting.idOb" />
                </a>                                      </td>
                                      <td class="row2" style="text-transform:uppercase;"><div align="center">$listpenting.nokpbaru</div></td>
                                      <td class="row2" style="text-transform:uppercase;">
                                      #if ($listpenting.pemiutang=="1")
                                      	$listpenting.nokplama
                                      #end
                                      </td>
                                      <td class="row2" style="text-transform:uppercase;">$listpenting.noakaun</td>
                                      <td class="row2"><div align="right" >$listpenting.nilaihutang</div></td>
                                      <td class="row2" style="text-transform:uppercase;">$listpenting.butiranhutang</td>
                                        #if($listpenting.status_Ob=="1")
                                         #set($statu="Dewasa/Waras")
                                         #end
                                         #if($listpenting.status_Ob=="2")
                                         #set($statu="Belum Dewasa")
                                         #end
                                         #if($listpenting.status_Ob=="3")
                                         #set($statu="Hilang")
                                         #end
                                         #if($listpenting.status_Ob=="4")
                                         #set($statu="Tidak Sempurna Akal")
                                         #end
                                         #if($listpenting.status_Ob=="" || $listpenting.status_Ob=="0")
                                         #set($statu="")
                                         #end                                    </tr>
                                    #end
                                    #end
                                    #end
                                  </table>
                                  #else
                                  <table width="100%">
                                    <tr  class="table_header">
                                     <td width="25%"><div align="center">NAMA PEMIUTANG</div></td>
                                      <td width="15%"><div align="center">MyID BARU</div></td>                                      
                                      <td width="12%"><div align="center">NO PENDAFTARAN</div></td>
                                      <td width="12%"><div align="center">NO AKAUN</div></td>
                                      <td width="10%"><div align="right">NILAI HUTANG(RM)</div></td>
                                      <td width="24%"><div align="center">BUTIRAN HUTANG</div></td>
                                    </tr>
                                   </table> 
                                    <table width="100%" bgcolor="#FFFFFF">
                                    <tr>
                                      <td width="100%"><div align="left">TIADA REKOD</div>
                                      </td>
                                      </tr>
                                   </table> 
                                     
                                  #end                                 </td>
                                </tr>
                              </table>  
                              </fieldset>                            </td>
                          </tr>
                    </table>
		  </div>
		  <div class="TabbedPanelsContent"></div>
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
<script>
<!-- TABS -->
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

function WarisView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Warisview";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}

function PemohonView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemohonview";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.submit();
}

function SimatiView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.submit();
}

function HtaamView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}

function HAview() {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.submit();
}

function NAview() {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_nilai_harta";
	document.${formName}.hitButt.value="nilai_harta";
	document.${formName}.submit();
}

function PenghutangView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Penghutangview";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
}

function PemiutangView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemiutangview";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.submit();
}
function SaksiView() {
	document.${formName}.method="post";
	
	document.${formName}.mode.value="Saksiview";
	document.${formName}.hitButt.value="Saksi";	
	document.${formName}.submit();
}

function PentingView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pentingview";
	doAjaxCall${formName}("Penting");
	document.${formName}.submit();
}

function PengesahanView() {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();
}


function kembali_simati(){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	document.${formName}.hitButt.value="kembali_simati";
	document.${formName}.submit();
}

function setSelected()
 {
    document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;	
}
function cancelwaris() {
	document.${formName}.reset();
	document.${formName}.txtNoKPBaru1Waris.focus();
	document.${formName}.mode.value="Pemiutangview";
	document.${formName}.hitButt.value="Pemiutang";
	document.${formName}.submit();
}

<!-- PEMIUTANG -->
function kemaskini_penting(){
	document.${formName}.method="post";
	document.${formName}.mode.value="kemaskini_pemiutang";
	doAjaxCall${formName}("Pemiutang");
	
	document.${formName}.submit();
}

function simpan_penting(){
	document.${formName}.method="post";
	document.${formName}.mode.value="simpan_pemiutang";
		
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.submit();	
}

function tambah_saksi_baru(){
	document.${formName}.method="post";
	document.${formName}.mode.value="tambah_pemiutang_baru";
	
	doAjaxCall${formName}("Pemiutang");
    document.${formName}.submit();

}

function hapus_penting(){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
	    document.${formName}.mode.value="hapus_pemiutang";
		
		doAjaxCall${formName}("Pemiutang");
		document.${formName}.submit();
	}
}
function batalpenting(){
	document.${formName}.method="post";
    document.${formName}.mode.value="PemiutangView";
	
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.submit();
}


function tambah_penting() {
	
	if( document.${formName}.tambahpenting.value == "Simpan" ) {
	    input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.${formName}.mode.value="simpan_pemiutang";
		
	    doAjaxCall${formName}("Pemiutang");
		document.${formName}.submit();
		}
	}

	if( document.${formName}.tambahpenting.value == "Kemaskini" ) {
		document.${formName}.method="post";
		document.${formName}.mode.value="KemaskiniPemiutang";
		
    	doAjaxCall${formName}("Pemiutang");
		document.${formName}.submit();
	}
}
function hiddendisplay1(val) {

	if (val==2){
	document.getElementById("kpb").style.display="";
	document.getElementById("kpl").style.display="";
	document.getElementById("kps").style.display="";
	document.getElementById("kpn").style.display="";
	document.getElementById("aname").style.display="none";
	document.getElementById("adaftar").style.display="none";
	
	} 
	else if (val==1){
	document.getElementById("kpb").style.display="none";
	document.getElementById("kpl").style.display="none";
	document.getElementById("kps").style.display="none";
	document.getElementById("kpn").style.display="none";
	document.getElementById("aname").style.display="";
	document.getElementById("adaftar").style.display="";
	}
		
}
function edit_item(idOb) {

	document.${formName}.method="post";
	document.${formName}.mode.value="GetPemiutang";
	document.${formName}.idOb.value=idOb;
	
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.submit();
}

function jenis_hutangU(){
	if (document.${formName}.socJenisHutangPentingU[0].checked==true){
		document.${formName}.method="post";
		document.${formName}.mode.value="Pemiutangview";
		document.${formName}.action.value="";
		document.${formName}.socJenisHutangPentingU.value="2";
		doAjaxCall${formName}("Pemiutang");
		document.${formName}.submit();
	}
	if (document.${formName}.socJenisHutangPentingU[1].checked==true){
		document.${formName}.method="post";
		document.${formName}.mode.value="Pemiutangview";
		document.${formName}.action.value="";
		document.${formName}.socJenisHutangPentingU.value="1";
		doAjaxCall${formName}("Pemiutang");
		document.${formName}.submit();
	}
}

function tambah_simpan_penting(){
	if (document.${formName}.socJenisHutangPentingU[0].checked==false && document.${formName}.socJenisHutangPentingU[1].checked == false){
		alert("Sila pilih Kategori Pemiutang");
	
	}
	else{
	 
		if (document.${formName}.socJenisHutangPentingU[0].checked==true){
		
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
			else if (document.${formName}.txtNamaOBPenting.value == ""){
				alert('Sila masukkan " Nama Pemiutang" terlebih dahulu.');
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
					doAjaxCall${formName}("Pemiutang");
					document.${formName}.mode.value="tambah_pemiutang";
					
					document.${formName}.submit();
				}
			}
		}
	
		if (document.${formName}.socJenisHutangPentingU[1].checked==true){
	
			if (document.${formName}.txtNoKPLamaPenting1.value == ""){
				alert('Sila masukkan " No Pendaftaran" terlebih dahulu.');
				txtNoKPLamaPenting1.focus(); 
			}
			else if (document.${formName}.txtNamaOBPenting1.value == ""){
				alert('Sila masukkan " Nama Institusi " terlebih dahulu.');
				txtNamaOBPenting1.focus(); 
			}
			else if (document.${formName}.txtPoskodPenting.value!="" && document.${formName}.txtPoskodPenting.value.length < 5){
				alert("Sila masukkan no poskod dengan lengkap.");
				txtPoskodPenting.focus();
			}
			else{
				input_box=confirm("Adakah anda pasti?");
				if (input_box == true) {
					document.${formName}.method="post";
					doAjaxCall${formName}("Pemiutang");
					document.${formName}.mode.value="tambah_pemiutang";
					document.${formName}.submit();
				}
			}
		}
	}
}

function validLength(){
	if (document.${formName}.txtPoskodPenting.value!="" && document.${formName}.txtPoskodPenting.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodPenting.focus();
	}
}

function getBandar(){
	document.${formName}.method="post";
	document.${formName}.mode.value="getBandarTetap";
	
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.submit();
}
function hiddendisplay() {

	if (document.${formName}.socJenisHutangPentingU[0].checked == true){
	document.getElementById("kpb").style.display="";
	document.getElementById("kpl").style.display="";
	document.getElementById("kps").style.display="";
	document.getElementById("kpn").style.display="";
	document.getElementById("aname").style.display="none";
	document.getElementById("adaftar").style.display="none";	
	} 
	else if (document.${formName}.socJenisHutangPentingU[1].checked == true){
	document.getElementById("kpb").style.display="none";
	document.getElementById("kpl").style.display="none";
	document.getElementById("kps").style.display="none";
	document.getElementById("kpn").style.display="none";
	document.getElementById("aname").style.display="";
	document.getElementById("adaftar").style.display="";
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
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:3});
//-->
</script>
</body>
</html>