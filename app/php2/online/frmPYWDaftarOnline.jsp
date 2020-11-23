<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
<!-- 19/8/2020 -->
</style>
#set($saizTxtPerkara="1000")
<p>
	<input name="actionPenyewaan" type="hidden" id="actionPenyewaan" value="$actionPenyewaan"/>
	<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
	<input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
	<input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
	<input name="hitButton" type="hidden" id="hitButton"/>
	<input name="mode" type="hidden" id="mode" value="$mode"/>
	<input name="form_token" type="hidden" value='$!{session.getAttribute("form_token")}'>
	<input name="idJenisTanah" type="hidden" id="idJenisTanah" value="$idJenisTanah"/> 
	<input name="idPHPBorangK" id="idPHPBorangK" type="hidden" value="$idPHPBorangK" /> 
	<input name="idPPTBorangK" id="idPPTBorangK" type="hidden" value="$idPPTBorangK" /> 
	<input name="idHakmilikUrusan" id="idHakmilikUrusan" type="hidden" value="$idHakmilikUrusan" />
	<input name="idJenisPermohonan" type="hidden" id="idJenisPermohonan" value="$idJenisPermohonan"/> 
	<input name="idFailLama" type="hidden" id="idFailLama" value="$idFailLama"/>
	<input name="idSubsuburusan" type="hidden" id="idSubsuburusan" value="$idSubsuburusan"/>
	<input type="hidden" name="namatujuan" id="namatujuan" value="$namatujuan" />
</p>
<table width="100%" border="0">
<!-- TAMBAH MAKLUMAT PEMOHON -->
  <tr>
  	<td><fieldset><legend><strong>MAKLUMAT PEMOHON</strong></legend>
  	<table width="100%" border="0" cellspacing="2" cellpadding="2">
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">Kategori Pemohon</td>
  		<td width="1%">:</td>
  		<td width="70%"> $!pemohon.get("kategoriPemohon")</td>
  	</tr>
  	
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">
  		#if($!pemohon.get("kategoriPemohon") == "INDIVIDU") 
  			Nama Pemohon 
  		#end
  		#if($!pemohon.get("kategoriPemohon") != "INDIVIDU")
  			Nama Syarikat
  		#end
  		</td>
  		<td width="1%">:</td>
  		<td width="70%"> $!pemohon.get("namaPemohon")
  		<input type="hidden" name="txtNama" id="txtNama" value="$!pemohon.get("namaPemohon")"></td>
  	</tr>
 	<tr>
  		<td width="1%"></td>
  		<td width="28%">
  		#if($!pemohon.get("kategoriPemohon") == "INDIVIDU") 
  			No. Kad Pengenalan/MyID 
  		#end
  		#if($!pemohon.get("kategoriPemohon") != "INDIVIDU")
  			No. Pendaftaran Syarikat/MyCoid
  		#end
  		</td>
  		<td width="1%">:</td>
  		<td width="70%">$!pemohon.get("noPengenalan")</td>
  	</tr>
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">Alamat</td>
  		<td width="1%">:</td>
  		<td width="70%">$!pemohon.get("alamat1")<br>$!pemohon.get("alamat2")<br>$!pemohon.get("alamat3")</td>
  	</tr>
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">Poskod</td>
  		<td width="1%">:</td>
  		<td width="70%">$!pemohon.get("poskod")</td>
  	</tr>
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">Negeri</td>
  		<td width="1%">:</td>
  		<td width="70%">$!pemohon.get("negeri")</td>
  	</tr>
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">Bandar</td>
  		<td width="1%">:</td>
  		<td width="70%">$!pemohon.get("bandar")</td>
  	</tr>
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">No. Tel</td>
  		<td width="1%">:</td>
  		<td width="70%">$!pemohon.get("noTel")</td>
  	</tr>
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">No. Faks</td>
  		<td width="1%">:</td>
  		<td width="70%">$!pemohon.get("noFax")</td>
  	</tr>
  	<tr>
  		<td width="1%"></td>
  		<td width="28%">Emel</td>
  		<td width="1%">:</td>
  		<td width="70%">$!pemohon.get("emel")</td>
  	</tr>
  	</fieldset></table>
  	</td>
  </tr>
  
  <!-- MAKLUMAT PERMOHONAN -->
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
	  	 <td width="1%"><span class="style1">*</span></td>
	     <td width="28%">Jenis Permohonan</td>
	     <td width="1%">:</td>
	     <td width="70%">
	       	<select name="socJenisPermohonan"
			id="socJenisPermohonan" onchange="doChangeJenisPermohonan()"
			$inputTextClass class="$inputTextClass">
			<option $selected_0 value="0">SILA PILIH</option>
			<option $selected_1 value="1">PERMOHONAN BAHARU</option>
			<option $selected_2 value="2">PERMOHONAN PERLANJUTAN</option>
			<!-- <option $selected_3 value="3">PERMOHONAN PENGURANGAN KADAR SEWA</option>  -->
		</select>
	</td>
  </tr>
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)        
        <input type="hidden" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" 
        onblur="check_date(this);cekTarikhTerima(this)" size="9" $readonly class="$inputTextClass" />
        <tr>
        	<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
        	<td width="28%">Urusan</td>
          	<td width="1%">:</td>
          	<td width="70%">$selectUrusan</td>
          	<td><input type="hidden" name="idUrusan" id="idUrusan" value="$idUrusan"/></td>
        </tr>
        
        <tr>
          	<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          	<td width="28%">Suburusan</td>
          	<td width="1%">:</td>
          	<td width="70%">$selectSuburusan</td>
          	<td><input type="hidden" name="idSuburusan" id="idSuburusan" value="$idSuburusan"/></td>
        </tr>
        <tr>
			<td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
			<td width="28%">Tujuan</td>
			<td width="1%">:</td>
			<td width="70%">$selectSubSuburusan</td>
			<td><input type="hidden" name="idSubsuburusan" id="idSubsuburusan" value="$idSubsuburusan" /></td>
		</tr>
        #if($idSubsuburusan == 1120101 || $idSubsuburusan == 992016 || $idSubsuburusan == 992048 || $idSubsuburusan == 992022)
        <tr>
          <td width="1%">#if ($mode == 'new')<span class="style1">*</span>#end</td>
          <td width="28%">Keterangan Tujuan</td>
          <td width="1%">:</td>
          <td width="70%">
        	 <textarea name="txtTujuanLain" id="txtTujuanLain" rows="5" cols="50" $readonly class="$inputTextClass" onKeyUp="textCounter(this.form.txtTujuanLain,this.form.remLen1,$!saiztxtTujuanLain);" 
          		onKeyDown="textCounter(this.form.txtTujuanLain,this.form.remLen1,$!saiztxtTujuanLain);" >$beanMaklumatPermohonan.tujuanLain</textarea>
        	</td>
          </tr>
		#end
       
        <!--  <tr>
        	<td width="1%"></td>
        	<td width="28%">Tarikh Surat/Borang</td>
        	<td width="1%">:</td>
        	<td width="70%"><input type="text" name="txttarikhSurat" id="txttarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat"
        		onblur="check_date(this);cekTarikhSurat(this)" size="9" $readonly class="$inputTextClass" /> 
        		#if ($mode != 'view')
        			<a href="javascript:displayDatePicker('txttarikhSurat',false,'dmy');">
        			<img border="0" src="../img/calendar.gif" /></a>
        		#end
        	</td>
        </tr>-->
        <tr>
        	<td width="1%"></td>
        	<td width="28%">No. Rujukan Surat</td>
        	<td width="1%">:</td>
        	<td width="70%">
	        	<input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat"
	        	value="$beanMaklumatPermohonan.noRujukanSurat" $readonly onblur="this.value=this.value.toUpperCase();" 
	        	size="38" maxlength="50" />
			</td>
        </tr>
		#if ($mode != 'view')
		<tr>
			<td valign="top">&nbsp;</td>
			<td valign="top">&nbsp;</td>
			<td valign="top">&nbsp;</td>
			<td>Baki Aksara :&nbsp; 
				<input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtPerkara" />
			</td>
		</tr>
		#end
		#end
         </table>
      </fieldset></td>
  </tr>
  
  #if($idJenisPermohonan == '2')
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0">
  		<tr>
  			<td width="1%"><span class="style1">*</span></td>
    		<td width="28%">Senarai No. Fail Lama</td>
       		<td width="1%">:</td>
        	<td width="70%">$!selectNoFailLama</td>
  		</tr>
  		#if($idFailLama != '99999' && $idFailLama != 'null')
        <tr>
        	<td width="1%"></td>
        	<td width="28%">Urusan</td>
          	<td width="1%">:</td>
          	<td width="70%">$!pemohon.get("namaPemohon")</td>
        </tr>
        <tr>
          	<td width="1%"></td>
          	<td width="28%">Suburusan</td>
          	<td width="1%">:</td>
          	<td width="1%">$!beanMaklumatPermohonan.suburusan</td>
        </tr>
        <tr>
        	<td width="1%"></td>
        	<td width="28%">Tarikh Surat/Borang</td>
        	<td width="1%">:</td>
        	<td width="70%">$!beanMaklumatPermohonan.tarikhSurat</td>
        </tr>
        <tr>
        	<td width="1%"></td>
        	<td width="28%">No. Rujukan Surat</td>
        	<td width="1%">:</td>
        	<td width="70%">$!beanMaklumatPermohonan.noRujukanSurat</td>
        </tr>
        <tr>
        <td width="1%"></td>
        <td width="28%">Perkara</td>
        <td width="1%">:</td>
        <td width="70%">$!beanMaklumatPermohonan.perkara</td>
		</tr>
        #end
      </table>
   </fieldset></td>
  </tr>
  #end 
  
  <!-- JENIS PERMOHONAN 
  <tr>  
  		<td colspan="2"><fieldset>
  			<legend><strong>JENIS PERMOHONAN</strong></legend>
  			<table width="100%" border="0" cellspacing="2" cellpadding="2">
  				<tr>
  					<td width="1%"><span class="style1">*</span></td>
    				<td width="28%">Jenis Permohonan</td>
       				<td width="1%">:</td>
        			<td width="70%">
        				<select name="socJenisPermohonan"
							id="socJenisPermohonan" onchange="doChangeJenisPermohonan()"
							$inputTextClass class="$inputTextClass">
							<option $selected_0 value="0">SILA PILIH</option>
							<option $selected_1 value="1">PERMOHONAN BARU</option>
							<option $selected_2 value="2">PERMOHONAN PERLANJUTAN</option>
							<option $selected_3 value="3">PERMOHONAN PENGURANGAN KADAR SEWA</option>
						</select>
					</td>
  				</tr>
  			</table>
  		</td>
  </tr>-->
  #if($idJenisPermohonan == '1')
  <!-- MAKLUMAT TANAH -->
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT TANAH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        
        <tr>
			<td width="1%"><span class="style1">*</span></td>
			<td width="28%">Jenis Tanah</td>
			<td width="1%">:</td>
			<td width="70%">
				<select name="socJenisTanah" id="socJenisTanah" onchange="doChangeJenisTanah()"
					$inputTextClass class="$inputTextClass">
				<option $selected value="0">SILA PILIH</option>
				<option $selected1 value="1">TANAH MILIK PERSEKUTUAN</option>
				<option $selected2 value="2">TANAH RIZAB PERSEKUTUAN</option>
<!-- 				<option $selected3 value="3">BORANG K</option> -->
				</select>
			</td>
		</tr>
		
		#if($idJenisTanah == '1' || $idJenisTanah == '2')
		#foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
		<tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Negeri</td>
          <td width="1%">:</td>
          <td width="70%">
            $selectNegeri
          </td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Daerah</td>
          <td width="1%">:</td>
          <td width="70%">
          	$selectDaerah
          </td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Mukim</td>
          <td width="1%">:</td>
          <td width="70%">
          	$selectMukim
          </td>
        </tr>
        #if($idJenisTanah == '1')
		<tr>
          <td width="1%"><span class="style1">*</span></td>
          <td>Jenis Hakmilik</td>
          <td>:</td>
          <td>$selectJenisHakmilik</td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">No. Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%">
          <input type="text" name="noMilikTanah" id="noMilikTanah" value="$beanMaklumatTanah.noHakmilik"/></td>
        </tr>
        #end
        #if($idJenisTanah == '2')
        <tr>
        	<td width="1%"><span class="style1">*</span></td>
        	<td width="28%">No. Warta</td>
        	<td width="1%">:</td>
        	<td width="70%">
          		<input type="text" name="noWartaTanah" id="noWartaTanah" value="$beanMaklumatTanah.noWarta"/>
          	</td>
        </tr>
        <tr>
        	<td width="1%"><span class="style1">*</span></td>
        	<td width="28%">Tarikh Warta</td>
        	<td width="1%">:</td>
        	<td width="70%">
          		<input type="text" name="tarikhWartaTanah" id="tarikhWartaTanah" size="9" readonly="readonly" class="disabled" value="$beanMaklumatTanah.tarikhWarta"/><a
							href="javascript:displayDatePicker('tarikhWartaTanah',false,'dmy');"> <img
								border="0" src="../img/calendar.gif" /></a>
          	</td>
        </tr>
        #end
        <tr>
          <td><span class="style1">*</span></td>
          <td>Jenis Lot</td>
          <td>:</td>
          <td>$selectJenisLot</td>
        </tr>
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">No. Lot</td>
          <td width="1%">:</td>
          <td width="70%">
          <input type="text" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.noLot"/></td>
        </tr>
<!--         <tr> -->
<!--           <td width="1%">&nbsp;</td> -->
<!--           <td width="28%">Pegangan Hakmilik</td> -->
<!--           <td width="1%">:</td> -->
<!--           <td width="70%">  -->
<!--           #if ($mode == 'new') -->
<!--             <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" onblur="doChangePeganganHakmilik();"> -->
<!--           #else -->
<!--             <input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatTanah.peganganHakmilik" readonly="readonly" class="disabled"> -->
<!--           #end -->
<!--             <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi"> -->
<!--             <span class="style1">$errorPeganganHakmilik</span>  -->
<!--             <span class="style4"><i><font color="#ff0000">Contoh</font> : </i><span class="style5">160140GRN00000576</span></span> -->
<!--             </td> -->
<!--         </tr> -->
<!--         <tr> -->
<!--           <td width="1%">&nbsp;</td> -->
<!--           <td width="28%">Luas Lot</td> -->
<!--           <td width="1%">:</td> -->
<!--           <td width="70%"> -->
<!--           <input type="text" name="idLuasTanah" id="idLuasTanah" />  -->
<!--           <input type="text" name="luasTanah" id="luasTanah" /></td> -->
<!--         </tr> -->
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td>Unit Luas Ambil</td>
          <td>:</td>
          <td>
          	<select name="socLuas" style="width:200px;" $readonlyPopup class="$disabledPopup" $disabledPopup onchange="javascript:doChangeLuas(this.value)">  
			#set ($listUnitLuas = ["SILA PILIH",
					       "KM - KILOMETER PERSEGI",
					       "H - HEKTAR",
					       "M - METER PERSEGI",
					       "E - EKAR,ROOD,POLE",
					       "K - KAKI PERSEGI",
					       "P - EKAR PERPULUHAN",
					       "D - EKAR,DEPA",
					       "R - RELONG,JEMBA,KAKI PERSEGI",
					       "BN - BATU NAUTIKA"]
			      )
			#set( $counter = 0 )
			#foreach ($i in $listUnitLuas)
			
			#if ($idLuas == $counter) 
              <option selected value="$counter">$i</option>
            #else
			  <option value="$counter">$i</option>
            #end

			#set ($counter = $counter+1)

			#end
			</select>          
          </td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Luas Ambil</td>
          <td>:</td>
          <td>#if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' || $idLuas == '5' || $idLuas == '6' || $idLuas == '9')
            <input type="text" name="txtLuas1" id="txtLuas1" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonlyPopup class="$inputTextClassPopup"/ >
            #elseif ($idLuas == '7')
            <input type="text" name="txtLuas1" id="txtLuas1" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuas2" id="txtLuas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"/ $readonlyPopup class="$inputTextClassPopup">
            #elseif ($idLuas == '8' || $idLuas == '4')
            <input type="text" name="txtLuas1" id="txtLuas1" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuas2" id="txtLuas2" onkeyup="validateNumber(this,this.value);" size="4" $readonlyPopup class="$inputTextClassPopup" onBlur="kiraLuas('$idLuas')"/>
            <input type="text" name="txtLuas3" id="txtLuas3" onKeyUp="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" $readonlypopup class="$inputTextClassPopup"/>
          </td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>Luas Bersamaan</td>
          <td>:</td>
          <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" readonly="readonly" class="disabled"/>
            HEKTAR</td>
        </tr>
        <!-- <tr>
          <td>&nbsp;</td>
          <td>No. Warta</td>
          <td>:</td>
          <td>$beanMaklumatTanah.noWarta
          <input type="hidden" name="noWartaTanah" id="noWartaTanah" value="$beanMaklumatTanah.noWarta"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Warta</td>
          <td>:</td>
          <td>$beanMaklumatTanah.tarikhWarta</td>
        </tr> -->
<!--         <tr> -->
<!--           <td width="1%">&nbsp;</td> -->
<!--           <td width="28%">Kementerian</td> -->
<!--           <td width="1%">:</td> -->
<!--           <td width="70%">$beanMaklumatTanah.kementerian -->
<!--             <input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatTanah.idKementerian"> -->
<!--             <input type="hidden" name="kodKementerian" id="kodKementerian" value="$beanMaklumatTanah.kodKementerian"> -->
<!--           </td> -->
<!--         </tr> -->
<!--         <tr> -->
<!--           <td width="1%">&nbsp;</td> -->
<!--           <td width="28%">Agensi</td> -->
<!--           <td width="1%">:</td> -->
<!--           <td width="70%">$beanMaklumatTanah.agensi</td> -->
<!--         </tr> -->
<!--         <tr> -->
<!--           <td width="1%">&nbsp;</td> -->
<!--           <td width="28%">Kegunaan Tanah</td> -->
<!--           <td width="1%">:</td> -->
<!--           <td width="70%">$beanMaklumatTanah.kegunaanTanah -->
<!--           <input type="hidden" name="kegunaanTanah" id="kegunaanTanah" value="$beanMaklumatTanah.kegunaanTanah" />  -->
<!--           <input type="hidden" name="statusRizab" id="statusRizab" value="$beanMaklumatTanah.statusRizab" /></td> -->
<!--         </tr> -->
        #end
        #end
  		</tr>
  		
  		<!-- MAKLUMAT BORANG K -->
  		#if ($idJenisTanah == '3')
  		#foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
		<tr>
			<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			<td width="28%">Pegangan Hakmilik</td>
			<td width="1%">:</td>
			<td width="70%">
				#if ($mode == 'new') 
					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" 
					value="$beanMaklumatBorangK.peganganHakmilik" onblur="doChangePeganganHakmilikBorangK();" />
					<!--<input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Borang K" onclick="pilihBorangK()" />--> 
				#else 
					<input type="text" name="txtPeganganHakmilik" id="txtPeganganHakmilik" value="$beanMaklumatBorangK.peganganHakmilik"
					readonly="readonly" class="disabled" /> 
				#end 
					<span class="style1">$errorPeganganHakmilik</span>
			</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">No. Lot</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.lot</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Luas Lot</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.luas 
				<input type="hidden" name="idLuasTanah" id="idLuasTanah" value="$beanMaklumatBorangK.idLuas"> 
				<input type="hidden" name="luasTanah" id="luasTanah" value="$beanMaklumatBorangK.luasBersamaan">
			</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">No. Hakmilik</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.hakmilik</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Mukim</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.mukim</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Daerah</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.daerah</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Negeri</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.negeri <input type="hidden" name="idNegeriTanah" id="idNegeriTanah"
				value="$beanMaklumatBorangK.idNegeri">
			</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Kementerian</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.kementerian 
				<input type="hidden" name="idKementerianTanah" id="idKementerianTanah" value="$beanMaklumatBorangK.idKementerian"></td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Agensi</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.agensi</td>
		</tr>
		<tr>
			<td width="1%">&nbsp;</td>
			<td width="28%">Kegunaan Tanah</td>
			<td width="1%">:</td>
			<td width="70%">$beanMaklumatBorangK.kegunaanTanah</td>
		</tr>
		#end
		#end
		</table>
	</fieldset></td>
  </tr> #end
  
  #if ($mode != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  
  <tr>
    <td width="100%" align="center"> 
    #if ($mode == 'new')
      <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Seterusnya" onclick="daftar('$idLuas','$idJenisTanah')"/>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
    #end 
    </td>
  </tr>
</table>

<script>
function pilihTanah() {
	var url = "../x/${securityToken}/ekptg.view.php2.online.FrmPYWOnlinePopupSenaraiTanahView";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanah(idHakmilikAgensi) {
	document.${formName}.idHakmilikAgensi.value = idHakmilikAgensi;
	doAjaxCall${formName}("");
}
function doChangeUrusan() {
	doAjaxCall${formName}("doChangeUrusan");
}
function doChangeSuburusan() {
	doAjaxCall${formName}("doChangeSuburusan");
}
function doChangeSubsuburusan() {
	doAjaxCall${formName}("doChangeSubsuburusan");
}
function doChangePeganganHakmilik() {
	doAjaxCall${formName}("doChangePeganganHakmilik");
}
function doChangeKategori() {
	doAjaxCall${formName}("doChangeKategori");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}

function doChangeMukim() {
	doAjaxCall${formName}("doChangeMukim");
}
function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
}
function doChangeJenisTanah() {
	doAjaxCall${formName}("doChangeJenisTanah");
}
function doChangePeganganHakmilikBorangK() {
	doAjaxCall${formName}("doChangePeganganHakmilikBorangK");
}
function doChangeJenisPermohonan() {
	doAjaxCall${formName}("doChangeJenisPermohonan");
}   
function doChangeNoFailLama() {
	doAjaxCall${formName}("doChangeNoFailLama");
}

function cekTarikhWarta(elmnt) {  
	//CHECK DATE   
	var str1  = document.${formName}.tarikhWartaTanah.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhWartaTanah = new Date(yr1, mon1, dt1);
	
	var currentDate = new Date();
	
	if (tarikhWartaTanah > currentDate){
		alert('Tarikh Warta tidak boleh melebihi dari tarikh hari ini.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
}

function kiraLuas(idLuas){
	var jenisLuas = idLuas;
  
  	// KILOMETER PERSEGI
  	if(jenisLuas == "1"){

		var luasK = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasK = document.${formName}.txtLuas1.value*1;
		}
		var luasH = luasK*100;		
		document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "2"){ //HEKTER
  		
		var luasH = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasH = document.${formName}.txtLuas1.value*1;
		}
		document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "3"){ // METER PERSEGI
    	
		var luasM = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasM = document.${formName}.txtLuas1.value*1;
		}
  	  	var luasH = (luasM*0.0001);
	  	document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

	  	var luasE = 0;
		if (document.${formName}.txtLuas1.value != ''){
			luasE = document.${formName}.txtLuas1.value*1;
		}
	  	var luasR = 0;
		if (document.${formName}.txtLuas2.value != ''){
			luasR = document.${formName}.txtLuas2.value*1;
		}
	  	var luasP = 0;
		if (document.${formName}.txtLuas3.value != ''){
			luasP = document.${formName}.txtLuas3.value*1;
		}
	  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  	document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "5"){ //KAKI PERSEGI
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasAsal = document.${formName}.txtLuas1.value*1;
	  }
	  var luasH = luasAsal*0.0000092;
  	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "6"){	//EKAR PERPULUHAN
  	  
	  var luasAsal = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasAsal = document.${formName}.txtLuas1.value*1;
	  }
	  var luasH = luasAsal*0.405;
	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
  	  
   	} else if(jenisLuas == "7"){ //EKAR,DEPA
  	  
	  var luasE = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasE = document.${formName}.txtLuas1.value*1;
	  }
	  var luasD = 0;
	  if (document.${formName}.txtLuas2.value != ''){
		  luasD = document.${formName}.txtLuas2.value*1;
	  }
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);

   	} else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
  	  
	  var luasR = 0;
	  if (document.${formName}.txtLuas1.value != ''){
		  luasR = document.${formName}.txtLuas1.value*1;
	  }
	  var luasJ = 0;
	  if (document.${formName}.txtLuas2.value != ''){
		  luasJ = document.${formName}.txtLuas2.value*1;
	  }
	  var luasK = 0;
	  if (document.${formName}.txtLuas3.value != ''){
		  luasK = document.${formName}.txtLuas3.value*1;
	  }
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
	}
}
function daftar(idLuas, idJenisTanah) {
	if(document.${formName}.socJenisPermohonan.value == "0"){
		alert('Sila pilih Jenis Permohonan.');
  		document.${formName}.socJenisPermohonan.focus(); 
		return; 
	}
	if(document.${formName}.socUrusan.value == ""){
		alert('Sila pilih Urusan.');
  		document.${formName}.socUrusan.focus(); 
		return; 
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih Suburusan.');
  		document.${formName}.socSuburusan.focus(); 
		return; 
	}
// 	if(document.${formName}.txtTujuanLain.value == ""){
// 		alert('Sila masukkan keterangan.');
//   		document.${formName}.txtTujuanLain.focus(); 
// 		return; 
// 	}
// 	if(document.${formName}.idHakmilikAgensi.value == ""){
// 		alert('Sila pilih Pegangan Hakmilik.');
// 		return; 
// 	}

	if(document.${formName}.socJenisTanah.value == "0"){
		alert('Sila pilih Jenis Tanah.');
  		document.${formName}.socJenisTanah.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih Daerah.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
	if(document.${formName}.socMukim.value == ""){
		alert('Sila pilih Mukim.');
  		document.${formName}.socMukim.focus(); 
		return; 
	}
	if(idJenisTanah == '1') {
		if(document.${formName}.socJenisHakmilik.value == ""){
			alert('Sila pilih Jenis Hakmilik.');
	  		document.${formName}.socJenisHakmilik.focus(); 
			return; 
		}
		if(document.${formName}.noMilikTanah.value == ""){
			alert('Sila isi No. Hakmilik.');
	  		document.${formName}.noMilikTanah.focus(); 
			return; 
		}
	}
	if(idJenisTanah == '2') {
		if(document.${formName}.noWartaTanah.value == ""){
			alert('Sila isi No. Warta.');
	  		document.${formName}.noWartaTanah.focus(); 
			return; 
		}
		if(document.${formName}.tarikhWartaTanah.value == ""){
			alert('Sila isi Tarikh Warta.');
	  		document.${formName}.tarikhWartaTanah.focus(); 
			return; 
		}
	}
	if(document.${formName}.socJenisLot.value == ""){
		alert('Sila pilih Jenis Lot.');
  		document.${formName}.socJenisLot.focus(); 
		return; 
	}
	if(document.${formName}.noLotTanah.value == ""){
		alert('Sila isi No. Lot.');
  		document.${formName}.noLotTanah.focus(); 
		return; 
	}
	if(document.${formName}.socLuas.value == "0"){
		alert('Sila pilih Unit Luas Ambil.');
  		document.${formName}.socLuas.focus(); 
		return; 
	}
	
	if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
		if(document.${formName}.txtLuas1.value == ""){
			alert('Sila masukkan Luas Ambil.');
			document.${formName}.txtLuas1.focus(); 
			return; 
		}
	}
	else
	if(idLuas == '4' || idLuas == '8'){
		if(document.${formName}.txtLuas1.value == ""){
			alert('Sila masukkan Luas Ambil.');
			document.${formName}.txtLuas1.focus(); 
			return; 
		}
		if(document.${formName}.txtLuas2.value == ""){
			alert('Sila masukkan Luas Ambil.');
			document.${formName}.txtLuas2.focus(); 
			return; 
		}
		if(document.${formName}.txtLuas3.value == ""){
			alert('Sila masukkan Luas Ambil.');
			document.${formName}.txtLuas3.focus(); 
			return; 
		}
	} 
	else
	if(idLuas == '7'){
		if(document.${formName}.txtLuas1.value == ""){
			alert('Sila masukkan Luas Ambil.');
			document.${formName}.txtLuas1.focus(); 
			return; 
		}
		if(document.${formName}.txtLuas2.value == ""){
			alert('Sila masukkan Luas Ambil.');
			document.${formName}.txtLuas2.focus(); 
			return; 
		}
	}
	
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPenyewaan.value = "daftarBaru";
		return;
	}
	document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doDaftarBaru";
	document.${formName}.submit();
}
function kembali() {	
	document.${formName}.actionPenyewaan.value = "";
	document.${formName}.submit();
}
function seterusnya() {	
	document.${formName}.actionPenyewaan.value = "paparMaklumatPenyewaan";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
	// otherwise, update 'Baki Aksara' counter
	else
		countfield.value = maxlimit - field.value.length;
}
function pilihBorangK() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupSenaraiBorangKView";
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}


function janaTajuk() {
	if(document.${formName}.socUrusan.value == ""){
		alert('Sila pilih Urusan Sebelum Menjana Tajuk.');
			document.${formName}.socUrusan.focus(); 
		return; 
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih Suburusan Sebelum Menjana Tajuk.');
			document.${formName}.socSuburusan.focus(); 
		return; 
	}
	if(document.${formName}.socSubsuburusan.value == ""){
		alert('Sila pilih Tujuan Sebelum Menjana Tajuk.');
			document.${formName}.socSubsuburusan.focus(); 
		return; 
	}
	if(document.${formName}.idHakmilikAgensi.value == "" && document.${formName}.idHakmilikSementara.value == ""){
		alert('Sila pilih Pegangan Hakmilik Sebelum Menjana Tajuk.');
		document.${formName}.idHakmilikAgensi.focus(); 
		return; 
	}
	var strTajuk = " ";
	var strTujuan = " ";
	var milikOrRizab = " ";
	var namaPemohon = document.${formName}.txtNama.value;
	var str1 = document.${formName}.noLotTanah.value;
	var str2 = document.${formName}.noMilikTanah.value;
	var str3 = document.${formName}.namaMukimTanah.value;
	var str4 = document.${formName}.namaDerahTanah.value;	
	var str5 = document.${formName}.namaNegeriTanah.value;	
	var str6 = document.${formName}.noWartaTanah.value;
	var statusRizabTnh = document.${formName}.statusRizab.value;
	var strTujuan = " ";
	
	if(document.${formName}.socSuburusan.value != "") {
		strTujuan = document.${formName}.namatujuan.value;
	}
	
	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str6;
	}
	
	if(document.${formName}.socUrusan.value == "7"){
		if(document.${formName}.socSuburusan.value == "35"){
			strTajuk = "PERMOHONAN PENYEWAAN RUANG BANGUNAN " + str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan;
		} 
		else if(document.${formName}.socSuburusan.value == "36" || document.${formName}.socSuburusan.value == "37"){
			strTajuk = "PERMOHONAN PENYEWAAN TANAH PERSEKUTUAN " + str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan;
		}
		else{
			strTajuk;
		}
	} 
	else if(document.${formName}.socUrusan.value == "12"){
		strTajuk = "PERMOHONAN MENGELUARKAN HASIL " + strTujuan + " DI " +  str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan;
	} 
	else if(document.${formName}.socUrusan.value == "13"){
		strTajuk = "PERMOHONAN MENGELUARKAN " + strTujuan + " DI " +  str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan;
	} 
	else{
		strTajuk;
	}
	document.${formName}.txtPerkara.value = strTajuk;
}

</script>