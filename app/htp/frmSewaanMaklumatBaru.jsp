	#parse ("app/htp/frmSewaanInfo.jsp")			

<fieldset>
  <legend><strong>Maklumat Sewaan 211</strong></legend>
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
    <table border="1" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top"><table width="100%" border="0">
                 <tr><td colspan="2"><b>Jumlah</b><hr/></td></tr>
              <tr>
                <td><div align="right"><strong>Jumlah Sewaan Bulanan(RM) :</strong></div></td>
                	#set ($iii = $hakmilikbangunan.sewabulanan)
                	#if ( $iii == "" )
						#set ( $sewa = "" )
					#else
						#set ( $sewa = $iii )
					#end
                <td><input type="text" name="txtsewa" size="5" onkeydown="isxNumber('txtsewa');"></td>
              </tr>
              <tr>
              	<td  colspan="2"><br/><b>Tempoh</b><hr/></td>                                       
              </tr>
              <tr>
                <td><div align="right"><strong>Dari :</strong></div></td>
                <td>
                	<input type="text" name="txddari" size="15" value="">
                	<input type="button" onClick="displayDatePicker('txddari',false,'dmy');" value="Kalendar"/>
                </td>
              </tr>
              <tr>
                <td><div align="right"><strong>Hingga :</strong></div></td>
                <td>
                	<input type="text" name="txdhingga" size="15" value="" >
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
                <input type="text" name="txtluas" size="5" value="">
                $socLuas
                </td>
              </tr>         
              <tr>
              	<td  colspan="2"><br/><b>Alamat</b><hr/></td>                                       
              </tr>          
              <tr>
                <td><div align="right"><strong>Alamat Lokasi/Premis :</strong></div></td>
                <td><input type="text" name="txtalamat1" size="30" value=""></td>
              </tr>
                <tr>
                <td><div align="right"><strong></strong></div></td>
                <td><input type="text" name="txtalamat2" size="30" value=""></td>
              </tr>            
              <tr>
                <td><div align="right"><strong></strong></div></td>
                <td><input type="text" name="txtalamat3" size="30" value=""></td>
              </tr>    
                <tr>
                <td><div align="right"><strong>Poskod</strong></div></td>
                <td><input type="text" name="txtposkod" maxlength="5" size="5" 
                onkeyup="isxNumber('txtposkod');"></td>
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
                <td><input type="text" name="txtpegawai" size="40" value=""></td>
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
			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="tambahMaklumat2()">&nbsp
			<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal">&nbsp;
			<input type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti()">&nbsp;
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="back()">&nbsp;
			<input type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="back(-2)">&nbsp;
		</td>
      </tr>
          <input type="hidden" name="command">
   		<input type="hidden" name="id_kemaskini" value="$permohonanInfo.idpermohonan">
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

function tambahMaklumat2() {

	var dari_bulan;
	var dari_hari;
	var dari_tahun;
	var hingga_bulan;
	var hingga_hari;
	var hingga_tahun;
  	if ( document.f2.txtsewa.value == "" ) { 
  		alert('Sila masukkan maklumat sewa terlebih dahulu.');
  		document.f2.txtsewa.focus(); return; 
  	}
    if ( document.f2.txddari.value == "" ) { 
  		alert('Sila masukkan tarikh dari terlebih dahulu.');
    	//document.freg.txddari.focus(); 
    	return; 
    }
   	if ( document.f2.txdhingga.value == "" ) { 
  		alert('Sila masukkan tarikh hingga terlebih dahulu.');
    	return; 
    } 
    if ( document.f2.txtluas.value == "" ) { 
    	alert('Sila masukkan maklumat luas terlebih dahulu.');
    	document.f2.txtluas.focus(); 
    	return; 
    }
    if ( document.f2.socLuas.value == "" ) { 
    	alert('Sila masukkan maklumat lot terlebih dahulu.');
    	document.f2.socLuas.focus(); 
    	return; 
    }
     if ( document.f2.socLuas.value == "" ) { 
    	alert('Sila masukkan maklumat lot terlebih dahulu.');
    	document.f2.socLuas.focus(); 
    	return; 
    }    
    if ( document.f2.txtalamat1.value == "" ) { 
    	alert('Sila masukkan alamat 1 terlebih dahulu.');
    	document.f2.txtalamat1.focus(); 
    	return; 
    }    
    if ( document.f2.txtalamat2.value == "" ) { 
    	alert('Sila masukkan alamat 2 lot terlebih dahulu.');
    	document.f2.txtalamat2.focus(); 
    	return; 
    }
    if ( document.f2.txtposkod.value == "" ) { 
    	alert('Sila masukkan maklumat poskod terlebih dahulu.');
    	document.f2.txtposkod.focus(); 
    	return; 
    } 
   	if ( document.f2.socDaerah.value == "" ) { 
    	alert('Sila pilih maklumat daerah terlebih dahulu.');
    	document.f2.socDaerah.focus(); 
    	return; 
    }
    
      
    
	hingga_bulan = document.f2.txdhingga.value.substring(3,5);
	hingga_hari = document.f2.txdhingga.value.substring(0,2);
	hingga_tahun = document.f2.txdhingga.value.substring(6,10);
	var hinggatemp = hingga_bulan+"/"+hingga_hari+"/"+hingga_tahun;
	dari_bulan = document.f2.txddari.value.substring(3,5);
	dari_hari = document.f2.txddari.value.substring(0,2);
	dari_tahun = document.f2.txddari.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var dari = Date.parse(daritemp);
	var hingga = Date.parse(hinggatemp);

	var diff_date =  hingga - dari;
	
	var num_years = diff_date/31536000000;
	var num_months = (diff_date % 31536000000)/2628000000;
	var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;
	//alert("this is not a number 1:"+Math.floor(num_years));
	//alert("bulan:"+num_months+"cxvcxvcxv:"+Math.floor(num_months));
	//alert("this is not a number 3:"+Math.floor(num_days));
	document.f2.txtbulan.value = Math.floor(num_months);

	/*var my_string = document.f2.txtposkod.value;
	if(isNaN(my_string)){
		alert("this is not a number ");
	}else{
		alert("this is a number ");
	}*/
	//isxNumber(this.txtposkod);
	//return true ;
	document.f2.command.value = "maklumatsimpan";
	method="post"
	//document.tbh.action = "?_portal_module=ekptg.view.htp.FrmSewaanSemak";
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

function isxNumber(dateFieldName){
	var targetDateField = document.getElementsByName (dateFieldName).item(0);
 	var val = targetDateField.value;
	if(val == null || isNaN(val)) {
   		alert("Sila masukkan nombor yang betul.");
   		//val.value = "";
   		//dateFieldName.value = 
   		//RemoveNonNumeric(val);
  		val.focus();
   		return;
	}
}

</script>
<script language="JavaScript"> 
//document.forms[0].no_fail.focus(); 
</script>