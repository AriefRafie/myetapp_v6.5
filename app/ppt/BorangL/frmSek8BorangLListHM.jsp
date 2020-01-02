#parse("app/ppt/Sek8Paging.jsp")

<fieldset id="top">
<legend><strong>Borang L</strong></legend>
<center>

	#parse("app/ppt/frmPPTHeader.jsp")
	
	#if($isEdit=="no")
		#set($disability = "readonly class=disabled")
	#else
		#set($disability = "")
	#end
	
    
    
	
<br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
    
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianHakmilik('$id_permohonan','hakmilik_borangL')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
    </td>
    </tr>
    </table>
    
			
			<!--
            #parse("app/ppt/frmCarianListHMSek8.jsp")
    			
    	
            #if($saiz_listTanah > 20)
            <div style="width:100%;height:400;overflow:auto"> 
            #end
      
            <table width="100%"  cellpadding="0" cellspacing="2" border="0">     
                    
                    <tr class="table_header">
                  		<td align="center"><b>No</b></td>
                  		<td><b>No.LOT/No.PT</b></td>
                  		<td><b>No.Hakmilik</b></td>
                  		#if($!flag_subjaket!="")<td><b>No.Subjaket</b></td>#end
                  		<td><b>Tarikh Borang L</b></td>
                        <td><b>Tempoh (Bil. Hari)</b></td>
                  		<td><b>Status Borang L</b></td>
                        <td><b>Cetak Borang L</b></td>
           		 	</tr>
                    
   					#if($saiz_listTanah!=0)
                    	#foreach($listTanah in $listMaklumatTanah)
                    	#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              		 		#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    	
                    	
                    <tr>
                		<td class="$row" align="center">$!listTanah.bil</td>
                		<td class="$row">$!listTanah.no_lotpt</td>  
                		<td class="$row">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
                		#if($!flag_subjaket!="")
                		<td class="$row">Sj.$!listTanah.no_subjaket</td>
                		#end
                		<td class="$row">
                		#if($isEdit=="yes")
                   		#set($dateName = "txdTarikhBorangL"+$!listTanah.bil)
                   		<input name="$!dateName" id="$!dateName" size="11" type="text" value="$!listTanah.tarikh_borangl" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	  		<img src="../img/calendar.gif" onclick="displayDatePicker('$!dateName',false,'dmy');">
            	  		#set($idBL = "id_borangl"+$!listTanah.bil)
            	  		<input type="hidden" name="$!idBL" value="$!listTanah.id_borangl">
            	  		<input type="hidden" name="id_hakmilik" value="$!listTanah.id_hakmilik">
            			#else
            			$!listTanah.tarikh_borangl
            			#end
            			</td>
                        <td class="$row">
                        #if($isEdit=="yes")
                        #set($txtTempohName = "txtTempoh"+$!listTanah.bil)
                        <input name="$!txtTempohName" type="text" id="$!txtTempohName" onBlur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value)" value="$!listTanah.tempoh" size="10" maxlength="10" e>
                        #else
            			$!listTanah.tempoh
            			#end
            			</td>
                        
                		<td class="$row">
                		#if($isEdit=="yes")
                		#set($statusBL = "sorStatusBorangL"+$!listTanah.bil)
                		<select name="$!statusBL" style="width:170px">
      						<option value="" #if($!listTanah.jenis_pilih=="") selected=selected #end >Sila Pilih</option>	
			      			<option value="1" #if($!listTanah.jenis_pilih=="1") selected=selected #end>Hakmilik Belum Diterima</option>
			      			<option value="2" #if($!listTanah.jenis_pilih=="2") selected=selected #end>Hakmilik Telah Diterima</option>	
			      		</select>      
			      		#else
			      		$!listTanah.status_borang_l
			      		#end     		
                		</td>  
                        <td class="$row">
                        #if($!listTanah.tarikh_borangl != "")
                        
                        <input type="button" name="cmdCetakBorangL" value ="Cetak" onClick="javascript:cetakBorangL($!listTanah.id_hakmilik,$!id_fail,$!id_permohonan,'$!listTanah.tarikh_borangl','$!listTanah.tempoh')">
                        #end                        
                        </td>
            		</tr>
                    	#end
                    	
                    #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
                    #end
                    
                </table>
            
            #if($saiz_listTanah > 20)
            </div>
            #end
	-->
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
            <!--
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangL('$!id_permohonan')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangL('$!id_permohonan')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_permohonan')">
				#end
                -->
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">			
			</td>
		</tr>
	</table>

</center>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>

function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilikBorangL?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}



function cetakBorangL(id_hakmilik,id_fail,id_permohonan,tarikhBorangL,tempohBL) {
	
	//alert("tarikhBorangL : "+tarikhBorangL);

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_hakmilik="+id_hakmilik+"&id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&report=BorangL&selectNoFail=yes&tarikhBorangL="+tarikhBorangL+"&tempohBL="+tempohBL;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function simpanBorangL(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.command2.value = "kemaskiniBorangL";
	document.${formName}.command3.value = "simpanBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangL";
	document.${formName}.submit();
}
function batalKemaskini(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangL";
	document.${formName}.submit();
}
function kemaskiniBorangL(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.command2.value = "kemaskiniBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangL";
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
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangL";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangL";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangL";
	document.${formName}.submit();
}
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
</script>