

<style type="text/css">
<!--
.style2 {font-size: 10px}
.style1 {color: #0000FF}
.style40 {color: #FF0000}
.style6 {
	font-size: 9px;
	font-style: italic;
}
.style42 {color: #000000}
.style43 {
	font-size: 10px;
	color: #FF0000;
	font-style: italic;
}
-->
</style>
<div id=content>

<!--input name="mode" type="hidden" value="" /-->
<input type="hidden" name="idMesyuarat" value="$!idMesyuarat" />
<input type="hidden" name="idMesyuaratR" id="idMesyuaratR" value="$!idMesyuaratR" />
<input type="hidden" name="idMesyuaratA" id="idMesyuaratA" value="$!idMesyuaratA" />
<input type="hidden" name="OidMesyuarat" value="$!OidMesyuarat" />
<input type="hidden" name="tabId"  id="tabId" value="$selectedTab"/>
<input type="hidden" name="socKategoriMsyrt"  id="socKategoriMsyrt" value="$selectedTab"/>
<input type="hidden" name="kategori_Mesyuarat" value="$kategori_Mesyuarat" />
<input type="hidden" name="kategori_MesyuaratSrc" value="$kategori_MesyuaratSrc" />
<input type="hidden" name="action" value="$action" />

&nbsp;

			  <fieldset>
		<legend>CARIAN</legend>
			<table width="100%">
            <tr>
            <td width="29%" align="right" scope="row" valign="top"><div align="right">Jenis Mesyuarat</div></td>
            <td width="1%" align="right" scope="row" valign="top"><div align="right" class="style2">:</div></td>
    <td>
      <label>
        <select name="socKategoriMsyrtSrc" id="socKategoriMsyrtSrc">
        #if ($kategori_MesyuaratSrc == "1")
          <option>-Sila Pilih-</option>
          <option value="1" selected="selected">AD-HOC</option>
          <option value="2">RUTIN</option>
        
        #elseif ($kategori_MesyuaratSrc == "2")
          <option>-Sila Pilih-</option>
          <option value="1">AD-HOC</option>
          <option value="2" selected="selected">RUTIN</option>
        #elseif ($kategori_MesyuaratSrc == "0")
          <option selected="selected">-Sila Pilih-</option>
          <option value="1">AD-HOC</option>
          <option value="2">RUTIN</option>
         #end
        </select>
        </label>       </td>
  </tr>
			  <tr>
			    <td width="29%" align="right" scope="row" valign="top"><div align="right">Tajuk Mesyuarat</div></td>
			    <td width="1%" align="right" scope="row" valign="top"><div align="right" class="style2">:</div></td>
			    <td width="70%">
			      <label>
        			<textarea name="txtTajukMsyrt" cols="41" id="txtTajukMsyrt" onblur="this.value=this.value.toUpperCase()">$txtTajukMsyrt</textarea>
        		  </label>                </td>
  			</tr>
            
  			<tr>
    			<td align="right" scope="row"><div align="right">Urusetia / Seksyen</div></td>
    			<td width="1%" scope="row"><div align="right" class="style2">:</div></td>
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
<!--    			<input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
--> 
<input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick=" onclick="javascript:doAjaxCall${formname}('','actionPage=kosongkan')"" />
                </label></td>
  			</tr>
		</table>

	</fieldset>
					<fieldset>
		<legend>SENARAI MESYUARAT</legend>

			<table width="100%" >

  			<tr align="left">
    				<td colspan="4" scope="row">&nbsp;</td>
    				<td colspan="4" align="right">&nbsp;</td>
  			</tr>
			<tr>
            <td colspan="8"> #parse("app/utils/record_paging7.jsp")</td>
            </tr>
  			<tr align="left">
    				<td colspan="4" scope="row"><!--input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="tambah()"/-->
      				<label>
      				<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Senarai" />
     	 			</label></td>
    				<td colspan="4" align="right">&nbsp;</td>
  			</tr>
  			<tr class="table_header">
   				<td width="1%" scope="row">NO</td>
    				<td width="5%">URUSETIA / SEKSYEN</td>
    				#if ($kategori_MesyuaratSrc==2)
                    <td width="2%">BIL MESYUARAT SEMASA</td>
                    <td width="25%">TAJUK MESYUARAT</td>
    				<td width="20%">LOKASI</td>
                    <td width="5%">TAMBAH MESYUARAT</td>
					#else
                    <td width="2%">BIL MESYUARAT</td>
                    <td width="20%">TAJUK MESYUARAT</td>
    				<td width="5%">TARIKH MESYUARAT</td>
					<td width="10%">LOKASI</td>
					<td width="5%">MASA</td>
    				<td width="9%">STATUS MESYUARAT</td>
    				<td width="1%"></td>
                    #end  			</tr>
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
    				<td class="$row"> $listMesyuarat.kod_Seksyen</td>
    #else
    				<td class="$row"> $listMesyuarat.kod_Seksyen</td>
	#end
			    <td class="$row"> $listMesyuarat.bil_Mesyuarat</td>
				    <td class="$row"><!--a href="javascript:edit_item('$listMesyuarat.id_Mesyuarat','$listMesyuarat.kategori_Mesyuarat')" class="style1">$listMesyuarat.tajuk_Mesyuarat</a--><a href="javascript:papar_mesyuarat('$listMesyuarat.id_Mesyuarat','$listMesyuarat.kategori_Mesyuarat')" class="style1">$listMesyuarat.tajuk_Mesyuarat</a></td>
    				#if ($listMesyuarat.kategori_Mesyuarat ==2 || $kategori_MesyuaratSrc ==2)
                    	
                        #if ($kategori_MesyuaratSrc !=2)
           	  <td class="$row"></td>
                        #end    

                    #else
				    <td class="$row"> $listMesyuarat.tarikh_Mesyuarat</td>
                    #end
                    
				    <td class="$row"> $listMesyuarat.lokasi</td>
                    #if ($listMesyuarat.kategori_Mesyuarat ==2 || $kategori_MesyuaratSrc ==2)
                        #if ($kategori_MesyuaratSrc !=2)
                   	<td class="$row"></td>
                    <td class="$row"></td>
                        #end    
                    #else
    				<td class="$row"> $listMesyuarat.masa_Mesyuarat_Dari</td>                   
    				<td class="$row"> $listMesyuarat.keterangan</td>
                    #end
                    #if ($listMesyuarat.kategori_Mesyuarat =='2')
                    <td class="$row">#if ($listMesyuarat.id_Mesyuarat!="")
                      <input type="button" name="cmdDaftarTambah" id="cmdDaftarTambah" value="Tambah" onclick = "DaftarTambah('$listMesyuarat.id_Mesyuarat')" />
                      #end</td>
              #else
                    <td class="$row">#if ($listMesyuarat.id_Mesyuarat!="")<input type="button" name="cmdViewMinit" id="cmdViewMinit" value="Minit" onclick = "ViewMinit('$listMesyuarat.id_Mesyuarat','0')" />#end</td>
                    #end  			</tr>
  #end
		</table>
                <table width="100%" >
 		 <tr>
  		  <td align="right"><strong>CL-05-02</strong></td>
 		 </tr>
		</table>
	</fieldset>
</div>

<input type="hidden" id="selectedTab" name="selectedTab" value="$selectedTab" />
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<script type="text/javascript">

function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;
	document.${formName}.idMesyuarat.value=0;
	
	if (tabId == 1){
	document.${formName}.socKategoriMsyrt.value = 1;
	}else{
	document.${formName}.socKategoriMsyrt.value = 0;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action="
	document.${formName}.submit();
	
}
function carian(){
	
	document.${formName}.action= "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=carian";
	document.${formName}.submit();
}
function tambah(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=tambah";
	document.${formName}.submit();
	
}

function ViewMinit(id,tabID){
	//alert(id);


	document.${formName}.ID_MESYUARAT.value = id;
	document.${formName}.selectedTab.value = tabID;	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMinitMesyuarat&action=agenda"
//	document.${formName}.action.value = 'agenda';	
	document.${formName}.submit();
}

function DaftarTambah(id){
	//alert(id);
	document.${formName}.tabId.value = 1;
	document.${formName}.OidMesyuarat.value = id;
	document.${formName}.idMesyuarat.value = id;
	document.${formName}.idMesyuaratA.value = id;

		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuaratRutin&action=daftarTambah"
		document.${formName}.submit();
}
function papar_mesyuarat(id,kmesyuarat){
	document.${formName}.idMesyuarat.value = id;
	document.${formName}.action= "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=papar_maklumat";
	document.${formName}.submit();
}

function edit_item(id,kategoriMesyuarat){

	
	document.${formName}.kategori_Mesyuarat.value = kategoriMesyuarat;

	document.${formName}.idMesyuarat.value = id;

	if(kategoriMesyuarat == "1"){
		document.${formName}.tabId.value = 1;
		document.${formName}.idMesyuaratA.value = id;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuaratRutin&action=papar"
		document.${formName}.submit();
	}else if(kategoriMesyuarat == "2"){
		document.${formName}.idMesyuaratR.value = id;
		document.${formName}.tabId.value = 0;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuaratRutin&action=papar"
		document.${formName}.submit();
		
	}else{
	alert("Error Kategori Mesyuarat =' " + kategoriMesyuarat + "'. Sila hubungi Admin");
	}

}
function kosongkan(){
	//alert("kosong");
	document.${formName}.reset();
	document.${formName}.kategori_MesyuaratSrc = "0";
    document.${formName}.socKategoriMsyrtSrc.value = "0";
	document.${formName}.kategori_MesyuaratSrc.value = "0";
	
	document.${formName}.txtTajukMsyrt.value = "";
//	document.${formName}.txtTajukMsyrt1.value = "";
	document.${formName}.txdTarikhMsyrt.value = "";
	document.${formName}.socSeksyen.value = "";
	document.${formName}.socLokasi.value = "";
	document.${formName}.submit();
		//alert("kosong");
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

function batal(){

	document.${formName}.tabId.value = "0";
	document.${formName}.selectedTab = "0";
	

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=";
	document.${formName}.submit();
}
function kembali(){

	document.${formName}.tabId.value = "0";
	document.${formName}.selectedTab = "0";
	

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=";
	document.${formName}.submit();

//	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=SenaraiMesyuarat";
//	document.${formName}.submit();
}
function kemaskini(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=kemaskini";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function simpan(){
	 
	var resulttxtDariJam = document.${formName}.txtDariJam.value.length;
	var resulttxtHinggaJam = document.${formName}.txtHinggaJam.value.length;
	
	if (document.${formName}.txtBilMsyrt.value == ""){
		alert('Sila masukkan " Bil Mesyuarat " terlebih dahulu.');
		document.${formName}.txtBilMsyrt.focus();
		return;
	} 
	if (document.${formName}.txtTajukMsyrt1.value == ""){
		alert('Sila masukkan " Tajuk Mesyuarat " terlebih dahulu.');
		document.${formName}.txtTajukMsyrt1.focus();
		return;
	}
		if (document.${formName}.txdTarikh.value == ""){
		alert('Sila masukkan " Tarikh Mesyuarat " terlebih dahulu.');
		document.${formName}.txdTarikh.focus();
		return;
	}
		
		if (document.${formName}.txtDariJam.value == ""){
		alert('Sila masukkan " Waktu Mesyuarat " terlebih dahulu.');
		document.${formName}.txtDariJam.focus();
		return;
		}
		if (resulttxtDariJam != 4 || resulttxtHinggaJam != 4){
		alert(' Sila patuhi Format jam, 4 angka. Contoh "0900"');
		document.${formName}.txtDariJam.focus();
		return;
		}

		if (document.${formName}.socWaktuDariJam.value == "0"){
		alert('Sila Pilih " Dari Jam " terlebih dahulu.');
		document.${formName}.socWaktuDariJam.focus();
		return;	
	}
		if (document.${formName}.txtHinggaJam.value == ""){
		alert('Sila masukkan " Waktu Mesyuara " terlebih dahulu.');
		document.${formName}.txtHinggaJam.focus();
		return;
	}
		if (document.${formName}.socWaktuHinggaJam.value == "0"){
		alert('Sila Pilih " Hingga Jam " terlebih dahulu.');
		document.${formName}.socWaktuHinggaJam.focus();
		return;	
	}
			if (document.${formName}.socLokasi1.value == ""){
		alert('Sila pilih " Lokasi " terlebih dahulu.');
		document.${formName}.socLokasi1.focus();
		return;
	}			if (document.${formName}.socStatus.value == ""){
		alert('Sila pilih " Status Mesyuarat " terlebih dahulu.');
		document.${formName}.socStatus.focus();
		return;
	}
		
		if (document.${formName}.socSeksyen1.value == ""){
		alert('Sila pilih " Urusetia / Seksyen " terlebih dahulu.');
		document.${formName}.socSeksyen1.focus();
		return;
	}
			if (document.${formName}.socPegawai.value == ""){
		alert('Sila pilih " Nama Pegawai " terlebih dahulu.');
		document.${formName}.socPegawai.focus();
		return;
	}
	
		if (document.${formName}.idMesyuarat.value == "" || document.${formName}.idMesyuarat.value == 0){
		//alert(document.${formName}.kategori_MesyuaratSrc.value);
		
			if (document.${formName}.mode.value == "simpandaftarTambah"){
			//alert("victory 1");
				document.${formName}.mode.value = "simpandaftarTambah";		
			}else {
			//alert("victory 2");
				document.${formName}.mode.value = "tambahBaru";
			}
	}

	else{
		
		document.${formName}.mode.value = "kemaskiniMesyuarat";
		//alert('kemaskiniMesyuarat');
	}
	
	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=simpan";
	

	document.${formName}.submit();
}
function tambahAhli(id,command){
	document.${formName}.idMesyuarat.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=tambahAhli";

	document.${formName}.submit();
}
//function tambahAhli(id,command){
//
//	
//	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAhliMesyuarat?idMesyuarat="+id+"&command="+command;
//    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
//      if ((document.window != null) && (!hWnd.opener))
//        hWnd.opener = document.window;
//      if (hWnd.focus != null) hWnd.focus();
//
//}
function papar_ahli(id,command){

	
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAhliMesyuarat?ahliMesyuarat="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}

function tambahAgenda(id,command){

	
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAgendaMesyuarat?idMesyuarat="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
function papar_agenda(id,command){

	
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniAgendaMesyuarat?agendaMsyrt="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=210, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}

function refresh() {
alert('refresh');
}



</script>
