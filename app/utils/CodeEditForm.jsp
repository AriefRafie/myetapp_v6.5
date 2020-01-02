<table width="500" cellpadding="1" cellspacing="0" border="0">
<tr>
    <td class="table_header" colspan="5" width="40%"><b>&nbsp;$NiceTableName</b></td>
</tr>

#if ($mode == "edit")
#set ($list = $SenaraiFail.get(0))
#end

#foreach( $field in [1..$CodeData.getFieldCount()] )
#set ( $size = $FieldLength.get($field) )
<tr>
<td width="40%">$util.capitalizedFirstCharacter($NiceFieldName.get($field))</td>
<td>
##if ($list.get("isLookup$field") == "false")
#if ($CodeData.isLookupByFieldName($FieldName.get($field)))
	<select name=Form_$FieldName.get($field) class="bigselect">
	$CodeData.getSelectOptionValue($FieldName.get($field),$!list.get($field))
	</select>
#else
	<input type=text name="Form_$FieldName.get($field)" 
	#if ($mode == "edit")
		value="$!list.get($field)"
	#else
		value=""
	#end
	size=$!CodeData.getConvertedFieldSize($size) onblur="this.value=this.value.toUpperCase();" maxLength=$size>
#end	
	
	</td>
	</tr>
#end

<input type=hidden name="tableName" value="$tablename">
<input type=hidden name="id" value="$list.get(0)">
<input type=hidden name="id_fieldname" value="$FieldName.get(0)">

</table>

#if ($mode == "edit")
<input type=button value="Update" onClick="javascript:doAjaxCall${formname}('doUpdate')">
#else
<input type=button value="Add" onClick="javascript:doAjaxCall${formname}('doAdd')">
#end
<hr width="500" align=left>
<input type=button value="Senarai Code Setup" onClick="javascript:doAjaxCall${formname}('goBack')">
<input type=button value="Senarai:$NiceTableName" onClick="javascript:doAjaxCall${formname}('goBack2')">
