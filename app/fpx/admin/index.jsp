<table WIDTH="100%">
<tr>
    <td><fieldset>
      <legend><b>Carian</b></legend>

      <table width="60%">
      	<tr>
      		<td>
      			MyID / MYcoID
      		</td>
      		<td>
      			<input type="text" name="nokp" value="$!nokp" >
      		</td>
      	</tr>
      	<tr>
      		<td>
      			Nama
      		</td>
      		<td>
      			<input type="text" name="nama" value="$!nama" >
      		</td>
      	</tr>
      	
      	<tr>
        	<td>&nbsp;</td>
        	<td>
            	<input name="cari" value="Cari" type="button" onclick="javascript:search()" />
  				<input type=button value = "Kosongkan" onClick="javascript:kembali();">
            </td>
        </tr>
        
      </table>
    </fieldset>
</td>
</tr>
<tr>
    <td><fieldset>
      <legend><b>SENARAI TRANSAKSI FPX</b></legend>
       #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="6" scope="row"></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>No. Transaksi FPX</strong></td>
           <td width="15%"><strong>Tarikh</strong></td>
          <td width="15%"><strong>No Fail</strong></td>
           <td width="30%"><strong>Nama</strong></td>
          <td width="10%"><strong>MyID / MYcoID No.</strong></td>
           <td width="5%"><strong>Amaun</strong></td>
          <td width="10%" align="center"><strong>Status</strong></td>
        </tr>
        #set ($count = 0)
      #foreach ( $fail in $SenaraiFail )
      #set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
        <tr>
          <td class="$row" align="center">$!count</td>
          <td class="$row"><a href="javascript:detail('$fail.noTransaksi')" class="style1">$fail.noTransaksi</a></td>
           <td class="$row">$!fail.tarikhMasuk</td>
          <td class="$row">$!fail.noFail</td>
            
          <td class="$row">$!fail.nama</td>
          <td class="$row">$!fail.noKp</td>
           <td class="$row">$nf.format($!fail.amount)</td>
          <td class="$row" align="center">
          #if($fail.status=='SUCCESSFUL')
          	<i>SUCCESSFUL</i>
          #else
          		<i>UNSUCCESSFUL</i>
          #end
          </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
 
</table>

<fieldset>
<legend>Transaksi Hari Ini</legend>
  	<table width="30%">
  		<tr>
  			<td>Jumlah Transaksi Hari ini :</td>
  			<td>$!analatic.getTotalTransaction()</td>
  		</tr>
  		<tr>
  			<td>Jumlah Transaksi Berjaya :</td>
  			<td>$!analatic.totalSuccess</td>
  		</tr>
  		 <tr>
  			<td>Jumlah Transaksi Gagal :</td>
  			<td>$!analatic.totalFail</td>
  		</tr>
  		<tr>
  			<td>Jumlah Kutipan (RM) :</td>
  			<td>$nf.format($!analatic.totalColletion)</td>
  		</tr>
  		
  	</table>
  	</fieldset>
<script>
function search(){
	var nokp = document.${formName}.nokp.value;
	var nama = document.${formName}.nama.value;	
	doAjaxCall${formName}("search","nokp="+nokp+"&nama="+nama);
}
function detail(id){
	doAjaxCall${formName}("view","noTransaksi="+id);
}
function kembali(){
	doAjaxCall${formName}("main");
}
function cetakResit(id,nilai) {
	
 	var url = "../servlet/ekptg.report.online.SalinanResitBayaran?id_fpx="+id+"&nilai="+nilai;
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

</script>