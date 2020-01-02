 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
  
<fieldset>
<legend>Maklumat Kemasukan Bayaran</legend>
	<fieldset><legend>
<b >Carian</b>
</legend>
<table  align="center" border="0" width="80%" >
  <tbody>
    <!--<tr>
      <td width="30%" height="24" scope="row" align="right">No Fail : </td>
      <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>-->
          <tr> 
        <td scope="row" align="center">&nbsp;No Fail : <input name="txtNoFail" id="txtNoFail" type="text" value="$noFail" size="50" maxlength="50" style="text-transform:uppercase;"> </td>
        <!--<td>:&nbsp;</td>
        <td><input name="txtNoFail" id="txtNoFail" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > </td>
      -->
      	<input type="hidden" name="idPermohonan" value="$idPermohonan"/>
      	</tr>
    <!--<tr>
      <td width="30%" height="24" scope="row" align="right">Nama Pemohon : </td>
      <td width="70%"><input name="txtPemohon" id="txtPemohon" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">Nama Simati : </td>
      <td width="70%"><input name="txtSimati" id="txtSimati" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>
    <tr>
      <td width="30%" height="24" scope="row" align="right">No.KP Simati : </td>
      <td width="70%"><select name="jenisIc" style="width: 100px;text-transform:uppercase;" >
      <option value="0">Sila Pilih</option>
      <option value="1">No. KP Baru</option>
      <option value="2">No. KP Lama</option>
      <option value="3">No. KP Lain</option>
      </select>&nbsp;&nbsp;<input name="txtIcSimati" id="txtIcSimati" type="text" value="" size="20" maxlength="14" style="text-transform:uppercase;" > 
 <input type="hidden" name="idFail" /></td>
    </tr>-->
    <tr>
      <td valign="top" align="center" colspan="1">
      	<input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:searchData()">
        <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" >
        </td>
    </tr>
  </tbody>
</table>
<table border="0" align="center" width="100%" >
 	#set ($cnt = 0)
 	<tr class="table_header">
    <td width="2%" align="center"><strong>##</strong></td>
   	<td width="20%" align="center"><strong>No Fail</strong></td>
    <td width="39%" align="center"><strong>Nama Si Mati</strong></td>
    <td width="39%" align="center"><strong>Nama Pemohon</strong></td>
    <td width="0%" align="center"></td>
    </tr>
 
 	#foreach ( $senarai in $vecSenaraiCarian )
  	#set ( $cnt = $cnt + 1 )
    	#set( $i = $velocityCount )
        #if ( ($i % 2) == 0 )
        	#set( $row = "row2" )
      	#else
        	#set( $row = "row1" )
        #end
   	<tr>
  	<td class="$row">$cnt.</td>
  	<td class="$row"><a href="javascript:doPilihFail('$senarai.idPermohonan','$senarai.noFail')" class=style1>$senarai.noFail</a></td>
  	<td class="$row" align="center">$senarai.namaSimati</td>
  	<td class="$row" align="center">$senarai.namaPemohon</td>
  	<td class="$row">
		<!--
		<a href="javascript:doDelete('$senarai.idlaporanbilfail')"><img src="../img/delete.gif" border="0"></a>
    --></td> 
   	</tr>	
	#end 
	
	#if ($cnt == 0)
  	<tr> 
   	<td colspan="4" align="center" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  	</tr>
    #end 
</table>
</fieldset>
<p></p>
<fieldset>
<table border="0" align="center" width="60%" >      
     <tbody> 
     	<!--<tr> 
        <td scope="row" align="left">&nbsp;No Fail </td>
        <td>:&nbsp;</td>
        <td></td>
      </tr> -->
      <tr> 
        <td scope="row" align="left">&nbsp;Bayaran </td>
        <td>:&nbsp;</td>
        <td>$selectJenisBayaran</td>
      </tr>
      <tr> 
        <td scope="row" align="left">&nbsp;Cara Bayar </td>
        <td>:&nbsp;</td>
        <td>$selectCaraBayaran</td>
      </tr>   
      #set ($txdBayar = "")
      
      <tr> 
        <td scope="row" align="left">&nbsp;Tarikh Bayar</td>
        <td>:&nbsp;</td>
        <td>
       		<label>
      			<input name="txdBayar" type="text" id="txdBayar" value="$txdBayar" size="10" />
        	</label>
        	<a href="javascript:displayDatePicker('txdBayar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
		</td>
       </tr> 
    	#set ($txtBilangan = "")
    	#set ($rujukan = "")
       <tr> 
        <td scope="row" align="left">&nbsp;No Rujukan </td>
        <td>:&nbsp;</td>
        <td> 
			<input name="Form_rujukan" id="Form_rujukan" type="text" value="$rujukan" maxlength="15"  size="15" />
		</td>
      </tr>      
      <tr> 
        <td scope="row" align="left">&nbsp;Jumlah </td>
        <td>:&nbsp;</td>
        <td> 
			<input name="Form_bilangan" id="Form_bilangan" type="text" value="$jumlahBayaran" maxlength="10" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="javascript:validatePoskod(this,this.value)" size="10" />
		</td>
      </tr>  
	<tr>
		<td valign="top" align="center" colspan="3">
		<input name="btnsubmit" type="button" value="Simpan" onclick="tambah()">
		<input type="reset" value="Batal" >
		</td>
	</tr>
     
   </tbody>
 </table>
</fieldset>	

<fieldset>
<legend>Senarai Kemasukan </legend>
 #parse("app/utils/record_paging.jsp")	
<table border="0" align="center" width="100%" >
 	#set ($cnt = 0)
 	<tr class="table_header">
    <td width="2%" align="center"><strong>##</strong></td>
   	<td width="20%" align="center"><strong>No Fail</strong></td>
    <td width="35%" align="center"><strong>Bayaran</strong></td>
    <td width="25%" align="center"><strong>Jumlah(RM)</strong></td>
    <td width="12%" align="center"><strong>Tarikh</strong></td>
    <td width="6%" align="center"></td>
    </tr>
 
 	#foreach ( $senarai in $SenaraiFail )
  	#set ( $cnt = $cnt + 1 )
    	#set( $i = $velocityCount )
        #if ( ($i % 2) == 0 )
        	#set( $row = "row2" )
      	#else
        	#set( $row = "row1" )
        #end
   	<tr>
  	<td class="$row">$cnt.</td>
  	<td class="$row">$senarai.nofail</td>
  	<td class="$row" align="center">$senarai.keterangan</td>
  	<td class="$row" align="center">$senarai.jumlahbayaran</td>  	
  	<td class="$row" align="center">$senarai.tarikhbayar</td>
  	<td class="$row">
		<a href="javascript:doEdit('$senarai.idbayaran','$senarai.idjenisbayaran','$senarai.tarikhbayar','$senarai.noresit','$senarai.jumlahbayaran','$senarai.idpermohonan')"><img src="../img/edit.gif" border="0"></a>
		<a href="javascript:doDelete('$senarai.idbayaran','$senarai.nofail')"><img src="../img/delete.gif" border="0"></a>
    </td> 
   	</tr>	
	#end 
	
	#if ($cnt == 0)
  	<tr> 
   	<td colspan="4" align="center" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  	</tr>
    #end 
</table>
</fieldset>


</fieldset>



<input type="hidden" name="idlaporanbilfail" value="0">
<input type="hidden" name="command3">	

<!--</form>	-->
	

	
<script>
	
	function doPilihFail(id,fail) {
		document.${formName}.txtNoFail.value = fail;
		document.${formName}.idPermohonan.value = id;
		doAjaxCall${formName}("PilihFail");
	}	
	
	function searchData() {
		if(document.${formName}.txtNoFail.value==""){
			alert('Sila isi No Fail Fail terlebih dahulu');
			return;
		}
		doAjaxCall${formName}("Carian");
	}	
		
	function doEdit(id,jenis,tarikh,ruj,bilangan,idp) {
		document.${formName}.idlaporanbilfail.value = id;
		document.${formName}.socJenisbayaran.value = jenis;
		document.${formName}.txdBayar.value = tarikh;
		document.${formName}.Form_rujukan.value = ruj;
		document.${formName}.Form_bilangan.value = bilangan;
		document.${formName}.idPermohonan.value = idp;
		document.${formName}.btnsubmit.value = "Kemaskini";
		
	}
		
		
	function doDelete(id,no) {
		if ( !window.confirm("Adakah anda pasti?") ) return;
		document.${formName}.idlaporanbilfail.value = id;
		document.${formName}.txtNoFail.value = no;		
		doAjaxCall${formName}("Delete");
		
	}
		
	function doJenisBayaran(no) {
		document.${formName}.txtNoFail.value = no;		
	
		if(document.${formName}.socJenisbayaran.value=="0")
			return;
		//doAjaxCall${formName}("SelectJenisBayaran","txtNoFail="+document.${formName}.txtNoFail.value);
		doAjaxCall${formName}("SelectJenisBayaran");
	
	}
	
	function doJenisBayaran() {
	
		if(document.${formName}.socJenisbayaran.value=="0"){
			return;
		}
		//doAjaxCall${formName}("SelectJenisBayaran","txtNoFail="+document.${formName}.txtNoFail.value);
		doAjaxCall${formName}("SelectJenisBayaran");
	
	}
	
	function tambah() {
	
		if(document.${formName}.idPermohonan.value==""){
			alert("Sila pilih No Fail terlebih dahulu");
			return ; 	
		}
		//if(document.${formName}.socJenisbayaran.value=="" || document.${formName}.Form_id_Daerah.value==""){
		if(document.${formName}.socJenisbayaran.value=="0"){
			//alert("Sila pilih Unit/Daerah terlebih dahulu");
			alert("Sila pilih Bayaran terlebih dahulu");
			return;
		}
		if(document.${formName}.Form_bilangan.value==""){
			alert("Sila masukkan maklumat bayaran");
			return ; 	
		}
		
		doAjaxCall${formName}("simpan");
	}


</script>


