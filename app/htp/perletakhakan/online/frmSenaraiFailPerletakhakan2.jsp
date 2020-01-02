<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<fieldset>
<legend>CARIAN</legend>
<table width="100%" border="0">
  <tr>
    <td width="29%"><div align="right">No Fail</div></td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtNoFail" type="text" id="txtNoFail" size="43" value="$txtNoFail"></td>
  </tr>
  <tr>
    <td width="29%"><div align="right">Tajuk Fail</div></td>
    <td width="1%">:</td>
    <td width="70%"><input name="txtTajukFail" type="text" id="txtTajukFail" size="43" value="$txtTajukFail"></td>
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
    	<input class="stylobutton100" type="button" name="cmdCari" id="cmdCari" value="Cari" onClick="carian()"/>
    	<input class="stylobutton100" type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="kosongkan()"/>
    </td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend>SENARAI FAIL</legend>

#parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
  <tr class="table_header">
    <td width="5%" align="center"><b>Bil.</b></td>
    <td width="20%"><b>No Fail</b></td>
    <td width="50%"><b>Tajuk Fail</b></td>
    <td width="10%"><b>Negeri</b></td>
    <td width="15%"><b>Status</b></td>
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
    <td ><a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"><font color="blue">$list.noFail</font></a></td>
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
	//OK
	function viewFailById(idA,idB,idC,idE){
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPerletakhakanPendaftaranOnline&actionPerletakhakan=papar&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
		document.${formName}.submit();
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

function doChangeNegeri(){
	doAjaxCall${formName}("doChangeNegeri","");
}
function doChangeDaerah(){
	doAjaxCall${formName}("doChangeDaerah","");
}
function doChangeKementerian(){
	doAjaxCall${formName}("doChangeKementerian","");
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
function maklumatHakmilik(id){
	//document.${formName}.actionPerletakhakan.value = "papar";
	//document.${formName}.idFail.value = id;
	//document.${formName}.action = "?_portal_module=ekptg.view.online.htp.perletakhakan.FrmPermohonanPerletakhakan&actionPerletakhakan=maklumathakmilik&idFail="+id;
	//document.${formName}.submit();
	doAjaxCall${formName}("","actionPerletakhakan=maklumathakmilik&idFail="+id);
}
function paparHakmilik(id){	
	document.${formName}.actionPerletakhakan.value = "maklumathakmilik";
	document.${formName}.mode.value ="viewHakmilik";
	document.${formName}.idHakmilikurusan.value = id;
	doAjaxCall${formName}(""); 
}

function senaraiFail(id){	
	document.${formName}.actionPerletakhakan.value = "";
	//document.${formName}.mode.value ="viewHakmilik";
	//document.${formName}.idHakmilikurusan.value = id;
	doAjaxCall${formName}(""); 
}
</script>