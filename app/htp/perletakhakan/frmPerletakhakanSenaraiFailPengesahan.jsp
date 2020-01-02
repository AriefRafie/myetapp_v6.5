<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<fieldset>
<legend>CARIAN</legend>
<table width="100%" border="0">
  <tr>
    <td width="29%"><div align="right">No Fail</div></td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNoFail" type="text" id="txtNoFail" size="40" value="$txtNoFail"></td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Tajuk Fail</div></td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtTajukFail" type="text" id="txtTajukFail" size="40" value="$txtTajukFail"></td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Kementerian</div></td>
    <td width="1%">:</td>
    <td width="70%">$selectKementerianC</td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Agensi</div></td>
    <td width="1%">:</td>
    <td width="70%">$selectAgensiC</td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Negeri</div></td>
    <td width="1%">:</td>
    <td width="70%">$selectNegeriC</td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Daerah</div></td>
    <td width="1%">:</td>
    <td width="70%">$selectDaerahC</td>
  </tr>
  <tr>
    <td width="29%"><div align="right">
      <div align="right">Bandar/Pekan/Mukim</div>
    </div></td>
    <td width="1%">:</td>
    <td width="70%">$selectMukimC</td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%">
    	<input class="stylobutton" type="button" name="cmdCari" id="cmdCari" value="Cari" onClick="carian()"/>
    	<input class="stylobutton" type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="kosongkan()"/>
    </td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend>SENARAI FAIL</legend>
<!-- 
<input class="stylobutton" name="cmdTambah" id="cmdTambah" type="button" value="Tambah" onClick="javascript:tambah()"/>
-->
#parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
  <tr class="table_header">
    <td width="3%" align="center">Bil.</td>
    <td width="20%">No Fail/Rujukan Online</td>
    <td width="50%">Tajuk Fail</td>
    <td width="12%">Negeri</td>
    <td width="15%">Status</td>
  </tr>
  
 #if ($SenaraiFail.size() > 0) 
 #foreach ($list in $SenaraiFail)  
  #if ($list.bil == '') 
  	#set ($row = 'row1')
  #elseif ($list.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr class="$row">
    <td align="center">$list.bil.</td>
    <td >
    #if($list.noFail!='TIADA')
    	<a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"><font color="blue">$list.noFail</font></a>
    #end
    <a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"><font color="blue">$list.noPermohonan</font></a>
    </td>
    <td >$list.tujuan</td>
    <td >$list.negeri</td>
    <td >$list.keterangan</td>
  </tr>
  #end
  #else
   <tr>
   	<td class="row1" align="center">&nbsp;</td>
    <td class="row1">Tiada Rekod</td>
    <td class="row1">&nbsp;</td>
    <td class="row1" align="center">&nbsp;</td>
    <td class="row1">&nbsp;</td>
   </tr>
  #end
</table>
<input name="txtcarian" type="hidden" value="$!iscarian" >

</fieldset>

<script>
function tambah(){
	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=tambah";
	//document.${formName}.submit();
	doAjaxCall${formName}("","actionPerletakhakan=tambah");
	
}

function viewFailById(idA,idB,idC,idE){
	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
	//document.${formName}.submit();
	doAjaxCall${formName}("","actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
	
}
function carian(id){
	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=";
	//document.${formName}.submit();
	document.${formName}.txtcarian.value="ya";
	doAjaxCall${formName}("","");
	
}
function kosongkan(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=";
	document.${formName}.txtNoFail.value="";
	document.${formName}.txtTajukFail.value="";
	document.${formName}.socKementerianC.value="";
	document.${formName}.socAgensiC.value="";
	document.${formName}.socNegeriC.value="";
	document.${formName}.socDaerahC.value="";
	document.${formName}.socMukimC.value="";
	document.${formName}.submit();
}

function doChangeNegeriCarian(){
	doAjaxCall${formName}("doChangeNegeri","");
}
function doChangeDaerahCarian(){
	doAjaxCall${formName}("doChangeDaerah","");
}
function doChangeKementerianCarian(){
	doAjaxCall${formName}("doChangeKementerian","");
}

function doChangeNegeri(){
	doAjaxCall${formName}("doChangeNegeri","actionPerletakhakan=tambah");
}
function doChangeNegeriEdit(){
	doAjaxCall${formName}("doChangeNegeri","actionPerletakhakan=kemaskini");
}
function doChangeKementerian(){
	doAjaxCall${formName}("doChangeKementerian","actionPerletakhakan=tambah");
}
function doChangeKementerianEdit(){
	doAjaxCall${formName}("doChangeKementerian","actionPerletakhakan=kemaskini");
}

// 01/06/2010 -
function senaraiDokumenSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

//2010/04/09 bertujuan mencetak doket atau Fail
// Dibuat oleh  : Rosli
// Dimodifikasi oleh :
function cetakFailDoket(id,temp,urlserv) {
	var param ="";
    var url = "../servlet/"+urlserv+"?"+id+temp;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function edit(idA,idB,idC,idE){
if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih "Kementerian" terlebih dahulu.');
		document.${formName}.socKementerian.focus();
		return;
	}
		if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih "Agensi" terlebih dahulu.');
		document.${formName}.socAgensi.focus();
		return;
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih "Urusan" terlebih dahulu.');
		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila pilih "Tujuan" terlebih dahulu.');
		document.${formName}.txtTajuk.focus();
		return;
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila pilih "No Fail KJP" terlebih dahulu.');
		document.${formName}.txtNoFailKJP.focus();
		return;
	}
		if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila pilih "Tarikh Surat KJP" terlebih dahulu.');
		document.${formName}.txdTarikhSurKJP.focus();
		return;
	}

	doAjaxCall${formName}("","actionPerletakhakan=edit&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
		
}

function sahkan(idFail,idPermohohan){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=sahkan&idPermohonan="+idPermohohan+"&idFail="+idFail;
	document.${formName}.submit();
	
}

function batal(){
	doAjaxCall${formName}("","actionPerletakhakan=papar");
	
}

function simpan(){
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih "Kementerian" terlebih dahulu.');
		document.${formName}.socKementerian.focus();
		return;
	}
		if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih "Agensi" terlebih dahulu.');
		document.${formName}.socAgensi.focus();
		return;
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih "Urusan" terlebih dahulu.');
		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila pilih "Tujuan" terlebih dahulu.');
		document.${formName}.txtTajuk.focus();
		return;
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila pilih "No Fail KJP" terlebih dahulu.');
		document.${formName}.txtNoFailKJP.focus();
		return;
	}
		if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila pilih "Tarikh Surat KJP" terlebih dahulu.');
		document.${formName}.txdTarikhSurKJP.focus();
		return;
	}
	
	doAjaxCall${formName}("","actionPerletakhakan=simpanBaru");

}
function kemaskini(idA,idB,idC,idE) {
	doAjaxCall${formName}("","actionPerletakhakan=kemaskini&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE);
	
}

function kembali() {	
	doAjaxCall${formName}("","actionPerletakhakan=");
	
}

//Fungsi2 untuk page frmPerletakhakanTabHakmilik.jsp
function maklumatHakmilik(id){
	//document.${formName}.actionPerletakhakan.value = "papar";
	//document.${formName}.idFail.value = id;
	//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=maklumathakmilik&idFail="+id;
	//document.${formName}.submit();
	doAjaxCall${formName}("","actionPerletakhakan=maklumathakmilik&idFail="+id);
}

//Fungsi2 untuk page frmPerletakhakanTabHakmilikOnline.jsp
function tambahHakmilik(){
	//document.${formName}.actionPerletakhakan.value = "papar";
	//document.${formName}.mode.value = "newHakmilik";
	//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=papar&mode=newHakmilik";
	//document.${formName}.submit();
	//doAjaxCall${formName}("","actionPerletakhakan=maklumathakmilik&mode=newHakmilik");
	doAjaxCall${formName}("papar");
}
</script>