#parse("app/ppt/SementaraPaging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")


<fieldset id="top">
<legend><b>Agihan Tugas</b></legend>
<center>
	#parse("app/ppt/frmPPTHeader.jsp")

<br/>
	
	<fieldset id="bottom">
		<legend><strong>Statistik Agihan Kepada Pegawai</strong></legend>
			
    		#if($statistikAgihan_size > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center"><b>No</b></td>
        			<td><b>Nama Pegawai</b></td>
                  	<td><b>Bil. Permohonan Yang Terlibat</b></td>                  
                  	<td><b>Bil. Hakmilik Yang Diterima</b></td>
            	</tr>
        		
        		#if($statistikAgihan_size!=0) 
        		
                    #foreach($list in $statistikAgihan)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
       
               <tr>
                    <td class="$row" align="center">$!list.bil</td>
                    <td class="$row">$!list.nama_pegawai</td>
                	<td class="$row">$!list.total_permohonan</td>  
                	<td class="$row">$!list.total_hakmilik</td>
               <tr>
                    #end
                        
               #else
                    <tr>
                    	<td colspan="2">Tiada Rekod</td>
                    </tr>
               #end
		  </table>
		  
			#if($statistikAgihan_size > 5)
                </div>
            #end
	
		</fieldset>
		
		<br/>
		
	<fieldset id="bottom">
	<legend><strong>Maklumat Pengagihan / Penyerahan Tugas</strong></legend>
	
		#if($mode=="new")
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="24%">Pegawai Pengagih</td>
				<td width="1%">:</td>
				<td width="74%"><input type="text" size="50" name="txtNamaPegawai" id="txtNamaPegawai" value="${session.getAttribute('_portal_username')}" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase()" readonly class="disabled"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Serah Tugas</td>
				<td>:</td>
				<td>$!currentDATE&nbsp;$!frmtdate</td>
			</tr>
			<tr>
				<td><font color="red">*</font></td>
				<td>Pegawai Penerima</td>
				<td>:</td>
				<td>$!selectPegawai
              
                #if($saiz_listTanah>1)
                
                 &nbsp; <span >
				  <input name="start"  type="text" id="start" value="$!listTanah.bil" size="4" maxlength="4" onblur="validateTarikh(this,this.value);" onkeyup="validateTarikh(this,this.value);" />
                  <strong>&gt;&gt;</strong>				
			    <input name="end"  type="text" id="end" value="$!listTanah.bil" size="4" maxlength="4" onblur="validateTarikh(this,this.value);" onkeyup="validateTarikh(this,this.value);" />
				 &nbsp;
                <a href="javascript:autoagih()"><font color="blue" title="Klik untuk pengagihan secara automatik">Agih</font></a> 
                &nbsp;
                 <a href="javascript:autoagihX()"><font color="blue"  title="Klik untuk mengosongkan senarai semak">Kosongkan</font></a> 
                </span>
               
                #end
                
                </td>
		  </tr>
			<tr>
				<td>&nbsp;</td>
				<td>Jawatan Pegawai Penerima</td>
				<td>:</td>
				<td><input type="text" name="lblJawatanPegawai" id="lblJawatanPegawai" value="$!lblJawatanPegawai" readonly class="disabled" size="40" /></td>
			</tr>
			
			<!-- <tr>
				<td>&nbsp;</td>
				<td valign="top">Catatan</td>
				<td valign="top">:</td>
				<td valign="top"><textarea name="txtCatatan" id="txtCatatan" cols="47%" rows="3" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen2,100);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen2,100);" >$!txtCatatan</textarea></td>
			</tr> -->
			<tr><td colspan="4">&nbsp;</td></tr>
			
		</table>
		
		#if($showList=="yes")
		<fieldset id="bottom">
		<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
			
			<table width="100%" border="0">   
                	<tr>
                    	<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center"><b>No</b></td>
        			<td><b>No.Hakmilik</b></td>
                  	<td><b>FT</b></td> 
                  	<td><b>QT</b></td>                
                  	<td><b>Mukim/Pekan/Bandar</b></td>
            		<td><b>Keluasan</b></td> 
            		#if($saiz_listTanah!=0)<td width="4%" align="center"><input type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></td>#end
        		</tr>
        		
        		#set($total = 0)
        		
        		#if($saiz_listTanah!=0) 
        		
                    #foreach($listTanah in $listHMByPegawai)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
                   
                    #if($listTanah.id_pegawai == $!id_pegawai)
                    	#set($check="checked")
                    	#set($total = $total + 1)
                    #else
                    	#set($check="")
                    #end
                    
               <tr>
                    <td class="$row" align="center">$!listTanah.bil</td>
                    <td class="$row">$!listTanah.no_hakmilik</td>
                	<td class="$row">$!listTanah.no_lot</td>          
                	<td class="$row">$!listTanah.kod_lot$!listTanah.no_pt</td>     
                	<td class="$row">$!listTanah.nama_mukim</td>
                	<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
                   #if($saiz_listTanah!=0)<td class="$row" align="center"><input type="checkbox" $check name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>
                   #end
               <tr>
                    #end
                        
               #else
                    <tr>
                    	<td colspan="2">Tiada Hakmilik</td>
                    </tr>
               #end
		  </table>
		  
		  
	
			#if($saiz_listTanah > 5)
                </div>
            #end
	
		</fieldset>
		
		<table width="100%" border="0"> 
		  	<tr>
		  		<td align="right"><b>Jumlah Lot Diagihkan: $!total</b></td>
		  	</tr>
		</table>
		  
		#end
		
		#end
		
		
<!-- #if($mode=="view")
		
		#if($onchange=="no")
		#foreach($data in $dataAgihan)
			#set($txtNamaPegawai = $data.pegawai_agih)
			#set($txdTarikhSerah = $data.tarikh_agih)
			#set($lblJawatanPegawai = $data.jawatan)
			#set($txtCatatan = $data.perihal_agih)
		#end
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
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="24%">Pegawai Pengagih</td>
				<td width="1%">:</td>
				#if($isEdit=="no")
				<td width="74%"><input type="text" size="50" name="txtNamaPegawai" id="txtNamaPegawai" value="$!txtNamaPegawai" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase()" readonly class="disabled"></td>
				#else
				<td width="74%"><input type="text" size="50" name="txtNamaPegawai" id="txtNamaPegawai" value="${session.getAttribute('_portal_username')}" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase()" readonly class="disabled"></td>
				#end
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Serah Tugas</td>
				<td>:</td>
				#if($isEdit=="no")
				<td>$!txdTarikhSerah&nbsp;$!frmtdate</td>
				#else
				<td>$!currentDATE&nbsp;$!frmtdate</td>
				#end	
			</tr>
			<tr>
				<td><font color="red">#if($isEdit=="yes")*#end</font></td>
				<td>Pegawai Penerima</td>
				<td>:</td>
				<td>$!selectPegawai</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Jawatan Pegawai Penerima</td>
				<td>:</td>
				<td><input type="text" name="lblJawatanPegawai" id="lblJawatanPegawai" value="$!lblJawatanPegawai" readonly class="disabled" size="40" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td valign="top">Catatan</td>
				<td valign="top">:</td>
				<td valign="top"><textarea $disability $disabilityx name="txtCatatan" id="txtCatatan" cols="47%" rows="3" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen2,100);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen2,100);" >$!txtCatatan</textarea></td>
			</tr>
		</table>
		#end -->		
		
	<fieldset>
	<legend>Senarai Pegawai Yang Telah Menerima Agihan Hakmilik Untuk Permohonan Ini</legend>
		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Nama Pegawai</b></td>
        			<!-- <td><b>No. Hakmilik</b></td> -->
        			<td><b>Jumlah Hakmilik</b></td>
            	</tr>
        		
        		#if($saiz_listPegawai!=0) 
        		
                    #foreach($listPg in $listPegawaiTerimaHM)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
 
               <tr>
               		<td class="$row" align="center">$!listPg.bil</td>
               		<td class="$row">$!listPg.user_name</td>  
               		<!-- <td class="$row">$!listPg.kod_jenis_hakmilik $!listPg.no_hakmilik</td> -->   
               		<td class="$row">$!listPg.totalhm</td>        
               <tr>
                    #end
                        
               #else
                    <tr>
                    	<td colspan="2">Tiada Agihan Dibuat</td>
                    </tr>
               #end
		  </table>
	</fieldset>		
		
	</fieldset>


			<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanAgihan('$!id_permohonan','$!id_tugas','$!mode')">
					#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniAgihan('$!id_permohonan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanAgihan('$!id_permohonan','$!id_tugas','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batal('$!id_permohonan')">
						#end
					#end	
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">
					</td>
				</tr>
			</table>
			
</center>
</fieldset>

			

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="id_tugas" value="$!id_tugas">
<input type="hidden" name="id_pegawai" value="$!id_pegawai">

<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function batal(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewAgihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas";
	document.${formName}.submit();
}
function doChangePegawaiUpdate() {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "viewAgihan";
	document.${formName}.command2.value = "kemaskiniAgihan";
	document.${formName}.command3.value = "doChangePegawaiUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas";
	document.${formName}.submit();
}
function kemaskiniAgihan() {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "viewAgihan";
	document.${formName}.command2.value = "kemaskiniAgihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas";
	document.${formName}.submit();
}
function simpanAgihan(idpermohonan,idtugas,mode) {

	if(document.${formName}.socPegawai.value == ""){
		alert("Sila pilih \"Pegawai Penerima\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "bottom";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "tambahAgihan";
			document.${formName}.command2.value = "simpanAgihan";
		}else{
			document.${formName}.id_tugas.value = idtugas;
			document.${formName}.command.value = "viewAgihan";
			document.${formName}.command2.value = "kemaskiniAgihan";
			document.${formName}.command3.value = "updateAgihan";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas";
		document.${formName}.submit();
	}
}
function doChangePegawai() {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "tambahAgihan";
	document.${formName}.command2.value = "doChangePegawai";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.command.value = "xx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahAgihan";
	document.${formName}.command2.value = "doChangePegawai";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahAgihan";
	document.${formName}.command2.value = "doChangePegawai";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas";
	document.${formName}.submit();
}
</script>


<!-- FOR CHECKBOX -->
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
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
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


function autoagih()
{

if(document.${formName}.start.value == "" )
{
alert("Sila masukkan nilai mula")
document.${formName}.start.focus();
}
else if(document.${formName}.end.value == "" )
{
alert("Sila masukkan nilai akhir")
document.${formName}.end.focus();
}
else if(parseInt(document.${formName}.start.value) < 0 )
{
alert("Sila pastikan nilai mula melebih dari 0")
document.${formName}.start.focus();
}
else if(parseInt(document.${formName}.end.value) < 0 )
{
alert("Sila pastikan nilai akhir melebih dari 0")
document.${formName}.end.focus();
}
else if(parseInt(document.${formName}.start.value) > parseInt(document.${formName}.end.value)  )
{
alert("Sila pastikan nilai mula lebih kecil ataupun sama dengan nilai akhir")
document.${formName}.start.focus();
}
else if(parseInt(document.${formName}.end.value) > parseInt('$saiz_listTanah'))
{
alert("Sila pastikan nilai akhir lebih kecil ataupun sama dengan jumlah lot")
document.${formName}.end.focus();
}
else
{
/*
if(document.${formName}.cbsemaks.length > 1)
{     
	  for (i = 0; i <= document.${formName}.cbsemaks.length; i++)
	  {	
      document.${formName}.cbsemaks[i].checked = false;	  
	  }  
}
else
{
	  document.${formName}.cbsemaks.checked = false;	  	
}	

*/

var start = parseInt(document.${formName}.start.value)-1;
var end = parseInt(document.${formName}.end.value)-1;
if(document.${formName}.cbsemaks.length > 1)
{  

      for (k = 0; k < document.${formName}.cbsemaks.length; k++)
	  {	
      document.${formName}.cbsemaks[k].checked = false;	  
	  }  

   
	  for (i = start; i <= end; i++)
	  {	
      document.${formName}.cbsemaks[i].checked = true;	  
	  }  
}
else
{
	  document.${formName}.cbsemaks.checked = true;	  	
}	
}

}

function autoagihX()
{


if(document.${formName}.cbsemaks.length > 1)
{     
	  for (i = 0; i <= document.${formName}.cbsemaks.length; i++)
	  {	
      document.${formName}.cbsemaks[i].checked = false;	  
	  }  
}
else
{
	  document.${formName}.cbsemaks.checked = true;	  	
}	

}

</script>