<!--<% //@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %> -->
<meta http-equiv="Content-Script-Type" content="text/javascript">
<style type="text/css">
<!--
.style1 {color: #FF0000}
.style40 {color: #0000FF}
.style2 {
	font-size: 9px;
	font-style: italic;FrmBorangPSek17Online
}
.style3 {color: #0000FF}
-->
</style>
<head>
#set ($checked1 = "")
#set ($checked2 = "")
#set ($checked3 = "")
#set ($checked4 = "")
#set ($checked5 = "")
#set ($txtchecked5 = "")
#set ($txtchecked8 = "")
#set ($txtchecked9 = "")
#set ($txtchecked9d = "")
#set ($checked6 = "")
#set ($checked7 = "")
#set ($checked8 = "")
#set ($checked9 = "")
#set ($checked10 = "")
#set ($checked11 = "")
#set ($checked12 = "")
#set ($checked13 = "")
#set ($checked14 = "")
#set ($checked15 = "")
#set ($checked16 = "")
#set ($checked17 = "")
#set ($checked18 = "")
#set ($checked19 = "")
#set ($checked20 = "")
#set ($checked21 = "")
#set ($checked22 = "")
#set ($checked23 = "")
#set ($checked24 = "")
#set ($txtchecked23 = "")
#set ($chkmode = "")
#set($idpermohonan_baru = "")

#if ($SimpanStatus == "2")
#set ($chkmode = "disabled")
#else 
#set ($chkmode = "")
#end

#if ($SimpanStatus == "1" ||$SimpanStatus == "2" )
	#foreach($List in $ListSemakan)
    #set($idpermohonan_baru = $List.id_permohonan)
 
		#if ($List.idsemakansenarai == "1")
			#set ($checked1 = "checked")
		#end
		#if ($List.idsemakansenarai == "2")
			#set ($checked2 = "checked")
		#end
		#if ($List.idsemakansenarai == "3")
			#set ($checked3 = "checked")
		#end	
		#if ($List.idsemakansenarai == "4")
			#set ($checked4 = "checked")
		#end
		#if ($List.idsemakansenarai == "5")
			#set ($checked5 = "checked")
			#set ($txtchecked5 = $List.Catatan)
		#end
		#if ($List.idsemakansenarai == "6")
			#set ($checked6 = "checked")
		#end
		#if ($List.idsemakansenarai == "7")
			#set ($checked7 = "checked")
		#end
		#if ($List.idsemakansenarai == "8")
			#set ($checked8 = "checked")
			#set ($txtchecked8 = $List.Catatan)
		#end
		#if ($List.idsemakansenarai == "9")
			#set ($checked9 = "checked")
			#set ($txtchecked9 = $List.Catatan)
			#set ($txtchecked9d = $List.tarikh_pelbagai)
             
            #set ($txtNomborResit = $List.Catatan)
			#set ($txdTarikhByrn = $List.tarikh_pelbagai)
		#end
		#if ($List.idsemakansenarai == "10")
			#set ($checked10 = "checked")
		#end
		#if ($List.idsemakansenarai == "11")
			#set ($checked11 = "checked")
		#end
		#if ($List.idsemakansenarai == "12")
			#set ($checked12 = "checked")
		#end
		#if ($List.idsemakansenarai == "13")
			#set ($checked13 = "checked")
		#end
		#if ($List.idsemakansenarai == "14")
			#set ($checked14 = "checked")
		#end
		#if ($List.idsemakansenarai == "15")
			#set ($checked15 = "checked")
		#end
		#if ($List.idsemakansenarai == "16")
			#set ($checked16 = "checked")
		#end
		#if ($List.idsemakansenarai == "17")
			#set ($checked17 = "checked")
            #set ($txtchecked23 = $List.Catatan)
		#end
		
		
	#end
#end
</head>
<body  onload="submitForm();" >



#set($noFail = "")
#foreach($list in $View)
#set($noFail = $list.noFail)
#set($idPemohon = $list.idPemohon)
#set($id_Status = $list.id_Status)
#end
<form name="f1" method="post" action="" >

<table width="100%">
<tr>
<td>

#set($temp_maklumat_permohonan = "$temp_maklumat_permohonan")
#if($!skrin_online_17 == "yes")
#set($temp_maklumat_permohonan = "Maklumat Borang P")
#end



#if($pendaftaran == "")
<div align="right">bbb#if($pendaftaran == "")#if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView  == '')<a href="javascript:javascript:Kembali()" class="style3" ><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a>#end#if($flagFromSenaraiFailSek8 == 'yes')<a href="javascript:javascript:kembaliSenaraiFail('$noFail')" class="style3" ><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan" /></a>#end#if ($flagFromSenaraiPermohonanSek8 == 'yes')<a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style3" ><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan" /></a>#end#if($flagForView  == 'yes')<a href="javascript:ForView('$noFail')" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#end<img src="../img/2current.png" alt="" border="0" title="Senarai Semak" /><img src="../img/arrowgaris.png" alt="" border="0"/>#if($daftar=="yes")#if($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView  == '')<a href="javascript:Seterusnya_X()" class="style3" ><img src="../img/3enable.png" alt="" border="0" title="Pendaftaran Permohonan"/></a>#else<a href="javascript:Seterusnya()" class="style3" ><img src="../img/3enable.png" alt="" border="0" title="Pendaftaran Permohonan"/></a>#end#else<img src="../img/3disable2.png" alt="" border="0" title="Pendaftaran Permohonan" />#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($dah_daftar_ke == "sudah")#if($pendaftaran == "")<a href="javascript:tabS()" class="style3"><img src="../img/4enable2.png" alt="" border="0" title="$temp_maklumat_permohonan"/></a>#else<a href="javascript:tab()" class="style3"><img src="../img/4enable2.png" alt="" border="0"  title="$temp_maklumat_permohonan"/></a>#end#else<img src="../img/4disable2.png" alt="" border="0"  title="$temp_maklumat_permohonan" />#end</div>
#else
<div align="right"><img src="../img/1current.png" alt="" border="0" title="Senarai Semak" /><img src="../img/arrowgaris.png" alt="" border="0"/>#if($daftar=="yes")<a href="javascript:Seterusnya_X()" class="style3" ><img src="../img/2enable.png" alt="" border="0" title="Pendaftaran Permohonan"/></a>#else<img src="../img/2disable.png" alt="" border="0" title="Pendaftaran Permohonan" />#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($dah_daftar_ke == "sudah")#if($pendaftaran == "")<a href="javascript:tabS()" class="style3"><img src="../img/3enable2.png" alt="" border="0" title="$temp_maklumat_permohonan"/></a>#else<a href="javascript:tab()" class="style3"><img src="../img/3enable2.png" alt="" border="0"  title="$temp_maklumat_permohonan"/></a>#end#else<img src="../img/3disable.png" alt="" border="0"  title="$temp_maklumat_permohonan" />#end</div>
#end
</td>
</tr>
</table>




<div id="tab_Semak"></div>
#parse("app/ppk/info_paging_online.jsp")


<fieldset >
<legend><!--$temp_maklumat_permohonan Awal-->Maklumat Permohonan Terdahulu</legend>
<table border="0" align="center" width="100%">
    <tbody>
      <tr style="display:none">
        <td width="1%" align="left" style="text-transform:uppercase;">&nbsp;</td>
        <td width="25%" align="left" style="text-transform:uppercase;">No Permohonan Online</td> 
        <td width="1%"><div align="center">:</div></td>
        <td width="73%" style="text-transform:uppercase;"><div class="style40">$!nopermohonan
        </div></td>
      </tr>
      <tr>
        <td  width="1%" align="left" style="text-transform:uppercase;">&nbsp;</td>
        <td width="25%" align="left" style="text-transform:uppercase;">No Fail Lama</td> 
        <td  width="1%" align="left"><div align="center">:</div></td>
        <td width="73%" style="text-transform:uppercase;"><div class="style40">$!nofaillama 
        </div></td>
      </tr>
       <tr>
         <td  align="left" style="text-transform:uppercase;">&nbsp;</td>
         <td  align="left" style="text-transform:uppercase;">Tempat Permohonan Lama </td> 
        <td ><div align="center">:</div></td>
        <td  style="text-transform:uppercase;"><div class="style40">$!daerah - $!negeri</div></td>
      </tr>
       <tr>
         <td  valign="middle" align="left" style="text-transform:uppercase;">&nbsp;</td>
         <td valign="middle" align="left" style="text-transform:uppercase;">Nama Pemohon </td> 
        <td ><div align="center">:</div></td>
        <td  style="text-transform:uppercase;"><div class="style40">$!pemohon</div></td>
      </tr>
      </tbody>
      </table>
</fieldset>

<br>
<fieldset>
#parse("app/ppk/info_berjaya_disimpan.jsp")

#if($!dah_daftar_ke != "sudah")
#set($pesanan_senarai_semak = "")
#if($ListSemakan.size() > 0)
#set($pesanan_senarai_semak = "<blink>*</blink> <font color='black'>Sila Klik Pada Butang (2)</font> untuk Meneruskan Permohonan Online Ini.")
#else
#set($pesanan_senarai_semak = "<blink>*</blink> Sila Pastikan Tujuan Permohonan Borang P Dipilih untuk Meneruskan Permohonan Online ini.<br><blink>*</blink> Apabila Maklumat Tujuan Permohonan Borang P Berjaya Disimpan, Pengguna Dibenarkan untuk Meneruskan Proses Permohonan.")
#end
#else
#set($pesanan_senarai_semak = "<blink>*</blink> Sila Klik Pada Butang <font color='black'>(2)</font> atau <font color='black'>(3)</font> untuk Mengemaskinian dan Penambahan Maklumat Permohonan.")
#end


<div id="info_senaraisemak"></div>
<script>
parent.document.getElementById("info_senaraisemak").innerHTML="<div class=\"warning_online_ppk\"><b>$pesanan_senarai_semak</b></div>";
</script>




<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
 <input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
 <input name="flagForView" type="hidden" id="flagForView" value="$!flagForView"/>




 #foreach($View in $ViewLamaSub)
        #set ($no_subjaket = $View.no_subjaket)
 #end  
 
 
 <input name="no_subjaket" type="hidden" value="$no_subjaket" />
 <input type="hidden" name="dah_daftar_ke" id="dah_daftar_ke" value="$dah_daftar_ke" />   
 <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
 <input name="idtempTerdahulu" type="hidden" value="$idpermohonan" />
<input name="idpermohonan" type="hidden" value="$idpermohonan" />
<input name="idpermohonan_baru" type="hidden" value="$idpermohonan_baru" />



#foreach($xx in $View)
#set($idSimati = $xx.idSimati)
#end


<input name="idSimati" type="hidden" value="$idSimati" />
<input name="id_Simati" type="hidden" value="$idSimati" />
 
<input name="txtNomborResitH" type="hidden" value="$txtNomborResit" />
<input name="txdTarikhByrnH" type="hidden" value="$txdTarikhByrn" />



#foreach($listxx in $View_PermohonanBaru)
#set($bkp = $listxx.bkp)
#set($lp = $listxx.lp)
#set($bpa = $listxx.bpa)
#set($lpa = $listxx.lpa)
#set($ht = $listxx.ht)
#set($lt = $listxx.lt)
#end
<!--
batal pentadbir ::: $bkp
lantik pentadbir ::: $lp
batal amanah ::: $bpa
lantik amanah ::: $lpa
harta tertingal ::: $ht
lain tujuan ::: $lt
-->

<input name="bkp" type="hidden" value="$bkp" />
<input name="lp" type="hidden" value="$lp" />
<input name="bpa" type="hidden" value="$bpa" />
<input name="lpa" type="hidden" value="$lpa" />
<input name="ht" type="hidden" value="$ht" />
<input name="lt" type="hidden" value="$lt" />
<!--
   #if($chkmode == "disabled")
   #set($chkmodeR = "readonly") 
   #else 
   #set($chkmodeR = "") 
   #end
-->   
 #set($chkmodeR = "") 
 #set($chkmode = "")   
   
  <table width="100%" border="0">
    <tr>
      <td width="1%" valign="top"><table width="100%" border="0" style="display:none">
        <tr>
          <td><table width="100%" border="0">
            <tr>
              <td width="10%">&nbsp;</td>
              <td width="80%"><strong>Senarai Semak</strong></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td><table width="100%" border="0">
            <tr>
              <td width="14%">&nbsp;</td>
              <td width="1%" valign="top" > #if($chkmode != "disabled")<span class="style1">*</span>#end</td>
              <td width="3%">
                <input name="cbsemaks" type="checkbox" id="cbsemaks" $chkmode value="1" $checked1 />              </td>
              <td width="82%">
            
              #if($chkmode != "disabled")Borang P lengkap diisi #else
              Borang P lengkap diisi<span class="style1"> </span>              #end</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td valign="top">#if($chkmode != "disabled")<span class="style1">*</span>#end</td>
               <td width="3%">
             <input name="cbsemaks" type="checkbox" id="cbsemaks" $chkmode value="2" $checked2 onClick="ReadOnlyCheckBox(this);checkit2()" />              </td>
              <td>
#if($chkmode != "disabled")Dokumen Sokongan#else
              Dokumen Sokongan  #end</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td><table width="100%" border="0">
            <tr>
              <td width="20%">&nbsp;</td>
              <td width="3%"><input name="cbsemaks" type="checkbox" id="cbsemaks" $chkmode value="3" $checked3 onClick="checkit3()" /></td>
              <td width="77%">
                Salinan perintah terdahulu</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
               <td width="3%"> <input type="checkbox" name="cbsemaks" $checked4 $chkmode  value="4" id="cbsemaks" onClick="checkit4()" /></td>
              <td>
               
              Carian Rasmi / Cabutan dari no hakmilik</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
               <td width="3%"><input type="checkbox" name="cbsemaks" $checked5 $chkmode  value="5" id="cbsemaks" onClick="checkit5()" /></td>
              <td>
                
              Penyata Akaun / Saham</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
                 <td width="3%"><input type="checkbox" name="cbsemaks" $checked6 $chkmode  value="6" id="cbsemaks" onClick="checkit6()" /></td>
              <td>
                
              Pendaftaran Kenderaan</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td width="3%">   <input type="checkbox" name="cbsemaks" $checked7 $chkmode value="7" id="cbsemaks" onClick="checkit7()" /></td>
              <td>
             
              Perjanjian jualbeli harta</span></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td><table width="100%" border="0">
            <tr>
              <td width="14%">&nbsp;</td>
              <td width="1%">&nbsp;</td>
              <td width="3%"><input name="cbsemaks" type="checkbox" id="cbsemaks4" $chkmode value="8" $checked8 /></td>
              <td width="82%">
                Melantik Peguam</td>
                
                   
                    
                    
            #if($listb.size() > 0)
  #foreach($List1 in $listb)
  #set($lepassatusept = $List1.lepassatusept )
  
 
 #end
 #else
 #set($lepassatusept = "no" )
 #end
               <input type="hidden" name="lepassatusept" id="lepassatusept" value="$lepassatusept" />
      				 #if($lepassatusept == "no" )
                    <label id="divCheckbox"  style="visibility: hidden;" >
                    <input name="cbsemaks" $chkmode type="checkbox" id="cbsemaks5" value="9" $checked9 onClick="checkit9()"/>                 
                    <input type="hidden" name="txtNomborResit" id="txtNomborResit" size="15"  maxlength="20" value="" />  
                    <input type="hidden" name="txdTarikhByrn" id="txdTarikhByrn" size="15"  maxlength="20" value="" />     
                    </label> 
     				  #end            </tr>
            
            
            
            #if($lepassatusept == "yes" )
            
            <tr>
              <td>&nbsp;</td>
              <td valign="top"> #if($chkmode != "disabled")<span class="style1">*</span> #end</td>
              <td width="3%"><input name="cbsemaks" $chkmode type="checkbox" id="cbsemaks5" value="9" $checked9 onClick="checkit9()"/></td>
              <td>
                #if($chkmode != "disabled")Bayaran Permohonan #else
              Bayaran Permohonan  #end</td>
            </tr>
          </table></td>
          
          
          
        </tr>
        <tr>
          <td><table width="100%" border="0">
            <tr>
              <td width="10%">&nbsp;</td>
              <td width="20%"><div align="right">                No Resit  </div></td>
              <td width="1%">:</td>
              <td width="49%"><input type="text" name="txtNomborResit" id="txtNomborResit" size="20"  maxlength="15" $chkmodeR class="$chkmode" value="$txtchecked9" onKeyUp="checkitD()" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase()"  /></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><div align="right"> Tarikh Bayaran</div></td>
              <td>:</td>
              <td><label>
                <input type="text" name="txdTarikhByrn" id="txdTarikhByrn" size="11" maxlength="10"  value="$txtchecked9d" $chkmodeR class="$chkmode"  onFocus="checkitX()"  onBlur="trans_date(this.value)"  />
#if ($SimpanStatus != "2") <a href="javascript:displayDatePicker('txdTarikhByrn',false,'dmy');" >#parse("app/ppk/ppk_calender.jsp")</a> #end </label></td>
            </tr>
            
            #end
          </table></td>
        </tr>
      </table></td>
      <td width="40%" valign="top"   >
      
      
      <fieldset>
      <legend><strong>Tujuan Permohonan Borang P</strong></legend>
      <table width="100%" border="0" >
        <tr>
          <td >
          
          
          <table width="100%" border="0">
            <tr>
              <td width="5%"></td>
              <td width="95%"></td>
              
              
              
              
            </tr>
          </table></td>
        </tr>
        <tr>
          <td><table width="100%" border="0">
            <tr>
           
              <td width="10%">&nbsp;</td>
              <td width="3%"><input name="cbsemaks" type="checkbox" id="cbsemaks" $chkmode  $checked10 value="10"  /></td>
              <td width="87%">
                Harta tertinggal di permohonan awal</td>
            </tr>
            #set($pa = "")
            #set($pt = "")
            <!--
            listsemakhta :: $listsemakhta
            listsemakha :: $listsemakha
            -->
            #foreach ($sm in $listsemakhta)
            #if($sm.flag_pa == "Y")
            #set($pa = "ada")
            #end
            #if($sm.flag_pt == "Y")
            #set($pt = "ada")
            #end          
            #end
            
             #foreach ($sa in $listsemakha)
            #if($sa.flag_pa == "Y")
            #set($pa = "ada")
            #end
            #if($sa.flag_pt == "Y")
            #set($pt = "ada")
            #end          
            #end
            
            
         
        
            <tr id="amanah">
              <td>&nbsp;</td>
              <td width="3%"><input name="cbsemaks" type="checkbox" id="cbsemaks6" $chkmode value="11" $checked11 onClick="ReadOnlyCheckBox(this);checkit11()" /></td>
              <td>
                Pemegang Amanah</td>
            </tr>
          </table></td>
        </tr>
        <tr id="anak_amanah">
          <td><table width="100%" border="0">
            <tr>
              <td width="15%">&nbsp;</td>
               <td width="3%"><input name="cbsemaks" type="checkbox" id="cbsemaks1" $chkmode value="12" $checked12 onClick="checkit12()" /></td>
              <td width="82%">
                Batal</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><input name="cbsemaks" type="checkbox" id="cbsemaks7" $chkmode value="13" $checked13 onClick="checkit13()" /></td>
              <td>
                Lantik Baru</td>
            </tr>
          </table></td>
        </tr>
        <tr id="tadbir">
          <td><table width="100%" border="0">
            <tr>
              <td width="10%">&nbsp;</td>
               <td width="3%"><input name="cbsemaks" type="checkbox" id="cbsemaks8" $chkmode value="14" $checked14 onClick="ReadOnlyCheckBox(this);checkit14()" /></td>
              <td width="87%">
                Pemegang Surat Kuasa Tadbir</td>
            </tr>
          </table></td>
        </tr>
        <tr id="anak_tadbir">
          <td><table width="100%" border="0">
            <tr>
              <td width="15%">&nbsp;</td>
               <td width="3%"><input name="cbsemaks" type="checkbox" id="cbsemaks9" $chkmode value="15" $checked15 onClick="checkit15()" /></td>
              <td width="82%">
                Batal</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
               <td width="3%"> <input name="cbsemaks" type="checkbox" id="cbsemaks10" $chkmode value="16" $checked16 onClick="checkit16()" /></td>
              <td>
             
                Lantik baru</td>
            </tr>
          </table></td>
        </tr>
        
       <tr>
          <td><table width="100%" border="0">
            <tr>
           
              <td width="11%">&nbsp;</td>
              <td width="3%"><input name="cbsemaks" type="checkbox" id="cbsemaks17" $chkmode value="17" $checked17 onClick="checkit17()" /></td>
              <td width="87%">
               Lain-lain tujuan</td>
            </tr>
         
        
            <tr id="amanah">
              <td>&nbsp;</td>
              <td width="3%"></td>
              <td>
                <div align="left">
                  <textarea name="txtLainLainTujuan" id="txtLainLainTujuan" $chkmodeR class="$chkmode" cols="40" rows="2" style="text-transform:uppercase;" onKeyUp="checkit18()" >$txtchecked23</textarea>
                   </div></td>
            </tr>
          </table></td>
        </tr>
        
      </table>
      </fieldset>
     
      
      
      </td>
      
       <td width="60%" valign="top"   >
     <br>
      <fieldset>
     
      <table width="100%" border="0">
        
          <!-- <tr>
      	<td  valign="top" width="1%">&nbsp;</td>
		<td  valign="top" width="1%"></td>
	    <td  valign="top" width="1%"></td>
      	<td  width="97%" >
      
        <b>(1) Maklumat perintah terdahulu.</b></td>
	</tr>
    
        <tr>
      	<td  valign="top">&nbsp;</td>
		<td  valign="top"></td>
	    <td  valign="top"></td>
      	<td  ><textarea cols="60" rows="5" name="perintahdahulu" $chkmodeR class="$chkmode" onKeyUp="textCounter(this.form.perintahdahulu,this.form.remPerintahDahulu,2000);" onKeyDown="textCounter(this.form.perintahdahulu,this.form.remPerintahDahulu,2000);" >$!perintahlama</textarea></td>
	</tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td  valign="top">&nbsp;</td>
        <td ><input type="text" readonly class="disabled" name="remPerintahDahulu" size="3" maxlength="4" value="2000"> Baki Aksara </td>
    </tr> -->  
    <tr>
	  	<td  valign="top">&nbsp;</td>
		<td  valign="top"></td>
	    <td  valign="top"></td>
      	<td >
        <br>#if($chkmode != "disabled")<span class="style1">*</span>#end
        <b>Masukkan Permohonan Khusus Yang Dipohon</b></td>
	</tr>  
	<tr>
	  	<td  valign="top">&nbsp;</td>
		<td  valign="top"></td>
	    <td  valign="top"></td>
      	<td ><textarea cols="60" rows="5" name="perintahminta" $chkmodeR class="$chkmode"  onkeyup="textCounter(this.form.perintahminta,this.form.remPerintahMinta,2000);" onKeyDown="textCounter(this.form.perintahminta,this.form.remPerintahMinta,2000);" >$!perintahbaru</textarea></td>
	</tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td  valign="top"></td>
      <td ><input type="text" readonly class="disabled" name="remPerintahMinta" size="3" maxlength="4" value="2000"> Baki Aksara </td>
    </tr>
        </table>
      </fieldset>
      
      </td>
    </tr>
  </table>

  
  <!--
  			#if($pt == "")
            <script>
			
			document.getElementById('tadbir').style.display="none";
			document.getElementById('anak_tadbir').style.display="none";
			</script>
            #end
            
            #if($pa == "")
            <script>
			document.getElementById('amanah').style.display="none";
			document.getElementById('anak_amanah').style.display="none";
			</script>
            #end
         
         -->
            
  #if($chkmode != "disabled")
<table width="100%">
  <tr>
    <td><!--<span class="style2"><span class="style1">Perhatian</span> : Sila pastikan label bertanda <span class="style1">*</span> diisi.</span>--></td>
  </tr>
</table>
#end

<!-- <div id="frmsemakharta" style="display:none">
#parse("app/ppk/FrmSemakHartaSek17.jsp")
</div> -->

<!-- #parse("app/ppk/FrmSemakHartaSek17.jsp") -->

  <p align="center">
<label>
<input type="hidden" name="idtemp" value="$IdPermohonan"/>
<input type="hidden" name="idPermohonan" value="$IdPermohonan"/>
<input type="hidden" name="negid" value="$NegId"/>
<input type="hidden" name="idFlag"/>
<input type="hidden" name="idUser" value="$userid"/>
<input type="hidden" name="eventStatus" value="$EventStatus"/>
<input type="hidden" name="flagno"/>
<input type="hidden" name="GetNewPemohon" value="$GetNewPemohon"/>
<input type="hidden" name="command" />
#if ($SimpanStatus == "2")

    #if($id_Status != "169" && $id_Status != "21" && $id_Status != "64" && $id_Status != "163" && $id_Status != "164" && $id_Status != "165")

	#if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView  == '')
	<!-- <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini1()"/>  -->
    #else
    
  <!--  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/>  -->
    #end
    #end 
    
    #parse("app/ppk/syarat_kemaskini.jsp")
	#if($boleh_kemaskini == "yes")
    
    #if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView  == '')
    #if($pendaftaran == 'yes')
   <!-- <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini1()"/>  -->
    #else
    <!-- <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/> -->
    #end
	  
    #else     
   <!--  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/> -->
    #end
    
	#end
    
    
    
     
    <!--
    <input name="cmdSeterusnya" type="button"  id="cmdSeterusnya" value="Seterusnya" onClick="Seterusnya()"/>
    -->
    
#else
<script>
document.getElementById('frmsemakharta').style.display = "";
</script>
<!--
	<input name="cmdSimpan" type="button" id="cmdSimpan"  value="Simpan" onClick="DoTheCheck()" />
	<input name="cmdBatal" type="reset"  id="cmdBatal" value="Batal"/> -->
#end</label>
  <input name="cmdSimpan" type="button" id="cmdSimpan"  value="Simpan" onClick="DoTheCheck()" />
  
  #if($pendaftaran != "")
  #if($daftar=="yes")
  
  #end 
  #end
  
  
  #if($dah_daftar_ke == "sudah")
  #if($pendaftaran != "")
   
  #end
  #end
  
  
  
  
  
  
  
  </fieldset>
	<!--<input name="cmdBatal" type="reset"  id="cmdBatal" value="Batal"/>-->
</form>
<!--
 <p align="right">CL - 01 - 45</p>
 -->


 
 #if($pendaftaran == "")
<div align="right">#if($pendaftaran == "")#if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView  == '')<a href="javascript:javascript:Kembali()" class="style3" ><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a>#end#if($flagFromSenaraiFailSek8 == 'yes')<a href="javascript:javascript:kembaliSenaraiFail('$noFail')" class="style3" ><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan" /></a>#end#if ($flagFromSenaraiPermohonanSek8 == 'yes')<a href="javascript:kembaliSenaraiPermohonan('$noFail')" class="style3" ><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan" /></a>#end#if($flagForView  == 'yes')<a href="javascript:ForView('$noFail')" class="style40"><img src="../img/1enable.png" alt="" border="0" title="Senarai Permohonan"/></a>#end<img src="../img/arrowgaris.png" alt="" border="0"/>#end<img src="../img/2current.png" alt="" border="0" title="Senarai Semak" /><img src="../img/arrowgaris.png" alt="" border="0"/>#if($daftar=="yes")#if($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '' && $flagForView  == '')<a href="javascript:Seterusnya_X()" class="style3" ><img src="../img/3enable.png" alt="" border="0" title="Pendaftaran Permohonan"/></a>#else<a href="javascript:Seterusnya()" class="style3" ><img src="../img/3enable.png" alt="" border="0" title="Pendaftaran Permohonan"/></a>#end#else<img src="../img/3disable2.png" alt="" border="0" title="Pendaftaran Permohonan" />#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($dah_daftar_ke == "sudah")#if($pendaftaran == "")<a href="javascript:tabS()" class="style3"><img src="../img/4enable2.png" alt="" border="0" title="$temp_maklumat_permohonan"/></a>#else<a href="javascript:tab()" class="style3"><img src="../img/4enable2.png" alt="" border="0"  title="$temp_maklumat_permohonan"/></a>#end#else<img src="../img/4disable2.png" alt="" border="0"  title="$temp_maklumat_permohonan" />#end</div>
#else
<div align="right"><img src="../img/1current.png" alt="" border="0" title="Senarai Semak" /><img src="../img/arrowgaris.png" alt="" border="0"/>#if($daftar=="yes")<a href="javascript:Seterusnya_X()" class="style3" ><img src="../img/2enable.png" alt="" border="0" title="Pendaftaran Permohonan"/></a>#else<img src="../img/2disable.png" alt="" border="0" title="Pendaftaran Permohonan" />#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($dah_daftar_ke == "sudah")#if($pendaftaran == "")<a href="javascript:tabS()" class="style3"><img src="../img/3enable2.png" alt="" border="0" title="$temp_maklumat_permohonan"/></a>#else<a href="javascript:tab()" class="style3"><img src="../img/3enable2.png" alt="" border="0"  title="$temp_maklumat_permohonan"/></a>#end#else<img src="../img/3disable.png" alt="" border="0"  title="$temp_maklumat_permohonan" />#end</div>
#end
 
 </body>
<script>

function submitForm() {    
//alert('$val_tab')
if('$!val_tab' != "" && '$!val_tab' != null)
{

   window.location.hash='$!val_tab';
   goTo('$!val_tab');
   }
   else
{
window.location.hash='tab_Semak';
goTo('tab_Semak');
}
	
} 


function checkit1(){
	if (document.f1.cbsemaks[0].checked == true){
		  document.f1.cbsemaks[0].readonly = true;
		  document.f1.cbsemaks[2].checked = true;
	}
	if (document.f1.cbsemaks[0].checked == false){
		  document.f1.cbsemaks[2].checked = false;
		  document.f1.cbsemaks[4].checked = false;
	}
	
}
function checkit2(){
	if (document.f1.cbsemaks[1].checked == true){
		  document.f1.cbsemaks[2].focus();
		   document.f1.cbsemaks[2].checked = true;
		    document.f1.cbsemaks[3].checked = false;
			 document.f1.cbsemaks[4].checked = false;
			  document.f1.cbsemaks[5].checked = false;
			   document.f1.cbsemaks[6].checked = false;

	}
	
	if (document.f1.cbsemaks[1].checked == false){
		  document.f1.cbsemaks[2].focus();
		   document.f1.cbsemaks[2].checked = false;
		    document.f1.cbsemaks[3].checked = false;
			 document.f1.cbsemaks[4].checked = false;
			  document.f1.cbsemaks[5].checked = false;
			   document.f1.cbsemaks[6].checked = false;

	}
	
}
function checkit3(){
    if (document.f1.cbsemaks[2].checked == true){
		  document.f1.cbsemaks[1].checked = true;
	}	
	
	if (document.f1.cbsemaks[2].checked == false && document.f1.cbsemaks[3].checked == false && document.f1.cbsemaks[4].checked == false && document.f1.cbsemaks[5].checked == false && document.f1.cbsemaks[6].checked == false){
	
		  document.f1.cbsemaks[1].checked = false;
	}else if (document.f1.cbsemaks[2].checked == false && (document.f1.cbsemaks[3].checked == true || document.f1.cbsemaks[4].checked == true || document.f1.cbsemaks[5].checked == true || document.f1.cbsemaks[6].checked == true)){
	
		  document.f1.cbsemaks[1].checked = true;
	}
	
	
}


function checkit4(){
    if (document.f1.cbsemaks[3].checked == true){
		  document.f1.cbsemaks[1].checked = true;
	}	
	
	if (document.f1.cbsemaks[2].checked == false && document.f1.cbsemaks[3].checked == false && document.f1.cbsemaks[4].checked == false && document.f1.cbsemaks[5].checked == false && document.f1.cbsemaks[6].checked == false){
	
		  document.f1.cbsemaks[1].checked = false;
	}else if (document.f1.cbsemaks[3].checked == false && (document.f1.cbsemaks[2].checked == true || document.f1.cbsemaks[4].checked == true || document.f1.cbsemaks[5].checked == true || document.f1.cbsemaks[6].checked == true)){
	
		  document.f1.cbsemaks[1].checked = true;
	}
	
	
}


function checkit5(){
    if (document.f1.cbsemaks[4].checked == true){
		  document.f1.cbsemaks[1].checked = true;
	}	
	
	if (document.f1.cbsemaks[2].checked == false && document.f1.cbsemaks[3].checked == false && document.f1.cbsemaks[4].checked == false && document.f1.cbsemaks[5].checked == false && document.f1.cbsemaks[6].checked == false){
	
		  document.f1.cbsemaks[1].checked = false;
	}else if (document.f1.cbsemaks[4].checked == false && (document.f1.cbsemaks[2].checked == true || document.f1.cbsemaks[3].checked == true || document.f1.cbsemaks[5].checked == true || document.f1.cbsemaks[6].checked == true)){
	
		  document.f1.cbsemaks[1].checked = true;
	}
	
	
}



function checkit6(){
    if (document.f1.cbsemaks[5].checked == true){
		  document.f1.cbsemaks[1].checked = true;
	}	
	
	if (document.f1.cbsemaks[2].checked == false && document.f1.cbsemaks[3].checked == false && document.f1.cbsemaks[4].checked == false && document.f1.cbsemaks[5].checked == false && document.f1.cbsemaks[6].checked == false){
	
		  document.f1.cbsemaks[1].checked = false;
	}else if (document.f1.cbsemaks[5].checked == false && (document.f1.cbsemaks[2].checked == true || document.f1.cbsemaks[3].checked == true || document.f1.cbsemaks[4].checked == true || document.f1.cbsemaks[6].checked == true)){
	
		  document.f1.cbsemaks[1].checked = true;
	}
		
}

function checkit7(){
    if (document.f1.cbsemaks[6].checked == true){
		  document.f1.cbsemaks[1].checked = true;
	}	
	
	if (document.f1.cbsemaks[2].checked == false && document.f1.cbsemaks[3].checked == false && document.f1.cbsemaks[4].checked == false && document.f1.cbsemaks[5].checked == false && document.f1.cbsemaks[6].checked == false){
	
		  document.f1.cbsemaks[1].checked = false;
	}else if (document.f1.cbsemaks[6].checked == false && (document.f1.cbsemaks[2].checked == true || document.f1.cbsemaks[3].checked == true || document.f1.cbsemaks[4].checked == true || document.f1.cbsemaks[5].checked == true)){
	
		  document.f1.cbsemaks[1].checked = true;
	}
		
}
function checkit8(){
	if (document.f1.cbsemaks[6].checked == true){
		  document.f1.cbsemaks[8].checked = true;
		  document.f1.cbsemakradio[0].checked = true;
	}
	if (document.f1.cbsemaks[6].checked == false){
		  document.f1.cbsemakradio[0].checked = false;
		  document.f1.cbsemakradio[1].checked = false;
		  document.f1.cbsemakradio[2].checked = false;
		  document.f1.cbsemakradio[3].checked = false;
		  document.f1.cbsemaks[8].checked = false;
		  document.f1.cbsemaks[11].checked = false;
		  document.f1.cbsemaks[13].checked = false;
		  document.f1.cbsemaks[15].checked = false;
		  document.f1.txtNomborSijil.value = "";
		  document.f1.txtNomborPermit.value = "";
	}
}
function checkit9(){

	if (document.f1.cbsemaks[8].checked == true){
	  document.f1.txtNomborResit.focus();
	  document.f1.txtNomborResit.value = "";
	  document.f1.txdTarikhByrn.value = "";		  
		
	}
	
	if (document.f1.cbsemaks[8].checked == false){
	  document.f1.txtNomborResit.focus();
	  document.f1.txtNomborResit.value = "";
	  document.f1.txdTarikhByrn.value = "";		  
		
	}
	
}

function checkitD(){
	if (document.f1.txtNomborResit.value != ""){
		 document.f1.cbsemaks[8].checked = true;
	}
	if (document.f1.txtNomborResit.value == "" &&  document.f1.txdTarikhByrn.value == "" ){
		 document.f1.cbsemaks[8].checked = false;
	}
	else if(document.f1.txtNomborResit.value == "" &&  document.f1.txdTarikhByrn.value != "" ){
		 document.f1.cbsemaks[8].checked = true;
	}
	
}

function checkitX(){
	if (document.f1.txdTarikhByrn.value.length > 0) {
		 document.f1.cbsemaks[8].checked = true;
	}
	else if (document.f1.txdTarikhByrn.value == "") {
	
	if (document.f1.txtNomborResit.value != "")
	{
		 document.f1.cbsemaks[8].checked = true;
	}
	else if (document.f1.txtNomborResit.value == ""){
		 document.f1.cbsemaks[8].checked = false;
	}
	}
}

function checkit11(){
	if (document.f1.cbsemaks[10].checked == true){
	      document.f1.cbsemaks[11].focus();
		  document.f1.cbsemaks[11].checked = true;
		  document.f1.cbsemaks[12].checked = false;
	}
	else if(document.f1.cbsemaks[10].checked == false){
	      document.f1.cbsemaks[11].focus();
		  document.f1.cbsemaks[11].checked = false;
		  document.f1.cbsemaks[12].checked = false;
	}
	
}

function checkit12(){
	if (document.f1.cbsemaks[11].checked == true){
		  document.f1.cbsemaks[10].checked = true;
		
	}
	else if (document.f1.cbsemaks[11].checked == false && document.f1.cbsemaks[12].checked == false){
	 document.f1.cbsemaks[10].checked = false;
		 
	}
}

function checkit13(){
	if (document.f1.cbsemaks[12].checked == true){
		  document.f1.cbsemaks[10].checked = true;		
	}
	else if (document.f1.cbsemaks[12].checked == false && document.f1.cbsemaks[11].checked == false){
	 document.f1.cbsemaks[10].checked = false;
		 
	}
}


function checkit14(){
	if (document.f1.cbsemaks[13].checked == true){
	      document.f1.cbsemaks[14].focus();
		  document.f1.cbsemaks[14].checked = true;
		  document.f1.cbsemaks[15].checked = false;
	}
	else if(document.f1.cbsemaks[13].checked == false){
	      document.f1.cbsemaks[14].focus();
		  document.f1.cbsemaks[14].checked = false;
		  document.f1.cbsemaks[15].checked = false;
	}
	
}

function checkit15(){
	if (document.f1.cbsemaks[14].checked == true){
		  document.f1.cbsemaks[13].checked = true;
		
	}
	else if (document.f1.cbsemaks[14].checked == false && document.f1.cbsemaks[15].checked == false){
	 document.f1.cbsemaks[13].checked = false;
		 
	}
}

function checkit16(){
	if (document.f1.cbsemaks[15].checked == true){
		  document.f1.cbsemaks[13].checked = true;		
	}
	else if (document.f1.cbsemaks[15].checked == false && document.f1.cbsemaks[14].checked == false){
	 document.f1.cbsemaks[13].checked = false;
		 
	}
}

function checkit17(){


	if (document.f1.cbsemaks[16].checked == true){
	  document.f1.txtLainLainTujuan.focus();
	  document.f1.txtLainLainTujuan.value = "Tujuan :";
	//  document.f1.txdTarikhByrn.value = "";		  
		
	}
	
	if (document.f1.cbsemaks[16].checked == false){
	  document.f1.txtLainLainTujuan.focus();
	  document.f1.txtLainLainTujuan.value = "";
	//  document.f1.txdTarikhByrn.value = "";		  
		
	}
	
}

function checkit18(){
	if (document.f1.txtLainLainTujuan.value != ""){
		 document.f1.cbsemaks[16].checked = true;
	}
	if (document.f1.txtLainLainTujuan.value == "" ){
		 document.f1.cbsemaks[16].checked = false;
	}
	
}

function checkit19(){
	
	if (document.f1.txtLainLainTujuan.value == "" ){
		 document.f1.cbsemaks[16].checked = false;
	}
	
}




function DoTheCheck() {

     var str1  = document.getElementById("txdTarikhByrn").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);

    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
	var dm = document.f1.txdTarikhByrn;
	
	/* if (document.f1.cbsemaks[0].checked == false) {
		alert("Sila masukkan maklumat Borang P yg telah lengkap diisi");
		
		document.f1.cbsemaks[0].focus();
		return;	
	}
	else if (document.f1.cbsemaks[2].checked == false && document.f1.cbsemaks[3].checked == false && document.f1.cbsemaks[4].checked == false && document.f1.cbsemaks[5].checked == false && document.f1.cbsemaks[6].checked == false) {
		alert("Sila masukkan Dokumen Sokongan");
		document.f1.cbsemaks[2].focus();	
	} */
	/*
	if (document.f1.cbsemaks[8].checked == false ){
		alert("Sila masukkan Bayaran Pemohonan");
		  document.f1.perintahminta.focus();
	}
	else if (document.f1.cbsemaks[8].checked == true && document.f1.txtNomborResit.value == "") {
		alert("Sila masukkan No. Resit Bayaran Pemohonan");
		  document.f1.txtNomborResit.focus();
		  return;
	}
	else if (document.f1.cbsemaks[8].checked == true && document.f1.txdTarikhByrn.value == "") {
		alert("Sila masukkan Tarikh Bayaran Pemohonan");
		document.f1.txdTarikhByrn.focus();
		return;
	}
	*/
	
	/* else if (document.f1.lepassatusept.value == "yes" && document.f1.cbsemaks[8].checked == false ){
		alert("Sila masukkan Bayaran Pemohonan");
		 document.f1.txtNomborResit.focus();
	}
	else if (document.f1.lepassatusept.value == "yes" && document.f1.cbsemaks[8].checked == true && document.f1.txtNomborResit.value == "") {
		alert("Sila masukkan No. Resit Bayaran Pemohonan");
		 document.f1.txtNomborResit.focus();
	}
	else if (document.f1.lepassatusept.value == "yes" && document.f1.cbsemaks[8].checked == true && document.f1.txdTarikhByrn.value == "") {
		alert("Sila masukkan Tarikh Bayaran Pemohonan");
		document.f1.txdTarikhByrn.focus();
	}
	else if (document.f1.lepassatusept.value == "yes" && isDate(dm.value)==false){
		dm.focus()
		return false
	}
	else if (date1 > currentTime){
		alert("Sila pastikan tarikh bayaran tidak melebihi dari tarikh hari ini.");
		document.f1.txdTarikhByrn.focus();
		return;
	}
	else */ 
	
	/* if (document.f1.cbsemaks[9].checked == false && document.f1.cbsemaks[11].checked == false && document.f1.cbsemaks[12].checked == false && document.f1.cbsemaks[14].checked == false && document.f1.cbsemaks[15].checked == false  && document.f1.cbsemaks[16].checked == false ) {
		alert("Sila masukkan Permohonan Khusus Yang Dipohon");
		document.f1.cbsemaks[9].focus();
		return;
	} */	
	
	if (document.f1.perintahminta.value == false ){
		alert("Sila masukkan permohonan khusus yang dipohon");
		  document.f1.perintahminta.focus();
	}

	else{
	
		Simpan();
	}

}
function Simpan(){
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.f1.method="POST";
		document.f1.command.value="Simpan";
		document.f1.action="";
		
		if(document.f1.cbsemaks[9].checked == true)
		{
		document.f1.ht.value="Y";
		}else
		{
		document.f1.ht.value="T";
		}
		
		if(document.f1.cbsemaks[11].checked == true)
		{
		document.f1.bpa.value="Y";
		}else
		{
		document.f1.bpa.value="T";
		}
		
		if(document.f1.cbsemaks[12].checked == true)
		{
		document.f1.lpa.value="Y";
		}else
		{
		document.f1.lpa.value="T";
		}
		
		
		if(document.f1.cbsemaks[15].checked == true)
		{
		document.f1.lp.value="Y";
		}else
		{
		document.f1.lp.value="T";
		}
		
		if(document.f1.cbsemaks[14].checked == true)
		{
		document.f1.bkp.value="Y";
		}else
		{
		document.f1.bkp.value="T";
		}
	//::	
		if(document.f1.cbsemaks[16].checked == true)
		{
		document.f1.lt.value="Y";
		}else
		{
		document.f1.lt.value="T";
		}
		
		
	document.f1.submit();
	}	
}
function Seterusnya() {
		document.f1.method="POST";
		document.f1.command.value="Seterusnya";
		document.f1.idFlag.value="1";
		document.f1.flagno.value="2";
		document.f1.action = "";
		document.f1.submit();
}

function Seterusnya_X() {
		document.f1.method="POST";
		document.f1.command.value="Seterusnya";
		document.f1.idFlag.value="1";
		document.f1.flagno.value="2";
		document.f1.action = "";
		document.f1.submit();
}

function Kemaskini(){
	document.f1.method="POST";
	document.f1.command.value="Kemaskini";
	//document.f1.action="";
	document.f1.action = "";
	document.f1.submit();
}
function Kemaskini1(){
	document.f1.method="POST";
	document.f1.command.value="Kemaskini";
	document.f1.action="";
	//document.f1.action = "";
	document.f1.submit();
}

function cetak(){
	window.print();
}
function Kembali(){
	document.f1.method="POST";
	document.f1.command.value="xxx";
	document.f1.action = "";
	document.f1.submit();
}
function kembaliSenaraiFail(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.f1.submit();
}
function kembaliSenaraiPermohonan(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.f1.method="POST";
	document.f1.submit();
}
function papar(){
	document.f1.command.value = "papar";
	document.f1.method="POST";
		document.f1.action = "";
	
	document.f1.submit();
}
function tab()
{
    document.f1.command.value = "tab";
	document.f1.method="POST";	
	document.f1.action = "";	
	document.f1.submit();

}




function tabS()
{
    document.f1.command.value = "tab";
	document.f1.method="POST";	
		document.f1.action = "";
	
	document.f1.submit();

}

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
		alert("Sila masukkan bulan yg sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yg sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yg sah")
		return false
	}
return true
}

function isIc(dtStr){
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
		alert("Format no kp baru seperti ini, cth : 800808-08-0008 ")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah pada no kp baru")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah pada no kp baru")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan no kp yang sah")
		return false
	}
return true
}


 function trans_date(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhByrn.value = trans;

}
else
{
return;
}

}

function trans_date(t_d)
{


if(t_d.length == 8)
{
var a = t_d.charAt(0);
var b = t_d.charAt(1);
var c = t_d.charAt(2);
var d = t_d.charAt(3);
var e = t_d.charAt(4);
var f = t_d.charAt(5);
var g = t_d.charAt(6);
var h = t_d.charAt(7);

var trans = a+""+b+"/"+c+""+d+"/"+e+""+f+""+g+""+h;
//alert("value :"+t_d+"lenght :"+t_d.length+"trans :"+trans)


document.f1.txdTarikhByrn.value = trans;

}
else
{
return;
}

}


function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
}


function ForView(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.f1.submit();
}






</script>