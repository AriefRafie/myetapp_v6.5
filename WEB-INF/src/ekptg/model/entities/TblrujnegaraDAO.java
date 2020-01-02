package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujnegara entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujnegara
 * @author MyEclipse Persistence Tools
 */

public class TblrujnegaraDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujnegaraDAO.class);
	// property constants
	public static final String KOD_NEGARA = "kodNegara";
	public static final String NAMA_NEGARA = "namaNegara";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujnegara transientInstance) {
		log.debug("saving Tblrujnegara instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujnegara persistentInstance) {
		log.debug("deleting Tblrujnegara instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujnegara findById(java.lang.Long id) {
		log.debug("getting Tblrujnegara instance with id: " + id);
		try {
			Tblrujnegara instance = (Tblrujnegara) getSession().get(
					"ekptg.model.entities.Tblrujnegara", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujnegara instance) {
		log.debug("finding Tblrujnegara instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujnegara").add(
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
		log.debug("finding Tblrujnegara instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujnegara as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodNegara(Object kodNegara) {
		return findByProperty(KOD_NEGARA, kodNegara);
	}

	public List findByNamaNegara(Object namaNegara) {
		return findByProperty(NAMA_NEGARA, namaNegara);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujnegara instances");
		try {
			String queryString = "from Tblrujnegara";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujnegara merge(Tblrujnegara detachedInstance) {
		log.debug("merging Tblrujnegara instance");
		try {
			Tblrujnegara result = (Tblrujnegara) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujnegara instance) {
		log.debug("attaching dirty Tblrujnegara instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujnegara instance) {
		log.debug("attaching clean Tblrujnegara instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}