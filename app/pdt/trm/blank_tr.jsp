

<!--
:::::: [JENISSUB : $JENISSUB]
[ID_WARTATRMINDUK : $ID_WARTATRMINDUK]
[ID_WARTATRM : $ID_WARTATRM]
-->
<tr id="divRow" >
<td align="left" valign="top" colspan="14" >
</td>
</tr>

#set($commandResetList = "showSenarai")
#if($ID_WARTATRMINDUK!="" && $JENISSUB != "")
    #if($JENISSUB == "B")
        #set($commandResetList = "showSenaraiBatal")
    #else
        #set($commandResetList = "showSenaraiGanti")
    #end
#end

<!--
****** :::::::::: $flag_reset_list divLIST : div_Senarai$JENISSUB$ID_WARTATRMINDUK : $commandResetList
-->

<!-- ::::::: $flag_reset_list -->
#set($divSenarai = "div_Senarai"+$JENISSUB+$ID_WARTATRMINDUK )
#if($flag_reset_list == "yes" && $ID_WARTATRM != "")
<script>
/*
$jquery(document).ready(function () {
             doDivAjaxCall3$formname('div_Senarai$JENISSUB$ID_WARTATRMINDUK','$commandResetList','OPENCLOSE_CARIAN=close&FLAG_CARIAN=N&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB');			 	  
 });
*/
 
 $jquery(document).ready(function () {
		 doDivAjaxCall$formname('$divSenarai','$commandResetList','ID_WARTATRMINDUK=$ID_WARTATRMINDUK&LOCATION=$divSenarai&JENISSUB=$JENISSUB'); 
	  });

</script>
#end


