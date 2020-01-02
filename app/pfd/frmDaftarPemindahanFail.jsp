<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
-->
</style>
<form name="f1" method="post">

<table width="100%">
  <tr>
    <td><fieldset>
  <legend>
  CARIAN   </legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" scope="row"><div align="right" class="style2">No Fail</div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td width="70%"><label>
        <input name="txtNoFail" type="text" id="txtNoFail" onkeyup="this.value=this.value.toUpperCase()" value="$txtNoFail" size="55" />
        </label>
        <input type="hidden" name="idFail" id="idFail" />
      <input type="hidden" name="command" id="command" /></td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style2">Tajuk Fail</div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td><label>
        <textarea name="txtTajukFail" cols="54" onkeyup="this.value=this.value.toUpperCase()" id="txtTajukFail">$txtTajukFail</textarea>
        </label>      </td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style2">Negeri</div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td> $selectNegeriD   </td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style2">Seksyen</div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td><label></label> 
      $selectSeksyenD     </td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style2">Status</div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td><label></label> 
      $selectStatusD     </td>
    </tr>
    <tr>
      <td scope="row"><div align="right" class="style2">Tarikh Daftar</div></td>
      <td width="1%" scope="row"><div align="right">:</div></td>
      <td><label>
        <input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" size="10"  />
        </label>
        <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      </td>
    </tr>
    <tr>
      <td scope="row"><label></label>      </td>
      <td width="1%" scope="row">&nbsp;</td>
      <td><label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()" />
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label>      </td>
    </tr>
  </table>
  </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
  <legend>SENARAI FAIL</legend>
  <table width="100%" align="center" >
    <tr class="table_header">
      <td width="3%" scope="row">NO</td>
      <td width="15%">NEGERI</td>
      <td width="10">SEKSYEN</td>
      <td width="17%">NO FAIL</td>
      <td width="20%">TAJUK FAIL</td>
      <td width="10">TARIKH DAFTAR FAIL</td>
      <td width="10%">STATUS FAIL</td>
      <td width="15"> STATUS PERGERAKAN FAIL</td>
    </tr>
    #foreach ($fail in $Senaraifail)

    <tr>
      <td>$fail.bil</td>
      <td>$fail.nama_Negeri</td>
      <td>$fail.kod_Seksyen</td>
      <td><a href="javascript:edit_item('$fail.id_Fail')" class="style1">$fail.no_Fail</a></td>
      <td>$fail.tajuk_Fail</td>
      <td>$fail.tarikh_Daftar_Fail</td>
      <td>$fail.keterangan1</td>
      <td>$fail.keterangan2</td>
    </tr>
    #end
  </table>
  </fieldset></td>
  </tr>
</table>



  
  
  <p>&nbsp;</p>
</form>
<script>
function edit_item(id){
	document.f1.command.value = "step1";
	document.f1.action = "";
	document.f1.idFail.value = id;
	document.f1.submit();

}
function carian(){
	document.f1.action.value = "";
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
<script language="JavaScript"> document.forms[0].txtNoFail.focus(); </script>