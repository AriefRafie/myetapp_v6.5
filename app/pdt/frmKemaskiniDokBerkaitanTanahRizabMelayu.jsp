<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {color: #0033FF}
-->
</style>
<input name="mode" type="hidden" value="$mode" />
<input name="idDokumen" type="hidden" value="$idDokumen" />
<!--<input name="idLampiran" type="text" value="" />-->
&nbsp;
  <fieldset>
  <legend><strong>Maklumat Dokumen</strong></legend>
  <table width="100%" border="0" cellspacing="0">
  <tr>
    <td width="29%">Jenis Dokumen</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNoRujDokumen" type="text" id="txtNoRujDokumen" size="44" maxlength="50" $readOnly value="$noRujDok">
    </label></td>
  </tr>
  <tr>
    <td valign="top"><span class="style1">*</span>Tajuk Dokumen</td>
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
  <!--
  <tr>
    <td><span class="style1">*</span>Jenis Dokumen</td>
    <td>:</td>
    <td>$selectJenisDokumen</td>
  </tr>
  -->
  <tr>
    <td>Tarikh Dokumen</td>
    <td>:</td>
    <td><label>
      <input name="txdTarikhDokumen" type="text" id="txdTarikhDokumen" value="$tkhDok" size="10" 0" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" $readOnly>
     <a href="javascript:displayDatePicker('txdTarikhDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></td>
  </tr>
  <tr>
    <td>Bahagian / Urusetia</td>
    <td>:</td>
    <td>$selectSeksyen</td>
  </tr>
  <tr>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtCatatan" cols="82" rows="5" id="txtCatatan" $readOnly onChange="javascript:this.value=ucwords(this.value)">$catatan</textarea>
    </label></td>
  </tr>
  
 	<tr hidden="hidden">
            <td scope="row" valign="top"><i>Tag</i> Dokumen</td>
            <td scope="row" valign="top">:</td> 
            <td scope="row" valign="top">
             	<textarea name="tag_dokumen" cols="82" rows="5" onblur="this.value=this.value.toUpperCase()" $readOnly >$!tag_Dokumen</textarea>
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
<table width="100%" >
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
      <!--<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus('$lampiran.id_Lampiran')"> -->
    	<a alt="Hapus Dokumen" href = "javascript:hapus('$dokumen.idDokumen')">
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
    <td align="right"><strong>CL-06-13</strong></td>
  	</tr>
  </table>


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
	//if (document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txtNoRujDokumen.value == ""){
			//alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
			//document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txtNoRujDokumen.focus();
			//return;
	//} 
	var editorInstance = FCKeditorAPI.GetInstance('txtTajukDokumen');   
  	var tajuk_Dokumen = editorInstance.GetHTML(true);
	var textlength = tajuk_Dokumen.length;                           
    if(textlength==0){
    	alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
        oEditor.EditorDocument.body.focus();
        return;
 	}		
	/*	if (document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txtTajukDokumen.value == ""){
			alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
			document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.txtTajukDokumen.focus();
			return;
	}

	if (document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.socJenisDokumen.value == ""){
			alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.');
			document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.socJenisDokumen.focus();
			return;
	} */
	
if ( !window.confirm("Anda Pasti?") ) return;	

if (document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.idDokumen.value == "" || document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.idDokumen.value == 0){
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "simpan";
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.command.value = "simpan";
	//doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("","action=simpan&mode=");
	
} else {
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action.value = "editData"; 
	document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.command.value = "editData";
	//doAjaxCallFekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu("","action=editData&mode=");
	
}

//
document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.mode.value = "";
//
document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.action = "?_portal_module=ekptg.view.pdt.FrmDokBerkaitanTanahRizabMelayu&txtTajukDokumen_="+escape(tajuk_Dokumen);
document.Fekptg_view_pdt_FrmDokBerkaitanTanahRizabMelayu.submit();

}
</script>
<script>
//alert("ssssss");
//alert("sssssss"+'$current_role');

$jquery(document).ready(function () {

	//if('$current_role'=="(PDT)HQPengguna" || '$current_role'=="(PDT) Pengguna Bahagian Strata")
	if('$current_role'=="(PDT)HQPengguna")
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		//$jquery("input[type=button]").hide();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		$jquery("input[type=button]").show();
	}
	else
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		//$jquery("input[type=button]").show();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		$jquery("input[type=button]").hide();
	}
});

</script>
