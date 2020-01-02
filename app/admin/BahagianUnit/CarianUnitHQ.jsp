<tr >
<!--<td></td>-->
<td colspan="14">
<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
<tr>
    <!--<td width="8" nowrap></td>-->
    <td>
<fieldset>
<legend>Carian Unit
<input type="button" id="cmdAddUnit" name="cmdAddUnit"   
onClick="document.getElementById('div_viewUnit$ID_SEKSYEN').style.display='';doDivAjaxCall$formname('div_viewUnit$ID_SEKSYEN','editUnit','ID_SEKSYEN=$ID_SEKSYEN&ID_UNIT=');" value="Tambah Unit" >
</legend>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" >
#if ($ID_SEKSYEN == 2) 
<tr colspan="10">
	<td height="27" colspan="10" align="left">Carian Unit</td>
	<td >:</td>
	<td valign="top">
	<input type="text" id="carianUnit" name="carianUnit" style="text-transform:uppercase;" size="42" value="$!carianUnit" 
	onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianUnit','FlagCari=Y'); return false; }">	
	</td>
	<td>
<!--<input type="button" id="cmdCariPejabat" name="cmdCariPejabat" value="Cari" onClick="doDivAjaxCall$formname('div_SenaraiUnit','showSenaraiUnit','ID_SEKSYEN=$ID_SEKSYEN');" >
<input type="button" id="cmdBatalCariPejabat" name="cmdBatalCariPejabat" value="Reset" onClick="doDivAjaxCall$formname('div_carian','batalCarianUnit','FlagCari=Y');" >
-->
</td>
</tr>
<tr colspan="10">
	<td colspan="10" align="left" height="27">Negeri</td><td >:</td>
	<td valign="top">
    <select id="ID_NEGERI_$ID_SEKSYEN"  name="ID_NEGERI_$ID_SEKSYEN" >	   
           <option value = "" >SILA PILIH</option>
            #foreach ( $ruj in $list_TBLRUJNEGERI )		
                #set ( $selected_ruj = "" )
                #if($viewUnitHQ.ID_NEGERI==$ruj.ID)
                #set ( $selected_ruj = "selected" )
                #end	
                <option $selected_ruj value="$ruj.ID" >
                $ruj.KETERANGAN
                </option>					
                
            #end
	</select>
	</td>
	<td>
</td>
</tr>

<tr colspan="10">
<td colspan="10" align="left"></td><td ></td>
<td valign="top" align="left" height="27">
<input type="button" id="cmdCariPejabat" name="cmdCariPejabat" value="Cari" onClick="doDivAjaxCall$formname('div_SenaraiUnit$ID_SEKSYEN','showSenaraiUnit','ID_SEKSYEN=$ID_SEKSYEN');" >
<input type="button" id="cmdBatalCariPejabat" name="cmdBatalCariPejabat" value="Reset" onClick="doDivAjaxCall$formname('div_carian','batalCarianUnit','FlagCari=Y');" >
</td>
<td></td>
</tr>

#else 
<tr colspan="10">
	<td colspan="10" align="left" height="27">Carian Unit</td><td >:</td>
	<td valign="top">
	<input type="text" id="carianUnit" name="carianUnit" style="text-transform:uppercase;" size="50" value="$!carianUnit" 
	onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_senaraiUtama','carianUnit','FlagCari=Y'); return false; }">	
	
    <input type="button" id="cmdCariPejabat" name="cmdCariPejabat" value="Cari" onClick="doDivAjaxCall$formname('div_SenaraiUnit$ID_SEKSYEN','showSenaraiUnit','ID_SEKSYEN=$ID_SEKSYEN');" >
<input type="button" id="cmdBatalCariPejabat" name="cmdBatalCariPejabat" value="Reset" onClick="doDivAjaxCall$formname('div_carian','batalCarianUnit','FlagCari=Y');" >

    </td>
	<td></td>
</tr>
#end
</table>

<div id="div_viewUnit$ID_SEKSYEN" style="display:none"></div>

<table border="0" cellpadding="2" cellspacing="2" align="center" width="100%">
<tr id="div_SenaraiUnit$ID_SEKSYEN" width="100%" >
<td colspan="14">
<fieldset id="div_rowPejabatUrusan" >
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

<table width="100%" >
<tr colspan="10">
<td colspan="10">
<input type="hidden" name="ID_SEKSYEN" value="$ID_SEKSYEN">

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
			   <td   align="center" valign="top" class="$cariUnitHQ.rowCss2">$cariUnitHQ.BIL.</td>
			   <td  align="left" valign="top" class="$cariUnitHQ.rowCss2">$cariUnitHQ.NAMA_UNIT ($cariUnitHQ.KOD_JKPTG)</td>
			   <td  align="left" valign="top" class="$cariUnitHQ.rowCss2">$cariUnitHQ.NEGERI</td>
         
              <td align ="center" valign="top" class="$cariUnitHQ.rowCss2"><a href="javascript:doDivAjaxCall$formname('div_viewUnit$ID_SEKSYEN$cariUnitHQ.ID_PEJABATJKPTG','viewDetailsUnit','ID_SEKSYEN=$cariUnitHQ.ID_SEKSYEN&ID_UNIT=$cariUnitHQ.ID_PEJABATJKPTG');">
              <img src="../img/edit.gif" border="0"></a>
			<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','carianBahagian','ID_SEKSYEN=$cariUnitHQ.ID_SEKSYEN&FLAG_DELETE=Y');}">
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


</table>


</fieldset>
</td>
</tr>
</table>
</td>
</tr>