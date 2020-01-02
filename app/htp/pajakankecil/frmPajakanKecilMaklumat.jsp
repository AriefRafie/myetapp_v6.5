<!--frmPajakanKecilMaklumatBaru-->
#set ($date = "readonly class=disabled" )
#set ($pagemode = $pageMode)	
	######set ( $luas = "0.00")
    #set ( $luas = "0")
		#set ( $tarikhmula = "" )
		#set ( $tarikhtamat = "")
		#set ( $alamat1 ="")
		#set ( $alamat2 = "")
		#set ( $alamat3 = "")
		#set ( $poskod  = "")
	
	#if ( $pagemode == "3"  )
    #set ($inputstylerep = "" )
    #set ($date = "readonly class=disabled" )
		##set ( $sewa = $util.formatDecimal($hakmilikbangunaninfo.sewabulanan) )
		#set ( $sewa = $hakmilikbangunaninfo.sewabulanan)
		#set ( $alamat1 = $hakmilikbangunaninfo.alamat1)
		#set ( $alamat2 = $hakmilikbangunaninfo.alamat2)
		#set ( $alamat3 = $hakmilikbangunaninfo.alamat3)
		#set ( $poskod  = $hakmilikbangunaninfo.poskod)
		#set ( $luas = $hakmilikbangunaninfo.luas )
		#set ( $tarikhmula = $hakmilikbangunaninfo.tmula )
		#set ( $tarikhtamat = $hakmilikbangunaninfo.ttamat )
        #set ( $bulan = $hakmilikbangunaninfo.bulan )
        #set ( $tahun = $hakmilikbangunaninfo.tahun )
	#elseif( $pagemode == "4"  )
    	#set ($inputstylerep = "readonly class=disabled" )
		#set ( $sewa = $hakmilikbangunaninfo.sewabulanan)
		#set ( $alamat1 = $hakmilikbangunaninfo.alamat1)
		#set ( $alamat2 = $hakmilikbangunaninfo.alamat2)
		#set ( $alamat3 = $hakmilikbangunaninfo.alamat3)
		#set ( $poskod  = $hakmilikbangunaninfo.poskod)
		#set ( $luas = $hakmilikbangunaninfo.luas )
		#set ( $tarikhmula = $hakmilikbangunaninfo.tmula )
		#set ( $tarikhtamat = $hakmilikbangunaninfo.ttamat )
        #set ( $bulan = $hakmilikbangunaninfo.bulan )
        #set ( $tahun = $hakmilikbangunaninfo.tahun )
     #elseif( $pagemode == "5"  )
    	#set ($inputstylerep = "" )
        #set ($date = "readonly class=disabled" )
		#set ( $sewa = $hakmilikbangunaninfo.sewabulanan)
		#set ( $alamat1 = $hakmilikbangunaninfo.alamat1)
		#set ( $alamat2 = $hakmilikbangunaninfo.alamat2)
		#set ( $alamat3 = $hakmilikbangunaninfo.alamat3)
		#set ( $poskod  = $hakmilikbangunaninfo.poskod)
		#set ( $luas = $hakmilikbangunaninfo.luas )
		#set ( $tarikhmula = $hakmilikbangunaninfo.tmula )
		#set ( $tarikhtamat = $hakmilikbangunaninfo.ttamat )
        #set ( $bulan = $hakmilikbangunaninfo.bulan )
        #set ( $tahun = $hakmilikbangunaninfo.tahun )
	#end



<br/>
#parse('app/htp/frmPajakanKecilPaging.jsp')
<!-- <br/> -->

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
	#parse ("app/htp/frmPajakanKecilInfo.jsp")			
	</td>
  </tr>
<!-- <tr>
  
   	<td>&nbsp;</td>
  
  </tr>	-->
  <tr>
    <td>
    <fieldset><legend><strong>MAKLUMAT SEWAAAN</strong></legend>
    <table width="100%" border="0">
      <tr>
        <td width="50%" >
        <fieldset>
        <legend><strong>JUMLAH / TEMPOH</strong></legend>
        <table width="100%" border="0">
          <tr>
			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Jumlah Sewaan Bulanan(RM)</div>
				</div>
			</td>
            <td width="1%">:</td>
            <td width="58%">
	         <input name="txtsewa" type="text" value="$!sewa" $inputstylerep onkeyup="validateNumber(this,this.value);" onblur="this.value=this.value.toUpperCase();formatCurrencyPertama(this.value);"  />
            </td>
          </tr>
          <tr>
			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Tempoh (Dari)</div>
				</div>            
            </td>
            <td width="1%">:</td>
            <td width="58%"> <input type="text" name="txddari" size="15" value="$tarikhmula" $inputstylerep onkeyup="validateNumber(this,this.value);" onblur="javascript:check_date(this);validateTarikhSemasaIsNull(document.${formName}.txddari);"/>
	        <a href="javascript:displayDatePicker('txddari',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
            </td>
          </tr>
          <tr>
			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Tempoh (Hingga)</div>
				</div> 
			</td>
            <td width="1%">:</td>
            <td width="58%">
            <input type="text" name="txdhingga" size="15" value="$tarikhtamat" onkeyup="validateNumber(this,this.value);" onBlur="check_date(this);trans_date21(this.value);" $inputstylerep />
	        <a href="javascript:displayDatePicker('txdhingga',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
            </td>
          </tr>
          <tr>
			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Tahun</div>
				</div>             
            </td>
            <td width="1%">:</td>
            <td width="58%"><input type="text" name="txttahun" size="5" maxlength="4" value="$tahun" onkeyup="validateNumber(this,this.value);" $inputstylerep/> </td>
          </tr>
          <tr>
			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Bulan</div>
				</div>             
            </td>
            <td width="1%">:</td>
            <td width="58%"><input type="text" name="txtbulan" size="5" maxlength="2" value="$bulan" onkeyup="validateNumber(this,this.value);" $inputstylerep/></td>
          </tr>
          <tr>
			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Keluasan</div>
				</div>             
            </td>
            <td width="1%">:</td>
            <td width="58%"><input type="text" name="txtluas" size="5" value="$luas" onkeyup="validateNumber(this,this.value);" $inputstylerep /></td>
          </tr>
          <tr>
 			<td width="1%">&nbsp;</td>
            <td width="40%">&nbsp;</td>
            <td width="1%">&nbsp;</td>
            <td width="58%">$socLuas</td>
          </tr>
        </table>
        </fieldset>
        
        </td>
        
        <td width="50%" valign="top">
        
        <fieldset>
        <legend><strong>ALAMAT</strong></legend>
        <table width="100%" border="0">
          <tr>
 			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Alamat Lokasi/Premis</div>
				</div>             
            </td>
            <td width="1%">:</td>
            <td width="58%">	<input type="text" name="txtalamat1" size="30" value="$alamat1" onblur="this.value=this.value.toUpperCase()" $inputstylerep /></td>
          </tr>
          <tr>
 			<td width="1%">&nbsp;</td>
            <td width="40%">&nbsp;</td>
            <td width="1%">:</td>
            <td width="58%">	<input type="text" name="txtalamat2" size="30" value="$alamat2" onblur="this.value=this.value.toUpperCase()" $inputstylerep /></td>
          </tr>
          <tr>
 			<td width="1%">&nbsp;</td>
            <td width="40%">&nbsp;</td>
            <td width="1%">:</td>
            <td width="58%"><input type="text" name="txtalamat3" size="30" value="$alamat3" onblur="this.value=this.value.toUpperCase()" $inputstylerep /></td>
          </tr>
          <tr>
 			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Poskod</div>
				</div>             
            </td>
            <td width="1%">:</td>
            <td width="58%"><input type="text" name="txtposkod" maxlength="5" size="5" onkeyup="validatePoskod(this,this.value);" value="$poskod" $inputstylerep /></td>
          </tr>
          <tr>
 			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Daerah</div>
				</div>             
            </td>
            <td width="1%">:</td>
            <td width="58%">$socDaerah</td>
          </tr>
          <tr>
 			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Negeri</div>
				</div>             
            </td>
            <td width="1%">:</td>
            <td width="58%">$socNegeri</td>
          </tr>
          <!--<tr>
 			<td width="1%">&nbsp;</td>
            <td width="40%">
            	<div align="right" class="labelinput">
					<div align="left">Nama Pegawai</div>
				</div>             
            </td>
            <td width="1%">:</td>
            <td width="58%">$socPegawai</td>
          </tr> -->
                    <input type="hidden" name="socPegawai"/>
          
        </table>
        </fieldset>
        
        
        
        </td>
      </tr>
      </table>
    </fieldset>
    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center">
	#if (!$!jenisAkses.equals('Readonly'))
    	#if($pagemode == "" )
		<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="tambahMaklumatsewaansimpan('$permohonanInfo.idpermohonan')" />
		#end
				
        #if($pagemode == "3")
		<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="tambahMaklumatsewaansimpan('$permohonanInfo.idpermohonan')" />
		<input class="stylobutton" type="reset" name="cmdReset" id="cmdReset" value="Kosongkan" />
		#elseif($pagemode == "4")
		<input  class="stylobutton"type="button" name="cmdKemaskini" id="cmdsblm" value="Kemaskini" onclick="kemaskiniMaklumatSewaan()" />
		<!-- Komen pada 15/06/2010
		<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backPre('$permohonanInfo.idfail','$permohonanInfo.idpermohonan')" />
		<input  class="stylobutton"type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti8('$permohonanInfo.idpermohonan')" /> -->
        #else
        <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateMaklumatSewaan('$permohonanInfo.idpermohonan')" />
        <!-- Komen pada 15/06/2010
        <input  class="stylobutton"type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti8('$permohonanInfo.idpermohonan')" />
		<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backPre('$permohonanInfo.idfail','$permohonanInfo.idpermohonan')" /> -->
        #end
	
  #end    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
	<input type="hidden" name="idHakmilikBangunan" value="$hakmilikbangunaninfo.idhakmilikbangunan" />
	<input type="hidden" name="id_kemaskini" value="$permohonanInfo.idpermohonan" />
   	<input type="hidden" name="langkah" value="0" />
   	<input type="hidden" name="fail" />
   	<input type="hidden" name="pagemode" value="$pagemode" />  
  
</table>

