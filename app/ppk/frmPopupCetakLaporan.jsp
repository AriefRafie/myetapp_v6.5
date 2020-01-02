<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
#parse("css/eTapp_PPK.css")
</style>

<p>
   <input name="report" type="hidden" id="report" value="$report"/>
   <input name="flagReport" type="hidden" id="flagReport" value="$flagReport"/>
   <input name="idperbicaraan" type="hidden" id="idperbicaraan" value="$id_perbicaraan"/>
   <input name="idhta" type="hidden" id="idhta" value="$idhta"/>
   <input name="daerahrayuan" type="hidden" id="daerahrayuan" value="$daerahrayuan"/>
   <input name="negerirayuan" type="hidden" id="negerirayuan" value="$negerirayuan"/>
   <input name="idobminor" type="hidden" id="idobminor" value="$idobminor"/>
   <input name="idfail" type="hidden" id="idfail" value="$idfail"/> 
   <input name="idsimati" type="hidden" id="idsimati" value="$idsimati"/> 
   <input name="idpejabatjkptg" type="hidden" id="idpejabatjkptg" value="$idpejabatjkptg"/> 
   <input name="idPegawai" type="hidden" id="idPegawai" value="$id_Pegawai"/>  
   <input name="noKP" type="hidden" id="noKP" value="$noKP" />
   <input name="Alamat" type="hidden" id="Alamat" value="$Alamat" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    	<fieldset><legend><strong>Cetakan Laporan</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="30%">&nbsp;</td>
                <td width="70%">&nbsp;</td>
              </tr>
              #if($report != 'SlipPendengaran')
              <tr>
                <td width="30%">&nbsp;&nbsp; No. Fail</td>
                <td width="70%">: $noFail.toUpperCase()</td>
              </tr>
              #end
              #if($flagReport == 'S')
              <tr>
                <td><span class="$style style1">*</span> Bil. Dokumen</td>
                <td>: <input name="txtBilDokumen" type="text" value="$bilDokumen" size="2" maxlength="2" onblur="validateNumber(this,this.value);"></td>
              </tr>
              #end
              
              #if($report == 'SuratIringanBorangQ' || $report == 'SuratIringanNotis' || $report == 'SuratLulusPrmhnnBKE' || $report == 'SuratTolakPrmhnnBKE' || $report == 'SuratIringanNotisTerengganu'  || $report == 'SuratBatalPermohonanKpLama' || $report == 'SuratBatalPermohonanKpBaru' || $report == 'SuratBatalPermohonanKpLain' || $report == 'SuratIringanPerintahTerengganu' || $report == 'SuratIringanPerintahTerengganuSek17')
              
              <!--|| $report == 'SuratIringanNotis'  buang popup-->
              
              #if($report == 'SuratIringanNotis' )
              <input name="socPegawai" type="hidden" id="socPegawai" value = "0">
              #else
              
              <tr>
		  
                <td><span class="$style style1">*</span> Nama Pegawai</td>
                <td>: $selectedNamaPegawai</td>
              </tr>
              <tr>
                <td><span class="$style style1">*</span> Nama Pemohon</td>
                <td>: <input name="NamaPemohon" type="text" size="50"  maxlength="100"></td>
              </tr>
              <tr>
                <td><span class="$style style1">*</span> Alamat Pemohon</td>
                <td>: <input name="Alamat1" type="text" size="50"  maxlength="100"></td>
              </tr>
              <tr>
                <td></td>
                <td>&nbsp;&nbsp;<input name="Alamat2" type="text" size="50"  maxlength="100"></td>
              </tr>
              <tr>
                <td></td>
                <td>&nbsp;&nbsp;<input name="Alamat3" type="text" size="50"  maxlength="100"></td>
              </tr>
               <tr>
                <td><span class="$style style1">*</span> Poskod</td>
                <td>: <input name="Poskod" type="text" type="text" size="6"></td>
              </tr>
                            <tr>
                <td><span class="$style style1">*</span> Bandar</td>
                <td>: <input name="Bandar" type="text" size="25"  maxlength="100"></td>
              </tr>
               <tr>
                <td><span class="$style style1">*</span> Negeri</td>
                <td>: <input name="Negeri" type="text" size="15"  maxlength="100"></td>
              </tr>
              #end
             
              
              
              #elseif($report != 'BorangK1' && $report != 'BorangK3' && $report != 'BorangDD' && $report != 'BorangDD17' && $report != 'SlipPendengaran' )
              
               #if($report == 'suratAkuanTerima' || $report == 'suratAkuanTerimaP' 
                || $report == 'SuratMaklumatTambahan' || $report == 'SuratPeringatanMaklumatTambahan' 
                || $report == 'SuratPindahMT' || $report == 'SuratPindahMTII' 
                || $report == 'SuratBatalPermohonan' ||$report == 'SuratPeringatan' 
                || $report == 'SuratBatalPermohonan1'
                || $report == 'SuratPeringatan_TM' || $report == 'SuratIringanBorangB' 
                || $report == 'SuratIringanNotisPengeluaranGeran' || $report == 'SuratArahanAkuanARB'  
                || $report == 'SuratIringanPerintahKuasaTadbir'  || $report == 'SuratIringanPerintahKuasaTadbirII'  
                || $report == 'SuratBatalPermohonan_MT' || $report == 'SuratPindahMTPerbicaraan'
                )
               
               <input name="socPegawai" type="hidden" id="socPegawai" value = "0" >
               
               #else              
              <tr>
                <td><span class="$style style1">*</span> Nama Pegawai</td>
                <td>: $selectNamaPegawai</td>
              </tr>
               #end
              
              #end  
              
              
                         
              #if($report == 'SuratIringanNotisTerengganu')
              <tr>
                <td><span class="$style style1">*</span> Daerah Notis</td>
                <td>: $selectDaerah</td>
              </tr>
              #end
              #if($report == 'SuratIringanPerintahTerengganu' || $report == 'SuratIringanPerintahTerengganuSek17') 
               <tr>
                <td>&nbsp;&nbsp; Daerah</td>
                <td>: $selectDaerahUrus</td>
              </tr>
              #end
              #if($report == 'BorangK1' || $report == 'BorangK3')
              <tr>
                <td><span class="$style style1">*</span> Mahkamah</td>
                <td>: $selectMahkamah</td>
              </tr>
              #end
              #if($report == 'SlipPendengaran')
               <tr>
                <td><span class="$style style1">*</span>Daerah</td>
                <td>: $selectDaerahUrus_KPP</td>
              </tr>
              <tr>
                <td><span class="$style style1">*</span>Tarikh Bicara (Mula)</td>
                <td>: <input name="txdTarikhBicara" type="text" id="txdTarikhBicara" size="11"> 
                <a href="javascript:displayDatePicker('txdTarikhBicara',false,'dmy');"><img src="../../img/calendar.gif" alt="" border="0"/></a></td>
              </tr>
               <tr>
                <td><span class="$style style1">*</span>Tarikh Bicara (Sehingga)</td>
                <td>: <input name="txdTarikhBicaraTo" type="text" id="txdTarikhBicaraTo" size="11"> 
                <a href="javascript:displayDatePicker('txdTarikhBicaraTo',false,'dmy');"><img src="../../img/calendar.gif" alt="" border="0"/></a></td>
              </tr>
              <tr>
                <td><span class="$style style1">*</span>Nama Pegawai Pengendali</td>
                <td>: $selectNamaPegawai_KPP</td>
              </tr>
              #end
              #if($report == 'BorangDD' || $report == 'BorangDD17')
              <tr><td colspan="2">&nbsp;</td></tr>
              <tr>
              	  <td><span class="$style style1">*</span> Sila pilih orang berkepentingan</td>
              	  <td>:</td>
              </tr>
              <tr>
              	  <td colspan="2">&nbsp;<input type="checkbox" title="Pilih Semua" name="checkallob" onClick="this.value=checkALLob(this.form.cblistob,$listAllOB_size)">&nbsp;<b>Pilih Semua</b></td>
              </tr>
              		#if($listAllOB_size!=0)
          			 	#foreach($ob in $listAllOB)
           	 			#set($id_ob=$ob.id_ob)
           	 			#set($nama_ob=$ob.nama_ob)
                   		#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              			 	#set( $row = "row1" )
         				#else
               		 		#set( $row = "row2" )
         				#end
         				
         				#if($ob.flag!="0")
         					#set($cbcheck="checked")
         				#else
         					#set($cbcheck="")
         				#end
         				 
              			<tr>
               	 			<td colspan="2" class="$row">&nbsp;<input type="checkbox" $cbcheck name="cblistob" value="$ob.id_ob">&nbsp;$nama_ob.toUpperCase()</td>
              			</tr>
              			#end
              		#else
              			<tr>
  							<td colspan="2" class="$row">Tiada Rekod</td>
  						</tr>
              		#end	
              #end
              
              <tr>
                <td>&nbsp;</td>
                <td>
                 #if($report == 'SlipPendengaran')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSlipPendengaran()">
                #end
                #if($report == 'suratAkuanTerima')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratAkuanTerima('$noFail','$idfail','$flagReport')">
                #end
                
                 #if($report == 'suratAkuanTerimaP')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratAkuanTerimaP('$noFail','$idfail','$flagReport')">
                #end
                
                  #if($report == 'SuratBatalPermohonanKpLama' || $report == 'SuratBatalPermohonanKpBaru' || $report == 'SuratBatalPermohonanKpLain')
          
                  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratBatalPermohonanKp('$report','$noKP')">
                #end
                #if($report == 'SuratBatalPermohonan')
                     <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratBatalPermohonan('$report','$noFail','$idfail','$flagReport')">
                 #end
                 #if($report == 'SuratBatalPermohonan1')
                     <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratBatalPermohonan1('$report','$noFail','$idfail','$flagReport')">
                 #end
                   #if($report == 'SuratIringanBorangB')
                <input type="button" name="cmdCetak2" id="cmdCetak2" value="Cetak" onclick="javascript:cetakSuratIringanBorangB('$noFail','$idfail','$flagReport')" />
                #end
                 #if($report == 'SuratMaklumatTambahan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakMaklumatTambahan('$noFail','$idfail','$flagReport')">
                #end
                 #if($report == 'SuratPeringatanMaklumatTambahan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPeringatanMaklumatTambahan('$noFail','$idfail','$flagReport')">
                #end
                 #if($report == 'BorangB2')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangB('$noFail','$idfail','$flagReport')">
                #end
                
              	#if($report == 'SuratPindahMT' || $report == 'SuratPindahMTPerbicaraan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPindahMT('$noFail','$idfail','$flagReport','$report')">
                #end
                
                #if($report == 'SuratPindahMTII')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPindahMTII('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'suratBatalBicara')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetaksuratBatalBicara('$noFail','$idfail','$flagReport','$id_perbicaraan')">
                #end   
                
                #if($report == 'suratBatalBicaraSek17')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetaksuratBatalBicaraSek17('$noFail','$idfail','$flagReport','$id_perbicaraan')">
                #end                              
                
              	#if($report == 'BorangI' || $report == 'BorangIPerbicaraan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangI('$noFail','$idfail','$flagReport','$report','$id_perbicaraan')">
                #end
                
                #if($report == 'NilaianHartaPPSPP')                
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:NilaianHartaPPSPP('$noFail','$idfail','$flagReport')">
                #end
                
                 #if($report == 'SuratIringanJPPH')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanJPPH('$noFail','$idfail','$idhta','$flagReport')">
                #end
                 #if($report == 'SuratIringanJPPH_TM')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanJPPH_TM('$noFail','$idfail','$idhta','$flagReport')">
                #end
                
                #if($report == 'SuratPeringatan')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPeringatan('$noFail','$idfail','$idhta','$flagReport')">
                #end
                
                #if($report == 'SuratPeringatan_TM')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratPeringatan_TM('$noFail','$idfail','$idhta','$flagReport')">
                #end
                
                #if($report == 'SuratIringanNotis')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanNotis('$noFail','$idfail','$flagReport','$idpejabatjkptg')">
                #end
                
                #if($report == 'SuratIringanNotisTerengganu')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanNotisTerengganu('$noFail','$idfail','$flagReport','$idpejabatjkptg')">
                #end
                
                #if($report == 'SuratIringanPerintahTerengganuSek17')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanPerintahTerengganuSek17('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'SuratIringanPerintahTerengganu')
                 <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanPerintahTerengganu('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'BorangD')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangD('$noFail','$idfail','$flagReport','$id_perbicaraan')">
                #end
                
                #if($report == 'BorangS')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangS('$idfail','$flagReport','$id_perbicaraan','$idsimati')">
                #end
                
                #if($report == 'BorangDD')
                <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanBorangDD('$id_perbicaraan','$listAllOB_size')">
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangDD('$noFail','$idfail','$flagReport','$id_perbicaraan','$listAllOB_size')">
                #end
                
                #if($report == 'BorangDD17')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangDD17('$noFail','$idfail','$flagReport','$id_perbicaraan','$listAllOB_size')">
                #end
                
                #if($report == 'BorangE')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangE('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'BorangEE')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangEE('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'BorangF')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangF('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'BorangHH')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangHH('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'BorangL')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangL('$noFail','$idfail','$flagReport','$id_perbicaraan')">
                #end
                
                #if($report == 'BorangM')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangM('$noFail','$idfail','$flagReport','$id_perbicaraan')">
                #end
                
                #if($report == 'BorangN')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangN('$noFail','$idfail','$flagReport','$id_perbicaraan')">
                #end
             
		#if($report == 'SuratLulusPrmhnnBKE')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratLulusPrmhnnBKE('$noFail','$idfail','$flagReport')">
                #end
				
		#if($report == 'SuratTolakPrmhnnBKE')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratTolakPrmhnnBKE('$noFail','$idfail','$flagReport')">
                #end
				
		#if($report == 'BorangQ')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangQ('$noFail','$idfail','$flagReport')">
                #end
		
		#if($report == 'SuratIringanBorangQ')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakIringanBorangQ('$noFail','$idfail','$flagReport')">
                #end
		
		#if($report == 'BorangR')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangR('$noFail','$idfail','$flagReport')">
                #end
				
		#if($report == 'BorangH')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangH('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'BorangK1')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangK1('$noFail','$idfail','$flagReport','$daerahrayuan','$negerirayuan','$id_perbicaraan')">
                #end
                
                #if($report == 'BorangK2')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangK2('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'BorangK3')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangK3('$noFail','$idfail','$flagReport','$daerahrayuan','$negerirayuan','$id_perbicaraan')">
                #end
                
                #if($report == 'BorangJ')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakBorangJ('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'SuratIringanNotisPengeluaranGeran')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanNotisPengeluaranGeran('$noFail','$idfail','$flagReport','$id_perbicaraan')">
                #end
                
                #if($report == 'SuratIringanPerintahKuasaTadbir')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanPerintahKuasaTadbir('$noFail','$idfail','$flagReport','$id_perbicaraan')">
                #end
                
                #if($report == 'SuratIringanPerintahKuasaTadbirII')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratIringanPerintahKuasaTadbirII('$noFail','$idfail','$flagReport','$id_perbicaraan')">
                #end
                
                #if($report == 'SuratTerimaFail')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSuratTerimaFail('$noFail','$idfail','$flagReport')">
                #end
                
                
                 #if($report == 'SuratBatalPermohonanLainKes')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:SuratBatalPermohonanLainKes('$noFail','$idfail','$flagReport')">
                #end
                
                 #if($report == 'SuratArahanAkuanARB')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:SuratArahanAkuanARB('$noFail','$idfail','$flagReport')">
                #end
                
                #if($report == 'SuratBatalPermohonan_MT')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:SuratBatalPermohonan_MT('$noFail','$idfail','$flagReport')">
                #end
                 #if($report == 'SuratKaveat')
                <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:SuratKaveat('$noFail','$idfail','$flagReport')">
                #end
                <input type="button" name="cmdKeluar" id="cmdKeluar" value="Keluar" onclick="javascript:keluar()">                </td>
              </tr>
            </table>
      </fieldset>
    </td>
  </tr>
    <tr>
 	<td>&nbsp;</td>
  </tr>
#if($report != 'SlipPendengaran')
  <tr>
 	<td>
 		<font color="blue"><u><strong>Nota</strong></u></font> <br>
 		<!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
 		<b>Aktif</b> = Pegawai yang bertugas di Unit Pusaka semasa <br>
 		<b>Tidak Aktif</b> = Pegawai yang telah berpindah ke Unit Pusaka lain / Bersara <br>
     </td>
  </tr>

#end
</table>


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
function cetakSlipPendengaran(){
	
	if(document.${formName}.socDaerahUrus.value == ""){
		alert('Sila pilih Daerah.');
  		document.${formName}.socDaerahUrus.focus(); 
		return; 
	}
	else if(document.${formName}.txdTarikhBicara.value == ""){
		alert('Sila masukkan Tarikh Bicara (Mula).');
  		document.${formName}.txdTarikhBicara.focus(); 
		return; 
	}
	else if(document.${formName}.txdTarikhBicaraTo.value == ""){
		alert('Sila masukkan Tarikh Bicara (Sehingga).');
  		document.${formName}.txdTarikhBicaraTo.focus(); 
		return; 
	}
	
	else
	{
		var sp = "";
		if(document.${formName}.socPegawai.value != "")
		{
		sp =  document.${formName}.socPegawai.value;
		}
		else
		{
			sp = "000";	
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
		
		}	
		
		
	 var url = "../../servlet/ekptg.report.ppk.SlipPendengaran?idDaerah="+document.${formName}.socDaerahUrus.value+"&tarikhBicara="+document.${formName}.txdTarikhBicara.value+"&tarikhBicaraTo="+document.${formName}.txdTarikhBicaraTo.value+"&id_pegawai="+sp;
    var hWnd = window.open(url,'Cetak1','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	}
	
}
function cetakSuratAkuanTerima(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratAkuanTerima?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratAkuanTerimaP(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratAkuanTerimaP?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratIringanBorangB(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanBorangB?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangB(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangB2?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangL(noFail,idfail,flagReport,idperbicaraan) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangL?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport+"&idperbicaraan="+idperbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangM(noFail,idfail,flagReport,idperbicaraan) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangM?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport+"&idperbicaraan="+idperbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangN(noFail,idfail,flagReport,idperbicaraan) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangN?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport+"&idperbicaraan="+idperbicaraan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangJ(noFail,idfail,idperbicaraan,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangJ?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

	//function cetakSuratPindahMT(noFail,idfail,flagReport) {
	function cetakSuratPindahMT(noFail,idfail,flagReport,templ) {
		if (flagReport == 'S'){
			if(document.${formName}.txtBilDokumen.value == ""){
			alert('Sila masukkan Bil. Dokumen.');
	  		document.${formName}.txtBilDokumen.focus();
			return; 
			}
		}
		if(document.${formName}.socPegawai.value == ""){
			alert('Sila pilih pegawai.');
	  		document.${formName}.socPegawai.focus(); 
			return; 
		}
	
		//window.close();
	    var url = "../../servlet/ekptg.report.ppk.SuratPindahMT?template="+templ+"&nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}

function cetakSuratPindahMTII(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratPindahMTII?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetaksuratBatalBicara(noFail,idfail,flagReport,idperbicaraan) {

	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.suratBatalBicara?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;

    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetaksuratBatalBicaraSek17(noFail,idfail,flagReport,idperbicaraan) {

	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.suratBatalBicaraSek17?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;

    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

	//function cetakBorangI(noFail,idfail,flagReport) {
	function cetakBorangI(noFail,idfail,flagReport,templ,id_perbicaraan) {
		if (flagReport == 'S'){
			if(document.${formName}.txtBilDokumen.value == ""){
			alert('Sila masukkan Bil. Dokumen.');
	  		document.${formName}.txtBilDokumen.focus();
			return; 
			}
		}
		if(document.${formName}.socPegawai.value == ""){
			alert('Sila pilih pegawai.');
	  		document.${formName}.socPegawai.focus(); 
			return; 
		}
	
		//window.close();
	    var url = "../../servlet/ekptg.report.ppk.BorangI?template="+templ+"&nofail="+noFail+"&idperbicaraan="+id_perbicaraan+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}

function cetakMaklumatTambahan(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratMintaMaklumatTambahan?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratPeringatanMaklumatTambahan(noFail,idfail,flagReport) {
	
	
	
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratPeringatan?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratIringanJPPH(noFail,idfail,idhta,flagReport) {
	
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanJPPH?nofail="+noFail+"&idfail="+idfail+"&idhta="+idhta+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratIringanJPPH_TM(noFail,idfail,idhta,flagReport) {
	
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanJPPH_TM?nofail="+noFail+"&idfail="+idfail+"&idhta="+idhta+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratPeringatan(noFail,idfail,idhta,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratPeringatanJPPH?nofail="+noFail+"&idfail="+idfail+"&idhta="+idhta+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratPeringatan_TM(noFail,idfail,idhta,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratPeringatanJPPH_TM?nofail="+noFail+"&idfail="+idfail+"&idhta="+idhta+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangD(noFail,idfail,flagReport,idperbicaraan) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	var url = "../../servlet/ekptg.report.ppk.BorangD?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
	//var url = "../../servlet/ekptg.report.ppk.BorangD?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
   	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangS(idfail,flagReport,idperbicaraan,idsimati) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	/*
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	*/
	var url = "../../servlet/ekptg.report.ppk.BorangS?idfail="+idfail+"&idsimati="+idsimati+"&idperbicaraan="+idperbicaraan+"&flagReport="+flagReport;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangDD(noFail,idfail,flagReport,idperbicaraan,param) {

	var checkSelected=false;
	if(param>1){
		for(var i=0 ; i < document.${formName}.cblistob.length; i++) 
		{ 
    		if (document.${formName}.cblistob[i].checked)
        	{
  				checkSelected=true; 
  			}
		}
	}else{
		if (document.${formName}.cblistob.checked)
    	{
			checkSelected=true; 
    	}
	}
	
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	else if(!checkSelected)
	{
		alert("Sila pilih orang berkepentingan.");
		return;
	}
	else{
		
		//document.${formName}.idperbicaraan.value = idperbicaraan;
		//document.${formName}.command.value = "simpanPilihanOb";
		//document.${formName}.submit();
	
    	var url = "../../servlet/ekptg.report.ppk.BorangDD?idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&flagReport="+flagReport;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
	}
}
function simpanBorangDD(idperbicaraan,param) {

	var checkSelected=false;
	if(param>1){
		for(var i=0 ; i < document.${formName}.cblistob.length; i++) 
		{ 
    		if (document.${formName}.cblistob[i].checked)
        	{
  				checkSelected=true; 
  			}
		}
	}else{
		if (document.${formName}.cblistob.checked)
    	{
			checkSelected=true; 
    	}
	}
	
	if(!checkSelected)
	{
		alert("Sila pilih orang berkepentingan.");
		return;
	}
	else{		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.idperbicaraan.value = idperbicaraan;
		document.${formName}.command.value = "simpanPilihanOb";
		document.${formName}.submit();
	}
}
function cetakBorangDD17(noFail,idfail,flagReport,idperbicaraan,param) {
	
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	/*
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	*/
	var checkSelected=false;
	//var stringIdOb="";
	if(param>1){
		for(var i=0 ; i < document.${formName}.cblistob.length; i++) 
		{ 
    		if (document.${formName}.cblistob[i].checked)
        	{
  				checkSelected=true; 
  				//stringIdOb = stringIdOb + document.${formName}.cblistob[i].value + ",";
  			}
		}
	}else{
		if (document.${formName}.cblistob.checked)
    	{
			checkSelected=true; 
			//stringIdOb = document.${formName}.cblistob.value + ",";
    	}
	}
	
	//stringIdOb = stringIdOb.substring(0,stringIdOb.length-1);

	if(!checkSelected)
	{
	alert("Sila pilih orang berkepentingan.");
	return;
	}

	document.${formName}.idperbicaraan.value = idperbicaraan;
	document.${formName}.command.value = "simpanPilihanOb";
	document.${formName}.submit();

    var url = "../../servlet/ekptg.report.ppk.BorangDD17?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangH(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

    var url = "../../servlet/ekptg.report.ppk.BorangH?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangE(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangE?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangEE(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangEE?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangF(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangF?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangHH(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangHH?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratIringanNotis(noFail,idfail,flagReport,idpejabatjkptg) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanNotis?nofail="+noFail+"&idfail="+idfail+"&idpejabatjkptg="+idpejabatjkptg+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
   	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratIringanNotisTerengganu(noFail,idfail,flagReport,idpejabatjkptg) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih Daerah Notis.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanNotisTerengganu?nofail="+noFail+"&idfail="+idfail+"&idpejabatjkptg="+idpejabatjkptg+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&idPejabatJKPTG="+document.${formName}.socDaerah.value+"&flagReport="+flagReport;
   	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
	
}

function cetakSuratIringanPerintahTerengganu(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	if(document.${formName}.socDaerahUrus.value == ""){
		alert('Sila pilih Daerah.');
  		document.${formName}.socDaerahUrus.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanPerintahTerengganu?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&idDaerah="+document.${formName}.socDaerahUrus.value+"&flagReport="+flagReport;
   	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
	
}
function cetakSuratIringanPerintahTerengganuSek17(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	if(document.${formName}.socDaerahUrus.value == ""){
		alert('Sila pilih Daerah.');
  		document.${formName}.socDaerahUrus.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanPerintahTerengganuSek17?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&idDaerah="+document.${formName}.socDaerahUrus.value+"&flagReport="+flagReport;
   	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
	
}


		function cetakSuratBatalPermohonan(f,noFail,idfail,flagReport) {
			if (flagReport == 'S'){
				if(document.${formName}.txtBilDokumen.value == ""){
				alert('Sila masukkan Bil. Dokumen.');
		  		document.${formName}.txtBilDokumen.focus();
				return; 
				}
			}
			if(document.${formName}.socPegawai.value == ""){
				alert('Sila pilih pegawai.');
		  		document.${formName}.socPegawai.focus(); 
				return; 
			}

			//window.close();
			
			
		    var url = "../../servlet/ekptg.report.ppk.SuratBatalPermohonan?report="+f+"&nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
		    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}	
		
		function cetakSuratBatalPermohonan1(f,noFail,idfail,flagReport) {
			if (flagReport == 'S'){
				if(document.${formName}.txtBilDokumen.value == ""){
				alert('Sila masukkan Bil. Dokumen.');
		  		document.${formName}.txtBilDokumen.focus();
				return; 
				}
			}
			if(document.${formName}.socPegawai.value == ""){
				alert('Sila pilih pegawai.');
		  		document.${formName}.socPegawai.focus(); 
				return; 
			}

			//window.close();
			
			
		    var url = "../../servlet/ekptg.report.ppk.SuratBatalPermohonan?report="+f+"&nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
		    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}	
		
function cetakSuratBatalPermohonanKp(f,noKP) {
			/*if (flagReport == 'S'){
				if(document.${formName}.txtBilDokumen.value == ""){
				alert('Sila masukkan Bil. Dokumen.');
		  		document.${formName}.txtBilDokumen.focus();
				return; 
				}
			}*/


			//window.close();
			var NamaPemohon = document.${formName}.NamaPemohon.value;
				NamaPemohon = NamaPemohon.toUpperCase();
			var Alamat1 = document.${formName}.Alamat1.value;
				Alamat1 = Alamat1.toUpperCase();
			var Alamat2 = 	document.${formName}.Alamat2.value;
				Alamat2 = Alamat2.toUpperCase();
			var Alamat3 = 	document.${formName}.Alamat3.value;
				Alamat3 = Alamat3.toUpperCase();
			var Bandar = 	document.${formName}.Bandar.value;
				Bandar = Bandar.toUpperCase();
			var Negeri = 	document.${formName}.Negeri.value;
				Negeri = Negeri.toUpperCase();
			
			
		    /*var url = "../../servlet/ekptg.report.ppk.SuratBatalPermohonan?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
		    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();*/
		    
			if(document.${formName}.socPegawai.value == ""){
				alert('Sila pilih pegawai.');
		  		document.${formName}.socPegawai.focus(); 
				return; 
				}
				if(document.${formName}.NamaPemohon.value == ""){
					alert('Sila masukkan nama pemohon.');
			  		document.${formName}.NamaPemohon.focus(); 
					return; }
				if(document.${formName}.Alamat1.value == ""){
					alert('Sila masukkan alamat pemohon.');
				  	document.${formName}.Alamat1.focus(); 
					return; }
				if(document.${formName}.Poskod.value == ""){
					alert('Sila masukkan poskod.');
				  	document.${formName}.Poskod.focus(); 
					return; }
				if(document.${formName}.Bandar.value == ""){
					alert('Sila masukkan maklumat bandar.');
					document.${formName}.Bandar.focus(); 
					return; }
				if(document.${formName}.Negeri.value == ""){
					alert('Sila masukkan alamat negeri.');
					document.${formName}.Bandar.focus(); 
					return; }
		var url = "../../servlet/ekptg.report.ppk.SuratBatalPermohonan?report="+f+"&noKP="+noKP+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport=&Alamat1="+Alamat1+"&NamaPemohon="+NamaPemohon+"&Alamat2="+Alamat2+"&Alamat3="+Alamat3+"&Poskod="+document.${formName}.Poskod.value+"&Bandar="+Bandar+"&Negeri="+Negeri;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
		}		
function cetakSuratLulusPrmhnnBKE(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratLulusPrmhnnBKE?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratTolakPrmhnnBKE(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratTolakPrmhnnBKE?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangQ(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangQ?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangR(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangR?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangK1(noFail,idfail,flagReport,daerah,negeri,idperbicaraan) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	/*
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	*/
	if(document.${formName}.socMahkamah.value == ""){
		alert('Sila pilih mahkamah.');
  		document.${formName}.socMahkamah.focus(); 
		return; 
	}
	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangK1?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&iddaerah="+daerah+"&NEGERI="+negeri+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangK2(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangK2?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function NilaianHartaPPSPP(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
	  var url = "../../servlet/ekptg.report.ppk.NilaianHartaPPSPP?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
  //  var url = "../../servlet/ekptg.report.ppk.NilaianHartaPPSPP?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangK3(noFail,idfail,flagReport,daerah,negeri,idperbicaraan) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	/*
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}
	*/
	if(document.${formName}.socMahkamah.value == ""){
		alert('Sila pilih mahkamah.');
  		document.${formName}.socMahkamah.focus(); 
		return; 
	}
	//window.close();
    var url = "../../servlet/ekptg.report.ppk.BorangK3?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&iddaerah="+daerah+"&NEGERI="+negeri+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function getdetailmahkamah(){
	document.${formName}.command.value = "getdetailmahkamah";
	document.${formName}.submit();
}

function cetakSuratIringanNotisPengeluaranGeran(noFail,idfail,flagReport,idperbicaraan) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanNotisPengeluaranGeran?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratIringanPerintahKuasaTadbir(noFail,idfail,flagReport,idperbicaraan) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanPerintahKuasaTadbir?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratIringanPerintahKuasaTadbirII(noFail,idfail,flagReport,idperbicaraan) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanPerintahKuasaTadbirII?nofail="+noFail+"&idfail="+idfail+"&idperbicaraan="+idperbicaraan+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratTerimaFail(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratTerimaFail?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}





function SuratBatalPermohonanLainKes(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratBatalPermohonanLainKes?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function SuratArahanAkuanARB(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratArahanAkuanARB?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}




function SuratBatalPermohonan_MT(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratBatalPermohonan_MT?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function SuratKaveat(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratKaveat?nofail="+noFail+"&idfail="+idfail+"&bilDokumen="+document.${formName}.txtBilDokumen.value+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakIringanBorangQ(noFail,idfail,flagReport) {
	if (flagReport == 'S'){
		if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
		}
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus(); 
		return; 
	}

	//window.close();
    var url = "../../servlet/ekptg.report.ppk.SuratIringanBorangQ?nofail="+noFail+"&idfail="+idfail+"&idPegawai="+document.${formName}.socPegawai.value+"&flagReport="+flagReport+"&bilDokumen="+document.${formName}.txtBilDokumen.value;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>

<script language="javascript">
var checked = false;
function checkALLob(field,size) {

  if (!checked) { 

	 if(size>1){
    	for (i = 0; i < field.length; i++) 
    	{ 
      		field[i].checked = true;
   		}
    		checked = true;
	 }else{
		  document.${formName}.cblistob.checked = true;
	   	  checked = true;
	 }
	 
  } else {

	 if(size>1){
    	for (i = 0; i < field.length; i++) 
    	{ 
     		field[i].checked = false;
    	}
    		checked = false;
	 }else{
		 document.${formName}.cblistob.checked = false;
	   	 checked = false;
	 }	
	 	
  }
  
}
</script>


