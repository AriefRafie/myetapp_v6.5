
<!--
SENARAI :::: $docType

ID_STATUS ::: $ID_STATUS

$listLampiran.size()

mode :: $mode
-->


#set($dashLampiran = "-")
#set($displayBrowseFail = "")
#if((($listLampiran.size()>=3 || ($ID_STATUS != "" && $ID_STATUS != "16125")) && $docType == "PENGADU")
||
(($listLampiran.size()>=3 || ($ID_STATUS != "" && $ID_STATUS != "16122" )) && $docType == "UI")
||
(($listLampiran.size()>=3 || ($ID_STATUS != "" && $ID_STATUS != "16127" )) && $docType == "BAHAGIAN")
)

	#set($displayBrowseFail = "none")
#else

	#if(($mode=="view" && $docType == "PENGADU") || ($mode=="view" && $docType == "UI") || ($mode=="view" && $docType == "BAHAGIAN"))
    	#set($displayBrowseFail = "none")
    #else
        <br />
        #set($dashLampiran = "")
    #end	
#end

<script>
document.getElementById("ShowFieldFile$docType$ID_ADUANPUBLIC").style.display = "$displayBrowseFail";
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
        #if(((
        ($ID_PENGADU == $USER_ID_SYSTEM && $ID_STATUS == "16125")
        || 
        ($ID_PENGADU == "" && $USER_ROLE == "user_unit_intergriti" && ($ID_STATUS == "16125"))
        ) 
        && $mode=="edit" && $docType == "PENGADU")  
        ||   ((
        ($ID_PEGAWAI_UI == $USER_ID_SYSTEM && $ID_STATUS == "16122")
        ) 
        && $mode=="edit" && $docType == "UI")
        ||   ((
        ($ID_PEGAWAI_BAHAGIAN == $USER_ID_SYSTEM && $ID_STATUS == "16127")
        ) 
        && $mode=="edit" && $docType == "BAHAGIAN"))
        <td valign="top" align="center">
        <a href="javascript:if(confirm('$label_adakah_pasti_delete_doc')){ doDivAjaxCall3$formname('divSenaraiLampiran$docType$Lam.ID_ADUANPUBLIC','deleteLampiran','ID_ADUANPUBLIC=$Lam.ID_ADUANPUBLIC&NAMA_LAMPIRAN=$Lam.NAMA_LAMPIRAN&ID_ADUANPUBLICLAMPIRAN=$Lam.ID_ADUANPUBLICLAMPIRAN&ID_STATUS=$ID_STATUS&ID_PENGADU=$ID_PENGADU&ID_PEGAWAI_UI=$ID_PEGAWAI_UI&ID_PEGAWAI_BAHAGIAN=$ID_PEGAWAI_BAHAGIAN&mode=$mode&docType=$docType');}">
        <img title="$label_hapus"  src="../img/hapus.gif" border="0">
        </a>
        </td>
        #end
        <td valign="top" align="left">
        <span class="font_tajuk_sub" id="$span2" style="cursor:pointer" >
            <u onClick="paparDoc($Lam.ID_ADUANPUBLICLAMPIRAN);">$Lam.NAMA_LAMPIRAN</u>
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

#if($docType=="PENGADU")
<script>
     $jquery(document).ready(function () {
				 doDivAjaxCall3$formname('divKronologi_$ID_ADUANPUBLIC','showKronologi','ID_ADUANPUBLIC=$ID_ADUANPUBLIC');			 	  
	 });
	 //alert('mmmmmmmmmmmmm');	
</script>
#end

