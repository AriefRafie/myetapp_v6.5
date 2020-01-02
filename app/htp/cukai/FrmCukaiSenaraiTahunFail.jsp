
<table width="98%" border="0">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
<fieldset style="width:auto"><legend>SENARAI FAIL MENGIKUT TAHUN</legend>
#parse("app/utils/record_paging.jsp")
<table width="50%" border="0">
<tr class="table_header">
<td width="1%">Bil.</td>
  <td >Tahun</td>
  <td >Bil. Fail</td>
</tr>
#set ( $cnt = 0 )			
  #foreach ( $senarai in $SenaraiTahun )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
<tr>
<td class="$row" width="1%">$cnt.
</td>
<td class="$row"><a href="javascript:doAjaxCall${formName}('senaraiFail','tahun=$senarai.tahunFail')" style="font-style:oblique">$senarai.tahunFail</a></td>
<td class="$row">$senarai.Counttahun</td>
</tr>#end

#if ($cnt == 0)
  	<tr> 
  		<td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  	</tr>
#end
</table>
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
	doAjaxCall${formName}("senaraiFail");
}
function carianHakmilik(){

	doAjaxCall${formName}("searchHakmilik");

}
function tambahanManual(tahun){
	doAjaxCall${formName}("createNew",'tahun=tahun');
	
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

	var jumBayaran = tahunan + CukaiLain + Tungakan + Denda - Lebihan;

	document.${formName}.txtJumBayaran.value = jumBayaran.toFixed(2);
}
function calculate2(){
	var kenebayar1 = document.${formName}.kenebayar.value * 1;
	var cukailain1 = document.${formName}.cukailain.value * 1;
	var tungakkan1 = document.${formName}.tungakkan.value * 1;
	var denda1 = document.${formName}.denda.value * 1;
	var lebihan1 = document.${formName}.lebihan.value * 1;
	
	var jumBayaran2 = kenebayar1 + cukailain1 + tungakkan1 + denda1 - lebihan1;
		
	document.${formName}.JumBayaran.value = jumBayaran2.toFixed(2);
	
	}
</script>