<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">




#if($flag_skrin == "skrin_hakmilik_sek8_KJP" )
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
id daerah : <input type="text" id="ID_DAERAH" name="ID_DAERAH" value="$ID_DAERAH">


</div>




<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
<fieldset>
<legend>CARIAN HAKMILIK
</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">


        
<tr >
          <td width="1%">&nbsp;</td>
          <td width="25%" align="left" valign="top">NO. LOT / NO. PT</td>
          <td width="1%" valign="top">:</td>
          <td width="73%" valign="top">          
          <input name="NO_LOT" type="text" id="NO_LOT" value="$NO_LOT" size="80" maxlength="1000"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />         
          </td>
        </tr> 
        
        
        <tr >
          <td>&nbsp;</td>
          <td align="left" valign="top">NO. HAKMILIK</td>
          <td valign="top">:</td>
          <td valign="top">          
          <input name="NO_HAKMILIK" type="text" id="NO_HAKMILIK" value="$NO_HAKMILIK" size="80" maxlength="1000"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />         
          </td>
        </tr>  
        
        
        <tr >
          <td>&nbsp;</td>
          <td align="left" valign="top">MUKIM</td>
          <td valign="top">:</td>
          <td valign="top">          
         	$!selectMukim
          </td>
        </tr>  
        
        
              
        

<tr>
  <td  ></td>
  <td align="left" valign="top" ></td>
  <td valign="top" ></td>
  <td valign="top" >
   
   
    <input type="button" id="cariHakmilik" name="cariHakmilik" value="CARI" onclick="carian('$flag_skrin')"/>
    <input type="button" id="kosongHariHakmilik" name="kosongHariHakmilik" value="RESET" onclick="kosongkan('$flag_skrin')"/>
    
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
        
       
        
        
        
        <table align="center" width="100%" cellspacing="1" cellpadding="0">
      
        
        <tr class="table_header">
        
                  		<td align="center" width="5%"><b><font color="white">NO</font></b></td>
                  		<td  width="30%"><b><font color="white">NO HAKMILIK</font></b></td>
                  		<td  width="20%"><b><font color="white">NO. LOT/NO. PT</font></b></td>              
                  		<td  width="25%"><b><font color="white">MUKIM/PEKAN/BANDAR</font></b></td>
                  		<td  width="20%" ><b><font color="white">LUAS DIAMBIL</font></b></td>             
           		 	</tr>
        
        
        #if($SenaraiFail.size()>0)

                    #foreach ( $listTanah in $SenaraiFail )
                    #set ($count = $count+1)
                    #set( $i = $velocityCount )
                    #if ( ($i % 2) != 1 )
                    #set( $rowx = "row2" )
                    #else
                    #set( $rowx = "row1" )
                    #end
                      <tr>
                		<td class="$rowx" align="center">$!listTanah.rn</td>
                      
                        <td  class="$rowx"><a href="javascript:salinHakmilik('$id_permohonan','$!listTanah.id_hakmilik','$flag_skrin')"><font color="blue">
                        
                        #if($!listTanah.no_hakmilik != "")
                        $!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik
                        #else
                        -
                        #end
                        
                        </font></a></td>
                        
                		<td class="$rowx">$!listTanah.no_lotpt</td>     
                		<td class="$rowx" >$!listTanah.nama_mukim  
                        #if($listTanah.seksyen != "" && $listTanah.seksyen != "-")                      
                        <font style='font-size:10px' >Seksyen $listTanah.seksyen</font>
                        #end
                        </td>
                		<td  class="$rowx" >$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
                		
                		
                		
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













<script>






function refresh_Popup(id_hakmilik,flag_skrin)
{

	
	$jquery(document).ready(function()
		{
			var refreshId = setInterval( function()
			{
			
				document.${formName}.command.value = "refresh_Popup";	
				document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupCarianPB?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
				document.${formName}.submit();
			
			}, 3000);
		});
		
}




if('$tutup_skrin_popup' == "yes")
{
	kembaliKeSkrinUtama('$id_permohonan');
}


function salinHakmilik(id_permohonan,id_hakmilik_salin,flag_skrin)
{
	try
	{
		window.opener.salin_hakmilik(id_permohonan,id_hakmilik_salin);
		
	}
	catch (err) {}
    window.close();
    return false;
}


function paparpbpopup(id_hakmilikpb,flag_skrin)
{
	try
	{
		window.opener.salin_pb(id_hakmilikpb);
		
	}
	catch (err) {}
    window.close();
    return false;
}



function carian(flag_skrin) 
 {	
	document.${formName}.command.value = "cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupSalinPBHakmilik?&flag_skrin="+flag_skrin;	
	document.${formName}.submit();			
				
 }	
 
 function kosongkan(flag_skrin) 
 {
	document.${formName}.NO_LOT.value = "";
	document.${formName}.NO_HAKMILIK.value = "";
	document.${formName}.socMukim.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupSalinPBHakmilik?&flag_skrin="+flag_skrin;	
	document.${formName}.submit();							
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
		if(flag_skrin=="skrin_list_hakmilik_pb_sek8")
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




function doCheckAll1(sudahbatal){  
var cc = 0;  
    if (document.${formName}.all1.checked == true){
        if (document.${formName}.ids1.length == null){
		cc++;
            document.${formName}.ids1.checked = true;
        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.ids1.length == null){
            document.${formName}.ids1.checked = false;

        } else {
            for (i = 0; i < document.${formName}.ids1.length; i++){
                document.${formName}.ids1[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}


function doCheckAllBorangG(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangG.checked == true){
        if (document.${formName}.borangG.length == null){
		cc++;
            document.${formName}.borangG.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangG.length == null){
            document.${formName}.borangG.checked = false;


        } else {
            for (i = 0; i < document.${formName}.borangG.length; i++){
                document.${formName}.borangG[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}

function doCheckAllBorangE(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangE.checked == true){
        if (document.${formName}.borangE.length == null){
		cc++;
            document.${formName}.borangE.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangE.length == null){
            document.${formName}.borangE.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangE.length; i++){
                document.${formName}.borangE[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}



function doCheckAllBorangK(sudahbatal){  
var cc = 0;  
    if (document.${formName}.allborangK.checked == true){
        if (document.${formName}.borangK.length == null){
		cc++;
            document.${formName}.borangK.checked = true;
        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = true;
				cc++;
            }
        }
    } else {
        if (document.${formName}.borangK.length == null){
            document.${formName}.borangK.checked = false;

        } else {
            for (i = 0; i < document.${formName}.borangK.length; i++){
                document.${formName}.borangK[i].checked = false;
            }
        }
    }
	
	   document.${formName}.jumlah_dipilih.value  = cc;	
	   document.${formName}.jumlah_semua.value  = sudahbatal;
	   
	 //  alert(sudahbatal);
     /*
	   url = "../servlet/ekptg.view.ppt.PenarikanBalikCheck";
	   actionName = "jumlah_dipilih";
	   target = "jumlahlot";
	   doAjaxUpdater(document.${formName}, url, target, actionName);
       */
       $jquery("#jumlahlot").html("<span  style='color:#0000FF'>"+cc+"</span> / <span  style='color:#0000FF'>"+sudahbatal+"</span>");	
	
	
}





function doUpdateCheckAll1(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.ids1.length > 1)
{     
	  for (i = 0; i < document.${formName}.ids1.length; i++)
	  {
      if (document.${formName}.ids1[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.ids1.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.all1.checked = false;
	  }
	  else
	  {
	  document.${formName}.all1.checked = true;
	  }
	  
	  
	 
	
}



function doUpdateCheckAllBorangG(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangG.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangG.length; i++)
	  {
      if (document.${formName}.borangG[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangG.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangG.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangG.checked = true;
	  }
	  
	  
	  
	  	
	
}

function doUpdateCheckAllBorangE(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangE.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangE.length; i++)
	  {
      if (document.${formName}.borangE[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangE.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangE.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangE.checked = true;
	  }
	  
	  
	
}



function doUpdateCheckAllBorangK(sudahbatal){  
var c = 0;
var cc = 0;
if(document.${formName}.borangK.length > 1)
{     
	  for (i = 0; i < document.${formName}.borangK.length; i++)
	  {
      if (document.${formName}.borangK[i].checked == false)
	  {	 
	  c++
      }
	  else
	  {	 
	  cc++
      }
	  }  
}
else
{
if (document.${formName}.borangK.checked == false)
{	 
c++;
}
else
{
 cc++
}	 	
}	 
   if(c>0)
	  {	  
	  document.${formName}.allborangK.checked = false;
	  }
	  else
	  {
	  document.${formName}.allborangK.checked = true;
	  }
	  
	  
	
}


function popupPemegangAmanah_popupPB(id_hakmilik,id_hakmilikpb,flag_skrin){

	var w = "600";
	var h = "400";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../${securityToken}/ekptg.view.ppt.FrmPopupPemegangAmanah_PopupPB?id_hakmilik="+id_hakmilik+"&id_hakmilikpb="+id_hakmilikpb+"&flag_skrin="+flag_skrin;
		
	var hWnd = window.open(url, "Daftar Pemegang Amanah", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}



</script>



