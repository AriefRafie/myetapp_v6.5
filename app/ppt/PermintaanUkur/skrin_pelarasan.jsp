








<style type="text/css"  >
td.td_total_pelarasan {
	border-top:1px solid blue;
	border-bottom:3px  double blue;	
}

td.td_total_pengiraan {
	border-top:1px solid black;
}
</style>

#if($jenisPU == "internal")

        #foreach($data in $dataHakmilikBorangK)
            #set($lblMukim=$data.nama_mukim)
            #set($lblJenisHakmilik=$data.jenis_hakmilik)
            #set($lblNoHakmilik=$data.no_hakmilik)
            #set($lblTarikhDaftar=$data.tarikh_daftar)
            #set($lblNoLot=$data.no_lotpt)
            #set($lblTarikhBorangK=$data.tarikh_borangk)
            #set($lblLuasAmbil=$data.luas_ambil)
            #set($lblUnitLuas=$data.unitByKategori)
            #set($lblLuasLot=$data.luas_lot)
            #set($countBakiLuas=$data.baki_luas_asal)
            #set($lblNoSyit=$data.no_syit)
            #set($tarikh_borangk=$data.tarikh_borangk)   
        #end

#end

#if($jenisPU == "standalone")
        #foreach($data in $dataPU)
                        #set($txtNoHakmilik=$data.NO_HAKMILIK)
                        #set($txtNoLot=$data.NO_LOT)
                        #set($txdTarikhBorangK=$data.TARIKH_BORANGK)
                        #set($tarikh_borangk=$data.TARIKH_BORANGK)  
                        #set($txtLuasAmbil=$data.LUAS_AMBIL_HM)
                        #set($sorDropdownUnit=$data.ID_UNITLUASAMBIL)                
                        #set($txtLuasAsal=$data.LUAS_ASAL_HM)
                        #set($sorDropdownUnitAsal=$data.ID_UNITLUASASAL)			
                        #set($txtNoFailPU=$data.NO_FAIL_PU)
                        #set($txtNoPelan=$data.NO_PELAN)
                        #set($txtNoPU=$data.NO_PU)
                        #set($txdTarikhSuratPTG=$data.TARIKH_SURAT_PTG)
                        #set($txdTarikhHantarJUPEM=$data.TARIKH_HANTAR_JUPEM)
                        #set($txdTarikhBorangPU=$data.TARIKH_PU)				
                        #set($txdTarikhTerimaPA=$data.TARIKH_TERIMA_PA)
                        #set($txtNoPA=$data.NO_PA)
                        #set($txdTarikhTerimaSA=$data.TARIKH_TERIMA_SA)
                        #set($txdTarikhPenyelesaian=$data.TARIKH_SELESAI)
                        #set($txtCatatan=$data.CATATAN)
                        #set($txtLotBaru=$data.NO_LOT_BARU)			    
                        #set($txtKeluasanPU=$Utils.formatLuasHM($Utils.parseDouble($data.LUAS_PU)))
                        #set($countBezaLuas=$Utils.formatLuasHM($Utils.parseDouble($data.BEZA_LUAS)))
                        #set($countBakiLuas=$Utils.formatLuasHM($Utils.parseDouble($data.BAKI_LUAS)))
                        #set($lblLuasAsal=$Utils.formatLuasHM($Utils.parseDouble($data.LUAS_ASAL)))
                        #set($lblLuasLot=$Utils.formatLuasHM($Utils.parseDouble($data.LUAS_ASAL)))
                        #set($lblLuasAmbil=$Utils.formatLuasHM($Utils.parseDouble($data.LUAS_AMBIL)))
                        #set($sorJenisPelarasan=$data.JENIS_PELARASAN)
                        
                        #set($getNilaiSeunit=$data.nilai)
                        #set($NilaiSeunit=$data.nilaiseunit)
                        
                        
                        #set($temp_tarikhBayarTambahan=$data.TARIKH_BAYARAN_TAMBAHAN)
                        #set($tarikhBayarTambahan=$data.TARIKH_BAYARAN_TAMBAHAN)
                        
                        
                        #set($txtKeluasanPU=$Utils.formatLuasHM($Utils.parseDouble($data.LUAS_PU)))
                        #set($temp_luasPA=$Utils.formatLuasHM($Utils.parseDouble($data.LUAS_PU)))
                       
                        

        #end
#end



<div id="TabbedPanelsPelarasan" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="klikTab('0')">Pelarasan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="klikTab('1')">Bahagian Perseorangan</li>
    <li class="TabbedPanelsTab" tabindex="0" id="tab_caj_1" onclick="klikTab('2')">Caj Bayaran Lewat 8%</li>
    <li class="TabbedPanelsTab" tabindex="0" id="tab_caj_2" onclick="klikTab('3')">Bahagian Perseorangan (Caj Bayaran Lewat)</li>
    <li class="TabbedPanelsTab" tabindex="0" id="tab_caj_3" onclick="klikTab('4')">Keseluruhan Pelarasan</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
    
    <fieldset  >
<legend>Pelarasan Perbezaan Keluasan</legend>





  #set($M = "")
                #set($type = "")
                    #if($jenisPU == "internal")
                        #set($temp_luasPA = "")
                    #end

				#if($mode=="new")
                    #if($jenisPU == "internal")
                        #set($txtKeluasanPU = "")
                    #end   
                #set($disability = "")
				#set($disabilityx = "")
                #set($M = "*")
                #set($type = "text")
                
                #end

				#if($mode=="view")
                    #if($jenisPU == "internal")
                        #foreach($dataPU in $dataPermintaanUkur)
                            #set($txtKeluasanPU=$dataPU.luas_pu)
                            #set($countBezaLuas=$dataPU.beza_luas)
                            #set($sorJenisPelarasan=$dataPU.jenis_pelarasan)
                            #set($lblBezaLuas=$dataPU.lblBezaLuas)
                        #end
                    #end
  				
  				#if($isEdit=="no")
					#set($disability = "readonly")
					#set($disabilityx = "class=disabled")
					#set($disability1 = "disabled")
					#set($M = "")
                    #set($type = "hidden")
                    #if($jenisPU == "internal")
                     	#set($temp_luasPA = $!txtKeluasanPU)
                    #end
				#else
					#set($M = "*")
					#set($disability = "")
					#set($disabilityx = "")
					#set($disability1 = "")
                    #set($type = "text")
				#end
                
                #end
                
                

<table width="100%" border="0"  >
<tr>
<td width="50%" valign="top">

<div id="print_1" >
<table width="100%" border="0" align="left"  >               
<tr>
<td align="right" valign="top" ><font color="red">$M</font></td>
<td align="left" valign="top">Luas Pelan Akui </td>
<td valign="top"  ><b>A</b></td>
<td valign="top">:</td>
<td align="right" valign="top">

#if($jenisPU == "internal")
    <font color="blue">$!temp_luasPA</font>
    #if($type=="text")
    <input type="$type" $disability $disabilityx  name="txtKeluasanPU" id="txtKeluasanPU" value="$!txtKeluasanPU" size="10" style="text-align:right" maxlength="11" onblur="validateLuas(this,this.value,'$!txtKeluasanPU');kiraBezaLuasNew('$saiz_pb')" onkeyup="validateNilai(this,this.value);kiraBezaLuasNew('$saiz_pb')" >
    #end
#end

#if($jenisPU == "standalone")
    #if($type=="text")
     <input type="$type" $disability $disabilityx  name="txtKeluasanPU" id="txtKeluasanPU" value="$!txtKeluasanPU" size="10" style="text-align:right" maxlength="11" onblur="validateLuas(this,this.value,'$!txtKeluasanPU');kiraBezaLuasNew('$saiz_pb')" onkeyup="validateNilai(this,this.value);kiraBezaLuasNew('$saiz_pb')" >
    #else
    <font color="blue">$!temp_luasPA</font>
    #end
#end

</td>
<td align="left" valign="top">Hektar</td>
</tr>
<tr>
<td width="1%" valign="top"></td>
<td width="36%" valign="top" align="left">Luas Asal</td>
<td width="2%" valign="top"><b>B</b></td>
<td width="1%" valign="top">:</td>
<td width="25%" valign="top" align="right"><font color="blue">$!lblLuasLot</font></td>
<td width="35%" valign="top" align="left">Hektar</td>
</tr>
<input type="hidden" name="bakiLuasNew" value="$Utils.RemoveSymbol($countBakiLuas)">
<input type="hidden" name="countBezaLuas" id="countBezaLuas"  >
<input type="hidden" name="sorJenisPelarasan" id="sorJenisPelarasan" >
<input type="hidden" name="hdJenisPelarasan">
#if($type!="text")
<input type="$type" $disability $disabilityx  name="txtKeluasanPU" id="txtKeluasanPU" value="$!txtKeluasanPU" size="10" style="text-align:right" maxlength="11" onblur="validateLuas(this,this.value,'$!txtKeluasanPU');kiraBezaLuasNew('$saiz_pb')" onkeyup="validateNilai(this,this.value);kiraBezaLuasNew('$saiz_pb')" >
#end
<tr>
<td valign="top"></td>
<td align="left" valign="top">Luas Borang K</td>
<td valign="top"><b>C</b></td>
<td valign="top">:</td>
<td align="right" valign="top"><font color="blue">$!lblLuasAmbil</font></td>
<td align="left" valign="top" >Hektar</td>
</tr>
<tr>
<td valign="top"></td>
<td align="left" valign="top">Baki Luas Asal <b>(B-C)</b></td>
<td valign="top"><b>D</b></td>
<td valign="top">:</td>
<td align="right" valign="top"><font color="blue">$!countBakiLuas</font>
</td>
<td align="left" valign="top">Hektar</td>
</tr>	
<tr>
<td valign="top">&nbsp;</td>
<td align="left" valign="top">Beza Luas <b>(D-A)</b></td>
<td valign="top" ><b></b></td>
<td valign="top">:</td>
<td align="right" class="td_total_pelarasan" valign="top">
<div id="beza_luas" style="text-align:right"></div>
</td>
<td align="left" valign="top">Hektar</td>
</tr>
<tr>
<td valign="top"></td>
<td align="left" valign="top">Jenis Pelarasan</td>
<td valign="top"  ><b></b></td>
<td  valign="top">:</td>
<td align="left"  valign="top"> 
<div id="jenis_pelarasan"></div>
</td>	
<td align="left" valign="top"></td>
</tr>
</table>
</div>

</td>

<td width="50%" valign="top">

<div id="print_2" >
<table width="100%" border="0" align="left" >
<tr>
<td valign="top"  width="1%"></td>
<td valign="top" width="38%" align="left">Perbezaan Luas</td>
<td valign="top" width="1%">:</td>
<td valign="top"  width="25%" align="right"><font color="blue"><div id="beza_luas_mp" style="text-align:right"></div></font></td>
<td width="25%" align="left" valign="top" >Meter Persegi</td>
</tr>
<tr>



<td valign="top"  >
#if($jenisPU == "standalone")
<font color="red">$M</font>
#end
</td>
<td  valign="top" align="left">Nilai Award #if($jenisPU == "standalone")(RM)
#end
</td>
<td valign="top" >:</td>
<td valign="top"  align="right">
#if($jenisPU == "internal")
<font color="blue">$!getNilaiSeunit</font> 
<input type="hidden" name="NilaiSeunit" id="NilaiSeunit"  value="$!NilaiSeunit" >
#end

#if($jenisPU == "standalone")
#if($type=="text")
<input type="text" name="NilaiSeunit" id="NilaiSeunit"  value="$!NilaiSeunit" style="text-align:right" onblur="validateModal(this,this.value,'$!NilaiSeunit');kiraBezaLuasNew('$saiz_pb')" onkeyup="validateNilai(this,this.value);kiraBezaLuasNew('$saiz_pb')" >
#else
<font color="blue">$!getNilaiSeunit</font>
<input type="hidden" name="NilaiSeunit" id="NilaiSeunit"  value="$!NilaiSeunit" onblur="validateModal(this,this.value,'$!txtLuasAsal');"  onkeyup="validateNilai(this,this.value);" >
#end
#end


</td>
<td valign="top"  align="left">Per Meter Persegi</td>
</tr>
<tr>
<td valign="top" ></td>
<td  valign="top" align="left">Nilai Award (RM) x Luas Perbezaan</td>
<td valign="top" >:</td>
<td  valign="top" align="right" class="td_total_pelarasan"><font color="blue"><div id="jumlah_nilai" style="text-align:right"></div></font></td>
<td   valign="top" align="left"><div id="jenis_pelarasan_nilai" style="text-align:left"></div></td>
</tr>
</table>
<input type="hidden" name="countBezaLuasMp" id="countBezaLuasMp"  >

</div>


</td>
</tr>
</table>

</fieldset>



#if($jenisPU == "internal")
			<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updatePelarasanLuas('$!id_permintaanukur')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPelarasanLuas('$!id_permintaanukur')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updatePelarasanLuas('$!id_permintaanukur')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskiniPelarasanLuas('$!id_permintaanukur')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
   #end 
    </div>
    <div class="TabbedPanelsContent">
    
    <fieldset>
<legend>Pengiraan Mengikut Bahagian Perseorangan</legend>



<table width="50%" border="0"  align="left"  >
<tr>
<td>

<table width="100%" border="0"  align="left"  >
<tr>
<td width="1%" ></td>
<td  width="36%" valign="top" align="left">Jumlah Pampasan
</td>
<td valign="top" width="3%"  align="right">:</td>
<td  valign="top"  width="25%" align="right" class="td_total_pelarasan"><font color="blue"><div id="jumlah_nilai_bhg" style="text-align:right"></div></font></td>
<td   valign="top" width="35%" align="left"><div id="jenis_pelarasan_nilai_bhg" style="text-align:left"></div></td>
</tr>
</table>

</td>
</tr>

<tr>
<td>

<table width="100%" border="0" align="left"  >
<tr class="table_header" >
<td width="5%" align="center"  valign="top" >Bil</td>
<td width="50%" align="left" valign="top" >Nama Tuan Tanah</td>
<td width="20%" align="center" valign="top" >Bahagian</td>
<td width="25%" align="right"  valign="top"> Jumlah Bayaran (RM)</td>
</tr>
</table>

</td>
</tr>	


 #if($saiz_pb!=0)
<tr>
<td>	  
                
             
  				#set($wid_tab = "100%")                 
                 <table width="$wid_tab" border="0" align="left"   >  
                
			    
            		#foreach($listPB in $listMaklumatPB)
              			#set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              			#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
                            
               		<tr>
                   		<td width="5%"  class="$row" align="center">$!listPB.bil</td>
                   		<td width="50%" class="$row" align="left">$!listPB.nama_pb<br />
                        $!listPB.no_pb</td>
                   		<td width="20%" class="$row" align="center">$!listPB.syer_atas / $!listPB.syer_bawah
                        #set($div_syer_atas = "div_syer_atas"+$!listPB.bil)
                        <input type="hidden" id="$div_syer_atas" name="$div_syer_atas"  value="$!listPB.syer_atas" />
                        #set($div_syer_bawah = "div_syer_bawah"+$!listPB.bil)
                        <input type="hidden" id="$div_syer_bawah" name="$div_syer_bawah"  value="$!listPB.syer_bawah" />
                        </td>   
                        <td width="25%" class="$row" align="right">
                        #set($div_bhg_per = "div_bhg_per"+$!listPB.bil)                       
                        <div id="$div_bhg_per"></div>
                        </td>                 		
              		</tr>
                   
           			#end
                     </table>
</td>
</tr>
 
<tr>
<td>
 
 
 
                   <table width="100%" border="0"  align="left"  >   
                    <tr class="table_header" >
                   		
                   		<td width="55%"  align="right" > <b>JUMLAH :</b></td>
                   		<td width="20%" align="center"><div id="div_frac_total"></div></td>   
                        <td  width="25%" align="right">
                        <div id="div_frac_nilai"></div>
                        </td>                 		
              		</tr>
                    </table>
                    
</td>
</tr>                    
                    
        		#else
<tr>
<td>
                
                <table width="50%" border="0" align="left"  >  
                    <tr>
                    	<td colspan="5">Tiada rekod</td>
                    </tr>
                    </table>
</td>
</tr>                    
        		#end

</table>
</fieldset>

    
    </div>
    
    
    <div class="TabbedPanelsContent">
    
<fieldset id="fs_bayaran_lewat" style="display:none">
<legend>Caj Bayaran Lewat 8%</legend>

<table width="50%" border="0"  align="left"  >
<tr>
<td width="1%" ></td>
<td  width="36%" valign="top" align="left">Tarikh Borang K
</td>
<td valign="top" width="3%"  align="right">:</td>
<td  valign="top"  width="25%" align="right" ><font color="blue">$tarikh_borangk</font>
<input type="hidden" id="dtBorangK" name="dtBorangK" value="$tarikh_borangk">
</td>
<td   valign="top" width="35%" align="left"></td>
</tr>


  				#set($M = "")
                #set($type = "")
                #if($jenisPU == "internal")
                	#set($temp_tarikhBayarTambahan = "")
                #end    

				#if($mode=="new")
  				
                #if($jenisPU == "internal")
  					#set($tarikhBayarTambahan = "")
                #end
                
                    
                #set($disability = "")
				#set($disabilityx = "")
                #set($M = "*")
                #set($type = "text")
                
                #end

				#if($mode=="view")
                    #if($jenisPU == "internal")
                        #foreach($dataPU in $dataPermintaanUkur)
                            #set($tarikhBayarTambahan=$dataPU.tarikh_bayaran_tambahan)    			
                        #end
                    #end
  				
  				#if($isEdit=="no")
					#set($disability = "readonly")
					#set($disabilityx = "class=disabled")
					#set($disability1 = "disabled")
					#set($M = "")
                    #set($type = "hidden")
                    #if($jenisPU == "internal")
                     	#set($temp_tarikhBayarTambahan = $!tarikhBayarTambahan)
                    #end    
				#else
					#set($M = "*")
					#set($disability = "")
					#set($disabilityx = "")
					#set($disability1 = "")
                    #set($type = "text")
				#end
                
                #end


<tr>
<td align="right" valign="top" ><font color="red">$M</font></td>
<td align="left" valign="top">Tarikh Bayaran Tambahan</td>
<td valign="top"  align="right">:</td>
<td align="right" valign="top">
#if($jenisPU == "internal")
	<font color="blue">$temp_tarikhBayarTambahan</font>
#end

#if($jenisPU == "standalone")
#if($type=="hidden")
<font color="blue">$temp_tarikhBayarTambahan</font>
#end
#end


<input type="$type" $disability $disabilityx  name="tarikhBayarTambahan" id="tarikhBayarTambahan" size="11"  value="$tarikhBayarTambahan" onkeyup="validateTarikh(this,this.value);kiraBezaLuasNew('$saiz_pb')" onblur="check_date(this);kiraBezaLuasNew('$saiz_pb')" >
            			
                     
</td>
<td  valign="top" align="left">   #if($type == "text")
                        	<img src="../img/calendar.gif" onclick="displayDatePicker('tarikhBayarTambahan',false,'dmy');">&nbsp;$!frmtdate
                            #end</td>
</tr>

<tr>
<td align="right" valign="top" ></td>
<td align="left" valign="top">Bil. Hari</td>
<td valign="top"  align="right">:</td>
<td align="right" valign="top">
<div id="set_total_day"></div> 
</td>
<td  valign="top" align="left"> 
</tr>

<tr>
<td colspan="6" align="left" valign="top">

<div id="set_div_date"></div>
</td>
</tr>
</table>




</fieldset>

#if($jenisPU == "internal")
				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpanCaj" value ="Simpan" onClick="javascript:updatePelarasanLuas('$!id_permintaanukur')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskiniCaj" value ="Kemaskini" onClick="javascript:kemaskiniPelarasanLuas('$!id_permintaanukur')">
						#else
						<input type="button" name="cmdUpdateCaj" value ="Simpan" onClick="javascript:updatePelarasanLuas('$!id_permintaanukur')">
						<input type="button" name="cmdBatalCaj" value ="Batal" onClick="javascript:batalKemaskiniPelarasanLuas('$!id_permintaanukur')">
						#end
					#end
				
						<input type="button" name="cmdKembaliCaj" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
   #end 

    
    </div>
    
    
    <div class="TabbedPanelsContent">
    
    
    
    <fieldset id="fs_pelarasan_bayaran_lewat" style="display:none"  >
<legend>Butir-Butir Pelarasan Caj Bayaran Lewat 8%</legend>

<table width="50%" border="0"  align="left"  >
<tr>
<td  >

<table width="100%" border="0"  align="left"  >
<tr>
<td width="1%" ></td>
<td  width="36%" valign="top" align="left">Jumlah Pampasan (Caj Bayaran Lewat)
</td>
<td valign="top" width="3%"  align="right">:</td>
<td  valign="top"  width="25%" align="right" class="td_total_pelarasan"><font color="blue"><div id="jumlah_bayaran_lewat"></div></font></td>
<td   valign="top" width="35%" align="left"></td>
</tr>
</table>

</td>
</tr>

<tr>
<td  >



<table width="100%" border="0"  >
<tr class="table_header" >
<td width="5%" align="center"  valign="top" >Bil</td>
<td width="50%" align="left" valign="top" >Nama Tuan Tanah</td>
<td width="20%" align="center" valign="top" >Bahagian</td>
<td width="25%" align="right"  valign="top"> Jumlah Bayaran (RM)</td>
</tr>
</table>
			  
</td>
</tr>               
             
 
 
    #if($saiz_pb!=0)
    
     				#set($wid_tab = "100%") 
               
    <tr>
<td  >            
                 <table width="$wid_tab" border="0"  >  
                
			  
            		#foreach($listPB in $listMaklumatPB)
              			#set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              			#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
                            
               		<tr>
                   		<td width="5%"  class="$row" align="center">$!listPB.bil</td>
                   		<td width="50%" class="$row" align="left">$!listPB.nama_pb<br />
                        $!listPB.no_pb</td>
                   		<td width="20%" class="$row" align="center">$!listPB.syer_atas / $!listPB.syer_bawah
                        #set($div_syer_atas_caj = "div_syer_atas_caj"+$!listPB.bil)
                        <input type="hidden" id="$div_syer_atas_caj" name="$div_syer_atas_caj"  value="$!listPB.syer_atas" />
                        #set($div_syer_bawah_caj = "div_syer_bawah_caj"+$!listPB.bil)
                        <input type="hidden" id="$div_syer_bawah_caj" name="$div_syer_bawah_caj"  value="$!listPB.syer_bawah" />
                        </td>   
                        <td width="25%" class="$row" align="right">
                        #set($div_bhg_per_caj = "div_bhg_per_caj"+$!listPB.bil)                       
                        <div id="$div_bhg_per_caj"></div>
                        </td>                 		
              		</tr>
                   
           			#end
                     </table>
        </td>
        </tr>
        
        <tr>
		<td  >
        
               
                   <table width="100%" border="0"  >   
                    <tr class="table_header" >
                   		
                   		<td width="55%"  align="right" > <b>JUMLAH :</b></td>
                   		<td width="20%" align="center"><div id="div_frac_total_caj"></div></td>   
                        <td  width="25%" align="right">
                           <div id="div_frac_nilai_caj"></div>
                        </td>                 		
              		</tr>
                    </table>
                    
         </td>
         </tr>
         
          
        		#else
<tr>
<td  >  
                <table width="100%" border="0"  >  
                    <tr>
                    	<td colspan="5">Tiada rekod</td>
                    </tr>
                    </table>
</td>
</tr>
        		#end




</table>
</fieldset>

    
    </div>
     <div class="TabbedPanelsContent">
     
     
     <fieldset>
     <legend>Jumlah Keseluruhan Pampasan</legend>
     
       <table width="100%" border="0"  align="left"  >
   
   
<tr>
<td>

<table width="100%" border="0" align="left"  >
<tr  class="table_header" >
<td width="2%" rowspan="2" align="center"  valign="top" >Bil</td>
<td width="20%" rowspan="2" align="left" valign="top" >Nama Tuan Tanah</td>
<td width="25%" rowspan="2" align="left" valign="top" >Penerima / Penama</td>
<td width="8%" rowspan="2" align="center" valign="top" >Bil. Hari Lewat</td>
<td width="45%"  align="center" valign="top" colspan="3" >Nilai Pelarasan (RM)</td>
</tr>
<tr  class="table_header" >
<td width="15%"  align="right"valign="top" >Tanah</td>
<td width="15%"  align="right" valign="top" >Caj Bayaran Lewat</td>
<td width="15%" align="right"  valign="top">Keseluruhan</td>
</tr>
</table>

</td>
</tr>	


 #if($saiz_pb!=0)
<tr>
<td>	  
                
             
  				                
                 <table width="100%" border="0" align="left"   >  
                
			    
            		#foreach($listPB in $listMaklumatPB)
              			#set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              			#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
                            
               		<tr>
                   		<td width="2%"  class="$row" valign="top"  align="center">$!listPB.bil</td>
                   		<td width="20%" class="$row" valign="top"  align="left">$!listPB.nama_pb
                        #if($!listPB.no_pb != "")
                        <br />$!listPB.no_pb
                        #end
                        
                        #if($!listPB.syer_atas != "" && $!listPB.syer_bawah != "")
                        <br />
                        Bhg : $!listPB.syer_atas / $!listPB.syer_bawah
                        #end  
                        
                        #set($div_syer_atas_all = "div_syer_atas_all"+$!listPB.bil)
                        <input type="hidden" id="$div_syer_atas_all" name="$div_syer_atas_all"  value="$!listPB.syer_atas" />
                        #set($div_syer_bawah_all = "div_syer_bawah_all"+$!listPB.bil)
                        <input type="hidden" id="$div_syer_bawah_all" name="$div_syer_bawah_all"  value="$!listPB.syer_bawah" />
                        
                        
                        #set($id_award_all = "id_award_all"+$!listPB.bil)
                       
                        <input type="hidden" id="$id_award_all" name="$id_award_all"  style="width:100%"  value="$!listPB.id_award" />
                        </td>
                   		<td width="25%" class="$row"  valign="top" align="left">
                        #set($div_penama_all = "div_penama_all"+$!listPB.bil)
                        #set($text_penama_all = "text_penama_all"+$!listPB.bil)
                        
                        
                        #set($penama_default = "$!listPB.nama_pb")
                        #if($!listPB.penama != "")
                        #set($penama_default = "$!listPB.penama")
                        #end
                        
                        
                      
                #set($type = "")
                #if($mode=="new")  				
                    #set($disability = "")
                    #set($disabilityx = "")
                    #set($type = "text")                
                #end

				#if($mode=="view") 				
                    #if($isEdit=="no")
                        #set($disability = "readonly")
                        #set($disabilityx = "class=disabled")                       		
                        #set($type = "hidden")
                    #else
                        #set($disability = "")
                        #set($disabilityx = "")                       
                    #end                
                #end
                        
                        #if($type=="hidden")
                         <div id="$div_penama_all" >$!penama_default</div>
                        #else
                         <div id="$div_penama_all" style="display:none">$!penama_default</div>
                        #end
                       
                        <input type="$type" $disability $disabilityx id="$text_penama_all" name="$text_penama_all"  style="width:100%"  value="$!penama_default" />
                        
                        
                        
                        </td>   
                        <td width="8%" class="$row" valign="top" align="center">
                        #set($div_tempoh_lewat_all = "div_tempoh_lewat_all"+$!listPB.bil)
                        #set($text_tempoh_lewat_all = "text_tempoh_lewat_all"+$!listPB.bil)
                        <div id="$div_tempoh_lewat_all"></div>
                        <input type="hidden" id="$text_tempoh_lewat_all" name="$text_tempoh_lewat_all"  style="width:100%"  />
                        </td> 
                        <td width="15%" class="$row" valign="top" align="right">                        
                        #set($div_pampasan_tanah_all = "div_pampasan_tanah_all"+$!listPB.bil)
                        #set($text_pampasan_tanah_all = "text_pampasan_tanah_all"+$!listPB.bil)
                        <div id="$div_pampasan_tanah_all"></div>
                        <input type="hidden" id="$text_pampasan_tanah_all" name="$text_pampasan_tanah_all" style="width:100%"   />
                        </td>
                        <td width="15%" class="$row" valign="top" align="right">
                        #set($div_bhg_per_caj_all = "div_bhg_per_caj_all"+$!listPB.bil)  
                        #set($text_bhg_per_caj_all = "text_bhg_per_caj_all"+$!listPB.bil)                       
                        <div id="$div_bhg_per_caj_all"></div>
                         <input type="hidden" id="$text_bhg_per_caj_all" name="$text_bhg_per_caj_all" style="width:100%"  />
                        </td>   
                        <td width="15%" class="$row" valign="top" align="right">
                        #set($div_total_all = "div_total_all"+$!listPB.bil) 
                        #set($text_total_all = "text_total_all"+$!listPB.bil)                        
                        <div id="$div_total_all"></div>
                        <input type="hidden" id="$text_total_all" name="$text_total_all" style="width:100%"   />
                        </td>                  		
              		</tr>
                   
           			#end
                     </table>
</td>
</tr>
 
<tr>
<td>
 
 
 
                   <table width="100%" border="0"  align="left"  >   
                    <tr class="table_header" >
                   		
                   	
                   		<td width="55%"  align="right" colspan="3">  
                        <b>JUMLAH :</b>                                        
                        </td>
                   		
                        <td width="15%"  align="right">
                        <div id="div_total_pampasan_tanah_all"></div>
                        </td>
                         <td width="15%"  align="right">
                        <div id="div_total_bhg_per_caj_all"></div>
                        </td>   
                         <td width="15%"  align="right">
                         <div id="div_total_total_all"></div>
                        </td>                  		     		
              		</tr>
                    </table>
                    
</td>
</tr>                    
                    
        		#else
<tr>
<td>
                
                <table width="100%"  border="0" align="left"  >  
                    <tr>
                    	<td colspan="6">Tiada rekod</td>
                    </tr>
                    </table>
</td>
</tr>                    
        		#end

   
   
 </table>
 
 
 #if($jenisPU == "internal")
 <table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpanAll" value ="Simpan" onClick="javascript:updatePelarasanLuas('$!id_permintaanukur')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskiniAll" value ="Kemaskini" onClick="javascript:kemaskiniPelarasanLuas('$!id_permintaanukur')">
						#else
						<input type="button" name="cmdUpdateAll" value ="Simpan" onClick="javascript:updatePelarasanLuas('$!id_permintaanukur')">
						<input type="button" name="cmdBatalAll" value ="Batal" onClick="javascript:batalKemaskiniPelarasanLuas('$!id_permintaanukur')">
						#end
					#end
				
						<input type="button" name="cmdKembaliAll" value ="Kembali" onClick="javascript:viewListHM('$!id_permohonan')">
						</td>
					</tr>
				</table>
   #end 
     
     
     </fieldset>
     
     
     
     </div>
  </div>
</div>


<input type="hidden" name="jenisPU" id="jenisPU"  value="$!jenisPU" >





<script type="text/javascript">
//alert("MASUK");

window.location.hash='TabbedPanelsPelarasan';

setTab('$selectedTabPelarasan');
kiraBezaLuasNew('$saiz_pb');


//alert("1"+'$selectedTabPelarasan');



</script>
