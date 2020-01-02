#set ($NAMA_NEGERITEMP = '')
#set ($setahun = 1)
#if ($mula_bulan == '1')
	#set ($mula_bulan = 'JANUARI')
	#set ($bulan_mohon = 'OGOS')
	#set ($tahun_mohon = $tahun_sebelum)
#elseif ($mula_bulan == '2')
	#set ($mula_bulan = 'FEBRUARI')
	#set ($bulan_mohon = 'SEPTEMBER')
	#set ($tahun_mohon = $tahun_sebelum)
#elseif ($mula_bulan == '3')
	#set ($mula_bulan = 'MAC')
	#set ($bulan_mohon = 'OKTOBER')
	#set ($tahun_mohon = $tahun_sebelum)
#elseif ($mula_bulan == '4')
	#set ($mula_bulan = 'APRIL')
	#set ($bulan_mohon = 'NOVEMBER')
	#set ($tahun_mohon = $tahun_sebelum)
#elseif ($mula_bulan == '5')
	#set ($mula_bulan = 'MEI')
	#set ($bulan_mohon = 'DISEMBER')
	#set ($tahun_mohon = $tahun_sebelum)
#elseif ($mula_bulan == '6')
	#set ($mula_bulan = 'JUN')
	#set ($bulan_mohon = 'JANUARI')
	#set ($tahun_mohon = $mula_tahun)
#elseif ($mula_bulan == '7')
	#set ($mula_bulan = 'JULAI')
	#set ($bulan_mohon = 'FEBRUARI')
	#set ($tahun_mohon = $mula_tahun)
#elseif ($mula_bulan == '8')
	#set ($mula_bulan = 'OGOS')
	#set ($bulan_mohon = 'MAC')
	#set ($tahun_mohon = $mula_tahun)
#elseif ($mula_bulan == '9')
	#set ($mula_bulan = 'SEPTEMBER')
	#set ($bulan_mohon = 'APRIL')
	#set ($tahun_mohon = $mula_tahun)
#elseif ($mula_bulan == '10')
	#set ($mula_bulan = 'OKTOBER')
	#set ($bulan_mohon = 'MEI')
	#set ($tahun_mohon = $mula_tahun)
#elseif ($mula_bulan == '11')
	#set ($mula_bulan = 'NOVEMBER')
	#set ($bulan_mohon = 'JUN')
	#set ($tahun_mohon = $mula_tahun)
#elseif ($mula_bulan == '12')
	#set ($mula_bulan = 'DISEMBER')
	#set ($bulan_mohon = 'JULAI')
	#set ($tahun_mohon = $mula_tahun)
#end
<!-- Start Styles. Move the 'style' tags and everything between them to between the 'head' tags -->
<style type="text/css">
.myTable { background-color:#fff;border-collapse:collapse; }
.myTable th { background-color:#0066CC;color:#000; }
.myTable td, .myTable th { padding:5px;border:1px solid #000; }
</style>
<!-- End Styles -->
<p align = "center"><b><font face="verdana" size="2">LAPORAN PRESTASI PENCAPAIAN KPI BULANAN SEKSYEN $section</font></b></p>
<p align = "center"><b><font face="verdana" size="2">LAPORAN BAGI $mula_bulan $mula_tahun</font></b></p>


  

<table class="myTable" table align="center" width="92%" border="1" cellspacing="0" cellpadding="0">
<tr>
	<th width="25%" align="center">
	<font face="verdana" size="1" color="#fff">NEGERI</font>
	<th width="25%" align="center">
	<font face="verdana" size="1" color="#fff">UNIT</font>
	</th>
	<th width="25%" align="center">
	<font face="verdana" size="1" color="#fff">DAERAH</font>
	</th>
	<th width="10%" align="center">
	<font face="verdana" size="1" color="#fff">PERMOHONAN YANG DITERIMA BAGI BULAN $bulan_mohon $tahun_mohon</font>
	</th>
	<th width="10%" align="center">
	<font face="verdana" size="1" color="#fff">PERMOHONAN SELESAI</font>
	</th>
	<th width="5%" align="center">
	<font face="verdana" size="1" color="#fff">%</font>
	</th>
	
</tr>
#set ($JUMLAH_KPI_SEMUA1 = 0.00)

#set ($count = 0)
##set ($JUMLAH_MOHON_SEMUA = 0)
##set ($JUMLAH_SELESAI_SEMUA = 0)
##set ($JUMLAH_KPI_SEMUA = 0)
#foreach($list in $SenaraiLaporan)
	#set ($count = $count + 1)
	##set($JUMLAH_MOHON=$list.JUMLAH_MOHON)
	##set($JUMLAH_MOHON_SEMUA = ($JUMLAH_MOHON_SEMUA + $JUMLAH_MOHON))
	##set($JUMLAH_SELESAI = $list.JUMLAH_SELESAI)
	##set($JUMLAH_SELESAI_SEMUA = ($JUMLAH_SELESAI_SEMUA + $JUMLAH_SELESAI))
#end
##set ($JUMLAH_MOHON_SEMUA = $JUMLAH_MOHON_SEMUA/2)
##set ($JUMLAH_SELESAI_SEMUA = $JUMLAH_SELESAI_SEMUA/2)
##set ($JUMLAH_KPI_SEMUA = $EkptgUtil.parseDouble($JUMLAH_SELESAI_SEMUA) * $EkptgUtil.parseDouble(100))

#set ($count1 = 1)        
#foreach($list in $SenaraiLaporan)
	  #set ($ID_NEGERI = $list.ID_NEGERI)
	  #set ($KPI = $list.KPI)
      #set($NAMA_NEGERI=$list.NAMA_NEGERI)
      #set($NAMA_PEJABAT=$list.NAMA_PEJABAT)
      #set($NAMA_DAERAH=$list.NAMA_DAERAH)
      #set($JUMLAH_MOHON=$list.JUMLAH_MOHON)
      #set($JUMLAH_SELESAI=$list.JUMLAH_SELESAI)
      #set($LAYER=$list.LAYER)

   
   #if ($LAYER==2)
<tr bgcolor="#FCD5B4">

	<td colspan=2 align="center" >
	<b>$NAMA_PEJABAT </b>
	</td>
	<td width="10%" align="center">
	<b>$JUMLAH_MOHON</b>
	</td>
	<td width="10%" align="center">
	<b>$JUMLAH_SELESAI</b>
	</td>
	<td width="5%" align="center">
	<font color="red"><b>$KPI</b></font>
	</td>
	
</tr>
#else
<tr>
#if ($count1 == 1)
<td width="25%" align="center" rowspan="$count">
	#if ($NAMA_NEGERITEMP == $NAMA_NEGERI)
		</td>
	#else
		
		<b>$NAMA_NEGERI</b></td>
	#end
#set ($count1 = $count1+1)
#end
	<td width="25%" align="center">
	#if ($NAMA_PEJABATTEMP == $NAMA_PEJABAT)
	</td>
	#else
	$NAMA_PEJABAT</td>
	#end
	<td width="25%" align="center">
	$NAMA_DAERAH
	</td>
	<td width="10%" align="center">
	$JUMLAH_MOHON
	</td>
	<td width="10%" align="center">
	$JUMLAH_SELESAI
	</td>
	<td width="5%" align="center">

	<font color="red"><b>$KPI</b></font>
	</td>
	
</tr>

#end
#set ($NAMA_NEGERITEMP = $NAMA_NEGERI)
#set ($NAMA_PEJABATTEMP = $NAMA_PEJABAT)
#end
<tr bgcolor="#ffff00">

	<td colspan=2 align="center" >
	<font size="2"><b>JUMLAH KESELURUHAN</b></font>
	</td>
	<td width="10%" align="center">
		<font size="2"><b>$jumlahMohon</b></font>
	</td>
	<td width="10%" align="center">
		<font size="2"><b>$jumlahSelesai</b></font>
	</td>
	<td width="5%" align="center">
		<font color="red" size="2"><b>$jumlahPeratus</b></font>
	</td>
	
</tr>
</table>

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