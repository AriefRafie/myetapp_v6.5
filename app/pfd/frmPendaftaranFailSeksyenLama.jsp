<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {
	font-size: 9px;
	font-style: italic;
}
-->
</style>

<body onLoad="ResetScrollPosition();">
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" >FAIL LAMA</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tabFailSeksyenBaru()">FAIL BARU</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="tabFailSeksyenArkib()">FAIL ARKIB</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
    <fieldset>
			<legend>MAKLUMAT FAIL LAMA</legend>
            #if($Flag_failWujud == 'true')
  			<div class="error">No Fail telah didaftarkan.</div>
 			#end
            #if($hapus == 'true')
  			<div class="success">Fail telah dihapus.</div>
 			#end
                   <table width="100%">
            <tr>
              <td width="2%" scope="row"><div align="center" class="style40 style1">
                <div align="center">*</div>
              </div></td>
              <td width="27%" scope="row"><div align="left" class="style42">No Fail</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td width="70%"><input name="noFail" type="text" id="noFail" value="$!noFail" size="44" style="text-transform:uppercase;" $disabledNoFail $readonlyNoFail onBlur="checkNoFail('$noFail')"/></td>
            </tr>
              
 <tr>
              <td align="left" valign="top" scope="row">&nbsp;</td>
              <td align="left" valign="top" scope="row"><div align="right"></div></td>
              <td valign="top" scope="row">&nbsp;</td>
          <td><input name="noTurutan" type="text" id="noTurutan" value="$!noTurutan" size="5" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $disabledNoTurutan $readonlyNoTurutan/>
              <em>No Turutan Fail</em></td>
            </tr>
            <tr>
              <td align="left" valign="top" scope="row">&nbsp;</td>
              <td align="left" valign="top" scope="row"><div align="right"></div></td>
              <td valign="top" scope="row">&nbsp;</td>
              <td><input name="noJilid" type="text" id="noJilid" value="$!noJilid" size="5" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $disabledNoJilid $readonlyNoJilid/>
                <em>No Turutan Jilid</em></td>
            </tr>
            <tr>
              <td align="left" valign="top" scope="row">&nbsp;</td>
              <td align="left" valign="top" scope="row"><div align="right"></div></td>
              <td valign="top" scope="row">&nbsp;</td>
              <td><input name="noSubjaket" type="text" id="noSubjaket" value="$!noSubjaket" size="5" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $disabledNoSubjaket $readonlyNoSubjaket/>
                <em>              No Turutan Subjaket</em></td>
            </tr>
            <tr>
              <td align="left" valign="top" scope="row"><div align="center" class="style1">
                <div align="center"><span class="style40">*</span></div>
              </div></td>
              <td align="left" valign="top" scope="row"><div align="left" class="style42">Tajuk Fail</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>
               #if($mode == 'baru' || $mode == 'kemaskini')
 
                  <textarea  name="txtTajukFailB" cols="41" id="txtTajukFailB" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $disabledTajukFail $readonlyTajukFail>$!tajukFailB</textarea>
                  	
                    #if($mode == 'baru' || $mode == 'kemaskini')
                  
                     <script> 
								              	var area1 = new FCKeditor("txtTajukFailB");
												area1.ToolbarSet = 'PFD';
									      		area1.BasePath = '/${appname}/library/fck/' ;
									      		//area.Height = 200;
												//area.Width = 780;
												area1.ReplaceTextarea();             	
								              </script> 
                    #end
    
                #elseif ($mode == 'papar' || $mode == '' || $mode == 'paparSemua')
    			
                	$!tajukFailB
	
    			#end
                  
                  </td>
            </tr>
            <tr>
              <td scope="row"><div align="center" class="style1">
                <div align="center"><span class="style40">*</span></div>
              </div></td>
              <td scope="row"><div align="left" class="style42">Seksyen</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>
                $selectSeksyenB          </td>
            </tr>
          
            
            <tr>
              <td scope="row"><div align="center" class="style1">
                <div align="center"><span class="style40">*</span></div>
              </div></td>
              <td scope="row"><div align="left" class="style42">Urusan</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>
               #foreach($urusan in $SenaraiUrusan)
            
           		 #if($idUrusan==$urusan.ID_URUSAN)
            
            		#set($namaUrusan=$urusan.NAMA_URUSAN)
                    #set($kodUrusan=$urusan.KOD_URUSAN)
              
            	#end 
               #end
     
                #if($idUrusan !="" && $idUrusan !="0")
                  <input name="idUrusan" id="idUrusan" type="hidden" value="$!idUrusan"/>
                  
                  <select name="socUrusanB" class="autoselect" onChange="getSubUrusan();" $readonly $disabled>
                  	  	<option value="$idUrusan" >$kodUrusan - $namaUrusan</option>	
                 		#foreach($urusan in $SenaraiUrusan)
                         #if($idUrusan!=$urusan.ID_URUSAN)
                         <option value="$urusan.ID_URUSAN" >$urusan.KOD_URUSAN - $urusan.NAMA_URUSAN</option>
                		 #end
                        #end
                          <option value="">SILA PILIH</option>
                </select>
                
                #else
                 <select name="socUrusanB" class="autoselect" onChange="getSubUrusan();">
                  	  	 <option value="">SILA PILIH</option>	
                 		#foreach($urusan in $SenaraiUrusan)
                         <option value="$urusan.ID_URUSAN" >$urusan.KOD_URUSAN - $urusan.NAMA_URUSAN</option>
                		 #end
                </select>
                #end
              
                
                         </td>
            </tr>
           
            #foreach($suburusan in $SenaraiSubUrusan)
            
           		 #if($idSubUrusan==$suburusan.ID_SUBURUSAN)
            
            		#set($namaSubUrusan=$suburusan.NAMA_SUBURUSAN)
                    #set($kodSubUrusan=$suburusan.KOD_SUBURUSAN)
              
            	#end 
            #end
               	
          
            
         
            <tr>
              <td scope="row"><div align="center"></div></td>
              <td scope="row"><div align="left">Sub Urusan</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>
           
         
                	#if($idSubUrusan != "" && $idSubUrusan != "0")
                    <input name="idSubUrusan" id="idSubUrusan" type="hidden" value="$!idSubUrusan"/>
                <select name="socSuburusanB" class="autoselect" $readonly $disabled>
                  <option value="$idSubUrusan">$kodSubUrusan - $namaSubUrusan</option>
                 #foreach($suburusan in $SenaraiSubUrusan)
                  	#if($suburusan.KOD_SUBURUSAN_ARKIB == "")
                 		<option value="$suburusan.ID_SUBURUSAN" selected>$suburusan.KOD_SUBURUSAN - $suburusan.NAMA_SUBURUSAN</option>
                 	#else
                 		<option value="$suburusan.ID_SUBURUSAN" selected>$suburusan.KOD_SUBURUSAN_ARKIB - $suburusan.NAMA_SUBURUSAN</option>
                 	#end  
                 #end
           
              
                  <option value="">SILA PILIH</option>	 
                </select>                  
                 #else
                <select name="socSuburusanB" class="autoselect">
                      <option value="">SILA PILIH</option>
                      #foreach($suburusan in $SenaraiSubUrusan)
                       	#if($suburusan.KOD_SUBURUSAN_ARKIB == "")
                      		<option value="$suburusan.ID_SUBURUSAN" selected>$suburusan.KOD_SUBURUSAN - $suburusan.NAMA_SUBURUSAN</option>
                      	#else
                      		<option value="$suburusan.ID_SUBURUSAN" selected>$suburusan.KOD_SUBURUSAN_ARKIB - $suburusan.NAMA_SUBURUSAN</option>
                      	#end  
                      #end
                    </select> 
                #end
                
                </td>
            </tr>
           
          
     #if($getSubSubUrusanbyIdSubUrusan_size > 0)
        <tr>
          <td scope="row">&nbsp;</td>
          <td scope="row">Sub-Sub Urusan</td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td>$selectSubSubUrusanB </td>
        </tr>
          #else
            <tr>
              <td scope="row"><div align="center"></div></td>
              <td scope="row"><div align="left">Sub-Sub Urusan</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>
                <select name="socSubSubUrusanB" disabled="disabled">
                  <option value="">SILA PILIH</option>
                </select>              </td>
            </tr>
            #end
            <tr>
              <td scope="row"><div align="center"><span class="style1">*</span></div></td>
              <td scope="row"><div align="left">Taraf Keselamatan</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>
               $selectTarafB          </td>
            </tr>
            <tr>
              <td scope="row"><div align="center"><span class="style1">*</span></div></td>
              <td scope="row"><div align="left">Status Fail</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>
               $selectStatusB         </td>
            </tr>
            <tr>
              <td scope="row">&nbsp;</td>
              <td scope="row"><div align="left">Lokasi Simpanan Fail</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td>$selectLokasiFailB </td>
            </tr>
            <tr>
              <td scope="row">&nbsp;</td>
              <td align="left" valign="top" scope="row"><div align="left">Kabinet</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td><input name="txtKabinetB" type="text" id="txtKabinetB" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value="$!kabinetB" size="44" $disabledK $readonlyK/></td>
            </tr>
            <tr>
              <td scope="row"><div align="center"><span class="style1">*</span></div></td>
              <td scope="row"><div align="left">Tarikh Daftar Fail</div></td>
              <td width="1%" valign="top" scope="row"><div align="center">:</div></td>
              <td><input name="txtTarikhDaftarB" type="text" id="txtTarikhDaftarB" value = "$!tarikhDaftarB" size="10"/ $disabledDate $readonlyDate> <a href="javascript:displayDatePicker('txtTarikhDaftarB',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
            </tr>
            <tr>
              <td scope="row">&nbsp;</td>
              <td scope="row"><div align="left"></div></td>
              <td valign="top" scope="row"><div align="center"></div></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6 style2"><span class="style40 style1">Perhatian </span><span class="style1">:</span> Pastikan label bertanda <span class="style40 style1">*</span>  diisi.</span></td>
              </tr>
    <tr>
    <td colspan="4" align="center"> #if($mode == 'baru')
 	    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpan()"/>
 	       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batal()"/>
 	       <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>
 	    
 	    #elseif($mode == 'papar')
 	    <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskini('$!idFail')"/>
        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="cetak('$!idFail')" />
 	    <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="hapus1('$!idFail')" />
 	    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>
 	    
 	    
 	    #elseif($mode == 'kemaskini')
 	    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="update1('$!idFail')"/>
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="batalView()"/>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>
 	    #else
 	    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()"/>

 	    #end 	 </td>
  </tr>
          </table>
	  </fieldset>
    </div>
	<div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
</fieldset>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="action1" id="action1" >
<input type="hidden" name="idFail" id="idFail" value="$idFail"/>
<input type="hidden" name="modeAktiviti" id="modeAktiviti" value="$modeAktiviti"/>
<input type="hidden" name="command1" id="command1"/>
<input type="hidden" name="aktivitiValue" id="aktivitiValue" value="$aktivitiValue"/>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<input name="mode" id="mode" type="hidden" value="$!mode"/>
<input type="hidden" id="ScrollX" name='ScrollX'  />
<input type="hidden" id="ScrollY" name='ScrollY'  />
</body>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
</script>
<script>
function checkNoFail(noFail){
	//document.${formName}.noFail.value = noFail;
	SaveScrollXY();        
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&command=checkNoFail&action1=tabFailSeksyenLama";
	document.${formName}.submit();
	
}
function batalView(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=papar";
	document.${formName}.submit();
}
function batal(){

	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtTajukFailB.value = "";
	//document.${formName}.socSeksyenB.value = "";
	document.${formName}.socUrusanB.value = "";
	//document.${formName}.socSuburusanB.value = "";
	//document.${formName}.socTarafA.value = "";
	//document.${formName}.socStatusA.value = "";
	document.${formName}.socLokasiFailB.value = "";
	document.${formName}.txtKabinetB.value = "";

	
	return;
	
}

//function cetak(idFail) {
//		var url = "../servlet/ekptg.report.pfd.NoFailTajukFail?reportType=PDF&idfail="+idFail;
//	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
//	    if ((document.window != null) && (!hWnd.opener))
//		hWnd.opener = document.window;
//	    if (hWnd.focus != null) hWnd.focus();
//
//}

function cetak(idFail) {
        var url = "../servlet/ekptg.report.pfd.PFDFail?reportType=PDF&KULITFAIL_ID="+idFail;
        var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
        if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
        if (hWnd.focus != null) hWnd.focus();

}
function update1(){

		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth()+1;
		//curr_month++
		var curr_year = d.getFullYear();
	
		var today = curr_date +'/' + curr_month +'/' + curr_year;
		
		
			   var str1  = document.${formName}.txtTarikhDaftarB.value;	   
			   var dt1   = parseInt(str1.substring(0,2),10);
			   var mon1  = parseInt(str1.substring(3,5),10)-1;
			   var yr1   = parseInt(str1.substring(6,10),10);		   
			   var DateDaftar = new Date(yr1, mon1, dt1);
			   
		if( DateDaftar > d)
		{
			alert('Sila pastikan " Tarikh Daftar Fail " tidak melebihi tarikh hari ini.');
			document.${formName}.txtTarikhDaftarB.focus();
			return false;
		}

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=updateFailSeksyenLama";
	//document.${formName}.idFail.value = idFail;
	document.${formName}.submit();

}
function hapus1(idFail){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=hapus";
	document.${formName}.idFail.value = idFail;
	document.${formName}.submit();

}

function kemaskini(idFail){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=kemaskiniFailSeksyenLama";
	document.${formName}.idFail.value = idFail;

	document.${formName}.submit();

}
function kembali(){

	document.${formName}.action ="?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=";
	document.${formName}.submit();
}
function simpan(){

		var editorInstance = FCKeditorAPI.GetInstance('txtTajukFailB');   
        var txtTajukFailB = editorInstance.GetHTML(true);
       	var textlength = txtTajukFailB.length;
                
                
         if(textlength==0)
         {
         alert('Sila masukkan " Tajuk Fail " terlebih dahulu.');
         oEditor.EditorDocument.body.focus();
         return;
         }

		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth()+1;
		//curr_month++
		var curr_year = d.getFullYear();
	
		var today = curr_date +'/' + curr_month +'/' + curr_year;
		
		
			   var str1  = document.${formName}.txtTarikhDaftarB.value;	   
			   var dt1   = parseInt(str1.substring(0,2),10);
			   var mon1  = parseInt(str1.substring(3,5),10)-1;
			   var yr1   = parseInt(str1.substring(6,10),10);		   
			   var DateDaftar = new Date(yr1, mon1, dt1);
			   
	
		if( DateDaftar > d)
		{
			alert('Sila pastikan " Tarikh Daftar Fail " tidak melebihi tarikh hari ini.');
			document.${formName}.txtTarikhDaftarB.focus();
			return false;
		}
		
		if (document.${formName}.noFail.value == ""){
				alert('Sila masukkan " No Fail " terlebih dahulu.');
				document.${formName}.noFail.focus();
				return;
		}
				
		if (document.${formName}.socSeksyenB.value == ""){
				alert('Sila masukkan " Seksyen " terlebih dahulu.');
				document.${formName}.socSeksyenB.focus();
				return;
		}
		if (document.${formName}.socUrusanB.value == ""){
				alert('Sila masukkan " Urusan " terlebih dahulu.');
				document.${formName}.socUrusanB.focus();
				return;
		}  
		
		if (document.${formName}.socTarafB.value == ""){
				alert('Sila masukkan " Taraf Keselamatan " terlebih dahulu.');
				document.${formName}.socTarafB.focus();
				return;
		}  
		
		if (document.${formName}.socStatusB.value == ""){
				alert('Sila masukkan " Status Fail " terlebih dahulu.');
				document.${formName}.socStatusB.focus();
				return;
		}  	

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=simpanFailSeksyenLama";
	document.${formName}.submit();
}

function tabFailSeksyenBaru(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSeksyenBaru&modeAktiviti=1";
	document.${formName}.submit();
}
function tabFailSeksyenArkib(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSeksyenArkib";
	document.${formName}.submit();
}
function doChangeUrusanB() {
	document.${formName}.action1.value = "tabFailSeksyenLama";
 	doAjaxCall${formName}("doChangeUrusanB");
}

function aktivitiTiada(){
 	document.${formName}.modeAktiviti.value = "tiada"
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSeksyenLama";
	document.${formName}.submit();

}
function aktivitiAda(){
 	document.${formName}.modeAktiviti.value = "ada";
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranFail&action1=tabFailSeksyenLama";
	document.${formName}.submit();

} 
function getSubUrusan(){
	SaveScrollXY();        
	document.${formName}.command1.value = "getSubUrusan";
	document.${formName}.action = "";
	document.${formName}.submit();
	
}
function SaveScrollXY() {
    document.${formName}.ScrollX.value = document.body.scrollLeft;
    document.${formName}.ScrollY.value = document.body.scrollTop;
   }


function ResetScrollPosition() {
    var hidx, hidy;
    hidx = '$ScrollX';
   hidy = '$ScrollY';
                        
    if (typeof hidx != 'undefined' && typeof hidy != 'undefined') {
      window.scrollTo(hidx, hidy);
    }
  }


</script>

