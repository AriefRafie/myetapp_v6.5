package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpmaklumatmsyrt entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpmaklumatmsyrt
 * @author MyEclipse Persistence Tools
 */

public class TblhtpmaklumatmsyrtDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpmaklumatmsyrtDAO.class);

	// property constants

	public void save(Tblhtpmaklumatmsyrt transientInstance) {
		log.debug("saving Tblhtpmaklumatmsyrt instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpmaklumatmsyrt persistentInstance) {
		log.debug("deleting Tblhtpmaklumatmsyrt instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpmaklumatmsyrt findById(
			ekptg.model.entities.TblhtpmaklumatmsyrtId id) {
		log.debug("getting Tblhtpmaklumatmsyrt instance with id: " + id);
		try {
			Tblhtpmaklumatmsyrt instance = (Tblhtpmaklumatmsyrt) getSession()
					.get("ekptg.model.entities.Tblhtpmaklumatmsyrt", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpmaklumatmsyrt instance) {
		log.debug("finding Tblhtpmaklumatmsyrt instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpmaklumatmsyrt").add(
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
		log.debug("finding Tblhtpmaklumatmsyrt instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpmaklumatmsyrt as model where model."
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
		log.debug("finding all Tblhtpmaklumatmsyrt instances");
		try {
			String queryString = "from Tblhtpmaklumatmsyrt";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpmaklumatmsyrt merge(Tblhtpmaklumatmsyrt detachedInstance) {
		log.debug("merging Tblhtpmaklumatmsyrt instance");
		try {
			Tblhtpmaklumatmsyrt result = (Tblhtpmaklumatmsyrt) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpmaklumatmsyrt instance) {
		log.debug("attaching dirty Tblhtpmaklumatmsyrt instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpmaklumatmsyrt instance) {
		log.debug("attaching clean Tblhtpmaklumatmsyrt instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}