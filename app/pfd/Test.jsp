<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())
<form action="" method="post" name="f1" enctype="multipart/form-data">

<input name="command" type="hidden" value="" />
<input name="idDokumen" type="hidden" value="1" />
<input name="nav" type="hidden" value="" />
 <label>
  <input type="file" name="txtLampiran" id="txtLampiran">
  </label>
  <label>
  <input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Upload()" >
  </label>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" scope="row" align="right">No Fail</td>
      <td width="1%" scope="row">:</td>
      <td width="70%"><label>
        <input name="txtNoFail" type="text" id="txtNoFail" onkeyup="this.value=this.value.toUpperCase()" value="$txtNoFail" size="55" />
        </label>
          <input type="hidden" name="idFail" />
          <input type="hidden" name="action" />
          <input type="hidden" name="mode" /></td>
    </tr>
    <tr>
      <td scope="row" align="right" valign="top">Tajuk Fail</td>
      <td width="1%" scope="row" valign="top">:</td>
      <td><label>
        <textarea name="txtTajukFail" cols="53" onkeyup="this.value=this.value.toUpperCase()" id="txtTajukFail">$txtTajukFail</textarea>
        </label>
      </td>
    </tr>
    <tr>
      <td scope="row" align="right">Negeri</td>
      <td width="1%" scope="row">:</td>
      <td> $selectNegeriD </td>
    </tr>
    <tr>
      <td scope="row" align="right">Seksyen</td>
      <td width="1%" scope="row">:</td>
      <td> $selectSeksyenD</td>
    </tr>
    <tr>
      <td scope="row" align="right">Status</td>
      <td width="1%" scope="row">:</td>
      <td> $selectStatusD</td>
    </tr>
    <tr>
      <td scope="row" align="right">Tarikh Daftar</td>
      <td width="1%" scope="row">:</td>
      <td><label>
        <input type="text" name="txdTarikhDaftar" id="txdTarikhDaftar" value="$txdTarikhDaftar" />
        </label>
        <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </td>
    </tr>
    <tr>
      <td colspan="2" scope="row"><label></label>
      </td>
      <td><label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label>
      </td>
    </tr>
  </table>
  <fieldset><legend>Senarai Fail</legend>
 <p>Jumlah Senarai : $total</p>
 <table width="100%" border="0">
    
    <tr align="right">
      <td colspan="4">
     
      #if ( $i >= $Senarai.size())
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" />
      #else
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumya" disabled="disabled"  />
      #end
 	  #if (($i < $total && $Senarai.size() != $total))
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" />
      #else
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" disabled="disabled" />
      #end      </td>
    </tr>
    <tr>
      <td width="1%">No</td>
      <td width="20%">No Fail</td>
      <td width="20%">Tajuk Fail</td>
      <td width="20%">Status Fail</td>
    </tr>
   #foreach ($fail in $Senarai)
   
    <tr>
      <td>$fail.bil</td>
      <td>$fail.no_Fail</td>
      <td>$fail.tajuk_Fail</td>
      <td>$fail.keterangan1</td>
    </tr>
   #end
  </table>
</fieldset>

</form>
<script language="JavaScript">
function Upload(){
	alert(document.f1.txtLampiran.value);
	document.f1.command.value = "simpan";
	document.f1.action = "";
	document.f1.submit();

}
function seterusnya(){    	
	document.f1.command.value = "next";
	document.f1.action = "";
	document.f1.submit();
}
function sebelumnya(){    	
	document.f1.command.value = "previous";
	document.f1.action = "";
	document.f1.submit();
}
function carian(){
	document.f1.command.value = "";
	document.f1.submit();
}
function kosongkan(){
	document.f1.reset();
	document.f1.txtNoFail.value = "";
	document.f1.txtTajukFail.value = "";
	document.f1.socNegeriD.value = "";
	document.f1.socSeksyenD.value = "";
	document.f1.socStatusD.value = "";
	document.f1.txdTarikhDaftar.value = "";
	
}
</script>


