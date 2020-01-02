<script>
$jquery("#div_rowPenggunaNegeri_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowPejabatUrusan').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPejabatUrusan').offset().top);
}
</script>

#set ($mode = "2")
<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<!--<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<strong> MAKLUMAT PEJABAT </strong>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">
</td>
</tr>-->
</table>

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


<table width="100%" id="div_ViewURUSAN2" style="display:none">
<tr >
</tr>
</table>



<table width="100%" align="center">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listPejabatUrusan.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		 #parse("app/admin/Utilities/record_paging_PU.jsp")
		 <!--  #parse("app/utils/record_paging.jsp") -->
		   </td>
	</tr>
	#end 
   
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">Negeri</td>
           <td   align="left" valign="top">Nama Pejabat</td>
           <td   align="left" valign="top">Alamat</td>
          
		     <td   align="left" valign="top">Status</td>
              <td   align="left" valign="top">Tindakan</td>
	</tr>
	#if($listPejabatUrusan.size()>0)
	#set ($mode = "2")
	<!--PejUrus = userHQ-->
		#foreach($PejUrus in $listPejabatUrusan)
		<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
			   <td   align="center" valign="top" class="$PejUrus.rowCss">$PejUrus.BIL. </td>
			   <td  align="left" valign="top" class="$PejUrus.rowCss">$PejUrus.NAMA_NEGERI</td>
			   <td  align="left" valign="top" class="$PejUrus.rowCss">$PejUrus.NAMA_PEJABAT</td>
			   <td  align="left" valign="top" class="$PejUrus.rowCss">$PejUrus.ALAMAT1</td>
			   
               <td align="left" valign="top" class="$PejUrus.rowCss">$PejUrus.FLAG_AKTIF</td>
         
              <td align ="center" valign="top" class="$PejUrus.rowCss"><a href="javascript:doDivAjaxCall$formname('div_ViewURUSAN$PejUrus.ID_PEJABAT$mode','viewPejabatUrusan','mode=2&ID_PEJABAT=$PejUrus.ID_PEJABAT&ID_NEGERI=$PejUrus.ID_NEGERI&JENISPEJ=2');">
              <img src="../img/edit.gif" border="0"></a>
			<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','showSenaraiPejabat2','ID_PEJABAT=$PejUrus.ID_PEJABAT&FLAG_DELETE=Y&JENISPEJ=2');}">
			<img title="Hapus"  src="../img/delete.gif" border="0"></a>
</td>  
		</tr>
		<tr  id="div_ViewURUSAN$PejUrus.ID_PEJABAT$mode">
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
	<div id="SenaraiForPrint2">	
	</div>


</td>
</tr>
</table>
</fieldset>
