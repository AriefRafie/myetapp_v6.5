<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
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
 <input type="text" id="id_borange" name="id_borange" value="$id_borange">
</div>




<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
<fieldset>
<legend>CARIAN
</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

        
<tr >
          <td width="1%">&nbsp;</td>
          <td width="25%" align="left" valign="top">NAMA PB</td>
          <td width="1%" valign="top">:</td>
          <td width="73%" valign="top">          
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
      <legend><b>SENARAI BORANG F</b></legend>          
		<!--#parse("app/utils/record_pagingPopup.jsp")-->
        #set ($count = 0) 
       
      
        <table align="center" width="100%" cellspacing="1" cellpadding="0">
        
        
        
        
        
        
        
      
     
        
        
        
        
        
    
        
        <tr class="table_header">
        
                  		<td align="center" ><b><font color="white">NO</font></b></td>
                  		<td  ><b><font color="white">SENARAI REKOD/TARIKH DAFTAR</font></b></td>
                  		<td  align="center"><b><font color="white">TEMPOH (HARI)</font></b></td>            
                  		<td  align="center"><b><font color="white">ULASAN</font></b></td>
                  		<td  align="center" ><b><font color="white">SENARAI PB</font></b></td>
                        <td  align="center" ><b><font color="white"></font></b></td>
                                 
           		 	</tr>
                    
                    
                        	
       
        
        
        
        
        #if($SenaraiFail.size()>0)

                    #foreach ( $listN in $SenaraiFail )
                    #set ($count = $count+1)
                    #set( $i = $velocityCount )
                    #if ( ($i % 2) != 1 )
                    #set( $rowx = "row2" )
                    #else
                    #set( $rowx = "row1" )
                    #end
                     <tr>
           	<td class="$rowx" align="center">$!listN.bil</td>
            <td class="$rowx">
            <a href="javascript:viewBorangFInBulk('$!listN.ID_BORANGF','$flag_skrin')"><font color="blue">Rekod $!listN.bil - $!listN.TARIKH_MASUK</font></a></td>
			<td class="$rowx" align="center">$!listN.TEMPOH</td>  
			<td align="center" class="$rowx">$!listN.ULASAN</td>   
        	<td align="center" class="$rowx"><a href="javascript:popupCarianPB('$!listN.ID_BORANGF','$!id_permohonan','$flag_skrin')"><font color="blue"><b>$!listN.TOTALHM</b></font></a></td>
            <td align="center" class="$rowx"><input type="button" name="cmdHaspusBorangE" value="Hapus Borang F" onClick="javascript:hapusBorangF('$!listN.ID_BORANGF','$!id_permohonan','$flag_skrin','$id_hakmilik');"></td>   
            
            
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

function hapusBorangF(id_borangf,id_permohonan,flag_skrin,id_hakmilik) {	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "hapusBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianHakmilik_BorangF?id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin+"&id_borangf="+id_borangf+"&id_hakmilik="+id_hakmilik;
	document.${formName}.submit();
}

function popupCarianPB(id_borangf,id_permohonan,flag_skrin)
{
	
	var url = "../${securityToken}/ekptg.view.ppt.SkrinPopupCarianPB?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin+"&id_borangf="+id_borangf;
	var hWnd = window.open(url,'Senarai Lot','width=800,height=600, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
	
}


function hapusBorangE(id_borange,id_permohonan,flag_skrin) {	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "hapusBorangE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianHakmilik_BorangE_F?id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin+"&id_borange="+id_borange;
	document.${formName}.submit();
}

function viewPopupLot(id_borange,id_permohonan,flag_skrin){
	var url = "../${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?id_borange="+id_borange+"&type=borange&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'Senarai Lot','width=800,height=600, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function kemasukanF(id_borange,id_permohonan,flag_skrin){
		try {
		window.opener.maklumatBorangF(id_borange);	
			}
	catch (err) {}
   	window.close();	
    return false;
}





//refreshSkrinHakmilik(id_permohonan,flag_skrin);

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
	document.${formName}.NO_PB.value = "";
	document.${formName}.NAMA_PB.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();							
 }	



function paparHakmilik(id_hakmilik,id_permohonan,flag_skrin){
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
		else if(flag_skrin=="laporan_tanah_terperinci")
		{
		window.opener.viewHM(id_hakmilik);	
		}
		/*
		else if(flag_skrin=="laporan_bangunan")
		{    
        var url = "../${securityToken}/ekptg.view.ppt.SkrinPopupBangunan?&id_permohonan="+id_permohonan+"&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
        var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
           hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();
        hWnd.focus();
		}
        */
		else if(flag_skrin=="laporan_bangunan")
		{    
        window.opener.viewHM(id_hakmilik);	
		}

		
		
	}
	catch (err) {}
   	window.close();	
    return false;
}



function viewBorangFInBulk(id_borangF,flag_skrin)
{
	try {
		
			window.opener.viewBorangFInBulk(id_borangF);
		
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



