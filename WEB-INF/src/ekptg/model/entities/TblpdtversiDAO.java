package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtversi entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtversi
 * @author MyEclipse Persistence Tools
 */

public class TblpdtversiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpdtversiDAO.class);

	// property constants

	public void save(Tblpdtversi transientInstance) {
		log.debug("saving Tblpdtversi instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtversi persistentInstance) {
		log.debug("deleting Tblpdtversi instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtversi findById(java.lang.Long id) {
		log.debug("getting Tblpdtversi instance with id: " + id);
		try {
			Tblpdtversi instance = (Tblpdtversi) getSession().get(
					"ekptg.model.entities.Tblpdtversi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtversi instance) {
		log.debug("finding Tblpdtversi instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtversi").add(
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
		log.debug("finding Tblpdtversi instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblpdtversi as model where model."
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
		log.debug("finding all Tblpdtversi instances");
		try {
			String queryString = "from Tblpdtversi";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtversi merge(Tblpdtversi detachedInstance) {
		log.debug("merging Tblpdtversi instance");
		try {
			Tblpdtversi result = (Tblpdtversi) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtversi instance) {
		log.debug("attaching dirty Tblpdtversi instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtversi instance) {
		log.debug("attaching clean Tblpdtversi instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}