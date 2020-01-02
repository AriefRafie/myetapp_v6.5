
<fieldset>
<legend>Carian</legend>
<table width="100%" border="0">
  <tr>
    <td width="29%" align="right">No Fail</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtNoFail" id="txtNoFail" size="40" value="$!txtNoFail"></td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"><input type="button" name="cmdCari" id="cmdCari" value="Cari" onClick="carian()"/>
    <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="kosongkan()"/></td>
  </tr>
</table>
</fieldset>
<br>
<fieldset>
<legend>Senarai Maklumat Perletakhakan</legend>
#parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
  <tr class="table_header">
    <td>No</td>
    <td>No Fail</td>
    <td>Tarikh Mohon</td>
    <td>Tujuan Mohon</td>
    <td>Status Fail</td>
  </tr>
#foreach ($fail in $SenaraiFail)
  
  #if ($fail.bil == '') 
  	#set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$fail.bil </td>
    #if ($fail.bil == '') 
    <td  class="$row">$fail.noFail</td>
    #else
    <td class="$row"><a href="javascript:viewFailById('$fail.idFail','$fail.idPermohonan')"><font color="blue">$fail.noFail</font></a></td>
    #end
    <td class="$row">$fail.tarikhDaftar</td>
    <td class="$row">$fail.tujuan</td>
    <td class="$row">$fail.keterangan</td>
  </tr>
  #end
</table>

<input name="idFail" type="hidden" id="idFail"/>
<input name="idPermohonan" type="hidden" id="idPermohonan"/>
<input name="idSuburusanStatusFail" type="hidden" />
<input name="actionPerletakhakan" type="hidden" />

</fieldset>
<script>
function viewFailById(idA,idB){
	
	document.${formName}.actionPerletakhakan.value = "papar";
	document.${formName}.idFail.value = idA;
    document.${formName}.idPermohonan.value = idB;
    doAjaxCall${formName}("");
	//document.${formName}.submit();
}

function carian(){
	document.${formName}.actionPerletakhakan.value = "";
	document.${formName}.submit();
}

function doChangeTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function kembali() {
	document.${formName}.actionPerletakhakan.value = "";
	doAjaxCall${formName}("");
}
function kemaskini(tabId) {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanAkta() {

	if(document.${formName}.txtNoAkta.value == ""){
		alert('Sila masukkan No Akta.');
  		document.${formName}.txtNoAkta.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhKuatkuasa.value == ""){
		alert('Sila masukkan Tarikh Kuatkuasa.');
  		document.${formName}.txdTarikhKuatkuasa.focus(); 
		return; 
	}
	if(document.${formName}.txtTajukAkta.value == ""){
		alert('Sila masukkan Tajuk Akta.');
  		document.${formName}.txtTajukAkta.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPelepasan.value = "papar";
		return;
	}
	
	document.${formName}.hitButton.value = "simpanAkta";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanPerintah() {
	
	if(document.${formName}.txtNoSamanPemula.value == ""){
		alert('Sila masukkan No Saman Pemula.');
  		document.${formName}.txtNoSamanPemula.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaMahkamah.value == ""){
		alert('Sila masukkan Nama Mahkamah.');
  		document.${formName}.txtNamaMahkamah.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhPerintah.value == ""){
		alert('Sila masukkan Tarikh Perintah.');
  		document.${formName}.txdTarikhPerintah.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPelepasan.value = "papar";
		return;
	}
	
	document.${formName}.hitButton.value = "simpanPerintah";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanBayaran() {

	if(document.${formName}.socCaraBayar.value == ""){
		alert('Sila pilih Cara Bayar.');
  		document.${formName}.socCaraBayar.focus(); 
		return; 
	}
	if(document.${formName}.txtNoCek.value == ""){
		alert('Sila masukkan No Cek.');
  		document.${formName}.txtNoCek.focus(); 
		return; 
	}
	if(document.${formName}.txtJumlahBayar.value == ""){
		alert('Sila masukkan Jumlah Bayaran.');
  		document.${formName}.txtJumlahBayar.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPelepasan.value = "papar";
		return;
	}
	
	document.${formName}.hitButton.value = "simpanBayaran";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function formatCurrencySemasa(num) { 
	num = num.toString().replace(/\$|\,/g,''); 
	if(isNaN(num)) num = "0"; 
	sign = (num == (num = Math.abs(num)));
 	num = Math.floor(num*100+0.50000000001); 
 	cents = num%100; num = Math.floor(num/100).toString();
  	if(cents<10) cents = "0" + cents; 
 	 for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	  num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3)); 
    document.${formName}.txtJumlahBayar.value = num + '.' + cents; 
}
function formatCurrencySemasa1(num) { 
	num = num.toString().replace(/\$|\,/g,''); 
	if(isNaN(num)) num = "0"; 
	sign = (num == (num = Math.abs(num)));
 	num = Math.floor(num*100+0.50000000001); 
 	cents = num%100; num = Math.floor(num/100).toString();
  	if(cents<10) cents = "0" + cents; 
 	 for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	  num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3)); 
    document.${formName}.txtAmaunResit.value = num + '.' + cents; 
}

function setSenaraiSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

// 2010/05/25 -Pilih Pegawai untuk tandatangan surat
function openSuratPegawai(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}

</script>