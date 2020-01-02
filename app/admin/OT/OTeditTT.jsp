#if($viewPMT.ID_TRANSAKSI!="")
<script>
//alert("mula");
if( $jquery('#div_rowTT$viewPMT.ID_TRANSAKSI').length )         // use this if you are using id to check
{
	//alert("1");
	window.scrollTo(0, $jquery('#div_rowTT$viewPMT.ID_TRANSAKSI').offset().top);
}
</script>
#else
<script>
if( $jquery('#div_addTT').length )         // use this if you are using id to check
{
		//alert("3");
		window.scrollTo(0, $jquery('#div_addTT').offset().top);
}
</script>
#end
<tr >
<td align="left" valign="top" colspan="14">
	<fieldset class="classFade">
	<legend>
	
	#if($viewTT.ID_TRANSAKSI != "")
	Kemaskini Maklumat $viewTT.NAMA_TABLE
	#else
	Kemasukan Maklumat
	#end
	</legend>
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%" id="td_checkTransaksiTT_$viewTT.ID_TRANSAKSI"><input type="hidden" id="checkTransaksiTT_$viewTT.ID_TRANSAKSI" name="checkTransaksiTT_$viewTT.ID_TRANSAKSI" value="true" ></td>
			</tr>
			
			
			<input  type="hidden" id="ID_SEKSYEN_$viewTT.ID_TRANSAKSI" 
				name="ID_SEKSYEN_$viewTT.ID_TRANSAKSI" 
				value="$viewTT.ID_SEKSYEN" onKeyUp="" >		
			
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
				<input style="text-transform:uppercase;" size="50" type="text" id="NAMA_AKTIVITI_$viewTT.ID_TRANSAKSI" 
				name="NAMA_AKTIVITI_$viewTT.ID_TRANSAKSI" 
				value="$viewTT.NAMA_AKTIVITI" onKeyUp="" >				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Nama Table
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input style="text-transform:uppercase;" size="50" type="text" id="NAMA_TABLE_$viewTT.ID_TRANSAKSI" 
				name="NAMA_TABLE_$viewTT.ID_TRANSAKSI" 
				value="$viewTT.NAMA_TABLE" 
				onKeyUp="doDivAjaxCall3$formname('td_checkTransaksiTT_$viewTT.ID_TRANSAKSI','checkTT','ID_TRANSAKSI=$viewTT.ID_TRANSAKSI');" 
				>				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Nama Field Check
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input style="text-transform:uppercase;" size="50" type="text" id="FIELD_CHECK_$viewTT.ID_TRANSAKSI" 
				name="FIELD_CHECK_$viewTT.ID_TRANSAKSI" 
				value="$viewTT.FIELD_CHECK" 
				onKeyUp="doDivAjaxCall3$formname('td_checkTransaksiTT_$viewTT.ID_TRANSAKSI','checkTT','ID_TRANSAKSI=$viewTT.ID_TRANSAKSI');" 
				>				
				</td>
			</tr>
			
			<!--  
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Query No. Fail
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				 <textarea style="text-transform:uppercase;" name="QUERY_NOFAIL_$viewTT.ID_TRANSAKSI" id="QUERY_NOFAIL_$viewTT.ID_TRANSAKSI" cols="80" rows="8" 
		 onKeyup="check_length(this,4000,'span_QUERY_NOFAIL_$viewTT.ID_TRANSAKSI');doDivAjaxCall3$formname('td_checkTransaksiTT_$viewTT.ID_TRANSAKSI','checkTT','ID_TRANSAKSI=$viewTT.ID_TRANSAKSI');" >$viewTT.QUERY_NOFAIL</textarea>
         <span id="span_QUERY_NOFAIL_$viewTT.ID_TRANSAKSI"  ></span>	
         <br>
         [Contoh : SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '999999' AND ROWNUM = 1]		
				</td>
			</tr>
			-->
			
			
		
      	
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
				
				
				
				#if($viewTT.ID_TRANSAKSI!="")
				<input type="button" id="BTNSAVE$viewTT.ID_TRANSAKSI" name="BTNSAVE$viewTT.ID_TRANSAKSI" 
				onClick="if(valEditTT('$viewTT.ID_TRANSAKSI')==true){doDivAjaxCall$formname('div_rowTT$viewTT.ID_TRANSAKSI','saveTT','ID_TRANSAKSI=$viewTT.ID_TRANSAKSI&BIL=$BIL&rowCss=$rowCss');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$viewTT.ID_TRANSAKSI" name="BTNBTL$viewTT.ID_TRANSAKSI" onClick="doDivAjaxCall$formname('div_rowTT$viewTT.ID_TRANSAKSI','editTT','ID_TRANSAKSI=$viewTT.ID_TRANSAKSI&BIL=$BIL&rowCss=$rowCss');" value="Batal" > 
	   			<input type="button" id="BTNCLOSE$viewTT.ID_TRANSAKSI" name="BTNCLOSE$viewTT.ID_TRANSAKSI" onClick="doDivAjaxCall$formname('div_rowTT$viewTT.ID_TRANSAKSI','closeTT','ID_TRANSAKSI=$viewTT.ID_TRANSAKSI&BIL=$BIL&rowCss=$rowCss');" value="Tutup" > 
	   			#else
	   			<input type="button" id="BTNSAVE$viewTT.ID_TRANSAKSI" name="BTNSAVE$viewTT.ID_TRANSAKSI" 
				onClick="if(valEditTT('')==true){doDivAjaxCall$formname('div_SenaraiTT','insertTT','ID_TRANSAKSI=');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$viewTT.ID_TRANSAKSI" name="BTNBTL$viewTT.ID_TRANSAKSI" onClick="doDivAjaxCall$formname('div_addTT','addTT','ID_TRANSAKSI=');" value="Batal" > 
	   			<input type="button" id="BTNCLOSE$viewTT.ID_TRANSAKSI" name="BTNCLOSE$viewTT.ID_TRANSAKSI" onClick="document.getElementById('div_addTT').innerHTML='';" value="Tutup" > 
	   			#end
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>