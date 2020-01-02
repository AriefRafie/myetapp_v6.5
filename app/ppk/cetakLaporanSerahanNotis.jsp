<!-- Start Styles. Move the 'style' tags and everything between them to between the 'head' tags -->
<style type="text/css">
.myTable { background-color:#eee;border-collapse:collapse; }
.myTable th { background-color:#fff;color:#000;width:50%; }
.myTable td, .myTable th { padding:5px;border:1px solid #000; }
</style>
<!-- End Styles -->
<p align = "center"><b>LAPORAN SERAHAN PERINTAH</b> <br/>
$noFail</p>
<!--

<table width="100%" border="0" cellspacing="2" cellpadding="2">
<tr align="center"><td>
<b>$noFail </b>
</tr></td>
<tr align="center"><td>
<b>Cara Serahan : </b>$cara_serahan 
</tr></td>
<tr align="center"><td>
<b>Tarikh Serahan : </b>$tarikh_serahan 
</tr></td>
<tr align="center"><td>
<b>Nama Penerima : </b>$nama_penerima </br>
</tr></td>
<tr align="center"><td>
<b>No. K/P Penerima : </b>$no_kp_penerima </br>
</tr></td>
<tr align="center"><td>
<b>Catatan : </b>$catatan
</tr></td>
</table>
  -->


<table class="myTable" width="100%" border="1" cellspacing="0" cellpadding="0">
<tr>
	<th>
	Nama Penyerah
	</th>
	<th>
	Nama Penerima
	</th>
	<th>
	No. KP Penerima
	</th>
	<th>
	Tarikh Serahan
	</th>
	<th>
	Cara Serahan
	</th>
	<th>
	
	Catatan
	
</th>
</tr>

<tr>
	<td>
	$penyerah
	</td>
	<td>
	$nama_penerima
	</td>
	<td>
	$no_kp_penerima
	</td>
	<td>
	$tarikh_serahan
	</td>
	<td>
	$cara_serahan
	</td>
	<td>
	$catatan
	</td>
</tr>

</table>
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