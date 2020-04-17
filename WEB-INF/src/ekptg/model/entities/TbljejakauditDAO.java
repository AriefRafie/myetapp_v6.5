package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tbljejakaudit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tbljejakaudit
 * @author MyEclipse Persistence Tools
 */

public class TbljejakauditDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TbljejakauditDAO.class);
	// property constants
	public static final String KOD_PENGGUNA = "kodPengguna";
	public static final String KOD_SKRIN = "kodSkrin";
	public static final String CATATAN = "catatan";

	public void save(Tbljejakaudit transientInstance) {
		log.debug("saving Tbljejakaudit instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tbljejakaudit persistentInstance) {
		log.debug("deleting Tbljejakaudit instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tbljejakaudit findById(java.lang.Long id) {
		log.debug("getting Tbljejakaudit instance with id: " + id);
		try {
			Tbljejakaudit instance = (Tbljejakaudit) getSession().get(
					"ekptg.model.entities.Tbljejakaudit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tbljejakaudit instance) {
		log.debug("finding Tbljejakaudit instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tbljejakaudit").add(
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
		log.debug("finding Tbljejakaudit instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tbljejakaudit as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodPengguna(Object kodPengguna) {
		return findByProperty(KOD_PENGGUNA, kodPengguna);
	}

	public List findByKodSkrin(Object kodSkrin) {
		return findByProperty(KOD_SKRIN, kodSkrin);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findAll() {
		log.debug("finding all Tbljejakaudit instances");
		try {
			String queryString = "from Tbljejakaudit";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tbljejakaudit merge(Tbljejakaudit detachedInstance) {
		log.debug("merging Tbljejakaudit instance");
		try {
			Tbljejakaudit result = (Tbljejakaudit) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tbljejakaudit instance) {
		log.debug("attaching dirty Tbljejakaudit instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tbljejakaudit instance) {
		log.debug("attaching clean Tbljejakaudit instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}