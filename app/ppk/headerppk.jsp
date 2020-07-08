<!-- ::::: $!headerppk -->

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

#set($boleh_kemaskini_parent = "no")
#if($!headerppk.HEADER_NO_SUBJAKET == $!headerppk.HEADER_NO_SUBJAKET_MAX)
#set($boleh_kemaskini_parent = "yes")
#end



<input type="hidden" name="id_permohonansimati_atheader" id="id_permohonansimati_atheader" value="$!headerppk.ID_PERMOHONANSIMATI"/>
<input type="hidden" name="id_fail_atheader" id="id_fail_atheader"  value="$!headerppk.ID_FAIL"/>
<input type="hidden" name="id_permohonan_atheader" id="id_permohonan_atheader"  value="$!headerppk.ID_PERMOHONAN"/>
<input type="hidden" name="CAPAIAN_FAIL_UNIT_LUAR" id="CAPAIAN_FAIL_UNIT_LUAR"  value="$headerppk.CAPAIAN_FAIL_UNIT_LUAR"/>
<input type="hidden" name="id_permohonan_param" id="id_permohonan_param"  value="$headerppk.id_permohonan_param"/>
$!headerppk.message



#set($id_fail_atheader = $!headerppk.ID_FAIL)
#set($id_permohonan_atheader = $!headerppk.ID_PERMOHONAN)
#set($id_permohonansimati_atheader = $!headerppk.ID_PERMOHONANSIMATI)
#set($no_fail_atheader = $!headerppk.NO_FAIL)
#set($TARIKH_MOHON_BICARA_SEMULA = $!headerppk.TARIKH_MOHON_BICARA_SEMULA)

<fieldset id="headerppk">
<legend><strong>MAKLUMAT PERMOHONAN</strong></legend> 
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top" width="50%"><div align="center">
      <table width="100%" border="0">
      
      
       <!-- aishahlatip 01062017 -->
    #if($!headerppk.FLAG_KEBENARAN=="1" && $!headerppk.CHECK_EXPIRY =="Y")
        
        
         <tr>
          <td colspan=3 style="text-transform:uppercase;" valign="top"><b><FONT color=red>:$!headerppk.ALERT_LUPUT_KEMASKINI:</FONT></b></td>
        </tr>
        
         <tr>
          <td colspan=3 style="text-transform:uppercase;" valign="top">&nbsp;</td>
        </tr>
     #end
     
        <tr id="tr_nofail">
          <td width="30%" style="text-transform:uppercase;" valign="top"><div align="right">
        
          
          
          No Fail</div><input type="hidden" name="TARIKH_MOHON_BICARA_SEMULA" value="$TARIKH_MOHON_BICARA_SEMULA"/></td>
          <td width="1%" style="text-transform:uppercase;" valign="top">:</td>
          <td width="69%" style="text-transform:uppercase;" valign="top">
          #if($headerppk.NO_FAIL != "")
	          #set ($panjangNamaFail = $headerppk.NO_FAIL.length())
	          #set ($lastTwoChar = $panjangNamaFail - 1) 
	          #set ($lastThreeChar = $lastTwoChar - 1)
	          #set ($hurufH = $headerppk.NO_FAIL.substring($lastThreeChar,$lastTwoChar))
	          #if ($hurufH == "H")
	          	#set ($!headerppk.NAMA_STATUS = "TIDAK AKTIF")
	          #end
          #end
           
          
    <ul id="sddmheader"  >
    <li><a href="#" 
        onmouseover="mopen('m1')" 
        onmouseout="mclosetime()"><font color="blue">$!headerppk.NO_FAIL</font>        
        </a> 
        <div id="m1" 
            onmouseover="mcancelclosetime()" 
            onmouseout="mclosetime()" >           
           #foreach ($list in $list_sub_header)
          <a href="javascript:papar_header('$list.idPermohonan','$list.idStatus','$list.bil','$list.idPermohonanSimati','$list.tarikhMohon','$list.flagjenisfail','$list.seksyen','$list.idSimati')" >$list.keterangan</a>
           #end
            
        </div>
    </li>
   </ul>
	<div style="clear:both"></div>
<!--    
        
         <input type="hidden" name="idPermohonan"/>
         <input type="hidden" name="idPermohonanSimati"/>
         <input type="hidden" name="idStatus"/>
         <input type="hidden" name="flagFromSenaraiFailSek8"/>
         <input type="hidden" name="flagForView"/>         
         
         <input type="hidden" name="id_perbicaraan" value="$id_perbicaraan"/>
         <input type="hidden" name="idpermohonan"/>
         <input type="hidden" name="idpermohonansimati"/>
         <input type="hidden" name="tarikh_mohon" />
         <input type="hidden" name="id_status"/>
         <input type="hidden" name="command"/>
         <input type="hidden" name="id_Simati"/>         
        
		 <input type="hidden" name="id_permohonan"/>		
		 
		 <input type="hidden" name="idSimati"/>
          
    -->      
            </td>
            <!-- arief add button LOG TUGASAN FAIL 8/4/2020 OPEN -->
            #foreach ($ListIDStatus in $id_status)
				#set($idstatus = $ListIDStatus.id_status )
			#end
            #if ($idstatus != 8 || $idstatus != 150 || $idstatus != 160 )
            <td>
            <input name="cmdLogTugasanFail" id="cmdLogTugasanFail" value="LOG TUGASAN FAIL" type="button" onClick="javascript:viewLogTugasanFail()">
            </td>
            #end
            <!--arief add button LOG TUGASAN FAIL 8/4/2020 CLOSE -->
        </tr>
        <tr> 
          <td  style="text-transform:uppercase;" valign="top"><div align="right">Negeri</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top">:</td>
          
          <td style="text-transform:uppercase;" valign="top"><font color="blue">$!headerppk.NAMA_NEGERI</font></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top"><div align="right">Daerah / Jajahan</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.DAERAH_MOHON</font></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Unit</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.NAMA_PEJABAT, $!headerppk.DAERAH_PEJABAT</font></td>
        </tr>
        
       #if(($!headerppk.ID_PERMOHONAN_SEBELUM_BICARA != "" || $!headerppk.ID_PERMOHONAN_SELEPAS_BICARA != "") && ($!headerppk.NO_FAIL_SEBELUM_BICARA!=""))
		&nbsp;
        #else
        <tr id="tr_sasarankpi">
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Tempoh Sasaran KPI</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><div id="headerppk_kpi_ppk" ></div></td>
        </tr>
        
        #end
       
        
        
        #if($!headerppk.ID_PERMOHONAN_SEBELUM_BICARA != "" || $!headerppk.ID_PERMOHONAN_SELEPAS_BICARA != "")
        <tr >
          <td style="text-transform:uppercase;" valign="top" align="right" >Status Perbicaraan Semula</td>
          <td  style="text-transform:uppercase;" valign="top" >:</td>
          <td  valign="top" >
          #if($!headerppk.NO_FAIL_SEBELUM_BICARA!="")
              <font color="blue"><blink>Fail ini dibenarkan untuk dibicarakan semula.</blink></font>
              <br>Untuk semakan maklumat perbicaraan terdahulu, <br />sila klik               
               <a href="javascript:historyFail($!headerppk.SEKSYEN_SEBELUM_BICARA,$!headerppk.ID_PERMOHONAN_SEBELUM_BICARA,$!headerppk.ID_STATUS_SEBELUM_BICARA,$!headerppk.ID_PERMOHONANSIMATI_SEBELUM)" ><font color="blue"> $!headerppk.NO_FAIL_SEBELUM_BICARA</font></a>             
              </font>
          #end  
              
          #if($!headerppk.NO_FAIL_SELEPAS_BICARA!="")
             <font color="blue"><blink>Fail ini telah dibicarakan semula.</blink></font>
              <br>Untuk semakan maklumat perbicaraan semasa, <br />sila klik               
              <a href="javascript:currentFail($!headerppk.SEKSYEN_SELEPAS_BICARA,$!headerppk.ID_PERMOHONAN_SELEPAS_BICARA,$!headerppk.ID_STATUS_SELEPAS_BICARA)" ><font color="blue"> $!headerppk.NO_FAIL_SELEPAS_BICARA</font></a>  
          #end 
          
          #if($!headerppk.USER_NAME_BICARA != "")
          <br />
          Pengesahan oleh : <font color="blue">$!headerppk.USER_NAME_BICARA</font>
          #else
          <br />
          Pengesahan oleh : <font color="blue">KEPUTUSAN MAHKAMAH</font>
          #end 
          	
          #if($!headerppk.CATATAN_BICARA_SEMULA != "") 
          <br />
          <b>Catatan :
          <font color="red">$!headerppk.CATATAN_BICARA_SEMULA</font></b> 
          #end
          </td>
        </tr>
        #end
      </table>
    </div></td>
    <td width="50%" valign="top">
      <table width="100%" border="0">
      
      #if($!headerppk.FLAG_KEBENARAN=="1" && $!headerppk.CHECK_EXPIRY =="Y")
       <tr>
          <td colspan=3 style="text-transform:uppercase;" valign="top">&nbsp;</td>
        </tr>
        
        <tr>
          <td colspan=3 style="text-transform:uppercase;" valign="top">&nbsp;</td>
        </tr>
        
        #end
      
      
        <tr>
          <td width="20%" style="text-transform:uppercase;" valign="top" ><div align="right">Status Fail</div></td>
          <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
          <td width="79%" style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.NAMA_STATUS</font>
          <input type="hidden" name="id_status_semasa" id="id_status_semasa"  value="$!headerppk.ID_STATUS"/>
           <!-- open razman add untuk bicara online -->
          #if($!headerppk.ID_STATUS == "21" ||
          $!headerppk.ID_STATUS == "18" ||
          $!headerppk.ID_STATUS == "41" ||
          $!headerppk.ID_STATUS == "25" ||
          $!headerppk.ID_STATUS == "64" ||
          $!headerppk.ID_STATUS == "163" ||
          $!headerppk.ID_STATUS == "166" ||
          $!headerppk.ID_STATUS == "180" ||
          $!headerppk.ID_STATUS == "164" ||
          $!headerppk.ID_STATUS == "165")
          <div id="linkBicaraOnline">
          <a href="javascript:funcBicaraInteraktif();" ><font color="blue"><u><b>>> Skrin Bicara Interaktif</b></u></font></a>
          </div>
          #end
          <!-- close razman add untuk bicara online -->
          
          </td>
        </tr>
        #set($flag_kemaskini_selesai = "yes")
        
        
        <!--
fail2 yang sepatutnya tidak boleh kemaskini
SELECT * FROM TBLRUJSTATUS WHERE ID_STATUS IN ('47','70','169','50','21','999')

       headerppk.ID_STATUS =  $!headerppk.ID_STATUS -->
        #if($!headerppk.ID_STATUS == "21" || $!headerppk.ID_STATUS == "47"  || $!headerppk.ID_STATUS == "70" || $!headerppk.ID_STATUS == "169" || $!headerppk.ID_STATUS == "50" || $!headerppk.ID_STATUS == "21" || $!headerppk.ID_STATUS == "999")
        	
            #if($!headerppk.FLAG_KEBENARAN_EDIT == "1") 
            #set($flag_kemaskini_selesai = "yes")
            
            #if($!headerppk.STATUS_EDIT_USER.CHECK_EDIT == "no")
            
            #set($flag_kemaskini_selesai = "no")
            
            #end
            
            
            
            #else
          
                #set($flag_kemaskini_selesai = "no")
                
            #end
            #else
                #set($flag_kemaskini_selesai = "yes")
                
            #end
        
        
       
            #if($!role == "userSemakanPerintah") 
            #set($flag_kemaskini_selesai = "no")
            
            #end
    
     
       #if($!headerppk.ID_STATUS == "21" || $!headerppk.ID_STATUS == "47" || $!headerppk.ID_STATUS == "70" || $!headerppk.ID_STATUS == "169" || $!headerppk.ID_STATUS == "50" || $!headerppk.ID_STATUS == "999")
       <tr>
          <td  style="text-transform:uppercase;" valign="top" ><div align="right">Status Pengemaskinian Fail</div></td>
          <td style="text-transform:uppercase;" valign="top" >:</td>
          <td  valign="top" >   
           #if($!role == "userSemakanPerintah") 
           <font color="red"><b>Tidak Dibenarkan</b></font> 
           #else
         		#if($!headerppk.FLAG_KEBENARAN_EDIT == "1") 
                
                #if($!headerppk.STATUS_EDIT_USER.CHECK_EDIT == "yes")
                <font color="blue"><b>Dibenarkan</b></font> Oleh <font color="blue">$!headerppk.USER_NAME</font> 
                #else                
                <font color="red"><b>Tidak Dibenarkan</b></font>                   
                #end
                            
                #else
                <font color="red"><b>Tidak Dibenarkan</b></font> 
                #end
                #if($!headerppk.CATATAN_KEBENARAN_EDIT != "")                
                <br />
               Catatan :
                 <font color="blue">$!headerppk.CATATAN_KEBENARAN_EDIT</font> 
                #end
            #end    
             #if ($hurufH != "H")   
             	#if($!headerppk.SEKSYEN == "8") 
             		#if($!headerppk.HANTAR_LAP_PERINTAH == "Y") 
			             #if($!headerppk.USER_ROLE == "adminppk")
			             <br /> 
			             Sila buat semakan di    
			             <a href="javascript:paparKemaskiniFail('$!headerppk.ID_FAIL','$!headerppk.NO_FAIL')" >
			              <font color="blue">sini</font></a>
	             
	             		#elseif($!headerppk.USER_ROLE == "user_ppk")
	             	
			             <br /> 
			             Sila mohon kebenaran di    
			             <a href="javascript:paparKemaskiniFail('$!headerppk.ID_FAIL','$!headerppk.NO_FAIL')" >
			              <font color="blue">sini</font></a>
			       	   #end
			       	#else
			       		<br /> 
			       		<font color="red">Laporan Perintah masih belum dihantar.</font></a>
             		#end
             		
             		
             		
             	#else
             	
             			#if($!headerppk.USER_ROLE == "adminppk")
			             <br /> 
			             Sila buat semakan di    
			             <a href="javascript:paparKemaskiniFail('$!headerppk.ID_FAIL','$!headerppk.NO_FAIL')" >
			              <font color="blue">sini</font></a>
	             
	             		#elseif($!headerppk.USER_ROLE == "user_ppk")
	             	
			             <br /> 
			             Sila mohon kebenaran di    
			             <a href="javascript:paparKemaskiniFail('$!headerppk.ID_FAIL','$!headerppk.NO_FAIL')" >
			              <font color="blue">sini</font></a>
			       	   #end
             	
             	#end
             		
             		
             #end
          </td>
        </tr>
       #end
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Seksyen</div></td>
          <td  style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.SEKSYEN</font></td>
        </tr>
        
      
        <tr id="tr_tarikhmohon">
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Tarikh Mohon</div></td>
           <td  style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.TARIKH_MOHON</font>
          <input type="hidden" name="tarikh_mohon_headerppk" id="tarikh_mohon_headerppk" value="$!headerppk.TARIKH_MOHON"  />
        
          
          </td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Nama Pemohon</div></td>
           <td  style="text-transform:uppercase;" valign="top" >:</td>
          <td  valign="top" >
          <font style="text-transform:uppercase;" color="blue">$!headerppk.NAMA_PEMOHON</font>
           <br />
           #if (($hurufH != "H") && ($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "N") && ($!headerppk.ID_STATUS != "152")&& ($!headerppk.ID_STATUS != "21")&& ($!headerppk.ID_STATUS != "175")&& ($!headerppk.ID_STATUS != "177"))
          	
          
           Penggantian pemohon boleh dibuat  
           <a href="javascript:paparKemaskiniPemohon('$!headerppk.ID_FAIL','$!headerppk.NO_FAIL')" >
           <font color="blue">di sini</font> (Jika Perlu)</a>
           #end
           #if($!headerppk.NAMA_PEMOHON_LAMA != "")
           <br />           
          Pemohon Asal : <font color="blue">$!headerppk.NAMA_PEMOHON_LAMA</font>
           #end
           
          #if($!headerppk.NO_KP_BARU_LAMA != "") 
          <br />
          MyID Pemohon Asal : <font color="blue">$!headerppk.NO_KP_BARU_LAMA</font>
          #end
          
          #if($!headerppk.NO_KP_LAMA_LAMA != "") 
          <br />
          MyID Lama Pemohon Asal : <font color="blue">$!headerppk.NO_KP_LAMA_LAMA</font>
          #end
          
          #if($!headerppk.NO_KP_LAIN_LAMA != "" && $!headerppk.JENIS_KP_LAMA != "" && $!headerppk.JENIS_KP_LAMA != "0") 
          <br />
          MyID Lain Pemohon Asal : <font color="blue">
          #if($!headerppk.JENIS_KP_LAMA=="5")
          TENTERA -                                           
          #elseif($!headerppk.JENIS_KP_LAMA=="6")
          POLIS -                                           
          #elseif($!headerppk.JENIS_KP_LAMA=="4")
          PASSPORT -                                           
          #elseif($!headerppk.JENIS_KP_LAMA=="7")
          LAIN-LAIN -         
          #end          
          $!headerppk.NO_KP_LAIN_LAMA</font>
          #end
          <!-- Salnizam edit starts -->
          
          <br />
          #if($!headerppk.NAMA_PEMOHON_LAMA != "")
          Bukti Dokumen Sokongan : 
          #if($!headerppk.TEST=="101")
          <input name="cetak" type="button" value="Muat turun Dokumen" onclick="doOpen($!headerppk.ID_PERMOHONANSIMATI)" />                                              
          #end 
          #end         
          </font>
          
           <!-- Salnizam edit ends -->
          </td>
        </tr>
        
       
        
       #if($!headerppk.EMEL != "")
         <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Emel Pemohon</div></td>
           <td  style="text-transform:uppercase;" valign="top" >:</td>
          <td ><a href="mailto:$!emel_pemohon"><font color="blue"><u>$!headerppk.EMEL</u></font></a></td>
        </tr>
       #end
        
     
        
        <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right">Nama Simati</div></td>
           <td width="1%" style="text-transform:uppercase;" valign="top" >:</td>
          <td style="text-transform:uppercase;" valign="top" ><font color="blue">$!headerppk.NAMA_SIMATI</font>
          #set($tarikhmati_simati = $!headerppk.TARIKH_MATI)
          </td>
        </tr>

        #if($!headerppk.ID_STATUS != "21" && $!headerppk.ID_STATUS != "47"  && $!headerppk.ID_STATUS != "70" && $!headerppk.ID_STATUS != "169" && $!headerppk.ID_STATUS != "50" && $!headerppk.ID_STATUS != "41" && $!headerppk.ID_STATUS != "999")
        #if($!headerppk.ADA_MAKLUMAT_HUTANG_SIMATI == "Y")
         <tr>
          <td style="text-transform:uppercase;" valign="top" ><div align="right"></div></td>
           <td width="1%" style="text-transform:uppercase;" valign="top" ></td>
          <td style="text-transform:uppercase;" valign="top" ><font color="red"><b>Terdapat maklumat hutang berkaitan Simati yang belum disalin. Untuk maklumat lanjut, sila semak di dalam modul</b></font>
          <a href="javascript:papareHutang('$!headerppk.ID_Hutang')" >
			              <font color="blue"> eHutang.</font></a>
          </td>
        </tr>
        #end 
        
        
        #end
        <tr>
        </tr>
        <!--<tr>
            <td width="25%" valign="top"></td>
            <td width="1%" valign="top"></td>
            <td width="34%" valign="top" align="right"><input type="button" id="" name="" value="Capaian Arkib Dokumen" onclick="javascript:arkibWindow('$no_fail_atheader')"/></td>
         </tr> -->
      </table>
    </td>
  </tr>
</table>
</fieldset>


<br>
<!-- getflag  -->

<br>
<!--headerppk.CAPAIAN_FAIL_UNIT_LUAR = $!headerppk.CAPAIAN_FAIL_UNIT_LUAR -->
#if($!headerppk.CAPAIAN_FAIL_UNIT_LUAR == "Y")
	
	#set($flag_kemaskini_selesai = "no")
	
	#set($disability="readonly")
	#set($disability1="disabled")
	#set($disabilityCSS="class=disabled")
	#set($$newForm="no")
	
#end
<br>








<!--:::::::::::::::: $!flag_kemaskini_selesai
<br />
:::::::::::::::: $!headerppk.STATUS_EDIT_USER.CHECK_EDIT
-->
  #if($!headerppk.NO_FAIL != "")
      <script>
      document.getElementById('tr_nofail').style.display="";
      </script>
      #else
      <script>
      document.getElementById('tr_nofail').style.display="none";
      </script>
      #end
        
        
      #if($!headerppk.TARIKH_MOHON != "")
      <script>
      document.getElementById('tr_sasarankpi').style.display="";
      </script>
      #else
      <script>
      document.getElementById('tr_sasarankpi').style.display="none";    
      </script>
      #end
        
         #if($!headerppk.TARIKH_MOHON != "")
      <script>
      document.getElementById('tr_tarikhmohon').style.display="";
      </script>
      #else
      <script>
      document.getElementById('tr_tarikhmohon').style.display="none";
      </script>
      #end
        

<!--
ScrollX :<input type="text" id="ScrollX" name='ScrollX'  />
ScrollY :<input type="text" id="ScrollY" name='ScrollY'  />
-->
</fieldset>

<div id="field_location"></div>
<!--JENIS VM :::$!flag_jenis_vm
   -->


<!--JKPTG/PK/03/02/0584/2009/S17-1-->



#if($!flag_jenis_vm == "vtemplate")

<script>

function doOpen(id) {
	//alert('id : '+id);
    var url = "../servlet/ekptg.view.ppk.DisplayBlobTukarPemohon?id="+id;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function paparKemaskiniFail(id_fail_carian,txtNoFailSub) {
//document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&command=paparSub";
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
//document.f1.id_fail_carian.value = id_fail_carian;
//document.f1.txtNoFailSub.value = txtNoFailSub;
document.f1.submit();
}

function paparKemaskiniPemohon(id_fail_carian,txtNoFailSub) {
//document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&command=paparSub";
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmTukarPemohon&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
//document.f1.id_fail_carian.value = id_fail_carian;
//document.f1.txtNoFailSub.value = txtNoFailSub;
document.f1.submit();
}

function papareHutang(id_fail_carian) {
	//document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&command=paparSub";
	document.f1.action = "$EkptgUtil.getTabID("e-Hutang",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiHutangView&command=paparPenghutang&id="+id_fail_carian+"";
	//document.f1.id_fail_carian.value = id_fail_carian;
	//document.f1.txtNoFailSub.value = txtNoFailSub;
	document.f1.submit();
	}

</script>
#else
<script>

function doOpen(id) {
	
    var url = "../servlet/ekptg.view.ppk.DisplayBlobTukarPemohon?id="+id;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function paparKemaskiniFail(id_fail_carian,txtNoFailSub) {
//alert("tab id"+"$EkptgUtil.getTabID("Utiliti",$portal_role)");	
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmKebenaranKemaskiniFail&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
//document.${formName}.id_fail_carian.value = id_fail_carian;
//document.${formName}.txtNoFailSub.value = txtNoFailSub;
document.${formName}.submit();
}

function paparKemaskiniPemohon(id_fail_carian,txtNoFailSub) {
//alert("tab id"+"$EkptgUtil.getTabID("Utiliti",$portal_role)");	
document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmTukarPemohon&command=paparSub&id_fail_carian="+id_fail_carian+"&txtNoFailSub="+txtNoFailSub+"";
//document.${formName}.id_fail_carian.value = id_fail_carian;
//document.${formName}.txtNoFailSub.value = txtNoFailSub;
document.${formName}.submit();
}






</script>

#end

#if($!flag_jenis_vm == "vtemplate")

<script>
var temp_tarikh = "";
var temp_tarikh2 = "";
temp_tarikh2 = document.f1.TARIKH_MOHON_BICARA_SEMULA.value;

temp_tarikh = document.f1.tarikh_mohon_headerppk.value;
//alert (temp_tarikh);
var id_status_semasa = document.f1.id_status_semasa.value;
var dateKPISek8 = new Date('2017', '7', '1');
//alert ("dateKPISek8 = " + dateKPISek8);

	var flag_kpi_baru = "no"
	var beze_hari = 0;
	if (temp_tarikh != "" )
	{
		
		var d1 = parseInt(temp_tarikh.substring(0,2),10);
		var m1 = parseInt(temp_tarikh.substring(3,5),10)-1;
		var y1 = parseInt(temp_tarikh.substring(6,10),10);
		var myDate1 = new Date(y1, m1, d1, 23, 59, 59);
		var tahum_semasa = myDate1.getFullYear();
		if(parseInt(tahum_semasa)>=2013)
		{
		flag_kpi_baru = "yes";	
		}	
		var waktusemasa = new Date();
		var daySemasa = waktusemasa.getDate();
		var monthSemasa = waktusemasa.getMonth();
		var yearSemasa = waktusemasa.getFullYear();
		waktusemasa = new Date(yearSemasa, monthSemasa, daySemasa , 23, 59, 59);
		//alert("waktusemasa = "+waktusemasa);
		var ONE_DAY = 1000 * 60 * 60 * 24;
		var difference_ms = Math.abs(waktusemasa - myDate1);
		beze_hari = Math.round(difference_ms/ONE_DAY);
		//alert("beze_hari2 = "+beze_hari);
	}
	
	if (temp_tarikh2 != "" )
	{
		
		var d2 = parseInt(temp_tarikh2.substring(0,2),10);
		var m2 = parseInt(temp_tarikh2.substring(3,5),10)-1;
		var y2 = parseInt(temp_tarikh2.substring(6,10),10);
		var myDate2 = new Date(y2, m2, d2, 23, 59, 59);
		var tahum_semasa = myDate2.getFullYear();
		if(parseInt(tahum_semasa)>=2013)
		{
		flag_kpi_baru = "yes";	
		}	
		var waktusemasa = new Date();
		var daySemasa = waktusemasa.getDate();
		var monthSemasa = waktusemasa.getMonth();
		var yearSemasa = waktusemasa.getFullYear();
		waktusemasa = new Date(yearSemasa, monthSemasa, daySemasa , 23, 59, 59);
		var ONE_DAY = 1000 * 60 * 60 * 24;
		var difference_ms = Math.abs(waktusemasa - myDate2);
		beze_hari = Math.round(difference_ms/ONE_DAY);
	}



if (temp_tarikh != "" ){
var tarikhHantar = temp_tarikh;
var days = 0;
if('$!headerppk.SEKSYEN' == '8')
{	
	//alert (myDate1);
	var beza_hari_KPI_Sek8 = (myDate1 - dateKPISek8);
	var ONE_DAY = 1000 * 60 * 60 * 24;
	beza_hari_KPI_Sek8 = Math.round(beza_hari_KPI_Sek8/ONE_DAY);
	
	
	if (beza_hari_KPI_Sek8 > 0)
		{
		var days = 120;
		}
	else if((flag_kpi_baru == "yes") && (beza_hari_KPI_Sek8 < 0))
		{
		var days = 135;
		}
	else
		{
		var days = 165;
		}
	

}
if('$!headerppk.SEKSYEN' == '17')
{

var tarikhHantar_day = parseInt(tarikhHantar.substring(0,2),10);
var tarikhHantar_month = parseInt(tarikhHantar.substring(3,5),10)-1;
var tarikhHantar_year = parseInt(tarikhHantar.substring(6,10),10);
var tarikhPenghantaran = new Date(tarikhHantar_year, tarikhHantar_month, tarikhHantar_day);

var dateKPISek17 = new Date('2017', '9', '1');
//var days = 90;
var beza_hari_KPI_Sek17 = (tarikhPenghantaran - dateKPISek17);
var ONE_DAY = 1000 * 60 * 60 * 24;
beza_hari_KPI_Sek17 = Math.round(beza_hari_KPI_Sek17/ONE_DAY);

if (beza_hari_KPI_Sek17 < 0)
	{
	var days = 90;
	}
else
	{
	var days = 120;
	}
}
var dt1 = parseInt(tarikhHantar.substring(0,2),10) + days;

var mon1 = parseInt(tarikhHantar.substring(3,5),10)-1;
var yr1 = parseInt(tarikhHantar.substring(6,10),10);


var tarikhHantar2 = temp_tarikh2;
var dt2 = parseInt(tarikhHantar2.substring(0,2),10) + days;
var mon2 = parseInt(tarikhHantar2.substring(3,5),10)-1;
var yr2 = parseInt(tarikhHantar2.substring(6,10),10);

var currentTime = new Date();
var currentHari = currentTime.getDate();
var currentBulan = currentTime.getMonth()+1;
var currentTahun = currentTime.getFullYear();
//alert ("currentTime = "+currentTime);
var myDate = new Date(yr1, mon1, dt1, 23, 59, 59);
var day = myDate.getDate();
var month = myDate.getMonth()+1;
var year = myDate.getFullYear();
var tarikhJangkaTerima = "";

var currentTime2 = new Date();
var myDate2 = new Date(yr2, mon2, dt2);
var day2 = myDate2.getDate();
var month2 = myDate2.getMonth()+1;
var year2 = myDate2.getFullYear();
var tarikhJangkaTerima2 = "";

if(month>=10){
if(day>=10){
tarikhJangkaTerima = day + "/" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/" + month + "/" + year;
}
} else {
if(day>=10){
tarikhJangkaTerima = day + "/0" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/0" + month + "/" + year;
}
}

if (day2 >0)
{
if(currentTime>myDate2)
{	 
	beze_hari = beze_hari-0;
	var days3 = days-beze_hari;
	if((id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70') && (days3 == '0'))
	{
		//alert("id_status1b = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
	}
	else if(id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70')
	{
		$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
	}
	else if (days3 == '0')
	{
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
	}
	else
	{
		//alert("id_status2b = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'><blink><b>"+tarikhJangkaTerima+" <br>"+((days-beze_hari)*-1)+" hari lewat </b></blink></span>");	
	}
}
else
{
	if((id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70') && (days3 == '0'))
	{
		//alert("id_status3b = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
	}
	else if (days3 == '0')
	{
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
	}
	else
	{
		//alert("id_status4b = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+" <br>"+(days-beze_hari)+" hari lagi</b></span>");	
	}					
}
}
else
	{  
	beze_hari = beze_hari-0;
	//alert("currentTime = "+currentTime);
	//alert("myDate = "+myDate);
	var days3 = days-beze_hari;
		if(currentTime>myDate)
			
		{   
			
			
			if((id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70') && (days3 == '0'))
			{
				//alert("id_status1 = " + id_status_semasa );
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
			}
			else if(id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70')
			{
				$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
			}
			else if (days3 == '0')
				{
				$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
				}
			else
			{
				//alert("Read here" );
				//alert("beze_hari = " + beze_hari );
				
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'><blink><b>"+tarikhJangkaTerima+" <br>"+((days-beze_hari)*-1)+" hari lewat </b></blink></span>");	
			}
		}
		else
		{
			//alert ("else");
			if(id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70')
			{
				//alert("id_status3 = " + id_status_semasa );
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
			}
			else if (days3 == '0')
			{
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
			}
			else
			{
				//alert("id_status4 = " + id_status_semasa );
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+" <br>"+(days-beze_hari)+" hari lagi</b></span>");	
			}				
		}
	}
	
} else {
	//alert("id_status5 = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'></span>");
}






</script>

#else

<script>

var temp_tarikh = "";
var temp_tarikh2 = "";
temp_tarikh = document.${formName}.tarikh_mohon_headerppk.value;
//alert (temp_tarikh);
temp_tarikh2 = document.${formName}.TARIKH_MOHON_BICARA_SEMULA.value;
var id_status_semasa = document.${formName}.id_status_semasa.value;
var dateKPISek8 = new Date('2017', '7', '1');
//alert ("dateKPISek8 = "+dateKPISek8);
	var flag_kpi_baru = "no"
	var beze_hari = 0;
	if (temp_tarikh != "" )
	{
		var d1 = parseInt(temp_tarikh.substring(0,2),10);
		var m1 = parseInt(temp_tarikh.substring(3,5),10)-1;
		var y1 = parseInt(temp_tarikh.substring(6,10),10);
		var myDate1 = new Date(y1, m1, d1, 23, 59, 59);
			var tahum_semasa = myDate1.getFullYear();
			if(parseInt(tahum_semasa)>=2013)
			{
				flag_kpi_baru = "yes";	
			}	
			var waktusemasa = new Date();
			var daySemasa = waktusemasa.getDate();
			var monthSemasa = waktusemasa.getMonth();
			var yearSemasa = waktusemasa.getFullYear();
			waktusemasa = new Date(yearSemasa, monthSemasa, daySemasa , 23, 59, 59);
			var ONE_DAY = 1000 * 60 * 60 * 24;
			var difference_ms = Math.abs(waktusemasa - myDate1);
			beze_hari = Math.round(difference_ms/ONE_DAY);
	}
	
	if (temp_tarikh2 != "" )
	{
		var d2 = parseInt(temp_tarikh2.substring(0,2),10);
		var m2 = parseInt(temp_tarikh2.substring(3,5),10)-1;
		var y2 = parseInt(temp_tarikh2.substring(6,10),10);
		var myDate2 = new Date(y2, m2, d2, 23, 59, 59);
			//var myDate2 = new Date(y2, m2, d2);
			var tahum_semasa = myDate2.getFullYear();
			if(parseInt(tahum_semasa)>=2013)
			{
				flag_kpi_baru = "yes";	
			}	
			var waktusemasa = new Date();
			var daySemasa = waktusemasa.getDate();
			var monthSemasa = waktusemasa.getMonth();
			var yearSemasa = waktusemasa.getFullYear();
			waktusemasa = new Date(yearSemasa, monthSemasa, daySemasa , 23, 59, 59);
			var ONE_DAY = 1000 * 60 * 60 * 24;
			var difference_ms = Math.abs(waktusemasa - myDate2);
			beze_hari = Math.round(difference_ms/ONE_DAY);
			

	}



if (temp_tarikh != "" ){
var tarikhHantar = temp_tarikh;
var beza_hari_KPI_Sek8 = (myDate1 - dateKPISek8);
beza_hari_KPI_Sek8 = Math.round(beza_hari_KPI_Sek8/ONE_DAY);

var days = 0;
if('$!headerppk.SEKSYEN' == '8')
{
	if (beza_hari_KPI_Sek8 > 0)
		{
		var days = 120;
		}
	else if((flag_kpi_baru == "yes") && (beza_hari_KPI_Sek8 < 0))
		{
		var days = 135;
		}
	else
		{
		var days = 165;
		}
}
if('$!headerppk.SEKSYEN' == '17')
{

	var tarikhHantar_day = parseInt(tarikhHantar.substring(0,2),10);
	var tarikhHantar_month = parseInt(tarikhHantar.substring(3,5),10)-1;
	var tarikhHantar_year = parseInt(tarikhHantar.substring(6,10),10);
	var tarikhPenghantaran = new Date(tarikhHantar_year, tarikhHantar_month, tarikhHantar_day);
	
	var dateKPISek17 = new Date('2017', '9', '1');
	var beza_hari_KPI_Sek17 = (tarikhPenghantaran - dateKPISek17);
	var ONE_DAY = 1000 * 60 * 60 * 24;
	beza_hari_KPI_Sek17 = Math.round(beza_hari_KPI_Sek17/ONE_DAY);
	
	if (beza_hari_KPI_Sek17 < 0)
		{
		var days = 90;
		}
	else
		{
		var days = 120;
		}
}
var dt1 = parseInt(tarikhHantar.substring(0,2),10) + days;
var mon1 = parseInt(tarikhHantar.substring(3,5),10)-1;
var yr1 = parseInt(tarikhHantar.substring(6,10),10);


var tarikhHantar2 = temp_tarikh2;
var dt2 = parseInt(tarikhHantar2.substring(0,2),10) + days;
var mon2 = parseInt(tarikhHantar2.substring(3,5),10)-1;
var yr2 = parseInt(tarikhHantar2.substring(6,10),10);

var currentTime = new Date();
var myDate = new Date(yr1, mon1, dt1);
var day = myDate.getDate();
var month = myDate.getMonth()+1;
var year = myDate.getFullYear();
var tarikhJangkaTerima = "";
//alert ("currentTime = "+currentTime);
var currentTime2 = new Date();
var myDate2 = new Date(yr2, mon2, dt2);
var day2 = myDate2.getDate();
var month2 = myDate2.getMonth()+1;
var year2 = myDate2.getFullYear();
var tarikhJangkaTerima2 = "";

if(month>=10){
if(day>=10){
tarikhJangkaTerima = day + "/" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/" + month + "/" + year;
}
} else {
if(day>=10){
tarikhJangkaTerima = day + "/0" + month + "/" + year;
} else {
tarikhJangkaTerima = "0"+ day + "/0" + month + "/" + year;
}
}
if(month2>=10){
	if(day2>=10){
	tarikhJangkaTerima2 = day2 + "/" + month2 + "/" + year2;
	} else {
	tarikhJangkaTerima2 = "0"+ day2 + "/" + month2 + "/" + year2;
	}
	} else {
	if(day2>=10){
	tarikhJangkaTerima2 = day2 + "/0" + month2 + "/" + year2;
	} else {
	tarikhJangkaTerima2 = "0"+ day2 + "/0" + month2 + "/" + year2;
	}
	
}

if (day2 >0)
{
if(currentTime>myDate2)
{
	beze_hari = beze_hari-0;
	var days3 = days-beze_hari;
	if((id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70') && (days3 == '0'))
	{
		//alert("id_status6 = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima2+"</b></span>");
	}
	else if(id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70')
	{
		$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
	}
	else if (days3 == '0')
	{
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
	}
	else
	{
		//alert("id_status7 = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'><blink><b>"+tarikhJangkaTerima2+" <br>"+((days-beze_hari)*-1)+" hari lewat </b></blink></span>");	
	}
}
else
{
	if(id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70')
	{
		//alert("id_status8c = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima2+"</b></span>");
	}else
	{
		//alert("id_status9c = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima2+" <br>"+(days-beze_hari)+" hari lagi</b></span>");	
	}				
}
}
else
	{
	beze_hari = beze_hari-0;
	var days3 = days-beze_hari;

		if(currentTime>myDate)
		{
			
			//alert("id_status6b = " + id_status_semasa );
			//alert("days3 = " + days3 );
			if((id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70') && (days3 == '0'))
			{
				
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
			}
			else if(id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70')
			{
				$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
			}
			else if (days3 == '0')
			{
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
			}
			else
			{
				//alert("id_status7b = " + id_status_semasa );
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'><blink><b>"+tarikhJangkaTerima+" <br>"+((days-beze_hari)*-1)+" hari lewat </b></blink></span>");	
			}
		}
		else
		{
			if(id_status_semasa=='21' || id_status_semasa=='152' || id_status_semasa=='47' || id_status_semasa=='70')
			{
				//alert("id_status8b = " + id_status_semasa );
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+"</b></span>");
			}else
			{
				//alert("id_status9b = " + id_status_semasa );
			$jquery("#headerppk_kpi_ppk").html("<span  style='color:blue'><b>"+tarikhJangkaTerima+" <br>"+(days-beze_hari)+" hari lagi</b></span>");	
			}				
		}
	}

} 
else {
	//alert("id_status10 = " + id_status_semasa );
	$jquery("#headerppk_kpi_ppk").html("<span  style='color:red'></span>");
}

</script>

#end

 #if($!headerppk.FLAG_KEBENARAN=="1" && $!headerppk.CHECK_EXPIRY =="Y")
<script>

window.onload = function() {
	
	alert("PERINGATAN: Tempoh kemaskini maklumat telah tamat. \n"+
			"Sekiranya anda ingin membuat pegemaskinian maklumat pada fail ini, sila mohon kebenaran.");
	
}

</script>

#end


<script>
function arkibWindow(noFail){
	
		var url = "../x/${securityToken}/ekptg.view.integrasi.sid.FrmPopupPaparArkibDokumen?kodModul=PPK&noFail="+noFail;
		
		var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
		 if ((document.window != null) && (!hWnd.opener))
		       hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
			
	}
	//razman add
	$jquery(document).ready(function () {
		
		//alert("idPerbicaraan : "+ $jquery('input[name="idPerbicaraan"]').length+" id_perbicaraan : "+$jquery('input[name="id_perbicaraan"]').length);
		
				
		if ($jquery('input[name="idPerbicaraan"]').length == 0 && $jquery('input[name="id_perbicaraan"]').length == 0) {
		   // alert("xada");			
			document.getElementById('linkBicaraOnline').style.display = "none";
		}
		else
		{
			//alert("ada : "+'$!headerppk.USER_ROLE');
			if('$!headerppk.USER_ROLE' == "adminppk")
			{
				
				
				var id_perbicaraan = "";
				
				if('$!flag_jenis_vm' == "vtemplate")
				{
					if ($jquery('input[name="idPerbicaraan"]').length > 0)
					{
						id_perbicaraan = document.f1.idPerbicaraan.value;
					}
					else if ($jquery('input[name="id_perbicaraan"]').length > 0)
					{
						id_perbicaraan = document.f1.id_perbicaraan.value;
					}
				}
				else
				{
					if ($jquery('input[name="idPerbicaraan"]').length > 0)
					{
						id_perbicaraan = document.${formName}.idPerbicaraan.value;
					}
					else if ($jquery('input[name="id_perbicaraan"]').length > 0)
					{
						id_perbicaraan = document.${formName}.id_perbicaraan.value;
					}
				}	
				
				//alert("id_perbicaraan :::: "+id_perbicaraan);		
				if(id_perbicaraan != "")
				{
					document.getElementById('linkBicaraOnline').style.display = "";
				}
				else
				{
					document.getElementById('linkBicaraOnline').style.display = "none";
				}
			}
			else
			{
				document.getElementById('linkBicaraOnline').style.display = "none";
			}
		}
	});
	
</script>

<!--  arief add VIEW LOG TUGASAN FAIL OPEN-->
<script>
function viewLogTugasanFail()
{
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmPopupLogTugasanView?idFail=$idFail"; //&command=LogTugasanView";
	var hWnd = window.open(url,'Cetak','width=625,height=500, resizable=yes,scrollbars=no');
	if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	if (hWnd.focus != null)
		hWnd.focus();
}
</script>
<!--  arief add VIEW LOG TUGASAN FAIL CLOSE-->


