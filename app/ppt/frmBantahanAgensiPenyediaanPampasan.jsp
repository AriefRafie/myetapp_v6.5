#parse("app/ppt/Sek8PagingPampasan.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">
<center>
<legend><strong>Penyediaan Pampasan Kepada Agensi</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

	<div id="TabbedPanels1" class="TabbedPanels">
  		<ul class="TabbedPanelsTabGroup">   		
   			<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);viewJumlahAward('$!id_hakmilik')" tabindex="1">Borang G dan H</li>
   			#if($id_borangg!="")<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);penerimaanBorangH('$!id_borangg')" tabindex="1">Penerimaan Borang H</li>#end
  		</ul>
  		
  		<div class="TabbedPanelsContentGroup">
  		
    		<!-- START TAB 1 -->
    		<div class="TabbedPanelsContent">
    
    		<br/>
    		
    		<fieldset>
  
    			#if($list_saiz > 5)
        		<div style="width:100%;height:100;overflow:auto"> 
        		#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>No</b></td>
    					<td>&nbsp;<b>No. PB</b></td>
    					<td>&nbsp;<b>Nama PB</b></td>
    					<td>&nbsp;<b>Jumlah Pampasan</b></td>
    				</tr>
    				
    			#if($list_saiz!=0)
           	 	#foreach($listP in $listSediaPampasan)
              		#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              				#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listP.bil</td>
                   		<td class="$row">&nbsp;$!listP.no_pb</td>
                   		<td class="$row">&nbsp;$!listP.nama_pb</td>
                   		<td class="$row">&nbsp;RM&nbsp;$!Utils.format2Decimal($listP.bayar_pampasan)</td>
              		<tr>
           		#end
        		#else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
        		#end
    				
    			</table>
    			
    			#if($list_saiz > 5)
        		</div>
        		#end
    		</fieldset>
    			
    		#if($mode=="new")	
    		<fieldset>
    			<table width="100%" border="0">
    				<tr>
    					<td width="1%">&nbsp;</td>
    					<td width="30%">Jumlah Award Keseluruhan (RM)</td>
    					<td width="1%">:</td>
    					<td width="68%"><input readonly class="disabled" type="text" maxlength="12" size="15" style="text-align:right" name="txtJumlahAward" id="txtJumlahAward" value="$!total_pampasan" onkeyup="validateNumber(this,this.value)" onblur="validateModal(this,this.value,'$!total_pampasan')" /></td>
    				</tr>
    				<tr>
    					<td><font color="red">*</font></td>
    					<td>Tarikh Borang G</td>
    					<td>:</td>
    					<td><input name="txdTarikhBorangG" id="txdTarikhBorangG" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangG',false,'dmy');">&nbsp;$!frmtdate</td>
    				</tr>
    				<tr>
    					<td><font color="red">*</font></td>
    					<td>Tarikh Borang H</td>
    					<td>:</td>
    					<td><input name="txdTarikhBorangH" id="txdTarikhBorangH" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangH',false,'dmy');">&nbsp;$!frmtdate</td>
    				</tr>
    			</table>
    		</fieldset>
    		#end	
    		
    		#if($mode=="view")	
    		#foreach($dataG in $dataBorangG)
    			#set($txtJumlahAward=$dataG.jumlah_award)
    			#set($txdTarikhBorangG=$dataG.tarikh_borangg)
    			#set($txdTarikhBorangH=$dataG.tarikh_borangh)
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
	
    		<fieldset>
    			<table width="100%" border="0">
    				<tr>
    					<td width="1%">&nbsp;</td>
    					<td width="30%">Jumlah Award Keseluruhan (RM)</td>
    					<td width="1%">:</td>
    					<td width="68%"><input readonly class="disabled" type="text" maxlength="12" size="15" style="text-align:right" name="txtJumlahAward" id="txtJumlahAward" value="$!total_pampasan" onkeyup="validateNumber(this,this.value)" onblur="validateModal(this,this.value,'$!txtJumlahAward')" /></td>
    				</tr>
    				<tr>
    					<td><font color="red">$M</font></td>
    					<td>Tarikh Borang G</td>
    					<td>:</td>
    					<td><input $disability $disabilityx name="txdTarikhBorangG" id="txdTarikhBorangG" size="11" type="text" value="$!txdTarikhBorangG" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangG',false,'dmy');">&nbsp;$!frmtdate#end</td>
    				</tr>
    				<tr>
    					<td><font color="red">$M</font></td>
    					<td>Tarikh Borang H</td>
    					<td>:</td>
    					<td><input $disability $disabilityx name="txdTarikhBorangH" id="txdTarikhBorangH" size="11" type="text" value="$!txdTarikhBorangH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            			#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangH',false,'dmy');">&nbsp;$!frmtdate#end</td>
    				</tr>
    			</table>
    		</fieldset>
    		#end		
    			
    		<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBorangG('$!id_permohonan','$!id_siasatan','$!id_borangg','$!mode')">
					#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangG('$!id_permohonan','$!id_borangg')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangG('$!id_permohonan','$!id_siasatan','$!id_borangg','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniTotalAward('$!id_hakmilik')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewlistHM('$!id_permohonan')">
					</td>
				</tr>
			</table>
    
    		</div>
  			<!-- END TAB 1 -->
  			
  
  			<!-- START TAB 2 -->
  			#if($id_borangg!="")
    		<div class="TabbedPanelsContent">
			
				#if($mode=="new")
				<fieldset>
					<table width="100%" border="0">
						<tr>
							<td width="1%"><font color="red">*</font></td>
							<td width="28%">Nama Penerima</td>
							<td width="1%">:</td>
							<td width="70%">$!selectPenerima</td>
						</tr>
						<tr>
    						<td>&nbsp;</td>
    						<td>Tarikh Serah</td>
    						<td>:</td>
    						<td><input name="txdTarikhSerah" id="txdTarikhSerah" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerah',false,'dmy');">&nbsp;$!frmtdate</td>
    					</tr>
    					<tr>
    						<td>&nbsp;</td>
    						<td>Tarikh Terima</td>
    						<td>:</td>
    						<td><input name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate</td>
    					</tr>
    					<tr>
    						<td>&nbsp;</td>
    						<td>Tarikh Akhir Bayaran Pampasan</td>
    						<td>:</td>
    						<td><input name="txdTarikhAkhirBayar" id="txdTarikhAkhirBayar" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAkhirBayar',false,'dmy');">&nbsp;$!frmtdate</td>
    					</tr>
    					
    					<!-- <tr>
    						<td><font color="red">*</font></td>
    						<td>Keputusan</td>
    						<td>:</td>
    						<td><select name="socKeputusanBorangH" id="socKeputusanBorangH" style="width:280px">
      		
      							<option value="">SILA PILIH</option>
      							<option value="1">MENERIMA TAWARAN</option>	
      							<option value="2">MENERIMA TAWARAN DENGAN BANTAHAN</option>		
      							<option value="3">TIDAK MENERIMA TAWARAN</option>	
    							<option value="4">TIDAK BERKENAAN</option>	
    		
							</select></td>
            			</tr> -->
    					
					</table>
				</fieldset>
				#end
			
				#if($mode=="view")
				
				#foreach($dataH in $dataBorangH)
					#set($txdTarikhSerah = $dataH.tarikh_serah)
					#set($txdTarikhTerima = $dataH.tarikh_terima)
					#set($txdTarikhAkhirBayar = $dataH.tarikh_akhir_bayar)
					#set($socKeputusanBorangH = $dataH.keputusan)
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
			
				<fieldset>
					<table width="100%" border="0">
						<tr>
							<td width="1%"><font color="red">$M</font></td>
							<td width="28%">Nama Penerima</td>
							<td width="1%">:</td>
							<td width="70%">$!selectPenerima</td>
						</tr>
						<tr>
    						<td>&nbsp;</td>
    						<td>Tarikh Serah</td>
    						<td>:</td>
    						<td><input $disability $disabilityx name="txdTarikhSerah" id="txdTarikhSerah" size="11" type="text" value="$!txdTarikhSerah" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSerah',false,'dmy');">&nbsp;$!frmtdate#end</td>
    					</tr>
    					<tr>
    						<td>&nbsp;</td>
    						<td>Tarikh Terima</td>
    						<td>:</td>
    						<td><input $disability $disabilityx name="txdTarikhTerima" id="txdTarikhTerima" size="11" type="text" value="$!txdTarikhTerima" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');">&nbsp;$!frmtdate#end</td>
    					</tr>
    					<tr>
    						<td>&nbsp;</td>
    						<td>Tarikh Akhir Bayaran Pampasan</td>
    						<td>:</td>
    						<td><input $disability $disabilityx name="txdTarikhAkhirBayar" id="txdTarikhAkhirBayar" size="11" type="text" value="$!txdTarikhAkhirBayar" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAkhirBayar',false,'dmy');">&nbsp;$!frmtdate#end</td>
    					</tr>
    					
    					<!-- <tr>
    						<td><font color="red">$M</font></td>
    						<td>Keputusan</td>
    						<td>:</td>
    						<td><select $disability1 $disabilityx name="socKeputusanBorangH" id="socKeputusanBorangH" style="width:280px">
      		
      							#if($socKeputusanBorangH=="1")
      							<option value="1">MENERIMA TAWARAN</option>	
      							<option value="">SILA PILIH</option>
      							<option value="2">MENERIMA TAWARAN DENGAN BANTAHAN</option>		
      							<option value="3">TIDAK MENERIMA TAWARAN</option>	
    							<option value="4">TIDAK BERKENAAN</option>	
      							#elseif($socKeputusanBorangH=="2")
      							<option value="2">MENERIMA TAWARAN DENGAN BANTAHAN</option>	
      							<option value="">SILA PILIH</option>
      							<option value="1">MENERIMA TAWARAN</option>		
      							<option value="3">TIDAK MENERIMA TAWARAN</option>	
    							<option value="4">TIDAK BERKENAAN</option>	
      							#elseif($socKeputusanBorangH=="3")
      							<option value="3">TIDAK MENERIMA TAWARAN</option>
      							<option value="">SILA PILIH</option>
      							<option value="1">MENERIMA TAWARAN</option>	
      							<option value="2">MENERIMA TAWARAN DENGAN BANTAHAN</option>			
    							<option value="4">TIDAK BERKENAAN</option>	
      							#elseif($socKeputusanBorangH=="4")
      							<option value="4">TIDAK BERKENAAN</option>	
      							<option value="">SILA PILIH</option>
      							<option value="1">MENERIMA TAWARAN</option>	
      							<option value="2">MENERIMA TAWARAN DENGAN BANTAHAN</option>		
      							<option value="3">TIDAK MENERIMA TAWARAN</option>	
      							#else
      							<option value="">SILA PILIH</option>
      							<option value="1">MENERIMA TAWARAN</option>	
      							<option value="2">MENERIMA TAWARAN DENGAN BANTAHAN</option>		
      							<option value="3">TIDAK MENERIMA TAWARAN</option>	
    							<option value="4">TIDAK BERKENAAN</option>	
      							#end
      						
							</select></td>
            			</tr> -->
    					
					</table>
				</fieldset>
				#end
			
			<table width="100%" border="0">
				<tr align="center">
					<td>
					
					#if($list_borangh!=0)
						<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport2')" />
					#end
					
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPenerimaanBorangH('$!id_permohonan','$!id_borangg','$!id_borangh','$!mode')">
					#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenerimaanBorangH('$!id_borangh')">
						<input type="button" name="cmdHapusH" value="Hapus" onClick="hapusBorangH('$!id_borangg','$!id_borangh')" >
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPenerimaanBorangH('$!id_permohonan','$!id_borangg','$!id_borangh','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalPenerimaan('$!id_borangh')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewlistHM('$!id_permohonan')">
					</td>
				</tr>
			</table>
			
			<fieldset>
			<legend><strong>Senarai Penerimaan Borang H</strong></legend>
  
  				#if($mode=="view")
    			<table width="100%" border="0">
    				<tr>
    					<td colspan="2"><input type="button" name="cmdTambahH" value="Tambah" onClick="javascript:penerimaanBorangH('$!id_borangg')"></td>
    				</tr>
    			</table>
    			#end
    			
    			#if($list_borangh > 5)
        		<div style="width:100%;height:100;overflow:auto"> 
        		#end
            	
    			<table width="100%" border="0">	
    				<tr class="table_header">
    					<td width="4%" align="center"><b>No</b></td>
    					<td width="50%">&nbsp;<b>Nama Penerima</b></td>
    					<td width="46%">&nbsp;<b>Tarikh Akhir Bayaran Pampasan</b></td>
    					<!-- <td width="31%">&nbsp;<b>Keputusan</b></td> -->
    				</tr>
    				
    			#if($list_borangh!=0)
           	 	#foreach($listH in $listBorangH)
              		#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              				#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    
               		<tr>
                   		<td class="$row" align="center">$!listH.bil</td>
                   		<td class="$row">&nbsp;<a href="javascript:viewPenerimaanBorangH('$!listH.id_borangh')"><font color="blue">$!listH.nama_pb</font></a></td>
                   		<td class="$row">&nbsp;$!listH.tarikh_akhir_bayar</td>
                   		<!-- <td class="$row">&nbsp;$!listH.jenis_keputusan</td> -->
              		<tr>
           		#end
        		#else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
        		#end
    				
    			</table>
    			
    			#if($list_borangh > 5)
        		</div>
        		#end
        		
    		</fieldset>
			
    		</div>
    		#end
    		<!-- END TAB 2 -->
    		
  		</div>
	</div>


</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
		 <td><a href="#" onClick="javascript:cetakBorangG('$!id_fail')"><font color="blue">Borang G</font></a></td>
	  </tr>
    </table>
</fieldset>	

<fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
		 <td><a href="#" onClick="javascript:cetakBorangH('$!id_fail')"><font color="blue">Borang H</font></a></td>
	  </tr>
    </table>
</fieldset>	

<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="command4">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_tanah" value="$!id_tanah">
<input type="hidden" name="id_bangunan" value="$!id_bangunan">
<input type="hidden" name="id_siasatan" value="$!id_siasatan">
<input type="hidden" name="id_pihakberkepentingan" value="$!id_pihakberkepentingan">
<input type="hidden" name="id_hakmilikpb" value="$!id_hakmilikpb">
<input type="hidden" name="id_award" value="$!id_award">
<input type="hidden" name="id_borangg" value="$!id_borangg">
<input type="hidden" name="id_borangh" value="$!id_borangh">

<!-- DATA TEMP -->
<input type="hidden" name="id_kategoritanah" value="$!id_kategoritanah">
<input type="hidden" name="luas_ambil" value="$!luas_ambil">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>
function cetakBorangH(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangH?id_Fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangG(idfail) {
    var url = "../servlet/ekptg.report.ppt.BorangG?id_Fail="+idfail;
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
function kemaskiniPenerimaanBorangH(id_borangh) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangh.value = id_borangh;
	document.${formName}.command.value = "viewPenerimaanBorangH";
	document.${formName}.command2.value = "kemaskiniPenerimaanBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function hapusBorangH(id_borangg,id_borangh) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangg.value = id_borangg;
	document.${formName}.id_borangh.value = id_borangh;
	document.${formName}.command.value = "hapusBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
/*
function hapusMaklumat(id_hakmilik,idAward) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_award.value = idAward;
	document.${formName}.command.value = "hapusMaklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
*/
function batalPenerimaan(id_borangh) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangh.value = id_borangh;
	document.${formName}.command.value = "viewPenerimaanBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewPenerimaanBorangH(id_borangh) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangh.value = id_borangh;
	document.${formName}.command.value = "viewPenerimaanBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanPenerimaanBorangH(id_permohonan,id_borangg,id_borangh,mode) {

	var dat1=document.${formName}.txdTarikhSerah;
	var dat2=document.${formName}.txdTarikhTerima;
	var dat3=document.${formName}.txdTarikhAkhirBayar;
	
	if(document.${formName}.socPenerima.value == ""){
		alert("Sila pilih \"Nama Penerima\" terlebih dahulu.");
  		document.${formName}.socPenerima.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false){
		dat2.focus()
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false){
		dat3.focus()
		return;
	}
/*	else if(document.${formName}.socKeputusanBorangH.value == ""){
		alert("Sila pilih \"Keputusan\" terlebih dahulu.");
  		document.${formName}.socKeputusanBorangH.focus(); 
		return;
	}
*/	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "1";
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.id_borangg.value = id_borangg;

		if(mode=="new"){
			document.${formName}.command.value = "penerimaanBorangH";
			document.${formName}.command2.value = "simpanPenerimaanBorangH";
		}else{
			document.${formName}.id_borangh.value = id_borangh;
			document.${formName}.command.value = "viewPenerimaanBorangH";
			document.${formName}.command2.value = "kemaskiniPenerimaanBorangH";
			document.${formName}.command3.value = "updatePenerimaanBorangH";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
		document.${formName}.submit();
	}
}
function penerimaanBorangH(id_borangg) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "1";
	document.${formName}.id_borangg.value = id_borangg;
	document.${formName}.command.value = "penerimaanBorangH";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kemaskiniBorangG(id_permohonan,id_borangg) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_borangg.value = id_borangg;
	document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.command2.value = "kemaskiniBorangG";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanBorangG(id_permohonan,id_siasatan,id_borangg,mode) {

	var dat1=document.${formName}.txdTarikhBorangG;
	var dat2=document.${formName}.txdTarikhBorangH;

	if(document.${formName}.txdTarikhBorangG.value == ""){
		alert("Sila masukkan \"Tarikh Borang G\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangG.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
	}
	else if(document.${formName}.txdTarikhBorangH.value == ""){
		alert("Sila masukkan \"Tarikh Borang H\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangH.focus(); 
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false){
		dat2.focus()
		return;
	}
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "0";
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.id_siasatan.value = id_siasatan;

		if(mode=="new"){
			document.${formName}.command.value = "viewJumlahAward";
			document.${formName}.command2.value = "simpanBorangG";
		}else{
			document.${formName}.id_borangg.value = id_borangg;
			document.${formName}.command.value = "viewJumlahAward";
			document.${formName}.command2.value = "kemaskiniBorangG";
			document.${formName}.command3.value = "updateBorangG";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
		document.${formName}.submit();
	}
}
function batalKemaskiniTotalAward(id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewJumlahAward(id_hakmilik) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
/*
function listPenyediaanPampasan(idHakmilik) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "listPenyediaanPampasan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}

function batalKemaskiniPenyediaan(idAward) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_award.value = idAward;
	document.${formName}.command.value = "viewPenyediaanPampasan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewPenyediaanPampasan(idAward,idHakmilik) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_award.value = idAward;
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "viewPenyediaanPampasan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kemaskiniPenyediaanPampasan(idPermohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idPermohonan;
	document.${formName}.command.value = "viewPenyediaanPampasan";
	document.${formName}.command2.value = "kemaskiniPenyediaanPampasan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function PBgetDataUpdate() {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.command.value = "viewPenyediaanPampasan";
	document.${formName}.command2.value = "kemaskiniPenyediaanPampasan";
	document.${formName}.command3.value = "PBgetDataUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function simpanMaklumatPampasan(idpermohonan,idAward,mode) {

	if(document.${formName}.socPB.value == ""){
		alert("Sila pilih \"Nama PB\" terlebih dahulu.");
  		document.${formName}.socPB.focus(); 
		return;
	}
	else if(document.${formName}.txtNoPB.value == ""){
		alert("Sila masukkan \"No.PB\" terlebih dahulu.");
  		document.${formName}.txtNoPB.focus(); 
		return;
	}
	else if(document.${formName}.txtSyorAtas.value == "" || document.${formName}.txtSyorBawah.value == ""){
		alert("Sila lengkapkan \"Syer\" terlebih dahulu.");
  		document.${formName}.txtSyorAtas.focus(); 
		return;
	}
	else if(document.${formName}.txtLuasAmbil.value == ""){
		alert("Sila masukkan \"Luas Ambil\" terlebih dahulu.");
  		document.${formName}.txtLuasAmbil.focus(); 
		return;
	}
	else if(document.${formName}.unitLuas.value == ""){
		alert("Sila pilih \"Unit Luas Ambil\" terlebih dahulu.");
  		document.${formName}.unitLuas.focus(); 
		return;
	}
	else if(document.${formName}.socStatusPenerima.value == ""){
		alert("Sila pilih \"Jenis Pampasan\" terlebih dahulu.");
  		document.${formName}.socStatusPenerima.focus(); 
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.tabId.value = "0";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "listPenyediaanPampasan";
			document.${formName}.command2.value = "PBgetData";
			document.${formName}.command3.value = "simpanMaklumatPampasan";
		}else{
			document.${formName}.id_award.value = idAward;
			document.${formName}.command.value = "viewPenyediaanPampasan";
			document.${formName}.command2.value = "kemaskiniPenyediaanPampasan";
			document.${formName}.command3.value = "updateMaklumatPampasan";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
		document.${formName}.submit();
	}
}
function PBgetData() {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.tabId.value = "0";
	document.${formName}.command.value = "listPenyediaanPampasan";
	document.${formName}.command2.value = "PBgetData";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
*/
function kembali() {
	document.${formName}.command.value = "xxx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewlistHM(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
</script>


<!-- KIRA KIRA -->
<script>
/*
function addTotalUpdate() {
	
	if(document.${formName}.socPB.value!="" && document.${formName}.txtSyorAtas.value!=""
	   && document.${formName}.txtSyorBawah.value!=""){
		   
		var HDlainPampasan = parseFloat(document.${formName}.hdLainPampasan.value);
		var lainPampasan = parseFloat(document.${formName}.txtLainPampasan.value);
		document.${formName}.TEMPhdLainPampasan.value = lainPampasan;
		var TEMPHDlainPampasan = parseFloat(document.${formName}.TEMPhdLainPampasan.value);
		var total = parseFloat(document.${formName}.hdJumlahPampasan.value);
		
			var lain = "";
			if(document.${formName}.hdLainPampasan.value!=""){
				lain = lainPampasan - HDlainPampasan;
			}else{
				lain = lainPampasan;
			}
	
			if(document.${formName}.txtLainPampasan.value != ""){
				document.${formName}.txtJumlahPampasan.value = (total + lain).toFixed(2);
			}else{
				if(document.${formName}.TEMPhdLainPampasan.value!="NaN" && document.${formName}.TEMPhdLainPampasan.value!=""){
					document.${formName}.txtJumlahPampasan.value = (total - TEMPHDlainPampasan).toFixed(2);
				}else{
					if(document.${formName}.hdLainPampasan.value!="NaN" && document.${formName}.hdLainPampasan.value!=""){
						document.${formName}.txtJumlahPampasan.value = (total - HDlainPampasan).toFixed(2);
					}else{
						document.${formName}.txtJumlahPampasan.value = total;
					}
				}
			}	
	
		
	}else{
		alert("Sila pastikan maklumat diatas dilengkapkan terlebih dahulu.");
		document.${formName}.txtLainPampasan.value = "";
		document.${formName}.socPB.focus(); 
		return;
	}
}
function addTotal() {

	if(document.${formName}.socPB.value!="" && document.${formName}.txtSyorAtas.value!=""
	&& document.${formName}.txtSyorBawah.value!=""){
		   
		var total = parseFloat(document.${formName}.hdJumlahPampasan.value);
		var lainPampasan = parseFloat(document.${formName}.txtLainPampasan.value);
	
		if(document.${formName}.txtLainPampasan.value != ""){
			document.${formName}.txtJumlahPampasan.value = (total + lainPampasan).toFixed(2);
		}else{
			document.${formName}.txtJumlahPampasan.value = total;
		}

	}else{
			alert("Sila pastikan maklumat diatas dilengkapkan terlebih dahulu.");
			document.${formName}.txtLainPampasan.value = "";
			document.${formName}.socPB.focus(); 
			return;
	}
}
function editTotalBhgPB() {

	if(document.${formName}.socPB.value!="" && document.${formName}.txtSyorAtas.value!=""
	&& document.${formName}.txtSyorBawah.value!=""){
		   
		//var total = parseFloat(document.${formName}.hdJumlahPampasan.value);
		
		var lainPampasan = 0.00;
		if(document.${formName}.txtLainPampasan.value!=""){
			lainPampasan = parseFloat(document.${formName}.txtLainPampasan.value);
		}

		var txtTanahAward = 0.00;
		if(document.${formName}.txtTanahAward.value!=""){
			txtTanahAward = parseFloat(document.${formName}.txtTanahAward.value);
		}
		
		var txtPecahpisahAward = 0.00;
		if(document.${formName}.txtPecahpisahAward.value!=""){
			txtPecahpisahAward = parseFloat(document.${formName}.txtPecahpisahAward.value);
		}
		
		var txtPenjejasanAward = 0.00;
		if(document.${formName}.txtPenjejasanAward.value!=""){
			txtPenjejasanAward = parseFloat(document.${formName}.txtPenjejasanAward.value);
		}

		var txtNaikAward = 0.00;
		if(document.${formName}.txtNaikAward.value!=""){
			txtNaikAward = parseFloat(document.${formName}.txtNaikAward.value);
		}

		var txtBangunanAward = 0.00;
		if(document.${formName}.txtBangunanAward.value!=""){
			txtBangunanAward = parseFloat(document.${formName}.txtBangunanAward.value);
		}
		
		document.${formName}.txtJumlahPampasan.value = (lainPampasan + txtTanahAward + txtPecahpisahAward + txtPenjejasanAward + txtNaikAward + txtBangunanAward).toFixed(2);

	}else{
			alert("Sila pastikan maklumat diatas dilengkapkan terlebih dahulu.");
			document.${formName}.socPB.focus(); 
			return;
	}
}
function validateSyer() {

	//syer
	var sA = parseFloat(document.${formName}.txtSyorAtas.value);
	var sB = parseFloat(document.${formName}.txtSyorBawah.value);

	//nilai asal
	//var txtFeeAsal = parseFloat(document.${formName}.txtFeeAsal.value);
	var txtTanahAsal = parseFloat(document.${formName}.txtTanahAsal.value);
	var txtPecahpisahAsal = parseFloat(document.${formName}.txtPecahpisahAsal.value);
	var txtPenjejasanAsal = parseFloat(document.${formName}.txtPenjejasanAsal.value);
	var txtNaikAsal = parseFloat(document.${formName}.txtNaikAsal.value);
	var txtBangunanAsal = parseFloat(document.${formName}.txtBangunanAsal.value);
	
	//lain lain pampasan
	var lainPampasan = parseFloat(document.${formName}.txtLainPampasan.value).toFixed(2);
	
	if(sA > sB){
		alert("Sila pastikan \"Syer Atas\" tidak melebihi \"Syer Bawah\"");
	}else{
		var bhg = sA / sB;

		if(document.${formName}.txtSyorAtas.value != "" && document.${formName}.txtSyorBawah.value != ""){

			//var txtFeeAward = parseFloat((txtFeeAsal * bhg).toFixed(2));
			var txtTanahAward = parseFloat((txtTanahAsal * bhg).toFixed(2));
			var txtPecahpisahAward = parseFloat((txtPecahpisahAsal * bhg).toFixed(2));
			var txtPenjejasanAward = parseFloat((txtPenjejasanAsal * bhg).toFixed(2));
			var txtNaikAward = parseFloat((txtNaikAsal * bhg).toFixed(2));
			var txtBangunanAward = parseFloat((txtBangunanAsal * bhg).toFixed(2));
			
			//document.${formName}.txtFeeAward.value = txtFeeAward;
			document.${formName}.txtTanahAward.value = txtTanahAward;
			document.${formName}.txtPecahpisahAward.value = txtPecahpisahAward;
			document.${formName}.txtPenjejasanAward.value = txtPenjejasanAward;
			document.${formName}.txtNaikAward.value = txtNaikAward;
			document.${formName}.txtBangunanAward.value = txtBangunanAward;

			var total = (txtBangunanAward + txtTanahAward + txtPecahpisahAward + txtPenjejasanAward + txtNaikAward).toFixed(2);

			if(document.${formName}.txtLainPampasan.value!=""){
				document.${formName}.txtJumlahPampasan.value = parseFloat(total) + parseFloat(lainPampasan);
			}else{
				document.${formName}.txtJumlahPampasan.value = total;	
			}	
			
			document.${formName}.hdJumlahPampasan.value = total;
	
		}else{
			//document.${formName}.txtFeeAward.value = "0.00";
			document.${formName}.txtTanahAward.value = "0.00";
			document.${formName}.txtPecahpisahAward.value = "0.00";
			document.${formName}.txtPenjejasanAward.value = "0.00";
			document.${formName}.txtNaikAward.value = "0.00";
			document.${formName}.txtBangunanAward.value = "0.00";
			
			document.${formName}.txtJumlahPampasan.value = "0.00";
			document.${formName}.hdJumlahPampasan.value = "0.00";
		}
	}
}
*/
</script>


<script>
window.onload = submitForm;
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});
function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}

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
      var strValidCharacters = "1234567890./";
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
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	}else{
		elmnt.value = "";
	}
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