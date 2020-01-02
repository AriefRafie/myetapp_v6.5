<div style="height:250" id="div_stat">
<table width="100%" >
<tr>
<td width="12%" align="center" valign="top">
<img width="30" height="30" src="../img/images_stat.png" align="center"/>
</td>
<td width="88%" valign="top">
<table width="100%">
<tr>
<td   valign="top" >
<b>Carta Status Permohonan di Pengkalan Data <br /><font color="blue">$!negeri_sever</font></b>
</td>
</tr>

<tr>
<td   valign="top" >


    <canvas id="cvs" width="300" height="100" >[No canvas support]</canvas>
    
  
  
    



</td>
</tr>
</table>
</td>
</tr>
</table>
</div>

 <script>
	
	var sek8 = '$!fail_sek8';
	var sek17 = '$!fail_sek17';
	var hapus = '$!fail_hapus';
	var semua = '$!jumlah_keseluruhan';
	var semua_pie = parseInt(sek8)+parseInt(sek17);
	var fail_selesai = '$!fail_selesai';
	var fail_xselesai = '$!fail_xselesai';
	
	
	
	
	
            var hbar = new RGraph.HBar('cvs', [parseInt(fail_xselesai),parseInt(fail_selesai)]);
            hbar.Set('chart.units.pre','');
            hbar.Set('chart.units.post','');
            hbar.Set('chart.colors', ['blue','red','blue','blue']);
            hbar.Set('chart.strokestyle', 'rgba(0,0,0,0)');
            hbar.Set('chart.labels.above', true);			
            //hbar.Set('chart.vmargin', 20);
            hbar.Set('chart.background.grid', true);
            hbar.Set('chart.labels', ['Belum Selesai','Selesai']);            
            if (!RGraph.isOld()) {
                hbar.Set('chart.tooltips', ['Belum Selesai','Selesai']);
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
			
			/*
			 var pie = new RGraph.Pie('cvs_pie', [parseInt(sek8),parseInt(sek17)]);
			//pie.Set('chart.gutter.left', 45);
            pie.Set('chart.colors', ['purple','yellow']);
            pie.Set('chart.labels', ['Seksyen 8','Seksyen 17']);
			//pie.Set('chart.key', ['Seksyen 8','Seksyen 17']);
            //pie.Set('chart.key.background', 'white');
            //pie.Set('chart.labels.sticks', true);
            //pie.Set('chart.labels.sticks.length', 5);
            pie.Set('chart.exploded', 5);            
            if (!RGraph.isOld()) {
                pie.Set('chart.shadow', true);
                pie.Set('chart.shadow.offsetx', 4);
                pie.Set('chart.shadow.offsety', 4);
                pie.Set('chart.shadow.blur', 4);
                pie.Set('chart.tooltips', ['123','9291']);
            }			
            pie.Set('chart.radius', 50);
          //  pie.Set('chart.centerx', 90);
           // pie.Set('chart.centery', 120);
            pie.Set('chart.strokestyle', 'rgba(0,0,0,0)');            
            RGraph.isOld() ? pie.Draw() : RGraph.Effects.Pie.RoundRobin(pie); 
			*/          
        
		/*
		function changeTab(index)
		{
		doAjaxCall${formName}("changeTab","tab_index="+index+"");
		}*/
	</script>