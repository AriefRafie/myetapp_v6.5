<!--frmCukaiSenarai.jsp-->
  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style><!--<strong> Senarai Fail Cukai </strong>
<br><br>-->

<table width="99%" border="0">
  <tr>
    <td>&nbsp; </td>
  </tr>  
  
  <tr>
    <td>
 
<fieldset> <legend>MAKLUMAT PILIHAN</legend>
<!--<form name="f1" method="post"> -->
  <table border="0" width="100%">
    	<tr>			  
			<td colspan="2" align="center">&nbsp;</td>
		</tr>
      <tr >
        <td align="right" width="26%">Negeri : &nbsp;&nbsp;</td>
        <td width="74%">$selectNegeri</td>
      </tr>    
      <tr>
        <td align="right" width="26%">Daerah : &nbsp;&nbsp;</td>
        <td>$selectDaerah</td>
      </tr>
      <tr>
        <td align="right" width="26%">Bandar/Pekan/Mukim : &nbsp;&nbsp;</td>
        <td>$selectMukim</td>
      </tr>
      <tr>
        <td align="right">Pilihan lanjut</td>
        <td>
            <input name="status" type="checkbox" value="pembelian"/>
            Pembelian
         	<input name="status" type="checkbox" value="pajakan"/>
            Pajakan
            <input name="status" type="checkbox" value="rizab"/>
            Rizab
            
        </td>
      </tr>

      <tr>
        <td></td>
        <td><input class="stylobutton100"  name="cari" value="Hantar" type="button" onclick="javascript:search_data()">
        <!--<input value="Kosongkan" onclick="javascript:cancel()" type="button">--></td>
      </tr>
  </table>
<!--  <input type="hidden" name="command">
</form>-->
</fieldset>


		</td>
	</tr>
</table>

  <input type="hidden" name="idpermohonan">
  <input type="hidden" name="idFail" value="$fail.idFail">
  <input type="hidden" name="noFail">
  <input type="hidden" name="command1">
  <input type="hidden" name="mode">

<script>

  

function cancel() {
	document.f1.reset();
	document.f1.NamaFail.value = "";
	document.f1.NoFail.value = "";
	document.f1.socNegeri.value = "";
	document.f1.NamaFail.focus();
}

function Tambah() {
	document.tbh.command.value = "FailBaru";
	//document.tbh.action = "?_portal_module=ekptg.htp.FrmGadaianSemakan";
	document.tbh.action = "";
	document.tbh.submit();
}

function search_data(){
	if(document.${formName}.idnegeri.value==""){
		alert('Sila pilih negeri terlebih dahulu');
		return;
		}
	//document.${formName}.command.value = "cukaiterperinci";
	idnegeri = document.${formName}.idnegeri.value;
	iddaerah = document.${formName}.iddaerah.value;
	idmukim = document.${formName}.idmukim.value;
	
	
	param = "idnegeri="+idnegeri+"&iddaerah="+iddaerah+"&idmukim="+idmukim;
	doAjaxCall${formName}("cukaiterperinci");
	//document.${formName}.command.value = "cukaiterperinci";																							;
	//showWindow('ekptg.view.htp.FrmCukaiSenaraiHakmilik?command=cukaiterperinci&'+param);
}

function showWindow(servlet){

          //var url = "../x/${securityToken}/ekptg.view.htp.FrmCukaiSenaraiHakmilik";
          var url = "../x/${securityToken}/"+servlet;
          var hWnd = window.open(url,'printuser','width=1050,height=500, resizable=yes,scrollbars=yes');
          if ((document.window != null) && (!hWnd.opener))
            hWnd.opener = document.window;
          if (hWnd.focus != null) hWnd.focus();
}

function seterusnya(id, no) {
	document.f2.idpermohonan.value = id;
	document.f2.noFail.value = no;
	document.f2.mode.value = "view";
	document.f2.command.value = "surat";
	document.f2.action = "";
	document.f2.submit();
}

function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}

function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}

</script>
