
#if ($statusBorangO == "true")
    <div class="warning">
    Sila proses permohonan. Tempoh Penghantaran Borang O ke Mahkamah hampir luput.
    </div>
#end

            <div class="warning">
    		<b>Syarat :</b><br/> 
    		1. Jika hadir/diwakili semasa award pampasan ialah 6 minggu dari tarikh award pampasan bertulis (Borang G)<br/>
    		2. Jika tidak hadir 6 minggu dari tarikh tawaran pampasan (Borang H) diterima atau 6 bulan dari tarikh award dibuat (Borang G), mengikut mana yang dahulu tamat
            </div>         


#if ($clearForm == "yes")    
    #set ($txtNoBantahan = "")	
    #set ($txtPengambilanNo = "")	
    #set ($txdTkhMasuk = "")
    #set ($txdTkhTerimaBrgN = "")
    #set ($txdBrgN = "")
    #set ($txtNoLot = "")
    #set ($txtNoPt = "")
    #set ($txdTkhBrgH = "")
    #set ($txdTkhAward = "")
    #set ($txtNoHakmilik = "")
    #set ($txtNama = "")
    #set ($txtAlamat1 = "")
    #set ($txtAlamat2 = "")
    #set ($txtAlamat3 = "")
    #set ($txtPoskod = "")
    #set ($txtKptgnAtasTnh = "")
    #set ($txtAlasanBantahan = "")
    #set ($txtUnitLot = "")   
    #set ($txtUnitPt = "")
    #set ($txtNoPt = "") 
    #set ($jenis_pembantah = "") 
    #set ($ukuran_luas = "") 
    #set ($amaun_pampasan = "") 
    #set ($terima_pampasan = "") 
    #set ($umpuk_pampasan = "") 
    #set ($TEMPchecked1 = "") 
    #set ($TEMPchecked2 = "") 
    #set ($TEMPchecked3 = "") 
    #set ($TEMPchecked4 = "") 
    #set ($txtAmaunTuntutan = "")   
    
    #set ($txtMaklumatBantahanTamat = "")
#end

#set ( $id_status = $statusFail.get("id_status") )

#foreach ( $senarai in $Header )
	#set ($nama_kementerian=$senarai.nama_kementerian)
    #set ($id_kementerian=$senarai.id_kementerian)
    #set ($id_agensi=$senarai.id_agensi)
    #set ($no_fail=$senarai.no_fail)
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

#if ($flag == "semak")
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
    
    #foreach ( $getAlamat in $getAlamatPembantah )
        #set ($id_pihakberkepentingan=$getAlamat.id_pihakberkepentingan)
        #set ($txtAlamat1=$getAlamat.alamat1) 
        #set ($txtAlamat2=$getAlamat.alamat2) 
        #set ($txtAlamat3=$getAlamat.alamat3) 
        #set ($txtPoskod=$getAlamat.poskod)        
	#end   
    
#end

#if ($clear == "alamat")
	#set ($txtAlamat1="")
    #set ($txtAlamat2="")
    #set ($txtAlamat3="")
    #set ($txtPoskod="")
#end

#foreach ( $data in $getTarikhPenting )
	#set ($txdTkhAward=$data.tarikh_borangg)
    #set ($txdTkhBrgH=$data.tarikh_borangh)  
#end

<!------------ CHECKING HADIR --------------->

#if ($date == "_award")
	#set ( $Checking_tarikh = $getMaklumatChecking.get("tarikh_borangg") )
#end

<!---------- CHECKING TIDAK HADIR ------------>

#if ($date_xHadirG == "borangG")
	#set ( $Checking_tarikh_xHadirG = $getMaklumatChecking.get("tarikh_borangg") )
#end

#if ($date_xHadirH == "borangH")
	#set ( $Checking_tarikh_xHadirH = $getMaklumatChecking.get("tarikh_terimaborangh") )
#end

#set ( $flag_hadir = $getMaklumatChecking.get("flag_hadir") )

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>
    
    #parse("app/ppt/frmPPTHeader.jsp")

<!------------------------------------------- HEADER ---------------------------------------------->

 <!--    <table width="100%" border="0">
           <tr>	
             <td width="24%" style="text-transform:uppercase">Kementerian</td>
             <td width="1%">:</td>
             <td width="75%">$!nama_kementerian
             
       	   </tr>
           <tr>
             <td style="text-transform:uppercase">No Fail Permohonan</td>
             <td>:</td>
             <td>$!no_fail</td>
           </tr>
           <tr>
           	 <td style="text-transform:uppercase">Status Permohonan</td>
             <td>:</td>
             <td>$!keterangan</td>
           </tr>           
           

           <tr>
             <td > <a href="javascript:open_header('detail_header')"><font color="blue">Maklumat Lengkap</font></a></td>
             <td></td>
             <td></td>
           </tr>
           
           <tr> 
          	<td  colspan="4">
            
        <fieldset id="detail_header" style=" display:none"  >
         <table width="100%" >            
         
                    
           
           <tr>
             <td width="24%" style="text-transform:uppercase">No. Permohonan</td>
             <td width="1%">:</td>
             <td width="75%">$!no_permohonan</td>
           </tr>
           <tr>
             <td style="text-transform:uppercase">Tarikh Permohonan</td>
             <td>:</td>
             <td>$!tarikh_permohonan</td>           
           </tr>
           <tr>
             <td style="text-transform:uppercase">Projek Negeri</td>
             <td>:</td>
             <td>$!projek_negeri</td>           
           </tr>
           <tr>
             <td style="text-transform:uppercase">Daerah / Jajahan</td>
             <td>:</td>
             <td>$!nama_daerah</td>            
           </tr>
           <tr>
             <td height="30" valign="top" style="text-transform:uppercase">Tujuan Pengambilan</td>
             <td valign="top">:</td>
             <td valign="top">$!tujuan</td>            
           </tr>          
           <tr>
           <td style="text-transform:uppercase">Agensi</td>
             <td>:</td>
             <td>$!nama_agensi<input type="hidden" name="id_agensi" id="id_agensi" value="$!id_agensi" /> </td>
           </tr>           
           <tr>
           <td style="text-transform:uppercase">Tarikh Dikehendaki</td>
             <td>:</td>
             <td>$!tarikh_kehendaki</td>
           </tr>
           <tr>
             <td style="text-transform:uppercase">No. Rujukan Kementerian</td>
             <td>:</td>
             <td>$!no_rujukan_surat</td>
           </tr>         
           <tr>
             <td style="text-transform:uppercase">No. Rujukan PTD</td>
             <td>:</td>
             <td>$!no_rujukan_ptd</td>
           </tr>           
           <tr>
             <td style="text-transform:uppercase">No. Rujukan PTG</td>
             <td>:</td>
             <td>$!no_rujukan_ptg</td>
           </tr>                      
           
           
          </table>
          </fieldset>
          </td>
          </tr>           
           
           
 	 </table>-->
     </br>     
<!------------------------------------------- END HEADER ---------------------------------------------->  

<!-------------------------------------- MAKLUMAT BANTAHAN -------------------------------------------->
<!--<div id="testing"></div>-->

<fieldset>

<legend>Maklumat Bantahan</legend>
    <table width="100%" border="0"> 
        <tr>
            <td width="22%">&nbsp;&nbsp;&nbsp;Bil. Bantahan</td>
          <td width="1%">:</td>
          <td width="28%"><input type="text" name="txtNoBantahan" id="txtNoBantahan" value="$!txtNoBantahan" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" tabindex="1" class="disabled" readonly /></td>
          <td width="1%">&nbsp;</td>
          <td width="20%">&nbsp;&nbsp;&nbsp;Status Bantahan</td>
          <td width="1%">:</td>
          <td width="27%"><select name="flag_syarat" id="flag_syarat" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled="disabled" >                   
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
          </select>          </td>
      </tr>
        <tr>
            <td rowspan="2">&nbsp;&nbsp;&nbsp;Pengambilan No.</td>
            <td rowspan="2">:</td>
            <td rowspan="2"><input type="text" name="txtPengambilanNo" id="txtPengambilanNo" value="$!txtPengambilanNo" tabindex="2" class="disabled" readonly />
           <!-- <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhMasuk',false,'dmy');" />--></td>
            <td rowspan="2">&nbsp;</td>
            <td rowspan="2">&nbsp;&nbsp;&nbsp;Pihak yang membantah</td>
          <td rowspan="2">:</td>
            <td><select name="jenis_pembantah" tabindex="6" id="jenis_pembantah" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled="disabled" >
              
                    
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
                                 
          
            </select>            </td>
        </tr>
        <tr>
          <td><!--<input type="text" name="txtUnitLot" id="txtUnitLot" value="$!txtUnitLot" class="disabled" readonly tabindex="8" />--></td>
        </tr>  
        <tr>
            <td><font color="red">*</font>&nbsp;Tarikh Terima </td>
            <td>:</td>
            <td><input type="text" name="txdTkhTerimaBrgN" id="txdTkhTerimaBrgN" value="$!txdTkhTerimaBrgN" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" tabindex="3" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerimaBrgN',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i></td>
            <td rowspan="3">&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;Tarikh Terima </td>
            <td>:</td>
            <td><input type="text" name="txdTkhBrgH" id="txdTkhBrgH" value="$!txdTkhBrgH" size="10" onblur="check_date(this);" class="disabled" readonly />
             <!-- <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhBrgH',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i> --></td>
        </tr>
        <tr>
          <td valign="top">&nbsp;&nbsp;&nbsp;Borang N</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;&nbsp;&nbsp;Borang H</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;&nbsp;Tarikh Borang N</td>
          <td>:</td>
          <td valign="top"><input type="text" name="txdBrgN" id="txdBrgN" value="$!txdBrgN" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" tabindex="4" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdBrgN',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i></td>
          <td><font color="red">*</font>&nbsp;Tarikh <em>Award</em></td>
          <td>:</td>
          <td><input type="text" name="txdTkhAward" id="txdTkhAward" value="$!txdTkhAward" size="10" onblur="check_date(this);checkBantahanCondition();" onkeyup="validateDate(this,this.value);" tabindex="10" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhAward',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i></td>
        </tr> 
        <tr>
            <td>&nbsp;&nbsp;&nbsp;No. Hakmilik</td>
            <td>:</td>
            <td>$selectHakmilik<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" /><input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$!id_hakmilikpb" /></td>
            <td>&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;Status Hadir Bicara</td>
           	<td>:</td>
            <td>
            
          #if ($flag_hadir=="1")           
          <input type="text" name="txtHadirBicara" id="txtHadirBicara" value="HADIR" size="10" class="disabled" readonly/>
          #else
          <input type="text" name="txtHadirBicara" id="txtHadirBicara" value="TIDAK HADIR" size="13" class="disabled" readonly />
          #end          </td>
        </tr> 
        
        <tr>
          <td>&nbsp;&nbsp;&nbsp;No Lot</td>
          <td>:</td>
         <!-- <td>$selectNoLot</td>-->
          <td><input type="text" name="txtNoLot" id="txtNoLot" value="$!txtNoLot" class="disabled" readonly="readonly" /></td>
          <td>&nbsp;</td>
          
          <td colspan="3">
          
          ##if ($flag_hadir=="1") 
          <!--<i><font color="#FF0000" style="font-size:10px">&nbsp;&nbsp;&nbsp;Syarat: Jika hadir/diwakili semasa award pampasan ialah 6 minggu &nbsp;&nbsp;&nbsp;dari tarikh award pampasan bertulis (Borang G)</font></i>-->  
          ##else
         <!-- <i><font color="#FF0000" style="font-size:10px">Syarat: Jika tidak hadir 6 minggu dari tarikh tawaran pampasan (Borang H) diterima atau 6 bulan dari tarikh award dibuat (Borang G), mengikut mana yang dahulu tamat</font></i>  -->
          ##end 
          
           <i><font color="#FF0000" style="font-size:10px">
          <b>Syarat :</b><br/> 
          <span id="alert_bantahan1">1. Jika hadir/diwakili semasa award pampasan ialah 6 minggu dari tarikh award pampasan bertulis (Borang G)</span>
          <br/>
          <span id="alert_bantahan2">2. Jika tidak hadir 6 minggu dari tarikh tawaran pampasan (Borang H) diterima atau 6 bulan dari tarikh award dibuat (Borang G), mengikut mana yang dahulu tamat</span>
          </font></i>  
                   
          </td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;&nbsp;No PT</td>
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
            <td width="22%">&nbsp;&nbsp;&nbsp;Nama Pembantah</td>
            <td width="1%">:</td>
            <td colspan="5">$selectNamaPembantah<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$!id_pihakberkepentingan" /></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;Alamat</td>
            <td>:</td>
            <td colspan="5"><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" class="disabled" readonly tabindex="12" /></td>
      </tr>  
        <tr>
            <td>&nbsp;</td>
            <td></td>
            <td colspan="5"><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" class="disabled" readonly tabindex="13" /></td>
      </tr> 
        <tr>
            <td>&nbsp;</td>
            <td></td>
            <td colspan="5"><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" class="disabled" readonly tabindex="14" /></td>
      </tr> 
        <tr>
          <td>&nbsp;&nbsp;&nbsp;Poskod</td>
          <td>:</td>
          <td colspan="5"><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" class="disabled" readonly tabindex="15" /></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;&nbsp;Negeri</td>
          <td>:</td>
          <td colspan="5">$selectNegeri</td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;&nbsp;Bandar</td>
          <td>:</td>
          <td colspan="5">$selectBandar</td>
        </tr> 
        <tr>
          <td>&nbsp;&nbsp;&nbsp;Jenis Pihak Pembantah</td>
          <td>:</td>
          <td colspan="5">$selectPihakBantah</td>
        </tr>
        <tr>
          <td valign="top"><font color="red">*</font>&nbsp;Kepentingan</td>
          <td valign="top">:</td>
          <td colspan="5"><textarea name="txtKptgnAtasTnh" id="txtKptgnAtasTnh" cols="50" rows="6" tabindex="16">$!txtKptgnAtasTnh</textarea></td>
        </tr>
        
       #if ($syarat=="bawah") 
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="5">   
                         	
          	<i><font color="#FF0000" style="font-size:10px"> PAMPASAN PIHAK BERKEPENTINGAN INI KURANG ATAU SAMA DENGAN RM3000.00 </font></i>          
            
            </td>
        </tr>
       #end
        
        <tr>
            <td><font color="red">*</font>&nbsp;Bantahan Terhadap</td>
            <td>:</td>
            <td width="4%">
            
            #if ($alasan1=="1") 
            <input type="checkbox" name="ukuran_luas" id="ukuran_luas" value="Y" tabindex="17" $TEMPchecked1 disabled />
            #else
            <input type="checkbox" name="ukuran_luas" id="ukuran_luas" value="Y" tabindex="17" $TEMPchecked1 />
            #end            
            
            </td>            
            <td width="73%" colspan="4">Ukuran Luas Tanah Tersebut;</td>
        </tr>

       
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>
            
            #if ($alasan2=="2") 
            <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 disabled />
            #else
            <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 />
            #end            
            
            </td>
            <td colspan="4">Jumlah Pampasan;</td>
        </tr>  
          
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>
            #if ($alasan3=="3") 
            <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" tabindex="19" $TEMPchecked3 disabled />
            #else
            <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" tabindex="19" $TEMPchecked3 />
            #end            </td>
            <td colspan="4">Orang-orang yang  menerima pampasan;</td>
      </tr>
      
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>
            #if ($alasan4=="4") 
            <input type="checkbox" name="umpuk_pampasan" id="umpuk_pampasan" value="Y" tabindex="20" $TEMPchecked4 disabled />
            #else
            <input type="checkbox" name="umpuk_pampasan" id="umpuk_pampasan" value="Y" tabindex="20" $TEMPchecked4 />
            #end            </td>
            <td colspan="4">Bahagian Pemberian Pampasan</td>
      </tr> 
                  
                                   
        <tr>
          <td><font color="red">*</font>&nbsp;Amaun Tuntutan (RM)</td>
          <td>:</td>
          <td colspan="5"><input type="text" size="15" name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!txtAmaunTuntutan" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAmaunTuntutan')" onkeyup="validateNumber(this,this.value);" /></td>
        </tr>       
        <tr>
          <td valign="top"><font color="red">*</font>&nbsp;Alasan Bantahan</td>
          <td valign="top">:</td>
          <td colspan="5"><textarea name="txtAlasanBantahan" id="txtAlasanBantahan" cols="50" rows="6" tabindex="21" onKeyDown="textCounter(this.form.txtAlasanBantahan,this.form.remLentxtAlasanBantahan,400);" onKeyUp="textCounter(this.form.txtAlasanBantahan,this.form.remLentxtAlasanBantahan,400);">$!txtAlasanBantahan</textarea></td>
        </tr>
        <!-- <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="5"><input type="text" readonly class="disabled" name="remLentxtAlasanBantahan" size="3" maxlength="3" value="400"> Baki Aksara </td>
        </tr>  -->
        <tr>
          <td valign="top">&nbsp;&nbsp;&nbsp;Maklumat Bantahan Tamat Tempoh</td>
          <td valign="top">:</td>
          <td colspan="5"><textarea name="txtMaklumatBantahanTamat" id="txtMaklumatBantahanTamat" cols="50" rows="6" tabindex="21" >$!txtMaklumatBantahanTamat</textarea></td>
        </tr>                        
    </table> 
    
</fieldset>

<!------------------------------------------------------ END MAKLUMAT PEMBANTAH ---------------------------------------->

<div align="center">
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:add_bantahan()" />
  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal()" /> 
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembaliList()" />
</div> 

</fieldset>

<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue"> Borang N - Permohonan Supaya Bantahan Dirujukkan Kepada Mahkamah </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$id_pihakberkepentingan" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
<input type="hidden" name="status_bantahan" id="status_bantahan" value="$status_bantahan" />
<!--<input type="hidden" name="Checking_tarikh" id="Checking_tarikh" value="$!Checking_tarikh" />-->
<input type="hidden" name="date_xHadirH" id="date_xHadirH" value="$!date_xHadirH" />
<input type="hidden" name="date_xHadirG" id="date_xHadirG" value="$!date_xHadirG" />
<input type="hidden" name="date" id="date" value="$!date" />
<input type="hidden" name="id_kementerian" id="id_kementerian" value="$!id_kementerian" />

<script>
//alert("Tarikh Borang H :"+document.${formName}.txdTkhBrgH.value);
//alert("Tarikh Award :"+document.${formName}.txdTkhAward.value);
//alert("Kehadiran :"+document.${formName}.txtHadirBicara.value);

//document.${formName}.alert_bantahan1.class = "blink";
//$jquery("#alert_bantahan1").removeClass("blink");
//$("#alert_bantahan1").text("Idle").removeClass("label-success").addClass("label-warning");
//$jquery("#alert_bantahan2").addClass("blink");
//document.${formName}.alert_bantahan2.class = "blink";

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

function CalculateWeek(selected_tarikh)
{
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




/*window.onload=function()
{
	//tarikh luput hadir (Borang G)
	var HADIR_BG = document.getElementById("date").value;
	var BH = document.getElementById("date_xHadirH").value;
	var BG = document.getElementById("date_xHadirG").value;
	
	if(HADIR_BG != ''){
   		alert("Permohonan bantahan ini tidak berjaya kerana telah melebihi tempoh 42 hari dari Tarikh Award oleh PTD");
		document.${formName}.command.value = "block_bantahan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
		document.${formName}.submit();	
	}	
	else if(BH != ''){
   		alert("Permohonan bantahan ini tidak berjaya kerana telah melebihi tempoh 42 hari dari tarikh terima Borang H");
		document.${formName}.command.value = "block_bantahan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
		document.${formName}.submit();		
	}
	else if(BG != ''){
   		alert("Permohonan bantahan ini tidak berjaya kerana telah melebihi tempoh 6 bulan dari Tarikh Award oleh PTD");
		document.${formName}.command.value = "block_bantahan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
		document.${formName}.submit();		
	}	
	else{
	document.${formName}.command.value = "DaftarBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	}	

}*/

/*window.onload=function()
{
	//tarikh luput
	var TL  = document.getElementById("Checking_tarikh").value;

	var dt1   = parseInt(TL.substring(0,2),10)+12;
   	var mon1  = parseInt(TL.substring(3,5),10);
   	var yr1   = parseInt(TL.substring(6,10),10);
   	var reminderTarikhLuput = new Date(yr1, mon1, dt1);
	

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth();
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	
	if(currentDate > reminderTarikhLuput){
   		alert("Tarikh Award/Tarikh Terima Borang H sudah melepasi 42 hari. Permohonan Bantahan tidak berjaya.");
		//document.getElementById("cmdBarux").style.display="block";
		document.${formName}.command.value = "block_bantahan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
		document.${formName}.submit();
  		return;
	}
}*/
/*---DAYAH BELAJAR JQUERY NGAN MAN---
$jquery(document).ready(function () {
	doDivAjaxCall$formname('testing','testing','');	
	
});*/


function open_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function kembaliList(){
	document.${formName}.command.value = "kembaliList";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function batal(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
/*function doChangeidNegeri() {
	document.${formName}.command.value = "doChangeidNegeri";
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
}*/
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function add_bantahan(){
	
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
		alert("Sila masukkan \"Tarikh Terima Borang H\" terlebih dahulu.");
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
  		document.${formName}.txtKptgnAtasTnh.focus(); 
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
	document.${formName}.command.value = "add_bantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
	}
}
function UploadDokumen(){
	document.${formName}.command.value = "UploadDokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
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
</script>

