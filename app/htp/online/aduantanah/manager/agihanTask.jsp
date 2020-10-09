<table width="90%">


	<tr>
		<td>
		<FIELDSET>
		<legend>MAKLUMBALAS AGIHAN</legend>
			<TABLE>
				<TR>
					<TD>
						Arahan

					</TD>
					<TD>
						:

					</TD>

					<TD>
						#foreach ( $response in $responses)
							$!response.ARAHAN
						#end
					</TD>
				</TR>
				<TR>
					<TD>
						Bahagian / Seksyen Bertangungjawab

					</TD>
					<TD>
						:

					</TD>

					<TD>
						#foreach ( $response in $responses)
							$!response.NAMA_PEGAWAI
						#end
					</TD>
				</TR>
				<TR>
					<TD>
						Status

					</TD>
					<TD>
						:

					</TD>

					<TD>
						#foreach ( $response in $responses)
							$!response.STATUS
						#end
					</TD>
				</TR>
				<TR>
					<TD valign="top">
						Ulasan/Catatan

					</TD>
					<TD valign="top">
						:

					</TD>

					<TD>

						#if($response.tindakan.category.id =="4" || $response.tindakan.category.id =="5" || $response.tindakan.category.id =="1")
							<textarea rows="10" cols="100" name="ulasanRespon" onblur="this.value=this.value.toUpperCase();">$!response.jawapan</textarea>

						#else
							#foreach ( $response in $responses)
								$!response.JAWAPAN
							#end

						#end
					</TD>
				</TR>
			</TABLE>
		</FIELDSET>
		</td>


	</tr>
	<tr>
		<td align="center" colspan="4">

			#parse('app/online/aduan/toolbar/toolbar.jsp')

		</td>


	</tr>


</table>
<input type="hidden" name="idComplaint" value="$!complaint.id">
<input type="hidden" name="idResponse" value="$!response.id">