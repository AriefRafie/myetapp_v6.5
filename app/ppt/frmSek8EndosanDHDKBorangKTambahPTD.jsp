
#set($frmtdate = "&nbsp;<i><font style='font-size:10px'>dd/mm/yyyy</font></i>")

#if($showAlertPaging=="yes")
<script>
alert("Sila Klik Kembali dan Klik 'Paging' No.21 Untuk Bukti Penyampaian Borang K");
</script>
#end



#if($mode=="new")
<fieldset id="top" style="width:70%">
<legend><strong>Maklumat Pejabat PTD</strong></legend>
	<table width="100%" border="0">
		<tr>
			<td width="1%"><font color="red">*</font></td>
			<td width="25%">Nama Pejabat</td>
			<td width="1%">:</td>
			
			#if($saiz_listHakmilikPTD != 0)
			<td width="73%">$!selectPejabatPTD</td>
			#else
			<td width="73%">$!selectPejabatPTDX</td>
			#end
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top">Catatan</td>
			<td valign="top">:</td>
			<td><textarea name="txtCatatanPTD" id="txtCatatanPTD" rows="4" cols="60%" onKeyUp="textCounter(this.form.txtCatatanPTD,this.form.remLen99,4000);" onKeyDown="textCounter(this.form.txtCatatanPTD,this.form.remLen99,4000);" ></textarea></td>
		</tr>
	</table>
</fieldset>	

<br/>

<fieldset id="bottom">
<legend><strong>Senarai Hakmilik</strong></legend>

    		#if($saiz_listHakmilikPTD > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
            
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			#if($saiz_listHakmilikPTD != 0)
        			<td align="center" width="4%"><b><input type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></b></td>
        			#end
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Bandar/Pekan/Mukim</b></td>
        			<td><b>Jenis Hakmilik</b></td>
        			<td><b>No.Hakmilik</b></td>
        			<td><b>Tarikh Endorsan $!frmtdate</b></td>
        		</tr>
        		
        		#if($saiz_listHakmilikPTD!=0)
                    #foreach($listTanah in $listHakmilikPTD)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
               	   #if($saiz_listHakmilikPTD != 0)
               	   <td class="$row" align="center"><input type="checkbox" name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>
                   #end
                   <td class="$row" align="center">$!listTanah.bil</td>
                   <td class="$row">$!listTanah.nama_mukim</td>
                   <td class="$row">$!listTanah.jenis_hakmilik</td>
                   <td class="$row">$!listTanah.no_hakmilik</td>
                   <td class="$row">
                    #set($dateName = "txdTarikhCatatan"+$!listTanah.bil)
                   	<input name="$!dateName" id="$!dateName" size="11" type="text" value="$!listTanah.tarikh_catatan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	  	<img src="../img/calendar.gif" onclick="displayDatePicker('$!dateName',false,'dmy');">
            		<input type="hidden" name="id_endosanborangk" value="$!listTanah.id_endosanborangk"></td>
                <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listHakmilikPTD > 5)
                </div>
            #end
            
</fieldset>
#end


#if($mode=="view")

		#foreach($data in $dataHeader)
			#set($txtCatatanPTD=$data.catatan_borangk_ptd)
		#end
		
		#if($isEdit=="no")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
			#set($M = "")
		#else
			#set($M = "*")
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")
		#end

<fieldset id="top" style="width:70%">
<legend><strong>Maklumat Pejabat PTD</strong></legend>
	<table width="100%" border="0">
		<tr>
			<td width="1%"><font color="red">$!M</font></td>
			<td width="25%">Nama Pejabat</td>
			<td width="1%">:</td>
			<td width="73%">$!selectPejabatPTD</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign="top">Catatan</td>
			<td valign="top">:</td>
			<td><textarea name="txtCatatanPTD" $disability $disabilityx id="txtCatatanPTD" rows="4" cols="60%" onKeyUp="textCounter(this.form.txtCatatanPTD,this.form.remLen99,4000);" onKeyDown="textCounter(this.form.txtCatatanPTD,this.form.remLen99,4000);" >$!txtCatatanPTD</textarea></td>
		</tr>
	</table>
</fieldset>

<br/>

<fieldset id="bottom">
<legend><strong>Senarai Hakmilik</strong></legend>

    		#if($saiz_listHakmilikPTD > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			#if($saiz_listHakmilikPTD != 0)
        			<td align="center" width="4%"><b><input $disability1 type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></b></td>
        			#end
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Bandar/Pekan/Mukim</b></td>
        			<td><b>Jenis Hakmilik</b></td>
        			<td><b>No.Hakmilik</b></td>
        			<td><b>Tarikh Endorsan $!frmtdate</b></td>
        		</tr>
        		
        		#if($saiz_listHakmilikPTD!=0)
                    #foreach($listTanah in $listHakmilikPTD)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
         			
         			#if($listTanah.flag_endosan == "2")
         				#set($checked = "checked")
         			#else
         				#set($checked = "")
         			#end
                    
               <tr>	
               	   #if($saiz_listHakmilikPTD != 0)
               	   <td class="$row" align="center">#if($listTanah.flag_hantar_htp != "1")<input $disability1 type="checkbox" $checked name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik">#end</td>
                   #end
                   <td class="$row" align="center">$!listTanah.bil</td>
                   <td class="$row">$!listTanah.nama_mukim</td>
                   <td class="$row">$!listTanah.jenis_hakmilik</td>
                   <td class="$row">$!listTanah.no_hakmilik</td>
                   <td class="$row">
                   	#set($dateName = "txdTarikhCatatan"+$!listTanah.bil)
                   	<input $disability $disabilityx name="$!dateName" id="$!dateName" size="11" type="text" value="$!listTanah.tarikh_catatan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	  	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('$!dateName',false,'dmy');">#end
            		<input type="hidden" name="id_endosanborangk" value="$!listTanah.id_endosanborangk"></td>
                <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listHakmilikPTD > 5)
                </div>
            #end
            
</fieldset>
#end
	

	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($mode=="new")
				#if($saiz_listHakmilikPTD != 0)
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPTD('$!id_permohonan','$!saiz_listHakmilikPTD','$!mode')">
				#end
				#end
				
				#if($mode=="view")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPTD('$!id_permohonan')">
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPTD('$!id_permohonan','$!saiz_listHakmilikPTD','$!mode')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batal('$!id_permohonan')">
					#end
				#end				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewEndosan('$!id_permohonan')">
				
			</td>
		</tr>
	</table>


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
function batal(id_permohonan) {

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "tambahPTD";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK";
	document.${formName}.submit();
}
function kemaskiniPTD(id_permohonan) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "tambahPTD";
	document.${formName}.command2.value = "kemaskiniPTD";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK";
	document.${formName}.submit();
}
function simpanPTD(idpermohonan,size,mode){


	var checkSelected=false;
	if(size>1){
		for(var i=0 ; i < document.${formName}.cbsemaks.length; i++) 
		{ 
    		if (document.${formName}.cbsemaks[i].checked)
        	{
  				checkSelected=true; 
  			}
		}
	}else{
		if (document.${formName}.cbsemaks.checked)
    	{
			checkSelected=true; 
    	}
	}

	if(checkSelected && document.${formName}.socPejabatPTD.value == ""){
		alert("Sila pilih \"Nama Pejabat\" terlebih dahulu.");
  		document.${formName}.socPejabatPTD.focus(); 
		return;
	}
/*	else if(!checkSelected)
	{
		alert("Sila Pilih Hakmilik Terlebih Dahulu.");
		return;
	}
*/	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "tambahPTD";
			document.${formName}.command2.value = "simpanPTD";
		}else{
			document.${formName}.command.value = "tambahPTD";
			document.${formName}.command2.value = "kemaskiniPTD";
			document.${formName}.command3.value = "updatePTD";
		}		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK";
		document.${formName}.submit();
	}
}
function viewEndosan(id_permohonan) {

	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewEndosan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK";
	document.${formName}.submit();
}
</script>

<script language="javascript">
var checked = false;
function checkALL() {

	 if (document.${formName}.checkall.checked == true){
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = true;
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = true;
	            }
	        }
	    } else {
	        if (document.${formName}.cbsemaks.length == null){
	            document.${formName}.cbsemaks.checked = false;
	            
	        } else {
	            for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	                document.${formName}.cbsemaks[i].checked = false;
	            }
	       
	        }
	    }
}
function doUpdateCheckAll(){  

	var c = 0;
	if(document.${formName}.cbsemaks.length > 1){     
		
		for (i = 0; i < document.${formName}.cbsemaks.length; i++){
	      if (document.${formName}.cbsemaks[i].checked == false){	 
		  	c++
	      }
		}  

	}else{
		
		if (document.${formName}.cbsemaks.checked == false){				 
			c++;
		}	 	
	}	 
	 
	if(c>0){
			  
		document.${formName}.checkall.checked = false;
	
	}
	else{
		document.${formName}.checkall.checked = true;
	}       
}
</script>


<script>
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>