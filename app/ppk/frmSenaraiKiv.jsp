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


<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
    <fieldset><legend><b>Carian</b></legend>
        <table width="100%" align="center" border="0">
         
           <tr>
              <td width="30%" height="24" scope="row" align="right">MyID Simati  </td>
              <td width="1%">: </td>
              <td width="69%">
                <input name="myIdSimati" id="myIdSimati" type="text" value="$myIdSimati" size="50" maxlength="14" value="$myIdSimati" style="text-transform:uppercase;" ></td>
            </tr>
            <tr>
              <td width="30%" height="24" scope="row" align="right">No Fail  </td>
              <td width="1%">: </td>
              <td width="69%"><input name="noFail" id="noFail" type="text" value="$noFail" size="50" maxlength="50" value="$noFail" style="text-transform:uppercase;" > 
            </td>
            </tr>
            
             <tr>
                <td width="30%" height="24" scope="row" align="right"> <font color="red"></font> Pegawai Pengendali</td>
                <td width="1%">: </td>
                <td width="69%" align=left> 
                	$!selectPegawai </td>
              </tr>
             
              <tr>
                <td width="30%" height="24" scope="row" align="right"> <font color="red"></font> Tarikh Matang</td>
                <td width="1%">: </td>
                <td width="69%" align=left> 
                	<input type="text"  name="txtTarikhMatang" id="txtTarikhMatang" style="text-transform:uppercase;" value="$txtTarikhMatang" size="15" maxlength="15" >
                    <a href="javascript:displayDatePicker('txtTarikhMatang',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> </td>
              </tr>
         
             <tr>
                <td width="30%" height="24" scope="row" align="right"> <font color="red"></font> Tarikh Perintah</td>
                <td width="1%">: </td>
                <td width="69%" align=left><input type="text"  name="txtTarikhPerintah" id="txtTarikhPerintah" style="text-transform:uppercase;" value="$txtTarikhPerintah" size="15" maxlength="15" >
                    <a href="javascript:displayDatePicker('txtTarikhPerintah',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> </td>
              </tr> 
              
              <tr>
                <td width="30%" height="24" scope="row" align="right">Seksyen</td>
                <td width="1%">: </td>
                <td width="69%" align=left> 
                	<select name=seksyen id=seksyen >
                		<option value="0" $selseksyen>Semua</option>
                		<option value="8" $selseksyen8>Seksyen 8</option>
                		<option value="17" $selseksyen17>Seksyen 17</option>
                	</select>


				 </td>
              </tr>
            
            <tr>
              <td scope="row"></td>
              <td ></td>
              <td ><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
                <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()">
                <input type="button" id="cmdCetakRole" name="cmdCetakRole" value="Cetak" onClick="doDivAjaxCall$formname('div_PrintKIV','printKIV','');" >
                
                </td>
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
        ** Fail-fail permohonan yang telah Selesai Perbicaraannya tetapi masih lagi KIV di atas sebab-sebab tertentu.		
		#set ($pagingTitle = "Jumlah Carian")
		#parse("app/utils/record_paging.jsp")
        <table align="center" width="100%"> 
          <tbody>
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
              <td width="40%"><strong>No Fail</strong></td>
              <td width="35%"><strong>Nama Simati</strong></td>
              <td width="25%"><strong>Tarikh Matang</strong></td>
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
            <td class="$row">
            #if($list.idPermohonan == '')
            $list.noFail
            #else
            <a href="javascript:paparKiv('$list.ID_PERMOHONAN','$list.ID_SIMATI','$list.SEKSYEN','$list.ID_PERMOHONANSIMATI','$list.TARIKH_MOHON')" class="style1">$list.NO_FAIL</a>
            #end
            </td>
            <td class="$row">$list.NAMA_SIMATI.toUpperCase()</td>
           <td class="$row">$list.DATE_KIV</td>
            </tr>

          #end
          
           #if($counter == 0)
            <tr>
            <td></td>
            <td  colspan="10">
           Tiada rekod
           </td>
           </tr>
           #end 
          
         
        </table>
		</fieldset>
	</td>
  </tr>
</table>









<div id="div_PrintKIV" style="display:none"></div>


<script>

function paparKiv(idPermohonan,idSimati,seksyen,idpermohonansimati,tarikhMohon)
{

	if (seksyen == '8'){
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanPerbicaraan&command=papar_selesai";
		}else{
			document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailKeputusanBicaraanSek17&command=papar_selesai";
		}
			document.${formName}.idpermohonan.value = idPermohonan;
			document.${formName}.id_status.value = '41';
			document.${formName}.id_Simati.value = idSimati;
		
	document.${formName}.method="POST";	
	document.${formName}.submit();
	}


function carian(){
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiKiv";
	document.${formName}.command.value = "carian";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.myIdSimati.value = "";
	document.${formName}.noFail.value = "";	
	document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiKiv";
	document.${formName}.submit();
}

function cetak() {
	
    
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupKIVReportView?"; 
//	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupRekodPindaanReportView?userId="+userId; 
	
	var hWnd = window.open(url,'printuser','width=700,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}


function printDiv(divName,GROUPUNIK,ROLEUNIK) {
	

    var originalContents = document.body.innerHTML;
    $jquery("#"+divName+" :button").hide();
    
    
    /* var elementModul =  document.getElementById('div_carianModul_'+GROUPUNIK+ROLEUNIK);
    if (typeof(elementModul) != 'undefined' && elementModul != null)
    {
    	elementModul.style.display = "none";
    }
    
    
    var elementModulCB =  document.getElementById('CB_Modul_'+GROUPUNIK+ROLEUNIK);
    if (typeof(elementModulCB) != 'undefined' && elementModulCB != null)
    {
    	document.getElementById('show_cb_Modul_'+GROUPUNIK+ROLEUNIK).style.display = "none";
    	if(elementModulCB.checked==true)
    	{
    		var elementModulPrint =  document.getElementById('div_ModulforPrint_'+GROUPUNIK+ROLEUNIK);
		    if (typeof(elementModulPrint) != 'undefined' && elementModulPrint != null)
		    {
		    	elementModulPrint.style.display = "";      	
		    }
    	}
    } */
    
    
    var printContents = document.getElementById(divName).innerHTML;
    var header='<header><div align="left"  style="font-size:150%">&nbsp;&nbsp;<b>Senarai Fail yang mempunyai dokumen KIV</b></div><br></header>'
	var footer ="";
    var popupWin = window.open('Cetakan', '_blank', 'width=1100,height=600');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()">'+header+'<div class="page-break" >'+ printContents + '</div>'+footer+'</html>');
    popupWin.document.close(); 
    document.body.innerHTML = originalContents;
    return false;
}

</script>