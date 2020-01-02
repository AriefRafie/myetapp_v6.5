package ekptg.model.online.aduan;

import java.util.Vector;

import ekptg.model.online.aduan.entity.OnlineLampiran;

public interface IJawapanLampiran {
	public Vector<OnlineLampiran> getLampiranByRecord(String idRekod);
}
