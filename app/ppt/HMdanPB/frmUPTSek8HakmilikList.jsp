#parse("app/ppt/Sek8Paging.jsp")

#if($showAlertPaging=="yes")
<script>
alert("Sila Teruskan 'Paging' No.4 Sekiranya Hakmilik Telah Diagihkan Oleh Pengarah");
</script>
#end 


<fieldset>
<legend><strong>Hakmilik Seksyen 8/Pb/Bebanan</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Lot <input type="button" name="cmdTambah" value ="Tambah Hakmilik" onClick="javascript:tambahHakmilik('$!flag_subjaket');">
    <input type="button" name="janaSJ" value ="Jana Subjaket" onClick="javascript:popupCarianHakmilikJanaSubjaket('$id_permohonan','skrin_list_hakmilik_pb_sek8');">
    </strong></legend>
		<a href="javascript:popupCarianHakmilik('$id_permohonan','skrin_list_hakmilik_pb_sek8')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>	
			<!-- Fungsi carian by no.lot/pt/nama pb -->
               <!-- #parse("app/ppt/frmCarianListHMSek8.jsp") -->
    	<!--		
    		#if($saiz_listTanah > 10)
             <div style="width:100%;height:285;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td>&nbsp;</td>
        			<td align="center"><b>Jumlah PB</b></td>
                  	<td><b>No.Hakmilik</b></td>
                  	<td><b>No.LOT/No.PT</b></td>                  
                  	<td><b>Mukim/Pekan/Bandar</b></td>
            		<td><b>Keluasan Diambil</b></td> 
            		#if($showSJ=="yes")<td align="center"><b>No.Subjaket</b></td>#end
            		#if($flag_subjaket=="1" && $showSJ=="no")<td><b>No.Subjaket</b></td> #end
            		#if($flagSegera=="3")<td><b>Pengambilan Segera</b></td>#end
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
               		<td class="$row" align="center" ><input type="button" name="cmdTambahPB" value="Tambah PB" onClick="javascript:tambahWakil('$!listTanah.id_hakmilik')"></td>
                	<td class="$row" align="center">$!listTanah.totalPB</td>
                	<td class="$row"><a href="javascript:viewHM('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</font></a></td>
                	<td class="$row">$!listTanah.no_lotpt</td>   
                	<td class="$row">$!listTanah.nama_mukim #if($listTanah.seksyen!="")<font style=font-size:10px>Seksyen $listTanah.seksyen</font>#end</td>
                	<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
                	#if($showSJ=="yes")<td class="$row" align="center"><input type="text" name="txtSj" id="txtSj" style="text-align:center" value="$!listTanah.no_subjaket" maxlength="4" size="4" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)" ></td>#end
                   	#if($flag_subjaket=="1" && $showSJ=="no")
                		<td class="$row">
                		#if($listTanah.no_subjaket!="")Sj.#end 
                		$!listTanah.no_subjaket
                		</td>
                	#end
                	#if($flagSegera=="3")
                		<td class="$row">
                			#if($listTanah.flag_segera_sebahagian=="Y")YA #elseif($listTanah.flag_segera_sebahagian=="N")TIDAK #end 
                		</td> 
                	#end
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listTanah > 10)
                </div>   
            #end
	-->
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($saiz_listTanah > 1 && $flag_subjaket!="1")
					#if($showSJ=="no")	
                    <!--
					<input type="button" name="cmdJanaAuto" value="Jana Subjaket (auto)" onClick="javascript:janaSubjaket($!id_permohonan)">
					<input type="button" name="cmdJanaManual" value="Jana Subjaket (manual)" onClick="javascript:janaSubjaketManual($!id_permohonan)">
                    -->
					#else
                    <!--
					<input type="button" name="cmdSimpan" value="Simpan" onClick="javascript:simpanSj('$!id_permohonan','$!saiz_listTanah')">
					<input type="button" name="cmdBatalJana" value="Batal" onClick="javascript:batalJana('$!id_permohonan')">
                    -->
					#end
				#end
				
              
                
                #if(($id_status=="127" && ($flag_subjaket=="1" && $saiz_listTanah != 0 )) || ($id_status=="127" && $saiz_listTanah == 1))					
					<img src="../img/emel.gif" title="Minta untuk tindakan pengarah juga akan dihantar melalui emel" >
					<input type="button" name="cmdHantar" value="Hantar Untuk Tindakan Pengarah" onClick="javascript:hantar('$!id_fail');">
				#end
				
				#if($saiz_listTanah > 1 && $flag_subjaket=="1")
					#if($showSJ=="no")
                    <!--
					<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="javascript:janaSubjaketManual('$!id_permohonan');">
                    -->
					#else
                    <!--
					<input type="button" name="cmdSimpan" value="Simpan" onClick="javascript:simpanSj('$!id_permohonan','$!saiz_listTanah')">
					<input type="button" name="cmdBatalJana" value="Batal" onClick="javascript:batalJana('$!id_permohonan')">
                    -->
					#end
				#end
				
				#if($id_negeriProjek=="03" || $id_negeriProjek=="3")
				<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
				#end
				
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table>

</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
		 <td><a href="#" onClick="javascript:cetakBorangC('$!id_fail','$!saiz_listTanah','$!id_negeriProjek')"><font color="blue">Borang C</font></a></td>
	  	</tr>
 	</table>
</fieldset>	


<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik">
<input type="hidden" name="id_hakmilikpb">
<input type="hidden" name="saiz_listTanah" value="$!saiz_listTanah">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<input type="hidden" name="location" id="location" />
<input type="hidden" name="point" id="point" />

#foreach($listId in $listSeqSubjaket)
<input type="hidden" name="ListIdHM" value="$!listId.id_hakmilik">
#end


<script>




window.onload = submitForm;



function refreshHakmilik(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}









function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}
function popupCarianHakmilikJanaSubjaket(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilikJanaSubjaket?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}


function cetakBorangC(idfail,totalHM,negeri) {
	
	if(totalHM > 1){
		var url = "../servlet/ekptg.report.ppt.BorangCLebih?id_Fail="+idfail;   	
	}else{
		var url = "../servlet/ekptg.report.ppt.BorangC?id_Fail="+idfail;
	}

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
function simpanSj(id_permohonan,param) {
	/*
	var inputItem = 0;
	var checkSelected=false;

	if(param>1){
		for(var i=0 ; i < document.${formName}.txtSj.length; i++){ 

    		if (document.${formName}.txtSj[i].value==""){
    			inputItem++;
  			}
		}
	}

	if(inputItem>0){
		alert("Sila pastikan kesemua Sj dimasukkan");
		return;
    }
	*/
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.command2.value = "janaSubjaketManual";
	document.${formName}.command3.value = "simpanSj";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}

function hantarEmel(id_permohonan,param) {
	
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.command2.value = "";
	document.${formName}.command3.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}

function batalJana(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function janaSubjaketManual(idPermohonan){
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = idPermohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.command2.value = "janaSubjaketManual";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function tambahPB(idHakmilik){

	document.${formName}.ScreenLocation.value = "changePB";
	
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function janaSubjaket(idPermohonan){

	if ( !window.confirm("Adakah Anda Pasti? Sebarang penambahan hakmilik selepas ini memerlukan subjaket dikesemua hakmilik dijana semula") ) return;
	document.${formName}.id_permohonan.value = idPermohonan;
	document.${formName}.command.value = "janaSubjaket";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function hantar(idfail){ //yati

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik&id_fail="+idfail;
	document.${formName}.submit();
}
function viewHM(idHakmilik){

	document.${formName}.ScreenLocation.value = "changeHM";
	
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function tambahHakmilik(flagSubjaket){

	if(flagSubjaket=="1"){
		alert("No Subjaket perlu dijana semula sekiranya ada penambahan hakmilik");
	}
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.submit();
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

function tambahWakil(id_hakmilik)
{

	/*
	document.${formName}.command.value = "tambahPB";	
	document.${formName}.subminor_command.value = "tambah_wakil";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	document.${formName}.submit();
	*/
	
	//alert(" id_hakmilik : "+id_hakmilik)

	document.${formName}.command.value = "tambahPB";	
	document.${formName}.subminor_command.value = "tambah_wakil";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik";
	document.${formName}.location.value = "maklumat_pb";
	document.${formName}.point.value = "socJenisPB";
	document.${formName}.id_hakmilikpb.value = "";	
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	document.${formName}.submit();
	
	
}



</script>