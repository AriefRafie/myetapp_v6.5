package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujnegeri entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujnegeri
 * @author MyEclipse Persistence Tools
 */

public class TblrujnegeriDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujnegeriDAO.class);
	// property constants
	public static final String KOD_NEGERI = "kodNegeri";
	public static final String NAMA_NEGERI = "namaNegeri";
	public static final String ID_NEGARA = "idNegara";
	public static final String KOD_MAMPU = "kodMampu";
	public static final String ABBREV = "abbrev";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujnegeri transientInstance) {
		log.debug("saving Tblrujnegeri instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujnegeri persistentInstance) {
		log.debug("deleting Tblrujnegeri instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujnegeri findById(java.lang.Long id) {
		log.debug("getting Tblrujnegeri instance with id: " + id);
		try {
			Tblrujnegeri instance = (Tblrujnegeri) getSession().get(
					"ekptg.model.entities.Tblrujnegeri", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujnegeri instance) {
		log.debug("finding Tblrujnegeri instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujnegeri").add(
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
		log.debug("finding Tblrujnegeri instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujnegeri as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodNegeri(Object kodNegeri) {
		return findByProperty(KOD_NEGERI, kodNegeri);
	}

	public List findByNamaNegeri(Object namaNegeri) {
		return findByProperty(NAMA_NEGERI, namaNegeri);
	}

	public List findByIdNegara(Object idNegara) {
		return findByProperty(ID_NEGARA, idNegara);
	}

	public List findByKodMampu(Object kodMampu) {
		return findByProperty(KOD_MAMPU, kodMampu);
	}

	public List findByAbbrev(Object abbrev) {
		return findByProperty(ABBREV, abbrev);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujnegeri instances");
		try {
			String queryString = "from Tblrujnegeri";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujnegeri merge(Tblrujnegeri detachedInstance) {
		log.debug("merging Tblrujnegeri instance");
		try {
			Tblrujnegeri result = (Tblrujnegeri) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujnegeri instance) {
		log.debug("attaching dirty Tblrujnegeri instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujnegeri instance) {
		log.debug("attaching clean Tblrujnegeri instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}