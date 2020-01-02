<br/>
<form name="f1" method="POST">
<input type="hidden" name="id_fail" value="$id_fail">
<input type="text" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_status" value="$id_status">
<input type="hidden" name="id_suburusan" value="$id_suburusan">
<input type="hidden" name="command">
#foreach($data in $PermohonanList)
    #set($txtNoRujukan=$data.no_rujukan_upt)
#end    

  <p>
  
  <fieldset>
          <legend><strong>Maklumat Surat Dari Jabatan Teknikal</strong></legend>
          <table width="100%" border="0" cellspacing="2" cellpading="0">
          
          <tr>
            <td width="17%">Bil Surat</td>
            <td width="1%">:</td>
            <td width="82%"><input type="text" size="30px" name="txtBilSuratTerima" onblur="this.value=this.value.toUpperCase();" id="txtBilSuratTerima" style="text-transform:uppercase;"  /></td>
          </tr>          

          <tr>
            <td valign="top">No Ruj. Surat</td>
            <td valign="top">:</td>
            <td><input type="text" size="30px" name="txtNoRujSurat" onblur="this.value=this.value.toUpperCase();" id="txtNoRujSurat" style="text-transform:uppercase;"  /></td>
          </tr>
            
          <tr>
            <td>Tarikh Terima</td>
            <td>:</td>
            <td><input name="txdTkhTerima" size="11px" id="txdTkhTerima" type="text" />
              <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerima',false,'dmy');" onblur="check_date(this)" /></td>
          </tr>
            
          <tr>
            <td>Tarikh Surat</td>
            <td>:</td>
            <td><input name="txdTkhSurat" size="11px" id="txdTkhSurat" type="text" />
            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTkhSurat',false,'dmy');" onblur="check_date(this)" /></td>
          </tr> 
            
          <tr>
            <td>Keputusan</td>
            <td>:</td>
            <td><input type="text" size="30px" name="socKeputusan" onblur="this.value=this.value.toUpperCase();" id="socKeputusan" style="text-transform:uppercase;"  /></td>
          </tr>
          <tr>
          	<td valign="top">Ulasan</td>
          	<td valign="top">:</td>
          	<td><textarea name="txtUlasan" cols="47%" rows="3" id="txtUlasan"></textarea></td>
          </tr>  
          </table>
  </fieldset>     
	<div align="center">              
      <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_JT();" />
      <input name="cmdBatal" type="button" value="Kembali" onclick="javascript: kembali_skrin2('$id_fail','$id_permohonan');" />
  	</div>
</form>

<script>
function Simpan_JT(){	
	if(document.f1.agensi.value == ""){
		alert("Sila pilih \"Nama Jabatan\" terlebih dahulu.");
		document.f1.agensi.focus(); 
		return;
	}
	var radioSelected = false;
	for (j = 0;  j < f1.flag_terima.length;  j++){
		if (f1.flag_terima[j].checked)
		radioSelected = true;
	}
	if (!radioSelected){
		alert("Sila pilih \"Terima Surat Dari JT?\" terlebih dahulu.");
		return (false);
	}
else{	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.f1.method = "POST";
	document.f1.command.value = "Simpan_JT";
	document.f1.action = "";
	document.f1.submit();
}
}
function kembali_skrin2(id_fail,id_permohonan) {
	document.f1.id_fail.value = id_fail;
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.command.value = "kembali_skrin2";
	document.f1.action = "";
	document.f1.submit();
}
function validate(elmnt,content) {
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
function doChangeidKementerian() {
	document.f1.command.value = "doChangeidKementerian";
	document.f1.action = ""; 
	document.f1.submit();
	
}
function doChangeidNegeri() {
	document.f1.command.value = "doChangeidNegeri";
	document.f1.action = ""; 
	document.f1.submit();
	
}
function doChangeidNegeri2() {
	document.f1.command.value = "doChangeidNegeri2";
	document.f1.action = ""; 
	document.f1.submit();
		
}	
function getSuratDariJT() {
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmSenaraiFailKeputusanPermohonan?idPermohonan="+idPermohonan+"&command="+command;
	var hWnd = window.open(url,'printuser','width=600,height=600, resizable=yes,scrollbars=yes');
	document.f1.sorPenentuanBidangKuasaTeruskan[0].checked == false;
}
</script> 