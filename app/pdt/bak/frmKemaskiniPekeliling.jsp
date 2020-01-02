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
        <input name="txtBilPekeliling" type="text" id="txtBilPekeliling" value="$bilPekeliling" size="6" 
        $readOnly onchange="javascript:getTahunPekeliling()"/>
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
        <textarea name="txtTajukPekeliling" cols="41" id="txtTajukPekeliling" onblur="this.value=this.value.toUpperCase();" $readOnly>$tjkPekeliling</textarea>
      </label></td>
    </tr>
    <tr>
      <td>Tahun</td>
      <td>:</td>
      <td><label>
        <input name="txtTahun" type="text" id="txtTahun" value="$tahun" size="3" readonly="readonly"/>
      </label></td>
    </tr>
    <tr>
      <td>Tarikh Pekeliling</td>
      <td>:</td>
      <td><label>
        <input name="txdTarikhPekeliling" type="text" id="txdTarikhPekeliling" value="$tarikhPekeliling" size="10" $readOnly>
        #if ($mode != 'View')
       <a href="javascript:displayDatePicker('txdTarikhPekeliling',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
       #end
       </label></td>
    </tr>
    <tr>
      <td>Tarikh Kuatkuasa</td>
      <td>:</td>
      <td><label>
        <input name="txdTarikhKuatkuasa" type="text" id="txdTarikhKuatkuasa" value="$tkhKuatkuasa" size="10" $readOnly>
        #if ($mode != 'View')<a href="javascript:displayDatePicker('txdTarikhKuatkuasa',false,'dmy');">
        <img border="0" src="../img/calendar.gif"/></a>
        #end
        </label></td>
    </tr>
    <tr>
      <td>Seksyen</td>
      <td>:</td>
      <td>$selectSeksyen</td>
    </tr>
    <tr>
      <td>No Fail</td>
      <td>:</td>
      <td>$selectFail</td>
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
        <input name="txtLampiran" type="file" id="txtLampiran" size="51" $readOnly/>
      </label></td>
    </tr>
    #end
    <tr>
      <td valign="top">Catatan</td>
      <td valign="top">:</td>
      <td><label>
        <textarea name="txtCatatan" cols="41" id="txtCatatan" $readOnly onChange="javascript:this.value=ucwords(this.value)">$catatan</textarea>
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
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
        </label>
        <label>
        <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()" />
        </label>
        <label></label>
        <label>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
        </label>
#else
<label>
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
</label>
<label>
<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
</label>
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
function batal(){
	
	if (document.${formName}.mode.value == "Update"){
		document.${formName}.action.value = "papar";
	}
	else if (document.${formName}.mode.value == "New"){
		document.${formName}.action.value = "SenaraiPekeliling";
	}
	
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action.value = "SenaraiPekeliling";
	document.${formName}.action = "?_portal_module=${moduleId}";
	//document.${formName}.action.value = "SenaraiPekeliling";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function tambah(){
	
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
	document.${formName}.action = "?_portal_module=${moduleId}";
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
	
	document.${formName}.enctype = "multipart/form-data";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function kemaskini(){
	document.${formName}.action.value = "kemaskini";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function getTahunPekeliling(){
	document.${formName}.txtTahun.value = document.${formName}.txtBilPekeliling.value.substring(document.${formName}.txtBilPekeliling.value.lastIndexOf('/')+1,document.${formName}.txtBilPekeliling.value.length);
}
</script>
