#if($page == '1')
<!--frmPembelianSemakan.jsp -->


#set ($tajuk = "")
#set ($idFail = "")
#set ($noFail = "")
#set ($tSurat = "")
#set ($tPermohonan = "")
#set ($noRujukanKJP = "")
#set ($noRujukan = "")
#set ($tAgihan = "")
#set ($idAgensi = "")
#set ($idSuburusan = "")
#foreach ( $semak in $Semak )
	#set ($tajuk = $semak.tajuk)
    #set ($idFail = $semak.idFail)
    #set ($noFail = $semak.noFail)
    #set ($tSurat = $semak.tSurat)
    #set ($noRujukanKJP = $semak.noRujukanKJP)
    #set ($noRujukan = $semak.noRujukan)
    #set ($tAgihan = $semak.tAgihan)
    #set ($tPermohonan = $semak.tPermohonan)
    #set ($idSuburusan = $semakbaru.idSuburusan)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdPermohonan != "")
	#set ($btnName = "value='Batal'")
#end

<br>
<fieldset>
<legend><strong>MAKLUMAT FAIL</strong></legend><table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
                <td width="20%"><div align="left"><strong>Negeri</strong></div></td>
                <td width="2%">:</td>
                <td width="75%">$selectNegeri</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Kementerian</strong></div></td>
                <td>:</td>
                <td>$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Agensi</strong></div></td>
                <td>:</td>
                <td>$selectAgensi</td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Urusan</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Status Tanah</strong></div></td>
                <td>:</td>
                <td>$statusTanah</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Jenis Fail</strong></div></td>
                <td>:</td>
                <td>$jenisFail</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>UP. Pegawai</strong></div></td>
                <td>:</td>
                <td></td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><font color="#FF0000">*</font></div></td>
                <td valign="top"><div align="left"><strong>Tajuk</strong></div></td>
                <td valign="top">:</td>
                <td><label>
                  <textarea name="txtTajuk" id="txtTajuk" cols="41" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc $classDis >$tajuk</textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="45%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="33%"><div align="left"><strong>No. Fail Seksyen</strong></div></td>
                <td width="2%">:</td>
                <td width="62%"><input type="text" name="txtNoFailSek" size="28" value="$noFail" readonly="readonly" class="disabled"></td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>No. Fail KJP</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" onkeyup="this.value=this.value.toUpperCase();" size="28" maxlength="50" value="$noRujukanKJP" $mode $classDis /></td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>No. Fail UPT</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode $classDis /></td>
            </tr>
              <tr>
                <td style="visibility:hidden"><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>No. Fail Lain</strong></div></td>
                <td>:</td>

                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode $classDis /></td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Surat KJP</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$tSurat" onblur="check_date(this)" $classDis >
<<<<<<< .mine
                <img src="../img/calendar.gif" alt="" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
=======
                
                #if($viewmode == 'new' || $viewmode == 'update')
                
                <img src="../img/calendar.gif" alt="" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');">
                
                #end
                
                </td>
>>>>>>> .r28048
              </tr>
            <tr>
              <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Permohonan</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="10" value="$tPermohonan" onblur="check_date(this)" $classDis  />
<<<<<<< .mine
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
=======
                
                #if($viewmode == 'new' || $viewmode == 'update')
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">
                #end
                </td>
>>>>>>> .r28048
              </tr>
              #if ($IdPermohonan == "")
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Buka Fail</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="10" value="$datenow" readonly="readonly" class="disabled" >
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/></td>
              </tr>
            #else
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Buka Fail</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="10" value="$tAgihan" readonly="readonly" $classDis>
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/></td>
              </tr>
            #end
        </table></td>
      </tr>
      <tr>
        <td colspan="2">
        
        <table width="100%" border="0">
          <tr>
            <td colspan="2"></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2">
            
            <fieldset>
            <legend><strong>PERMOHONAN PERAKUAN PEMBELIAN DARI KJP</strong></legend>
            <table width="100%" border="0">
  	#set ( $checked = "" )
    #foreach ( $semak in $senaraiSemakan )
        #set( $i = $velocityCount )
        #if ( ($i % 2) == 0 )
            #set( $row = "row2" )
        #else
            #set( $row = "row1" )
        #end
        <tr>
            <td class="$row" width="5%" align="right">
                #if ( $semakclass.isSemakan("$IdPermohonan", "$semak.id" ))
                    #set ( $checked = "checked" )
                #else
                   #set ( $checked = "" )
                #end
                <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $modeSoc>
            </td>
            <td width="95%" class="$row">
                $semak.keterangan
            </td>
        </tr>
    #end
</table>
</fieldset>
</td>
          </tr>
        </table></td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPS_Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPS_Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fPS_Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fPS_Kembali()">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fPS_Seterusnya()">&nbsp;&nbsp;<input type="button" name="cmdPapar" id="cmdPapar" value="Papar Surat" style="display:none"></td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idFail" value="$idFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
</fieldset>

<!-- **end of frmPembelianSemakan.jsp**-->

<!-- frmPembelianMaklumat.jsp -->
#elseif($page == '2')

<br />
<fieldset>
<legend> <strong>MAKLUMAT PERMOHONAN</strong></legend>
<table width="130%">
<tr>
		<td>
			#parse ("app/htp/frmPembelianInfoAjax.jsp")
		</td>
    </tr>
    <tr>
    	<td>
        	<hr size="2" width="100%" align="center" />
        </td>
    </tr>
    <tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
              <ul class="TabbedPanelsTabGroup"> <!-- ;fPM_TanahView() -->
                <li class="TabbedPanelsTab" title="Tanah" tabindex="0" onclick="javascript:setSelected(0,'Maklumat','tanahview',0)"><strong><font size="1">MAKLUMAT TANAH</font></strong></li>
                
               
                <!-- fPM_TBangunView()  -->
                <li class="TabbedPanelsTab" title="Tanah & Bangunan" tabindex="0" onclick="javascript:setSelected(1,'Maklumat','tbangunview',0)"><strong><font size="1">MAKLUMAH TANAH &amp; BANGUNAN</font></strong></li>
              
                
                 <!-- fPM_PemilikView()  -->
                <li class="TabbedPanelsTab" title="Pemilik" tabindex="0" onclick="javascript:setSelected(2,'Maklumat','pemilik',0)"><strong><font size="1">NAMA PEMILIK</font></strong></li>

                <!-- fPM_PeguamView()  -->
                <li class="TabbedPanelsTab" title="Peguam" tabindex="0" onclick="javascript:setSelected(3,'Maklumat','peguamview',0)"><strong><font size="1">PEGUAM YANG DILANTIK</font></strong></li>
                
                 <!-- tab penjual  -->
                <li class="TabbedPanelsTab" title="Peguam" tabindex="0" onclick="javascript:setSelected(4,'Maklumat','penjualview',0)"><strong><font size="1">NAMA PENJUAL</font></strong><br />
                </li>
                
                <!-- tab perakuan kptg  -->
                <li class="TabbedPanelsTab" title="Perakuan JKPTG" tabindex="0" onclick="javascript:setSelected(5,'Maklumat','perakuankptgview',0)"><strong><font size="1">PERAKUAN JKPTG</font></strong><br />
                </li>
                
              </ul>
              
              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent"> <!-- content maklumat asas tanah -->
	<!--                <div id = "satu"> </div> -->
    			#if($selectedTab == '0')
                	#if($tabmode == '0')
	                	#parse ("app/htp/test1.jsp")
                    
    	            #else
        	        	#parse ("app/htp/frmPembelianTanahAjax.jsp") 
            	    #end
                #end

                </div> <!-- close content 1 -->

                
                <div class="TabbedPanelsContent"> <!-- maklumat tanah & bangunan -->
               <!--	<div id = "dua"></div> -->
          		#if($selectedTab == '1')
                	#if($tabmode == '0')
	                	#parse ("app/htp/test2.jsp")
                    
                    #else
                    	#parse("app/htp/frmPembelianTBangunAjax.jsp");
                	#end
                #end

                </div> <!-- close content maklumat tanah & bangunan -->


                <div class="TabbedPanelsContent"> <!-- maklumat tanah atau tanah & bangunan -->
               	<!-- <div id = "tiga"></div> -->
       			#if($selectedTab == '2')
                	#parse ("app/htp/test3.jsp")
                #end

                </div> <!-- close maklumat tanah atau tanah & bangunan -->


                <div class="TabbedPanelsContent"> <!-- maklumat peguam -->
                <!-- <div id = "empat"></div> -->
       			#if($selectedTab == '3')
                	#parse ("app/htp/test4.jsp")
                #end

                </div> <!-- close maklumat peguam -->
                
                <!-- maklumat penjual -->
                 <div class="TabbedPanelsContent"> 

       			#if($selectedTab == '4')
                	#parse ("app/htp/test5.jsp")
                #end

                </div> 
                <!-- close maklumat penjual -->
                
                   <!-- maklumat penjual -->
                <div class="TabbedPanelsContent"> 

       			#if($selectedTab == '5')
                	#parse ("app/htp/frmPembelianTabPerakuanJKPTG.jsp")
                #end

                </div> 
                <!-- close maklumat penjual -->
                
              </div>
            </div>
         </td>
      </tr>
</table>
</fieldset>

<script language="javascript" type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>

<!-- **end of frmPembelianMaklumat.jsp**-->

<!-- start draf perjanjian jual beli-->

#elseif($page == '3')

<br/>
<fieldset>
<legend> <strong>DRAF PERJANJIAN JUAL BELI</strong></legend>
<table width="130%">
<tr>
		<td>
			#parse ("app/htp/frmPembelianInfoAjax.jsp")
		</td>
    </tr>
    <tr>
    	<td>
        	<hr size="2" width="100%" align="center" />
        </td>
    </tr>
    <tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
            
              <ul class="TabbedPanelsTabGroup"> 
                <li class="TabbedPanelsTab" title="Kelulusan" tabindex="0" onclick="javascript:setSelected(0,'Draf','mode',0)"><strong><font size="1">KELULUSAN</font></strong></li>
                
               
<<<<<<< .mine
                <li class="TabbedPanelsTab" title="Draf Pembelian" tabindex="0" onclick="javascript:setSelected(1,'Draf','mode',0)"><strong><font size="1">Draf Pembelian</font></strong></li>
=======
                <li class="TabbedPanelsTab" title="Draf Pembelian" tabindex="0" onclick="javascript:setSelected(1,'Draf','mode',0)"><strong><font size="1">DRAF PERJANJIAN</font></strong></li>
>>>>>>> .r27959
                
              </ul>
              
              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent"> 
				<!-- Kelulusan -->	
    			#if($selectedTab == '0')
        	        	#parse ("app/htp/frmPembelianSemakanKementerian.jsp") 
                #end

                </div> <!-- close content kelulusan -->

                
                <div class="TabbedPanelsContent">
				 <!-- Draf Pembelian -->
                 <!-- just nk bg link, frmPembelianDrafPenjanjianTambahAjax kat bwh -->
          		#if($selectedTab == '1')
                	#if($tabmode == '0')
                    	#parse("app/htp/frmPembelianDrafPerjanjianTambahAjax.jsp")
                        
                    #else
                    	#parse("app/htp/frmPembelianDrafPenjanjianTambah.jsp");
               		#end
               #end

                </div> <!-- close content draf pembelian -->
                
              </div>
            </div>
         </td>
      </tr>
</table>
</fieldset>

<script language="javascript" type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>

<!-- end draf perjanjian jual beli-->

<!-- start perjanjian jual beli-->

#elseif($page == '4')

<br/>
<fieldset>
<legend><strong>PERJANJIAN JUAL BELI</strong> </legend><table width="130%">
<tr>
		<td>
			#parse ("app/htp/frmPembelianInfoAjax.jsp")
		</td>
    </tr>
    <tr>
    	<td>
        	<hr size="2" width="100%" align="center" />
        </td>
    </tr>
    <tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
            
              <ul class="TabbedPanelsTabGroup"> 
<<<<<<< .mine
                <li class="TabbedPanelsTab" title="Senarai Semak" tabindex="0" onclick="javascript:setSelected(0,'Perjanjian','mode',0)"><strong><font size="1">Senarai Semak</font></strong></li>
=======
              
                   <li class="TabbedPanelsTab" title="Draf Pembelian" tabindex="0" onclick="javascript:setSelected(0,'Perjanjian','drafview',0)"><strong><font size="1">DRAF PERJANJIAN</font></strong></li>
                   
                    <li class="TabbedPanelsTab" title="Senarai Semak" tabindex="0" onclick="javascript:setSelected(1,'Perjanjian','senaraisemakview',0)"><strong><font size="1">SENARAI SEMAK</font></strong></li>
              
               <li class="TabbedPanelsTab" title="Perjanjian Pembelian" tabindex="0" onclick="javascript:setSelected(2,'Perjanjian','pembelianview',0)"><strong><font size="1">PERJANJIAN PEMBELIAN</font></strong></li>
<<<<<<< .mine
>>>>>>> .r27959
=======
               
               <li class="TabbedPanelsTab" title="Perjanjian Tambahan" tabindex="0" onclick="javascript:setSelected(3,'Perjanjian','ptambahanview',0)"><strong><font size="1">PERJANJIAN TAMBAHAN</font></strong></li>
>>>>>>> .r28308
                
               
                <li class="TabbedPanelsTab" title="Perjanjian Pembelian" tabindex="0" onclick="javascript:setSelected(1,'Perjanjian','mode',0)"><strong><font size="1">Perjanjian Pembelian</font></strong></li>
                
              </ul>
              
              <div class="TabbedPanelsContentGroup">
<<<<<<< .mine
=======
                
                 <div class="TabbedPanelsContent">
				 <!-- Draf Pembelian -->
                 <!-- just nk bg link, frmPembelianDrafPenjanjianTambahAjax kat bwh
                 	
                  -->
          		#if($selectedTab == '0')
                	#if($tabmode == '0')
                    	#parse("app/htp/frmPembelianDrafPerjanjianTambahAjax.jsp")
                        #####parse("app/htp/frmPajakanTabDrafPerjanjianSenarai.jsp")


                    #else
                    	#parse("app/htp/frmPembelianDrafPenjanjianTambah.jsp");
               		#end
               #end

                </div> <!-- close content draf pembelian -->
                
                
>>>>>>> .r28308
                <div class="TabbedPanelsContent"> 
				<!-- Senarai Semak -->	
    			#if($selectedTab == '0')
        	        	#parse ("app/htp/frmPembelianJualBeliSenaraiSemakAjax.jsp") 
                #end

                </div> 
                <!-- close content senarai semak -->

                
                <div class="TabbedPanelsContent">
				 <!-- Perjanjian Pembelian -->
          		#if($selectedTab == '1')
                    	#parse("app/htp/frmPembelianJualBeliPerjanjianAjax.jsp")
                        ####parse("app/htp/frmRekodPendaftaranHakmilik.jsp")
               #end

                </div> <!-- close content perjanjian pembelian -->
                
                 <div class="TabbedPanelsContent">
				 <!-- Perjanjian tambahan -->
          		#if($selectedTab == '3')
                    	#parse("app/htp/frmPembelianPerjanjianTambahan.jsp")

               	#end

                </div> <!-- close content perjanjian pembelian -->
                
              </div>
            </div>
         </td>
      </tr>
</table>
</fieldset>

<script language="javascript" type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>

<!-- end perjanjian jual beli-->


<!-- frmPembelianSemakan1.jsp-->
<!-- digunakan bila klik dr senarai fail-->
#elseif($page == '10')

#set ($tajuk = "")
#set ($idFail = "")
#set ($noFail = "")
#set ($tSurat = "")
#set ($tPermohonan = "")
#set ($noRujukanKJP = "")
#set ($noRujukan = "")
#set ($tAgihan = "")
#set ($idAgensi = "")
#set ($idSuburusan = "")
#foreach ( $semak in $Semak )
	#set ($tajuk = $semak.tajuk)
    #set ($idFail = $semak.idFail)
    #set ($noFail = $semak.noFail)
    #set ($tSurat = $semak.tSurat)
    #set ($noRujukanKJP = $semak.noRujukanKJP)
    #set ($noRujukan = $semak.noRujukan)
    #set ($tAgihan = $semak.tAgihan)
    #set ($tPermohonan = $semak.tPermohonan)
    #set ($idSuburusan = $semakbaru.idSuburusan)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdPermohonan != "")
	#set ($btnName = "value='Batal'")
#end

<fieldset>
<legend>Maklumat Semakan</legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
                <td width="20%"><div align="left"><strong>Negeri</strong></div></td>
                <td width="2%">:</td>
                <td width="75%">$selectNegeri</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Kementerian</strong></div></td>
                <td>:</td>
                <td>$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Agensi</strong></div></td>
                <td>:</td>
                <td>$selectAgensi</td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Urusan</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan
                <input type="hidden" name="idSuburusan" value="$idSuburusan"></td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Status Tanah</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Jenis Fail</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>UP. Pegawai</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><font color="#FF0000">*</font></div></td>
                <td valign="top"><div align="left"><strong>Tajuk</strong></div></td>
                <td valign="top">:</td>
                <td><label>
                  <textarea name="txtTajuk" id="txtTajuk" cols="41" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc $classDis >$tajuk</textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="45%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="33%"><div align="left"><strong>No. Fail Seksyen</strong></div></td>
                <td width="2%">:</td>
                <td width="62%"><input type="text" name="txtNoFailSek" size="28" value="$noFail" readonly="readonly" class="disabled" ></td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>No. Fail KJP</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" onkeyup="this.value=this.value.toUpperCase();" size="28" maxlength="50" value="$noRujukanKJP" $mode $classDis /></td>
            </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>No. Fail UPT</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode $classDis /></td>
            </tr>
              <tr>
                <td>&nbsp;</td>
                <td><div align="left"><strong>No. Fail Lain</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode $classDis /></td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Surat KJP</strong></div></td>
                <td>:</td>
<<<<<<< .mine
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$tSurat" readonly="readonly">
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
=======
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$tSurat" readonly="readonly" onblur="check_date(this)" $classDis >
                
                #if($viewmode == 'new' || $viewmode == 'update')
                
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2">
                
				#end
                
                </td>
>>>>>>> .r28048
              </tr>
            <tr>
              <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Permohonan</strong></div></td>
                <td>:</td>
<<<<<<< .mine
                <td><input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="10" value="$tPermohonan" readonly="readonly" />
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
=======
                <td><input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="10" value="$tPermohonan" readonly="readonly" onblur="check_date(this)" $classDis />
                
                #if($viewmode == 'new' || $viewmode == 'update')
                
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2">
                #end
                
                </td>
>>>>>>> .r28048
              </tr>
              #if ($IdPermohonan == "")
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Buka Fail</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="10" value="$datenow" readonly="readonly" $classDis >
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/></td>
              </tr>
            #else
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Buka Fail</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="10" value="$tAgihan" readonly="readonly" $classDis >
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/></td>
              </tr>
            #end
        </table></td>
      </tr>
      <tr>
        <td colspan="2"><table width="100%" border="0">
          <tr>
            <td colspan="2"><hr size="2" width="100%"></td>
          </tr>
          <tr>
            <td colspan="2"><strong>Senarai Semakan Pembelian</strong></td>
          </tr>
          <tr>
            <td colspan="2"><table width="100%" border="0">
  	#set ( $checked = "" )
    #foreach ( $semak in $senaraiSemakan )
        #set( $i = $velocityCount )
        #if ( ($i % 2) == 0 )
            #set( $row = "row2" )
        #else
            #set( $row = "row1" )
        #end
        <tr>
            <td class="$row" width="5%" align="right">
                #if ( $semakclass.isSemakan("$IdPermohonan", "$semak.id" ))
                    #set ( $checked = "checked" )
                #else
                   #set ( $checked = "" )
                #end
                <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $modeSoc $classDis >
            </td>
            <td width="95%" class="$row">
                $semak.keterangan
            </td>
        </tr>
    #end
</table>
</td>
          </tr>
        </table></td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPS1_Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPS1_Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fPS1_Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fPS1_Kembali()">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fPS1_Seterusnya()">&nbsp;&nbsp;<input type="button" name="cmdPapar" id="cmdPapar" value="Papar Surat" style="display:none"></td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idFail" value="$idFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
</fieldset>

<!-- **end of frmPembelianSemakan1.jsp**-->

<!-- frmSenaraiFailPembelian.jsp -->

#else
<br/>
<fieldset> <legend><strong>CARIAN</strong></legend><table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td height="25" align="right"><strong>No. Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NoFail" type="text" size="48" maxlength="400" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();" /></td>
      </tr>
      <tr>
        <td height="25" align="right"><strong>Nama Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NamaFail" type="text" size="48" maxlength="300" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td height="25"  align="right"><strong>Negeri : &nbsp;&nbsp;</strong></td>
        <td>$selectNegeri</td>
      </tr>
      <tr style="display:none">
        <td  align="right"><strong>Status : &nbsp;&nbsp;</strong></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td></td>
        <td><input name="cari" value="Cari" type="button" onclick="javascript:fSFP_search_data()">
        <input value="Kosongkan" onclick="javascript:fSFP_cancel()" type="button"></td>
      </tr>
    </tbody>
  </table>
</fieldset>
<br/>
<fieldset>
<legend><strong>SENARAI FAIL</strong></legend>
	<input name="add" value="Tambah" type="button" onClick="javascript:fSFP_Tambah()">
  <table border="0" cellpadding="2" cellspacing="1" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="5%"><b>No</b></td>
        <td width="23%"><b>No Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="32%"><b>Nama Fail</b></td>
        <td width="23%"><b>Status</b></td>
      </tr>
      #set ($count = 0)
      #foreach ( $fail in $Senaraifail )
      	#set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row" scope="row">$fail.bil</td>
        <td class="$row"><a href="javascript:fSFP_seterusnya('$fail.idPermohonan', '$fail.noFail')" class="style1">$fail.noFail</a></td>
        <td class="$row">$fail.negeri</td>
        <td class="$row">$fail.tajuk</td>
        <td class="$row">$fail.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr>
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
    </tbody>
  </table>
  <!--
  <input type="hidden" name="idPermohonan">
  <input type="hidden" name="idFail" value="$fail.idFail">
  <input type="hidden" name="noFail">
  -->

</fieldset>

<!-- **end of frmSenaraiFailPembelian.jsp** -->
#end

<!-- start script -->
<script language="javascript" type="text/javascript">


<!-- script frmPembelianSemakan.jsp -->


function fPS_Kembali() {
	doAjaxCall${formName}('');
}

function fPS_Seterusnya() {
	doAjaxCall${formName}('Maklumat','mode=tanahview');
}

function fPS_Kemaskini() {
	doAjaxCall${formName}('FailBaru','mode=kemaskini');
}

function fPS_Batal() {

	if(document.${formName}.idPermohonan.value == ""){
		doAjaxCall${formName}('FailBaru','mode=baru');
	}
	else{
//		doAjaxCall${formName}('FailBaru');
		doAjaxCall${formName}('FailBaru','mode=view');
	}

}

function fPS_Simpan() {

	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih " Kementerian " terlebih dahulu.');
  		document.${formName}.socKementerian.focus();
		return;
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih " Agensi " terlebih dahulu.');
  		document.${formName}.socAgensi.focus();
		return;
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih " Urusan " terlebih dahulu.');
  		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila masukkan " No. Fail KJP " terlebih dahulu.');
  		document.${formName}.txtNoFailKJP.focus();
		return;
	}
	if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila masukkan " Tarikh Surat KJP " terlebih dahulu.');
  		document.${formName}.txdTarikhSurKJP.focus();
		return;
	}
	
	/*
	if(document.${formName}.txtNoFailLain.value == ""){
		alert('Sila masukkan " No. Fail Lain " terlebih dahulu.');
  		document.${formName}.txtNoFailLain.focus();
		return;
	}
	*/
	
	if(document.${formName}.txdTarikhPermohonan.value == ""){
		alert('Sila masukkan " Tarikh Permohonan " terlebih dahulu.');
  		document.${formName}.txdTarikhPermohonan.focus();
		return;
	}


	if ( !window.confirm("Anda Pasti?") ) return;
	doAjaxCall${formName}('FailBaru','mode=simpan');
}

function doChangeKementerian(){
	doAjaxCall${formName}('FailBaru','mode=doChangeKementerian');
}

<!-- end of script frmPembelianSemakan.jsp -->



<!-- script frmPembelianSemakan1.jsp-->


function fPS1_Kembali() {
	doAjaxCall${formName}('');
}

function fPS1_Seterusnya() {
	doAjaxCall${formName}('Maklumat','mode=tanahview');
}

function fPS1_Kemaskini() {
	doAjaxCall${formName}('Semakan','mode=kemaskini');
}

function fPS1_Batal() {
	doAjaxCall${formName}('Semakan','mode=view');
}

function fPS1_Simpan() {
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih " Kementerian " terlebih dahulu.');
  		document.${formName}.socKementerian.focus();
		return;
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih " Agensi " terlebih dahulu.');
  		document.${formName}.socAgensi.focus();
		return;
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih " Urusan " terlebih dahulu.');
  		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila masukkan " No. Fail KJP " terlebih dahulu.');
  		document.${formName}.txtNoFailKJP.focus();
		return;
	}
	if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila masukkan " Tarikh Surat KJP " terlebih dahulu.');
  		document.${formName}.txdTarikhSurKJP.focus();
		return;
	}
	if(document.${formName}.txtNoFailLain.value == ""){
		alert('Sila masukkan " No. Fail Lain " terlebih dahulu.');
  		document.${formName}.txtNoFailLain.focus();
		return;
	}
	if(document.${formName}.txdTarikhPermohonan.value == ""){
		alert('Sila masukkan " Tarikh Permohonan " terlebih dahulu.');
  		document.${formName}.txdTarikhPermohonan.focus();
		return;
	}

	if ( !window.confirm("Anda Pasti?") ) return;

	doAjaxCall${formName}('Semakan','mode=simpan');
}

function alertMsg(msg){
	alert(msg);
	return;
}

<!-- end of frmPembelianSemakan1.jsp-->



<!-- script frmPembelianMaklumat.jsp -->

//var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

function validatePoskod(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function doChangeDaerah(){
		doAjaxCall${formName}('Maklumat','mode=tanahview&dochange=daerah');
}

/*pemilik javascript controller*/
function fPM_KemaskiniPemilik() {
	doAjaxCall${formName}('Maklumat','mode=kemaskinipemilik');
}

function fPM_BatalPemilik() {
	doAjaxCall${formName}('Maklumat','mode=pemilikview');
}

function fPM_SimpanPemilik() {
	
	/*
	if(document.${formName}.txtNamaPemaju.value == ""){
		alert('Sila masukkan " Nama Pemaju / Pemilik " terlebih dahulu.');
  		document.${formName}.txtNamaPemaju.focus();
		return;
	}
	if(document.${formName}.socJenisNoPB.value == ""){
		alert('Sila pilih " Jenis Nopb " terlebih dahulu.');
  		document.${formName}.socJenisNoPB.focus();
		return;
	}
	if(document.${formName}.txtNoRuj.value == ""){
		alert('Sila masukkan " No. Rujukan Pemaju " terlebih dahulu.');
  		document.${formName}.txtNoRuj.focus();
		return;
	}
	if(document.${formName}.txtAlamat1.value == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		document.${formName}.txtAlamat1.focus();
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
  		document.${formName}.txtPoskod.focus();
		return;
	}
	if(document.${formName}.socADaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socADaerah.focus();
		return;
	}
	if(document.${formName}.socANegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socANegeri.focus();
		return;
	}
	*/
	if ( !window.confirm("Anda Pasti?") ) return;

	doAjaxCall${formName}('Maklumat','mode=simpanpemilik');
}

function fPM_KembaliPemilik() {
	doAjaxCall${formName}('SenaraiPermohonan');
}

function fPM_PemilikView() {
	doAjaxCall${formName}('Maklumat','mode=pemilik');
}

function fPM_detailsPemilik(idPermohonan, idHakmilikurusan, command, mode){
	doAjaxCall${formName}(command,'mode='+mode+'&idPermohonanP='+idPermohonan+'&idHakmilikurusanP='+idHakmilikurusan);
}

/*pemilik javascript controller end*/

/*peguam javascript controller*/
function fPM_KemaskiniPeguam() {
	doAjaxCall${formName}('Maklumat','mode=peguamkemaskini&tabId=3');
}

function fPM_BatalPeguam() {
	doAjaxCall${formName}('Maklumat','mode=peguamview');
}

function fPM_SimpanPeguam() {
	if(document.${formName}.txtNamaPeguam.value == ""){
		alert('Sila masukkan " Nama Peguam " terlebih dahulu.');
  		document.${formName}.txtNamaPeguam.focus();
		return;
	}
	/*
	if(document.${formName}.txtKodPeguam.value == ""){
		alert('Sila masukkan " Kod Peguam " terlebih dahulu.');
  		document.${formName}.txtKodPeguam.focus();
		return;
	}
	if(document.${formName}.txtAlamat1.value == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		document.${formName}.txtAlamat1.focus();
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
  		document.${formName}.txtPoskod2.focus();
		return;
	}
	if(document.${formName}.socBDaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socBDaerah.focus();
		return;
	}
	if(document.${formName}.socBNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socBNegeri.focus();
		return;
	}
	*/
	if ( !window.confirm("Anda Pasti?") ) return;

	doAjaxCall${formName}('Maklumat','mode=peguamsimpan');
}

function fPM_KembaliPeguam() {
	doAjaxCall${formName}('');
}

function fPM_PeguamView() {
	doAjaxCall${formName}('Maklumat','mode=peguamview');
}

function fPM_seterusnya(){
	doAjaxCall${formName}('Draf','mode=baru&tabSelection=Kelulusan');
}

/*peguam javascript controller end*/

/*tanah javascript controller*/
function fPM_KemaskiniTanah() {
	doAjaxCall${formName}('Maklumat','mode=tanakkemaskini');
}

function fPM_BatalTanah() {
	doAjaxCall${formName}('Maklumat','mode=tanahview');
}

function fPM_SimpanTanah() {
	if(document.${formName}.txdTarikhHantar.value == ""){
		alert('Sila pilih " Tarikh Hantar " terlebih dahulu.');
  		document.${formName}.txdTarikhHantar.focus();
		return;
	}
	if ( !window.confirm("Anda Pasti?") ) return;

	doAjaxCall${formName}('Maklumat','mode=tanahsimpan');
}

function fPM_KembaliTanah() {
	doAjaxCall${formName}('SenaraiPermohonan');
}

function fPM_TanahView() {
	doAjaxCall${formName}('Maklumat','mode=tanahview');
}

function fPM_search_Tanahdata(){
	doAjaxCall${formName}('Maklumat','mode=tanahview');
}

function fPM_Tanahcancel(){
	document.${formName}.reset;
	document.${formName}.socDaerah.value = "";
	document.${formName}.socMukim.value = "";
	document.${formName}.socDaerah.focus();
}

function fPM_TanahTambah(command,mode,tabId,tabmode) {
	//var url = "../x/${securityToken}/ekptg.view.htp.PembelianPopup?idPermohonan="+idPermohonan+"&command="+command+"&mode="+mode;
	//var hWnd = window.open(url,'printuser','width=1024,height=600, resizable=yes,scrollbars=yes');
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	
}

function fPM_doChangeNegeri(){
	doAjaxCall${formName}('Tanah','mode=dochangenegeri&tabId=0&tabmode=1');
}

function fPM_doChangeDaerah(){
	doAjaxCall${formName}('Tanah','mode=dochangedaerah&tabId=0&tabmode=1');
}


function fPM_TanahDetails(idPermohonan,idHakmilikurusan,command,mode) {
	//var url = "../x/${securityToken}/ekptg.view.htp.PembelianPopup?idPermohonan="+idPermohonan+"&idHakmilikurusan="+idHakmilikurusan+"&command="+command+"&mode="+mode;
	//var hWnd = window.open(url,'printuser','width=1024,height=600, resizable=yes,scrollbars=yes');
	doAjaxCall${formName}(command,'mode='+mode+'&idPermohonan='+idPermohonan+'&idHakmilikurusan='+idHakmilikurusan+'&tabmode=1');
	
}
/*tanah javascript controller end*/

/* script frmPembelianTanahAjax.jsp */

function fPTA_Kembali() {
	//window.close();
}
function fPTA_seterusnya() {
	/*
	document.${formName}.action = "";
	document.${formName}.mode.value = "hakmilikview";
	document.${formName}.command.value = "Tanah";
	document.${formName}.submit();
	*/
	doAjaxCall${formName}('Tanah','mode=hakmilikview');
}
function fPTA_Kemaskini() {
	/*
	document.${formName}.action = "";
	document.${formName}.mode.value = "kemaskini";
	document.${formName}.command.value = "Tanah";
	document.${formName}.submit();
	*/
	doAjaxCall${formName}('Tanah','mode=kemaskini');
}
function fPTA_Batal() {
//	document.${formName}.action = "";
var mode = '';
	if(document.${formName}.idHakmilikurusan.value == ""){
		//document.${formName}.mode.value = "baru";
		mode = 'baru';
	}
	else{
		//document.${formName}.mode.value = "view";
		mode = 'tanahview';
	}
	/*
	document.${formName}.command.value = "Tanah";
	document.${formName}.submit();
	*/
	
//	doAjaxCall${formName}('Tanah','mode='+mode);
	doAjaxCall${formName}('Maklumat','mode='+mode);
}
function fPTA_Simpan() {
	//alert('testxx');
	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socDaerahC.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socDaerahC.focus(); 
		return; 
	}
	if(document.${formName}.socMukimC.value == ""){
		alert('Sila pilih " Bandar/Pekan/Mukim " terlebih dahulu.');
  		document.${formName}.socMukimC.focus(); 
		return; 
	}
	if(document.${formName}.socJenisHakmilik.value == ""){
		alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.');
  		document.${formName}.socJenisHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtNoHakmilik.value == ""){
		alert('Sila masukkan " No. Hakmilik " terlebih dahulu.');
  		document.${formName}.txtNoHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtKodLot.value == ""){
		alert('Sila masukkan " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.txtKodLot.focus(); 
		return; 
	}
	if(document.${formName}.socLot.value == ""){
		alert('Sila pilih " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.socLot.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhTerima.value == ""){
		alert('Sila masukkan " Tarikh Mula " terlebih dahulu.');
  		document.${formName}.txdTarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.txtLuas.value == ""){
		alert('Sila masukkan " Luas " terlebih dahulu.');
  		document.${formName}.txtLuas.focus(); 
		return; 
	}
	if(document.${formName}.socLuas.value == ""){
		alert('Sila pilih " Unit Luas " terlebih dahulu.');
  		document.${formName}.socLuas.focus(); 
		return; 
	}
	if(document.${formName}.txtNoPelan.value == ""){
		alert('Sila masukkan " No. Pelan Akui " terlebih dahulu.');
  		document.${formName}.txtNoPelan.focus(); 
		return; 
	}	
	if(document.${formName}.txdTarikhLuput.value == ""){
		alert('Sila masukkan " Tarikh Luput " terlebih dahulu.');
  		document.${formName}.txdTarikhLuput.focus(); 
		return; 
	}

	if(document.${formName}.socRizab.value == ""){
		alert('Sila pilih " Rizab " terlebih dahulu.');
  		document.${formName}.socRizab.focus(); 
		return; 
	}
	if(document.${formName}.socKategori.value == ""){
		alert('Sila pilih " Kategori " terlebih dahulu.');
  		document.${formName}.socKategori.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;

/*
	document.${formName}.action = "";
	document.${formName}.mode.value = "simpan";
	document.${formName}.command.value = "Tanah";
	document.${formName}.submit();
	//window.close();
	*/
	
	doAjaxCall${formName}('Tanah','mode=simpan');
	
}

/*
document.forms[0].cmdKemaskini.style.display = document.${formName}.style1.value;
document.forms[0].cmdSimpan.style.display = document.${formName}.style2.value;
document.forms[0].cmdBatal.style.display = document.${formName}.style2.value;
document.forms[0].cmdKembali.style.display = document.${formName}.style1.value;
document.forms[0].cmdSeterusnya.style.display = document.${formName}.style1.value;
*/

/* end of script*/

/* start script frmPembelianTBangunAjax.jsp  */

function Kembali() {
	window.close();
}
function fPTBA_seterusnya() {
	/*
	document.${formName}.action = "";
	document.${formName}.mode.value = "hakmilikview";
	document.${formName}.command.value = "TBangun";
	document.${formName}.submit();
	*/
	doAjaxCall${formName}('TBangun','mode=hakmilikview');
}
function fPTBA_Kemaskini() {
	/*
	document.${formName}.action = "";
	document.${formName}.mode.value = "kemaskini";
	document.${formName}.command.value = "TBangun";
	document.${formName}.submit();
	*/
	doAjaxCall${formName}('TBangun','mode=kemaskini');	
}
function fPTBA_Batal() {
	var mode;
	if(document.${formName}.idHakmilikurusan.value == ""){
		mode = "baru";
	}
	else{
		mode = "view";
	}
	/*
	document.${formName}.command.value = "TBangun";
	document.${formName}.submit();
	*/
	doAjaxCall${formName}('TBangun','mode='+mode);	
}
function fPTBA_Simpan() {
	if(document.${formName}.SocNoHakmilik.value == ""){
		alert('Sila pilih " No. Hakmilik " terlebih dahulu.');
  		document.${formName}.SocNoHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtNoPetak.value == ""){
		alert('Sila masukkan " No. Petak " terlebih dahulu.');
  		document.${formName}.txtNoPetak.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;

	/*
	document.${formName}.action = "";
	document.${formName}.mode.value = "simpan";
	document.${formName}.command.value = "TBangun";
	document.${formName}.submit();
	//window.close();
	*/
	doAjaxCall${formName}('TBangun','mode=simpan');
}

/*
document.forms[0].cmdKemaskini.style.display = document.${formName}.style1.value;
document.forms[0].cmdSimpan.style.display = document.${formName}.style2.value;
document.forms[0].cmdBatal.style.display = document.${formName}.style2.value;
document.forms[0].cmdKembali.style.display = document.${formName}.style1.value;
document.forms[0].cmdSeterusnya.style.display = document.${formName}.style1.value;
*/


/* end of script  */

/*tanah & bangunan javascript controller*/
function fPM_TBangunView(){
	doAjaxCall${formName}('Maklumat','mode=tbangunview');
}

function fPM_search_TBangundata(){
	doAjaxCall${formName}('Maklumat','mode=tbangunview');
}

function fPM_TBanguncancel(){
	document.${formName}.reset;
	document.${formName}.socDaerahB.value = "";
	document.${formName}.socMukimB.value = "";
	document.${formName}.socDaerah.focus();
}

function fPM_TBangunTambah(command,mode,tabId,tabmode) {
	/*
	var url = "../x/${securityToken}/ekptg.view.htp.PembelianPopup?idPermohonan="+idPermohonan+"&command="+command+"&mode="+mode;
	var hWnd = window.open(url,'printuser','width=700,height=250, resizable=yes,scrollbars=yes');
	*/
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}

function fPM_TBangunDetails(idPermohonan,idHakmilikurusan,command,mode) {
	var url = "../x/${securityToken}/ekptg.view.htp.PembelianPopup?idPermohonan="+idPermohonan+"&idHakmilikurusan="+idHakmilikurusan+"&command="+command+"&mode="+mode;
	var hWnd = window.open(url,'printuser','width=700,height=250, resizable=yes,scrollbars=yes');
}

/*tanah & bangunan javascript controller end*/

function setSelected(tabId,command,mode,tabmode) {
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}

/*
function setSelected(tabId,mode,tabmode) {
	doAjaxCall${formName}('Maklumat','mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}
*/

<!-- end of script frmPembelianMaklumat.jsp -->


<!-- script frmSenaraiFailPembelian.jsp -->

function fSFP_cancel() {

}

function fSFP_Tambah() {
	doAjaxCall${formName}('FailBaru');
}

function fSFP_search_data(){
	doAjaxCall${formName}('');
}

function fSFP_seterusnya(id, no) {
	doAjaxCall${formName}('Semakan','mode=view&idPermohonan='+id+'&noFail='+no);
}

<!-- end of script frmSenaraiFailPembelian.jsp -->

<!-- script frmPembelianDrafPerjanjianTambahAjax.jsp -->

function fPDPTA_Kemaskini(idDraf){
	doAjaxCall${formName}('Perjanjian','mode=drafview&dochange=update&idDraf='+idDraf);
}

function fPDPTA_Simpan(){
}

function fPDPTA_SimpanUpdate(idDraf){
	doAjaxCall${formName}('Perjanjian','mode=drafupdate&idDraf='+idDraf);
}

function fPDPTA_Batal(){
}

function fPDPTA_Kembali(){
}

function daftarBaruDraf(){
	doAjaxCall${formName}('Perjanjian','mode=drafview&dochange=newDraf');
}

function paparDraf(idDraf){
	doAjaxCall${formName}('Perjanjian','mode=drafview&dochange=view&idDraf='+idDraf);
}



function fPDPTA_Seterusnya(){
	doAjaxCall${formName}('Perjanjian');
}


<!-- end of script-->


  function fPSK_Simpan(){
      doAjaxCall${formName}();
  }
  
  function fPSK_Kemaskini(){
          doAjaxCall${formName}();
  }
  
  function fPSK_Seterusnya(){
          doAjaxCall${formName}();
  }
  
  function fPSK_Batal(){
      doAjaxCall${formName}();
  }
  
  function fPSK_Kembali(){
      doAjaxCall${formName}();
  }


<<<<<<< .mine
=======
function fPJBPA_Kemaskini(){
	doAjaxCall${formName}("Perjanjian","mode=pembeliankemaskini");
}
>>>>>>> .r28418

<<<<<<< .mine

=======
function fPJBPA_SimpanUpdatePerjanjian(){
	doAjaxCall${formName}("Perjanjian","mode=pembeliansimpanupdate");
}

>>>>>>> .r28418
function fPJBPA_Batal(){
	doAjaxCall${formName}("","");
}

function fPJBPA_Kembali(){
	doAjaxCall${formName}("","");
}

<!-- end of script-->

function PenjualPembeli(){
	if(document.${formName}.PenjualSama.checked == true){
		doAjaxCall${formName}("Maklumat","mode=penjualview");
	}
	
	if(document.${formName}.PenjualSama.checked == false){
		doAjaxCall${formName}("Maklumat","mode=penjualview");
	}
		
}

//script test5.jsp

function doChangePenjual(){
		doAjaxCall${formName}("Maklumat","mode=penjualview&dochange=doChangePenjual&tabId=4");
}

function doChangeNegeriPenjual(){
	doAjaxCall${formName}("Maklumat","mode=penjualview&dochange=negeri&tabId=4");
}

function SimpanPenjual(){
	doAjaxCall${formName}('Maklumat','mode=penjualsimpan');
}

function KemaskiniPenjual(){
	
}

function BatalPenjual(){
	
}

function KembaliPenjual(){
	
}

function SeterusnyaPenjual(){
	doAjaxCall${formName}('Perjanjian','mode=drafview');
}


//end of script


//script for frmPembelianJualBeliSenaraiSemakAjax.jsp

function fPJBSSA_KemaskiniSemak(){
	doAjaxCall${formName}('Perjanjian','mode=senaraisemakkemaskini&tabId=1');
}

function fPJBSSA_SimpanSemak(){
	doAjaxCall${formName}('Perjanjian','mode=senaraisemakSimpan');
}

function fPJBSSA_BatalSemak(){
	doAjaxCall${formName}('Perjanjian','mode=');
}

function fPJBSSA_KembaliSemak(){
	doAjaxCall${formName}('Perjanjian','mode=');
}



//end of script

function doChangeNegeriPeguam(){
	doAjaxCall${formName}('Maklumat','mode=peguamview&dochange=negeri&tabId=3');
}

function doChangeNegeriPeguamKemaskini(){
	doAjaxCall${formName}('Maklumat','mode=peguamkemaskini&dochange=negeri&tabId=3');
}

//perakuan jkptg
function fPTPJKPTG_SimpanUlasan() {
	if(document.${formName}.txdTarikhHantar.value == ""){
		alert('Sila pilih " Tarikh Hantar " terlebih dahulu.');
  		document.${formName}.txdTarikhHantar.focus();
		return;
	}
	if ( !window.confirm("Anda Pasti?") ) return;

	doAjaxCall${formName}('Maklumat','mode=perakuankptgsimpan&tabId=5');
}

function fPTPJKPTG_kemaskini() {
	doAjaxCall${formName}('Maklumat','mode=perakuankptgkemaskini&tabId=5');
}

function doChangeNegeriPembeli(){
	doAjaxCall${formName}('Maklumat','mode=pemilik&dochange=negeri&tabId=2');
}

//script untuk perjanjian tmbahan (frmPembelianPerjanjianTambahan.jsp)

function fPPT_Kemaskini(idPerjanjianTambahan){
	doAjaxCall${formName}('Perjanjian','mode=ptambahankemaskini&idPerjanjianTambahan='+idPerjanjianTambahan);
}

function paparPTambahan(idPerjanjianTambahan){
	doAjaxCall${formName}('Perjanjian','mode=ptambahanview&dochange=view&idPerjanjianTambahan='+idPerjanjianTambahan);
}

function fPPT_SimpanPerjanjianTambahan(){
		doAjaxCall${formName}('Perjanjian','mode=ptambahansimpan');
}

function fPPT_SimpanUpdatePerjanjianTambahan(idPerjanjianTambahan){
		doAjaxCall${formName}('Perjanjian','mode=ptambahansimpanupdate&idPerjanjianTambahan='+idPerjanjianTambahan);
}

function fPPT_Batal(){
		doAjaxCall${formName}('Perjanjian','mode=ptambahanbatal');
}

function fPPT_Kembali(){
		doAjaxCall${formName}('Perjanjian','mode=ptambahankembali');
}

function daftarBaruPTambahan(){
		doAjaxCall${formName}('Perjanjian','mode=ptambahanview&dochange=newPTambahan');
}




</script>
