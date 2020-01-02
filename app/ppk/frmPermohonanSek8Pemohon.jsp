
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script src="dvdhla/eclipse/eclipseJ2EE/workspace-ekptg/ekptgv2/ekptgv2_110609/app/ppk/SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="dvdhla/eclipse/eclipseJ2EE/workspace-ekptg/ekptgv2/ekptgv2_110609/app/ppk/SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}
body {
	background-color: #FFFFFF;
}
.style36 {font-size: 12}
.style38 {font-size: 10px}
.style39 {	color: #FF0000;
	font-weight: bold;
}
-->
</style>
</head>

<body>
<form id="form1" name="f1" method="post" action="">
  


<table width="100%" border="0">

<tr>
<td>
 <input type="text" name="command" value="">
 <input type="text" name="mode" value="">
 <input name="tabIdatas" type="text" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="text" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="text" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="text" id="tabIdtepi" value="$selectedTabtepi"/>

 
</td>

</tr>

#foreach($list in $View)
    #set ($id = $list.idPermohonan)
    #set ($idPemohon = $list.idPemohon)
 

<tr>
<td><fieldset>
<legend>MAKLUMAT PERMOHONAN</legend>


<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%"><div align="right">No Fail :</div></td>
          <td width="65%"><div align="left">$list.noFail</div></td>
        </tr>
        <tr>
          <td valign="top"><div align="right">Negeri :</div></td>
          <td><div align="left">$list.namanegeri</div></td>
        </tr>
        <tr>
          <td valign="top"><div align="right">Daerah / Jajahan :</div></td>
          <td><div align="left"><span class="style3">$list.namadaerah</span></div></td>
        </tr>
        <tr>
          <td><div align="right">Unit :</div></td>
          <td><div align="left"><span class="style3">$list.keterangan_unit_psk</span></div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%"><div align="right">Status Fail :</div></td>
          <td width="65%"><div align="left"><span class="style3">$list.keterangan</span></div></td>
        </tr>
        <tr>
          <td><div align="right">Seksyen :</div></td>
          <td><div align="left"><span class="style3">$list.seksyen</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td><div align="right">Tarikh Mohon :</div></td>
          <td><div align="left"><span class="style3">$list.tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td><div align="right">Nama Pemohon :</div></td>
          <td><div align="left"><span class="style3">$list.namaPemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td><div align="right">Nama Simati :</div></td>
          <td><span class="style3">$list.namaSimati</span>
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/></td>
        </tr>
      </table>
    </div></td>
  </tr>
</table>

</fieldset>
</td>
</tr>
#end



  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab style1 style3" tabindex="0">PERMOHONAN</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0">HARTA TAK ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0">HARTA ALIH</li>
      <li class="TabbedPanelsTab style1 style3" tabindex="0">NILAIAN HARTA</li>
    </ul>
    <div class="TabbedPanelsContent">
    <div class="TabbedPanelsContentGroup">
     
        <div id="TabbedPanels2" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0" onclick="setSelected(0,0,0,0);SimatiView()" >SIMATI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">PEMOHON</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">WARIS</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">ORANG KEPENTINGAN</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">SAKSI</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">PEMIUTANG</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">PENGHUTANG</li>
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent"></div>
            
            <div class="TabbedPanelsContent">
              <div id="TabbedPanels3" class="TabbedPanels">
                <ul class="TabbedPanelsTabGroup">
                  <li class="TabbedPanelsTab style1 style3" tabindex="0">PEMOHON</li>
                  <li class="TabbedPanelsTab style1 style3" tabindex="0">PEGUAM</li>
                </ul>
                <div class="TabbedPanelsContentGroup">
                  <div class="TabbedPanelsContent" >Content 1</div>
                   <div class="TabbedPanelsContent" >Content 2</div>
                 
                </div>
              </div>
            </div>
            
            <div class="TabbedPanelsContent">Content 4
            
            
            </div>
            <div class="TabbedPanelsContent">Content 5</div>
            <div class="TabbedPanelsContent">Content 6</div>
            <div class="TabbedPanelsContent">Content 7</div>
            <div class="TabbedPanelsContent">Content 2</div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContent">
        <div id="TabbedPanels4" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab style1 style3" tabindex="0">HARTA TAK ALIH (ADA HAKMILIK)</li>
            <li class="TabbedPanelsTab style1 style3" tabindex="0">HARTA TAK ALIH (TIADA HAKMILIK)</li>
          </ul>
          <div class="TabbedPanelsContentGroup">
            <div class="TabbedPanelsContent">Content 1</div>
            <div class="TabbedPanelsContent">Content 2</div>
          </div>
        </div>
      </div>
      <div class="TabbedPanelsContent">Content 4</div>
      <div class="TabbedPanelsContent">Content 2</div>
    </div>
  </div>    </td>
  </tr>
</table>
</form>

<script>

<!-- SIMATI -->
function SimatiView() {
	document.f1.action = "";
	document.f1.mode.value = "Simatiview";
	document.f1.command.value = "Simati";
	document.f1.submit();
}
function kemaskini_simati(){
	document.f1.mode.value = "kemaskini_simati";
	document.f1.command.value = "Simati";
	document.f1.action = "";
	document.f1.submit();
}
function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.f1.tabIdatas.value = tabIdatas;
    document.f1.tabIdtengah.value = tabIdtengah;
    document.f1.tabIdbawah.value = tabIdbawah;	
	document.f1.tabIdtepi.value = tabIdtepi;	
}
function SimpanSimati() {
	input_box = confirm("Adakah anda pasti?");
	
	
	if (input_box == true) {
	document.f1.mode.value = "simpan_simati";
	document.f1.command.value = "Simati";
	document.f1.action = "";
	document.f1.submit();
    }
	
}
function BatalSimati() {
input_box = confirm("Adakah anda pasti?");
	
	
	if (input_box == true) {
	document.f1.action = "";
	document.f1.mode.value = "batal_simati";
	document.f1.command.value = "Simati";
	document.f1.submit();
	}
}
</script>

<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabatas});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabtengah});
var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3",{defaultTab:$selectedTabbawah});
var TabbedPanels4 = new Spry.Widget.TabbedPanels("TabbedPanels4",{defaultTab:$selectedTabtepi});

</script>



</body>
</html>
