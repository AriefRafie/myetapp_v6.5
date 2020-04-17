package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtjadualpindaseksyen entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtjadualpindaseksyen
 * @author MyEclipse Persistence Tools
 */

public class TblpdtjadualpindaseksyenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtjadualpindaseksyenDAO.class);
	// property constants
	public static final String NO_SEKSYEN = "noSeksyen";
	public static final String NAMA_SEKSYEN = "namaSeksyen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtjadualpindaseksyen transientInstance) {
		log.debug("saving Tblpdtjadualpindaseksyen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtjadualpindaseksyen persistentInstance) {
		log.debug("deleting Tblpdtjadualpindaseksyen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtjadualpindaseksyen findById(java.lang.Long id) {
		log.debug("getting Tblpdtjadualpindaseksyen instance with id: " + id);
		try {
			Tblpdtjadualpindaseksyen instance = (Tblpdtjadualpindaseksyen) getSession()
					.get("ekptg.model.entities.Tblpdtjadualpindaseksyen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtjadualpindaseksyen instance) {
		log.debug("finding Tblpdtjadualpindaseksyen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtjadualpindaseksyen").add(
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
		log.debug("finding Tblpdtjadualpindaseksyen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtjadualpindaseksyen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoSeksyen(Object noSeksyen) {
		return findByProperty(NO_SEKSYEN, noSeksyen);
	}

	public List findByNamaSeksyen(Object namaSeksyen) {
		return findByProperty(NAMA_SEKSYEN, namaSeksyen);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtjadualpindaseksyen instances");
		try {
			String queryString = "from Tblpdtjadualpindaseksyen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtjadualpindaseksyen merge(
			Tblpdtjadualpindaseksyen detachedInstance) {
		log.debug("merging Tblpdtjadualpindaseksyen instance");
		try {
			Tblpdtjadualpindaseksyen result = (Tblpdtjadualpindaseksyen) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtjadualpindaseksyen instance) {
		log.debug("attaching dirty Tblpdtjadualpindaseksyen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtjadualpindaseksyen instance) {
		log.debug("attaching clean Tblpdtjadualpindaseksyen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}