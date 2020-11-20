<style type="text/css">
<!--

body {
text-align:center;
font-family:serif;
background:#FAF8CC;
}

.style1 {color: #0000FF}

.table_header {
color:#FFF;
background-color:#960;
font-weight:400;
border-style:10px solid #FFF;
}

.module_content,td.row1,.row1 {
background-color:#FAF8CC;
}

.table_row2,.module_content_bg {
background-color:#F2E3E9;
}

td.row2,td.selected,.row2 {
background-color:#ebbc5d;
}

.stylobutton {
width:140px;
}

.stylobutton100 {
width:100px;
}

input.button {
border:0.05em solid;
cursor:pointer;
font-weight:700 !important;
overflow:visible;
width:103px;
padding:0 0.25em;
}
-->
</style>
<link rel="stylesheet" type="text/css" href="../../css/eTapp_HTP.css" />
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
			<fieldset><legend><strong>URUSAN TANAH </strong></legend>
			<table width="100%">
			<tr>
            	<td>
					<fieldset>	<legend>MAKLUMAT SEMASA</legend>
				<!-- Ajax: Maklumat Detail -->
					<div id="div_maklumatsemasa"></div>

				<!-- SENARAI URUSAN -->
					<table width="100%">
						<tr class="table_header">
							<td scope="row" width="5%" align="center">Bil.</td>
							<td width="20%">No. Fail</td>
							<td width="35%">Tujuan</td>
							<td width="20%">Urusan</td>
							<td width="20%">Status Semasa</td>
						</tr>

			 	#if($!senaraiUrusanLain.size()!=0)
		           	#foreach($list in $!senaraiUrusanLain)
		                #set( $i = $velocityCount )
		         		#if ( ($i % 2) != 1 )
		              		 #set( $row = "row2" )
		         		#else
		               		 #set( $row = "row1" )
		         		#end

			        	<tr>
			        		<td class="$row" align="center">$list.bil</td>
			        		<td class="$row">$!list.noFail</td>
			            	<td class="$row">$!list.tujuan</td>
			            	<td class="$row">$!list.urusan</td>
			            	<td class="$row">$!list.tindakan</td>
			        	</tr>
		        	#end

		     	#else
		        		<tr>
		        			<td colspan="5">Tiada rekod</td>
		        		</tr>
		     	#end
					</table>
				<!-- END SENARAI URUSAN SEMASA -->
					</fieldset>
	            </td>
       		</tr>
       		<tr>
            	<td>
					<fieldset>	<legend>MAKLUMAT PENYEWAAN</legend>

					<!-- NOT USE. REPLACE WITH DIV -->

					<!-- Ajax: Maklumat Detail -->
					<div id="div_maklumatPenyewaan"></div>

					<!-- LIST PENYEWAAN -->
					<table width="100%">
						<tr class="table_header">
							<td scope="row" width="5%" align="center">Bil.</td>
							<td width="20%">No. Fail Penyewaan</td>
							<td width="20%">Penyewa</td>
							<td width="35%">Tujuan</td>
							<td width="10%">Tarikh Mula</td>
							<td width="10%">Tarikh Tamat</td>
						</tr>

			 	#if($listPHPPenyewaan.size()!=0)
		           	#foreach($list in $listPHPPenyewaan)
		                #set( $i = $velocityCount )
		         		#if ( ($i % 2) != 1 )
		              		 #set( $row = "row2" )
		         		#else
		               		 #set( $row = "row1" )
		         		#end

			        	<tr>
			        		<td class="$row" align="center">$list.BIL</td>
			        		<td class="$row">
			        			<a href="javascript:viewDetailSewa('$!list.ID_PERMOHONAN')"><font color="blue">$!list.NO_FAIL</font></a>
			        			<input type="hidden" name="id_permohonan" value="$!list.ID_PERMOHONAN">
			        		</td>
			            	<td class="$row">$list.NAMA_PEMOHON</td>
			            	<td class="$row">$list.TUJUAN</td>
			            	<td class="$row">$list.TARIKH_MULA_PERJANJIAN</td>
			            	<td class="$row">$list.TARIKH_TAMAT_PERJANJIAN</td>
			        	</tr>
		        	#end

		     	#else
		        		<tr>
		        			<td colspan="6">Tiada rekod</td>
		        		</tr>
		     	#end
					</table>
		<!-- END LIST PENYEWAAN -->
					</fieldset>
	            </td>
       		</tr>
       		<tr>
				<td>
					<fieldset>	<legend>MAKLUMAT PAJAKAN</legend>
						<div id="divmaklumatpajakan"></div>

					<table width="100%">
						<tr class="table_header">
							<td scope="row" width="5%" align="center">Bil.</td>
							<td width="20%">No. Fail Pajakan</td>
							<td width="20%">Nama Pemajak</td>
							<td width="35%">Tujuan</td>
							<td width="10%">Tarikh Mula</td>
							<td width="10%">Tarikh Tamat</td>
						</tr>
				#if($!senaraiPajakan.size()!=0)
		           	#foreach($list in $!senaraiPajakan)
		                #set( $i = $velocityCount )
		         		#if ( ($i % 2) != 1 )
		              		 #set( $row = "row2" )
		         		#else
		               		 #set( $row = "row1" )
		         		#end

			        	<tr>
			        		<td class="$row" align="center">$list.bil</td>
			        		<td class="$row">
			        			<a href="javascript:terperinciPajakan('$!list.idPermohonan','$!list.idHakmilik')"><font color="blue">$!list.noFail</font></a>
			        			<input type="hidden" name="id_permohonan" value="$!list.idPermohonan">
			        			<input type="hidden" name="idHakmilik" value="$!list.idHakmilik">
			        		</td>
			            	<td class="$row">$!list.pemohon</td>
			            	<td class="$row">$!list.tujuan</td>
			            	<td class="$row">
				            	#if($!list.tarikhMula.equals("01/01/1900"))
				            	#else
				            		$!list.tarikhMula
				            	#end
				            </td>
			            	<td class="$row">
			            		#if($!list.tarikhTamat.equals("01/01/1900"))
				            	#else
				            		$!list.tarikhTamat
				            	#end
				            </td>
			        	</tr>
		        	#end

		     	#else
		        		<tr>
		        			<td colspan="6">Tiada rekod</td>
		        		</tr>
		     	#end
					</table>
					</fieldset>
				</fieldset>
	            </td>
       		</tr>
			<tr>
				<td>
	            	<fieldset>	<legend>MAKLUMAT PENSWASTAAN</legend>
					<table width="100%">
				       	<tr class="table_header">
				            <td scope="row" width="5%" align="center">Bil.</td>
				           	<td width="20%">No. Fail</td>
				         	<td width="75%">Tindakan Lanjut</td>
				      	</tr>
				#set ($list = "")
			 	#if ($!swasta.size() > 0)
				    #foreach ($list in $swasta)
			        	#if ($list.bil == '')
				       		#set( $row = "row1" )
				      	#elseif (($list.bil % 2) != 0)
				        	#set( $row = "row1" )
				      	#else
				       		#set( $row = "row2" )
				     	#end
					 	<tr>
					   		<td class="$row" align="center"><a href="javascript:paparHakmilik('$!list.idHakmilikUrusan')" >$list.bil.</a></td>
					      	<td class="$row">$list.noFail</td>
					     	<td class="$row">
					            	$!list.tindakan
					            	#if($list.tindakan == 'PAJAK SEMUA')
					          			<a href="javascript:daftarPajakanSemua('$list.idHakmilikUrusan')" class="style1">.</a>
					          		#end
					     	</td>
					  	</tr>
			     	#end

				#else
						<tr>
			            	<td class="row1" colspan="3">Tiada Rekod</td>
						</tr>
			  	#end
					</table>
					</fieldset>
	       		</td>
      		</tr>
		</table>
	</td>
  </tr>
  <tr>
    <td>
    	<fieldset><legend><strong>MAKLUMAT TANAH</strong></legend>
	#set($idHakmilikUrusan = "0")
        #foreach($beanMaklumatTanah in $BeanMaklumatTanah)
       		#set($idHakmilik = $beanMaklumatTanah.idHakmilik)
        	#set($idHakmilikUrusan = $beanMaklumatTanah.idHakmilikUrusan)
            #set($peganganHakmilik = $beanMaklumatTanah.peganganHakmilik)
			#set($jenisLot = $beanMaklumatTanah.jenisLot)
            #set($noLot = $beanMaklumatTanah.noLot)
            #set($luasLot = $beanMaklumatTanah.luasLot)
 			##set($socLuas_ = $beanMaklumatTanah.idLuasPajakanBer)
           	#set($txtLuas = $beanMaklumatTanah.luasBersamaan)
            #set($jenisHakmilik = $beanMaklumatTanah.jenisHakmilik)
            #set($noHakmilik = $beanMaklumatTanah.noHakmilik)
            #set($noWarta = $beanMaklumatTanah.noWarta)
            #set($tarikhWarta = $beanMaklumatTanah.tarikhWarta)
            #set($mukim = $beanMaklumatTanah.mukim)
            #set($daerah = $beanMaklumatTanah.daerah)
            #set($negeri = $beanMaklumatTanah.negeri)
            #set($kategoriTanah = $beanMaklumatTanah.kategoriTanah)
            #set($subKategoriTanah = $beanMaklumatTanah.subKategoriTanah)
            #set($syarat = $beanMaklumatTanah.syarat)
            #set($sekatan = $beanMaklumatTanah.sekatan)
            #set($kementerian = $beanMaklumatTanah.kementerian)
            #set($agensi = $beanMaklumatTanah.agensi)
            #set($namaSeksyen = $beanMaklumatTanah.namaSeksyen)
        #end

            <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    <tr>
                      <td width="50%">
                          <table width="100%"  cellpadding="2" cellspacing="2" border="0">
                              #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
                              <tr>
                                  <td width="37%" align="right">PEGANGAN HAKMILIK :</td>
                                  <td width="63%"><font color="blue">$peganganHakmilik</font>
                                 </td>
                            </tr>
                              <tr>
                                  <td align="right">JENIS LOT :</td>
                                  <td><font color="blue">$jenisLot</font></td>
                            </tr>
                              <tr>
                                  <td align="right">NO. LOT :</td>
                                  <td><font color="blue">$noLot</font></td>
                            </tr>
                            <tr>
                                  <td align="right">LUAS :</td>
                                  <td><font color="blue">$luasLot</font></td>
                            </tr>
                            <tr>
                                  <td align="right">JENIS HAKMILIK :</td>
                                  <td><font color="blue">$jenisHakmilik</font></td>
                            </tr>
                            <tr>
                                  <td align="right">NO. HAKMILIK :</td>
                                  <td><font color="blue">$noHakmilik</font></td>
                            </tr>
                            <tr>
                                  <td align="right">NO. WARTA :</td>
                                  <td><font color="blue">$noWarta</font></td>
                            </tr>
                            <tr>
                                  <td align="right">TARIKH WARTA :</td>
                                  <td><font color="blue">$tarikhWarta</font></td>
                            </tr>
                            <tr>
                                  <td align="right">NEGERI :</td>
                                  <td><font color="blue">$negeri</font></td>
                            </tr>
                            <tr>
                                  <td align="right">DAERAH :</td>
                                  <td><font color="blue">$daerah</font></td>
                            </tr>
                            <tr>
                                  <td align="right">MUKIM :</td>
                                  <td><font color="blue">$mukim</font></td>
                            </tr>
                            <tr>
                                  <td align="right">SEKSYEN :</td>
                                  <td><font color="blue">$namaSeksyen</font></td>
                            </tr>

                          </table>
                          </td>
                            <td width="50%" valign="top">
                                <table width="100%"  cellpadding="2" cellspacing="2" border="0">
                                    <tr>
                                        <td width="42%" align="right">KATEGORI TANAH</td>
                                        <td width="1%">:</td>
                                        <td width="57%"><font color="blue">$kategoriTanah</font></td>
                                  </tr>
                                    <tr>
                                        <td width="42%" align="right">SUBKATEGORI TANAH</td>
                                        <td width="1%">:</td>
                                        <td width="57%"><font color="blue">$subKategoriTanah</font></td>
                                  </tr>
                                    <tr>
                                        <td width="42%" align="right">SYARAT NYATA</td>
                                        <td width="1%" valign="top">:</td>
                                        <td width="57%"><font color="blue">$syarat</font></td>
                                  </tr>
                                  <!--<tr>
                                        <td align="right">&nbsp;</td>
                                  </tr>
                                  <tr>
                                        <td align="right">&nbsp;</td>
                                  </tr> -->
                                  <tr>
                                        <td width="42%" align="right" valign="top">SEKATAN KEPENTINGAN</td>
                                        <td width="1%" valign="top">:</td>
                                        <td width="57%"><font color="blue">$sekatan</font></td>
                                  </tr>
                                 <!--<tr>
                                        <td align="right">&nbsp;</td>
                                  </tr>
                                  <tr>
                                        <td align="right">&nbsp;</td>
                                  </tr> -->
                                  <tr>
                                        <td width="42%" align="right" valign="top">KEMENTERIAN</td>
                                        <td width="1%" valign="top">:</td>
                                        <td width="57%"><font color="blue">$kementerian</font></td>
                                  </tr>
                                  <!--<tr>
                                        <td align="right">&nbsp;</td>
                                  </tr> -->
                                  <tr>
                                        <td width="42%" align="right" valign="top">AGENSI</td>
                                        <td width="1%" valign="top">:</td>
                                        <td width="57%"><font color="blue">$agensi</font></td>
                                  </tr>
                                  <tr>
                                  		<td width="42%" align="right">UNIT LUAS PAJAKAN</td>
                                        <td width="1%">:</td>
                                        <td width="57%">
											#parse("app/htp/unit_luas.jsp")
                                       	</td>
                            	</tr>
                           		<tr>
                                  		<td width="42%" align="right">LUAS ASAL</td>
                                        <td width="1%">:</td>
                                        <td width="57%">
											#if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6' || $socLuas == '9')
												<input type="text" name="txtluasasal1" class="$disabled" id="txtluasasal1" value="$!txtLuasAsal"  size="20" maxlength="8" onkeyup="validateNumber(this,this.value);" $disability onblur="format5Decimal(this,this.value,'');kiraLuasAsal('$socLuas');"/>
											#elseif($socLuas == '8' || $socLuas == '4')
												<input type="text" name="txtluasasal2" class="$disabled" id="txtluasasal2" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" />
												<input type="text" name="txtluasasal3" class="$disabled" id="txtluasasal3" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" />
												<input type="text" name="txtLuasasal4" class="$disabled" id="txtluasasal4" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" onblur="kiraLuasAsal('$socLuas');"/>
											#elseif($socLuas == '7')
												<input type="text" name="txtluasasal5" class="$disabled" id="txtluasasal5" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" />
												<input type="text" name="txtluasasal6" class="$disabled" id="txtluasasal6" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" onblur="kiraLuasAsal('$socLuas');"/>
											#else
												<input type="text" name="txtluasasal1" value="$!txtLuasLama" id="txtluasasal1" size="20" maxlength="8" $disability $readability onkeyup="validateNumber(this,this.value);" onblur="kiraLuasAsal('$socLuas');"/>
											#end
												<input type="hidden" name=txtluasasal value=$!Luas>
												<input type="hidden" name="txtluaslamasal" id="txtluaslamasal" value="$!Luas" />
												<input type="hidden" name="txtluasgabungasal" id="txtluasgabungasal" value="$!txtLuasLama" />
                                        </td>
                            </tr>
                            <tr>
                                  		<td width="42%" align="right">LUAS PAJAKAN</td>
                                        <td width="1%">:</td>
                                        <td width="57%">
											#if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6' || $socLuas == '9')
												<input value="$!txtLuasLama" name="txtLuas1" type="text" class="$disabled" id="txtLuas1" size="20" maxlength="8" onkeyup="validateNumber(this,this.value);" $disability onblur="format5Decimal(this,this.value,'');kiraLuas('$socLuas');"/>
											#elseif($socLuas == '8' || $socLuas == '4')
												<input name="txtLuas2" type="text" class="$disabled" id="txtLuas2" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" />
												<input name="txtLuas3" type="text" class="$disabled" id="txtLuas3" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" />
												<input name="txtLuas4" type="text" class="$disabled" id="txtLuas4" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" onblur="kiraLuas('$socLuas');"/>
											#elseif($socLuas == '7')
												<input name="txtLuas5" type="text" class="$disabled" id="txtLuas5" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" />
												<input name="txtLuas6" type="text" class="$disabled" id="txtLuas6" size="8" maxlength="8" $disability onkeyup="validateNumber(this,this.value);" onblur="kiraLuas('$socLuas');"/>
											#else
												<input value="$!txtLuasLama" name="txtLuas1" type="text" id="txtLuas1" size="20" maxlength="8" $disability $readability onkeyup="validateNumber(this,this.value);" onblur="kiraLuas('$socLuas');"/>
											#end
												<input type=hidden name=XtxtLuas value=$!Luas>
												<input name="txtLuasLama" type="hidden" id="txtLuasLama" value="$!Luas" />
												<input name="txtLuasGabung" type="hidden" id="txtLuasGabung" value="$!txtLuasLama" />
                                        </td>
                            </tr>
                       		<tr>
                                  		<td width="42%" align="right">LUAS PAJAKAN (UNIT LUAS BERSAMAAN)</td>
                                        <td width="1%">:</td>
                                        <td width="57%">
          						        	<select $style name="socluasbersamaan" style="width:200px;" onchange="doChangeLuasBersamaan('$tabmode')" $disability_ $readability >
									  	#set ($listUnitLuas = ["SILA PILIH","KM - KILOMETER PERSEGI","H - HEKTAR","M - METER PERSEGI","E - EKAR,ROOD,POLE","K - KAKI PERSEGI","P - EKAR PERPULUHAN","D - EKAR,DEPA","R - RELONG,JEMBA,KAKI PERSEGI","BN - BATU NAUTIKA"])
									    #set( $counter = 0 )
									    #foreach ($i in $listUnitLuas)
									    #if ($!counter == $!socLuas_)
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
                                  		<td width="42%" align="right">LUAS PAJAKAN (LUAS BERSAMAAN)</td>
                                        <td width="1%">:</td>
                                        <td width="57%">
                                        	<input name="txtLuas" type="text" id="txtLuas" value="$!txtLuas" size="20" $disability $readability onkeyup="this.value=this.value.toUpperCase();" />
                                        </td>
                            </tr>

                                  #end
                            </table>
                        </td>
                    </tr>
            </table>
        </fieldset>
    </td>
  </tr>
  <tr>
  	<td align="center">
  		#if(!$!disability.equals('disabled'))
    	<input type="button" class="stylobutton100" name="cmdPilih" id="cmdPilih" value="Simpan" onClick="simpanTanah('$idHakmilik')">
    	#end
    	<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Senarai Tanah" onClick="kembali()">
    	<input type="button" class="stylobutton100" name="cmdtutup" value="Tutup" onClick="tutup()">
    	<input type="button" class="stylobutton100" name="cmdrefrehs" value="Refresh" onClick="refresh()">
    </td>
  </tr>
</table>

<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="actionPopup"/>
<input type="hidden" name="idhakmilikurusan" value="$!idHakmilikUrusan"/>
<input type="hidden" name="idHakmilik" id="idHakmilik" value="$idHakmilik"/>

<script>
	function kembali() {
		document.${formName}.actionPopup.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}

	function simpanTanah(idHakmilik) {
		document.${formName}.hitButton.value = "simpanHakmilik";
		document.${formName}.actionPopup.value = "papar";
		//document.${formName}.actionPopup.value = "kemaskini";
		document.${formName}.idHakmilik.value = idHakmilik;
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		//window.opener.refreshFromPilihTanah();
		//window.close();

	}

	function doChangeKodLuas(val) {
		document.${formName}.actionPopup.value = "papar";
		document.${formName}.mode.value = "doChangeKodLuas";
		//document.${formName}.idHakmilik.value = idHakmilik;
		doAjaxCall${formName}("paparDetailHakmilik2");

	}

	//2018/02/18 Guna fungsi dalam fail javaScriptUmum.jsp
	function kiraLuas_(idLuas){
		var intConv = 5;
	  	var jenisLuas = idLuas;
	  	//KE HEKTAR
	  	if(jenisLuas == "1"){ // KILOMETER PERSEGI
	  		var luasK = (document.${formName}.txtLuas1.value);
			var luasH = luasK*100;
	  		//document.${formName}.txtLuas.value = luasH.toFixed(4);
	  		document.${formName}.txtLuas.value = luasH.toFixed(intConv);

		}else if(jenisLuas == "2"){ //HEKTAR
	  		var luasH = (document.${formName}.txtLuas1.value);
	  		document.${formName}.txtLuas.value = luasH;

		}else if(jenisLuas == "3"){ // METER PERSEGI
	  		var luasM = document.${formName}.txtLuas1.value;
	  	  	var luasH = (luasM*0.0001);
		  	//document.${formName}.txtLuas.value = luasH.toFixed(4);
		  	document.${formName}.txtLuas.value = luasH.toFixed(intConv);

		}else if(jenisLuas == "4"){ //EKAR, ROOD, POLE
	  	  	var luasE = document.${formName}.txtLuas2.value;
		  	var luasR = document.${formName}.txtLuas3.value;
		  	var luasP = document.${formName}.txtLuas4.value;
		  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
	  	  	//document.${formName}.txtLuas.value = luasH.toFixed(4);
		  	document.${formName}.txtLuas.value = luasH.toFixed(intConv);

	   	}else if(jenisLuas == "5"){ //KAKI PERSEGI
	  	  	var luasAsal = document.${formName}.txtLuas1.value;
		  	var luasK = luasAsal*0.0000092;
	  	  	//document.${formName}.txtLuas.value = luasK.toFixed(4);
		  	document.${formName}.txtLuas.value = luasK.toFixed(intConv);

	   	}else if(jenisLuas == "6"){	//EKAR PERPULUHAN
	  	  	var luasAsal = document.${formName}.txtLuas1.value;
		  	/* AZAM */
		  	var luasK = luasAsal*0.405;
	  	  	//document.${formName}.txtLuas.value = luasK.toFixed(4);
		  	document.${formName}.txtLuas.value = luasK.toFixed(intConv);
		  	/* var luasK = luasAsal*0.0000092;
	  	  	document.${formName}.txtLuas.value = luasK.toFixed(4);
	  	  	var num1 = (parseFloat(a) * 4046.86)/1000;  //Ekar perpuluhan to Hektar
	  	  	*/

	   	}else if(jenisLuas == "7"){ //EKAR,DEPA
	  	  	var luasE = document.${formName}.txtLuas5.value;
		  	var luasD = document.${formName}.txtLuas6.value;
		  	var luasH = (luasE*0.4046864)+(luasD*0.00040469);
	  	  	//document.${formName}.txtLuas.value = luasH.toFixed(4);
		  	document.${formName}.txtLuas.value = luasH.toFixed(intConv);

	   	}else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
	  	  	var luasR = document.${formName}.txtLuas2.value;
		  	var luasJ = document.${formName}.txtLuas3.value;
		  	var luasK = document.${formName}.txtLuas4.value;
		  	var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
	  	  	//document.${formName}.txtLuas.value = luasH.toFixed(4);
		  	document.${formName}.txtLuas.value = luasH.toFixed(intConv);

	   	}
	   	//by Rosli 2010/05/10
	 	if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
			if(document.${formName}.socLuas.value == "4"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
			}else if(document.${formName}.socLuas.value == "7"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
			}else if(document.${formName}.socLuas.value == "8"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
			}

	 	}else{
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
		 }

	}

	function doChangeLuasBersamaan(idBersamaan){
		var id = document.${formName}.socluasbersamaan.value;
		document.${formName}.actionPopup.value = "papar";
		kiraLuas(id);
	}

	// function semua kongsi
	function validateNumber(elmnt,content) {
		//if it is character, then remove it..
		if (isNaN(content)) {
			elmnt.value = RemoveNonNumeric(content);
			return;
		}
	}

	function tutup() {
		window.opener.refreshFromPilihTanah();
		window.close();

	}

	function refresh() {
		window.opener.refreshFromPilihTanah();
	}


</script>
#parse("app/htp/utiliti/javaScriptUmum.jsp")

