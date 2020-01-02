<html>
<head>
<title>Untitled Document</title>
<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>
</head>

<body>
<table width="97%" border="0">
<tr>
<td>
#set ($IdPemohon = "")
#set ($flag = "")
#set ($idhub = "")

#foreach ($dataPemohon in $ListDataPemohon)
#set ($IdPemohon = $dataPemohon.idPemohon)
#if ($dataPemohon.noKpBaruPemohon1!="")
#set ($noKpBaru1 = $dataPemohon.noKpBaruPemohon1)
#end
#if ($dataPemohon.noKpBaruPemohon2!="")
#set ($noKpBaru2 = $dataPemohon.noKpBaruPemohon2)
#end
#if ($dataPemohon.noKpBaruPemohon3!="")
#set ($noKpBaru3 = $dataPemohon.noKpBaruPemohon3)
#end
#if ($dataPemohon.namaPemohon!="")
#set ($name = $dataPemohon.namaPemohon)
#end
#if ($dataPemohon.noKpLama!="")
#set ($nokplama = $dataPemohon.noKpLama)
#end
#if ($dataPemohon.umurPemohon!="")
##set ($umur = $dataPemohon.umurPemohon)
#end
#if ($dataPemohon.jantinaPemohon!="")
#set ($jantina = $dataPemohon.jantinaPemohon)
#end
#if ($dataPemohon.jenisagama!="")
#set ($agama = $dataPemohon.jenisagama)
#end
#if ($dataPemohon.jeniswarga!="")
#set ($warga = $dataPemohon.jeniswarga)
#end
#if ($dataPemohon.alamat1!="")
#set ($add1 = $dataPemohon.alamat1)
#end
#if ($dataPemohon.alamat2!="")
#set ($add2 = $dataPemohon.alamat2)
#end
#if ($dataPemohon.alamat3!="")
#set ($add3 = $dataPemohon.alamat3)
#end
#if ($dataPemohon.bandarpemohon!="")
#set ($bandar = $dataPemohon.bandarpemohon)
#end
#if ($dataPemohon.poskodpemohon!="")
#set ($poskod = $dataPemohon.poskodpemohon)
#end
#if ($dataPemohon.hppemohon!="")
#set ($nohp = $dataPemohon.hppemohon)
#end
#if ($dataPemohon.telpemohon!="")
#set ($notel = $dataPemohon.telpemohon)
#end
#if ($dataPemohon.faxpemohon!="")
#set ($nofax = $dataPemohon.faxpemohon)
#end
#if ($dataPemohon.emelpemohon!="")
#set ($email = $dataPemohon.emelpemohon)
#end
#if ($dataPemohon.catatan!="")
#set ($catatan = $dataPemohon.catatan)
#end
#if ($dataPemohon.tarafkptg!="")
#set ($taraf = $dataPemohon.tarafkptg)
#end
#if ($dataPemohon.saudara!="")
#set ($saudara = $dataPemohon.saudara)
#end
#if ($dataPemohon.statuspeguam!="")
#set ($peguam = $dataPemohon.statuspeguam)
#end
#if ($dataPemohon.noKpLain!="")
#set ($nokplain = $dataPemohon.noKpLain)
#end
#if ($dataPemohon.jenisKp!="")
#set ($jenisKpPemohon = $dataPemohon.jenisKp)
#end
#set ($flaghub = $dataPemohon.flaghubungan)
#set ($idhub = $dataPemohon.idhubungan)
#if ($dataPemohon.alamat1surat!="")
#set ($add1surat = $dataPemohon.alamat1surat)
#end
#if ($dataPemohon.alamat2surat!="")
#set ($add2surat = $dataPemohon.alamat2surat)
#end
#if ($dataPemohon.alamat3surat!="")
#set ($add3surat = $dataPemohon.alamat3surat)
#end
#if ($dataPemohon.bandarsurat!="")
#set ($bandarsurat = $dataPemohon.bandarsurat)
#end
#if ($dataPemohon.poskodsurat!="")
#set ($poskodsurat = $dataPemohon.poskodsurat)
#end
#end

<input type="hidden" name="v_tab" id="v_tab" value="" />
<input name="idpermohonansimati" type="hidden"  value="$idpermohonansimati"/>
  #foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
    #set ($idSimati = $list.idSimati)
<input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
<input name="idPemohon" type="hidden"  value="$idPemohon"/>
<input name="idSimati" type="hidden"  value="$idSimati"/>
<input name="idtemp" type="hidden"  value="$id"/>
#end

 <input type="hidden" name="hitButt">
 <input type="hidden" name="mode">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="simpanStatus" value="$SimpanStatus">
  <input type="hidden" name="idPemohon" value="$IdPemohon" />
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/> 
  <input name="flaghub" type="hidden" id="flaghub" value="$flaghub"/> 
</td>
</tr>
<tr>
<td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:SimatiView();">PERMOHONAN</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:HtaamView();">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:HAview()">HARTA ALIH</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
      <div id="TabbedPanels2" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:SimatiView()">SIMATI</li>
          <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:PemohonView()">PEMOHON</li>
          <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:WarisView()">WARIS</li>
          <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:PemiutangView()">PEMIUTANG</li>
          <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:PenghutangView()">PENGHUTANG</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent">
            <div id="TabbedPanels3" class="TabbedPanels">
              <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:PemohonView()">PEMOHON</li>
                #if ($peguam == "Y")
                #set ($checkPeguam = "checked")
                <li class="TabbedPanelsTab" tabindex="0" onClick="javascript:PeguamView()">PEGUAM</li>
                #end
              </ul>
              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent">
<table width="97%" border="0">
                      <tr>
                        <td><p></p>                       
                        
                            <fieldset>
                            <legend>MAKLUMAT PEMOHON</legend>
                              <table width="100%">
                              <tr>
                                <td width="50%" valign="top">
                                    <table width="100%" border="0">
                                      <tr>
                                        <td width="30%"><div align="right" class="style1"><span class="style38">MyID Baru :</span></div></td>
                                        <td width="70%" class="style36"><label>
                                          <input name="txtnoKpBaru1Pemohon" type="text" id="txtnoKpBaru1Pemohon" value="$!noKpBaru1" size="6" maxlength="6" $readonly onBlur="getAgeByIC(this,this.value,'txtUmurPemohon')" $setmode2 />-<input name="txtnoKpBaru2Pemohon" type="text" id="textfield9" value="$!noKpBaru2" size="1" maxlength="2" $readonly $setmode2 />-<input name="txtnoKpBaru3Pemohon" type="text" id="textfield10" value="$!noKpBaru3" size="4" maxlength="4" $readonly $setmode2 />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td width="30%"><div align="right" class="style1"><span class="style38">MyID Lama :</span></div></td>
                                        <td width="70%" class="style36"><input name="txtNoKPLamaPemohon" type="text" value="$!nokplama" id="txtNoKPLamaPemohon" size="8" maxlength="8" style="text-transform:uppercase;" $setmode $readonly/></td>
                                      </tr>
                                      <tr>
                                        <td ><div align="right" class="style1"><span class="style38">Lain-lain MyID :</span></div></td>
                                        <td class="style36"><select name="socJenisKP" style="width: 100px;" $setmode2 $setmode3> 
                                        #set ($id = "")
							            #set ($keterangan = "")
							            #set ($selected = "")
								        #if ($jenisKpPemohon != "")    
								            #foreach($Listkp in $listkp)
								            #set ($id = $Listkp.id)
								            #set ($keterangan = $Listkp.keterangan)
								            	#if ($id == $$jenisKpPemohon)
								            		#set ($selected = "selected")
								            	<option value="$id" $selected/>$keterangan.toUpperCase()</option>
								            	#end
								            #end
							            	<option value="0"/>SILA PILIH</option>
							            	#foreach($Listkp in $listkp)
							            	#set ($id = $Listkp.id)
							           		#set ($keterangan = $Listkp.keterangan)
								            	<option value="$id"/>$keterangan.toUpperCase()</option>
							            	#end
							           	#else
							           		<option value="0"/>SILA PILIH</option>
							            	#foreach($Listkp in $listkp)
							            	#set ($id = $Listkp.id)
							           		#set ($keterangan = $Listkp.keterangan)
								            	<option value="$id"/>$keterangan.toUpperCase()</option>
							            	#end
							           	#end
							            	</select>&nbsp;<input name="txtNoKPLainPemohon" type="text" id="textfield5"  value="$!nokplain" style="width: 97px; text-transform:uppercase;" maxlength="12" $setmode $readonly/></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right" class="style1"><span class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Nama Pemohon :</span></div></td>
                                        <td class="style36">

                                          <input name="txtNamaPemohonPemohon" type="text" id="txtNamaPemohonPemohon" value="$!name" size="34" $setmode style="text-transform:uppercase;" $readonly/>                                       </td>
                                      </tr>
                                      <tr>
                                        <td><div align="right" class="style1"><span class="style38">Jantina :</span></div></td>
                                        <td class="style36"><select name="socJantina" style="width: 225px;" $setmode2 onChange="socJantina()" $setmode3>
		                              #if ($jantina != "0")
		                              		#if ($jantina == "2")
			                                <option value="2" selected>PEREMPUAN</option>
			                                <option value="1">LELAKI</option>
		                              		#elseif ($jantina == "1")
											<option value="1" selected>LELAKI</option>
			                                <option value="2">PEREMPUAN</option>
		                              		#else 
		                              		<option value="0">SILA PILIH</option>
			                                <option value="2">PEREMPUAN</option>
			                                <option value="1">LELAKI</option>
		                              		#end
		                              #else
		                                <option value="0">SILA PILIH</option>
		                                <option value="2">PEREMPUAN</option>
		                                <option value="1">LELAKI</option>
		                              #end
		                                </select></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right" class="style1"><span class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Taraf Kepentingan :</span></div></td>
                                        <td><select name="socTaraf" style="width: 225px;" $setmode2 $setmode3>
                                        #set ($tarafid = "")
                                        #set ($tarafkod = "")
                                        #set ($tarafketerangan = "")
                                    #if ($taraf != "")
                                    	#set ($selectTaraf = "")
                                    	#foreach ($listtaraf in $ListTaraf)
                                        #set ($tarafid = $listtaraf.id_Tarafkptg)
                                        #set ($tarafkod = $listtaraf.kod)
                                        #set ($tarafketerangan = $listtaraf.keterangan)	
                                        	#if ($tarafid == $taraf)
                                        	#set ($selectTaraf = "selected")
                                        <option value="$tarafid" $selectTaraf>$tarafketerangan</option>
                                        	#end
                                        #end
                                        <option value="0">SILA PILIH</option>
                                        #foreach ($listtaraf in $ListTaraf)
                                        #set ($tarafid = $listtaraf.id_Tarafkptg)
                                        #set ($tarafkod = $listtaraf.kod)
                                        #set ($tarafketerangan = $listtaraf.keterangan)
                                        <option value="$tarafid">$tarafketerangan</option>
                                        #end
                                    #else
                                    	#set ($selectflag = "")
                                        #foreach ($listtaraf in $ListTaraf)
                                        #set ($tarafid = $listtaraf.id_Tarafkptg)
                                        #set ($tarafkod = $listtaraf.kod)
                                        #set ($tarafketerangan = $listtaraf.keterangan)
                                        	#if ($taraf == "")

                                        		#if ($tarafid == $idhub)
                                        			#set ($selectflag = "selected")
                                        <option value="$tarafid" $selectflag>$tarafketerangan</option>
 												#end
 											#end
                                        #end
                                        <option value="0">SILA PILIH</option>
                                        #foreach ($listtaraf in $ListTaraf)
                                        #set ($tarafid = $listtaraf.id_Tarafkptg)
                                        #set ($tarafkod = $listtaraf.kod)
                                        #set ($tarafketerangan = $listtaraf.keterangan)
                                        <option value="$tarafid">$tarafid - $tarafketerangan</option>
                                        #end
                                    #end
                                        </select></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right" class="style1"><span class="style38">Talian Persaudaraan :</span></div></td>
                                        <td><select name="socTalianSaudara" style="width: 225px;" $setmode2 $setmode3>
							            #set ($idsaudara = "")
							        	#set ($kodsaudara = "")
							        	#set ($keterangansaudara = "")
                                        	#if ($jantina=="2")
                                            	#set ($jan="02")
                                            #elseif ($jantina=="1")
                                            	#set ($jan="01")
                                            #elseif ($jantina=="")
                                            	#set ($jan="")
                                            #end
							        #if ($saudara == "0")
                                    #set ($selectHub = "")
							        	#foreach ($list in $ListPersaudaraan)
							            #set ($idsaudara = $list.id_Saudara)
							        	#set ($kodsaudara = $list.kod)
							        	#set ($keterangansaudara = $list.keterangan)
								        	#if ($saudara == "" && $flag == 1)
								        		#if ($idsaudara == $idhub)
								        			#set ($selectHub = "selected")
								            	<option value="$idsaudara" $selectHub>$keterangansaudara</option>
								            	#end
								            #end
							            #end
							        	<option value="0">SILA PILIH</option>
							            #foreach ($list in $ListPersaudaraan)
							            #set ($idsaudara = $list.id_Saudara)
							        	#set ($kodsaudara = $list.kod)
							        	#set ($keterangansaudara = $list.keterangan)
							            	<option value="$idsaudara">$idsaudara - $keterangansaudara</option>
							            #end
							        #else
							        	#set ($selectSaudara = "")
							            #foreach ($list in $ListPersaudaraan)
							            #set ($idsaudara = $list.id_Saudara)
							        	#set ($kodsaudara = $list.kod)
							        	#set ($keterangansaudara = $list.keterangan)
                                    		#if ($idsaudara == $saudara || $list.jantina == $jan)
							        			#set ($selectSaudara = "selected")
							            	<option value="$idsaudara" $selectSaudara>$keterangansaudara</option>
							            	#end
							            #end
							            <option value="0">SILA PILIH</option>
							            #foreach ($list in $ListPersaudaraan)
							            #set ($idsaudara = $list.id_Saudara)
							        	#set ($kodsaudara = $list.kod)
							        	#set ($keterangansaudara = $list.keterangan)
                                        	#if ($idsaudara == $saudara)
							            	<option value="$idsaudara" selected>$keterangansaudara</option>
                                            #end
							            #end
							        #end
							            </select></td>
						            </tr>
                                      <tr>
                                        <td><div align="right" class="style1"><span class="style38">Agama :</span></div></td>
                                        <td class="style36"><select name="socAgama" style="width: 225px;" $setmode2 $setmode3>
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
                                        <td><div align="right" class="style1"><span class="style38">Warganegara :</span></div></td>
                                        <td class="style36"><select name="socWarga" style="width: 225px;" $setmode2 $setmode3>
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
                                        <td><div align="right" class="style1"><span class="style38">Umur :</span></div></td>
                                        <td class="style36"><label>
                                          <input name="txtUmurPemohon" type="text" id="txtUmurPemohon" value="$!umur"  size="3" maxlength="3" $readmode onKeyUp="javascript:validatePoskod(this,this.value)" $setmode $readonly/>
                                        </label></td>
                                      </tr>
                                      
									  <tr>
                                      <td width="30%" class="style38"><div align="right" class="style38 style1"><font class="mandatory" color="#FF0000">*&nbsp;</font>Alamat Tetap :</div></td>
                                      <td width="70%"><label>
                                        <input name="txtAlamatTerakhir1Pemohon" type="text" id="txtAlamatTerakhir" value="$!add1" size="34"  style="text-transform:uppercase;" $setmode $readonly/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right"><span class="style3"><span class="style1"></span></span></div></td>
                                      <td><label>
                                        <input name="txtAlamatTerakhir2Pemohon" type="text" id="txtAlamatTerakhir2"  value="$!add2" size="34"  style="text-transform:uppercase;" $setmode $readonly/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right"><span class="style3"><span class="style1"></span></span></div></td>
                                      <td><input name="txtAlamatTerakhir3Pemohon" type="text" id="txtAlamatTerakhir3" value="$!add3" size="34" style="text-transform:uppercase;" $setmode $readonly/></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38 style1"><font class="mandatory" color="#FF0000">*&nbsp;</font>Poskod :</div></td>
                                      <td><label>
                                      <input name="txtPoskodPemohon" type="text" id="txtPoskodPemohon " value="$!poskod" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()" $setmode $readonly/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38 style1"><font class="mandatory" color="#FF0000">*&nbsp;</font>Negeri :</div></td>
                                      <td>$selectNegeriTetap </td>
                                    </tr>
									<tr>
                                      <td class="style38"><div align="right" class="style38 style1"><font class="mandatory" color="#FF0000">*&nbsp;</font>Bandar :</div></td>
                                      <td style="text-transform:uppercase;"><!--<input name="txtBandarPemohon" type="text" id="txtBandarPemohon " value="$!bandar" size="34" style="text-transform:uppercase;" $setmode/>-->$selectBandarTetap</td>
                                    </tr>
									<tr>
                                      <td class="style38"></td>
                                      <td><table>
                                      <tr>
                                      <td width="3%"><input type="checkbox" name="chcAlamat" value="1" onClick="getDuplicateAddress()" $setmode3 $check3/></td>
                                      <td width="97%"><font class="style38"><i>Sekiranya alamat tetap dan alamat surat menyurat adalah sama</i></font></td>
                                      </tr>
                                      </table></td>
                                    </tr>
                                  </table></td>
                                <td><table width="100%">
                                      <tr> 
                                        <td width="40%" class="style38"><div align="right" class="style38 style1"><font class="mandatory" color="#FF0000">*&nbsp;</font>Alamat Surat Menyurat :</div></td>
                                        <td width="60%"><label> 
                                          <input name="txtAlamatSurat1Pemohon" type="text" id="txtAlamatSurat1Pemohon" value="$!add1surat" size="34"  style="text-transform:uppercase;" $setmode $readonly/>
                                          </label></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38"><div align="right"><span class="style3"><span class="style1"></span></span></div></td>
                                        <td><label> 
                                          <input name="txtAlamatSurat2Pemohon" type="text" id="txtAlamatSurat2Pemohon"  value="$!add2surat" size="34"  style="text-transform:uppercase;" $setmode $readonly/>
                                          </label></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38"><div align="right"><span class="style3"><span class="style1"></span></span></div></td>
                                        <td><input name="txtAlamatSurat3Pemohon" type="text" id="txtAlamatSurat3Pemohon" value="$!add3surat" size="34" style="text-transform:uppercase;" $setmode $readonly/></td>
                                      </tr>
                                      <tr> 
                                   <td class="style38"><div align="right" class="style38 style1"><font class="mandatory" color="#FF0000">*&nbsp;</font>Poskod
                                        :</div></td>
                                        <td><label> 
                                          <input name="txtPoskodSuratPemohon" type="text" id="txtPoskodSuratPemohon " value="$!poskodsurat" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()" $setmode $readonly/>
                                          </label></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38"><div align="right" class="style38 style1"><font class="mandatory" color="#FF0000">*&nbsp;</font>Negeri :</div></td>
                                        <td>$selectNegeriSurat</td>
                                      </tr>
									  <tr> 
                                        <td class="style38"><div align="right" class="style38 style1"><font class="mandatory" color="#FF0000">*&nbsp;</font>Bandar :</div></td>
                                        <td><label> 
                                          <!--<input name="txtBandarSuratPemohon" type="text" id="txtBandarSuratPemohon " value="$!bandarsurat" size="34" style="text-transform:uppercase;" $setmode/>-->
                                          </label>$selectBandarSurat</td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" ><div align="right" class="style1">No Telefon :</div></td>
                                        <td><input name="txtNoTelefonPemohon" type="text" id="txtNoTelefonPemohon" value="$!notel" size="12" maxlength="11" $setmode onKeyUp="javascript:validatePoskod(this,this.value)" $readonly/>&nbsp;<span class="style52">format : 031234567</span></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" ><div align="right" class="style1">No Telefon Bimbit :</div></td>
                                        <td><input name="txtNoTelefonBimbitPemohon" type="text" id="txtNoTelefonBimbitPemohon" value="$!nohp" size="12"  maxlength="10" onKeyUp="javascript:validatePoskod(this,this.value)" $setmode $readonly/>&nbsp;<span class="style52">format : 0121234567</span></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" ><div align="right" class="style1">No Faks :</div></td>
                                        <td><input name="txtNoFaksPemohon" type="text" id="txtNoFaksPemohon" value="$!nofax" size="12" maxlength="12" $setmode onKeyUp="javascript:validatePoskod(this,this.value)" $readonly/></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" ><div align="right" class="style1">Emel :</div></td>
                                        <td><input name="txtEmelPemohon" type="text" id="txtEmelPemohon" value="$!email" size="34" $setmode $readonly/></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right" class="style1"><span class="style38">Diwakili Peguam :</span></div></td>
                                        <td class="style36"><label>
                                          <input type="checkbox" name="checkPeguam" id="checkPeguam" value="Y" $checkPeguam $setmode3 $readonly>
                                        </label></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" valign="top"><div align="right" class="style38 style1">Catatan :</div></td>
                                        <td><textarea name="txtCatatanPemohon" cols="31" rows="3" id="txtCatatanPemohon" $setmode style="text-transform:uppercase;" $readonly>$!catatan</textarea></td>
                                      </tr>
                                      <tr>
                                        <td class="style38" ><p>&nbsp;</p>
                                          <p>&nbsp;</p>
                                          <p>&nbsp;</p>
                                          <p>&nbsp;</p>
                                          <p>&nbsp;</p>
                                          <p>&nbsp;</p></td>
                                        <td>&nbsp;</td>
                                      </tr>
                                  </table></td>
                              </tr>
                            </table>
							<br>
                          <table>
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
                              </fieldset>
                          <p> </p></td>
                      </tr>
                      <tr>
                        <td align="center"> 
							#if ($idstatus=="150" || $idstatus=="171")
                        		#if ($KemaskiniStatus == 1)
                                      <input type="button" name="cmdKemaskin2" id="cmdKemaskin2" value="Kemaskini"  onClick="kemaskini_pemohon('0','1','0','0')"/>
                                	  #else
                                      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onClick="SimpanPemohon('0','1','0','0')"/>
                                      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onClick="PemohonView('0','1','0','0')"/>
                                #end     
                             #end
                                      </td>
                      </tr>
                    </table>                    </div>
                <div class="TabbedPanelsContent"></div>
              </div>
            </div>
             &nbsp;
          </div>
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent"></div>
          <div class="TabbedPanelsContent"></div>
        </div>
      </div>
       &nbsp;
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

function PentingView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pentingview";
	doAjaxCall${formName}("Penting");
	document.${formName}.submit();
}

function SimpanPemohon(){
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.${formName}.txtEmelPemohon.value;	
	
	if (document.${formName}.txtNamaPemohonPemohon.value == "0"){
		alert("Sila masukan Nama Pemohon");
		document.${formName}.txtNamaPemohonPemohon.focus();
	}
	else if (document.${formName}.socTaraf.value == "0"){
		alert("Sila pilih Taraf Kepentingan");
		document.${formName}.socTaraf.focus();
	}
	else if (document.${formName}.txtPoskodPemohon.value!="" && document.${formName}.txtPoskodPemohon.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		document.${formName}.txtPoskodPemohon.focus();
	}
	else if(!em.match(emailExp) && em!=""){
		alert("Alamat email tidak sah!");		
		document.${formName}.txtEmelPemohon.focus();
	}
	else{
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
		//document.${formName}.method="post";
		document.${formName}.mode.value="simpan_pemohon_data";
		doAjaxCall${formName}("Pemohon");
		//document.${formName}.submit();
		
		}
	}
}

function emailValidator(elem, helperMsg){
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	
	if(elem.value!=""){
	if(elem.value.match(emailExp)){
		return true;
	}else{
		alert(helperMsg);		
		elem.focus();
		return false;
	}
	}
}

function kemaskini_pemohon(){
	//document.${formName}.method="post";
	document.${formName}.mode.value="kemaskini_pemohon";
	doAjaxCall${formName}("Pemohon");
	//document.${formName}.submit();
}

function PeguamView() {
	document.${formName}.method="post";
	document.${formName}.mode.value="Peguamview";
	doAjaxCall${formName}("Peguam");
	document.${formName}.submit();
}

function cancelwaris() {
	input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.${formName}.reset();
		document.${formName}.txtNoKPBaru1Waris.focus();
	}
}

<!-- PEMOHON -->
function BatalPemohon() {
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="batal_pemohon";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.submit();
	}
}

function getDaerahAlamatPemohon() {
	document.${formName}.method="post";
	document.${formName}.mode.value="getDaerah";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.submit();
}

function validLength(){
	if (document.${formName}.txtPoskodPemohon.value!="" && document.${formName}.txtPoskodPemohon.value.length < 5) {
		alert("Sila masukkan No Poskod dengan lengkap.");
		document.${formName}.txtPoskodPemohon.focus();
	}
}

function submitForm() {    
   // document.val.focus();
	goTo('$val_tab');
	Effect.ScrollTo('$val_tab').focus(); return false;
	
	//doucument.f1.'$val_tab'.focus();
} 

function socJantina(){
	socTalianSaudara.refresh();
}

function onChangeBandarTetap(){
	document.${formName}.mode.value="onChangeBandarTetap";
	doAjaxCall${formName}("Pemohon");
}

function getDuplicateAddress(){
		if (document.${formName}.chcAlamat.checked == true) {
				document.${formName}.chcAlamat.checked = true;
				document.${formName}.mode.value="onChangeDuplicate";
				doAjaxCall${formName}("Pemohon");

		}else if (document.${formName}.chcAlamat.checked == false) {
				document.${formName}.chcAlamat.checked = false;
				document.${formName}.mode.value="onChangeNotDuplicate";
				doAjaxCall${formName}("Pemohon");
		}
}
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:1});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:0});
//-->
</script>
</body>
</html>
