<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style>

<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
#parse("css/eTapp_PPK.css")
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





</fieldset >
<br />

<fieldset style="display:none" ><legend>

<b>Semakan Permohonan</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">No Fail : </td>
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
                <td class="table_header" width="5%"><b></b></td>
      		    <td class="table_header" width="10%"><b>ID FAIL</b></td>
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
                <td  >  
         <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="white"> Papar  </font>
         #else
         <font color="blue"> Papar  </font>
         #end  
         
              </a>   
                </td>
      			
      		    <td  >  
                  $list.ID_FAIL   
                </td>
                
                <td  > 
                $list.NO_FAIL  
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
    
    <table width="97%"  cellpadding="1" cellspacing="2" border="0" >
    <tr>
    <td><fieldset>
		<legend><b>Senarai Status Permohonan</b></legend>
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%"><strong>Bil</strong></td>
              <td width="95%"><strong>Status</strong></td>
            </tr>
          #set ($list = "")
          #foreach ($list in $SenaraiFail)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          <tr>
            <td class="$row">$list.bil</td>
            <td class="$row">
            #if($list.idPermohonan == '')
            
            $list.keterangan
            #else
            <a href="javascript:papar('$list.idPermohonan','$list.idStatus','$list.bil','$list.idPermohonanSimati','$list.tarikhMohon','$list.flagjenisfail','$list.seksyen','$list.idSimati')" class="style1"><font color="blue">$list.keterangan</font></a>
            #end
            </td>
          </tr>
          #end
          </tbody>
        </table>
		</fieldset>
	</td>
  </tr>
    </table>

      
		
    #end
     
   
		
	</fieldset>
    
    
     
   <!--
    
latest id_status ::::::::::: $!latest_idstatus
role :::::: $!role
user_name :::: $!user_name
usid :::: $!usid
-->

#if($!latest_idstatus == "21")
#if($!role == "adminppk")

<fieldset>
<legend title="Kebenaran Pengemaskinian Maklumat Bagi Fail Berstatus SELESAI"><b>Kebenaran Pengemaskinian Maklumat</b></legend>
 
 <table width="97%"  cellpadding="1" cellspacing="2" border="0" >
 <tr>
 <td colspan="3">
 <font color="blue">
 <i>Ruanganan ini bertujuan untuk <b>Pegawai</b> memberi kebenaran bagi membuat pengemaskinian pada Fail yang berstatus <b>SELESAI</b></i>
 </font>
 </td>
 </tr>
 <tr>
      			<td scope="row" width="28%">Pengesahan Kebenaran Pengemaskinian Fail</td>
                <td width="1%">:              
                </td>
      			<td width="67%">
                
                #if($flag_kebenaran_edit == "1")
                
                   #if($usid == $user_id_kebenaran_edit)
                        #if($!flag_kebenaran_edit == "1")
                        #set($check = "checked")
                        #end
                        <input  type="checkbox" name="check_flag_kebenaran_edit"  id="check_flag_kebenaran_edit" $check onClick="check_flag()" >
                   
                   #else
                        <font color="blue">Dibenarkan</font>               
                        #if($!flag_kebenaran_edit == "1")
                        #set($check = "checked")
                        #end
                        <input  type="checkbox" name="check_flag_kebenaran_edit" style="display:none" id="check_flag_kebenaran_edit" $check onClick="check_flag()" >
                   #end
                #else
                    #set($check = "")
                    #if($!flag_kebenaran_edit == "1")
                    #set($check = "checked")
                    #end
                     <input type="checkbox" name="check_flag_kebenaran_edit" id="check_flag_kebenaran_edit" $check onClick="check_flag()" >
                 
                #end 
                         
                </td>
    		</tr>
             #if($!flag_kebenaran_edit == "1")
                          
            
    		 <tr  id="tr_nama">
      			<td scope="row">Kebenaran Dikeluarkan Oleh</td>
                <td>
                :                              
                </td>                
      			<td>        
                <font color="blue">$!nama_user_yg_beri_kebenaran</font>
                </td>
    		 </tr>
             
              <tr   id="tr_catatan" >
      			<td scope="row" valign="top">Catatan</td>
                <td valign="top">
                :                              
                </td>                
      			<td  valign="top">
                  #if($usid == $user_id_kebenaran_edit)
             <textarea name="catatan_kebenaran_edit" id="catatan_kebenaran_edit" cols="50"   rows="4"  placeholder="Sila Masukkan Catatan..."         
         onBlur="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');"  
         onKeyup="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');" 
         onKeydown="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');"                    
           >$catatan_kebenaran_edit</textarea>
           #else
           <div  style="width:50%"><font color="blue">$!catatan_kebenaran_edit</font></div>          
           #end
           
                 
         <div><span id="catatan_kebenaran_edit_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
        
         <div id="catatan_kebenaran_edit_check" class="alert_msg" ></div> 
         </td>
    		 </tr> 
             
            #else
            
          
            <tr  id="tr_nama" style="display:none" >
      			<td scope="row">Kebenaran Dikeluarkan Oleh</td>
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
                
             <textarea name="catatan_kebenaran_edit" id="catatan_kebenaran_edit" cols="50"   rows="4"  placeholder="Sila Masukkan Catatan..."         
         onBlur="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');"  
         onKeyup="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');" 
         onKeydown="check_length(this,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');"                    
           >$catatan_kebenaran_edit</textarea>
           
                 
         <div><span id="catatan_kebenaran_edit_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
        
         <div id="catatan_kebenaran_edit_check" class="alert_msg" ></div> 
         </td>
    		 </tr>
            
            
            #end
            <tr>
 <td colspan="3">
  #if($flag_kebenaran_edit == "1") 
       #if($usid == $user_id_kebenaran_edit)
       <input name="cmdSimpan" id="cmdSimpan" value="Simpan" type="button" onClick="javascript:cmdSimpan_flag()">
       #end
  #else
 <input name="cmdSimpan" id="cmdSimpan" value="Simpan" type="button" onClick="javascript:cmdSimpan_flag()">
 #end
 
 </td>
 </tr>    		
		</table>
        
        
        
        
</fieldset>


#else

 
    <fieldset>
<legend title="Kebenaran Pengemaskinian Maklumat Bagi Fail Berstatus SELESAI"><b>Kebenaran Pengemaskinian Maklumat</b></legend>
 
 <table width="97%"  cellpadding="1" cellspacing="2" border="0" >
 <tr>
 <td colspan="4">
 <font color="blue">
 <i>Ruanganan ini bertujuan untuk <b>Pegawai</b> memberi kebenaran bagi membuat pengemaskinian pada Fail yang berstatus <b>SELESAI</b></i>
 </font>
 </td>
 </tr>
 <tr>
      			<td scope="row" width="28%">Pengesahan Kebenaran Pengemaskinian Fail</td>
                <td width="1%">:              
                </td>
      			<td width="67%" colspan="2">
                
                #if($flag_kebenaran_edit == "1") 
                <font color="blue">Dibenarkan</font>               
                #else
                <font color="red">Tidak Dibenarkan</font> 
                #end
               
                         
                </td>
    		</tr>
            #if($!flag_kebenaran_edit == "1")  
    		<tr >
      			<td scope="row">Kebenaran Dikeluarkan Oleh</td>
                <td>
                :                              
                </td>                
      			<td colspan="2">
                               
                <font color="blue">$!nama_user_yg_beri_kebenaran</font>
                
                
                </td>
    		</tr>
            <tr >
      			<td scope="row" valign="top">Catatan</td>
                <td valign="top">
                :                              
                </td>                
      			<td  colspan="2">
                               
                <div  style="width:50%"><font color="blue">$!catatan_kebenaran_edit</font></div>  
                
                
                </td>
                
    		</tr>
            #end
                		
		</table>
        
        
        
        
</fieldset>

#end

#end
   


<!--
flag_kebenaran_edit :::: $flag_kebenaran_edit
id_permohonan_kebenaran :::: $id_permohonan_kebenaran
-->
<input type="hidden" name="flag_kebenaran_edit" id="flag_kebenaran_edit"  value="$flag_kebenaran_edit" >    
<input type="hidden" name="user_id_kebenaran_edit" id="user_id_kebenaran_edit"  value="$user_id_kebenaran_edit">
<input type="hidden" name="id_permohonan_kebenaran" id="id_permohonan_kebenaran" value="$id_permohonan_kebenaran"/>

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


#if($!latest_idstatus == "21")
#if($!role == "adminppk")
<script type="text/javascript">
check_flag();
</script>
#end
#end

<script type="text/javascript">
check_length(document.${formName}.catatan_kebenaran_edit,'4000','catatan_kebenaran_edit_check','catatan_kebenaran_edit_num','normal','yes','keterangan');
function cmdSimpan_flag()
{
	SaveScrollXY(); 
	document.${formName}.command.value = "simpanFlag";
	document.${formName}.action = "";
	document.${formName}.cmdSimpan.value = "Sila Tunggu...";	
	document.${formName}.submit();

}
function search_main_data_sub(){
	SaveScrollXY();        
	document.${formName}.command.value = "cariMainSub";
	document.${formName}.action = "";
	//doAjaxCall${formName}("cariMainSub");
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";	
	document.${formName}.submit();
}
function kosongkan_sub() {
	SaveScrollXY();        
	document.${formName}.action = "";
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
	document.${formName}.action = "";
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
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData";
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
	//alert("AAA"+document.${formName}.check_flag_kebenaran_edit.checked);
	if(document.${formName}.check_flag_kebenaran_edit.checked == true)
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

</script>