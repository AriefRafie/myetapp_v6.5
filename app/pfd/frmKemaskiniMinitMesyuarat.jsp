<script src="../../../../testing/SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="../../../../testing/SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style8 {color: #0033FF}
.style9 {font-size: 10px}
.style10 {color: #0000FF}
-->
</style>

<form action="" method="post" name="f1" id="f1">

<input name="mode" type="hidden" value="$mode" />
<input name="command" type="hidden" value="" />
<input name="idMinitmesyuaratsubpara" type="hidden" value="" />
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
&nbsp;
<table width="100%" border="1" >
    <tr>
      <td><fieldset>
      <legend>MAKLUMAT MESYUARAT</legend>
      <table width="100%" border="0">
      #foreach ($mesyuarat in $Mesyuarat)
      <input name="idMesyuarat" type="hidden" value="$mesyuarat.id_Mesyuarat" />
        <tr>
          <td width="29%"><div align="left">BIL MESYUARAT</div></td>
          <td width="1%"><div align="right">:</div></td>
          <td width="70%" class="style10">$mesyuarat.bil_Mesyuarat.toUpperCase()</td>
        </tr>
        <tr>
          <td><div align="left">TAJUK MESYUARAT</div></td>
          <td width="1%"><div align="right">:</div></td>
          <td class="style10">$mesyuarat.tajuk_Mesyuarat.toUpperCase()</td>
        </tr>
        <tr>
          <td><div align="left">TARIKH MESYUARAT</div></td>
         <td width="1%"><div align="right">:</div></td>
          <td class="style10">$mesyuarat.tarikh_Mesyuarat.toUpperCase()</td>
        </tr>
       #end
      </table>
      </fieldset>      </td>
    </tr>
    <tr>
      <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="javascript:setSelected(0);" class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Perkara Minit</font></strong></li>
          <li onClick="javascript:setSelected(1);" class="TabbedPanelsTab" tabindex="1"><strong><font size="1">Keterangan Minit</font></strong></li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
          <fieldset>
          <legend>PERKARA MINIT</legend>
          <table width="100%" border="0" >
          #foreach ($minit in $PerkaraMinit)
          <input name="idMinitmesyuarat" type="hidden" value="$minit.id_Minitmesyuarat" />
              <tr>
                <td width="29%"><div align="right" class="style9">
                  <div align="left">Agenda Mesyuarat</div>
                </div></td>
                <td width="1%"><div align="right">:</div></td>
                <td width="70%">$selectAgendaA</td>
              </tr>
              <tr>
                <td valign="top"><div align="right" class="style9">
                  <div align="left">Perkara Minit</div>
                </div></td>
                <td width="1%" valign="top"><div align="right">:</div></td>
                <td><label>
                  <textarea name="txtPerkaraMinit" cols="41" id="txtPerkaraMinit" $readOnly1 >$minit.tajuk_Minit</textarea>
                </label></td>
              </tr>
              <tr>
                <td colspan="3">                </td>
                </tr>
          	#end
            </table>
          </fieldset>
          &nbsp;
        <fieldset>
                <legend>SENARAI PERKARA MINIT</legend>
<table width="100%" border="0" >
                  <tr class="table_header">
                    <td width="1%">NO</td>
                    <td width="29%">AGENDA MESYUARAT</td>
                    <td width="70%">PERKARA MINIT</td>
                </tr>
                #foreach ($perkara in $SenaraiPerkaraMinit)
                 #if ($perkara.bil == '') 
                	#set ($row = 'row1')
              	#elseif ($perkara.bil % 2 != 0)
                	#set ($row = 'row1')
              	#else 
               		#set ($row = 'row2')
              	#end 
               
                  <tr>
                    <td class="$row">$perkara.bil</td>
                    <td class="$row">$perkara.agenda_Mesyuarat</td>
                    <td class="$row"><a href="javascript:paparPerkaraMinit('$perkara.id_Minitmesyuarat')" class="style1 style1 style8">$perkara.tajuk_Minit</a></td>
                  </tr>
                 #end
                </table>
              </fieldset>
          </div>
          <div class="TabbedPanelsContent">
          <fieldset>
          <legend>KETERANGAN MINIT</legend>
          <table width="100%" border="0" >
          #foreach ($paparPara in $Para)

          <input name="idMinitmesyuaratpara" type="hidden" value="$paparPara.id_Minitmesyuaratpara" />
              <tr>
                <td width="29%"><div align="right" class="style9">
                  <div align="left">Agenda Mesyuarat</div>
                </div></td>
                <td width="1%"><div align="right" class="style9">:</div></td>
                <td width="70%">$selectAgendaB</td>
              </tr>
              <tr>
                <td><div align="right" class="style9">
                  <div align="left">Perkara Minit</div>
                </div></td>
                <td width="1%"><div align="right" class="style9">:</div></td>>
                <td>$selectPerkaraMinitB</td>
              </tr>
              <tr>
                <td valign="top"><div align="right" class="style9">
                  <div align="left">Minit</div>
                </div></td>
                <td width="1%" valign="top"><div align="right" class="style9">:</div></td>
                <td><label>
                  <textarea name="txtMinit" cols="41" id="txtMinit" $readOnly2>$paparPara.para</textarea>
                </label></td>
              </tr>
              <tr>
                <td><div align="right" class="style9">
                  <div align="left">Tindakan</div>
                </div></td>
                <td width="1%"><div align="right" class="style9">:</div></td>
                <td><label>
                  <textarea name="txtTindakan" cols="41" id="txtTindakan" $readonly2="$readOnly2">$paparPara.pihak_Bertindak</textarea>
                </label></td>
              </tr>
              <tr>
                <td valign="top"><div align="right" class="style9">
                  <div align="left">Maklumbalas</div>
                </div></td>
                <td width="1%" valign="top"><div align="right" class="style9">:</div></td>
                <td><label>
                <textarea name="txtMaklumbalas" cols="41" id="txtMaklumbalas" $readOnly2>$paparPara.maklumbalas</textarea>
                </label></td>
              </tr>
              <tr>
                <td colspan="3">                <div align="left"></div></td>
                </tr>
              <tr>
                <td colspan="3">                <div align="left"></div></td>
                </tr>
            </table>
          </fieldset>
          &nbsp;
        <fieldset>
                <legend>SENARAI MINIT</legend>
<table width="100%" border="0" >
                  <tr class="table_header">
                    <td width="1%">NO</td>
                    <td width="10%">AGENDA MESYUARAT</td>
                    <td width="20%">PERKARA MINIT</td>
                    <td width="30%">MINIT</td>
                    <td width="10%">TINDAKAN</td>
                    <td width="29%">MAKLUMBALAS</td>
                </tr>
                  #foreach ($para in $SenaraiMinit)
                 
                   #if ($para.bil == '') 
                    #set ($row = 'row1')
                  #elseif ($para.bil % 2 != 0)
                    #set ($row = 'row1')
                  #else 
                    #set ($row = 'row2')
                  #end 
                  <tr>
                    <td class="$row">$para.bil</td>
                    <td class="$row">$para.agenda_Mesyuarat</td>
                    <td class="$row">$para.tajuk_Minit</td>
                    <td class="$row"><a href="javascript:paparPara('$para.id_Minitmesyuaratpara')" class="style8">$para.para</a></td>
                    <td class="$row">$para.pihak_Bertindak</td>
                    <td class="$row">$para.maklumbalas</td>
                  </tr>
                  #end
                </table>
              </fieldset>
              &nbsp;
                <fieldset>
                <legend>SENARAI SUB MINIT
                <label>
                <input type="button" name="cmdTambahSubPara" id="cmdTambahSubPara" value="Tambah" onclick="tambahSubPara($paparPara.id_Minitmesyuaratpara,'tambah')" />
                </label>
                </legend>
                <table width="100%" border="0" >
                  <tr class="table_header">
                    <td width="3%">NO</td>
                    <td width="97%">SUB MINIT</td>
                  </tr>
                  #foreach ($subpara in $SenaraiSubMinit)
                  
                   #if ($subpara.bil == '') 
                    #set ($row = 'row1')
                  #elseif ($subpara.bil % 2 != 0)
                    #set ($row = 'row1')
                  #else 
                    #set ($row = 'row2')
                  #end 
                  <tr>
                    <td class="$row">$subpara.bil</td>
                    #if ($subpara.bil != '')
                    <td class="$row"><a href="javascript:paparSubPara('$subpara.id_Minitmesyuaratsubpara','papar')" class="style8">$subpara.sub_Para</a></td>
                    #else
                     <td class="$row">$subpara.sub_Para</td>
                    #end                  </tr>
                  #end
                </table>
              </fieldset>
          </div>
        </div>
      </div></td>
    </tr>
    <tr align="center">
      <td><label>
      #if ($mode == 'View')
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
        <input type="submit" name="cmdCetak" id="cmdCetak" value="Cetak" />
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
      #else
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
      #end
      </label></td>
    </tr>
  </table>
  #end
  <table width="100%" >
  <tr>
    <td align="right"><strong>CL-05-23</strong></td>
  </tr>
</table>
</form>

<script>
function batal(){

	document.f1.command.value = "SenaraiMesyuarat";
	document.f1.action = "";
	document.f1.submit();
}
function kembali(){

	document.f1.command.value = "SenaraiMesyuarat";
	document.f1.action = "";
	document.f1.submit();
}
function simpan(){
	if ( !window.confirm("Anda Pasti?") ) return;
	document.f1.action = "";
	document.f1.command.value = "simpan";
	document.f1.submit();
}
function paparPerkaraMinit(id){
	
	document.f1.action = "";
	document.f1.command.value = "paparPerkaraMinit";
	document.f1.mode.value = "";
	document.f1.idMinitmesyuarat.value = id;
	document.f1.submit();
	
	
}
function paparPara(id){
	document.f1.action = "";
	document.f1.command.value = "paparPara";
	document.f1.mode.value = "";
	document.f1.idMinitmesyuaratpara.value = id;
	document.f1.submit();
	
	
}
function kemaskini(){
	document.f1.action = "";
	document.f1.command.value = "kemaskini";
	document.f1.mode.value = "";
	document.f1.submit();

}
function tambahSubPara(id,command){
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniSubMinitMesyuarat?idMinitmesyuaratpara="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();


}
function paparSubPara(id,command){
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniSubMinitMesyuarat?passIdMinitmesyuaratsubpara="+id+"&command="+command;
    var hWnd = window.open(url,'printuser','width=500,height=200, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
}
function setSelected(tabId) {
	document.f1.tabId.value = tabId;
	
}
</script>

<script>
<!--
//var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});


//-->
</script>