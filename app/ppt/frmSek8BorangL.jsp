#parse("app/ppt/Sek8Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<!-- #if($showAlertPaging=="yes")
<script>
alert("Sila Klik 'Paging' No.20 Untuk Endorsan Borang K");
</script>
#end -->


<fieldset id="top">
<legend><strong>Penjanaan Borang L dan Penerimaan Hakmilik</strong></legend>
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">		
  		<ul class="TabbedPanelsTabGroup">
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(0);TABpenjanaanBorangL($!id_permohonan)" tabindex="1">Penjanaan Borang L</li>
    		<li class="TabbedPanelsTab" onClick="javascript:setSelected(1);TABviewPenerimaanHakmilik($!id_permohonan)" tabindex="1">Penerimaan Hakmilik</li>
    	</ul>
  	
  		<div class="TabbedPanelsContentGroup">


			<!-- START TAB 1 -->
  			<div class="TabbedPanelsContent">
  		
  			<br/>
  			
  				#if($mode=="new")
  				<fieldset>
  				<legend><strong>Maklumat Borang L</strong></legend>
  					<table width="100%" border="0">
  						<tr>
  							<td width="1%"><font color="red">*</font></td>
  							<td width="15%">Tarikh Borang L</td>
  							<td width="1%">:</td>
  							<td width="73%"><input name="txdTarikhBorangL" id="txdTarikhBorangL" size="11" type="text" value="" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
          					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangL',false,'dmy');">&nbsp;$!frmtdate</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tempoh (Hari)</td>
  							<td>:</td>
  							<td><input type="text" name="txtTempoh" id="txtTempoh" value="" size="2" maxlength="2" onkeyup="validateNumber(this,this.value);" ></td>
  						</tr>
  						
  						<!-- <tr>
  							<td>&nbsp;</td>
  							<td>Pilihan Hakmilik (Telah Dikeluarkan Borang K)</td>
  							<td>:</td>
  							<td><select name="sorPilihHakmilik" style="width:350px">
      		
      							<option value="">SILA PILIH</option>    			
      							<option value="1">SEMUA HAKMILIK</option>	
      							<option value="2">HAKMILIK PENGAMBILAN KESELURUHAN LOT SAHAJA</option>		
      					
							</select></td>
  						</tr> -->
  						
  					</table>	
  				</fieldset>
  				
  				<br/>
  				
  				<fieldset>
				<legend><strong>Pilihan Hakmilik (Telah Dikeluarkan Borang K)</strong></legend>
			
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
        		    #if($saiz_listTanah!=0)<td width="4%" align="center"><input type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></td>#end
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>No.LOT/No.PT</b></td> 
        			<td><b>Jenis Hakmilik</b></td>
        			<td><b>No.Hakmilik</b></td>
        			<td><b>Bandar/Pekan/Mukim</b></td>
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
               		#if($saiz_listTanah!=0)<td class="$row" align="center"><input type="checkbox" name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>#end
                   	<td class="$row" align="center">$!listTanah.bil</td>
                   	<td class="$row">$!listTanah.no_lotpt</td> 
                   	<td class="$row">$!listTanah.jenis_hakmilik</td>
                   	<td class="$row">$!listTanah.no_hakmilik</td> 
                  	<td class="$row">$!listTanah.nama_mukim</td> 
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
  				#end
  		
  				
  				
  				#if($mode=="view")
  				
  				#foreach($data in $dataBorangL)
  					#set($txdTarikhBorangL=$data.tarikh_borangl)
  					#set($txtTempoh=$data.tempoh)
  					#set($sorPilihHakmilik=$data.jenis_pilih)
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
  				<legend><strong>Maklumat Borang L</strong></legend>
  					<table width="100%" border="0">
  						<tr>
  							<td width="1%"><font color="red">$!M</font></td>
  							<td width="15%">Tarikh Borang L</td>
  							<td width="1%">:</td>
  							<td width="73%"><input $disability $disabilityx name="txdTarikhBorangL" id="txdTarikhBorangL" size="11" type="text" value="$!txdTarikhBorangL" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
          					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangL',false,'dmy');">&nbsp;$!frmtdate#end</td>
  						</tr>
  						<tr>
  							<td>&nbsp;</td>
  							<td>Tempoh (Hari)</td>
  							<td>:</td>
  							<td><input $disability $disabilityx type="text" name="txtTempoh" id="txtTempoh" value="$!txtTempoh" size="2" maxlength="2" onkeyup="validateNumber(this,this.value);" ></td>
  						</tr>
  						
  						<!-- <tr>
  							<td>&nbsp;</td>
  							<td>Pilihan Hakmilik (Telah Dikeluarkan Borang K)</td>
  							<td>:</td>
  							<td><select $disability1 $disabilityx name="sorPilihHakmilik" style="width:350px">
      		
      							#if($sorPilihHakmilik=="1")
      							<option value="1">SEMUA HAKMILIK</option>	
      							<option value="">SILA PILIH</option>    			
      							<option value="2">HAKMILIK PENGAMBILAN KESELURUHAN LOT SAHAJA</option>	
      							#elseif($sorPilihHakmilik=="2")
      							<option value="2">HAKMILIK PENGAMBILAN KESELURUHAN LOT SAHAJA</option>	
      							<option value="">SILA PILIH</option>    			
      							<option value="1">SEMUA HAKMILIK</option>	
      							#else
      							<option value="">SILA PILIH</option>    			
      							<option value="1">SEMUA HAKMILIK</option>	
      							<option value="2">HAKMILIK PENGAMBILAN KESELURUHAN LOT SAHAJA</option>	
      							#end
      				
							</select></td>
  						</tr> -->
  						
  					</table>	
  				</fieldset>
  				
  				<br/>
  				
  				<fieldset>
				<legend><strong>Pilihan Hakmilik (Telah Dikeluarkan Borang K)</strong></legend>
			
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
        			#if($saiz_listTanah!=0)<td width="4%" align="center"><input $disability1 type="checkbox" title="Sila Semak Untuk Pilih Semua" name="checkall" id="checkall" onclick="checkALL()" ></td>#end
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>No.LOT/No.PT</b></td>
        			<td><b>Jenis Hakmilik</b></td>
        			<td><b>No.Hakmilik</b></td>
        			<td><b>Bandar/Pekan/Mukim</b></td>
        		</tr>
        		
        		#if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
                    
                     #if($listTanah.flag_borangl == "1")
                    	#set($checkHM = "checked")
                    #else
                   		#set($checkHM = "")
                    #end
                    
               	<tr>
               		#if($saiz_listTanah!=0)<td class="$row" align="center"><input $disability1 $checkHM type="checkbox" name="cbsemaks" id="cbsemaks" onclick="doUpdateCheckAll()" value="$!listTanah.id_hakmilik"></td>#end
                   	<td class="$row" align="center">$!listTanah.bil</td>
                   	<td class="$row">$!listTanah.no_lotpt</td> 
                   	<td class="$row">$!listTanah.jenis_hakmilik</td>
                   	<td class="$row">$!listTanah.no_hakmilik</td> 
                  	<td class="$row">$!listTanah.nama_mukim</td> 
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
  				#end
  				
  				<table width="100%" border="0">
					<tr align="center">
						<td>
							#if($mode=="new")
							<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBorangL('$!id_permohonan','$!id_borangl','$!mode','$!saiz_listTanah')">
							#end
				
							#if($mode=="view")
								#if($isEdit=="no")
								<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangL('$!id_borangl')">
								<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
								#else
								<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangL('$!id_permohonan','$!id_borangl','$!mode','$!saiz_listTanah')">
								<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalBorangL('$!id_permohonan')">
								#end
							#end
				
							<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">
						</td>
					</tr>
				</table>
  		
  			</div>	
  			<!-- END TAB 1 -->
  			
  			
  			<!-- START TAB 2 -->
  			<div class="TabbedPanelsContent">
  		
  			<br/>
  			
  				#set($rad1 = "checked")
  				#set($rad2 = "")
  				#set($rad3 = "")
  			
  				#if($radio=="2")
  					#set($rad1 = "")
  					#set($rad2 = "checked")
  					#set($rad3 = "")
  				#elseif($radio=="3")
  					#set($rad1 = "")
  					#set($rad2 = "")
  					#set($rad3 = "checked")
  				#else
  					#set($rad1 = "checked")
  					#set($rad2 = "")
  					#set($rad3 = "")
  				#end
  				
  				<fieldset>
  				<legend><strong>Pilih Pertanyaan</strong></legend>	
  					
  					<table width="100%" border="0">
  						<tr>
  							<td>
  								<input type="radio" name="sorPertanyaan" $rad1 id="sorSemua" onchange="viewPenerimaanHakmilik('$!id_permohonan')" value="1">Kesemua Hakmilik
  								<input type="radio" name="sorPertanyaan" $rad2 id="sorBelum" onchange="viewPenerimaanHakmilik('$!id_permohonan')" value="2">Hakmilik Belum Diterima
  								<input type="radio" name="sorPertanyaan" $rad3 id="sorSudah" onchange="viewPenerimaanHakmilik('$!id_permohonan')" value="3">Hakmilik Telah Diterima
  							</td>
  						</tr>
  					</table>
  				
  				</fieldset>
  				
  				<br/>
  				
  				<fieldset>
				<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
			
					<table width="100%" border="0">   
                		<tr>
                    		<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot2" value="$!carianNoLot2" maxlength="20" size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    					</tr>
    				</table>
    			
    				#if($saiz_listTanah > 5)
        			<div style="width:100%;height:100;overflow:auto"> 
        			#end	
    			
    				<table width="100%" border="0"> 
  
        				<tr class="table_header">
        					<td align="center" width="4%"><b>No</b></td>
        					<td><b>No.Hakmilik</b></td>
        					<td><b>Jenis Hakmilik</b></td>
        					<td><b>Bandar/Pekan/Mukim</b></td>
        					<td><b>Tarikh Terima</b></td>
        					<td><b>Status Borang L</b></td>
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
                   			<td class="$row"><a href="javascript:MaklumatPenerimaan('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.no_hakmilik</font></a></td> 
                   			<td class="$row">$!listTanah.jenis_hakmilik</td>
                  			<td class="$row">$!listTanah.nama_mukim</td> 
                  			<td class="$row">$!listTanah.tarikh_terima_hm</td> 
                  			<td class="$row">$!listTanah.statusBorangL</td> 
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
  				
  			</div>
  			<!-- END TAB 2 -->
  			
  			
  		</div>
  	</div>
  		
</center>
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
		 <td><a href="#" onClick="javascript:cetakBorangL('$!id_permohonan')"><font color="blue">Borang L</font></a></td>
	  </tr>
    </table>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik">
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_borangl" value="$!id_borangl">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>
function cetakBorangL(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=BorangL&selectNoFail=yes";
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
function MaklumatPenerimaan(id_hakmilik) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "MaklumatPenerimaan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function viewPenerimaanHakmilik(id_permohonan) {
	document.${formName}.paging.value = "19";
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewPenerimaanHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function batalBorangL(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.paging.value = "19";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "penjanaanBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function kemaskiniBorangL(id_borangl) {
	document.${formName}.paging.value = "19";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borangl.value = id_borangl;
	document.${formName}.command.value = "penjanaanBorangL";
	document.${formName}.command2.value = "kemaskiniBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function simpanBorangL(idpermohonan,id_borangl,mode,param) {

	var dat1=document.${formName}.txdTarikhBorangL;

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh borang k 
	var TS  = document.getElementById("txdTarikhBorangL").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateBorangL = new Date(yr1, mon1, dt1);

   	var checkSelected=false;
	if(param>1){
		for(var i=0 ; i < document.${formName}.cbsemaks.length; i++) 
		{ 
    		//var total=0;
    		if (document.${formName}.cbsemaks[i].checked)
        	{
  				//total++;
  				checkSelected=true; 
  			}
		}
	}else{
		if (document.${formName}.cbsemaks.checked)
    	{
			//total++;
			checkSelected=true; 
    	}
	}
	
   	if(document.${formName}.txdTarikhBorangL.value == ""){
		alert("Sila masukkan \"Tarikh Borang L\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangL.focus(); 
		return;
	}	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dat1.value!="" && (dateBorangL < currentDate)){
	   	alert("Sila pastikan \"Tarikh Borang L\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhBorangL.focus();
		return;
	}
*/	else if(!checkSelected){
		alert("Sila pilih hakmilik terlebih dahulu.");
		document.${formName}.checkall.focus();
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.paging.value = "19";
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "penjanaanBorangL";
			document.${formName}.command2.value = "simpanBorangL";
		}else{
			document.${formName}.command.value = "penjanaanBorangL";
			document.${formName}.command2.value = "kemaskiniBorangL";
			document.${formName}.command3.value = "updateBorangL";
		}
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
		document.${formName}.submit();
	}
}
</script>

<!-- TAB -->
<script>
function TABviewPenerimaanHakmilik(id_permohonan) {
	document.${formName}.paging.value = "19";
	document.${formName}.tabId.value = "1";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewPenerimaanHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function TABpenjanaanBorangL(id_permohonan) {
	document.${formName}.tabId.value = "0";
	document.${formName}.paging.value = "19";
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "penjanaanBorangL";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
</script>

<script>
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	var tabid = document.${formName}.tabId.value;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.paging.value = "19";
	document.${formName}.id_permohonan.value = idpermohonan;
	if(tabid=="0"){
		document.${formName}.command.value = "penjanaanBorangL";
	}else{
		document.${formName}.command.value = "viewPenerimaanHakmilik";
	}
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	var tabid = document.${formName}.tabId.value;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.paging.value = "19";
	document.${formName}.id_permohonan.value = idpermohonan;
	if(tabid=="0"){
		document.${formName}.carianNoLot.value = "";
		document.${formName}.command.value = "penjanaanBorangL";
	}else{
		document.${formName}.carianNoLot2.value = "";
		document.${formName}.command.value = "viewPenerimaanHakmilik";
	}
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8BorangK";
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