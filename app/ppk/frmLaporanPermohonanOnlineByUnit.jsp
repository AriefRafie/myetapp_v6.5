
<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>

 <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
 <input type="hidden" name="userId" value='$usid'>
<br />
<fieldset>
<legend><b>CARIAN</b>
</legend>
<table width="100%" align="center" border="0">

  <tbody>
    <tr>
      <td width="29%" height="24" scope="row" align="right"><div align="right">MyId Pemohon / Simati </div></td>
      <td width="1%"><div align="center">:</div></td>
      <td width="70%"><input name="myid" id="myid" type="text" size="50" maxlength="50" value="$myid" style="text-transform:uppercase;" > 
 </td>
    </tr>
    
     <tr>
      <td scope="row"><div align="right">Nama Simati / Pemohon</div></td>
      <td><div align="center">:</div></td>
      <td><input name="nama" id="nama" type="text" size="50" maxlength="50" value="$nama" style="text-transform:uppercase;" /></td>
    </tr>
    <tr>
      <td scope="row"><div align="right">No Fail Online / Pusaka</div></td>
      <td><div align="center">:</div></td>
      <td><input name="no_fail" id="no_fail" type="text" size="50" maxlength="50" value="$no_fail" style="text-transform:uppercase;" /></td>
    </tr>
    <tr>
      <td scope="row"><div align="right">Seksyen</div></td>
      <td><div align="center">:</div></td>
      <td>
      #set($listseksyen = ["8","17"])
						     <select  class="autoselect" name="seksyen" id="seksyen" >
						   	 <option value="" $selected_jenis_log >SILA PILIH</option>
						   		#foreach ( $y in $listseksyen )
						   		#if( $y == $!seksyen )
						   			#set ( $selected_jenis_log = "selected" )
						   		#else
						   			#set ( $selected_jenis_log = "" )
						   		#end                       
						   	<option value="$y" $selected_jenis_log >
                            #if($y == "8")
                            SEKSYEN 8
                            #elseif($y == "17")
                            SEKSYEN 17
                            #end
        </option>
						   		#end
							</select>
      </td>
    </tr>
    <tr>
      <td scope="row"><div align="right">Unit Pembahagian Pusaka</div></td>
      <td>:</td>
      <td>#set($listPejabat = $ListPejabat)
						     <select  class="autoselect" name="pejabat" id="pejabat" >
						   	 <option value="" $selected_jenis_log >SILA PILIH</option>
						   		#foreach ( $z in $listPejabat)
						   		#if( $z.ID_PEJABATJKPTG == $!nama_pejabat )
						   			#set ( $selected_jenis_log = "selected" )
						   		#else
						   			#set ( $selected_jenis_log = "" )
						   		#end                       
						   	<option value="$z.ID_PEJABATJKPTG" $selected_jenis_log >
                                $!z.NAMA_PEJABAT
                            </option>
						   		#end
							</select></td>
    </tr>
    <tr>
      <td scope="row">&nbsp;</td>
      <td>&nbsp;</td>
      <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="search_data()" />
        <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="kosong_data()" /></td>
    </tr>
    <tr>
      <td scope="row">&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;      </td>
    </tr>
  </tbody>
</table>
</fieldset>

<br/>


<fieldset>
<legend><b>SENARAI PERMOHONAN ONLINE</b></legend>
<table width="100%">

<tr>
     		<td colspan="5" scope="row" align="right">
    	
		#set ($pagingTitle = "Jumlah Carian")
		#parse("app/utils/record_paging.jsp")
    		</td>
    	</tr>
<tr>
	<td><input name="cmdCetak" id="cmdCetak" value="Cetak" type="button"  onclick="Cetak()"></td>
</tr>
</table>

<table align="center" width="100%"> 
  	
  
  
    <tr class="table_header">
      <td width="5%"><div align="center">Bil</div></td>
      <td width="20%">No Rujukan Online</td>
      <td width="20%"><div align="center">No Fail</div></td>
      <td width="10%"><div align="center">Tarikh Permohonan</div></td>
     
      <td width="15%"><div align="left">Nama Pemohon</div></td>
      <td width="15%"><div align="center">Seksyen</div></td>
      <td width="30%"><div align="center">Unit Pembahagian Pusaka</div></td>
    </tr>
            
          #set ($list = "")
          #set ($counter = 0)
          #foreach ($fail in $SenaraiFail)
          #set( $counter = $counter + 1 )
          	#if ($fail.bil == '')
                #set( $row = "row1" )
            #elseif (($fail.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
          
					      <tr class="$row">
				          <td ><div align="center">$fail.bil</div></td>
				          <td >
                          <!--<a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_Simati','$flagno','$idFlag','$SimpanStatus')" class="style1">
				          </a>-->
                          $fail.noonline
                          </td>
                          <td ><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.no_Fail</div></td>
				          <td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.tarikhmohononline</div></td>
				      
				          <td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$fail.namapemohon
                     
                          </div></td>
				          <td ><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.seksyen</div></td>
           				  <td ><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$fail.nama_pejabat</div></td>	
</tr>
                        
                          
                    
			#end
            
            
            #if($counter == 0)
            <tr>
            <td></td>
            <td class="$row">
           Tiada rekod
           </td>
           </tr>
           #end 	     
	      
        
  
     
        					
   
  </table>
</fieldset>


    
    

 
<script>
function search_data(){
	if (document.${formName}.no_fail.value == "" && document.${formName}.nama.value == "" && document.${formName}.myid.value == "" && document.${formName}.seksyen.value == "" && document.${formName}.pejabat.value == "")
	{
		alert("Sila masukkan maklumat carian.");		
		document.f1.no_fail.focus(); 
		return; 
	}
	else {
	//alert("XXX"+document.${formName}.command.value);
	//document.${formName}.command.value = "Cari";
	//document.${formName}.action = "";
	//document.${formName}.submit();
	doAjaxCall${formName}("Cari");
	document.${formName}.cmdCari.value = "Sila Tunggu....";	
	}
}

function kosong_data(){
	
	doAjaxCall${formName}("");
	document.${formName}.cmdKosongkan.value = "Sila Tunggu....";	
	
}
function Cetak() {

		var url = "../servlet/ekptg.report.ppk.LaporanPermohonanOnlineByUnit?noKP="+escape(document.${formName}.myid.value)+"&nama="+escape(document.${formName}.nama.value)+"&noFail="+escape(document.${formName}.no_fail.value)+"&seksyen="+escape(document.${formName}.seksyen.value)+"&idPejabat="+escape(document.${formName}.pejabat.value)+"&userId="+escape(document.${formName}.userId.value);
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

}
</script>
