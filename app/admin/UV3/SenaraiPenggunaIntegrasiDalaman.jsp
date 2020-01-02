<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_INT').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_INT').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_INT').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_INT').style.display = "";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "";
</script>
#end
<!-- close ct -->

<fieldset id="div_rowPenggunaINT">


#if($SuccessMesejDeleteUser!="")
<div class="info" id="div_rowPenggunaINT_deletemesej">
	$SuccessMesejDeleteUser	
</div>
<script>
$jquery("#div_rowPenggunaINT_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowPenggunaINT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPenggunaINT').offset().top);
}
</script>
#end

<table width="100%" id="div_viewPenggunaINT" style="display:none">
<tr >
</tr>
</table>



<table width="100%">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listPenggunaINT.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/UV3/record_paging_V3.jsp")
		   </td>
		   
	#if($listPenggunaINT.size()==1)
	<script>
	$jquery("#showUserCount_INT").html("("+$totalRecords+" USER FOUND)");
	</script>
	#else
	<script>
	$jquery("#showUserCount_INT").html("("+$totalRecords+" USERS FOUND)");
	</script>
	#end
		     
	</tr>
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">ID Pengguna</td>
		   <td   align="left" valign="top">Nama</td>
		   <td   align="left" valign="top">Emel</td>
		   <td   align="left" valign="top">Jenis</td>
		   <!--
		   <td   align="left" valign="top">Pejabat</td>		     
		   <td   align="left" valign="top">Negeri</td>
		   <td   align="left" valign="top">Daerah</td>
		   -->
		   <td   align="left" valign="top">Log Masuk Terakhir</td>
		   <td   align="center" valign="top">Status</td>	 
		   <td   align="center" valign="top">Catatan</td>	 
		   <td   align="center" valign="top">Tindakan</td>	   
	</tr>
	#if($listPenggunaINT.size()>0)
	
	
		#foreach($userINT in $listPenggunaINT)
		<tr id="div_rowPenggunaINT$userINT.USER_ID">
			   <td class="$userINT.rowCss"  align="center" valign="top" >$userINT.BIL </td>
			   <td class="$userINT.rowCss"  align="left" valign="top">
			   #set($span1 = "span1listPenggunaINT"+$userINT.BIL)
			   <span id="$span1"> 
			   $userINT.USER_LOGIN
			   </span>
			   </td>
			   
			   
			   <td class="$userINT.rowCss"  align="left" valign="top">
			   #set($span2 = "span2listPenggunaINT"+$userINT.BIL)
			   <span id="$span2"> 
			    $userINT.USER_NAME
			   </span>
			   </td>
			  
			   
			   <td class="$userINT.rowCss"  align="left" valign="top">	
			   #set($span3 = "span3listPenggunaINT"+$userINT.BIL)
			   <span id="$span3"> 	  
			  $userINT.EMEL
			    </span>
			   </td>
			   
			   
			   
			   <td class="$userINT.rowCss"  align="left" valign="top">
			   
			   #set($span4 = "span4listPenggunaINT"+$userINT.BIL)
			   <span id="$span4">  
			   $userINT.JENIS_PEJABAT
			   </span>
			   
			   
			   #set($span5 = "span5listPenggunaINT"+$userINT.BIL)
			   <span id="$span5"></span>
			   
			   #set($span6 = "span6listPenggunaINT"+$userINT.BIL)
			   <span id="$span6"></span>
			   
			   #set($span7 = "span7listPenggunaINT"+$userINT.BIL)
			   <span id="$span7"></span>
			   </td>
			   <!-- 
			   <td class="$userINT.rowCss"  align="left" valign="top">
			   
			   #set($span6 = "span6listPenggunaINT"+$userINT.BIL)
			   <span id="$span6">  
			   $userINT.NEGERI_JPPH
			   </span>
			   
			   </td>
			   
			   <td class="$userINT.rowCss"  align="left" valign="top">			   
			   #set($span7 = "span7listPenggunaINT"+$userINT.BIL)
			   <span id="$span7">  
			    $userINT.DAERAH_JPPH
			   </span>			   
			   </td>
			 	 -->
			  
			   <td class="$userINT.rowCss"  align="left" valign="top">$userINT.WAKTU_AKHIR_LOGIN</td>
			   <td class="$userINT.rowCss"  align="center" valign="top">$userINT.KEAKTIFAN</td>	 
			   <td class="$userINT.rowCss"  align="left" valign="top">
			   $userINT.DISPLAY_NOTE
			    </td>
			   <td class="$userINT.rowCss"  align="center" valign="top">
			   
			   <a href="javascript:doDivAjaxCall$formname('div_viewPenggunaINT$userINT.USER_ID','viewPenggunaINT','internalType=INT&USER_ID=$userINT.USER_ID&USER_LOGIN=$userINT.USER_LOGIN&ID_JENISPEJABAT=$userINT.ID_JENISPEJABAT');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_PenggunaINT','showSenaraiPenggunaINT','USER_NAME=$userINT.NAMA_PENUH&ID_JENISPEJABAT=$userINT.ID_JENISPEJABAT&USER_ID=$userINT.USER_ID&FLAG_DELETE=Y&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		   
			   </td>
			   <!-- TEMP -->
			  
			  	   
		</tr>
		<tr  id="div_viewPenggunaINT$userINT.USER_ID">
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


	#if($PrintlistPenggunaINT.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_INT');
	var butoncetakUserCT =  document.getElementById('cmdCetakBorangPenggunaCT_INT');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
		butoncetakUserCT.style.display = "";
    }
	</script>
	#end
	<div id="SenaraiForPrint_INT">	
	</div>



</td>
</tr>
</table>
</fieldset>
<script>
if( $jquery('#TABLE_CT_ID_INT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_INT').offset().top);
}
</script>
#if($FlagCari=="Y")
<script>   
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_PenggunaOnline','showSenaraiPenggunaOnline','FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
</script>
#end