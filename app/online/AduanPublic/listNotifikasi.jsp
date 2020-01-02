<table border="0" cellpadding="1" cellspacing="1" width="100%">
<tr><td width="2%"></td><td width="1%"></td><td></td></tr>
#set($countNoti = 0)
#if($listNotifikasi.size()>0)    
    #foreach ( $N in $listNotifikasi)
        #if(
        (($N.ID_STATUS == "16121" || $N.ID_STATUS == "16127" || $N.ID_STATUS == "16123") && $USER_ROLE == "user_unit_intergriti")
        ||
        (($N.ID_STATUS == "16126") && $USER_ROLE == "wakil_bahagian_aduan")
        ||
        (($N.ID_STATUS == "16122" || $N.ID_STATUS == "16123" || $N.ID_STATUS == "16127") && ($USER_ROLE == "online" || $USER_ROLE == "php-online-user" || $USER_ROLE == "ppk-online-user" || $USER_ROLE == "ppt-online-user"))
        )
        #set($countNoti = $countNoti + 1)
        <tr onclick="doDivAjaxCall3$formname('div_Senarai','showSenarai','OPENCLOSE_CARIAN=close&FLAG_CARIAN=N&FLAG_NOTIFIKASI=Y&ID_STATUS_NOTI=$N.ID_STATUS&ID_TERIMA_NOTI=$N.ID_TERIMA');" style="cursor:pointer"  title="$N.KETERANGAN">
        <td >
        <div style="width:100%; background-color:blue" align="center" valign="top" class="blink" > 
        <b><font color="WHITE">$N.CNT</font></b>
        </div>
        </td>
        <td></td>
        <td>$N.KETERANGAN</td>
        </tr> 
        #end                                            
    #end   
#end
#if($FLAG_NOTIFIKASI == "Y")
	<tr><td colspan="5"  align="left">
    <input type="button" id="cmdTutup" name="cmdTutup" value="$button_reset" 
				onClick="doDivAjaxCall$formname('div_Senarai','showSenarai','OPENCLOSE_CARIAN=close&FLAG_CARIAN=N');" >
    </td></tr>
#end
#if($countNoti > 0)
    <tr><td colspan="5" style="border-bottom: 1px solid black;"></td></tr>
#end
</table>



