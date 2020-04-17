package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpbayarancukai entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpbayarancukai
 * @author MyEclipse Persistence Tools
 */

public class TblhtpbayarancukaiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpbayarancukaiDAO.class);
	// property constants
	public static final String STATUS = "status";
	public static final String NAMA_BANK = "namaBank";
	public static final String AMAUN = "amaun";
	public static final String NO_RUJBAYARAN = "noRujbayaran";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpbayarancukai transientInstance) {
		log.debug("saving Tblhtpbayarancukai instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpbayarancukai persistentInstance) {
		log.debug("deleting Tblhtpbayarancukai instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpbayarancukai findById(java.lang.Long id) {
		log.debug("getting Tblhtpbayarancukai instance with id: " + id);
		try {
			Tblhtpbayarancukai instance = (Tblhtpbayarancukai) getSession()
					.get("ekptg.model.entities.Tblhtpbayarancukai", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpbayarancukai instance) {
		log.debug("finding Tblhtpbayarancukai instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpbayarancukai").add(
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
		log.debug("finding Tblhtpbayarancukai instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpbayarancukai as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByNamaBank(Object namaBank) {
		return findByProperty(NAMA_BANK, namaBank);
	}

	public List findByAmaun(Object amaun) {
		return findByProperty(AMAUN, amaun);
	}

	public List findByNoRujbayaran(Object noRujbayaran) {
		return findByProperty(NO_RUJBAYARAN, noRujbayaran);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpbayarancukai instances");
		try {
			String queryString = "from Tblhtpbayarancukai";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpbayarancukai merge(Tblhtpbayarancukai detachedInstance) {
		log.debug("merging Tblhtpbayarancukai instance");
		try {
			Tblhtpbayarancukai result = (Tblhtpbayarancukai) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpbayarancukai instance) {
		log.debug("attaching dirty Tblhtpbayarancukai instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpbayarancukai instance) {
		log.debug("attaching clean Tblhtpbayarancukai instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}