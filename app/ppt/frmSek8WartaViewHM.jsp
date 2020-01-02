#if($mode=="view")	
	
	#foreach($data in $dataMaklumatTanah)
		#set($noHakmilik=$data.no_hakmilik)		
		#set($tarikhLuput=$data.tarikh_luput)
		#set($tarikhDaftar=$data.tarikh_daftar)		
		#set($bakiTempoh=$data.tempoh_luput)
		#set($lokasi=$data.lokasi)
		#set($syaratNyata=$data.syarat_nyata)
		#set($syaratKhas=$data.syarat_khas)		
		#set($sekatanKepentingan=$data.sekatan_kepentingan)
		#set($sekatanHak=$data.sekatan_hak)		
		#set($noSyit=$data.no_syit)
		#set($noLot=$data.no_lot)
		#set($luasAmbil=$data.luas_ambil)
		#set($seksyen=$data.seksyen)
		#set($catatan=$data.catatan)
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
	
	<fieldset id="changeHM">
	<legend><strong>Maklumat Hakmilik</strong></legend>	
		<table width="100%" border="0">
			<tr>
				<td width="29%"><font color="red">$!M</font>Jenis Hakmilik</td>
				<td width="1%">:</td>
				<td width="70%">$!selectJenisHakmilik</td>
			</tr>	
			<tr>
				<td><font color="red">$!M</font>No. Hakmilik</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!noHakmilik" size="12" maxlength="50"   ></td>
			</tr>
			<tr>
				<td>Tarikh Luput</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txdTarikhLuput" id="txdTarikhLuput" value="$!tarikhLuput" size="12" maxlength="10" onblur="validateTarikh(this,this.value);check_date(this)"  >
				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLuput',false,'dmy');">#end</td>
			</tr>
			<tr>
				<td>Tarikh Daftar</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" value="$!tarikhDaftar" size="12" maxlength="10" onblur="validateTarikh(this,this.value);check_date(this)"  >
				#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhDaftar',false,'dmy');">#end</td>
			</tr>
			<tr>
				<td>Baki Tempoh (Tahun)</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtBakiTempoh" id="txtBakiTempoh" value="$!bakiTempoh" size="12" maxlength="10"   ></td>
			</tr>
			<tr>
				<td>Kategori Tanah</td>
				<td>:</td>
				<td>$!selectKategoriTanah</td>
			</tr>
			<tr>
				<td><font color="red">$!M</font>Bandar/Pekan/Mukim</td>
				<td>:</td>
				<td>$!selectMukim</td>
			</tr>
			<tr>
				<td>Seksyen</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtSeksyen" id="txtSeksyen" value="$!seksyen" size="10" maxlength="30"   ></td>
			</tr>
			<tr>
				<td>Lokasi</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtLokasi" id="txtLokasi" value="$!lokasi" size="50" maxlength="100"   ></td>
			</tr>
			<tr>
				<td>Syarat Nyata</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtSyaratNyata" id="txtSyaratNyata" value="$!syaratNyata" size="50" maxlength="400"   ></td>
			</tr>
			<tr>
				<td>Syarat Khas</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtSyaratKhas" id="txtSyaratKhas" value="$!syaratKhas" size="50" maxlength="400"   ></td>
			</tr>
			<tr>
				<td>Sekatan Kepentingan</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtSekatanKepentingan" id="txtSekatanKepentingan" value="$!sekatanKepentingan" size="50" maxlength="400"   ></td>
			</tr>
			<tr>
				<td>Sekatan Hak</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtSekatanHak" id="txtSekatanHak" value="$!sekatanHak" size="50" maxlength="400"   ></td>
			</tr>
			<tr>
				<td><font color="red">$!M</font>No. Syit</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtNoSyit" id="txtNoSyit" value="$!noSyit" size="12" maxlength="40"   ></td>
			</tr>
			<tr>
				<td>Kod Luas No.LOT/PT</td>
				<td>:</td>
				<td>$!selectKodLot</td>
			</tr>
			<tr>
				<td><font color="red">$!M</font>No.LOT/No.PT</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtNoLot" id="txtNoLot" value="$!noLot" size="12" maxlength="20"   ></td>
			</tr>
			<tr>
				<td><font color="red">$!M</font>Luas Diambil</td>
				<td>:</td>
				<td><input $disability $disabilityx type="text" name="txtLuasAmbil" id="txtLuasAmbil" value="$!luasAmbil" size="15" maxlength="10" onkeyup="validateNumber(this,this.value);"   >&nbsp;$!selectUnitLuas</td>
			</tr>
			<tr>
            	<td valign="top">Catatan</td>
            	<td valign="top">:</td>
            	<td><textarea $disability $disabilityx name="txtCatatan" id="txtCatatan" cols="70%" rows="10"   onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" >$!catatan</textarea></td>
            </tr>
            #if($isEdit=="yes")
            <tr>
        		<td>&nbsp;</td>
             	<td>&nbsp;</td>
             	<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="4000"></td>
           	</tr> 
           	#end
		</table>
	</fieldset>	
#end

	<table width="100%" border="0">
		<tr align="center">
			<td>
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewSenaraiWarta('$!id_permohonan')">
			</td>
		</tr>
	</table>

<br/>

	<fieldset id="bottom">
	<legend><strong>Jadual Tanah - Tanah Yang Terlibat Dengan Pengambilan</strong></legend>
		
			<table width="100%" border="0">   
                	<tr>
                    	<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot2" value="$!carianNoLot2" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center"><b>No</b></td>
        			<td><b>No.LOT/No.PT</b></td>
        			<td><b>No.Hakmilik</b></td>
            		<td><b>Bandar/Pekan/Mukim</b></td>
            		<td><b>Keluasan</b></td> 
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
                   <td class="$row"><a href="javascript:viewHM('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.kod_jenis_hakmilik $!listTanah.no_lot</font></a></td>
                   <td class="$row">$!listTanah.no_hakmilik</td>
                   <td class="$row">$!listTanah.nama_mukim</td>
                   <td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitluaslot</td>
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


<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function viewHM(idHakmilik){

	document.${formName}.ScreenLocation.value = "changeHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "viewHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.carianNoLot2.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function viewSenaraiWarta(id_permohonan) {

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewSenaraiWarta";
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
</script>
	