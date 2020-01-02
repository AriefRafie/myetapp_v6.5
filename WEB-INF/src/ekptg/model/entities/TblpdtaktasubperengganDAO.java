package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtaktasubperenggan entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtaktasubperenggan
 * @author MyEclipse Persistence Tools
 */

public class TblpdtaktasubperengganDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtaktasubperengganDAO.class);
	// property constants
	public static final String NOTA_BIRAI = "notaBirai";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtaktasubperenggan transientInstance) {
		log.debug("saving Tblpdtaktasubperenggan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtaktasubperenggan persistentInstance) {
		log.debug("deleting Tblpdtaktasubperenggan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtaktasubperenggan findById(java.lang.Long id) {
		log.debug("getting Tblpdtaktasubperenggan instance with id: " + id);
		try {
			Tblpdtaktasubperenggan instance = (Tblpdtaktasubperenggan) getSession()
					.get("ekptg.model.entities.Tblpdtaktasubperenggan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtaktasubperenggan instance) {
		log.debug("finding Tblpdtaktasubperenggan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtaktasubperenggan").add(
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
		log.debug("finding Tblpdtaktasubperenggan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtaktasubperenggan as model where model."
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
		log.debug("finding all Tblpdtaktasubperenggan instances");
		try {
			String queryString = "from Tblpdtaktasubperenggan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtaktasubperenggan merge(Tblpdtaktasubperenggan detachedInstance) {
		log.debug("merging Tblpdtaktasubperenggan instance");
		try {
			Tblpdtaktasubperenggan result = (Tblpdtaktasubperenggan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtaktasubperenggan instance) {
		log.debug("attaching dirty Tblpdtaktasubperenggan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtaktasubperenggan instance) {
		log.debug("attaching clean Tblpdtaktasubperenggan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}