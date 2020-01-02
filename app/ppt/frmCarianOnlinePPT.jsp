#set($ModuleName = "${session.getAttribute('_portal_module')}")

<fieldset>
  <legend><strong>CARIAN</strong></legend>
	<table width="100%" cellspacing="2" cellpadding="0" border="0">
   
    	<tr>
    		<td valign="top" width="1%">&nbsp;</td>
        	<td valign="top" width="18%">No.Rujukan/ No. Fail</td>
        	<td valign="top" width="1%" align="right">:</td>
            <td valign="top" width="80%"><input type="text" name="txtNoFailCarian" value="$!txtNoFailCarian" style="text-transform:uppercase;" id="txtNoFailCarian" size="35" onkeyup="this.value=this.value.toUpperCase();" />
            &nbsp;&nbsp;<a href="javascript:advanceSearch('advSearch')"><font color="blue">Carian Terperinci</font></a></td>
        </tr>
    </table>    

	<div id="advSearch" style=" display:none">

	<table width="100%" cellspacing="2" cellpadding="0" border="0">
		
		#if($ModuleName=="ekptg.view.ppt.FrmPermohonanUPTOnline")
		<tr>
        	<td valign="top" width="1%">&nbsp;</td>
        	<td valign="top" width="18%">Tarikh Permohonan KJP</td>
        	<td valign="top" width="1%" align="right">:</td>
            <td valign="top" width="80%"><input name="txdTarikhPermohonan" value="$!txdTarikhPermohonan" size="11" id="txdTarikhPermohonan" type="text" onblur="check_date(this)" />
       		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">$!frmtdate</td>
        </tr>
        #end
        
        <tr>
        	<td valign="top" width="1%">&nbsp;</td>
        	<td valign="top" width="18%">Status Permohonan</td>
        	<td valign="top" width="1%" align="right">:</td>
            <td valign="top" width="80%">$!selectStatusSPT</td>
        </tr>
        
        #if($ModuleName=="ekptg.view.ppt.FrmPermohonanUPTOnline")		
        <tr>
            <td valign="top" width="1%">&nbsp;</td>
            <td valign="top" width="18%">Urusan</td>
            <td valign="top" width="1%" align="right">:</td>
            <td valign="top" width="80%"><select name="sorUrusanCarian" style="auto">
      			
      			#if($sorUrusanCarian=="51")
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
				<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
				<option value="">SILA PILIH</option>
      			#elseif($sorUrusanCarian=="52")
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
				<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
				<option value="">SILA PILIH</option>
      			#elseif($sorUrusanCarian=="53")
      			<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
      			<option value="">SILA PILIH</option>
      			#else
      			<option value="">SILA PILIH</option>
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
				<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
      			#end
  
				</select></td>
            </tr>
            #end
            
            <tr>
         	  	<td valign="top" width="1%">&nbsp;</td>
           	   	<td valign="top" width="18%">No. / Bil. Permohonan</td>
           	   	<td valign="top" width="1%" align="right">:</td>
                <td valign="top" width="80%"><input type="text" name="txtBilPermohonanCarian" value="$!txtBilPermohonanCarian" style="text-transform:uppercase;" id="txtBilPermohonanCarian" size="15" onkeyup="this.value=this.value.toUpperCase();" /></td>
            </tr>
            
            <tr>
	        	<td valign="top" width="1%">&nbsp;</td>
	        	<td valign="top" width="18%">Negeri Permohonan</td>
	        	<td valign="top" width="1%" align="right">:</td>
	            <td valign="top" width="80%">$!selectNegeriCarian</td>
        	</tr>
        
        	<!-- <tr>
	        	<td valign="top" width="1%">&nbsp;</td>
	        	<td valign="top" width="18%">Nama Pihak Berkepentingan</td>
	        	<td valign="top" width="1%" align="right">:</td>
	            <td valign="top" width="80%"><input type="text" name="txtNamaPBCarian" value="$!txtNamaPBCarian" style="text-transform:uppercase;" id="txtNamaPBCarian" size="50" onkeyup="this.value=this.value.toUpperCase();" /></td>
        	</tr> -->
        	
        	<!-- <tr>
	        	<td valign="top" width="1%">&nbsp;</td>
	        	<td valign="top" width="18%">No. Pihak Berkepentingan</td>
	        	<td valign="top" width="1%" align="right">:</td>
	            <td valign="top" width="80%"><input type="text" name="txtNoPBCarian" value="$!txtNoPBCarian" style="text-transform:uppercase;" id="txtNoPBCarian" size="20" onkeyup="this.value=this.value.toUpperCase();" /><i><font color='red' style='font-size:10px'> cth: 700121034429/I88820</font></i></td>
        	</tr> -->
        	
        	<tr>
	        	<td valign="top" width="1%">&nbsp;</td>
	        	<td valign="top" width="18%">Jenis Hakmilik</td>
	        	<td valign="top" width="1%" align="right">:</td>
	            <td valign="top" width="80%">$!selectJenisHMCarian</td>
        	</tr>
        	
        	<tr>
	        	<td valign="top" width="1%">&nbsp;</td>
	        	<td valign="top" width="18%">No. Hakmilik</td>
	        	<td valign="top" width="1%" align="right">:</td>
	            <td valign="top" width="80%"><input type="text" name="txtNoHakmilikCarian" value="$!txtNoHakmilikCarian" style="text-transform:uppercase;" id="txtNoHakmilikCarian" size="15" onkeyup="this.value=this.value.toUpperCase();" />
	            <i><font color='red' style='font-size:10px'>(Tidak termasuk kod hakmilik)</font></i></td>
        	</tr>
        	
        	<tr>
	        	<td valign="top" width="1%">&nbsp;</td>
	        	<td valign="top" width="18%">No. Lot/PT</td>
	        	<td valign="top" width="1%" align="right">:</td>
	            <td valign="top" width="80%"><input type="text" name="txtNoLotCarian" value="$!txtNoLotCarian" style="text-transform:uppercase;" id="txtNoLotCarian" size="15" onkeyup="this.value=this.value.toUpperCase();" /></td>
        	</tr>
        	
        	<tr>
         	  	<td valign="top" width="1%">&nbsp;</td>
           	   	<td valign="top" width="18%">Nama Projek</td>
           	   	<td valign="top" width="1%" align="right">:</td>
                <td valign="top" width="80%"><textarea name="txtTujuanCarian" id="txtTujuanCarian" cols="40%" rows="4" >$!txtTujuanCarian</textarea></td>
            </tr>
            
        
    </table>
        
</div>    
	
	<table width="100%" cellspacing="2" cellpadding="0" border="0">
    	<tr>
        	<td width="1%">&nbsp;</td>
        	<td width="18%">&nbsp;</td>
        	<td width="1%">&nbsp;</td>
        	<td width="80%">
            	&nbsp;&nbsp;<input name="cari" value="Cari" type="button" onclick="javascript:search_data()" />
  				<input type=button value = "Kosongkan" onClick="javascript:clearData();">
            </td>
        </tr>
    </table>

</fieldset>

<script>
function advanceSearch(id){
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
