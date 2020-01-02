
<div id="div_LaporanPejabatforPrint" >
<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%">
<tr>
<td>


	<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
	

	<!-- #if($listPejabatUrusan.size()>0)
	
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/Utilities/record_paging_PU.jsp")
		  #parse("app/utils/record_paging.jsp")
		   </td>
	</tr>
	#end  -->
   
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">Negeri</td>
           <td   align="left" valign="top">Nama Pejabat</td>
           <td   align="left" valign="top">Alamat</td>
           #if ($jenisPejabat.equals("1"))
		   <td   align="left" valign="top">Jumlah Kakitangan</td>
		   #end
		     <td   align="left" valign="top">Status</td>
             <!--  <td   align="left" valign="top">Tindakan</td> -->
	</tr>
	#if($PrintlistPejabat.size()>0)
	
	<!--PejUrus = userHQ-->
		#foreach($PejUrus in $PrintlistPejabat)
		<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
			   <td   align="center" valign="top" >$PejUrus.BIL. </td>
			   <td  align="left" valign="top">$PejUrus.NAMA_NEGERI</td>
			   <td  align="left" valign="top">$PejUrus.NAMA_PEJABAT</td>
			   <td  align="left" valign="top">$PejUrus.ALAMAT1</td>
			    #if ($jenisPejabat.equals("1"))
			   <td   align="center" valign="top">$PejUrus.KAKI_TANGAN</td>
			   #end 
			   
               <td align="left" valign="top">$PejUrus.FLAG_AKTIF</td>
         
             <!--  <td align ="center" valign="top"><a href="javascript:doDivAjaxCall$formname('div_viewPejabat$PejUrus.ID_PEJABAT','viewPejabatUrusan','ID_PEJABAT=$PejUrus.ID_PEJABAT&ID_NEGERI=$PejUrus.ID_NEGERI&JENISPEJ=$jenisPejabat');">
              <img src="../img/edit.gif" border="0"></a>
			<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','showSenaraiPejabat','ID_PEJABAT=$PejUrus.ID_PEJABAT&FLAG_DELETE=Y&JENISPEJ=$jenisPejabat');}">
			<img title="Hapus"  src="../img/delete.gif" border="0"></a>
</td>   -->
		</tr>
		<!-- <tr  id="div_viewPejabat$PejUrus.ID_PEJABAT">
				<td align="left" valign="top" colspan="14">
		
		</td>
		</tr> -->
		#end
		
	#else
	<tr >
		   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
		     
	</tr>
	#end
	</table>

	#if($PrintlistPejabat.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPejabat');
	//alert(' masuk sini ');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	#end


</td>
</tr>
</table>
</div>

<script type="text/javascript">
	printHideDiv('div_LaporanPejabatforPrint','$jenisPejabatReport','');
	</script>
