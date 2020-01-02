<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
#parse("css/eTapp_PPT.css")
</style>

<fieldset>
	<legend><strong>Senarai Pegawai Bertugas</strong></legend>

     <table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
         <tr class="table_header">
        	<td align="center"><b>No</b></td>
        	<td><b>No. Hakmilik</b></td> 
        	<td><b>No. LOT/PT</b></td>
        	<td><b>Nama Pegawai Bertugas</b></td>
        </tr>
        
      #if($listPegawaiBertugas.size()!=0)
           	#foreach($list in $listPegawaiBertugas)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         	<tr>
        		<td class="$row" align="center">$list.bil</td>
        		<td class="$row">$!list.NO_HAKMILIK</td>
        		<td class="$row">$!list.NO_LOT</td>
            	<td class="$row">$!list.NAMA_PEGAWAI</td>
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
	
	