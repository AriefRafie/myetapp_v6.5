<script>
if( $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').offset().top);
}
else
{
	
	if( $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI').offset().top);
	}
	
}
</script>

<tr id="div_detailIntegrasi$detailIntegrasi.ID_INTEGRASI">
<td align="left" valign="top" colspan="14">
<fieldset>
<legend>Maklumat Integrasi Antara Sistem </legend>
<table width="100%" border="0">
<tr>
<td valign="top"  width="1%"></td>
<td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td>
<td valign="top"  width="70%">
<!-- $viewPengguna --></td>
</tr>
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				 Nama Sistem
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.NAMA
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Lain Sistem
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.NAMA_LAIN
				</td>
			</tr>	
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jenis Capaian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.ALAMAT1
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
				$detailIntegrasi.EMEL
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
				#if($detailIntegrasi.FLAG_AKTIF=="1" || $detailIntegrasi.FLAG_AKTIF=="") Aktif
                #else Tidak Aktif
                #end
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

<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Mula Dibangunkan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
              
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Mula Digunapakai
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
               
				</td>
			</tr>
    

         <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" ><br />
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
		</tr>
        
        <!--<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daftar oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$detailIntegrasi.USER_NAME
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
				$detailIntegrasi.TARIKH_MASUK
				</td>
			</tr>-->
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
				$detailIntegrasi.USER_NAME
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
				$detailIntegrasi.TARIKH_KEMASKINI
				</td>
		</tr>
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" ><br />
				</td>
				<td valign="top" >
				</td>
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
				<td >
    
 
    <input type="button" id="cmdEditAgensi" name="cmdEditAgensi" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewInteg$detailIntegrasi.ID_INTEGRASI','addInteg','Type=$Type&ID_INTEGRASI=$detailIntegrasi.ID_INTEGRASI');" >

<input type="button" id="cmdTutupAgensi" name="cmdTutupAgensi" value="Tutup" onClick="doDivAjaxCall$formname('div_viewInteg$detailIntegrasi.ID_INTEGRASI','closeCarian','');" >   	
<input type="button" id="CetakIntegrasi_$Type" name="CetakIntegrasi_$Type"
onClick="doDivAjaxCall$formname('DetailsForPrint_$Type$detailIntegrasi.ID_INTEGRASI','viewDetailsIntegrasi','FlagCetak=Y&Type=$Type&ID_INTEGRASI=$detailIntegrasi.ID_INTEGRASI');" 
				 value="Cetak Laporan" />
<br>
</td>		
</tr>	
</table>

<div id="DetailsForPrint_$Type$detailIntegrasi.ID_INTEGRASI" style="display:none"></div>
</fieldset>


<br />
<fieldset>
<legend>Senarai Dokumen </legend>


<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listDokumen.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/Integrasi/record_pagingDoc.jsp")
		   </td>
      
	</tr>
	#end 
  <tr>
  <td>
  <input size="50" id="tambahButton" name="tambahButton" onClick="document.getElementById('tambahDocDiv').style.display='';" type="button" value="Tambah Dokumen">
  <div id="tambahDocDiv" style="display:none">
   <fieldset>
<legend><strong>Muatnaik Lampiran</strong></legend>
	<table width="100%">
		<!--<tr>
			<td width="29%" align="right" scope="row">Tajuk Dokumen</td>
			<td width="1%" scope="row">:</td>
			<td width="70%">
				<input size="30" name="tajukDokumen" type="text" id="tajukDokumen" value="$!tajukDokumen" />
			</td>
		</tr>
		
		<tr>
			<td width="29%" align="right" scope="row">Jenis Dokumen</td>
			<td width="1%" scope="row">:</td>
			<td width="20%">
				<input size="30" name="jenisDokumen" type="text" id="jenisDokumen" value="$!jenisDokumen" />
			</td>
		</tr>
        
        <tr>
			<td width="29%" align="right" scope="row" valign="top">Keterangan Dokumen</td>
			<td width="1%" scope="row">:</td>
			<td width="20%">
				<textarea name="keteranganDokumen" id="" cols="50" rows="5"></textarea>
			</td>
		</tr>-->
		
        <tr>
			<td width="29%" align="right" scope="row"></td>
			<td width="1%" scope="row">:</td>
			<td width="70%">
				<input size="50" id="tambahDoc" name="tambahDoc" onChange="uploadDoc(this,'$detailIntegrasi.ID_INTEGRASI','displayDoc$detailIntegrasi.ID_INTEGRASI');" type="file">	
			</td>
		</tr>
        
	</table>
    </fieldset>	
  </div>
  </td>
  </tr> 

<tr>
<td>
<div id="displayDoc$detailIntegrasi.ID_INTEGRASI">
<table border="0" cellspacing="1" cellpadding="1" width="100%" >
<tr class="table_header" >
<td width="16%"   align="center" valign="top">Bil.</td>
<!--<td width="18%"   align="center" valign="top">Kod</td>-->
<td width="46%"   align="center" valign="top">Nama Dokumen </td>
<td width="20%"   align="center" valign="top">Tindakan</td>

		   
	</tr>
	#if($listDokumen.size()>0)
	<!--gred = userHQ-->
#foreach($list in $listDokumen)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" >$list.BIL.</td>
<!--<td  align="center" valign="top">($list.KOD_AGENSI)</td>-->
<td  align="left" valign="top"><a href="javascript:paparDoc('$list.ID_DOKUMEN');"><font color="blue"><u>$list.NAMA_DOKUMEN</u></font></a></td>

<td align ="center" valign="top">
<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('displayDoc$detailIntegrasi.ID_INTEGRASI','showDokumen','Type=$Type&FLAG_DELETE=Y&ID_INTEGRASI=$list.ID_INTEGRASI&ID_DOKUMEN=$list.ID_DOKUMEN');}"><img src="../img/delete.gif" border="0"></a>

</td> 
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>
</div>
</td>
</tr>
</table>
</fieldset>

<br />
<fieldset>
<legend>Senarai Transaksi </legend>


<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listTrans.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/Integrasi/record_paging.jsp")
		   </td>
      
	</tr>
	#end 
  <tr>
  <td>
 <!-- <input size="50" id="tambahDoc2" name="tambahDoc2" onchange="uploadDoc(this,'$detailIntegrasi.ID_INTEGRASI','displayDoc$detailIntegrasi.ID_INTEGRASI');" type="file">--></td>
  </tr> 

<tr>
<td>
<div id="displayDoc$detailIntegrasi.ID_INTEGRASI">
<table border="0" cellspacing="1" cellpadding="1" width="100%" >
<tr class="table_header" >
<td width="16%"   align="center" valign="top">Bil.</td>
<!--<td width="18%"   align="center" valign="top">Kod</td>-->
<td width="46%"   align="center" valign="top">Nama Dokumen </td>
<td width="20%"   align="center" valign="top">Tindakan</td>

		   
	</tr>
	#if($listTrans.size()>0)
	<!--gred = userHQ-->
#foreach($list in $listTrans)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" >$list.BIL.</td>
<!--<td  align="center" valign="top">($list.KOD_AGENSI)</td>-->
<td  align="left" valign="top"><a href="javascript:paparDoc('$list.ID_DOKUMEN');"><font color="blue"><u>$list.NAMA_DOKUMEN</u></font></a></td>

<td align ="center" valign="top">
<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('displayDoc$detailIntegrasi.ID_INTEGRASI','showDokumen','Type=$Type&FLAG_DELETE=Y&ID_INTEGRASI=$list.ID_INTEGRASI&ID_DOKUMEN=$list.ID_DOKUMEN');}"><img src="../img/delete.gif" border="0"></a>

</td> 
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>
</div>
</td>
</tr>
</table>
</fieldset>

</td>
</tr>

