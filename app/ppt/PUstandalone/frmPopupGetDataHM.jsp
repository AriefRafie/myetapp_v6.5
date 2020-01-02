<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">

#parse("css/eTapp_PPT.css")
</style>

<br/>

<fieldset>
  <legend><strong>Carian Hakmilik/Lot</strong></legend>
	<table width="100%" cellspacing="2" cellpadding="0" border="0">
   		
   		<tr>
    	 	<td width="3%">&nbsp;</td>
    		<td width="25%">No.Fail JKPTG/PTG/PTD/UPT</td>
        	<td width="1%">:</td>
            <td width="71%"><input type="text" name="txtNoFail" value="$!txtNoFail" style="text-transform:uppercase;" id="txtNoFail" size="40" onkeyup="this.value=this.value.toUpperCase();" /></td>
        </tr>
        
    	<tr>
    	 	<td>&nbsp;</td>
    		<td>No.Lot/PT</td>
        	<td>:</td>
            <td ><input type="text" name="txtNolot" value="$!txtNolot" style="text-transform:uppercase;" id="txtNolot" size="20" onkeyup="this.value=this.value.toUpperCase();" /></td>
        </tr>
        
        <tr>
    	 	<td>&nbsp;</td>
    		<td>No.Hakmilik</td>
        	<td>:</td>
            <td><input type="text" name="txtNoHakmilik" value="$!txtNoHakmilik" style="text-transform:uppercase;" id="txtNoHakmilik" size="20" onkeyup="this.value=this.value.toUpperCase();" /></td>
        </tr>
   
   		<tr>
    	 	<td>&nbsp;</td>
    		<td>Nama Pihak Berkepentingan</td>
        	<td>:</td>
            <td><input type="text" name="txtNamaPB" value="$!txtNamaPB" style="text-transform:uppercase;" id="txtNamaPB" size="40" onkeyup="this.value=this.value.toUpperCase();" /></td>
        </tr>
        
        <tr>
        	<td colspan="3">&nbsp;</td>
        	<td>
            	<input name="cari" value="Cari" type="button" onclick="javascript:searchHM()" />
  				<input type=button value = "Kosongkan" onClick="javascript:clearData();">
            </td>
        </tr>
        
    </table>
</fieldset>

<br/>

<fieldset>
	<legend><strong>Senarai Hakmilik/Lot</strong></legend>
	 
     <table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
         <tr class="table_header">
        	<td align="center"><b>No</b></td>
            <td><b>No.LOT/PT</b></td>
           	<td><b>No.Hakmilik</b></td>  
           	<td><b>Daerah</b></td> 
           	<td><b>Mukim</b></td>          
        </tr>
        
      #if($sizelistHM!=0)
           	#foreach($list in $listHM)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         	<tr>
        		<td class="$row" align="center">$!list.bil</td>
	            <td class="$row"><a href="javascript:getDataHM('$!list.ID_HAKMILIK')"><font color="blue">$!list.NO_LOTPT</font></td>
	            <td class="$row">$!list.KOD_JENIS_HAKMILIK&nbsp;$!list.NO_HAKMILIK</td>
	            <td class="$row">$!list.NAMA_DAERAH</td>
				<td class="$row">$!list.NAMA_MUKIM</td>
        	</tr>	      	
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
			<input type="button" name="cmdTutup" value ="Tutup" onClick="javascript:window.close()">
			</td>
		</tr>
	</table>



<script>
function getDataHM(id_hakmilik) {
	window.opener.getAndSetDataHM(id_hakmilik);
	window.close();
}
function clearData() {
	doAjaxCall${formName}("clearData");
}
function searchHM(){
	var txtNofail = document.${formName}.txtNoFail.value;
	var txtNolot = document.${formName}.txtNolot.value;
	var txtNoHakmilik = document.${formName}.txtNoHakmilik.value;
	var txtNamaPB = document.${formName}.txtNamaPB.value;
	if(txtNofail!="" || txtNolot!="" || txtNoHakmilik!="" || txtNamaPB!=""){
		doAjaxCall${formName}("searchHM");
	}else{
		doAjaxCall${formName}("clearData");
	}
}
</script>
	