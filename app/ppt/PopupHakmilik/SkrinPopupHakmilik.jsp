

#if($flag_skrin == "salin_hakmilik_sek8_KJP" )
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
<input type="text" name="form_token_pop" value='$!{session.getAttribute("form_token_pop")}'>
</div>




<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
<fieldset>
<legend>CARIAN PROJEK SEKSYEN 4 <input type="button" name="cmdKembaliSkrinUtama" value ="KEMBALI KE SKRIN UTAMA UNTUK PAPARAN DATA"  onClick="javascript:kembaliKeSkrinUtama('$id_permohonan')">
</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

<tr>
          <td width="1%">&nbsp;</td>
          <td width="25%" align="left" valign="top">NO. FAIL</td>
          <td width="1%" valign="top">:</td>
          <td width="73%" valign="top">
          
          <input name="NO_FAIL" type="text" id="NO_FAIL"  value="$NO_FAIL" size="100" maxlength="500"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />

          
           </td>
        </tr>
        
<tr >
          <td>&nbsp;</td>
          <td align="left" valign="top">PROJEK</td>
          <td valign="top">:</td>
          <td valign="top">
          
          <input name="NAMA_PROJEK" type="text" id="NAMA_PROJEK" value="$NAMA_PROJEK" size="100" maxlength="1000"  style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" />

          
           </td>
        </tr>        
        

<tr>
  <td  ></td>
  <td align="left" valign="top" ></td>
  <td valign="top" ></td>
  <td valign="top" >
   
   
    <input type="button" id="cariAduan" name="cariAduan" value="CARI" onclick="carian()"/></td>
  
  
</tr>
</table>
</fieldset>
</td>
  </tr>
</table>


#if($command == "paparHakmilik" && $id_mohon_selected  != "")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI HAKMILIK</b>  
      #if($listhakmilik.size()>0)<input type="button" id="salinHakmilik" name="salinHakmilik" value="SALIN MAKLUMAT HAKMILIK" onclick="transferHakmilik()"/>#end
      </legend>  
       
       <table align="center" width="100%" cellspacing="1" cellpadding="0">
        <tr >
        <td width="40%" valign="top">
        <table align="center" width="100%" cellspacing="1" cellpadding="0">
        <tr >
          <td scope="row" width="1%" valign="top" align="center"></td>
          <td width="28%"  valign="top">NO. FAIL JKPTG</td>               
          <td width="1%" valign="top">:</td>    
          <td width="70%" align="justify" valign="top"><font color="BLUE">
          #if($hash_maklumat_permohonan.NO_FAIL != "")
         	$hash_maklumat_permohonan.NO_FAIL
          #else
          	BELUM DISAHKAN
          #end
          </font></td>          
        </tr>
        #if($hash_maklumat_permohonan.NO_RUJUKAN_PTG != "")
         <tr >
          <td scope="row" valign="top" align="center"></td>
          <td   valign="top">NO. FAIL PTG</td>               
          <td  valign="top">:</td>    
          <td  align="justify" valign="top"><font color="BLUE">$hash_maklumat_permohonan.NO_RUJUKAN_PTG</font></td>          
        </tr>
        #end
        #if($hash_maklumat_permohonan.NO_RUJUKAN_PTD != "")
         <tr >
          <td scope="row" valign="top" align="center"></td>
          <td   valign="top">NO. FAIL PTD</td>               
          <td  valign="top">:</td>    
          <td  align="justify" valign="top"><font color="BLUE">$hash_maklumat_permohonan.NO_RUJUKAN_PTD</font></td>          
        </tr>
        #end
        #if($hash_maklumat_permohonan.NO_RUJUKAN_UPT != "")
        <tr >
          <td scope="row" valign="top" align="center"></td>
          <td   valign="top">NO. FAIL UPT</td>               
          <td  valign="top">:</td>    
          <td  align="justify" valign="top"><font color="BLUE">$hash_maklumat_permohonan.NO_RUJUKAN_UPT</font></td>          
        </tr>
        #end
        </table>
        </td>
        <td width="60%" valign="top">
        <table align="center" width="100%" cellspacing="1" cellpadding="0">
        <tr >
          <td scope="row" width="1%" valign="top" align="center"></td>
          <td width="28%"  valign="top">PROJEK</td>               
          <td width="1%" valign="top">:</td>    
          <td width="70%" align="justify" valign="top"><font color="BLUE">$hash_maklumat_permohonan.PROJEK</font></td>          
        </tr>
         <tr >
          <td scope="row" valign="top" align="center"></td>
          <td   valign="top">JUMLAH HAKMILIK</td>               
          <td  valign="top">:</td>    
          <td  align="justify" valign="top"><font color="BLUE">$listhakmilik.size()</font></td>          
        </tr>
        </table>
        </td>
        </tr>
        </table>
        #if($listhakmilik.size() > 15)
                <div style="width:100%;height:285;overflow:auto"> 
        #end	
        <table align="center" width="100%" cellspacing="1" cellpadding="0">
        <tr class="table_header">
          <td scope="row" width="5%" valign="top" align="center"><strong><font color="white">BIL.</font></strong></td>
          <td width="30%"  valign="top"><strong><font color="white">NO. HAKMILIK</font></strong></td>               
          <td width="30%" valign="top"><strong><font color="white">LOT</font></strong></td>    
          <td width="35%"  valign="top"><strong><font color="white">MUKIM</font></strong></td>          
        </tr>
        
        
        #if($listhakmilik.size()>0)

                     #foreach ( $fail in $listhakmilik )
                    #set ($count = $count+1)
                    #set( $i = $velocityCount )
                    #if ( ($i % 2) != 1 )
                    #set( $rowx = "row2" )
                    #else
                    #set( $rowx = "row1" )
                    #end
                      <tr>
                      <td class="$rowx" align="center" valign="top">$fail.BIL</td>
                      <td class="$rowx" valign="top">
                      $fail.NO_HAKMILIK 
                      </td>
                      <td class="$rowx" valign="top">
                      $fail.NO_LOT 
                      </td>
                      <td class="$rowx" valign="top"> 
                      $fail.NAMA_MUKIM
                      </td>
                      </tr>
                      #end
                      
                      
          #else
          <tr>  
            <td colspan="8"><font color="red">TIADA REKOD</font></td>    
          </tr>
          
          #end            
          </table>
          
        #if($listhakmilik.size() > 15)
               </div>
        #end     
         
          
</fieldset>
</td>
</tr>
</table>

#end



<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI FAIL</b></legend>    
     
         
		#parse("app/utils/record_pagingPopup.jsp")
     
        
        
        #set ($count = 0) <table align="center" width="100%" cellspacing="1" cellpadding="0">
       
        
        <tr class="table_header">
          <td scope="row" width="5%" valign="top" align="center"><strong><font color="white">BIL.</font></strong></td>
          <td width="15%"  valign="top"><strong><font color="white">NO. FAIL</font></strong></td>               
          <td width="40%" valign="top"><strong><font color="white">NAMA PROJEK</font></strong></td>    
          <td width="15%" align="center" valign="top"><strong><font color="white">JUMLAH HAKMILIK</font></strong></td> 
          <td width="15%" align="center" valign="top"><strong><font color="white">SALIN HAKMILIK</font></strong></td> 
          
        </tr>
        #if($SenaraiFail.size()>0)
        #foreach ( $fail in $SenaraiFail )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
          <tr>
          <td class="$row" align="center" valign="top">$fail.BIL</td>
          <td class="$row" valign="top"> 
          #if($fail.NO_FAIL!="")         
          NO. FAIL JKPTG : <font color="BLUE">$fail.NO_FAIL </font> 
          #else
          <font color="BLUE">BELUM DISAHKAN </font> 
          #end  
          
          #if($fail.NO_RUJUKAN_PTG!="")         
          <br />NO. FAIL PTG : <font color="BLUE">$fail.NO_RUJUKAN_PTG  </font>
          #end  
          
          #if($fail.NO_RUJUKAN_PTD!="")         
          <br />NO. FAIL PTD : <font color="BLUE">$fail.NO_RUJUKAN_PTD  </font>
          #end  
          
          #if($fail.NO_RUJUKAN_UPT!="")         
          <br />NO. FAIL UPT : <font color="BLUE">$fail.NO_RUJUKAN_UPT </font> 
          #end                 
          </td>         
         
          
                        
                 
          <td class="$row" valign="top"><font color="BLUE">$fail.PROJEK</font>
          
          </td> 
          <td class="$row" valign="top" align="center"><font color="BLUE">$fail.TOTAL_HAKMILIK</font></td> 
          <td class="$row" valign="top" align="center"><input type="button" id="showHakmilik" name="showHakmilik" value="PAPAR HAKMILIK" onclick="paparHakmilik('$fail.ID_PERMOHONAN')"/></td> 
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

if('$tutup_skrin_popup' == "yes")
{
	kembaliKeSkrinUtama('$id_permohonan');
}


function transferHakmilik() 
 {	
 	document.${formName}.command.value = "transfer";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupHakmilik?&id_negeri="+'$id_negeri'+"&id_daerah="+'$id_daerah'+"&jenis_seksyen="+'$jenis_seksyen';
	document.${formName}.submit();			
				
 }	

function paparHakmilik(id_permohonan) 
 {	
 	document.${formName}.id_mohon_selected.value = id_permohonan;
	document.${formName}.command.value = "paparHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SkrinPopupHakmilik?&id_negeri="+'$id_negeri'+"&id_daerah="+'$id_daerah'+"&jenis_seksyen="+'$jenis_seksyen';
	document.${formName}.submit();			
				
 }	

function carian() 
 {	
	document.${formName}.command.value = "cari";
	document.${formName}.submit();			
				
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



