package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblkumpulanpengguna entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblkumpulanpengguna
 * @author MyEclipse Persistence Tools
 */

public class TblkumpulanpenggunaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblkumpulanpenggunaDAO.class);
	// property constants
	public static final String KOD_PENGGUNA = "kodPengguna";
	public static final String KOD_KUMPULAN = "kodKumpulan";

	public void save(Tblkumpulanpengguna transientInstance) {
		log.debug("saving Tblkumpulanpengguna instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblkumpulanpengguna persistentInstance) {
		log.debug("deleting Tblkumpulanpengguna instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblkumpulanpengguna findById(java.lang.Long id) {
		log.debug("getting Tblkumpulanpengguna instance with id: " + id);
		try {
			Tblkumpulanpengguna instance = (Tblkumpulanpengguna) getSession()
					.get("ekptg.model.entities.Tblkumpulanpengguna", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblkumpulanpengguna instance) {
		log.debug("finding Tblkumpulanpengguna instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblkumpulanpengguna").add(
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
		log.debug("finding Tblkumpulanpengguna instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblkumpulanpengguna as model where model."
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

	public List findByKodKumpulan(Object kodKumpulan) {
		return findByProperty(KOD_KUMPULAN, kodKumpulan);
	}

	public List findAll() {
		log.debug("finding all Tblkumpulanpengguna instances");
		try {
			String queryString = "from Tblkumpulanpengguna";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblkumpulanpengguna merge(Tblkumpulanpengguna detachedInstance) {
		log.debug("merging Tblkumpulanpengguna instance");
		try {
			Tblkumpulanpengguna result = (Tblkumpulanpengguna) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblkumpulanpengguna instance) {
		log.debug("attaching dirty Tblkumpulanpengguna instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblkumpulanpengguna instance) {
		log.debug("attaching clean Tblkumpulanpengguna instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}