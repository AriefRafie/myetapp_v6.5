
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
<script type="text/javascript" src="../../library/js/jquery-1.8.0.js"></script>

<link rel="stylesheet" type="text/css" href="../../css/eTapp_HTP.css" />
   <input type="hidden" name="report" id="report" value="$!report"/>
   <input type="hidden" name="idpermohonan" id="idpermohonan" value="$!idpermohonan"/>   
   <input type="hidden" name="namaPegawai" id="namaPegawai" value="$!namaPegawai"/>
   <input type="hidden" name="idJawatan" id="idJawatan" value="$!idJawatan"/> 
   <input type="hidden" name="jawatanPegawai" id="jawatanPegawai" value="$!jawatanPegawai"/>
   <input type="hidden" name="emelPegawai" id="emelPegawai" value="$!emelPegawai"/> 
   <input type="hidden" name="userid" id="userid" value='$!{session.getAttribute("_ekptg_user_id")}'/> 
   
    	<fieldset><legend><strong>Maklumat Emel</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
        	
              <tr>
              	<td width="1%">&nbsp;</td>
                <td width="20%">&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td width="78%">&nbsp;</td>
              </tr>
             <tr>
              	<td width="1%">&nbsp;</td>
                <td width="20%">Perkara</td>
                <td width="1%">:</td>
                <td width="78%">$!tajukEmel</td>
              </tr>             
              <tr>
               <td width="1%">&nbsp;</td>
                <td width="20%" valign="top">Kandungan</td>
                <td width="1%" valign="top">:</td>
                <td width="78%">
                	<textarea name="txtkandungan" cols="100" rows="25" class="" id="txtkandungan" >$!kandungan</textarea>  							
                				<script>
								var area = new FCKeditor("txtkandungan");
								area.BasePath = '../../${appname}/library/fck/' ;
								area.ToolbarSet = 'Pengumuman';
								area.Height = 300;
								area.Width = 450;
								area.ReplaceTextarea();            	
							</script>
							<div id='word_count'></div>  							
            
                </td>
              </tr>
              <tr> 
		        <td colspan="4">&nbsp;</td>

		      </tr> 
        
        <table align="center" width="70%" border="0" cellspacing="2" cellpadding="2">
        	<tr align="center">
                <td>
                	#set ($labelCetak = 'Hantar Emel')
            		#if($report == 'emelperingatan')
                		<input type="button" name="cmdCetak" id="cmdCetak" value="$!labelCetak" onclick="javascript:emelPeringatanBayaran()">
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

	function emelPeringatanBayaran() {
		input_box = confirm("Emel Peringatan Bayaran Lewat Akan Dihantar Kepada Pemajak");
		if (input_box == true) {
			document.${formName}.command.value = "peringatanbayaran";
			document.${formName}.action = "?_portal_module=ekptg.view.htp.utiliti.FrmPopupEmel"; 
			document.${formName}.submit();
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
			
			var url = "../../servlet/ekptg.report.htp.SuratKepadaKJPPajakan?"+params;
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

	function keluar() {
		window.close();
	}	
</script>
