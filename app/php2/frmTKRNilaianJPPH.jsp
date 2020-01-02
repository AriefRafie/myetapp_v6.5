<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>NILAI TANAH DIPOHON</legend>
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="10%"><strong>Lot</strong></td>
          <td width="10%"><strong>No Hakmilik</strong></td>
          <td width="10%"><strong>No Warta</strong></td>
          <td width="15%"><strong>Mukim</strong></td>
          <td width="10%"><strong>Daerah</strong></td>
          <td width="10%"><strong>Negeri</strong></td>
           <td width="10%" align="center"><strong>Nilaian Tanah(RM)</strong></td>
          <td width="10%" align="center"><strong>Nilaian Bangunan(RM)</strong></td>
          <td width="10%" align="center"><strong>Jumlah Nilaian(RM)</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiTanahPohon.size() > 0)
        #foreach ($list in $SenaraiTanahPohon)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row">$list.lot</td>
          <td class="$row">$list.hakmilik</td>
          <td class="$row">$list.noWarta</td>
          <td class="$row" >$list.mukim</td>
          <td class="$row">$list.daerah</td>
          <td class="$row">$list.negeri</td>
          <td class="$row" align="center"><input name="txtNilaiTanahPohon" type="text" value="$list.nilaian" style="text-align:right" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$list.nilaian');total()" >
          <td class="$row" align="center"><input id="txtNilaiBangunanPohon" name="txtNilaiBangunanPohon" type="text" value="$list.nilaianB" style="text-align:right" $readonly class="$inputTextClass" onblur="total()">
          <td class="$row" align="center">
          <input id="txtNilaianTotal" name="txtNilaianTotal" type="text" style="text-align:right" $total() value = "$list.nilaianTotal" readonly="readonly" class="$inputTextClass" >
          <input name="idHakmilikPermohonan" type="hidden" value="$list.idHakmilikPermohonan">
          </td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend>NILAI TANAH GANTI</legend>
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="10%"><strong>Lot</strong></td>
          <td width="10%"><strong>No Hakmilik</strong></td>
          <td width="10%"><strong>No Warta</strong></td>
          <td width="15%"><strong>Mukim</strong></td>
          <td width="10%"><strong>Daerah</strong></td>
          <td width="10%"><strong>Negeri</strong></td>
          <td width="10%" align="center"><strong>Nilaian Tanah(RM)</strong></td>
          <td width="10%" align="center"><strong>Nilaian Bangunan(RM)</strong></td>
          <td width="10%" align="center"><strong>Jumlah Nilaian(RM)</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiTanahGanti.size() > 0)
        #foreach ($list in $SenaraiTanahGanti)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row">$list.lot</td>
          <td class="$row">$list.hakmilik</td>
          <td class="$row">$list.noWarta</td>
          <td class="$row" >$list.mukim</td>
          <td class="$row">$list.daerah</td>
          <td class="$row">$list.negeri</td>
          <td class="$row" align="center"><input name="txtNilaiTanahGanti" type="text" value="$list.nilaian" style="text-align:right" $readonly class="$inputTextClass" onblur="validateCurrency(this,this.value,'$list.nilaian');total1()">
          <td class="$row" align="center"><input id="txtNilaiBangunanGanti" name="txtNilaiBangunanGanti" type="text" value="$list.nilaianB" style="text-align:right" $readonly class="$inputTextClass" onblur="total1()">
          <td class="$row" align="center">
          <input id="txtGantiTotal" name="txtGantiTotal" type="text" style="text-align:right" $total() value = "$list.nilaianTotal" readonly="readonly" class="$inputTextClass" >
          <input name="idTanahGanti" type="hidden" value="$list.idTanahGanti">
          </td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center"> #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniNilaian()"/>
      #if ($idStatus == '1610199')
      <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniNilaian()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalNilaian()"/>
      #end </td>
  </tr>
</table>
<script>
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
function kemaskiniNilaian() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");	
}
function simpanKemaskiniNilaian(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatNilaian";
	document.${formName}.submit();
}
function batalNilaian(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function total(){

	if (document.${formName}.txtNilaiTanahPohon.value != "" || document.${formName}.txtNilaiBangunanPohon.value != ""){

	var tanah = document.${formName}.txtNilaiTanahPohon.value.replace(/,/g,'');
	var bangunan = document.${formName}.txtNilaiBangunanPohon.value.replace(/,/g,'');
	var nilaian = tanah * 1;
	var nilaianB = bangunan * 1;
	//var nilaian = document.${formName}.nilaian.value;
	//var nilaianB = document.${formName}.nilaianB.value;
	
	var total = parseFloat(nilaian) + parseFloat(nilaianB);

	document.${formName}.txtNilaianTotal.value = total.toFixed(2);
	}
	else{
	
	document.${formName}.txtNilaianTotal.value = "";
	}
}
function total1(){

	if (document.${formName}.txtNilaiTanahGanti.value != "" || document.${formName}.txtNilaiBangunanGanti.value != ""){

	var tanah = document.${formName}.txtNilaiTanahGanti.value.replace(/,/g,'');
	var bangunan = document.${formName}.txtNilaiBangunanGanti.value.replace(/,/g,'');
	var nilaian = tanah * 1;
	var nilaianB = bangunan * 1;
	//var nilaian = document.${formName}.nilaian.value;
	//var nilaianB = document.${formName}.nilaianB.value;
	
	var total = parseFloat(nilaian) + parseFloat(nilaianB);

	document.${formName}.txtGantiTotal.value = total.toFixed(2);
	}
	else{
	
	document.${formName}.txtGantiTotal.value = "";
	}
}
</script>
