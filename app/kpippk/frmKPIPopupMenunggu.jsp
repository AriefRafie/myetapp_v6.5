
<br/>
<center>

<fieldset>
<legend><strong>Senarai Fail : $!tajukPopup</strong></legend>
	
	<table width="100%" border="0">
		<tr>
			<td><input name="cmdKembali" value="Kembali" type="button" onclick="javascript:window.close()" /></td>
		</tr>
	</table>
	
	#if($saiz_listFail > 10)
    <div style="width:100%;height:285;overflow:auto"> 
    #end	
    			
    <table width="100%" border="0"> 
  
        <tr class="table_header">
        	<td align="center"><b>BIL</b></td>
        	<td><b>NO FAIL</b></td>            
            <td><b>STATUS FAIL TERKINI</b></td>
            <td><b>BIL. HARI MENUNGGU</b></td>
        </tr>
        		
        #if($saiz_listFail!=0)
           #foreach($list in $listFail)
              #set( $i = $velocityCount )
         	#if ( ($i % 2) != 1 )
              #set( $row = "row2" )
         	#else
              #set( $row = "row1" )
            #end
                    
        <tr>
            <td class="$row" align="center">$!list.bil</td>
            <td class="$row"><a href="javascript:gotoFail('$!list.id_permohonan','$!list.id_fail','$!list.id_status','$!list.seksyen','$!list.id_simati')"><font color="blue">$!list.no_fail</font></a></td>		
            <td class="$row">$!list.status</td>
            <td class="$row">$!list.bil_hari</td>				
        <tr>
           #end
        #else
        <tr>
            <td colspan="2">Tiada rekod</td>
        </tr>
        #end
	
	</table>
	
	#if($saiz_listFail > 10)
    </div>
    #end
	
</fieldset>
</center>


<!-- START HIDDEN VALUE -->

<!-- Main Id -->
<input type="hidden" name="id_suburusan" value="$!id_suburusan">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- Do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<!-- Others -->
<input type="hidden" name="command2">
<!-- END HIDDEN VALUE -->



<!-- START MAIN JAVASCRIPT -->
<script>
function gotoFail(id_permohonan,id_fail,id_status,seksyen,id_simati) {
	window.opener.gotoPage(id_permohonan,id_fail,id_status,seksyen,id_simati);
	window.close();
}
/*
function gotoFail(){

	
}
*/
</script>
<!-- END MAIN JAVASCRIPT -->


<!-- START OTHERS JAVASCRIPT -->
<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
</script>
<!-- END OTHERS JAVASCRIPT -->