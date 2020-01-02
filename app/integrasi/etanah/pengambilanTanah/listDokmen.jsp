#if($!jenis_skrin == "TarikBalik" || $!jenis_skrin == "BorangI" || $!jenis_skrin == "BorangD" || $!jenis_skrin == "BorangB" || $!jenis_skrin == "hantarPelanChartingS8" || $!jenis_skrin == "hantarPelanChartingS4")
		<table border="0" width="100%"  class="nav"> 
<tr  >
<td valign="top" >
<b>SENARAI LOT YANG TERLIBAT</b>
 </td>
</tr>
</table>
         #parse("app/utils/record_paging_popup.jsp")
        <table align="center" width="100%" cellspacing="2" cellpadding="2" class="dashboard_sub"> 
        
       
          
            <tr class="table_header">
              <td scope="row"  align="center"><strong>Bil</strong></td>
              <td ><strong>No. Hakmilik</strong></td>
              <td ><strong>No. Lot</strong></td>
              <td align="right" ><strong>Luas Asal</strong></td>
              <td align="right" ><strong >Luas Ambil</strong></td>
               #if($!jenis_skrin == "BorangI")  
                            
                            <td align="center" ><b>Tarikh Borang I</b></td>
                           
                #end
                #if($!jenis_skrin == "BorangK")  
                            
                            <td align="center" ><b>Tarikh Borang K</b></td>
                            <td align="center" ><b>Jenis Pengambilan</b></td>
                #end
            </tr>
          #set ($list = "")
          #set ($counter = 0)
          #foreach ($list in $SenaraiFail)
          #set( $counter = $counter + 1 )
          	#if ($list.BIL == '')
                #set( $row = "row1" )
            #elseif (($list.BIL % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end           
           
          <tr>
            <td class="$row">
			$list.BIL
			</td>
           
            <td class="$row">$list.KOD_JENIS_HAKMILIK $list.NO_HAKMILIK</td>
            
            <td class="$row">$list.NO_LOT</td>
            <td class="$row" align="right">$list.LUAS_ASAL</td>
            <td class="$row" align="right">$list.LUAS_AMBIL</td>
             #if($!jenis_skrin == "BorangI")  
                            
                            <td class="$row" align="center">$list.TARIKH_BORANGI</td>
                            #end
              #if($!jenis_skrin == "BorangK")  
                            
                            <td class="$row" align="center">$list.TARIKH_BORANGK</td>
                           <td class="$row" align="center"><!--$list.STATUS_AMBIL-->
                           
                           #if($list.STATUS_AMBIL == "P")
                           KESELURUHAN
                           #elseif($list.STATUS_AMBIL == "S")
                           SEBAHAGIAN
                           #else
                           
                           #end
                           
                           
                           </td>
                            
                            #end
            </tr>
          #end
          
           #if($counter == 0)
            <tr>
           
            <td colspan="10" class="row">
           Tiada rekod
           </td>
           </tr>
           #end 
          
        </table>
		
        
<br />

#end
