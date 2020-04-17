package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtenakmenpinda entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtenakmenpinda
 * @author MyEclipse Persistence Tools
 */

public class TblpdtenakmenpindaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtenakmenpindaDAO.class);
	// property constants
	public static final String NO_ENAKMEN_ASAL = "noEnakmenAsal";
	public static final String NO_ENAKMEN_PINDAAN = "noEnakmenPindaan";
	public static final String NO_ENAKMEN_BARU = "noEnakmenBaru";
	public static final String NAMA_ENAKMEN_BARU = "namaEnakmenBaru";
	public static final String JUSTIFIKASI_PINDAAN = "justifikasiPindaan";
	public static final String KANDUNGAN_PINDAAN = "kandunganPindaan";
	public static final String BIL = "bil";
	public static final String DIR_FAIL = "dirFail";
	public static final String ID_FAIL = "idFail";
	public static final String CATATAN = "catatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtenakmenpinda transientInstance) {
		log.debug("saving Tblpdtenakmenpinda instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtenakmenpinda persistentInstance) {
		log.debug("deleting Tblpdtenakmenpinda instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpinda findById(java.lang.Long id) {
		log.debug("getting Tblpdtenakmenpinda instance with id: " + id);
		try {
			Tblpdtenakmenpinda instance = (Tblpdtenakmenpinda) getSession()
					.get("ekptg.model.entities.Tblpdtenakmenpinda", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtenakmenpinda instance) {
		log.debug("finding Tblpdtenakmenpinda instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtenakmenpinda").add(
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
		log.debug("finding Tblpdtenakmenpinda instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtenakmenpinda as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoEnakmenAsal(Object noEnakmenAsal) {
		return findByProperty(NO_ENAKMEN_ASAL, noEnakmenAsal);
	}

	public List findByNoEnakmenPindaan(Object noEnakmenPindaan) {
		return findByProperty(NO_ENAKMEN_PINDAAN, noEnakmenPindaan);
	}

	public List findByNoEnakmenBaru(Object noEnakmenBaru) {
		return findByProperty(NO_ENAKMEN_BARU, noEnakmenBaru);
	}

	public List findByNamaEnakmenBaru(Object namaEnakmenBaru) {
		return findByProperty(NAMA_ENAKMEN_BARU, namaEnakmenBaru);
	}

	public List findByJustifikasiPindaan(Object justifikasiPindaan) {
		return findByProperty(JUSTIFIKASI_PINDAAN, justifikasiPindaan);
	}

	public List findByKandunganPindaan(Object kandunganPindaan) {
		return findByProperty(KANDUNGAN_PINDAAN, kandunganPindaan);
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
		log.debug("finding all Tblpdtenakmenpinda instances");
		try {
			String queryString = "from Tblpdtenakmenpinda";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpinda merge(Tblpdtenakmenpinda detachedInstance) {
		log.debug("merging Tblpdtenakmenpinda instance");
		try {
			Tblpdtenakmenpinda result = (Tblpdtenakmenpinda) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtenakmenpinda instance) {
		log.debug("attaching dirty Tblpdtenakmenpinda instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtenakmenpinda instance) {
		log.debug("attaching clean Tblpdtenakmenpinda instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}