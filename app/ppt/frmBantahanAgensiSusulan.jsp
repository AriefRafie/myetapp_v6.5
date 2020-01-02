<div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">TIADA MAKLUMAT SUSULAN BANTAHAN DIDAFTARKAN.</div>";
</script>
#end

#if ($clearForm == "yes")
	#set ($txtNoProsiding = "")
    #set ($txdTkhPerintah = "")
    #set ($txdTkhTerimaPerintah = "")	
    #set ($txtAmaunPampasanBantahan = "")
    #set ($txtKosPengapitHakim = "")
    #set ($txtTempohBayaran = "")
    #set ($sorKeputusanMahkamah = "")
    #set ($sorStatusPulangDep = "")
    #set ($unitTempohBayaran = "")
    #set ($txtKeteranganPampasan = "")
    #set ($txtNoRujukanMahkamah = "")
#end

#foreach ( $senarai in $Header )
	#set ($nama_kementerian=$senarai.nama_kementerian)
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

#foreach ( $senarai in $getMaklumatBantahan )    
    #set ($desc_status_bantahan_ap=$senarai.desc_status_bantahan_ap)
#end


#if($flag == "semak")
	#foreach ( $senarai in $getMaklumatSusulan)
    	#set ($txtNoProsiding=$senarai.no_rujukan_tanah)
        #set ($txdTkhPerintah=$senarai.tarikh_perintah)
        #set ($txdTkhTerimaPerintah=$senarai.tarikh_terima_perintah)
        #set ($txtAmaunPampasanBantahan=$senarai.amaun_award)
        #set ($txtKosPengapitHakim=$senarai.kos_pengapit_hakim)
        #set ($txtTempohBayaran=$senarai.tempoh_bayar)
        #set ($unitTempohBayaran=$senarai.unit_tempoh)
        #set ($txtNoRujukanMahkamah = $senarai.no_rujukan_mahkamah)
        #set ($txtKosJPPH = $senarai.kos_jpph)
        #set ($txtNamaJPPH = $senarai.nama_jpph)
        #set ($txtKosSwasta = $senarai.kos_swasta)
        #set ($txtNamaSwasta = $senarai.nama_swasta)
        #set ($txtNamaSyarikat = $senarai.syarikat_swasta)
    #end
#end

#set ( $id_award = $id_award.get("id_award") )

<!--  #set ( $txtKeteranganPampasan = $getKeteranganPampasan.get("keterangan_pampasan") )-->

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>

<!-- Header -->
	#parse("app/ppt/frmPPTHeader.jsp")
	
     </br>

<!------------------------------------------ TAB SUSULAN BANTAHAN -------------------------------------------->

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelectedTab(0);bantahan()">Bantahan</li>
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
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div> 
    
    <div class="TabbedPanelsContent">
    
    
    #if ($mode=="disabled")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#else
		#set($disability = "")
		#set($disabilityx = "")
		#end
		
 
<!---------------------------------------- PERINTAH MAHKAMAH --------------------------------------------->
<br />
<fieldset>
<legend>Maklumat Perintah</legend>
	<table width="100%" border="0">
	    <tr>
           <td width="1%">#if ($mode=="")<font color="red">*</font>#end</td>
		  <td>  
           
          No Ruj. Prosiding Bantahan (No Ruj Tanah)
         
          </td>
			<td>:</td>
			<td colspan="5"> #if ($mode=="disabled")
          <input type="text" name="txtNoProsiding" id="txtNoProsiding" value="$!txtNoProsiding" class="disabled" readonly />
          #else
          <input type="text" name="txtNoProsiding" id="txtNoProsiding" value="$!txtNoProsiding" />
          #end</td>
		</tr>
		<tr>
            <td width="1%"></td>
			<td>No. Rujukan Mahkamah</td>
			<td>:</td>
			<td colspan="5"><input type="text" size="35" $disability $disabilityx name="txtNoRujukanMahkamah" id="txtNoRujukanMahkamah" value="$!txtNoRujukanMahkamah" /></td>
		</tr>
		
		
	
    	<tr>
           <td width="1%">#if ($mode=="")<font color="red">*</font>#end</td>
          <td width="21%">
            
            
            Keputusan Mahkamah
            
            </td>
       	  <td width="1%">:</td>
       	  <td width="28%">
            #if ($mode=="disabled")
            <input type="radio" name="sorKeputusanMahkamah" id="sorKeputusanMahkamah" value="1" disabled $TEMPchecked1 />Penambahan Pampasan
            #else
            <input type="radio" name="sorKeputusanMahkamah" id="sorKeputusanMahkamah" value="1" $TEMPchecked1 />Penambahan Pampasan            #end            
            </td>
          <td width="1%">#if ($mode=="")<font color="red">*</font>#end</td>
       	  <td width="21%">
          
         
          Tarikh Perintah Mahkamah
          
          </td>
       	  <td width="1%">:</td>
   	  	  <td width="27%">
            #if ($mode=="disabled")
            <input type="text" name="txdTkhPerintah" id="txdTkhPerintah" value="$!txdTkhPerintah" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" class="disabled" readoly />            
            #else
            <input type="text" name="txdTkhPerintah" id="txdTkhPerintah" value="$!txdTkhPerintah" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" tabindex="1" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhPerintah',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
          #end            </td>   
        </tr> 
    	<tr>
          <td width="1%"></td>
            <td width="21%">&nbsp;</td>
   	  	  <td width="1%">&nbsp;</td>
       	  <td width="28%">
            #if ($mode=="disabled")
            <input type="radio" name="sorKeputusanMahkamah" id="sorKeputusanMahkamah" value="2" disabled $TEMPchecked2/>Pengurangan Pampasan
            #else
            <input type="radio" name="sorKeputusanMahkamah" id="sorKeputusanMahkamah" value="2" $TEMPchecked2/>Pengurangan Pampasan
            #end          
            
            </td>
          <td width="1%">#if ($mode=="")<font color="red">*</font>#end</td>
       	  <td width="22%">
          
          
          Tarikh Terima Perintah
         
          </td>
       	  <td width="1%">:</td>
   	  	  <td width="27%">
          	#if ($mode=="disabled")
          	<input type="text" name="txdTkhTerimaPerintah" id="txdTkhTerimaPerintah" value="$!txdTkhTerimaPerintah" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" class="disabled" readonly />         
          	#else
            <input type="text" name="txdTkhTerimaPerintah" id="txdTkhTerimaPerintah" value="$!txdTkhTerimaPerintah" size="10" onblur="check_date(this);" onkeyup="validateDate(this,this.value);" tabindex="1" />
          <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerimaPerintah',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
          #end          	</td>   
        </tr>  
    	<tr>
          <td width="1%"></td>
            <td width="21%">&nbsp;</td>
   	  	  <td width="1%">&nbsp;</td>
       	  <td width="28%">
            #if ($mode=="disabled")
            <input type="radio" name="sorKeputusanMahkamah" id="sorKeputusanMahkamah" value="3" disabled $TEMPchecked3 />Kekal
            #else
            <input type="radio" name="sorKeputusanMahkamah" id="sorKeputusanMahkamah" value="3" $TEMPchecked3 />Kekal
            #end          </td>
          <td width="1%">#if ($mode=="")<font color="red">*</font>#end</td>
       	  <td width="22%">
          
       
          Amaun Pampasan
        
          
          </td>
       	  <td width="1%">:</td>
   	      <td width="27%">
            #if ($mode=="disabled")
            <input type="text" size="11" name="txtAmaunPampasanBantahan" id="txtAmaunPampasanBantahan" value="$!Util.formatDecimal($!txtAmaunPampasanBantahan)" maxlength="12" class="disabled" readonly />
            #else
            <input type="text" size="11" name="txtAmaunPampasanBantahan" id="txtAmaunPampasanBantahan" value="$!txtAmaunPampasanBantahan" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAmaunPampasanBantahan')" onkeyup="validateNumber(this,this.value);" />
            <!-- <input type="hidden" name="txtAmaunPampasanBantahan" id="txtAmaunPampasanBantahan" value="$!txtAmaunPampasanBantahan" /> -->
          #end            </td>
    	</tr> 
    	<tr>
          <td width="1%"></td>
    	  <td>&nbsp;</td>
    	  <td>&nbsp;</td>
    	  <td>&nbsp;</td>
          <td>&nbsp;</td>
    	  <td>Bantahan&nbsp;(RM)</td>
    	  <td>&nbsp;</td>
    	  <td>&nbsp;</td>
  	  </tr>
    	<tr>
         <td width="1%"></td>
            <td width="21%" valign="top">Kos Pengapit Hakim&nbsp;(RM)</td>
   	  	  <td width="1%" valign="top">:</td>
       	  <td width="28%"><fieldset><legend>JPPH</legend>
            <table width="100%">
            	<tr>
                    <td width="1%"></td>
                	<td width="20%">Kos                    </td>
                    <td width="1%">:                    </td>
                    <td width="70%"><input type="text" size="11" name="txtKosJPPH" id="txtKosJPPH" value="$!Util.formatDecimal($!txtKosJPPH)" maxlength="12" #if($mode=="disabled") class="disabled" readonly #end onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtKosJPPH')"/>
                  </td>
              </tr>
                <tr>
                    <td width="1%"></td>
                	<td>Nama
                    </td>
                    <td>:
                    </td>
                    <td><input type="text" size="40" name="txtNamaJPPH" id="txtNamaJPPH" value="$!txtNamaJPPH" maxlength="12" #if($mode=="disabled") class="disabled" readonly #end/>
                    </td>
                </tr>
             </table>
            </fieldset>
             <fieldset><legend>Swasta</legend>
            <table width="100%">
            	<tr>
                    <td width="1%"></td>
                	<td width="20%">Kos                    </td>
                    <td width="10%">:                    </td>
                  <td width="70%"><input type="text" size="11" name="txtKosSwasta" id="txtKosSwasta" value="$!Util.formatDecimal($!txtKosSwasta)" maxlength="12" #if($mode=="disabled") class="disabled" readonly #end onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtKosSwasta')"/>
                  
                  </td>
                </tr>
                <tr>
                    <td width="1%"></td>
                	<td>Nama                    </td>
                    <td>:                    </td>
                    <td><input type="text" size="40" name="txtNamaSwasta" id="txtNamaSwasta" value="$!txtNamaSwasta" maxlength="12" #if($mode=="disabled") class="disabled" readonly #end/>                    </td>
                </tr>
                <tr>
                    <td width="1%"></td>
                	<td>Syarikat                    </td>
                    <td>:                    </td>
                    <td><input type="text" size="40" name="txtNamaSyarikat" id="txtNamaSyarikat" value="$!txtNamaSyarikat" maxlength="12" #if($mode=="disabled") class="disabled" readonly #end/>                    </td>
                </tr>
             </table>
            </fieldset>
            <table width="100%">
            <tr>
               <td width="1%"></td>
            	<td width="13%">
            Jumlah</td>
             <td width="1%">:             </td>
             <td width="70%">
             <input type="text" size="11" name="txtKosPengapitHakim" id="txtKosPengapitHakim" value="$!Util.formatDecimal($!txtKosPengapitHakim)" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtKosPengapitHakim')" onkeyup="validateNumber(this,this.value);" #if($mode=="disabled") class="disabled" readonly #end/></td></tr></table></td>
          <td width="1%">&nbsp;</td>
       	  <td width="22%" valign="top">&nbsp;&nbsp;</td>
       	  <td width="1%" valign="top">&nbsp;</td>
   	     
          
            
            
            
            
           <!-- <input type="text" size="11" name="txtKosPengapitHakim" id="txtKosPengapitHakim" value="$!txtKosPengapitHakim" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtKosPengapitHakim')" onkeyup="validateNumber(this,this.value);" />-->
            
            
            <input type="hidden" name="txtKosPengapitHakim" id="txtKosPengapitHakim" value="$!txtKosPengapitHakim" />
                 </td>
    	</tr>  
    	<tr>
           <td width="1%"></td>
            <td width="21%">Tempoh Bayaran</td>
   	  	  <td width="1%">:</td>
       	  <td width="28%">  #if ($mode=="disabled")
          <input type="text" name="txtTempohBayaran" id="txtTempohBayaran" value="$!txtTempohBayaran" size="1" onkeyup="validateNumber(this,this.value);" class="disabled" readonly />
   	        <select name="unitTempohBayaran" id="unitTempohBayaran" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled >  
                       
			#if($unitTempohBayaran=="1")                                                   
   	          <option value="1">Hari</option>
              <option value="2">Minggu</option> 
              <option value="3">Bulan</option>   	                      
            #elseif($unitTempohBayaran=="2")                                                
   	          <option value="2">Minggu</option>
              <option value="1">Hari</option> 
              <option value="3">Bulan</option>   
            #elseif($unitTempohBayaran=="3")                                                
   	          <option value="3">Bulan</option>
              <option value="2">Minggu</option>
              <option value="1">Hari</option>                     
	        #else                                                     
   	          <option value="1">Hari</option>
              <option value="2">Minggu</option> 
            <option value="3">Bulan</option>    	          
            #end 
            </select>
            
          #else 
          
          <input type="text" name="txtTempohBayaran" id="txtTempohBayaran" value="$!txtTempohBayaran" size="1" onkeyup="validateNumber(this,this.value);" />
   	        <select name="unitTempohBayaran" id="unitTempohBayaran" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" >  
                       
			#if($unitTempohBayaran=="1")                                                   
   	          <option value="1">Hari</option>
              <option value="2">Minggu</option> 
              <option value="3">Bulan</option>   	                      
            #elseif($unitTempohBayaran=="2")                                                
   	          <option value="2">Minggu</option>
              <option value="1">Hari</option> 
              <option value="3">Bulan</option>   
            #elseif($unitTempohBayaran=="3")                                                
   	          <option value="3">Bulan</option>
              <option value="2">Minggu</option>
              <option value="1">Hari</option>                     
	        #else                                                     
   	          <option value="1">Hari</option>
              <option value="2">Minggu</option> 
            <option value="3">Bulan</option>    	          
            #end 
            </select>          
          
          #end       </td>
          <td width="1%">&nbsp;</td>
       	  <td width="22%">&nbsp;&nbsp;</td>
       	  <td width="1%">&nbsp;</td>
   	      <td width="27%">
           </td>
    	</tr>   
    	
    	<tr>
          <td width="1%"></td>
          <td width="21%" valign="top">Keterangan Pampasan</td>
       	  <td width="1%" valign="top">:</td>
       	  <td colspan="5">

          #if ($mode=="disabled")
          <textarea name="txtKeteranganPampasan" id="txtKeteranganPampasan" cols="85" rows="6" class="disabled" readonly>$!txtKeteranganPampasan</textarea>
          #else
          <textarea name="txtKeteranganPampasan" id="txtKeteranganPampasan" cols="85" rows="6" onKeyDown="textCounter(this.form.txtKeteranganPampasan,this.form.remLentxtKeteranganPampasan,400);" onKeyUp="textCounter(this.form.txtKeteranganPampasan,this.form.remLentxtKeteranganPampasan,400);">$!txtKeteranganPampasan</textarea>
          #end          </td>
  	    </tr>               
    	<tr>
          <td width="1%"></td>
          <td width="21%" valign="top"></td>
       	  <td width="1%" valign="top"></td>
       	  <td colspan="5">&nbsp;&nbsp;<input type="text" readonly class="disabled" name="remLentxtKeteranganPampasan" size="3" maxlength="3" value="400"> Baki Aksara</td>
  	    </tr>                                                               
    </table>
</fieldset>



<!-- :::upload -->
<input type="hidden" name="nama_skrin" id="nama_skrin" value="perintah"  />

<fieldset id="senarai_dokumen" >
<legend>Senarai Dokumen Yang Disertakan</legend>
    
    <input name="cmdTambahDokumen" type="button" value="Tambah" onClick="tambahDokumen()" title="Sila klik untuk tambah dokumen" >    
    #if($listDokumen_size > 0)
     <input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumenMaster('$!readmode')" title="Sila tick untuk hapus dokumen" >
    #end
    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanSusulan()" />
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
  #foreach($list1 in $listDokumen)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
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
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
</table>
  
</fieldset> 

<!--------------------------------------- END PERINTAH MAHKAMAH ------------------------------------------>
        <div align="center">
          #if ($button=="view")
          <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniSusulan()" />
          <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />          
          #end
          
          #if ($button=="edit")
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanSusulan()" /> 
          <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="" /> 
          #end
          
          
          <input type="button" name="cmdkembali" id="cmdkembali" value="Kembali" onclick="javascript:kembaliList()" />
              
        </div>     
    </div>
    
    
    
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
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetaksuratKepadaAPSupayaMembayarPampasanTambahan_AP('$id_fail','$id_bantahan','$id_permohonan')"><font color="blue"> Surat Iringan Kepada Agensi Pemohon </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$id_pihakberkepentingan" />
<input type="hidden" name="status_bantahan" id="status_bantahan" value="$status_bantahan" />
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />
<input type="hidden" name="id_award" id="id_award" value="$id_award" />

<script type="text/javascript">
//:::upload
function tambahDokumen() {
	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	var id_hakmilik = document.${formName}.id_hakmilik.value ;	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=tambah_dokumen&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&id_hakmilik="+id_hakmilik+"&location=maklumat_dokumen&point=txtnamadokumen";	
	document.${formName}.submit();
}
//:::upload
function view_Lampiran(id_dokumen) {
    var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=view_Dokumen_Details&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=maklumat_dokumen&point=maklumat_dokumen&id_dokumen="+id_dokumen;		
	document.${formName}.submit();
}
//:::upload
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
//:::upload
function hapusDokumenMaster(r){
input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_bantahan = document.${formName}.id_bantahan.value ;
	var id_permohonan = document.${formName}.id_permohonan.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=hapusDokumenMasterPerintah&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&location=senarai_dokumen&point=senarai_dokumen&readmode="+r;	
	document.${formName}.submit();
	}
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

function cetaksuratKepadaAPSupayaMembayarPampasanTambahan_AP(id_fail,id_bantahan,id_permohonan) {
	//var url = "../servlet/ekptg.report.ppt.suratKepadaAPSupayaMembayarPampasanTambahan_AP?idFail="+id_fail+"&id_bantahan="+id_bantahan;
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+id_fail+"&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&report=suratKepadaAPSupayaMembayarPampasanTambahan_AP&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function kembaliList(){
	document.${formName}.command.value = "kembaliList";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}
function bantahan(){
	document.${formName}.command.value = "bantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function deposit(){
	document.${formName}.command.value = "deposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function borangO(){
	document.${formName}.command.value = "borangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function lanjutanTempoh(){
	document.${formName}.command.value = "lanjutanTempoh";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function susulanBantahan(){
	document.${formName}.command.value = "susulanBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function pemulanganDeposit(){
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function tarikBalikBantahan(){
	document.${formName}.command.value = "tarikBalikBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function batalBantahan(){
	document.${formName}.command.value = "batalBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}
function simpanSusulan(){
	if(document.${formName}.txtNoProsiding.value == ""){
		alert("Sila masukkan \"No Ruj. Prosiding Bantahan (No Ruj Tanah)\" terlebih dahulu.");
  		document.${formName}.txtNoProsiding.focus(); 
		return;		
	}	
	var radioSelected = false;	
	for (i = 0;  i < ${formName}.sorKeputusanMahkamah.length;  i++){
		if (${formName}.sorKeputusanMahkamah[i].checked)
		radioSelected = true;
	}	
	if (!radioSelected){
		alert("Sila pilih \"Keputusan Mahkamah\" terlebih dahulu.");
		return (false);
	}
							
	if(document.${formName}.txdTkhPerintah.value == ""){
		alert("Sila masukkan \"Tarikh Perintah\" terlebih dahulu.");
  		document.${formName}.txdTkhPerintah.focus(); 
		return;		
	}
	if(document.${formName}.txdTkhTerimaPerintah.value == ""){
		alert("Sila masukkan \"Tarikh Terima Perintah\" terlebih dahulu.");
  		document.${formName}.txdTkhTerimaPerintah.focus(); 
		return;		
	}	
	if(document.${formName}.txtAmaunPampasanBantahan.value == ""){
		alert("Sila masukkan \"Amaun Pampasan Bantahan (RM)\" terlebih dahulu.");
  		document.${formName}.txtAmaunPampasanBantahan.focus(); 
		return;		
	}		
/*	if(document.${formName}.txtKosPengapitHakim.value == ""){
		alert("Sila masukkan \"Kos Pengapit Hakim (RM)\" terlebih dahulu.");
  		document.${formName}.txtKosPengapitHakim.focus(); 
		return;		
	}		
	if(document.${formName}.txtTempohBayaran.value == ""){
		alert("Sila masukkan \"Tempoh Bayaran\" terlebih dahulu.");
  		document.${formName}.txtTempohBayaran.focus(); 
		return;		
	}	
	if(document.${formName}.unitTempohBayaran.value == ""){
		alert("Sila masukkan \"Unit Tempoh Bayaran\" terlebih dahulu.");
  		document.${formName}.unitTempohBayaran.focus(); 
		return;		
	}*/					
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "simpanSusulan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
		document.${formName}.submit();
	}
}

function kemaskiniSusulan(){
	document.${formName}.command.value = "kemaskiniSusulan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian";
	document.${formName}.submit();
}

function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
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
function validateDate(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumericDate(content);
		return;
	}
}
function RemoveNonNumericDate( strString )
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
