<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<!-- peje -->
<input type="hidden" name="idPermohonan" />
<input type="hidden" name="idPermohonanSimati"/>
<input type="hidden" name="idStatus" />

<!-- shaz -->
<input type="hidden" name="id_permohonan" />
<input type="hidden" name="id_status" />

<!-- elly -->
<input type="hidden" name="idpermohonan" />
<input type="hidden" name="id_Simati" />
<input type="hidden" name="id_fail" />

<!-- razman -->
<input type="hidden" name="idpermohonansimati" />
<input type="hidden" name="tarikh_mohon" />
<input type="hidden" name="idSimati" />

<!-- kak ayu -->
<input type="hidden" name="idsimati" />
<br />
<fieldset>
  <legend><b>CARIAN</b> </legend>
  <table width="100%" align="center" border="0">
    <tbody>
      <tr>
        <td width="29%" height="24" scope="row" align="right"><div align="right">MyId Pemohon / Simati </div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="70%"><input name="myid" id="myid" type="text" size="50" maxlength="50" value="$myid" style="text-transform:uppercase;" ></td>
      </tr>
      <tr>
        <td scope="row"><div align="right">Nama Simati / Pemohon</div></td>
        <td><div align="center">:</div></td>
        <td><input name="nama" id="nama" type="text" size="50" maxlength="50" value="$nama" style="text-transform:uppercase;" /></td>
      </tr>
      <tr>
        <td scope="row"><div align="right">No Fail <em>Online</em> / Pusaka</div></td>
        <td><div align="center">:</div></td>
        <td><input name="no_fail" id="no_fail" type="text" size="50" maxlength="50" value="$no_fail" style="text-transform:uppercase;" /></td>
      </tr>
      <tr>
        <td scope="row"><div align="right">Seksyen</div></td>
        <td><div align="center">:</div></td>
        <td> #set($listseksyen = ["8","17"])
          <select  class="autoselect" name="seksyen" id="seksyen" >
            <option value="" $selected_jenis_log >Seksyen 8 dan 17</option>
            
            
            
            
						   		#foreach ( $y in $listseksyen )
						   		#if( $y == $!seksyen )
						   			#set ( $selected_jenis_log = "selected" )
						   		#else
						   			#set ( $selected_jenis_log = "" )
						   		#end                       
						   	
            
            
            
            <option value="$y" $selected_jenis_log > #if($y == "8")
            Seksyen 8
            #elseif($y == "17")
            Seksyen 17
            #end </option>
            
            
            
            
						   		#end
							
          
          
          
          </select></td>
      </tr>
      
      <tr>
        <td scope="row"><div align="right">Tempoh</div></td>
        <td><div align="center">:</div></td>
        <td valign="top"><input name="tarikhMula" id="tarikhMula" value="$!tarikhMula" size="11" type="text" onblur="check_date(this)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('tarikhMula',false,'dmy');">
             hingga
             <input name="tarikhAkhir" id="tarikhAkhir" value="$!tarikhAkhir" size="11" type="text" onblur="check_date(this)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('tarikhAkhir',false,'dmy');">
             </td>
        
      </tr>
      
      <tr>
        <td scope="row">&nbsp;</td>
        <td>&nbsp;</td>
        <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="search_data()" />
          <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="kosong_data()" /></td>
      </tr>
      <tr>
        <td scope="row">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </tbody>
  </table>
</fieldset>
<br/>
<fieldset>
  <legend><b>SENARAI PERMOHONAN <em>ONLINE</em></b></legend>
  <!--
<p align="left"><input name="cmdTambah" id="cmdTambah" value="Tambah" type="button"  onclick="Tambah()"></p>

-->
  <table width="100%">
    <tr>
      <td colspan="5" scope="row" align="right"> #set ($pagingTitle = "Jumlah Carian")
        #parse("app/utils/record_paging.jsp") </td>
    </tr>
  </table>
  <table align="center" width="100%">
    <tr class="table_header">
      <td width="5%"><div align="center">Bil</div></td>
      <td width="20%">No Rujukan <em>Online</em></td>
      <td width="20%"><div align="center">No Fail</div></td>
      <td width="15%"><div align="center">Tarikh Permohonan</div></td>
      <td width="30%"><div align="left">Nama Pemohon</div></td>
      <td width="15%"><div align="center">Seksyen</div></td>
      <td width="15%"><div align="center">Cetak Borang A / P</div></td>
      <td width="15%"><div align="center">Kemaskini Borang A / P</div></td>
    </tr>
    #set ($list = "")
    #set ($counter = 0)
    #foreach ($fail in $SenaraiFail)
    #set( $counter = $counter + 1 )
    #if ($fail.bil == '')
    #set( $row = "row1" )
    #elseif (($fail.bil % 2) != 0)
    #set( $row = "row1" )
    #else 
    #set( $row = "row2" )
    #end
    <tr class="$row">
      <td ><div align="center">$fail.bil</div></td>
      <td ><!--<a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_Simati','$flagno','$idFlag','$SimpanStatus')" class="style1">
				          </a>--> 
        $fail.noonline </td>
      #if ($fail.no_Fail == "-")
      <td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.no_Fail</div></td>
      #else
      <td><div align="center" style="text-transform:uppercase;" onblur="uppercase()"><a href="javascript:papar('$fail.id_Permohonan','$fail.idStatus','$fail.id_Fail','$fail.id_Simati','$fail.idPermohonansimati','$fail.tarikhmohon','$fail.flagJenisFail','$fail.seksyen')" class="style1">$fail.no_Fail</a></div></td>
      #end
      <td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.tarikhmohononline</div></td>
      <td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$fail.namapemohon </div></td>
      <td ><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.seksyen</div></td>
      <td ><div align="center"> #if($fail.seksyen == "8")
          <input name="cetak" type="button" value="Cetak Borang A" onclick="cetakBorangA('$fail.noonline')" />
          #end 
          #if($fail.seksyen == "17")
          <input name="cetak" type="button" value="Cetak Borang P" onclick="cetakBorangP('$fail.noonline','$fail.id_Fail','$fail.id_FailDulu','$fail.id_Permohonan')" />
          #end </div></td>
      <td ><div align="center"> #if($fail.seksyen == "8")
          <input name="kemaskini" type="button" value="Kemaskini Borang A" onclick="papar('$fail.id_Permohonan','$fail.id_Simati','$fail.seksyen','$fail.id_Pemohon','$fail.no_subjaket')" />
          #end
          #if($fail.seksyen == "17")
          <input name="kemaskini" type="button" value="Kemaskini Borang P" onclick="papar17('$fail.id_Permohonan','$fail.id_Simati','$fail.seksyen','$fail.id_Pemohon','$fail.no_subjaket')" />
          #end </div></td>
    </tr>
    #end
    
    
    #if($counter == 0)
    <tr>
      <td></td>
      <td class="$row"> Tiada rekod </td>
    </tr>
    #end
  </table>
</fieldset>
<script>
function search_data(){
	if (document.${formName}.no_fail.value == "" && document.${formName}.nama.value == "" && document.${formName}.myid.value == "" && document.${formName}.seksyen.value == "" && document.${formName}.tarikhMula.value == "" && document.${formName}.tarikhAkhir.value == "")
	{
		alert("Sila masukkan maklumat carian.");		
		document.f1.no_fail.focus(); 
		return; 
	}
	else {
	//alert("XXX"+document.${formName}.command.value);
	//document.${formName}.command.value = "Cari";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("Cari");
	document.${formName}.cmdCari.value = "Sila Tunggu....";	
	}
}

function kosong_data(){
	
	doAjaxCall${formName}("");
	document.${formName}.cmdKosongkan.value = "Sila Tunggu....";	
	
}

function papar(idPermohonan,idSimati,seksyen,idpemohon,no_subjaket) {

 var token = document.${formName}.form_token.value;

 var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnBorangAMaklumatPemohon?command=Simati&mode=Simatiview&idPermohonan="+idPermohonan+"&idpermohonan="+idPermohonan+"&idPemohon="+idpemohon+"&idSimati="+idSimati+"&form_token"+token;
   var hWnd = window.open(url,'Cetak','width=1000,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
			
}

function papar17(idPermohonan,idSimati,seksyen,idpemohon,no_subjaket) {


 var url = "../x/${securityToken}/ekptg.view.ppk.FrmBorangPSek17Online?command=Simati&mode=Simatiview&idPermohonan="+idPermohonan+"&idpermohonan="+idPermohonan+"&idPemohon="+idpemohon+"&idSimati="+idSimati+"&no_subjaket="+(parseInt(RemoveNonNumericX(no_subjaket)) - 1);
 
   var hWnd = window.open(url,'Cetak','width=1000,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

			
}
function RemoveNonNumericX( strString )
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

function cetakBorangA(noFailOnline) 
{
    var url = "../servlet/ekptg.report.ppk.BorangAOnline?NoPermohonan="+noFailOnline;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangP(nopermohonan,idfail,idfaillama,idpermohonanbaru) {
    var url="../servlet/ekptg.report.ppk.BorangP?idfaillama="+idfaillama+"&idfailbaru="+idfail+"&idpermohonanbaru="+idpermohonanbaru;
    var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
	 if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script> 

<!-- ADD BY PEJE - FUNCTION PAPAR FAIL --> 
<script>
function papar(idPermohonan, idStatus, idFail, idSimati, idpermohonansimati, tarikhMohon, jenisfail, seksyen) {
			
	if (idStatus == '41'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
		}
		
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idPermohonanSimati.value = idpermohonansimati;
		document.${formName}.idStatus.value = idStatus;
		
	}	
	else 
	//shaz
	if (idStatus == '53' || idStatus == '151' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData&tabId=0";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData&tabId=0";
		}
				
		document.${formName}.id_permohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
			
	} else  if (idStatus == '44' || idStatus == '173' || idStatus == '175' || idStatus == '177' ){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis";
		}
			
		document.${formName}.id_permohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
	} 	
	else 
	if (idStatus == '21' || idStatus == '25'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=semakRayuan";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=semakRayuan";
		}			
		document.${formName}.id_permohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
	} else 
	if (idStatus == '64'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=maklumatRayuan&tabId=0";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=maklumatRayuan&tabId=0";
		}			
		document.${formName}.id_permohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
	} else 
	if (idStatus == '163' || idStatus == '164' || idStatus == '165' || idStatus == '166' || idStatus == '167' || idStatus == '180'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaanSek17&command=semakKeputusanRayuan";
		}
		document.${formName}.id_permohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
	} else 
//elly
	if (idStatus == '18'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_notis";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_notis";
		}
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
		document.${formName}.id_Simati.value = idSimati;
	} else 
	if (idStatus == '47'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_batal";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_batal";
		}
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
		document.${formName}.id_Simati.value = idSimati;
	}
	else 
	if (idStatus == '172'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_kolateral";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_kolateral";
		}
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
		document.${formName}.id_Simati.value = idSimati;

	} else 
	if (idStatus == '174'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanMT";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanMT";
		}
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
		document.${formName}.id_Simati.value = idSimati;
	} else 
	if (idStatus == '176'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanROTS";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanROTS";
		}
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.id_status.value = idStatus;
		document.${formName}.id_Simati.value = idSimati;

	} else 
	if (idStatus == '56'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&command=papar";
		}
		if (seksyen == '17'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&command=papar17";
		}
			
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.idPermohonan.value = idPermohonan;
	}	
	else 
	if ((idStatus == '8' || idStatus == '9' || idStatus == '170' || idStatus == '169') && (jenisfail == '1' || jenisfail == '2' )){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
		}
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.idSimati.value = idSimati;
	} else 
	if ((idStatus == '8' || idStatus == '9' || idStatus == '170') && jenisfail == '3'){
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar";
		document.${formName}.idpermohonan.value = idPermohonan;
	} else 
//man		
	if (idStatus == '14' || idStatus == '50' || idStatus == '152'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
		}
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idpermohonansimati.value = idpermohonansimati;
		document.${formName}.idSimati.value = idSimati;
		document.${formName}.tarikh_mohon.value = tarikhMohon;
		
	} else 
//kak ayu		
	if (idStatus == '153'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&hitButt=papar";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanSek17BKE&hitButt=papar";
		}
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.idsimati.value = idSimati;
	
		// Kemaskini oleh Mohamad Rosli pada 10/03/2011, tambah kondisi untuk status BATAL LAIN - LAIN KES (id_status=70)	
	} else if(idStatus == '70'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
		} else {
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
		}
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idpermohonansimati.value = idpermohonansimati;
		document.${formName}.idSimati.value = idSimati;
		document.${formName}.tarikh_mohon.value = tarikhMohon;
	
	} else {
		alert("Sila Hubungi Sistem Admin")
	
	}

	document.${formName}.method="POST";
	document.${formName}.submit();
	
}
</script>