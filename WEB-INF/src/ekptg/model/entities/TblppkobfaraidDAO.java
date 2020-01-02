package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkobfaraid entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkobfaraid
 * @author MyEclipse Persistence Tools
 */

public class TblppkobfaraidDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkobfaraidDAO.class);
	// property constants
	public static final String STATUS_HIDUP = "statusHidup";
	public static final String BA_FARAID = "baFaraid";
	public static final String BB_FARAID = "bbFaraid";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkobfaraid transientInstance) {
		log.debug("saving Tblppkobfaraid instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkobfaraid persistentInstance) {
		log.debug("deleting Tblppkobfaraid instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkobfaraid findById(java.lang.Long id) {
		log.debug("getting Tblppkobfaraid instance with id: " + id);
		try {
			Tblppkobfaraid instance = (Tblppkobfaraid) getSession().get(
					"ekptg.model.entities.Tblppkobfaraid", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkobfaraid instance) {
		log.debug("finding Tblppkobfaraid instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkobfaraid").add(
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
		log.debug("finding Tblppkobfaraid instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkobfaraid as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStatusHidup(Object statusHidup) {
		return findByProperty(STATUS_HIDUP, statusHidup);
	}

	public List findByBaFaraid(Object baFaraid) {
		return findByProperty(BA_FARAID, baFaraid);
	}

	public List findByBbFaraid(Object bbFaraid) {
		return findByProperty(BB_FARAID, bbFaraid);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkobfaraid instances");
		try {
			String queryString = "from Tblppkobfaraid";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkobfaraid merge(Tblppkobfaraid detachedInstance) {
		log.debug("merging Tblppkobfaraid instance");
		try {
			Tblppkobfaraid result = (Tblppkobfaraid) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkobfaraid instance) {
		log.debug("attaching dirty Tblppkobfaraid instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkobfaraid instance) {
		log.debug("attaching clean Tblppkobfaraid instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}