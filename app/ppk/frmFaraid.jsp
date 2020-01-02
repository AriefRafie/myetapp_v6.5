#if ($wait == "true")
<script>
window.onload = function() {
  generateFaraid();
}
function generateFaraid()  { 
	doAjaxCall${formName}('generate');
}
</script>

#else

<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>
<!--#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())-->
<input name="action" type="hidden" value="$action">
<input name="idPermohonan" type="hidden" value="$idPermohonan" />
<input name="bhgnBaitulMal" type="hidden" value="$bhgnBaitulMal" />
<input name="idSimati" type="hidden" value="$idSimati" />
  <table width="100%" border="0">
    <tr>
      <td><fieldset>
      <legend>MAKLUMAT SIMATI</legend>
      
      <table width="100%">
      
        <tr>
          <td width="30%"><div align="right" class="style1">
            <div align="right">Nama</div>
          </div></td>
          <td width="1%"><div align="center" class="style1">
            <div align="right">:</div>
          </div></td>
          <td width="69%">
          $namaSiMati.toUpperCase()
          </td>
        </tr>
        <tr>
          <td><div align="right" class="style1">
            <div align="right">No KP</div>
          </div></td>
          <td><div align="center" class="style1">
            <div align="right">:</div>
          </div></td>
          <td>$!noKp</td>
        </tr>
        <tr>
          <td><div align="right" class="style1">
            <div align="right">Tarikh Mati</div>
          </div></td>
          <td><div align="center" class="style1">
            <div align="right">:</div>
          </div></td>
          <td>$!tarikhMati</td>
        </tr>
      </table>
      </fieldset>
      </td>
    </tr>
    <tr>
      <td><fieldset>
      <legend>MAKLUMAT PEWARIS</legend>
      <table width="100%" border="0">
        <tr class="table_header">
           <td width="10%"><div align="center">NO</div></td>
           <td width="30%"><div align="center">NAMA</div></td>
           <td width="30%"><div align="center">HUBUNGAN</div></td>
           <td width="30%"><div align="center">BAHAGIAN</div></td>
        </tr>
       #set ( $cnt = 0 )
       #foreach ($waris in $Senarai)
  	  
  	  #if ($waris.getBahagianAtas() == 0)
  	  ## prepare on how to skip this data
  	  #end
  	  
	  #set ( $cnt = $cnt + 1 )
	  #set( $i = $velocityCount )
	  #if ( ($i % 2) == 0 )
	      #set( $row = "row2" )
	  #else
	      #set( $row = "row1" )
	  #end
	  
	  #if ($waris.getNama().toUpperCase() == "BAITULMAL")
	  #set ($bhgnBaitulMal = $waris.getBahagianAtas())
	  #end 

        <tr>
           <td width="10%" class="$row">$cnt</td>
           <td width="30%" class="$row">$waris.getNama()</td>
           <td width="30%" class="$row">$waris.getHubungan()</td>
           <td width="30%" class="$row">$waris.getBahagian()</td>
        </tr>
        #end 
      </table>
      </fieldset>
      </td>
    </tr>
    <tr>
      <td>
      <table width="100%" border="0">
        <tr>
          <td><label>
            <div align="center">
              <input type="button" name="button" id="button" 
              value="Cetak" onclick="javascript:cetak('$idSimati','$idPermohonan','$!bhgnBaitulMal','$!idPermohonanSimati');return true;" />
            </div>
          </label></td>
        </tr>
      </table></td>
    </tr>
  </table>

      
      <a href="#" onclick="Effect.toggle('toggle_appear', 'appear');resize();return false;">
      [Lihat Pembahagian Terperinci]</a>
      <div id="toggle_appear" style="display:none">
	      <div>
	      <br>
	      $!detailFaraidCalculation
	      <br>
	      Masa Diambil:$!timetaken saat 
	      </div>
      </div>
      
 
#end
<script>
function resize() {
window.moveTo(0,0);
window.resizeTo(screen.width,screen.height);
}

function seterusnya(){    	
	document.${formName}.action.value = "next";
	document.${formName}.submit();
}
function sebelumnya(){    	
	document.${formName}.action.value = "previous";
	document.${formName}.submit();
}
function cetak(idSimati,idPermohonan,bhgnBaitulMal,idPermohonanSimati) {
    var url = "../../servlet/ekptg.report.ppk.SuratAkuanFaraid?idSimati="+idSimati+"&idPermohonan="+idPermohonan+"&bhgnBaitulMal="+bhgnBaitulMal+"&idPermohonanSimati="+idPermohonanSimati;
    var hWnd = window.open(url,'Cetak','width=700,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>