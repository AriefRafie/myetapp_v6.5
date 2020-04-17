package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujpfdtarafkeselamatan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblrujpfdtarafkeselamatan
 * @author MyEclipse Persistence Tools
 */

public class TblrujpfdtarafkeselamatanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujpfdtarafkeselamatanDAO.class);
	// property constants
	public static final String KOD_TARAF_KESELAMATAN = "kodTarafKeselamatan";
	public static final String TARAF_KESELAMATAN = "tarafKeselamatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujpfdtarafkeselamatan transientInstance) {
		log.debug("saving Tblrujpfdtarafkeselamatan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujpfdtarafkeselamatan persistentInstance) {
		log.debug("deleting Tblrujpfdtarafkeselamatan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujpfdtarafkeselamatan findById(java.lang.Long id) {
		log.debug("getting Tblrujpfdtarafkeselamatan instance with id: " + id);
		try {
			Tblrujpfdtarafkeselamatan instance = (Tblrujpfdtarafkeselamatan) getSession()
					.get("ekptg.model.entities.Tblrujpfdtarafkeselamatan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujpfdtarafkeselamatan instance) {
		log.debug("finding Tblrujpfdtarafkeselamatan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujpfdtarafkeselamatan").add(
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
		log.debug("finding Tblrujpfdtarafkeselamatan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujpfdtarafkeselamatan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodTarafKeselamatan(Object kodTarafKeselamatan) {
		return findByProperty(KOD_TARAF_KESELAMATAN, kodTarafKeselamatan);
	}

	public List findByTarafKeselamatan(Object tarafKeselamatan) {
		return findByProperty(TARAF_KESELAMATAN, tarafKeselamatan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujpfdtarafkeselamatan instances");
		try {
			String queryString = "from Tblrujpfdtarafkeselamatan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujpfdtarafkeselamatan merge(
			Tblrujpfdtarafkeselamatan detachedInstance) {
		log.debug("merging Tblrujpfdtarafkeselamatan instance");
		try {
			Tblrujpfdtarafkeselamatan result = (Tblrujpfdtarafkeselamatan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujpfdtarafkeselamatan instance) {
		log.debug("attaching dirty Tblrujpfdtarafkeselamatan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujpfdtarafkeselamatan instance) {
		log.debug("attaching clean Tblrujpfdtarafkeselamatan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}