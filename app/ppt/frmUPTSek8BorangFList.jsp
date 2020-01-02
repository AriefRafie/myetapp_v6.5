#parse("app/ppt/Sek8Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="middle">
	<legend><strong>Maklumat Borang E dan Borang F</strong></legend>
	
		#if($mode=="new")
		<table width="100%" border="0">  
			<tr>
				<td width="1%"><font color="red">*</font></td>
				<td width="18%">Tarikh Borang E</td>
				<td width="1%">:</td>
				<td width="80%"><input name="txdTarikhBorangE" id="txdTarikhBorangE" size="11" type="text" value="$!txdTarikhBorangE" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangE',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>
			<tr>
                <td><font color="red">*</font></td>
                <td>Tarikh Borang F</td>
                <td>:</td>
                <td><input name="txdTarikhBorangF" id="txdTarikhBorangF" size="11" type="text" value="$!txdTarikhBorangF" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangF',false,'dmy');">&nbsp;$!frmtdate</td>
    		</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Siasatan</td>
				<td>:</td>
				<td><input name="txdTarikhSiasatan" id="txdTarikhSiasatan" size="11" type="text" value="$!txdTarikhSiasatan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSiasatan',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Masa Siasatan</td>
				<td>:</td>
				<td><input type="text" name="txtMasaSiasatan" id="txtMasaSiasatan" value="$!txtMasaSiasatan" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
					<select name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

					#if($socJenisWaktu=="1")
					<option value="1">PAGI</option>
                    <option value="2">TENGAHARI</option>
                    <option value="3">PETANG</option>
                    <option value="0">SILA PILIH</option>
					#elseif($socJenisWaktu=="2")
					<option value="2">TENGAHARI</option>	
                    <option value="1">PAGI</option>
                    <option value="3">PETANG</option>
                    <option value="0">SILA PILIH</option>
					#elseif($socJenisWaktu=="3")
					<option value="3">PETANG</option>
                    <option value="1">PAGI</option>
                    <option value="2">TENGAHARI</option>
                    <option value="0">SILA PILIH</option>
					#else
					<option value="0">SILA PILIH</option>
                    <option value="1">PAGI</option>
                    <option value="2">TENGAHARI</option>
                    <option value="3">PETANG</option>
					#end
					
                </select>&nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Alamat</td>
				<td>:</td>
				<td><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="45" maxlength="80"   ></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
				<td><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="45" maxlength="80"   ></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
				<td><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="45" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Poskod</td>
				<td>:</td>
				<td><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)" ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Negeri</td>
				<td>:</td>
				<td>$!selectNegeri</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Bandar</td>
				<td>:</td>
				<td>$!selectBandar</td>
			</tr>
		</table>
		#end
		
		
		#if($mode=="view")
		
		#if($onchange=="no")
		#foreach($data in $dataBorangE)
			#set($txdTarikhBorangF=$data.tarikh_borangf)
			#set($txdTarikhBorangE=$data.tarikh_borange)
			#set($txdTarikhSiasatan=$data.tarikh_siasatan)
			#set($txtMasaSiasatan=$data.masa_siasatan)
			#set($socJenisWaktu=$data.jenis_waktu)
			#set($txtAlamat1=$data.alamat1)
			#set($txtAlamat2=$data.alamat2)
			#set($txtAlamat3=$data.alamat3)
			#set($txtPoskod=$data.poskod)
			#set($tarikh_cetak=$data.tarikh_cetak)
		#end
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
		
		<table width="100%" border="0">  
			<tr>
				<td width="1%"><font color="red">$!M</font></td>
				<td width="18%">Tarikh Borang E</td>
				<td width="1%">:</td>
				<td width="80%"><input $disability $disabilityx name="txdTarikhBorangE" id="txdTarikhBorangE" size="11" type="text" value="$!txdTarikhBorangE" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangE',false,'dmy');">&nbsp;$!frmtdate#end</td>
			</tr>
			<tr>
                <td><font color="red">$!M</font></td>
                <td>Tarikh Borang F</td>
                <td>:</td>
                <td><input $disability $disabilityx name="txdTarikhBorangF" id="txdTarikhBorangF" size="11" type="text" value="$!txdTarikhBorangF" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangF',false,'dmy');">&nbsp;$!frmtdate#end</td>
    		</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Siasatan</td>
				<td>:</td>
				<td><input $disability $disabilityx name="txdTarikhSiasatan" id="txdTarikhSiasatan" size="11" type="text" value="$!txdTarikhSiasatan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSiasatan',false,'dmy');">&nbsp;$!frmtdate#end</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Masa Siasatan</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtMasaSiasatan" id="txtMasaSiasatan" value="$!txtMasaSiasatan" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
					<select $disability1 $disabilityx name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

					#if($socJenisWaktu=="1")
					<option value="1">PAGI</option>
					<option value="2">TENGAHARI</option>
                    <option value="3">PETANG</option>
                    <option value="0">SILA PILIH</option>
					#elseif($socJenisWaktu=="2")
					<option value="2">TENGAHARI</option>
					<option value="1">PAGI</option>
                    <option value="3">PETANG</option>
                    <option value="0">SILA PILIH</option>
					#elseif($socJenisWaktu=="3")
					<option value="3">PETANG</option>
					<option value="1">PAGI</option>
                    <option value="2">TENGAHARI</option>
                    <option value="0">SILA PILIH</option>
					#else
					<option value="0">SILA PILIH</option>
                    <option value="1">PAGI</option>
                    <option value="2">TENGAHARI</option>
                    <option value="3">PETANG</option>
					#end
					
                </select>&nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Alamat</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="45" maxlength="80"   ></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
				<td><input $disability $disabilityx type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="45" maxlength="80"   ></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
				<td><input $disability $disabilityx type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="45" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Poskod</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)" ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Negeri</td>
				<td>:</td>
				<td>$!selectNegeri</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Bandar</td>
				<td>:</td>
				<td>$!selectBandar</td>
			</tr>
		</table>
		#end
		
	</fieldset>

	<!--  <input type="button" name="cmdUpdate" value="Kemaskini" onClick="javascript:updateTarikhBorangF('$!id_permohonan');"> -->

	<table width="100%" border="0">
		<tr align="center">
			<td>
			
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBorangE('$!id_permohonan','$!id_borange','$!mode')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangE('$!id_borange')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangE('$!id_permohonan','$!id_borange','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_permohonan')">
				#end
			#end
				
			</td>
		</tr>
	</table>
               

<br/>	

	<fieldset>
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
			
			<table width="100%" border="0">   
                	<tr>
                    	<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
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
            		<td><b>Pegawai</b></td> 
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
                    <td class="$row">$!listTanah.no_hakmilik</td>
                	<td class="$row">$!listTanah.no_lot</td>          
                	<td class="$row">$!listTanah.kod_lot$!listTanah.no_pt</td>     
                	<td class="$row">$!listTanah.nama_mukim</td>
                	<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>							
                    <td class="$row">$!listTanah.nama_pegawai</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listTanah > 5)
                </div>
            #end
	
	</fieldset>

<br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Penerima</strong></legend>
	
		<table width="100%" border="0">   
              <tr>
                 <td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahPenerima('$!id_permohonan');"></td>
              </tr>
    	</table>

		#if($saiz_listBorangF > 5)
                <div style="width:100%;height:100;overflow:auto"> 
        #end
	
		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Nama Penerima</b></td>
        			<td><b>No. PB</b></td>
        			<!-- <td><b>Tarikh Terima</b></td> -->
        		</tr>
        		
        		#if($saiz_listBorangF!=0)
                    #foreach($list in $listBorangF)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!list.bil</td>
                   <td class="$row"><a href="javascript:viewPenerima('$!list.id_borangf')"><font color="blue">$!list.nama_pb</font></a></td>
                   <td class="$row">$!list.no_pb</td>
                   <!-- <td class="$row">$!list.tarikh_terima</td> -->
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		</table>
			
		#if($saiz_listBorangF > 5)
        	</div>
        #end	
		
	</fieldset>


	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($mode=="view" && $isEdit=="no" )
				<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
				#end
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table>

</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
		<td><a href="#" onClick="javascript:cetakBorangE('$!id_fail','$!tarikh_cetak')"><font color="blue">Borang E</font></a></td>
	  </tr>
	  #if($saiz_listBorangF!=0)
      <tr>  
      	<td><a href="#" onClick="javascript:cetakBorangF('$!id_fail')"><font color="blue">Borang F</font></a></td>
      </tr>     
      #end
    </table>
</fieldset>	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik">
<input type="hidden" name="id_borangf">
<input type="hidden" name="id_borange" value="$!id_borange">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function onchangeNegeriBorangEUpdate(){
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.command2.value = "kemaskiniBorangE";
	document.${formName}.command3.value = "onchangeNegeriBorangEUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function batalKemaskini(id_permohonan){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function kemaskiniBorangE(id_borange){
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.command2.value = "kemaskiniBorangE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function simpanBorangE(id_permohonan,id_borange,mode){

	var dat1 = document.${formName}.txdTarikhBorangF
	var dat2 = document.${formName}.txdTarikhBorangE

	if(document.${formName}.txdTarikhBorangE.value == ""){
		alert("Sila masukkan \"Tarikh Borang E\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangE.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhBorangF.value == ""){
		alert("Sila masukkan \"Tarikh Borang F\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangF.focus(); 
		return;
	}	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "middle";
		document.${formName}.id_permohonan.value = id_permohonan;

		if(mode=="new"){
			document.${formName}.command.value = "viewListBorangF";
			document.${formName}.command2.value = "simpanBorangE";
		}else{
			document.${formName}.id_borange.value = id_borange;
			document.${formName}.command.value = "viewListBorangF";
			document.${formName}.command2.value = "kemaskiniBorangE";
			document.${formName}.command3.value = "updateBorangE";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
		document.${formName}.submit();
	}
	
}
function onchangeNegeriBorangE(){
	document.${formName}.ScreenLocation.value = "middle";
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.command2.value = "onchangeNegeriBorangE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function cetakBorangE(idfail,tarikh_cetak) {

	if(tarikh_cetak==""){
		document.${formName}.ScreenLocation.value = "middle";
		document.${formName}.command.value = "simpanTarikhCetak";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
		document.${formName}.submit();
	}
	
    var url = "../servlet/ekptg.report.ppt.BorangE?id_Fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangF(idfail) {

	var url = "../servlet/ekptg.report.ppt.BorangF?id_Fail='"+idfail+"'";
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
function viewPenerima(id_borangf){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borangf.value = id_borangf;
	document.${formName}.command.value = "viewPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function tambahPenerima(idpermohonan){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahPenerima";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
</script>


<script>
function checkDigit() {
	if(document.getElementById("txtMasaSiasatan").value != "" && document.getElementById("txtMasaSiasatan").value.length < 4){
		alert("Sila Pastikan Format Masa Siasatan Adalah \"HHMM\"");
		document.${formName}.txtMasaSiasatan.focus(); 
		return;	
	}
}
function validateJenisWaktu(elmnt,content) {

	var length = parseInt(document.getElementById("txtMasaSiasatan").value.length);
	
	if(length>=2){

		var ValJam = content.substring(0,2);

		if(ValJam!=""){	
		
			if(ValJam >= 07 && ValJam <= 11){
				document.getElementById("socJenisWaktu").value = "1" ;
			}else if(ValJam == 12){
				document.getElementById("socJenisWaktu").value = "2" ;
			}else if(ValJam == 01 || ValJam == 02 || ValJam == 03 || ValJam == 04 || ValJam == 05 || ValJam == 06){
				document.getElementById("socJenisWaktu").value = "3" ;
			}else{
				document.getElementById("socJenisWaktu").value = "0" ;
			}
		
		}else{
			document.getElementById("socJenisWaktu").value = "0" ;
		}	
		return;

	}else{
		document.getElementById("socJenisWaktu").value = "0";
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
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
</script>


<script>
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

function isIc(dtStr){
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
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}
</script>