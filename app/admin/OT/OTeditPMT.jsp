#if($viewPMT.ID_MINITTRANSAKSI!="")
<script>
//alert("1 : "+'$viewPMT.ID_MINITTRANSAKSI');
if( $jquery('#div_rowPMT$viewPMT.ID_MINITTRANSAKSI').length )         // use this if you are using id to check
{
	//alert("2");
	window.scrollTo(0, $jquery('#div_rowPMT$viewPMT.ID_MINITTRANSAKSI').offset().top);
}
</script>
#else
<script>
if( $jquery('#div_addPMT').length )         // use this if you are using id to check
{
	//alert("4");
	window.scrollTo(0, $jquery('#div_addPMT').offset().top);
}
</script>
#end
<tr  >
<td align="left" valign="top" colspan="14">
	<fieldset class="classFade">
	<legend>
	
	#if($viewPMT.ID_MINITTRANSAKSI != "")
	Kemaskini Maklumat $viewPMT.NAMA_AKTIVITI
	#else
	Kemasukan Maklumat
	#end
	</legend>
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" id="td_checkTransaksiPMT_$viewPMT.ID_MINITTRANSAKSI">
			<input type="hidden" id="checkTransaksiPMT_$viewPMT.ID_MINITTRANSAKSI" name="checkTransaksiPMT_$viewPMT.ID_MINITTRANSAKSI" value="true" >
			</td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Nama Aktiviti
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="ID_TRANSAKSI_$viewPMT.ID_MINITTRANSAKSI"  name="ID_TRANSAKSI_$viewPMT.ID_MINITTRANSAKSI"
				onChange="doDivAjaxCall3$formname('td_checkTransaksiPMT_$viewPMT.ID_MINITTRANSAKSI','checkPMT','ID_MINITTRANSAKSI=$viewPMT.ID_MINITTRANSAKSI');" 
				
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $AK in $listAktiviti )		
							#set ( $selected_AK = "" )
							#if($viewPMT.ID_TRANSAKSI==$AK.ID_TRANSAKSI)
							#set ( $selected_AK = "selected" )
							#end
							<option $selected_AK value="$AK.ID_TRANSAKSI" >
							$AK.NAMA_AKTIVITI
							</option>							
						#end
					</select>
				
							
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Minit Untuk Kemasukkan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input type="text" id="MINIT_INSERT_$viewPMT.ID_MINITTRANSAKSI" onKeyUp="javascript:numberOnly(this,this.value)" maxLength="3" size="5" style="text-transform:uppercase;" name="MINIT_INSERT_$viewPMT.ID_MINITTRANSAKSI" value="$viewPMT.MINIT_INSERT" >
	  			</td>
			</tr>
			
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Minit Untuk Pengemaskinian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input type="text" id="MINIT_UPDATE_$viewPMT.ID_MINITTRANSAKSI" onKeyUp="javascript:numberOnly(this,this.value)" maxLength="3" size="5" style="text-transform:uppercase;" name="MINIT_UPDATE_$viewPMT.ID_MINITTRANSAKSI" value="$viewPMT.MINIT_UPDATE" >
	  			</td>
			</tr>
			
			
		
      	
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >			
				#if($viewPMT.ID_MINITTRANSAKSI!="")				
				<input type="button" id="BTNSAVE$viewPMT.ID_MINITTRANSAKSI" name="BTNSAVE$viewPMT.ID_MINITTRANSAKSI" 
				onClick="if(valEditPMT('$viewPMT.ID_MINITTRANSAKSI')==true){doDivAjaxCall$formname('div_rowPMT$viewPMT.ID_MINITTRANSAKSI','savePMT','ID_MINITTRANSAKSI=$viewPMT.ID_MINITTRANSAKSI&BIL=$BIL&rowCss=$rowCss');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$viewPMT.ID_MINITTRANSAKSI" name="BTNBTL$viewPMT.ID_MINITTRANSAKSI" onClick="doDivAjaxCall$formname('div_rowPMT$viewPMT.ID_MINITTRANSAKSI','editPMT','ID_MINITTRANSAKSI=$viewPMT.ID_MINITTRANSAKSI&BIL=$BIL&rowCss=$rowCss');" value="Batal" > 
	   			<input type="button" id="BTNCLOSE$viewPMT.ID_MINITTRANSAKSI" name="BTNCLOSE$viewPMT.ID_MINITTRANSAKSI" onClick="doDivAjaxCall$formname('div_rowPMT$viewPMT.ID_MINITTRANSAKSI','closePMT','ID_MINITTRANSAKSI=$viewPMT.ID_MINITTRANSAKSI&BIL=$BIL&rowCss=$rowCss');" value="Tutup" > 
	   			#else
	   			
	   			<input type="button" id="BTNSAVE$viewPMT.ID_MINITTRANSAKSI" name="BTNSAVE$viewPMT.ID_MINITTRANSAKSI" 
				onClick="if(valEditPMT('')==true){doDivAjaxCall$formname('div_SenaraiPMT','insertPMT','ID_MINITTRANSAKSI=');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$viewPMT.ID_MINITTRANSAKSI" name="BTNBTL$viewPMT.ID_MINITTRANSAKSI" onClick="doDivAjaxCall$formname('div_addPMT','addPMT','ID_MINITTRANSAKSI=');" value="Batal" > 
	   			<input type="button" id="BTNCLOSE$viewPMT.ID_MINITTRANSAKSI" name="BTNCLOSE$viewPMT.ID_MINITTRANSAKSI" onClick="document.getElementById('div_addPMT').innerHTML='';" value="Tutup" > 
	   			#end
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>