<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">



#if($flag_skrin == "daftar_sek8_online" || $flag_skrin == "skrin_hakmilik_sek8_KJP" ) 
<link rel="stylesheet" type="text/css" href="../../css/eTapp_KJP.css">
#else
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css">
#end
<!--
POPUP HAKMILIK
<br />
id_permohonan : $id_permohonan
<br />
-->




<div style="display:none">
id_mohon_selected : <input type="text" id="id_mohon_selected" name="id_mohon_selected" value="$id_mohon_selected">
<br />
id_permohonan : <input type="text" id="id_permohonan" name="id_permohonan" value="$id_permohonan">

</div>




<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
<fieldset>
<legend>CARIAN HAKMILIK
</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

<tr>
          <td width="1%">&nbsp;</td>
          <td width="25%" align="left" valign="top">NO. LOT/ NO. PT</td>
          <td width="1%" valign="top">:</td>
          <td width="73%" valign="top">          
          <input name="NO_LOT" type="text" id="NO_LOT"  value="$NO_LOT" size="80" maxlength="500"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />          
           </td>
        </tr>
        
<tr >
          <td>&nbsp;</td>
          <td align="left" valign="top">NAMA PB</td>
          <td valign="top">:</td>
          <td valign="top">          
          <input name="NAMA_PB" type="text" id="NAMA_PB" value="$NAMA_PB" size="80" maxlength="1000"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />         
          </td>
        </tr> 
        
        
        <tr >
          <td>&nbsp;</td>
          <td align="left" valign="top">MYID PB</td>
          <td valign="top">:</td>
          <td valign="top">          
          <input name="NO_PB" type="text" id="NO_PB" value="$NO_PB" size="80" maxlength="1000"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />         
          </td>
        </tr>        
        

<tr>
  <td  ></td>
  <td align="left" valign="top" ></td>
  <td valign="top" ></td>
  <td valign="top" >
   
   
    <input type="button" id="cariHakmilik" name="cariHakmilik" value="CARI" onclick="carian()"/>
    <input type="button" id="kosongHariHakmilik" name="kosongHariHakmilik" value="RESET" onclick="kosongkan()"/>
    
    </td>
  
  
</tr>
</table>
</fieldset>
</td>
  </tr>
</table>



<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI HAKMILIK</b></legend>          
		#parse("app/utils/record_pagingPopup.jsp")
        #set ($count = 0) 
        <!--
       : $showSJ
        <br />
       : $flag_subjaket
        -->
        
        
        #if($flag_skrin == "skrin_list_hakmilik_pb_sek8" || $flag_skrin == "skrin_hakmilik_pb_sek8")
        
            #if($SenaraiFail.size() > 1 && $flag_subjaket!="1")
                        #if($showSJ=="no")	
                        <input type="button" style="display:none" name="cmdJanaAuto" value="Jana Subjaket (auto)" onClick="javascript:janaSubjaket($!id_permohonan,'$flag_skrin')">
                        <input type="button" style="display:none" name="cmdJanaManual" value="Jana Subjaket (manual)" onClick="javascript:janaSubjaketManual($!id_permohonan,'$flag_skrin')">
                        #else
                        <input type="button" style="display:none" name="cmdSimpan" value="Simpan Subjaket" onClick="javascript:simpanSj('$!id_permohonan','$flag_skrin')">
                        <input type="button" style="display:none" name="cmdBatalJana" value="Batal" onClick="javascript:janaSubjaketManual('$!id_permohonan','$flag_skrin'))">
                        #end
            #end
            
            #if($SenaraiFail.size() > 1 && $flag_subjaket=="1")
                        #if($showSJ=="no")
                        <input type="button" style="display:none" name="cmdJanaAuto" value="Jana Subjaket (auto)" onClick="javascript:janaSubjaket($!id_permohonan,'$flag_skrin')">
                        <input type="button" style="display:none" name="cmdKemaskini" value="Kemaskini Subjaket" onClick="javascript:janaSubjaketManual('$!id_permohonan','$flag_skrin');">
                        #else
                        <input type="button" style="display:none" name="cmdSimpan" value="Simpan Subjaket" onClick="javascript:simpanSj('$!id_permohonan','$flag_skrin')">
                        <input type="button" style="display:none" name="cmdBatalJana" value="Batal" onClick="javascript:janaSubjaketManual('$!id_permohonan','$flag_skrin')">
                        #end
            #end
        
        #end
        
        
        
        
       #if($flag_skrin == "hakmilik_borangL")
        
        #if($isEdit=="no")
				<input type="button" style="display:none" name="cmdKemaskini" value ="Kemaskini Maklumat Borang L" onClick="javascript:kemaskiniBorangL('$!id_permohonan','$flag_skrin')">
				#else
				<input type="button" style="display:none" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangL('$!id_permohonan','$flag_skrin')">
				<input type="button" style="display:none" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_permohonan','$flag_skrin')">
				#end
        #end        
        
        
        <table align="center" width="100%" cellspacing="1" cellpadding="0">
      
        
        <tr class="table_header">
        				<!-- PPT-30(ii) -->
        				<td align="center" ><b><input type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></b></td>
                  		<td align="center" ><b><font color="white">NO</font></b></td>
                  		<td  ><b><font color="white">NO HAKMILIK</font></b></td>
                        #if($flag_skrin != "hakmilik_borangL")
                  		<td  align="center"><b><font color="white">JUMLAH PB</font></b></td>
                        #end
                  		<td  ><b><font color="white">NO. LOT/NO. PT</font></b></td>    
                        #if($flag_skrin != "hakmilik_borangL")          
                  		<td  ><b><font color="white">MUKIM/PEKAN/BANDAR</font></b></td>
                        #end
                  		<td   ><b><font color="white">LUAS DIAMBIL</font></b></td>
                  		
                        #if($flag_skrin == "daftar_sek8_online" || $flag_skrin == "skrin_hakmilik_sek8_KJP")
                        <td   ><b><font color="white">TARIKH H</font></b></td>
                        <td   ><b><font color="white">TARIKH K</font></b></td>
                        #end
                        #if($flag_skrin == "daftar_sek8_online")  
                        
                        
                        #elseif($flag_skrin == "bantahan_mahkamah") 
                     
                        <td ><b><font color="white">STATUS BANTAHAN</font></b></td>
                        #else
                  		<td ><b><font color="white">NO. SUBJAKET</font></b></td>
                  		<td   align="center"><b><font color="white">BORANG I</font></b></td>  
                        #end
                        #if($flag_skrin == "daftar_sek8_online" || $flag_skrin == "skrin_hakmilik_sek8_KJP")
                        <td  align="center"><b><font color="white">CETAK</font></b></td>
                        #end
                        
                           
                        #if($flag_skrin == "laporan_bangunan")                        
                		<td   align="center"><b><font color="white">TAMBAH BANGUNAN</font></b></td>                		 
                        #end 
                        
                         #if($flag_skrin == "senarai_siasatan" || $flag_skrin == "senarai_siasatan_sementara")
                        
                		<td   align="center"><b><font color="white">SIASATAN</font></b></td>   
                		 
                        #end 
                        
                         #if($flag_skrin == "hakmilik_borangL")
                        
                		<td   align="center"><b><font color="white">TARIKH BORANG L</font></b></td>
                        <td   align="center"><b><font color="white">TEMPOH (HARI)</font></b></td>
                        <td   align="center"><b><font color="white">STATUS BORANG L</font></b></td>
                        <td   align="center"><b><font color="white">CETAK BORANG L</font></b></td>   
                		 
                        #end 
                                 
           		 	</tr>
        
        
        #if($SenaraiFail.size()>0)

					#set ($count_check = 0)
                    #foreach ( $listTanah in $SenaraiFail )
                    #set ($count = $count+1)
                    #set( $i = $velocityCount )
                    #if ( ($i % 2) != 1 )
                    #set( $rowx = "row2" )
                    #else
                    #set( $rowx = "row1" )
                    #end
                    
                      <tr>
                        <!-- PPT-30 -->
                        <td class="$rowx" align="center"><input type="checkbox" class="idHakmilik" $checkedCB name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>
                		<td class="$rowx" align="center">$!listTanah.rn</td>
                        <!--
                		#if($showLinkHM=="yes" || ($ModuleName=="ekptg.view.ppt.FrmSek8PermintaanUkur" && $listTanah.flagPU == "yes"))
               		 	<td  class="$rowx"><a href="javascript:viewHM('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</font></a></td>
                		#else
                		<td  class="$rowx">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
                		#end
                        -->
                        <td  class="$rowx">
                            
                        #if($flag_skrin == "warta" || $flag_skrin == "papar_lot_borangE")
                       $!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik
                       
                        #elseif($flag_skrin == "bantahan_mahkamah")
                        
                        <a href="javascript:paparByAgensi('$listTanah.id_hakmilik','$listTanah.status_bantahan_ap','$id_permohonan','$flag_skrin')"><font color="blue">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</font></a>
                       
                       	#elseif($flag_skrin == "kemasukan_borangF")
                        <a href="javascript:kemasukanBorangF('$!listTanah.id_hakmilik','$id_permohonan','$flag_skrin','$!listTanah.id_borange')"><font color="blue">
                        
                        #if($!listTanah.no_hakmilik != "")
                        $!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik
                        #else
                        -
                        #end
                        
                        
                        </font></a>
                       	#elseif($flag_skrin == "senarai_siasatan" || $flag_skrin == "senarai_siasatan_sementara"  || $flag_skrin == "hakmilik_borangL")
                        $!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik
                        
                        
                        #elseif($flag_skrin == "senarai_pu" && $listTanah.flagPU != "yes")
                        $!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik
                        
                        #else
                        <a href="javascript:paparHakmilik('$!listTanah.id_hakmilik','$id_permohonan','$flag_skrin')"><font color="blue">
                        
                        #if($!listTanah.no_hakmilik != "")
                        $!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik
                        #else
                        -
                        #end
                        
                        </font></a>
                        
                        #end
                        
                        </td>
                        #if($flag_skrin != "hakmilik_borangL")
                		<td class="$rowx" align="center">$!listTanah.totalPB
                        
                        #if($flag_skrin == "skrin_list_hakmilik_pb_sek8" || $flag_skrin == "skrin_hakmilik_pb_sek8" || $flag_skrin == "daftar_sek8_online" || $flag_skrin == "skrin_hakmilik_sek8_KJP")
                        &nbsp;
                        
                       <!--  <a href="javascript:tambahPbPopup('$!listTanah.id_hakmilik','$flag_skrin')"><font color="blue">(Tambah PB)</font></a> -->
                        <a href="javascript:paparHakmilik('$!listTanah.id_hakmilik','$id_permohonan','$flag_skrin')"><font color="blue">(Tambah PB)</font>
                        
                        #end 
                        
                        </td>
                        #end
                		<td class="$rowx">$!listTanah.no_lotpt</td> 
                        #if($flag_skrin != "hakmilik_borangL")    
                		<td class="$rowx" >$!listTanah.nama_mukim  
                        #if($listTanah.seksyen != "" && $listTanah.seksyen != "-")                      
                        <font style='font-size:10px' >Seksyen $listTanah.seksyen</font>
                        #end
                        </td>
                        #end
                		<td  class="$rowx" >$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
                        #if($flag_skrin == "daftar_sek8_online" || $flag_skrin == "skrin_hakmilik_sek8_KJP")
                		<td  class="$rowx" >$!listTanah.tarikh_borangh</td>
                		<td  class="$rowx" >$!listTanah.tarikh_borangk</td>
                		#end
                        
                        #if($flag_skrin == "daftar_sek8_online") 
                        
                        #elseif($flag_skrin == "bantahan_mahkamah") 
                        <td class="$rowx">
                          #if ( ($listTanah.flag_online == '1') || ($listTanah.flag_online == '2') )
                          <font color="red">$listTanah.keteranganStatusBantahan (Permohonan Online)</font>
                          #else
                          $listTanah.keteranganStatusBantahan
                          #end
                        </td>
                        
                        
                        
                        #else
                        
                		<td  class="$rowx">
                        
                        
                       #set($input_div_temp = $!listTanah.id_hakmilik + "div")
                       #set($input_div_alert = $count_check + "alert")
                       <div style="display:none" id="$input_div_temp" ></div>
                       
                       
                       #if($showSJ=="yes")
                       #set($input_sj = $!listTanah.id_hakmilik + "txtSj")
                    
                    
                       <input type="text" name="$input_sj" id="$input_sj" style="text-align:center" value="$!listTanah.no_subjaket" maxlength="4" size="4" onkeyup="validateNumber(this,this.value);checkDuplicated(this.value,'$input_div_temp','$count_check','$input_div_alert');" onblur="checkDuplicated(this.value,'$input_div_temp','$count_check','$input_div_alert');validateNumber(this,this.value)" >             
                       
                       
                       #end
                         
                       #if($flag_subjaket=="1" && $showSJ=="no")
                        #if($!listTanah.no_subjaket != "")
                        Sj.$!listTanah.no_subjaket
                        #end
                       #end 
                        
                  
                        <span  id="$input_div_alert" ></span>
                        
                        </td>
                        
                        <td class="$rowx" align="center">
                		#if($listTanah.flag_segera_sebahagian=="Y")YA #elseif($listTanah.flag_segera_sebahagian=="N")TIDAK #end 
                		#end
                	
                		#if($no_fail == "")
                		<td align="center" class="$rowx"><span class="blink"><b>Borang K belum dikeluarkan</b></span></td>
                		#else
                		#if($flag_skrin == "daftar_sek8_online" || $flag_skrin == "skrin_hakmilik_sek8_KJP")
                		<td  align="center" class="$rowx" >$!listTanah.cetak
                		 <input a href="#" type="button" value="Cetak" onClick="javascript:cetakBorangK('$!id_permohonan','$listTanah.id_hakmilik')"><font color="blue"></font></a>
        			
                		#end
                		#end
                	
                		
                        #if($flag_skrin == "laporan_bangunan")
                        <td class="$rowx" align="center">
                			<input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahBG('$!listTanah.id_hakmilik','$flag_skrin')">
                		</td> 
                        #end
                        
                         #if($flag_skrin == "senarai_siasatan" || $flag_skrin == "senarai_siasatan_sementara")
                        
                		 <td class="$rowx">
            

       
       #if($listTanah.id_siasatan != "")      
             
                         <table width="100%"  >
<tr>
<td colspan="3">
 <div align="left"><a href="javascript:papar('$listTanah.id_siasatan','$listTanah.id_hakmilik','$flag_skrin')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">MAKLUMAT  SIASATAN</font></a></div></td>
</tr>

<tr>
<td colspan="3">
 <div align="left"><a href="javascript:kehadiran('$listTanah.id_siasatan','$flag_skrin')" title="Memaparkan secara lengkap maklumat kehadiran"><font color="blue">MAKLUMAT KEHADIRAN</font></a></div></td>
</tr>


<tr>
<td colspan="3">
 <div align="left"><a href="javascript:maklumatsiasatan('$listTanah.id_siasatan','$flag_skrin')" title="Memaparkan secara lengkap nota siasatan"><font color="blue">NOTA SIASATAN </font></a></div></td>
</tr>
</table> 
               #else      
            
           <div align="left"><a href="javascript:UrusanSiasatanSingle('$listTanah.id_hakmilik','','$flag_skrin')" title="Memaparkan secara lengkap maklumat siasatan"><font color="blue">MAKLUMAT SIASATAN</font></a></div>
                
                #end    
                     
                     
                      </td>     
                		 
                        #end 
                        
                        
                        
                        #if($flag_skrin == "hakmilik_borangL")
                        <td class="$rowx">
                		#if($isEdit=="yes")
                   		#set($dateName = "txdTarikhBorangL"+$!listTanah.bil)
                   		<input name="$!dateName" id="$!dateName" size="11" type="text" value="$!listTanah.tarikh_borangl" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	  		<img src="../../img/calendar.gif" onclick="displayDatePicker('$!dateName',false,'dmy');">
            	  		#set($idBL = "id_borangl"+$!listTanah.bil)
            	  		<input type="hidden" name="$!idBL" value="$!listTanah.id_borangl">
            	  		<input type="hidden" name="id_hakmilik" value="$!listTanah.id_hakmilik">
            			#else
            			$!listTanah.tarikh_borangl
            			#end
            			</td>
                        <td class="$rowx">
                        #if($isEdit=="yes")
                        #set($txtTempohName = "txtTempoh"+$!listTanah.bil)
                        <input name="$!txtTempohName" type="text" id="$!txtTempohName" onBlur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value)" value="$!listTanah.tempoh" size="10" maxlength="10" e>
                        #else
            			$!listTanah.tempoh
            			#end
            			</td>
                        
                		<td class="$rowx">
                		#if($isEdit=="yes")
                		#set($statusBL = "sorStatusBorangL"+$!listTanah.bil)
                		<select name="$!statusBL" style="width:170px">
      						<option value="" #if($!listTanah.jenis_pilih=="") selected=selected #end >Sila Pilih</option>	
			      			<option value="1" #if($!listTanah.jenis_pilih=="1") selected=selected #end>Hakmilik Belum Diterima</option>
			      			<option value="2" #if($!listTanah.jenis_pilih=="2") selected=selected #end>Hakmilik Telah Diterima</option>	
			      		</select>      
			      		#else
			      		$!listTanah.status_borang_l
			      		#end     		
                		</td>  
                        <td class="$rowx">
                        #if($!listTanah.tarikh_borangl != "")
                        
                        <input type="button" name="cmdCetakBorangL" value ="Cetak" onClick="javascript:cetakBorangL($!listTanah.id_hakmilik,$!id_fail,$!id_permohonan,'$!listTanah.tarikh_borangl','$!listTanah.tempoh')">
                        #end                        
                        </td>
                        #end


            		</tr>
                    
              #set ($count_check = $count_check + 1)      
                    
                      #end
                      
          #else
          <tr>  
            <td colspan="8"><font color="red">TIADA REKOD</font></td>    
          </tr>
          
          #end            
          </table>
          </br>
          
      </fieldset>
      
      
<!-- PPT - 30(ii) -->
<!--
		<table width="100%" border="0">
			<tr align="center">
				<td>

				<input type="button" name="cmdCetak" value="Cetak" onClick="javascript:cetakHakmilik('$!id_permohonan');">
					
				</td>
			</tr>
		</table>   
		
      </fieldset>
      
      </td>
  </tr>
</table>
-->

 #if($flag_skrin == "senarai_siasatan" || $flag_skrin == "senarai_siasatan_sementara")
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
		
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
			  <tr>
		      	<td align="center"><input type="button" value="Kembali" onClick="screen5('$id_permohonan')"></td>
			  </tr>			 	  
		    </table>

		</td>
		</tr>
		</table>
		#end

<!-- PPT-30 (ii) -->
#if($flag_skrin == "hakmilik_borangk")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
		<fieldset>
		<legend><b>SENARAI SURAT</b></legend>  
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
			  <tr>
		      	<td><a href="#" onClick="javascript:cetakSuratEndorsanBorangK('$!id_permohonan','$!id_hakmilik')"><font color="blue">Surat Endorsan Borang K</font></a></td>
			  </tr>
			  <tr>
		      	<td><a href="#" onClick="javascript:cetakSuratIringanAgensiPemohon('$!id_permohonan','$!id_hakmilik')"><font color="blue">Surat Iringan Kepada Agensi Pemohon</font></a></td>
			  </tr>	  
		    </table>
		</fieldset>	
      </td>
  </tr>
</table>
 #end

#foreach ( $listTanah in $SenaraiFail )
#set($input_sj_js = $!listTanah.id_hakmilik + "txtSj")
#set($div_temp = $!listTanah.id_hakmilik + "div")
<script>
var f_if = '$input_sj_js';
var div_temp = '$div_temp';
var xxx = document.getElementById(f_if).value;
$jquery("#"+div_temp).html("<input type='text' id='getSJ' name='getSJ' value='"+xxx+"' >");	
</script>
#end



 
#if($refreshHakmilik == "yes")
<script>

		$jquery(document).ready(function()
		{
			refreshSkrinHakmilik('$id_permohonan');
		});

</script>
#end

<script>
function checkDuplicated(value,div_id,bil,div_alert)
{
	$jquery("#"+div_id).html("<input type='text' id='getSJ' name='getSJ' value='"+value+"' >");
	
	var arr2 = [];
	if (document.${formName}.getSJ.length == null){
		
     } else {
		 	arr2 = [];
            for (i = 0; i < document.${formName}.getSJ.length; i++){		
						//$jquery("#"+i+"alert").html("");
						//$jquery("#"+bil+"alert").html("");  
			arr2[arr2.length] = document.${formName}.getSJ[i].value;
                      
            }
     }
	//alert("arr2 : "+arr2);	
	var sorted_arr = arr2.sort(); 
	var results = [];
	for (var i = 0; i < arr2.length - 1; i++) {
		if (sorted_arr[i + 1] == sorted_arr[i]) {
			results.push(sorted_arr[i]);
		}
	}
	
	/*
	alert(results);
	
	
	if ($jquery.inArray( '1', results ) > -1 )	
	{
		alert("ade");
	}
	
	*/
	
	
	
	
	
	if (document.${formName}.getSJ.length == null)
	{
		
    } 
	else 
	{
            for (i = 0; i < document.${formName}.getSJ.length; i++)
			{
				//alert("val :"+document.${formName}.getSJ[i].value+", bil :"+bil+", i :"+i+" results :"+results);	
					var value_c = document.${formName}.getSJ[i].value;
					//alert("value_c :"+value_c+" , results : "+results);
					
					
					if($jquery.inArray(value_c, results) > -1 )
					{
						$jquery("#"+i+"alert").html("<font color = 'red' class='blink'><b>X</b></font>");
						//$jquery("#"+bil+"alert").html("<font color = 'red' class='blink'><b>X</b></font>");
					}
					else
					{
						
						$jquery("#"+i+"alert").html("");
					}
            }
    }	
}

//refreshSkrinHakmilik(id_permohonan,flag_skrin);

function paparByAgensi(id_hakmilik,status_bantahan_ap,id_permohonan,flag_skrin)
{
	try {
	window.opener.paparByAgensi(id_hakmilik,status_bantahan_ap,id_permohonan);
	}
	catch (err) {}
    window.close();
    return false;
}


function cetakBorangL(id_hakmilik,id_fail,id_permohonan,tarikhBorangL,tempohBL) {
	
	//alert("tarikhBorangL : "+tarikhBorangL);

	var url = "../${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_hakmilik="+id_hakmilik+"&id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&report=BorangL&selectNoFail=yes&tarikhBorangL="+tarikhBorangL+"&tempohBL="+tempohBL;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function kemasukanBorangF(id_hakmilik,id_permohonan,flag_skrin,id_borange)
{
	try {
	window.opener.showBorangF(id_hakmilik);
	}
	catch (err) {}
    window.close();
    return false;
}

function tambahBG(id_hakmilik,flag_skrin)
{
	try {
	window.opener.viewInfoTanah(id_hakmilik);
	}
	catch (err) {}
    window.close();
    return false;
}


function refreshSkrinHakmilik(id_permohonan,flag_skrin)
{
	window.opener.refreshHakmilik(id_permohonan);
}


function UrusanSiasatanSingle(id_hakmilik,id_pembatalan,flag_skrin)
{
   try {
	    if(flag_skrin=="senarai_siasatan")
		{	    
        window.opener.UrusanSiasatanSingle(id_hakmilik,'');	
		}
		else if(flag_skrin=="senarai_siasatan_sementara")
		{
		
		window.opener.UrusanSiasatan(id_hakmilik,'');	
		}
			
	}
	catch (err) {}
   	window.close();	
    return false;

}


function papar(id_siasatan,id_hakmilik,flag_skrin)
{
	try {	    
        window.opener.papar(id_siasatan,id_hakmilik);		
	}
	catch (err) {}
   	window.close();	
    return false;
	
}


function kehadiran(id_siasatan,flag_skrin)
{

	try {	    
        window.opener.kehadiran(id_siasatan);		
	}
	catch (err) {}
   	window.close();	
    return false;
	
}


function maklumatsiasatan(id_siasatan,flag_skrin)
{
	try {	    
        window.opener.maklumatsiasatan(id_siasatan,flag_skrin);	
	}
	catch (err) {}
   	window.close();	
    return false;
	

}




function simpanBorangL(id_permohonan,flag_skrin) {
	
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "simpanBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianHakmilik?id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	document.${formName}.submit();
	/*
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.command2.value = "kemaskiniBorangL";
	document.${formName}.command3.value = "simpanBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangL";
	document.${formName}.submit();*/
}
function batalKemaskini(id_permohonan,flag_skrin) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "batalKemaskiniL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianHakmilik?id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	document.${formName}.submit();
	/*
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangL";
	document.${formName}.submit();
	*/
}
function kemaskiniBorangL(id_permohonan,flag_skrin) {
	document.${formName}.command.value = "kemaskiniBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianHakmilik?id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	document.${formName}.submit();
	/*
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.command2.value = "kemaskiniBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangL";
	document.${formName}.submit();
	*/
}


function simpanSj(id_permohonan,flag_skrin) {	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "simpanSj";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianHakmilik?id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	document.${formName}.submit();
}

function janaSubjaketManual(id_permohonan,flag_skrin){	
	document.${formName}.command.value = "janaSubjaketManual";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianHakmilik?id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	document.${formName}.submit();
}

function janaSubjaket(id_permohonan,flag_skrin){

	if ( !window.confirm("Adakah Anda Pasti? Sebarang penambahan hakmilik selepas ini memerlukan subjaket dikesemua hakmilik dijana semula") ) return;
	document.${formName}.command.value = "janaSubjaket";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianHakmilik?id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	document.${formName}.submit();
}

if('$tutup_skrin_popup' == "yes")
{
	kembaliKeSkrinUtama('$id_permohonan');
}




function carian() 
 {	
	document.${formName}.command.value = "cari";
	document.${formName}.submit();			
				
 }	
 
 function kosongkan() 
 {
	document.${formName}.NO_LOT.value = "";	
	document.${formName}.NO_PB.value = "";
	document.${formName}.NAMA_PB.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();							
 }	

function maklumatBorangF(id_borange){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "maklumatBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}

/*
function kemasukanBorangF(id_hakmilik,id_permohonan,flag_skrin,id_borange)
{
	
	try {
	var url = "../c/${securityToken}/ekptg.view.ppt.FrmUPTSek8BorangF?id_borange="+id_borange+"&command=maklumatBorangF&ScreenLocation=top";
	var hWnd = window.open(url,'Senarai Lot','width=800,height=400, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
		
		}
	catch (err) {}
   	window.close();	
    return false;
		
	
}
*/


function paparHakmilik(id_hakmilik,id_permohonan,flag_skrin){
	try {
		if(flag_skrin=="daftar_sek8")	{
			window.opener.viewHM(id_hakmilik);
		}	
		else if(flag_skrin=="daftar_sek4")	{
			window.opener.viewMaklumat(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_sek8")	{
			window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_sek4")	{
			window.opener.viewMaklumat(id_hakmilik);
		}
		else if(flag_skrin=="skrin_list_hakmilik_pb_sek8")	{
			window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_pb_sek8")	{
			window.opener.viewHM(id_hakmilik);	
		}
		else if(flag_skrin=="laporan_tanah_terperinci")	{
			//alert(id_hakmilik);
			window.opener.viewHM(id_hakmilik);	
		}
		else if(flag_skrin=="bukti_notis")	{
			window.opener.viewHM(id_hakmilik);	
		}
		//alert(id_hakmilik);
		else if(flag_skrin=="skrin_hakmilik_sek8_KJP")	{
			window.opener.tambahPB(id_hakmilik);	
		}
		/* else if(flag_skrin=="daftar_sek8_online")	{
			window.opener.tambahPB(id_hakmilik);	
		} */
		/*
		else if(flag_skrin=="laporan_bangunan")	{    
        
        var url = "../${securityToken}/ekptg.view.ppt.SkrinPopupBangunan?&id_permohonan="+id_permohonan+"&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
        var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
           hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();
        hWnd.focus();
		
		}
        */
		else if(flag_skrin=="laporan_bangunan")	{    
        	window.opener.viewHM(id_hakmilik);	
		}
		else if(flag_skrin=="hakmilik_pampasan")	{    
        	window.opener.viewHM(id_hakmilik);	
		}
		else if(flag_skrin=="hakmilik_borangk")	{    
        	window.opener.viewHM(id_hakmilik);	
		}
		else if(flag_skrin=="bukti_notis_borangK")	{    
        	window.opener.viewHM(id_hakmilik);	
		}
		else if(flag_skrin=="senarai_pu")	{    
        	window.opener.viewHM(id_hakmilik);	
		}
		else if(flag_skrin=="daftar_sementara")	{    
        	window.opener.viewHM(id_hakmilik);	
		}
		else if(flag_skrin=="skrin_hakmilik_sementara")	{
			window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="laporan_bangunan_sementara")	{    
        	window.opener.viewInfoTanah(id_hakmilik);	
		}
		else if(flag_skrin=="laporan_tanah_terperinci_sementara")	{
			window.opener.maklumatTanah(id_hakmilik);	
		}
		else if(flag_skrin=="notis_awam_sementara")	{
			window.opener.tambahNotis(id_hakmilik);	
		}
		else if(flag_skrin=="bukti_notis_sementara")	{
			window.opener.viewListPenyampaianNotis(id_hakmilik);	
		}
		else if(flag_skrin=="skrin_jpph_sementara")	{
			window.opener.viewMaklumat(id_hakmilik);	
		}
		else if(flag_skrin=="penandaan_sementara")	{
			window.opener.penandaanKawasan(id_hakmilik);	
		}
		else if(flag_skrin=="senarai_pampasan_sementara")	{
			window.opener.viewSenaraiPampasanPB(id_hakmilik);	
		}
		else if(flag_skrin=="daftar_sek8_online")	{
			window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_sek8_KJP")	{
			window.opener.viewHM(id_hakmilik);
		}
	}
	catch (err) {}
   	window.close();	
    return false;
}


function tambahPbPopup(id_hakmilik,flag_skrin)	{
	alert(id_hakmilik);
	try {
		if(flag_skrin=="skrin_list_hakmilik_pb_sek8" || flag_skrin=="skrin_hakmilik_pb_sek8" || flag_skrin == "daftar_sek8_online" || flag_skrin == "skrin_hakmilik_sek8_KJP")	{
			//alert('masuk1==='+id_hakmilik);
			window.opener.tambahPB(id_hakmilik);
			//alert('masuk2==='+id_hakmilik);
		}	else if(flag_skrin=="skrin_hakmilik_sementara" || flag_skrin == "skrin_hakmilik_sek8_KJP" || flag_skrin == "daftar_sek8_online")	{
			//alert('masuk3==='+id_hakmilik);
			window.opener.tambahPB(id_hakmilik);
		}
		
		/* else if(flag_skrin=="senarai_pampasan_sementara")	{
			window.opener.viewSenaraiPampasanPB(id_hakmilik);	
		}
		else if(flag_skrin=="daftar_sek8_online")	{
			window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_sek8_KJP")	{
			window.opener.viewHM(id_hakmilik);
		} */
		
		
	}
	catch (err) {}
    window.close();
    return false;
}


function kembaliKeSkrinUtama(id_permohonan) {
	
	try {
		//simpanDisemak(ID_PLA);
        window.opener.HandlePopup_from_copy_hakmilik(id_permohonan);		
    }
    catch (err) {}
    window.close();
    return false;
	document.${formName}.cmdKembaliSkrinUtama.value = "Sila Tunggu....";		
}


//PPT-30(ii)
function checkALL() {

	 if (document.${formName}.checkall.checked == true){
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = true;
	            }
	        }
	    } else {
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = false;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = false;	                
	            }
	            
	        }
	    }
}

//pilihan cetak surat hakmilik belum ada 260220
function cetakSuratIringan(id_hakmilik,id_fail,id_permohonan,tarikhBorangL,tempohBL) {
	
	//alert("tarikhBorangL : "+tarikhBorangL);

	var url = "../${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_hakmilik="+id_hakmilik+"&id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&report=BorangL&selectNoFail=yes&tarikhBorangL="+tarikhBorangL+"&tempohBL="+tempohBL;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function cetakSuratEndorsanBorangK(idpermohonan, idhakmilik) {
	
	var checkSelected = false;
	if(document.${formName}.cbsemaks.length > 1){
		for(var i = 0 ; i < document.${formName}.cbsemaks.length; i++)	{ 
    		if (document.${formName}.cbsemaks[i].checked)  	{
  				checkSelected=true;
  			}
		}
	}	else	{
		if (document.${formName}.cbsemaks.checked)	{
			checkSelected=true;
    	}
	}
	
	 if(!checkSelected){
		alert("Sila semak pada \"Kotak Semakan\" bagi mencetak Surat Endorsan Borang K.");
		return;
		
	}	else	{

	var selected = new Array();
	var inputElements = document.getElementsByClassName("idHakmilik"); // ClassName from cbsemaks
	for(var i = 0; i < inputElements.length; i++) {
		if (inputElements[i].checked)	{
			selected.push(inputElements[i].value);
		}
	}
	
	if (selected.length > 0) {
		idhakmilik = selected.join(",");
		// alert(idhakmilik);
	}
	
	 var url = "../${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratEndorsanBorangK1";
	// var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E"; // takpakai
     var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
     if ((document.window != null) && (!hWnd.opener))
	 Wnd.opener = document.window;
     if (hWnd.focus != null) hWnd.focus();
    
    }
}


function cetakSuratIringanAgensiPemohon(idpermohonan,idhakmilik) {
	
	var checkSelected = false;
	if(document.${formName}.cbsemaks.length > 1){
		for(var i = 0 ; i < document.${formName}.cbsemaks.length; i++)	{ 
    		if (document.${formName}.cbsemaks[i].checked)  	{
  				checkSelected=true;
  			}
		}
	}	else	{
		if (document.${formName}.cbsemaks.checked)	{
			checkSelected=true;
    	}
	}
	
	 if(!checkSelected){
		alert("Sila semak pada \"Kotak Semakan\" bagi mencetak Surat Iringan Agensi Pemohon.");
		return;
		
	}	else	{

	var selected = new Array();
	var inputElements = document.getElementsByClassName("idHakmilik"); // ClassName from cbsemaks
	for(var i = 0; i < inputElements.length; i++) {
		if (inputElements[i].checked)	{
			selected.push(inputElements[i].value);
		}
	}
	
	if (selected.length > 0) {
		idhakmilik = selected.join(",");
		// alert(idhakmilik);
	}
	
	var url = "../${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratIringanAP&selectNoFail=no";
	//var url = "../servlet/ekptg.report.ppt.BuktiPenyampaian?idHakmilik="+idhakmilik+"&flagJenisSuratCara=E";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
    }
}
function cetakBorangK(idpermohonan,idhakmilik) {

	var url = "../${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=BorangK&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BorangK?id_hakmilik="+idhakmilik+"&id_Fail="+idfail+"&namaPegawai="+namaPengarah;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
//PPT-30(ii)
function tambahWakil()
{

	
	document.${formName}.command.value = "tambahPB";	
	document.${formName}.subminor_command.value = "tambah_wakil";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.submit();
	
}

function screen5(id_permohonan)
{

	  window.close();   // Closes the new window
}


</script>

