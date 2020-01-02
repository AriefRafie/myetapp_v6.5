<style type="text/css">
<!--
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
.style3 {color: #FF0000; font-style: italic; }
-->
</style>
#parse("app/php2/frmAPBLaporanPasirHeader.jsp")


#if ($clearForm=="yes")
    #set ($txdTarikhHantar = "")
    #set ($txtNamaBarge = "")
    #set ($txtLokasi = "")
    #set ($txtKuantiti = "")
    #set ($txtAnggaranRoyalti = "")
    #set ($txtNoKastam = "")
#end

#if ($flag=="semak")
    #foreach ( $data in $dataPasir )
        #set ($txdTarikhHantar=$data.tarikh_hantar)
        #set ($txtNamaBarge=$data.nama_barge)
        #set ($txtLokasi=$data.lokasi_dibekalkan)
        #set ($txtKuantiti=$data.kuantiti)
        #set ($txtAnggaranRoyalti=$data.anggaran_royalti)
        #set ($txtNoKastam=$data.no_kastam)
        #set ($txdHariHantar=$data.hari_hantar)
    #end    
#end

#foreach ( $dataPelesen in $dataPelesen )
	#set ($txtKadarRoyalti=$dataPelesen.kadar_royalti)
#end

<fieldset>

<table width="100%">
  <tr>
    <td colspan="2" align="left">
    <fieldset>
    	<legend>Maklumat Pengeluaran Pasir Laut</legend>
    <table width="100%">
  <tr>
    <td align="left" width="50%">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Tarikh Hantar
      #else
      <font color="red">*</font>&nbsp;Tarikh Hantar
      #end    </td>
    <td width="1%">:</td>
    <td width="49%">
    
    #if ($mode=="disabled")
    <input type="text" name="txdTarikhHantar" id="txdTarikhHantar" value="$!txdTarikhHantar" size="10" class="disabled" readonly>
    #else
    <input type="text" name="txdTarikhHantar" id="txdTarikhHantar" value="$!txdTarikhHantar" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);"> <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
    #end    
    
    
    </td>
  </tr>
  <tr>
    <td align="left" width="50%">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Hari Hantar
      #else
      <font color="red">*</font>&nbsp;Hari Hantar
      #end    </td>
    <td width="1%">:</td>
    <td width="49%">
    
    #if ($mode=="disabled")
    <input type="text" name="txdHariHantar" id="txdHariHantar" value="$!txdHariHantar" size="10" class="disabled" readonly>
    #else
    <input type="text" name="txdHariHantar" id="txdHariHantar" size="10" value="$!txdHariHantar" > 
    #end    
    
    
    </td>
  </tr>
  <tr>
    <td align="left" width="50%">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Masa Hantar
      #else
      &nbsp;&nbsp;Masa Hantar
      #end    </td>
    <td width="1%">:</td>
    <td width="49%">$selectJamDari$selectMinitDari
    </td>
  </tr>
    <tr>
    <td align="left" width="50%" colspan="3">&nbsp;&nbsp;&nbsp;<span class="style3">Sila pastikan masa adalah dalam format 24 jam (HHMM).</span>
    </td>
  </tr>
  <tr>
    <td align="left">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Nama Kapal/Barge pengangkutan
      #else
      <font color="red">*</font>&nbsp;Nama Kapal/Barge pengangkutan
      #end    </td>
    <td>:</td>
    <td>
    
    #if ($mode=="disabled")
    <input type="text" name="txtNamaBarge" id="txtNamaBarge" size="35" value="$!txtNamaBarge" class="disabled" readonly />
    #else
    <input type="text" name="txtNamaBarge" id="txtNamaBarge" size="35" value="$!txtNamaBarge" >    
    #end    
    
    </td>
  </tr>
  <tr>
    <td align="left">&nbsp;&nbsp;&nbsp;Lokasi Pasir Dibekalkan</td>
    <td>:</td>
    <td>
    
    #if ($mode=="disabled")
    <input type="text" name="txtLokasi" id="txtLokasi" value="$!txtLokasi" size="35" class="disabled" readonly />
    #else
    <input type="text" name="txtLokasi" id="txtLokasi" value="$!txtLokasi" size="35" />
    #end

    </td>
  </tr>
</table>
    </fieldset>    
    </td>    
    <td width="40%">
    <fieldset>    
    <table width="100%">
  <tr>
    <td align="left" width="55%">
    
      #if ($button=="view")
      &nbsp;&nbsp;&nbsp;Kuantiti (meter padu)
      #else
      <font color="red">*</font>&nbsp;Kuantiti (meter padu)
      #end      </td>
    <td width="3%">:</td>
    <td width="42%">
    
    #if ($mode=="disabled")
    <input type="text" name="txtKuantiti" id="txtKuantiti" value="$!txtKuantiti" size="5" class="disabled" readonly />
    #else
    <input type="text" name="txtKuantiti" id="txtKuantiti" value="$!txtKuantiti" size="5" onblur="getAnggaranRoyalti()" />
    #end

     x RM $!Util.formatDecimal($!txtKadarRoyalti) 
     <input type="hidden" name="txtKadarRoyalti" id="txtKadarRoyalti" value="$!txtKadarRoyalti" />
     </td>
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
    
    #if ($mode=="disabled")
    <input type="text" name="txtAnggaranRoyalti" id="txtAnggaranRoyalti" value="$!txtAnggaranRoyalti" size="10" class="disabled" readonly/>
    #else
    <input type="text" name="txtAnggaranRoyalti" id="txtAnggaranRoyalti" value="$!txtAnggaranRoyalti" size="10" onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value);" maxlength="12"  />
    #end   
    
     </td>
  </tr>
  <tr>
    <td align="left">&nbsp;&nbsp;&nbsp;No Kastam</td>
    <td>:</td>
    <td>
    
      #if ($mode=="disabled")
      <input type="text" name="txtNoKastam" id="txtNoKastam" value="$!txtNoKastam" size="10" class="disabled" readonly />
      #else
      <input type="text" name="txtNoKastam" id="txtNoKastam" value="$!txtNoKastam" size="10" />
      #end  
  
    
    </td>
  </tr>  
  
</table>
    </fieldset>        
    </td>
  </tr>
  
  <tr>
    <td colspan="3" align="center">
    
    #if ($button=="add")
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanPasir()" />    
   	  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal_daftarPasir()" />      
    #end
    
    #if ($button=="edit")
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanEditBorang()" />    
   	  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal_editPasir('$id_borangk2k3')" />
    #end
    
    #if ($button=="view")
    	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniBorang('$id_borangk2k3')"/>
    	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusBorang('$id_borangk2k3')"/>  
    #end        
    
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali_paparLaporan('$!id_laporanpasir')" />    
      
    </td>  
  </tr>
</table>
</fieldset>

<input type="hidden" name="id_jadualkedualesenAPB" id="id_jadualkedualesenAPB" value="$!id_jadualkedualesenAPB" />
<input type="hidden" name="id_laporanpasir" id="id_laporanpasir" value="$!id_laporanpasir" />
<input type="hidden" name="id_borangk2k3" id="id_borangk2k3" value="$!id_borangk2k3" />
<input type="hidden" name="bulan_pengambilan" id="bulan_pengambilan" value="$!bulan_pengambilan" />
<input type="hidden" name="jumKuantitiHidden" id="jumKuantitiHidden" value="$!jumKuantitiHidden" />
<input type="hidden" name="jumRoyaltiHidden" id="jumRoyaltiHidden" value="$!jumRoyaltiHidden" />
<input type="hidden" name="actionLaporanPasir" id="actionLaporanPasir" value="$actionLaporanPasir"/>


<script>

function getAnggaranRoyalti(){

	var KT  = document.getElementById("txtKuantiti").value;	
	var KR  = document.getElementById("txtKadarRoyalti").value;
	//alert(KR);
	var AnggaranRoyalti = "";
	//var fee = "0.70"
	
	AnggaranRoyalti = KT * KR;
	
	document.getElementById("txtAnggaranRoyalti").value = AnggaranRoyalti.toFixed(2) ;
	
}

function simpanEditBorang() {

	if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;		
	}
	if(document.${formName}.txdHariHantar.value == ""){
		alert("Sila masukkan \"Hari Hantar\" terlebih dahulu.");
  		document.${formName}.txdHariHantar.focus(); 
		return;		
	}	
	if(document.${formName}.txtNamaBarge.value == ""){
		alert("Sila masukkan \"Nama Kapal/Barge Pengangkutan\" terlebih dahulu.");
  		document.${formName}.txtNamaBarge.focus(); 
		return;		
	}	
	if(document.${formName}.txtKuantiti.value == ""){
		alert("Sila masukkan \"Kuantiti\" terlebih dahulu.");
  		document.${formName}.txtKuantiti.focus(); 
		return;		
	}	
	if(document.${formName}.txtAnggaranRoyalti.value == ""){
		alert("Sila masukkan \"Anggaran Royalti\" terlebih dahulu.");
  		document.${formName}.txtAnggaranRoyalti.focus(); 
		return;		
	}	

	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.actionLaporanPasir.value = "simpanEditBorang";
		document.${formName}.submit();
	}	
				
}

function batal_editPasir() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.actionLaporanPasir.value = "paparPasir";
	document.${formName}.submit();
}

function kemaskiniBorang(id_borangk2k3) {
	document.${formName}.id_borangk2k3.value = id_borangk2k3;
	document.${formName}.actionLaporanPasir.value = "kemaskiniBorang";
	document.${formName}.submit();
}

function hapusBorang(id_borangk2k3){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	var id_borangk2k3 = document.${formName}.id_borangk2k3.value ;		
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBLaporanPasir&command=hapusBorang&id_borangk2k3="+id_borangk2k3;	
	document.${formName}.submit();
	}	
}

function simpanPasir() {

	if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;		
	}	
	if(document.${formName}.txdHariHantar.value == ""){
		alert("Sila masukkan \"Hari Hantar\" terlebih dahulu.");
  		document.${formName}.txdHariHantar.focus(); 
		return;		
	}
	if(document.${formName}.txtNamaBarge.value == ""){
		alert("Sila masukkan \"Nama Kapal/Barge Pengangkutan\" terlebih dahulu.");
  		document.${formName}.txtNamaBarge.focus(); 
		return;		
	}	
	if(document.${formName}.txtKuantiti.value == ""){
		alert("Sila masukkan \"Kuantiti\" terlebih dahulu.");
  		document.${formName}.txtKuantiti.focus(); 
		return;		
	}	
	if(document.${formName}.txtAnggaranRoyalti.value == ""){
		alert("Sila masukkan \"Anggaran Royalti\" terlebih dahulu.");
  		document.${formName}.txtAnggaranRoyalti.focus(); 
		return;		
	}	

	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.actionLaporanPasir.value = "simpanPasir";
		document.${formName}.submit();
	}	
				
}

function batal_daftarPasir() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.actionLaporanPasir.value = "daftarPasirLaut";
	document.${formName}.submit();
}

function kembali_paparLaporan(id_laporanpasir) {
	document.${formName}.id_laporanpasir.value = id_laporanpasir;
	document.${formName}.actionLaporanPasir.value = "papar_laporan";
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


