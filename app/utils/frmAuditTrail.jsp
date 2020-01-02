
<fieldset>
  <legend><strong>Carian</strong></legend>
  
  
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
        
		<option value="$!ja.id_seksyen" #if($ja.id_seksyen==$id_seksyen) selected="selected" #end>
        <!-- :::: $!ja.id_seksyen $!ja.kod_seksyen - $!ja.seksyen -->
        #if($!ja.id_seksyen == 1)
        PENGURUSAN PENGAMBILAN TANAH
        #elseif($!ja.id_seksyen == 2)
        PEMBAHAGIAN PUSAKA KECIL
        #elseif($!ja.id_seksyen == 3)
        HARTA TANAH PERSEKUTUAN
        #elseif($!ja.id_seksyen == 4)
        PENGUATKUASA DAN HASIL PERSEKUTUAN
        #elseif($!ja.id_seksyen == 6)
        PENGURUSAN FAIL DAN DOKUMEN
        #end

        
        
        
        </option>
		
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
        <input type="button" onclick="cari()" value="Cari" id="cmdCari" name="cmdCari">
        <input type="button" value="Kosongkan" id="cmdKosongkan" name="cmdKosongkan"  onclick="kosongkan()">
      </td>
    </tr>
  </tbody></table>
</fieldset>

<fieldset>
  <legend><strong>Senarai</strong></legend>
  
<!-- Senarai -->
#if ($SenaraiFail.size() > 0)
#parse("app/utils/record_paging.jsp")
<table width="100%"  border="0">
<tr class="table_header">
	<td width="3%">Bil</td>
    <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
    <td width="12">No Fail</td>
    
	<td width="28%">Keterangan</td>
    <td width="10%">Jenis Aktiviti</td>
    <td width="20%">Modul</td>
	<td width="10%">IP Address</td>
	<td width="17%">Nama Pengguna</td>
	<td width="12">Masa Aktiviti</td>
    
   <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
    <td width="12">Tarikh Akhir Log Masuk</td>
  	<td width="12">Status</td>
   
</tr>
<!-- Table Content -->
#foreach ( $list in $SenaraiFail )
#set( $counter = $velocityCount )
#if ( ($counter % 2) == 0 )
    #set( $row = "row2" )
#else
    #set( $row = "row1" )
#end
#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
<tr class="$row">
<td valign="top">
$cnt
</td>

<td valign="top">$list.keterangan</td>
<td valign="top">

#if($list.jenis_aktiviti == "INS")
Insert (Kemasukan)
#end
                            
#if($list.jenis_aktiviti == "DEL")
Delete (Hapus)
#end
                            
#if($list.jenis_aktiviti == "UPD")
Update (Kemaskini)
#end

</td>
<td valign="top"><!-- $list.seksyen -->

		#if($list.ID_SEKSYEN == 1)
        PENGURUSAN PENGAMBILAN TANAH
        #elseif($list.ID_SEKSYEN == 2)
        PEMBAHAGIAN PUSAKA KECIL
        #elseif($list.ID_SEKSYEN == 3)
        HARTA TANAH PERSEKUTUAN
        #elseif($list.ID_SEKSYEN == 4)
        PENGUATKUASA DAN HASIL PERSEKUTUAN
        #elseif($list.ID_SEKSYEN == 6)
        PENGURUSAN FAIL DAN DOKUMEN
        #end


</td>
 <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
<td valign="top">$list.NO_FAIL</td>

<td valign="top">$list.ip_address</td>
<td valign="top">$list.user_name</td>
<td valign="top">$list.masa_aktiviti</td>

 <!--<td width="12">Penambahan admin 5/1/2017</td>--> 
<td valign="top">$list.dot</td>
<td valign="top">$list.flag_aktif</td>

</tr>
#end
<input type=hidden name=page value=$page>
</table>
#else
<div class="infox">Tiada Rekod</div>
#end
</fieldset>

<script>

function cari() {
	doAjaxCall${formname}('cari');
}

function kosongkan() {
	doAjaxCall${formname}('kosongkan');
}

</script>