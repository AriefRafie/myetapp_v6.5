<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Carian Audit Trail</legend>
  <table width="100%">
    <tbody> <!--<td width="12">Penambahan admin no fail 5/1/2017</td>--> 
    <tr>
      <td width="29%" align="right" scope="row">No Fail</td>
      <td width="1%" scope="row">:</td>
      <td width="70%">
        <input size=50 type="text" value="$!NO_FAIL" name="NO_FAIL">
      </td>
    </tr><tr>
      <td width="29%" align="right" scope="row">Keterangan</td>
      <td width="1%" scope="row">:</td>
      <td width="70%">
        <input size=50 type="text" value="$!txtKeterangan" name="txtKeterangan">
      </td>
    </tr>
    <tr>
      <td align="right" scope="row" valign="top">Ketegori</td>
      <td scope="row" valign="top">:</td>
      <td valign="top">
                            #set($jenis_log_list = ["Fail","Hakmilik","User","Pihak Berkepentingan"])
						     <select  class="autoselect" name="jenis_log" id="jenis_log" >
						   	 <option value="" $selected_jenis_log >Keseluruhan</option>
						   		#foreach ( $y in $jenis_log_list )
						   		#if( $y == $!jenis_log )
						   			#set ( $selected_jenis_log = "selected" )
						   		#else
						   			#set ( $selected_jenis_log = "" )
						   		#end                       
						   	<option value="$y" $selected_jenis_log >$y</option>
						   		#end
							</select>
         
      </td>
    </tr>
    
    <tr>
      <td align="right" scope="row" valign="top">Jenis Aktiviti</td>
      <td scope="row" valign="top">:</td>
      <td valign="top">
                          
                            #set($jenis_aktiviti_id = ["INS","DEL","UPD"])
						     <select  class="autoselect" name="jenis_aktiviti" id="jenis_aktiviti" >
						   	 <option value="" $selected_jenis_aktiviti >Keseluruhan</option>
						   		#foreach ( $x in $jenis_aktiviti_id )
						   		#if( $x == $!jenis_aktiviti )
						   			#set ( $selected_jenis_aktiviti = "selected" )
						   		#else
						   			#set ( $selected_jenis_aktiviti = "" )
						   		#end                       
						   	<option value="$x" $selected_jenis_aktiviti >
                            
                            
                            
                            #if($x == "INS")
                            Insert (Kemasukan)
                            #end
                            
                            #if($x == "DEL")
                            Delete (Hapus)
                            #end
                            
                            #if($x == "UPD")
                            Update (Kemaskini)
                            #end
                            
                            </option>
						   		#end
							</select>
         
      </td>
    </tr>
    
    
    
    
    <tr>
      <td align="right" scope="row">Modul</td>
      <td scope="row">:</td>
      <td>
      <input type="hidden"  name="id_status" id="id_status">
      <select name="id_seksyen" id="id_seksyen" > 
       <option value="" >Keseluruhan</option>
       #foreach($ja in $list_seksyen) 
       <option value="$!ja.id_seksyen" #if($ja.id_seksyen==$id_seksyen) selected="selected" #end>$!ja.seksyen</option>
       #end  
		</select>  

      </td>
    </tr>
    
    <tr style="display:none">
      <td align="right" scope="row">Urusan</td>
      <td scope="row">:</td>
      <td>
    
       <select name="id_suburusan" id="id_suburusan" > 
        <option value="" >Keseluruhan</option>
		#foreach($ja in $list_suburusan) 
		<option value="$!ja.id_suburusan" #if($ja.id_suburusan==$id_suburusan) selected="selected" #end>$!ja.kod_suburusan - $!ja.nama_suburusan</option>
		#end 
		</select>  
      </td>
    </tr>
    
    <tr>
      <td align="right" scope="row">IP Address</td>
      <td scope="row">:</td>
      <td>
        <input type="text" value="$!txtIP" name="txtIP">
      </td>
    </tr>
        <tr>
      <td align="right" scope="row">Nama Pengguna</td>
      <td scope="row">:</td>
      <td>
        <input type="text" value="$!txtUser" name="txtUser">
      </td>
    </tr>   
       <tr>
      <td align="right" scope="row">Tarikh Aktiviti</td>
      <td scope="row">:</td>
      <td>
        <input size=10 type="text" value="$!txtTarikhAktiviti" name="txtTarikhAktiviti">
        <a href="javascript:displayDatePicker('txtTarikhAktiviti',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>   
        
      </td>
    </tr>    
    
        <tr>
      <td align="right" scope="row">&nbsp;</td>
      <td scope="row">&nbsp;</td>
      <td>
        <input type="button" id="cmdCari" name="cmdCari" value="Cari" onClick="doDivAjaxCall$formname('div_ListAT','showSenaraiAT','');" >
	  <input type="button" id="cmdBatal" name="cmdBatal" value="Batal" onClick="document.getElementById('TARIKH_AKHIR_AT').value='';
      document.getElementById('TARIKH_MULA_AT').value='';document.getElementById('carianAT').value='';doDivAjaxCall$formname('div_ListAT','showSenaraiAT','');" >
		
        <input style="display:none" type="button" id="cmdCetak" name="cmdCetak" value="Cetak" onclick="doDivAjaxCall$formname('SenaraiForPrint','printAT','');"  />	
      </td>
    </tr>
  </tbody></table>
</fieldset>

</table>