package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblborang entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblborang
 * @author MyEclipse Persistence Tools
 */

public class TblborangDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblborangDAO.class);
	// property constants
	public static final String KOD_BORANG = "kodBorang";
	public static final String NAMA_BORANG = "namaBorang";
	public static final String KETERANGAN = "keterangan";

	public void save(Tblborang transientInstance) {
		log.debug("saving Tblborang instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblborang persistentInstance) {
		log.debug("deleting Tblborang instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblborang findById(java.lang.Long id) {
		log.debug("getting Tblborang instance with id: " + id);
		try {
			Tblborang instance = (Tblborang) getSession().get(
					"ekptg.model.entities.Tblborang", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblborang instance) {
		log.debug("finding Tblborang instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblborang").add(
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
		log.debug("finding Tblborang instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblborang as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodBorang(Object kodBorang) {
		return findByProperty(KOD_BORANG, kodBorang);
	}

	public List findByNamaBorang(Object namaBorang) {
		return findByProperty(NAMA_BORANG, namaBorang);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tblborang instances");
		try {
			String queryString = "from Tblborang";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblborang merge(Tblborang detachedInstance) {
		log.debug("merging Tblborang instance");
		try {
			Tblborang result = (Tblborang) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblborang instance) {
		log.debug("attaching dirty Tblborang instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblborang instance) {
		log.debug("attaching clean Tblborang instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}