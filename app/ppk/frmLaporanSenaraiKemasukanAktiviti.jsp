<style type="text/css">
<!--
.style1 {
	color: #000000
}
-->
</style>
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<fieldset>
  <legend>Carian</legend>
  <table width="100%" border="0">
    <tr>
      <td width="23%"><div align="right">Bulan</div></td>
      <td width="1%">:</td>
      <td><select name="socBulan" style="width: 100px;">
          
        #if($bulan == '')
		
          <option value="" selected>SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '01')
        
          <option value="">SILA PILIH</option>
          <option value="01" selected>JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '02')
        
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02" selected="selected">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '03')
        
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03" selected="selected">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '04')
        
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04" selected="selected">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '05')
        
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05" selected="selected">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '06')
        
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06" selected="selected">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '07')
        
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07" selected="selected">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '08')
        
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08" selected="selected">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '09')
        
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09" selected="selected">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '10')
        
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10" selected="selected">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '11')
         
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11" selected="selected">NOVEMBER</option>
          <option value="12">DISEMBER</option>
          
        #elseif($bulan == '12')
         
          <option value="">SILA PILIH</option>
          <option value="01">JANUARI</option>
          <option value="02">FEBUARI</option>
          <option value="03">MAC</option>
          <option value="04">APRIL</option>
          <option value="05">MEI</option>
          <option value="06">JUN</option>
          <option value="07">JULAI</option>
          <option value="08">OGOS</option>
          <option value="09">SEPTEMBER</option>
          <option value="10">OKTOBER</option>
          <option value="11">NOVEMBER</option>
          <option value="12" selected="selected">DISEMBER</option>
          
        #end
	
        </select></td>
    </tr>
    <!--
  <tr>
    <td width="23%"><div align="right">Tahun</div></td>
    <td width="1%">:</td>
    <td><select  name="tahun1" style="width: 100px;">
    	#if($tahun == '')
        
      <option value="" selected>SILA PILIH</option>
		<option value="2010">2010</option>
		<option value="2011">2011</option>
        #elseif($tahun == '2010')
        <option value="">SILA PILIH</option>
		<option value="2010">2010</option>
		<option value="2011">2011</option>
        #elseif($tahun == '2011')
        <option value="">SILA PILIH</option>
		<option value="2010">2010</option>
		<option value="2011">2011</option>
        #end
        
    </select></td>
  </tr>
  
  -->
    <tr>
      <td width="23%"><div align="right">Tahun</div></td>
      <td width="1%">&nbsp;</td>
      <td>$selectTahun</td>
    </tr>
    <tr>
      <td width="23%"><div align="right"></div></td>
      <td width="1%">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td width="23%"><div align="right"></div></td>
      <td width="1%">&nbsp;</td>
      <td><input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="cari()" />
        <input type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()"/></td>
    </tr>
  </table>
</fieldset>
<p>
<fieldset>
  <legend>Senarai Aktiviti</legend>
  <input name="cmdTambah" type="submit" value="Tambah" onclick="tambah()">
  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="cetak('$userid')">
  <table width="100%" border="0">
    <tr class="table_header">
      <td width="1%"><strong>No</strong></td>
      <td width="70%"><strong>Aktiviti</strong></td>
      <td align="center"><strong>Tarikh Mula</strong></td>
      <td align="center"><strong>Tarikh Tamat</strong></td>
    </tr>
    #foreach ( $senarai in $senaraiRekod )
    #if ($senarai.bil == '') 
    #set ($row = 'row1')
    #elseif ($senarai.bil % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
    <tr>
      <td class="$row">$senarai.bil</td>
      #if($senarai.bil != '')
      <td class="$row"><a href="javascript:papar('$senarai.id')" class="style1">$senarai.event_text</a></td>
      #else
      <td class="$row" align="left">$senarai.event_text</td>
      #end
      <td class="$row" align="center">$senarai.event_datef</td>
      <td class="$row" align="center">$senarai.event_datef_end</td>
    </tr>
    #end
  </table>
  <input name="cnt" type="hidden" value="$cnt">
  <input name="senaraiRekod" type="hidden" value="$senaraiRekod">
</fieldset>
<script>
window.onload=function()
{
populatedropdown("yeardropdown")
}
function populatedropdown(yearfield){
var today=new Date()
var yearfield=document.getElementById(yearfield)

var thisyear=today.getFullYear()
for (var y=0; y<20; y++){	
yearfield.options[y]=new Option(thisyear, thisyear)
thisyear-=1
}
yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true) 
}
function tambah() {

	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmLaporanKemasukanAktiviti&action=tambah";
	document.${formName}.submit();
	
}
function cari() {

	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmLaporanKemasukanAktiviti&action=";
	document.${formName}.submit();
	
}
function kosongkan() {

	document.${formName}.socBulan.value = "";
	document.${formName}.socTahun.value = "";
	document.${formName}.submit();
	
}
function cetak(userid) {

	if(document.${formName}.socBulan.value !='00' && document.${formName}.socTahun.value){
		var url = "../servlet/ekptg.report.ppk.LaporanAktiviti?userid="+userid+"&bulan="+document.${formName}.socBulan.value+"&tahun="+document.${formName}.socTahun.value;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();	
	
	 
	}
	else{
		
		alert("Sila pilih Bulan dan Tahun");
	}

	
    
	
	
}
function papar(id) {
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmLaporanKemasukanAktiviti&action=papar&idCalendar="+id;
	document.${formName}.submit();
	
}
</script> 