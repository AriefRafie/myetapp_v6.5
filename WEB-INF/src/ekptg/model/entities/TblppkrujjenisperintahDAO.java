package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkrujjenisperintah entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkrujjenisperintah
 * @author MyEclipse Persistence Tools
 */

public class TblppkrujjenisperintahDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkrujjenisperintahDAO.class);
	// property constants
	public static final String KOD = "kod";
	public static final String KETERANGAN = "keterangan";

	public void save(Tblppkrujjenisperintah transientInstance) {
		log.debug("saving Tblppkrujjenisperintah instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkrujjenisperintah persistentInstance) {
		log.debug("deleting Tblppkrujjenisperintah instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkrujjenisperintah findById(java.lang.Long id) {
		log.debug("getting Tblppkrujjenisperintah instance with id: " + id);
		try {
			Tblppkrujjenisperintah instance = (Tblppkrujjenisperintah) getSession().get(
					"ekptg.model.entities.Tblppkrujjenisperintah", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkrujjenisperintah instance) {
		log.debug("finding Tblppkrujjenisperintah instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkrujjenisperintah").add(
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
		log.debug("finding Tblppkrujjenisperintah instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkrujjenisperintah as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKod(Object kod) {
		return findByProperty(KOD, kod);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tblppkrujjenisperintah instances");
		try {
			String queryString = "from Tblppkrujjenisperintah";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkrujjenisperintah merge(Tblppkrujjenisperintah detachedInstance) {
		log.debug("merging Tblppkrujjenisperintah instance");
		try {
			Tblppkrujjenisperintah result = (Tblppkrujjenisperintah) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkrujjenisperintah instance) {
		log.debug("attaching dirty Tblppkrujjenisperintah instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkrujjenisperintah instance) {
		log.debug("attaching clean Tblppkrujjenisperintah instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}