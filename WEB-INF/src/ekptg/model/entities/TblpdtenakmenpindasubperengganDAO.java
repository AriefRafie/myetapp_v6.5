package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtenakmenpindasubperenggan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtenakmenpindasubperenggan
 * @author MyEclipse Persistence Tools
 */

public class TblpdtenakmenpindasubperengganDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtenakmenpindasubperengganDAO.class);
	// property constants
	public static final String NOTA_BIRAI = "notaBirai";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtenakmenpindasubperenggan transientInstance) {
		log.debug("saving Tblpdtenakmenpindasubperenggan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtenakmenpindasubperenggan persistentInstance) {
		log.debug("deleting Tblpdtenakmenpindasubperenggan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpindasubperenggan findById(java.lang.Long id) {
		log.debug("getting Tblpdtenakmenpindasubperenggan instance with id: "
				+ id);
		try {
			Tblpdtenakmenpindasubperenggan instance = (Tblpdtenakmenpindasubperenggan) getSession()
					.get("ekptg.model.entities.Tblpdtenakmenpindasubperenggan",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtenakmenpindasubperenggan instance) {
		log.debug("finding Tblpdtenakmenpindasubperenggan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtenakmenpindasubperenggan").add(
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
		log
				.debug("finding Tblpdtenakmenpindasubperenggan instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtenakmenpindasubperenggan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNotaBirai(Object notaBirai) {
		return findByProperty(NOTA_BIRAI, notaBirai);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtenakmenpindasubperenggan instances");
		try {
			String queryString = "from Tblpdtenakmenpindasubperenggan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpindasubperenggan merge(
			Tblpdtenakmenpindasubperenggan detachedInstance) {
		log.debug("merging Tblpdtenakmenpindasubperenggan instance");
		try {
			Tblpdtenakmenpindasubperenggan result = (Tblpdtenakmenpindasubperenggan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtenakmenpindasubperenggan instance) {
		log.debug("attaching dirty Tblpdtenakmenpindasubperenggan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtenakmenpindasubperenggan instance) {
		log.debug("attaching clean Tblpdtenakmenpindasubperenggan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}