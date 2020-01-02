<tr colspan="14" width="100%">
<td>

<legend>Senarai Unit </legend>
<input type="hidden" name="ID_SEKSYEN" value="$ID_SEKSYEN">
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
<br>

<table width="100%">
<tr>
<td>


		<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listUnitHQ.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/BahagianUnit/record_paging_UnitHQ.jsp")
		 <!--  #parse("app/utils/record_paging.jsp") -->
		   </td>
	</tr>
	#end 
   
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">Nama Unit/Pejabat</td>
           <td   align="left" valign="top">Negeri</td>
           <td   align="left" valign="top">Tindakan</td>
	</tr>
	#if($listUnitHQ.size()>0)
	#foreach($cariUnitHQ in $listUnitHQ)
		<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
			   <td   align="center" valign="top" class="$cariUnitHQ.rowCss2" >$cariUnitHQ.BIL. </td>
			   <td  align="left" valign="top" class="$cariUnitHQ.rowCss2">$cariUnitHQ.NAMA_UNIT ($cariUnitHQ.KOD_JKPTG)</td>
			   <td  align="left" valign="top" class="$cariUnitHQ.rowCss2">$cariUnitHQ.NEGERI</td>
         
              <td align ="center" valign="top" class="$cariUnitHQ.rowCss2"><a href="javascript:doDivAjaxCall$formname('div_viewUnit$ID_SEKSYEN$cariUnitHQ.ID_PEJABATJKPTG','viewDetailsUnit','ID_SEKSYEN=$cariUnitHQ.ID_SEKSYEN&ID_UNIT=$cariUnitHQ.ID_PEJABATJKPTG');">
              <img src="../img/edit.gif" border="0"></a>
			<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUnit','carianBahagian','ID_SEKSYEN=$cariUnitHQ.ID_SEKSYEN&FLAG_DELETE=Y');}">
			<img title="Hapus"  src="../img/delete.gif" border="0"></a>
		</td>  
		</tr>
		<tr  id="div_viewUnit$ID_SEKSYEN$cariUnitHQ.ID_PEJABATJKPTG">
		<td align="left" valign="top" colspan="14">
		
		</td>
		</tr>
		#end
	#else
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>


</td>
</tr>
</table>
</fieldset>

</td>
</tr>