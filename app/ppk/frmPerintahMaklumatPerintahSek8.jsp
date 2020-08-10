<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
#set ($PPT = "")
<p>
  <input type="hidden" name="jeniskp" value="$jeniskp">
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionPerintah" type="hidden" id="actionPerintah" value="$actionPerintah"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="selectedTabLower" type="hidden" id="selectedTabLower" value="$selectedTabLower"/>
  <input name="hitButt" type="hidden" id="hitButt" value="$hitButt"/>
  <input name="hitButt2" type="hidden" id="hitButt2" value="$hitButt2"/>
  <input name="anchor" type="hidden" id="anchor"/>
  <input name="jenisPerintah" type="hidden" id="jenisPerintah" value="$jenisPerintah"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPermohonanSimati" type="hidden" id="idPermohonanSimati" value="$idPermohonanSimati"/>
  <input name="idPerintah" type="hidden" id="idPerintah" value="$idPerintah"/>
  <input name="idPerbicaraan" type="hidden" id="idPerbicaraan" value="$idPerbicaraan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="flagAdaHTA" type="hidden" id="flagAdaHTA" value="$flagAdaHTA"/>
  <input name="flagAdaHTATH" type="hidden" id="flagAdaHTATH" value="$flagAdaHTATH"/>
  <input name="flagAdaHA" type="hidden" id="flagAdaHA" value="$flagAdaHA"/>
  <input name="flagAdaHTAPT" type="hidden" id="flagAdaHTAPT" value="$flagAdaHTAPT"/>
  <input name="flagAdaHAPT" type="hidden" id="flagAdaHAPT" value="$flagAdaHAPT"/>
  <input name="flagAdaHTAPKT" type="hidden" id="flagAdaHTAPKT" value="$flagAdaHTAPKT"/>
  <input name="flagAdaHAPKT" type="hidden" id="flagAdaHAPKT" value="$flagAdaHAPKT"/>
  <input name="flagAdaHTAPL" type="hidden" id="flagAdaHTAPL" value="$flagAdaHTAPL"/>
  <input name="flagAdaHAPL" type="hidden" id="flagAdaHAPL" value="$flagAdaHAPL"/>
  <input name="flagAdaHTAPF" type="hidden" id="flagAdaHTAPF" value="$flagAdaHTAPF"/>
  <input name="flagAdaHAPF" type="hidden" id="flagAdaHAPF" value="$flagAdaHAPF"/>
  <input name="flagSelesaiHTA" type="hidden" id="flagSelesaiHTA" value="$flagSelesaiHTA"/>
  <input name="flagSelesaiHA" type="hidden" id="flagSelesaiHA" value="$flagSelesaiHA"/>
  <input name="flagSelesaiHAARB" type="hidden" id="flagSelesaiHAARB" value="$flagSelesaiHAARB"/>
  
  <input name="idHTA" type="hidden" id="idHTA" value="$idHTA"/>
  <input name="idHA" type="hidden" id="idHA" value="$idHA"/>
  <input name="idJenisHA" type="hidden" id="idJenisHA" value="$idJenisHA"/>
  <input name="printOption" type="hidden" id="printOption"/>
  <input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
  <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
   <input name="usid" type="hidden" id="usid" value="$usid"/>
   
</p>
<body onLoad = $onload >

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <!-- START HEADER -->
  <tr>
    <td> #foreach($list in $MaklumatPermohonan)
      #set($idPermohonan=$list.idPermohonan)
      #set($idSimati=$list.idSimati)
      <input name="idSimati" type="hidden" id="idSimati" value="$idSimati"/>
      #set($noFail=$list.noFail)
      #set($idFail=$list.idFail)
      <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
      #set($negeri=$list.pmNama_negeri)
      <input name="idNegeri" type="hidden" id="idNegeri" value="$list.pmidnegeri"/>
      #set($idNegeri = $list.pmidnegeri)
      #set($daerah=$list.namadaerah)
      #set($unit=$list.namaPejabat)
      #set($seksyen=$list.seksyen)
      #set($statusFail=$list.keterangan)
      #set($tarikhMohon=$list.tarikhMohon)
      #set($namaSimati=$list.namaSimati)
      #set($namaSipemohon=$list.namaPemohon)
      #end
      
      <!-- 
      $noFail
      $SimpanStatus
      -->
      #set ($Tarikh_serahan = $TARIKH_SERAHAN)  
      #set ($Nama_Penerima = $NAMA_PENERIMA) 
      #set ($kp1 = $KP1)
      #set ($kp2 = $KP2)
      #set ($kp3 = $KP3)
      #set ($Catatan = $CATATAN) 
      #set ($Jenis_Penghantaran = $JENIS_PENGHANTARAN)
      #set ($kemaskini1 = $kemaskini1)
      #set ($ID_PENGHANTAR_NOTIS = $ID_PENGHANTAR_NOTIS)

      #if ($kemaskini1 == "1" && $kemaskini == "")
      	#set ($kemaskini = "1")
      <!-- 	kemaskini = $kemaskini  --> 
      #else
      	#set ($kemaskini = "0")
      <!-- 	kemaskini = $kemaskini  --> 
      #end

      #if ($SimpanStatus == "2")
        #set ($chkmode = "disabled")
      #else
      	#set ($chkmode = "")
      #end
      
      #if($!headerppk.size()>0)
      #parse("app/ppk/headerppk.jsp")
      #end
      <fieldset id="header_lama" style="display:none" >
        <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td width="50%"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
                <tr>
                  <td width="37%" align="right">NO. FAIL :</td>
                  <td width="63%"><font color="blue">$noFail
                    <input type="hidden" name="noFail" id="noFail" value="$noFail">
                    </font></td>
                </tr>
                <tr>
                  <td align="right">NEGERI :</td>
                  <td><font color="blue">$negeri.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right">DAERAH / JAJAHAN :</td>
                  <td><font color="blue">$daerah.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right" valign="top">UNIT :</td>
                  <td rowspan="2" valign="top"><font color="blue">$unit.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                </tr>
              </table></td>
            <td width="50%"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
                <tr>
                  <td width="37%" align="right">STATUS FAIL :</td>
                  <td width="63%"><font color="blue">$statusFail.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right">SEKSYEN :</td>
                  <td><font color="blue">$seksyen.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right">TARIKH MOHON :</td>
                  <td><font color="blue">$tarikhMohon</font></td>
                </tr>
                <tr>
                  <td align="right">NAMA PEMOHON :</td>
                  <td><font color="blue">$namaSipemohon.toUpperCase()</font></td>
                </tr>
                <tr>
                  <td align="right">NAMA SIMATI :</td>
                  <td><font color="blue">$namaSimati.toUpperCase()</font></td>
                </tr>
              </table></td>
          </tr>
        </table>
      </fieldset>
      #if($!headerppk.size()>0)
      #else 
      <script  type="text/javascript">
if(document.getElementById("header_lama").style.display=="none")
{
document.getElementById("header_lama").style.display="block";
}
</script> 
      #end </td>
  </tr>
  <!-- END HEADER --> 
  #if (($flagPopup == '' || $flagPopup == 'openHTA' || $flagPopup == 'openHTATH' || $flagPopup == 'openHA') && ($flagPopup != 'openHTAPT' || $flagPopup != 'openHAPT' || $flagPopup != 'openHTAPKT' || $flagPopup != 'openHAPKT' || $flagPopup != 'openHTAPL' || $flagPopup != 'openHAPL' || $flagPopup != 'openHTAPF' || $flagPopup != 'openHAPF'))
  <tr>
    <td>&nbsp;</td>
  </tr>
  <!-- START PERINTAH PEMBAHAGIAN -->
  <tr>
    <td><a name="tabUpper"></a>
      <fieldset>
        <legend><strong>PERINTAH PEMBAHAGIAN</strong></legend>
        <div id="TabbedPanels1" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li onClick="javascript:setSelectedTabUpper(0);" class="TabbedPanelsTab" tabindex="0">HARTA TAK ALIH (ADA HAKMILIK)</li>
            <li onClick="javascript:setSelectedTabUpper(1);" class="TabbedPanelsTab" tabindex="0">HARTA TAK ALIH (TIADA HAKMILIK)</li>
            <li onClick="javascript:setSelectedTabUpper(2);" class="TabbedPanelsTab" tabindex="0">HARTA ALIH</li>
            #if ($idStatus == 21)
           		<li onClick="javascript:setSelectedTabUpper(3);" class="TabbedPanelsTab" tabindex="0">PENGHANTARAN PERINTAH</li>
            #end
          </ul>
          <div class="TabbedPanelsContentGroup"> 
            <!-- START CONTENT HARTA TAK ALIH (ADA HAKMILIK) -->
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HARTA TAK ALIH (ADA HAKMILIK) --> 
                #if ($flagPopup == 'openHTA')
                #set ($maklumatHTA = "") 
                #foreach($maklumatHTA in $BeanMaklumatHTA)
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #if ($modePopup != 'new')
                      <tr>
                        <td>&nbsp;</td>
                        <td>Keterangan Hakmilik</td>
                        <td valign="top">:</td>
                        <td>$maklumatHTA.keteranganHakmilik</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>Status Pemilikan</td>
                        <td valign="top">:</td>
                        <td>$maklumatHTA.jenisPB</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>Jenis Tanah</td>
                        <td valign="top">:</td>
                        <td>$maklumatHTA.jenisTanah</td>
                      </tr>
                      #end
                      <tr>
                        <td width="1%">#if($mode == 'update' && ($modePopup == 'new' || $modePopup == 'update')) <span class="style1">*</span> #end</td>
                        <td width="20%">Jenis Perintah</td>
                        <td valign="top" width="1%">:</td>
                        <td width="78%">$selectJenisPerintahHTA </td>
                      </tr>
                      #if ($modePopup == 'new')
                      <tr>
                        <td valign="top"><span class="style1">*</span></td>
                        <td valign="top">Keterangan Hakmilik</td>
                        <td valign="top">:</td>
                        <td> #if ($listSizeHTAPopup > 10)
                          <div style="width:100%;height:300;overflow:auto"> #set($saizTableHTA="100%")
                            #else
                            #set($saizTableHTA="95%")
                            #end
                            <table align="left" width="$saizTableHTA" cellspacing="2" cellpadding="2" border="0">
                              <tr class="table_header" style="height:25">
                                <td width="4%" align="center">BIL</td>
                                <td width="85%">KETERANGAN HAKMILIK</td>
                                <td width="10%" align="center">STATUS PEMILIKAN</td>
                                #if ($listSizeHTAPopup != '0')
                                <td width="1%"><input type="checkbox" name="all" onClick="doCheckAll()"></td>
                                #end </tr>
                              #set ($listHTAPopup = "")
                              #foreach ($listHTAPopup in $SenaraiHTAPopup)
                              #if ($listHTAPopup.bil == '')
                              #set( $row = "row1" )
                              #elseif (($listHTAPopup.bil % 2) != 0)
                              #set( $row = "row1" )
                              #else 
                              #set( $row = "row2" )
                              #end
                              <tr style="height:25">
                                <td align="center">$listHTAPopup.bil</td>
                                <td >$listHTAPopup.keteranganHakmilik</td>
                                <td align="center">$listHTAPopup.kodPB</td>
                                #if ($listSizeHTAPopup != '0')
                                <td ><input type="checkbox" value="$listHTAPopup.idHTA" name="idspentadbir" onClick="doUpdateCheckAll()"/></td>
                                #end </tr>
                              #end
                            </table>
                            #if ($listSizeHTAPopup > 10) </div>
                          #end </td>
                      </tr>
                      #end
                      <tr>
                        <td valign="top">&nbsp;</td>
                        <td valign="top">Catatan</td>
                        <td valign="top">:</td>
                        <td><textarea name="txtCatatanHTA" cols="45" rows="5" id="txtCatatanHTA" $readonly class="$inputTextClass" >$maklumatHTA.catatan</textarea></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td> #if ($mode == 'update')
                          #if ($modePopup == 'new')
                          <input type="button" name="cmdSimpanHTA" id="cmdSimpanHTA" value="Simpan" onClick="javascript:simpanBaruHTA($listSizeHTAPopup)"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanHTA').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHTA" id="cmdBatalHTA" value="Kembali" onClick="javascript:batalHTA()"/>
                          #end
                          #if ($modePopup == 'update')
                          <input name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" type="button" value="Kemaskini" onClick="javascript:simpanKemaskiniHTA()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanKemaskini').style.display = "none";
                                        </script> 
                          #end
                          <input  type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="javascript:hapusHTA()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdHapus').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHTA" id="cmdBatalHTA" value="Kembali" onClick="javascript:batalHTA()"/>
                          #end
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHTA" id="cmdBatalHTA" value="Kembali" onClick="javascript:batalHTA()"/>
                          #end </td>
                      </tr>
                      #if ($mode == 'update' && ($modePopup == 'new' || $modePopup == 'update'))
                      <tr>
                        <td colspan="4" height="50px" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
                      </tr>
                      #end
                    </table></td>
                </tr>
                #end
                #end 
                <!-- END OPEN POPUP HARTA TAK ALIH (ADA HAKMILIK) --> 
                <!-- START LIST HARTA TAK ALIH (ADA HAKMILIK) -->
                <tr>
                  <td> #if ($flagAdaHTA == '1')
                    <fieldset>
                    #set ($printalert = '1')
                    
                      <legend><strong>SENARAI HARTA TAK ALIH (ADA HAKMILIK)</strong></legend>
                      #if ($SenaraiHTA.size() > 10)
                      <div style="width:100%;height:215;overflow:auto"> #end
                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                          <tr>
                            <td colspan="4"> #if ($mode == 'update')
                              #if ($flagPopup == '')
                            
                              #if ($flagSelesaiHTA == '' && $userRole != "user_ppk")
                              <input name="cmdTambahHTA" id="cmdTambahHTA" value="Daftar Perintah" type="button" onClick="javascript:tambahHTA()"/>
                              #end
                              #end
                              #end </td>
                          </tr>
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="55%">KETERANGAN HAKMILIK</td>
                            <td width="20%">JENIS PERINTAH</td>
                            <td width="10%" align="center">STATUS PEMILIKAN</td>
                            <td width="10%" align="center">BAHAGIAN SIMATI</td>
                          </tr>
                          #set ($PKP = "")
                         
                          #set ($listHTA = "")
                          
                          
                          #set ($idPerintahHTAOBMST="")
                          
                          #foreach ($listHTA in $SenaraiHTA)
                          
                          #if ($listHTA.bil == '')
                          #set( $row = "row1" )
                          #set ($idPerintahHTAOBMST=$idPerintahHTAOBMST+$listHTA.idPerintahHTAOBMST)
                          #elseif (($listHTA.bil % 2) != 0)
                          #set( $row = "row1" )
                          #set ($idPerintahHTAOBMST=$idPerintahHTAOBMST+$listHTA.idPerintahHTAOBMST)
                          #else 
                          #set( $row = "row2" )
                          #set ($idPerintahHTAOBMST=$idPerintahHTAOBMST+$listHTA.idPerintahHTAOBMST)
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTA.bil</td>
                            #if($listHTA.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTA.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTA($listHTA.idPerintahHTAOBMST)"><font color="#0000FF">$listHTA.keteranganHakmilik</font></a></td>
                            #end
                            <td class="$row">$listHTA.jenisPerintah

                            #if ($listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS" || $listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS  (FARAID)")
                            	
                            	#set ($PPT = "Ada")
                            	
                            	
                            #end
                            
                            #if ($listHTA.jenisPerintah == "PERINTAH  KUASA PENTADBIR" || $listHTA.jenisPerintah == "PERINTAH KUASA PENTADBIR")
                               	#set ($PKP = "Ada")
                            	
                            #end
                            </td>
                            <td class="$row" align="center">$listHTA.kodPB</td>
                            <td class="$row" align="center">$listHTA.bahagianSimati</td>
                          </tr>
                          #end
                        </table>
                        #if ($SenaraiHTA.size() > 10) </div>
                      #end
                    </fieldset>
                    #else
                    &nbsp;<font color="black">SIMATI TIDAK MEMILIKI SEBARANG HARTA TAK ALIH (ADA HAKMILIK)</font> #end </td>
                </tr>
                <!-- END LIST HARTA TAK ALIH (ADA HAKMILIK) -->
              </table>
            </div>
            <!-- END CONTENT HARTA TAK ALIH (ADA HAKMILIK) --> 
            <!-- START CONTENT HARTA TAK ALIH (TIADA HAKMILIK) -->
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HARTA TAK ALIH (TIADA HAKMILIK) --> 
                #if ($flagPopup == 'openHTATH')
                #set ($maklumatHTATH = "")
                #foreach($maklumatHTATH in $BeanMaklumatHTATH)
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      <tr>
                        <td width="20%">Keterangan Hakmilik</td>
                        <td width="1%">:</td>
                        <td width="79%">$maklumatHTATH.keteranganHakmilik</td>
                      </tr>
                      #if ($maklumatHTATH.kategoriHarta == '1')
                      <tr>
                        <td width="20%">Alamat Harta</td>
                        <td width="1%">:</td>
                        <td width="79%">$maklumatHTATH.keterangan</td>
                      </tr>
                      #end
                      #if ($maklumatHTATH.kategoriHarta == '2')
                      <tr>
                        <td width="20%">No. Roh</td>
                        <td width="1%">:</td>
                        <td width="79%">$maklumatHTATH.keterangan</td>
                      </tr>
                      #end
                      #if ($maklumatHTATH.kategoriHarta == '3')
                      <tr>
                        <td width="20%">Jenis Kepentingan</td>
                        <td width="1%">:</td>
                        <td width="79%">$maklumatHTATH.keterangan</td>
                      </tr>
                      #end
                      <tr>
                        <td width="20%">Status Pemilikan</td>
                        <td width="1%">:</td>
                        <td width="79%">$maklumatHTATH.jenisPB</td>
                      </tr>
                      <tr>
                        <td width="20%">Jenis Tanah</td>
                        <td width="1%">:</td>
                        <td width="79%">$maklumatHTATH.jenisTanah</td>
                      </tr>
                      <tr>
                        <td width="20%">Jenis Perintah</td>
                        <td width="1%">:</td>
                        <td width="79%">$maklumatHTATH.jenisPerintah</td>
                      </tr>
                      <tr>
                        <td valign="top" width="20%">Catatan</td>
                        <td valign="top" width="1%">:</td>
                        <td valign="top" width="79%"><textarea name="txtCatatanHTATH" cols="45" rows="5" id="txtCatatanHTATH" $readonly class="$inputTextClass" >$maklumatHTATH.catatan</textarea></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td> #if ($mode == 'update')
                          <input name="cmdWSPusakaTH" type="button" value="Simpan Maklumat Harta ke Sistem eTanah" onClick="javascript:sendWSPusaka($idHTA)"/>
                          #if ($modePopup == 'update')
                          <input id="cmdSimpanKemaskini1" name="cmdSimpanKemaskini1" type="button" value="Kemaskini" onClick="javascript:simpanKemaskiniHTATH()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanKemaskini1').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdHapus1" id="cmdHapus1" value="Hapus" onClick="javascript:hapusHTATH()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdHapus1').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHTATH" id="cmdBatalHTATH" value="Kembali" onClick="javascript:batalHTATH()"/>
                          #end
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHTATH" id="cmdBatalHTATH" value="Kembali" onClick="javascript:batalHTATH()"/>
                          #end </td>
                      </tr>
                    </table></td>
                </tr>
                #end
                #end 
                <!-- END OPEN POPUP HARTA TAK ALIH (TIADA HAKMILIK) --> 
                <!-- START LIST HARTA TAK ALIH (TIADA HAKMILIK) -->
                <tr>
                  <td> #if ($flagAdaHTATH == '1')
                    <fieldset>
                      <legend><strong>SENARAI HARTA TAK ALIH (TIADA HAKMILIK)</strong></legend>
                      <table width="100%" border="0" cellspacing="2" cellpadding="2">
                        <tr class="table_header">
                          <td scope="row" width="5%" align="center">BIL</td>
                          <td width="55%">KETERANGAN HAKMILIK</td>
                          <td width="20%">JENIS PERINTAH</td>
                          <td width="10%" align="center">STATUS PEMILIKAN</td>
                          <td width="10%" align="center">BAHAGIAN SIMATI</td>
                        </tr>
                        #set ($listHTATH= "")
                         
                        #foreach ($listHTATH in $SenaraiHTATH)

                        #if ($listHTATH.bil == '')
                        #set( $row = "row1" )
                        #elseif (($listHTATH.bil % 2) != 0)
                        #set( $row = "row1" )
                        #else 
                        #set( $row = "row2" )
                        #end
                        <tr>
                          <td class="$row" align="center">$listHTATH.bil</td>
                          #if($listHTATH.idPerintahHTAOBMST == '')
                          <td class="$row">$listHTATH.keteranganHakmilik</td>
                          #else
                          <td class="$row"><a href="javascript:paparHTATH($listHTATH.idPerintahHTAOBMST)"><font color="#0000FF">$listHTATH.keteranganHakmilik</font><font color="#000000">$listHTATH.keterangan</font></a></td>
                          #end
                          <td class="$row">$listHTATH.jenisPerintah</td>
                          #if ($listHTATH.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS" || $listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS  (FARAID)")
                            	
                            	#set ($PPT = "Ada")
                            	
                            #end
                            #if (($listHTATH.jenisPerintah == "PERINTAH KUASA PENTADBIR") || ($listHTATH.jenisPerintah == "PERINTAH  KUASA PENTADBIR"))
                            	
                            	#set ($PKP = "Ada")
                            	
                            #end
                            
                          <td class="$row" align="center">$listHTATH.kodPB</td>
                          <td class="$row" align="center">$listHTATH.bahagianSimati</td>
                          
                        </tr>
                        #end
                      </table>
                    </fieldset>
                    #else
                    &nbsp;<font color="black">SIMATI TIDAK MEMILIKI SEBARANG HARTA TAK ALIH (TIADA HAKMILIK)</font> #end </td>
                </tr>
                <!-- END LIST HARTA TAK ALIH (TIADA HAKMILIK) -->
              </table>
            </div>
            <!-- END CONTENT HARTA TAK ALIH (TIADA HAKMILIK) --> 
            <!-- START CONTENT HARTA ALIH -->
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HARTA ALIH --> 
                #if ($flagPopup == 'openHA')
                #set ($maklumatHA = "") 
                #foreach($maklumatHA in $BeanMaklumatHA)
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #if ($modePopup != 'new')
                      <tr>
                        <td>&nbsp;</td>
                        <td>Jenis Harta Alih</td>
                        <td>:</td>
                        <td>$maklumatHA.jenisHartaAlih $maklumatHA.keterangan</td>
                      </tr>
                      #end
                      <tr>
                        <td width="1%">#if($mode == 'update' && ($modePopup == 'new' || $modePopup == 'update')) <span class="style1">*</span> #end</td>
                        <td width="15%">Jenis Perintah</td>
                        <td width="1%">:</td>
                        <td width="83%">$selectJenisPerintahHA</td>
                      </tr>
                      #if ($modePopup == 'new')
                      <tr>
                        <td valign="top"><span class="style1">*</span></td>
                        <td valign="top">Harta Alih</td>
                        <td valign="top">:</td>
                        <td valign="top"> #if ($listSizeHAPopup > 10)
                          <div style="width:100%;height:280;overflow:auto"> #set($saizTableHA="100%")
                            #else
                            #set($saizTableHA="95%")
                            #end
                            <table align="left" width="$saizTableHA" cellspacing="2" cellpadding="2" border="0">
                              <tr class="table_header">
                                <td width="4%" align="center">BIL</td>
                                <td width="20%">JENIS HARTA ALIH</td>
                                <td width="35%">MODEL / BANK - NO PENDAFTARAN / AKAUN</td>
                                <td width="20%" align="right">NILAI / AMAUN (RM)</td>
                                #if ($listSizeHAPopup != '0')
                                <td width="1%"><input type="checkbox" name="all" onClick="doCheckAll()"></td>
                                #end </tr>
                              #set ($listHAPopup = "")
                              #foreach ($listHAPopup in $SenaraiHAPopup)
                              #if ($listHAPopup.bil == '')
                              #set( $row = "row1" )
                              #elseif (($listHAPopup.bil % 2) != 0)
                              #set( $row = "row1" )
                              #else 
                              #set( $row = "row2" )
                              #end
                              <tr>
                                <td align="center">$listHAPopup.bil</td>
                                <td >$listHAPopup.jenisHartaAlih</td>
                                <td >$listHAPopup.jenama.toUpperCase() - $listHAPopup.no_daftar.toUpperCase()</td>
                                <td align="right">$listHAPopup.nilai</td>
                                #if ($listSizeHAPopup != '0')
                                <td ><input type="checkbox" value="$listHAPopup.idHA" name="idspentadbir" onClick="doUpdateCheckAll()"/></td>
                                #end </tr>
                              #end
                            </table>
                            #if ($listSizeHAPopup > 10) </div>
                          #end </td>
                      </tr>
                      #end
                      <tr>
                        <td valign="top">&nbsp;</td>
                        <td valign="top">Catatan</td>
                        <td valign="top">:</td>
                        <td valign="top"><textarea name="txtCatatanHA" cols="45" rows="5" id="txtCatatanHA" $readonly class="$inputTextClass" >$maklumatHA.catatan</textarea></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td> #if ($mode == 'update')
                          #if ($modePopup == 'new')
                          <input type="button" name="cmdSimpanHA" id="cmdSimpanHA" value="Simpan" onClick="javascript:simpanBaruHA($listSizeHAPopup)"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanHA').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHA" id="cmdBatalHA" value="Kembali" onClick="javascript:batalHA()"/>
                          #end
                          #if ($modePopup == 'update')
                          <input name="cmdSimpanKemaskini2" id="cmdSimpanKemaskini2" type="button" value="Kemaskini" onClick="javascript:simpanKemaskiniHA()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanKemaskini2').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdHapus2" id="cmdHapus2" value="Hapus" onClick="javascript:hapusHA()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdHapus2').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHA" id="cmdBatalHA" value="Kembali" onClick="javascript:batalHA()"/>
                          #end
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHA" id="cmdBatalHA" value="Kembali" onClick="javascript:batalHA()"/>
                          #end </td>
                      </tr>
                      #if ($mode == 'update' && ($modePopup == 'new' || $modePopup == 'update'))
                      <tr>
                        <td colspan="4" height="50px" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
                      </tr>
                      #end
                    </table></td>
                </tr>
                #end
                #end 
                <!-- END OPEN POPUP HARTA ALIH --> 
                <!-- START LIST HARTA ALIH -->
                <tr>
                  <td> #if ($flagAdaHA == '1')
                  
                		  
                          
                    <fieldset>
                      <legend><strong>SENARAI HARTA ALIH</strong></legend>
                      #if ($SenaraiHA.size() > 10)
                      <div style="width:100%;height:215;overflow:auto"> #end
                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                          <tr>
                            <td colspan="5"> #if ($mode == 'update')
                              #if ($flagPopup == '')
                               #if ($flagSelesaiHA == '' && $userRole != "user_ppk")
                              <input name="cmdTambahHA" id="cmdTambahHA" value="Daftar Perintah" type="button" onClick="javascript:tambahHA()">
                              #end
                              #end
                              #end </td>
                          </tr>
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="40%">JENIS HARTA ALIH</td>
                            <td width="30%">JENIS PERINTAH</td>
                            <td width="25%" align="right">NILAI TARIKH MOHON (RM)</td>
                          </tr>
                          #set ($listHA = "")
                          
                          #foreach ($listHA in $SenaraiHA)
                          #if ($listHA.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHA.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else 
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHA.bil</td>
                            #if($listHA.idPerintahHAOBMST == '')
                            <td class="$row">$listHA.jenisHarta</td>
                            #else
                            <td class="$row"><a href="javascript:paparHA($listHA.idPerintahHAOBMST)"><font color="#0000FF">$listHA.jenisHarta</font><font color="#000000">$listHA.keterangan</font></a></td>
                            #end
                            <td class="$row">$listHA.jenisPerintah
                            
                            #if ($listHA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS" || $listHTA.jenisPerintah == "PERINTAH PEMBAHAGIAN TERUS  (FARAID)")
                            	
                            	#set ($PPT = "Ada")
                            	
                            #end
                            #if ($listHA.jenisPerintah == "PERINTAH  KUASA PENTADBIR")
                            	#set ($PKP = "Ada")
                            	
                            #end
                            
                            </td>
                            
                            
                            
                            <td class="$row" align="right">$listHA.nilaiTarikhMohon</td>
                          </tr>
                          #end
                        </table>
                        #if ($SenaraiHA.size() > 5) </div>
                      #end
                    </fieldset>
                    #else
                    &nbsp;<font color="black">SIMATI TIDAK MEMILIKI SEBARANG HARTA ALIH</font> #end </td>
                </tr>
                <!-- END LIST HARTA ALIH -->
              </table>
            </div>
            <!-- START CONTENT HARTA ALIH --> 
            
            
            
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                 <!-- SALNIZAM EDIT START TAMBAH LAPORAN PENGHANTARAN PERINTAH -->
            <tr><td>
            <!-- <a name="tabLowest"></a>  -->
            <a name="hantarperintah"></a>
            <fieldset>
            <legend><strong>PENGHANTARAN PERINTAH</strong></legend>
            <fieldset>
                      <legend><strong>MAKLUMAT SERAHAN</strong></legend>
                      <table width="100%" border="0">
                                      <tr>
                                     #set ($sta="sta18")
                                    
              <td width="20%" ><div align="right">#if($chkmode!="disabled")<font color="red">*</font>#end&nbsp;Nama Penyerah</div></td>
              <td width="3%"><div align="right">:</div></td>
              <td ></td>
             
              #if ($chkmode == "disabled")
              <td width="77%"><input size = "50" type="text" $chkmode name="txtNamaPenghantarNotis" id="txtNamaPenghantarNotis" value="$!NAMA_PENYERAH"></td>
             
               #else
                <td width="77%">
                #if($NAMA_PENYERAH !="")
                	<input size = "50" type="text" name="txtNamaPenghantarNotis" id="txtNamaPenghantarNotis" value="$!NAMA_PENYERAH">
                #else
                	<input size = "50" type="text" name="txtNamaPenghantarNotis" id="txtNamaPenghantarNotis" value="">
                #end
                <!-- User tak mahu tarikh nama penyerah daripada database
                <select name="txtNamaPenghantarNotis" style="width:320">
        										#if($onchangeMyList=="no" && $NAMA !="")
        										
        		<option value="$ID_PENGHANTAR_NOTIS">$KOD_PENGHANTAR_NOTIS - $NAMA</option>
                  //<option value="">SILA PILIH1&nbsp;</option> 
        											#foreach($pn in $penghantarNotis)
        											#if($pn.kod_penghantar_notis!=$KOD_PENGHANTAR_NOTIS)
                  <option value="$pn.id_penghantarnotis">$pn.kod_penghantar_notis - $pn.nama.toUpperCase()</option>
                  									#end
	                    							#end  
	                    						#else
                  
                  <option value="">SILA PILIH&nbsp;</option>
                 									#foreach($pnoc in $penghantarNotis)
	                    							
                  <option value="$pnoc.id_penghantarnotis">$pnoc.kod_penghantar_notis - $pnoc.nama.toUpperCase()</option>
	                    							#end
	                    						#end
                </select>
                User tak mahu tarikh nama penyerah daripada database -->
                </td>
               <!-- $ID_PENGHANTAR_NOTIS -  -->  
               #end
              <!-- <td width="73%"><input type="text" $disableZERO $classZero name="txtNamaPenghantarNotis" $check $checkClass value="$namaPenghantar" maxlength="80" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td> --> 
            </tr>
                      <tr>
                      <td width="20%" ><div align="right">#if($chkmode!="disabled")<font color="red">*</font>#end
                      &nbsp;Jenis Serahan
                      </div><input type="hidden" name="no_fail" value="$noFail"></td>
                      <td width = "3%"><div align="right">
                      :
                      </div></td>
                      <td >
                      <input type="radio" name="radioJenis" id="radioJenis" $chkmode onClick="checkIt1()" #if ($Jenis_Penghantaran == "Serahan Tangan") checked #end value="Serahan Tangan">
                      </td>
                      <td width ="77%"><div align="left">
                      Serahan Tangan
                      </div></td>
                      </tr>
<tr>
                      <td width="20%" ><div align="right">
                      
                      </div></td>
                      <td width = "3%"><div align="right">
                     
                      </div></td>
                      <td >
                      <input type="radio" name="radioJenis" id="radioJenis" $chkmode #if ($Jenis_Penghantaran == "Pos") checked #end value="Pos"> 
                      </td>
                      <td width ="77%"><div align="left">
                      Pos
                      </div></td>
                      </tr>
                       
                      <tr>
                      <td width="20%" ><div align="right">#if($chkmode!="disabled")<font color="red">*</font>#end
                      &nbsp;Tarikh Serahan
                      </div></td>
                      <td width = "3%"><div align="right">
                      :
                      </div></td>
                      <td >
                      
                      </td>
                      
                      <td >
                     
                      <input type="text" $chkmode name="txtTarikh" id="txtTarikh" style="text-transform:uppercase;" #if ($Tarikh_serahan != "") value=$Tarikh_serahan #else value="" #end size="15" maxlength="15" >
                                            #if($chkmode != "disabled")
                       <a href="javascript:displayDatePicker('txtTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
                       #end 

                      </td>
                      <td>
                      </td>
                      </tr>
                       
                      <!-- Status diterima atau dikembalikan tidak perlu. Start. 
                     <tr>
                      <td width="20%" ><div align="right">
                      
                      </div></td>
                      <td width = "3%"><div align="right">
                     
                      </div></td>
                      <td >
                      
                      </td>
                      <td width ="77%"><div align="left">
                      <input type="radio" name="radioPos" id="radioPos" onClick="checkIt()" $chkmode #if ($Jenis_Penghantaran == "Pos-Diterima") checked #end value="Pos_Diterima"> 
                      Diterima
                      </div></td>
                      </tr> 
                      
                                           <tr>
                      <td width="20%" ><div align="right">
                      
                      </div></td>
                      <td width = "3%"><div align="right">
                     
                      </div></td>
                      <td >
                      
                      </td>
                      <td width ="77%"><div align="left">
                      <input type="radio" name="radioPos" id="radioPos" onClick="checkIt()" $chkmode #if ($Jenis_Penghantaran == "Pos-Dikembalikan") checked #end value="Pos_Dikembalikan"> 
                      Dikembalikan
                      </div></td>
                      </tr> 
                      Status diterima atau dikembalikan tidak perlu. End. -->
                      </table>
                      </fieldset>
           <fieldset>
                      <legend><strong>MAKLUMAT PENERIMAAN</strong></legend>
                      <table width ="100%" border ="0">
                      
                      <tr>
                      <td width="20%" ><div align="right">#if($chkmode!="disabled")<font color="red">*</font>#end
                      &nbsp;Nama Penerima
                      </div></td>
                      <td width = "3%"><div align="right">
                      :
                      </div></td>
                	<td>#if($chkmode!="disabled")
                   		 <select id="myList" name="myList" onchange="onchangemyList()" style="width:140">
                   		 	
                  			<option value="">SILA PILIH&nbsp;</option>
								#foreach($listOB in $listOBatas18)
                 			<option value="$listOB.id_ob">$listOB.nama_ob.toUpperCase()</option>
                    			#end  
                    			#foreach($listPentingPeguam in $listPentingPeguam)
                    			
                    			<option value="peguam$listPentingPeguam.namaFirma.toUpperCase()">$listPentingPeguam.namaFirma.toUpperCase()</option>
                    			#end
                    		#if ($selectionBox == "Lain-lain")
                   		 		<option value="Lain-lain" selected="selected">LAIN-LAIN</option>
                   		 	#else
                    			<option value="Lain-lain">LAIN-LAIN</option>
                    		#end
                    		
               			</select>
                   		&nbsp;#end
                    	<input type="text" id="namaPenerima" $chkmode $disableZERO $classZero $checkClass name="txtNamaPenerima" value="$!Nama_Penerima" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
               		</td>
                      <td>
                      </td>
                      </tr>
                      <tr>
                      <td width="20%" ><div align="right">
                      No. K/P Penerima
                      </div></td>
                      <td width = "3%"><div align="right">
                      :
                      </div></td>
                     
                      <td>
                      #if($jeniskp=="baru")
                      
                    <input type="text" $chkmode $checkClass $disableZERO $classZero size="6" name="txtNoKPBaru1" id="txtNoKPBaru1" value="$!kp1" maxlength="6"  />
                    -
                    <input name="txtNoKPBaru2"  $chkmode $disableZERO $checkClass $classZero id="txtNoKPBaru2" type="text" value="$!kp2"  size="1" maxlength="2" />
                    -
                    <input name="txtNoKPBaru3" $chkmode $checkClass $disableZERO $classZero id="txtNoKPBaru3"  type="text" value="$!kp3" size="3" maxlength="4"  />
                    <input type="hidden" name="txtNoKPLain" id="txtNoKPLain" value="">
                    <input type="hidden" name="txtNoKPLama" id="txtNoKPLama" value="">
                    #set($No_Kp_Penerima = $kp1 + $kp2 + $kp3)
                   
                    
                    #elseif($jeniskp=="lama")
                    <input type="text" $chkmode $checkClass $disableZERO $classZero size="11" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" name="txtNoKPLama" value="$!kplama" maxlength="10" onkeyup="javascript:validateNumber()" />
                    <input type="hidden" name="txtNoKPBaru1" id="txtNoKPBaru1" value="">
                    <input type="hidden" name="txtNoKPBaru2" id="txtNoKPBaru2" value="">
                    <input type="hidden" name="txtNoKPBaru3" id="txtNoKPBaru3" value="">
                    <input type="hidden" name="txtNoKPLain" id="txtNoKPLain" value="">
                    #set($No_Kp_Penerima = $kplama)
                    #elseif($jeniskp=="lain")
                    
                    <input type="text" $chkmode $checkClass $disableZERO $classZero size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" name="txtNoKPLain" value="$kplain" maxlength="20" onkeyup="javascript:validateNumber()" />
                    <input type="hidden" name="txtNoKPBaru1" id="txtNoKPBaru1" value="">
                    <input type="hidden" name="txtNoKPBaru2" id="txtNoKPBaru2" value="">
                    <input type="hidden" name="txtNoKPBaru3" id="txtNoKPBaru3" value="">
                    <input type="hidden" name="txtNoKPLama" id="txtNoKPLama" value="">
                    #set($No_Kp_Penerima = $kplain)
                    #else
                    
                    <input type="text" $chkmode $checkClass $disableZERO $classZero size="6" name="txtNoKPBaru1" id="txtNoKPBaru1" value="$!kp1" maxlength="6"   />
                    -
                    <input name="txtNoKPBaru2"  $chkmode $disableZERO $checkClass $classZero id="txtNoKPBaru2" type="text" value="$!kp2"  size="1" maxlength="2" />
                    -
                    <input name="txtNoKPBaru3" $chkmode $checkClass $disableZERO $classZero id="txtNoKPBaru3"  type="text" value="$!kp3" size="3" maxlength="4"  />
                    <input type="hidden" name="txtNoKPLain" id="txtNoKPLain" value="">
                    <input type="hidden" name="txtNoKPLama" id="txtNoKPLama" value="">
                    #set($No_Kp_Penerima = $kp1 + "-" + $kp2 + "-" + $kp3)
                    #end 
                      </td>
                      </tr>
                      <tr>
                      <td width="20%" ><div align="right">
                      Catatan
                      </div></td>
                      <td width = "3%"><div align="right">
                      :
                      </div></td>
                      <td >
                      
                      #set ($readonly = $chkmode)
                      #if ($readonly == "disabled")
                      	#set ($readonly = 1)
                      #end
                      #if ($Catatan == "")
                     	   #set ($Catatan = "")
                      #else
                           #set ($Catatan = $Catatan)
                      #end
                      #if ($readonly == "1")
                      <textarea rows="4" cols="50" name="txtCatatan" disabled>$Catatan</textarea>
                      #else
                      <textarea rows="4" cols="50" name="txtCatatan">$Catatan</textarea>
                      #end
                      </td>
                      <td>
                      </td>
                      </tr>
                      
                      </table>
                      </fieldset>
                      <table width ="100%" border ="0">
                      <tr><td width="100%"><div align="center">
                      
                      #if ($SimpanStatus == '2')
                      		<input type="button" name="cmdPrint" id="cmdPrint" value="Cetak Laporan Serahan" onClick="PrintLaporan('$NAMA_PENYERAH','$Jenis_Penghantaran','$Tarikh_serahan',document.getElementById('namaPenerima').value,'$No_Kp_Penerima','$Catatan','$noFail')"/>
                       		<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/>
                       		
                       #else
                       		<input type="button" name="cmdPrint" id="cmdPrint" disabled value="Cetak Laporan Serahan" onClick="PrintLaporan('$namaPenghantar','$Jenis_Penghantaran','$Tarikh_serahan',document.getElementById('namaPenerima').value,'$No_Kp_Penerima','$Catatan','$noFail')"/>
                       		<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="Batal()"/>
                       	<!-- 	<input type="button" name="cmdKemaskini" disabled id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/> -->
                        	
                       #end
                       
                      <input type="button" name="cmdSimpan" id="cmdSimpan" $chkmode value="Simpan" onClick="DoTheCheck()"/>
           				</div></td></tr>
           				</table>
            </fieldset> 
            </td>          
            </tr>
            <tr><td>
            </td></tr>
            <!-- SALNIZAM EDIT END TAMBAH LAPORAN PENGHANTARAN PERINTAH -->
 
              </table>
            </div>
            
            
            
            
            
            
            
          </div>
        </div>
      </fieldset></td>
  </tr>
  <!-- END PERINTAH PEMBAHAGIAN --> 
  #end
  #if($flagPopup == '' || $flagPopup == 'openHTAPT' || $flagPopup == 'openHAPT'  || $flagPopup == 'openHTAPKT' || $flagPopup == 'openHAPKT'|| $flagPopup == 'openHTAPL' || $flagPopup == 'openHAPL'  || $flagPopup == 'openHTAPF' || $flagPopup == 'openHAPF')
  <tr>
    <td>&nbsp;</td>
  </tr>
  <!-- START PEMBAHAGIAN HARTA -->
  <tr>
    <td><a name="tabLower"></a>
      <fieldset>
        <legend><strong> PEMBAHAGIAN HARTA</strong></legend>
        
        <div id="TabbedPanels2" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li onClick="javascript:setSelectedTabLower(0);" class="TabbedPanelsTab" tabindex="0">PERINTAH TERUS</li>
            <li onClick="javascript:setSelectedTabLower(1);" class="TabbedPanelsTab" tabindex="0">PERINTAH KUASA TADBIR</li>
            <li onClick="javascript:setSelectedTabLower(2);" class="TabbedPanelsTab" tabindex="0">PERINTAH LELONG</li>
            <li onClick="javascript:setSelectedTabLower(3);" class="TabbedPanelsTab" tabindex="0">PERINTAH FARAID</li>
          </ul>
          <div class="TabbedPanelsContentGroup"> 
            <!-- START CONTENT PERINTAH TERUS -->
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HTAPT --> 
                #if ($flagPopup == 'openHTAPT')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <tr>
                        <td width="30%" align="right">KETERANGAN HAKMILIK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keteranganHakmilik</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">STATUS PEMILIKAN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisPB</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENIS TANAH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisTanah</font>
                          <input name="idJenisTanah" type="hidden" value="$headerDetail.idJenisTanah"></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>

                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font>
                       
                        <input type="hidden" name="bahagianSimatiAtas" value="$headerDetail.bahagianSimatiAtas" class="$inputTextClass">
                        <input type="hidden" name="bahagianSimatiBawah" value="$headerDetail.bahagianSimatiBawah" class="$inputTextClass">
                        </td>

                       

                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"> #if ($SenaraiHTAPTDTL.size() > 10)
                          <div style="width:100%;height:225;overflow:auto"> #set($sizeUp="100%")
                            #set($alignUp="left")
                            #set($sizeDown="99%")
                            #set($alignDown="left")
                            #else	
                            #set($sizeUp="95%")
                            #set($alignUp="center")
                            #set($sizeDown="95%")
                            #set($alignDown="center")
                            #end
                            <table width="$sizeUp" border="0" cellspacing="2" cellpadding="2" align="$alignUp">
                              <tr>
                                <td colspan="4" align="right"><div id="calculateTotal_result" style="color:#FF0000;font-weight:bold"></div></td>
                              </tr>
                              <tr class="table_header">
                                <td align="center" width="5%">BIL</td>
                                <td width="40%">NAMA WARIS</td>
                                <td width="20%">STATUS WARIS</td>
                                <td align="center" width="35%">BAHAGIAN WARIS</td>
                              </tr>
                              #set ($listHTAPTDTL = "")
                              #set ($cnt = 0)
                              #foreach ($listHTAPTDTL in $SenaraiHTAPTDTL)
                              #set ($cnt = $cnt+1)
                              <tr>
                                <input name="idspentadbir" type="hidden" value="$listHTAPTDTL.idOB">
                                <input name="status" type="hidden" value="$listHTAPTDTL.status">
                                <td align="center">$listHTAPTDTL.bil</td>
                                #if ($listHTAPTDTL.status == '')
                                #if ($listHTAPTDTL.statusHidup == '1')
                                <td>$listHTAPTDTL.namaWaris &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span></td>
                                #else
                                <td>$listHTAPTDTL.namaWaris</td>
                                #end
                                #else
                                <td><a href="javascript:updatePAPTHTAPT('$listHTAPTDTL.idOB','$idPermohonanSimati','$idSimati','$listHTAPTDTL.status','$idPerintah','$mode','$idHTA','$flag_kemaskini_selesai','$idFail')"><font color="#0000FF">$listHTAPTDTL.namaWaris</font></a></td>
                                #end
                                <td>$listHTAPTDTL.status</td>
                                <td align="center"><input name="txtBA" type="text" size="15" maxlength="15" style="text-align:center" value="$listHTAPTDTL.BA" onBlur="validateBahagianWaris(this,this.value,$listHTAPTDTL.BA);calculateTotal();checkPembahagianGSA($cnt);" $readonly class="$inputTextClass">
                                  /
                                  <input name="txtBB" type="text" size="15" maxlength="15" style="text-align:center" value="$listHTAPTDTL.BB" onBlur="validateBahagianWaris(this,this.value,$listHTAPTDTL.BB);calculateTotal();" $readonly class="$inputTextClass"></td>
                              </tr>
                              #end
                            </table>
                            #if ($SenaraiHTAPTDTL.size() > 10) </div>
                          #else <br/>
                          #end 
                          <table width="$sizeDown" border="0" cellspacing="2" cellpadding="2" align="$alignDown">
                            <!-- COMMENT BY PEJE - TIDAK DIPERLUKAN LAGI DAH
                          <tr>
                            <td colspan="4"><hr color="#000000"></td>
                          </tr>
                          <tr>
                            <td align="center" width="5%"><input type="checkbox" name="chkWarisHilang" id="chkWarisHilang" $checked onClick="test();calculateTotal();" value="1" $disabled>
                            </td>
                            <td width="45%">&nbsp;
                              <input type="button" name="cmdLantikPT" id="cmdLantikPT" value="Lantik Pentadbir" onClick="javascript:updatePAPTHTAPT('','$idPermohonanSimati','$idSimati','HILANG','$idPerintah','$mode','$idHTA','$flag_kemaskini_selesai')" $disabledHilang/>
                            </td>
                            <td width="15%">&nbsp;</td>
                            <td align="center" width="35%"><input name="txtBAHilang" type="text" size="14" maxlength="14" style="text-align:center" value="$BAHilang" onBlur="validateBahagianWaris(this,this.value,$BAHilang);calculateTotal();" $disabledHilang $readonly class="$inputTextClass">
                              /
                              <input name="txtBBHilang" type="text" size="14" maxlength="14" style="text-align:center" value="$BBHilang" onBlur="validateBahagianWaris(this,this.value,$BBHilang);calculateTotal();" $disabledHilang $readonly class="$inputTextClass">
                            </td>
                          </tr>
                          -->
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="35%"><input name="txtJumlahBA" id="txtJumlahBA" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBA" readonly class="disabled">
                                /
                                <input name="txtJumlahBB" id="txtJumlahBB" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBB" readonly class="disabled"></td>
                            </tr>
                            <tr>
                              <td colspan="3"><i><font color="#ff0000">Perhatian</font> : Sila simpan pembahagian harta terlebih dahulu dan klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i></td>
                              <td align="center"> #if ($mode == 'update')
                                <input type="button" name="cmdSamakanPembawah" id="cmdSamakanPembawah" value="Samakan Pembawah" onClick="javascript:samakanPembawah()"/>
                                #if($flag_kemaskini_selesai != "yes") 
                                <script>
                                        document.getElementById('cmdSamakanPembawah').style.display = "none";
                                        </script> 
                                #end
                                #end </td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4">&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2" align="center"> #if ($mode == 'update')
                          <input type="button" name="cmdSimpanHTAPT" id="cmdSimpanHTAPT" value="Simpan" onClick="javascript:simpanKemaskiniHTAPT()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanHTAPT').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdPembahagianRata" id="cmdPembahagianRata" value="Pembahagian Separa" onClick="javascript:pembahagianSepara()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdPembahagianRata').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdKosongkanPembahagian" id="cmdKosongkanPembahagian" value="Kosongkan" onClick="javascript:kosongkanPembahagian()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdKosongkanPembahagian').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdPembahagianHartaLain" id="cmdPembahagianHartaLain" value="Pembahagian Harta Lain" onClick="javascript:pembahagianHartaLainHTA('$idPerintah','$idHTA')"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdPembahagianHartaLain').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHTAPT" id="cmdBatalHTAPT" value="Kembali" onClick="javascript:batalHTAPT()"/>
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHTAPT" id="cmdBatalHTAPT" value="Kembali" onClick="javascript:batalHTAPT()"/>
                          #end </td>
                      </tr>
                    </table></td>
                </tr>
                #end 
                <!-- END OPEN POPUP HTAPT --> 
                #if ($flagPopup != 'openHAPT') 
                <!-- START LIST HTAPT -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                      #if($flagAdaHTAPT == '1')
                      
                      #if ($SenaraiHTAPT.size() > 10)
                      <div style="width:100%;height:210;overflow:auto"> #end
                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="65%">KETERANGAN HAKMILIK</td>
                            <td width="15%" align="center">STATUS PEMILIKAN</td>
                            <td width="15%" align="center">JENIS TANAH</td>
                          </tr>
                          #set ($listHTAPT = "")
                          #foreach ($listHTAPT in $SenaraiHTAPT)
                          #if ($listHTAPT.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHTAPT.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else 
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTAPT.bil</td>
                            #if($listHTAPT.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTAPT.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTAPT('$listHTAPT.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPT.keteranganHakmilik</font></a></td>
                            <td class="$row" align="center">$listHTAPT.kodPB</td>
                            <td class="$row" align="center">$listHTAPT.jenisTanah</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHTAPT.size() > 10) </div>
                      #end
                      
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HTAPT --> 
                #end 
                <!-- START OPEN POPUP HAPT --> 
                #if ($flagPopup == 'openHAPT')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <input name="idJenisTanah" type="hidden">
                      <tr>
                        <td width="30%" align="right">JENIS HARTA ALIH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisHA</font><font color="#000000">$headerDetail.keterangan</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENAMA/MODEL/BANK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenama</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">NO. PENDAFTARAN/AKAUN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.noDaftar</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                       <input type="hidden" name="bahagianSimatiAtas" value="$headerDetail.bahagianSimatiAtas" class="$inputTextClass">
                        <input type="hidden" name="bahagianSimatiBawah" value="$headerDetail.bahagianSimatiBawah" class="$inputTextClass">
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"> #if ($SenaraiHAPTDTL.size() > 10)
                          <div style="width:100%;height:225;overflow:auto"> #set($sizeUp="100%")
                            #set($alignUp="left")
                            #set($sizeDown="99%")
                            #set($alignDown="left")
                            #else	
                            #set($sizeUp="95%")
                            #set($alignUp="center")
                            #set($sizeDown="95%")
                            #set($alignDown="center")
                            #end
                            <table width="$sizeUp" border="0" cellspacing="2" cellpadding="2" align="$alignUp">
                              <tr>
                                <td colspan="4" align="right"><div id="calculateTotal_result" style="color:#FF0000;font-weight:bold"></div></td>
                              </tr>
                              <tr class="table_header">
                                <td align="center" width="5%">BIL</td>
                                <td width="40%">NAMA WARIS</td>
                                <td width="20%">STATUS WARIS</td>
                                <td align="center" width="35%">BAHAGIAN WARIS</td>
                              </tr>
                              #set ($listHAPTDTL = "")
                              #set ($cnt = 0)
                              #foreach ($listHAPTDTL in $SenaraiHAPTDTL)
                              #set ($cnt = $cnt+1)
                              <tr>
                                <input name="idspentadbir" type="hidden" value="$listHAPTDTL.idOB">
                                <input name="status" type="hidden" value="$listHAPTDTL.status">
                                <td align="center">$listHAPTDTL.bil</td>
                                #if ($listHAPTDTL.status == '')
                                #if ($listHAPTDTL.statusHidup == '1')
                                <td>$listHAPTDTL.namaWaris &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span></td>
                                #else
                                <td>$listHAPTDTL.namaWaris</td>
                                #end
                                #else
                                <td><a href="javascript:updatePAPTHAPT('$listHAPTDTL.idOB','$idPermohonanSimati','$idSimati','$listHAPTDTL.status','$idPerintah','$mode','$idHA','$flag_kemaskini_selesai','$idFail')"><font color="#0000FF">$listHAPTDTL.namaWaris</font></a></td>
                                #end
                                <td>$listHAPTDTL.status</td>
                                <!-- Harta Tak Alih -->
                                <td align="center"><input name="txtBA" type="text" size="15" maxlength="15" style="text-align:center" value="$listHAPTDTL.BA" onBlur="validateBahagianWaris(this,this.value,$listHAPTDTL.BA);calculateTotal();" $readonly class="$inputTextClass">
                                  /
                                  <input name="txtBB" type="text" size="15" maxlength="15" style="text-align:center" value="$listHAPTDTL.BB" onBlur="validateBahagianWaris(this,this.value,$listHAPTDTL.BB);calculateTotal();" $readonly class="$inputTextClass"></td>
                              </tr>
                              #end
                            </table>
                            #if ($SenaraiHAPTDTL.size() > 10) </div>
                          #else <br/>
                          #end
                          <table width="$sizeDown" border="0" cellspacing="2" cellpadding="2" align="$alignDown">
                            <!-- COMMENT BY PEJE - TIDAK DIPERLUKAN LAGI DAH
                          <tr>
                            <td colspan="4"><hr color="#000000"></td>
                          </tr>
                          <tr>
                            <td align="center" width="5%"><input type="checkbox" name="chkWarisHilang" id="chkWarisHilang" $checked onClick="test();calculateTotal();" value="1" $disabled>
                            </td>
                            <td width="45%">WARIS TIDAK DAPAT DIKESAN &nbsp;
                              <input type="button" name="cmdLantikPT" id="cmdLantikPT" value="Lantik Pentadbir" onClick="javascript:updatePAPTHAPT('','$idPermohonanSimati','$idSimati','HILANG','$idPerintah','$mode','$idHA','$flag_kemaskini_selesai')" $disabledHilang/>
                            </td>
                            <td width="15%">HILANG</td>
                            <td align="center" width="35%"><input name="txtBAHilang" type="text" size="14" maxlength="14" style="text-align:center" value="$BAHilang" onBlur="validateBahagianWaris(this,this.value,$BAHilang);calculateTotal();" $disabledHilang $readonly class="$inputTextClass">
                              /
                              <input name="txtBBHilang" type="text" size="14" maxlength="14" style="text-align:center" value="$BBHilang" onBlur="validateBahagianWaris(this,this.value,$BBHilang);calculateTotal();" $disabledHilang $readonly class="$inputTextClass">
                            </td>
                          </tr>
                          -->
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="35%"><input name="txtJumlahBA" id="txtJumlahBA" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBA" readonly class="disabled">
                                /
                                <input name="txtJumlahBB" id="txtJumlahBB" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBB" readonly class="disabled"></td>
                            </tr>
                            <tr>
                              <td colspan="3"><i><font color="#ff0000">Perhatian</font> : Sila simpan pembahagian harta terlebih dahulu dan klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i></td>
                              <td align="center"> #if ($mode == 'update')
                                <input type="button" name="cmdSamakanPembawah1" id="cmdSamakanPembawah1" value="Samakan Pembawah" onClick="javascript:samakanPembawah()"/>
                                #if($flag_kemaskini_selesai != "yes") 
                                <script>
                                        document.getElementById('cmdSamakanPembawah1').style.display = "none";
                                        </script> 
                                #end
                                #end </td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4">&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2" align="center"> #if ($mode == 'update')
                          <input type="button" name="cmdSimpanHAPT1" id="cmdSimpanHAPT1" value="Simpan" onClick="javascript:simpanKemaskiniHAPT()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanHAPT1').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdPembahagianRata1" id="cmdPembahagianRata1" value="Pembahagian Separa" onClick="javascript:pembahagianSepara()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdPembahagianRata1').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdKosongkanPembahagian1" id="cmdKosongkanPembahagian1" value="Kosongkan" onClick="javascript:kosongkanPembahagian()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdKosongkanPembahagian1').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdPembahagianHartaLain" id="cmdPembahagianHartaLain" value="Pembahagian Harta Lain" onClick="javascript:pembahagianHartaLainHA('$idPerintah','$idHA')"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdPembahagianHartaLain').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHAPT" id="cmdBatalHAPT" value="Kembali" onClick="javascript:batalHAPT()"/>
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHAPT" id="cmdBatalHAPT" value="Kembali" onClick="javascript:batalHAPT()"/>
                          #end </td>
                      </tr>
                    </table></td>
                </tr>
                #end 
                <!-- END OPEN POPUP HAPT --> 
                #if ($flagPopup != 'openHTAPT') 
                <!-- START LIST HAPT -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>
                      #if($flagAdaHAPT == '1')
                      
                      #if ($SenaraiHAPT.size() > 10)
                      <div style="width:100%;height:190;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="35%">JENIS HARTA ALIH</td>
                            <td width="30%">JENAMA/MODEL/BANK</td>
                            <td width="30%">NO. PENDAFTARAN/AKAUN</td>
                          </tr>
                          #set ($listHAPT = "")
                          #foreach ($listHAPT in $SenaraiHAPT)
                          #if ($listHAPT.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHAPT.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else 
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHAPT.bil</td>
                            #if($listHAPT.idPerintahHAOBMST == '')
                            <td class="$row">$listHAPT.jenisHA</td>
                            #else
                            <td class="$row"><a href="javascript:paparHAPT('$listHAPT.idPerintahHAOBMST')"><font color="#0000FF">$listHAPT.jenisHA.toUpperCase()</font><font color="#000000">$listHAPT.keterangan</font></a></td>
                            <td class="$row">$listHAPT.jenama.toUpperCase()</td>
                            <td class="$row">$listHAPT.noDaftar.toUpperCase()</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHAPT.size() > 10) </div>
                      #end
                      
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HAPT --> 
                #end

              </table>
            </div>
            <!-- END CONTENT PERINTAH TERUS --> 
            <!-- START CONTENT PERINTAH KUASA TADBIR -->
           
            
            
            
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HTAPKT --> 
                #if ($flagPopup == 'openHTAPKT')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <input name="idJenisTanah" type="hidden" value="$headerDetail.idJenisTanah">
                      <tr>
                        <td width="30%" align="right">KETERANGAN HAKMILIK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keteranganHakmilik</font></td>
                      </tr>
                      #if ($headerDetail.kategoriHarta == '1')
                      <tr>
                        <td width="30%" align="right">ALAMAT HARTA :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keterangan</font></td>
                      </tr>
                      #end
                      #if ($headerDetail.kategoriHarta == '2')
                      <tr>
                        <td width="30%" align="right">NO ROH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keterangan</font></td>
                      </tr>
                      #end
                      #if ($headerDetail.kategoriHarta == '3')
                      <tr>
                        <td width="30%" align="right">JENIS KEPENTINGAN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keterangan</font></td>
                      </tr>
                      #end
                      <tr>
                        <td width="30%" align="right">STATUS PEMILIKAN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisPB</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENIS TANAH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisTanah</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"><table width="60%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr>
                              <td> #if ($ListHTAPKTDTL.size() > 5)
                                <div style="width:100%;height:133;overflow:auto"> #end
                                  <table align="center" width="100%" border="0">
                                    <tr class="table_header"> #if ($listSize != '0')
                                      <td scope="row" width="1%">&nbsp;</td>
                                      #end
                                      <td scope="row" width="4%" align="center">BIL</td>
                                      <td width="95%">NAMA PENTADBIR</td>
                                    </tr>
                                    #set ($list = "")
                                    #foreach ($list in $ListHTAPKTDTL)
                                    #if ($list.bil == '')
                                    #set( $row = "row1" )
                                    #elseif (($list.bil % 2) != 0)
                                    #set( $row = "row1" )
                                    #else 
                                    #set( $row = "row2" )
                                    #end
                                    <tr> #if ($listSize != '0')
                                      #if($list.flag == 'Y')
                                      #set($checked = 'checked')
                                      #else
                                      #set($checked = '')
                                      #end
                                      <td class="$row"><input type="checkbox" value="$list.idOB" name="idsOB" $checked $disabled onClick="doUpdateCheck('$list.bil')"/></td>
                                      #end
                                      <td class="$row" align="center">$list.bil</td>
                                      #if ($list.statusHidup == '1')
                                      <td class="$row">$list.namaOB &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span></td>
                                      #else
                                      <td class="$row">$list.namaOB</td>
                                      #end </tr>
                                    #end
                                  </table>
                                  #if ($ListHTAPKTDTL.size() > 5) </div>
                                #end </td>
                            </tr>
                            <tr>
                              <td>&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td colspan="2">&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2">&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"> #if ($SenaraiPembahagianHTAPKTDTL.size() > 10)
                          <div style="width:100%;height:225;overflow:auto"> #set($sizeUp="100%")
                            #set($alignUp="left")
                            #set($sizeDown="99%")
                            #set($alignDown="left")
                            #else	
                            #set($sizeUp="95%")
                            #set($alignUp="center")
                            #set($sizeDown="95%")
                            #set($alignDown="center")
                            #end
                            <table width="$sizeUp" border="0" cellspacing="2" cellpadding="2" align="$alignUp">
                              <tr>
                                <td colspan="4" align="right"><div id="calculateTotal_result" style="color:#FF0000;font-weight:bold"></div></td>
                              </tr>
                              <tr class="table_header">
                                <td colspan="3"><strong>PEMBAHAGIAN HARTA</strong></td>
                              </tr>
                              <tr class="table_header">
                                <td align="center" width="5%">BIL</td>
                                <td width="60%">NAMA WARIS</td>
                                <td align="center" width="35%">BAHAGIAN WARIS</td>
                              </tr>
                              #set ($listPembahagianHTAPKTDTL = "")
                              #set ($cnt = 0)
                              #foreach ($listPembahagianHTAPKTDTL in $SenaraiPembahagianHTAPKTDTL)
                              #set ($cnt = $cnt+1)
                              <tr>
                                <input name="idspentadbir" type="hidden" value="$listPembahagianHTAPKTDTL.idOB">
                                <input name="status" type="hidden" value="$listPembahagianHTAPKTDTL.status">
                                <td align="center">$listPembahagianHTAPKTDTL.bil</td>
                                #if ($listPembahagianHTAPKTDTL.statusHidup == '1')
                                <td>$listPembahagianHTAPKTDTL.namaWaris &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span></td>
                                #else
                                <td>$listPembahagianHTAPKTDTL.namaWaris</td>
                                #end
                                <td align="center"><input name="txtBA" type="text" size="15" maxlength="15" style="text-align:center" value="$listPembahagianHTAPKTDTL.BA" onBlur="validateBahagianWaris(this,this.value,$listPembahagianHTAPKTDTL.BA);calculateTotal();" $readonly class="$inputTextClass">
                                  /
                                  <input name="txtBB" type="text" size="15" maxlength="15" style="text-align:center" value="$listPembahagianHTAPKTDTL.BB" onBlur="validateBahagianWaris(this,this.value,$listPembahagianHTAPKTDTL.BB);calculateTotal();" $readonly class="$inputTextClass"></td>
                              </tr>
                              #end
                            </table>
                            #if ($SenaraiPembahagianHTAPKTDTL.size() > 10) </div>
                          #else <br/>
                          #end
                          <table width="$sizeDown" border="0" cellspacing="2" cellpadding="2" align="$alignDown">
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="35%"><input name="txtJumlahBA" id="txtJumlahBA" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBA" readonly class="disabled">
                                /
                                <input name="txtJumlahBB" id="txtJumlahBB" type="text" size="14" style="text-align:center;font-weight:bold" value="$txtJumlahBB" readonly class="disabled"></td>
                            </tr>
                            <tr>
                              <td colspan="3">&nbsp;</td>
                              <td align="center"> #if ($mode == 'update')
                                <input type="button" name="cmdSamakanPembawah" id="cmdSamakanPembawah" value="Samakan Pembawah" onClick="javascript:samakanPembawah()"/>
                                #if($flag_kemaskini_selesai != "yes") 
                                <script>
                                        document.getElementById('cmdSamakanPembawah').style.display = "none";
                                        </script> 
                                #end
                                #end </td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4">&nbsp;</td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                      <!-- 
                      #foreach ($pemegangamanah in $ListHTAPKTDTL)
                      	#if ($pemegangamanah.ID_TARAFKPTG  == '10' && $pemegangamanah.flag == 'Y')
                     		<font color="red"><b>
							<blink>PERINTAH INI MELIBATKAN PEMEGANG AMANAH BAGI HARTA TAK ALIH. SILA PASTIKAN BORANG HH DICETAK.</blink></b></font>
							<script type="text/javascript">
								alert("PERINTAH INI MELIBATKAN PEMEGANG AMANAH BAGI HARTA TAK ALIH. SILA PASTIKAN BORANG HH DICETAK.");
							</script>
                      	#end
                      	 
                      #end-->
                        <td align="center" colspan="2"> #if ($mode == 'update')
                          #if ($modePopup == 'update')
                          <input name="cmdSimpanKemaskiniHTAPKT" id="cmdSimpanKemaskiniHTAPKT" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHTAPKT()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHTAPKT').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdPembahagianRata" id="cmdPembahagianRata" value="Pembahagian Separa" onClick="javascript:pembahagianSepara()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdPembahagianRata').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdPembahagianFaraid" id="cmdPembahagianFaraid" value="Pembahagian Faraid" onClick="javascript:pembahagianFaraid()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdPembahagianFaraid').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdKosongkanPembahagian" id="cmdKosongkanPembahagian" value="Kosongkan" onClick="javascript:kosongkanPembahagian()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdKosongkanPembahagian').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHTAPKT" id="cmdBatalHTAPKT" value="Kembali" onClick="javascript:batalHTAPKT()"/>
                          #end
                          #end
                          
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHTAPKT" id="cmdBatalHTAPKT" value="Kembali" onClick="javascript:batalHTAPKT()"/>
                          #end </td>
                      </tr>
                    </table></td>
                </tr>
                #end 
                <!-- END OPEN POPUP HTAPKT --> 
                #if ($flagPopup != 'openHAPKT') 
                <!-- START LIST HTAPKT -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                      #if($flagAdaHTAPKT == '1')
                      
                      #if ($SenaraiHTAPKT.size() > 10)
                      <div style="width:100%;height:190;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="65%">KETERANGAN HAKMILIK</td>
                            <td width="15%" align="center">STATUS PEMILIKAN</td>
                            <td width="15%" align="center">JENIS TANAH</td>
                          </tr>
                          #set ($listHTAPKT = "")
                          #foreach ($listHTAPKT in $SenaraiHTAPKT)
                          #if ($listHTAPKT.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHTAPKT.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else 
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTAPKT.bil</td>
                            #if($listHTAPKT.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTAPKT.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTAPKT('$listHTAPKT.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPKT.keteranganHakmilik</font><font color="#000000">$listHTAPKT.keterangan</font></a></td>
                            <td class="$row" align="center">$listHTAPKT.kodPB</td>
                            <td class="$row" align="center">$listHTAPKT.jenisTanah</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHTAPKT.size() > 10) </div>
                      #end
                      
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HTAPKT --> 
                #end 
                <!-- START OPEN POPUP HAPKT --> 
                #if ($flagPopup == 'openHAPKT')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <tr>
                        <td width="30%" align="right">JENIS HARTA ALIH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisHA</font><font color="#000000">$headerDetail.keterangan</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENAMA/MODEL/BANK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenama</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">NO. PENDAFTARAN/AKAUN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.noDaftar</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"><table width="60%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr>
                              <td> #if ($ListHAPKTDTL.size() > 5)
                                <div style="width:100%;height:133;overflow:auto"> #end
                                  <table align="center" width="100%" border="0">
                                    <tr class="table_header"> #if ($listSize != '0')
                                      <td scope="row" width="1%">&nbsp;</td>
                                      #end
                                      <td scope="row" width="4%" align="center">BIL</td>
                                      <td width="95%">NAMA PENTADBIR</td>
                                    </tr>
                                    #set ($list = "")
                                    #foreach ($list in $ListHAPKTDTL)
                                    #if ($list.bil == '')
                                    #set( $row = "row1" )
                                    #elseif (($list.bil % 2) != 0)
                                    #set( $row = "row1" )
                                    #else 
                                    #set( $row = "row2" )
                                    #end
                                    <tr> #if ($listSize != '0')
                                      #if($list.flag == 'Y')
                                      #set($checked = 'checked')
                                      #else
                                      #set($checked = '')
                                      #end
                                      <td class="$row"><input type="checkbox" value="$list.idOB" name="idspentadbir" $checked $disabled onClick="doUpdateCheck('$list.bil')"/></td>
                                      #end
                                      <td class="$row" align="center">$list.bil</td>
                                      #if ($list.statusHidup == '1')
                                      <td class="$row">$list.namaOB &nbsp;&nbsp;<span class="style1"><strong>(MENINGGAL DUNIA)</strong></span></td>
                                      #else
                                      <td class="$row">$list.namaOB</td>
                                      #end </tr>
                                    #end
                                  </table>
                                  #if ($ListHAPKTDTL.size() > 5) </div>
                                #end </td>
                            </tr>
                            <tr>
                              <td>&nbsp;</td>
                            </tr>
                            <tr>
                              <td align="center"> #if ($mode == 'update')
                                #if ($modePopup == 'update')
                                <input name="cmdSimpanKemaskiniHAPKT"  id="cmdSimpanKemaskiniHAPKT" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHAPKT()"/>
                                #if($flag_kemaskini_selesai != "yes") 
                                <script>
                                        document.getElementById('cmdSimpanKemaskiniHAPKT').style.display = "none";
                                        </script> 
                                #end
                                <input type="button" name="cmdBatalHAPKT" id="cmdBatalHAPKT" value="Kembali" onClick="javascript:batalHAPKT()"/>
                                #end
                                #end
                                
                                #if ($mode == 'view')
                                <input type="button" name="cmdBatalHAPKT" id="cmdBatalHAPKT" value="Kembali" onClick="javascript:batalHAPKT()"/>
                                #end </td>
                            </tr>
                          </table></td>
                      </tr>
                    </table></td>
                </tr>
                #end 
                <!-- END OPEN POPUP HAPKT --> 
                #if ($flagPopup != 'openHTAPKT') 
                <!-- START LIST HAPKT -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>
                      #if($flagAdaHAPKT == '1')
                      <table align="center" width="100%">
                        <tr class="table_header">
                          <td scope="row" width="5%" align="center">BIL</td>
                          <td width="35%">JENIS HARTA ALIH</td>
                          <td width="30%">JENAMA/MODEL/BANK</td>
                          <td width="30%">NO. PENDAFTARAN/AKAUN</td>
                        </tr>
                        #set ($listHAPKT = "")
                        #foreach ($listHAPKT in $SenaraiHAPKT)
                        #if ($listHAPKT.bil == '')
                        #set( $row = "row1" )
                        #elseif (($listHAPKT.bil % 2) != 0)
                        #set( $row = "row1" )
                        #else 
                        #set( $row = "row2" )
                        #end
                        <tr>
                          <td class="$row" align="center">$listHAPKT.bil</td>
                          #if($listHAPKT.idPerintahHAOBMST == '')
                          <td class="$row">$listHAPKT.jenisHA</td>
                          #else
                          <td class="$row"><a href="javascript:paparHAPKT('$listHAPKT.idPerintahHAOBMST')"><font color="#0000FF">$listHAPKT.jenisHA</font><font color="#000000">$listHAPKT.keterangan</font></a></td>
                          <td class="$row">$listHAPKT.jenama</td>
                          <td class="$row">$listHAPKT.noDaftar</td>
                          #end </tr>
                        #end
                      </table>
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HAPKT --> 
                #end
              </table>
            </div>
            <!-- END CONTENT PERINTAH KUASA TADBIR --> 
            <!-- START CONTENT PERINTAH LELONG -->
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HTAPL --> 
                #if ($flagPopup == 'openHTAPL')
                #set ($maklumatHTAPL = "") 
                #foreach($maklumatHTAPL in $BeanMaklumatHTAPL)
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Keterangan Hakmilik</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">$maklumatHTAPL.keteranganHakmilik</td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' && $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td width="28%">Jenis Lelong</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%"><input type="radio" name="sorJenisLelong" value="A" id="sorJenisLelong" $checkedA $disabled/>
                          Lelong Awam
                          <input type="radio" name="sorJenisLelong" value="T" id="sorJenisLelong" $checkedT $disabled/>
                          Lelong Tender </td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' && $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Tarikh Jualan</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%"><input type="text" name="txdTarikhJualan" size="10" id="txdTarikhJualan" readonly="readonly" value="$maklumatHTAPL.tarikhJualan" class="$inputTextClass"/>
                          #if($mode == 'update' && $modePopup == 'update') <a href="javascript:displayDatePicker('txdTarikhJualan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end </td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' && $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Harga Rizab</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">RM
                          <input type="text" name="txtHargaRizab" id="txtHargaRizab" $readonly class="$inputTextClass" value="$maklumatHTAPL.hargaRizab" onBlur="validateCurrency(this,this.value,'$maklumatHTAPL.hargaRizab');"/></td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' && $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Amaun</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">RM
                          <input type="text" name="txtAmaun" id="txtAmaun" $readonly class="$inputTextClass" value="$maklumatHTAPL.amaun" onBlur="validateCurrency(this,this.value,'$maklumatHTAPL.amaun');"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Catatan</td>
                        <td valign="top" width="1%">:</td>
                        <td valign="top" width="70%"><textarea name="txtCatatanHTAPL" cols="45" rows="5" id="txtCatatanHTAPL" $readonly class="$inputTextClass" >$maklumatHTAPL.catatan</textarea></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2"> #if ($mode == 'update')
                          #if ($modePopup == 'update')
                          <input name="cmdSimpanKemaskiniHTAPL" id="cmdSimpanKemaskiniHTAPL" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHTAPL()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHTAPL').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHTAPL" id="cmdBatalHTAPL" value="Kembali" onClick="javascript:batalHTAPL()"/>
                          #end
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHTAPL" id="cmdBatalHTAPL" value="Kembali" onClick="javascript:batalHTAPL()"/>
                          #end </td>
                      </tr>
                      #if ($mode == 'update' || $modePopup == 'update')
                      <tr>
                        <td colspan="4" height="50px" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
                      </tr>
                      #end
                    </table></td>
                </tr>
                #end
                #end 
                <!-- END OPEN POPUP HTAPL --> 
                #if ($flagPopup != 'openHAPL') 
                <!-- START LIST HTAPL -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                      #if($flagAdaHTAPL == '1')
                      
                      #if ($SenaraiHTAPL.size() > 10)
                      <div style="width:100%;height:250;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="40%">KETERANGAN HAKMILIK</td>
                            <td width="15%">JENIS LELONG</td>
                            <td width="10%" align="center">TARIKH JUALAN</td>
                            <td width="15%" align="right">HARGA RIZAB (RM)</td>
                            <td width="15%" align="right">AMAUN (RM)</td>
                          </tr>
                          #set ($listHTAPL = "")
                          #foreach ($listHTAPL in $SenaraiHTAPL)
                          #if ($listHTAPL.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHTAPL.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else 
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTAPL.bil</td>
                            #if($listHTAPL.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTAPL.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTAPL('$listHTAPL.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPL.keteranganHakmilik</font></a></td>
                            <td class="$row">$listHTAPL.jenisLelong</td>
                            <td class="$row">$listHTAPL.tarikhJualan</td>
                            <td class="$row" align="right">$listHTAPL.hargaRizab</td>
                            <td class="$row" align="right">$listHTAPL.amaun</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHTAPL.size() > 10) </div>
                      #end
                      
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HTAPL --> 
                #end 
                <!-- START OPEN POPUP HAPL --> 
                #if ($flagPopup == 'openHAPL')
                #set ($maklumatHAPL = "") 
                #foreach($maklumatHAPL in $BeanMaklumatHAPL)
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Jenis Harta Alih</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">$maklumatHAPL.jenisHA</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Jenama/Model/Bank</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">$maklumatHAPL.jenama</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">No. Pendaftaran/Akaun</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">$maklumatHAPL.noDaftar</td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' || $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td width="28%">Jenis Lelong</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%"><input type="radio" name="sorJenisLelongHAPL" value="A" id="sorJenisLelongHAPL" $checkedA $disabled/>
                          Lelong Awam
                          <input type="radio" name="sorJenisLelongHAPL" value="T" id="sorJenisLelongHAPL" $checkedT $disabled/>
                          Lelong Tender </td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' || $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Tarikh Jualan</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%"><input type="text" name="txdTarikhJualanHAPL" size="10" id="txdTarikhJualanHAPL" readonly="readonly" value="$maklumatHAPL.tarikhJualan" class="$inputTextClass"/>
                          #if($mode == 'update' || $modePopup == 'update') <a href="javascript:displayDatePicker('txdTarikhJualanHAPL',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end </td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' || $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Harga Rizab</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">RM
                          <input type="text" name="txtHargaRizabHAPL" id="txtHargaRizabHAPL" $readonly class="$inputTextClass" value="$maklumatHAPL.hargaRizab" onBlur="validateCurrency(this,this.value,'$maklumatHAPL.hargaRizab');"/></td>
                      </tr>
                      <tr>
                        <td width="1%">#if($mode == 'update' || $modePopup == 'update') <span class="style1">*</span> #end</td>
                        <td valign="top" width="28%">Amaun</td>
                        <td width="1%">:</td>
                        <td valign="top" width="70%">RM
                          <input type="text" name="txtAmaunHAPL" id="txtAmaunHAPL" $readonly class="$inputTextClass" value="$maklumatHAPL.amaun" onBlur="validateCurrency(this,this.value,'$maklumatHAPL.amaun');"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td valign="top" width="28%">Catatan</td>
                        <td valign="top" width="1%">:</td>
                        <td valign="top" width="70%"><textarea name="txtCatatanHTAPL" cols="45" rows="5" id="txtCatatanHTAPL" $readonly class="$inputTextClass" >$maklumatHAPL.catatan</textarea></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2"> #if ($mode == 'update')
                          #if ($modePopup == 'update')
                          <input name="cmdSimpanKemaskiniHAPL" id="cmdSimpanKemaskiniHAPL"  type="button" value="Simpan" onClick="javascript:simpanKemaskiniHAPL()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHAPL').style.display = "none";
                                        </script> 
                          #end
                          <input type="button" name="cmdBatalHAPL" id="cmdBatalHAPL" value="Kembali" onClick="javascript:batalHAPL()"/>
                          #end
                          #end
                          #if ($mode == 'view')
                          <input type="button" name="cmdBatalHAPL" id="cmdBatalHAPL" value="Kembali" onClick="javascript:batalHAPL()"/>
                          #end </td>
                      </tr>
                      #if ($modePopup == 'new' || $modePopup == 'update')
                      <tr>
                        <td colspan="4" height="50px" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
                      </tr>
                      #end
                    </table></td>
                </tr>
                #end
                #end 
                <!-- END OPEN POPUP HAPL --> 
                #if ($flagPopup != 'openHTAPL') 
                <!-- START LIST HAPL -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>
                      #if($flagAdaHAPL == '1')
                      <table align="center" width="100%">
                        <tr class="table_header">
                          <td scope="row" width="5%" align="center">BIL</td>
                          <td width="20%">JENIS HARTA ALIH</td>
                          <td width="20%">NO. PENDAFTARAN/AKAUN</td>
                          <td width="15%">JENIS LELONG</td>
                          <td width="10%" align="center">TARIKH JUALAN</td>
                          <td width="15%" align="right">HARGA RIZAB (RM)</td>
                          <td width="15%" align="right">AMAUN (RM)</td>
                        </tr>
                        #set ($listHAPL = "")
                        #foreach ($listHAPL in $SenaraiHAPL)
                        #if ($listHAPL.bil == '')
                        #set( $row = "row1" )
                        #elseif (($listHAPL.bil % 2) != 0)
                        #set( $row = "row1" )
                        #else 
                        #set( $row = "row2" )
                        #end
                        <tr>
                          <td class="$row" align="center">$listHAPL.bil</td>
                          #if($listHAPL.idPerintahHAOBMST == '')
                          <td class="$row">$listHAPL.jenisHA</td>
                          #else
                          <td class="$row"><a href="javascript:paparHAPL('$listHAPL.idPerintahHAOBMST')"><font color="#0000FF">$listHAPL.jenisHA</font><font color="#000000">$listHAPL.keterangan</font></a></td>
                          <td class="$row">$listHAPL.noDaftar</td>
                          <td class="$row">$listHAPL.jenisLelong</td>
                          <td class="$row">$listHAPL.tarikhJualan</td>
                          <td class="$row" align="right">$listHAPL.hargaRizab</td>
                          <td class="$row" align="right">$listHAPL.amaun</td>
                          #end </tr>
                        #end
                      </table>
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HAPL --> 
                #end
              </table>
            </div>
            <!-- END CONTENT PERINTAH LELONG --> 
            <!-- START CONTENT PERINTAH FARAID -->
            <div class="TabbedPanelsContent">
              <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <!-- START OPEN POPUP HTAPF --> 
                #if ($flagPopup == 'openHTAPF')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <tr>
                        <td width="30%" align="right">KETERANGAN HAKMILIK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.keteranganHakmilik</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">STATUS PEMILIKAN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisPB</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENIS TANAH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisTanah</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"><table width="95%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr class="table_header">
                              <td align="center" width="5%">BIL</td>
                              <td width="60%">NAMA WARIS</td>
                              <td width="20%">STATUS WARIS</td>
                              <td align="center" width="15%">BAHAGIAN WARIS</td>
                            </tr>
                            #set ($listHTAPFDTL = "")
                            #foreach ($listHTAPFDTL in $SenaraiHTAPFDTL)
                            <tr>
                              <input name="idspentadbir" type="hidden" value="$listHTAPFDTL.idOB">
                              <td align="center">$listHTAPFDTL.bil</td>
                              #if ($listHTAPFDTL.status == '')
                              <td>$listHTAPFDTL.namaWaris</td>
                              #else
                              <td><a href="javascript:updatePAPTHTAPF('$listHTAPFDTL.idOB','$idPermohonanSimati','$idSimati','$listHTAPFDTL.status','$idPerintah','$mode','$idHTA','$flag_kemaskini_selesai')"><font color="#0000FF">$listHTAPFDTL.namaWaris</font></a></td>
                              #end
                              <td>$listHTAPFDTL.status</td>
                              <td align="center">$listHTAPFDTL.bahagianWaris</td>
                            </tr>
                            #end
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="15%"><strong>$jumlahBahagian</strong></td>
                            </tr>
                            <tr>
                              <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4"><i>&nbsp;&nbsp;&nbsp;&nbsp;<font color="#ff0000">Perhatian</font> : Sila klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2" align="center"> #if($mode == 'update')
                          <input name="cmdSimpanKemaskiniHTAPF" id="cmdSimpanKemaskiniHTAPF" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHTAPF()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHTAPF').style.display = "none";
                                        </script> 
                          #end
                          #end
                          <input type="button" name="cmdBatalHTAPF" id="cmdBatalHTAPF" value="Kembali" onClick="javascript:batalHTAPF()"/></td>
                      </tr>
                    </table></td>
                </tr>
                #end 
                <!-- END OPEN POPUP HTAPF --> 
                #if ($flagPopup != 'openHAPF') 
                <!-- START LIST HTAPF -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA TAK ALIH</strong></legend>
                      #if($flagAdaHTAPF == '1')
                      
                      #if ($SenaraiHTAPF.size() > 10)
                      <div style="width:100%;height:190;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="65%">KETERANGAN HAKMILIK</td>
                            <td width="15%" align="center">STATUS PEMILIKAN</td>
                            <td width="15%" align="center">JENIS TANAH</td>
                          </tr>
                          #set ($listHTAPF = "")
                          #foreach ($listHTAPF in $SenaraiHTAPF)
                          #if ($listHTAPF.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHTAPF.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else 
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHTAPF.bil</td>
                            #if($listHTAPF.idPerintahHTAOBMST == '')
                            <td class="$row">$listHTAPF.keteranganHakmilik</td>
                            #else
                            <td class="$row"><a href="javascript:paparHTAPF('$listHTAPF.idPerintahHTAOBMST')"><font color="#0000FF">$listHTAPF.keteranganHakmilik</font></a></td>
                            <td class="$row" align="center">$listHTAPF.kodPB</td>
                            <td class="$row" align="center">$listHTAPF.jenisTanah</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHTAPF.size() > 10) </div>
                      #end 
                      
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA TAK ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HTAPF --> 
                #end 
                <!-- START OPEN POPUP HAPF --> 
                #if ($flagPopup == 'openHAPF')
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                      #set ($headerDetail = "")
                      #foreach($headerDetail in $BeanHeaderDetail)
                      <tr>
                        <td width="30%" align="right">JENIS HARTA ALIH :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenisHA</font><font color="#000000">$headerDetail.keterangan</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">JENAMA/MODEL/BANK :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.jenama</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">NO. PENDAFTARAN/AKAUN :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.noDaftar</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right">BAHAGIAN SIMATI :</td>
                        <td width="70%" align="left"><font color="blue">$headerDetail.bahagianSimati</font></td>
                      </tr>
                      <tr>
                        <td width="30%" align="right" valign="top">CATATAN :</td>
                        <td width="70%" align="left"><textarea name="txtCatatan" cols="50" rows="5" id="txtCatatan" $readonly class="$inputTextClass" >$headerDetail.catatan</textarea></td>
                      </tr>
                      #end
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2"><table width="95%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr class="table_header">
                              <td align="center" width="5%">BIL</td>
                              <td width="60%">NAMA WARIS</td>
                              <td width="20%">STATUS WARIS</td>
                              <td align="center" width="15%">BAHAGIAN WARIS</td>
                            </tr>
                            #set ($listHAPFDTL = "")
                            #foreach ($listHAPFDTL in $SenaraiHAPFDTL)
                            <tr>
                              <input name="idspentadbir" type="hidden" value="$listHAPFDTL.idOB">
                              <td align="center">$listHAPFDTL.bil</td>
                              #if ($listHAPFDTL.status == '')
                              <td>$listHAPFDTL.namaWaris</td>
                              #else
                              <td><a href="javascript:updatePAPTHAPF('$listHAPFDTL.idOB','$idPermohonanSimati','$idSimati','$listHAPFDTL.status','$idPerintah','$mode','$idHA','$flag_kemaskini_selesai')"><font color="#0000FF">$listHAPFDTL.namaWaris</font></a></td>
                              #end
                              <td >$listHAPFDTL.status</td>
                              <td align="center">$listHAPFDTL.bahagianWaris</td>
                            </tr>
                            #end
                            <tr class="table_header">
                              <td width="5%">&nbsp;</td>
                              <td colspan="2" align="left"><strong>JUMLAH</strong></td>
                              <td align="center" width="15%"><strong>$jumlahBahagian</strong></td>
                            </tr>
                            <tr>
                              <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                              <td valign="bottom" colspan="4"><i>&nbsp;&nbsp;&nbsp;&nbsp;<font color="#ff0000">Perhatian</font> : Sila klik pada nama waris untuk melantik pemegang amanah / pentadbir bagi waris yang menerima bahagian.</i></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="2" align="center"> #if($mode == 'update')
                          <input name="cmdSimpanKemaskiniHAPF" id="cmdSimpanKemaskiniHAPF" type="button" value="Simpan" onClick="javascript:simpanKemaskiniHAPF()"/>
                          #if($flag_kemaskini_selesai != "yes") 
                          <script>
                                        document.getElementById('cmdSimpanKemaskiniHAPF').style.display = "none";
                                        </script> 
                          #end
                          #end
                          <input type="button" name="cmdBatalHAPF" id="cmdBatalHAPF" value="Kembali" onClick="javascript:batalHAPF()"/></td>
                      </tr>
                    </table></td>
                </tr>
                #end 
                <!-- END OPEN POPUP HAPF --> 
                #if ($flagPopup != 'openHTAPF') 
                <!-- START LIST HAPF -->
                <tr>
                  <td><fieldset>
                      <legend><strong>SENARAI PEMBAHAGIAN HARTA ALIH</strong></legend>
                      #if($flagAdaHAPF == '1')
                      
                      #if ($SenaraiHAPF.size() > 10)
                      <div style="width:100%;height:190;overflow:auto"> #end
                        <table align="center" width="100%">
                          <tr class="table_header">
                            <td scope="row" width="5%" align="center">BIL</td>
                            <td width="35%">JENIS HARTA ALIH</td>
                            <td width="30%">JENAMA/MODEL/BANK</td>
                            <td width="30%">NO. PENDAFTARAN/AKAUN</td>
                          </tr>
                          #set ($listHAPF = "")
                          #foreach ($listHAPF in $SenaraiHAPF)
                          #if ($listHAPF.bil == '')
                          #set( $row = "row1" )
                          #elseif (($listHAPF.bil % 2) != 0)
                          #set( $row = "row1" )
                          #else 
                          #set( $row = "row2" )
                          #end
                          <tr>
                            <td class="$row" align="center">$listHAPF.bil</td>
                            #if($listHAPF.idPerintahHAOBMST == '')
                            <td class="$row">$listHAPF.jenisHA</td>
                            #else
                            <td class="$row"><a href="javascript:paparHAPF('$listHAPF.idPerintahHAOBMST')"><font color="#0000FF">$listHAPF.jenisHA.toUpperCase()</font><font color="#000000">$listHAPF.keterangan</font></a></td>
                            <td class="$row">$listHAPF.jenama.toUpperCase()</td>
                            <td class="$row">$listHAPF.noDaftar</td>
                            #end </tr>
                          #end
                        </table>
                        #if ($SenaraiHAPF.size() > 10) </div>
                      #end
                      
                      #else <br>
                      &nbsp;&nbsp;<font color="black">TIADA PEMBAHAGIAN HARTA ALIH BAGI PERINTAH INI</font> <br>
                      <br>
                      #end
                    </fieldset></td>
                </tr>
                <!-- END LIST HAPF --> 
                #end
              </table>
            </div>
            <!-- END CONTENT PERINTAH FARAID --> 
          </div>
        </div>
      </fieldset></td>
  </tr>
  <!-- END PEMBAHAGIAN HARTA --> 
  #end
  <!-- arief add TANDATANGAN DIGITAL bagi PERINTAH OPEN -->
  <tr>
  	<td><a name="Tandatangan Digital Perintah"></a>
    	<fieldset>
        	<legend><strong> TANDATANGAN DIGITAL </strong></legend>
      			<div class="TabbedPanelsContent"> <br/>
    				#parse("/app/ppk/tindakanPegawaiPerintahSek8.jsp")
      			</div>
      	</fieldset>
    </td>
</tr>
<!-- arief add TANDATANGAN DIGITAL bagi PERINTAH CLOSE -->

  
  <tr>
    <td>&nbsp;</td>
  </tr>
  
            <!-- Mula Ruangan isi Laporan Penghantaran Perintah -->
            
            <!-- Akhir Ruangan isi Laporan Penghantaran Perintah -->
 
  <!--  Salnizam edit start untuk tambah blinking alert -->
<script type="text/javascript">
  function blink() {
    var blinks = document.getElementsByTagName('blink');
    for (var i = blinks.length - 1; i >= 0; i--) {
      var s = blinks[i];
      s.style.visibility = (s.style.visibility === 'visible') ? 'hidden' : 'visible';
    }
    window.setTimeout(blink, 1000);
  }
  if (document.addEventListener) document.addEventListener("DOMContentLoaded", blink, false);
  else if (window.addEventListener) window.addEventListener("load", blink, false);
  else if (window.attachEvent) window.attachEvent("onload", blink);
  else window.onload = blink;
</script>
  <!-- printalert = $printalert  -->
  #if ($printalert == '1')
  <tr><td><!-- <font color="red"><b>
<blink>Perintah ini melibatkan pemegang amanah bagi harta tak alih. Sila pastikan Borang HH dicetak.</blink></b></font> -->
  
  </td></tr>
  #end
  <!--  Salnizam edit end untuk tambah blinking alert -->
  <tr>
  
  
  
    <td align="center"> #if ($flagPopup == '')
      #if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '')
       #if ($idStatus == '41' && $userRole != "user_ppk")
      <input type="button" name="cmdHantar" id="cmdHantar" value="Selesai Permohonan" onClick="javascript:selesaiPermohonan('$flagFromSenaraiFailSek8','$flagFromSenaraiPermohonanSek8','$noFail')"/>
      #end
      #if ($flagSelesaiPembahagian == 'Y')
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembali()"/>
      #end
      #if ($flagFromSenaraiFailSek8 == 'yes')
     #if ($flagSelesaiPembahagian == 'Y' )
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$noFail')"/>
      #end 
      #if ($flagFromSenaraiPermohonanSek8 == 'yes')
      #if ($idStatus == '41' && $userRole != "user_ppk" )
      <input type="button" name="cmdHantar" id="cmdHantar" value="Selesai Permohonan" onClick="javascript:selesaiPermohonan('$flagFromSenaraiFailSek8','$flagFromSenaraiPermohonanSek8','$noFail')"/>
      #end
      #if ($flagSelesaiPembahagian == 'Y' )
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$noFail')"/>
      #end 
      #end 
      <!-- START INTERGRASI ETANAH -->
      #if ($flagSelesaiPembahagian == 'Y')
      <input type="button" name="cmdJanaPerintah" id="cmdJanaPerintah" value="Integrasi Sistem eTanah" onClick="javascript:janaPerintah('$idFail')"/>
      #end
      <!-- END INTERGRASI ETANAH -->
      <!-- arief add open NOTIFIKASI EMAIL-->
      #if ($flagStatusFail == "SELESAI")
      <input type="button" name="cmdNotiEmail" id="cmdNotiEmail" value="Email Perintah" onClick="javascript:emailPerintah('$idFail')"/>
      #end
      <!-- arief add close NOTIFIKASI EMAIL-->
      <br>
      <br>
      <span class="style2"><blink>$systemMsg</blink></span></td>
  </tr>
</table>
<fieldset id="tableReport" style="display:none;"-->
  <legend><strong>SENARAI LAPORAN</strong></legend>
  <table width="100%" border="0" cellspacing="2" cellpadding="2">
	
  #if ($PPT == "Ada" || $flagAdaHTAPT == 1)
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBorangE('$idFail','$idPermohonanSimati','$idSimati','$idPerintahHTAOBMST')"> Borang E </a></td>
    </tr>
  #end
    <!-- <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBorangEE('$idFail')"> Borang EE </a></td>
    </tr> -->
    #if ($PPT != "Ada" && $PKP == "Ada" )
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBorangF('$idFail')"> Borang F </a></td>
    </tr>
    #end  
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBorangH('$idFail')"><font color="blue"> Borang H </font></a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakBorangHH('$idFail')"> Borang HH </a><!-- <font color="red">Sila pastikan Borang HH dicetak</font> --></td>
    </tr>
    
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakNotisKeluarGeranPot()"> Notis Pengeluaran Geran (Cetakan Secara Potrait)</a></td>
    </tr>
    <!-- Edited by Salnizam SDS 10.2.3.6.5 A 
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakNotisKeluarGeranLand()"> Notis Pengeluaran Geran (Cetakan Secara Landskap)</a></td>
    </tr>
    -->
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:hantarNotisKeluarGeran()"> Hantar Notis Pengeluaran Geran ke Mahkamah Tinggi</a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakSuratIringanPerintahTerengganu('$noFail','$idPerbicaraan')"> Surat Iringan Perintah III - Pentadbir Tanah </a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakSuratIringanNotisPengeluaranGeran('$noFail','$idPerbicaraan')"> Surat Iringan Notis Pengeluaran Geran </a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakSuratIringanPerintahKuasaTadbir('$noFail','$idPerbicaraan')"> Surat Iringan Perintah Kuasa Tadbir I</a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakSuratIringanPerintahKuasaTadbirII('$noFail','$idPerbicaraan')"> Surat Iringan Perintah Kuasa Tadbir II</a></td>
    </tr>
        <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakSuratKemaskiniPerintahI('$noFail','$idFail')"> Surat Iringan Kepada Pempetisyen/Pemohon</a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakSuratKemaskiniPerintah2('$noFail','$idFail')"> Surat Iringan Kepada Pendaftar/Pentadbir Tanah</a></td>
    </tr>
    <tr>
      <td ><a href="#" class="style2" onClick="javascript:cetakRekodPindaan('$usid')"> Rekod Pembetulan/Pindaan</a></td>
    </tr>
   
  </table>
</fieldset>
#parse("app/ppk/headerppk_script.jsp")
<p align="right">CL - 01 - 64</p>
<div id="pembahagianSepara_result"></div>
<div id="kosongkanPembahagian_result"></div>
<div id="samakanPembawah_result"></div>
<div id="checkPembahagianGSA_result"></div>
<div id="pembahagianFaraid_result"></div>
<!-- START SCRIPT FOR TAB --> 
<script type="text/javascript">
#if (($flagPopup == '' || $flagPopup == 'openHTA' || $flagPopup == 'openHTATH' || $flagPopup == 'openHA') && ($flagPopup != 'openHTAPT' || $flagPopup != 'openHAPT' || $flagPopup != 'openHTAPKT' || $flagPopup != 'openHAPKT' || $flagPopup != 'openHTAPL' || $flagPopup != 'openHAPL' || $flagPopup != 'openHTAPF' || $flagPopup != 'openHAPF'))
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end

#if($flagPopup == '' || $flagPopup == 'openHTAPT' || $flagPopup == 'openHAPT'  || $flagPopup == 'openHTAPKT' || $flagPopup == 'openHAPKT'|| $flagPopup == 'openHTAPL' || $flagPopup == 'openHAPL'  || $flagPopup == 'openHTAPF' || $flagPopup == 'openHAPF')
	var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabLower});
#end
</script> 
<!-- END SCRIPT FOR TAB --> 
<!-- START SCRIPT FOR SCREEN --> 
<script>

function cetakBorangH(idFail) {
	//var url = "../servlet/ekptg.report.ppk.BorangH_Perintah?idfail="+idFail; 
	
	var url = "../servlet/ekptg.report.ppk.BorangH?idfail="+idFail+"&idobminor="+"&BorangH_Notis=T";;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function test(){
	if (document.${formName}.chkWarisHilang.checked == false){
		document.${formName}.txtBAHilang.value = "0";
		document.${formName}.txtBBHilang.value = "0";
		document.${formName}.txtBAHilang.disabled = "true";
		document.${formName}.txtBBHilang.disabled = "true";
		document.${formName}.cmdLantikPT.disabled = "true";
	} else if (document.${formName}.chkWarisHilang.checked == true){
		document.${formName}.txtBAHilang.disabled = "";
		document.${formName}.txtBBHilang.disabled = "";
		document.${formName}.cmdLantikPT.disabled = "";
		
	}
}


function goToAnchor(nameAnchor){
     window.location.hash = nameAnchor;
}
function setSelectedTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function setSelectedTabLower(tabId) {
	document.${formName}.selectedTabLower.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function doCheckAll(){    
    if (document.${formName}.all.checked == true){
        if (document.${formName}.idspentadbir.length == null){
            document.${formName}.idspentadbir.checked = true;
        } else {
            for (i = 0; i < document.${formName}.idspentadbir.length; i++){
                document.${formName}.idspentadbir[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.idspentadbir.length == null){
            document.${formName}.idspentadbir.checked = false;
        } else {
            for (i = 0; i < document.${formName}.idspentadbir.length; i++){
                document.${formName}.idspentadbir[i].checked = false;
            }
        }
    }
}
function doUpdateCheckAll(){  
	var c = 0;
	if (document.${formName}.idspentadbir.length > 1){
		for (i = 0; i < document.${formName}.idspentadbir.length; i++){
			if (document.${formName}.idspentadbir[i].checked == false){
				c++
			}
		}
	} else {
		if (document.${formName}.idspentadbir.checked == false){
			c++
		}
	}
	  
	if(c > 0){
		document.${formName}.all.checked = false;
	} else {
		document.${formName}.all.checked = true;
	}				         
}
function doUpdateCheck(bil){  
	var b = parseInt(bil)-1;
	var c = 0;
	var j = 0;
	if (document.${formName}.idspentadbir.length > 1){
		for (i = 0; i < document.${formName}.idspentadbir.length; i++){
			if (document.${formName}.idspentadbir[i].checked == true){
				c++;
			}
		}
		if (c > 4){
		
			alert("Hanya 4 Pentadbir yang boleh dilantik.")
			
			for (j = 0; j < document.${formName}.idspentadbir.length; j++){
				if (b == j){
					document.${formName}.idspentadbir[j].checked = false;
				}
			}
		}
	}			         
}

function checkIt(){
	if (document.${formName}.radioPos[0].checked == true || document.${formName}.radioPos[1].checked == true )
	{	
		document.${formName}.radioJenis[1].checked = true;
	}	
}

function checkIt1(){
	if (document.${formName}.radioJenis[0].checked == true )
	{	
	    document.${formName}.radioPos[0].checked = false;
	    document.${formName}.radioPos[1].checked = false;
	}	
}

function Kemaskini(){
	document.${formName}.method="POST";
	document.${formName}.anchor.value = "hantarperintah";
	document.${formName}.hitButt.value="KemaskiniLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.submit();
}

function onchangemyList() {
	//document.${formName}.command.value = "semakDataLaporan";
	document.${formName}.hitButt.value = "onchangemyList";
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8"; 
	document.${formName}.submit();
}


function Batal(){
	document.${formName}.method="POST";
	document.${formName}.hitButt.value="Batal";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.submit();
}

function DoTheCheck() {
	
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	var str1  = document.${formName}.txtTarikh.value;
    
	var dm = document.${formName}.txtTarikh.value;
	
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10);
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(dt1, mon1, yr1);
    var jeniskp = document.${formName}.jeniskp.value;

   // alert("dt1 = "+dt1+" mon1 = "+mon1+" yr1 = "+yr1);
    
    if (document.${formName}.txtNamaPenghantarNotis.value == "")
	{
		alert("Sila masukkan Nama Penyerah");
		return;
	}
	
   
    
    if (document.${formName}.txtNamaPenerima.value == "")
	{
		alert("Sila masukkan Nama Penerima");
		return;
	}
    
    
	if (document.${formName}.radioJenis[0].checked == false && document.${formName}.radioJenis[1].checked == false ) {
		alert("Sila masukkan Jenis Serahan");
		document.${formName}.radioJenis[0].focus();
	}
	
	
	
	
	else{
		
		Simpan();
	}

}

function Simpan(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="POST";
		document.${formName}.hitButt.value="SimpanSerahan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
		document.${formName}.submit();
		//document.${formName}.modePopup.value = "";
		//alert("Sukses");
	}	
}


function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
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
function validateCurrency(elmnt,content,content2) {
	content = content.replace(/,/g,'');
	content2 = content2.replace(/,/g,'');
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function validateBahagianWaris(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num;
	return;
}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.actionPerintah.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.submit();
}
function kembaliSenaraiFail(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function kembaliSenaraiPermohonan(noFail) {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function selesaiPermohonan(fail,permohonan,noFail) {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButt.value = "selesaiPermohonan";
	document.${formName}.actionPerintah.value = "papar";
	
	document.${formName}.submit();
}
</script> 
<!-- END SCRIPT FOR SCREEN --> 
<!-- START SCRIPT FOR TAB ATAS --> 
<script>
<!--START FUNCTION HTA -->
function tambahHTA() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTA";
	document.${formName}.modePopup.value = "new";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanBaruHTA(param) {
	if(document.${formName}.socJenisPerintahHTA.value == ""){
		alert('Sila pilih Jenis Perintah.');
  		document.${formName}.socJenisPerintahHTA.focus(); 
		return; 
	}
	
	if (param != 0){	
		
		var c = 0;
		if (document.${formName}.idspentadbir.length > 1){
			for (i = 0; i < document.${formName}.idspentadbir.length; i++){
				if (document.${formName}.idspentadbir[i].checked == true){
					c++
				}
			}
		} else {
			if (document.${formName}.idspentadbir.checked == true){
				c++
			}
		}
		if(c == 0){
			alert('Sila pilih Keterangan Hakmilik.');
			return;
		}
	} else {
		alert('Tiada Keterangan Hakmilik untuk didaftarkan.');
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTA";
	document.${formName}.modePopup.value = "new";
	document.${formName}.hitButt.value = "simpanBaruHTA";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function batalHTA() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function paparHTA(idHTA) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTA";
	document.${formName}.modePopup.value = "update";
	document.${formName}.idHTA.value = idHTA;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanKemaskiniHTA() {
	if(document.${formName}.socJenisPerintahHTA.value == ""){
		alert('Sila pilih Jenis Perintah.');
  		document.${formName}.socJenisPerintahHTA.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTA";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButt.value = "simpanKemaskiniHTA";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function hapusHTA() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButt.value = "hapusHTA";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
<!--END FUNCTION HTA -->

<!--START FUNCTION HTATH -->
function paparHTATH(idHTA) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTATH";
	document.${formName}.modePopup.value = "update";
	document.${formName}.idHTA.value = idHTA;
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanKemaskiniHTATH() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTATH";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButt.value = "simpanKemaskiniHTATH";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function hapusHTATH() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButt.value = "hapusHTATH";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function batalHTATH() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
<!--END FUNCTION HTATH -->

<!--START FUNCTION HA -->
function tambahHA() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHA";
	document.${formName}.modePopup.value = "new";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function paparHA(idHA) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHA";
	document.${formName}.modePopup.value = "update";
	document.${formName}.idHA.value = idHA;
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanBaruHA(param) {
	if(document.${formName}.socJenisPerintahHA.value == ""){
		alert('Sila pilih Jenis Perintah.');
  		document.${formName}.socJenisPerintahHA.focus(); 
		return; 
	}
	
	if (param != 0){	
		
		var c = 0;
		if (document.${formName}.idspentadbir.length > 1){
			for (i = 0; i < document.${formName}.idspentadbir.length; i++){
				if (document.${formName}.idspentadbir[i].checked == true){
					c++
				}
			}
		} else {
			if (document.${formName}.idspentadbir.checked == true){
				c++
			}
		}
		if(c == 0){
			alert('Sila pilih Harta Alih.');
			return;
		}
	} else {
		alert('Tiada Harta Alih untuk didaftarkan.');
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHA";
	document.${formName}.modePopup.value = "new";
	document.${formName}.hitButt.value = "simpanBaruHA";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function batalHA() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function simpanKemaskiniHA() {
	if(document.${formName}.socJenisPerintahHA.value == ""){
		alert('Sila pilih Jenis Perintah.');
  		document.${formName}.socJenisPerintahHA.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHA";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButt.value = "simpanKemaskiniHA";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
function hapusHA() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButt.value = "hapusHA";
	document.${formName}.anchor.value = "tabUpper";
	document.${formName}.submit();
}
<!--END FUNCTION HA -->
</script> 
<!-- END SCRIPT FOR TAB ATAS --> 
<!-- START SCRIPT FOR TAB BAWAH --> 
<script>

<!-- START FUNCTION PERINTAH TERUS -->

	<!-- START FUNCTION HTA -->
function paparHTAPT(id) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.idHTA.value = id;
	document.${formName}.flagPopup.value = "openHTAPT";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanKemaskiniHTAPT() {
	
	
	//alert('validateGSA : '+validateGSA());
	
	var a = parseInt(document.${formName}.txtJumlahBA.value);
	var b = parseInt(document.${formName}.txtJumlahBB.value);
	
	if ( a > b ){
		alert("Jumlah Pembahagian Telah Melebihi 1");
		document.${formName}.actionPerintah.value = "papar";
		return;
	
	} else if ( a < b ){
		
		//if ( !window.confirm("Jumlah Pembahagian Masih Kurang Dari 1. Adakah Anda Pasti ?") ){
			alert("Jumlah Pembahagian Masih Kurang Dari 1");
			document.${formName}.actionPerintah.value = "papar";
			return;
		//}
	} 
	else if (validateGSA()==false)
	{
		return;
		
	}	
	else {
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPerintah.value = "papar";
			return;
		}
	}
	
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTAPT";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.hitButt.value = "simpanKemaskiniHTAPT";
	document.${formName}.submit();
}
function batalHTAPT() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function updatePAPTHTAPT(idOB,idPermohonanSimati,idSimati,statusWaris,idPerintah,mode,idPerintahHTAOBMST,flag_kemaskini_selesai,idfail) {

	if (statusWaris == 'HILANG'){
		var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPTHTA?idOB="+idOB+"&idPermohonanSimati="+idPermohonanSimati+"&idSimati="+idSimati+"&statusWaris="+statusWaris+"&idPerintah="+idPerintah+"&mode="+mode+"&idPerintahHTAOBMST="+idPerintahHTAOBMST+"&flag_kemaskini_selesai="+flag_kemaskini_selesai+"&idfail="+idfail;
	} else {
		var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPAHTA?idOB="+idOB+"&idPermohonanSimati="+idPermohonanSimati+"&idSimati="+idSimati+"&statusWaris="+statusWaris+"&idPerintah="+idPerintah+"&mode="+mode+"&idPerintahHTAOBMST="+idPerintahHTAOBMST+"&flag_kemaskini_selesai="+flag_kemaskini_selesai+"&idfail="+idfail;
	}
	
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
	<!-- START FUNCTION HTA -->
	
	<!-- START FUNCTION HA -->
function paparHAPT(id) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.idHA.value = id;
	document.${formName}.flagPopup.value = "openHAPT";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanKemaskiniHAPT() {
	var a = parseInt(document.${formName}.txtJumlahBA.value);
	var b = parseInt(document.${formName}.txtJumlahBB.value);
	
	if ( a > b ){
		alert("Jumlah Pembahagian Telah Melebihi 1");
		document.${formName}.actionPerintah.value = "papar";
		return;
	
	} else if ( a < b ){
		
		if ( !window.confirm("Jumlah Pembahagian Masih Kurang Dari 1. Adakah Anda Pasti ?") ){
			document.${formName}.actionPerintah.value = "papar";
			return;
		}
	} else {
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPerintah.value = "papar";
			return;
		}
	}
	
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHAPT";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.hitButt.value = "simpanKemaskiniHAPT";
	document.${formName}.submit();
}
function batalHAPT() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function updatePAPTHAPT(idOB,idPermohonanSimati,idSimati,statusWaris,idPerintah,mode,idPerintahHAOBMST,flag_kemaskini_selesai) {

	if (statusWaris == 'HILANG'){
		var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPTHA?idOB="+idOB+"&idPermohonanSimati="+idPermohonanSimati+"&idSimati="+idSimati+"&statusWaris="+statusWaris+"&idPerintah="+idPerintah+"&mode="+mode+"&idPerintahHAOBMST="+idPerintahHAOBMST+"&flag_kemaskini_selesai="+flag_kemaskini_selesai;
	} else {
		var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPAHA?idOB="+idOB+"&idPermohonanSimati="+idPermohonanSimati+"&idSimati="+idSimati+"&statusWaris="+statusWaris+"&idPerintah="+idPerintah+"&mode="+mode+"&idPerintahHAOBMST="+idPerintahHAOBMST+"&flag_kemaskini_selesai="+flag_kemaskini_selesai;
	}
	
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
	<!-- START FUNCTION HA -->
	
<!-- END FUNCTION PERINTAH TERUS -->

<!-- START FUNCTION PERINTAH KUASA TADBIR -->

	<!-- START FUNCTION HTA -->
function paparHTAPKT(id) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTAPKT";
	document.${formName}.modePopup.value = "update";
	document.${formName}.idHTA.value = id;
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function simpanKemaskiniHTAPKT() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTAPKT";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButt.value = "simpanKemaskiniHTAPKT";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function batalHTAPKT() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
	<!-- END FUNCTION HTA -->
	
	<!-- START FUNCTION HA -->
function paparHAPKT(id) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHAPKT";
	document.${formName}.modePopup.value = "update";
	document.${formName}.idHA.value = id;
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function simpanKemaskiniHAPKT() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHAPKT";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButt.value = "simpanKemaskiniHAPKT";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function batalHAPKT() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
	<!-- END FUNCTION HA -->
	
<!-- END FUNCTION PERINTAH KUASA TADBIR -->

<!-- START FUNCTION PERINTAH LELONG -->

	<!-- START FUNCTION HTA -->
function paparHTAPL(id) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTAPL";
	document.${formName}.modePopup.value = "update";
	document.${formName}.idHTA.value = id;
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanKemaskiniHTAPL() {
	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorJenisLelong.length;  i++){
		if (${formName}.sorJenisLelong[i].checked)
		radioSelected = true;
	}
	if (!radioSelected){
		alert('Sila pilih Jenis Lelong.');
		return (false);
	}
	if(document.${formName}.txdTarikhJualan.value == ""){
		alert('Sila masukkan Tarikh Jualan.');
  		document.${formName}.txdTarikhJualan.focus(); 
		return; 
	}
	if(document.${formName}.txtHargaRizab.value == ""){
		alert('Sila harga Rizab.');
  		document.${formName}.txtHargaRizab.focus(); 
		return; 
	}
		if(document.${formName}.txtAmaun.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaun.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTAPL";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButt.value = "simpanKemaskiniHTAPL";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function batalHTAPL() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
	<!-- END FUNCTION HTA -->
	
	<!-- START FUNCTION HA -->
function paparHAPL(id) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHAPL";
	document.${formName}.modePopup.value = "update";
	document.${formName}.idHA.value = id;
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanKemaskiniHAPL() {
	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorJenisLelongHAPL.length;  i++){
		if (${formName}.sorJenisLelongHAPL[i].checked)
		radioSelected = true;
	}
	if (!radioSelected){
		alert('Sila pilih Jenis Lelong.');
		return (false);
	}
	if(document.${formName}.txdTarikhJualanHAPL.value == ""){
		alert('Sila masukkan Tarikh Jualan.');
  		document.${formName}.txdTarikhJualanHAPL.focus(); 
		return; 
	}
	if(document.${formName}.txtHargaRizabHAPL.value == ""){
		alert('Sila masukkan harga Rizab.');
  		document.${formName}.txtHargaRizabHAPL.focus(); 
		return; 
	}
	if(document.${formName}.txtAmaunHAPL.value == ""){
		alert('Sila masukkan Amaun.');
  		document.${formName}.txtAmaunHAPL.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHAPL";
	document.${formName}.modePopup.value = "update";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.hitButt.value = "simpanKemaskiniHAPL";
	document.${formName}.submit();
}
function batalHAPL() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
	<!-- END FUNCTION HA -->
	
<!-- END FUNCTION PERINTAH LELONG -->

<!-- START FUNCTION PERINTAH FARAID -->

	<!-- START FUNCTION HTA -->
function paparHTAPF(id) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.idHTA.value = id;
	document.${formName}.flagPopup.value = "openHTAPF";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function batalHTAPF() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function simpanKemaskiniHTAPF() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHTAPF";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButt.value = "simpanKemaskiniHTAPF";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function updatePAPTHTAPF(idOB,idPermohonanSimati,idSimati,statusWaris,idPerintah,mode,idPerintahHTAOBMST,flag_kemaskini_selesai) {

	if (statusWaris == 'HILANG'){
		var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPTHTA?idOB="+idOB+"&idPermohonanSimati="+idPermohonanSimati+"&idSimati="+idSimati+"&statusWaris="+statusWaris+"&idPerintah="+idPerintah+"&mode="+mode+"&idPerintahHTAOBMST="+idPerintahHTAOBMST+"&flag_kemaskini_selesai="+flag_kemaskini_selesai;
	} else {
		var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPAHTA?idOB="+idOB+"&idPermohonanSimati="+idPermohonanSimati+"&idSimati="+idSimati+"&statusWaris="+statusWaris+"&idPerintah="+idPerintah+"&mode="+mode+"&idPerintahHTAOBMST="+idPerintahHTAOBMST+"&flag_kemaskini_selesai="+flag_kemaskini_selesai;
	}
	
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
	<!-- END FUNCTION HTA -->
	
	<!-- START FUNCTION HA -->
function paparHAPF(id) {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.idHA.value = id;
	document.${formName}.flagPopup.value = "openHAPF";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function batalHAPF() {
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function simpanKemaskiniHAPF() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPerintah.value = "papar";
		return;
	}
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.flagPopup.value = "openHAPF";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButt.value = "simpanKemaskiniHAPF";
	document.${formName}.anchor.value = "tabLower";
	document.${formName}.submit();
}
function updatePAPTHAPF(idOB,idPermohonanSimati,idSimati,statusWaris,idPerintah,mode,idPerintahHAOBMST,flag_kemaskini_selesai) {

	if (statusWaris == 'HILANG'){
		var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPTHA?idOB="+idOB+"&idPermohonanSimati="+idPermohonanSimati+"&idSimati="+idSimati+"&statusWaris="+statusWaris+"&idPerintah="+idPerintah+"&mode="+mode+"&idPerintahHAOBMST="+idPerintahHAOBMST+"&flag_kemaskini_selesai="+flag_kemaskini_selesai;
	} else {
		var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPAHA?idOB="+idOB+"&idPermohonanSimati="+idPermohonanSimati+"&idSimati="+idSimati+"&statusWaris="+statusWaris+"&idPerintah="+idPerintah+"&mode="+mode+"&idPerintahHAOBMST="+idPerintahHAOBMST+"&flag_kemaskini_selesai="+flag_kemaskini_selesai;
	}
	
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
	<!-- END FUNCTION HA -->
	
<!-- END FUNCTION PERINTAH FARAID -->
</script> 
<!-- END SCRIPT FOR TAB BAWAH --> 
<!--  START SCRIPT FOR CHECKING BAHAGIAN WARIS --> 
<script>

function validateGSA()
{ //alert ("validateGSA");
	var check_GSA = true;
	var idJenisTanah = document.${formName}.idJenisTanah.value * 1;
	
	if(idJenisTanah==3)
	{
	var array_index_selected = null;
	var bahagianSimatiAtas = document.${formName}.bahagianSimatiAtas.value;
	var bahagianSimatiBawah = document.${formName}.bahagianSimatiBawah.value;
	
	
	var checklist_field_BA = document.getElementsByName("txtBA");
	var checklist_field_BB = document.getElementsByName("txtBB");
	var checklist_length = document.getElementsByName("txtBA").length;
	var bahagian = (bahagianSimatiAtas*1) / (bahagianSimatiBawah*1);
	//alert("bahagian " +bahagian);
	
	//var on_key_up_index = null;
	
	
	var count_total_ob = 0;
	if(checklist_length>0)
	{
		array_index_selected = [];
		for (k = 0; k < checklist_length; k++) {
			var element_field_index_BA = checklist_field_BA[k];
			//alert('z');
			if(checklist_field_BA[k].value >0 || checklist_field_BB[k].value >0 )
			{
				//alert('masuk');
				//on_key_up_index = k;				
				//array_index_selected.push(k);
				count_total_ob ++;
			}
			//alert('element_field_index : '+element_field_index_BA.value);
		}
		//alert('array_index_selected : '+array_index_selected);
	}
	
	//index simpan dalam array dlu
	
	//alert("count_total_ob = "+count_total_ob)
	if(bahagian == 1 && count_total_ob > 2)
	{
		alert("Bagi tanah GSA bahagian simati adalah 1/1, Sila pastikan pembahagian diberikan kepada 2 orang waris sahaja!");
		check_GSA = false;
	}
	else if(bahagian == 0.5 && count_total_ob != 1)
	{
		alert("Bagi tanah GSA bahagian simati adalah 1/2, Sila pastikan pembahagian diberikan kepada seorang waris sahaja!");	
		check_GSA = false;
	}
	
	}
	
	
	
	return check_GSA;
	
}


function calculateTotal() {
	var idJenisTanah = document.${formName}.idJenisTanah.value * 1;
	
	/*
	var checklist_checkbox = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB);
	var checklist_length = document.getElementsByName("CHECK_"+namaField+""+ID_BORANGPNB).length;
	//alert('checklist_length : '+checklist_length);
	for (k = 0; k < checklist_length; k++) {
			//checklist_checkbox[k].checked = true;
			//alert(checklist_checkbox[k].value);
			var element_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value);
			var value_fielddate = document.getElementById("TARIKH_"+namaField+""+checklist_checkbox[k].value).value;
			checkFormatDate_V3(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value);
			checkMandatory_Date(element_fielddate,value_fielddate,"span_CHECK_TARIKH_"+namaField+""+checklist_checkbox[k].value,checklist_checkbox[k]);
			
	}
	*/
	
	
	if(idJenisTanah==3)
	{
		
		var array_index_selected = null;
		var bahagianSimatiAtas = document.${formName}.bahagianSimatiAtas.value;
		var bahagianSimatiBawah = document.${formName}.bahagianSimatiBawah.value;
		
		
		var checklist_field_BA = document.getElementsByName("txtBA");
		var checklist_field_BB = document.getElementsByName("txtBB");
		var checklist_length = document.getElementsByName("txtBA").length;
		var bahagian = (bahagianSimatiAtas*1) / (bahagianSimatiBawah*1);
		//alert(bahagianSimatiAtas + 'a'+bahagian);
		
		//var on_key_up_index = null;
		
		
		if(checklist_length>0)
		{
			array_index_selected = [];
			for (k = 0; k < checklist_length; k++) {
				var element_field_index_BA = checklist_field_BA[k];
				//alert('z');
				if(checklist_field_BA[k].value >0 || checklist_field_BB[k].value >0 )
				{
					//alert('masuk');
					//on_key_up_index = k;				
					array_index_selected.push(k);
				}
				//alert('element_field_index : '+element_field_index_BA.value);
			}
			//alert('array_index_selected : '+array_index_selected);
		}
		
		//index simpan dalam array dlu
		
		
		//alert('leng array : '+array_index_selected.length+' bahagian : '+bahagian);
		for (x = 0; x < checklist_length; x++) {
			
			if(array_index_selected.length == 2 && bahagian == 1)
			{
				//alert('atas');				
				if(array_index_selected.indexOf(x) < 0)
				{
					//alert('x : '+x+' ada -'+array_index_selected.length);
					checklist_field_BA[x].readonly = 'readonly';
					checklist_field_BB[x].readonly = 'readonly';
					checklist_field_BA[x].className = 'disabled';
					checklist_field_BB[x].className = 'disabled';
				}
				
			}
			else if(array_index_selected.length == 1 && bahagian == 0.5)
			{
				//alert('bawah'+ ' x : '+x+' dalam array ? : '+array_index_selected.indexOf(x));				
				if(array_index_selected.indexOf(x) < 0)
				{
					//alert('x : '+x+' ada -'+array_index_selected.length);
					checklist_field_BA[x].readonly = 'readonly';
					checklist_field_BB[x].readonly = 'readonly';
					checklist_field_BA[x].className = 'disabled';
					checklist_field_BB[x].className = 'disabled';
				}				
			} 
			else
			{
				checklist_field_BA[x].readonly = '';
				checklist_field_BB[x].readonly = '';
				checklist_field_BA[x].className = '';
				checklist_field_BB[x].className = '';
			}
			
		}
		
		
		
		
	}
	
	var bahagianSimatiAtas = document.${formName}.bahagianSimatiAtas.value;
	var bahagianSimatiBawah = document.${formName}.bahagianSimatiBawah.value;
	
	url = "../servlet/ekptg.view.ppk.FrmPerintahSek8Check?command=calculateTotal"+"&bahagianSimatiAtas="+bahagianSimatiAtas+"&bahagianSimatiBawah="+bahagianSimatiBawah+"&idJenisTanah="+idJenisTanah;
	
	actionName = "calculateTotal";
	target = "calculateTotal_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function setTotalPecahan(BA,BB){
	document.${formName}.txtJumlahBA.value = BA;
	document.${formName}.txtJumlahBB.value = BB;
}
function pembahagianSepara() {	
	url = "../servlet/ekptg.view.ppk.FrmPerintahSek8Check?command=pembahagianSepara";
	actionName = "pembahagianSepara";
	target = "pembahagianSepara_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function kosongkanPembahagian() {
	url = "../servlet/ekptg.view.ppk.FrmPerintahSek8Check?command=kosongkanPembahagian";
	actionName = "kosongkanPembahagian";
	target = "kosongkanPembahagian_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function pembahagianFaraid() {	
	url = "../servlet/ekptg.view.ppk.FrmPerintahSek8Check?command=pembahagianFaraid";
	actionName = "pembahagianFaraid";
	target = "pembahagianFaraid_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function setPecahan(BA,BB,index,size){
	if (size > 1){
		
		document.${formName}.txtBA[index].value = BA;
		document.${formName}.txtBB[index].value = BB;
	} else {
		
		document.${formName}.txtBA.value = BA;
		document.${formName}.txtBB.value = BB;
	}
}
function setPecahanHilang(BA,BB){
	document.${formName}.txtBAHilang.value = BA;
	document.${formName}.txtBBHilang.value = BB;
}
function samakanPembawah() {
	url = "../servlet/ekptg.view.ppk.FrmPerintahSek8Check?command=samakanPembawah";
	actionName = "samakanPembawah";
	target = "samakanPembawah_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function checkPembahagianGSA(count) {
	
	var idJenisTanah = document.${formName}.idJenisTanah.value * 1;
	
	if (idJenisTanah == 3){
		
		url = "../servlet/ekptg.view.ppk.FrmPerintahSek8Check?command=checkPembahagianGSA&count="+count;
		actionName = "checkPembahagianGSA";
		target = "checkPembahagianGSA_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
	}	
}
function popupMsg(msg){
	alert(msg);
}
</script> 
<!--  END SCRIPT FOR CHECKING BAHAGIAN WARIS --> 
<!-- START SCRIPT FOR REPORT --> 
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakBorangE(idFail,idPermohonanSimati,idSimati,idPerintahHTAOBMST) {
	var url = "../servlet/ekptg.report.ppk.BorangE?idfail="+idFail+"&idPermohonanSimati="+idPermohonanSimati+"&idSimati="+idSimati+"&idPerintahHTAOBMST="+idPerintahHTAOBMST;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function PrintLaporan(nama_penghantarnotis,cara_serahan,Tarikh_serahan,Nama_Penerima,no_kp_penerima,catatan,noFail)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppk.PaparanMaklumatSerahan?cara_serahan="+cara_serahan+"&tarikh_serahan="+Tarikh_serahan+"&nama_penerima="+Nama_Penerima+"&penyerah="+nama_penghantarnotis+"&no_kp_penerima="+no_kp_penerima+"&catatan="+catatan+"&noFail="+noFail;
	var hWnd = window.open(url,'printuser','width=700,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function cetakBorangEE(idFail) {
	var url = "../servlet/ekptg.report.ppk.BorangEE?idfail="+idFail;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakBorangF(idFail) {
	var url = "../servlet/ekptg.report.ppk.BorangF?idfail="+idFail;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakBorangHH(idFail) {
	var url = "../servlet/ekptg.report.ppk.BorangHH?idfail="+idFail;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakNotisKeluarGeranPot() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButt.value = "selesai";
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.printOption.value = "potrait";
	document.${formName}.submit();
}
function cetakNotisKeluarGeranLand() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButt.value = "selesai";
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.printOption.value = "landskap";
	document.${formName}.submit();
}
function hantarNotisKeluarGeran() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPerintahSek8";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButt.value = "selesai";
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.printOption.value = "hantar";
	document.${formName}.submit();
}
function generateNotisKeluarGeranPot(idFail){
	var url = "../servlet/ekptg.report.ppk.BorangB1POT?idfail="+idFail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function generateNotisKeluarGeranLand(idFail){
	var url = "../servlet/ekptg.report.ppk.BorangB1LAND?idfail="+idFail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function generateNotisKeluarGeran(idFail){
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiPerintah?idfail="+idFail+"&command=borangPerintah";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringanNotisPengeluaranGeran(noFail,idPerbicaraan) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idperbicaraan="+idPerbicaraan+"&report=SuratIringanNotisPengeluaranGeran&flagReport=S";
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringanPerintahKuasaTadbir(noFail,idPerbicaraan) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idperbicaraan="+idPerbicaraan+"&report=SuratIringanPerintahKuasaTadbir&flagReport=S";
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringanPerintahKuasaTadbirII(noFail,idPerbicaraan) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idperbicaraan="+idPerbicaraan+"&report=SuratIringanPerintahKuasaTadbirII&flagReport=S";
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringanPerintahTerengganu(noFail,idPerbicaraan) {
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&idperbicaraan="+idPerbicaraan+"&report=SuratIringanPerintahTerengganu&flagReport=S";
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
//aishah
function cetakSuratKemaskiniPerintahI(noFail,idfail) {
	
    
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupSuratEditPerintahReportView?noFail="+noFail+"&idfail="+idfail+"&report=SuratKemaskiniPerintah&flagReport=1"; 
	
	var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
//aishah
function cetakSuratKemaskiniPerintah2(noFail,idfail) {
	
    
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupSuratEditPerintahReportView?noFail="+noFail+"&idfail="+idfail+"&report=SuratKemaskiniPerintah2&flagReport=2"; 
	
	var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

//aishah
function cetakRekodPindaan(userId) {
	
    
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupRekodPindaanReportView?userId="+userId; 
	
	var hWnd = window.open(url,'printuser','width=700,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}



</script> 
<!-- END SCRIPT FOR REPORT --> 

<script>
function pembahagianHartaLainHTA(idPerintah, idPerintahHTAOBMST) {

	var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPembahagianSerentak?idPerintah="+idPerintah+"&idPerintahHTAOBMST="+idPerintahHTAOBMST;
	
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function pembahagianHartaLainHA(idPerintah, idPerintahHAOBMST) {

	var url = "../x/${securityToken}/ekptg.view.ppk.PopupPerintahPembahagianSerentak?idPerintah="+idPerintah+"&idPerintahHAOBMST="+idPerintahHAOBMST;
	
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script> 


<script>
function janaPerintah() {
	document.${formName}.hitButt.value = "janaPerintah";
	document.${formName}.actionPerintah.value = "papar";
	document.${formName}.submit();
}

function popupIntergrasiPerintahEtanah(idFail, idPerintah) {
	var url = "../x/${securityToken}/ekptg.view.ppk.PopupIntergrasiPerintahEtanahView?idPerintah="+idPerintah+"&idFail="+idFail;
	
    var hWnd = window.open(url,'printuser','width=1000,height=350, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

<script>
//arief add Tandatangan Digital OPEN
function sendDGcertPerintah(NO_FAIL,id_perbicaraan,idfail,id_permohonan,idpermohonansimati){
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiDGCertPerintah?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&id_permohonan="+id_permohonan+"&idpermohonansimati="+idpermohonansimati+"&commandw=sendDGCertPerintah";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function verifyDGcertPerintah(NO_FAIL,id_perbicaraan,idfail,id_permohonan,idpermohonansimati){
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiDGCertPerintah?nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&id_permohonan="+id_permohonan+"&idpermohonansimati="+idpermohonansimati+"&flagVersion=popupPNB&commandw=verify";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
//arief add Tandatangan Digital CLOSE

/**List fail-fail Tandatangan Digital di Perintah:
	1.	FrmPerintahSek8.java
	2.	FrmPerintahMaklumatPerintahSek8.jsp
	3.	tindakanPegawaiPerintahSek8.jsp
	4.	FrmIntegrasiDGCertPerintah.java
	5.	DGCertPerintah.jsp
	6.	FrmPerintahSek8Data.java
	7.	TandatanganSuccessPerintah.jsp
*/
</script>
