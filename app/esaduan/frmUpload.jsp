 

 
 
 
#if($!flag_buka_upload == "yes")
<table width="100%" border="0" cellspacing="2" cellpadding="2"   >
  <tr>
    <td colspan="2">
    <fieldset >
     <table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_upload">
     <tr>
     <td width="2%" align="right"> </td>
     <td width="20%" align="right"><font color="red">*</font>Nama Dokumen</td>
     <td width="1%"></td>
     <td width="80%"><input name="tajuk" type="text" id="tajuk" size="50" maxlength="200"   value="$!tajuk" > </td>
     </tr>
     <tr>
      <td width="2%" align="right"> </td>
     <td  align="right"><font color="red">*</font>Dokumen</td>
     <td ></td>
     <td > #foreach( $i in [1..$num_files] )  #end
  	<input id="fileupload" name="fileupload" type=file size=50 value="$!fileupload" />
   <input type="button" name="cmdSimpan" id="cmdSimpan" value="Muatnaik" onClick="uploadDokumen('$!ID_PLA','$!ID_PLADOKUMEN')" ></td>
     </tr>
     </table>
   
    <!--
    
    <table width="70%" border="0" cellspacing="2" cellpadding="2">
    #if($listDokumen_aduan.size() > 0)
    <tr>
    <td colspan="5" align="right"><input name="cmdHapusDokumen" type="button" value="Hapus" onClick="hapusDokumen()"></td>
    </tr>
    #end
    <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="50%">Nama Dokumen</td>
    <td width="40%">Muat Turun</td>
     #if($listDokumen_aduan.size() > 0)
      <td width="5%">
     
      <div align="center">
      <input type="checkbox" name="all1" id="all1" onclick="doCheckAll1()" title="Semak untuk pilih semua" />
      </div>
      
      </td>
      #end
  </tr>
 
  
 #if($listDokumen_aduan.size() > 0)
  #foreach($list1 in $listDokumen_aduan)        
           
             #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
  <tr>  
    <td class="$row" >$list1.BIL</td>
  
    <td class="$row" >$list1.tajuk</td>
   
   
    <td class="$row"><a href="javascript:papar_Lampiran('$list1.id_esdokumen')"><font color="blue">$list1.nama_fail</font></a></td>   
    <td class="$row" ><div align="center">
     <input type="checkbox" name="ids1" id="ids1" onclick="doUpdateCheckAll1()" value="$list1.id_esdokumen" >
     </div></td>
  </tr>
  #end
  #else
  <tr>  
    <td colspan="5">Tiada Rekod</td>    
  </tr>
  #end
      </table>
      -->
      </fieldset>
        </td>
    </tr>
    </table>
      
    #if($!flag_simpan_doc == "yes")    
    #if($!id_statusesaduan_DB == "" || $!id_statusesaduan_DB == "16125")
    <script>
	window.location.hash='tajuk';
	goTo('tajuk');	
	</script>  
    #else
    <script>
	window.location.hash='table_atas';
	
	</script>
    #end
    
      
    #end
#end