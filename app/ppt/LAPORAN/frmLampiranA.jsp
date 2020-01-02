#set($header="LAMPIRAN A")

<table style="border-collapse:collapse;" width="95%" align="center" cellpadding="2" cellspacing="1">
<thead >
<tr  >
<th colspan="24" align="center" style="font-size:90%;">$header<br><br></th>
</tr>
<!--
<tr  >
<th colspan="11" align="center"  style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" >Maklumat Projek</th>
<th colspan="11" align="center"  style="border:1px solid black; font-size:65%; background-color:#CCCCCC;">Tarikh-Tarikh Tindakan</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" rowspan="2" >Tempoh P.T (Bulan)</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" rowspan="2" >Catatan</th>
</tr>
-->
<tr >
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="left" >Bil.</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="left" >Kementerian</th>

<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="left" >Projek</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="left" >No. Fail</th>
<!--
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="left" >Daerah / Mukim</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Keluasan (Hektar)</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Luas Rizab Melayu</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Bil. Lot</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Bil. Pemilik</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Caj Bayaran Lewat</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Jumlah Pampasan</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Terima Permohonan</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Deraf Kerta MMK Hantar</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Kelulusan PBN</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Warta</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Borang E</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Siasatan</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Borang G</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Borang H</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Bayaran Pampasan</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Borang I</th>
<th style="border:1px solid black; font-size:65%; background-color:#CCCCCC;" valign="top" align="center" >Borang K</th>
-->
</tr>
</thead >


#foreach($lamp in $listLampiranA)
#set($colorRow = "white")
#if($lamp.TYPE=="1")
	#set($colorRow = "#CCCCCC")
#end
<tr >
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="left" ></td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="left" >$lamp.NAMA_KEMENTERIAN</td>

<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="left" >$lamp.TUJUAN</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="left" >
#if($lamp.TYPE=="1")
	$lamp.NO_FAIL
#else
	#if($lamp.NO_SUBJAKET != "0" && $lamp.NO_SUBJAKET != "")
	SJ $lamp.NO_SUBJAKET<br />
    #end
    #if($lamp.NO_LOTPT != "")
    $lamp.NO_LOTPT<br />
    #end
    #if($lamp.NO_HAKMILIK != "" && $lamp.NO_HAKMILIK != "TDK")
    $lamp.NO_HAKMILIK
    #end
    
#end
</td>
<!--
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="left" >
#if($lamp.TYPE=="1")
	$lamp.NAMA_DAERAH
#else
	$lamp.NAMA_MUKIM
#end
</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.LUAS_SELURUH</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.LUAS_SELURUH_RIZAB</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.JUM_LOT</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.JUM_PEMILIK</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.JUM_PAMPASAN_LEWAT</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.JUM_PAMPASAN</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_PERMOHONAN</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_HANTAR</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_MMK_KEPUTUSAN</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_WARTA</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_BORANGE</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_SIASATAN</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_BORANGG</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_BORANGH</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_BAYARAN</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_BORANGI</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >$lamp.TARIKH_BORANGK</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >
#if($lamp.TYPE=="1")
	#if($lamp.TEMPOH_BH_BULAN_INDIVIDU != "")
	$lamp.TEMPOH_BH_BULAN_INDIVIDU
    <br />
    #end
    $lamp.JENIS_PENGAMBILAN    
#else
    #if($lamp.TEMPOH_BH_BULAN!="")
        $lamp.TEMPOH_BH_BULAN
    #else
        $lamp.TEMPOH_SIASATAN_BULAN
    #end
#end

</td>
<td style="border:1px solid black; font-size:65%; background-color:$colorRow;" valign="top" align="center" >
#if($lamp.TYPE=="1")
	$lamp.STATUS
#else
	$lamp.CATATAN_HM
#end
</td>
-->
</tr>
#end
</table>

<script>
  $jquery(document).ready(function () {
	 //printLaporan('div_LampiranAhtml','$header');		 	  
  });
</script>

