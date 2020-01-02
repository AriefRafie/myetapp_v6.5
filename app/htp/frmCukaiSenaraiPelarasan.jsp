  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>

<fieldset>
<legend>MAKLUMAT PILIHAN</legend>
<!--<form name="f1" method="post"> -->
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
    	<tr>			  
			<td colspan="2" align="center">&nbsp;</td>
      </tr>
      <tr >
        <td align="right" width="40%"><div align="right"><strong>Negeri : &nbsp;&nbsp;</strong></div></td>
        <td>$selectNegeri</td>
        <input type="hidden" name="idnegeri" value="$idnegeri">
      </tr>    
      <tr>
        <td align="right" width="40%"><div align="right"><strong>Daerah : &nbsp;&nbsp;</strong></div></td>
        <td>$selectDaerah</td>
        <input type="hidden" name="iddaerah" value="$iddaerah">
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right"><strong>Bandar/Pekan/Mukim : &nbsp;&nbsp;</strong></div></td>
        <td>$selectMukim</td>
        <input type="hidden" name="idmukim" value="$idmukim">
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right"><strong>Jenis Hakmilik : &nbsp;&nbsp;</strong></div></td>
        <td>$selectJenisHakmilik</td>
        <input type="hidden" name="idjenishakmilik" value="$idjenishakmilik">
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right"><strong>No. Hakmilik : &nbsp;&nbsp;</strong></div></td>
        <td><input type="text" name="txtNoHakmilik" size="40" onkeyup="validateCurrency(this,this.value);" value="$carianNoHakmilik"/></td>
      </tr>
      <tr>
        <td></td>
        <td><input class=button name="cari" value="Carian Hakmilik" type="button" onclick="javascript:search_data()">
        	<input class=button value="Kosongkan" onclick="javascript:batal()" type="button"></td>
      </tr>
      <tr>			  
			<td colspan="2" align="center">&nbsp;</td>
		</tr>
    </tbody>
  </table>  
<!--  <input type="text" name="noHakmilik" value="$noHakmilik">		
  <input type="hidden" name="command">
</form>-->
</fieldset>
<fieldset>
<legend>SENARAI CUKAI</legend>

#if ($lists.size() > 0)

           <table width="100%" border="0" cellpadding="2" cellspacing="0">
                <tr>
                    <td  align="left">TOTAL: $totalRecords</td>
                    <td  align="right">
                        SHOW
                        <select class="smallselect" name="itemsPerPage" onchange="javascript:doAjaxCall${formname}('pelarasanCukai','action1=doChangeItemPerPage')">
                            <option value="10" #if ($itemsPerPage == 10) selected #end>10</option>
                            <option value="20" #if ($itemsPerPage == 20) selected #end>20</option>
                            <option value="30" #if ($itemsPerPage == 30) selected #end>30</option>
                            <option value="40" #if ($itemsPerPage == 40) selected #end>40</option>
                            <option value="50" #if ($itemsPerPage == 50) selected #end>50</option>
                        </select>
                    </td>
                 </tr>
            </table>
            
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
	    <tr>
		<td nowrap>
        
		#if( $isFirstPage )
		    <input class="stylobutton"  type="button"  value="<< Previous" disabled>
		#else
		    <input class="stylobutton"  type="button"  value="<< Previous" onclick="javascript:doAjaxCall${formname}('record_listing','action1=getPrevious')">
		#end
		#if( $isLastPage )
		    <input class="stylobutton"  type="button"  value="Next >>" disabled>
		#else
		    <input class="stylobutton"  type="button"  value="Next >>" onclick="javascript:doAjaxCall${formname}('record_listing','action1=getNext')">
		#end
		</td> 
       <!-- -->
        
		<td width="90%">
		&nbsp;
		#foreach ( $i in [1..$totalPages] )
			#if ($i == $page)
			<b>[$i]</b>
			#else
			<a href="javascript:doAjaxCall${formname}('pelarasanCukai','action1=getPage&value=$i')">[$i]</a>
			#end
		#end
		</td>
		<td align="right" nowrap>Page $page of $totalPages</td>
	    </tr>
</table>
            
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tbody>
    <tr>
    	<td colspan="6">&nbsp;&nbsp;&nbsp;</td>
    </tr>
	<tr class="table_header">
      <td width="4%"><strong>Bil.</strong></td>
   	  <td width="13%"><strong>Negeri</strong></td>
  	  <td width="13%"><strong>Daerah</strong></td>
  	  <td width="16%"><strong>Mukim</strong></td>
   	  <td width="40%"><strong>Kementerian</strong></td>
  	  <td width="12%"><b>No. Hakmilik</b></td>
   	  <td width="4%"></td>
    </tr>
      	#foreach ( $fail in $lists )
            #set( $counter = $velocityCount )
			#if ( ($counter % 2) == 0 )
    			#set( $row = "row2" )
			#else
    			#set( $row = "row1" )
			#end
    
    <tr class="$row" onMouseOver="this.className='highlight'" onMouseOut="this.className='$row'">
    	<td height="25">
		#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
		$cnt
		</td>
      	<td>$fail.nama_negeri</td>
        <td>$fail.nama_daerah</td>
        <td>$fail.nama_mukim</td>
        <td>$fail.nama_kementerian</td>
        <td>$fail.kod_jenis_hakmilik$fail.no_hakmilik</td>
        <td>
        <input name="cmdEdit" value="Edit" id="cmdEdit" type="button" onClick="javascript:editCukai('$fail.idNegeri','$fail.idDaerah','$fail.idMukim','$fail.idKementerian','$fail.no_hakmilik','$fail.idJenisHakmilik')"></td>
   	</tr> 
        #end
   #else
    	<tr> 
        <td colspan="7" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
    	</tr>
   #end             
	</tbody>
</table>
	<input type="hidden" name="page" value="$page">
	<input type="hidden" name="idNegeri">
  	<input type="hidden" name="idDaerah">
  	<input type="hidden" name="idMukim">
	<input type="hidden" name="idKementerian">
	<input type="hidden" name="noHakmilik">
	<input type="hidden" name="idJenisHakmilik">
	<input type="hidden" name="command1" value="$command1">
	<input type="hidden" name="pagemode" value="$pagemode">
	<input type="hidden" name="action1" value="$action1">
	 <!-- </form>--->
</fieldset>

<script>
function validateCurrency(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function batal() {
	document.${formName}.reset();
	document.${formName}.command1.value = ""
	document.${formName}.txtNoHakmilik.value = "";
	document.${formName}.socJenisHakmilik.value = "";
	document.${formName}.socNegeri.value = "";
	document.${formName}.socDaerah.value = "";
	document.${formName}.socMukim.value = "";	
	document.${formName}.socNegeri.focus();
	//document.${formName}.action = "";
	document.${formName}.submit();
}
function search_data(){
	document.${formName}.command.value = "";
	//document.${formName}.action = "";
	document.${formName}.submit();
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function doChangeMukim() {
	doAjaxCall${formName}("doChangeMukim");
}
function doChangeHakmilik() {
	doAjaxCall${formName}("doChangeHakmilik");
}
function editCukai(idNegeri,idDaerah,idMukim,idKementerian,noHakmilik,idJenisHakmilik) {
	document.${formName}.idNegeri.value = idNegeri;
	document.${formName}.idDaerah.value = idDaerah;
	document.${formName}.idMukim.value = idMukim;
	document.${formName}.idKementerian.value = idKementerian;
	document.${formName}.noHakmilik.value = noHakmilik;
	document.${formName}.idJenisHakmilik.value = idJenisHakmilik;
	document.${formName}.command.value = "pelarasanCukai";
	document.${formName}.pagemode.value = "viewCukai";
	//document.${formName}.action = "";
	document.${formName}.submit();
}
</script>
