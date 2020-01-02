
	<fieldset id="div_ListAT">
    
	<legend><b>AKTIVITI / AUDITTRAIL</b>
    <!--(JUMLAH KESELURUHAN AKTVITI : <b>$totalRecords</b>)-->
    </legend>
	
	<table style="border-collapse:collapse;" width="100%" align="center" cellpadding="2" cellspacing="1">
    
    <!--#if ($auditTrail.size() >0)
	<tr >
    <td  align="left" valign="top" colspan="14" >
    #parse("app/admin/AuditTrail/record_paging.jsp")
	</td>
	</tr>
	#end -->
    
	<tr class="table_header" style="border :1px solid black; font-size:100%">
	<td width="3%" style="border :1px solid black; font-size:100%"><strong>Bil</strong></td>
    <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
    <td width="12" style="border :1px solid black; font-size:100%"><strong>No Fail</strong></td>
	<td width="28%" style="border :1px solid black; font-size:100%"><strong>Keterangan</strong></td>
    <td width="10%" style="border :1px solid black; font-size:100%"><strong>Jenis Aktiviti</strong></td>
    <td width="20%" style="border :1px solid black; font-size:100%"><strong>Modul</strong></td>
	<td width="10%" style="border :1px solid black; font-size:100%"><strong>IP Address</strong></td>
	<td width="17%" style="border :1px solid black; font-size:100%"><strong>Nama Pengguna</strong></td>
	<td width="12" style="border :1px solid black; font-size:100%"><strong>Masa Aktiviti</strong></td>
   <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
    <td width="12" style="border :1px solid black; font-size:100%"><strong>Tarikh Akhir Log Masuk</strong></td>
  	<td width="12" style="border :1px solid black; font-size:100%"><strong>Status</strong></td>
</tr>
<!-- Table Content -->
#if ($auditTrail.size() >0)
#foreach($list in $auditTrail)
<tr class="$row">
<td valign="top" style="border :1px solid black; font-size:90%">$list.BIL</td>

<td valign="top" style="border :1px solid black; font-size:90%">$list.keterangan</td>
<td valign="top" style="border :1px solid black; font-size:90%">

#if($list.jenis_aktiviti == "INS")
Insert (Kemasukan)
#end
                            
#if($list.jenis_aktiviti == "DEL")
Delete (Hapus)
#end
                            
#if($list.jenis_aktiviti == "UPD")
Update (Kemaskini)
#end</td>
<td valign="top" style="border :1px solid black; font-size:90%"><!-- $list.seksyen -->

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
        #end</td>
 <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
<td valign="top" style="border :1px solid black; font-size:90%">$list.NO_FAIL</td>

<td valign="top" style="border :1px solid black; font-size:90%">$list.ip_address</td>
<td valign="top" style="border :1px solid black; font-size:90%">$list.user_name</td>
<td valign="top" style="border :1px solid black; font-size:90%">$list.masa_aktiviti</td>

 <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
<td valign="top" style="border :1px solid black; font-size:90%">$list.dot</td>
<td valign="top" style="border :1px solid black; font-size:90%">$list.FLAG_AKTIF</td>
</tr>
#end
#else 
<tr ><td  align="left" valign="top" colspan="14" >Tiada Rekod</td></tr>
#end
<input type=hidden name=page value=$page>
</table>
</fieldset>
	
<script>
$jquery(document).ready(function () {
		printHideDiv('SenaraiForPrint');
		
/* 		var divPrint =  document.getElementById('cmdCetakPejabat');
		divPrint.style.display = "none"; */
		
		var SenaraiForPrint =  document.getElementById('SenaraiForPrint');
		SenaraiForPrint.style.display = "none";
		
	});
</script>