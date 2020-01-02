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
	    	
	    </td>
	  </tr>
  <tr>
    <td>
    	<fieldset><legend><strong>MAKLUMAT TANAH</strong></legend>
	##set($idHakmilikUrusan = "0")            
	##set($socLuas_ = 0)            
        ##foreach($beanMaklumatTanah in $BeanMaklumatTanah)
        	#set($idHakmilik = $beanMaklumatTanah.getIdHakmilik())
        	#set($!idHakmilikUrusan = $beanMaklumatTanah.getIdHakmilikUrusan())
            #set($peganganHakmilik = $beanMaklumatTanah.getPeganganHakmilik())
			#set($jenisLot = $beanMaklumatTanah.getRujLot().getKeterangan())
            #set($noLot = $beanMaklumatTanah.getNoLot())
            ##set($luasLot = $beanMaklumatTanah.getLuasBersamaan())
            #set($luasLot = $beanMaklumatTanah.getLuasString())
 			##set($socLuas_ = $!beanMaklumatTanah.getLuasBersamaanPajakan())            
           	#set($txtLuas = $beanMaklumatTanah.getLuasBersamaan())
            #set($jenisHakmilik = $beanMaklumatTanah.getRujJenisHakmilik().getKodJenisHakmilik())
            #set($noHakmilik = $beanMaklumatTanah.getNoHakmlik())
            #set($noWarta = $beanMaklumatTanah.getNoWarta())
            #set($tarikhWarta = $!beanMaklumatTanah.getTarikhWarta())             
            #set($mukim = $beanMaklumatTanah.getMukim().getNamaMukim())
            #set($daerah = $beanMaklumatTanah.getDaerah().getNamaDaerah())
            #set($negeri = $beanMaklumatTanah.getNegeri().getNamaNegeri())            
            #set($kategoriTanah = $beanMaklumatTanah.getKategori())
            #set($subKategoriTanah = $beanMaklumatTanah.getSubKategori())
            #set($syarat = $beanMaklumatTanah.getSyarat())
            #set($sekatan = $!beanMaklumatTanah.getSekatan())
            #set($kementerian = $beanMaklumatTanah.getAgensi().getKementerian().getNamaKementerian())
            #set($agensi = $beanMaklumatTanah.getAgensi().getNamaAgensi()) 
                    
        ##end
            
   <input name="idHakmilik" type="hidden" id="idHakmilik" value="$idHakmilik"/>
           <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    <tr>
                      <td width="50%">
                          <table width="100%"  cellpadding="2" cellspacing="2" border="0">
                              ##foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
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
                                  <td><font color="blue">$!luasLot</font></td>
                            </tr>
                            <tr>
                                  <td align="right">JENIS HAKMILIK :</td>
                                  <td><font color="blue">$jenisHakmilik</font></td>
                            </tr>
                            <tr>
                                  <td align="right">NO. HAKMILIK :</td>
                                  <td><font color="blue">$noHakmilik </font></td>
                            </tr>
                            <tr>
                                  <td align="right">NO. WARTA :</td>
                                  <td><font color="blue">$noWarta</font></td>
                            </tr>
                            <tr>
                                  <td align="right">TARIKH WARTA :</td>
                                  <td><font color="blue">$!tarikhWarta</font></td>
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
                                        <td width="1%">:</td>
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
                                        <td width="1%">:</td>
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
                                        <td width="1%">:</td>
                                        <td width="57%"><font color="blue">$kementerian</font></td>
                                  </tr>
                                  <!--<tr>
                                        <td align="right">&nbsp;</td>
                                  </tr> -->
                                  <tr>
                                        <td width="42%" align="right" valign="top">AGENSI</td>
                                        <td width="1%">:</td>
                                        <td width="57%"><font color="blue">$agensi</font></td>
                                  </tr>
                                  <tr>
                                  		<td width="42%" align="right">LUAS PAJAKAN (UNIT LUAS ASAL)</td>
                                        <td width="1%">:</td>
                                        <td width="57%">
											#parse("app/htp/unit_luas.jsp") 
                                       	</td>
                            </tr>
                            <tr>
                                  		<td width="42%" align="right">LUAS PAJAKAN (LUAS ASAL)</td>
                                        <td width="1%">:</td>
                                        <td width="57%">
											#if($socLuas == '1' || $socLuas == '2' || $socLuas == '3' || $socLuas == '5' || $socLuas == '6' || $socLuas == '9')
												<input value="$!txtLuasLama" name="txtLuas1" type="text" class="$disabled" id="txtLuas1" size="20" maxlength="8" onkeyup="validateNumber(this,this.value);" $readonly onblur="format5Decimal(this,this.value,'');kiraLuas('$socLuas');"/>
											#elseif($socLuas == '8' || $socLuas == '4')
												<input name="txtLuas2" type="text" class="$disabled" id="txtLuas2" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
												<input name="txtLuas3" type="text" class="$disabled" id="txtLuas3" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
												<input name="txtLuas4" type="text" class="$disabled" id="txtLuas4" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onblur="kiraLuas('$socLuas');"/>
											#elseif($socLuas == '7')
												<input name="txtLuas5" type="text" class="$disabled" id="txtLuas5" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" />
												<input name="txtLuas6" type="text" class="$disabled" id="txtLuas6" size="8" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onblur="kiraLuas('$socLuas');"/>
											#else
												<input value="$!txtLuasLama" name="txtLuas1" type="text" class="$disabled" id="txtLuas1" size="20" maxlength="8" $readonly onkeyup="validateNumber(this,this.value);" onblur="kiraLuas('$socLuas');"/>
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
          						        	<select $style name="socluasbersamaan" style="width:200px;" onchange="doChangeLuasBersamaan('$tabmode')">
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
                                        	<input name="txtLuas" type="text" class="disabled" id="txtLuas" onkeyup="this.value=this.value.toUpperCase();" value="$!Utils.formatLuas($!beanMaklumatTanah.getLuasBersamaanPajakan())" size="20" $readonly />
                                        </td>
                            </tr> 
 
                                  ##end
                            </table>                      
                        </td>
                    </tr>
            </table>        
        </fieldset>
    </td>
  </tr>
  <tr>
  	<td align="center">
    	<input type="button" class="stylobutton100" name="cmdPilih" id="cmdPilih" value="Simpan" onClick="simpanTanah('$idHakmilik')">
     	<!-- <input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Senarai Tanah" onClick="kembali()"> -->
    	<input type="button" class="stylobutton100" name="cmdTutup" id="cmdTutup" value="Tutup" onClick="tutup()">
    	<input type="button" class="stylobutton100" name="cmdrefrehs" value="Refresh" onClick="refresh()">
    </td>
  </tr>
</table>

<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="actionPopup"/>
<input type="hidden" name="idhakmilikurusan" value="$!idHakmilikUrusan"/>

<script>
	function kembali() {	
		document.${formName}.mode.value = "senaraitanah";
		//alert(document.${formName}.mode.value);
		//document.${formName}.submit();
		//doAjaxCall${formName}("paparx");
		doAjaxCall${formName}("");

	}

	function simpanTanah(idHakmilik) {
		document.${formName}.hitButton.value = "simpanHakmilik";
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

