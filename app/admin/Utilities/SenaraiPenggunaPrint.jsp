#set ($jenisPejabatReport = $viewPejabat.NAMA_PEJABAT)
<div id="div_LaporanPenggunaforPrint" >
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
		   <td   align="left" valign="top">Nama</td>
           <td   align="left" valign="top">Jawatan</td>
           <td   align="left" valign="top">Peranan Utama</td>
		     <td   align="left" valign="top">Status</td>
             <!--  <td   align="left" valign="top">Tindakan</td> -->
	</tr>
	#if($PrintlistUserPejabat.size()>0)
	<!--PejUrus = userHQ-->
		#foreach($PejUrus in $PrintlistUserPejabat)
		<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
			   <td   align="center" valign="top" >$PejUrus.BIL. </td>
			   <td  align="left" valign="top">$PejUrus.FULLNAME</td>
			   <td  align="left" valign="top">$PejUrus.JAWATAN</td>
			   <td  align="left" valign="top">$PejUrus.ROLE_UTAMA</td>
			   <td align="left" valign="top">$PejUrus.KEAKTIFAN</td>
		#end
		
	#else
	<tr >
		   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
		     
	</tr>
	#end
	</table>

	#if($PrintlistUserPejabat.size()>0)
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
	printHideDiv('div_LaporanPenggunaforPrint','$jenisPejabatReport','');
	</script>
