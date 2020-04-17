package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpperenggankkplpsn entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpperenggankkplpsn
 * @author MyEclipse Persistence Tools
 */

public class TblphpperenggankkplpsnDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpperenggankkplpsnDAO.class);
	// property constants
	public static final String PERENGGAN = "perenggan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpperenggankkplpsn transientInstance) {
		log.debug("saving Tblphpperenggankkplpsn instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpperenggankkplpsn persistentInstance) {
		log.debug("deleting Tblphpperenggankkplpsn instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpperenggankkplpsn findById(java.lang.Long id) {
		log.debug("getting Tblphpperenggankkplpsn instance with id: " + id);
		try {
			Tblphpperenggankkplpsn instance = (Tblphpperenggankkplpsn) getSession()
					.get("ekptg.model.entities.Tblphpperenggankkplpsn", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpperenggankkplpsn instance) {
		log.debug("finding Tblphpperenggankkplpsn instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpperenggankkplpsn").add(
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
		log.debug("finding Tblphpperenggankkplpsn instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpperenggankkplpsn as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPerenggan(Object perenggan) {
		return findByProperty(PERENGGAN, perenggan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpperenggankkplpsn instances");
		try {
			String queryString = "from Tblphpperenggankkplpsn";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpperenggankkplpsn merge(Tblphpperenggankkplpsn detachedInstance) {
		log.debug("merging Tblphpperenggankkplpsn instance");
		try {
			Tblphpperenggankkplpsn result = (Tblphpperenggankkplpsn) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpperenggankkplpsn instance) {
		log.debug("attaching dirty Tblphpperenggankkplpsn instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpperenggankkplpsn instance) {
		log.debug("attaching clean Tblphpperenggankkplpsn instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}