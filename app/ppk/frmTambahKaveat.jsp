<script type="text/javascript" src="../../library/js/CalendarPopup.js" ></script>
<link rel="stylesheet" type="text/css" href="../../library/js/jquery.timepickr.css" />
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />

<style type="text/css">
<!-- 
input[readonly] {
	background-color:#AAD198;
	border:#546F0E 1px solid;
	color:#000;
}
-->
</style>

<style type="text/css">
<!--
.style2 {color: #0000FF}
.style3 {
	color: #FF0000;
	font-size: 9px;
	font-style: italic;
}
.style4 {
	font-size: 9px;
	font-style: italic;
}
.style5 {color: #FF0000}
.style6 {font-size: 10px}
.style7 {font-size: 10px; color: #FF0000; }
.style8 {font-size: 10px; color: #000000; }
.style9 {color: #000000}
-->
</style>

<body  >

<form id="f1" name="f1" action="" method="post">
#if ($selesai == '1')
	Penambahan maklumat Pengkaveat telah berjaya.
	<table width="100%">
             <tr>
             <td align="center">
             <input type="button" name="tutup" id="tutup" value="Tutup" align="center" onClick="tutupTetingkap()" />
             </td>
             </tr>
             </table>
#else

#foreach ($ListData in $ViewPemohon)

#set ($jenis_permohonan = $ListData.jenispermohonan)
#set ($idPemohon = $ListData.idPemohon)
#set ($idSimati = $ListData.idSimati)
#set ($idpermohonan_simati = $ListData.id_Permohonansimati)
#set ($salinan_arahan = $ListData.salinan_arahan)
#set ($catatan_sd = $ListData.catatan_sd)
#set ($txtSuratAkuanARB = $ListData.txtSuratAkuanARB)
#set ($tujuanPindah = $ListData.tujuanPindah)<!-- razman add -->

#set ($jenisborangC = $ListData.jenisborangC)




     <input name="id_taraf_mohon" type="hidden"  value="$ListData.id_taraf_mohon"/>
     <input name="taraf_pemohon" type="hidden"  value="$ListData.taraf_pemohon"/>
     #set ($id_taraf_mohon = "$ListData.id_taraf_mohon")
   

    <input name="idFail" type="hidden"  value="$ListData.idFail"/>
    #set ($idFail = $ListData.idFail)
    
    
    <input name="id_Fail" type="hidden"  value="$ListData.idFail"/>
    <input name="idpermohonansimati" type="hidden"  value="$ListData.id_Permohonansimati"/>

	#set ($idPermohonan = $ListData.idPermohonan)
   <input name="idPermohonan" type="hidden"  value="$idPermohonan"/>
       
	#set ($noFail = $ListData.noFail)
    <input name="noFail" type="hidden"  value="$ListData.noFail"/>
    
	#set ($statusFail = $ListData.keterangan)
    <input name="keterangan" type="hidden"  value="$ListData.keterangan"/> 
       
    #set ($id_Status = $ListData.id_Status)
    <input name="id_Status" type="hidden"  value="$ListData.id_Status"/>  
    
     
	#set ($negeri = $ListData.namanegeri)
    <input name="namanegeri" type="hidden"  value="$ListData.namanegeri"/>
    
	#set ($tarikh_mohon = $ListData.tarikhMohon)
    <input name="tarikhMohon" type="hidden"  value="$ListData.tarikhMohon"/>
    
	#set ($daerah = $ListData.namadaerah)
    <input name="namadaerah" type="hidden"  value="$ListData.namadaerah"/>
    
	#set ($namasimati = $ListData.namaSimati)
    <input name="namaSimati" type="hidden"  value="$ListData.namaSimati"/>
    
	#set ($seksyen = $ListData.seksyen)
    <input name="seksyen" type="hidden"  value="$ListData.seksyen"/>
    
	#set ($namapemohon = $ListData.namaPemohon)
    <input name="namaPemohon" type="hidden"  value="$ListData.namaPemohon"/>
    
	#set ($namaUnit = $ListData.namaPejabat)
    <input name="namaPejabat" type="hidden"  value="$ListData.namaPejabat"/>
    
	
		#set ($jumHta = $Util.formatDecimal($ListData.jumHtaTarikhMohon))
        #set ($jumHtaX = $ListData.jumHtaTarikhMohon)
        
      
      
      #set ($jumHtaX1 = $Util.formatDecimal($jumHtaX))  
	
    <input name="jumHtaTarikhMohon" type="hidden"  value="$ListData.jumHtaTarikhMohon"/>
    
	
		#set ($jumHa = $Util.formatDecimal($ListData.jumHaTarikhMohon))
         #set ($jumHaX = $ListData.jumHaTarikhMohon)
        #set ($jumHaX1 = $Util.formatDecimal($jumHaX))
        
	
    <input name="jumHaTarikhMohon" type="hidden"  value="$ListData.jumHaTarikhMohon"/>
   
		#set ($Overall =  $Util.formatDecimal($ListData.jumHartaTarikhMohon))
        #set ($OverallX =  $ListData.jumHartaTarikhMohon)
        #set ($OverallX1 =  $Util.formatDecimal($OverallX))
        
        
        #set($Overalldum =  $ListData.jumHartaTarikhMohon)
	
   <input name="jumHartaTarikhMohon" type="hidden"  value="$ListData.jumHartaTarikhMohon"/>
    
	#set ($tarikh_Hantar_BorangB = $ListData.tarikhborangB)
    <input name="tarikhborangB" type="hidden"  value="$ListData.tarikhborangB"/>
    
	#set ($tarikh_Terima_BorangC = $ListData.tarikhborangC)
    <input name="tarikhborangC" type="hidden"  value="$ListData.tarikhborangC"/>
    
    #set ($security_Code_Link = $ListData.sCL)
    <input name="sCL" type="hidden"  value="$ListData.sCL"/>
    
	#set ($result_BorangC = $ListData.res_BrgC)
	<input name="res_BrgC" type="hidden"  value="$ListData.res_BrgC"/>
	
	#set ($kep_BorangC = $ListData.kep_BrgC)
	<input name="kep_BrgC" type="hidden"  value="$ListData.kep_BrgC"/>
        
	#set ($tarikh_Hantar_Nilaian = $ListData.tarikhhantarnilaian)
    <input name="tarikhhantarnilaian" type="hidden"  value="$ListData.tarikhhantarnilaian"/>
    
	#set ($tarikh_Terima_Nilaian = $ListData.tarikhterimanilaian)
    <input name="tarikhterimanilaian" type="hidden"  value="$ListData.tarikhterimanilaian"/>
    
    <input name="tarikh_mohon" type="hidden"  value="$ListData.tarikhMohon"/>
    
	#set ($jenisborangc = $ListData.jenisborangC)
    <input name="jenisborangC" type="hidden"  value="$ListData.jenisborangC"/>
    #set ($jenisborangcX  = $ListData.jenisborangC)
    
    
    #if ($jenisborangc == "P")
	#set ($check8 = "checked")
   
    #elseif ($jenisborangc == "K")
	#set ($check9 = "checked")
   
    #end
    
	#set ($keputusan = $ListData.keputusanpermohonan)
    <input name="keputusanpermohonan" type="hidden"  value="$ListData.keputusanpermohonan"/>
    
    #set ($catatan = $ListData.catatan)
    <input name="catatan" type="hidden"  value="$ListData.catatan"/>
    
    
     #set($jenis_pejabat = $ListData.jenis_pej)   
     #set($no_failawal = $ListData.nofailawal)
     #set($pemohon_awal = $ListData.pemohonawal)
     #set($jenis_pej_idd = $ListData.pejabatawal)
    
    
    
#set($txtNamaKaveat = $ListData.txtNamaKaveat)
#set($txtNoKaveat = $ListData.txtNoKaveat)
#set($txtNamaFirma = $ListData.txtNamaFirma)
#set($txtAlamat1Peguam =  $ListData.txtAlamat1Peguam)
#set($txtAlamat2Peguam = $ListData.txtAlamat2Peguam)
#set($txtAlamat3Peguam = $ListData.txtAlamat3Peguam)
#set($txtPoskodPeguam = $ListData.txtPoskodPeguam)
#set($socNegeriPeguam = $ListData.socNegeriPeguam)
#set($txtBandarPeguam = $ListData.txtBandarPeguam)
#set($txtNomborTelefonPeguam = $ListData.txtNomborTelefonPeguam)
#set($txtTarikhKaveat = $ListData.txtTarikhKaveat)
    
     <input name="negerimahkamah" type="hidden"  value="$ListData.negerimahkamah"/>
    <input name="daerahmahkamah" type="hidden"  value="$ListData.daerahmahkamah"/>
    
    #set($negerimahkamah = $ListData.negerimahkamah)
    #set($daerahmahkamah = $ListData.daerahmahkamah)
    
    
    <!--
    	h.put("id_Daerah_Mahkamah", rs.getString("id_Daerah_Mahkamah")==null?"":rs.getString("id_Daerah_Mahkamah"));
				h.put("id_Negerimahkamah", rs.getString("id_Negerimahkamah")==null?"":rs.getString("id_Negerimahkamah"));
				h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
				
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));				
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));				
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				
				h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
				h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
				
    -->
    
    
    
   
    
#end


 
 <fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">PENAMBAHAN MAKLUMAT KAVEAT</font></legend>
        <table width="100%">
             <tr>
  <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
                <td width="28%"><div align="right" class="style53 style6 style6">
                <div align="left">Nama Pengkaveat </div>
              </div>                </td>
              <td width="1%"><div align="center" class="style38">:</div></td>
              <td width="70%"><label>
                <input name="txtNamaKaveat" type="text" id="txtNamaKaveat" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$txtNamaKaveat2a" size="50" maxlength="150"  />
              </label></td>
            </tr>
            <tr>
               <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
              <td><div align="right" class="style53 style6 style6">
                <div align="left">No. Kaveat</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtNoKaveat" type="text" id="txtNoKaveat" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" size="34"  value="$txtNoKaveat2a" maxlength="25" />
              </label>
              
              </td>
            </tr>
            <tr>
              <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
              <td width="28%"><div align="right" class="style53 style6 style6">
                <div align="left">Nama Firma</div>
              </div>                </td>
              <td width="1%"><div align="center" class="style38">:</div></td>
              <td width="70%"><label>
                <input name="txtNamaFirma" type="text" id="txtNamaFirma" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()" size="50"  value="$txtNamaFirma2a"/>
              </label></td>
            </tr>
            
            <tr>
                <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
              <td class="style38"><div align="right" class="style53 style6 style6">
                 <div align="left">Alamat </div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtAlamat1Peguam" type="text" id="txtAlamat1Peguam" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$txtAlamat1Peguam2a" size="50" maxlength="150"  />
              </label></td>
            </tr>
            <tr>
                <td width="1%" valign="top">&nbsp;</td>
              <td class="style38"><div align="left"><span class="style3"><span class="style52"><span class="style6"><span class="style6"></span></span></span></span></div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtAlamat2Peguam" type="text" id="txtAlamat2Peguam" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$txtAlamat2Peguam2a" size="50" maxlength="150"/>
              </label></td>
            </tr>
            <tr>
              <td class="style51 style6 style5">&nbsp;</td>
              <td class="style38"><div align="left"><span class="style3"><span class="style52"><span class="style6"><span class="style6"></span></span></span></span></div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><input name="txtAlamat3Peguam" type="text" id="txtAlamat3Peguam"   style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  value="$txtAlamat3Peguam2a" size="50" maxlength="150"  /></td>
            </tr>
            <tr>
                <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
              <td><div align="right" class="style53 style6 style6">
                 <div align="left">Poskod</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtPoskodPeguam" type="text" id="txtPoskodPeguam" size="5" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  onkeyup="javascript:validateIC(event,this,this.value,'txtPoskodPeguam')" maxlength="5" value="$txtPoskodPeguam2a"/>
              </label></td>
            </tr>
            <tr>
              <td width="1%" valign="top" ><span class="style7">#if($setMode != "disabled")* #end</span></td>
            <td width="28%" class="style38"><div align="right" class="style51 style52 style6">
              <div align="left">#if($setMode != "disabled")  Negeri1 #else <span class="style52">Negeri2</span> #end</div>
            </div></td>
            <td width="1%"><div align="center" class="style38">:</div></td>
            <td width="70%">
            
            
            #set ($setMode = "")
            #if($setMode == "disabled")
             #if($socNegeriPeguam==""||$socNegeriPeguam==0)
                                      
            #set($negerikodpeguam="")
                                      
            #else
            #foreach($listnegpeg in $listnegeri)           
            #if($socNegeriPeguam==$listnegpeg.id_Negeri)
            #set($negerikodpeguam="$listnegpeg.kod_Negeri - $listnegpeg.nama_Negeri")
            #end 
            
            #end 
            #end      
            <input name="socNegeriPeguam" type="text"  id="socNegeriPeguam" style="text-transform:uppercase;" value="$negerikodpeguam" size="50"  />
            #else
        
            #if($socNegeriPeguam==""||$socNegeriPeguam==0)
                                      
            #set($negerikodpeguam="")
                                      
            #else
           #foreach($listnegpeg in $listnegeri)  
                   
            #if($socNegeriPeguam==$listnegpeg.id_Negeri)
            
            #set($negerikodpeguam="$listnegpeg.kod_Negeri - $listnegpeg.nama_Negeri")
            
            #end 
            
            #end 
            #end      
           
                          
                                  #if(($socNegeriPeguam=="")||($socNegeriPeguam=="0"))
                                  <select name="socNegeriPeguam" class="autoselect" style="text-transform:uppercase;" onBlur="uppercase()" onChange="getBandarTetap()" >
                                  <option value="" style="text-transform:uppercase;" onBlur="uppercase()" >Sila Pilih Negeri</option>
                                  #foreach($listneg in $listnegeri)
                                  <option value="$listneg.id_Negeri" style="text-transform:uppercase;" onBlur="uppercase()" >$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                  #end
                                  </select>                                  
                                  #else
                                  <select name="socNegeriPeguam" class="autoselect" id="socNegeriPemohon" style="text-transform:uppercase;" onBlur="uppercase()" onChange="getBandarTetap()"  $setMode >
                                  
                                  <option value="$socNegeriPeguam" style="text-transform:uppercase;" onBlur="uppercase()" >$negerikodpeguam</option>
                                  #foreach($listneg in $listnegeri)
                                  #if($socNegeriPeguam!=$listneg.id_Negeri)
                                  <option value="$listneg.id_Negeri">$listneg.kod_Negeri - $listneg.nama_Negeri</option>
                                  #end    
	                              #end
                                  
                                  </select>

                                  #end
                                  
                        #end        </td>
             </tr>
             
              #set($bandartetap = $txtBandarPeguam)
          <tr>
             <td width="1%" valign="top" >&nbsp;</td>
            <td class="style38"><div align="right" class="style51 style52 style6">
              <div align="left">#if($setMode != "disabled")  Bandar #else <span class="style52">Bandar</span> #end</div>
            </div></td>
            <td class="style36"><div align="right"><span class="style38">:</span></div></td>
            <td><label>             
              #if($setMode == "disabled")
              #if($bandartetap == ""||$bandartetap == 0)                             
              #set($bandarkodpeguam = "")                                      
              #else
              #foreach($listdaerah in $listBandarTetapbyNegeri)                                
              #if($bandartetap == $listdaerah.id) 
              #set($bandarkodpeguam = "$listdaerah.kod - $listdaerah.nama")                               
              
              #end 
              #end
            
            
            #end      
            <input name="txtBandarPeguam" type="text"  id="txtBandarPeguam" style="text-transform:uppercase;" value="$bandarkodpeguam" size="50"  />
            #else
            
              #foreach($listdaerah in $listBandarTetapbyNegeri)                                
              #if($bandartetap==$listdaerah.id)                                
              #set($listDaerahbyNegeriK=$listdaerah.kod)
              #set($listDaerahbyNegeriN=$listdaerah.nama)
              #end 
              #end
              
              
              #if($disabled=="disabled")
              #set($setModedaerah="disabled")
              #end
              #if($bandartetap!="" && $bandartetap!="0" )
              <select name="txtBandarPeguam" id="txtBandarPeguam" class="autoselect"   style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandarTetap()" >
                                      <option value="$bandartetap">$listDaerahbyNegeriK - $listDaerahbyNegeriN</option>
                
                                  #foreach($listdaerah in $listBandarTetapbyNegeri)
                                 
                                  #if($bandartetap!=$listdaerah.id)
                                    
	                    
                <option value="$listdaerah.id">$listdaerah.kod - $listdaerah.nama</option>
                             
                                   
                                  #end    
	                               #end
                                 
              </select>
              #else
              <select name="txtBandarPeguam" id="txtBandarPeguam" class="autoselect"  style="text-transform:uppercase;" onBlur="uppercase()" onclick="CheckBandarTetap()" >
                <option value="">Sila Pilih Bandar</option>                
                                  #foreach($listDaerah in $listBandarTetapbyNegeri)
                <option value="$listDaerah.id">$listDaerah.kod - $listDaerah.nama</option>
	                               #end
              </select>
              #end 
              #end
              </label></td>
          </tr>
            <tr>
               <td width="1%" valign="top">&nbsp;</td>
              <td><div align="right" class="style51 style52 style6 style6">
                <div align="left">No Telefon</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtNomborTelefonPeguam" type="text" id="txtNomborTelefonPeguam" onKeyUp="javascript:validateIC(event,this,this.value,'txtNomborTelefonPeguam')" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  size="14" maxlength="14"  value="" />
              </label></td>
            </tr>
             <tr>
               <td width="1%" valign="top">&nbsp;</td>
              <td><div align="right" class="style51 style52 style6 style6">
                <div align="left">Tarikh Kaveat</div>
              </div></td>
              <td><div align="center" class="style38">:</div></td>
              <td><label>
                <input name="txtTarikhKaveat" type="text" id="txtTarikhKaveat" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()"  size="10" maxlength="10"  value=""  />
              </label>
                #if ($EventStatus != 1)
          <a href="javascript:displayDatePicker('txtTarikhKaveat',false,'dmy');"/><img border="0" src="../../img/calendar.gif"/></a>
          #end
              </td>
            </tr>
        </table>
        
</fieldset>
<table width="100%">
             <tr>
             <td align="center"><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="getSimpan()"/> &nbsp;
             <input type="button" name="tutup" id="tutup" value="Tutup" align="center" onClick="tutupTetingkap()" />
             </td>
             </tr>
             </table>
             <input type="hidden" name="command"/>
#end
</form>

</body>

<script>

function tutupTetingkap() {
	window.opener.location.reload();
	window.close();
}

function getBandarTetap(){
	
		document.f1.command.value = "get_Bandarbaru";
			document.f1.action="?_portal_module=FrmTambahKaveat";
		   document.f1.submit();
		


	}
checkDateKP();

function checkDateKP() {

	
	var d1 = document.f1.txdTarikhHantarBorangB;
	var d3 = document.f1.txdTarikhHantarNilaian;
	
	var cmdSeterusnya = document.f1.cmdSeterusnya;		
	
    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = day + "/" + month + "/" + year;
	
    var str1  = document.getElementById("txdTarikhHantarBorangB").value;    
    var str3  = document.getElementById("txdTarikhHantarNilaian").value;
    var str5  = document.getElementById("tarikh_permohonan").value;  
	
	if(str1!="")
	{  
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);
	var date1 = new Date(yr1, mon1, dt1);//txdTarikhHantarBorangB
	}
    if(str3!="")
	{
    var dt3   = parseInt(str3.substring(0,2),10);
    var mon3  = parseInt(str3.substring(3,5),10)-1;
    var yr3   = parseInt(str3.substring(6,10),10);
	var date3 = new Date(yr3, mon3, dt3);//txdTarikhHantarNilaian
	}
    if(str5!="")
	{   
    var dt5   = parseInt(str5.substring(0,2),10);
    var mon5  = parseInt(str5.substring(3,5),10)-1;
    var yr5   = parseInt(str5.substring(6,10),10);
	var date5 = new Date(yr5, mon5, dt5);//txdTarikhTPemrohonan
	}
	
	var string_field_temp = "";
  
    if(str1=="" || str3=="")
	{
	 
	
		if(testIsValidObjectKP(cmdSeterusnya)==false)
		{
		string_field_temp = string_field_temp + "<input type='hidden' id='cmdSeterusnya' name='cmdSeterusnya'/>";
		}
		else
		{
		string_field_temp = string_field_temp + "";
		}	    
		
		
		
		$jquery("#alert_field").html("<font color = 'red'>Fail Permohonan belum cukup tempoh 30 hari daripada tarikh hantar borang B dan proses permohonan tidak dapat diteruskan. Sila pastikan maklumat <b>Tarikh Hantar Borang B</b> dan <b>Tarikh Hantar Nilaian</b> di isi.</font>"+string_field_temp);	
		//alert("1");
		//alert("2"+document.getElementById("cmdSeterusnya"));
//		document.getElementById('cmdSimpan').style.display="none";
//		document.getElementById('cmdSeterusnya').style.display="none";
		document.getElementById("cmdSeterusnya").disabled = true;
		
	} 
	else
	{

		$jquery("#alert_field").html("");
		document.getElementById("cmdSeterusnya").disabled = false;
	} 
  
}





function testIsValidObjectKP(objToTest) {

//alert("objToTest:"+objToTest)
if (objToTest == null || objToTest == undefined) {
return false;
}
return true;
}


function uploadSuppDoc(id,IdSimati)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppk.SkrinPopupUploadDokumen?&id_Permohonan="+id+"&IdSimati="+IdSimati+"&id_jenisDoc=99205";
	var hWnd = window.open(url,'printuser','width=350,height=200, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
	
	
}

function getKemaskini() {
	document.f1.method="post";
	document.f1.command.value="getKemaskini_keputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
}
function getKemaskinibatal() {
input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method="post";
	document.f1.command.value="getKemaskini_keputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
	}else
	{return;}
}
function getBatal() {
	document.f1.method="post";
	document.f1.command.value="getBack_keputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
}


function semakMTPermohonan() {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&command=borangPermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function semakMTBorangI() {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&command=hantarBorangI&dari=keputusanpermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=500, resizable=yes,scrollbars=no');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function tambahKaveat() {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmTambahKaveat?idFail=$idFail&command=hantarBorangI&dari=keputusanpermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=500, resizable=yes,scrollbars=no');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function hantarBorangI() {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmIntegrasiMT?idFail=$idFail&command=borangI";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function semakBorangC(x) {
    var url = "../x/${securityToken}/ekptg.view.ppk.FrmMTBorangC?noFail="+x+"&command=borangPermohonan";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function muatTurunBorangC() {
    var url = "http://ettap.kehakiman.gov.my/BKM/bkm_frmSearchProbatePublic.aspx?SecurityCode=$!security_Code_Link";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function getSimpan() {

  
   if(document.f1.txtNamaKaveat.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan nama pengkaveat diisi" );		
    document.f1.txtNamaKaveat.focus();   
   }
   else if(document.f1.txtNoKaveat.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan no kaveat diisi" );		
    document.f1.txtNoKaveat.focus();   
   }
   else if(document.f1.txtNamaFirma.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan nama firma diisi" );		
    document.f1.txtNamaFirma.focus();   
   }
   else if(document.f1.txtAlamat1Peguam.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan alamat peguam diisi" );		
    document.f1.txtAlamat1Peguam.focus();   
   }
   else if(document.f1.txtPoskodPeguam.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan poskod alamat peguam diisi" );		
    document.f1.txtPoskodPeguam.focus();   
   }
   else if(document.f1.socNegeriPeguam.value == "" && document.f1.sorPenentuanBidangKuasa[0].checked == true && document.f1.radio_j[3].checked  == true )
   {
    alert("Sila pastikan negeri alamat peguam dipilih" );		
    document.f1.socNegeriPeguam.focus();   
   }
  
   
   
   else{

input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method="post";
	document.f1.command.value="getSimpan_keputusan";
	document.f1.action="?_portal_module=FrmTambahKaveat";
	document.f1.submit();
	}else
	{	
	return;
	}
	}
	
}
function getMahkamah(idPermohonan,command) {
	var url = "../x/${securityToken}/FrmSenaraiFailKeputusanPermohonanInternal?idPermohonan="+idPermohonan+"&command="+command;
	var hWnd = window.open(url,'printuser','width=600,height=600, resizable=yes,scrollbars=yes');
	document.f1.sorPenentuanBidangKuasaTeruskan[0].checked == false;
}
function selectRadio1() {

if(document.f1.sorPenentuanBidangKuasa[0].checked == true)
{

if(document.f1.jumlah.value >= nilaimax)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = true;
}
else
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
}

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;

document.getElementById('tableReportX').style.display="none";//razman add
}






/*
	if (document.f1.sorPenentuanBidangKuasa[0].checked == true) {
		document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = true;
	}
	if (document.f1.sorPenentuanBidangKuasa[1].checked == true) {
		document.f1.sorPenentuanBidangKuasa[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
	}
	if (document.f1.sorPenentuanBidangKuasa[3].checked == true) {
		document.f1.sorPenentuanBidangKuasa[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
	}
	*/
}

function selectRadio2() {

//ubah2

// alert("1"+document.f1.sorPenentuanBidangKuasaTeruskan[0].checked)
//alert("2"+document.f1.sorPenentuanBidangKuasaTeruskan[1].checked)


if(document.f1.sorPenentuanBidangKuasaTeruskan[0].checked == true)
{
document.f1.sorPenentuanBidangKuasa[0].checked = true;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = false;
document.getElementById('tableReportX').style.display="none"; //razman add
document.getElementById('kv1').style.display="block";
document.getElementById('kv2').style.display="block";
//document.getElementById('kaveat').style.display="block";
//alert("test1")
}
}

function selectRadio3() {

if(document.f1.sorPenentuanBidangKuasaTeruskan[1].checked == true)
{
document.f1.sorPenentuanBidangKuasa[0].checked = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;

document.getElementById('tableReportX').style.display="none";//razman add

}

}

function nktambah() {
	document.getElementById('kaveattambah').style.display="block";
	
}


function selectRadio4() {
if(document.f1.sorPenentuanBidangKuasa[1].checked == true)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;

document.f1.sorPenentuanBidangKuasa[0].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.f1.sorPenentuanBidangKuasa[3].checked = false;
document.getElementById('tableReportX').style.display="none"; //razman add
}

}


function selectRadio5() {

	if(document.f1.sorPenentuanBidangKuasa[2].checked == true)
	{
		document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
		document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
		document.f1.sorPenentuanBidangKuasa[0].checked = false;
		document.f1.sorPenentuanBidangKuasa[1].checked = false;
		document.f1.sorPenentuanBidangKuasa[3].checked = false;
		document.getElementById('tableReportX').style.display="block"; //razman edit add block
		//alert("1");
	}

}

function selectRadio6() {

if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;

document.f1.sorPenentuanBidangKuasa[0].checked = false;

document.f1.sorPenentuanBidangKuasa[1].checked = false;

document.f1.sorPenentuanBidangKuasa[2].checked = false;

document.getElementById('tableReportX').style.display="none";//razman add
}


document.f1.jenis_pej.value = "";
document.f1.jenis_pej_id.value = "";


document.f1.tempatmohonawal.value = "";



}

function putih(){

if(document.f1.jumlah.value >= nilaimax)
{
document.f1.sorKeputusanBorangC[0].checked = true;
document.f1.sorKeputusanBorangC[1].checked = false;


document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = false;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = true;
document.f1.sorPenentuanBidangKuasa[3].checked = false;

document.f1.sorPenentuanBidangKuasa[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = false;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;

document.f1.setMode1.value = "disabled";
document.f1.setMode2.value = "disabled";
document.f1.setMode3.value = "disabled";
document.f1.setMode4.value = "disabled";
document.f1.setMode5.value = "";
document.f1.setMode6.value = "";


}
else
{

if('$!headerppk.TOTAL_HTA' == 0 && '$!headerppk.TOTAL_HA' == 0)
{ 
document.f1.sorKeputusanBorangC[0].checked = true;
document.f1.sorKeputusanBorangC[1].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = false;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = true;
document.f1.sorPenentuanBidangKuasa[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = true;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;
document.f1.setMode1.value = "disabled";
document.f1.setMode2.value = "disabled";
document.f1.setMode3.value = "disabled";
document.f1.setMode4.value = "disabled";
document.f1.setMode5.value = "disabled";
document.f1.setMode6.value = "";
}
else
{
document.f1.sorKeputusanBorangC[0].checked = true;
document.f1.sorKeputusanBorangC[1].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = false;
document.f1.sorPenentuanBidangKuasa[1].checked = true;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = false;
document.f1.sorPenentuanBidangKuasa[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[1].disabled = false;
document.f1.sorPenentuanBidangKuasa[2].disabled = false; // razman add false
document.f1.sorPenentuanBidangKuasa[3].disabled = false;
document.f1.setMode1.value = "disabled";
document.f1.setMode2.value = "disabled";
document.f1.setMode3.value = "disabled";
document.f1.setMode4.value = "";
document.f1.setMode5.value = "";  // razman buang disable
document.f1.setMode6.value = "";
}



}



}

function kuning(){
if(document.f1.id_taraf_mohon.value != "")
{var idm = document.f1.id_taraf_mohon.value;}
else
{idm="";}

//alert("::::: IDM"+idm);

if(document.f1.jumlah.value >= nilaimax)
{


document.f1.sorKeputusanBorangC[0].checked = false;
document.f1.sorKeputusanBorangC[1].checked = true;


//document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
//document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = true;
if(idm == 6)
{
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = true;
}else
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = true;
}

document.f1.sorPenentuanBidangKuasa[0].checked = true;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = false;


document.f1.sorPenentuanBidangKuasa[0].disabled = false;


//document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
//document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;
if(idm == 6)
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;
}
else
{
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;
}
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = true;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;

document.f1.setMode1.value = "";
//document.f1.setMode2.value = "disabled";
if(idm == 6)
{
document.f1.setMode2.value = "";
}
else
{
document.f1.setMode2.value = "disabled";
}
document.f1.setMode3.value = "";
document.f1.setMode4.value = "disabled";
document.f1.setMode5.value = "disabled";
document.f1.setMode6.value = "";


document.f1.txtCatatan.value = "";





   
  //  #set($catatan="Pindah ke Mahkamah Tinggi kerana nilai harta melebihi 2 juta ringgit")
    
}

else
{

document.f1.sorKeputusanBorangC[0].checked = false;
document.f1.sorKeputusanBorangC[1].checked = true;


document.f1.sorPenentuanBidangKuasaTeruskan[0].checked = true;
document.f1.sorPenentuanBidangKuasaTeruskan[1].checked = false;
document.f1.sorPenentuanBidangKuasa[0].checked = true;
document.f1.sorPenentuanBidangKuasa[1].checked = false;
document.f1.sorPenentuanBidangKuasa[2].checked = false;
document.f1.sorPenentuanBidangKuasa[3].checked = false;


document.f1.sorPenentuanBidangKuasa[0].disabled = false;
document.f1.sorPenentuanBidangKuasaTeruskan[0].disabled = false;
document.f1.sorPenentuanBidangKuasaTeruskan[1].disabled = false;
document.f1.sorPenentuanBidangKuasa[1].disabled = true;
document.f1.sorPenentuanBidangKuasa[2].disabled = true;
document.f1.sorPenentuanBidangKuasa[3].disabled = false;

document.f1.setMode1.value = "";
document.f1.setMode2.value = "";
document.f1.setMode3.value = "";
document.f1.setMode4.value = "disabled";
document.f1.setMode5.value = "disabled";
document.f1.setMode6.value = "";

//document.f1.txtCatatan.value = "";

}

/*
if(document.f1.jumlah.value > nilaimax)
{

 #set ($check8 = "checked")
    #set  ($check3 = "") 
    
    #set ($setMode = "")
    #set ($setMode1 = "disabled")
    #set ($setMode2 = "disabled")
    #set ($setMode3 = "disabled")
    #set ($setMode4 = "disabled")
    #set ($setMode5 = "")
    #set ($setMode6 = "")
      
    #set  ($check0 = "")   
    #set  ($check1 = "")   
    #set  ($check2 = "") 
    #set  ($check4 = "checked")   
    #set  ($check5 = "")      
	#set ($check9 = "")
    
    #set($catatan="Pindah ke Mahkamah Tinggi kerana nilai harta melebihi 2 juta ringgit")
    

}
else
{




}
*/
}

function edit_item(){
	input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {
	document.f1.method="post";
	document.f1.command.value = "paparKeputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	document.f1.submit();
	}
	else
	{
	return;
	}
}

function doOpen(id) {
	//alert('id : '+id);
    var url = "../servlet/ekptg.view.ppk.DisplayBuktiKematian?id="+id+"&jenisDoc=99205";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function deleteSuppDoc()
{
	input_box = confirm("Adakah anda pasti?");
	if (input_box == true) {
	document.f1.method = "POST";
	document.f1.command.value = "deleteSuppDocMode";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
	}
	else
		{
		return
		}
	
	
}


/*
function cetak(noFail) {
    var url = "../servlet/ekptg.report.ppk.test?NoFail="+noFail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}*/



function cetakSuratBatalPermohonan(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratBatalPermohonan&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratKaveat(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratKaveat&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratPindahMT(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratPindahMT&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBorangI(noFail) {
    //var url = "../servlet/ekptg.report.ppk.BorangI?nofail="+noFail;
	
	var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=BorangI&flagReport=B";
	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

	function buktiPenyampaian(noFail,idfail){	
	    var url = "../servlet/ekptg.report.ppk.BuktiPenyampaianMT?nofail="+noFail+"&idfail="+idfail+"&template=BuktiPenyampaianMT";  
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
	}

	function setTable(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}
	
function setTableAwal(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}


function setTableA(id,loc,tab){
//ubah keputusan
//alert("1"+document.f1.sorPenentuanBidangKuasaTeruskan[0].checked)
//alert("2"+document.f1.sorPenentuanBidangKuasaTeruskan[1].checked)
//alert("3"+document.f1.sorKeputusanBorangC[1].checked+""+id)

var jum = document.f1.jumlah.value;
//alert(jum)
//document.f1.setMode1.value = "checked";
if(document.f1.sorKeputusanBorangC[1].checked == true && document.getElementById(id).style.display=="none" )
{

    document.f1.v_loc.value = loc;
	document.f1.v_tab.value = tab;	
	document.f1.setMode1.value = "checked";
		document.getElementById(id).style.display="block";

if(jum >= nilaimax)
{
document.getElementById('tableReportX').style.display="none";
		document.f1.radio_j[2].checked = true;
		document.f1.jenis_pej.value = 8;
		
		document.f1.checkJ1.value = "";
		document.f1.checkJ2.value = "";
		document.f1.checkJ4.value = "";
		document.f1.checkJ3.value = "checked";
}
else{
		document.getElementById('tableReportX').style.display="none";
		document.f1.radio_j[0].checked = true;
		document.f1.jenis_pej.value = 9;
		
		document.f1.checkJ1.value = "checked";
		document.f1.checkJ2.value = "";
		document.f1.checkJ3.value = "";
		document.f1.checkJ4.value = "";
}
		
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value  = "";
			document.f1.viewcheckJ3.value  = "";
			document.f1.viewcheckJ4.value  = "";
			
	
	
		
		
	
document.f1.submit();

/*
if(document.f1.sorPenentuanBidangKuasaTeruskan[1].checked == true)
{

			document.f1.setMode3.value = "checked";
			document.f1.setMode2.value = "";	
				
			document.f1.v_loc.value = loc;
			document.f1.v_tab.value = tab;	
	
			document.getElementById(id).style.display="block";
			document.getElementById('tableReportX').style.display="none";
			document.f1.radio_j[2].checked = true;
			document.f1.jenis_pej.value = 8;
			
			document.f1.checkJ1.value = "";
			document.f1.checkJ2.value = "";
			document.f1.checkJ3.value = "checked";
			document.f1.command.value = "get_jenisPejabat";
			document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value = "";
			document.f1.viewcheckJ3.value = "";				
					
}	
*/
	
}
}




/*
function setTableA(id,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true && document.getElementById(id).style.display=="none" )
{
	document.f1.v_loc.value = loc;
	document.f1.v_tab.value = tab;
		document.getElementById(id).style.display="block";
		document.getElementById('tableReportX').style.display="none";
		document.f1.radio_j[2].checked = true;
		document.f1.jenis_pej.value = 8;		
		document.f1.checkJ1.value = "";
		document.f1.checkJ2.value = "";
		document.f1.checkJ3.value = "checked";
		document.f1.command.value = "get_jenisPejabatLama";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value  = "";
			document.f1.viewcheckJ3.value  = "";
			
			
		
		
	
document.f1.submit();

  document.getElementById('tableReportPindah').style.display="none";
	
}


}

*/





//ubah
//baru ni
function setTableA_X1(id,loc,tab){

document.f1.setMode1.value = "checked";
if(document.f1.sorKeputusanBorangC[1].checked == true )
{

//alert(document.f1.v_loc.value)
	document.f1.v_loc.value = loc;
	document.f1.v_tab.value = tab;
	
			document.f1.setMode2.value = "checked";
			document.f1.setMode3.value = "";	
		
			document.getElementById(id).style.display="block";
			document.getElementById('tableReportX').style.display="none";
			document.f1.radio_j[0].checked = true;
			document.f1.jenis_pej.value = 9;
			
			document.f1.checkJ1.value = "checked";
			document.f1.checkJ2.value = "";
			document.f1.checkJ3.value = "";
			document.f1.checkJ4.value = "";
			
			document.f1.command.value = "get_jenisPejabat";
			document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			document.f1.radio_j[3].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value = "";
			document.f1.viewcheckJ3.value = "";	
		//	document.f1.viewcheckJ4.value = "";	
}

if(document.f1.sorPenentuanBidangKuasaTeruskan[1].checked == true)
{

			document.f1.setMode3.value = "checked";
			document.f1.setMode2.value = "";	
				
			document.f1.v_loc.value = loc;
			document.f1.v_tab.value = tab;	
	
			document.getElementById(id).style.display="block";
			document.getElementById('tableReportX').style.display="none";
			document.f1.radio_j[2].checked = true;
			document.f1.jenis_pej.value = 8;
			
			document.f1.checkJ1.value = "";
			document.f1.checkJ2.value = "";
			document.f1.checkJ3.value = "checked";
			document.f1.checkJ4.value = "";
			document.f1.command.value = "get_jenisPejabat";
			document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
		
			document.f1.radio_j[0].disabled = false;
			document.f1.radio_j[1].disabled = false;
			document.f1.radio_j[2].disabled = false;
			document.f1.radio_j[3].disabled = false;
			
			document.f1.viewcheckJ1.value = "";
			document.f1.viewcheckJ2.value = "";
			document.f1.viewcheckJ3.value = "";	
			document.f1.viewcheckJ4.value = "";				
					
}	

document.f1.submit();	

}




function setTableA_JENISA(id,val,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true  )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.f1.radio_j[0].checked = true;
		
		document.f1.jenis_pej.value = val;
		
		document.f1.checkJ1.value = "checked";
		
		document.f1.checkJ2.value = "";
		document.f1.checkJ3.value = "";
		document.f1.checkJ4.value = "";
		
		
		
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	document.f1.submit();
	
}

}


function setTableA_JENISB(id,val,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.f1.radio_j[1].checked = true;
		document.f1.jenis_pej.value = val;
		document.f1.checkJ2.value = "checked";
		document.f1.checkJ1.value = "";
		document.f1.checkJ3.value = "";
		document.f1.checkJ4.value = "";
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	document.f1.submit();
	
}

}




function setTableA_JENISC(id,val,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true  )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.f1.radio_j[2].checked = true;
		document.f1.jenis_pej.value = val;
		document.f1.checkJ3.value = "checked";
		
		document.f1.checkJ1.value = "";
		document.f1.checkJ2.value = "";
		document.f1.checkJ4.value = "";
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	   document.f1.submit();
	
}

}






function setTableA_J(id,idx){

//alert(document.f1.sorPenentuanBidangKuasa[3].checked)
if(document.f1.sorKeputusanBorangC[1].checked == true && document.getElementById(id).style.display=="none" )
{
//ubah3	

document.getElementById(id).style.display="block";


if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
{
document.getElementById(id).style.display="none";
}
	
}
else if(document.f1.sorKeputusanBorangC[0].checked == true && document.getElementById(idx).style.display=="none" )
{
       //razman add open
	  // alert('x');
	   if(document.f1.sorPenentuanBidangKuasa[2].checked == true)
	   {
		  document.getElementById(idx).style.display="block";	
	   }
	   else
	   {
		  document.getElementById(idx).style.display="none";
	   }
	   //razman add close
	   
	  
	    if(document.f1.jumlah.value >= nilaimax)
		{
		 	document.getElementById(idx).style.display="block";		
			if(document.f1.sorPenentuanBidangKuasa[3].checked == true)
			{
			 document.getElementById(idx).style.display="none";
			}
			document.getElementById(id).style.display="none";
		}
		
		
		if(document.f1.jumlah.value < nilaimax)
		{
	
		//document.getElementById(idx).style.display="none"; //razman comment
		//document.getElementById(id).style.display="none"; //razman comment
		
		}
		
		
}


}
function setTableB_TUTUP()
{
document.getElementById('tableReportA').style.display="none";
document.getElementById('tableReportX').style.display="none";


}

function setTableB(id,idx){
if(document.getElementById(id).style.display=="block" )
{
	
		document.getElementById(id).style.display="none";
	
}

//razman add open
if(document.f1.sorPenentuanBidangKuasa[2].checked == true)
{
  document.getElementById(idx).style.display="block";	
}
else
{
  document.getElementById(idx).style.display="none";
}
//razman add close


if(document.f1.jumlah.value >= nilaimax)
{
	//alert('lebih');
document.getElementById(idx).style.display="block";
//razman add open
document.f1.tujuanPindah[0].disabled = false;
document.f1.tujuanPindah[1].disabled = false;
document.f1.tujuanPindah[0].checked = true;
document.f1.socNegeri.value = "0";
document.f1.socDaerah.value = "0";

//razman add close

}
else
{
	//alert('kurang');
	//razman add open
	if(document.f1.sorPenentuanBidangKuasa[2].checked == true)
	{
		document.getElementById(idx).style.display="block";
		document.f1.tujuanPindah[0].checked = false;
		document.f1.tujuanPindah[0].disabled = true;
		document.f1.tujuanPindah[1].checked = true;
		document.f1.socNegeri.value = "0";
		document.f1.socDaerah.value = "0";
	}
	else
	{
		document.getElementById(idx).style.display="none";
	}
	//razman add close
}

}

function alamatP(val,loc,tab)
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.f1.command.value = "get_alamatPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
		document.f1.jenis_pej_id.value = val;
	    //document.f1.idPermohonan.value = id;
	    document.f1.submit();
	
}

function check_negeri()
{
if(document.f1.socNegeri.value == "0" || document.f1.socNegeri.value == "")
{

alert("Sila pilih negeri mahkamah terlebih dahulu")
document.f1.socNegeri.focus()

}
}


function getDaerah(val,loc,tab){
document.f1.v_loc.value=loc;
document.f1.v_tab.value=tab;

	document.f1.command.value="getMahkamah";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();	
}
function getAddress(loc,tab){
document.f1.v_loc.value=loc;
document.f1.v_tab.value=tab;

	document.f1.command.value="getMahkamahAddress";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();	
}
/*
function submitForm() {    
   // document.val.focus();
	
	window.location.hash='$val_loc';
	
	goTo('$val_tab');

	return false;
	
	
} 
*/

function submitForm() {    
//alert('$val_tab')

if('$val_loc' != "" && '$val_loc' != null)
{

   window.location.hash='$val_loc';
   
   }

if('$val_tab' != "" && '$val_tab' != null)
{

  // window.location.hash='$val_tab';
   //goTo('$val_tab');
   var nextFieldID = '$!val_tab';
   document.getElementById(nextFieldID).focus();
   }
	
} 




function edit_item(){

input_box = confirm("Adakah anda pasti?");
		if (input_box == true) {

	document.f1.method="post";
	document.f1.command.value = "paparKeputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
//	document.f1.idPermohonan.value = id;
//	document.f1.idpermohonansimati.value = sm;
//	document.f1.tarikh_mohon.value = tm;
	document.f1.submit();
	}
	else
	{return;}
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



 function trans_date1(t_d)
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


document.f1.txdTarikhHantarBorangB.value = trans;

}
else
{
return;
}

}


 function trans_date2(t_d)
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


document.f1.txdTarikhTerimaBorangC.value = trans;

}
else
{
return;
}

}


 function trans_date3(t_d)
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


document.f1.txdTarikhHantarNilaian.value = trans;

}
else
{
return;
}

}


 function trans_date4(t_d)
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


document.f1.txdTarikhTerimaNilaian.value = trans;

}
else
{
return;
}

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

function kenotis(seksyen,idStatus)
{



if (idStatus == '53' || idStatus == '151' )
{
			if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakNoData&tabId=0";
			}
				
						
	document.f1.method = "post";
	document.f1.submit();
} 
else if (idStatus == '44' || idStatus == '173' || idStatus == '175' || idStatus == '177' )
{
			if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=tukarNotis&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=tukarNotis&tabId=0";
			}
				
	document.f1.method = "post";
	document.f1.submit();
} 

else if (idStatus == '18')
{
			if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
			}
				
						
	document.f1.method = "post";
	document.f1.submit();
}
else if (idStatus == '50')
{
alert("Permohonan telah dipindahkan ke mahkamah tinggi");
}
else
{
//alert("Status permohonan tidak sah untuk notis perbicaraan");
if (seksyen == '8'){
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakWithData&tabId=0";
			}else{
				document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotisSek17&command=semakWithData&tabId=0";
			}
				
						
	document.f1.method = "post";
	document.f1.submit()
}
	

}

function cetakSuratBatalPermohonanMT(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratBatalPermohonan_MT&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakSuratBatalPermohonan_LK(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratBatalPermohonanLainKes&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}



function cetakSuratBatalPermohonan_ARB(noFail) {
 //   var url = "../servlet/ekptg.report.ppk.SuratPindahMT?nofail="+noFail;
 var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+noFail+"&report=SuratArahanAkuanARB&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function pilih_pilih()
{
if((document.f1.tempatmohonawal.value == "" || document.f1.tempatmohonawal.value == "0") && (document.f1.socNegeriWarisSurat != null && (document.f1.socNegeriWarisSurat.value == "" || document.f1.socNegeriWarisSurat.value == "0" )))
{
alert("Sila pilih negeri terlebih dahulu")
document.f1.socNegeriWarisSurat.focus() 
}
}

function setTableA_JENISB_NEG(id,val,loc,tab){
if(document.f1.sorKeputusanBorangC[1].checked == true && document.f1.socNegeriWarisSurat.value != "" )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.f1.radio_j[1].checked = true;
		document.f1.jenis_pej.value = val;
		document.f1.checkJ2.value = "checked";
		document.f1.checkJ1.value = "";
		document.f1.checkJ3.value = "";
		
		document.f1.command.value = "get_jenisPejabat_neg";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	
	document.f1.pilih_negeri_ptd.value = "yes";
	document.f1.submit();
	
}

}





function CheckBandarTetap()
{
if(document.f1.socNegeriPeguam.value == "" || document.f1.socNegeriPeguam.value == "0")
{
  alert("Sila pilih negeri terlebih dahulu.");
  document.f1.socNegeriPeguam.focus();
  return;
	  	
}


}


function setTableA_JENIS_KAV(id,val,loc,tab){


if(document.f1.sorKeputusanBorangC[1].checked == true  )
{
document.f1.v_loc.value = loc;
document.f1.v_tab.value = tab;
	
		document.getElementById(id).style.display="block";
		document.getElementById('kaveat').style.display="block";
		document.getElementById('kv1').style.display="block";
		document.getElementById('kv2').style.display="block";
		document.getElementById('bukan_kaveat').style.display="none";
		if(document.getElementById('bukan_alamatkaveat') != null)
		{
		document.getElementById('bukan_alamatkaveat').style.display="none";	
		}
	//	alert("asjsahja")

		
		document.f1.radio_j[3].checked = true;
		document.f1.jenis_pej.value = val;
		document.f1.checkJ4.value = "checked";
		document.f1.checkJ3.value = "";
		document.f1.checkJ1.value = "";
		document.f1.checkJ2.value = "";
		
		document.f1.command.value = "get_jenisPejabat";
		document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	//document.f1.idPermohonan.value = id;
	   document.f1.submit();
	
}

}

function checkTarikh(){
	
	if(document.f1.salinanArahan.checked == true)
		{
		
		document.getElementById('tarikhTerimaARB').style.display="block";
		document.getElementById('tarikhTerimaARB2').style.display="block";
		document.getElementById('tarikhTerimaARB3').style.display="block";
		document.getElementById('tarikhTerimaARB4').style.display="block";
		
		}
	else
		document.getElementById('tarikhTerimaARB').style.display="none";	
	document.getElementById('tarikhTerimaARB2').style.display="none";
	document.getElementById('tarikhTerimaARB3').style.display="none";
	document.getElementById('tarikhTerimaARB4').style.display="none";
	
}


function ForView(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8ForView&txtNoFail="+noFail;
	document.f1.submit();
}

function keNilaian_Harta(jenis_permohonan,idPermohonan,idPemohon,idSimati,id_Permohonansimati)
{
if(jenis_permohonan != 3)
{
document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar&idpermohonan="+idPermohonan+"&idPemohon="+idPemohon+"&idSimati="+idSimati+"&id_Permohonansimati="+id_Permohonansimati;	
}


if(jenis_permohonan == 3)
{
document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=FrmPrmhnnSek8InternalKutipan&command=papar&idpermohonan="+idPermohonan+"&idPemohon="+idPemohon+"&idSimati="+idSimati+"&id_Permohonansimati="+id_Permohonansimati;	
}

document.f1.method="POST";
document.f1.submit();
}

function buttonkeHartaAlih(salinanArahan)
{
	
		var dvPassport = document.getElementById("divbuttonkeHartaAlih");
        dvPassport.style.display = salinanArahan.checked ? "block" : "none";

        
}

function jumptoHartaAlih(jenis_permohonan,idPermohonan,idPemohon,idSimati,id_Permohonansimati,negeriARB)
{
	document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&idpermohonan="+idPermohonan+"&idPemohon="+idPemohon+"&idSimati="+idSimati+"&id_Permohonansimati="+id_Permohonansimati+"&source=KeputusanPermohonan&negeriARB="+negeriARB+"&jenisHA=98";
	document.f1.command.value="harta_alih";
	document.f1.mode.value="view_harta_alih";
	document.f1.method="POST";
	document.f1.submit();
	/*
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnSek8Internal#maklumat_pemohon";
	var hWnd = window.open(url,'Cetak','width=625,height=400, resizable=no,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	*/
}
	
function returnFromMT(){
	document.f1.method="post";
	document.f1.command.value = "paparKeputusan";
	document.f1.action="?_portal_module=FrmSenaraiFailKeputusanPermohonanInternal";
	document.f1.submit();
}
</script>