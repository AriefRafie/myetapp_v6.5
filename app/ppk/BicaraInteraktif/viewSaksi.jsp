
#if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
#end

<table border="0" cellspacing="1" cellpadding="3" width="100%" class="classFade" > 
	#if($listSaksi.size()>0)
    <!--
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/ppk/BicaraInteraktif/recordPageList.jsp")
		   </td>		     
	</tr>
    -->
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top" width="5%">Bil.</td>
		   <td   align="left" valign="top" >Nama</td>
           <td   align="left" valign="top" >Pengenalan</td>
		   <td   align="left" valign="top" >Kaitan / Hubungan</td>	
           <td   align="center" valign="top" >Umur</td>	
           <td   align="left" valign="top" >Status</td>		  
           <td   align="center" valign="top" width="10%">Tindakan
           </td>
	</tr>
	#if($listSaksi.size()>0)	
		#foreach($pr in $listSaksi)
		<tr id="rowSaksi_$pr.ID_BIKEHADIRAN" >
			   <td class="$pr.rowCss"  align="center" valign="top" >$pr.BIL </td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.NAMA</td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.PENGENALAN</td>	
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.HUBUNGAN</td>	
               <td class="$pr.rowCss"  align="center" valign="top" >$pr.UMUR</td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.STATUS</td>
               <td class="$pr.rowCss"  align="center" valign="top" >
               <a href="javascript:doDivAjaxCall$formname('rowSaksi_$pr.ID_BIKEHADIRAN','editSaksi','ID_BIKEHADIRAN=$pr.ID_BIKEHADIRAN&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&rowCss=$pr.rowCss&BIL=$pr.BIL&scrolPosition='+getPageLocation())"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
               <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){doDivAjaxCall$formname('view_saksi','delete_saksi','ID_BIKEHADIRAN=$pr.ID_BIKEHADIRAN&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation());}"><img title="Hapus" src="../img/delete.gif" border="0"></a>
               
               
               <span id="spanIconKeteranganSaksi$pr.ID_BIKEHADIRAN" style="display:none">
               <a href="javascript:doDivAjaxCall$formname('tdViewKeteranganSaksi$pr.ID_BIKEHADIRAN','showKeteranganSaksi','ID_BIKEHADIRAN=$pr.ID_BIKEHADIRAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI&ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&scrolPosition='+getPageLocation()+'&div=tdViewKeteranganSaksi$pr.ID_BIKEHADIRAN')"><img title="Keterangan" src="../img/write.gif" #if($pr.KETERANGAN != "") class="blink" #end border="0"></a>	   
               </span>
				
                <script type="text/javascript">
				if('$pr.ID_BIKEHADIRAN' != "")
				{
					document.getElementById('spanIconKeteranganSaksi$pr.ID_BIKEHADIRAN').style.display = "";
				}				
				</script>
               
               </td>	
		</tr>	
        <tr>
        <td colspan="8" valign="top" id="tdViewKeteranganSaksi$pr.ID_BIKEHADIRAN">
        <textarea style="display:none"  id="KETERANGAN_SAKSI_$pr.ID_BIKEHADIRAN" name="KETERANGAN_SAKSI_$pr.ID_BIKEHADIRAN">$pr.KETERANGAN</textarea>
        </td>
        </tr>	
		#end	
	#end
    <tr>
    <td colspan="10" valign="top" style="border-bottom: 1px solid black !important;"><b>Tambah Saksi</b></td>
    </tr>
    <tr id="rowAddSaksi" >
			   <td class="$pr.rowCss"  align="center" valign="top" >-</td>
               <td class="$pr.rowCss"  align="left" valign="top" ><input type="text" style="text-transform:uppercase" name="NAMA_SAKSI_" class="fullwidth_input" id="NAMA_SAKSI_" value = "" placeholder="Sila Masukkan Nama..." maxlength="500"  /></td>
               <td class="$pr.rowCss"  align="left" valign="top" ><input type="text" style="text-transform:uppercase" name="PENGENALAN_SAKSI_" class="fullwidth_input"  id="PENGENALAN_SAKSI_" value = ""  maxlength="20" /></td>	
               <td class="$pr.rowCss"  align="left" valign="top" >
               $dataHubungan
               <input type="text" style="text-transform:uppercase" name="HUBUNGAN_TURUTHADIR_" class="fullwidth_input"  id="HUBUNGAN_SAKSI_" value = "" maxlength="100" list="dataHubungan"   /></td>
               <td class="$pr.rowCss"  align="center" valign="top" ><input type="text" style="text-transform:uppercase" name="UMUR_SAKSI_" class="fullwidth_input"  id="UMUR_SAKSI_" value = ""  maxlength="3" onkeydown="validateNumber(event);" /></td>
               <td class="$pr.rowCss"  align="left" valign="top" >
               $dataStatusOB
               <input type="text" style="text-transform:uppercase" name="STATUS_SAKSI_" class="fullwidth_input"  id="STATUS_SAKSI_" value = ""  maxlength="100" list="dataStatusOB" /></td>	
               <td class="$pr.rowCss"  align="center" valign="top" >
               <input type="button" id="cmdAddSaksi" name="cmdAddSaksir" value="Tambah" onClick="simpanSaksi('','$ID_PERBICARAAN','$ID_PERMOHONAN',getPageLocation(),'','')" >               
               </td>	
		</tr>		
	</table>
</fieldset>
<script>
	document.getElementById('rowAddSaksi').style.display = "";

	var flagDisable = document.getElementById("flagDisable").value;
	if(flagDisable == "Y")
	{
		disableInput("view_saksi");
	}
 
	$jquery(document).ready(function () {
		//alert("x : "+$jquery('#divPeringatanMesrakeputusan').length);	
	if($jquery('#divPeringatanMesrakeputusan').length > 0)
	{
		doDivAjaxCall$formname('divPeringatanMesrakeputusan','show_PeringatanMesra','ID_PERBICARAAN=$ID_PERBICARAAN&ID_PERMOHONAN=$ID_PERMOHONAN&ID_PERMOHONANSIMATI=$ID_PERMOHONANSIMATI');	
	}
			 	  
	});
</script>