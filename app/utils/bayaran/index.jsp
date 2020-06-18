<!--<br/>-->
<form name="f1" method="post">
	<fieldset><legend><b>BAYARAN</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">No. Fail : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$nofail" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdSemak" id="cmdSemak" value="Semak" type="button" onclick="javascript:search_data()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan()"></td>
    		</tr>
    		
		</table>
		
	</fieldset>

##if($listSemak_size!=0 && $listSemak_size!="")	
 #if ($listSemak_size == false || $carianrekod == "tiada")
 
<br/>

	<fieldset>
	<legend><strong>MAKLUMAT FAIL</strong></legend>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
				<td colspan="4"><b>Tajuk</b></td>
			</tr>

         	<tr>
            	<td colspan="4">$keterangan_status</td>
        	</tr>	
       		<tr>
	        	<td align="right">#if($mode == 'updateBayaran' || $mode == 'newBayaran')<font color="#FF0000">*</font>#end</td>
	        	<td>Tujuan</td>
	        	<td>:</td>
	        	<td>$!selectTujuan</td>
	      	</tr>	      
      		<tr>
        		<td align="right">#if($mode == 'updateBayaran' || $mode == 'newBayaran')<font color="#FF0000">*</font>#end</td>
        		<td>Cara  Bayaran</td>
        		<td>:</td>
        		<td>$!selectCaraBayar</td>
      		</tr>
      		<tr>
	        <td>&nbsp;</td>
	        <td>No.</td>
	        <td>:</td>
	        <td><input type="text" name="txtNoBayaran" id="txtNoBayaran" size="25" value="$!NoBayaran" class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();" /></td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td>Nama Bank</td>
	        <td>:</td>
	        <td><input type="text" name="txtNamaBank" id="txtNamaBank" size="25" value="$!namaBank"  class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();" /></td>
	      </tr>
	      <tr>
	        <td align="right">&nbsp;</td>
	        <td>Tarikh Cek</td>
	        <td>&nbsp;</td>
	        <td><input type="text" name="txdTarikhCek" id="txdTarikhCek" size="10" value="$!tarikhCek"  class="$classDis" $readOnly onblur="check_date(this); checkDate(document.${formName}.txdTarikhCek);" />
	#if($mode == 'updateBayaran' || $mode == 'newBayaran') <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTarikhCek',false,'dmy');" /> #end 
				</td>
	      </tr>
	      <tr>
	        <td width="15%" align="right">&nbsp;</td>
	        <td width="31%">Tarikh Terima </td>
	        <td width="1%">:</td>
	        <td width="41%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="10" value="$!tarikhTerima"  class="$classDis" $readOnly onblur="check_date(this); checkDate(document.${formName}.txdTarikhTerima);" />
	          
	          #if($mode == 'updateBayaran' || $mode == 'newBayaran') <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');" /> 
	          
	          #end 
	      	</td>
	      </tr>
	      <tr>
	        <td align="right">#if($mode == 'updateBayaran' || $mode == 'newBayaran')<font color="#FF0000">*</font>#end</td>
	        <td>Jumlah Bayaran (RM)</td>
	        <td>:</td>
	        <td><input type="text" name="txtJumlahBayaran" id="txtJumlahBayaran" size="25" value="$!jumlahBayaran"  class="$classDis" $readOnly onblur="validateCurrency(this,this.value,'')"  /></td>
	      </tr>
	      <tr>
	        <td align="right">#if($mode == 'updateBayaran' || $mode == 'newBayaran') #end</td>
	        <td>No. Resit</td>
	        <td>:</td>
	        <td><input type="text" name="txtNoResit" id="txtNoResit" size="25" value="$!NoResit" class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();" /></td>
	      </tr>
	      <tr>
	        <td align="right">#if($mode == 'updateBayaran' || $mode == 'newBayaran') #end</td>
	        <td>Tarikh Resit</td>
	        <td>&nbsp;</td>
	        <td><input type="text" name="txdtarikhResit" id="txdtarikhResit" size="10" value="$!tarikhResit"  class="$classDis" $readOnly onblur="check_date(this);checkDate(document.${formName}.txdtarikhResit);" />
	        
	          #if($mode == 'updateBayaran' || $mode == 'newBayaran')
	           <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdtarikhResit',false,'dmy');" />
	            #end 
	            
	     	</td>
	   	</tr>
     	<tr>
        	<td align="right">#if($mode == 'updateBayaran' || $mode == 'newBayaran') #end</td>
        	<td>Tarikh Hantar Resit</td>
        	<td>:</td>
        	<td><input type="text" name="txdtarikhHantarResit" id="txdtarikhHantarResit" size="10" value="$!tarikhHantarResit"  class="$classDis" $readOnly onblur="check_date(this);checkDate(document.${formName}.txdtarikhHantarResit);" />
        
			#if($mode == 'updateBayaran' || $mode == 'newBayaran')
			<img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdtarikhHantarResit',false,'dmy');" /> 
			#end 
			</td>
      </tr>		
      <tr>
        <td colspan="4">&nbsp;</td>
      </tr>      
            <tr>
        <td colspan="4" align="center">        
        #if ($mode == 'newBayaran')
        	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanBayaran()" />
       		<input class="stylobutton100" type="reset" name="cmdReset" id="cmdReset" value="Kosongkan"/>
        	<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalBayaran()" />
   		#elseif ($mode == 'viewBayaran')
         	<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniBayaran()" />
         	<input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapusBayaran()" />
         	<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalBayaran()" />
    	#elseif ($mode == 'updateBayaran')
        	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanUpdateBayaran()" />
      		<!-- <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/> -->
       		<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateBayaran()" />
     	#end 
   		</td>
        </tr>	
		</table>
	</fieldset>

<br/>
    
#end

#if ($!carianrekod == "tiada")
    <br/>

	<fieldset>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr>
				<td>No fail : <b>$!nofail</b> &nbsp;tiada dalam pangkalan data.</td>
			</tr>
		</table>
	</fieldset>
#end	

	
<input type="hidden" name="command" />	
<input type="hidden" name="id_status" value="$id_status"/>
<input type="hidden" name="keterangan" value="$keterangan_status"/>
<input type="hidden" name="id_fail" value="$id_fail"/>
<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_suburusanstatusfail" value="$id_suburusanstatusfail"/>
<input type="hidden" name="seksyen" value="$seksyen"/>
<input type="hidden" name="level" value=""/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_keputusanpermohonan">
<input type="hidden" name="id_perbicaraan">
<input type="hidden" name="id_perintah">
</form>

<script>
	
	function simpanBayaran(){
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.f1.actionPajakan.value = "BayaranPajakan";
			return;
		}
		
		document.f1.command.value = "tukarstatus";
		document.f1.submit();
	}
	
function kosongkan() {
	document.f1.action = "";
	document.f1.command.value = "kosongkan";
	document.f1.txtNoFail.focus();
	document.f1.submit();
}
function tukarstatus(id_permohonan,keterangan,id_suburusanstatusfail,id_fail,level,keterangan2,id_keputusanpermohonan,id_perbicaraan,id_perintah) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.f1.action = "";
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.id_suburusanstatusfail.value = id_suburusanstatusfail;
	document.f1.id_keputusanpermohonan.value = id_keputusanpermohonan;
	document.f1.id_fail.value = id_fail;
	document.f1.id_perbicaraan.value = id_perbicaraan;
	document.f1.id_perintah.value = id_perintah;
	document.f1.level.value = level;
	document.f1.command.value = "tukarstatus";
	document.f1.submit();
	alert("Status \""+keterangan+"\" telah ditukar kepada status \""+keterangan2+"\" ");
}
	function search_data(){
		if(document.f1.txtNoFail.value=="" ){
			return ;	}
		else{
			document.f1.command.value = "Cari";
			document.f1.action = "";
			document.f1.submit();
		}
	}

	//semakan Tarikh semasa
	function checkDateV01(inputfield) {
		var today = new Date();
		dari_bulan = inputfield.value.substring(3,5);
		dari_hari = inputfield.value.substring(0,2);
		dari_tahun = inputfield.value.substring(6,10);
		var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
		var myDate = Date.parse(daritemp);
	
		if (myDate>today) {
	  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
	  		inputfield.value = "";
	  		inputfield.focus();
	 		return false;
	 	}
	 	return true;
	
	}
</script>