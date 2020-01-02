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
                             </label>
 &nbsp;
Kemaskini Bayaran Urusan Pusaka Kecil</b></legend>
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
        
     
     #if($!view_list_bayaran == "yes")
     
     #if($!headerppk.size()>0) 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr >
    
    <td >  
    
   
    #parse("app/ppk/headerppk.jsp")
   

</td>
</tr>
</table>
     #end
     
     #if($!papar_list_bayaran.size()>0)
     <br>
     <fieldset>
     <legend>SENARAI BAYARAN 
     
     #if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")
     <input name="cmdSimpanKPSimati" id="cmdSimpanKPSimati" value="Simpan" type="button" onClick="javascript:simpanSub('$id_fail_carian','cmdSimpanKPSimati')">
     <input id="cmdHapus" name="cmdHapus" type="button"  style="display:none" value="Hapus" onClick="javascript:hapus()" />
     #end
    
     </legend>
     
         <table width="85%"  cellpadding="1" cellspacing="2" border="0">
         <tr >
                    		   
                <td class="table_header" width="45%" valign="top"><b>JENIS BAYARAN</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO. RESIT</b></td>
                <td class="table_header" width="15%" valign="top"><b>JUMLAH BAYARAN</b></td>
                <td class="table_header" width="15%" valign="top"><b>TARIKH BAYARAN</b></td>
                   <td class="table_header" width="10%" valign="top">           
   <input type="checkbox" name="all1_delete_data"    id="all1_delete_data" onClick="doCheckall1_delete_data()" title="Semak untuk pilih semua" />                 
                 </td> 
                              
                          
    		</tr>
           
    		
    		#foreach($list in $papar_list_bayaran)
    		 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
                
           
         #set($ID_BAYARAN = "ID_BAYARAN"+$list.BIL)  
         #set($ID_JENISBAYARAN = "ID_JENISBAYARAN"+$list.BIL) 
         #set($KETERANGAN = "KETERANGAN"+$list.BIL)
         #set($NO_RESIT = "NO_RESIT"+$list.BIL)
         #set($JUMLAH_BAYARAN = "JUMLAH_BAYARAN"+$list.BIL)  
         #set($TARIKH_BAYARAN = "TARIKH_BAYARAN"+$list.BIL) 
         
         
           
         
       
         
         
        
        
         <tr  class="$row"    >
         
         <td valign="top"  >
          <input type="hidden" name="$ID_BAYARAN" id="$ID_BAYARAN" value="$list.ID_BAYARAN"   >     
         
         
        
               			 <select  class="autoselect" name="$ID_JENISBAYARAN" id="$ID_JENISBAYARAN" >	   	 
						   		#foreach ( $y in $papar_list_jenisbayaran )
                            
						   		#if( $y.ID_JENISBAYARAN == $list.ID_JENISBAYARAN )
						   			#set ( $selected_aktif = "selected" )
						   		#else
						   			#set ( $selected_aktif = "" )
						   		#end                       
						   	<option value="$y.ID_JENISBAYARAN" $!selected_aktif >                            
                            $y.JENIS_BAYARAN                            
                            </option>
						   		#end
                                
							</select> 
                            
                                  
                  
         </td>
           <td valign="top"  >  
           <input name="$NO_RESIT" type="text" id="$NO_RESIT" value="$list.NO_RESIT" size="20" maxlength="20"   >       
           </td>
           
            <td valign="top" > 
             <input name="$JUMLAH_BAYARAN" type="text" id="$JUMLAH_BAYARAN" onBlur="validateModal(this,this.value,'$list.JUMLAH_BAYARAN');" style="text-align:right" value="$list.JUMLAH_BAYARAN" size="20" maxlength="12"   >    
        </td>
           
            <td valign="top" > 
               
                    <input name="$TARIKH_BAYARAN" type="text" id="$TARIKH_BAYARAN" value="$list.TARIKH_BAYARAN_CONVERT" size="10" maxlength="10"     onBlur="check_date(this);validateTarikh(this,this.value)"  />
                                  <a href="javascript:displayDatePicker('$TARIKH_BAYARAN',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")  
           </td>
           <TD valign="top">
           <input type="checkbox" name="ids1_delete_data" id="ids1_delete_data"  onClick="doUpdateCheckall1_delete()" value="$!list.ID_BAYARAN" >
           </TD>
           
           </tr>
       #end
           </table>
           
           
        
            
            
        
     
     </fieldset>
     #end
        <br>
        <font color="blue"><b>Penambahan Maklumat Bayaran</b></font>
        
     
     <table width="85%"  cellpadding="1" cellspacing="2" border="0">
<tr >
      			
      		    <td class="table_header" width="45%" valign="top"><b>JENIS BAYARAN</b></td>
                <td class="table_header" width="15%" valign="top"><b>NO. RESIT</b></td>
                <td class="table_header" width="15%" valign="top"><b>JUMLAH BAYARAN</b></td>
                <td class="table_header" width="15%" valign="top"><b>TARIKH BAYARAN</b></td>
                <td class="table_header" width="10%" valign="top"></td>
                          
    		</tr>
            
            <tr class="row">
      			
      		    <td class="row" valign="top" >
                <font color="red">*</font>
                <select  class="autoselect" name="ID_JENISBAYARAN_ADD" id="ID_JENISBAYARAN_ADD" >	 
                <option value="" >SILA PILIH STATUS</option>    	 
						   		#foreach ( $y in $papar_list_jenisbayaran )
                                        
						   	<option value="$y.ID_JENISBAYARAN" >                            
                            $y.JENIS_BAYARAN                           
                            </option>
						   		#end
                               
							</select>
                </td>
                <td valign="top"  >  
           <input name="NO_RESIT_ADD" type="text" id="NO_RESIT_ADD"  size="20" maxlength="20"   >       
           </td>
           
            <td valign="top" > 
            #set($JUMLAH_BAYARAN_ADD = "")
             <input name="JUMLAH_BAYARAN_ADD" type="text" id="JUMLAH_BAYARAN_ADD" onBlur="validateModal(this,this.value,'$JUMLAH_BAYARAN_ADD');" style="text-align:right" value="$JUMLAH_BAYARAN_ADD" size="20" maxlength="12"   >    
        </td>
           
            <td valign="top" > 
               
                    <input name="TARIKH_BAYARAN_ADD" type="text" id="TARIKH_BAYARAN_ADD"  size="10" maxlength="10"     onBlur="check_date(this);validateTarikh(this,this.value)"  />
                                  <a href="javascript:displayDatePicker('TARIKH_BAYARAN_ADD',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")  
           </td>
                <td>  
                #if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N")          
                <input name="cmdTambahData" id="cmdTambahData" value="Tambah" type="button" onClick="javascript:tambah()">
                #end
                </td>
                
                
                          
    		</tr>
            </table>
     
     #end 
     
      #if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "Y")
     <table width="100%"  cellpadding="1" cellspacing="2" border="0">
	     <tr>
	     	<td align=center>  <input name="kembali" id="kembali" value="Kembali" type="button" onClick="javascript:history.back();"> </td>
	     </tr>
     </table>
     #end
     
     
     
     

<input type="hidden" name="id_fail_carian" id="id_fail_carian" value="$id_fail_carian" /> 

<!--ScrollX :  --><input type="hidden" id="ScrollX" name='ScrollX'  />
<!--ScrollY :  --><input type="hidden" id="ScrollY" name='ScrollY'  />  



#parse("app/ppk/headerppk_script.jsp")
   
<script >


	
function search_main_data_sub()
{
	SaveScrollXY();  	
	//doAjaxCall${formName}("cariMainSub");
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKemaskiniBayaranPusaka";  
	document.${formName}.command.value = "cariMainSub";
	document.${formName}.submit();
	document.${formName}.cmdSemakSub.value = "Sila Tunggu...";
	
}
function kosongkan_sub() {
SaveScrollXY();        

document.${formName}.txtNoFailSub.focus();
//doAjaxCall${formName}("kosongkan");
document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKemaskiniBayaranPusaka";  
document.${formName}.command.value = "kosongkan";
document.${formName}.submit();
}
function paparFail(id_fail)
{
SaveScrollXY();        
document.${formName}.id_fail_carian.value = id_fail;
//doAjaxCall${formName}("paparSub");
document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKemaskiniBayaranPusaka";  
document.${formName}.command.value = "paparSub";
document.${formName}.submit();
}


function simpanSub(id_fail,nama_butang)
{


SaveScrollXY();        
document.${formName}.id_fail_carian.value = id_fail;
//doAjaxCall${formName}("simpanSub");
document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKemaskiniBayaranPusaka";  
document.${formName}.command.value = "simpanSub";
document.${formName}.submit();
document.getElementById(nama_butang).value = "Sila Tunggu...";


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
	
	if (document.${formName}.all1_delete_data.checked == true)
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


function doUpdateCheckall1_delete(){  
var c = 0;
var x = 0;

if(document.${formName}.ids1_delete_data.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1_delete_data.length; i++)
	  {
      if (document.${formName}.ids1_delete_data[i].checked == false)
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
if (document.${formName}.ids1_delete_data.checked == false)
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
	  document.${formName}.all1_delete_data.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1_delete_data.checked = true;
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

function validateModal(elmnt,content,content2) {
 //   alert(content)	
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != "")
	{
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	}
	else
	{
	elmnt.value ="";
	return;
	}
	
}

function hapus(){
input_box = confirm("Adakah anda pasti? Data akan dihapuskan dari Pengkalan Data");
	if (input_box == true) 
	{	
	SaveScrollXY();        
	
	//doAjaxCall${formName}("hapusSub");
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKemaskiniBayaranPusaka";  
	document.${formName}.command.value = "hapusSub";
	document.${formName}.submit();
	document.${formName}.cmdHapus.value="Sila Tunggu...";
	}
}

function tambah()
{
	
	if(document.${formName}.ID_JENISBAYARAN_ADD.value == "")
	{
		alert("Sila Pastikan Jenis Bayaran dipilih");
		document.${formName}.ID_JENISBAYARAN_ADD.focus();		
	}
	else
	{
		input_box = confirm("Adakah anda pasti?");
		if (input_box == true) 
		{	
		SaveScrollXY();	
		document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKemaskiniBayaranPusaka";  
		//doAjaxCall${formName}("tambahSub");
		document.${formName}.command.value = "tambahSub";
		document.${formName}.submit();
	    document.${formName}.cmdTambahData.value = "Sila Tunggu...";
		}
	}
}


</script>