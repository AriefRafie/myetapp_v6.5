<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
			&nbsp;
		</td>
    </tr>
 	<tr>
    	<td>    	

			#parse("app/htp/utiliti/frmIndexCarianHeader.jsp")
			
		            <tr>
		            	<td  width="29%"  scope="row" align="right">Nama Pemohon</td>
		              	<td width="1%">:</td>		              
		              	<td><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" value="$!permohonan.getNoRujukanLain()" size="43" maxlength="50" style="text-transform:uppercase;" /></td>
		            </tr>            
            		<tr>
              			<td width="30%" height="24" scope="row" align="right">Tarikh Terima</td>
		        		<td width="1%">:</td>		              
              			<td width="70%">
              				<input size="11" maxlength="10" type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$!permohonan.getPermohonan().getTarikhTerima()" onblur="check_date(this)">
      						<a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
      					</td>
            		</tr>
			
			#parse("app/htp/utiliti/frmIndexCarianFooter.jsp")

		</td>
	</tr>

  <tr>
    <td><fieldset>
		<legend><b>SENARAI FAIL</b></legend>
        #parse("app/utils/record_paging.jsp")
        
        <table align="center" width="100%"> 
		#if (!$!jenisAkses.equals('Readonly'))
            <tr>
              <td colspan="5" scope="row"><input name="cmdDaftar" type="button" class="stylobutton100" value="Tambah" onclick="javascript:daftarBaru()"/></td>
            </tr>
        #end   
	<tr class="table_header">
   		<!--<td scope="row" width="4%" align="center"><strong>Bil</strong></td>
     	<td width="20%"><strong>NO FAIL</strong></td>
     	<td width="27%" align="center"><strong>NEGERI</strong></td>
      	<td width="25%" align="center"><strong>TAJUK FAIL</strong></td>
      	<td width="24%" align="center"><strong>STATUS</strong></td> -->
      		<td width="3%"><b>BIL.</b></td>
			<td width="20%"><b>NO. FAIL</b></td>
			<td width="37%"><b>TAJUK FAIL</b></td>
			<td width="17%"><b>NEGERI</b></td>
			<td width="23%"><b>STATUS</b></td>
	</tr>
          #set ($list = "")
          #if ($SenaraiFail.size() > 0)
          #foreach ($list in $SenaraiFail)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          <tr>
            <!--<td class="$row" align="center">$list.bil</td>
            <td class="$row"><a href="javascript:papar('$list.idFail')" class="style1">$list.noFail</a></td>
            <td class="$row" align="center">$list.negeri</td>
            <td class="$row" align="center">$list.tajuk</td>
            <td class="$row" align="center"></td> -->
			<td class="$row" >$list.bil.</td>
            <td class="$row"><a href="javascript:papar('$list.idFail')" class="style1">$list.noFail</a></td>
            <td class="$row" >$list.tajuk</td>
            <td class="$row" >$list.negeri</td>
            <td class="$row" >$list.status</td>
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
		</fieldset>
	</td>
  </tr>
</table>
<div id="setSessionIdFail_result"></div>
<input type="hidden" name="idFail" />
<input type="hidden" name="actionPenswastaan" />
<input type="hidden" name="mode" />
<input type="hidden" name="XflagAdvSearch" value="$!flagAdvSearch">

<script>
	
	function Xcarian(){
		document.${formName}.flagAdvSearch.value = "Y";
		document.${formName}.actionPenswastaan.value = "";
		document.${formName}.submit();
	}
	
	function carianFail(){
		document.${formName}.flagAdvSearch.value = "Y";
		document.${formName}.actionPenswastaan.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
	}
	
	function cancel() {
		document.${formName}.reset();
	}
	
	
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.submit();
}
function daftarBaru(){
	document.${formName}.actionPenswastaan.value = "daftarBaru";
	document.${formName}.mode.value = "new";	
	document.${formName}.submit();
}

	function papar(idFail){
		url = "../servlet/ekptg.view.htp.FrmPenswastaan2Servlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		
		document.${formName}.actionPenswastaan.value = "papar";
		document.${formName}.idFail.value = idFail;
		document.${formName}.mode.value = "view";	
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}
	
	function doChangeKementerian() {
		doAjaxCall${formName}("doChangeKementerian");
	}
	
	function simpan() {
		
		if(document.${formName}.socNegeri.value == ""){
			alert('Sila pilih Negeri.');
	  		document.${formName}.socNegeri.focus(); 
			return; 
		}
		if(document.${formName}.socKementerian.value == ""){
			alert('Sila pilih Kementerian.');
	  		document.${formName}.socKementerian.focus(); 
			return; 
		}
		if(document.${formName}.socAgensi.value == ""){
			alert('Sila pilih Agensi.');
	  		document.${formName}.socAgensi.focus(); 
			return; 
		}
		//if(document.${formName}.socAgensi.value == ""){
			//alert('Sila pilih Sub Urusan.');
	  		//document.${formName}.socAgensi.focus(); 
			//return; 
		//}
		if(document.${formName}.socStatusTanah.value == ""){
			alert('Sila pilih Status Tanah.');
	  		document.${formName}.socStatusTanah.focus(); 
			return; 
		}
		if(document.${formName}.socJenisFail.value == ""){
			alert('Sila pilih Jenis Fail.');
	  		document.${formName}.socJenisFail.focus(); 
			return; 
		}
		if(document.${formName}.txtNoFailKJP.value == ""){
			alert('Sila masukkan No. Fail KJP.');
	  		document.${formName}.txtNoFailKJP.focus(); 
			return; 
		}
		if(document.${formName}.tarikhSuratKJP.value == ""){
			alert('Sila masukkan Tarikh Surat KJP.');
	  		document.${formName}.tarikhSuratKJP.focus(); 
			return; 
		}
		//if(document.${formName}.txtNoFailLain.value == ""){
	//		alert('Sila masukkan No. Fail Lain.');
	//  		document.${formName}.txtNoFailLain.focus(); 
	//		return; 
	//	}
		if(document.${formName}.tarikhAgihan.value == ""){
			alert('Sila masukkan Tarikh Agihan.');
	  		document.${formName}.tarikhAgihan.focus(); 
			return; 
		}
		if(document.${formName}.txtTajuk.value == ""){
			alert('Sila masukkan Tajuk.');
	  		document.${formName}.txtTajuk.focus(); 
			return; 
		}
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPenswastaan.value = "daftarBaru";
			return;
		}
		
		document.${formName}.actionPenswastaan.value = "papar";
		document.${formName}.hitButton.value = "simpan";
		document.${formName}.submit();
	}
	
	function kembali() {	
		document.${formName}.actionPenswastaan.value = "";
		document.${formName}.submit();
	}
	
	function Kemaskini(){
		document.${formName}.actionPenswastaan.value = "papar";
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}("");
	}
	
	function batal(){
		document.${formName}.actionPenswastaan.value = "papar";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
	}
	
	function saveUpdate(){
		document.${formName}.mode.value = "view";
		document.${formName}.actionPenswastaan.value = "papar";
		document.${formName}.hitButton.value = "saveUpdate";
		doAjaxCall${formName}("");
	}
	
	//by Rosli 2010/04/01
	function tambahFailLain(id,command){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmFailLainKemaskini?idFailLama="+id+"&command="+command;
	    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
	  	if ((document.window != null) && (!hWnd.opener))
	   		hWnd.opener = document.window;
	  	if (hWnd.focus != null) hWnd.focus();
	}
	
	// 18/07/2010 -
	function senaraiDokumenSurat(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}
	
	//2010/07/18 bertujuan mencetak doket atau Fail
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
	
</script>