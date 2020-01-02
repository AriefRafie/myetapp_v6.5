


<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT LOG</strong></legend>
      #if($!id_statusesaduan_DB == "" || $!id_statusesaduan_DB == "16125")
  <table width="100%" border="0" cellspacing="2" cellpadding="2">
      #if($!id_esaduan!="")
      
      #if($!log_aduan!="")
      <tr>
          <td>&nbsp;</td>
          <td align="left">Log</td>
          <td>:</td>
          <td><input value="$!log_aduan" type="hidden" name="log_aduan" id="log_aduan"  />            
            <font color="blue" >$!log_aduan</font>        	</td>
        </tr>
       #end 
        <tr>
          <td>&nbsp;</td>
          <td align="left">Status Log</td>
          <td>:</td>
          <td><input value="$!nama_status" type="hidden" name="nama_status" id="nama_status"  />            
           <font color="blue">$!nama_status </font>        	</td>
        </tr>
      #end
      
      	
        #if($!setDefaultUser == "yes")
        <script type="text/javascript" >
		document.getElementById('tr_user_id').style.display = "none";
		</script>
        #else
        <script type="text/javascript" >
		document.getElementById('tr_user_id').style.display = "";
		</script>   
        #end
      
      
      <tr id="tr_user_id" style="display:none">
          <td width="2%" align="right"  valign="top"><font color="red">*</font></td>
          <td align="left" width="20%">Pengadu</td>
          <td width="1%">:</td>
          <td width="77%">
          <input value="$!id_negeri_pengadu" type="hidden" name="id_negeri_pengadu" id="id_negeri_pengadu"  />
<input value="$!id_pejabat_pengadu" type="hidden" name="id_pejabat_pengadu" id="id_pejabat_pengadu"  />
       
          
        <select name="user_id" id="user_id"  onChange="pilihUser();"  onBlur="pilihUser();"> 
        
       
        
        #if($!setDefaultUser == "no")
        <option value="" >SILA PILIH NAMA PENGADU</option>
        #end
                 
		#foreach($lu in $list_users) 
		<option value="$!lu.user_id" #if($lu.user_id==$!user_id) selected="selected" #end>$!lu.user_name</option>
		#end 
		</select>          </td>
        </tr>
        #if($!nama_pengadu!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Nama</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="nama_pengadu" name="nama_pengadu"  onBlur="this.value=this.value.toUpperCase();" value="$!nama_pengadu">
            <font color="blue">$!nama_pengadu</font>            </td>
          </tr>
        #end
        #if($!nama_jawatan!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Jawatan</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="nama_jawatan" name="nama_jawatan"  onBlur="this.value=this.value.toUpperCase();" value="$!nama_jawatan">
            <font color="blue">$!nama_jawatan</font>            </td>
          </tr>
        #end
       
        <tr>
          <td align="right"><font color="red">*</font></td>
          <td align="left">Emel</td>
          <td>:</td>
          <td> #if($!emel!="")
          <input TABINDEX="1" onClick="" value="$!emel" type="hidden" name="emel" id="emel" autocomplete="off" />            
             <a href="mailto:$!emel"><font color="blue"><u>$!emel</u></font></a>    
             #else
           <input  onClick="" value="$!emel" type="email" name="emel" id="emel" size="40" maxlength="100"  />     
           <font color="blue"><i>cth: abc@email.com</i></font>
             
             #end
             
             
             
                 	</td>
        </tr>
        
        
        <tr>
            <td>&nbsp;</td>
            <td align="left">No. Tel</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="no_tel" name="no_tel"  onBlur="this.value=this.value.toUpperCase();" value="$!no_telx">
           <font color="blue">$!no_telefon</font>            </td>
          </tr>

         #if($!seksyen!="")
         <tr>
            <td>&nbsp;</td>
            <td align="left">Seksyen</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="seksyen" name="seksyen"  onBlur="this.value=this.value.toUpperCase();" value="$!seksyen">
            <font color="blue">$!seksyen</font>            </td>
          </tr>
         #end
          #if($!pejabat!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Pejabat</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="pejabat" name="pejabat"  onBlur="this.value=this.value.toUpperCase();" value="$!pejabat">
            <font color="blue">$!pejabat</font>            </td>
          </tr>
         #end 
         #if($!negeri!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Negeri</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="negeri" name="negeri"  onBlur="this.value=this.value.toUpperCase();" value="$!negeri">
            <font color="blue">$!negeri</font>            </td>
          </tr>
          #end
          #if($!daerah!="")
          <tr>
            <td>&nbsp;</td>
            <td align="left">Daerah</td>
            <td >:</td>
            <td><input TABINDEX="1" type="hidden" id="daerah" name="daerah"  onBlur="this.value=this.value.toUpperCase();" value="$!daerah">
            <font color="blue">$!daerah</font>            </td>
          </tr>
          #end
          
           <tr>
          <td width="2%" align="right"><font color="red">*</font></td>
          <td align="left">Nama Modul/Urusan</td>
          <td>:</td>
          <td>
        
         #set($temp_id_modul = "")
         #set($temp_keterangan_modul = "SILA PILIH JENIS MODUL")
        
         #if($id_jenismodulesaduan == "")
                 
             #foreach($jm in $list_module)  
                 #if($jm.id_seksyen == $!id_seksyen_user )                    
                     #if($!jm.kod_module != "")
                     #set($temp_keterangan_modul = "$!jm.kod_module - " ) 
                     #end
                 #set($temp_keterangan_modul = $temp_keterangan_modul + $!jm.jenis_module)          
                 #set($temp_id_modul = "$!jm.id_jenismodulesaduan")
                 #end 
             #end
         
         #end
          
        
          <select name="id_jenismodulesaduan" id="id_jenismodulesaduan" onchange="showNotification(this.value)" >         
          <option value="$temp_id_modul" >$temp_keterangan_modul</option>
              
		#foreach($jm in $list_module) 
		<option value="$!jm.id_jenismodulesaduan" #if($jm.id_jenismodulesaduan==$id_jenismodulesaduan) selected="selected" #end>
       
       #if($!jm.kod_module != "")
        $!jm.kod_module - 
        #end
        $!jm.jenis_module</option>
		#end 
		</select>          </td>
        </tr>
        
        <tr style="display:none">
          <td>&nbsp;</td>
          <td align="left">Nama Modul/Urusan</td>
          <td>:</td>
          <td><input value="$!nama_modul" type="text" style="text-transform:uppercase;" name="nama_modul" id="nama_modul" size="40" maxlength="40" onBlur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" />          </td>
        </tr>
        
        <tr>
          <td valign="top" align="right"> <span id="notifyPPK2" style="display:none"><font color="red">*</font></span></td>
          <td align="left">No. Fail</td>
          <td>:</td>
          <td><input value="$!no_fail" type="text" style="text-transform:uppercase;" name="no_fail" id="no_fail" size="40" maxlength="40" onBlur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" />  <span id="notifyPPK" style="display:none"> <br /> Sila masukkan no fail bagi modul PPK.  </span>     </td>
        </tr>
        
        
        
        
        #if($!role == "admin_es" || $!role == "developer_es")
        <tr>
          <td valign="top" align="right"><font color="red">*</font></td>
          <td align="left" valign="top">Tarikh Log</td>
          <td valign="top">:</td>
          <td valign="top">
          
      
          
          
          <input name="tarikh_aduan_hantar_date" type="text" id="tarikh_aduan_hantar_date" size="10" maxlength="10" value="$tarikh_aduan_hantar_date" onBlur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  />
<a href="javascript:displayDatePicker('tarikh_aduan_hantar_date',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
 <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
							
                            #set($array_hour = ["01", "02","03","04","05","06","07","08","09","10","11","12"])


						     <select  class="autoselect" name="tarikh_aduan_hantar_hour" id="tarikh_aduan_hantar_hour">
						   	 <option value="" $selected_hour >HH</option>
						   		#foreach ( $y in $array_hour )
						   		#if( $y == $!tarikh_aduan_hantar_hour )
						   			#set ( $selected_hour = "selected" )
						   		#else
						   			#set ( $selected_hour = "" )
						   		#end                       
						   	<option value="$y" $selected_hour >$y</option>
						   		#end
							</select>
                            <font color="blue">HH</font>
                            
                             #set($array_minute = ["00","01", "02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"])
                            
                            <select  class="autoselect" name="tarikh_aduan_hantar_minute" id="tarikh_aduan_hantar_minute">
						   	 <option value="" $selected_minute >MM</option>
						   		#foreach ( $y in $array_minute )
						   		#if( $y == $!tarikh_aduan_hantar_minute )
						   			#set ( $selected_minute = "selected" )
						   		#else
						   			#set ( $selected_minute = "" )
						   		#end                       
						   	<option value="$y" $selected_minute >$y</option>
						   		#end
							</select>
                            <font color="blue">MM</font>
                            
           				#set($array_ampm = ["AM","PM"])            
           				<select  class="autoselect" name="tarikh_aduan_hantar_ampm" id="tarikh_aduan_hantar_ampm">
						   	 <option value="" $selected_ampm >AM/PM</option>
						   		#foreach ( $y in $array_ampm )
						   		#if( $y == $!tarikh_aduan_hantar_ampm )
						   			#set ( $selected_ampm = "selected" )
						   		#else
						   			#set ( $selected_ampm = "" )
						   		#end                       
						   	<option value="$y" $selected_ampm >$y</option>
						   		#end
							</select>
                            <font color="blue">AM / PM</font>
          
           </td>
        </tr>
        #else
          <input type="hidden" id="tarikh_aduan_hantar_date" name="tarikh_aduan_hantar_date" value="$!tarikh_aduan_hantar_date"  />
          <input type="hidden" id="tarikh_aduan_hantar_hour" name="tarikh_aduan_hantar_hour" value="$!tarikh_aduan_hantar_hour"  />
          <input type="hidden" id="tarikh_aduan_hantar_minute" name="tarikh_aduan_hantar_minute" value="$!tarikh_aduan_hantar_minute"  />
          <input type="hidden" id="tarikh_aduan_hantar_ampm" name="tarikh_aduan_hantar_ampm" value="$!tarikh_aduan_hantar_ampm"  />
         
        #end
        
       
        
         <tr>
          <td>&nbsp;</td>
          <td align="left">Nama Skrin</td>
          <td>:</td>
          <td><input value="$!nama_skrin" type="text" style="text-transform:uppercase;" name="nama_skrin" id="nama_skrin" size="40" maxlength="40" onBlur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" />          </td>
        </tr>
        #if($!role != "admin_es" && $!role != "developer_es")
        <tr>
          <td width="2%" align="right"  valign="top"><font color="red">*</font></td>
          <td align="left">Jenis Log</td>
          <td>:</td>
          <td><select name="id_jenisaduan" id="id_jenisaduan" >  
          
       
        <option value="" >SILA PILIH JENIS LOG</option>
      
              
		#foreach($ja in $list_jenisaduan) 
		<option value="$!ja.id_jenisaduan" #if($ja.id_jenisaduan==$id_jenisaduan) selected="selected" #end>$!ja.kod_jenis_aduan - $!ja.jenis_aduan</option>
		#end 
		</select>          </td>
        </tr>
        #end
        
       
        <tr>
          <td width="2%">&nbsp;</td>
          <td align="left">Sumber Log</td>
          <td>:</td>
          <td> 
        #if($!role != "admin_es" && $!role != "developer_es")
        
           
         #if($id_sumberaduan == "")             
            <input type="hidden" id="id_sumberaduan" name="id_sumberaduan"   value="16101">             
            <font color="blue">MyeTaPP</font>                 
         #else
            <input type="hidden" id="id_sumberaduan" name="id_sumberaduan"   value="$id_sumberaduan">             
            #foreach($source in $list_sumberaduan) 
            #if($source.id_sumberaduan==$id_sumberaduan)<font color="blue">$!source.nama_sumber</font>  
            #end
            #end                 
         #end
        
       
    
        #else
                
         #set($temp_id_sumber = "")
         #set($temp_keterangan_sumber = "SILA PILIH SUMBER LOG")        
         #if($id_sumberaduan == "")             
                 #set($temp_keterangan_sumber = "01 - eTaPP")          
                 #set($temp_id_sumber = "16101")               
         #end
           <select name="id_sumberaduan" id="id_sumberaduan"  >
           <option value="$temp_id_sumber"  >$temp_keterangan_sumber</option>           
            #foreach($source in $list_sumberaduan) 
            <option value="$!source.id_sumberaduan" #if($source.id_sumberaduan==$id_sumberaduan) selected="selected" #end>$!source.kod_sumber - $!source.nama_sumber</option>
            #end 
			</select>          
           #end 
            </td>
        </tr>
        
         
         <tr>
          <td width="2%" align="right"  valign="top"><font color="red">*</font></td>
          <td align="left" valign="top">Keterangan Log</td>
          <td valign="top">:</td>
          <td>
        <textarea name="aduan" id="aduan" cols="80"   rows="8"  placeholder="Sila Masukkan Keterangan Log..."         
         onBlur="check_length(this,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');"  
         onKeyup="check_length(this,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');" 
         onKeydown="check_length(this,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');"                    
           >$aduan</textarea>
                 
         <div><span id="aduan_num" style="color:blue;" ></span><span> Baki Aksara</span></div>
        
         <div id="aduan_check" class="alert_msg" ></div>            
         
         
          #if($!id_esaduan == "")
           <div><span style="color:blue;" >Fungsi upload akan dipaparkan selepas maklumat log disimpan didalam bentuk 'Deraf'</span></div>
          #end
         
         </td>
        </tr>
        #if($!id_esaduan != "")
         <tr>
          <td width="2%" align="right"  valign="top"></td>
          <td align="left" valign="top">Dokumen Sokongan</td>
          <td valign="top">:</td>
          <td valign="top">
          #if($listDokumen_aduan.size() > 0)
          #foreach($list1 in $listDokumen_aduan) 
          $list1.tajuk - <a href="javascript:papar_Lampiran('$list1.id_esdokumen')"><font color="blue"><u>$list1.nama_fail</u></font></a><a href="javascript:deleteDokumen1by1('$list1.id_esdokumen')" title="Hapus" ><font color="blue">&nbsp;<img   src="../img/validno.png"  height="10" width="10" alt="" border="0"/></font></a><br />
          #end
          #end
          <a href="javascript:bukaUpload()"><img src="../img/attachment-icon.png" alt="" border="0"/><font color="blue"><i>Muatnaik Dokumen</i></font></a>
                </td>
        </tr>
        
        <tr>
        <td colspan="4">
#if($!open_upload=="yes")
	#parse("app/esaduan/frmUpload.jsp")
#end
		</td>
        </tr>
        #end
        
      </table>
  #else
  #parse("app/esaduan/frmMaklumatAduanHeader.jsp")
  #end
  
      </fieldset></td>
  </tr>
  
  
 <input type="hidden" id="open_maklumat_teknikal_temp" name="open_maklumat_teknikal_temp" value="$!open_maklumat_teknikal" />
   
    #if($!open_maklumat_teknikal == "yes")
    
   <tr >
    
    <td colspan="2" >
     <fieldset><legend><strong>KETERANGAN TEKNIKAL</strong></legend>
     <table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_upload">
     <tr>
          <td  align="right"  valign="top"><font color="red">*</font></td>
          <td align="left">Jenis Log</td>
          <td>:</td>
          <td><select name="id_jenisaduan" id="id_jenisaduan" >  
        <option value="" >SILA PILIH JENIS LOG</option>
		#foreach($ja in $list_jenisaduan) 
		<option value="$!ja.id_jenisaduan" #if($ja.id_jenisaduan==$id_jenisaduan) selected="selected" #end>$!ja.kod_jenis_aduan - $!ja.jenis_aduan</option>
		#end 
		</select>          </td>
     </tr>  
     <tr>
        <td valign="top" align="right"> <font color="red">*</font></td>
        <td align="left" valign="top">Status Log</td>
        <td valign="top">:</td>
        <td valign="top">
         <select name="id_statusesaduan" id="id_statusesaduan" onchange="changeStatus(this.value);"> 
          <option value="" >SILA PILIH STATUS LOG</option>     
          #foreach($ja in $list_statusaduan)
           #if(($!role == "admin_es" || $!role == "developer_es") && ($ja.id_statusesaduan == "16125" || $ja.id_statusesaduan == "16124" ) )
           	<option style="display:none" value="$!ja.id_statusesaduan" #if($ja.id_statusesaduan==$id_statusesaduan) selected="selected" #end>$!ja.kod_statusesaduan - $!ja.nama_status</option>
           #else
           	<option value="$!ja.id_statusesaduan" #if($ja.id_statusesaduan==$id_statusesaduan) selected="selected" #end>$!ja.kod_statusesaduan - $!ja.nama_status</option>
           #end
          #end 
          </select>
        </td>
      </tr>
      <tr id="tarikhSelesai">
      	<td valign="top" align="right"> <font color="red">*</font></td>
        <td align="left" valign="top">Tarikh Selesai</td>
        <td valign="top">:</td>
      	<td>
      	<!-- <input name="tarikh_selesai" type="text" id="tarikh_selesai" size="10" maxlength="10" onBlur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  value="$!tarikh_selesai"/> -->	
      		<input name="tarikh_selesai" type="text" id="tarikh_selesai" size="10" maxlength="10" value="$!tarikh_selesai"/>
			<a href="javascript:displayDatePicker('tarikh_selesai',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
			<span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
      	</td>
      </tr>
      <tr>
          <td width="2%"></td>
          <td align="left" width="20%" valign="top">Kategori </td>
          <td width="1%" valign="top">:</td>
          <td width="77%" > 
        #set($check_flag_masalah_db = "")    
        #if($flag_masalah_db == "Y")
        #set($check_flag_masalah_db = "checked")  
        #end    
        <input type="checkbox" $check_flag_masalah_db name="flag_masalah_db" id="flag_masalah_db"  value="Y" > 
        Pangkalan Data <br />
          
        #set($check_flag_masalah_skrin = "")    
        #if($flag_masalah_skrin == "Y")
        #set($check_flag_masalah_skrin = "checked")  
        #end
        <input type="checkbox" $check_flag_masalah_skrin name="flag_masalah_skrin" id="flag_masalah_skrin"  value="Y" > Skrin / <i>Interface</i> <br />
        
        #set($check_flag_masalah_report = "")    
        #if($flag_masalah_report == "Y")
        #set($check_flag_masalah_report = "checked")  
        #end
        <input type="checkbox" $check_flag_masalah_report name="flag_masalah_report" id="flag_masalah_report"  value="Y" > <i>Report</i> / Laporan / Surat <br />
        
         #set($check_flag_masalah_hw = "")    
        #if($flag_masalah_hw == "Y")
        #set($check_flag_masalah_hw = "checked")  
        #end
        <input type="checkbox" $check_flag_masalah_hw name="flag_masalah_hw" id="flag_masalah_hw"  value="Y" > <i>Hardware</i>
        <br />
        
         #set($check_flag_masalah_flow = "")    
        #if($flag_masalah_flow == "Y")
        #set($check_flag_masalah_flow = "checked")  
        #end
        <input type="checkbox" $check_flag_masalah_flow name="flag_masalah_flow" id="flag_masalah_flow"  value="Y" > Lain-lain
           	</td>
        </tr>
        
         <tr>
          <td valign="top" align="right"></td>
          <td align="left" valign="top">Tahap Kesukaran</td>
          <td valign="top">:</td>
          <td valign="top">$thpKesukaran</td>
        </tr>
        
         <tr>
          <td valign="top" align="right"></td>
          <td align="left" valign="top">Jangkamasa</td>
          <td valign="top">:</td>
          <td valign="top"><input size="5" name="txtDari" readonly="readonly" value="$!txtDari"> Hingga <input size="5" name="txtHingga" readonly="readonly" value="$!txtHingga"> Hari
         </td>
        </tr>
        
          <tr>
          <td></td>
          <td align="left" valign="top">Keterangan Teknikal</td>
          <td valign="top">:</td>
          <td valign="top">
           <textarea name="ulasan_teknikal" id="ulasan_teknikal" cols="80"   rows="8"  placeholder="Sila Masukkan Ulasan Teknikal..."         
         onBlur="check_length(this,'4000','ulasan_teknikal_check','ulasan_teknikal_num','normal','yes','keterangan ulasan_teknikal');"  
         onKeyup="check_length(this,'4000','ulasan_teknikal_check','ulasan_teknikal_num','normal','yes','keterangan ulasan_teknikal');" 
         onKeydown="check_length(this,'4000','ulasan_teknikal_check','ulasan_teknikal_num','normal','yes','keterangan ulasan_teknikal');"                    
           >$ulasan_teknikal</textarea>
                 
         <div><span id="ulasan_teknikal_num" style="color:blue;" ></span><span> Baki Aksara</span>         </div>
        
         <div id="ulasan_teknikal_check" class="alert_msg" ></div>
            	</td>
        </tr>
     
        
        
          #if($!open_agihan=="yes")
          
          
       
       
       
  #end
  
     </table>
     </fieldset>
    </td>
  </tr>
  
  
   #else
              
        <input type="hidden"  name="id_statusesaduan" id="id_statusesaduan"  value="$!id_statusesaduan" >
        <input type="hidden"  name="flag_masalah_db" id="flag_masalah_db"  value="$!flag_masalah_db" >
        <input type="hidden"  name="flag_masalah_skrin" id="flag_masalah_skrin"  value="$!flag_masalah_skrin" >
        <input type="hidden"  name="flag_masalah_report" id="flag_masalah_report"  value="$!flag_masalah_report" >
        <input type="hidden"  name="ulasan_teknikal" id="ulasan_teknikal"  value="$!ulasan_teknikal" >   
        
   #end
        
   #if($!id_statusesaduan_DB == "" || $!id_statusesaduan_DB == "16125")
  <tr>
    
    <td colspan="2" align="center">
    #set($nama_simpan_atas = "Simpan Maklumat Aduan")
    #if($!id_esaduan == "" || $!id_statusesaduan == "16125")
    #set($nama_simpan_atas = "Simpan")
    #end
  
                    <input type="button" name="cmdSimpanAduan"  id="cmdSimpanAduan" value="$nama_simpan_atas" onClick="javascript:daftarAduan()" />    
                        <input type="button" name="nextPapar"  id="nextPapar" value="Kembali" onClick="javascript:nextPaparAduan()" />
                   
   #if($!id_esaduan != "" && $!id_statusesaduan == "16125")
 <input type="button"  name="cmdHantarAduan" id="cmdHantarAduan" value="Hantar Aduan" onClick="javascript:daftarAduan_hantar()" />   
   #end               
                    
                    <!--<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembali()"/>--></td>
  </tr>
  #else
   #if($!role == "admin_es" || $!role == "developer_es" )
   <tr>
    
    <td colspan="2" align="center">
  
                    <input type="button" name="cmdSimpanAduan"  id="cmdSimpanAduan" value="Simpan" onClick="javascript:daftarAduan()" />    
                           
                   
   #if($!id_esaduan != "" && $!id_statusesaduan == "16125")
 <input type="button"  name="cmdHantarAduan" id="cmdHantarAduan" value="Hantar Aduan" onClick="javascript:daftarAduan_hantar()" />   
   #end               
                    
                    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembali()"/>
                </td>
  </tr>
  #end
   #end
  
  
 #if($!open_agihan=="yes" && $!open_maklumat_teknikal == "yes")
   
  
  <tr >
    
    <td colspan="2" >
     <fieldset><legend><strong>AGIHAN TUGAS</strong></legend>
     <table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_upload">
     
          
    
          
          <tr >
          <td width="2%"></td>
          <td width="20%" align="left" valign="top">Agihan Tugas</td>
          <td width="1%" valign="top">:</td>
          <td width="77%"  valign="top">
           
         #parse("app/esaduan/frmAgihanTugas.jsp")

        <br />
            	</td>
        </tr>
       
         <tr>
          <td></td>
          <td align="left" valign="top">Komen Teknikal</td>
          <td valign="top">:</td>
          <td valign="top">
           
         #parse("app/esaduan/frmMaklumBalasTeknikal.jsp")

            	</td>
        </tr>
       
     </table>
     </fieldset>
    </td>
  </tr>
  
  #end
  
</table>

<!--penambahbaikan jangkamasa - wani -->
<script>

var get_id_jenisaduan = document.${formName}.id_jenisaduan.value;
var get_txtThpKesukaran = document.${formName}.txtThpKesukaran.value;
if(get_id_jenisaduan=="" && get_txtThpKesukaran == "1")
{
	document.${formName}.txtDari.value = 1;
	document.${formName}.txtHingga.value = 3;
}

else if(document.${formName}.txtThpKesukaran.value == "1"){
		document.${formName}.txtDari.value = "1";
		document.${formName}.txtHingga.value = "3";
	}
else if(document.${formName}.txtThpKesukaran.value == "2"){
		document.${formName}.txtDari.value = "4";
		document.${formName}.txtHingga.value = "7";
	}
else{
		document.${formName}.txtDari.value = "8";
		document.${formName}.txtHingga.value = "12";
	}
</script>



#if($!flag_simpan_doc != "yes")
<script type="text/javascript" >

check_length(document.${formName}.aduan,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');

if(document.${formName}.open_maklumat_teknikal_temp.value == "yes")
{
check_length(document.${formName}.ulasan_teknikal,'4000','ulasan_teknikal_check','ulasan_teknikal_num','normal','no','ulasan teknikal');
}

if(document.${formName}.nama_status.value == 'SELESAI')
	document.getElementById('tarikhSelesai').style.display="";
else 
	document.getElementById('tarikhSelesai').style.display="none";
</script>
#else
<script>
check_length1(document.${formName}.aduan,'4000','aduan_check','aduan_num','normal','yes','keterangan aduan');
check_length1(document.${formName}.ulasan_teknikal,'4000','ulasan_teknikal_check','ulasan_teknikal_num','normal','no','ulasan teknikal');
function check_length1(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   //$jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");



}

</script>
#end

