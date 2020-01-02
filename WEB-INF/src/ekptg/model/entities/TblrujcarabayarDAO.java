package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujcarabayar entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujcarabayar
 * @author MyEclipse Persistence Tools
 */

public class TblrujcarabayarDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujcarabayarDAO.class);
	// property constants
	public static final String KOD_CARA_BAYAR = "kodCaraBayar";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujcarabayar transientInstance) {
		log.debug("saving Tblrujcarabayar instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujcarabayar persistentInstance) {
		log.debug("deleting Tblrujcarabayar instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujcarabayar findById(java.lang.Long id) {
		log.debug("getting Tblrujcarabayar instance with id: " + id);
		try {
			Tblrujcarabayar instance = (Tblrujcarabayar) getSession().get(
					"ekptg.model.entities.Tblrujcarabayar", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujcarabayar instance) {
		log.debug("finding Tblrujcarabayar instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujcarabayar").add(
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
		log.debug("finding Tblrujcarabayar instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujcarabayar as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodCaraBayar(Object kodCaraBayar) {
		return findByProperty(KOD_CARA_BAYAR, kodCaraBayar);
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
		log.debug("finding all Tblrujcarabayar instances");
		try {
			String queryString = "from Tblrujcarabayar";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujcarabayar merge(Tblrujcarabayar detachedInstance) {
		log.debug("merging Tblrujcarabayar instance");
		try {
			Tblrujcarabayar result = (Tblrujcarabayar) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujcarabayar instance) {
		log.debug("attaching dirty Tblrujcarabayar instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujcarabayar instance) {
		log.debug("attaching clean Tblrujcarabayar instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}