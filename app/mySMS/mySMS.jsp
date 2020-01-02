<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<p>
<a href="#" class="style1" onClick="LihatHistory()">Lihat <em>History</em></a></p>
<fieldset><legend>mySMS</legend>
<table width="100%" border="0">
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
  <tr>
    <td width="29%"><div align="right">Tempoh</div></td>
    <td width="1%">:</td>
    <td>
    Dari
      <input name="txdTarikhDari" type="text" id="txdTarikhDari" value="$!tarikhDari"> 
      <a href="javascript:displayDatePicker('txdTarikhDari',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>   
    Hingga 
    <input name="txdTarikhHingga" type="text" id="txdTarikhHingga" value="$!tarikhHingga">
    <a href="javascript:displayDatePicker('txdTarikhHingga',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>   <span class="style52">(dd/mm/yyyy)</span>
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
      <input type="button" name="cmdSemak" id="cmdSemak" value="Semak" onClick="semak()">
    </label></td>
  </tr>
</table>
</fieldset>
<p>
#if($jenisSMS != "")
	#if($jenisSMS == "1")
	<fieldset id="pendaftaran"><legend>Pendaftaran</legend>
	#parse("app/utils/record_paging.jsp") 

		<table width="100%" border="0">
		  <tr class="table_header">
		    <td width="3%"><strong>No.</strong></td>
		    <td width="40%"><strong>No. Fail</strong></td>
		    <td width="40%"><strong>No. KP Simati</strong></td>
		    <td width="17%"><strong>Status Fail</strong></td>
		  </tr>
		#foreach ($daftar in $PermohonanList)
		  	#set( $i = $velocityCount )
			#if ( ($i % 2) != 1 )
				#set( $row = "row2" )
			#else
				#set( $row = "row1" )
			#end
		  <tr class="$row">
		    <td class="$row">$daftar.bil.</td>
		    <td class="$row">$daftar.NO_RUJSEKSYEN</td>
		    <td class="$row">$daftar.NO_KP_SIMATI</td>
		    <td class="$row">$daftar.STATUS_FAIL</td>
		  </tr>
		#end
		</table>
		<input name="cmdExport1" type="button" value="Export" onclick="exportData()">
		#if($action == "exportData")
			<!-- Sila klik <a href="../mySMS/$foldername/$filename" class="style1">$filename</a> untuk simpan fail. -->
			Sila klik <a href="../download?file=$pathSMS$foldername/$filename" class="style1">$filename</a> untuk simpan fail.
		
		#end
	</fieldset>
	#end

	#if($jenisSMS == "2")
	<fieldset id="notis"><legend>Perbicaraan</legend>
	
		#parse("app/utils/record_paging.jsp") 
		<table width="100%" border="0">
		  <tr class="table_header">
		    <td width="3%"><strong>No.</strong></td>
		    <td width="17%"><strong>No. Fail</strong></td>
		    <td width="10%"><strong>Tarikh Notis</strong></td>
		    <td width="10%"><strong>Tarikh Bicara</strong></td>
		    <td width="10%"><strong>Masa Bicara</strong></td>
		    <td width="50%"><strong>Tempat Bicara</strong></td>
		  </tr>
		  #foreach ($bicara in $PermohonanList)
			#set( $i = $velocityCount )
			#if ( ($i % 2) != 1 )
				#set( $row = "row2" )
			#else
				#set( $row = "row1" )
			#end
		  <tr class="$row">
		    <td  class="$row">$bicara.bil.</td>
		    <td  class="$row">$bicara.NO_RUJSEKSYEN</td>
		    <td  class="$row">$bicara.TARIKH_NOTIS</td>
		    <td  class="$row">$bicara.TARIKH_BICARA</td>
		    <td  class="$row">$bicara.MASA_BICARA</td>
		    <td  class="$row">$bicara.TEMPAT_BICARA</td>
		   
		  </tr>
		  #end
		</table>
		<input name="cmdExport2" type="button" value="Export" onClick="exportData()">
		#if($action == "exportData")
		<!-- Sila klik <a href="../mySMS/$foldername/$filename" class="style1">$filename</a> untuk simpan fail. -->
			Sila klik <a href="../download?file=$pathSMS$foldername/$filename" class="style1">$filename</a> untuk simpan fail.
		#end
	</fieldset>
	#end
	
	#if($jenisSMS == "3")
	<fieldset id="perintah"><legend>Perintah</legend>
	
		#parse("app/utils/record_paging.jsp") 
		<table width="100%" border="0">
		  <tr class="table_header">
		    <td width="3%"><strong>No.</strong></td>
		   	<td width="70%"><strong>No. Fail</strong></td>
		    <td width="10%"><strong>Tarikh Selesai</strong></td>
		    <td width="17%"><strong>Status Fail</strong></td>
		  </tr>
		   #foreach ($perintah in $PermohonanList)
			#set( $i = $velocityCount )
			#if ( ($i % 2) != 1 )
				#set( $row = "row2" )
			#else
				#set( $row = "row1" )
			#end		  
		  <tr class="$row">
		    <td class="$row">$perintah.bil.</td>
		    <td class="$row">$perintah.NO_RUJSEKSYEN</td>
		    <td class="$row">$perintah.TARIKH_SELESAI</td>
		    <td class="$row">$perintah.STATUS_FAIL</td>
		  </tr>
		  #end
		</table>
		<input name="cmdExport3" type="button" value="Export" onClick="exportData()">
		#if($action == "exportData")
		<!-- Sila klik <a href="../mySMS/$foldername/$filename" class="style1">$filename</a> untuk simpan fail. -->
			Sila klik <a href="../download?file=$pathSMS$foldername/$filename" class="style1">$filename</a> untuk simpan fail.		
		#end
	</fieldset>
	#end


#end
</fieldset>

#if($action == "exportData")
<br />
&nbsp;Sila Klik <a href="http://www.mysms.gov.my/v3/" target="_blank"><font color="blue"><i>http://www.mysms.gov.my/v3/</i></font></a> untuk memasukkan data ke Gerbang SMS Kerajaan Malaysia. 
#end

<script>
	function semak() {	
		if(document.${formName}.txdTarikhDari.value == ""
			&& document.${formName}.txdTarikhHingga.value == ""){
			alert("Sila Pilih Tempoh Dari/ Hingga Terlebih Dahulu");
			return;
			
		}
		document.${formName}.action = "?_portal_module=ekptg.view.sms.mySMS&action=";
		document.${formName}.submit();
		
		
	} 
	function LihatHistory(){	
		document.${formName}.action = "?_portal_module=ekptg.view.sms.mySMS&action=LihatHistory";
		document.${formName}.submit();
	}
	function exportData() {
		document.${formName}.action = "?_portal_module=ekptg.view.sms.mySMS&action=exportData";
		document.${formName}.submit();		
	}
</script>