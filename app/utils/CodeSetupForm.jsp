#if ($error != "")
<div class="error">$!error</div>
#end

#if ($CodeData.getRecordCount() > 0)
	    #*
	    <fieldset>
	    <legend><strong>Carian</strong>
	    		      <font size=1>
			       [
				<a href="#" onclick="Effect.toggle('toggle_appear', 'appear'); 
				return false;">Show/Hide</a>
				]
			     </font>
	   </legend>
	      <div id="toggle_appear" style="display:none">
	     <table width="100%">
	      #foreach( $field in [1..$CodeData.getFieldCount()] )
		      <tr>
		      <td width="29%" align="right">$NiceFieldName.get($field)</td>
		      <td width="1%">:</td>
		      <td width="70%"><input type="text" name="txtNoEnakmen" id="txtNoEnakmen" />
		      </td>
		      </tr>
		
	     #end
	     </table>
		</div>      
	      

  	</fieldset>  
	
		*#


          #parse("app/utils/record_paging.jsp")
             #set ($columnCount = $CodeData.getFieldCount())
             #set ($columnSize = $columnCount + 2)
             <br />
             <fieldset>
<table width="100%" cellpadding="3" cellspacing="1" border="0">
<tr>
    <td class="table_header" colspan=$columnSize><b>&nbsp;Table : $!NiceTableName</b>

    </td>
    
</tr>
<tr class="table_row">
<td></td>
#set ($limitPerColumn = 5)
#foreach( $field in [1..$CodeData.getFieldCount()] )
	#if ( $field < $limitPerColumn )
	<td height="30px">
	<a href="javascript:doAjaxCall${formname}('record_listing','orderby=$FieldName.get($field)&ordertype=$ordertype')">
	##$util.capitalizedFirstCharacter($NiceFieldName.get($field))
	$!NiceFieldName.get($field).toUpperCase()
	</a>
	</td>
	#end
#end
<td></td>
</tr>
<!-- Table Content -->
#foreach ( $list in $SenaraiFail )
#set( $counter = $velocityCount )
#if ( ($counter % 2) == 0 )
    #set( $row = "row2" )
#else
    #set( $row = "row1" )
#end
##$CodeData.printObject($list)	
##onClick="javascript:doAjaxCall${formName}('edit_form','id=$list.get(0)')" 
<tr class="$row" onMouseOver="this.className='highlight'" onMouseOut="this.className='$row'">
<td>
#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
$cnt
</td>
#foreach( $i in [1..$CodeData.getFieldCount()] )
	#if ( $i < $limitPerColumn )
		<td>
		##if ($list.get("isLookup$i") == "false")
		##$list.get($i)
		##else
		##	$CodeData.getLookup($list.get("isLookup$i"),$list.get($i))
		##end
		$CodeData.getValue($list.get($i))
		</td>
	#end
#end
<td align="center" width="5%">
<a href="javascript:doAjaxCall${formName}('edit_form','id=$list.get(0)')"><img src="../img/edit.gif" border="0"></a>
&nbsp;&nbsp;
<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('delete','id_fieldname=$FieldName.get(0)&id=$list.get(0)') } "><img src="../img/delete.gif" border="0"></a>
<!--<input type="checkbox" value="$list.get(0)" name="ids"/>-->
</td>
</tr>
#end
<input type=hidden name=page value=$page>
</table>
#elseif ($error != "")

#else
<div class="info">Tiada Rekod</div>
#end

</fieldset>
<input class="stylobutton"  type="button" value="Tambah" onClick="javascript:doAjaxCall${formname}('AddNew')">
<input class="stylobutton"  type="button" value="Kembali" onClick="javascript:doAjaxCall${formname}('goBack')">