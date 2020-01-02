
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}

.style3 {font-size: 12px}
.style36 {font-size: 12}
.style38 {font-size: 10px}
.style40 {color: #0000FF}
.style44 {
	font-size: 9px;
	font-style: italic;
	color: #FF0000;
}
.style49 {color: #FF0000}
.style50 {
	font-size: 9px;
	font-style: italic;
}
.style51 {color: #0000FF; font-size: 9px; }
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
.style42 {
	color: #0000FF;
}
-->
</style>

#if ($ambilKeterangan==1)
<body onload="ambilKeteranganPopup();" >
#set ($ambilKeterangan = 0)
#end
<!-- 
hidupMati = $hidupMati
berhak = $berhak
status_perkahwinan = $status_perkahwinan
cerai = $cerai
cerai_mati = $cerai_mati
jantinaAnak = $jantinaAnak
bapa=$bapa
masabapasimatimeninggal=$masabapasimatimeninggal  -->
#set($readmodeR = "readonly")
#set($readmode = "disabled")
<input type="hidden" value="$id_perbicaraan" name="id_perbicaraan">
<input type="hidden" value="$nofail" name="nofail">
<fieldset>
				<legend><strong>BICARA ONLINE</strong></legend>
<div id="TabbedPanels1" class="TabbedPanels">
    		<ul class="TabbedPanelsTabGroup">
            	<li class="TabbedPanelsTab" tabindex="0" onclick="setSelected(0);maklumatPerbicaraan('$id_permohonan')">MAKLUMAT PERBICARAAN</li>
            	<li class="TabbedPanelsTab" tabindex="1" onclick="setSelected(1);maklumatKehadiran('$id_permohonan')">KEHADIRAN</li>
            	<li class="TabbedPanelsTab" tabindex="2" onclick="setSelected(2);maklumatSimati('$id_permohonan')">PEMOHON</li>
            	<li class="TabbedPanelsTab" tabindex="3" onclick="setSelected(3);maklumatSimati('$id_permohonan')">SIMATI</li>
            	<li class="TabbedPanelsTab" tabindex="4" onclick="setSelected(4);maklumatSimati('$id_permohonan')">WARIS</li>
            	<li class="TabbedPanelsTab" tabindex="5" onclick="setSelected(5);maklumatSimati('$id_permohonan')">HARTA</li>
            	<li class="TabbedPanelsTab" tabindex="6" onclick="setSelected(6);maklumatSimati('$id_permohonan')">HUTANG</li>
            	<li class="TabbedPanelsTab" tabindex="7" onclick="setSelected(7);maklumatSimati('$id_permohonan')">SIASATAN WARIS</li>
            	<!--  <li class="TabbedPanelsTab" tabindex="2" onclick="setSelected(2);maklumatPemohon('$id_permohonan')">PEMOHON</li>
            	<li class="TabbedPanelsTab" tabindex="3" onclick="setSelected(3);maklumatWaris('$id_permohonan')">WARIS</li>
            	<li class="TabbedPanelsTab" tabindex="4" onclick="setSelected(4)">ORANG BERKEPENTINGAN</li>
            	<li class="TabbedPanelsTab" tabindex="5" onclick="setSelected(5)">PENGHUTANG</li>
            	<li class="TabbedPanelsTab" tabindex="6" onclick="setSelected(6)">HARTA TAK ALIH</li>
            	<li class="TabbedPanelsTab" tabindex="7" onclick="setSelected(7)">HARTA ALIH</li> -->
         	 </ul>
          <div class="TabbedPanelsContentGroup"> 
           
            <div class="TabbedPanelsContent">
            
             	<fieldset>
				<legend><strong>MAKLUMAT PERBICARAAN</strong></legend>
				<table>
				<tr>
					<td>No. Fail
					</td>
					<td>:</td>
					<td>$nofail
					</td>
				</tr>
				<tr>
					<td>Tempat
					</td>
					<td>:</td>
					<td>$!tempat_bicara<br/>
					$!alamat_bicara1<br/>
					$!alamat_bicara2<br/>
					$!alamat_bicara3<br/>
					$!poskod
					</td>
				</tr>
				<tr>
					<td>Tarikh
					</td>
					<td>:</td>
					<td>$tarikh_bicara
					</td>
				</tr>
				<tr>
					<td>Nama Pegawai
					</td>
					<td>:</td>
					<td>$!peg_pengendali
					</td>
				</tr>
				</table></fieldset>

								</div>
								
						<div class="TabbedPanelsContent">
          	#foreach($listmati in $listSimati)
          		#set($namaSimati = $listmati.namaSimati)
          		#set($noKpBaru2 = $listmati.noKpBaru2)
          		#set($noKpBaru1 = $listmati.noKpBaru1)
          		#set($noKpBaru3 = $listmati.noKpBaru3)
          		#set($namaLain = $listmati.namaLain)
          		#set($tempatMati = $listmati.tempatMati)
          		#set($tarikhMati = $listmati.tarikhMati)
          		#set($sebabMati = $listmati.sebabMati)
          		#set($tempatMati = $listmati.tempatMati)
          		#set($noKpLama = $listmati.noKpLama)
          		
          		
          		 
          	#end
          		<input type="hidden" name="questioner" value="$questioner"> 
              	<fieldset>
              	<legend>SENARAI KEHADIRAN</legend>
              	<input type="button" name="tambah" value="Tambah" onclick="javascript:tambahKehadiran($id_permohonan)">
              	<table width="60%" border="0" cellspacing="2" cellpadding="2">
              	<tr class="table_header">
              	<td width="5%" align="center">Bil.
              	</td>
              	<td>Nama <input type="hidden" name="namaPenanya" value="$namaHadir">
              	</td>
              	<td width="35%" align="center">Hubungan
              	</td>
              	<td width="15%" align="center">Kehadiran
              	</td>
              	<td width="25%" align="center">Keterangan
              	</td>
              	
              	</tr>
              	#set($nohadir=1)
          		#foreach($namaPemohon in $listPemohon)
          		#set($namaPemohon = $namaPemohon.namaPemohon)
          		<tr>
              			<td align="center">$nohadir
              			</td>
              			<td>$namaPemohon
              			</td>
              			<td align="center">Pemohon
              			</td>
              			<td align="center"> <input type="checkbox" name="pemohon" >
              			</td>
              			<td align="center"><a href="javascript:ambilKeterangan()">Ambil keterangan</a>
              			</td>
              		</tr>
          		
          		#end
          		#set($nohadir=2)
          		
          		#foreach($namaWaris in $listWaris)
          		#set($namaWaris = $namaWaris.nama_Ob)
          		<tr>
              			<td align="center">$nohadir
              			</td>
              			<td>$namaWaris
              			</td>
              			<td align="center">Waris
              			</td>
              			<td align="center"> <input type="checkbox" name="waris" >
              			</td>
              			<td align="center"><a href="javascript:ambilKeterangan()">Ambil keterangan</a>
              			</td>
              		</tr>
          		#set($nohadir=$nohadir+1)
          		#end
          		
              	
              	
              	
              	</table>
              	</fieldset>
              	</div>
								
								            
			<div class="TabbedPanelsContent">
            
             	<fieldset>
                            <legend id="maklumat_pemohon">MAKLUMAT PEMOHON</legend>
                              <table width="100%">
                              <tr>
                                <td width="50%" valign="top">
                                
                                #set($status_pemohon = $!listpemohon.status_pemohon)
                                
                               <input type="hidden" name="status_pemohon" value="$!status_pemohon" />
                                <input type="hidden" name="idPermohonan" value="$listpemohon.idPermohonan" />
                                    <input type="hidden" name="idPemohon" value="$listpemohon.idPemohon" />
                               
                                    <table width="100%" border="0">
                                      <span >
                                      <tr id="individu_kp1">
                                        <td width="1%">&nbsp;</td>
                                        <td width="28%"><div align="right" class="style38">
                                          <div align="left"><span class="style38">MyID Baru</span></div>
                                        </div></td>
                                        <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td width="70%" class="style36">
                                        
                                          
                                           <input name="txtnoKpBaru1Pemohon" type="text" id="txtnoKpBaru1Pemohon" style="width: 50px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtnoKpBaru2Pemohon')" value="$listpemohon.noKpBaru1" size="7" maxlength="6"  onBlur="getAgeByIC(this,this.value,'txtUmurPemohon');getDOB(this.value)" $readmodeR class="$readmode" />
                                     - 
                                     <input name="txtnoKpBaru2Pemohon" type="text" id="txtnoKpBaru2Pemohon" style="width: 20px;" onKeyUp="javascript:validateIC(event,this,this.value,'txtnoKpBaru3Pemohon')" value="$listpemohon.noKpBaru2" size="1" maxlength="2" $readmodeR class="$readmode" />
                                     -
                                     <input name="txtnoKpBaru3Pemohon" type="text" id="txtnoKpBaru3Pemohon"  style="width: 40px;" onkeyup="javascript:validateIC(event,this,this.value,'txtnoKpBaru3Pemohon')" onblur="jantinaic();getSaudara('txtNoKPLamaPemohon')"  value="$listpemohon.noKpBaru3" size="5" maxlength="4" $readmodeR class="$readmode" />     
                                     <input name="txdTarikhLahirPemohon" id="txdTarikhLahirPemohon" value="" type="hidden" />                                    
                                     
                                     #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
                                      #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style51" >  www.jpn.gov.my</a>
  #end       
  #end
                                </td>
                                      </tr>
                                      <tr id="individu_kp2">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">MyID Lama</span></div>
                                        </div></td>
                                        <td class="style36">:</td>
                                        <td class="style36"><label>
                                          <input name="txtNoKPLamaPemohon" type="text" id="txtNoKPLamaPemohon"  value="$listpemohon.noKpLama" size="12" maxlength="15" $readmodeR class="$readmode" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" />
                                        </label></td>
                                      </tr>
                                      
                                 
                                      <tr id="individu_kp3">
                                        <td >&nbsp;</td>
                                        <td ><div align="right" class="style38">
                                          <div align="left">Jenis MyID Lain</div>
                                        </div></td>
                                        <td width="1%" class="style36" ><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"> #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jenisKp=="5")
                                          #set($pkp="Tentera")
                                          
                                          #elseif($listpemohon.jenisKp=="6")
                                          #set($pkp="Polis")
                                          
                                          #elseif($listpemohon.jenisKp=="4")
                                          #set($pkp="Pasport")
                                          
                                            #elseif($listpemohon.jenisKp=="7")
                                          #set($pkp="Lain-lain")
                                          
                                          #elseif($listpemohon.jenisKp=="0")
                                          #set($pkp="")
                                          
                                          #else
                                          #set($pkp="")
                                          #end
                                          
                                          #if($pkp=="")
                                          <input name="textfield4" type="text" id="textfield"  size="12" $readmodeR class="$readmode"  onblur="this.value=this.value.toUpperCase()" />
                                          #else
                                          <input name="textfield4" type="text" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$pkp"  size="12" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socJenisKPLainPemohon" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$pkp"  size="15" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socJenisKPLainPemohon" id="select" class="mediumselect" style="text-transform:uppercase;" onchange="kplain(this.value)"  onblur="kplainX(this.value)" />
                                            
                                            
                                      
									
								   #if($listpemohon.jenisKp=="5")
	                                 
                                      
                                            
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                             <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                            
                                            
                                      
	                              
	                               #elseif($listpemohon.jenisKp=="6")
	                                
                                      
                                            
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                              <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                            
                                            
                                      
	                              
								   #elseif($listpemohon.jenisKp=="4")
	                               
                                      
                                            
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                              <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                                #elseif($listpemohon.jenisKp=="7")
	                               
                                      <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                            
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                              
                                             <option value="0" style="text-transform:uppercase;" onblur="uppercase()">-KOSONGKAN-</option>
                                            
                                            
                                      
	                               #else
	                                 
                                      
                                            
                                            <option value="0" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jenis KP</option>
                                            <option value="5" style="text-transform:uppercase;" onblur="uppercase()">Tentera</option>
                                            <option value="6" style="text-transform:uppercase;" onblur="uppercase()">Polis</option>
                                            <option value="4" style="text-transform:uppercase;" onblur="uppercase()">Pasport</option>
                                              <option value="7" style="text-transform:uppercase;" onblur="uppercase()">Lain-lain</option>
                                            
                                            
                                      
	                               #end
                                    
                                  
                                    
                                          
                                          </select>
                                          #end
                                          
                                          <label></label>                                        </td>
                                      </tr>
                                      
                                      
                                #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($listpemohon.jenisKp != 0 && $listpemohon.jenisKp != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
                              
                                      <tr id="individu_kp4">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">MyID Lain</span></div>
                                        </div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36">
                                        
                                        #if($readmode == "disabled")
                                          <input name="txtNoKPLainPemohon" type="text" id="textfield5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.noKpLain" size="20" maxlength="25"  $readmodeR class="$readmode"  />
                                        #else
                                       
                                      <input name="txtNoKPLainPemohon" type="text" id="textfield5" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.noKpLain" size="20" maxlength="25" $readmodekp />
                                        #end                                        </td>
                                      </tr>
                                      </span>
                                      
                                      <tr>
                                        <td valign="top">#if($readmode != "disabled" ) <span class="style38 style44">*</span>
                                            #end</td>
                                        <td><div align="right" class="style38">
                                          <div align="left">#if($readmode != "disabled" ) Nama Pemohon
                                            #else
                                            Nama Pemohon
                                            #end                                          </div>
                                        </div></td>
                                       <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label>
                                          <input name="txtNamaPemohonPemohon" type="text" id="textfield13" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.namaPemohon" size="45" maxlength="150" $readmodeR class="$readmode" />
                                        </label></td>
                                      </tr>
                                      
                                   <tr>
										<td>
											<span class="style38"></span>
										</td>
										<td>
											<div align="left" class="style38">Nama Lain</div>
										</td>
										<td class="style36" style="text-transform: uppercase;">:</td>
										<td class="style36" style="text-transform: uppercase;">
											<label> 
											
												<input name="txtNamaLainPemohon" type="text" id="txtNamaLainPemohon"
												style="text-transform: uppercase;" value="$listpemohon.namaLain" size="34" maxlength="30"
												$readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()" />
											
											</label>
										</td>
									</tr>
                                      
                                      
                                      <tr id="individu_jantina">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">Jantina</span></div>
                                        </div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label> 
                                        #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jantina=="1")
                                          #set($sexpemohon="Lelaki")
                                          
                                          
                                          #elseif($listpemohon.jantina=="2")
                                          #set($sexpemohon="Perempuan")
                                          
                                          #else
                                          #set($sexpemohon="")
                                          #end
                                          
                                          #if($sexpemohon=="")
                                          <input name="textfield" type="text" id="textfield2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" $readmodeR class="$readmode" />
                                          #else
                                          <input name="textfield" type="text" id="textfield2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$sexpemohon"  size="15" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socJantinaPemohon" type="hidden" id="textfield2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.jantina"  size="30" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socJantinaPemohon" id="socJantinaPemohon" class="mediumselect" style="text-transform:uppercase;" onblur="uppercase()"   onchange="getSaudara('socTalianPersaudaraanPemohon')" >
                                            
                                         
                                   #if($listpemohon.jantina=="1")
	                               
                                      
                                            
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                            
                                            
                                      
	                               #elseif($listpemohon.jantina=="2")
	                               
                                      
                                            
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                            
                                            
                                      
	                               #else
	                               
                                      
                                            
                                            <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Jantina</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Lelaki</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Perempuan</option>
                                            
                                            
                                      
	                               #end
                                     
                                    
                                    
                                          
                                          </select>
                                          #end </label></td>
                                      </tr>
                                      <tr id="individu_saudara">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">Talian Persaudaraan</span></div>
                                        </div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td>
                                        
                                          #if($listpemohon.idsaudara=="" || $listpemohon.idsaudara=="0" )
                                          
                                          #set($kodsaudara="")
                                          #else
                                          #foreach($listsau in $listsaudara)
                                           
                                          #if($listpemohon.idsaudara==$listsau.id_Saudara)
                                          
                                          #set($kodsaudara="$listsau.kod - $listsau.keterangan")
                                       
                                          #end    
                                          #end
                                          
                                          #end
                                          
                                          #if($readmode=="disabled")
                                          <input name="socTalianPersaudaraan" type="text" class="$readmode" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$kodsaudara"  size="45" maxlength="150" $readmodeR />
                                               <input name="socTalianPersaudaraanPemohon" type="hidden" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="textfield" value="$listpemohon.idsaudara"  size="34" $readmodeR class="$readmode" />
                                          #else
                                          
                                          
                                          #if($listpemohon.idsaudara!="")
                                          
                                          <select name="socTalianPersaudaraanPemohon" id="socTalianPersaudaraanPemohon" class="largeselect" style="text-transform:uppercase;" onblur="uppercase()">
                                          <option value="$listpemohon.idsaudara" style="text-transform:uppercase;" onblur="uppercase()">$kodsaudara</option>                                         
                                          
                                          #foreach($listsau in $listsaudara)
                                          #if($listpemohon.jantina=="1")
                                          #set($jan="01")
                                         
                                          #elseif($listpemohon.jantina=="2")
                                          #set($jan="02")
                                          #end                                  
                                          #if($listpemohon.idsaudara!=$listsau.id_Saudara)
                                          #if($listsau.jantina==$jan)
                                          <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>     
                                          #end
                                          
                                            #if($listsau.id_Saudara=="27" )
                                
	                               
                                              <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                              
                                   #end
                                          
                                          #end    
	                                      #end    
                                         </select>
                                         
                                          #end
                                          
                                           #if($listpemohon.idsaudara=="")
                                          <select name="socTalianPersaudaraanPemohon" id="socTalianPersaudaraanPemohon" class="largeselect" style="text-transform:uppercase;" onblur="uppercase()" onfocus="insertsaudara()">
                                          <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Talian Persaudaraan</option>
                                          
                                          #foreach($listsau in $listsaudara)
                                          
                                          #if($listpemohon.jantina=="1")
                                          #set($jan="01")
                                          
                                          #elseif($listpemohon.jantina=="2")
                                          #set($jan="02")
                                          
                                          #else
                                          
                                          #set($jan="")
                                        
                                          #end
                                          
                                          #if($jan!="")
                                          #if($listsau.jantina==$jan)
                                          <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>                                         
                                          #end
                                            #if($listsau.id_Saudara=="27" )
                                
	                               
                                              <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="text-transform:uppercase;">$listsau.kod - $listsau.keterangan</option>
                                              
                                   #end
                                          #else
                                          <option value="$listsau.id_Saudara" style="text-transform:uppercase;" onblur="uppercase()">$listsau.kod - $listsau.keterangan</option>                                 
                                          #end
                                          
                                            #end
                                          </select>
                                          
                                          #end
                                          
                                          
                                          #end                                          </td>
                                      </tr>
                                      <tr>
                                        <td valign="top">#if($readmode != "disabled" )#end</td>
                                        <td><div align="right" class="style38">
                                          <div align="left">#if($readmode != "disabled" ) Taraf Kepentingan
                                            #else 
                                            Taraf Kepentingan
                                            #end                                          </div>
                                        </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td>
                                          #if($listpemohon.idTarafkptg=="")                                          
                                          #set($tarafkePemohonan="")                                                          
                                          
                                          
                                          #else
                                          #foreach($listtar in $listtaraf)
                                          
                                          #if($listpemohon.idTarafkptg==$listtar.id_Tarafkptg)
                                          
                                          #set($tarafkePemohonan="$listtar.kod - $listtar.keterangan")
                                          #set($tarafkePemohonanid="$listtar.id_Tarafkptg")
                                          
                                          
                                          
                                          #end  
                                          
                                          
                                          
                                          #end
                                          #end
                                          
                                          #if($listpemohon.idTarafkptg!="" && $listpemohon.idTarafkptg!=0 && $listpemohon.idTarafkptg!="null" )
                                          #set($dahada="ada")
                                          #else
                                          #set($dahada="Xada")
                                          #end
                                          
                                          <!--
                                          ::::::: ID TARAF :$listpemohon.idTarafkptg   
                                          ::::::: ADA TARAF
                                          -->
                                          
                                        <!--  listPemohonOB size:::: $listPemohonOB.size() -->
                                          #if( $listPemohonOB.size()>0)
                                          #set($tmpp="ada")
                                          #else
                                          #set($tmpp="Xada")
                                          #end
                                        <!-- listPemohonOB size:::: --> <input name="adaob" type="hidden"  value="$tmpp" />
                                          
                                          
                                          
                                          <input name="adataraf" type="hidden"  value="$dahada" />
                                          #if($readmode=="disabled")
                                          <input name="textfield2" type="text" id="textfield3" style="text-transform:uppercase;" onblur="uppercase()" value="$tarafkePemohonan"  size="45" $readmodeR class="$readmode" />
                                          <input name="textfield2" type="hidden" id="textfield3" style="text-transform:uppercase;"  onblur="uppercase()" value="$listpemohon.idTarafkptg"  size="34" $readmodeR class="$readmode" />
                                          #else
                                          
                                          #if($listpemohon.idTarafkptg!="")
                                          <input type="hidden" name="socTarafKePemohonanpp2" value="$tarafkePemohonanid" />
                                           <input name="textfield2" type="text" id="textfield3" style="text-transform:uppercase;" onblur="uppercase()" value="$tarafkePemohonan"  size="45" readonly class="disabled" />
                                            <input type="hidden" name="socTarafKePemohonanPemohon" value="$tarafkePemohonanid" />
                                         
                                          #else
                                           <input name="textfield2" type="text" id="textfield3" style="text-transform:uppercase;" onblur="uppercase()" value=""  size="45" readonly class="disabled" />
                                            <input type="hidden" name="socTarafKePemohonanPemohon" value="$tarafkePemohonanid" />
                                          
                                          <!--
                                          <select name="socTarafKePemohonanPemohon" class="largeselect" style="text-transform:uppercase;" onblur="uppercase()" onchange="pilih_taraf()" >
                                            <option value="" style="text-transform:uppercase;" onblur="uppercase()">Sila Pilih Taraf Kepentingan</option>
                                            
                                            
                                        #foreach($listtar in $listtaraf)
                                        
                                            <option value="$listtar.id_Tarafkptg" style="text-transform:uppercase;" onblur="uppercase()">$listtar.kod - $listtar.keterangan</option>
                                            
                                        #end
                                       
                                          </select>
                                           -->
                                          #end
                                         
                                          #end </td>
                                      </tr>
                                      <tr id="individu_agama">
                                        <td>&nbsp;</td>
                                        <td><div align="right" class="style38">
                                          <div align="left"><span class="style38">Agama</span></div>
                                        </div></td>
                                         <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label> #if($readmode=="disabled")
                                          
                                          #if($listpemohon.jenisAgama=="1")
                                          
                                          #set($agp="Islam")
                                          
                                          #elseif($listpemohon.jenisAgama=="2")
                                          
                                          #set($agp="Bukan Islam")
                                          
                                          #else
                                          
                                          #set($agp="")
                                          
                                          #end
                                          
                                          #if($agp=="")
                                          <input name="socAgamaPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                                          #else
                                          <input name="socAgamaPe" type="text" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$agp"  size="34" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socAgamaPemohon" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.jenisAgama"  size="30" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socAgamaPemohon" id="select3" class="mediumselect" style="text-transform:uppercase;" onblur="uppercase()">
                                          
                                      
                                   #if($listpemohon.jenisAgama=="1")
	                               
	                               
                                      
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                            <option value="" style="text-transform:uppercase;" >Sila Pilih</option>
                                            
                                      
	                               #elseif($listpemohon.jenisAgama=="2")
	                               
                                      
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                            <option value="" style="text-transform:uppercase;" >Sila Pilih</option>
                                      
	                               #else
	                               
                                      		<option value="" style="text-transform:uppercase;" >Sila Pilih</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Islam</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Islam</option>
                                            
                                      
	                               #end
                                      
                                    
                                    
                                          </select>
                                          #end </label></td>
                                      </tr>
                                      <tr id="individu_warga">
                                        <td valign="top">&nbsp;</td>
                                        <td valign="top"><div align="right" class="style38">
                                          <div align="left"><span class="style38">Warganegara</span></div>
                                        </div></td>
                                         <td width="1%" class="style36" valign="top"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36" valign="top">#if($readmode=="disabled")
                                          
                                          #if($listpemohon.jenisWarga=="1")
                                          
                                          #set($wrp="Warganegara")
                                          
                                          #elseif($listpemohon.jenisWarga=="2")
                                          
                                          #set($wrp="Bukan Warganegara")
                                          
                                          #elseif($listpemohon.jenisWarga=="3")
                                          
                                          #set($wrp="Pemastautin Tetap")
                                          
                                          #else
                                          #set($wrp="")
                                          
                                          #end
                                          
                                          #if($wrp=="")
                                          <input name="socWarganegaraPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                                          #else
                                          <input name="socWarganegaraPe" type="text" id="textfield" value="$wrp" style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                                          #end
                                          <input name="socWarganegaraPemohon" type="hidden" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.jenisWarga"  size="30" $readmodeR class="$readmode" />
                                          #else
                                          <select name="socWarganegaraPemohon" id="select4" class="autoselect" style="text-transform:uppercase;" 
                                           onchange="alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')" onblur="uppercase();alamatwarga(this.value,'alamatwarga','tr_nama_warga','nama_warga')">
                                            
                                      
                                   #if($listpemohon.jenisWarga=="1")
	                               
                                      
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            
                                      
	                               #elseif($listpemohon.jenisWarga=="2")
	                               
                                      
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            
                                      
	                               #elseif($listpemohon.jenisWarga=="3")
	                               
                                      
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            
                                      
                                   #else
                                   
                                      
                                           
                                            <option value="1" style="text-transform:uppercase;" onblur="uppercase()">Warganegara</option>
                                            <option value="2" style="text-transform:uppercase;" onblur="uppercase()">Bukan Warganegara</option>
                                            <option value="3" style="text-transform:uppercase;" onblur="uppercase()">Pemastautin Tetap</option>
                                            
                                      
	                               #end
                                  
                                    
                                    
                                          </select>
                                         
                                          <div id="alamatwarga"></div>
                                          #end                                          
                                        
                                          </td>
                                      </tr>
                                      
                                
                                   
                                    <tr id="tr_nama_warga">
                                        <td valign="top" ></td>
                                        <td>
                                          <div align="left" class="style38">
                                          Negara                                          
                                        </div></td>
                                        <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36">
                                        <input type = 'text' id = 'nama_warga' name = 'nama_warga' size='30' maxlength='200' $readmodeR class="$readmode"  list = 'datalist'  value="$listpemohon.nama_warga"    />
                                        <datalist id = 'datalist'>#foreach($ja in $kenegaraan)<option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>#end
                                        </datalist>   
                                       </td>
                                      </tr>

                                      
                                      
                                      <tr id="individu_umur">
                                        <td valign="top" >#if($readmode != "disabled" ) <span class="style43">*</span>
                                            
                                            #end</td>
                                        <td><div align="right" class="style38">
                                          <div align="left">#if($readmode != "disabled" ) Umur
                                            
                                            #else
                                            Umur
                                            #end                                          </div>
                                        </div></td>
                                        <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td class="style36"><label>
                                         <input name="txtUmurPemohon" type="text" style="text-transform:uppercase;" onblur="Checkumur()" id="txtUmurPemohon" value="$listpemohon.umur" size="2" maxlength="3" $readmodeR class="$readmode" onkeyup="javascript:validateIC(this,this.value,'txtUmurPemohon')" />
                                        
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38 style38" valign="top">#if($readmode != "disabled" ) 
                                          <span class="style38 style44">
                                             *
                                          
                                          </span>
                                          #end
                                          </td>
                                        <td class="style38 style38"><div align="left">#if($readmode != "disabled" ) </div>
                                          <div align="right" class="style38 style44">
                                              <div align="left" class="style52">Alamat Tetap</div>
                                          </div>
                                          <div align="left">#else
                                            Alamat Tetap
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                        <input name="txtAlamatTerakhir1Pemohon" type="text" class="$readmode" id="txtAlamatTerakhir"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.alamat1" size="45" maxlength="100"  $readmodeR />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">&nbsp;</td>
                                        <td class="style38"><div align="left"><span class="style3"><span class="style38"></span></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><label>
                                        <input name="txtAlamatTerakhir2Pemohon" type="text" class="$readmode"id="txtAlamatTerakhir2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.alamat2" size="45" maxlength="100" $readmodeR />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">&nbsp;</td>
                                        <td class="style38"><div align="left"><span class="style3"><span class="style38"></span></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><input name="txtAlamatTerakhir3Pemohon" type="text" class="$readmode" id="txtAlamatTerakhir3" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.alamat3" size="45" maxlength="100" $readmodeR /></td>
                                      </tr>
                                      <tr>
                                        <td class="style38 style38" valign="top">#if($readmode != "disabled" )<span class="style44">*</span> #end</td>
                                        <td class="style38 style38"><div align="left">#if($readmode != "disabled" ) </div>
                                          <div align="right" class="style43">
                                              <div align="left" class="style52">Poskod</div>
                                          </div>
                                          <div align="left">#else
                                            Poskod
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td ><label>
                                        <input name="txtPoskodPemohon" type="text" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" id="txtPoskodPemohon" value="$listpemohon.poskod" size="5" maxlength="5" $readmodeR class="$readmode" onkeyup="javascript:validatePoskod(this,this.value)" />
                                        </label></td>
                                      </tr>
                                 
                                     <tr>
                                       <td class="style38 style38" valign="top" >#if($readmode != "disabled" ) <span class="style44">*</span> #end</td>
                                        <td class="style38 style38"><div align="left">#if($readmode != "disabled" ) </div>
                                          <div align="right" class="style43">
                                              <div align="left" class="style52">Negeri</div>
                                          </div>
                                          <div align="left">#else
                                            Negeri
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        #if($listpemohon.idnegeri=="" || $listpemohon.idnegeri=="0" )
                                        #set($kod="")
                                        
                                        #else
                                        
                                        #foreach($listneg in $listnegeri)      
                                        
                                        
                                        #if($listpemohon.idnegeri==$listneg.id_Negeri)
                                        
                                        #set($kod="$listneg.kod_Negeri - $listneg.nama_Negeri")
                                        
                                        
                                        #end
                                        #end
                                        
                                        #end
                                        <td>#if($readmode=="disabled")
                                          <input name="socNegeri" type="text" class="$readmode" id="textfield7" style="text-transform:uppercase;" onblur="uppercase()" value="$kod"  size="45" maxlength="100" $readmodeR />
#else                                     
                                        
                                        #if($listpemohon.idnegeri!="" && $listpemohon.idnegeri!=0)
                                        <select name="socNegeriPemohon" class="autoselect" id="socNegeriPemohon"  onchange="getBandarTetap('txtBandarPemohon')" >
                                          <option value="$listpemohon.idnegeri" style="text-transform:uppercase;" onblur="uppercase()">$kod</option>
                                          
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($listpemohon.idnegeri!=$listneg.id_Negeri)                                  
                                  
                       
                                          <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                          
                                     
                                  #end    
	                              #end
                                        
                                        </select>
#else
<select name="socNegeriPemohon" class="autoselect"  onchange="getBandarTetap('txtBandarPemohon')">
  <option value="0" >SILA PILIH NEGERI</option>
  
                                  #foreach($listneg in $listnegeri)
                   
  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
  
                                    
	                               #end
                                        
</select>
#end   
                                                                             
                                        #end </td>
                                      </tr>
                                      
                                      
                                      
                                      
                                      
                                                       
                                      <tr id="tr_mesej_pelbagainegara">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat)</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara' name = 'nama_pelbagainegara' size='30' maxlength='200' class="$readmode" $readmodeR list = 'datalist'  value="$listpemohon.nama_pelbagainegara"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    </td>
        </tr>
                                      
                                   
                                      
                                      
                                      
                                      #set($bandartetap = $listpemohon.bandartetap)
                                      
                                      <tr>
                                        <td class="style38 style38" valign="top" >#if($readmode != "disabled" )#end</td>
                                        <td class="style38 style38"><div align="left">#if($readmode != "disabled" ) </div>
                                          <div align="right" class="style43">
                                              <div align="left" class="style52">Bandar</div>
                                          </div>
                                          <div align="left">#else
                                            Bandar
                                            #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                        
                                              
                             
                                    #if($bandartetap == "" || $bandartetap=="0")
                             #set($kodbx="")
                             
                             #else
                             
                             #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($bandartetap==$listneg.id)                                      
                                      #set($kodbx="$listneg.kod - $listneg.nama")
                                                                      
                                      #end
                             #end
                             
                             #end
                                
                                        
                                        
                                        #if($readmode=="disabled")
                      <input name="socNegeri12" type="text" class="$readmode" id="textfield7"  style="text-transform:uppercase;" onblur="uppercase()" value="$kodbx"  size="45" maxlength="100" $readmodeR />        
                      #else                
                                        
                                        #foreach($listdaerah in $listBandarTetapbyNegeri)
            
            #if($bandartetap==$listdaerah.id)
            
            #set($listDaerahbyNegeriK=$listdaerah.kod)
            #set($listDaerahbyNegeriN=$listdaerah.nama)
            
            
            
            #end 
            #end
            
            
            #if($disabled=="disabled")
            #set($readmodedaerah="disabled")
            #end
            #if($bandartetap!="" && $bandartetap!="0" )
            <select name="txtBandarPemohon" id="txtBandarPemohon" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
              <option value="$bandartetap">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
              
              
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($bandartetap!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      
              
              <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
              
              
                      
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            </select>
#else
<select name="txtBandarPemohon" id="txtBandarPemohon" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarTetap()" >
  <option value="">Sila Pilih Bandar</option>
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            
</select>
#end
#end
 </label></td>
                                      </tr>
                                      
                                      
                        
                                    </table></td>
                                <td valign="top"><table width="100%">
#if($readmode != "disabled")
<tr>
  <td width="1%" class="style38">&nbsp;</td>
                                      <td width="28%" class="style38"><div align="left"></div></td>
                                      <td class="style36">&nbsp;</td>
                                      <td width="70%"><label>
                                    #if($check_copy == "on")
                                    #set($ch_copy = "checked")
                                    #else
                                    #set($ch_copy = "")
                                    #end
                                      
                                        <input type="checkbox" name="copy" id="copy" $ch_copy onclick="copycopyX('maklumat_pemohon')"  />
                                        <span class="style50" >Alamat surat menyurat adalah alamat tetap</span></label></td>
                                    </tr>

                                      #end
                                    
                                      <tr>
                                        <td class="style38" valign="top">#if($readmode != "disabled" ) <span class="style44">*</span> #end</td>
                                        <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                                        <div align="right" class="style38 style44">
                                              <div align="left" class="style52">Alamat Surat</div>
                                        </div>
                                          <div align="left">#else
                                            Alamat Surat
                                          #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                          <input name="txtAlamatTerakhir1PemohonSurat" type="text" class="$readmode" $readmodeR  id="txtAlamatTerakhir1PemohonSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.alamat1Surat" size="45" maxlength="100"   />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">&nbsp;</td>
                                        <td class="style38"><div align="left"><span class="style3"></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><label>
                                          <input name="txtAlamatTerakhir2PemohonSurat" type="text" class="$readmode" id="txtAlamatTerakhir2PemohonSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"  value="$listpemohon.alamat2Surat" size="45" maxlength="100"  $readmodeR />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">&nbsp;</td>
                                        <td class="style38"><div align="left"><span class="style3"></span></div></td>
                                        <td>&nbsp;</td>
                                        <td><input name="txtAlamatTerakhir3PemohonSurat" type="text" class="$readmode" id="txtAlamatTerakhir3PemohonSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$listpemohon.alamat3Surat" size="45" maxlength="100" $readmodeR /></td>
                                      </tr>
                                      <tr>
                                        <td class="style38">#if($readmode != "disabled" ) <span class="style44">*</span> #end</td>
                                        <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                                        <div align="right" class="style43">
                                              <div align="left" class="style52">Poskod</div>
                                        </div>
                                          <div align="left">#else
                                            Poskod
                                          #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td ><label>
                                          <input name="txtPoskodPemohonSurat" type="text" style="text-transform:uppercase;" onblur="uppercase()" id="txtPoskodPemohonSurat" value="$listpemohon.poskodSurat" size="5" maxlength="5" $readmodeR class="$readmode" onKeyUp="javascript:validatePoskod(this,this.value)" />
                                        </label></td>
                                      </tr>
                                      <tr>
                                        <td class="style38" valign="top">#if($readmode != "disabled" ) <span class="style44">*</span> #end</td>
                                        <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                                        <div align="right" class="style43">
                                              <div align="left" class="style52">Negeri</div>
                                        </div>
                                          <div align="left">#else
                                            Negeri
                                          #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        #if($listpemohon.idnegeriSurat=="" || $listpemohon.idnegeriSurat=="0" )
                                        #set($kod="")
                                        
                                        #else
                                        
                                        #foreach($listneg in $listnegeri)      
                                        
                                        
                                        #if($listpemohon.idnegeriSurat==$listneg.id_Negeri)
                                        
                                        #set($kod="$listneg.kod_Negeri - $listneg.nama_Negeri")
                                        
                                        
                                        #end
                                        #end
                                        
                                        #end
                                        <td> #if($readmode=="disabled")
                                          <input name="textfield5" type="text" class="$readmode" id="textfield6" style="text-transform:uppercase;" onblur="uppercase()" value="$kod"  size="45" maxlength="100" $readmodeR />
                                          #else   
                                          
                                          
                                          #if($listpemohon.idnegeriSurat!="" && $listpemohon.idnegeriSurat!=0)
                                          <select name="socNegeriPemohonSurat" class="autoselect" id="socNegeriPemohonSurat" onchange="getBandarSurat('txtBandarPemohonSurat')" >
                                            <option value="$listpemohon.idnegeriSurat" style="text-transform:uppercase;" onblur="uppercase()" >$kod</option>
                                            
                                            
                                            
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($listpemohon.idnegeriSurat!=$listneg.id_Negeri)
                                  
                                  
                       
                                            
                                            
                                            <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                            
                                            
                                            
                                     
                                  #end    
	                              #end
                                        
                                          
                                          
                                          </select>
                                          #else
                                          <select name="socNegeriPemohonSurat" id="socNegeriPemohonSurat" class="autoselect" onchange="getBandarSurat('txtBandarPemohonSurat')" >
                                            <option value="0" >SILA PILIH NEGERI</option>
                                            
                                            
                                            
                                  #foreach($listneg in $listnegeri)
                   
                                            
                                            
                                            <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onblur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                            
                                            
                                            
                                    
	                               #end
                                        
                                          
                                          
                                          </select>
                                          #end   
                                          
                                          #end </td>
                                      </tr>
                                      
                                      
                                      
                                      
                                      
                                      
                                      
                                      
                                      <tr id="tr_mesej_pelbagainegara_surat">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara_surat"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara_surat">
                                    <td valign="top" ></td>
                                    <td>Negara (Alamat Surat)</td>
                                    <td >:</td>
                                    <td >
                            <input type = 'text' id = 'nama_pelbagainegara_surat' name = 'nama_pelbagainegara_surat' size='30' maxlength='200' class="$readmode" $readmodeR list = 'datalist'  value="$listpemohon.nama_pelbagainegara_surat"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    </td>
        </tr>
        
                                      
                                      
                                      
                                      
                                      
                                      
                                      <tr>
                                        <td class="style38" valign="top">#if($readmode != "disabled" )  #end</td>
                                        <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                                        <div align="right" class="style43">
                                              <div align="left" class="style52">Bandar</div>
                                        </div>
                                          <div align="left">#else
                                            Bandar
                                          #end </div></td>
                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                       
                                        
                                         #set($bandarsurat = $listpemohon.bandarsurat)
                                     
                                          #if($bandarsurat == "" || $bandarsurat=="0")
                             #set($kodbs="")
                             
                             #else
                             
                             #foreach($listneg in $listBandarSuratbyNegeri)      
                                      
                                      
                                      #if($bandarsurat==$listneg.id)                                      
                                      #set($kodbs="$listneg.kod - $listneg.nama")
                                                                      
                                      #end
                             #end
                             
                             #end
                                   
                                   
                                   
                                    #if($readmode=="disabled" )
                      <input name="socBandarS" type="text" class="$readmode" id="socBandar"   style="text-transform:uppercase;" onblur="uppercase()" value="$kodbs"  size="45" maxlength="100" $readmodeR />                                        
                                        #else   
                                      
                                        
                                        #foreach($listdaerah in $listBandarSuratbyNegeri)
            
            #if($bandarsurat==$listdaerah.id)
            
            #set($listDaerahbyNegeriK=$listdaerah.kod)
            #set($listDaerahbyNegeriN=$listdaerah.nama)
            
            
            
            #end 
            #end
            
            
            #if($disabled=="disabled")
            #set($readmodedaerah="disabled")
            #end
            #if($bandarsurat!="" && $bandarsurat!="0" )
            <select name="txtBandarPemohonSurat" id="txtBandarPemohonSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
              <option value="$bandarsurat">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
              
              
              
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarSuratbyNegeri)
                                 
                                  #if($bandarsurat!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      
              
              
              <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
              
              
              
                      
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
            </select>
#else
<select name="txtBandarPemohonSurat" id="txtBandarPemohonSurat" class="autoselect" $readmode   style="text-transform:uppercase;" onblur="uppercase()" onclick="CheckBandarSurat()" >
  <option value="">Sila Pilih Bandar</option>
  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarSuratbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

</select>
#end 
#end
</label></td>
                                      </tr>
                                    
                                     <tr>
                                       <td class="style38" >&nbsp;</td>
                                          <td class="style38" ><div align="left">No Telefon</div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoTelefonPemohon" type="text" id="txtNoTelefonPemohon" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.noTel" size="14" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTelefonPemohon')" maxlength="14" $readmodeR class="$readmode" /></td>
                                        </tr>
                                        #if($readmode != "disabled" )
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"></div></td>
                                          <td class="style36">&nbsp;</td>
                                          <td valign="top"><span class="style50">cth: 031234567</span></td>
                                        </tr>
                                        #end
                                        <tr id="no_hp">
                                          <td class="style38" >&nbsp;</td>
                                       
                                          <td class="style38" ><div align="left">No Telefon Bimbit</div></td>
                                           <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoTelefonBimbitPemohon" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTelefonBimbitPemohon')" type="text" id="txtNoTelefonBimbitPemohon" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.noHp" size="14" maxlength="14" $readmodeR class="$readmode" /></td>
                                        </tr>
                                           #if($readmode != "disabled" )
                                        <tr id="no_hp_info">
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"></div></td>
                                          <td class="style36">&nbsp;</td>
                                          <td valign="top"><span class="style50">cth: 0121234567</span></td>
                                        </tr>
                                        #end
                                        <tr id="no_fax" >
                                          <td class="style38" >&nbsp;</td>
                                          <td class="style38" ><div align="left">No Faks</div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>
                                          <td><input name="txtNoFaksPemohon" type="text" id="txtNoFaksPemohon" style="text-transform:uppercase;" onblur="uppercase()" value="$listpemohon.noFax" size="14" maxlength="12" $readmodeR class="$readmode" onkeyup="javascript:validateIC(event,this,this.value,'txtNoFaksPemohon')" /></td>
                                        </tr>
                                        <tr>
                                          <td class="style38" >&nbsp;</td>
                                          <td class="style38" ><div align="left">Email</div></td>
                                          <td width="1%" class="style36"><div align="right"><span class="style38">:</span></div></td>                                        
                                          <td>
                                            
                                        <input name="txtEmelPemohon" type="text" id="txtEmelPemohon"  value="$listpemohon.emel" size="45" maxlength="100" $readmodeR class="$readmode" />                                        </td>
                                          </tr>
                                        #if($readmode != "disabled" )
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left"></div></td>
                                          <td valign="top">&nbsp;</td>
                                          <td valign="top" height="1"><span class="style50">cth: abc@email.com </span></td>
                                        </tr>
                                        #end
                                        
                                         #if($!skrin_online == "yes")
                                         <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="left">Diwakili Peguam</div></td>
                                          <td valign="top">:</td>
                                          <td valign="top" height="1"> 
                                          
                                           #if($listpemohon.status_peguam=="Y")
                                            #set($chq="checked")
                                            #else
                                            #set($chq="")
                                            #end
                                          
                                          <input name="status_peguam" type="checkbox" value="Y" $readmode $chq >
                                        <input name="peguam" type="hidden" value=""> </td>
                                        </tr>
                                        #else                                        
                                        <input name="status_peguam" type="hidden" value = "$listpemohon.status_peguam" >
                                        #end
                                       
                                        
                                        
                                        <tr>
                                          <td class="style38" valign="top">&nbsp;</td>
                                          <td class="style38" valign="top"><div align="right" class="style38">
                                            <div align="left">Catatan</div>
                                          </div></td>
                                          <td width="1%" class="style36" valign="top"><div align="right"><span class="style38">:</span></div></td>
                                          <td><textarea name="txtCatatanPemohon"  cols="31" rows="3" id="txtCatatanPemohon" $readmodeR class="$readmode" >$listpemohon.catatan</textarea></td>
                                      </tr>
                                </table></td>
                              </tr>
                            </table>
                              </fieldset>

								</div>	
					<div class="TabbedPanelsContent">			

<fieldset>
                    <legend>MAKLUMAT SIMATI</legend>
                    <table width="100%">
                      <tr>
                        <td width="50%">
                       
                            <table width="100%" border="0">
                             #if($flagForView == 'yes')
                             
                             <!--
                             
                             <tr> 
                                <td width="1%"><span class="style38"></span></td>
                                <td width="28%"><div align="left" class="style38">MyID Baru</div></td>
                                <td width="1%" class="style36" >:</td>
                                
                                <td width="70%" class="style36" >
                               
                                  
                             
                                     <input name="txtNoKPBaru1Simati" id="txtNoKPBaru1Simati" style="width: 50px;" type="text" value="$listmati.noKpBaru1" size="7" maxlength="6" readonly   class="disabled"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Simati')"  onblur="qryHowOld()" />
                        
                -
                
  <input name="txtNoKPBaru2Simati" id="txtNoKPBaru2Simati" style="width: 20px;" type="text" value="$listmati.noKpBaru2" size="3" maxlength="2" readonly   class="disabled"  onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Simati')"  />
 
                -
               
  <input name="txtNoKPBaru3Simati" id="txtNoKPBaru3Simati"  style="width: 40px;" type="text" value="$listmati.noKpBaru3" readonly   class="disabled"  size="5" maxlength="4"  onblur="jantinaic();" />
 
  
  <input type="hidden" name="txdTarikhLahirSimati" id="txdTarikhLahirSimati" value=""  />
  #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style51">  www.jpn.gov.my</a>
  #end  
  <div id="checkkp_result"> </div></td>
                              </tr>
                              <tr>
                                <td ><span class="style38"></span></td>
                                <td ><div align="left" class="style38">MyID Lama</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" ><label>
                                  <input name="txtNoKPLamaSimati" type="text" id="textfield4" style="text-transform:uppercase;" value="$listmati.noKpLama" size="10" maxlength="9" readonly   class="disabled"  onBlur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td ><span class="style38"></span></td>
                                <td ><div align="left" class="style38">Lain-lain KP</div></td>
                                <td class="style36">:</td>
                                <td class="style36"> 
                                
                                 #if($flagForView == 'yes')
                                  
                                  #if($listmati.jenisKp=="5")
                                  #set($jkp="Tentera")
                                  
                                  #elseif($listmati.jenisKp=="6")
                                  #set($jkp="Polis")
                                  
                                  #elseif($listmati.jenisKp=="4")
                                  #set($jkp="Pasport")
                                  
                                  #else
                                  #set($jkp="")
                                  #end
                                  
                                  #if($jkp=="")
                                  <input name="textfield4" type="text" id="textfield" value="" size="10" maxlength="9"  readonly   class="disabled" onBlur="this.value=this.value.toUpperCase()"        />
                                  #else
                                  <input name="textfield4"  type="text" id="textfield" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$jkp"  size="10" maxlength="9" readonly   class="disabled" />
                                  #end
                                  <input name="socJenisKPLainSimati" type="hidden" id="socJenisKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$listmati.jenisKp"  />
                                  #else
                                  <select name="socJenisKPLainSimati" id="socJenisKPLainSimati" style="width: 180px;" onChange="kplain(this.value)" onBlur="kplainX(this.value)" />
	 								   #if($listmati.jenisKp=="4")
	                                    <option value="4" selected>PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5" >TENTERA</option>
                                         <option value="0" >-KOSONGKAN-</option>
		                               #elseif($listmati.jenisKp=="5")
                                         <option value="5" selected>TENTERA</option>
										<option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
                                       <option value="0" >-KOSONGKAN-</option>
	                                  
	 								   #elseif($listmati.jenisKp=="6")
                                       <option value="6" selected>POLIS</option>
										<option value="4">PASPORT</option>
	                                    
	                                    <option value="5">TENTERA</option>
                                         <option value="0" >-KOSONGKAN-</option>
		                               #else
	                                    <option value="0">SILA PILIH JENIS KP</option>
	                                    <option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5">TENTERA</option>
		                               #end
                                  </select>
                                  #end
                                  <label></label></td>
                              </tr>
                              
                              #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($listmati.jenisKp != 0 && $listmati.jenisKp != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
                              
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">MyID Lain</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" >
                                #if($readmode == "disabled")
                                 <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="10" maxlength="9"  readonly   class="disabled" />
                                 #else
                               
                                <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="10" maxlength="9" readonly   class="disabled" />
                                #end</td>
                              </tr>
                             -->
                             
                             
                             #else
                             
                               #end
                             
                             
                             
                             <tr>
                                <td width="1%"><span class="style38"></span></td>
                                <td width="28%"><div align="left" class="style38">MyID Baru</div></td>
                                <td width="1%" class="style36" >:</td>
                                
                                <td width="70%" class="style36" >
                               
                           
                                     <input name="txtNoKPBaru1Simati" id="txtNoKPBaru1Simati" style="width: 50px;" type="text" value="$listmati.noKpBaru1" size="7" maxlength="6" $readmodeR class="$readmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru2Simati')" />
                         
                -
                
  <input name="txtNoKPBaru2Simati" id="txtNoKPBaru2Simati" style="width: 20px;" type="text" value="$listmati.noKpBaru2" size="3" maxlength="2" $readmodeR class="$readmode" onKeyUp="javascript:validateIC(event,this,this.value,'txtNoKPBaru3Simati')"  />
 
                -
               
  <input name="txtNoKPBaru3Simati" id="txtNoKPBaru3Simati"  style="width: 40px;" type="text" value="$listmati.noKpBaru3" $readmodeR class="$readmode" size="5" maxlength="4"  onblur="calculateTarikhLahirSimati();jantinaic();" />
  
  
  <input type="hidden" name="txdTarikhLahirSimati" id="txdTarikhLahirSimati" value=""  />
  #if($!skrin_online != "yes" && $!skrin_online_17 != "yes")
  #if($readmode != "disabled")
  
  <a href="http://www.jpn.gov.my" target="_blank" class="style51">  www.jpn.gov.my</a>
  #end 
  #end 
  <div id="checkkp_result"> </div></td>
                              </tr>
                              <tr>
                                <td ><span class="style38"></span></td>
                                <td ><div align="left" class="style38">MyID Lama</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" ><label>
                                  <input name="txtNoKPLamaSimati" type="text" id="textfield4" style="text-transform:uppercase;" value="$listmati.noKpLama" size="15" maxlength="15" $readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td ><span class="style38"></span></td>
                                <td ><div align="left" class="style38">Jenis MyID Lain</div></td>
                                <td class="style36">:</td>
                                <td class="style36"> #if($readmode=="disabled")
                                  
                                  #if($listmati.jenisKp=="5")
                                  #set($jkp="Tentera")
                                  
                                  #elseif($listmati.jenisKp=="6")
                                  #set($jkp="Polis")
                                  
                                  #elseif($listmati.jenisKp=="4")
                                  #set($jkp="Pasport")
                                  
                                   #elseif($listmati.jenisKp=="7")
                                  #set($jkp="Lain-lain")
                                  
                                  #else
                                  #set($jkp="")
                                  #end
                                  
                                  #if($jkp=="")
                                  <input name="textfield4" type="text" id="textfield" value="" size="10" maxlength="9"  $readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()"      $readmode  />
                                  #else
                                  <input name="textfield4"  type="text" id="textfield" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$jkp"  size="10" maxlength="9" $readmodeR class="$readmode" />
                                  #end
                                  <input name="socJenisKPLainSimati" type="hidden" id="textfield" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$jkp"  size="15" $readmodeR class="$readmode" />
                                  #else
                                  <select name="socJenisKPLainSimati" id="select" style="width: 180px;" onChange="kplain(this.value)" onBlur="kplainX(this.value)" />
	 								   #if($listmati.jenisKp=="4")
	                                    <option value="4" selected>PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5" >TENTERA</option>
                                          <option value="7" >LAIN-LAIN</option>
                                         <option value="0" >-KOSONGKAN-</option>
		                               #elseif($listmati.jenisKp=="5")
                                         <option value="5" selected>TENTERA</option>
										<option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
                                         <option value="7" >LAIN-LAIN</option>
                                       <option value="0" >-KOSONGKAN-</option>
	                                  
	 								   #elseif($listmati.jenisKp=="6")
                                       <option value="6" selected>POLIS</option>
										<option value="4">PASPORT</option>
	                                    
	                                    <option value="5">TENTERA</option>
                                         <option value="7" >LAIN-LAIN</option>
                                         <option value="0" >-KOSONGKAN-</option>
                                          #elseif($listmati.jenisKp=="7")
                                           <option value="7" >LAIN-LAIN</option>
                                       <option value="6" selected>POLIS</option>
										<option value="4">PASPORT</option>
	                                    
	                                    <option value="5">TENTERA</option>
                                        
                                         <option value="0" >-KOSONGKAN-</option>
		                               #else
	                                    <option value="0">SILA PILIH JENIS KP</option>
	                                    <option value="4">PASPORT</option>
	                                    <option value="6">POLIS</option>
	                                    <option value="5">TENTERA</option>
                                         <option value="7" >LAIN-LAIN</option>
		                               #end
                                  </select>
                                  #end
                                  <label></label></td>
                              </tr>
                              
                              #if($readmode=="disabled")
                               #set($readmodekp="disabled")
                              #end
                              
                              #if($readmode=="")
                             #if($listmati.jenisKp != 0 && $listmati.jenisKp != "")
                               #set($readmodekp="")
                              #else
                              #set($readmodekp="disabled")
                              #end
                               
                               
                              #end
                              
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">MyID Lain</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" >
                                #if($readmode == "disabled")
                                 <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="10" maxlength="25"  $readmodeR class="$readmode" />
                                 #else
                               
                                <input name="txtNoKPLainSimati" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" type="text" id="textfield5"  value="$listmati.noKpLain" size="10" maxlength="25" $readmodekp />
                                #end</td>
                              </tr>
                              
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Tarikh Lahir Simati</div></td>
                                <td class="style36" >:</td>
                                <td class="style36" >
                                
                                <input name="tarikhLahirSimati" type="text" id="tarikhLahirSimati" style="text-transform:uppercase;" value="$listmati.tarikhLahirSimati" size="15" maxlength="15" $readmodeR class="$readmode" onBlur="calculateUmurSimati();" />
                                #if($readmode!="disabled")
                                <a href="javascript:displayDatePicker('tarikhLahirSimati',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
                                </td>
                              </tr>
                             
                             
                           
                              
                              <tr>
                                <td valign="top"><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                                <td><div align="left" class="style38">#if($readmode == "disabled")Nama Simati                               #else
                                 Nama Simati
                                  #end
                                </div></td>
                                <td class="style36" >:</td>
                                <td class="style36" ><label>
                                  <input name="txtNamaSimati" type="text" id="textfield6" style="text-transform:uppercase;" value="$listmati.namaSimati" size="34" maxlength="200" $readmodeR class="$readmode"  onblur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Nama Lain</div></td>
                                <td class="style36" style="text-transform:uppercase;">:</td>
                                <td class="style36" style="text-transform:uppercase;"><label>
                                  <input name="txtNamaLainSimati" type="text" id="textfield" style="text-transform:uppercase;" value="$listmati.namaLain"  size="34" maxlength="30" $readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()" />
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Jantina</div></td>
                                <td class="style36">:</td>
                                <td class="style36"><label> #if($readmode=="disabled")
                                  
                                  #if($listmati.jantina=="1")
                                  #set($sex="LELAKI")
                                  
                                  
                                  #elseif($listmati.jantina=="2")
                                  #set($sex="PEREMPUAN")
                                  
                                  #else
                                  #set($sex="")
                                  #end
                                  
                                  #if($sex=="")
                                  <input name="socJantinaSi" type="text" id="textfield" value=""  size="10" maxlength="9" $readmodeR class="$readmode" />
                                  #else
                                  <input name="socJantinaSi" type="text" id="textfield" style="text-transform:uppercase;" value="$sex" $readmodeR class="$readmode" size="10" maxlength="9"  />
                                  #end
                                  <input name="socJantinaSimati" type="hidden" id="textfield" value="$listmati.jantina"  size="30" $readmodeR class="$readmode" />
                                  #else
                                 
                              
                                      
                                   #if($listmati.jantina=="1")
	                               
                                       <select name="socJantinaSimati" id="socJantinaSimati7" style="width: 180px;" >
                                    
                              
                                    <option value="1">LELAKI</option>
                                    <option value="2">PEREMPUAN</option>
                                </select>
                                      
	                               #elseif($listmati.jantina=="2")
	                                <select name="socJantinaSimati" id="socJantinaSimati8" style="width: 180px;" >
                                    
                                      
                              
                                    <option value="2">PEREMPUAN</option>
                                    <option value="1">LELAKI</option>
                                </select>
                                      
	                               #else
	                               
                                       <select name="socJantinaSimati" id="socJantinaSimati9" style="width: 180px;" >
                                    
                              
                                    <option value="">SILA PILIH JANTINA</option>
                                    <option value="1">LELAKI</option>
                                    <option value="2">PEREMPUAN</option>
                                </select>
                                      
	                               #end
                                     
                                    
                                    
                            
                                
                                  #end </label></td>
                              </tr>
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Agama</div></td>
                                <td class="style36">:</td>
                                <td class="style36"><label> #if($readmode=="disabled")
                                  
                                  #if($listmati.jenisAgama=="1")
                                  
                                  #set($ag="ISLAM")
                                  
                                  #elseif($listmati.jenisAgama=="2")
                                  
                                  #set($ag="BUKAN ISLAM")
                                  
                                  #else
                                  
                                  #set($ag="")
                                  
                                  #end
                                  
                                  #if($ag=="")
                                  <input name="socAgamaSi" type="text" id="textfield" value=""  size="34" $readmodeR class="$readmode" />
                                  #else
                                  <input name="socAgamaSi" type="text" id="textfield" style="text-transform:uppercase;" value="$ag"  size="34" $readmodeR class="$readmode" />
                                  #end
                                  <input name="socAgamaSimati" type="hidden" id="textfield" value="$listmati.jenisAgama"  size="30" $readmodeR class="$readmode" />
                                  #else
                                  <select name="socAgamaSimati" id="select3" style="width: 180px;" >
                                    
                              
                                      
                                   #if($listmati.jenisAgama=="1")
	                               
	                               
                                      
                              
                                    <option value="1" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">ISLAM</option>
                                    <option value="2" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">BUKAN ISLAM</option>
                                    
                              
                                      
	                               #elseif($listmati.jenisAgama=="2")
	                               
                                      
                              
                                    <option value="2" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">BUKAN ISLAM</option>
                                    <option value="1" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">ISLAM</option>
                                    
                              
                                      
	                               #else
	                               
                                      
                              
                                    
                                    <option value="1" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">ISLAM</option>
                                    <option value="2" style="text-transform:uppercase;" onBlur="text-transform:uppercase;">BUKAN ISLAM</option>
                                    
                              
                                      
	                               #end
                                      
                                    
                                    
                            
                                  </select>
                                  #end </label></td>
                              </tr>
                              <tr>
                                <td><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                                <td><div align="left" class="style38">Warganegara</div></td>
                                <td class="style36">:</td>
                                <td class="style36"><label> #if($readmode=="disabled")
                                  
                                  #if($listmati.jenisWarga=="1")
                                  
                                  #set($wr="Warganegara")
                                  
                                  #elseif($listmati.jenisWarga=="2")
                                  
                                  #set($wr="Bukan Warganegara")
                                  
                                  #elseif($listmati.jenisWarga=="3")
                                  
                                  #set($wr="Pemastautin Tetap")
                                  
                                  #else
                                  #set($wr="")
                                  
                                  #end
                                  
                                  #if($wr=="")
                                  <input name="socWarganegaraSi" type="text" style="text-transform:uppercase;" onBlur="uppercase()"  id="textfield" value=""  size="34" $readmodeR class="$readmode" />
                                  #else
                                  <input name="socWarganegaraSi" type="text" style="text-transform:uppercase;" onBlur="uppercase()"  id="textfield" value="$wr"  size="34" $readmodeR class="$readmode" />
                                  #end
                                  <input name="socWarganegaraSimati" style="text-transform:uppercase;" onBlur="uppercase()"  type="hidden" id="textfield" value="$wr"  size="30" $readmode $readmodeR class="$readmode" />
                                  #else
                                  <select name="socWarganegaraSimati" id="select4"  class="autoselect" >
                                    
                              
                                      
                                   #if($listmati.jenisWarga=="1")
	                               
                                      
                              
                                    <option value="1">WARGANEGARA</option>
                                    <option value="2">BUKAN WARGANEGARA</option>
                                    <option value="3">PENDUDUK TETAP</option>
                                   
                              
                                      
	                               #elseif($listmati.jenisWarga=="2")
	                               
                                      
                              
                                    <option value="2">BUKAN WARGANEGARA</option>
                                    <option value="1">WARGANEGARA</option>
                                    <option value="3">PENDUDUK TETAP</option>
                                   
                                    
                              
                                      
	                               #elseif($listmati.jenisWarga=="3")
	                               
                                      
                              
                                    <option value="3">PENDUDUK TETAP</option>
                                    <option value="1">WARGANEGARA</option>
                                    <option value="2">BUKAN WARGANEGARA</option>
                                  
                              
                                      
                                   #else
                                   
                                      
                              
                                 	
                                    <option value="1">WARGANEGARA</option>
                                    <option value="2">BUKAN WARGANEGARA</option>
                                    <option value="3">PENDUDUK TETAP</option>
                                    
                              
                                      
	                               #end
                                  
                                    
                                    
                            
                                  </select>
                                  #end </label></td>
                              </tr>
                              <tr>
                                <td valign="top"><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></span></td>
                                <td><div align="left" class="style38">Bukti Kematian</div></td>
                                <td>:</td>
                                <td>
                                
                    
                                
                                 #if($listmati.idBuktimati=="")
                                #set($bukti="") 
                                #end
                                
                                
                                #foreach($listbuk in $listbuktimati)  
                                                           
                                #if($listmati.idBuktimati==$listbuk.id_Buktimati)                                
                                #set($bukti="$listbuk.kod - $listbuk.keterangan")  
                                #end
                                
                                
                               
                                
                                
                                #end
                 
                                
                                #if($readmode=="disabled")  
                                
                                                              
                                
                                
                                  <input name="txtBuktiKematian" type="text" id="textfield" value="$bukti"  $readmodeR class="$readmode" size="34" onBlur="this.value=this.value.toUpperCase()"  />
                                  <input name="socBuktiKematianSimati" type="hidden" onBlur="this.value=this.value.toUpperCase()" id="textfield" value="$listmati.idbuktikematian"  size="30" $readmodeR class="$readmode" />
                                  
                                  #else
                                  
                                  
                                  #if($listmati.idBuktimati!="")
                                  
                                  
                                   <select name="socBuktiKematianSimati" class="autoselect" onChange="jenis_hutangU(this.value)" >
                                      <option value="$listmati.idBuktimati">$bukti</option>                         
                              
                                        
                                  #foreach($listbuk in $listbuktimati)                                 
                                  #if($listmati.idBuktimati!=$listbuk.id_Buktimati)
                                    <option value="$listbuk.id_Buktimati">$listbuk.kod -  $listbuk.keterangan</option>
                                  #end    
	                               #end
                                  
                                  </select>
                                  #else
                                  
                                  <select name="socBuktiKematianSimati" class="autoselect" onChange="jenis_hutangU(this.value)" >
                                    <option value="">SILA PILIH BUKTI MATI</option>
                                    
                            
                                    
                                  #foreach($listbuk in $listbuktimati)
                                 
                                 
	                               
                                      
                              
                                    <option value="$listbuk.id_Buktimati">$listbuk.keterangan</option>
                                    
                              
                                      
                                   
                                    
	                               #end
                                  
                                  
                                  
                                  
                                    
                            
                                  </select>
                                  #end
                                  
                                  #end </td>
                              </tr>
                  
                               
                                     #if($readmode=="disabled")                                  
                                     	#set($readmodesy="disabled")                                   
                                     #end
                                     
                                     #if($readmode!="disabled")                                    
	                                     #if($listmati.idBuktimati=="1" || $listmati.idBuktimati=="4")
	                                      	#set($readmodesy="")       
	                                     #else
	                                      	#set($readmodesy="disabled")
	                                     #end       
	                                     
	                                     #if($listmati.idBuktimati=="2")   
	                                      	#set($readmodeDateAkuan="")   
	                                     #else
	                                      	#set($readmodeDateAkuan="disabled")
	                                     #end                                                               
                                     #end
                          
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">No Sijil Mati / Permit Menguburkan</div></td>
                                <td class="style36" style="text-transform:uppercase;">:</td>
                                <td class="style36" style="text-transform:uppercase;"><label>
                              
                                #if($readmode == "disabled")
                                 <input name="txtNoSijilMatiSimati" onBlur="this.value=this.value.toUpperCase()" type="text" id="txtNoSijilMatiSimati" style="text-transform:uppercase;" value="$listmati.noSijilMati" size="10" maxlength="25" $readmodeR class="$readmode"/>
                                #else
                                  <input name="txtNoSijilMatiSimati" onBlur="this.value=this.value.toUpperCase()" type="text" id="txtNoSijilMatiSimati" style="text-transform:uppercase;" value="$listmati.noSijilMati" size="10" maxlength="10" $readmodesy />
                                #end
                                </label></td>
                              </tr>
                              
                              <tr>
                               <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Tarikh Surat Akuan</div></td>
                                <td class="style36" style="text-transform:uppercase;">:</td>
                                <td class="style36" style="text-transform:uppercase;"><label>
                              
                                #if($readmode=="disabled")
                                  <input name="txdTarikhSuratAkuan" type="text" id="textfield" value="$!listmati.txdTarikhSuratAkuan"  size="10" maxlength="25"   $readmodeR class="$readmode" />                             
                                #else
                                  <input name="txdTarikhSuratAkuan" type="text" id="txdTarikhSuratAkuan" value="$!listmati.tarikhSuratAkuan" size="10" maxlength="25"  $readmodeDateAkuan   onBlur="trans_dateAkuan(this.value);"  />
                                  <a href="javascript:displayDatePicker('txdTarikhSuratAkuan',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a> 
                                #end 
                                </label>
                              </tr>
                             
                              <tr>
                                <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                                <td ><div align="left" class="style38">
                                #if($readmode == "disabled") 
                                	Tarikh Mati                               
                                #else
                                	Tarikh Mati
                                #end
                                </div></td>
                                <td>:</td>
                                <td> 
                                	#if($readmode=="disabled")
	                                  	<input name="txdTarikhMati" type="text" id="textfield" value="$!listmati.tarikhMati"  size="10" maxlength="25"   $readmodeR class="$readmode" />
	                                    <input name="txdTarikhMati" type="hidden" id="textfield" value="$!listmati.tarikhMatidb"  size="9"   $readmodeR class="$readmode" />
                                  	#else
	                                  <!-- <input name="txdTarikhMatiSimati" type="text" id="txdTarikhMatiSimati" value="$!listmati.tarikhMati" size="10" maxlength="10"   $readmodeR class="$readmode"  onfocus="qryHowOld()" onBlur="trans_date(this.value);qryHowOld()"  /> -->
	                                  <input name="txdTarikhMatiSimati" type="text" id="txdTarikhMatiSimati" value="$!listmati.tarikhMati" size="10" maxlength="10"   $readmodeR class="$readmode"  onBlur="trans_date(this.value)"  /> <!-- addby aishah untuk onload terus umur dari tarikh mati date 7/10/2017 -->
	                                  <a href="javascript:displayDatePicker('txdTarikhMatiSimati',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a> #end 
	                                  
	                                  <input name="mohondate" type="hidden" id="mohondate" value="$md" size="10" maxlength="10" $readmode  />
                                  	#if($readmode != "disabled" )
                                  		<span class="style52">dd/mm/yyyy</span>
                                  	#end                                  
								</td>
                              </tr>
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Umur Ketika Mati</div></td>
                                <td class="style36">:</td>
                                <td class="style36"><label>
                                  <input name="txtUmurSimati" type="text" id="txtUmurSimati" onKeyUp="javascript:validateIC(event,this,this.value,'txtUmurSimati')"  onblur="Checkumur()" value="$listmati.umur" size="4" maxlength="3"   $readmodeR class="$readmode" />
                                </label></td>
                              </tr>
                              <tr>
                                <td><span class="style38"></span></td>
                                <td><div align="left" class="style38">Waktu Kematian</div></td>
                                <td class="style36">:</td>
                                <td class="style36"><input name="socWaktuKematianSimati" type="text" id="socWaktuKematianSimati" value="$listmati.masamati"  onKeyUp="javascript:validateIC(event,this,this.value,'socWaktuKematianSimati')" size="4" maxlength="4"   $readmodeR class="$readmode" onBlur="jeniswaktu1()" /> 
                                  <label>
                                  #if($readmode=="disabled")
                                  #set($waktu = "")
                                  #if( $listmati.jeniswaktu == 1)
                                     #set($waktu = "PAGI")  
                                  #elseif($listmati.jeniswaktu == 2)
                                     #set($waktu = "TENGAHARI")                                   
                                  #elseif($listmati.jeniswaktu == 3)
                                     #set($waktu = "PETANG")      
                                     #elseif($listmati.jeniswaktu == 4)
                                     #set($waktu = "MALAM")                                   
                                  #else
                                     #set($waktu = "")
                                  #end             
                                  
                                   <input name="jeniswaktu_d" type="text" id="jeniswaktu_d"    value="$waktu" size="4" maxlength="3" style="width:100"  $readmodeR class="$readmode" />
                                     <input name="jeniswaktu" type="hidden" id="jeniswaktu"    value="$listmati.jeniswaktu" />
                                  #else
                                  <select name="jeniswaktu" id="jeniswaktu" style="width:100"  onFocus="jeniswaktu2()" >
                                  
                                  #if( $listmati.jeniswaktu == 1)
                                  
                                  <option value="1" id="op_pagi" >PAGI</option>
                                  <option value="2" id="op_tengahari">TENGAHARI</option>
                                  <option value="3" id="op_petang">PETANG</option>
                                  <option value="4" id="op_malam">MALAM</option>                                  
                                  <option value="">SILA PILIH</option>                                    
                                    #elseif($listmati.jeniswaktu == 2)
                                      <option value="2" id="op_tengahari">TENGAHARI</option>
                                     <option value="1" id="op_pagi">PAGI</option>                                 
                                    <option value="3" id="op_petang">PETANG</option>
                                    <option value="4" id="op_malam">MALAM</option>
                                  
                                      <option value="">SILA PILIH</option>
                                    #elseif($listmati.jeniswaktu == 3)
                                     <option value="3" id="op_petang">PETANG</option>
                                  <option value="1" id="op_pagi">PAGI</option>
                                   <option value="2" id="op_tengahari">TENGAHARI</option>                                   
                                    <option value="4" id="op_malam">MALAM</option>
                                      <option value="">SILA PILIH</option>
                                       #elseif($listmati.jeniswaktu == 4)
                                        <option value="4" id="op_malam">MALAM</option>
                                        <option value="1" id="op_pagi">PAGI</option>
                                   <option value="2" id="op_tengahari">TENGAHARI</option>
                                    <option value="3" id="op_petang">PETANG</option>  
                                      <option value="">SILA PILIH</option>
                                    #else
                                  <option value="">SILA PILIH</option>
                                 
                                  <option value="1" id="op_pagi" >PAGI</option>                                 
                                  <option value="2" id="op_tengahari" >TENGAHARI</option>
                                  <option value="3"  id="op_petang" >PETANG</option>
                                  <option value="4" id="op_malam" >MALAM</option>            
                                     
                                    #end                                
                                    
                                 
                                  </select>
                                  #end
                                  </label>
                                  #if($readmode != "disabled" )
                                  <span class="style52">format 12 jam (HHMM)</span>
                                  #end                             
                                  
                                      </td>
                              </tr>
                          </table></td>
                        <td valign="top"><table width="100%">
                            <tr valign="top">
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                              <!-- <td width="1%" class="style38">&nbsp;</td> -->
                              <td width="28%" class="style38"><div align="right" class="style38">
                                <div align="left">Tempat Mati</div>
                              </div></td>
                              <td width="1%" style="text-transform:uppercase;">:</td>
                              <td width="70%" style="text-transform:uppercase;"><label>
                                                   
                              <textarea name="txtTempatMatiSimati" id="patMatiSimati"   cols="31" rows="3"    $readmodeR class="$readmode" >$listmati.tempatMati</textarea>
                              </label></td>
                            </tr>
                            <tr>
                              <!-- <td class="style38" valign="top" > -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                              
                             
                                 
                                 
                                 
                                 
                                 </td>
                              <td class="style38" valign="top">
                                <div align="left">#if($readmode == "disabled")
                                  Sebab Kematian
                                  #else </div>
                                <div align="right" >
                                  <div align="left">Sebab Kematian</div>
                                </div>
                                <div align="left">#end                              </div></td>
                              <td valign="top">:</td>
                              <td><textarea name="txtSebabKematianSimati" cols="31" rows="3" id="txtSebabKematian"   $readmodeR class="$readmode" >$listmati.sebabMati</textarea></td>
                            </tr>
                            <tr>
                              <!-- <td class="style38">&nbsp;</td> -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                              <td class="style38"><div align="right" class="style38">
                                <div align="left">Alamat Terakhir</div>
                              </div></td>
                              <td >:</td>
                              <td ><label>
                                <input name="txtAlamatTerakhir1Simati" type="text" id="txtAlamatTerakhir" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" value="$listmati.alamat1" size="34" maxlength="50"    $readmodeR class="$readmode"  />
                              </label></td>
                            </tr>
                            <tr>
                              <td class="style38">&nbsp;</td>
                              <td class="style38"><div align="left"><span class="style3"></span></div></td>
                              <td >:</td>
                              <td ><label>
                                <input name="txtAlamatTerakhir2Simati" type="text" id="txtAlamatTerakhir2" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$listmati.alamat2" size="34" maxlength="50"   $readmodeR class="$readmode" />
                              </label></td>
                            </tr>
                            <tr>
                              <td class="style38">&nbsp;</td>
                              <td class="style38"><div align="left"><span class="style3"></span></div></td>
                              <td >:</td>
                              <td ><input name="txtAlamatTerakhir3Simati" type="text" id="txtAlamatTerakhir3" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" value="$listmati.alamat3" size="34" maxlength="50"   $readmodeR class="$readmode" /></td>
                            </tr>
                            <tr>
                              <!-- <td class="style38">&nbsp;</td> -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                              <td class="style38"><div align="right" class="style38">
                                <div align="left">Poskod </div>
                              </div></td>
                              <td style="text-transform:uppercase;">:</td>
                              <td style="text-transform:uppercase;"><label>
                                <input name="txtPoskodSimati" type="text" id="txtPoskod " value="$listmati.poskod" size="5" maxlength="5"   $readmodeR class="$readmode" style="text-transform:uppercase;" onKeyUp="javascript:validateIC(event,this,this.value,'txtPoskodSimati')"/>
                              </label></td>
                            </tr>
                            <tr>
                              <!-- <td class="style38">&nbsp;</td> -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                              <td class="style38"><div align="right" class="style38">
                                <div align="left">Negeri</div>
                              </div></td>
                              
                              
                              <td>:                              </td>
                              
                                
                            #if($listmati.idnegeri=="" || $listmati.idnegeri=="0" )
                             #set($kod="")
                             
                             #else
                             
                             #foreach($listneg in $listnegeri)      
                                      
                                      
                                      #if($listmati.idnegeri==$listneg.id_Negeri)
                                      
                                      #set($kod="$listneg.kod_Negeri - $listneg.nama_Negeri")
                                      
                                      
                                      #end
                             #end
                             
                             #end
                                
                                
                                      
                                      <td>
                                      
                                       #if($readmode=="disabled")
                      <input name="socNegeri" type="text" id="textfield7"  style="width: 265px; text-transform:uppercase;" onBlur="uppercase()" value="$kod"  size="34"   $readmodeR class="$readmode" />                                        
                                        #else                                     
                                        
                                        #if($listmati.idnegeri!="" && $listmati.idnegeri!=0)
                                        <select name="socNegeriSimati" class="autoselect" id="socNegeriPemohon"  onchange="getBandarTetap('txtBandarSimati')" >
                                        <option value="$listmati.idnegeri" style="text-transform:uppercase;" onBlur="uppercase()">$kod</option>
                                  #foreach($listneg in $listnegeri)                                 
                                  #if($listmati.idnegeri!=$listneg.id_Negeri)
                       <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                     
                                  #end    
	                              #end
                                        </select>
                                        #else
                                        <select name="socNegeriSimati" class="autoselect" onChange="getBandarTetap('txtBandarSimati')">
                                          <option value="" >SILA PILIH NEGERI</option>
                                  #foreach($listneg in $listnegeri)
                   <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onBlur="uppercase()">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                    
	                               #end
                                        </select>
                                        #end   
                                                                             
                                        #end                                        </td>
                            </tr>
                            
                  
                  
                            
                              
        <tr id="tr_mesej_pelbagainegara">
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top">
        </td>
        <td valign="top"  >
        <div id="div_mesejpelbagagainegara"></div>
        </td>        
        </tr>  
        
        <tr id="tr_pelbagainegara">
                                    <td valign="top" ></td>
                                    <td>Negara</td>
                                    <td >:</td>
                                    <td >
                                    <input type = 'text' id = 'nama_pelbagainegara' name = 'nama_pelbagainegara' size='30' maxlength='200' $readmodeR class="$readmode"  list = 'datalist'  value="$listmati.nama_pelbagainegara"    />
                                    <datalist id = 'datalist'>
                                    #foreach($ja in $kenegaraan)
                                    <option label='$!ja.NAMA_WARGA' value = '$!ja.NAMA_WARGA'></option>
                                    #end </datalist>
                                    </td>
        </tr>
        
                            
                            
                            <tr>
                              <!-- <td class="style38">&nbsp;</td> -->
                              <td valign="top" ><span class="style38">#if($readmode != "disabled") <span class="style49">*</span> #end</span></td>
                               #set($bandartetap = $listmati.bandartetap)
                              <td class="style38"><div align="left">#if($readmode != "disabled" ) </div>
                              <div align="right" class="style43">
                                    <div align="left">Bandar</div>
                              </div>
                                <div align="left">#else
                                  Bandar
                                  #end </div></td>
                              <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                              <td><label> 
                              
                              
                              
                           
                             
                             #foreach($listneg in $listBandarTetapbyNegeri)      
                                      
                                      
                                      #if($bandartetap == $listneg.id)                                      
                                      #set($kodb = "$listneg.kod - $listneg.nama")                                                                      
                                      #end
                             #end
                             
                           
                              
                              
                              #if($readmode=="disabled" )
                    
                               #if($bandartetap != "" && $bandartetap != "0")
                      <input name="socBandar" type="text" id="socBandar"   style="width: 265px; text-transform:uppercase;" onBlur="uppercase()" value="$kodb"  size="34"   $readmodeR class="$readmode" />   
                      #else
                      <input name="socBandar" type="text" id="socBandar"  onblur="uppercase()" value=""  size="34"   $readmodeR class="$readmode" />  
                      
                      #end    
                                                       
                                        #else
                              
                              #foreach($listdaerah in $listBandarTetapbyNegeri)                                
                                #if($bandartetap==$listdaerah.id)                                
                                #set($listDaerahbyNegeriK=$listdaerah.kod)
                                #set($listDaerahbyNegeriN=$listdaerah.nama)
                                #end 
                                #end
                                
                                
                                #if($disabled=="disabled")
                                #set($readmodedaerah="disabled")
                                #end
                                #if($bandartetap!="" && $bandartetap!="0" )
                                <select name="txtBandarSimati" id="txtBandarSimati" class="autoselect" $readmode   style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandarTetap()" >
                  <option value="$bandartetap">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                                  
              
              
                      
                                                  
                                            
                                              
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($bandartetap!=$listdaerah.id)
                                    
	                               
                                              
                                            
                                                  
                      
              
              
                                  <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                                  
              
              
                      
                                                  
                                            
                                              
                                   
                                  #end    
	                               #end
                                  
                                  
                                  
                                  
                                            
                                          
                                                
                    
            
            
                                </select>
                                #else
                                <select name="txtBandarSimati" id="txtBandarSimati" class="autoselect" $readmode   style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandarTetap()" >
                                  <option value="">Sila Pilih Bandar</option>
                                  
  
              
    
    
  
                                              
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                                 
                                
	                               
                                              
  
    
    
              
  
                                  <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
                                  
  
              
    
    
  
                                              
                                   
                                 
	                               #end
                                  
                                  
                                  
                                  
                                            

  
  
            

                                </select>
                                #end 
                                #end</label></td>
                            </tr>
                            <tr>
                              <td class="style38" valign="top">&nbsp;</td>
                              <td class="style38" valign="top"><div  align="right" class="style38">
                                <div align="left">Catatan</div>
                              </div></td>
                              <td valign="top">:</td>
                              <td><textarea name="txtCatatanSimati"  cols="31" rows="3" id="Catatan"   $readmodeR class="$readmode" >$listmati.catatan</textarea></td>
                       </tr>
                        </table></td>
                      </tr>
                      
                     
                      
                      
                    </table>
                                   
                     #if($readmode != "disabled") 
                    <table width="100%">
  <tr>
    <td><span class="style44">Perhatian</span><span class="style50"> : Sila masukkan salah satu nombor MyID  dan pastikan label bertanda <span class="style49">*</span> diisi.</span></td>
  </tr>
</table>
#end
                    
                    </fieldset>
                    </div>
                    
                    <div class="TabbedPanelsContent">
            
            #foreach($listpemohon in $listPemohon)
          		#set($namaPemohon = $listpemohon.namaPemohon)
          		#set($noKpBaruPemohon2 = $listpemohon.noKpBaru2)
          		#set($noKpBaruPemohon1 = $listpemohon.noKpBaru1)
          		#set($noKpBaruPemohon3 = $listpemohon.noKpBaru3)
          		#set($namaLainPemohon = $listpemohon.namaLain)
          		#set($noKpLamaPemohon = $listpemohon.noKpLama)
          		#set($jantinaPemohon = $listpemohon.jantina)
          		#set($jenisAgama = $listpemohon.jenisAgama)
          		#set($jenisWarga = $listpemohon.jenisWarga)
          		#set($idsaudara = $listpemohon.idsaudara)

          	#end
          	#set($nowa=0)
            <fieldset>
            <legend>MAKLUMAT WARIS</legend>
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
			<tr class="table_header">
            		<td width="5%" align="center">No.
            		</td>
            		<td width="25%" align="center">Nama Waris
            		</td>
            		<td width="20%" align="center">MyID Baru
            		</td>
            		<td width="5%" align="center">Umur
            		</td>
            		<td width="20%" align="center">Tali Persaudaraan
            		</td>
            		<td width="15%" align="center">Status
            		</td>
            		<td width="10%" align="center">Lapisan
            		</td>
            	
            	</tr>
            	#foreach($listwaris in $listWaris)
                   #set($nowa=$nowa+1)
                   #set($nama_Ob=$listwaris.nama_Ob)
                   #set($idwaris=$listwaris.idwaris)
                   #set($nokpbaru=$listwaris.nokpbaru)
                   #set($umur=$listwaris.umur)
                   #set($statushidup=$listwaris.statushidup)
                   #if($statushidup=="0")
                       #set($hidup="Masih Hidup")
                   #end
                   #if($statushidup=="1")
                       #set($hidup="Sudah Meninggal")
                   #end
                   #if($statushidup=="")
                       #set($hidup="")
                   #end
                   #set($lapis=$listwaris.lapis)
                #end   
                
                
                    <tr >
                                      <td width="5%" class="row1"><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$nowa</div></td>
                                      <td><div align="left" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:get_waris('$idwaris')" class="style42">$nama_Ob</a></div></td>
                                      <td><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$nokpbaru</div></td>
                                      <td><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$umur</div></td>
                                      <td></td>
                                      <td><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$hidup</div></td>
                                      <td><div align="center" class="style72" style="text-transform:uppercase;" onblur="uppercase()">$lapis</div></td>
                   </tr>
            	
            </table>
            </fieldset>
            
            </div>
				
				

              	<div class="TabbedPanelsContent">
              	<!-- 
          	#foreach($listmati in $listSimati)
          		#set($namaSimati = $listmati.namaSimati)
          		#set($noKpBaru2 = $listmati.noKpBaru2)
          		#set($noKpBaru1 = $listmati.noKpBaru1)
          		#set($noKpBaru3 = $listmati.noKpBaru3)
          		#set($namaLain = $listmati.namaLain)
          		#set($tempatMati = $listmati.tempatMati)
          		#set($tarikhMati = $listmati.tarikhMati)
          		#set($sebabMati = $listmati.sebabMati)
          		#set($tempatMati = $listmati.tempatMati)
          		#set($noKpLama = $listmati.noKpLama)
          		
          		
          		 
          	#end
          		<input type="hidden" name="questioner" value="$questioner"> 
              	<!-- 
              	<fieldset>
              	<legend>SENARAI KEHADIRAN</legend>
              	
              	<table width="40%" border="0" cellspacing="2" cellpadding="2">
              	<tr class="table_header">
              	<td width="5%" align="center">Bil.
              	</td>
              	<td>Nama <input type="hidden" name="namaPenanya" value="$namaHadir">
              	</td>

              	</tr>
              	#set($nohadir=0)
          		#foreach($listHadir in $listKehadiran)
          			#set($namaHadir = $listHadir.nama)
          			#set($hubunganHadir = $listHadir.hubungan)
          			#set($kehadiran = $listHadir.kehadiran)
          			#set($nohadir = $nohadir+1)
          			
          			<tr>
              			<td align="center">$nohadir
              			</td>
              			<td><a href="javascript:paparSoalan('$namaHadir')" class="style42" >$namaHadir</a>
              			</td>

              		</tr>
          		#end
              	
	</table>
	</fieldset>
    -->
   <fieldset>
	<legend>HARTA</legend>
	</fieldset>
    </div>
    
    <div class="TabbedPanelsContent">
    <fieldset>
	<legend>HUTANG</legend>
	</fieldset>
    </div>
    
                
    <div class="TabbedPanelsContent">
	<fieldset>
	<legend>SIASATAN</legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
            	<tr class="table_header">
            		<td width="5%" align="center">Bil.
            		</td>
            		<td width="30%" align="center">Soalan
            		</td>
            		<td width="30%" align="center">Skema
            		</td>
            		<td width="5%" align="center">Check
            		</td>
            		<td width="30%" align="center">Jawapan
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">1.
            		</td>
            		<td width="30%">Nama Pemohon
            		</td>
            		<td width="30%" ><input  type="text" value="$!namaPemohon" size="34" maxlength="200" $readmodeR class="$readmode">
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon1" value="">
            		</td>
            		<td width="30%" >
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">2.
            		</td>
            		<td width="30%">No KP pemohon
            		</td>
            		<td width="30%">MyID Baru &nbsp;: &nbsp;<input  type="text" size="6" value="$!noKpBaruPemohon1" $readmodeR class="$readmode">-<input  type="text" size="2" value="$!noKpBaruPemohon2" $readmodeR class="$readmode">-<input  size="4" type="text" value="$!noKpBaruPemohon3" $readmodeR class="$readmode"><br/><br/>
            		MyID Lama : &nbsp;<input name="txtNoKPLamaSimati" type="text" id="textfield4" style="text-transform:uppercase;" value="$!noKpLamaPemohon" size="15" maxlength="15" $readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()" />
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon2" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">3.
            		</td>
            		<td width="30%">Jantina pemohon
            		</td>
            		<td width="30%">
            		#if($jantinaPemohon=="1")
                      #set($sexpemohon="Lelaki")
     				#elseif($listpemohon.jantina=="2")
                      #set($sexpemohon="Perempuan")
                    #else
                      #set($sexpemohon="")
                    #end
                    #if($sexpemohon=="")
                    	<input name="textfield" type="text" id="textfield2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" $readmodeR class="$readmode" />
                    #else
                        <input name="textfield" type="text" id="textfield2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$sexpemohon"  size="15" $readmodeR class="$readmode" />
                    #end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon3" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            	#if($jenisAgama=="1")
                    #set($agp="Islam")
                #elseif($jenisAgama=="2")
                    #set($agp="Bukan Islam")
                #else
                    #set($agp="")
                #end
                <td width="5%" align="center">4.
            		</td>
            		<td width="30%">Agama pemohon
            		</td>
            		<td width="30%">
            		#if($agp=="")
                       <input name="socAgamaPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                    #else
                       <input name="socAgamaPe" type="text" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$agp"  size="34" $readmodeR class="$readmode" />
                    #end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon4" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            	#if($jenisWarga=="1")
                   #set($wrp="Warganegara")
                #elseif($jenisWarga=="2")
                    #set($wrp="Bukan Warganegara")
                #elseif($jenisWarga=="3")
                    #set($wrp="Pemastautin Tetap")
                #else
                    #set($wrp="")
                #end
                <td width="5%" align="center">5.</td>
            		<td width="30%">Warganegara pemohon
            		</td>
            		<td width="30%">
            		#if($wrp=="")
                       <input name="socWarganegaraPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                    #else
                       <input name="socWarganegaraPe" type="text" id="textfield" value="$wrp" style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                    #end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon5" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            	   #if($idsaudara=="" || $idsaudara=="0" )
                      #set($kodsaudara="")
                   #else
                      #foreach($listsau in $listsaudara)
                          #if($idsaudara==$listsau.id_Saudara)
                             #set($kodsaudara="$listsau.keterangan")
                          #end    
                       #end
                   #end
                <td width="5%" align="center">6.</td>
            		<td width="30%">Talian persaudaraan
            		</td>
            		<td width="30%">
            		<input name="socTalianPersaudaraan" type="text" class="$readmode" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$kodsaudara"  size="34" maxlength="150" $readmodeR />
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon6" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	<tr>
            		<td width="5%" align="center">6.
            		</td>
            		<td width="30%">Nama Simati
            		</td>
            		<td width="30%"><input  type="text" value="$!namaSimati" size="34" maxlength="200" $readmodeR class="$readmode">
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">7.
            		</td>
            		<td width="30%">Nama Lain Simati
            		</td>
            		#if ($namaLain == "")
            			#set ($namaLain = "                         -                         ")
            		#end
            		<td width="30%"><input  type="text" value="$!namaLain" size="34" maxlength="200" $readmodeR class="$readmode">
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox2" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">8.
            		</td>
            		<td width="30%">No KP simati
            		</td>
            		<td width="30%">MyID Baru &nbsp;: &nbsp;<input  type="text" size="6" value="$!noKpBaru1" $readmodeR class="$readmode">-<input  type="text" size="2" value="$!noKpBaru2" $readmodeR class="$readmode">-<input  size="4" type="text" value="$!noKpBaru3" $readmodeR class="$readmode"><br/><br/>
            		MyID Lama : &nbsp;<input name="txtNoKPLamaSimati" type="text" id="textfield4" style="text-transform:uppercase;" value="$!noKpLama" size="15" maxlength="15" $readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()" />
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox3" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">9.
            		</td>
            		<td width="30%">Tarikh mati
            		</td>
            		<td width="30%"><input  type="text" value="$!tarikhMati" size="34" maxlength="200" $readmodeR class="$readmode">
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox4" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">10.
            		</td>
            		<td width="30%">Sebab kematian
            		</td>
            		<td width="30%"><textarea name="txtSebabKematianSimati" cols="31" rows="3" id="txtSebabKematian"   $readmodeR class="$readmode" >$sebabMati</textarea>
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox5" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">11.
            		</td>
            		<td width="30%">Tempat dikebumikan (sekiranya simati bukan Islam, dibakar/ditanam di mana)
            		</td>
            		<td width="30%"><textarea name="txtTempatMatiSimati" id="patMatiSimati"   cols="31" rows="3"    $readmodeR class="$readmode" >$tempatMati</textarea>
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox6" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">12.
            		</td>
            		<td width="30%">Jumlah perbelanjaan kebumikan simati sudah bayar atau belum
            		</td>
            		<td width="30%"> 
            				<select style="width: 150px;">
  								<option value="SUDAH">SUDAH DISELESAIKAN</option>
 								<option value="BELUM">BELUM DISELESAIKAN</option>
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox7" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	<tr>
            	<td colspan="5" align="center"><b>**********SOALAN BERKAITAN SIASATAN WARIS**********</b></td>
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center"><input type="hidden" name="hidupIbu1" value="$hidupIbu">13.
            		</td>
            		<td width="30%">Status Perkahwinan
            		</td>
            		<td width="30%"><select name="perkahwinan" style="width: 150px;" onclick="semakPerkahwinan()">
            		#if ($status_perkahwinan == "TIDAK")
            			<option value="TIDAK">TIDAK BERKAHWIN</option>
            			<option value="YA">BERKAHWIN</option>
            			#elseif ($status_perkahwinan == "YA")
            			<option value="YA">BERKAHWIN</option>
            			<option value="TIDAK">TIDAK BERKAHWIN</option>
            		#else
  								<option value="TIDAK">TIDAK BERKAHWIN</option>
 								<option value="YA">BERKAHWIN</option>
 					#end
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox8" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	#if($status_perkahwinan == "YA")
            	<tr>
            		<td width="5%" align="center">14.
            		</td>
            		<td width="30%">Bilangan suami atau isteri (SUAMI/ISTERI)
            		</td>
            		<td width="30%"><select name="bilpasangan" style="width: 35px;" onclick="semakPerkahwinan()">
 								<option value="1">1</option>
 								<option value="2">2</option>
 								<option value="3">3</option>
 								<option value="4">4</option>
 								<option value="5">5</option>
 								</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox15" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            	#if($bilpasangan != "")
            	<tr>
            	<td colspan="5" align="center"><b>**********SOALAN PERKAHWINAN 1**********</b></td>
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Nama Isteri
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">13.
            		</td>
            		<td width="30%">Cerai Hidup/Mati
            		</td>
            		<td width="30%"><select name="cerai" style="width: 150px;" onclick="semakPerkahwinan()">
            		#if ($cerai == "CERAI HIDUP")
            			<option value="CERAI HIDUP">CERAI HIDUP</option>
            			<option value="CERAI MATI">CERAI MATI</option>
            		#elseif ($cerai == "CERAI MATI")
            			<option value="CERAI MATI">CERAI MATI</option>
            			<option value="CERAI HIDUP">CERAI HIDUP</option>
            			
            		#else
  								<option value="CERAI HIDUP">CERAI HIDUP</option>
 								<option value="CERAI MATI">CERAI MATI</option>
 					#end
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox8" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            		#if ($cerai == "CERAI HIDUP")
            		<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Tarikh Penceraian
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Berhak
            		</td>
            		<td width="30%">
            		#if ($berhak == "Dapat")
            		
            		<input type="radio" name="Berhak" value="Dapat" onclick="semakPerkahwinan()" checked="checked"> Dapat
            		<input type="radio" name="Berhak" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#elseif ($berhak == "Tidak Dapat")
            		
            		<input type="radio" name="Berhak" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="Berhak" value="Tidak Dapat" onclick="semakPerkahwinan()" checked="checked">Tidak Dapat
            		#else
            		
            		<input type="radio" name="Berhak" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="Berhak" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            		#end
            		
            		#if ($cerai == "CERAI MATI")
            		<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Pasangan Hidup/Mati
            		</td>

            		<td width="30%"><select name="cerai_mati" style="width: 150px;" onclick="semakPerkahwinan()">
            		#if ($cerai_mati == "HIDUP")
            			<option value="HIDUP">HIDUP</option>
            			<option value="MATI">MATI</option>
            			
            		#elseif ($cerai_mati == "MATI")
            			<option value="MATI">MATI</option>
            			<option value="HIDUP">HIDUP</option>
            			
            		#else
  								<option value="HIDUP">HIDUP</option>
 								<option value="MATI">MATI</option>
 					#end
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            		
            		
            	#if ($cerai_mati == "MATI")
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Tarikh Mati
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	#end
            	
            	<tr>
            		<td width="5%" align="center">15xx.
            		</td>
            		<td width="30%">Berhak
            		</td>
            		<td width="30%">
            		#if ($berhak == "Dapat")
            		
            		<input type="radio" name="Berhak" value="Dapat" onclick="semakPerkahwinan()" checked="checked"> Dapat
            		<input type="radio" name="Berhak" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#elseif ($berhak == "Tidak Dapat")
            		
            		<input type="radio" name="Berhak" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="Berhak" value="Tidak Dapat" onclick="semakPerkahwinan()" checked="checked">Tidak Dapat
            		#else
            		
            		<input type="radio" name="Berhak" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="Berhak" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	#end
            	#if ($berhak != "")
            	<tr>
            	<td colspan="5" align="center"><b>**********SOALAN ANAK DARI PASANGAN 1**********</b></td>
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Ada anak
            		</td>
            		#if ($anak == "Ada")
            		<td width="30%"><input type="radio" name="anak" value="Ada" onclick="semakPerkahwinan()" checked="checked"> Ada
            		<input type="radio" name="anak" value="Tiada" onclick="semakPerkahwinan()">Tiada
            		</td>
            		#elseif ($anak == "Tiada")
            		<td width="30%"><input type="radio" name="anak" value="Ada" onclick="semakPerkahwinan()"> Ada
            		<input type="radio" name="anak" value="Tiada" onclick="semakPerkahwinan()" checked="checked">Tiada
            		#else
            		<td width="30%"><input type="radio" name="anak" value="Ada" onclick="semakPerkahwinan()"> Ada
            		<input type="radio" name="anak" value="Tiada" onclick="semakPerkahwinan()">Tiada
            		#end
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            	#if ($anak=="Ada")
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Bilangan anak
            		</td>
            		<td width="30%"><select name="bilAnak" style="width: 35px;" onclick="semakPerkahwinan()">
 								#if ($bilAnak != "")
 								<option value="$bilAnak">$bilAnak</option>
 								#end
 								<option value="1">1</option>
 								<option value="2">2</option>
 								<option value="3">3</option>
 								<option value="4">4</option>
 								<option value="5">5</option>
 								</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
           	<tr>
            	<td colspan="5" align="center"><b>**********SOALAN ANAK PERTAMA DARI PASANGAN 1**********</b></td>
            	</tr>
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Nama Anak 1
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Jantina
            		</td>
            		<td width="30%"><select name="jantinaAnak" style="width: 105px;" onclick="semakPerkahwinan()">
 								<option value="Lelaki">Lelaki</option>
 								<option value="Perempuan">Perempuan</option>
 								</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Status Anak
            		</td>
            		<td width="30%"><select name="statusAnak" style="width: 105px;" onclick="semakPerkahwinan()">
 								<option value="1">Anak Kandung</option>
 								<option value="2">Anak Angkat</option>
 								</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Hidup/Mati
            		</td>
            		<td width="30%">
            		#if ($hidupMati == "Hidup")
            		
            		<input type="radio" name="hidupMati" value="Hidup" onclick="semakPerkahwinan()" checked="checked"> Hidup
            		<input type="radio" name="hidupMati" value="Mati" onclick="semakPerkahwinan()">Mati
            		#elseif ($hidupMati == "Mati")
            		
            		<input type="radio" name="hidupMati" value="Hidup" onclick="semakPerkahwinan()" > Hidup
            		<input type="radio" name="hidupMati" value="Mati" onclick="semakPerkahwinan()" checked="checked">Mati
            		#else
            		
            		<input type="radio" name="hidupMati" value="Hidup" onclick="semakPerkahwinan()" > Hidup
            		<input type="radio" name="hidupMati" value="Mati" onclick="semakPerkahwinan()">Mati
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	#if ($hidupMati  == "Mati")
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Tarikh Mati
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	#end
            	
            	#if ($hidupMati == "Hidup")
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Hadir
            		</td>
            		<td width="30%">
            		#if ($kehadiran == "Hadir")
            		
            		<input type="radio" name="kehadiran" value="Hadir" onclick="semakPerkahwinan()" checked="checked"> Hadir
            		<input type="radio" name="kehadiran" value="Tidak Hadir" onclick="semakPerkahwinan()">Tidak Hadir
            		#elseif ($kehadiran == "Tidak Hadir")
            		
            		<input type="radio" name="kehadiran" value="Hadir" onclick="semakPerkahwinan()" > Hadir
            		<input type="radio" name="kehadiran" value="Tidak Hadir" onclick="semakPerkahwinan()" checked="checked">Tidak Hadir
            		#else
            		
            		<input type="radio" name="kehadiran" value="Hadir" onclick="semakPerkahwinan()" > Hadir
            		<input type="radio" name="kehadiran" value="Tidak Hadir" onclick="semakPerkahwinan()">Tidak Hadir
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            	#if ($kehadiran == "Tidak Hadir" && $hidupMati == "Hidup")
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">DDA
            		</td>
            		<td width="30%">
            		#if ($DDA == "Ada")
            		
            		<input type="radio" name="DDA" value="Ada" onclick="semakPerkahwinan()" checked="checked"> Ada
            		<input type="radio" name="DDA" value="Tiada" onclick="semakPerkahwinan()">Tiada
            		#elseif ($DDA == "Tiada")
            		
            		<input type="radio" name="DDA" value="Ada" onclick="semakPerkahwinan()" > Ada
            		<input type="radio" name="DDA" value="Tiada" onclick="semakPerkahwinan()" checked="checked">Tiada
            		#else
            		
            		<input type="radio" name="DDA" value="Ada" onclick="semakPerkahwinan()" > Ada
            		<input type="radio" name="DDA" value="Tiada" onclick="semakPerkahwinan()">Tiada
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            	#if ($kehadiran != "")
            	<tr>
            		<td width="5%" align="center">15xx.
            		</td>
            		<td width="30%">Berhak
            		</td>
            		<td width="30%">
            		#if ($BerhakAnak == "Dapat")
            		
            		<input type="radio" name="BerhakAnak" value="Dapat" onclick="semakPerkahwinan()" checked="checked"> Dapat
            		<input type="radio" name="BerhakAnak" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#elseif ($BerhakAnak == "Tidak Dapat")
            		
            		<input type="radio" name="BerhakAnak" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="BerhakAnak" value="Tidak Dapat" onclick="semakPerkahwinan()" checked="checked">Tidak Dapat
            		#else
            		
            		<input type="radio" name="BerhakAnak" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="BerhakAnak" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#if ($BerhakAnak != "")
            		<tr>
            	<td colspan="5" align="center"><b>**********SOALAN ANAK KEDUA DARI PASANGAN 1**********</b></td>
            	</tr>
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Nama Anak 2
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Jantina
            		</td>
            		<td width="30%"><select name="jantinaAnak2" style="width: 105px;" onclick="semakPerkahwinan()">
            					#if ($jantinaAnak2 == "Lelaki")
 								<option value="Lelaki">Lelaki</option>
 								<option value="Perempuan">Perempuan</option>
 								#elseif ($jantinaAnak2 == "Perempuan")
 								<option value="Perempuan">Perempuan</option>
 								<option value="Lelaki">Lelaki</option>
 								#else
 								<option value="Lelaki">Lelaki</option>
 								<option value="Perempuan">Perempuan</option>
 								#end
 								
 								
 								</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Status Anak
            		</td>
            		<td width="30%"><select name="statusAnak" style="width: 105px;" onclick="semakPerkahwinan()">
 								<option value="1">Anak Kandung</option>
 								<option value="2">Anak Angkat</option>
 								</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Hidup/Mati
            		</td>
            		<td width="30%">
            		#if ($hidupMati2 == "Hidup")
            		
            		<input type="radio" name="hidupMati2" value="Hidup" onclick="semakPerkahwinan()" checked="checked"> Hidup
            		<input type="radio" name="hidupMati2" value="Mati" onclick="semakPerkahwinan()">Mati
            		#elseif ($hidupMati2 == "Mati")
            		
            		<input type="radio" name="hidupMati2" value="Hidup" onclick="semakPerkahwinan()" > Hidup
            		<input type="radio" name="hidupMati2" value="Mati" onclick="semakPerkahwinan()" checked="checked">Mati
            		#else
            		
            		<input type="radio" name="hidupMati2" value="Hidup" onclick="semakPerkahwinan()" > Hidup
            		<input type="radio" name="hidupMati2" value="Mati" onclick="semakPerkahwinan()">Mati
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	#if ($hidupMati2  == "Mati")
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Tarikh Mati
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	#end
            	
            	#if ($hidupMati2 == "Hidup")
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Hadir
            		</td>
            		<td width="30%">
            		#if ($kehadiran2 == "Hadir")
            		
            		<input type="radio" name="kehadiran2" value="Hadir" onclick="semakPerkahwinan()" checked="checked"> Hadir
            		<input type="radio" name="kehadiran2" value="Tidak Hadir" onclick="semakPerkahwinan()">Tidak Hadir
            		#elseif ($kehadiran2 == "Tidak Hadir")
            		
            		<input type="radio" name="kehadiran2" value="Hadir" onclick="semakPerkahwinan()" > Hadir
            		<input type="radio" name="kehadiran2" value="Tidak Hadir" onclick="semakPerkahwinan()" checked="checked">Tidak Hadir
            		#else
            		
            		<input type="radio" name="kehadiran2" value="Hadir" onclick="semakPerkahwinan()" > Hadir
            		<input type="radio" name="kehadiran2" value="Tidak Hadir" onclick="semakPerkahwinan()">Tidak Hadir
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            	#if ($kehadiran2 == "Tidak Hadir" && $hidupMati2 == "Hidup")
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">DDA
            		</td>
            		<td width="30%">
            		#if ($DDA2 == "Ada")
            		
            		<input type="radio" name="DDA2" value="Ada" onclick="semakPerkahwinan()" checked="checked"> Ada
            		<input type="radio" name="DDA2" value="Tiada" onclick="semakPerkahwinan()">Tiada
            		#elseif ($DDA2 == "Tiada")
            		
            		<input type="radio" name="DDA2" value="Ada" onclick="semakPerkahwinan()" > Ada
            		<input type="radio" name="DDA2" value="Tiada" onclick="semakPerkahwinan()" checked="checked">Tiada
            		#else
            		
            		<input type="radio" name="DDA2" value="Ada" onclick="semakPerkahwinan()" > Ada
            		<input type="radio" name="DDA2" value="Tiada" onclick="semakPerkahwinan()">Tiada
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            	#if ($kehadiran2 != "")
            	<tr>
            		<td width="5%" align="center">15xx.
            		</td>
            		<td width="30%">Berhak
            		</td>
            		<td width="30%">
            		#if ($BerhakAnak2 == "Dapat")
            		
            		<input type="radio" name="BerhakAnak2" value="Dapat" onclick="semakPerkahwinan()" checked="checked"> Dapat
            		<input type="radio" name="BerhakAnak2" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#elseif ($BerhakAnak2 == "Tidak Dapat")
            		
            		<input type="radio" name="BerhakAnak2" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="BerhakAnak2" value="Tidak Dapat" onclick="semakPerkahwinan()" checked="checked">Tidak Dapat
            		#else
            		
            		<input type="radio" name="BerhakAnak2" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="BerhakAnak2" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            	#end
            	<!-- End di ats utk anak kedua  -->
            	#if ($BerhakAnak2 != "")
            	<tr>
            	<td colspan="5" align="center"><b>**********SIASATAN TERHADAP IBU **********</b></td>
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Nama Ibu
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">13.
            		</td>
            		<td width="30%">Hidup/Mati
            		</td>
            		<td width="30%">
            		
            		<select name="hidupIbu" style="width: 150px;" onclick="semakPerkahwinan()">
            		#if ($hidupIbu == "HIDUP")
            			<option value="HIDUP">HIDUP</option>
            			<option value="MATI">MATI</option>
            			
            		#elseif ($hidupIbu == "MATI")
            			<option value="MATI">MATI</option>
            			<option value="HIDUP">HIDUP</option>
            			
            			
            		#else
  								<option value="HIDUP">HIDUP</option>
 								<option value="MATI">MATI</option>
 							
 					#end
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox8" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            		#if ($hidupIbu == "MATI")
            		<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Tarikh Penceraian
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            	#if ($BerhakAnak2 != "")
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Berhak
            		</td>
            		<td width="30%">
            		#if ($berhakIbu == "Dapat")
            		
            		<input type="radio" name="BerhakIbu" value="Dapat" onclick="semakPerkahwinan()" checked="checked"> Dapat
            		<input type="radio" name="BerhakIbu" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#elseif ($berhakIbu == "Tidak Dapat")
            		
            		<input type="radio" name="BerhakIbu" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="BerhakIbu" value="Tidak Dapat" onclick="semakPerkahwinan()" checked="checked">Tidak Dapat
            		#else
            		
            		<input type="radio" name="BerhakIbu" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="BerhakIbu" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	#end
            	#end
            	
            	
            	#if ($berhakIbu != "")
            	<tr>
            	<td colspan="5" align="center"><b>**********SIASATAN TERHADAP BAPA **********</b></td>
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Nama Bapa
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">13.
            		</td>
            		<td width="30%">Hidup/Mati
            		</td>
            		<td width="30%"><select name="hidupBapa" style="width: 150px;" onclick="semakPerkahwinan()">
            		#if ($hidupBapa == "HIDUP")
            			<option value="HIDUP">HIDUP</option>
            			<option value="MATI">MATI</option>
            		#elseif ($hidupBapa == "MATI")
            			<option value="MATI">MATI</option>
            			<option value="HIDUP">HIDUP</option>
            			
            		#else
  								<option value="HIDUP">HIDUP</option>
 								<option value="MATI">MATI</option>
 					#end
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox8" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            		#if ($hidupBapa == "MATI")
            		<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Tarikh Penceraian
            		</td>
            		<td width="30%"><input  type="text" value="" size="34" maxlength="200" >
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            		#end
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Berhak
            		</td>
            		<td width="30%">
            		#if ($berhakBapa == "Dapat")
            		
            		<input type="radio" name="BerhakBapas" value="Dapat" onclick="semakPerkahwinan()" checked="checked"> Dapat
            		<input type="radio" name="BerhakBapa" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#elseif ($berhakBapa == "Tidak Dapat")
            		
            		<input type="radio" name="BerhakBapa" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="BerhakBapa" value="Tidak Dapat" onclick="semakPerkahwinan()" checked="checked">Tidak Dapat
            		#else
            		
            		<input type="radio" name="BerhakBapa" value="Dapat" onclick="semakPerkahwinan()" > Dapat
            		<input type="radio" name="BerhakBapa" value="Tidak Dapat" onclick="semakPerkahwinan()">Tidak Dapat
            		#end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox1" value=""></td>
            		
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	#end
				#end
            	
            	 <tr>	

            		<td colspan="5" align="center"><input type="checkbox" name="kosongkandia" value="kosongkandia">&nbsp;<input type="button" name="kosongkan" value="Kosongkan" id="semakWaris" onclick="semakPerkahwinan()"/>
            		</td>     	
            	</tr>
 <!--           	
            	<tr>
            		<td width="5%" align="center">14.
            		</td>
            		<td width="30%">Adakah simati mempunyai ibu (IBU)
            		</td>
            		<td width="30%"><select name="ibu" style="width: 100px;">
  								<option value="YA">YA</option>
 								<option value="TIDAK">TIDAK</option>
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox14" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	<tr>
            		<td width="5%" align="center">14.
            		</td>
            		<td width="30%">Simati pernah berkahwin atau tidak
            		</td>
            		<td width="30%"><select style="width: 150px;">
  								<option value="SUDAH">PERNAH BERKAHWIN</option>
 								<option value="BELUM">TIDAK PERNAH BERKAHWIN</option>
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox14" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Sekiranya simati sudah berkahwin, berapakah bilangan suami atau isteri (SUAMI/ISTERI)
            		</td>
            		<td width="30%"><select style="width: 35px;">
 								<option value="1">1</option>
 								<option value="2">2</option>
 								<option value="3">3</option>
 								<option value="4">4</option>
 								<option value="5">5</option>
 								</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox15" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">16.
            		</td>
            		<td width="30%">Sekiranya simati sudah berkahwin, berapakah bilangan anak
            		</td>
            		<td width="30%"><select style="width: 35px;">
  								<option value="0">0</option>
 								<option value="1">1</option>
 								<option value="2">2</option>
 								<option value="3">3</option>
 								<option value="4">4</option>
 								<option value="5">5</option>
 								<option value="6">6</option>
 								<option value="7">7</option>
 								<option value="8">8</option>
 								<option value="9">9</option>
 								<option value="10">10</option>
 								<option value="11">11</option>
 								<option value="12">12</option>
 								<option value="13">13</option>
 								<option value="14">14</option>
 								<option value="15">15</option>
 								<option value="16">16</option>
 								<option value="17">17</option>
 								<option value="18">18</option>
 								<option value="19">19</option>
 								<option value="20">20</option>
 								<option value="21">21</option>
 								<option value="22">22</option>
 								<option value="23">23</option>
 								<option value="24">24</option>
 								<option value="25">25</option>
 								<option value="26">26</option>
 								<option value="27">27</option>
 								<option value="28">28</option>
 								<option value="29">29</option>
 								<option value="30">30</option>
 								
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox16" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">17.
            		</td>
            		<td width="30%">Berapa ramai anak lelaki dan anak perempuan simati yang masih hidup
            		</td>
            		<td width="30%">Anak Lelaki (AL)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   :&nbsp;&nbsp;<input type="text" size="2" name="bilAnakLelakiHidup"><br/><br/>
            			Anak Perempuan (AP) :&nbsp;&nbsp;<input type="text" size="2" name="bilAnakPerempuanHidup">
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox17" value="">
            		</td>
            		<td width="30%"> 
            			
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">18.
            		</td>
            		<td width="30%">Berapa ramai anak lelaki dan anak perempuan simati yang telah meninggal dunia
            		</td>
            		<td width="30%">Anak Lelaki&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   :&nbsp;&nbsp;<input type="text" size="2" name="bilAnakLelakiMati"><br/><br/>
            			Anak Perempuan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<input type="text" size="2" name="bilAnakPerempuanMati">
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox18" value="">
            		</td>
            		<td width="30%"> 
            			
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">19.
            		</td>
            		<td width="30%">Adakah simati meninggalkan wasiat?
            		</td>
            		<td width="30%">
            		<select style="width: 100px;">
 								<option value="YA">YA</option>
 								<option value="TIDAK">TIDAK</option>
 					</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox19" value="">
            		</td>
            		
            		
            		<td width="30%"> 
            		<textarea rows="3" cols="50">
							
						</textarea> 
            			
            		</td>
            	
            	</tr>
            	#if ($bapa=="TIDAK")
            	<tr bgcolor="#FFF9AE">
            		<td width="5%" align="center">.
            		</td>
            		<td width="30%">Adakah Bapa simati meninggal dunia SEBELUM atau SELEPAS simati?
            		</td>
            		<td width="30%">
            		<select style="width: 100px;" name="BapaSimatiselection">
 								<option value="SELEPAS">SELEPAS</option>
 								<option value="SEBELUM">SEBELUM</option>
 					</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="BapaSimati" value="">
            		</td>
            		
            		
            		<td width="30%"> 
            		<textarea rows="3" cols="50">
							
						</textarea> 
            			
            		</td>
            	
            	</tr>
            	
            	<tr bgcolor="#FFF9AE">
            		<td width="5%" align="center">13.
            		</td>
            		<td width="30%">Adakah Bapa simati mempunyai bapa (DATUK)
            		</td>
            		<td width="30%"><select name="bapa" style="width: 100px;">
  								<option value="YA">YA</option>
 								<option value="TIDAK">TIDAK</option>
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox8" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr bgcolor="#FFF9AE">
            		<td width="5%" align="center">14.
            		</td>
            		<td width="30%">Adakah Bapa simati mempunyai ibu (NENEK)
            		</td>
            		<td width="30%"><select name="ibu" style="width: 100px;">
  								<option value="YA">YA</option>
 								<option value="TIDAK">TIDAK</option>
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox14" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	<tr bgcolor="#FFF9AE">
            		<td width="5%" align="center">14.
            		</td>
            		<td width="30%">Bapa simati pernah berkahwin atau tidak
            		</td>
            		<td width="30%"><select style="width: 150px;">
  								<option value="SUDAH">PERNAH BERKAHWIN</option>
 								<option value="BELUM">TIDAK PERNAH BERKAHWIN</option>
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox14" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr bgcolor="#FFF9AE">
            		<td width="5%" align="center">15.
            		</td>
            		<td width="30%">Sekiranya bapa simati sudah berkahwin, berapakah bilangan suami atau isteri (SUAMI/ISTERI)
            		</td>
            		<td width="30%"><select style="width: 35px;">
 								<option value="1">1</option>
 								<option value="2">2</option>
 								<option value="3">3</option>
 								<option value="4">4</option>
 								<option value="5">5</option>
 								</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox15" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr bgcolor="#FFF9AE">
            		<td width="5%" align="center">16.
            		</td>
            		<td width="30%">Sekiranya bapa simati sudah berkahwin, berapakah bilangan anak
            		</td>
            		<td width="30%"><select style="width: 35px;">
  								<option value="0">0</option>
 								<option value="1">1</option>
 								<option value="2">2</option>
 								<option value="3">3</option>
 								<option value="4">4</option>
 								<option value="5">5</option>
 								<option value="6">6</option>
 								<option value="7">7</option>
 								<option value="8">8</option>
 								<option value="9">9</option>
 								<option value="10">10</option>
 								<option value="11">11</option>
 								<option value="12">12</option>
 								<option value="13">13</option>
 								<option value="14">14</option>
 								<option value="15">15</option>
 								<option value="16">16</option>
 								<option value="17">17</option>
 								<option value="18">18</option>
 								<option value="19">19</option>
 								<option value="20">20</option>
 								<option value="21">21</option>
 								<option value="22">22</option>
 								<option value="23">23</option>
 								<option value="24">24</option>
 								<option value="25">25</option>
 								<option value="26">26</option>
 								<option value="27">27</option>
 								<option value="28">28</option>
 								<option value="29">29</option>
 								<option value="30">30</option>
 								
							</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox16" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr bgcolor="#FFF9AE">
            		<td width="5%" align="center">17.
            		</td>
            		<td width="30%">Berapa ramai anak lelaki dan anak perempuan bapa simati yang masih hidup
            		</td>
            		<td width="30%">Anak Lelaki (AL)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   :&nbsp;&nbsp;<input type="text" size="2" name="bilAnakLelakiHidup"><br/><br/>
            			Anak Perempuan (AP) :&nbsp;&nbsp;<input type="text" size="2" name="bilAnakPerempuanHidup">
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox17" value="">
            		</td>
            		<td width="30%"> 
            			
            		</td>
            	
            	</tr>
            	
            	<tr bgcolor="#FFF9AE">
            		<td width="5%" align="center">18.
            		</td>
            		<td width="30%">Berapa ramai anak lelaki dan anak perempuan bapa simati yang telah meninggal dunia
            		</td>
            		<td width="30%">Anak Lelaki&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   :&nbsp;&nbsp;<input type="text" size="2" name="bilAnakLelakiMati"><br/><br/>
            			Anak Perempuan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<input type="text" size="2" name="bilAnakPerempuanMati">
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox18" value="">
            		</td>
            		<td width="30%"> 
            			
            		</td>
            	
            	</tr>
            	
            	<tr bgcolor="#FFF9AE">
            		<td width="5%" align="center">19.
            		</td>
            		<td width="30%">Adakah bapa simati meninggalkan wasiat?
            		</td>
            		<td width="30%">
            		<select style="width: 100px;">
 								<option value="YA">YA</option>
 								<option value="TIDAK">TIDAK</option>
 					</select> 
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkbox19" value="">
            		</td>
            		
            		
            		<td width="30%"> 
            		<textarea rows="3" cols="50">
							
						</textarea> 
            			
            		</td>
            	
            	</tr>
            	
            	#end
            	<tr>		
            		<td colspan="5" align="center"><input type="button" name="semakWaris" value="Semak Senarai Waris" id="semakWaris" onclick="semakWaris1()"/>
            		</td>     	
            	</tr>
            
            -->  
            </table>
	</fieldset>
            
            <!-- End questioner !=0 -->
            
            <!-- End tab simati -->
            </div>
            
            <!-- Tab Pemohon -->
            <div>
            
            #foreach($listpemohon in $listPemohon)
          		#set($namaPemohon = $listpemohon.namaPemohon)
          		#set($noKpBaruPemohon2 = $listpemohon.noKpBaru2)
          		#set($noKpBaruPemohon1 = $listpemohon.noKpBaru1)
          		#set($noKpBaruPemohon3 = $listpemohon.noKpBaru3)
          		#set($namaLainPemohon = $listpemohon.namaLain)
          		#set($noKpLamaPemohon = $listpemohon.noKpLama)
          		#set($jantinaPemohon = $listpemohon.jantina)
          		#set($jenisAgama = $listpemohon.jenisAgama)
          		#set($jenisWarga = $listpemohon.jenisWarga)
          		#set($idsaudara = $listpemohon.idsaudara)

          	#end
            <fieldset>
            <legend>MAKLUMAT PEMOHON</legend>
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
			<tr class="table_header">
            		<td width="5%" align="center">Bil.
            		</td>
            		<td width="30%" align="center">Soalan
            		</td>
            		<td width="30%" align="center">Jawapan
            		</td>
            		<td width="5%" align="center">Check
            		</td>
            		<td width="30%" align="center">Catatan
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">1.
            		</td>
            		<td width="30%">Nama Pemohon
            		</td>
            		<td width="30%" ><input  type="text" value="$!namaPemohon" size="34" maxlength="200" $readmodeR class="$readmode">
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon1" value="">
            		</td>
            		<td width="30%" >
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	
            	
            	<tr>
            		<td width="5%" align="center">2.
            		</td>
            		<td width="30%">No KP pemohon
            		</td>
            		<td width="30%">MyID Baru &nbsp;: &nbsp;<input  type="text" size="6" value="$!noKpBaruPemohon1" $readmodeR class="$readmode">-<input  type="text" size="2" value="$!noKpBaruPemohon2" $readmodeR class="$readmode">-<input  size="4" type="text" value="$!noKpBaruPemohon3" $readmodeR class="$readmode"><br/><br/>
            		MyID Lama : &nbsp;<input name="txtNoKPLamaSimati" type="text" id="textfield4" style="text-transform:uppercase;" value="$!noKpLamaPemohon" size="15" maxlength="15" $readmodeR class="$readmode" onBlur="this.value=this.value.toUpperCase()" />
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon2" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            		<td width="5%" align="center">3.
            		</td>
            		<td width="30%">Jantina pemohon
            		</td>
            		<td width="30%">
            		#if($jantinaPemohon=="1")
                      #set($sexpemohon="Lelaki")
     				#elseif($listpemohon.jantina=="2")
                      #set($sexpemohon="Perempuan")
                    #else
                      #set($sexpemohon="")
                    #end
                    #if($sexpemohon=="")
                    	<input name="textfield" type="text" id="textfield2"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="15" $readmodeR class="$readmode" />
                    #else
                        <input name="textfield" type="text" id="textfield2" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$sexpemohon"  size="15" $readmodeR class="$readmode" />
                    #end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon3" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            	#if($jenisAgama=="1")
                    #set($agp="Islam")
                #elseif($jenisAgama=="2")
                    #set($agp="Bukan Islam")
                #else
                    #set($agp="")
                #end
                <td width="5%" align="center">4.
            		</td>
            		<td width="30%">Agama pemohon
            		</td>
            		<td width="30%">
            		#if($agp=="")
                       <input name="socAgamaPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                    #else
                       <input name="socAgamaPe" type="text" id="textfield" style="text-transform:uppercase;" onblur="uppercase()" value="$agp"  size="34" $readmodeR class="$readmode" />
                    #end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon4" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            	#if($jenisWarga=="1")
                   #set($wrp="Warganegara")
                #elseif($jenisWarga=="2")
                    #set($wrp="Bukan Warganegara")
                #elseif($jenisWarga=="3")
                    #set($wrp="Pemastautin Tetap")
                #else
                    #set($wrp="")
                #end
                <td width="5%" align="center">5.</td>
            		<td width="30%">Warganegara pemohon
            		</td>
            		<td width="30%">
            		#if($wrp=="")
                       <input name="socWarganegaraPe" type="text" id="textfield"  style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                    #else
                       <input name="socWarganegaraPe" type="text" id="textfield" value="$wrp" style="text-transform:uppercase;" onblur="uppercase()" size="34" $readmodeR class="$readmode" />
                    #end
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon5" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	<tr>
            	   #if($idsaudara=="" || $idsaudara=="0" )
                      #set($kodsaudara="")
                   #else
                      #foreach($listsau in $listsaudara)
                          #if($idsaudara==$listsau.id_Saudara)
                             #set($kodsaudara="$listsau.keterangan")
                          #end    
                       #end
                   #end
                <td width="5%" align="center">6.</td>
            		<td width="30%">Talian persaudaraan
            		</td>
            		<td width="30%">
            		<input name="socTalianPersaudaraan" type="text" class="$readmode" id="textfield" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="$kodsaudara"  size="34" maxlength="150" $readmodeR />
            		</td>
            		<td width="5%" align="center"><input type="checkbox" name="checkboxPemohon6" value="">
            		</td>
            		<td width="30%"> 
            			<textarea rows="3" cols="50">
							
						</textarea> 
            		</td>
            	
            	</tr>
            	
            	
			</table>
            
            </fieldset>
            </div>
            <!-- Tab Pemohon -->

</div>
</div>
</fieldset>
         
				

#foreach($IdPermohonanSimati2 in $IdPermohonanSimati)
#set($id_permohonansimati = $IdPermohonanSimati2.id_permohonansimati)
#end
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/> 
<input type="hidden" name="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_simati" value="$id_simati" />
<input type="hidden" name="id_Permohonansimati" value="$!id_permohonansimati" />
<script>

function semakWaris1() {
	var bapa=document.${formName}.bapa.value;
	//alert("document.${formName}.BapaSimati = "+document.${formName}.BapaSimati);
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline?bapa="+bapa;
		document.${formName}.command.value = "maklumatSimati";
		document.${formName}.submit();


	
}

function semakPerkahwinan() {
	var bapa=document.${formName}.perkahwinan.value;
	//alert("document.${formName}.BapaSimati = "+document.${formName}.BapaSimati);

		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline?bapa="+bapa;
		document.${formName}.command.value = "maklumatSimati";
		document.${formName}.submit();
		

	
}

function kosongkan() {
	var bapa=document.${formName}.perkahwinan.value;
	//alert("document.${formName}.BapaSimati = "+document.${formName}.BapaSimati);
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline?bapa="+bapa;
		document.${formName}.command.value = "kosongkan";
		document.${formName}.submit();


	
}



function setSelected(tabId) {
	
    document.${formName}.tabId.value = tabId;	
}

function paparSoalan(namaHadir) {
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "maklumatSimati";
	document.${formName}.namaPenanya.value = namaHadir;
	document.${formName}.submit();
}

function maklumatSimati(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.namaPenanya.value = "0";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "maklumatSimati";
	document.${formName}.submit();
}

function maklumatKehadiran(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "maklumatKehadiran";
	document.${formName}.submit();
}


function maklumatPerbicaraan(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.questioner.value = "0";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "papar_bicara";
	document.${formName}.submit();
}

function maklumatPemohon(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "maklumatPemohon";
	document.${formName}.submit();
}

function maklumatWaris(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "maklumatWaris";
	document.${formName}.submit();
}

function tambahKehadiran(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "tambahKehadiran";
	document.${formName}.submit();
}

function ambilKeterangan()
{
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
	document.${formName}.command.value = "ambilKeterangan";
	document.${formName}.submit();
	}

function ambilKeteranganPopup()
{   
	//alert("cetakNilaiHarta2");
   	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupKeterangan";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}

</script>
<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>