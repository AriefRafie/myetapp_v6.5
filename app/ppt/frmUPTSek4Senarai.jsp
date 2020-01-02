#parse("app/ppt/Sek4Paging.jsp")

<br/>
<fieldset id="top">
	<legend><strong>Urusan MMK/MB Seksyen 4</strong></legend>  

	#parse("app/ppt/frmPPTHeader.jsp")
  
<br/>
    

   <fieldset id="bottom">
   	<legend><strong>Kertas Kerja MMK</strong></legend>  
     <table width="100%">
         	<tr>
            <td>
            	#if($saiz_list < 1)
            	<input type=button name="cmdBaru" value ="Kemasukan" onClick="javascript:kemasukanMMKBaru('$!id_permohonan');">
            	#end
            	<!-- <input type=button style="display:none;" name="cmdBarux" id="cmdBarux" value="Kemasukan" onClick="javascript:kemasukanMMKBaru('$!id_permohonan');"> -->
            </td>
            </tr>   
     </table>
     
      <table width="100%" cellspacing="2" cellpadding="0" border="0">
 		 <tr class="table_header" >
     	
     		<td align="center"><b>No</b></td>
        	<td><b>No. Ruj Kertas</b></td>
            <td><b>Tarikh Kelulusan MMK / Tarikh Mesyuarat Bersidang</b></td>
            <td><b>Keputusan</b></td>
            <!-- <td><b>Tarikh Warta</b></td>
            <td><b>Tarikh Luput</b></td> -->
            <!-- <td><b>Status</b></td> -->
        </tr>
        
	#set($showHantar = "no") 
    
    #set($id_papar_mmk = '')
    
       	#if($saiz_list!=0)
        #foreach($senarai in $senaraiKertas)
        	 
        	 #if($senarai.tarikh_keputusan != "")
        	 	#set($showHantar = "yes")
        	 #else
        	 	#set($showHantar = "no")
        	 #end
        	 
       		 #set($idMMK = $senarai.id_mmk)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		  
        #set($id_papar_mmk = $senarai.id_mmk)
         		  
         		  
        <tr>
       		<td class="$row" align="center">$!senarai.bil</td>
        	<td class="$row"><a href="javascript:viewKertasMMK('$senarai.id_mmk')"><font color="blue">$!senarai.no_rujmmk</font></a></td>
        	<td class="$row">$!senarai.tarikh_mmk</td>
        	<td class="$row">$!senarai.status_keputusan</td>
        	<!-- <td class="$row">$!senarai.tarikh_keputusan</td>
        	<td class="$row">$!senarai.tarikh_luput</td> -->
        	<!-- <td class="$row">$!senarai.flag_semak</td>	 -->
        </tr>
        #end
        #else
        <tr>
       		<td colspan="7">Tiada rekod</td>
        </tr>
        #end
     </table>
     

 </fieldset>
 	<table width="100%" cellspacing="0" cellpadding="0" >  
 	<tr align="center">
 		<td>
 		
 		<!-- #if($showHantar=="yes")
 			#if($id_status!="31")
 				<input name="cmdSeterusnya" type="button" value="Seterusnya" onClick="Hantar('$id_permohonan')">	
 			#end
 		#end -->
 		#if($id_papar_mmk!='')
 		<input name="cmdPaparMMK" type="button" value="Papar Kertas MMK" onClick="viewKertasMMK('$id_papar_mmk')">
 		#end
 		<input name="cmdKembali" type="button" value="Kembali" onClick="KembaliList()">
 		</td>
 	</tr>
 	</table>
</fieldset>            


<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_mmk" value="$!idMMK">
<input type="hidden" name="id_status" value="$!id_status">

<!-- <input type="hidden" name="tarikh_luput" id="tarikh_luput" value="$!tarikh_luput"> -->


<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
/*
window.onload=function()
{

	//tarikh luput
	var TL  = document.getElementById("tarikh_luput").value;

	var dt1   = parseInt(TL.substring(0,2),10);
   	var mon1  = parseInt(TL.substring(3,5),10)-4;
   	var yr1   = parseInt(TL.substring(6,10),10);
   	var reminderTarikhLuput = new Date(yr1, mon1, dt1);

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);
	
	if(currentDate > reminderTarikhLuput){
   		alert("Kertas kerja MMK hampir luput.");
		document.getElementById("cmdBarux").style.display="block";
  		return;
	}
}
*/
function kemasukanMMKBaru(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "kemasukanMMKBaru";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.submit();
}
function viewKertasMMK(id_mmk) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_mmk.value = id_mmk;
	document.${formName}.command.value = "viewKertasMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.submit();
}
function Hantar(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai"; 
	document.${formName}.submit();	
}
function KembaliList() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.command.value = "xxx";
	document.${formName}.submit();
}

</script>

<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>