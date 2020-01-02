<style type="text/css">
tr.tr_class td {
	background-color:#01DFD7;	
	font-weight:bold;
	color:white;
}
</style>



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


<fieldset><legend><b>
 #parse("app/ppk/tag_baru.jsp")
 &nbsp;
Kebenaran Mengemaskini Fail</b></legend>
		
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
      <br>
      #if($!check_notifikasi_index_maklumbalas_teknikal.size()>0)
     <table width="100%" border="0" cellspacing="2" cellpadding="2">
     <tr>
     #if($!check_notifikasi_index_maklumbalas_teknikal.size()>0)
     <td width="3%"  style="background-color:blue" align="center" valign="top">
     <b><font color="WHITE"><blink>$!check_notifikasi_index_maklumbalas_teknikal.size()</blink></font></b>
     </td>
     #else
     <td width="3%" align="center" valign="top">     
     </td>
     #end
     <td width="97%" valign="top">
     <a href="javascript:setTable('noti_teknikal')"><font color="blue">
    Notifikasi Kebenaran Pengemaskinian Fail     
     </font></a>
     </td>
     </tr>
     </table> 
      
      <fieldset id="noti_teknikal" style="display:none">
      <table align="center" width="100%" >       
         <tr>
          <td colspan="7" scope="row"></td>
        </tr>
        <tr class="table_header" >
          <td scope="row" width="3%" align="center"><strong>No</strong></td>
          <td width="25%"><strong>No. Fail</strong></td>
          <td width="25%"><strong>Nama Simati</strong></td>
          <td width="25%"><strong>Nama Pemohon</strong></td>
          
          <td width="22%"><strong>Status</strong></td>         
        </tr>
        
        #if($check_notifikasi_index_maklumbalas_teknikal.size()>0)
        #set ($count = 0)
        #foreach ( $fail in $check_notifikasi_index_maklumbalas_teknikal )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
        <tr>
          <td class="$row" align="center">$!count
          <input type="hidden"  name="id_fail_alert" id="id_fail_alert" value="$!fail.ID_FAIL" >
          </td>          
          <td class="$row"><b><a href="javascript:paparFailAlert('$fail.ID_FAIL','$!fail.NO_FAIL')" ><font color="blue">$!fail.NO_FAIL</font></a></b></td>         
          <td class="$row">$!fail.NAMA_SIMATI</td>
          <td class="$row">$!fail.NAMA_PEMOHON</td>
          <td class="$row">$!fail.NAMA_STATUS</td>
         </tr>
        #end
        
         #else
  <tr>  
    <td colspan="6">Tiada Rekod</td>    
  </tr>
  #end
      </table>
      </fieldset>
     #end
        
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
                <td  style="display:none">  
         <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="white"> Papar  </font>
         #else
         <font color="blue"> Papar  </font>
         #end  
         
              </a>   
                </td>
      			
      		    <td  style="display:none">  
                  $list.ID_FAIL   
                </td>
                
                <td  > 
                  <a href="javascript:paparFail('$list.ID_FAIL')"  > 
         #if($list.ID_FAIL  == $id_fail_carian)
         <font color="blue">  $list.NO_FAIL   </font>
         #else
         <font color="blue">  $list.NO_FAIL   </font>
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



<script type="text/javascript">

var total_sub = '$!list_sub.size()';
if(total_sub>0)
{
semak_aktif_sub('$!list_sub.size()');
}

function kosongkan() {
SaveScrollXY();        
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
	//document.${formName}.command.value = "kosongkan";
	document.${formName}.txtNoFail.focus();
	doAjaxCall${formName}("kosongkan");	
	//document.${formName}.submit();
}
function tukarstatus(id_permohonan,keterangan,id_suburusanstatusfail,id_fail,level,keterangan2,id_keputusanpermohonan,id_perbicaraan,id_perintah) {
SaveScrollXY();        
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_suburusanstatusfail.value = id_suburusanstatusfail;
	document.${formName}.id_keputusanpermohonan.value = id_keputusanpermohonan;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_perbicaraan.value = id_perbicaraan;
	document.${formName}.id_perintah.value = id_perintah;
	document.${formName}.level.value = level;
	//document.${formName}.command.value = "tukarstatus";
	//document.${formName}.submit();
	doAjaxCall${formName}("tukarstatus");	
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
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
	doAjaxCall${formName}("tukarstatus_sub");
	}
	
}
function search_data(){
SaveScrollXY();        
	//document.${formName}.command.value = "Cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
	doAjaxCall${formName}("Cari");
	//document.${formName}.submit();
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
	doAjaxCall${formName}("cariSub");
	
}

function hapus(){
input_box = confirm("Adakah anda pasti? Data akan dihapuskan dari Pengkalan Data");
	if (input_box == true) 
	{	
	SaveScrollXY();        
	//document.${formName}.command.value = "hapusSub";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";
	doAjaxCall${formName}("hapusSub");
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
		doAjaxCall${formName}("tambahSub");
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
	doAjaxCall${formName}("updateSub");
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
document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";       
document.${formName}.id_fail_carian.value = id_fail;
//doAjaxCall${formName}("paparSub");
document.${formName}.command.value = "paparSub";
		     document.${formName}.submit();
}

function paparFailAlert(id_fail,no_fail)
{
SaveScrollXY();   
document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail";     
document.${formName}.id_fail_carian.value = id_fail;
//document.${formName}.txtNoFailSub.value = no_fail;
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


function setTable(id){
	
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

</script>