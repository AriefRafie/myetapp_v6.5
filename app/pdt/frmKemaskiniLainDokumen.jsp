


<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {color: #0033FF}
-->
</style>
<input name="idDokumen" type="hidden" value="$idDokumen" />
<div id="mydiv">

<input type="hidden" id="action" name="action" value="$action"/>
<input type="hidden" id="mode" name="mode" value="$mode"/>

<!--<input name="idDokumen" type="hidden" value="$idDokumen" />-->
<!--<input name="idLampiran" type="text" value="" />-->
&nbsp;
  <fieldset>
  <legend><strong>Maklumat Dokumen</strong></legend>
  <table width="100%" border="0" cellspacing="0">
  <tr>
    <td width="29%">Jenis Dokumen</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNoRujDokumen" type="text" id="txtNoRujDokumen" size="44" maxlength="100" $readOnly value="$noRujDok">
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
      <input name="txdTarikhDokumen" type="text" id="txdTarikhDokumen" value="$tkhDok" $readOnly size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);"/>
     <a href="javascript:displayDatePicker('txdTarikhDokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></td>
  </tr>
  <tr>
    <td>Bahagian / Urusetia</td>
    <td>:</td>
    <td>$selectSeksyen</td>
  </tr>
  <tr>
    <td>No. Fail</td>
    <td>:</td>
    <td><input value="$!noFail" $readOnly name="txtNamaFail" type="text" id="txtNamaFail" size="43" maxlength="50" /></td>
  </tr>
  <tr>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtCatatan" cols="82" rows="5" id="txtCatatan" $readOnly onChange="javascript:this.value=ucwords(this.value)">$!catatan</textarea>
    </label></td>
  </tr>
  
 	<tr>
            <td scope="row" valign="top"><i>Tag</i> Dokumen</td>
            <td scope="row" valign="top">:</td> 
            <td scope="row" valign="top">
             	<textarea name="tag_dokumen" cols="82" rows="5" onblur="this.value=this.value.toUpperCase()" $readOnly>$!tagDokumen</textarea>
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
      <button type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" ><font size = "1">Kemaskini</button>
      </label>
      <label>
      <button type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()"><font size = "1">Tambah</button>
      </label>
      <label></label>
      <label>
      <button type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"><font size = "1">Batal</button>
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
 <button type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Tambah" onclick="tambahLampiran('$idDokumen','tambah')"><font size = "1">Tambah</button>
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
    <td class="$row"><a href="javascript:papar_Lampiran('$lampiran.id_Lampiran')" class="style2">$lampiran.nama_Fail<input name="idLampiran"  type="hidden" value="$lampiran.id_Lampiran" /></a></td>
    #else
    <td class="$row">$lampiran.nama_Fail</td>
    #end
    <!--<td class="$row">$lampiran.jenis_Mime</td>-->
    <td class="$row"><label>
     
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
    <td align="right"><strong>CL-06-13</strong></td>
  	</tr>
  </table>
</div>

<script type="text/javascript" src="../app/pdt/dokumenlain.js"></script>

<script>
getDisableFieldDiv('mydiv','$mode');
</script>


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


</script>