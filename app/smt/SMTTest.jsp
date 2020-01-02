<form name="setup" method="post">

<table class="table_row" width="80%" cellpadding="1" cellspacing="0">

	<tr>
		<td valign="top" align="center">&nbsp;</td>	
	</tr>
	<tr>
		<td>
	    <table width="491" border="0">
          <tr>
            <td width="107">Nama</td>
            <td width="374"><label>
              <input type="text" name="nama" id="nama" />
            </label></td>
          </tr>
          <tr>
            <td>IC Lama</td>
            <td><label>
              <input type="text" name="ic_lama" id="ic_lama" />
            </label></td>
          </tr>
          <tr>
            <td>IC Baru</td>
            <td><input type="text" name="ic_baru" id="ic_baru" /></td>
          </tr>
          <tr>
            <td>Alamat</td>
            <td><label>
              <textarea name="alamat" id="alamat" cols="45" rows="5"></textarea>
            </label></td>
          </tr>
        </table>
        </td>
	</tr>
	<tr>
		<td valign="top">

			<table>
  				<tr>
					<td valign="top" colspan="2">
						<input class="button"  name="buttonsubmit" type="button" value="Cari" onKeyPress="add_item()" onclick="add_item()">
						<input class="button" type="button" value="Batal" onclick="cancel_update()">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<p>&nbsp;</p>
<p>
  <input class="button"  name="buttonsubmit2" type="button" value="Tambah" onkeypress="add_item()" onclick="add_item()" />
  <br />
</p>
<table width="100%" cellpadding="2" cellspacing="1">
  <tr class="table_header">
    <td width="7%">&nbsp;</b> </td>
    <td width="59%" ><strong>Nama</strong></td>
    <td width="28%" >IC Lama</td>
    <td width="28%" >IC Baru</td>
    <td width="28%" >Alamat</td>
    <td width="28%" ><b>Tarikh</b> Kemaskini </td>
    <td width="6%">&nbsp;&nbsp;</td>
  </tr>
 <tr>
      <td class="$row"> $cnt. </td>
    <td class="$row"> $hello.text </td>
    <td class="$row">&nbsp;</td>
    <td class="$row">&nbsp;</td>
    <td class="$row">&nbsp;</td>
    <td class="$row"> $hello.last_modified_date </td>
    <td class="$row" align="right"><a href="javascript:edit_item('$hello.hello_id', '$hello.text')"><img src="../img/edit.gif" border="0" /></a> <a href="javascript:delete_item('$hello.hello_id')"><img src="../img/delete.gif" border="0" /></a> </td>
  </tr>

</table>
<p><br>
    <input type="hidden" name="command">
    <input type="hidden" name="hello_id">
</p>
</form>
<script>
function add_item() {
	if ( document.setup.buttonsubmit.value == "Kemaskini" ) {
		document.setup.command.value = "update";	
	}
	else {
		document.setup.command.value = "add";
	}
	if(document.setup.hello_text.value == ""){
		alert('Please say something..');
		document.setup.hello_text.focus();
		return;
	}
	document.setup.action = "";
	document.setup.submit();
}
function delete_item(id) {
	if ( !window.confirm("Anda Pasti?") ) return;
	document.setup.command.value = "delete";
	document.setup.hello_id.value = id;
	document.setup.action = "";
	document.setup.submit();
}
function edit_item(id,text) {
	document.setup.hello_id.value = id;
	document.setup.hello_text.value = text;
	document.setup.buttonsubmit.value = "Kemaskini";
}
function cancel_update() {
	document.setup.buttonsubmit.value = "Tambah";
	document.setup.reset();
	document.forms[0].kod_hello.focus();
}

</script>
