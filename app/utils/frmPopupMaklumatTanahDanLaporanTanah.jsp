<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="idFail" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
<!--   <tr> -->
<!--     <td> -->
<!--     <fieldset> -->
<!--       <legend><b>CARIAN</b></legend> -->
<!--       <table width="100%" align="center" border="0"> -->
<!--         <tr> -->
<!--           <td width="30%" height="24" align="right">No Fail : </td> -->
<!--           <td width="70%"><input name="txtCarian" id="txtCarian" type="text" size="50" maxlength="50" style="text-transform:uppercase;" ></td> -->
<!--         </tr> -->
<!--         <tr> -->
<!--         	<td></td> -->
<!--         	<td align="left"> -->
<!-- 	          	<input type="button"  value="Cari" onClick="cari()"  /> -->
<!-- 				<input type="button"  value="Kosongkan" onClick="kosong()"  /> -->
<!--         	</td> -->
<!--         </tr> -->
<!--        </table> -->
<!--      </fieldset> -->
<!--     </td> -->
<!--    </tr> -->
   
   <tr>
    <td><fieldset>
      <legend><b>SENARAI FAIL</b></legend>
<!--       #parse("app/utils/record_paging.jsp") -->
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>No Hakmilik</strong></td>
          <td width="15%"><strong>No Fail JKPTG</strong></td>
          <td width="35%"><strong>Tajuk Fail</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="10%"><strong>Urusan</strong></td>
          <td width="10%"><strong>Keputusan</strong></td>
          <td width="8%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="10%"><strong>Laporan Tanah</strong></td>
        </tr>
        #set ($list = "")
        #if ($BeanMaklumatTanahDanLaporanTanah.size() > 0)
        #foreach ($list in $BeanMaklumatTanahDanLaporanTanah)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row">$list.kodLot $list.noLot</td>
          <td class="$row">$list.noFail</td>
          <td class="$row">$list.tajukFail</td>
		  <td class="$row">$list.status</td>
          <td class="$row">$list.namaUrusan</td>		  
          <td class="$row">$list.keputusan</td>
          <td class="$row" align="center">$list.tarikhKeputusan</td>
		  <td class="$row">$list.idLaporantanah</td>
        </tr>
        #end
        #else
        <tr>
		  <td class="$row" align="center">&nbsp;</td>
          <td class="$row">Tiada Rekod</td>
          <td class="$row">&nbsp;</td>
          <td class="$row">&nbsp;</td>
		  <td class="$row">&nbsp;</td>
          <td class="$row">&nbsp;</td>		  
          <td class="$row">&nbsp;</td>
          <td class="$row" align="center">&nbsp;</td>
		  <td class="$row">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
  	<td colspan="4" align="center">
		<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="closeWin()"/>
  	</td>
  </tr>
</table>
<script>
function closeWin(){
	window.opener.refreshFromPilihTanahBorangK();
	window.close();
}
</script>