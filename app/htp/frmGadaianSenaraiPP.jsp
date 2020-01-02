<strong> Senarai Fail PP </strong>
<br><br>
<fieldset> <legend>Carian</legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
    <!--
      <tr>
        <td height="25" align="right"><strong>No. Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NoFail" type="text" size="55" maxlength="400" value="" onkeyup="this.value=this.value.toUpperCase();" /></td>
      </tr>
      -->
      <tr>
        <td height="25" align="right"><strong>No. Fail PP : &nbsp;&nbsp;</strong></td>
        <td><input name="nofailpp" type="text" size="55" maxlength="300" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      <tr>
        <td></td>
        <td><input name="cari" value="Cari" type="button" onclick="javascript:search_data()">
        <input value="Kosongkan" onclick="javascript:kosong()" type="button" /></td>
      </tr>
    </tbody>
  </table>
</fieldset>

<fieldset>
<legend>Senarai Fail</legend>
<table border="0" cellpadding="2" cellspacing="1" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="5%"><b>No</b></td>
        <td width="22%"><b>No Fail PP</b></td>
        <td width="33%"><b>Nama Peminjam</b></td>
        <td width="25%"><b>Nama Tetuan</b></td>
        <td width="15%"><b>Tarikh Surat</b></td>
    </tr>
      #set ($count = 0)
      #foreach ( $fail in $SenaraiPP )
      #set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row">$fail.bil</td>
        <td class="$row">$fail.noPP</a></td>
        <td class="$row">$fail.peminjam</td>
        <td class="$row">$fail.peguam</td>
        <td class="$row">$fail.tarikhPermohonan</td>
    </tr>    	

               <tr>
                 <td class="$row">&nbsp;</td>
                 <td class="$row">&nbsp;</td>
                 <td class="$row">$fail.nolot, $fail.daerah, $fail.mukim, $fail.negeri                 </td>
                 <td class="$row">&nbsp;</td>
                 <td class="$row">$fail.tarikhsuratKJP</td>
               </tr>
      #end
      #if ($count == 0)
      <tr>
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
    </tbody>
  </table>

 
  
</fieldset>

<!-- start script -->
<script language="javascript" type="text/javascript">

function search_data(){
	doAjaxCall${formName}('Search');
}

function kosong(){
	document.${formName}.reset();
	document.${formName}.nofailpp.value = "";
	document.${formName}.nofailpp.focus();
}
</script>

<!-- end script -->





