<style type="text/css">

.style1 {
	color: #0033FF
}

</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'/>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  <input type="hidden" name="actionPenyewaan" />
  <input type="hidden" name="idFail" />
  <input type="hidden" name="idStatus" />
  <input type="hidden" name="idPemohon" />
  <input type="hidden" id="namamodul" name="namamodul"  >
  <input type="hidden" id="namatab" name="namatab"  >
</p>

<!-- Carian -->
#parse("app/ppt/frmStatusBicaraOnlineCarian.jsp")

<br/>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>Senarai Status Bicara</b></legend>
      <table align="center" width="100%">
        
		<tr class="table_header">
          <td scope="row"  align="center"><strong>Bil</strong></td>
          <td ><strong>Nama Projek</strong></td>
          <td ><strong>Jenis PB</strong></td>
          <td  align="left"><strong>Status</strong></td>
          
        </tr>
        
        #set ($list = "")
        #if ($listBicaraOnline.size() > 0)
        #foreach ($list in $listBicaraOnline)
        #if ($list.BIL == '')
        	#set( $row = "row1" )
        #elseif (($list.BIL % 2) != 0)
        	#set( $row = "row1" )
        #else 
        	#set( $row = "row2" )
        #end
        
        <tr>
          <td class="$row" align="center">$list.BIL</td>
          <td class="$row"><a id="hoverover" style="cursor:pointer; color:#0000FF" onClick="ShowPopup(this,$list.BIL);"  title="Klik untuk maklumat lengkap">
          $list.PROJEK
          </a></td>
          <td class="$row">$list.JENIS_PB</td>
          <td class="$row">$list.STATUS</td>
        </tr>
        
        <tr>
        <td colspan="7">
		<fieldset id="$list.BIL" style="visibility:collapse; display:none;">
 			<table width="100%" border="0">
 					<!-- <tr>
 						<td width="1%"></td>
 						<td width="25%">Nama PB</td>
 						<td width="1%">:</td>
 						<td width="73%">$list.NAMA_PB</td>
 					</tr>
 				
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>Jenis PB</td>
 						<td>:</td>
 						<td>
 						$list.JENIS_PB
 						</td>
 					</tr> -->
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>
 						Status
 						</td>
 						<td>:</td>
 						<td>
 						$list.STATUS
 						</td>
 					</tr>
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>
 						No. Fail
 						</td>
 						<td>:</td>
 						<td>
 						$list.NO_FAIL
 						</td>
 					</tr>
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>No. Siasatan</td>
 						<td>:</td>
 						<td>
 						$list.NO_SIASATAN
 						</td>
 					</tr>
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>Tarikh Siasatan</td>
 						<td>:</td>
                       <td>
 						$list.TARIKH_SIASATAN
                       </td>
 					</tr>
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>
 						Masa Siasatan
 						</td>
 						<td>:</td>
 						<td> 						
 						$list.MASA_SIASATAN $list.JENIS_WAKTU_SIASATAN					
 						</td>
 					</tr>
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>
 						Tarikh Perintah
 						</td>
 						<td>:</td>
 						<td>
 						$list.TARIKH_PERINTAH
 						</td>
 					</tr><!-- 
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>
 						Nama Projek
 						</td>
 						<td>:</td>
 						<td>
 						$list.PROJEK
 						</td>
 					</tr> -->
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>No. Hakmilik</td>
 						<td>:</td>
 						<td>
 						$list.NO_HAKMILIK
 						</td>
 					</tr>
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>
 						No. Lot
 						</td>
 						<td>:</td>
 						<td>
 						$list.NO_LOT
 						</td>
 					</tr>
 					<tr>
 						<td valign='top'>&nbsp;</td>
 						<td valign='top'>
 						Tempat Bicara
 						</td>
 						<td valign='top'>:</td>
 						<td valign='top'>
 						$list.TEMPAT_BICARA
 						#if($list.ALAMAT1 != "")
							<br/>$list.ALAMAT1
						#elseif($list.ALAMAT2 != "")
							<br>$list.ALAMAT2
						#elseif($list.ALAMAT3 != "")
							<br>$list.ALAMAT3						
						#end
						
						#if($list.POSKOD!="")
							<br>$list.POSKOD
							#if($list.BANDAR!="")
							&nbsp;$list.BANDAR
							#end
						#else
							#if($list.BANDAR!="")
							<br>$list.BANDAR
							#end
						#end
						
						#if($list.NEGERI!="")
						<br>$list.NEGERI
						#end
						</td>
 					</tr>
 				</table>
 				</fieldset>
		</td>
		</tr>
        #end
        #else
        
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

<script>
function ShowPopup(hoveritem,tab)
{
	var hp = document.getElementById(tab);
	//alert("hp.style.display :"+hp.style.display+";hp.style.visibility:"+hp.style.visibility);
	//hp.style.display=="none" &&
    if( hp.style.visibility=="collapse" && hp.style.display=="none"){
        hp.style.display = "block";
        hp.style.visibility = "visible";
    }
    //hp.style.display=="block" &&
    else if( hp.style.visibility=="visible" && hp.style.display=="block"){
        hp.style.display = "none";
        hp.style.visibility = "collapse";
    }
}
function findKosongkan() {
	document.${formName}.findNoFail.value = "";
	document.${formName}.findNamaProjek.value = "";
	document.${formName}.findNoSiasatan.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();
}
function carian() {
	document.${formName}.command.value = "cari";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.namamodul.value = "";
	document.${formName}.namatab.value = "";
    document.${formName}.action = "?myrole=online";
    //document.${formName}.namatab.value = "Menu";
    document.${formName}.namamodul.value = "ekptg.view.online.FrmOnlineMenuUtama";
    document.${formName}.submit();
}
</script>