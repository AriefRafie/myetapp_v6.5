  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>
    		

<fieldset> <legend>MAKLUMAT CARIAN</legend>
<!--<form name="f1" method="post"> -->
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    	<tr>			  
			<td colspan="2" align="center">&nbsp;</td>
		</tr>
    
      <tr >
        <td align="right" width="40%">Nama Fail : &nbsp;&nbsp;</td>
        <td><input name="NamaFail" type="text" size="43" maxlength="80" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td align="right" width="40%">No. Fail : &nbsp;&nbsp;</td>
        <td><input name="NoFail" type="text" size="43" maxlength="43" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr >
        <td height="25"  align="right">Negeri : &nbsp;&nbsp;</td>
        <td>$selectNegeri</td>
      </tr>
      <tr>
        <td></td>
        <td><input name="cari" value="Cari" type="button" onclick="javascript:search_data()">
        <input value="Kosongkan" onclick="javascript:cancel()" type="button"></td>
      </tr>
  </table>
</fieldset>
		</td>
	</tr>
		
	<tr>
		<td>

			<fieldset><legend>SENARAI FAIL</legend>
				#parse("app/utils/record_paging.jsp")
			
				<table border="0" width="100%">
			  		<tr class="table_header">
			        	<td width="5%"><b>BIL.</b></td>
			        	<td width="20%"><b>NO FAIL</b></td>
			        	<td width="39%"><b>TAJUK</b></td>
			        	<td width="13%"><b>NEGERI</b></td>
			        	<td width="23%"><b>STATUS</b></td>
			      	</tr>	
			  	#set ($count = 0)
			    #foreach ( $fail in $SenaraiFail )
			    	#set ($count = $count+1)
			        #set( $i = $velocityCount )
			        #if ( ($i % 2) != 1 )
			        	#set( $row = "row2" )
			       	#else
			        	#set( $row = "row1" )
			       	#end
			      	<tr>
				        <td class="$row" scope="row">$count.</td>
				        <td class="$row"><a href="javascript:seterusnya('$fail.idpermohonan', '$fail.nofail')" class="style1">$fail.nofail</a></td>
				        <td class="$row">$fail.tajuk</td>
				        <td class="$row">$fail.negeri</td>
				        <td class="$row">$fail.keterangan</td>
			      	</tr>
			      #end
			      
			      #if ($count == 0)
			      	<tr> 
			        	<td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
			      	</tr>
			      #end
			  	</table>
			
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
	document.f1.command.value = "";
	//document.f1.nama_fail.value = key;
	document.f1.action = "";
	document.f1.submit();
}
//1st 
function seterusnya_lama(id, no) {
	document.f2.idpermohonan.value = id;
	document.f2.noFail.value = no;
	document.f2.mode.value = "view";
	document.f2.command.value = "surat";
	document.f2.action = "";
	document.f2.submit();
}

function seterusnya(id, no) {
	document.${formName}.idpermohonan.value = id;
	document.${formName}.noFail.value = no;
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("surat");		
}

// 2010/02/07 -Pilih Pegawai untuk tandatangan surat
function openSuratPegawai(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

//2010/04/11 bertujuan mencetak doket atau Fail
//Diambil dari page(jsp) frmSenaraiFailUntukSurat.jsp
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

	// 2010/09/27 -Pilih Pegawai untuk tandatangan surat [CUKAI]
	function openSuratPegawaiCukai(urli,param,laporan,tem){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	}
	
		// 2010/09/27 -Pilih Pegawai untuk tandatangan surat [CUKAI]
	function openSuratPegawaiNegeri(urli,param,laporan,tem){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawainegeri&urli="+urli+"&"+param+tem+"&flagReport=S&peringkat="+laporan;
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	}

</script>
<script language="JavaScript"> 
	//document.forms[0].NamaFail.focus(); 
	#if ($Negeri == 20)
		//document.forms[0].socNegeri.value = "";
	#end
</script>