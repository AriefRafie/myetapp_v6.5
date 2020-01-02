package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujppkjenisperintah entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujppkjenisperintah
 * @author MyEclipse Persistence Tools
 */

public class TblrujppkjenisperintahDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujppkjenisperintahDAO.class);
	// property constants
	public static final String KOD = "kod";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujppkjenisperintah transientInstance) {
		log.debug("saving Tblrujppkjenisperintah instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujppkjenisperintah persistentInstance) {
		log.debug("deleting Tblrujppkjenisperintah instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujppkjenisperintah findById(java.lang.Long id) {
		log.debug("getting Tblrujppkjenisperintah instance with id: " + id);
		try {
			Tblrujppkjenisperintah instance = (Tblrujppkjenisperintah) getSession()
					.get("ekptg.model.entities.Tblrujppkjenisperintah", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujppkjenisperintah instance) {
		log.debug("finding Tblrujppkjenisperintah instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujppkjenisperintah").add(
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
		log.debug("finding Tblrujppkjenisperintah instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujppkjenisperintah as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKod(Object kod) {
		return findByProperty(KOD, kod);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujppkjenisperintah instances");
		try {
			String queryString = "from Tblrujppkjenisperintah";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujppkjenisperintah merge(Tblrujppkjenisperintah detachedInstance) {
		log.debug("merging Tblrujppkjenisperintah instance");
		try {
			Tblrujppkjenisperintah result = (Tblrujppkjenisperintah) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujppkjenisperintah instance) {
		log.debug("attaching dirty Tblrujppkjenisperintah instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujppkjenisperintah instance) {
		log.debug("attaching clean Tblrujppkjenisperintah instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}