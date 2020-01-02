
#set($id_siasatan = "")




#parse("app/ppt/header_maklumat_siasatan_ppt.jsp")


<fieldset>
<legend>Senarai Siasatan</legend>
<table width="100%">
  <tr class="table_header">
  	<td width="8%">Bil. </td>
    <td width="8%">No. Kes</td>
    <td width="23%">No. Siasatan</td>
    <td width="16%">Tarikh Siasatan</td>
    <td  width="22%">Masa Siasatan</td>
    <td  width="31%">Status Siasatan</td>
  </tr>
  
  #set($count = 0) 
  #foreach($list in $list_siasatan)
   #set($count = $count + 1)
  #end
  :::::::::::::: $count
  
   #foreach($list in $list_siasatan)
  
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		#set( $row = "row1" )
         		#end
                      
  <tr class="$row">
  	<td>$list.BIL</td>
    <td>$list.BIL</td>
    <td><a href="javascript:papar_simati('$list.NO_KES')"><font color="blue">$list.NO_SIASATAN</font></a></td>
    <td>$list.TARIKH_SIASATAN</td>
    <td>$list.STATUS_SIASATAN</td>
    <td>$list.STATUS_SIASATAN</td>
  </tr>
  #end
</table>
</fieldset>

