
<style type="text/css">
<!--

body {
text-align:center;
font-family:serif;
background:#FAF8CC;
}

.style1 {color: #0000FF}

.table_header {
color:#FFF;
background-color:#960;
font-weight:400;
border-style:10px solid #FFF;
}

.module_content,td.row1,.row1 {
background-color:#FAF8CC;
}

.table_row2,.module_content_bg {
background-color:#F2E3E9;
}

td.row2,td.selected,.row2 {
background-color:#ebbc5d;
}

.stylobutton {
width:140px;
}

input.button {
border:0.05em solid;
cursor:pointer;
font-weight:700 !important;
overflow:visible;
width:103px;
padding:0 0.25em;
}
-->
</style>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_HTP.css" />
   <input type="hidden" name="report" id="report" value="$!report"/>
   <input type="hidden" name="idpermohonan" id="idpermohonan" value="$!idpermohonan"/>
   <input type="hidden" name="namaPegawai" id="namaPegawai" value="$!namaPegawai"/>
   <input type="hidden" name="idJawatan" id="idJawatan" value="$!idJawatan"/>
   <input type="hidden" name="jawatanPegawai" id="jawatanPegawai" value="$!jawatanPegawai"/>
   <input type="hidden" name="emelPegawai" id="emelPegawai" value="$!emelPegawai"/>
   <input type="hidden" name="userid" id="userid" value='$!{session.getAttribute("_ekptg_user_id")}'/>

    	<fieldset><legend><strong>Pilihan Pegawai</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">

              <tr>
              	<td width="1%">&nbsp;</td>
                <td width="30%">&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td width="68%">&nbsp;</td>
              </tr>

              <tr>
                <td><font color="red">*</font></td>
                <td>Nama Pegawai</td>
                <td>:</td>
                <td>$!selectPenolongDanPengarah</td>
              </tr>
    		#if($report == 'SuratKepadaPemohonPajakan'
    			|| $report == 'HTPajakanSuratJKPTGNegeri'
    			|| $report == 'HTPajakanSuratBayaran'
    			|| $report == 'HTPajakanSuratBayaranLewat'
    			)
              <tr>
		        <td>&nbsp;</td>
		        <td scope="row" align="left">&nbsp;Tarikh Hantar</td>
		        <td>:&nbsp;</td>
		        <td>
		       		<label>
		      			<input name="txdMula" type="text" id="txdMula" value="$!txdMula" size="10" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdMula);" />
		        	</label>
		        	<a href="javascript:displayDatePicker('txdMula',false,'dmy');">
		        		<img border="0" src="../../img/calendar.gif"/>
				 		<span class="pautanms" class="stylefont">dd/mm/yyyy</span>
      				</a> <!--
		        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;
		     		<label>
		      			<input name="txdAkhir" type="text" id="txdAkhir" value="$!txdAkhir" size="10" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdAkhir);"/>
		        	</label>
		        	<a href="javascript:displayDatePicker('txdAkhir',false,'dmy');">
		        		<img border="0" src="../img/calendar.gif"/>
				 		<span class="pautanms">dd/mm/yyyy</span>
		        	</a>
				 --></td>
		      </tr>
     		#end

        <table align="center" width="70%" border="0" cellspacing="2" cellpadding="2">
        	<tr align="center">
                <td>
                	#if($report == 'HTPajakanSuratBayaranLewat')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Previu" onclick="javascript:cetakPeringatanBayaranLewat()">
                	#end
            		#if($report == 'HTPajakanSuratBayaran')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Previu" onclick="javascript:cetakPeringatanBayaran()">
                	#end

                	#if($report == 'SuratKepadaPemohonPajakan')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Previu" onclick="javascript:cetakSuratKepadaPemohon()">
                	#end

                	#if($report == 'SuratKepadaKJPPajakan')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Previu" onclick="javascript:cetakSuratKepadaKjp()">
                	#end

                	#if($report == 'SuratKepadaKJPPH')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Previu" onclick="javascript:cetakSuratKepadaJPPH()">
                	#end

                	#if($report == 'HTPajakanSuratJKPTGNegeri')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="Previu" onclick="javascript:cetakSuratKepadaJKPTGNegeri()">
                	#end

               		<input type="button" name="cmdKeluar" id="cmdKeluar" value="Keluar" onclick="javascript:keluar()">
                </td>
              </tr>
        </table>



<!-- Untuk borang dan surat -->
<script>

	function cetakPeringatanBayaranLewat() {	
		var idpermohonan = document.${formName}.idpermohonan.value;
		var namaPegawai = document.${formName}.namaPegawai.value;
		var idJawatan = document.${formName}.idJawatan.value;
		var jawatanPegawai = document.${formName}.jawatanPegawai.value;
		var emelPegawai = document.${formName}.emelPegawai.value;
		var tarikh = document.${formName}.txdMula.value;
		var userid = document.${formName}.userid.value;
		var params = "tarikh="+tarikh+"&userid="+userid;

		if(document.${formName}.socPenolongDanPengarah.value == ""){
			alert('Sila pilih Nama Pegawai terlebih dahulu.');
	  		document.${formName}.socPenolongDanPengarah.focus();
			return;

		}else if(tarikh == ""){
			alert('Sila pilih Tarikh Hantar terlebih dahulu.');
		  	document.${formName}.txdMula.focus();
			return;

		}else{
			params += "&idpermohonan="+idpermohonan+"&namaPegawai="+namaPegawai+"&idJawatan="+idJawatan+"&jawatanPegawai="+jawatanPegawai+"&emelPegawai="+emelPegawai;

			var url = "../../servlet/ekptg.report.htp.SuratPajakanPeringatanBayarLewat?"+params;
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();

		}

	}

	function cetakPeringatanBayaran() {
		var idpermohonan = document.${formName}.idpermohonan.value;
		var namaPegawai = document.${formName}.namaPegawai.value;
		var idJawatan = document.${formName}.idJawatan.value;
		var jawatanPegawai = document.${formName}.jawatanPegawai.value;
		var emelPegawai = document.${formName}.emelPegawai.value;
		var tarikh = document.${formName}.txdMula.value;
		var userid = document.${formName}.userid.value;
		var params = "tarikh="+tarikh+"&userid="+userid;

		if(document.${formName}.socPenolongDanPengarah.value == ""){
			alert('Sila pilih Nama Pegawai terlebih dahulu.');
	  		document.${formName}.socPenolongDanPengarah.focus();
			return;

		}else if(tarikh == ""){
			alert('Sila pilih Tarikh Hantar terlebih dahulu.');
		  	document.${formName}.txdMula.focus();
			return;

		}else{
			params += "&idpermohonan="+idpermohonan+"&namaPegawai="+namaPegawai+"&idJawatan="+idJawatan+"&jawatanPegawai="+jawatanPegawai+"&emelPegawai="+emelPegawai;

			var url = "../../servlet/ekptg.report.htp.SuratPajakanPeringatanBayarKepadaPemohon?"+params;
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();

		}

	}
	function cetakSuratKepadaPemohon() {
		var idpermohonan = document.${formName}.idpermohonan.value;
		var namaPegawai = document.${formName}.namaPegawai.value;
		var idJawatan = document.${formName}.idJawatan.value;
		var jawatanPegawai = document.${formName}.jawatanPegawai.value;
		var emelPegawai = document.${formName}.emelPegawai.value;
		var tarikh = document.${formName}.txdMula.value;
		var userid = document.${formName}.userid.value;
		var params = "tarikh="+tarikh+"&userid="+userid;

		if(document.${formName}.socPenolongDanPengarah.value == ""){
			alert('Sila pilih Nama Pegawai terlebih dahulu.');
	  		document.${formName}.socPenolongDanPengarah.focus();
			return;

		}else if(tarikh == ""){
			alert('Sila pilih Tarikh Hantar terlebih dahulu.');
		  	document.${formName}.txdMula.focus();
			return;

		}else{
			params += "&idpermohonan="+idpermohonan+"&namaPegawai="+namaPegawai+"&idJawatan="+idJawatan+"&jawatanPegawai="+jawatanPegawai+"&emelPegawai="+emelPegawai;

			var url = "../../servlet/ekptg.report.htp.SuratPajakanKepadaPemohon?"+params;
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();

		}

	}

	function cetakSuratKepadaKjp(){
		var idpermohonan = document.${formName}.idpermohonan.value;
		var namaPegawai = document.${formName}.namaPegawai.value;
		var idJawatan = document.${formName}.idJawatan.value;
		var jawatanPegawai = document.${formName}.jawatanPegawai.value;
		var emelPegawai = document.${formName}.emelPegawai.value;
		var params = "";

		if(document.${formName}.socPenolongDanPengarah.value == ""){
			alert('Sila pilih nama pegawai terlebih dahulu.');
	  		document.${formName}.socPenolongDanPengarah.focus();
			return;

		}else{
			params = "idpermohonan="+idpermohonan+"&namaPegawai="+namaPegawai+"&idJawatan="+idJawatan+"&jawatanPegawai="+jawatanPegawai+"&emelPegawai="+emelPegawai;

			var url = "../../servlet/ekptg.report.htp.SuratPajakanKepadaKJP?"+params;
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
		}

	}

	function cetakSuratKepadaJPPH(){
		var idpermohonan = document.${formName}.idpermohonan.value;
		var namaPegawai = document.${formName}.namaPegawai.value;
		var idJawatan = document.${formName}.idJawatan.value;
		var jawatanPegawai = document.${formName}.jawatanPegawai.value;
		var emelPegawai = document.${formName}.emelPegawai.value;
		var params = "";

		if(document.${formName}.socPenolongDanPengarah.value == ""){
			alert('Sila pilih nama pegawai terlebih dahulu.');
	  		document.${formName}.socPenolongDanPengarah.focus();
			return;

		}else{
			params = "idpermohonan="+idpermohonan+"&namaPegawai="+namaPegawai+"&idJawatan="+idJawatan+"&jawatanPegawai="+jawatanPegawai+"&emelPegawai="+emelPegawai;

			var url = "../../servlet/ekptg.report.htp.SuratPajakanKepadaJPPH?"+params;
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();

		}

	}

	function cetakSuratKepadaJKPTGNegeri(){
		var idpermohonan = document.${formName}.idpermohonan.value;
		var namaPegawai = document.${formName}.namaPegawai.value;
		var idJawatan = document.${formName}.idJawatan.value;
		var jawatanPegawai = document.${formName}.jawatanPegawai.value;
		var emelPegawai = document.${formName}.emelPegawai.value;
		var tarikh = document.${formName}.txdMula.value;
		var userid = document.${formName}.userid.value;
		var params = "tarikh="+tarikh+"&userid="+userid;

		if(document.${formName}.socPenolongDanPengarah.value == ""){
			alert('Sila pilih nama pegawai terlebih dahulu.');
	  		document.${formName}.socPenolongDanPengarah.focus();
			return;

		}else if(tarikh == ""){
			alert('Sila pilih Tarikh Hantar terlebih dahulu.');
		  	document.${formName}.txdMula.focus();
			return;

		}else{
			params += "&idpermohonan="+idpermohonan+"&namaPegawai="+namaPegawai+"&idJawatan="+idJawatan+"&jawatanPegawai="+jawatanPegawai+"&emelPegawai="+emelPegawai;

			var url = "../../servlet/ekptg.report.htp.SuratPajakanKepadaJKPTGNegeri?"+params;
		    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		    if ((document.window != null) && (!hWnd.opener))
		   	hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();

		}

	}

	function doChangePegawai() {
		document.${formName}.command.value = "doChangePegawai";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.utiliti.FrmPopupPilihPegawaiReportView";
		document.${formName}.submit();

	}

	function keluar() {
		window.close();
	}
</script>
