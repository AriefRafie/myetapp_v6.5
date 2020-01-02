  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
<fieldset> <legend>Maklumat Carian</legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
        <td align="right" width="40%">No. Fail : &nbsp;&nbsp;</td>
        <td><input name="NoFail" type="text" size="43" maxlength="43" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();"></td>
	</tr>
	<tr>
        <td></td>
        <td>
        	<input class="stylobutton" name="cari" value="Cari" type="button" onclick="javascript:search_data()">
        	<input class="stylobutton" value="Kosongkan" onclick="javascript:cancel()" type="button">
     	</td>
      </tr>
  </table>
</fieldset>
<fieldset>
<legend>Senarai Fail</legend>
	<table border="0" cellpadding="2" cellspacing="1" width="100%">
       <tr class="table_header">
        <td width="3%" ><b>No</b></td>
        <td width="15%"><b>No Fail</b></td>
        <td width="50%"><b>Nama</b></td>
        <td width="20%"><b>Negeri</b></td>
        <td width="13%"><b>Status</b></td>
      </tr>	
      #set ($count = 0)
      #foreach ( $fail in $Senaraifail )
      	#set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row" scope="row">$count</td>
        <td class="$row"><a href="javascript:senaraiSurat('$fail.idpermohonan', '$fail.no')" class="style1">$fail.no</a></td>
        <td class="$row">$fail.tajuk</td>
        <td class="$row">$fail.negeri</td>
        <td class="$row">$fail.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr> 
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
      <input type="hidden" name="idpermohonan">
  <input type="hidden" name="idFail" value="$fail.idFail">
   <input type="hidden" name="mode">
  </table>

</fieldset>

<script>

function cancel() {
	document.${formName}.reset();
}

function search_data(){
	//document.${formName}.field.value='a';
	doAjaxCall${formName}("carian");	
}

function senaraiSurat(id, no) {
	document.${formName}.idpermohonan.value = id;
	document.${formName}.NoFail.value = no;
	doAjaxCall${formName}("surat");	

}

function showWindow(servlet){
          var url = "../x/${securityToken}/"+servlet;
          var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
          if ((document.window != null) && (!hWnd.opener))
            hWnd.opener = document.window;
          if (hWnd.focus != null) hWnd.focus();
}
</script>