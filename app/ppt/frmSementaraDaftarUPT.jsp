
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


#parse("app/ppt/SementaraPaging.jsp")

<!-- Portal Role -->
#set($portal_role = "${session.getAttribute('myrole')}")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<br/>

<fieldset id="top">
<legend><strong>Daftar Pendudukan Atau Penggunaan Sementara</strong></legend>

	#if($mode=="new")
	
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
                <td><input type="text" name="txtNoRujukanPTG" id="txtNoRujukanPTG" value="$!txtNoRujukanPTG" maxlength="30" size="37" ></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTD</td>
            	<td>:</td>
                <td><input type="text" name="txtNoRujukanPTD" value="$!txtNoRujukanPTD" maxlength="30" size="37" ></td>
            </tr>
           
            <tr>
            	<td>&nbsp;</td>
            	<td>Urusan</td>
            	<td>:</td>
            	<td>PENDUDUKAN ATAU PENGGUNAAN SEMENTARA</td>
          	</tr>
            
            <tr>
            	<td><font color="red">*</font></td>
            	<td>Tarikh Permohonan Diterima</td>
            	<td>:</td>
                <td><input name="txdTarikhPermohonan" value="$!txdTarikhPermohonan" size="11" id="txdTarikhPermohonan" type="text" onblur="check_date(this);getJenisKodDaerah('$!userIdNeg','$!mode','$!isEdit')" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">$!frmtdate</td>
            </tr>
            
             #if ($currentStatus =="138" || $txdTarikhPermohonanKjp != "" )
           	<tr>
           		<td>&nbsp;</td>
            	<td>Tarikh Permohonan KJP Diterima</td>
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
         	
         	
         	#if($userIdNeg=="10")
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
     		
     		<!-- PPT-41 -->
            <tr>
            	<td><font color="red">*</font></td>
            	<td>Tarikh Pendudukan Mula</td>
            	<td>:</td>
                <td><input name="txdTarikhPendudukanMula" value="$!txdTarikhPendudukanMula" size="11" id="txdTarikhPendudukanMula" type="text"  onblur="monthsDiff(); check_date(this);" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPendudukanMula',false,'dmy');">$!frmtdate</td>
            </tr>
            <tr>
            	<td><font color="red">*</font></td>
            	<td>Tarikh Pendudukan Akhir</td>
            	<td>:</td>
                <td><input name="txdTarikhPendudukanAkhir" value="$!txdTarikhPendudukanAkhir" size="11" id="txdTarikhPendudukanAkhir" type="text"  onblur="monthsDiff(); check_date(this);" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPendudukanAkhir',false,'dmy');">$!frmtdate</td>
            </tr>
           	<tr>
           		<td>&nbsp;</td>
            	<td>Tempoh Pendudukan (Bulan)</td>
            	<td>:</td>
            	<td><input type="text" name="txtTempohPendudukan" value="$!txtTempohPendudukan" size="15" class="disabled" readonly></td>
            </tr>
     		<!-- END PPT-41 -->
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
                <td><input type="text" class="disabled" readonly name="txtAlamat1" value="$!txtAlamat1" id="txtAlamat1" maxlength="80" size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="3">&nbsp;</td>
                <td><input class="disabled" readonly type="text" name="txtAlamat2" value="$!txtAlamat2" id="txtAlamat2" maxlength="80" size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="3">&nbsp;</td>
                <td><input class="disabled" readonly type="text" name="txtAlamat3" value="$!txtAlamat3" id="txtAlamat3" maxlength="80" size="60" /></td>
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
            	<td width="25%">Peruntukan</td>
            	<td width="1%">:</td>
                <td width="73%">
                	<input name="sorFlagPeruntukan" $checkPA type="radio" value="1" />Ada
                	<input name="sorFlagPeruntukan" $checkPB type="radio" value="2" />Tiada                </td> 
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
                <td><textarea name="txtTujuan" id="txtTujuan" cols="40%" rows="4" >$!txtTujuan</textarea></td>
            </tr>
         	
         	<tr>
         	 	<td>&nbsp;</td>
           	    <td>No. Ruj Surat KJP</td>
           	    <td>:</td>
                <td><input type="text" name="txtRujukanKementerian" id="txtRujukanKementerian" maxlength="50" value="$!txtRujukanKementerian" size="23" /></td>
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
        </table>	
</fieldset>
	#end


	#if($mode=="view")
	
		#set($flagSahkan = "")
		
		#if($onchange=="no")
			#foreach($data in $dataPermohonan)
			#set($lblNoPermohonan=$data.noPermohonan)
			#set($lblNoFail=$data.no_fail)
			#set($txtNoRujukanPTG=$data.no_rujukan_ptg)
			#set($txtNoRujukanPTD=$data.no_rujukan_ptd)
			#set($txdTarikhPermohonan=$data.tarikh_permohonan)
			#set($txdTarikhPermohonanKjp=$data.tarikh_permohonan_kjp)
			#set($txtAlamat1=$data.alamat1)
   			#set($txtAlamat2=$data.alamat2)
   			#set($txtAlamat3=$data.alamat3)
			#set($txtPoskod=$data.poskod)
			#set($sorFlagPeruntukan=$data.flag_peruntukan)
			#set($txtTujuan=$data.tujuan)
			#set($txtRujukanKementerian=$data.no_rujukan_surat)
			#set($txdTarikhKehendaki=$data.tarikh_kehendaki)
   			#set($txdTarikhSurat=$data.tarikh_surat)
   			#set($flagPermohonan=$data.flag_jenispermohonan)
   			#set($lblNoOnline=$data.no_permohonan_online)
 
   			<!-- PPT-41 -->
   			#set($txdTarikhPendudukanMula=$data.tarikh_pendudukan_mula)
   			#set($txdTarikhPendudukanAkhir=$data.tarikh_pendudukan_akhir)
   			#set($txtTempohPendudukan=$data.tempoh_pendudukan)
   			
   			#set($sorJenisKodDaerah=$data.flag_jenis_kod_daerah)
  
   			#set($lblSuburusan=$data.nama_suburusan)
   			#set($flagSahkan=$data.flag_semak)		
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
				PERMOHONAN ONLINE INI TELAH DITOLAK
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
           		<td>No. Permohonan Online</td>
           		<td>:</td>
            	<td><b>$!lblNoOnline</b></td>
            </tr>
            #end
           
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTG</td>
            	<td>:</td>
                <td><input type="text" $disability $disabilityx name="txtNoRujukanPTG" id="txtNoRujukanPTG" value="$!txtNoRujukanPTG" maxlength="30" size="37" ></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
            	<td>No. Rujukan PTD</td>
            	<td>:</td>
                <td><input type="text" $disability $disabilityx name="txtNoRujukanPTD" value="$!txtNoRujukanPTD" maxlength="30" size="37" ></td>
            </tr>
           
            <tr>
            	<td>&nbsp;</td>
            	<td>Urusan</td>
            	<td>:</td>
            	<td>$!lblSuburusan</td>
            </tr>
            
            <tr>
            	<td><font color="red">$!M</font></td>
            	<td>Tarikh Permohonan Diterima</td>
            	<td>:</td>
                <td><input name="txdTarikhPermohonan" $disability $disabilityx  value="$!txdTarikhPermohonan" size="11" id="txdTarikhPermohonan" type="text" onblur="check_date(this);getJenisKodDaerah('$!userIdNeg','$!mode','$!isEdit')" />
       		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">$!frmtdate#end</td>
            </tr>
            
             #if ($currentStatus =="138" || $txdTarikhPermohonanKjp != "" )
            	<tr>
            	<td>&nbsp;</td>
            	<td>Tarikh Permohonan KJP Diterima</td>
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

     		<!-- PPT-41 -->
            <tr>
            	<td><font color="red">*</font></td>
            	<td>Tarikh Pendudukan Mula</td>
            	<td>:</td>
                <td><input name="txdTarikhPendudukanMula" $disability $disabilityx  value="$!txdTarikhPendudukanMula" size="11" id="txdTarikhPendudukanMula" type="text" onblur="check_date(this);monthsDiff();" />
       		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPendudukanMula',false,'dmy');">$!frmtdate#end</td>
            </tr>
            <tr>
            	<td><font color="red">*</font></td>
            	<td>Tarikh Pendudukan Akhir</td>
            	<td>:</td>
                <td><input name="txdTarikhPendudukanAkhir" $disability $disabilityx  value="$!txdTarikhPendudukanAkhir" size="11" id="txdTarikhPendudukanAkhir" type="text" onblur="check_date(this); monthsDiff();" />
       		 	#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPendudukanAkhir',false,'dmy');">$!frmtdate#end</td>
            </tr>
           	<tr>
           		<td>&nbsp;</td>
            	<td>Tempoh Pendudukan (Bulan)</td>
            	<td>:</td>
            	<td><input type="text" name="txtTempohPendudukan" value="$!txtTempohPendudukan" size="15" class="disabled" readonly></td>
            </tr>
     		<!-- END PPT-41 -->
     		
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
                <td><input type="text" class="disabled" readonly name="txtAlamat1" value="$!txtAlamat1" id="txtAlamat1" maxlength="80" size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="3">&nbsp;</td>
                <td><input class="disabled" readonly type="text" name="txtAlamat2" value="$!txtAlamat2" id="txtAlamat2" maxlength="80" size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="3">&nbsp;</td>
                <td><input class="disabled" readonly type="text" name="txtAlamat3" value="$!txtAlamat3" id="txtAlamat3" maxlength="80" size="60" /></td>
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
            	<td width="25%">Peruntukan</td>
            	<td width="1%">:</td>
                <td width="73%">
                	<input name="sorFlagPeruntukan" $disability1 $checkPA type="radio" value="1" />Ada
                	<input name="sorFlagPeruntukan" $disability1 $checkPB type="radio" value="2" />Tiada                </td> 
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
                <td><textarea name="txtTujuan" $disability $disabilityx id="txtTujuan" cols="40%" rows="4" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();">$!txtTujuan</textarea></td>
            </tr>
         	
         	<tr>
         	 	<td>&nbsp;</td>
           	    <td>No. Ruj Surat KJP</td>
           	    <td>:</td>
                <td><input type="text" $disability $disabilityx name="txtRujukanKementerian" id="txtRujukanKementerian" maxlength="50" value="$!txtRujukanKementerian" size="23" /></td>
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
        </table>	
</fieldset>
	
	<br/>
	
	<input name="tabId" type="hidden" id="tabId" value="$!selectedTab"/>
	
	<div id="TabbedPanels1" class="TabbedPanels">
      
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(0);" tabindex="1">Maklumat Tanah Terlibat</li>
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(1);" tabindex="1">Senarai Dokumen</li>       
      </ul>
      
      <div class="TabbedPanelsContentGroup">
      
      	<!-- START TAB 1 -->
        <div class="TabbedPanelsContent">
        	<fieldset>
        	<legend><strong>Maklumat Tanah Terlibat</strong> <input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambahHakmilik('$!flag_subjaket');"></legend>
            
             <a href="javascript:popupCarianHakmilik('$id_permohonan','daftar_sementara')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
   <!--

                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                	<tr>
                    	<td width="30%" align="left"><input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambahHakmilik('$!flag_subjaket');"></td>
    					<td width="70%" align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20" ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    			</table>
                
                #if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
                #end
                
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">     
                    
                    <tr class="table_header">
                  		<td align="center"><b>No</b></td>
                  		<td><b>No.Hakmilik</b></td>
                  		<td><b>FT</b></td>    
                  		<td><b>QT</b></td>             
                  		<td><b>Mukim/Pekan/Bandar</b></td>
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
                    	
                    	
                    <tr>
                		<td class="$row" align="center">$!listTanah.bil</td>
               		 	<td class="$row"><a href="javascript:viewHM('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.no_hakmilik</font></a></td>
                		<td class="$row">$!listTanah.no_lot</td>          
                		<td class="$row">$!listTanah.kod_lot$!listTanah.no_pt</td>     
                		<td class="$row">$!listTanah.nama_mukim</td>
                		<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
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
        </div>
        
        <!-- END TAB 1 -->
        
        
        
        
        <div class="TabbedPanelsContent">
        
        <fieldset>
        		<legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>
                
                <a href="javascript:popupCarianDokumen('$id_permohonan','daftar_sementara')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
                
                </fieldset>
                <br />
                <!--
                
        	<fieldset>
        		<legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>
                
                

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
            
            -->
        </div>
      	
  </div> 
  </div> 
	
	#end


	<table width="100%" border="0">
		<tr align="center">
			<td>
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPendaftaran('$!id_permohonan','$!mode')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
					
					#if(($flagStatusOnline=="" || $flagStatusOnline=="0") && ($id_status=="138" && ($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")) )
					<input type="button" name="cmdTolakPermohonan" value="Tolak Permohonan Online" onClick="javascript:tolakPermohonanOnline('$id_permohonan','yes')">
					#end
					
					#if($id_status=="11" || $id_status=="113" || $id_status=="138")
        			#if($saiz_listTanah!=0)
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
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPendaftaran('$!id_permohonan')">
					#end
					
					##if($id_status!="11" && $id_status!="113" && $id_status!="138")
          				<!--<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />-->
          			##end
				#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPendaftaran('$!id_permohonan','$!mode')">
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
        	<td ><a href="#" class="style2" onClick="javascript:cetakSuratKPTG('$!id_fail','$!nama_pengarah')"><font color="blue">Surat Iringan Kepada PTG</font></a></td>
      </tr>  
      <tr>
       	 	<td ><a href="#" class="style2" onClick="javascript:cetakLampiranA('$!id_fail','$!nama2Mukim')"><font color="blue">Lampiran A</font></a></td>
      </tr>   
    </table>
</fieldset>	
	
<input type="hidden" name="command2">
<input type="hidden" name="command3">	
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="id_hakmilik">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<input type="hidden" name="ResultAdd" value="$!ResultAdd">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>





<script>
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
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
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
			
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
			document.${formName}.submit();
		}
	}

}
function kembaliScreenUtama(idpermohonan) {
	document.${formName}.tabId.value = "0";
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function tolakPermohonanOnline(id_permohonan,formnew) {

	var w = "400";
	var h = "200";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmPopupTolakPermohonan?id_permohonan="+id_permohonan+"&formnew="+formnew;
	
	var hWnd = window.open(url, "Permohonan Online Ditolak", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}
function cetakSuratKPTG(idfail,namaPengarah) {
	var url = "../servlet/ekptg.report.ppt.SuratKpdPTGDariJKPTGIbuPejabat?idfail="+idfail+"&namaPengarah="+namaPengarah;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakLampiranA(idfail,namaMukim) {
	//var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idpermohonansimati="+idpermohonansimati+"&report=BorangDD&flagReport=B";
    var url = "../servlet/ekptg.report.ppt.LampiranASek8?idfail="+idfail+"&nama_mukim="+namaMukim;
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan"; 
	document.${formName}.submit();	
}
function updateFlagSah(id_permohonan) {	
	document.${formName}.ScreenLocation.value = "bottom";	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "updateFlagSah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan"; 
	document.${formName}.submit();	
}
function viewHM(id_hakmilik){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "viewHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function tambahHakmilik(flagSubjaket) {

	if(flagSubjaket=="1"){
		alert("No Subjaket perlu dijana semula sekiranya ada penambahan hakmilik");
	}
	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahHakmilik";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan"; 
	document.${formName}.submit();
}
function hapusDokumen(iddokumen) {
	
	document.${formName}.ScreenLocation.value = "middle";
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan&command=hapusDokumen&id_dokumen="+iddokumen;
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan"; 
	document.${formName}.submit();
}
function doChangeKementerianUpdate() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socAgensi";
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	document.${formName}.command3.value = "doChangeKementerianUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function doChangeProjekNegeriUpdate() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socDaerah";
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	document.${formName}.command3.value = "doChangeProjekNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function batalKemaskini(idpermohonan) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
//PPT-41
function kemaskiniPendaftaran(idpermohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "txtNoRujukanPTG";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.command2.value = "kemaskiniPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function simpanPendaftaran(idpermohonan,mode) {

	var dat1=document.${formName}.txdTarikhSurat;
	var dat2=document.${formName}.txdTarikhKehendaki;
	var dat3=document.${formName}.txdTarikhPermohonan;
	//PPT-41
	var dat4=document.${formName}.txdTarikhPendudukanMula;
	var dat5=document.${formName}.txdTarikhPendudukanAkhir;

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
   	
   	//tarikh pendudukan mula PPT-41
   	var Mula  = document.getElementById("txdTarikhPendudukanMula").value;
	var dt3   = parseInt(Mula.substring(0,2),10);
   	var mon3  = parseInt(Mula.substring(3,5),10);
   	var yr3   = parseInt(Mula.substring(6,10),10);
   	var datePendudukanMula = new Date(yr3, mon3, dt3);
	
    //tarikh pendudukan akhir PPT-41
   	var Akhir  = document.getElementById("txdTarikhPendudukanAkhir").value;
	var dt4   = parseInt(Mula.substring(0,2),10);
   	var mon4  = parseInt(Mula.substring(3,5),10);
   	var yr4   = parseInt(Mula.substring(6,10),10);
   	var datePendudukanAkhir = new Date(yr4, mon4, dt4);
   	
   	if(document.${formName}.txdTarikhPermohonan.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan\" terlebih dahulu.");
  		document.${formName}.txdTarikhPermohonan.focus(); 
		return;
	}
   	else if(document.${formName}.txdTarikhPendudukanMula.value == ""){ //PPT-41
		alert("Sila masukkan \"Tarikh Pendudukan Mula\" terlebih dahulu.");
  		document.${formName}.txdTarikhPermohonan.focus(); 
		return;
	}
   	else if(document.${formName}.txdTarikhPendudukanAkhir.value == ""){ //PPT-41
		alert("Sila masukkan \"Tarikh Pendudukan Akhir\" terlebih dahulu.");
  		document.${formName}.txdTarikhPermohonan.focus(); 
		return;
	}
   	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
   	else if(document.${formName}.socKementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.socKementerian.focus(); 
		return;
	}
	else if (!radioSelected){
		alert("Sila pilih \"Peruntukan\" terlebih dahulu.");
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
	else if (dat4.value!="" && isDate(dat4.value)==false)
	{ //PPT-41
		dat4.focus()
		return;
	}
	else if (dat5.value!="" && isDate(dat5.value)==false)
	{ //PPT-41
		dat5.focus()
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
			//alert(idpermohonan);
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
		document.${formName}.submit();
	}
	
}
function doChangeProjekNegeri() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socDaerah";
	document.${formName}.command.value = "pendaftaran";
	document.${formName}.command2.value = "doChangeProjekNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function doChangeKementerian() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.CursorPoint.value = "socAgensi";
	document.${formName}.command.value = "pendaftaran";
	document.${formName}.command2.value = "doChangeKementerian";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.command.value = "clearData";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.CursorPoint.value = "";
	document.${formName}.carianNoLot.value = "";
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakPendaftaran";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraPermohonan";
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


//PPT-41
function monthsDiff() {  

	var date1=document.${formName}.txdTarikhPendudukanMula.value;
	var date2=document.${formName}.txdTarikhPendudukanAkhir.value;
	
	var datearray1 = date1.split("/");
	var date1 = datearray1[1] + '/' + datearray1[0] + '/' + datearray1[2];
	var datearray2 = date2.split("/");
	var date2 = datearray2[1] + '/' + datearray2[0] + '/' + datearray2[2];
	
	var d1 = new Date(date1);
	var d2 = new Date(date2);
		
    var months;
    months = (d2.getFullYear() - d1.getFullYear()) * 12;
    months -= d1.getMonth();
    months += d2.getMonth();

    if(months > -1){
		document.${formName}.txtTempohPendudukan.value = months;
    }

}


</script>

<script type="text/javascript">
//var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>


#parse("app/ppt/frmPPTHeader_script.jsp")