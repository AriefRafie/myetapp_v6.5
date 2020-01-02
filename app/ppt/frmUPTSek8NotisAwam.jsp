#parse("app/ppt/frmLabelSet.jsp")
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#set($flag1 = "")
#set($flag2 = "")
#set($flag3 = "")
	
#foreach($dt in $listNotis)
#set($flag1 = $dt.flag1)
#set($flag2 = $dt.flag2)
#set($flag3 = $dt.flag3)
#end

#if($flag1 == "1" && $flag2 == "1" && $flag3 == "1")
 	#set($hide="1")
 	#set($hideNoItem="readonly")
 	#set($hideNoItem1="disabled")
 	#set($hideNoItemx="class=disabled")
#else
 	#set($hide="0")
 	#set($hideNoItem="")
 	#set($hideNoItem1="")
 	#set($hideNoItemx="")
#end

#parse("app/ppt/frmPPTHeaderHM.jsp")

<br/>

<fieldset id="top">

<legend><strong>Maklumat Notis Borang E Yang Terlibat</strong></legend>
	
		 #if($mode=="new")
    	 <table width="100%" border="0">  
        	
        	<!-- <tr>
        	  <td>#if($hide=="0")<font color="red">*</font>#end</td>
              <td width="25%">Notis</td>
              <td width="1%">:</td>
              <td width="73%"><input type="text" $hideNoItem $hideNoItemx name="txtNotis" id="txtNotis" value="" size="30" maxlength="100"   /></td>
          	</tr> -->
        	
            <tr>
            	<td width="1%">#if($hide=="0")<font color="red">*</font>#end</td>
            	<td width="24%">Tarikh Dikeluarkan / Endorsan</td>
            	<td width="1%">:</td>
                <td width="74%"><input $hideNoItem $hideNoItemx name="txdTarikhKeluar" id="txdTarikhKeluar" size="11" type="text" value="$!txdTarikhKeluar" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($hide=="0")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhKeluar',false,'dmy');">&nbsp;$!frmtdate#end</td>
            </tr>
            
            <!-- <tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Notis Diterima (Jika perlu)</td>
            	<td>:</td>
                <td><input $hideNoItem $hideNoItemx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($hide=="0")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
            </tr> -->
            
            
            <!-- <tr>
            	<td>&nbsp;</td>
            	<td>Slip Bukti Penyampaian</td>
            	<td>:</td>
  				<td><input id="fileupload" name="fileupload" type="file" size="50" /></td>
  			</tr> -->
            
        </table>
        #end
        
        
        
        #if($mode=="view")
        
        #foreach($data in $dataNotis)
        	#set($txtNotis=$data.notis)
        	#set($txdTarikhKeluar=$data.tarikh_keluar)
        	#set($txdTarikhTerima=$data.tarikh_terima)
        	#set($txdTarikhTampal=$data.tarikh_tampal)
        	#set($socTempatTampal=$data.jenis_tempat_tampal)
        	#set($txtTempat=$data.tempat)
        #end
        
        #if($isEdit=="no")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
		#else
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")
		#end
	
    	 <table width="100%" border="0">  
        	
        	<!-- <tr>
        	  <td>#if($isEdit=="yes")<font color="red">*</font>#end</td>
              <td width="25%">Notis</td>
              <td width="1%">:</td>
              <td width="73%"><input $disability $disabilityx type="text" name="txtNotis" id="txtNotis" value="$!txtNotis" size="30" maxlength="100"   /></td>
          	</tr> -->
        	
            <tr>
            	<td width="1%">#if($isEdit=="yes")<font color="red">*</font>#end</td>
            	<td width="24%">Tarikh Dikeluarkan / Endorsan</td>
            	<td width="1%">:</td>
                <td width="74%"><input $disability $disabilityx name="txdTarikhKeluar" id="txdTarikhKeluar" size="11" type="text" value="$!txdTarikhKeluar" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhKeluar',false,'dmy');">&nbsp;$!frmtdate#end</td>
            </tr>
            
            <!-- <tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Notis Diterima (Jika perlu)</td>
            	<td>:</td>
                <td><input $disability $disabilityx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
            </tr> -->
            
            
            <!-- <tr>
            	<td>&nbsp;</td>
            	<td>Slip Bukti Penyampaian</td>
            	<td>:</td>
  				<td><input $disability1 $disabilityx id="fileupload" name="fileupload" type="file" size="50" /></td>
  			</tr> -->
            
            
        </table>
        #end
        
</fieldset>

<fieldset>

	#if($mode=="new")
	<table width="100%" border="0"> 
		<tr>
            <td width="1%">#if($hide=="0")	<font color="red">*</font>#end</td>
            <td width="24%">Tarikh Tampal</td>
            <td width="1%">:</td>
            <td width="74%"><input $hideNoItem $hideNoItemx name="txdTarikhTampal" id="txdTarikhTampal" size="11" type="text" value="$!txdTarikhTampal" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this);checkTarikhSiasatan()" >
            #if($hide=="0")	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTampal',false,'dmy');">&nbsp;$!frmtdate#end</td>
        </tr>
		<tr>
        	<td>#if($hide=="0")<font color="red">*</font>#end</td>
            <td>Tempat Tampal</td>
            <td>:</td>
            <td><select name="socTempatTampal" $hideNoItem1 $hideNoItemx style="width:auto">
      		
      			<option selected value="">SILA PILIH</option>    
      			#if($flag1!="1")<option value="1">PTD / PTG</option>#end	
      			#if($flag2!="1")<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>#end	
      			#if($flag3!="1")<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>#end	
      		
			</select></td>
      	</tr>
      	<tr>
			<td valign="top">#if($hide=="0")<font color="red">*</font>#end</td>
			<td valign="top">Keterangan Tempat</td>
			<td valign="top">:</td>
			<td valign="top"><textarea $hideNoItem $hideNoItemx name="txtTempat" id="txtTempat" cols="40%" rows="4" onKeyUp="textCounter(this.form.txtTempat,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtTempat,this.form.remLen3,400);" ></textarea></td>
		</tr>
		#if($hide=="0")	
		<tr>
        	<td colspan="3">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="400"></td>
        </tr>  
        #end    
	</table> 
	#end
		
		
	#if($mode=="view")
	<table width="100%" border="0"> 
		<tr>
            <td width="1%">#if($isEdit=="yes")<font color="red">*</font>#end</td>
            <td width="24%">Tarikh Tampal</td>
            <td width="1%">:</td>
            <td width="74%"><input $disability $disabilityx name="txdTarikhTampal" id="txdTarikhTampal" size="11" type="text" value="$!txdTarikhTampal" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTampal',false,'dmy');">&nbsp;$!frmtdate#end</td>
        </tr>
		<tr>
        	<td>&nbsp;</td>
            <td>Tempat Tampal</td>
            <td>:</td>
            <td><select name="socTempatTampalTemp" disabled class="disabled" style="width:auto">
      			
      			#if($socTempatTampal=="1")
      			<option value="1">PTD / PTG</option>	
      			<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>				
      			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>	
      			<option value="">SILA PILIH</option>  
      			#elseif($socTempatTampal=="2")
      			<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>	
      			<option value="1">PTD / PTG</option>			
      			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>	
      			<option value="">SILA PILIH</option>  
      			#elseif($socTempatTampal=="3")
      			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>  
      			<option value="1">PTD / PTG</option>	
      			<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>		
      			<option value="">SILA PILIH</option>  				
      			#else
      			<option value="">SILA PILIH</option>    
      			<option value="1">PTD / PTG</option>	
      			<option value="2">PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR</option>				
      			<option value="3">TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH</option>	
      			#end
      			
			</select></td>
      	</tr>
      	<tr>
			<td valign="top">#if($isEdit=="yes")<font color="red">*</font>#end</td>
			<td valign="top">Keterangan Tempat</td>
			<td valign="top">:</td>
			<td valign="top"><textarea $disability $disabilityx name="txtTempat" id="txtTempat" cols="40%" rows="4" onKeyUp="textCounter(this.form.txtTempat,this.form.remLen3,400);" onKeyDown="textCounter(this.form.txtTempat,this.form.remLen3,400);" >$!txtTempat</textarea></td>
		</tr>
		#if($isEdit=="yes")
		<tr>
        	<td colspan="3">&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="400"></td>
        </tr>       
        #end
	</table> 
	
	<input type="hidden" name="socTempatTampal" value="$!socTempatTampal">
	
	#end	
	
	#if($mode=="new" && $hide=="0" || ($mode=="view" && $isEdit=="yes"))	
	<br/>
	<table width="100%" align="left">
		<tr>
			<td>$perhatian5</td>
		</tr>
	</table>
	#end	
		
</fieldset>

	
	<table width="80%" border="0" align="left">
		<tr align="center">
			<td>
			#if($mode=="new" && $hide=="0")	
			<input type="button" value="Simpan" name="cmdSimpan" onClick="javascript:simpanNotis('$!id_hakmilik','$!id_notisawam','$!mode')"> 
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniNotis()">
				<input type="button" name="cmdHapus" value="Hapus" onClick="hapusNotis('$!id_notisawam')" >
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanNotis('$!id_hakmilik','$!id_notisawam','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batal('$!id_notisawam')">
				#end
			#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListNotis('$!id_permohonan')">
			</td>
		</tr>
	</table>

<br/>
<br/>

	<fieldset id="bottom">
	<legend><strong>Maklumat Notis Borang E Yang Terlibat</strong></legend>
	
		#if($mode=="view" && $hide=="0")
		<table width="100%" border="0">   
              <tr>
                 <td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahNotis('$!id_hakmilik');"></td>
              </tr>
    	</table>
		#end
		
		#if($saiz_listNotis > 5)
                <div style="width:100%;height:100;overflow:auto"> 
        #end
	
		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Tempat Tampal</b></td>
        			<td><b>Tarikh Tampal</b></td>
        			<td><b>Tarikh Dikeluarkan/Endosan</b></td>
        			<!-- <td><b>Tarikh Notis Diterima</b></td> -->
            		<!-- <td><b>Slip Bukti Penyampaian</b></td> -->
        		</tr>
        		
        		#if($saiz_listNotis!=0)
                    #foreach($list in $listNotis)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!list.bil</td>
                   <td class="$row"><a href="javascript:viewNotis('$!list.id_notisawam')"><font color="blue">$!list.jenis_tempat</font></a></td>
                   <td class="$row">$!list.tarikh_tampal</td>
                   <td class="$row">$!list.tarikh_keluar</td>
                   <!-- <td class="$row">$!list.tarikh_terima</td> -->
                   <!-- <td class="$row"><a href="javascript:viewLampiran('$!list.id_dokumen')"><font color="blue">$!list.nama_fail</font></a></td> -->
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		</table>
			
		#if($saiz_listNotis > 5)
        	</div>
        #end	
		
	</fieldset>
	
	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_notisawam" value="$!id_notisawam">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="lblTarikhSiasatan" id="lblTarikhSiasatan" value="$!lblTarikhSiasatan">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

#if($showAlertPaging=="yes" && $saiz_listNotis==1)
<script>
alert("Sila Pastikan Ketiga-tiga Tempat Tampal Dipilih dan Klik 'Paging' No.14 Untuk Bukti Penyampaian Borang E");
</script>
#end


<script>
function checkTarikhSiasatan(){	

	var dat1=document.${formName}.txdTarikhTampal
	var dat2=document.${formName}.lblTarikhSiasatan
	var tarikhs=document.${formName}.lblTarikhSiasatan.value;

	//tarikh siasatan
	var TS  = document.getElementById("lblTarikhSiasatan").value;
	var dt1   = parseInt(TS.substring(0,2),10)+21;
   	var mon1  = parseInt(TS.substring(3,5),10)-1;
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateSiasatan = new Date(yr1, mon1, dt1);

  	//tarikh surat
	var TSR  = document.getElementById("txdTarikhTampal").value;
	var dt2   = parseInt(TSR.substring(0,2),10);
   	var mon2  = parseInt(TSR.substring(3,5),10)-1;
   	var yr2   = parseInt(TSR.substring(6,10),10);
   	var dateTampal = new Date(yr2, mon2, dt2);
   	
   	if((dat1.value!="" && dat2.value!="") && (isDate(dat1.value)==true) && (dateTampal > dateSiasatan)){
	   	alert("Sila pastikan \"Tarikh Tampal\" tidak kurang 21 hari dari tarikh siasatan iaitu pada "+tarikhs+".");
		document.${formName}.txdTarikhTampal.focus();
		return;
	}
	
}

function batal(idNotisAwam){	
	if ( !window.confirm("Adakah Anda Pasti?")) return;	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_notisawam.value = idNotisAwam;
	document.${formName}.command.value = "viewNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function kemaskiniNotis(){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewNotis";
	document.${formName}.command2.value = "kemaskiniNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function hapusNotis(idNotisAwam){	
	if ( !window.confirm("Adakah Anda Pasti?")) return;	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_notisawam.value = idNotisAwam;
	document.${formName}.command.value = "hapusNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function viewNotis(idNotisAwam){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_notisawam.value = idNotisAwam;
	document.${formName}.command.value = "viewNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function viewListNotis(id_permohonan) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function simpanNotis(idHakmilik,idNotisAwam,mode){	

	var dat1=document.${formName}.txdTarikhKeluar
	var dat3=document.${formName}.txdTarikhTampal
	
	var dat2=document.${formName}.lblTarikhSiasatan
	var tarikhs=document.${formName}.lblTarikhSiasatan.value;
	
	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat
	var TS  = document.getElementById("txdTarikhTampal").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10)-1;
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateTampal = new Date(yr1, mon1, dt1);

  	//tarikh siasatan
	var TSt  = document.getElementById("lblTarikhSiasatan").value;
	var dt2   = parseInt(TSt.substring(0,2),10)+21;
   	var mon2  = parseInt(TSt.substring(3,5),10)-1;
   	var yr2   = parseInt(TSt.substring(6,10),10);
   	var dateSiasatan = new Date(yr2, mon2, dt2);
   	
/*	if(document.${formName}.txtNotis.value == ""){
		alert("Sila masukkan \"Notis\" terlebih dahulu.");
  		document.${formName}.txtNotis.focus(); 
		return;
	}
*/	
	if(document.${formName}.txdTarikhKeluar.value == ""){
		alert("Sila masukkan \"Tarikh Endorsan\" terlebih dahulu.");
	  	document.${formName}.txdTarikhKeluar.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		//return;
	}
	else
	if(document.${formName}.txdTarikhTampal.value == ""){
		alert("Sila masukkan \"Tarikh Tampal\" terlebih dahulu.");
		document.${formName}.txdTarikhTampal.focus(); 
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		//return;
	}
	else if((dat3.value!="" && dat2.value!="") && (isDate(dat3.value)==true) && (dateTampal > dateSiasatan)){
	   	alert("Sila pastikan \"Tarikh Tampal\" tidak kurang 21 hari dari tarikh siasatan iaitu pada "+tarikhs+".");
		document.${formName}.txdTarikhTampal.focus();
		return;
	}
	else
	if(document.${formName}.socTempatTampal.value == ""){
		alert("Sila pilih \"Tempat Tampal\" terlebih dahulu.");
		document.${formName}.socTempatTampal.focus(); 
		return;
	}
	else
	if(document.${formName}.txtTempat.value == ""){
		alert("Sila masukkan \"Tempat\" terlebih dahulu.");
		document.${formName}.txtTempat.focus(); 
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;	
		var TK = document.${formName}.txdTarikhKeluar.value ;
		var dopost = document.${formName}.form_token.value ;

		var item = "";
		if(mode=="new"){
			item = "&command=tambahNotis&command2=simpanNotis&id_hakmilik="+idHakmilik+"&form_token="+dopost+"&txdTarikhKeluar="+TK;
		}else{
			item = "&command=viewNotis&command2=kemaskiniNotis&command3=updateNotis&id_notisawam="+idNotisAwam+"&id_hakmilik="+idHakmilik+"&form_token="+dopost+"&txdTarikhKeluar="+TK;
		}

		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam"+item;
		document.${formName}.submit();		
	}
}
function viewLampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function tambahNotis(idHakmilik){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
</script>

<script>
//Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
		return false
	}
return true
}
</script>