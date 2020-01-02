

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
  <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Agensi	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
<input type="text" id="carianAgensi" name="carianAgensi" style="text-transform:uppercase;" size="50" value="$!carianAgensi" 
onkeypress="if(event.keyCode == 13) { doDivAjaxCall$formname('div_SenaraiAgensi$ID_KEMENTERIAN','showSenaraiAgensi',''); return false; }">	
</td>
</tr>

<tr>
<td valign="top" >	
</td>			

</tr>
<tr>
<td valign="top" >				
</td>			
<td valign="top" >
</td>
<td valign="top" >
</td>
<td valign="top" >
<br>
<input type="button" id="cmdCariAgensi" name="cmdCariAgensi" value="Cari" 
onClick="doDivAjaxCall$formname('div_SenaraiAgensi$ID_KEMENTERIAN','showSenaraiAgensi','ID_KEMENTERIAN=$ID_KEMENTERIAN&carianAgensi='+$jquery('#carianAgensi').val());" >
<input type="button" id="cmdBatalCariAgensi" name="cmdBatalCariAgensi" value="Reset" onClick="$jquery('#carianAgensi').val('');" >
<input type="button" id="cmdTambahAgensi" name="cmdTambahAgensi" value="Tambah Agensi" onClick="doDivAjaxCall$formname('div_AddAgensi$ID_KEMENTERIAN','addAgensi','ID_KEMENTERIAN=$ID_KEMENTERIAN&ID_AGENSI=');" >

</td>
</tr>
</table>
<br />
<div id="div_AddAgensi$ID_KEMENTERIAN">
</div> 

<div id="div_SenaraiAgensi$ID_KEMENTERIAN">
<table border="0" cellpadding="2" cellspacing="2" align="center" width="98%">
<tr width="100%" >
<td colspan="14">
<table width="100%" align="center">
<tr>
<td>
<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listAgensi.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/Kementerian/record_paging_Agensi.jsp")
         <!--   #parse("app/admin/UV3/record_paging_V3.jsp")-->
		   </td>
      
	</tr>
	#end 	
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Kod</td>
<td   align="left" valign="top">Nama Agensi</td>
<td   align="left" valign="top">Tindakan</td>
</tr>

#if($listAgensi.size()>0)
#foreach($list in $listAgensi)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" class="$list.rowCss2">$list.BIL</td>
<td  align="left" valign="top" class="$list.rowCss2">$list.KOD_AGENSI</td>
<td  align="left" valign="top" class="$list.rowCss2">$list.NAMA_AGENSI</td>

<td align ="center" valign="top" class="$list.rowCss2"><a href="javascript:doDivAjaxCall$formname('div_viewAgensi$list.ID_AGENSI','viewDetailsAgensi','ID_AGENSI=$list.ID_AGENSI');"><img src="../img/edit.gif" border="0"></a>
<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('deletePejabat','ID_AGENSI=$list.ID_AGENSI') } "><img src="../img/delete.gif" border="0"></a>
</td> 
</tr>
<tr  id="div_viewAgensi$list.ID_AGENSI">
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
</td>
</tr>
</table>
</div>

<br>
</td>
</tr>
</table>