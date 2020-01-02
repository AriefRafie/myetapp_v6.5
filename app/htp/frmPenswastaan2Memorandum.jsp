    
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  
  <input name="actionPenswastaan" type="hidden" id="actionPenswastaan" value="$actionPenswastaan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idJemaahMenteri" type="hidden" id="idJemaahMenteri" value="$idJemaahMenteri"/>


#foreach ($beanMemorandum in $BeanMemorandum)
                        #set($txdTerimaDariKSU = $beanMemorandum.txdTerimaDariKSU)
                        #set($txdUlasanKeKSU = $beanMemorandum.txdUlasanKeKSU)
                        #set($txtNoMemorandum = $beanMemorandum.txtNoMemorandum)
                        #set($txdMystJM = $beanMemorandum.txdMystJM)
                        #set($txdKeputusan = $beanMemorandum.txdKeputusan)
                        #set($txtUlasan = $beanMemorandum.txtUlasan)
                        
					#end 

<table width="100%" border="0">
#if ($idFail != '')
  <tr>
    <td>#parse("app/htp/frmPenswastaan2Header.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
    	<fieldset>
		<legend><strong>ULASAN MEMORANDUM</strong></legend>
		
		<table width="100%" border="0">
		  <tr>
		    <td width="50%">
				<table width="100%" border="0">
				 	<tr>
					<td>
				    	<table width="100%" border="0">
					      <tr>
					        <td width="1%">&nbsp;</td>
					        <td width="30%" align="left">Tarikh Terima dari KSU</td>
					        <td width="1%">:</td>
					        <td width="70%"><input type="text" name="txdTerimaDariKSU" id="txdTerimaDariKSU" value="$txdTerimaDariKSU" $readonly class="$inputTextClass"/>
					            <a href="javascript:displayDatePicker('txdTerimaDariKSU',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
					      </tr>
					      <tr>
					        <td width="1%">&nbsp;</td>
					        <td width="30%" align="left">Tarikh Ulasan ke KSU</td>
					        <td width="1%">:</td>
					        <td width="70%"><input type="text" name="txdUlasanKeKSU" id="txdUlasanKeKSU" value="$txdUlasanKeKSU" $readonly class="$inputTextClass"/>
					            <a href="javascript:displayDatePicker('txdUlasanKeKSU',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
					      </tr>
				    	</table>
		    		</td>
		  			</tr>
				</table>
			</td>
		    <td width="50%">&nbsp;</td>
		  </tr>
		</table>
		</fieldset>
		
		</td>
	</tr>

<!--<tr>
 <td>&nbsp;</td>
  </tr>	-->
	<tr>
    	<td>
    		<fieldset><legend><strong>KEPUTUSAN MEMORANDUM</strong></legend>

				<table width="100%" border="0">
			  		<tr>
			    		<td width="50%"> 
							<table width="100%" border="0">
							<tr>
								<td>
							    	<table width="100%" border="0">
									<tr>					
			        					<td width="1%"><font color="#FF0000">*</font></td>
			        					<td width="29%" align="left">&nbsp;No. Memorandum</td>
			        					<td width="1%">:</td>
			      						<td width="70%">
			      							<input type="text" name="txtNoMemorandum" id="txtNoMemorandum" value="$txtNoMemorandum" $readonly class="$inputTextClass"/>
			      						</td>
			    					</tr>
			    					<tr>
								        <td width="1%">&nbsp;</td>
								        <td width="30%" align="left">Tarikh Mesyuarat JM</td>
								        <td width="1%">:</td>
								        <td width="70%"><input type="text" name="txdMystJM" id="txdMystJM" value="$txdMystJM" $readonly class="$inputTextClass"/>
								            <a href="javascript:displayDatePicker('txdMystJM',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
								    </tr>
								    <tr>
								        <td width="1%">&nbsp;</td>
								        <td width="30%" align="left">Tarikh Keputusan</td>
								        <td width="1%">:</td>
								        <td width="70%"><input type="text" name="txdKeputusan" id="txdKeputusan" value="$txdKeputusan" $readonly class="$inputTextClass"/>
								            <a href="javascript:displayDatePicker('txdKeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
								    </tr>
								    <tr>
								        <td width="1%">&nbsp;</td>
								        <td width="30%" align="left">Keputusan</td>
								        <td width="1%">:</td>
								      	<td width="70%">
								        <label>
								          <select name="socKeputusan" id="socKeputusan" $readonly class="$disabled" $disabled>
								          	<option value="">SILA PILIH</option>
								         	<option value="L" selected="selected">DILULUSKAN</option>
								         	<option value="TL">TIDAK DILULUSKAN</option>
								          </select>
								        </label>
								        </td>
								
								    </tr>
								    <tr>
								        <td width="1%">&nbsp;</td>
								        <td width="30%" align="left" valign="top">Ulasan</td>
								        <td width="1%" valign="top">:</td>
								      	<td width="70%">
								        <label>
								        	<textarea name="txtUlasan" id="txtUlasan" cols="45" rows="5" $readonly class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase()">$txtUlasan</textarea>
								        </label>        
								       	</td>
								    </tr>
								</td>
							</tr>
							</table>
						</td>
			   			<td width="50%">&nbsp;</td> 
			  		</tr>
				</table>
			</fieldset>

		</td>
	</tr>

	<tr>
	    <td><center>
		#if ($mode == 'view') 
		      <input class="stylobutton" name="cmdkemaskini" type="button" value="Kemaskini" onclick="KemaskiniMerandum();"/>
		    
		#elseif ($mode == 'update') 
			<input class="stylobutton" name="cmdSimpan" type="button" value="Simpan" onclick="javascript:simpanUpdateMemorandum()"/>
		    <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
		    <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalMemorandum()" />
		#end
	     </center>
	     </td>
	</tr>
  #else
  <tr>
    <td>
    	&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    </td>
  </tr>
  #end
  
</table>





<script type="text/javascript">

function KemaskiniMerandum(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}

function batalMemorandum(){
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function simpanUpdateMemorandum(){
//
	if(document.${formName}.txtNoMemorandum.value == ""){
		alert('Sila masukkan maklumat No. Memorandum.');
  		document.${formName}.txtNoMemorandum.focus(); 
		return; 
	}	
	document.${formName}.actionPenswastaan.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanUpdateMemorandum";	
	doAjaxCall${formName}("");
}

</script>
