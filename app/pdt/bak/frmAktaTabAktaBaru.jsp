        <fieldset>
        <table width="100%" border="0">      
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">*</span> No Akta</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              <input name="txtNoAkta" type="text" id="txtNoAkta" onblur="this.value=this.value.toUpperCase()" value="$!Akta_NoAkta" size="25" $RO_General />
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">*</span> Nama Akta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtNamaAkta" type="text" id="txtNamaAkta" onblur="this.value=this.value.toUpperCase()" value="$!Akta_NamaAkta" size="50" $RO_General />
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Kuatkuasa</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txdTarikhKuatkuasa" id="txdTarikhKuatkuasa" value="$!Akta_TarikhKuatkuasa" size="10" $RO_General />
              <a href="javascript:displayDatePicker('txdTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif" /></a>      
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Mansuh</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txdTarikhMansuh" id="txdTarikhMansuh" value="$!Akta_TarikhMansuh" size="10" $RO_General />
              <a href="javascript:displayDatePicker('txdTarikhMansuh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">No Warta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtNoWarta" type="text" id="txtNoWarta" onblur="this.value=this.value.toUpperCase()" value="$!Akta_NoWarta" size="25" $RO_General />
            </td>
          </tr> 
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Warta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txdTarikhWarta" id="txdTarikhWarta" value="$!Akta_TarikhWarta" onblur="this.value=this.value.toUpperCase()" size="10" $RO_General />
              <a href="javascript:displayDatePicker('txdTarikhWarta',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            </td>
          </tr>
          <!--
          <tr>
            <td width="29%" align="right" valign="top">Taraf Keselamatan Akta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="radio" name="sorTaraf" id="sorTaraf" value="1" $RO_General $Akta_AktaTerbuka />Terbuka
              <input type="radio" name="sorTaraf" id="sorTaraf" value="1" $RO_General $Akta_AktaSulit />Sulit
            </td>
          </tr>
          -->
          <tr>
            <td width="29%" align="right" valign="top">Seksyen / Urusetia</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtSeksyen" type="text" id="txtSeksyen" value="$!Akta_IDSeksyen" size="25" $RO_General />
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">No Fail</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="socNoFail" type="text" id="socNoFail" value="$!Akta_NoFail" size="50" $RO_General />
            </td>
          </tr> 
          <tr>
            <td width="29%" align="right" valign="top">Catatan</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
             <textarea name="txtCatatan" id="txtCatatan" cols="50" rows="5" onblur="this.value=this.value.toUpperCase()" $RO_General>$!Akta_Catatan</textarea>
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Muat Naik Dokumen</td>
            <td align="center" valign="top">:</td> 
            <td valign="top"><input id="txfMuatNaikDokumen" name="txfMuatNaikDokumen" type="file" size="40" /></td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Tarikh Daftar</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" value="$!Akta_TarikhDaftar" size="10" $RO_General />
              <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            </td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>#if ($action == 'view')
              <input type="button" value="Kemaskini" onclick="doAdd()"/>
#elseif ($action == 'doUpdate')
<input type="button" value="Simpan Kemasknini" onclick="doKemaskiniPara()"/>
<input type="button" value="Batal" onclick="doCancel()" />
#elseif ($action == 'update')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="doSaveAkta()"/>
#else
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doBack()"/>
#end </td>
          </tr>
        </table>
        </fieldset>
        <br />

<script>

function doBack() {
doAjaxCall${formName}("");
}

function doSave() {
    doAjaxCall${formName}("","action=save");
}

function doSaveAkta() {
	if (document.${formName}.txtNoAkta.value == "") {
		alert("Sila masukkan no akta");
		document.${formName}.txtNoAkta.focus();
		return;
	}
	else if (document.${formName}.txtNamaAkta.value == "") {
		alert("Sila masukkan nama akta");
		document.${formName}.txtNamaAkta.focus();
		return;
	}
    var x = create_request_string(document.${formName});
    //alert(x);
    //return;
    document.${formName}.method="post";
    document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
    document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmViewAkta?xx=yy&mode=insert&action=save&"+x;
    document.${formName}.submit();
    //doAjaxCall${formName}("","action=save");
}


</script>