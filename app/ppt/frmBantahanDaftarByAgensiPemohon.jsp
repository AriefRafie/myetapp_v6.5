
          ##if ($flag_hadir=="1") 
 
<!--            <div class="warning">
    		Syarat: Jika hadir/diwakili semasa award pampasan ialah 6 minggu dari tarikh award pampasan bertulis (Borang G)
    		</div>-->
          
          ##else
         
<!--            <div class="warning">
    		Syarat: Jika tidak hadir 6 minggu dari tarikh tawaran pampasan (Borang H) diterima atau 6 bulan dari tarikh award dibuat (Borang G), mengikut mana yang dahulu tamat
            </div> -->        
         
          ##end 


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

#set ( $id_status = $statusFail.get("id_status"))

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

#foreach ( $senaraiPampasan in $getMaklumatPampasan )
    #set ($txtAmaunTuntutan=$senaraiPampasan.amaun_bayaran)
#end

#foreach ( $data in $getTarikhPenting )
	#set ($txdTkhAward=$data.tarikh_sedia_award)
    #set ($txdTkhBrgH=$data.tarikh_borangh)    
#end

#if ($date == "_award")
	#set ( $Checking_tarikh = $getMaklumatChecking.get("tarikh_sedia_award") )
#end

#if ($date == "_borangH")
	#set ( $Checking_tarikh = $getMaklumatChecking.get("tarikh_terimaborangh") )
#end

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>

<!------------------------------------------- HEADER ---------------------------------------------->

     <table width="100%" border="0">
           <tr>	
             <td width="24%" style="text-transform:uppercase">Kementerian</td>
             <td width="1%">:</td>
             <td width="75%">$!nama_kementerian
             <input type="hidden" name="id_kementerian" id="id_kementerian" value="$!id_kementerian" /></td>
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
            
        <fieldset id="detail_header" style=" display:none">
         <table width="100%" >            
         
                    
           
           <tr>
             <td width="24%" style="text-transform:uppercase">Bil. Permohonan</td>
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
             <td>$!nama_agensi<input type="text" name="id_agensi" id="id_agensi" value="$!id_agensi" /> </td>
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
           
           
 	 </table>
     </br>     
<!------------------------------------------- END HEADER ---------------------------------------------->  

<!-------------------------------------- MAKLUMAT BANTAHAN -------------------------------------------->

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
                        
            
            #if($jenis_pembantah=="2")                                     
            
              <option value="2">Agensi Pemohon</option>
              <option value="1">Pihak Berkepentingan</option>
              
            
            #elseif($jenis_pembantah=="1") 
            
              <option value="2">Agensi Pemohon</option>
              <option value="1">Pihak Berkepentingan</option>
              
            
	        #else   
            
              <option value="2">Agensi Pemohon</option>
              <option value="1">Pihak Berkepentingan</option>
                          
            #end 
                                 
          
            </select>            </td>
        </tr>
        <tr>
          <td></td>
        </tr>  
        <tr>
            <td><font color="red">*</font>&nbsp;Tarikh Terima </td>
            <td>:</td>
            <td><input type="text" name="txdTkhTerimaBrgN" id="txdTkhTerimaBrgN" value="$!txdTkhTerimaBrgN" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="3" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerimaBrgN',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i></td>
            <td rowspan="3">&nbsp;</td>
            <td><font color="red">*</font>&nbsp;Tarikh Terima </td>
            <td>:</td>
            <td><input type="text" name="txdTkhBrgH" id="txdTkhBrgH" value="$!txdTkhBrgH" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="9" />
              <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhBrgH',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i></td>
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
          <td valign="top"><input type="text" name="txdBrgN" id="txdBrgN" value="$!txdBrgN" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="4" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdBrgN',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i></td>
          <td><font color="red">*</font>&nbsp;Tarikh <em>Award</em></td>
          <td>:</td>
          <td><input type="text" name="txdTkhAward" id="txdTkhAward" value="$!txdTkhAward" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="10" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhAward',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i></td>
        </tr> 
        <tr>
            <td>&nbsp;&nbsp;&nbsp;No. Hakmilik</td>
            <td>:</td>
            <td>$selectHakmilik<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" /><input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$!id_hakmilikpb" /></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
           	<td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr> 
        <tr>
          <td>&nbsp;&nbsp;&nbsp;No Lot</td>
          <td>:</td>
          <td>$selectNoLot</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
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

<!------------------------------------ END MAKLUMAT BANTAHAN ------------------------------------------> 

<!------------------------------------ MAKLUMAT PEMBANTAH --------------------------------------------->  
<br />

<fieldset>
	<legend>Maklumat Pembantah</legend>
    <table width="100%" border="0">
        <tr>
          <td width="33%">&nbsp;&nbsp;&nbsp;Nama Pembantah</td>
          <td width="1%">:</td>
          <td colspan="5"><input type="text" name="txtNamaPembantah" id="txtNamaPembantah" value="$!txtNamaPembantah" size="70" class="disabled" readonly tabindex="12" /><input type="hidden" name="txtIdKementerian" id="txtIdKementerian" value="$!txtIdKementerian" /></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;Alamat</td>
            <td>:</td>
            <td colspan="5"><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="70" class="disabled" readonly tabindex="12" /></td>
      </tr>  
        <tr>
            <td>&nbsp;</td>
            <td></td>
            <td colspan="5"><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="70" class="disabled" readonly tabindex="13" /></td>
      </tr> 
        <tr>
            <td>&nbsp;</td>
            <td></td>
            <td colspan="5"><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="70" class="disabled" readonly tabindex="14" /></td>
      </tr> 
        <tr>
          <td>&nbsp;&nbsp;&nbsp;Poskod</td>
          <td>:</td>
          <td colspan="5"><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" class="disabled" readonly tabindex="15" /></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;&nbsp;Negeri</td>
          <td>&nbsp;</td>
          <td colspan="5"><input type="text" name="txtNamaNegeri" id="txtNamaNegeri" value="$!txtNamaNegeri" size="50" class="disabled" readonly tabindex="14" /><input type="hidden" name="txtIdNegeri" id="txtIdNegeri" value="$!txtIdNegeri" /></td>
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
          	<i><font color="#FF0000" style="font-size:10px"> PAMPASAN PIHAK BERKEPENTINGAN INI KURANG ATAU SAMA DENGAN RM15000.00 </font></i>          
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
          <td width="1%"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
            <td>
	        	<table id="bantahanpampasan">
	        		<tr>
	        		 <!-- PPT-35 (i) Jenis Bantahan Pampasan -->
					 #set ( $checked = "" )
				     #foreach ($semakan in $senaraiSemakan)
					 <td  width="10">
		             
					 #if ( $semakclass.isSemakan("$permohonanInfo.idpermohonan", "$semakan.id" ))
					   	#set ( $checked = "checked" )
					 #else
					   	#set ( $checked = "" )
					 #end
					   	 <input class="cb" type="checkbox" name="jenisbantahanpampasan" value="$semakan.id" onclick="checkJumlahPampasan(true)" $checked>
					 </td>
					 <td >
					 	$semakan.keterangan <!-- $semak.id -->
					 </td>
				     #end
				    </tr>
				</table>
            	
          
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>
            
            #if ($alasan3=="3") 
            <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" tabindex="19" $TEMPchecked3 disabled />
            #else
            <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" tabindex="19" $TEMPchecked3 />
            #end            
            
            </td>
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
            #end            
            
            </td>
            <td colspan="4">Bahagian Pemberian Pampasan</td>
      </tr> 
                  
                                   
        <tr>
          <td>&nbsp;&nbsp;&nbsp;Amaun Pampasan Ditawarkan (RM)</td>
          <td>:</td>
          <td colspan="5"><input type="text" size="11" name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!txtAmaunTuntutan" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAmaunTuntutan')" onkeyup="validateNumber(this,this.value);" /></td>
        </tr>       
        <tr>
          <td valign="top"><font color="red">*</font>&nbsp;Alasan Bantahan</td>
          <td valign="top">:</td>
          <td colspan="5"><textarea name="txtAlasanBantahan" id="txtAlasanBantahan" cols="50" rows="6" tabindex="21" onKeyDown="textCounter(this.form.txtAlasanBantahan,this.form.remLentxtAlasanBantahan,400);" onKeyUp="textCounter(this.form.txtAlasanBantahan,this.form.remLentxtAlasanBantahan,400);" >$!txtAlasanBantahan</textarea></td>
        </tr>
        
        <!-- <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="5"><input type="text" readonly class="disabled" name="remLentxtAlasanBantahan" size="3" maxlength="3" value="400"> Baki Aksara </td>
        </tr>   --> 
        
         <tr>
          <td valign="top">&nbsp;&nbsp;&nbsp;Maklumat Bantahan Tamat Tempoh</td>
          <td valign="top">:</td>
          <td colspan="5"><textarea name="txtMaklumatBantahanTamat" id="txtMaklumatBantahanTamat" cols="50" rows="6" tabindex="22" >$!txtMaklumatBantahanTamat</textarea></td>
        </tr>    
                                                 
    </table> 
    
</fieldset>

<!------------------------------------------------------ END MAKLUMAT PEMBANTAH ---------------------------------------->

<div align="center">
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:add_bantahanAP()" />
  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalAP()" /> 
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
<input type="hidden" name="status_bantahan_ap" id="status_bantahan_ap" value="$status_bantahan_ap" />
<input type="hidden" name="Checking_tarikh" id="Checking_tarikh" value="$!Checking_tarikh" />

<script>

/*window.onload=function()
{
	//tarikh luput
	var TL  = document.getElementById("Checking_tarikh").value;

	var dt1   = parseInt(TL.substring(0,2),10)+12;
   	var mon1  = parseInt(TL.substring(3,5),10);
   	var yr1   = parseInt(TL.substring(6,10),10);
   	var reminderTarikhLuput = new Date(yr1, mon1, dt1);
	
	//alert(reminderTarikhLuput);

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth();
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);
	
	//alert(currentDate);
	
	if(currentDate > reminderTarikhLuput){
   		alert("Tarikh Award/Tarikh Terima Borang H sudah melepasi 42 hari. Permohonan Bantahan tidak berjaya.");
		//document.getElementById("cmdBarux").style.display="block";
		document.${formName}.command.value = "block_bantahan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
		document.${formName}.submit();
  		return;
	}
}*/

function open_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function kembaliList(){
	document.${formName}.command.value = "bantahan_agensi";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function batalAP(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "batalAP";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
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
function add_bantahanAP(){

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
	if(document.${formName}.txdTkhBrgH.value == ""){
		alert("Sila masukkan \"Tarikh Terima Borang H\" terlebih dahulu.");
  		document.${formName}.txdTkhBrgH.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhAward.value == ""){
		alert("Sila masukkan \"Tarikh Award\" terlebih dahulu.");
  		document.${formName}.txdTkhAward.focus(); 
		return;		
	}	
	if(document.${formName}.txtNamaPembantah.value == ""){
		alert("Sila masukkan \"Nama Pembantah\" terlebih dahulu.");
  		document.${formName}.txtNamaPembantah.focus(); 
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
	document.${formName}.command.value = "add_bantahanAP";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
	}
}
function UploadDokumen(){
	document.${formName}.command.value = "UploadDokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
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



// PPT-35 (i) Jenis Bantahan Pampasan Jika Dipilih
function semakJenisBantahanPampasan() {
	var checked = 0;
    for (var i = 0; i < 3; i++) {
      if(document.${formName}["jenisbantahanpampasan"][i].checked == !false){
      	checked++;
      	return checked++;
      }
    }
    
    if (checked == 0) {
		alert("Pastikan pilihan 'Jumlah Pampasan' dipilih");
  		document.${formName}.txtKptgnAtasTnh.focus(); 
		return error++;
    }
    return;
}

function checkJumlahPampasan(checked) {
    var elm = document.${formName}.amaun_pampasan;
    if (checked != elm.checked) {
        elm.click();
    }
}




</script>

