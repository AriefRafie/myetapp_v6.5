<style type="text/css">
<!--
.style1 {
	color: #0000FF;
	font-weight: bold;
}
.style3 {color: #00FF00}
.style5 {color: #00FF00; font-weight: bold; }
.style6 {
	color: #FF0000;
	font-weight: bold;
}
.style8 {color: #00FFFF; font-weight: bold; }
-->
</style>

<table style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%">

<tr class="table_header">
<td height="41" colspan="10" valign="top">
<strong>MAKLUMAT KOD UPI  SETAKAT TARIKH $tarikhPrint</strong></td>
</tr>
<tr >
<td colspan="10"></td>
</tr>
<br />
<br />
<tr>
<td valign="top">
INFO : </td>
<td width="93%">
  <p><strong>UPI</strong> adalah merujuk kepada Kod Pencaman Lot Tanah ( Unique Parcel Identifier ).<br />
    Bertujuan untuk mewujudkan kod persempadanan tanah yang selaras dengan struktur kod yang unik. <br />
    Rujukan lanjut berkaitan  Kod dan Nama Sempadan Pentadbiran Tanah (UPI) ini boleh dicapai di <strong>Portal Malaysia Centre for Geospatial Data Insfrastructure ( MacGDI ) </strong><strong>www.mygeoportal.gov.my</strong> <br />
      <strong>Unique Parcel Identifier (UPI) </strong>merupakan satu kaedah pengecaman yang unik bagi mengenalpasti lokasi lot (petak) tanah yang mengandungi satu siri nombor untuk negeri, daerah, mukim, seksyen dan nombor lot. <br />
    Contoh : <br />
    Struktur Kod UPI bagi Tanah Milik ( FT Semenanjung ) - 16 Aksara  [<span class="style1">08</span>][<span class="style8">12</span>][<span class="style3">03</span>][<span class="style6">000</span>][<strong>0001234</strong>] =  [ <span class="style1">Negeri</span> ][ <span class="style8">Daerah</span> ][ <span class="style5">Mukim/Bandar/Pekan</span> ][ <span class="style6">Seksyen</span> ][ <strong>No.Lot</strong> ] = [ <span class="style1">Perak</span> ][ <span class="style8">Muallim</span> ][ <span class="style3"><strong>Mukim Slim</strong></span> ][ <span class="style6">Tiada Seksyen</span> ][ <strong>Lot 1234</strong> ] <br />
    <br />
  
  </td>
</tr>

<tr>
<td width="93%" colspan="14"><strong>Statistik Ringkas : </strong>
<br />
<br />
<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
<tr class="table_header" >
<td>Bil.</td>
<td>Negeri</td>
<td>Jumlah Daerah</td>
<td>Jumlah Mukim</td>
<td>Jumlah Seksyen</td>
</tr>
#if ($statNegeri.size() > 0)
#foreach ($statN in $statNegeri)
<tr>
<td>$statN.BIL</td>
<td>$statN.NEGERI</td>
<td align="center">$statN.JUMLAH_DAERAH</td>
<td align="center">$statN.JUMLAH_MUKIM</td>
<td align="center"></td>
</tr>
#end
#end
</table>
</td>
</tr>

<br />
<br />

<tr>
<td colspan="10"><strong> Senarai Kod UPI : </strong></td>
</tr>
<tr>
<td colspan="10">
<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
	<tr class="table_header" >
		   <td width="8%"   align="center" valign="top">Kod UPI</td>
	      <td width="20%"   align="left" valign="top">Negeri</td>
          
         <td width="8%"   align="center" valign="top">Kod UPI</td>
	      <td width="20%"   align="left" valign="top">Daerah</td>
          
          <td width="8%"   align="center" valign="top">Kod UPI</td>
	      <td width="30%"   align="left" valign="top">Mukim</td>
	</tr>
    
	#if($printKodUPI.size()>0)
	#foreach($senaraiNegeri in $printKodUPI)
	
    #if($senaraiNegeri.LAYER == "1") 
    <tr>
    <td align="center" border="0"><b>($senaraiNegeri.KOD_UPI)</b></td>
    <td colspan="5" border="0"><b>$senaraiNegeri.LAYER_1</b></td>
    </tr>
	#end
    
    #if($senaraiNegeri.LAYER == "2")
    <tr>
    <td align="right"   valign="top" ></td>
    <td align="right"   valign="top" ></td>
    <td align="center"   valign="top" border="0"><b>($senaraiNegeri.KOD_UPI)</b></td>
    <td align="left"   valign="top" colspan="3" border="0">$senaraiNegeri.LAYER_2</td>
    </tr>
	#end
    
    #if($senaraiNegeri.LAYER == "3")
    <tr>
    <td align="right"   valign="top" ></td>
    <td align="left"   valign="top" ></td>
     <td align="right"   valign="top" ></td>
    <td align="right"   valign="top" ></td>
    <td align="center"  valign="top" border="0"><b>($senaraiNegeri.KOD_UPI)</b></td>
    <td align="left"   valign="top" border="0">$senaraiNegeri.LAYER_3</td>
    </tr>
	#end
    
    
    
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
</div>

<script>
$jquery(document).ready(function () {
		printHideDiv('SenaraiForPrint');
		
		/*var divPrint =  document.getElementById('cmdCetakPejabat');
		divPrint.style.display = "none";*/
		
		var SenaraiForPrint =  document.getElementById('SenaraiForPrint');
		SenaraiForPrint.style.display = "none";
		
	});
</script>