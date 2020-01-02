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
.style40 {color: #0000FF}
.style42 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>

#set ($IdPemohon = "")

#set ($peguam = "")
#set ($negeriId = "")
#set ($idhub = "")

#if ($new_data != "yes")
    #foreach ($dataPemohon in $ListDataPemohon)
    #set ($IdPemohon = $dataPemohon.idPemohon)
    #set ($noKpBaru1 = $dataPemohon.noKpBaruPemohon1)
    #set ($noKpBaru2 = $dataPemohon.noKpBaruPemohon2)
    #set ($noKpBaru3 = $dataPemohon.noKpBaruPemohon3)
    #set ($name = $dataPemohon.namaPemohon)
    #set ($nokplama = $dataPemohon.noKpLama)
    ##set ($umur = $dataPemohon.umurPemohon)
    ##set ($jantina = $dataPemohon.jantinaPemohon)
    #set ($agama = $dataPemohon.jenisagama)
    #set ($warga = $dataPemohon.jeniswarga)
    #set ($add1 = $dataPemohon.alamat1)
    #set ($add2 = $dataPemohon.alamat2)
    #set ($add3 = $dataPemohon.alamat3)
    #set ($bandar = $dataPemohon.bandarpemohon)
    #set ($poskod = $dataPemohon.poskodpemohon)
    #set ($nohp = $dataPemohon.hppemohon)
    #set ($notel = $dataPemohon.telpemohon)
    #set ($nofax = $dataPemohon.faxpemohon)
    #set ($email = $dataPemohon.emelpemohon)
    #set ($catatan = $dataPemohon.catatan)
    #set ($taraf = $dataPemohon.tarafkptg)
    #set ($saudara = $dataPemohon.saudara)
    #set ($peguam = $dataPemohon.statuspeguam)
    #set ($negeriId = $dataPemohon.idnegeri)
    #set ($nokplain = $dataPemohon.noKpLain)
    #set ($jenisKpPemohon = $dataPemohon.jenisKp)
    #set ($idhub = $dataPemohon.idhubungan)
    #set ($addsurat1 = $dataPemohon.alamat1surat)
    #set ($addsurat2 = $dataPemohon.alamat2surat)
    #set ($addsurat3 = $dataPemohon.alamat3surat)
    #set ($poskodsurat = $dataPemohon.poskodsurat)
    #set ($flaghub = $dataPemohon.flaghubungan)
    #end
#end
<body>
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
  <table width="100%" border="0">
<tr>
<td>
 <input type="hidden" name="hitButt">
 <input type="hidden" name="mode">
 <input type="hidden" name="action">
 <input type="hidden" name="simpanStatus" value="$SimpanStatus">
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
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')">PERMOHONAN</li>
      #if ($new_data != "yes")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="HAview('2','0','0','0')" >HARTA ALIH</li>
	  #else
	  <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="goalert('0','0','0','0')">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="goalert('0','0','0','0')" >HARTA ALIH</li>
	  #end
      #set ($hidden = "")
      #if ($hidden == "0")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="NAview('3','0','0','0')" >NILAIAN HARTA</li>
      #end
      #if ($new_data != "yes")
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PengesahanView('4','0','0','0')">PENGESAHAN PERMOHONAN</li>
      #else
      <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="goalert('4','0','0','0')">PENGESAHAN PERMOHONAN</li>
      #end
    </ul>
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
        <div id="TabbedPanels2" class="TabbedPanelsContentVisible">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SimatiView('0','0','0','0')" >SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemohonView('0','1','0','0')">PEMOHON</li>
             #set ($hidden = "")
            	#if ($new_data != "yes")
		             #if ($flaghub == 1)
		            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="WarisView('0','2','0','0')">WARIS</li>
		            #elseif ($flaghub == 2)
		            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PentingView('0','3','0','0')">ORANG KEPENTINGAN</li>
		            #end
		      		#if ($hidden == "0")
		            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SaksiView('0','4','0','0')">SAKSI</li>
		            #end
                 
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemiutangView('0','3','0','0')">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PenghutangView('0','4','0','0')">PENGHUTANG</li>
          #else
          	 #if ($flaghub == 1)
		            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="WarisView('0','2','0','0')">WARIS</li>
		            #elseif ($flaghub == 2)
		            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PentingView('0','3','0','0')">ORANG KEPENTINGAN</li>
		            #end	      		
                    #if ($hidden == "0")
	            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="SaksiView('0','4','0','0')">SAKSI</li>
	            #end
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="goalert('0','0','0','0')">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="goalert('0','0','0','0')">PENGHUTANG</li>
          #end
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent">
              <div id="TabbedPanels3" class="TabbedPanelsContentVisible">
                <ul class="TabbedPanelsTabGroup">
                  <li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PemohonView('0','1','0','0')">PEMOHON</li>
                  #if ($peguam == "Y")
                  	<li class="TabbedPanelsTab style1 style3" tabindex="0" onClick="PeguamView('0','1','1','0')">PEGUAM</li>
                  #elseif ($peguam == "T")
                  	<li class="TabbedPanelsTab style1 style3" tabindex="0">PEGUAM</li>
                  #end
                </ul>
                <div class="TabbedPanelsContentGroup">
                  <div class="TabbedPanelsContent" >
                    <table width="97%" border="0" bgcolor="#FFFFFF">
                      <tr>
                        <td>                       
                        #if ($peguam == "Y")
                            #set ($check1 = "checked")
                        #elseif ($peguam == "T")
                            #set ($check2 = "checked")
                        #end
                            <fieldset>
                            <legend>MAKLUMAT PEMOHON</legend>
                              <table width="100%">
                              <tr>
                                <td width="50%" valign="top">
                                    <table width="100%" border="0">
                                      <tr>
                                        <td width="40%"><div align="right"><span class="style38">No KP Baru :</span></div></td>
                                        <td width="60%" class="style36">
                                          <input name="txtnoKpBaru1Pemohon" type="text" id="txtnoKpBaru1Pemohon" value="$!noKpBaru1" size="6" maxlength="6" $setmode onBlur="getAgeByIC(this,this.value,'txtUmurPemohon')" $setmode3/>-<input name="txtnoKpBaru2Pemohon" type="text" id="textfield9" value="$!noKpBaru2" size="1" maxlength="2" $setmode $setmode3/>-<input name="txtnoKpBaru3Pemohon" type="text" id="textfield10" value="$!noKpBaru3" size="4" maxlength="4" $setmode $setmode3/>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td width="30%"><div align="right"><span class="style38">No KP Lama :</span></div></td>
                                        <td width="70%" class="style36"><input name="txtNoKPLamaPemohon" type="text" value="$!nokplama" id="txtNoKPLamaPemohon" size="8" maxlength="8" style="text-transform:uppercase;" $setmode $setmode3/></td>
                                      </tr>
                                      <tr>
                                        <td valign="top"><div align="right"><span class="style38">Lain-lain KP :</span></div></td>
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
							            	<option value="0"/>Sila Pilih</option>
							            	#foreach($Listkp in $listkp)
							            	#set ($id = $Listkp.id)
							           		#set ($keterangan = $Listkp.keterangan)
								            	<option value="$id"/>$keterangan.toUpperCase()</option>
							            	#end
							           	#else
							           		<option value="0"/>Sila Pilih</option>
							            	#foreach($Listkp in $listkp)
							            	#set ($id = $Listkp.id)
							           		#set ($keterangan = $Listkp.keterangan)
								            	<option value="$id"/>$keterangan.toUpperCase()</option>
							            	#end
							           	#end
							            	</select>&nbsp;<input name="txtNoKPLainPemohon" type="text" id="textfield5"  value="$!nokplain" style="width: 97px; text-transform:uppercase;" maxlength="9" $setmode $setmode3/></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38"><span class="style42"><font color="#ff0000">*</font>&nbsp;</span>Nama Pemohon :</span></div></td>
                                        <td class="style36">
                                          <input name="txtNamaPemohonPemohon" type="text" id="textfield13" value="$!name" size="34" $setmode style="text-transform:uppercase;" $setmode3/>
                                       </td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38"><font color="#ff0000">*</font>&nbsp;Taraf Kepentingan :</span></div></td>
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
                                        <option value="0">Sila Pilih</option>
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
                                        <option value="0">Sila Pilih</option>
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
                                        <td><div align="right"><span class="style38">Talian Persaudaraan :</span></div></td>
                                        <td><select name="socTalianSaudara" style="width: 225px;" $setmode2 $setmode3>
							            #set ($idsaudara = "")
							        	#set ($kodsaudara = "")
							        	#set ($keterangansaudara = "")
							        #if ($saudara != "0")
							        	#set ($selectSaudara = "")
							            #foreach ($list in $ListPersaudaraan)
							            #set ($idsaudara = $list.id_Saudara)
							        	#set ($kodsaudara = $list.kod)
							        	#set ($keterangansaudara = $list.keterangan)
							        		#if ($idsaudara == $saudara)
							        			#set ($selectSaudara = "selected")
							            	<option value="$idsaudara" $selectSaudara>$keterangansaudara</option>
							            	#end
							            #end
							            <option value="0">Sila Pilih</option>
							            #foreach ($list in $ListPersaudaraan)
							            #set ($idsaudara = $list.id_Saudara)
							        	#set ($kodsaudara = $list.kod)
							        	#set ($keterangansaudara = $list.keterangan)
							            	<option value="$idsaudara">$keterangansaudara</option>
							            #end
							        #else
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
							        	<option value="0">Sila Pilih</option>
							            #foreach ($list in $ListPersaudaraan)
							            #set ($idsaudara = $list.id_Saudara)
							        	#set ($kodsaudara = $list.kod)
							        	#set ($keterangansaudara = $list.keterangan)
							            	<option value="$idsaudara">$idsaudara - $keterangansaudara</option>
							            #end
							        #end
							            </select></td>
						                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Jantina:</span></div></td>
                                        <td class="style36"><select name="socJantina" style="width: 225px;" $setmode2 $setmode3>
		                              #if ($jantina == "0" || $jantina == "")
                                            <option value="0">Sila Pilih</option>
		                                	<option value="2">PEREMPUAN</option>
		                                	<option value="1">LELAKI</option>
		                              #else
		                                	#if ($jantina == "2")
			                                <option value="2" selected>PEREMPUAN</option>
			                                <option value="1">LELAKI</option>
		                              		#elseif ($jantina == "1")
			                                <option value="2">PEREMPUAN</option>
			                                <option value="1" selected>LELAKI</option>
		                              		#else 
		                              		<option value="0">Sila Pilih</option>
			                                <option value="2">PEREMPUAN</option>
			                                <option value="1">LELAKI</option>
		                              		#end
		                              #end
		                                </select></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Agama :</span></div></td>
                                        <td class="style36"><select name="socAgama" style="width: 225px;" $setmode2 $setmode3>
		                                #if ($agama != "" && $agama != "0")
		                                	#if ($agama == "1")
		                                	<option value="1" selected>ISLAM</option>
		                                	<option value="2">BUKAN ISLAM</option>
		                                	#elseif ($agama == "2")
		                                	<option value="1">ISLAM</option>
		                                	<option value="2" selected>BUKAN ISLAM</option>
		                                	#end
		                                #else
		                                	<option value="0">SILA PILIH</option>
		                                	<option value="1">ISLAM</option>
		                                	<option value="2">BUKAN ISLAM</option>
		                                #end
		                                </select></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Warganegara :</span></div></td>
                                        <td class="style36"><select name="socWarga" style="width: 225px;" $setmode2 $setmode3>
		                                #if ($warga != "" && $warga != "0")
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
	   	 							#else
		   	 							<option value="0">SILA PILIH</option>
	                                	<option value="1">WARGANEGARA</option>
	                                	<option value="2">BUKAN WARGANEGARA</option>
	                                	<option value="3">PENDUDUK TETAP</option>
	   	 							#end
		                                </select></td>
		                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Umur :</span></div></td>
                                        <td class="style36"><label>
                                          <input name="txtUmurPemohon" type="text" id="txtUmurPemohon" value="$!umur"  size="3" maxlength="3" $readmode onKeyUp="javascript:validatePoskod(this,this.value)" $setmode $setmode3/>
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Diwakili Peguam :</span></div></td>
                                        <td class="style36"><input type="radio" name="sorPeguam" value="Y" $setmode2 $check1 $setmode3> Ya&nbsp;&nbsp;&nbsp;<input type="radio" name="sorPeguam" value="T" $setmode2 $check2 $setmode3> Tidak</td>
                                      </tr>
                                      <tr>
                                      <td width="30%" class="style38"><div align="right" class="style38"><font color="#ff0000">*</font>&nbsp;Alamat Tetap :</div></td>
                                      <td width="70%"><label>
                                        <input name="txtAlamatTerakhir1Pemohon" type="text" id="txtAlamatTerakhir" value="$!add1" size="34"  $readmode style="text-transform:uppercase;" $setmode $setmode3/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                      <td><label>
                                        <input name="txtAlamatTerakhir2Pemohon" type="text" id="txtAlamatTerakhir2"  value="$!add2" size="34" $readmode style="text-transform:uppercase;" $setmode $setmode3/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                      <td><input name="txtAlamatTerakhir3Pemohon" type="text" id="txtAlamatTerakhir3" value="$!add3" size="34" $readmode style="text-transform:uppercase;" $setmode $setmode3/></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38"><font color="#ff0000">*</font>&nbsp;Poskod :</div></td>
                                      <td><label>
                                        <input name="txtPoskodPemohon" type="text" id="txtPoskodPemohon " value="$!poskod" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()" $setmode $setmode3/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38"><font color="#ff0000">*</font>&nbsp;Negeri :</div></td>
                                      <td>$!selectNegeriTetap</td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38"><font color="#ff0000">*</font>&nbsp;Bandar :</div></td>
                                      <td><!--
                                        <input name="txtBandarPemohon" type="text" id="txtBandar " value="$bandar" size="34" $readmode style="text-transform:uppercase;" $setmode $setmode3/>-->$!selectBandarTetap
                                     </td>
                                    </tr>
                                    <tr>
                                    <td></td>
                                    <td><table>
                                    <tr>
                                    <td><input type="checkbox" name="chkAddress" value="1" onClick="getDuplicateAddress()" $checked $setmode2 $setmode3></td>
                                    <td><i>Sekiranya alamat tetap dan alamat surat menyurat adalah sama</i></td>
                                    </tr>
                                    </table>
                                    </td>
                                    <tr>
                                    </table></td>
                                <td valign="top"><table width="100%">
                                <tr>
                                      <td width="40%" class="style38"><div align="right" class="style38"><font color="#ff0000">*</font>&nbsp;Alamat Surat Menyurat :</div></td>
                                      <td width="60%"><label>
                                        <input name="txtAlamatTerakhir1PemohonSurat" type="text" id="txtAlamatTerakhir1PemohonSurat" value="$!addsurat1" size="34"  $readmode style="text-transform:uppercase;" $setmode $setmode3/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                      <td><label>
                                        <input name="txtAlamatTerakhir2PemohonSurat" type="text" id="txtAlamatTerakhir2PemohonSurat"  value="$!addsurat2" size="34" $readmode style="text-transform:uppercase;" $setmode $setmode3/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                      <td><input name="txtAlamatTerakhir3PemohonSurat" type="text" id="txtAlamatTerakhir3" value="$!addsurat3" size="34" $readmode style="text-transform:uppercase;" $setmode $setmode3/></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38"><font color="#ff0000">*</font>&nbsp;Poskod :</div></td>
                                      <td><label>
                                        <input name="txtPoskodPemohonSurat" type="text" id="txtPoskodPemohonSurat " value="$!poskodsurat" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()" $setmode $setmode3/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38"><font color="#ff0000">*</font>&nbsp;Negeri :</div></td>
                                      <td>$!selectNegeriSurat</td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38"><font color="#ff0000">*</font>&nbsp;Bandar :</div></td>
                                      <td><!--
                                        <input name="txtBandarPemohon" type="text" id="txtBandar " value="$bandar" size="34" $readmode style="text-transform:uppercase;" $setmode $setmode3/>-->$!selectBandarSurat
                                     </td>
                                    </tr>
                                     <tr>
                                          <td class="style38" valign="top"><div align="right">No Telefon:</div></td>
                                          <td><input name="txtNoTelefonPemohon" type="text" id="txtNoTelefonPemohon" value="$!notel" size="10" maxlength="9" $setmode onKeyUp="javascript:validatePoskod(this,this.value)" $setmode3/>&nbsp;<span class="style52">format : 0312345678</span></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right">No Telefon Bimbit :</div></td>
                                          <td><input name="txtNoTelefonBimbitPemohon" type="text" id="txtNoTelefonBimbitPemohon" value="$!nohp" size="10"  maxlength="9" onKeyUp="javascript:validatePoskod(this,this.value)" $setmode $setmode3/>&nbsp;<span class="style52">format : 01212345678</span></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right">No Faks :</div></td>
                                          <td><input name="txtNoFaksPemohon" type="text" id="txtNoFaksPemohon" value="$!nofax" size="10" maxlength="9" $setmode onKeyUp="javascript:validatePoskod(this,this.value)" $setmode3/>&nbsp;<span class="style52">format : 0312345678</span></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right">Email :</div></td>
                                          <td><input name="txtEmelPemohon" type="text" id="txtEmelPemohon" value="$!email" size="34" $setmode $setmode3/></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
                                          <td><textarea name="txtCatatanPemohon" cols="31" rows="3" id="txtCatatanPemohon" $setmode style="text-transform:uppercase;" $setmode3>$!catatan</textarea></td>
                                      </tr>
                                      
                                      </tr>
                                </table></td>
                              </tr>
                             <tr><td colspan="2" height="60px">
                                      <table>
                    <tr>
                    <td width="3%"><i>*</i></td>
                    <td width="97%"><i>Sila pastikan salah satu No Pengenalan diisi.</i></td>
                    </tr>
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan label bertanda <font color="#ff0000"> * </font> 
                      diisi.</i></td>
                    </tr>
                    <tr>
                    <td><i>*</i></td>
                    <td><i>Sila pastikan maklumat pada tab PEMOHON 
                      dan HARTA TAK ALIH diisikan dengan lengkap dan seterusnya membuat pengesahan 
                      penghantaran pada tab PENGESAHAN PERMOHONAN.</i></td>
                    </tr>
                    </table>
                                      </td>
                            </table>
                              </fieldset>
                          <p> </p></td>
                      </tr>
                      <tr>
                        <td align="center">
                         #if ($idStatus == "160")
                        		#if ($KemaskiniStatus == 1)
                                      <input type="button" name="cmdKemaskin2" id="cmdKemaskin2" value="Kemaskini"  onClick="kemaskini_pemohon('0','1','0','0')"/>
                                	  #else
                                      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onClick="SimpanPemohon('0','1','0','0')"/>
                                      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onClick="PemohonView('0','1','0','0')"/>
                                #end 
                          #end
                                      
                                      </td>
                      </tr>
                    </table>
                  </div>
                   <div class="TabbedPanelsContentVisible" ></div>
                </div>
              </div>
            </div>
            
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
            <div class="TabbedPanelsContentVisible"></div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContentVisible">
        <div id="TabbedPanels4" class="TabbedPanelsContentVisible">
         
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent"></div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContent"></div>
      <div class="TabbedPanelsContent"></div>
    </div>
  </div>    </td>
  </tr>
</table>
<script>
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
	document.${formName}.method="POST";
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
	document.${formName}.method="POST";
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
	document.${formName}.action.value.value="";
	document.${formName}.submit();
}

function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="view_nilai_harta";
	document.${formName}.hitButt.value="nilai_harta";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Penghutangview";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemiutangview";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Saksiview";
	document.${formName}.hitButt.value="Saksi";	
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pentingview";
	doAjaxCall${formName}("Penting");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	document.${formName}.hitButt.value="kembali_simati";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
	
}

function SimpanPemohon(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.${formName}.txtEmelPemohon.value;	
	
	if (document.${formName}.txtNamaPemohonPemohon.value == "" ){
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
		document.${formName}.mode.value="simpan_pemohon_data";
		doAjaxCall${formName}("Pemohon");
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;
		document.${formName}.action.value="";
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

function kemaskini_pemohon(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.mode.value = "kemaskini_pemohon";
	//document.${formName}.hitButt.value = "Pemohon";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	//document.${formName}.action.value = "";
	//document.${formName}.submit();
}

function PeguamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.mode.value = "Peguamview";
	doAjaxCall${formName}("Peguam");
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action.value = "";
	document.${formName}.submit();
}

function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){

	document.${formName}.mode.value = "Simati";
	document.${formName}.hitButt.value = "kembali_simati";
	document.${formName}.action.value = "";
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
    document.${formName}.tabIdatas.value = tabIdatas;
    document.${formName}.tabIdtengah.value = tabIdtengah;
    document.${formName}.tabIdbawah.value = tabIdbawah;	
	document.${formName}.tabIdtepi.value = tabIdtepi;	
}

function cancelwaris() {
	input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.${formName}.reset();
		document.${formName}.txtNoKPBaru1Waris.focus();
	}
}
<!-- PEMOHON -->

function BatalPemohon() {
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.action.value = "";
	document.${formName}.mode.value = "batal_pemohon";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.submit();
	}
}

<!-- SIMATI -->
function kemaskini_simati() {
	document.${formName}.mode.value = "kemaskini_simati";
	document.${formName}.hitButt.value = "Simati";
	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function SimpanSimati() {
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.mode.value = "simpan_simati";
	document.${formName}.hitButt.value = "Simati";
	document.${formName}.action.value = "";
	document.${formName}.submit();
    }
}

function BatalSimati() {
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.action.value = "";
	document.${formName}.mode.value = "batal_simati";
	document.${formName}.hitButt.value = "Simati";
	document.${formName}.submit();
	}
}

function getTaliSaudara() {
	document.${formName}.action.value = "";
	document.${formName}.mode.value = "talisaudara_pemohon";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.tabIdatas.value = "0";
    document.${formName}.tabIdtengah.value = "1";
    document.${formName}.tabIdbawah.value = "0";	
	document.${formName}.tabIdtepi.value = "0";
	document.${formName}.submit();
}

function validLength(){
	if (document.${formName}.txtPoskodPemohon.value!="" && document.${formName}.txtPoskodPemohon.value.length < 5){
		alert("Sila masukkan no poskod dengan lengkap.");
		txtPoskodPemohon.focus();
	}
}

function getDuplicateAddress(){
		if (document.${formName}.chkAddress.checked == true) {
				document.${formName}.chkAddress.checked = true;
				document.${formName}.mode.value="onChangeDuplicate";
				doAjaxCall${formName}("Pemohon");

		}else if (document.${formName}.chkAddress.checked == false) {
				document.${formName}.chkAddress.checked = false;
				document.${formName}.mode.value="onChangeNotDuplicate";
				doAjaxCall${formName}("Pemohon");
		}
}

function onChangeBandarTetap(){
	document.${formName}.mode.value = "onChangeBandarTetap";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.tabIdatas.value = 0;
	document.${formName}.tabIdtengah.value = 2;
	document.${formName}.tabIdbawah.value = 0;
	document.${formName}.tabIdtepi.value = 0;
	//document.${formName}.submit();
}
</script>

<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>



</body>
