package ekptg.fpx;

import java.util.Vector;

import ekptg.fpx.entity.DataFpx;

public interface IFpx {
	public DataFpx save(DataFpx fpx);
	public DataFpx update(DataFpx fpx);
	public DataFpx get(String idFpx);
	public void delete(String idFpx);
	public DataFpx doProcess(DataFpx fpx);
	
	public Vector<DataFpx> search(String ic,String noFail,String tarikh,String nama,String jenisBayaran);
}
