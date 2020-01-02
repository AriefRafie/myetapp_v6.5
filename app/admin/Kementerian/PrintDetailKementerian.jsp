<div>
<table width="100%" border="0">
<tr>
<td valign="top"  width="1%"></td>
<td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td>
<td valign="top"  width="70%">
<!-- $viewPengguna --></td>
</tr>
<!--  <input type=text name="ID_KEMENTERIAN" value="$detailsKementerian.ID_KEMENTERIAN">-->
             
       
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				 Nama Kementerian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.NAMA_KEMENTERIAN
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kod Kementerian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.KOD_KEMENTERIAN
				</td>
			</tr>	
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.ALAMAT1
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
				$detailsKementerian.ALAMAT2
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
				$detailsKementerian.ALAMAT3
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
				$detailsKementerian.POSKOD
				</td>
			</tr> 
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				 Nama Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.NAMA_NEGERI
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Emel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.EMEL
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				URL Portal
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				www.nre.gov.my
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Koordinat GPS
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				??
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Aktif/Tidak Aktif
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				Tiada catatan
				</td>
			</tr>
			
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daftar oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.ID_MASUK
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Daftar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.TARIKH_MASUK
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kemaskini oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.ID_KEMASKINI
				</td>
			</tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Kemaskini
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailsKementerian.TARIKH_KEMASKINI
				</td>
		</tr>
           <!-- <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td >
                
 <input type="button" id="cmdEditGred" name="cmdEditGred" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewKementerian$detailsKementerian.ID_KEMENTERIAN','addKementerian','ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN');" >
			
<input type="button" id="cmdTutupGred" name="cmdTutupGred" value="Tutup" onClick="doDivAjaxCall$formname('div_viewKementerian$detailsKementerian.ID_KEMENTERIAN','closeDiv','');" >   	

<input type="button" id="cmdCetakPejabat" name="cmdCetakPejabat" value="Cetak"
onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('SenaraiForPrintAgensi','cetakDetailsKementerian','FlagCetak=Y&ID_KEMENTERIAN=$detailsKementerian.ID_KEMENTERIAN');"  />
<br>
</td>		
</tr>	-->
</table>
<br />
<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<font size="3"><strong> Senarai Agensi </strong></font>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">

</td>
</tr>
</table> 

<table border="0" cellpadding="2" cellspacing="2" align="center" width="98%">
<tr width="100%" >
<td colspan="14">
<table width="100%" align="center">
<tr>
<td>
<table border="0" cellspacing="1" cellpadding="1" width="100%" >  
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Kod</td>
<td   align="left" valign="top">Nama Agensi</td>

		   
	</tr>
	#if($listAgensi.size()>0)
	
	<!--gred = userHQ-->
#foreach($list in $listAgensi)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" >$list.BIL</td>
<td  align="left" valign="top">$list.KOD_AGENSI</td>
<td  align="left" valign="top">$list.NAMA_AGENSI</td> 
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

<script>
$jquery(document).ready(function () {
		printHideDiv('SenaraiForPrintAgensi');
		
		var divPrint =  document.getElementById('cmdCetakPejabat');
		divPrint.style.display = "none";
		
		var SenaraiForPrint =  document.getElementById('SenaraiForPrintAgensi');
		SenaraiForPrint.style.display = "none";
		
	});
</script>