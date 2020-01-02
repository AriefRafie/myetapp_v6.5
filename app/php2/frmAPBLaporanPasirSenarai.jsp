<style type="text/css">
<!--
.kaler_biru {color: #0000FF}
-->
</style>

#foreach ( $dataPelesen in $dataPelesen )
	#set ($lblNamaPelesen=$dataPelesen.dataPelesen)
    #set ($lblNoPelesen=$dataPelesen.no_siri_lesen)
#end

#if ($socBulan == '')
	#set ($selected = 'selected')
#else 
	#set ($selected = '')
#end
#if ($socBulan == '1')
	#set ($selected1 = 'selected')
#else 
	#set ($selected1 = '')
#end
#if ($socBulan == '2')
	#set ($selected2 = 'selected')
#else 
	#set ($selected2 = '')
#end
#if ($socBulan == '3')
	#set ($selected3 = 'selected')
#else 
	#set ($selected3 = '')
#end
#if ($socBulan == '4')
	#set ($selected4 = 'selected')
#else 
	#set ($selected4 = '')
#end
#if ($socBulan == '5')
	#set ($selected5 = 'selected')
#else 
	#set ($selected5 = '')
#end
#if ($socBulan == '6')
	#set ($selected6 = 'selected')
#else 
	#set ($selected6 = '')
#end
#if ($socBulan == '7')
	#set ($selected7 = 'selected')
#else 
	#set ($selected7 = '')
#end
#if ($socBulan == '8')
	#set ($selected8 = 'selected')
#else 
	#set ($selected8 = '')
#end
#if ($socBulan == '9')
	#set ($selected9 = 'selected')
#else 
	#set ($selected9 = '')
#end
#if ($socBulan == '10')
	#set ($selected10 = 'selected')
#else 
	#set ($selected10 = '')
#end
#if ($socBulan == '11')
	#set ($selected11 = 'selected')
#else 
	#set ($selected11 = '')
#end
#if ($socBulan == '12')
	#set ($selected12 = 'selected')
#else 
	#set ($selected12 = '')
#end
    
<fieldset>
	<legend>Carian</legend>
    	<table  width="100%" cellspacing="4" cellpadding="0" border="0">
        	<tr>
        	  <td>&nbsp;</td>
        	  <td align="right">Nama Pelesen</td>
        	  <td>:</td>
        	  <td><span class="kaler_biru">$!lblNamaPelesen</span></td>
      	  </tr>
        	<tr>
        	  <td>&nbsp;</td>
        	  <td align="right">No Lesen</td>
        	  <td>:</td>
        	  <td><span class="kaler_biru">$!lblNoPelesen</span></td>
      	  </tr>
        	<tr>
              <td width="8%">&nbsp;</td>
              <td width="22%" align="right">Bulan</td>
              <td width="1%">:</td>
              <td width="69%"><select name="socBulan" id="socBulan" style="width:auto" >
                <option $selected value="">SILA PILIH</option>
                <option $selected1 value="1">JANUARI</option>
                <option $selected2 value="2">FEBRUARI</option>
                <option $selected3 value="3">MAC</option>
                <option $selected4 value="4">APRIL</option>
                <option $selected5 value="5">MEI</option>
                <option $selected6 value="6">JUN</option>
                <option $selected7 value="7">JULAI</option>
                <option $selected8 value="8">OGOS</option>
                <option $selected9 value="9">SEPTEMBER</option>
                <option $selected10 value="10">OKTOBER</option>
                <option $selected11 value="11">NOVEMBER</option>
                <option $selected12 value="12">DISEMBER</option>
              </select></td>
          </tr>  
            <tr>
            	<td width="8%">&nbsp;</td>
           	  <td align="right">Tahun</td>
                <td>:</td>
              	<td><input type="text" name="txtTahun" id="txtTahun" value="$!txtTahun" size="10" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value);" /></td>               
            </tr>
      	  	<tr>
      	    	<td>&nbsp;</td>
          		<td></td>
                <td></td>
                <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:search_laporan()" />
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="javascript:kosongkan_skrinLaporan()" />
                <input name="cmdKembali" id="cmdKembali" value="Kembali" type="button" onclick="javascript:kembali_skrindepan()" /></td>
          	</tr>                  
        </table>
</fieldset>
<br/>
<fieldset>
	<legend>Senarai Maklumat Laporan</legend>
    
     #parse("app/utils/record_paging.jsp")
     
 	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
     	<tr>
        	<td><input name="cmdTambah" id="cmdTambah" value="Tambah" type="button" onclick="javascript:daftarLaporan()" /></td>           
        </tr>
     </table>

      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
            <td width="5%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
            <td width="25%" style="text-transform:uppercase">Bulan</td>
            <td width="25%" style="text-transform:uppercase">Tahun</td>
            <td width="25%" style="text-transform:uppercase">Kuantiti Isipadu (meter padu)</td>
            <td width="25%" style="text-transform:uppercase">Jumlah Anggaran Royalti (RM)</td>
        </tr>
  #if($list_size!=0)     
       #foreach($senarai in $PermohonanList)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
              <td class="$row" align="center">$senarai.bil</td>            
              <td class="$row"><a href="javascript:papar_laporan('$senarai.id_laporanpasir','$senarai.bulan_pengambilan')"><font color="blue">$senarai.nama_bulan</font></a></td>  
              <td class="$row">$senarai.tahun_pengambilan</td>
              <td class="$row">$!senarai.jumlah_kuantiti</td>
              <td class="$row">$!senarai.jumlah_royalti</td>
          </tr>          
      	#end   
        
   #else
          <tr>
              <td colspan="8">Tiada rekod</td>
          <tr>
   #end
      </table>
</fieldset>

<input type="hidden" name="actionLaporanPasir" id="actionLaporanPasir" value="$actionLaporanPasir"/>
<input type="hidden" name="id_jadualkedualesenAPB" id="id_jadualkedualesenAPB" value="$!id_jadualkedualesenAPB" />
<input type="hidden" name="id_laporanpasir" id="id_laporanpasir" />
<input type="hidden" name="bulan_pengambilan" id="bulan_pengambilan" />
     
<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function papar_laporan(id_laporanpasir,bulan_pengambilan) {

	document.${formName}.id_laporanpasir.value = id_laporanpasir;
	document.${formName}.bulan_pengambilan.value = bulan_pengambilan;
	document.${formName}.actionLaporanPasir.value = "papar_laporan";
	document.${formName}.submit();
}

function kosongkan_skrinLaporan() {	
	document.${formName}.socBulan.value = "";
	document.${formName}.txtTahun.value = "";
	document.${formName}.actionLaporanPasir.value = "papar_pelesen";
	document.${formName}.submit();
}

function search_laporan(){
	document.${formName}.actionLaporanPasir.value = "search_laporan";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBLaporanPasir";
	document.${formName}.submit();
}

function kembali_skrindepan(){
	document.${formName}.actionLaporanPasir.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBLaporanPasir";
	document.${formName}.submit();
}

function daftarLaporan() {
	document.${formName}.actionLaporanPasir.value = "daftarLaporan";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBLaporanPasir";
	document.${formName}.submit();
}

function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

</script>