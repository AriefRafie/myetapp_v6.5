package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtphakmilikgadaian entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtphakmilikgadaian
 * @author MyEclipse Persistence Tools
 */

public class TblhtphakmilikgadaianDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtphakmilikgadaianDAO.class);

	// property constants

	public void save(Tblhtphakmilikgadaian transientInstance) {
		log.debug("saving Tblhtphakmilikgadaian instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtphakmilikgadaian persistentInstance) {
		log.debug("deleting Tblhtphakmilikgadaian instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtphakmilikgadaian findById(
			ekptg.model.entities.TblhtphakmilikgadaianId id) {
		log.debug("getting Tblhtphakmilikgadaian instance with id: " + id);
		try {
			Tblhtphakmilikgadaian instance = (Tblhtphakmilikgadaian) getSession()
					.get("ekptg.model.entities.Tblhtphakmilikgadaian", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtphakmilikgadaian instance) {
		log.debug("finding Tblhtphakmilikgadaian instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtphakmilikgadaian").add(
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
		log.debug("finding Tblhtphakmilikgadaian instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtphakmilikgadaian as model where model."
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
		log.debug("finding all Tblhtphakmilikgadaian instances");
		try {
			String queryString = "from Tblhtphakmilikgadaian";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtphakmilikgadaian merge(Tblhtphakmilikgadaian detachedInstance) {
		log.debug("merging Tblhtphakmilikgadaian instance");
		try {
			Tblhtphakmilikgadaian result = (Tblhtphakmilikgadaian) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtphakmilikgadaian instance) {
		log.debug("attaching dirty Tblhtphakmilikgadaian instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtphakmilikgadaian instance) {
		log.debug("attaching clean Tblhtphakmilikgadaian instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}