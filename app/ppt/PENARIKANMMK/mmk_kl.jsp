
#set($id_mmk = "")
#set($STATUS_SEMAKAN = "")


#set($txtTujuan = "Kertas Mesyuarat ini bertujuan untuk mendapatkan pertimbangan dan keputusan Jawatankuasa Kerta Tanah Wilayah Persekutuan Kuala Lumpur (JKKTWPKL) berhubung dengan permohonan penarikan balik pengambilan tanah di bawah Seksyen 35 Akta Pengambilan Tanah 1960 bagi projek tersebut.")

#set($txtTujuan1 = "Tujuan  kertas ringkasan ini ialah untuk mendapat kelulusan Y.A.B Dato Menteri Besar bagi menarik balik pengambilan tanah dibawah Seksyen 35 Akta Pengambilan Tanah 1960 bagi lot "+$no_lot_mmk+" di "+$nama_mukim_mukim+", Daerah "+ $nama_daerah1 +" seluas "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar) bagi "+ $tujuan +" di "+ $nama_daerah1 +", Selangor Darul Ehsan yang sebelum ini telah diwartakan di bawah Seksyen 8 Akta yang sama dan telah diisytiharkan melalui Warta Kerajaan Negeri Selangor "+ $no_warta +"  bertarikh "+ $tarikh_warta +". ")


#set($txtPerihalTanah = "3.1   Butir-butir tanah <br/><br/>
Butir-butir tanah yang terlibat adalah...... 
<br/><br/>
3.2   Laporan Tanah
<br/><br/>
Tanah-tanah terlibat terletak ........"
)

#set($txtSebab = "")
#set($txtImplikasi = "")
#set($txtUlasan = "")
#set($txtSyor = "Pentadbir Tanah "+ $nama_daerah1 +"  mengesyorkan:- <br/><br/>
3.1	Pengambilan Tanah bagi lot "+$no_lot_mmk+" di Mukim "+$nama_mukim_mukim+", Daerah "+ $nama_daerah1 +" seluas "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar)  yang telah dibuat perisytiharan di bawah Seksyen 8 Akta Pengambilan Tanah 1960 melalui Warta Kerajaan Negeri Selangor "+ $no_warta +"  bertarikh "+ $tarikh_warta +" ditarik balik di bawah Seksyen 35 Akta yang sama.
<br/><br/>
3.2	Penarikan balik pengambilan tanah ini hendaklah disiarkan dalam Warta Kerajaan Negeri Selangor menurut seksyen 35(1A) Akta Pengambilan Tanah.
")


#set($txtSyor1 = "Pengambilan Tanah bagi lot "+$no_lot_mmk+" di "+$nama_mukim_mukim+", Daerah "+ $nama_daerah1 +" seluas "+$maklumat_hakmilik_mmk_hektar+" hektar ("+$maklumat_hakmilik_mmk_ekar+" ekar)  yang telah dibuat perisytiharan di bawah Seksyen 8 Akta Pengambilan Tanah 1960 melalui Warta Kerajaan Negeri Selangor "+ $no_warta +"  bertarikh "+ $tarikh_warta +" ditarik balik di bawah Seksyen 35 Akta yang sama.")

#set($txtSyor2 = "Penarikan balik pengambilan tanah ini hendaklah disiarkan dalam Warta Kerajaan Negeri Selangor menurut seksyen 35(1A) Akta Pengambilan Tanah.")



#set($txtAsasPertimbangan = "")
#set($txtNilaiAtasTanah = "Adalah disyorkan supaya nilaian ke atas tanah yang akan diambil ini 
dirujuk kepada penilaian kompeten.")
#set($txtSebabPenarikan= "Sebab-sebab penarikan balik adalah "+$!maklumat_penarikanmmk)
#set($txtLatarbelakang= "")
#set($txtKesimpulan= "")
#set($txtUlasanPengarah= "")
#set($txtTajuk= "KERTAS RINGKASAN DARIPADA PENTADBIR TANAH "+$nama_daerah+" NEGERI SELANGOR DARUL EHSAN.")

#set($txtKeputusan= "")
#set($txtPerihalPohon= "")
#set($txtNamaTuan= "")
#set($txtPerakuanPentadbir= "")
#set($txtPeruntukan= "Wang peruntukan bagi pembiayaan pengambilan tanah ini telah disediakan secukupnya oleh pihak "+$nama_kementerian1+".")
#set($txtLokasi= "")


<!-- id_pembatalan = id_penarikanbalik -->



#foreach($view in $maklumat_penyediaan)
#set($id_mmk = $view.ID_MMK)
#set($id_pembatalan = $view.ID_PEMBATALAN)
#set($STATUS_SEMAKAN = $view.STATUS_SEMAKAN)
#set($txtTujuan = $view.TUJUAN)
#set($txtLatarbelakang = $view.LATARBELAKANG)
#set($txtImplikasi = $view.IMPLIKASI)
#set($txtUlasan = $view.ULASAN)
#set($txtSyor = $view.SYOR)
#set($txtAsasPertimbangan = $view.ASAS_PERTIMBANGAN)
#set($txtNilaiAtasTanah = $view.NILAI_ATAS_TANAH)
#set($txtSebabPenarikan= $view.SEBAB_PENARIKAN)
#set($txtPerihalTanah= $view.PERIHAL_TANAH)
#set($txtKesimpulan= $view.KESIMPULAN)
#set($txtUlasanPengarah= $view.ULASAN_PENGARAH)
#set($txtTajuk= $view.TAJUK)
#set($flag_mmk= $view.FLAG_MMK)

#set($txtKeputusan= $view.KEPUTUSAN)
#set($txtPerihalPohon= $view.PERIHAL_POHON)
#set($txtNamaTuan= $view.NAMA_TUAN_TANAH)
#set($txtPerakuanPentadbir= $view.PERAKUAN_PENTADBIR_TNH)

#set($txtPeruntukan= $view.PENGESAHAN_PERUNTUKAN)

#set($txtLokasi= $view.BUTIR_TANAH)



#end


#set($nama_pengarah= "HJ. CHE ROSLAN B. CHE DAUD")
#set($nama_menteri= "Y.A.B. TAN SRI DATO` SERI ABD KHALID BIN IBRAHIM")



<table width="100%">

<tr   style="display:none">
              <td width="4%"  valign="top">&nbsp;</td>
              <td width="20%" valign="top"> Tajuk MMK</td>
              <td width="1%" valign="top">:</td>
     
        <td width="75%" valign="top">    </td>
         </tr>
</table>







<table width="100%" >
<tr  >
              <td width="4%"  valign="top"><strong>1.</strong></td>
              <td colspan="2" valign="top"> <strong>BUTIR-BUTIR ASAS</strong></td>
              <td width="1%" valign="top">&nbsp;</td>
    
        <td width="75%" colspan="2" valign="top">&nbsp;</td>
  </tr>
  
  
  
  <tr  >
              <td width="4%"  valign="top">&nbsp;</td>
              <td width="4%" valign="top">1.1</td>
              
              <td width="16%" valign="top">Pemohon</td>
              <td width="1%" valign="top">:</td>
    
        <td width="75%" colspan="2" valign="top">$!nama_kementerian</td>
  </tr>
  
  
  <tr  >
              <td width="4%"  valign="top">&nbsp;</td>
              <td width="4%" valign="top">1.2</td>
              
              <td width="16%" valign="top">Tarikh Permohonan</td>
              <td width="1%" valign="top">:</td>
    
        <td width="75%" colspan="2" valign="top">$!tarikh_warta</td>
  </tr>
  
  <tr  >
              <td width="4%"  valign="top">&nbsp;</td>
              <td width="4%" valign="top">1.3</td>
              
              <td width="16%" valign="top">Lot-Lot Yang Terlibat</td>
              <td width="1%" valign="top">:</td>
    
        <td width="75%" colspan="2" valign="top">
        Seperti disenaraikan dalam Jadual
       Penarikan Balik Pengambilan Tanah di kandungan</td>
  </tr>
  
  <tr  >
              <td width="4%"  valign="top">&nbsp;</td>
              <td width="4%" valign="top">1.4</td>
              
              <td width="16%" valign="top">Bilangan Lot</td>
              <td width="1%" valign="top">:</td>
    
    <td width="75%" colspan="2" valign="top">
       
       
        #if($!maklumat_hakmilik_mmk_lot.size()>0 &&  $!maklumat_hakmilik_mmk_lot.size() < 10)
        
        #end
        
        
    ( $!maklumat_hakmilik_mmk_lot.size() ) Lot
    
    </td>
  </tr>
  
  <tr  >
              <td width="4%"  valign="top">&nbsp;</td>
              <td width="4%" valign="top">1.5</td>
              
              <td width="16%" valign="top">Luas Lot Yang Perlu Ditarik Balik</td>
              <td width="1%" valign="top">:</td>
    
    <td width="75%" colspan="2" valign="top">$maklumat_hakmilik_mmk_hektar hektar ($maklumat_hakmilik_mmk_ekar  ekar)</td>
  </tr>
  
  <tr  >
              <td width="4%"  valign="top">&nbsp;</td>
              <td width="4%" valign="top">1.6</td>
              
              <td width="16%" valign="top">Projek</td>
              <td width="1%" valign="top">:</td>
    
        <td width="75%" colspan="2" valign="top">$!tujuan</td>
  </tr>
  
  <tr  >
              <td width="4%"  valign="top">&nbsp;</td>
              <td width="4%" valign="top">1.7</td>
              
              <td width="16%" valign="top">Lokasi</td>
              <td width="1%" valign="top">:</td>
    
        <td width="4%" valign="top">
    </td>
        <td width="71%" valign="top">
        
        
          <textarea name="txtLokasi" id="txtLokasi" cols="80"   rows="9"        
         onBlur="check_length(this,'30000','txtLokasi_check','txtLokasi_num','normal','no','ulasan');"  
         onKeyup="check_length(this,'30000','txtLokasi_check','txtLokasi_num','normal','no','ulasan');" 
         onKeydown="check_length(this,'30000','txtLokasi_check','txtLokasi_num','normal','no','ulasan');"                    
          $readonlymode class = "$disabledmode" 
        >$txtLokasi</textarea>       
       #if($readmode == "edit")           
       <div><span id="txtLokasi_num" style="color:blue;" ></span><span> Baki Aksara</span>       </div>
         #else
         <input name="txtLokasi_num" id="txtLokasi_num" size="3" value="30000"  style=" display:none" > 
         #end
  <div id="txtLokasi_check" class="alert_msg" ></div>  
        </td>
  </tr>
  
  <tr  >
              <td  colspan="6">&nbsp;</td>
  </tr>
  
            <tr  >
              <td width="4%"  valign="top"><strong>2.</strong></td>
              <td colspan="2" valign="top"> <strong>TUJUAN</strong></td>
              <td width="1%" valign="top">:</td>
    
        <td width="75%" colspan="2" valign="top">
       
    #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "TUJUAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="TUJUAN_MAIN_temp1"  id="TUJUAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end             
   
            
   <span id="TUJUAN_MAIN"></span>           
   <div id="TUJUAN_MAIN_temp"></div>   </td>
  </tr>
             
             <tr>
              <td valign="top">&nbsp;</td>
              <td colspan="2" valign="top"></td>
              <td valign="top"></td>
               
        <td width="75%" colspan="2"></td>
  </tr>
         
         <tr>
              <td valign="top"><strong>3.</strong></td>
              <td colspan="2" valign="top"><strong>LATAR BELAKANG</strong></td>
              <td valign="top">:</td>
    
               
        <td width="75%" colspan="2">
    #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "LATARBELAKANG" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="LATARBELAKANG_MAIN_temp1"  id="LATARBELAKANG_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="LATARBELAKANG_MAIN"></span>           
   <div id="LATARBELAKANG_MAIN_temp"></div>        </td>
  </tr>
            
               <tr>
              <td valign="top">&nbsp;</td>
              <td colspan="2" valign="top"></td>
              <td valign="top"></td>
               
        <td width="75%" colspan="2"></td>
  </tr>
            
   
           
            
            <tr>
            <td valign="top"><strong>4.</strong></td>
              <td colspan="2" valign="top"><strong>ULASAN PENTADBIR TANAH WILAYAH PERSEKUTUAN KUALA LUMPUR</strong></td>
              <td valign="top">:</td>
              
      
        <td width="75%" colspan="2" valign="top">
        <table width="100%">
          <tr>
            <td width="5%" valign="top">4.1</td>
            <td width="70%"><div align="justify">Penarikan balik tanah-tanah ini adalah selaras dengan peruntukan di bawah Seksyen 35(1) Akta Pengambilan Tanah 1960. Walau bagaimanapun pembayaran yang terlibat akibat dari prosiding pengambilan tanah hendaklah dibuat sebagaimana yang telah ditetapkan di bawah Seksyen 35(2) Akta Pengambilan Tanah 1960 yang berbunyi berikut :-</div></td>
            <td width="25%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>
            
            <table width="100%">
  <tr>
    <td width="14%">&nbsp;</td>
    <td colspan="2"><em>&quot; 35 (2) Whenever the State Authority withdraws from any acquisition under subsection (1), the Land Administrator shall -</em></td>
   
  </tr>
   <tr>
    <td width="14%">&nbsp;</td>
    <td width="10%" valign="top"><div align="center"><em>a)</em></div></td>
    <td width="76%"><em>Determine the amount of compensation due for the damage, if any, done to such land by action taken under Section 5 and not already paid for under Section 6, and pay such amount to the person injured; and</em></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top"><div align="center"><em>b)</em></div></td>
    <td><em>Pay to the person interested all such cost as shall have been incurred by them by reason or in consequence of the proceedings for acquisition, together with compensation for the damage, id any, which they may have sustained by reason or in consequence of such proceedings.&quot;</em></td>
  </tr>
  
</table>

            
            </td>
            <td>&nbsp;</td>
          </tr>
        </table>           </td>
         </tr>
         
         
           <tr>
            <td valign="top"></td>
              <td colspan="2" valign="top"></td>
              <td valign="top">:</td>
              
      
        <td width="75%" colspan="2" valign="top">
        
         #if($senarai_submmk.size() > 0)
    #foreach($list in $senarai_submmk)   
    #if($list.FLAG_JENIS_MMK == "ULASAN_PENTADBIR" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    <input type="hidden" name="ULASAN_PENTADBIR_temp1"  id="ULASAN_PENTADBIR_temp1" value="$list.KETERANGAN_SUBMMK" >  
    #end
    #end
    #end    
             
   
            
   <span id="ULASAN_PENTADBIR"></span>           
   <div id="ULASAN_PENTADBIR_temp"></div>        </td>
         </tr>
         
         
</table>
          
          
         		
		
		
		
		
	
		
<input name="txtImplikasi" id="txtImplikasi" value="" type="hidden" />
          <input name="txtAsasPertimbangan" id="txtAsasPertimbangan" value="" type="hidden" />          
          <input name="txtKesimpulan" id="txtKesimpulan" value="" type="hidden" />        
          <input name="txtUlasanPengarah" id="txtUlasanPengarah" value="" type="hidden" />
          <input name="txtKeputusan" id="txtKeputusan" value="" type="hidden" />
          <input name="txtPerihalPohon" id="txtPerihalPohon" value="" type="hidden" />
          <input name="txtNamaTuan" id="txtNamaTuan" value="" type="hidden" />
          <input name="txtPerakuanPentadbir" id="txtPerakuanPentadbir" value="" type="hidden" />
  
          
<script>

window.onload = submitForm,textarea_TUJUAN_MAIN('tambahtolak','umum',''),textarea_LATARBELAKANG_MAIN('tambahtolak','umum',''),textarea_ULASAN_PENTADBIR('tambahtolak','umum',''),check_length(document.${formName}.txtLokasi,'30000','txtLokasi_check','txtLokasi_num','normal','no','ulasan');

/*
function convert_ayat(num1)
{
var ayat = ""
var num = parseInt(num1);

alert(num1.length);

if(num1.length == 1)
{

if(num == 1)
{
ayat = "Satu"
}
else if(num == 2)
{
ayat = "Dua"
}
else if(num == 3)
{
else ayat = "Tiga"
}
else if(num == 4)
{
ayat = "Empat"
}
else if(num == 5)
{
ayat = "Lima"
}
else if(num == 6)
{
ayat = "Enam"
}
else if(num == 7)
{
ayat = "Tujuh"
}
else if(num == 8)
{
ayat = "Lapan"
}
else if(num == 9)
{
ayat = "Sembilan"
}
else if(num == 11)
{
ayat = "Sembilan"
}

}





document.${formName}.ayat.value = ayat;
$jquery("#ayat1").html(""+ayat);
}
*/



function submitForm() 
{



if('$location' != "" && '$location' != null && '$point' != "" && '$point' != null)
{
window.location.hash='$location';
goTo('$point');
}
else
{
window.location.hash='paging';
goTo('paging');
}



if('$flag_mmk' == "1" && ('$portal_role' == "(PPT)Pengarah" || '$portal_role' == "(PPT)PengarahUnit" || '$portal_role' == "(PPT)PenolongPengarah" || '$portal_role' == "(PPT)PenolongPengarahUnit" || '$portal_role' == "(PPT)KetuaPenolongPengarah" || '$portal_role' == "(PPT)KetuaPenolongPengarahUnit"))
{
//document.${formName}.semakan_mmk.style.display = "block";
document.getElementById('semakan_mmk').style.display = "block";
}
else if('$flag_mmk' == "2" && ('$portal_role' == "(PPT)Pengarah" || '$portal_role' == "(PPT)PengarahUnit" || '$portal_role' == "(PPT)PenolongPengarah" || '$portal_role' == "(PPT)PenolongPengarahUnit" || '$portal_role' == "(PPT)KetuaPenolongPengarah" || '$portal_role' == "(PPT)KetuaPenolongPengarahUnit"))
{
document.getElementById('semakan_mmk').style.display = "block";
document.getElementById('keputusan_mmk').style.display = "block";
//document.${formName}.semakan_mmk.style.display = "block";
//document.${formName}.keputusan_mmk.style.display = "block";
}
else if('$flag_mmk' == "2" && ('$portal_role' != "(PPT)Pengarah" && '$portal_role' != "(PPT)PengarahUnit" && '$portal_role' != "(PPT)PenolongPengarah" && '$portal_role' != "(PPT)PenolongPengarahUnit" && '$portal_role' != "(PPT)KetuaPenolongPengarah" && '$portal_role' != "(PPT)KetuaPenolongPengarahUnit"))
{
document.getElementById('keputusan_mmk').style.display = "block";
//document.${formName}.keputusan_mmk.style.display = "block";
}
else
{
document.getElementById('semakan_mmk').style.display = "none";
document.getElementById('keputusan_mmk').style.display = "none";
}





}



function textarea_TUJUAN_MAIN(tambahtolak,jenis,index_tolak)
{

var TUJUAN_MAIN_temp1_length=0;

if(document.${formName}.TUJUAN_MAIN_temp1 != null)
{

if(document.${formName}.TUJUAN_MAIN_temp1.length>0)
{
TUJUAN_MAIN_temp1_length = document.${formName}.TUJUAN_MAIN_temp1.length;
}
else
{
TUJUAN_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanTUJUAN_MAIN!=null)
{

if(document.${formName}.txtUlasanTUJUAN_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanTUJUAN_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='TUJUAN_MAIN_tempX1' name='TUJUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUJUAN_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='TUJUAN_MAIN_tempX1' name='TUJUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUJUAN_MAIN.value+"'></td></tr>";

}
}




$jquery("#TUJUAN_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(TUJUAN_MAIN_temp1_length>0)
{
ttTUJUAN_MAIN = TUJUAN_MAIN_temp1_length;
}
else
{
ttTUJUAN_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttTUJUAN_MAIN = ttTUJUAN_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttTUJUAN_MAIN = ttTUJUAN_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttTUJUAN_MAIN; i++)
  {	
  if(ttTUJUAN_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[1].value;
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[0].value;
	
    } 	
	}		
   
    if(jenis == "umum")
    {
	temp_value = '$txtTujuan';
	}	
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>2.1  </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanTUJUAN_MAIN_num\" id=\"txtUlasanTUJUAN_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
		 
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat TUJUAN_MAIN'> "+
	      " </span>"; 	
	     if(ttTUJUAN_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_TUJUAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_TUJUAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat TUJUAN_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttTUJUAN_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1.value;
	
	}	
	else if(ttTUJUAN_MAIN>2 && i!=(ttTUJUAN_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i].value;
	
	}	
	else if(ttTUJUAN_MAIN>1 && i!=(ttTUJUAN_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[parseInt(index_tolak)+1].value;
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i].value;
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i+1].value;	
	
   }	

	}
	else if(ttTUJUAN_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.TUJUAN_MAIN_tempX1[0].value;
	
   }
   }		
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 2."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat TUJUAN_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanTUJUAN_MAIN_num"+i+"\" id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttTUJUAN_MAIN>1 && ttTUJUAN_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat TUJUAN_MAIN'> "+
	" </span>"; 
	}
	if(ttTUJUAN_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_TUJUAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_TUJUAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat TUJUAN_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	
	$jquery("#TUJUAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length > 1 )
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanTUJUAN_MAIN[t].value = document.${formName}.TUJUAN_MAIN_temp1[t].value;
	
    }
	}
	else if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length < 1 )
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanTUJUAN_MAIN.value = document.${formName}.TUJUAN_MAIN_temp1[0].value;
	}
	}
	else if(TUJUAN_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanTUJUAN_MAIN.value = document.${formName}.TUJUAN_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(TUJUAN_MAIN_temp1_length > 1)
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.TUJUAN_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.TUJUAN_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(TUJUAN_MAIN_temp1_length == 1)
	{	
	 document.${formName}.TUJUAN_MAIN_temp1.value = "";			
	 var element = document.${formName}.TUJUAN_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttTUJUAN_MAIN; i++)
    {		
    if(ttTUJUAN_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanTUJUAN_MAIN,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanTUJUAN_MAIN[i],'30000','txtUlasanTUJUAN_MAIN_check'+i,'txtUlasanTUJUAN_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}



function textarea_LATARBELAKANG_MAIN(tambahtolak,jenis,index_tolak)
{

var LATARBELAKANG_MAIN_temp1_length=0;

if(document.${formName}.LATARBELAKANG_MAIN_temp1 != null)
{

if(document.${formName}.LATARBELAKANG_MAIN_temp1.length>0)
{
LATARBELAKANG_MAIN_temp1_length = document.${formName}.LATARBELAKANG_MAIN_temp1.length;
}
else
{
LATARBELAKANG_MAIN_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanLATARBELAKANG_MAIN!=null)
{

if(document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanLATARBELAKANG_MAIN.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG_MAIN_tempX1' name='LATARBELAKANG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG_MAIN[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG_MAIN_tempX1' name='LATARBELAKANG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG_MAIN.value+"'></td></tr>";

}
}




$jquery("#LATARBELAKANG_MAIN_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(LATARBELAKANG_MAIN_temp1_length>0)
{
ttLATARBELAKANG_MAIN = LATARBELAKANG_MAIN_temp1_length;
}
else
{
ttLATARBELAKANG_MAIN = 1;
}
}
if(jenis == "tambah")
{
ttLATARBELAKANG_MAIN = ttLATARBELAKANG_MAIN + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttLATARBELAKANG_MAIN = ttLATARBELAKANG_MAIN + parseInt(tambahtolak);
}




  for (i = 0; i < ttLATARBELAKANG_MAIN; i++)
  {	
  if(ttLATARBELAKANG_MAIN==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[0].value
	
    } 	
	}		
   
     if(jenis == "umum")
    {
	temp_value = '$txtLatarbelakang';
	}	
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>3.1 </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLATARBELAKANG_MAIN_num\" id=\"txtUlasanLATARBELAKANG_MAIN_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat LATARBELAKANG_MAIN'> "+
	      " </span>"; 	
	     if(ttLATARBELAKANG_MAIN>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat LATARBELAKANG_MAIN' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttLATARBELAKANG_MAIN==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1.value
	
	}	
	else if(ttLATARBELAKANG_MAIN>2 && i!=(ttLATARBELAKANG_MAIN-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i].value
	
	}	
	else if(ttLATARBELAKANG_MAIN>1 && i!=(ttLATARBELAKANG_MAIN+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i+1].value	
	
   }	

	}
	else if(ttLATARBELAKANG_MAIN==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[0].value;
	
   }
   }	
   
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 3."+(i+1)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','ulasan kos-kos akibat LATARBELAKANG_MAIN');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttLATARBELAKANG_MAIN>1 && ttLATARBELAKANG_MAIN==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat LATARBELAKANG_MAIN'> "+
	" </span>"; 
	}
	if(ttLATARBELAKANG_MAIN>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat LATARBELAKANG_MAIN' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#LATARBELAKANG_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1 )
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanLATARBELAKANG_MAIN[t].value = document.${formName}.LATARBELAKANG_MAIN_temp1[t].value;
	
    }
	}
	else if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length < 1 )
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	
    document.${formName}.txtUlasanLATARBELAKANG_MAIN.value = document.${formName}.LATARBELAKANG_MAIN_temp1[0].value;
	}
	}
	else if(LATARBELAKANG_MAIN_temp1_length == 1)
	{
	document.${formName}.txtUlasanLATARBELAKANG_MAIN.value = document.${formName}.LATARBELAKANG_MAIN_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(LATARBELAKANG_MAIN_temp1_length > 1)
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.LATARBELAKANG_MAIN_temp1[index_tolak].value = "";	
	var element = document.${formName}.LATARBELAKANG_MAIN_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(LATARBELAKANG_MAIN_temp1_length == 1)
	{	
	 document.${formName}.LATARBELAKANG_MAIN_temp1.value = "";			
	 var element = document.${formName}.LATARBELAKANG_MAIN_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttLATARBELAKANG_MAIN; i++)
    {		
    if(ttLATARBELAKANG_MAIN==1)
    {	
	check_length(document.${formName}.txtUlasanLATARBELAKANG_MAIN,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanLATARBELAKANG_MAIN[i],'30000','txtUlasanLATARBELAKANG_MAIN_check'+i,'txtUlasanLATARBELAKANG_MAIN_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}




function textarea_ULASAN_PENTADBIR(tambahtolak,jenis,index_tolak)
{

var ULASAN_PENTADBIR_temp1_length=0;

if(document.${formName}.ULASAN_PENTADBIR_temp1 != null)
{

if(document.${formName}.ULASAN_PENTADBIR_temp1.length>0)
{
ULASAN_PENTADBIR_temp1_length = document.${formName}.ULASAN_PENTADBIR_temp1.length;
}
else
{
ULASAN_PENTADBIR_temp1_length = 1;
}
}

var code_temp1 = "";
if(document.${formName}.txtUlasanULASAN_PENTADBIR!=null)
{

if(document.${formName}.txtUlasanULASAN_PENTADBIR.length > 1)
{
 for (i = 0; i < document.${formName}.txtUlasanULASAN_PENTADBIR.length; i++)
 {
 code_temp1 += "<tr><td><input type='hidden' id='ULASAN_PENTADBIR_tempX1' name='ULASAN_PENTADBIR_tempX1' value= '"+document.${formName}.txtUlasanULASAN_PENTADBIR[i].value+"'></td></tr>";
 
 } 
}
else
{
code_temp1 += "<tr><td><input type='hidden' id='ULASAN_PENTADBIR_tempX1' name='ULASAN_PENTADBIR_tempX1' value= '"+document.${formName}.txtUlasanULASAN_PENTADBIR.value+"'></td></tr>";

}
}




$jquery("#ULASAN_PENTADBIR_temp").html(""+code_temp1); 
var codes1 = "";

if(jenis == "umum")
{
if(ULASAN_PENTADBIR_temp1_length>0)
{
ttULASAN_PENTADBIR = ULASAN_PENTADBIR_temp1_length;
}
else
{
ttULASAN_PENTADBIR = 1;
}
}
if(jenis == "tambah")
{
ttULASAN_PENTADBIR = ttULASAN_PENTADBIR + parseInt(tambahtolak);
}
if(jenis == "tolak")
{
ttULASAN_PENTADBIR = ttULASAN_PENTADBIR + parseInt(tambahtolak);
}




  for (i = 0; i < ttULASAN_PENTADBIR; i++)
  {	
  if(ttULASAN_PENTADBIR==1)
  {
  
  
    var temp_value = "";
    var temp_amaunt = "";
	
	if(i==0 && jenis == "tolak")
	{	
	if(parseInt(index_tolak)==0)
    {
	temp_value = document.${formName}.ULASAN_PENTADBIR_tempX1[1].value
	
    }
    if(parseInt(index_tolak)==1)
    {
	temp_value = document.${formName}.ULASAN_PENTADBIR_tempX1[0].value
	
    } 	
	}		
   
     if(jenis == "umum")
    {
	temp_value = '$txtLatarbelakang';
	}	
   
   
	codes1 += "<table width='100%'><tr> <td width='5%' valign='top'>4.2 </td>"+
	"<td  > "+
	     " <textarea name=\"txtUlasanULASAN_PENTADBIR\" id=\"txtUlasanULASAN_PENTADBIR\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanULASAN_PENTADBIR_check','txtUlasanULASAN_PENTADBIR_num','normal','no','ulasan kos-kos akibat ULASAN_PENTADBIR');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanULASAN_PENTADBIR_check','txtUlasanULASAN_PENTADBIR_num','normal','no','ulasan kos-kos akibat ULASAN_PENTADBIR');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanULASAN_PENTADBIR_check','txtUlasanULASAN_PENTADBIR_num','normal','no','ulasan kos-kos akibat ULASAN_PENTADBIR');textarea_kerosakan1()\" "+        
         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	 		
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanULASAN_PENTADBIR_num\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanULASAN_PENTADBIR_num\" id=\"txtUlasanULASAN_PENTADBIR_num\" size=\"3\" value=\"400\"  style=\"display:none\" ></span> "+
         "#end"+
         "<div id=\"txtUlasanULASAN_PENTADBIR_check\" class=\"alert_msg\" ></div> "+
	" </td>"+
		
	"</tr><tr> <td width='5%' valign='top'>  </td>"+	
	"<td align='left' valign='top' > ";	 	
		 codes1 +=  "#if($readmode == 'edit' ) ";
	     codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_ULASAN_PENTADBIR(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_ULASAN_PENTADBIR(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat ULASAN_PENTADBIR'> "+
	      " </span>"; 	
	     if(ttULASAN_PENTADBIR>1) {      
	     codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_ULASAN_PENTADBIR(-1,'tolak','');textarea_kerosakan1()\" onClick=\"textarea_ULASAN_PENTADBIR(-1,'tolak','');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat ULASAN_PENTADBIR' > "+
	"</span> ";
	}
	codes1 +=" #end";		 
	codes1 +=" </td>"+	
	"</tr></table>";
	
	 
	}
	else
	{	
	var temp_value = "";
	var temp_amaunt = "";	
	if(ttULASAN_PENTADBIR==2 && i==0 && jenis == "tambah")
	{	
	temp_value = document.${formName}.ULASAN_PENTADBIR_tempX1.value
	
	}	
	else if(ttULASAN_PENTADBIR>2 && i!=(ttULASAN_PENTADBIR-1) && jenis == "tambah")
	{	
	temp_value = document.${formName}.ULASAN_PENTADBIR_tempX1[i].value
	
	}	
	else if(ttULASAN_PENTADBIR>1 && i!=(ttULASAN_PENTADBIR+1) && jenis == "tolak")
	{	
   if(i==parseInt(index_tolak))
   {
	temp_value = document.${formName}.ULASAN_PENTADBIR_tempX1[parseInt(index_tolak)+1].value
	
   }
   if(i<parseInt(index_tolak))
   {
    temp_value = document.${formName}.ULASAN_PENTADBIR_tempX1[i].value
	
   }
   if(i>parseInt(index_tolak))
   {
    temp_value = document.${formName}.ULASAN_PENTADBIR_tempX1[i+1].value	
	
   }	

	}
	else if(ttULASAN_PENTADBIR==1 && i==1 && jenis == "tolak")
	{	
   if(parseInt(index_tolak)==0)
   {
	temp_value = document.${formName}.ULASAN_PENTADBIR_tempX1[1].value;

	
   }
   if(parseInt(index_tolak)==1)
   {
	temp_value = document.${formName}.ULASAN_PENTADBIR_tempX1[0].value;
	
   }
   }	
   
   
 
	
	codes1 += "<table width='100%'  ><tr> <td width='5%' valign='top'> 4."+(i+2)+"  </td>"+
	"<td >"+
	     "<textarea name=\"txtUlasanULASAN_PENTADBIR\" id=\"txtUlasanULASAN_PENTADBIR\" cols=\"80\"   rows=\"9\""+          
         "onBlur=\"check_length(this,'30000','txtUlasanULASAN_PENTADBIR_check"+i+"','txtUlasanULASAN_PENTADBIR_num"+i+"','normal','no','ulasan kos-kos akibat ULASAN_PENTADBIR');textarea_kerosakan1()\" "+  
         "onKeyup=\"check_length(this,30000,'txtUlasanULASAN_PENTADBIR_check"+i+"','txtUlasanULASAN_PENTADBIR_num"+i+"','normal','no','ulasan kos-kos akibat ULASAN_PENTADBIR');textarea_kerosakan1()\" "+
         "onKeydown=\"check_length(this,'30000','txtUlasanULASAN_PENTADBIR_check"+i+"','txtUlasanULASAN_PENTADBIR_num"+i+"','normal','no','ulasan kos-kos akibat ULASAN_PENTADBIR');textarea_kerosakan1()\" "+         " $readonlymode class = \"$disabledmode\" >"+temp_value+"</textarea> "+	
		 "#if($readmode == 'edit' ) "+           
         "<div ><span id=\"txtUlasanULASAN_PENTADBIR_num"+i+"\" style=\"color:blue;\" ></span><span> Baki Aksara</span> "+       
         "</div>"+
         "#else"+
         "<span name=\"txtUlasanULASAN_PENTADBIR_num"+i+"\" id=\"txtUlasanULASAN_PENTADBIR_num"+i+"\" size=\"3\" value=\"400\"  style=\"display:none\"  ></span> "+
         "#end"+
         "<div id=\"txtUlasanULASAN_PENTADBIR_check"+i+"\" class=\"alert_msg\" ></div> "+		  
	"</td>"+
	"</tr><tr>"+
		
	"<td width='5%' valign='top'>  </td><td align='left' valign='top' > ";	     	
    codes1 +=  "#if($readmode == 'edit' ) ";		 
    if(ttULASAN_PENTADBIR>1 && ttULASAN_PENTADBIR==(i+1)) {  	
	codes1 += " <span ><input type='button' name='cmdBatalNN' id='cmdBatalNN' value='+' onkeypress=\"textarea_ULASAN_PENTADBIR(1,'tambah','');textarea_kerosakan1()\"  onClick=\"textarea_ULASAN_PENTADBIR(1,'tambah','');textarea_kerosakan1()\" title='Menambah maklumat pampasan kos-kos akibat ULASAN_PENTADBIR'> "+
	" </span>"; 
	}
	if(ttULASAN_PENTADBIR>1) {      
	codes1 += "<span >"+
	"<input type='button' name='cmdBatalKK' id='cmdBatalKK' value='-' onkeypress=\"textarea_ULASAN_PENTADBIR(-1,'tolak','"+i+"');textarea_kerosakan1()\" onClick=\"textarea_ULASAN_PENTADBIR(-1,'tolak','"+i+"');textarea_kerosakan1()\" title='Mengurangkan maklumat pampasan kos-kos akibat ULASAN_PENTADBIR' > "+
	"</span> ";
	}
	codes1 +="#end";		 
	codes1 +=" </td>"+	
	"</tr>"+
	"<tr height='5'><td ></td></tr>"+	
	"</table>";
	}	
	}
	
	$jquery("#ULASAN_PENTADBIR").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" )
	{
	if(ULASAN_PENTADBIR_temp1_length > 1 && document.${formName}.txtUlasanULASAN_PENTADBIR.length > 1 )
	{
	for (t = 0; t < ULASAN_PENTADBIR_temp1_length; t++)
    {	
    document.${formName}.txtUlasanULASAN_PENTADBIR[t].value = document.${formName}.ULASAN_PENTADBIR_temp1[t].value;
	
    }
	}
	else if(ULASAN_PENTADBIR_temp1_length > 1 && document.${formName}.txtUlasanULASAN_PENTADBIR.length < 1 )
	{
	for (t = 0; t < ULASAN_PENTADBIR_temp1_length; t++)
    {	
    document.${formName}.txtUlasanULASAN_PENTADBIR.value = document.${formName}.ULASAN_PENTADBIR_temp1[0].value;
	}
	}
	else if(ULASAN_PENTADBIR_temp1_length == 1)
	{
	document.${formName}.txtUlasanULASAN_PENTADBIR.value = document.${formName}.ULASAN_PENTADBIR_temp1.value;
	}
	}
	
	
	if(jenis == "tolak")
	{	
	if(ULASAN_PENTADBIR_temp1_length > 1)
	{
	for (t = 0; t < ULASAN_PENTADBIR_temp1_length; t++)
    {	 
	if(index_tolak==t)
	{  
	document.${formName}.ULASAN_PENTADBIR_temp1[index_tolak].value = "";	
	var element = document.${formName}.ULASAN_PENTADBIR_temp1[index_tolak];
    element.parentNode.removeChild(element);	
	}
    }	
	}
	else if(ULASAN_PENTADBIR_temp1_length == 1)
	{	
	 document.${formName}.ULASAN_PENTADBIR_temp1.value = "";			
	 var element = document.${formName}.ULASAN_PENTADBIR_temp1;
     element.parentNode.removeChild(element); 	 
	}
	}			
		
	
		
    if(jenis == "tolak" || jenis == "tambah" || jenis == "umum")
	{	
	for (i = 0; i < ttULASAN_PENTADBIR; i++)
    {		
    if(ttULASAN_PENTADBIR==1)
    {	
	check_length(document.${formName}.txtUlasanULASAN_PENTADBIR,'30000','txtUlasanULASAN_PENTADBIR_check','txtUlasanULASAN_PENTADBIR_num','normal','no','ulasan kos-kos akibat posiding');	
	}
	else
	{	
	check_length(document.${formName}.txtUlasanULASAN_PENTADBIR[i],'30000','txtUlasanULASAN_PENTADBIR_check'+i,'txtUlasanULASAN_PENTADBIR_num'+i,'normal','no','ulasan kos-kos akibat posiding');	
	}		 
	}	
	}
	
	
}








function textarea_kerosakan1()
{
	
    var TUJUAN_MAIN_temp1_length=0;
	if(document.${formName}.TUJUAN_MAIN_temp1 != null)
	{
	if(document.${formName}.TUJUAN_MAIN_temp1.length>0)
	{
	TUJUAN_MAIN_temp1_length = document.${formName}.TUJUAN_MAIN_temp1.length;
	}
	else
	{
	TUJUAN_MAIN_temp1_length = 1;
	}
	}

    if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length > 1 )
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.TUJUAN_MAIN_temp1[t].value = document.${formName}.txtUlasanTUJUAN_MAIN[t].value;
	}
	}
	else if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length < 1 )
	{
	for (t = 0; t < TUJUAN_MAIN_temp1_length; t++)
    {	
    document.${formName}.TUJUAN_MAIN_temp1[0].value = document.${formName}.txtUlasanTUJUAN_MAIN.value;
    }
	}
	else if(TUJUAN_MAIN_temp1_length == 1)
	{
	document.${formName}.TUJUAN_MAIN_temp1.value = document.${formName}.txtUlasanTUJUAN_MAIN.value;

	}
	
	
	
	var LATARBELAKANG_MAIN_temp1_length=0;
	if(document.${formName}.LATARBELAKANG_MAIN_temp1 != null)
	{
	if(document.${formName}.LATARBELAKANG_MAIN_temp1.length>0)
	{
	LATARBELAKANG_MAIN_temp1_length = document.${formName}.LATARBELAKANG_MAIN_temp1.length;
	}
	else
	{
	LATARBELAKANG_MAIN_temp1_length = 1;
	}
	}

    if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1 )
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	
    document.${formName}.LATARBELAKANG_MAIN_temp1[t].value = document.${formName}.txtUlasanLATARBELAKANG_MAIN[t].value;
	}
	}
	else if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length < 1 )
	{
	for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++)
    {	
    document.${formName}.LATARBELAKANG_MAIN_temp1[0].value = document.${formName}.txtUlasanLATARBELAKANG_MAIN.value;
    }
	}
	else if(LATARBELAKANG_MAIN_temp1_length == 1)
	{
	document.${formName}.LATARBELAKANG_MAIN_temp1.value = document.${formName}.txtUlasanLATARBELAKANG_MAIN.value;

	}
	
	
	
	var ULASAN_PENTADBIR_temp1_length=0;
	if(document.${formName}.ULASAN_PENTADBIR_temp1 != null)
	{
	if(document.${formName}.ULASAN_PENTADBIR_temp1.length>0)
	{
	ULASAN_PENTADBIR_temp1_length = document.${formName}.ULASAN_PENTADBIR_temp1.length;
	}
	else
	{
	ULASAN_PENTADBIR_temp1_length = 1;
	}
	}

    if(ULASAN_PENTADBIR_temp1_length > 1 && document.${formName}.txtUlasanULASAN_PENTADBIR.length > 1 )
	{
	for (t = 0; t < ULASAN_PENTADBIR_temp1_length; t++)
    {	
    document.${formName}.ULASAN_PENTADBIR_temp1[t].value = document.${formName}.txtUlasanULASAN_PENTADBIR[t].value;
	}
	}
	else if(ULASAN_PENTADBIR_temp1_length > 1 && document.${formName}.txtUlasanULASAN_PENTADBIR.length < 1 )
	{
	for (t = 0; t < ULASAN_PENTADBIR_temp1_length; t++)
    {	
    document.${formName}.ULASAN_PENTADBIR_temp1[0].value = document.${formName}.txtUlasanULASAN_PENTADBIR.value;
    }
	}
	else if(ULASAN_PENTADBIR_temp1_length == 1)
	{
	document.${formName}.ULASAN_PENTADBIR_temp1.value = document.${formName}.txtUlasanULASAN_PENTADBIR.value;

	}
	
		
		

		
		
		
}



function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
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
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
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





function addslashes(str,element) {
str=str.replace(/\\/g,'\\\\');
str=str.replace(/\'/g,'\\\'');
str=str.replace(/\"/g,'\\"');
str=str.replace(/\0/g,'\\0');
element.value = str;
}


function stripslashes(str,element) {
str=str.replace(/\\'/g,'\'');
str=str.replace(/\\"/g,'"');
str=str.replace(/\\0/g,'\0');
str=str.replace(/\\\\/g,'\\');
element.value = str;
}

</script>