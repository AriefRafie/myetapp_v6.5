<br/>

#set ($chkmode = "")
#set ($checked1 = "")
#set ($checked2 = "")
#set ($checked3 = "")
#set ($checked4 = "")
#set ($checked5 = "")
#set ($checked6 = "")
#set ($checked7 = "")

#set ($checked10 = "")
#set ($checked20 = "")

#if($edit=="no")
	#set ($chkmode = "disabled")
#else 
	#set ($chkmode = "")
#end

	#foreach($List in $senaraiSemakan)
		#set($idSemak=$List.id_senaraisemak)
		
		
		#if ($List.semak1 == "1")
			#set ($checked1 = "checked")
			#set ($checked10 = "checked")
		#end
		
		#if ($List.semak2 == "1")
			#set ($checked2 = "checked")
			#set ($checked20 = "checked")
		#end
		
		#if ($List.semak3 == "1")
			#set ($checked3 = "checked")
		#end	
		
		#if ($List.semak4 == "1")
			#set ($checked4 = "checked")
		#end
		
		#if ($List.semak5 == "1")
			#set ($checked5 = "checked")
			#set ($txtchecked5 = $List.Catatan)
		#end
		
		#if ($List.semak6 == "1")
			#set ($checked6 = "checked")
		#end
		
		#if ($List.semak7 == "1")
			#set ($checked7 = "checked")
		#end
		
	#end

#set($idSuburusan=$dataPermohonan.idSuburusan)
#set($idstatus=$dataPermohonan.id_status)


#if($semak=="yes")

#if($changeby == "no")
	#foreach($data in $dataPermohonan)
		#set($jenisPermohonan=$data.flag_jenispermohonan)
	   	#set($idFail=$data.idFail)
	   	#set($noPermohonan=$data.noPermohonan)
	   	#set($tarikhPohon=$data.tarikh_permohonan)
	   	#set($tujuan=$data.tujuan)
	   	#set($rujKementerian=$data.no_rujukan_surat)
	   	#set($tarikhHendak=$data.tarikh_kehendaki)
	   	#set($tarikhSurat=$data.tarikh_surat)
	   	#set($alamat1=$data.alamat1)
	   	#set($alamat2=$data.alamat2)
	   	#set($alamat3=$data.alamat3)
	   	#set($poskod=$data.poskod)
	   	#set($agensi=$data.nama_agensi)
	   	#set($kementerian=$data.nama_kementerian)
	   	#set($status=$data.status)
	   	#set($idstatus=$data.id_status)
	   	#set($urusan=$data.nama_suburusan)
	   	#set($daerah=$data.daerah)
   		#set($negeri=$data.nama_negeri)
   		#set($idSuburusan=$data.idSuburusan)
   		#set($idSenaraiSemak=$data.id_senaraisemak)
   		#set($namaSuburusan=$data.nama_suburusan)
	#end

	#foreach($data2 in $dataPermohonan2)
   		#set($projekNegeri=$data2.projek_negeri)
	#end
#end


#end

#if($semak=="no")
#set($namaSuburusan="")
#set($noPermohonan = "")
#set($tarikhPohon = "$currentDATE")
#set($tujuan="")
#set($rujKementerian="")
#set($tarikhHendak="")
#set($tarikhSurat="")
#set($alamat1="")
#set($alamat2="")
#set($alamat3="")
#set($poskod="")
#set($agensi="")
#set($kementerian="")
#set($status="")
#set($urusan="")
#set($daerah="")
#set($negeri="")
#set($projekNegeri="")
#end

<fieldset>

<legend><strong>Daftar Permohonan</strong></legend>

#if($semak=="no")

<fieldset>
<legend><strong>&nbsp;Maklumat Asas Permohonan</strong></legend>

    	<table width="100%"  cellpadding="0" border="0">
        
            <tr>
            	<td width="25%">No.Permohonan&nbsp;</td>
            	<td width="75%">:&nbsp;<input type="text" name="no_permohonan" value="" size="38" class="disabled" readonly></td>            	
            </tr>
           
            <tr>
            	<td><font color="red">*</font>Urusan&nbsp;</td>
            	<td>:&nbsp;$!SelectSuburusanUPT</td>
            </tr>
            
            <tr>
            	<td>Jenis Permohonan&nbsp;</td>
            	<td>:&nbsp;PERMOHONAN ONLINE</td>
            </tr>
            
             <tr>
            	<td>Tarikh Permohonan</td>
            	<td>:&nbsp;$!tarikhPohon</td>
            </tr>
            
            <tr>
            	<td>Status Permohonan</td>
                <td>:&nbsp;DAFTAR</td>
            </tr>
            
        </table>  
</fieldset>
#else
<fieldset>
<legend><strong>&nbsp;Maklumat Asas Permohonan</strong></legend>


    	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
        
            <tr>
            	<td width="25%">No.Permohonan&nbsp; </td>
              	<td width="75%">:&nbsp;$noPermohonan</td>
            </tr>
           
            <tr>
            	<td>Urusan&nbsp;</td>
            	<td>:&nbsp;$namaSuburusan</td>
            </tr>
            
            <tr>
            	<td>Jenis Permohonan</td>
            	<td>:&nbsp;$jenisPermohonan</td>
            </tr>
            
            <tr>
            	<td>Tarikh Permohonan</td>
            	<td>:&nbsp;$tarikhPohon</td>
            </tr>
            
            <tr>
            	<td>Status Permohonan</td>
                <td>:&nbsp;$status</td>
            </tr>
            
            
            
        </table> 
      
    </fieldset>
#end

<br/>
    
#if($semak=="no")    

<fieldset id="changeKementerian">
<legend><strong>&nbsp;Maklumat Agensi / KJP</strong></legend>

<br/>
		
		<table width="100%"  cellspacing="1" cellpadding="1" border="0">
        	
        	<tr>
				<td width="25%"><font color="red">*</font>Kementerian</td>
				<td width="75%">:&nbsp;$SelectKementerian</td>
			</tr>
           
           	<tr>
				<td>Nama Agensi </td>
				<td>:&nbsp;$SelectAgensi</td>
			</tr>
           
           <tr>      
                <td valign="top" width="25%"><font color="red">*</font>Alamat&nbsp;</td>
                <td width="75%">:&nbsp;<input type="text" name="alamat1" value="$addAlamat1" id="Alamat1" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" size="50"></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td><font color="white">:</font>&nbsp;<input type="text" name="alamat2" value="$addAlamat2" id="alamat2" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" size="50"></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td><font color="white">:</font>&nbsp;<input type="text" name="alamat3" value="$addAlamat3" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" id="alamat3" size="50"></td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>Poskod</td>
                <td>:&nbsp;<input type="text" name="poskod" size="4" onkeyup="validatePoskod(this,this.value);"  value="$addPoskod" maxlength="5" id="poskod"></td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>Negeri</td>
                <td>:&nbsp;$SelectNegeri</td>
            </tr>
            	
       </table>  
   </fieldset>

<br/>
   
   <fieldset>
   <legend><strong>Maklumat Lengkap Projek</strong></legend>
       <table width="100%"  cellspacing="1" cellpadding="1" border="0">    
         	
         	<tr>
            	<td><font color="red">*</font>Amaun Peruntukan&nbsp;</td>
                <td>:&nbsp;<input name="new_flag_peruntukan" type="radio" value="1" $TEMPcheckedAda >Ada
                	<input name="new_flag_peruntukan" type="radio" value="2" $TEMPcheckedTiada >Tiada</td> 
            </tr>
            
            <tr>
           	  <td><font color="red">*</font>Pengambilan Segera?&nbsp;</td>
                <td>:&nbsp;<input name="new_flag_segera" type="radio" value="1" $TEMPcheckedYa >Ya
           	  &nbsp;&nbsp;<input name="new_flag_segera" type="radio" value="2" $TEMPcheckedTidak >Tidak</td>
             </tr> 
         	
         	<tr>
           	   <td><font color="red">*</font>Negeri&nbsp;</td>
                <td>:&nbsp;$project_negeri</td>
             </tr>
          
             <tr>
             	<td><font color="red">*</font>Daerah&nbsp;</td>
                <td>:&nbsp;$SelectDaerah</td>
             </tr>
         	
         	<tr>
            	<td><font color="red">*</font>Bandar/Pekan/Mukim&nbsp;</td>
                <td>:&nbsp;$SelectBandar</td>
            </tr>
         	
         	  <tr>
           	   	<td valign="top"><font color="red">*</font>Tujuan&nbsp;</td>
                <td>&nbsp;&nbsp;<textarea name="tujuan"  id="tujuan"  cols="40%" maxlength="400" rows="4" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();">$addTujuan</textarea></td>
              </tr>
         	
         	 <tr>
           	    <td width="25%">No. Ruj Surat KJP&nbsp;</td>
                <td width="75%">:&nbsp;<input type="text" name="rujukan_kementerian" id="rujukan_kementerian" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" maxlength="50" value="$addRujukan_kementerian" size="23" #if($semak=="yes")readonly #end ></td>
            </tr>
         	
         	<tr>
            	<td>Tarikh Surat KJP&nbsp;</td>
                <td>:&nbsp;<input name="tarikh_surat" value="$addTarikhSurat" onblur="check_date(this)" size="11" id="tarikh_surat" type="text">
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_surat',false,'dmy');"></td>
            </tr>
         	
            <tr>
            	<td>Tarikh Dikehendaki&nbsp;</td>
                <td>:&nbsp;<input name="tarikh_kehendaki" onblur="check_date(this)" value="$addTarikhHendak" size="11" id="tarikh_kehendaki" type="text">
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_kehendaki',false,'dmy');"></td>
            </tr>
         	
        </table>	
    </fieldset>
#end
    
   
#if($semak=="yes")  
   
   #if($edit=="no")
   		#set($disabiliti = "readonly")
   		#set($disabilitix = "class=disabled")
   		#set($disabiliti1 = "disabled")
   #else
   		#set($disabiliti = "")
   		#set($disabilitix = "")
   		#set($disabiliti1 = "")
   #end
<fieldset id="changeKementerian">
<legend><strong>&nbsp;Maklumat Agensi / KJP</strong></legend>

<br/>
		
		<table width="100%"  cellspacing="1" cellpadding="1" border="0">
        	
        	<tr>
				<td width="25%"><font color="red">#if($edit=="yes")*#end</font>Kementerian</td> 
				<td width="75%">:&nbsp;$selectKementerian</td>
			</tr>
           
           <tr>
				<td>Nama Agensi</td>
				<td>:&nbsp;$selectAgensi</td>
			</tr>
           
           	<tr>      
                <td valign="top"><font color="red">#if($edit=="yes")*#end</font>Alamat&nbsp;</td>
                <td>:&nbsp;<input type="text" name="edit_alamat1" value="$alamat1" maxlength="80" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" id="edit_alamat1" size="50" $disabiliti $disabilitix /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td><font color="white">:</font>&nbsp;<input type="text" name="edit_alamat2" maxlength="80" value="$alamat2" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" id="edit_alamat2" size="50" $disabiliti $disabilitix /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td><font color="white">:</font>&nbsp;<input type="text" name="edit_alamat3" maxlength="80" value="$alamat3" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" id="edit_alamat3" size="50" $disabiliti $disabilitix /></td>
            </tr>
            
             <tr>
            	<td><font color="red">#if($edit=="yes")*#end</font>Poskod&nbsp;</td>
                <td>:&nbsp;<input type="text" name="edit_poskod" value="$poskod" size="4" onkeyup="validatePoskod(this,this.value);" maxlength="5" id="edit_poskod" $disabiliti $disabilitix /></td>
            </tr>
            
            <tr>
            	<td><font color="red">#if($edit=="yes")*#end</font>Negeri</td>
                <td>:&nbsp;$selectNegeri</td>
            </tr>
            
          </table>
   </fieldset>
          
<br/>
   
   <fieldset>
   <legend><strong>Maklumat Lengkap Projek</strong></legend>         
          <table width="100%"  cellspacing="1" cellpadding="1" border="0">   
          
           <tr>
            	<td width="25%"><font color="red">#if($edit=="yes")*#end</font>Amaun Peruntukan&nbsp;</td>
                <td width="75%">:&nbsp;<input name="flag_peruntukan" type="radio" value="1" $checkedAda $disabiliti1 />Ada
                	<input name="flag_peruntukan" type="radio" value="2" $checkedTiada $disabiliti1 />Tiada</td> 
            </tr>
            
            <tr>
           	  <td><font color="red">#if($edit=="yes")*#end</font>Pengambilan Segera?&nbsp;</td>
                <td>:&nbsp;<input name="flag_segera" type="radio" value="1" $checkedYa $disabiliti1 />Ya
           	  &nbsp;&nbsp;<input name="flag_segera" type="radio" value="2" $checkedTidak $disabiliti1 />Tidak</td>
             </tr> 
          
           	 <tr>
           	   <td><font color="red">#if($edit=="yes")*#end</font>Negeri&nbsp;</td>
                <td>:&nbsp;$selectProjekNegeri</td>
             </tr>
        
           	 <tr>
             	<td><font color="red">#if($edit=="yes")*#end</font>Daerah&nbsp;</td>
                <td>:&nbsp;$selectDaerah</td>
             </tr>	 
             
             <tr>
            	<td><font color="red">#if($edit=="yes")*#end</font>Bandar/Pekan/Mukim&nbsp;</td>
                <td>:&nbsp;$selectBandar</td>
            </tr>
             
             <tr>
           	   <td valign="top"><font color="red">#if($edit=="yes")*#end</font>Tujuan&nbsp;</td>
               <td valign="top"><font color="white">:</font>&nbsp;<textarea name="edit_tujuan" maxlength="400" id="edit_tujuan" cols="45%" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" rows="5" $disabiliti $disabilitix >$tujuan</textarea></td>
             </tr>
        
         	
            <tr>
           	  <td>No. Ruj Surat KJP&nbsp;</td>
              <td>:&nbsp;<input type="text" name="edit_rujukan_kementerian" id="rujukan_kementerian" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" maxlength="50" value="$rujKementerian" size="30" $disabiliti $disabilitix /></td>
            </tr>
         	
         	<tr>
            	<td>Tarikh Surat KJP&nbsp;</td>
                <td>:&nbsp;<input name="edit_tarikh_surat" value="$tarikhSurat" size="11" id="edit_tarikh_surat" onblur="check_date(this)" type="text" $disabiliti $disabilitix >
       		 		#if($edit=="yes")
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('edit_tarikh_surat',false,'dmy');"></td>
            		#end
            </tr>
         	
            <tr>
            	<td>Tarikh Dikehendaki&nbsp;</td>
                <td>:&nbsp;<input name="edit_tarikh_kehendaki" value="$tarikhHendak" onblur="check_date(this)" id="tarikh_kehendaki" size="11" type="text" $disabiliti $disabilitix >
       		 		#if($edit=="yes")
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('edit_tarikh_kehendaki',false,'dmy');">
                	#end
                </td>
            </tr>
           
        </table>	
      
</fieldset>
#end   
   
   
<br/>    

#if($semak=="yes")    
    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" tabindex="0">Maklumat Tanah Terlibat</li>
        <li class="TabbedPanelsTab" tabindex="0">Senarai Dokumen</li>
       <!-- #if($currentSuburusan!="53")
        <li class="TabbedPanelsTab" tabindex="0">Senarai Semakan</li>
        #end --> 
      </ul>
      
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
        	<fieldset>
        	<legend><strong>Maklumat Tanah Terlibat</strong></legend>


                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                    
                    #if($currentStatus=="113")
                    <tr>
                    	<td colspan="2" scope="row"><input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambah();"></td>
    				</tr>
                    #end
                    
        #if($id_suburusan=="51")
        
        			<tr class="table_header">
                    	<td width="4%" align="center"><b>No</b></td>
                    	<td width="96%"><b>&nbsp;Bandar/Pekan/Mukim</b></td>
                    </tr>
        
        			#if($saiz_listTanah!=0)
                    	#foreach($listTanah in $listMaklumatTanah)
                    		#set( $i = $velocityCount )
         					#if ( ($i % 2) != 1 )
              		 			#set( $row = "row2" )
         					#else
               					#set( $row = "row1" )
         					#end
         				
                    		#set($idMaklumat=$listTanah.id_hakmilik)
                    <tr>
                    	<td class="$row" align="center">$listTanah.bil</td>
						<td class="$row"><a href="javascript:edit_maklumat('$idMaklumat')"><font color="blue">$listTanah.nama_mukim</font></a></td>
                    </tr>
                    	#end
                  #else
                  
                    <tr>
                    	<td colspan="5">Tiada rekod</td>
                    </tr>
                  #end
        
        
        #else      
         
                    <tr class="table_header">
                    	<td width="4%" align="center"><b>No</b></td>
                    	<td><b>No.Hakmilik</b></td>
                        <td><b>No.Lot / No.PT</b></td>
                        <td><b>Bandar/Pekan/Mukim</b></td>
                        <td><b>Luas Lot</b></td>
                    </tr>
                    
   					
                  #if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                    	#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              		 		#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
         				
                    #set($idMaklumat=$listTanah.id_hakmilik)
                    <tr>
                    	<td class="$row" align="center">$listTanah.bil</td>
                    	<td class="$row"><a href="javascript:edit_maklumat('$idMaklumat')"><font color="blue">$listTanah.no_hakmilik</font></a></td>
                        <td class="$row">$listTanah.kod_lot $listTanah.no_lot</td>
                        <td class="$row">$listTanah.nama_mukim</td>
                        <td class="$row">$listTanah.luas_lot&nbsp;$listTanah.unitluaslot</td>  
                    </tr>
                    #end
                  #else
                    <tr>
                    	<td colspan="5">Tiada rekod</td>
                    </tr>
                  #end
                  
       #end            
       
        
                </table>        	
          </fieldset>	
        </div>
        
        <div class="TabbedPanelsContent">
        	<fieldset>
        		<legend><strong>Senarai Dokumen yang Disertakan</strong></legend>

				<table width="100%"  cellpadding="0" border="0">
                
               		#if($currentStatus=="113")
                	<tr>
                		<td colspan="4"><input type="button" name="cmdTambah2" value ="Tambah" onClick="javascript:tambahDokumen('$id_permohonan');"></td>
                    </tr>
                    #end
                    
                </table>
                 
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                    
                    <tr class="table_header">
                    	<td width="3%" align="center"><b>No</b></td>
                    	<td width="25%"><b>Nama Dokumen</b></td>
                        <td width="37%"><b>Keterangan</b></td>
                        <td width="35%"><b>Dokumen Sokongan</b></td>
                    </tr>
                
                
                  
                
          #if($listD_size!=0)
          
             #foreach($listD in $listDokumen)  
              #set($idDokumen=$listD.id_Dokumen)    
                   
                    #set( $i = $velocityCount )       	
         		#if ( ($i % 2) != 1 )
              		#set( $row = "row2" )
         		#else
               		#set( $row = "row1" )
         		#end
                   
                
                    <tr>
                    	<td class="$row" align="center">$!listD.bil</td>
                    	<td class="$row">$!listD.tajuk</td>
                        <td class="$row">$!listD.keterangan</td>
                       	<td class="$row"><a href="javascript:papar_Lampiran('$idDokumen')"><font color="blue">$!listD.nama_Fail</font></a></td>	
                    </tr>
              #end  
              		 
           #else
                	<tr>
                    	<td colspan="4">Tiada rekod</td>
                    </tr>
           #end
                    
                </table>        	
            </fieldset>	
        </div>
 
<!-- #if($currentSuburusan!="53")	
        <div class="TabbedPanelsContent">
    	

<fieldset>
<legend><strong>Senarai Semakan</strong></legend>
<br/>
  #if($currentSuburusan=="51")	
    <table width="100%" cellpadding="0" cellspacing="0">
   		<tr>
    		<td width="5%">&nbsp;</td>
    		<td align="justify" width="95%"><input type="checkbox" value="1" $chkmode name="cbsemaks10" id="cbsemaks10" $checked10 >
      			1. Pelan Pengambilan Tanah yang lengkap.</td>
  		</tr>
        
  		<tr>
    		<td>&nbsp;</td>
    		<td><input type="checkbox" name="cbsemaks20" $chkmode value="1" id="cbsemaks20" $checked20 >
      			2. Pengesahan peruntukan yang mencukupi untuk membiayai kos pampasan 
                   kerosakan akibat kerja-kerja ukur dan kajian tanah.</td>
 		</tr>
        
  </table>
  #end
<br/>  
  #if($currentSuburusan=="52")	
  	<table width="100%" cellpadding="0" cellspacing="0">
    	<tr>
            <td width="5%">&nbsp;</td>
    		<td width="95%"><input type="checkbox" name="cbsemaks1"  $chkmode value="1" id="cbsemaks1" $checked1>
     						 1. Pelan Pengambilan Tanah yang lengkap.</td>
  		</tr>
        
  		<tr>
    		<td>&nbsp;</td>
    		<td><input type="checkbox" name="cbsemaks2" $chkmode value="1" id="cbsemaks2" $checked2 >
     		 	2. Sijil Carian Rasmi/ Persendirian yang terkini.</td>
  		</tr>
  
  		<tr>
    		<td>&nbsp;</td>
    		<td><input type="checkbox" name="cbsemaks3" $chkmode value="1" id="cbsemaks3" $checked3 >
      			3. Ulasan dari Jabatan-Jabatan Teknikal. </td>
  		</tr>
 
  		<tr>
    		<td>&nbsp;</td>
    		<td><input type="checkbox" name="cbsemaks4" $chkmode value="1" id="cbsemaks4" $checked4 >
      			4. Ulasan dari Jabatan Alam Sekitar.</td>
  		</tr>
  
  		<tr>
    		<td>&nbsp;</td>
    		<td><input type="checkbox" name="cbsemaks5" $chkmode value="1" id="cbsemaks5" $checked5 >
      			5. Persetujuan Jawatankuasa Pembangunan Daerah atau Jawatankuasa seumpamanya. </td>
  		</tr>
  
  		<tr>
    		<td>&nbsp;</td>
    		<td><input type="checkbox" name="cbsemaks6" $chkmode value="1" id="cbsemaks6" $checked6 >
      			6. Pengesahan peruntukan yang mencukupi termasuk kos penandaan dan lain-lain kos.</td>
  		</tr>
  
  		<tr>
    		<td>&nbsp;</td>
   			<td><input type="checkbox" name="cbsemaks7" $chkmode value="1" id="cbsemaks7" $checked7 >
      			7. Surat Perakuan segera (Borang I)</td>
  		</tr>
    </table>
  	#end
    </fieldset>
    
        </div>
      	#end -->      	
  
      	
  </div> 
  </div>    
 
#end 

      
    <table width="100%"  cellpadding="0" border="0">
    	<tr align="center">
        	<td>
        	#if($semak=="yes")
        	#if($currentStatus=="113")	
        		#if($edit=="yes")
        		<input name="cmdBatal" type="button" value="Batal" onClick="batal_update('$id_permohonan')">
        		<input name="cmdUpdate" type="button" value="Simpan" onClick="update_item('$id_permohonan')">
        		#end
       		
        	  #if($edit=="no")
        		<input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini()">
          		#if($saiz_listTanah!=0)
          		<input name="cmdHantar" type="button" value="Hantar" onClick="hantar('$id_permohonan')">
          		#end
          	  #end
          	#end	
          	#end
          		
          	#if($semak=="no")
                <input name="cmdSimpan" type="button" value="Simpan" onKeyPress="add_item()" onClick="add_item()">      						
                <input name="cmdBatal" type="button" value="Kembali" onClick="batal()">
            #end
                
            #if($semak=="yes")
       			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
       			<!-- <input name="cmdCetak" type="button" value="Cetak"> -->
       		#end
            </td>
        </tr>
    </table>
  
</fieldset>  
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_dokumen" value="$idDokumen">
<input type="hidden" name="id_hakmilik" value="$idMaklumat">
<input type="hidden" name="id_fail" value="$idFail">
<input type="hidden" name="id_semak" value="$idSemak">
<input type="hidden" name="id_status" value="$idstatus">
<input type="hidden" name="suburusan" value="$idSuburusan">
<input type="hidden" name="id_senaraiSemak" value="$idSenaraiSemak">
<input type="hidden" name="id_suburusan" value="$id_suburusan">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$ScreenLocation">
<input type="hidden" name="CursorPoint" value="$CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
window.onload = submitForm;

function edit_maklumat(id_hakmilik) {
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "edit_maklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
	document.${formName}.submit();
}
function tambahDokumen(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "tambahDokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
}
function get_doc(id_dokumen) {
	document.${formName}.id_dokumen.value = id_dokumen;
	document.${formName}.command.value = "getDOC";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
}
function tambah() {
	document.${formName}.command.value = "tambah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
}
function kemaskini() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "editKementerian";
	
	document.${formName}.command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
}
function add_item()
{
	
	var radioSelected2 = false;
	for (j = 0;  j < ${formName}.new_flag_segera.length;  j++){
	if (${formName}.new_flag_segera[j].checked)
	radioSelected2 = true;
	}
	var radioSelected = false;
	for (i = 0;  i < ${formName}.new_flag_peruntukan.length;  i++){
	if (${formName}.new_flag_peruntukan[i].checked)
	radioSelected = true;
	}
	

	if(document.${formName}.socSuburusan.value == ""){
		alert("Sila pilih \"Urusan\" terlebih dahulu.");
  		document.${formName}.socSuburusan.focus(); 
		return;
	}
	else if(document.${formName}.kementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.kementerian.focus(); 
		return;
	}
	else if (!radioSelected){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
		return;
	}
	else if (!radioSelected2){
		alert("Sila pilih \"Pengambilan segera\" terlebih dahulu.");
		return;
	}
	else if(document.${formName}.projek_negeri.value == ""){
		alert("Sila pilih \"Negeri maklumat projek\" terlebih dahulu.");
  		document.${formName}.projek_negeri.focus(); 
		return;
	}
	else if(document.${formName}.daerah.value == ""){
		alert("Sila pilih \"Daerah\" terlebih dahulu.");
  		document.${formName}.daerah.focus(); 
		return;
	}
	else if(document.${formName}.tujuan.value == ""){
		alert("Sila masukkan \"tujuan\" terlebih dahulu.");
  		document.${formName}.tujuan.focus(); 
		return;
	}
	else if(document.${formName}.alamat1.value == ""){
		alert("Sila masukkan \"alamat\" terlebih dahulu.");
  		document.${formName}.alamat1.focus(); 
		return;
	}
	else if(document.${formName}.negeri.value == ""){
		alert("Sila pilih \" Negeri \" terlebih dahulu.");
  		document.${formName}.negeri.focus(); 
		return;
	}
	else if(document.${formName}.poskod.value == ""){
		alert("Sila masukkan \"poskod\" terlebih dahulu.");
  		document.${formName}.poskod.focus(); 
		return;
	}
	else if (document.${formName}.poskod.value != "" && document.${formName}.poskod.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat perayu dengan selengkapnya");
		document.${formName}.poskod.focus();
	}
	else if(document.${formName}.bandar.value == ""){
		alert("Sila pilih \" Bandar/Pekan/Mukim \" terlebih dahulu.");
  		document.${formName}.bandar.focus(); 
		return;
	} 
 	else
	{
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "simpan";
		document.${formName}.method = "POST";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
		alert("Sila klik permohonan ini semula untuk dihantar ke Seksyen Pengambilan Tanah");
		document.${formName}.submit();
	}	
}
function batal_update(id_permohonan)
{
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
	document.${formName}.submit();
}
function batal() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
	document.${formName}.reset();
	document.${formName}.command.value = "batal";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
	document.${formName}.command.value = "batal";
	document.${formName}.submit();
}
function update_item(id_permohonan) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	
	if (document.${formName}.edit_poskod.value != "" && document.${formName}.edit_poskod.value.length < 5) {
	alert ("Sila masukkan 5 digit poskod");
	document.${formName}.edit_poskod.focus();
	return;
	} 

	if(document.${formName}.editKementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.editKementerian.focus(); 
		return;
	}
	if(document.${formName}.flag_peruntukan.value == ""){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
  		document.${formName}.flag_peruntukan.focus(); 
		return;
	}
	if(document.${formName}.flag_segera.value == ""){
		alert("Sila pilih \"Pengambilan segera\" terlebih dahulu.");
  		document.${formName}.flag_segera.focus(); 
		return;
	}

	if(document.${formName}.editProjekNegeri.value == ""){
		alert("Sila pilih \"Negeri maklumat projek \" terlebih dahulu.");
  		document.${formName}.editProjekNegeri.focus(); 
		return;
	} 
	if(document.${formName}.editDaerah.value == ""){
		alert("Sila pilih \"Daerah\" terlebih dahulu.");
  		document.${formName}.editDaerah.focus(); 
		return;
	}
	if(document.${formName}.edit_tujuan.value == ""){
		alert("Sila masukkan \"tujuan\" terlebih dahulu.");
  		document.${formName}.edit_tujuan.focus(); 
		return;
	}
	if(document.${formName}.edit_alamat1.value == ""){
		alert("Sila masukkan \"alamat\" terlebih dahulu.");
  		document.${formName}.edit_alamat1.focus(); 
		return;
	}
	if(document.${formName}.editNegeri.value == ""){
		alert("Sila pilih \" Negeri \" terlebih dahulu.");
  		document.${formName}.editNegeri.focus(); 
		return;
	}
	if(document.${formName}.edit_poskod.value == ""){
		alert("Sila masukkan \"poskod\" terlebih dahulu.");
  		document.${formName}.edit_poskod.focus(); 
		return;
	} 
	if(document.${formName}.editBandar.value == ""){
		alert("Sila pilih \" Bandar/Pekan/Mukim \" terlebih dahulu.");
  		document.${formName}.editBandar.focus(); 
		return;
	} 
	else
	{  
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.command.value = "update";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
		document.${formName}.submit();	
 	}
}
function hantar(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();	
}
function validatePoskod(elmnt,content) {
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
function doChangeidNegeri() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "daerah";
	
	document.${formName}.command.value = "doChangeidNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
	
	}
function doChangeidNegeri2() {
	document.${formName}.command.value = "doChangeidNegeri2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
		
	}	
function doChangeidKementerian() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "agensi";
	
	document.${formName}.command.value = "doChangeidKementerian";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
	
	}
function doChangeidKementerianUPDATE() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "editAgensi";
	
	document.${formName}.command.value = "doChangeidKementerianUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
	
	}
function doChangeidNegeriUPDATE() {
	document.${formName}.command.value = "doChangeidNegeriUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
	
	}
function doChangeidProjekNegeriUPDATE() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "editDaerah";
	
	document.${formName}.command.value = "doChangeidProjekNegeriUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
	
	}
function doChangeidMukim() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "bandar";
	
	document.${formName}.command.value = "doChangeidMukim";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
	
	}
function doChangeAlamatAgensi() {
	document.${formName}.command.value = "doChangeAlamatAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
	
	}
function doChangeidMukimUPDATE() {
	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "editBandar";
	
	document.${formName}.command.value = "doChangeidMukimUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
	
	}
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=500,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>


<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
//-->
</script>
