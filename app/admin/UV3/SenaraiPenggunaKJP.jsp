


<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_KJP').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_KJP').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_KJP').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_KJP').style.display = "";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "";
</script>
#end
<!-- close ct -->

<fieldset id="div_rowPenggunaKJP">


#if($SuccessMesejDeleteUser!="")
<div class="info" id="div_rowPenggunaKJP_deletemesej">
	$SuccessMesejDeleteUser	
</div>
<script>
$jquery("#div_rowPenggunaKJP_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowPenggunaKJP').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPenggunaKJP').offset().top);
}
</script>
#end

<table width="100%" id="div_viewPenggunaKJP" style="display:none">
<tr >
</tr>
</table>



<table width="100%">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listPenggunaKJP.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/UV3/record_paging_V3.jsp")
		   </td>
	#if($listPenggunaKJP.size()==1)
	<script>
	$jquery("#showUserCount_KJP").html("("+$totalRecords+" USER FOUND)");
	</script>
	#else
	<script>
	$jquery("#showUserCount_KJP").html("("+$totalRecords+" USERS FOUND)");
	</script>
	#end
		     
	</tr>
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">ID Pengguna</td>
		  <!-- <td   align="left" valign="top">MyID</td> --> 
		   <td   align="left" valign="top">Nama</td>
		   <td   align="left" valign="top">Emel</td>
		   <td   align="left" valign="top">Kementerian</td>
		   <td   align="left" valign="top">Agensi</td>
		   <td   align="left" valign="top">Tugasan</td>
		   <td   align="left" valign="top">Log Masuk Terakhir</td>
		   <td   align="center" valign="top">Status</td>	 
		   <td   align="center" valign="top">Catatan</td>	 
		   <td   align="center" valign="top">Tindakan</td>	   
	</tr>
	#if($listPenggunaKJP.size()>0)
	
	
		#foreach($userKJP in $listPenggunaKJP)
		<tr id="div_rowPenggunaKJP$userKJP.USER_ID">
			   <td class="$userKJP.rowCss"  align="center" valign="top" >$userKJP.BIL </td>
			   <td class="$userKJP.rowCss"  align="left" valign="top">
			   #set($span1 = "span1listPenggunaKJP"+$userKJP.BIL)
			   <span id="$span1"> 
			   $userKJP.USER_LOGIN
			   </span>
			   
			   <!-- tumpang -->
			   #set($span2 = "span2listPenggunaKJP"+$userKJP.BIL)
			   <span id="$span2"> 
			   </span>
			   </td>
			   <!--  
			   <td class="$userKJP.rowCss"  align="left" valign="top">
			   #set($span2 = "span2listPenggunaKJP"+$userKJP.BIL)
			   <span id="$span2"> 
			    #if($userKJP.NO_KP_BARU!="")				
					$userKJP.NO_KP_BARU1 - $userKJP.NO_KP_BARU2 - $userKJP.NO_KP_BARU3				
				#end
			   </span>
			   -->
			   
			   </td>
			   <td class="$userKJP.rowCss"  align="left" valign="top">	
			   #set($span3 = "span3listPenggunaKJP"+$userKJP.BIL)
			   <span id="$span3"> 	  
			   $userKJP.NAMA_PENUH
			    </span>
			   </td>
			   
			   
			   <td class="$userKJP.rowCss"  align="left" valign="top">
			   
			   #set($span4 = "span4listPenggunaKJP"+$userKJP.BIL)
			   <span id="$span4">  
			   $userKJP.EMEL
			   </span>
			   
			   </td>
			   <td class="$userKJP.rowCss"  align="left" valign="top">
			   
			   #set($span5 = "span5listPenggunaKJP"+$userKJP.BIL)
			   <span id="$span5">  
			   $userKJP.NAMA_KEMENTERIAN
			   </span>
			   
			   </td>
			   
			   <td class="$userKJP.rowCss"  align="left" valign="top">
			   
			   #set($span6 = "span6listPenggunaKJP"+$userKJP.BIL)
			   <span id="$span6">  
			   $userKJP.NAMA_AGENSI
			   </span>
			   
			   </td>
			   
			   <td class="$userKJP.rowCss"  align="left" valign="top">
			   
			   #set($span7 = "span7listPenggunaKJP"+$userKJP.BIL)
			   <span id="$span7">  
			   $userKJP.NAMA_JAWATAN
			   </span>
			   
			   </td>
			   
			    
			  
			  
			   <td class="$userKJP.rowCss"  align="left" valign="top">$userKJP.WAKTU_AKHIR_LOGIN</td>
			   <td class="$userKJP.rowCss"  align="center" valign="top">$userKJP.KEAKTIFAN</td>	 
			   <td class="$userKJP.rowCss"  align="left" valign="top">
			   $userKJP.DISPLAY_NOTE
			    </td>
			   <td class="$userKJP.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewPenggunaKJP$userKJP.USER_ID','viewPenggunaKJP','internalType=KJP&USER_ID=$userKJP.USER_ID&USER_LOGIN=$userKJP.USER_LOGIN');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_PenggunaKJP','showSenaraiPenggunaKJP','USER_NAME=$userKJP.NAMA_PENUH&USER_ID=$userKJP.USER_ID&FLAG_DELETE=Y&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		   
			   </td>
			   <!-- TEMP -->
			  
			  	   
		</tr>
		<tr  id="div_viewPenggunaKJP$userKJP.USER_ID">
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
	
	#if($PrintlistPenggunaKJP.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_KJP');
	var butoncetakUserCT =  document.getElementById('cmdCetakBorangPenggunaCT_KJP');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
		butoncetakUserCT.style.display = "";
    }
	</script>
	#end
	<div id="SenaraiForPrint_KJP">	
	</div>
	
</td>
</tr>
</table>
</fieldset>
<script>
if( $jquery('#TABLE_CT_ID_KJP').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_KJP').offset().top);
}
</script>
#if($FlagCari=="Y")
<script>   
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_PenggunaINT','showSenaraiPenggunaINT','FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
</script>
#end
