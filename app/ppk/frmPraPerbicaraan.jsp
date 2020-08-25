<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style> 
<!-- 
list_fail = $list_fail  -->
#foreach($list in $list_fail)
	#set($txtid_permohonansimati = $list.ID_PERMOHONANSIMATI)
	#set($txtid_simati = $list.ID_SIMATI)
#end
#if($edit=="no")
	#set($disability="readonly")
	#set($disability1="disabled")
	#set($disabilityCSS="class=disabled")
#else
	#set($disability="")
	#set($disability1="")
	#set($disabilityCSS="")
#end
#set($keputusan_praperbicaraan="")
#set($catatan_keputusan="")
#foreach($listPra1 in $listPra)
    #set($sebab_inkuiri=$listPra1.sebab_inkuiri)
    #set($catatan_notis=$listPra1.catatan_notis)
    #set($catatan_sebabinkuiri=$listPra1.catatan_sebabinkuiri)
    #set($tarikh_inkuiri=$listPra1.tarikh_inkuiri)
    #set($alamat1=$listPra1.alamat1)
    #set($alamat2=$listPra1.alamat2)
    #set($alamat3=$listPra1.alamat3)
    #set($poskod=$listPra1.poskod)
    #set($idnegeri=$listPra1.idnegeri)
    #set($id_praperbicaraan=$listPra1.id_praperbicaraan)
    #set($txtMasaBicara=$listPra1.masa_bicara)
    #set($jenisWaktu=$listPra1.jenis_masa_bicara)
    #set($keputusan_praperbicaraan=$listPra1.keputusan_praperbicaraan)
    #set($catatan_keputusan=$listPra1.catatan_keputusan)
    
#end

         
        
<!-- ::::::::::: $!total_harta -->
<br/>
<body onLoad="ResetScrollPosition();" >
<fieldset style="display:none"  ><legend><b>Semakan Status</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">No Fail : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$nofail" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdSemak" id="cmdSemak" value="Semak" type="button" onClick="javascript:search_data()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan()"></td>
    		</tr>
    		
		</table>
		
	



#if($listSemak_size!=0 && $listSemak_size!="")	
 
<br/>

	<fieldset>
	<legend><strong>Status Semasa</strong></legend>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
				<td><b>Status</b></td>
			</tr>

         	<tr>
            	<td>$keterangan_status</td>
        	</tr>	
      
			
		</table>
	</fieldset>

<br/>
	#if($id_status!=21 && $id_status!=64 && $id_status!=163 && $id_status!=169)
	<fieldset>
	<legend><strong>Pertukaran Status</strong></legend>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
      			<td scope="row" width="5%"><b>Bil</b></td>
      			<td width="95%"><b>Status</b></td>
    		</tr>
    		
    		#foreach($list in $listSemak)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
    		<tr>
      			<td class="$row">$list.bil</td>
      			<td class="$row"><a href="javascript:tukarstatus('$id_permohonan','$keterangan_status','$id_suburusanstatusfail','$id_fail','$list.level','$list.list_keterangan','$id_keputusanpermohonan','$id_perbicaraan','$id_perintah')"><font color="blue">$list.list_keterangan</font></a></td>
    		</tr>
    		#end
       
		</table>
	</fieldset>	
    #end
    
#end

#if ($!carianrekod == "tiada")
    <br/>

	<fieldset>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr>
				<td>No fail : <b>$!nofail</b> &nbsp;tiada dalam pangkalan data.</td>
			</tr>
		</table>
	</fieldset>
#end





</fieldset>
<br />

<fieldset><legend>

<b>
#parse("app/ppk/tag_baru.jsp")
&nbsp;
Semakan Permohonan</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">MyID Simati / No Fail : </td>
      			<td width="70%"><input name="txtNoFailSub" id="txtNoFailSub" type="text" value="$txtNoFailSub" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
              
                </td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdSemakSub" id="cmdSemakSub" value="Semak" type="button" onClick="javascript:search_main_data_sub()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan_sub()"></td>
    		</tr>
    		
		</table>
        #if($view_list_fail == "yes")
      
        <br>
        <fieldset>
        <table width="97%"  cellpadding="1" cellspacing="2" border="0">
<tr >
                <td class="table_header" width="5%"  style="display:none"><b></b></td>
      		    <td class="table_header" width="10%"  style="display:none"><b>ID FAIL</b></td>
                <td class="table_header" width="20%"><b>NO FAIL</b></td>
                <td class="table_header" width="20%"><b>NAMA SIMATI</b></td>
                <td class="table_header" width="20%"><b>NAMA PEMOHON</b></td>
                <td class="table_header" width="10%"><b>TARIKH MOHON</b></td>
                <td class="table_header" width="15%"><b>STATUS SEMASA</b></td>
                
                          
    		</tr>
    		<!-- listPra = $listPra -->
            #if($list_fail.size() > 0)
    		
    		#foreach($list in $list_fail)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
               
               
               #set($tr_id = "tr_id"+$i)
               
              <!-- onMouseOver="tr_id_up('$tr_id','$row')" onMouseOut="tr_id_out('$tr_id','$row')" -->
         
         #if($list.ID_FAIL  == $id_fail_carian)
         
         #set($id_permohonansimati = $list.ID_PERMOHONANSIMATI)
         #set($id_simati = $list.ID_SIMATI)
         #set($id_permohonan = $list.ID_PERMOHONAN)
         #set( $row = "tr_class" )
         #end
            <tr id="$tr_id" class="$row"    >
                <td   style="display:none" >  
         <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="white"> Papar  </font>
         #else
         <font color="blue"> Papar  </font>
         #end           
              </a>  
               
                </td>
      			
      		    <td  style="display:none"  >  
                  $list.ID_FAIL   
                </td>
                
                <td  > 
                 <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="blue"> $list.NO_FAIL    </font>
         #else
         <font color="blue"> $list.NO_FAIL    </font>
         #end           
              </a>  
                
                </td>
                
                <td  >
               $list.NAMA_SIMATI 
                </td>
                
                <td >
                $list.NAMA_PEMOHON 
                </td>
                
                <td >
               $list.TARIKH_MOHON
                </td>
                
                 <td >
               $list.NAMA_STATUS
                </td>
                          
    		</tr>
         
            #end
            #else
            
            <tr class="row">
      			
      		    <td class="row" colspan="10" > 
                Tiada Rekod              
                </td>
               
                          
    		</tr>
            
            #end
            </table>
        </fieldset>
      #end  
        

#if($!cari_sub == "yes")
 
     
     #if($!headerppk.size()>0) 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr >    
    <td >  
    #parse("app/ppk/headerppk.jsp")
</td>
</tr>
</table>
     #end
     

<br>  
<fieldset>
<legend ><b>NOTIS PRA-PERBICARAAN</b></legend>

<table width="97%"  cellpadding="1" cellspacing="2" border="0" >
 <tr>
              <td valign="top"><font color="red">*</font>Tarikh Inkuiri</td>
              <td>:</td>
              <td><input type="text" name="txdTarikhInkuiri"  $disability $disabilityCSS id="txdTarikhInkuiri" value="$!tarikh_inkuiri" size="11" maxlength="10" onblur="check_date(this);getTarikhNotis();validateTarikh(this,this.value);checkCutiAm(this.value);"  />
                    <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhInkuiri',false,'dmy');">&nbsp;<i><font color="blue" style="font-size:10px">dd/mm/yyyy</font></i>
                    <input type="hidden" name="id_praperbicaraan" id="id_praperbicaraan" value="$!id_praperbicaraan" />
                    </td>
            </tr>
            
 <tr>
              <td valign="top"><font color="red">*</font>Masa Bicara</td>
              <td>:</td>
              <td><input type="text" name="txtMasaBicara"  $disability $disabilityCSS id="txtMasaBicara" value="$!txtMasaBicara" onkeyup="validateNumber(this,this.value);" onblur="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
                    
                    <!-- &nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font> -->
                    
                 <select name="socJenisWaktu" id="socJenisWaktu" $disability1 $disabilityCSS style="width:auto" >
                 	#if($jenisWaktu=="")
                 		#set($_jenisWaktu="")
                 	#else
                 		#set($_jenisWaktu=$jenisWaktu)
                 	#end
                 	
                 	#if($_jenisWaktu=="1")
	                 <option value="1">PAGI</option>
	                 <option value="0">SILA PILIH</option>
	                 <option value="2">TENGAHARI</option>
	                 <option value="3">PETANG</option>
                	#elseif($_jenisWaktu=="2")
	                 <option value="2">TENGAHARI</option>
	                 <option value="0">SILA PILIH</option>
	                 <option value="1">PAGI</option>
	                 <option value="3">PETANG</option>
                 	#elseif($_jenisWaktu=="3")
	                 <option value="3">PETANG</option>
	                 <option value="0">SILA PILIH</option>
	                 <option value="1">PAGI</option>
	                 <option value="2">TENGAHARI</option>
                 	#else
	                 <option value="0">SILA PILIH</option>
	                 <option value="1">PAGI</option>
	                 <option value="2">TENGAHARI</option>
	                 <option value="3">PETANG</option>
                 	#end	
                </select>
                    &nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)&nbsp;(Masa bicara 0830 pagi hingga 0430 petang)</i></font></td>
            </tr>
 
 <tr>
 <td ><font color="red">*</font>Sebab </td>
 <td>:</td>
 #if($edit != "no" && $proses != "kemaskini")
 <td>
 <select name="socSebab" id="socSebab" $disability1 onchange="checkSebabLain()">
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option>
 <option value="1" style="text-transform:uppercase;">Perlantikan Sahabat/Penjaga</option>
 <option value="2" style="text-transform:uppercase;">Bukti Kematian Yang Diragui</option>
 <option value="3" style="text-transform:uppercase;">Perbezaan Nama Simati Dalam Bukti Kematian</option>
 <option value="4" style="text-transform:uppercase;">Keperluan Mendapatkan Maklumat Tambahan</option>
 <option value="5" style="text-transform:uppercase;">Sebab-sebab Lain</option>
 </select>
 
 </td>
 #else
 #if($sebab_inkuiri == "1" && $proses != "kemaskini")
 <td>
 <select name="socSebab" id="socSebab"  $disability1 onchange="checkSebabLain()">
 <option value="1" style="text-transform:uppercase;">Perlantikan Sahabat/Penjaga</option>
 </select>
 </td>
 #end
 
 #if($sebab_inkuiri == "2" && $proses != "kemaskini")
 <td>
 <select name="socSebab" id="socSebab"  $disability1 onchange="checkSebabLain()">
 <option value="2" style="text-transform:uppercase;">Bukti Kematian Yang Diragui</option>
 </select>
 </td>
 #end
 
 #if($sebab_inkuiri == "3" && $proses != "kemaskini")
 <td>
 <select name="socSebab" id="socSebab"  $disability1 onchange="checkSebabLain()">
 <option value="3" style="text-transform:uppercase;">Perbezaan Nama Simati Dalam Bukti Kematian</option>
 </select>
 </td>
 #end
 
 #if($sebab_inkuiri == "4" && $proses != "kemaskini")
 <td>
 <select name="socSebab" id="socSebab"  $disability1 onchange="checkSebabLain()">
 <option value="4" style="text-transform:uppercase;">Keperluan Mendapatkan Maklumat Tambahan</option>
 </select>
 </td>
 #end
 
  #if($sebab_inkuiri == "5" && $proses != "kemaskini")
 <td>
 <select name="socSebab" id="socSebab"  $disability1 onchange="checkSebabLain()">
 <option value="5" style="text-transform:uppercase;">Sebab-sebab Lain</option>
 </select>
 </td>
 #end
 #end
 
 #if($edit == "yes" && $sebab_inkuiri == "1")
 <td>
 <select name="socSebab" id="socSebab" $disability1 onchange="checkSebabLain()">
 <option value="1" style="text-transform:uppercase;">Perlantikan Sahabat/Penjaga</option>
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option> 
 <option value="2" style="text-transform:uppercase;">Bukti Kematian Yang Diragui</option>
 <option value="3" style="text-transform:uppercase;">Perbezaan Nama Simati Dalam Bukti Kematian</option>
 <option value="4" style="text-transform:uppercase;">Keperluan Mendapatkan Maklumat Tambahan</option>
 <option value="5" style="text-transform:uppercase;">Sebab-sebab Lain</option>
 </select>
 
 </td>
 #end
 #if($edit == "yes" && $sebab_inkuiri == "2")
 <td>
 <select name="socSebab" id="socSebab" $disability1 onchange="checkSebabLain()">
 <option value="2" style="text-transform:uppercase;">Bukti Kematian Yang Diragui</option>
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option> 
 <option value="1" style="text-transform:uppercase;">Perlantikan Sahabat/Penjaga</option>
 <option value="3" style="text-transform:uppercase;">Perbezaan Nama Simati Dalam Bukti Kematian</option>
 <option value="4" style="text-transform:uppercase;">Keperluan Mendapatkan Maklumat Tambahan</option>
 <option value="5" style="text-transform:uppercase;">Sebab-sebab Lain</option>
 </select>
 
 </td>
 
#end
 #if($edit == "yes" && $sebab_inkuiri == "3")
 <td>
 <select name="socSebab" id="socSebab" $disability1 onchange="checkSebabLain()">
 <option value="3" style="text-transform:uppercase;">Perbezaan Nama Simati Dalam Bukti Kematian</option> 
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option> 
 <option value="1" style="text-transform:uppercase;">Perlantikan Sahabat/Penjaga</option>
 <option value="2" style="text-transform:uppercase;">Bukti Kematian Yang Diragui</option>
 <option value="4" style="text-transform:uppercase;">Keperluan Mendapatkan Maklumat Tambahan</option>
 <option value="5" style="text-transform:uppercase;">Sebab-sebab Lain</option>
 </select>
 
 </td>
 
 #end
 #if($edit == "yes" && $sebab_inkuiri == "4")
 <td>
 <select name="socSebab" id="socSebab" $disability1 onchange="checkSebabLain()">
 <option value="4" style="text-transform:uppercase;">Keperluan Mendapatkan Maklumat Tambahan</option> 
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option> 
 <option value="1" style="text-transform:uppercase;">Perlantikan Sahabat/Penjaga</option>
 <option value="2" style="text-transform:uppercase;">Bukti Kematian Yang Diragui</option>
 <option value="3" style="text-transform:uppercase;">Perbezaan Nama Simati Dalam Bukti Kematian</option> 
 <option value="5" style="text-transform:uppercase;">Sebab-sebab Lain</option>
 </select>
 
 </td>
 
  #end
 #if($edit == "yes" && $sebab_inkuiri == "5")
 <td>
 <select name="socSebab" id="socSebab" $disability1 onchange="checkSebabLain()">
 <option value="4" style="text-transform:uppercase;">Keperluan Mendapatkan Maklumat Tambahan</option> 
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option> 
 <option value="1" style="text-transform:uppercase;">Perlantikan Sahabat/Penjaga</option>
 <option value="2" style="text-transform:uppercase;">Bukti Kematian Yang Diragui</option>
 <option value="3" style="text-transform:uppercase;">Perbezaan Nama Simati Dalam Bukti Kematian</option> 
 <option value="5" style="text-transform:uppercase;">Sebab-sebab Lain</option>
 </select>
 
 </td>
 #end
 
 </tr>
 
 <tr>
 <td></td>
 <td></td>
 <td>
 <div id="divSebabsebabLain"  style="display:none"><input name="txtSebabsebabLain" placeholder="Masukkan sebab-sebab lain" type="text" id="$!catatan_sebabinkuiri"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" size="50" value="" /></div></td>
 </tr>
 
 <!-- <tr>
             <td><font color="red">*</font>Tempat Bicara</td>
             <td>:</td>
             <td>
                   <input type="radio" id="pKptg" $checkP1 checked name="jenisPejabat" value="1" onclick="onchangeJenisPejabat()" >
                   Cawangan JKPTG
                   &nbsp;&nbsp;
                   <input type="radio" $checkP2 id="pTanah" name="jenisPejabat" value="2" onclick="onchangeJenisPejabat()" >
                   Pejabat Tanah
                   &nbsp;&nbsp;
                   <input type="radio" $checkP3 id="pLain" name="jenisPejabat" value="0" onclick="onchangeJenisPejabat()" >
                   Lain - lain tempat</td>
</tr>

<tr>
<td>&nbsp;</td>
<td>:</td>
<td>$selectBicara</td>
</tr> -->

<tr>
<td valign="top"><font color="red">*</font>Alamat</td>
<td>:</td>
<td><input $!addressReadonly $!addressReadonlyClass  $disability $disabilityCSS type="text" size="52" name="txtAlamatBicara1" id="txtAlamatBicara1" value="$!alamat1" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
</tr>

<tr>
<td>&nbsp;</td>
<td>:</td>
<td><input $!addressReadonly $!addressReadonlyClass $disability $disabilityCSS type="text"  size="52" name="txtAlamatBicara2" id="txtAlamatBicara2" value="$!alamat2" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
</tr>
<tr>
<td>&nbsp;</td>
<td>:</td>
<td><input type="text" $!addressReadonlyClass $!addressReadonly $disability $disabilityCSS size="52" name="txtAlamatBicara3" id="txtAlamatBicara3" value="$!alamat3" maxlength="80"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
</tr>

<tr>
<td valign="top"><font color="red">*</font>Poskod</td>
<td>:</td>
<td><input type="text" name="txtPoskod" id="txtPoskod"  $disability $disabilityCSS $!addressReadonlyClass $!addressReadonly onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" maxlength="5" size="5" value="$!poskod" /></td>
</tr>
                
<tr>
<td valign="top"><font color="red">*</font>Negeri</td>
<td>:</td>
<td>
<select name="socNegeriBicara" class="autoselect" id="socNegeriBicara" $disability1>
<option value="" style="text-transform:uppercase;" onblur="uppercase()">SILA PILIH</option>
#if($idnegeri == "1")
	<option value="1" selected>JOHOR</option>
#else
	<option value="1" >JOHOR</option>
#end
#if($idnegeri == "2")
	<option value="2" selected>KEDAH</option>
#else
	<option value="2" >KEDAH</option>
#end
#if($idnegeri == "3")
	<option value="3" selected>KELANTAN</option>
#else
	<option value="3" >KELANTAN</option>
#end
#if($idnegeri == "4")
	<option value="4" selected>MELAKA</option>
#else
	<option value="4" >MELAKA</option>
#end
#if($idnegeri == "5")
	<option value="5" selected>NEGERI SEMBILAN</option>
#else
	<option value="5" >NEGERI SEMBILAN</option>
#end
#if($idnegeri == "6")
	<option value="6" selected>PAHANG</option>
#else
	<option value="6" >PAHANG</option>
#end
#if($idnegeri == "7")
	<option value="7" selected>PULAU PINANG</option>
#else
	<option value="7">PULAU PINANG</option>
#end
#if($idnegeri == "8")
	<option value="8" selected>PERAK</option>
#else
	<option value="8">PERAK</option>
#end
#if($idnegeri == "9")
	<option value="9" selected>PERLIS</option>
#else
	<option value="9">PERLIS</option>
#end
#if($idnegeri == "10")
	<option value="10" selected>SELANGOR</option>
#else
	<option value="10">SELANGOR</option>
#end
#if($idnegeri == "12")
	<option value="12" selected>SABAH</option>
#else
	<option value="12">SABAH</option>
#end
#if($idnegeri == "13")
	<option value="13" selected>SARAWAK</option>
#else
	<option value="13">SARAWAK</option>
#end	
#if($idnegeri == "14")
	<option value="14">WILAYAH PERSEKUTUAN KUALA LUMPUR</option>
#else
	<option value="14">WILAYAH PERSEKUTUAN KUALA LUMPUR</option>
#end
#if($idnegeri == "15")
	<option value="15" selected>WILAYAH PERSEKUTUAN LABUAN</option>
#else
	<option value="15" >WILAYAH PERSEKUTUAN LABUAN</option>
#end
#if($idnegeri == "16")
	<option value="16" selected>WILAYAH PERSEKUTUAN PUTRAJAYA</option>
#else
	<option value="16">WILAYAH PERSEKUTUAN PUTRAJAYA</option>
#end
</select>


</td>
</tr>
                
            
 <tr>
 <td>Catatan</td>
 <td>:</td>
 <td><textarea name="txtCatatan" cols="60%"  $disability $disabilityCSS rows="7" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" >$!catatan_notis</textarea></td>
 </tr>
 </table>
 
<!--  <table width="97%"  cellpadding="1" cellspacing="2" border="0" >
 <tr>
 <td colspan="3">
 <font color="blue">
 <i>Ruanganan ini bertujuan untuk <b>Pegawai</b> membatalkan<strong> Perintah </strong>semasa untuk dibicarakan semula (Arahan Dari Mahkamah Tinggi).</i><br>
 <i>Setelah Fail ini dibatalkan, pengguna boleh membicarakan semula <strong>Perintah</strong> ini bermula daripada status 'Notis Perbicaraan'.</i>
 </font>
 </td>
 </tr>
 <tr>
 <td colspan="3">
 
 <i>
 <font color="red">
 Perhatian : </font> 
 Apabila fail ini <b>dibatalkan dan dibicarakan semula,</b> maklumat pada keseluruhan fail ini masih disimpan sebagai 'history'.</i>
<br><br>
 </td>
 </tr>
 <tr>
      			<td scope="row" width="28%">No. Fail</td>
                <td width="1%">:              
                </td>
      			<td width="67%">
             <font color="blue">$!NO_FAIL</font>
             
              <a href="javascript:currentFail($!SEKSYEN,$!ID_PERMOHONAN,$!ID_STATUS)" ><font color="blue">$!NO_FAIL</font></a>
           
                    
                             
                         
                </td>
    		</tr>
             <tr>
      			<td scope="row">Status Semasa</td>
                <td >:              
                </td>
      			<td >
              <font color="blue">$!STATUS</font> 
                             
                         
                </td>
    		</tr>
 <tr>
      			<td scope="row" >Pengesahan Kebenaran Membatalkan Fail</td>
                <td >:              
                </td>
      			<td >
              
                     <input  type="checkbox" name="check_flag_batal"  id="check_flag_batal" $check onClick="check_flag()" >
                   
                 
             
                         
                </td>
    		</tr>
                 
            <tr  id="tr_nama" style="display:none" >
      			<td scope="row">Pengesahan Dibuat Oleh</td>
                <td>
                :                              
                </td>                
      			<td>               
               <font color="blue">$!user_name</font>                
                </td>
    		</tr>
            <tr  id="tr_catatan" style="display:none"  >
      			<td scope="row" valign="top">Catatan</td>
                <td valign="top">
                :                              
                </td>                
      			<td  valign="top">
                
             <textarea name="catatan_batal" id="catatan_batal" cols="50"   rows="4"  placeholder="Sila Masukkan Catatan..."         
         onBlur="check_length(this,'4000','catatan_batal_check','catatan_batal_num','normal','yes','keterangan');"  
         onKeyup="check_length(this,'4000','catatan_batal_check','catatan_batal_num','normal','yes','keterangan');" 
         onKeydown="check_length(this,'4000','catatan_batal_check','catatan_batal_num','normal','yes','keterangan');"                    
           >$catatan_batal</textarea>
           
                 
         <div><span id="catatan_batal_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
        
         <div id="catatan_batal_check" class="alert_msg" ></div> 
         </td>
    		 </tr>
            
            
          
            <tr>
             <td >
             </td>
 <td colspan="2">

 <input name="cmdSimpan" id="cmdSimpan" value="Batalkan Fail Untuk Perbicaraan Semula" type="button" onClick="javascript:cmdSimpan_flag()">

 
 </td>
 </tr>    		
		</table> -->
        
        
        
        
</fieldset>

           <fieldset>
          <legend><strong>SENARAI ORANG BERKEPENTINGAN </strong></legend>
          #if ($listOB_size > 10)
          <div style="width:100%;height:187;overflow:auto"> #end
                <table width="100%"  cellpadding="1" cellspacing="2" border="0">

              <tr class="table_header">
                    <td align="center" width="4%"><b>No</b></td>
                    <!-- 
                    #if ($listOB_size != '0')
                    <td width="1%"><input type="checkbox" name="allOB" onClick="doCheckAllOB()"></td>
                    #end
                     -->
                    <td width="45%"><b>Nama Orang Berkepentingan</b></td>
                    <td width="10%"><b>Umur</b></td>
                    <td width="15%"><b>Status OB</b></td>
                    <td width="20%"><b>Taraf Kepentingan</b></td>
                    <!-- 
                    <td width="10%" align="center"><b>Serahan Tangan / Pos</b></td>
					<td width="10%" align="center"><b>Emel</b></td>
					<td width="10%" align="center"><b>PNMB</b></td>
					 -->
                    
                  </tr>
		              #if($listOB_size!=0)
		              #foreach($ob in $listOB)
		              #set($id_ob=$ob.id_ob)
		              #set($nama_ob=$ob.nama_ob)
		              #set( $i = $velocityCount )
		              #if ( ($i % 2) != 1 )
		              #set( $row = "row2" )
		              #else
		              #set( $row = "row1" )
		              #end
		              <tr>
                    <td class="$row" align="center" width="4%">$ob.bil</td>
                    <!-- 
                    #if($ob.age < 18 || $ob.status_ob == 4)
                    #if ($listOB_size != '0')
                    <td>&nbsp;</td>
                    #end
                    <td class="$row"><a href="javascript:maklumatPenjagaAdd('$id_ob','$Utils.escapeJavaScript($nama_ob)')"><font color="blue">$ob.nama_ob.toUpperCase()</font></a></td>
                    #else
                    #if ($listOB_size != '0')
                    <td class="$row"><input type="checkbox" value="$ob.id_ob" name="idsOB" onClick="doUpdateCheckAllOB()"/></td>
                    #end
                     -->
                    <td class="$row">
                    $ob.nama_ob.toUpperCase()
                    <input type=hidden name=idob_hidden id=idob_hidden value="$ob.id_ob" size=20 />
                    
                    </td>
                    <!-- #end -->
                    <td class="$row">$ob.age</td>
                    <td class="$row">$ob.keteranganOB</td>
                    <td class="$row">$ob.keterangan</td>
                    <!-- 
                     #if($ob.age < 18 || $ob.status_ob == 4)
  					  <td colspan=3 class="$row" >&nbsp;</td>
                   
                  	#else
                  	 <td class="$row" align="center"><input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="1" onClick="doCheckAllOBserahTgn($ob.bil,'1')" ></td>
					
					#if($ob.emel != '')
					
					<td class="$row" align="center"><input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="3" onClick="doCheckAllOBserahTgn($ob.bil,'3')" ></td>
					#else
					 <td class="$row" >&nbsp;</td>
					#end
					<td class="$row" align="center"><input type="radio" name="radioJenisSerah$ob.id_ob" id="radioJenisSerah$ob.id_ob" value="5" onClick="doCheckAllOBserahTgn($ob.bil,'5')" disabled></td>
                  	#end
                   -->
                  </tr>
              #end
              #else
              <tr>
                    <td colspan="4">Tiada Rekod</td>
                  </tr>
              #end
            </table>
                #if ($listOB_size > 10) </div>
          #end
        </fieldset>



<table width="100%">
<tr>
<td align="center">

#if($buttonKemaskini=="yes")
	<input name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" type="button" onClick="javascript:kemaskini()">
#end
#if($buttonSimpan=="yes")
	<input name="cmdSimpan" id="cmdSimpan" value="Simpan" type="button" onClick="javascript:simpan()">
#end
</td></tr>
</table>

<fieldset><legend><b>KEPUTUSAN PRA-PERBICARAAN</b></legend>
<table width="97%"  cellpadding="1" cellspacing="2" border="0" >
<tr>
<td>Keputusan</td>
<td>:</td>
<td> 
#if($keputusan_praperbicaraan == "1")
<select name="socKeputusan" id="socKeputusan" onchange="socKeputusan2()">
 <option value="1" style="text-transform:uppercase;">Summary Distribution</option>
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option>
 <option value="2" style="text-transform:uppercase;">Perbicaraan Penuh</option>
 <option value="3" style="text-transform:uppercase;">Pendete Lite</option>

 </select>
#end
#if($keputusan_praperbicaraan == "2")
<select name="socKeputusan" id="socKeputusan" onchange="socKeputusan2()">
 <option value="2" style="text-transform:uppercase;">Perbicaraan Penuh</option>
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option>
 <option value="1" style="text-transform:uppercase;">Summary Distribution</option>
 <option value="3" style="text-transform:uppercase;">Pendete Lite</option>

 </select>
#end
#if($keputusan_praperbicaraan == "3")
<select name="socKeputusan" id="socKeputusan" onchange="socKeputusan2()">
 <option value="3" style="text-transform:uppercase;">Pendete Lite</option>
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option>
 <option value="1" style="text-transform:uppercase;">Summary Distribution</option>
 <option value="2" style="text-transform:uppercase;">Perbicaraan Penuh</option>
 

 </select>
#end
#if($keputusan_praperbicaraan == "")
<select name="socKeputusan" id="socKeputusan" onchange="socKeputusan2()">
 <option value=""  style="text-transform:uppercase;">Sila Pilih</option>
 <option value="1" style="text-transform:uppercase;">Summary Distribution</option>
 <option value="2" style="text-transform:uppercase;">Perbicaraan Penuh</option>
 <option value="3" style="text-transform:uppercase;">Pendete Lite</option>

 </select>
#end
 </td>

</tr>
<tr>
<td></td>
<td></td>
<td><div id="divSummaryDistribution"  style="display:none"><textarea name="txtSummaryDistribution" placeholder="Masukkan keterangan Summary Distribution" id="txtSummaryDistribution"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" cols="60%" rows="7" >$!catatan_keputusan</textarea></div></td>
</tr>

<tr>
<td></td>
<td></td>
<td><div id="divPendeteLite"  style="display:none"><textarea name="txtPendeteLite" placeholder="Masukkan keterangan Pendete Lite"  id="txtPendeteLite"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" rows="7" cols="60%" >$!catatan_keputusan</textarea></div></td>
</tr>
</table>
</fieldset>
<table width="100%">
<tr>
<td align="center">
#set($buttonSimpanKeputusan="yes")
#set($buttonKemaskiniKeputusan="")
#if($buttonKemaskiniKeputusan=="yes")
	<input name="cmdKemaskiniKeputusan" id="cmdKemaskiniKeputusan" value="Kemaskini" type="button" onClick="javascript:kemaskiniKeputusan()">
#end
#if($buttonSimpanKeputusan=="yes")
	<input name="cmdSimpanKeputusan" id="cmdSimpanKeputusan" value="Simpan" type="button" onClick="javascript:simpanKeputusan()">
#end
</td></tr>
</table>
#end



<input type="hidden" name="flag_kebenaran_edit" id="flag_kebenaran_edit"  value="$flag_kebenaran_edit" >    
<input type="hidden" name="user_id_kebenaran_edit" id="user_id_kebenaran_edit"  value="$user_id_kebenaran_edit">
<input type="hidden" name="id_permohonan_kebenaran" id="id_permohonan_kebenaran" value="$ID_PERMOHONAN"/>

<input type="hidden" name="user_id_temp" id="user_id_temp"   value="$!usid" >
<input type="hidden" name="command2" id="command2" value="" >
<input type="hidden" name="command3" id="command3" value="" >
<input type="hidden" name="id_status" value="$id_status"/>
<input type="hidden" name="keterangan" value="$keterangan_status"/>
<input type="hidden" name="id_fail" value="$id_fail"/>
<input type="hidden" name="id_fail_carian" id="id_fail_carian" value="$id_fail_carian" />
<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_suburusanstatusfail" value="$id_suburusanstatusfail"/>
<input type="hidden" name="seksyen" value="$seksyen"/>
<input type="hidden" name="level" value=""/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_keputusanpermohonan">
<input type="hidden" name="id_perbicaraan">
<input type="hidden" name="id_perintah">
<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />
  <!-- peje -->
         <input type="hidden" name="idPermohonan" value="$id_permohonan"/>
         <input type="hidden" name="idPermohonanSimati" value="$txtid_permohonansimati"/>
         <input type="hidden" name="idStatus"/>
         <input type="hidden" name="flagFromSenaraiFailSek8"/>
          <input type="hidden" name="flagForView"/>
         
         <!-- elly -->
         <input type="hidden" name="id_perbicaraan" value="$id_perbicaraan"/>
         <input type="hidden" name="idpermohonan"/>
         <input type="hidden" name="idpermohonansimati"/>
         <input type="hidden" name="tarikh_mohon" />
         <input type="hidden" name="id_status"/>
         <input type="hidden" name="id_Simati" value="$txtid_simati"/>		
		 <!-- man -->
		 <input type="hidden" name="idSimati"/>


#parse("app/ppk/headerppk_script.jsp")

#if($!latest_idstatus == "21")
#if($!role == "adminppk")
<script type="text/javascript">
check_flag();
</script>
#end
#end

<script type="text/javascript">
function checkSebabLain()
{
	if(document.${formName}.socSebab.value =="5")
		document.getElementById('divSebabsebabLain').style.display="block";
	else
		document.getElementById('divSebabsebabLain').style.display="none";
	}
	
function kemaskini()
{
 	document.${formName}.command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPraPerbicaraan";
	document.${formName}.submit(); 
	
	}
	
function simpan()
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
 	document.${formName}.command.value = "simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPraPerbicaraan";
	document.${formName}.submit(); 
	
	}
	
function simpanKeputusan()
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
 	document.${formName}.command.value = "simpanKeputusan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPraPerbicaraan";
	document.${formName}.submit(); 
	
	}
	
function socKeputusan2()
{
	if(document.${formName}.socKeputusan.value =="1")
	{
		document.getElementById('divSummaryDistribution').style.display="block";
		document.getElementById('divPendeteLite').style.display="none";
	}
	else if (document.${formName}.socKeputusan.value =="3")
	{
		document.getElementById('divSummaryDistribution').style.display="none";
		document.getElementById('divPendeteLite').style.display="block";
	}
	else
	{
		document.getElementById('divSummaryDistribution').style.display="none";
		document.getElementById('divPendeteLite').style.display="none";
	}
		
}

check_length(document.${formName}.catatan_batal,'4000','catatan_batal_check','catatan_batal_num','normal','yes','keterangan');
function cmdSimpan_flag()
{
	SaveScrollXY(); 
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "simpanFlag";
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmPraPerbicaraan";
	document.${formName}.cmdSimpan.value = "Sila Tunggu...";	
	document.${formName}.submit();

}
function search_main_data_sub(){
	SaveScrollXY();  
	document.${formName}.command.value = "cariMainSub";
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmPraPerbicaraan";
	
	//doAjaxCall${formName}("cariMainSub");
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";	
	document.${formName}.submit();
}
function kosongkan_sub() {
	SaveScrollXY();        
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmPraPerbicaraan";
	document.${formName}.command.value = "kosongkan";
	document.${formName}.txtNoFail.focus();
	//doAjaxCall${formName}("kosongkan");
	document.${formName}.submit();
}
function SaveScrollXY() {
    document.${formName}.ScrollX.value = document.body.scrollLeft;
    document.${formName}.ScrollY.value = document.body.scrollTop;
   }

function ResetScrollPosition() {
    var hidx, hidy;
    hidx = '$ScrollX';
   hidy = '$ScrollY';
                        
    if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
}
function paparFail(id_fail)
{
SaveScrollXY();        
document.${formName}.id_fail_carian.value = id_fail;
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmPraPerbicaraan";
	document.${formName}.command.value = "paparSub";
		document.${formName}.submit();
}


function papar(idPermohonan,idStatus,bil,idPermohonanSimati,tarikhMohon,jenisfail,seksyen,idSimati) {
SaveScrollXY(); 
	//keputusan rayuan
	if (idStatus == '164' || idStatus == '165'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaanSek17&command=semakKeputusanRayuan";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
	} else 
	//permohonan rayuan
	if (idStatus == '64' || idStatus == '163' || idStatus == '166' || idStatus == '167' || idStatus == '180' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=semakRayuan";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=semakRayuan";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
	} else
		
	//perintah
	if (idStatus == '21' || idStatus == '25'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
		}
			document.${formName}.idPermohonanSimati.value = idPermohonanSimati;
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idStatus.value = idStatus;
		
	} else 
	//keputusan perbicaraan (selesai perbicaraan)
	if (idStatus == '41' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;
	} else
	//keputusan perbicaraan (tangguh perbicaraan)		
	if (idStatus == '44' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_tangguh";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_tangguh";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;

	} else
	//keputusan perbicaraan (tangguh MT)		
	if (idStatus == '174' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanMT";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanMT";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;

	} else
	//keputusan perbicaraan (tangguh ROTS)		
	if (idStatus == '176' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanROTS";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanROTS";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;
			
	} else
	//keputusan perbicaraan (tangguh kolateral)		
	if (idStatus == '172' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_kolateral";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_kolateral";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;			
					
	} else
	//keputusan perbicaraan (batal perbicaraan)		
	if (idStatus == '47' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_batal";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_batal";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.id_Simati.value = idSimati;		
	
	} else 
	//notis perbicaraan
	if ((idStatus == '18' || idStatus == '44' || idStatus == '175' || idStatus == '173' || idStatus == '177' )){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
		}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
	
	} else 
	//keputusan permohonan
	if ((idStatus == '50' || idStatus == '53' || idStatus == '151' || idStatus == '152' || idStatus == '14'  || idStatus == '70'  )){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
		}
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idpermohonansimati.value = idPermohonanSimati;
			document.${formName}.tarikh_mohon.value = tarikhMohon;
			document.${formName}.idSimati.value = idSimati;
		
	} 
	
	
	
	
	
	
	
	
	else 
	//pendaftaran
	if ((idStatus == '8' || idStatus == '9'  || idStatus == '169' ) && (jenisfail == '1' || jenisfail == '2' )){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.command.value = "papar";
			document.${formName}.idSimati.value = idSimati;
	}
	if (bil == '1' && (jenisfail == '3' )){		
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar";
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.command.value = "papar";
	}
	
	document.${formName}.flagForView.value = "yes";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function check_flag()
{
	//alert("AAA"+document.${formName}.check_flag_batal.checked);
	if(document.${formName}.check_flag_batal.checked == true)
	{
	document.${formName}.flag_kebenaran_edit.value = "1";	
	document.${formName}.user_id_kebenaran_edit.value = document.${formName}.user_id_temp.value;
	document.getElementById('tr_nama').style.display = "";	
	document.getElementById('tr_catatan').style.display = "";
	}
	else
	{
	document.${formName}.flag_kebenaran_edit.value = "";
	document.${formName}.user_id_kebenaran_edit.value = "";	
	document.getElementById('tr_nama').style.display = "none";	
	document.getElementById('tr_catatan').style.display = "none";
	}
}


function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   //$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");



}


function currentFail(seksyen,idPermohonan,idStatus)
{
if (idStatus == '53' || idStatus == '151' )
{
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData&tabId=0";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData&tabId=0";
			}

} 
else if (idStatus == '44' || idStatus == '173' || idStatus == '175' || idStatus == '177' )
{
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis&tabId=0";
			}

} 

else if (idStatus == '18')
{
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
			}

}


else
{
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
			}

}
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_status.value = idStatus;
			document.${formName}.method="POST";
	document.${formName}.submit();
}

function onchangeJenisPejabat() {
	//doAjaxCall${formName}("onchangeJenisPejabat");
	document.${formName}.command2.value = "onchangeJenisPejabat";
	document.${formName}.command.value = "paparSub";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPraPerbicaraan"; 
	document.${formName}.submit();	
}

function doChangeidTempatBicara() {
	document.${formName}.command2.value = "onchangeJenisPejabat";
	document.${formName}.command.value = "paparSub";
	document.${formName}.command3.value = "doChangeidTempatBicara";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmPraPerbicaraan"; 
	document.${formName}.submit();
	
}


function historyFail(seksyen,idPermohonan,idStatus,idPermohonanSimati)
{
if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
		}
			document.${formName}.idPermohonanSimati.value = idPermohonanSimati;
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idStatus.value = idStatus;
			document.${formName}.method="POST";
	document.${formName}.submit();
}
</script>