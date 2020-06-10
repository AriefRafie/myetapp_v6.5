#set($saizJUPEM="4000")
#set($saizJPS="4000")
#set($saizJMG="4000")
#set($saizPHN="4000")
#set($saizDOF="4000")
#set($saizJLM="4000")
#set($saizJAS="4000")
#set($saizPTG="4000")
#set($saizUlasanJabatan="4000")
<style type="text/css">
<!--
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idKertasKerjaApb" type="hidden" id="idKertasKerjaApb" value="$idKertasKerjaApb"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td><div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $flagOpenDetail)  
  #foreach ($beanMaklumatKertasRingkasPermohonan in $BeanMaklumatKertasRingkasPermohonan)
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT KERTAS RINGKASAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Kertas</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhKertas" type="text" class="$inputTextClass" id="txtTarikhKertas" value="$beanMaklumatKertasRingkasPermohonan.tarikhKertas" size="9"  $readonly onblur="check_date(this);">
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhKertas',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Nama PTP</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaPTP" type="text" class="$inputTextClass" id="txtNamaPTP" value="$beanMaklumatKertasRingkasPermohonan.namaPTP" size="50" maxlength="200"  $readonly ></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Nama KSU</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaKSU" type="text" class="$inputTextClass" id="txtNamaKSU" value="$beanMaklumatKertasRingkasPermohonan.namaKSU" size="50" maxlength="200"  $readonly  /></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Nama Menteri</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNamaMenteri" type="text" class="$inputTextClass" id="txtNamaMenteri" value="$beanMaklumatKertasRingkasPermohonan.namaMenteri" size="50" maxlength="200"  $readonly  /></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>ULASAN JABATAN-JABATAN  TEKNIKAL</strong><br />
      </legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">Jabatan Ukur dan Pemetaan  Malaysia (JUPEM)</td>
          <td width="1%" valign="top">:</td>
          <td width="70%"><textarea name="JUPEM" id="JUPEM" cols="100" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.JUPEM,this.form.remLenJUPEM,$!saizJUPEM);" onKeyDown="textCounter(this.form.JUPEM,this.form.remLenJUPEM,$!saizJUPEM);">$beanMaklumatKertasRingkasPermohonan.ulasanJUPEM</textarea>
          </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLenJUPEM" size="3" maxlength="3" value="$!saizJUPEM" /></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Jabatan Pengairan dan Saliran (JPS)</td>
          <td valign="top">:</td>
          <td><textarea name="JPS" id="JPS" cols="100" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.JPS,this.form.remLenJPS,$!saizJPS);" onKeyDown="textCounter(this.form.JPS,this.form.remLenJPS,$!saizJPS);">$beanMaklumatKertasRingkasPermohonan.ulasanJPS</textarea>
          </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLenJPS" size="3" maxlength="3" value="$!saizJPS" /></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Jabatan Mineral dan  Geosains Malaysia (JMG)</td>
          <td valign="top">:</td>
          <td><textarea name="JMG" id="JMG" cols="100" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.JMG,this.form.remLenJMG,$!saizJMG);" onKeyDown="textCounter(this.form.JMG,this.form.remLenJMG,$!saizJMG);">$beanMaklumatKertasRingkasPermohonan.ulasanJMG</textarea>
          </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLenJMG" size="3" maxlength="3" value="$!saizJMG" /></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td valign="top"><p>Pusat Hidrografi Nasional (PHN)</p></td>
          <td valign="top">:</td>
          <td><textarea name="PHN" id="PHN" cols="100" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.PHN,this.form.remLenPHN,$!saizPHN);" onKeyDown="textCounter(this.form.PHN,this.form.remLenPHN,$!saizPHN);">$beanMaklumatKertasRingkasPermohonan.ulasanPHN</textarea>
          </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLenPHN" size="3" maxlength="3" value="$!saizPHN" /></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Jabatan Perikanan Malaysia (DOF)</td>
          <td valign="top">:</td>
          <td><textarea name="DOF" id="DOF" cols="100" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.DOF,this.form.remLenDOF,$!saizDOF);" onKeyDown="textCounter(this.form.DOF,this.form.remLenDOF,$!saizDOF);">$beanMaklumatKertasRingkasPermohonan.ulasanDOF</textarea>
          </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLenDOF" size="3" maxlength="3" value="$!saizDOF" /></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Jabatan Laut Semenanjung Malaysia (JLM)</td>
          <td valign="top">:</td>
          <td><textarea name="JLM" id="JLM" cols="100" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.JLM,this.form.remLenJLM,$!saizJLM);" onKeyDown="textCounter(this.form.JLM,this.form.remLenJLM,$!saizJLM);">$beanMaklumatKertasRingkasPermohonan.ulasanJLM</textarea>
          </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLenJLM" size="3" maxlength="3" value="$!saizJLM" /></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Jabatan Alam Sekitar (JAS)</td>
          <td valign="top">:</td>
          <td><textarea name="JAS" id="JAS" cols="100" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.JAS,this.form.remLenJAS,$!saizJAS);" onKeyDown="textCounter(this.form.JAS,this.form.remLenJAS,$!saizJAS);">$beanMaklumatKertasRingkasPermohonan.ulasanJAS</textarea>
          </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLenJAS" size="3" maxlength="3" value="$!saizJAS" /></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Pejabat Tanah Galian (PTG) </td>
          <td valign="top">:</td>
          <td><textarea name="PTG" id="PTG" cols="100" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.PTG,this.form.remLenPTG,$!saizPTG);" onKeyDown="textCounter(this.form.PTG,this.form.remLenPTG,$!saizPTG);">$beanMaklumatKertasRingkasPermohonan.ulasanPTG</textarea>
          </td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLenPTG" size="3" maxlength="3" value="$!saizPTG" /></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>ULASAN JABATAN KETUA PENGARAH TANAH DAN GALIAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td width="28%" valign="top">Ulasan</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><textarea name="txtUlasanJabatan" id="txtUlasanJabatan" cols="100" rows="5" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtUlasanJabatan,this.form.remLenUlasanJabatan,$!saizUlasanJabatan);" onKeyDown="textCounter(this.form.txtUlasanJabatan,this.form.remLenUlasanJabatan,$!saizUlasanJabatan);">$beanMaklumatKertasRingkasPermohonan.ulasanJabatan</textarea></td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLenUlasanJabatan" size="3" maxlength="3" value="$!saizUlasanJabatan" /></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td valign="top">Syor</td>
          <td valign="top">:</td>
          <td valign="top"><select name="socSyor" id="socSyor" style="width:200px;" $readonly class="$inputTextClass" $inputTextClass> 
			#if($beanMaklumatKertasRingkasPermohonan.syorJabatan == 'L')
              <option value="">SILA PILIH</option>
              <option value="L" selected="selected">LULUS</option>
              <option value="D">LULUS SECARA DASAR</option>
              <option value="T">TOLAK</option>
              <option value="G">TANGGUH</option>
             #elseif($beanMaklumatKertasRingkasPermohonan.syorJabatan == 'D')
              <option value="">SILA PILIH</option>
              <option value="L">LULUS</option>
              <option value="D" selected="selected">LULUS SECARA DASAR</option>
              <option value="T">TOLAK</option>
              <option value="G">TANGGUH</option>
            #elseif($beanMaklumatKertasRingkasPermohonan.syorJabatan == 'T')
              <option value="">SILA PILIH</option>
              <option value="L">LULUS</option>
              <option value="D">LULUS SECARA DASAR</option>
              <option value="T" selected="selected">TOLAK</option>
              <option value="G">TANGGUH</option>
            #elseif($beanMaklumatKertasRingkasPermohonan.syorJabatan == 'G')
              <option value="">SILA PILIH</option>
              <option value="L">LULUS</option>
              <option value="D">LULUS SECARA DASAR</option>
              <option value="T">TOLAK</option>
              <option value="G" selected="selected">TANGGUH</option>
            #else
              <option value="" selected="selected">SILA PILIH</option>
              <option value="L">LULUS</option>
              <option value="D">LULUS SECARA DASAR</option>
              <option value="T">TOLAK</option>
              <option value="G">TANGGUH</option>
            #end
            </select></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT MESYUARAT JAWATANKUASA <i>ONE STOP CENTRE</i></strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%" valign="top">&nbsp;</td>
          <td width="28%" valign="top">Bil. Mesyuarat</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><input name="txtBilMesyuarat" type="text" class="$inputTextClass" id="txtBilMesyuarat" value="$!beanMaklumatKertasRingkasPermohonan.bilMesyuarat" size="50" maxlength="50"  $readonly  /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Mesyuarat</td>
          <td>:</td>
          <td><input name="txtTarikhMesyuarat" type="text" class="$inputTextClass" id="txtTarikhMesyuarat" value="$!beanMaklumatKertasRingkasPermohonan.tarikhMesyuarat" size="9"  $readonly onblur="check_date(this);" />
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhMesyuarat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">&nbsp;</td>
          <td width="1%">&nbsp;</td>
          <td width="70%"> #if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
            #if($idStatus =='1610213')
            <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end
            <input type="button" name="cdmCetak3" id="cdmCetak3" value="Cetak" onClick="javascript:setTable('tableReport')"/>
            #elseif ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="doSimpanKemaskiniMaklumatKertasRingkasPermohonan()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
            #end </td>
        </tr>
      </table></td>
  </tr>  
  #elseif ($idFail != '')
  <tr>
    <td> 
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI DOKUMEN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakMinitBebas('$idFail')"> Memo </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakKertasRingkasan('$idFail')"> Kertas Ringkasan </a></td>
  </tr>
</table>
</fieldset>
<script>
function doKemaskini() {
	document.${formName}.mode.value = "update";
	document.${formName}.submit();
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatKertasRingkasPermohonan() {
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatKertasRingkasPermohonan";
	document.${formName}.submit();
}
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
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
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakMinitBebas(idFail) {
	var url = "../servlet/ekptg.report.php2.APBMinitBebas?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakKertasRingkasan(idFail) {
	var url = "../servlet/ekptg.report.php2.APBKertasRingkasan?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<input name="step" type="hidden" id="step"/>
<script>
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
