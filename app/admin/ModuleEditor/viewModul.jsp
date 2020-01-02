<fieldset>
<legend>$viewModul.MODULE_ID_ASAL</legend>
#if($SuccessMesej!="")
<div class="info" id="div_SuccessMesejModule$MODULE_GROUP$viewModul.MODULE_ID" >
		#if($SuccessMesej=="Insert")
		
			Maklumat Modul Berjaya Didaftar
		
		#elseif($SuccessMesej=="Update")
		
			Maklumat Modul Berjaya Dikemaskini
		
		#end
	</div>	
	<script >
	//alert($jquery('div_SuccessMesejModule$MODULE_GROUP$viewModul.MODULE_ID').length);
	$jquery("#div_SuccessMesejModule$MODULE_GROUP$viewModul.MODULE_ID").show().delay(3000).fadeOut();
	</script>
#end
<script>
	//alert("div_rowModul$MODULE_GROUP$viewModul.MODULE_ID : "+$jquery('#div_rowModul$MODULE_GROUP$viewModul.MODULE_ID').length);
	if( $jquery('#div_rowModul$MODULE_GROUP$viewModul.MODULE_ID').length)         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_rowModul$MODULE_GROUP$viewModul.MODULE_ID').offset().top);
	}
	else
	{
		if( $jquery('#div_rowModul').length)         // use this if you are using id to check
		{
			window.scrollTo(0, $jquery('#div_rowModul').offset().top);
		}
	}
</script>
<div id="printableArea_$MODULE_GROUP$viewModul.MODULE_ID">
<table border="0" width="100%" cellspacing="1" cellpadding="1" >
<tr>
<td width="1%" valign="top" align="center">
</td>
<td width="28%" valign="top" align="left">
</td>
<td width="1%" valign="top" align="center">
</td>
<td width="70%" valign="top" align="left">
</td>
</tr>
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
ID Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.MODULE_ID_ASAL
</td>
</tr>
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Nama Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.MODULE_TITLE
</td>
</tr>
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Class Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.MODULE_CLASS
</td>
</tr>
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Kumpulan Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.MODULE_GROUP
</td>
</tr>
#if($viewModul.MODULE_DESCRIPTION!="")
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Keterangan Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.MODULE_DESCRIPTION
</td>
</tr>
#end

<!--penambahan admin 18/1/2017-->
#if($viewModul.MODULE_VERSION!="")
<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Versi Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.MODULE_VERSION
</td>
</tr>
#end
<!--tamat-->

<tr id="div_DisplayRoleModule$MODULE_GROUP$viewModul.MODULE_ID" >
<td colspan="4" >
<script>	
				  $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_DisplayRoleModule$MODULE_GROUP$viewModul.MODULE_ID','showDisplayRole','MODULE_ID_TRIM=$viewModul.MODULE_ID&MODULE_ID=$viewModul.MODULE_ID_ASAL&MODULE_GROUP=$MODULE_GROUP');			 	  
				  });		
</script>
</td>
			
</tr>

<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Pendaftar Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.USER_ADD
</td>
</tr>

<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Tarikh Daftar Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.TARIKH_MASUK	
</td>
</tr>


<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Pengguna Terakhir Mengemaskini Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.USER_UPD
</td>
</tr>

<tr>
<td valign="top" align="center">
</td>
<td valign="top" align="left">
Tarikh Terakhir Mengemaskini Modul
</td>
<td valign="top" align="center">
:
</td>
<td valign="top" align="left">
$viewModul.TARIKH_KEMASKINI
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
				<input type="button" id="BTNEDIT$MODULE_GROUP$viewModul.MODULE_ID" name="BTNEDIT$MODULE_GROUP$viewModul.MODULE_ID"   onClick="editModule('$MODULE_GROUP','$viewModul.MODULE_ID','$viewModul.MODULE_ID_ASAL')" value="Kemaskini" > 
	   			<input type="button" id="BTNCLOSE$MODULE_GROUP$viewModul.MODULE_ID" name="BTNCLOSE$MODULE_GROUP$viewModul.MODULE_ID" onClick="closeModule('$MODULE_GROUP','$viewModul.MODULE_ID','$viewModul.MODULE_ID_ASAL')" value="Tutup" > 
	   			<input type="button" id="BTNPRINT$MODULE_GROUP$viewModul.MODULE_ID" name="BTNPRINT$MODULE_GROUP$viewModul.MODULE_ID" onclick="printDiv('printableArea_$MODULE_GROUP$viewModul.MODULE_ID')" value="Cetak" />
	   			</td>
			</tr>
			
</table>

</div>
</fieldset>
<br>

