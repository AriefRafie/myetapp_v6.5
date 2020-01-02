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


<table width="98%" align="center">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="98%" align="center" > 
	#if($listNegeri.size()>0)
	
	<script> 
		
		  var defaultNegeri =  document.getElementById('defaultNegeri');
		  defaultNegeri.style.display = "none";
		  
		</script>
	
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/Spatial/record_paging_Negeri.jsp")
		 <!--  #parse("app/utils/record_paging.jsp") -->
		   </td>
	</tr>
	#end 
   
	<tr class="table_header" >
		   <td width="9%"   align="center" valign="top">Kod UPI</td>
	      <td width="83%"   align="left" valign="top">Negeri</td>
          <!--<td width="8%"   align="left" valign="top">Tindakan</td>-->
	</tr>
   
	#if($listNegeri.size()>0)
	#foreach($senaraiNegeri in $listNegeri)
	<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
	   <td   align="center" valign="top" class="$senaraiNegeri.rowCss">($senaraiNegeri.KOD_NEGERI)</td>
	   <td  align="left" valign="top" class="$senaraiNegeri.rowCss"><a href="#" onclick="document.getElementById('carianUmum').value='';doDivAjaxCall$formname('SenaraiDaerah','showSenaraiDaerah','FlagCari=N&cetakReport=$cetakReport&FlagCetak=$FlagCetak&ID_NEGERI=$senaraiNegeri.ID_NEGERI&NAMA_NEGERI=$senaraiNegeri.NAMA_NEGERI&NAMA_DAERAH=&NAMA_MUKIM=');"><u>$senaraiNegeri.NAMA_NEGERI</u> </a><br /></td>
	<!--   <td align ="center" valign="top"> <a href="javascript:doDivAjaxCall$formname('div_viewBahagian$TYPE$cariBahagianHQ.ID_SEKSYEN','viewDetailsBahagian','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN&TYPE=$TYPE');">
       <img src="../img/edit.gif" border="0"></a>
<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','carianBahagian','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN&FLAG_DELETE=Y&TYPE=$TYPE');}">
	<img title="Hapus"  src="../img/delete.gif" border="0"></a> 
	
	
	</td>  -->
    </tr>

     <tr id="div_viewDaerahByNegeri$senaraiNegeri.KOD_NEGERI">
     <td align="left" valign="top" colspan="5">
     </td>
     </tr>

	#end
	#else 
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>
	#if($PrintlistUPI.size()>0)
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
</fieldset>

