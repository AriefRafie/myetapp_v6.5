#parse("app/ppt/Sek4Paging.jsp")
#parse("app/ppt/frmLabelSet.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">
<legend><strong>Urusan MMK/MB Seksyen 4</strong></legend>  
  
	#parse("app/ppt/frmPPTHeader.jsp")
	
<br/>    




    <fieldset> 
    <legend><strong>Maklumat MMK/MB</strong></legend>  
	
       <div id="TabbedPanels1" class="TabbedPanels">
       
       <ul class="TabbedPanelsTabGroup">
         	
         	<li class="TabbedPanelsTab"  onclick="setSelected(0);PenyedianView('$!id_permohonan')">Penyediaan</li>       	
         	#if($flag_semak==1 || $flag_semak==2)<li class="TabbedPanelsTab" index="0" onclick="setSelected(1);SemakanView('$!id_permohonan')">Semakan</li>#end         	
         	#if($flag_semak==2)<li class="TabbedPanelsTab" index="0" onclick="setSelected(2);KeputusanView('$!id_permohonan')">Keputusan PBN</li>#end
       
       </ul>
       
       <div class="TabbedPanelsContentGroup">
         
       <div class="TabbedPanelsContent">
          <a href="javascript:open_info()" class="help" title="info"><b><font color="red"><span class="blink"><i>* Peringatan! sila klik disini untuk maklumat lanjut.</i></span></font></b></a>
    	

		#set($flagSemakPengarah = "")
		#set($username = "")
        
        #set($txtTajuk= "")
        #set($txtSidang= "")
        #set($txtTempatSidang= "")
        #set($txtTarikhSidang= "")
        #set($txtMasaSidang= "")
        #set($jeniswaktu= "")

		

		#foreach($vk in $viewKertas)
        
        #set($txtSidang= $vk.KETERANGAN_SIDANG)
        #set($txtTempatSidang= $vk.TEMPAT_SIDANG)
        #set($txtTarikhSidang= $vk.TARIKH_SIDANG)
        #set($txtMasaSidang= $vk.WAKTU_SIDANG)
        #set($jeniswaktu= $vk.JENIS_WAKTU_SIDANG)        
        #set($txtTajuk= $vk.TAJUK)
		
		#set($id_mmk = $vk.id_mmk)
		#set($ulasan = $vk.ulasan)
		#set($noRujukan = $vk.no_rujmmk)
		#set($tarikh_semak = $vk.tarikh_semak)
		#set($tarikh_mmk = $vk.tarikh_mmk)	
		#set($username = $vk.user_name)
		#set($tarikhHantarKP = $vk.tarikh_hantar)
		
		#set($txttujuan = $vk.tujuan)
		#set($txtlatarbelakang = $vk.latarbelakang)
		#set($txtlaporan = $vk.perihal_tanah)
		#set($txtsyor = $vk.syor) 
		#set($txtkesimpulan = $vk.kesimpulan) 
		#set($txtimplikasi = $vk.implikasi) 
		#set($txtasaspertimbangan = $vk.asas_pertimbangan) 
		#set($txtnilaian= $vk.nilai_atas_tanah)
		#set($txtulasan= $vk.ulasan_pengarah)
		#set($txtkeputusan= $vk.keputusan)		
		#set($txtperuntukan= $vk.pengesahan_peruntukan)
		#set($txtperakuan= $vk.perakuan_pentadbir_tnh)
		#set($txtkedudukan= $vk.kedudukan_laporan_tnh)	
		#set($txtperihalpohon= $vk.perihal_pohon)
		#set($txtanggaran= $vk.anggaran_kos)
		#set($txtulasanjt= $vk.ulasan_jabteknikal)
		#set($txtjawatankuasa= $vk.jawatankuasa_tanah)
		#set($txtkedudukan= $vk.kedudukan_laporan_tnh)
		#set($txtpandangan= $vk.pandangan)
		#set($txthallain= $vk.hal_lain)
		#set($flagSemakPengarah = $vk.flag_semakan_pengarah)
		
		#set($txtJenisPenggunaan= $vk.jenis_penggunaan_tnh)
		#set($txtLokasi= $vk.lokasi_tanah)
		#set($txtKedudukan= $vk.kedudukan_tanah)
		#set($txtKeadaan = $vk.keadaan_tanah)
		
		#end



		#if($edit_penyediaan=="yes" || $edit_penyediaan=="new") 
			#set($disabilityP="") 	 
            #set($disabledmode = "")
    	    #set($readonlymode = "")
            
            #set($readmode = "edit")
		#else
			#set($disabilityP="class=disabled readonly")
            #set($disabledmode = "disabled")
            #set($readonlymode = "readonly")
            
            #set($readmode = "view")
		#end 
		
		
		 <!-- MMK by Id Negeri -->
		
		 #if($negeriMMK=="2")
		 	#parse("app/ppt/MMKSek4/mmkKEDAH.jsp")
		 #elseif($negeriMMK=="3")
		 	#parse("app/ppt/MMKSek4/mmkKELANTAN.jsp")
		 #elseif($negeriMMK=="4")
		 	#parse("app/ppt/MMKSek4/mmkMELAKA.jsp")
		 #elseif($negeriMMK=="5")
		 	#parse("app/ppt/MMKSek4/mmkNSEMBILAN.jsp")
		 #elseif($negeriMMK=="6")
		 	#parse("app/ppt/MMKSek4/mmkPAHANG.jsp")
		 #elseif($negeriMMK=="7")
		 	#parse("app/ppt/MMKSek4/mmkPPINANG.jsp")
		 #elseif($negeriMMK=="8")
		 	#parse("app/ppt/MMKSek4/mmkPERAK.jsp")
		 #elseif($negeriMMK=="9")
		 	#parse("app/ppt/MMKSek4/mmkPERLIS.jsp")
		 #elseif($negeriMMK=="10")
		 	#parse("app/ppt/MMKSek4/mmkSELANGOR.jsp")
		 #elseif($negeriMMK=="11")
		 	#parse("app/ppt/MMKSek4/mmkTERENGGANU.jsp")
		 #elseif($negeriMMK=="14" || $negeriMMK=="15" || $negeriMMK=="16")
		 	#parse("app/ppt/MMKSek4/mmkWPKL.jsp")
		 #else
		 	#parse("app/ppt/MMKSek4/mmkSELANGOR.jsp")
		 #end
		 
		<br/>		


#if($tarikhBorangA!="")
	#set($noupdate = "yes")
#else
	#set($noupdate = "no")	
#end 	 
	 
	 <table width="100%" cellspacing="0" cellpadding="0">   
		   <tr align="center">
  				<td>
  					#if($view=="yes")  			
  						#if($edit_penyediaan=="no")
  							<input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini_penyediaan()">
                            
                            #if($ID_NEGERIPROJEK == "4" || $ID_NEGERIPROJEK == "5")          
                            <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Borang A)" onClick="popupEtanah('$id_fail','$id_permohonan','BorangA','')">
                            #end
                            <!--
                            <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah Deraf MMK (Syor Pentadbir Tanah)" onClick="popupEtanah('$id_fail','$id_permohonan','','MMK_S4','')">
                    -->
                            
  							#if($id_status!="31" && $id_status!="52")
       						<input name="cmdHapus" type="button" value="Hapus" onClick="deleteKertasMMK('$!id_mmk')">       			
       						#end
       					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
       					#else
       					<input name="cmdSimpan" type="button" value="Simpan" onclick="update_penyediaan('$!id_mmk')"> 
       					<input name="cmdBatal" type="button" value="Batal" onClick="batal_kemaskini()">
       					#end       			
       				#else       			
       				<input name="cmdSimpan" type="button" value="Simpan"  onclick="simpanPenyediaan('$!id_permohonan')">  
       				#end
       		
       				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliSenaraiKertas('$!id_permohonan','$!id_status')">      		
       			</td>	
       	   </tr>	
	 </table>

         </div>
   
   
   
   
     <!-- TAB SEMAKAN -->    
     #if($flag_semak==1 || $flag_semak==2)    
     
     #if($edit_semakan=="no") 
   		#set($disabilityS="class=disabled readonly") 
   		#set($disabilitySx="class=disabled readonly") 
   		
  		#set($disabilityS1="class=disabled disabled") 
  		#set($M="")
  		#set($M2="")
  		#set($showCal="")
  		#set($showCal2="")
        
        
	 #else
	 
	 	#if($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt" )
			#set($disabilityS="") 	
			#set($disabilityS1="") 
			#set($M="*")
			#set($showCal="yes")		
		#else
			#set($disabilityS="class=disabled readonly") 
  			#set($disabilityS1="class=disabled disabled") 
			#set($M="")
			#set($showCal="")
		#end
		
		#set($disabilitySx="")
		#set($showCal2="yes")
		#set($M2="*")
		
	 #end 

         <div class="TabbedPanelsContent">
         
         <br/>
         <table width="100%" cellspacing="0">
 		 	<tr valign="top">
			  <td width="45%">
			  
			  <table width="100%" border="0">
			  	
			  	<tr>
			  		<td width="38%">Nama Pegawai</td>
			  		<td width="62%">
			  			#if($username!="")
			  				#if($portal_role != "(PPT)KetuaPenolongPengarahUnit" && $portal_role != "(PPT)PengarahUnit" && $portal_role != "adminppt" )
			  					:&nbsp;$!username.toUpperCase()
			  				#else
			  					#if($edit_semakan=="yes")
			  						:&nbsp;$!{session.getAttribute('_portal_username').toUpperCase()}
			  					#else
			  						:&nbsp;$!username.toUpperCase()
			  					#end
			  				#end
			  			#else
			  				#if($portal_role != "(PPT)KetuaPenolongPengarahUnit" && $portal_role != "(PPT)PengarahUnit" && $portal_role != "adminppt" )
			  					:&nbsp;<input type="text" name="lblpegawai" size="20" value="" readonly class="disabled" >
			  				#else
			  					:&nbsp;$!{session.getAttribute('_portal_username').toUpperCase()}
			  				#end
			  			#end
			  		</td>
			  	</tr>
			  
			  	#if($tarikh_semak=="")
			  		#set($tarikhSemakanx=$tarikhSemakan)
			  	#else
			  		#set($tarikhSemakanx=$tarikh_semak)
			  	#end
			  	
                <tr>
                  <td><font color="red">$!M</font>Tarikh Semakan</td>
                  <td>:&nbsp;<input type="text" name="txdTarikhSemak" size="11"  onblur="check_date(this)" value="$!tarikhSemakanx" id="txdTarikhSemak" $disabilityS >
                  #if($edit_semakan=="yes" && $showCal=="yes") 
                  <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSemak',false,'dmy');">$!frmtdate
                  #end	
                  </td>
                </tr>
                
                <tr>
                  <td><font color="red">$!M</font>Keputusan Semakan</td>
                  <td>:&nbsp;<select name="socKeputusanSemak" id="socKeputusanSemak" style="width:auto" $disabilityS1>
                  			<option value="" $select>SILA PILIH</option>
							<option value="1" $selectA>TELAH DISEMAK</option>
							<option value="2" $selectB>BELUM DISEMAK</option>
				  </select></td>
                </tr>
               	
               	<tr>
                  <td>Tarikh Hantar MMK</td>
                  <td>:&nbsp;<input type="text" name="txdTarikhHantar" id="txdTarikhHantar" size="11"  onblur="check_date(this)" value="$!tarikhHantarKP" $disabilitySx >
                  #if($edit_semakan=="yes" && $showCal2=="yes") 
                  <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');">$!frmtdate	
               	  #end
               	  </td>
                </tr>
               
              </table></td>
  				<td valign="top" width="55%"><table width="100%" border="0">
                  <tr>
                    <td valign="top" width="27%">&nbsp;&nbsp;Ulasan</td>
                    <td valign="top" width="1%">:&nbsp;</td>
                    <td width="72%"><textarea name="txtUlasan" cols="40%" rows="5" id="txtUlasan" $disabilityS>$ulasan</textarea></td>
                  </tr>

                </table></td>
			</tr>
            </table>
            
            #if($edit_semakan=="yes") 
            <table width="100%" border="0">
        		<tr><td>&nbsp;</td></tr>
        		<tr>
        			<td>$!perhatian3</td>
        		</tr>
        	</table>
        	#else
        	<br/>
       		#end
  		
      <table width="100%" cellspacing="0" cellpadding="0"> 
		   <tr align="center">
  			<td>
  			#if($view=="yes")
  			
  				<!-- if flag_semakan_pengarah == ""  && role != pgh -->
  				#if($flagSemakPengarah == "" && ($portal_role != "(PPT)KetuaPenolongPengarahUnit" && $portal_role != "(PPT)PengarahUnit" && $portal_role != "adminppt" ))
  					<img src="../img/emel.gif" title="Minta untuk semakan juga akan dihantar melalui emel" >
  					<input name="cmdSimpan" type="button" value="Hantar Untuk Semakan" onclick="hantarPengesahan('$id_mmk')"> 
  				#end
  				
  				#if($edit_semakan=="no") 		
  					#if(($tarikh_semak!="") || ($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt" ))
       				<input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini_semakan()">
       				#end
       				<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
       			#end
       			
       			#if($edit_semakan=="yes") 
       				<input name="cmdSimpan" type="button" value="Simpan" onclick="update_item_semakan('$id_mmk')"> 
       				<input name="cmdBatal" type="button" value="Batal" onClick="batal_kemaskini()">
       			#end
       	
       			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliSenaraiKertas('$id_permohonan','$id_status')">
       			
       		#end
       		</td>	
       	   </tr>	
	 </table>
	 
         </div>
     #end
     
     
     
     <!-- TAB KEPUTUSAN -->
     #if($flag_semak==2)    
     
     	#foreach($vkk in $viewKertasKeputusan)	
		#set($tarikh_terimaKP=$vkk.tarikh_terima)
		#set($tarikhHantarKP=$vkk.tarikh_hantar)
		#set($ulasanKP=$vkk.ulasan)
		#set($tarikh_keputusanKP=$vkk.tarikh_keputusan)		
		#set($id_mmk_keputusan=$vkk.id_mmkkeputusan)
		#end
	
		#foreach($vk in $viewKertas)
			#set($tarikhSemak = $vk.tarikh_semak)
		#end

		#if($edit_keputusan=="no") 
   			#set($disabilityK="class=disabled readonly") 
   			#set($disabilityK1="disabled") 
		#else
			#set($disabilityK="") 	
			#set($disabilityK1="") 
		#end 
		
         <div class="TabbedPanelsContent">
         
         <br/>
         	<input type="hidden" name="hdTarikhSemak" id="hdTarikhSemak" value="$!tarikhSemak">
         	
         	
    		<fieldset>
     		<table width="100%" cellspacing="0" border="0">
     
 		 	<tr>
			    <td width="1%">&nbsp;</td>
  				<td width="25%">No. Rujukan MMK</td>
  				<td width="1%">:</td>			
  				<td width="73%"><input type="text" name="txtRujMMK"  id="txtRujMMK" maxlength="40"  value="$noRujukan" size="20" $disabilityK ></td>
			</tr>
	
			<tr>
				<td valign="top"><font color="red">#if($edit_keputusan=="yes")*#else&nbsp;#end</font></td>
				<td>Tarikh Kelulusan MMK / <br/>Tarikh Mesyuarat Bersidang</td>
				<td>:</td>
				<td><input name="txdTarikhMasuk_edit" onblur="check_date(this)" size="11" id="txdTarikhMasuk_edit" value="$tarikh_mmk" type=text $disabilityK>
				#if($edit_keputusan=="yes") 
  				<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhMasuk_edit',false,'dmy');">$!frmtdate		
  				#end
				</td>		
			</tr>

			<!-- <tr>
                  <td><font color="red">#if($edit_semakan=="yes")*#end</font>Tarikh Hantar</td>
                  <td>:&nbsp;<input type="text" name="txdTarikhHantar" id="txdTarikhHantar" size="11"  onblur="check_date(this)" value="$!tarikhHantarKP" $disabilityS >
                  #if($edit_semakan=="yes") 
                  <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');">$!frmtdate	
               	  #end
               	  </td>
            </tr> -->
			  	
			<!-- <tr>
                  <td width="50%"><font color="red">#if($edit_keputusan=="yes")*#end</font>Tarikh Warta</td>
                  <td width="50%">:&nbsp;<input type="text" name="txdTarikhKeputusan" size="11"  onblur="check_date(this)" value="$!tarikh_keputusanKP" id="txdTarikhKeputusan" $disabilityK >
                  #if($edit_keputusan=="yes") 
                  <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhKeputusan',false,'dmy');">$!frmtdate
                  #end
                  </td>
                </tr> -->       
                
             <tr>
                 <td><font color="red">#if($edit_keputusan=="yes")*#end</font></td>
                 <td>Tarikh Terima Keputusan</td>
                 <td>:</td>
                 <td><input type="text" name="txdTarikhTerima2" onblur="check_date(this)" value="$!tarikh_terimaKP" size="11" id="txdTarikhTerima2" $disabilityK >
                 #if($edit_keputusan=="yes") 
                 <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerima2',false,'dmy');">$!frmtdate
                 #end
                 </td>
             </tr>
          
             <tr>
                 <td><font color="red">#if($edit_keputusan=="yes")*#end</font></td>
                 <td>Keputusan</td>
                 <td>:</td>
                 <td>
                    <input name="sorKeputusan" type="radio" id="sorKeputusan" value="1" $diluluskan $disabilityK1>
                     Diluluskan
                    <input type="radio" name="sorKeputusan" id="sorKeputusan" value="2" $ditolak $disabilityK1>
                     Ditolak
                    <input type="radio" name="sorKeputusan" id="sorKeputusan" value="3" $ditangguh $disabilityK1>
                     Ditangguh
                 </td>
             </tr>
                
                <tr>
                	<td valign="top"></td>
                    <td valign="top">Ulasan</td>
                    <td valign="top">:</td>
                    <td><textarea name="txtUlasan2" rows="5" cols="40%" id="txtUlasan2" $disabilityK >$!ulasanKP</textarea></td>
                </tr>
	 </table>
	 	
	 		#if($edit_keputusan=="yes")
	 		<table width="100%" border="0">
        		<tr><td>&nbsp;</td></tr>
        		<tr>
        			<td>$!perhatian3</td>
        		</tr>
        	</table>
	 		#end
	 		
	 </fieldset>
	 
	 <table width="100%" cellspacing="0" cellpadding="0">  
		   			<tr align="center">
  							<td>
  						#if($view=="yes")
  							#if($edit_keputusan=="no") 				
  								<input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini_keputusan()">
       							<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
       						#end
       							<!-- <input name="cmdCetak" type="button" value="Cetak"> -->
       						
       						#if($edit_keputusan=="yes") 
       							<input name="cmdSimpan" type="button" value="Simpan" onclick="update_item_keputusan('$id_mmk_keputusan')"> 
       							<input name="cmdBatal" type="button" value="Batal" onClick="batal_kemaskini()">
       						#end
       					#end
       					
       						#if($edit_keputusan=="no")
       							<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="KembaliSenaraiKertas('$id_permohonan','$id_status')">	
       						#end
       						</td>	
       	   			</tr>
     </table>
       	   	
         </div>
         
     #end    
       </div>
     </div>   
</fieldset>  
	
<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td ><a href="#" onClick="javascript:cetakBorangA('$!id_permohonan','$noupdate')"><font color="blue">Borang A</font></a></td>
      </tr>
      #if($negeriMMK=="10" || $negeriMMK=="8")
      <tr>  
      	<td><a href="#" onClick="javascript:cetakMB('$!id_permohonan','$!negeriMMK')"><font color="blue">Kertas MB</font></a></td>
      </tr> 
      #end
      <tr>  
      	<td><a href="#" onClick="javascript:cetakMMK('$!id_permohonan','$!id_fail','$!negeriMMK','$!no_fail','$!nama_pengarah','$!id_mmk')"><font color="blue">Kertas Kerja MMK</font></a></td>
      </tr>     
    </table>
</fieldset>	
	
<input type="hidden" name="tarikhLuput">	
	
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>     
<input type="hidden" name="mode">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_status" value="$id_status">
<input type="hidden" name="id_mmk" value="$id_mmk">
<input type="hidden" name="id_mmk_keputusan" value="$id_mmk_keputusan">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>

function popupEtanah(id_fail,id_permohonan,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

//ONLOAD
window.onload = mmkItem,submitForm;

function open_info() 
{

 var width  = 500;
 var height = 500;
 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=front';
 params += ', menubar=no';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
 new_window = open("","title",params);
 new_window.document.open();

new_window.document.write("<html><title>Perhatian</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");
new_window.document.write("<table><tr><td><font color='blue' >Jika anda ingin memasukkan data dengan kaedah </font><font color='red' ><b><i>COPY & PASTE</i></b></font> <font color='blue' >daripada Microsoft Words, Excell ataupun Website, anda hendaklah </font><font color='red' ><b>PASTE pada notepad</b></font> <font color='blue' > terlebih dahulu dan seterusnya perlu memastikan simbol-simbol ataupun <i>special characters</i> seperti <br><br><font color='red' > * '' & $ {} [] || ^^ >></font> <br><br>dan lain-lain seumpamanya digantikan dengan menaip semula simbol-simbol yang sama pada papan kekunci (keyboard) anda. Seterusnya, anda hendaklah memastikan simbol <font color='red' >\"\"</font> tidak disertakan dalam rekod kemasukan maklumat MMK, tetapi anda boleh menggantikannya dengan simbol <font color='red' >''</font> (Jika Perlu). Jika tidak berbuat demikian, kemungkinan besar simbol-simbol pelek (cth : tanda soal terbalik) dan yang tidak sepatutnya akan muncul dipaparan skrin dan cetakan kertas MMK anda.</font></td></tr></table>");
new_window.document.write("</body></html>");
new_window.document.close();



}




function close_window() 
{
new_window.close();
}


function mmkItem(){

	if('$negeriMMK'=="2"){
		//kedah
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),
		textarea_PENDAPAT_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="3"){
		//kelantan
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_PERIHALAP_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),
		textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum',''),
		textarea_ULASANPENGARAH_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="4"){
		//melaka
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_PERIHALPERMOHONAN_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),
		textarea_PERIHALPEMOHON_MAIN('tambahtolak','umum',''),textarea_ANGGARANPAMPASAN_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum',''),
		textarea_LAPORANTEKNIKAL_MAIN('tambahtolak','umum',''),textarea_PANDANGANYB_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="5"){
		//n.sembilan
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_NILAITANAH_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum','')
		,textarea_ULASANPENGARAH_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="6"){
		//pahang
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum',''),
		textarea_WANGPERUNTUKAN_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="7"){
		//ppinang
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_PERIHALPERMOHONAN_MAIN('tambahtolak','umum',''),textarea_ANGGARANKOS_MAIN('tambahtolak','umum',''),
		textarea_LAPORANTEKNIKAL_MAIN('tambahtolak','umum',''),textarea_ULASANPT_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum',''),
		textarea_JAWATANKUASANEGERI_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="8"){
		//perak
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum',''),
		textarea_WANGPERUNTUKAN_MAIN('tambahtolak','umum',''),textarea_LAPORANTEKNIKAL_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum',''),
		textarea_KELULUSAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="9"){
		//perlis
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),
		textarea_LAPORANTEKNIKAL_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="10"){
		//selangor
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="11"){
		//terengganu
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
	}else if('$negeriMMK'=="14"  || '$negeriMMK'=="15" || '$negeriMMK'=="16" ){
		
		//alert("atas");
		
		//kl
		/*
		textarea_BUTIRASAS_MAIN('tambahtolak','umum',''),textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum',''),
		textarea_KEADAANTANAH_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_KEMUDAHANASAS_MAIN('tambahtolak','umum',''),textarea_NILAITANAH_MAIN('tambahtolak','umum',''),
		textarea_WANGPERUNTUKAN_MAIN('tambahtolak','umum',''),textarea_ULASANPT_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
		*/
		//format baru
		textarea_BUTIRASAS1_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS2_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS3_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS4_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS5_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS6_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS7_MAIN('tambahtolak','umum',''),textarea_BUTIRASAS8_MAIN('tambahtolak','umum',''),textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum',''),textarea_PERIHALTANAH_MAIN('tambahtolak','umum',''),textarea_KEMUDAHANASAS_MAIN('tambahtolak','umum',''),textarea_BUTIRTANAH_MAIN('tambahtolak','umum',''),textarea_NILAITANAH_MAIN('tambahtolak','umum',''),textarea_WANGPERUNTUKAN_MAIN('tambahtolak','umum',''),textarea_ULASANPT_MAIN('tambahtolak','umum',''),textarea_PERAKUANPT_MAIN('tambahtolak','umum',''),textarea_KEPUTUSAN_MAIN('tambahtolak','umum','');
		
		//alert("bawah")

		
	}else{
		//selangor
		textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_LAPORANTANAH_MAIN('tambahtolak','umum',''),textarea_SYOR_MAIN('tambahtolak','umum','');
	}
		
}
function cetakMMK(idpermohonan,idfail,negeriMMK,nofail,namaPentadbir,id_mmk) {

	if(negeriMMK=="2"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4Kedah&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek4Kedah?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="3"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4Kelantan&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek4Kelantan?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="4"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4Melaka&flagReport=S";
	}else if(negeriMMK=="5"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4NSembilan&flagReport=S&flagShowTarikhCetak=yes";
	}else if(negeriMMK=="6"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8Pahang&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek4Pahang?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="7"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek8PPinang&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek4PPinang?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="8"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4Perak&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek4Perak?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="9"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4Perlis&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek4Perlis?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="10"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4Selangor&flagReport=S";
	}else if(negeriMMK=="11"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4Terengganu&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek4Terengganu?idFail="+idfail+"&no_fail="+nofail+"&namaPentadbir="+namaPentadbir;
	}else if(negeriMMK=="14" || negeriMMK=="15" || negeriMMK=="16"){
		//var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4WPKL&flagReport=S";
		//var url = "../servlet/ekptg.report.ppt.MMKSek4WPKL?idFail="+idfail+"&no_fail="+nofail+"&namaPegawai="+namaPentadbir;
		//var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4WPKL&flagReport=S";
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4WPKL&flagReport=S&selectNoFail=yes&flagShowTarikhCetak=yes&id_mmk="+id_mmk;
	}else{
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MMKSek4Selangor&flagReport=S";
	}
	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
   
}
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation'; 
		if('$CursorPoint' != ""){
			goTo('$CursorPoint');
		}
	}

}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

function setSelected(tabId) {
    document.${formName}.tabId.value = tabId;	
}
function hantarPengesahan(id_mmk)
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_mmk.value = id_mmk;
	document.${formName}.command.value = "hantarPengesahan"
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.submit();
}
function update_item_semakan(id_mmk)
{

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh semak
	var TS  = document.getElementById("txdTarikhSemak").value;
	var dt1   = parseInt(TS.substring(0,2),10);
   	var mon1  = parseInt(TS.substring(3,5),10);
   	var yr1   = parseInt(TS.substring(6,10),10);
   	var dateSemak = new Date(yr1, mon1, dt1);

  	//tarikh hantar
	var TH  = document.getElementById("txdTarikhHantar").value;
	var dt2   = parseInt(TH.substring(0,2),10);
   	var mon2  = parseInt(TH.substring(3,5),10);
   	var yr2   = parseInt(TH.substring(6,10),10);
   	var dateHantar = new Date(yr2, mon2, dt2);
   	
	var dat1=document.${formName}.txdTarikhSemak
	var dat2=document.${formName}.txdTarikhHantar
	
	if(document.${formName}.txdTarikhSemak.value == ""){
		alert("Sila masukkan \"Tarikh Semak\" terlebih dahulu.");
  		document.${formName}.txdTarikhSemak.focus(); 
		return;
	}
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dateSemak < currentDate){
		alert("Sila pastikan \"Tarikh Semakan\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhSemak.focus();
		return;
	}
	
	else if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;
	}
*/	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
/*	else if(dateHantar < currentDate){
		alert("Sila pastikan \"Tarikh Hantar MMK\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhHantar.focus();
		return;
	}
*/	else if(dateHantar < dateSemak){
		alert("Sila pastikan \"Tarikh Hantar MMK\" tidak kurang dari Tarikh Semakan.");
		document.${formName}.txdTarikhHantar.focus();
		return;
	}
	else if(document.${formName}.socKeputusanSemak.value == ""){
		alert("Sila pilih \"Keputusan Semakan\" terlebih dahulu.");
  		document.${formName}.socKeputusanSemak.focus(); 
		return;
	}
/*	else if(document.${formName}.txtUlasan.value == ""){
		alert("Sila masukkan \"Ulasan\" terlebih dahulu.");
  		document.${formName}.txtUlasan.focus(); 
		return;
	}
*/	else
	{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_mmk.value = id_mmk;
	document.${formName}.command.value = "update_item_semakan"
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.submit();
	}
}
function update_penyediaan(id_mmk)
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_mmk.value = id_mmk;
	document.${formName}.command.value = "update_penyediaan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.submit();
}
function update_item_keputusan(id_mmk_keputusan)
{

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh mmk
	var TM  = document.getElementById("txdTarikhMasuk_edit").value;
	var dt1   = parseInt(TM.substring(0,2),10);
   	var mon1  = parseInt(TM.substring(3,5),10);
   	var yr1   = parseInt(TM.substring(6,10),10);
   	var dateRujMMK = new Date(yr1, mon1, dt1);

  	//tarikh hantar
	//var TH  = document.getElementById("txdTarikhHantar").value;
	//var dt2   = parseInt(TH.substring(0,2),10);
   	//var mon2  = parseInt(TH.substring(3,5),10);
   	//var yr2   = parseInt(TH.substring(6,10),10);
   	//var dateHantar = new Date(yr2, mon2, dt2);

  	//tarikh keputusan/warta
	//var TK  = document.getElementById("txdTarikhKeputusan").value;
	//var dt3   = parseInt(TK.substring(0,2),10);
   	//var mon3  = parseInt(TK.substring(3,5),10);
   	//var yr3   = parseInt(TK.substring(6,10),10);
   	//var dateKeputusan = new Date(yr3, mon3, dt3);

  	//tarikh terima
	var TT  = document.getElementById("txdTarikhTerima2").value;
	var dt4   = parseInt(TT.substring(0,2),10);
   	var mon4  = parseInt(TT.substring(3,5),10);
   	var yr4   = parseInt(TT.substring(6,10),10);
   	var dateTerima = new Date(yr4, mon4, dt4);

  	//tarikh semakan
	var TS  = document.getElementById("hdTarikhSemak").value;
	var dt5   = parseInt(TS.substring(0,2),10);
   	var mon5  = parseInt(TS.substring(3,5),10);
   	var yr5   = parseInt(TS.substring(6,10),10);
   	var dateSemak = new Date(yr5, mon5, dt5);
   	
	var dat1=document.${formName}.txdTarikhMasuk_edit
	//var dat2=document.${formName}.txdTarikhHantar
	//var dat3=document.${formName}.txdTarikhKeputusan
	var dat4=document.${formName}.txdTarikhTerima2
	
/*	var tarK  = document.getElementById("txdTarikhKeputusan").value;

	var day   = parseInt(tarK.substring(0,2),10);
   	var mon  = parseInt(tarK.substring(3,5),10);
   	var yr   = parseInt(tarK.substring(6,10),10)+1;

   	var tarikhLuput = "";
   	
if(tarK!=""){   
	
	if(mon>=10){
		if(day>=10){
			tarikhLuput = day + "/" + mon + "/" + yr;	
		}else{
			tarikhLuput = "0"+ day + "/" + mon + "/" + yr;	
		}
			
	}else{
		if(day>=10){
			tarikhLuput = day + "/0" + mon + "/" + yr;	
		}else{
			tarikhLuput = "0"+ day + "/0" + mon + "/" + yr;	
		}
	}
}*/

/*	if(document.${formName}.txdTarikhHantar.value == ""){
		alert("Sila masukkan \"Tarikh Hantar\" terlebih dahulu.");
  		document.${formName}.txdTarikhHantar.focus(); 
		return;
	}
	if(document.${formName}.txdTarikhKeputusan.value == ""){
		alert("Sila masukkan \"Tarikh Warta\" terlebih dahulu.");
  		document.${formName}.txdTarikhKeputusan.focus(); 
		return;
	}
*/	if(document.${formName}.txdTarikhMasuk_edit.value == ""){
		alert("Sila masukkan \"Tarikh Kelulusan MMK\" terlebih dahulu.");
		document.${formName}.txdTarikhMasuk_edit.focus(); 
		return;
	}
	else 
	if(dateRujMMK < dateSemak){
		alert("Sila pastikan \"Tarikh Kelulusan MMK\" tidak kurang dari Tarikh Semakan MMK.");
		document.${formName}.txdTarikhMasuk_edit.focus();
		return;
	}
	else if(document.${formName}.txdTarikhTerima2.value == ""){
		alert("Sila masukkan \"Tarikh Terima Keputusan\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerima2.focus(); 
		return;
	}
	else 
	if(dateTerima < dateRujMMK){
		alert("Sila pastikan \"Tarikh Terima Keputusan\" tidak kurang dari Tarikh Kelulusan MMK.");
		document.${formName}.txdTarikhTerima2.focus();
		return;
	}	
	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorKeputusan.length;  i++){
	if (${formName}.sorKeputusan[i].checked)
	radioSelected = true;
	}
	if (!radioSelected){
	alert("Sila pilih \"Keputusan\" terlebih dahulu.");
	return (false);
	}
/*	else if(document.${formName}.txtUlasan2.value == ""){
		alert("Sila masukkan \"Ulasan\" terlebih dahulu.");
  		document.${formName}.txtUlasan2.focus(); 
		return;
	}		
	else 
	if(dateRujMMK < currentDate){
		alert("Sila pastikan \"Tarikh Rujukan MMK\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhMasuk_edit.focus();
		return;
	}
*/	
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
*/	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "TabbedPanels1";
		document.${formName}.id_mmk_keputusan.value = id_mmk_keputusan;
		//document.${formName}.tarikhLuput.value = tarikhLuput;
		document.${formName}.command.value = "update_item_keputusan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
		document.${formName}.submit();
	}

}
function simpanPenyediaan(id_permohonan)
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "simpanPenyediaan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.submit();
}
function KembaliSenaraiKertas(id_permohonan,id_status) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	document.${formName}.command.value = "viewSenaraiMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.submit();
}
function deleteKertasMMK(idMMK) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "deleteKertasMMK";
	document.${formName}.id_mmk.value = idMMK;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.submit();
}
function batal_kemaskini() {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "batal_kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai"; 
	document.${formName}.submit();
}
function kemaskini_semakan() {
	document.${formName}.command.value = "kemaskini_semakan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai"; 
	document.${formName}.submit();
}
function kemaskini_keputusan() {
	document.${formName}.command.value = "kemaskini_keputusan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai"; 
	document.${formName}.submit();	
}
function kemaskini_penyediaan() {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "kemaskini_penyediaan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai"; 
	document.${formName}.submit();
}
function PenyedianView(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.command.value = "viewKertasMMK";
	document.${formName}.submit();
}
function SemakanView(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.command.value = "viewKertas_semakan";
	document.${formName}.submit();
}
function KeputusanView(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
	document.${formName}.command.value = "viewKertas_keputusan";
	document.${formName}.submit();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakBorangA(idpermohonan,noupdate) {

	if(noupdate=="no"){
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
		document.${formName}.command.value = "SimpanTarikhBorangA";
		document.${formName}.submit();
	}

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=BorangA&selectNoFail=yes&flagShowTarikhCetak=yes";	
    //var url = "../servlet/ekptg.report.ppt.BorangA?idfail="+idfail+"&namaMukim="+nama2Mukim;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakMB(idpermohonan,negeriMMK) {

	if(negeriMMK=="8"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MBPerak&selectNoFail=yes";
	}else if(negeriMMK=="10"){
		var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=MBSelangor";
	}
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>

<script>
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
</script>

<script>
//Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
		return false
	}
return true
}
</script>