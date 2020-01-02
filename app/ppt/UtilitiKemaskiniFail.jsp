<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset>
        <legend><b>Semakan Permohonan</b></legend>
        <table width="100%" align="center" border="0">
          <tbody>
            <tr>
              <td width="30%" height="24" scope="row" align="right">No Fail : </td>
              <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="40" maxlength="50" style="text-transform:uppercase;" >
         <!-- razman -->
        <input type="hidden" name="id_permohonan" />			
        <input type="hidden" name="flagAdvSearch" value="$flagAdvSearch"/>
        <input type="hidden" name="flag_MyInfoPPT"/>
        <input type="hidden" name="flag_UtilitiKemaskiniFail"/>
         
         <!-- elly -->
        
         <!-- shah -->
		
		 
         </td>
            </tr>
            <tr>
              <td scope="row"></td>
              <td><input name="cmdCari" id="cmdCari" value="Semak" type="button" onClick="javascript:carian()">
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
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
    <td>
    
    <fieldset>
		<legend><b>Senarai Status Permohonan</b></legend>
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%"><strong>Bil</strong></td>
              <td width="95%"><strong>Status</strong></td>
            </tr>
          #set ($list = "")
          #foreach ($list in $SenaraiFail)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          <tr>
            <td class="$row">$list.bil</td>
            #if($list.ID_PERMOHONAN == '')
            <td class="$row">$list.STATUS</td>
            #else
            <td class="$row"><a href="javascript:papar('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA')" class="style1">$list.STATUS</a></td>
            #end
          </tr>
          #end
          </tbody>
        </table>
		</fieldset>
	</td>
  </tr>
</table>

<script>
function carian(){
document.${formName}.action = "?_portal_module=ekptg.view.ppt.UtilitiKemaskiniFail";
document.${formName}.method="POST";
//	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	//document.${formName}.submit();
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UtilitiKemaskiniFail";
document.${formName}.method="POST";
//	document.${formName}.action.value = "";
	document.${formName}.submit();
	
	
}
function papar(ID_PERMOHONAN,ID_STATUS,ID_FAIL,TARIKH_PERMOHONAN,FLAG_JENISPERMOHONAN,ID_SUBURUSAN,FLAG_SEGERA) {

//**START SEKSYEN 4 DAN 8
	
	//status permohonan dan disahkan pengarah
	if (ID_STATUS == '11' || ID_STATUS == '127'){	
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=semak&paging=1";
		}else if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8&command=semakPendaftaran&ScreenLocation=top&paging=1";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan&command=semakPendaftaran&ScreenLocation=top&paging=1";			
		}else{
			alert("Sila Hubungi Admin");
		}	
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close 

	
	//status tindakan pegawai
	else if (ID_STATUS == '148'){
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4&command=tambahAgihan&ScreenLocation=top&paging=2";
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai&command=tambahLaporan&paging=3";
		}else if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8&command=tambahAgihan&ScreenLocation=top&paging=3";
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah&command=tambahLaporan&paging=4";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraAgihanTugas&command=tambahAgihan&ScreenLocation=top&paging=3";			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close 

	
	//status laporan awal dan mmk
	else if (ID_STATUS == '147' || ID_STATUS == '26'){
		
		if (ID_SUBURUSAN == '51'){
			if(ID_STATUS == '147'){
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek4LaporanAwalTanahSenarai&command=tambahLaporan&paging=3";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai&command=viewSenaraiMMK&ScreenLocation=top&paging=4";
			}
		}else if (ID_SUBURUSAN == '52'){
			if(ID_STATUS == '147'){
				//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD&command=viewListHM&ScreenLocation=top&paging=5";
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8LaporanAwalTanah&command=tambahLaporan&paging=4";
			}else{
				//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Warta&command=viewSenaraiWarta&ScreenLocation=top&paging=10";
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmMMKSek8&command=viewMMK&ScreenLocation=top&paging=9";
			}
		}else if (ID_SUBURUSAN == '53'){
			if(ID_STATUS == '147'){
				//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD&command=viewListHM&ScreenLocation=top&paging=5";
				document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraLaporanAwalTanah&command=tambahLaporan&paging=4";
			}else{
				//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Warta&command=viewSenaraiWarta&ScreenLocation=top&paging=10";
				document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraMMK&command=viewMMK&ScreenLocation=top&paging=8";
			}			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close

	
	else if (ID_STATUS == '31' || ID_STATUS == '52'){
		
		if (ID_SUBURUSAN == '51'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 4",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek4NotisAwam&command=viewListNotis&ScreenLocation=top&paging=5";
		}else if (ID_SUBURUSAN == '52'){
			if(ID_STATUS == '31'){
				//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK&command=viewEndosan&ScreenLocation=top&paging=11";
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Warta&command=viewSenaraiWarta&ScreenLocation=top&paging=10";
			}else{
				document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam&command=viewListNotis&ScreenLocation=top&paging=13";
				//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyampaianNotis&command=viewListHM&ScreenLocation=top&paging=14";
			}
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraNotisAwam&command=viewListNotis&ScreenLocation=top&paging=9";					
		}else{
			alert("Sila Hubungi Admin");
		}	
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close 

	
	else if (ID_STATUS == '16'){
		
		if (ID_SUBURUSAN == '52'){
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek8&command=tambahAgihan&ScreenLocation=top&paging=3";
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8Hakmilik&command=semakHM&ScreenLocation=top&paging=2";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik&command=semakHM&ScreenLocation=top&paging=2";			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close

	
	else if (ID_STATUS == '43' || ID_STATUS == '22'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8InfoJPBD&command=viewListHM&ScreenLocation=top&paging=5";
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmMMKSek8&command=viewMMK&ScreenLocation=top&paging=9";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraInfoJPPH&command=viewListHM&ScreenLocation=top&paging=11";		
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close


	else if (ID_STATUS == '132' || ID_STATUS == '133' || ID_STATUS == '134'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmMMKSek8&command=viewMMK&ScreenLocation=top&paging=9";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraMMK&command=viewMMK&ScreenLocation=top&paging=8";			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close


	else if (ID_STATUS == '35'){
		
		if (ID_SUBURUSAN == '52'){
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF&command=viewListHM&ScreenLocation=top&paging=12";
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK&command=viewEndosan&ScreenLocation=top&paging=11";
		}else{
			alert("Sila Hubungi Admin");
		}

		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		
	}//close


	else if (ID_STATUS == '54'){
		
		if (ID_SUBURUSAN == '52'){
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam&command=viewListNotis&ScreenLocation=top&paging=13";
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF&command=viewListHM&ScreenLocation=top&paging=12";
		}else{
			alert("Sila Hubungi Admin");
		}

		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
		
	}//close


	else if (ID_STATUS == '58'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyampaianNotis&command=viewListHM&ScreenLocation=top&paging=14";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraBuktiPenyampaianNotis&command=viewListHM&ScreenLocation=top&paging=10";			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close 

	
	else if (ID_STATUS == '38'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan&command=penandaanKawasan&ScreenLocation=top&paging=15";
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Siasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=16";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraPenandaanKawasan&command=penandaanKawasan&ScreenLocation=top&paging=12";		
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close


	else if (ID_STATUS == '62'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Siasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=16";
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&command=viewlistHM&ScreenLocation=top&paging=17";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.FrmSementaraSiasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=13";	
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close


	else if (ID_STATUS == '68' || ID_STATUS == '72'){
		
		if (ID_SUBURUSAN == '52'){
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8Siasatan&command=Siasatan&sub_command=Senarai&subminor_command=View&id_pembatalan=''&paging=16";
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan&command=viewlistHM&ScreenLocation=top&paging=17";
		}else if (ID_SUBURUSAN == '53'){
			document.${formName}.action = "$EkptgUtil.getTabID("Pendudukan/Penggunaan Sementara",$portal_role)?_portal_module=ekptg.view.ppt.SementaraPampasanPB&command=viewlistHM&ScreenLocation=top&paging=14";			
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close

	
	else if (ID_STATUS == '59'){
		
		if (ID_SUBURUSAN == '52'){
			//document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8BorangK&command=viewListHM&ScreenLocation=top&paging=18";
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8AmbilSegera&command=viewSegera&ScreenLocation=top&paging=22";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close


	else if (ID_STATUS == '76'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8BorangK&command=viewListHM&ScreenLocation=top&paging=18";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close 


	else if (ID_STATUS == '82'){
		
		if (ID_SUBURUSAN == '52'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppt.FrmSek8PermintaanUkur&command=viewListHM&ScreenLocation=top&paging=23";
		}else{
			alert("Sila Hubungi Admin");
		}
		document.${formName}.id_permohonan.value = ID_PERMOHONAN;
	}//close

	
//**END SEKSYEN 4 DAN 8

	
	//penarikan balik - razman

	else if (ID_STATUS == '74'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPenarikanBalikInternal&command=senarai&sub_command=papar";
			}
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
	}
	
//pembatalan - razman	
	else if (ID_STATUS == '235'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Pembatalan/Penarikan Balik",$portal_role)?_portal_module=ekptg.view.ppt.FrmPembatalanInternal&command=senarai&sub_command=papar";
			}
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;			
	}
	
//bantahan - elly 

	// DALAM PROSES
	else if (ID_STATUS == '181'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// URUSAN DEPOSIT
	else if (ID_STATUS == '183'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}

	// URUSAN MAHKAMAH
	else if (ID_STATUS == '184'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// TARIK BALIK BANTAHAN
	else if (ID_STATUS == '185'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// URUSAN BAYARAN
	else if (ID_STATUS == '186'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=UrusanPampasan";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// SELESAI
	else if (ID_STATUS == '187'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// PEMBATALAN OLEH MT
	else if (ID_STATUS == '220'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanPampasan&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// BORANG O
	else if (ID_STATUS == '1610248'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}	
	
	// DALAM PROSES[AGENSI]
	else if (ID_STATUS == '199'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}		
	
	// URUSAN DEPOSIT[AGENSI]
	else if (ID_STATUS == '200'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}

	// URUSAN MAHKAMAH[AGENSI]
	else if (ID_STATUS == '201'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
	}
	
	// TARIK BALIK[AGENSI]
	else if (ID_STATUS == '203'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;		
				
	}
				
	// BORANG O [AGENSI]
	else if (ID_STATUS == '1610249'){
			if (ID_SUBURUSAN == '52'){
				document.${formName}.action = "$EkptgUtil.getTabID("Bantahan Mahkamah",$portal_role)?_portal_module=ekptg.view.ppt.FrmBantahanAgensiPemohonSenaraiCarian&command=papar_pb";
			}
				//document.${formName}.id_fail.value = ID_FAIL;	
				document.${formName}.id_permohonan.value = ID_PERMOHONAN;					

	}else{
		alert("Sila Hubungi Admin");
		return;
	}	
		
	
	
	document.${formName}.flag_UtilitiKemaskiniFail.value = "yes";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
</script>