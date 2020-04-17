package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpkertaskerjapenyewaan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphpkertaskerjapenyewaan
 * @author MyEclipse Persistence Tools
 */

public class TblphpkertaskerjapenyewaanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpkertaskerjapenyewaanDAO.class);
	// property constants
	public static final String ID_MESYUARAT = "idMesyuarat";
	public static final String ID_KERTASKERJA = "idKertaskerja";
	public static final String NO_VERSI = "noVersi";
	public static final String TAJUK_KERTAS = "tajukKertas";
	public static final String TUJUAN = "tujuan";
	public static final String LAPORAN_TANAH = "laporanTanah";
	public static final String LAPORAN_PENILAI_TANAH = "laporanPenilaiTanah";
	public static final String ULASAN_KJP = "ulasanKjp";
	public static final String ULASAN_URUSETIA = "ulasanUrusetia";
	public static final String SYOR_URUSETIA = "syorUrusetia";
	public static final String NO_KERTAS = "noKertas";
	public static final String BIL_MESYUARAT = "bilMesyuarat";
	public static final String KEPUTUSAN = "keputusan";
	public static final String ULASAN_KEPUTUSAN = "ulasanKeputusan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DOKUMEN = "idDokumen";

	public void save(Tblphpkertaskerjapenyewaan transientInstance) {
		log.debug("saving Tblphpkertaskerjapenyewaan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpkertaskerjapenyewaan persistentInstance) {
		log.debug("deleting Tblphpkertaskerjapenyewaan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpkertaskerjapenyewaan findById(java.lang.Long id) {
		log.debug("getting Tblphpkertaskerjapenyewaan instance with id: " + id);
		try {
			Tblphpkertaskerjapenyewaan instance = (Tblphpkertaskerjapenyewaan) getSession()
					.get("ekptg.model.entities.Tblphpkertaskerjapenyewaan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpkertaskerjapenyewaan instance) {
		log.debug("finding Tblphpkertaskerjapenyewaan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpkertaskerjapenyewaan").add(
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
		log.debug("finding Tblphpkertaskerjapenyewaan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpkertaskerjapenyewaan as model where model."
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

	public List findByTajukKertas(Object tajukKertas) {
		return findByProperty(TAJUK_KERTAS, tajukKertas);
	}

	public List findByTujuan(Object tujuan) {
		return findByProperty(TUJUAN, tujuan);
	}

	public List findByLaporanTanah(Object laporanTanah) {
		return findByProperty(LAPORAN_TANAH, laporanTanah);
	}

	public List findByLaporanPenilaiTanah(Object laporanPenilaiTanah) {
		return findByProperty(LAPORAN_PENILAI_TANAH, laporanPenilaiTanah);
	}

	public List findByUlasanKjp(Object ulasanKjp) {
		return findByProperty(ULASAN_KJP, ulasanKjp);
	}

	public List findByUlasanUrusetia(Object ulasanUrusetia) {
		return findByProperty(ULASAN_URUSETIA, ulasanUrusetia);
	}

	public List findBySyorUrusetia(Object syorUrusetia) {
		return findByProperty(SYOR_URUSETIA, syorUrusetia);
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
		log.debug("finding all Tblphpkertaskerjapenyewaan instances");
		try {
			String queryString = "from Tblphpkertaskerjapenyewaan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpkertaskerjapenyewaan merge(
			Tblphpkertaskerjapenyewaan detachedInstance) {
		log.debug("merging Tblphpkertaskerjapenyewaan instance");
		try {
			Tblphpkertaskerjapenyewaan result = (Tblphpkertaskerjapenyewaan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpkertaskerjapenyewaan instance) {
		log.debug("attaching dirty Tblphpkertaskerjapenyewaan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpkertaskerjapenyewaan instance) {
		log.debug("attaching clean Tblphpkertaskerjapenyewaan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}