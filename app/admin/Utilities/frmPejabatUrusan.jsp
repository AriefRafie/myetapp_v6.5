<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Carian &nbsp;&nbsp;
<input type="button" id="cmdAddPejabat" name="cmdAddPejabat"   
onClick="doDivAjaxCall$formname('div_senaraiUtama','editPejabat','ID_PEJABAT=');" value="Tambah Pejabat" > 
</legend>
<table border="0" cellpadding="2" cellspacing="2">
	<tr>
	<td >Jenis Pejabat tryyyy </td>
	<td>  
	<select id="Form_id_jenispejabat" name="Form_id_jenispejabat">
	$!selectPejabat
	</select>
	</td>
	</tr>
            <tr>
             <td >Negeri</td>
             <td>             
	     $!selectNegeri
             </td>
    	</tr>
       <!--<tr>
           <td >Daerah</td>
           
           <td>             
		$selectDaerah
           </td>
    </tr>-->
    
        <tr>
             <td >Seksyen</td>
             <td>             
    	     $!selectSeksyen
             </td>
    </tr>
    
    
	<tr>
	<td></td>
	<td>
<input type="button" id="cmdCariPejabat" name="cmdCariPejabat" value="Cari" onClick="doDivAjaxCall$formname('div_senaraiUtama','showSenaraiPejabat','');" >
<input type="button" id="cmdBatalCariPejabat" name="cmdBatalCariPejabat" value="Reset" onClick="$jquery('#carianTerperinci').val('');doDivAjaxCall$formname('div_carian','batalCarianUtama','FlagCari=Y');" >

</td>


</tr>
</table>
</fieldset>

<script>   
  $jquery(document).ready(function () {
	  doDivAjaxCall$formname('div_senaraiUtama','showSenaraiPejabat','');	  
  });  
  </script>

<div id="div_rowPejabat"></div>
<div id="div_viewPejabat"></div>

</table>





<!--<table>
<tr>
<td>
 <p>
  <input class="stylobutton"  type="button" value="Tambah Pejabat" onclick="javascript:doAjaxCall${formName}('editPejabat');" />
</p>
</td>
</tr>
</table>
-->
<!--
<tr>
</tr>

<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<strong> MAKLUMAT PEJABAT </strong>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">
<a href="javascript:document.getElementById('div_viewPejabat1').style.display=''; doDivAjaxCall${formname}('div_viewPejabat1','editPejabat','idPejabat=$viewPejabat.ID_PEJABAT');">
<img title="Tambah Pengguna" src="../img/plus3.gif" border="0"></a>
</td>
</tr>
</table>
<div id="div_viewPejabat1" >
		
		<script> 
		
		  $jquery(document).ready(function () {
			 doDivAjaxCall$formname('div_viewPejabat1','editPejabat','idPejabat=$viewPejabat.ID_PEJABAT'+$jquery('#editPejabat').val());			 	  
		  });
		
		</script>
	
		</div>	-->