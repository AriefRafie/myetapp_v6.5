#parse("app/ppt/frmLabelSet.jsp")
#parse("app/ppt/HadAksesOnlinePPT.jsp")

<!-- Portal Role -->
#set($portal_role = "${session.getAttribute('_portal_role')}")

#set($frmtdate = "&nbsp;<i><font color=blue style='font-size:10px'>dd/mm/yyyy</font></i>")

#if ($showPopupHantar=="yes")
<div class="success">
Permohonan ini telah berjaya dihantar.&nbsp;&nbsp;<u><a href="javascript:cetakPengesahan()">Cetak Pengesahan Permohonan</a></u>
</div>
#end

<!-- #if ($showSave=="yes")
<div class="success">
Maklumat Permohonan telah berjaya disimpan.
</div>
#end -->
#set($perhatianOnline= "<i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style=font-size:10px>Bagi negeri Johor, Sabah dan Sarawak, permohonan pengambilan tidak melalui Sistem MyeTaPP</font></i>")


<br/>

<fieldset id="top">
<legend><strong>Daftar Permohonan</strong></legend>

	#if($mode=="new")
	
	<fieldset>
	<legend><strong>Maklumat Asas Permohonan</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
			<tr align="right">
				<td>PERMOHONAN ONLINE</td>
            </tr>
		</table>
		
    	<table width="100%"  cellpadding="0" border="0">
        
        	<tr>
            	<td width="1%">&nbsp;</td>
            	<td width="25%">Bil. Permohonan</td>
            	<td width="1%">:</td>
            	<td width="73%"><input type="text" name="lblNoPermohonan" value="" size="15" class="disabled" readonly></td>            	
            </tr>
           
           <!-- <tr>
           		<td>&nbsp;</td>
            	<td>No. Permohonan Online</td>
            	<td>:</td>
            	<td><input type="text" name="lblNoOnline" value="" size="15" class="disabled" readonly></td>
            </tr> -->
           	
           
           <!-- DISABLED UNTUK USER ONLINE SHJ 
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTG</td>
            	<td>:</td>
                <td><input type="text" name="txtNoRujukanPTG" id="txtNoRujukanPTG" value="$!txtNoRujukanPTG" maxlength="30" size="37"  ></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTD</td>
            	<td>:</td>
                <td><input type="text" name="txtNoRujukanPTD" value="$!txtNoRujukanPTD" maxlength="30" size="37"  ></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan UPT</td>
            	<td>:</td>
                <td><input type="text" name="txtNoRujukanUPT" value="$!txtNoRujukanUPT" maxlength="30" size="37"  ></td>
            </tr>
           
           	-->
           	
            <tr>
            	<td><font color="red">*</font></td>
            	<td>Urusan</td>
            	<td>:</td>
            	<td><select name="sorUrusan" style="width:auto" onchange="doChangeUrusan()">
      			
      			#if($sorUrusan=="51")
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
				<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
				<option value="">SILA PILIH</option>
      			#elseif($sorUrusan=="52")
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
				<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
				<option value="">SILA PILIH</option>
      			#elseif($sorUrusan=="53")
      			<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
      			<option value="">SILA PILIH</option>
      			#else
      			<option value="">SILA PILIH</option>
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
				<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
      			#end
  
				</select></td>
            </tr>
            
            <!-- <tr>
            	<td><font color="red">*</font></td>
            	<td>Tarikh Permohonan</td>
            	<td>:</td>
                <td><input name="txdTarikhPermohonan" value="$!dateToday" size="11" id="txdTarikhPermohonan" type="text" onblur="check_date(this)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">$!frmtdate</td>
            </tr> -->
            
            <!-- <tr>
            	<td><font color="red">*</font></td>
            	<td>Tarikh Permohonan KJP</td>
            	<td>:</td>
                <td><input name="tarikh_permohonan_kjp" value="$!dateToday" size="11" id="tarikh_permohonan_kjp" type="text" onblur="check_date(this)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_permohonan_kjp',false,'dmy');">$!frmtdate</td>
            </tr> -->
     
        </table> 
      
    </fieldset>

<br/>
     
	<fieldset id="middle">
	<legend><strong>Maklumat Agensi / KJP</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
        	
        	<tr>
        		<td width="1%">&nbsp;</td>
				<td width="25%">Kementerian</td>
				<td width="1%">:</td>
				<td width="73%">$!selectKementerian</td>
			</tr>
           
           	<tr>
           		<td>&nbsp;</td>
				<td>Nama Agensi</td>
				<td>:</td>
				<td>$!selectAgensi</td>
			</tr>
           
           <tr>      
           		<td>&nbsp;</td>
                <td>Alamat</td>
                <td>:</td>
                <td><input type="text" class="disabled" readonly name="txtAlamat1" value="$!txtAlamat1" id="txtAlamat1" maxlength="80"   size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="3">&nbsp;</td>
                <td><input class="disabled" readonly type="text" name="txtAlamat2" value="$!txtAlamat2" id="txtAlamat2" maxlength="80"   size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="3">&nbsp;</td>
                <td><input class="disabled" readonly type="text" name="txtAlamat3" value="$!txtAlamat3" id="txtAlamat3" maxlength="80"   size="60" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>Poskod</td>
            	<td>:</td>
                <td><input type="text" class="disabled" readonly name="txtPoskod" size="5" onkeyup="validateNumber(this,this.value);"  value="$!txtPoskod" maxlength="5" id="txtPoskod" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>Negeri</td>
            	<td>:</td>
                <td>$!selectNegeri</td>
            </tr>
            	
       </table>  
   </fieldset>

<br/>
   
   <fieldset id="bottom">
   <legend><strong>Maklumat Lengkap Projek</strong></legend>
       
		<table width="100%" border="0">
      
         	#if($sorFlagPeruntukan=="1")
         		#set($checkPA="checked")
         		#set($checkPB="")
         	#elseif($sorFlagPeruntukan=="2")
         		#set($checkPA="")
         		#set($checkPB="checked")
         	#else
         		#set($checkPA="")
         		#set($checkPB="")
         	#end
         	
         	<tr>
         		<td width="1%"><font color="red">*</font></td>
            	<td width="25%">Peruntukan Projek</td>
            	<td width="1%">:</td>
                <td width="73%">
                	<input name="sorFlagPeruntukan" $checkPA type="radio" value="1" />Ada
                	<input name="sorFlagPeruntukan" $checkPB type="radio" value="2" />Tiada
          			<font color=red style=font-size:10px></font><td>
            </tr>
            
         	#if($sorFlagPeruntukan=="1")
         		#set($checkPA="checked")
         		#set($checkPB="")
         	#elseif($sorFlagPeruntukan=="2")
         		#set($checkPA="")
         		#set($checkPB="checked")
         	#else
         		#set($checkPA="")
         		#set($checkPB="")
         	#end
         	
         	
         #if($showSegera=="yes")
            #if($sorFlagSegera=="1")
         		#set($checkSA="checked")
         		#set($checkSB="")
         	#elseif($sorFlagSegera=="2")
         		#set($checkSA="")
         		#set($checkSB="checked")
         	#else
         		#set($checkSA="")
         		#set($checkSB="")
         	#end
         	#end
            <tr>
            	<td><font color="red">*</font></td>
           	  	<td>Borang I?</td>
           	  	<td>:</td>
                <td>
                	<input name="sorFlagSegera" $checkSA type="radio" value="1" onclick="javascript:hideLabel(1)" />Ya
           	  		&nbsp;&nbsp;<input name="sorFlagSegera" $checkSB type="radio" checked="checked" value="2" onclick="javascript:hideLabel(2)"/>Tidak
           	  		<i id="label1" hidden="true"><font color=red style=font-size:10px>Perhatian:</font><font>Sila muatnaik imej surat iringan Borang I di tab Muatnaik Dokumen</font></i>
           	  	</td>	
           		 <td></td>
            </tr> 
         	<tr>
         		<td><font color="red">*</font></td>
           	   	<td>Negeri</td>
           	   	<td>:</td>
                <td>$!selectNegeriProjek</td>
            </tr>
          
            <tr>
            	<td><font color="red">*</font></td>
             	#if($showJajahan=="yes")
             		<td>Jajahan</td>
             	#else
             		<td>Daerah</td>
             	#end
             	<td>:</td>
                <td>$!selectDaerah</td>
            </tr>
         	
         	<tr>
         	  	<td valign="top"><font color="red">*</font></td>
           	   	<td valign="top">Nama Projek</td>
           	   	<td valign="top">:</td>
                <td><textarea name="txtTujuan" id="txtTujuan" cols="40%" rows="4" value="$!txtTujuan">Permohonan Pengambilan Tanah Bagi Projek</textarea></td>
            </tr>
            
         #if($showSegera=="yes")  
            #set($checkJPA = "")
            #set($checkJPB = "")
            
            #if($sorJenisProjek=="1")
            	#set($checkJPA = "checked")
            #elseif($sorJenisProjek=="2")
            	#set($checkJPB = "checked")
            #end
            
            <tr>
            	<td colspan="3">&nbsp;</td>
            	<td>
            		<input name="sorJenisProjek" $checkJPA type="radio" value="1" />Jajaran
            		<input name="sorJenisProjek" $checkJPB type="radio" value="2" />Tapak
            	</td>
            </tr>
         	
         	<tr><td colspan="4">&nbsp;</td></tr>
         #end 		
         
         	<tr>
         		<td><font color="red">*</font></td>
           	    <td>Jumlah Hakmilik</td>
           	    <td>:</td>
           	    <td><input type="text" name="txtJumHM" id="txtJumHM" maxlength="4" value="$!txtJumHM" size="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
         	</tr>
         	
         	<tr>
         	 	<td>&nbsp;</td>
           	    <td>No. Ruj Surat KJP</td>
           	    <td>:</td>
                <td><input type="text" name="txtRujukanKementerian" id="txtRujukanKementerian"   maxlength="50" value="$!txtRujukanKementerian" size="35" /></td>
            </tr>
         	
         	<tr>
         		<td>&nbsp;</td>
            	<td>Tarikh Surat KJP</td>
            	<td>:</td>
                <td><input name="txdTarikhSurat" id="txdTarikhSurat" value="$!txdTarikhSurat" size="11" type="text" onblur="check_date(this)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">$!frmtdate</td>
            </tr>
         	
            <tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Dikehendaki</td>
            	<td>:</td>
                <td><input name="txdTarikhKehendaki" id="txdTarikhKehendaki" value="$!txdTarikhKehendaki" size="11" type="text" onblur="check_date(this)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhKehendaki',false,'dmy');">$!frmtdate</td>
            </tr>
         	 <tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Pengesahan Pengarah Negeri</td>
            	<td>:</td>
                <td><input class="disabled" readonly name="txdTarikhPengesahan" id="txdTarikhPengesahan" value="$!txdTarikhPengesahan" size="11" type="text" onblur="check_date(this)" />
<!--        		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPengesahan',false,'dmy');">$!frmtdate --> </td>
            </tr>
        </table>	
        
        <table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian3
        		<br>$!perhatianOnline
        		</td>
        	</tr>
        </table>
  
    </fieldset>

	#end

	#if($mode=="view")
	
		#set($flagSahkan = "")
		
		#if($onchange=="no")
			#foreach($data in $dataPermohonan)
			#set($lblNoPermohonan=$data.noPermohonan)
			#set($lblNoOnline=$data.no_permohonan_online)
			#set($txtNoRujukanPTG=$data.no_rujukan_ptg)
			#set($txtNoRujukanPTD=$data.no_rujukan_ptd)
			#set($txtNoRujukanUPT=$data.no_rujukan_upt)
			#set($txdTarikhPermohonan=$data.tarikh_permohonan)
			#set($tarikh_permohonan_kjp=$data.tarikh_permohonan_kjp)
			#set($txtAlamat1=$data.alamat1)
   			#set($txtAlamat2=$data.alamat2)
   			#set($txtAlamat3=$data.alamat3)
   			#set($txdTarikhPengesahan=$data.tarikh_sahkan)
			#set($txtPoskod=$data.poskod)
			#set($sorFlagPeruntukan=$data.flag_peruntukan)
  			#set($sorFlagSegera=$data.flag_segera)
			#set($txtTujuan=$data.tujuan)
			#set($txtRujukanKementerian=$data.no_rujukan_surat)
			#set($txdTarikhKehendaki=$data.tarikh_kehendaki)
			#set($txdTarikhPengesahan=$data.tarikh_pengesahan)
   			#set($txdTarikhSurat=$data.tarikh_surat)
   			#set($sorJenisProjek=$data.flag_jenis_projek)
   			#set($lblJenisPermohonan=$data.flag_jenispermohonan)
   			#set($lblSuburusan=$data.nama_suburusan)
   			#set($id_senaraisemak=$data.id_senaraisemak)
   			#set($flagSahkan=$data.flag_semak)	
   			#set($sorUrusan=$data.idSuburusan)	
   			#set($txtJumHM=$data.jumlah_hakmilik)	
			#end
		#end
	
		#if($isEdit=="no")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#set($disability1 = "disabled")
		#set($M = "")
		#else
		#set($M = "*")
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
		#end
	
	<fieldset>
	<legend><strong>Maklumat Asas Permohonan</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
			<tr align="right">
				<td>$!lblJenisPermohonan</td>
            </tr>
		</table>
		
		#if($flagStatusOnline=="1")
			<div class="warning">
				PERMOHONAN INI TELAH DIKEMBALIKAN
				#if($catatan_status_online!="")
				DENGAN ALASAN $!catatan_status_online
				#end		
			</div>
		#end
		
    	<table width="100%"  cellpadding="0" border="0">
        
            <tr>
            	<td width="1%">&nbsp;</td>
            	<td width="25%">Bil. Permohonan</td>
            	<td width="1%">:</td>
            	<td width="73%">$!lblNoPermohonan 
            	<b>
            	#if($flag_semakan_online=="1")
            	(Menunggu Semakan)
            	#elseif($flag_semakan_online=="2")
            	(Menunggu Kelulusan Pengarah)
            	#elseif($flag_semakan_online=="3")
            	(Telah Diluluskan Pengarah)
            	#end
            	</b>
            	</td>            	
            </tr>
           
            #if($lblNoOnline!="")
           	<tr>
           		<td>&nbsp;</td>
            	<td>No. Rujukan Online</td>
            	<td>:</td>
            	<td><b>$!lblNoOnline</b></td>
            </tr>
            #end
            
            
            #if($txtNoRujukanPTG!="")
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTG</td>
            	<td>:</td>
                <td><input type="text" readonly class="disabled" name="txtNoRujukanPTG" id="txtNoRujukanPTG" value="$!txtNoRujukanPTG" maxlength="30" size="37"  ></td>
            </tr>
            #end
            
            #if($txtNoRujukanPTD!="")
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTD</td>
            	<td>:</td>
                <td><input type="text" readonly class="disabled" name="txtNoRujukanPTD" value="$!txtNoRujukanPTD" maxlength="30" size="37"  ></td>
            </tr>
            #end
            
             #if($txtNoRujukanUPT!="")
           	<tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan UPT</td>
            	<td>:</td>
                <td><input type="text" readonly class="disabled" name="txtNoRujukanUPT" value="$!txtNoRujukanUPT" maxlength="30" size="37"  ></td>
            </tr>
            #end
            
            <tr>
            	<td><font color="red">$!M</font></td>
            	<td>Urusan</td>
            	<td>:</td>
            	<td><select $disability1 $disabilityx name="sorUrusan" style="width:auto" onchange="doChangeUrusanUpdate()" >
      			
      			#if($sorUrusan=="51")
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
				<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
				<option value="">SILA PILIH</option>
      			#elseif($sorUrusan=="52")
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
				<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
				<option value="">SILA PILIH</option>
      			#elseif($sorUrusan=="53")
      			<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
      			<option value="">SILA PILIH</option>
      			#else
      			<option value="">SILA PILIH</option>
      			<option value="51">PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</option>
      			<option value="52">PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</option>
				<option value="53">PENGAMBILAN TANAH SEMENTARA</option>
      			#end
  
				</select></td>
            </tr>
            #if($flag_semakan_online=="3")
            #if($id_suburusan=="51" || $id_suburusan=="52")
				
			#else
			<tr>
				<td>&nbsp;</td>
	           	<td width="25%" valign="top">TARIKH PERMOHONAN KJP</td>
	           	<td width="1%" valign="top">:</td>
	           	<td width="40%" valign="top">
	           	
	           	<input name="tarikh_permohonan_kjp" $disability $disabilityx value="$!dateToday" size="11" id="tarikh_permohonan_kjp" type="text" onblur="check_date(this)" />$!frmtdate</td>
	      		
	         </tr>
	         #end
	         #end
			
            <!-- <tr>
            	<td><font color="red">$!M</font></td>
            	<td>Tarikh Permohonan</td>
            	<td>:</td>
                <td><input name="txdTarikhPermohonan" $disability $disabilityx  value="$!txdTarikhPermohonan" size="11" id="txdTarikhPermohonan" type="text" onblur="check_date(this)" />
       		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">$!frmtdate#end</td>
            </tr> -->
            
            <!-- <tr>
            	<td><font color="red">$!M</font></td>
            	<td>Tarikh Permohonan KJP</td>
            	<td>:</td>
                <td><input name="tarikh_permohonan_kjp" $disability $disabilityx  value="$!tarikh_permohonan_kjp" size="11" id="tarikh_permohonan_kjp" type="text" onblur="check_date(this)" />
       		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_permohonan_kjp',false,'dmy');">$!frmtdate#end</td>
            </tr> -->
     
        </table> 
      
    </fieldset>

<br/>
	<fieldset id="middle">
	<legend><strong>Maklumat Agensi / KJP</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
        	
        	<tr>
        		<td width="1%">&nbsp;</td>
				<td width="25%">Kementerian</td>
				<td width="1%">:</td>
				<td width="73%">$!selectKementerian</td>
			</tr>
           
           	<tr>
           		<td>&nbsp;</td>
				<td>Nama Agensi</td>
				<td>:</td>
				<td>$!selectAgensi</td>
			</tr>
           
           <tr>      
           		<td>&nbsp;</td>
                <td>Alamat</td>
                <td>:</td>
                <td><input type="text" class="disabled" readonly name="txtAlamat1" value="$!txtAlamat1" id="txtAlamat1" maxlength="80"   size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="3">&nbsp;</td>
                <td><input class="disabled" readonly type="text" name="txtAlamat2" value="$!txtAlamat2" id="txtAlamat2" maxlength="80"   size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="3">&nbsp;</td>
                <td><input class="disabled" readonly type="text" name="txtAlamat3" value="$!txtAlamat3" id="txtAlamat3" maxlength="80"   size="60" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td></td>
            	<td>:</td>
                <td><input type="text" class="disabled" readonly name="txtPoskod" size="5" onkeyup="validateNumber(this,this.value);"  value="$!txtPoskod" maxlength="5" id="txtPoskod" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>Negeri</td>
            	<td>:</td>
                <td>$!selectNegeri</td>
            </tr>
            	
       </table>  
   </fieldset>
   
   <br/>
	
	<br/>
   
   <fieldset id="bottom">
   <legend><strong>Maklumat Lengkap Projek</strong></legend>
       
       <table width="100%"  cellpadding="0" border="0">    
         	
         	#if($sorFlagPeruntukan=="1")
         		#set($checkPA="checked")
         		#set($checkPB="")
         	#elseif($sorFlagPeruntukan=="2")
         		#set($checkPA="")
         		#set($checkPB="checked")
         	#else
         		#set($checkPA="")
         		#set($checkPB="")
         	#end
         	
         	<tr>
         		<td width="1%"><font color="red">$!M</font></td>
            	<td width="25%">Peruntukan Projek</td>
            	<td width="1%">:</td>
                <td width="73%">
                	<input name="sorFlagPeruntukan" $disability1 $checkPA type="radio" value="1" />Ada
                	<input name="sorFlagPeruntukan" $disability1 $checkPB type="radio" value="2" />Tiada
                </td> 
            </tr>
            
         #if($showSegera=="yes")   
            #if($sorFlagSegera=="1")
         		#set($checkSA="checked")
         		#set($checkSB="")
         	#elseif($sorFlagSegera=="2")
         		#set($checkSA="")
         		#set($checkSB="checked")
         	#else
         		#set($checkSA="")
         		#set($checkSB="")
         	#end
         	
            <tr>
            	<td><font color="red">$!M</font></td>
           	  	<td>Borang I?</td>
           	  	<td>:</td>
                <td>
                	<input name="sorFlagSegera" $disability1 $checkSA type="radio" value="1" />Ya
           	  		&nbsp;&nbsp;<input name="sorFlagSegera" $disability1 $checkSB type="radio" value="2" />Tidak
           	  	</td>
            </tr> 
         #end
         	
         	<tr>
         		<td><font color="red">$!M</font></td>
           	   	<td>Negeri</td>
           	   	<td>:</td>
                <td>$!selectNegeriProjek</td>
            </tr>
          
            <tr>
            	<td><font color="red">$!M</font></td>
             	#if($showJajahan=="yes")
             		<td>Jajahan</td>
             	#else
             		<td>Daerah</td>
             	#end
             	<td>:</td>
                <td>$!selectDaerah</td>
            </tr>
         	
         	<tr>
         	  	<td valign="top"><font color="red">$!M</font></td>
           	   	<td valign="top">Nama Projek</td>
           	   	<td valign="top">:</td>
                <td><textarea name="txtTujuan" $disability $disabilityx id="txtTujuan" cols="40%" rows="4" >$!txtTujuan</textarea></td>
            </tr>
         	
         #if($showSegera=="yes")  	
         	#set($checkJPA = "")
            #set($checkJPB = "")
            
            #if($sorJenisProjek=="1")
            	#set($checkJPA = "checked")
            #elseif($sorJenisProjek=="2")
            	#set($checkJPB = "checked")
            #end
            
         	<tr>
            	<td colspan="3">&nbsp;</td>
            	<td>
            		<input name="sorJenisProjek" $disability1 $checkJPA type="radio" value="1" />Jajaran
            		<input name="sorJenisProjek" $disability1 $checkJPB type="radio" value="2" />Tapak
            	</td>
            </tr>
            
            <!-- <tr>
         	  	<td valign="top"><font color="red">$!M</font></td>
           	   	<td valign="top">Surat Iringan</td>
           	   	<td valign="top">:</td>
           	   	<td><a href="javascript:popupCarianHakmilik('$id_permohonan','daftar_sek8_online')"><font color="blue">$!lblNoOnline .doc</font></a></td>
            </tr> -->
         #end
         
            <tr>
         		<td><font color="red">$!M</font></td>
           	    <td>Jumlah Hakmilik</td>
           	    <td>:</td>
           	    <td><input type="text" $disability $disabilityx name="txtJumHM" id="txtJumHM" maxlength="4" value="$!txtJumHM" size="5" onblur="validateNumber(this,this.value);" onkeyup="validateNumber(this,this.value);" /></td>
         	</tr>
         	
         	<tr>
         	 	<td>&nbsp;</td>
           	    <td>No. Ruj Surat KJP</td>
           	    <td>:</td>
                <td><input type="text" $disability $disabilityx name="txtRujukanKementerian" id="txtRujukanKementerian"   maxlength="50" value="$!txtRujukanKementerian" size="35" /></td>
            </tr>
         	
         	<tr>
         		<td>&nbsp;</td>
            	<td>Tarikh Surat KJP</td>
            	<td>:</td>
                <td><input name="txdTarikhSurat" $disability $disabilityx id="txdTarikhSurat" value="$!txdTarikhSurat" size="11" type="text" onblur="check_date(this)" />
       		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSurat',false,'dmy');">$!frmtdate#end</td>
            </tr>
         	
            <tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Dikehendaki</td>
            	<td>:</td>
                <td><input name="txdTarikhKehendaki" $disability $disabilityx id="txdTarikhKehendaki" value="$!txdTarikhKehendaki" size="11" type="text" onblur="check_date(this)" />
       		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhKehendaki',false,'dmy');">$!frmtdate#end</td>
            </tr>
             <tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Pengesahan Pengarah Negeri</td>
            	<td>:</td>
                <td><input name="txdTarikhPengesahan" $disability $disabilityx id="txdTarikhPengesahan" value="$!txdTarikhPengesahan" size="11" type="text" onblur="check_date(this)" />
<!--        		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPengesahan',false,'dmy');">$!frmtdate#end -->
       		 	</td>
            </tr>
        </table>	
        
        #if($isEdit=="yes")
        <table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian3</td>
        	</tr>
        </table>
        #end
        
    </fieldset>
	 #if ($listJabatanTeknikal.size() > 0)
	<fieldset>
      <legend><b>Ulasan Jabatan Teknikal</b></legend>
      <table align="center" width="100%">
        
		<tr class="table_header">
          <td scope="row"  align="center"><strong>Bil</strong></td>
          <td ><strong>Nama Jabatan</strong></td>
          <td ><strong>Tarikh Terima Ulasan</strong></td>
          <td ><strong>Status Sokongan</strong></td>
          <td  align="left"><strong>Ulasan</strong></td>
        </tr>
        
        #set ($list = "")
        #if ($listJabatanTeknikal.size() > 0)
        #foreach ($list in $listJabatanTeknikal)
        #if ($list.bil == '')
        	#set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        	#set( $row = "row1" )
        #else 
        	#set( $row = "row2" )
        #end
        
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row">$list.NAMA_JABATAN</a></td>
          <td class="$row">$list.TARIKH_TERIMA_ULASAN</td>
          <td class="$row">$list.KEPUTUSAN</td>
          <td class="$row" align="left">$list.ULASAN</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset>
	
	<br/>
	#end
	
	<input name="tabId" type="hidden" id="tabId" value="$!selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">
      
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(0);" tabindex="1">MAKLUMAT TANAH</li>
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(1);" tabindex="1">MUATNAIK DOKUMEN</li>
        #if($id_suburusan=="51" || $id_suburusan=="52")
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(2);" tabindex="1">SENARAI SEMAKAN</li>
        #end
      </ul>
      
      <div class="TabbedPanelsContentGroup">
      
      	<!-- START TAB 1 -->
        <div class="TabbedPanelsContent">
        	<fieldset>
        	<legend><strong>Maklumat Tanah Terlibat</strong>
            
            
            			#if($roleAgensi=="no")
            			            			
                		#if($id_status=="8" || $flagStatusOnline=="1")
	                		
	                    	#if($id_jawatan_user == $layer1 && ($flag_semakan_online=="" || $flag_semakan_online=="4"))
	                    	<input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambahHakmilik('$!flag_subjaket');">
                            
                            #if($sorUrusan=="52")                                                     
                            <input  name="cmdPupupHakmilik" id="cmdPupupHakmilik" onClick="popupGetHakmilik('$id_permohonan','8','salin_hakmilik_sek8_KJP')" type="button" value="POPUP SALIN HAKMILIK SEKSYEN 4" />
                            #end
                            #if($sorUrusan=="51")                                                     
                            <input  name="cmdPupupHakmilik" id="cmdPupupHakmilik" onClick="popupGetHakmilik('$id_permohonan','4','salin_hakmilik_sek8_KJP')" type="button" value="POPUP SALIN HAKMILIK SEKSYEN 8" />
                            #end
           
    						#end
    					#end
    					#end
                        
            
            
            </legend>
            <a href="javascript:popupCarianHakmilik('$id_permohonan','daftar_sek8_online')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
               <!--         

                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                	<tr>
                		#if($roleAgensi=="no")
                		#if($id_status=="8" || $flagStatusOnline=="1")
	                		#if(($id_jawatan_user == $layer1 && $flag_semakan_online=="") || 
							($id_jawatan_user == $layer2 && ($flag_semakan_online=="" || $flag_semakan_online=="1" )) ||
							($id_jawatan_user == $layer3 && ($flag_semakan_online=="" || $flag_semakan_online=="1" || $flag_semakan_online=="2")))
	                    	<td width="30%" align="left"><input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambahHakmilik('$!flag_subjaket');">
                            
                            
           
                            
                            </td>
    						#end
    					#end
    					#end
    					<td width="70%" align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    			</table>
                
             
                
                #if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
                #end
                
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">     
                    
                    <tr class="table_header">
                  		<td align="center"><b>Bil</b></td>
                  		<td><b>No.LOT/No.PT</b></td> 
                  		<td><b>No.Hakmilik</b></td>      
                  		<td><b>Mukim/Pekan/Bandar</b></td>
                  		#if($showSegera=="yes")<td><b>Keluasan diambil</b></td>#end
           		 	</tr>
                    
   					#if($saiz_listTanah!=0)
                    	#foreach($listTanah in $listMaklumatTanah)
                    	#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              		 		#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    	
                    	
                    <tr>
                		<td class="$row" align="center">$!listTanah.bil</td>
               		 	<td class="$row"><a href="javascript:viewHM('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.no_lotpt</font></a></td> 
               		 	<td class="$row">$!listTanah.kod_jenis_hakmilik $!listTanah.no_hakmilik</td>
                		<td class="$row">$!listTanah.nama_mukim #if($!listTanah.seksyen!="")SEKSYEN $!listTanah.seksyen#end</td>
                		#if($showSegera=="yes")<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>#end
            		<tr>
                    	#end
                    	
                    #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
                    #end
                    
                </table>   
                
                #if($saiz_listTanah > 5)
                </div>
                #end
                 -->    	
          	</fieldset>	
          	
          	#if($!id_status=="8")
          	<table width="100%" border="0">
          		<tr>
          			
          			<td width="9%"><font color=red style=font-size:10px >Perhatian :</font></td></span>
          			#if($txtJumHM == $saiz_listTanah)
          			<td width="91%">$!perhatian11 <i><font color=red style=font-size:10px>$!saiz_listTanah/$!txtJumHM</font></i></td>
          			#else
          			<td width="91%">$!perhatian11 <i><font color=red style=font-size:10px><span class="blink">$!saiz_listTanah/$!txtJumHM</font></i></td>
          			#end
          		</tr>
				<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian8</td>
        			
        		</tr>
        		#if(($txtJumHM == $saiz_listTanah) && ($id_suburusan=="51" || $id_suburusan=="52"))  	
	        		#if($id_jawatan_user == $layer3 && $flag_semakan_online=="3")
	        		<tr>
						<td>&nbsp;</td>
	        			<td>$!perhatian10</td>
	        		</tr>
	        		#end
        		#end
			</table>
			#end
			
        </div>
        
        <!-- END TAB 1 -->
        
        <!-- START TAB 2 -->
        <div class="TabbedPanelsContent">
        	<fieldset>
        		<legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>
				
				#if($roleAgensi=="no")
				#if($id_status=="8" || $flagStatusOnline=="1")
		            
					#if($id_jawatan_user == $layer1 && ($flag_semakan_online=="" || $flag_semakan_online=="4"))
					<table width="100%"  cellpadding="0" border="0">
	                	<tr>
	                    	<td colspan="4"><input type="button" name="cmdTambah2" value ="Tambah" onClick="javascript:tambahDokumen('$id_permohonan');"></td>
	                    </tr>
	                </table>
	                #end
                #end
                #end
                
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                    <tr class="table_header">
                    	<td width="3%" align="center"><b>Bil</b></td>
                    	<td width="20%"><b>Nama Dokumen</b></td>
                        <td width="20%"><b>Keterangan</b></td>
                        <td width="20%"><b>Jenis Dokumen</b></td>
                        <td width="30%"><b>Muat Turun</b></td>
                        #if($listD_size!=0 && ($id_status=="8" || $flagStatusOnline=="1"))
                        	#if($id_jawatan_user == $layer3 && $flag_semakan_online=="3")
                        	<td width="6" align="center"><b>&nbsp;</b></td>
                        	#end
                       	#end
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
                    	<td class="$row" align="center">$listD.bil</td>
                    	<td class="$row">$listD.tajuk</td>
                        <td class="$row">$listD.keterangan</td>
                        <td class="$row">$!listD.jenisdoc</td>
                       	<td class="$row"><a href="javascript:papar_Lampiran('$!listD.id_Dokumen')"><font color="blue">$listD.nama_Fail</font></a></td>
                    	#if($listD_size!=0 && ($id_status=="8" || $flagStatusOnline=="1"))
                    		#if($id_jawatan_user == $layer3 && $flag_semakan_online=="3")
                    		<td class="$row" align="center"><input type="button" name="cmdHapusDoc" value ="Hapus" onClick="hapusDokumen('$!listD.id_Dokumen')"></td>	
                   			#end
                   		#end
                    </tr>
             #end  
              		 
         #else
                	<tr>
                    	<td colspan="4">Tiada rekod</td>
                    </tr>
         #end
                    
                </table>        	
            </fieldset>	
            
            #if($!id_status=="8")
          	<table width="100%" border="0">
          		<tr>
          			<td width="9%"><font color=red style=font-size:10px>Perhatian :</font></td>
          			<td width="91%">$!perhatian11</td>
          		</tr>
				<tr>
					<td>&nbsp;</td>
        			<td>$!perhatian8</td>
        		</tr>
        		#if(($txtJumHM == $saiz_listTanah) && ($id_suburusan=="51" || $id_suburusan=="52"))        
        			#if($id_jawatan_user == $layer3 && $flag_semakan_online=="3")  			
	        		<tr>
						<td>&nbsp;</td>
	        			<td>$!perhatian10</td>
	        		</tr>
        			#end
        		#end
			</table>
			#end
			
        </div>
      	<!-- END TAB 2 -->
      	
      	<!-- START TAB 3 -->
      	#if($id_suburusan=="51" || $id_suburusan=="52")
 		<div class="TabbedPanelsContent">
    	
    	#set ($checked1 = "")
		#set ($checked2 = "")
		#set ($checked3 = "")
		#set ($checked4 = "")
		#set ($checked5 = "")
		#set ($checked6 = "")
		#set ($checked7 = "")
		
		#set ($checked10 = "")
		#set ($checked20 = "")
    	
    	
    	#foreach($List in $senaraiSemakan) 
    	#set($idSemak=$List.id_senaraisemak)  	
    	#if ($List.semak1 == "1")#set ($checked1 = "checked")#end		
		#if ($List.semak2 == "1")#set ($checked2 = "checked")#end		
		#if ($List.semak3 == "1")#set ($checked3 = "checked")#end			
		#if ($List.semak4 == "1")#set ($checked4 = "checked")#end		
		#if ($List.semak5 == "1")#set ($checked5 = "checked")#end		
		#if ($List.semak6 == "1")#set ($checked6 = "checked")#end		
		#if ($List.semak7 == "1")#set ($checked7 = "checked")#end
		
		#if ($List.semak10 == "1")#set ($checked10 = "checked")#end		
		#if ($List.semak20 == "1")#set ($checked20 = "checked")#end
		
    	#end
    	
    	
		<fieldset>
		<legend><strong>Senarai Semakan</strong></legend>
	
		<br/>
		
		#if($sorUrusan=="52")                                                     
          <table width="100%" cellpadding="0" cellspacing="0">
    		<tr>
            	<td width="5%">&nbsp;</td>
            	<td width="1%"><font color="red">$!M</font></td>
    			<td width="94%"><input type="checkbox" name="cbsemaks1" $disability1 value="1" id="cbsemaks1" $checked1>
     				1. Pelan Pengambilan Tanah yang lengkap.</td>
  			</tr>
        
  			<tr>
    			<td>&nbsp;</td>
    		 	<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name="cbsemaks2" $disability1 value="1" id="cbsemaks2" $checked2 >
     		 		2. Sijil Carian Rasmi/ Persendirian yang terkini.</td>
  			</tr>
  			
  			<tr>
    			<td>&nbsp;</td>
    		 	<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name="cbsemaks3" $disability1 value="1" id="cbsemaks2" $checked3 >
     		 		3. Ulasan dari Jabatan-Jabatan Teknikal.</td>
  			</tr>
  			
  			<tr>
    			<td>&nbsp;</td>
    		 	<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name="cbsemaks4" $disability1 value="1" id="cbsemaks2" $checked4 >
     		 		4. Ulasan dari Jabatan Alam Sekitar.</td>
  			</tr>
  			
  			<tr>
    			<td>&nbsp;</td>
    		 	<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name="cbsemaks5" $disability1 value="1" id="cbsemaks2" $checked5 >
     		 		5. Pengesahan peruntukan pampasan yang mencukupi termasuk kos penandaan dan lain-lain kos.</td>
  			</tr>
  			
  			<tr>
    			<td>&nbsp;</td>
    		 	<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name="cbsemaks6" $disability1 value="1" id="cbsemaks2" $checked6 >
     		 		6. Surat Perakuan segera (Borang I)</td>
  			</tr>
  			
  			<tr>
    			<td>&nbsp;</td>
    		 	<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name="cbsemaks7" $disability1 value="1" id="cbsemaks2" $checked7 >
     		 		7. Surat iringan permohonan rasmi dari agensi.</td>
  			</tr>
    	</table>
        #end
        #if($sorUrusan=="51")                                                     
          <table width="100%" cellpadding="0" cellspacing="0">
    		<tr>
            	<td width="5%">&nbsp;</td>
            	<td width="1%"><font color="red">$!M</font></td>
    			<td width="94%"><input type="checkbox" name="cbsemaks10" $disability1 value="1" id="cbsemaks10" $checked10 >
     				1. Pelan Pengambilan Tanah yang lengkap.</td>
  			</tr>
        
  			<tr>
    			<td>&nbsp;</td>
    		 	<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name="cbsemaks20" $disability1 value="1" id="cbsemaks20" $checked20 >
     		 		2. Pengesahan peruntukan yang mencukupi untuk membiayai kos pampasan kerosakan akibat kerja-kerja ukur dan kajian tanah.</td>
  			</tr>
    	</table>
        #end

   		
    
		</fieldset>
				#if($!id_status=="8")
				<table width="100%" border="0">
					<tr>
						<td width="9%"><font color=red style="font-size: 10px">Perhatian
								:</font></td>
						<td width="91%">$!perhatian11</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>$!perhatian8</td>
					</tr>
					#if(($txtJumHM == $saiz_listTanah) && ($id_suburusan=="51" ||
					$id_suburusan=="52")) #if($id_jawatan_user == $layer3 &&
					$flag_semakan_online=="3")
					<tr>
						<td>&nbsp;</td>
						<td>$!perhatian10</td>
					</tr>
					#end #end
				</table>
				#end
			</div> 
			#end
        <!-- END TAB 3 -->
        
      	
  </div> 
  </div> 
	
	#end

	<table width="100%" border="0">
		<tr align="center">
			<td>
			
			#if($roleAgensi=="no")
			
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPendaftaran('$!id_permohonan','$!showSegera','$!mode')">
			#end
	
			#if($mode=="view" && ($id_status!="11" && $id_status!="8" ))
			<input type="button" name="cmdPengesahanA" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahan()">
			#end
			
			#if($mode=="view" && ($id_status=="8" || $flagStatusOnline=="1"))
				#if($isEdit=="no")			
				
          			#if($txtJumHM == $saiz_listTanah)  
          				<!-- Pembantu Tadbir Hantar Untuk Semakan <img src='../img/emel.gif'>-->
          				#if($id_jawatan_user == $layer1)
          				#if($flag_semakan_online=="" || $flag_semakan_online=="4")
          				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPendaftaran('$!id_permohonan')">
          				<input type="button" name="cmdHantarSemakan" value ="Hantar Untuk Semakan" onClick="javascript:hantarSemakan('$!id_permohonan')">
          				#end
          				#end
          				
          				<!-- Penolong Pengarah Membuat Semakan -->
          				#if($id_jawatan_user == $layer2 && $flag_semakan_online=="1")
          				<input type="button" name="cmdReturnFile" value ="Kembalikan kepada penyedia" onClick="javascript:returnFile('$!id_permohonan','4','penyemak')" >
						<input type="button" name="cmdSahSemakan" value ="Sahkan Semakan" onClick="javascript:sahSemakan('$!id_permohonan')" >
          				#end
          				
						<!-- Permohonan meluluskan permohonan -->
          				#if($id_jawatan_user == $layer3 && $flag_semakan_online=="2")
	          			<input type="button" name="cmdReturnFile" value ="Kembalikan kepada penyedia" onClick="javascript:returnFile('$!id_permohonan','4','pelulus')" >
	          			<input type="button" name="cmdLulusPermohonan" value ="Luluskan Permohonan" onClick="javascript:lulusPermohonan('$!id_permohonan')" >	
	          			#end	
	          			
	          			<!-- Telah diluluskan dan Pengarah yang hantar -->
	          			#if($id_jawatan_user == $layer3 && $flag_semakan_online=="3")
	          				#if($id_suburusan=="51" || $id_suburusan=="52")
	          				<input type="button" name="cmdHantar" value ="Seterusnya" onClick="javascript:hantarPermohonan('$!id_permohonan','1')">
	          				#else
	          				<input type="button" name="cmdHantar" value ="Hantar" onClick="javascript:hantarPermohonan('$!id_permohonan','2')">
	          				#end
	          			#end
          			
          			#elseif($txtJumHM != $saiz_listTanah)
          				#if(($id_jawatan_user == $layer1 && $flag_semakan_online=="") || ($id_jawatan_user == $layer1 && $flag_semakan_online=="4"))
          				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPendaftaran('$!id_permohonan')">
          				#end
          			
          				<!-- Penolong Pengarah Kembalikan kepada penyedia -->
          				#if($id_jawatan_user == $layer2 && $flag_semakan_online=="1")
          				<input type="button" name="cmdReturnFile" value ="Kembalikan kepada penyedia" onClick="javascript:returnFile('$!id_permohonan','4','penyemak')" >
          				#end
          				
						<!-- Pengarah Kembalikan kepada penyedia -->
          				#if($id_jawatan_user == $layer3 && $flag_semakan_online=="2")
          				<input type="button" name="cmdReturnFile" value ="Kembalikan kepada penyedia" onClick="javascript:returnFile('$!id_permohonan','4','pelulus')" >
	          			#end	
	          			
          			#end
          			
					<!-- #if(($id_jawatan_user == $layer1) && ($flag_semakan_online==""))
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPendaftaran('$!id_permohonan')">
					#end -->
					
          		#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPendaftaran('$!id_permohonan','$!showSegera','$!mode')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_permohonan')">
				#end
			#end
			
			#if($mode=="view" && $no_fail=="")
					<input type="button" name="" value ="Hapus Rekod" onClick="javascript:hapus_beramai('$!id_fail')">
			#end
			
			#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">
				</td>
			</tr>
	</table>
	
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        	<td ><a href="#" class="style2" onClick="javascript:cetakSuratKPTG('$!id_permohonan')"><font color="blue">Surat Iringan Kepada PTG</font></a></td>
      </tr>  
      <tr>
       	 	<td ><a href="#" class="style2" onClick="javascript:cetakLampiranA('$!id_permohonan','$!nama2Mukim')"><font color="blue">Lampiran A</font></a></td>
      </tr>   
    </table>
</fieldset>	
	
<input type="hidden" name="command2">
<input type="hidden" name="command3">	
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="id_hakmilik">
<input type="hidden" name="id_senaraisemak" value="$!id_senaraisemak">
<input type="hidden" name="user_id" value="$!user_id">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<input type="hidden" name="ResultAdd" value="$!ResultAdd">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<input type="hidden" name="returnType">

<script>

function hapus_beramai(id_fail) {
	
	document.${formName}.ScreenLocation.value = "middle";
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline&command=hapus_fail&id_fail="+id_fail;
	document.${formName}.submit();
}

function popupBorangI(id_permohonan,jenis_seksyen,flag_skrin)
{
	var id_negeri = document.${formName}.socNegeriProjek.value;
	var id_daerah = document.${formName}.socDaerah.value;
	
	//alert("id_negeri :"+id_negeri);
	//alert("id_daerah :"+id_daerah);
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadBorangI?id_permohonan="+id_permohonan+"&id_negeri="+id_negeri+"&id_daerah="+id_daerah+"&jenis_seksyen="+jenis_seksyen+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function popupGetHakmilik(id_permohonan,jenis_seksyen,flag_skrin)
{
	var id_negeri = document.${formName}.socNegeriProjek.value;
	var id_daerah = document.${formName}.socDaerah.value;
	
	//alert("id_negeri :"+id_negeri);
	//alert("id_daerah :"+id_daerah);
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupHakmilik?id_permohonan="+id_permohonan+"&id_negeri="+id_negeri+"&id_daerah="+id_daerah+"&jenis_seksyen="+jenis_seksyen+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function HandlePopup_from_copy_hakmilik(id_permohonan) {
	//document.${formName}.ScreenLocation.value = "top";
	//document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}

function returnFile(idpermohonan,returnType,jenisTolak) {
	/*
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.returnType.value = returnType;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "returnFile";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
	*/
	

	var w = "400";
	var h = "250";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmPopupTolakPermohonan?id_permohonan="+idpermohonan+"&formnew=yes&jenisTolak="+jenisTolak;
	
	var hWnd = window.open(url, "Permohonan Online Dikembalikan", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function returnFromPopupTolak(idpermohonan,returnType,jenisTolak)
{
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.returnType.value = returnType;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "returnFile";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}



function lulusPermohonan(idpermohonan,user_id) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "lulusPermohonan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function sahSemakan(idpermohonan,user_id) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "sahSemakan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function hantarSemakan(idpermohonan,user_id) {
	//alert("xxxx");
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	//alert(idpermohonan);
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "hantarSemakan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function cetakPengesahan() {
    var url="../servlet/ekptg.report.ppt.PengesahanPermohonanOnline?idpermohonan="+document.${formName}.id_permohonan.value;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
}
function hantarPermohonan(idpermohonan,flag) {

	if ( flag=="2" && (!window.confirm("Adakah Anda Pasti?"))) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "hantarPermohonan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}

function doChangeUrusanUpdate() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "txdTarikhPermohonan";
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	document.${formName}.command3.value = "doChangeUrusanUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}

function doChangeUrusan() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "txdTarikhPermohonan";
	document.${formName}.command.value = "pendaftaran";
	document.${formName}.command2.value = "doChangeUrusan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}

function cetakSuratKPTG(idpermohonan) {
	//var url = "../servlet/ekptg.report.ppt.SuratKpdPTGDariJKPTGIbuPejabat?idFail="+idfail+"&namaPegawai="+namaPengarah;
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=SuratKpdPTGDariJKPTGIbuPejabat&flagReport=S";	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakLampiranA(idpermohonan,namaMukim) {
	/*var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idpermohonansimati="+idpermohonansimati+"&report=BorangDD&flagReport=B";
    var url = "../servlet/ekptg.report.ppt.LampiranASek8?idfail="+idfail+"&nama_mukim="+namaMukim;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=LampiranASek8&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}
function viewHM(id_hakmilik){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function tambahHakmilik(flagSubjaket) {

	if(flagSubjaket=="1"){
		alert("No Subjaket perlu dijana semula sekiranya ada penambahan hakmilik");
	}
	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline"; 
	document.${formName}.submit();
}
function hapusDokumen(iddokumen) {
	
	document.${formName}.ScreenLocation.value = "middle";
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline&command=hapusDokumen&id_dokumen="+iddokumen;
	document.${formName}.submit();
}
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function tambahDokumen(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "tambahDokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline"; 
	document.${formName}.submit();
}

function doChangeKementerianUpdate() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socAgensi";
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	document.${formName}.command3.value = "doChangeKementerianUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function doChangeProjekNegeriUpdate() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socDaerah";
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	document.${formName}.command3.value = "doChangeProjekNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function batalKemaskini(idpermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function kemaskiniPendaftaran(idpermohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "txtNoRujukanPTG";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function simpanPendaftaran(idpermohonan,showSegera,mode) {

	var dat1=document.${formName}.txdTarikhSurat;
	var dat2=document.${formName}.txdTarikhKehendaki;
	var dat3=document.${formName}.tarikh_permohonan_kjp;

	if(showSegera=="yes"){
		var radioSelected2 = false;
		for (j = 0;  j < ${formName}.sorFlagSegera.length;  j++){
			if (${formName}.sorFlagSegera[j].checked)
			radioSelected2 = true;
		}
	}
	
	var radioSelected = false;
	for (i = 0;  i < ${formName}.sorFlagPeruntukan.length;  i++){
		if (${formName}.sorFlagPeruntukan[i].checked)
		radioSelected = true;
	}

	//tarikh surat kjp
	var KJP  = document.getElementById("txdTarikhSurat").value;
	var dt1   = parseInt(KJP.substring(0,2),10);
   	var mon1  = parseInt(KJP.substring(3,5),10);
   	var yr1   = parseInt(KJP.substring(6,10),10);
   	var dateKJP = new Date(yr1, mon1, dt1);

   	//tarikh kehendaki
   	var Khndk  = document.getElementById("txdTarikhKehendaki").value;
	var dt2   = parseInt(Khndk.substring(0,2),10);
   	var mon2  = parseInt(Khndk.substring(3,5),10);
   	var yr2   = parseInt(Khndk.substring(6,10),10);
   	var dateKehendaki = new Date(yr2, mon2, dt2);
   	
    /* //tarikh permohonan
   	var Tmohon  = document.getElementById("txdTarikhPermohonan").value;
	var Tdt2   = parseInt(Tmohon.substring(0,2),10);
   	var Tmon2  = parseInt(Tmohon.substring(3,5),10);
   	var Tyr2   = parseInt(Tmohon.substring(6,10),10);
   	var dateMohon = new Date(Tyr2, Tmon2, Tdt2); */
   	
   /* //tarikh permohonan KJP
   	var Tmohon  = document.getElementById("tarikh_permohonan_kjp").value;
	var Tdt2   = parseInt(Tmohon.substring(0,2),10);
   	var Tmon2  = parseInt(Tmohon.substring(3,5),10);
   	var Tyr2   = parseInt(Tmohon.substring(6,10),10);
   	var dateMohon = new Date(Tyr2, Tmon2, Tdt2); */
   	
   	if(document.${formName}.sorUrusan.value == ""){
		alert("Sila pilih \"Jenis Urusan\" terlebih dahulu.");
  		document.${formName}.sorUrusan.focus(); 
		return;
	}
   	/* else if(document.${formName}.txdTarikhPermohonan.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan\" terlebih dahulu.");
  		document.${formName}.txdTarikhPermohonan.focus(); 
		return;
	} */
   	/* else if(document.${formName}.tarikh_permohonan_kjp.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan KJP\" terlebih dahulu.");
  		document.${formName}.tarikh_permohonan_kjp.focus(); 
		return;
	}
   	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	} */
   	else if(document.${formName}.socKementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.socKementerian.focus(); 
		return;
	}
	else if (!radioSelected){
		alert("Sila pilih \"Peruntukan\" terlebih dahulu.");
		return;
	}
	else if (showSegera=="yes" && !radioSelected2){
		alert("Sila pilih \"Pengambilan Segera\" terlebih dahulu.");
		return;
	}
	else if(document.${formName}.socNegeriProjek.value == ""){
		alert("Sila pilih \"Negeri maklumat projek\" terlebih dahulu.");
  		document.${formName}.socNegeriProjek.focus(); 
		return;
	}
	else if(document.${formName}.socDaerah.value == ""){
		alert("Sila pilih \"Daerah / Jajahan\" terlebih dahulu.");
  		document.${formName}.socDaerah.focus(); 
		return;
	}
	else if(document.${formName}.txtTujuan.value == ""){
		alert("Sila masukkan \"Nama Projek\" terlebih dahulu.");
  		document.${formName}.txtTujuan.focus(); 
		return;
	}
	else if(dateKehendaki < dateKJP){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh surat KJP.");
	 	document.${formName}.txdTarikhKehendaki.focus();
	 	return;
	}
	/* else if(dateKehendaki < dateMohon){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh Permohonan.");
	 	document.${formName}.txdTarikhKehendaki.focus();
	 	return;
	} */
	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else if(document.${formName}.txtJumHM.value == "" || document.${formName}.txtJumHM.value == "0" ){
		alert("Sila pastikan permohonan ini mempunyai Hakmilik");
  		document.${formName}.txtJumHM.focus(); 
		return;
	}
	else {
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.CursorPoint.value = "";

		if(mode=="new"){
			document.${formName}.command.value = "pendaftaran";
			document.${formName}.command2.value = "simpanPendaftaran";
		}else{
			document.${formName}.id_permohonan.value = idpermohonan;
			document.${formName}.command.value = "semakPendaftaran";
			document.${formName}.command2.value = "kemaskiniPendaftaran";
			document.${formName}.command3.value = "updatePendaftaran";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
		document.${formName}.submit();
	}
	
}
function doChangeProjekNegeri() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socDaerah";
	document.${formName}.command.value = "pendaftaran";
	document.${formName}.command2.value = "doChangeProjekNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function doChangeKementerian() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socAgensi";
	document.${formName}.command.value = "pendaftaran";
	document.${formName}.command2.value = "doChangeKementerian";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.command.value = "clearData";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.carianNoLot.value = "";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTOnline";
	document.${formName}.submit();
}
</script>



<script>
window.onload = submitForm;
function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
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

function hideLabel(id){
	if(id=='1'){
		document.getElementById("label1").style.display="inline";
	}else{
		document.getElementById("label1").style.display="none";
	}
}

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
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

<script type="text/javascript">
//var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
