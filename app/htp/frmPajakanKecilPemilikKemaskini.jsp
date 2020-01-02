<script type="text/javascript" src="../library/js/ekptgTools.js" ></script>

	#parse ("app/htp/frmPajakanKecilInfo.jsp")			

<fieldset>
  <legend><strong>Maklumat  <!--Hakmilik-->|$pagemode</strong></legend>


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

#if ( $pagemode == 2  )
	##set ( $sewa = $util.formatDecimal($hakmilikbangunaninfo.sewabulanan) )
	#set ( $norujukan = $pihak.NoRujukan)
	#set ( $nama = $pihak.Nama )
	#set ( $alamat1 = $pihak.Alamat1)
	#set ( $alamat2 = $pihak.Alamat2)
	#set ( $alamat3 = $pihak.Alamat3)
	#set ( $poskod  = $pihak.poskod)
	##set ( $tarikhmula = $hakmilikbangunaninfo.tmula )
	##set ( $tarikhtamat = $hakmilikbangunaninfo.ttamat )
#end
    <table border="1" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top"><table width="978" border="0">
                 <tr><td colspan="6"><b>Hakmilik</b><hr/></td></tr>
              <tr>
                <td width="272"><div align="right"><strong>Negeri  </strong></div></td>
                 <td width="7"><strong>:</strong></td>
                 <td width="231">$socNegeri
                <input type="hidden" name="idnegeri2" size="30" value="$idnegeri" /></td>
                 <td width="166"><div align="right"><strong>No Hakmilik :</strong></div></td>
                 <td width="156"><select name="sochakmilik" onchange="enableField()">
                   <option value="0">Sila Pilih No Hakmilik</option>
                   
                               
  						#foreach ( $senarai in $senaraihakmilik )
				  			
                               
                   <option value="$senarai.idhakmilikurusan">$senarai.nohakmilik</option>
                   
                               
  						#end
                  		
                               
                   <option value="">Lain-Lain</option>
                </select></td>
                 <td width="120">&nbsp;</td>
              </tr>
                           <tr>
                             <td><div align="right"><strong>Daerah</strong></div></td>
                             <td><strong>:</strong></td>
                             <td>$socDaerah1 </td>
                             <td><div align="right"></div></td>
                             <td>$socHakmilik</td>
                             <td><input type="text" name="txtnohakmilik" size="15" value="$nohakmilik" /></td>
                           </tr>
                           <tr>
                <td><div align="right"><strong>Mukim  </strong></div></td>
                 <td><strong>:</strong></td>
                 <td>$socMukim </td>
                 <td><div align="right"><strong>No Lot:</strong></div></td>
                 <td>$noLot </td>
                 <td><input type="text" name="txtnolot" size="15" value="$nolot" /></td>
              </tr>
              <tr>
              	<td  colspan="6"><br/><b>Pemilik</b><hr/></td>                                       
              </tr>
                 
              <tr>
                <td><div align="right"><strong>No. KP/Polis/Tentera/Syarikat :</strong></div></td>
                <td>&nbsp;</td>
                <td>
                <input type="text" name="txtnorujukan" size="30" value="$norujukan">                </td>
                <td><div align="right"><strong>Alamat Lokasi/Premis :</strong></div></td>
                <td><input type="text" name="txtalamat1" size="30" value="$alamat1" /></td>
                <td>&nbsp;</td>
              </tr> 
              <tr>
                <td><div align="right"><strong>Nama :</strong></div></td>
                <td>&nbsp;</td>
                <td>
                <input type="text" name="txtnama" size="30" value="$nama">                </td>
                <td><div align="right"></div></td>
                <td><input type="text" name="txtalamat2" size="30" value="$alamat2" /></td>
                <td>&nbsp;</td>
              </tr>         
               <tr>
                <td><div align="right"></div></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><div align="right"></div></td>
                <td><input type="text" name="txtalamat3" size="30" value="$alamat3" /></td>
                <td>&nbsp;</td>
              </tr>
                <tr>
                <td><div align="right"><strong></strong></div></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><div align="right"><strong>Poskod : </strong></div></td>
                <td><input type="text" name="txtposkod" maxlength="5" size="5" value="$poskod"
                	onkeyup="validatePoskod(this,this.value);" /></td>
                <td>&nbsp;</td>
              </tr>            
              <tr>
                <td><div align="right"><strong></strong></div></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><div align="right"><strong>Daerah :</strong></div></td>
                <td>$socDaerah </td>
                <td>&nbsp;</td>
              </tr>    
                <tr>
                <td><div align="right"></div></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><div align="right"><strong>Negeri : </strong></div></td>
                <td>$socNegeri
                  <input type="hidden" name="idnegeri" size="30" value="$idnegeri" /></td>
                <td>&nbsp;</td>
              </tr>  
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
<!--			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="tambahMaklumat('$permohonanInfo.idpermohonan')">&nbsp-->
<!--			<input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan">&nbsp;-->
<!--			<input type="button" name="cmdX" id="cmdX" value="Kembali" onclick="senaraiHakmilik('$permohonanInfo.idpermohonan')">&nbsp;-->
<!--	-->
			<!--<input type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti('$permohonanInfo.idpermohonan')">&nbsp;
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="back()">&nbsp;
			<input type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="back(-2)">&nbsp;
			-->
		</td>
      </tr>
   	<input type="hidden" name="id_kemaskini" value="$permohonanInfo.idpermohonan">
   	<input type="hidden" name="pagemode" value="$pagemode">


<script>

function senaraiHakmilik(i) {
	document.${formName}.id_kemaskini.value = i;
	document.${formName}.command.value = "pksemakanseterus";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function nexti(i) {
	document.${formName}.id_kemaskini.value = i;
	document.${formName}.command.value = "pkpemilikseterus";
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
	document.${formName}.pagemode.value = 1;
	//document.${formName}.method = "post";
	//document.${formName}.command.value = "pemiliksimpan";
	document.${formName}.command.value = "pkpemiliktambah";
	document.${formName}.action = "";
	document.${formName}.submit();
	
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
</script>
<script language="JavaScript"> 
function doChangeDaerah(id){
	if(document.${formName}.socDaerah1.value=="")
		
		return;       			
	
document.${formName}.id_kemaskini.value = id;
	doAjaxCall${formName}("pilihMukim");
}
//document.forms[0].no_fail.focus(); 
</script>