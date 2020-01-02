<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
-->
</style>

<input name="mode" type="hidden" value="" />
<input name="idMesyuarat" type="hidden" value="" />
&nbsp;


<table width="100%">
  <tr>
    <td><fieldset>
<legend>CARIAN</legend>
<table width="100%">
  <tr>
    <td width="29%" align="right" scope="row" valign="top"><div align="right">Tajuk Mesyuarat</div></td>
    <td width="1%" align="right" scope="row" valign="top"><div align="right" class="style2">:</div></td>
    <td width="70%">
      <label>
        <textarea name="txtTajukMsyrt" cols="41" id="txtTajukMsyrt" onblur="this.value=this.value.toUpperCase()"></textarea>
      </label>       </td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Urusetia / Seksyen</div></td>
    <td align="right" scope="row"><div align="right" class="style2">:</div></td>
    <td>
      <label>$selectSeksyen</label>      </td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Lokasi</div></td>
    <td align="right" scope="row"><div align="right" class="style2">:</div></td>
    <td>
      <label>$selectLokasi</label>          </td>
  </tr>
  <tr>
    <td align="right" scope="row"><div align="right">Tarikh Mesyuarat</div></td>
    <td align="right" scope="row"><div align="right" class="style2">:</div></td>
    <td>
      <label>
        <input name="txtTarikhMsyrt" type="text" id="txtTarikhMsyrt" size="10" />
      </label>
              <a href="javascript:displayDatePicker('txtTarikhMsyrt',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>       </td>
  </tr>
  <tr>
    <td colspan="2" align="right" scope="row">&nbsp;</td>
    <td><label>
      <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
      <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
    </label></td>
  </tr>
</table>
</fieldset>
</td>
  </tr>
  <tr>
    <td><fieldset>
<legend>SENARAI MESYUARAT</legend>
<table width="100%" >
  <tr>
    <td colspan="4" scope="row">Jumlah Rekod : $!totalRecords</td>
    <td colspan="4" align="right">Papar
      <label>
      <select name="itemsPerPage" id="itemsPerPage" onchange="javascript:doAjaxCall${formname}('doChanges','action=doChangeItemPerPage')" class="smallselect">
        <option value="10" #if ($itemsPerPage == 10) selected="selected" #end>10</option>
        <option value="20" #if ($itemsPerPage == 20) selected="selected" #end>20</option>
        <option value="30" #if ($itemsPerPage == 30) selected="selected" #end>30</option>
        <option value="40" #if ($itemsPerPage == 40) selected="selected" #end>40</option>
        <option value="50" #if ($itemsPerPage == 50) selected="selected" #end>50</option>
      </select>
    </label></td>
  </tr>

  <tr >
    <td colspan="4" scope="row">#if ($SenaraiFail.size() == 0)
			#foreach ( $i in [1..$totalPages] )
				#if ($i == $page) <b>[$i]</b> #else <a href="javascript:doAjaxCall${formname}('doChanges','action=getPage&amp;value=$i')">[$i]</a> #end
			#end
    #end </td>
    <td colspan="4" align="right">Mukasurat $!page dari $!totalPages</td>
  </tr>
  
  <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="10%">URUSETIA / SEKSYEN</td>
    <td width="2%">BIL MESYUARAT</td>
    <td width="20%">TAJUK MESYUARAT</td>
    <td width="5%">TARIKH MESYUARAT</td>
    <td width="5%">LOKASI</td>
    <td width="5%">MASA</td>
    <td width="10%">STATUS MESYUARAT</td>
  </tr>
   #foreach ($mesyuarat in $Senarai)
   #if ($mesyuarat.bil == '') 
  	#set ($row = 'row1')
  #elseif ($mesyuarat.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$mesyuarat.bil</td>
    <td class="$row">$mesyuarat.kod_Seksyen</td>
     #if ($mesyuarat.bil != '') 
    <td class="$row"><a href="javascript:papar('$mesyuarat.id_Mesyuarat')" class="style1 style1">$mesyuarat.bil_Mesyuarat</a></td>
    #else
    <td class="$row">$mesyuarat.bil_Mesyuarat</td>
	#end
    <td class="$row">$Util.subString($mesyuarat.tajuk_Mesyuarat,0,10)</td>
    <td class="$row">$mesyuarat.tarikh_Mesyuarat</td>
    <td class="$row">$mesyuarat.lokasi</td>
    <td class="$row">$mesyuarat.masa_Mesyuarat_Dari</td>
    <td class="$row">$mesyuarat.keterangan</td>
  </tr>
  #end
</table>
</fieldset>
</td>
  </tr>
</table>



<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-22</strong></td>
  </tr>
</table>
<!--/form-->
<script>

function tambah(id){
	document.${formName}.action = "tambah";
	document.${formName}.idMesyuarat.value = id;
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function carian(){
	document.${formName}.submit();
}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.txtTajukMsyrt.value = "";
	document.${formName}.txdTarikhMsyrt.value = "";
	document.${formName}.socSeksyen.value = "";
	document.${formName}.socLokasi.value = "";
}

</script>