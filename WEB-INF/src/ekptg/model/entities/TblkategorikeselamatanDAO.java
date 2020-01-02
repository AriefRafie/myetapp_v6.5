package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblkategorikeselamatan entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblkategorikeselamatan
 * @author MyEclipse Persistence Tools
 */

public class TblkategorikeselamatanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblkategorikeselamatanDAO.class);
	// property constants
	public static final String KOD_KATEGORI_KESELAMATAN = "kodKategoriKeselamatan";
	public static final String KATEGORY_KESELAMATAN = "kategoryKeselamatan";
	public static final String KETERANGAN = "keterangan";

	public void save(Tblkategorikeselamatan transientInstance) {
		log.debug("saving Tblkategorikeselamatan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblkategorikeselamatan persistentInstance) {
		log.debug("deleting Tblkategorikeselamatan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblkategorikeselamatan findById(java.lang.Long id) {
		log.debug("getting Tblkategorikeselamatan instance with id: " + id);
		try {
			Tblkategorikeselamatan instance = (Tblkategorikeselamatan) getSession()
					.get("ekptg.model.entities.Tblkategorikeselamatan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblkategorikeselamatan instance) {
		log.debug("finding Tblkategorikeselamatan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblkategorikeselamatan").add(
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
		log.debug("finding Tblkategorikeselamatan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblkategorikeselamatan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodKategoriKeselamatan(Object kodKategoriKeselamatan) {
		return findByProperty(KOD_KATEGORI_KESELAMATAN, kodKategoriKeselamatan);
	}

	public List findByKategoryKeselamatan(Object kategoryKeselamatan) {
		return findByProperty(KATEGORY_KESELAMATAN, kategoryKeselamatan);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tblkategorikeselamatan instances");
		try {
			String queryString = "from Tblkategorikeselamatan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblkategorikeselamatan merge(Tblkategorikeselamatan detachedInstance) {
		log.debug("merging Tblkategorikeselamatan instance");
		try {
			Tblkategorikeselamatan result = (Tblkategorikeselamatan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblkategorikeselamatan instance) {
		log.debug("attaching dirty Tblkategorikeselamatan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblkategorikeselamatan instance) {
		log.debug("attaching clean Tblkategorikeselamatan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}