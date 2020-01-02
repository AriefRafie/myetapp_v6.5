
	<fieldset id="div_ListAT">
    
	<legend><b>AKTIVITI / AUDITTRAIL</b>&nbsp;&nbsp;(JUMLAH KESELURUHAN AKTVITI : <b>$totalRecords</b>)</legend>
	
	<table width="100%"  border="0">
    
    #if ($auditTrail.size() >0)
	<tr >
    <td  align="left" valign="top" colspan="14" >
    #parse("app/admin/AuditTrail/record_paging.jsp")
	</td>
	</tr>
	#end 
    
	<tr class="table_header">
	<td width="3%">Bil</td>
    <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
    <td width="12%">No Fail / No Hak Milik / Nama Pengguna / Modul / dll</td>
	<td width="28%">Keterangan</td>
    <td width="10%">Jenis Aktiviti</td>
    <td width="20%">Modul</td>
	<td width="10%">IP Address</td>
	<td width="17%">Nama Pengguna</td>
	<!--<td width="12">Penambahan admin 5/1/2017</td>--> 
    <td width="12%">Tarikh Akhir Log Masuk</td>
  	<td width="12%">Status</td>
    <td width="12%">Masa Aktiviti</td>
</tr>
<!-- Table Content -->
#if ($auditTrail.size() >0)
#foreach($list in $auditTrail)
<tr >
<td valign="top" class="$list.rowCss">$list.BIL</td>
<td valign="top" class="$list.rowCss">$list.NO_FAIL</td>
<td valign="top" class="$list.rowCss">$list.keterangan</td>
<td valign="top" class="$list.rowCss">

#if($list.jenis_aktiviti == "INS")
Insert (Kemasukan)
#end
                            
#if($list.jenis_aktiviti == "DEL")
Delete (Hapus)
#end
                            
#if($list.jenis_aktiviti == "UPD")
Update (Kemaskini)
#end

</td>
<td valign="top" class="$list.rowCss"><!-- $list.seksyen -->

		#if($list.ID_SEKSYEN == 1)
        PENGURUSAN PENGAMBILAN TANAH
        #elseif($list.ID_SEKSYEN == 2)
        PEMBAHAGIAN PUSAKA KECIL
        #elseif($list.ID_SEKSYEN == 3)
        HARTA TANAH PERSEKUTUAN
        #elseif($list.ID_SEKSYEN == 4)
        PENGUATKUASA DAN HASIL PERSEKUTUAN
        #elseif($list.ID_SEKSYEN == 6)
        PENGURUSAN FAIL DAN DOKUMEN
        #end


</td>
 <!--<td width="12">Penambahan admin 5/1/2017</td>--> 


<td valign="top" class="$list.rowCss">$list.ip_address</td>
<td valign="top" class="$list.rowCss">$list.user_name</td>
 <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
<td valign="top" class="$list.rowCss">$list.dot</td>
<td valign="top" class="$list.rowCss">$list.FLAG_AKTIF</td>
<td valign="top" class="$list.rowCss">$list.masa_aktiviti</td>
</tr>
#end
#else 
<tr ><td  align="left" valign="top" colspan="14" >Tiada Rekod</td></tr>
#end
<input type=hidden name=page value=$page>
</table>

#if ($auditTrail.size() >0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetak');
	//alert(' masuk sini ');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	#end
	<div id="SenaraiForPrint">	
	</div>

	</fieldset>
	
	
