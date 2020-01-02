<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style>


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
     
#if($!role == "adminppk" && $FLAG_KEBENARAN_BICARA_SEMULA != "1")
#if($!ID_STATUS == "21")
<br>  
<fieldset>
<legend ><b>Kebenaran Benaran Membicarakan Semula Fail</b></legend>
 
 <table width="97%"  cellpadding="1" cellspacing="2" border="0" >
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
             <!-- 
              <a href="javascript:currentFail($!SEKSYEN,$!ID_PERMOHONAN,$!ID_STATUS)" ><font color="blue">$!NO_FAIL</font></a>
            -->
                    
                             
                         
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
		</table>
        
        
        
        
</fieldset>
#else
<br>
<fieldset>
<legend ><b>Kebenaran Benaran Membicarakan Semula Fail</b></legend>
 
 <table width="97%"  cellpadding="1" cellspacing="2" border="0" >
 <tr>
 <td colspan="3">
 <font color="blue">
 <i>Ruanganan ini bertujuan untuk <b>Pegawai</b> membatalkan <strong>Perintah</strong> semasa untuk dibicarakan semula (Arahan Dari Mahkamah Tinggi)</i>
 </font>
 </td>
 </tr>
 <tr>
 <td colspan="3">
 
 <i>
 <font color="red">
 Perhatian : </font> 
 Apabila fail ini <b>dibatalkan,</b> fail ini tidak akan aktif dan fail ini tidak dapat diaktifkan semula</i>
<br><br>
 </td>
 </tr>
 <tr>
      			<td scope="row" width="28%">No. Fail</td>
                <td width="1%">:              
                </td>
      			<td width="67%">
              <font color="blue">$!NO_FAIL</font>
             <!-- 
              <a href="javascript:currentFail($!SEKSYEN,$!ID_PERMOHONAN,$!ID_STATUS)" ><font color="blue">$!NO_FAIL</font></a>
            -->
                    
                         
                </td>
    		</tr>
             <tr>
      			<td scope="row">Status Semasa</td>
                <td >:              
                </td>
      			<td >
              <font color="blue">$!STATUS</font>&nbsp; <font color="red"><blink>Fail ini tidak dibenarkan untuk dibicarakan semula!</blink></font>
                             
                         
                </td>
    		</tr>
            </table></fieldset>
#end
#else

<br>
<fieldset>
<legend ><b>Kebenaran Benaran Membicarakan Semula Fail</b></legend>
 
 <table width="97%"  cellpadding="1" cellspacing="2" border="0" >
 <tr>
 <td colspan="3">
 <font color="blue">
 <i>Ruanganan ini bertujuan untuk <b>Pegawai</b> membatalkan <strong>Perintah</strong> semasa untuk dibicarakan semula (Arahan Dari Mahkamah Tinggi)</i>
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
      			<td scope="row" width="28%" valign="top">No. Fail</td>
                <td width="1%" valign="top">:              
                </td>
      			<td width="67%" valign="top">
              <!-- <font color="blue">$!NO_FAIL</font> -->
             
              <a href="javascript:currentFail($!SEKSYEN,$!ID_PERMOHONAN,$!ID_STATUS)" ><font color="blue">$!NO_FAIL</font></a>
              <br>
               Sila klik <a href="javascript:currentFail($!SEKSYEN,$!ID_PERMOHONAN,$!ID_STATUS)" ><font color="blue">Notis Perbicaraan</font></a> bagi menyemak  dan mengemaskini maklumat notis dan urusan seterusnya.
            
                             
                         
                </td>
    		</tr>
             <tr>
      			<td scope="row" valign="top">Status Semasa</td>
                <td valign="top">:              
                </td>
      			<td valign="top">
              <font color="blue">$!STATUS</font>
              #if($!NO_FAIL_SEBELUM_BICARA!="")
              &nbsp; <font color="red"><blink>Fail ini dibenarkan untuk dibicarakan semula!</blink></font>
              <br>Untuk semakan maklumat perbicaraan terdahulu, sila klik               
               <a href="javascript:historyFail($SEKSYEN_SEBELUM_BICARA,$ID_PERMOHONAN_SEBELUM_BICARA,$ID_STATUS_SEBELUM_BICARA,$ID_PERMOHONANSIMATI_SEBELUM)" ><font color="blue"> $!NO_FAIL_SEBELUM_BICARA</font></a>             
              </font>
              #end  
              
              #if($!NO_FAIL_SELEPAS_BICARA!="")
             &nbsp; <font color="red"><blink>Fail ini telah dibicarakan semula!</blink></font>
              <br>Untuk semakan maklumat perbicaraan semasa, sila klik
               
              <a href="javascript:currentFail($!SEKSYEN_SELEPAS_BICARA,$!ID_PERMOHONAN_SELEPAS_BICARA,$!ID_STATUS_SELEPAS_BICARA)" ><font color="blue"> $!NO_FAIL_SELEPAS_BICARA</font></a>  
              #end           
            
                </td>
    		</tr>
            <tr >
      			<td scope="row" valign="top">Pengesahan Dibuat Oleh</td>
                <td valign="top">
                :                              
                </td>                
      			<td colspan="2" valign="top">
                      
                #if($!USER_NAME != "")               
                <font color="blue">$!USER_NAME</font>                
                #else                
                <font color="blue">KEPUTUSAN MAHKAMAH</font>
                #end
                
                </td>
    		</tr>
            <tr >
      			<td scope="row" valign="top">Catatan</td>
                <td valign="top" >
                :                              
                </td>                
      			<td  colspan="2" valign="top" >
                               
                <div  style="width:50%"><font color="blue">$!CATATAN_BICARA_SEMULA</font></div>  
                
                
                </td>
                
    		</tr>
            
            
            
            </table></fieldset>

#end
#end
		
	</fieldset>


<!--
flag_kebenaran_edit :::: $flag_kebenaran_edit
id_permohonan_kebenaran :::: $id_permohonan_kebenaran
-->



<input type="hidden" name="flag_kebenaran_edit" id="flag_kebenaran_edit"  value="$flag_kebenaran_edit" >    
<input type="hidden" name="user_id_kebenaran_edit" id="user_id_kebenaran_edit"  value="$user_id_kebenaran_edit">
<input type="hidden" name="id_permohonan_kebenaran" id="id_permohonan_kebenaran" value="$ID_PERMOHONAN"/>

<input type="hidden" name="user_id_temp" id="user_id_temp"   value="$!usid" >
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
         <input type="hidden" name="idPermohonan"/>
         <input type="hidden" name="idPermohonanSimati"/>
         <input type="hidden" name="idStatus"/>
         <input type="hidden" name="flagFromSenaraiFailSek8"/>
          <input type="hidden" name="flagForView"/>
         
         <!-- elly -->
         <input type="hidden" name="id_perbicaraan" value="$id_perbicaraan"/>
         <input type="hidden" name="idpermohonan"/>
         <input type="hidden" name="idpermohonansimati"/>
         <input type="hidden" name="tarikh_mohon" />
         <input type="hidden" name="id_status"/>
         <input type="hidden" name="id_Simati"/>		
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
check_length(document.${formName}.catatan_batal,'4000','catatan_batal_check','catatan_batal_num','normal','yes','keterangan');
function cmdSimpan_flag()
{
	SaveScrollXY(); 
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "simpanFlag";
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmBatalkanPermohonan";
	document.${formName}.cmdSimpan.value = "Sila Tunggu...";	
	document.${formName}.submit();

}
function search_main_data_sub(){
	SaveScrollXY();        
	document.${formName}.command.value = "cariMainSub";
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmBatalkanPermohonan";
	
	//doAjaxCall${formName}("cariMainSub");
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";	
	document.${formName}.submit();
}
function kosongkan_sub() {
	SaveScrollXY();        
	//document.${formName}.action = "";
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmBatalkanPermohonan";
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
	document.${formName}.action="?_portal_module=ekptg.view.ppk.FrmBatalkanPermohonan";
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