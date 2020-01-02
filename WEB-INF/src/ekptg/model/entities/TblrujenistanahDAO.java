package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujenistanah entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujenistanah
 * @author MyEclipse Persistence Tools
 */

public class TblrujenistanahDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujenistanahDAO.class);

	// property constants

	public void save(Tblrujenistanah transientInstance) {
		log.debug("saving Tblrujenistanah instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujenistanah persistentInstance) {
		log.debug("deleting Tblrujenistanah instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujenistanah findById(ekptg.model.entities.TblrujenistanahId id) {
		log.debug("getting Tblrujenistanah instance with id: " + id);
		try {
			Tblrujenistanah instance = (Tblrujenistanah) getSession().get(
					"ekptg.model.entities.Tblrujenistanah", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujenistanah instance) {
		log.debug("finding Tblrujenistanah instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujenistanah").add(
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
		log.debug("finding Tblrujenistanah instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujenistanah as model where model."
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
		log.debug("finding all Tblrujenistanah instances");
		try {
			String queryString = "from Tblrujenistanah";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujenistanah merge(Tblrujenistanah detachedInstance) {
		log.debug("merging Tblrujenistanah instance");
		try {
			Tblrujenistanah result = (Tblrujenistanah) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujenistanah instance) {
		log.debug("attaching dirty Tblrujenistanah instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujenistanah instance) {
		log.debug("attaching clean Tblrujenistanah instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}