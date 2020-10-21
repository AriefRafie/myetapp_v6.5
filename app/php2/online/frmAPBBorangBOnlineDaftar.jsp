<style type="text/css">
<!--
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
<p>
	<input type="hidden" name="actionOnline" id="actionOnline" value="$actionOnline"/>
	<input type="hidden" name="idJadualKeduaLesen" id="idJadualKeduaLesen" value="$idJadualKeduaLesen" />
	<input type="hidden" name="id_laporanpasir" id="id_laporanpasir" value="$id_laporanpasir" />
	<input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
</p>

<!--  #if ($returnChecking == "true")
    <div class="warning">
    Rekod Bulan Dan Tahun yang dimasukkan telah wujud.
    </div> 
#end
-->
 #parse("app/php2/online/frmAPBBorangAOnlineHeader.jsp")


#if ($clearForm=="yes")
    #set ($txtJumKuantiti = "")
    #set ($txtJumRoyalti = "")
    #set ($txdTarikhPengeluaran ="")
    #set ($txtTahun = "")
    #set ($socBulan = "")
#end

#if ($flag=="semak")
    #foreach ( $data in $dataLaporan )
        #set ($txtJumRoyalti=$data.jumlah_royalti)
        #set ($txdTarikhPengeluaran=$data.tarikh_pengeluaran)
        #set ($txtTahun=$data.tahun_pengambilan)
        #set ($txtKontraktor=$data.kontraktor)
        #set ($txtPembeli=$data.pembeli)
        #set ($txtJumKuantiti=$data.jumlah_kuantiti)
    #end   
#end

<fieldset>

<table width="100%">
  <tr>
    <td colspan="2" align="left" width="50%">
    <fieldset>
    	<legend>Daftar Pengeluaran Pasir Laut</legend>
    <table width="100%">
  <tr>
    <td align="left" width="39%">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Kuantiti (meter padu)
      #else
      <font color="red">*</font>&nbsp;Kuantiti (meter padu)
      #end 
    
    </td>
    <td width="2%">:</td>
    <td width="59%">
    
    #if ($button=="view")
    <input type="text" name="txtJumKuantiti" id="txtJumKuantiti" value="$!txtJumKuantiti" size="5" class="disabled" readonly />
    #else
    <input type="text" name="txtJumKuantiti" id="txtJumKuantiti" value="$!txtJumKuantiti" size="5" onblur="getAnggaranRoyalti()" />
    #end
    
    <!-- <input type="hidden" name="jumKuantitiHidden" id="jumKuantitiHidden" value="$!txtJumKuantiti" /> -->
    #if ($button=="view")
    x RM <select name="socRoyalti" id="socRoyalti" style="width:50px;" class="disabled" readonly><option value="3">3.00</option><option value="1">1.00</option><option value="0.7" selected="selected">0.70</option></select> 
    #else
    x RM <select name="socRoyalti" id="socRoyalti" onchange="getAnggaranRoyalti()" style="width:50px;" class=""><option value="3">3.00</option><option value="1">1.00</option><option value="0.7" selected="selected">0.70</option></select> 
    #end
    
     <input type="hidden" name="txtKadarRoyalti" id="txtKadarRoyalti" value="$!txtKadarRoyalti" /> </td>
  </tr>
 
  <tr>
  
    <td align="left">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Anggaran Royalti (RM)
      #else
      <font color="red">*</font>&nbsp;Anggaran Royalti (RM)
      #end   
    
    </td>
    <td>:</td>
    <td>
    
    #if ($button=="view")
    <input type="text" name="txtJumRoyalti" id="txtJumRoyalti" value="$!txtJumRoyalti" size="10" class="disabled" readonly/>
    #else
    <input type="text" name="txtJumRoyalti" id="txtJumRoyalti" value="$!txtJumRoyalti" size="10" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value);" maxlength="12"  />
    #end  
    
    <!-- <input type="hidden" name="jumRoyaltiHidden" id="jumRoyaltiHidden" value="$!txtJumRoyalti" /> -->
    </td>
  </tr>
  
 <!--<tr>
	<td align="left" width="50%">
	    
	#if ($button=="view")
	&nbsp;&nbsp;&nbsp;Tarikh Pengeluaran
	#else
	<font color="red">*</font>&nbsp;Tarikh Operasi
	#end       
	
	</td>
	<td width="1%">:</td>
	<td width="49%">
	   <input type="text" name="txdTarikhPengeluaran" id="txdTarikhPengeluaran" value="$txdTarikhPengeluaran" onblur="check_date(this)" size="9"/>
         <a href="javascript:displayDatePicker('txdTarikhPengeluaran',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
    </td>
  </tr>-->
</table>
    </fieldset>    
    </td>    
    <td width="50%">
    <fieldset>    
    <table width="100%">
  <tr>
    <td align="left" width="50%">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Bulan
      #else
      <font color="red">*</font>&nbsp;Bulan
      #end       

    </td>
    <td width="1%">:</td>
    <td width="49%">$selectBulan</td>
  </tr>
  <tr>
    <td align="left">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Tahun Pengambilan
      #else
      <font color="red">*</font>&nbsp;Tahun Pengambilan
      #end          
    
    </td>
    <td>:</td>
    <td>
    
    #if ($button=="view")
    <input type="text" name="txtTahun" id="txtTahun" value="$!txtTahun" size="10" class="disabled" readonly/>
    #else 
    <input type="text" name="txtTahun" id="txtTahun" value="$!txtTahun" size="10" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value);" maxlength="4" />
    #end 
    
    </td>
  </tr>
  
   <!-- <tr>
    <td align="left">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Kontraktor
      #else
      <font color="red">*</font>&nbsp;Kontraktor
      #end          
    
    </td>
    <td>:</td>
    <td>
    
    #if ($mode=="disabled")
    <input type="text" name="txtKontraktor" id="txtKontraktor" value="$!txtKontraktor" size="35" class="disabled" readonly/>
    #else
    <input type="text" name="txtKontraktor" id="txtKontraktor" value="$!txtKontraktor" size="35" />
    #end 
    
    </td>
  </tr> -->
  
  <!-- <tr>
    <td align="left">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Pembeli
      #else
      <font color="red">*</font>&nbsp;Pembeli
      #end          
    
    </td>
    <td>:</td>
    <td>
    
    #if ($mode=="disabled")
    <input type="text" name="txtPembeli" id="txtPembeli" value="$!txtPembeli" size="35" class="disabled" readonly/>
    #else
    <input type="text" name="txtPembeli" id="txtPembeli" value="$!txtPembeli" size="35" />
    #end 
    
    </td>
  </tr> -->
</table>
    </fieldset>        
    </td>
  </tr>
  
  <tr>
    <td colspan="3" align="center">
    
    #if ($button=="add")
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanLaporan()" />    
   	  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal/Kembali" onclick="batalBaru()" />      
    #end
    
    #if ($button=="edit")
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanEditLaporan()" />    
   	  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal/Kembali" onclick="batalEdit('$id_laporanpasir')" />
    #end
    
    #if ($button=="view")
    	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniLaporan('$id_laporanpasir')"/>
    	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusLaporan('$id_laporanpasir')"/> 
        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" /> 
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali_pelesen(id_jadualkedualesenAPB)" />    
    #end        
  
    </td>
  </tr>
</table>
</fieldset>

<br/>

#if ($button!="add")
<fieldset>
	<legend>Senarai Laporan Pengeluaran Pasir Laut</legend>
	<!-- #parse("app/utils/record_paging.jsp") -->
	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
		<tr>
        	<td><input name="cmdTambah" id="cmdTambah" value="Tambah" type="button" onclick="javascript:uploadBaruDokumen('$id_laporanpasir')" /></td>           
     	</tr>
	</table>
	<input type="hidden" name="id_dokumen" id="id_dokumen"  />
	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
		<tr class="table_header">
        	<td scope="row" width="0.5%" align="center">Bil</td>
            <td width="15%" style="text-transform:uppercase">Nama Lampiran</td>
            #if($list_size!=0)     
           	#foreach($senarai in $SenaraiDokumen)
            	#set( $i = $velocityCount )
                	#if ( ($i % 2) != 1 )
                         #set( $row = "row2" )
                    #else
                         #set( $row = "row1" )
                    #end
                <tr valign="top">
                  <td class="$row" align="center">$senarai.bil</td>            
                  <td class="$row">
                  <a href="javascript:paparMaklumatDokumen('$senarai.id_dokumen')"><font color="blue">$senarai.nama_dokumen</font></a></td>  
              	</tr>          
            #end
            #else
              <tr>
                  <td colspan="8">Tiada rekod</td>
              <tr>
            #end  
    	</tr>
	</table>
</fieldset>
#end
<!-- #if ($id_laporanpasir != "")

    <br/>
    <fieldset>
        <legend>Senarai Pengeluaran Pasir Laut</legend>
        
         #parse("app/utils/record_paging.jsp")
         
         <table width="100%"  cellpadding="1" cellspacing="2" border="0">
            <tr>
                <td><input name="cmdTambah" id="cmdTambah" value="Tambah" type="button" onclick="javascript:daftarPasirLaut('$!id_laporanpasir')" /></td>           
            </tr>
         </table>
        
          <table width="100%"  cellpadding="1" cellspacing="2" border="0">
            <tr class="table_header">
                <td width="8%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
                <td width="15%" style="text-transform:uppercase">Tarikh Hantar</td>
                <td width="34%" style="text-transform:uppercase">Nama Kapal/Barge Pengangkut</td>
                <td width="28%" style="text-transform:uppercase">Lokasi Pasir Dibekalkan</td>
                <td width="15%" style="text-transform:uppercase">No Kastam</td>
            </tr>
      #if($list_size!=0)     
           #foreach($senarai in $PermohonanPasir)
                         #set( $i = $velocityCount )
                    #if ( ($i % 2) != 1 )
                         #set( $row = "row2" )
                    #else
                         #set( $row = "row1" )
                    #end
                
              <tr valign="top">
                  <td class="$row" align="center">$senarai.bil</td>            
                  <td class="$row"><a href="javascript:paparPasir('$senarai.id_borangk2k3')"><font color="blue">$senarai.tarikh_hantar</font></a></td>  
                  <td class="$row">$senarai.nama_barge</td>
                  <td class="$row">$senarai.lokasi_dibekalkan</td>
                  <td class="$row" align="left">$senarai.no_kastam</td>
              </tr>          
            #end   
            
       #else
              <tr>
                  <td colspan="8">Tiada rekod</td>
              <tr>
       #end
          </table>
          
    </fieldset>

#end -->


<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Dokumen</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakAPBLaporanPengeluaranPasirLaut('$id_jadualkedualesenAPB','$bulan_pengambilan','$id_laporanpasir')"><font color="blue"> Laporan Pengeluaran Pasir Laut </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>


<input type="hidden" name="id_jadualkedualesenAPB" id="id_jadualkedualesenAPB" value="$!id_jadualkedualesenAPB" />
<input type="hidden" name="id_laporanpasir" id="id_laporanpasir" value="$!id_laporanpasir" />
<input type="hidden" name="bulan_pengambilan" id="bulan_pengambilan" value="$!bulan_pengambilan" />
<input type="hidden" name="id_borangk2k3" id="id_borangk2k3" />
<input type="hidden" name="jumKuantitiHidden" id="jumKuantitiHidden" value="$!jumKuantitiHidden" />
<input type="hidden" name="jumRoyaltiHidden" id="jumRoyaltiHidden" value="$!jumRoyaltiHidden" />

<script>

function cetakAPBLaporanPengeluaranPasirLaut(id_jadualkedualesenAPB,bulan_pengambilan,id_laporanpasir) {

	var url = "../servlet/ekptg.report.php2.APBLaporanPengeluaranPasirLaut?id_jadualkedualesenapb="+id_jadualkedualesenAPB+"&bulan_pengambilan="+bulan_pengambilan+"&id_laporanpasir="+id_laporanpasir;
	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function paparPasir(id_borangk2k3) {
	document.${formName}.id_borangk2k3.value = id_borangk2k3;
	document.${formName}.actionOnline.value = "paparPasir";
	document.${formName}.submit();
}

function daftarPasirLaut(id_laporanpasir) {
	document.${formName}.id_laporanpasir.value = id_laporanpasir;
	document.${formName}.actionOnline.value = "daftarPasirLaut";
	document.${formName}.submit();
}

function kemaskiniLaporan(id_laporanpasir) {
	document.${formName}.actionOnline.value = "kemaskiniLaporan";
	//document.${formName}.mode.value = "view";
	document.${formName}.id_laporanpasir.value = id_laporanpasir;
	document.${formName}.submit();
}
function kembali_pelesen(id_jadualkedualesenAPB) {
	document.${formName}.id_jadualkedualesenAPB.value = id_jadualkedualesenAPB;
	document.${formName}.actionOnline.value = "papar_pelesen";
	document.${formName}.submit();
}

function batalBaru() {
	document.${formName}.actionOnline.value = "papar_pelesen";
	document.${formName}.submit();
}

function batalEdit(id_laporanpasir) {
	//if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.actionOnline.value = "papar_pelesen";
	document.${formName}.submit();
}

function simpanLaporan() {

	if(document.${formName}.txtJumKuantiti.value == ""){
		alert("Sila masukkan \"Jumlah Kuantiti\" terlebih dahulu.");
  		document.${formName}.txtJumKuantiti.focus(); 
		return;		
	}	
	if(document.${formName}.txtJumRoyalti.value == ""){
		alert("Sila masukkan \"Jumlah Royalti\" terlebih dahulu.");
  		document.${formName}.txtJumRoyalti.focus(); 
		return;		
	}
	/*if(document.${formName}.txdTarikhPengeluaran.value == ""){
		alert("Sila masukkan Tarikh Pengeluaran.");
  		document.${formName}.txdTarikhPengeluaran.focus(); 
		return;		
	}*/
	if(document.${formName}.socBulan.value == ""){
		alert("Sila masukkan \"Bulan\" terlebih dahulu.");
  		document.${formName}.socBulan.focus(); 
		return;		
	}
	if(document.${formName}.txtTahun.value == ""){
		alert("Sila masukkan \"Tahun Pengambilan\" terlebih dahulu.");
  		document.${formName}.txtTahun.focus(); 
		return;		
	}
	
	/* if(document.${formName}.txtKontraktor.value == ""){
		alert("Sila masukkan \"Nama Kontraktor\" terlebih dahulu.");
  		document.${formName}.txtKontraktor.focus(); 
		return;		
	} */
	/* if(document.${formName}.txtPembeli.value == ""){
		alert("Sila masukkan \"Nama Pembeli\" terlebih dahulu.");
  		document.${formName}.txtPembeli.focus(); 
		return;		
	}	 */

	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.actionOnline.value = "simpanLaporan";
		document.${formName}.submit();
	}	
				
}
function simpanEditLaporan() {

	if(document.${formName}.txtJumKuantiti.value == ""){
		alert("Sila masukkan \"Jumlah Kuantiti\" terlebih dahulu.");
  		document.${formName}.txtJumKuantiti.focus(); 
		return;		
	}	
	if(document.${formName}.txtJumRoyalti.value == ""){
		alert("Sila masukkan \"Jumlah Royalti\" terlebih dahulu.");
  		document.${formName}.txtJumRoyalti.focus(); 
		return;		
	}	
	if(document.${formName}.socBulan.value == ""){
		alert("Sila masukkan \"Bulan\" terlebih dahulu.");
  		document.${formName}.socBulan.focus(); 
		return;		
	}	
	if(document.${formName}.txtTahun.value == ""){
		alert("Sila masukkan \"Tahun Pengambilan\" terlebih dahulu.");
  		document.${formName}.txtTahun.focus(); 
		return;		
	}
	/* if(document.${formName}.txtKontraktor.value == ""){
		alert("Sila masukkan \"Nama Kontraktor\" terlebih dahulu.");
  		document.${formName}.txtKontraktor.focus(); 
		return;		
	} */
	/* if(document.${formName}.txtPembeli.value == ""){
		alert("Sila masukkan \"Nama Pembeli\" terlebih dahulu.");
  		document.${formName}.txtPembeli.focus(); 
		return;		
	} */	

	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.actionOnline.value = "simpanEditLaporan";
		document.${formName}.submit();
	}	
				
}

function hapusLaporan(id_laporanpasir){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_laporanpasir = document.${formName}.id_laporanpasir.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView&command=hapusLaporan&id_laporanpasir="+id_laporanpasir;	
	document.${formName}.submit();
	}	
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

<!-- PENGIRAAN ROYALTI 06072017 -->
<script>
function getAnggaranRoyalti(){

	var KT  = document.getElementById("txtJumKuantiti").value;	
	//var KR  = document.getElementById("txtKadarRoyalti").value;
	var KR  = document.getElementById("socRoyalti").value;
	//alert(KR);
	var AnggaranRoyalti = "";
	//var fee = "0.70"
	
	AnggaranRoyalti = KT * KR;
	
	document.getElementById("txtJumRoyalti").value = AnggaranRoyalti.toFixed(2) ;
	
}
</script>

<!-- FUNCTION UPLOAD FILE PASIR LAUT -16102020 -->
<script>
function uploadBaruDokumen(id_laporanpasir) {
	document.${formName}.id_laporanpasir.value = id_laporanpasir;
	document.${formName}.actionOnline.value = "uploadBaruDokumen";
	document.${formName}.submit();
}

function paparMaklumatDokumen(id_dokumen) {
	alert(id_dokumen);
	document.${formName}.id_dokumen.value = id_dokumen;
	document.${formName}.actionOnline.value = "paparDokumen";
	document.${formName}.submit();
}
function calculateRoyalti()
{
	//papar royalti dalam dropdown
	var kuantiti  = document.getElementById("txtJumKuantiti").value;	
	var anggaranRoyalti  = document.getElementById("txtJumRoyalti").value;
	var royalti = "";
	royalti = anggaranRoyalti/kuantiti;
	document.getElementById("socRoyalti").value=royalti;
}
calculateRoyalti();
</script>