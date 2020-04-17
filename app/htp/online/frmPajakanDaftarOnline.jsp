<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<p>&nbsp;</p>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  
  <input name="actionPajakan" type="hidden" id="actionPajakan" value="$actionPajakan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="idFail" type="text" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input nama="idpemohon" type="hidden" id="idpemohon"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
	<td colspan ='2'>
	<fieldset>
    	<legend><strong>MAKLUMAT PEMOHON</strong></legend>
    	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    	<tr>
    		<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">Kategori Pemohon</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("kategoriPemohon")</td>
		</tr>
		<tr>
    		<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">Nama</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("namaPemohon")</td>
		</tr>
		<tr>
    		<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">MyID/MyCoID</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("noPengenalan")</td>
		</tr>
		<tr>
    		<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">Alamat</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("alamat1")<br/>$!pemohon.get("alamat2")<br/>$!pemohon.get("alamat3")</td>
		</tr>
		<tr>
    		<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">Poskod</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("poskod")</td>
		</tr>
		<tr>
    		<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td width="28%">Negeri</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("negeri")</td>
		</tr>
		<tr>
    		<td width="1%"></td>
            <td width="28%">Bandar</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("bandar")</td>
		</tr>
		<tr>
    		<td width="1%"></td>
            <td width="28%">No. Telefon</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("noTel")</td>
		</tr>
		<tr>
    		<td width="1%"></td>
            <td width="28%">No. Fax</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("noFax")</td>
		</tr>
		<tr>
    		<td width="1%"></td>
            <td width="28%">Emel</td>
            <td width="1%">:</td>
            <td width="70%">$!pemohon.get("emel")</td>
		</tr>
    	</table>	
    </fieldset>
    </td>
	</tr>
	<tr>
    	<td colspan="2">
    	<fieldset>
    		<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
    
   			<table width="100%" border="0" cellspacing="2" cellpadding="2">
       		#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
         	<tr>
          		<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
            	<td width="28%">Negeri</td>
            	<td width="1%">:</td>
            	<td width="70%">$selectNegeri</td>
         	</tr>
         	<tr>
          		<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            	<td>Kementerian</td>
            	<td>:</td>
            	<td>$selectKementerian</td>
         	</tr>
         	<tr>
	         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            	<td>Agensi</td>
            	<td>:</td>
            	<td>$selectAgensi</td>
         	</tr>
         	<tr>
	         	<td>&nbsp;</td>
            	<td>Urusan</td>
            	<td>:</td>
            	<td>882 - PAJAKAN TANAH</td>
         	</tr>
         	<tr>
         		<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            	<td>Sub Urusan</td>
            	<td>:</td>
            	<td>$selectSuburusan</td>
         	</tr>
         	<tr>
         		<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            	<td>Status Tanah</td>
            	<td>:</td>
            	<td>$selectStatusTanah</td>
         	</tr>
         	<tr>
         		<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
         		<td>Jenis Fail</td>
            	<td>:</td>
            	<td>$selectJenisFail</td>
         	</tr>
         	<!-- TUTUP 09042020-->
        	<!-- 
         	<tr>
         		<td width="1%">&nbsp;</td>
            	<td width="28%">No. Fail Jabatan</td>
            	<td width="1%">:</td>
            	<td width="70%"><input type="text" name="txtNoFail" id="txtNoFail" disabled="disabled" class="disabled" value="$beanMaklumatPermohonan.noFail"/></td>
         	</tr>
         	-->
         	<!-- TUTUP 09042020 -->
         	<!--
         	<tr>
	         	<td>
            	<!--	#if ($mode != 'view')<span class="style1">*</span>#end 
         		</td>
            	<td>No. Fail KJP</td>
            	<td>:</td>
            	<td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.noFailKJP" onblur="this.value=this.value.toUpperCase();" /></td>
         	</tr>
         	-->
         	<tr>
	         	<td>
            	<!--	#if ($mode != 'view')<span class="style1">*</span>#end --> 
	         	</td>
    	        <td valign="top">Tarikh Surat KJP</td>
        	    <td>:</td>
	            <td><input type="text" size="11" maxlength="10" name="tarikhSuratKJP" id="tarikhSuratKJP" onblur="check_date(this)" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.tarikhSuratKJP"/>
    	        #if ($mode != 'view')
        	    <a href="javascript:displayDatePicker('tarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            	#end            </td>
         </tr>	
         <tr>
         <td style="visibility:hidden">#if ($mode != 'view')<span class="style1">*</span>#end </td>
         <td>No. Fail Lain /  Pemohon</td>
         <td>:</td>
         <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$beanMaklumatPermohonan.noFailLain" onblur="this.value=this.value.toUpperCase();"/></td>
         </tr>
         <tr>
          <td>
            	<!--	#if ($mode != 'view')<span class="style1">*</span>#end --> 
          	</td>
        	<td>Tarikh Surat Pemohon</td>
            <td>:</td>
            <td>            	
            	<input type="text" size="11" maxlength="10" name="tarikhSuratPemohon" class="$classDis" id="tarikhSuratPemohon" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratPemohon" readonly="readonly" $readOnly/>
				#if ($mode != 'view') 
					<a href="javascript:displayDatePicker('tarikhSuratPemohon',false,'dmy');"><img src="../img/calendar.gif" alt="Calendar" border="0"/> 
				#end 
			</td>         
         </tr>
         <tr style="display:none">
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td valign="top">Tarikh Agihan</td>
            <td>:</td>
            <td><input type="text" name="tarikhAgihan" id="tarikhAgihan" onblur="check_date(this)" size="9" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.tarikhAgihan"/>
            #if ($mode != 'view')
            <a href="javascript:displayDatePicker('tarikhAgihan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            #end            
            </td>
         </tr>
         <tr>
         	<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
           <td valign="top">Tajuk</td>
            <td valign="top">:</td>
            <td valign="top"><textarea name="txtTajuk" id="txtTajuk" rows="5" cols="41" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea></td>
         </tr>
         #end
        </table>
    </fieldset>    
    </td>
  </tr>
<!--  <tr>
    <td colspan="2">&nbsp;</td>
  </tr> -->
  	#if ($mode != 'view')
  	<tr>
    	<td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
 	</tr>
  	#end
	
	<tr>
    	<td width="30%">&nbsp;</td>
    	<td width="70%">
    	#if ($mode == 'new')
    		<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
    		<input type="button" class="stylobutton" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
    	#end
    	#if($mode == 'view')
    		<input type="button" class="stylobutton" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
    	#end
    	</td>
	</tr>
</table>

<script>
	
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
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Sub Urusan.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
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
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan Tajuk.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "daftarBaru";
		return;
	}
	
	document.${formName}.hitButton.value = "simpan";
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.mode.value = "view";	
	document.${formName}.submit();
	//goToNext();
}

function kembali() {	
	document.${formName}.actionPajakan.value = "";
	document.${formName}.submit();
}

function seterusnya(){
	document.${formName}.action = "$EkptgUtil.getTabID("Harta Tanah Persekutuan",$portal_role)?_portal_module=ekptg.view.htp.online.FrmOnlineMaklumatPajakanView";	
	document.${formName}.submit();
}
</script>
