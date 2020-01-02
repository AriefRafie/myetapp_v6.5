
#set($dashLampiran = "-")
#set($displayBrowseFail = "")
#if($listLampiran.size()>=3 || ($ID_STATUS != "" && $ID_STATUS != "16125"))
	#set($displayBrowseFail = "none")
#else
	#if($mode=="view")
    	#set($displayBrowseFail = "none")
    #else
        <br />
        #set($dashLampiran = "")
    #end	
#end
<script>
document.getElementById("ShowFieldFile$ID_WARTATRM").style.display = "$displayBrowseFail";
</script>

<!--
$ID_STATUS<br />
$ID_PENGADU<br />
$mode<br />
-->

<table border="0" cellpadding="0" cellspacing="0" align="left">
#if($listLampiran.size()>0)
    	#foreach($Lam in $listLampiran)
        <tr>
        <!--<td valign="top" align="center">$Lam.BIL</td>-->
        #if((
        ($ID_PENGADU == $USER_ID_SYSTEM && $ID_STATUS == "16125")
        || 
        ($ID_PENGADU == "" && $USER_ROLE == "user_unit_intergriti" && ($ID_STATUS == "16125"))
        ) 
        && $mode=="edit")
        <td valign="top" align="center">
        <a href="javascript:if(confirm('$label_adakah_pasti_delete_doc')){ doDivAjaxCall3$formname('divSenaraiLampiran$Lam.ID_WARTATRM','deleteLampiran','ID_WARTATRM=$Lam.ID_WARTATRM&NAMA_LAMPIRAN=$Lam.NAMA_LAMPIRAN&ID_WARTATRMLAMPIRAN=$Lam.ID_WARTATRMLAMPIRAN&ID_STATUS=$ID_STATUS&ID_PENGADU=$ID_PENGADU&mode=$mode');}">
        <img title="$label_hapus"  src="../img/hapus.gif" border="0">
        </a>
        </td>
        #end
        <td valign="top" align="left">
        <span class="font_tajuk_sub" id="$span2" style="cursor:pointer" >
            <u onClick="paparDoc($Lam.ID_WARTATRMLAMPIRAN);">$Lam.NAMA_LAMPIRAN</u>
        </span>
            
        </td>
        </tr>
        #end
#else
		#if($ID_STATUS != "" && $ID_STATUS != "16125") #end 
   		<tr>
        <td valign="top" align="left">$dashLampiran</td>
        </tr>
        
   
#end
</table>


<script>
     $jquery(document).ready(function () {
				 doDivAjaxCall3$formname('divKronologi_$ID_WARTATRM','showKronologi','ID_WARTATRM=$ID_WARTATRM');			 	  
	 });	
</script>


