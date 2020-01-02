<center>
<fieldset>
<legend>Profil Pegawai</legend>
	<table width="100%" border="0">
		<tr>
			<td>&nbsp;</td>
			<td>Nama Pegawai</td>
			<td>:</td>
			<td>$!selectPegawai</td>
		</tr>
	</table>
</fieldset>
</center>


<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>