<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style>


<!-- 

::::::::::: $!total_harta 
-->
<br/>
<input type="hidden" id="tot" name="tot" value="$!total_harta" >
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

<fieldset><legend><b>
 #parse("app/ppk/tag_baru.jsp")
 &nbsp;
Semakan Suburusanstatusfail</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">MyID Simati / No Fail : </td>
      			<td width="70%"><input name="txtNoFailSub" id="txtNoFailSub" type="text" value="$!txtNoFailSub" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" />
              
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
                <td class="table_header" width="5%" style="display:none"><b></b></td>
      		    <td class="table_header" width="10%" style="display:none"><b>ID FAIL</b></td>
                <td class="table_header" width="20%"><b>NO FAIL</b></td>
                <td class="table_header" width="20%"><b>NAMA SIMATI</b></td>
                <td class="table_header" width="20%"><b>NAMA PEMOHON</b></td>
                <td class="table_header" width="10%"><b>TARIKH MOHON</b></td>
                <td class="table_header" width="15%"><b>STATUS SEMASA</b></td>
                
                          
    		</tr>
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
         #set( $row = "tr_class" )
         #end
            <tr id="$tr_id" class="$row"    >
                <td style="display:none"  >  
         <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="white"> Papar  </font>
         #else
         <font color="blue"> Papar  </font>
         #end  
         
              </a>   
                </td>
      			
      		    <td  style="display:none" >  
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
     
     
     <br />             
        <fieldset width=100>
	<legend><strong> NOTA PENGGUNAAN TUKAR STATUS </strong></legend>
	
    <table >
    <tr>
    <td>1) </td>
    <td ><b>TUJUAN</b> :</td>
    </tr>
    
    <tr>
    <td></td>
    <td >Penukaran Nama Pegawai Pengendali di dalam Notis Perbicaraan dan Keputusan Perbicaraan.</td>
    </tr>
    
     <tr>
    <td></td>
    <td ><b>KAEDAH</b> :</td>
    </tr>
    
    <tr>
    <td></td>
    <td ><ul>
		  <li>Tukar status pada "Permohonan Selesai" sahaja.</li>
		  <li>Kemaskini seperti biasa.</li>
		  <li>Tukar semula status pada "Selesai".</li>
		</ul>
	</td>
    </tr>
    
    <tr>
        <td>2) </td>
    	<td ><b>TUJUAN</b> :</td>
    </tr>
    
    <tr>
	    <td></td>
	    <td >Penukaran pembahagian perintah.</td>
    </tr>
    
     <tr>
    <td></td>
    <td ><b>KAEDAH</b> :</td>
    </tr>
    
    <tr>
    <td></td>
    <td ><ul>
		  <li>Tukar status pada "Notis Perbicaraan".</li>
		  <li>Klik pada checkbox "Semak Untuk Hapus Data Berkenaan Status ini" pada status "Selesai Perbicaraan" dan tekan butang Hapus.</li>
		  <li>Masukkan semula Keputusan Perbicaraan dan Perintah.</li>
		</ul>
	</td>
    </tr>
    
     <tr>
        <td>3) </td>
    	<td ><b>TUJUAN</b> :</td>
    </tr>
    
    <tr>
	    <td></td>
	    <td >Penukaran keputusan perbicaraan.</td>
    </tr>
    
     <tr>
    <td></td>
    <td ><b>KAEDAH</b> :</td>
    </tr>
    
    <tr>
    <td></td>
    <td ><ul>
		  <li>Tukar status pada "Notis Perbicaraan".</li>
		  <li>Klik pada checkbox "Semak Untuk Hapus Data Berkenaan Status ini" pada status "Selesai Perbicaraan" dan tekan butang Hapus.</li>
		  <li>Masukkan semula Keputusan Perbicaraan dan Perintah.</li>
		</ul>
	</td>
    </tr>
    
    <tr>
        <td colspan=2 class=blink><b><font color=red>*Amaran : Penyalahgunaan Tukar Status akan menyebabkan data bertindih. </font></b></td>
    	
    </tr>
    
    
    </table>
</fieldset>

     
     
        <br />             
        <fieldset>
	<legend><strong>Senarai Suburusanstatusfail <font color="blue">$!txtNoFailSub_selected
    <input type="hidden" name="txtNoFailSub_selected" id="txtNoFailSub_selected" value="$!txtNoFailSub_selected" >
    </font> </strong>
    #if($list_sub.size() > 0)
    <input name="cmdSimpanData" id="cmdSimpanData" value="Simpan" type="button" onClick="javascript:update_data_sub()">
    <input id="cmdHapus" name="cmdHapus" type="button"  style="display:none" value="Hapus" onClick="javascript:hapus()" />
    #end
    </legend>
    <div id="alert_sub"></div>   
    
    <input type="hidden" id="NO_FAIL_TEMP" name="NO_FAIL_TEMP" value="$!NO_FAIL_TEMP"  />
    <input type="hidden" id="ID_FAIL_TEMP" name="ID_FAIL_TEMP" value="$!ID_FAIL_TEMP"  />
    <input type="hidden" id="ID_PERMOHONAN_TEMP" name="ID_PERMOHONAN_TEMP" value="$!ID_PERMOHONAN_TEMP"  />  
    <input type="hidden" id="ID_SUBURUSAN_TEMP" name="ID_SUBURUSAN_TEMP" value="$!ID_SUBURUSAN_TEMP"  />  
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
<tr >
      			<td class="table_header" width="2%" valign="bottom"><b>Bil</b></td>
      		    <td class="table_header" width="44%"  valign="bottom"><b>Status Fail</b></td>
                <td class="table_header" width="15%"  valign="bottom"><b>Status Aktif</b></td>
                 <td class="table_header" width="18%" valign="bottom">
                <b>Semak Untuk Hapus Suburusan Status Fail</b>
                <input type="checkbox"  style="display:none" name="all1_delete"    id="all1_delete" onClick="doCheckall1_delete()" title="Semak untuk pilih semua" />
                 
                 </td>
                 
                  <td class="table_header" width="18%" valign="bottom">
                <b>Semak Untuk Hapus Data Berkenaan Status Ini</b>
                <input type="checkbox"style="display:none"  name="all1_delete_data"    id="all1_delete_data" onClick="doCheckall1_delete_data()" title="Semak untuk pilih semua" />
                 
                 </td>
                 
                 <td width="3%" background="white" align="left"  valign="top">
                 
                 </td>
               
</tr>
         
            #if($list_sub.size() > 0)
    		
    		#foreach($list in $list_sub)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
    		<tr>
      			<td class="$row" >$list.BIL
                  <input type="hidden" id="ID_SUBURUSANSTATUSFAIL" name="ID_SUBURUSANSTATUSFAIL" value="$!list.ID_SUBURUSANSTATUSFAIL"  /> 
                </td>
      			         
               
                <td class="$row"  >
              
               <!-- :::: $list.ID_STATUS BILA STATUS 14...BUKA DROPDOWN..TP HANYA BOLEH PILIH ID STATUS 151 (TERUS) 152 (BATAL)-->
               <!-- ::::::::: $list.ID_STATUS -->
                #set($ID_SUBURUSANSTATUS_TEMP = "ID_SUBURUSANSTATUS"+$list.BIL)
                
                #if($list.ID_STATUS != "14" && $list.ID_STATUS != "151" && $list.ID_STATUS != "152")
                <input type="hidden" name="$ID_SUBURUSANSTATUS_TEMP" id="$ID_SUBURUSANSTATUS_TEMP" value="$list.ID_SUBURUSANSTATUS" >
                <!--  
               			 <select  class="autoselect" name="X$ID_SUBURUSANSTATUS_TEMP" id="X$ID_SUBURUSANSTATUS_TEMP" style="display:none">	   	 
						   		#foreach ( $y in $list_status )
                                #if($y.ID_SUBURUSAN == $!ID_SUBURUSAN_TEMP)
						   		#if( $y.ID_SUBURUSANSTATUS == $list.ID_SUBURUSANSTATUS )
						   			#set ( $selected_aktif = "selected" )
						   		#else
						   			#set ( $selected_aktif = "" )
						   		#end                       
						   	<option value="$y.ID_SUBURUSANSTATUS" $!selected_aktif >                            
                            $y.NAMA_STATUS                            
                            </option>
						   		#end
                                #end
							</select> 
							-->
                            $list.STATUS  
                            #else
                          <!--  ::::::::: $!ID_SUBURUSAN_TEMP -->
                            <select  class="autoselect" name="$ID_SUBURUSANSTATUS_TEMP" id="$ID_SUBURUSANSTATUS_TEMP" >	   	 
						   		#foreach ( $y in $list_status )
                        #if($y.ID_SUBURUSAN == $!ID_SUBURUSAN_TEMP && ($y.ID_STATUS == "14" || $y.ID_STATUS == "151" || $y.ID_STATUS == "152"))
						   		#if( $y.ID_SUBURUSANSTATUS == $list.ID_SUBURUSANSTATUS )
						   			#set ( $selected_aktif = "selected" )
						   		#else
						   			#set ( $selected_aktif = "" )
						   		#end                       
						   	<option value="$y.ID_SUBURUSANSTATUS" $!selected_aktif >                            
                            $y.NAMA_STATUS                            
                            </option>
						   		#end
                                #end
							</select> 
                            
                            #end           
                
                
                </td>
                <td class="$row">
               					#if($list.AKTIF == "1")
						   			#set ( $check_aktif = "checked" )
						   		#else
						   			#set ( $check_aktif = "" )
						   		#end 
                
       <input type="checkbox" name="AKTIF_CHECK" id="AKTIF_CHECK" onClick="check_aktif('$list_sub.size()')"  $check_aktif  value="$list.AKTIF">
       <input type="hidden" name="AKTIF" id="AKTIF" value="$list.AKTIF" >
               
               
               #set($list_aktif = ["0", "1"]) 
                 <select  class="autoselect" name="AKTIF_TEMP" id="AKTIF_TEMP" onChange="setAktif(this.value)" style="display:none">
						   	 
						   		#foreach ( $y in $list_aktif )
						   		#if( $y == $list.AKTIF )
						   			#set ( $selected_aktif = "selected" )
						   		#else
						   			#set ( $selected_aktif = "" )
						   		#end                       
						   	<option value="$y" $!selected_aktif >
                            
                            #if($y == "0")
                            Tidak Aktif
                            #else
                            Aktif
                            #end
                            
                            </option>
						   		#end
							</select>             
              <!--   $list.AKTIF  --></td>
             <td class="$row">
             <input type="checkbox" name="ids1_delete" id="ids1_delete"  onClick="doUpdateCheckall1_delete()" value="$!list.ID_SUBURUSANSTATUSFAIL" >
             <font  color="blue"  onMouseOver="this.style.cursor='help'" onClick="open_new_window('1','$list.STATUS');" ><img src="../img/info.png"  align="center" /></font>              
             </td>
              <td class="$row">
              
             <!-- $!list.ID_STATUS == '8' || $!list.ID_STATUS == '170' || $!list.ID_STATUS == '9' ||  -->
              
              #if($!list.ID_STATUS == '8' || $!list.ID_STATUS == '170' || $!list.ID_STATUS == '9' || $!list.ID_STATUS == '14' || $!list.ID_STATUS == '53' || $!list.ID_STATUS == '70' || $!list.ID_STATUS == '18' || $!list.ID_STATUS == '14' || $!list.ID_STATUS == '44' || $!list.ID_STATUS == '47' || $!list.ID_STATUS == '41' || $!list.ID_STATUS == '25' || $!list.ID_STATUS == '21'  || $!list.ID_STATUS == '151' || $!list.ID_STATUS == '152')
             <input type="checkbox" name="ids1_delete_data" id="ids1_delete_data"  onClick="doUpdateCheckall1_delete()" value="$!list.ID_STATUS" >     <font  color="blue"  onMouseOver="this.style.cursor='help'" onClick="open_new_window('2','$list.ID_STATUS');" ><img src="../img/info.png"  align="center" /></font> 
              #else
            <input type="checkbox" style="display:none" name="ids1_delete_data" id="ids1_delete_data"  onClick="doUpdateCheckall1_delete()" value="$!list.ID_STATUS" >   
              #end
              
           
       <!--     :::: $!list.ID_STATUS -->
             </td>
<td  background="white" align="left">
                 
                 </td>
    		</tr>
    		#end
            
            #else
            <tr>
      			<td class="row" colspan="7">Tiada rekod</td>      			
    		</tr>
            #end
       
		</table>
        <br>
        <font color="blue"><b>Penambahan Suburusanstatusfail</b></font>
        <table width="100%"  cellpadding="1" cellspacing="2" border="0">
<tr >
      			
      		    <td class="table_header" width="61%"><b>Status Fail</b></td>
                <td class="table_header" width="18%"><b>Status Aktif</b></td>
                <td class="table_header" width="18%"></td>
                <td width="3%"></td>
                          
    		</tr>
            
            <tr class="row">
      			
      		    <td class="row" >
                <select  class="autoselect" name="ID_SUBURUSANSTATUS_ADD" id="ID_SUBURUSANSTATUS_ADD" >	 
                <option value="" >SILA PILIH STATUS</option>    	 
						   		#foreach ( $y in $list_status )
                        #if($y.ID_SUBURUSAN == $!ID_SUBURUSAN_TEMP)
						   		#if( $y.ID_SUBURUSANSTATUS == $list.ID_SUBURUSANSTATUS )
						   			#set ( $selected_aktif = "selected" )
						   		#else
						   			#set ( $selected_aktif = "" )
						   		#end                       
						   	<option value="$y.ID_SUBURUSANSTATUS" $!selected_aktif >                            
                            $y.NAMA_STATUS                            
                            </option>
						   		#end
                                #end
							</select>
                </td>
                <td >
                
                              
       <input type="checkbox" name="AKTIF_ADD_CHECK" id="AKTIF_ADD_CHECK" onClick="check_aktif_add()" >
       <input type="hidden" name="AKTIF_ADD" id="AKTIF_ADD"  >
                
                          
                
                </td>
                <td>
                
                <input name="cmdTambahData" id="cmdTambahData" value="Tambah" type="button" onClick="javascript:tambah_sub('$!list_sub.size()')">
                </td>
                
                <td></td>
                          
    		</tr>
            </table>
            
            
        <br/>
<!--	sub_id_status ::::::::::: $sub_id_status -->
   
  #if($listSemak_sub.size() > 0)  
    #if($sub_id_status!=21 && $sub_id_status!=64 && $sub_id_status!=163 && $sub_id_status!=169)
	
<font color="blue"><b>Selesaikan Permohonan</b></font>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
	  <tr >
      			<td class="table_header" width="5%"><b>Bil</b></td>
      			<td class="table_header" width="92%"><b>Status</b></td>
                <td  width="3%"></td>
</tr>
    		
    		#foreach($list in $listSemak_sub)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
    		<tr>
      			<td class="$row">$list.bil</td>
      			<td class="$row"><a href="javascript:tukarstatus_sub('$sub_id_permohonan','$sub_keterangan_status','$sub_id_suburusanstatusfail','$sub_id_fail','$list.level','$list.list_keterangan','$sub_id_keputusanpermohonan','$sub_id_perbicaraan','$sub_id_perintah')"><font color="blue">$list.list_keterangan</font></a></td>
                <td></td>
</tr>
    		#end
       
		</table>
	
    #end
     #end   
        
	</fieldset>	
    
   
    

		#end
	</fieldset>



	

	

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


#parse("app/ppk/headerppk_script.jsp")


<script type="text/javascript">

var total_sub = '$!list_sub.size()';
if(total_sub>0)
{
semak_aktif_sub('$!list_sub.size()');
}

function kosongkan() {
SaveScrollXY();        
	document.${formName}.action = "";
	//document.${formName}.command.value = "kosongkan";
	document.${formName}.txtNoFail.focus();
	//doAjaxCall${formName}("kosongkan");	
	document.${formName}.command.value = "kosongkan";
	document.${formName}.submit();
	//document.${formName}.submit();
}
function tukarstatus(id_permohonan,keterangan,id_suburusanstatusfail,id_fail,level,keterangan2,id_keputusanpermohonan,id_perbicaraan,id_perintah) {
SaveScrollXY();        
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_suburusanstatusfail.value = id_suburusanstatusfail;
	document.${formName}.id_keputusanpermohonan.value = id_keputusanpermohonan;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.id_perintah.value = id_perintah;
	document.${formName}.level.value = level;
	//document.${formName}.command.value = "tukarstatus";
	//document.${formName}.submit();
	//doAjaxCall${formName}("tukarstatus");	
	document.${formName}.command.value = "tukarstatus";
	document.${formName}.submit();
	alert("Status \""+keterangan+"\" telah ditukar kepada status \""+keterangan2+"\" ");
}
function tukarstatus_sub(id_permohonan,keterangan,id_suburusanstatusfail,id_fail,level,keterangan2,id_keputusanpermohonan,id_perbicaraan,id_perintah) {
SaveScrollXY(); 
var tot = document.${formName}.tot.value;
//alert("XXX"+tot);


	if(parseInt(tot) == 0)
	{
	alert("Sila pastikan fail ini memiliki harta tak alih atau harta alih.")
	}
	 else
	 {     
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_suburusanstatusfail.value = id_suburusanstatusfail;
	document.${formName}.id_keputusanpermohonan.value = id_keputusanpermohonan;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.id_perintah.value = id_perintah;
	document.${formName}.level.value = level;
	document.${formName}.command.value = "tukarstatus_sub";
	//document.${formName}.submit();
	//alert("Status \""+keterangan+"\" telah ditukar kepada status \""+keterangan2+"\" ");
	//doAjaxCall${formName}("tukarstatus_sub");
	document.${formName}.command.value = "tukarstatus_sub";
	document.${formName}.submit();
	}
	
}
function search_data(){
SaveScrollXY();        
	//document.${formName}.command.value = "Cari";
	document.${formName}.action = "";
	//doAjaxCall${formName}("Cari");
	//document.${formName}.submit();
	document.${formName}.command.value = "Cari";
	document.${formName}.submit();
}

function search_main_data_sub(){
SaveScrollXY();        
	//document.${formName}.command.value = "cariSub";
	//document.${formName}.action = "";
	//doAjaxCall${formName}("cariMainSub");
	document.${formName}.command.value = "cariMainSub";
	document.${formName}.submit();
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";
	
	
}


function search_data_sub(){
SaveScrollXY();        
	//document.${formName}.command.value = "cariSub";
	//document.${formName}.action = "";
	//doAjaxCall${formName}("cariSub");
	document.${formName}.command.value = "cariSub";
	document.${formName}.submit();
	
}

function hapus(){
input_box = confirm("Adakah anda pasti? Data akan dihapuskan dari Pengkalan Data");
	if (input_box == true) 
	{	
	SaveScrollXY();        
	//document.${formName}.command.value = "hapusSub";
	document.${formName}.action = "";
	//doAjaxCall${formName}("hapusSub");
	document.${formName}.command.value = "hapusSub";
	document.${formName}.submit();
	document.${formName}.cmdHapus.value="Sila Tunggu...";
	}
}


function semak_aktif_sub(jumlah_sub)
{
//alert("XXXXXXX :"+document.${formName}.AKTIF.length );
//alert("jumlah_sub :"+jumlah_sub );
		var check = 0;				
		if(jumlah_sub>0)
		{		
		if (document.${formName}.AKTIF.length == null)
		{
            	if(document.${formName}.AKTIF.value == '1')
				{
				check++;
				}
			//alert("1:"+document.${formName}.AKTIF.value);
        } 
		else 
		{
           		 for (i = 0; i < document.${formName}.AKTIF.length; i++)
				 {
					//alert("2:"+document.${formName}.AKTIF[i].value);
					if(document.${formName}.AKTIF[i].value == '1')
					{
					check++;
					}
           		 }
        }
	}	
//alert("check"+check);
else if(check > 1)
{
$jquery("#alert_sub").html("<font color = 'red'><blink>Sila Pastikan Status Aktif Tidak Lebih Daripada Satu</blink></font>");
}
}

function tambah_sub(jumlah_sub){

		var check = 0;
		
		//alert("XXXXXXX:"+document.${formName}.AKTIF.length)
		//alert("jumlah_sub:"+jumlah_sub);
		
		if(jumlah_sub>0)
		{
		
		if (document.${formName}.AKTIF.length == null){
            	if(document.${formName}.AKTIF.value == '1')
				{
				check++;
				}
			//alert("1:"+document.${formName}.AKTIF.value);
        } else {
           		 for (i = 0; i < document.${formName}.AKTIF.length; i++)
				 {
					//alert("2:"+document.${formName}.AKTIF[i].value);
					if(document.${formName}.AKTIF[i].value == '1')
					{
					check++;
					}
           		 }
        }
	}	
	
    if(document.${formName}.AKTIF_ADD.value == '1')
	{
	check++;
	}	
		
	//alert("check :"+check);	
		
    if(document.${formName}.ID_SUBURUSANSTATUS_ADD.value == "")
	{
	alert("Sila Pilih Status Fail");
	}
	else if(document.${formName}.AKTIF_ADD.value == "")
	{
	alert("Sila Pilih Status Aktif");
	}
	/*else if(check == 0)
	{
	alert("Sila Pastikan Satu Status Adalah Aktif");
	}*/
	else if(check > 1)
	{
	alert("Sila Pastikan Status Aktif Tidak Lebih Daripada Satu");
	}
	else
	{
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) 
		{	
		SaveScrollXY();        
		//document.${formName}.command.value = "tambahSub";
		document.${formName}.action = "";
		//doAjaxCall${formName}("tambahSub");
		document.${formName}.command.value = "tambahSub";
		document.${formName}.submit();
	    document.${formName}.cmdTambahData.value = "Sila Tunggu...";
		}
	}
}



function update_data_sub(){
var check = 0;
		if (document.${formName}.AKTIF.length == null){
            if(document.${formName}.AKTIF.value == '1')
				{
				check++;
				}
			//alert("1:"+document.${formName}.AKTIF.value);
        } else {
            for (i = 0; i < document.${formName}.AKTIF.length; i++){
                //alert("2:"+document.${formName}.AKTIF[i].value);
				if(document.${formName}.AKTIF[i].value == '1')
				{
				check++;
				}
            }
        }
    
	//alert("check :"+check);

    if(check > 1)
	{
	alert("Sila Pastikan Status Aktif Tidak Lebih Daripada Satu");
	}
	else
	{
	SaveScrollXY();        
	//document.${formName}.command.value = "updateSub";
	document.${formName}.action = "";
	//doAjaxCall${formName}("updateSub");
	document.${formName}.command.value = "updateSub";
		document.${formName}.submit();
	document.${formName}.cmdSimpanData.value="Sila Tunggu...";
	}
}


function kosongkan_sub() {
SaveScrollXY();        
	document.${formName}.action = "";
	//document.${formName}.command.value = "kosongkan";
	document.${formName}.txtNoFail.focus();
	//doAjaxCall${formName}("kosongkan");
	//document.${formName}.submit();
	document.${formName}.command.value = "kosongkan";
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
  
  
  
  function doCheckall1_delete_data(){   
//alert("test"); 
    if (document.${formName}.all1_delete_data.checked == true){
	
	document.getElementById('cmdHapus').style.display = "";
	
        if (document.${formName}.ids1_delete_data.length == null){
            document.${formName}.ids1_delete_data.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete_data.length; i++){
                document.${formName}.ids1_delete_data[i].checked = true;
            }


        }
    } else {
	
	if (document.${formName}.all1_delete.checked == true)
	{
	document.getElementById('cmdHapus').style.display = "";
	}
	else
	{
	document.getElementById('cmdHapus').style.display = "none";
	}
	
        if (document.${formName}.ids1_delete_data.length == null){
            document.${formName}.ids1_delete_data.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete_data.length; i++){
                document.${formName}.ids1_delete_data[i].checked = false;
            }
        }
    }
	
}

function doCheckall1_delete(){    
    if (document.${formName}.all1_delete.checked == true){
	document.getElementById('cmdHapus').style.display = "";
        if (document.${formName}.ids1_delete.length == null){
            document.${formName}.ids1_delete.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete.length; i++){
                document.${formName}.ids1_delete[i].checked = true;
            }


        }
    } else {
	if (document.${formName}.all1_delete_data.checked == true)
	{
	document.getElementById('cmdHapus').style.display = "";
	}
	else
	{
	document.getElementById('cmdHapus').style.display = "none";
	}
        if (document.${formName}.ids1_delete.length == null){
            document.${formName}.ids1_delete.checked = false;
        } else {
            for (i = 0; i < document.${formName}.ids1_delete.length; i++){
                document.${formName}.ids1_delete[i].checked = false;
            }
        }
    }
	
	//doCheckall1_delete_data()
}


function doUpdateCheckall1_delete(){  
var c = 0;
var x = 0;

if(document.${formName}.ids1_delete.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1_delete.length; i++)
	  {
      if (document.${formName}.ids1_delete[i].checked == false)
	  {	 
	  c++
      }else
	  {	 
	  x++
      }
	  }  
}
else
{
if (document.${formName}.ids1_delete.checked == false)
{	 
c++;
}
else
{
x++
}
	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1_delete.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1_delete.checked = true;
	  }
	  
	  
	  
	  var cc = 0;
var xx = 0;

if(document.${formName}.ids1_delete_data.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1_delete_data.length; i++)
	  {
      if (document.${formName}.ids1_delete_data[i].checked == false)
	  {	 
	  cc++
      }else
	  {	 
	  xx++
      }
	  }  
}
else
{
if (document.${formName}.ids1_delete_data.checked == false)
{	 
cc++;
}
else
{
xx++
}
	 	
}



	 
   if(cc>0)
	  {	  
	  document.${formName}.all1_delete_data.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1_delete_data.checked = true;
	  }
	  
	  if((x+xx)>0) 
	  {
	  document.getElementById('cmdHapus').style.display = "";
	  }
	  else
	  {
	  document.getElementById('cmdHapus').style.display = "none";
	  }
	  
}





function open_new_window(jenis_popup,nama_suburusan) 
{
 var width  = 300;
 if(jenis_popup == "2")
 {
 if(nama_suburusan == "8" || nama_suburusan == "9" || nama_suburusan == "170")
{
 var height = 500;
}
else
{
 var height = 300;
 }
 }
 else
 {
 var height = 200;
 }
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
//new_window = open("","hoverwindow",params);
new_window = open("","title",params);

new_window.document.open();

new_window.document.write("<html><title>JavaScript New Window</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
if(jenis_popup == "1")
{
new_window.document.write("Semak dan tekan butang 'Hapus' untuk menghapuskan Urusan<br><b><font color='blue'>"+nama_suburusan+"</font></b><br>");

}
if(jenis_popup == "2")
{
new_window.document.write("Senarai <i>Table</i> yang akan dihapuskan<br>");
if(nama_suburusan == "8" || nama_suburusan == "9" || nama_suburusan == "170")
{
new_window.document.write("<b><font color='blue'>TBLPFDFAIL</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERMOHONAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPEMOHON</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPEGUAMPEMOHON</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERMOHONANSIMATI</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKSIMATI</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLRUJSUBURUSANSTATUSFAIL</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKBAYARAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLSEMAKANHANTAR</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKOB</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKHTA</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKHA</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKKEPUTUSANPERMOHONAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERBICARAAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKNOTISOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKNOTISPERBICARAAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAH</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBDTL</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBDTL</font></b><br>");
}
if(nama_suburusan == "151" || nama_suburusan == "152" || nama_suburusan == "14" || nama_suburusan == "53" || nama_suburusan == "70")
{
new_window.document.write("<b><font color='blue'>TBLPPKKEPUTUSANPERMOHONAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERBICARAAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKNOTISOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKNOTISPERBICARAAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAH</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBDTL</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBDTL</font></b><br>");
}
if(nama_suburusan == "18")
{
new_window.document.write("<b><font color='blue'>TBLPPKPERBICARAAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKNOTISOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKNOTISPERBICARAAN</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAH</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBDTL</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBDTL</font></b><br>");
}
if(nama_suburusan == "44" || nama_suburusan == "47")
{
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAH</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBDTL</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBDTL</font></b><br>");
}
if(nama_suburusan == "41" || nama_suburusan == "21" || nama_suburusan == "25")
{
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAH</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHTAOBDTL</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBMST</font></b><br>");
new_window.document.write("<b><font color='blue'>TBLPPKPERINTAHHAOBDTL</font></b><br>");
}
}

new_window.document.write("<br>");
new_window.document.write("</body></html>");
new_window.document.close(); 







}


function close_window() 
{
new_window.close();
}

function tr_id_up(tr_id,row)
{

var tr_class = document.getElementById(tr_id);
tr_class.className = "tr_class";

}

function tr_id_out(tr_id,row)
{

var tr_class = document.getElementById(tr_id);
tr_class.className = row;

}

function paparFail(id_fail)
{
SaveScrollXY();        
document.${formName}.id_fail_carian.value = id_fail;
//doAjaxCall${formName}("paparSub");
document.${formName}.command.value = "paparSub";
		document.${formName}.submit();
}

function setAktif(val)
{
alert("VAL:"+val)
}

function check_aktif(jumlah_sub)
{
		var check = 0;				
		if(jumlah_sub>0)
		{		
		if (document.${formName}.AKTIF_CHECK.length == null)
		{
            	if(document.${formName}.AKTIF_CHECK.checked == true)
				{
				document.${formName}.AKTIF.value = "1";
				}
				else
				{
				document.${formName}.AKTIF.value = "0";
				}
			
        } 
		else 
		{
           		 for (i = 0; i < document.${formName}.AKTIF_CHECK.length; i++)
				 {
					//alert("2:"+document.${formName}.AKTIF_CHECK[i].checked);
					if(document.${formName}.AKTIF_CHECK[i].checked == true)
					{
					document.${formName}.AKTIF[i].value = "1";
					}
					else
					{
					document.${formName}.AKTIF[i].value = "0";
					}
           		 }
        }
	}	
}

function check_aktif_add()
{
//alert("XXXXX:"+document.${formName}.AKTIF_ADD_CHECK.checked);
				if(document.${formName}.AKTIF_ADD_CHECK.checked == true)
				{
				document.${formName}.AKTIF_ADD.value = "1";
				}
				else
				{
				document.${formName}.AKTIF_ADD.value = "0";
				}
}

</script>