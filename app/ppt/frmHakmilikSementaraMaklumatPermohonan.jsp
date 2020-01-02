<style type="text/css">
#sddmheader
{	margin: 0;
	padding: 0;
	z-index: 30}

#sddmheader li
{	margin: 0;
	padding: 0;
	list-style: none;
	float: left;
	
	}

#sddmheader li a
{	display: block;
	color: #FFF;
	text-align: center;
	text-decoration: none}

#sddmheader li a:hover
{	
background: #E0F2F7;
}

#sddmheader div
{	position: absolute;
	visibility: hidden;
	margin: 0;
	padding: 0;	
	border: 1px solid #5970B2;
	z-index:2;
	}

	#sddmheader div a
	{	position: relative;
		display: block;
		margin: 0;
		padding: 2.5px 10px;
		width: auto;
		white-space: nowrap;
		text-align: left;
		text-decoration: none;
		background: #EAEBD8;
		color: #2875DE;
		font: 11px arial;
		z-index:2;
		}

	#sddmheader div a:hover
	{	background: #49A3FF;
		color: #FFFFFF;
		}

<!--
.kaler_biru {color: #0000FF}
-->
</style>


<fieldset>
<strong>
<legend>Maklumat Permohonan</legend>
</strong>
<table width="100%">
  <tr>
    <td width="29%" align="left">KEMENTERIAN</td>
    <td width="1%">:</td>
    <td width="70%">$nama_kementerian</td>
  </tr>
  <tr>
    <td align="left">NO. RUJ. JKPTG NEGERI</td>
    <td>:</td>
    <td>
    
     <ul id="sddmheader"  >
    <li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()"><font color="blue">$no_fail</font>        
        </a> 
        <div id="m1" 
            onmouseover="mcancelclosetime()" 
            onmouseout="mclosetime()" >   
          
            
           #foreach ($list in $list_sub_header)
         <a href="javascript:paparPerjalananFail('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','${session.getAttribute('_portal_role')}')" >$list.STATUS</a>
            #end
           
            
        </div>
    </li>
   </ul>
	<div style="clear:both"></div>
    
    </td>
  </tr>
  <tr>
    <td> <a href="javascript:bukak_header('detail_header')"><font color="blue">Maklumat Lengkap</font></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
  	<td  colspan="3">
    <fieldset id="detail_header" style=" display:none"  >
    <table>
    <tr>
    <td width="29%" align="left">AGENSI</td>
    <td width="1%">:</td>
    <td width="70%">$idAgensi</td>
  </tr>
  <tr>
    <td width="29%" align="left">TARIKH TERIMA</td>
    <td width="1%">:</td>
    <td width="70%">$tarikh_terima</td>
  </tr>
  <tr>
    <td width="29%" align="left">PROJEK NEGERI</td>
    <td width="1%">:</td>
    <td width="70%">$projek_negeri</td>
  </tr>
  <tr>
    <td width="29%" align="left">DAERAH</td>
    <td width="1%">:</td>
    <td width="70%">$nama_daerah</td>
  </tr>
  <tr>
    <td width="29%" align="left">TARIKH DIKEHENDAKI</td>
    <td width="1%">:</td>
    <td width="70%">$tarikh_kehendaki</td>
  </tr>
  <tr>
    <td width="29%" align="left">TUJUAN PENGAMBILAN</td>
    <td width="1%">:</td>
    <td width="70%">$tujuan</td>
  </tr>
  <tr>
    <td width="29%" align="left">&nbsp;</td>
    <td width="1%">:</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td width="29%" align="left">NO. RUJ.KEMENTERIAN</td>
    <td width="1%">:</td>
    <td width="70%">$no_rujukan_surat</td>
  </tr>
  <tr>
    <td width="29%" align="left">NO. RUJ. PTD</td>
    <td width="1%">:</td>
    <td width="70%">$no_rujukan_ptd</td>
  </tr>
  <tr>
    <td width="29%" align="left">NO. RUJ. PTG</td>
    <td width="1%">:</td>
    <td width="70%">$no_rujukan_ptg</td>
  </tr>
  <!--<tr>
    <td width="25%" align="left">NO. RUJ. JKPTG NEGERI</td>
    <td width="1%">:</td>
    <td width="74%">$no_rujukan_upt</td>
  </tr>-->
  <tr>
    <td width="29%" align="left">STATUS PERMOHONAN</td>
    <td width="1%">:</td>
    <td width="70%">$keterangan</td>
  </tr>
    </table>
    </fieldset>
    </td>
  </tr>
</table>
</fieldset>


 <script>
	 function bukak_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
		//window.location.hash=id;
        //goTo(id);
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
	 </script>
     
     #parse("app/ppt/frmPPTHeader_script.jsp")