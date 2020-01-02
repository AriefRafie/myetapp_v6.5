<style type="text/css">
<!--
.style1 {color: #0033FF}
.bayaran {
	color: #F00;
}
-->
</style>

<table width="100%" border="0">

#if ($idFail != '')
	<tr>
		<td>
		#parse('app/htp/pajakan/paging.jsp')
		</td>
    </tr>
    
    <tr>
      <td>#parse("app/htp/frmPajakanHeader.jsp")</td>
    </tr>
    </br>
  <!-- <tr>
    <td>&nbsp;</td>
  </tr> -->
  #else
  <tr>
    <td>
    	&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    </td>
  </tr>
  #end
 
	#if ($mode == 'baharu' || $mode == 'kemaskini' || $mode == 'papar')
  	<tr>
    	<td>
         
    <fieldset>
    <legend><strong>MAKLUMAT TINDAKAN</strong></legend>
    <table width="100%" border="0">
    	<tr>
	        <td align="right">#if($mode == 'kemaskini' || $mode == 'baharu')<font color="#FF0000">*</font>#end</td>
	        <td>Tarikh Tindakan</td>
	        <td>:</td>
	        <td>
	        	<input type="text" name="txdtindakan" id="txdtindakan" size="10" value="$!tarikhTindakan"  class="$classDis" $readOnly onblur="checkDate(document.${formName}.txdtindakan);" />
	        #if($mode == 'kemaskini' || $mode == 'baharu')
				<img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdtindakan',false,'dmy');" /> 
			#end 
			</td>
        	<td>&nbsp;</td>
    	</tr>
      <tr>
        <td align="right" valign="top">#if($mode == 'kemaskini' || $mode == 'baharu')<font color="#FF0000">*</font>#end</td>
        <td valign="top">Tujuan</td>
        <td valign="top">:</td>
  		<td>
					<textarea name="txtcatatan" id="txtcatatan" cols="50" rows="5" maxlength="250" 
            				onkeyup="textCounter(this.form.txtcatatan,this.form.remtxtcatatan,250);" onkeydown="textCounter(this.form.txtcatatan,this.form.remtxtcatatan,250);"
            				class="$classDis" $!readOnly>$!catatan</textarea>	            
	  	</td>
	                    <td>&nbsp;</td>
      </tr>
      #if ($mode != 'papar')
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td valign="top">&nbsp;</td>
				<td><input type="text" readonly class="disabled" name="remtxtcatatan" size="3" maxlength="4" value="250"> Baki Aksara </td>
			</tr>	
			#end                        
            

      <tr>
        <td colspan="5">&nbsp;</td>
      </tr>
      <tr>
        	<td colspan="5" align="center">
        
        #if ($mode == 'baharu')
      		<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanPantau()" />
     		<input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
        	<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPantau()" />
     	#elseif ($mode == 'papar')
         	<input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniPantau()" />
         	<input class="stylobutton100" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:hapusPantau()" />
          	<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalPantau()" />
                    
       	#elseif ($mode == 'kemaskini')
         	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanKemaskiniPantau()" />
           	<!-- <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/> -->
         	<input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalKemaskiniPantau()" />
       	#end 
            </td>
        </tr>
    </table>
    
    </fieldset>
    
    </td>
  </tr>
  <tr>
    <td></td>
  </tr>

#end

#if ( $idFail != '' )
	<!-- <tr>
    	<td>&nbsp;</td>
	</tr> -->
  </br>
	<tr>
    	<td> 
    		<fieldset><legend><strong>SENARAI TINDAKAN</strong></legend>
		    <div align="left">
			      <table width="100%" border="0">
			         ##if ($mode == 'papar')
			         #if ($mode == '')
			        <tr>
			          <td colspan="8" scope="col">
			          	<input class="stylobutton100" type="button" name="cmdDaftar" id="cmdDaftar" value="Daftar" onclick="javascript:daftarBaruPantau()" />
			          </td>
			        </tr>
			        #end
			        <tr class="table_header">
			          <td width="3%" scope="col"><strong>Bil.</strong></td>
			          <td width="82%" scope="col"><strong>Tindakan</strong></td>	
			          <td width="15%" scope="col"><strong>Tarikh Tindakan</strong></td>
			          </tr>
		        #set ($count = 0)
		        #foreach ( $st in $senaraiTindakan )
		        #set ($count = $count+1)
		        #set( $i = $velocityCount )
		        #if ( ($i % 2) != 1 )
		        #set( $row = "row2" )
		        #else
		        #set( $row = "row1" )
		        #end
		        <tr>
		          <td class="$row" scope="row">$i.</td>
		          <td class="$row"><a href="javascript:paparPantau('$st.idSusulan')" class="style1">$!st.keterangan</a></td>
		          <td class="$row">$!st.tarikh</td>
		          </tr>
				#end

		        #if ($count == 0)
		        <tr>
		          <td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
		        </tr>
		        #end
		      </table>
	    	</div>
	    	</fieldset>    
		</td>
	</tr>
#end
  
  <tr align="">
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  	<input type="hidden" name="mode" id="mode" value="$mode"/>
  	<input type="hidden" name="hitButton" id="hitButton" value="$hitButton"/>
  	<input type="hidden" name="idFail" id="idFail" value="$idFail"/>
  	<input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
    <input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>
    <input type="hidden" name="actionPajakan" value="$actionPajakan"/>
    <input type="hidden" name="subUrusan" value="$subUrusan"/>
    <input type="hidden" name="idsusulan" id="idsusulan" value="$!idSusulan"/>
	<input type="hidden" name="selectedTab" value="$!selectedTab"/>

<script language="javascript" type="text/javascript">





function validateCurrency(elmnt,content,content2) {
//if it is character, then remove it..
if (isNaN(content)) {
elmnt.value = content2;
return;
}

if(content != ""){
var num = content * 1;
elmnt.value = num.toFixed(2);
return;
} else {
elmnt.value ="";
return;
}
}

//semakan Tarikh semasa
function checkDate(inputfield) {
	var today = new Date();
	
	dari_bulan = inputfield.value.substring(3,5);
	dari_hari = inputfield.value.substring(0,2);
	dari_tahun = inputfield.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
	var myDate = Date.parse(daritemp);

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		inputfield.value = "";
  		inputfield.focus();
 		return;
 	}	

}

//semakan Tarikh semasa
function checkDateV01(inputfield) {
	var today = new Date();
	dari_bulan = inputfield.value.substring(3,5);
	dari_hari = inputfield.value.substring(0,2);
	dari_tahun = inputfield.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
	var myDate = Date.parse(daritemp);

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		inputfield.value = "";
  		inputfield.focus();
 		return false;
 	}
 	return true;

}
/*
function langkah1(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanSenaraiFailView";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

function langkah2(permohonan,idFail){
	//alert('bayaran:2:'+permohonan+","+idFail);
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&actionPajakan=papar";
	document.${formName}.idPermohonan.value = permohonan;
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
function langkah3(permohonan,idFail){
	//alert('bayaran:3:'+permohonan+","+idFail);
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&idPermohonan="+permohonan+"&actionPajakan=paparan";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

function langkah4(permohonan,idFail){
	//alert('bayaran:4:'+permohonan+","+idFail);
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPerjanjianView&idPermohonan="+permohonan+"&actionPajakan=papar";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
} 
*/

</script>
