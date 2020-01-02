

#set ($IdPemohon = "")
#set ($flag = "")
#set ($idhub = "")


	#foreach ($dataPemohon in $ListDataPemohon)		
		#set ($IdPemohon = $dataPemohon.idPemohon)
		#if($onchange == "no")
			#set ($noKpBaru1 = $dataPemohon.noKpBaruPemohon1)
			#set ($noKpBaru2 = $dataPemohon.noKpBaruPemohon2)
			#set ($noKpBaru3 = $dataPemohon.noKpBaruPemohon3)
			#set ($name = $dataPemohon.namaPemohon)
			#set ($nokplama = $dataPemohon.noKpLama)
			#set ($umur = $dataPemohon.umurPemohon)
			#set ($jantina = $dataPemohon.jantinaPemohon)
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
			#set ($nokplain = $dataPemohon.noKpLain)
			#set ($jenisKpPemohon = $dataPemohon.jenisKp)
			#set ($flaghub = $dataPemohon.flaghubungan)
			#set ($idhub = $dataPemohon.idhubungan)
			#set ($add1surat = $dataPemohon.alamat1surat)
			#set ($add2surat = $dataPemohon.alamat2surat)
			#set ($add3surat = $dataPemohon.alamat3surat)
			#set ($bandarsurat = $dataPemohon.bandarsurat)
			#set ($poskodsurat = $dataPemohon.poskodsurat)
		#end
	#end


<style type="text/css">
<!--
.style2 {font-size: 10px}
.style3 {font-style: italic}
-->
</style>
<body onLoad="submitForm()">
<input name="peguam" type="hidden" value="$peguam">
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
<fieldset>
<legend>Pembahagian Pusaka Kecil</legend>
<fieldset>
<legend>Maklumat untuk Borang A</legend>
  <table width="100%" border="0">
<tr>
<td>
 <input type="hidden" name="hitButt">
 <input type="hidden" name="mode">
 <input type="hidden" name="action">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="simpanStatus" value="$SimpanStatus">
  <input type="hidden" name="idPemohon" value="$IdPemohon" />
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/> 
  <input name="flaghub" type="hidden" id="flaghub" value="$flaghub"/> 
  <input name="hideTabPengesahan " type="hidden" value="$hideTabPengesahan ">
<input name="check1" type="hidden" value="$check1">
<input name="check2" type="hidden" value="$check2">
<input name="check3" type="hidden" value="$check3">
</td>
</tr>
  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab" onClick="SimatiView('0','0','0','0')" tabindex="0">PERMOHONAN</li>
      #if ($new_data != "yes")

      <li class="TabbedPanelsTab" onClick="HtaamView('1','0','0','0')" tabindex="0">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab" onClick="HAview('2','0','0','0')" tabindex="0" >HARTA ALIH</li>
	  #else	  
	  <li class="TabbedPanelsTab" onClick="HtaamView('1','0','0','0')" tabindex="0">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab" onClick="HAview('2','0','0','0')" tabindex="0">HARTA ALIH</li>
	  #end
      #set ($hidden = "")
      #if ($hidden == "0")

	  <li class="TabbedPanelsTab" onClick="NAview('3','0','0','0')" tabindex="0" >NILAIAN HARTA</li>
      #end
      #if($hideTabPengesahan != "hide")
      #if ($new_data != "yes")

      <li class="TabbedPanelsTab" onClick="PengesahanView('4','0','0','0')" tabindex="0">PENGESAHAN PERMOHONAN</li>
      #else      
      <li class="TabbedPanelsTab" onClick="goalert('4','0','0','0')" tabindex="0">PENGESAHAN PERMOHONAN</li>
      #end
      #end
    </ul>
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
        <div id="TabbedPanels2" class="TabbedPanelsContentVisible">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab" onClick="SimatiView('0','0','0','0')" tabindex="0" >SIMATI</li>
            <li class="TabbedPanelsTab" onClick="PemohonView('0','1','0','0')" tabindex="0">PEMOHON</li>
             #set ($hidden = "")
            	#if ($new_data != "yes")
		             

             <li class="TabbedPanelsTab" onClick="WarisView('0','2','0','0')" tabindex="0">WARIS</li>
	           	            
		            <li class="TabbedPanelsTab" onClick="PentingView('0','3','0','0')" tabindex="0">ORANG KEPENTINGAN</li>
	           
		      		#if ($hidden == "0")

		            <li class="TabbedPanelsTab" onClick="SaksiView('0','4','0','0')" tabindex="0">SAKSI</li>
	            #end                    
	            <li class="TabbedPanelsTab" onClick="PemiutangView('0','5','0','0')" tabindex="0">PEMIUTANG</li>
            <li class="TabbedPanelsTab" onClick="PenghutangView('0','6','0','0')" tabindex="0">PENGHUTANG</li>
            #else
                    
            <li class="TabbedPanelsTab" onClick="WarisView('0','2','0','0')" tabindex="0">WARIS</li>
	            	            
		            <li class="TabbedPanelsTab" onClick="PentingView('0','3','0','0')" tabindex="0">ORANG KEPENTINGAN</li>
	              
	      		#if ($hidden == "0")

	            <li class="TabbedPanelsTab" onClick="SaksiView('0','4','0','0')" tabindex="0">SAKSI</li>
                #end                    
                <li class="TabbedPanelsTab" onClick="PemiutangView('0','5','0','0')" tabindex="0">PEMIUTANG</li>
            <li class="TabbedPanelsTab" onClick="PenghutangView('0','6','0','0')" tabindex="0">PENGHUTANG</li>
          #end
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent"></div>
            <div class="TabbedPanelsContent">
              <div id="TabbedPanels3" class="TabbedPanelsContentVisible">
                <ul class="TabbedPanelsTabGroup">
                  <li class="TabbedPanelsTab" onClick="PemohonView('0','1','0','0')" tabindex="0">PEMOHON</li>
                  #if ($peguam == "Y")
                  	#set ($checkPeguam = "checked")
                  <li class="TabbedPanelsTab" onClick="PeguamView('0','1','1','0')" tabindex="0">PEGUAM</li>
                  #end
                </ul>
                <div class="TabbedPanelsContentGroup">
                  <div class="TabbedPanelsContent" >
                    <table width="100%" border="0">
                      <tr>
                        <td><p></p>  
                       
                                             
                       <!-- #if ($peguam == "Y")
                            #set ($check1 = "checked")
                        #elseif ($peguam == "T")
                            #set ($check2 = "checked")
                        #else
                            #set ($check1 = "")
                            #set ($check2 = "")
                        #end-->
                            <fieldset>
                            <legend>MAKLUMAT PEMOHON</legend>
                              <table width="100%">
                              <tr>
                                <td width="50%" valign="top">
                                    <table width="100%" border="0">
                                      <tr>
                                        <td width="30%"><div align="right"><span class="style38">MyID Baru :</span></div></td>
                                        <td width="70%" class="style36"><label>
                                          <input name="txtnoKpBaru1Pemohon" type="text" id="txtnoKpBaru1Pemohon" value="$!noKpBaru1" size="6" maxlength="6" $readonly onKeyUp="javascript:validateIC(event,this,this.value,'textfield9')" onBlur="getAgeByIC(this,this.value,'txtnoKpBaru2Pemohon')" $setmode2 />-<input name="txtnoKpBaru2Pemohon" type="text" id="textfield9" value="$!noKpBaru2" size="1" onKeyUp="javascript:validateIC(event,this,this.value,'textfield10')" maxlength="2" $readonly $setmode2 />-<input name="txtnoKpBaru3Pemohon" type="text" id="textfield10" value="$!noKpBaru3" size="4" maxlength="4" $readonly $setmode2 />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td width="30%"><div align="right"><span class="style38">MyID Lama :</span></div></td>
                                        <td width="70%" class="style36"><input name="txtNoKPLamaPemohon" type="text" value="$!nokplama" id="txtNoKPLamaPemohon" size="8" maxlength="8" style="text-transform:uppercase;" $setmode $readonly/></td>
                                      </tr>
                                      <tr>
                                        <td ><div align="right"><span class="style38">Lain-lain MyID :</span></div></td>
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
                                        <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Nama Pemohon :</span></div></td>
                                        <td class="style36">
                                          <input name="txtNamaPemohonPemohon" type="text" id="txtNamaPemohonPemohon" value="$!name" size="34" $setmode style="text-transform:uppercase;" $readonly/>                                       </td>
                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Jantina :</span></div></td>
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
                                        <td><div align="right"><span class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Taraf Kepentingan :</span></div></td>
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
                                      <tr >
                                        <td><div align="right"><span class="style38">Talian Persaudaraan :</span></div></td>
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
                                        <td><div align="right"><span class="style38">Agama :</span></div></td>
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
                                        <td><div align="right"><span class="style38">Warganegara :</span></div></td>
                                        <td class="style36"><select name="socWarga" style="width: 225px;" $setmode2 $setmode3>
		                            #if ($warga == "" || $warga == "0")
		   	 								<option value="0">SILA PILIH</option>
	                                	<option value="1">WARGANEGARA</option>
	                                	<option value="2">BUKAN WARGANEGARA</option>
	                                	<option value="3">PEMASTAUTIN TETAP</option>
	   	 							#else
                                        #if ($warga == "1")
		                                	<option value="1" SELECTED>WARGANEGARA</option>
		                                	<option value="2">BUKAN WARGANEGARA</option>
		                                	<option value="3">PEMASTAUTIN TETAP</option>
		                                	#elseif ($warga == "2")
		                                	<option value="1">WARGANEGARA</option>
		                                	<option value="2" SELECTED>BUKAN WARGANEGARA</option>
		                                	<option value="3">PEMASTAUTIN TETAP</option>
		                                	#elseif ($warga == "3")
		                                	<option value="1">WARGANEGARA</option>
		                                	<option value="2">BUKAN WARGANEGARA</option>
		                                	<option value="3" SELECTED>PEMASTAUTIN TETAP</option>
		                                	#end
		   	 							
	   	 							#end
		                                </select></td>
		                                      </tr>
                                      <tr>
                                        <td><div align="right"><span class="style38">Umur :</span></div></td>
                                        <td class="style36"><label>
                                          <input name="txtUmurPemohon" type="text" id="txtUmurPemohon" value="$!umur"  size="3" maxlength="3" $readmode onKeyUp="javascript:validatePoskod(this,this.value)" $setmode $readonly/>
                                        </label></td>
                                      </tr>
                                     
									  <tr>
                                      <td width="30%" class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Alamat Tetap :</div></td>
                                      <td width="70%"><label>
                                        <input name="txtAlamatTerakhir1Pemohon" type="text" id="txtAlamatTerakhir" value="$!add1" size="34"  style="text-transform:uppercase;" $setmode $readonly/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                      <td><label>
                                        <input name="txtAlamatTerakhir2Pemohon" type="text" id="txtAlamatTerakhir2"  value="$!add2" size="34"  style="text-transform:uppercase;" $setmode $readonly/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                      <td><input name="txtAlamatTerakhir3Pemohon" type="text" id="txtAlamatTerakhir3" value="$!add3" size="34" style="text-transform:uppercase;" $setmode $readonly/></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Poskod :</div></td>
                                      <td><label>
                                      <input name="txtPoskodPemohon" type="text" id="txtPoskodPemohon " value="$!poskod" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()" $setmode $readonly/>
                                      </label></td>
                                    </tr>
                                    <tr>
                                      <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Negeri :</div></td>
                                      <td>$selectNegeriTetap </td>
                                    </tr>
									<tr>
                                      <td class="style38"><div align="right" class="style38">Bandar :</div></td>
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
									<tr>
									  <td class="style38"></td>
									  <td>&nbsp;</td>
									  </tr>
                                  </table></td>
                                <td><table width="100%">
                                      <tr> 
                                        <td width="40%" class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Alamat Surat Menyurat :</div></td>
                                        <td width="60%"><label> 
                                          <input name="txtAlamatSurat1Pemohon" type="text" id="txtAlamatSurat1Pemohon" value="$!add1surat" size="34"  style="text-transform:uppercase;" $setmode $readonly/>
                                          </label></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                        <td><label> 
                                          <input name="txtAlamatSurat2Pemohon" type="text" id="txtAlamatSurat2Pemohon"  value="$!add2surat" size="34"  style="text-transform:uppercase;" $setmode $readonly/>
                                          </label></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38"><div align="right"><span class="style3"></span></div></td>
                                        <td><input name="txtAlamatSurat3Pemohon" type="text" id="txtAlamatSurat3Pemohon" value="$!add3surat" size="34" style="text-transform:uppercase;" $setmode $readonly/></td>
                                      </tr>
                                      <tr> 
                                   <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Poskod
                                            :</div></td>
                                        <td><label> 
                                          <input name="txtPoskodSuratPemohon" type="text" id="txtPoskodSuratPemohon " value="$!poskodsurat" size="5" maxlength="5" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="validLength()" $setmode $readonly/>
                                          </label></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38"><div align="right" class="style38"><font class="mandatory" color="#FF0000">*&nbsp;</font>Negeri :</div></td>
                                        <td>$selectNegeriSurat</td>
                                      </tr>
									  <tr> 
                                        <td class="style38"><div align="right" class="style38">Bandar :</div></td>
                                        <td><label> 
                                          <!--<input name="txtBandarSuratPemohon" type="text" id="txtBandarSuratPemohon " value="$!bandarsurat" size="34" style="text-transform:uppercase;" $setmode/>-->
                                          </label>$selectBandarSurat</td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" ><div align="right">No Telefon :</div></td>
                                        <td><input name="txtNoTelefonPemohon" type="text" id="txtNoTelefonPemohon" value="$!notel" size="12" maxlength="11" $setmode onKeyUp="javascript:validatePoskod(this,this.value)" $readonly/>&nbsp;<span class="style52 style2"><em>format : 031234567</em></span></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" ><div align="right">No Telefon Bimbit :</div></td>
                                        <td><input name="txtNoTelefonBimbitPemohon" type="text" id="txtNoTelefonBimbitPemohon" value="$!nohp" size="12"  maxlength="10" onKeyUp="javascript:validatePoskod(this,this.value)" $setmode $readonly/>&nbsp;<span class="style52 style2"><em>format : 0121234567</em></span></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" ><div align="right">No Faks :</div></td>
                                        <td><input name="txtNoFaksPemohon" type="text" id="txtNoFaksPemohon" value="$!nofax" size="12" maxlength="12" $setmode onKeyUp="javascript:validatePoskod(this,this.value)" $readonly/></td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" ><div align="right">Emel :</div></td>
                                        <td><input name="txtEmelPemohon" type="text" id="txtEmelPemohon" value="$!email" size="34" $setmode $readonly/></td>
                                      </tr>
                                       <tr>
                                        <td><div align="right"><span class="style38">Diwakili Peguam :</span></div></td>
                                        <td class="style36">
                                        <input name="checkPeguam" type="checkbox" value="Y" $setmode3 $checkPeguam>
                                        <input name="peguam" type="hidden" value=""> 
                                        </td>
                                      </tr>
                                      <tr> 
                                        <td class="style38" valign="top"><div align="right" class="style38">Catatan :</div></td>
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
                            <table width="100%">
                            	<tr>
                                	<td>
                                    <i>
                        <font color=red style=font-size:10px>Perhatian</font>
                        <font style=font-size:10px>: Sila pastikan label bertanda</font>&nbsp;
                        <font color=red style=font-size:10px>*</font>&nbsp;
                        <font style=font-size:10px>diisi.</font>            
                        </i> 
                        <br>
                        <i>
                         <font style=font-size:10px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila pastikan salah satu MyID diisi.</font>&nbsp;
                         </i>
                         </td>
                         </tr>
                         </table>
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
</fieldset>
</fieldset
></body>
<script>
function goalert(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value = "Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.tabIdatas.value = tabIdatas;
	document.${formName}.tabIdtengah.value = tabIdtengah;
	document.${formName}.tabIdbawah.value = tabIdbawah;
	document.${formName}.tabIdtepi.value = tabIdtepi;
	document.${formName}.action = "";
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
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	//document.${formName}.hitButt.value="Pemohon";	
	doAjaxCall${formName}("Pemohon");
	document.${formName}.submit();
}

function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="POST";
	document.${formName}.mode.value="Simatiview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Simati");
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

function SaksiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Saksiview";
	document.${formName}.hitButt.value="Saksi";	
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action="";
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pentingview";
	document.${formName}.hitButt.value="Penting";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Penting");
	document.${formName}.submit();
}

function SimpanPemohon(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
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
		document.${formName}.tabIdatas.value=tabIdatas;
		document.${formName}.tabIdtengah.value=tabIdtengah;
		document.${formName}.tabIdbawah.value=tabIdbawah;
		document.${formName}.tabIdtepi.value=tabIdtepi;
		document.${formName}.action.value="";
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

function kemaskini_pemohon(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	//document.${formName}.method="post";
	document.${formName}.mode.value="kemaskini_pemohon";
	document.${formName}.hitButt.value="Pemohon";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Pemohon");
	//document.${formName}.submit();
}

function PeguamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Peguamview";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	doAjaxCall${formName}("Peguam");
	document.${formName}.submit();
}

function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	document.${formName}.hitButt.value="kembali_simati";
	document.${formName}.action="";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
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

<!-- SIMATI -->
function kemaskini_simati() {
	document.${formName}.method="post";
	document.${formName}.mode.value="kemaskini_simati";
	document.${formName}.hitButt.value="Simati";
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function SimpanSimati() {
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.method="post";
	document.${formName}.mode.value="simpan_simati";
	document.${formName}.hitButt.value="Simati";
	document.${formName}.action.value="";
	document.${formName}.submit();
    }
}

function BatalSimati() {
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="batal_simati";
	document.${formName}.hitButt.value="Simati";
	document.${formName}.submit();
	}
}

function getDaerahAlamatPemohon() {
	document.${formName}.method="post";
	document.${formName}.mode.value="getDaerah";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.tabIdatas.value="0";
    document.${formName}.tabIdtengah.value="1";
    document.${formName}.tabIdbawah.value="0";	
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
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
	document.${formName}.tabIdatas.value="0";
    document.${formName}.tabIdtengah.value="1";
    document.${formName}.tabIdbawah.value="0";	
	document.${formName}.tabIdtepi.value="0";
	//document.${formName}.hitButt.value="Pemohon";
	document.${formName}.action.value="";
	doAjaxCall${formName}("Pemohon");
	//doAjaxCall${formName}("onChangeBandarTetap");
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
/*function checkPeguam(){
	
	alert("try");
	
	if (document.${formName}.checkPeguam[0].checked == true) {
		document.${formName}.method="POST";
		document.${formName}.checkPeguam.checked = true;
		document.${formName}.peguam.value="Y";
		document.${formName}.submit();
  	}
	else if (document.${formName}.checkPeguam[0].checked == false) {
		document.${formName}.method="POST";
		document.${formName}.checkPeguam.checked = false;
		document.${formName}.peguam.value="T";
		document.${formName}.submit();
	}
}*/
</script>

<script type="text/javascript">

var TabbedPanels1=new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2=new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3=new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4=new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>




