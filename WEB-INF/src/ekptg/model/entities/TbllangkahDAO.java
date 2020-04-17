package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tbllangkah entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tbllangkah
 * @author MyEclipse Persistence Tools
 */

public class TbllangkahDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TbllangkahDAO.class);
	// property constants
	public static final String KOD_PROSES = "kodProses";
	public static final String KOD_LANGKAH = "kodLangkah";
	public static final String NAMA_LANGKAH = "namaLangkah";
	public static final String KETERANGAN = "keterangan";
	public static final String BM_MASA = "bmMasa";
	public static final String BM_KOS = "bmKos";
	public static final String ALAMAT_LANGKAH = "alamatLangkah";

	public void save(Tbllangkah transientInstance) {
		log.debug("saving Tbllangkah instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tbllangkah persistentInstance) {
		log.debug("deleting Tbllangkah instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tbllangkah findById(java.lang.Long id) {
		log.debug("getting Tbllangkah instance with id: " + id);
		try {
			Tbllangkah instance = (Tbllangkah) getSession().get(
					"ekptg.model.entities.Tbllangkah", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tbllangkah instance) {
		log.debug("finding Tbllangkah instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tbllangkah").add(
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
		log.debug("finding Tbllangkah instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tbllangkah as model where model."
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

	public List findByKodLangkah(Object kodLangkah) {
		return findByProperty(KOD_LANGKAH, kodLangkah);
	}

	public List findByNamaLangkah(Object namaLangkah) {
		return findByProperty(NAMA_LANGKAH, namaLangkah);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByBmMasa(Object bmMasa) {
		return findByProperty(BM_MASA, bmMasa);
	}

	public List findByBmKos(Object bmKos) {
		return findByProperty(BM_KOS, bmKos);
	}

	public List findByAlamatLangkah(Object alamatLangkah) {
		return findByProperty(ALAMAT_LANGKAH, alamatLangkah);
	}

	public List findAll() {
		log.debug("finding all Tbllangkah instances");
		try {
			String queryString = "from Tbllangkah";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tbllangkah merge(Tbllangkah detachedInstance) {
		log.debug("merging Tbllangkah instance");
		try {
			Tbllangkah result = (Tbllangkah) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tbllangkah instance) {
		log.debug("attaching dirty Tbllangkah instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tbllangkah instance) {
		log.debug("attaching clean Tbllangkah instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}