#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())
<form id="f1" name="f1" method="post" action="">
<input name="idAduan" type="hidden" value="">
<input name="command" type="hidden" value="">
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
     <a href="javascript:displayDatePicker('txdTarikhAduan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  <tr>
    <td align="right">Status Aduan</td>
    <td>:</td>
    <td><label>
      <select name="socStatusAduan" id="socStatusAduan">
      #if($socStatusAduan == 0)
        <option selected="selected">Sila Pilih Status Aduan</option>
        <option value="1">DALAM PROSES</option>
        <option value="2">TELAH DIAMBIL TINDAKAN</option>
      #elseif($socStatusAduan == 1)
        <option>Sila Pilih Status Aduan</option>
        <option value="1" selected="selected">DALAM PROSES</option>
        <option value="2">TELAH DIAMBIL TINDAKAN</option>
      #else
      	<option>Sila Pilih Status Aduan</option>
        <option value="1">DALAM PROSES</option>
        <option value="2" selected="selected">TELAH DIAMBIL TINDAKAN</option>
      #end
      </select>
      
    </label></td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input type="submit" name="cmdCari" id="cmdCari" value="Cari" />
    </label>
      <label>
      <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" />
      </label></td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend><strong>Senarai Aduan</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td colspan="3"><strong>
      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onClick="tambah()" />
    </strong></td>
    <td colspan="3" align="right">
      #if ( $i >= $Senarai.size())
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" onclick="sebelumnya()" align="right" />
      #else
      <input type="button" name="cmdPrevious" id="cmdPrevious" value=" &lt; Sebelumnya" disabled="disabled" align="right" />
      #end
 	  #if (($i < $total && $Senarai.size() != $total))
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" onclick="seterusnya()" align="right" />
      #else
      <input type="button" name="cmdNext" id="cmdNext" value="Seterusnya &gt;" disabled="disabled" align="right" />
      #end
    
    </td>
    </tr>
  <tr class="table_header">
    <td width="1%">No</td>
    <td width="10%">No Aduan</td>
    <td width="20%">Jenis Aduan</td>
    <td width="10%">Tarikh Aduan</td>
    <td width="10%">Status Aduan</td>
    <td width="30%">Tindakan Susulan</td>
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
    #if ($aduan.bil != '')
    <td><a href="javascript:edit_item('$aduan.id_Aduan')" class="style1">$aduan.no_Aduan_Online</a></td>
    #else
    <td>$aduan.no_Aduan_Online</td>
    #end
    <td>$aduan.jenis_Aduan</td>
    <td>$aduan.tarikh_Aduan</td>
    <td>$aduan.status_Aduan</td>
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
</form>
<script>
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
function edit_item(id){
	document.f1.command.value = "tambahAduan";
	document.f1.action = "";
	document.f1.idAduan.value = id;
	document.f1.submit();
}
function carian(){
	document.f1.action.value = "";
	document.f1.submit();
}
function kosongkan(){
	document.f1.reset();
	document.f1.txtNoAduan.value = "";
	document.f1.socJenisAduan.value = "";
	document.f1.txdTarikhAduan.value = "";
	document.f1.socStatusAduan.value = "";

}
function tambah(){    	
	document.f1.command.value = "tambahAduan";
	document.f1.action = "";
	document.f1.submit();
}
</script>