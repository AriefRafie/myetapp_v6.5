	#parse ("app/htp/frmSewaanInfo.jsp")			

<fieldset>
  <legend><strong>Maklumat <!--Hakmilik--></strong></legend>
  <form name=f2 method="post">
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
#set ($nohakmilik = "")
#set ($nolot = "")
#set ( $nama = "") 
#set ( $alamat1 = "")
#set ( $alamat2 = "")
#set ( $alamat3 = "")
#set ( $poskod  = "")

#if ( $pagemode == 2  )
	##set ( $sewa = $util.formatDecimal($hakmilikbangunaninfo.sewabulanan) )
	#set ( $sewa = $hakmilikbangunaninfo.sewabulanan)
	#set ( $alamat1 = $hakmilikbangunaninfo.alamat1)
	#set ( $alamat2 = $hakmilikbangunaninfo.alamat1)
	#set ( $alamat3 = $hakmilikbangunaninfo.alamat1)
	#set ( $poskod  = $hakmilikbangunaninfo.poskod)
	#set ( $luas = $hakmilikbangunaninfo.luas )
	#set ( $tarikhmula = $hakmilikbangunaninfo.tmula )
	#set ( $tarikhtamat = $hakmilikbangunaninfo.ttamat )
#end
    <table border="1" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top"><table width="100%" border="0">
                 <tr><td colspan="2"><b>Hakmilik</b><hr/></td></tr>
              <tr>
                <td><div align="right"><strong>No Hakmilik :</strong></div></td>
                 <td>
                 	<select name="sochakmilik" onchange="enableField()">
                  		<option value="0">Sila Pilih No Hakmilik</option>
                  		<option value="">Lain-Lain</option>
                	</select>
                 	<input type="text" name="txtnohakmilik" size="15" value="$nohakmilik">
                 </td>
              </tr>
                           <tr>
                <td><div align="right"><strong>No Lot:</strong></div></td>
                 <td><input type="text" name="txtnolot" size="15" value="$nolot"></td>
              </tr>
              <tr>
              	<td  colspan="2"><br/><b>Pemilik</b><hr/></td>                                       
              </tr>
                 
              <tr>
                <td><div align="right"><strong>Nama :</strong></div></td>
                <td>
                <input type="text" name="txtnama" size="30" value="$nama">
                </td>
              </tr>         
               <tr>
                <td><div align="right"><strong>Alamat Lokasi/Premis :</strong></div></td>
                <td><input type="text" name="txtalama1" size="30" value="$alamat1"></td>
              </tr>
                <tr>
                <td><div align="right"><strong></strong></div></td>
                <td><input type="text" name="txtalama2" size="30" value="$alamat2"></td>
              </tr>            
              <tr>
                <td><div align="right"><strong></strong></div></td>
                <td><input type="text" name="txtalama3" size="30" value="$alamat3"></td>
              </tr>    
                <tr>
                <td><div align="right"><strong>Poskod</strong></div></td>
                <td><input type="text" name="txtposkod" maxlength="5" size="5" value="$poskod"
                onkeyup="isxNumber();"></td>
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
			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="tambahMaklumat('$permohonanInfo.idpermohonan')">&nbsp
			<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal">&nbsp;
			<input type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti()">&nbsp;
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="back()">&nbsp;
			<input type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="back(-2)">&nbsp;
		</td>
      </tr>
          <input type="hidden" name="command">
   		<input type="hidden" name="id_kemaskini">
  </form>


<script>
function nexti() {
	document.f2.command.value = "maklumatseterus";
	document.f2.action = "";
	document.f2.submit();
}

function cancel() {
	//document.setup.buttonsubmit.value = "ADD";
	document.cari.reset();
	//document.forms[0].kod_agensi.focus();
}

function senaraiPermohonan(id) {
	document.f2.action = "?_portal_module=HTP-00&fail="+id;
	document.f2.submit();
}

function tambahMaklumat(id) {
	//var hingga = Date.parse(document.f2.txdhingga.value);
	/*var dari = Date.parse(document.f2.txddari.value);
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
	
	document.f2.id_kemaskini.value = id;
	
	document.f2.method = "post";
	document.f2.command.value = "pemiliksimpan";
	document.f2.action = "";
	document.f2.submit();
	
}
function isxNumber(){
	var val = document.f2.txtposkod.value;
	if(val == null || isNaN(val)) {
   		alert("Sila masukkan nombor yang betul.");
   		document.f2.txtposkod.value = "";
   		document.f2.txtposkod.focus();
   		return;
	}
	
}

 function enableField(){
 	//alert(document.f2.sochakmilik.value);
 	//if(document.f2.sochakmilik.value==1)
	document.f2.txtnohakmilik.value = document.f2.sochakmilik.value;
}
</script>
<script language="JavaScript"> 
//document.forms[0].no_fail.focus(); 
</script>