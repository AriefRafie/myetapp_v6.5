package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtaktapinda entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtaktapinda
 * @author MyEclipse Persistence Tools
 */

public class TblpdtaktapindaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpdtaktapindaDAO.class);
	// property constants
	public static final String NO_AKTA_ASAL = "noAktaAsal";
	public static final String NO_AKTA_PINDAAN = "noAktaPindaan";
	public static final String NO_AKTA_BARU = "noAktaBaru";
	public static final String NAMA_AKTA_BARU = "namaAktaBaru";
	public static final String JUSTIFIKASI_PINDAAN = "justifikasiPindaan";
	public static final String NO_MEMORANDUM_MENTERI = "noMemorandumMenteri";
	public static final String KETERANGAN_JEMAAH_MENTERI = "keteranganJemaahMenteri";
	public static final String BIL = "bil";
	public static final String DIR_FAIL = "dirFail";
	public static final String ID_FAIL = "idFail";
	public static final String CATATAN = "catatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtaktapinda transientInstance) {
		log.debug("saving Tblpdtaktapinda instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtaktapinda persistentInstance) {
		log.debug("deleting Tblpdtaktapinda instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtaktapinda findById(java.lang.Long id) {
		log.debug("getting Tblpdtaktapinda instance with id: " + id);
		try {
			Tblpdtaktapinda instance = (Tblpdtaktapinda) getSession().get(
					"ekptg.model.entities.Tblpdtaktapinda", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtaktapinda instance) {
		log.debug("finding Tblpdtaktapinda instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtaktapinda").add(
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
		log.debug("finding Tblpdtaktapinda instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtaktapinda as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoAktaAsal(Object noAktaAsal) {
		return findByProperty(NO_AKTA_ASAL, noAktaAsal);
	}

	public List findByNoAktaPindaan(Object noAktaPindaan) {
		return findByProperty(NO_AKTA_PINDAAN, noAktaPindaan);
	}

	public List findByNoAktaBaru(Object noAktaBaru) {
		return findByProperty(NO_AKTA_BARU, noAktaBaru);
	}

	public List findByNamaAktaBaru(Object namaAktaBaru) {
		return findByProperty(NAMA_AKTA_BARU, namaAktaBaru);
	}

	public List findByJustifikasiPindaan(Object justifikasiPindaan) {
		return findByProperty(JUSTIFIKASI_PINDAAN, justifikasiPindaan);
	}

	public List findByNoMemorandumMenteri(Object noMemorandumMenteri) {
		return findByProperty(NO_MEMORANDUM_MENTERI, noMemorandumMenteri);
	}

	public List findByKeteranganJemaahMenteri(Object keteranganJemaahMenteri) {
		return findByProperty(KETERANGAN_JEMAAH_MENTERI,
				keteranganJemaahMenteri);
	}

	public List findByBil(Object bil) {
		return findByProperty(BIL, bil);
	}

	public List findByDirFail(Object dirFail) {
		return findByProperty(DIR_FAIL, dirFail);
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtaktapinda instances");
		try {
			String queryString = "from Tblpdtaktapinda";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtaktapinda merge(Tblpdtaktapinda detachedInstance) {
		log.debug("merging Tblpdtaktapinda instance");
		try {
			Tblpdtaktapinda result = (Tblpdtaktapinda) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtaktapinda instance) {
		log.debug("attaching dirty Tblpdtaktapinda instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtaktapinda instance) {
		log.debug("attaching clean Tblpdtaktapinda instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}