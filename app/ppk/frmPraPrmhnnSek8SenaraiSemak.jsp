<!--<% //@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %> -->
<meta http-equiv="Content-Script-Type" content="text/javascript">
<style type="text/css">
<!--
.style10 {color: #0000FF}
-->
</style>
<head>
<style type="text/css">
<!--
.style2 {color: #FF0000}
.style4 {
	font-size: 14px;
	color: #0000FF;
}
.style5 {	font-size: 9px;
	font-style: italic;
}
.style11 {
	font-size: 9px;
	color: #0000FF;
}
-->
</style>
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

#if ($SimpanStatus == "2")
#set ($chkmode = "disabled")
#else 
#set ($chkmode = "")
#end




#if ($SimpanStatus == "1" ||$SimpanStatus == "2" )
	#foreach($List in $ListSemakan)
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
		#end
		#if ($List.idsemakansenarai == "18")
			#set ($checked18 = "checked")
		#end
		#if ($List.idsemakansenarai == "19")
			#set ($checked19 = "checked")
		#end
		#if ($List.idsemakansenarai == "20")
			#set ($checked20 = "checked")
		#end
		#if ($List.idsemakansenarai == "21")
			#set ($checked21 = "checked")
		#end
		#if ($List.idsemakansenarai == "22")
			#set ($checked22 = "checked")
		#end
		#if ($List.idsemakansenarai == "23")
			#set ($checked23 = "checked")
			#set ($txtchecked23 = $List.Catatan)
		#end
		#if ($List.idsemakansenarai == "24")
			#set ($checked24 = "checked")
		#end	
        
      
	#end
#end


</head>
<body>

#foreach($List in $ListSemakan)  
    #set($no_online=$List.no_online)
   
#end


<table width="100%">
<tr>
<td>

<div align="right"><span ><img src="../img/1current.png" alt="" border="0" title="Senarai Semak"/><img src="../img/arrowgaris.png" alt="" border="0"/>#if($daftar== "yes" )<a href="javascript:Seterusnya()" class="style10" ><img src="../img/2enable.png" alt="" border="0" title="Pendaftaran Permohonan" /></a>#else<img src="../img/2disable.png" alt="" border="0" title="Pendaftaran Permohonan" />#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($nofail== "yes")<a href="javascript:tab()" class="style10"   ><img src="../img/3enable2.png" alt="" border="0" title="Maklumat Permohonan" /></a>#else<img src="../img/3disable.png" alt="" border="0" title="Maklumat Permohonan" />#end</span></div>



</td>
</tr>
</table>

#parse("app/ppk/bil_fail.jsp")
<fieldset>
<table width="100%" border="0">
  <tr>
    <td><table width="100%" border="0">
      <tr>
        <td width="40%"><div align="right">NO RUJUKAN ONLINE</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%" class="style4">$no_online</td>
      </tr>
    </table></td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend>Senarai Semakan</legend>
<br>


<form name="f1" method="post">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

#foreach($ls in $ListSemakanSimati)

#if($ls.idsemakansenarai=="5")
#set($buktimati="1")
#set($sijilmati="$ls.Catatan")
#set($id_Simati="$ls.id_Simati")
#end


#if($ls.idsemakansenarai=="6")
#set($buktimati="2")
#set($id_Simati="$ls.id_Simati")
#set($sijilmati="")
#end



#if($ls.idsemakansenarai=="7")
#set($buktimati="3")
#set($id_Simati="$ls.id_Simati")
#set($sijilmati="")
#end

#if($ls.idsemakansenarai=="8")
#set($id_Simati="$ls.id_Simati")
#set($buktimati="4")
#set($sijilmati="$ls.Catatan")
#end


#end
<input name="buktimati" type="hidden" value="$buktimati" />
<input name="sijilmati" type="hidden" value="$sijilmati" />
<input name="id_Simati" type="hidden" value="$id_Simati" />


<input name="idSimati" type="hidden" value="$id_Simati" />
<input name="idpermohonan" type="hidden" value="$IdPermohonan" />
<input name="idPemohon" type="hidden" value="$idPemohon" />


<table width="100%" border="0"><tr><td width="50%"  valign="top" ><table width="100%" border="0">
  <tr>
    <td width="50%"  valign="top" >
    
    <table width="100%" border="0">
      <tr>
        <td >
    <table width="100%" border="0">
       
          <tr>
            <td width="10%" >&nbsp;</td>
            <td width="1%"  valign="top" >#if($chkmode != "disabled") <span class="style2">*</span> #end</td>
            <td width="3%" ><input name="cbsemaks" type="checkbox" id="cbsemaks" $chkmode  value="1" $checked1 onClick="ReadOnlyCheckBox(this);checkit1()"/>            </td>
            <td width="86%"> #if($chkmode != "disabled") Borang A lengkap diisi #else
              
              Borang A lengkap diisi
              
              #end </td>
          </tr>
        </table>
           </td>
      </tr>
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="20%" >&nbsp;</td>
            <td width="3%" valign="top" ><input name="cbsemaks" type="checkbox" id="cbsemaks" $chkmode value="2" $checked2 onClick="checkit2()"/>            </td>
            <td width="77%" > Fotostat kad pengenalan pemohon disertakan</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td  ><input name="cbsemaks" type="checkbox" id="cbsemaks" $chkmode value="3" $checked3 onClick="checkit3()"/>            </td>
            <td> Fotostat kad pengenalan waris disertakan</td>
          </tr>
        </table>
        </td>
      </tr>
      <tr>
        <td>
      
        
        <table width="100%" border="0">
        
         #if($listsijilmati.size() > 0 )
         
         	#foreach($listm in $listsijilmati)
    	  		#set ($no_sijil = $listm.no_sijil)
    	  		#set ($jenis_sijil = $listm.jenis_sijil)
    	  	#end
    	  	
    	  	#if($jenis_sijil == "1")
          		#set($checked5 = "checked")
          		#set($txtchecked5 = $no_sijil)
           	#end
    	  	
         #end 
         
          #if($SimpanStatus == "0" && $EventStatus=="0" && $backStatus == "0" && $listsijilmati.size() > 0 )
         
         #set($checked4 = "checked") 
          
          
          #foreach($listm in $listsijilmati)
    	  #set ($no_sijil = $listm.no_sijil)
    	  #set ($jenis_sijil = $listm.jenis_sijil)
          
          #end
          
          #if($jenis_sijil == "1")
          #set($checked5 = "checked")
          #set($txtchecked5 = $no_sijil)
          
          #end
          
          #if($jenis_sijil == "2")
          #set($checked6 = "checked")
          
          #end
          
          #if($jenis_sijil == "3")
          #set($checked7 = "checked")
          
          #end
          
          #if($jenis_sijil == "4")
          #set($checked8 = "checked")
          #set($txtchecked8 = $no_sijil)
          #end
          
          #end
          
          
          
        
        
        
          <tr>
            <td width="10%" >&nbsp;</td>
            <td width="1%" valign="top" > #if($chkmode != "disabled") <span class="style2">*</span> #end</td>
            <td width="3%" ><input name="cbsemaks" type="checkbox" id="cbsemaks" $chkmode value="4" $checked4 onClick="ReadOnlyCheckBox(this);checkit4()"/>            </td>
            <td width="86%"> #if($chkmode != "disabled") Bukti kematian #else
              Bukti kematian
              #end </td>
          </tr>
        </table>
        
        </td>
      </tr>
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="20%" >&nbsp;</td>
            <td width = "3%" valign="top" ><div align="center">
              <input type="radio" name="cbsemakradio" $chkmode id="radio"  value="5" $checked5 onClick="checkit5()"/>
            </div></td>
            <td width="77%"> Sijil Perakuan Kematian <br/>
              (Surat Mati)
              <label id="divCheckbox"  style="visibility: hidden;">
                    <input type="checkbox" name="cbsemaks" $checked5 value="5" id="cbsemaks" />
                  </label></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td width="7%" ><label></label></td>
            <td width="73%" >No. Sijil 
              <label>
              
              #if($chkmode == "disabled")
              #set($chkmodeR = "readonly" )
              #else              
              #set($chkmodeR = "" )
              #end
                    <input type="text" name="txtNomborSijil" id="txtNomborSijil" maxlength="25" $chkmodeR  class="$chkmode" value="$!txtchecked5" onKeyUp="checkitA()" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  />
                  </label></td>
          </tr>
          <tr style="border-bottom: 1px solid #000;">
            <td>&nbsp;</td>
            <td ></td>
            <td> </td>
          </tr>
          <!-- 
          <tr>
            <td>&nbsp;</td>
            <td ><div align="center">
              <input type="radio" name="cbsemakradio" id="radio2" $chkmode value="6" $checked6 onClick="checkit6()"/>
            </div></td>
            <td> Surat Sumpah Kematian
              <label id="divCheckbox"  style="visibility: hidden;">
                    <input type="checkbox" name="cbsemaks" $checked6 value="6" id="cbsemaks" />
                    </label>            </td>
          </tr>
           -->
          <tr>
            <td>&nbsp;</td>
            <td valign="top"><div align="center">
              <input type="radio" name="cbsemakradio" $chkmode id="radio3" value="7" $checked7 onClick="checkit7()"/>
            </div></td>
            <td> Perintah Mahkamah (Kematian)
              <label id="divCheckbox"  style="visibility: hidden;">
                    <input type="checkbox" name="cbsemaks" $checked7 value="7" id="cbsemaks" />
                  </label></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td width="7%" ><label></label></td>
            <td width="73%" >Tarikh Perintah&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <label>
                <input type="text" name="txtPerintahMahkamah" id="txtPerintahMahkamah" width="10" maxlength="10" $chkmodeR  class="$chkmode" value="$!txtchecked6d" onKeyUp="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"/>
                <a href="javascript:displayDatePicker('txtPerintahMahkamah',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
                <span style="font-size:9px;color:blue;font-style:italic">dd/mm/yyyy</span>
              </label>
            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td width="7%" ><label></label></td>
            <td width="73%" >No. Kes Mahkamah
              <label>
              
              #if($chkmode == "disabled")
              #set($chkmodeR = "readonly" )
              #else              
              #set($chkmodeR = "" )
              #end
                    <input type="text" name="txtNoKesMahkamah" id="txtNoKesMahkamah" maxlength="25" $chkmodeR  class="$chkmode" value="$!txtchecked6" onKeyUp="" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  />
                  </label></td>
          </tr>
          <!-- 
          <tr>
            <td>&nbsp;</td>
            <td ><div align="center">
              <input type="radio" name="cbsemakradio" id="radio4" $chkmode value="8" $checked8 onClick="checkit8()"/>
            </div></td>
            <td> Permit Menguburkan
              <label id="divCheckbox"  style="visibility: hidden;">
                    <input type="checkbox" name="cbsemaks" $checked8 value="8" id="cbsemaks" />
                    </label>            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><div align="center"></div></td>
            <td>No. Permit
              <label>
              #if($chkmode == "disabled")
              #set($chkmodeR = "readonly" )
              #else              
              #set($chkmodeR = "" )
              #end
                    <input type="text" name="txtNomborPermit" id="txtNomborPermit" maxlength="10" $chkmodeR class="$chkmode" value="$txtchecked8" onKeyUp="checkitB()" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  />
                  </label></td>
          </tr>
           -->
        </table>
       
      #foreach($List1 in $list2)
  #set($lepassatusept = $List1.lepassatusept )
 #end

          <input type="hidden" name="lepassatusept" id="lepassatusept" value="$lepassatusept" />
      				 #if($lepassatusept == "no" )
                    <label id="divCheckbox"  style="visibility: hidden;" >
                    <input name="cbsemaks" type="checkbox" id="cbsemaks5" checked value="9"  onClick="checkit9()"/>  
                    <input type="hidden" name="txtNomborResit" id="txtNomborResit" size="15"  maxlength="20" value="" />  
                    <input type="hidden" name="txdTarikhByrn" id="txdTarikhByrn" size="15"  maxlength="20" value="" />     
                    </label> 
     				  #end
        
        </td>
       
      </tr>
     
      
      #if($lepassatusept == "yes" )
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="10%" >&nbsp;</td>
            <td width="1%" valign="top" >#if($chkmode != "disabled") <span class="style2">*</span> #end</td>
            <td width="3%" ><input name="cbsemaks" type="checkbox" id="cbsemaks5" $chkmode value="9" $checked9 onClick="checkit9()"/>            </td>
            <td width="86%"> #if($chkmode != "disabled") Bayaran Permohonan #else
              Bayaran Permohonan
              #end </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="20%" >&nbsp;</td>
            <td width="80%" >No. Resit
              <label>
              #if($chkmode == "disabled")
              #set($chkmodeR = "readonly" )
              #else              
              #set($chkmodeR = "" )
              #end
                    <input type="text" name="txtNomborResit" id="txtNomborResit" size="15"  maxlength="20" $chkmodeR class="$chkmode" value="$txtchecked9" onKeyUp="checkitC()" style="text-transform:uppercase;"/>
                    <input type="hidden" name="txtNomborResit1" id="txtNomborResit1" size="15"  maxlength="20" $chkmodeR class="$chkmode" value="$txtchecked9" onKeyUp="checkitC()" style="text-transform:uppercase;"/>
                  </label></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Tarikh Bayaran
              <label>
              
             
                    <input type="text" name="txdTarikhByrn" id="txdTarikhByrn" size="11" maxlength="10" value="$txtchecked9d" $chkmodeR class="$chkmode"   onFocus="checkitX()"  onBlur="trans_date(this.value)"  />
                    
                    <input type="hidden" name="txdTarikhByrn1" id="txdTarikhByrn1" size="11" maxlength="10" value="$txtchecked9d" />
                #if ($SimpanStatus != "2") <a href="javascript:displayDatePicker('txdTarikhByrn',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> <span class="style3 style6 style11"><em>dd/mm/yyyy</em></span>#end </label></td>
          </tr>
          #if($chkmode != "disabled")
          #if ($SimpanStatus != "2")
          #end

          #end
        </table></td>
      </tr>
      
     
     #end
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="10%" >&nbsp;</td>
             <td width="1%" >&nbsp;</td>
            <td width="89%" >
         
    
       
          #if($SimpanStatus == "0" && $EventStatus=="0" && $backStatus == "0" && $llxx.size() > 0 )
          #set($checked10 = "checked")
          
          
          #end
          
            <input name="cbsemaks" $chkmode type="checkbox" id="cbsemaks6" $checked10 value="10" />
              Melantik Peguam</td>
          </tr>
        </table>
        
        </td>
      </tr>
    </table>
    
    </td>
    <td width="50%" ><table width="100%" border="0">
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="10%" >&nbsp;</td>
            <td width="1%" >&nbsp;</td>
            <td width="3%" ><input name="cbsemaks" type="checkbox" id="cbsemaks7" $chkmode value="11" $checked11 onClick="ReadOnlyCheckBox(this);checkit10()" />            </td>
            <td width="86%" > Dokumen hakmilik semua harta yang dituntut</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="20%" >&nbsp;</td>
            <td width="3%" ><div align="center">
              <input name="cbsemaks" type="checkbox" id="cbsemaks8" $chkmode value="12" $checked12 onClick="checkit11();checkit_harta()"/>
            </div></td>
            <td width="77%"> Carian Rasmi</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td ><div align="center">
              <input name="cbsemaks" type="checkbox" id="cbsemaks9" $chkmode value="13" $checked13 onClick="checkit12();checkit_harta()"/>
            </div></td>
            <td> Salinan Hakmilik / Cabutan dari hakmilik</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><div align="center">
              <input name="cbsemaks" type="checkbox" id="cbsemaks1" $chkmode value="14" $checked14 onClick="ReadOnlyCheckBox(this);checkit13()"/>
            </div></td>
            <td> Salinan Dokumen lain bagi pengesahan harta</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td width="8%"><div align="center"></div></td>
            <td width="72%"><input name="cbsemaks" type="checkbox" id="cbsemaks10" $chkmode value="15" $checked15 onClick="checkit14();checkit_harta()"/>
              Perjanjian jualbeli harta</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><div align="center"></div></td>
            <td><input name="cbsemaks" type="checkbox" id="cbsemaks11" $chkmode value="16" $checked16 onClick="checkit15();checkit_harta()"/>
              Perjanjian Pemilikan</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="10%" >&nbsp;</td>
             <td width="1%" >&nbsp;</td>
            <td width="3%" ><input name="cbsemaks" type="checkbox" id="cbsemaks12" $chkmode value="17" $checked17 onClick="ReadOnlyCheckBox(this);checkit16()"/>            </td>
            <td width="86%"> Salinan dokumen sokongan bagi Harta Alih</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="22%" >&nbsp;</td>
            <td width="3%" ><div align="center">
              <input name="cbsemaks" type="checkbox" id="cbsemaks13" $chkmode value="18" $checked18 onClick="ReadOnlyCheckBox(this);checkit17()"/>
            </div></td>
            <td width="75%"> Wang Simpanan</td>
          </tr>
          <tr>
            <td >&nbsp;</td>
            <td ><div align="center"></div></td>
            <td ><input name="cbsemaks" type="checkbox" id="cbsemaks17" $chkmode  value="19" $checked19 onClick="checkit18()"/>
              Akaun / Penyata Bank</td>
          </tr>
          <tr>
            <td  >&nbsp;</td>
            <td><div align="center"></div></td>
            <td><input name="cbsemaks" type="checkbox" id="cbsemaks18" $chkmode value="20" $checked20 onClick="checkit19()"/>
              Penyata Tabung Haji</td>
          </tr>
          <tr>
            <td >&nbsp;</td>
            <td ><div align="center">
              <input name="cbsemaks" type="checkbox" id="cbsemaks14" $chkmode value="21" $checked21 onClick="checkit20()"/>
            </div></td>
            <td> Saham</td>
          </tr>
          <tr>
            <td >&nbsp;</td>
            <td ><div align="center">
              <input name="cbsemaks" type="checkbox" id="cbsemaks15" $chkmode value="22" $checked22 onClick="checkit21()"/>
            </div></td>
            <td> Perakuan Pendaftaran Kenderaan / Geran</td>
          </tr>
          <tr>
            <td >&nbsp;</td>
            <td ><div align="center">
              <input name="cbsemaks" type="checkbox" id="cbsemaks16" $chkmode value="23" $checked23 onClick="checkit22()"/>
            </div></td>
            <td> Lain - lain</td>
          </tr>
          <tr>
            <td  >&nbsp;</td>
            <td >&nbsp;</td>
            <td >
              #if($chkmode == "disabled")
              #set($chkmodeR = "readonly" )
              #else              
              #set($chkmodeR = "" )
              #end
            
            <textarea name="txtLainLainTujuan" id="txtLainLainTujuan" $chkmodeR class="$chkmode" cols="35" rows="5" style="text-transform:uppercase;" onKeyUp="checkitD()" onBlur="this.value=this.value.toUpperCase()"  >$txtchecked23</textarea></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="10%" >&nbsp;</td>
            <td width="1%"  valign="top"></td>
            <td width="3%" ><input name="cbsemaks" type="checkbox" id="cbsemaks19" value="24"  $chkmode $checked24 onClick="ReadOnlyCheckBox(this);checkit_harta_cancel()" />            </td>
            <td width="86%"> #if($chkmode != "disabled") Memiliki Harta Tak Alih #else
              Memiliki Harta Tak Alih
              #end </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table></td>
    </tr>
</table>

#if($chkmode != "disabled")
<table width="100%">
  <tr>
    <td><span class="style5"><span class="style2">Perhatian</span> : Sila pastikan label berwarna <span class="style2">merah</span> diisi.</span></td>
  </tr>
</table>
#end
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
	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="Kemaskini()"/>
    <!--   
    <input name="cmdSeterusnya" type="button"  id="cmdSeterusnya" value="Seterusnya" onClick="Seterusnya()"/> 
    -->
	#if ($backStatus == "1")
	
	#end
#else
	<input type="button" name="cmdTolakPermohonan" value="Kembalikan Permohonan" onClick="javascript:tolakPermohonanOnline('$!IdPermohonan','yes')">
	<input name="cmdSimpan" type="button" id="cmdSimpan"  value="Simpan" onClick="DoTheCheck()"/>  
	<input name="cmdBatal" type="reset"  id="cmdBatal" value="Batal"/>
	
#end</label>
<!--
 <input name="cmdKembali" type="button"  id="cmdKembali" value="Kembali" onClick="Kembali()"/>
 -->
</form>
<p align="right">CL - 01 - 63</p>
  </fieldset>
 
 
 <table width="100%">
<tr>
<td>
<div align="right"><span ><img src="../img/1current.png" alt="" border="0" title="Senarai Semak"/><img src="../img/arrowgaris.png" alt="" border="0"/>#if ($daftar== "yes" )<a href="javascript:Seterusnya()" class="style10" ><img src="../img/2enable.png" alt="" border="0" title="Pendaftaran Permohonan" /></a>#else<img src="../img/2disable.png" alt="" border="0" title="Pendaftaran Permohonan" />#end<img src="../img/arrowgaris.png" alt="" border="0"/>#if($nofail== "yes")<a href="javascript:tab()" class="style10"   ><img src="../img/3enable2.png" alt="" border="0" title="Maklumat Permohonan" /></a>#else<img src="../img/3disable.png" alt="" border="0" title="Maklumat Permohonan" />#end</span></div></td>
</tr>
</table>
 
 
</body>
<script>
	function tolakPermohonanOnline(id_permohonan,formnew) {	
		var w = "400";
		var h = "200";
		var left = (screen.width/2)-(w/2);
		var top = (screen.height/2)-(h/2);
		var url = "../x/${securityToken}/FrmPopupTolakPermohonan?id_permohonan="+id_permohonan+"&formnew="+formnew+"&modul=ppk&jenisTolak=internal";
		
		var hWnd = window.open(url, "Permohonan Online Dikembalikan", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		
	}
	
function checkit1(){
	if (document.f1.cbsemaks[0].checked == true){
	document.f1.cbsemaks[1].checked = true;
		 
	}
	if (document.f1.cbsemaks[0].checked == false){
		  document.f1.cbsemaks[1].checked = false;
		  document.f1.cbsemaks[2].checked = false;
	}
	
}
function checkit2(){
	
	if (document.f1.cbsemaks[1].checked == true){
		  document.f1.cbsemaks[0].checked = true;		 
	}
	else
	{
	if (document.f1.cbsemaks[1].checked == false && document.f1.cbsemaks[2].checked == false ){
		  document.f1.cbsemaks[0].checked = false;		 
	}
	else if (document.f1.cbsemaks[1].checked == false && document.f1.cbsemaks[2].checked == true ){
		  document.f1.cbsemaks[0].checked = true;		 
	}
	
	}
	
}
function checkit3(){
	if (document.f1.cbsemaks[2].checked == true){
		  document.f1.cbsemaks[0].checked = true;		 
	}
	else
	{
	if (document.f1.cbsemaks[2].checked == false && document.f1.cbsemaks[1].checked == false ){
		  document.f1.cbsemaks[0].checked = false;		 
	}
	else if (document.f1.cbsemaks[2].checked == false && document.f1.cbsemaks[1].checked == true ){
		  document.f1.cbsemaks[0].checked = true;		 
	}
	
	}
}


function checkit4(){
	if (document.f1.cbsemaks[3].checked == true)
	{	
	document.f1.cbsemakradio[0].checked = true;
	document.f1.cbsemaks[4].checked = true;
	document.f1.txtNomborSijil.focus();		
	}
	if (document.f1.cbsemaks[3].checked == false)
	{
	document.f1.txtNomborPermit.value="";
	document.f1.txtNomborSijil.value="";
	
	document.f1.cbsemakradio[0].checked = false;
	document.f1.cbsemaks[4].checked = false;
	
	document.f1.cbsemakradio[1].checked = false;
	document.f1.cbsemaks[5].checked = false;
	
	document.f1.cbsemakradio[2].checked = false;
	document.f1.cbsemaks[6].checked = false;
	
	document.f1.cbsemakradio[3].checked = false;
	document.f1.cbsemaks[7].checked = false;
	}	
}

function checkit5(){

if (document.f1.cbsemakradio[0].checked == true)
	{	
	document.f1.cbsemakradio[0].checked = true; //Sijil Perakuan Kematian
	document.f1.cbsemaks[3].checked = true;
	document.f1.cbsemaks[4].checked = true;
	
	document.f1.cbsemaks[5].checked = false;
	//document.f1.cbsemaks[6].checked = false; Peguam
    //document.f1.cbsemaks[7].checked = true; //Dokumen hakmilik semua harta yang dituntut
    //document.f1.cbsemaks[8].checked = false; //Carian Rasmi
	document.f1.txtNomborSijil.focus();	

	document.f1.txtNomborPermit.value = "";	
	}
	
else
	{	
	if(document.f1.cbsemakradio[1].checked == false && document.f1.cbsemakradio[2].checked == false && document.f1.cbsemakradio[3].checked == false)	
	{
	document.f1.cbsemaks[3].checked = false;
	document.f1.cbsemaks[4].checked = false;
	
	document.f1.cbsemaks[5].checked = false;
	//document.f1.cbsemaks[6].checked = false;
    //document.f1.cbsemaks[7].checked = false;
	document.f1.txtNomborSijil.value = "";		

	document.f1.txtNomborPermit.value = "";
	}	
	else
	{
	document.f1.cbsemaks[3].checked = true;
	document.f1.cbsemaks[4].checked = true;
	
	document.f1.cbsemaks[5].checked = true;
	//document.f1.cbsemaks[6].checked = true;
    //document.f1.cbsemaks[7].checked = true;

	document.f1.txtNomborPermit.value = "";
	}
	
}
}

function checkit6(){

if (document.f1.cbsemakradio[1].checked == true)
	{	
	document.f1.cbsemaks[3].checked = true;
	document.f1.cbsemaks[5].checked = true;
	document.f1.txtNomborSijil.value = "";
	document.f1.txtNomborPermit.value = "";
	
	document.f1.cbsemaks[4].checked = false;
	//document.f1.cbsemaks[6].checked = false;
    //document.f1.cbsemaks[7].checked = false;

	}
	
else
	{	
	if(document.f1.cbsemakradio[0].checked == false && document.f1.cbsemakradio[2].checked == false && document.f1.cbsemakradio[3].checked == false)	
	{
	document.f1.cbsemaks[3].checked = false;
	document.f1.txtNomborSijil.value = "";
	document.f1.txtNomborPermit.value = "";
	document.f1.cbsemaks[5].checked = false;
	document.f1.cbsemaks[4].checked = false;
	//document.f1.cbsemaks[6].checked = false;
   // document.f1.cbsemaks[7].checked = false;
			
	}	
	else
	{
	document.f1.cbsemaks[3].checked = true;
	document.f1.cbsemaks[5].checked = true;
	document.f1.cbsemaks[4].checked = false;
	//document.f1.cbsemaks[6].checked = false;
    //document.f1.cbsemaks[7].checked = false;
	document.f1.txtNomborSijil.value = "";
	document.f1.txtNomborPermit.value = "";
	}
	
}
}

function checkit7(){

if (document.f1.cbsemakradio[2].checked == true)
	{	
	document.f1.cbsemaks[3].checked = true;
	document.f1.cbsemaks[6].checked = true;
	document.f1.txtNomborSijil.value = "";
	document.f1.txtNomborPermit.value = "";
	
	document.f1.cbsemaks[4].checked = false;
	document.f1.cbsemaks[5].checked = false;
    //document.f1.cbsemaks[7].checked = false;
	}
	
else
	{	
	if(document.f1.cbsemakradio[0].checked == false && document.f1.cbsemakradio[1].checked == false && document.f1.cbsemakradio[3].checked == false)	
	{
	document.f1.cbsemaks[3].checked = false;
	document.f1.txtNomborSijil.value = "";
	document.f1.txtNomborPermit.value = "";
	document.f1.cbsemaks[6].checked = false;
	
	document.f1.cbsemaks[4].checked = false;
	document.f1.cbsemaks[5].checked = false;
    //document.f1.cbsemaks[7].checked = false;
			
	}	
	else
	{
	document.f1.cbsemaks[3].checked = true;
	document.f1.cbsemaks[6].checked = true;
	document.f1.txtNomborSijil.value = "";
	document.f1.txtNomborPermit.value = "";
	
	document.f1.cbsemaks[4].checked = false;
	document.f1.cbsemaks[5].checked = false;
    //document.f1.cbsemaks[7].checked = false;
	}
	
}
}

function checkit8(){

if (document.f1.cbsemakradio[3].checked == true)
	{	
	document.f1.cbsemaks[3].checked = true;
	document.f1.txtNomborPermit.focus();
	document.f1.cbsemaks[7].checked = true;
	document.f1.txtNomborSijil.value = "";
	
	document.f1.cbsemaks[4].checked = false;
	document.f1.cbsemaks[5].checked = false;
    document.f1.cbsemaks[6].checked = false;
	}
	
else
	{	
	if(document.f1.cbsemakradio[0].checked == false && document.f1.cbsemakradio[1].checked == false && document.f1.cbsemakradio[2].checked == false)	
	{
	document.f1.cbsemaks[3].checked = false;
	document.f1.txtNomborSijil.value = "";
	document.f1.txtNomborPermit.value = "";
	document.f1.cbsemaks[7].checked = false;	
	document.f1.cbsemaks[4].checked = false;
	document.f1.cbsemaks[5].checked = false;
    document.f1.cbsemaks[6].checked = false;
			
	}	
	else
	{
	document.f1.cbsemaks[3].checked = true;
	document.f1.cbsemaks[7].checked = true;
	
	document.f1.cbsemaks[4].checked = false;
	document.f1.cbsemaks[5].checked = false;
    document.f1.cbsemaks[6].checked = false;
	}
	
}
}




function checkitA()
{
if(document.f1.txtNomborSijil.value != "")
{
    document.f1.cbsemakradio[0].checked = true;
    document.f1.cbsemaks[3].checked = true;
	document.f1.cbsemaks[4].checked = true;
    document.f1.txtNomborPermit.value = "";

    document.f1.cbsemaks[5].checked = false;
	document.f1.cbsemaks[6].checked = false;
    document.f1.cbsemaks[7].checked = false;
}

if(document.f1.txtNomborSijil.value == "")
{
    document.f1.cbsemakradio[0].checked = false;
    document.f1.cbsemaks[3].checked = false;
	document.f1.cbsemaks[4].checked = false;
    document.f1.txtNomborPermit.value = "";
	document.f1.txtNomborSijil.value = ""

    document.f1.cbsemaks[5].checked = false;
	document.f1.cbsemaks[6].checked = false;
    document.f1.cbsemaks[7].checked = false;
}

}

function checkitB()
{
if(document.f1.txtNomborPermit.value != "")
{
    document.f1.cbsemakradio[3].checked = true;
    document.f1.cbsemaks[3].checked = true;
	document.f1.cbsemaks[7].checked = true;
    document.f1.txtNomborSijil.value = "";

    document.f1.cbsemaks[5].checked = false;
	document.f1.cbsemaks[6].checked = false;
    document.f1.cbsemaks[4].checked = false;
}

if(document.f1.txtNomborPermit.value == "")
{
    document.f1.cbsemakradio[3].checked = false;
    document.f1.cbsemaks[3].checked = false;
	document.f1.cbsemaks[4].checked = false;
    document.f1.txtNomborPermit.value = "";
	document.f1.txtNomborSijil.value = ""

    document.f1.cbsemaks[5].checked = false;
	document.f1.cbsemaks[6].checked = false;
    document.f1.cbsemaks[7].checked = false;
}

}



function checkit9(){

if (document.f1.cbsemaks[8].checked == true)
	{	
	document.f1.cbsemaks[8].checked = true;
	document.f1.txtNomborResit.focus();
	}
	
	if (document.f1.cbsemaks[8].checked == false)
	{	
	document.f1.cbsemaks[8].checked = false;
	document.f1.txtNomborResit.value="";
	document.f1.txdTarikhByrn.value="";	
	
	}
	
}



function checkitC()
{
if(document.f1.txtNomborResit.value != "")
{
    document.f1.cbsemaks[8].checked = true;
	
}

if(document.f1.txtNomborResit.value == "")
{
    document.f1.cbsemaks[8].checked = false;
	document.f1.txdTarikhByrn.value = "";
  
}

}

function checkit10()
{

 if (document.f1.cbsemaks[10].checked == true)
	{	
	 document.f1.cbsemaks[11].checked = true;
	 }
	
	
if(document.f1.cbsemaks[10].checked == false)
{
  
	document.f1.cbsemaks[11].checked = false;
	document.f1.cbsemaks[12].checked = false;
	document.f1.cbsemaks[13].checked = false;
	document.f1.cbsemaks[14].checked = false;
	document.f1.cbsemaks[15].checked = false;
	  
}
}

function checkit11()
{
 if (document.f1.cbsemaks[8].checked == true)
	{	
	document.f1.cbsemaks[7].checked = true;	
	document.f1.cbsemaks[20].checked = true;	
	}
	
	if (document.f1.cbsemaks[8].checked == false)
	{	
	if(document.f1.cbsemaks[9].checked == false && document.f1.cbsemaks[10].checked == false )
	{
	document.f1.cbsemaks[7].checked = false;
	document.f1.cbsemaks[8].checked = false;
	document.f1.cbsemaks[9].checked = false;
	document.f1.cbsemaks[10].checked = false;
	document.f1.cbsemaks[11].checked = false;
	document.f1.cbsemaks[12].checked = false;
	document.f1.cbsemaks[20].checked = false;
	//document.f1.cbsemaks[14].checked = false;
	//document.f1.cbsemaks[15].checked = false;
	
	}
	else
	{
	document.f1.cbsemaks[7].checked = true;
	document.f1.cbsemaks[8].checked = false;
	
	}
 }
}


function checkit12()
{
 if (document.f1.cbsemaks[9].checked == true)
	{	
	document.f1.cbsemaks[7].checked = true;	
	document.f1.cbsemaks[20].checked = true;	
	}	
	if (document.f1.cbsemaks[9].checked == false)
	{	
	if(document.f1.cbsemaks[8].checked == false && document.f1.cbsemaks[10].checked == false )
	{
	
		document.f1.cbsemaks[7].checked = false;
		document.f1.cbsemaks[8].checked = false;
		document.f1.cbsemaks[9].checked = false;
		document.f1.cbsemaks[10].checked = false;
		document.f1.cbsemaks[11].checked = false;
		document.f1.cbsemaks[12].checked = false;
		document.f1.cbsemaks[20].checked = false;
	
	}
	else
	{
	document.f1.cbsemaks[7].checked = true;
	document.f1.cbsemaks[9].checked = false;
	
	}
 }
}


function checkit13()
{
 if (document.f1.cbsemaks[10].checked == true)
	{	
	document.f1.cbsemaks[7].checked = true;
	document.f1.cbsemaks[11].checked = true;	
	}	
	
	if (document.f1.cbsemaks[9].checked == false)
	{	
	if(document.f1.cbsemaks[8].checked == false && document.f1.cbsemaks[9].checked == false && document.f1.cbsemaks[10].checked == false )
	{
	
	document.f1.cbsemaks[7].checked = false;
	document.f1.cbsemaks[8].checked = false;
	document.f1.cbsemaks[9].checked = false;
	document.f1.cbsemaks[10].checked = false;
	document.f1.cbsemaks[11].checked = false;
	document.f1.cbsemaks[12].checked = false;
	
	}
	else
	{
	document.f1.cbsemaks[11].checked = false;
	document.f1.cbsemaks[12].checked = false;
	
	}
 }
}



function checkit14()
{
 if (document.f1.cbsemaks[11].checked == true)
	{	
	document.f1.cbsemaks[7].checked = true;
	document.f1.cbsemaks[10].checked = true;
	document.f1.cbsemaks[20].checked = true;	
	}	
	
	if (document.f1.cbsemaks[11].checked == false)
	{	
	
	
	
	
	
	if(document.f1.cbsemaks[12].checked == false && (document.f1.cbsemaks[9].checked == false && document.f1.cbsemaks[8].checked == false) )
	{
	document.f1.cbsemaks[10].checked = false;
	document.f1.cbsemaks[11].checked = false;
	document.f1.cbsemaks[12].checked = false;
	document.f1.cbsemaks[7].checked = false;
	document.f1.cbsemaks[8].checked = false;
	document.f1.cbsemaks[9].checked = false;
	document.f1.cbsemaks[20].checked = false;
	
	}
	else if(document.f1.cbsemaks[12].checked == false && (document.f1.cbsemaks[9].checked != false || document.f1.cbsemaks[8].checked != false) )
	{

	document.f1.cbsemaks[11].checked = false;
	document.f1.cbsemaks[10].checked = false;
	document.f1.cbsemaks[12].checked = false;
	
	
	}
	else
	{
	document.f1.cbsemaks[11].checked = false;
	
	
	}
 }
}



function checkit15()
{
 if (document.f1.cbsemaks[12].checked == true)
	{	
	document.f1.cbsemaks[7].checked = true;
	document.f1.cbsemaks[10].checked = true;
	document.f1.cbsemaks[20].checked = true;
	}	
	
	if (document.f1.cbsemaks[12].checked == false)
	{	
	if(document.f1.cbsemaks[11].checked == false && (document.f1.cbsemaks[9].checked == false && document.f1.cbsemaks[8].checked == false) )
	{
	document.f1.cbsemaks[10].checked = false;
	document.f1.cbsemaks[11].checked = false;
	document.f1.cbsemaks[12].checked = false;
	document.f1.cbsemaks[7].checked = false;
	document.f1.cbsemaks[8].checked = false;
	document.f1.cbsemaks[9].checked = false;
	document.f1.cbsemaks[20].checked = false;
	
	}
	else if(document.f1.cbsemaks[11].checked == false && (document.f1.cbsemaks[9].checked != false || document.f1.cbsemaks[8].checked != false) )
	{

	document.f1.cbsemaks[11].checked = false;
	document.f1.cbsemaks[12].checked = false;
	document.f1.cbsemaks[10].checked = false;
	document.f1.cbsemaks[20].checked = false;
	}
	else
	{
	document.f1.cbsemaks[15].checked = false;
	
	
	}
 }
}

function checkit16()
{

 if (document.f1.cbsemaks[13].checked == true)
	{	
	document.f1.cbsemaks[14].checked = true;
	document.f1.cbsemaks[15].checked = true;
	}	
	
	if (document.f1.cbsemaks[13].checked == false)
	{	
	document.f1.cbsemaks[14].checked = false;
	document.f1.cbsemaks[15].checked = false;
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[17].checked = false;
	document.f1.cbsemaks[18].checked = false;
	document.f1.cbsemaks[19].checked = false;
	document.f1.txtLainLainTujuan.value="";
	}	

}


function checkit17()
{

 if (document.f1.cbsemaks[14].checked == true)
	{	
	document.f1.cbsemaks[13].checked = true;
	document.f1.cbsemaks[15].checked = true;
	}	
	
	if (document.f1.cbsemaks[14].checked == false)
	{
		
	if (document.f1.cbsemaks[17].checked == true || document.f1.cbsemaks[19].checked == true || document.f1.cbsemaks[18].checked == true)
	{	
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[15].checked = false;
	}
	else if (document.f1.cbsemaks[17].checked == false && document.f1.cbsemaks[19].checked == false && document.f1.cbsemaks[18].checked == false)
	{	
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[15].checked = false;	
	document.f1.cbsemaks[13].checked = false;	
	document.f1.txtLainLainTujuan.value="";
	}
	
	}	

}


function checkit18()
{

 if (document.f1.cbsemaks[15].checked == true)
	{	
	document.f1.cbsemaks[13].checked = true;
	document.f1.cbsemaks[14].checked = true;
	}	
	
	if (document.f1.cbsemaks[15].checked == false)
	{
		
	if (document.f1.cbsemaks[16].checked == false && (document.f1.cbsemaks[17].checked == true || document.f1.cbsemaks[19].checked == true || document.f1.cbsemaks[18].checked == true))
	{	
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[15].checked = false;
	document.f1.cbsemaks[14].checked = false;
	}
	else if (document.f1.cbsemaks[16].checked == false && document.f1.cbsemaks[17].checked == false && document.f1.cbsemaks[19].checked == false && document.f1.cbsemaks[18].checked == false)
	{	
	document.f1.cbsemaks[14].checked = false;
	document.f1.cbsemaks[13].checked = false;
	document.f1.cbsemaks[15].checked = false;
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[17].checked = false;
	document.f1.cbsemaks[18].checked = false;
	document.f1.cbsemaks[19].checked = false;
	document.f1.txtLainLainTujuan.value="";
	}
	
	}	

}




function checkit19()
{

 if (document.f1.cbsemaks[16].checked == true)
	{	
	document.f1.cbsemaks[13].checked = true;
	document.f1.cbsemaks[14].checked = true;
	}	
	
	if (document.f1.cbsemaks[16].checked == false)
	{
		
	if (document.f1.cbsemaks[15].checked == false && (document.f1.cbsemaks[17].checked == true || document.f1.cbsemaks[19].checked == true || document.f1.cbsemaks[18].checked == true))
	{	
	document.f1.cbsemaks[17].checked = false;
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[15].checked = false;
	}
	else if (document.f1.cbsemaks[15].checked == false && document.f1.cbsemaks[17].checked == false && document.f1.cbsemaks[19].checked == false && document.f1.cbsemaks[18].checked == false)
	{	
	document.f1.cbsemaks[13].checked = false;
	document.f1.cbsemaks[15].checked = false;
	document.f1.cbsemaks[14].checked = false;
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[17].checked = false;
	document.f1.cbsemaks[18].checked = false;
	document.f1.cbsemaks[19].checked = false;
	
	document.f1.txtLainLainTujuan.value="";
	}
	
	}	

}


function checkit20()
{

 if (document.f1.cbsemaks[17].checked == true)
	{	
	document.f1.cbsemaks[13].checked = true;
	}	
	
	if (document.f1.cbsemaks[17].checked == false)
	{	
	if(document.f1.cbsemaks[14].checked == false && document.f1.cbsemaks[18].checked == false && document.f1.cbsemaks[19].checked == false)
	{
	document.f1.cbsemaks[13].checked = false;
	document.f1.cbsemaks[14].checked = false;
	document.f1.cbsemaks[15].checked = false;
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[17].checked = false;
	document.f1.cbsemaks[18].checked = false;
	document.f1.cbsemaks[19].checked = false;
	
	document.f1.txtLainLainTujuan.value="";
	}
	}
}

function checkit21()
{

 if (document.f1.cbsemaks[18].checked == true)
	{	
	document.f1.cbsemaks[13].checked = true;
	}	
	
	if (document.f1.cbsemaks[18].checked == false)
	{	
	if(document.f1.cbsemaks[14].checked == false && document.f1.cbsemaks[17].checked == false && document.f1.cbsemaks[19].checked == false)
	{
	document.f1.cbsemaks[13].checked = false;
	document.f1.cbsemaks[14].checked = false;
	document.f1.cbsemaks[15].checked = false;
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[17].checked = false;
	document.f1.cbsemaks[18].checked = false;
	document.f1.cbsemaks[19].checked = false;
	
	document.f1.txtLainLainTujuan.value="";
	}
	}
}


function checkit22()
{

 if (document.f1.cbsemaks[19].checked == true)
	{	
	document.f1.cbsemaks[13].checked = true;
	document.f1.txtLainLainTujuan.focus();	
	}	
	
	if (document.f1.cbsemaks[19].checked == false){	
	if(document.f1.cbsemaks[14].checked == false && document.f1.cbsemaks[17].checked == false && document.f1.cbsemaks[18].checked == false)
	{
		//alert("Read here");
	document.f1.txtLainLainTujuan.value="";
	document.f1.cbsemaks[13].checked = false;
	document.f1.cbsemaks[15].checked = false;
	document.f1.cbsemaks[14].checked = false;
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[17].checked = false;
	document.f1.cbsemaks[18].checked = false;
	document.f1.cbsemaks[19].checked = false;
	
	}
	}
}

function checkitD()
{

 if (document.f1.txtLainLainTujuan.value != "")
	{	
	document.f1.cbsemaks[13].checked = true;
	document.f1.cbsemaks[19].checked = true;
	return;
	}	
	
 if (document.f1.txtLainLainTujuan.value == "")
	{
		
if (document.f1.cbsemaks[16].checked == false && document.f1.cbsemaks[17].checked == false && document.f1.cbsemaks[18].checked == false)
	{	
	document.f1.cbsemaks[14].checked = false;
	document.f1.cbsemaks[13].checked = false;
	document.f1.cbsemaks[15].checked = false;
	document.f1.cbsemaks[16].checked = false;
	document.f1.cbsemaks[17].checked = false;
	document.f1.cbsemaks[18].checked = false;
	document.f1.cbsemaks[19].checked = false;
	document.f1.txtLainLainTujuan.value="";
	return;
	}
	else
	{
	document.f1.cbsemaks[19].checked = false;
	document.f1.txtLainLainTujuan.value="";
	return;
	}
	
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

function DoTheCheck() {

    var currentTime = new Date()
	var month = currentTime.getMonth() + 1
	var day = currentTime.getDate()
	var year = currentTime.getFullYear()
	//document.write(month + "/" + day + "/" + year)

	var date2 = day + "/" + month + "/" + year;	
	var dm = document.f1.txdTarikhByrn;
	var str1  = document.getElementById("txdTarikhByrn").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);
	
	if (document.f1.cbsemaks[0].checked == false) {
		alert("Sila masukkan maklumat Borang A");
		document.f1.cbsemaks[0].focus();
		return;
	}
	else if (document.f1.cbsemaks[3].checked == false) {
		alert("Sila masukkan Bukti Kematian");
		return;
	}

	else if (document.f1.cbsemakradio[0].checked == true && document.f1.txtNomborSijil.value == ""){
		alert("Sila masukkan No. Sijil Kematian");
		return;
	}
	else if (document.f1.cbsemakradio[3].checked == true && document.f1.txtNomborPermit.value == ""){
		alert("Sila masukkan No. Permit");
		return;
	}
		else if (document.f1.lepassatusept.value == "yes" && document.f1.cbsemaks[8].checked == false ){
		alert("Sila masukkan Bayaran Pemohonan");
	}
	else if (document.f1.lepassatusept.value == "yes" && document.f1.cbsemaks[8].checked == true && document.f1.txtNomborResit.value == "") {
		alert("Sila masukkan No. Resit Bayaran Pemohonan");
	}
	else if (document.f1.lepassatusept.value == "yes" && document.f1.cbsemaks[8].checked == true && document.f1.txdTarikhByrn.value == "") {
		alert("Sila masukkan Tarikh Bayaran Pemohonan");
	}
	else if (document.f1.lepassatusept.value == "yes" && isDate(dm.value)==false){
		dm.focus()
		return false
	}
	//else if (document.f1.cbsemaks[23].checked == false) {
	//	alert("Sila masukkan Memiliki Harta Tak Alih");
	//	return;
	//}
	else if ( date1 > currentTime ){
	
		alert("Sila pastikan tarikh bayaran tidak melebihi dari tarikh hari ini");
		
		txdTarikhByrn.focus();
		return;
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
		document.f1.submit();
	}	
	else
	{return;}
}
function Seterusnya() {
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
	document.f1.action="";
	document.f1.submit();
}


function validDate() {
	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
	

	if (document.f1.txdTarikhByrn.value > currentDate){
		alert("Sila pastikan tarikh bayaran tidak melebihi dari tarikh hari ini.");
		txdTarikhByrn.clear();
	}
	else
	{
	return;
	}
	
}

function Kembali(){
	document.f1.method="POST";
	document.f1.command.value="Kembali_semakan_pemohon";
	document.f1.action="";
	document.f1.submit();
}

function cetak(){
	window.print();
}

function tab()
{
	/*
    document.f1.command.value = "tab";	
	document.f1.action = "";	
	document.f1.submit();
	*/ 
	//razman add
	document.f1.command.value = "tab";
	document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal";
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


function checkit_harta()
{
if (document.f1.cbsemaks[11].checked == true || document.f1.cbsemaks[12].checked == true || document.f1.cbsemaks[14].checked == true || document.f1.cbsemaks[15].checked == true)
 {
 document.f1.cbsemaks[23].checked = true;
 }
 else
 {
 document.f1.cbsemaks[23].checked = false;
 }	
}

function checkit_harta_cancel()
{
if(document.f1.cbsemaks[23].checked == false)
{
document.f1.cbsemaks[10].checked = false;
document.f1.cbsemaks[11].checked = false;
document.f1.cbsemaks[12].checked = false;
document.f1.cbsemaks[13].checked = false;
document.f1.cbsemaks[14].checked = false;
document.f1.cbsemaks[15].checked = false;
}	
}



</script>