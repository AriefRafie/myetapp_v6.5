<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<p>
<a href="#" class="style1" onClick="LihatHistory()">Lihat <em>History</em></a></p>
<fieldset><legend>Pilihan</legend>
<table width="100%" border="0">
	<!--
  <tr>
    <td width="29%"><div align="right">Jenis SMS</div></td>
    <td width="1%">:</td>
    <td><select name="socJenisSMS" id="socJenisSMS">
     #if($jenisSMS == "0")
      <option value="0" selected>Sila Pilih</option>
      <option value="1">Pendaftaran</option>
      <option value="2">Perbicaraan</option>
      <option value="3">Perintah</option>
      #elseif ($jenisSMS == "1")
      <option value="0">Sila Pilih</option>
      <option value="1" selected>Pendaftaran</option>
      <option value="2">Perbicaraan</option>
      <option value="3">Perintah</option>
      #elseif ($jenisSMS == "2")
      <option value="0">Sila Pilih</option>
      <option value="1">Pendaftaran</option>
      <option value="2" selected="selected">Perbicaraan</option>
      <option value="3">Perintah</option>
      #elseif ($jenisSMS == "3")
      <option value="0">Sila Pilih</option>
      <option value="1">Pendaftaran</option>
      <option value="2">Perbicaraan</option>
      <option value="3" selected>Perintah</option>
      #else
      <option value="0" selected>Sila Pilih</option>
      <option value="1">Pendaftaran</option>
      <option value="2">Perbicaraan</option>
      <option value="3">Perintah</option>
      #end
      
    
    </select>
    </td>
  </tr>
  -->
  <tr>
    <td width="29%"><div align="right">Tempoh</div></td>
    <td width="1%">:</td>
    <td>
      <input name="txdTarikhDari" type="text" id="txdTarikhDari" value="$!tarikhDari"> 
      <a href="javascript:displayDatePicker('txdTarikhDari',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>   
    hingga 
    <input name="txdTarikhHingga" type="text" id="txdTarikhHingga" value="$!tarikhHingga">
    <a href="javascript:displayDatePicker('txdTarikhHingga',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>   <span class="style52">dd/mm/yyyy</span>
    </td>
  </tr>
  <tr>
    <td width="29%"><div align="right"></div></td>
    <td width="1%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="29%"><div align="right"></div></td>
    <td width="1%">&nbsp;</td>
    <td><label>
      <input type="submit" name="cmdSemak" id="cmdSemak" value="Semak" onClick="semak()">
    </label></td>
  </tr>
</table>
</fieldset>
<p>

<fieldset id="pendaftaran"><legend>Senarai Hakmilik/Rizab</legend>


#parse("app/utils/record_paging.jsp") 
<table width="100%" border="0">
  <tr class="table_header">
    <td><strong>Bil.</strong></td>
    <td><strong>Negeri</strong></td>
    <td><strong>Daerah</strong></td>
    <td><strong>Mukim</strong></td>
    <td><strong>No. Hakmilik</strong></td>
    <td><strong>No. Warta</strong></td>
    <td><strong>No. Lot</strong></td>
  </tr>
	#set ($count = 0)
	#foreach ($daftar in $PermohonanList)
  
  ##if ($bil == '') 
  	##set ($row = 'row1')
  ##elseif ($bil % 2 != 0)
  	##set ($row = 'row1')
  ##else 
  	##set ($row = 'row2')
  ##end 

	#set ($count = $count+1)
  	#set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
       #set( $row = "row2" )
    #else
       #set( $row = "row1" )
    #end 
  
  
  <tr>
    <td class="$row">$!daftar.getBil().</td>
    <td class="$row">$!daftar.getNegeri().getNamaNegeri()</td>
    <td class="$row">$!daftar.getDaerah().getNamaDaerah()</td>
    <td class="$row">$!daftar.getMukim().getNamaMukim()</td>
    <td class="$row">$!daftar.getRujJenisHakmilik().getKodJenisHakmilik() $!daftar.getNoHakmlik()</td>
    <td class="$row">$!daftar.getNoWarta()</td>
    <td class="$row">$!daftar.getNoLot()</td>
  </tr>
  #end
</table>
<input name="cmdExport1" type="button" value="Export (Hakmilik)" onclick="exportData()">
<input name="cmdExport2" type="button" value="Export (Agensi)" onclick="exportAgensi()">
#if($action == "exportData")
Sila klik <a href="../app/integrasi/$foldername/$filename" class="style1">$filename</a> untuk simpan fail.
#end
</fieldset>


</fieldset>
<script>
	function semak() {
		doAjaxCall${formName}("");		
	}
	
	function LihatHistory(){
		doAjaxCall${formName}("LihatHistory");
	}

	function exportData() {
		doAjaxCall${formName}("exportData");
				
	}
	
	function exportAgensi() {
		doAjaxCall${formName}("exportAgensi");				
	}
</script>