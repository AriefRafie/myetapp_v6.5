<span class="font_tajuk_sub"  style="cursor:pointer" >
#set($ID_FKDOC = $ID_WARTATRM)
#if($TEMPID_WARTATRM != "")
	#set($ID_FKDOC = $TEMPID_WARTATRM)
#end

            		<u onClick="paparDoc($ID_FKDOC,'$type');">
            		#if($type=="WARTA")
            			$view.NAMA_FAIL_WARTA
            		#else
            			$view.NAMA_FAIL_PELAN
            		#end
            		</u>
</span> 


#if($TEMPID_WARTATRM != "")
<script>
//x
//TEMPID_WARTATRM_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM
document.getElementById('TEMPID_WARTATRM_$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM').value = '$TEMPID_WARTATRM';
</script>
#end