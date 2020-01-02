<script>
if( $jquery('#'+'div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN').offset().top);
	}
	
}
</script>

<tr id="div_viewBahagianHQ$viewBahagianHQ.ID_SEKSYEN">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Bahagian - Ringkasan</legend>
<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%">
			<tr colspan="14">
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			   
            <tr colspan="14">
				<td valign="top" colspan="14"><strong>$viewBahagianHQ.NAMA_SEKSYEN</strong>				
				</td>			

			</tr>
            <tr>
            <td>
           <!-- <input type="text" id="ID_SEKSYEN" 
				name="ID_SEKSYEN" 
				value="$viewBahagianHQ.ID_SEKSYEN" >-->
        
            </td></tr>
            <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Lain Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.KOD_SEKSYEN
				</td>
			</tr>
			
			<!-- <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.ALAMAT1
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
				$viewBahagianHQ.ALAMAT2
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
				$viewBahagianHQ.ALAMAT3
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Poskod
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.POSKOD
				</td>
			</tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bandar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.BANDAR
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.NAMA_NEGERI
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No.Telefon
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.NO_TEL
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Faks</td>
				<td valign="top" >
				:</td>
				<td valign="top" >
				$viewBahagianHQ.NO_FAX
				</td>
			</tr> -->
			
			<tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >$viewBahagianHQ.CATATAN
				</td>
			</tr>
			
			
			<tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewBahagianHQ.FLAG_AKTIF
				</td>
			</tr>
    		
			<!-- <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
                
                <input type="button" id="cmdEditPengguna" name="cmdEditPengguna" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewBahagian$viewBahagianHQ.ID_SEKSYEN','editBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');" >
				<input type="button" id="BTNEDIT$internalType$viewPengguna.USER_ID" name="BTNEDIT$internalType$viewPengguna.USER_ID"   onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','edit_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Kemaskini" > 
                #if ($viewBahagianHQ.ID_SEKSYEN!= "")
	   			<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" onClick="doDivAjaxCall$formname('div_viewBahagian$viewBahagianHQ.ID_SEKSYEN','batalBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');" >
	   		#else	
	<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" onclick="doDivAjaxCall$formname('div_viewBahagian','batalBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');" value="Tutup" >
			#end
			<input type="button" id="cmdPrint" name="cmdPrint" onclick="doDivAjaxCall$formname('SenaraiForPrintDetail','printDetailsBahagian','ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN');" value="Cetak" >
			
			</td>
			</tr> -->
			
<tr  width="100%"><td valign="top" >				
				</td>
<td colspan="14">


<legend>Senarai Pengguna : </legend>
<input type="hidden" name="ID_SEKSYEN" value="$ID_SEKSYEN">
<br />
<fieldset id="div_rowPejabatUrusan">

<table width="100%">
<tr>
<td>
<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%">  
<tr class="table_header" >
		   <td width="1%"   align="center" valign="top">Bil.</td>
		   <td width="50%"   align="left" valign="top">Nama Pengguna</td>
           <td width="14%"   align="left" valign="top">No KP</td>
          <td width="30%"   align="left" valign="top">Jawatan</td>
           <td width="5%"   align="left" valign="top">Status</td>
	</tr>
	#if($listPengguna.size()>0)
	#foreach($pengguna in $listPengguna)
		<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
			   <td   align="center" valign="top" >$pengguna.BIL. </td>
			   <td  align="left" valign="top">$pengguna.USER_NAME </td>
			   <td  align="left" valign="top">$pengguna.NO_KP</td>
               <td  align="left" valign="top">$pengguna.NAMA_JAWATAN </td>
			   <td  align="center" valign="top">$pengguna.KEAKTIFAN</td>

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

<br />

<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<font size="3"><strong> Pengesahan </strong></font>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">

</td>
</tr>

<tr >
<td width="2%" >
</td>
<td width="58%" colspan="14">Perhatian : Sebelum menandatangani dokumen ini, sila pastikan maklumat yang telah diisi adalah SAH.</td>
</tr>
</table>

<br /><br /><br /><br />
<table border="0" cellpadding="2" cellspacing="2" align="center" width="98%">
<tr>
<td width="50%">
Disahkan Oleh :
<br />
<br />
<br />
<br />
.............................
<br />
Nama : 
<br />
Jawatan : 
<br />
Tarikh : 
<br />
Cop Rasmi : 
<br />
<br />
<br />
<br />

</td>

<td width="50%" align="">
Dimasukkan Oleh : (Admin Sistem)
<br />
<br />
<br />
<br />
.............................
<br />
Nama : 
<br />
Jawatan : 
<br />
Tarikh : 
<br />
Cop Rasmi : 
<br />
<br />
<br />
<br />

</fieldset>

</td>
</tr>
</table>
</fieldset>

	<br>
</td>		
</tr>

<script>
$jquery(document).ready(function () {
		printHideDiv('BorangSemakanPengguna');
		
/* 		var divPrint =  document.getElementById('cmdCetakPejabat');
		divPrint.style.display = "none"; */
		
		var BorangSemakanPengguna =  document.getElementById('BorangSemakanPengguna');
		BorangSemakanPengguna.style.display = "none";
		
	});
</script>