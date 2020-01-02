<!-- Start Styles. Move the 'style' tags and everything between them to between the 'head' tags -->
<style type="text/css">
.myTable { background-color:#fff;border-collapse:collapse; }
.myTable th { background-color:#CCCCCC;color:#000; }
.myTable td, .myTable th { padding:5px;border:1px solid #000; }
</style>
<!-- End Styles -->
<p align = "center"><b><font face="verdana" size="2">LAPORAN SERAHAN HARTA TAK ALIH KEPADA PIHAK BERKUASA NEGERI (PBN)</font></b></p>



 
<table class="myTable" table align="center" width="92%" border="1" cellspacing="0" cellpadding="0">
<tr>
	<th width="3%" align="left">
	<font face="verdana" size="1">BIL.</font>
	<th width="18%" align="left">
	<font face="verdana" size="1">NO. FAIL</font>
	</th>
	<th width="20%" align="left">
	<font face="verdana" size="1">NAMA SIMATI</font>
	</th>
	<th width="20%" align="left">
	<font face="verdana" size="1">NAMA PEMOHON</font>
	</th>
	<th width="20%" align="left">
	<font face="verdana" size="1">NAMA BAITULMAL</font>
	</th>
	<th width="10%" align="left">
	<font face="verdana" size="1">TARIKH MOHON</font>
	</th>
	<th width="10%" align="left">
	
	<font face="verdana" size="1">CATATAN</font>
	
</th>
</tr>
#set ($bil = 0)
#foreach($list in $SenaraiSerahan)
	  #set ($bil = $bil+1)
      #set($nama_pemohon=$list.namaPemohon)
      #set($nama_simati=$list.namaSimati)
      #set($no_fail=$list.noFail)
      #set($NAMA_BAITULMAL=$list.NAMA_BAITULMAL)
      #set($tarikh_Mohon=$list.tarikhMohon)
      
<tr>
	<td width="3%" align="center">
	$bil
	<td width="18%">
	$no_fail
	</td>
	<td width="20%">
	$nama_simati
	</td>
	<td width="20%">
	$nama_pemohon
	</td>
	<td width="20%">
	$NAMA_BAITULMAL
	</td>
	<td width="10%" align="center">
	$tarikh_Mohon
	</td>
	<td width="10%">
	
	</td>
</tr>
#end
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