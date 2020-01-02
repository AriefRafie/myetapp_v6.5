<strong>BAYARAN CUKAI TANAH PERSEKUTUAN</strong><br>
<br>
<fieldset>
<!-- <form name="f1" method="post"> -->
#parse ("app/htp/frmCukaiInfo.jsp")
<!-- </form> -->
</fieldset>
<fieldset>
	<!--<form name="f2" method="post"> -->
    
    
    
	  <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
        	<tr>
            #if ( $idPeringkatbayaran == 0 )
				<input class="stylobutton" name="cmdBayar" value="Tambah Bayaran" id="cmdBayar" type="button" disabled="disabled" >
    		#else	
    			<input class="stylobutton" name="cmdBayar" value="Tambah Bayaran" id="cmdBayar" type="button" onClick="TambahBayaran();">
			#end
            </tr>
          	<tr class="table_header">
            	<td width="13%" align="center"><strong>Daerah</strong></td>
            	<td width="24%" align="center"><strong>Amaun Cek (RM)</strong></td>
            	<td width="24%" align="center"><strong>No. Cek</strong></td>
            	<td width="20%" align="center"><strong>No. Resit</strong></td>
            	<td width="19%" align="center"><b>Tarikh Resit</b></td>
           	 	<td width="24%" align="center"><strong></strong></td>
          	</tr>      
        #set ($count = 0)
      	#foreach ( $fail in $Bayaran )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
           #set( $row = "row2" )
        #else
           #set( $row = "row1" )
        #end
  <tr>
    <td class="$row" height="25"><div align="center">$fail.nama_daerah</div></td>
    <td class="$row"><div align="center">$fail.amaun</div></td>
    <td class="$row"><div align="center">$fail.no_resit</div></td>
    <td class="$row"><div align="center">$fail.no_rujbayaran</div></td>
    <td class="$row"><div align="center">$fail.tkh_bayaran</div></td>
    <td class="$row"><div align="center">
    	<!--<input name="cmdEdit" value="Edit" id="cmdEdit" type="button" onClick="javascript:EditBayaran('$fail.id_bayarancukai','$fail.idBaucer')">-->
		<a href="javascript:EditBayaran('$fail.id_bayarancukai','$fail.idBaucer')"><img src="../img/edit.gif" border="0" style="display:none"></a>
        <a href="javascript:delete_carabayar('$fail.id_bayarancukai')"><img src="../img/delete.gif" border="0" style="display:none"></a>
  	</div></td>
  
  </tr>
	    #end
        #if ($count == 0)
  <tr> 
   	<td colspan="7" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  </tr>
       	#end    
  <tr>
    <td colspan="5" align="center"><input class="stylobutton" type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="KemaskiniBayaran()" style="display:none">
      <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="SimpanBayaran()" style="display:none">
      <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" $btnname onClick="BatalBayaran()" style="display:none">
      <input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliBayaran()">
      <input class="stylobutton" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" style="display:none">
      <input class="stylobutton" type="button" name="cmdPapar" id="cmdPapar" value="Papar Surat" style="display:none">
      <input name="cmdCetak" value="Cetak" id="cmdCetak" class="stylobutton" type="button" onClick="javascript:CetakBayaran('$tahun_cukai','$idNegeri')">
    </td>
  </tr>
      </table>
    <input type="hidden" name="idPermohonan" value="$idPermohonan">
  	<input type="hidden" name="idNegeri" value="$idNegeri">
    <input type="hidden" name="idBayaranCukai" >
    <input type="hidden" name="idBaucer" >
  	<input type="hidden" name="negeri" value="$negeri">
  	<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
  	<input type="hidden" name="peringkat_bayaran" value="$peringkat_bayaran" >
  	<input type="hidden" name="idPeringkatbayaran" value="$idPeringkatbayaran">
  	<input type="hidden" name="command1" value="$command1">  
  	<input type="hidden" name="pagemode" value="$pagemode">
  	<input type="hidden" name="style1" value="$Style1">
  	<input type="hidden" name="style2" value="$Style2">
 	<input type="hidden" name="socbayaran" value="$peringkat_bayaran" >

        <!--</form>-->
        
</fieldset>
<script type="text/javascript">

function TambahBayaran() {
	document.${formName}.action = "";
	document.${formName}.command.value = "tambahBayaran";
	document.${formName}.pagemode.value = "baruBay";
	document.${formName}.submit();
}
function EditBayaran(idBayaranCukai,idBaucer) {
	document.${formName}.action = "";
	document.${formName}.command.value = "tambahBayaran";
	document.${formName}.pagemode.value = "viewBay";
	document.${formName}.idBayaranCukai.value = idBayaranCukai;
	document.${formName}.idBaucer.value = idBaucer;
	document.${formName}.submit();
}
function SimpanBayaran(){	
	document.${formName}.pagemode.value = "simpanBayaran";
	document.${formName}.command.value = "cukaiBayaran";
	document.${formName}.action = "";
	document.${formName}.submit(); 
}
function KembaliBayaran() {
	document.${formName}.action = "";
	document.${formName}.command.value = "cukaiperingkatbayar";
	document.${formName}.pagemode.value = "baucerview";
	document.${formName}.submit();
}

//rosli 2010/04/19
function CetakBayaran(tahun,idnegeri){
	var url = "../servlet/ekptg.report.htp.cukai.LaporanCukai?TAHUN="+tahun+"&ID_NEGERI="+idnegeri+"&template=HTPCukaiMemoSenaraiBaucer";
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
//document.${formName}.cmdSimpanPenyata.style.display = document.${formName}.style2.value;
//document.${formName}.cmdKemaskiniPenyata.style.display = document.${formName}.style1.value;
</script>