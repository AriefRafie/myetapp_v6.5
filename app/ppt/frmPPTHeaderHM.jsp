<style type="text/css">
<!--

.font1 {
	color: #FFFFFF;
	
	font-style: italic;
}

.font2 {
	color: #0000FF;
	
	font-style: italic;
}
-->
</style>





#foreach($dataMT in $dataMaklumatTanah)
    #set($id_hakmilik=$dataMT.id_hakmilik)
	#set($jenisHM=$dataMT.jenis_hakmilik)
	#set($noHM=$dataMT.no_hakmilik)
	#set($daerahHM=$dataMT.nama_daerah)
	#set($mukimHM=$dataMT.nama_mukim)
	#set($seksyenHM=$dataMT.seksyen)
	#set($noLotHM=$dataMT.no_lot)
	#set($noPTHM=$dataMT.no_pt)
	#set($kategoriHM=$dataMT.kategori_tanah)
	#set($luasAmbilHM=$dataMT.luas_ambil)
	#set($unitLuasHM=$dataMT.unitByKategori)
	#set($unitQT=$dataMT.unitQT)
	#set($no_lotpt=$dataMT.no_lotpt)
	#set($kod_jenisHM=$dataMT.kod_jenis_hakmilik)
    #set ($ID_NEGERIPROJEK=$dataMT.id_negeri) 
    
#end

<fieldset>
	<legend><strong>Maklumat Lot</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	
		<tr>
            <td width="25%">JENIS HAKMILIK</td>
            <td width="1%">:</td>
            <td width="74%"><font color="blue">$!jenisHM.toUpperCase()</font></td>
        </tr>
        <tr>
           	<td>NO.HAKMILIK</td>
           	<td>:</td>
          	<td><font color="blue">$!kod_jenisHM $!noHM</font></td>
        </tr>
        <tr>
           	<td>DAERAH</td>
           	<td>:</td>
           	<td><font color="blue">$!daerahHM</font></td>
        </tr>
        <tr>
        	<td>BANDAR/PEKAN/MUKIM</td>
        	<td>:</td>
        	<td><font color="blue">$!mukimHM</font></td>
        </tr> 
        <tr>
        	<td>NO.LOT/NO.PT</td>
        	<td>:</td>
        	<td><font color="blue">$!no_lotpt</font></td>
        </tr> 
        <tr>
        	<td>KATEGORI TANAH</td>
        	<td>:</td>
        	<td><font color="blue">$!kategoriHM</font></td>
        </tr> 
        <tr>
        	<td>LUAS DIAMBIL</td>
        	<td>:</td>
        	<td><font color="blue">$!luasAmbilHM&nbsp;$!unitLuasHM</font></td>
        </tr>

	</table>
</fieldset>


  <script>


function open_new_window(jenis_popup) 
{
 var width  = 0;
 var height = 0;
if(jenis_popup == "1")
{
  width  = 300;
  height = 300;
}
if(jenis_popup == "3")
{
  width  = 300;
  height = 200;
}
if(jenis_popup == "2")
{
 var width  = 300;
 var height = 300;
}



 var left   = (screen.width  - width)/2;
 var top    = (screen.height - height)/2;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=yes';
 params += ', menubar=yes';
 params += ', toolbar=yes';
 params += ', resizable=no';
 params += ', scrollbars=no';
 params += ', status=no';
 params += ', toolbar=no';
//new_window = open("","hoverwindow",params);
new_window = open("","title",params);

new_window.document.open();

new_window.document.write("<html><title>JavaScript New Window</title>");
new_window.document.write("<body bgcolor=\"#FFFFFF\">");

if(jenis_popup == "1")
{
new_window.document.write("Sila klik pada kotak pilihan borang yang disediakan. Tujuan fungsi 'flag/checkbox' pilihan borang adalah untuk memudahkan pengguna untuk menetapkan/menyenaraikan setiap pihak berkepentingan di borang C,D,E,F,G,H dan K. Tanda '/' pada kotak pilihan borang menunjukkan pihak berkepentingan tersebut telah dipilih dan sebalikynya. Setelah pilihan borang telah dipilih,sila klik butang 'Simpan Pilihan Borang' untuk menyimpan rekod.");
}


if(jenis_popup == "2")
{
new_window.document.write("<table width = '100%' >")
new_window.document.write("<tr>")
new_window.document.write("<td colspan = '3'>")
new_window.document.write("Tujuan medan 'Keterangan Jenis PB' adalah untuk memudahkan pengguna untuk menyatakan sebarang keterangan merujuk kepada jenis pihak berkepentingan yang telah dipilih. Contoh : ");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("</table>")
new_window.document.write("<table width = '100%'  >")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Jenis Pihak Berkepentingan");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah");
new_window.document.write("</td>")
new_window.document.write("</tr>")
new_window.document.write("<tr>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Keterangan Jenis PB");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write(":");
new_window.document.write("</td>")
new_window.document.write("<td valign = 'top'>")
new_window.document.write("Pemegang Amanah Kepada Ali bin Abu");
new_window.document.write("</td>")
new_window.document.write("</tr>")

new_window.document.write("</table>")
}

if(jenis_popup == "3")
{
new_window.document.write("Skrin 'Pihak Berkepentingan' adalah skrin baru yang ditempatkan di Urusan Siasatan & Perintah Seksyen 8. Tujuan skrin ini adalah untuk memudahkan pengguna untuk menambah, menghapus, mengemaskini dan menetapkan pilihan borang pada mana-mana  pihak bekepentingan.");
}

new_window.document.write("<br>");

new_window.document.write("</body></html>");
new_window.document.close(); 

}

function close_window() 
{
new_window.close();
}

</script>