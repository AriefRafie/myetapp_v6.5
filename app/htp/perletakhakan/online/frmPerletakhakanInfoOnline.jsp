<style type="text/css">
<!--
.csscolor {
	color: #00C;
}
-->
<!--
.pautanms {color: #0000FF}
-->
</style>

#foreach($list in $List)
	#set ($txtNamaKementerian = $list.txtNamaKementerian)
    #set ($txtNamaAgensi = $list.txtNamaAgensi)
    #set ($txtNamaSuburusan = $list.txtNamaSuburusan)
	#set ($txtTajuk = $list.txtTajuk)
    #set ($txtNoFailSeksyen = $list.txtNoFailSeksyen)
    #set ($txtNoFailKJP = $list.txtNoFailKJP)
    #set ($txtNoFailLain = $list.txtNoFailLain)
    #set ($txdTarikhSurKJP = $list.txdTarikhSurKJP)
    #set ($txdTarikhTerima = $list.txdTarikhTerima)
    #set ($txdTarikhBukaFail = $list.txdTarikhBukaFail)
    #set ($idPermohonan = $list.idPermohonan)
    #set ($txdNamaNegeri= $list.txdNamaNegeri)
    #set ($txdNamaDaerah= $list.txdNamaDaerah)
    
#end
<style type="text/css">
<!--
.stylelabel{color: #0000FF}
-->
</style>
<fieldset>
<legend><strong>MAKLUMAT FAIL</strong></legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">

      <tr>
		<td width="50%" align="center" valign="top">
			<table width="100%" border="0">
              <tr>
                <td width="35%" style="text-transform:uppercase;"><div align="right">Negeri</div></td>
                <td width="1%">:</td>            
                <td width="64%">
                	<div align="left" class="stylelabel">$txdNamaNegeri</div>               	
                </td>
              </tr>
              <tr>
                <td width="35%" style="text-transform:uppercase;"><div align="right">Daerah</div></td>
                <td width="1%">:</td>            
                <td width="64%">
                	<div align="left" class="stylelabel">$txdNamaDaerah</div>               	
                </td>
              </tr>
              <tr>
                <td width="35%" style="text-transform:uppercase;" valign="top"><div align="right">Kementerian</div></td>
                <td width="1%" valign="top">:</td>            
                <td width="64%">
                	<div align="left" class="stylelabel">$txtNamaKementerian</div>               	
                </td>
              </tr>              
              <tr>
                <td width="35%" style="text-transform:uppercase;" valign="top"><div align="right">Agensi</div></td>
                <td width="1%" valign="top">:</td>            
                <td width="64%">
                	<div align="left" class="stylelabel">$txtNamaAgensi</div>               	
                </td>
              </tr>   
              <tr>
                <td width="35%" style="text-transform:uppercase;"><div align="right">Urusan</div></td>
                <td width="1%">:</td>            
                <td width="64%">
                	<div align="left" class="stylelabel">$txtNamaSuburusan</div>               	
                </td>
              </tr>               
              <tr>
                <td width="35%" style="text-transform:uppercase;" valign="top" align="right">
                	Tajuk
                </td>
                <td width="1%" valign="top">:</td>            
                <td width="64%">
                	<div align="left" class="stylelabel">$txtTajuk</div>               	
                </td>
              </tr>   
             </table>	
    	</td>
        <td width="50%" align="center" valign="top"><table width="100%" border="0">
        	<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Seksyen</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$txtNoFailSeksyen</div>               	
				</td>		
              </tr> 
        	<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail KJP</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$txtNoFailKJP</div>               	
				</td>		
              </tr> 
         	<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Lain</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$txtNoFailLain</div>               	
				</td>		
              </tr>                      
         	<tr>
               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Buka Fail</div></td>
                <td width="1%">:</td>
                <td width="64%">
                	<div align="left" class="stylelabel">$txdTarikhBukaFail</div>               	
				</td>		
              </tr>            
        </table>
        </td>
      </tr>

  </table> 
</fieldset>
