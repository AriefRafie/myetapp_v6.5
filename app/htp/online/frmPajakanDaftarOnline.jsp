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
  	<input name="idStatusTanah" type="hidden" id="idStatusTanah" value="$idStatusTanah"/> 
  	<input name="idJenisTanah" type="hidden" id="idJenisTanah" value="$idJenisTanah"/> 
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

		    <tr>
         		<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            	<td>Status Tanah</td>
            	<td>:</td>
            	<td>$selectStatusTanah</td>
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
   
	
  ##if($idJenisPermohonan == '1')
	<!-- MAKLUMAT TANAH -->
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT TANAH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
		
		#if($idJenisTanah == '2' || $idJenisTanah == '4')
		
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
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Daerah</td>
          <td width="1%">:</td>
          <td width="70%">
          	$selectDaerah
          </td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Mukim</td>
          <td width="1%">:</td>
          <td width="70%">
          	$selectMukim
          </td>
        </tr>
        #if($idJenisTanah == '4')
		<tr>
          <td width="1%"><span class="style1">*</span></td>
          <td>Jenis Hakmilik</td>
          <td>:</td>
          <td>$selectJenisHakmilik</td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">No. Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%">
          <input type="text" name="noMilikTanah" id="noMilikTanah" value="$beanMaklumatTanah.noHakmilik"/></td>
        </tr>
        #end
        #if($idJenisTanah == '2')
        <tr>
        	<td width="1%"><span class="style1">*</span></td>
        	<td width="28%">No. Warta</td>
        	<td width="1%">:</td>
        	<td width="70%">
          		<input type="text" name="noWartaTanah" id="noWartaTanah" value="$beanMaklumatTanah.noWarta"/>
          	</td>
        </tr>
        <tr>
        	<td width="1%"><span class="style1">*</span></td>
        	<td width="28%">Tarikh Warta</td>
        	<td width="1%">:</td>
        	<td width="70%">
          		<input type="text" name="tarikhWartaTanah" id="tarikhWartaTanah" size="9" readonly="readonly" class="disabled" value="$beanMaklumatTanah.tarikhWarta"/><a
							href="javascript:displayDatePicker('tarikhWartaTanah',false,'dmy');"> <img
								border="0" src="../img/calendar.gif" /></a>
          	</td>
        </tr>
        #end
        <tr>
          <td><span class="style1">*</span></td>
          <td>Jenis Lot</td>
          <td>:</td>
          <td>$selectJenisLot</td>
        </tr>
        
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">No. Lot</td>
          <td width="1%">:</td>
          <td width="70%">
          <input type="text" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.noLot"/></td>
        </tr>

        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td>Unit Luas Ambil</td>
          <td>:</td>
          <td>
          	<select name="socLuas" style="width:200px;" $readonlyPopup class="$disabledPopup" $disabledPopup onchange="javascript:doChangeLuas(this.value)">  
			#set ($listUnitLuas = ["SILA PILIH",
					       "KM - KILOMETER PERSEGI",
					       "H - HEKTAR",
					       "M - METER PERSEGI",
					       "E - EKAR,ROOD,POLE",
					       "K - KAKI PERSEGI",
					       "P - EKAR PERPULUHAN",
					       "D - EKAR,DEPA",
					       "R - RELONG,JEMBA,KAKI PERSEGI",
					       "BN - BATU NAUTIKA"]
			      )
			#set( $counter = 0 )
			#foreach ($i in $listUnitLuas)
			
			#if ($idLuas == $counter) 
              <option selected value="$counter">$i</option>
            #else
			  <option value="$counter">$i</option>
            #end

			#set ($counter = $counter+1)

			#end
			</select>          
          </td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Luas Ambil</td>
          <td>:</td>
          <td>#if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
            <input type="text" name="txtLuas1" id="txtLuas1" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonlyPopup class="$inputTextClassPopup"/ >
            #elseif ($idLuas == '7')
            <input type="text" name="txtLuas1" id="txtLuas1" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuas2" id="txtLuas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"/ $readonlyPopup class="$inputTextClassPopup">
            #elseif ($idLuas == '8' || $idLuas == '4')
            <input type="text" name="txtLuas1" id="txtLuas1" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuas2" id="txtLuas2" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuas3" id="txtLuas3" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonlypopup class="$inputTextClassPopup"/>
          </td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>Luas Bersamaan</td>
          <td>:</td>
          <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" readonly="readonly" class="disabled"/>
            HEKTAR</td>
        </tr>
        #end
        #end
  		</tr>
  		
  		<!-- MAKLUMAT BORANG K -->
  		#if ($idJenisTanah == '3')
  		#foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
		<tr>
			<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			<td width="28%">Pegangan Hakmilik</td>
			<td width="1%">:</td>
			<td width="70%">
				#if ($mode == 'new') 
					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" 
					value="$beanMaklumatBorangK.peganganHakmilik" onblur="doChangePeganganHakmilikBorangK();" />
					<!--<input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Borang K" onclick="pilihBorangK()" />--> 
				#else 
					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik"
					readonly="readonly" class="disabled" /> 
				#end 
					<span class="style1">$errorPeganganHakmilik</span>
			</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">No. Lot</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.lot</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Luas Lot</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.luas 
				<input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatBorangK.idLuas"> 
				<input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatBorangK.luasBersamaan">
			</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">No. Hakmilik</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.hakmilik</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Mukim</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.mukim</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Daerah</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.daerah</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Negeri</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.negeri <input type="hidden" name="idNegeriTanah" id="idNegeriTanah"
				value="$beanMaklumatBorangK.idNegeri">
			</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Kementerian</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.kementerian 
				<input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatBorangK.idKementerian"></td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Agensi</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.agensi</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Kegunaan Tanah</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.kegunaanTanah</td>
		</tr>
		#end
		#end
		</table>
	</fieldset></td>
  </tr> ###end
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
function doChangeStatusTanah() {
	doAjaxCall${formName}("doChangeStatusTanah");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}

function doChangeMukim() {
	doAjaxCall${formName}("doChangeMukim");
}

function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
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

function doChangeJenisTanah() {
	doAjaxCall${formName}("doChangeJenisTanah");
}

function kiraLuas(idLuas){
	var jenisLuas = idLuas;
  
  	// KILOMETER PERSEGI
  	if(jenisLuas == "1"){

		var luasK = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasK = document.${formName}.txtLuas1.value*1;
		}
		var luasH = luasK*100;		
		document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "2"){ //HEKTER
  		
		var luasH = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasH = document.${formName}.txtLuas1.value*1;
		}
		document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "3"){ // METER PERSEGI
    	
		var luasM = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasM = document.${formName}.txtLuas1.value*1;
		}
  	  	var luasH = (luasM*0.0001);
	  	document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

	  	var luasE = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasE = document.${formName}.txtLuas1.value*1;
		}
	  	var luasR = 0;
		if (document.${formName}.txtLuas2.value != ''){
			luasR = document.${formName}.txtLuas2.value*1;
		}
	  	var luasP = 0;
		if (document.${formName}.txtLuas3.value != ''){
			luasP = document.${formName}.txtLuas3.value*1;
		}
	  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  	document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "5"){ //KAKI PERSEGI
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasAsal = document.${formName}.txtLuas1.value*1;
	  }
	  var luasH = luasAsal*0.0000092;
  	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "6"){	//EKAR PERPULUHAN
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasAsal = document.${formName}.txtLuas1.value*1;
	  }
	  var luasH = luasAsal*0.405;
	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
  	  
   	} else if(jenisLuas == "7"){ //EKAR,DEPA
  	  
	  var luasE = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasE = document.${formName}.txtLuas1.value*1;
	  }
	  var luasD = 0;
	  if (document.${formName}.txtLuas2.value != ''){
		  luasD = document.${formName}.txtLuas2.value*1;
	  }
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
  	  
	  var luasR = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasR = document.${formName}.txtLuas1.value*1;
	  }
	  var luasJ = 0;
	  if (document.${formName}.txtLuas2.value != ''){
		  luasJ = document.${formName}.txtLuas2.value*1;
	  }
	  var luasK = 0;
	  if (document.${formName}.txtLuas3.value != ''){
		  luasK = document.${formName}.txtLuas3.value*1;
	  }
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
	}
}
</script>
