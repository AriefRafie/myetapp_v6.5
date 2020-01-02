

<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_Negeri').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_Negeri').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_Negeri').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_Negeri').style.display = "";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "";
</script>
#end
<!-- close ct -->

<fieldset id="div_rowPenggunaNegeri">
#if($SuccessMesejDeleteUser!="")
<div class="info" id="div_rowPenggunaNegeri_deletemesej">
	$SuccessMesejDeleteUser	
</div>
<script>
$jquery("#div_rowPenggunaNegeri_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowPenggunaNegeri').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPenggunaNegeri').offset().top);
}
</script>
#end


<table width="100%" id="div_viewPenggunaNegeri" style="display:none">
<tr >
</tr>
</table>



<table width="100%" align="center">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listPenggunaInternalNegeri.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/UV3/record_paging_V3.jsp")
		   </td>
		   
	#if($listPenggunaInternalNegeri.size()==1)
	<script>
	$jquery("#showUserCount_Negeri").html("("+$totalRecords+" USER FOUND)");
	</script>
	#else
	<script>
	$jquery("#showUserCount_Negeri").html("("+$totalRecords+" USERS FOUND)");
	</script>
	#end
		     
	</tr>
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">Negeri</td>
		   <td   align="left" valign="top">Bahagian</td>
		   <td   align="left" valign="top">Unit</td>
		   <td   align="left" valign="top">ID Pengguna</td>
		   <td   align="left" valign="top">Nama</td>
		   <td   align="left" valign="top" style="display:none">Kumpulan Perkhidmatan</td>
		   <td   align="left" valign="top">Jawatan</td>
		   <td   align="left" valign="top">Peranan Utama</td>
		   <td   align="center" valign="top">Jumlah Peranan Tambahan</td>
		   <td   align="left" valign="top">Log Masuk Terakhir</td>
		   <td   align="center" valign="top">Status</td>	 
		   <td   align="center" valign="top">Catatan</td>	 
		   <td   align="center" valign="top">Tindakan</td>	   
	</tr>
	#if($listPenggunaInternalNegeri.size()>0)
	
	
		#foreach($userNegeri in $listPenggunaInternalNegeri)
		<tr id="div_rowPenggunaNegeri$userNegeri.USER_ID" >
			   <td class="$userNegeri.rowCss"  align="center" valign="top" >$userNegeri.BIL </td>
			   <td class="$userNegeri.rowCss"  align="left" valign="top">
			   
			   #set($span1 = "span1listPenggunaInternalNegeri"+$userNegeri.BIL)
			   <span id="$span1"> 
			   $userNegeri.NEGERI_PEJABAT
			   </span>
			   
			   </td>
			   <td class="$userNegeri.rowCss"  align="left" valign="top">
			   
			   #set($span2 = "span2listPenggunaInternalNegeri"+$userNegeri.BIL)
			   <span id="$span2"> 
			   $userNegeri.BAHAGIAN
			   </span>
			   
			   </td>
			   
			   
			   <td class="$userNegeri.rowCss"  align="left" valign="top">
			   
			   #set($span3 = "span3listPenggunaInternalNegeri"+$userNegeri.BIL)
			   <span id="$span3"> 
			   $userNegeri.NAMA_PEJABAT
			   </span>
			   
			   </td>
			   
			   
			   <td class="$userNegeri.rowCss"  align="left" valign="top">
			   
			   #set($span4 = "span4listPenggunaInternalNegeri"+$userNegeri.BIL)
			   <span id="$span4">  
			   $userNegeri.USER_LOGIN
			   </span>
			   
			   </td>
			   <td class="$userNegeri.rowCss"  align="left" valign="top">
			   
			   #set($span5 = "span5listPenggunaInternalNegeri"+$userNegeri.BIL)
			   <span id="$span5">  
			   $userNegeri.FULLNAME
			   </span>
			   
			   </td>
			   
			    <td class="$userNegeri.rowCss"  align="left" valign="top" style="display:none">
			    
			    </td>
			   
			   <td class="$userNegeri.rowCss"  align="left" valign="top">
			   
			   #set($span6 = "span6listPenggunaInternalNegeri"+$userNegeri.BIL)
			   <span id="$span6">  
			   $userNegeri.JAWATAN
			   </span>
			   
			   
			   </td>
			   <td class="$userNegeri.rowCss"  align="left" valign="top">
			   #set($span7 = "span7listPenggunaInternalNegeri"+$userNegeri.BIL)
			   <span id="$span7"> 
			   $userNegeri.ROLE_UTAMA
			   </span>
			   
			   </td>
			   <td class="$userNegeri.rowCss"  align="center" valign="top">
			   $userNegeri.TOTAL_ROLE
			   </td>
			   <td class="$userNegeri.rowCss"  align="left" valign="top">$userNegeri.WAKTU_AKHIR_LOGIN</td>
			   <td class="$userNegeri.rowCss"  align="center" valign="top">$userNegeri.KEAKTIFAN</td>	 
			   <td class="$userNegeri.rowCss"  align="left" valign="top">
			   $userNegeri.DISPLAY_NOTE
			    </td>
			   <td class="$userNegeri.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewPenggunaNegeri$userNegeri.USER_ID','viewPenggunaInternal','internalType=Negeri&USER_ID=$userNegeri.USER_ID&USER_LOGIN=$userNegeri.USER_LOGIN');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_PenggunaInternalNegeri','showSenaraiPenggunaInternalNegeri','USER_NAME=$userNegeri.FULLNAME&USER_ID=$userNegeri.USER_ID&FLAG_DELETE=Y&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		   
			   </td>	   
		</tr>
		<tr  id="div_viewPenggunaNegeri$userNegeri.USER_ID">
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

	
	
	#if($PrintlistPenggunaInternalNegeri.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_Negeri');
	var butoncetakUserCT =  document.getElementById('cmdCetakBorangPenggunaCT_Negeri');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
		butoncetakUserCT.style.display = "";
    }
	</script>
	#end
	<div id="SenaraiForPrint_Negeri">	
	</div>

</td>
</tr>
</table>
</fieldset>
<br>
<script>
if( $jquery('#TABLE_CT_ID_Negeri').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_Negeri').offset().top);
}
</script>
#if($FlagCari=="Y")
<script>   
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_PenggunaKJP','showSenaraiPenggunaKJP','FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
</script>
#end