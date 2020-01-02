package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptmmkkeputusan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptmmkkeputusan
 * @author MyEclipse Persistence Tools
 */

public class TblpptmmkkeputusanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpptmmkkeputusanDAO.class);
	// property constants
	public static final String ID_MMK = "idMmk";
	public static final String STATUS_KEPUTUSAN = "statusKeputusan";
	public static final String ULASAN = "ulasan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptmmkkeputusan transientInstance) {
		log.debug("saving Tblpptmmkkeputusan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptmmkkeputusan persistentInstance) {
		log.debug("deleting Tblpptmmkkeputusan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptmmkkeputusan findById(java.lang.Long id) {
		log.debug("getting Tblpptmmkkeputusan instance with id: " + id);
		try {
			Tblpptmmkkeputusan instance = (Tblpptmmkkeputusan) getSession()
					.get("ekptg.model.entities.Tblpptmmkkeputusan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptmmkkeputusan instance) {
		log.debug("finding Tblpptmmkkeputusan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptmmkkeputusan").add(
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
		log.debug("finding Tblpptmmkkeputusan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptmmkkeputusan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdMmk(Object idMmk) {
		return findByProperty(ID_MMK, idMmk);
	}

	public List findByStatusKeputusan(Object statusKeputusan) {
		return findByProperty(STATUS_KEPUTUSAN, statusKeputusan);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblpptmmkkeputusan instances");
		try {
			String queryString = "from Tblpptmmkkeputusan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptmmkkeputusan merge(Tblpptmmkkeputusan detachedInstance) {
		log.debug("merging Tblpptmmkkeputusan instance");
		try {
			Tblpptmmkkeputusan result = (Tblpptmmkkeputusan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptmmkkeputusan instance) {
		log.debug("attaching dirty Tblpptmmkkeputusan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptmmkkeputusan instance) {
		log.debug("attaching clean Tblpptmmkkeputusan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}