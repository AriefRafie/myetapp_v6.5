<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style>
<br>
<br>
<body onLoad="ResetScrollPosition();" >
<fieldset><legend><b>
 <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>Baru</blink></font></b>
                             </label>&nbsp;
Kemaskini Myid</b></legend>
		
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
                <td style="display:none" >  
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
         <font color="blue"> $list.NO_FAIL  </font>
         #else
         <font color="blue"> $list.NO_FAIL  </font>
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
        
     
     #if($!view_list_myid == "yes")
      #if($!headerppk.size()>0) 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr >    
    <td >  
    #parse("app/ppk/headerppk.jsp")
</td>
</tr>
</table>
     #end
     
     
     #if($!papar_list_simati.size()>0)
     <br>
     <fieldset>
     <legend>SIMATI <input name="cmdSimpanKPSimati" id="cmdSimpanKPSimati" value="Simpan" type="button" onClick="javascript:simpanSub('$id_fail_carian','cmdSimpanKPSimati')"></legend>
     
         <table width="80%"  cellpadding="1" cellspacing="2" border="0">
         <tr >
                    		   
                <td class="table_header" width="45%" valign="top"><b>NAMA SIMATI</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO KP BARU</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO KP LAMA</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO KP LAIN</b></td>
                              
                          
    		</tr>
           
    		
    		#foreach($list in $papar_list_simati)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
         #set($id_simati = "id_simati"+$list.BIL)   
         #set($no_kp_baru_simati = "no_kp_baru_simati"+$list.BIL)    
         #set($no_kp_lama_simati = "no_kp_lama_simati"+$list.BIL) 
         #set($no_kp_lain_simati = "no_kp_lain_simati"+$list.BIL)  
         
         #set($alert_baru_simati = "alert_baru_simati"+$list.BIL)         
         #set($alert_lama_simati = "alert_lama_simati"+$list.BIL) 
         #set($alert_lain_simati = "alert_lain_simati"+$list.BIL) 
         
         
        
        
         <tr  class="$row"    >
         <td valign="top"  >  
         <a href="javascript:paparFail('$list.ID_SIMATI')"  >   
         </a>     
         <font color="blue"> $list.NAMA_SIMATI</font>  
      <!--  $list.ID_SIMATI -->
         <input type="hidden" name="$id_simati" id="$id_simati" value="$list.ID_SIMATI"   >
           
           </td>
           <td valign="top"  >  
         
             
        <input name="$no_kp_baru_simati" type="text" id="$no_kp_baru_simati" value="$list.NO_KP_BARU" size="20"  maxlength="12"
        onKeyUp="check_format_alert_field(this,'$alert_baru_simati','baru');check_myid_simati('check_myid_kemaskinimyid','$alert_baru_simati');check_myid_temp('temp_alert_baru_simati')"
         onKeyDown="check_format_alert_field(this,'$alert_baru_simati','baru');check_myid_simati('check_myid_kemaskinimyid','$alert_baru_simati');check_myid_temp('temp_alert_baru_simati')"
         onBlur="check_format_alert_field(this,'$alert_baru_simati','baru');check_myid_simati('check_myid_kemaskinimyid','$alert_baru_simati');check_myid_temp('temp_alert_baru_simati')"
         >
           
        <div id="$alert_baru_simati" ></div>
        <div id="temp_alert_baru_simati" >$!alert_baru_simati_value</div>
       
      <!--  ::: $alert_baru_simati    -->   
         
           </td>
           
            <td valign="top" > 
         
        <input name="$no_kp_lama_simati" type="text" id="$no_kp_lama_simati" value="$list.NO_KP_LAMA" size="20"  maxlength="8" 
        
         onKeyUp="check_format_alert_field(this,'$alert_lama_simati','lama');check_myid_simati('check_myid_kemaskinimyid','$alert_lama_simati');check_myid_temp('temp_alert_lama_simati')"
         onKeyDown="check_format_alert_field(this,'$alert_lama_simati','lama');check_myid_simati('check_myid_kemaskinimyid','$alert_lama_simati');check_myid_temp('temp_alert_lama_simati')"
         onBlur="check_format_alert_field(this,'$alert_lama_simati','lama');check_myid_simati('check_myid_kemaskinimyid','$alert_lama_simati');check_myid_temp('temp_alert_lama_simati')"
        
          > 
          
        <div id="$alert_lama_simati" ></div>  
       <div id="temp_alert_lama_simati" >$!alert_lama_simati_value</div>    
        
           </td>
           
            <td valign="top" > 
      
        <input name="$no_kp_lain_simati" type="text" id="$no_kp_lain_simati" value="$list.NO_KP_LAIN" size="20"  maxlength="20" 
        
        onKeyUp="check_myid_simati('check_myid_kemaskinimyid','$alert_lain_simati');check_myid_temp('temp_alert_lain_simati')"
         onKeyDown="check_myid_simati('check_myid_kemaskinimyid','$alert_lain_simati');check_myid_temp('temp_alert_lain_simati')"
         onBlur="check_myid_simati('check_myid_kemaskinimyid','$alert_lain_simati');check_myid_temp('temp_alert_lain_simati')"
          > 
           <div id="$alert_lain_simati" ></div>           
           <div id="temp_alert_lain_simati" >$!alert_lain_simati_value</div>      
                 
           </td>
           
           </tr>
       #end
           </table>
     
     </fieldset>
     #end   
     
     #if($!papar_list_pemohon.size()>0)
     <br>
     <fieldset>
     <legend>PEMOHON <input name="cmdSimpanKPPemohon" id="cmdSimpanKPPemohon" value="Simpan" type="button" onClick="javascript:simpanSub('$id_fail_carian','cmdSimpanKPPemohon')"></legend>
     
     <table width="80%"  cellpadding="1" cellspacing="2" border="0">
         <tr >
                    		   
                <td class="table_header" width="45%" valign="top"><b>NAMA PEMOHON</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO KP BARU</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO KP LAMA</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO KP LAIN</b></td>
                              
                          
    		</tr>
           
    		
    		#foreach($list in $papar_list_pemohon)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
         #set($id_pemohon = "id_pemohon"+$list.BIL)   
         #set($no_kp_baru_pemohon = "no_kp_baru_pemohon"+$list.BIL)    
         #set($no_kp_lama_pemohon = "no_kp_lama_pemohon"+$list.BIL) 
         #set($no_kp_lain_pemohon = "no_kp_lain_pemohon"+$list.BIL)  
         #set($alert_baru_pemohon = "alert_baru_pemohon"+$list.BIL) 
         
         #set($alert_lama_pemohon = "alert_lama_pemohon"+$list.BIL)  
        
        
         <tr  class="$row"    >
         <td valign="top"  >  
         <a href="javascript:paparFail('$list.ID_PEMOHON')"  >   
         </a>     
         <font color="blue"> $list.NAMA_PEMOHON</font>  
        <!-- $list.ID_PEMOHON -->
         <input type="hidden" name="$id_pemohon" id="$id_pemohon" value="$list.ID_PEMOHON"   >
         #set($id_pemohon_checked = $list.ID_PEMOHON)  
           </td>
           <td valign="top"  >  
         
              
        <input name="$no_kp_baru_pemohon" type="text" id="$no_kp_baru_pemohon" value="$list.NO_KP_BARU" size="20"  maxlength="12"  
         onKeyUp="sama_pemohon('$no_kp_baru_pemohon','$no_kp_lama_pemohon','$no_kp_lain_pemohon');check_format_alert_field(this,'$alert_baru_pemohon','baru')"
         onKeyDown="sama_pemohon('$no_kp_baru_pemohon','$no_kp_lama_pemohon','$no_kp_lain_pemohon');check_format_alert_field(this,'$alert_baru_pemohon','baru')"
         onBlur="sama_pemohon('$no_kp_baru_pemohon','$no_kp_lama_pemohon','$no_kp_lain_pemohon');check_format_alert_field(this,'$alert_baru_pemohon','baru')"
          > 
        <div id="$alert_baru_pemohon" ></div>
       
      <!--  ::: $alert_baru_pemohon    -->   
         
           </td>
           
            <td valign="top" > 
         
          
         
         
        <input name="$no_kp_lama_pemohon" type="text" id="$no_kp_lama_pemohon" value="$list.NO_KP_LAMA" size="20"  maxlength="8"  
          onKeyUp="sama_pemohon('$no_kp_baru_pemohon','$no_kp_lama_pemohon','$no_kp_lain_pemohon');check_format_alert_field(this,'$alert_lama_pemohon','lama')"
         onKeyDown="sama_pemohon('$no_kp_baru_pemohon','$no_kp_lama_pemohon','$no_kp_lain_pemohon');check_format_alert_field(this,'$alert_lama_pemohon','lama')"
         onBlur="sama_pemohon('$no_kp_baru_pemohon','$no_kp_lama_pemohon','$no_kp_lain_pemohon');check_format_alert_field(this,'$alert_lama_pemohon','lama')"
         >  
         <div id="$alert_lama_pemohon" ></div>       
        
           </td>
           
            <td valign="top" > 
      
        <input name="$no_kp_lain_pemohon" type="text" id="$no_kp_lain_pemohon" value="$list.NO_KP_LAIN" size="20"  maxlength="20"  
            onKeyUp="sama_pemohon('$no_kp_baru_pemohon','$no_kp_lama_pemohon','$no_kp_lain_pemohon');"
         onKeyDown="sama_pemohon('$no_kp_baru_pemohon','$no_kp_lama_pemohon','$no_kp_lain_pemohon');"
         onBlur="sama_pemohon('$no_kp_baru_pemohon','$no_kp_lama_pemohon','$no_kp_lain_pemohon');"
         >       
                 
           </td>
           
           </tr>
       #end
           </table>
     
     </fieldset>  
     #end
     
     #if($!papar_list_ob.size()>0)
     <br>
     <fieldset>
     <legend>WARIS <input name="cmdSimpanKPOb" id="cmdSimpanKPOb" value="Simpan" type="button" onClick="javascript:simpanSub('$id_fail_carian','cmdSimpanKPOb')"></legend>
     <table width="80%"  cellpadding="1" cellspacing="2" border="0">
         <tr >
                    		   
                <td class="table_header" width="45%" valign="top"><b>NAMA WARIS</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO KP BARU</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO KP LAMA</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO KP LAIN</b></td>
                              
                          
    		</tr>
           
    		
    		#foreach($list in $papar_list_ob)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
                
         #set($id_ob = "id_ob"+$list.BIL)   
         #set($id_pemohon_ob = "id_pemohon_ob"+$list.BIL)
         #set($no_kp_baru_ob = "no_kp_baru_ob"+$list.BIL)    
         #set($no_kp_lama_ob = "no_kp_lama_ob"+$list.BIL) 
         #set($no_kp_lain_ob = "no_kp_lain_ob"+$list.BIL)  
         #set($alert_baru_ob = "alert_baru_ob"+$list.BIL) 
         
         #set($alert_lama_ob = "alert_lama_ob"+$list.BIL)  
         
        
         <tr  class="$row"    >
         <td valign="top"  >  
         <a href="javascript:paparFail('$list.ID_OB')"  >   
         </a>     
         <font color="blue"> $list.NAMA_OB</font>  
        <!--          $list.ID_OB -->
         <input type="hidden" name="$id_ob" id="$id_ob" value="$list.ID_OB"   >
         <input type="hidden" name="$id_pemohon_ob" id="$id_pemohon_ob" value="$list.ID_PEMOHON"   >
           
           </td>
            <td valign="top"  >  
         
         #if($id_pemohon_checked == $list.ID_PEMOHON)
         #set($sama_kp_baru = $no_kp_baru_ob)
         #set($sama_kp_lama = $no_kp_lama_ob) 
         #set($sama_kp_lain = $no_kp_lain_ob)  
         #set($sama_kp_alert = $alert_baru_ob)         
         #else
         #set($sama_kp_baru = "")
         #set($sama_kp_lama = "")
         #set($sama_kp_lain = "")   
         #set($sama_kp_alert = "")       
         #end
        <!-- :::: $sama_kp_baru -->
          
        <input name="$no_kp_baru_ob" type="text" id="$no_kp_baru_ob" value="$list.NO_KP_BARU" size="20"  maxlength="12"  
         onKeyUp="sama_ob();check_format_alert_field(this,'$alert_baru_ob','baru')"
         onKeyDown="sama_ob();check_format_alert_field(this,'$alert_baru_ob','baru')"
         onBlur="sama_ob();check_format_alert_field(this,'$alert_baru_ob','baru')"
         > 
        <div id="$alert_baru_ob" ></div>
        
      <!--  ::: $alert_baru_ob    -->   
         
           </td>
           
            <td valign="top" > 
      
        <input name="$no_kp_lama_ob" type="text" id="$no_kp_lama_ob" value="$list.NO_KP_LAMA" size="20"  maxlength="8"  
         onKeyUp="sama_ob();check_format_alert_field(this,'$alert_lama_ob','lama')"
         onKeyDown="sama_ob();check_format_alert_field(this,'$alert_lama_ob','lama')"
         onBlur="sama_ob();check_format_alert_field(this,'$alert_lama_ob','lama')"
         >     
         
         <div id="$alert_lama_ob" ></div>    
               
           </td>
           
            <td valign="top" > 
        
        <input name="$no_kp_lain_ob" type="text" id="$no_kp_lain_ob" value="$list.NO_KP_LAIN" size="20"  maxlength="20"  
        onKeyUp="sama_ob();"
         onKeyDown="sama_ob();"
         onBlur="sama_ob();"
         >       
               
           </td>
           
           </tr>
       #end
           </table>
     </fieldset> 
     #end   
      
     #end
        

<input type="hidden" name="id_fail_carian" id="id_fail_carian" value="$id_fail_carian" /> 
<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />  
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'> 
<input type="hidden" name="sama_kp_baru" id="sama_kp_baru" value="$!sama_kp_baru"   >
<input type="hidden" name="sama_kp_lama" id="sama_kp_lama" value="$!sama_kp_lama"   >
<input type="hidden" name="sama_kp_lain" id="sama_kp_lain" value="$!sama_kp_lain"   >
<input type="hidden" name="sama_kp_alert" id="sama_kp_alert" value="$!sama_kp_alert"   >


#parse("app/ppk/headerppk_script.jsp")

#if($!papar_list_ob.size()>0)	
<script >
check_format_kp_baru('ob','$!papar_list_ob.size()');
check_format_kp_lama('ob','$!papar_list_ob.size()');
</script>
#end

#if($!papar_list_pemohon.size()>0)	
<script >
check_format_kp_baru('pemohon','$!papar_list_pemohon.size()');
check_format_kp_lama('pemohon','$!papar_list_pemohon.size()');
</script>
#end

#if($!papar_list_simati.size()>0)	
<script >
check_format_kp_baru('simati','$!papar_list_simati.size()');
check_format_kp_lama('simati','$!papar_list_simati.size()');
</script>
#end



   
<script >


	
function search_main_data_sub()
{
	SaveScrollXY();  	
	//doAjaxCall${formName}("cariMainSub");
	document.${formName}.command.value = "cariMainSub";
	document.${formName}.submit();
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";
}
function kosongkan_sub() {
SaveScrollXY();        
document.${formName}.action = "";
document.${formName}.txtNoFailSub.focus();
//doAjaxCall${formName}("kosongkan");
document.${formName}.command.value = "kosongkan";
	document.${formName}.submit();
}
function paparFail(id_fail)
{
SaveScrollXY();        
document.${formName}.id_fail_carian.value = id_fail;
//doAjaxCall${formName}("paparSub");
document.${formName}.command.value = "paparSub";
	document.${formName}.submit();
}
function simpanSub(id_fail,nama_butang)
{

var total_alert = 0;
if(document.${formName}.alert_temp.length == null)
{
	if(document.${formName}.alert_temp.value == 'valid')
	{
	total_alert = total_alert+1;
	}
} 
else 
{
	for (i = 0; i < document.${formName}.alert_temp.length; i++)
	{
		if(document.${formName}.alert_temp[i].value == 'valid')
		{
		total_alert = total_alert+1;
		}
	}
}



if(total_alert>0)
{
alert("Sila Pastikan Myid Lengkap Diisi");
}
else
{
SaveScrollXY();        
document.${formName}.id_fail_carian.value = id_fail;
//doAjaxCall${formName}("simpanSub");
document.${formName}.command.value = "simpanSub";
	document.${formName}.submit();
document.getElementById(nama_butang).value = "Sila Tunggu...";

}

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


function check_format_kp_baru(jenis_kp,saiz)
{
//alert("SAIZ:"+saiz);
var saizint = parseInt(saiz);

if(saizint == 1)
{  
	var field = document.getElementById('no_kp_baru_'+jenis_kp+saizint); 		  
	//alert("no_kp_ob = 1 :"+field.value)
	check_format_alert(field.value,'alert_baru_'+jenis_kp+saizint);
}
else
{
	  for (i = 1; i <= saizint; i++)
	  {
	  var field = document.getElementById('no_kp_baru_'+jenis_kp+i,'baru');
	  //alert("no_kp_ob > 1 ("+i+"):"+field.value)	
	  //alert("no_kp_ob > 1 ("+i+"):"+'alert_baru_'+jenis_kp+i)	
	  //alert("alert_baru_"+jenis_kp+i);
	  check_format_alert(field.value,'alert_baru_'+jenis_kp+i,'baru');
	  //$jquery("#alert_baru_"+jenis_kp+i).html("<font color = 'red'>"+"alert_baru_"+jenis_kp+i+"</font>");
	  } 	 	   
}
}

function check_format_kp_lama(jenis_kp,saiz)
{
//alert("SAIZ:"+saiz);
var saizint = parseInt(saiz);

if(saizint == 1)
{  
	var field = document.getElementById('no_kp_lama_'+jenis_kp+saizint); 		  
	//alert("no_kp_ob = 1 :"+field.value)
	check_format_alert(field.value,'alert_lama_'+jenis_kp+saizint,'lama');
}
else
{
	  for (i = 1; i <= saizint; i++)
	  {
	  var field = document.getElementById('no_kp_lama_'+jenis_kp+i);	 
	  check_format_alert(field.value,'alert_lama_'+jenis_kp+i,'lama');	  
	  } 	 	   
}
}
  
function check_format_alert(value,div_id,jenis)
{
	
if(jenis == "baru")
{	
var ayat_alert = "";
if(value!="")
{
	if(value.length != 12)
	{
	ayat_alert = "Myid Tidak Lengkap";	
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>"+ayat_alert+" </font>");
	}
	else
	{
	ayat_alert = "";
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}	
}
else
{
return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
}
}


if(jenis == "lama")
{	
var ayat_alert = "";
if(value!="")
{
	if(value.length != 8 && value.length != 7)
	{
		if(value!="TDK")
		{	
		ayat_alert = "Myid Tidak Lengkap";	
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>"+ayat_alert+" </font>");
		}
		else
		{
		ayat_alert = "";
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
		}
	}
	else
	{
	ayat_alert = "";
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}	
}
else
{
return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
}
}

}

function check_format_alert_field(fil,div_id,jenis)
{
	
if(jenis=="baru")
{
	if(fil.value!="")
	{
		if(fil.value.length != 12)
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>Myid Tidak Lengkap</font>");
		}
		else
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
		}	
	}
	else
	{
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
	}
}

if(jenis=="lama")
{
	
	if(fil.value!="")
	{
		if(fil.value.length != 8 && fil.value.length != 7)
		{
			if(fil.value!="TDK")
			{
			return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='valid'  ><font color = 'red'>Myid Tidak Lengkap</font>");	
			}else
			{
			return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
			}
		}
		else
		{
		return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
		}	
	}
	else
	{
	return $jquery("#"+div_id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");	
	}
}

	
}


function check_myid_temp(id)
{

	return $jquery("#"+id).html("<input type='hidden' name='alert_temp' id='alert_temp' value='xvalid'  >");
}

function sama_pemohon(field_pemohon_baru,field_pemohon_lama,field_pemohon_lain)
{
	
	var field_kp_alert = document.getElementById('sama_kp_alert').value;
	var field_ob_kp_baru = document.getElementById('sama_kp_baru').value;
	var field_ob_kp_lama = document.getElementById('sama_kp_lama').value;
	var field_ob_kp_lain = document.getElementById('sama_kp_lain').value;
	
	var field_pemohon_baru_temp = document.getElementById(field_pemohon_baru).value;
	var field_pemohon_lama_temp = document.getElementById(field_pemohon_lama).value;
	var field_pemohon_lain_temp = document.getElementById(field_pemohon_lain).value;
	
	if(field_ob_kp_baru!="")
	{
	document.getElementById(field_ob_kp_baru).value = field_pemohon_baru_temp.substring(0,field_pemohon_baru_temp.length);
	check_format_alert_field(document.getElementById(field_ob_kp_baru),field_kp_alert)
	}
	
	if(field_ob_kp_lama!="")
	{
	document.getElementById(field_ob_kp_lama).value = field_pemohon_lama_temp.substring(0,field_pemohon_lama_temp.length);
	}
	if(field_ob_kp_lain!="")
	{
	document.getElementById(field_ob_kp_lain).value = field_pemohon_lain_temp.substring(0,field_pemohon_lain_temp.length);
	}
	
}


function sama_ob()
{
	
	
	var field_ob_kp_baru = document.getElementById('sama_kp_baru').value;
	var field_ob_kp_lama = document.getElementById('sama_kp_lama').value;
	var field_ob_kp_lain = document.getElementById('sama_kp_lain').value;
	
	var field_pemohon_baru_temp = document.getElementById(field_ob_kp_baru).value;
	var field_pemohon_lama_temp = document.getElementById(field_ob_kp_lama).value;
	var field_pemohon_lain_temp = document.getElementById(field_ob_kp_lain).value;
	
	
	if(field_pemohon_baru_temp!="")
	{
	document.getElementById('no_kp_baru_pemohon1').value = field_pemohon_baru_temp.substring(0,field_pemohon_baru_temp.length);
	check_format_alert_field(document.getElementById('no_kp_baru_pemohon1'),'alert_baru_pemohon1')
	}
	
	if(field_pemohon_lama_temp!="")
	{
	document.getElementById('no_kp_lama_pemohon1').value = field_pemohon_lama_temp.substring(0,field_pemohon_lama_temp.length);
	}
	
	if(field_pemohon_lain_temp!="")
	{
	document.getElementById('no_kp_lain_pemohon1').value = field_pemohon_lain_temp.substring(0,field_pemohon_lain_temp.length);
	}
	
	
}

function check_myid_simati(action,div_alert)
{	
	/*url = "../servlet/ekptg.view.ppk.PendaftaranCheck";	
	actionName = action;
	target = div_alert;
	doAjaxUpdater(document.${formName}, url, target, actionName);*/
}


</script>