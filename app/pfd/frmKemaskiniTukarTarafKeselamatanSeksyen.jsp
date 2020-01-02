<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {
	font-size: 9px;
	font-style: italic;
}
.style42 {color: #0000FF}
.style43 {color: #000000}
.style46 {font-size: 9px; font-style: italic; color: #FF0000; }
-->
</style>

<input type="hidden" name="action1" id="action1" />
<input type="hidden" name="idFailBaru" id="idFailBaru" value="$idFailBaru"/>
<input type="hidden" name="idFailAsal" id="idFailAsal" value="$idFailAsal"/>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<input name="idUrusan" id="idUrusan" type="hidden" value="$!idUrusan"/>
<input name="idSuburusan" id="idSuburusan" type="hidden" value="$!idSuburusan"/>
<input name="idSubsuburusan" id="idSubsuburusan" type="hidden" value="$!idSubsuburusan"/>
<input name="idSubsubsuburusan" id="idSubsubsuburusan" type="hidden" value="$!idSubsubsuburusan"/>
<input name="idTaraf" id="idTaraf" type="hidden" value="$!idTaraf"/>
<input name="idAktiviti" id="idAktiviti" type="hidden" value="$!idAktiviti"/>
<input name="noTurutan" id="noTurutan" type="hidden" value="$!noTurutan"/>
<fieldset>
<legend>MAKLUMAT FAIL</legend>
                         #if($tukar == 'success')
  	<div class="success">Penukaran status fail telah berjaya.</div>
 			#end
       				 <table width="100%">
          <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row">&nbsp;</td>
          <td valign="top" scope="row"><div align="center"></div></td>
          <td>&nbsp;</td>
        </tr>
      #if($mode == 'kemaskini' || $mode == 'papar' || $mode == 'paparSemua')
        <tr>
          <td class="style40" scope="row">&nbsp;</td>
          <td scope="row"><span class="style43">No Fail Baru</span></td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td><label><span class="style42">$noFailAsal.toUpperCase() </span> </label></td>
        </tr>
        <tr>
          <td class="style40" scope="row">&nbsp;</td>
          <td scope="row"><span class="style43">No Fail Asal </span></td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td><span class="style42">
            <label>$noFailBaru.toUpperCase()</label>
            </span> </td>
        </tr>
       #else
        <tr>
          <td class="style40" scope="row">&nbsp;</td>
          <td scope="row"><span class="style43">No Fail Asal</span></td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td><label><span class="style42">$noFailAsal.toUpperCase() </span> </label></td>
        </tr>
        #end
        <tr>
          <td width="2%" align="left" valign="top" class="style40" scope="row">&nbsp;</td>
          <td width="27%" align="left" valign="top" scope="row"><div align="left" class="style43">Tajuk Fail</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td width="70%">$tajukFailB
            </td>
        </tr>
        <tr>
          <td class="style40" scope="row">&nbsp;</td>
          <td scope="row"><div align="left" class="style43">Seksyen</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
            $!seksyen          </td>
        </tr>
        <tr>
          <td class="style40" scope="row">&nbsp;</td>
          <td scope="row"><div align="left" class="style43">Urusan</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
            $!namaUrusan</td>
        </tr>
       
           
            <tr>
              <td scope="row">&nbsp;</td>
              <td scope="row"><div align="left">Sub Urusan</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>
               $!namaSuburusan</td>
            </tr>
           
          
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row">Sub-Sub Urusan</td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$!namaSubSuburusan </td>
        </tr>
        
         <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row">Sub Sub-Sub Urusan</td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$!namaSubSubSuburusan </td>
        </tr>
         
         
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Taraf Keselamatan</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
          #if($mode != 'papar') $!selectTarafB #else $!taraf #end</td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Status Fail</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>
            $!status</td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Lokasi Simpanan Fail</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$!lokasi</td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Kabinet</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$!kabinetB</td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td scope="row"><div align="left">Tarikh Daftar Fail</div></td>
          <td width="1%" align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$!tarikhDaftarB</td>
        </tr>
        <tr>
          <td class="style2" scope="row">&nbsp;</td>
          <td class="style2" scope="row"><div align="left"></div></td>
          <td valign="top" scope="row"><div align="center"></div></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" class="style2" scope="row">&nbsp;</td>
          </tr>
              <tr>    </tr>
    <tr>
    <td colspan="4" align="center"> #if($mode == 'baru')
 	    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan('$idFailAsal')"/>
 	    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
 	    
 	    #elseif($mode == 'papar')
 	    
    
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
 	    
 	    
 	    #elseif($mode == 'kemaskini')
 	    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="update1('$!idFail')"/>
 	    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
 	    #else
 	    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>

 	    #end 	 </td>
  </tr>
      </table>    
</fieldset>
<script>

function cetak(idFail) {
        var url = "../servlet/ekptg.report.pfd.PFDFail?reportType=PDF&KULITFAIL_ID="+idFail;
        var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();

}

function kembali(){

	document.${formName}.action ="?_portal_module=ekptg.view.pfd.TukarTarafKeselamatan&action1=";
	document.${formName}.submit();
}
function simpan(id){
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.TukarTarafKeselamatan&action1=simpan&idFailAsal="+id;
	document.${formName}.submit();
}

</script>
