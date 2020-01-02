<style type="text/css">
<!--
.style2 {	font-size: x-small;
	font-style: italic;
	color: #0000FF;
}
.style1 {color: #0000FF}
-->
<!--
.pautanms {color: #0000FF}
-->
</style>
<table width="98%" border="0">
	<tr>	
		<td>
			&nbsp;
		</td>
	</tr>

	<tr>
		<td>

			<input name="idHakmilik" type="hidden" value="$idHakmilik" />
			<input name="mode" type="hidden" value="$mode" />
			<fieldset><legend>MAKLUMAT HAKMILIK</legend>
				<table width="100%" border="0">				
					<tr>
					    <td>
						##parse("app/htp/rekod/frmPendaftaranHakmilikMaklumatFailReadOnly.jsp")
						</td>
					</tr>
	  				<tr>
				    	<td>
						#parse("app/htp/rekod/frmPendaftaranMaklumatHakmilikReadOnly.jsp")
				    	</td>
				  	</tr>
				</table>
			</fieldset>

<!-- <table width="99%" border="0">
  <tr>
    <td> -->

    		<fieldset> <legend>MAKLUMAT PERGERAKAN</legend>
			    <table width="100%" border="0">
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'view') * #end </span></td>				        
				        <td valign="top" width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Jenis Dokumen</div>
				        	</div>
				       	</td>				      	
				      	<td valign="top" width="1%">:</td>				        
				        <td width="68%">
							#set($checked4 ="4")
					        #set($checkedH ="HG")
					        #set($checked3 ="")
							#if($sorDokumen =="4")
					          	#set($checked4 ="checked")
					         	#set($checkedH ="")
					            #set($checked3 ="")
					        #elseif($sorDokumen =="HG")
					         	#set($checkedH ="checked")
					         	#set($checked4 ="")
					         	#set($checked3 ="")
					        #elseif($sorDokumen =="3")
					         	#set($checked3 ="checked")
					            #set($checkedH ="")
					            #set($checked4 ="")
					        #end
					          <input name="sorDokumen" type="radio" id="sorDokumen" value="HG" $checkedH $disabled/> Hakmilik <br />
					 		 <!--  <input type="radio" name="sorDokumen" id="sorDokumen" value="4" $checked4 $disabled/> KAD 284 <br />
					          <input type="radio" name="sorDokumen" id="sorDokumen" value="3" $checked3 $disabled/> KAD 283 <br /> -->
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'view') * #end </span></td>				        
				        <td valign="top" width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Urusan</div>
				        	</div>
				       	</td>				      	
				      	<td valign="top" width="1%">:</td>				        
				        <td width="68%">
				        	<input name="txtKeterangan" type="text" id="txtKeterangan" value="$txtKeterangan" size="50" $!readonly class="$disabled" style="text-transform:uppercase;"/>
				        </td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'view') * #end </span></td>				        
				        <td valign="top" width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Kepada</div>
				        	</div>
				       	</td>				      	
				      	<td valign="top" width="1%">:</td>				        
				        <td width="68%">
			          <input name="txtKepada" type="text" id="txtKepada" value="$txtKepada" size="30" maxlength="59"  $!readonly class="$disabled" style="text-transform:uppercase;"/>
				        </td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'view') * #end </span></td>				        
				        <td valign="top" width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Serahan</div>
				        	</div>
				       	</td>				      	
				      	<td valign="top" width="1%">:</td>				        
				        <td width="68%">
							<input name="txdTarikhSerah" type="text" id="txdTarikhSerah" value="$txdTarikhSerah" size="9" $!readonly class="$disabled" onblur="check_date(this)"/>
						#if ($mode != 'view')
			            	<a href="javascript:displayDatePicker('txdTarikhSerah',false,'dmy');"> 
			            		<img border="0" src="../img/calendar.gif"/></a> 
			           	#end
			            		<span class="style2"> dd/mm/yyyy </span>
						</td>
					</tr>			   
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'view') * #end </span></td>				        
				        <td valign="top" width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Status Serahan</div>
				        	</div>
				       	</td>				      	
				      	<td valign="top" width="1%">:</td>				        
				        <td width="68%">
							<select name="socStatusS" id="socStatusS" style="width:200px;" $!readonly class="$disabled" $disabled >
					          
					         #if($socStatusS == 'D')
					          
					          <option value="">SILA PILIH</option>
					          <option value="D" selected="selected"> D - DALAMAN</option>
					          <option value="L"> L - LUARAN</option>
					          
					         #elseif ($socStatusS == 'L') 
					          
					          <option value="">SILA PILIH</option>
					          <option value="D"> D - DALAMAN</option>
					          <option value="L"selected="selected"> L - LUARAN</option>
					          
					         #else  
					          
					          <option value="" selected="selected">SILA PILIH</option>
					          <option value="D"> D - DALAMAN</option>
					          <option value="L"> L - LUARAN</option>
					          
					         #end
					        
					        </select>
			        	</td>
					</tr>	

                	<tr>
						<td valign="top" width="1%">
				        </td>				        
				        <td valign="top" width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Kembali</div>
				        	</div>
				       	</td>				      	
				      	<td valign="top" width="1%">:</td>				        
				        <td width="68%">
							<input name="txdTarikhKembali" type="text" id="txdTarikhKembali" value="$txdTarikhKembali" size="9" $!readonly class="$disabled" onblur="check_date(this)"/>
			        	#if ($mode != 'view')
			          			<a href="javascript:displayDatePicker('txdTarikhKembali',false,'dmy');">
			          				<img border="0" src="../img/calendar.gif"/></a> 
			          	#end
			          				<span class="style2"> dd/mm/yyyy </span>
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        </td>				        
				        <td valign="top" width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tajuk</div>
				        	</div>
				       	</td>				      	
				      	<td valign="top" width="1%">:</td>				        
				        <td width="68%">
				        	<textarea name="txtajukper" cols="47" rows="5" class="$disabled" $!readonly onkeyup="textCounter(this.form.txtajukper,this.form.remtxtajuk,250);" onkeydown="textCounter(this.form.txtajukper,this.form.remtxtajuk,250);" >$!txTajukPer</textarea>
				        </td>
					</tr>
					
				#if ($mode != 'view')
					<tr>
				        <td>&nbsp;</td>
				        <td>&nbsp;</td>
				        <td width="1%" valign="top">&nbsp;</td>
				        <td><input type="text" readonly class="disabled" name="remtxtajuk" size="3" maxlength="4" value="250"> Baki Aksara </td>
				    </tr>	
				#end								
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory"></span></td>				        
				        <td valign="top" width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Fail</div>
				        	</div>
				       	</td>				      	
				      	<td valign="top" width="1%">:</td>				        
				        <td width="68%">
				        	<input type="text" name="txtfailrujukanper" value="$!failRujukanPer" size="50" maxlength="49" $!readonly class="$disabled" style="text-transform:uppercase;"/>
				        </td>
					</tr>							
                	<tr>
						<td valign="top" width="1%">
				        </td>				        
				        <td valign="top" width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Catatan</div>
				        	</div>
				       	</td>				      	
				      	<td valign="top" width="1%">:</td>				        
				        <td width="68%">
				        	<textarea name="txtCatatan" cols="47" rows="5" class="$disabled" $!readonly id="txtCatatan" onkeyup="textCounter(this.form.txtCatatan,this.form.remTxtCatatan,250);" onkeydown="textCounter(this.form.txtCatatan,this.form.remTxtCatatan,250);" >$txtCatatan</textarea>
				        </td>
					</tr>
					
				#if ($mode != 'view')
					<tr>
				        <td>&nbsp;</td>
				        <td>&nbsp;</td>
				        <td width="1%" valign="top">&nbsp;</td>
				        <td><input type="text" readonly class="disabled" name="remTxtCatatan" size="3" maxlength="4" value="250"> Baki Aksara </td>
				    </tr>	
				#end
			    </table>
			    <label></label>
			    </fieldset>
 <!--   </td>
  </tr>
</table> -->

		</td>
	</tr>
	#if ($mode != 'view')
      <tr>
        <td>
   			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
        </td>
      </tr>
	#end
	<tr>
		<td>
			<div align="center">
		  	#if ($mode =='new')
		    	<input type="button" class="stylobutton100" name="btnAddHakmilik" id="btnAddHakmilik" value="Simpan" onclick="tambahPergerakanDetail($idHakmilik)"/>
		    	<input type="button" class="stylobutton100" name="btnBackPergerakan" id="btnBackPergerakan" value="Batal" onclick="kembaliSenaraiPergerakan()"/>
		    #end
		  	#if ($mode == 'update')
		    	<input type="button" class="stylobutton100" name="btnUpdateHakmilik" id="btnUpdateHakmilik" value="Simpan" onclick="updatePergerakanDetail($idPergerakan)"/>
		    	<input type="button" class="stylobutton100" name="btnBackPergerakan" value="Batal" onclick="batalKemaskini($idPergerakan)"/>
		    #end
		  	#if ($mode == 'view')
		    	<input type="button" class="stylobutton100" name="btnKemaskiniHakmilik" id="btnKemaskiniHakmilik" value="Kemaskini" onclick="kemaskiniPergerakanDetail($idPergerakan)"/>
		    	<!--modified by rosli 20100728-->
		    	<input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" onclick="cetakPergerakan($idPergerakan);" />
		    	<input type="button" class="stylobutton100" name="btnBackPergerakan" id="btnBackPergerakan" value="Kembali" onclick="kembaliSenaraiPergerakan()"/>
		    #end
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<fieldset id="tableReport1" style="display:none;">
			<legend>SENARAI LAPORAN</legend>
			<table width="100%" border="0">
		  	<tr>
		   	 <td><a href="javascript:cetakPergerakan($idPergerakan);" class="style1">Borang Pergerakan Dokumen</a></td>
		  	</tr>
			</table>
			</fieldset>			
		</td>
	</tr>
	
</table>
<script>
	
	function XtambahPergerakanDetail(idhakmilik){
	//VALIDATAION
	 if(document.${formName}.sorDokumen.checked == false){ 
		alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.'); 
		document.${formName}.sorDokumen.focus();
		return; 
	 }
	 if(document.${formName}.txtKeterangan.value == ""){ 
		alert('Sila masukkan " Urusan " terlebih dahulu.'); 
		document.${formName}.txtKeterangan.focus();
		return; 
	 }
	 if(document.${formName}.txtKepada.value == ""){ 
		alert('Sila masukkan " Kepada " terlebih dahulu.'); 
		document.${formName}.txtKepada.focus();
		return; 
	 }
	 if(document.${formName}.txdTarikhSerah.value == ""){ 
		alert('Sila masukkan " Tarikh Serah" terlebih dahulu.'); 
		document.${formName}.txdTarikhSerah.focus(); 
		return; 
	 }	
	 if(document.${formName}.socStatusS.value == ""){
		alert('Sila masukkan " Status Pinjaman " terlebih dahulu.'); 
		document.${formName}.socStatusS.focus(); 
		return; 
	 }
	  var str1 = document.${formName}.txdTarikhSerah.value; 
	  var dt1 = parseInt(str1.substring(0,2),10); 
	  var mon1 = parseInt(str1.substring(3,5),10)-1; 
	  var yr1 = parseInt(str1.substring(6,10),10);
	  var tarikhSerah = new Date(yr1, mon1, dt1);
	  var str2 = document.${formName}.txdTarikhKembali.value; 
	  var dt2 = parseInt(str2.substring(0,2),10); 
	  var mon2 = parseInt(str2.substring(3,5),10)-1; 
	  var yr2 = parseInt(str2.substring(6,10),10); 
	  var tarikhKembali = new Date(yr2, mon2, dt2); 
	  var currentDate = new Date(); 
	  if (tarikhSerah > currentDate){ 
	  	 alert('Tarikh Serah tidak boleh melebihi dari tarikh hari ini.'); 
	   	 document.${formName}.txdTarikhSerah.focus(); return; 
	  } 
	  if (tarikhKembali > currentDate){ 
	   	  alert('Tarikh Kembali tidak boleh melebihi dari tarikh hari ini.'); 
	   	  document.${formName}.txdTarikhKembali.focus(); return; 
	  } 
	  if (tarikhSerah > tarikhKembali){ 
	      alert('Tarikh Kembali mestilah melebihi dari Tarikh Serah.'); 
	     document.${formName}.txdTarikhKembali.focus(); return; 
	  }
	 //END OF VALIDATION
	  document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=tambahPergerakanHakmilikDetail";
	  document.${formName}.submit();
	}
	
	function XkemaskiniPergerakanDetail(id){
	  document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=kemaskiniPergerakanHakmilikDetail&idPergerakan="+id;
	  document.${formName}.submit();
	}
	function XupdatePergerakanDetail(id){
	//VALIDATAION
	 if(document.${formName}.sorDokumen.checked == false){ 
		alert('Sila masukkan " Jenis Dokumen " terlebih dahulu.'); 
		document.${formName}.sorDokumen.focus();
		return; 
	 }
	 if(document.${formName}.txtKeterangan.value == ""){ 
		alert('Sila masukkan " Urusan " terlebih dahulu.'); 
		document.${formName}.txtKeterangan.focus();
		return; 
	 }
	 if(document.${formName}.txtKepada.value == ""){ 
		alert('Sila masukkan " Kepada " terlebih dahulu.'); 
		document.${formName}.txtKepada.focus();
		return; 
	 }
	 if(document.${formName}.txdTarikhSerah.value == ""){ 
		alert('Sila masukkan " Tarikh Serah" terlebih dahulu.'); 
		document.${formName}.txdTarikhSerah.focus(); 
		return; 
	 }	
	 if(document.${formName}.socStatusS.value == ""){
		alert('Sila masukkan " Status Pinjaman " terlebih dahulu.'); 
		document.${formName}.socStatusS.focus(); 
		return; 
	 }
	   var str1 = document.${formName}.txdTarikhSerah.value; 
	  var dt1 = parseInt(str1.substring(0,2),10); 
	  var mon1 = parseInt(str1.substring(3,5),10)-1; 
	  var yr1 = parseInt(str1.substring(6,10),10);
	  var tarikhSerah = new Date(yr1, mon1, dt1);
	  var str2 = document.${formName}.txdTarikhKembali.value; 
	  var dt2 = parseInt(str2.substring(0,2),10); 
	  var mon2 = parseInt(str2.substring(3,5),10)-1; 
	  var yr2 = parseInt(str2.substring(6,10),10); 
	  var tarikhKembali = new Date(yr2, mon2, dt2); 
	  var currentDate = new Date(); 
	  if (tarikhSerah > currentDate){ 
	  	 alert('Tarikh Serah tidak boleh melebihi dari tarikh hari ini.'); 
	   	 document.${formName}.txdTarikhSerah.focus(); return; 
	  } 
	  if (tarikhKembali > currentDate){ 
	   	  alert('Tarikh Kembali tidak boleh melebihi dari tarikh hari ini.'); 
	   	  document.${formName}.tarikhKembali.focus(); return; 
	  } 
	  if (tarikhSerah > tarikhKembali){ 
	      alert('Tarikh Kembali mestilah melebihi dari Tarikh Serah.'); 
	     document.${formName}.tarikhKembali.focus(); return; 
	  }
	 //END OF VALIDATION
	
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=updatePergerakanHakmilikDetail&idPergerakan="+id;	
		document.${formName}.submit();
	}
	function setTable(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}
	function XcetakPergerakan(id){
		var url = "../servlet/ekptg.report.htp.BorangPergerakanHakmilik?idPergerakan="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	function XkembaliSenaraiPergerakan(){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=paparDetailPergerakanHakmilik";
		document.${formName}.submit();
	}
	
</script>