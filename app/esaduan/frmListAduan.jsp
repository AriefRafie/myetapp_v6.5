
 <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
      <td valign="top" width="2%" >
       <a href="javascript:laporanAduan()" >
     <img src="../img/images_stat.png" alt="" border="0" height="20" width="20"/>
    </a>
     </td>
     <td width="98%"  valign="middle">
     <a href="javascript:laporanAduan()" >
 
     <font color="blue" >
      Paparan Statistik Log
     </font></a>
     </td>
     </tr>
     </table>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
<fieldset>
<legend>CARIAN</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">

<tr>
          <td>&nbsp;</td>
          <td align="left" valign="top">Log</td>
          <td valign="top">:</td>
          <td valign="top">
          
          <input name="log_aduan_cari" type="text" id="log_aduan_cari"  value="$log_aduan_cari"   />

          
           </td>
        </tr>
        
       <tr >
          <td  valign="top"></td>
          <td >Pengadu</td>
          <td >:</td>
          <td >
            <input name="user_name" type="text" id="user_name"  value="$user_name"   />
        <!--<select name="user_id_cari" id="user_id_cari"> 
        <option value="" >SILA PILIH NAMA PENGADU</option>
		#foreach($lu in $list_users) 
		<option value="$!lu.user_id" #if($lu.user_id==$!user_id_cari) selected="selected" #end>$!lu.user_name</option>
		#end 
		</select>     -->     
        </td>
        </tr>
        

        
        <tr>
          <td>&nbsp;</td>
          <td align="left" valign="top">No Fail (Yang Bermasalah)</td>
          <td valign="top">:</td>
          <td valign="top">
          
          <input name="no_fail_cari" type="text" id="no_fail_cari"  value="$no_fail_cari"   />

          
           </td>
        </tr>
        
        <!--        
<tr>
          <td>&nbsp;</td>
          <td align="left" valign="top">Tarikh Log</td>
          <td valign="top">:</td>
          <td valign="top">
          
          <input name="tarikh_aduan_hantar_date_cari" type="text" id="tarikh_aduan_hantar_date_cari" size="10" maxlength="10" value="$tarikh_aduan_hantar_date_cari" onBlur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  />
<a href="javascript:displayDatePicker('tarikh_aduan_hantar_date_cari',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
 <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
					
          
           </td>
        </tr>
        -->
        
         <tr>
          <td valign="top" align="right"></td>
          <td align="left" valign="top">Tarikh Log</td>
          <td valign="top">:</td>
          <td valign="top">
          
      
          
          Daripada :
          <input name="tarikh_aduan_hantar_date_cari" type="text" id="tarikh_aduan_hantar_date_cari" size="10" maxlength="10" value="$tarikh_aduan_hantar_date_cari" onBlur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  />
<a href="javascript:displayDatePicker('tarikh_aduan_hantar_date_cari',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
 <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
          &nbsp;Sehingga :
   <input name="tarikh_aduan_hantar_date_akhir" type="text" id="tarikh_aduan_hantar_date_akhir" size="10" maxlength="10" value="$tarikh_aduan_hantar_date_akhir" onBlur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  />
<a href="javascript:displayDatePicker('tarikh_aduan_hantar_date_akhir',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
 <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
						
          
           </td>
        </tr>

<tr>
          <td  width="20%"></td>
          <td align="left" valign="top" width="15%">Status Log</td>
          <td valign="top" width="1%">:</td>
          <td valign="top" width="64%">
          
         
          
            <select name="id_statusesaduan_cari" id="id_statusesaduan_cari" > 
            <option value="" >SILA PILIH STATUS ADUAN</option>           
            #foreach($ja in $list_statusaduan) 
            <option value="$!ja.id_statusesaduan" #if($ja.id_statusesaduan==$id_statusesaduan_cari) selected="selected" #end>$!ja.kod_statusesaduan - $!ja.nama_status</option>
            #end 
            </select>
        
            	</td>
        </tr>
        
        <tr>
          <td ></td>
          <td >Nama Modul/Urusan</td>
          <td>:</td>
          <td><select name="id_jenismodulesaduan_cari" id="id_jenismodulesaduan_cari" >  
          
       
        <option value="" >SILA PILIH JENIS MODUL</option>
      
              
		#foreach($jm in $list_module) 
		<option value="$!jm.id_jenismodulesaduan" #if($jm.id_jenismodulesaduan==$id_jenismodulesaduan_cari) selected="selected" #end>
       
       #if($!jm.kod_module != "")
        $!jm.kod_module - 
        #end
        $!jm.jenis_module</option>
		#end 
		</select>          </td>
        </tr>
<tr>
 <td  ></td>
          <td align="left" valign="top" ></td>
          <td valign="top" ></td>
          <td valign="top" >
          <input type="button" id="cariAduanX" name="cariAduanX" value="Cari" onclick="cariAduan()"/>
          <input type="button" id="resetAduan" name="resetAduan" value="Reset" onclick="cariReset()"/>
    </td>
	
	
</tr>
</table>
</fieldset>
</td>
  </tr>
</table>

#set($total_notification = 0)

#set($total_notification = $!check_notifikasi_index_maklumbalas_aduan.size() + $!check_notifikasi_index_maklumbalas_teknikal.size())

#if($total_notification>0)
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>NOTIFIKASI 
      <span style="background-color:blue;display:none"  >&nbsp;<font color="WHITE"><blink>$total_notification</blink></font>&nbsp;</span></b>  
      </legend>
      
      
      #if($!check_notifikasi_index_maklumbalas_aduan.size()>0)
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
     <tr>
     #if($!check_notifikasi_index_maklumbalas_aduan.size()>0)
     <td width="3%"  style="background-color:blue" align="center" valign="top">
     <b><font color="WHITE"><blink>$!check_notifikasi_index_maklumbalas_aduan.size()</blink></font></b>
     </td>
     #else
     <td width="3%"   align="center" valign="top">
     </td>
     #end
     <td width="97%" valign="top">
     <a href="javascript:setTable('noti_aduan')"><font color="blue">
     MAKLUMBALAS LOG (STATUS ADUAN, KOMEN-KOMEN ADUAN, TINDAKAN)
     </font></a>
     </td>
     </tr>
     </table> 
     <fieldset id="noti_aduan" style="display:none">
     <table align="center" width="100%" >       
         <tr>
          <td colspan="7" scope="row"></td>
        </tr>
        <tr class="table_header" >
          <td scope="row" width="3%" align="center"><strong>No</strong></td>
          <td width="20%"><strong>Nama Pengadu</strong></td>
          <td width="15%"><strong>Modul / Urusan</strong></td>
          <td width="17%"><strong>No. Fail</strong></td>
          
          <td width="12.5%"  align="center"><strong>Tarikh Aduan</strong></td>
          <td width="20%"><strong>Log dan Status Aduan</strong></td>
          <td width="12.5%" align="center"><strong>Tarikh Dikemaskini</strong></td>
        </tr>
        
        #if($check_notifikasi_index_maklumbalas_aduan.size()>0)
        #set ($count = 0)
        #foreach ( $fail in $check_notifikasi_index_maklumbalas_aduan )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
        <tr>
          <td class="$row" align="center">$!count</td>
          <td class="$row"><a href="javascript:paparAduan('$fail.id_esaduan')" class="style1"><font color="BLUE">$fail.user_name</font></a></td>
          <td class="$row">          
        #if($!fail.kod_module != "")
        $!fail.kod_module - 
        #end
        $!fail.jenis_module
          </td>
          <td class="$row">$!fail.no_fail</td>
         
          <td class="$row">$!fail.tarikh_aduan_hantar</td>
          <td class="$row">
          #if($!fail.log_aduan!="")
          <a href="javascript:paparAduan('$fail.id_esaduan')" class="style1">
          <font color="blue">$!fail.log_aduan</font>
          </a>
          <br />
          #end
          $!fail.nama_status</td>
          <td class="$row">$!fail.tarikh_kemaskini</td>
        </tr>
        #end
        
         #else
  <tr>  
    <td colspan="6">Tiada Rekod</td>    
  </tr>
  #end
      </table>
      </fieldset>
      #end
   
   
   #if($!check_notifikasi_index_maklumbalas_teknikal.size()>0)
     <table width="100%" border="0" cellspacing="2" cellpadding="2">
     <tr>
     #if($!check_notifikasi_index_maklumbalas_teknikal.size()>0)
     <td width="3%"  style="background-color:blue" align="center" valign="top">
     <b><font color="WHITE"><blink>$!check_notifikasi_index_maklumbalas_teknikal.size()</blink></font></b>
     </td>
     #else
     <td width="3%" align="center" valign="top">     
     </td>
     #end
     <td width="97%" valign="top">
     <a href="javascript:setTable('noti_teknikal')"><font color="blue">
     MAKLUMBALAS LOG TEKNIKAL (AGIHAN TUGAS, KOMEN-KOMEN TEKNIKAL)
     
     </font></a>
     </td>
     </tr>
     </table> 
      
      <fieldset id="noti_teknikal" style="display:none">
      <table align="center" width="100%" >       
         <tr>
          <td colspan="7" scope="row"></td>
        </tr>
        <tr class="table_header" >
          <td scope="row" width="3%" align="center"><strong>No</strong></td>
          <td width="20%"><strong>Nama Pengadu</strong></td>
          <td width="15%"><strong>Modul / Urusan</strong></td>
          <td width="17%"><strong>No. Fail</strong></td>
          
          <td width="12.5%"  align="center"><strong>Tarikh Aduan</strong></td>
          <td width="20%"><strong>Log dan Status Aduan</strong></td>
          <td width="12.5%" align="center"><strong>Tarikh Dikemaskini</strong></td>
        </tr>
        
        #if($check_notifikasi_index_maklumbalas_teknikal.size()>0)
        #set ($count = 0)
        #foreach ( $fail in $check_notifikasi_index_maklumbalas_teknikal )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
        <tr>
          <td class="$row" align="center">$!count</td>
          <td class="$row"><a href="javascript:paparAduan('$fail.id_esaduan')" class="style1"><font color="BLUE">$fail.user_name</font></a></td>
          <td class="$row">          
        #if($!fail.kod_module != "")
        $!fail.kod_module - 
        #end
        $!fail.jenis_module
          </td>
          <td class="$row">$!fail.no_fail</td>
         
          <td class="$row">$!fail.tarikh_aduan_hantar</td>
          <td class="$row">
          #if($!fail.log_aduan!="")
          <a href="javascript:paparAduan('$fail.id_esaduan')" class="style1">
          <font color="blue">$!fail.log_aduan</font>
          </a>
          <br />
          #end
          $!fail.nama_status</td>
          <td class="$row">$!fail.tarikh_kemaskini</td>
        </tr>
        #end
        
         #else
  <tr>  
    <td colspan="6">Tiada Rekod</td>    
  </tr>
  #end
      </table>
      </fieldset>
     #end
      
     
      </fieldset></td>
  </tr>
</table>

#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI LOG</b></legend>
      
      
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
       
         <tr>
          <td colspan="8" scope="row">
          #if($role != "adminsuper_es")
          <input id="cmdTambahAduan" name="cmdTambahAduan" type="button" value="Daftar Log" onclick="javascript:daftarBaru()" />
          
          <input id="cmdHapusAduan" name="cmdHapusAduan" type="button"  style="display:none" value="Hapus Aduan" onclick="javascript:hapusAduan()" />
          #end
          </td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="3%" align="center"><strong>No</strong></td>
          <td width="20%"><strong>Nama Pengadu</strong></td>
          <td width="15%"><strong>Modul / Urusan</strong></td>
          <td width="17%"><strong>No. Fail</strong></td>
          
          <td width="12.5%"  align="center"><strong>Tarikh Log</strong></td>
          <td width="12.5%"  align="center"><strong>Tempoh Log(Hari)</strong></td>
          <td width="17%"><strong>Log dan Status Log</strong></td>
          <td width="12.5%" align="center"><strong>Tarikh Dikemaskini</strong></td>
          #if($role == "admin_es")
          <td width="3%" align="center">
            <div align="center">
      <input type="checkbox" name="all1_delete" id="all1_delete" onClick="doCheckall1_delete()" title="Semak untuk pilih semua" />
      </div>
          </td>
          #end
        </tr>
        
        #if($SenaraiFail.size()>0)
        #set ($count = 0)
        #foreach ( $fail in $SenaraiFail )
        #set ($count = $count+1)
        #set( $i = $velocityCount )
        #if ( ($i % 2) != 1 )
        #set( $row = "row2" )
        #else
        #set( $row = "row1" )
        #end
        <tr>
          <td class="$row" align="center">$fail.BIL</td>
          <td class="$row"><a href="javascript:paparAduan('$fail.id_esaduan')" class="style1"><font color="BLUE">$fail.user_name</font></a></td>
          <td class="$row">          
        #if($!fail.kod_module != "")
        $!fail.kod_module - 
        #end
        $!fail.jenis_module
          </td>
          <td class="$row">$!fail.no_fail</td>
         
          <td class="$row">$!fail.tarikh_aduan_hantar</td>
          <td class="$row" align="center">#if ($!fail.nama_status == "SELESAI") $!fail.hari_selesai #else $!fail.hari_takselesai #end </td>
          <td class="$row">
          #if($!fail.log_aduan!="") 
          <a href="javascript:paparAduan('$fail.id_esaduan')" class="style1">
          <font color="blue">$!fail.log_aduan</font>
          </a>
          <br />
          #end
          
         
          #set ($x = 0)
          #if ($!fail.nama_status == "SELESAI")
          #set ($x = $fail.hari_selesai)
          #else 
          #set ($x = $fail.hari_takselesai)
          #end
          #set ($lol = $x)
          #if ($lol != 0 && $lol <= 3) <font color="green"><b>$!fail.nama_status</b> </font>
          #elseif ($lol > 8) <font color="red"> <b>$!fail.nama_status</b> </font>
          #else 
          $!fail.nama_status 
          #end
          </td>
          <td class="$row">$!fail.tarikh_kemaskini</td>
          #if($role == "admin_es")
          <td class="$row">
          <div align="center">
            <input type="checkbox" name="ids1_delete" id="ids1_delete" $check_tech onClick="doUpdateCheckall1_delete()" value="$fail.id_esaduan" >
          </div>
          </td>
         #end 
        </tr>
        #end
        
         #else
  <tr>  
    <td colspan="8">Tiada Rekod</td>    
  </tr>
  #end
      </table>
      </fieldset></td>
  </tr>
  
</table>

<table width="50%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
    <legend><b>PETUNJUK</b></legend>
    <table align="center" width="50%">
       
         <tr>
          <td size="5" scope="row" style="background-color:green">
           <font color="green">status</font>
    </td>
    <td scope="row" >
          = TEMPOH LOG DALAM MASA TIGA HARI
    </td>
    </tr>
    <tr>
           <td size="5" scope="row" style="background-color:red">
          <font color="red">status</font>
    </td>
    <td scope="row" >
          = TEMPOH LOG LEBIH TUJUH HARI
    </td>
    </tr>
    </table>
    </fieldset>
</td>
</tr>
</table>


