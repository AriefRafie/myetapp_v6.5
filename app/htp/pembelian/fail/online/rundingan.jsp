
<table style="width:98%">
	<tr>
    	<td>
    		<fieldset><legend>SENARAI MAKLUMAT RUNDING HARGA</legend>
    			<table style="width:100%" >
    				<tr >
						<!-- <td width="3%"></td> -->
						<td colspan="6" width="97%">
						<input type="button" class="stylobutton_" name="cmdtambah" id="cmdtambah" value="Tambah" onclick="javascript:tambahRundingan()">
						</td>
						
				  	</tr>
			    	<tr class="row2">
						<td width="3%"><b>Bil</b></td>
						<td width="20%"><b>Nilai Tanah(RM)</b></td>
						<td width="20%"><b>Nilai Bangunan(RM)</b></td>
						<td width="20%"><b>Harga Tawaran(RM)</b></td>
						<td width="20%"><b>Harga Dipersetujui(RM)</b></td>
						<td width="17%"><b>Keputusan</b></td>
				  	</tr>
			#foreach ($rundingan in $senarai)
	        	#set( $i = $velocityCount )
	       		#if ( ($i % 2) == 0 )
	   	        	#set( $row = "row2" )
	            #else
	               	#set( $row = "row1" )
	          	#end
	          	
	          		<tr class="$row">
			        	<td><a class="stylelabel" href="javascript:kemaskiniRundingan($rundingan.idMaklumatMysrt)">$i.</a></td>
			        	<td><a class="stylelabel" href="javascript:kemaskiniRundingan($rundingan.idMaklumatMysrt)">$!util_.format2Decimal($rundingan.nilaiTanah)</a></td>
			        	<td>$!util_.format2Decimal($rundingan.nilaiBangunan)</td>
						<td>$!util_.format2Decimal($rundingan.hargaTawaran)</td>
			        	<td>$!util_.format2Decimal($rundingan.hargaSetuju)</td>
			        	<td>$!rundingan.keputusan</td>
			      	</tr>
	      	
	      	#end
				</table>		
  			</fieldset>
  		</td>
  	</tr>	
  	
  	<tr>
    <td>
    <fieldset id="rundingharga"><legend>MAKLUMAT RUNDING HARGA</legend>
        <table width="100%" align="center" border="0">
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Harga JPPH (RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
				        	#set ( $unit = "" )	

        					#if($rundinganMode == "new" || $rundinganMode == "update" )
				        	<select name="unit_bersamaan" id="socLuas" style="width:200px;" $mode class="$disabled" ">
								     
								      	<option value="">SILA PILIH</option>
								      	
									  	<option value="MP" #if($!rundingan.unitBersamaan =="MP") selected #end > MP - METER PERSEGI</option>
								        <option value="KP" #if($!rundingan.unitBersamaan =="KP") selected #end> KP - KAKI PERSEGI</option>
								      
								</select>
							#else
								#if($!rundingan.unitBersamaan =="MP")
									#set ( $unit = 'MP - METER PERSEGI' )
								#end
								#if($!rundingan.unitBersamaan =="KP")
									#set ( $unit = 'KP - KAKI PERSEGI' )
								#end
							#end	$!unit
							<input type="text" name="harga_bersamaan" id="harga_bersamaan" size="11" 
    						value="$!util_.format2Decimal($!rundingan.hargaBersamaan)" onBlur=""  $mode>
						</td>
					</tr>
					
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Nilai Tanah Oleh JPPH (RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="nilai_tnh" id="nilai_tnh" size="11" 
    						value="$!util_.format2Decimal($!rundingan.nilaiTanah)" onBlur=""  $mode>
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Nilai Bangunan Oleh JPPH (RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="nilai_bgn" id="nilai_bgn" size="11" 
    						value="$!util_.format2Decimal($!rundingan.nilaiBangunan)" onBlur=""  $mode>
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Harga Tawaran (RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="harga_beli" id="harga_beli" size="11" 
    						value="$!util_.format2Decimal($!rundingan.hargaTawaran)" onBlur=""  $mode>
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Harga Dipersetujui (RM)</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="harga_setuju" id="harga_setuju" size="11" 
    						value="$!util_.format2Decimal($!rundingan.hargaSetuju)" onBlur=""  $mode>
						</td>
					</tr>					            
                <!--	<tr>
						<td valign="top" width="1%">
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tempoh Serahan
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
							<input type="text" name="tempoh_serah" id="harga_setuju" size="11" 
    						value="$!rundingan.tempohSerahan" onBlur=""  $mode> Bulan
						</td>
					</tr>		-->		
					<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"> #if($rundinganMode == "new" || $rundinganMode == "update" ) * #end </span>
				        </td>
				         <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Keputusan Rundingan</div>
				       	</td>
						<td width="1%">:</td>				        
				        <td width="68%">
				        	
  				        #set ( $keputus = "" )	
        				#if($rundinganMode == "new" || $rundinganMode == "update" )
								<select name="keputusan" $mode>
									<option value="">SILA PILIH</option>
									<option value="BERJAYA" #if($!rundingan.keputusan == "BERJAYA") selected #end>BERJAYA</option>
									<option value="TANGGUH" #if($!rundingan.keputusan == "TANGGUH") selected #end>TANGGUH</option>
									<option value="BATAL" #if($!rundingan.keputusan == "BATAL") selected #end>BATAL</option>
								</select>
						#else
							#set ( $keputus = $!rundingan.keputusan )
						#end	$!keputus		
						</td>
						
						
					</tr>
					
					<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>
				         <td width="30%" valign="top">
				        	<div align="right" class="labelinput">
				        	<div align="left">Ulasan</div>
				       	</td>
						<td width="1%" valign="top">:</td>				        
				        <td width="68%">
								<textarea name="ulasan" onkeyup="this.value=this.value.toUpperCase();"  cols="41" rows="5" $mode onKeyDown="textCounter(this.form.ulasan,this.form.remLen4,1500);" onKeyUp="textCounter(this.form.ulasan,this.form.remLen4,1500);">$!rundingan.ulasan</textarea>
						</td>
						
						
					</tr>
					<tr>
					<td valign="top">&nbsp;</td>
					<td valign="top">&nbsp;</td>
					<td valign="top">&nbsp;</td>
					<td valign="top"><input type="text" readonly class="disabled" name="remLen4" size="4" maxlength="3" value="1500"> Baki Aksara</td>
				</tr>

              <td scope="row">&nbsp;</td>
              <td>&nbsp;</td>
            
        </table>
	<!--<table width="100%">
		<tr>
        		<td>
        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        		</td>
           	</tr>

	</table>  -->      
        
	  </fieldset>
    </td>
  </tr>
  <tr>
      <td>
      	#if($rundinganMode == "new" || $rundinganMode == "update" )
        	<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
      	#end
      </td>
  </tr>
    <tr>
      <td>
  						<div align="center">
  						
  						#if($rundinganMode == "new")
  							<input type="button" class="stylobutton_" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanRundingan()">
  							
  						#elseif($rundinganMode == "update")
  							<input type="button" class="stylobutton_" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:updateRundingan()">
  						#else
  							<input type="button" class="stylobutton_" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="javascript:kemaskiniRundingan()">
  						#end
  						<!-- 
  						<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Kembali" onclick="skrinSenarai($!rundingan.fail.getNoFail());">
  						-->
  						</div>      	
      </td>
  </tr>
 </table>
<script>
// melalui page number
// STEP 4
/* 	alert($mode);
	if($mode != ""){
		alert('x kosong');		
	} */
	function tambahRundingan(){
		alert('rundingan:');
		doAjaxCall${formName}("tambahRundingan");
	}
	function simpanRundingan(){
		if(document.${formName}.keputusan.value == ""){
			alert('Sila pilih " Keputusan " terlebih dahulu.');
	  		document.${formName}.keputusan.focus(); 
			return; 
		}
		doAjaxCall${formName}("simpanRundingan");
	}
	function updateRundingan(){
		doAjaxCall${formName}("updateRundingan");
	}
	function kemaskiniRundingan(){
		doAjaxCall${formName}("kemaskiniRundingan");
	}
	function textCounter(field, countfield, maxlimit) {
		if (field.value.length > maxlimit) // if too long...trim it!
			field.value = field.value.substring(0, maxlimit);
			// otherwise, update 'Baki Aksara' counter
		else 
			countfield.value = maxlimit - field.value.length;
	}
	
	function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
	
	}


//paging number

function step5(idPermohonan){
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.PerjanjianPembelianModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}

function step3(idPermohonan){
	//doAjaxCall${formName}('perakuanPembelian','&idPermohonan='+idPermohonan);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=perakuanPembelian&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step2(idPermohonan){
	//doAjaxCall${formName}('maklumatTanah','&idPermohonan='+idPermohonan);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=maklumatTanah&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function step1(idPermohonan,idhtp){
	//doAjaxCall${formName}("detail",'idPermohonan='+idPermohonan+'&idHtpPermohonan='+idhtp);
	document.${formName}.action = "$EkptgUtil.getTabID('Pembelian',$portal_role)?_portal_module=ekptg.view.htp.pembelian.SenaraiFailModule&command=detail&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function skrinSenarai(no){
	doAjaxCall${formName}("","noFail="+no);
}

function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
}

function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
</script>