<!-- Start Styles. Move the 'style' tags and everything between them to between the 'head' tags -->
<style type="text/css">
.myTable { background-color:#eee;border-collapse:collapse; }
.myTable th { background-color:#fff;color:#000; }
.myTable td, .myTable th { padding:5px;border:1px solid #000; }
</style>
<!-- End Styles -->
<p align = "center"><font face="Arial" size="3"><b>SENARAI SEMAK RAYUAN</b></font> <br/>

</p>

<table class="tg" width="80%" align="center">
<tbody>
<tr>
<td width="20%"><strong><span style="font-family: Arial; font-size: small;">Nama Simati</span></strong></td>
<td>:</td>
<td><strong><span style="font-family: Arial; font-size: small;">$namaSimati</span></strong></td>
</tr>
<tr>
<td width="20%"><strong><span style="font-family: Arial; font-size: small;">Tarikh Mati</span></strong></td>
<td>:</td>
<td><strong><span style="font-family: Arial; font-size: small;">$tarikhMati</span></strong></td>
</tr>
<tr>
<td width="20%"><strong><span style="font-family: Arial; font-size: small;">No. Fail</span></strong></td>
<td>:</td>
<td><strong><span style="font-family: Arial; font-size: small;">$noFail</span></strong></td>
</tr>
</tbody>
</table>
<table class="myTable" border="0" width="80%" cellspacing="0" align="center">
<tbody>
<tr><th class="tg-efv9" rowspan="2"><strong><span style="font-family: Arial; font-size: small;">BIL</span></strong></th><th class="tg-efv9" rowspan="2"><strong><span style="font-family: Arial; font-size: small;">TINDAKAN</span></strong></th><th class="tg-efv9" colspan="2"><strong><span style="font-family: Arial; font-size: small;">LENGKAP</span></strong></th><th class="tg-efv9" rowspan="2"><strong><span style="font-family: Arial; font-size: small;">&nbsp;&nbsp;&nbsp;CATATAN&nbsp;&nbsp;&nbsp;</span></strong></th></tr>
<tr>
<td  align="center" width="5%"><strong><span style="font-family: Arial; font-size: small;">&nbsp;&nbsp;&nbsp;YA&nbsp;&nbsp;&nbsp;</span></strong></td>
<td  align="center" width="5%"><strong><span style="font-family: Arial; font-size: small;">TIDAK</span></strong></td>
</tr>
<tr>
<td class="tg-031e"  style="text-align: center;"><span style="font-family: Arial; font-size: small;">1.</span></td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Borang K1 lengkap diisi.</span></td>
<td class="tg-031e" align="center">/</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e">&nbsp;</td>
</tr>
<tr>
<td class="tg-031e"  style="text-align: center;"><span style="font-family: Arial; font-size: small;">2.</span></td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Notis Rayuan dikemukakan dalam</span></td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e">&nbsp;</td>
</tr>
<tr>
<td class="tg-031e">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Tempoh 14 hari daripada tarikh keputusan dibuat oleh Pentadbir</span></td>
<td class="tg-031e" align="center">#if ($checkradiobutton == 1) / #end</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e">&nbsp;</td>
</tr>
<tr>
<td class="tg-031e">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Tempoh masa yang dibenarkan oleh Mahkamah Tinggi</span></td>
<td class="tg-031e" align="center">#if ($checkradiobutton != 1) / #end</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e">&nbsp;</td>
</tr>
<tr>
<td class="tg-031e"  style="text-align: center;"><span style="font-family: Arial; font-size: small;">3.</span></td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Fee Rayuan sebanyak RM50.00 telah dijelaskan.</span></td>
<td class="tg-031e" align="center">/</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e">&nbsp;</td>
</tr>
<tr>
<td class="tg-031e">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">No. Resit</span></td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">$no_resit</span></td>
</tr>
<tr>
<td class="tg-031e">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Tarikh Bayaran</span></td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">$EtxdTarikhByrnFee</span></td>
</tr>
<tr>
<td class="tg-031e"  style="text-align: center;"><span style="font-family: Arial; font-size: small;">4.</span></td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Deposit bagi kos rayuan di atas nama Pentadbir telah dijelaskan.</span></td>
<td class="tg-031e" align="center">/</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e">&nbsp;</td>
</tr>
<tr>
<td class="tg-031e">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Jumlah Bayaran Deposit</span></td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">RM $EtxtJumDeposit</span></td>
</tr>
<tr>
<td class="tg-031e">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">No Resit</span></td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">$EtxtNomborResitDeposit</span></td>
</tr>
<tr>
<td class="tg-031e">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Tarikh Bayaran</span></td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">$EtxdTarikhByrnDeposit</span></td>
</tr>
<tr>
<td class="tg-031e"  style="text-align: center;"><span style="font-family: Arial; font-size: small;">5.</span></td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Berpuas hati notis rayuan telah diserahkan kepada semua pihak yang berkepentingan.</span></td>
<td class="tg-031e" align="center">/</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e">&nbsp;</td>
</tr>
<tr>
<td class="tg-031e">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">Tarikh Terima Rayuan</span></td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e" align="center">&nbsp;</td>
<td class="tg-031e"><span style="font-family: Arial; font-size: small;">$EtxdTarikhTerimaRayuan</span></td>
</tr>
</tbody>
</table>
<table class="tg" style="height: 135px;" width="80%" align="center">
<tbody>
<tr>
<td width="15%"><span style="font-family: Arial; font-size: small;"><strong><span style="text-decoration: underline;">Ulasan</span></strong></span></td>
<td>:</td>
<td width="60%"><span style="font-family: Arial; font-size: small;">[ Lengkap / Tidak Lengkap ] untuk diproses.</span></td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td><span style="font-family: Arial; font-size: small;"><strong>Nama</strong></span></td>
<td>:</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td><span style="font-family: Arial; font-size: small;"><strong>Jawatan</strong></span></td>
<td>:</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td><span style="font-family: Arial; font-size: small;"><strong>Tandatangan</strong></span></td>
<td>:</td>
<td>&nbsp;</td>
<td style="text-align: left;" align="right"><span style="font-family: Arial; font-size: small;"><strong>Tarikh :<br /></strong></span></td>
<td>&nbsp;</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>
<!-- 
<table>
<tr>
<td>
<input type="checkbox" disabled checked name="Borang_K1">
</td>
<td>
&nbsp;
</td>
<td>
Borang K1 lengkap diisi.
</td>
</tr>

<tr>
<td>
<input type="checkbox" disabled checked name="Notis_Rayuan">
</td>
<td>
&nbsp;
</td>
<td>
Notis Rayuan dikemukakan dalam
</td>
</tr>

<tr>
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>
<td>
#if ($checkradiobutton == 1)
	<input type="radio"  name="Tempoh_14hari" disabled checked> Tempoh 14 hari daripada tarikh keputusan dibuat oleh Pentadbir
#else
	<input type="radio"  name="Tempoh_14hari" disabled> Tempoh 14 hari daripada tarikh keputusan dibuat oleh Pentadbir
#end

</td>
</tr>

<tr>
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>
<td>
#if ($checkradiobutton == 2)
	<input type="radio"  name="Tempoh_MahkamahTinggi" disabled checked> Atau tempoh masa yang dibenarkan oleh Mahkamah Tinggi
#else
	<input type="radio"  name="Tempoh_MahkamahTinggi" disabled> Atau tempoh masa yang dibenarkan oleh Mahkamah Tinggi
#end

</td>
</tr>

<tr>
<td>
<input type="checkbox" disabled checked name="Fee_Rayuan">
</td>
<td>
&nbsp;
</td>
<td>
Fee Rayuan sebanyak RM50.00 telah dijelaskan.
</td>
</tr>

<tr>
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>
<td>
No. Resit&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: $no_resit
</td>
</tr>

<tr>
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>
<td>
Tarikh Bayaran : $EtxdTarikhByrnFee
</td>
</tr>

<tr>
<td>
<input type="checkbox" disabled checked name="Deposit">
</td>
<td>
&nbsp;
</td>
<td>
Deposit bagi kos rayuan di atas nama Pentadbir telah dijelaskan.
</td>
</tr>

<tr>
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>
<td>
Jumlah Bayaran Deposit : RM $EtxtJumDeposit
</td>
</tr>

<tr>
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>
<td>
No Resit : $EtxtNomborResitDeposit
</td>
</tr>

<tr>
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>
<td>
Tarikh Bayaran : $EtxdTarikhByrnDeposit
</td>
</tr>

<tr>
<td>
<input type="checkbox" disabled checked name="Berpuashati">
</td>
<td>
&nbsp;
</td>
<td>
Berpuas hati notis rayuan telah diserahkan kepada semua pihak yang berkepentingan.
</td>
</tr>

<tr>
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>
<td>
Tarikh Terima Rayuan : $EtxdTarikhTerimaRayuan
</td>
</tr>

</table>
 -->
<hr>
<p align = "center">

<form>
<input id="printpagebutton" type="button" value="Cetak" onclick="printpage()" >
<!--onClick="window.print()"  -->
</form>

</p>

<!-- Script untuk remove button print dalam cetakan -->
<script type="text/javascript">
    function printpage() {
        //Get the print button and put it into a variable
        var printButton = document.getElementById("printpagebutton");
        //Set the print button visibility to 'hidden' 
        printButton.style.visibility = 'hidden';
        //Print the page content
        window.print()
        //Set the print button to 'visible' again 
        //[Delete this line if you want it to stay hidden after printing]
        printButton.style.visibility = 'visible';
    }
</script>