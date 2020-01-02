<style type="text/css">
<!--

body {
	text-align:center;
	font-family:serif;
	background:#FAF8CC;
}

.style1 {color: #0000FF}

.table_header {
color:#FFF;
background-color:#960;
font-weight:400;
border-style:10px solid #FFF;
}

.module_content,td.row1,.row1 {
background-color:#FAF8CC;
}

.table_row2,.module_content_bg {
background-color:#F2E3E9;
}

td.row2,td.selected,.row2 {
background-color:#ebbc5d;
}

.stylobutton {
width:140px;
}

input.button {
border:0.05em solid;
cursor:pointer;
font-weight:700 !important;
overflow:visible;
width:103px;
padding:0 0.25em;
}

.apps-container {
	align:center;
	width:100%;
	height:auto;
	min-height:95%;
	margin-right:auto;
	margin-left:auto;
	border:1px solid #000;
	font-family:Verdana;
	font-size:8pt;
	font-weight:400;
	color:#475E62;
	border:1px solid #dfc8a4;
}

td {
	font-size:8pt;
	font-family:Verdana;
	font-weight:normal;
}

fieldset {
	border:1px solid #C6B879;
	padding:3px;
}

fieldset legend {
	background:#ECD672;
	font-weight:700;
	padding:6px;
}
-->
</style>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_HTP.css" />
<table width="100%" border="0">
  <tr>	
  	 <td>

<fieldset><legend>SENARAI KEMENTERIAN/ AGENSI TERKINI</legend>
<table width="100%" border="0">
  <tr class="table_header">
    <td width="3%">Bil.</td>
    <td width="40%"><div align="left">Kementerian</div></td>
    <td width="40%"><div align="left">Agensi</div></td>
    <td width="17%"><div align="left">Luas(Hektar)</div></td>
  </tr>
  #foreach ($senarai in $SenaraiTransaksi)
  #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
       #set( $row = "row2" )
    #else
       #set( $row = "row1" )
    #end
  <tr>
    <td class="$row">$i.</td>
    <td class="$row"><div align="left">$senarai.namaKementerian</div></td>
    <td class="$row"><div align="left">$senarai.namaAgensi</div></td>
    <td class="$row"><div align="right">$senarai.luasBersamaan</div></td>
  </tr>
  #end
	#if ($!SenaraiTransaksi.size() == 0)
 	<tr>
    	<td class="$row">&nbsp;</td>
    	<td class="$row" colspan=3><div align="left">Tiada Rekod</div></td>
  	</tr>
		
  	#end
</table>
</fieldset>

  	 </td>
  </tr>
</table>