<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {color: #0033FF}
-->
</style>
<input name="mode" type="hidden" value="$mode" />
<input name="idDokumen" type="hidden" value="$!idDokumen" />
&nbsp;
  <fieldset>
  <legend><strong>Maklumat Perundangan</strong></legend>
  <table width="100%" border="0" cellspacing="0">
	  <tr>
	    <td>Nama Akta</td>
	    <td>:</td>
	    <td>$selectSeksyen</td>
	  </tr>
	 <tr>
	    <td valign="top"><span class="style1">*</span>Maklumat Perundangan</td>
	    <td valign="top">:</td>
	    <td><label>
	    	#if ($mode == 'View')
	    		$!tjkDok
	    	#else	
	      <textarea name="txtTajukDokumen" cols="41" id="txtTajukDokumen" onkeyup="this.value=this.value.toUpperCase();" $readOnly>$tjkDok</textarea>
	      
	                         <script> 
						var area1 = new FCKeditor("txtTajukDokumen");
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
	    <td width="29%">Tempat Bicara</td>
	    <td width="1%">:</td>
	    <td width="70%"><label>
	      <input name="txtNoRujDokumen" type="text" id="txtNoRujDokumen" size="44" $readOnly value="$noRujDok">
	    </label></td>
	  </tr>
	  <tr>
	    <td>Tarikh Bicara</td>
	    <td>:</td>
	    <td><label>
	      <input name="txdTarikhBicara" type="text" value="$!tarikhBicara" size="10" 0" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly>
	     <a href="javascript:displayDatePicker('txdTarikhBicara',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></td>
	  </tr>
	  <tr>
	    <td valign="top">Maklumat Keputusan</td>
	    <td valign="top">:</td>
	    <td><label>
	      <textarea name="txtCatatan" cols="82" rows="5" id="txtCatatan" $readOnly onChange="javascript:this.value=ucwords(this.value)">$catatan</textarea>
	    </label></td>
	  </tr>	  
  	<tr>
	    <td>Tarikh Keputusan</td>
	    <td>:</td>
	    <td><label>
	      <input name="txdTarikhDokumen" type="text" id="txdTarikhDokumen" value="$tkhDok" size="10" 0" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly>
	     <a href="javascript:displayDatePicker('txdTarikhDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></td>
	  </tr>
  <!-- <tr>
    <td>No. Fail</td>
    <td>:</td>
    <td><input value="$!txtNamaFail" $readOnly name="txtNamaFail" type="text" id="txtNamaFail" size="43" maxlength="50" /></td>
  </tr> -->

  
 	<tr>
            <td scope="row" valign="top"><i>Tag</i> Dokumen</td>
            <td scope="row" valign="top">:</td> 
            <td scope="row" valign="top">
             	<textarea name="tag_dokumen" cols="82" rows="5" onblur="this.value=this.value.toUpperCase()" $readOnly>$!tag_Dokumen</textarea>
            	<input name="id_tagdokumen" type="hidden" value="$!id_tagdokumen"/>
            </td>
	</tr>
 
  <tr>
    <td>Tarikh Daftar</td>
    <td>:</td>
    <td><label>
      
    $tkhDaftar</label></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>#if ($mode == 'View')
      <label>
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
      </label>
      <label>
      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()" />
      </label>
      <label></label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
      </label>
      <label></label>
#else
<label>
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
</label>
<label>
<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
</label>
#end </td>
  </tr>
  <tr>
    <td colspan="3" align="center">&nbsp;</td>
    </tr>
</table>
<fieldset><legend><strong>Senarai Lampiran</strong></legend>
<p><strong>
  <label>
  #if ($mode == 'View')
 <input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Tambah" onclick="tambahLampiran('$idDokumen','tambah')">
 <!--
  <input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Tambah" onclick="tambahLampiran('tambah')">
  -->
  #end
  </label>
</strong></p>
<table width="100%">
  <tr class="table_header">
    <td width="3%"><strong>Bil.</strong></td>
    <td width="92%"><strong>Nama Fail</strong></td>
    <!--<td width="20%"><strong>Jenis Fail</strong></td>-->
    <td width="5%">&nbsp;</td>
  </tr>
  #foreach($lampiran in $SenaraiLampiran)
    #if ($lampiran.bil == '') 
  	#set ($row = 'row1')
  #elseif ($lampiran.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$lampiran.bil.</td>
    #if ($lampiran.bil != '') 
    <td class="$row"><a href="javascript:papar_Lampiran('$lampiran.id_Lampiran')" class="style2">$lampiran.nama_Fail<input name="idLampiran" type="hidden" value="$lampiran.id_Lampiran" /></a></td>
    #else
    <td class="$row">$lampiran.nama_Fail</td>
    #end
    <!--<td class="$row">$lampiran.jenis_Mime</td>-->
    <td class="$row"><label>
      <!--<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus('$lampiran.id_Lampiran')">-->
          	<a alt="Hapus Dokumen" href = "javascript:hapus('$lampiran.id_Lampiran')">
			&nbsp;&nbsp;<img border="0" src="../img/delete.gif" />
		</a> 
    </label></td>
  </tr>
#end
</table>

</fieldset>
  </fieldset>
   <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-06-15</strong></td>
  	</tr>
  </table>

<script type="text/javascript" src="../app/pdt/perundangan.js"></script>  

<script type="text/javascript"> 
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

	function simpan() {
		//if (document.Fekptg_view_pdt_PendaftaranDokumen.txtNoRujDokumen.value == ""){
				//alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
				//document.Fekptg_view_pdt_PendaftaranDokumen.txtNoRujDokumen.focus();
				//return;
		//} 
		
		//if (document.${formName}.txtTajukDokumen.value == ""){
		//		alert('Sila masukkan " Maklumat " terlebih dahulu.');
		//		document.${formName}.txtTajukDokumen.focus();
		//		return;
		//}
		
		var editorInstance = FCKeditorAPI.GetInstance('txtTajukDokumen');   
      	var tajuk_Dokumen = editorInstance.GetHTML(true);
       	var textlength = tajuk_Dokumen.length;                           
        if(textlength==0){
        	alert('Sila masukkan " Maklumat " terlebih dahulu.');
            oEditor.EditorDocument.body.focus();
            return;
     	}		
		/* if (document.Fekptg_view_pdt_PendaftaranDokumen.socJenisDokumen.value == ""){
				alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.');
				document.Fekptg_view_pdt_PendaftaranDokumen.socJenisDokumen.focus();
				return;
		} */
		
		if ( !window.confirm("Anda Pasti?") ) return;	
		if (document.${formName}.idDokumen.value == "" || document.${formName}.idDokumen.value == 0){
				//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "simpan";
			//doAjaxCall${formName}("","action=simpan&mode=");
			//document.${formName}.action.value = "simpan";
			//document.${formName}.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan&action=simpan&socSeksyen="+document.${formName}.socSeksyen.value+"&txtTajukDokumen="+escape(txtTajukDokumen)+"&txtNoRujDokumen="+escape(document.${formName}.txtNoRujDokumen.value)+"&txdTarikhBicara="+escape(document.${formName}.txdTarikhBicara.value)+"&txtCatatan="+escape(document.${formName}.txtCatatan.value)+"&txdTarikhDokumen="+escape(document.${formName}.txdTarikhDokumen.value)+"&tag_dokumen="+escape(document.${formName}.tag_dokumen.value)+"&id_tagdokumen="+escape(document.${formName}.id_tagdokumen.value)+"&";
			
			document.${formName}.command.value = "simpan";
			
		} else { 
				//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "editData"; 
			//doAjaxCall${formName}("","action=editData&mode=");
			//document.${formName}.action.value = "editData";
			
			document.${formName}.command.value = "editData";
			
		}		
			//document.Fekptg_view_pdt_PendaftaranDokumen.mode.value = "";
			//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		document.${formName}.mode.value = "";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan&txtTajukDokumen_="+escape(txtTajukDokumen);
		document.${formName}.submit();
		
	}
	
	function batal(){	
		if (document.${formName}.mode.value == "Update"){		
				//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "papar";
			//doAjaxCall${formName}("","action=papar");
			
			document.${formName}.command.value = "papar";
		
		}else if (document.${formName}.mode.value == "New" || document.${formName}.mode.value == "View"){
				//document.Fekptg_view_pdt_PendaftaranDokumen.action.value = "SenaraiDokumen";
			//doAjaxCall${formName}("","action=SenaraiDokumen");
			
			document.${formName}.command.value = "SenaraiDokumen";
	
		}		
			//document.Fekptg_view_pdt_PendaftaranDokumen.submit();
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan";
		document.${formName}.submit();
	
		
	}
	
	function tambahLampiran(id,sendCommand){
		var url = "../x/${securityToken}/ekptg.view.pdt.FrmTambahLampiranSemua?idDokumen="+id+"&sendCommand="+sendCommand;
		var hWnd = window.open(url,'printuser','width=800,height=400, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
	      if ((document.window != null) && (!hWnd.opener))
	        hWnd.opener = document.window;
	      if (hWnd.focus != null) hWnd.focus();
	
	}
	
	function hapus(id_){
	if ( !window.confirm("Anda Pasti?") ) return;

		document.${formName}.command.value = "hapus";
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.MaklumatPerundangan&idLampiran="+id_;
		document.${formName}.submit();
		
	}
</script>