<!-- open ct -->
#if($CT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CT_CARIAN_MOHON').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_MOHON').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$MOHONernalType').className = "classFade";
</script>
#end
#if($CT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CT_CARIAN_MOHON').style.display = "none";
	document.getElementById('span_LINK_CT_CARIAN_MOHON').style.display = "";
	//document.getElementById('div_CT_CARIAN_$MOHONernalType').className = "";
</script>
#end
<!-- close ct -->


<fieldset id="div_rowPenggunaMOHON">


#if($SuccessMesejDeleteUser!="")
<div class="info" id="div_rowPenggunaMOHON_deletemesej">
	$SuccessMesejDeleteUser	
</div>
<script>
$jquery("#div_rowPenggunaMOHON_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowPenggunaMOHON').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPenggunaMOHON').offset().top);
}
</script>
#end

<table width="100%" id="div_viewPenggunaMOHON" style="display:none">
<tr >
</tr>
</table>




<table width="100%">
<tr>
<td>
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listPenggunaMOHON.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/UV3/record_paging_V3.jsp")
		   </td>
	#if($listPenggunaMOHON.size()==1)
	<script>
	$jquery("#showUserCount_MOHON").html("("+$totalRecords+" USER FOUND)");
	</script>
	#else
	<script>
	$jquery("#showUserCount_MOHON").html("("+$totalRecords+" USERS FOUND)");
	</script>
	#end
		     
	</tr>
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">MyID</td>
		   <td   align="left" valign="top">Nama</td>
		   <td   align="left" valign="top">Emel</td>
		   <td   align="left" valign="top">Negeri</td>
		   <td   align="left" valign="top">Bahagian</td>
		   <td   align="left" valign="top">Unit</td>
		   <td   align="left" valign="top">Tarikh Daftar</td>
		   <td   align="left" valign="top">Status</td>
		   <td   align="center" valign="top">Tindakan</td>	   
	</tr>
	#if($listPenggunaMOHON.size()>0)
	
	
		#foreach($userMOHON in $listPenggunaMOHON)
		<tr id="div_rowPenggunaMOHON$userMOHON.ID_PERMOHONAN">
			   <td class="$userMOHON.rowCss"  align="center" valign="top" >$userMOHON.BIL </td>
			   
			   <td class="$userMOHON.rowCss"  align="left" valign="top">
			   #set($span1 = "span1listPenggunaMOHON"+$userMOHON.BIL)
			   <span id="$span1"> 
			   $userMOHON.NOKP
			   </span>
			   </td>
			   
			   <td class="$userMOHON.rowCss"  align="left" valign="top">	
			   #set($span2 = "span2listPenggunaMOHON"+$userMOHON.BIL)
			   <span id="$span2"> 	  
			   $userMOHON.NAMA
			   </span>
			   </td>
			   
			   
			   <td class="$userMOHON.rowCss"  align="left" valign="top">			   
			   #set($span3 = "span3listPenggunaMOHON"+$userMOHON.BIL)
			   <span id="$span3">  
			   $userMOHON.EMEL
			   </span>			   
			   </td>
			   
			   <td class="$userMOHON.rowCss"  align="left" valign="top">			   
			   #set($span4 = "span4listPenggunaMOHON"+$userMOHON.BIL)
			   <span id="$span4">  
			   $userMOHON.NEGERI
			   </span>			   
			   </td>
			   
			   
			   <td class="$userMOHON.rowCss"  align="left" valign="top">			   
			   #set($span5 = "span5listPenggunaMOHON"+$userMOHON.BIL)
			   <span id="$span5">  
			   $userMOHON.BAHAGIAN
			   </span>			   
			   </td>
			   
			   <td class="$userMOHON.rowCss"  align="left" valign="top">			   
			   #set($span6 = "span6listPenggunaMOHON"+$userMOHON.BIL)
			   <span id="$span6">  
			   $userMOHON.NAMA_UNIT
			   </span>			   
			   </td>
			   
			   
			   <td class="$userMOHON.rowCss"  align="left" valign="top"> 
			   $userMOHON.TARIKH_PENDAFTARAN
			   </td>
			   
			   <td class="$userMOHON.rowCss"  align="left" valign="top">
			   
			   #set($span7 = "span7listPenggunaMOHON"+$userMOHON.BIL)
			   <span id="$span7">  
			   $userMOHON.STATUS
			   </span>
			   
			   </td>
			   
			    
			  
			  
			   
			   <td class="$userMOHON.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewPenggunaMOHON$userMOHON.ID_PERMOHONAN','viewPenggunaMOHON','internalType=MOHON&ID_PERMOHONAN=$userMOHON.ID_PERMOHONAN');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_PenggunaMOHON','showSenaraiPenggunaMemohon','NAMA=$userMOHON.NAMA&ID_PERMOHONAN=$userMOHON.ID_PERMOHONAN&FLAG_DELETE=Y&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
               
	   		  </td>
			   <!-- TEMP -->
			  
			  	   
		</tr>
		<tr  id="div_viewPenggunaMOHON$userMOHON.ID_PERMOHONAN">
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
	
	#if($PrintlistPenggunaMOHON.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPenggunaCT_MOHON');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	#end
	<div id="SenaraiForPrint_MOHON">	
	</div>


</td>
</tr>
</table>
</fieldset>
#if($FlagCari=="Y")
<script>   
		  $jquery(document).ready(function () {
			 // doDivAjaxCall$formname('div_PenggunaINT','showSenaraiPenggunaINT','FlagCari=$FlagCari&carianTerperinci='+$jquery('#carianTerperinci').val());			 	  
		  });
</script>
#end


<script>
if( $jquery('#TABLE_CT_ID_MOHON').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_MOHON').offset().top);
}
</script>

