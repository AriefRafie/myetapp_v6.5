<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
        <legend><b>CARIAN</b></legend>
        <table width="100%" align="center" border="0">
          <tr>
            <td width="30%" height="24" scope="row" align="right">No Fail : </td>
            <td width="70%"><input name="findNoFail" id="findNoFail" type="text" value="$!findNoFail" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
            <td width="30%" height="24" scope="row" align="right">Nama Projek : </td>
            <td width="70%"><input name="findNamaProjek" id="findNamaProjek" type="text" value="$!findNamaProjek" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
            <td width="30%" height="24" scope="row" align="right">No EFT / No Cek : </td>
            <td width="70%"><input name="findNoCekEFT" id="findNoCekEFT" type="text" value="$!findNoCekEFT" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <!-- <tr>
            <td width="30%" height="24" scope="row" align="right">Kaedah Pembayaran : </td>
            <td width="70%"><input name="findKaedahBayar" id="findKaedahBayar" type="text" value="$!findKaedahBayar" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <tr>
            <td width="30%" height="24" scope="row" align="right">Kaedah Pembayaran : </td>
            <td valign="top">
            	<select name="findKaedahBayar" style="auto">
      			<option value = "" >SILA PILIH</option>
      			<option value="1">Cek</option>
      			<option value="2">EFT</option>
				  #foreach ( $bayar in $cara_bayar )
				  #set ( $selected_doc = "" )
				  #if($findKaedahBayar==$bayar.CARA_BAYAR)
				  #set ( $selected_doc = "selected" )
				  #end
				  <option $selected_doc value="$bayar.CARA_BAYAR" >$bayar.CARA_BAYAR</option>
				  #end
				</select></td>
          </tr>  -->         
          <tr>
          	<td>&nbsp</td>
          	<td>
        		<input type="button" name="cari" id="cari" value="Cari" onclick="javascript:carian();" />
  				<input type="button" name="kosongkan" id="kosongkan" value ="Kosongkan" onClick="javascript:findKosongkan();"/>
  				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembali();"/>
            </td>
        </tr>
        </table>
      </fieldset></td>
  </tr>
</table>
