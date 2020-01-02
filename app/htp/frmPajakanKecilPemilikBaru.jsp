<script type="text/javascript" src="../library/js/ekptgTools.js" ></script>

<strong>[CL-02-0405] Pajakan Kecil Tanah/Bangunan testing</strong>
<br><br>

#parse ("app/htp/frmPajakanKecilInfo.jsp")	
<fieldset>
  <legend><strong>Maklumat <!--Hakmilik-->new | $pagemode</strong></legend>
  <!--<form name=${formName} method="post">-->
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
#set ($nohakmilik = "")
#set ($nolot = "")
#set ( $norujukan = "") 
#set ( $nama = "") 
#set ( $alamat1 = "")
#set ( $alamat2 = "")
#set ( $alamat3 = "")
#set ( $poskod  = "")
		
#set ($inputstylerep = "" )
#set ($cbstyle = "" )

#if ( $pagemode == 2  )
	#set ($nohakmilik = $hakmilik.NoHakmilik)
	#set ($nolot =  $hakmilik.NoLot)

	##set ( $sewa = $util.formatDecimal($hakmilikbangunaninfo.sewabulanan) )
	#set ( $norujukan = $pihak.NoRujukan)
	#set ( $nama = $pihak.Nama )
	#set ( $alamat1 = $pihak.Alamat1)
	#set ( $alamat2 = $pihak.Alamat2)
	#set ( $alamat3 = $pihak.Alamat3)
	#set ( $poskod  = $pihak.poskod)
	##set ( $tarikhmula = $hakmilikbangunaninfo.tmula )
	##set ( $tarikhtamat = $hakmilikbangunaninfo.ttamat )
	#set ($inputstylerep = "class=disabled readonly" )
	#set ($cbstyle = "disabled class=disabled" )
	
#elseif ( $pagemode == 1  )
		#set ($inputstylerep = "class=disabled readonly" )
		#set ($cbstyle = "disabled class=disabled" )

	##set ( $sewa = $util.formatDecimal($hakmilikbangunaninfo.sewabulanan) )
	##set ( $norujukan = $pihak.NoRujukan)
	##set ( $nama = $pihak.Nama )
	##set ( $alamat1 = $pihak.Alamat1)
	##set ( $alamat2 = $pihak.Alamat2)
	##set ( $alamat3 = $pihak.Alamat3)
	##set ( $poskod  = $pihak.poskod)
	##set ( $tarikhmula = $hakmilikbangunaninfo.tmula )
	##set ( $tarikhtamat = $hakmilikbangunaninfo.ttamat )	
#end
    <table border="1" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top"><table width="100%" border="0">
		#if ( $pagemode == 0 || $pagemode == 1 || $pagemode == 2)
                 <tr><td colspan="2"><b>Hakmilik</b><hr/></td></tr>
              <tr>
                <td><div align="right"><strong>No Hakmilik :</strong></div></td>
                 <td>
                 	<!-- <select name="sochakmilik" onchange="enableField()" $cbstyle>
                  		<option value="0">Sila Pilih No Hakmilik</option>
  						#foreach ( $senarai in $senaraihakmilik )
  							#set ($sochakmilik = "")
  							#if ( $senarai.nohakmilik == $nohakmilik )
  								#set ($sochakmilik = "selected")
				            #else
				         		#set ($sochakmilik = "")			            
				            #end
				  			<option value="$senarai.idhakmilikurusan" $sochakmilik>$senarai.nohakmilik</option>
  						#end
                  		<option value="">Lain-Lain</option>
                	</select> -->
                	$socHakmilik
                	#if ( $pagemode != 2)
                 	<input type="text" name="txtnohakmilik" size="15" value="$nohakmilik" $inputstylerep>
                 	#end
                 </td>
              </tr>
                           <tr>
                <td><div align="right"><strong>No Lot:</strong></div></td>
                 <td><input type="text" name="txtnolot" size="15" value="$nolot" $inputstylerep></td>
              </tr>
            #end
            #if ( $pagemode == 0 || $pagemode == 2 )  
              <tr>
              	<td  colspan="2"><br/><b>Pemilik</b><hr/></td>                                       
              </tr>
                 
              <tr>
                <td><div align="right"><strong>No. KP/Polis/Tentera/Syarikat :</strong></div></td>
                <td>
                <input type="text" name="txtnorujukan" size="30" value="$norujukan" $inputstylerep>
                </td>
              </tr> 
              <tr>
                <td><div align="right"><strong>Nama :</strong></div></td>
                <td>
                <input type="text" name="txtnama" size="30" value="$nama" $inputstylerep>
                </td>
              </tr>         
               <tr>
                <td><div align="right"><strong>Alamat Lokasi/Premis :</strong></div></td>
                <td><input type="text" name="txtalamat1" size="30" value="$alamat1" $inputstylerep></td>
              </tr>
                <tr>
                <td><div align="right"><strong></strong></div></td>
                <td><input type="text" name="txtalamat2" size="30" value="$alamat2" $inputstylerep></td>
              </tr>            
              <tr>
                <td><div align="right"><strong></strong></div></td>
                <td><input type="text" name="txtalamat3" size="30" value="$alamat3" $inputstylerep></td>
              </tr>    
                <tr>
                <td><div align="right"><strong>Poskod</strong></div></td>
                <td><input type="text" name="txtposkod" maxlength="5" size="5" value="$poskod"
                	onkeyup="validatePoskod(this,this.value);" $inputstylerep></td>
              </tr> 
               <tr>
                <td><div align="right"><strong>Daerah :</strong></div></td>
                <td>$socDaerah </td>
              </tr>              
              <tr>
                <td><div align="right"><strong>Negeri</strong></div></td>
                <td>$socNegeri
                	<input type="hidden" name="idnegeri" size="30" value="$idnegeri"></td>
              </tr>  
            #end
            </table>
                                                 
  		</td>	
   	</tr>
   </tbody>
  </table>
  </fieldset>
  <!--<fieldset>
  <legend><strong>Maklumat Lain</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
  	<td>No</td>
  	<td>No Fail</td>
  	<td>Nama Fail</td>
  	<td>Status Pergerakan Fail</td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraiList )
  	#set ( $cnt = $cnt + 1 )

  <tr>
  <td>$cnt.</td>
  <td>
  	<a href="javascript:senaraiPermohonan('$senarai.id')">
	$senarai.no
	</a>
  </td>
  <td>$senarai.tajuk</td>
  <td>$senarai.keterangan</td>
  </tr>
  #end
  </table>
  </fieldset>-->
        <tr>
		<td colspan="2" align="center">
			#if ( $pagemode == 1 || $pagemode == 2 )  
				<input class="button" type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="tambahMaklumat('$permohonanInfo.idpermohonan')">&nbsp
		    #end
		    #if ( $pagemode == 0  )  
				<input class="button" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="tambahMaklumat('$permohonanInfo.idpermohonan')">&nbsp
				<input class="button" type="reset" name="cmdKosong" id="cmdKosong" value="Kosongkan">&nbsp;
			#end
			<input class="button" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick ="batalHakmilik1('$permohonanInfo.idpermohonan')">&nbsp;
			<!--
			<input type="button" name="cmdSeterus" id="cmdSeterus" value="Seterusnya" onclick="nexti()">&nbsp;
			-->
			<!--
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backPre('$permohonanInfo.idpermohonan')">&nbsp;
			<input type="button" name="cmdFail" id="cmdFail" value="Senarai Fail" onclick="backMain('$permohonanInfo.idpermohonan')">&nbsp;
			-->
		</td>
      </tr>
    	<input type="hidden" name="fail" >
  		<input type="hidden" name="id_kemaskini">
   		<input type="hidden" name="pagemode">
   		<input type="hidden" name="langkah" value="0" >
   		
 <!--          
 <input type="hidden" name="command">
 </form>
 -->


<script>
function nexti() {
	document.${formName}.command.value = "maklumatseterus";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function cancel() {
	//document.setup.buttonsubmit.value = "ADD";
	document.cari.reset();
	//document.forms[0].kod_agensi.focus();
}

function senaraiPermohonan(id) {
	document.${formName}.action = "?_portal_module=HTP-00&fail="+id;
	document.${formName}.submit();
}

function tambahMaklumat(id) {
	//var hingga = Date.parse(document.${formName}.txdhingga.value);
	/*var dari = Date.parse(document.${formName}.txddari.value);
	var diff_date =  hingga - dari;
	alert (new Date());
	var num_years = diff_date/31536000000;
	var num_months = (diff_date % 31536000000)/2628000000;
	var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;
	alert("this is not a number 1:"+Math.floor(num_years));
	alert("this is not a number 2:"+Math.floor(num_months));
	alert("this is not a number 3:"+Math.floor(num_days));
	*/
	//isxNumber(this.txtposkod);
	//return true ;
 	//method="post"
	//document.tbh.action = "?_portal_module=ekptg.view.htp.FrmSewaanSemak";
	//document.tbh.submit();
	
	if(document.${formName}.sochakmilik.value == "" || document.${formName}.sochakmilik.value == 0){
		if(document.${formName}.txtnohakmilik.value == "" || document.${formName}.txtnohakmilik.value == 0){
			alert('Sila pilih No Hakmilik terlebih dahulu @\n Lain-lain bagi kemasukan baru');
			return;
		}else if(document.${formName}.txtnolot.value == ""){	
			alert('Sila masukkan No Lot terlebih dahulu');
			return;
		}	
	}


	if ( document.${formName}.txtnama.value == "" ) { 
    	alert('Sila masukkan nama terlebih dahulu.');
    	document.${formName}.txtnama.focus(); 
    	return; 
    }    
	
	if ( document.${formName}.txtalamat1.value == "" ) { 
    	alert('Sila masukkan alamat 1 terlebih dahulu.');
    	document.${formName}.txtalamat1.focus(); 
    	return; 
    }    
    if ( document.${formName}.txtalamat2.value == "" ) { 
    	alert('Sila masukkan alamat 2 lot terlebih dahulu.');
    	document.${formName}.txtalamat2.focus(); 
    	return; 
    }
    if ( document.${formName}.txtposkod.value == "" ) { 
    	alert('Sila masukkan maklumat poskod terlebih dahulu.');
    	document.${formName}.txtposkod.focus(); 
    	return; 
    } 
   	if ( document.${formName}.socDaerah.value == "" ) { 
    	alert('Sila pilih maklumat daerah terlebih dahulu.');
    	document.${formName}.socDaerah.focus(); 
    	return; 
    }
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 2;
	
	//document.${formName}.method = "post";
	//document.${formName}.command.value = "pkpemiliksimpan";
	document.${formName}.command.value = "pkpemiliktambah";
	document.${formName}.action = "";
	document.${formName}.submit();
	
/*	function kemaskiniPemilik(id,idp) {
	document.${formName}.fail.value = idp;
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 2;
	document.${formName}.command.value = "pkpemiliktambah";
	document.${formName}.action = "";
	document.${formName}.submit();
*/
}
}
function isxNumber(){
	var val = document.${formName}.txtposkod.value;
	if(val == null || isNaN(val)) {
   		alert("Sila masukkan nombor yang betul.");
   		document.${formName}.txtposkod.value = "";
   		document.${formName}.txtposkod.focus();
   		return;
	}
	
}

 function enableField(){
 	//alert(document.${formName}.sochakmilik.value);
 	//if(document.${formName}.sochakmilik.value==1)
	document.${formName}.txtnohakmilik.value = document.${formName}.sochakmilik.value;
}
function batalHakmilik(i) {
alert('ddd');
	//document.${formName}.id_kemaskini.value = i;
	//document.${formName}.command.value = "pksemakanseterus";
	//document.${formName}.action = "";
	//document.${formName}.submit();
}

function backPre(id) {
	document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.langkah.value = '0->-1';
	document.${formName}.fail.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function backMain() {
	//document.${formName}.command.value = "semakanseterus";
	//document.${formName}.method="post";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function batalHakmilik1(i) {
	alert('ddd');
}
</script>
