package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblproses entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblproses
 * @author MyEclipse Persistence Tools
 */

public class TblprosesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblprosesDAO.class);
	// property constants
	public static final String KOD_PROSES = "kodProses";
	public static final String NAMA_PROSES = "namaProses";
	public static final String KETERANGAN = "keterangan";
	public static final String BM_TENAGAKERJA = "bmTenagakerja";

	public void save(Tblproses transientInstance) {
		log.debug("saving Tblproses instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblproses persistentInstance) {
		log.debug("deleting Tblproses instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblproses findById(java.lang.Long id) {
		log.debug("getting Tblproses instance with id: " + id);
		try {
			Tblproses instance = (Tblproses) getSession().get(
					"ekptg.model.entities.Tblproses", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblproses instance) {
		log.debug("finding Tblproses instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblproses").add(
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
		log.debug("finding Tblproses instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblproses as model where model."
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

	public List findByNamaProses(Object namaProses) {
		return findByProperty(NAMA_PROSES, namaProses);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByBmTenagakerja(Object bmTenagakerja) {
		return findByProperty(BM_TENAGAKERJA, bmTenagakerja);
	}

	public List findAll() {
		log.debug("finding all Tblproses instances");
		try {
			String queryString = "from Tblproses";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblproses merge(Tblproses detachedInstance) {
		log.debug("merging Tblproses instance");
		try {
			Tblproses result = (Tblproses) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblproses instance) {
		log.debug("attaching dirty Tblproses instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblproses instance) {
		log.debug("attaching clean Tblproses instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}