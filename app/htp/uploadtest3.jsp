#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
#if($page == '0')
<br/>

<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="File successfully uploaded.";
</script>
#end

#if ($maxsize)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="Fail melebihi 1MB.";
</script>
#end

#if ($wrongEXT)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="Pastikan format fail ialah .xls";
</script>
#end

<form method="post" enctype="multipart/form-data" name="f1">

<center>
	<fieldset style="width:80%">
	<legend><strong>Muat Turun Fail</strong></legend>
	
    	 <table width="100%" cellpadding="0" border="0">  
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        	  <td width="9%">&nbsp;</td>
              <td width="19%"><font color="red">*</font>Negeri </td>
              <td width="1%">:</td>
              <td width="71%">$lblNegeri</td>
          </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td valign="top"><font color="red">*</font>Daerah </td>
            	<td valign="top">:&nbsp;</td>
                <td>$lblDaerah</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td valign="top"><font color="red">*</font>Nama Dokumen</td>
              <td valign="top">:</td>
              <td><input type="text" name="nama_dokumen" value="" onkeyup="this.value=this.value.toUpperCase();" id="nama_dokumen" size="43px" /></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td valign="top">Keterangan </td>
              <td valign="top">:</td>
              <td><textarea name="keterangan" id="keterangan" onkeyup="this.value=this.value.toUpperCase();" cols="40%" rows="5"></textarea></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>Dokumen Sokongan</td>
            	<td>:</td>
                <td><input class="stylobutton" id="fileupload11" name="fileupload11" type=file size=40 /></td><br/>
  			</tr>
            <tr>
              <td colspan="4"><input class="stylobutton" type="button" value="Simpan" onclick="Upload()" />
              <input class="stylobutton" type="button" value="Kemasukan Manual" onclick="TambahanManual()" />
              <input class="stylobutton" type="button" value="Senarai Hakmilik" onclick="SenaraiFailUpload()" /></td>
            </tr>
     		<tr align="center">
        		<td>&nbsp;</td>
        	</tr>           
        </table>
        <br/>
    </fieldset>


	<table width="100%"  cellpadding="0" border="0">

    </table> 
	
<center>

<input type="hidden" name="command1">

</form>
<!-- page frmCukaiBorangManual -->
#elseif($page == '1')

<fieldset>
<legend>Borang Maklumat Manual</legend>
<table width="100%" border="0">
  <tr>
    <td width="50%"><fieldset><legend>Maklumat Tanah</legend>
    
        <table width="100%" border="0">
          <tr>
            <td width="1%"><font color="red">*</font></td>
            <td width="28%">Negeri</td>
            <td width="1*">:</td>
            <td width="70%">$manualNegeri</td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Daerah</td>
            <td>:</td>
            <td>$manualDaerah</td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Mukim/Pekan/Bandar</td>
            <td>:</td>
            <td>$manualMukim</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Jenis HM</td>
            <td>:</td>
            <td>$JenisHakmilik</td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Jenis/No HM</td>
            <td>:</td>
            <td><input name="txtNoHakmilik" type="text" id="txtNoHakmilik" size="46" onkeyup="this.value=this.value.toUpperCase();">
              </td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Jenis PT/Lot</td>
            <td>:</td>
            <td>$jenisLot
              </td>
          </tr>
          <tr>
            <td height="30">&nbsp;</td>
            <td>NO PT/Lot</td>
            <td>:</td>
            <td><input name="txtNoLot" type="text" id="txtNoLot" size="47" onkeyup="this.value=this.value.toUpperCase();" /></td>
          </tr>
          <tr>
            <td height="30"><font color="red">*</font></td>
            <td>Kegunaan Tanah</td>
            <td>:</td>
            <td><textarea name="txtKegunaanTanah" id="txtKegunaanTanah" cols="45" rows="5" onkeyup="this.value=this.value.toUpperCase();"></textarea></td>
          </tr>
        </table>
    </fieldset></td>
    <td width="50%"><fieldset><legend>Bayaran</legend>
    
        <table width="609" height="259" border="0">
          <tr>
            <td width="8"><font color="red">*</font></td>
            <td width="143">Tahunan</td>
            <td width="34">: RM</td>
            <td width="406">
                <input name="txtTahunan" type="text" id="txtTahunan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()">
              </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Cukai Lain</td>
            <td>: RM</td>
            <td>
                <input name="txtCukaiLain" type="text" id="txtCukaiLain" size="11"  onblur="validateCurrency(this,this.value,'');calculate()">
              </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Tunggakan</td>
            <td>: RM</td>
            <td>
                <input name="txtTungakan" type="text" id="txtTungakan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()">
             </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Denda Lewat</td>
            <td>: RM</td>
            <td>
                <input name="txtDenda" type="text" id="txtDenda" size="11"  onblur="validateCurrency(this,this.value,'');calculate()">
              </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Lebihan(Parit/Tali air)</td>
            <td>: RM</td>
            <td>
                <input name="txtLebihan" type="text" id="txtLebihan" size="11"  onblur="validateCurrency(this,this.value,'');calculate()">
              </td>
          </tr>
          <tr>
            <td><font color="red">*</font></td>
            <td>Cukai Kena Bayar</td>
            <td>: RM</td>
            <td>
                <input name="txtJumBayaran" type="text" id="txtJumBayaran" size="11"  onblur="validateCurrency(this,this.value,'');calculate()"$inputstyleread>
             </td>
          </tr>
          <tr>
            <td height="30">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="30">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
    </fieldset></td>
  </tr>
</table>
<input class="stylobutton" name="Simpan" type="button" value="Simpan" onclick="SimpanManual()"/>
 <input class="stylobutton" name="Batal" type="button" value="Batal" onclick="MainPage()"/>
</fieldset>

#end
<script>
function Upload(){

	var ext = document.${formName}.fileupload11.value;
	var nama_dokumen = document.${formName}.nama_dokumen.value;
	var keterangan = document.${formName}.keterangan.value;
	var fileupload11 = document.${formName}.fileupload11.value;
	var lblNegeri = document.${formName}.lblNegeri.value;
	var lblDaerah = document.${formName}.lblDaerah.value;

	if ( document.${formName}.lblNegeri.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.lblNegeri.focus(); return; 
	}
	document.${formName}.enctype="multipart/form-data";
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?command=uploadFile&extension="+ext+"&nama_dokumen="+nama_dokumen+"&fileupload11="+fileupload11+"&keterangan="+keterangan+"&lblNegeri="+lblNegeri+"&lblDaerah="+lblDaerah;
	document.${formName}.submit();
	
}

function SimpanManual(){

	if ( document.${formName}.manualNegeri.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.manualNegeri.focus(); return; 
	}
	if ( document.${formName}.manualDaerah.value == "" ) { 
  		alert('Sila pilih daerah terlebih dahulu.');
  		document.${formName}.manualDaerah.focus(); return; 
	}
	if ( document.${formName}.manualMukim.value == "" ) { 
  		alert('Sila pilih mukim terlebih dahulu.');
  		document.${formName}.manualMukim.focus(); return; 
	}

	if ( document.${formName}.txtNoHakmilik.value == "" ) { 
  		alert('Sila isikan no hakmilik terlebih dahulu.');
  		document.${formName}.txtNoHakmilik.focus(); return; 
	}
	if ( document.${formName}.txtNoLot.value == "" ) { 
  		alert('Sila isikan no lot terlebih dahulu.');
  		document.${formName}.txtNoLot.focus(); return; 
	}

	if ( document.${formName}.txtKegunaanTanah.value == "" ) { 
  		alert('Sila isikan kegunaan tanah terlebih dahulu.');
  		document.${formName}.txtKegunaanTanah.focus(); return; 
	}
	
	doAjaxCall${formName}("SimpanManual");
}

function TambahanManual(){

	doAjaxCall${formName}("TambahanManual");
}

/*function MainPage_Lama(){
	doAjaxCall${formName}("doChangeDaerah");
}*/

function MainPage(){
	doAjaxCall${formName}("?_portal_module=ekptg.view.htp.UploadBaru&doChangeDaerah");
}

function cariSenaraiTemp(){
	
	if ( document.${formName}.lblNegeri2.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.lblNegeri2.focus(); return; 
	}
	if ( document.${formName}.lblDaerah2.value == "" ) { 
  		alert('Sila pilih daerah terlebih dahulu.');
  		document.${formName}.lblDaerah2.focus(); return; 
	}
	if ( document.${formName}.lblMukim2.value == "" ) { 
  		alert('Sila pilih mukim terlebih dahulu.');
  		document.${formName}.lblMukim2.focus(); return; 
	}

	doAjaxCall${formName}("carianFail");
}
function doChangeDaerah(){
	doAjaxCall${formName}("doChangeDaerah");
}

function doChangeDaerah1(){
	doAjaxCall${formName}("doChangeDaerah1");
}

function doChangeDaerahManual(){
	doAjaxCall${formName}("doChangeDaerahManual");
}

function SenaraiFailUpload(){

	doAjaxCall${formName}("SenaraiFailUpload");
}
//function extra---------------------------------------------------------------------------

function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890.,";
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
function calculate(){
	var tahunan = document.${formname}.txtTahunan.value * 1;
	var CukaiLain = document.${formname}.txtCukaiLain.value * 1;
	var Tungakan = document.${formname}.txtTungakan.value * 1;
	var Denda = document.${formname}.txtDenda.value * 1;
	var Lebihan = document.${formname}.txtLebihan.value * 1;

	var jumBayaran = tahunan + CukaiLain + Tungakan + Denda + Lebihan;

	document.${formname}.txtJumBayaran.value = jumBayaran.toFixed(2);
}
</script>