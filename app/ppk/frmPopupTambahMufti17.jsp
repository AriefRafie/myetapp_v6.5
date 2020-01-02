
<form name="f1" method="POST">
<p></p>
<br/>
<center>

	<fieldset style="width:80%">
	<legend><strong>Maklumat Pejabat Mufti</strong></legend>

#if ( $modePM == "add" )	
    	 <table width="100%" cellpadding="0" border="0">           
            <tr>
              <td><font color="red">Negeri</font></td>
              <td>:</td>
              <td>&nbsp;$selectNegeri</td>
            </tr>
            <tr>
              <td><font color="red">Nama Pejabat</font></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtnamapej" id="txtnamapej" maxlength="50" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" /></td>
            </tr>
            <tr>
              <td><font color="red">Alamat</font></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat1" id="txtAlamat1" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>            
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat2" id="txtAlamat2" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat3" id="txtAlamat3" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ></td>
            </tr>
            <tr>
              <td>Poskod</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtPoskod" id="txtPoskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" ></td>
            </tr>           
            <tr>
              <td><font color="red">Bandar</font></td>
              <td>&nbsp;</td>
              <td>&nbsp;$selectBandar</td>
            </tr>
            <tr>
              <td>No Telefon</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtTelefon" id="txtTelefon" onkeyup="validateNumber(this,this.value);" ></td>
            </tr>
            <tr>
              <td>No Fax</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtfax" id="txtfax" onkeyup="validateNumber(this,this.value);" /></td>
            </tr>
        </table>
    <br/> 
	<table width="100%"  cellpadding="0" border="0">
    	<tr align="center">
        	<td>
                <input type="button" value="Simpan" onClick="Simpan()">
                <input type="button" name="cmdExit" value="kembali" onclick="self.close();return false;" />
            </td>
        </tr>
    </table>
#end

#if ( $modePM == "view" )	
    	 <table width="100%" cellpadding="0" border="0">           
            <tr>
              <td><font color="red">Negeri</font></td>
              <td>:</td>
              <td>&nbsp;$selectNegeri</td>
            </tr>
            <tr>
              <td><font color="red">Nama Pejabat</font></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtnamapej" id="txtnamapej" value="PEJABAT MUFTI" maxlength="50" size="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" /></td>
            </tr>
            <tr>
              <td><font color="red">Alamat</font></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat1" id="txtAlamat1" value="NO 123 KAWASAN KOMPLEKS" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>            
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat2" id="txtAlamat2" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();"></td>
            </tr>
            <tr>
              <td></td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtAlamat3" id="txtAlamat3" maxlength="50" size="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ></td>
            </tr>
            <tr>
              <td>Poskod</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtPoskod" id="txtPoskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" ></td>
            </tr>           
            <tr>
              <td><font color="red">Bandar</font></td>
              <td>&nbsp;</td>
              <td>&nbsp;$selectBandar</td>
            </tr>
            <tr>
              <td>No Telefon</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtTelefon" id="txtTelefon" onkeyup="validateNumber(this,this.value);" ></td>
            </tr>
            <tr>
              <td>No Fax</td>
              <td>:</td>
              <td>&nbsp;<input type="text" name="txtfax" id="txtfax" onkeyup="validateNumber(this,this.value);" /></td>
            </tr>
        </table>
    <br/> 
	<table width="100%"  cellpadding="0" border="0">
    	<tr align="center">
        	<td>
                <input type="button" value="Simpan" onClick="Simpan()">
                <input type="button" name="cmdExit" value="kembali" onclick="self.close();return false;" />
            </td>
        </tr>
    </table>
#end


    
</fieldset>


<input name="idPermohonan" type="hidden" value="$idPermohonan" /> 
<input name="id_perbicaraan" type="hidden" value="$id_perbicaraan" /> 
<input name="id_perintah" type="hidden" value="$id_perintah" />
<input type="hidden" readonly="true" name="ekptg_user_id" value="${session.getAttribute("_ekptg_user_id")}" /> 
<input type="hidden" name="command2">  
<input type="hidden" name="command3">

</form>

<script>

function Simpan(){

	if(document.f1.socNegeri.value == "")
	{
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.f1.socNegeri.focus(); 
		return;
	}	
	if(document.f1.txtnamapej.value == "")
	{
		alert("Sila masukkan \"Nama Pejabat\" terlebih dahulu.");
  		document.f1.txtnamapej.focus(); 
		return;
	}	
	if((document.f1.txtAlamat1.value == "")||(document.f1.txtAlamat2.value == "")||(document.f1.txtAlamat3.value == ""))
	{
		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
  		document.f1.txtAlamat1.focus(); 
		return;
	}	
	if(document.f1.socBandar.value == "")
	{
		alert("Sila Pilih \"Bandar\" terlebih dahulu.");
  		document.f1.socBandar.focus(); 
		return;
				
	}else{		
	if ( !window.confirm("Adakah Anda Pasti?")) return;	
	window.close();
	//document.f1.action = "?command3=SimpanPejMufti&idPermohonan="+id;
	document.f1.command3.value = "SimpanPejMufti";	
	document.f1.action = ""; 
	document.f1.submit();
	window.opener.refreshTambahMufti();
	}		
}

function kembali(id_permohonan) {
	history.go(-1);
}

function doChangeidNegeri() {
	document.f1.command2.value = "doChangeidNegeri";
	document.f1.action = ""; 
	document.f1.submit();	
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
