package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtaktapindasubperenggan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtaktapindasubperenggan
 * @author MyEclipse Persistence Tools
 */

public class TblpdtaktapindasubperengganDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtaktapindasubperengganDAO.class);
	// property constants
	public static final String NOTA_BIRAI = "notaBirai";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtaktapindasubperenggan transientInstance) {
		log.debug("saving Tblpdtaktapindasubperenggan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtaktapindasubperenggan persistentInstance) {
		log.debug("deleting Tblpdtaktapindasubperenggan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtaktapindasubperenggan findById(java.lang.Long id) {
		log
				.debug("getting Tblpdtaktapindasubperenggan instance with id: "
						+ id);
		try {
			Tblpdtaktapindasubperenggan instance = (Tblpdtaktapindasubperenggan) getSession()
					.get("ekptg.model.entities.Tblpdtaktapindasubperenggan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtaktapindasubperenggan instance) {
		log.debug("finding Tblpdtaktapindasubperenggan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtaktapindasubperenggan").add(
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
				.debug("finding Tblpdtaktapindasubperenggan instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtaktapindasubperenggan as model where model."
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
		log.debug("finding all Tblpdtaktapindasubperenggan instances");
		try {
			String queryString = "from Tblpdtaktapindasubperenggan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtaktapindasubperenggan merge(
			Tblpdtaktapindasubperenggan detachedInstance) {
		log.debug("merging Tblpdtaktapindasubperenggan instance");
		try {
			Tblpdtaktapindasubperenggan result = (Tblpdtaktapindasubperenggan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtaktapindasubperenggan instance) {
		log.debug("attaching dirty Tblpdtaktapindasubperenggan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtaktapindasubperenggan instance) {
		log.debug("attaching clean Tblpdtaktapindasubperenggan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}