<div class="viewMaklumatTR" >

<table style="margin-top:5px;margin-bottom:5px" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td width="2%"></td>
<td width="96%">

<a class="blue" href="javascript:doDivAjaxCall$formname('view_historyJana','showMaklumatHistoryJana','ID_PERBICARAAN=$ID_PERBICARAAN&skrinName=historyJana&scrolPosition='+getPageLocation());"><u>'Refresh'</u></a><br />	   
			

#if($listHistoryJana.size()>0)
<fieldset >	
	<table border="0" cellspacing="1" cellpadding="3" width="100%" > 
	#if($listHistoryJana.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/ppk/BicaraInteraktif/recordPageList.jsp")
		   </td>		     
	</tr>
	#end 
  
    
	<tr class="table_header" >
		   <td   align="center" valign="top" width="5%">Bil.</td>
		   <td   align="left" valign="top">No. Fail (Bil. Bicara)</td>
		   <td   align="left" valign="top">Waktu Bicara</td>
           <td   align="left" valign="top">Pegawai Bicara</td>
		  
           <td   align="left" valign="top">No. Pindaan</td>  
           <td   align="left" valign="top">Dijana Oleh</td>    
            <td   align="left" valign="top">Waktu Jana</td>       
           <td   align="center" valign="top" width="10%">Papar/Cetak</td>
	</tr>
	#if($listHistoryJana.size()>0)	
		#foreach($pr in $listHistoryJana)
		<tr  >
			   <td class="$pr.rowCss"  align="center" valign="top" >$pr.BIL </td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.NO_FAIL ($pr.BIL_BICARA)</td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.WAKTU_BICARA
               </td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.NAMA_PEGAWAI</td>	
              	
                <td class="$pr.rowCss"  align="left" valign="top" >$pr.NO_PINDAAN</td>	
                 <td class="$pr.rowCss"  align="left" valign="top" >$pr.PENJANA</td>	
                  <td class="$pr.rowCss"  align="left" valign="top" >$pr.TARIKH_TRANSAKSI_FULL</td>
			   <td class="$pr.rowCss"  align="center" valign="top">
			   <a href="javascript:printHistoryJana('$pr.ID_HISTORYJANANOTA','$pr.ID_PERBICARAAN');"><img title="Cetak" src="../img/print.gif" border="0"></a>	                
			   </td>	   
		</tr>		
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="10" >Tiada Rekod</td>		     
	</tr>
	#end
	</table>
</fieldset>

#else
Tiada Rekod Keterangan
#end
</td>
<td width="2%">
</td>
</tr>
</table>

#if($div != "")
 <script>
 $jquery(document).ready(function () {
     //alert('x');
     //divToTop("view_keputusan");
     $jquery('#'+'$div').scrollView();
     //alert('x2');
 });	 
 </script>    
#end
</div> 
<input type="hidden" id="ID_PERBICARAAN_TEMP" name="ID_PERBICARAAN_TEMP" value="$ID_PERBICARAAN" />