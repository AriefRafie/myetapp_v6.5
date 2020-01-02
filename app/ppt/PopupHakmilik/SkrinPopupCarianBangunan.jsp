<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css">
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

<input type="text" id="ID_BANGUNAN_SELECT" name="ID_BANGUNAN_SELECT" value="$ID_BANGUNAN_SELECT">
</div>




<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
<fieldset>
<legend>CARIAN BANGUNAN
</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

<tr>
          <td width="1%">&nbsp;</td>
          <td width="25%" align="left" valign="top">NO. BANGUNAN</td>
          <td width="1%" valign="top">:</td>
          <td width="73%" valign="top">          
          <input name="NO_BANGUNAN" type="text" id="NO_BANGUNAN"  value="$NO_BANGUNAN" size="80" maxlength="500"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />          
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
      <legend><b>SENARAI BANGUNAN</b> </legend>          
		#parse("app/utils/record_pagingPopup.jsp")
        #set ($count = 0)
         
        <table align="center" width="100%" cellspacing="1" cellpadding="0">        
        <tr class="table_header">
        
                  		<td align="center" width="5%"><b><font color="white">NO</font></b></td>
                  		<td  width="15%"><b><font color="white">NO. BANGUNAN</font></b></td>
                  		<td  width="15%" align="LEFT"><b><font color="white">JENIS BANGUNAN</font></b></td>
                  		<td  width="50%"><b><font color="white">MAKLUMAT PEMILIK/PENYEWA/PEMBELI/LAIN-LAIN</font></b></td>  
                        <td  width="15%" align="center"><b><font color="white">TAMBAH PENDUDUK	</font></b></td>  
                                   
           		 	</tr>
        
        
        #if($SenaraiFail.size()>0)

                    #foreach ( $listBg in $SenaraiFail )
                    #set ($count = $count+1)
                    #set( $i = $velocityCount )
                    #if ( ($i % 2) != 1 )
                    #set( $rowx = "row2" )
                    #else
                    #set( $rowx = "row1" )
                    #end
                      <tr>
                		<td class="$rowx" valign="top" align="center">$!listBg.bil</td>
                       
                        <td  class="$rowx" valign="top"><a href="javascript:paparBangunan('$!listBg.id_bangunan','$flag_skrin')"><font color="blue"><b>$!listBg.no_bangunan</b></font></a></td>
                        
                		<td class="$rowx"  valign="top" align="left">$!listBg.jenis
                        
                        </td>
                		<td class="$rowx"  valign="top">
                        #if($!listBg.list_pb.size() > 0)
                        <table align="center" width="100%" cellspacing="0"  cellpadding="0">
       					
                        
                        
                        #foreach ($listBg in $!listBg.list_pb )
                        
                       
                        
                   		<tr >        
                  		<td valign="top" align="center" width="5%" class="$rowy"><ul style="color:blue"><li></li></ul></td>
                  		<td valign="top" width="95%" ><a href="javascript:paparPenduduk('$!listBg.id_bangunan','$!listBg.id_bangunanpb','$flag_skrin')"><font color="blue">
                       
                       <b>$listBg.nama_pb</b></font>
                        #if($listBg.no_unit_bangunan!="" || $listBg.jenis_pb!="")
                        <br />
                        #if($listBg.no_unit_bangunan!="")
                       (No Unit : $listBg.no_unit_bangunan)
                        #end 
                        #if($listBg.jenis_pb!="")
                        (Jenis : $listBg.jenis_pb)
                        #end
                        #end
                       	
                        </a>
                        </td>
                        
                       
                  		
           		 		</tr>
                        #end
                       
                        
                        
                        </table>
                         #else
                                
                  		
                        #end
                        
                        </td>     
                		
                        
                         <td  class="$rowx" valign="top" align="center">
                       <input type="button" name="cmdTambahPB" value="Tambah" onClick="javascript:tambahMaklumatBangunanPB('$!listBg.id_bangunan')">
                        </td>
                		
            		</tr>
                      #end
                      
                      
          #else
          <tr>  
            <td colspan="8"><font color="red">TIADA REKOD</font></td>    
          </tr>
          
          #end            
          </table>
      
      
      </fieldset>
      
      </td>
  </tr>
</table>








 
#if($refreshHakmilik == "yes")
<script>

		$jquery(document).ready(function()
		{
			refreshSkrinHakmilik('$id_permohonan');
		});

</script>
#end

<script>
//refreshSkrinHakmilik(id_permohonan,flag_skrin);


function kembaliHakmilik(id_permohonan,flag_skrin){
	try {
		if(flag_skrin=="laporan_bangunan")
		{    
        var url = "../${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
        var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
           hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();
        hWnd.focus();
		}
        
	}
	catch (err) {}
	if(flag_skrin!="laporan_bangunan")
	{
   	window.close();
	}
    return false;
}

function tambahMaklumatBangunanPB(id_bangunan)
{
	try {
	window.opener.tambahMaklumatBangunanPB(id_bangunan);
	}
	catch (err) {}
    window.close();
    return false;
}

function refreshSkrinHakmilik(id_permohonan,flag_skrin)
{
	window.opener.refreshHakmilik(id_permohonan);
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
	document.${formName}.NO_BANGUNAN.value = "";	
	document.${formName}.NO_PB.value = "";
	document.${formName}.NAMA_PB.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();							
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

function paparBangunan(id_bangunan,flag_skrin)
{
	try {
	window.opener.viewBangunan(id_bangunan);
	}
	catch (err) {}
    window.close();
    return false;
}


function paparBangunan(id_bangunan,id_bangunanpb,flag_skrin)
{
	try {
	window.opener.viewBangunan(id_bangunan);
	}
	catch (err) {}
    window.close();
    return false;
}


function paparPenduduk(id_bangunan,id_bangunanpb,flag_skrin)
{
	try {
	window.opener.viewMaklumatBangunanPB(id_bangunan,id_bangunanpb)
	}
	catch (err) {}
    window.close();
    return false;
}

function paparHakmilik(id_hakmilik,flag_skrin){
	try {
		if(flag_skrin=="daftar_sek8")
		{
		window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_sek8")
		{
		window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_list_hakmilik_pb_sek8")
		{
		window.opener.viewHM(id_hakmilik);
		}
		else if(flag_skrin=="skrin_hakmilik_pb_sek8")
		{
		window.opener.viewHM(id_hakmilik);	
		}
	}
	catch (err) {}
    window.close();
    return false;
}

function tambahPbPopup(id_hakmilik,flag_skrin)
{
	try {
		if(flag_skrin=="skrin_list_hakmilik_pb_sek8" || flag_skrin=="skrin_hakmilik_pb_sek8")
		{
			window.opener.tambahWakil(id_hakmilik);
		}
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

</script>



