<style type="text/css">
<!--
.style1 {color: #0033FF}
.bayaran {
	color: #F00;
}
-->
</style>

<table width="100%" border="0">

#if ($idFail != '')
	<tr>
		<td>
		#parse('app/htp/pajakan/paging.jsp')
		</td>
    </tr>

    <tr>
      <td>#parse("app/htp/frmPajakanHeader.jsp")</td>
    </tr>
    </br>
  <!-- <tr>
    <td>&nbsp;</td>
  </tr> -->

	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT PENAMATAN</strong></legend>
				<table width="100%" border="0">
  						<tr>
  							<td width="50%" valign="top">&nbsp;
								<table width="100%" >
									<tr>
						                <td>&nbsp;</td>
						                <td>Cara Penamatan</td>
						                <td>:</td>
						                <td><select name="sockategori" id="sockategori" $!classDis>

						                  #if($selesaiBean.kat == '98' || $selesaiBean.kat == "" )
						                  <option value="98" selected="selected">PENAMATAN AWAL</option>
						                  <option value="99">TAMAT PAJAKAN</option>

						                  #else
						                  <option value="98" >PENAMATAN AWAL</option>
						                  <option value="99" selected="selected" >TAMAT PAJAKAN</option>

						                  #end

						                </select></td>
						        	</tr>

			          				<tr>
							 			<td width="1%">&nbsp;</td>
							            <td width="40%">
							            	<div align="right" class="labelinput">
												<div align="left">Tarikh </div>
											</div>
							            </td>
							            <td width="1%">:</td>
							            <td width="58%">
								        	<input name="txdTarikhTerima" type="text" id="txdTarikhTerima" onblur="check_date(this);checkDate(this);" value="$!selesaiBean.tarikhSelesai" size="10" maxlength="10" $classDis $mode />

					                    #if ($mode == "baru" || $mode == "kemaskini")
					                        <img src="../img/calendar.gif" alt="calendar" border="0" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');" />

					                     #end
										</td>
						          	</tr>
				          			<tr>
							 			<td width="1%" valign=top>&nbsp;</td>
							            <td width="40%" valign=top>
							            	<div align="right" class="labelinput">
												<div align="left">Sebab Penamatan</div>
											</div>
							            </td>
							            <td width="1%" valign=top>:</td>
					                	  <td>
					                	  	<textarea name="txtKeterangan" id="txtKeterangan" cols="45" rows="3" maxlength="250"
					                	  		onkeyup="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,250);"
					                	  		onkeydown="textCounter(this.form.txtKeterangan,this.form.remtxtcatatan,250);"
					                	  		$mode $classDis >$!selesaiBean.catatan</textarea>
					                	  </td>
                	  				</tr>
							     #if ($mode == "baru" || $mode == "kemaskini")
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td valign="top">&nbsp;</td>
								        <td><input type="text" readonly class="disabled" name="remtxtcatatan" size="3" maxlength="4" value="250"> Baki Aksara </td>
								    </tr>
								#end
								</table>
							</td>
  							<td width="50%" valign="top">&nbsp;</td>
						</tr>
				</table>
			</fieldset>
		</td>
     </tr>

	#if($mode == 'baru' || $mode == "kemaskini")
  	<!--<table width="100%">-->
    	<tr>
       		<td><span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span></td>
   	    </tr>
  	<!--</table>-->
	#end

    <tr>
    <td>

		<div align="center"> <!-- -->
	#if($mode.equals("baru"))
     		<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanSelesai()">
            <!--<input type="reset" class="stylobutton100" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:fGTG_BatalGeran()"> -->
            <input type="reset" class="stylobutton100" name="cmdBatal" value="Kosongkan" >
		<!-- </div> -->
	#elseif($mode.equals("kemaskini"))
		<!-- <div align="center"> -->
        	<input type="button" class="stylobutton100" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick="javascript:kemaskiniSimpanSelesai()">
            <!-- <input type="reset" class="stylobutton" name="cmdBatal2" id="cmdBatal2" $btnNamePemilik onclick="javascript:fGTG_BatalGeran()"/> -->
		<!-- </div> -->
    #elseif($mode.equals("view"))
		<!-- <div align="center"> -->
        	<input type="button" class="stylobutton100" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniSelesai()">
            <!-- <input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGTG_KembaliGeran()"> -->

	                <input class="stylobutton100" type="button" name="cmdCetak" id="cmdCetak" value="Surat" onclick="javascript:setTable('tableReport5')" />
	#end
      	</div>
		</td>
	</tr>

  #else
  <tr>
    <td>
    	&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    </td>
  </tr>
  #end



  <tr align="">
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  	<input type="hidden" name="mode" id="mode" value="$mode"/>
  	<input type="hidden" name="hitButton" id="hitButton" value="$hitButton"/>
  	<input type="hidden" name="idFail" id="idFail" value="$idFail"/>
  	<input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
    <input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>
    <input type="hidden" name="actionPajakan" value="$actionPajakan"/>
    <input type="hidden" name="subUrusan" value="$subUrusan"/>
    <input type="hidden" name="idBayaran" id="idBayaran" value="$idBayaran"/>
	<input type="hidden" name="noFail" value="$NoFail">
 	<input type="hidden" name="selectedTab" value="$!selectedTab"/>

<fieldset id="tableReport5" style="display:none;">
<legend><strong>SENARAI CETAKAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
      	<td><a href="#" onClick="javascript:cetakSuratKemungkiran('$!idPermohonan')"><font color="blue">NOTIS KEMUNGKIRAN PERTAMA / KEDUA & KETIGA</font></a></td>
	  </tr>
	</table>
</fieldset>
<script language="javascript" type="text/javascript">

function daftarBaruBayaran(){
	document.${formName}.actionPajakan.value = "BayaranPajakan";
	document.${formName}.mode.value = "newBayaran";
	doAjaxCall${formName}("");
}

function SimpanBayaran(){
	if(document.${formName}.socTujuan.value == ""){
		alert('Sila pilih Tujuan.');
  		document.${formName}.socTujuan.focus();
		return;
	}
	if(document.${formName}.socCaraBayar.value == ""){
		alert('Sila pilih Cara Bayar.');
  		document.${formName}.socCaraBayar.focus();
		return;
	}

	/*
	if(document.${formName}.txdTarikhTerima.value == ""){
		alert('Sila Masukkan Tarikh Terima.');
  		document.${formName}.txdTarikhTerima.focus();
		return;
	}

	*/
	if(document.${formName}.txdTarikhCek.value != "" ){
		//return checkDate(document.${formName}.txdTarikhCek);
		checkDateV01(document.${formName}.txdTarikhCek);
	}
	if(document.${formName}.txdTarikhTerima.value != "" ){
		//return checkDate(document.${formName}.txdTarikhTerima);
		checkDateV01(document.${formName}.txdTarikhTerima);
	}
	if(document.${formName}.txtJumlahBayaran.value == ""){
		alert('Sila Masukkan Jumlah Bayaran.');
  		document.${formName}.txtJumlahBayaran.focus();
		return;
	}
	if(document.${formName}.txtNoResit.value == ""){
		alert('Sila Masukkan No Resit.');
  		document.${formName}.txtNoResit.focus();
		return;
	}
	if(document.${formName}.txdtarikhResit.value == ""){
		alert('Sila Masukkan Tarikh Resit.');
  		document.${formName}.txdtarikhResit.focus();
		return;
	}

	if(document.${formName}.txdtarikhResit.value != ''){
		checkDateV01(document.${formName}.txdtarikhResit);
	}

	if(document.${formName}.txdtarikhHantarResit.value == ''){
		alert('Sila Masukkan Tarikh Hantar Resit.');
  		document.${formName}.txdtarikhHantarResit.focus();
		return;
	}

	if(document.${formName}.txdtarikhHantarResit.value != ''){
		checkDateV01(document.${formName}.txdtarikhHantarResit);
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "saveBayaran";
	doAjaxCall${formName}("");
}
function BatalBayaran(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "BayaranPajakan";
	doAjaxCall${formName}("");
}
function KemaskiniBayaran(){
	document.${formName}.actionPajakan.value = "BayaranPajakan";
	document.${formName}.mode.value = "updateBayaran";
	doAjaxCall${formName}("");
}

function SimpanUpdateBayaran(){

	if(document.${formName}.socTujuan.value == ""){
		alert('Sila pilih Tujuan.');
  		document.${formName}.socTujuan.focus();
		return;
	}
	if(document.${formName}.socCaraBayar.value == ""){
		alert('Sila pilih Cara Bayar.');
  		document.${formName}.socCaraBayar.focus();
		return;
	}

	/*
	if(document.${formName}.txdTarikhTerima.value == ""){
		alert('Sila Masukkan Tarikh Terima.');
  		document.${formName}.txdTarikhTerima.focus();
		return;
	}
	*/
	if(document.${formName}.txdTarikhCek.value != "" ){
		//return checkDate(document.${formName}.txdTarikhCek);
		checkDateV01(document.${formName}.txdTarikhCek);
	}
	if(document.${formName}.txdTarikhTerima.value != "" ){
		//return checkDate(document.${formName}.txdTarikhTerima);
		checkDateV01(document.${formName}.txdTarikhTerima);

	}
	if(document.${formName}.txtJumlahBayaran.value == ""){
		alert('Sila Masukkan Jumlah Bayaran.');
  		document.${formName}.txtJumlahBayaran.focus();
		return;
	}
	if(document.${formName}.txtNoResit.value == ""){
		alert('Sila Masukkan No Resit.');
  		document.${formName}.txtNoResit.focus();
		return;
	}
	if(document.${formName}.txdtarikhResit.value == ""){
		alert('Sila Masukkan Tarikh Resit.');
  		document.${formName}.txdtarikhResit.focus();
		return;
	}
	if(document.${formName}.txdtarikhResit.value != ''){
		checkDateV01(document.${formName}.txdtarikhResit);
	}

	if(document.${formName}.txdtarikhHantarResit.value == ''){
		alert('Sila Masukkan Tarikh Hantar Resit.');
  		document.${formName}.txdtarikhHantarResit.focus();
		return;
	}

	if(document.${formName}.txdtarikhHantarResit.value != ''){
		checkDateV01(document.${formName}.txdtarikhHantarResit);
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		return;
	}

	document.${formName}.mode.value = "viewBayaran";
	document.${formName}.hitButton.value = "saveUpdateBayaran";
	doAjaxCall${formName}("");
}
function BatalUpdateBayaran(){
	document.${formName}.mode.value = "viewDraf";
	document.${formName}.actionPajakan.value = "BayaranPajakan";
	doAjaxCall${formName}("");
}
function paparBayaran(idBayaran){
	document.${formName}.idBayaran.value = idBayaran;
	document.${formName}.mode.value = "viewBayaran";
	document.${formName}.actionPajakan.value = "BayaranPajakan";
	doAjaxCall${formName}("");
}
function HapusBayaran(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "BayaranPajakan";
	document.${formName}.hitButton.value = "hapusBayaran";
	doAjaxCall${formName}("");
}

function batalUpdateBayaran(){
	document.${formName}.mode.value = "viewBayaran";
	document.${formName}.actionPajakan.value = "BayaranPajakan";
	doAjaxCall${formName}("");
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

//semakan Tarikh semasa
function checkDate(inputfield) {
	var today = new Date();

	dari_bulan = inputfield.value.substring(3,5);
	dari_hari = inputfield.value.substring(0,2);
	dari_tahun = inputfield.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	var myDate = Date.parse(daritemp);

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		inputfield.value = "";
  		inputfield.focus();
 		return;
 	}

}

//semakan Tarikh semasa
function checkDateV01(inputfield) {
	var today = new Date();
	dari_bulan = inputfield.value.substring(3,5);
	dari_hari = inputfield.value.substring(0,2);
	dari_tahun = inputfield.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	var myDate = Date.parse(daritemp);

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		inputfield.value = "";
  		inputfield.focus();
 		return false;
 	}
 	return true;

}

function langkah1(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanSenaraiFailView";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

function langkah2(permohonan,idFail){
	//alert('bayaran:2:'+permohonan+","+idFail);
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&actionPajakan=papar";
	document.${formName}.idPermohonan.value = permohonan;
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function langkah3(permohonan,idFail){
	//alert('bayaran:3:'+permohonan+","+idFail);
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&idPermohonan="+permohonan+"&actionPajakan=paparan";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

function langkah4(permohonan,idFail){
	//alert('bayaran:4:'+permohonan+","+idFail);
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPerjanjianView&idPermohonan="+permohonan+"&actionPajakan=papar";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

//TAB PENGHANTARAN/ SELESAI

	function simpanSelesai(){
		if(document.${formName}.txdTarikhTerima.value == ""){
			alert('Sila masukkan " Tarikh " terlebih dahulu.');
	  		document.${formName}.txdTarikhTerima.focus();
			return;
		}
		document.${formName}.mode.value = "selesaisimpan";
		doAjaxCall${formName}("");

	}

	function kemaskiniSimpanSelesai(){
		document.${formName}.mode.value = "selesaikemaskinisimpan";
		doAjaxCall${formName}("");

	}

	function kemaskiniSelesai(){
		document.${formName}.mode.value = "selesaikemaskini";
		doAjaxCall${formName}("");

	}

	function cetakSuratKemungkiran(idpermohonan) {
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=HTPPajakanNotisKemungkiran&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

	}


</script>
##parse("app/htp/utiliti/javaScriptUmum.jsp")
