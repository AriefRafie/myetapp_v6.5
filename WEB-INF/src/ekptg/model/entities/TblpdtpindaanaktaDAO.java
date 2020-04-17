package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtpindaanakta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtpindaanakta
 * @author MyEclipse Persistence Tools
 */

public class TblpdtpindaanaktaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtpindaanaktaDAO.class);

	// property constants

	public void save(Tblpdtpindaanakta transientInstance) {
		log.debug("saving Tblpdtpindaanakta instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtpindaanakta persistentInstance) {
		log.debug("deleting Tblpdtpindaanakta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanakta findById(java.lang.Long id) {
		log.debug("getting Tblpdtpindaanakta instance with id: " + id);
		try {
			Tblpdtpindaanakta instance = (Tblpdtpindaanakta) getSession().get(
					"ekptg.model.entities.Tblpdtpindaanakta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtpindaanakta instance) {
		log.debug("finding Tblpdtpindaanakta instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtpindaanakta").add(
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
		log.debug("finding Tblpdtpindaanakta instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtpindaanakta as model where model."
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
		log.debug("finding all Tblpdtpindaanakta instances");
		try {
			String queryString = "from Tblpdtpindaanakta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanakta merge(Tblpdtpindaanakta detachedInstance) {
		log.debug("merging Tblpdtpindaanakta instance");
		try {
			Tblpdtpindaanakta result = (Tblpdtpindaanakta) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtpindaanakta instance) {
		log.debug("attaching dirty Tblpdtpindaanakta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtpindaanakta instance) {
		log.debug("attaching clean Tblpdtpindaanakta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}