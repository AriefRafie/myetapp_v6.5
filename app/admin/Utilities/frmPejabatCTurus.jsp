#set ($mode = "2")

<script>
if( $jquery('#TABLE_CT_ID_'+'$mode').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#TABLE_CT_ID_'+'$mode').offset().top);
}
</script>

<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TABLE_CT_ID_$mode">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Carian Terperinci  
<!--<input type="button" id="cmdAddPejabat" name="cmdAddPejabat"   
onClick="doDivAjaxCall$formname('div_viewPejabat','editPejabat','ID_PEJABAT=');" value="Tambah Pejabat" > -->
</legend>
<table width="832" border="0" cellpadding="2" cellspacing="2">
	<!--<tr>
	<td >Jenis Pejabat</td><td >:</td>
	<td valign="top">
	<input type="radio" id="radioPejabat" name="radioPejabat" value="1" 
	onClick="hidePejUrus();sendValueJenisPejabat(this,this.value,'jenisPejabat');"  >Pejabat JKPTG
	<input type="radio" id="radioPejabat" name="radioPejabat" value="2"
	onClick="showPejUrus();sendValueJenisPejabat(this,this.value,'jenisPejabat');"   >Pejabat Urusan
	
	<input type="hidden" id="jenisPejabat" name="jenisPejabat" value="$jenisPejabat" >	
	<input type="hidden" id="cetakReport" name="cetakReport" value="$cetakReport" >
	</td>
	</tr>-->
	
    <tr>
	<td>
	Pejabat Urusan
	</td><td >:</td>
	<td>
    <select id="ID_JENISPEJABAT_$mode"  name="ID_JENISPEJABAT_$mode" >	   
	<option value = "" >SILA PILIH</option>
	#foreach ( $ruj in $list_TBLRUJJENISPEJABAT )		
	<option $selected_ruj value="$ruj.ID" >$ruj.KETERANGAN</option>
	#end
	</select>
	</td>
    </tr>
    
            <tr>
             <td >Negeri</td><td >:</td>
             <td>             
	      <select id="ID_NEGERI_$mode"  name="ID_NEGERI_$mode" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
						<option $selected_ruj value="$ruj.ID" >$ruj.KETERANGAN</option>
						#end
					</select>
             </td>
    	</tr>
    	
    
        <tr>
             <td>Bahagian</td><td >:</td>
             <td> <select id="ID_SEKSYEN_$mode"  name="ID_SEKSYEN_$mode" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJSEKSYEN )		
						<option $selected_ruj value="$ruj.ID" >$ruj.KETERANGAN</option>
						#end
					</select>
             </td>
    </tr>
    
   
    
  <tr>
	<td></td>
	<td ></td>
	<td>
<input type="button" id="cmdCariPejabat" name="cmdCariPejabat" value="Cari" 
onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('div_URUSAN','showSenaraiPejabat2','mode=2&scrolPosition='+getPageLocation('$command')); document.getElementById('fieldJKPTG').style.display='none';document.getElementById('cmdPrint2').style.display='';" >
<input type="button" id="cmdBatalCariPejabat" name="cmdBatalCariPejabat" value="Reset" onClick="doDivAjaxCall$formname('div_carian','batalCarianUtama','FlagCari=Y');" >
<input style="display:none" type="button" id="cmdPrint2" name="cmdPrint2"
onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('SenaraiForPrint2','cetakListPejabat','FlagCetak=Y&mode=2');" 
				 value="Cetak Laporan" />
</td>

</tr>
 <tr>
 <td></td>
 <td></td>
	<!--<td>Statistik Unit JKPTG <img width="20" height="20" src="../img/images_stat.png" onclick="doCetakStats('fieldJKPTG')"></td>-->
</tr>
    
	
</table>
</fieldset>

</td></tr>
</table>

<br />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td width="8" nowrap></td>
<td colspan="14">
<fieldset id="fieldJKPTG" style="display:none">
<div id="div_statsJKPTG">
</div>
</fieldset>
</td>
</tr>
</table>



<script>   
 
  //document.getElementById("div_DropdownPejUrus").style.visibility='hidden';
  
  function showPejUrus(){
      document.getElementById('div_DropdownPejUrus').style.display = '';

  }
  function hidePejUrus(){
	  //div_DropdownPejUrus
     document.getElementById('div_DropdownPejUrus').style.display = 'none';

}


function openStatsJKPTG(){

if(document.getElementById('div_statsOnline').style.display=="none"){
		document.getElementById('div_statsOnline').style.display="";
		//doDivAjaxCall$formname('div_statsJKPTG','showStatsJKPTG','');
	}
else if(document.getElementById('div_statsOnline').style.display==""){
		document.getElementById('div_statsOnline').style.display="none";
		document.getElementById('div_statsSubOnline').style.display="none";
	}

}

</script>

<div id="div_rowPejabat"></div>

