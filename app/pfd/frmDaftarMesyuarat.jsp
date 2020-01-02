<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
-->
</style>

<input name="mode" type="hidden" value="" />
<input name="idMesyuarat" type="hidden" value="" />
<input name="tabId"  id="tabId" value="$selectedTab"/>
&nbsp;
<table width="100%" border="1" cellspacing="0">
  <tr>
    <td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" onClick="javascript:setSelected(0);" tabindex="1">Fail Lama1</li>
    <li class="TabbedPanelsTab" onClick="javascript:setSelected(1);" tabindex="1">Tambah Mersyuarat</li>
  </ul>
  
    <div class="TabbedPanelsContentGroup">
		  <div class="TabbedPanelsContent" style="$display1">
          
          
          <table width="100%">
  <tr>
    <td> <fieldset>
		<legend>CARIAN</legend>
			<table width="100%">
			  <tr>
			    <td width="29%" align="right" scope="row" valign="top"><div align="right">Tajuk Mesyuarat</div></td>
			    <td width="1%" align="right" scope="row" valign="top"><div align="right" class="style2">:</div></td>
			    <td width="70%">
			      <label>
        			<textarea name="txtTajukMsyrt" cols="41" id="txtTajukMsyrt" onblur="this.value=this.value.toUpperCase()">$txtTajukMsyrt</textarea>
        		  </label>
                </td>
  			</tr>
  			<tr>
    			<td align="right" scope="row"><div align="right">Urusetia / Seksyen</div></td>
    			<td  scope="row"><div align="right" class="style2">:</div></td>
    			<td><label>$selectSeksyen</label>      </td>
  			</tr>
  			<tr>
    			<td align="right" scope="row"><div align="right">Lokasi</div></td>
    			<td align="right" scope="row"><div align="right" class="style2">:</div></td>
    			<td><label>$selectLokasi</label>          </td>
  			</tr>
  			<tr>
    			<td align="right" scope="row"><div align="right">Tarikh Mesyuarat</div></td>
    			<td width="1%" scope="row"><div align="right" class="style2">:</div></td>
    			<td><label>
 			       <input name="txdTarikhMsyrt" type="text" id="txdTarikhMsyrt" value="$txdTarikhMsyrt" size="10" />
        			</label>
   			    <a href="javascript:displayDatePicker('txdTarikhMsyrt',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>       </td>
  			</tr>
  			<tr>
    			<td colspan="2" align="right" scope="row">&nbsp;</td>
    			<td><label>
     				 <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
      				 <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
    			</label></td>
  			</tr>
		</table>
	</fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
		<legend>SENARAI MESYUARAT</legend>

			<table width="100%" >
  				<tr align="left">
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
      </label>
      				</td>
  			</tr>
  			<tr align="left">
    				<td colspan="4" scope="row">&nbsp;</td>
    				<td colspan="4" align="right">&nbsp;</td>
  			</tr>
  			<tr align="left">
   				<td colspan="4" scope="row">#if ($SenaraiFail.size() == 0)
					#foreach ( $i in [1..$totalPages] )
						#if ($i == $page) <b>[$i]</b> #else <a href="javascript:doAjaxCall${formname}('doChanges','action=getPage&amp;value=$i')">[$i]</a> #end
					#end
    		#end 
           		</td>
    				<td colspan="4" align="right">Mukasurat $!page dari $!totalPages</td>
  			</tr>
  			<tr align="left">
    				<td colspan="4" scope="row"><input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()"/>
      				<label>
      				<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Senarai" />
     	 			</label></td>
    				<td colspan="4" align="right">&nbsp;</td>
  			</tr>
  			<tr class="table_header">
   				<td width="1%" scope="row">NO</td>
    				<td width="10%">URUSETIA / SEKSYEN</td>
    				<td width="2%">BIL MESYUARAT</td>
    				<td width="20%">TAJUK MESYUARAT</td>
    				<td width="5%">TARIKH MESYUARAT</td>
    				<td width="5%">LOKASI</td>
    				<td width="5%">MASA</td>
    				<td width="10%">STATU MESYUARAT</td>
  			</tr>
  #foreach ($listMesyuarat in $SenaraiMesyuarat)
   #if ($listMesyuarat.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listMesyuarat.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  			<tr>
   				<td class="$row">$listMesyuarat.bil</td>
    #if ($listMesyuarat.bil != '') 
    				<td class="$row"><a href="javascript:edit_item('$listMesyuarat.id_Mesyuarat')" class="style1">$listMesyuarat.kod_Seksyen</a></td>
    #else
    				<td class="$row">$listMesyuarat.kod_Seksyen</td>
	#end
			    <td class="$row">$listMesyuarat.bil_Mesyuarat</td>
				    <td class="$row">$listMesyuarat.tajuk_Mesyuarat</td>
				    <td class="$row">$listMesyuarat.tarikh_Mesyuarat</td>
				    <td class="$row">$listMesyuarat.lokasi</td>
    				<td class="$row">$listMesyuarat.masa_Mesyuarat_Dari</td>
    				<td class="$row">$listMesyuarat.keterangan</td>
  			</tr>
  #end
		</table>
	</fieldset></td>
  </tr>
</table>

          
          
			 
					
					<!--/fieldset-->
    		</div>
          <div class="TabbedPanelsContent" style="$display2">
    			<fieldset>
          			<legend>MAKLUMAT FAIL BARU</legend>
                    <table><tr><td></td>shutto</tr></table>
    			</fieldset> 
    	   </div>
  	</div>
</div>

    </td>
  </tr>
</table>

<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-18</strong></td>
  </tr>
</table>

<script>

function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;
	
}
function carian(){
	document.${formName}.action.value = "";
	document.${formName}.submit();
}
function tambah(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=tambah";
	document.${formName}.submit();
	
}
function edit_item(id){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=papar";
	document.${formName}.idMesyuarat.value = id;
	document.${formName}.submit();

}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.txtTajukMsyrt.value = "";
	document.${formName}.txdTarikhMsyrt.value = "";
	document.${formName}.socSeksyen.value = "";
	document.${formName}.socLokasi.value = "";
}
function cetak(nofail,tajukfail,idnegeri,idseksyen,idstatus,tarikhdaftar) {
	
	
		
		var url = "../servlet/ekptg.report.pfd.SenaraiFail?reportType=PDF&nofail="+nofail+"&tajukfail="+tajukfail+"&idnegeri="+idnegeri+"&idseksyen="+idseksyen+"&idstatus="+idstatus+"&tarikhdaftar="+tarikhdaftar;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

}
function doChanges() {
	doAjaxCall${formName}("doChanges");
}
</script>