
<table width="100%" border="0" cellspacing="2" cellpadding="2" id="table_atas">
   <tr>
   <td width="45%" valign="top">
   <table width="100%" border="0" cellspacing="2" cellpadding="2">
   
        #if($!nama_pengadu!="")
          <tr>
            <td width="1%">&nbsp;</td>
            <td align="right" width="25%" valign="top" >Nama Pengadu</td>
            <td width="1%"  valign="top" >:</td>
            <td width="73%" valign="top" ><input TABINDEX="1" type="hidden" id="nama_pengadu" name="nama_pengadu"  onBlur="this.value=this.value.toUpperCase();" value="$!nama_pengadu">
           
         <font color="blue" >$!nama_pengadu</font>          </td>
          </tr>
        #end
        
         <input  type="hidden" id="user_id" name="user_id"   value="$!user_id">
         <input value="$!id_negeri_pengadu" type="hidden" name="id_negeri_pengadu" id="id_negeri_pengadu"  />
		<input value="$!id_pejabat_pengadu" type="hidden" name="id_pejabat_pengadu" id="id_pejabat_pengadu"  />
        
        #if($!nama_jawatan!="")
        <tr>
            <td>&nbsp;</td>
            <td align="right" valign="top" >Jawatan</td>
            <td valign="top" >:</td>
            <td valign="top" ><input TABINDEX="1" type="hidden" id="nama_jawatan" name="nama_jawatan"  onBlur="this.value=this.value.toUpperCase();" value="$!nama_jawatan">
           <font color="blue"> $!nama_jawatan</font>            </td>
          </tr>
        #end
        #if($!emel!="")
        <tr>
          <td>&nbsp;</td>
          <td align="right" valign="top">Emel</td>
          <td  valign="top">:</td>
          <td valign="top" ><input TABINDEX="1" onClick="" value="$!emel" type="hidden" name="emel" id="emel" autocomplete="off" />            
             <a href="mailto:$!emel"><font color="blue"><u>$!emel</u></font></a>        	</td>
        </tr>
        #end
        <tr>
            <td>&nbsp;</td>
            <td align="right" valign="top">No. Tel</td>
            <td valign="top" >:</td>
            <td valign="top"><input TABINDEX="1" type="hidden" id="no_tel" name="no_tel"  onBlur="this.value=this.value.toUpperCase();" value="$!no_telefon">
           <font color="blue">$!no_telefon</font>            </td>
          </tr>
         #if( $!id_seksyen != "" &&  $!id_seksyen != "0")
         <tr>
            <td>&nbsp;</td>
            <td align="right" valign="top" >Seksyen</td>
            <td valign="top" >:</td>
            <td valign="top" ><input TABINDEX="1" type="hidden" id="seksyen" name="seksyen"  onBlur="this.value=this.value.toUpperCase();" value="$!seksyen">
            <font color="blue">$!seksyen</font>            </td>
          </tr>
         #end
          #if( $!nama_kementerian != "")
         <tr>
            <td>&nbsp;</td>
            <td align="right" valign="top" >Kementerian</td>
            <td valign="top" >:</td>
            <td valign="top" ><input TABINDEX="1" type="hidden" id="nama_kementerian" name="nama_kementerian"  onBlur="this.value=this.value.toUpperCase();" value="$!nama_kementerian">
            <font color="blue">$nama_kementerian</font>            </td>
          </tr>
         #end
         #if( $!nama_agensi != "")
         <tr>
            <td>&nbsp;</td>
            <td align="right" valign="top" >Agensi</td>
            <td valign="top" >:</td>
            <td valign="top" ><input TABINDEX="1" type="hidden" id="nama_agensi" name="nama_agensi"  onBlur="this.value=this.value.toUpperCase();" value="$!nama_agensi">
            <font color="blue">$nama_agensi</font>            </td>
          </tr>
         #end
          #if($!pejabat!="" && $!nama_kementerian == "")
          <tr>
            <td>&nbsp;</td>
            <td align="right" valign="top" >Pejabat</td>
            <td valign="top" >:</td>
            <td valign="top" ><input TABINDEX="1" type="hidden" id="pejabat" name="pejabat"  onBlur="this.value=this.value.toUpperCase();" value="$!pejabat">
            <font color="blue">$!pejabat</font>            </td>
          </tr>
         #end 
         #if($!negeri!="")
          <tr>
            <td>&nbsp;</td>
            <td align="right" valign="top" >Negeri</td>
            <td valign="top" >:</td>
            <td valign="top"><input TABINDEX="1" type="hidden" id="negeri" name="negeri"  onBlur="this.value=this.value.toUpperCase();" value="$!negeri">
            <font color="blue">$!negeri</font>            </td>
          </tr>
          #end
          #if($!daerah!="")
          <tr>
            <td>&nbsp;</td>
            <td align="right" valign="top">Daerah</td>
            <td valign="top" >:</td>
            <td valign="top"><input TABINDEX="1" type="hidden" id="daerah" name="daerah"  onBlur="this.value=this.value.toUpperCase();" value="$!daerah">
            <font color="blue">$!daerah</font>            </td>
          </tr>
          #end
          
           #if($!id_esaduan!="")
      <tr>
          <td >&nbsp;</td>
          <td align="right" valign="top">Log </td>
          <td  valign="top">:</td>
          <td valign="top"><input value="$!log_aduan" type="hidden" name="log_aduan" id="log_aduan"  />            
           <font color="blue">$!log_aduan</font>        	</td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td align="right" valign="top">Status Log</td>
          <td valign="top">:</td>
          <td valign="top">
            <font color="blue"><b><blink>$!nama_status</blink></b></font>        	</td>
        </tr>
      #end
      
   </table>
   </td>
   <td width="55%" valign="top">
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
    
    
     #if($!tarikh_aduan_hantar != "")
      <tr>
          <td>&nbsp;</td>
          <td align="right" valign="top">Tarikh Log</td>
          <td valign="top">:</td>
          <td valign="top">
          <font color="blue">$!tarikh_aduan_hantar</font>          
          <input type="hidden" id="tarikh_aduan_hantar_date" name="tarikh_aduan_hantar_date" value="$!tarikh_aduan_hantar_date"  />
          <input type="hidden" id="tarikh_aduan_hantar_hour" name="tarikh_aduan_hantar_hour" value="$!tarikh_aduan_hantar_hour"  />
          <input type="hidden" id="tarikh_aduan_hantar_minute" name="tarikh_aduan_hantar_minute" value="$!tarikh_aduan_hantar_minute"  />
          <input type="hidden" id="tarikh_aduan_hantar_ampm" name="tarikh_aduan_hantar_ampm" value="$!tarikh_aduan_hantar_ampm"  />
      
           </td>
        </tr>
      #end
     
      #if($!no_fail != "")
      <tr>
          <td>&nbsp;</td>
          <td align="right" valign="top">No. Fail</td>
          <td valign="top">:</td>
          <td valign="top"><font color="blue">$!no_fail</font>
          
           </td>
        </tr>
      #end
       <input  type="hidden" id="no_fail" name="no_fail"   value="$!no_fail"> 
       
       
       #if($!id_jenismodulesaduan != "")
       <tr>
          <td >&nbsp;</td>
          <td align="right" valign="top">Nama Modul/Urusan</td>
          <td valign="top">:</td>
          <td valign="top">  
        
        #foreach($jm in $list_module) 
		#if($jm.id_jenismodulesaduan==$id_jenismodulesaduan) 
        #if($!jm.kod_module != "")        
        <font color="blue">$!jm.kod_module -</font>  
        #end
        <font color="blue">$!jm.jenis_module</font>          
		#end 
        #end
        
        
        </td>
        </tr>
        #end
        <input  type="hidden" id="id_jenismodulesaduan" name="id_jenismodulesaduan"   value="$!id_jenismodulesaduan">    
        
        
        #if($!nama_skrin != "")
          <tr>
          <td>&nbsp;</td>
          <td align="right" valign="top">Nama Skrin</td>
          <td valign="top">:</td>
          <td valign="top"><font color="blue">$!nama_skrin</font>
           </td>
        </tr>
        #end
         <input  type="hidden" id="nama_skrin" name="nama_skrin"   value="$!nama_skrin"> 
          
        #if($!id_jenisaduan != "")
        <tr>
          <td >&nbsp;</td>
          <td align="right" valign="top">Jenis Log</td>
          <td valign="top">:</td>
          <td valign="top">
		#foreach($ja in $list_jenisaduan) 
		#if($ja.id_jenisaduan==$id_jenisaduan)<font color="blue">$!ja.jenis_aduan</font>  
        #end
		#end          </td>
        </tr>
        #end
      
       #if($!role != "admin_es" && $!role != "developer_es")
         <input  type="hidden" id="id_jenisaduan" name="id_jenisaduan"   value="$!id_jenisaduan">    
       #end
        
        #if($!id_sumberaduan != "")
        <tr>
          <td >&nbsp;</td>
          <td align="right" valign="top">Sumber Log</td>
          <td valign="top">:</td>
          <td valign="top">         
	#foreach($source in $list_sumberaduan) 
	#if($source.id_sumberaduan==$id_sumberaduan)<font color="blue">$!source.nama_sumber</font>  
    #end
    #end    </td>
        </tr>
        
        #end
        <input  type="hidden" id="id_sumberaduan" name="id_sumberaduan"   value="$!id_sumberaduan">  
        
        <tr id="expand_text" height="10px" >
          <td  width="1%">&nbsp;</td>
          <td align="right" valign="top" width="23%">Keterangan Log</td>
          <td valign="top" width="1%">:</td>
          <td valign="top" width="75%"    >
          
          <font color="blue">$!aduan</font> 
                 <textarea id="aduan" name="aduan"  style="display:none" >$!aduan</textarea>
         <div><span id="aduan_num" style="color:blue; display:none" ></span><span></span></div>
         <div id="aduan_check" class="alert_msg" ></div>
      

    </td>
        </tr>
        
        <tr>
          <td width="2%" align="right"  valign="top"></td>
          <td align="right" valign="top">Dokumen Sokongan</td>
          <td valign="top">:</td>
          <td valign="top">
          #if($listDokumen_aduan.size() > 0)
          #foreach($list1 in $listDokumen_aduan) 
          $list1.tajuk - <a href="javascript:papar_Lampiran('$list1.id_esdokumen')"><font color="blue"><u>$list1.nama_fail</u></font></a><a href="javascript:deleteDokumen1by1('$list1.id_esdokumen')" title="Hapus" ><font color="blue">&nbsp;<img   src="../img/validno.png" height="10" width="10" alt="" border="0"/></font></a><br />
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
        
       </table>
     
   
   </td>   
   </tr>
   
   
   </table>
  