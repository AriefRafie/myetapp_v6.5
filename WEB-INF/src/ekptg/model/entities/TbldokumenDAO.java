package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tbldokumen entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tbldokumen
 * @author MyEclipse Persistence Tools
 */

public class TbldokumenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TbldokumenDAO.class);
	// property constants
	public static final String KOD_FAIL = "kodFail";
	public static final String KOD_DOKUMEN = "kodDokumen";
	public static final String TAJUK_DOKUMEN = "tajukDokumen";
	public static final String CATATAN = "catatan";
	public static final String KOD_PENGGUNA = "kodPengguna";
	public static final String DOKUMEN = "dokumen";
	public static final String TANDATANGAN = "tandatangan";
	public static final String KOD_KATEGORI_KESELAMATAN = "kodKategoriKeselamatan";
	public static final String KOD_KATEGORI_DOKUMEN = "kodKategoriDokumen";

	public void save(Tbldokumen transientInstance) {
		log.debug("saving Tbldokumen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tbldokumen persistentInstance) {
		log.debug("deleting Tbldokumen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tbldokumen findById(java.lang.Long id) {
		log.debug("getting Tbldokumen instance with id: " + id);
		try {
			Tbldokumen instance = (Tbldokumen) getSession().get(
					"ekptg.model.entities.Tbldokumen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tbldokumen instance) {
		log.debug("finding Tbldokumen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tbldokumen").add(
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
		log.debug("finding Tbldokumen instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tbldokumen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodFail(Object kodFail) {
		return findByProperty(KOD_FAIL, kodFail);
	}

	public List findByKodDokumen(Object kodDokumen) {
		return findByProperty(KOD_DOKUMEN, kodDokumen);
	}

	public List findByTajukDokumen(Object tajukDokumen) {
		return findByProperty(TAJUK_DOKUMEN, tajukDokumen);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByKodPengguna(Object kodPengguna) {
		return findByProperty(KOD_PENGGUNA, kodPengguna);
	}

	public List findByDokumen(Object dokumen) {
		return findByProperty(DOKUMEN, dokumen);
	}

	public List findByTandatangan(Object tandatangan) {
		return findByProperty(TANDATANGAN, tandatangan);
	}

	public List findByKodKategoriKeselamatan(Object kodKategoriKeselamatan) {
		return findByProperty(KOD_KATEGORI_KESELAMATAN, kodKategoriKeselamatan);
	}

	public List findByKodKategoriDokumen(Object kodKategoriDokumen) {
		return findByProperty(KOD_KATEGORI_DOKUMEN, kodKategoriDokumen);
	}

	public List findAll() {
		log.debug("finding all Tbldokumen instances");
		try {
			String queryString = "from Tbldokumen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tbldokumen merge(Tbldokumen detachedInstance) {
		log.debug("merging Tbldokumen instance");
		try {
			Tbldokumen result = (Tbldokumen) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tbldokumen instance) {
		log.debug("attaching dirty Tbldokumen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tbldokumen instance) {
		log.debug("attaching clean Tbldokumen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}