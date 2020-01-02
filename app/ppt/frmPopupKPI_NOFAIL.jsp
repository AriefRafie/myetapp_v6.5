



 

 
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td  valign = "top" >
    <fieldset>
	<legend><strong>Senarai Fail : $tajuk</strong></legend>
	<p></p>
    
	<input type="button" name="cmdExit" value="kembali" onclick="self.close();return false;" />    
	<table align="center" width="100%"> 
        
           <tr class="table_header">
           	  
              <td  valign = "top" scope="row" width="3%" align="center">BIL</td>
             <td  valign = "top" width="20%">NO FAIL JKPTG</td>
             <td  valign = "top" width="20%">NO FAIL PTG</td>
             <td  valign = "top" width="20%" align="center">NO FAIL PTD</td>
             <td  valign = "top" width="17%" align="center"><div align="center">STATUS TERKINI FAIL</div></td>
             <td  valign = "top" width="20%" align="center"><div align="center">BIL. HARI MENUNGGU</div></td>
    	</tr>
          #set ($list = "")
          #set ($bil = 0)
          #foreach ($list in $list_KPI_POPUP)
          
          #if($tempat == "1" && $kategori == "A" && $list.HARI_TUNGGU_MMK <= $range1 && $list.HARI_TUNGGU_MMK > 0)
          
          
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
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end                      </td>        
            <td  valign = "top" class="$row">
           
             <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>           
         <!--    $list.NO_RUJUKAN_PTG  -->          </td>            
            <td  valign = "top" class="$row">
      <!--      $list.NO_RUJUKAN_PTD  -->
           
             <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a>                       </td>
            <td  valign = "top" class="$row">
              <div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
           #end           </div></td>           
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_MMK</div></td>                    
   	    </tr>
          #end
          
          
          #if($tempat == "1" && $kategori == "B" && $list.HARI_TUNGGU_MMK <= $range2 && $list.HARI_TUNGGU_MMK >= $range1 && $list.HARI_TUNGGU_MMK > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">    <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>  
            
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_MMK</div></td>         
    	    </tr>
          #end
          
          #if($tempat == "1" && $kategori == "C" && $list.HARI_TUNGGU_MMK >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
            #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">
               <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>             </td>            
            <td  valign = "top" class="$row">
             <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a>            </td>
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>      
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_MMK</div></td>         
    	    </tr>
          #end
          
          
          
          #if($tempat == "2" && $kategori == "A" && $list.HARI_TUNGGU_BAYAR <= $range1 && $list.HARI_TUNGGU_BAYAR > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">    <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>       
             
                <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                  #if($ls.ID_STATUS == $list.ID_STATUS)
                  $ls.KETERANGAN
                  #end            
              #end</div></td>    
             <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BAYAR</div></td>    
    	    </tr>
          #end
          
          #if($tempat == "2" && $kategori == "B" && $list.HARI_TUNGGU_BAYAR <= $range2 && $list.HARI_TUNGGU_BAYAR >= $range1 && $list.HARI_TUNGGU_BAYAR > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end          
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end             </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>     
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BAYAR</div></td>         
    	    </tr>
          #end
          
          #if($tempat == "2" && $kategori == "C" && $list.HARI_TUNGGU_BAYAR >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end       
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a> </td>  
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BAYAR</div></td>        
    	    </tr>
          #end
          
          
          
          
          #if($tempat == "3" && $kategori == "A" && $list.HARI_TUNGGU_BANTAHAN_PERINTAH <= $range1 && $list.HARI_TUNGGU_BANTAHAN_PERINTAH > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end            
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
            
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>  
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BANTAHAN_PERINTAH</div></td>        
    	    </tr>
          #end
          
          #if($tempat == "3" && $kategori == "B" && $list.HARI_TUNGGU_BANTAHAN_PERINTAH <= $range2 && $list.HARI_TUNGGU_BANTAHAN_PERINTAH >= $range1 && $list.HARI_TUNGGU_BANTAHAN_PERINTAH > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end         
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>  
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BANTAHAN_PERINTAH</div></td>        
    	    </tr>
          #end
          
          #if($tempat == "3" && $kategori == "C" && $list.HARI_TUNGGU_BANTAHAN_PERINTAH >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end            
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">
                <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>            </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a> </td>  
              <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BANTAHAN_PERINTAH</div></td>        
    	    </tr>
          #end
          
          
          
           #if($tempat == "4" && $kategori == "A" && $list.HARI_TUNGGU_JPPH <= $range1 && $list.HARI_TUNGGU_JPPH > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">
                <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>            </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>    
              <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_JPPH</div></td>      
    	    </tr>
          #end
          
          #if($tempat == "4" && $kategori == "B" && $list.HARI_TUNGGU_JPPH <= $range2 && $list.HARI_TUNGGU_JPPH >= $range1 && $list.HARI_TUNGGU_JPPH > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">    <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>    
              <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_JPPH</div></td>        
    	    </tr>
          #end
          
          #if($tempat == "4" && $kategori == "C" && $list.HARI_TUNGGU_JPPH >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end          
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">    <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a> </td>    
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_JPPH</div></td>        
    	    </tr>
          #end
          
          
           #if($tempat == "5" && $kategori == "A" && $list.HARI_TUNGGU_JT <= $range1 && $list.HARI_TUNGGU_JT > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end          
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row"> 
                <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>            </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>
             <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
               #if($ls.ID_STATUS == $list.ID_STATUS)
               $ls.KETERANGAN
               #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_JT</div></td>            
    	    </tr>
          #end
          
          #if($tempat == "5" && $kategori == "B" && $list.HARI_TUNGGU_JT <= $range2 && $list.HARI_TUNGGU_JT >= $range1 && $list.HARI_TUNGGU_JT > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end        
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row"> 
                <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>            </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>    
              <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_JT</div></td>         
    	    </tr>
          #end
          
          #if($tempat == "5" && $kategori == "C" && $list.HARI_TUNGGU_JT >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end            
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row"> 
                <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>            </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a> </td>
              <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_JT</div></td>             
    	    </tr>
          #end
          
          
           #if($tempat == "6" && $kategori == "A" && $list.HARI_TUNGGU_PBN <= $range1 && $list.HARI_TUNGGU_PBN > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end             </td>        
            <td  valign = "top" class="$row">
                <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>            </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>  
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_PBN</div></td>           
    	    </tr>
          #end
          
          #if($tempat == "6" && $kategori == "B" && $list.HARI_TUNGGU_PBN <= $range2 && $list.HARI_TUNGGU_PBN >= $range1 && $list.HARI_TUNGGU_PBN > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end 
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>
              <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_PBN</div></td>           
    	    </tr>
          #end
          
          #if($tempat == "6" && $kategori == "C" && $list.HARI_TUNGGU_PBN >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end          
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a> </td>  
              <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_PBN</div></td>         
    	    </tr>
          #end
          
          
          
           #if($tempat == "7" && $kategori == "A" && $list.HARI_TUNGGU_DHDK <= $range1 && $list.HARI_TUNGGU_DHDK > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>   
             <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
               #if($ls.ID_STATUS == $list.ID_STATUS)
               $ls.KETERANGAN
               #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_DHDK</div></td>        
    	    </tr>
          #end
          
          #if($tempat == "7" && $kategori == "B" && $list.HARI_TUNGGU_DHDK <= $range2 && $list.HARI_TUNGGU_DHDK >= $range1 && $list.HARI_TUNGGU_DHDK > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end         
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>   
                <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                  #if($ls.ID_STATUS == $list.ID_STATUS)
                  $ls.KETERANGAN
                  #end            
              #end</div></td>    
             <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_DHDK</div></td>           
    	    </tr>
          #end
          
          #if($tempat == "7" && $kategori == "C" && $list.HARI_TUNGGU_DHDK >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end 
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end             </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a> </td>  
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
             <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_DHDK</div></td>            
    	    </tr>
          #end
          
          
          
           #if($tempat == "8" && $kategori == "A" && $list.HARI_TUNGGU_BAYARAN <= $range1 && $list.HARI_TUNGGU_BAYARAN > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td> 
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BAYARAN</div></td>             
    	    </tr>
          #end
          
          #if($tempat == "8" && $kategori == "B" && $list.HARI_TUNGGU_BAYARAN <= $range2 && $list.HARI_TUNGGU_BAYARAN >= $range1 && $list.HARI_TUNGGU_BAYARAN > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end 
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>  
                <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                  #if($ls.ID_STATUS == $list.ID_STATUS)
                  $ls.KETERANGAN
                  #end            
              #end</div></td>    
             <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BAYARAN</div></td>            
    	    </tr>
          #end
          
          #if($tempat == "8" && $kategori == "C" && $list.HARI_TUNGGU_BAYARAN >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end           
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a> </td>    
             <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
               #if($ls.ID_STATUS == $list.ID_STATUS)
               $ls.KETERANGAN
               #end            
              #end</div></td>    
             <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BAYARAN</div></td>          
    	    </tr>
          #end
          
          
           #if($tempat == "9" && $kategori == "A" && $list.HARI_TUNGGU_BORANGK <= $range1 && $list.HARI_TUNGGU_BORANGK > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end          
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>  
                <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                  #if($ls.ID_STATUS == $list.ID_STATUS)
                  $ls.KETERANGAN
                  #end            
              #end</div></td>    
             <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BORANGK</div></td>            
    	    </tr>
          #end
          
          #if($tempat == "9" && $kategori == "B" && $list.HARI_TUNGGU_BORANGK <= $range2 && $list.HARI_TUNGGU_BORANGK >= $range1 && $list.HARI_TUNGGU_BORANGK > 0)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end          
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a></td>  
              <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                #if($ls.ID_STATUS == $list.ID_STATUS)
                $ls.KETERANGAN
                #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BORANGK</div></td>        
    	    </tr>
          #end
          
          #if($tempat == "9" && $kategori == "C" && $list.HARI_TUNGGU_BORANGK >= $range1)         
          #set ($bil = $bil+1)  
          #if ( ($bil % 2) != 1 ) #set( $row = "row2" ) #else #set( $row = "row1" ) #end 
            <tr>          	
            <td  valign = "top" class="$row" align="center">$bil</td>           
            <td  valign = "top" class="$row">
             #if($list.NO_FAIL == '')
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">Tiada No Fail JKPTG </a>
            #else
            	<a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_FAIL</a>
            #end            </td>        
            <td  valign = "top" class="$row">     <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTG</a>  </td>            
            <td  valign = "top" class="$row"> <a href="javascript:papar_popup('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','$!{session.getAttribute('_portal_role')}')" class="style1">$list.NO_RUJUKAN_PTD</a> </td> 
               <td  valign = "top" class="$row"><div align="center">#foreach ($ls in $list_status)            
                 #if($ls.ID_STATUS == $list.ID_STATUS)
                 $ls.KETERANGAN
                 #end            
              #end</div></td>    
            <td  valign = "top" class="$row"><div align="right">$list.HARI_TUNGGU_BORANGK</div></td>         
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



function papar_popup(ID_PERMOHONAN,ID_STATUS,ID_FAIL,TARIKH_PERMOHONAN,FLAG_JENISPERMOHONAN,ID_SUBURUSAN,FLAG_SEGERA,USER_ROLE) 
{

//alert("aaaa");
//window.opener.paparx();



window.opener.papar_screen(ID_PERMOHONAN,ID_STATUS,ID_FAIL,TARIKH_PERMOHONAN,FLAG_JENISPERMOHONAN,ID_SUBURUSAN,FLAG_SEGERA,USER_ROLE);	
window.close();
}


</script>

