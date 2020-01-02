<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<input name="action" type="hidden" value="$!action" />
<input name="mode" type="hidden" value="$!mode" />
<input name="idPekeliling" type="hidden" value="$!idPekeliling">
<input name="idStatuspekeliling" type="hidden" value="$!idStatuspekeliling" />
<input name="idSeksyen" type="hidden" value="$!idSeksyen" />

&nbsp;
<fieldset><legend><strong>Maklumat Pekeliling</strong></legend> 
 <table width="100%" border="0" cellspacing="0">
    <tr>
      <td><span class="style1">*</span>Bil Pekeliling</td>
      <td>:</td>
      <td><label>
        <input name="txtBilPekeliling" type="text" id="txtBilPekeliling" onchange="javascript:getTahunPekeliling()" value="$bilPekeliling" size="8" maxlength="7" 
        $readOnly readonly="readonly" class="disabled"/>
      </label></td>
    </tr>
    <tr>
      <td width="29%"><span class="style1">*</span>Kategori Pekeliling</td>
      <td width="1%">:</td>
      <td width="70%">$selectKategoriPekeliling</td>
    </tr>
    <tr>
      <td><span class="style1">*</span>Perkara Pekeliling</td>
      <td>:</td>
      <td>$selectPerkaraPekeliling</td>
    </tr>
    <tr>
      <td valign="top"><span class="style1">*</span>Tajuk Pekeliling</td>
      <td valign="top">:</td>
      <td><label>
        <textarea name="txtTajukPekeliling" cols="41" id="txtTajukPekeliling" onblur="this.value=this.value.toUpperCase();" $readOnly readonly="readonly" class="disabled">$tjkPekeliling</textarea>
      </label></td>
    </tr>
    <tr>
      <td>Tahun</td>
      <td>:</td>
      <td><label>
        <input name="txtTahun" type="text" id="txtTahun" value="$tahun" size="3" readonly="readonly" readonly="readonly" class="disabled"/>
      </label></td>
    </tr>
    <tr>
      <td>Tarikh Pekeliling</td>
      <td>:</td>
      <td><label>
<input name="txdTarikhPekeliling" type="text" id="txdTarikhPekeliling" value="$tarikhPekeliling" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly readonly="readonly" class="disabled"/>        
</td>
    </tr>
    <tr>
      <td>Tarikh Kuatkuasa</td>
      <td>:</td>
      <td><label>
            <input name="txdTarikhKuatkuasa" type="text" id="txdTarikhKuatkuasa" value="$tkhKuatkuasa" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly readonly="readonly" class="disabled">
        </td>
    </tr>
    <tr>
      <td>Seksyen</td>
      <td>:</td>
      <td>$selectSeksyen</td>
    </tr>
    <tr>
      <td>No Fail</td>
      <td>:</td>
      <td><input name="txtNoFail" type="text" id="txtNoFail" onblur="check_date(this);" 
      value="$!noFail" size="50" maxlength="100" $readOnly readonly="readonly" class="disabled"/></td>
    </tr>
    <tr>
      <td>Status Pekeliling</td>
      <td>:</td>
      <td>$selectStatus</td>
    </tr>
    #if ($mode != 'View')
    <tr>
      <td valign="top">Lampiran</td>
      <td valign="top">:</td>
      <td><label>
        <input name="txtLampiran" type="file" id="txtLampiran" size="51" $readOnly readonly="readonly" class="disabled"/>
      </label></td>
    </tr>
    #end
    <tr>
      <td valign="top">Catatan</td>
      <td valign="top">:</td>
      <td><label>
        <textarea name="txtCatatan" cols="41" id="txtCatatan" $readOnly onChange="javascript:this.value=ucwords(this.value)" readonly="readonly" class="disabled">$catatan</textarea>
      </label></td>
    </tr>
    <tr>
      <td>Tarikh Daftar</td>
      <td>:</td>
      <td><label>$tarikhDaftar</label></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td> #if ($mode == 'View')
        <label>
        
        </label>
        <!--
        <label>
        <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()" />
        </label>
        -->
        <label></label>
        <label>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
        </label>
#elseif ($mode == 'afterSimpan')

  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
#else

#end
</label></td>
   </tr>
    <tr>
      <td colspan="3" align="center"><label>
 </table>
</fieldset>
 <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-11</strong></td>
  	</tr>
  </table>
<script>

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


function batal(){
	document.${formName}.method="POST";
	if (document.${formName}.mode.value == "Update"){
		//document.${formName}.action.value = "papar";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=papar";
	}
	else if (document.${formName}.mode.value == "New"){
		//document.${formName}.action.value = "SenaraiPekeliling";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=SenaraiPekeliling";
	}
	
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.readonly.PendaftaranPekelilingReadonly&action=SenaraiPekeliling";
	document.${formName}.submit();
	
	//doAjaxCall${formName}("");
}
function kembali2(){
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=SenaraiPekeliling";
	document.${formName}.submit();
	
	//doAjaxCall${formName}("");
}
function tambah(){
	document.${formName}.method="POST";
	document.${formName}.action.value = "tambahDataLain";
	document.${formName}.idPekeliling.value = "";
	document.${formName}.txtBilPekeliling.value = "";
	document.${formName}.socKategoriPekeliling.value = "";
	document.${formName}.socPerkaraPekeliling.value = "";
	document.${formName}.txtTajukPekeliling.value = "";
	document.${formName}.txtTahun.value = "";
	document.${formName}.txdTarikhPekeliling.value = "";
	document.${formName}.txdTarikhKuatkuasa.value = "";
	document.${formName}.socSeksyen.value = "";
	document.${formName}.socFail.value = "";
	document.${formName}.txtCatatan.value = "";
	document.${formName}.mode.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=tambahDataLain";
	document.${formName}.submit();
	
}

function simpan(){
 		if (document.${formName}.txtBilPekeliling.value == ""){
				alert('Sila masukkan " Bil Pekeliling " terlebih dahulu.');
				document.${formName}.txtBilPekeliling.focus();
				return;
		} 
		if (document.${formName}.socKategoriPekeliling.value == ""){
				alert('Sila masukkan " Kategori Pekeliling " terlebih dahulu.');
				document.${formName}.socKategoriPekeliling.focus();
				return;
		}
		if (document.${formName}.socPerkaraPekeliling.value == ""){
				alert('Sila masukkan " Perkara Pekeliling " terlebih dahulu.');
				document.${formName}.socPerkaraPekeliling.focus();
				return;
		} 
		if (document.${formName}.txtTajukPekeliling.value == ""){
				alert('Sila masukkan " Tajuk Pekeliling " terlebih dahulu.');
				document.${formName}.txtTajukPekeliling.focus();
				return;
		} 
		if ( !window.confirm("Anda Pasti?") ) return;
		/*
		if (document.${formName}.txdTarikhKuatkuasa.value < document.${formName}.txdTarikhPekeliling.value){
			alert ("Tarikh Kuatkuasa tidak boleh melebihi Tarikh Pekeliling");
			document.${formName}.txdTarikhKuatkuasa.focus();
			return;
		}
		*/
	
	var id = document.${formName}.idPekeliling.value ;
	//alert("idPekeliling:"+id);
	var x = create_request_string(document.${formName});
	if (document.${formName}.idPekeliling.value == "" || document.${formName}.idPekeliling.value == 0){
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=simpan&"+x;
	}
	else{
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=editData&idPekeliling="+id+"&"+x; 
	}
	document.${formName}.mode.value = "";
	document.${formName}.enctype = "multipart/form-data";
	document.${formName}.encoding = "multipart/form-data";

	document.${formName}.submit();
}
function kemaskini(){
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=kemaskini";
	document.${formName}.submit();
}
function getTahunPekeliling(){
	document.${formName}.txtTahun.value = document.${formName}.txtBilPekeliling.value.substring(document.${formName}.txtBilPekeliling.value.lastIndexOf('/')+1,document.${formName}.txtBilPekeliling.value.length);
}
</script>
