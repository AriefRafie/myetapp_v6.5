



 

 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td  valign = "top" >
    <fieldset>
	<legend ><strong>Senarai Fail : $tajuk</strong></legend>
	<p>
   <table  width="100%" align="left"> 
      <tr >
           	  
              <td colspan="4"  ><input type="button" name="cmdExit" value="kembali" onclick="self.close();return false;" />    </td>
    	</tr>
        
           <tr class="table_header">
           	  
              <td  valign = "top" scope="row" width="3%" align="center">BIL</td>
             <td  valign = "top" width="40%">NO FAIL JKPTG</td>             
             <td  valign = "top" width="37%" align="center"><div align="center">STATUS TERKINI FAIL</div></td>
             <td  valign = "top" width="20%" align="center"><div align="center">BIL. HARI MENUNGGU</div></td>
    	</tr>
        
     
      
          #set ($list = "")
          #set ($bil = 0)
          #foreach ($list in $list_KPI_POPUP)
          
          #if($kategori == "A" && $list.HARI_TUNGGU <= $range1 && $list.HARI_TUNGGU > 0)
          
          
          #set ($bil = $bil+1)            
              
         		#if ( ($bil % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end           
         <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
         <!--   #if($list.NO_FAIL == '')
            Tiada No Fail JKPTG
            #else
            $list.NO_FAIL
            #end -->
           
           #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup_php('$list.ID_FAIL','$list.ID_STATUS','$list.ID_SUBURUSAN')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup_php('$list.ID_FAIL','$list.ID_STATUS','$list.ID_SUBURUSAN')" class="style1">$list.NO_FAIL</a>
            #end                      </td>        
           
            <td  valign = "top" class="$row">
              <div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
           #end           </div></td>           
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU</div></td>                    
   	    </tr>
          #end
          
          
          #if($kategori == "B" && $list.HARI_TUNGGU <= $range2 && $list.HARI_TUNGGU >= $range1 && $list.HARI_TUNGGU > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup_php('$list.ID_FAIL','$list.ID_STATUS','$list.ID_SUBURUSAN')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup_php('$list.ID_FAIL','$list.ID_STATUS','$list.ID_SUBURUSAN')" class="style1">$list.NO_FAIL</a>
            #end            </td>        

               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU</div></td>         
    	    </tr>
          #end
          
          #if($kategori == "C" && $list.HARI_TUNGGU >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
            #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup_php('$list.ID_FAIL','$list.ID_STATUS','$list.ID_SUBURUSAN')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup_php('$list.ID_FAIL','$list.ID_STATUS','$list.ID_SUBURUSAN')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
           
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>      
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU</div></td>         
    	    </tr>
          #end
          
          
   
          
          
          
          
          #end
          
          
          #if($bil == 0)
          <tr>
          <td  valign = "top" colspan="5">
          Tiada Rekod          </td>
          </tr>
          #end
        </table>
    
</fieldset>
    </td>
  </tr>
</table>

 

<script>




function papar_popup_php(idFail,idStatus,idSuburusan) 
{
window.opener.papar_screen_php(idFail,idStatus,idSuburusan);
window.close();
}


</script>

