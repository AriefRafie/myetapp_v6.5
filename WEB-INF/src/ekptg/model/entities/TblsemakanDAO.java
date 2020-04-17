package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblsemakan entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblsemakan
 * @author MyEclipse Persistence Tools
 */

public class TblsemakanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblsemakanDAO.class);
	// property constants
	public static final String ID_PARENT = "idParent";
	public static final String KOD_SEMAK = "kodSemak";
	public static final String PERIHAL = "perihal";
	public static final String LAIN_LAIN = "lainLain";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblsemakan transientInstance) {
		log.debug("saving Tblsemakan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblsemakan persistentInstance) {
		log.debug("deleting Tblsemakan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblsemakan findById(java.lang.Long id) {
		log.debug("getting Tblsemakan instance with id: " + id);
		try {
			Tblsemakan instance = (Tblsemakan) getSession().get(
					"ekptg.model.entities.Tblsemakan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblsemakan instance) {
		log.debug("finding Tblsemakan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblsemakan").add(
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
		log.debug("finding Tblsemakan instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblsemakan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdParent(Object idParent) {
		return findByProperty(ID_PARENT, idParent);
	}

	public List findByKodSemak(Object kodSemak) {
		return findByProperty(KOD_SEMAK, kodSemak);
	}

	public List findByPerihal(Object perihal) {
		return findByProperty(PERIHAL, perihal);
	}

	public List findByLainLain(Object lainLain) {
		return findByProperty(LAIN_LAIN, lainLain);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblsemakan instances");
		try {
			String queryString = "from Tblsemakan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblsemakan merge(Tblsemakan detachedInstance) {
		log.debug("merging Tblsemakan instance");
		try {
			Tblsemakan result = (Tblsemakan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblsemakan instance) {
		log.debug("attaching dirty Tblsemakan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblsemakan instance) {
		log.debug("attaching clean Tblsemakan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}