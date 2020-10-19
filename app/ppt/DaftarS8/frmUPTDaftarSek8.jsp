<style type="text/css">


#sddmheader
{	margin: 0;
	padding: 0;
	z-index: 30}

#sddmheader li
{	margin: 0;
	padding: 0;
	list-style: none;
	float: left;
	
	}

#sddmheader li a
{	display: block;
	color: #FFF;
	text-align: center;
	text-decoration: none}

#sddmheader li a:hover
{	
background: #E0F2F7;
}

#sddmheader div
{	position: absolute;
	visibility: hidden;
	margin: 0;
	padding: 0;	
	border: 1px solid #5970B2;
	z-index:2;
	}

	#sddmheader div a
	{	position: relative;
		display: block;
		margin: 0;
		padding: 2.5px 10px;
		width: auto;
		white-space: nowrap;
		text-align: left;
		text-decoration: none;
		background: #EAEBD8;
		color: #2875DE;
		font: 11px arial;
		z-index:2;
		}

	#sddmheader div a:hover
	{	background: #49A3FF;
		color: #FFFFFF;
		}

</style>

<!-- Portal Role -->

#parse("app/ppt/Sek8Paging.jsp")
#parse("app/ppt/frmLabelSet.jsp")

<!-- Portal Role -->
#set($portal_role = "${session.getAttribute('myrole')}")

#set($id_user = "${session.getAttribute('_ekptg_user_id')}")


#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#if($showAlertPaging=="yes")
<script>
alert("Sila Klik 'Paging' No.2 Untuk Penambahan Hakmilik dan Pihak Berkepentingan");
</script>
#end 

<br/>
   #set($no_jkptg="")

<fieldset id="top">
<legend><strong>Daftar Permohonan</strong></legend>

	#if($mode=="new")
	<!--  #set($txdTarikhPermohonanKjp="$currentDATE") -->
	#set($txdTarikhPengesahan="$currentDATE")
	
	
	
	<fieldset>
	<legend><strong>Maklumat Asas Permohonan</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
			<tr align="right">
				<td>PERMOHONAN KAUNTER</td>
            </tr>
		</table>
		
    	<table width="100%"  cellpadding="0" border="0">
        
            <tr>
            	<td width="1%">&nbsp;</td>
            	<td width="25%">Bil. Permohonan</td>
            	<td width="1%">:</td>
            	<td width="73%"><input type="text" name="lblNoPermohonan" value="" size="15" class="disabled" readonly></td>            	
            </tr>
           
           	<tr>
           		<td>&nbsp;</td>
            	<td>No. Fail Permohonan</td>
            	<td>:</td>
            	<td><input type="text" name="lblNoFail" value="" size="15" class="disabled" readonly></td>
            </tr>
           
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTG</td>
            	<td>:</td>
                <td><input type="text" name="txtNoRujukanPTG" id="txtNoRujukanPTG" value="$!txtNoRujukanPTG" maxlength="100" size="37" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();doCheckExistNoFail('ptg',this.value)" ></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTD</td>
            	<td>:</td>
                <td><input type="text" name="txtNoRujukanPTD" value="$!txtNoRujukanPTD" maxlength="100" size="37" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();doCheckExistNoFail('ptd',this.value)" ></td>
            </tr>
           
           	<tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan UPT</td>
            	<td>:</td>
                <td><input type="text" name="txtNoRujukanUPT" value="$!txtNoRujukanUPT" maxlength="100" size="37" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();doCheckExistNoFail('upt',this.value)" ></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>Urusan</td>
            	<td>:</td>
            	<td>PENGAMBILAN TANAH DIBAWAH SEKSYEN 8</td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font></td>
            	<td>Tarikh Permohonan</td>
            	<td>:</td>
                <td><input type="text" name="txdTarikhPermohonan" value="$!txdTarikhPermohonan" size="11" id="txdTarikhPermohonan" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">&nbsp;$!frmtdate</td>
            </tr>
            
            
             #if ($currentStatus =="138" || $txdTarikhPermohonanKjp != "" )
           	<tr>
           		<td>&nbsp;</td>
            	<td>Tarikh Permohonan KJP</td>
            	<td>:</td>
            	<td><input type="text" name="txdTarikhPermohonanKjp" value="$!txdTarikhPermohonanKjp" size="11" class="disabled" readonly></td>
            </tr>
            #end
     
     		#if($sorJenisKodDaerah=="1")
         		#set($checkJK1="checked")
         		#set($checkJK2="")
         	#elseif($sorJenisKodDaerah=="2")
         		#set($checkJK1="")
         		#set($checkJK2="checked")
         	#else
         		#set($checkJK1="")
         		#set($checkJK2="")
         	#end
         	
         	
         	#if($userIdNeg=="10") <!-- untuk selangor sahaja-->
     		<tr>
         		<td>&nbsp;</td>
            	<td>Jenis Kod Daerah</td>
            	<td>:</td>
                <td>
                	<input name="sorJenisKodDaerah" $!checkJK1 type="radio" value="1" onchange="onchangeJenisKodDaerah('$!mode','$!isEdit')" />PTG (Sebelum 01/10/2010)
                	<input name="sorJenisKodDaerah" $!checkJK2 type="radio" value="2" onchange="onchangeJenisKodDaerah('$!mode','$!isEdit')" />SPTB (Selepas 01/10/2010)
                </td> 
            </tr>
     		#end
     		
        </table> 
      
    </fieldset>

<br/>
     
	<fieldset id="middle">
	<legend><strong>Maklumat Agensi / KJP</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
        	
        	<tr>
        		<td width="1%"><font color="red">*</font></td>
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
                <td><input type="text" class="disabled" readonly name="txtPoskod" size="4" onkeyup="validateNumber(this,this.value);"  value="$!txtPoskod" maxlength="5" id="txtPoskod" /></td>
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
         		<td width="1%"><font color="red">*</font></td>
            	<td width="25%">Peruntukan Projek</td>
            	<td width="1%">:</td>
                <td width="73%">
                	<input name="sorFlagPeruntukan" $checkPA type="radio" value="1" />Ada
                	<input name="sorFlagPeruntukan" $checkPB type="radio" value="2" />Tiada
                </td> 
            </tr>
            
            #if($sorFlagSegera=="1")
         		#set($checkSA="checked")
         		#set($checkSB="")
         		#set($checkSC="")
         	#elseif($sorFlagSegera=="2")
         		#set($checkSA="")
         		#set($checkSB="checked")
         		#set($checkSC="")
         	#elseif($sorFlagSegera=="3")
         		#set($checkSA="")
         		#set($checkSB="")
         		#set($checkSC="checked")
         	#else
         		#set($checkSA="")
         		#set($checkSB="")
         		#set($checkSC="")
         	#end
         	
            <tr>
            	<td><font color="red">*</font></td>
           	  	<td>Borang I?</td>
           	  	<td>:</td>
                <td>
                	<input name="sorFlagSegera" $checkSA type="radio" value="1" />Ya
           	  		&nbsp;&nbsp;<input name="sorFlagSegera" $checkSB type="radio" value="2" />Tidak
           	  		<!-- <div style="display:none"></div> -->
           	  		&nbsp;&nbsp;<input name="sorFlagSegera" $checkSC type="radio" value="3" />Sebahagian
           	  	</td>
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
                <td><textarea name="txtTujuan" id="txtTujuan" cols="60%" rows="4" >$!txtTujuan</textarea></td>
            </tr>
            
            #if($userIdNeg=='2' || $userIdNeg=='5')
            <tr>
         	  	<td valign="top"><font color="red">*</font></td>
           	   	<td valign="top">Project Name</td>
           	   	<td valign="top">:</td>
                <td><textarea name="txtTujuanBI" id="txtTujuanBI" cols="60%" rows="4" >$!txtTujuanBI</textarea></td>
            </tr>
            #end
            
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
         		<td><font color="red">*</font></td>
            	<td>Tarikh Daftar Permohonan Lengkap</td>
            	<td>:</td>
                <td><input name="txdPermohonanLengkap" id="txdPermohonanLengkap" value="$!txdPermohonanLengkap" size="11" type="text" onblur="check_date(this)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdPermohonanLengkap',false,'dmy');">$!frmtdate</td>
            </tr>
           
            #if($id_status=="11" || $id_status=="113" || $id_status=="138")
            #if($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")
            <tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Pengesahan Pengarah Negeri</td>
            	<td>:</td>
                <td><input class="disabled" readonly name="txdTarikhPengesahan" id="txdTarikhPengesahan" value="$!txdTarikhPengesahan" size="11" type="text" onblur="check_date(this)" />
        		 <!--  <img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPengesahan',false,'dmy');">$!frmtdate --> </td>
            </tr>
            #end
            #end
         	
        </table>	
        
        <table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian3</td>
        	</tr>
        </table>
        
    </fieldset>

	#end

	#if($mode=="view")
	
		#set($flagSahkan = "")
		#set($flagJenisPermohonan = "")
		
		
	
		#if($onchange=="no")
			#foreach($data in $dataPermohonan)
			#set($lblNoPermohonan=$data.noPermohonan)
			#set($lblNoOnline=$data.no_permohonan_online)
			#set($lblNoFail=$data.no_fail)
            #set($no_jkptg=$data.no_jkptg)
			#set($txtNoRujukanPTG=$data.no_rujukan_ptg)
			#set($txtNoRujukanPTD=$data.no_rujukan_ptd)
			#set($txtNoRujukanUPT=$data.no_rujukan_upt)
			#set($txdTarikhPermohonan=$data.tarikh_permohonan)
			#set($txdTarikhPermohonanKjp=$data.tarikh_permohonan_kjp)
			
			#set($txtAlamat1=$data.alamat1)
   			#set($txtAlamat2=$data.alamat2)
   			#set($txtAlamat3=$data.alamat3)
			#set($txtPoskod=$data.poskod)
			#set($txdTarikhKehendaki=$data.tarikh_sahkan)
			#set($sorFlagPeruntukan=$data.flag_peruntukan)
  			#set($sorFlagSegera=$data.flag_segera)
			#set($txtTujuan=$data.tujuan)
			#set($txtRujukanKementerian=$data.no_rujukan_surat)
			#set($txdTarikhKehendaki=$data.tarikh_kehendaki)
   			#set($txdTarikhSurat=$data.tarikh_surat)
   			#set($sorJenisProjek=$data.flag_jenis_projek)
   			#set($flagPermohonan=$data.flag_jenispermohonan)
   			#set($txdPermohonanLengkap=$data.tarikh_lengkap)
   			#set($jumlahHakmilik=$data.jumlah_hakmilik)
   			
   			#set($sorJenisKodDaerah=$data.flag_jenis_kod_daerah)
   			
   			#set($flagJenisPermohonan = $data.no_flag_jenispermohonan)
   			
   			#set($lblSuburusan=$data.nama_suburusan)
   			#set($flagSahkan=$data.flag_semak)		
   			
   			<!-- untuk n9 shj [27042011] -->
   			#set($txtTujuanBI=$data.tujuan_bi)
   			
   			
   				#if($flagSahkan=="2")	
				#set($txdTarikhPengesahan=$data.tarikh_sahkan)
				#else
				#set($txdTarikhPengesahan="$currentDATE")
				#end
	
	
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
				<td>$!flagPermohonan</td>
            </tr>
		</table>
		
		#if($flagStatusOnline=="1")
			<div class="warning">
				PERMOHONAN ONLINE INI TELAH DIKEMBALIKAN
				#if($catatan_status_online!="")
				DENGAN ALASAN $!catatan_status_online
				#end		
				#if($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")
				<br/>
				<font color="blue"><i><b>&raquo;<a href="javascript:tolakPermohonanOnline('$!id_permohonan','no')"><font color="blue">kemaskini</font></a></b></i></font>
				#end
			</div>
		#end
		
    	<table width="100%"  cellpadding="0" border="0">
        
            <tr>
            	<td width="1%">&nbsp;</td>
            	<td width="25%">Bil. Permohonan</td>
            	<td width="1%">:</td>
            	<td width="73%">$!lblNoPermohonan</td>            	
            </tr>
           
           	<tr>
           		<td>&nbsp;</td>
            	<td>No. Fail Permohonan</td>
            	<td>:</td>
            	<td>
                
                
                 <ul id="sddmheader"  >
    <li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()"><font color="blue">$!lblNoFail</font>        
        </a> 
        <div id="m1" 
            onmouseover="mcancelclosetime()" 
            onmouseout="mclosetime()" >   
          
            
           #foreach ($list in $list_sub_header)
         <a href="javascript:paparPerjalananFail('$list.ID_PERMOHONAN','$list.ID_STATUS','$list.ID_FAIL','$list.TARIKH_PERMOHONAN','$list.FLAG_JENISPERMOHONAN','$list.ID_SUBURUSAN','$!list.FLAG_SEGERA','${session.getAttribute('_portal_role')}')" >$list.STATUS</a>
            #end
           
            
        </div>
    </li>
   </ul>
	<div style="clear:both"></div>
                
                
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
            
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTG</td>
            	<td>:</td>
                <td><input type="text" $disability $disabilityx name="txtNoRujukanPTG" id="txtNoRujukanPTG" value="$!txtNoRujukanPTG" maxlength="100" size="37" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();doCheckExistNoFailUpdate('ptg',this.value)" ></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTD</td>
            	<td>:</td>
                <td><input type="text" $disability $disabilityx name="txtNoRujukanPTD" value="$!txtNoRujukanPTD" maxlength="100" size="37" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();doCheckExistNoFailUpdate('ptd',this.value)" ></td>
            </tr>
           
           	<tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan UPT</td>
            	<td>:</td>
                <td><input type="text" $disability $disabilityx name="txtNoRujukanUPT" value="$!txtNoRujukanUPT" maxlength="100" size="37" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();doCheckExistNoFailUpdate('upt',this.value)" ></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>Urusan</td>
            	<td>:</td>
            	<td>$!lblSuburusan</td>
            </tr>
       
            <tr>
            	<td><font color="red">$!M</font></td>
            	<td>Tarikh Permohonan </td>
            	<td>:</td>
            	 <td><input name="txdTarikhPermohonan" $disability $disabilityx  value="$!txdTarikhPermohonan" size="11" id="txdTarikhPermohonan" type="text" onblur="check_date(this);getJenisKodDaerah('$!userIdNeg','$!mode','$!isEdit')" />
       		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">$!frmtdate#end</td>
            </tr>
            
             #if ($currentStatus =="138" || $txdTarikhPermohonanKjp != "" )
            	<tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Permohonan KJP</td>
            	<td>:</td>
                <td><input type="text" class="disabled" readonly name="txdTarikhPermohonanKjp" value="$!txdTarikhPermohonanKjp" id="txdTarikhPermohonanKjp"  size="11" /></td>
            </tr>
            #end
     
     		#if($sorJenisKodDaerah=="1")
         		#set($checkJK1="checked")
         		#set($checkJK2="")
         	#elseif($sorJenisKodDaerah=="2")
         		#set($checkJK1="")
         		#set($checkJK2="checked")
         	#else
         		#set($checkJK1="")
         		#set($checkJK2="")
         	#end
         	
         	
         	#if($userIdNeg=="10")
     		<tr>
         		<td>&nbsp;</td>
            	<td>Jenis Kod Daerah</td>
            	<td>:</td>
                <td>
                	<input name="sorJenisKodDaerah" $disability1 $disabilityx $!checkJK1 type="radio" value="1" onchange="onchangeJenisKodDaerah('$!mode','$!isEdit')" />PTG (Sebelum 01/10/2010)
                	<input name="sorJenisKodDaerah" $disability1 $disabilityx $!checkJK2 type="radio" value="2" onchange="onchangeJenisKodDaerah('$!mode','$!isEdit')" />SPTB (Selepas 01/10/2010)
                </td> 
            </tr>
     		#end
     
        </table> 
      
    </fieldset>

<br/>
     
	<fieldset id="middle">
	<legend><strong>Maklumat Agensi / KJP</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
        	
        	<tr>
        		<td width="1%"><font color="red">$!M</font></td>
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
                <td><input type="text" class="disabled" readonly name="txtPoskod" size="4" onkeyup="validateNumber(this,this.value);"  value="$!txtPoskod" maxlength="5" id="txtPoskod" /></td>
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
            
            #if($sorFlagSegera=="1")
         		#set($checkSA="checked")
         		#set($checkSB="")
         		#set($checkSC="")
         	#elseif($sorFlagSegera=="2")
         		#set($checkSA="")
         		#set($checkSB="checked")
         		#set($checkSC="")
         	#elseif($sorFlagSegera=="3")
         		#set($checkSA="")
         		#set($checkSB="")
         		#set($checkSC="checked")
         	#else
         		#set($checkSA="")
         		#set($checkSB="")
         		#set($checkSC="")
         	#end
         	
            <tr>
            	<td><font color="red">$!M</font></td>
           	  	<td>Borang I?</td>
           	  	<td>:</td>
                <td>
                	<input name="sorFlagSegera" $disability1 $checkSA type="radio" value="1" />Ya
           	  		&nbsp;&nbsp;<input name="sorFlagSegera" $disability1 $checkSB type="radio" value="2" />Tidak
           	  		<!-- <div style="display:none"></div> -->
           	  		&nbsp;&nbsp;<input name="sorFlagSegera" $disability1 $checkSC type="radio" value="3" />Sebahagian          	  		
           	  	</td>
            </tr> 
         	
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
                <td><textarea name="txtTujuan" $disability $disabilityx id="txtTujuan" cols="60%" rows="4" >$!txtTujuan</textarea></td>
            </tr>
         	
         	#if($userIdNeg=='2' || $userIdNeg=='5')
         	<tr>
         	  	<td valign="top"><font color="red">$!M</font></td>
           	   	<td valign="top">Project Name</td>
           	   	<td valign="top">:</td>
                <td><textarea name="txtTujuanBI" $disability $disabilityx id="txtTujuanBI" cols="60%" rows="4" >$!txtTujuanBI</textarea></td>
            </tr>
            #end
            
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
            
            <tr><td colspan="4">&nbsp;</td></tr>
            
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
            	<td><font color="red">$!M</font></td>
            	<td>Tarikh Daftar Permohonan Lengkap</td>
            	<td>:</td>
                <td><input name="txdPermohonanLengkap" $disability $disabilityx id="txdPermohonanLengkap" value="$!txdPermohonanLengkap" size="11" type="text" onblur="check_date(this)" />
       		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdPermohonanLengkap',false,'dmy');">$!frmtdate#end</td>
            </tr>
         
            #if($id_status=="11" || $id_status=="113" || $id_status=="138")
            #if($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")
            <tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Pengesahan Pengarah Negeri</td>
            	<td>:</td>
                <td><input class="disabled" readonly name="txdTarikhPengesahan" $disability $disabilityx id="txdTarikhPengesahan" value="$!txdTarikhPengesahan" size="11" type="text" onblur="check_date(this)" />
        		 	<!--  #if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPengesahan',false,'dmy');">$!frmtdate#end  -->
       		 	</td>
            </tr>
            #end
                 #end
         	
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
	
	<br/>
	
	<input name="tabId" type="hidden" id="tabId" value="$!selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">
      
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(0);" tabindex="1">Maklumat Tanah Terlibat</li>
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(1);" tabindex="1">Senarai Dokumen</li>
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(2);" tabindex="1">Senarai Semakan</li>
      </ul>
      
      <div class="TabbedPanelsContentGroup">
      
      	<!-- START TAB 1 -->
        <div class="TabbedPanelsContent">
        	<fieldset>
        	<legend><strong>Maklumat Tanah Terlibat</strong>
           
            #if($ModuleName=="ekptg.view.ppt.FrmPermohonanUPTSek8" || $ModuleName=="ekptg.view.ppt.FrmUPTSek8Hakmilik")
            #end
      	<input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambahHakmilik('$!flag_subjaket');">
    	
            
            #if($!no_jkptg == "")
             <input  name="cmdPupupHakmilik" id="cmdPupupHakmilik" onClick="popupGetHakmilik('$id_permohonan','8')" type="button" value="POPUP SALIN HAKMILIK SEKSYEN 4" />
             #end
             </legend>
             
             <a href="javascript:popupCarianHakmilik('$id_permohonan','daftar_sek8')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
   
             

                <!-- Fungsi carian by no.lot/pt/nama pb -->
                <!--#parse("app/ppt/frmCarianListHMSek8.jsp")-->
                
                <!-- List Hakmilik -->
                <!--#parse("app/ppt/frmSeksyen8ListHM_new.jsp")-->
                     	
          	</fieldset>	
          	
          	<table width="100%" border="0">
				<tr>
					<td>$!perhatian6</td>
				</tr>
			</table>
          	
        </div>
        
        <!-- END TAB 1 -->
        
        
        <div class="TabbedPanelsContent">
        	<fieldset>
        		<legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>
                
                <a href="javascript:popupCarianDokumen('$id_permohonan','daftar_sek8')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
   <!--

				<table width="100%"  cellpadding="0" border="0">
                	<tr>
                    	<td colspan="4"><input type="button" name="cmdTambah2" value ="Tambah" onClick="javascript:tambahDokumen('$id_permohonan');"></td>
                    </tr>
                </table>
                
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                    <tr class="table_header">
                    	<td width="3%" align="center"><b>No</b></td>
                    	<td width="27%"><b>Nama Dokumen</b></td>
                        <td width="34%"><b>Keterangan</b></td>
                        <td width="30%"><b>Dokumen Sokongan</b></td>
                        #if($listD_size!=0)
                        <td width="6" align="center"><b>&nbsp;</b></td>
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
                       	<td class="$row"><a href="javascript:papar_Lampiran('$!listD.id_Dokumen')"><font color="blue">$listD.nama_Fail</font></a></td>
                    	#if($listD_size!=0)
                    	<td class="$row" align="center"><input type="button" name="cmdHapusDoc" value ="Hapus" onClick="hapusDokumen('$!listD.id_Dokumen')"></td>	
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
            
            <table width="100%" border="0">
				<tr>
					<td>$!perhatian6</td>
				</tr>
			</table>
			-->
            
        
        </fieldset>
      	</div>
 	
        <div class="TabbedPanelsContent">
    	
    	#set ($checked1 = "")
		#set ($checked2 = "")
		#set ($checked3 = "")
		#set ($checked4 = "")
		#set ($checked5 = "")
		#set ($checked6 = "")
		#set ($checked7 = "")
		#set ($checked8 = "")
    	
    	
    	#foreach($List in $senaraiSemakan)   	
    	#if ($List.semak1 == "1")#set ($checked1 = "checked")#end		
		#if ($List.semak2 == "1")#set ($checked2 = "checked")#end		
		#if ($List.semak3 == "1")#set ($checked3 = "checked")#end			
		#if ($List.semak4 == "1")#set ($checked4 = "checked")#end		
		#if ($List.semak5 == "1")#set ($checked5 = "checked")#end		
		#if ($List.semak6 == "1")#set ($checked6 = "checked")#end		
		#if ($List.semak7 == "1")#set ($checked7 = "checked")#end
		#if ($List.semak8 == "1")#set ($checked8 = "checked")#end
    	#end


		#if($id_status=="11" || $id_status=="113" || $id_status=="138")
			#set($disCheck="")
			#set($namecb1="cbsemaks1")
			#set($namecb2="cbsemaks2")
			#set($namecb6="cbsemaks6")
		#else
			#set($disCheck="disabled")
			#set($namecb1="cbsemaks1x")
			#set($namecb2="cbsemaks2x")
			#set($namecb6="cbsemaks6x")
			<input type="hidden" name="cbsemaks1" id="cbsemaks1" value="1">
			<input type="hidden" name="cbsemaks2" id="cbsemaks2" value="1">
			<input type="hidden" name="cbsemaks6" id="cbsemaks6" value="1">
		#end
		    	
		<fieldset>
		<legend><strong>Senarai Semakan</strong></legend>
	
		<br/>

   		<table width="100%" cellpadding="0" cellspacing="0">
    		<tr>
            	<td width="5%">&nbsp;</td>
            	<td width="1%"><font color="red">$!M</font></td>
    			<td width="94%"><input type="checkbox" name='$!namecb1' $disCheck $disability1 value="1" id='$!namecb1' $checked1>
     				1. Pelan Pengambilan Tanah yang lengkap.</td>
  			</tr>
        
  			<tr>
    			<td>&nbsp;</td>
    		 	<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name='$!namecb2' $disCheck $disability1 value="1" id='$!namecb2' $checked2 >
     		 		2. Sijil Carian Rasmi/ Persendirian yang terkini.</td>
  			</tr>
  
  			<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="checkbox" name="cbsemaks3" $disability1 value="1" id="cbsemaks3" $checked3 >
      				3. Ulasan dari Jabatan-Jabatan Teknikal. </td>
  			</tr>
 
  			<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="checkbox" name="cbsemaks4" $disability1 value="1" id="cbsemaks4" $checked4 >
      				4. Ulasan dari Jabatan Alam Sekitar.</td>
  			</tr>
  		#if($userIdNeg=="3" || $userIdNeg=="11")
   
  			<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="checkbox" name="cbsemaks5" $disability1 value="1" id="cbsemaks5" $checked5 >
      				5. Persetujuan Jawatankuasa Pembangunan Daerah atau Jawatankuasa seumpamanya. </td>
  			</tr>
  			
  			<tr>
    			<td>&nbsp;</td>
    			<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name='$!namecb6' $disCheck $disability1 value="1" id='$!namecb6' $checked6 >
      				6. Pengesahan peruntukan pampasan yang mencukupi termasuk kos penandaan dan lain-lain kos.</td>
  			</tr>
  
  			<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
   				<td><input type="checkbox" name="cbsemaks7" $disability1 value="1" id="cbsemaks7" $checked7 >
      				7. Surat Perakuan segera (Borang I)</td>
  			</tr>
  			
  			<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="checkbox" name="cbsemaks8" $disability1 value="1" id="cbsemaks8" $checked8 >
      				8. Surat iringan permohonan rasmi dari agensi. </td> 
  			</tr>
  		#end
  		
  		#if($userIdNeg!="3" && $userIdNeg!="11")
  		
  		<tr>
    			<td>&nbsp;</td>
    			<td><font color="red">$!M</font></td>
    			<td><input type="checkbox" name='$!namecb6' $disCheck $disability1 value="1" id='$!namecb6' $checked6 >
      				5. Pengesahan peruntukan pampasan yang mencukupi termasuk kos penandaan dan lain-lain kos.</td>
  			</tr>
  
  			<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
   				<td><input type="checkbox" name="cbsemaks7" $disability1 value="1" id="cbsemaks7" $checked7 >
      				6. Surat Perakuan segera (Borang I)</td>
  			</tr>
  			
  			<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="checkbox" name="cbsemaks8" $disability1 value="1" id="cbsemaks8" $checked8 >
      				7. Surat iringan permohonan rasmi dari agensi. </td> 
  			</tr>
  		
  		#end
  			
  			
    	</table>
    
		</fieldset>
    
    		<table width="100%" border="0">
				<tr>
					<td>$!perhatian6</td>
				</tr>
			</table>
			
        </div>
        
  </div> 
  </div> 
	
	#end
	
	

	<table width="100%" border="0">
		<tr align="center">
			<td>
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPendaftaran('$!id_permohonan','$!mode','$!userIdNeg')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")

					#if(($flagStatusOnline=="" || $flagStatusOnline=="0") && ($id_status=="138" && ($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")) )
					<input type="button" name="cmdTolakPermohonan" value="Kembalikan Permohonan" onClick="javascript:tolakPermohonanOnline('$id_permohonan','yes')">
					#end
				
					#if($id_status=="11" || $id_status=="113" || $id_status=="138")
	        			#if(($checked1 == "checked" && $checked2 == "checked" && $checked6 == "checked" ) && $saiz_listTanah!=0)
	          				#if($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")
	          					#if($flagStatusOnline=="" || $flagStatusOnline=="0")
	          					<input name="cmdSah" type="button" value="Sahkan" onClick="sah('$id_permohonan')">
	          					#end
	          				#else
	          					#if($flagSahkan!="1")
	          					<img src="../img/emel.gif" title="Minta pengesahan juga akan dihantar melalui emel" >
	          					<input name="cmdSah" type="button" value="Hantar Untuk Pengesahan" onClick="updateFlagSah('$id_permohonan')">
	          					#end
	          				#end
	          			#end
          			#end
          			
          			
					#if(($flagSahkan!="1" && $flagSahkan!="2") 
					||($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt"))
                    
                    
                    
                    
					<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="javascript:kemaskiniPendaftaran('$!id_permohonan')">
                 		#if($ID_NEGERIPROJEK == "4")     
                  	<input type="button" name="cmdpopupetanah" value="Integrasi e-Tanah Melaka(Hantar Permohonan)" onClick="eTanahPermohonan('$id_fail','$id_permohonan','WartaS8','')">
                    	#end
                    <!--
                    <input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Pelan untuk Charting)" onClick="popupEtanah('$id_fail','$id_permohonan','hantarPelanChartingS8','')">
                    -->
                    
					#end
					
					#if($id_status!="11" && $id_status!="113" && $id_status!="138")
          				<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
          			#end
				#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPendaftaran('$!id_permohonan','$!mode','$!userIdNeg')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_permohonan')">
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
	
<input type="hidden" name="jenisNofail">
<input type="hidden" name="curNoFail">
<input type="hidden" name="valSimpanNoRujukanPTG" value="$!valSimpanNoRujukanPTG">
<input type="hidden" name="valSimpanNoRujukanPTD" value="$!valSimpanNoRujukanPTD">
<input type="hidden" name="valSimpanNoRujukanUPT" value="$!valSimpanNoRujukanUPT">
	
<input type="hidden" name="command2">
<input type="hidden" name="command3">	
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="jumlahHakmilik" value="$!jumlahHakmilik">
<input type="hidden" name="id_hakmilik">
<input type="hidden" name="id_senaraisemak" value="$!id_senaraisemak">
<input type="hidden" name="id_user" value="$!id_user">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<input type="hidden" name="ResultAdd" value="$!ResultAdd">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


#if($showAlertDuplicate=="yes")
<script>
alert("Telah wujud no. fail "+'$!showNoRujukan'+" di dalam rekod");
</script>
#end 
	
<script>

function HandlePopup_from_copy_hakmilik(id_permohonan) {
	//document.${formName}.ScreenLocation.value = "top";
	//document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}


function popupGetHakmilik(id_permohonan,jenis_seksyen)
{
	var id_negeri = document.${formName}.socNegeriProjek.value;
	var id_daerah = document.${formName}.socDaerah.value;
	
	//alert("id_negeri :"+id_negeri);
	//alert("id_daerah :"+id_daerah);
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupHakmilik?id_permohonan="+id_permohonan+"&id_negeri="+id_negeri+"&id_daerah="+id_daerah+"&jenis_seksyen="+jenis_seksyen;
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

function popupCarianDokumen(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}


	function eTanahPermohonan(id_fail,id_permohonan,jenis_skrin,command) {	
		var url = "../x/${securityToken}/ekptg.view.integrasi.etanah.PermohonanPengambilan?idFail="+id_fail+"&idPermohonan="+id_permohonan+"&jenisSkrin="+jenis_skrin+"&command="+command;	
	    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		
	}

function popupEtanah(id_fail,id_permohonan,jenis_skrin,command) {

	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

function onchangeJenisKodDaerah(mode,isEdit) {

	if(mode=="new" || (mode=="view" && isEdit=="yes")){
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.CursorPoint.value = "";

		if(mode=="new"){
			document.${formName}.command.value = "pendaftaran";
			document.${formName}.command2.value = "onchangeJenisKodDaerah";
		}else{
			document.${formName}.command.value = "semakPendaftaran";
			document.${formName}.command2.value = "kemaskiniPendaftaran";
			document.${formName}.command3.value = "onchangeJenisKodDaerah";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
		document.${formName}.submit();
	}

}
function getJenisKodDaerah(userIdNeg,mode,isEdit) {

	var dat1=document.${formName}.txdTarikhPermohonan;

	if(dat1.value!="" && userIdNeg=="10" && (mode=="new" || (mode=="view" && isEdit=="yes"))){
		if(isDate(dat1.value)==false){
			dat1.focus()
			return;
		}else{

			document.${formName}.ScreenLocation.value = "top";
			document.${formName}.CursorPoint.value = "";

			if(mode=="new"){
				document.${formName}.command.value = "pendaftaran";
				document.${formName}.command2.value = "getJenisKodDaerah";
			}else{
				document.${formName}.command.value = "semakPendaftaran";
				document.${formName}.command2.value = "kemaskiniPendaftaran";
				document.${formName}.command3.value = "getJenisKodDaerah";
			}
			
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
			document.${formName}.submit();
			
		/*
			//tarikh permohonan
			var TP  = document.getElementById("txdTarikhPermohonan").value;
			var dt1   = parseInt(TP.substring(0,2),10);
		   	var mon1  = parseInt(TP.substring(3,5),10)-1;
		   	var yr1   = parseInt(TP.substring(6,10),10);
		   	var datePrmhn = new Date(yr1, mon1, dt1);

		  	//tarikh change kod daerah
			var TKD  = "01/10/2010";
			var dt2   = parseInt(TKD.substring(0,2),10);
		   	var mon2  = parseInt(TKD.substring(3,5),10)-1;
		   	var yr2   = parseInt(TKD.substring(6,10),10);
			var dateKod = new Date(yr2, mon2, dt2);

		   	//sebelum 01/10/2010
		   	if(datePrmhn < dateKod){
		   		document.${formName}.sorJenisKodDaerah[0].checked=true;
		   		document.${formName}.sorJenisKodDaerah[1].checked=false;
		   	//selepas 01/10/2010		
			}else{
				document.${formName}.sorJenisKodDaerah[0].checked=false;
		   		document.${formName}.sorJenisKodDaerah[1].checked=true;
			}
		*/	
		}
	}

}
function kembaliScreenUtama(idpermohonan) {
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function tolakPermohonanOnline(id_permohonan,formnew) {

	var w = "400";
	var h = "250";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmPopupTolakPermohonan?id_permohonan="+id_permohonan+"&formnew="+formnew+"&jenisTolak=internal";
	
	var hWnd = window.open(url, "Permohonan Online Dikembalikan", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}
function doCheckExistNoFail(flagNoFail,curNoFail) {

	if(curNoFail.trim() != "" && ('$!mode' == "new" || ('$!mode' == "view" && '$!isEdit' == "yes") )){
		document.${formName}.ScreenLocation.value = "top";	
		document.${formName}.CursorPoint.value = "";
		document.${formName}.jenisNofail.value = flagNoFail;
		document.${formName}.curNoFail.value = curNoFail;
		document.${formName}.command.value = "pendaftaran";
		document.${formName}.command2.value = "doCheckExistNoFail";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8"; 
		document.${formName}.submit();	
	}
	
}
function doCheckExistNoFailUpdate(flagNoFail,curNoFail) {

	if(curNoFail.trim() != "" && ('$!mode' == "new" || ('$!mode' == "view" && '$!isEdit' == "yes") )){
		document.${formName}.ScreenLocation.value = "top";	
		document.${formName}.CursorPoint.value = "";
		document.${formName}.jenisNofail.value = flagNoFail;
		document.${formName}.curNoFail.value = curNoFail;
		document.${formName}.command.value = "semakPendaftaran";
		document.${formName}.command2.value = "kemaskiniPendaftaran";
		document.${formName}.command3.value = "doCheckExistNoFailUpdate";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8"; 
		document.${formName}.submit();	
	}
	
}

function cetakSuratKPTG(idpermohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=SuratKpdPTGDariJKPTGIbuPejabat&selectNoFail=yes&flagShowTarikhCetak=yes";	
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

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=LampiranASek8&flagReport=S&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}
function sah(id_permohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "sah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8"; 
	document.${formName}.submit();	
}
function updateFlagSah(id_permohonan) {	
	
	if(document.${formName}.txdPermohonanLengkap.value == ""){
		alert("Sila masukkan \"Tarikh Daftar Permohonan Lengkap\" terlebih dahulu.");
  		document.${formName}.txdPermohonanLengkap.focus(); 
		return;
	}
	document.${formName}.ScreenLocation.value = "bottom";	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "updateFlagSah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8"; 
	document.${formName}.submit();	
}
function viewHM(id_hakmilik){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function tambahHakmilik(flagSubjaket) {

	if(flagSubjaket=="1"){
		alert("No Subjaket perlu dijana semula sekiranya ada penambahan hakmilik");
	}
	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8"; 
	document.${formName}.submit();
}
function hapusDokumen(iddokumen) {
	
	document.${formName}.ScreenLocation.value = "middle";
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8&command=hapusDokumen&id_dokumen="+iddokumen;
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8"; 
	document.${formName}.submit();
}
function doChangeKementerianUpdate() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socAgensi";
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	document.${formName}.command3.value = "doChangeKementerianUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function doChangeProjekNegeriUpdate() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socDaerah";
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	document.${formName}.command3.value = "doChangeProjekNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function batalKemaskini(idpermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function kemaskiniPendaftaran(idpermohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function simpanPendaftaran(idpermohonan,mode,userIdNeg) {

	var dat1=document.${formName}.txdTarikhSurat;
	var dat2=document.${formName}.txdTarikhKehendaki;
	//var dat3=document.${formName}.txdTarikhPermohonan;
	//var dat4=document.${formName}.txdPermohonanLengkap;

	var radioSelected2 = false;
	for (j = 0;  j < ${formName}.sorFlagSegera.length;  j++){
		if (${formName}.sorFlagSegera[j].checked)
		radioSelected2 = true;
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

   	
   	if(document.${formName}.valSimpanNoRujukanPTG.value == "no"){
		alert("Telah wujud no. fail rujukan PTG yang sama di dalam rekod");
		return;
	}else if(document.${formName}.valSimpanNoRujukanPTD.value == "no"){
		alert("Telah wujud no. fail rujukan PTD yang sama di dalam rekod");
		return;
	}else if(document.${formName}.valSimpanNoRujukanUPT.value == "no"){
		alert("Telah wujud no. fail rujukan UPT yang sama di dalam rekod");
		return;
	}
   /*	else if(document.${formName}.txdTarikhPermohonan.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan Di Terima\" terlebih dahulu.");
  		document.${formName}.txdTarikhPermohonan.focus(); 
		return;
	}
  	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}*/
   	else if(document.${formName}.socKementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.socKementerian.focus(); 
		return;
	}
	else if (!radioSelected){
		alert("Sila pilih \"Peruntukan\" terlebih dahulu.");
		return;
	}
	else if (!radioSelected2){
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
	else if((userIdNeg=="2" || userIdNeg=="5") && document.${formName}.txtTujuanBI.value == ""){
		alert("Sila masukkan \"Project Name\" terlebih dahulu.");
  		document.${formName}.txtTujuanBI.focus(); 
		return;
	}
	else if(dateKehendaki < dateKJP){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh surat KJP.");
	 	document.${formName}.txdTarikhKehendaki.focus();
	 	return;
	}
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
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
		document.${formName}.submit();
	}
	
}
function doChangeProjekNegeri() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socDaerah";
	document.${formName}.command.value = "pendaftaran";
	document.${formName}.command2.value = "doChangeProjekNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function doChangeKementerian() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socAgensi";
	document.${formName}.command.value = "pendaftaran";
	document.${formName}.command2.value = "doChangeKementerian";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.command.value = "clearData";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.carianNoLot.value = "";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek8";
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

#parse("app/ppt/frmPPTHeader_script.jsp")