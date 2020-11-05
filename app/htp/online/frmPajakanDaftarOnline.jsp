<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<p>&nbsp;</p>
<p>
  	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  	<input type="hidden" name="actionPajakan" id="actionPajakan" value="$actionPajakan"/>
  	<input type="hidden" name="mode" id="mode" value="$mode"/>
  	<input type="hidden" name="hitButton" id="hitButton" value="$hitButton"/>
		<input type="hidden" name="idFail" id="idFail" value="$idFail"/>
  	<input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
  	<input type="hidden" nama="idpemohon" id="idpemohon"/>
  	<input type="hidden" name="idHakmilikUrusan" id="idHakmilikUrusan" value="$idHakmilikUrusan" />
  	<input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
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
            <td width="28%" valign="top">Alamat</td>
            <td width="1%" valign="top">:</td>
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
<!--         	 <tr>
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
         	</tr>-->
<!--         	<tr>
	         	<td>
            	</td>
    	        <td valign="top">Tarikh Surat KJP</td>
        	    <td>:</td>
	            <td><input type="text" size="11" maxlength="10" name="tarikhSuratKJP" id="tarikhSuratKJP" onblur="check_date(this)" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.tarikhSuratKJP"/>
    	        #if ($mode != 'view')
        	    <a href="javascript:displayDatePicker('tarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            	#end</td>
         </tr>	-->
<!--         <tr>
         <td style="visibility:hidden">#if ($mode != 'view')<span class="style1">*</span>#end </td>
         <td>No. Fail Lain /  Pemohon</td>
         <td>:</td>
         <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$beanMaklumatPermohonan.noFailLain" onblur="this.value=this.value.toUpperCase();"/></td>
         </tr> -->
         	<tr>
			<td width="1%"><span class="style1">*</span></td>
			<td width="28%">Status Tanah</td>
			<td width="1%">:</td>
			<td width="70%">
				<select name="socJenisTanah" id="socJenisTanah" onchange="doChangeJenisTanah()"
					$inputTextClass class="$inputTextClass">
				<option $selected value="0">SILA PILIH</option>
				<option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option>
				<option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option>
				</select>
			</td>
		</tr>
         <tr>
          <td>
            #if ($mode != 'view')<span class="style1">*</span>#end 
          </td>
        	<td width="28%">Tarikh Surat Pemohon</td>
          <td width="1%">:</td>
          <td width="70%">            	
            	<input type="text" size="11" maxlength="10" name="tarikhSuratPemohon" id="tarikhSuratPemohon" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratPemohon" readonly="readonly" $readOnly/>
				#if ($mode != 'view') 
					<a href="javascript:displayDatePicker('tarikhSuratPemohon',false,'dmy');"><img src="../img/calendar.gif" alt="Calendar" border="0"/> 
				#end 
			</td>         
         </tr>
<!--        <tr style="display:none">
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td valign="top">Tarikh Agihan</td>
            <td>:</td>
            <td><input type="text" name="tarikhAgihan" id="tarikhAgihan" onblur="check_date(this)" size="9" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.tarikhAgihan"/>
            #if ($mode != 'view')
            <a href="javascript:displayDatePicker('tarikhAgihan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            #end            
            </td>
         </tr> -->
         <tr>
         	<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
           <td valign="top">Tujuan Pajakan</td>
            <td valign="top">:</td>
            <td valign="top"><textarea name="txtTajuk" id="txtTajuk" rows="5" cols="41" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea>
            </td>
         </tr>
         #end
        </table>
    </fieldset>    
    </td>
  </tr>
   
  <tr>
    	<td colspan="2">
			<fieldset>
    			<legend><strong>MAKLUMAT TANAH</strong></legend>
    			#foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
    			<input type="hidden" name="idHakmilik" id="idHakmilik" value="$beanMaklumatTanah.idHakmilik" />
    			
    			<table width="100%" border="0" cellspacing="2" cellpadding="2">
<!--        			<tr> -->
<!--          				<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td> -->
<!--          				<td>No. Lot</td> -->
<!--          				<td>:</td>-->
<!--          				<td> -->
<!--          					#if ($mode == 'new')-->
<!--          						<input type="text" nam   e="txtnoLot" id="txtnoLot" value="$beanMaklumatTanah.noLot">-->
<!--          					#else -->
<!--          						<input type="text" name="txtnoLot" id="txtnoLot" value="$beanMaklumatTanah.noLot" readonly="readonly" class="disabled">-->
<!--          					#end  -->
          						<input type="hidden" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.lot" /></td>-->
<!--        			</tr>-->
        			
<!--        			<tr>-->
<!--          				<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>-->
<!--          				<td>No. Hakmilik</td>-->
<!--          				<td>:</td>-->
<!--          				<td><input type="text" name="txtnoHakmilik" id="txtnoHakmilik" value="$beanMaklumatTanah.noHakmilik" onblur="doChangePeganganHakmilik();">-->
<!--          					<input type="hidden" name="noMilikTanah" id="noMilikTanah" value="$beanMaklumatTanah.hakmilik" /><span class="style1">$errorPeganganHakmilik</span> </td>-->
<!--        			</tr>-->
<!--        			<tr>
        				<td width="1%"></td>
          				<td width="28%">Pegangan Hakmilik</td>
          				<td width="1%">:</td>
          				<td width="70%"> $beanMaklumatTanah.peganganHakmilik
          						#if ($mode == 'new')
            					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();">
            					<input class="stylobutton100" name="cmdDaftar" type="button" value="Carian Tanah" onClick="pilihTanah('$idPermohonan')">
          						#else
            					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled">
          						#end
            					<input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi">
            					<span class="style1">$errorPeganganHakmilik</span> 
            					<span class="style4"><i><font color="#ff0000">Contoh</font> : </i><span class="style5">160140GRN00000576</span></span>
            				
            				</td>
        			</tr>-->
        				#if($idJenisTanah == '1' || $idJenisTanah == '2')
						#foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
						<tr>
				          <td width="1%"><span class="style1">*</span></td>
				          <td width="28%">Negeri</td>
				          <td width="1%">:</td>
				          <td width="70%">
				            $selectNegeri
				          </td>
				        </tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Luas Lot</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.luasLot
          						<input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatTanah.idLuas" /> 
          						<input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatTanah.luasBersamaan" /></td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
			          		<td>Mukim</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.mukim
          						<input type="hidden" name="namaMukimTanah" id="namaMukimTanah" value="$beanMaklumatTanah.mukim" /></td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Daerah</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.daerah
          						<input type="hidden" name="namaDerahTanah" id="namaDerahTanah" value="$beanMaklumatTanah.daerah" /></td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Negeri</td>
          					<td>:</td>			
          					<td>$beanMaklumatTanah.negeri
            					<input type="hidden" name="idNegeriTanah" id="idNegeriTanah" value="$beanMaklumatTanah.idNegeriTanah">
            					<input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$$beanMaklumatTanah.negeri">
          					</td>
        				</tr>
        				<!--<tr>
          					<td>&nbsp;</td>
          					<td>Kementerian</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.kementerian
            					<input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatTanah.idKementerian">
            					<input type="hidden" name="kodKementerian" id="kodKementerian" value="$beanMaklumatTanah.kodKementerian">
          					</td>
        				</tr>
        				<tr>
          					<td>&nbsp;</td>
          					<td>Agensi</td>
          					<td>:</td>
          					<td>$beanMaklumatTanah.agensi
          						<input type="hidden" name="idAgensiTanah" id="idAgensiTanah" value="$beanMaklumatTanah.idAgensi">
        				</tr>-->
        				
              			<tr>
                           	<td></td>
                        	<td valign="top">&nbsp;</td>
                           	<td colspan="2" valign="top">
                       		#if ($mode == 'view')
                				<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniTanah()"/>
                				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:doBackList()"/>
               				#elseif ($mode == 'update')
                				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanKemaskiniTanah()"/>
                				<input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" $hide/>
                				<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalKemaskiniTanah()"/>
           				 	#end</td>
                        </tr>
					</table>
                    #end
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
	<td align="center">
    	<!-- <td width="30%">&nbsp;</td>
    	<td width="70%"> -->
    	#if ($mode == 'new')
    		<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
    		<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
    	#end
    	#if($mode == 'view')
    	#end
    	</td>
	</tr>
	
	
</table>

<script>
	
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}

function simpan() {
	/*
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
	}*/
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan Tujuan.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "daftarBaru";
		return;
	}
	
	document.${formName}.hitButton.value = "simpan";
	document.${formName}.actionPajakan.value = "paparMaklumatPajakan";
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

function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
</script>
