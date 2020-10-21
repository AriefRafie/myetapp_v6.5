<style type="text/css">
<!--
.kaler_biru {color: #0000FF}
-->
</style>

<p>
<input type="hidden" name="actionOnline" id="actionOnline" value="$actionOnline"/>
<input type="hidden" name="idJadualKeduaLesen" id="idJadualKeduaLesen" value="$idJadualKeduaLesen" />
<input type="hidden" name="id_laporanpasir" id="id_laporanpasir" />
<input type="hidden" name="bulan_pengambilan" id="bulan_pengambilan" />
<input type="hidden" name="mode" id="mode" value="$mode"/>
</p>     

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
    
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      
      <table width="100%" align="center" border="0">
        <tr>
          <td></td>
          <td width="30%" height="24" scope="row" align="right">Nama Pemegang Lesen </td>
          <td> :</td>
          <td width="70%">$namaPemohon</td>
        </tr>
        <tr>
          <td></td>
          <td width="30%" height="24" scope="row" align="right"> No. Lesen  </td>
          <td>:</td>
          <td width="70%">$noLesen</td>
        </tr>
      <!--  <tr>
              <td width="8%">&nbsp;</td>
              <td width="22%" align="right">Bulan</td>
              <td width="1%">:</td>
              <td width="69%">$selectBulanList</td>
          </tr>  
        <tr>
          <td></td>
          <td height="24" scope="row" align="right">Tahun </td>
          <td>:</td>
          <td><input name="txtTahun2" type="text" id="txtTahun2" value="$tahun" size="10" onkeyup="validateNumber(this,this.value);"></td>
        </tr>
          
        <tr>
          <td></td>
          <td scope="row"></td>
          <td>&nbsp;</td>
                 </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
          <td></td>
          <td></td>
        </tr>
         --> 
      </table>
      </fieldset></td>
  </tr>
<br/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
	<legend>Senarai Maklumat Laporan</legend>
    
     #parse("app/utils/record_paging.jsp")
     
 	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
     	<tr>
        	<td><input name="cmdTambah" id="cmdTambah" value="Tambah" type="button" onclick="javascript:daftarLaporan('$!idJadualKeduaLesen')" /></td>           
        </tr>
     </table>

      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
            <td width="5%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
            <!--<td width="25%" style="text-transform:uppercase">Tarikh Operasi</td>-->
            <td width="25%" style="text-transform:uppercase">Bulan</td>
            <td width="25%" style="text-transform:uppercase">Tahun</td>
            <td width="25%" style="text-transform:uppercase">Kuantiti Isipadu (meter padu)</td>
            <td width="25%" style="text-transform:uppercase">Jumlah Anggaran Royalti (RM)</td>
        </tr>
  #if($list_size!=0)     
  
       #foreach($senarai in $SenaraiFailBorangB)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
              <td class="$row" align="center">$senarai.bil</td>
              <!--<td class="$row"><a href="javascript:papar_laporan('$senarai.id_laporanpasir','$senarai.bulan_pengambilan')"><font color="blue">$senarai.nama_bulan</font></a></td>-->             
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
</tr>
</td>
</table>

<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function papar_laporan(id_laporanpasir,bulan_pengambilan) {

	document.${formName}.actionOnline.value = "papar_laporan";
	document.${formName}.mode.value = "view";
	document.${formName}.id_laporanpasir.value = id_laporanpasir;
	document.${formName}.bulan_pengambilan.value = bulan_pengambilan;	
	document.${formName}.submit();
}

function kosongkan_skrinLaporan() {	
	document.${formName}.socBulan.value = "";
	document.${formName}.txtTahun.value = "";
	document.${formName}.actionOnline.value = "papar_pelesen";
	document.${formName}.submit();
}

function search_laporan(){
	document.${formName}.actionOnline.value = "search_laporan";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView";
	document.${formName}.submit();
}

function kembali_skrindepan(){
	document.${formName}.actionOnline.value = "";
	//document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView";
	document.${formName}.submit();
}

function daftarLaporan(idJadualKeduaLesen) {
	document.${formName}.actionOnline.value = "daftarLaporan";
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