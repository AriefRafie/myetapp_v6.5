package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpkertaskerjapelepasan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphpkertaskerjapelepasan
 * @author MyEclipse Persistence Tools
 */

public class TblphpkertaskerjapelepasanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpkertaskerjapelepasanDAO.class);
	// property constants
	public static final String ID_MESYUARAT = "idMesyuarat";
	public static final String ID_KERTASKERJA = "idKertaskerja";
	public static final String NO_VERSI = "noVersi";
	public static final String TUJUAN = "tujuan";
	public static final String PERIHAL_KEMAJUANTANAH = "perihalKemajuantanah";
	public static final String PIHAK_POHON_SERAHBALIK = "pihakPohonSerahbalik";
	public static final String LAPORAN_PENILAI_TANAH = "laporanPenilaiTanah";
	public static final String ULASAN_KEMENTERIAN_TANI = "ulasanKementerianTani";
	public static final String PERAKUAN_PTP_ATASMEMO = "perakuanPtpAtasmemo";
	public static final String PERAKUAN_PTP_ATASKERTAS = "perakuanPtpAtaskertas";
	public static final String SYOR = "syor";
	public static final String ULASAN_JABATAN = "ulasanJabatan";
	public static final String ULASAN_MENTERI_KEWANGAN = "ulasanMenteriKewangan";
	public static final String PERAKUAN_KSU = "perakuanKsu";
	public static final String NO_KERTAS = "noKertas";
	public static final String BIL_MESYUARAT = "bilMesyuarat";
	public static final String KEPUTUSAN = "keputusan";
	public static final String ULASAN_KEPUTUSAN = "ulasanKeputusan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DOKUMEN = "idDokumen";

	public void save(Tblphpkertaskerjapelepasan transientInstance) {
		log.debug("saving Tblphpkertaskerjapelepasan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpkertaskerjapelepasan persistentInstance) {
		log.debug("deleting Tblphpkertaskerjapelepasan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpkertaskerjapelepasan findById(java.lang.Long id) {
		log.debug("getting Tblphpkertaskerjapelepasan instance with id: " + id);
		try {
			Tblphpkertaskerjapelepasan instance = (Tblphpkertaskerjapelepasan) getSession()
					.get("ekptg.model.entities.Tblphpkertaskerjapelepasan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpkertaskerjapelepasan instance) {
		log.debug("finding Tblphpkertaskerjapelepasan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpkertaskerjapelepasan").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tblphpkertaskerjapelepasan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpkertaskerjapelepasan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdMesyuarat(Object idMesyuarat) {
		return findByProperty(ID_MESYUARAT, idMesyuarat);
	}

	public List findByIdKertaskerja(Object idKertaskerja) {
		return findByProperty(ID_KERTASKERJA, idKertaskerja);
	}

	public List findByNoVersi(Object noVersi) {
		return findByProperty(NO_VERSI, noVersi);
	}

	public List findByTujuan(Object tujuan) {
		return findByProperty(TUJUAN, tujuan);
	}

	public List findByPerihalKemajuantanah(Object perihalKemajuantanah) {
		return findByProperty(PERIHAL_KEMAJUANTANAH, perihalKemajuantanah);
	}

	public List findByPihakPohonSerahbalik(Object pihakPohonSerahbalik) {
		return findByProperty(PIHAK_POHON_SERAHBALIK, pihakPohonSerahbalik);
	}

	public List findByLaporanPenilaiTanah(Object laporanPenilaiTanah) {
		return findByProperty(LAPORAN_PENILAI_TANAH, laporanPenilaiTanah);
	}

	public List findByUlasanKementerianTani(Object ulasanKementerianTani) {
		return findByProperty(ULASAN_KEMENTERIAN_TANI, ulasanKementerianTani);
	}

	public List findByPerakuanPtpAtasmemo(Object perakuanPtpAtasmemo) {
		return findByProperty(PERAKUAN_PTP_ATASMEMO, perakuanPtpAtasmemo);
	}

	public List findByPerakuanPtpAtaskertas(Object perakuanPtpAtaskertas) {
		return findByProperty(PERAKUAN_PTP_ATASKERTAS, perakuanPtpAtaskertas);
	}

	public List findBySyor(Object syor) {
		return findByProperty(SYOR, syor);
	}

	public List findByUlasanJabatan(Object ulasanJabatan) {
		return findByProperty(ULASAN_JABATAN, ulasanJabatan);
	}

	public List findByUlasanMenteriKewangan(Object ulasanMenteriKewangan) {
		return findByProperty(ULASAN_MENTERI_KEWANGAN, ulasanMenteriKewangan);
	}

	public List findByPerakuanKsu(Object perakuanKsu) {
		return findByProperty(PERAKUAN_KSU, perakuanKsu);
	}

	public List findByNoKertas(Object noKertas) {
		return findByProperty(NO_KERTAS, noKertas);
	}

	public List findByBilMesyuarat(Object bilMesyuarat) {
		return findByProperty(BIL_MESYUARAT, bilMesyuarat);
	}

	public List findByKeputusan(Object keputusan) {
		return findByProperty(KEPUTUSAN, keputusan);
	}

	public List findByUlasanKeputusan(Object ulasanKeputusan) {
		return findByProperty(ULASAN_KEPUTUSAN, ulasanKeputusan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdDokumen(Object idDokumen) {
		return findByProperty(ID_DOKUMEN, idDokumen);
	}

	public List findAll() {
		log.debug("finding all Tblphpkertaskerjapelepasan instances");
		try {
			String queryString = "from Tblphpkertaskerjapelepasan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpkertaskerjapelepasan merge(
			Tblphpkertaskerjapelepasan detachedInstance) {
		log.debug("merging Tblphpkertaskerjapelepasan instance");
		try {
			Tblphpkertaskerjapelepasan result = (Tblphpkertaskerjapelepasan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpkertaskerjapelepasan instance) {
		log.debug("attaching dirty Tblphpkertaskerjapelepasan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpkertaskerjapelepasan instance) {
		log.debug("attaching clean Tblphpkertaskerjapelepasan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}