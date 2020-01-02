<div id="div_stat">
<table width="100%" id="table_carta"  >
<tr>
<td width="12%" align="center" valign="top">
<img width="30" height="30" src="../img/images_stat.png" align="center"/>
</td>
<td width="88%" valign="top">
<table width="100%">
<tr>
<td   valign="top" >
<b>Carta Status Permohonan Seksyen 8 di Pengkalan Data <font color="blue">$!negeri_sever</font></b>
</td>
</tr>

<tr>
<td   valign="top" >


    <canvas id="cvs" width="300" height="100" >[No canvas support]</canvas>
    
  
  
    



</td>
</tr>

<tr>
<td   valign="top" >
<b>Carta Status Hakmilik (Permohonan Seksyen 8) di Pengkalan Data <font color="blue">$!negeri_sever</font></b>
</td>
</tr>

<tr>
<td   valign="top" >


    <canvas id="cvs1" width="300" height="100" >[No canvas support]</canvas>
    
  
  
    



</td>
</tr>
</table>
</td>
</tr>
</table>
</div>


 <script>
	

	var diterima = '$!DITERIMA';
	var diselesai = '$!DISELESAI';
	
	var lot_diterima = '$!LOT_DITERIMA';
	var lot_diselesai = '$!LOT_DISELESAI';
	
            var hbar = new RGraph.HBar('cvs', [parseInt(diterima),parseInt(diselesai)]);
            hbar.Set('chart.units.pre','');
            hbar.Set('chart.units.post','');
            hbar.Set('chart.colors', ['blue','red','blue','blue']);
            hbar.Set('chart.strokestyle', 'rgba(0,0,0,0)');
            hbar.Set('chart.labels.above', true);            
            hbar.Set('chart.background.grid', true);
            hbar.Set('chart.colors.sequential', true);
            hbar.Set('chart.colors', ['yellow', 'blue']);  
			hbar.Set('chart.labels', ['Belum Selesai','Selesai']);           
            if (!RGraph.isOld()) {
                hbar.Set('chart.tooltips', ['Permohonan Belum Selesai','Permohonan Selesai']);
            }            
            hbar.Set('chart.labels.above.decimals',0);
            hbar.Set('chart.xlabels', false);
            hbar.Set('chart.gutter.left', 90);
            hbar.Set('chart.gutter.right', 60);
            hbar.Set('chart.gutter.top',10);
    
            hbar.Set('chart.noxaxis', true);
            hbar.Set('chart.noxtickmarks', true);
            hbar.Set('chart.noytickmarks', true);
            RGraph.isOld() ? hbar.Draw() : RGraph.Effects.HBar.Grow(hbar); 
			
			
			
			var hbar = new RGraph.HBar('cvs1', [parseInt(lot_diterima),parseInt(lot_diselesai)]);
            hbar.Set('chart.units.pre','');
            hbar.Set('chart.units.post','');
            hbar.Set('chart.colors.sequential', true);
            hbar.Set('chart.colors', ['yellow', 'blue']);  
            hbar.Set('chart.strokestyle', 'rgba(0,0,0,0)');
            hbar.Set('chart.labels.above', true);            
            hbar.Set('chart.background.grid', true);
            hbar.Set('chart.labels', ['Belum Selesai','Selesai']);            
            if (!RGraph.isOld()) {
                hbar.Set('chart.tooltips', ['Hakmilik Belum Selesai','Hakmilik Selesai']);
            }            
            hbar.Set('chart.labels.above.decimals',0);
            hbar.Set('chart.xlabels', false);
            hbar.Set('chart.gutter.left', 90);
            hbar.Set('chart.gutter.right', 60);
            hbar.Set('chart.gutter.top',10);
    
            hbar.Set('chart.noxaxis', true);
            hbar.Set('chart.noxtickmarks', true);
            hbar.Set('chart.noytickmarks', true);
            RGraph.isOld() ? hbar.Draw() : RGraph.Effects.HBar.Grow(hbar);
		
	</script>