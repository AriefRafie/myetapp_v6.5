package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpversikoordinat entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpversikoordinat
 * @author MyEclipse Persistence Tools
 */

public class TblphpversikoordinatDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpversikoordinatDAO.class);
	// property constants
	public static final String NO_VERSI = "noVersi";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpversikoordinat transientInstance) {
		log.debug("saving Tblphpversikoordinat instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpversikoordinat persistentInstance) {
		log.debug("deleting Tblphpversikoordinat instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpversikoordinat findById(java.lang.Long id) {
		log.debug("getting Tblphpversikoordinat instance with id: " + id);
		try {
			Tblphpversikoordinat instance = (Tblphpversikoordinat) getSession()
					.get("ekptg.model.entities.Tblphpversikoordinat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpversikoordinat instance) {
		log.debug("finding Tblphpversikoordinat instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpversikoordinat").add(
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
		log.debug("finding Tblphpversikoordinat instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpversikoordinat as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoVersi(Object noVersi) {
		return findByProperty(NO_VERSI, noVersi);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpversikoordinat instances");
		try {
			String queryString = "from Tblphpversikoordinat";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpversikoordinat merge(Tblphpversikoordinat detachedInstance) {
		log.debug("merging Tblphpversikoordinat instance");
		try {
			Tblphpversikoordinat result = (Tblphpversikoordinat) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpversikoordinat instance) {
		log.debug("attaching dirty Tblphpversikoordinat instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpversikoordinat instance) {
		log.debug("attaching clean Tblphpversikoordinat instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}