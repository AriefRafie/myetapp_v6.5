<!--UploadBaru.jsp-->
<!--CL-02-017-->
#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
#if($page == '0')
<br/>



#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="Fail berjaya dimuat naik.";
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
<center>
<table>
	<tr>
		<td>
			<div id="fileupload_progress"></div>
			<div id="progressBar" style="display: none;">
			<div id="progressBarBoxContent"></div>
		</td>
	
	</tr>
	<tr>
		<td>
			#if ($completed)
				<B>Fail berjaya dimuat naik. $!totalRecord Rekod Berjaya Disimpan</B>
			#end
		</td>
		
	</tr>

</table>
</center>
<form method="post" enctype="multipart/form-data" name="f1">
<table width="100%">
	<tr>
		<td>
			<!--<fieldset style="width:80%"> -->
			<fieldset >
				<legend><strong>MUAT NAIK FAIL</strong></legend>
					<table width="100%" cellpadding="0" border="0"> 
					<!-- 
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
			             -->
			            <tr>
			              <td width="1%"><font color="red">*</font></td>
			              <td width="30%"valign="top">Nama Dokumen</td>
			              <td width="1%" valign="top">:</td>
			              <td width="68%"><input type="text" name="nama_dokumen" value="" onkeyup="this.value=this.value.toUpperCase();" id="nama_dokumen" size="40" /></td>
			            </tr>
			           <tr>
			              <td><font color="red">*</font></td>
			              <td valign="top">Tahun</td>
			              <td valign="top">:</td>
			              <td><input type="text" name="tahun_cukai" value="" onkeyup="validatePoskod(this,this.value);" id="tahun_cukai" size="10px" maxlength="4" /></td>
			            </tr>
			            <tr>
			              <td>&nbsp;</td>
			              <td valign="top">Keterangan </td>
			              <td valign="top">:</td>
			              <td><textarea name="keterangan" id="keterangan" onkeyup="this.value=this.value.toUpperCase();" cols="40%" rows="5"></textarea></td>
			            </tr>
            
			            <tr>
			            	<td><font color="red">*</font></td>
			            	<td>Dokumen Sokongan</td>
			            	<td>:</td>
			                <td>
			                
			                <!-- <input class="stylobutton" id="fileupload11" name="fileupload11" type=file size=40 /> -->
			                
			                <!---->
			                <input type = "file" name = "browse" style = "display:none">
							<input name = "fileupload11">
							<input type = "button" class="stylobutton100" size="40" onclick = "document.f1.browse.click();document.f1.fileupload11.value = document.f1.browse.value;" value="Sila Pilih Fail">
			                
			                </td><br/>
			  			</tr>
			  			<tr>
			        		<td colspan="4">
			        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
			        		</td>
			           	</tr>
			  			 <tr>
			              	<!-- <td colspan="4" align="center"><input class="stylobutton100" type="button" value="Simpan" onclick="Upload()" />
			              -->
			              ##<input class="stylobutton" type="button" value="Kemasukan Manual" onclick="TambahanManual()" />
			              ##<input class="stylobutton" type="button" value="Senarai Hakmilik" onclick="SenaraiFailUpload()" /></td>
			            </tr>
					</table>
			</fieldset>
		</td>
	</tr>
	
	<tr>
		<td align="center"><input class="stylobutton100" type="button" value="Simpan" onclick="Upload()" />
	</tr>

</table>



<input type="hidden" name="Xcommand">

</form>
<!-- page frmCukaiBorangManual -->
#elseif($page == '1')
<form method="post" name="f1">

<fieldset>
<legend>KEMASUKAN MAKLUMAT MANUAL</legend>
<table width="100%" border="0">
  <tr>
    <td width="50%"><fieldset><legend>MAKLUMAT TANAH</legend>
    
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
    <td width="50%"><fieldset><legend>BAYARAN</legend>
    
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

</form>
#end

<script>
function Upload(){

	var ext = document.f1.fileupload11.value;
	var nama_dokumen = document.f1.nama_dokumen.value;
	var keterangan = document.f1.keterangan.value;
	var fileupload11 = document.f1.fileupload11.value;
	var lblNegeri = "";
	var lblDaerah = "";
	var tahunCukai = document.f1.tahun_cukai.value;

	if(nama_dokumen ==""){
		alert("Sila Masukkan Nama Dokumen");
		return;
		
	}else if(tahunCukai == ""){
		alert("Sila Masukkan Tahun");
		return;
		
	}else if(ext ==""){
		alert("Sila Pilih Dokumen Untuk Dimuat Naik");
		document.f1.fileupload11.focus();
		return;
		
	}


	document.f1.enctype="multipart/form-data";
	if ( !window.confirm("Adakah Anda Pasti?")) 
		return;
	document.f1.action = "?command=uploadFile&extension="+ext+"&nama_dokumen="+nama_dokumen+"&fileupload11="+fileupload11+"&keterangan="+keterangan+"&lblNegeri="+lblNegeri+"&lblDaerah="+lblDaerah+"&tahun_cukai="+document.f1.tahun_cukai.value;
	document.f1.submit();
	document.f1.innerHTML ='<table width="100%" bgcolor="#FF1C2E"><tr><td><img src="/${session.getAttribute("_portal_appname")}/img/indicator.gif"><b>Sila tunggu...</b></td></tr></table>';
	
	
}

function doChangeDaerah(){
	var lblNegeri = document.f1.lblNegeri.value;
	var lblDaerah = document.f1.lblDaerah.value;
	document.f1.action = "?command=doChangeDaerah&lblNegeri="+lblNegeri+"&lblDaerah="+lblDaerah;	
	document.f1.submit();

}

function TambahanManual(){
	//document.f1.action = "?_portal_module=ekptg.view.htp.Upload&command=TambahanManual";	
	document.f1.action = "?command=TambahanManual";	
	document.f1.submit();
}

function doChangeDaerahManual(){
	document.f1.action = "?command=doChangeDaerahManual";	
	document.f1.submit();
}

function MainPage(){
	document.f1.action = "?command=doChangeDaerahManual";	
	document.f1.submit();
	//	doAjaxCall${formName}("doChangeDaerah");
}

	function SimpanManual(){
	
		if ( document.f1.manualNegeri.value == "" ) { 
	  		alert('Sila pilih negeri terlebih dahulu.');
	  		document.f1.manualNegeri.focus(); return; 
		}
		if ( document.f1.manualDaerah.value == "" ) { 
	  		alert('Sila pilih daerah terlebih dahulu.');
	  		document.f1.manualDaerah.focus(); return; 
		}
		if ( document.f1.manualMukim.value == "" ) { 
	  		alert('Sila pilih mukim terlebih dahulu.');
	  		document.f1.manualMukim.focus(); return; 
		}
	
		if ( document.f1.txtNoHakmilik.value == "" ) { 
	  		alert('Sila isikan no hakmilik terlebih dahulu.');
	  		document.f1.txtNoHakmilik.focus(); return; 
		}
		if ( document.f1.txtNoLot.value == "" ) { 
	  		alert('Sila isikan no lot terlebih dahulu.');
	  		document.f1.txtNoLot.focus(); return; 
		}
	
		if ( document.f1.txtKegunaanTanah.value == "" ) { 
	  		alert('Sila isikan kegunaan tanah terlebih dahulu.');
	  		document.f1.txtKegunaanTanah.focus(); return; 
		}
		
		doAjaxCallf1("SimpanManual");
	}

	function SenaraiFailUpload(){
		//document.f1.action = "?_portal_module=ekptg.view.htp.Upload&command=SenaraiFailUpload";	
	
		document.f1.action = "?command=SenaraiFailUpload";	
		document.f1.submit();
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
		var tahunan = document.f1.txtTahunan.value * 1;
		var CukaiLain = document.f1.txtCukaiLain.value * 1;
		var Tungakan = document.f1.txtTungakan.value * 1;
		var Denda = document.f1.txtDenda.value * 1;
		var Lebihan = document.f1.txtLebihan.value * 1;
	
		var jumBayaran = tahunan + CukaiLain + Tungakan + Denda + Lebihan;
	
		document.f1.txtJumBayaran.value = jumBayaran.toFixed(2);
	}

</script>