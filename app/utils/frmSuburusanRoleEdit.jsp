#if ($result == "success")
<div class=info>Kemaskini Berjaya</div>
#end
<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
		<fieldset>
		<legend>Maklumat Sub Urusan/Role </legend>
		<table border="0" cellpadding="2" cellspacing="2">
		    <tr>
		         <td>Urusan</td>
		         <td>$selectUrusan</td>
			</tr>
		    <tr>
		    	<td >Sub Urusan</td>
		        <td>$selectSuburusan</td>
		    </tr>
		 	<tr>
				<td >Role</td>
				<td>  					
					<select name="Form_name">
	                    <option value="">- Sila pilih -</option>
	                	#foreach ( $role in $roleList )
		                    #if ( $role.getName() != "root" )
		                        #if ( $userRole ==  $role.getName() )
		                            #set ( $description = $role.getDescription() )
		                    <option value="$role.getName()" selected>$role.getName()</option>
		                        #else
		                    <option value="$role.getName()">$role.getName()</option>
		                        #end
		                    #end
	                	#end
	                </select>
                </td>
			</tr>
		</table>
		</fieldset>
	</td>
</tr>
</table>

<input type=hidden name="id" value="$id">
#if ($mode == "add")
	<input type=button value=Simpan onClick="javascript:doAjaxCall${formname}('simpan')">
#else
	<input type=button value=Kemaskini onClick="javascript:doAjaxCall${formname}('doUpdate')">
#end
<input type=button value=Kembali onClick="javascript:doAjaxCall${formname}('goBack')">

<script type="javascript">
	function doChanges() {
	alert('xxxx');
	  doAjaxCall${formName}("edit");
	}
	
	function doChanges1() {
	  alert('HAI group');
	  doAjaxCall${formName}("edit1");
	}
</script>

