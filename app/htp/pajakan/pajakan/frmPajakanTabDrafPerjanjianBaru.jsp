<fieldset>
	<legend>Draf Perjanjian</legend>
	<table width="100%" border="0">
		<tr>
	    	<td>
	    		<table width="100%" border="0">
		      		<tr>
		        		<td width="20%" align="right">&nbsp;</td>
		        		<td width="29%">Tarikh Hantar Draf</td>
		        		<td width="1%">:</td>
		        		<td width="50%">
		        			<input type="text" name="txdTarikhHantarDraf" id="txdTarikhHantarDraf" size="10" value="$tarikhHantarDraf" />
		          			<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');">
		          		</td>
		        		<td width="20%">&nbsp;</td>
		      		</tr>
			      <tr>
			        <td align="right">&nbsp;</td>
			        <td>Tarikh Terima Draf</td>
			        <td>:</td>
			        <td><input type="text" name="txdTarikhTerimaDraf" id="txdTarikhTerimaDraf" size="10" value="$tarikhTerimaDraf" />
			          <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdTarikhTerimaDraf',false,'dmy');"></td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td>&nbsp;</td>
			        <td>Tarikh Hantar PKP</td>
			        <td>:</td>
			        <td><input type="text" name="txdTarikhHantarPKP" id="txdTarikhHantarPKP" size="10" value="$tarikhHantarPKP" />
			          <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhHantarPKP',false,'dmy');"></td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td>&nbsp;</td>
			        <td>Tarikh Terima PKP</td>
			        <td>:</td>
			        <td><input type="text" name="txdTarikhTerimaPKP" id="txdTarikhTerimaPKP" size="10" value="$tarikhTerimaPKP" />
			          <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdTarikhTerimaPKP',false,'dmy');"></td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td>&nbsp;</td>
			        <td>Catatan</td>
			        <td>:</td>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			      </tr>
	    		</table>
	    	</td>
	  	</tr>
	  	<tr>
	    	<td>&nbsp;</td>
	  	</tr>
	  	<tr align="center">
	    	<td>
		    	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="" />
		&nbsp;&nbsp;
				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="" />
		&nbsp;&nbsp;
				<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="" />
		&nbsp;&nbsp;
				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="" />
		&nbsp;&nbsp;
				<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="" />
		&nbsp;&nbsp;
			</td>
	  	</tr>
	  	<tr>
	    	<td>&nbsp;</td>
	  	</tr>
	  	<tr>
	    	<td>&nbsp;</td>
	  	</tr>
	</table>
</fieldset>
#parse("app/htp/utiliti/javascript/javaScriptPajakanPerjanjian.jsp")
