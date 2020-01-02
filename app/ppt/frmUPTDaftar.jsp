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


#parse("app/ppt/frmLabelSet.jsp")
#parse("app/ppt/Sek4Paging.jsp")

<!-- Portal Role -->
#set($portal_role = "${session.getAttribute('myrole')}")

<br/>

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

#set ($chkmode = "")
#set ($checked1 = "")
#set ($checked2 = "")
#set ($checked3 = "")
#set ($checked4 = "")
#set ($checked5 = "")
#set ($checked6 = "")
#set ($checked7 = "")
#set ($checked8 = "")

#set ($checked10 = "")
#set ($checked20 = "")

#set($flagSahkan = "")

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
		
		#if ($List.semak8 == "1")
			#set ($checked8 = "checked")
		#end
		
	#end

#set($idSuburusan=$dataPermohonan.idSuburusan)
#set($idstatus=$dataPermohonan.id_status)


#if($semak=="yes")

#if($changeby == "no")
	#foreach($data in $dataPermohonan)
		
		#set($lblNoOnline=$data.no_permohonan_online)
	
   		#set($idKementerianA=$data.idKementerian)
   		#set($idNegeriA=$data.idNegeri)

   		#set($jenisPermohonan=$data.flag_jenispermohonan)
   		#set($noPermohonan=$data.noPermohonan)
   		#set($noJKPTG=$data.no_fail)
        #set($no_jkptg=$data.no_jkptg)
        
        
   		#set($tarikhPohon=$data.tarikh_permohonan)
   		#set($txdTarikhPermohonanKjp=$data.tarikh_permohonan_kjp)
   		#set($tarikh_tolak=$data.tarikh_tolak)
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
   		#set($no_rujukan_ptg=$data.no_rujukan_ptg)
   		#set($no_rujukan_ptd=$data.no_rujukan_ptd)
   		#set($no_rujukan_upt=$data.no_rujukan_upt)
   		#set($flagSahkan=$data.flag_semak)
   		
   		#set($sorJenisKodDaerah=$data.flag_jenis_kod_daerah)
   		
	#end
	
	#foreach($data2 in $dataPermohonan2)
   		#set($projekNegeri=$data2.projek_negeri)
	#end
#end


#end

#if($semak=="no")

#set($lblNoOnline="")

#set($jenisPermohonan="")
#set($namaSuburusan="")
#set($noPermohonan = "")
#set($noJKPTG="")
#set($tarikhPohon="$currentDATE")
#set($txdTarikhPermohonanKjp="$currentDATE")
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

#set($idKementerianA="")
#set($idNegeriA="")
#end


<fieldset>


<legend><strong>Daftar Permohonan</strong></legend>

#if($semak=="yes")
<fieldset>
<legend><strong>&nbsp;Maklumat Asas Permohonan</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
			<tr align="right">
				<td>$!jenisPermohonan</td>
            </tr>
		</table>

		#if($flagStatusOnline=="1")
			<div class="warning">
				PERMOHONAN ONLINE INI TELAH DIKEMBALIKAN
				#if($catatan_status_online!="")
				PADA  $!tarikh_tolak DENGAN ALASAN $!catatan_status_online
				#end		
				#if($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")
				<br/>
				<font color="blue"><i><b>&raquo;<a href="javascript:tolakPermohonanOnline('$!id_permohonan','no')"><font color="blue">kemaskini</font></a></b></i></font>
				#end
			</div>
		#end
		
    	<table width="100%"  cellpadding="1" cellspacing="1" border="0">
        
            <tr>
            	<td width="25%">Bil. Permohonan&nbsp; </td>
              	<td width="75%">:&nbsp;$!noPermohonan</td>
            </tr>
            
           	<tr>
            	<td>No. Fail Permohonan</td>
            	<td> <ul id="sddmheader"  >
    <li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()"><font color="black">:</font><font color="blue">&nbsp;$!noJKPTG</font>        
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
           		<td>No. Rujukan Online</td>
            	<td>:&nbsp;<b>$!lblNoOnline</b></td>
            </tr>
            #end
            
           	<tr>
            	<td>No. Rujukan PTG</td>
            	<td>:&nbsp;<input type="text" id="no_rujukan_ptg" name="no_rujukan_ptg" value="$!no_rujukan_ptg" maxlength="30" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" size="37" #if($edit=="no")class="disabled" readonly #end   /></td>
            </tr>
            
            <tr>
            	<td>No. Rujukan PTD</td>
            	<td>:&nbsp;<input type="text" id="no_rujukan_ptd" name="no_rujukan_ptd" value="$!no_rujukan_ptd" maxlength="30" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" size="37" #if($edit=="no")class="disabled" readonly #end   /></td>
            </tr>
            
            <tr>
            	<td>No. Rujukan UPT</td>
            	<td>:&nbsp;<input type="text" id="no_rujukan_upt" name="no_rujukan_upt" value="$!no_rujukan_upt" maxlength="30" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" size="37" #if($edit=="no")class="disabled" readonly #end   /></td>
            </tr>
            
            <tr>
            	<td>Urusan&nbsp;</td>
            	<td>:&nbsp;$!namaSuburusan</td>
            </tr>
            
           
            <tr>
            	<td><font color="red">#if($edit=="yes")*#end</font>Tarikh Permohonan</td>
                <td>:&nbsp;<input #if($edit=="no")class="disabled" readonly #end name="edit_tarikh_permohonan" value="$!tarikhPohon" size="11" id="edit_tarikh_permohonan" type="text" onblur="check_date(this);getJenisKodDaerah('$!userIdNeg','$!semak','$!edit')" />
       		 		#if($edit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('edit_tarikh_permohonan',false,'dmy');">$!frmtdate#end</td>
            </tr>
          
            #if ($currentStatus =="138" || $txdTarikhPermohonanKjp != "" )
              	<tr>
            	<td>Tarikh Permohonan KJP Diterima</td>
            	<td>:&nbsp;<input #if($edit=="no")class="disabled" readonly #end type="text" id="txdTarikhPermohonanKjp" name="txdTarikhPermohonanKjp" value="$!txdTarikhPermohonanKjp" maxlength="11" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" size="11" />
            	#if($edit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPermohonanKjp',false,'dmy');">$!frmtdate#end</td>
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
         		<td>Jenis Kod Daerah</td>
                <td>:&nbsp;
                	<input name="sorJenisKodDaerah" #if($edit=="no")class="disabled" disabled #end $!checkJK1 type="radio" value="1" onchange="onchangeJenisKodDaerah('$!semak','$!edit')" />PTG (Sebelum 01/10/2010)
                	<input name="sorJenisKodDaerah" #if($edit=="no")class="disabled" disabled #end $!checkJK2 type="radio" value="2" onchange="onchangeJenisKodDaerah('$!semak','$!edit')" />SPTB (Selepas 01/10/2010)
                </td> 
            </tr>
     		#end
            
            
        </table> 
      
    </fieldset>
#end

#if($semak=="no")
	<fieldset>
<legend><strong>&nbsp;Maklumat Asas Permohonan</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
			<tr align="right">
				<td>PERMOHONAN KAUNTER</td>
            </tr>
		</table>
		
    	<table width="100%"  cellpadding="0" border="0">
        
            <tr>
            	<td width="25%">Bil. Permohonan&nbsp; </td>
            	<td width="75%">:&nbsp;<input type="text" name="no_permohonan" value="" size="15" class="disabled" readonly></td>            	
            </tr>
           
           	<tr>
            	<td>No. Fail Permohonan</td>
            	<td>:&nbsp;<input type="text" name="no_rujukan_jkptg" value="" size="37" class="disabled" readonly></td>
            </tr>
           
            <tr>
            	<td>No. Rujukan PTG</td>
                <td>:&nbsp;<input type="text" name="no_rujukan_ptg" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" value="$!no_rujukan_ptg" maxlength="30" size="37"  ></td>
            </tr>
            
            <tr>
            	<td>No. Rujukan PTD</td>
                <td>:&nbsp;<input type="text" name="no_rujukan_ptd" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" value="$!no_rujukan_ptd" maxlength="30" size="37"  ></td>
            </tr>
           
           	<tr>
            	<td>No. Rujukan UPT</td>
                <td>:&nbsp;<input type="text" name="no_rujukan_upt" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" value="$!no_rujukan_upt" maxlength="30" size="37"  ></td>
            </tr>
            
            <tr>
            	<td>Urusan&nbsp;</td>
            	<td>:&nbsp;PENGAMBILAN TANAH DIBAWAH SEKSYEN 4</td>
            </tr>
            
            
            <tr>
            	<td><font color="red">*</font>Tarikh Permohonan</td>
                <td>:&nbsp;<input name="txdTarikhPermohonan" value="$!tarikhPohon" size="11" id="txdTarikhPermohonan" type="text" onblur="check_date(this);getJenisKodDaerah('$!userIdNeg','$!semak','$!edit')" />
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">$!frmtdate</td>
            </tr>

  			#if ($currentStatus =="138" || $txdTarikhPermohonanKjp != "" )
              	<tr>
            	<td>Tarikh Permohonan KJP Diterima</td>
            	<td>:&nbsp;<input type="text" id="txdTarikhPermohonanKjp" name="txdTarikhPermohonanKjp" value="$!txdTarikhPermohonanKjp" maxlength="11" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" size="11" #if($edit=="no")class="disabled" readonly #end   /></td>
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
         		<td>Jenis Kod Daerah</td>
                <td>:&nbsp;
                	<input name="sorJenisKodDaerah" $!checkJK1 type="radio" value="1" onchange="onchangeJenisKodDaerah('$!semak','$!edit')" />PTG (Sebelum 01/10/2010)
                	<input name="sorJenisKodDaerah" $!checkJK2 type="radio" value="2" onchange="onchangeJenisKodDaerah('$!semak','$!edit')" />SPTB (Selepas 01/10/2010)
                </td> 
            </tr>
     		#end
            
            
        </table> 
      
    </fieldset>
#end


<br/>
    
#if($semak=="no")    
<fieldset id="changeKementerian">
<legend><strong>&nbsp;Maklumat Agensi / KJP</strong></legend>

<p/>
		
		<table width="100%"  cellpadding="0" border="0">
        	
        	<tr>
				<td width="25%"><font color="red">*</font>Kementerian </td>
				<td width="75%">:&nbsp;$SelectKementerian</td>
			</tr>
           
           	<tr>
				<td>Nama Agensi </td>
				<td>:&nbsp;$SelectAgensi</td>
			</tr>
           
           <tr>      
                <td valign="top" width="25%">Alamat</td>
                <td width="75%">:&nbsp;<input type="text" readonly class="disabled" name="alamat1" value="$addAlamat1" id="alamat1" maxlength="80"   size="60" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td><font color="white">:</font>&nbsp;<input type="text" readonly class="disabled" name="alamat2" value="$addAlamat2" id="alamat2" maxlength="80"   size="60" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td><font color="white">:</font>&nbsp;<input type="text" readonly class="disabled" name="alamat3" value="$addAlamat3" maxlength="80"   id="alamat3" size="60" /></td>
            </tr>
            
            <tr>
            	<td>Poskod</td>
                <td>:&nbsp;<input type="text" name="poskod" size="4" readonly class="disabled" onkeyup="validatePoskod(this,this.value);"  value="$addPoskod" maxlength="5" id="poskod" /></td>
            </tr>
            
            <tr>
            	<td>Negeri</td>
                <td>:&nbsp;$SelectNegeri</td>
            </tr>
            	
       </table>  
   </fieldset>

<br/>
   
   <fieldset>
   <legend><strong>Maklumat Lengkap Projek</strong></legend>
       <table width="100%"  cellpadding="0" border="0">    
         	
         	<tr>
            	<td><font color="red">*</font>Peruntukan Projek&nbsp;</td>
                <td>:&nbsp;<input name="new_flag_peruntukan" type="radio" value="1" $TEMPcheckedAda />Ada
                	<input name="new_flag_peruntukan" type="radio" value="2" $TEMPcheckedTiada />Tiada</td> 
            </tr>
            
            <!-- <tr>
           	  <td><font color="red">*</font>Pengambilan Segera?&nbsp;</td>
                <td>:&nbsp;<input name="new_flag_segera" type="radio" value="1" $TEMPcheckedYa />Ya
           	  &nbsp;&nbsp;<input name="new_flag_segera" type="radio" value="2" $TEMPcheckedTidak />Tidak</td>
             </tr> -->
             
         	
         	<tr>
           	   <td><font color="red">*</font>Negeri&nbsp;</td>
                <td>:&nbsp;$project_negeri</td>
             </tr>
          
             <tr>
             	#if($showJajahan=="yes")
             	<td><font color="red">*</font>Jajahan&nbsp;</td>
             	#else
             	<td><font color="red">*</font>Daerah&nbsp;</td>
             	#end
                <td>:&nbsp;$SelectDaerah</td>
             </tr>
             
             <!-- #if($showJajahan=="yes")
             <tr>
             	<td><font color="red">*</font>Daerah Penggawa&nbsp;</td>
             	<td>:&nbsp;$SelectDaerahPenggawa</td>
             </tr>
         	 #end -->
             
         	 
         	<!-- #if($!idProjekNegeri=="3")
         	<tr>
             	<td><font color="red">*</font>Jajahan&nbsp;</td>
                <td>:&nbsp;$!SelectJajahan</td>
            </tr>           	
            #else
            <input type="hidden" name="jajahan" id="jajahan">
         	#end -->
         	
         	<!-- <tr>
            	<td><font color="red">*</font>Bandar/Pekan/Mukim&nbsp;</td>
                <td>:&nbsp;$SelectBandar</td>
            </tr> -->
         	
         	  <tr>
           	   	<td valign="top"><font color="red">*</font>Nama Projek&nbsp;</td>
                <td>&nbsp;&nbsp;<textarea name="tujuan"  id="tujuan"  cols="40%" maxlength="400" rows="4">$addTujuan</textarea></td>
              </tr>
         	
         	 <tr>
           	    <td width="25%">No. Ruj Surat KJP&nbsp;</td>
                <td width="75%">:&nbsp;<input type="text" name="rujukan_kementerian" id="rujukan_kementerian"   maxlength="50" value="$addRujukan_kementerian" size="35" #if($semak=="yes")readonly #end /></td>
            </tr>
         	
         	<tr>
            	<td>Tarikh Surat KJP&nbsp;</td>
                <td>:&nbsp;<input name="tarikh_surat" value="$!addTarikhSurat" size="11" id="tarikh_surat" type="text" onblur="check_date(this)" />
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_surat',false,'dmy');">$!frmtdate</td>
            </tr>
         	
            <tr>
            	<td>Tarikh Dikehendaki&nbsp;</td>
                <td>:&nbsp;<input name="tarikh_kehendaki" value="$!addTarikhHendak" size="11" id="tarikh_kehendaki" type="text" onblur="check_date(this)" />
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_kehendaki',false,'dmy');">$!frmtdate</td>
            </tr>
         	
        </table>	
        
        <table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian3</td>
        	</tr>
        </table>
        
    </fieldset>
#end
    
 
#if($semak=="yes")    
<fieldset id="changeKementerian">
<legend><strong>&nbsp;Maklumat Agensi / KJP</strong></legend>
<p/>
		
		
		<table width="100%"  cellpadding="0" border="0">
        	
        	<tr>
				<td width="25%"><font color="red">#if($edit=="yes")*#end</font>Kementerian</td> 
				<td width="75%">:&nbsp;$selectKementerian</td>
			</tr>
           
           <tr>
				<td>Nama Agensi</td>
				<td>:&nbsp;$selectAgensi</td>
			</tr>
           
           	<tr>      
                <td valign="top">Alamat</td>
                <td>:&nbsp;<input type="text" id="edit_alamat1" name="edit_alamat1" value="$alamat1" maxlength="80" size="60" readonly class="disabled" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td><font color="white">:</font>&nbsp;<input type="text" name="edit_alamat2" maxlength="80" value="$alamat2"   id="edit_alamat2" size="60" readonly class="disabled" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td><font color="white">:</font>&nbsp;<input type="text" name="edit_alamat3" maxlength="80" value="$alamat3"   id="edit_alamat3" size="60" readonly class="disabled" /></td>
            </tr>
            
             <tr>
            	<td>Poskod</td>
                <td>:&nbsp;<input type="text" name="edit_poskod" value="$poskod" size="4" onkeyup="validatePoskod(this,this.value);" maxlength="5" id="edit_poskod" readonly class="disabled" /></td>
            </tr>
            
            <tr>
            	<td>Negeri</td>
                <td>:&nbsp;$selectNegeri</td>
            </tr>
            
          </table>
   </fieldset>
          
<br/>
   
   <fieldset>
   <legend><strong>Maklumat Lengkap Projek</strong></legend>         
          <table width="100%"  cellpadding="0" border="0">   
          
           <tr>
            	<td width="25%"><font color="red">#if($edit=="yes")*#end</font>Peruntukan Projek&nbsp;</td>
                <td width="75%">:&nbsp;<input name="flag_peruntukan" type="radio" value="1" $checkedAda #if($edit=="no")disabled #end />Ada
                	<input name="flag_peruntukan" type="radio" value="2" $checkedTiada #if($edit=="no")disabled #end />Tiada</td> 
            </tr>
            
            <!-- <tr>
           	  <td><font color="red">#if($edit=="yes")*#end</font>Pengambilan Segera?&nbsp;</td>
                <td>:&nbsp;<input name="flag_segera" type="radio" value="1" $checkedYa #if($edit=="no")disabled #end />Ya
           	  &nbsp;&nbsp;<input name="flag_segera" type="radio" value="2" $checkedTidak #if($edit=="no")disabled #end />Tidak</td>
             </tr> -->
             
          
           	 <tr>
           	   <td><font color="red">#if($edit=="yes")*#end</font>Negeri&nbsp;</td>
                <td>:&nbsp;$selectProjekNegeri</td>
             </tr>
        
           	 <tr>
           	 	#if($showJajahan=="yes")
             	<td><font color="red">#if($edit=="yes")*#end</font>Jajahan&nbsp;</td>
             	#else
             	<td><font color="red">#if($edit=="yes")*#end</font>Daerah&nbsp;</td>
             	#end
                <td>:&nbsp;$selectDaerah</td>
             </tr>	 
             
             <!-- <tr>
            	<td><font color="red">#if($edit=="yes")*#end</font>Bandar/Pekan/Mukim&nbsp;</td>
                <td>:&nbsp;$selectBandar</td>
            </tr> -->
             
             
             <tr>
           	   <td valign="top"><font color="red">#if($edit=="yes")*#end</font>Nama Projek&nbsp;</td>
               <td valign="top"><font color="white">:</font>&nbsp;<textarea name="edit_tujuan" maxlength="400" id="edit_tujuan" cols="45%"  rows="5" #if($edit=="no")class="disabled" readonly #end>$tujuan</textarea></td>
             </tr>
        
         	
            <tr>
           	  <td>No. Ruj Surat KJP&nbsp;</td>
              <td>:&nbsp;<input type="text" name="edit_rujukan_kementerian" id="rujukan_kementerian"   maxlength="50" value="$rujKementerian" size="35" #if($edit=="no")class="disabled" readonly #end/></td>
            </tr>
         	
         	<tr>
            	<td>Tarikh Surat KJP&nbsp;</td>
                <td>:&nbsp;<input name="edit_tarikh_surat" value="$tarikhSurat" size="11" id="edit_tarikh_surat" type="text" onblur="check_date(this)" #if($edit=="no")class="disabled" readonly #end >
       		 		#if($edit=="yes")
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('edit_tarikh_surat',false,'dmy');">$!frmtdate
            		#end
            	</td>	
            </tr>
         	
            <tr>
            	<td>Tarikh Dikehendaki&nbsp;</td>
                <td>:&nbsp;<input name="edit_tarikh_kehendaki" value="$tarikhHendak" id="edit_tarikh_kehendaki" size="11" type="text" onblur="check_date(this)" #if($edit=="no")class="disabled" readonly #end>
       		 		#if($edit=="yes")
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('edit_tarikh_kehendaki',false,'dmy');">$!frmtdate
                	#end
                </td>
            </tr>
           
        </table>	
      	
      	#if($edit=="yes")
      	<table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian3</td>
        	</tr>
        </table>
        #end
        
</fieldset>
#end   
   
   
<br/>    

#if($semak=="yes")    

<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(0);" tabindex="1">Maklumat Tanah Terlibat</li>
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(1);" tabindex="1">Senarai Dokumen</li>
        #if($currentSuburusan!="53")
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(2);" tabindex="1">Senarai Semakan</li>
        #end
      </ul>
      
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
        	<fieldset>
        	<legend><strong>Maklumat Tanah Terlibat</strong> <input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambah();">
            
            #if($!no_jkptg == "")
             <input  name="cmdPupupHakmilik" id="cmdPupupHakmilik" onClick="popupGetHakmilik('$id_permohonan','4')" type="button" value="POPUP SALIN HAKMILIK SEKSYEN 8" />
             #end
            
            </legend>
            <table width="100%"   border="0">   
            <tr><td  align="left">
            <a href="javascript:popupCarianHakmilik('$id_permohonan','daftar_sek4')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
            </td>
            </tr>
            </table>
   
<!--
				
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                	<tr>
                    	
                    	<td width="30%" align="left"><input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambah();"></td>
    				
    						
    					
    					<td width="70%" align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    					
    				</tr>
    			</table>
                
                #if($saiz_listTanah > 5)
	<div style="width:100%;height:100;overflow:auto"> 
	#end
                
 	<table width="100%"  cellpadding="0" cellspacing="2" border="0">     
                    
		<tr class="table_header">
        	<td width="4%" align="center"><b>No</b></td>
          	<td width="46%"><b>&nbsp;Bandar/Pekan/Mukim</b></td>
           	<td width="50%"><b>No.LOT/No.PT</b></td> 
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
	            <td class="$row"><a href="javascript:viewMaklumat('$listTanah.id_hakmilik')"><font color="blue">$!listTanah.nama_mukim</font></a></td>
	            <td class="$row">$!listTanah.no_lotpt</td>
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
          
          	<table width="100%" border="0">
				<tr>
					<td>$!perhatian7</td>
				</tr>
			</table>
			
        </div>
        
        <div class="TabbedPanelsContent">
        	<fieldset>
        		<legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>
                <a href="javascript:popupCarianDokumen('$id_permohonan','daftar_sek4')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
			
			<!-- #if($currentStatus=="11" || $currentStatus=="113")#end  -->
			
            <!--
				<table width="100%"  cellpadding="0" border="0">
                	<tr>
                    	<td colspan="4"><input type="button" name="cmdTambah2" value ="Tambah" onClick="javascript:tambahDokumen('$id_permohonan');"></td>
                    </tr>
                </table>
                
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                    <tr class="table_header">
                    	<td width="3" align="center"><b>No</b></td>
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
                       	<td class="$row"><a href="javascript:papar_Lampiran('$idDokumen')"><font color="blue">$listD.nama_Fail</font></a></td>
                    	#if($listD_size!=0)
                    	<td class="$row" align="center"><input name="cmdHapus" type="button" value="Hapus" onClick="hapusDokumen('$!idDokumen')"></td>
                    	#end
                     	
                    <tr>
             #end  
              		 
         #else
                	<tr>
                    	<td colspan="4">Tiada rekod</td>
                    </tr>
         #end
                    
                </table>   
                -->     	
            </fieldset>	
            
            <table width="100%" border="0">
				<tr>
					<td>$!perhatian7</td>
				</tr>
			</table>
			
        </div>
      	
  #if($currentSuburusan!="53")	
        <div class="TabbedPanelsContent">
    	


#if($currentStatus=="11" || $currentStatus=="113" || $currentStatus=="138")
	#set($disCheck="")
	#set($namecb1="cbsemaks10")
	#set($namecb2="cbsemaks20")
#else
	#set($disCheck="disabled")
	#set($namecb1="cbsemaks10x")
	#set($namecb2="cbsemaks20x")
	<input type="hidden" name="cbsemaks10" id="cbsemaks10" value="1">
	<input type="hidden" name="cbsemaks20" id="cbsemaks20" value="1">
#end


<fieldset>
<legend><strong>Senarai Semakan</strong></legend>
<br/>
    <table width="100%" cellpadding="0" cellspacing="0">
   		<tr>
    		<td width="4%">&nbsp;</td>
    		<td width="1%" valign="top">#if($edit=="yes")<font color="red">*</font>#end</td>
    		<td width="3%"><input type="checkbox" value="1" $disCheck $chkmode name='$!namecb1' id='$!namecb1' $checked10 ></td>
      		<td width="3%">1.</td>
      		<td width="89%">Pelan Pengambilan Tanah yang lengkap.</td>
      			
  		</tr>
        
  		<tr>
    		<td>&nbsp;</td>
    		<td valign="top">#if($edit=="yes")<font color="red">*</font>#end</td>
    		<td><input type="checkbox" name='$!namecb2' $disCheck $chkmode value="1" id='$!namecb2' $checked20 ></td>
    		<td>2.</td>
    		<td>Pengesahan peruntukan yang mencukupi untuk membiayai kos pampasan kerosakan akibat kerja-kerja ukur dan kajian tanah.</td>
		</tr>
        
  </table>
</fieldset>
    
    		<table width="100%" border="0">
				<tr>
					<td>$!perhatian7</td>
				</tr>
			</table>
			
        </div>
  #end
      	
  </div> 
  </div>    
 
#end 


    <table width="100%"  cellpadding="0" border="0">
    	<tr align="center">
        	<td>
        	#if($semak=="yes")
	
        		#if($edit=="yes")
        		<input name="cmdUpdate" type="button" value="Simpan" onClick="update_item('$id_permohonan')">
        		<input name="cmdBatal" type="button" value="Batal" onClick="batal_update('$id_permohonan')">
        		#end
       		
        		#if($edit=="no")	
        		
        			#if(($flagStatusOnline=="" || $flagStatusOnline=="0") && ($currentStatus=="138" && ($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")) )
					<input type="button" name="cmdTolakPermohonan" value="Kembalikan Permohonan" onClick="javascript:tolakPermohonanOnline('$id_permohonan','yes')">
					#end
				
        			#if($currentStatus=="11" || $currentStatus=="113" || $currentStatus=="138")
        			#if(($checked10 == "checked" && $checked20 == "checked") && ($saiz_listTanah!=0))
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
					<input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini()">
                    <!--<input type="button" name="cmdPopupeTanah" value="Integrasi eTanah (Hantar Pelan untuk Charting)" onClick="popupEtanah('$id_fail','$id_permohonan','hantarPelanChartingS4','')"> -->
					#end
          			
          			#if($currentStatus!="11" && $currentStatus!="113" && $currentStatus!="138")
          			<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
          			#end
          			
          		<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
       			<!-- <input name="cmdCetak" type="button" value="Cetak"> -->	
          		#end	
          	#end
          		
          	#if($semak=="no")
                <input name="cmdSimpan" type="button" value="Simpan" onClick="add_item()">      						
                <input name="cmdBatal" type="button" value="Kembali" onClick="batal()">
            #end
               
            </td>
        </tr>
    </table>   

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
<!-- <input type="hidden" name="command"> -->
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_hakmilik" value="$!idMaklumat">
<input type="hidden" name="id_semak" value="$!idSemak">
<input type="hidden" name="id_status" value="$!idstatus">
<input type="hidden" name="suburusan" value="$!idSuburusan">
<input type="hidden" name="id_senaraiSemak" value="$!idSenaraiSemak">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="idKementerianA" value="$!idKementerianA">
<input type="hidden" name="idNegeriA" value="$!idNegeriA">
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<input type="hidden" name="ResultAdd" value="$!ResultAdd">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

</fieldset>

<script>
window.onload = submitForm;

function popupEtanah(id_fail,id_permohonan,jenis_skrin,command) {
	var url = "../x/${securityToken}/ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanah?id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&jenis_skrin="+jenis_skrin+"&command="+command;	
    var hWnd = window.open(url,'printuser','width=1200,height=1000, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
	
}

function popupCarianDokumen(id_permohonan,flag_skrin){	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function popupCarianHakmilik(id_permohonan,flag_skrin){
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
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();

}

function popupGetHakmilik(id_permohonan,jenis_seksyen){
	var id_negeri = document.${formName}.editProjekNegeri.value;
	var id_daerah = document.${formName}.editDaerah.value;
	
	//alert("id_negeri :"+id_negeri);
	//alert("id_daerah :"+id_daerah);
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupHakmilik?id_permohonan="+id_permohonan+"&id_negeri="+id_negeri+"&id_daerah="+id_daerah+"&jenis_seksyen="+jenis_seksyen;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function onchangeJenisKodDaerah(mode,isEdit) {
	if(mode=="no" || (mode=="yes" && isEdit=="yes")){

		if(mode=="no"){
			document.${formName}.command.value = "onchangeJenisKodDaerah";
		}else{
			document.${formName}.command.value = "onchangeJenisKodDaerahUpdate";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
		document.${formName}.submit();
	}

}

function getJenisKodDaerah(userIdNeg,mode,isEdit) {
	if(mode=="no"){
		var dat1=document.${formName}.txdTarikhPermohonan;
	}else{
		var dat1=document.${formName}.edit_tarikh_permohonan;
	}

	if((dat1.value!="" && userIdNeg=="10") && (mode=="no" || (mode=="yes" && isEdit=="yes"))){
		if(isDate(dat1.value)==false){
			dat1.focus()
			return;
		}else{
			if(mode=="no"){
				document.${formName}.command.value = "getJenisKodDaerah";
			}else{
				document.${formName}.command.value = "getJenisKodDaerahUpdate";
			}
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
			document.${formName}.submit();
		}
	}
	
}

function kembaliScreenUtama(id_permohonan) {
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();

}

function tolakPermohonanOnline(id_permohonan,formnew) {
	var w = "400";
	var h = "200";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmPopupTolakPermohonan?id_permohonan="+id_permohonan+"&formnew="+formnew+"&jenisTolak=internal";
	//var url = "../x/${securityToken}/ekptg.view.ppt.FrmPopupTolakPermohonan?id_permohonan="+id_permohonan+"&formnew="+formnew;
	
	var hWnd = window.open(url, "Permohonan Online Dikembalikan", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}

function viewMaklumat(id_hakmilik) {	
	document.${formName}.ScreenLocation.value = "changeTanah";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "edit_maklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();

}

function viewHM(id_hakmilik){
	document.${formName}.ScreenLocation.value = "changeTanah";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "edit_maklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();

}

function updateFlagSah(id_permohonan) {	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "updateFlagSah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	

}

function edit_maklumat(id_hakmilik) {
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "edit_maklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();

}

function tambahDokumen(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "tambahDokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();

}

function tambah() {	
	document.${formName}.ScreenLocation.value = "changeTanah";
	//document.${formName}.command.value = "tambah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=tambah"; 
	document.${formName}.submit();

}

function kemaskini() {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();

}

function add_item(){
	var dat1=document.${formName}.tarikh_surat
	var dat2=document.${formName}.tarikh_kehendaki
	var dat3=document.${formName}.txdTarikhPermohonan
	var radioSelected = false;
	for (i = 0;  i < ${formName}.new_flag_peruntukan.length;  i++){
	if (${formName}.new_flag_peruntukan[i].checked)
	radioSelected = true;
	}

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat kjp
	var KJP  = document.getElementById("tarikh_surat").value;
	var dt1   = parseInt(KJP.substring(0,2),10);
   	var mon1  = parseInt(KJP.substring(3,5),10);
   	var yr1   = parseInt(KJP.substring(6,10),10);
   	var dateKJP = new Date(yr1, mon1, dt1);

   	//tarikh kehendaki
   	var Khndk  = document.getElementById("tarikh_kehendaki").value;
	var dt2   = parseInt(Khndk.substring(0,2),10);
   	var mon2  = parseInt(Khndk.substring(3,5),10);
   	var yr2   = parseInt(Khndk.substring(6,10),10);
   	var dateKehendaki = new Date(yr2, mon2, dt2);

   	if(document.${formName}.txdTarikhPermohonan.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan Di Terima\" terlebih dahulu.");
  		document.${formName}.txdTarikhPermohonan.focus(); 
		return;
	
   	}else if (dat3.value!="" && isDate(dat3.value)==false){
		dat3.focus()
		return;
	
   	}else if(document.${formName}.kementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.kementerian.focus(); 
		return;
   	
   	}else if (!radioSelected){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
		return;
	
   	}else if(document.${formName}.projek_negeri.value == ""){
		alert("Sila pilih \"Negeri maklumat projek\" terlebih dahulu.");
  		document.${formName}.projek_negeri.focus(); 
		return;
	
   	}else if(document.${formName}.daerah.value == ""){
		alert("Sila pilih \"Daerah / Jajahan\" terlebih dahulu.");
  		document.${formName}.daerah.focus(); 
		return;
	
   	}else if(document.${formName}.tujuan.value == ""){
		alert("Sila masukkan \"Nama Projek\" terlebih dahulu.");
  		document.${formName}.tujuan.focus(); 
		return;
	
   	}
/*	else if(document.${formName}.alamat1.value == ""){
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

	//validate tarikh
	else if(dateKJP > currentDate){
   		alert("Sila pastikan \"Tarikh Surat KJP\" tidak lebih dari tarikh hari ini.");
	 	document.${formName}.tarikh_surat.focus();
	 	return;
	} 
*/  else if(dateKehendaki < dateKJP){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh surat KJP.");
	 	document.${formName}.tarikh_kehendaki.focus();
	 	return;
	
	}
/*   	else if(dateKehendaki < currentDate){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh hari ini.");
	 	document.${formName}.tarikh_kehendaki.focus();
	 	return;
	}
*/  
	else if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
		
	}else if (dat2.value!="" && isDate(dat2.value)==false){
		dat2.focus()
		return;
	
	}else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.method = "POST";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=simpan";
		document.${formName}.submit();
	
	}	

}

function batal_update(id_permohonan){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();

}

function batal() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.reset();
	document.${formName}.command.value = "batal";
	document.${formName}.submit();

}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.command.value = "batal";
	document.${formName}.submit();

}

function update_item(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	var dat1=document.${formName}.edit_tarikh_surat
	var dat2=document.${formName}.edit_tarikh_kehendaki
	var dat3=document.${formName}.edit_tarikh_permohonan
	
	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat kjp
	var KJP  = document.getElementById("edit_tarikh_surat").value;
	var dt1   = parseInt(KJP.substring(0,2),10);
   	var mon1  = parseInt(KJP.substring(3,5),10);
   	var yr1   = parseInt(KJP.substring(6,10),10);
   	var dateKJP = new Date(yr1, mon1, dt1);

   	//tarikh kehendaki
   	var Khndk  = document.getElementById("edit_tarikh_kehendaki").value;
	var dt2   = parseInt(Khndk.substring(0,2),10);
   	var mon2  = parseInt(Khndk.substring(3,5),10);
   	var yr2   = parseInt(Khndk.substring(6,10),10);
   	var dateKehendaki = new Date(yr2, mon2, dt2);

   	
    if(document.${formName}.edit_tarikh_permohonan.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan Di Terima\" terlebih dahulu.");
  		document.${formName}.edit_tarikh_permohonan.focus(); 
		return;
	
    }else if (dat3.value!="" && isDate(dat3.value)==false){
		dat3.focus()
		return;
	
    }
    if(document.${formName}.editKementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.editKementerian.focus(); 
		return;
	
    }else if(document.${formName}.flag_peruntukan.value == ""){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
  		document.${formName}.flag_peruntukan.focus(); 
		return;
	
    }else if(document.${formName}.editProjekNegeri.value == ""){
		alert("Sila pilih \"Negeri maklumat projek\" terlebih dahulu.");
  		document.${formName}.editProjekNegeri.focus(); 
		return;
	
    }else if(document.${formName}.editDaerah.value == ""){
		alert("Sila pilih \"Daerah / Jajahan\" terlebih dahulu.");
  		document.${formName}.editDaerah.focus(); 
		return;
	
    }else if(document.${formName}.edit_tujuan.value == ""){
		alert("Sila masukkan \"Nama Projek\" terlebih dahulu.");
  		document.${formName}.edit_tujuan.focus(); 
		return;
	
    }

	//validate tarikh
/*	else if(dateKJP > currentDate){
   		alert("Sila pastikan \"Tarikh Surat KJP\" tidak lebih dari tarikh hari ini.");
	 	document.${formName}.edit_tarikh_surat.focus();
	 	return;
	} 
*/  else if(dateKehendaki < dateKJP){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh surat KJP.");
	 	document.${formName}.edit_tarikh_kehendaki.focus();
	 	return;
	
	}
/*   	else if(dateKehendaki < currentDate){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh hari ini.");
	 	document.${formName}.edit_tarikh_kehendaki.focus();
	 	return;
	}
*/  else if (dat1.value!="" && isDate(dat1.value)==false){
		dat1.focus()
		return;
		
	}else if (dat2.value!="" && isDate(dat2.value)==false){
		dat2.focus()
		return;
	
	}else{  
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.command.value = "update";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
		document.${formName}.submit();	
 	
	}
    
}

function sah(id_permohonan) {	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "sah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	
	
}

function lulus(id_permohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "lulus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	
	
}

function validatePoskod(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
	
}

function RemoveNonNumeric( strString ){
	var strValidCharacters = "1234567890";
	var strReturn = "";
   	var strBuffer = "";
   	var intIndex = 0;
      // Loop through the string
  	for( intIndex = 0; intIndex < strString.length; intIndex++ ){
    	strBuffer = strString.substr( intIndex, 1 );
   	    // Is this a number
   	    if( strValidCharacters.indexOf( strBuffer ) > -1 ){
           	strReturn += strBuffer;
  	  	}
  	}
  	return strReturn;
      
}

function doChangeidNegeri() {
	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "daerah";	
	//doAjaxCall${formName}("doChangeidNegeri");
	document.${formName}.command.value = "doChangeidNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	
}
/*function doChangeidNegeri2() {
	document.${formName}.command.value = "doChangeidNegeri2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
		
	}	
*/	
function doChangeidKementerian() {	
	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "agensi";
	
	//doAjaxCall${formName}("doChangeidKementerian");
	document.${formName}.command.value = "doChangeidKementerian";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	
	
}

function doChangeidKementerianUPDATE() {
	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "editAgensi";
	
	//doAjaxCall${formName}("doChangeidKementerianUPDATE");
	document.${formName}.command.value = "doChangeidKementerianUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	
}
/*
function doChangeidNegeriUPDATE() {
	document.${formName}.command.value = "doChangeidNegeriUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	
	}
*/	
function doChangeidProjekNegeriUPDATE() {
	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "editDaerah";
	//doAjaxCall${formName}("doChangeidProjekNegeriUPDATE");
	document.${formName}.command.value = "doChangeidProjekNegeriUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	

}

function doChangeAlamatAgensi() {
	document.${formName}.command.value = "doChangeAlamatAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	
}
/*
function doChangeidMukim() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "tujuan";
	document.${formName}.command.value = "doChangeidMukim";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	}
	
function doChangeidMukimUPDATE() {
	
	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "edit_tujuan";
	document.${formName}.command.value = "doChangeidMukimUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	
	}
*/
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}

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

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
	
}

function cetakLampiranA(idpermohonan,namaMukim) {
	//var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idpermohonansimati="+idpermohonansimati+"&report=BorangDD&flagReport=B";
    /*var url = "../servlet/ekptg.report.ppt.LampiranASek4?idfail="+idfail+"&nama_mukim="+namaMukim;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    */

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=LampiranASek4&flagReport=S&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}

function cetakSuratKPTG(idpermohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=SuratKpdPTGDariJKPTGIbuPejabat&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}

function cariLOT(idpermohonan) {	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
	
}

function kosongkanLOT(idpermohonan) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
	
}

function hapusDokumen(iddokumen) {	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=hapusDokumen&iddokumen="+iddokumen;
	document.${formName}.submit();
	
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
	return true;
	
}

</script>

#parse("app/ppt/frmPPTHeader_script.jsp")