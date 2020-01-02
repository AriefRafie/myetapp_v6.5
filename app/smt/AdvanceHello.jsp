<form name="setup" method="post">

<table class="table_row" width="80%" cellpadding="1" cellspacing="0">

	<tr>
		<td valign="top" align="center">
			<table width="100%" cellpadding="2" cellspacing="1">
				<tr class="table_header">
					<td width="7%">&nbsp;</b>					</td>
			  <td width="59%" ><strong>Text</strong></td>
	  <td width="28%" >
						<b>Tarikh</b>					</td>					
				  <td width="6%">&nbsp;&nbsp;</td>
			  </tr>
					#set ( $cnt = 0 )			
					#foreach ( $hello in $SenaraiHello )
					#set ( $cnt = $cnt + 1 )
					#set( $i = $velocityCount )
					#if ( ($i % 2) == 0 )
					#set( $row = "row2" )
					#else
					#set( $row = "row1" )
					#end
					
					<tr>
						<td class="$row">
							$cnt.
						</td>
						<td class="$row">
							$hello.text
						</td>
                        			<td class="$row">
						$hello.last_modified_date
						</td>
						<td class="$row" align="right">
							<a href="javascript:edit_item('$hello.hello_id', '$hello.text')"><img src="../img/edit.gif" border="0"></a>
                        				<a href="javascript:delete_item('$hello.hello_id')"><img src="../img/delete.gif" border="0"></a>
							
						</td>						
					</tr>	
					
				#end				
			</table>
			
		</td>	
	</tr>
	<tr>
		<td>&nbsp;
			
		</td>
	</tr>
	<tr>
		<td valign="top">

			<table>
				<tr>
					<td>
						Hello Text:
						<br><br>
						<input name="hello_text" maxlength="80" size="80" value=""  onChange="this.value=this.value.toUpperCase();">
					</td>
				</tr>
  				<tr>
					<td valign="top" colspan="2">
						<input class="button"  name="buttonsubmit" type="button" value="Tambah" onKeyPress="add_item()" onclick="add_item()">
						<input class="button" type="button" value="Batal" onclick="cancel_update()">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="command">
<input type="hidden" name="hello_id">
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
