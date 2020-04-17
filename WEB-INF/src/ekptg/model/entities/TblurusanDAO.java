package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblurusan entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblurusan
 * @author MyEclipse Persistence Tools
 */

public class TblurusanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblurusanDAO.class);
	// property constants
	public static final String KOD_PROSES = "kodProses";
	public static final String KOD_FAIL = "kodFail";
	public static final String KOD_PENGGUNA = "kodPengguna";
	public static final String KOD_STATUS_URUSAN = "kodStatusUrusan";
	public static final String CATATAN = "catatan";

	public void save(Tblurusan transientInstance) {
		log.debug("saving Tblurusan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblurusan persistentInstance) {
		log.debug("deleting Tblurusan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblurusan findById(java.lang.Long id) {
		log.debug("getting Tblurusan instance with id: " + id);
		try {
			Tblurusan instance = (Tblurusan) getSession().get(
					"ekptg.model.entities.Tblurusan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblurusan instance) {
		log.debug("finding Tblurusan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblurusan").add(
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
		log.debug("finding Tblurusan instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblurusan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodProses(Object kodProses) {
		return findByProperty(KOD_PROSES, kodProses);
	}

	public List findByKodFail(Object kodFail) {
		return findByProperty(KOD_FAIL, kodFail);
	}

	public List findByKodPengguna(Object kodPengguna) {
		return findByProperty(KOD_PENGGUNA, kodPengguna);
	}

	public List findByKodStatusUrusan(Object kodStatusUrusan) {
		return findByProperty(KOD_STATUS_URUSAN, kodStatusUrusan);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findAll() {
		log.debug("finding all Tblurusan instances");
		try {
			String queryString = "from Tblurusan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblurusan merge(Tblurusan detachedInstance) {
		log.debug("merging Tblurusan instance");
		try {
			Tblurusan result = (Tblurusan) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblurusan instance) {
		log.debug("attaching dirty Tblurusan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblurusan instance) {
		log.debug("attaching clean Tblurusan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}