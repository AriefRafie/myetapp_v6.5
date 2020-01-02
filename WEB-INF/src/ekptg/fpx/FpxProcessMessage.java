package ekptg.fpx;

import ekptg.fpx.entity.DataFpx;

public class FpxProcessMessage extends FpxBean implements IFpx {

	@Override
	public DataFpx doProcess(DataFpx fpx) {
		String noTransaksi = fpx.getNoTransaksi();
		DataFpx temp = null;
		if(get(noTransaksi)==null){
			//save
			temp = save(fpx);
		}else{
			if(fpx.getStatus().equals("SUCCESSFUL")){
				fpx.setNoResit(getReceiptNo(noTransaksi));
			}
			temp = update(fpx);
		}
		return get(temp.getNoTransaksi());
	}
	
	

}
