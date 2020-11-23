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
      <legend><b>SENARAI ADUAN</b></legend>
       #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="6" scope="row"></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%" align="center"><strong>No Aduan</strong></td>
          <td width="25%"><strong>Jenis Aduan</strong></td>
          <td width="20%"><strong>Status Aduan</strong></td>
          <td width="15%" align="center"><strong>Tarikh Aduan</strong></td>
          <td width="20%" align="center"><strong>Tarikh Dikemaskini</strong></td>
        </tr>
        #set ($count = 0)
      #foreach ( $fail in $lists )
      #set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
        <tr>
          <td class="$row" align="center">$!count</td>
          <td class="$row" align="center"><a href="javascript:detail('$fail.id')" class="style1">$fail.id</a></td>
          <td class="$row">$!fail.type.code - $!fail.type.description </td>
          <td class="$row">$!fail.statusPenyelesaian</td>
          <td class="$row" align="center">$!fail.tarikhAduan</td>
          <td class="$row" align="center">$!fail.tarikhKemaskini</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
  	<td>
  		 <input type="reset" value="Kembali ke Menu Utama" onclick="javascript:menuUtama()"/>
  	</td>
  </tr>
</table>
<script>
	function cariAduan(){
		doAjaxCall${formName}("viewComplaint");
	}

	function kembali(){
		doAjaxCall${formName}("kembali");
	}
	function detail(id){
   	   		doAjaxCall${formName}("viewComplaint","noAduan="+id);
   	}
	function menuUtama(){
		document.${formName}.action = "$EkptgUtil.getTabID("Menu",$myrole)?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
		document.${formName}.submit();
	}
</script>