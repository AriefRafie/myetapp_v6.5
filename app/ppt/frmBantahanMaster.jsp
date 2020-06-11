<!-- <div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">Tiada Hakmilik Didaftarkan.</div>";
</script>
#end -->


#foreach ( $senarai in $Header )
	#set ($nama_kementerian=$senarai.nama_kementerian)
    #set ($id_kementerian=$senarai.id_kementerian)
    #set ($id_agensi=$senarai.id_agensi)
    #set ($no_fail=$senarai.no_fail)
    #set ($id_fail=$senarai.id_fail)
    #set ($no_permohonan=$senarai.no_permohonan)
    #set ($tarikh_terima=$senarai.tarikh_terima)
    #set ($projek_negeri=$senarai.projek_negeri)
    #set ($nama_daerah=$senarai.nama_daerah)
    #set ($tujuan=$senarai.tujuan)
    #set ($tarikh_kehendaki=$senarai.tarikh_kehendaki)
    #set ($no_rujukan_surat=$senarai.no_rujukan_surat)
    #set ($no_rujukan_ptd=$senarai.no_rujukan_ptd)
    #set ($no_rujukan_ptg=$senarai.no_rujukan_ptg)
    #set ($no_rujukan_upt=$senarai.no_rujukan_upt)
    #set ($keterangan=$senarai.keterangan) 
    #set ($nama_agensi=$senarai.nama_agensi)
    #set ($tarikh_permohonan=$senarai.tarikh_permohonan)   
#end 

#set ( $id_status = $statusFail.get("id_status") )

#if ($flag=="semak")
    #foreach ( $senarai in $getMaklumatBantahan )
        #set ($id_bantahan=$senarai.id_bantahan)
        #set ($txtNoBantahan=$senarai.no_bantahan)
        #set ($jenis_pembantah=$senarai.jenis_pembantah)
        #set ($txdTkhTerimaBrgN=$senarai.tarikh_terima)
        #set ($txdBrgN=$senarai.tarikh_borangn)
        #set ($id_pihakberkepentingan=$senarai.id_pihakberkepentingan)
        #set ($status_bantahan=$senarai.status_bantahan)
        #set ($id_hakmilik=$senarai.id_hakmilik)
        #set ($txtAlasanBantahan=$senarai.alasan)
        #set ($txtKptgnAtasTnh=$senarai.kepentingankeatas)
        #set ($id_jenispb=$senarai.id_jenispb)
        #set ($keteranganjenispb=$senarai.keterangan)
        #set ($nama_pb=$senarai.nama_pb)
        #set ($txtAlamat1=$senarai.alamat1)
        #set ($txtAlamat2=$senarai.alamat2)
        #set ($txtAlamat3=$senarai.alamat3)
        #set ($txtPoskod=$senarai.poskod)
        #set ($id_negeri=$senarai.id_negeri)
        #set ($id_bandar=$senarai.id_bandar)
        #set ($no_hakmilik=$senarai.no_hakmilik)
        #set ($txtNoPt=$senarai.no_pt)
        #set ($no_lot=$senarai.no_lot)
        #set ($flag_syarat=$senarai.flag_syarat)
        #set ($ukuran_luas=$senarai.flag_penerima_pampasan)
        #set ($amaun_pampasan=$senarai.flag_bahagian_pampasan)
        #set ($terima_pampasan=$senarai.flag_ukur_luas)
        #set ($umpuk_pampasan=$senarai.flag_pampasan)
        #set ($txdTkhAward=$senarai.tarikh_terima_award)
        #set ($txtPengambilanNo=$senarai.no_siasatan)
        #set ($txtAmaunTuntutan=$senarai.amaun_tuntutan)  
        #set ($desc_status_bantahan=$senarai.desc_status_bantahan)  
        #set ($txtMaklumatBantahanTamat=$senarai.maklumat_bantahan_tamat_tempoh)  
    #end
#end

#foreach ( $data in $getTarikhPenting )
    #set ($txdTkhBrgH=$data.tarikh_borangh)    
#end

#set ( $flag_hadir = $getMaklumatChecking.get("flag_hadir") )

#foreach ( $info in $getHakmilik )
    #set ($id_hakmilik=$info.id_hakmilik)
    #set ($no_lot=$info.no_lot) 
    #set ($txtUnitLot=$info.unitlot) 
    #set ($txtNoPt=$info.no_pt)
    #set ($txtUnitPt=$info.unitpt)
    #set ($txtPengambilanNo=$info.no_siasatan)
    #set ($txtNoLot=$info.no_lotpt)
    #if($txtNoPt=="")
        #set ($txtNoPt="")   
        #set ($txtUnitPt="")
    #end        
#end

#if ($mode=="disabled")
	#set ($selectstyle = "disabled class=disabled" )
#elseif ($mode!="disabled"  &&  $alasan2 == "2")
	#set ($selectstyle = "disabled class=disabled" )
#else
	#set ($selectstyle = "" )
#end

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>

<!------------------------------------------- HEADER ---------------------------------------------->

    <!-- Header -->
	#parse("app/ppt/frmPPTHeader.jsp")
	
     </br>
<!------------------------------------------- END HEADER --------------------------------------------->  

<!------------------------------------------ TAB BANTAHAN -------------------------------------------->

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelectedTab(0);bantahan()">Bantahan
    
    </li>
    <li class="TabbedPanelsTab" tabindex="1" onclick="setSelectedTab(1);deposit()">Deposit</li>
    #if($!idBorangO_check != "")
    <li class="TabbedPanelsTab" tabindex="2" onclick="setSelectedTab(2);borangO()">Borang O</li>
    <li class="TabbedPanelsTab" tabindex="3" onclick="setSelectedTab(3);lanjutanTempoh()">Lanjutan Tempoh</li>
    <li class="TabbedPanelsTab" tabindex="4" onclick="setSelectedTab(4);susulanBantahan()">Perintah</li>
    <li class="TabbedPanelsTab" tabindex="5" onclick="setSelectedTab(5);pemulanganDeposit()">Pemulangan Deposit</li>
    <li class="TabbedPanelsTab" tabindex="6" onclick="setSelectedTab(6);tarikBalikBantahan()">Tarik Balik Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="7" onclick="setSelectedTab(7);batalBantahan()">Pembatalan Bantahan oleh MT</li>
    #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
<fieldset>
<legend>Maklumat Bantahan</legend>
    <table width="100%" border="0"> 
        <tr>
          <td width="1%"></td>
          <td width="24%">Bil. Bantahan</td>
          <td width="1%">:</td>
          <td width="24%"><input type="text" name="txtNoBantahan" id="txtNoBantahan" value="$!txtNoBantahan" size="35" class="disabled" readonly /></td>
          <td width="1%">&nbsp;</td>
          <td width="23%">Status Bantahan</td>
          <td width="1%">:</td>
          <td width="26%">
          #if ($mode=="disabled")
          <select name="flag_syarat" id="flag_syarat" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled >                
			#if($flag_syarat=="1")                                                   
            <option value="1">Syarat Dipenuhi</option>
            <option value="2">Syarat Tidak Dipenuhi</option>                      
            #elseif($flag_syarat=="2")                                                 
            <option value="2">Syarat Tidak Dipenuhi</option>
            <option value="1">Syarat Dipenuhi</option>           
	        #else                                                     
            <option value="1">Syarat Dipenuhi</option>
            <option value="2">Syarat Tidak Dipenuhi</option>            
            #end         
          </select>
          
          #else
          
          <select name="flag_syarat" id="flag_syarat" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled >                   
			#if($flag_syarat=="1")                                                   
            <option value="1">Syarat Dipenuhi</option>
            <option value="2">Syarat Tidak Dipenuhi</option>                      
            #elseif($flag_syarat=="2")                                                 
            <option value="2">Syarat Tidak Dipenuhi</option>
            <option value="1">Syarat Dipenuhi</option>           
	        #else                                                     
            <option value="1">Syarat Dipenuhi</option>
            <option value="2">Syarat Tidak Dipenuhi</option>            
            #end         
          </select>
          #end          </td>
      	</tr>
        <tr>
          <td width="1%"></td>
          <td>Pengambilan No.</td>
          <td>:</td>
          <td><input type="text" name="txtPengambilanNo" id="txtPengambilanNo" value="$!txtPengambilanNo" tabindex="2" class="disabled" readonly /></td>
          <td>&nbsp;</td>
          <td>Pihak membantah</td>
          <td>:</td>
          <td>
          #if($mode=="disabled")
            <select name="jenis_pembantah" id="jenis_pembantah" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled >                         
			#if($jenis_pembantah=="1")                                        
              <option value="1">Pihak Berkepentingan</option>
              <option value="2">Agensi Pemohon</option> 
            #elseif($jenis_pembantah=="2")                                          
              <option value="2">Agensi Pemohon</option>
              <option value="1">Pihak Berkepentingan</option> 
	        #else                                         
              <option value="1">Pihak Berkepentingan</option>
              <option value="2">Agensi Pemohon</option>
            #end                   
            </select> 
                       
         #else
            <select name="jenis_pembantah" tabindex="6" id="jenis_pembantah" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled >
                    
			#if($jenis_pembantah=="1")                                        
            <option value="1">Pihak Berkepentingan</option>
            <!--<option value="2">02-Agensi Pemohon</option> -->         
            #elseif($jenis_pembantah=="2")                                     
           <!-- <option value="2">02-Agensi Pemohon</option>-->
            <option value="1">Pihak Berkepentingan</option>           
	        #else                                         
            <option value="1">Pihak Berkepentingan</option>
           <!-- <option value="2">02-Agensi Pemohon</option>-->
            #end                                
          </select>
          #end          </td>
      	</tr>  
        <tr>
          <td width="1%">#if($mode=="")<font color="red">*</font>#end</td>
          <td>
        
          Tarikh Terima 
                </td>
          <td>:</td>
          <td>
          #if($mode=="disabled")
          <input type="text" name="txdTkhTerimaBrgN" id="txdTkhTerimaBrgN" value="$!txdTkhTerimaBrgN" size="10" class="disabled" readonly />
          #else
		  <input type="text" name="txdTkhTerimaBrgN" id="txdTkhTerimaBrgN" value="$!txdTkhTerimaBrgN" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" tabindex="3" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerimaBrgN',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>          
          #end          </td>
          <td>#if($mode=="")<font color="red">*</font>#end</td>
          <td>
         
          Tarikh Terima
                   </td>
          <td>:</td>
          <td>          
          #if ($mode=="disabled")
          <input type="text" name="txdTkhBrgH" id="txdTkhBrgH" value="$!txdTkhBrgH" size="10" class="disabled" readonly />
 		  #else
          <input type="text" name="txdTkhBrgH" id="txdTkhBrgH" value="$!txdTkhBrgH" size="10" class="disabled" readonly />
          <!-- <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhBrgH',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>  -->       
          #end         </td>
      	</tr> 
        <tr>
          <td width="1%"></td>
          <td valign="top">Borang N</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td valign="top">Borang H</td>
          <td>&nbsp;</td>
          <td></td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td>Tarikh Borang N</td>
          <td>:</td>
          <td>
          
          #if($mode=="disabled")
          <input type="text" name="txdBrgN" id="txdBrgN" value="$!txdBrgN" size="10" class="disabled" readonly />
          #else
          <input type="text" name="txdBrgN" id="txdBrgN" value="$!txdBrgN" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" tabindex="4" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdBrgN',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
          #end          </td>
          <td>#if($mode=="")<font color="red">*</font>#end</td>
          
          <td>
          
           Tarikh <em>Award</em>
                   </td>
          <td>:</td>
          <td>
          
          #if ($mode=="disabled") 
         <input type="text" name="txdTkhAward" id="txdTkhAward" value="$!txdTkhAward" size="10"  class="disabled" readonly />
 		 #else
          <input type="text" name="txdTkhAward" id="txdTkhAward" value="$!txdTkhAward" size="10" onblur="check_date(this);checkBantahanCondition();" tabindex="10" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhAward',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>        
          #end          </td>
      	</tr> 
        <tr>
          <td width="1%"></td>
          <td>No. Hakmilik</td>
          <td>:</td>
          <td>$selectHakmilik<!--<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />--> </td>
          <td>&nbsp;</td>
          <td>Status Hadir Bicara</td>
          <td>:</td>
          <td>
          
          #if ($flag_hadir=="1")           
          <input type="text" name="txtHadirBicara" id="txtHadirBicara" value="HADIR" size="10" class="disabled" readonly />
          #else
          <input type="text" name="txtHadirBicara" id="txtHadirBicara" value="TIDAK HADIR" size="13" class="disabled" readonly />
          #end          </td>
      	</tr>
        
        <tr>
          <td width="1%"></td>
          <td>No Lot</td>
          <td>:</td>
         <!-- <td>$selectNoLot</td>-->
          <td><input type="text" name="txtNoLot" id="txtNoLot" value="$!txtNoLot" class="disabled" readonly="readonly" /></td>
          <td>&nbsp;</td>
          <td colspan="3">
           <i><font color="#FF0000" style="font-size:10px">
          <b>Syarat :</b><br/> 
          <span id="alert_bantahan1">1. Jika hadir/diwakili semasa award pampasan ialah 6 minggu dari tarikh award pampasan bertulis (Borang G)</span>
          <br/>
          <span id="alert_bantahan2">2. Jika tidak hadir 6 minggu dari tarikh tawaran pampasan (Borang H) diterima atau 6 bulan dari tarikh award dibuat (Borang G), mengikut mana yang dahulu tamat</span>
          </font></i>  
          </td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td>No PT</td>
          <td>:</td>
          <td><input type="text" name="txtNoPt" id="txtNoPt" value="$!txtNoPt" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly="readonly" tabindex="8" /></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>                            
    </table>   
</fieldset>

<br />
<!------------------------------------ END MAKLUMAT BANTAHAN ------------------------------------------> 

<!------------------------------------ MAKLUMAT PEMBANTAH --------------------------------------------->  

<fieldset>
	<legend>Maklumat Pembantah</legend>
    <table width="100%" border="0"> 
        <tr>
          <td width="1%"></td>
          <td width="22%">&nbsp;Nama Pembantah</td>
          <td width="1%">:</td>
          <td colspan="2">$selectNamaPembantah</td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td>&nbsp;Alamat</td>
          <td>:</td>
          <td colspan="2"><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" class="disabled" readonly tabindex="12" /></td>
        </tr>  
        <tr>
            <td width="1%"></td>
            <td>&nbsp;</td>
            <td></td>
            <td colspan="2"><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" class="disabled" readonly tabindex="13" /></td>
        </tr> 
        <tr>
            <td width="1%"></td>
            <td>&nbsp;</td>
            <td></td>
            <td colspan="2"><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" class="disabled" readonly tabindex="14" /></td>
        </tr> 
        <tr>
          <td width="1%"></td>
          <td>&nbsp;Poskod</td>
          <td>:</td>
          <td colspan="2"><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" class="disabled" readonly tabindex="15" /></td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td>&nbsp;Negeri</td>
          <td>:</td>
          <td colspan="2">$selectNegeri</td>
        </tr>
        <tr>
            <td width="1%"></td>
            <td>&nbsp;Bandar</td>
            <td>:</td>
            <td colspan="2">$selectBandar</td>
        </tr> 
        <tr>
          <td width="1%"></td>
          <td>&nbsp;Jenis Pihak Pembantah</td>
          <td>:</td>
          <td colspan="2">$selectPihakBantah</td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode=="")<font color="red">*</font>#end</td>
          <td valign="top">
          
          &nbsp;Kepentingan
         
          </td>
          <td valign="top">:</td>
          <td colspan="2">
          #if ($mode=="disabled")
          <textarea name="txtKptgnAtasTnh" id="txtKptgnAtasTnh" cols="50" rows="6" class="disabled" readonly>$!txtKptgnAtasTnh</textarea>
          #else
          <textarea name="txtKptgnAtasTnh" id="txtKptgnAtasTnh" cols="50" rows="6" tabindex="16">$!txtKptgnAtasTnh</textarea>
          #end          
          </td>
        </tr>
        
        
       #if ($syarat=="bawah") 
        <tr>
          <td width="1%"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="5">                  	
          	<i><font color="#FF0000" style="font-size:10px"> PAMPASAN PIHAK BERKEPENTINGAN INI KURANG ATAU SAMA DENGAN RM3000.00 </font></i>          
          </td>
        </tr>
       #end
       
       
        <tr>
          <td width="1%" valign="top">#if ($mode=="" )<font color="red">*</font> #end</td>
          <td>
          
         
          &nbsp;Bantahan Terhadap
                         
          </td>
          
          <td>:</td>
          <td width="3%">
          
           #if($ukuran_luas=="Y")
           #set($TEMPchecked1 = "checked")
           #else
           #set($TEMPchecked1 = "")
           #end
       
           
          
          #if ($mode=="disabled" )
          <input type="checkbox" name="ukuran_luas" id="ukuran_luas" value="Y" disabled $TEMPchecked1  />
          #elseif ($mode!="disabled"  &&  $alasan1 == "1")
          <input type="checkbox" name="ukuran_luas" id="ukuran_luas" value="Y" disabled $TEMPchecked1  />
          #else
          <input type="checkbox" name="ukuran_luas" id="ukuran_luas" value="Y" tabindex="17" $TEMPchecked1  />
          #end          
          
          </td>
          
          <td width="76%">Ukuran Luas Tanah Tersebut;</td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>
          
           #if($amaun_pampasan=="Y")
           #set($TEMPchecked2 = "checked")
           #else
           #set($TEMPchecked2 = "")
           #end
           
         
          
          #if ($mode=="disabled")
          <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" disabled $TEMPchecked2  />
          #elseif ($mode!="disabled"  &&  $alasan2 == "2")
          <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" disabled $TEMPchecked2  />
          #else
          <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 />
          #end          
          
          </td>
          
          <td>Jumlah Pampasan;</td>
        </tr>
        
        <!-- PPT-35 (i) -->
        <tr>
          <td width="1%"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        	<td>
	        	<table>
	        		<tr>
	        		 <!-- PPT-35 i -->
					#set ( $checked = "" )
				    #foreach ($semak in $senaraiSemakan)
					    	<td width="10">
		                   
					          #if ( $semakclass.isSemakan("$permohonanInfo.idpermohonan", "$semak.id" ))
					        	#set ( $checked = "checked" )
					        #else
					        	#set ( $checked = "" )
					    	#end
					    	#set ( $checked = "" )
					        	 <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $selectstyle>
					       	</td>
					        <td>
					        	$semak.keterangan <!-- $semak.id -->
					        </td>
					      
				    #end	
				    </tr>
				</table>
        	
        	
        	
	            <!--  table>
					<tr>
		            	<td>
			            #if ($alasan2=="2") 
			            <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 disabled />
			            #else
			            <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 />
			            #end            
			            </td>
			            <td>Nilai Tanah</td>
			            <td>
			            #if ($alasan2=="2") 
			            <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 disabled />
			            #else
			            <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 />
			            #end            
			            </td>
			            <td>Bangunan</td>
			            <td>
			            #if ($alasan2=="2") 
			            <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 disabled />
			            #else
			            <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 />
			            #end            
			            </td>
			            <td>Kecederaan</td>
	            	</tr>
	            </table-->
        	</td>
        </tr>
        
        <tr>
          <td width="1%"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>
          
          
           #if($terima_pampasan=="Y")
           #set($TEMPchecked3 = "checked")
           #else
           #set($TEMPchecked3 = "")
           #end
          
        
          #if ($mode=="disabled")
          <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" disabled $TEMPchecked3 />
          #elseif ($mode!="disabled"  &&  $alasan3 == "3")
          <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" disabled $TEMPchecked3 />
          #else
          <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" tabindex="19"  $TEMPchecked3 />
          #end          
          
          </td>
          
          <td>Orang-orang yang  menerima pampasan;</td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>
          
           #if($umpuk_pampasan=="Y")
           #set($TEMPchecked4 = "checked")
           #else
           #set($TEMPchecked4 = "")
           #end
          
        
          #if ($mode=="disabled")
          <input type="checkbox" name="umpuk_pampasan" id="umpuk_pampasan" value="Y" tabindex="20" disabled $TEMPchecked4 />
          #elseif ($mode!="disabled"  &&  $alasan4 == "4")
          <input type="checkbox" name="umpuk_pampasan" id="umpuk_pampasan" value="Y" tabindex="20" disabled $TEMPchecked4 />
          #else
          <input type="checkbox" name="umpuk_pampasan" id="umpuk_pampasan" value="Y" tabindex="20" $TEMPchecked4 />
          #end 
                   
          </td>
          
          <td>Bahagian Pemberian Pampasan</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode=="" )<font color="red">*</font> #end</td>
          <td>
          
         
          &nbsp;Amaun Tuntutan (RM)
                            
          </td>
          
          <td>:</td>
          <td colspan="2">
          
          #if ($mode=="disabled")
          <input type="text" size="15" name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!Util.formatDecimal($!txtAmaunTuntutan)" maxlength="12" class="disabled" readonly />
          #else
          <input type="text" size="15" name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!txtAmaunTuntutan" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAmaunTuntutan')" onkeyup="validateNumber(this,this.value);" />          
          #end 
                   
          </td>
        </tr>        
        <tr>
          <td width="1%" valign="top">#if ($mode=="" )<font color="red">*</font> #end</td>
          <td valign="top">
          
          &nbsp;Alasan Bantahan
         
          </td>

          <td valign="top">:</td>
          <td colspan="2">       
          #if ($mode=="disabled")
          <textarea name="txtAlasanBantahan" id="txtAlasanBantahan" cols="50" rows="6" class="disabled" readonly>$!txtAlasanBantahan</textarea>
          #else
          <textarea name="txtAlasanBantahan" id="txtAlasanBantahan" cols="50" rows="6" tabindex="21" onKeyDown="textCounter(this.form.txtAlasanBantahan,this.form.remLentxtAlasanBantahan,400);" onKeyUp="textCounter(this.form.txtAlasanBantahan,this.form.remLentxtAlasanBantahan,400);" >$!txtAlasanBantahan</textarea>
          #end          
          </td>
        </tr>  
        
        <!-- <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="5"><input type="text" readonly class="disabled" name="remLentxtAlasanBantahan" size="3" maxlength="3" value="400"> Baki Aksara </td>
        </tr>  -->
        
        <tr>
          <td width="1%"></td>
          <td valign="top">&nbsp;Maklumat Bantahan Tamat Tempoh</td>

          <td valign="top">:</td>
          <td colspan="2">       
          #if ($mode=="disabled")
          <textarea name="txtMaklumatBantahanTamat" id="txtMaklumatBantahanTamat" cols="50" rows="6" class="disabled" readonly>$!txtMaklumatBantahanTamat</textarea>
          #else
          <textarea name="txtMaklumatBantahanTamat" id="txtMaklumatBantahanTamat" cols="50" rows="6" tabindex="21" >$!txtMaklumatBantahanTamat</textarea>
          #end          
          </td>
        </tr>         
                                                
    </table> 
    
</fieldset>

<br />
<!------------------------------------------------------ END MAKLUMAT PEMBANTAH ---------------------------------------->  


<!-- PPT-35 (ii) && Integrasi Mahkamah -->
<!----------------------------------------- SENARAI DOKUMEN YANG DISERTAKAN --------------------------------------------->

<!-- :::upload -->

<input type="hidden" name="nama_skrin" id="nama_skrin" value="bantahan"  />
<fieldset id="senarai_dokumen" >
<legend>Dokumen Integrasi Mahkamah</legend>
    
    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" title="Sila klik untuk tambah dokumen" >    
    #if($listDokumen_size > 0)
     <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumenMaster('$!readmode')" title="Sila tick untuk hapus dokumen" >
    #end
    <table width="100%">
  <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="30%">Nama Dokumen</td>
    <td width="30%">Keterangan</td>
    <td width="30%">Dokumen Sokongan (Papar)</td>
     #if($listDokumen_size > 0)
      <td width="5%">
     
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onclick="doCheckAll1()" title="Semak untuk pilih semua" />
      </div>
      
      </td>
      #end
  </tr>
 
  
 #if($listDokumen_size > 0)
  #set ($cnt=0)
  #foreach($list1 in $listDokumen)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
	  #if($list1.JENIS_DOKUMEN == "bantahan")   <!-- PPT-35 (ii) -->  
	  #set ($cnt=1)       		
	  <tr>  
	    <td class="$row" >$list1.BIL</td>
	    <td class="$row" ><a href="javascript:view_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.TAJUK</font></a></td>
	    <td class="$row" >$list1.KETERANGAN</td>
	    <td class="$row"><a href="javascript:papar_Lampiran('$list1.ID_DOKUMEN')"><font color="blue">$list1.NAMA_FAIL</font></a></td>   
	    <td class="$row" ><div align="center">
	       <input type="checkbox" name="ids1" id="ids1" onclick="doUpdateCheckAll1()" value="$list1.ID_DOKUMEN" >
	     </div></td>
	  </tr>
	  #end
  	#end
	  #if($cnt==0)
	  <tr>  
	    <td colspan="5">Tiada Rekod</td>    
	  </tr>
	  #end
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
</table>
 
</fieldset> 

<!-- PPT-35 (ii) -->
<br />
        <div align="center"> 
   	#if ($button=="view")  
          
    	#if($flag_online=="2" && $id_status_bantahan=="181")
        	<input type="button" name="cmdTolakPermohonan" id="cmdTolakPermohonan" value="Tolak Permohonan" onclick="javascript:tolakPermohonan()" />     
      	#end
          
          	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniBantahan()" /> 
         	<!-- <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" /> -->
       	<!-- INTEGRASI MAHKAMAH TINGGI-->
          	<input type="button" name="cmdintegrasimt" id="cmdintegrasimt" value="Integrasi MT" onclick="javascript:hantarBantahan()" /> 
     	<!-- END INTERGRASI MAHKAMAH -->          
  	#end
          
  	#if ($button=="edit")
      		<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanBantahan()" /> 
          	<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal_bantahan()" /> 
   	#end          
          	<input type="button" name="cmdkembali" id="cmdkembali" value="Kembali" onclick="javascript:kembaliList()" /> 
        </div>   

    </div>
   
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<input type=hidden name=selectedtab />
<!--------------------------------------- END TAB BANTAHAN ------------------------------------------->
</fieldset>

<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
<!--       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakBorangN('$id_fail','$id_bantahan')"><font color="blue"> Borang N - Permohonan Supaya Bantahan Dirujukkan Kepada Mahkamah </font></a></td>
      </tr>-->      
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakSuratMintaDepositDalam30Hari('$id_fail','$id_bantahan','$id_hakmilikpb')"><font color="blue"> Surat Makluman Kemukakan Bukti Bayaran - Makluman kepada PB supaya kemukakan bukti bayaran deposit </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$id_pihakberkepentingan" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="status_bantahan" id="status_bantahan" value="$status_bantahan" />
<input type="hidden" name="readmode" id="readmode"  value="$!readmode"/>
<input type="hidden" name="id_siasatan" id="id_siasatan" value="$!id_siasatan" />

<script type="text/javascript">
/**
* Fungsi hantar maklumat bantahan ke MT
*/
function hantarBantahan() {	
	var idBantahan = "&idbantahan="+$jquery('#id_bantahan').val();
	var idFail = "&idfail=$!id_fail";
	var idHarta  = "&idharta="+$jquery('#id_hakmilikpb').val();
	var idPermohonan  = "&idpermohonan="+$jquery('#id_permohonan').val();
	var idSiasatan  = "&idsiasatan="+$jquery('#id_siasatan').val();
	var idWarta  = "&idwarta=$!idWarta";
	var param = idHarta+idPermohonan+idSiasatan+idWarta+idFail+idBantahan;
	//alert(param);
	var url = "../x/${securityToken}/ekptg.view.ppt.bantahan.IntegrasiMT?"+param+"&command=bantahanpb&frmFrom=frmPrmhnnSek8DaftarSek8";
	var hWnd = window.open(url,'Cetak','width=625,height=575, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	

} 

$jquery("#alert_bantahan1").html($jquery("#alert_bantahan1").text()).removeClass("blink");
$jquery("#alert_bantahan2").html($jquery("#alert_bantahan2").text()).removeClass("blink");

checkBantahanCondition();
function checkBantahanCondition(){
	var status_hadir = document.${formName}.txtHadirBicara.value;
	var tarikh_borangH = document.${formName}.txdTkhBrgH.value;
	var tarikh_award = document.${formName}.txdTkhAward.value;
	//var status_hadir = "HADIR";
	//var tarikh_borangH = "17/03/2014";
	//var tarikh_award = "20/10/2014";
	if(status_hadir == "HADIR")
	{	
		if(CalculateWeek(tarikh_award)>=42)
		{
			var gettext = $jquery("#alert_bantahan1").text();
			$jquery("#alert_bantahan1").html("<b><font color='blue'>"+gettext+"</font></b>"+"<input type='hidden' name='chech_syarat' id='chech_syarat' value='lulus' >"+" <img src='../img/validyes.png' />").addClass("blink");	
		}
		else
		{
			var gettext = $jquery("#alert_bantahan1").text();
			$jquery("#alert_bantahan1").html("<b><font color='red'>"+gettext+"</font></b>"+"<input type='hidden' name='chech_syarat' id='chech_syarat' value='tidak_lulus' >").addClass("blink");	
		}
	}
	else if(status_hadir == "TIDAK HADIR")
	{
		if(CalculateWeek(tarikh_borangH)>=42 || CalculateWeek(tarikh_award)>=180 )
		{
			var gettext = $jquery("#alert_bantahan2").text();
			$jquery("#alert_bantahan2").html("<b><font color='blue'>"+gettext+"</font></b>"+"<input type='hidden' name='chech_syarat' id='chech_syarat' value='lulus' >"+" <img src='../img/validyes.png'  />").addClass("blink");	
		}
		else
		{
			var gettext = $jquery("#alert_bantahan2").text();
			$jquery("#alert_bantahan2").html("<b><font color='red'>"+gettext+"</font></b>"+"<input type='hidden' name='chech_syarat' id='chech_syarat' value='tidak_lulus' >").addClass("blink");	
		}
		//alert("JUMLAH MINGGU TIDAK HADIR :"+CalculateWeek(tarikh_award));
	}	

}	

function CalculateWeek(selected_tarikh){
	//alert("selected_tarikh :"+selected_tarikh);	
	
            // Current Date
            var date1 = new Date();
            //alert(date1); 
			//Parsing the value from the text control. The actual date is in dd/mm/yyyy format as per your post.
            var arrDateValue = selected_tarikh.split('/'); // Actual Date is in dd/mm/yyyy
			
			//alert("arrDateValue :"+arrDateValue); 
			// Converting to mm/dd/yyyy date format
            var Date2 = new Date(arrDateValue[1] + '/' + arrDateValue[0] + '/' + arrDateValue[2]); // mm/dd/yyyy
           // alert(Date2);            

            // one week calculation
            //var perWeek = 24 * 60 * 60 * 1000 * 7;  			
			var perWeek = 1000*60*60*24; // calculate days          

            // calculating total week. FYI the week starts from Monday to Sunday
           //var totalWeeks = Math.round((date1.valueOf()- Date2.valueOf())/ perWeek) + 1;
		    var totalWeeks = Math.round((date1.valueOf()- Date2.valueOf())/ perWeek)+1;            

            // alerting the total Weeks
           // alert(totalWeeks);
			return totalWeeks;
}

function tolakPermohonan(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "tolakPermohonan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function open_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cetakBorangN(id_fail,id_bantahan) {
	var url = "../servlet/ekptg.report.ppt.BorangN?idFail="+id_fail+"&id_bantahan="+id_bantahan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function cetakSuratMintaDepositDalam30Hari(id_fail,id_bantahan,id_hakmilikpb) {
	var url = "../servlet/ekptg.report.ppt.SuratMintaDepositDalam30Hari?idFail="+id_fail+"&id_bantahan="+id_bantahan+"&id_hakmilikpb="+id_hakmilikpb;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function tambah_dokumen(){
	document.${formName}.command.value = "tambah_dokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function batal_bantahan(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "bantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}

function bantahan(){
	document.${formName}.command.value = "bantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function deposit(){
	document.${formName}.command.value = "deposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function borangO(){
	document.${formName}.command.value = "borangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function lanjutanTempoh(){
	document.${formName}.command.value = "lanjutanTempoh";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function susulanBantahan(){
	document.${formName}.command.value = "susulanBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function pemulanganDeposit(){
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function tarikBalikBantahan(){
	document.${formName}.command.value = "tarikBalikBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function batalBantahan(){
	document.${formName}.command.value = "batalBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function kembaliList(){
	document.${formName}.command.value = "kembaliList";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function kemaskiniBantahan(){
	document.${formName}.command.value = "kemaskiniBantahan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

function doChangeNoLot() {
	document.${formName}.command.value = "doChangeNoLot";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();		
}

function doChangeAlamatPembantah() {
	document.${formName}.command.value = "doChangeAlamatPembantah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();		
}

function simpanBantahan() {


	if(document.${formName}.chech_syarat.value != "" && document.${formName}.chech_syarat.value == "tidak_lulus"){
		alert("Pastikan syarat Permohonan Bantahan dipenuhi terlebih dahulu.");
  		return;		
	}
	if(document.${formName}.txdTkhTerimaBrgN.value == ""){
		alert("Sila masukkan \"Tarikh Terima Borang N\" terlebih dahulu.");
  		document.${formName}.txdTkhTerimaBrgN.focus(); 
		return;		
	}
/*	if(document.${formName}.txdBrgN.value == ""){
		alert("Sila masukkan \"Tarikh Borang N\" terlebih dahulu.");
  		document.${formName}.txdBrgN.focus(); 
		return;		
	}*/	
	if(document.${formName}.socHakmilik.value == ""){
		alert("Sila pilih \"No. Hakmilik\" terlebih dahulu.");
  		document.${formName}.socHakmilik.focus(); 
		return;		
	}	
/*	if(document.${formName}.txdTkhBrgH.value == ""){
		alert("Sila masukkan \"Tarikh Borang H\" terlebih dahulu.");
  		document.${formName}.txdTkhBrgH.focus(); 
		return;		
	}	
*/	if(document.${formName}.txdTkhAward.value == ""){
		alert("Sila masukkan \"Tarikh Award\" terlebih dahulu.");
  		document.${formName}.txdTkhAward.focus(); 
		return;		
	}	
	if(document.${formName}.socNamaPembantah.value == ""){
		alert("Sila pilih \"Nama Pembantah\" terlebih dahulu.");
  		document.${formName}.socNamaPembantah.focus(); 
		return;		
	}	
	if(document.${formName}.txtKptgnAtasTnh.value == ""){
		alert("Sila masukkan \"Kepentingan\" terlebih dahulu.");
  		document.${formName}.txtKptgnAtasTnh.focus(); 
		return;		
	}	
	if( document.${formName}.ukuran_luas.checked == false && document.${formName}.amaun_pampasan.checked == false && document.${formName}.terima_pampasan.checked == false && document.${formName}.umpuk_pampasan.checked == false ) {
		alert("Sila pilih \"Bantahan Terhadap\" terlebih dahulu.");
  		document.${formName}.ukuran_luas.focus(); 
		return;	
	}	
	if(document.${formName}.txtAmaunTuntutan.value == ""){
		alert("Sila masukkan \"Amaun Tuntutan\" terlebih dahulu.");
  		document.${formName}.txtAmaunTuntutan.focus(); 
		return;		
	}	
	if(document.${formName}.txtAlasanBantahan.value == ""){
		alert("Sila masukkan \"Alasan Bantahan\" terlebih dahulu.");
  		document.${formName}.txtAlasanBantahan.focus(); 
		return;		
	}		
	else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return; 
	document.${formName}.command.value = "simpanBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";  //PPT-35 (i)
	document.${formName}.submit();	
	}
}

function tambahDokumen() {
	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;	
	var id_hakmilikpb = document.${formName}.id_hakmilikpb.value ;		
	var id_hakmilik = document.${formName}.id_hakmilik.value ;	
	var id_pihakberkepentingan = document.${formName}.id_pihakberkepentingan.value ;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=tambah_dokumen&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&id_hakmilikpb="+id_hakmilikpb+"&id_hakmilik="+id_hakmilik+"&id_pihakberkepentingan="+id_pihakberkepentingan+"&location=maklumat_dokumen&point=txtnamadokumen";	
	document.${formName}.submit();
}

function view_Lampiran(id_dokumen) {
    var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=view_Dokumen_Details&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}

function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hapusDokumenMaster(r){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		var id_bantahan = document.${formName}.id_bantahan.value ;
		var id_permohonan = document.${formName}.id_permohonan.value ;		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=hapusDokumenMaster&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&readmode="+r;	
		document.${formName}.submit();
	}
}

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

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function RemoveNonNumeric( strString )
{
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

function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
}

function validateDate(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}

function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/";
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

<!--UTK DEFAULTKAN TAB KEPADA TAB BANTAHAN
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedtab});
//-->
	
</script>
