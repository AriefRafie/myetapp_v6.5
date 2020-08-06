
	<!-- SEKSYEN 4 DAN 8 --><strong></strong>

	
	

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")
	
	<input name="id_borange" type="hidden" id="id_borange" value="$!id_borange"/> 
	
   <input name="report" type="hidden" id="report" value="$!report"/>
   <input name="id_fail" type="hidden" id="id_fail" value="$!id_fail"/> 
   <input name="no_fail" type="hidden" id="no_fail" value="$!no_fail"/> 
   <input name="id_permohonan" type="hidden" id="id_permohonan" value="$!id_permohonan"/> 
   <input name="id_pegawai" type="hidden" id="id_pegawai" value="$!id_pegawai"/> 
   <input name="nama2Mukim" type="hidden" id="nama2Mukim" value="$!nama2Mukim"/> 
   
   <input name="nama_pegawai" type="hidden" id="nama_pegawai" value="$!nama_pegawai"/> 
   <input name="nama_pegawai1" type="hidden" id="nama_pegawai1" value="$!nama_pegawai1"/> 
   <input name="nama_pegawai2" type="hidden" id="nama_pegawai2" value="$!nama_pegawai2"/> 
   
   
   
   <input name="jawatan" type="hidden" id="jawatan" value="$!jawatan"/> 
   <input name="nama_pengarah" type="hidden" id="nama_pengarah" value="$!nama_pengarah"/> 
   <input name="id_tanahumum" type="hidden" id="id_tanahumum" value="$!id_tanahumum"/> 
   <input name="id_siasatan" type="hidden" id="id_siasatan" value="$!id_siasatan"/> 
   <input name="id_jawatan" type="hidden" id="id_jawatan" value="$!id_jawatan"/> 
   <input name="id_hakmilik" type="hidden" id="id_hakmilik" value="$!id_hakmilik"/> 
   <input name="listLOT" type="hidden" id="listLOT" value="$!listLOT"/> 
   <input name="listLOTHM" type="hidden" id="listLOTHM" value="$!listLOTHM"/> 
   <input name="id_negeri" type="hidden" id="id_negeri" value="$!id_negeri"/> 
   <input name="totalHM" type="hidden" id="totalHM" value="$!totalHM"/> 
   <input name="flagJenisSuratCara" type="hidden" id="flagJenisSuratCara" value="$!flagJenisSuratCara"/> 
   
   <input name="flagCetakJPBD" type="hidden" id="flagCetakJPBD" value="$!flagCetakJPBD"/> 
   <input name="flagCetakSiasatan" type="hidden" id="flagCetakSiasatan" value="$!flagCetakSiasatan"/> 
   
   <input name="no_rujukan_ptg" type="hidden" id="no_rujukan_ptg" value="$!no_rujukan_ptg"/> 
   <input name="no_rujukan_ptd" type="hidden" id="no_rujukan_ptd" value="$!no_rujukan_ptd"/> 
   <input name="no_rujukan_upt" type="hidden" id="no_rujukan_upt" value="$!no_rujukan_upt"/> 
   <input name="selectNoFail" type="hidden" id="selectNoFail" value="$!selectNoFail"/>
   
   <input name="bydate" type="hidden" id="bydate" value="$!bydate"/>
   
   <input name="flagShowTarikhCetak" type="hidden" id="flagShowTarikhCetak" value="$!flagShowTarikhCetak"/>
   
   <input name="id_mmk" type="hidden" id="id_mmk" value="$!id_mmk"/>
   <input name="id_permintaanukur" type="hidden" id="id_permintaanukur" value="$!id_permintaanukur"/>
   
   <!-- BANTAHAN -->
   <input name="id_bantahan" type="hidden" id="id_bantahan" value="$!id_bantahan"/>
   <input name="id_hakmilikpb" type="hidden" id="id_hakmilikpb" value="$!id_hakmilikpb"/> 
   <input name="id_bayaran" type="hidden" id="id_bayaran" value="$!id_bayaran"/> 
   
   
   <input name="id_buktipenyampaian" type="hidden" id="id_buktipenyampaian" value="$!id_buktipenyampaian"/> 
   


    	<fieldset><legend><strong>Cetakan Laporan</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
        	
              <tr>
              	<td width="1%">&nbsp;</td>
                <td width="30%">&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td width="68%">&nbsp;</td>
              </tr>
              
              <!-- RADIO BUTTON SELECT FILE -->
              
              
               #if($report == 'MMKSek8WPKL' || $report == 'MMKSek4WPKL')
                 
             
                  <tr>
                    <td>&nbsp;</td>
                    <td>Bil. Kertas</td>
                    <td>:</td>
                    <td><input type="text" id="txtBilKertas" name="txtBilKertas" value="$!txtBilKertas" maxlength="50" size="30" /></td>
                  </tr>
                  
                  <tr>
                    <td>&nbsp;</td>
                    <td>Bil. Salinan</td>
                    <td>:</td>
                    <td><input type="text" id="txtBilSalinan" name="txtBilSalinan" value="$!txtBilSalinan" maxlength="50" size="30" /></td>
                  </tr>
                 
                  
              #end    
              
              #if($selectNoFail == 'yes')
              
              <tr>
                <td valign="top"><font color="red">*</font></td>
                <td valign="top">No. Fail</td>
                <td valign="top">:</td>
                <td><select name="sorSelectNoFail" class="autoselect">
      		
		      			#if($sorSelectNoFail=="1")
						<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="4">NO. RUJUKAN UPT</option>
						<option value="">SILA PILIH</option>
		      			#elseif($sorSelectNoFail=="2")
		      			<option value="2">NO. RUJUKAN PTG</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="4">NO. RUJUKAN UPT</option>
						<option value="">SILA PILIH</option>
		 				#elseif($sorSelectNoFail=="3")
		 				<option value="3">NO. RUJUKAN PTD</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="4">NO. RUJUKAN UPT</option>
						<option value="">SILA PILIH</option>
		 				#elseif($sorSelectNoFail=="4")
		 				<option value="4">NO. RUJUKAN UPT</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="">SILA PILIH</option>
						#else
		      			<option value="">SILA PILIH</option>
		      			<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="4">NO. RUJUKAN UPT</option>
		      			#end
		      			
					</select></td>
              </tr>
              
              #else
              
            #if($report != "BorangC_TGANU" && $report != "BorangCLebih_TGANU" && $report != "CoveringPU_SA" && $report != "coveringSijilPU_SA" && $report != "minitSijilPU_SA")  
              <tr>
                <td>&nbsp;</td>
                <td>No. Fail Permohonan</td>
                <td>:</td>
                <td>$!no_fail.toUpperCase()</td>
              </tr>
              
              #if($no_rujukan_ptg!="")
              <tr>
                <td>&nbsp;</td>
                <td>No. Rujukan PTG</td>
                <td>:</td>
                <td>$!no_rujukan_ptg.toUpperCase()</td>
              </tr>
              #end
              
              #if($no_rujukan_ptd!="")
              <tr>
                <td>&nbsp;</td>
                <td>No. Rujukan PTD</td>
                <td>:</td>
                <td>$!no_rujukan_ptd.toUpperCase()</td>
              </tr>
              #end
              
              #if($no_rujukan_upt!="")
              <tr>
                <td>&nbsp;</td>
                <td>No. Rujukan UPT</td>
                <td>:</td>
                <td>$!no_rujukan_upt.toUpperCase()</td>
              </tr>
			  #end
			  #end 	
              #end	
            
              #if($report != 'MBSelangor' && $report != 'MBPerak' && $report != 'MBSelangorSS8' && $report != 'MMKSek4NSembilan'
              && $report != 'MMKSek4Selangor' && $report != 'MMKSek4Melaka' && $report != 'MMKSek8Selangor' && $report != 'MMKSek8NSembilan'
              && $report != 'MMKSek8Melaka' && $report != 'MMKSek4WPKL' && $report != 'MMKSek8WPKL' && $report != 'MMKSek4Kedah' && $report != 'MMKSek4Perak'
              && $report != 'MMKSek8Perak' && $report != 'MMKSek8PPinang' && $report != 'MMKSek8Kedah' && $report != 'BorangDBI'
              && $report != 'MMKSek4Perlis' && $report != 'MMKSek8Perlis' && $report != 'MMKSek4Terengganu' && $report != 'MMKSek8Terengganu'
              && $report != 'MMKSek8Kelantan' && $report != 'MMKSek8Pahang' && $report != 'MMKSek4Kelantan' && $report != 'SementaraMMKSelangor' 
              && $report != 'SementaraMMKKL' && $report != 'BorangM' && $report != 'laporanTanahSS8' && $report != 'BorangA' 
              && $report != 'BorangB'  && $report != 'LaporanTerperinciTanah' && $report != 'BorangD' && $report != 'LampiranA1'
              && $report != 'Perkara4PEKELILING' && $report != 'BuktiPenyampaian' && $report != 'BuktiPenyampaianH' && $report != 'BuktiPenyampaianK' && $report != 'BorangE' && $report != 'BorangF'
              && $report != 'BorangG' && $report != 'BorangH' && $report != 'SuratAkuanPenerimaanCek' && $report != 'SuratAkuanPenerimaanCekBayaranLewat'
              && $report != 'BorangK' && $report != "BorangC_TGANU" && $report != "BorangCLebih_TGANU"   
              && $report != 'NotaSiasatanSek8' && $report != 'borangAkta486' && $report != 'KertasMinitMB' && $report != 'BuktiPenyampaianRamai' && $report != 'BuktiPenyampaianRamaiH' && $report != 'BuktiPenyampaianRamaiK' 
              && $report != 'BorangL' && $report != 'SuratAkuanPenerimaanCekLainKos' && $report != 'borangAkta486Penarikan' 
              && $report != 'coveringPU' && $report != 'coveringPU_SA' && $report != 'borangPU' && $report != 'lampiranAPU' && $report != 'lampiranBPU'
              && $report != 'BorangL' && $report != 'SuratEndorsanBorangK' && $report != 'SuratIringanAgensiPemohon' 
              && $report != 'coveringSijilPU' && $report != 'minitSijilPU' && $report != 'cetakNotis' && $report != 'senaraiKehadiran' 
              && $report != 'senaraiKehadiranKosong' && $report != 'BayaranLainKos_Nofail' && $report != 'sabpn_notis_awam_sek4' && $report != 'sabpn_notis_awam_sek8'  && $report != 'sabpn_notis_borange'  
              && $report != 'sabpn_notis_borangk'  && $report != 'sabpn_notis_borangh')
<!--               || $report == 'SuratPengosonganTanah' || -->
              <!-- PPT-27 & PPT-30-->
              <tr>
                <td><font color="red">*</font></td>
                <td>Nama Pegawai</td>
                <td>:</td>
                <td>$!selectPegawai</td>
              </tr>
              #end
              
              #if($report == 'BatalEndosDPTG'  || $report == 'BatalEndosDPTD')
              <tr>
                <td></td>
                <td>Bahagian</td>
                <td>:</td>
                <td><input type="text" id="bahagian" name="bahagian" value="" size="50" /></td>
              </tr>
              <tr>
                <td></td>
                <td>Untuk Perhatian</td>
                <td>:</td>
                <td><input type="text" id="untuk_perhatian" name="untuk_perhatian" value="" size="50" /></td>
              </tr>
              #end
              
              
               #if($report == 'sabpn_notis_awam_sek4' || $report == 'sabpn_notis_awam_sek8'  || $report == 'sabpn_notis_borange'  || $report == 'sabpn_notis_borangk' || $report == 'sabpn_notis_borangh') 
              <tr>
			<td ><font color="red">*</font></td>
			<td >Nama Penghantar Notis</td>
			<td >:</td>
			<td >
            <select name="selectPenghantarNotis" class="autoselect" id="selectPenghantarNotis"  onchange="setNamaPenghantar(this.value)" >
                                        <option value="" >SILA PILIH</option>
                                  #foreach($ln in $listPenghantarNotis)                                 
                              <option value="$ln.NAMA_PEGAWAI">$ln.NAMA_PEGAWAI</option>
                             #end
                                        </select>
            <input type="text" name="txtNamaHantar" id="txtNamaHantar" value="" size="47" maxlength="80"   ></td>
		    </tr>
            
            #if($report == 'sabpn_notis_borange' || $report == 'sabpn_notis_borangk' || $report == 'sabpn_notis_borangh')
             <tr>
                	<td>&nbsp;</td>
                	<td>Keterangan Waktu Penghantaran</td>
                	<td>:</td>
                	<td><input type="text" id="keterangan_waktu_hantar" name="keterangan_waktu_hantar" value="" size="50" /></td>
              </tr>
              #end
            
            <tr>
                	<td>&nbsp;</td>
                	<td>Tempat</td>
                	<td>:</td>
                	<td><input type="text" id="tempatSAPBN" name="tempatSAPBN" value="" size="50" /></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Negeri</td>
                	<td>:</td>
                	<td><input type="text" id="negeriSAPBN" name="negeriSAPBN" value="" size="50" /></td>
              </tr>
              #end
              
              
               #if($report == 'BorangF') 
               <tr>
                <td>&nbsp;</td>
                <td valign="top">Julat Paparan Borang F</td>
                <td valign="top">:</td>
                <td valign="top">
               
                Mula : <input type="text" id="mulaPB" name="mulaPB" value="" maxlength="30" size="20" onkeyup="removeChar(this,this.value);" onblur="removeChar(this,this.value);"  /> Sehingga : 
                <input type="text" id="akhirPB" name="akhirPB" value="" maxlength="30" size="20" onkeyup="removeChar(this,this.value);" onblur="removeChar(this,this.value);"  />
                <br />
                <font color="blue">Fungsi ini disediakan untuk mencetak Borang F yang memiliki pihak berkepentingan yang sangat ramai. Pengguna hanya perlu mencetaknya secara kelompok. 
                <br /><br />
                Contohnya : <br />Jika Borang F memiliki 100 pihak berkepentingan, penguna perlu masukkan <b>mula : 1 & sehingga : 50</b> dan cetak bagi kelompok pertama. <br />Kemudian masukkan <b>mula : 51 & sehingga : 100</b> dan cetak bagi kelompok kedua. 
                <br /><br />
                Tujuan utama fungsi ini adalah meringankan cetakan Borang F yang memiliki senarai pihak berkepentingan yang sangat ramai.
                <br /><br />
                ** Jika Borang F tidak memiliki ramai pihak berkepentingan. Sila kosongkan medan mula dan sehingga ini. Cetak seperti biasa.
                </font> 
                
                </td>
              </tr>           
               #end 
               
               
                #if($report == 'BorangH') 
               <tr>
                <td>&nbsp;</td>
                <td valign="top">Julat Paparan Borang H</td>
                <td valign="top">:</td>
                <td valign="top">
               
                Mula : <input type="text" id="mulaPB" name="mulaPB" value="" maxlength="30" size="20" onkeyup="removeChar(this,this.value);" onblur="removeChar(this,this.value);"  /> Sehingga : 
                <input type="text" id="akhirPB" name="akhirPB" value="" maxlength="30" size="20" onkeyup="removeChar(this,this.value);" onblur="removeChar(this,this.value);"  />
                <br />
                <font color="blue">Fungsi ini disediakan untuk mencetak Borang H yang memiliki pihak berkepentingan yang sangat ramai. Pengguna hanya perlu mencetaknya secara kelompok. 
                <br /><br />
                Contohnya : <br />Jika Borang H memiliki 100 pihak berkepentingan, penguna perlu masukkan <b>mula : 1 & sehingga : 50</b> dan cetak bagi kelompok pertama. <br />Kemudian masukkan <b>mula : 51 & sehingga : 100</b> dan cetak bagi kelompok kedua. 
                <br /><br />
                Tujuan utama fungsi ini adalah meringankan cetakan Borang H yang memiliki senarai pihak berkepentingan yang sangat ramai.
                <br /><br />
                ** Jika Borang H tidak memiliki ramai pihak berkepentingan. Sila kosongkan medan mula dan sehingga ini. Cetak seperti biasa.
                </font> 
                
                </td>
              </tr>           
               #end 
              
              #if($report == 'coveringSijilPU' || $report == 'coveringSijilPU_SA')              
               <tr>
                <td>&nbsp;</td>
                <td>PTG / PTD</td>
                <td>:</td>
                <td>
                #set($array_type_pej = ["PTD","PTG"])
                
                <select  class="autoselect" name="type_pej" id="type_pej"  >
					#foreach ( $y in $array_type_pej )
						   		                  
					<option value="$y"  >
                    	#if($y == "PTD")
                        	PTD
                        #elseif($y == "PTG")
                            PTG                   
                        #end                           
                    </option>
					#end
				</select>
                
                </td>
              </tr>              
              #end
              
              #if($report == 'MMKSek8WPKL' || $report == 'MMKSek4WPKL')
                 
              #else
              
               #if($jawatan!="")
                  <tr>
                    <td>&nbsp;</td>
                    <td>Jawatan</td>
                    <td>:</td>
                    <td>$!jawatan</td>
                  </tr>
                  #end
                  
              #end    
                  
              
              #if($report == 'SuratSiasatanKpdAP')
              <tr>
                <td>&nbsp;</td>
                <td>Emel</td>
                <td>:</td>
                <td><input type="text" id="txtEmel" name="txtEmel" value="$!emel" maxlength="50" size="30" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No. Telefon</td>
                <td>:</td>
                <td><input type="text" id="txtTelefon" name="txtTelefon" value="" maxlength="50" size="30" /></td>
              </tr>
              #end
              
              #if($report == 'SuratCajLewat')
              <tr>
                <td>&nbsp;</td>
                <td>Untuk Perhatian</td>
                <td>:</td>
                <td><input type="text" id="txtUP" name="txtUP" value="" maxlength="30" size="20" /></td>
              </tr>
              #end
              
              
              #if($report == 'SuratIringanMMK')
              <tr>
                <td>&nbsp;</td>
                <td>Bil Rujukan Kami</td>
                <td>:</td>
                <td><input type="text" id="txtBilRujukanKami" name="txtBilRujukanKami" value="" maxlength="5" size="5" /></td>
              </tr>
              
              #if($!id_negeri != '2')
              <tr>
                <td>&nbsp;</td>
                <td>Bil Rujukan Tuan</td>
                <td>:</td>
                <td><input type="text" id="txtBilRujukanTuan" name="txtBilRujukanTuan" value="" maxlength="5" size="5" /></td>
              </tr>
              #end
              
              #end
              
              #if($report == 'MMKSek8Pahang')
              <tr>
                	<td>&nbsp;</td>
                	<td>Yang Ke</td>
                	<td>:</td>
                	<td><input type="text" id="txtYangKe" name="txtYangKe" value="" maxlength="50" size="30" /></td>
              </tr>
              #end
              
              #if($report == 'MMKSek8PPinang' || $report == 'MMKSek8Kelantan' || $report == 'MMKSek8Pahang' || $report == 'MMKSek4Kelantan')
              <tr>
                	<td>&nbsp;</td>
                	<td>Kertas Bil.</td>
                	<td>:</td>
                	<td><input type="text" id="txtKertasBil" name="txtKertasBil" value="" maxlength="20" size="15" /></td>
              </tr>
              #end
              
              #if($report == 'MMKSek8Perlis')
              <tr>
                	<td>&nbsp;</td>
                	<td>Persidangan</td>
                	<td>:</td>
                	<td><input name="txtPersidangan" value="" maxlength="100" size="50" id="txtPersidangan" type="text"></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Masa</td>
                	<td>:</td>
                	<td><input name="txtMasa" value="" maxlength="20" size="20" id="txtMasa" type="text"></td>
              </tr>
              #end
              
              #if($report == 'MMKSek8Pahang' || $report == 'MMKSek8Perlis')
              <tr>
                	<td>&nbsp;</td>
                	<td>Tarikh</td>
                	<td>:</td>
                	<td><input name="txdTarikh" value="" maxlength="30" size="20" id="txdTarikh" type="text" ></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Tempat</td>
                	<td>:</td>
                	<td><input type="text" id="txtTempat" name="txtTempat" value="" maxlength="100" size="50" /></td>
              </tr> 
              #end
              
              #if($report == 'MBSelangor' || $report == 'MBSelangorSS8' || $report == 'MMKSek4NSembilan'
              || $report == 'MMKSek4Selangor' || $report == 'MMKSek8Selangor' || $report == 'MMKSek8NSembilan' || $report == 'MMKSek8PPinang'
              || $report == 'MMKSek4Perlis' || $report == 'MMKSek8Pahang' || $report == 'SementaraMMKSelangor' || $report == 'SementaraMMKKL'  || $report == 'sabpn_notis_awam_sek4' || $report == 'sabpn_notis_awam_sek8' || $report == 'sabpn_notis_borange' || $report == 'sabpn_notis_borangk' || $report == 'sabpn_notis_borangh')
              <tr>
                	<td>&nbsp;</td>
                	<td>Bil</td>
                	<td>:</td>
                	<td><input type="text" id="txtBil" name="txtBil" value="" maxlength="5" size="5" /></td>
              </tr>
              #end
              
              
              #if($report == 'MMKSek8Kelantan' || $report == 'MMKSek4Kelantan')
              <tr>
                	<td>&nbsp;</td>
                	<td>Nama Penolong Pengarah</td>
                	<td>:</td>
                	<td>$!selectPPengarah</td>
              </tr>
              
              <tr>
                	<td>&nbsp;</td>
                	<td>Nama Pentadbir Tanah dan Jajahan</td>
                	<td>:</td>
                	<td><input type="text" id="txtNamaPentadbirJajahan" name="txtNamaPentadbirJajahan" value="" maxlength="50" size="40" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>
              #end
         
              
              #if($report == 'MMKSek4Kedah' || $report == 'MMKSek8Kedah')
              <tr>
                	<td>&nbsp;</td>
                	<td>Kertas No.</td>
                	<td>:</td>
                	<td><input type="text" id="txtKertasNo" name="txtKertasNo" value="" maxlength="20" size="15" /></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Pegawai Yang Menandatangan</td>
                	<td>:</td>
                	<td><input type="text" id="txtPegawaiTT" name="txtPegawaiTT" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="" size="50" /></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Jawatan Pegawai Yang Menandatangan</td>
                	<td>:</td>
                	<td><input type="text" id="txtJawatanPegawaiTT" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" name="txtJawatanPegawaiTT" value="" size="50" /></td>
              </tr>
               <tr>
                	<td>&nbsp;</td>
                	<td>Jawatan Bagi Pihak (Jika Ada) <br />cth : B.P. PENGARAH TANAH DAN GALIAN</td>
                	<td>:</td>
                	<td><input type="text" id="txtBPJawatanPegawaiTT" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" name="txtBPJawatanPegawaiTT" value="" size="50" /></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Bersamaan (Tarikh Dalam Bulan Islam)</td>
                	<td>:</td>
                	<td><input type="text" id="txtTarikhBulanIslam" name="txtTarikhBulanIslam" value="" size="50" /></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Tarikh</td>
                	<td>:</td>
                	<td><input type="text" id="txtTarikhTT" name="txtTarikhTT" value="" size="50" /></td>
              </tr>
              #end
              
              #if($report == 'MBSelangor' || $report == 'MBPerak' || $report == 'MBSelangorSS8' || $report == 'MMKSek4Perak' || $report == 'MMKSek8Perak'
              || $report == 'MMKSek8NSembilan' || $report == 'MMKSek4NSembilan')
              <tr>
                	<td>&nbsp;</td>
                	<td>Nama Menteri Besar</td>
                	<td>:</td>
                	<td><input type="text" id="txtNamaMB" name="txtNamaMB" value="" maxlength="50" size="40" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Nama Pengarah Tanah dan Galian</td>
                	<td>:</td>
                	<td><input type="text" id="txtNamaPTG" name="txtNamaPTG" value="" maxlength="50" size="40" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>
              
              #if($report != 'MMKSek4Perak' && $report != 'MMKSek8Perak' && $report != 'MMKSek8NSembilan' && $report != 'MMKSek4NSembilan')
              <tr>
                	<td>&nbsp;</td>
                	<td>No. Pelan Permohonan</td>
                	<td>:</td>
                	<td><input type="text" id="txtNoPelan" name="txtNoPelan" value="" maxlength="50" size="40" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>
              #end
              
              #end
              
              #if($report == 'MMKSek4Melaka' || $report == 'MMKSek8Melaka' || $report == 'BorangDBI'
              || $report == 'MMKSek4Terengganu' || $report == 'MMKSek8Terengganu' 
              || $report == 'borangAkta486' || $report == 'borangAkta486Penarikan')
              <tr>
                	<td>&nbsp;</td>
                	<td>Nama Pengarah Tanah dan Galian</td>
                	<td>:</td>
                	<td><input type="text" id="txtNamaPTG" name="txtNamaPTG" value="" maxlength="50" size="40" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>
              #end
              
              #if($report == 'MMKSek8Kelantan' || $report == 'MMKSek4Kelantan')
               <tr>
                	<td>&nbsp;</td>
                	<td>Nama Pengarah Tanah dan Galian</td>
                	<td>:</td>
                	<td>$!selectPengarah</td>
              </tr>
              #end
              
              #if($report == 'MMKSek8Selangor'  || $report == 'MMKSek8PPinang' || $report == 'SementaraMMKSelangor' || $report == 'SementaraMMKKL' )
              <tr>
                	<td>&nbsp;</td>
                	<td>Nama Pentadbir Tanah</td>
                	<td>:</td>
                	<td><input type="text" id="txtNamaPentadbir" name="txtNamaPentadbir" value="" maxlength="50" size="30" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>
              #end
              
              
              #if( $report == 'MMKSek4WPKL' || $report == 'MMKSek8WPKL'  )
              <tr>
                	<td>&nbsp;</td>
                	<td>Nama Pentadbir Tanah</td>
                	<td>:</td>
                	<td><input type="text" id="txtNamaPentadbir" name="txtNamaPentadbir" value="$!txtNamaPentadbir" maxlength="50" size="30" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>
              #end
              
              #if( ($report == 'Perkara4JPPH') || ($report == 'SuratIringanBorangO') || ($report == 'SuratIringanBorangO_AP') 
              || ($report == 'PerancangJPPH') || ($report == 'SuratKepadaJPPHSupayaMenghadiriPerundingan') 
              || ($report == 'SuratPanggilanPerundinganSementara')
              || ($report == 'SuratPanggilanPerundinganSambunganSementara') 
              || ($report == 'SiasatanJPPH') || ($report == 'JPBDkeJPPH'))

              <tr>
                <td><font color="red">*</font></td>
                <td>Pejabat JPPH</td>
                <td>:</td>
                <td>$!selectPejabatJPPH</td>
              </tr>
              #end
              
              #if($report == 'IringanARB' )
              <tr>
                <td><font color="red">*</font></td>
                <td>Pejabat Amanah Raya Berhad</td>
                <td>:</td>
                <td>$!selectPejabatARB</td>
              </tr>
              #end
              
              #if($report == 'BorangM' )
              <tr>
                <td>&nbsp;</td>
                <td>Nama Pengarah Negeri</td>
                <td>:</td>
                <td><input type="text" id="txtNamaPengarahNeg" name="txtNamaPengarahNeg" value="" maxlength="50" size="30" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>              
              #end
              
              
              
 
             #if( $report == "BorangC_TGANU" || $report == "BorangCLebih_TGANU" || $report == "MMKSek8Perlis")
              
              <tr>
                	<td>&nbsp;</td>
                	<td>Disediakan oleh :</td>
                	<td>:</td>
                	<td><input type="text" id="disediakan" name="disediakan" value="" maxlength="100" size="50" /></td>
              </tr>
              
                  <tr>
                	<td>&nbsp;</td>
                	<td>Disediakan oleh (JAWATAN) :</td>
                	<td>:</td>
                	<td><input type="text" id="jawatan_disediakan" name="jawatan_disediakan" value="" maxlength="100" size="50" /></td>
              </tr>
              
              
              <tr>
                	<td>&nbsp;</td>
                	<td>Disemak oleh :</td>
                	<td>:</td>
                	<td><input type="text" id="disemak" name="disemak" value="" maxlength="100" size="50" /></td>
              </tr>
              
                  <tr>
                	<td>&nbsp;</td>
                	<td>Disemak oleh (JAWATAN) :</td>
                	<td>:</td>
                	<td><input type="text" id="disemak_jawatan" name="disemak_jawatan" value="" maxlength="100" size="50" /></td>
              </tr>
              #end
              
              #if( $report == "MMKSek8Perlis")
             
              <tr>
                	<td>&nbsp;</td>
                	<td>Diluluskan oleh :</td>
                	<td>:</td>
                	<td><input type="text" id="dilulus" name="dilulus" value="" maxlength="100" size="50" /></td>
              </tr>
              
                  <tr>
                	<td>&nbsp;</td>
                	<td>Diluluskan oleh (JAWATAN) :</td>
                	<td>:</td>
                	<td><input type="text" id="dilulus_jawatan" name="dilulus_jawatan" value="" maxlength="100" size="50" /></td>
              </tr>
              
              #end
              
              
              
               #if($report == "MMKSek8Perlis" )
               <tr>
                <td valign="top"></td>
                <td valign="top">No. Ruj. Fail</td>
                <td valign="top">:</td>
                <td><select name="sorSelectNoFail" class="autoselect">
      		
		      			#if($sorSelectNoFail=="1")
						<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="4">NO. RUJUKAN UPT</option>
						<option value="">SILA PILIH</option>
		      			#elseif($sorSelectNoFail=="2")
		      			<option value="2">NO. RUJUKAN PTG</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="4">NO. RUJUKAN UPT</option>
						<option value="">SILA PILIH</option>
		 				#elseif($sorSelectNoFail=="3")
		 				<option value="3">NO. RUJUKAN PTD</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="4">NO. RUJUKAN UPT</option>
						<option value="">SILA PILIH</option>
		 				#elseif($sorSelectNoFail=="4")
		 				<option value="4">NO. RUJUKAN UPT</option>
		 				<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="">SILA PILIH</option>
						#else
		      			<option value="">SILA PILIH</option>
		      			<option value="1">NO. RUJUKAN JKPTG</option>
						<option value="2">NO. RUJUKAN PTG</option>
						<option value="3">NO. RUJUKAN PTD</option>
						<option value="4">NO. RUJUKAN UPT</option>
		      			#end
		      			
					</select></td>
              </tr>
              
              #end
              
              
              
              
              
              #if($report == 'SuratMintaBayaran'||$report=='Surat_Iringan_Mohon_Bayaran')
              
                #set($eftemail = "")
              	#if($id_negeri=='10')
              		#set($eftemail = "eftselangor@kptg.gov.my")
              	#end
              <tr>
                	<td>&nbsp;</td>
                	<td>No.Telefon</td>
                	<td>:</td>
                	<td><input type="text" id="txtNotel" name="txtNotel" value="" maxlength="50" size="30" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Emel</td>
                <td>:</td>
                <td><input type="text" id="txtEmel" name="txtEmel" value="$!emel" maxlength="50" size="30" /></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Emel EFT</td>
                	<td>:</td>
                	<td><input type="text" id="txtEmelEFT" name="txtEmelEFT" value="$!eftemail" maxlength="50" size="30" /></td>
              </tr>
              #end
              
              
              #if($report == 'SuratSiasatanKpdAP' )
              <tr>
                	<td>&nbsp;</td>
                	<td>Untuk Perhatian (KJP)</td>
                	<td>:</td>
                	<td><input type="text" id="txtUntukPerhatianKJP" name="txtUntukPerhatianKJP" value="" maxlength="50" size="30" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>
              <tr>
                	<td>&nbsp;</td>
                	<td>Untuk Perhatian (PTD)</td>
                	<td>:</td>
                	<td><input type="text" id="txtUntukPerhatianPTD" name="txtUntukPerhatianPTD" value="" maxlength="50" size="30" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" /></td>
              </tr>
              #end
              
     		  
     		  #if($flagShowTarikhCetak=="yes")
     		  <tr>
                	<td>&nbsp;</td>
                	<td>
                    
                    #if($report == 'MMKSek8WPKL' || $report == 'MMKSek4WPKL')
                    Tarikh Kertas MMK Dicetak
                    #else
                    
                    
                    
                    Tarikh Surat Dicetak
                    
                    #end
                    </td>
                	<td>:</td>
                	<td><input type="text" id="txtTarikhSuratCetak" name="txtTarikhSuratCetak" value="$!dateToday" maxlength="30" size="20" /></td>
              </tr>

              #if($report == 'BorangDBI')
              <tr>
                	<td colspan="3">&nbsp;</td>
                	<td><input type="text" id="txtTarikhSuratCetakBI" name="txtTarikhSuratCetakBI" value="$!sysdateEng" maxlength="30" size="25" /></td>
              </tr>
              #end
              
              
              
              
              
              
              
              
              
     		  #end
              
              
              #if($report == "MMKSek8WPKL" || $report == "MMKSek4WPKL")
              
               <tr>
                <td></td>
                <td>Disediakan oleh</td>
                <td>:</td>
                <td>$!selectPegawai</td>
              </tr>
              
              <tr>
                <td></td>
                <td>Disemak oleh</td>
                <td>:</td>
                <td>$!selectPegawai1</td>
              </tr>
              
               <tr>
                <td></td>
                <td>Ditaip oleh</td>
                <td>:</td>
                <td>$!selectPegawai2</td>
              </tr>
              
              #end
              
              
              
     		   #if($report == "BorangL")
     		  <tr>
                	<td>&nbsp;</td>
                	<td>Tarikh Borang L</td>
                	<td>:</td>
                	<td><input type="text" id="tarikhBorangL" name="tarikhBorangL" value="$!tarikhBorangL" maxlength="30" size="20" /></td>
              </tr>
              
               <tr>
                	<td>&nbsp;</td>
                	<td>Tempoh (Bil. Hari)</td>
                	<td>:</td>
                	<td><input type="text" id="txtBilHariBorangL" name="txtBilHariBorangL" value="$!tempohBL" maxlength="30" size="10" /></td>
              </tr>
              #end
            </table>
            
            
            
        </fieldset>
        
        #if($report == 'SuratMintaBayaran' || (($report == 'BorangG' || $report == 'BorangH') && ($showG_MT=='yes' || $showG_ARB=='yes'))||$report=='Surat_Iringan_Mohon_Bayaran' || $report=='SuratBayaranAgensi')
        
        <fieldset>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">            
            #if($report == 'SuratMintaBayaran' || (($report == 'BorangG' || $report == 'BorangH') && $showG_MT=='yes')||$report=='Surat_Iringan_Mohon_Bayaran' || $report=='SuratBayaranAgensi')
            	<tr class="table_header">
            		<td colspan="4"><b>Maklumat Akaun Mahkamah Tinggi</b></td>
            	</tr>
            	<tr>
            		<td>&nbsp;</td>
            		<td>Nama Bank</td>
            		<td>:</td>
            		<td><input type="text" id="txtNamaBankMT" name="txtNamaBankMT" value="$!namaBankMT" maxlength="80" size="60"  /></td>
            	</tr>
            	<tr>
            		<td width="1%">&nbsp;</td>
            		<td width="30%">Nama Akaun</td>
            		<td width="1%">:</td>
            		<td width="68%"><input type="text" id="txtNamaAkaunMT" name="txtNamaAkaunMT" value="$!namaMT" maxlength="80" size="50"  /></td>
            	</tr>
            	<tr>
            		<td>&nbsp;</td>
            		<td>Nombor Akaun</td>
            		<td>:</td>
            		<td><input type="text" id="txtNomborAkaunMT" name="txtNomborAkaunMT" value="$!noAkaunMT" maxlength="80" size="30"  /></td>
            	</tr>            	
            	
            #end
            #if($report == 'SuratMintaBayaran' || (($report == 'BorangG' || $report == 'BorangH') && $showG_ARB=='yes')||$report=='Surat_Iringan_Mohon_Bayaran' || $report=='SuratBayaranAgensi')	
            	<tr class="table_header">
            		<td colspan="4"><b>Maklumat Akaun Amanah Raya Berhad</b></td>
            	</tr>
            	<tr>
            		<td>&nbsp;</td>
            		<td>Nama Bank</td>
            		<td>:</td>
            		<td><input type="text" id="txtNamaBankARB" name="txtNamaBankARB" value="$!namaBankARB" maxlength="80" size="60"  /></td>
            	</tr>
            	<tr>
            		<td width="1%">&nbsp;</td>
            		<td width="30%">Nama Akaun</td>
            		<td width="1%">:</td>
            		<td width="68%"><input type="text" id="txtNamaAkaunARB" name="txtNamaAkaunARB" value="$!namaARB" maxlength="80" size="50"  /></td>
            	</tr>
            	<tr>
            		<td>&nbsp;</td>
            		<td>Nombor Akaun</td>
            		<td>:</td>
            		<td><input type="text" id="txtNomborAkaunARB" name="txtNomborAkaunARB" value="$!noAkaunARB" maxlength="80" size="30"  /></td>
            	</tr>
            #end	
            #if($report == 'SuratMintaBayaran' || (($report == 'BorangG' || $report == 'BorangH') && ($showG_MT=='yes' || $showG_ARB=='yes')))
       		<tr>
            <td></td>
            		<td></td>
            		<td></td>
            <td ><input type="button" name="simpanAcc" id="simpanAcc" value="Simpan Maklumat Akaun" onclick="javascript:saveAcc();"></td></tr>
           #end
            </table>
        </fieldset>
        #end
        
        #if($report == 'SuratSiasatanKpdAP' && $flagCetakSiasatan == 'projek' )
        <fieldset>
        
        	<table width="40%" border="0" cellspacing="2" cellpadding="2">
        	
        		<tr class="table_header">
		        	<td align="center" width="10%"><b>No</b></td>
		        	<td><b>Tarikh - Tarikh Siasatan</b></td> 
		        </tr>
			   
	      #if($listTarikhSiasatan.size()!=0)
	
	           	#foreach($listT in $listTarikhSiasatan)
	
	                #set( $i = $velocityCount )
	         		#if ( ($i % 2) != 1 )	
	              		 #set( $row = "row2" )
	         		#else	
	               		 #set( $row = "row1" )	
	         		#end	
	        	<tr>
	        		<td class="$row" align="center">$listT.bil</td>
	            	<td class="$row">$!listT.tarikh2Siasatan</td>	            		
	        	</tr>
	        	#end	
	     #else	
	        	<tr>
	        		<td colspan="2">Tiada rekod</td>
	        	<tr>	
	     #end
        	
        	</table>
        
        </fieldset>
        
        <fieldset>
        	
        	<table width="60%" border="0" cellspacing="2" cellpadding="2">
        		<tr>
                	<td width="40%">Tarikh Siasatan Pada Surat</td>
                	<td width="1%">:</td>
                	<td width="59"><input type="text" id="txdTarikhsiasatan" name="txdTarikhsiasatan" value="$!lbltarikhSiastan" maxlength="100" size="30" /></td>
              </tr>
        	</table>
        	
        </fieldset>
        #end
        
        #if($report == 'MMKSek8Perak')
       
        
        #set($multipleEkar = $Utils.parseDouble($!totalLuasAmbil_nonformat) * 2.47105381)
        #set($totalLuasEkar = $Utils.formatLuasHM($!multipleEkar))
        
        
        
        #set($catatanKeputusanMB="Dilulus / Ditolak pengambilan tanah di atas sebahagian "+$!totalHM+" lot daripada tanah bermilik seluas lebih kurang "+$!totalLuasAmbil+" hektar ("+$!totalLuasEkar+" ekar) di dalam "+$!namaMukimInit+", Daerah "+$!projek_daerah+" yang dinyatakan dalam Borang 'C' seperti yang ditunjukkan kawasan berwarna merah pada surihan (2A) dlm."+$!no_rujukan_ptg+" untuk tujuan "+$!namaProjek+" di bawah Seksyen 3(1) (a) Akta Pengambilan Tanah 1960, diisytiharkan pengambilan tanah tersebut di bawah Seksyen 8 Akta yang sama dan dikeluarkan Sijil Perakuan Segera di bawah Seksyen 19 Akta yang sama jika diperlukan mengikut perwakilan kuasa yang diisytiharkan dalam Warta Kerajaan Negeri Perak No. 1067 bertarikh 30 Ogos, 1962 dan Warta Kerajaan Negeri Perak No. 1013 bertarikh 24 Oktober 1996 di bawah peruntukan Delegation of Power Ordinance No. 56 of 1956. ")
     
        <fieldset>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
        	 	<tr>
              		<td width="1%">&nbsp;</td>
                	<td width="30%">Keputusan MB Perak</td>
                	<td width="1%">&nbsp;</td>
                	<td width="68%">&nbsp;</td>
              	</tr>
              	
              	<tr>
         	  		<td valign="top">&nbsp;</td>
           	   		<td colspan="3"><textarea name="txtKeputusanMB" id="txtKeputusanMB" cols="110%" rows="20" >$!catatanKeputusanMB</textarea></td>
            	</tr>
            
            </table>  
        </fieldset>      
        #end
        
        <table align="center" width="70%" border="0" cellspacing="2" cellpadding="2">
        	<tr align="center">
                <td>
                
                	<!-- REPORT SEKSYEN 4 & 8 ------------->
                
                	#if($report == 'LampiranASek4')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakLampiranA('$!id_fail','$!nama2Mukim','$!nama_pegawai','$!jawatan')">
                	#end
                
                	#if($report == 'LampiranASek8')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakLampiranA8('$!id_fail','$!nama2Mukim','$!nama_pegawai','$!jawatan')">
                	#end
                	
                	#if($report == 'MBSelangor')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMBSelangor('$!id_fail','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'MBPerak')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMBPerak('$!id_fail','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'MBSelangorSS8')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMBSelangorSS8('$!id_fail','$!namaMukimInit')">
                	#end
                	
                	#if($report == 'KertasMinitMB')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakKertasMinitMB('$!id_fail','$!nama_pengarah')">
                	#end
                	
                	#if($report == 'laporanTanahSS4')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakLaporanTanah('$!id_tanahumum','$!nama_pegawai')">
                	#end
                	
                	#if($report == 'NotaSiasatanSek8')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakNotaSiasatan('$!id_siasatan','$!nama_pengarah','$!nama_pegawai','$!id_hakmilik')">
                	#end
                	
                	#if($report == 'IringanARB')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakIringanARB('$!id_siasatan','$!id_jawatan','$!nama_pegawai')">
                	#end
                	
                	#if($report == 'Perkara4JPPH')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakJPPH('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'Perkara1JPBD')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPerkara1JPBD('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim','$!totalHM')">
                	#end
                	
                	#if($report == 'Perkara2PTD')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPerkara2PTD('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'Perkara3KSU')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPerkara3KSU('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'EndosanDSUK')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakEndosanDSUK('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                    
                    #if($report == 'BatalEndosDPTG')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBatalEndosDPTG('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                    
                    #if($report == 'BatalEndosDPTD')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBatalEndosDPTD('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'wartaPNMB')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakWartaPNMB('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'borangAkta486')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangAkta486('$!id_fail','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'borangAkta486Penarikan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangAkta486Penarikan('$!id_fail','$!id_mmk','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'EndosanDPTD')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakEndosanDPTD('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'EndorsanKPTD')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakEndorsanKPTD('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim','$!listLOT')">
                	#end
                	
                	#if($report == 'EndorsanKHQ')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakEndorsanKHQ('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim','$!listLOTHM')">
                	#end
                	
                	#if($report == 'EndorsanKSUK')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakEndorsanKSUK('$!id_fail','$!nama_pegawai','$!id_jawatan','$!nama2Mukim','$!listLOT')">
                	#end
                	
                	#if($report == 'EndorsanKSUKSeluruh')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakEndorsanKSUKSeluruh('$!id_fail','$!nama_pegawai','$!id_jawatan')">
                	#end
                	
                	#if($report == 'SuratSiasatanKpdAP')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSiasatanAP('$!id_fail','$!id_borange','$!nama_pegawai','$!id_jawatan','$!flagCetakSiasatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'SuratKpdAPPBUlangSiasatan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSiasatanAPPBUlang('$!id_fail','$!id_hakmilik','$!nama_pegawai','$!id_jawatan')">
                	#end
                	
                	#if($report == 'SuratKpdPBUlangSiasatan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSiasatanPBUlang('$!id_fail','$!id_hakmilik','$!nama_pegawai','$!id_jawatan')">
                	#end
                	
                	#if($report == 'Afidavit_Perintah')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakAfidavitPerintah('$!id_hakmilikpb')">
                	#end
                	
                	#if($report == 'Afidavit_ExParte')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakAfidavitExParte('$!id_hakmilikpb')">
                	#end
                	
                	#if($report == 'Afidavit')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakAfidavit('$!id_hakmilikpb')">
                	#end
                	
                	#if($report == 'Afidavit_SijilPerakuan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakAfidavitSijilPerakuan('$!id_hakmilikpb')">
                	#end
                	
                	<!-- PPT-26 (ii) -->                	
                	#if($report == 'Surat_Iringan_Afidavit')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanAfidavit('$!id_hakmilikpb','$!id_negeri','$!id_fail','$!id_jawatan','$!no_fail','$!nama_pegawai')">
                	#end
                	
                	#if($report == 'Surat_Iringan_Mohon_Bayaran')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanMohonBayaran('$!id_hakmilikpb','$!id_negeri','$!id_fail','$!id_jawatan','$!no_fail','$!nama_pegawai')">
                	#end
                	
                	#if($report == 'Surat_Iringan_Pembayaran')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanPembayaran('$!id_hakmilikpb','$!id_fail','$!nama_pegawai','$!id_jawatan','$!no_fail','$!id_negeri')">
                	#end
                	<!-- PPT-26 (ii) END-->
                	
                	#if($report == 'PerancangJPPH')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPerancangJPPH('$!id_fail','$!id_permohonan','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'JPBDkeJPPH')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakJPBDkeJPPH('$!id_permohonan','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'SiasatanJPPH')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSiasatanJPPH('$!id_permohonan','$!nama_pegawai','$!id_jawatan','$!id_borange')">
                	#end
                	
                	#if($report == 'SuratAPBayarTambahan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratAPBayarTambahan('$!id_hakmilik','$!nama_pegawai','$!id_jawatan')">
                	#end
                	
                	#if($report == 'SuratMintaBayaran')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMohonBayaran('$!id_hakmilik','$!nama_pegawai','$!id_jawatan','$!nama2Mukim','$!id_negeri')">
                	#end
                	
                	#if($report == 'SuratPBBayarBalik')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPUBayarBalik('$!id_hakmilikpb','$!nama_pegawai','$!id_jawatan','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'SuratLainKos')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratLainKos('$!id_hakmilik','$!nama_pegawai','$!id_jawatan')">
                	#end
                    
                    #if($report == 'BayaranLainKos_Nofail')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBayaranLainKosKedah('$!id_hakmilik')">
                	#end
                
                	#if($report == 'MMKSek4Selangor')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek4Selangor('$!id_fail','$!nama_pengarah','$!no_fail','$!id_negeri')">
                	#end
                
                	#if($report == 'MMKSek4Melaka')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek4Melaka('$!id_fail','$!nama2Mukim','$!no_fail')">
                	#end
                	
                	#if($report == 'EndorsanKPTDSeluruh')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakEndorsanKPTDSeluruh('$!id_fail','$!nama_pegawai','$!id_jawatan')">
                	#end
                	
                	#if($report == 'MMKSek8Selangor')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8Selangor('$!id_fail','$!nama_pengarah','$!no_fail','$!id_negeri')">
                	#end
                	
                	#if($report == 'MMKSek4NSembilan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek4NSembilan('$!id_fail','$!nama2Mukim','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek8NSembilan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8NSembilan('$!id_fail','$!nama2Mukim','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek8Melaka')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8Melaka('$!id_fail','$!nama2Mukim','$!no_fail')">
                	#end
                    
                    #if($report == 'MMKSek8WPKL')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8WPKL('$!id_fail','$!no_fail','$!nama_pegawai','$!nama_pegawai1','$!nama_pegawai2')">
                	#end
                	
                	#if($report == 'MMKSek4WPKL')
                	
                    <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek4WPKL('$!id_fail','$!no_fail','$!nama_pegawai','$!nama_pegawai1','$!nama_pegawai2','$!id_mmk')">
                	#end
                	
                	#if($report == 'MMKSek4Kedah')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek4Kedah('$!id_fail','$!nama_pengarah','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek4Perak')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek4Perak('$!id_fail','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek8Perak')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8Perak('$!id_fail','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek8PPinang')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8PPinang('$!id_fail','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek8Kedah')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8Kedah('$!id_fail','$!nama_pengarah','$!no_fail')">
                	#end
                	
                	#if($report == 'BorangDBI')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangDBI('$!id_fail','$!nama2Mukim','$!totalHM','$!selectNoFail')">
                	#end
                	
                	#if($report == 'MMKSek4Perlis')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek4Perlis('$!id_fail','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek8Perlis')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8Perlis('$!id_fail','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek4Terengganu')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek4Terengganu('$!id_fail','$!nama2Mukim','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek8Terengganu')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8Terengganu('$!id_fail','$!nama2Mukim','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek8Kelantan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8Kelantan('$!id_fail','$!nama2Mukim','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek8Pahang')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek8Pahang('$!id_fail','$!no_fail')">
                	#end
                	
                	#if($report == 'MMKSek4Kelantan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMMKSek4Kelantan('$!id_fail','$!nama2Mukim','$!no_fail')">
                	#end
                	
                	#if($report == 'SuratIringanMMK')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanMMK('$!id_fail','$!nama_pegawai','$!id_jawatan','$!id_negeri')">
                	#end
                	
                	#if($report == 'LampiranA1')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratJPBD('$!id_permohonan','$!nama2Mukim','$!flagCetakJPBD')">
                	#end
                	
                	#if($report == 'BuktiPenyampaian')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBuktiPenyampaian('$!id_hakmilik','$!flagJenisSuratCara','$!id_borange')">
                	#end
                    
                    #if($report == 'BuktiPenyampaianH')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBuktiPenyampaianH('$!id_hakmilik','$!flagJenisSuratCara')">
                	#end
                    
                    #if($report == 'BuktiPenyampaianK')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBuktiPenyampaianK('$!id_hakmilik','$!flagJenisSuratCara')">
                	#end
                	
                	#if($report == 'BuktiPenyampaianRamai')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBuktiPenyampaianRamai('$!id_hakmilik','$!flagJenisSuratCara','$!id_borange')">
                	#end
                    #if($report == 'BuktiPenyampaianRamaiH')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBuktiPenyampaianRamaiH('$!id_hakmilik','$!flagJenisSuratCara')">
                	#end
                    
                     #if($report == 'BuktiPenyampaianRamaiK')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBuktiPenyampaianRamaiK('$!id_hakmilik','$!flagJenisSuratCara')">
                	#end
                	
                	#if($report == 'SuratKpdPTGDariJKPTGIbuPejabat')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratKPTG('$!selectNoFail','$!id_fail','$!nama_pegawai','$!id_jawatan')">
                	#end
                	
                	#if($report == 'cetakNotis')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakNotis('$!selectNoFail','$!id_permohonan','$!nama_pengarah')">
                	#end
                	
                	#if($report == 'senaraiKehadiran')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSenaraiKehadiran('$!selectNoFail','$!id_permohonan')">
                	#end
					<!-- PPT-27 -->
                	#if($report == 'senaraiKehadiranKosong')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSenaraiKehadiranKosong('$!selectNoFail','$!id_permohonan')">
                	#end
                	<!-- PPT-30 -->
                    #if($report == 'BuktiPenyampaianL')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBuktiPenyampaianL('$!id_hakmilik','$!flagJenisSuratCara')">
                	#end
                	
                    #if($report == 'SuratPengosonganTanah')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPengosonganTanah('$!id_permohonan','$!id_fail','$!id_hakmilik','$!nama_pegawai')">
                	#end
                	
                	#if($report == 'SuratEndorsanBorangK')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratEndorsanBorangK('$!selectNoFail','$!id_permohonan')">
                	#end
                	
                	#if($report == 'SuratIringanAgensiPemohon')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak1" onclick="javascript:cetakSuratIringanAgensiPemohon('$!selectNoFail','$!id_permohonan')">
                	#end
                	<!-- PPT-30 END -->
                	#if($report == 'laporanTanahSS8')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakLaporanAwalSS8('$!id_tanahumum','$!id_negeri')">
                	#end
                	
                	#if($report == 'BorangA')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangA('$!id_fail','$!nama2Mukim')">
                	#end
                	
                	#if($report == 'BorangB')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangB('$!id_fail','$!nama_pengarah')">
                	#end
                	
                	#if($report == 'LaporanTerperinciTanah')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakLaporanTanahSS8('$!id_fail','$!id_hakmilik')">
                	#end
                	
                	#if($report == 'BorangD')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangD('$!id_fail','$!totalHM')">
                	#end
                    
                    #if($report == 'BorangDLebih')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangDLebih('$!id_fail','$!totalHM')">
                	#end
                	
                	#if($report == 'Perkara4PEKELILING')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakPerkara4PEKELILING('$!id_fail')">
                	#end
                	
                	#if($report == 'BorangE')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangE('$!id_borange','$!nama_pengarah')">
                	#end
                	
                	#if($report == 'BorangF')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangF('$!id_hakmilik','$!nama_pengarah')">
                	#end
                	
                	#if($report == 'BorangG')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangG('$!id_permohonan','$!id_fail','$!id_hakmilik','$showG_MT','$showG_ARB','$bydate')">
                	#end
                	
                	#if($report == 'BorangH')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangH('$!id_fail','$!id_hakmilik','$!id_negeri','$!showG_MT','$!showG_ARB')">
                	#end
                	
                	#if($report == 'SuratPanggilPBTerimaPampasan')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilPBTerimaPampasan('$!id_fail','$!id_bayaran','$!nama_pengarah','$!nama_pegawai','$!id_jawatan')">
                	#end
                	
                	#if($report == 'SuratPanggilPBTerimaPampasan_LainKos')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilPBTerimaPampasan_LainKos('$!id_fail','$!id_bayaran','$!nama_pengarah','$!nama_pegawai','$!id_jawatan')">
                	#end
                	
                	#if($report == 'SuratAkuanPenerimaanCek')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratAkuanPenerimaanCek('$!id_bayaran')">
                	#end
                	
                	#if($report == 'SuratAkuanPenerimaanCekBayaranLewat')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratAkuanPenerimaanCekBayaranLewat('$!id_bayaran')">
                	#end
                	
                	#if($report == 'SuratAkuanPenerimaanCekLainKos')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratAkuanPenerimaanCekLainKos('$!id_bayaran')">
                	#end
                	
                	#if($report == 'suratMaklumanSerahBayaranPampasanKpdAPSEK8')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratMaklumanSerahBayaranPampasanKpdAP('$!id_fail','$!nama_pegawai')">
                	#end
                	
                	#if($report == 'SuratCajLewat')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratCajLewat('$!id_hakmilik','$!nama_pegawai','$!id_jawatan')">
                	#end
                	
                	#if($report == 'BorangK')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangK('$!id_fail','$!id_hakmilik')">
                	#end
                	
                	#if($report == 'BorangL')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangL('$!id_hakmilik','$!totalHM')">
                	#end
                	
                	#if($report == 'coveringPU')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakCoveringPU('$!id_permintaanukur','$!nama_pengarah')">
                	#end
                    
                    
                    #if($report == 'CoveringPU_SA') 
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakCoveringPU_SA('$!id_permintaanukur','$!nama_pengarah')">
                	#end
                    
                    #if($report == 'coveringSijilPU_SA') 
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakCoveringSijilPU_SA('$!id_permintaanukur','$!nama_pengarah')">
                	#end
                    
                    #if($report == 'minitSijilPU_SA') 
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMinitSijilPU_SA('$!id_permintaanukur','$!nama_pengarah')">
                	#end
                	
                	#if($report == 'lampiranAPU')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakLampiranAPU('$!id_permintaanukur')">
                	#end
                	
                	#if($report == 'lampiranBPU')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakLampiranBPU('$!id_permohonan')">
                	#end
                	
                	#if($report == 'coveringSijilPU')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakCoveringSijilPU('$!id_permintaanukur','$!nama_pengarah')">
                	#end
                	
                	#if($report == 'minitSijilPU')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMinitSijilPU('$!id_permintaanukur','$!nama_pengarah')">
                	#end
                	
                    <!-- END REPORT SEKSYEN 4 & 8 --------->
                    
                    
                    <!-- REPORT SEMENTARA -------------->
                    
                    #if($report == 'SementaraMMKSelangor')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSementaraMMKSelangor('$!id_fail','$!nama_pengarah','$!no_fail','$!id_negeri')">
                	#end
                    
                    #if($report == 'SementaraMMKKL')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSementaraMMKKL('$!id_fail','$!nama_pengarah','$!no_fail','$!id_negeri')">
                	#end  
                    
                    #if($report == 'suratUtkPanggilanTerimaPampasanKpdPB')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetaksuratUtkPanggilanTerimaPampasanKpdPB('$!id_fail','$!id_bayaran','$!nama_pegawai')">
                	#end                                      
                    
                    #if($report == 'suratMaklumanSerahBayaranPampasanKpdAP')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetaksuratMaklumanSerahBayaranPampasanKpdAP('$!id_fail','$!id_bayaran','$!nama_pegawai')">
                	#end  
                    
                    #if($report == 'BorangM')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangM('$!id_fail','$!id_hakmilikpb')">
                	#end                                       
                    
                    <!-- PPT 43i-->
                    #if($report == 'SuratBayaranAgensi')
                	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMohonBayaranAgensi('$!id_fail','$!nama_pegawai','$!no_fail','$!id_hakmilik')">
                	#end
                    
                    <!-- END REPORT SEMENTARA ------------->
                    
                    
                    <!-- REPORT BANTAHAN ------------------>
                    
                	#if($report == 'SuratMintaDepositDalam30Hari')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratMintaDepositDalam30Hari('$!id_fail','$id_bantahan','$id_hakmilikpb','$!nama_pegawai')">
                	#end  
                    
                	#if($report == 'SuratMintaDepositDalam30HariAP')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratMintaDepositDalam30HariAP('$!id_fail','$id_bantahan','$id_hakmilik','$id_siasatan','$!nama_pegawai')">
                	#end                                              
                    
                	#if($report == 'SuratIringanBorangO_AP')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanBorangO_AP('$!id_fail','$id_bantahan','$id_hakmilik','$!nama_pegawai')">
                	#end                                                        
                    
                	#if($report == 'SuratIringanBorangO')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanBorangO('$!id_fail','$id_bantahan','$id_hakmilikpb','$!nama_pegawai','$!id_jawatan')">
                	#end 
                    
                    #if($report == 'suratKepadaAPSupayaMembayarPampasanTambahan')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetaksuratKepadaAPSupayaMembayarPampasanTambahan('$!id_fail','$id_bantahan','$!nama_pegawai','$!id_negeri')">
                	#end 
                    
                    #if($report == 'suratKepadaAPSupayaMembayarPampasanTambahan_AP')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetaksuratKepadaAPSupayaMembayarPampasanTambahan_AP('$!id_fail','$id_bantahan','$!nama_pegawai')">
                	#end 
                    
                    #if($report == 'suratUtkPanggilanTerimaPampasanKpdPB_bantahan')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetaksuratUtkPanggilanTerimaPampasanKpdPB_bantahan('$!id_bayaran','$id_fail','$!nama_pegawai')">
                	#end      
                    
                    #if($report == 'suratMaklumanSerahBayaranPampasanKpdAP_bantahan')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetaksuratMaklumanSerahBayaranPampasanKpdAP_bantahan('$id_bayaran','$!id_fail','$id_hakmilikpb','$!nama_pegawai')">
                	#end                
                           
                	#if($report == 'SuratKepadaJPPHSupayaMenghadiriPerundingan')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratKepadaJPPHSupayaMenghadiriPerundingan('$!id_fail','$!nama_pegawai')">
                	#end  
                    
                    #if($report == 'SuratPanggilanPerundinganSementara')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilanPerundinganSementara('$!id_fail','$!nama_pegawai','$!id_siasatan','$!id_jawatan','$id_hakmilik')">
                	#end  
                    
                    #if($report == 'SuratPanggilanPerundinganSementaraAgensi')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilanPerundinganSementaraAgensi('$!id_fail','$!nama_pegawai','$!id_siasatan','$!id_jawatan','$id_hakmilik')">
                	#end  
                    
                    #if($report == 'SuratPanggilanPerundinganSementaraSambunganAgensi')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilanPerundinganSementaraSambunganAgensi('$!id_fail','$!nama_pegawai','$!id_siasatan','$!id_jawatan','$id_hakmilik')">
                	#end 
                    
                    #if($report == 'SuratPanggilanPerundinganSementaraSambunganPB')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilanPerundinganSementaraSambunganPB('$!id_fail','$!nama_pegawai','$!id_siasatan','$!id_jawatan','$id_hakmilik')">
                	#end   
                    
                    #if($report == 'SuratPanggilanPerundinganSementaraPB')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilanPerundinganSementaraPB('$!id_fail','$!nama_pegawai','$!id_siasatan','$!id_jawatan','$id_hakmilik')">
                	#end  
                    
                    #if($report == 'SuratPanggilanPerundinganSambunganSementara')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPanggilanPerundinganSambunganSementara('$!id_fail','$!nama_pegawai','$!id_siasatan','$!id_jawatan','$id_hakmilik')">
                	#end    
                    
                	#if($report == 'SuratKpdAPRundingan')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratKpdAPRundingan('$!id_fail','$id_hakmilik','$!nama_pegawai')">
                	#end    
                    
                	#if($report == 'AkuanPenerimaanCek_bantahan')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakAkuanPenerimaanCek_bantahan('$id_bayaran','$!id_fail','$id_hakmilikpb','$id_bantahan','$!nama_pegawai')">
                	#end    
                    
                    #if($report == 'BorangC_TGANU')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:BorangC_TGANU('$!id_fail')">
                	#end    
                    
                    
                    #if($report == 'BorangCLebih_TGANU')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:BorangCLebih_TGANU('$!id_fail')">
                	#end     
                    
                    #if($report == 'sabpn_notis_awam_sek4')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:sabpn_notis_awam_sek4('$!id_permohonan')">
                	#end 
                    
                    #if($report == 'sabpn_notis_awam_sek8')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:sabpn_notis_awam_sek8('$!id_permohonan')">
                	#end  
                    
                    #if($report == 'sabpn_notis_borange')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:sabpn_notis_borange('$!id_buktipenyampaian','$!id_permohonan')">
                	#end                                                               
                      
                      
                    #if($report == 'sabpn_notis_borangk')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:sabpn_notis_borangk('$!id_buktipenyampaian','$!id_permohonan')">
                	#end     
                    
                    
                    #if($report == 'sabpn_notis_borangh')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:sabpn_notis_borangh('$!id_borangh','$!id_permohonan')">
                	#end                   
                	
                	<!-- PPT-11 -->
                	#if($report == 'suratPelupusanHakmilik')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Surat Pelupusan" onclick="javascript:suratPelupusanHakmilik('$!id_hakmilik','$!id_fail', '$!id_permohonan', '$!bilLot')">
                	#end
                    
                    <!-- END REPORT BANTAHAN -------------->
                    
               		<input type="button" name="cmdKeluar" id="cmdKeluar" value="Keluar" onclick="javascript:keluar()">
               		
                </td>
              </tr>
        </table>




<!-- Untuk borang dan surat -->
<script>

function setNamaPenghantar(val){
	//alert("VAL :"+val);
	document.${formName}.txtNamaHantar.value = val;
}

function removeChar(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric3(content);
		return;
	}
}


function saveAcc() {
	document.${formName}.command.value = "simpanAcc";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupPilihPegawaiReportView"; 
	document.${formName}.submit();
}


function RemoveNonNumeric3( strString )
{
      var strValidCharacters = "1234567890.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}


function cetakSenaraiKehadiran(selectNoFail,idpermohonan) {

	if (selectNoFail == "yes" && document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		
		

		var url = "../../servlet/ekptg.report.ppt.senaraiKehadiran?id_permohonan="+idpermohonan+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

//PPT-27
function cetakSenaraiKehadiranKosong(selectNoFail,idpermohonan) {

	if (selectNoFail == "yes" && document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		
		

		var url = "../../servlet/ekptg.report.ppt.senaraiKehadiranKosong?id_permohonan="+idpermohonan+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakNotis(selectNoFail,idpermohonan,nama_pegawai) {

	if (selectNoFail == "yes" && document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.cetakNotisUmum?id_permohonan="+idpermohonan+"&nama_pegawai="+nama_pegawai+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function sabpn_notis_awam_sek4(id_permohonan)
{
	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if (document.${formName}.txtNamaHantar.value == ""){
		alert("Sila masukan nama penghantar notis.");
		document.${formName}.txtNamaHantar.focus(); 
		return;
	}
	else{
		
		var namaPenghantar = document.${formName}.txtNamaHantar.value;
		var txtBil = document.${formName}.txtBil.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var tempatSAPBN = document.${formName}.tempatSAPBN.value;
		var negeriSAPBN = document.${formName}.negeriSAPBN.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SABPN_S4?id_permohonan="+id_permohonan+"&namaPenghantar="+namaPenghantar+"&no_fail="+nofail+"&bil_surat="+txtBil+"&tempatSAPBN="+tempatSAPBN+"&negeriSAPBN="+negeriSAPBN;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function sabpn_notis_awam_sek8(id_permohonan)
{
	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if (document.${formName}.txtNamaHantar.value == ""){
		alert("Sila masukan nama penghantar notis.");
		document.${formName}.txtNamaHantar.focus(); 
		return;
	}
	else{
		
		var namaPenghantar = document.${formName}.txtNamaHantar.value;
		var txtBil = document.${formName}.txtBil.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var tempatSAPBN = document.${formName}.tempatSAPBN.value;
		var negeriSAPBN = document.${formName}.negeriSAPBN.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SABPN_S8?id_permohonan="+id_permohonan+"&namaPenghantar="+namaPenghantar+"&no_fail="+nofail+"&bil_surat="+txtBil+"&tempatSAPBN="+tempatSAPBN+"&negeriSAPBN="+negeriSAPBN;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
	
}


function sabpn_notis_borange(id_buktipenyampaian,id_permohonan)
{
	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if (document.${formName}.txtNamaHantar.value == ""){
		alert("Sila masukan nama penghantar notis.");
		document.${formName}.txtNamaHantar.focus(); 
		return;
	}
	else{
		
		var namaPenghantar = document.${formName}.txtNamaHantar.value;
		var txtBil = document.${formName}.txtBil.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var tempatSAPBN = document.${formName}.tempatSAPBN.value;
		var negeriSAPBN = document.${formName}.negeriSAPBN.value;
		var keterangan_waktu_hantar = document.${formName}.keterangan_waktu_hantar.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SABPN_BORANGE?id_permohonan="+id_permohonan+"&id_buktipenyampaian="+id_buktipenyampaian+"&namaPenghantar="+namaPenghantar+"&no_fail="+nofail+"&bil_surat="+txtBil+"&tempatSAPBN="+tempatSAPBN+"&negeriSAPBN="+negeriSAPBN+"&keterangan_waktu_hantar="+keterangan_waktu_hantar;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
	
}

function sabpn_notis_borangk(id_buktipenyampaian,id_permohonan)
{
	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if (document.${formName}.txtNamaHantar.value == ""){
		alert("Sila masukan nama penghantar notis.");
		document.${formName}.txtNamaHantar.focus(); 
		return;
	}
	else{
		
		var namaPenghantar = document.${formName}.txtNamaHantar.value;
		var txtBil = document.${formName}.txtBil.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var tempatSAPBN = document.${formName}.tempatSAPBN.value;
		var negeriSAPBN = document.${formName}.negeriSAPBN.value;
		var keterangan_waktu_hantar = document.${formName}.keterangan_waktu_hantar.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SABPN_BORANGK?id_permohonan="+id_permohonan+"&id_buktipenyampaian="+id_buktipenyampaian+"&namaPenghantar="+namaPenghantar+"&no_fail="+nofail+"&bil_surat="+txtBil+"&tempatSAPBN="+tempatSAPBN+"&negeriSAPBN="+negeriSAPBN+"&keterangan_waktu_hantar="+keterangan_waktu_hantar;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
	
}

function sabpn_notis_borangh(id_borangh,id_permohonan)
{
	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if (document.${formName}.txtNamaHantar.value == ""){
		alert("Sila masukan nama penghantar notis.");
		document.${formName}.txtNamaHantar.focus(); 
		return;
	}
	else{
		
		var namaPenghantar = document.${formName}.txtNamaHantar.value;
		var txtBil = document.${formName}.txtBil.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var tempatSAPBN = document.${formName}.tempatSAPBN.value;
		var negeriSAPBN = document.${formName}.negeriSAPBN.value;
		var keterangan_waktu_hantar = document.${formName}.keterangan_waktu_hantar.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SABPN_BORANGH?id_permohonan="+id_permohonan+"&id_borangh="+id_borangh+"&namaPenghantar="+namaPenghantar+"&no_fail="+nofail+"&bil_surat="+txtBil+"&tempatSAPBN="+tempatSAPBN+"&negeriSAPBN="+negeriSAPBN+"&keterangan_waktu_hantar="+keterangan_waktu_hantar;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakMinitSijilPU(id_permintaanukur,nama_pengarah) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.minitSijilPU?id_permintaanukur="+id_permintaanukur+"&namaPengarah="+nama_pengarah+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakCoveringSijilPU(id_permintaanukur,nama_pengarah) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var tp = document.${formName}.type_pej.value;
		
		var url = "../../servlet/ekptg.report.ppt.coveringSijilPU?id_permintaanukur="+id_permintaanukur+"&typePej="+tp+"&namaPengarah="+nama_pengarah+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakLampiranBPU(id_permohonan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.lampiranBPU?id_permohonan="+id_permohonan+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakLampiranAPU(id_permintaanukur) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.lampiranAPU?id_permintaanukur="+id_permintaanukur+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakCoveringPU(id_permintaanukur,nama_pengarah) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.coveringPU?id_permintaanukur="+id_permintaanukur+"&namaPengarah="+nama_pengarah+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakCoveringPU_SA(id_permintaanukur,nama_pengarah) {

	

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		var nama_pegawai = document.${formName}.nama_pegawai.value;
		var jawatan = document.${formName}.jawatan.value;
		
		
		
		var url = "../../servlet/ekptg.report.ppt.CoveringPU_SA?id_permintaanukur="+id_permintaanukur+"&namaPengarah="+nama_pegawai+"&jawatan="+jawatan+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	
}

function cetakMinitSijilPU_SA(id_permintaanukur,nama_pengarah) {


		//var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		var nama_pegawai = document.${formName}.nama_pegawai.value;
		var jawatan = document.${formName}.jawatan.value;
		
		var url = "../../servlet/ekptg.report.ppt.MinitSijilPU_SA?id_permintaanukur="+id_permintaanukur+"&namaPengarah="+nama_pegawai+"&jawatan="+jawatan+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	
}

function cetakCoveringSijilPU_SA(id_permintaanukur,nama_pengarah) {

	
		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		var nama_pegawai = document.${formName}.nama_pegawai.value;
		var jawatan = document.${formName}.jawatan.value;
		var tp = document.${formName}.type_pej.value; 
		
		
		var url = "../../servlet/ekptg.report.ppt.CoveringSijilPu_SA?id_permintaanukur="+id_permintaanukur+"&typePej="+tp+"&namaPengarah="+nama_pegawai+"&jawatan="+jawatan+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	
}



function cetakBorangL(id_hakmilik,totalhm) {
	
	var bilHariBorangL = document.${formName}.txtBilHariBorangL.value;
	var tarikhBorangL = document.${formName}.tarikhBorangL.value;

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}	else	{

		//alert("ATAS :"+document.${formName}.sorSelectNoFail.value);
		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		//alert("nofail :"+nofail);
		var url = "";
		//if(totalhm > 1){
		//	url = "../../servlet/ekptg.report.ppt.BorangLLebih?id_Fail="+idfail+"&no_fail="+nofail; 
		//}else{
			url = "../../servlet/ekptg.report.ppt.BorangL?id_hakmilik="+id_hakmilik+"&no_fail="+nofail+"&bilHariBorangL="+bilHariBorangL+"&tarikhBorangL="+tarikhBorangL;
		//}
		
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
}
function cetakBorangAkta486Penarikan(id_fail,idmmk,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		
		var namaPTG = document.${formName}.txtNamaPTG.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var url = "../../servlet/ekptg.report.ppt.borangAkta486Penarikan?id_fail="+id_fail+"&id_mmk="+idmmk+"&mukim="+mukim+"&no_fail="+nofail+"&pengarah_ptg="+namaPTG;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakBorangAkta486(idfail,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		
		var namaPTG = document.${formName}.txtNamaPTG.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var url = "../../servlet/ekptg.report.ppt.borangAkta486?id_fail="+idfail+"&mukim="+mukim+"&no_fail="+nofail+"&pengarah_ptg="+namaPTG;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakWartaPNMB(idfail,nama_pegawai,id_jawatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.wartaPNMB?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratAPBayarTambahan(idhakmilik,nama_pegawai,id_jawatan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SuratAPBayarTambahan?ID_HAKMILIK="+idhakmilik+"&namaPegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratPUBayarBalik(idhakmilikpb,nama_pegawai,id_jawatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SuratPBBayarBalik?ID_HAKMILIKPB="+idhakmilikpb+"&namaPegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakEndorsanKPTD(idfail,nama_pegawai,id_jawatan,mukim,listLOT) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.EndorsanKPTD?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_lot="+listLOT+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakEndorsanKPTDSeluruh(idfail,nama_pegawai,id_jawatan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.EndorsanKPTDSeluruh?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakEndorsanKSUK(idfail,nama_pegawai,id_jawatan,mukim,listLOT) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.EndorsanKSUK?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_lot="+listLOT+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakEndorsanKHQ(idfail,nama_pegawai,id_jawatan,mukim,listLOT) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.EndorsanKHQ?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_lot="+listLOT+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakEndorsanKSUKSeluruh(idfail,nama_pegawai,id_jawatan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.EndorsanKSUKSeluruh?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakBorangK(idfail,idhakmilik) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var url = "../../servlet/ekptg.report.ppt.BorangK?id_hakmilik="+idhakmilik+"&id_Fail="+idfail+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
}
function cetakAfidavit(idhakmilikpb) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var id_pegawai = document.${formName}.socPegawai.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.Afidavit?id_hakmilikpb="+idhakmilikpb+"&id_pegawai="+id_pegawai+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakAfidavitExParte(idhakmilikpb) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var id_pegawai = document.${formName}.socPegawai.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.Afidavit_ExParte?id_hakmilikpb="+idhakmilikpb+"&id_pegawai="+id_pegawai+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakAfidavitPerintah(idhakmilikpb) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var id_pegawai = document.${formName}.socPegawai.value;

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.Afidavit_Perintah?id_hakmilikpb="+idhakmilikpb+"&id_pegawai="+id_pegawai+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratCajLewat(idhakmilik,nama_pegawai,id_jawatan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		
		var up = document.${formName}.txtUP.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SuratCajLewat?id_hakmilik="+idhakmilik+"&id_jawatan="+idJawatan+"&u.p.="+up+"&namaPengarah="+nama_pegawai+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
		
	}
	
}
function cetakSuratMaklumanSerahBayaranPampasanKpdAP(idfail,nama_pegawai) {


	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.suratMaklumanSerahBayaranPampasanKpdAP?idFail="+idfail+"&no_fail="+nofail+"&sysdate="+sysdate+"&namaPegawai="+nama_pegawai;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratAkuanPenerimaanCekLainKos(idbayaran) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.SuratAkuanPenerimaanCekLainKos?ID_BAYARAN="+idbayaran+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratAkuanPenerimaanCek(idbayaran) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.SuratAkuanPenerimaanCek?ID_BAYARAN="+idbayaran+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratAkuanPenerimaanCekBayaranLewat(idbayaran) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.SuratAkuanPenerimaanCekBayaranLewat?ID_BAYARAN="+idbayaran+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratPanggilPBTerimaPampasan_LainKos(idfail,idbayaran,nama_pengarah,nama_pegawai,id_jawatan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		
		var url = "../../servlet/ekptg.report.ppt.SuratPanggilPBTerimaPampasan_LainKos?idFail="+idfail+"&idBayaran="+idbayaran+"&namaPegawai="+nama_pegawai+"&no_fail="+nofail+"&id_jawatan='"+id_jawatan+"'";
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratPanggilPBTerimaPampasan(idfail,idbayaran,nama_pengarah,nama_pegawai,id_jawatan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		
		var url = "../../servlet/ekptg.report.ppt.SuratPanggilPBTerimaPampasan?idFail="+idfail+"&idBayaran="+idbayaran+"&namaPegawai="+nama_pegawai+"&no_fail="+nofail+"&id_jawatan='"+id_jawatan+"'";
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratLainKos(idhakmilik,nama_pegawai,id_jawatan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SuratLainKos?id_hakmilik="+idhakmilik+"&namaPengarah="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakBayaranLainKosKedah(idhakmilik) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

	

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.BayaranLainKos?id_hakmilik="+idhakmilik+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}



function cetakBorangH(idfail,idhakmilik,id_negeri,showG_MT,showG_ARB) {
	
	var mula = document.${formName}.mulaPB.value;
	var akhir = document.${formName}.akhirPB.value;


	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var bankMT = "";
		var noMT = "";
		var akaunMT = "";
		var bankARB = "";
		var noARB = "";
		var akaunARB = "";
		var valMT = "no";
		
		if(showG_MT=="yes"){
			bankMT = document.${formName}.txtNamaBankMT.value;
			noMT = document.${formName}.txtNomborAkaunMT.value;
			akaunMT = document.${formName}.txtNamaAkaunMT.value;
			valMT = "yes";
		}

		if(showG_ARB=="yes"){
			bankARB = document.${formName}.txtNamaBankARB.value;
			noARB = document.${formName}.txtNomborAkaunARB.value;
			akaunARB = document.${formName}.txtNamaAkaunARB.value;
		}

		var item = "&valMT="+valMT+"&bankMT="+bankMT+"&noMT="+noMT+"&akaunMT="+akaunMT+"&bankARB="+bankARB+"&noARB="+noARB+"&akaunARB="+akaunARB;
		
		var url = "../../servlet/ekptg.report.ppt.BorangH?id_Fail="+idfail+"&id_hakmilik="+idhakmilik+"&no_fail="+nofail+"&userIdNegeri="+id_negeri+item+"&mula="+mula+"&akhir="+akhir;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakBorangG(idpermohonan,idfail,idhakmilik,showG_MT,showG_ARB,bydate) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var bankMT = "";
		var noMT = "";
		var akaunMT = "";
		var bankARB = "";
		var noARB = "";
		var akaunARB = "";
		var valMT = "no";
		
		if(showG_MT=="yes"){
			bankMT = document.${formName}.txtNamaBankMT.value;
			noMT = document.${formName}.txtNomborAkaunMT.value;
			akaunMT = document.${formName}.txtNamaAkaunMT.value;
			valMT = "yes";
		}

		if(showG_ARB=="yes"){
			bankARB = document.${formName}.txtNamaBankARB.value;
			noARB = document.${formName}.txtNomborAkaunARB.value;
			akaunARB = document.${formName}.txtNamaAkaunARB.value;
		}

		var item = "&valMT="+valMT+"&bankMT="+bankMT+"&noMT="+noMT+"&akaunMT="+akaunMT+"&bankARB="+bankARB+"&noARB="+noARB+"&akaunARB="+akaunARB;
		
		var url = "../../servlet/ekptg.report.ppt.BorangG?id_permohonan="+idpermohonan+"&bydate="+bydate+"&id_Fail="+idfail+"&id_hakmilik="+idhakmilik+"&no_fail="+nofail+item;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakMohonBayaran(idhakmilik,nama_pegawai,id_jawatan,mukim,id_negeri) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var bankMT = document.${formName}.txtNamaBankMT.value;
		var noMT = document.${formName}.txtNomborAkaunMT.value;
		var akaunMT = document.${formName}.txtNamaAkaunMT.value;
		var bankARB = document.${formName}.txtNamaBankARB.value;
		var noARB = document.${formName}.txtNomborAkaunARB.value;
		var akaunARB = document.${formName}.txtNamaAkaunARB.value;
		var noTel = document.${formName}.txtNotel.value;
		var emel = document.${formName}.txtEmel.value;
		
		var emelEFT = document.${formName}.txtEmelEFT.value;
		var sysdate = document.${formName}.txtTarikhSuratCetak.value;

		var item1 = "id_hakmilik="+idhakmilik+"&namaPegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'";
		var item2 = "&mukim="+mukim+"&no_fail="+nofail+"&emel_eft="+emelEFT+"&sysdate="+sysdate; 
		var item3 = "&emel="+emel+"&no_tel="+noTel+"&bankMT="+bankMT+"&noMT="+noMT+"&akaunMT="+akaunMT+"&bankARB="+bankARB+"&noARB="+noARB+"&akaunARB="+akaunARB;
		
		var url = "../../servlet/ekptg.report.ppt.SuratMintaBayaran?"+item1+item2+item3;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSiasatanPBUlang(idfail,idhakmilik,nama_pegawai,id_jawatan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.SuratKpdPBUlangSiasatan?idFail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&idHakmilik="+idhakmilik+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSiasatanAPPBUlang(idfail,idhakmilik,nama_pegawai,id_jawatan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.SuratKpdAPPBUlangSiasatan?idFail="+idfail+"&namaPegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&idHakmilik="+idhakmilik+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakIringanARB(id_siasatan,idjawatan,nama_pegawai) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	else if(document.${formName}.socPejabatARB.value == ""){
		alert('Sila pilih pejabat Amanah Raya Berhad terlebih dahulu.');
  		document.${formName}.socPejabatARB.focus(); 
		return; 
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var id_pejabat = document.${formName}.socPejabatARB.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
    	var url = "../../servlet/ekptg.report.ppt.IringanARB?id_siasatan="+id_siasatan+"&id_pejabat="+id_pejabat+"&id_jawatan="+idjawatan+"&namaPegawai="+nama_pegawai+"&no_fail="+nofail;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakNotaSiasatan(id_siasatan,nama_pengarah,nama_pegawai,id_hakmilik) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
/*	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}*/
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
    	var url = "../../servlet/ekptg.report.ppt.NotaSiasatanSek8?id_siasatan="+id_siasatan+"&namaPegawai=''&namaPengarah=''&no_fail="+nofail+"&id_hakmilik="+id_hakmilik;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakBorangF(idhakmilik,nama_pengarah) {


	
	
	var mula = document.${formName}.mulaPB.value;
	var akhir = document.${formName}.akhirPB.value;


	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var url = "../../servlet/ekptg.report.ppt.BorangF?id_hakmilik="+idhakmilik+"&namaPegawai="+nama_pengarah+"&no_fail="+nofail+"&mula="+mula+"&akhir="+akhir;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakBorangE(id_borange,nama_pengarah) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var id_permohonan = document.${formName}.id_permohonan.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var url = "../../servlet/ekptg.report.ppt.BorangE?id_borange="+id_borange+"&namaPegawai="+nama_pengarah+"&no_fail="+nofail+"&id_permohonan="+id_permohonan;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakBuktiPenyampaianH(idhakmilik,flag) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
		//alert("idhakmilik---- "+idhakmilik);
		var url = "../../servlet/ekptg.report.ppt.BuktiPenyampaianH?idHakmilik="+idhakmilik+"&flagJenisSuratCara="+flag+"&no_fail="+nofail;
		
		//alert("URL :"+url);
		
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakBuktiPenyampaianK(idhakmilik,flag) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
		
		var url = "../../servlet/ekptg.report.ppt.BuktiPenyampaianK?idHakmilik="+idhakmilik+"&flagJenisSuratCara="+flag+"&no_fail="+nofail;
		
		//alert("URL :"+url);
		
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

//PPT-30(i)
function cetakBuktiPenyampaianL(idhakmilik,flag) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;

	}	else	{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
		
		var url = "../../servlet/ekptg.report.ppt.BuktiPenyampaianL?idHakmilik="+idhakmilik+"&flagJenisSuratCara="+flag+"&no_fail="+nofail;
		
		//alert("URL :"+url);
		
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakSuratPengosonganTanah(idpermohonan,idfail,idhakmilik,nama_pegawai) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.SuratPengosonganTanah?id_hakmilik="+idhakmilik+"&id_fail="+idfail+"&no_fail="+nofail+"&nama_pegawai="+nama_pegawai;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakSuratEndorsanBorangK(idhakmilik,idpermohonan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.SuratEndorsanBorangK?ID_BAYARAN="+idbayaran+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakSuratIringanAgensiPemohon(idhakmilik,idpermohonan) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}
		
		var url = "../../servlet/ekptg.report.ppt.SuratIringanAgensiPemohon?ID_BAYARAN="+idbayaran+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
//PPT-30(i) END

function cetakBuktiPenyampaian(idhakmilik,flag,id_borange) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
		
		var url = "../../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&id_borange="+id_borange+"&flagJenisSuratCara="+flag+"&no_fail="+nofail;
		
		//alert("URL :"+url);
		
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakBuktiPenyampaianRamaiH(idhakmilik,flag,id_borange) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var url = "../../servlet/ekptg.report.ppt.BuktiPenyampaianRamaiH?idHakmilik="+idhakmilik+"&flagJenisSuratCara="+flag+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakBuktiPenyampaianRamaiK(idhakmilik,flag,id_borange) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var url = "../../servlet/ekptg.report.ppt.BuktiPenyampaianRamaiK?idHakmilik="+idhakmilik+"&flagJenisSuratCara="+flag+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakBuktiPenyampaianRamai(idhakmilik,flag,id_borange) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var url = "../../servlet/ekptg.report.ppt.BuktiPenyampaianRamai?idHakmilik="+idhakmilik+"&id_borange="+id_borange+"&flagJenisSuratCara="+flag+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSiasatanAP(id_fail,id_borange,nama_pegawai,id_jawatan,flagCetakSiasatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var upPTD = document.${formName}.txtUntukPerhatianPTD.value;
		var upKJP = document.${formName}.txtUntukPerhatianKJP.value;

		var emel = document.${formName}.txtEmel.value;
		var telefon = document.${formName}.txtTelefon.value;


		var tarikh = "";
		var url = "";
		if(flagCetakSiasatan=="projek"){
			tarikh = document.${formName}.txdTarikhsiasatan.value;
		}
		
        var date = encodeURIComponent(tarikh);

        var sysdate = document.${formName}.txtTarikhSuratCetak.value;
        
		var urlItem = "id_Fail="+id_fail+"&id_borange="+id_borange+"&namaPegawai="+nama_pegawai+"&emel="+emel+"&telefon="+telefon+"&id_jawatan='"+idJawatan+"'&no_fail="+nofail+"&up_ptd="+upPTD+"&up_kjp="+upKJP+"&tarikh_siasatan="+date+"&mukim="+mukim+"&sysdate="+sysdate;

		if(flagCetakSiasatan=="projek"){
			url = "../../servlet/ekptg.report.ppt.SuratSiasatanKpdAP_Prmhn?"+urlItem;
		}else{
			url = "../../servlet/ekptg.report.ppt.SuratSiasatanKpdAP?"+urlItem;
		}

		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakEndosanDPTD(idfail,nama_pegawai,id_jawatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.EndosanDPTD?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakEndosanDSUK(idfail,nama_pegawai,id_jawatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.EndosanDSUK?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakBatalEndosDPTG(idfail,nama_pegawai,id_jawatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}
		var untuk_perhatian = document.${formName}.untuk_perhatian.value;
		var bahagian = document.${formName}.bahagian.value;
		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.BatalEndosDPTG?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate+"&untuk_perhatian="+untuk_perhatian+"&bahagian="+bahagian;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakBatalEndosDPTD(idfail,nama_pegawai,id_jawatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var untuk_perhatian = document.${formName}.untuk_perhatian.value;
		var bahagian = document.${formName}.bahagian.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.BatalEndosDPTD?id_fail="+idfail+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate+"&untuk_perhatian="+untuk_perhatian+"&bahagian="+bahagian;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakPerkara4PEKELILING(idfail) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.Perkara4PEKELILING?idFail="+idfail+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakJPPH(idfail,nama_pegawai,id_jawatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	else if(document.${formName}.socPejabatJPPH.value == ""){
		alert('Sila pilih pejabat JPPH terlebih dahulu.');
  		document.${formName}.socPejabatJPPH.focus(); 
		return; 
	}else{

		var id_pejabat = document.${formName}.socPejabatJPPH.value;
		
		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.Perkara4JPPH?idFail="+idfail+"&id_pejabat="+id_pejabat+"&namaPengarah="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakPerkara3KSU(idfail,nama_pegawai,id_jawatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.Perkara3KSU?idFail="+idfail+"&namaPengarah="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakPerkara2PTD(idfail,nama_pegawai,id_jawatan,mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}	

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.Perkara2PTD?idFail="+idfail+"&namaPengarah="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSiasatanJPPH(idpermohonan,nama_pegawai,id_jawatan,id_borange) {

	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	else if(document.${formName}.socPejabatJPPH.value == ""){
		alert('Sila pilih pejabat JPPH terlebih dahulu.');
  		document.${formName}.socPejabatJPPH.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}	
		
		var id_pejabat = document.${formName}.socPejabatJPPH.value;
		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SuratSiasatanJPPH?id_pejabat="+id_pejabat+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&id_borange="+id_borange+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakJPBDkeJPPH(idpermohonan,nama_pegawai,id_jawatan,mukim) {

	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	else if(document.${formName}.socPejabatJPPH.value == ""){
		alert('Sila pilih pejabat JPPH terlebih dahulu.');
  		document.${formName}.socPejabatJPPH.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}	
		
		var id_pejabat = document.${formName}.socPejabatJPPH.value;
		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.JPBDkeJPPH?id_permohonan="+idpermohonan+"&id_pejabat="+id_pejabat+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakPerancangJPPH(idfail,idpermohonan,nama_pegawai,id_jawatan,mukim) {

	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	else if(document.${formName}.socPejabatJPPH.value == ""){
		alert('Sila pilih pejabat JPPH terlebih dahulu.');
  		document.${formName}.socPejabatJPPH.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}	
		
		var id_pejabat = document.${formName}.socPejabatJPPH.value;
		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.PerancangJPPH?idFail="+idfail+"&id_permohonan="+idpermohonan+"&id_pejabat="+id_pejabat+"&nama_pegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratJPBD(idpermohonan,mukim,flagCetakJPBD) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		

		if(flagCetakJPBD=="lot"){
			var url = "../../servlet/ekptg.report.ppt.LampiranA1Lot?id_permohonan="+idpermohonan+"&no_fail="+nofail+"&mukim="+mukim;	
		}else{
			var url = "../../servlet/ekptg.report.ppt.LampiranA1?id_permohonan="+idpermohonan+"&no_fail="+nofail+"&mukim="+mukim;	
		}	
		
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakBorangD(idfail,totalhm) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		

		if(totalhm > 1){
			var url = "../../servlet/ekptg.report.ppt.BorangDLebih?id_Fail="+idfail+"&no_fail="+nofail+"&borang=2";	
		}else{
			var url = "../../servlet/ekptg.report.ppt.BorangD?id_Fail="+idfail+"&no_fail="+nofail+"&borang=2";		
		}
		
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakSuratIringanMMK(idfail,nama_pegawai,id_jawatan,id_negeri) {

	
	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
		
	}else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var bilRujKami = document.${formName}.txtBilRujukanKami.value;

		var bilRujTuan = "";
		if(id_negeri!="2"){
			bilRujTuan = document.${formName}.txtBilRujukanTuan.value;
		}
		
		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}	

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SuratIringanMMK?idFail="+idfail+"&namaPengarah="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&bilRujKami="+bilRujKami+"&bilRujTuan="+bilRujTuan+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
}
function cetakLaporanTanahSS8(idfail,id_hakmilik) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
		
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}	

		var url = "../../servlet/ekptg.report.ppt.LaporanTanah?id_Fail="+idfail+"&idHakmilik="+id_hakmilik+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakPerkara1JPBD(idfail,nama_pegawai,id_jawatan,mukim,totalHM) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
		
	}else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
		
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}	

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.Perkara1JPBD?idFail="+idfail+"&namaPengarah="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate+"&bil_lot="+totalHM;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakBorangB(idfail,namaPengarah) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
		
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.BorangB?idfail="+idfail+"&namaPegawai="+namaPengarah+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}

}
function cetakMBSelangor(idfail,nama2Mukim) {

	var bil = document.${formName}.txtBil.value;
	var namaMB = document.${formName}.txtNamaMB.value;
	var namaPTG = document.${formName}.txtNamaPTG.value;
	var noPelan = document.${formName}.txtNoPelan.value;
	
	var url = "../../servlet/ekptg.report.ppt.MBSelangor?idFail="+idfail+"&namaMenteri="+namaMB+"&namaDato="+namaPTG+"&mukim="+nama2Mukim+"&noPelan="+noPelan+"&bilSurat="+bil;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMBPerak(idfail,nama2Mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
		
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		var namaMB = document.${formName}.txtNamaMB.value;
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		
		
		var url = "../../servlet/ekptg.report.ppt.MBPerak?idFail="+idfail+"&mukim="+nama2Mukim+"&namaDato="+namaMB+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
}
function cetakBorangA(idfail,nama2mukim) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
		
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.BorangA?idfail="+idfail+"&namaMukim="+nama2Mukim+"&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}

}
function cetakLaporanTanah(id_tanahumuum,nama_pegawai) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		

				
		var url = "../../servlet/ekptg.report.ppt.laporanTanahSS4?idTanahumum="+id_tanahumuum+"&namaPegawai="+nama_pegawai+"&no_fail="+nofail;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}

}
function cetakLaporanAwalSS8(id_tanahumum,id_negeri) {

	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		

		var url = "../../servlet/ekptg.report.ppt.laporanTanahSS8?idTanahumum="+id_tanahumum+"&no_fail="+nofail+"&id_negeri="+id_negeri;	
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakMMKSek4Kelantan(idfail,mukim,no_fail) {
	
	var namaPTG = document.${formName}.socPengarah.value;
	var kertasBil = document.${formName}.txtKertasBil.value;
	var penPengarah = document.${formName}.socPPengarah.value;
	var ptanahJajahan = document.${formName}.txtNamaPentadbirJajahan.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek4Kelantan?idFail="+idfail+"&namaPegawai="+penPengarah+"&namaPTD="+ptanahJajahan+"&namaPengarah="+namaPTG+"&mukim="+mukim+"&no_fail="+no_fail+"&bilKertas="+kertasBil;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek8Pahang(idfail,no_fail) {
	
	var kertasBil = document.${formName}.txtKertasBil.value;
	var yangKe = document.${formName}.txtYangKe.value;
	var tarikh = document.${formName}.txdTarikh.value;
	var tempat = document.${formName}.txtTempat.value;
	var bil = document.${formName}.txtBil.value;
	
	  
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek8Pahang?idFail="+idfail+"&no_fail="+no_fail+"&kertasBil="+kertasBil+"&bilSurat="+bil+"&yangKe="+yangKe+"&tarikh="+tarikh+"&tempat="+tempat;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek8Kelantan(idfail,mukim,no_fail) {
	
	var namaPTG = document.${formName}.socPengarah.value;
	var kertasBil = document.${formName}.txtKertasBil.value;
	var penPengarah = document.${formName}.socPPengarah.value;
	var ptanahJajahan = document.${formName}.txtNamaPentadbirJajahan.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek8Kelantan?idFail="+idfail+"&namaPegawai="+penPengarah+"&namaPTD="+ptanahJajahan+"&namaPengarah="+namaPTG+"&mukim="+mukim+"&no_fail="+no_fail+"&bilKertas="+kertasBil;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek8Terengganu(idfail,mukim,no_fail) {
	
	var namaPTG = document.${formName}.txtNamaPTG.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek8Terengganu?idFail="+idfail+"&namaPegawai="+namaPTG+"&mukim="+mukim+"&no_fail="+no_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek4Terengganu(idfail,mukim,no_fail) {
	
	var namaPTG = document.${formName}.txtNamaPTG.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek4Terengganu?idFail="+idfail+"&namaPegawai="+namaPTG+"&mukim="+mukim+"&no_fail="+no_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratKPTG(selectNoFail,idfail,nama_pegawai,id_jawatan) {

	if (selectNoFail == "yes" && document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var idJawatan = 0;
		if(id_jawatan!=""){
			idJawatan = id_jawatan;
		}

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
		var url = "../../servlet/ekptg.report.ppt.SuratKpdPTGDariJKPTGIbuPejabat?idFail="+idfail+"&namaPegawai="+nama_pegawai+"&id_jawatan='"+idJawatan+"'&no_fail="+nofail+"&sysdate="+sysdate;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakMMKSek8Perlis(idfail,no_fail) {

	var persidangan = document.${formName}.txtPersidangan.value;
	var masa = document.${formName}.txtMasa.value;
	var tarikh = document.${formName}.txdTarikh.value;
	var tempat = document.${formName}.txtTempat.value;
	
	
	
	var disediakan = document.${formName}.disediakan.value;
	var disemak = document.${formName}.disemak.value;
	var dilulus = document.${formName}.dilulus.value;
	//alert("1");
	var disediakan_jawatan = document.${formName}.jawatan_disediakan.value;
	//alert("2");
	var disemak_jawatan = document.${formName}.disemak_jawatan.value;
	//alert("3");
	var dilulus_jawatan = document.${formName}.dilulus_jawatan.value;
	//alert("4");
	
	
		var valType = document.${formName}.sorSelectNoFail.value;
		var no_rujukan_fail = "";
		
		if(valType=="1"){
			no_rujukan_fail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			no_rujukan_fail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			no_rujukan_fail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			no_rujukan_fail = document.${formName}.no_rujukan_upt.value;
		}else{
			no_rujukan_fail = "";
		}		
	
	var no_rujukan_fail = no_rujukan_fail;
	
	
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek8Perlis?idFail="+idfail+"&no_fail="+no_fail+"&persidangan="+persidangan+"&masa="+masa+"&tarikh="+tarikh+"&tempat="+tempat+"&disediakan="+disediakan+"&disemak="+disemak+"&dilulus="+dilulus+"&disediakan_jawatan="+disediakan_jawatan+"&disemak_jawatan="+disemak_jawatan+"&dilulus_jawatan="+dilulus_jawatan+"&no_rujukan_fail="+no_rujukan_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}


function cetakMMKSek4Perlis(idfail,no_fail) {

	var bil = document.${formName}.txtBil.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek4Perlis?idFail="+idfail+"&bilSurat="+bil+"&no_fail="+no_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangDBI(idfail,mukim,bilhakmilik,selectNoFail) {

	var namaPTG = document.${formName}.txtNamaPTG.value;
	var sysdate = document.${formName}.txtTarikhSuratCetak.value;
	var sysdateBI = document.${formName}.txtTarikhSuratCetakBI.value;

	if(selectNoFail == "yes" && document.${formName}.sorSelectNoFail.value == "" ){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}else{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}		
		
			if(bilhakmilik > 1){
			var url = "../../servlet/ekptg.report.ppt.BorangDBI_Lebih?id_fail="+idfail+"&nama_pengarah="+namaPTG+"&nama_mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate+"&sysdateBI="+sysdateBI;			
			}else{
			var url = "../../servlet/ekptg.report.ppt.BorangDBI?id_fail="+idfail+"&nama_pengarah="+namaPTG+"&nama_mukim="+mukim+"&no_fail="+nofail+"&sysdate="+sysdate+"&sysdateBI="+sysdateBI;	
			}
			
		//var url = "../../servlet/ekptg.report.ppt.BorangDBI?id_fail="+idfail+"&nama_pengarah="+namaPTG+"&nama_mukim="+mukim;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
		
	}
	
}
function cetakMMKSek8Kedah(idfail,nama_pengarah,no_fail) {
	
	var kertasNo = document.${formName}.txtKertasNo.value;
	
	
	var PegawaiTT = document.${formName}.txtPegawaiTT.value;
	var JawatanPegawaiTT = document.${formName}.txtJawatanPegawaiTT.value;
	var BPJawatanPegawaiTT = document.${formName}.txtBPJawatanPegawaiTT.value;
	var TarikhBulanIslam = document.${formName}.txtTarikhBulanIslam.value;
	var TarikhTT = document.${formName}.txtTarikhTT.value;

	var url = "../../servlet/ekptg.report.ppt.MMKSek8Kedah?idFail="+idfail+"&namaPentadbir="+nama_pengarah+"&kertasNo="+kertasNo+"&no_fail="+no_fail+"&PegawaiTT="+PegawaiTT+"&JawatanPegawaiTT="+JawatanPegawaiTT+"&BPJawatanPegawaiTT="+BPJawatanPegawaiTT+"&TarikhBulanIslam="+TarikhBulanIslam+"&TarikhTT="+TarikhTT;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek8PPinang(idfail,no_fail) {

	var kertasBil = document.${formName}.txtKertasBil.value;
	var bil = document.${formName}.txtBil.value;
	var namaPentadbir = document.${formName}.txtNamaPentadbir.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek8PPinang?idFail="+idfail+"&kertasBil="+kertasBil+"&bilSurat="+bil+"&namaPegawai="+namaPentadbir+"&no_fail="+no_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek8Perak(idfail,no_fail) {

	var namaMB = document.${formName}.txtNamaMB.value;
	var namaPTG = document.${formName}.txtNamaPTG.value;
	var keputusanMB = document.${formName}.txtKeputusanMB.value;
	
	var kmb = encodeURIComponent(keputusanMB);
	 
	var url = "../../servlet/ekptg.report.ppt.MMKSek8Perak?idFail="+idfail+"&namaPegawai="+namaPTG+"&namaMenteri="+namaMB+"&no_fail="+no_fail+"&keputusanMB="+kmb;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek4Perak(idfail,no_fail) {

	var namaMB = document.${formName}.txtNamaMB.value;
	var namaPTG = document.${formName}.txtNamaPTG.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek4Perak?idFail="+idfail+"&namaPegawai="+namaPTG+"&namaMenteri="+namaMB+"&no_fail="+no_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek4Kedah(idfail,nama_pengarah,no_fail) {
	
	var kertasNo = document.${formName}.txtKertasNo.value;

	var url = "../../servlet/ekptg.report.ppt.MMKSek4Kedah?idFail="+idfail+"&namaPentadbir="+nama_pengarah+"&kertasNo="+kertasNo+"&no_fail="+no_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}

function cetakMMKSek8WPKL(idfail,no_fail,nama_pegawai,nama_pegawai1,nama_pegawai2) {
	
	var namaPentadbir = document.${formName}.txtNamaPentadbir.value;	
	var txtBilKertas = document.${formName}.txtBilKertas.value;
	var txtBilSalinan = document.${formName}.txtBilSalinan.value;
	var txtTarikhSuratCetak = document.${formName}.txtTarikhSuratCetak.value;	
	
	  var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}	
		
			
	
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek8WPKL?idFail="+idfail+"&namaPentadbir="+namaPentadbir+"&no_fail="+nofail+"&nama_pegawai="+nama_pegawai+"&nama_pegawai1="+nama_pegawai1+"&nama_pegawai2="+nama_pegawai2+"&txtBilKertas="+txtBilKertas+"&txtBilSalinan="+txtBilSalinan+"&txtTarikhSuratCetak="+txtTarikhSuratCetak;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}

function cetakMMKSek4WPKL(idfail,no_fail,nama_pegawai,nama_pegawai1,nama_pegawai2,id_mmk) {
	
	var namaPentadbir = document.${formName}.txtNamaPentadbir.value;	
	var txtBilKertas = document.${formName}.txtBilKertas.value;
	var txtBilSalinan = document.${formName}.txtBilSalinan.value;
	var txtTarikhSuratCetak = document.${formName}.txtTarikhSuratCetak.value;	
	
	  var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}	
		
			
	
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek4WPKL?idFail="+idfail+"&namaPentadbir="+namaPentadbir+"&no_fail="+nofail+"&nama_pegawai="+nama_pegawai+"&nama_pegawai1="+nama_pegawai1+"&nama_pegawai2="+nama_pegawai2+"&txtBilKertas="+txtBilKertas+"&txtBilSalinan="+txtBilSalinan+"&txtTarikhSuratCetak="+txtTarikhSuratCetak+"&idMMK="+id_mmk;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}

/*
function cetakMMKSek4WPKL(idfail,no_fail) {
	
	var namaPentadbir = document.${formName}.txtNamaPentadbir.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek4WPKL?idFail="+idfail+"&namaPegawai="+namaPentadbir+"&no_fail="+no_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
*/


function cetakMMKSek8Melaka(idfail,mukim,no_fail) {
	
	var namaPTG = document.${formName}.txtNamaPTG.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek8Melaka?idFail="+idfail+"&namaPengarah="+namaPTG+"&mukim="+mukim+"&no_fail="+no_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek8NSembilan(idfail,mukim,no_fail) {
	
	var bil = document.${formName}.txtBil.value;
	var namaPTG = document.${formName}.txtNamaPTG.value;
	var namaMB = document.${formName}.txtNamaMB.value;

	var sysdate = document.${formName}.txtTarikhSuratCetak.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek8NSembilan?idFail="+idfail+"&namaMB="+namaMB+"&namaPegawai="+namaPTG+"&bilSurat="+bil+"&mukim="+mukim+"&no_fail="+no_fail+"&sysdate="+sysdate;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek8Selangor(idfail,nama_pengarah,no_fail,id_negeri) {
	
	var bil = document.${formName}.txtBil.value;
	var namaPentadbir = document.${formName}.txtNamaPentadbir.value;

	if(id_negeri=="1"){
		var url = "../../servlet/ekptg.report.ppt.MMKSek8Johor?idFail="+idfail+"&namaPentadbir="+namaPentadbir+"&bilSurat="+bil+"&no_fail="+no_fail;
	}else{
		var url = "../../servlet/ekptg.report.ppt.MMKSek8Selangor?idFail="+idfail+"&namaPentadbir="+namaPentadbir+"&bilSurat="+bil+"&no_fail="+no_fail;
	}
	
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek4Melaka(idfail,mukim,no_fail) {
	
	var namaPTG = document.${formName}.txtNamaPTG.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek4Melaka?idFail="+idfail+"&namaPengarah="+namaPTG+"&mukim="+mukim+"&no_fail="+no_fail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek4Selangor(idfail,nama_pengarah,no_fail,id_negeri) {
	
	var bil = document.${formName}.txtBil.value;

	if(id_negeri=="1"){
		var url = "../../servlet/ekptg.report.ppt.MMKSek4Johor?idFail="+idfail+"&namaPentadbir="+nama_pengarah+"&bilSurat="+bil+"&no_fail="+no_fail;
	}else{
		var url = "../../servlet/ekptg.report.ppt.MMKSek4Selangor?idFail="+idfail+"&namaPentadbir="+nama_pengarah+"&bilSurat="+bil+"&no_fail="+no_fail;
	}
	
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMMKSek4NSembilan(idfail,mukim,no_fail) {
	
	var bil = document.${formName}.txtBil.value;
	var namaPTG = document.${formName}.txtNamaPTG.value;
	var namaMB = document.${formName}.txtNamaMB.value;

	var sysdate = document.${formName}.txtTarikhSuratCetak.value;
	
	var url = "../../servlet/ekptg.report.ppt.MMKSek4NSembilan?idFail="+idfail+"&namaMB="+namaMB+"&namaPegawai="+namaPTG+"&bilSurat="+bil+"&mukim="+mukim+"&no_fail="+no_fail+"&sysdate="+sysdate;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function doChangePegawai() {
	
	//	var namaPenghantar = document.${formName}.txtNamaHantar.value;
	//	var txtBil = document.${formName}.txtBil.value;
	//	var valType = document.${formName}.sorSelectNoFail.value;
	//	var tempatSAPBN = document.${formName}.tempatSAPBN.value;
	//	var negeriSAPBN = document.${formName}.negeriSAPBN.value;
	//	var keterangan_waktu_hantar = document.${formName}.keterangan_waktu_hantar.value;
	//	var nofail = document.${formName}.no_fail.value;
	
	
	document.${formName}.command.value = "doChangePegawai";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupPilihPegawaiReportView"; 
	document.${formName}.submit();
}


function doChangePegawai1() {
	document.${formName}.command.value = "doChangePegawai1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupPilihPegawaiReportView"; 
	document.${formName}.submit();
}


function doChangePegawai2() {
	document.${formName}.command.value = "doChangePegawai2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupPilihPegawaiReportView"; 
	document.${formName}.submit();
}

function cetakAfidavitSijilPerakuan(idhakmilikpb) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var id_pegawai = document.${formName}.socPegawai.value;
	
		var url = "../../servlet/ekptg.report.ppt.Afidavit_SijilPerakuan?id_hakmilikpb="+idhakmilikpb+"&id_pegawai="+id_pegawai;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakKertasMinitMB(idfail,namaPengarah) {

	var url = "../../servlet/ekptg.report.ppt.KertasMinitMB?idfail="+idfail+"&namaPengarah="+namaPengarah;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMBSelangorSS8(idfail,mukim) {

	var bil = document.${formName}.txtBil.value;
	var namaMB = document.${formName}.txtNamaMB.value;
	var namaPTG = document.${formName}.txtNamaPTG.value;
	var noPelan = document.${formName}.txtNoPelan.value;
	
	var url = "../../servlet/ekptg.report.ppt.MBSelangorSS8?idfail="+idfail+"&namaDato="+namaMB+"&namaPengarah="+namaPTG+"&noPelan="+noPelan+"&mukim="+mukim+"&bilSurat="+bil;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}

function cetakLampiranA(idfail,nama2Mukim,nama_pegawai,jawatan) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
    	var url = "../../servlet/ekptg.report.ppt.LampiranASek4?idfail="+idfail+"&nama_mukim="+nama2Mukim+"&namaPegawai="+nama_pegawai+"&jawatan="+jawatan+"&sysdate="+sysdate;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}
function cetakLampiranA8(idfail,nama2Mukim,nama_pegawai,jawatan) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var sysdate = document.${formName}.txtTarikhSuratCetak.value;
		
    	var url = "../../servlet/ekptg.report.ppt.LampiranASek8?idfail="+idfail+"&nama_mukim="+nama2Mukim+"&namaPegawai="+nama_pegawai+"&jawatan="+jawatan+"&sysdate="+sysdate;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}



<!-- REPORT BANTAHAN -->

function cetakSuratMintaDepositDalam30Hari(idfail,id_bantahan,id_hakmilikpb,nama_pegawai) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.SuratMintaDepositDalam30Hari?idfail="+idfail+"&id_bantahan="+id_bantahan+"&id_hakmilikpb="+id_hakmilikpb+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakSuratMintaDepositDalam30HariAP(idfail,id_bantahan,id_hakmilik,id_siasatan,nama_pegawai) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.SuratMintaDepositDalam30HariAP?idFail="+idfail+"&id_bantahan="+id_bantahan+"&idHakmilik="+id_hakmilik+"&idSiasatan="+id_siasatan+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}


function cetaksuratKepadaAPSupayaMembayarPampasanTambahan(idfail,id_bantahan,nama_pegawai,id_negeri) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.suratKepadaAPSupayaMembayarPampasanTambahan?idFail="+idfail+"&id_bantahan="+id_bantahan+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}

function cetaksuratKepadaAPSupayaMembayarPampasanTambahan_AP(idfail,id_bantahan,nama_pegawai) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.suratKepadaAPSupayaMembayarPampasanTambahan_AP?idFail="+idfail+"&id_bantahan="+id_bantahan+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}



function cetakSuratIringanBorangO_AP(idfail,id_bantahan,id_hakmilik,nama_pegawai) {

	var id_pejabat = document.${formName}.socPejabatJPPH.value;

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
		else if(document.${formName}.socPejabatJPPH.value == ""){
		alert('Sila pilih pejabat JPPH terlebih dahulu.');
  		document.${formName}.socPejabatJPPH.focus(); 
		return; 
	}
	
    	var url = "../../servlet/ekptg.report.ppt.SuratIringanBorangO_AP?idFail="+idfail+"&id_bantahan="+id_bantahan+"&idHakmilik="+id_hakmilik+"&namaPegawai="+nama_pegawai+"&id_pejabat='"+id_pejabat+"'";
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratIringanBorangO(idfail,id_bantahan,id_hakmilikpb,nama_pegawai,id_jawatan) {

	var id_pejabat = document.${formName}.socPejabatJPPH.value;

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	else if(document.${formName}.socPejabatJPPH.value == ""){
		alert('Sila pilih pejabat JPPH terlebih dahulu.');
  		document.${formName}.socPejabatJPPH.focus(); 
		return; 
	}
	
    	var url = "../../servlet/ekptg.report.ppt.SuratIringanBorangO?idFail="+idfail+"&id_bantahan="+id_bantahan+"&id_hakmilikpb="+id_hakmilikpb+"&namaPegawai="+nama_pegawai+"&id_pejabat='"+id_pejabat+"'&id_jawatan="+id_jawatan;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	
}

function cetaksuratUtkPanggilanTerimaPampasanKpdPB_bantahan(id_bayaran,idfail,nama_pegawai) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.suratUtkPanggilanTerimaPampasanKpdPB_bantahan?id_bayaran="+id_bayaran+"&idFail="+idfail+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}

function cetaksuratMaklumanSerahBayaranPampasanKpdAP_bantahan(id_bayaran,idfail,id_hakmilikpb,nama_pegawai) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.suratMaklumanSerahBayaranPampasanKpdAP_bantahan?idBayaran="+id_bayaran+"&idFail="+idfail+"&idHakmilikpb="+id_hakmilikpb+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakSuratKepadaJPPHSupayaMenghadiriPerundingan(idfail,nama_pegawai) {

	var id_pejabat = document.${formName}.socPejabatJPPH.value;

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
		else if(document.${formName}.socPejabatJPPH.value == ""){
		alert('Sila pilih pejabat JPPH terlebih dahulu.');
  		document.${formName}.socPejabatJPPH.focus(); 
		return; 
	}
	
    	var url = "../../servlet/ekptg.report.ppt.SuratKepadaJPPHSupayaMenghadiriPerundingan?idFail="+idfail+"&namaPegawai="+nama_pegawai+"&id_pejabat='"+id_pejabat+"'";
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}



function cetakSuratPanggilanPerundinganSementara(idfail,nama_pegawai,id_siasatan,id_jawatan,id_hakmilik) {

	var id_pejabat = document.${formName}.socPejabatJPPH.value;

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
		else if(document.${formName}.socPejabatJPPH.value == ""){
		alert('Sila pilih pejabat JPPH terlebih dahulu.');
  		document.${formName}.socPejabatJPPH.focus(); 
		return; 
	}
	
    	var url = "../../servlet/ekptg.report.ppt.SuratPanggilanPerundinganSementara?idFail="+idfail+"&namaPegawai="+nama_pegawai+"&id_pejabat='"+id_pejabat+"'&id_siasatan='"+id_siasatan+"'&id_jawatan='"+id_jawatan+"'&id_hakmilik="+id_hakmilik;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratPanggilanPerundinganSementaraAgensi(idfail,nama_pegawai,id_siasatan,id_jawatan,id_hakmilik) {

	var id_pejabat = "";

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	
	
    	var url = "../../servlet/ekptg.report.ppt.SuratPanggilanPerundinganSementaraAgensi?idFail="+idfail+"&namaPegawai="+nama_pegawai+"&id_pejabat='"+id_pejabat+"'&id_siasatan='"+id_siasatan+"'&id_jawatan='"+id_jawatan+"'&id_hakmilik="+id_hakmilik;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratPanggilanPerundinganSementaraSambunganAgensi(idfail,nama_pegawai,id_siasatan,id_jawatan,id_hakmilik) {

	var id_pejabat = "";

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
	
	
    	var url = "../../servlet/ekptg.report.ppt.SuratPanggilanPerundinganSementaraSambunganAgensi?idFail="+idfail+"&namaPegawai="+nama_pegawai+"&id_pejabat='"+id_pejabat+"'&id_siasatan='"+id_siasatan+"'&id_jawatan='"+id_jawatan+"'&id_hakmilik="+id_hakmilik;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratPanggilanPerundinganSementaraSambunganPB(idfail,nama_pegawai,id_siasatan,id_jawatan,id_hakmilik) {

	var id_pejabat = "";

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
		
	
    	var url = "../../servlet/ekptg.report.ppt.SuratPanggilanPerundinganSementaraSambunganPB?idFail="+idfail+"&namaPegawai="+nama_pegawai+"&id_pejabat='"+id_pejabat+"'&id_siasatan='"+id_siasatan+"'&id_jawatan='"+id_jawatan+"'&id_hakmilik="+id_hakmilik;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratPanggilanPerundinganSementaraPB(idfail,nama_pegawai,id_siasatan,id_jawatan,id_hakmilik) {

	var id_pejabat = "";

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
		
	
    	var url = "../../servlet/ekptg.report.ppt.SuratPanggilanPerundinganSementaraPB?idFail="+idfail+"&namaPegawai="+nama_pegawai+"&id_pejabat='"+id_pejabat+"'&id_siasatan='"+id_siasatan+"'&id_jawatan='"+id_jawatan+"'&id_hakmilik="+id_hakmilik;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratPanggilanPerundinganSambunganSementara(idfail,nama_pegawai,id_siasatan,id_jawatan,id_hakmilik) {

	var id_pejabat = document.${formName}.socPejabatJPPH.value;

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	
		else if(document.${formName}.socPejabatJPPH.value == ""){
		alert('Sila pilih pejabat JPPH terlebih dahulu.');
  		document.${formName}.socPejabatJPPH.focus(); 
		return; 
	}
	
    	var url = "../../servlet/ekptg.report.ppt.SuratPanggilanPerundinganSambunganSementara?idFail="+idfail+"&namaPegawai="+nama_pegawai+"&id_pejabat='"+id_pejabat+"'&id_siasatan='"+id_siasatan+"'&id_jawatan='"+id_jawatan+"'&id_hakmilik="+id_hakmilik;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}



function cetakSuratKpdAPRundingan(idfail,id_hakmilik,nama_pegawai) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.SuratKpdAPRundingan?idfail="+idfail+"&idhakmilik="+id_hakmilik+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakAkuanPenerimaanCek_bantahan(id_bayaran,idfail,id_hakmilikpb,id_bantahan,nama_pegawai) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.AkuanPenerimaanCek_bantahan?idBayaran="+id_bayaran+"&idFail="+idfail+"&idHakmilikpb="+id_hakmilikpb+"&idBantahan="+id_bantahan+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}

<!-- END REPORT BANTAHAN -->

<!-- REPORT SEMENTARA -->

function cetakBorangM(idfail,id_hakmilikpb) {
	var namaPengarahNeg = document.${formName}.txtNamaPengarahNeg.value;
		
	var url = "../../servlet/ekptg.report.ppt.BorangM?idfail="+idfail+"&idhakmilikpb="+id_hakmilikpb+"&namaPegawai="+namaPengarahNeg;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}

function cetaksuratMaklumanSerahBayaranPampasanKpdAP(idfail,id_bayaran,nama_pegawai) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.suratMaklumanSerahBayaranPampasanKpdAP?idFail="+idfail+"&idBayaran="+id_bayaran+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}

function cetaksuratUtkPanggilanTerimaPampasanKpdPB(idfail,id_bayaran,nama_pegawai) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
    	var url = "../../servlet/ekptg.report.ppt.suratUtkPanggilanTerimaPampasanKpdPB?idFail="+idfail+"&idBayaran="+id_bayaran+"&namaPegawai="+nama_pegawai;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakSementaraMMKSelangor(idfail,nama_pengarah,no_fail,id_negeri) {
	
	var bil = document.${formName}.txtBil.value;
	var namaPentadbir = document.${formName}.txtNamaPentadbir.value;
	
	var url = "../../servlet/ekptg.report.ppt.SementaraMMKSelangor?idFail="+idfail+"&nama_pengarah="+namaPentadbir+"&bilSurat="+bil+"&no_fail="+no_fail;
	
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}

function cetakSementaraMMKKL(idfail,nama_pengarah,no_fail,id_negeri) {
	
	var bil = document.${formName}.txtBil.value;
	var namaPentadbir = document.${formName}.txtNamaPentadbir.value;
	
	var url = "../../servlet/ekptg.report.ppt.SementaraMMKKL?idFail="+idfail+"&namaPentadbir="+nama_pengarah+"&bilSurat="+bil+"&namaPegawai="+namaPentadbir+"&no_fail="+no_fail;
	
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
}

//PPT-43(i)
function cetakMohonBayaranAgensi(id_fail,nama_pengarah,no_fail,id_hakmilik) {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
		document.${formName}.socPegawai.focus(); 
		return; 
	}else{
		var bankMT = document.${formName}.txtNamaBankMT.value;
		var noMT = document.${formName}.txtNomborAkaunMT.value;
		var akaunMT = document.${formName}.txtNamaAkaunMT.value; 
								
		var bankARB = document.${formName}.txtNamaBankARB.value;
		var noARB = document.${formName}.txtNomborAkaunARB.value;
		var akaunARB = document.${formName}.txtNamaAkaunARB.value;
			
		var item1 = "idfail="+id_fail+"&id_hakmilik="+id_hakmilik+"&namaPegawai="+nama_pengarah;
		var item2 = "&bankMT="+bankMT+"&noMT="+noMT+"&akaunMT="+akaunMT;
		var item3 = "&bankARB="+bankARB+"&noARB="+noARB+"&akaunARB="+akaunARB;
			
	    var url = "../../servlet/ekptg.report.ppt.SuratMohonBayaranAgensi?"+item1+item2+item3;
	    			
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
}

<!-- END REPORT SEMENTARA -->

</script>



<!-- Other -->
<script>
function keluar() {
	window.close();
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	} else {
		if (content.length < 2){
			document.${formName}.txtBilDokumen.value = "0" + content;
		}
	}
}
function RemoveNonNumeric( strString ){
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}


function BorangC_TGANU(idfail) {
    	var url = "../../servlet/ekptg.report.ppt.BorangC?id_Fail="+idfail+"&disediakan="+document.${formName}.disediakan.value+"&disemak="+document.${formName}.disemak.value+"&jawatan_disediakan="+document.${formName}.jawatan_disediakan.value+"&disemak_jawatan="+document.${formName}.disemak_jawatan.value+"&borang=1";
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}


function BorangCLebih_TGANU(idfail) {

    	var url = "../../servlet/ekptg.report.ppt.BorangCLebih?id_Fail="+idfail+"&disediakan="+document.${formName}.disediakan.value+"&disemak="+document.${formName}.disemak.value+"&jawatan_disediakan="+document.${formName}.jawatan_disediakan.value+"&disemak_jawatan="+document.${formName}.disemak_jawatan.value+"&borang=1";
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
}

//PPT-26 (ii) 

function cetakSuratIringanAfidavit(idhakmilikpb,id_negeri,id_fail,id_pegawai,no_fail,nama_pegawai) {

	//alert(idhakmilikpb);
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var id_pegawai = document.${formName}.socPegawai.value;
	
		var url = "../../servlet/ekptg.report.ppt.SuratIringanAfidavit?id_hakmilikpb="+idhakmilikpb+"&id_jawatan="+id_pegawai+"&idFail="+id_fail+"&no_fail="+no_fail+"&namaPengarah="+nama_pegawai;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}

function cetakSuratIringanMohonBayaran(idhakmilikpb,id_negeri,id_fail,id_pegawai,no_fail,nama_pegawai) {
	
	if (document.${formName}.sorSelectNoFail.value == ""){
		alert("Sila pilih jenis \"No Fail\" terlebih dahulu.");
		document.${formName}.sorSelectNoFail.focus(); 
		return;
	}
	else if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}	else	{

		var valType = document.${formName}.sorSelectNoFail.value;
		var nofail = "";
		
		if(valType=="1"){
			nofail = document.${formName}.no_fail.value;
		}else if(valType=="2"){
			nofail = document.${formName}.no_rujukan_ptg.value;
		}else if(valType=="3"){
			nofail = document.${formName}.no_rujukan_ptd.value;
		}else if(valType=="4"){
			nofail = document.${formName}.no_rujukan_upt.value;
		}else{
			nofail = document.${formName}.no_fail.value;
		}

		var bankMT = document.${formName}.txtNamaBankMT.value;
		var noMT = document.${formName}.txtNomborAkaunMT.value;
		var akaunMT = document.${formName}.txtNamaAkaunMT.value;
		
		var bankARB = document.${formName}.txtNamaBankARB.value;
		var noARB = document.${formName}.txtNomborAkaunARB.value;
		var akaunARB = document.${formName}.txtNamaAkaunARB.value;
		
		var noTel = document.${formName}.txtNotel.value;
		var emel = document.${formName}.txtEmel.value;
		var id_pegawai = document.${formName}.socPegawai.value;
		var emelEFT = document.${formName}.txtEmelEFT.value;

		var item1 = "id_hakmilikpb="+idhakmilikpb+"&namaPengarah="+nama_pegawai+"&id_jawatan="+id_pegawai;
		var item2 = "&no_fail="+nofail+"&email_eft="+emelEFT;
		var item3 = "&emel="+emel+"&no_tel="+noTel+"&bankMT="+bankMT+"&noMT="+noMT+"&akaunMT="+akaunMT+"&bankARB="+bankARB+"&noARB="+noARB+"&akaunARB="+akaunARB;
		
		var url = "../../servlet/ekptg.report.ppt.SuratMohonBayaran?"+item1+item2+item3;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}


function cetakSuratIringanPembayaran(idhakmilikpb,id_fail,nama_pegawai,id_jawatan,no_fail,id_negeri) {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih nama pegawai terlebih dahulu.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}else{

		var id_pegawai = document.${formName}.socPegawai.value;
	
		var url = "../../servlet/ekptg.report.ppt.SuratIringanPembayaran?id_hakmilikpb="+idhakmilikpb+"&id_jawatan="+id_pegawai+"&idFail="+id_fail+"&no_fail="+no_fail+"&namaPengarah="+nama_pegawai;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
}
//PPT-26 (ii) END

// PPT-11 CETAK SURAT PELUPUSAN
function suratPelupusanHakmilik(idhakmilik, idfail, idpermohonan, bilLot)	{
	
	// Get bitLot from URL
	var url_string = (window.location.href).toLowerCase();
	var url = new URL(url_string);
	var bilLot = url.searchParams.get("bilLot");
	
	var id_pegawai = document.${formName}.socPegawai.value;
	nofail = document.${formName}.no_fail.value;
	
	if (document.${formName}.socPegawai.value == ""){
	alert("Sila pilih \"Nama Pegawai\" terlebih dahulu.");
	document.${formName}.socPegawai.focus(); 
	return;
	
	}	else	{
	
	var url = "../../servlet/ekptg.report.ppt.SuratPelupusanHakmilik?idHakmilik="+idhakmilik+"&idfail="+idfail+"&id_permohonan="+idpermohonan+"&no_fail="+nofail;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
	
	}
}

</script>