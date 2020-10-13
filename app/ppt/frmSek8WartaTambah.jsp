#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#if($showAlertPaging=="yes")
<script>
alert("Sila Klik Butang Kembali Dan Klik 'Paging' No.11 Untuk Proses Endorsan");
</script>
#end 

<!-- OPEN fieldset borang d -->
<!-- PENAMBAHAN YATI 6/1/2017 -->
<!-- PPT-05 tukar kedudukan-->
		<fieldset style="width:100%">
		<legend><strong>Maklumat Borang D</strong></legend>
		#if($mode=="new")
		<table width="100%" border="0">   
                <tr>
                	<td width="1%"><font color="red">*</font></td>
                	<td width="30%">Tarikh Borang D</td>
                	<td width="1%">:</td>
                	<td width="68%"><input name="txdTarikhBorangD" id="txdTarikhBorangD" size="11" type="text" value="$!tarikh_borangd" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangD',false,'dmy');">&nbsp;$!frmtdate</td>
    			</tr>
    				
    			<tr>
    				<td>&nbsp;</td>
    				<td valign="top">Catatan</td>
    				<td valign="top">:</td>
    				<td valign="top"><textarea name="txtCatatanBorangD" id="txtCatatanBorangD" rows="3" cols="30%" onKeyUp="textCounter(this.form.txtCatatanBorangD,this.form.remLen1,1500);" onKeyDown="textCounter(this.form.txtCatatanBorangD,this.form.remLen1,1500);" ></textarea></td>
    			</tr>	
    	</table>
    	
    	#end
    	
    #if($mode=="view")
	
	#foreach($data in $dataWarta)
		
		#set($txdTarikhBorangD = $data.tarikh_borangd)
		#set($txtCatatanBorangD = $data.catatan)		
			
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
                	<td width="1%"><font color="red">*</font></td>
                	<td width="30%">Tarikh Borang D</td>
                	<td width="1%">:</td>
                	<td width="68%"><input $disability $disabilityx name="txdTarikhBorangD" id="txdTarikhBorangD" size="11" type="text" value="$!txdTarikhBorangD" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangD',false,'dmy');">&nbsp;$!frmtdate#end</td>
    			</tr>
    			<tr>
    				<td>&nbsp;</td>
    				<td valign="top">Catatan</td>
    				<td valign="top">:</td>
    				<td valign="top"><textarea $disability $disabilityx name="txtCatatanBorangD" id="txtCatatanBorangD" rows="3" cols="30%" onKeyUp="textCounter(this.form.txtCatatanBorangD,this.form.remLen1,1500);" onKeyDown="textCounter(this.form.txtCatatanBorangD,this.form.remLen1,1500);" >$!txtCatatanBorangD</textarea></td>
    			</tr>	
    	</table>
		#end
		</fieldset>
		<!-- close borang d -->
		<br/>

<fieldset id="top" style="width:100%">
<legend><strong>Maklumat Warta - Seksyen 8</strong></legend>
	
#if($mode=="new")
	<table width="100%" border="0">
		<tr>
			<td width="1%" valign="top">&nbsp;</td>	
			<td width="30%">Tarikh Hantar Borang D</td>
			<td width="1%">:</td>
			<td width="68%"><input name="txdTarikhHantarBorangD" maxlength="10" id="txdTarikhHantarBorangD" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantarBorangD',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
		<tr>
			<td valign="top"><font color="red">*</font></td>
			<td>Proses Warta</td>
			<td>:</td>
			<td><select name="socWarta" id="socWarta" style="width:144px">
      		
      			<option value="">SILA PILIH</option>
      			<option value="1">CAWANGAN JKPTG</option>
				<option value="2">PTG</option>
      			
			</select></td>
		</tr>
		<tr>	
			<td valign="top"><font color="red">*</font></td>		
			<td>No.Warta</td>
			<td>:</td>
			<td><input type="text" name="txtNoWarta" id="txtNoWarta" maxlength="100" value=""  size="50"  /></td>
		</tr>
		<tr>	
			<td valign="top">&nbsp;</td>		
			<td>No.Kenyataan</td>
			<td>:</td>
			<td><input type="text" name="txtNoKenyataan" id="txtNoKenyataan" maxlength="50" value=""  size="50"  /></td>
		</tr>
		<tr>
			<td valign="top"><font color="red">*</font></td>	
			<td>Tarikh Warta</td>
			<td>:</td>
			<td><input name="txdTarikhWarta" id="txdTarikhWarta" size="11" maxlength="10" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhWarta',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
		<tr>
			<td valign="top">&nbsp;</td>	
			<td>Tarikh Terima Warta</td>
			<td>:</td>
			<td><input name="txdTarikhTerimaWarta" id="txdTarikhTerimaWarta" size="11" maxlength="10" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
            <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaWarta',false,'dmy');">&nbsp;$!frmtdate</td>
		</tr>
	</table>
#end


#if($mode=="view")
	
	#foreach($data in $dataWarta)
		#set($socWarta=$data.proses_warta)
		#set($txtNoWarta=$data.no_warta)
		#set($txdTarikhWarta=$data.tarikh_warta)
		#set($txdTarikhHantarBorangD=$data.tarikh_hantar_borangd)
		#set($txtNoKenyataan=$data.no_kenyataan)
		#set($txdTarikhTerimaWarta=$data.tarikh_terima_warta)
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
			<td width="1%" valign="top">&nbsp;</td>	
			<td width="30%">Tarikh Hantar Borang D</td>
			<td width="1%">:</td>
			<td width="68%"><input $disability $disabilityx name="txdTarikhHantarBorangD" id="txdTarikhHantarBorangD" maxlength="10" size="11" type="text" value="$!txdTarikhHantarBorangD" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
            #if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantarBorangD',false,'dmy');">&nbsp;$!frmtdate#end</td>
		</tr>
		<tr>
			<td width="1%" valign="top"><font color="red">$!M</font></td>	
			<td width="30%">Proses Warta</td>
			<td width="1%">:</td>
			<td width="69%"><select $disability1 $disabilityx name="socWarta" id="socWarta" style="width:144px">
      		
      			#if($socWarta=="1")
      			<option value="1">CAWANGAN JKPTG</option>   			
				<option value="2">PTG</option>
				<option value="">SILA PILIH</option>  
      			#elseif($socWarta=="2")
      			<option value="2">PTG</option>
      			<option value="1">CAWANGAN JKPTG</option>		
      			<option value="">SILA PILIH</option>		
      			#else
      			<option value="">SILA PILIH</option>
      			<option value="1">CAWANGAN JKPTG</option>
				<option value="2">PTG</option>
      			#end
      		
			</select></td>
		</tr>
		<tr>			
			<td valign="top"><font color="red">#if($isEdit=="yes")*#end</font></td>
			<td>No.Warta</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtNoWarta" id="txtNoWarta" maxlength="100" value="$!txtNoWarta"  size="50"  /></td>
		</tr>
		<tr>	
			<td valign="top">&nbsp;</td>		
			<td>No.Kenyataan</td>
			<td>:</td>
			<td><input $disability $disabilityx type="text" name="txtNoKenyataan" id="txtNoKenyataan" maxlength="50" value="$!txtNoKenyataan"  size="50"  /></td>
		</tr>
		<tr>
			<td valign="top"><font color="red">#if($isEdit=="yes")*#end</font></td>
			<td>Tarikh Warta</td>
			<td>:</td>
			<td><input $disability $disabilityx name="txdTarikhWarta" id="txdTarikhWarta" maxlength="10" size="11" type="text" value="$!txdTarikhWarta" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhWarta',false,'dmy');">&nbsp;$!frmtdate#end</td>
		</tr>
		<tr>
			<td valign="top">&nbsp;</td>
			<td>Tarikh Terima Warta</td>
			<td>:</td>
			<td><input $disability $disabilityx name="txdTarikhTerimaWarta" id="txdTarikhTerimaWarta" maxlength="10" size="11" type="text" value="$!txdTarikhTerimaWarta" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaWarta',false,'dmy');">&nbsp;$!frmtdate#end</td>
		</tr>
	</table>
#end
	
	
</fieldset> 
<!--  CLOSE fieldset maklumat warta -->


		
		<br/>
		
		<!-- open endosan borang d -->
		<fieldset style="width:100%">
		
		<legend><strong>Maklumat Rekod Endosan Borang D</strong></legend>
		#if($mode=="new")
		<table width="100%" border="0">
		
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="30%">No. Perserahan</td>
				<td width="1%">:</td>
				<td width="68%"><input type="text" name="txtPerserahan" id="txtPerserahan" value="" size="21" maxlength="30"   ></td>
			</tr>
			
			<!-- <tr>
				<td>&nbsp;</td>
				<td>Diterima Daripada</td>
				<td>:</td>
				<td><select name="sorTerima" style="width:150px">
      		
      				<option value="">SILA PILIH</option>		
		
				</select></td>
			</tr> -->
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Catatan Dibuat</td>
				<td>:</td>
				<td>
             
                	#set($tarikh_endorsan_default = "")
                    #if($!hash_maklumatEndorsan_from_etanah.TARIKH_ENDORSAN != "")   
                    #set($tarikh_endorsan_default = $!hash_maklumatEndorsan_from_etanah.TARIKH_ENDORSAN) 
                    #end  
                
         
                <input name="txdTarikhCatatan" id="txdTarikhCatatan" size="11" type="text" value="$tarikh_endorsan_default" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhCatatan',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Masa Catatan Dibuat</td>
				<td>:</td>
				<td><input type="text" name="txtMasaCatatan" id="txtMasaCatatan" value="" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
					<select name="socJenisWaktu" id="socJenisWaktu" style="width:98px">

                    	<option value="0">SILA PILIH</option>
                    	<option value="1">PAGI</option>
                    	<option value="2">TENGAHARI</option>
                    	<option value="3">PETANG</option>	
                    	
                    </select>&nbsp;<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font></td>
			</tr>	
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Terima</td>
				<td>:</td>
				<td><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>	
		</table>
		#end
		
		#if($mode == "view")
		
		#foreach($data in $dataWarta)
			
			#set($txdTarikhTerima = $data.tarikh_terima)
			#set($txdTarikhCatatan = $data.tarikh_catatan)
			#set($txtMasaCatatan = $data.masa_catatan)
			#set($socJenisWaktu = $data.jenis_masa)
			#set($txtPerserahan = $data.no_perserahan)
		
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
					<td width="1%">&nbsp;</td>
					<td width="30%">No. Perserahan</td>
					<td width="1%">:</td>
					<td width="68%"><input $disability $disabilityx type="text" name="txtPerserahan" id="txtPerserahan" value="$!txtPerserahan" size="21" maxlength="30"   ></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Catatan Dibuat</td>
					<td>:</td>
					<td>
                    
                    #set($tarikh_endorsan_default = "")
                    #if($!hash_maklumatEndorsan_from_etanah.TARIKH_ENDORSAN != "")   
                    #set($tarikh_endorsan_default = $!hash_maklumatEndorsan_from_etanah.TARIKH_ENDORSAN) 
                    #end  
                     
                    <input $disability $disabilityx name="txdTarikhCatatan" id="txdTarikhCatatan" size="11" type="text" value="$!txdTarikhCatatan" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhCatatan',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Masa Catatan Dibuat</td>
					<td>:</td>
					<td><input $disability $disabilityx type="text" name="txtMasaCatatan" id="txtMasaCatatan" value="$!txtMasaCatatan" onblur="validateNumber(this,this.value);checkDigit()" onkeyup="validateNumber(this,this.value);validateJenisWaktu(this,this.value)" maxlength="4" size="4" />
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
                    	
                    	
                    </select>&nbsp;#if($isEdit=="yes")<font color="blue" style="font-size:10px"><i>format 12 jam (HHMM)</i></font>#end</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Tarikh Terima</td>
					<td>:</td>
					<td><input $disability $disabilityx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            		#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
				</tr>
			</table>
			#end
		
		</fieldset>
		<!-- close FIELDSET BORAND D -->



	<table align="left" width="100%" border="0">
		<tr align="center">
        <td width="32%">
        
        </td>
			<td width="68%" align="left">
				#if($mode=="new")
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanWarta('$!id_permohonan','$!id_warta','$!mode')">
                        #if($ID_NEGERIPROJEK == "4" || $ID_NEGERIPROJEK == "5")          
                        <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Borang D & Maklumat Warta)" onClick="popupEtanah('$id_fail','$id_permohonan','WartaS8','')">
                        #end
                        <!-- ETANAH WPKL -->
                        #if($ID_NEGERIPROJEK == "14")          
                        <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Borang D & Maklumat Warta)" onClick="popupEtanahWPKL('$!id_fail','$!id_permohonan','$!id_warta','WartaS8')">
                        #end
				#end
				
				#if($mode=="view")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniWarta('$!id_warta')">
                    
                    
                    
					<input type="button" name="cmdHapus" value="Hapus" onClick="hapusWarta('$!id_permohonan','$!id_warta')">
                    
                  
                        #if($ID_NEGERIPROJEK == "4" || $ID_NEGERIPROJEK == "5")          
                        <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Borang D & Maklumat Warta)" onClick="popupEtanah('$id_fail','$id_permohonan','WartaS8','')">
                        #end
                         <!-- ETANAH WPKL -->
                        #if($ID_NEGERIPROJEK == "14")          
                        <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Borang D & Maklumat Warta)" onClick="popupEtanahWPKL('$!id_fail','$!id_permohonan','$!id_warta','WartaS8')">
                        #end
                    
                    #else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanWarta('$!id_permohonan','$!id_warta','$!mode')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:viewWarta('$!id_warta','1')">
					#end
				#end				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewSenaraiWarta('$!id_permohonan')">
				
			</td>
		</tr>
	</table>

<br/>
<br/>
<br/>

    
    <fieldset id="bottom">
	<legend><strong>&nbsp;MUATNAIK BORANG D YANG DITANDATANGANI</strong></legend>
                
               
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
     <a href="javascript:popupCarianDokumen('$id_endosanborangd','BorangD','$id_permohonan')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
    </td>
    </tr>
    </table>
    
	</fieldset>	
	

<br/>
<br/>



<fieldset>
	<legend><strong>Senarai Warta</strong></legend>	
	
		#if($mode=="view")
		<table width="100%" border="0">   
            <tr>
                <td><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahWarta('$!id_permohonan')"></td>
    		</tr>
    	</table>
    	#end
    			
    	#if($saiz_listWarta > 5)
        <div style="width:100%;height:100;overflow:auto"> 
        #end	
    			
    	<table width="100%" border="0"> 
  
        	<tr class="table_header">
        		<td align="center" width="4%"><b>No</b></td>
        		<td width="20%"><b>Proses Warta</b></td>
        		<td width="46%"><b>No.Warta</b></td>
            	<td width="30%"><b>Tarikh Warta</b></td>
        	</tr>
        		
        #if($saiz_listWarta!=0)
            #foreach($list in $listWarta)
               #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		#set( $row = "row2" )
         		#else
               		#set( $row = "row1" )
         		#end
                    
            <tr>
                <td class="$row" align="center">$!list.bil</td>
               	<td class="$row"><a href="javascript:viewWarta('$!list.id_warta','0')"><font color="blue">$!list.proses_warta</font></a></td>
                <td class="$row">$!list.no_warta</td>
                <td class="$row">$!list.tarikh_warta</td>
            <tr>
            #end
        #else
        
            <tr>
                <td colspan="2">Tiada rekod</td>
            </tr>
        #end
        
		  </table>
	
		#if($saiz_listWarta > 5)
        </div>
        #end
            
</fieldset>


<input type="hidden" name="id_endosanborangd" id="id_endosanborangd" value="$!id_endosanborangd">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_warta" value="$!id_warta">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="tarikh_mmk" id="tarikh_mmk" value="$!tarikh_mmk">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>





<script>
/*
function popupEtanah(id_fail,id_permohonan,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}
*/

function popupEtanahWPKL(idFail, idPermohonan, idPPTWarta, jenisSkrin) {
	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupETanahPPTWPKL?idFail="+idFail+"&idPermohonan="+idPermohonan+"&idPPTWarta="+idPPTWarta+"&jenisSkrin="+jenisSkrin;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function popupEtanah(id_fail,id_permohonan,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

function popupCarianDokumen(id_endosanborangd,flag_skrin,id_permohonan)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_endosanborangd="+id_endosanborangd+"&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function hapusWarta(idpermohonan,idwarta){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_warta.value = idwarta;
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "hapusWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function kemaskiniWarta(idwarta){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_warta.value = idwarta;
	document.${formName}.command.value = "viewWarta";
	document.${formName}.command2.value = "kemaskiniWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function viewWarta(idwarta,mode){

	if(mode=="1"){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	}
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_warta.value = idwarta;
	document.${formName}.command.value = "viewWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function simpanWarta(id_permohonan,idwarta,mode) {

	var dat1=document.${formName}.txdTarikhWarta;
	var dat2=document.${formName}.txdTarikhTerimaWarta;
	var dat3=document.${formName}.txdTarikhHantarBorangD;
	//var dmmk=document.${formName}.tarikh_mmk.value;
	
	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh warta
	var TM  = document.getElementById("txdTarikhWarta").value;
	var dt1   = parseInt(TM.substring(0,2),10);
   	var mon1  = parseInt(TM.substring(3,5),10);
   	var yr1   = parseInt(TM.substring(6,10),10);
   	var tarikhWarta = new Date(yr1, mon1, dt1);

/*  var TMK  = document.getElementById("tarikh_mmk").value;
	var dt2   = parseInt(TMK.substring(0,2),10);
   	var mon2  = parseInt(TMK.substring(3,5),10);
   	var yr2   = parseInt(TMK.substring(6,10),10);
   	var tarikhMMK = new Date(yr2, mon2, dt2);
*/
   	
	if(document.${formName}.socWarta.value == ""){
		alert("Sila pilih \"Proses Warta\" terlebih dahulu.");
  		document.${formName}.socWarta.focus(); 
		return;
	}
	else if(document.${formName}.txtNoWarta.value == ""){
		alert("Sila masukkan \"No. Warta\" terlebih dahulu.");
  		document.${formName}.txtNoWarta.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhWarta.value == ""){
		alert("Sila masukkan \"Tarikh Warta\" terlebih dahulu.");
  		document.${formName}.txdTarikhWarta.focus(); 
		return;
	}
	else if(document.${formName}.txdTarikhBorangD.value == ""){
		alert("Sila masukkan \"Tarikh Borang D\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangD.focus(); 
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
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
/*	else if(tarikhWarta < tarikhMMK){
		alert("Sila pastikan \"Tarikh Warta\" tidak kurang dari \"Tarikh Kelulusan MMK\" iaitu pada "+dmmk);
		document.${formName}.txdTarikhWarta.focus();
		return;
	}
*/	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";	
		document.${formName}.id_permohonan.value = id_permohonan;

		if(mode=="view"){
			document.${formName}.id_warta.value = idwarta;
			document.${formName}.command.value = "viewWarta";
			document.${formName}.command2.value = "kemaskiniWarta";
			document.${formName}.command3.value = "updateWarta";
		}else{
			document.${formName}.CursorPoint.value = "socWarta";	
			document.${formName}.command.value = "tambahWarta";
			document.${formName}.command2.value = "simpanWarta";
		}

		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
		document.${formName}.submit();
	}
}
function viewSenaraiWarta(id_permohonan) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewSenaraiWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function tambahWarta(idpermohonan){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
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