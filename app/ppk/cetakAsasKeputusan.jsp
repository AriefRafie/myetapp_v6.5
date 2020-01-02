<p align="center"><font face="Arial">
<table style="height: 169px;" width="85%">
<tbody>
<tr>
<td style="text-align: center;"><font size="2"><b>MALAYSIA</b></font></td>
</tr>
<tr>
<td style="text-align: center;"><font size="2">NEGERI <b> $negeriPermohonan</b></font></td>
</tr>
<tr>
<td style="text-align: center;"><font size="2">DAERAH <b> $daerahPermohonan</b></font></td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td style="text-align: center;"><font size="2">GUAMAN PEMBAHAGIAN NO. <b>$!nofail</b></font></td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td><font size="2">Dalam hal harta pusaka <b>$namaSimati, Tarikh mati : $tarikhMati, No. Sijil Mati : $sijil_mati, </b>Permintaan daripada <b> $namaPerayu, No. KP : $ic1 - $ic2 - $ic3,</b> beralamat di <b> $alamat1
#if ($alamat1 != "")
	&#44;
#end $alamat2 
#if ($alamat2 != "")
	&#44;
#end
$alamat3 
#if ($alamat3 != "")
	&#44;
#end
$poskod $bandar	&#44;
 $negeri2.
 </b></font></td>
</tr>
<tr>
<td></td>
</tr>
<tr>
<td style="text-align: center;"><font size="2"><b><span style="text-decoration: underline;"><p>ASAS-ASAS KEPUTUSAN</p></span></b></font></td>
</tr>
<tr>
<td><br/></td>
</tr>
<tr>
<td><font size="2">$asas_keputusan</font></td>
</tr>
</tbody>
</table>
</font>
<p>&nbsp;</p>
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