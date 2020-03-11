#set ($inputstyle = "class=disabled" )
#set ($inputstyleread = "readonly class=disabled" )
#set ($selectstyle = "disabled class=disabled" )
<fieldset>
  <legend>Fail Cukai Kemaskini Semasa FAIL CUKAI KEMASKINI SEMASA</legend>
  #parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
	<tr class="table_header">
	    <td width="6%">Bil</td>
	    <td width="12%">No. Hakmilik</td>
	    <td width="7%">Fail Dari</td>
	    <td width="13%">Tali Air (<span class="$row">RM</span>)</td>
	    <td width="15%">Parit (<span class="$row">RM </span>)</td>
	    <td width="15%">Cukai (<span class="$row">RM</span>)</td>
	    <td width="19%">Prosess</td>
  	</tr>
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $SenaraiFail )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  	<tr>
    	<td rowspan="2" class="$row">$cnt.</td>
        <td rowspan="2" class="$row">$senarai.NO_HAKMILIKUPLOAD</td>
      	<td class="$row">PTG/PTD</td>
       	<td class="$row"><input  value=" $senarai.cukai_taliair" class=disabled disabled ></td>
       	<td class="$row"><input  value="$senarai.cukai_parit" class=disabled disabled ></td>
       	<td class="$row"><input  value="$senarai.CUKAI_KENA_BAYAR" class=disabled disabled ></td>
    	<td rowspan="2" class="$row"><input type="button" name="button" id="button" value="Kemaskini"></td>
  	</tr>
    <tr>
    	<td class="$row">JKPTG</td>
    	<td class="$row"><input type="text"></td>
    	<td class="$row"><input type="text"></td>
    	<td class="$row"><input type="text"></td>

	#end
  	</tr>
#if ($cnt == 0)
  	<tr>
  		<td colspan="5" class="$row"><font color="#FF0000">Tiada Rekod.</font></td>
	</tr>
 #end
</table>
</fieldset>