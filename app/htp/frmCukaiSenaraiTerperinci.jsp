<!--frmCukaiSenaraiTerperinci.jsp-->
  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>

<table width="99%" border="0">
  <tr>
    <td>&nbsp; </td>
  </tr>  
  
  <tr>
    <td>  
<fieldset>
  <legend>NEGERI : $infoNegeri.namaNegeri</legend>
#parse("app/utils/record_paging.jsp")
  <table width="100%" cellspacing="1" cellpadding="2" border="0">
  <tr class="table_header">
  	<td>Bil#</td>
  	<td>Mukim/Pekan/Bandar</td>
  	<td>Jenis/No HM</td>
  	<td>NO PT/Lot</td>
  	<td>Luas(H)</td>
  	<td>Kegunaan Tanah</td>
  	<td>Tahunan(RM)</td>
  	
  	<td>Cukai Lain</td>
  	<td>Tunggakan(RM)</td>
  	<td>Denda(RM)</td>
  	<td>Lebihan(RM)</td>
  	<td>Jumlah Bayaran(RM)</td>
  	<td>No Fail Seksyen/PTG/PTD</td>
  	<td>Catatan</td>
  	
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraicukai )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr>
  <td class="$row">$cnt.</td>
  <td class="$row">$senarai.lokasi</td><!-- $senarai.idhakmilik $senarai.lokasi -->
<!-- 
   	<td class="$row"><a href="javascript:editCukai('$fail.idNegeri','$fail.idDaerah','$fail.idMukim','$fail.idKementerian','$senarai.nohakmilik','$fail.idJenisHakmilik')" class="style1">$senarai.pHakmilik</a></td> 
-->
  <td class="$row">$senarai.pHakmilik</td>
  <td class="$row">$senarai.NO_LOTUPLOAD</td>
   <td class="$row">$senarai.luas</td>
    <td class="$row">$senarai.KEGUNAAN_TANAH</td>
  <td class="$row" align="right">$!senarai.cukai_kena_bayar</td>
  
    <td class="$row">$senarai.cukailain</td>
  <td class="$row">$senarai.tunggakan</td>
    <td class="$row">$senarai.denda</td>
  <td class="$row">$senarai.lebihan</td>
    <td class="$row">$senarai.cukai_perlu_bayar</td>
    <td class="$row">$!senarai.nofail<br>$!senarai.nofailptg_</td>
    <td class="$row">TIADA</td>
  
  </tr>
  #end
  
#if ($cnt == 0)
  	<tr> 
  		<td colspan="4" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  	</tr>
#end
  </table>
    <input type="hidden" name="idpermohonan" >
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >
   	
   	<input type="hidden" name="idnegeri" value="$!idNegeri" >
   	<input type="hidden" name="iddaerah" value="$!idDaerah" >
   	<input type="hidden" name="idmukim" value="$!idMukim" >

 <!-- </form> -->
</fieldset>



		</td>
	</tr>
</table>

<script>
	function cancel() {
		document.${formName}.reset();
	}
	
	function selectBayaran() {
		var type = document.${formName}.socbayaran.value;
		
		document.${formName}.command.value = "cukaiperingkatbayar";
		//document.${formName}.langkah.value = '0->0';
		document.${formName}.action = "";
		document.${formName}.submit();
	}
	
	function uploadFail(id) {
		document.${formName}.command.value = "cukaifailupload";
		document.${formName}.idpermohonan.value = id;
		document.${formName}.action = "";
		document.${formName}.submit();
	}
	
	function tambahPermohonan() {
		document.${formName}.command.value = "pkfailbaru";
		document.${formName}.langkah.value = '0->1';
		//document.${formName}.method = "post";
		document.${formName}.pagemode.value = "0";
		document.${formName}.action = "";
		document.${formName}.submit();
		
	}
	
	function editCukai(idNegeri,idDaerah,idMukim,idKementerian,noHakmilik,idJenisHakmilik) {
		//document.${formName}.idNegeri.value = idNegeri;
		//document.${formName}.idDaerah.value = idDaerah;
		//document.${formName}.idMukim.value = idMukim;
		//document.${formName}.idKementerian.value = idKementerian;
		//document.${formName}.noHakmilik.value = noHakmilik;
		//document.${formName}.idJenisHakmilik.value = idJenisHakmilik;
		//document.${formName}.command.value = "kemaskiniCukai";
		//document.${formName}.pagemode.value = "viewCukai";
		//document.${formName}.action = "";
		strurl = '&pagemode=viewCukai';
		//http://localhost:8081/ekptgv2.3/c/1244210176004?_portal_module=ekptg.view.htp.FrmCukaiKemaskini`
		//document.${formName}.action = "../../c/1244210176004?_portal_module=ekptg.view.htp.FrmCukaiKemaskini&command=kemaskiniCukai"+strurl;
		document.${formName}.action = "..?_portal_module=ekptg.view.htp.FrmCukaiKemaskini&command=kemaskiniCukai"+strurl;
		document.${formName}.submit();
	}
</script>
