
	## 0=view
	## 1=save
	## 2=view
	## 2=update
	## 3=delete
	## 4=simpan permohonan
	#set ( $pagemode = 0 )			
	#set ( $nofailseksyen = $nofail )
	#set ( $nofaillain = ""  )
	#set ( $tarikhsurat = "" )	
	#set ( $tarikhdaftar= $sekarang )	

	#set ($inputstyle = "class=disabled" )
	#set ($inputstylerep = "" )
	#set ($selectstyle = "" )
	#set ($cbstyle = "" )
	
	#set ($tajuk = "")
	#set ($idFail = "")
	#if ($pageMode == 2 )	
		##set ($inputstyle = "" )
		##set ($selectstyle = "" )
		#set ($inputstylerep = "class=disabled readonly" )
		#set ($cbstyle = "disabled class=disabled" )
		
		#set ($tajuk = $permohonanInfo.tujuan)	
		#set ( $nofailseksyen = $permohonanInfo.fail)
		#set ( $nofaillain = $permohonanInfo.rujukanlain)
		##set ( $tarikhsurat = $permohonanInfo.tsurat)	
		##set ( $tarikhdaftar = $permohonanInfo.tdaftar)	
		#set ( $tarikhsurat = $util.getDateTime($permohonanInfo.tsurat, "dd/MM/yyyy"))	
		#set ( $tarikhdaftar = $util.getDateTime($permohonanInfo.tdaftar, "dd/MM/yyyy"))	
	#elseif($pageMode == 4)		
		##set ($tajuk = $permohonanInfo.tujuan)	
		#set ($tajuk = $permohonanInfo.tajukfail)	
		#set ( $nofailseksyen = $permohonanInfo.fail)
		#set ( $nofaillain = $permohonanInfo.rujukanlain)
		#set ( $tarikhsurat = $util.getDateTime($permohonanInfo.tsurat, "dd/MM/yyyy"))	
		#set ( $tarikhdaftar = $util.getDateTime($permohonanInfo.tdaftar, "dd/MM/yyyy"))	
		#set ( $idFail = $permohonanInfo.idFail)
	#else
		##set ($inputstyle = "class=disabled" )
		##elseif ( $pageMode != 0  )
		#set ( $pagemode = $pageMode )	
		#set ($tajuk = $!tajukTemp)	
	#end		
<!--<strong> Pendaftaran $titleProses</strong>
<br><br> -->
<table width="98%" border="0">
	<tr>
		<td>
<fieldset>
<legend>PENDAFTARAN FAIL</legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
	<td width="50%" align="center" valign="top">	<table width="100%" border="0">
			<tr>
			<td colspan=3> 
			##if ( $operation == "success" )
			    <table width="100%" border="0">
			        <tr>
			            <td>
			            <font color="blue">
			            <b>
			            #if ( $pageMode == "2" )
			                <!--Maklumat kemasukan  baru telah berjaya disimpan.-->
			                <script type="text/javascript">
								alert("Maklumat kemasukan  baru telah berjaya disimpan!")
							</script>
			            #elseif ($pageMode == "4" )
			                Maklumat berjaya dikemskini
			            #end
			            </b>
			            </font>
			            </td>
			        </tr>
			    </table>
			##elseif ( $operation == "failed" )
			 <!--   <table width="100%" border="0">
			        <tr>
			            <td>
			            <b>
			            <font color="red">
			            Maklumat tidak berjaya disimpan/dikemaskini.
			            </font>
			            </b>
			            </td>
			        </tr>
			    </table>
			##else
			    <table width="100%" border="0">
			        <tr>
			            <td nowrap>&nbsp;</td>
			        </tr>
			    </table> -->
			##end
			</td>
			</tr>
	       	<tr>
                <td><div align="left"><strong>No. Fail Seksyen </strong></div></td>
                <td><strong>:</strong></td>
                <td><input type="text" name="txtNoFailSek" size="30" value="$nofailseksyen"  $inputstyle readonly></td>
              </tr>	
              <tr>
                <td><div align="left"><strong><font color="#FF0000">*</font>Negeri</strong></div></td>
                <td><strong>:</strong></td>
                <td>$socNegeri</td>
              </tr>
              <!--<tr>
                <td><div align="left"><strong><font color="#FF0000">*</font>Kementerian </strong></div></td>
               	<td><strong>:</strong></td>
            	<td>$socKementerian</td>
              </tr>
             <tr>
                <td><div align="left"><strong><font color="#FF0000">*</font>Agensi </strong></div></td>
                <td><strong>:</strong></td>
                <td>$socAgensi</td>
              </tr> -->
              <tr>
                <td><div align="left"><strong><font color="#FF0000">*</font>Urusan </strong></div></td>
                <td><strong>:</strong></td>
                <td>$socUrusan</td>
              </tr>
           	<!--<tr>
                <td><div align="right"><strong>Urusan :</strong></div></td>
                <td>$socSuburusan</td>
              </tr>-->
               <tr>
                <td valign="top"><div align="left"><strong><font color="#FF0000">*</font>Tajuk </strong></div></td>
                <td valign="top"><strong>:</strong></td>
                <td><label>
                  <textarea name="txttajuk" cols="54" rows="3" onkeyup="this.value=this.value.toUpperCase();" $inputstylerep>$!tajuk</textarea>
                </label></td>
              </tr>
			<tr>
                <td><div align="left"><strong><!--<font color="#FF0000">*--></font>Tarikh Buka Fail </strong></div></td>
               	<td><strong>:</strong></td>
                <td><input type="text" name="txdbukafail" value="$tarikhdaftar" id="txdbukafail" size="15"  readonly $inputstyle></td>
              </tr>
        </table></td>
        <!--
        <td width="50%" align="center" valign="top"><table width="100%" border="0">

               <tr>
                <td><div align="left"><strong><font color="#FF0000">*</font>No. Fail Lain </strong></div></td>
                <td><strong>:</strong></td>	           
                <td><input type="text" name="txtNoFailLain" size="30" value="$nofaillain" id="txtNoFailLain" onkeyup="checkDate();" $inputstylerep></td>
              </tr>
              <tr>
                <td><div align="left"><strong><font color="#FF0000">*</font>Tarikh Surat KJP</strong></div></td>
                <td><strong>:</strong></td>
                <td><input type="text" name="txdTarikhSuratKJP" value="$tarikhsurat" id="txdTarikhSurKJP" size="15" readonly $inputstyle>
                #if ($pageMode != 2 )	
                <a href="javascript:displayDatePicker('txdTarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
                #end
                </td>
              </tr>
        </table></td>-->
	</tr>
     <!----> <tr>
		<td colspan="2" align="center">
		
</td>
      </tr> 
      
    </tbody>
  </table>  
   	<input type="hidden" name="id_kemaskini" >
   	<input type="hidden" name="fail" value="$idFail">
   	<input type="hidden" name="pagemode" value="$pageMode">
	<input type="hidden" name="idseksyen" value="$socSeksyen" size="30" >
    <input type="hidden" name="idsuburusan" value="$idsuburusan" size="30">
    <input type="hidden" name="idurusan" value="$idurusan" size="30">
    <input type="hidden" name="idagensi" value="$idagensi" size="30">
    <input type="hidden" name="idkementerian" value="$idkementerian" size="30">
    <input type="hidden" name="statusfail" value="$statusfail" size="2">
   	
<!--</form>-->
</fieldset>

		</td>
	</tr>
	        #if ($mode != 'view')
	  <tr>
	    <td colspan="2" valign="bottom">
	    	<i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.
	    	<br><!--()-->
	    	</i></td>
	  </tr>
	  #end

	<tr>
		<td align="center">
				#if ($pageMode == 2 )	
					<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick= "simpanFail()">&nbsp;
				#end
				#if ($pageMode == 0 || $pageMode == 4)	
					#if ($tambahdisable == false)
					<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick= "simpanFail()">
					<input type="reset" class="stylobutton" name="cmdBatal" id="cmdBatal" value="Kosongkan" onclick="cancel">
					#end
				#end <!--
				#if ($pageMode == 2 )	
				<input type="button" class="stylobutton" name="cmdSeterus" id="cmdSeterus" value="Seterusnya" onclick="nexti($idPermohonan)">&nbsp;
				#end
				 &nbsp;-->
				#if ($tambahdisable == false)
					<input type="button" class="stylobutton" name="cmdKembali" id="cmdKembali" value="Batal" onclick="backMain()">
				#end
	
		</td>
	</tr>
</table>
<script>

function cancel() {
	document.${formName}.reset();
}

function simpanFail(){
	if ( document.${formName}.statusfail.value == "1" ) { 
  		alert('Sila kemaskini Status Fail kepada Selesai terlebih dahulu.');
  		return; 
  	} 
  	if ( document.${formName}.socNegeri.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); return; 
  	} 
    if ( document.${formName}.txttajuk.value == "" ) { 
    	alert('Sila masukkan maklumat tajuk terlebih dahulu.');
    	document.${formName}.txttajuk.focus(); 
    	return; 
    }  
    document.${formName}.command.value = "cukaifailbaru";
    
	if(document.${formName}.pagemode.value == "0"){
      	//document.${formName}.pagemode.value = "1";
  	}else if(document.${formName}.pagemode.value == "2"){
      	//document.${formName}.command.value = "pkpermohonanbaru";
      	//document.${formName}.command.value = "pkpermohonankemaskini";
      	document.${formName}.id_kemaskini.value = "$permohonanInfo.idpermohonan";
      	document.${formName}.pagemode.value = "3";
  	}else{
  	      	document.${formName}.id_kemaskini.value = "$permohonanInfo.idpermohonan";
  	
  	}
    document.${formName}.method = "post";
	document.${formName}.action = "";
	document.${formName}.submit();


}

function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}

function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}

function doChangeSemakFail() {
	if(document.${formName}.socNegeri.value=='' || document.${formName}.socNegeri.value==0 ){
	 return; }
	doAjaxCall${formName}("doChangeSemakFail");
}


function backMain() {
  	document.${formName}.method = "post";
	//document.${formName}.command.value = "pkfail";
	document.${formName}.action = "";
  	//document.${formName}.action = "?_portal_module=FileUpload";
	document.${formName}.submit();
}

function checkDate() {
	var today = new Date();
	
	dari_bulan = document.${formName}.txdTarikhSuratKJP.value.substring(3,5);
	dari_hari = document.${formName}.txdTarikhSuratKJP.value.substring(0,2);
	dari_tahun = document.${formName}.txdTarikhSuratKJP.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
 		return;
 	}

}

function nexti(id) {
	document.${formName}.command.value = "pkpendaftaranseterus";
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.method="post";
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>
