	#parse ("app/htp/frmSewaanInfo.jsp")			

<fieldset>
  <legend><strong>Maklumat Sewaan 66</strong></legend>
  <!--<form name="tbh" method="post">
  	<input name="add" value="Tambah" type="button" onClick="tambahPermohonan()">
  </form>-->
  <form name=f2 method="post">
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
  	<!--<td>No</td>
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
  #end -->
#set ($dari = "")
#set ( $sewa = 0.00) 
#if ( $hakmilikbangunaninfo != "null"  )
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
                 <tr><td colspan="2"><b>Jumlah</b><hr/></td></tr>
              <tr>
                <td><div align="right"><strong>Jumlah Sewaan Bulanan(RM) :</strong></div></td>
  
                <td><input type="text" name="txtsewa" size="15" value="$sewa"></td>
              </tr>
              <tr>
              	<td  colspan="2"><br/><b>Tempoh</b><hr/></td>                                       
              </tr>
              <tr>
                <td><div align="right"><strong>Dari :</strong></div></td>
                <td>
                	<input type="text" name="txddari" size="15" value="$tarikhmula">
                	<input type="button" onClick="displayDatePicker('txddari',false,'dmy');" value="Kalendar"/>
                </td>
              </tr>
              <tr>
                <td><div align="right"><strong>Hingga :</strong></div></td>
                <td>
                	<input type="text" name="txdhingga" size="15" value="$tarikhtamat" >
                	<input type="button" onClick="displayDatePicker('txdhingga',false,'dmy');" value="Kalendar"/>
                </td>
              </tr>      
              <tr>
                <td><div align="right"><strong>Bulan :</strong></div></td>
                <td><input type="text" name="txtbulan" size="5" value=""></td>
              </tr>     
              <tr>
                <td><div align="right"><strong>Keluasan :</strong></div></td>
                <td>
                <input type="text" name="luas" size="30" value="$luas">
               $socLuas
                </td>
              </tr>         
              <tr>
              	<td  colspan="2"><br/><b>Alamat</b><hr/></td>                                       
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
                <td>$socNegeri</td>
              </tr>  
               <tr>
                <td><div align="right"><strong>Nama Pegawai</strong></div></td>
                <td><input type="text" name="txtpegawai" size="30" value=""></td>
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
			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="tambahMaklumat()">&nbsp
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

function tambahMaklumat() {
	var hingga = Date.parse(document.f2.txdhingga.value);
	var dari = Date.parse(document.f2.txddari.value);
	var diff_date =  hingga - dari;
	alert (new Date());
var num_years = diff_date/31536000000;
var num_months = (diff_date % 31536000000)/2628000000;
var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;
	alert("this is not a number 1:"+Math.floor(num_years));
	alert("this is not a number 2:"+Math.floor(num_months));
	alert("this is not a number 3:"+Math.floor(num_days));
	//isxNumber(this.txtposkod);
	//return true ;
 	//method="post"
	//document.tbh.action = "?_portal_module=ekptg.view.htp.FrmSewaanSemak";
	//document.tbh.submit();
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

</script>
<script language="JavaScript"> 
//document.forms[0].no_fail.focus(); 
</script>