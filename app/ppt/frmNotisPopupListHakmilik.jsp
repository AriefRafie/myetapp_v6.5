<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPT.css">


<fieldset>
	<legend><strong>Senarai Hakmilik</strong></legend>

     <table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
         <tr class="table_header">
         
         
         	<td align="center" ><b><font color="white">NO</font></b></td>
                  		<td  ><b><font color="white">NO. HAKMILIK</font></b></td>
                  		<td  align="center"><b><font color="white">NO. LOT / PT</font></b></td>
                  		<td  align="center"><b><font color="white">MUKIM/PEKAN/BANDAR</font></b></td>  
                        #if($!flag_subjaket!="")            
                  		<td  align="center"><b><font color="white">NO. SUBJAKET</font></b></td>
                        #end
                  	
         
        
        </tr>
        
      #if($listHM.size()!=0)
           	#foreach($list in $listHM)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         	<tr>
        		<td class="$row" align="center">$!list.bil</td>
	            <td class="$row">$!list.kod_jenis_hakmilik $!list.no_hakmilik</td>
				<td class="$row">$!list.no_lotpt</td>
	            <td class="$row">$!list.nama_mukim #if($list.seksyen!="")<font style="font-size:10px">Seksyen $list.seksyen</font>#end</td>
	       		#if($!flag_subjaket!="")<td class="$row">Sj.$!list.no_subjaket</td>#end
        	</tr>	      	
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
			<input type="button" name="cmdTutup" value ="Tutup" onClick="javascript:window.close()">
			</td>
		</tr>
	</table>
	
	