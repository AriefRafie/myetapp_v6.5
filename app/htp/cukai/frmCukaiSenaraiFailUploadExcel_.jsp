  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
<table width="98%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>

<fieldset>
  <legend>SENARAI HAKMILIK MUAT TURUN CUKAI </legend>
  <fieldset><legend>CARIAN BAGI TAHUN : $!Tahun</legend>
    <table width="50%" border="0" align="center">
      <tr>
         <td width="29%">Negeri</td>
         <td width="1%">:</td>
         <td width="70%">$lblNegeri2</td>
      </tr>
       <tr>
         <td>Daerah</td>
         <td>:</td>
         <td>$lblDaerah2</td>
       </tr>
       <tr>
         <td>Bandar/Pekan/Mukim</td>
         <td>:</td>
         <td>$lblMukim2</td>
       </tr>
       <tr>
         <td>No. Hakmilik</td>
         <td>:</td>
         <td>
          
             <input onkeyup="this.value=this.value.toUpperCase();" type="text" name="txtNoHakmilik" id="txtNoHakmilik" size="43px"/></td>
       </tr>
       <tr>
         <td colspan="3"><input class="stylobutton100" type="button" name="btnCariHakmilik" id="btnCariHakmilik" value="Cari" onclick="carianHakmilik()"/></td>
       </tr>
    </table>
  </fieldset>
  <input type="hidden" value="$Tahun" name="tahun"/>
#parse("app/utils/record_paging.jsp")

		<table width="100%" cellspacing="1" cellpadding="2" border="0">
		    <tr>
		      <td colspan="5"><input class="stylobutton100" type="button" value="Tambah" onclick="tambahanManual($Tahun)" /></td>
		    </tr>
    <tr class="table_header">
 
  	<td width="3%">Bil.</td>
  	<td width="20%">Mukim</td>
  	<td width="15%">No. Hakmilik</td>
  	<td width="15%">No. Lot/PT</td>
  	<td width="30%" >Kegunaan</td>
  	<td width="17%" align=right>Cukai Perlu di Bayar (RM)</td>
  	</tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $SenaraiFailTemp )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
                 #set ($cukai = 	$senarai.denda + $senarai.tunggakkan + $senarai.lebihan + $senarai.cukailain + $senarai.cukaiKenaBayar)
  <tr>
  <td class="$row">$cnt.</td>
  <td class="$row">
    $senarai.rujMukim.namaMukim 
    </td>

    <!--<td class="$row"><a href="javascript:uploadFail('$senarai.idpermohonan')" class="style1">$senarai.NO_HAKMILIKUPLOAD</a></td>-->
    <td class="$row"><a href="javascript:doAjaxCall${formName}('viewCukaiDetail','mode1=1&idCukai=$senarai.idCukaiTemp')" class="style1"> $!senarai.getRujJenisHakmilik().getKodJenisHakmilik() $senarai.noHakmilik</a></td>
    <td class="$row">$!senarai.getRujLot().getKodLot() $senarai.noLot</td>
    <td class="$row">$senarai.getKegunaanTanah()</td>
    <td class="$row" align=right> $UTIL.format2Decimal($senarai.cukaiPerluBayar)</td>
    </tr>
  #end
  
#if ($cnt == 0)
  	<tr> 
  		<td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  	</tr>
#end
  </table>
<input type="hidden" name="idpermohonan" >
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >
 <!-- </form> -->
</fieldset>
  	    </td> 	
  	</tr>
</table>

<script>

function doChangeDaerah1(){

	doAjaxCall${formName}("doChangeDaerah");

}
function kemaskiniManual(){
	doAjaxCall${formName}("viewCukaiDetail",'mode1=2&idCukai=$senarai.ID_CUKAITEMP');
}
function Kembali(){
	doAjaxCall${formName}("");
}
function carianHakmilik(){

	doAjaxCall${formName}("searchHakmilik");

}
function tambahanManual(){
	doAjaxCall${formName}("createNew");
	
}

function simpanManual(){

	if ( document.${formName}.manualNegeri.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.manualNegeri.focus(); return; 
	}
	if ( document.${formName}.manualDaerah.value == "" ) { 
  		alert('Sila pilih daerah terlebih dahulu.');
  		document.${formName}.manualDaerah.focus(); return; 
	}
	if ( document.${formName}.manualMukim.value == "" ) { 
  		alert('Sila pilih mukim terlebih dahulu.');
  		document.${formName}.manualMukim.focus(); return; 
	}

	if ( document.${formName}.txtNoHakmilik.value == "" ) { 
  		alert('Sila isikan no hakmilik terlebih dahulu.');
  		document.${formName}.txtNoHakmilik.focus(); return; 
	}
	if ( document.${formName}.txtNoLot.value == "" ) { 
  		alert('Sila isikan no lot terlebih dahulu.');
  		document.${formName}.txtNoLot.focus(); return; 
	}

	if ( document.${formName}.txtKegunaanTanah.value == "" ) { 
  		alert('Sila isikan kegunaan tanah terlebih dahulu.');
  		document.${formName}.txtKegunaanTanah.focus(); return; 
	}
	
	doAjaxCall${formName}("saveCukai");
}

function updateManual(){

	if ( document.${formName}.manualNegeri.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.manualNegeri.focus(); return; 
	}
	if ( document.${formName}.manualDaerah.value == "" ) { 
  		alert('Sila pilih daerah terlebih dahulu.');
  		document.${formName}.manualDaerah.focus(); return; 
	}
	if ( document.${formName}.manualMukim.value == "" ) { 
  		alert('Sila pilih mukim terlebih dahulu.');
  		document.${formName}.manualMukim.focus(); return; 
	}

	if ( document.${formName}.txtNoHakmilik.value == "" ) { 
  		alert('Sila isikan no hakmilik terlebih dahulu.');
  		document.${formName}.txtNoHakmilik.focus(); return; 
	}
	if ( document.${formName}.txtNoLot.value == "" ) { 
  		alert('Sila isikan no lot terlebih dahulu.');
  		document.${formName}.txtNoLot.focus(); return; 
	}

	if ( document.${formName}.txtKegunaanTanah.value == "" ) { 
  		alert('Sila isikan kegunaan tanah terlebih dahulu.');
  		document.${formName}.txtKegunaanTanah.focus(); return; 
	}

	if ( document.${formName}.txtTahunan.value == "" ) { 
  		alert('Sila isikan cukai Tahunan dahulu.');
  		document.${formName}.txtTahunan.focus(); return; 
	}
	
	doAjaxCall${formName}("updateCukai");
}

function doChangeDaerahManual(){
	doAjaxCall${formName}("doChangeDaerahManual");
}

function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}

function calculate(){
	var tahunan = document.${formName}.txtTahunan.value * 1;
	var CukaiLain = document.${formName}.txtCukaiLain.value * 1;
	var Tungakan = document.${formName}.txtTungakan.value * 1;
	var Denda = document.${formName}.txtDenda.value * 1;
	var Lebihan = document.${formName}.txtLebihan.value * 1;

	var jumBayaran = tahunan + CukaiLain + Tungakan + Denda + Lebihan;

	document.${formName}.txtJumBayaran.value = jumBayaran.toFixed(2);
}

</script>