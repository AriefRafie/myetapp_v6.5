<style type="text/css">
<!--
.labelmandatory {color: #FF0000}
-->

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
<p>
   <input name="idpermohonan" type="hidden" id="idpermohonan" value="$permohonanInfo.idpermohonan"/>
   <input name="template" type="hidden" id="template" value="$permohonanInfo.template"/>
   <input name="pegawai" type="hidden" id="pegawai" value="$permohonanInfo.namapegawai"/>
   <input name="jawatan" type="hidden" id="jawatan" value="$permohonanInfo.jawatan"/>
   <input name="notelefon" type="hidden" id="notelefon" value="$permohonanInfo.notelefon"/>
   <input name="email" type="hidden" id="email" value="$permohonanInfo.email"/>
   <input name="peringkat" type="hidden" value="$!peringkat"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>&nbsp;</td>
	</tr>
  <tr>
    <td> ##$!peringkat 
    	<fieldset><legend><strong>Cetakan Surat</strong></legend>
        	<table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="29%">&nbsp;</td>
                <td width="70%">&nbsp;</td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="29%">&nbsp;&nbsp; No. Fail</td>
                <td width="70%">: $permohonanInfo.fail.toUpperCase()</td>
              </tr>
              ##if($flagReport == 'S')
              <tr>
                <td width="1%"><span class="labelmandatory">*</span></td>
                <td width="29%">Bil. Dokumen</td>
                <td width="70%">: <input name="txtBilDokumen" type="text" value="$bilDokumen" size="3" maxlength="3" onblur="validateNumber(this,this.value);"></td>
              </tr>
              ##end
              <tr>            	
                <td width="1%"><span class="labelmandatory">*</span></td>
                <td width="29%">Nama Pegawai</td>
                <td width="70%">: $selectNamaPegawai</td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
              	##if($peringkat == 'negeri')
              	  <td width="29%"><span class="$style style1"></span> Rujukan Surat</td>
              	##end  
              	  <td width="70%">: <input type="text" name="up" size="36" ></td>
              </tr>
              <!--<tr>
              	  <td><span class="$style style1"></span> Maklumat Bebanan</td>
              	  <td>: --> 
              	  <input type="hidden" name="beban" size="36" value="$peringkat"><!--</td>
              </tr>-->
              #if($peringkat == 'negeri' || $peringkat == 'cukaimaklum')            	
              <tr>
              	<td width="1%">&nbsp;</td>
              	  <td width="29%"><span class="$style style1"></span> Tahun Cukai</td>
              	  <td width="70%">: <input type="text" name="tahun" maxlength="4" onkeyup= "RemoveNonNumeric(this)" size="3" ></td>
              </tr>
              #end
               <tr>
              	  <td colspan="3">&nbsp;</td>
              </tr>
              
              <tr>
                <!-- <td>&nbsp;</td> -->
                <td colspan="3" align="center">
                 <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:cetakSurat('$permohonanInfo.idpermohonan','$permohonanInfo.template','$permohonanInfo.namapegawai','$permohonanInfo.jawatan','$permohonanInfo.notelefon','$permohonanInfo.email')">
                 <!--<a href="javascript:cetakSurat('$permohonanInfo.idpermohonan','$permohonanInfo.template','$permohonanInfo.namapegawai','$permohonanInfo.jawatan','$permohonanInfo.notelefon','$permohonanInfo.email')">CETAK</a>
                 --><input type="button" name="cmdKeluar" id="cmdKeluar" value="Keluar" onclick="javascript:keluar()">
                </td>
              </tr>
            </table>
        </fieldset>
    </td>
  </tr>
</table>


<script>
//frmPilihPegawai

function keluar() {
	window.close();
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	} else {
		if (content.length < 2){
			document.${formName}.txtBilDokumen.value = "0" + content;
		}
	}
}

function RemoveNonNumeric( strString ){
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function doChangePegawai(){
	if(document.${formName}.idpegawai.value==0)
		return;
		
	doAjaxCall${formName}("PilihPegawai");
}

function doChangePegawaiNegeri(){
	if(document.${formName}.idpegawai.value==0)
		return;
		
	doAjaxCall${formName}("pegawainegeri");
	
}

function cetakSurat(id,temp,pg,jwt,tel,email) {
	if(document.${formName}.txtBilDokumen.value == ""){
		alert('Sila masukkan Bil. Dokumen.');
  		document.${formName}.txtBilDokumen.focus();
		return; 
	}
	if(document.${formName}.idpegawai.value == "0"){
		alert('Sila pilih pegawai.');
  		document.${formName}.idpegawai.focus(); 
		return; 
	}

	var param ="&pegawai="+pg+"&jawatan="+jwt+"&notelefon="+tel+"&email="+email+"&beban="+document.${formName}.beban.value+"&up="+document.${formName}.up.value+"&tahun="+document.${formName}.tahun.value;
    var url = "../../servlet/ekptg.report.htp.SuratHTP?bil="+document.${formName}.txtBilDokumen.value+"&idpermohonan="+id+"&template="+temp+param;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>


