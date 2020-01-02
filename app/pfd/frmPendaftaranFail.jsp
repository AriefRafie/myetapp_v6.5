<style type="text/css">
<!--
.style2 {font-size: 10px}
.style40 {color: #FF0000}
.style6 {
	font-size: 9px;
	font-style: italic;
}
.style42 {color: #000000}
-->
</style>
<div id=content>

<input name="mode" type="hidden" value="$mode" />
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
<input name="action" type="hidden" id="action" value="$action"/>
&nbsp;
<table width="100%" border="1" cellspacing="0">
  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" onClick="javascript:setSelected(0);" tabindex="1">Fail Lama</li>
    <li class="TabbedPanelsTab" onClick="javascript:setSelected(1);" tabindex="1">Fail Baru</li>
    <li class="TabbedPanelsTab" onClick="javascript:setSelected(1);" tabindex="1">Fail Arkib</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent" style="$display1">
      <fieldset>
       <legend>MAKLUMAT FAIL LAMA</legend>
       <table width="100%">
        <tr>
          <td width="2%" scope="row"><div align="center" class="style40">*</div></td>
          <td width="27%" scope="row"><div align="left" class="style42">No Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td width="70%"><input name="txtNoFail" type="text" id="txtNoFail" value="$!noFail" size="44" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly/>
</td>
        </tr>
        <tr>
          <td scope="row"><div align="center"><span class="style40">*</span></div></td>
          <td scope="row"><div align="left" class="style42">Negeri</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
            $selectNegeriA          </td>
        </tr>
        <tr>
          <td scope="row"><div align="center"><span class="style40">*</span></div></td>
          <td scope="row"><div align="left" class="style42">Seksyen</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
            $selectSeksyenA          </td>
        </tr>
        <tr>
          <td scope="row"><div align="center"><span class="style40">*</span></div></td>
          <td scope="row"><div align="left" class="style42">Urusan</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
            $selectUrusanA          </td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Sub Urusan</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
           $selectSubUrusanA                  </td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Taraf Keselamatan</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
           $selectTarafA          </td>
        </tr>
        <tr>
          <td scope="row" valign="top"><div align="center" class="style40">*</div></td>
          <td scope="row" valign="top"><div align="left">Tajuk Fail</div></td>
          <td width="1%" valign="top" scope="row"><div align="right">:</div></td>
          <td>
              <label>
              <textarea  name="txtTajukFailA" cols="41" id="txtTajukFailA" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly>$tajukFailA</textarea>
              </label>         </td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Status Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
           $selectStatusA         </td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Lokasi Simpanan Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td><textarea name="txtLokasiFailA" cols="41" id="txtLokasiFailA" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly>$!lokasiFailA</textarea></td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Faharasat</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td><input name="txtFaharasatA" type="text" id="txtFaharasatA" value = "$!faharasatA" size="25" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly/> </td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Tarikh Daftar Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td><input name="txtTarikhDaftarA" type="text" id="txtTarikhDaftarA" value = "$!tarikhDaftarA" size="10"/> <a href="javascript:displayDatePicker('txtTarikhDaftarA',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row"><div align="left"></div></td>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label bertanda <span class="style40">*</span>  diisi.</span></td>
          </tr>
      </table>
      </fieldset>
    </div>
    <div class="TabbedPanelsContent" style="$display2">
    <fieldset>
          <legend>MAKLUMAT FAIL BARU</legend>
          <table width="100%">
        <tr>
          <td width="2%" class="style40" scope="row"><div align="center">*</div></td>
          <td width="27%" scope="row"><div align="left" class="style42">Negeri</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td width="70%">
           $selectNegeriB         </td>
        </tr>
        <tr>
          <td class="style40" scope="row"><div align="center">*</div></td>
          <td scope="row"><div align="left" class="style42">Seksyen</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
            $selectSeksyenB          </td>
        </tr>
        <tr>
          <td class="style40" scope="row"><div align="center">*</div></td>
          <td scope="row"><div align="left" class="style42">Urusan</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
            $selectUrusanB          </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Sub Urusan</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
           $selectSubUrusanB          </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Taraf Keselamatan</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
           $selectTarafB          </td>
        </tr>
        <tr>
          <td valign="top" class="style2" scope="row"><div align="center" class="style40">*</div></td>
          <td valign="top" scope="row"><div align="left">Tajuk Fail</div></td>
          <td width="1%" valign="top" scope="row"><div align="right">:</div></td>
          <td>
              <label>
              <textarea name="txtTajukFailB" cols="41" id="txtTajukFailB" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly>$tajukFailB</textarea>
              </label>          </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Status Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
            $selectStatusB         </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Lokasi Simpanan Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td><textarea name="txtLokasiFailB" cols="41" id="txtLokasiFailB" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly>$!lokasiFailB</textarea></td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Faharasat</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td><input name="txtFaharasatB" type="text" id="txtFaharasatB" value = "$!faharasatB" size="25" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly /> </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Tarikh Daftar Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td><input name="txtTarikhDaftarB" type="text" id="txtTarikhDaftarB" value = "$!tarikhDaftarB" size="10"/> <a href="javascript:displayDatePicker('txtTarikhDaftarB',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td class="style2" scope="row"><div align="left"></div></td>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" class="style2" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label bertanda <span class="style40">*</span> wajib diisi.</span></td>
          </tr>
      </table>    
    </fieldset> 
    </div>
     <div class="TabbedPanelsContent" style="$display3">
      <legend>MAKLUMAT FAIL BARU</legend>
          <table width="100%">
        <tr>
          <td width="2%" class="style40" scope="row"><div align="center">*</div></td>
          <td width="27%" scope="row"><div align="left" class="style42">Negeri</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td width="70%">
           $selectNegeriB         </td>
        </tr>
        <tr>
          <td class="style40" scope="row"><div align="center">*</div></td>
          <td scope="row"><div align="left" class="style42">Seksyen</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
            $selectSeksyenB          </td>
        </tr>
        <tr>
          <td class="style40" scope="row"><div align="center">*</div></td>
          <td scope="row"><div align="left" class="style42">Urusan</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
            $selectUrusanB          </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Sub Urusan</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
           $selectSubUrusanB          </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Taraf Keselamatan</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
           $selectTarafB          </td>
        </tr>
        <tr>
          <td valign="top" class="style2" scope="row"><div align="center" class="style40">*</div></td>
          <td valign="top" scope="row"><div align="left">Tajuk Fail</div></td>
          <td width="1%" valign="top" scope="row"><div align="right">:</div></td>
          <td>
              <label>
              <textarea name="txtTajukFailB" cols="41" id="txtTajukFailB" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly>$tajukFailB</textarea>
              </label>          </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Status Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td>
            $selectStatusB         </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Lokasi Simpanan Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td><textarea name="txtLokasiFailB" cols="41" id="txtLokasiFailB" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly>$!lokasiFailB</textarea></td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Faharasat</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td><input name="txtFaharasatB" type="text" id="txtFaharasatB" value = "$!faharasatB" size="25" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly /> </td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Tarikh Daftar Fail</div></td>
          <td width="1%" scope="row"><div align="right">:</div></td>
          <td><input name="txtTarikhDaftarB" type="text" id="txtTarikhDaftarB" value = "$!tarikhDaftarB" size="10"/> <a href="javascript:displayDatePicker('txtTarikhDaftarB',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td class="style2" scope="row"><div align="left"></div></td>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" class="style2" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label bertanda <span class="style40">*</span> wajib diisi.</span></td>
          </tr>
      </table>    
     </div>
  </div>
</div>
    </td>
  </tr>
  <tr align="center">
    <td>  
 	#if($mode == 'baru')
     <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
     <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
     <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>

    #elseif($mode == 'papar')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="cetak('$!idFail')" />
    

    #elseif($mode == 'kemaskini')
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="update()"/>
     <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
   	#else
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
    #end
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-02</strong></td>
  </tr>
</table>
</div>

<script type="text/javascript">

function batal(){
	
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtTajukFailA.value = "";
	document.${formName}.socNegeriA.value = "";
	document.${formName}.socSeksyenA.value = "";
	document.${formName}.socUrusanA.value = "";
	document.${formName}.socSuburusanA.value = "";
	document.${formName}.socTarafA.value = "1";
	document.${formName}.socStatusA.value = "7";
	document.${formName}.txtLokasiFailA.value = "";
	document.${formName}.txtFaharasatA.value = "";
	document.${formName}.socNegeriB.value = "";
	document.${formName}.socSeksyenB.value = "";
	document.${formName}.socUrusanB.value = "";
	document.${formName}.socSuburusanB.value = "";
	document.${formName}.socTarafB.value = "1";
	document.${formName}.socStatusB.value = "7";
	document.${formName}.txtLokasiFailB.value = "";
	document.${formName}.txtFaharasatB.value = "";
	document.${formName}.txtTajukFailB.value = "";
	return;
	
}
function cetak(idFail) {
		var url = "../servlet/ekptg.report.pfd.NoFailTajukFail?reportType=PDF&idfail="+idfail;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

}
function kembali(){

	document.${formName}.action ="?_portal_module=ekptg.view.pfd.PendaftaranFail&action=SenaraiFail";
	document.${formName}.submit();
}
function simpan(){
	
	// fail lama
	if(document.${formName}.tabId.value == '0'){

		var ADate = document.${formName}.txtTarikhDaftarA.value;
	
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth();
		curr_month++
		var curr_year = d.getFullYear();

   		var today = curr_date +'/' + curr_month +'/' + curr_year;

		if( ADate > today)
		{
			alert('Sila pastikan " Tarikh Daftar Fail " tidak melebihi tarikh hari ini.');
			document.${formName}.txtTarikhDaftarA.focus();
			return false;
		}
	
		if(document.${formName}.txtNoFail.value == ""){
			alert('Sila masukkan " No Fail " terlebih dahulu.');
			document.${formName}.txtNoFail.focus(); 
			return;
		}
		
		if (document.${formName}.socNegeriA.value == ""){
				alert('Sila masukkan " Negeri " terlebih dahulu.');
				document.${formName}.socNegeriA.focus();
				return;
		}
		
		if (document.${formName}.socSeksyenA.value == ""){
				alert('Sila masukkan " Seksyen " terlebih dahulu.');
				document.${formName}.socSeksyenA.focus();
				return;
		} 
		
		if (document.${formName}.socUrusanA.value == ""){
				alert('Sila masukkan " Urusan " terlebih dahulu.');
				document.${formName}.socUrusanA.focus();
				return;
		}
		
		if (document.${formName}.txtTajukFailA.value == ""){
				alert('Sila masukkan " Tajuk Fail " terlebih dahulu.');
				document.${formName}.txtTajukFailA.focus();
				return;
		}
		
		if (document.${formName}.txtLokasiFailA.value == ""){
				alert('Sila masukkan " Lokasi Fail " terlebih dahulu.');
				document.${formName}.txtLokasiFailA.focus();
				return;
		}
		

	}
	else{	
		// fail baru
		var BDate = document.${formName}.txtTarikhDaftarB.value;
		
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth();
		curr_month++
		var curr_year = d.getFullYear();
	
		var today = curr_date +'/' + curr_month +'/' + curr_year;
	
		if( BDate > today)
		{
			alert('Sila pastikan " Tarikh Daftar Fail " tidak melebihi tarikh hari ini.');
			document.${formName}.txtTarikhDaftarB.focus();
			return false;
		}
		
		if (document.${formName}.socNegeriB.value == ""){
				alert('Sila masukkan " Negeri " terlebih dahulu.');
				document.${formName}.socNegeriB.focus();
				return;
		}
		if (document.${formName}.socSeksyenB.value == ""){
				alert('Sila masukkan " Seksyen " terlebih dahulu.');
				document.${formName}.socSeksyenB.focus();
				return;
		}
		if (document.${formName}.socUrusanB.value == ""){
				alert('Sila masukkan " Urusan " terlebih dahulu.');
				document.${formName}.socUrusanB.focus();
				return;
		}  
		
		if (document.${formName}.txtTajukFailB.value == ""){
				alert('Sila masukkan " Tajuk Fail " terlebih dahulu.');
				document.${formName}.txtTajukFailB.focus();
				return;
		}
		
		if (document.${formName}.txtLokasiFailB.value == ""){
				alert('Sila masukkan " Lokasi Fail " terlebih dahulu.');
				document.${formName}.txtLokasiFailB.focus();
				return;
		}
		

	}	

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action=simpan";
	document.${formName}.submit();
}

function kemaskini(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action=kemaskini";
	document.${formName}.submit();

}

function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;
	
}

function doChangeUrusanA() {
 	doAjaxCall${formName}("doChangeUrusanA");
}
function doChangeUrusanB() {
 	doAjaxCall${formName}("doChangeUrusanB");
}

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});


//-->
</script>
