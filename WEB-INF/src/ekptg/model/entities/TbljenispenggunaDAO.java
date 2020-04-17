package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tbljenispengguna entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tbljenispengguna
 * @author MyEclipse Persistence Tools
 */

public class TbljenispenggunaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TbljenispenggunaDAO.class);
	// property constants
	public static final String KOD_JENIS_PENGGUNA = "kodJenisPengguna";
	public static final String JENIS_PENGGUNA = "jenisPengguna";
	public static final String KETERANGAN = "keterangan";

	public void save(Tbljenispengguna transientInstance) {
		log.debug("saving Tbljenispengguna instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tbljenispengguna persistentInstance) {
		log.debug("deleting Tbljenispengguna instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tbljenispengguna findById(java.lang.Long id) {
		log.debug("getting Tbljenispengguna instance with id: " + id);
		try {
			Tbljenispengguna instance = (Tbljenispengguna) getSession().get(
					"ekptg.model.entities.Tbljenispengguna", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tbljenispengguna instance) {
		log.debug("finding Tbljenispengguna instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tbljenispengguna").add(
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
		log.debug("finding Tbljenispengguna instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tbljenispengguna as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodJenisPengguna(Object kodJenisPengguna) {
		return findByProperty(KOD_JENIS_PENGGUNA, kodJenisPengguna);
	}

	public List findByJenisPengguna(Object jenisPengguna) {
		return findByProperty(JENIS_PENGGUNA, jenisPengguna);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tbljenispengguna instances");
		try {
			String queryString = "from Tbljenispengguna";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tbljenispengguna merge(Tbljenispengguna detachedInstance) {
		log.debug("merging Tbljenispengguna instance");
		try {
			Tbljenispengguna result = (Tbljenispengguna) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tbljenispengguna instance) {
		log.debug("attaching dirty Tbljenispengguna instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tbljenispengguna instance) {
		log.debug("attaching clean Tbljenispengguna instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}