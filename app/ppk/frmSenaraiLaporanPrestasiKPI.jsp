 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
  <br>
  <br>

<fieldset>
<legend>Laporan Prestasi Pencapaian KPI Bulanan </legend>
<fieldset>
<table border="0" align="center" width="60%" >      
     <tbody> 
     <tr> 
        <td scope="row" align="left">&nbsp;Negeri </td>
        <td>:&nbsp;</td>
        <td>$selectNegeri</td>
      </tr>
      <!-- 
      <tr> 
        <td scope="row" align="left">&nbsp;Unit </td>
        <td>:&nbsp;</td>
        <td>$selectUnit</td>
      </tr>
      <tr> 
        <td scope="row" align="left">&nbsp;Daerah </td>
        <td>:&nbsp;</td>
        <td>$selectDaerah</td>
      </tr>  

      
    	#set ($txdMula = "")
   		#set ($txdAkhir = "")
   		       -->
      
      <tr> 
        <td scope="row" align="left">&nbsp;Bulan </td>
        <td>:&nbsp;</td>
        <td>
       	<select name="bulan" style="width: 100px;">
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		</select>
		</td>
      </tr>  
      
      <tr> 
        <td scope="row" align="left">&nbsp;Tahun </td>
        <td>:&nbsp;</td>
        <td><select id="yeardropdown" name="tahun1" style="width: 100px;">
        <option value="2018" selected="">2018</option><option value="2017">2017</option><option value="2016">2016</option><option value="2015">2015</option><option value="2014">2014</option><option value="2013">2013</option><option value="2012">2012</option><option value="2011">2011</option><option value="2010">2010</option><option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option><option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option><option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option></select>
		</td>
      </tr>  
      
      <tr> 
        <td scope="row" align="left">&nbsp;Seksyen </td>
        <td>:&nbsp;</td>
        <td><select id="sectiondropdown" name="section" style="width: 100px;">
        <option value="8">Seksyen 8</option>
        <option value="17">Seksyen 17</option>
        </select>
		</td>
      </tr>  

   </tbody>
 </table>
</fieldset>
 <table border="0" align="center" width="60%" > 
	<tr> 
		<td align="center"><input type="button" name="cmdCetak" value="Cetak" onclick="javascript:cetakPrestasi()"></td>
	</tr>
		
</table>
</fieldset>	


 

	
<script>
window.onload=function()
{
populatedropdown("yeardropdown")
populatedropdown2("yeardropdown2")
}

function cetakPrestasi()
{
//MENGIKUT UNIT	
		var negeri = document.${formName}.socNegeri.value;
		var	mula_bulan = document.${formName}.bulan.value;
		var	mula_tahun = document.${formName}.tahun1.value;
		var section = document.${formName}.section.value;
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPrestasiKPI?mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&section="+section+"&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
	
}


function populatedropdown(yearfield){
var today=new Date()
var yearfield=document.getElementById(yearfield)

var thisyear=today.getFullYear()
for (var y=0; y<20; y++){	
yearfield.options[y]=new Option(thisyear, thisyear)
thisyear-=1
}
yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true) 
}

function populatedropdown2(yearfield){
	var today=new Date()
	var yearfield=document.getElementById(yearfield)

	var thisyear=today.getFullYear()
	for (var y=0; y<20; y++){
	yearfield.options[y]=new Option(thisyear, thisyear)
	thisyear-=1
	}
	yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true) 
	}



function openLaporan(urli,param,laporan,tem){
	var negeri = 0;
	var unit = 0;
	var daerah = document.${formName}.socDaerah.value;
	var pnegeri = "&ID_NEGERI=0";
	var ptahun = "&TAHUN=";
	var ptem = "&template="+tem;
	var pbulanmula = "&BULANTAHUN=0";
	var pbulantamat = "&BULANTAHUNTMT=0";
	var punit = "&ID_PEJABAT=0";
	var pdaerah = "&ID=0";

	if(laporan=="negeri"){
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		pnegeri = "&ID_NEGERI="+negeri;
	}else if(laporan=="unit"){
			if(unit==""){
			alert("Sila pilih \"Unit\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}	
		punit = "&ID_PEJABAT="+unit;
	}else if(laporan=="daerah"){
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		pdaerah = "&ID="+daerah;
	}else{
	
		akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
  		akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
  		akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
  		mula_bulan = document.${formName}.txdMula.value.substring(3,5);
 		mula_hari = document.${formName}.txdMula.value.substring(0,2);
  		mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
	
		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
	  
	  	if(akhir<mula){
	    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
	    	return;
	  	}
	  	if(akhir>tarikhsemasa){
	    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
	    	return;
	  	}
		
		if(laporan=="negeritahun"){
			if(negeri==""){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}	
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="negeribulan"){
			if(negeri==""){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="negeriselang"){
			if(negeri==""){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		/** By Unit*/
		if(laporan=="unittahun"){
			if(unit==""){
				alert("Sila pilih \"Unit\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_PEJABAT="+unit;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}	
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="unitbulan"){
			if(unit==""){
				alert("Sila pilih \"Unit\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_PEJABAT="+unit;
			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="unitselang"){
			if(unit==""){
				alert("Sila pilih \"Unit\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_PEJABAT="+unit;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		/** By Daerah*/
		if(laporan=="daerahtahun"){
			if(daerah==""){
				alert("Sila pilih \"Daerah\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			pdaerah = "&ID="+daerah;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}	
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="daerahbulan"){
			if(daerah==""){
				alert("Sila pilih \"Daerah\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			pdaerah = "&ID="+daerah;
			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="daerahselang"){
			if(daerah==""){
				alert("Sila pilih \"Daerah\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			pdaerah = "&ID="+daerah;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun;
		}		
		
	}
	
	var url = "../servlet/"+urli+"?"+param+pnegeri+ptahun+ptem+pbulanmula+pbulantamat+punit+pdaerah;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
}
function doChangeNegeri() {
	if(document.${formName}.socNegeri.value=="0")
		return;
	doAjaxCall${formName}("PilihNegeri");

}
</script>


