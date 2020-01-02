<form id="f1" name="f1" method="post" >
<table width="100%">
  <tr>
    <td>    
    <fieldset><legend>CARIAN PERMOHONAN PEMBATALAN</legend>
    <table width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%" ><div align="right">No Fail</div></td>
    <td width="1%">:</td>
    <td width="70%">
      <label>
        <input name="txtNoFail" type="text" id="txtNoFail" size="70" maxlength="100">
        </label>
      </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">No Ruj JKPTG Negeri</div></td>
    <td>:</td>
    <td>
      <label>
        <input name="txtNoRujJkptgNegeri" type="text" id="txtNoRujJkptgNegeri" size="70" maxlength="100">
        </label>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">Nama Kementerian</div></td>
    <td>:</td>
    <td>
      <label>
      <select name="socKementerian" id="socKementerian">
      </select>
      </label>
       </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">Urusan</div></td>
    <td>:</td>
    <td>
      <label>
        <select name="socUrusan" id="socUrusan">
        </select>
        </label>
      </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">Status</div></td>
    <td>:</td>
    <td>
      <label>
        <select name="socStatus" id="socStatus">
        </select>
        </label>
       </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="right"></div></td>
    <td>&nbsp;</td>
    <td>
      <label>
        <input type="button" name="cmdCari " id="cmdCari " value="Cari">
        </label>
      <label>
      <input  type="button" name="cmdIsiSemula  " id="cmdIsiSemula  " value="Batal">
      </label>
    </td>
  </tr>
</table>

    </fieldset>
    </td>
  </tr>
  
  <tr>
    <td>
    <fieldset><legend>SENARAI PERMOHONAN PEMBATALAN</legend>
    #parse("app/utils/record_paging.jsp")
    <table width="100%">
  <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="15%">No Pembatalan</td>
    <td width="10%">Tarikh Batal</td>
    <td width="15%">No Fail</td>
    <td width="15%">No. Jkptg Negeri</td>
    <td width="15%">Kementerian</td>
    <td width="15%">Daerah</td>
    <td width="15">Status</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

    </fieldset>
    </td>
  </tr>
  
</table>
</form>