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
<input name="current_role" type="hidden" value="$!current_role" />
<input name="idKategoriPekeliling" type="hidden" value="$!idKategoriPekeliling" />


&nbsp;
<div id="mydiv2">
<fieldset><legend><strong>Maklumat Pekeliling</strong></legend> 
 <table width="100%" border="0" cellspacing="0">
 <!--<div id="mydiv" >-->
    <tr>
      <td><span class="style1">*</span>Bil Pekeliling</td>
      <td>:</td>
      <td><label>
        <input name="txtBilPekeliling" type="text" id="txtBilPekeliling" onchange="javascript:getTahunPekeliling()" value="$bilPekeliling" size="8" maxlength="7" 
        />
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
 	    	#if ($mode == 'View')
	    		$!tjkPekeliling
	    	#else	
            
        <textarea name="txtTajukPekeliling" cols="41" id="txtTajukPekeliling" onblur="this.value=this.value.toUpperCase();" $readOnly>$tjkPekeliling</textarea>
       		<script> 
			var area1 = new FCKeditor("txtTajukPekeliling");
			area1.ToolbarSet = 'PFD';
			area1.BasePath = '/${appname}/library/fck/' ;
			area1.Height = 130;
			area1.Width = 530;
			area1.ReplaceTextarea();             	
			</script>
			
		#end
      </label></td>
    </tr>
    <tr>
      <td>Tahun</td>
      <td>:</td>
      <td><label>
        <input name="txtTahun" type="text" id="txtTahun" value="$tahun" size="3" $readOnly/>
      </label></td>
    </tr>
    <tr>
      <td>Tarikh Pekeliling</td>
      <td>:</td>
      <td><label>
<input name="txdTarikhPekeliling" type="text" id="txdTarikhPekeliling" value="$tarikhPekeliling" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly/>        
#if ($mode != 'View')
       <a href="javascript:displayDatePicker('txdTarikhPekeliling',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
       #end
      </label></td>
    </tr>
    <tr>
      <td>Tarikh Kuatkuasa</td>
      <td>:</td>
      <td><label>
            <input name="txdTarikhKuatkuasa" type="text" id="txdTarikhKuatkuasa" value="$tkhKuatkuasa" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly>
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
      <td>No. Fail</td>
      <td>:</td>
      <td><input name="txtNoFail" type="text" id="txtNoFail"  
      value="$!noFail" size="50" maxlength="100" $readOnly/></td>
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
   	<td width="29%" valign="top"><i>Tag</i> Dokumen</td>
   	<td align="center" valign="top">:</td> 
    <td valign="top">
    	<textarea name="tag_dokumen" cols="82" rows="5" onblur="this.value=this.value.toUpperCase()" $readOnly>$!tag_Dokumen</textarea>
   		<input name="id_tagdokumen" type="hidden" value="$!id_tagdokumen"/>
   	</td>
   	</tr>
   	
    <tr>
      <td valign="top">Catatan **</td>
      <td valign="top">:</td>
      <td><label>
        <textarea name="txtCatatan" cols="82" rows="5" id="txtCatatan" $readOnly onChange="javascript:this.value=ucwords(this.value)">$!catatan</textarea>
      </label></td>
    </tr>
    <tr>
      <td>Tarikh Daftar</td>
      <td>:</td>
      <td><label>$tarikhDaftar</label>
         		<input name="tarikhDaftar_" type="hidden" value="$!tarikhDaftar"/>
      
      </td>
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
        <button type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" ><font size = "1">Kemaskini</button>
        </label>
        <!--
        <label>
        <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()" />
        </label>
        -->
        <label></label>
        <label>
        <button type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"><font size = "1">Kembali</button>
        </label>
#elseif ($mode == 'afterSimpan')
 <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
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
      <!--</div>-->
 </table>
</fieldset>

</div>

 <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-11</strong></td>
  	</tr>
  	
  </table>
  
<script type="text/javascript" src="../app/pdt/pekeliling.js"></script>
  
<script>
getDisableFieldDiv('mydiv2','$mode');
</script>
  
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
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=SenaraiPekeliling";
	document.${formName}.submit();
}
/* function kembali2(){
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=SenaraiPekeliling";
	document.${formName}.submit();
	
	//doAjaxCall${formName}("");
} */
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
		//if (document.${formName}.txtTajukPekeliling.value == ""){
		//		alert('Sila masukkan " Tajuk Pekeliling " terlebih dahulu.');
		//		document.${formName}.txtTajukPekeliling.focus();
		//		return;
		//} 
		var editorInstance = FCKeditorAPI.GetInstance('txtTajukPekeliling');   
      	var tajuk_Dokumen = editorInstance.GetHTML(true);
       	var textlength = tajuk_Dokumen.length;                           
        if(textlength==0){
        	alert('Sila masukkan " Tajuk Pekeliling " terlebih dahulu.');
            oEditor.EditorDocument.body.focus();
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
		var x = create_request_string(document.${formName});
		//var x =0;
		if (document.${formName}.idPekeliling.value == "" || document.${formName}.idPekeliling.value == 0){
			//document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=simpan&"+x;
			//document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=simpan&";
			//document.${formName}.action.value = "simpan";
			document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=simpan&"+x+"&txtTajukPekeliling_="+tajuk_Dokumen; 
				
		}else{
			//document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=editData&idPekeliling="+id+"&"+x; 
			//document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=editData&idPekeliling="+id+"&"; 			
			//document.${formName}.action.value = "editData";
			document.${formName}.idPekeliling.value = id;
			document.${formName}.action = "?_portal_module=ekptg.view.pdt.PendaftaranPekeliling&action=editData&"+x+"&txtTajukPekeliling_="+tajuk_Dokumen; 
		
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

$jquery(document).ready(function () {
	
	var idKategoriPekeliling = document.${formName}.idKategoriPekeliling.value;
	
	//alert('$current_role'+' - '+idKategoriPekeliling);
	
	if(('$current_role'== '(PDT)HQPengguna')&&(idKategoriPekeliling==''|| idKategoriPekeliling=='1'||idKategoriPekeliling=='2'||idKategoriPekeliling=='3'||idKategoriPekeliling=='4'||idKategoriPekeliling=='5'))
	{
		//alert("masuk "+'$current_role');
		//alert("id pilih "+idKategoriPekeliling);
		$jquery('#mydiv').find('input, textarea, button, select, iframe, text').attr('disabled','');
		$jquery("input[type=button]").show();
		
		
	}
	else if(('$current_role'== '(PDT) Pengguna Bahagian Harta Tanah Persekutuan')&&(idKategoriPekeliling==''|| idKategoriPekeliling=='1'||idKategoriPekeliling=='2'||idKategoriPekeliling=='5'))
	{

		$jquery('#mydiv').find('input, textarea, button, select, iframe, text').attr('disabled','');
		$jquery("input[type=button]").show();	
	}
	
	else if(('$current_role'== '(PDT) Pengguna Bahagian Pengambilan Tanah'||'$current_role'== '(PDT) Pengguna Bahagian Pengurusan Perundangan Tanah'||'$current_role'== '(PDT) Pengguna Bahagian Standard Inspektorat'||'$current_role'== '(PDT) Pengguna Bahagian Penguatkuasa dan Hasil Persekutuan'||'$current_role'== '(PDT) Pengguna Bahagian Strata'||'$current_role'== '(PDT) Pengguna Bahagian Pengurusan ICT'||'$current_role'== '(PDT) Pengguna Bahagian Pembahagian Pusaka')&&(idKategoriPekeliling==''|| idKategoriPekeliling=='1'||idKategoriPekeliling=='5'))
	{
		$jquery('#mydiv').find('input, textarea, button, select, iframe, text').attr('disabled','');
		$jquery("input[type=button]").show();
		
	}
 	
	else
	{
		//alert('x');
	$jquery('#mydiv').find('input, textarea, button, select, iframe, text').attr('disabled','');
	$jquery("input[type=button]").hide();	
	} 
});

</script>
