<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset><legend><strong>MAKLUMAT TRANSAKSI</strong></legend>
      <table width="50%" border="0" cellspacing="2" cellpadding="2">
      		<TR>
      			<TD>
      				No. Transaksi
      			</TD>
      			<td>
      				:
      			</td>
      			<TD>
      				<input type="text" value="$!fpx.noTransaksi" size="40" readonly="readonly">
      			</TD>
      		
      		</TR>
      		<TR>
      			<TD>
      				MyID / MYcoID
      			</TD>
      			<td>
      				:
      			</td>
      			<TD>
      				<input type="text" value="$!fpx.noKp" readonly="readonly">
      			</TD>
      		
      		</TR>
      		<TR>
      			<TD>
      				Nama
      			</TD>
      			<td>
      				:
      			</td>
      			<TD>
      				<input type="text" value="$!fpx.nama.toUpperCase()" readonly="readonly">
      			</TD>
      		
      		</TR>
      		<TR>
      			<TD>
      				No. Fail
      			</TD>
      			<td>
      				:
      			</td>
      			<TD>
      				<input type="text" value="$!fpx.noFail" readonly="readonly">
      			</TD>
      		
      		</TR>
      		<TR>
      			<TD>
      				Amaun (RM)
      			</TD>
      			<td>
      				:
      			</td>
      			<TD>
      				<input type="text" value="$nf.format($!fpx.amount)" readonly="readonly">
      			</TD>
      		
      		</TR>
      		<TR>
      			<TD>
      				No. Bil
      			</TD>
      			<td>
      				:
      			</td>
      			<TD>
      				<input type="text" value="$!fpx.noBil" readonly="readonly">
      			</TD>
      		
      		</TR>
      		<TR>
      			<TD valign="top">
      				Catatan
      			</TD>
      			<td valign="top">
      				:
      			</td>
      			<TD>
      				<textarea rows="4" cols="40%" readonly="readonly">$!fpx.catatan</textarea>
      			</TD>
      		
      		</TR>
      		<TR>
      			<TD>
      				Status Transaksi FPX
      			</TD>
      			<td>
      				:
      			</td>
      			<TD>
      				<input type="text" value="$!fpx.status" readonly="readonly">
      			</TD>
      		
      		</TR>
      		<tr>
      			<td colspan="3" align="center">
      				<input name="cmdTambah" type="button" value="Kembali" onclick="javascript:kembali()" id="cmdTambah"/>
      				#if($fpx.status=='SUCCESSFUL')
      				<input name="cmdTambah" type="button" value="Cetak Resit" onclick="javascript:cetakResit($!fpx.id,'$!nilai')" id="cmdTambah"/>
      				#end
      			</td>
      		
      		</tr>
      </table>
     </fieldset>
    </td>
    </tr>
    </table>