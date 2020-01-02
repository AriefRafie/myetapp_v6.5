

<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_HQ').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_HQ').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_HQ').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_HQ').style.display = "";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "";
</script>
#end
<!-- close ct -->

<fieldset id="div_rowPenggunaHQ">
#if($SuccessMesejDeleteUser!="")
<div class="info" id="div_rowPenggunaHQ_deletemesej">
	$SuccessMesejDeleteUser	
</div>
<script>
$jquery("#div_rowPenggunaHQ_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowPenggunaHQ').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPenggunaHQ').offset().top);
}
</script>
#end

<table width="100%" id="div_viewPenggunaHQ" style="display:none">
<tr >
</tr>
</table>



<table width="100%" align="center">
<tr>
<td>

	
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listPenggunaInternalHQ.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/UV3/record_paging_V3.jsp")
		   </td>
		     
	</tr>
	#if($listPenggunaInternalHQ.size()==1)
	<script>
	$jquery("#showUserCount_HQ").html("("+$totalRecords+" USER FOUND)");
	</script>
	#else
	<script>
	$jquery("#showUserCount_HQ").html("("+$totalRecords+" USERS FOUND)");
	</script>
	#end
	
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
	#if($listPenggunaInternalHQ.size()>0)
	
	
		#foreach($userHQ in $listPenggunaInternalHQ)
		<tr id="div_rowPenggunaHQ$userHQ.USER_ID" >
			   <td class="$userHQ.rowCss"  align="center" valign="top" >$userHQ.BIL </td>
			   <td class="$userHQ.rowCss"  align="left" valign="top">
			   
			   #set($span1 = "span1listPenggunaInternalHQ"+$userHQ.BIL)
			   <span id="$span1"> 
			   $userHQ.NEGERI_PEJABAT
			   </span>
			   
			   </td>
			   <td class="$userHQ.rowCss"  align="left" valign="top">
			   
			   #set($span2 = "span2listPenggunaInternalHQ"+$userHQ.BIL)
			   <span id="$span2"> 
			   $userHQ.BAHAGIAN
			   </span>
			   
			   </td>
			   
			   
			   <td class="$userHQ.rowCss"  align="left" valign="top">
			   
			   #set($span3 = "span3listPenggunaInternalHQ"+$userHQ.BIL)
			   <span id="$span3"> 
			   $userHQ.NAMA_PEJABAT
			   </span>
			   
			   </td>
			   
			   
			   <td class="$userHQ.rowCss"  align="left" valign="top">
			   
			   #set($span4 = "span4listPenggunaInternalHQ"+$userHQ.BIL)
			   <span id="$span4">  
			   $userHQ.USER_LOGIN
			   </span>
			   
			   </td>
			   <td class="$userHQ.rowCss"  align="left" valign="top">
			   
			   #set($span5 = "span5listPenggunaInternalHQ"+$userHQ.BIL)
			   <span id="$span5">  
			   $userHQ.FULLNAME
			   </span>
			   
			   </td>
			   
			    <td class="$userHQ.rowCss"  align="left" valign="top" style="display:none">
			    
			    </td>
			   
			   <td class="$userHQ.rowCss"  align="left" valign="top">
			   
			   #set($span6 = "span6listPenggunaInternalHQ"+$userHQ.BIL)
			   <span id="$span6">  
			   $userHQ.JAWATAN
			   </span>
			   
			   
			   </td>
			   <td class="$userHQ.rowCss"  align="left" valign="top">
			   #set($span7 = "span7listPenggunaInternalHQ"+$userHQ.BIL)
			   <span id="$span7"> 
			   $userHQ.ROLE_UTAMA
			   </span>
			   
			   </td>
			   <td class="$userHQ.rowCss"  align="center" valign="top">
			   $userHQ.TOTAL_ROLE
			   </td>
			   <td class="$userHQ.rowCss"  align="left" valign="top">$userHQ.WAKTU_AKHIR_LOGIN</td>
			   <td class="$userHQ.rowCss"  align="center" valign="top">$userHQ.KEAKTIFAN</td>	 
			   <td class="$userHQ.rowCss"  align="left" valign="top">
			   $userHQ.DISPLAY_NOTE
			    </td>
			   <td class="$userHQ.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewPenggunaHQ$userHQ.USER_ID','viewPenggunaInternal','internalType=HQ&USER_ID=$userHQ.USER_ID&USER_LOGIN=$userHQ.USER_LOGIN');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_PenggunaInternalHQ','showSenaraiPenggunaInternalHQ','USER_NAME=$userHQ.FULLNAME&USER_ID=$userHQ.USER_ID&FLAG_DELETE=Y&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		   
			   </td>	   
		</tr>
		<tr  id="div_viewPenggunaHQ$userHQ.USER_ID">
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


	#if($PrintlistPenggunaInternalHQ.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_HQ');
	var butoncetakUserCT =  document.getElementById('cmdCetakBorangPenggunaCT_HQ');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
		butoncetakUserCT.style.display = "";
    }
	</script>
	#end
	<div id="SenaraiForPrint_HQ">	
	</div>
	


</td>
</tr>
</table>
</fieldset>
<br>
<script>
if( $jquery('#TABLE_CT_ID_HQ').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_HQ').offset().top);
}
</script>
#if($FlagCari=="Y")
<script>  
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_PenggunaInternalNegeri','showSenaraiPenggunaInternalNegeri','FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
		  
</script>
#end