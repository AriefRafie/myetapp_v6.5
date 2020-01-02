#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#if($mode=="new")
		
		#if($listMaklumatPB.size() != 0)
			#set($M = "*")
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")	
		#else
			#set($M = "")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
		#end
		
	#else
		
		#if($isEdit=="yes")
			#set($M = "*")
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")	
		#else
			#set($M = "")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
		#end	
		
		#foreach($data in $dataBorangFInBulk)
			#set($txtTempoh=$data.TEMPOH)
			#set($txtKeterangan=$data.ULASAN)			
		#end
	
	#end
	
<br/>

<fieldset id="top">
	<legend><strong>Senarai Pilihan Hakmilik</strong></legend>
    
     <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianHakmilik('$id_borange','$id_permohonan','kemasukan_borangF')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
    </td>
    </tr>
    </table>
<!--
     <table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
         <tr class="table_header">
        	<td align="center"><b>No</b></td>
            <td><b>No.Hakmilik</b></td>
           	<td><b>No.LOT/No.PT</b></td>              
            <td><b>Mukim/Pekan/Bandar</b></td>
            #if($!flag_subjaket!="")<td><b>No.Subjaket</b></td>#end
        </tr>
        
      #if($listHM.size()!=0)
           	#foreach($list in $listHM)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         	<tr>
        		<td class="$row" align="center">$!list.bil</td>
	            <td class="$row">
	            <a href="javascript:showBorangF('$!list.id_hakmilik')"><font color="blue">$!list.kod_jenis_hakmilik $!list.no_hakmilik</font></a>
	            </td>
				<td class="$row">$!list.no_lotpt</td>
	            <td class="$row">$!list.nama_mukim #if($list.seksyen!="")<font style=font-size:10px>Seksyen $list.seksyen</font>#end</td>
	       		#if($!flag_subjaket!="")<td class="$row">Sj.$!list.no_subjaket</td>#end
        	</tr>	      	
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
    -->
</fieldset>
	
	#if($showBorangF=="yes")
	
	#foreach($dataHM in $dataMaklumatTanah)
		#set($lblKodHakmilik = $dataHM.kod_jenis_hakmilik)
		#set($lblNoHakmilik = $dataHM.no_hakmilik)
		#set($lblNoLotPT = $dataHM.no_lotpt)
	#end
	
	<br/>
	<fieldset id="middle">
	<legend><strong>Maklumat Borang F</strong></legend>
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="20%">No.Hakmilik</td>
				<td width="1%">:</td>
				<td width="78%">$!lblKodHakmilik $!lblNoHakmilik</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>No.Lot</td>
				<td>:</td>
				<td>$!lblNoLotPT</td>
			</tr>
		</table>
	</fieldset>
	<fieldset>		
		<table width="100%" border="0">	
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="20%">Tempoh (Hari)</td>
				<td width="1%">:</td>
				<td width="78%"><input $disability $disabilityx type="text" name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="5" maxlength="3"  onblur="validateNumber(this,this.value)" onkeyup="validateNumber(this,this.value)" ></td>
			</tr>	
			<tr>
				<td>&nbsp;</td>
				<td valign="top">Keterangan</td>
				<td valign="top">:</td>
				<td valign="top"><textarea $disability $disabilityx name="txtKeterangan" id="txtKeterangan" cols="45%" rows="5" onKeyUp="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" onKeyDown="textCounter(this.form.txtKeterangan,this.form.remLen3,1500);" >$!txtKeterangan</textarea></td>
			</tr>
			<tr>
			    <td colspan="3">&nbsp;</td>
			    <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen3" size="3" maxlength="3" value="1500"></td>
			</tr>
		</table>
	
	<br/>
		
	<fieldset id="center">
	<legend><strong><font color="red">$M</font> Senarai Pilihan Pihak Berkepentingan</strong></legend>
   
    

	<table width="100%"  cellpadding="0" cellspacing="2" border="0">
		
		<tr class="table_header">
			#if($listMaklumatPB.size() != 0)
        	<td align="center" width="4%"><b><input $disability1 type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></b></td>
        	#end
       		<td align="center"><b>No</b></td>
            <td><b>Nama</b></td>
           	<td><b>No.PB</b></td>   
            <td><b>No.LOT/PT</b></td>
            <td><b>No.Hakmilik</b></td>
            #if($!flag_subjaket!="")<td><b>No.Subjaket</b></td>#end
        </tr>
             
   		#if($listMaklumatPB.size() != 0)
   			
      		#foreach($list in $listMaklumatPB)
            #set( $i = $velocityCount )
         	#if ( ($i % 2) != 1 )
           		#set( $row = "row2" )
	        #else
	            #set( $row = "row1" )
	       	#end
        
	        #if($list.SELECTEDCB > 0)
	        	#set($checkCB = "checked")
	        #else
	        	#set($checkCB = "")
	        #end
        	
        	
       	<tr>
       		#if($listMaklumatPB.size() != 0)
            <td class="$row" align="center"><input type="checkbox" $disability1 $checkCB name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!list.ID_HAKMILIKPB"></td>
            #end
           	<td class="$row" align="center">$!list.bil</td>
           	<td class="$row">$!list.NAMA_PB</td>
           	<td class="$row">#if($!list.NO_JENISPB!="")$!list.NO_JENISPB -#end $!list.NO_PB</td>
			<td class="$row">$!list.NO_LOTPT</td>     
			<td class="$row">$!list.KOD_JENIS_HAKMILIK $!list.NO_HAKMILIK</td>          
       		#if($!flag_subjaket!="")<td class="$row">Sj.$!list.NO_SUBJAKET</td>#end
        </tr>
        	#end
        #else
        <tr>
           	<td colspan="2">Tiada rekod</td>
       	</tr>
        #end  
        
    </table>	
                
	</fieldset>
		
		
	</fieldset>
	
	
	<table width="100%" border="0">
			<tr align="center">
				<td>
					#if($listMaklumatPB.size() != 0)
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBorangFInBulk('$!listMaklumatPB.size()','$!mode','$!id_borangf')">
					#end
						
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangFInBulk('$!id_borangf')">
						<input type="button" name="cmdKemaskini" value ="Hapus Semua Pilihan Dan Maklumat" onClick="javascript:hapusBorangFInBulk('$!id_borangf')">
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangFInBulk('$!listMaklumatPB.size()','$!mode','$!id_borangf')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:viewBorangFInBulk('$!id_borangf','batal')">
						#end
					#end
					#end
					<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:viewBorangEInBulk('$!id_borange')">
					
				</td>
			</tr>
		</table>
		
	<br/>
	<fieldset>
	<legend><strong>Senarai Rekod Maklumat Borang F</strong>
    #if($mode=="view")
	<input type="button" name="cmdMainscreen" value="Kemasukan Maklumat Borang F" onClick="javascript:showBorangF('$!id_hakmilik')">
	#end
    </legend>
    
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianBorangF('$id_hakmilik','senarai_borangF_inbulk')"><font color="blue">>> SKRIN CAPAIAN MAKLUMAT BORANG F</font></a>
    </td>
    </tr>
    </table>
    
    
   
    
    <!--
    
	<table width="100%" border="0">
		
		#if($mode=="view")
		<tr align="left">
			<td colspan="5">
				<input type="button" name="cmdMainscreen" value="Kemasukan Maklumat Borang F" onClick="javascript:showBorangF('$!id_hakmilik')">
			</td>
		</tr>
		#end
		
		<tr class="table_header">
       		<td align="center"><b>No</b></td>
            <td><b>Senarai Rekod/Tarikh Daftar</b></td>
           	<td><b>Tempoh (Hari)</b></td>              
            <td><b>Ulasan</b></td>
            <td align="center"><b>Senarai PB</b></td>
        </tr>
                    
   		#if($saiz_listBorangFInBulk!=0)
      		#foreach($listN in $listBorangFInBulk)
            #set( $i = $velocityCount )
         	#if ( ($i % 2) != 1 )
           		#set( $row = "row2" )
	        #else
	            #set( $row = "row1" )
	       	#end
         	
       	<tr>
           	<td class="$row" align="center">$!listN.bil</td>
            <td class="$row">
            <a href="javascript:viewBorangFInBulk('$!listN.ID_BORANGF','')"><font color="blue">Rekod $!listN.bil - $!listN.TARIKH_MASUK</font></a></td>
			<td class="$row">$!listN.TEMPOH</td>  
			<td class="$row">$!listN.ULASAN</td> 
			<td align="center" class="$row"><a href="javascript:viewPopupLot('$!listN.ID_BORANGF','$!id_permohonan')"><font color="blue"><b>$!listN.TOTALHM</b></font></a></td>   
        </tr>
        	#end
        #else
        <tr>
           	<td colspan="2">Tiada rekod</td>
       	</tr>
        #end 
		
	</table>
    -->	
	</fieldset>
	#else
		<table width="100%" border="0">
			<tr align="center">
				<td><input type="button" name="cmdKembali" value="Kembali" onClick="javascript:viewBorangEInBulk('$!id_borange')"></td>
			</tr>
		</table>
	#end
    
    
    <fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>  
      	<td><a href="#" onClick="javascript:cetakBorangF('$!id_permohonan','$!id_hakmilik')"><font color="blue">Borang F</font></a>
        
        </td>
      </tr>     
      <!--
      #if($userIdNeg=='10')
      <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang E dan F</font></a></td>
	  </tr>
      #else
      <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaian('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang E dan F - I</font></a></td>
	  </tr>
	  <tr>
      	<td><a href="#" onClick="javascript:cetakBuktiPenyampaianRamai('$!id_permohonan','$!id_hakmilik')"><font color="blue">Bukti Penyampaian Borang E dan F - II</font></a></td>
	  </tr>
      #end
      -->
    </table>
</fieldset>	
	
<br/>
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="id_borangf" value="$!id_borangf">
<input type="hidden" name="id_borange" value="$!id_borange">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>
function popupCarianBorangF(id_hakmilik,flag_skrin)
{
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik_BorangF?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}


function popupCarianPB(id_hakmilik,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianPB?&id_hakmilik="+id_hakmilik+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}



function popupCarianHakmilik(id_borange,id_permohonan,flag_skrin){
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?id_borange="+id_borange+"&type=borange&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'Senarai Lot','width=800,height=600, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}



function cetakBorangF(idpermohonan,idhakmilik) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=BorangF&selectNoFail=yes";
	//var url = "../servlet/ekptg.report.ppt.BorangF?id_hakmilik='"+idhakmilik+"'&namaPegawai="+namaPengarah;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function kemaskiniBorangFInBulk(id_borangf){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "maklumatBorangF";
	document.${formName}.command2.value = "viewMaklumatBorangFInBulk";
	document.${formName}.command3.value = "kemaskiniBorangFInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function hapusBorangFInBulk(id_borangf){	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "maklumatBorangF";
	document.${formName}.command2.value = "showBorangF";
	document.${formName}.command3.value = "hapusBorangFInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function viewBorangFInBulk(id_borangf,mode){	

	if(mode=="batal"){if ( !window.confirm("Adakah Anda Pasti?") ) return;}
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "maklumatBorangF";
	document.${formName}.command2.value = "viewMaklumatBorangFInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function viewPopupLot(id_borangf,id_permohonan){
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmMyInfoPopupPegawaiBertugas?id_borangf="+id_borangf+"&type=borangf&id_permohonan="+id_permohonan;
	var hWnd = window.open(url,'Senarai Lot','width=800,height=400, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function maklumatBorangF(id_borange){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "maklumatBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function simpanBorangFInBulk(size,mode,id_borangf){	

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

	if(!checkSelected){
		alert("Sila pilih \"Pihak Berkepentingan\" terlebih dahulu.");
		return;
	}else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "maklumatBorangF";
		if(mode=="new"){
			document.${formName}.command2.value = "showBorangF";
			document.${formName}.command3.value = "simpanBorangFInBulk";
		}else{
			document.${formName}.id_borangf.value = id_borangf;
			document.${formName}.command2.value = "viewMaklumatBorangFInBulk";
			document.${formName}.command3.value = "kemaskiniBorangFInBulk";
			document.${formName}.command4.value = "updateBorangFInBulk";
		}		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
		document.${formName}.submit();
	}
}
function showBorangF(id_hakmilik){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "maklumatBorangF";
	document.${formName}.command2.value = "showBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function viewBorangEInBulk(id_borange){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "viewMaklumatBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
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
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
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