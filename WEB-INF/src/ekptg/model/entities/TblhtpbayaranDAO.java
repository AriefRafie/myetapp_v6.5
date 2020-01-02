package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpbayaran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpbayaran
 * @author MyEclipse Persistence Tools
 */

public class TblhtpbayaranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpbayaranDAO.class);

	// property constants

	public void save(Tblhtpbayaran transientInstance) {
		log.debug("saving Tblhtpbayaran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpbayaran persistentInstance) {
		log.debug("deleting Tblhtpbayaran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpbayaran findById(ekptg.model.entities.TblhtpbayaranId id) {
		log.debug("getting Tblhtpbayaran instance with id: " + id);
		try {
			Tblhtpbayaran instance = (Tblhtpbayaran) getSession().get(
					"ekptg.model.entities.Tblhtpbayaran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpbayaran instance) {
		log.debug("finding Tblhtpbayaran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpbayaran").add(
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
		log.debug("finding Tblhtpbayaran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpbayaran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Tblhtpbayaran instances");
		try {
			String queryString = "from Tblhtpbayaran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpbayaran merge(Tblhtpbayaran detachedInstance) {
		log.debug("merging Tblhtpbayaran instance");
		try {
			Tblhtpbayaran result = (Tblhtpbayaran) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpbayaran instance) {
		log.debug("attaching dirty Tblhtpbayaran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpbayaran instance) {
		log.debug("attaching clean Tblhtpbayaran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}