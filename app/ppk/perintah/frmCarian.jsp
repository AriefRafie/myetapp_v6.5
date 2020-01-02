<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {color: #0000FF}
.style5 {
	color: #0000FF;
	font-style: italic;
	font-size: 9px;
}
-->
</style>



#if ($open_dashboard != "yes")
<!--
<table width="100%">
<tr>
<td align="right">
<a  href="#" title="" class="style2" onclick="javascript:dash()"><i>.</i></a> 
</td>
</tr>
</table>
-->

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset><legend><b>Carian</b></legend>
        <table width="100%" align="center" border="0">
          <tbody>
           <tr>
              <td width="30%" height="24" scope="row" align="right">MyID Simati : </td>
              <td width="70%">$selectJenisKp&nbsp;&nbsp;
                <input name="txtIcSimati" id="txtIcSimati" type="text" value="$txtIcSimati" size="20" maxlength="14" style="text-transform:uppercase;" ></td>
            </tr>
            
            <tr>
              <td width="30%" height="24" scope="row" align="right">MyID Pemohon : </td>
              <td width="70%">$selectJenisKpPemohon&nbsp;&nbsp;
                <input name="txtIcPemohon" id="txtIcPemohon" type="text" value="$txtIcPemohon" size="20" maxlength="14" style="text-transform:uppercase;" ></td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right">No Fail : </td>
              <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" > 
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
             
             
             <input type="hidden" name="flagAdvSearch" value="$flagAdvSearch"/>
             <input type="hidden" name="flagFromSenaraiPermohonanSek8"/>
             <input type="hidden" name="CAPAIAN_FAIL_UNIT_LUAR"/>
             </td>
            </tr>
            
            
           
            
            <tr>
              <td width="30%" height="24" scope="row" align="right">Tarikh Mohon : </td>
              <td width="70%"><input type="text" name="tarikhMohon" id="tarikhMohon" value="$tarikhMohon" size="9"/>
              <a href="javascript:displayDatePicker('tarikhMohon',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>              </td>
            </tr>
            
            <tr>
              <td width="30%" height="24" scope="row" align="right">Status Permohonan : </td>
              <td width="70%">$selectStatus &nbsp;&nbsp;
              #if ($flagAdvSearch == '')
                <a href="#" title="More" class="style2" onclick="javascript:more()">Buka Carian Terperinci</a> 
              #end
              #if ($flagAdvSearch == 'open') <a href="#" title="Less" class="style2" onclick="javascript:less()">Tutup Carian Terperinci</a> 
              #end              </td>
            </tr>
            #if ($flagAdvSearch == 'open')
            <tr>
              <td width="30%" height="24" scope="row" align="right">Nama Pemohon : </td>
              <td width="70%"><input name="txtPemohon" id="txtPemohon" type="text" value="$txtPemohon" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
            
            <tr>
              <td width="30%" height="24" scope="row" align="right">Nama Simati : </td>
              <td width="70%"><input name="txtSimati" id="txtSimati" type="text" value="$txtSimati" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
            
            <tr>
              <td width="30%" height="24" scope="row" align="right">No. Hakmilik : </td>
              <td width="70%"><input name="txtNoHakmilik" id="txtNoHakmilik" type="text" value="$txtNoHakmilik" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
		    <tr>
		      <td height="24" scope="row" align="right">No. Lot : </td>
		      <td><input name="txtNoLot" id="txtNoLot" type="text" value="$txtNoLot" size="50" maxlength="50" style="text-transform:uppercase;" /></td>
	      </tr>
	      <tr>
		  <td width="30%" height="24" scope="row" align="right">No. Sijil Simati : </td>
		  <td width="70%"><input name="txtNoSijil" id="txtSijil" type="text" value="$!txtNoSijil" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
            </tr>
		<tr>
		<td width="30%" height="24" scope="row" align="right">Tarikh Bicara : </td>
		<td width="70%"><input type="text" name="tarikhBicara" id="tarikhBicara" value="$!tarikhBicara" size="9"/>
		<a href="javascript:displayDatePicker('tarikhBicara',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>		</td>
		</tr>
           #end
            <tr>
              <td scope="row"></td>
              <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan('$flagAdvSearch')"></td>
            </tr>
            <tr>
              <td scope="row">&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </tbody>
        </table>
	  </fieldset>
    </td>
  </tr>
  <tr>
    <td><fieldset>
		<legend><b>Senarai Fail</b></legend>
		<!-- Jumlah Keseluruhan Fail : $JumlahFail<br> -->
		#set ($pagingTitle = "Jumlah Carian")	
		#parse("app/utils/record_paging.jsp")
		<!-- #parse("app/ppk/record_pagingAMP.jsp") -->
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
              <td width="30%"><strong>No Fail</strong></td>
              <td width="25%"><strong>Nama Simati</strong></td>
              <td width="10%" align="center"><strong>Tarikh Mohon</strong></td>
              <td width="15%"><strong>Status</strong></td>
              <td width="15%"><strong>Daftar Oleh</strong></td>
            </tr>
          #set ($list = "")
          #set ($counter = 0)
          #foreach ($list in $SenaraiFail)
          #set( $counter = $velocityCount - 1 )
          	#if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
            
            <!--#set( $counter = $velocityCount )
			#if ( ($counter % 2) == 0 )
				#set( $row = "row2" )
			#else
				#set( $row = "row1" )
			#end-->
          <tr>
            <td class="$row">
			#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
            $cnt
			</td>
            #if($list.idPermohonan == '')
            <td class="$row">$list.noFail</td>
            #else
            <td class="$row"><a href="javascript:papar('$list.idPermohonan','$list.idStatus','$list.idFail','$list.idSimati','$list.idpermohonansimati','$list.tarikhMohon','$list.flagjenisfail','$list.seksyen')" class="style1">$list.noFail</a></td>
            #end
            <td class="$row">$list.namaSimati.toUpperCase()</td>
            <td class="$row">$list.tarikhMohon</td>
            <td class="$row">$list.keterangan</td>
            <td class="$row">$list.daftarOleh</td>
            </tr>
          #end
          
           #if($counter == 0)
            <tr>
            <td></td>
            <td class="$row">
           Tiada rekod
           </td>
           </tr>
           #end 
          
          </tbody>
        </table>
		</fieldset>
	</td>
  </tr>
</table>
<div  style="display:none">
#if ($totalFailPindahMasuk > 0)

<div class="warning">
Terdapat $totalFailPindahMasuk fail yang diterima daripada luar.
<a href="#" onClick=gotoFailPindah()>Klik Sini</a>
</div>

#end
</div>



<script>
function gotoFailPindah() {
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailPindah";
	document.${formName}.submit();
}
</script>

#end



#if ($open_dashboard == "yes")

<!-- 
#parse("app/ppk/dashboard.jsp")
-->

#end


<script>

function dash() {
	
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&command=open_dashboard";
	document.${formName}.submit();
}

function gotoFLMS() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan";
	document.${formName}.submit();
}

function gotoFLMSstat() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.esaduan.FrmEtappSupportAduan&command=paparLaporan";
	document.${formName}.submit();
}


function gotoProfile() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=lebah_app_UpdateUserProfileModule";
	document.${formName}.submit();
}

function gotoCalendar() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=lebah.planner.CalendarModule";
	document.${formName}.submit();
}


function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function more(){
	
	document.${formName}.flagAdvSearch.value = "open";
	document.${formName}.action = "$EkptgUtil.getTabID("'Utiliti'",$portal_role)?_portal_module=ekptg.view.ppk.FrmAksesMaklumatPPK";
	document.${formName}.submit();
}
function less(){
	document.${formName}.flagAdvSearch.value = "";
	document.${formName}.action = "$EkptgUtil.getTabID("'Utiliti'",$portal_role)?_portal_module=ekptg.view.ppk.FrmAksesMaklumatPPK";
	document.${formName}.submit();
}
function carian(){
	
	document.${formName}.action = "$EkptgUtil.getTabID("'Utiliti'",$portal_role)?_portal_module=ekptg.view.ppk.FrmAksesMaklumatPPK";
	//document.${formName}.command.value = "carian";
	document.${formName}.submit();
	
	
	    
	
}
function SaveScrollXY() {
    document.${formName}.ScrollX.value = document.body.scrollLeft;
    document.${formName}.ScrollY.value = document.body.scrollTop;
   }
function kosongkan(flag) {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.tarikhMohon.value = "";
	document.${formName}.socStatus.value = "";
	if (flag != ''){
		document.${formName}.txtPemohon.value = "";
		document.${formName}.socJenisKpPemohon.value = "";
		document.${formName}.txtIcPemohon.value = "";
		document.${formName}.txtSimati.value = "";
		document.${formName}.socJenisKp.value = "";
		document.${formName}.txtIcSimati.value = "";
		document.${formName}.txtNoHakmilik.value = "";
		document.${formName}.txtNoLot.value = "";
		document.${formName}.txtNoSijil.value = "";
		document.${formName}.tarikhBicara.value = "";
	}
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8";
	document.${formName}.submit();
}

function semakBorangC(x) {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmMTBorangC?noFail="+x+"&command=borangPermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

	function papar(idPermohonan,idStatus,idFail,idSimati,idpermohonansimati,tarikhMohon,jenisfail,seksyen) {

		//perintah - peje
		/*
		if (idStatus == '25'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek8&actionPerintah=papar";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmPerintahSek17&actionPerintah=papar";
			}
				document.${formName}.idPermohonan.value = idPermohonan;
				document.${formName}.idPermohonanSimati.value = idpermohonansimati;
				document.${formName}.idStatus.value = idStatus;
		} else 
		*/
			
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
				}else{
					document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData&tabId=0";
				}
				
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
			
			} else  if (idStatus == '44' || idStatus == '173' || idStatus == '175' || idStatus == '177' ){
				if (seksyen == '8'){
					document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis";
				}else{
					document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis";
				}
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
			} 
	/*	else 
		if (idStatus == '18'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData";
			}
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
	} */
		else 
		if (idStatus == '21' || idStatus == '25'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=semakRayuan";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=semakRayuan";
			}			
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
	} else 
		if (idStatus == '64'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan&command=maklumatRayuan&tabId=0";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuanSek17&command=maklumatRayuan&tabId=0";
			}			
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
	} else 
		if (idStatus == '163' || idStatus == '164' || idStatus == '165' || idStatus == '166' || idStatus == '167' || idStatus == '180'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan&command=semakKeputusanRayuan";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaanSek17&command=semakKeputusanRayuan";
			}
				document.${formName}.id_permohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
	} else 
//elly
		if (idStatus == '18'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_notis";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_notis";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
				document.${formName}.id_Simati.value = idSimati;
	} else 
		if (idStatus == '47'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_batal";
			}else{
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
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_kolateral";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
				document.${formName}.id_Simati.value = idSimati;

	} else 
		if (idStatus == '174'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanMT";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanMT";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
				document.${formName}.id_Simati.value = idSimati;
	}else 
		if (idStatus == '176'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai_rujukanROTS";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai_rujukanROTS";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.id_status.value = idStatus;
				document.${formName}.id_Simati.value = idSimati;

	}/*
	else 
		if (idStatus == '56'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailPindah&command=paparTunggu";
			document.${formName}.id_permohonan.value = idPermohonan;
			document.${formName}.id_fail.value = idFail;
//	} else 
//		if (idStatus == '169'){
//			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailPindah&command=paparBatal";
//			document.${formName}.id_permohonan.value = idPermohonan;
//			document.${formName}.id_fail.value = idFail;
	} */
	
	else 
		if (idStatus == '56')
		{
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
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
			}
				document.${formName}.idpermohonan.value = idPermohonan;
				document.${formName}.idSimati.value = idSimati;
	} 
	 else 
		if ((idStatus == '8' || idStatus == '9' || idStatus == '170') && jenisfail == '3'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar";
			document.${formName}.idpermohonan.value = idPermohonan;
	} 
	 else 
//man		
		if (idStatus == '14' || idStatus == '50' || idStatus == '152'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
			}
				document.${formName}.idPermohonan.value = idPermohonan;
				document.${formName}.idpermohonansimati.value = idpermohonansimati;
				document.${formName}.idSimati.value = idSimati;
				document.${formName}.tarikh_mohon.value = tarikhMohon;
		
	} /* else 
		
		if (idStatus == ''){
		document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
		document.${formName}.idPermohonan.value = idPermohonan;
		document.${formName}.idpermohonansimati.value = idpermohonansimati;
		document.${formName}.tarikh_mohon.value = tarikhMohon;
		
	}*/  else 
//kak ayu		

	if (idStatus == '153'){
		if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanBKE&hitButt=papar";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmPemindahanSek17BKE&hitButt=papar";
		}
		document.${formName}.idpermohonan.value = idPermohonan;
		document.${formName}.idsimati.value = idSimati;
	
		// Kemaskini oleh Mohamad Rosli pada 10/03/2011, tambah kondisi untuk status BATAL LAIN - LAIN KES (id_status=70)	
		} else if(idStatus == '70'){
			if (seksyen == '8'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal&command=paparKeputusan";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal17&command=paparKeputusan";
			}
			document.${formName}.idPermohonan.value = idPermohonan;
			document.${formName}.idpermohonansimati.value = idpermohonansimati;
			document.${formName}.idSimati.value = idSimati;
			document.${formName}.tarikh_mohon.value = tarikhMohon;
	
		}else {
			alert("Sila Hubungi Sistem Admin")
	
		}
	
		document.${formName}.flagFromSenaraiPermohonanSek8.value = "yes";
		document.${formName}.method="POST";
		document.${formName}.submit();
	
	}
</script>