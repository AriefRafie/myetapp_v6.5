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
<!--	<tr>
		<td>
		##parse('app/htp/pajakan/paging.jsp')
		</td>
    </tr>
    
    <tr>
      <td>##parse("app/htp/frmPajakanHeader.jsp")</td>
    </tr>
      </br> 
 <tr>
    <td>&nbsp;</td>
  </tr> -->
#else
  <tr>
    <td>
    	&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    </td>
  </tr>
#end
 
	#if ($mode == 'newmaklumatbil' || $mode == 'updateBayaran' || $mode == 'viewBayaran')
  	<tr>
    	<td>
    
  ##foreach ($beanBayaran in $BeanBayaranList)  
	 #set($namaBank = $beanBayaran.namaBank )
  	 #set($NoBayaran = $beanBayaran.NoBayaran )
	 #set($tarikhTerima = $beanBayaran.tarikhTerima )
     #set($jumlahBayaran = $beanBayaran.jumlahBayaran )
     #set($NoResit = $beanBayaran.NoResit )
     #set($tarikhResit = $beanBayaran.tarikhResit )
     #set($tarikhHantarResit = $beanBayaran.tarikhHantarResit )
     #set($tarikhCek = $beanBayaran.tarikhCek )
     ##set($totalBayaran = $totalBayaran + $beanBayaran.jumlahBayaran )
 
    <fieldset>
    <legend><strong>MAKLUMAT BIL</strong></legend>
    <table width="100%" border="0">
  			     	<tr>
			            <td width="1%" class="bayaran">*</td>
			            <td width="15%">Tahun</td>
			            <td width="1%">:</td>
			            <td width="83%">
			            	<!-- <input type="text" name="txtnorujukan" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80" value="$!beanMaklumatPemohon.noPemohon" $readOnly class="$classDis" /> -->
			            	#set ( $selected = "" )
							<select class="autoselect" name="Form_tahun" onchange="getMaklumatBil()">
						   		<option value="0" $selected>Sila Pilih</option>
						   		#set ( $ints = $!tahunSemasa + 2 )
						   		#foreach ( $y in [$!tahunMula..$ints] )
						   		#if ( $y == $tahun_cukai )
						   			#set ( $selected = "selected" )
						   		#else
						   			#set ( $selected = "" )
						   		#end
						   		<option value="$y" $selected>$y</option>
						   		#end
							</select>
			           	</td>
			      	</tr>
	              	<tr>
		                <td>&nbsp;</td>
		                <td>Baki (RM)</td>
		                <td>:</td>
		                <td>
		                  <input name="txtbaki" type="text" value="$!pajakan.getKadarPajakanString()" size="11" $readOnly onblur="validateCurrency(this,this.value,'')"/></td>
		                <td>&nbsp;</td>
	              	</tr>
	              	<tr>
		                <td>&nbsp;</td>
		                <td>Bayaran Pajakan (RM)</td>
		                <td>:</td>
		                <td>
		                  <input name="txtpajakan" type="text" value="$!mbil.getPajakan().getKadarCukaiString()" size="11" $readOnly onblur="validateCurrency(this,this.value,'')"/></td>
		                <td>&nbsp;</td>
	              	</tr>
              		<tr>
				        <td>&nbsp;</td>
				        <td>Bayaran Cukai (RM)</td>
				        <td>:</td>
				        <td> 
				          <input name="txtcukai" type="text" value="$!mbil.getPajakan().getKadarPajakanString()" size="11" $readOnly onblur="validateCurrency(this,this.value,'')"/></td>
				        <td>&nbsp;</td>
			        </tr>			      	
              		<tr>
				        <td>&nbsp;</td>
				        <td>Denda (RM)</td>
				        <td>:</td>
				        <td> 
				          <input name="txtdenda" type="text" value="$!pajakan.getKadarCukaiString()" size="11" $readOnly onblur="validateCurrency(this,this.value,'')"/></td>
				        <td>&nbsp;</td>
			        </tr>
			        <tr>
			            <td width="1%"></td>
			            <td width="15%">Jumlah Bayaran (RM)</td>
			            <td width="1%">:</td>
			            <td width="83%">
		                  <input name="txtbayaran" type="text" value="$!pajakan.getKadarPajakanString()" size="11" $readOnly onblur="validateCurrency(this,this.value,'')"/></td>

			           	</td>
			      	</tr>


    </table>
    
    </fieldset>
  ##end 
    
    </td>
  </tr>
    #if ($mode == 'newmaklumatbil')
  	<tr>
		<td>
	    	<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
	    </td>
	</tr>
	#end
	
      <tr>
        <td colspan="4" align="center">
        
        #if ($mode == 'newmaklumatbil')
        	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanMaklumatBil()" />
        	<input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
            <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalDaftarBil()" />
      	#elseif ($mode == 'viewBayaran')
              <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniBayaran()" />
              <input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusBayaran()" />
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:BatalBayaran()" />
                    
                #elseif ($mode == 'updateBayaran')
                    <input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateBayaran()" />
                    <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateBayaran()" />
                #end 
            </td>
        </tr>	
  <tr>
    <td></td>
  </tr>

#end

#if ( ($idFail != '' && $idStatus != '1' && $idStatus != '6' && $idStatus != '12' && $idStatus != '63' && $idStatus != '65' && $idStatus != '69' && $idStatus != '86' && $idStatus != '87') || ($idFail != '' && $!page == "5"))
	<!-- <tr>
    	<td>&nbsp;</td>
	</tr> 
  </br>-->
	<tr>
    	<td> 
    		<fieldset><legend><strong>SENARAI BIL</strong></legend>
		    <div align="left">
			      <table width="100%" border="0">
			         #if ($mode == 'view')
			        <tr>
			          <td colspan="8" scope="col"><input class="stylobutton100" type="button" name="cmdDaftar" id="cmdDaftar" value="Daftar Baru" onclick="javascript:daftarBaru()" />
			          </td>
			        </tr>
			        #end
			        <tr class="table_header">
			          <td width="3%" scope="col"><strong>Bil.</strong></td>
			          <td width="20%" scope="col"><strong>No. Rujukan</strong></td>
			          <td width="20%" scope="col"><strong>Tahun</strong></td>
			          <td width="20%" scope="col"><strong>Tarikh Dijana</strong></td>
			          <!--<td width="15%" align="right" scope="col"><strong>Nama Bank</strong></td>
			          <td width="11%" scope="col"><strong>Tarikh Resit</strong></td>-->
			          <td width="20%" scope="col"><strong>Jumlah Bayaran <strong>(RM)</strong></strong></td>
			          <td width="10%" scope="col" valign="middle"></td>
			          </tr>
		        #set ($count = 0)
		        #foreach ( $bayaran in $BayaranList )
		        #set ($count = $count+1)
		        #set( $i = $velocityCount )
		        #if ( ($i % 2) != 1 )
		        #set( $row = "row2" )
		        #else
		        #set( $row = "row1" )
		        #end
		        <tr>
		          <td class="$row" scope="row">$bayaran.bil.</td>
		          <td class="$row"><a href="javascript:paparBayaran('$bayaran.idBayaran')" class="style1">$bayaran.NoResit</a></td>
		          <td class="$row">$bayaran.tujuan</td>
		          <td class="$row">$bayaran.tarikhTerima</td>
		          <td class="$row">$bayaran.namaBank</td>
		         	<td class="$row">$bayaran.tarikhResit</td>
		           <!-- <td class="$row">$bayaran.tarikhHantarResit</td>
		          <td class="$row">$bayaran.jumlahBayaran</td> -->
		          </tr>
				#end
		         #if ($count != 0)
		        <tr style="visibility:hidden">
		          <td colspan="7" align="right" class="$row">
		            <span class="bayaran">Jumlah Bayaran Keseluruhan = </span>          </td>
		          <td align="center" class="$row"><span class="bayaran">$totalBayaran</span></td>
		
		        </tr>
		        #end
		        #if ($count == 0)
		        <tr>
		          <td colspan="8" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
		        </tr>
		        #end
		      </table>
	    	</div>
	    	</fieldset>    
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
    <input type="hidden" name="subUrusan" value="$subUrusan"/>
    <input type="hidden" name="idBayaran" id="idBayaran" value="$idBayaran"/>
  	<!-- <input name="mode" type="hidden" id="mode" value="$mode"/> 
    <input type="hidden" name="actionPajakan" value="$actionPajakan"/>
  	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  	<input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  	<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  	<input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
    <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
	
  	-->

<script language="javascript" type="text/javascript">

	// butang [cmdDaftar]
	function XdaftarBaruBayaran(){
		//document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "newBil";
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


</script>
