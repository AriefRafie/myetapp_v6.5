<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())

  <input name="idAduan" type="hidden" value="">
  <input name="action" type="hidden" value="$action">
  &nbsp;
  <fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%" border="0" cellpadding="2">
    <tr>
      <td width="29%" align="right">No Aduan</td>
      <td width="1%">:</td>
      <td width="70%"><label>
        <input name="txtNoAduan" type="text" id="txtNoAduan" value="$txtNoAduan" />
      </label></td>
    </tr>
    <tr>
      <td align="right">Jenis Aduan</td>
      <td>:</td>
      <td>$selectJenisAduan</td>
    </tr>
    <tr>
      <td align="right">Tarikh Aduan</td>
      <td>:</td>
      <td><label>
        <input name="txdTarikhAduan" type="text" id="txdTarikhAduan" value="$txdTarikhAduan" />
        </label>
        <a href="javascript:displayDatePicker('txdTarikhAduan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </td>
    </tr>
    <!--<tr>
      <td align="right">Status Aduan</td>
      <td>:</td>
      <td><label>
        $selectStatus
      </label></td>
    </tr>-->
    <tr>
      <td align="right">&nbsp;</td>
      <td>&nbsp;</td>
      <td><label>
        <input type="submit" name="cmdCari" id="cmdCari" value="Cari" />
        </label>
          <label>
          <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label></td>
    </tr>
  </table>
  </fieldset>
  <fieldset>
  <legend><strong>Senarai Aduan</strong></legend>
  <table width="100%" border="0" cellpadding="2">
    <tr>
      <td colspan="3"><strong>
        <input type="button" name="cmdTambah" id="cmdTambah" value="Aduan Baru" onClick="aduanBaru()" />
      </strong></td>
      <td colspan="3" align="right"> #if ( $i >= $Senarai.size())
        <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
        #else
        <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" disabled="disabled" align="right" />
        #end
        #if (($i < $total && $Senarai.size() != $total))
        <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
        #else
        <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" disabled="disabled" align="right" />
        #end </td>
    </tr>
    <tr class="table_header">
      <td width="1%">No</td>
      <td width="10%">No Aduan</td>
      <td width="20%">Jenis Aduan</td>
      <td width="10%">Tarikh Aduan</td>
      <td width="30%">Jawapan</td>
    </tr>
    #foreach ($aduan in $Senarai)
    #if ($aduan.bil == '') 
    #set ($row = 'row1')
    #elseif ($aduan.bil % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end
    <tr>
      <td>$aduan.bil</td>
      <td>$aduan.no_Aduan_Online</td>
      <td>$aduan.jenis_Aduan</td>
      <td>$aduan.tarikh_Aduan</td>
      <td>$aduan.tindakan_Susulan</td>
    </tr>
    #end
  </table>
  </fieldset>
  <table width="100%" border="0" cellpadding="2">
    <tr>
      <td align="right"><strong>CL-08-03</strong></td>
    </tr>
  </table>

<script>
function seterusnya(){    	
	document.${formName}.action.value = "next";
	document.${formName}.submit();
}
function sebelumnya(){    	
	document.${formName}.action.value = "previous";
	document.${formName}.submit();
}
function carian(){
	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.txtNoAduan.value = "";
	document.${formName}.socJenisAduan.value = "";
	document.${formName}.txdTarikhAduan.value = "";
	document.${formName}.socStatusAduan.value = "";

}
function aduanBaru(){    	
	document.${formName}.action.value = "aduanBaru";
	document.${formName}.submit();
}
function edit_item(id){
	document.${formName}.action.value = "editAduan";
	document.${formName}.idAduan.value = id;
	document.${formName}.submit();
}
</script>
