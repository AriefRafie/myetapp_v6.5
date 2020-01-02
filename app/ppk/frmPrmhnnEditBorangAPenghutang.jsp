<html>
<head>
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
.style39 {	color: #FF0000;
	font-weight: bold;
}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
</head>
<body>
<table width="97%" border="0">
<tr>
<td>
 <input type="hidden" name="action">
 <input type="hidden" name="hitButt">
 <input type="hidden" name="mode">
<input type="hidden" name="idpermohonansimati" value="$idpermohonansimati">
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
          <div class="TabbedPanelsContent"></div>
		  <div class="TabbedPanelsContent">
		  <table width="99%">
            #if($readmode=="disabled")
                 #set($readmodesy="readonly class=disabled")
                 #set($readmodekp="readonly class=disabled")
             #end
             #if($readmode!="disabled")
                 #if($listob.jenishutang=="1")
                      #set($readmodesy="")
                      #set($readmodekp="readonly class=disabled")
                 #elseif($listob.jenishutang=="2")
                      #set($readmodesy="readonly class=disabled")
                      #set($readmodekp="")
                 #else
                      #set($readmodesy="readonly class=disabled")
                      #set($readmodekp="readonly class=disabled")
                 #end                                    
             #end
                  #if($nk_update_penting=="yes")
					#set ($nilaihutang = "0.00")
                    #foreach($listob in $listPentingbyIDOB)
                    	#set ($noacctu = $listob.noakaun)
	                    #if($listob.jenishutang=="1")
		                     #set ($checked1a = "checked")
		                     #set ($checked2a = "")
                             #set ($checked3a = "")
	                    #elseif($listob.jenishutang=="2")
		                     #set ($checked1a = "")
		                     #set ($checked2a = "checked")
                             #set ($checked3a = "")
                        #else
                        	#set ($checked1a = "")
                            #set ($checked2a = "")
                        	#set ($checked3a = "readonly class=disabled")
                    	#end
                    	#set ($jenishutang = $listob.jenishutang)
                    	#set ($namaOB = $listob.nama_Ob)
                    	#set ($nilaihutang = $listob.nilaihutang)
                             <tr>
                                         <td>
                                          <fieldset>
                                          <legend>MAKLUMAT PENGHUTANG</legend>
                                          
                                          <table width="100%" border="0">
                              <tr>
                               <td width="80%" valign="top"><table width="100%">
                                   <input type="hidden" name="txtIdSimatiPenting" value="$listob.idSimati" >   
                              <input type="hidden" name="txtIdOb" value="$listob.idOb" >
                               <input type="hidden" name="jenisHutangPentingU" value="$listob.jenishutang">       
                                   <tr>
                                     <td ><div align="right"><span class="style38"><font color="#ff0000">*</font>&nbsp;Kategori Penghutang</span></div></td>
                                     <td class="style36">:</td>
                                     <td class="style36"><input type="radio" name="socJenisHutangPentingU" id="socJenisHutangPentingU" value="2" disabled $checked2a onClick="jenis_hutangU(this.value)"/>&nbsp;<span class="style38">INDIVIDU</span>&nbsp;&nbsp;<input type="radio" name="socJenisHutangPentingU" id="socJenisHutangPentingU" value="1" $checked1a onClick="jenis_hutangU(this.value)" disabled/>&nbsp;<span class="style38">INSTITUSI</span></td>
                                   </tr>
                                 #if($jenishutang == "2")
                                  <tr id="kpb">
                                  <td width="29%" ><div align="right"><span class="style38">MyID Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36">
                                     <input name="txtNoKPBaru1PentingU" type="text" id="txtNoKPBaru1PentingU" style="width: 50px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2PentingU')" value="$listob.nokpbaru1" size="6" maxlength="6" $readmode1/>-<input name="txtNoKPBaru2PentingU" type="text" id="txtNoKPBaru2PentingU" style="width: 20px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$listob.nokpbaru2" size="2" maxlength="2" $readmode1/>-<input name="txtNoKPBaru3PentingU" type="text" id="txtNoKPBaru3PentingU"  style="width: 40px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$listob.nokpbaru3" size="4" maxlength="4" $readmode1/>
                                  </td>
                                </tr>
                                
                               
                                
                                
                                <tr id="kpl">
                                  <td width="29%" ><div align="right"><span class="style38">MyID Lama</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><input name="txtNoKPLamaPentingU" type="text" id="txtNoKPLamaPentingU" $readmode1 size="8" maxlength="8" style="text-transform:uppercase;" value="$!listob.nokplama"/></td>
                                </tr>

                                <tr id="kplain">
                                  <td><div align="right"><span class="style38">Lain-lain MyID</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
 									 <select name="socJenisKPLainPentingU" $readmode id="socJenisKPLainPentingU" style="text-transform:uppercase;width: 146px;" $chkdropmenu onBlur="uppercase()" >
								   #if($listob.jeniskp=="5")
									   <option value="5">Tentera</option>
                                      <option value="6">Polis</option>
                                      <option value="4">Pasport</option>
	                               #elseif($listob.jeniskp=="6")
                                      <option value="6">Polis</option>
                                      <option value="5">Tentera</option>
                                      <option value="4">Pasport</option>
								   #elseif($listob.jeniskp=="4")
                                      <option value="4">Pasport</option>
                                      <option value="5">Tentera</option>
                                      <option value="6">Polis</option>
	                               #else
                                      <option value="0">SILA PILIH JENIS KP</option>
                                      <option value="5">Tentera</option>
                                      <option value="6">Polis</option>
                                      <option value="4">Pasport</option>
	                               #end
                                    </select>&nbsp;<input name="txtNoKPLainPentingU" type="text" id="textfield5" value="$listob.nokplain" size="9" maxlength="12" $chkkplain $readmode1  style="text-transform:uppercase;"  />
									 </td>
                                </tr>
                               
                                  <tr id="namap">
                                    <td><div align="right"><span class="style38"><font color="#ff0000">*</font>&nbsp;Nama Penghutang</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting" type="text" id="txtNamaOBPenting" size="34" value="$!namaOB" $readmode1 style="text-transform:uppercase;" />
                                    </label></td>
                                  </tr>
                                  #end
                                  #if($jenishutang == "1")
        						 <tr id="nop">
                                  <td ><div align="right"><span class="style38"><font color="#ff0000">*</font>&nbsp;No Pendaftaran</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtSyarikatPentingU" type="text" id="txtSyarikatPentingU" value="$listob.nokplama" $readmode1 size="12" maxlength="12" style="text-transform:uppercase;" onBlur="uppercase()" /><input type="hidden" name="txtSyarikatPentingU" value="$listob.nokplama">
                                  </label></td>
                                </tr>
                                  <tr id="namai">
                                    <td><div align="right"><span class="style38"><font color="#ff0000">*</font>&nbsp;Nama Institusi</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaSyktPenting" type="text" id="txtNamaSyktPenting" value="$namaOB" size="34" $readmode1 style="text-transform:uppercase;" />
                                    </label></td>
                                  </tr>
                               #end
                                 <tr>
                                          <td><div align="right"><span class="style38">No Akaun</span></div></td>
                                           <td class="style36"><div align="right">:</div></td>
                                           <td class="style36"><input name="txtNoAkaunPentingU" type="text" id="txtNoAkaunPentingU" style="text-transform:uppercase;" value="$!noacctu" size="34" maxlength="15"  $readmode1/></td>
                                         </tr>
                                         <tr>
                                  <tr>
                                  <td><div align="right"><span class="style38">Nilai Hutang (RM)</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  #if($listob.nilaihutang!="")
	                                  #if ($readmode == "disabled")
	                                  	 #set ($nilaihutang = $Util.formatDecimal($listob.nilaihutang))
	                                  #else
	                                  		#set ($value1 = $Util.formatDecimal($listob.nilaihutang))
	                                  		#set ($nilaihutang = $EkptgUtil.RemoveSymbol($value1))
	                                  #end
                                  #end
                                  <td class="style36"><label>
                                  <input name="txtNilaiHutangPentingU" type="text" id="txtNilaiHutangPentingU" style="text-align: right;" value="$!nilaihutang" size="10" maxlength="7"  $readmode1 onKeyUp="javascript:validatePoskod(this,this.value);" onBlur="validateModal(this,this.value,$nilaihutang);"/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38" width="29%"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Alamat</div></td>
                                  <td width="1%"><div align="right" class="style38">:</div></td>
                                  <td width="70%"><label>
 									
                                    <input name="txtAlamatTerakhir1PentingU" type="text" id="txtAlamatTerakhir1PentingU" value="$listob.alamat1" size="34"  $readmode1 style="text-transform:uppercase;"  />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2PentingU" type="text" id="txtAlamatTerakhir2PentingU" value="$listob.alamat2" size="34" $readmode1 style="text-transform:uppercase;"  />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><input name="txtAlamatTerakhir3PentingU" type="text" id="txtAlamatTerakhir3PentingU" value="$listob.alamat3" size="34" $readmode1 style="text-transform:uppercase;"  /></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Poskod</div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtPoskodPentingU" type="text" id="txtPoskodPentingU" onKeyUp="javascript:validatePoskod(this,this.value)"  value="$listob.poskod" size="5" maxlength="5" $readmode1 style="text-transform:uppercase;"  onBlur="validLengthUpdate()"/>
                                  </label></td>
                                </tr>
                                
                                        <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Negeri</div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                   <td>$!selectNegeriTetap</td>
                                  </tr> 
                                  <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Bandar</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td>$!selectBandarTetap</td>
                                </tr>
                                <tr>    
                                  <td valign="top"><div align="right" class="style38">Butiran Hutang</div></td>
                                  <td class="style36" valign="top"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <textarea name="txtButiranHutangPentingU" cols="31"  rows="3"  $readmode1 id="txtButiranHutangPentingU" style="text-transform:uppercase;" >$listob.butiranhutang</textarea>
                                  </label></td>
                                </tr>
                                </table></td>
                              </tr>
                            </table>
                             </fieldset>
                                         </td>
                                       </tr>
                 					#end
                              #end     

                               #if ($nk_tambah_penting=="yes")
                               #if($socJenisHutang=="1")
                                     #set ($checked1a = "checked")
                                     #set ($checked2a = "")
                                     #set ($checked3a = "")
                              #elseif($socJenisHutang=="2")
                                     #set ($checked1a = "")
                                     #set ($checked2a = "checked")
                                     #set ($checked3a = "")
                              #else
                                    #set ($checked1a = "")
                                    #set ($checked2a = "")
                                    #set ($checked3a = "")
                              #end
                               <tr>
                            <td width="100%">
                            
                            <fieldset>
                            <legend>MAKLUMAT PENGHUTANG</legend>
                            <table width="100%" border="0">
                              <tr>
                                <td width="80%" valign="top"><table width="100%">
                                   <input type="hidden" name="txtIdSimatiPenting" value="$id_Simati" >        
                                   <tr>
                                     <td ><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Kategori Penghutang</span></div></td>
                                     <td class="style36">:</td>
                                     <td class="style36"><input type="radio" name="socJenisHutangPenting" id="socJenisHutangPenting" value="2" $checkradio2 onClick="hiddendisplay();"/>&nbsp;<span class="style38">INDIVIDU</span>&nbsp;&nbsp;<input type="radio" name="socJenisHutangPenting" id="socJenisHutangPenting" value="1" $checkradio1 onClick="hiddendisplay()"/>&nbsp;<span class="style38">INSTITUSI</span></td>
                                   </tr>
                                  <tr>
                                 
                                  <tr id="kpb">
                                  <td width="29%" ><div align="right"><span class="style38">MyID Baru</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><label>
                                     <input name="txtNoKPBaru1PentingU" type="text" id="txtNoKPBaru1PentingU" style="width: 50px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2PentingU')" value="$!nokpbaru1" size="6" maxlength="6" />-<input name="txtNoKPBaru2PentingU" type="text" id="txtNoKPBaru2PentingU" style="width: 20px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$!nokpbaru2" size="2" maxlength="2" />-<input name="txtNoKPBaru3PentingU" type="text" id="txtNoKPBaru3PentingU"  style="width: 40px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3PentingU')" value="$!nokpbaru3" size="4" maxlength="4" />
                                  </label></td>
                                </tr>
                                
                                
                                <tr id="nop">
                                  <td ><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;No Pendaftaran</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                    <input name="txtSyarikatPentingU" type="text" id="txtSyarikatPentingU" value="$!nokplama" size="12" maxlength="12" style="text-transform:uppercase;" onBlur="uppercase()" />
                                  </label></td>
                                </tr>
                               
                              
                                <tr id="kpl">
                                  <td width="29%" ><div align="right"><span class="style38">&nbsp;MyID Lama</span></div></td>
                                  <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td width="70%" class="style36"><input name="txtNoKPLamaPenting" type="text" id="txtNoKPLamaPenting" size="8" maxlength="8" value="$!nokplama" style="text-transform:uppercase;"/></td>
                                </tr>
                                
                             
								<tr id="kplain">
                                  <td><div align="right"><span class="style38">&nbsp;Lain-lain MyID</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"> 
									 <select name="socJenisKPLainPenting"  style="text-transform:uppercase; width: 146px;"  $readmode>
								  		#set ($id = "")
							            #set ($keterangan = "")
							            #set ($selected = "")
								        #if ($nojeniskp != "0")
								            #foreach($Listkp in $listkp)
								            #set ($id = $Listkp.id)
								            #set ($keterangan = $Listkp.keterangan)
								            	#if ($id == $nojeniskp)
								            		#set ($selected = "selected")
								            	<option value="$id" selected $selected/>$keterangan.toUpperCase()</option>
								            	#end
								            #end
								            <option value="0"/>SILA PILIH JENIS KP</option>
								            #foreach($Listkp in $listkp)
							            	#set ($id = $Listkp.id)
							           		#set ($keterangan = $Listkp.keterangan)
								            	<option value="$id"/>$keterangan.toUpperCase()</option>
							            	#end
								        #else
							            	<option value="0"/>SILA PILIH JENIS KP</option>
							            	#foreach($Listkp in $listkp)
							            	#set ($id = $Listkp.id)
							           		#set ($keterangan = $Listkp.keterangan)
								            	<option value="$id"/>$keterangan.toUpperCase()</option>
							            	#end
							           #end
                                    
									 </select>&nbsp;<input name="txtNoKPLainPenting" type="text" id="txtNoKPLainPenting" size="9" maxlength="9" $readmode style="text-transform:uppercase;" value="$!nokplain"/></td>
                                </tr>
								
                               
                                  <tr id="namap">
                                    <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Penghutang</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaOBPenting" type="text" id="txtNamaOBPenting" value="$!namaOB" size="34" $readmode style="text-transform:uppercase;" />
                                    </label></td>
                                  </tr>
                                  
                               
                              
                                  <tr id="namai">
                                    <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Institusi</span></div></td>
                                    <td><div align="right"><span class="style38">:</span></div></td>
                                    <td><label>
                                      <input name="txtNamaSyktPenting" type="text" id="txtNamaSyktPenting" size="34" value="$!namasykt" $readmode style="text-transform:uppercase;" />
                                    </label></td>
                                  </tr>
                               
                                  <tr>
                                     <td><div align="right"><span class="style38">No Akaun</span></div></td>
                                     <td class="style36">:</td>
                                     <td class="style36"><input name="txtNoAkaunPenting" type="text" id="txtNoAkaunPentingU2" style="text-transform:uppercase;" size="26" maxlength="20"  $readmode $checked3a value="$!noacct"/></td>
                                  </tr>
                                  <tr>
                                  <td><div align="right"><span class="style38">Nilai Hutang (RM)</span></div></td>
                                  <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36"><label>
                                  <input name="txtNilaiHutangPenting" type="text" id="txtNilaiHutangPenting" value="$!nilai" style="text-transform:uppercase; text-align: right;" size="10" maxlength="7"   $readmode onKeyUp="javascript:validatePoskod(this,this.value)"  onblur="validateModal(this,this.value,this.value);" $checked3a />
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38" width="29%"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Alamat Tetap</div></td>
                                  <td width="1%"><div align="right" class="style38">:</div></td>
                                  <td width="70%"><label>
 									
                                    <input name="txtAlamatTerakhir1Penting" type="text" id="txtAlamatTerakhir1Penting" size="34"  $readmode style="text-transform:uppercase;" $checked3a value="$!alamat1"/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                                    <input name="txtAlamatTerakhir2Penting" type="text" id="txtAlamatTerakhir2Penting" size="34" $readmode style="text-transform:uppercase;" $checked3a value="$!alamat2"/>
                                  </label></td>
                                </tr>
                                <tr>
                                  <td class="style38"><div align="right"><span class="style7"><span class="style38"></span></span></div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><input name="txtAlamatTerakhir3Penting" type="text" id="txtAlamatTerakhir3Penting" size="34" $readmode style="text-transform:uppercase;" $checked3a value="$!alamat3"/></td>
                                </tr>
                                         <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Poskod</div></td>
                                  <td><div align="right" class="style38">:</div></td>
                                  <td><label>
                       <input name="txtPoskodPenting" type="text" id="txtPoskodPenting" onKeyUp="javascript:validatePoskod(this,this.value)" style="text-transform:uppercase;" size="5" maxlength="5" $readmode onBlur="validLength()" $checked3a value="$!poskod"/>
                                  </label></td>
                                </tr>
                                 <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Negeri</div></td>
                                    <td><div align="right" class="style38">:</div></td>
                                  <td>$selectNegeriTetap</td>                                   
                                  </tr>
                                  <tr>
                                  <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Bandar</div></td>
                                  <td><div align="right"><span class="style38">:</span></div></td>
                                  <td>$selectBandarTetap
                                  </td>
                                </tr>
                                <tr>
                                  <td valign="top"><div align="right" class="style38">Butiran Hutang</div></td>
                                  <td class="style36" valign="top"><div align="right"><span class="style38">:</span></div></td>
                                  <td class="style36">
                                    <textarea name="txtButiranHutangPenting" cols="34"  rows="3"  $readmode style="text-transform:uppercase;" id="txtButiranHutangPenting"  $checked3a >$!butiran</textarea>
                                  </td>
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
                    </table>
                      <br>
                      <br>
                            </fieldset></td>
                          </tr>
                               #end        
            #if($nk_button_penting=="yes") 
                          <tr>
                            <td>  <table width="100%" border="0" align="center">
                                  <tr>
                                  <td align="center">
						#if ($idstatus=="150" || $idstatus=="171")
                                  #if($buttonpenting=="Simpan")
                              		<input type="button" name="tambahpenting" id="tambahpenting2" value="Simpan" onClick="tambah_simpan_penting('0','5','0','0')"/>								
                              		<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal"/>                                 
                                  #else
	                                  #if($buttonpenting=="Kemaskini")
		                                  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="tambah_penting('0','4','0','0')"/>
										  <input type="button" name="hapuspenting" id="hapuspenting" value="Hapus"  onclick="hapus_penting('0','4','0','0')" />
										  <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah"  onclick="PenghutangView('0','4','0','0')" />
		                                  #end
		                                  
		                                  #if($buttonpenting=="Update")
		                                  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="tambah_update('0','4','0','0')"/>
		                                  <input type="button" name="hapuspenting" id="hapuspenting" value="Hapus"  onclick="hapus_penting('0','4','0','0')" />
		                                  <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onClick="PenghutangView('0','4','0','0')"/>
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
                             <fieldset>
                            <legend>SENARAI PENGHUTANG</legend>
                              <table width="100%" >
                                <tr>
                                  <td>
                                  #if($listPenting.size()!=0 )
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td width="2%"><div align="center">NO</div></td>
                                      <td width="23%"><div align="center">NAMA PENGHUTANG</div></td>
                                      <td width="15%"><div align="center">MyID</div></td> 
                                      <td width="15%"><div align="center">NO SYKT</div></td>                                   
                                      <!--<td width="20%"><div align="center">JENIS PENGHUTANG</div></td>-->
                                      <td width="20%"><div align="right">JUMLAH HUTANG(RM)</div></td>
                                      <td width="20%"><div align="center">BUTIRAN HUTANG</div></td>
                                    </tr>
                                    #set($peno=0)
                                      #foreach($listpenting in $listPenting)
                                     #set($peno=$peno+1)
         							 #if($peno%2!=0)
                                    <tr bgcolor="white">
                                    <td class="row1"><div align="center" class="style41">$!peno</div></td>
                                      <td class="row1" style="text-transform:uppercase;"><a href="javascript:edit_item('$listpenting.idOb')"><div class="style42" >$!listpenting.nama_Ob</div></a>
                                        <input type="hidden" name="idob" value="$listpenting.idOb" /></td>
                                      <td class="row1" style="text-transform:uppercase;"><div align="center" >
                                       #if($listpenting.jenishutang=="2")
                                          #if ($listpenting.nokpbaru!="")
                                            $!listpenting.nokpbaru
                                          #elseif ($listpenting.nokplama!="")
                                            $!listpenting.nokplama
                                          #else
                                            $!listpenting.nokplain
                                          #end
                                       #end
                                      </div></td>
                                      <td class="row1" style="text-transform:uppercase;"><div align="center" >
                                      #if($listpenting.jenishutang=="1")
                                      	$!listpenting.nokplama
                                      #end
                                      </div></td>
                                         #if($listpenting.jenishutang=="1")
                                         #set($stat="Agensi")
                                         #end
                                         #if($listpenting.jenishutang=="2")
                                         #set($stat="Individu")
                                         #end                                        
                                         #if($listpenting.jenishutang=="" || $listpenting.jenishutang=="0")
                                         #set($stat="")
                                         #end           
                                        #set($ll=$listpenting.nilaihutang)
                                        
                                      <!--<td class="row1" style="text-transform:uppercase;"><div align="center" >$stat</div></td>-->
                                      #if($ll!="")
                                      <td class="row1" style="text-transform:uppercase;"><div align="right" >$listpenting.nilaihutang</div></td>
                                      #end
                                       #if($ll=="")
                                      <td class="row1" style="text-transform:uppercase;"><div align="center" ></div></td>
                                      #end
                                      <td class="row1" style="text-transform:uppercase;"><div align="left" >$listpenting.butiranhutang</div></td>           
                                    </tr>
                                    #else
                                        <tr >
                                   <td class="row2"><div align="center" class="style41">$peno</div></td>
                                      <td class="row2" style="text-transform:uppercase;"><a href="javascript:edit_item('$listpenting.idOb')"><div class="style42"> $!listpenting.nama_Ob</div>   
                                        <input type="hidden" name="idob" value="$listpenting.idOb" /></td>
                                      <td class="row2" style="text-transform:uppercase;"><div align="center">
                                      #if($listpenting.jenishutang=="2")
                                          #if ($listpenting.nokpbaru!="")
                                            $!listpenting.nokpbaru
                                          #elseif ($listpenting.nokplama!="")
                                            $!listpenting.nokplama
                                          #else
                                            $!listpenting.nokplain
                                          #end
                                       #end
                                      </div></td>
                                      <td class="row2" style="text-transform:uppercase;"><div align="center">
                                      #if($listpenting.jenishutang=="1")
                                      	$listpenting.nokplama
                                      #end
                                      </div></td>
                                       #if($listpenting.jenishutang=="1")
                                         #set($stat="Agensi")
                                         #end
                                         #if($listpenting.jenishutang=="2")
                                         #set($stat="Individu")
                                         #end                                        
                                         #if($listpenting.jenishutang=="" || $listpenting.jenishutang=="0")
                                         #set($stat="")
                                         #end           
                                     <!-- <td class="row2" style="text-transform:uppercase;"><div align="center" >$stat</div></td>-->
                                      #set($lll=$listpenting.nilaihutang)
                                      
                                      #if($lll!="")
                                      <td class="row2" style="text-transform:uppercase;"><div align="right" >$lll</div></td>
                                      #end
                                       #if($lll=="")
                                      <td class="row2" style="text-transform:uppercase;"><div align="center" ></div></td>
                                      #end
                                      <td class="row2" style="text-transform:uppercase;"><div align="left" >$!listpenting.butiranhutang</div></td>
                                    </tr>
                                    
                                    #end
                                    #end
                                  </table>
                                  #else
                                  <table width="100%">
                                    <tr  class="table_header">
                                      <td><div align="center">NO</div></td>
                                      <td><div align="center">NAMA PENGHUTANG</div></td>
                                      <td><div align="center">MyID BARU</div></td>
                                      <td><div align="center">JENIS PENGHUTANG</div></td>
                                      <td><div align="center">JUMLAH HUTANG(RM)</div></td>
                                      <td><div align="center">BUTIRAN HUTANG</div></td>
                                    </tr>
                                   </table> 
                                    <table width="100%" bgcolor="#FFFFFF">
                                    <tr>
                                      <td width="100%"><div align="left">Tiada Rekod</div>
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
function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}("Htaam");
	document.${formName}.submit();
}

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();
}

function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Warisview";
	doAjaxCall${formName}("Waris");
	document.${formName}.submit();
}
function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemohonview";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.submit();
}
function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.submit();
}

function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	document.${formName}.hitButt.value="harta_alih";
	document.${formName}.submit();
}
function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_nilai_harta";
	document.${formName}.hitButt.value="nilai_harta";
	document.${formName}.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Penghutangview";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
}
function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemiutangview";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.submit();
}
function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {

	document.${formName}.mode.value="Saksiview";
	document.${formName}.hitButt.value="Saksi";	
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pentingview";
	doAjaxCall${formName}("Penting");
	document.${formName}.submit();
}



function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	document.${formName}.hitButt.value="kembali_simati";
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
<!-- PENGHUTANG -->

function kemaskini_penting(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="kemaskini_penghutang";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
}

function simpan_penting(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
		document.${formName}.method="post";
		document.${formName}.mode.value="simpan_penghutang";
		doAjaxCall${formName}("Penghutang");
		document.${formName}.submit();
}

function tambah_saksi_baru(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
    document.${formName}.mode.value="tambah_penghutang_baru";
	doAjaxCall${formName}("Penghutang");
    document.${formName}.submit();

}

function hapus_penting(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.method="post";
    document.${formName}.mode.value="hapus_penghutang";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
	}
}
function batalpenting(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.method="post";
    document.${formName}.mode.value="batal_update_penghutang";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
	}
}

function tambah_simpan_penting(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){

	if (document.${formName}.socJenisHutangPenting[0].checked == false && document.${formName}.socJenisHutangPenting[1].checked == false) {
		alert("Sila pilih Kategori Penghutang");
	}else{

		if (document.${formName}.socJenisHutangPenting[0].checked == true){

			var negeri_code = document.${formName}.txtNoKPBaru2PentingU.value;
			var dob_code = document.${formName}.txtNoKPBaru1PentingU.value;
			
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

			if (document.${formName}.txtNoKPBaru1PentingU.value == "" && document.${formName}.txtNoKPBaru2PentingU.value == "" && document.${formName}.txtNoKPBaru3PentingU.value == "" && document.${formName}.txtNoKPLamaPenting.value == "" && document.${formName}.txtNoKPLainPenting.value == "") {
				alert("Sila masukkan salah satu No KP");
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && document.${formName}.txtNoKPBaru1PentingU.value.length < 6){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru1PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru2PentingU.value != "" && document.${formName}.txtNoKPBaru2PentingU.value.length < 2){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru3PentingU.value != "" && document.${formName}.txtNoKPBaru3PentingU.value.length < 4){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru3PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && document.${formName}.txtNoKPBaru2PentingU.value == "" && document.${formName}.txtNoKPBaru3PentingU.value == ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && document.${formName}.txtNoKPBaru2PentingU.value != "" && document.${formName}.txtNoKPBaru3PentingU.value == ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru3PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && document.${formName}.txtNoKPBaru2PentingU.value == "" && document.${formName}.txtNoKPBaru3PentingU.value != ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value == "" && document.${formName}.txtNoKPBaru2PentingU.value != "" && document.${formName}.txtNoKPBaru3PentingU.value != ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru1PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && isIc(tt)==false){
				txtNoKPBaru1PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru2PentingU.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
				negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" && negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" && negeri_code!="15" && negeri_code!="58" && negeri_code!="16" && negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99")) {
				alert("Sila masukkan kod negeri yang sah");
				txtNoKPBaru2PentingU.focus()
			}
			else if (document.${formName}.socJenisKPLainPenting.value != "0" && document.${formName}.txtNoKPLainPenting.value == ""){
				alert("Sila masukkan No KP Lain");
				txtNoKPLainPenting.focus();
			}
			else if (document.${formName}.txtNoKPLainPenting.value != "" && document.${formName}.socJenisKPLainPenting.value == "0"){
				alert("Sila pilih jenis No KP Lain");
				socJenisKPLainPenting.focus();
			}
			else if (document.${formName}.txtNamaOBPenting.value == "") {
				alert("Sila masukkan Nama Penghutang");
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
					doAjaxCall${formName}("Penghutang");
					document.${formName}.mode.value="tambah_penghutang";
					document.${formName}.submit();
				}			
			}
		}

		if (document.${formName}.socJenisHutangPenting[1].checked == true){

			if (document.${formName}.txtSyarikatPentingU.value == "") {
				alert("Sila masukkan No Pendaftaran");
				txtSyarikatPentingU.focus();
			}
			else if (document.${formName}.txtNamaSyktPenting.value == "") {
				alert("Sila masukkan Nama Institusi");
				txtNamaSyktPenting.focus();
			}
			else if (document.${formName}.txtPoskodPenting.value!="" && document.${formName}.txtPoskodPenting.value.length < 5){
				alert("Sila masukkan no poskod dengan lengkap.");
				txtPoskodPenting.focus();
			}
			else{
				input_box=confirm("Adakah anda pasti?");
				if (input_box == true) {
					document.${formName}.method="post";
					doAjaxCall${formName}("Penghutang");
					document.${formName}.mode.value="tambah_penghutang";
					document.${formName}.submit();
				}			
			}
		}
	}
}

function tambah_penting(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="KemaskiniPenghutang";
	doAjaxCall${formName}("Penghutang");	
	document.${formName}.submit();
}

function tambah_update(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){

	if (document.${formName}.jenisHutangPentingU.value == "2"){

			var negeri_code = document.${formName}.txtNoKPBaru2PentingU.value;
			var dob_code = document.${formName}.txtNoKPBaru1PentingU.value;
			
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

			if (document.${formName}.txtNoKPBaru1PentingU.value == "" && document.${formName}.txtNoKPBaru2PentingU.value == "" && document.${formName}.txtNoKPBaru3PentingU.value == "" && document.${formName}.txtNoKPLamaPentingU.value == "" && document.${formName}.txtNoKPLainPentingU.value == "") {
				alert("Sila masukkan salah satu No KP");
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && document.${formName}.txtNoKPBaru1PentingU.value.length < 6){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru1PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru2PentingU.value != "" && document.${formName}.txtNoKPBaru2PentingU.value.length < 2){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru3PentingU.value != "" && document.${formName}.txtNoKPBaru3PentingU.value.length < 4){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru3PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && document.${formName}.txtNoKPBaru2PentingU.value == "" && document.${formName}.txtNoKPBaru3PentingU.value == ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && document.${formName}.txtNoKPBaru2PentingU.value != "" && document.${formName}.txtNoKPBaru3PentingU.value == ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru3PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && document.${formName}.txtNoKPBaru2PentingU.value == "" && document.${formName}.txtNoKPBaru3PentingU.value != ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru2PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value == "" && document.${formName}.txtNoKPBaru2PentingU.value != "" && document.${formName}.txtNoKPBaru3PentingU.value != ""){
				alert("Sila masukkan No KP Baru sepenuhnya");
				txtNoKPBaru1PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru1PentingU.value != "" && isIc(tt)==false){
				txtNoKPBaru1PentingU.focus();
			}
			else if (document.${formName}.txtNoKPBaru2PentingU.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
				negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" && negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" && negeri_code!="15" && negeri_code!="58" && negeri_code!="16" && negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99")) {
				alert("Sila masukkan kod negeri yang sah");
				txtNoKPBaru2PentingU.focus()
			}
			else if (document.${formName}.socJenisKPLainPentingU.value != "0" && document.${formName}.txtNoKPLainPentingU.value == ""){
				alert("Sila masukkan No KP Lain");
				txtNoKPLainPentingU.focus();
			}
			else if (document.${formName}.txtNoKPLainPentingU.value != "" && document.${formName}.socJenisKPLainPentingU.value == "0"){
				alert("Sila pilih jenis No KP Lain");
				socJenisKPLainPentingU.focus();
			}
			else if (document.${formName}.txtNamaOBPenting.value == "") {
				alert("Sila masukkan Nama Penghutang");
				txtNamaOBPenting.focus();
			}
			else if (document.${formName}.txtPoskodPentingU.value!="" && document.${formName}.txtPoskodPentingU.value.length < 5){
				alert("Sila masukkan no poskod dengan lengkap.");
				txtPoskodPentingU.focus();
			}
			else{
				input_box=confirm("Adakah anda pasti?");
				if (input_box == true) {
						document.${formName}.method="post";
						document.${formName}.socJenisHutangPentingU[0].value = 2;
						document.${formName}.mode.value="simpan_penghutang";
						doAjaxCall${formName}("Penghutang");
						document.${formName}.submit();
				}			
			}
		}
		
		if (document.${formName}.jenisHutangPentingU.value == "1"){

			if (document.${formName}.txtSyarikatPentingU.value == "") {
				alert("Sila masukkan No Pendaftaran");
				txtSyarikatPentingU.focus();
			}
			else if (document.${formName}.txtNamaSyktPenting.value == "") {
				alert("Sila masukkan Nama Institusi");
				txtNamaSyktPenting.focus();
			}
			else if (document.${formName}.txtPoskodPentingU.value!="" && document.${formName}.txtPoskodPentingU.value.length < 5){
				alert("Sila masukkan no poskod dengan lengkap.");
				txtPoskodPentingU.focus();
			}
			else{
				input_box=confirm("Adakah anda pasti?");
				if (input_box == true) {
						document.${formName}.method="post";
						document.${formName}.socJenisHutangPentingU[1].value = 1;
						document.${formName}.mode.value="simpan_penghutang";
						doAjaxCall${formName}("Penghutang");
						document.${formName}.submit();
				}			
			}
		}
}

function jenis_hutangU(val) {
	if(val=="1")
	{
		document.${formName}.method="post";
		document.${formName}.socJenisHutangPenting.value="1";
		document.${formName}.mode.value="Penghutangview";
		doAjaxCall${formName}("Penghutang");
		document.${formName}.submit();
	}
	else if(val=="2")
	{
		document.${formName}.method="post";
		document.${formName}.socJenisHutangPenting.value="1";
		document.${formName}.mode.value="Penghutangview";
		doAjaxCall${formName}("Penghutang");
		document.${formName}.submit();
	}
	else
	{
		document.${formName}.method="post";
		document.${formName}.socJenisHutangPenting.value="0";
		document.${formName}.mode.value="Penghutangview";
		doAjaxCall${formName}("Penghutang");
		document.${formName}.submit();
	}
}
function hiddendisplay() {


	if (document.${formName}.socJenisHutangPenting[0].checked == true){
	document.getElementById("nop").style.display="none";
	document.getElementById("namai").style.display="none";
	document.getElementById("kpb").style.display="";
	document.getElementById("kpl").style.display="";
	document.getElementById("kplain").style.display="";
	document.getElementById("namap").style.display="";	
	
	} 
	else if (document.${formName}.socJenisHutangPenting[1].checked == true){
	document.getElementById("nop").style.display="";
	document.getElementById("namai").style.display="";
	document.getElementById("kpb").style.display="none";
	document.getElementById("kpl").style.display="none";
	document.getElementById("kplain").style.display="none";
	document.getElementById("namap").style.display="none";	
	}
		
}
function edit_item(idOb) 
{
	document.${formName}.method="post";
	document.${formName}.mode.value="GetPenghutang";

	document.${formName}.tabIdatas.value="0";
	document.${formName}.tabIdtengah.value="5";
	document.${formName}.tabIdbawah.value="0";
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.idOb.value=idOb;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.submit();
}

function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value=content2;
		return;
	}
	var num=content * 1;
	elmnt.value=num.toFixed(2);
	return;
}

function validLength(){
	if (document.${formName}.txtPoskodPenting.value!="" && document.${formName}.txtPoskodPenting.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodPenting.focus();
	}
}

function validLengthUpdate(){
	if (document.${formName}.txtPoskodPentingU.value!="" && document.${formName}.txtPoskodPentingU.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodPentingU.focus();
	}
}

function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
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

function onChangeBandarTetap(){
	if (document.${formName}.socJenisHutangPenting[0].checked){
		document.${formName}.method="post";
		document.${formName}.socJenisHutangPenting[0].value = 2;
		document.${formName}.mode.value="onChangeBandarTetap";
		document.${formName}.tabIdatas.value="0";
		document.${formName}.tabIdtengah.value="5";
		document.${formName}.tabIdbawah.value="0";	
		document.${formName}.tabIdtepi.value="0";
		document.${formName}.action.value="";
		doAjaxCall${formName}("Penghutang");
		document.${formName}.submit();
	}else if (document.${formName}.socJenisHutangPenting[1].checked){
		document.${formName}.method="post";
		document.${formName}.socJenisHutangPenting[1].value = 1;
		document.${formName}.mode.value="onChangeBandarTetap";
		document.${formName}.tabIdatas.value="0";
		document.${formName}.tabIdtengah.value="5";
		document.${formName}.tabIdbawah.value="0";	
		document.${formName}.tabIdtepi.value="0";
		document.${formName}.action.value="";
		doAjaxCall${formName}("Penghutang");
		document.${formName}.submit();
	}
}


function getDuplicateAddress(){
		if (document.${formName}.chcAlamat.checked == true) {
				document.${formName}.chcAlamat.checked = true;
				document.${formName}.mode.value="onChangeDuplicate";
				doAjaxCall${formName}("Penghutang");

		}else if (document.${formName}.chcAlamat.checked == false) {
				document.${formName}.chcAlamat.checked = false;
				document.${formName}.mode.value="onChangeNotDuplicate";
				doAjaxCall${formName}("Penghutang");
		}
}
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:4});
//-->
</script>
</body>
</html>