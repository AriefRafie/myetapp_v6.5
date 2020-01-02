
 <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
      <td valign="top" width="2%" >
       <a href="javascript:skrinAduan()" >
     <img src="../img/online/aduan.png" alt="" border="0" height="25" width="25"/>
    </a>
     </td>
     <td width="98%"  valign="middle">
     <a href="javascript:skrinAduan()" >
 
     <font color="blue" >
      Skrin Log
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
          <td width="20%" valign="top"></td>
          <td width="15%" valign="top">Negeri</td>
          <td width="1%" valign="top">:</td>
          <td width="64%" valign="top">
          
           <!--
         #if($id_negeri_cari == "")
                 
             #foreach($jm in $list_negeri)  
                 #if($jm.id_negeri == $!id_negeri_user )                    
                     #if($!jm.kod_negeri != "")
                     #set($temp_keterangan_negeri = "$!jm.kod_negeri - " ) 
                     #end
                 #set($temp_keterangan_negeri = $temp_keterangan_negeri + $!jm.nama_negeri)          
                 #set($temp_id_negeri = "$!jm.id_negeri")
                 #end 
             #end
         
         #end
          -->
          
         #set($report_nama_negeri = "") 
         #if($id_negeri_cari != "")
         #foreach($jm in $list_negeri)
         #if($jm.id_negeri==$id_negeri_cari)          
         #set($report_nama_negeri = $!jm.nama_negeri)
         #end
         #end
         #end         
         <input value="$!report_nama_negeri" type="hidden" name="report_nama_negeri" id="report_nama_negeri"  />
          
         #set($temp_id_negeri = "")
         #set($temp_keterangan_negeri = "SELURUH MALAYSIA")
       
         <select name="id_negeri_cari" id="id_negeri_cari" >  
                 
        <option value="$temp_id_negeri" >$temp_keterangan_negeri</option>             
		#foreach($jm in $list_negeri) 
		<option value="$!jm.id_negeri" #if($jm.id_negeri==$id_negeri_cari) selected="selected" #end>      
        #if($!jm.kod_negeri != "")
        $!jm.kod_negeri - 
        #end
        $!jm.nama_negeri
        
        </option>
		#end 
        
        
        
		</select>          </td>
        </tr>
        
        		
		<tr>
			<td></td>
			<td>Pejabat</td>
			<td>:</td>
			<td>
            
           
				<select id="id_pejabatjkptg" name="id_pejabatjkptg">
					<option value = "" >SILA PILIH</option>
					#foreach ( $neg in $list_pejabat )

					#set ( $selected_pejabat = "" )
					#if($id_pejabatjkptg==$neg.ID_PEJABATJKPTG)
						#set ( $selected_pejabat = "selected" )
					#end

					<option $selected_pejabat value="$neg.ID_PEJABATJKPTG" >$neg.NAMA_PEJABAT_FULL</option>
					#end
				</select>
			
			</td>
		</tr>
       
        <tr>
          <td width="20%" valign="top"></td>
          <td width="15%" valign="top">Nama Modul/Urusan</td>
          <td width="1%" valign="top">:</td>
          <td width="64%" valign="top">
          
         #set($report_nama_modul = "") 
         #if($id_jenismodulesaduan_cari != "")
         #foreach($jm in $list_module)
         #if($jm.id_jenismodulesaduan==$id_jenismodulesaduan_cari)          
         #set($report_nama_modul = $!jm.jenis_module)
         #end
         #end
         #end         
         <input value="$!report_nama_modul" type="hidden" name="report_nama_modul" id="report_nama_modul"  />
          
          
          
          
          <select name="id_jenismodulesaduan_cari" id="id_jenismodulesaduan_cari" >  
          
       
        <option value="" >SELURUH MODUL</option>
      
              
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
          <td  width="20%"></td>
          <td align="left" valign="top" width="15%">Status Log</td>
          <td valign="top" width="1%">:</td>
          <td valign="top" width="64%">
          
         
          
           
          <select name="id_statusesaduan_cari" id="id_statusesaduan_cari" > 
            <option value="" >SILA PILIH STATUS ADUAN</option>           
            #foreach($ja in $list_statusaduan) 
              #if( ($ja.id_statusesaduan == "16121" || $ja.id_statusesaduan == "16122" )|| $ja.id_statusesaduan == "16123" ) )
            <option value="$!ja.id_statusesaduan" #if($ja.id_statusesaduan==$id_statusesaduan_cari) selected="selected" #end>$!ja.kod_statusesaduan - $!ja.nama_status</option>
            #end 
             #end
            </select>
        
             <!-- <select name="id_statusesaduan_cari" id="id_statusesaduan_cari" > 
            <option value="" >SILA PILIH STATUS ADUAN</option>  
               #foreach($ja in $list_statusaduan)
           #if( ($ja.id_statusesaduan == "16125" || $ja.id_statusesaduan == "16124" ) )
           	<option style="display:none" value="$!ja.id_statusesaduan" #if($ja.id_statusesaduan==$id_statusesaduan_cari) selected="selected" #end>$!ja.kod_statusesaduan - $!ja.nama_status</option>
           #else
           	<option value="$!ja.id_statusesaduan" #if($ja.id_statusesaduan==$id_statusesaduan_cari) selected="selected" #end>$!ja.kod_statusesaduan - $!ja.nama_status</option>
           #end
          #end 
            </select>-->
        
            	</td>
        </tr>
        
        <tr>
          <td valign="top" align="right"></td>
          <td align="left" valign="top">Tarikh Log</td>
          <td valign="top">:</td>
          <td valign="top">
          
      
          
          Daripada :
          <input name="tarikh_mula" type="text" id="tarikh_mula" size="10" maxlength="10" value="$tarikh_mula" onBlur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  />
<a href="javascript:displayDatePicker('tarikh_mula',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
 <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
          &nbsp;Sehingga :
   <input name="tarikh_akhir" type="text" id="tarikh_akhir" size="10" maxlength="10" value="$tarikh_akhir" onBlur="validateTarikh(this,this.value);" onKeyUp="validateTarikh(this,this.value);"  />
<a href="javascript:displayDatePicker('tarikh_akhir',false,'dmy');">#parse("app/ppk/ppk_calender.jsp")</a>
 <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
						
          
           </td>
        </tr>
        
        
<tr>
 <td  ></td>
          <td align="left" valign="top" ></td>
          <td valign="top" ></td>
          <td valign="top" >
          <input type="button" id="cariAduanLaporan" name="cariAduanLaporan" value="Cari" onclick="cariLaporan()"/>
          <input type="button" id="resetLaporan" name="resetLaporan" value="Reset" onclick="laporanAduan()"/>
          </td>
	
	
</tr>
</table>
</fieldset>
</td>
  </tr>
</table>
<br>

<div id="print_modul">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>LAPORAN STATISTIK ADUAN MENGIKUT MODUL</b></legend>
      
     
     
      
      #parse("app/esaduan/frmLaporanModul.jsp")
      
      </fieldset></td>
  </tr>
</table>
</div>
<br>

<table width="100%" border="0" cellspacing="2" cellpadding="2"  style="display:none">
  <tr>
    <td><fieldset>
      <legend><b>LAPORAN STATISTIK ADUAN MENGIKUT NEGERI</b></legend>
      
     
     
      
      #parse("app/esaduan/frmLaporanNegeri.jsp")
      
      </fieldset></td>
  </tr>
</table>



<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>LAPORAN STATISTIK ADUAN MENGIKUT KATEGORI ADUAN</b></legend>
      
     
     
      
      #parse("app/esaduan/frmLaporanKategori.jsp")
      
      </fieldset></td>
  </tr>
</table>




<tr>

  <td  ></td>
          <td align="left" valign="top" ></td>
          <td valign="top" ></td>
          <td valign="top" >

          <td><center><input type="button" id="nextLaporan" name="nextLaporan" value="Kembali" onclick="NextlaporanAduan()" /></center></td>
</tr>	


<script>

</script>
