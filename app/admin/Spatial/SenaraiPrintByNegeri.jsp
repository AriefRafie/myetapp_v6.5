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
<td colspan="10">
MAKLUMAT KOD UPI SETAKAT TARIKH </td>
</tr>
<br />
<tr>
<td valign="top">
INFO : </td>
<td width="93%">
  <p><strong>UPI</strong> adalah merujuk kepada Kod Pencaman Lot Tanah ( Unique Parcel Identifier ). <br /> 
    Bertujuan untuk mewujudkan kod persempadanan tanah yang selaras dengan struktur kod yang unik. <br />
    Rujukan lanjut berkaitan  Kod dan Nama Sempadan Pentadbiran Tanah (UPI) ini boleh dicapai di <strong>Portal Malaysia Centre for Geospatial Data Insfrastructure ( MacGDI ) </strong><strong>www.mygeoportal.gov.my</strong> <br />
  <strong>Unique Parcel Identifier (UPI) </strong>merupakan satu kaedah pengecaman yang unik bagi mengenalpasti lokasi lot (petak) tanah yang mengandungi satu siri nombor untuk negeri, daerah, mukim, seksyen dan nombor lot. <br />
    Contoh : <br /> 
    Struktur Kod UPI bagi Tanah Milik ( FT Semenanjung ) - 16 Aksara  [<span class="style1">08</span>][<span class="style8">12</span>][<span class="style3">03</span>][<span class="style6">000</span>][<strong>0001234</strong>] =  [ <span class="style1">Negeri</span> ][ <span class="style8">Daerah</span> ][ <span class="style5">Mukim/Bandar/Pekan</span> ][ <span class="style6">Seksyen</span> ][ <strong>No.Lot</strong> ] = [ <span class="style1">Perak</span> ][ <span class="style8">Muallim</span> ][ <span class="style3"><strong>Mukim Slim</strong></span> ][ <span class="style6">Tiada Seksyen</span> ][ <strong>Lot 1234</strong> ] 
    <br />
    <br />
    
</td>
</tr>

<tr class="">
<td colspan="10">
<strong>Senarai Kod UPI bagi Negeri</strong><br />
</td>
</tr>

<tr>
<td colspan="10">
<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
	<tr class="table_header" >
		   <td width="9%"   align="center" valign="top">Kod</td>
	      <td width="83%"   align="left" valign="top">Negeri</td>
         <!-- <td width="8%"   align="left" valign="top">Tindakan</td>-->
	</tr>
	#if($PrintlistUPI.size()>0)
	#foreach($senaraiNegeri in $PrintlistUPI)
	<tr id="div_rowPejabatUrusan$PejUrus.ID_PEJABAT">
	   <td   align="center" valign="top" >($senaraiNegeri.KOD_NEGERI)</td>
	   <td  align="left" valign="top">$senaraiNegeri.NAMA_NEGERI <br /></td>
	 <input type="hidden" id="ID_NEGERI" name="ID_NEGERI" value="$senaraiNegeri.ID_NEGERI"   />
    </tr>
    
    
    cetakReport :::::::::: $cetakReport
    <script>
    $jquery(document).ready(function () {
		doDivAjaxCall$formname('div_viewDaerahByNegeriPrint$senaraiNegeri.ID_NEGERI','showSenaraiDaerah','cetakReport=$cetakReport&FlagCetak=Y&ID_NEGERI='+$jquery('#ID_NEGERI').val());			 	  
	});
    </script>
    
     <tr id="div_viewDaerahByNegeriPrint$senaraiNegeri.ID_NEGERI">
     <td align="left" valign="top" colspan="5">
     </td>
     </tr>

	#end
	#else 
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>

	#if($PrintlistUPI.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPejabat');
	//alert(' masuk sini ');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	#end
	<!--<div id="SenaraiForPrint">	
	</div>
-->
</td>
</tr>
</table>
</div>

<script>
$jquery(document).ready(function () {
		//printHideDiv('SenaraiForPrint');			 	  
	});
</script>

<!--<tr>
<td>

<fieldset>
<legend>Carian Kod UPI
</legend>
<table width="429" border="0" align="center" cellpadding="2" cellspacing="2">
<tr>
<td >Negeri </td><td >:</td>
<td valign="top">
<select id="ID_NEGERI"  name="ID_NEGERI" onChange = "doDivAjaxCall$formname('div_DAERAH_ID','showListDaerah','ID_NEGERI='+$jquery('#ID_NEGERI').val());">   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJNEGERI )		
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN
</option>					
#end
</select>
</td>
</tr>
<tr>
<td>Daerah</td><td >:</td>
<td valign="top" id="div_DAERAH_ID">
<select id="ID_DAERAH"  name="ID_DAERAH"
onChange = "doDivAjaxCall$formname('div_MUKIM_ID','showListMukim','ID_DAERAH='+$jquery('#ID_DAERAH').val());">	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJDAERAH )		
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN
</option>					
#end
</select>
</td>
</tr>
<tr>
<td>Mukim</td><td >:</td>
<td valign="top" id="div_MUKIM_ID">
<select id="ID_MUKIM"  name="ID_MUKIM">	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJMUKIM )		
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN
</option>					
#end
</select>
</td>
</tr>
<tr>
<td></td>
<td></td>
<td><input type="button" id="cmdCariNegeri" name="cmdCariNegeri" value="Cetak" onclick="doDivAjaxCall$formname('div_SenaraiNegeri','showSenaraiNegeri','FlagCari=Y');" /></td>
</tr>
</table>
</fieldset>

</td>
</tr>
<br />-->