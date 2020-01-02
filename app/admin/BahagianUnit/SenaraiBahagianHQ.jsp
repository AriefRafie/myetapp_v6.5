<fieldset id="div_rowPejabatUrusan">

#if($SuccessMesejDeleteUser!="")
<div class="info" id="div_rowPejabatUrusan_deletemesej">
	$SuccessMesejDeleteUser	
</div>
<script>
$jquery("#div_rowPejabatUrusan_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowPejabatUrusan').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPejabatUrusan').offset().top);
}
</script>
#end


<table width="100%" align="center"> 
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listBahagianHQ.size()>0)
	
	
	
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/BahagianUnit/record_paging_BhgnHQ.jsp")
		 <!--  #parse("app/utils/record_paging.jsp") -->
		   </td>
	</tr>
	#end 
   
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">Nama Bahagian/Unit</td>
           <!-- <td   align="left" valign="top">Negeri</td> -->
           <td   align="left" valign="top">Status</td>
           <td   align="left" valign="top">Catatan</td>
           <td   align="center" valign="top">Tindakan</td>
	</tr>
	#if($listBahagianHQ.size()>0)
	#foreach($cariBahagianHQ in $listBahagianHQ)
		<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
			   <td   align="center" valign="top" class="$cariBahagianHQ.rowCss" >$cariBahagianHQ.BIL. </td>
			   <td  align="left" valign="top" class="$cariBahagianHQ.rowCss" >$cariBahagianHQ.NAMA_SEKSYEN ($cariBahagianHQ.KOD_SEKSYEN)</td>
			  <!--  <td  align="left" valign="top">??</td> -->
			   <td  align="left" valign="top" class="$cariBahagianHQ.rowCss" >$cariBahagianHQ.FLAG_AKTIF</td>
			   <td align="left" valign="top" class="$cariBahagianHQ.rowCss">$cariBahagianHQ.CATATAN</td>
         
              <td align ="center" valign="top" class="$cariBahagianHQ.rowCss"><a href="javascript:doDivAjaxCall$formname('div_viewBahagian$cariBahagianHQ.ID_SEKSYEN','viewDetailsBahagian','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN');">
              <img src="../img/edit.gif" border="0"></a>
		<!--	<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','showSenaraiBahagian','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN&FLAG_DELETE=Y');}">
			<img title="Hapus"  src="../img/delete.gif" border="0"></a>-->
</td>  
		</tr>
		<tr id="div_viewBahagian$cariBahagianHQ.ID_SEKSYEN" >
		<td align="left" valign="top" colspan="14" >
		</td>
		</tr>
		#end
	#else
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>

	#if($listBahagianHQ.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetak');
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
</fieldset>
