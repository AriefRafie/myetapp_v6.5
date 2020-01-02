<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_Online').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_Online').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$OnlineernalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_Online').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_Online').style.display = "";
	//document.getElementById('div_CT_CARIAN_$OnlineernalType').className = "";
</script>
#end
<!-- close ct -->


<fieldset id="div_rowPenggunaOnline">


#if($SuccessMesejDeleteUser!="")
<div class="info" id="div_rowPenggunaOnline_deletemesej">
	$SuccessMesejDeleteUser	
</div>
<script>
$jquery("#div_rowPenggunaOnline_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowPenggunaOnline').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPenggunaOnline').offset().top);
}
</script>
#end

<table width="100%" id="div_viewPenggunaOnline" style="display:none">
<tr >
</tr>
</table>



<table width="100%">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listPenggunaOnline.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/UV3/record_paging_V3.jsp")
		   </td>
	#if($listPenggunaOnline.size()==1)
	<script>
	$jquery("#showUserCount_Online").html("("+$totalRecords+" USER FOUND)");
	</script>
	#else
	<script>
	$jquery("#showUserCount_Online").html("("+$totalRecords+" USERS FOUND)");
	</script>
	#end
		     
	</tr>
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">ID Pengguna</td>
		   <td   align="left" valign="top">Negeri</td>
		   <td   align="left" valign="top">Kategori</td>
		   <td   align="left" valign="top">MyID / MyCOID</td>
		   <td   align="left" valign="top">Nama</td>
		   <td   align="left" valign="top">Emel</td>
		   <td   align="left" valign="top">No. HP</td>
		   <td   align="left" valign="top">Log Masuk Terakhir</td>
		   <td   align="center" valign="top">Status</td>	 
		   <td   align="center" valign="top">Catatan</td>	 
		   <td   align="center" valign="top">Tindakan</td>	   
	</tr>
	#if($listPenggunaOnline.size()>0)
	
	
		#foreach($userOnline in $listPenggunaOnline)
		<tr id="div_rowPenggunaOnline$userOnline.USER_ID">
			   <td class="$userOnline.rowCss"  align="center" valign="top" >$userOnline.BIL </td>
			   <td class="$userOnline.rowCss"  align="left" valign="top">
			   #set($span1 = "span1listPenggunaOnline"+$userOnline.BIL)
			   <span id="$span1"> 
			   $userOnline.USER_LOGIN
			   </span>
			   </td>
			   
			   <td class="$userOnline.rowCss"  align="left" valign="top">
			   #set($span2 = "span2listPenggunaOnline"+$userOnline.BIL)
			   <span id="$span2"> 
			   $userOnline.NEGERI
			   </span>
			   
			   </td>
			   <td class="$userOnline.rowCss"  align="left" valign="top">
			   
			   
			  
			   $userOnline.KATEGORI
			  
			   
			   </td>
			   
			   
			   <td class="$userOnline.rowCss"  align="left" valign="top">
			   
			   #set($span3 = "span3listPenggunaOnline"+$userOnline.BIL)
			   <span id="$span3"> 
			    #if($userOnline.KATEGORI=="SYARIKAT")
					$userOnline.NO_PENGENALAN
				#elseif($userOnline.KATEGORI=="INDIVIDU")
					#if($userOnline.NO_PENGENALAN1!="")
					$userOnline.NO_PENGENALAN1 - $userOnline.NO_PENGENALAN2 - $userOnline.NO_PENGENALAN3
					#end
				#else
					#if($userOnline.NO_PENGENALAN1!="")
					$userOnline.NO_PENGENALAN1 - $userOnline.NO_PENGENALAN2 - $userOnline.NO_PENGENALAN3
					#end
				#end
			   </span>
			   
			   </td>
			   
			   
			   <td class="$userOnline.rowCss"  align="left" valign="top">
			   
			   #set($span4 = "span4listPenggunaOnline"+$userOnline.BIL)
			   <span id="$span4">  
			   $userOnline.NAMA_PENUH
			   </span>
			   
			   </td>
			   <td class="$userOnline.rowCss"  align="left" valign="top">
			   
			   #set($span5 = "span5listPenggunaOnline"+$userOnline.BIL)
			   <span id="$span5">  
			   $userOnline.EMEL
			   </span>
			   
			   </td>
			   
			    
			   <td class="$userOnline.rowCss"  align="left" valign="top">
			   
			   
			   $userOnline.NO_HP
			   
			   
			  
			   <td class="$userOnline.rowCss"  align="left" valign="top">$userOnline.WAKTU_AKHIR_LOGIN</td>
			   <td class="$userOnline.rowCss"  align="center" valign="top">$userOnline.KEAKTIFAN</td>	 
			   <td class="$userOnline.rowCss"  align="left" valign="top">
			   $userOnline.DISPLAY_NOTE
			    </td>
			   <td class="$userOnline.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewPenggunaOnline$userOnline.USER_ID','viewPenggunaOnline','internalType=Online&USER_ID=$userOnline.USER_ID&USER_LOGIN=$userOnline.USER_LOGIN');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_PenggunaOnline','showSenaraiPenggunaOnline','USER_NAME=$userOnline.NAMA_PENUH&USER_ID=$userOnline.USER_ID&FLAG_DELETE=Y&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		   
			   </td>
			   <!-- TEMP -->
			   #set($span6 = "span6listPenggunaOnline"+$userOnline.BIL)
			   <span id="$span6"></span>
			   #set($span7 = "span7listPenggunaOnline"+$userOnline.BIL)
			   <span id="$span7"></span>	   
		</tr>
		<tr  id="div_viewPenggunaOnline$userOnline.USER_ID">
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



	#if($PrintlistPenggunaOnline.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_Online');
	var butoncetakUserCT =  document.getElementById('cmdCetakBorangPenggunaCT_Online');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
		butoncetakUserCT.style.display = "";
    }
	</script>
	#end
	<div id="SenaraiForPrint_Online">	
	</div>
	
 
</td>
</tr>
</table>
</fieldset>
<script>
if( $jquery('#TABLE_CT_ID_Online').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_Online').offset().top);
}
</script>
#if($FlagCari=="Y")
<script>   
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_PenggunaMOHON','showSenaraiPenggunaMemohon','FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
</script>
#end


