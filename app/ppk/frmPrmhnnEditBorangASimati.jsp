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

#if ($skrin_online_popup == "yes")
<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
#end

</head>
<body>
<table width="97%" border="0">
<tr>
<td>
<input type="hidden" name="v_tab" id="v_tab" value="" />
#set ($Idsimati = "")  

#if ($new_data != "yes")
#foreach ($listData in $ViewDataSimat)
	#if ($listData.idsimati != "")
 	#set ($Idsimati = $listData.idsimati)
    #end
    #if ($listData.nokpbarusimati1 != "")
 	#set ($nokpbaru1 = $listData.nokpbarusimati1)
    #end
    #if ($listData.nokpbarusimati2 != "")
  	#set ($nokpbaru2 = $listData.nokpbarusimati2)
    #end
    #if ($listData.nokpbarusimati3 != "")
   	#set ($nokpbaru3 = $listData.nokpbarusimati3)
    #end
    #if ($listData.nokplamasimati != "")
   	#set ($nokplama = $listData.nokplamasimati)
    #end
    #if ($listData.jeniskpsimati != "")
	#set ($nojeniskp = $listData.jeniskpsimati)
    #end
    #if ($listData.nokplainsimati != "")
	#set ($nokplain = $listData.nokplainsimati)
    #end
    #if ($listData.namasimati != "")
	#set ($simatinama = $listData.namasimati)
    #end
    #if ($listData.namalainsimati != "")
	#set ($simatinamalain = $listData.namalainsimati)
    #end
    #if ($listData.waktukematian != "")
	#set ($waktumati = $listData.waktukematian)
    #end
    #if ($listData.tmptmatisimati != "")
	#set ($tmptmati = $listData.tmptmatisimati)   
    #end
    #if ($listData.alamat1simati != "")
	#set ($alamat1 = $listData.alamat1simati) 
    #end
    #if ($listData.alamat2simati != "")
	#set ($alamat2 = $listData.alamat2simati) 
    #end
    #if ($listData.alamat3simati != "")
	#set ($alamat3 = $listData.alamat3simati)
    #end
    #if ($listData.sebabmati != "")
	#set ($sbbmati = $listData.sebabmati)
    #end
    #if ($listData.bandarsimati != "")
	#set ($bandar = $listData.bandarsimati)
    #end 
    #if ($listData.poskodsimati != "")
	#set ($poskod = $listData.poskodsimati)
    #end
    #if ($listData.catatan != "")
	#set ($catatan = $listData.catatan)
    #end
    #if ($listData.jenisAgama != "" || $listData.jenisAgama != "0")
	#set ($agama = $listData.jenisAgama)
    #end
    #if ($listData.jenisWarga != "" || $listData.jenisWarga != "0")
	#set ($warga = $listData.jenisWarga)
    #end
    #if ($listData.umursimati != "")
	#set ($umur = $listData.umursimati)
    #end
    #if ($listData.idBuktiMati != "" || $listData.idBuktiMati != "0")
	#set ($buktimati = $listData.idBuktiMati)
    #end
    #if ($listData.nosijilsimati != "")
	#set ($nosijil = $listData.nosijilsimati)
    #end
    #if ($listData.tarikhmati != "")
	#set ($tarikhmati = $listData.tarikhmati)
    #end
    #if ($listData.idNegeri != "")
	#set ($negeriId = $listData.idNegeri)
    #end
    #if ($listData.idBandar != "")
    #set ($idBandar = $listData.idBandar)
    #end
    #if ($listData.jantinasimati != "")
    #set ($jantina = $listData.jantinasimati)
    #end
    
#end
 #end
 <input type="hidden" name="hitButt" >
 <input type="hidden" name="mode" >
 <input type="hidden" name="idPermohonan" value="$IdPermohonan">
 <input name="idSimati" type="hidden"  value="$idSimati"/>
 <input type="hidden" name="simpanStatus" value="$SimpanStatus">
 <input name="idpermohonansimati" type="hidden"  value="$idpermohonansimati"/>
 <input name="idtarafkptg" type="hidden"  value="$idtarafkptg"/>
 <input name="idsaudara" type="hidden"  value="$idsaudara"/>
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
      <div id="TabbedPanels2" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:SimatiView()">SIMATI</li>
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:PemohonView()">PEMOHON</li>
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:WarisView()">WARIS</li>
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:PemiutangView()">PEMIUTANG</li>
          <li class="TabbedPanelsTab" tabindex="0" style="style1 style3" onClick="javascript:PenghutangView()">PENGHUTANG</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
          <table width="98%" border="0">
                <tr>
                  <td>
                    <p></p>
                    <fieldset>
                    <legend>MAKLUMAT SIMATI</legend>
                    <table width="100%">
                      <tr>
                        <td width="50%">
                            <table width="100%" border="0">

                              <tr valign="top">
                                <td width="30%"><div align="right"><span class="style38">MyID Baru :</span></div></td>
                                <td width="70%" ><input name="txtNoKPBaru1Simati" type="text" id="txtNoKPBaru1Simati" value="$!nokpbaru1" size="6" maxlength="6" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Simati')" $setmode onBlur="qryHowOld();" $readonly/>
                                  -
                                  <input name="txtNoKPBaru2Simati" type="text" value="$!nokpbaru2" id="txtNoKPBaru2Simati" size="1" maxlength="2" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Simati')" $setmode $readonly/>-<input name="txtNoKPBaru3Simati" type="text" value="$!nokpbaru3" id="txtNoKPBaru3Simati" size="4" maxlength="4" $setmode onBlur="getJantina(this,this.value)"  $readonly/>                                </td>
                              </tr>
                              <tr>
                                <td ><div align="right"><span class="style38">MyID Lama :</span></div></td>
                                <td >
                                  <input name="txtNoKPLamaSimati" type="text" value="$!nokplama" id="txtNoKPLamaSimati" size="8" maxlength="8" style="text-transform:uppercase;" $setmode $readonly/>                                </td>
                              </tr>
                              <tr>
                                <td ><div align="right"><span class="style38">Lain-lain MyID :</span></div></td>
                                <td ><select name="socJenisKPLainSimati" id="select" style="width: 100px;" $setmode2 $setmode3>
                                #set ($id = "")
					            #set ($keterangan = "")
					            #set ($selected = "")
						        #if ($nojeniskp != "")
						            #foreach($Listkp in $listkp)
						            #set ($id = $Listkp.id)
						            #set ($keterangan = $Listkp.keterangan)
						            	#if ($id == $nojeniskp)
						            		#set ($selected = "selected")
						            	<option value="$id" $selected/>$!keterangan.toUpperCase()</option>
						            	#end
						            #end
						            <option value="0"/>SILA PILIH</option>
						            #foreach($Listkp in $listkp)
					            	#set ($id = $Listkp.id)
					           		#set ($keterangan = $Listkp.keterangan)
						            	<option value="$id"/>$!keterangan.toUpperCase()</option>
					            	#end
						        #else
					            	<option value="0"/>SILA PILIH</option>
					            	#foreach($Listkp in $listkp)
					            	#set ($id = $Listkp.id)
					           		#set ($keterangan = $Listkp.keterangan)
						            	<option value="$id"/>$!keterangan.toUpperCase()</option>
					            	#end
					           #end
                                 </select></td>
                              </tr>
                              <tr>
                                <td><div align="right"><span class="style38">MyID Lain :</span></div></td>
                                <td ><input name="txtNoKPLainSimati" type="text" id="textfield5" value="$!nokplain" style="width: 97px; text-transform:uppercase;" maxlength="12" $setmode $readonly/></td>
                              </tr>
                              <tr>
                                <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Nama Simati :</span></div></td>
                                <td ><input name="txtNamaSimati" type="text" id="textfield6" value="$!simatinama" size="34" style="text-transform:uppercase;" $setmode $readonly/>                                </td>
                              </tr>
                              <tr>
                                <td><div align="right"><span class="style38">Nama Lain :</span></div></td>
                                <td ><input name="txtNamaLainSimati" type="text" id="txtNamaLainSimati" value="$!simatinamalain" size="34" style="text-transform:uppercase;" $setmode $readonly/>                                </td>
                              </tr>
                              <tr>
                                <td><div align="right"><span class="style38">Jantina :</span></div></td>
                                <td ><select name="socJantina" style="width: 225px;" $setmode2 $setmode3>
                                #if ($jantina=="0" || $jantina=="")
                                		<option value="0">SILA PILIH</option>
		                                <option value="2">PEREMPUAN</option>
		                                <option value="1" >LELAKI</option>
                                #else
                                 	#if ($jantina == "2")
		                                <option value="2" selected>PEREMPUAN</option>
		                                <option value="1">LELAKI</option>
                                	#elseif ($jantina == "1")
		                                <option value="1"selected>LELAKI</option>
										<option value="2">PEREMPUAN</option>
                                	#else
		                                <option value="0">SILA PILIH</option>
		                                <option value="2">PEREMPUAN</option>
		                                <option value="1" >LELAKI</option>
                                	#end
                                #end
                                </select></td>
                              </tr>
                            
                              <tr>
                                <td><div align="right"><span class="style38">Agama :</span></div></td>
                                <td ><select name="socAgama" style="width: 225px;" $setmode2 $setmode3>
                                #if ($agama == "" || $agama == "0")
                                	<option value="0">SILA PILIH</option>
                                	<option value="1">ISLAM</option>
                                	<option value="2">BUKAN ISLAM</option>
                                #else
                                	#if ($agama == "1")
                                	<option value="1" selected>ISLAM</option>
                                	<option value="2">BUKAN ISLAM</option>
                                	#elseif ($agama == "2")
                                	<option value="1">ISLAM</option>
                                	<option value="2" selected>BUKAN ISLAM</option>
                                	#end
                                #end
                                </select></td>
                              </tr>
                              <tr>
                                <td><div align="right"><span class="style38">Warganegara :</span></div></td>
                                <td ><select name="socWarganegara" style="width: 225px;" $setmode2 $setmode3>
   	 							#if ($warga == "" || $warga == "0")
   	 								<option value="0">SILA PILIH</option>
                                	<option value="1">WARGANEGARA</option>
                                	<option value="2">BUKAN WARGANEGARA</option>
                                	<option value="3">PENDUDUK TETAP</option>
   	 							#else
	   	 							#if ($warga == "1")
                                	<option value="1" SELECTED>WARGANEGARA</option>
                                	<option value="2">BUKAN WARGANEGARA</option>
                                	<option value="3">PENDUDUK TETAP</option>
                                	#elseif ($warga == "2")
                                	<option value="1">WARGANEGARA</option>
                                	<option value="2" SELECTED>BUKAN WARGANEGARA</option>
                                	<option value="3">PENDUDUK TETAP</option>
                                	#elseif ($warga == "3")
                                	<option value="1">WARGANEGARA</option>
                                	<option value="2">BUKAN WARGANEGARA</option>
                                	<option value="3" SELECTED>PENDUDUK TETAP</option>
                                	#end
   	 							#end
                                </select></td>
                              </tr>
                              <tr>
                                <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Bukti Kematian :</span></div></td>
                                <td><select name="socBuktiMati" style="width: 225px;" $setmode2 onChange="getBuktiMati(this.value)" $setmode3>
                                #set ($buktiId = "")
                                #set ($buktiKod = "")
                                #set ($buktiKeterangan = "")
   	 							#set ($selectbukti = "")
   	 							#if ($buktimati != "")
 	   	 							#foreach ($listBukti in $JenisBuktiMati)
	   	 							#set ($buktiId = $listBukti.id_Buktimati)
	                                #set ($buktiKod = $listBukti.kod)
	                                #set ($buktiKeterangan = $listBukti.keterangan)
	                                	#if ($buktimati == $buktiId) 
	                                	#set ($selectbukti = "selected")
	   	 							<option value="$buktiId" $selectbukti>$buktiKeterangan</option>
	   	 								#end
	   	 							#end
	   	 							<option value="0" >SILA PILIH</option>
	   	 							#foreach ($listBukti in $JenisBuktiMati)
	   	 							#set ($buktiId = $listBukti.id_Buktimati)
	                                #set ($buktiKod = $listBukti.kod)
	                                #set ($buktiKeterangan = $listBukti.keterangan)
	   	 							<option value="$buktiId">$buktiKeterangan</option>
	   	 							#end
   	 							#elseif ($buktimati1 != "")
 	   	 							#foreach ($listBukti in $JenisBuktiMati)
	   	 							#set ($buktiId = $listBukti.id_Buktimati)
	                                #set ($buktiKod = $listBukti.kod)
	                                #set ($buktiKeterangan = $listBukti.keterangan)
	                                	#if ($buktimati == $buktiId) 
	                                	#set ($selectbukti = "selected")
	   	 							<option value="$buktiId" $selectbukti>$buktiKeterangan</option>
	   	 								#end
	   	 							#end
	   	 							<option value="0" >SILA PILIH</option>
	   	 							#foreach ($listBukti in $JenisBuktiMati)
	   	 							#set ($buktiId = $listBukti.id_Buktimati)
	                                #set ($buktiKod = $listBukti.kod)
	                                #set ($buktiKeterangan = $listBukti.keterangan)
	   	 							<option value="$buktiId">$buktiKeterangan</option>
	   	 							#end
   	 							#else
   	 								<option value="0" >SILA PILIH</option>
	   	 							#foreach ($listBukti in $JenisBuktiMati)
	   	 							#set ($buktiId = $listBukti.id_Buktimati)
	                                #set ($buktiKod = $listBukti.kod)
	                                #set ($buktiKeterangan = $listBukti.keterangan)
	   	 							<option value="$buktiId">$buktiKeterangan</option>
	   	 							#end
	   	 						#end
                                </select></td>
                              </tr>
                              <tr>
                                <td><div align="right"><span class="style38">No Sijil Mati / Permit Pengkuburan :</span></div></td>
                                <td ><label>
                                  <input name="txtNoSijilMatiSimati" type="text" id="txtNoSijilMatiSimati" value="$!nosijil" size="10" maxlength="10" style="text-transform:uppercase;" $setmode $readonly/>
                                </label></td>
                              </tr>
                              <tr>
                                <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Tarikh Mati :</span></div></td>
                                <td><input name="txdTarikhMati" type="text" id="txdTarikhMati" value="$!tarikhmati" size="10" maxlength="10" $setmode onBlur="javascript:trans_date(this.value);" onFocus="qryHowOld();" onKeyUp="qryHowOld(); javascript:validatePoskod(this,this.value);" onKeyDown="javascript:validatePoskod(this,this.value);" $readonly/>
                                  #if ($readonly!="readonly")
								  <a href="javascript:displayDatePicker('txdTarikhMati',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" $setmode/></a>
								  #end
								   <span class="style52">format : dd/mm/yyyy</span>                                   </td>
                              </tr>
                               <tr>
                                <td><div align="right"><span class="style38">Umur Ketika Mati :</span></div></td>
                                <td><input name="txtUmurSimati" type="text" id="txtUmurSimati" value="$!umur" size="3" maxlength="3" onKeyUp="javascript:validatePoskod(this,this.value);" $setmode onBlur="Checkumur()" $readonly/> <span class="style38">tahun</span>                                </td>
                              </tr>
                              <tr>
                                <td><div align="right"><span class="style38">Waktu Kematian :</span></div></td>
                                <td ><input name="socWaktuKematianSimati" type="text" id="textfield" value="$!waktumati" size="4" maxlength="4" onKeyUp="javascript:validatePoskod(this,this.value)" $setmode onBlur="validTarikhMati()" $readonly/> <span class="style52">format : 24 jam (HHMM)</span>                                </td>
                              </tr>
                          </table></td>
                        <td valign="top">
                        <table width="100%">
                              <tr valign="top"> 
                                <td width="30%" class="style38"><div align="right" class="style38">Tempat 
                                    Mati :</div></td>
                                <td width="70%"><textarea name="txtTempatMati" cols="31" rows="2" style="text-transform:uppercase;" onKeyUp="javascript:return imposeMaxLength(this,120);" $setmode $readonly>$!tmptmati</textarea></td>
                              </tr>
                              <tr> 
                                <td class="style38" valign="top"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*</font>&nbsp;Sebab 
                                    Kematian :</div></td>
                                <td><textarea name="txtSebabKematianSimati" cols="31" rows="3" id="txtSebabKematian" style="text-transform:uppercase;" $setmode $readonly>$!sbbmati</textarea></td>
                              </tr>
                              <tr> 
                                <td class="style38"><div align="right" class="style38">Alamat 
                                    Terakhir :</div></td>
                                <td><label> 
                                  <input name="txtAlamatTerakhir1Simati" type="text" id="txtAlamatTerakhir" value="$!alamat1" size="34" style="text-transform:uppercase;" $setmode $readonly/>
                                  </label></td>
                              </tr>
                              <tr> 
                                <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                <td><label> 
                                  <input name="txtAlamatTerakhir2Simati" type="text" id="txtAlamatTerakhir2"  value="$!alamat2" size="34"  style="text-transform:uppercase;" $setmode $readonly/>
                                  </label></td>
                              </tr>
                              <tr> 
                                <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                <td><input name="txtAlamatTerakhir3Simati" type="text" id="txtAlamatTerakhir3" value="$!alamat3" size="34"  style="text-transform:uppercase;" $setmode $readonly/></td>
                              </tr>
                              <tr> 
                                <td class="style38"><div align="right" class="style38">Poskod 
                                    :</div></td>
                                <td><label> 
                                  <input name="txtPoskodSimati" type="text" id="txtPoskodSimati " value="$!poskod" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" $setmode onBlur="validLength()" $readonly/>
                                  </label></td>
                              </tr>
                              <tr> 
                                <td class="style38"><div align="right" class="style38">Negeri :</div></td>
                                <td><select name="socNegeri" id="socNegeri" style="width: 225px;" $setmode2 onChange="getBandar('0','0','0','0')" $setmode3>
                              #set ($idnegeri = "")
                              #set ($namanegeri = "")
                              #set ($kodnegeri = "")
                              #set ($selectnegeri = "")
                              #if ($negeriId != "" || $negeriIdx != "")
	                              #foreach ($negeri in $ListNegeri)
	                              #set ($idnegeri = $negeri.id_Negeri)
	                              #set ($namanegeri = $negeri.nama_Negeri)
	                              #set ($kodnegeri = $negeri.kod_Negeri)
	                              		#if ($idnegeri == $negeriIdx || $idnegeri == $negeriId)
	                                <option value="$idnegeri" selected>$!namanegeri</option>
	                              		#end
	                              #end
                              	    <option value="0">SILA PILIH</option>
	                              #foreach ($negeri in $ListNegeri)
	                              #set ($idnegeri = $negeri.id_Negeri)
	                              #set ($namanegeri = $negeri.nama_Negeri)
	                              #set ($kodnegeri = $negeri.kod_Negeri)
	                                <option value="$idnegeri">$!namanegeri</option>
	                              #end
                              #else
                              	    <option value="0">SILA PILIH</option>
	                              #foreach ($negeri in $ListNegeri)
	                              #set ($idnegeri = $negeri.id_Negeri)
	                              #set ($namanegeri = $negeri.nama_Negeri)
	                              #set ($kodnegeri = $negeri.kod_Negeri)
	                                <option value="$idnegeri">$!namanegeri</option>
	                              #end
	                          #end
                                  </select></td>
                              </tr>
                              <tr> 
                                <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                                <td style="text-transform:uppercase;"><select name="socDaerah" id="socDaerah" $setmode2 $setmode3 style="text-transform:uppercase;width: 225px;">
           	  #if ($idBandar == "" || $idBandar == "0")
                   <option value="0">SILA PILIH</option>
                   #foreach ($listBandar in $listBandarbyNegeri)
                   <option value="$listBandar.idbandarnegeri">$!listBandar.nama</option>
                   #end
              
              #else
                   #foreach ($listBandar1 in $ListBandar)
                        #if ($listBandar1.id == $idBandar)
                   <option value="$listBandar1.id" selected>$!listBandar1.nama</option>
                        #end
                   #end
                  <option value="0" >SILA PILIH</option>
                   #foreach ($listBandar in $listBandarbyNegeri)
                   <option value="$listBandar.idbandarnegeri"> $!listBandar.nama</option>
                   #end
               #end
      </select></td>
                              </tr>
                              <tr> 
                                <td class="style38" valign="top"><div align="right" class="style38">Catatan 
                                    :</div></td>
                                <td><textarea name="txtCatatanSimati" cols="31" rows="3" id="Catatan" style="text-transform:uppercase;" $setmode $readonly>$!catatan</textarea></td>
                              </tr>
                              <tr>
                                <td class="style38" valign="top">&nbsp;</td>
                                <td>&nbsp;</td>
                              </tr>
                            </table></td>
                      </tr>
                    </table>
					<br>
					<br>
					<br><table>
                    <tr>
                    <td width="3%"><i>*</i></td>
                    <td width="97%"><i>Sila pastikan salah satu No Pengenalan diisi.</i></td>
                    </tr>
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
                 <tr>
                     
                    <td align="center">
                    
                    #if ($idstatus == "150" || $idstatus == "171") 
                    	#if ($SimpanStatus == 1) 
                      <input type="button" name="cmdKemaskini" id="cmdKemaskini2" value="Kemaskini" onClick="kemaskini_simati('0','0','0','0')" />
                      #else 
                      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="SimpanSimati('0','0','0','0')"/> 
                      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="SimatiView('0','0','0','0')"/>
						#end 
					 #end
					 </tr>
              </table>
              </div>
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent"></div>
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
<script>
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
	document.${formName}.method="POST";
	document.${formName}.mode.value="Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.submit();
}

function goalert() {
	alert("Sila masukkan maklumat Simati terlebih dahulu");
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
	doAjaxCall${formName}("nilai_harta");
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

function kembali_simati(){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	doAjaxCall${formName}("kembali_simati");
	document.${formName}.submit();
}

function kembali_simati(){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	doAjaxCall${formName}("kembali_simati");
	document.${formName}.submit();
}

function PengesahanView() {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();
}

function cancelwaris() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
document.${formName}.reset();
document.${formName}.txtNoKPBaru1Waris.focus();
}
}
<!-- PEMOHON -->

<!-- SIMATI -->
function kemaskini_simati(){
	document.${formName}.method="post";
	document.${formName}.mode.value="kemaskini_simati";
	doAjaxCall${formName}("Simati");
	document.${formName}.submit();
}

function SimpanSimati() {
	var currentTime = new Date()

	var str1  = document.getElementById("txdTarikhMati").value;
	var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);

	var negeri_code = document.${formName}.txtNoKPBaru2Simati.value;

	var tms = document.${formName}.txdTarikhMati;
	var dob_code = document.${formName}.txtNoKPBaru1Simati.value;
	
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

	var currentTime = new Date();
	var monthx = currentTime.getMonth() + 1;
	var dayx = currentTime.getDate();
	var yearx = currentTime.getFullYear();	
	var currentDate = dayx + "/" + monthx + "/" + yearx;

	
	if (document.${formName}.txtNoKPBaru1Simati.value == "" && document.${formName}.txtNoKPBaru2Simati.value == "" && document.${formName}.txtNoKPBaru3Simati.value == "" && document.${formName}.txtNoKPLamaSimati.value == "" && document.${formName}.txtNoKPLainSimati.value == "") {
		alert("Sila masukkan salah satu No KP simati");
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && document.${formName}.txtNoKPBaru1Simati.value.length < 6){
		alert("Sila masukkan No KP Baru simati sepenuhnya");
		txtNoKPBaru1Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru2Simati.value != "" && document.${formName}.txtNoKPBaru2Simati.value.length < 2){
		alert("Sila masukkan No KP Baru simati sepenuhnya");
		txtNoKPBaru2Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru3Simati.value != "" && document.${formName}.txtNoKPBaru3Simati.value.length < 4){
		alert("Sila masukkan No KP Baru simati sepenuhnya");
		txtNoKPBaru3Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && document.${formName}.txtNoKPBaru2Simati.value == "" && document.${formName}.txtNoKPBaru3Simati.value == ""){
		alert("Sila masukkan No KP Baru simati sepenuhnya");
		txtNoKPBaru2Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && document.${formName}.txtNoKPBaru2Simati.value != "" && document.${formName}.txtNoKPBaru3Simati.value == ""){
		alert("Sila masukkan No KP Baru simati sepenuhnya");
		txtNoKPBaru3Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && document.${formName}.txtNoKPBaru2Simati.value == "" && document.${formName}.txtNoKPBaru3Simati.value != ""){
		alert("Sila masukkan No KP Baru simati sepenuhnya");
		txtNoKPBaru2Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value == "" && document.${formName}.txtNoKPBaru2Simati.value != "" && document.${formName}.txtNoKPBaru3Simati.value != ""){
		alert("Sila masukkan No KP Baru simati sepenuhnya");
		txtNoKPBaru1Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru1Simati.value != "" && isIc(tt)==false){
	  	txtNoKPBaru1Simati.focus();
	}
	else if (document.${formName}.txtNoKPBaru2Simati.value != "" &&(negeri_code!="01" && negeri_code!="21" && negeri_code!="22" && negeri_code!="23" && negeri_code!="24" && negeri_code!="02" && negeri_code!="25" && negeri_code!="26" && negeri_code!="27" && negeri_code!="03" && negeri_code!="28" && negeri_code!="29" && negeri_code!="04" && negeri_code!="30" && negeri_code!="05" && negeri_code!="31" && negeri_code!="59" && negeri_code!="06" && negeri_code!="32" && negeri_code!="33" && negeri_code!="07" && negeri_code!="34" && negeri_code!="35" && negeri_code!="08" && negeri_code!="36" && negeri_code!="37" && negeri_code!="38" && negeri_code!="39"  && negeri_code!="09" && negeri_code!="40" && negeri_code!="10" && negeri_code!="41" && negeri_code!="42" && negeri_code!="43" && negeri_code!="44" && negeri_code!="11" && negeri_code!="45" && negeri_code!="46" && 
		negeri_code!="12" && negeri_code!="47" && negeri_code!="48" && negeri_code!="49" && negeri_code!="13" && negeri_code!="50" && negeri_code!="51" && negeri_code!="52" && negeri_code!="53" && negeri_code!="14" && negeri_code!="54" && negeri_code!="55" && negeri_code!="56" && negeri_code!="57" && negeri_code!="15" && negeri_code!="58" && negeri_code!="16" && negeri_code!="82" && negeri_code!="71" && negeri_code!="88" && negeri_code!="99")) {
		alert("Sila masukkan kod negeri yang sah");
		txtNoKPBaru2Simati.focus()
	}
	else if (document.${formName}.socJenisKPLainSimati.value != "0" && document.${formName}.txtNoKPLainSimati.value == ""){
		alert("Sila masukkan No KP Lain simati");
		txtNoKPLainSimati.focus();
	}
	else if (document.${formName}.txtNoKPLainSimati.value != "" && document.${formName}.socJenisKPLainSimati.value == "0"){
		alert("Sila masukkan jenis No KP Lain simati");
		socJenisKPLainSimati.focus();
	}
	else if (document.${formName}.txtNamaSimati.value == ""){
		alert("Sila masukkan Nama Simati");
		txtNamaSimati.focus();
	}
	else if (document.${formName}.txdTarikhMati.value=="" ){
		alert("Sila masukkan Tarikh Mati");
		txdTarikhMati.focus();
	}
	else if (document.${formName}.socBuktiMati.value =="0"){
		alert("Sila pilih bukti kematian");
		socBuktiMati.focus();
	}
	else if (isDate(tms.value)==false){
		tms.focus()
	}
	else if (document.${formName}.txdTarikhMati.value != "" && date1 > currentTime){
		alert("Sila pastikan tarikh mati tidak melebihi dari tarikh hari ini.");
		txdTarikhMati.focus();
	}
	else if (document.${formName}.txtSebabKematianSimati.value=="" ){
		alert("Sila masukkan Sebab Kematian");
		txtSebabKematianSimati.focus();
	}
	else if (document.${formName}.txtPoskodSimati.value != "" && document.${formName}.txtPoskodSimati.value.length < 5){
		alert("Sila masukkan No Poskod sepenuhnya");
		txtPoskodSimati.focus();
	}
	else{
	input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
			document.${formName}.method="post";
			document.${formName}.mode.value="simpan_simati";
			doAjaxCall${formName}("Simati");
			document.${formName}.submit();
		}
	}
}

function BatalSimati() {
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		document.${formName}.mode.value="batal_simati";
		doAjaxCall${formName}("Simati");
		document.${formName}.submit();
	}
}

function getBandar() {
		document.${formName}.method="post";
		document.${formName}.mode.value="getBandar";
		doAjaxCall${formName}("Simati");
		document.${formName}.submit();
}


function validTarikhMati() {
	if (document.${formName}.socWaktuKematianSimati.value != "" && document.${formName}.socWaktuKematianSimati.value.length < 4) {
		alert("Sila masukkan waktu mati dalam format yang betul");
		socWaktuKematianSimati.focus();
	}
	else if (document.${formName}.socWaktuKematianSimati.value != "" && (document.${formName}.socWaktuKematianSimati.value.charAt(2)> 5)) {
		alert("Sila masukkan waktu mati dalam format yang betul");
		socWaktuKematianSimati.focus();
	}
	else if (document.${formName}.socWaktuKematianSimati.value != "" && (document.${formName}.socWaktuKematianSimati.value.charAt(0)> 2)) {
		alert("Sila masukkan waktu mati dalam format yang betul");
		socWaktuKematianSimati.focus();
	}
	else if (document.${formName}.socWaktuKematianSimati.value != "" && (document.${formName}.socWaktuKematianSimati.value.charAt(0)==2 && document.${formName}.socWaktuKematianSimati.value.charAt(1)> 3)) {
		alert("Sila masukkan waktu mati dalam format yang betul");
		socWaktuKematianSimati.focus();
	}	
}

function validLength(){
	if (document.${formName}.txtPoskodSimati.value!="" && document.${formName}.txtPoskodSimati.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodSimati.focus();
	}
}

function getJantina(elmnt,content){
	//var ch=document.f1.txtNoKPBaru3Simati.value.charAt(3);
	var ch=content.charAt(3);
	if(ch%2 == 0) {
	document.${formName}.socJantina.value = 2 ;
	}
	if(ch%2 != 0) {
	document.${formName}.socJantina.value = 1 ;
	}
return;
}

function Checkumur() 
{
	if (document.${formName}.txtUmurSimati.value.length >= 3) {
		alert("Adakah anda pasti umur simati adalah "+document.${formName}.txtUmurSimati.value+" tahun?");
		txtUmurSimati.focus();
	}
}

function validDate() {
	var currentTime = new Date()

	 var str1  = document.getElementById("txdTarikhMati").value;
	 var dt1   = parseInt(str1.substring(0,2),10);
     var mon1  = parseInt(str1.substring(3,5),10)-1;
     var yr1   = parseInt(str1.substring(6,10),10);
   
    var date1 = new Date(yr1, mon1, dt1);

	if (document.${formName}.txdTarikhMati.value != "" && date1 > currentTime){
		alert("Sila pastikan tarikh mati tidak melebihi dari tarikh hari ini.");
		txdTarikhMati.focus();
	}
}

function getBuktiMati(val){
	if(val=="1")
	{
		document.${formName}.txtNoSijilMatiSimati.disabled = "";
		document.${formName}.txtNoSijilMatiSimati.value = "";
		document.${formName}.socBuktiKematianSimati.value="1";
	}
	if(val=="4")
	{
		document.${formName}.txtNoSijilMatiSimati.disabled = "";
		document.${formName}.txtNoSijilMatiSimati.value = "";
		document.${formName}.socBuktiKematianSimati.value="4";
	}	
	else {
		document.${formName}.txtNoSijilMatiSimati.disabled = "disabled";
		document.${formName}.txtNoSijilMatiSimati.value = "";
		document.${formName}.socBuktiKematianSimati.value = "val";
	}
}

function submitForm() {    
   // document.val.focus();
	goTo('$val_tab');
	Effect.ScrollTo('$val_tab').focus(); return false;
	
	//doucument.f1.'$val_tab'.focus();
} 

function qryHowOld()
   {
    var dob_code = document.${formName}.txtNoKPBaru1Simati.value;
   
	if(dob_code.charAt(0)<3)
	{
	 	var dum = "20";
	}
	else
	{
		var dum = "19";
	}
	
	 var tt = dob_code.charAt(4)+""+dob_code.charAt(5)+"/"+dob_code.charAt(2)+""+dob_code.charAt(3)+"/"+dum+dob_code.charAt(0)+""+dob_code.charAt(1);	
	 var dt_dob   = parseInt(tt.substring(0,2),10);
     var mon_dob  = parseInt(tt.substring(3,5),10)-1;
     var yr_dob   = parseInt(tt.substring(6,10),10);

	 var dob_codeX = document.${formName}.txdTarikhMati.value;

	var currentTime = new Date()
	var month = currentTime.getMonth() + 1
	var day = currentTime.getDate()
	var year = currentTime.getFullYear()
	
	if(day<10) day = "0" + day
  	if(month<10) month= "0" + month 
  	if(year<1000) year+=1900
	
	var currentDate = day + "/" + month + "/" + year;

	if (dob_codeX==""){
		//var ttX = currentDate;
		document.${formName}.txtUmurSimati.value = "" ;
	}else{
	 	var ttX = dob_codeX;
	}	
	 var dt_dobX   = parseInt(ttX.substring(0,2),10);
     var mon_dobX  = parseInt(ttX.substring(3,5),10)-1;
     var yr_dobX   = parseInt(ttX.substring(6,10),10);

     var varAsOfDate = new Date(yr_dobX, mon_dobX, dt_dobX);
	 var varBirthDate = new Date(yr_dob, mon_dob, dt_dob);
	 
	 var year1 = varAsOfDate.getFullYear();
	 var year2 = varBirthDate.getFullYear();
	 var year3 = year1 - year2;

	if (dob_code == "" || dob_code.length < 6 ) 
	{
	 	document.${formName}.txtUmurSimati.value = "" ;
	}
	else
	{
	 	document.${formName}.txtUmurSimati.value = year3 ;
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


document.${formName}.txdTarikhMati.value = trans;

qryHowOld();
}
else
{
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
<SCRIPT LANGUAGE="JavaScript">
var cal = new CalendarPopup("testdiv1");
cal.showNavigationDropdowns();
</script>
<SCRIPT LANGUAGE="JavaScript">

function refreshIt(divID,MyPage){
	xmlHttp = getXMLttp();
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4) {setInnerText(divID, xmlHttp.responseText); }
	}
	xmlHttp.open("GET", MyPage, true);
	xmlHttp.send(null);
	return false;
}

function setInnerText(divID, response) {
	val el = (document.getElementById) ? document.getElementById(divID) : document.all[divID];
	if(el) {el.innerText = response;}
}

</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:0});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:0});
//-->
</script>
</body>
</html>