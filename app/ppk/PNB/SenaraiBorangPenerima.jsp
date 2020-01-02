<tr>
<td></td>
<td colspan="10">
<fieldset class="classFade">
<legend>Senarai Penerima 
#if($USER_ROLE=="user_pnb")
<input type="button" id="cmdSavePenerima" name="cmdSavePenerima" value="Simpan" 
onClick="if(valSenaraiPenerima('$ID_BORANGPNB')==true){doDivAjaxCall$formname('div_rowBorangPenerima$ID_BORANGPNB','simpanBorangPenerima','ID_BORANGPNB=$ID_BORANGPNB&BIL=$BIL&rowCss=$rowCss');}" >
<input type="button" id="cmdBatalPenerima" name="cmdBatalPenerima" value="Batal" 
onClick="doDivAjaxCall$formname('div_rowBorangPenerima$ID_BORANGPNB','batalBorangPenerima','ID_BORANGPNB=$ID_BORANGPNB&BIL=$BIL&rowCss=$rowCss');" >
#end
</legend>
<table border="0" cellspacing="1" cellpadding="1" width="100%" >


<tr class="table_header" >
		   <td   align="center" valign="top" width="3%" >Bil.</td>
		   <td   align="left" valign="top" width="15%" >Nama</td>
		   <td   align="left" valign="top" width="10%" >Taraf</td>
           <td   align="left" valign="top" width="34%" >Alamat</td>
           
<!-- TAMBAH NO SURAT DAFTAR -->  
		   <td   align="left" valign="top" width="18%" >Status & Tarikh Hantar & No Daftar Surat
           #if($USER_ROLE=="user_pnb")
           <br />
   		   <table  width="100%" border="0" cellpadding="1" cellspacing="1" >
           <tr>
         
       		<td  valign="top" width="10%" align="center">
            <span id="show_CHECK_HANTAROB_ALL$ID_BORANGPNB" >
            <input type="checkbox"  onClick="doCheckUpdatePenerimaAll('$ID_BORANGPNB',this,'HANTAROB');"  name="CHECK_HANTAROB_ALL$ID_BORANGPNB" id="CHECK_HANTAROB_ALL$ID_BORANGPNB" value="$ID_BORANGPNB" >
            </span>
            <td valign="top" align="left">
           <span id="show_TARIKH_HANTAROB_ALL$ID_BORANGPNB" style="display:none">
           <input name="TARIKH_HANTAROB_ALL$ID_BORANGPNB"  id="TARIKH_HANTAROB_ALL$ID_BORANGPNB"
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_TARIKH_HANTAROB_ALL$ID_BORANGPNB');doCopyPenerimaAll_TARIKH(this.value,'$ID_BORANGPNB','HANTAROB');" 
				onFocus="checkFormatDate_V3(this,this.value,'span_TARIKH_HANTAROB_ALL$ID_BORANGPNB');doCopyPenerimaAll_TARIKH(this.value,'$ID_BORANGPNB','HANTAROB');" 
				type="text" style="text-transform:uppercase;" value="" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_HANTAROB_ALL$ID_BORANGPNB',false,'dmy');" title="Pilih Tarikh"><img border="0" src="../img/calendar.gif"/></a> 
       <span style="cursor:pointer"  onClick="document.getElementById('TARIKH_HANTAROB_ALL$ID_BORANGPNB').value='';checkFormatDate_V3(document.getElementById('TARIKH_HANTAROB_ALL$ID_BORANGPNB'),document.getElementById('TARIKH_HANTAROB_ALL$ID_BORANGPNB').value,'span_CHECK_TARIKH_HANTAROB_ALL$ID_BORANGPNB');doCopyPenerimaAll_TARIKH(document.getElementById('TARIKH_HANTAROB_ALL$ID_BORANGPNB').value,'$ID_BORANGPNB','HANTAROB');checkMandotoryPenerimaAll_TARIKH('$ID_BORANGPNB','HANTAROB');" title="Kosongkan Kesemua Tarikh" ><img border="0" src="../img/delete.gif"/></span> 
       <br />
       <font color = "white">Ini Adalah Tarikh Serahan</font>
      <span id="span_CHECK_TARIKH_HANTAROB_ALL$ID_BORANGPNB" style="background-color:white">
      <input type='hidden' id='CHECK_TARIKH_HANTAROB_ALL$ID_BORANGPNB' name='CHECK_TARIKH_HANTAROB_ALL$ID_BORANGPNB' value='true' >
       </span>
      </span>
           
           </td>
           </tr>
           <tr>
         <td></td>
           <td valign="top" align="left">
           <span id="show_NOSURAT_HANTAROB_ALL$ID_BORANGPNB" style="display:none" >
            <input type="text" id="NOSURAT_HANTAROB_ALL$ID_BORANGPNB" style="text-transform:uppercase;width:100%"  name="NOSURAT_HANTAROB_ALL$ID_BORANGPNB"  
      onkeyup="doCopyPenerimaAll_NOSURAT(this.value,'$ID_BORANGPNB','HANTAROB');"
      onblur="doCopyPenerimaAll_NOSURAT(this.value,'$ID_BORANGPNB','HANTAROB');"
      placeholder="NO SURAT DAFTAR" value="">
      </span>
           </td>
           </tr>
           </table>
           #end
           
           
           </td>
           
 <!-- TUTOP TAMBAH CATATAN NO SURAT DAFTAR -->      
     
           <td   align="left" valign="top" width="18%">Status, Tarikh & Catatan Pemulangan
           #if($USER_ROLE=="user_pnb")
           <br />
           
           <table  width="100%" border="0" cellpadding="1" cellspacing="1" >
           <tr>
          <td  valign="top" width="10%" align="center">
           <span id="show_CHECK_RETURN_ALL$ID_BORANGPNB" >
            <input  type="checkbox"  onClick="doCheckUpdatePenerimaAll('$ID_BORANGPNB',this,'RETURN');"  name="CHECK_RETURN_ALL$ID_BORANGPNB" id="CHECK_RETURN_ALL$ID_BORANGPNB" value="$ID_BORANGPNB" >
            </span>
           </td> 
           <td valign="top" align="left">
           <span id="show_TARIKH_RETURN_ALL$ID_BORANGPNB" style="display:none">
           <input name="TARIKH_RETURN_ALL$ID_BORANGPNB"  id="TARIKH_RETURN_ALL$ID_BORANGPNB"
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_TARIKH_RETURN_ALL$ID_BORANGPNB');doCopyPenerimaAll_TARIKH(this.value,'$ID_BORANGPNB','RETURN');" 
					onFocus="checkFormatDate_V3(this,this.value,'span_TARIKH_RETURN_ALL$ID_BORANGPNB');doCopyPenerimaAll_TARIKH(this.value,'$ID_BORANGPNB','RETURN');" 
				type="text" style="text-transform:uppercase;" value="" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_RETURN_ALL$ID_BORANGPNB',false,'dmy');" title="Pilih Tarikh"><img border="0" src="../img/calendar.gif"/></a> 
       <span style="cursor:pointer"  onClick="document.getElementById('TARIKH_RETURN_ALL$ID_BORANGPNB').value='';checkFormatDate_V3(document.getElementById('TARIKH_RETURN_ALL$ID_BORANGPNB'),document.getElementById('TARIKH_RETURN_ALL$ID_BORANGPNB').value,'span_CHECK_TARIKH_RETURN_ALL$ID_BORANGPNB');doCopyPenerimaAll_TARIKH(document.getElementById('TARIKH_RETURN_ALL$ID_BORANGPNB').value,'$ID_BORANGPNB','RETURN');checkMandotoryPenerimaAll_TARIKH('$ID_BORANGPNB','RETURN');" title="Kosongkan Kesemua Tarikh" ><img border="0" src="../img/delete.gif"/></span> 
       <br />
      <span id="span_CHECK_TARIKH_RETURN_ALL$ID_BORANGPNB" style="background-color:white">
      <input type='hidden' id='CHECK_TARIKH_RETURN_ALL$ID_BORANGPNB' name='CHECK_TARIKH_RETURN_ALL$ID_BORANGPNB' value='true' >
      </span>
      </span>
           
           </td>
           </tr>
           <tr>
           <td></td>
          <td valign="top" align="left">
           <span id="show_CATATAN_RETURN_ALL$ID_BORANGPNB" style="display:none">
            <input type="text" id="CATATAN_RETURN_ALL$ID_BORANGPNB" style="text-transform:uppercase;width:100%"  name="CATATAN_RETURN_ALL$ID_BORANGPNB"  
      onkeyup="doCopyPenerimaAll_CATATAN(this.value,'$ID_BORANGPNB','RETURN');"
      onblur="doCopyPenerimaAll_CATATAN(this.value,'$ID_BORANGPNB','RETURN');"
      value="">
      </span>
           </td>
           </tr>
           </table>
           #end
           
           
           </td>
</tr>
#if($listBorangPenerima.size()>0)	
		#foreach($LBP in $listBorangPenerima)
        <tr  id="div_ViewBorangPenerima$LBP.ID_BORANGPNBOB" >
		   <td class="$LBP.rowCssSUB"   align="center" valign="top" width="3%" >$LBP.BILSUB</td>
		   <td class="$LBP.rowCssSUB"   align="left" valign="top" >
           #set($spansub1 = "spansub1listBorangPenerima"+$LBP.BILSUB)
		   <span id="$spansub1"> 
           $LBP.NAMA
           </span> 
           #if($FLAG_PNB_CARIAN=="Y")          
           <script>
		   highlight_item(document.getElementById("CARIAN_NAMA_PENERIMA").value,'$spansub1');
           </script> 
           #end
           </td>
		   <td class="$LBP.rowCssSUB"   align="left" valign="top" >$LBP.TARAF</td>
           <td class="$LBP.rowCssSUB"   align="left" valign="top" >
           
           #set($spansub2 = "spansub2listBorangPenerima"+$LBP.BILSUB)
		   <span id="$spansub2"> 
           #if($LBP.ALAMAT_1!="")
           $LBP.ALAMAT_1,
           #end
           #if($LBP.ALAMAT_2!="")
           $LBP.ALAMAT_2,
           #end
           #if($LBP.ALAMAT_3!="")
           $LBP.ALAMAT_3,
           #end           
           
           #if($LBP.POSKOD!="" || $LBP.BANDAR!="" || $LBP.NEGERI!="")
           <br />          
           $LBP.POSKOD
           $LBP.BANDAR
           $LBP.NEGERI
           #end
           
           </span> 
           #if($FLAG_PNB_CARIAN=="Y")              
           <script>
		   highlight_item(document.getElementById("CARIAN_ALAMAT_PENERIMA").value,'$spansub2');
           </script> 
           #end
           
           </td>
<!-- TAMBAH NO SURAT DAFTAR 25/5 -->
		   <td class="$LBP.rowCssSUB"   align="left" valign="top" >
           #if($USER_ROLE=="user_pnb")
           <table  width="100%" border="0" cellpadding="1" cellspacing="1" >
           <tr>
           <td  valign="top" width="10%" align="center">
            <span id="show_CHECK_HANTAROB$LBP.ID_BORANGPNBOB">
           <input #if($LBP.FLAG_HANTAROB=='Y') checked #end type="checkbox"  onClick="doCheckUpdatePenerima('$LBP.ID_BORANGPNB','HANTAROB');"  name="CHECK_HANTAROB$LBP.ID_BORANGPNB" id="CHECK_HANTAROB$LBP.ID_BORANGPNB" value="$LBP.ID_BORANGPNBOB" >
	   </span>
           </td>
           <td  valign="top"  align="left">

<span id="show_TARIKH_HANTAROB$LBP.ID_BORANGPNBOB" style="display:none">
           <input name="TARIKH_HANTAROB$LBP.ID_BORANGPNBOB"  id="TARIKH_HANTAROB$LBP.ID_BORANGPNBOB"
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_TARIKH_HANTAROB$LBP.ID_BORANGPNBOB');checkMandotoryPenerimaAll_TARIKH('$LBP.ID_BORANGPNB','HANTAROB');" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_TARIKH_HANTAROB$LBP.ID_BORANGPNBOB');checkMandotoryPenerimaAll_TARIKH('$LBP.ID_BORANGPNB','HANTAROB');" 
				type="text" style="text-transform:uppercase;" value="$LBP.TARIKH_HANTAROB" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_HANTAROB$LBP.ID_BORANGPNBOB',false,'dmy');" title="Pilih Tarikh"><img border="0" src="../img/calendar.gif"/></a> 
       <span style="cursor:pointer"  onClick="document.getElementById('TARIKH_HANTAROB$LBP.ID_BORANGPNBOB').value='';checkFormatDate_V3(document.getElementById('TARIKH_HANTAROB$LBP.ID_BORANGPNBOB'),document.getElementById('TARIKH_HANTAROB$LBP.ID_BORANGPNBOB').value,'span_CHECK_TARIKH_HANTAROB$LBP.ID_BORANGPNBOB');checkMandotoryPenerimaAll_TARIKH('$LBP.ID_BORANGPNB','HANTAROB');" title="Kosongkan Tarikh" ><img border="0" src="../img/delete.gif"/></span> 
       <br />
      <span id="span_CHECK_TARIKH_HANTAROB$LBP.ID_BORANGPNBOB">
      <input type='hidden' id='CHECK_TARIKH_HANTAROB$LBP.ID_BORANGPNBOB' name='CHECK_TARIKH_HANTAROB$LBP.ID_BORANGPNBOB' value='true' >
      </span>

      </span>   
	</td>
   	</tr> 
           <tr>
           <td valign="top"></td>
           <td valign="top" align="left">
            <span id="show_NOSURAT_HANTAROB$LBP.ID_BORANGPNBOB" style="display:none" >
           <input type='text' id="NOSURAT_HANTAROB$LBP.ID_BORANGPNBOB" style="text-transform:uppercase;width:100%"  name="NOSURAT_HANTAROB$LBP.ID_BORANGPNBOB"  value="$LBP.NOSURAT_HANTAROB" placeholder="NO SURAT DAFTAR" >    
      </span>
      
           </td>
           </tr>
          
           </table> 
      #else
      
      #if($LBP.FLAG_HANTAROB=='Y')
      DIHANTAR PADA $LBP.TARIKH_HANTAROB
      #if($LBP.NOSURAT_HANTAROB != "")
              <br />
      NOSURAT : $LBP.NOSURAT_HANTAROB
      #end
      #else
      -
      #end
      
      #end
      
           </td>

<!-- TUTOP TAMBAH NO SURAT DAFTAR 25/5 -->
           
           <td class="$LBP.rowCssSUB"   align="left" valign="top" >
           #if($USER_ROLE=="user_pnb")
           <table  width="100%" border="0" cellpadding="1" cellspacing="1" >
           <tr>
           <td  valign="top" width="10%" align="center">
           
           <span id="show_CHECK_RETURN$LBP.ID_BORANGPNBOB" style="display:none">
           <input  #if($LBP.FLAG_RETURN=='Y') checked #end type="checkbox"  onClick="doCheckUpdatePenerima('$LBP.ID_BORANGPNB','RETURN');"  name="CHECK_RETURN$LBP.ID_BORANGPNB" id="CHECK_RETURN$LBP.ID_BORANGPNB" value="$LBP.ID_BORANGPNBOB" >
           </span>
           
           </td>
           <td  valign="top"  align="left">
           
           <span id="show_TARIKH_RETURN$LBP.ID_BORANGPNBOB" style="display:none">
           <input name="TARIKH_RETURN$LBP.ID_BORANGPNBOB"  id="TARIKH_RETURN$LBP.ID_BORANGPNBOB"
				onKeyUp="checkFormatDate_V3(this,this.value,'span_CHECK_TARIKH_RETURN$LBP.ID_BORANGPNBOB');checkMandotoryPenerimaAll_TARIKH('$LBP.ID_BORANGPNB','RETURN');" 
					onFocus="checkFormatDate_V3(this,this.value,'span_CHECK_TARIKH_RETURN$LBP.ID_BORANGPNBOB');checkMandotoryPenerimaAll_TARIKH('$LBP.ID_BORANGPNB','RETURN');" 
				type="text" style="text-transform:uppercase;" value="$LBP.TARIKH_RETURN" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_RETURN$LBP.ID_BORANGPNBOB',false,'dmy');" title="Pilih Tarikh"><img border="0" src="../img/calendar.gif"/></a> 
       <span style="cursor:pointer"  onClick="document.getElementById('TARIKH_RETURN$LBP.ID_BORANGPNBOB').value='';checkFormatDate_V3(document.getElementById('TARIKH_RETURN$LBP.ID_BORANGPNBOB'),document.getElementById('TARIKH_RETURN$LBP.ID_BORANGPNBOB').value,'span_CHECK_TARIKH_RETURN$LBP.ID_BORANGPNBOB');checkMandotoryPenerimaAll_TARIKH('$LBP.ID_BORANGPNB','RETURN');" title="Kosongkan Tarikh" ><img border="0" src="../img/delete.gif"/></span> 
      <br />
      <span id="span_CHECK_TARIKH_RETURN$LBP.ID_BORANGPNBOB" style="display:none">
      <input type='hidden' id='CHECK_TARIKH_RETURN$LBP.ID_BORANGPNBOB' name='CHECK_TARIKH_RETURN$LBP.ID_BORANGPNBOB' value='true' >
      </span>
      
          </span>
           </td>
           </tr>
           <tr>
           <td valign="top"></td>
           <td valign="top" align="left">
            <span id="show_CATATAN_RETURN$LBP.ID_BORANGPNBOB" style="display:none">
           <input type='text' id="CATATAN_RETURN$LBP.ID_BORANGPNBOB" style="text-transform:uppercase;width:100%"  name="CATATAN_RETURN$LBP.ID_BORANGPNBOB"  value="$LBP.CATATAN_RETURN" >      
      </span>
           </td>
           </tr>
           </table>
           #else
           
            #if($LBP.FLAG_RETURN=='Y')
              DIPULANGKAN PADA $LBP.TARIKH_RETURN
              #if($LBP.CATATAN_RETURN != "")
              <br />
              CATATAN : $LBP.CATATAN_RETURN
              #end
            #else
              -
            #end
           
           #end
           
           
           </td>
		</tr>
        #end        
#else
<tr >
		   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
		     
</tr>

#end         




</table>
</fieldset>
<BR />
</td>
</tr>


#if($USER_ROLE=="user_pnb")
<script>
doCheckUpdatePenerima('$ID_BORANGPNB','HANTAROB');
doCheckUpdatePenerima('$ID_BORANGPNB','RETURN');

</script>
#end

<script>
document.getElementById("HID_OPENCLOSE_ROWBORANG$ID_BORANGPNB").value = '$HID_OPENCLOSE_ROWBORANG';
$jquery(document).ready(function () {
				doDivAjaxCall$formname('div_rowBorang$ID_BORANGPNB','showViewBorang','BIL=$BIL&rowCss=$rowCss&ID_BORANGPNB=$ID_BORANGPNB&HID_OPENCLOSE_ROWBORANG='+$jquery('#HID_OPENCLOSE_ROWBORANG$ID_BORANGPNB').val());			 	  
			  });
</script>