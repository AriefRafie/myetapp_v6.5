<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
-->
</style>

<input name="mode" type="hidden" value="" />
<input name="idMesyuarat" type="hidden" value="" />
<!--&nbsp; -->
<fieldset>
<legend>CARIAN</legend>
<table width="100%">
  <tr>
    <td width="29%" align="right" scope="row" valign="top"><div align="right">Tajuk Mesyuarat</div></td>
    <td width="1%" scope="row" valign="top"><div align="right" class="style2">:</div></td>
    <td width="70%">
      <label>
        <textarea name="txtTajukMsyrt" cols="41" id="txtTajukMsyrt" onblur="this.value=this.value.toUpperCase()">$txtTajukMsyrt</textarea>
        </label>       </td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Urusetia / Seksyen</div></td>
    <td width="1%" scope="row"><div align="right" class="style2">:</div></td>
    <td>
      <label>$selectSeksyen</label>      </td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Lokasi</div></td>
    <td align="right" scope="row"><div align="right" class="style2">:</div></td>
    <td>
      <label>$selectLokasi</label>          </td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Tarikh Mesyuarat</div></td>
    <td width="1%" scope="row"><div align="right" class="style2">:</div></td>
    <td>
      <label>
        <input name="txdTarikhMsyrt" type="text" id="txdTarikhMsyrt" value="$txdTarikhMsyrt" size="10" />
        </label>
        <a href="javascript:displayDatePicker('txdTarikhMsyrt',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>       </td>
  </tr>
  <tr>
    <td colspan="2" align="right" scope="row">&nbsp;</td>
    <td><label>
      <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
      <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
    </label></td>
  </tr>
</table>
</fieldset>

	<fieldset>
	<legend>SENARAI MESYUARAT</legend>
    	<input type="button" class="stylobutton" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()"/>
      	<!--<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Senarai" /> -->
    	<input type="button" class="stylobutton" name="cmdmaklumat" id="cmdmaklumat" value="Maklumat Rundingan" onclick="maklumatRundingan()"/>

  		#parse("app/utils/record_paging.jsp")

		<table width="100%" cellpadding="2" cellspacing="0">  
			<tr class="table_header">
			    <td width="1%" scope="row">NO</td>
			    <td width="10%">URUSETIA / SEKSYEN</td>
			    <td width="2%">BIL MESYUARAT</td>
			    <td width="20%">TAJUK MESYUARAT</td>
			    <td width="5%">TARIKH MESYUARAT</td>
			    <td width="5%">LOKASI</td>
			    <td width="5%">MASA</td>
			    <td width="10%">STATUS MESYUARAT</td>
		  	</tr>
 			##foreach ($mesyuarat in $Senarai)
   			##foreach ($SenaraiMesyuarat in $Senarai)
			#foreach ($mesyuarat in $senaraiFail)
				#if ($mesyuarat.bil == '') 
			  		#set ($row = 'row1')
				#elseif ($mesyuarat.bil % 2 != 0)
			  		#set ($row = 'row1')
				#else 
			  		#set ($row = 'row2')
				#end 
  			<tr>
    			<td class="$row">$mesyuarat.bil</td>
    			<td class="$row">$mesyuarat.kod_Seksyen</td>
     			#if ($mesyuarat.bil != '') 
    				<td class="$row"><a href="javascript:papar('$mesyuarat.id_Mesyuarat')" class="style1 style1">$mesyuarat.bil_Mesyuarat</a></td>
    			#else
    				<td class="$row">$mesyuarat.bil_Mesyuarat</td>
				#end
    
			    <!--<td class="$row">$Util.subString($mesyuarat.tajuk_Mesyuarat,0,10)</td>-->
			    <td class="$row">$mesyuarat.tajuk_Mesyuarat</td>
			    <td class="$row">$mesyuarat.tarikh_Mesyuarat</td>
			    <td class="$row">$mesyuarat.lokasi</td>
			    <td class="$row">$mesyuarat.masa_Mesyuarat_Dari</td>
			    <td class="$row">$mesyuarat.keterangan</td>
			</tr>
  			
  			#end
		</table>
	</fieldset>
	<!--	<table width="100%" border="0" cellpadding="2">
  			<tr>
    			<td align="right"><strong>CL-05-18</strong></td>
  			</tr>
		</table> -->

<script>

function tambahAsal(){
	document.${formName}.action.value = "tambah";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	
}

function tambah(){
	doAjaxCall${formName}("tambah");
}

//simpan mesyuarat
function simpan(){
	if ( !window.confirm("Anda Pasti?") ) return;
	if (document.${formName}.txtBilMsyrt.value == ""){
		alert('Sila masukkan " Bil Mesyuarat " terlebih dahulu.');
		document.${formName}.txtBilMsyrt.focus();
		return;
	} 
	if (document.${formName}.txtTajukMsyrt.value == ""){
		alert('Sila masukkan " Tajuk Mesyuarat " terlebih dahulu.');
		document.${formName}.txtTajukMsyrt.focus();
		return;
	}
	//document.f1.action = "";
	//document.${formName}.command.value = "simpan";
	if (document.${formName}.idMesyuarat.value == "" || document.${formName}.idMesyuarat.value == 0){
		
		document.${formName}.mode.value = "tambahBaru";
	}
	else{
		
		document.${formName}.mode.value = "kemaskiniMesyuarat";
	}
	alert(document.${formName}.mode.value);
	//document.f1.submit();
	doAjaxCall${formName}("simpan");
	
}

function paparAsal(id){
	document.${formName}.action.value = "papar";
	document.${formName}.idMesyuarat.value = id;
	document.${formName}.mode.value = "";
	document.${formName}.submit();

}

function papar(id){
	document.${formName}.idMesyuarat.value = id;
	document.${formName}.mode.value = "";
	doAjaxCall${formName}("papar");
}

function kemaskini(){	
	document.${formName}.mode.value = "";
	doAjaxCall${formName}("kemaskini");
}

function carian(){
	document.${formName}.action.value = "";
	document.${formName}.submit();
}

function kosongkan(){
	document.${formName}.reset();
	document.${formName}.txtTajukMsyrt.value = "";
	document.${formName}.txdTarikhMsyrt.value = "";
	document.${formName}.socSeksyen.value = "";
	document.${formName}.socLokasi.value = "";
	
}

function tambahAhli(id,command){
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAhliMesyuarat?idMesyuarat="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}

function papar_ahli(id,command){
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAhliMesyuarat?ahliMesyuarat="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}

function tambahAgenda(id,command){
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAgendaMesyuarat?idMesyuarat="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}

function papar_agenda(id,command){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmKemaskiniAgendaMesyuarat?agendaMsyrt="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}

function refresh12() {
	alert('refresh');
	window.onload();
}

function maklumatRundingan(){
	doAjaxCall${formName}("maklumatrundingan");
	
}

</script>