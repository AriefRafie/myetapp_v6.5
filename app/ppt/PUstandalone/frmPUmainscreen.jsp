<script type="text/javascript"  src="../library/js/fraction.js"></script>


#parse("app/ppt/frmLabelSet.jsp")
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")
	
	#if($mode=="view")
		
		#if($onchange=="no")
			#foreach($data in $dataPU)
				#set($txtNoHakmilik=$data.NO_HAKMILIK)
				#set($txtNoLot=$data.NO_LOT)
				#set($txdTarikhBorangK=$data.TARIKH_BORANGK)
				#set($txtLuasAmbil=$data.LUAS_AMBIL_HM)
				#set($sorDropdownUnit=$data.ID_UNITLUASAMBIL)
                
                #set($txtLuasAsal=$data.LUAS_ASAL_HM)
				#set($sorDropdownUnitAsal=$data.ID_UNITLUASASAL)
			
				#set($txtNoFailPU=$data.NO_FAIL_PU)
				#set($txtNoPelan=$data.NO_PELAN)
				#set($txtNoPU=$data.NO_PU)
				#set($txdTarikhSuratPTG=$data.TARIKH_SURAT_PTG)
				#set($txdTarikhHantarJUPEM=$data.TARIKH_HANTAR_JUPEM)
				#set($txdTarikhBorangPU=$data.TARIKH_PU)
				
				#set($txdTarikhTerimaPA=$data.TARIKH_TERIMA_PA)
			    #set($txtNoPA=$data.NO_PA)
			    #set($txdTarikhTerimaSA=$data.TARIKH_TERIMA_SA)
			    #set($txdTarikhPenyelesaian=$data.TARIKH_SELESAI)
			    #set($txtCatatan=$data.CATATAN)
			    #set($txtLotBaru=$data.NO_LOT_BARU)
			    
			    #set($txtKeluasanPU=$Utils.formatLuasHM($Utils.parseDouble($data.LUAS_PU)))
			    #set($countBezaLuas=$Utils.formatLuasHM($Utils.parseDouble($data.BEZA_LUAS)))
		    	#set($countBakiLuas=$Utils.formatLuasHM($Utils.parseDouble($data.BAKI_LUAS)))
		    	#set($lblLuasAsal=$Utils.formatLuasHM($Utils.parseDouble($data.LUAS_ASAL)))
		    	#set($lblLuasAmbil=$Utils.formatLuasHM($Utils.parseDouble($data.LUAS_AMBIL)))
		    	#set($sorJenisPelarasan=$data.JENIS_PELARASAN)
                
                
                
                #set($txtNoFailPUJUPEM=$data.NO_FAIL_JUPEM_SA)
                #set($txtNoFailProjek=$data.NO_FAIL_PROJEK_SA)
                #set($txtTujuanProjek=$data.TUJUAN_PROJEK_SA)
                #set($txdTarikhWarta=$data.TARIKH_WARTA_SA)
                #set($txtNoWarta=$data.NO_WARTA_SA)
                #set($txtLuasBorangK=$data.LUAS_BORANGK_SA)
                #set($sorDropdownUnitBorangK=$data.ID_UNITBORANGK_SA)
                #set($txtNoWK=$data.NO_WK_SA)
                #set($txtNoPAasal=$data.NO_PA_ASAL_SA)
            
            
                
              
                
                
                
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
	
	#else
		#set($M = "*")
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
	#end
	
	
	

<fieldset id="top">
<legend><strong>MAKLUMAT PERMINTAAN UKUR</strong></legend>

<br/>

	<fieldset>
	<legend><strong>PENYEDIAAN PU</strong></legend>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%"><font color="red">$M</font></td>
  			<td width="30%"><b>No. Rujukan (Permintaan Ukur)</b></td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx type="text" name="txtNoFailPU" id="txtNoFailPU" value="$!txtNoFailPU" size="33" maxlength="40" ></td>
  		</tr>
        
	</table>
    
    <table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Projek</b></td></tr>
	</table>
    
    <table width="100%" border="0">
		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">No. Fail</td>
  			<td width="1%">:</td>
  			<td width="68%">
  				<input $!disability $!disabilityx type="text" name="txtNoFailProjek" id="txtNoFailProjek" value="$!txtNoFailProjek" size="33" maxlength="40" >
  			</td>
  		</tr>
        <tr>
  			<td>&nbsp;</td>
  			<td valign="top">Tujuan/Projek</td>
  			<td>:</td>
  			<td>
        
           <textarea name="txtTujuanProjek" id="txtTujuanProjek" cols="80"   rows="6"         
                            
          $!disability $!disabilityx
        >$!txtTujuanProjek</textarea>
                
       
          
            </td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Warta</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx type="text" name="txtNoWarta" id="txtNoWarta" value="$!txtNoWarta" size="33" maxlength="40" ></td>
  		</tr>
        <tr>
  			<td>&nbsp;</td>
  			<td>Tarikh Warta</td>
  			<td>:</td>
  			<td>
            <input $!disability $!disabilityx name="txdTarikhWarta" id="txdTarikhWarta" size="11" type="text" value="$!txdTarikhWarta" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhWarta',false,'dmy');">&nbsp;$!frmtdate #end
            
            
            </td>
  		</tr>
	</table>
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Hakmilik Asal</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">Daerah</td>
  			<td width="1%">:</td>
  			<td width="68%">
  				$!selectDaerah
  				#if($mode=="new")
  				<input type="button" name="cmdGetData" value ="Capaian Maklumat Hakmilik" onClick="javascript:getPopupDataHM()">
  				#end
  			</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Mukim</td>
  			<td>:</td>
  			<td>$!selectMukim</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Jenis Hakmilik</td>
  			<td>:</td>
  			<td>$!selectJenisHakmilik</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Hakmilik</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="20" maxlength="40" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Lot / No. PT</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoLot" id="txtNoLot" value="$!txtNoLot" size="20" maxlength="40" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Tarikh Borang K Didaftarkan</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhBorangK" id="txdTarikhBorangK" size="11" type="text" value="$!txdTarikhBorangK" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangK',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
        
         <tr>
  			<td><font color="red">$M</font></td>
  			<td>Luas Asal</td>
  			<td>:</td>
  			<td>
  			<input $!disability $!disabilityx type="text" name="txtLuasAsal" id="txtLuasAsal" value="$!txtLuasAsal" size="20" maxlength="40" onblur="validateLuas(this,this.value,'$!txtLuasAsal');"  onkeyup="validateNilai(this,this.value);" >
  			&nbsp;
  			<select $!disability1 $!disabilityx name="sorDropdownUnitAsal" style="width:132px">
  				<option value="" #if($sorDropdownUnitAsal=="") selected=selected #end >SILA PILIH</option>
      			<option value="1" #if($sorDropdownUnitAsal=="1") selected=selected #end >HEKTAR</option>
				<option value="2" #if($sorDropdownUnitAsal=="2") selected=selected #end >METER PERSEGI</option>
  			</select>
  			</td>
  		</tr>
        
        
        
        
        <tr>
  			<td><font color="red">$M</font></td>
  			<td>Luas Diambil</td>
  			<td>:</td>
  			<td>
            
           
            <input $!disability $!disabilityx type="text" name="txtLuasBorangK" id="txtLuasBorangK" value="$!txtLuasBorangK" size="20" maxlength="40" onblur="validateLuas(this,this.value,'$!txtLuasBorangK')" onkeyup="validateNilai(this,this.value);" >
  			&nbsp;
  			<select $!disability1 $!disabilityx name="sorDropdownUnitBorangK" style="width:132px">
  				<option value="" #if($sorDropdownUnitBorangK=="") selected=selected #end >SILA PILIH</option>
      			<option value="1" #if($sorDropdownUnitBorangK=="1") selected=selected #end >HEKTAR</option>
				<option value="2" #if($sorDropdownUnitBorangK=="2") selected=selected #end >METER PERSEGI</option>
  			</select>
  			
  			</td>
  		</tr>
        
        <tr>
  			<td><font color="red">$M</font></td>
  			<td>Luas Diambil (Borang K)</td>
  			<td>:</td>
  			<td>
  			<input $!disability $!disabilityx type="text" name="txtLuasAmbil" id="txtLuasAmbil" value="$!txtLuasAmbil" size="20" maxlength="40" onblur="validateLuas(this,this.value,'$!txtLuasAmbil')" onkeyup="validateNilai(this,this.value);" >
  			&nbsp;
  			<select $!disability1 $!disabilityx name="sorDropdownUnit" style="width:132px">
  				<option value="" #if($sorDropdownUnit=="") selected=selected #end >SILA PILIH</option>
      			<option value="1" #if($sorDropdownUnit=="1") selected=selected #end >HEKTAR</option>
				<option value="2" #if($sorDropdownUnit=="2") selected=selected #end >METER PERSEGI</option>
  			</select>
  			</td>
  		</tr>
        
        <tr style="display:none;">
  			<td></td>
  			<td>No. W.K</td>
  			<td>:</td>
  			<td>
            <input $!disability $!disabilityx type="text" name="txtNoWK" id="txtNoWK" value="$!txtNoWK" size="33" maxlength="40" >
            
            </td>
  		</tr>
       
        <tr>
  			<td></td>
  			<td>No. PA dan B1 (Asal)</td>
  			<td>:</td>
  			<td>
            <input $!disability $!disabilityx type="text" name="txtNoPAasal" id="txtNoPAasal" value="$!txtNoPAasal" size="33" maxlength="40" >
            
            </td>
  		</tr>
        
  	
  	</table>
  	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Sijil Pengecualian Bayaran Ukur</b></td></tr>
	</table>
	
	<table width="100%" border="0">
  		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">Tarikh Surat PTD</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhSuratPTG" id="txdTarikhSuratPTG" size="11" type="text" value="$!txdTarikhSuratPTG" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratPTG',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Syit</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx type="text" name="txtNoPelan" id="txtNoPelan" value="$!txtNoPelan" size="33" maxlength="40" ></td>
  		</tr> 		
  	</table>
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Borang PU</b></td></tr>
	</table>
	
	<table width="100%" border="0">
    <tr>
  			<td width="1%"></td>
  			<td width="30%">No. Fail JUPEM</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx type="text" name="txtNoFailPUJUPEM" id="txtNoFailPUJUPEM" value="$!txtNoFailPUJUPEM" size="33" maxlength="40" ></td>
  		</tr>
		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">Tarikh Hantar Ke JUPEM</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhHantarJUPEM" id="txdTarikhHantarJUPEM" size="11" type="text" value="$!txdTarikhHantarJUPEM" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantarJUPEM',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. PU</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx type="text" name="txtNoPU" id="txtNoPU" value="$!txtNoPU" size="20" maxlength="20"   ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Tarikh Borang PU</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhBorangPU" id="txdTarikhBorangPU" size="11" type="text" value="$!txdTarikhBorangPU" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangPU',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
	</table>
  					
	</fieldset>
	
<br/>

	<fieldset>
	<legend><strong>PENYELESAIAN SA/PA DAN B1</strong></legend>
	
	<table width="100%" border="0">
  		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">Tarikh Terima PA (Pelan Akui) dan B1</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhTerimaPA" id="txdTarikhTerimaPA" size="11" type="text" value="$!txdTarikhTerimaPA" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaPA',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td><font color="red">$M</font></td>
  			<td>No. PA dan B1</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx type="text" name="txtNoPA" id="txtNoPA" value="$!txtNoPA" size="20" maxlength="20"   ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. LOT Baru</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx type="text" name="txtLotBaru" id="txtLotBaru" value="$!txtLotBaru" size="20" maxlength="20"   ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Tarikh Terima SA (Surihan Alih)</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhTerimaSA" id="txdTarikhTerimaSA" size="11" type="text" value="$!txdTarikhTerimaSA" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
           	#if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaSA',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
	  		<td>&nbsp;</td>
	  		<td>Tarikh Penyelesaian</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhPenyelesaian" id="txdTarikhPenyelesaian" size="11" type="text" value="$!txdTarikhPenyelesaian" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPenyelesaian',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td valign="top">Catatan</td>
  			<td valign="top">:</td>
  			<td valign="top"><textarea $!disability $!disabilityx name="txtCatatan" id="txtCatatan" rows="4" cols="50%" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,400);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,400);" >$!txtCatatan</textarea></td>
  		</tr>
  	</table>
	
	</fieldset>

<br/>
#if($!id_pu!="")


	<fieldset>
	<legend><strong>MAKLUMAT TUAN TANAH</strong></legend>
	
    <div id="div_pb"></div>
	
	</fieldset>	
    <br />
	<fieldset>
	<legend><strong>PELARASAN LUAS</strong></legend>
	
    <div id="div_pelarasan"></div>
	
	</fieldset>	
#else
  <font color="red">Sila pastikan maklumat diatas disimpan terlebih dahulu sebelum membuat pelarasan.</font>
#end
    
	
	<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanDataPU('','$!mode')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
                        <input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReportPU')" />
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniDataPU('$!id_pu')">
						<input type="button" name="cmdHapus" value ="Hapus" onClick="javascript:hapusDataPU('$!id_pu')">
                        
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanDataPU('$!id_pu','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:viewPU('$!id_pu')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:backToList()">
						</td>
					</tr>
				</table>
				
</fieldset>



<fieldset id="tableReportPU" style="display:none;">
				<legend><strong>SENARAI LAPORAN</strong></legend>
					<table width="100%" border="0" cellspacing="2" cellpadding="2">
				      <tr>
						 <td><a href="#" onClick="javascript:cetakCoveringPU('$!id_pu','')"><font color="blue">Covering Borang Permintaan Ukur</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakBorangPU('$!id_pu','')"><font color="blue">Borang Permintaan Ukur</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakLampiranAPU('$!id_pu','')"><font color="blue">Lampiran A</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakLampiranBPU('$!id_pu')"><font color="blue">Lampiran B</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakCoveringSijilPU('$!id_pu','')"><font color="blue">Covering Mohon Sijil Pengecualian Bayaran Ukur</font></a></td>
					  </tr>
					  <tr>
						 <td><a href="#" onClick="javascript:cetakMinitSijilPU('$!id_pu','')"><font color="blue">Minit Mohon Sijil Pengecualian Bayaran Ukur</font></a></td>
					  </tr>
				    </table>
				</fieldset>


<input type="hidden" name="id_hakmilik">


<input type="hidden" name="mode" value='$!mode'>
<input type="hidden" name="isEdit" value='$!isEdit'>
<input type="hidden" name="id_pu" value="$!id_pu">


<input type="hidden" name="command2">
<input type="hidden" name="command3">


<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<input type="hidden" name="selectedTabPelarasan" id="selectedTabPelarasan"  value="$!selectedTabPelarasan"  />

#if($!id_pu!="")

<script> 

$jquery(document).ready(function () {	
		doDivAjaxCall$formname('div_pb','getpb','');
		doDivAjaxCall$formname('div_pelarasan','getPelarasan','');	
});


</script>

#end


<script>

//check_length(document.${formName}.txtTujuanProjek,'4000','txtTujuanProjek_check','txtTujuanProjek_num','normal','yes','tujuan');

function cetakCoveringPU(id_permintaanukur,id_permohonan) {
	//var url = "../servlet/ekptg.report.ppt.CoveringPU_SA?id_permintaanukur="+id_permintaanukur;
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=CoveringPU_SA&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangPU(id_permintaanukur,id_permohonan) {
	var url = "../servlet/ekptg.report.ppt.BorangPU_SA?id_permintaanukur="+id_permintaanukur;
	//var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=borangPU&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakLampiranAPU(id_permintaanukur,id_permohonan) {
	var url = "../servlet/ekptg.report.ppt.LampiranAPU_SA?id_permintaanukur="+id_permintaanukur;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakLampiranBPU(id_permintaanukur) {
	var url = "../servlet/ekptg.report.ppt.LampiranBPU_SA?id_permintaanukur="+id_permintaanukur;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakCoveringSijilPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=coveringSijilPU_SA&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}


function cetakMinitSijilPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=minitSijilPU_SA&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

/*
function cetakMinitSijilPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=minitSijilPU&selectNoFail=yes&flagShowTarikhCetak=no";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakCoveringSijilPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=coveringSijilPU&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakLampiranBPU(id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+id_permohonan+"&report=lampiranBPU&selectNoFail=yes&flagShowTarikhCetak=no";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakLampiranAPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=lampiranAPU&selectNoFail=yes&flagShowTarikhCetak=no";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangPU(id_permintaanukur,id_permohonan) {
	var url = "../servlet/ekptg.report.ppt.borangPU?id_permintaanukur="+id_permintaanukur;
	//var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=borangPU&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakCoveringPU(id_permintaanukur,id_permohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permintaanukur="+id_permintaanukur+"&id_permohonan="+id_permohonan+"&report=coveringPU&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
*/


function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");
}


function viewPB(id_pupb)
{
	document.${formName}.ID_PUPB.value = id_pupb;
	doDivAjaxCall$formname('div_pb','viewPB','');
	//doDivAjaxCall$formname('div_pelarasan','getPelarasan','');		
}

function hapusPBPU(id_pupb)
{
	document.${formName}.ID_PUPB.value = id_pupb;
	doDivAjaxCall$formname('div_pb','hapusPB','');
	doDivAjaxCall$formname('div_pelarasan','getPelarasan','');		
}

function simpanPBPU()
{
	if(document.${formName}.txtNamaPBPU.value == ""){
		alert("Sila masukkan \"Nama Pemilik\" terlebih dahulu.");
  		document.${formName}.txtNamaPBPU.focus(); 
		return;		
	}
	else if(document.${formName}.txtNoPBPU.value == ""){
		alert("Sila masukkan \"No. Pemilik\" terlebih dahulu.");
  		document.${formName}.txtNoPBPU.focus(); 
		return;		
	}else if(document.${formName}.txtSyerAtasPU.value == ""){
		alert("Sila masukkan \"Syer Atas\" terlebih dahulu.");
  		document.${formName}.txtSyerAtasPU.focus(); 
		return;		
	}else if(document.${formName}.txtSyerBawahPU.value == ""){
		alert("Sila masukkan \"Syer Bawah\" terlebih dahulu.");
  		document.${formName}.txtSyerBawahPU.focus(); 
		return;		
	}
	else if(parseFloat(document.${formName}.txtSyerAtasPU.value)>parseFloat(document.${formName}.txtSyerBawahPU.value)){
		alert("Sila pastikan Syer Atas lebih kecil atau sama dengan Syer Bawah.");
  		document.${formName}.txtSyerAtasPU.focus(); 
		return;		
	}
	else{
		
		if(parseFloat(document.${formName}.txtSyerAtasPU.value)==parseFloat(document.${formName}.txtSyerBawahPU.value))
		{
			document.${formName}.txtSyerAtasPU.value = "1";
			document.${formName}.txtSyerBawahPU.value = "1";
		}	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	doDivAjaxCall$formname('div_pb','simpanpb','');
	doDivAjaxCall$formname('div_pelarasan','getPelarasan','');		
	
	
	}
	
}

function tambahPBPU()
{
doDivAjaxCall$formname('div_pb','tambahpb','');	
//doDivAjaxCall$formname('div_pb','getpb','');

}

function klikTab(tab_id)
{
window.location.hash='TabbedPanelsPelarasan';
document.${formName}.selectedTabPelarasan.value = tab_id;	
//alert("1");
}
/*
function printDiv()
{
//window.location.href = CollinsPDF(document.getElementById(id).innerHTML,'return as DataUrl'); 

var html = "";
var css = "<style type=\"text/css\">"+
		  "td.td_total_pelarasan{"+
		  "border-top:1px solid blue;"+
		  "border-bottom:3px  double blue;"+
		  "}"+
		  "td.td_total_pengiraan{"+
		  "border-top:1px solid black;"+
		  "}"+		
		  "</style> ";
		  html = css;
		  
		  html = html + "<table  align=\"center\" width=\"100%\"><tr><td valign=\"top\" width=\"5%\"></td><td valign=\"top\" width=\"85%\">";
		  html = html + "<br><br>&nbsp;&nbsp;<b><font style=\"font-size:12px;font-family:Arial;\" >PELARASAN PERBEZAAN LUAS</font></b>";
		  html = html + "<br><br>";
		  html = html + "<div style=\"font-size:12px;font-family:Arial;\" >"+document.getElementById('print_1').innerHTML+"</div>";
		  html = html + "<br>";
		  html = html + "<div style=\"font-size:12px;font-family:Arial;\" >"+document.getElementById('print_2').innerHTML+"</div>";
		  html = html + "<br>";
		  html = html + "<div style=\"font-size:12px;font-family:Arial;\" >"+document.getElementById('print_4').innerHTML+"</div>";
  		  html = html + "</td><td valign=\"top\" width=\"10%\"></td></tr></table>";

window.location.href = CollinsPDF(html,'Return as DataURL');
}
*/

function setTab(tab_index)
{
	
	
	var tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan");
	
	if(tab_index == null || tab_index == "")
	{
	//alert("tab_index 1:"+tab_index);	
	document.${formName}.selectedTabPelarasan.value = "0";
	tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:0});	
	}
	else
	{
	//alert("tab_index 2:"+tab_index);
	document.${formName}.selectedTabPelarasan.value = tab_index;	
	
		if(tab_index==0)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:0});
		}
		else if(tab_index==1)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:1});
		}
		else if(tab_index==2)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:2});
		}
		else if(tab_index==3)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:3});
		}	
		else if(tab_index==4)
		{
		tp = new Spry.Widget.TabbedPanels("TabbedPanelsPelarasan",{defaultTab:4});
		}	
		
	}
	//alert("4");
}

function kiraBezaLuasNew(saiz_pb) {
	
	
	//alert("1");
	var luas_baru = parseFloat(document.${formName}.txtKeluasanPU.value);
	var baki_luas = parseFloat(document.${formName}.bakiLuasNew.value);
	var nilai_seunit_field = parseFloat(document.${formName}.NilaiSeunit.value);
	
	var total_bayaran_lewat = 0;
    var total_jumlah_hari = 0;

	var beza_luas = 0;
	var beza_luas_mp = 0;
	var nilai_seunit = 0;
	
	
	if (!isNaN(luas_baru) && !isNaN(baki_luas)){
		beza_luas =  baki_luas -luas_baru;
		beza_luas_mp = beza_luas * 10000;
		
		if(beza_luas_mp < 0)
		{
			beza_luas_mp = beza_luas_mp*-1;
		}
	}
	
	if (!isNaN(nilai_seunit_field)){
		nilai_seunit = nilai_seunit_field;
	}

	document.${formName}.countBezaLuas.value = beza_luas.toFixed(4);
	document.${formName}.countBezaLuasMp.value = beza_luas_mp.toFixed(4);
	$jquery("#beza_luas").html("<font color='blue'><b>"+beza_luas.toFixed(4)+"</b></font>");		
	$jquery("#beza_luas_mp").html("<font color='blue'>"+beza_luas_mp.toFixed(4)+"</font>");	 
	$jquery("#jumlah_nilai").html("<font color='blue'><b>RM "+formatCurrency(beza_luas_mp*nilai_seunit)+"</b></font>");	 
	$jquery("#jumlah_nilai_bhg").html("<font color='blue'><b>RM "+formatCurrency(beza_luas_mp*nilai_seunit)+"</b></font>");	 
	
	
	 
	if(beza_luas > 0){
		document.${formName}.sorJenisPelarasan.value = "1";
		document.${formName}.hdJenisPelarasan.value = "1";
		 $jquery("#jenis_pelarasan").html("<font color='blue'>PAMPASAN TAMBAHAN</font>");	 
		 $jquery("#jenis_pelarasan_nilai").html("<font color='blue'>(PAMPASAN TAMBAHAN)</font>");	 
		 $jquery("#jenis_pelarasan_nilai_bhg").html("<font color='blue'>(PAMPASAN TAMBAHAN)</font>");	
		 document.getElementById('fs_bayaran_lewat').style.display=""; 
		 document.getElementById('fs_pelarasan_bayaran_lewat').style.display=""; 
		 document.getElementById('tab_caj_1').style.display="";
		 document.getElementById('tab_caj_2').style.display=""; 	
		 document.getElementById('tab_caj_3').style.display="";	 	 
	}else if(beza_luas < 0){
		document.${formName}.sorJenisPelarasan.value = "2";
		document.${formName}.hdJenisPelarasan.value = "2";
		$jquery("#jenis_pelarasan").html("<font color='blue'>PENGURANGAN PAMPASAN</font>");	
		$jquery("#jenis_pelarasan_nilai").html("<font color='blue'>(PENGURANGAN PAMPASAN)</font>");
		$jquery("#jenis_pelarasan_nilai_bhg").html("<font color='blue'>(PENGURANGAN PAMPASAN)</font>");
		document.getElementById('fs_bayaran_lewat').style.display="none";
		document.getElementById('fs_pelarasan_bayaran_lewat').style.display="none"; 
		document.getElementById('tab_caj_1').style.display="none";
		document.getElementById('tab_caj_2').style.display="none"; 	
		document.getElementById('tab_caj_3').style.display="none";		 
	}else if(beza_luas == 0){
		document.${formName}.sorJenisPelarasan.value = "3";
		document.${formName}.hdJenisPelarasan.value = "3";
		$jquery("#jenis_pelarasan").html("<font color='blue'>KEKAL</font>");	
		$jquery("#jenis_pelarasan_nilai").html("<font color='blue'>(KEKAL)</font>");
		$jquery("#jenis_pelarasan_nilai_bhg").html("<font color='blue'>(KEKAL)</font>");
		document.getElementById('fs_bayaran_lewat').style.display="none"; 
		document.getElementById('fs_pelarasan_bayaran_lewat').style.display="none"; 
		document.getElementById('tab_caj_1').style.display="none";
		document.getElementById('tab_caj_2').style.display="none"; 	
		document.getElementById('tab_caj_3').style.display="none";		
	}else{
		document.${formName}.sorJenisPelarasan.value = "";
		document.${formName}.hdJenisPelarasan.value = "";
		$jquery("#jenis_pelarasan").html("");	
		$jquery("#jenis_pelarasan_nilai").html("");
		$jquery("#jenis_pelarasan_nilai_bhg").html("");
		document.getElementById('tab_caj_1').style.display="";
		document.getElementById('tab_caj_2').style.display=""; 	
		document.getElementById('tab_caj_3').style.display="";	
	}
	
	
	
	var frac_total = new Fraction(1,1);	
	var div_frac_nilai = 0;
	var fin_total = beza_luas_mp*nilai_seunit;
	for (var i=0;i<saiz_pb;i++)
	{
		var bhg_ats = document.getElementById('div_syer_atas'+(i+1)).value;
		var bhg_bwh = document.getElementById('div_syer_bawah'+(i+1)).value;
		
		if (!isNaN(bhg_ats) && !isNaN(bhg_bwh))
		{
		var div_total = ((fin_total*parseInt(bhg_ats))/parseInt(bhg_bwh));			
		$jquery("#div_bhg_per"+(i+1)).html("<font color = 'blue'>"+formatCurrency(div_total)+"</font>");	
		frac_total = frac_total.add(new Fraction(bhg_ats,bhg_bwh));
		div_frac_nilai = div_frac_nilai + div_total;
		}
		else
		{
		$jquery("#div_bhg_per"+(i+1)).html("");		
		}
	
	}
	frac_total = frac_total.subtract(1,1);
	$jquery("#div_frac_total").html("<b>"+frac_total+"</b>");	
	$jquery("#div_frac_nilai").html("<b>"+formatCurrency(div_frac_nilai)+"</b>");
	
	var dt_borangk = document.${formName}.dtBorangK.value;
	var dt_bayartambah = document.${formName}.tarikhBayarTambahan.value;
	var set_div_date = "";
	if(isDateValid(dt_bayartambah)==true && isDateValid(dt_borangk)==true)
	{
		var hari_bayar   = parseInt(dt_bayartambah.substring(0,2),10);
        var bulan_bayar  = parseInt(dt_bayartambah.substring(3,5),10);
        var tahun_bayar   = parseInt(dt_bayartambah.substring(6,10),10);	
		
		var hari_borangk   = parseInt(dt_borangk.substring(0,2),10);
        var bulan_borangk  = parseInt(dt_borangk.substring(3,5),10);
        var tahun_borangk   = parseInt(dt_borangk.substring(6,10),10);
		
		
		set_div_date = set_div_date + " <table width=\"100%\" border=\"0\"  align=\"left\"  >";
		set_div_date = set_div_date + " <tr><td>";
		
		set_div_date = set_div_date + " "
		+ "<table width=\"100%\" border=\"0\"  align=\"left\"  >  "
		+ " <tr class=\"table_header\">  "
		+ " <td align=\"center\"  width = \"25%\" >Tahun (Hari)</td> "
		+ " <td align=\"center\" width = \"50%\"  >Pengiraan</td> "
		+ " <td align=\"right\" width = \"25%\" >Jumlah Bayaran (RM)</td> "
		+ " </tr></table> ";
		
		set_div_date = set_div_date + " </td></tr>";
		
		
		if(parseInt(tahun_bayar)-parseInt(tahun_borangk) == 0)
		{
				var jumlah_hari = 0;
				var jumlah_hari_dalam_tahun = 0;
				set_div_date = set_div_date + " <tr><td>";
				set_div_date = set_div_date + "<table width=\"100%\" border=\"0\"  align=\"left\"  >  ";
				var myDate_borangk_last = new Date(tahun_bayar,bulan_bayar-1,hari_bayar);
				var myDate_borangk = new Date(tahun_borangk,bulan_borangk-1,hari_borangk);					
				jumlah_hari = DateDiff.inDays(myDate_borangk, myDate_borangk_last)+1;	
				jumlah_hari_dalam_tahun = daysInYear(tahun_borangk);				
				
				set_div_date = set_div_date + "<br><table width=\"100%\" border=\"0\"  align=\"left\"  >  ";
				set_div_date = set_div_date + "<tr >";
				set_div_date = set_div_date + " <td  width = \"25%\" align=\"center\"  >"+tahun_borangk+" (<b><font color='blue'>"+jumlah_hari+"</font></b>)</td> ";
				set_div_date = set_div_date + " <td  align=\"center\" width = \"50%\" >";				
				set_div_date = set_div_date + " <table width = \"70%\" align=\"center\" border = \"0\" >";
				set_div_date = set_div_date +"<tr><td align=\"center\"> ";
				set_div_date = set_div_date +"<b><font color='blue'>"+jumlah_hari+"</font></b> hari X RM <b><font color='blue'>"+formatCurrency(beza_luas_mp*nilai_seunit)+ "</font></b> X <b><font color='blue'>0.08</font></b>";
				set_div_date = set_div_date +"</td></tr> ";
				set_div_date = set_div_date +"<tr><td align=\"center\" class = \"td_total_pengiraan\" > ";
				set_div_date = set_div_date +"<b><font color='blue'>"+jumlah_hari_dalam_tahun+"</font></b>";
				set_div_date = set_div_date +"</td></tr> ";
				set_div_date = set_div_date +"</table> ";
				set_div_date = set_div_date +"</td> ";
				var bayaran_lewat = (jumlah_hari * (beza_luas_mp*nilai_seunit) * 0.08) / jumlah_hari_dalam_tahun;
				set_div_date = set_div_date + " <td  align=\"right\" width = \"25%\" ><font color='blue'>"+formatCurrency(bayaran_lewat)+"</font></td> "; 			
				set_div_date = set_div_date + "</tr>";
				total_bayaran_lewat = total_bayaran_lewat + bayaran_lewat;
				set_div_date = set_div_date + "</table>";
				set_div_date = set_div_date + " </td></tr>";
				
				total_jumlah_hari = total_jumlah_hari + jumlah_hari;
		}		
		else
		{
			set_div_date = set_div_date + " <tr><td>";
			set_div_date = set_div_date + "<table width=\"100%\" border=\"0\"  align=\"left\"  >  ";			
			for (var i=parseInt(tahun_borangk);i<=parseInt(tahun_bayar);i++)
			{
				var jumlah_hari = 0;
				var jumlah_hari_dalam_tahun = 0;
				
				jumlah_hari_dalam_tahun = daysInYear(i);
				var row = "";
				if ( (i % 2) != 1 )
              	{
					row = "row2";
				}
         		else
               	{
					row = "row1"
				}        		
				
				if(parseInt(tahun_borangk) == i)
				{
					var myDate_borangk_last = new Date(tahun_borangk,12-1,31);
					var myDate_borangk = new Date(tahun_borangk,bulan_borangk-1,hari_borangk);					
					jumlah_hari = DateDiff.inDays(myDate_borangk, myDate_borangk_last)+1;	
				}
				else if(parseInt(tahun_bayar) == i)
				{
					var myDate_bayar_first = new Date(tahun_bayar,1-1,1);
					var myDate_bayar = new Date(tahun_bayar,bulan_bayar-1,hari_bayar);					
					jumlah_hari = DateDiff.inDays(myDate_bayar_first, myDate_bayar)+1;						
				}
				else
				{
				jumlah_hari = daysInYear(i);
				}
				
				set_div_date = set_div_date + "<tr class="+row+">";
				set_div_date = set_div_date + " <td  width = \"25%\" align=\"center\"  >"+i+" (<b><font color='blue'>"+jumlah_hari+"</font></b>)</td> ";
				set_div_date = set_div_date + " <td  align=\"center\" width = \"50%\" >";				
				set_div_date = set_div_date + " <table width = \"70%\" align=\"center\" border = \"0\" >";
				set_div_date = set_div_date +"<tr><td align=\"center\"> ";
				set_div_date = set_div_date +"<b><font color='blue'>"+jumlah_hari+"</font></b> hari X RM <b><font color='blue'>"+formatCurrency(beza_luas_mp*nilai_seunit)+ "</font></b> X <b><font color='blue'>0.08</font></b>";
				set_div_date = set_div_date +"</td></tr> ";
				set_div_date = set_div_date +"<tr><td align=\"center\" class = \"td_total_pengiraan\" > ";
				set_div_date = set_div_date +"<b><font color='blue'>"+jumlah_hari_dalam_tahun+"</font></b>";
				set_div_date = set_div_date +"</td></tr> ";
				set_div_date = set_div_date +"</table> ";
				set_div_date = set_div_date +"</td> ";
				var bayaran_lewat = (jumlah_hari * (beza_luas_mp*nilai_seunit) * 0.08) / jumlah_hari_dalam_tahun;
				set_div_date = set_div_date + " <td  align=\"right\" width = \"25%\" ><font color='blue'>"+formatCurrency(bayaran_lewat)+"</font></td> "; 			
				set_div_date = set_div_date + "</tr>";
				total_bayaran_lewat = total_bayaran_lewat + bayaran_lewat;
				
				total_jumlah_hari = total_jumlah_hari + jumlah_hari;
			}
			set_div_date = set_div_date + "</table>";
			set_div_date = set_div_date + " </td></tr>";
		}
		set_div_date = set_div_date + " <tr><td>";
		set_div_date = set_div_date + " "
		+ "<table width=\"100%\" border=\"0\"  align=\"left\"  >  "
		+ " <tr class=\"table_header\">  "
		+ " <td align=\"right\"  width = \"75%\"  ><b>JUMLAH :</b></td> "
		+ " <td align=\"right\" width = \"25%\" ><b>"+formatCurrency(total_bayaran_lewat)+"</b></td> "
		+ " </tr></table> ";
		
		set_div_date = set_div_date + " </td></tr>";
		
		
		$jquery("#jumlah_bayaran_lewat").html("<font color='blue'><b>RM "+formatCurrency(total_bayaran_lewat)+"</b></font>");
		
		
		var frac_total_caj = new Fraction(1,1);	
		var div_frac_nilai_caj = 0;
		var fin_total_caj = total_bayaran_lewat;
		for (var i=0;i<saiz_pb;i++)
		{
			var bhg_ats_caj = document.getElementById('div_syer_atas_caj'+(i+1)).value;
			var bhg_bwh_caj = document.getElementById('div_syer_bawah_caj'+(i+1)).value;
			
			if (!isNaN(bhg_ats_caj) && !isNaN(bhg_bwh_caj))
			{
			var div_total_caj = ((fin_total_caj*parseInt(bhg_ats_caj))/parseInt(bhg_bwh_caj));			
			$jquery("#div_bhg_per_caj"+(i+1)).html("<font color = 'blue'>"+formatCurrency(div_total_caj)+"</font>");
			frac_total_caj = frac_total_caj.add(new Fraction(bhg_ats_caj,bhg_bwh_caj));
			div_frac_nilai_caj = div_frac_nilai_caj + div_total_caj;	
			}
			else
			{
			$jquery("#div_bhg_per_caj"+(i+1)).html("");		
			}
		
		}
		frac_total_caj = frac_total_caj.subtract(1,1);
		$jquery("#div_frac_total_caj").html("<b>"+frac_total_caj+"</b>");	
		$jquery("#div_frac_nilai_caj").html("<b>"+formatCurrency(div_frac_nilai_caj)+"</b>");
	
	
	set_div_date = set_div_date +"</table> ";
	}
	$jquery("#set_div_date").html(set_div_date);	
	$jquery("#set_total_day").html("<font color='blue'><b>"+total_jumlah_hari+"</font></b> hari");
	
	
	var total_total = 0;
	var total_pampasan_tanah = 0;
	var total_pampasan_lewat = 0;
	for (var i=0;i<saiz_pb;i++)
	{
		var bhg_ats_all = document.getElementById('div_syer_atas_all'+(i+1)).value;
		var bhg_bwh_all = document.getElementById('div_syer_bawah_all'+(i+1)).value;
		
		if (!isNaN(bhg_ats_all) && !isNaN(bhg_bwh_all))
		{
		var div_total_all = ((fin_total*parseInt(bhg_ats_all))/parseInt(bhg_bwh_all));
		var div_total_caj_all = ((fin_total_caj*parseInt(bhg_ats_all))/parseInt(bhg_bwh_all));	
		
							
		$jquery("#div_pampasan_tanah_all"+(i+1)).html("<font color = 'blue'>"+formatCurrency(div_total_all)+"</font>");	
		document.getElementById('text_pampasan_tanah_all'+(i+1)).value = formatCurrencyWithout(div_total_all);		
		
		$jquery("#div_bhg_per_caj_all"+(i+1)).html("<font color = 'blue'>"+formatCurrency(div_total_caj_all)+"</font>");	
		document.getElementById('text_bhg_per_caj_all'+(i+1)).value = formatCurrencyWithout(div_total_caj_all);	
			
		$jquery("#div_tempoh_lewat_all"+(i+1)).html("<font color = 'blue'>"+total_jumlah_hari+"</font>");	
		document.getElementById('text_tempoh_lewat_all'+(i+1)).value = total_jumlah_hari;		
		
		$jquery("#div_total_all"+(i+1)).html("<font color = 'blue'>"+formatCurrency(parseFloat(formatCurrencyWithout(div_total_all))+parseFloat(formatCurrencyWithout(div_total_caj_all)))+"</font>");	
		document.getElementById('text_total_all'+(i+1)).value = parseFloat(formatCurrencyWithout(div_total_all))+parseFloat(formatCurrencyWithout(div_total_caj_all));	
		
		total_total = total_total + parseFloat(formatCurrencyWithout(div_total_all))+parseFloat(formatCurrencyWithout(div_total_caj_all));
		total_pampasan_tanah = total_pampasan_tanah + parseFloat(formatCurrencyWithout(div_total_all));	
		total_pampasan_lewat = total_pampasan_lewat + parseFloat(formatCurrencyWithout(div_total_caj_all));
		}
		else
		{
		$jquery("#div_pampasan_tanah_all"+(i+1)).html("");		
		document.getElementById('text_pampasan_tanah_all'+(i+1)).value = "";
		$jquery("#div_bhg_per_caj_all"+(i+1)).html("");	
		document.getElementById('text_bhg_per_caj_all'+(i+1)).value = "";
		$jquery("#div_tempoh_lewat_all"+(i+1)).html("");	
		document.getElementById('text_tempoh_lewat_all'+(i+1)).value = "";
		$jquery("#div_total_all"+(i+1)).html("");	
		document.getElementById('text_total_all'+(i+1)).value = "";	
		}
	
	}
	$jquery("#div_total_pampasan_tanah_all").html("<b>"+formatCurrency(total_pampasan_tanah)+"</b>");
	$jquery("#div_total_bhg_per_caj_all").html("<b>"+formatCurrency(total_pampasan_lewat)+"</b>");
	$jquery("#div_total_total_all").html("<b>"+formatCurrency(total_total)+"</b>");
	
	
	
}

var DateDiff = {
 
    inDays: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();
 
        return parseInt((t2-t1)/(24*3600*1000));
    },
 
    inWeeks: function(d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();
 
        return parseInt((t2-t1)/(24*3600*1000*7));
    },
 
    inMonths: function(d1, d2) {
        var d1Y = d1.getFullYear();
        var d2Y = d2.getFullYear();
        var d1M = d1.getMonth();
        var d2M = d2.getMonth();
 
        return (d2M+12*d2Y)-(d1M+12*d1Y);
    },
 
    inYears: function(d1, d2) {
        return d2.getFullYear()-d1.getFullYear();
    }
}

function daysInYear(year) {
    if(year % 4 === 0 && (year % 100 !== 0 || year % 400 === 0)) {
        // Leap year
        return 366;
    } else {
        // Not a leap year
        return 365;
    }
}


function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num)) num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10) cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
	num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') +  num + '.' + cents);
}

function formatCurrencyWithout(num) {
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num)) num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10) cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
	num = num.substring(0, num.length - (4 * i + 3)) + '' + num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') +  num + '.' + cents);
}

function isDateValid(value, sepVal, dayIdx, monthIdx, yearIdx) {
    try {
        value = value.replace(/-/g, "/").replace(/\./g, "/");
        var SplitValue = value.split(sepVal || "/");
        if (SplitValue.length != 3) {
            return false;
        }
 
        //Auto  detection of indexes
        if (dayIdx === undefined || monthIdx === undefined || yearIdx === undefined) {
            if (SplitValue[0] > 31) {
                yearIdx = 0;
                monthIdx = 1;
                dayIdx = 2;
            } else {
                yearIdx = 2;
                monthIdx = 1;
                dayIdx = 0;
            }
        }
 
        //Change the below values to determine which format of date you wish to check. It is set to dd/mm/yyyy by default.
        var DayIndex = dayIdx !== undefined ? dayIdx : 0;
        var MonthIndex = monthIdx !== undefined ? monthIdx : 1;
        var YearIndex = yearIdx !== undefined ? yearIdx : 2;
 
        var OK = true;
        if (!(SplitValue[DayIndex].length == 1 || SplitValue[DayIndex].length == 2)) {
            OK = false;
        }
        if (OK && !(SplitValue[MonthIndex].length == 1 || SplitValue[MonthIndex].length == 2)) {
            OK = false;
        }
        if (OK && SplitValue[YearIndex].length != 4) {
            OK = false;
        }
        if (OK) {
            var Day = parseInt(SplitValue[DayIndex], 10);
            var Month = parseInt(SplitValue[MonthIndex], 10);
            var Year = parseInt(SplitValue[YearIndex], 10);
            var MonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
 
            if (OK = (Month <= 12 && Month > 0)) {
 
                var LeapYear = (((Year % 4) == 0) && ((Year % 100) != 0) || ((Year % 400) == 0));
                MonthDays[1] = (LeapYear ? 29 : 28);
 
                OK = Day > 0 && Day <= MonthDays[Month - 1];
            }
        }
        return OK;
    }
    catch (e) {
        return false;
    }
}

</script>




<script> 
function hapusDataPU(id_pu) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "viewPU";
	document.${formName}.command2.value = "hapusDataPU";
	document.${formName}.id_pu.value = id_pu;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPUstandalone";
	document.${formName}.submit();
}
function viewPU(id_pu) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_pu.value = id_pu;
	document.${formName}.command.value = "viewPU";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPUstandalone";
	document.${formName}.submit();
}
function kemaskiniDataPU(id_pu) {
	document.${formName}.command.value = "viewPU";
	document.${formName}.command2.value = "kemaskiniDataPU";
	document.${formName}.id_pu.value = id_pu;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPUstandalone";
	document.${formName}.submit();
}
function backToList() {
	document.${formName}.command.value = "else";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPUstandalone";
	document.${formName}.submit();
}
function simpanDataPU(id_pu,mode) {
	//alert("XXXXXXXXXXXXXXX");
	if(document.${formName}.txtNoFailPU.value == ""){
		alert("Sila masukkan \"No.Fail Permintaan Ukur\" terlebih dahulu.");
  		document.${formName}.txtNoFailPU.focus(); 
		return;
	}else if(document.${formName}.txtNoPA.value == ""){
		alert("Sila masukkan \"No.PA\" terlebih dahulu.");
  		document.${formName}.txtNoPA.focus(); 
		return;		
	}
	
	else if(document.${formName}.txtLuasAsal.value == ""){
		alert("Sila masukkan \"Luas Asal\" terlebih dahulu.");
  		document.${formName}.txtLuasAsal.focus(); 
		return;		
	}
	else if(document.${formName}.sorDropdownUnitAsal.value == ""){
		alert("Sila masukkan \"Jenis Luas Asal\" terlebih dahulu.");
  		document.${formName}.sorDropdownUnitAsal.focus(); 
		return;		
	}
	
	else if(document.${formName}.txtLuasAmbil.value == ""){
		alert("Sila masukkan \"Luas Ambil\" terlebih dahulu.");
  		document.${formName}.txtLuasAmbil.focus(); 
		return;		
	}
	else if(document.${formName}.sorDropdownUnit.value == ""){
		alert("Sila masukkan \"Jenis Luas Ambil\" terlebih dahulu.");
  		document.${formName}.sorDropdownUnit.focus(); 
		return;		
	}
	
	
	/*else if(document.${formName}.txtKeluasanPU.value == "" || document.${formName}.txtKeluasanPU.value == "0.0000" ){
		alert("Sila masukkan \"Luas PA\" terlebih dahulu.");
  		document.${formName}.txtKeluasanPU.focus(); 
		return;		
	}*/else{		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		if(mode=="new"){
			document.${formName}.command.value = "registerPU";
			document.${formName}.command2.value = "simpanDataPU";
		}else{
			document.${formName}.id_pu.value = id_pu;
			document.${formName}.command.value = "viewPU";
			document.${formName}.command2.value = "kemaskiniDataPU";
			document.${formName}.command3.value = "updateDataPU";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPUstandalone";
		document.${formName}.submit();
	}
}
function doChangeDaerah() {
	var mode = document.${formName}.mode.value;

	if(mode=="new"){
		document.${formName}.command.value = "registerPU";
	}else{
		document.${formName}.command.value = "viewPU";
	}
	document.${formName}.command2.value = "doChangeDaerah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPUstandalone";
	document.${formName}.submit();
}
function getAndSetDataHM(id_hakmilik) {
	document.${formName}.command.value = "registerPU";
	document.${formName}.command2.value = "getAndSetDataHM";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPUstandalone";
	document.${formName}.submit();
}
function getPopupDataHM() {
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmPUPopupGetDataHM";
	var hWnd = window.open(url,'Senarai Hakmilik/Lot','width=900,height=500, resizable=yes,scrollbars=yes');	
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
</script>

<!-- KIRA KIRA -->
<script>
function kiraBezaLuas() {

	var luas_baru = parseFloat(document.${formName}.txtKeluasanPU.value);
	var baki_luas = parseFloat(document.${formName}.countBakiLuas.value);
	var luas_asal = parseFloat(document.${formName}.lblLuasAsal.value);
	var luas_ambil = parseFloat(document.${formName}.lblLuasAmbil.value);	
	var beza_luas = parseFloat(document.${formName}.countBezaLuas.value);

	if (!isNaN(luas_asal) && !isNaN(luas_ambil)){
		baki_luas = luas_asal - luas_ambil;
	}
	
	if (!isNaN(luas_baru) && !isNaN(baki_luas)){
		beza_luas = luas_baru - baki_luas;
	}

	if (!isNaN(luas_asal) && !isNaN(luas_ambil)){
		document.${formName}.countBakiLuas.value = baki_luas.toFixed(4);	
		document.${formName}.HDcountBakiLuas.value = baki_luas.toFixed(4);
	}else{
		document.${formName}.countBakiLuas.value = "0.0000";
		document.${formName}.HDcountBakiLuas.value = "0.0000";
	}

	if (!isNaN(luas_baru)){
		document.${formName}.countBezaLuas.value = beza_luas.toFixed(4);
		document.${formName}.HDcountBezaLuas.value = beza_luas.toFixed(4);
	}else{
		document.${formName}.countBezaLuas.value = "0.0000";
		document.${formName}.HDcountBezaLuas.value = "0.0000";
	}
	
	if((!isNaN(luas_baru) && luas_baru!="0.0000") && beza_luas < 0){
		document.${formName}.sorJenisPelarasan.value = "1";
		document.${formName}.HDsorJenisPelarasan.value = "1";		
	}else if((!isNaN(luas_baru) && luas_baru!="0.0000") && beza_luas > 0){
		document.${formName}.sorJenisPelarasan.value = "2";
		document.${formName}.HDsorJenisPelarasan.value = "2";
	}else if((!isNaN(luas_baru) && luas_baru!="0.0000") && beza_luas == 0){
		document.${formName}.sorJenisPelarasan.value = "3";
		document.${formName}.HDsorJenisPelarasan.value = "3";
	}else{
		document.${formName}.sorJenisPelarasan.value = "";
		document.${formName}.HDsorJenisPelarasan.value = "";
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
function validateLuas(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(4);
		return;
	}else{
		elmnt.value = "";
	}
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
function validateNilai(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric3(content);
		return;
	}
}
function RemoveNonNumeric3( strString )
{
      var strValidCharacters = "1234567890.";
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