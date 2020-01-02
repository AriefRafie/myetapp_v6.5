<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>

#set($saizTxtPerkara="1000")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionPelepasan" type="hidden" id="actionPelepasan" value="$actionPelepasan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  
  <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" />
  <input type="hidden" name="idHakmilikSementara" id="idHakmilikSementara" value="$idHakmilikSementara" />
  <input type="hidden" name="idPHPBorangK" id="idPHPBorangK" value="$idPHPBorangK" />
  <input type="hidden" name="idPPTBorangK" id="idPPTBorangK" value="$idPPTBorangK" />
  <input type="hidden" name="idHakmilikUrusan" id="idHakmilikUrusan" value="$idHakmilikUrusan" />
  <input type="hidden" name="afterPost" id="afterPost" value="$afterPost" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
#if($afterPost == 'yes')
  <tr>
    <td>&nbsp;
      <div class="warning">MAKLUMAT BERJAYA DISIMPAN.</div></td>
  </tr>
#else
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%" valign="top">No. Fail</td>
          <td width="1%" >:</td>
          <td width="70%">
            <input name="noFail" type="text" id="noFail" size="49" maxlength="100" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.noFail" onBlur="this.value=this.value.toUpperCase();checkingExistNoFail();" />
            <input name="idPermohonan" type="hidden" value="$beanMaklumatPermohonan.idPermohonan" />
            <input name="idPemohon" type="hidden" value="$beanMaklumatPermohonan.idPemohon" /></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Urusan</td>
          <td>:</td>
          <td>PELEPASAN / PENYERAHANBALIK</td>
        </tr>
       <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td valign="top">Suburusan</td>
          <td>:</td>
          <td>
          	<select name="socSubUrusan" id="socSubUrusan" onchange="doChangeSubUrusan()">
				<option $selected3 value="0">SILA PILIH</option>
				<option $selected value="PLP" >PELEPASAN / PENYERAHAN BALIK </option>
				<option $selected1 value="TKR" >TUKAR GUNA </option>
				<option $selected2 value="PNW" >PENAWARAN </option>
			</select>
		  </td>
<!-- 		  <td width="70%"><select name="socJenisTanah" id="socJenisTanah" onchange="doChangeJenisTanah()" $inputTextClass class="$inputTextClass"> -->
<!--               <option $selected value="0">SILA PILIH</option> -->
<!--               <option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option> -->
<!--               <option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option> -->
<!--               <option $selected3 value="3">BORANG K</option> -->
<!--             </select> -->
<!--            </td> -->
        </tr>
      <tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Terima</td>
          <td>:</td>
          <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" onblur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass"/>
            #if ($mode != 'view') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass"/>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No. Rujukan Surat</td>
          <td>:</td>
          <td><input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat" value="$beanMaklumatPermohonan.noRujukanSurat" $readonly onblur="this.value=this.value.toUpperCase();" size="50" maxlength="50"/></td>
        </tr>
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizTxtPerkara);" onKeyDown="textCounter(this.form.txtPerkara,this.form.remLen1,$!saizTxtPerkara);" >$beanMaklumatPermohonan.perkara</textarea> 
          	#if ($mode == 'new')
            #if ($idJenisTanah == '1' || $idJenisTanah == '2')
            <input type="button" name="cmdJana" id="cmdJana" value="Jana Tajuk" onclick="janaTajuk()"/>
            #end
            #end</td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td>Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtPerkara" /></td>
         </tr>
        #end  
        #end
        
      </table>
      </fieldset></td>
  </tr>
#end
</table>

<!-- <div class="TabbedPanelsContent"> #if ($flagBorangK == 'Y') #parse("app/php2/frmPLPMaklumatBorangK.jsp") #else #parse("app/php2/frmPLPMaklumatTanah.jsp") #end</div> -->

<div id="checkingExistNoFail_result"></div>
<script  type="text/javascript"> 

function doChangeSubUrusan() {
// 	doAjaxCall${formName}("doChangeSubUrusan");
	document.${formName}.actionPelepasan.value = "nextSubUrusan";
	document.${formName}.submit();
}

</script>
